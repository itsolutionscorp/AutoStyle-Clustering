package us.bloch.solver.solvers;

import org.junit.Test;
import us.bloch.solver.puzzles.SquarePuzzle;

import java.util.List;

public class SimpleSolverTest {

    @Test
    public void testSolve() throws Exception {
        SquarePuzzle p = new SquarePuzzle(2);
        System.out.println(p);
        SimpleSolver<SquarePuzzle> simpleSolver = new SimpleSolver<>();
        List<SquarePuzzle> solution = simpleSolver.solve(p);
        solution.forEach(System.out::println);
    }
}