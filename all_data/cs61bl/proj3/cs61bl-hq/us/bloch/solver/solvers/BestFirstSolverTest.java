package us.bloch.solver.solvers;

import org.junit.Test;
import us.bloch.solver.NoSolutionException;
import us.bloch.solver.puzzles.SquarePuzzle;

import java.util.List;


public class BestFirstSolverTest {

    @Test
    public void testSolve() throws Exception {
        BestFirstSolver<SquarePuzzle> solver = new BestFirstSolver<SquarePuzzle>();

        SquarePuzzle p = new SquarePuzzle(3);
        SquarePuzzle p2 = new SquarePuzzle(p);
        SquarePuzzle p3 = new SquarePuzzle(p);
        p.setScoreWeights(1, 0);
        p2.setScoreWeights(0, 1);
        p3.setScoreWeights(1, 1);

        System.out.println(p);



        List<SquarePuzzle> solution = solver.solve(p);
        System.out.println("Best-first solver Steps taken: " + solver.getSteps());
        solver.solve(p2);
        System.out.println("Best-first solver Steps taken: " + solver.getSteps());
        solver.solve(p3);
        System.out.println("Best-first solver Steps taken: " + solver.getSteps());

        SimpleSolver<SquarePuzzle> simpleSolver = new SimpleSolver<>();
        simpleSolver.solve(p);
        System.out.println("Simple DFS solver steps taken: " + simpleSolver.getSteps());
    }

    @Test
    public void compareAvgSteps() {
        BestFirstSolver<SquarePuzzle> bestFirstSolver = new BestFirstSolver<>();
        SimpleSolver<SquarePuzzle> simpleSolver = new SimpleSolver<>();

        long totalBestFirstSteps = 0;
        long totalSimpleSteps = 0;
        int trials = 1000;

        int i = 0;
        while (i < trials) {
            SquarePuzzle p = new SquarePuzzle(3);
            try {
                bestFirstSolver.solve(p);
                simpleSolver.solve(p);
                totalSimpleSteps += simpleSolver.getSteps();
                totalBestFirstSteps += bestFirstSolver.getSteps();
                i++;
                System.out.println(i);
            } catch (NoSolutionException ignored) {
                // Only count valid puzzles
            }
        }

        System.out.println("Average best-first solver Steps taken: " + ((double) totalBestFirstSteps) / trials);
        System.out.println("Average simple DFS solver steps taken: " + ((double) totalSimpleSteps) / trials);
    }
}