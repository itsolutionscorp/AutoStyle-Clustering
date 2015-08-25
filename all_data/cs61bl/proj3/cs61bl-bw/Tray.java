import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Tray {// @Tim
	private int myRows;
	private int myCols;
	private int[] moveCoords = new int[4];

	ArrayList<int[]> goal;

	// matrix that represents the tray
	private int[][] blockTray;

	// map of block id's and lists of coordinates
	HashMap<Integer, int[]> trayConfig;
	HashMap<Integer, Block> trayBlocks; // @Jennifer

	// number of blocks in tray
	// private int numBlocks;

	private Tray previous;
	// a list of possible moves/trays. needs to check whether each one is in
	// hashmap configsSeen
	private ArrayList<Tray> childrenTrays = new ArrayList<Tray>();

	// Constructor: takes in int rows and columns and makes the blocktray
	// that size as well as initializing the hashmap
	public Tray(int rows, int cols) {
		blockTray = new int[rows][cols];
		trayConfig = new HashMap<Integer, int[]>();
		// numBlocks = 0;
		myRows = rows;
		myCols = cols;
		// goal = goalLines;
	}

	public Tray(int rows, int cols, Tray parent) {
		blockTray = new int[rows][cols];
		trayConfig = new HashMap<Integer, int[]>();
		// numBlocks = 0;
		previous = parent;
		myRows = rows;
		myCols = cols;
	}

	public void setGoal(ArrayList<int[]> myGoal) {
		goal = myGoal;
	}

	public void setMoveCoords(int a, int b, int c, int d) { // @Kelly
		moveCoords[0] = a;
		moveCoords[1] = b;
		moveCoords[2] = c;
		moveCoords[3] = d;
	}

	public int[] getMoveCoords() {
		return moveCoords;
	}

	public int getRowCount() {
		return myRows;
	}

	public int getColCount() {
		return myCols;
	}

	public Block getBlock(int blockID) { // @Jennifer
		if (!trayBlocks.containsKey(blockID)) {
			return null;
		} else {
			return trayBlocks.get(blockID);
		}
	}

	// getter for the HashMap, used in PossibleMoves @Jennifer
	public HashMap<Integer, int[]> getBlocksMap() {
		return trayConfig;
	}

	public void setBlocks(int a, int[] b) {
		trayConfig.put(a, b);
	}

	// getter for the Matrix, used in equals @Kelly
	public int[][] getMatrix() {
		return blockTray;
	}

	public void setMatrix(int a, int b, int c) {
		blockTray[a][b] = c;
	}

	// returns if empty, also checks out of bounds @Jennifer
	public boolean indexEmpty(int x, int y) {
		if (x < 0 || x >= getRowCount() || y < 0 || y >= getColCount()) {
			return false;
		} else {
			return blockTray[x][y] == 0;
		}

	}

	// top left == 0,0
	// inserts a specific block into the blocktray,
	// adds the id and location into the hashMap trayConfig
	public void insert(ArrayList<Block> blockList) {
		for (Block a : blockList) {
			// numBlocks += 1;
			if (a.getLocation()[0] > myRows || a.getLocation()[1] > myCols
					|| a.getLocation()[2] > myRows
					|| a.getLocation()[3] > myCols) {

				throw new IllegalArgumentException(
						"Invalid init and/or goal file.");
			} else {
				trayConfig.put(a.getID(), a.getLocation());
				for (int i = a.getLocation()[0]; i <= a.getLocation()[2]; i++) {
					for (int j = a.getLocation()[1]; j <= a.getLocation()[3]; j++) {
						// if there's something there before you insert, it is
						// impossible!
						if (blockTray[i][j] != 0) {
							throw new IllegalArgumentException(
									"Invalid init and/or goal file.");
						} else {
							blockTray[i][j] = a.getID();
						}
					}
				}
			}
		}

	}

	public boolean goalReached() {
		// Collection<int[]> temp = trayConfig.values();
		HashSet<Integer> checked = new HashSet<Integer>();
		boolean found = false;
		int count = 0;
		for (int[] b : goal) {
			for (Integer i : trayConfig.keySet()) {
				if (checked.contains(i)) {
					continue;
				} else if (Arrays.equals(trayConfig.get(i), b)) {
					checked.add(i);
					count++;
					found = true;
					break;
				}
			}
			if (found == false) {
				break;
			}
			found = false;
		}

		if (count == goal.size()) {
			return true;
		} else {
			return false;
		}
	}

	// public boolean goalReached() { // @Kelly
	//
	// for (int[] a : goal) { // changed goalCoords to goal
	// int goalX1 = a[0];
	// int goalY1 = a[1];
	// int goalX2 = a[2];
	// int goalY2 = a[3];
	//
	// int upperLeftID = blockTray[goalX1][goalY1]; // A
	// int lowerRightID = blockTray[goalX2][goalY2]; // A
	// if (upperLeftID != lowerRightID) {
	// // matrix ID not the same
	// return false;
	// }
	//
	// if (upperLeftID == 0 || lowerRightID == 0) {
	// // matrix ID is 0 (empty)
	// return false;
	// }
	//
	// for (int x = goalX1 - 1; x <= goalX2 + 1; x++) {
	// if (x < 0 || x > getRowCount() - 1) { // if x out of bounds,
	// // skip, don't check
	// continue;
	// }
	//
	// for (int y = goalY1 - 1; y <= goalY2 + 1; y++) {
	// if (y < 0 || y > getColCount() - 1) {
	// // if y out of bounds, skip, don't check
	// continue;
	// } else if (x >= goalX1 && x <= goalX2 && y >= goalY1
	// && y <= goalY2) {
	// if (blockTray[x][y] != upperLeftID) {
	// return false;
	// }
	// continue; // if it is true, continue;
	// } else if (blockTray[x][y] == upperLeftID) {
	// // for all other coordinates
	// // if the matrix ID stored
	// // there is equal to the
	// // block ID, return false;
	// return false;
	// }
	// }
	// }
	// }
	// return true;
	// }
	@Override
	public int hashCode() { // @Michael
		final int prime = 31;
		int result = 1;
		for (int[] b : this.trayConfig.values()) {
			int a = b[0] * 256 * 256 * 256 + b[1] * 256 * 256 + b[2] * 256
					+ b[3];
			result = prime * result + a;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Tray t = (Tray) obj;

		for (Integer i : trayConfig.keySet()) {
			if (!Arrays.equals(t.trayConfig.get(i), trayConfig.get(i))) {
				return false;
			}
		}
		return true;
	}

	public void setChildren(Tray t) {
		childrenTrays.add(t);
	}

	public void setParent(Tray t) {
		previous = t;
	}

	public Tray getParent() {
		return previous;
	}

	@SuppressWarnings("unchecked")
	public Tray clone() {
		Tray temp = new Tray(myRows, myCols, previous);
		temp.blockTray = new int[myRows][myCols];
		for (int i = 0; i < myRows; i++) {
			// for (int j = 0; j < myCols; j++) {
			// temp.blockTray[i][j] = blockTray[i][j];
			// }
			temp.blockTray[i] = (int[]) Arrays.copyOf(blockTray[i], myCols);
		}

		// temp.blockTray = blockTray.clone();
		// temp.trayConfig = new HashMap<Integer, int[]>();
		// temp.trayConfig.putAll(trayConfig);

		temp.trayConfig = (HashMap<Integer, int[]>) trayConfig.clone();
		// temp.numBlocks = numBlocks;
		return temp;
	}

	/**
	 * This will print out a board with each number representing a block
	 * 
	 * @Michael
	 */
	public void printBoard() { // @Michael
		System.out.println();
		int k = 0;
		while (k < myCols + 2) {
			k++;
			System.out.print('_');
		}
		System.out.println();
		for (int i = 0; i < myRows; i++) {
			System.out.print('|');
			for (int j = 0; j < myCols; j++) {
				if (blockTray[i][j] == 0) {
					System.out.print(' ');
				} else {
					System.out.print(blockTray[i][j]);
				}
			}
			System.out.println('|');

		}
		k = 0;
		while (k < myCols + 2) {
			k++;
			System.out.print('_');
		}
		System.out.println();
	}
}