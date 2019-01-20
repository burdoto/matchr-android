package de.kaleidox.matchr.android.bindings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import de.kaleidox.matchr.android.MatchrBinding;
import de.kaleidox.matchr.android.event.ResultsChangeEvent;
import de.kaleidox.matchr.android.functions.ResultProvider;
import de.kaleidox.matchr.android.listener.GenericListener;
import de.kaleidox.matchr.android.listener.ListenerManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import me.xdrop.fuzzywuzzy.model.Result;

public class TextViewMatchrBinding<T> implements MatchrBinding<T> {
    private final List<ListenerManager<GenericListener<ResultsChangeEvent<T>>>> listenerManagers;
    private final TextView textView;
    private final ResultProvider<T> resultProvider;
    private final Collection<T> options;
    private List<Result<T>> prevResults;

    public TextViewMatchrBinding(TextView textView, final Collection<T> options, final ResultProvider<T> resultProvider) {
        this.textView = textView;
        this.resultProvider = resultProvider;
        this.options = options;
        this.listenerManagers = new ArrayList<>();

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore this callback
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean call = false;
                List<Result<T>> results = resultProvider.fetch(s.toString(), options);
                int matches = 0;
                // because we are in a listener thread, this will not hurt too bad
                if (results.size() == prevResults.size()) {
                    for (int i = 0; i < results.size(); i++) {
                        Result<T> newRes = results.get(i);
                        Result<T> oldRes = prevResults.get(i);
                        if (!newRes.getString().equals(oldRes.getString())) call = true;
                        if (call) break;
                    }
                } else call = true;
                if (call) {
                    prevResults = results;
                    callListeners();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // ignore this callback
            }
        });
    }

    @Override
    public ListenerManager<GenericListener<ResultsChangeEvent<T>>> addResultsChangeListener(GenericListener<ResultsChangeEvent<T>> listener) {
        ResultsListenerManager manager = new ResultsListenerManager(listener);
        listenerManagers.add(manager);
        return manager;
    }

    private void callListeners() {
        ResultsChangeEvent<T> event = new ResultsChangeEvent<>(prevResults);
        for (ListenerManager<GenericListener<ResultsChangeEvent<T>>> listenerManager : listenerManagers)
            listenerManager.getListener().onEvent(event);
    }

    private class ResultsListenerManager implements ListenerManager<GenericListener<ResultsChangeEvent<T>>> {
        private final GenericListener<ResultsChangeEvent<T>> listener;

        public ResultsListenerManager(GenericListener<ResultsChangeEvent<T>> listener) {
            this.listener = listener;
        }

        @Override
        public boolean detachNow() {
            return listenerManagers.remove(this);
        }

        @Override
        public ScheduledFuture<?> detachIn(long time, TimeUnit unit) {
            return Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
                @Override
                public void run() {
                    listenerManagers.remove(ResultsListenerManager.this);
                }
            }, time, unit);
        }

        @Override
        public GenericListener<ResultsChangeEvent<T>> getListener() {
            return listener;
        }
    }
}
