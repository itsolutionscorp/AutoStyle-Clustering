import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;

public class Solver {
	Tray curTray;
	Tray initTray;
	final int rowNum;
	final int colNum;
	ArrayList<int[]> goal; // changed goalCoords to goal
	private HashSet<Tray> configSeen;
	private LinkedList<Tray> history = new LinkedList<Tray>();
	private int blockCount = 0;

	public Solver(int row, int column, Tray initial, ArrayList<int[]> goalList) {

		this.rowNum = row;
		this.colNum = column;
		this.goal = goalList; // changed goalCoords to goal

		initTray = initial;
		initTray.setGoal(goalList);

		curTray = initTray;
		configSeen = new HashSet<Tray>();
		history = new LinkedList<Tray>();

	}

	private HashMap<Integer, boolean[]> possibleMoves(Tray t) { // @Jennifer
		HashMap<Integer, boolean[]> rtn = new HashMap<Integer, boolean[]>();
		for (Integer i : t.getBlocksMap().keySet()) {
			int[] coord = t.getBlocksMap().get(i);
			// [x1, y1, x2, y2]
			boolean moveUp = true; // N
			boolean moveDown = true; // S
			boolean moveRight = true; // E
			boolean moveLeft = true; // W

			int x1 = coord[0];
			int y1 = coord[1];
			int x2 = coord[2];
			int y2 = coord[3];

			int height = Math.abs(x2 - x1);
			int width = Math.abs(y2 - y1);

			// METHOD 1
			for (int h = 0; h <= height; h++) {
				if (moveLeft == true && !t.indexEmpty(x1 + h, y1 - 1)) {
					moveLeft = false;
				}
				if (moveRight == true && !t.indexEmpty(x1 + h, y2 + 1)) {
					moveRight = false;
				}
			}

			for (int w = 0; w <= width; w++) {
				if (moveDown == true && !t.indexEmpty(x2 + 1, y1 + w)) {
					moveDown = false;
				}
				if (moveUp == true && !t.indexEmpty(x1 - 1, y1 + w)) {
					moveUp = false;
				}
			}

			// N, S, E, W
			boolean[] move = new boolean[4];
			move[0] = moveUp;
			move[1] = moveDown;
			move[2] = moveRight;
			move[3] = moveLeft;

			rtn.put(i, move);
		}

		return rtn;
	}

	public void toMove() {
		while (!curTray.goalReached()) {
			makeMove();
			if (curTray == null) {
				return;
			}
		}
	}

	public void makeMove() { // @Kelly and Michael
		/*
		 * takes in tray, picks block to move (calls from helper), uses a
		 * .equals to see if it's been visited before, creates all the new
		 * possible trays, set chldren to array
		 */

		// call helper
		Stack<int[]> temp = priorityHelper(curTray);
		Tray childTray = curTray.clone();
		childTray.setParent(curTray);

		while (temp != null && !temp.isEmpty()) {
			int[] bestMove = temp.pop();

			// create the tray by cloning the curTray
			int moveBlockID = bestMove[0]; // call helper

			// change the hashmap in the clone
			int oldX1 = curTray.getBlocksMap().get(moveBlockID)[0];
			int oldY1 = curTray.getBlocksMap().get(moveBlockID)[1];
			int oldX2 = curTray.getBlocksMap().get(moveBlockID)[2];
			int oldY2 = curTray.getBlocksMap().get(moveBlockID)[3];

			int newX1 = bestMove[1]; // new coordinate
			int newY1 = bestMove[2]; // new coordinate

			int xShift = newX1 - oldX1;
			int yShift = newY1 - oldY1;

			// calculate new x2 and y2 based on the shifts
			int newX2 = oldX2 + xShift;
			int newY2 = oldY2 + yShift;
			int[] newConfig = { newX1, newY1, newX2, newY2 };

			childTray.setBlocks(moveBlockID, newConfig);

			// check the tray
			if (configSeen.contains(childTray)) {
				int[] oldConfig = new int[] { oldX1, oldY1, oldX2, oldY2 };
				childTray.setBlocks(moveBlockID, oldConfig);
				continue;
			} else { // it's a new tray
				blockCount++;
				Block newBlock = new Block(blockCount, newX1, newY1, newX2,
						newY2);
				if (xShift < 0) {
					// move up (N)
					newBlock.setDirFalse(0);
				} else if (xShift > 0) {
					// move down (S)
					newBlock.setDirFalse(1);
				} else if (yShift < 0) {
					// move left (W)
					newBlock.setDirFalse(3);
				} else if (yShift > 0) {
					// move right (E)
					newBlock.setDirFalse(2);
				}

				childTray.setMoveCoords(oldX1, oldY1, newX1, newY1);
				childTray.setGoal(goal);

				// change the blockTray
				for (int i = oldX1; i <= oldX2; i++) {
					for (int j = oldY1; j <= oldY2; j++) {
						childTray.setMatrix(i, j, 0);
					}
				}

				for (int i = newX1; i <= newX2; i++) {
					for (int j = newY1; j <= newY2; j++) {
						childTray.setMatrix(i, j, moveBlockID);
					}
				}

				curTray.setChildren(childTray);
				childTray.setParent(curTray);
				curTray = childTray;
				history.add(curTray);
				configSeen.add(curTray);
				// curTray.
				return;
			}
		}
		curTray = curTray.getParent(); // where's our getParent
		if (curTray == null) {
			return;
		}
		history.removeLast();
	}

