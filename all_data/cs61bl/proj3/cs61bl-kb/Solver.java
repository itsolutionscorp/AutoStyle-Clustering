import java.util.Stack;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * TESTS FAILING
 * java Solver hard/big.tray.4 hard/many.blocks.20.goal | java -jar Checker.jar hard/big.tray.4 hard/many.blocks.20.goal
 * java Solver hard/big.tray.4 hard/many.blocks.20+180.goal | java -jar Checker.jar hard/big.tray.4 hard/many.blocks.20+180.goal
 * java Solver hard/big.tray.4 hard/many.blocks.100.goal | java -jar Checker.jar hard/big.tray.4 hard/many.blocks.100.goal
 * BULLET PROOFING ???
 */



/**
 * Solver program that finds the solution to sliding block puzzles.
 * If a solution leads from init to goal, it should be printed.
 * If there is no solution, the program should exit and nothing is printed.
 * Any other conditions prevent the program from running.
 */
public class Solver {
	// Height of tray.
	static int h;

	// Width of tray.
	static int w;

	// Initial configuration.
	static Vertex initConfig;

	// Goal configuration.
	static Vertex goalConfig;

	// Solver initialized.
	boolean yes;

	// All vertex associations with the edge that links them to their parent.
	HashMap<Vertex, Edge> all;

	/**
	 * Initializes Solver with an initFile and goalFile.
	 * @param initFile initial configuration file
	 * @param goalFile goal configuration file
	 */
	public Solver(String initFile, String goalFile) {
		try {
			Scanner init = new Scanner(new File(initFile));
			Scanner goal = new Scanner(new File(goalFile));

			// Set length and width of tray.
			h = init.nextInt();
			w = init.nextInt();

			// Read into init and goal files, creating configs from input.
			initConfig = readFile(init);
			goalConfig = readFile(goal);

			if (initConfig == null || goalConfig == null) {
				yes = false;
				System.out.println("Invalid init and/or goal file.");
			}

			// Initialize yes, all.
			else {
				yes = true;
				all = new HashMap<Vertex, Edge>();
			}
			
		} catch (FileNotFoundException f) {
			// Did not initialize, print error message.
			yes = false;
			System.out.println("Invalid init and/or goal file.");
		}
	}

	/**
	 * Takes in two file names as args and attempts to solve the puzzle.
	 * @param args system input
	 */
	public static void main(String[] args) {
		// Invalid number of args given.
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}

		// Initializes Solver.
		Solver s = new Solver(args[0], args[1]);

