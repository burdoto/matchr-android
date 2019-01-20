package de.kaleidox.matchr.android;

import de.kaleidox.matchr.android.event.ResultsChangeEvent;
import de.kaleidox.matchr.android.listener.GenericListener;
import de.kaleidox.matchr.android.listener.ListenerManager;

public interface FuzzyBinding<T> {
    ListenerManager<GenericListener<ResultsChangeEvent<T>>> addResultsChangeListener(
            GenericListener<ResultsChangeEvent<T>> listener);
}
