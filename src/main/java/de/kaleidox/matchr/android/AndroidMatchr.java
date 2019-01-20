package de.kaleidox.matchr.android;

import java.util.Collection;

import de.kaleidox.matchr.android.bindings.TextViewMatchrBinding;
import de.kaleidox.matchr.android.functions.ResultProvider;

import android.widget.TextView;

public final class AndroidMatchr {
    public static <T> MatchrBinding<T> attachFuzzy(TextView view,
                                                   Collection<T> options,
                                                   ResultProvider<T> resultProvider) {
        return new TextViewMatchrBinding<>(view, options, resultProvider);
    }
}
