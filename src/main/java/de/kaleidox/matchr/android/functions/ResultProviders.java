package de.kaleidox.matchr.android.functions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import me.xdrop.matchr.Matchr;
import me.xdrop.matchr.algorithms.Algorithm;
import me.xdrop.matchr.algorithms.AlgorithmFactory;
import me.xdrop.matchr.functions.StringMapper;
import me.xdrop.matchr.model.Result;
import me.xdrop.matchr.model.ScoringMethod;

/**
 * A factory for simple, predefined {@linkplain ResultProvider ResultProviders}.
 */
public class ResultProviders {
    /**
     * Creates a new ResultProvider that looks for only the best result.
     *
     * @param withAlgorithmFactory The algorithm to be used.
     * @param withStringMapper     The stringMapper to be used.
     * @param withMethod           The scoringMethod to be used.
     * @param <T>                  Type-variable for the element type.
     * @param <A>                  Type-variable for the algorithm.
     *
     * @return A new ResultProvider that only returns the best result.
     * @see Matchr#extractBest(String, Collection, StringMapper, ScoringMethod)
     */
    public static <T, A extends Algorithm> ResultProvider<T> best(
            final AlgorithmFactory<A> withAlgorithmFactory,
            final StringMapper<T> withStringMapper,
            final ScoringMethod withMethod) {
        return new ResultProvider<T>() {
            final Matchr<A> fuzzy = Matchr.algorithm(withAlgorithmFactory);
            final StringMapper<T> stringMapper = Objects.requireNonNull(withStringMapper);
            final ScoringMethod method = Objects.requireNonNull(withMethod);

            @Override
            public List<Result<T>> fetch(String target, Collection<T> options) {
                return Collections.singletonList(fuzzy.extractBest(target, options, stringMapper, method));
            }
        };
    }

    /**
     * Creates a new ResultProvider that limits the amount of results.
     *
     * @param withAlgorithmFactory The algorithm to be used.
     * @param withStringMapper     The stringMapper to be used.
     * @param withMethod           The scoringMethod to be used.
     * @param withLimit            The maximum amount of results to return.
     * @param <T>                  Type-variable for the element type.
     * @param <A>                  Type-variable for the algorithm.
     *
     * @return A new ResultProvider.
     * @see Matchr#extractLimited(String, Collection, StringMapper, ScoringMethod, int)
     */
    public static <T, A extends Algorithm> ResultProvider<T> limited(
            final AlgorithmFactory<A> withAlgorithmFactory,
            final StringMapper<T> withStringMapper,
            final ScoringMethod withMethod,
            final int withLimit) {
        return new ResultProvider<T>() {
            final Matchr<A> fuzzy = Matchr.algorithm(withAlgorithmFactory);
            final StringMapper<T> stringMapper = Objects.requireNonNull(withStringMapper);
            final ScoringMethod method = Objects.requireNonNull(withMethod);
            final int limit = withLimit;

            @Override
            public List<Result<T>> fetch(String target, Collection<T> options) {
                return fuzzy.extractLimited(target, options, stringMapper, method, limit);
            }
        };
    }

    /**
     * Creates a new ResultProvider that sorts the output.
     *
     * @param withAlgorithmFactory The algorithm to be used.
     * @param withStringMapper     The stringMapper to be used.
     * @param withMethod           The scoringMethod to be used.
     * @param <T>                  Type-variable for the element type.
     * @param <A>                  Type-variable for the algorithm.
     *
     * @return A new ResultProvider.
     * @see Matchr#extractAllSorted(String, Collection, StringMapper, ScoringMethod)
     */
    public static <T, A extends Algorithm> ResultProvider<T> sorted(
            final AlgorithmFactory<A> withAlgorithmFactory,
            final StringMapper<T> withStringMapper,
            final ScoringMethod withMethod) {
        return new ResultProvider<T>() {
            final Matchr<A> fuzzy = Matchr.algorithm(withAlgorithmFactory);
            final StringMapper<T> stringMapper = Objects.requireNonNull(withStringMapper);
            final ScoringMethod method = Objects.requireNonNull(withMethod);

            @Override
            public List<Result<T>> fetch(String target, Collection<T> options) {
                return fuzzy.extractAllSorted(target, options, stringMapper, method);
            }
        };
    }

    /**
     * Creates a new ResultProvider that returns an untreated list of results.
     *
     * @param withAlgorithmFactory The algorithm to be used.
     * @param withStringMapper     The stringMapper to be used.
     * @param withMethod           The scoringMethod to be used.
     * @param <T>                  Type-variable for the element type.
     * @param <A>                  Type-variable for the algorithm.
     *
     * @return A new ResultProvider.
     * @see Matchr#extractAll(String, Collection, StringMapper, ScoringMethod)
     */
    public static <T, A extends Algorithm> ResultProvider<T> all(
            final AlgorithmFactory<A> withAlgorithmFactory,
            final StringMapper<T> withStringMapper,
            final ScoringMethod withMethod) {
        return new ResultProvider<T>() {
            final Matchr<A> fuzzy = Matchr.algorithm(withAlgorithmFactory);
            final StringMapper<T> stringMapper = Objects.requireNonNull(withStringMapper);
            final ScoringMethod method = Objects.requireNonNull(withMethod);

            @Override
            public List<Result<T>> fetch(String target, Collection<T> options) {
                return fuzzy.extractAll(target, options, stringMapper, method);
            }
        };
    }
}
