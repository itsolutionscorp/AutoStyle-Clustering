import java.awt.Point;

/**
 * The block class represents a single block in the sliding block puzzle. Its
 * coordinates are given using the top-left space and the bottom-right space.
 * 
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 * @date August 11, 2015
 *
 */
public class Block implements Comparable<Block> {
	Point p1; // top-left point of the block
	Point p2; // bottom-right point of the block
	int hashCode; // saved hashCode to avoid recalculating

	/**
	 * Initializes a new Block with the given coordinates.
	 * 
	 * @param x1
	 *            Row of top-left point.
	 * @param y1
	 *            Column of top-left point.
	 * @param x2
	 *            Row of bottom-right point.
	 * @param y2
	 *            Column of bottom-right point.
	 */
	public Block(int x1, int y1, int x2, int y2) {
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
		String s = (p1.x * 11) + " " + (p1.y * 13) + " " + (p2.x * 17) + " "
				+ (p2.y * 19);
		hashCode = s.hashCode();
	}

	/**
	 * Initializes a new block as a copy of the given block.
	 * 
	 * @param b
	 *            Block to be copied.
	 */
	public Block(Block b) {
		this(b.p1.x, b.p1.y, b.p2.x, b.p2.y);
	}

	/**
	 * Static helper methods that returns the difference between two points.
	 * 
	 * @param a
	 *            First point.
	 * @param b
	 *            Point to compare to.
	 * @return -1 if it comes before. 0 if its the same point. 1 otherwise.
	 */
	public static int compareTo(Point a, Point b) {
		if (a.x != b.x) {
			return a.x - b.x;
		} else {
			return a.y - b.y;
		}
	}

	/**
	 * String representation of a block given in x1 y1 x2 y2 format.
	 */
	@Override
	public String toString() {
		return p1.x + " " + p1.y + " " + p2.x + " " + p2.y;
	}

	/**
	 * Determines if two Blocks are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Block)) {
			return false;
		}
		Block b = (Block) obj;
		return p1.equals(b.p1) && p2.equals(b.p2);
	}

	/**
	 * Returns the shape of the block represented by a point.
	 * 
	 * @return Point that represents the block shape. (x is height. y is width)
	 */
	public Point shape() {
		return new Point(p2.x - p1.x + 1, p2.y - p1.y + 1);
	}

	/**
	 * Returns the size of a block given by the differences between the
	 * bottom-right and the top-left coordinate.
	 * 
	 * @return int that is the size of the block.
	 */
	public int size() {
		return p2.x - p1.x + p2.y - p1.y;
	}

	/**
	 * Compares two blocks to which is greater.
	 */
	@Override
	public int compareTo(Block o) {
		if (size() == o.size()) {
			Point p = shape();
			Point op = o.shape();
			if (Math.abs(p.x - p.y) < Math.abs(op.x - op.y)) {
				return 1;
			} else if (Math.abs(p.x - p.y) > Math.abs(op.x - op.y)) {
				return -1;
			} else if (compareTo(p1, o.p1) != 0) {
				return compareTo(p1, o.p1);
			} else {
				return compareTo(p2, o.p2);
			}
		} else {
			return size() - o.size();
		}
	}

	/**
	 * Returns the distance between two blocks if the size matches.
	 * 
	 * @param b
	 *            Block to get distance to.
	 * @return The distance between two blocks if they are the same size. Max
	 *         distance otherwise.
	 */
	public int distance(Block b) {
		if (shape().equals(b.shape())) {
			return Math.abs(b.p1.x - p1.x) + Math.abs(b.p1.y - p1.y);
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * Return the hash code of this block.
	 */
	@Override
	public int hashCode() {
		return hashCode;
	}

}
