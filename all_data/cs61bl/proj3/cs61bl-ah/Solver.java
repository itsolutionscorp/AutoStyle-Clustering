import java.util.*;
import java.io.*;

public class Solver {
	int rows;
	int columns;
	ArrayList<Board.Block> goal;
	Board currentBoard;
	HashSet<Board> visited;
	boolean solved;
	Stack<Board> toSolveStack;
	Queue<Board> toSolveQueue;
	boolean[][] spaces;
	boolean moveUp;
	boolean moveDown;
	boolean moveRight;
	boolean moveLeft;

	public Solver(String initName, String goalName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(initName));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rows = Integer.parseInt(st.nextToken());
		columns = Integer.parseInt(st.nextToken());
		currentBoard = new Board();
		visited = new HashSet<>();
		spaces = new boolean[rows][columns];
		currentBoard.blocks = readBlocks(initName, false);
		goal = readBlocks(goalName, true);
		solved = false;
		toSolveStack = new Stack<>();
		toSolveQueue = new LinkedList<>();
		moveUp = true;
		moveDown = true;
		moveRight = true;
		moveLeft = true;
		br.close();
	}

	public void solve() {
		if (goal.size() > currentBoard.blocks.size() / 3) {
			solveQueue();
		} else {
			solveStack();
		}
	}

	public void solveStack() {
		toSolveStack.push(currentBoard);
		visited.add(currentBoard);
		while (!solved && !toSolveStack.isEmpty()) {
			currentBoard = toSolveStack.pop();
			if (currentBoard.isWinningState()) {
				solved = true;
				break;
			}
			for (Board.Block b : currentBoard.blocks) {
				tryMovesStack(b);
			}
		}
		if (solved) {
			Stack<Board> path = new Stack<Board>();
			for (Board b = currentBoard; b.predecessor != null; b = b.predecessor) {
				path.push(b);
			}
			while (!path.isEmpty()) {
				Board temp = path.pop();
				for (int q = 0; q < temp.blocks.size(); q++) {
					if (temp.predecessor != null && !temp.blocks.get(q).equals(temp.predecessor.blocks.get(q))) {
						System.out.println(temp.predecessor.blocks.get(q).getUpperLeft() + temp.blocks.get(q).getUpperLeft());
						break;
					}
				}
			}
		}
	}

	public void solveQueue() {
		toSolveQueue.offer(currentBoard);
		visited.add(currentBoard);
		while (!solved && !toSolveQueue.isEmpty()) {
			currentBoard = toSolveQueue.poll();
			if (currentBoard.isWinningState()) {
				solved = true;
				break;
			}
			for (Board.Block b : currentBoard.blocks) {
				tryMovesQueue(b);
			}
		}
		if (solved) {
			Stack<Board> path = new Stack<Board>();
			for (Board b = currentBoard; b.predecessor != null; b = b.predecessor) {
				path.push(b);
			}
			while (!path.isEmpty()) {
				Board temp = path.pop();
				for (int q = 0; q < temp.blocks.size(); q++) {
					if (temp.predecessor != null && !temp.blocks.get(q).equals(temp.predecessor.blocks.get(q))) {
						System.out.println(temp.predecessor.blocks.get(q).getUpperLeft() + temp.blocks.get(q).getUpperLeft());
						break;
					}
				}
			}
		}
	}

	public ArrayList<Board.Block> readBlocks(String fileName, boolean isGoal) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<Board.Block> result = new ArrayList<Board.Block>();
		if (!isGoal)
			br.readLine();
		String line = br.readLine();
		Integer ID = 0;
		Board board = new Board();
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line);
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			Board.Block b = board.new Block(ID, x1, y1, x2, y2);
			result.add(b);
			for (int i = x1; i <= x2; i++) {
				for (int j = y1; j <= y2; j++) {
					if (!isGoal) {
						if(spaces[i][j] == true) {
							throw new Exception();
						}
					}
					spaces[i][j] = true;
				}
			}
			ID++;
			line = br.readLine();
		}
		br.close();
		if(result.isEmpty()) {
			throw new Exception();
		}
		return result;
	}

	public void tryMovesStack(Board.Block block) {
		currentBoard.canMove(block.upperBound, block.leftBound, block.lowerBound, block.rightBound);
		if(moveUp) {
			Board next = move(block, 0, -1);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveStack.push(next);
			}
		}
		if(moveDown) {
			Board next = move(block, 0, 1);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveStack.push(next);
			}
		}
		if(moveRight) {
			Board next = move(block, 1, 0);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveStack.push(next);
			}
		}
		if(moveLeft) {
			Board next = move(block, -1, 0);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveStack.push(next);
			}
		}
		moveUp = true;
		moveDown = true;
		moveLeft = true; 
		moveRight = true;
	}

	public void tryMovesQueue(Board.Block block) {
		currentBoard.canMove(block.upperBound, block.leftBound, block.lowerBound, block.rightBound);
		if(moveUp) {
			Board next = move(block, 0, -1);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveQueue.offer(next);
			}
		}
		if(moveDown) {
			Board next = move(block, 0, 1);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveQueue.offer(next);
			}
		}
		if(moveRight) {
			Board next = move(block, 1, 0);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveQueue.offer(next);
			}
		}
		if(moveLeft) {
			Board next = move(block, -1, 0);
			if (!visited.contains(next)) {
				visited.add(next);
				toSolveQueue.offer(next);
			}
		}
		moveUp = true;
		moveDown = true;
		moveLeft = true; 
		moveRight = true;
	}

	public Board move(Board.Block target, int dx, int dy) {
		Board newBoard = new Board(currentBoard);
		Board.Block b = newBoard.blocks.get(target.id);
		b.rightBound += dx;
		b.leftBound += dx;
		b.upperBound += dy;
		b.lowerBound += dy;
		return newBoard;
	}

	public static void main(String[] args) throws IOException {
		try {
			//long time = System.currentTimeMillis();
			Solver s = new Solver(args[0], args[1]);
			//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("puzzle.info", true)));
			//out.println("--------\nPuzzle: " + args[0]);
			s.solve();
			//time = System.currentTimeMillis() - time;
			//out.println("Stack or Queue: " + s.stackOrQueue);
			//out.println("Time (in seconds): " + time / 1000.0 + "\n");
			//out.close();
		} 
		catch (Exception ex) {
			System.out.println("Invalid init and/or goal file.");
		} 
	}

	private class Board {
		private ArrayList<Block> blocks;
		private Board predecessor;

		public Board() {
			predecessor = null;
		}

		private Board(Board oldBoard) {
			blocks = new ArrayList<Block>();
			for (Block b : oldBoard.blocks) {
				blocks.add(new Block(b));
			}
			predecessor = oldBoard;
		}

		private boolean isWinningState() {
			for (Block b : goal) {
				if (!blocks.contains(b)) {
					return false;
				}
			}
			return true;
		}
		
		private void canMove(int upper, int left, int lower, int right) {
			if (upper <= 0) {
				moveUp = false;
			}
			if (lower >= rows - 1){
				moveDown = false;
			}
			if (right >= columns - 1) {
				moveRight = false;
			}
			if (left <= 0) {
				moveLeft = false;
			}
			for (Block block : blocks) {
				if (block.lowerBound == lower && block.upperBound == upper && block.rightBound == right && block.leftBound == left) {
					continue;
				}
				if (block.lowerBound == upper - 1) {
					if ((block.leftBound <= right && block.leftBound >= left) || (block.rightBound <= right && block.rightBound >= left) || (block.leftBound <= left && block.rightBound >= right)) {
						moveUp = false;
					}
				}
				if (block.upperBound == lower + 1) {
					if ((block.leftBound <= right && block.leftBound >= left) || (block.rightBound <= right && block.rightBound >= left) || (block.leftBound <= left && block.rightBound >= right)) {
						moveDown = false;
					}
				}
				if (block.leftBound == right + 1) {
					if ((block.lowerBound >= upper && block.lowerBound <= lower) || (block.upperBound >= upper && block.upperBound <= lower) || (block.upperBound <= upper && block.lowerBound >= lower)) {
						moveRight = false;
					}
				}
				if (block.rightBound == left - 1) {
					if ((block.lowerBound >= upper && block.lowerBound <= lower) || (block.upperBound >= upper && block.upperBound <= lower) || (block.upperBound <= upper && block.lowerBound >= lower)) {
						moveLeft = false;
					}
				}
			}
		}
		
		@Override
		public boolean equals(Object o) {
			Board b = (Board) o;
			for (Block q : blocks) {
				if (!b.blocks.contains(q)) {
					return false;
				}
			}
			return true;
		}

		@Override
		public String toString() {
			ArrayList<String> toReturn = new ArrayList<>();
			for (Block b : blocks) {
				toReturn.add(b.toString());
			}
			return toReturn.toString();
		}
		
		@Override
		public int hashCode() {
			int hash = 0;
			for (int i = 0; i < blocks.size(); i++) {
				Block b = blocks.get(i);
				hash += b.upperBound + Integer.rotateLeft(b.leftBound, 8) + Integer.rotateLeft(b.lowerBound, 16) + Integer.rotateLeft(b.rightBound, 24);
			}
			return hash;
		}

		private class Block {

			private int id;
			private int upperBound;
			private int leftBound;
			private int lowerBound;
			private int rightBound;

			private Block(int iden, int tLX, int tLY, int bRX, int bRY) {
				id = iden;
				upperBound = tLX;
				leftBound = tLY;
				lowerBound = bRX;
				rightBound = bRY;
			}

			private Block(Block b) {
				id = b.id;
				upperBound = b.upperBound;
				leftBound = b.leftBound;
				lowerBound = b.lowerBound;
				rightBound = b.rightBound;
			}

			@Override
			public boolean equals(Object o) {
				Block b = (Block) o;
				return upperBound == b.upperBound && leftBound == b.leftBound
						&& lowerBound == b.lowerBound
						&& rightBound == b.rightBound;
			}

			public String getUpperLeft() {
				return "" + upperBound + " " + leftBound + " ";
			}

			@Override
			public String toString() {
				return "" + upperBound + " " + leftBound + " " + lowerBound
						+ " " + rightBound;
			}
		}

	}

}