	// Method that Prints out the path @Michael
	public void printsPath() {
		for (Tray t : history) {
			for (int i = 0; i < 3; i++) {
				System.out.print(t.getMoveCoords()[i]);
				System.out.print(" ");
			}
			System.out.println(t.getMoveCoords()[3]);
		}
	}

	// returns a priority queue
	// int[] first index is the block ID
	// 2 and 3rd index are the x1, y1 of the upper left corner of the toMove
	// location
	private Stack<int[]> priorityHelper(Tray t) { // @Jennifer
		HashMap<Integer, boolean[]> possible = possibleMoves(t);
		if (possible.isEmpty()) {
			return null; // no possible moves
		}

		// BlockComparator comp = new BlockComparator();
		Stack<int[]> rtn = new Stack<int[]>();
		// new LinkedList<int[]>();

		for (Integer key : possible.keySet()) {
			boolean[] direction = possible.get(key); // n/s/e/w
			if (direction[0]) { // up/N
				int[] temp0 = new int[3];
				temp0[0] = key;
				temp0[1] = t.getBlocksMap().get(key)[0] - 1;
				temp0[2] = t.getBlocksMap().get(key)[1];
				rtn.add(temp0);
			}
			if (direction[1]) { // down/S
				int[] temp1 = new int[3];
				temp1[0] = key;
				temp1[1] = t.getBlocksMap().get(key)[0] + 1;
				temp1[2] = t.getBlocksMap().get(key)[1];
				rtn.add(temp1);
			}
			if (direction[2]) { // right/E
				int[] temp2 = new int[3];
				temp2[0] = key;
				temp2[1] = t.getBlocksMap().get(key)[0];
				temp2[2] = t.getBlocksMap().get(key)[1] + 1;
				rtn.add(temp2);
			}
			if (direction[3]) { // left/W
				int[] temp3 = new int[3];
				temp3[0] = key;
				temp3[1] = t.getBlocksMap().get(key)[0];
				temp3[2] = t.getBlocksMap().get(key)[1] - 1;
				rtn.add(temp3);
			}

		}

		return rtn;
	}

	private class BlockComparator implements Comparator<int[]> { // @Jennifer
		@Override
		public int compare(int[] o1, int[] o2) { // only returns 1, -1
			// TODO Auto-generated method stub

			// block 1 coordinates
			int b10 = curTray.getBlocksMap().get(o1[0])[0];
			int b11 = curTray.getBlocksMap().get(o1[0])[1];
			int b12 = curTray.getBlocksMap().get(o1[0])[2];
			int b13 = curTray.getBlocksMap().get(o1[0])[3];

			// block 2 coordinates
			int b20 = curTray.getBlocksMap().get(o2[0])[0];
			int b21 = curTray.getBlocksMap().get(o2[0])[1];
			int b22 = curTray.getBlocksMap().get(o2[0])[2];
			int b23 = curTray.getBlocksMap().get(o2[0])[3];

			int h1 = b12 - b10;
			int w1 = b13 - b11;
			int h2 = b22 - b20;
			int w2 = b23 - b21;

			boolean sizeMatch1 = false;
			boolean sizeMatch2 = false;

			for (int[] list : goal) {
				int goalH = Math.abs(list[0] - list[2]);
				int goalW = Math.abs(list[1] - list[3]);
				if (goalH == h1 && goalW == w1) {
					sizeMatch1 = true;
				}
				if (goalH == h2 && goalW == w2) {
					sizeMatch2 = true;
				}
			}

			if (sizeMatch1 == true && sizeMatch2 == false) {
				return -1;
			}
			if (sizeMatch2 == true && sizeMatch1 == false) {
				return 1;
			}

			// comparing blocks of the same size.
			double inf = Double.POSITIVE_INFINITY;
			double minD1 = inf;
			double minD2 = inf;

			// x1, y1, x2, y2
			for (int[] list : goal) { // changed goalCoords to goal
				double xDist1 = list[0] - o1[1];
				double yDist1 = list[1] - o1[2];
				double xDist2 = list[0] - o2[1];
				double yDist2 = list[1] - o2[2];

				double d1 = Math.pow(xDist1, 2) + Math.pow(yDist1, 2);
				double d2 = Math.pow(xDist2, 2) + Math.pow(yDist2, 2);
				d1 = Math.sqrt(d1);
				d2 = Math.sqrt(d2);

				// update to the smallest distance to any goal block
				if (d1 < minD1) {
					minD1 = d1;
				}
				if (d2 < minD2) {
					minD2 = d2;
				}

				// if block 1 is already in the goal position, try not to move
				// it!
				if (b10 == list[0] && b11 == list[1] && b12 == list[2]
						&& b13 == list[3]) {
					return 1;
				}
				// if block 2 is already in the goal position, try not to move
				// it!
				if (b20 == list[0] && b21 == list[1] && b22 == list[2]
						&& b23 == list[3]) {
					return -1;
				}

			}
			// block 1 is closer to one of the goal locations
			if (minD1 < minD2) {
				return -1;
			}

			// block 2 is closer to one of the goal locations
			if (minD1 > minD2) {
				return 1;
			}

			return 0;
		}
	}

