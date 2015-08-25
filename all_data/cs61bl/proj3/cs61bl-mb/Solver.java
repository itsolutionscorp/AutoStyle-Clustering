
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solver {
	
	BoardState initBoardState;
	BoardState endState;
	PriorityQueue<BoardState> states; 
	Comparator<BoardState> stateComparator; 
	Stack<String> moves;  
	HashSet<BoardState> oldStates;
	int counter = 0;
	
	public Solver(BoardState initBoardState, BoardState endState) {
		this.initBoardState = initBoardState;
		this.endState = endState;
		oldStates = new HashSet<BoardState>();
		oldStates.add(initBoardState);
		stateComparator = new BoardStateComparator(endState); 
		states = new PriorityQueue<BoardState>(15, stateComparator); 
		moves = new Stack<String>();
		states.add(initBoardState);
	}
	public static void main(String[] args) {
		
		if (isThereInputError(args)) {
			System.out.println("Invalid init and/or goal file");
			return;
		}
		File init = new File(args[0]);
		File goal = new File(args[1]);
		
		ArrayList<String> initStrings = readFile(init);
		ArrayList<String> goalStrings = readFile(goal);
		
		String[] sizes = initStrings.get(0).split(" ");
		int xSize = Integer.parseInt(sizes[0]);
		int ySize = Integer.parseInt(sizes[1]);
		initStrings.remove(0);
		
		ArrayList<Block> blocks = createBlockArray(initStrings);
		ArrayList<Block> endBlocks = createBlockArray(goalStrings);
		if (isValidBoardState(blocks, xSize, ySize) && isValidBoardState(endBlocks, xSize, ySize)) {
			BoardState boardState = new BoardState(blocks, xSize, ySize);
			BoardState endState = new BoardState(endBlocks, xSize, ySize);
			Solver solver = new Solver(boardState, endState);
			if (solver.solve()) {
				solver.moves.pop();
				while (!solver.moves.isEmpty()) {
					System.out.println(solver.moves.pop());
				}
			}
		} else {
			System.out.println("Invalid init and/or goal file");
		}
	}
	
	public boolean solve() {
		BoardState bs = states.poll();
		while(!isWinner(bs)) {
			for (Block b: bs.getBlocks()) {
				ArrayList<BoardState> potentialStates = bs.possibleMoves(b);
				for (BoardState st : potentialStates) {
					if ( !oldStates.contains(st) ) {
						states.add(st);
						oldStates.add(st);
					}
				}
			}
			if (states.isEmpty()) {
				return false;
			} else {
				bs = states.poll();
			}
		}
		while(bs != null) {
			moves.add(bs.getPreviousMove());
			bs = bs.getPreviousBoardState();
		}
		return true; 
	}

	
	public boolean isWinner(BoardState bs) {
		Block[][] board = bs.getBoard();
		for (Block b : endState.getBlocks()) {
			for (int i = b.getTopLeftX(); i <= b.getBotRightX(); i++) {
				for (int j = b.getTopLeftY(); j <= b.getBotRightY(); j++) {
					if (!(b.equals(board[i][j]))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static ArrayList<Block> createBlockArray(ArrayList<String> strings) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (String s : strings) { // ArrayList of block positions
			String[] point = s.split(" ");
			blocks.add(new Block(Integer.parseInt(point[0]),Integer.parseInt(point[1]),
							Integer.parseInt(point[2]), Integer.parseInt(point[3])));
		}
		return blocks;
	}
	public static ArrayList<String> readFile(File f) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
		    String line = reader.readLine();
		    while (line != null) {
		       lines.add(line);
			    line = reader.readLine();
		    }
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static boolean isValidBoardState(ArrayList<Block> blocks, int xsize, int ysize) {
		if (xsize <= 0 || ysize <= 0) {
			return false; 
		}
		boolean[][] board = new boolean[xsize][ysize];
		for (Block b : blocks) {
			int brx = b.getBotRightX();
			int bry = b.getBotRightY();
			int tlx = b.getTopLeftX();
			int tly = b.getTopLeftY();
			if (brx < 0 || bry < 0 || tlx < 0 || tly < 0) {
				return false; 
			} else if (brx >= xsize || bry >= ysize || tlx >= xsize || tly >= ysize) {
				return false;
			} else if (tlx > brx || tly > bry) {
				return false;
			} else {
				for (int i = tlx; i <= brx; i++) {
					for (int j = tly; j <= bry; j++) {
						if (board[i][j]) {
							return false;
						} else {
							board[i][j] = true;
						}
					}
				}
			}
		}
		return true;
	}
	
	public static boolean isThereInputError(String[] args) {
		if (args.length != 2) { 
			return true;
		} else {
			File init = new File(args[0]);
			File goal = new File(args[1]);
			if (!init.exists() || !goal.exists()) {
				return true;
			}
		}
		return false; 
	}
}
