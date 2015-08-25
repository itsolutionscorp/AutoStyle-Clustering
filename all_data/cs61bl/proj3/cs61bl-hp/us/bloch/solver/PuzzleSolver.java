package us.bloch.solver;

import java.util.List;

/**
 * A solver for a puzzle
 * @param <T> the type of puzzle to solve
 */
public interface PuzzleSolver<T extends PuzzleState<T>> {
    /**
     * Finds a solution to the puzzle defined by the given initial state.
     * @param initialState the initial state of the game to start searching from
     * @return the solution to the puzzle as a list of steps from the initial state to the win state
     * @throws NoSolutionException if a win state is unreachable from the initial state
     */
    List<T> solve(T initialState);

    /**
     * After the solver has been run on a puzzle, this method returns the number of states the solver had to check
     * before finding its solution.
     * @return the number of steps the solver took to find a solution to its last puzzle.
     */
    int getSteps();

    /**
     * After the solver has been run on a puzzle, this method returns the time in nanoseconds it took to
     * find the solution.
     * @return completion time in nanoseconds
     */
    long getCompletionTime();
}
