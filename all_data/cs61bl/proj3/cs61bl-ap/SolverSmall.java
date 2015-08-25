import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;



public class SolverSmall extends Solver {

	State begin;
	State end;

	public boolean stateIsOk(State s) {
		try {
			s.blankSpace();
		} catch (IllegalStateException e) {
			return false;
		}
		return true;
	}

	public void init(String initConfigFile, String goalConfigFile) {

		Scanner configScr = null;
		try {
			configScr = new Scanner(new FileReader(initConfigFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}
		State initial = new State();
		begin = initial;
		try {
			initial.mySize[0] = Integer.parseInt(configScr.next());
			initial.mySize[1] = Integer.parseInt(configScr.next());
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}

		try {
			while (configScr.hasNext()) {
				Scanner lineScanner = new Scanner(configScr.nextLine());
				while(lineScanner.hasNext()){
					int a = Integer.parseInt(lineScanner.next());
					int b = Integer.parseInt(lineScanner.next());
					int c = Integer.parseInt(lineScanner.next());
					int d = Integer.parseInt(lineScanner.next());
					if (lineScanner.hasNext()){
						System.out.println("Invalid init and/or goal file.");
						System.exit(1);
					}
					if (a>= 0 && a < begin.mySize[0] && c >= 0 && c < begin.mySize[0] &&
							b >= 0 && b< begin.mySize[1] && d>= 0 && d<begin.mySize[1]){
						Block bl = new Block(a, b, c, d);
						begin.myBlocks.add(bl);
					}else{
						System.out.println("Invalid init and/or goal file.");
						System.exit(1);
					}
				}
			}
		} catch (Exception e1) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}

		if (!stateIsOk(initial)) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}

		Scanner goalScr = null;
		try {
			goalScr = new Scanner(new FileReader(goalConfigFile));
		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");

			System.exit(1);
		}
		State goal = new State();
		end = goal;

		goal.mySize[0] = initial.mySize[0];
		goal.mySize[1] = initial.mySize[1];

		try {
			while (goalScr.hasNext()) {
				Scanner lineScanner = new Scanner(goalScr.nextLine());
				while (lineScanner.hasNext()) {
					int a = Integer.parseInt(lineScanner.next());
					int b = Integer.parseInt(lineScanner.next());
					int c = Integer.parseInt(lineScanner.next());
					int d = Integer.parseInt(lineScanner.next());
					if (lineScanner.hasNext()) {
						System.out.println("Invalid init and/or goal file.");
						System.exit(1);
					}
					if (a >= 0 && a < goal.mySize[0] && c >= 0
							&& c < goal.mySize[0] && b >= 0
							&& b < goal.mySize[1] && d >= 0
							&& d < goal.mySize[1]) {
						Block bl = new Block(a, b, c, d);
						goal.myBlocks.add(bl);
					} else {
						System.out.println("Invalid init and/or goal file.");
						System.exit(1);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}

		if (!stateIsOk(goal)) {
			System.out.println("Invalid init and/or goal file.");

			System.exit(1);
		}
		


	}

	private class State {
		int[] mySize = new int[2];
		List<Block> myBlocks;
		State myPred;
		ArrayList<Coordinate> blankSpace;
		String toThisState;

		public State() {
			myBlocks = new LinkedList<Block>();
			toThisState = "";
		}

		public State(State s) {
			myBlocks = new LinkedList<Block>();
			for (Block b : s.myBlocks) {
				myBlocks.add(b.copy());
			}
			myPred = s;
			toThisState = s.toThisState;
			mySize = s.mySize;
		}

		@Override
		public int hashCode() {
			int rtn = 0;
			for (int i = 0; i < myBlocks.size(); i++) {
				rtn += myBlocks.get(i).hashCode();
			}
			return rtn;
		}

		@Override
		public boolean equals(Object other) {
			State b = (State) other;
			if (mySize[0] != b.mySize[0] || mySize[1] != b.mySize[1]) {
				return false;
			}
			if (myBlocks.size() != b.myBlocks.size()) {
				return false;
			}

			for (Block block : myBlocks) {
				if (!b.myBlocks.contains(block)) {
					return false;
				}
			}
			return true;
		}

		public ArrayList<Coordinate> blankSpace() {// array of integer pairs
			if (blankSpace != null) {
				return blankSpace;
			}
			ArrayList<Coordinate> rtn = new ArrayList<Coordinate>();
			int[][] board = new int[mySize[0]][mySize[1]];
			for (Block b : myBlocks) {
				for (int i = b.upLeftCoord.myX; i <= b.bottomRightCoord.myX; i++) {
					for (int j = b.upLeftCoord.myY; j <= b.bottomRightCoord.myY; j++) {
						if (board[i][j] == 0) {
							board[i][j] = 1;
						} else if (board[i][j] == 1) {
							throw new IllegalStateException();
						}
					}
				}
			}
			for (int i = 0; i < mySize[0]; i++) {
				for (int j = 0; j < mySize[1]; j++) {
					if (board[i][j] != 1) {
						Coordinate toAdd = new Coordinate(i, j);
						rtn.add(toAdd);
					}
				}
			}
			blankSpace = rtn;
			return rtn;
		}
	}

	public boolean[] canMove(Block block, State s) {// return List of possible
													// moves of a block, 1st
		// elem is up, 2nd is down, 3rd is
		// left, 4th is right
		boolean[] rtn = new boolean[4];
		rtn[0] = canMoveUp(block, s);
		rtn[1] = canMoveDown(block, s);
		rtn[2] = canMoveLeft(block, s);
		rtn[3] = canMoveRight(block, s);
		return rtn;

	}

	public boolean canMoveUp(Block block, State s) {
		for (int i = block.upLeftCoord.myY; i <= block.bottomRightCoord.myY; i++) {
			Coordinate toCheck = new Coordinate(block.upLeftCoord.myX - 1, i);
			if (!s.blankSpace().contains(toCheck)) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveDown(Block block, State s) {
		for (int i = block.upLeftCoord.myY; i <= block.bottomRightCoord.myY; i++) {
			Coordinate toCheck = new Coordinate(block.bottomRightCoord.myX + 1,
					i);
			if (!s.blankSpace().contains(toCheck)) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveLeft(Block block, State s) {
		for (int i = block.upLeftCoord.myX; i <= block.bottomRightCoord.myX; i++) {
			Coordinate toCheck = new Coordinate(i, block.upLeftCoord.myY - 1);
			if (!s.blankSpace().contains(toCheck)) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveRight(Block block, State s) {
		for (int i = block.upLeftCoord.myX; i <= block.bottomRightCoord.myX; i++) {
			Coordinate toCheck = new Coordinate(i,
					block.bottomRightCoord.myY + 1);
			if (!s.blankSpace().contains(toCheck)) {
				return false;
			}
		}
		return true;
	}

	public boolean isTheSame(State a, State b) {
		return a.equals(b);
	}

	public List<State> stateMaker(State start) {// return an arraylist of states
												// based on all possible moves
												// from one state
		List<State> rtn = new ArrayList<State>();
		for (Block block : start.myBlocks) {
			boolean[] possibleMoves = canMove(block, start);
			for (int i = 0; i < 4; i++) {
				if (possibleMoves[i]) {
					rtn.add(moveBlock(block, start, i));
				}
			}
		}
		return rtn;
	}

	public State moveBlock(Block b, State s, int move) {// create a new state
														// based on a move
		if (move == 0) {// up
			State rtn = new State(s);
			rtn.myBlocks.remove(b);
			Block afterMove = new Block(b.upLeftCoord.myX - 1,
					b.upLeftCoord.myY, b.bottomRightCoord.myX - 1,
					b.bottomRightCoord.myY);
			rtn.myBlocks.add(afterMove);
			String toAdd = b.upLeftCoord.myX + " " + b.upLeftCoord.myY + " "
					+ (b.upLeftCoord.myX - 1) + " " + b.upLeftCoord.myY + "\n";
			rtn.toThisState = rtn.toThisState + toAdd;
			return rtn;
		}
		if (move == 1) {// down
			State rtn = new State(s);
			rtn.myBlocks.remove(b);
			Block afterMove = new Block(b.upLeftCoord.myX + 1,
					b.upLeftCoord.myY, b.bottomRightCoord.myX + 1,
					b.bottomRightCoord.myY);
			rtn.myBlocks.add(afterMove);
			String toAdd = b.upLeftCoord.myX + " " + b.upLeftCoord.myY + " "
					+ (b.upLeftCoord.myX + 1) + " " + b.upLeftCoord.myY + "\n";
			rtn.toThisState = rtn.toThisState + toAdd;
			return rtn;
		}
		if (move == 2) {// left
			State rtn = new State(s);
			rtn.myBlocks.remove(b);
			Block afterMove = new Block(b.upLeftCoord.myX,
					b.upLeftCoord.myY - 1, b.bottomRightCoord.myX,
					b.bottomRightCoord.myY - 1);
			rtn.myBlocks.add(afterMove);
			String toAdd = b.upLeftCoord.myX + " " + b.upLeftCoord.myY + " "
					+ b.upLeftCoord.myX + " " + (b.upLeftCoord.myY - 1) + "\n";
			rtn.toThisState = rtn.toThisState + toAdd;
			return rtn;
		}
		if (move == 3) {// right
			State rtn = new State(s);
			rtn.myBlocks.remove(b);
			Block afterMove = new Block(b.upLeftCoord.myX,
					b.upLeftCoord.myY + 1, b.bottomRightCoord.myX,
					b.bottomRightCoord.myY + 1);
			rtn.myBlocks.add(afterMove);
			String toAdd = b.upLeftCoord.myX + " " + b.upLeftCoord.myY + " "
					+ b.upLeftCoord.myX + " " + (b.upLeftCoord.myY + 1) + "\n";
			rtn.toThisState = rtn.toThisState + toAdd;
			return rtn;
		}

		return null;
	}

	public boolean isGoal(State curr) {
		for (Block bl : end.myBlocks) {
			if (!curr.myBlocks.contains(bl)) {
				return false;
			}
		}
		return true;
	}

	public String solverSmall(State begin, State goal) {
		HashSet<State> visited = new HashSet<State>();
		Stack<State> fringe = new Stack<State>();

		fringe.add(begin);
		while (!fringe.isEmpty()) {
			State curr = fringe.pop();
			if (isGoal(curr)) {
				return curr.toThisState;
			} else {
				visited.add(curr);
				List<State> childStates = stateMaker(curr);
				if (childStates.isEmpty()) {
					continue;
				}
				for (State s : childStates) {
					if (!visited.contains(s)) {
						fringe.push(s);
					} else {
						s = null;
					}
				}
			}
		}
		return null;
	}
}
