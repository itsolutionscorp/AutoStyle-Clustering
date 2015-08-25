import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
//import java.util.List;
import java.util.Scanner;
//import java.util.Set;
import java.util.Stack;

/*Solver.java*/

/**
 * Solves a sliding block puzzle by creating a graph of Tray objects and
 * searching through it until a goal state is reached.
 * 
 * @authors Matt Choi, Kelly Lee, Alan Kwok
 */
public class Solver {

	/**
	 * visited - a hashset of trays to check whether stuff has been visited or
	 * not. start - keeps track of the initial tray in case we need to restart
	 * or in case no solution possible. currPos - the current tray position of
	 * the board. finished - reverses the list of moves to get to the end.
	 */
	HashSet<Tray> visited; // which ones are visited, adds at every node
	Tray currPos; // current tray
	Stack<String> finished; // Created at the end by going back using prevs

	/**
	 * Creates a new Solver object in order to just store info. Initializes all
	 * variables listed above.
	 * 
	 * @param initial
	 *            The start Tray.
	 */
	public Solver(Tray initial) {
		this.visited = new HashSet<Tray>(7500);
		this.currPos = initial;
		this.finished = new Stack<String>();
	}

	/**
	 * This traverses the graph of trays. None of the work is actually done
	 * here, it's mostly just logistics.
	 */
	public void solve() {
		while (!this.currPos.won()) { // if it is a winning config it stops
			this.visited.add(this.currPos); // adds current position to visited.
			this.currPos.move(); // generates moves if not done so already.
									// Partial lazy computation (computing when
									// we need it)
			while (this.currPos.nextMove == null) {
				if (this.currPos.prev == null) {
					return;
				}
				this.currPos = this.currPos.prev;
				this.currPos.move();
			}
			Tray trial = this.currPos.nextMove;
			while (visited.contains(trial)) { // you don't want to revisit a
												// previously visited one
				this.currPos.move();
				while (this.currPos.nextMove == null) {
					if (this.currPos.prev == null) {
						return;
					}
					this.currPos = this.currPos.prev;
					this.currPos.move();
				}
				trial = this.currPos.nextMove;
			}
			this.currPos = trial; // since this trial position works, change the
									// current tray pointer to it in order for
									// it to loop.
			if (this.currPos == null) { // need to update this to work.
				// Basically if impossible and no way to
				// get to the goal.
				return;
			}
		}
		while (this.currPos != null) { // this part just basically reverses the
										// list of moves for what to print out.
										// This prints out the shortest path we
										// found. (But does not find it the
										// shortest way)
			finished.add(this.currPos.movemsg);
			this.currPos = this.currPos.prev;
		}
		this.finished.pop(); // the first one is null. Don't worry about it.
		while (!this.finished.isEmpty()) {
			System.out.println(this.finished.pop()); // prints each one in the
														// right order.
		}
	}

	/**
	 * A class that represents a block in a sliding block puzzle.
	 */
	public static class Block implements Comparable<Block> {

		/**
		 * Instance variables are all shorts. Includes upper left coordinate and
		 * lower right coordinate, along with height and width. Height and width
		 * currently unused, so I commented them out.
		 */
		short y1;
		short x1;
		short y2;
		short x2;
		int code;

		/**
		 * Constructor for a block. Binds the 4 coordinates.
		 * 
		 * @param y1
		 *            The y coordinate of the top left corner.
		 * @param x1
		 *            The x coordinate of the top left corner.
		 * @param y2
		 *            The y coordinate of the bottom right corner.
		 * @param x2
		 *            The x coordinate of the bottom right corner.
		 * 
		 * @throws IllegalArgumentException
		 *             If incorrect arguments for block are passed in, this will
		 *             throw an exception.
		 */
		public Block(short y1, short x1, short y2, short x2)
				throws IllegalArgumentException {
			if (x2 < x1 || y2 < y1) {
				throw new IllegalArgumentException();
			}
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
			this.code = -1;
		}

