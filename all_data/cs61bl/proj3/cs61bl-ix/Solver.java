/*
 * Solver.java
 * 
 * CS61BL @ Berkeley
 */
import java.util.*;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.*;

public class Solver {
	//iterate through whole collection instead finding specific index.Constant time.
	private HashSet<Board> visitedBoards;
	private Board gameToSolve;
	private List<int[]> goal;

	public List<int[]> solve(Board currentBoard) {
		List<Board> queue = new LinkedList<Board>();
		queue.add(gameToSolve);
		visitedBoards.add(gameToSolve);
		List<int[]> toReturn = new ArrayList<int[]>();
		
		if (gameToSolve.containsPiece(goal)) { //already the goal
			return toReturn;
		}
		
		while(!queue.isEmpty()) {
			Board current = queue.remove(0);
			
			//System.out.println(queue.size());
			//System.out.println(current.moves.size());
			for (int[] eachMove : current.getAllMoves()) { //iterate thorough all possible moves
				Board movedBoard = current.move(eachMove); //make a move
				
				// is a goal
				if (movedBoard.containsPiece(goal)) {
					return movedBoard.moves;
				}
				
				// not a goal
				if (!visitedBoards.contains(movedBoard)) { //not already encountered
					queue.add(movedBoard); //add a possible move
					visitedBoards.add(movedBoard);
				}
				//System.out.println(movedBoard.moves.size());
				
				//else, skip
			}
		}
		// no possible
		return new ArrayList<int[]>();
		

	}

	public Solver(Board toSolve, List<int[]> translatedGoal) {
		gameToSolve = toSolve;
		visitedBoards = new HashSet<Board>();
		goal = translatedGoal;
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 2) { // first check whether the argument length is 2
			System.out.println("Invalid argument number: expected 2");
			return; // terminate
		}

		// First, work on the tray configuration
		Path tray = FileSystems.getDefault().getPath(args[0]);
		if (!Files.exists(tray)) { // check if the file does not exist
			System.out.println("Tray file does not exist");
			return;
		}

		List<String> lines = Files.readAllLines(tray);

		String[] size = lines.get(0).split(" ");
		if (size.length != 2) {
			System.out.println("Size configuration argument number not correct");
			return;
		}

		int width;
		int height;
		try {
			width = Integer.parseInt(size[1]);
			height = Integer.parseInt(size[0]);
		} catch (NumberFormatException e) {
			System.out.println("Size configuration number format incorrect");
			return;
		}

		if (width <= 0 || height <= 0) {
			System.out.println("Size should not be negative");
			return;
		}

		ArrayList<int[]> pieces = new ArrayList<int[]>();

		for (int i = 1; i < lines.size(); i++) {
			String[] block = lines.get(i).split(" ");
			if (block.length != 4) {
				System.out.println("Block configuration argument number incorrect");
				return;
			}

			int[] blockInInt = new int[4];
			for (int j = 0; j < 4; j++) {
				try {
					blockInInt[j] = Integer.parseInt(block[j]);
				} catch (NumberFormatException e) {
					System.out.println("Block configuration number format incorrect");
					return;
				}

				if (blockInInt[j] < 0) {
					System.out.println("Block configuration should not be negative.");
					return;
				}
			}

			pieces.add(blockInInt);

		}

		// Next, we need to read the goal file
		Path goal = FileSystems.getDefault().getPath(args[1]);
		if (!Files.exists(goal)) { // check if the file does not exist
			System.out.println("Goal file does not exist");
			return;
		}

		List<String> goalLines = Files.readAllLines(goal);
		List<int[]> goals = new ArrayList<int[]>();
		for (String l : goalLines) {
			String[] blocks = l.split(" ");
			if (blocks.length != 4) {
				System.out.println("Goal configuration error");
				return;
			}
			try {
				int[] onePiece = new int[4];
				for (int i = 0; i < 4; i++) {
					onePiece[i] = Integer.parseInt(blocks[i]);
				}
				goals.add(onePiece);
			} catch (NumberFormatException e) {
				System.out.println("Goal configuration format error");
				return;
			}
		}
		
		Solver s = new Solver(new Board(width, height, pieces), goals);
		List<int[]> result = s.solve(s.gameToSolve);
		if (result != null) {
			for (int[] move : result) {
				for (int num : move) {
					System.out.print(num + " ");
				}
				System.out.println();
			}
		}

	}

}
