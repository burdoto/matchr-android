package de.kaleidox.matchr.android.functions;

import java.util.Collection;
import java.util.List;

import de.kaleidox.matchr.android.AndroidMatchr;

import me.xdrop.matchr.Matchr;
import me.xdrop.matchr.model.Result;

/**
 * An interface for using the {@link AndroidMatchr} functionality.
 * A variety of predefined ResultProviders can be found in {@link ResultProviders}.
 *
 * @param <T> Type of the returning results.
 *
 * @see ResultProviders
 */
public interface ResultProvider<T> {
    /**
     * Interface method for {@linkplain Matchr Matchr-Search} methods.
     * This method should call a method of any Matchr implementation.
     *
     * @param target  The target string.
     * @param options The possible options.
     *
     * @return A list of results.
     * @see Matchr
     */
    List<Result<T>> fetch(String target, Collection<T> options);
}