		// Solver initialized.
		if (s.yes) {
			// If initial config and goal config are not the same, run Solver.
			if (!s.initConfig.isGoal(goalConfig)) {
				s.all.put(initConfig, null); // Initial config is starting Vertex of graph.
				Vertex last = s.allConfigs(initConfig); // Finding all configurations.

				// Path from init to goal exists. Print out path of moves.
				if (last != null) {
					ArrayList<String> moves = s.path(initConfig, last);
					for (String each : moves) {
						System.out.println(each);
					}
				}
			}
		}
	}

	/**
	 * Stores found configurations to all.
	 * Terminates if goal config is found OR
	 * if all possible configs are found (there is no solution).
	 * @param v vertex which configs were found
	 */
	public Vertex allConfigs(Vertex parent) {
		Stack<Vertex> poss = new Stack<Vertex>();
		poss.push(parent);

		// All configs haven't been found or path hasn't been found.
		while (!poss.isEmpty()) {
			Vertex curr = poss.pop();
			Vertex last = new Vertex(new HashSet<Block>());
			HashMap<Vertex, String> valids = findConfigs(curr, last);

			for (Vertex v : valids.keySet()) {
					// System.out.println(valids.get(v));
					poss.push(v);
					all.put(v, new Edge(curr, v, valids.get(v)));
					if (v.equals(last)) {
						return last;
					}
			}
		}
		return null;
	}

	/**
	 * Finds possible configurations given vertex.
	 * @param v vertex to find configs for
	 */
	public HashMap<Vertex, String> findConfigs(Vertex v, Vertex l) {
		// Map of new configurations and move made.
		HashMap<Vertex, String> configs = new HashMap<Vertex, String>();

		// For each block in given config, check if it can move one unit in any direction.
		for (Block b : v.blocks()) {
			// b next to white space?
			if (v.canMove(b, "left")) {
				HashSet<Block> lst = new HashSet<Block>(v.blocks());
				Block n = b.moveBlock("left");
				lst.remove(b);
				lst.add(n);
				Vertex a = new Vertex(lst);
				if (!all.containsKey(a)) {
					configs.put(a, b.lCoord() + " " + n.lCoord());
					if (a.isGoal(goalConfig)) {
						l.myBlocks = a.blocks();
						return configs;
					}					
				}
			}

			if (v.canMove(b, "right")) {
				HashSet<Block> lst = new HashSet<Block>(v.blocks());
				Block n = b.moveBlock("right");
				lst.remove(b);
				lst.add(n);
				Vertex a = new Vertex(lst);
				if (!all.containsKey(a)) {
					configs.put(a, b.lCoord() + " " + n.lCoord());
					if (a.isGoal(goalConfig)) {
						l.myBlocks = a.blocks();
						return configs;
					}					
				}
			}
			if (v.canMove(b, "up")) {
				HashSet<Block> lst = new HashSet<Block>(v.blocks());
				Block n = b.moveBlock("up");
				lst.remove(b);
				lst.add(n);
				Vertex a = new Vertex(lst);
				if (!all.containsKey(a)) {
					configs.put(a, b.lCoord() + " " + n.lCoord());
					if (a.isGoal(goalConfig)) {
						l.myBlocks = a.blocks();
						return configs;
					}					
				}
			}

			if (v.canMove(b, "down")) {
				HashSet<Block> lst = new HashSet<Block>(v.blocks());
				Block n = b.moveBlock("down");
				lst.remove(b);
				lst.add(n);
				Vertex a = new Vertex(lst);
				if (!all.containsKey(a)) {
					configs.put(a, b.lCoord() + " " + n.lCoord());
					if (a.isGoal(goalConfig)) {
						l.myBlocks = a.blocks();
						return configs;
					}					
				}
			}
		}

		return configs;
	}

	/**
	 * Finds the path from init config and goal config.
	 * Precondition: pathExists.
	 * @param init initial configuration
	 * @param goal goal configuration
	 * @return string list of moves from init to goal
	 */
	public ArrayList<String> path(Vertex init, Vertex goal) {
		// String list of moves.
		ArrayList<String> moves = new ArrayList<String>();

		Vertex v = goal; // Pointer to goal, search backwards to init.
		while (!v.equals(init)) {
			Edge e = all.get(v);
			moves.add(e.toString());
			v = e.from();
		}

		Collections.reverse(moves);
		return moves;
	}

	/**
	 * Reads through a file and organizes information.
	 * @param in scanner of file
	 */
	public Vertex readFile(Scanner in) {
		// List of blocks for configuration.
		HashSet<Block> lst = new HashSet<Block>();

		// BULLET PROOF: IF FILES ARE EMPTY.

		// Read through entire file.
		while (in.hasNextInt()) {
			// Blocks are generated from four coordinates.
			Integer[] b = new Integer[4];
			for (int i = 0; i < 4; i += 1) {
				int n = in.nextInt();

				// Tests if block configurations are out of bounds.
				if ((i == 0 || i == 2) && (n < 0 || n >= h)) {
					return null;
				}
				if ((i == 1 || i == 3) && (n < 0 || n >= w)) {
					return null;
				}
				b[i] = n;
			}
			lst.add(new Block(b[0], b[1], b[2], b[3]));
		}
		return new Vertex(lst);
	}

	/**
	 * Block class that creates blocks with four coordinates.
	 */
	private class Block {
		// Integer coordinates of the block.
		private int lX, lY, rX, rY;

		/**
		 * Initializes a block using four given coordinates.
		 * @param a left x
		 * @param b left y
		 * @param c right x
		 * @param d right y
		 */
		public Block(int a, int b, int c, int d) {
			lX = a;
			lY = b;
			rX = c;
			rY = d;
		}

		/**
		 * Left x coordinate.
		 */
		public int leftX() {
			return lX;
		}

		/**
		 * Left y coordinate.
		 */
		public int leftY() {
			return lY;
		}

		/**
		 * Right x coordinate.
		 */
		public int rightX() {
			return rX;
		}

		/**
		 * Right y coordinate.
		 */
		public int rightY() {
			return rY;
		}

		/**
		 * Move block in given direction.
		 * @param dir direction to move block
		 * @return block with new coordinates
		 */
		public Block moveBlock(String dir) {
			switch (dir) {
				case "left":
					return new Block(lX, lY - 1, rX, rY - 1);

				case "right":
					return new Block(lX, lY + 1, rX, rY + 1);

				case "up":
					return new Block(lX - 1, lY, rX - 1, rY);

				case "down":
					return new Block(lX + 1, lY, rX + 1, rY);

				default:
					return this;
			}
		}

		/**
		 * Left coordinates of block.
		 * @return string coordinates
		 */
		public String lCoord() {
			return lX + " " + lY;
		}

		@Override
		public int hashCode() {
			return (lX + 1)*1000000000 + (lY + 1)*1000000 +
				   (rX + 1)*1000 + (rY + 1);
		}

		@Override
		public String toString() {
			return lX + " " + lY + " " + rX + " " + rY;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Block) {
				Block b = (Block) o;
				if (b.leftX() == leftX() && b.leftY() == leftY() &&
					b.rightX() == rightX() && b.rightY() == rightY()) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Vertex class that creates configurations with block positions.
	 */
	private class Vertex {
		// Block positions of vertex.
		private HashSet<Block> myBlocks;

		/**
		 * Instantiates a vertex with a list of blocks.
		 */
		public Vertex(HashSet<Block> lst) {
			myBlocks = lst;
		}

		/**
		 * List of myBlocks.
		 * @return list of blocks
		 */
		public HashSet<Block> blocks() {
			return myBlocks;
		}

		/**
		 * Compares if vertex has a configuration that meets goal config.
		 * @param g goal configuration
		 * @return vertex meets goal configuration
		 */
		public boolean isGoal(Vertex g) {
			for (Block b: g.blocks()) { //
				if (!myBlocks.contains(b)) {
					return false;
				}
			}
			return true;
		}

		/**
		 * Finds if given block in configuration can be moved one unit in any direction.
		 * @param b block to be moved
		 * @param dir direction to move block
		 * @return block can be moved in given direction
		 */
		public boolean canMove(Block b, String dir) {
			switch (dir) {
				case "left":
					for (Block each : myBlocks) {
						if (b.leftY() == 0) {
							return false;
						}
						if (b.leftY() - 1 == each.rightY()) {
							if (each.leftX() < b.leftX() && each.rightX() > b.rightX()) {
								return false;
							}
							if (b.leftX() <= each.leftX() && each.leftX() <= b.rightX()) {
								return false;
							}
							if (b.leftX() <= each.rightX() && each.rightX() <= b.rightX()) {
								return false;
							}
						}
					}
					return true;

				case "right":
					for (Block each : myBlocks) {
						if (b.rightY() == w - 1) {
							return false;
						}
						if (b.rightY() + 1 == each.leftY()) {
							if (each.leftX() < b.leftX() && each.rightX() > b.rightX()) {
								return false;
							}
							if (b.leftX() <= each.leftX() && each.leftX() <= b.rightX()) {
								return false;
							}
							if (b.leftX() <= each.rightX() && each.rightX() <= b.rightX()) {
								return false;
							}
						}
					}
					return true;

				case "up":
					for (Block each : myBlocks) {
						if (b.leftX() == 0) {
							return false;
						}
						if (b.leftX() - 1 == each.rightX()) {
							if (each.leftY() < b.leftY() && each.rightY() > b.rightY()) {
								return false;
							}
							if (b.leftY() <= each.leftY() && each.leftY() <= b.rightY()) {
								return false;
							}
							if (b.leftY() <= each.rightY() && each.rightY() <= b.rightY()) {
								return false;
							}
						}
					}
					return true;

				case "down":
					for (Block each : myBlocks) {
						if (b.rightX() == h - 1) {
							return false;
						}
						if (b.rightX() + 1 == each.leftX()) {
							if (each.leftY() < b.leftY() && each.rightY() > b.rightY()) {
								return false;
							}
							if (b.leftY() <= each.leftY() && each.leftY() <= b.rightY()) {
								return false;
							}
							if (b.leftY() <= each.rightY() && each.rightY() <= b.rightY()) {
								return false;
							}
						}
					}
					return true;

				default:
					return false;
			}
		}

		@Override
		public int hashCode() {
			int h = 1;
			for (Block b : myBlocks) {
				h *= b.hashCode();
			}
			return h;
		}

		@Override
		public String toString() {
			return myBlocks.toString();
		}		

		@Override
		public boolean equals(Object o) {
			if (o instanceof Vertex) {
				Vertex v = (Vertex) o;
				if (myBlocks.size() == v.blocks().size()) {
					return myBlocks.equals(v.blocks());
				}
			}
			return false;
		}
	}

	/**
	 * Edge class that contains vertices it links and associated move.
	 */
	private class Edge {
		// Vertex edge links from.
		private Vertex myFrom;

		// Vertex edge links to.
		private Vertex myTo;

		// Associated move.
		private String myMove;

		/**
		 * Instantiates new edge with from, to, and move inputs.
		 * @param from from vertex
		 * @param to to vertex
		 * @param move associated move
		 */
		public Edge(Vertex from, Vertex to, String move) {
			myFrom = from;
			myTo = to;
			myMove = move;
		}

		/**
		 * Vertex edge links from.
		 * @return from vertex
		 */
		public Vertex from() {
			return myFrom;
		}

		/**
		 * Vertex edge links to.
		 * @return to vertex
		 */
		public Vertex to() {
			return myTo;
		}

		@Override
		public String toString() {
			return myMove;
		}
	}
}
