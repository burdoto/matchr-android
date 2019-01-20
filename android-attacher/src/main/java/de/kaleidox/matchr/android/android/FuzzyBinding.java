package de.kaleidox.matchr.android.android;

import de.kaleidox.matchr.android.android.event.ResultsChangeEvent;
import de.kaleidox.matchr.android.android.listener.GenericListener;
import de.kaleidox.matchr.android.android.listener.ListenerManager;

public interface FuzzyBinding<T> {
    ListenerManager<GenericListener<ResultsChangeEvent<T>>> addResultsChangeListener(
            GenericListener<ResultsChangeEvent<T>> listener);
}
