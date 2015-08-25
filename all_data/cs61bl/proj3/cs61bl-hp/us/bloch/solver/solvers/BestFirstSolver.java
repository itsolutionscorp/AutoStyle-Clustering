package us.bloch.solver.solvers;

import us.bloch.solver.NoSolutionException;
import us.bloch.solver.PuzzleState;
import us.bloch.solver.PuzzleSolver;

import java.util.*;

/**
 *
 * @param <T>
 */
public class BestFirstSolver<T extends PuzzleState<T>> implements PuzzleSolver<T> {
    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public long getCompletionTime() {
        return nanoseconds;
    }

    private int steps;
    private long nanoseconds;

    @Override
    public List<T> solve(T initialState) {
        if (!initialState.isWinnable())
            throw new NoSolutionException();

        // Create and set up data structures
        // Keeps track of states we've seen so we don't add them to the fringe more than once
        Set<T> observedStates = new HashSet<>();
        // Keeps track of what states to look at next, based on their score value.
        PriorityQueue<T> fringe = new PriorityQueue<>(Comparator.comparingInt(T::score));
        observedStates.add(initialState);
        fringe.add(initialState);

        // Search for the solution
        T current;
        steps = 0;
        long startTime = System.nanoTime();
//        long stillbornPerThousand = 0;
//        long newStatesPerThousand = 0;
//        long thousandStartTime = 0;

        // Keep picking the best-looking game state from the fringe until we find the solution or there's nothing left
        do {
//            if (steps % 1000 == 0) {
//                System.out.printf("steps: %d%n" +
//                        "time (ms): %f%n" +
//                        "new states: %d%n" +
//                        "old states: %d%n" +
//                        "fringe size: %d%n" +
//                        "observed size %d%n" +
//                        "--------------------%n",
//                        steps, (System.nanoTime() - thousandStartTime) / 1000000.0, newStatesPerThousand, stillbornPerThousand, fringe.size(), observedStates.size());
//                thousandStartTime = System.nanoTime();
//                stillbornPerThousand = 0;
//                newStatesPerThousand = 0;
//            }
            steps++;
            current = fringe.poll();
//            System.out.println(current);
            for (T successor : current.successors()) { // finding successors: should be fast too
                if (!observedStates.contains(successor)) {
//                    newStatesPerThousand++;
                    successor.initialize();
                    observedStates.add(successor); // hashCode: gotta go fast
                    fringe.add(successor); // calculating scores: gotta go fast
//                } else {
//                    stillbornPerThousand++;
                }
            }
        } while (!current.isWin() && !fringe.isEmpty());

        nanoseconds = System.nanoTime() - startTime;

        if (current.isWin()) { // TODO if this check is expensive, we can store a boolean variable instead
            // If a solution is found, trace steps back to the initial state
            LinkedList<T> winningMoves = new LinkedList<>();
            for (T step = current; step != null; step = step.predecessor())
                winningMoves.addFirst(step);
            return winningMoves;
        } else {
            // TODO This can be changed to return an empty list or something rather than throwing an exception
            throw new NoSolutionException();
        }
    }
}
