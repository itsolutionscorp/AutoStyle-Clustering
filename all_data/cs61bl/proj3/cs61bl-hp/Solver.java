import us.bloch.solver.BlockLoader;
import us.bloch.solver.NoSolutionException;
import us.bloch.solver.PuzzleSolver;
import us.bloch.solver.puzzles.SlidingBlockPuzzle;
import us.bloch.solver.solvers.BestFirstSolver;
import us.bloch.solver.solvers.BruteForceSolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Main class for project
 */
public class Solver {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }

        SlidingBlockPuzzle initialState;
        boolean writeResults;

        writeResults = Files.isDirectory(Paths.get("results"));

        try {
            initialState = BlockLoader.readFiles(args[0], args[1]);
        } catch (IOException e) {
            System.err.println("Couldn't load files: " + e);
            return;
        } catch (IllegalArgumentException e) {
            System.err.println("Bad input files: " + e);
            return;
        }
        if (args.length == 3 && args[2].equals("dump")) {
            System.out.println("Puzzle:\n" + initialState);
            System.out.println("Goal:\n" + new SlidingBlockPuzzle(initialState.getWidth(), initialState.getHeight(),
                    initialState.getGoal(), new int[0]));
            return;
        }

        PuzzleSolver<SlidingBlockPuzzle> solver = new BruteForceSolver<>(false);
        String[] initFilePath = args[0].split("/");
        String initFile = initFilePath[initFilePath.length - 1];

        try {
            List<SlidingBlockPuzzle> states = solver.solve(initialState);
            for (int i = 1; i < states.size(); i++) {
                SlidingBlockPuzzle state = states.get(i);
                int fromBlock = state.getFromBlock();
                int toBlock = state.getToBlock();
                System.out.printf("%d %d %d %d%n", SlidingBlockPuzzle.Block.getLeftX(fromBlock),
                                                   SlidingBlockPuzzle.Block.getTopY(fromBlock),
                                                   SlidingBlockPuzzle.Block.getLeftX(toBlock),
                                                   SlidingBlockPuzzle.Block.getTopY(toBlock));
            }

            if (writeResults) {
                PrintWriter resultWriter = new PrintWriter("results/" + initFile + "-result.txt");
                resultWriter.println("states explored: " + solver.getSteps());
                resultWriter.println("solution length: " + states.size());
                resultWriter.println("time: " + (solver.getCompletionTime() / 1000000.0) + " ms");
                states.forEach(resultWriter::println);
                resultWriter.close();
            }
        } catch (NoSolutionException e) {
            if (writeResults) {
                PrintWriter resultWriter = new PrintWriter("results/" + initFile + "-result.txt");
                resultWriter.println("steps: " + solver.getSteps());
                resultWriter.println("time: " + (solver.getCompletionTime() / 1000000.0) + " ms");
                resultWriter.println("no solution");
                resultWriter.close();
            }
        }
    }
}