	public static void main(String[] args) {
		Solver solve = null;
		Scanner scanner = null;
		Scanner goalScan = null;

		if (args.length == 2) { // needs to have two files (init & goal)
			String init = args[0];
			String goal = args[1];
			try {
				scanner = new Scanner(new BufferedReader(new FileReader(init)));
				goalScan = new Scanner(new BufferedReader(new FileReader(goal)));
				int r = 0; // init row
				int c = 0; // init col

				// initialize tray with the given dimensions
				if (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] ints = line.split("[ ]");
					if (ints.length != 2) {
						throw new IllegalArgumentException();
					}
					r = Integer.parseInt(ints[0]);
					c = Integer.parseInt(ints[1]);
				} else {
					throw new IllegalArgumentException();
				}

				// initialize trays
				Tray initialTray = new Tray(r, c);
				int blockCount = 0; // start with no blocks
				ArrayList<Block> blockList = new ArrayList<Block>();

				ArrayList<Integer> height = new ArrayList<Integer>();
				ArrayList<Integer> width = new ArrayList<Integer>();

				// create new blocks based on the init file
				while (scanner.hasNextLine()) {

					String line = scanner.nextLine();
					String[] coord = line.split("[ ]");
					if (coord.length == 4) {
						blockCount++;
						int x1 = Integer.parseInt(coord[0]);
						int y1 = Integer.parseInt(coord[1]);
						int x2 = Integer.parseInt(coord[2]);
						int y2 = Integer.parseInt(coord[3]);

						if (x1 >= r || x2 >= r || y1 >= c || y2 >= c) {
							throw new IllegalArgumentException();
						}

						if (x1 > x2 || y1 > y2) {
							throw new IllegalArgumentException();
						}

						// keep track of all the block sizes
						height.add(x2 - x1);
						width.add(y2 - y1);

						Block block = new Block(blockCount, x1, y1, x2, y2);
						blockList.add(block);
					} else {
						throw new IllegalArgumentException();
					}
				}

				int endBlockCount = 0;
				ArrayList<int[]> goalCoords = new ArrayList<int[]>();

				while (goalScan.hasNextLine()) {
					String line = goalScan.nextLine();
					String[] finalCoord = line.split("[ ]");
					if (finalCoord.length == 4) {
						endBlockCount++;
						int y1 = Integer.parseInt(finalCoord[0]);
						int x1 = Integer.parseInt(finalCoord[1]);
						int y2 = Integer.parseInt(finalCoord[2]);
						int x2 = Integer.parseInt(finalCoord[3]);

						int[] list = new int[4];
						list[0] = y1;
						list[1] = x1;
						list[2] = y2;
						list[3] = x2;
						goalCoords.add(list);

					} else {
						throw new IllegalArgumentException();
					}
				}

				initialTray.insert(blockList);
				solve = new Solver(r, c, initialTray, goalCoords);

				if (endBlockCount > blockCount) {
					return;
				}

				if (solve != null) {
					solve.toMove();
					solve.printsPath();
				}

			} catch (FileNotFoundException e) {
				System.out.println("Invalid init and/or goal file.");
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid init and/or goal file.");
			} finally { // close all the scanners when you're done

				if (scanner != null) {
					scanner.close();
				}

				if (goalScan != null) {
					goalScan.close();
				}

			}

		} else {
			// error message, more than two files or arguments entered in
			// command line

			System.out.println("Invalid init and/or goal file.");
		}
	}
}
