import java.util.*;
import java.awt.Point;

/**
 * The tray class represents a possible configuration of the sliding block
 * puzzle. All the possible trays will be represented as a graph. The sliding
 * block solver uses a best-first traversal of all the possible tray
 * configurations in order to solve the puzzles.
 * 
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 * @date August 11, 2015
 *
 */
public class Tray implements Comparable<Tray> {
	int distance; // metric to compare trays
	Block move; // move associated with this tray
	Tray preTray;
	Block[][] tray;
	HashSet<Block> blocks;
	HashSet<Point> emptySpace;
	HashSet<String> tried;

	/**
	 * Constructor to make a new tray. Sets everything to null.
	 */
	public Tray() {
		distance = Integer.MAX_VALUE;
		tray = new Block[Solver.HEIGHT][Solver.WIDTH];
		blocks = new HashSet<Block>();
		emptySpace = new HashSet<Point>();
		tried = new HashSet<String>();
		move = null;
		preTray = null;
		for (int i = 0; i < Solver.HEIGHT; i++) {
			for (int j = 0; j < Solver.WIDTH; j++) {
				addEmptySpace(i, j);
			}
		}
	}

	/**
	 * Makes a new tray that is a copy of tray t.
	 * 
	 * @param t
	 *            Tray to be copied.
	 */
	public Tray(Tray t) {
		distance = t.distance;
		tray = new Block[Solver.HEIGHT][Solver.WIDTH];
		blocks = new HashSet<Block>(t.blocks);
		emptySpace = new HashSet<Point>(t.emptySpace);
		tried = new HashSet<String>();
		move = null;
		preTray = t;
		for (int i = 0; i < Solver.HEIGHT; i++) {
			for (int j = 0; j < Solver.WIDTH; j++) {
				if (t.tray[i][j] == null) {
					tray[i][j] = null;
				} else {
					tray[i][j] = t.tray[i][j];
				}
			}
		}
	}

	/**
	 * Adds a new block and updates the data structures appropriately.
	 * 
	 * @param x1
	 *            Row of top-left point.
	 * @param y1
	 *            Column of top-left point.
	 * @param x2
	 *            Row of bottom-right point.
	 * @param y2
	 *            Column of bottom-right point.
	 * @return The new block that was added.
	 * @throws IllegalArgumentException
	 */
	public Block addBlock(int x1, int y1, int x2, int y2)
			throws IllegalArgumentException {
		if (!Solver.inBoundary(x1, y1) || !Solver.inBoundary(x2, y2)) {
			throw new IllegalArgumentException();
		}
		Block b;
		String s = x1 + " " + y1 + " " + x2 + " " + y2;
		if (Solver.allBlocks.containsKey(s)) {
			b = Solver.allBlocks.get(s);
		} else {
			b = new Block(x1, y1, x2, y2);
			Solver.allBlocks.put(s, b);
		}
		for (int i = b.p1.x; i <= b.p2.x; i++) {
			for (int j = b.p1.y; j <= b.p2.y; j++) {
				if (tray[i][j] != null) {
					throw new IllegalArgumentException();
				}
				emptySpace.remove(new Point(i, j));
				tray[i][j] = b;
			}
		}
		blocks.add(b);
		return b;
	}

	/**
	 * Determines if the block at x0 and y0 can move in the direction given.
	 * 
	 * @param x0
	 *            Row of the block.
	 * @param y0
	 *            Column of the block.
	 * @param direction
	 *            Direction as given in Solver class.
	 * @return True if the block can move in that direction. False otherwise.
	 */
	public boolean canMove(int x0, int y0, int direction) {
		if (!Solver.inBoundary(x0, y0) || tray[x0][y0] == null || direction > 3
				|| tried.contains(tray[x0][y0].toString() + "-" + direction)) {
			return false;
		} else {
			int x1;
			int y1;
			Block b = tray[x0][y0];
			tried.add(b.toString() + "-" + direction);
			switch (direction) {
			case 0:
				x1 = b.p1.x - 1;
				for (int i = b.p1.y; i <= b.p2.y; i++) {
					if (!Solver.inBoundary(x1, i)
							|| !emptySpace.contains(new Point(x1, i))) {
						return false;
					}
				}
				break;
			case 3:
				x1 = b.p2.x + 1;
				for (int i = b.p1.y; i <= b.p2.y; i++) {
					if (!Solver.inBoundary(x1, i)
							|| !emptySpace.contains(new Point(x1, i))) {
						return false;
					}
				}
				break;
			case 1:
				y1 = b.p1.y - 1;
				for (int i = b.p1.x; i <= b.p2.x; i++) {
					if (!Solver.inBoundary(i, y1)
							|| !emptySpace.contains(new Point(i, y1))) {
						return false;
					}
				}
				break;
			case 2:
				y1 = b.p2.y + 1;
				for (int i = b.p1.x; i <= b.p2.x; i++) {
					if (!Solver.inBoundary(i, y1)
							|| !emptySpace.contains(new Point(i, y1))) {
						return false;
					}
				}
				break;
			default:
				break;
			}
			return true;
		}
	}