		/**
		 * Creates a new block of the result of it being moved by a direction,
		 * represented by the byte c. For the byte c, 0 means right, 1 means
		 * down, 2 means left, 3 means up.
		 * 
		 * @param b
		 *            A block that you are moving.
		 * @param c
		 *            Which direction you are moving it to.
		 */
		public Block(Block b, byte c) {
			this.y1 = b.y1;
			this.x1 = b.x1;
			this.y2 = b.y2;
			this.x2 = b.x2;
			if (c == 0) { // this part just basically moves the coordinates the
							// correct ways. Convince yourself it's right. :P
				this.x1++;
				this.x2++;
			} else if (c == 1) {
				this.y1++;
				this.y2++;
			} else if (c == 2) {
				this.x1--;
				this.x2--;
			} else if (c == 3) {
				this.y1--;
				this.y2--;
			}
			this.code = -1;
		}

		/**
		 * Hash all 4 digits. 256^4 max combinations. Since there are 256
		 * different combinations, each of them will take 8 bits. This makes
		 * every hashcode guaranteed to be unique, since you have exactly 1
		 * number per configuration, and every possible block has a different
		 * number. If you're still confused, try looking at the solution for the
		 * hashCode midterm question.
		 * 
		 * @return Returns an integer from 0 to 2^32 - 1;
		 */
		@Override
		public int hashCode() {
			if (this.code != -1) {
				return this.code;
			} else {
				this.code = y1 * 256 * 256 * 256 + x1 * 256 * 256 + y2 * 256
						+ x2;
				return this.code;
			}
		}

		/**
		 * Return whether all 4 coordinates are equal.
		 * 
		 * @param o
		 *            Object to compare for equality check.
		 * 
		 * @return Returns true if equal, false otherwise.
		 */
		@Override
		public boolean equals(Object o) {
			return this.hashCode() == o.hashCode();
		}

