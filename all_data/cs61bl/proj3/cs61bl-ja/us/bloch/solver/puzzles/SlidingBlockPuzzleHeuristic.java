package us.bloch.solver.puzzles;

import us.bloch.solver.puzzles.SlidingBlockPuzzle.Block;

import java.util.Arrays;
import java.util.List;

public class SlidingBlockPuzzleHeuristic {
    /**
     * Composite heuristic
     * @param puzzle the puzzle to check
     * @return the score
     */
    public static int evaluate(SlidingBlockPuzzle puzzle) {
        int result = 0;
        result += manhattanDistanceHeuristic(puzzle);// * 3;
        //result += goalOccupationHeuristic(puzzle);
        //result += interferingBlocksHeuristic(puzzle) * 2;
        // todo add more

        return result;
    }

    /**
     * Manhattan distance heuristic. Returns the distance between the goal and the closest block that fits it
     * @param puzzle the puzzle to check
     * @return the manhattan distance heuristic score for puzzle
     */
    static int manhattanDistanceHeuristic(SlidingBlockPuzzle puzzle) {
        return Arrays.stream(puzzle.getGoal()).map((goalBlock) -> blockGoalDistance(goalBlock, puzzle.getBlocks())).sum();
    }

    /**
     * Interfering block heuristic. Returns the number of occupied tiles
     * in the rectangle defined by the farthest corners of each goal block and the closest block that fits it
     * @param puzzle the puzzle to check
     * @return the interfering block heuristic score for puzzle
     */
    static int interferingBlocksHeuristic(SlidingBlockPuzzle puzzle) {
        int result = 0;
        for (int goalBlock : puzzle.getGoal()) {
            result += interferingBlocks(closestBlockToGoal(goalBlock, puzzle.getBlocks()), goalBlock, puzzle);
        }
        return result;
    }

    /**
     * Goal occupation heuristic. Returns the number of blocks inside the goal area that do not fit the goal.
     * @param puzzle the puzzle to check
     * @return the goal occupation heuristic score for puzzle
     */
    static int goalOccupationHeuristic(SlidingBlockPuzzle puzzle) {
        return Arrays.stream(puzzle.getGoal()).map((goalBlock) -> goalOccupied(goalBlock, puzzle)).sum();
    }

    private static int closestBlockToGoal(int goal, int[] blocks) {
        int[] compatibleBlocks = Arrays.stream(blocks)
                .filter((block) -> Block.getHeight(goal) == Block.getHeight(block) && Block.getWidth(goal) == Block.getWidth(block))
                .toArray();
        int closest = compatibleBlocks[0];
        int closestDist = manhattanDistance(closest, goal);
        for (int i = 1; i < compatibleBlocks.length; i++) {
            int distance = manhattanDistance(compatibleBlocks[i], goal);
            if (distance < closestDist) {
                closestDist = distance;
                closest = compatibleBlocks[i];
            }
        }
        return closest;
    }
    
    private static int interferingBlocks(int target, int goal, SlidingBlockPuzzle puzzle) {
        int result = 0;
        int topY = Math.min(Block.getTopY(target), Block.getTopY(goal));
        int bottomY = Math.max(Block.getBottomY(target), Block.getBottomY(goal));
        int leftX = Math.min(Block.getLeftX(target), Block.getLeftX(goal));
        int rightX = Math.max(Block.getRightX(target), Block.getRightX(goal));
        for (int y = topY; y <= bottomY; y++) {
            for (int x = leftX; x <= rightX; x++) {
                int blockAt = puzzle.getBlockAt(x, y);
                if (blockAt != SlidingBlockPuzzle.ABSENT && blockAt != target)
                    result++;
            }
        }
        return result;
    }
    
    /**
     * Finds the distance from a goal position to the closest matching block
     * @return the distance from goal to the closest matching block in blocks
     */
    private static int blockGoalDistance(int goal, int[] blocks) {
        return Arrays.stream(blocks)
                .filter((block) -> Block.getHeight(goal) == Block.getHeight(block) && Block.getWidth(goal) == Block.getWidth(block)) // filter for blocks of same size
                .map((block) -> manhattanDistance(block, goal)).min().getAsInt(); //
    }

    private static int manhattanDistance(int block1, int block2) {
        return Math.abs(Block.getLeftX(block1) - Block.getLeftX(block2)) + Math.abs(Block.getTopY(block1) - Block.getTopY(block2));
    }

    private static int goalOccupied(int goal, SlidingBlockPuzzle board) {
        int result = 0;
        for (int y = Block.getTopY(goal); y <= Block.getBottomY(goal); y++) {
            for (int x = Block.getLeftX(goal); x <= Block.getRightX(goal); x++) {
                int atCurrent = board.getBlockAt(x, y);
                if (atCurrent != SlidingBlockPuzzle.ABSENT && (Block.getWidth(atCurrent) != Block.getWidth(goal) && Block.getHeight(atCurrent) != Block.getHeight(goal)))
                    result++;
            }
        }
        return result;
    }
}
