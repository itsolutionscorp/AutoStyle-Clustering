import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solver {

	Board currentBoard;
	HashMap<Point, Block> goalBoard;
	int xDimension;
	int yDimension;

	public Solver(File init, File goal) throws FileNotFoundException,
			IOException, IllegalArgumentException {
		setupCurrentBoard(init);
		setupGoal(goal);
	}

	// reading file code adapted from
	// http://www.java-tips.org/java-se-tips-100019/18-java-io/2028-how-to-read-file-in-java.html
	// and string tokenizer based off of
	// http://stackoverflow.com/questions/5864159/count-words-in-a-string-method
	public void setupCurrentBoard(File init) throws IOException {
		FileInputStream fisB = new FileInputStream(init);
		InputStreamReader isrB = new InputStreamReader(fisB);
		BufferedReader brB = new BufferedReader(isrB);
		boolean first = true;
		while (brB.ready()) {
			if (first) {
				String dimension = brB.readLine();
				StringTokenizer st = new StringTokenizer(dimension);
				if (st.countTokens() != 2) {
					fisB.close();
					isrB.close();
					brB.close();
					throw new IllegalArgumentException("wrong dimensions");
				}
				yDimension = Integer.parseInt(st.nextToken());
				xDimension = Integer.parseInt(st.nextToken());
				if (xDimension < 0 || yDimension < 0) {
					fisB.close();
					isrB.close();
					brB.close();
					throw new IllegalArgumentException("negative dimensions");
				}
				currentBoard = new Board(xDimension, yDimension);
				first = false;
			} else {
				String currentPiece = brB.readLine();
				StringTokenizer st = new StringTokenizer(currentPiece);
				if (st.countTokens() != 4) {
					fisB.close();
					isrB.close();
					brB.close();
					throw new IllegalArgumentException(
							"cant read piece locations");
				}

				int topLeftY = Integer.parseInt(st.nextToken());
				int topLeftX = Integer.parseInt(st.nextToken());
				int bottomRightY = Integer.parseInt(st.nextToken());
				int bottomRightX = Integer.parseInt(st.nextToken());
				if (topLeftY < 0 || topLeftX < 0 || bottomRightY < 0 || bottomRightX < 0) {
					fisB.close();
					isrB.close();
					brB.close();
					throw new IllegalArgumentException(
							"negative piece locations");
				}
				if (topLeftX > bottomRightX || topLeftY > bottomRightY || bottomRightX >= xDimension || bottomRightY >= yDimension) {
					fisB.close();
					isrB.close();
					brB.close();
					throw new IllegalArgumentException(
							"bad piece locations");
				}
				Block thisBlock = new Block(topLeftX, topLeftY, bottomRightX,
						bottomRightY);
				currentBoard.addBlock(thisBlock);
			}
			currentBoard.setHashCode();
		}
		fisB.close();
		isrB.close();
		brB.close();
	}

	// reading file code adapted from
	// http://www.java-tips.org/java-se-tips-100019/18-java-io/2028-how-to-read-file-in-java.html
	// and string tokenizer based off of
	// http://stackoverflow.com/questions/5864159/count-words-in-a-string-method
	public void setupGoal(File goal) throws IOException {
		FileInputStream fisB = new FileInputStream(goal);
		InputStreamReader isrB = new InputStreamReader(fisB);
		BufferedReader brB = new BufferedReader(isrB);
		goalBoard = new HashMap<Point, Block>();
		while (brB.ready()) {
			String currentPiece = brB.readLine();
			StringTokenizer st = new StringTokenizer(currentPiece);
			if (st.countTokens() != 4) {
				fisB.close();
				isrB.close();
				brB.close();
				throw new IllegalArgumentException("cant read in goal");
			}
			int topLeftY = Integer.parseInt(st.nextToken());
			int topLeftX = Integer.parseInt(st.nextToken());
			int bottomRightY = Integer.parseInt(st.nextToken());
			int bottomRightX = Integer.parseInt(st.nextToken());
			if (topLeftY < 0 || topLeftX < 0 || bottomRightY < 0 || bottomRightX < 0) {
				fisB.close();
				isrB.close();
				brB.close();
				throw new IllegalArgumentException(
						"negative piece locations");
			}
			Block thisBlock = new Block(topLeftX, topLeftY, bottomRightX,
					bottomRightY);
			goalBoard.put(thisBlock.topLeft(), thisBlock);
		}
		fisB.close();
		isrB.close();
		brB.close();
	}

	public void solve() {
		SolveIterator iter = new SolveIterator(currentBoard, goalBoard);
		Board last = new Board(0, 0);
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				last = iter.next();
			}
		} else {
			if (currentBoard.equalsGoal(goalBoard)) {
				return;
			}
		}
		if (!iter.solved) {
			return;
		}
		last.printMoves();
	}

	private class SolveIterator implements Iterator<Board> {
		private LinkedList<Board> fringe;
		private TreeSet<Integer> seen;
		private HashMap<Point, Block> goal;
		public boolean solved = false;
		public double g;

		public SolveIterator(Board beginning, HashMap<Point, Block> end) {
			goal = end;
			g = 0.0;
			fringe = new LinkedList<Board>();
			fringe.add(beginning);
			seen = new TreeSet<Integer>();
			seen.add(beginning.hashCode());
		}

		public boolean hasNext() {
			return fringe.peek() != null;
		}

		public Board next() {
			Board b = fringe.remove();
			g++;
			if (b.equalsGoal(goal)) {
				fringe = new LinkedList<Board>();
				solved = true;
				return b;

			}
			for (Board board : b.possibleMoves(seen)) {
				fringe.add(board);
				seen.add(board.hashCode());
			}
			return b;
		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			IllegalArgumentException, IOException {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
		} else {
			File init = new File(args[0]);
			File goal = new File(args[1]);
			try {
				Solver mySolver = new Solver(init, goal);
				mySolver.solve();
			} catch (Exception e) {
				System.out.println("Invalid init and/or goal file.");
				return;
			}
		}
	}

}
