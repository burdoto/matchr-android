package de.kaleidox.matchr.android.event;

import java.util.List;

import me.xdrop.matchr.model.Result;

public class ResultsChangeEvent<T> extends Event {
    private final List<Result<T>> results;

    public ResultsChangeEvent(List<Result<T>> results) {
        super("Results Changed");
        this.results = results;
    }

    public List<Result<T>> getResults() {
        return results;
    }
}
