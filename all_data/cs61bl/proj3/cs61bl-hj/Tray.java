
/**
 * Project 3: Puzzle Solver Written for CS61bl Summer 2015
 * 
 * @author Gayatri Sabne (dr), Kristin Ho (gl), Kevin Liu (hj)
 *
 *         Solver Codeshare: https://codeshare.io/PCtvo
 * 				Testing Codeshare:
 *         https://codeshare.io/GPA1C
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Tray {

	// VARIABLES

	private int[][] myTray; // BLANK SPACE IS A 0
	private ArrayList<int[]> myBlocks; // index is block name, String describes
										// coordinates
	private Tray myPrev; // reference to parent tray
	private String myMove; // what move was made to get here
	private HashSet<String> nextMoves; // original top-left coordinate of the
										// block, next top-left coordinate eg: 1
										// 1 0 1
	// TEMP
	public boolean debug = false;
	
	// CONSTRUCTORS
	// Constructing for the first time
	public Tray(int height, int width, ArrayList<int[]> inputPieceConfig) {
		myPrev = null;
		myMove = null;
		myTray = new int[height][width];
		myBlocks = inputPieceConfig;
		for (int i = 1; i < inputPieceConfig.size(); i++) {
			int[] line = inputPieceConfig.get(i);
			int x1 = line[0];
			int y1 = line[1];
			int x2 = line[2];
			int y2 = line[3];
			addBlock(i, x1, y1, x2, y2);
		}
		nextMoves = new HashSet<String>();
	}

	// Constructing based on a previous Tray (parent Tray)
	public Tray(String move, Tray prev) {
		myMove = move;
		myPrev = prev;
		int[][] prevTray = prev.getTray();
		myTray = new int[prevTray.length][prevTray[0].length];
    
// 		for(int i = 0; i < prev.getTray().length; i++){
// 			myTray[i] = Arrays.copyOf(prevTray[i], prevTray[0].length);
// 		}
    
		myBlocks = new ArrayList<int[]>();
		ArrayList<int[]> prevBlocks = prev.getBlocks();
        myBlocks.add(null);
		for(int i = 1; i < prevBlocks.size(); i++){
			myBlocks.add(Arrays.copyOf(prevBlocks.get(i), 4));
		}		
    
    // HOPEFULLY FASTER
    for (int i = 1; i < myBlocks.size(); i++) {
			int[] line = myBlocks.get(i);
			int x1 = line[0];
			int y1 = line[1];
			int x2 = line[2];
			int y2 = line[3];
			addBlock(i, x1, y1, x2, y2);
		}
    
		nextMoves = new HashSet<String>();
		update(myMove);
	}

	// METHODS

	// PUBLIC METHODS

	

	public void calculateMoves() {
		for (int i = 1; i < myBlocks.size(); i++) {
			if (canMove(i, 'l'))
				nextMoves.add(stringMove(i, 'l'));
			if (canMove(i, 'r'))
				nextMoves.add(stringMove(i, 'r'));
			if (canMove(i, 'u'))
				nextMoves.add(stringMove(i, 'u'));
			if (canMove(i, 'd'))
				nextMoves.add(stringMove(i, 'd'));
		}
	}

  public void addBlock(int count, int x1, int y1, int x2, int y2) {
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				myTray[i][j] = count;
			}
		}
	}
  
	public boolean canMove(int piece, char direction) {
		switch (direction) {
		case 'l': {
			int y1 = myBlocks.get(piece)[1];
			int newY = y1 - 1;
			if (newY < 0)
				return false;
			int x1 = myBlocks.get(piece)[0];
			int x2 = myBlocks.get(piece)[2];

			for (int h = x1; h <= x2; h++) {
				if (myTray[h][newY] != 0)
					return false;
			}
			return true;
		}
		case 'r': {

			int y1 = myBlocks.get(piece)[3];
			int newY = y1 + 1;
			if (newY >= myTray[0].length)
				return false;
			int x1 = myBlocks.get(piece)[0];
			int x2 = myBlocks.get(piece)[2];

			for (int h = x1; h <= x2; h++) {
				if (myTray[h][newY] != 0)
					return false;
			}
			return true;
		}
		case 'u': {
			int x1 = myBlocks.get(piece)[0];
			int newX = x1 - 1;
			if (newX < 0)
				return false;
			int y1 = myBlocks.get(piece)[1];
			int y2 = myBlocks.get(piece)[3];

			for (int w = y1; w <= y2; w++) {
				if (myTray[newX][w] != 0)
					return false;
			}
			return true;
		}
		case 'd': {
			int x1 = myBlocks.get(piece)[2]; // because you have to bottom space
												// of the block
			int newX = x1 + 1;
			if (newX >= myTray.length)
				return false;
			int y1 = myBlocks.get(piece)[1];
			int y2 = myBlocks.get(piece)[3];

			for (int w = y1; w <= y2; w++) {
				if (myTray[newX][w] != 0)
					return false;
			}
			return true;
		}
		default:
			break;
		}

		return false;
	}

	public String stringMove(int piece, char direction) {
		switch (direction) {
		case 'l': {
			int x1 = myBlocks.get(piece)[0];
			int y1 = myBlocks.get(piece)[1];
			return x1 + " " + y1 + " " + x1 + " " + (y1 - 1);
		}
		case 'r': {
			// return a string with og x y, and x + 1, y
			int x1 = myBlocks.get(piece)[0];
			int y1 = myBlocks.get(piece)[1];
			return x1 + " " + y1 + " " + x1 + " " + (y1 + 1);
		}
		case 'u': {
			// return a string with og x y, and x, y + 1
			int x1 = myBlocks.get(piece)[0];
			int y1 = myBlocks.get(piece)[1];
			return x1 + " " + y1 + " " + (x1 - 1) + " " + y1;

		}
		case 'd': {
			// return a string with og x y, and x, y - 1
			int x1 = myBlocks.get(piece)[0];
			int y1 = myBlocks.get(piece)[1];
			return x1 + " " + y1 + " " + (x1 + 1) + " " + y1;
		}
		default:
			break;
		}

		return "";
	}

	public void update(String theMove) {
		// DEBUG STUFF
		if (debug) {
			System.out
					.println("\n\n\n=======The tray before updating.=======\n");
			printTray();
			System.out.println("The move to be made is: " + theMove + "\n");
		}

		String[] moves = theMove.split(" ");
		int x1 = Integer.parseInt(moves[0]);
		int y1 = Integer.parseInt(moves[1]);
		int piece = myTray[x1][y1];

		// DEBUG STUFF
		if (debug) {
			System.out.println("\nPiece is " + piece + " x1 is " + x1
					+ " and y1 is " + y1);
			System.out.print("My original coords are ");
			for (int i : myBlocks.get(piece))
				System.out.print(i + " ");
		}
		
		
		int x2 = myBlocks.get(piece)[2];
		int y2 = myBlocks.get(piece)[3];

		int nextX1 = Integer.parseInt(moves[2]);
		int nextY1 = Integer.parseInt(moves[3]);
		int nextX2 = nextX1 + x2 - x1;
		int nextY2 = nextY1 + y2 - y1;

		if (nextX1 > x1) {
			for (int i = y1; i <= y2; i++) {
				myTray[x1][i] = 0; // shave top
				myTray[nextX2][i] = piece; // add to bottom
			}

		} else if (nextY1 > y1) {
			for (int i = x1; i <= x2; i++) {
				myTray[i][y1] = 0; // shave left
				myTray[i][nextY2] = piece; // add to right

			}

		} else if (nextX1 < x1) {
			for (int i = y1; i <= y2; i++) {
				myTray[x2][i] = 0; // shave bottom
				myTray[nextX1][i] = piece; // add to top
			}

		} else { // nextY1 < y1
			for (int i = x1; i <= x2; i++) {
				myTray[i][y2] = 0; // shave right
				myTray[i][nextY1] = piece; // add to left
			}
		}

		int[] nextPiece = { nextX1, nextY1, nextX2, nextY2 };
		myBlocks.set(piece, nextPiece);

		// DEBUG STUFF
		if (debug) {
			System.out.println("\n\n=====The tray after updating.=======\n");
			printTray();
			System.out.print("My later coords are ");
			for (int i : myBlocks.get(piece))
				System.out.print(i + " ");
		}
	}

	public void printTray() {
		for (int i = 0; i < myTray.length; i++) {
			for (int j = 0; j < myTray[0].length; j++) {
				System.out.print(myTray[i][j] + " ");
			}
			System.out.println();
		}
	}

	// GETTER METHODS

	public Tray getPrev() {
		return myPrev;
	}

	public int[][] getTray() {
		return myTray;
	}

	public ArrayList<int[]> getBlocks() {
		return myBlocks;
	}

	public String getMove() {
		return myMove;
	}

	public HashSet<String> getNextMoves() {
		return nextMoves;
	}

	// METHODS OVERRIDEN FROM OBJECT

	public int hashCode() {
		return Arrays.deepHashCode(myTray);
	}

	public boolean equals(Object obj) {
		return Arrays.deepEquals(((Tray) obj).getTray(), myTray);

	}

}


