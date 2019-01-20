package de.kaleidox.matchr.android;

import android.widget.TextView;
import java.util.Collection;

import de.kaleidox.matchr.android.bindings.TextViewFuzzyBinding;
import de.kaleidox.matchr.android.functions.ResultProvider;

public class FuzzyDroid {
    public static <T> FuzzyBinding<T> attachFuzzy(TextView view,
                                                  Collection<T> options,
                                                  ResultProvider<T> resultProvider) {
        return new TextViewFuzzyBinding<>(view, resultProvider, options);
    }
}