		@Override
		public int compareTo(Block o) {
			if (this.hashCode() > o.hashCode()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * A class representing a state of the puzzle.
	 */
	public static class Tray {

		/**
		 * Knows its height and width, which will be used to make sure blocks
		 * don't move off the board. Also knows a hashset of the blocks. This
		 * along with the boolean array of points makes sure its unique. The
		 * boolean array of points is mostly to make moving stuff easier. My
		 * goal is another hashset of blocks. Prev is basically it's "parent",
		 * and movemsg is how it got from its parent. moves is its possible
		 * moves it can make. This is generated lazily.
		 */
		short myHeight;
		short myWidth;
		ArrayList<Block> myBlocks;
		ArrayList<Block> myGoal;
		Tray prev;
		Tray nextMove;
		String movemsg;
		MoveIterator mover;
		int code;

		/**
		 * Constructor for initial tray config.
		 * 
		 * @param height
		 * @param width
		 * @param blocks
		 * @param goal
		 */
		public Tray(short height, short width, ArrayList<Block> blocks,
				ArrayList<Block> goal) {
			this.myHeight = height;
			this.myWidth = width;
			setMaker(goal);
			setMaker(blocks);
			this.myBlocks = blocks;
			Collections.sort(this.myBlocks);
			this.myGoal = goal;
			this.code = -1;
		}

		/**
		 * Constructor for every subsequent tray config.
		 * 
		 * @param prev
		 * @param blocks
		 * @param posses
		 */
		public Tray(Tray prev, ArrayList<Block> blocks, String msg) {
			this.myHeight = prev.myHeight;
			this.myWidth = prev.myWidth;
			this.myGoal = prev.myGoal;
			this.myBlocks = blocks;
			Collections.sort(this.myBlocks);
			this.prev = prev;
			this.movemsg = msg;
			this.code = -1;
		}

		/**
		 * Creates the moves if not created already. This way it doesn't create
		 * the entire graph instantly, but rather one Node at a time. Can make
		 * it even more lazily computed my changing makeMoves.
		 */
		public void move() {
			if (this.mover == null) {
				this.mover = new MoveIterator(myBlocks);
			}
			this.mover.next();
		}

		/**
		 * 
		 * @param b
		 * @param blocks
		 * @param direction
		 * @return
		 */
		public static boolean overlaps(Block b, ArrayList<Block> blocks,
				byte direction) {
			for (Block a : blocks) {
				if (direction == 0) {
					if (b.x2 == a.x1 && !(b.y2 < a.y1 || a.y2 < b.y1)) { // checks
																			// if
																			// they're
																			// on
						// same horizontal
						return true;
					}
				} else if (direction == 1) {
					if (b.y2 == a.y1 && !(b.x2 < a.x1 || a.x2 < b.x1)) { // checks
																			// if
																			// they're
																			// on
						// same vertical
						return true;
					}
				} else if (direction == 2) {
					if (b.x1 == a.x2 && !(b.y2 < a.y1 || a.y2 < b.y1)) { // checks
																			// if
																			// they're
																			// on
						// same horizontal
						return true;
					}
				} else if (direction == 3) {
					if (b.y1 == a.y2 && !(b.x2 < a.x1 || a.x2 < b.x1)) { // checks
																			// if
																			// they're
																			// on
						// same vertical
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * Checks if a move is valid. if it is, adds it to this tray's stack of
		 * moves. Returns true or false depending if it works or not. This
		 * return boolean is not used, but it may be useful in the future?
		 * 
		 * @param dir
		 *            The direction of the move. R is 0, D is 1, L is 2, U is 3.
		 * @return
		 */
		public boolean checkMove(byte dir, Block b) {
			if (dir == 0) { // the case for it going right. I'll only document
							// this case, the others are pretty similar.
				if (b.x2 + 1 >= this.myWidth) { // if it's at the right edge of
												// the board, it'll move off.
												// this is bad.
					return false;
				}
				String msg = Short.toString(b.y1)
						+ " " // creates the move message. It works. Think
								// through why?
						+ Short.toString(b.x1) + " " + Short.toString(b.y1)
						+ " " + Integer.toString(b.x1 + 1);
				ArrayList<Block> newBlocks = new ArrayList<Block>(); // copies
																		// the
																		// blocks
																		// set.
				Block newB = new Block(b, dir); // creates a new moved
												// block
				if (Tray.overlaps(newB, this.myBlocks, dir)) {
					return false;
				}
				newBlocks.addAll(this.myBlocks); // copies over each block
				newBlocks.add(newB); // adds new moved block
				newBlocks.remove(b); // removes old unmoved block
				this.nextMove = (new Tray(this, newBlocks, msg));
			} else if (dir == 1) {
				if (b.y2 + 1 >= this.myHeight) {
					return false;
				}
				String msg = Short.toString(b.y1) + " " + Short.toString(b.x1)
						+ " " + Integer.toString(b.y1 + 1) + " "
						+ Short.toString(b.x1);
				ArrayList<Block> newBlocks = new ArrayList<Block>();
				Block newB = new Block(b, dir);
				if (Tray.overlaps(newB, this.myBlocks, dir)) {
					return false;
				}
				newBlocks.addAll(this.myBlocks);
				newBlocks.add(newB);
				newBlocks.remove(b);
				this.nextMove = (new Tray(this, newBlocks, msg));
			} else if (dir == 2) {
				if (b.x1 - 1 < 0) {
					return false;
				}
				String msg = Short.toString(b.y1) + " " + Short.toString(b.x1)
						+ " " + Short.toString(b.y1) + " "
						+ Integer.toString(b.x1 - 1);
				ArrayList<Block> newBlocks = new ArrayList<Block>();
				Block newB = new Block(b, dir);
				if (Tray.overlaps(newB, this.myBlocks, dir)) {
					return false;
				}
				newBlocks.addAll(this.myBlocks);
				newBlocks.add(newB);
				newBlocks.remove(b);
				this.nextMove = (new Tray(this, newBlocks, msg));
			} else if (dir == 3) {
				if (b.y1 - 1 < 0) {
					return false;
				}
				String msg = Short.toString(b.y1) + " " + Short.toString(b.x1)
						+ " " + Integer.toString(b.y1 - 1) + " "
						+ Short.toString(b.x1);
				ArrayList<Block> newBlocks = new ArrayList<Block>();
				Block newB = new Block(b, dir);
				if (Tray.overlaps(newB, this.myBlocks, dir)) {
					return false;
				}
				newBlocks.addAll(this.myBlocks);
				newBlocks.add(newB);
				newBlocks.remove(b);
				this.nextMove = (new Tray(this, newBlocks, msg));
			}
			return true; // it worked!
		}

		/**
		 * Returns whether this is a winning state or not.
		 * 
		 * @return Returns true if all blocks in goal are in the tray
		 *         configuration, and false otherwise.
		 */
		public boolean won() {
			return this.myBlocks.containsAll(this.myGoal);
		}

		public class MoveIterator {
			byte direction;
			Iterator<Block> blocks;
			Block b;

			public MoveIterator(ArrayList<Block> blocks) {
				this.direction = 0;
				this.blocks = blocks.iterator();
				if (this.blocks.hasNext()) {
					this.b = this.blocks.next();
				}
			}

			public void next() {
				if (b == null) {
					nextMove = null;
					return;
				}
				while (!checkMove(direction, b)) {
					direction++;
					if (direction >= 4) {
						if (blocks.hasNext()) {
							b = blocks.next();
						} else {
							b = null;
							nextMove = null;
							return;
						}
						direction = 0;
					}
				}
				direction++;
				if (direction >= 4) {
					if (blocks.hasNext()) {
						b = blocks.next();
					} else {
						b = null;
						return;
					}
					direction = 0;
				}
			}
		}

		/**
		 * Creates a boolean matrix of points based on a bunch of blocks. Fills
		 * in all the points of a block, rather than just the top left and bot
		 * right. just uses a simple double for loop per block.
		 * 
		 * @param blocks
		 * @return
		 * @throws IllegalArgumentException
		 */
		public void setMaker(ArrayList<Block> blocks)
				throws IllegalArgumentException {
			boolean[][] mySet = new boolean[myHeight][myWidth];
			for (Block b : blocks) {
				for (int y = b.y1; y <= b.y2; y++) {
					for (int x = b.x1; x <= b.x2; x++) {
						if (mySet[y][x]) {
							throw new IllegalArgumentException();
						} else {
							mySet[y][x] = true;
						}
					}
				}
			}
		}

		public int code() {
			int rtn = 0;
			for (Block b : this.myBlocks) {
				rtn = rtn * 101 + b.hashCode(); // Make this faster.
			}
			return rtn;
		}

		/**
		 * 
		 */
		@Override
		public int hashCode() {
			if (this.code == -1) {
				this.code = this.code();
			}
			return this.code;
		}

		/**
		 * 
		 */
		@Override
		public boolean equals(Object o) {
			return ((Tray) o).hashCode() == this.hashCode();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
		ArrayList<Short> tray = new ArrayList<Short>(); // creates two array
														// lists for the scanned
														// info
		Scanner fileScanner; // creates the scanner
		ArrayList<Short> goal = new ArrayList<Short>();
		try { // scans and fills the arraylists, then creates the initial
				// configs.
			fileScanner = new Scanner(new File(args[0]));
			while (fileScanner.hasNext()) {
				tray.add(Short.parseShort(fileScanner.next()));
			}
			fileScanner = new Scanner(new File(args[1]));
			while (fileScanner.hasNext()) {
				goal.add(Short.parseShort(fileScanner.next()));
			}
			fileScanner.close();
			short height = tray.get(0);
			short width = tray.get(1);
			if (goal.size() % 4 != 0 || tray.size() % 4 != 2 || height < 1
					|| width < 1) {
				throw new IllegalArgumentException();
			} else {
				ArrayList<Block> blocks = new ArrayList<Block>();
				for (int k = 2; k < tray.size(); k += 4) {
					short y1 = tray.get(k);
					short x1 = tray.get(k + 1);
					short y2 = tray.get(k + 2);
					short x2 = tray.get(k + 3);
					if (x1 < 0 || x1 > width || x2 < 0 || x2 > width || y1 < 0
							|| y1 > height || y2 < 0 || y2 > height) {
						throw new IllegalArgumentException();
					}
					Block b = new Block(y1, x1, y2, x2);
					blocks.add(b);
				}
				ArrayList<Block> win = new ArrayList<Block>();
				for (int k = 0; k < goal.size(); k += 4) {
					short y1 = goal.get(k);
					short x1 = goal.get(k + 1);
					short y2 = goal.get(k + 2);
					short x2 = goal.get(k + 3);
					if (x1 < 0 || x1 > width || x2 < 0 || x2 > width || y1 < 0
							|| y1 > height || y2 < 0 || y2 > height) {
						throw new IllegalArgumentException();
					}
					Block b = new Block(y1, x1, y2, x2);
					win.add(b);
				}
				Collections.sort(win);
				Tray startPos = new Tray(height, width, blocks, win);
				Solver gogogo = new Solver(startPos);
				gogogo.solve();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (NumberFormatException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			return;
		}
	}
}
