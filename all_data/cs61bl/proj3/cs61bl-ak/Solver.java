import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Solver {

	ArrayList<String> initConfig;
	ArrayList<String> goalConfig;
	Integer boardHeight;
	Integer boardWidth;
	Board myInit;
	Board myGoal;
	
	public Solver (File init, File goal) throws IOException {
		initConfig = readFile(init);
		goalConfig = readFile(goal);
		
		String[] size = initConfig.get(0).split("\\s+");
		boardHeight = Integer.parseInt(size[0]);
		boardWidth = Integer.parseInt(size[1]);
		initConfig.remove(0);
		
		myInit = new Board(initConfig);
		myGoal = new Board(goalConfig);
	}
	
	public ArrayList<String> readFile(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		ArrayList<String> board = new ArrayList<>();
		while (br.ready()) {
	    	board.add(br.readLine());
	    }
		br.close();
		return board;
	}
	
	public void solve() {
		Set<Board> visited = new HashSet<>();
		Stack<Board> fringe = new Stack<>();
		HashMap<Board, Board> steps = new HashMap<Board, Board>();
		fringe.push(myInit);
		Boolean isGoal = true;
		while (!fringe.empty()) {
			Board current = fringe.pop();
			isGoal = true;
			for (String key : myGoal.myBlocks.keySet()) {
				Block b = current.myBlocks.get(key);
				if (myGoal.myBlocks.get(key).equals(b)) {
					continue;
				}
				isGoal = false;
				break;
			}
			if (isGoal) {
				myGoal = current;
				break;
			}
			visited.add(current);
			for (Board b : current.makeMoves()) {
				if (!visited.contains(b)) {
					steps.put(b, current);
					fringe.push(b);
				}
			}
		}
		if (isGoal) {
			Stack<Board> reversePath = new Stack<>();
			Board currentStep = myGoal;
			reversePath.push(currentStep);
			while (currentStep != null) {
				Board previousStep = steps.get(currentStep);
				if (previousStep != null) {
					reversePath.push(previousStep);
				}
				currentStep = previousStep;
			}
			while (!reversePath.empty()) {
				String previousStep = reversePath.pop().myMove;
				if (previousStep != null) {
					System.out.println(previousStep);
				}
			}
		}
	}
	
	private class Board {
		
		HashMap<String, Block> myBlocks;
		HashSet<String> myEmpties;
		String myMove;
		int myHashCode;
		
		private Board(ArrayList<String> blocks) {
			myMove = null;
			boolean[][] myCoordinates = new boolean[boardHeight][boardWidth];
			myEmpties = new HashSet<String>();
			myBlocks = new HashMap<String, Block>();
			for (String bl : blocks) {
				String[] nums = bl.split("\\s");
				myBlocks.put(nums[0] + " " + nums[1], new Block(nums));
			}
			for (Block bl : myBlocks.values()) {
				Integer blY = bl.myCoordinates[0];
				Integer blX = bl.myCoordinates[1];
				for (int y = blY; y <= blY + bl.myHeight; y++) {
					for (int x = blX; x <= blX + bl.myWidth; x++) {
						myCoordinates[y][x] = true;
					}
				}
			}
			for (int y = 0; y < boardHeight; y++) {
				for (int x = 0; x < boardWidth; x++) {
					if (!myCoordinates[y][x]) {
						myEmpties.add(y + " " + x);
					}
				}
			}
			String bigString = "";
			PriorityQueue<String> order = new PriorityQueue<>();
			order.addAll(myBlocks.keySet());
			while (!order.isEmpty()) {
				bigString += myBlocks.get(order.poll()).myName;
			}
			myHashCode = bigString.hashCode();
		}
		
		public Board (HashMap<String, Block> blocks, HashSet<String> empties, String move) {
			myBlocks = blocks;
			myEmpties = empties;
			myMove = move;
			String bigString = "";
			PriorityQueue<String> order = new PriorityQueue<>();
			order.addAll(myBlocks.keySet());
			while (!order.isEmpty()) {
				bigString += myBlocks.get(order.poll()).myName;
			}
			myHashCode = bigString.hashCode();
		}
		
		public ArrayList<Board> makeMoves() {
			ArrayList<Board> allMoves = new ArrayList<>();
			for (Block bl : myBlocks.values()) {
				ArrayList<Integer[]> moves = bl.moves(myEmpties);
				for (Integer[] mv : moves) {
					allMoves.add(makeMove(mv));
				}
			}
			return allMoves;
		}
		
		public Board makeMove(Integer[] move) {
			String oldPos = move[0] + " " + move[1];
			String newPos = move[2] + " " + move[3];
			HashMap<String, Block> newBlocks = new HashMap<String, Block>(myBlocks);
			HashSet<String> newEmpties = new HashSet<String>(myEmpties);
			Block oldBlock = myBlocks.get(oldPos);
			newBlocks.remove(oldPos);
			newBlocks.put(newPos, oldBlock.move(move));
			Integer vertMove = move[2] - move[0];
			Integer horizMove = move[3] - move[1];
			Integer[] oldCoords = oldBlock.myCoordinates;
			if (horizMove != 0) {
				Integer x1;
				Integer x2;
				if (horizMove < 0) {
					x1 = oldCoords[1] - 1;
					x2 = oldCoords[3];
				} else {
					x1 = oldCoords[3] + 1; 
					x2 = oldCoords[1];
				}
				for (int y = oldCoords[0]; y <= oldCoords[2]; y++) {
					String oldEmpty = y + " " + x1;
					String newEmpty = y + " " + x2;
					newEmpties.remove(oldEmpty);
					newEmpties.add(newEmpty);
				}
			} else {
				Integer y1;
				Integer y2;
				if (vertMove < 0) {
					y1 = oldCoords[0] - 1;
					y2 = oldCoords[2];
				} else {
					y1 = oldCoords[2] + 1;
					y2 = oldCoords[0];
				}
				for (int x = oldCoords[1]; x <= oldCoords[3]; x++) {
					String oldEmpty = y1 + " " + x;
					String newEmpty = y2 + " " + x;
					newEmpties.remove(oldEmpty);
					newEmpties.add(newEmpty);
				}
			}
			String thisMove = move[0] + " " + move[1] + " " + move[2] + " " + move[3];
			return new Board(newBlocks, newEmpties, thisMove);
		}
		
		public int hashCode() {
			return myHashCode;
		}
		
		public boolean equals(Object other) {
			if (other instanceof Board) {
				if (((Board) other).myBlocks.equals(myBlocks)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		try{
			File init = new File(args[0]);
			File goal = new File(args[1]);
			if (!init.exists() || !goal.exists()) {
				System.out.println("Invalid init and/or goal file");
			} else {
				Solver game = new Solver(init, goal);
				game.solve();
			}
		} catch(Exception e) {
			
		}
	}
}