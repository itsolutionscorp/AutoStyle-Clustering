package us.bloch.solver.solvers;

import us.bloch.solver.NoSolutionException;
import us.bloch.solver.PuzzleSolver;
import us.bloch.solver.PuzzleState;

import java.util.*;

public class SimpleSolver<T extends PuzzleState<T>> implements PuzzleSolver<T> {
    private int steps;
    private long nanoseconds;

    @Override
    public List<T> solve(T initialState) throws NoSolutionException {
        Set<T> visitedStates = new HashSet<>();
        Deque<T> solutionSteps = new ArrayDeque<>();
        steps = 0;
        long startTime = System.nanoTime();
        boolean hasSolution = visit(initialState, visitedStates, solutionSteps);
        nanoseconds = System.nanoTime() - startTime;
        if (!hasSolution)
            throw new NoSolutionException();
        return new ArrayList<>(solutionSteps);
    }

    /**
     * Searches depth-first for a winning state.
     * If found, solutionSteps will be filled with the sequence of states from the winning state to the given state.
     *
     * @param state The state to start searching at
     * @param visitedStates the set of PuzzleStates that have been visited previously in this traversal.
     *                      Used to avoid backtracking and getting caught in loops.
     * @param solutionSteps A sequence of PuzzleStates, initially empty. this method will fill it in
     *                      with a sequence of states leading from the winning state to the initial state,
     *                      if such a sequence exists.
     * @return whether it is possible to win starting from the given state
     */
    private boolean visit(T state, Set<T> visitedStates, Deque<T> solutionSteps) {
        steps++;

        // Been to input state before, don't look there again
        if (visitedStates.contains(state))
            return false;
        visitedStates.add(state);

        // Found a win here at input state, start propagating back down
        if (state.isWin()) {
            solutionSteps.addFirst(state);
            return true;
        }

        // Check all possible successive states for wins
        for (T nextState : state.successors()) {
            if (visit(nextState, visitedStates, solutionSteps)) {
                // Found a win somewhere down the line, input state is part of it.
                solutionSteps.addFirst(state);
                return true;
            }
        }
        return false; // no solutions from input state
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public long getCompletionTime() {
        return nanoseconds;
    }
}
