import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Solver {

	private Board myStart;
	private HashSet<Board> seen;

	public static void main(String[] args) {
		try {
			Path init = Paths.get(args[0]);
			Path goal = Paths.get(args[1]);
			List<String> initBlocks;
			List<String> goalBlocks;
			initBlocks = Files.readAllLines(init);
			goalBlocks = Files.readAllLines(goal);
			Solver solve = new Solver(initBlocks, goalBlocks);
			solve.startSolving();
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
		}
	}

	public Solver(List<String> init, List<String> goal) throws Exception {
		HashSet<String> config = new HashSet<String>();
		HashSet<String> goalBlocks = new HashSet<String>();
		String[] split = init.get(0).split("\\s+");
		if (split.length != 2) {
			throw new Exception();
		}
		int[] intSplit = new int[2];
		for (int i = 0; i < 2; i++) {
			intSplit[i] = Integer.parseInt(split[i]);
			if (intSplit[i] < 1) {
				throw new Exception();
			}
		}
		int[][] checkBoard = new int[intSplit[0]][intSplit[1]];
		for (int i = 1; i < init.size(); i++) {
			String[] splitBlock = init.get(i).split("\\s+");
			int[] blockInt = new int[4];
			if (splitBlock.length != 4) {
				throw new Exception();
			}
			for (int j = 0; j < 4; j++) {
				blockInt[j] = Integer.parseInt(splitBlock[j]);
			}
			if (blockInt[2] < blockInt[0] || blockInt[3] < blockInt[1]) {
				throw new Exception();
			}
			for (int j = blockInt[0]; j <= blockInt[2]; j++) {
				for (int k = blockInt[1]; k <= blockInt[3]; k++) {
					checkBoard[j][k]++;
					if (checkBoard[j][k] > 1) {
						throw new Exception();
					}
				}
			}
			config.add(init.get(i));
		}
		checkBoard = new int[intSplit[0]][intSplit[1]];
		for (int i = 0; i < goal.size(); i++) {
			String[] splitBlock = goal.get(i).split("\\s+");
			int[] blockInt = new int[4];
			if (splitBlock.length != 4) {
				throw new Exception();
			}
			for (int j = 0; j < 4; j++) {
				blockInt[j] = Integer.parseInt(splitBlock[j]);
			}
			if (blockInt[2] < blockInt[0] || blockInt[3] < blockInt[1]) {
				throw new Exception();
			}
			for (int j = blockInt[0]; j <= blockInt[2]; j++) {
				for (int k = blockInt[1]; k <= blockInt[3]; k++) {
					checkBoard[j][k]++;
					if (checkBoard[j][k] > 1) {
						throw new Exception();
					}
				}
			}
			goalBlocks.add(goal.get(i));
		}
		Board board = new Board(intSplit[0], intSplit[1], null, config,
				goalBlocks, null);
		myStart = board;
		seen = new HashSet<Board>();
		seen.add(myStart);
	}

	public void startSolving() {
		if (!myStart.isGoal()) {
			Stack<Board> fringe = new Stack<Board>();
			fringe.push(myStart);
			while (!fringe.isEmpty()) {
				Board currentBoard = fringe.pop();
				if (currentBoard.isGoal()) {
					currentBoard.getSolveMoves();
					return;
				} else {
					seen.add(currentBoard);
					for (Board b : currentBoard.buildChildren()) {
						if (!seen.contains(b)) {
							fringe.push(b);
						}
					}
				}
			}
		}
	}

}
