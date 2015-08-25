package us.bloch.solver;

import us.bloch.solver.puzzles.SlidingBlockPuzzle;
import us.bloch.solver.puzzles.SlidingBlockPuzzle.Block;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BlockLoader {
    /**
     * Creates a new BlockLoader and reads the init and goal files line by line.
     *
     * @param init The initial file that will be used to create the starting SlidingBlockPuzzle.
     * @param goal The wining configuration of the SlidingBlockPuzzle.
     * @return A new SlidingBlockPuzzle to solve.
     */
    public static SlidingBlockPuzzle readFiles(String init, String goal) throws IOException {
        List<Integer> myBlocks = new ArrayList<>(); // pieces in the puzzle
        List<Integer> goalBlocks = new ArrayList<>(); // goal piece configurations

        Scanner s1 = new Scanner(new File(init));

        int width;
        int height;
        
        try {
            width = s1.nextInt(); // first number is the width of the board
            height = s1.nextInt(); // second number is the height of the board
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Invalid block format", e);
        }

        while (s1.hasNext()) {
            try {
                int leftX = s1.nextInt();
                int topY = s1.nextInt();
                int rightX = s1.nextInt();
                int bottomY = s1.nextInt();
                if (!validBlock(leftX, topY, rightX, bottomY))
                    throw new IllegalArgumentException("Invalid block in puzzle");
                myBlocks.add(Block.fromCoords(leftX, topY, rightX, bottomY));
            } catch (InputMismatchException e) {
                    throw new IllegalArgumentException("Invalid block format", e);
            }
        }

        //Read the goal file
        Scanner s2 = new Scanner(new File(goal));
        while (s2.hasNext()) {
            try {
                int leftX = s2.nextInt();
                int topY = s2.nextInt();
                int rightX = s2.nextInt();
                int bottomY = s2.nextInt();
                if (!validBlock(leftX, topY, rightX, bottomY))
                    throw new IllegalArgumentException("Invalid block in goal");
                goalBlocks.add(Block.fromCoords(leftX, topY, rightX, bottomY));
            } catch (InputMismatchException e) {
                throw new IllegalArgumentException("Invalid block format", e);
            }
        }
        int[] blocksArr = myBlocks.stream().mapToInt(Integer::intValue).toArray();
        int[] goalArr = goalBlocks.stream().mapToInt(Integer::intValue).toArray();
        return new SlidingBlockPuzzle(width, height, blocksArr, goalArr);
    }

    private static boolean validBlock(int leftX, int topY, int rightX, int bottomY) {
        return (rightX >= leftX && bottomY >= topY) &&
                (leftX >= 0 && leftX <= 255) &&
                (topY >= 0 && topY <= 255) &&
                (rightX >= 0 && rightX <= 255) &&
                (bottomY >= 0 && bottomY <= 255);
    }
}
