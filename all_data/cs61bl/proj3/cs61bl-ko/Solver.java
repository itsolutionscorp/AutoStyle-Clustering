import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Solver {

	/**
	 * size - the size of the Tray, as read from the first line of the initial
	 * configuration file.
	 * 
	 * initial - the first Tray that holds all Blocks in their initial
	 * configuration, as read from said file.
	 * 
	 * winConfig - the Tray that contains the winning configuration of Block(s),
	 * as read from the goal configuration file.
	 * 
	 * myTrays - the HashSet of all Tray objects created to find the winning
	 * configuration, works as a cache as to not duplicate any Trays.
	 * 
	 * numGoals - the number of goal placements needed to successfully solve the
	 * puzzle.
	 * 
	 * winningSizes - the HashSet of each goal's dimensions, represented in a
	 * String
	 * 
	 * invalid Config - the boolean, initially set to false, which prevents the
	 * algorithm from continuing if there is an invalid configuration of blocks
	 * in the initial Tray or winConfig Tray
	 */
	private static int[] size;
	private Tray initial;
	private Tray winConfig;
	private HashSet<Tray> myTrays;
	private static int numGoals;
	private static HashSet<String> winningSizes;
	private static boolean invalidConfig;

	/**
	 * The constructor for the Solver algorithm, called in the main class.
	 * 
	 * @param lines
	 *            A List of Strings of each line of the initial configuration
	 *            file, as parsed by the readAllLines method.
	 * @param goals
	 *            A List of Strings of each line of the goal configuration file,
	 *            as parsed by the readAllLines method.
	 */
	public Solver(List<String> lines, List<String> goals) {
		size = readLine(lines.get(0));
		if (size.length != 2) {
			invalidConfig = true;
		}
		winningSizes = new HashSet<String>();
		initial = new Tray(setInitialConfig(lines), null, null, null);
		winConfig = new Tray(setGoalConfig(goals), null, null, null);
		myTrays = new HashSet<Tray>();
		myTrays.add(initial);
	}

	/**
	 * A method called to parse a String of numbers and index each number
	 * separated by a space into an int array.
	 * 
	 * @param line
	 *            The String of numbers to be parsed.
	 * @return an int array of numbers of length 2 if reading the first line of
	 *         initial configuration file, or length 4 if reading any other line
	 *         of either file.
	 */
	private static int[] readLine(String line) {
		String[] split = line.split(" ");
		int[] ret = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			try {
				ret[i] = Integer.parseInt(split[i]);
			} catch (NumberFormatException e) {
				invalidConfig = true;
			}
		}
		return ret;
	}

	/**
	 * A method called to convert the ints in a two-element int array into a
	 * String where the elements are separated by a space.
	 */
	private static String arrayToString(int[] arr) {
		return "" + arr[0] + " " + arr[1];
	}

	/**
	 * A method called to create a HashMap of String keys to Block values, which
	 * is passed in as an argument to the winConfig Tray in the Solver
	 * constructor.
	 * 
	 * @param goals
	 *            A List of Strings, where each String is a separate line from
	 *            the goal configuration file.
	 * @return A HashMap of String keys to Block Values.
	 */
	private static HashMap<String, Block> setGoalConfig(List<String> goals) {
		HashMap<String, Block> goalHM = new HashMap<String, Block>();
		for (int k = 0; k < goals.size(); k++) {
			int[] read = readLine(goals.get(k));
			if (read.length != 4) {
				invalidConfig = true;
			}
			numGoals++;
			Block newBlock = new Block(read[0], read[1], read[2], read[3]);
			if (!inRange(newBlock, newBlock.getTopLeft())) {
				invalidConfig = true;
			}
			winningSizes.add(arrayToString(newBlock.getDimension()));
			for (int i = read[0]; i < read[2] + 1; i++) {
				for (int j = read[1]; j < read[3] + 1; j++) {
					String yx = "" + i + " " + j;
					if (goalHM.put(yx, newBlock) != null) {
						invalidConfig = true;
					}
				}
			}
		}
		return goalHM;
	}

	/**
	 * A method called to create a HashMap of String keys to Block values, which
	 * is passed in as an argument to the initial Tray in the Solver
	 * constructor.
	 * 
	 * @param lines
	 *            A List of Strings, where each String is a separate line from
	 *            the initial configuration file.
	 * @return A HashMap of String keys to Block Values.
	 */
	private static HashMap<String, Block> setInitialConfig(List<String> lines) {
		HashMap<String, Block> initialHM = new HashMap<String, Block>();
		for (int k = 1; k < lines.size(); k++) {
			int[] read = readLine(lines.get(k));
			if (read.length != 4) {
				invalidConfig = true;
			}
			Block newBlock = new Block(read[0], read[1], read[2], read[3]);
			if (!inRange(newBlock, newBlock.getTopLeft())) {
				invalidConfig = true;

			}
			for (int i = read[0]; i < read[2] + 1; i++) {
				for (int j = read[1]; j < read[3] + 1; j++) {
					String yx = "" + i + " " + j;
					if (initialHM.put(yx, newBlock) != null) {
						invalidConfig = true;
					}
				}
			}
		}
		return initialHM;
	}

	/**
	 * A method called upon to find all the possible moves a Block inside a Tray
	 * can make. A move is defined as a movement one unit right, left, up, or
	 * down. This method calls upon the isValidMove method.
	 * 
	 * @param b
	 *            The Block of which to find the possible moves.
	 * @param t
	 *            The Tray which contains the Block.
	 * @return An ArrayList of int arrays, where each array is a valid location
	 *         to where the Block can move.
	 */
	private ArrayList<int[]> findAllPossibleMoves(Block b, Tray t) {
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] startLocation = b.getTopLeft();
		int[] right = { startLocation[0], startLocation[1] + 1 };
		if (isValidMove(b, right, t)) {
			ret.add(right);
		}
		int[] left = { startLocation[0], startLocation[1] - 1 };
		if (isValidMove(b, left, t)) {
			ret.add(left);
		}
		int[] up = { startLocation[0] - 1, startLocation[1] };
		if (isValidMove(b, up, t)) {
			ret.add(up);
		}
		int[] down = { startLocation[0] + 1, startLocation[1] };
		if (isValidMove(b, down, t)) {
			ret.add(down);
		}
		return ret;
	}

	/**
	 * A method called upon to determine whether a Block's movement to a
	 * location passes the criteria for being a valid move. This method calls
	 * upon the inRange and blockFits methods.
	 * 
	 * @param b
	 *            The Block which will potentially be moved.
	 * @param location
	 *            The top left corner of the location to which the Block will
	 *            potentially be moved, represented as an int array of size 2.
	 * @param t
	 *            The Tray which contains the Block and location.
	 * @return true if both the inRange and blockFits methods return true when
	 *         called upon with the given parameters.
	 */
	private boolean isValidMove(Block b, int[] location, Tray t) {
		return inRange(b, location) && blockFits(b, location, t);
	}

	/**
	 * A method called upon to determine whether or not the movement of the
	 * Block to the specified location will result in the Block remaining in the
	 * boundaries of the Tray.
	 * 
	 * @param b
	 *            The Block which will potentially be moved.
	 * @param location
	 *            The top left corner of the location to which the Block will
	 *            potentially be moved, represented as an int array of size 2.
	 * @return true if the movement of the Block to the location will result in
	 *         the entire block remaining inside the Tray's boundaries.
	 */
	private static boolean inRange(Block b, int[] location) {
		int[] dimensions = b.getDimension();
		if (location[0] > size[0] || location[1] > size[1]) {
			return false;
		}
		if (location[0] < 0 || location[1] < 0) {
			return false;
		}
		if (location[0] + dimensions[0] > size[0]
				|| location[1] + dimensions[1] > size[1]) {
			return false;
		}
		return true;
	}

	/**
	 * A method called upon to determine whether the movement of the Block to
	 * the specified location will result in the Block overlapping onto another
	 * Block.
	 * 
	 * @param b
	 *            The Block which will potentially be moved.
	 * @param location
	 *            The top left corner of the location to which the Block will
	 *            potentially be moved, represented as an int array of size 2.
	 * @param t
	 *            The Tray which contains the Block and the location.
	 * @return true if the movement of the Block to the location will not result
	 *         in the Block overlapping onto another Block.
	 */
	private boolean blockFits(Block b, int[] location, Tray t) {
		int[] block = b.getDimension();
		String checkSpot = "";
		for (int i = location[0]; i < location[0] + block[0]; i++) {
			for (int j = location[1]; j < location[1] + block[1]; j++) {
				checkSpot = "" + i + " " + j;
				Block check = t.getMyBlocks().get(checkSpot);
				if (check != null && !check.equals(b)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * A method which does the heavy-lifting of the solving algorithm. It uses a
	 * priority queue to prioritize which Tray should be traversed first to find
	 * the quickest solution. It continues to run while a winning Tray has not
	 * been found or the priority queue is not empty, creating multiple Trays
	 * with each block moved in every possible direction, and repeats this
	 * process when each Tray is added to the prioirty queue once again.
	 * 
	 * @param t
	 *            The Tray to be traversed, the initial Tray.
	 * @return the winning Tray, if one is found, or a null Tray otherwise.
	 */
	private Tray bestTraversal(Tray t) {
		PriorityQueue<Tray> pq = new PriorityQueue<Tray>(20,
				new TrayPriority<Tray>("cumulative"));
		if (winner(t)) {
			return t;
		}
		pq.add(t);
		while (!pq.isEmpty()) {
			Tray biggestPriority = pq.poll();
			myTrays.add(biggestPriority);

			HashSet<Block> doneBlocks = new HashSet<Block>();
			PriorityQueue<Block> goodBlocks = new PriorityQueue<Block>(20,
					new BlockPriority<Block>(biggestPriority));
			for (Block b : biggestPriority.getMyBlocks().values()) {
				goodBlocks.add(b);
			}
			while (!goodBlocks.isEmpty()) {
				Block toMove = goodBlocks.poll();
				if (!doneBlocks.contains(toMove)) {
					doneBlocks.add(toMove);
					ArrayList<int[]> moves = findAllPossibleMoves(toMove,
							biggestPriority);
					for (int[] newMove : moves) {
						Tray next = makeNewTray(toMove, newMove,
								biggestPriority);
						if (!myTrays.contains(next)) {
							if (winner(next)) {
								return next;
							}
							pq.add(next);
							myTrays.add(next);
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * A method which determines the distance between a Block and it's
	 * corresponding goal location, given that the Block fits in the goal
	 * location.
	 * 
	 * @param b
	 *            The given Block.
	 * @param goal
	 *            The given Block's corresponding goal, represented as a Block.
	 * @return The linear distance between the Block b and its goal.
	 */
	private double distanceBetween(Block b, Block goal) {
		int[] bPos = b.getTopLeft();
		int[] goalPos = goal.getTopLeft();
		return Math.sqrt(Math.pow(bPos[1] - goalPos[1], 2)
				+ (Math.pow(bPos[0] - goalPos[0], 2)));
	}

	/**
	 * This nested class is a Comparator for Trays, to determine which Tray is
	 * most likely to solve the puzzle, and is therefore given the highest
	 * priority in the priority queue implemented in the bestTraversal method.
	 *
	 * @param <T>
	 *            <T> will always be a Tray
	 */
	private class TrayPriority<T> implements Comparator<T> {
		private String myType;

		/**
		 * The constructor for the TrayPriority Class.
		 * 
		 * @param type
		 *            The String of how we want to prioritize Trays in our
		 *            traversal.
		 */
		public TrayPriority(String type) {
			myType = type;
		}

		/**
		 * A getter method for the TrayPriority's myType instance.
		 * 
		 * @return The TrayPriority's myType instance.
		 */
		public String getMyType() {
			return myType;
		}

		/**
		 * A method called upon to prioritize the distance between the Block
		 * just moved in the given Tray and the Block's corresponding goal.
		 * 
		 * @param tray
		 *            The given tray of which to find the best priority.
		 * @return A part of the priority value assigned to the Tray, as a
		 *         double.
		 */
		private double prioritizeDistance(T tray) {
			double distanceSoFar = Double.MAX_VALUE;
			Block moved = ((Tray) tray).getMyMoved();
			if (moved == null) {
				return Double.MAX_VALUE;
			}
			for (Block win : winConfig.getMyBlocks().values()) {
				if (Arrays.equals(moved.getDimension(), win.getDimension())) {
					distanceSoFar = Math.min(distanceSoFar,
							distanceBetween(moved, win));
				}
			}
			return distanceSoFar;
		}

		/**
		 * A method which prioritizes Trays which have more Blocks in correct
		 * goal positions.
		 * 
		 * @param tray
		 *            The given tray of which to find the best prioirity.
		 * @return A part of the priority value assigned to the Tray, as a
		 *         double.
		 */
		private double prioritizeCount(T tray) {
			return numGoals * ((Tray) tray).getBlocksInPlace();
		}

		/**
		 * A method that overrides the compare method of Comparators. It
		 * compares the priorities of two different Trays, and appropriately
		 * returns and int as an evaluation of its findings.
		 */
		@Override
		public int compare(T o1, T o2) {
			double priorityOne = 0, priorityTwo = 0;
			if (myType.equals("distance")) {
				priorityOne = prioritizeDistance(o1);
				priorityTwo = prioritizeDistance(o2);
			}
			if (myType.equals("cumulative")) {
				priorityOne = prioritizeDistance(o1) - prioritizeCount(o1);
				priorityTwo = prioritizeDistance(o2) - prioritizeCount(o2);
			}
			if (priorityOne == priorityTwo) {
				return 0;
			} else if (priorityOne < priorityTwo) {
				return -1;
			} else
				return 1;

		}

		/**
		 * A method that overrides the equals method of the Object class. It
		 * determines whether or not two TrayPriority instances are prioritized
		 * by the same standards.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			return myType.equals(((TrayPriority<T>) obj).getMyType());
		}
	}

	/**
	 * This nested class essentially determines and prioritizes each Block in a
	 * given Tray.
	 *
	 * @param <T>
	 */
	private class BlockPriority<T> implements Comparator<T> {
		private Tray blockTray;

		/**
		 * The constructor for the BlockPriority class.
		 * 
		 * @param t
		 *            The Tray whose Blocks we are prioirtizing.
		 */
		public BlockPriority(Tray t) {
			blockTray = t;
		}

		/**
		 * The overriden compare method. It checks deterimes the relationship
		 * between two Blocks' priorities.
		 */
		@Override
		public int compare(T o1, T o2) {
			double priorityOne = prioritizeBlock(o1);
			double priorityTwo = prioritizeBlock(o2);
			if (priorityOne == priorityTwo) {
				return 0;
			} else if (priorityOne < priorityTwo) {
				return -1;
			} else
				return 1;
		}

		/**
		 * A method which assigns more priority to a bigger Block, and a lesser
		 * priority to a smaller Block.
		 * 
		 * @param block
		 *            The Block to be prioritized.
		 * @return The priority value assigned to the given Block, represented
		 *         as a double.
		 */
		public double prioritizeBlock(T block) {
			double myPriority = 100.0;
			int[] dimensions = ((Block) block).getDimension();
			if (((Block) block).getWinnerSize()) {
				myPriority -= 20;
				if (dimensions[0] > 3 || dimensions[1] > 3) {
					myPriority -= 5;
				}
			}
			return myPriority;
		}

		/**
		 * The overriden equals method. It is never used, but still must be overriden.
		 */
		@Override
		public boolean equals(Object obj) {
			return true;
		}
	}

	/**
	 * A method called upon in the makeMove method to create a new Tray. This
	 * method copies the HashMap of the parent Tray's Blocks, removes the Block
	 * to be moved, and puts in the Block moved to the new location.
	 * 
	 * @param b
	 *            The Block which will be moved.
	 * @param newLocation
	 *            The top left corner of the location to which the Block will be
	 *            moved, represented as an int array of size 2.
	 * @param parent
	 *            The parent Tray of the Tray to be created. This Tray contains
	 *            the Block in its original (not necessarily initial) location.
	 * @return a new Tray whose Block b is moved to the new location.
	 */
	private Tray makeNewTray(Block b, int[] newLocation, Tray parent) {
		int[] completeLocation = new int[4];
		completeLocation[0] = newLocation[0];
		completeLocation[1] = newLocation[1];
		int[] dimension = b.getDimension();

		completeLocation[2] = newLocation[0] + dimension[0] - 1;
		completeLocation[3] = newLocation[1] + dimension[1] - 1;

		HashMap<String, Block> newBlocks = new HashMap<String, Block>();
		newBlocks.putAll(parent.getMyBlocks());
		int[] oldLocation = b.getLocation();
		for (int y = oldLocation[0]; y < oldLocation[2] + 1; y++) {
			for (int x = oldLocation[1]; x < oldLocation[3] + 1; x++) {
				String temp1 = "" + y + " " + x;
				newBlocks.remove(temp1);
			}
		}
		Block rightBlock = new Block(completeLocation[0], completeLocation[1],
				completeLocation[2], completeLocation[3]);

		if (winningSizes.contains(arrayToString(rightBlock.getDimension()))) {
			rightBlock.setWinnerSize();
		}

		for (int i = completeLocation[0]; i < completeLocation[2] + 1; i++) {
			for (int j = completeLocation[1]; j < completeLocation[3] + 1; j++) {
				String temp2 = "" + i + " " + j;
				newBlocks.put(temp2, rightBlock);
			}
		}
		String theMove = "" + oldLocation[0] + " " + oldLocation[1] + " "
				+ newLocation[0] + " " + newLocation[1];
		return new Tray(newBlocks, parent, theMove, rightBlock);
	}

	/**
	 * A method called upon to determine whether or not the specified Tray's
	 * Blocks are in the winning configuration.
	 * 
	 * @param t
	 *            The Tray whose Blocks are potentially in the winning
	 *            configuration.
	 * @return true if the Tray's Blocks are in the winning configuration.
	 */
	private boolean winner(Tray t) {
		int blocksInPlace = 0;
		boolean toReturn = true;
		for (Block b : winConfig.getMyBlocks().values()) {
			String bKey = b.getTopLeft()[0] + " " + b.getTopLeft()[1];
			Block check = t.getMyBlocks().get(bKey);
			if (check == null || !check.equals(b)) {
				toReturn = false;
			}
			blocksInPlace++;
		}
		t.setBlocksInPlace(blocksInPlace);
		return toReturn;
	}

	/**
	 * This method traces the winning Tray's movement of Blocks through its
	 * ancestors until it reaches the initial Tray. It prints out all the
	 * movements made when each new Tray was created.
	 * 
	 * @param t
	 *            The Tray whose Block configuration matches the Block
	 *            configuration of the winning configuration Tray, potentially
	 *            null if no winning Tray is found.
	 */
	private void retrace(Tray t) {
		if (t == null) {
			return;
		}
		Tray temp = t;
		LinkedList<String> iterMe = new LinkedList<String>();
		while (!temp.equals(initial)) {
			iterMe.addFirst(temp.toString());
			temp = temp.getMyParent();
		}
		for (String s : iterMe) {
			System.out.println(s);
		}
	}

	/**
	 * The main method of the Solver algorithm.
	 * 
	 * @param args
	 *            The initial and goal configuration files passed into the
	 *            terminal.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file");
			return;
		}
		File init = new File(args[0]);
		File goal = new File(args[1]);
		if (!init.exists() || !goal.exists()) {
			System.out.println("Invalid init and/or goal file");
			return;
		}
		List<String> lines = new ArrayList<String>();
		List<String> goals = new ArrayList<String>();
		try {
			lines = Files.readAllLines(init.toPath());
			goals = Files.readAllLines(goal.toPath());
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file");
			return;
		}
		Solver algorithm = new Solver(lines, goals);
		if (invalidConfig == true) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		Tray winnerTray = algorithm.bestTraversal(algorithm.initial);
		algorithm.retrace(winnerTray);
	}
}
