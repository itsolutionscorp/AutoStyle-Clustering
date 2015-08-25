package us.bloch.solver;

import us.bloch.solver.puzzles.SlidingBlockPuzzle;

import java.io.IOException;
import java.util.Collections;

public class BoardDumper {
    public static void main(String[] args) throws IOException {
        SlidingBlockPuzzle puzzle = BlockLoader.readFiles(args[0], args[1]);
        System.out.println("Puzzle:\n" + puzzle);
        System.out.println("Goal:\n" + new SlidingBlockPuzzle(puzzle.getWidth(), puzzle.getHeight(), puzzle.getGoal(), new int[0]));
    }
}
