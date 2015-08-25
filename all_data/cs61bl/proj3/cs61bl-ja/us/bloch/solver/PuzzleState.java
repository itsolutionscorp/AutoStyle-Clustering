package us.bloch.solver;

import java.util.List;

public interface PuzzleState<T extends PuzzleState<T>> {
    /**
     * Find possible puzzle states that can be arrived at by making a single move from this state
     * @return a list of "next" states
     */
    List<T> successors(); // TODO this doesn't have to be a list; we could use a set if that works better

    /**
     * Gets the state that that immediately preceded this state;
     * a state that can lead to this state in one move.
     * @return the predecessor of this state
     */
    T predecessor();

    /**
     * Checks whether this state is a winning state
     * @return whether this is a winning state
     */
    boolean isWin();

    /**
     * Checks whether the puzzle can still be won from this state.
     * @return whether the puzzle is winnable from this state
     */
    boolean isWinnable(); // this is not so important for now; you can ignore it or return true

    /**
     * Initializes redundant, expensive-to-calculate parts of this puzzle state.
     * Called by a puzzle solver after it has determined that this state is "worth keeping"
     * (for example, it has not been seen or visited before).
     */
    void initialize();

    /**
     * A "golf-like" (lower is better) rating of how good this state is: How close to a solution it appears to be
     * @return a rating of this puzzle state
     */
    int score();
}