	/**
	 * Adds an empty space and updates emptySpace.
	 * 
	 * @param x
	 *            Row to add empty space at.
	 * @param y
	 *            Column to add empty space at.
	 */
	public void addEmptySpace(int x, int y) {
		Point p = new Point(x, y);
		emptySpace.add(p);
	}

	/**
	 * Removes the block at x0 and y0 then updates data structures.
	 * 
	 * @param x0
	 *            Row to remove block from.
	 * @param y0
	 *            Column to remove block from.
	 */
	public void removeBlock(int x0, int y0) {
		Block b = tray[x0][y0];
		for (int i = b.p1.x; i <= b.p2.x; i++) {
			for (int j = b.p1.y; j <= b.p2.y; j++) {
				addEmptySpace(i, j);
				tray[i][j] = null;
			}
		}
		blocks.remove(b);
	}

	/**
	 * Moves the block at x0 and y0 in the given direction.
	 * 
	 * @param x0
	 *            Row of the block.
	 * @param y0
	 *            Column of the block.
	 * @param direction
	 *            Direction as given in Solver class.
	 * @precondition canMove(x0,y0,direction) must be true.
	 */
	public void move(int x0, int y0, int direction) {
		Block b = tray[x0][y0];
		int dx = Solver.DIRECTION[direction][0];
		int dy = Solver.DIRECTION[direction][1];
		int x1 = b.p1.x + dx;
		int y1 = b.p1.y + dy;
		int x2 = b.p2.x + dx;
		int y2 = b.p2.y + dy;
		removeBlock(x0, y0);
		addBlock(x1, y1, x2, y2);
		move = new Block(b.p1.x, b.p1.y, x1, y1);
		if (!Solver.allBlocks.containsKey(move.toString())) {
			Solver.allBlocks.put(move.toString(), move);
		} else {
			move = Solver.allBlocks.get(move.toString());
		}
		if (b.shape().equals(Solver.biggestGoal.shape())) {
			distance = Solver.HEIGHT + Solver.WIDTH;
			for (Block i : blocks) {
				if (i.shape().equals(Solver.biggestGoal.shape())) {
					distance = Math.min(Solver.biggestGoal.distance(i),
							distance);
				}
			}
		}
	}

	/**
	 * Heuristics to determine which tray should go first in the priority queue.
	 * 
	 * @param o
	 *            Other tray to compare to.
	 * @return -1 if this move is better, 0 if it doesn't matter, and 1
	 *         otherwise.
	 */
	public int compareTo(Tray o) {
		if (o == null) {
			return 1;
		} else if (o.distance != distance) {
			return distance - o.distance;
		}
		Block[][] t = o.tray;
		for (int i = 0; i < Solver.HEIGHT; i++) {
			for (int j = 0; j < Solver.WIDTH; j++) {
				if (tray[i][j] == null && t[i][j] == null) {
					continue;
				} else if (tray[i][j] == null && t[i][j] != null) {
					return -1;
				} else if (tray[i][j] != null && t[i][j] == null) {
					return 1;
				} else if (!(tray[i][j].equals(t[i][j]))) {
					return tray[i][j].compareTo(t[i][j]);
				}
			}
		}
		return 0;
	}

	/**
	 * Returns the hash code of this tray as the sum of the hash code of its
	 * blocks.
	 */
	@Override
	public int hashCode() {
		return blocks.hashCode();
	}

	/**
	 * Determines if two sets are equal by comparing their sets.
	 * 
	 * @param otherTray
	 * @return True if the sets have the same elements.
	 */
	@Override
	public boolean equals(Object otherTray) {
		if (otherTray instanceof Tray) {
			Tray other = (Tray) otherTray;
			return blocks.equals(other.blocks);
		}
		return false;
	}
}
