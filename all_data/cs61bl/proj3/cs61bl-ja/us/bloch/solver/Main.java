package us.bloch.solver;

import us.bloch.solver.puzzles.SlidingBlockPuzzle;
import us.bloch.solver.puzzles.SlidingBlockPuzzle.Block;
import us.bloch.solver.solvers.SimpleSolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SlidingBlockPuzzle p = new SlidingBlockPuzzle(3, 3,
                new HashSet<>(Arrays.asList(new Block(0, 0, 2, 2), new Block(1, 2, 1, 1))),
                Arrays.asList(new Block(1, 1, 2, 2)));
        SimpleSolver solver = new SimpleSolver();
        List<PuzzleState> solution = solver.solve(p);
        solution.forEach(System.out::println);
        System.out.println();
    }
}
