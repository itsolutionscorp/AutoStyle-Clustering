public class Piece {
	private int x1, y1;
	private int width, height;
	private int id;

	public Piece(int id, int x1, int y1, int x2, int y2) {
		this.id = id;
		this.x1 = x1;

		this.y1 = y1;

		width = x2 - x1 + 1;
		height = y2 - y1 + 1;

	}

	/**
	 * @return x1
	 */
	public int x1() {
		return x1;
	}

	/**
	 * @return x2
	 */
	public int x2() {
		return x1 + width - 1;
	}

	/**
	 * @return y1
	 */
	public int y1() {
		return y1;
	}

	/**
	 * @return y2
	 */
	public int y2() {
		return y1 + height - 1;
	}

	/**
	 * @return The coordinates representing the top left corner of this piece
	 */
	public int[] myTopLeft() {
		return new int[] { x1, y1 };
	}

	/**
	 * This piece's width.
	 * 
	 * @return Width.
	 */
	public int myWidth() {
		return width;
	}

	/**
	 * This piece's height.
	 * 
	 * @return height
	 */
	public int myHeight() {
		return height;
	}

	/**
	 * Getter for this piece's ID
	 * 
	 * @return id
	 */
	public int getID() {
		return id;
	}

	/**
	 * Returns piece's topLeft and bottomRight xCoordinates
	 * 
	 * @return An array of this piece's topLeft and bottomRight (X,Y)
	 *         coordinates
	 */
	public int[] getCoordinates() {
		return new int[] { x1, y1 };
	}

	/**
	 * Updates the coordinates for this piece. Called upon a move.
	 * 
	 * @param newX
	 *            The new X
	 * @param newY
	 *            The new Y
	 */
	public void updateCoords(int newX, int newY) {
		x1 = newX;
		y1 = newY;
	}

	/**
	 * Generates a hashCode. Uses the pieces size and position.
	 * 
	 * @return This piece's hashCode.
	 */
	public int hashCode() {
		int pieceWidth = myWidth();
		int pieceHeight = myHeight();

		return (41 * (x1 + 1) + 37 * (y1 + 1)) + 131
				* (pieceWidth + pieceHeight) + 167;
	}

	/**
	 * Creates a complete copy of this piece.
	 * 
	 * @return The new copy
	 */
	public Piece copyPiece() {
		return new Piece(id, x1, y1, x2(), y2());
	}

	/**
	 * The toString representation for this object
	 * 
	 * @return toString rep
	 */
	public String toString() {

		return new String("[id: " + id + " (" + x1 + ", " + y1 + ") " + "("
				+ x2() + ", " + y2() + ")]");
	}

	/**
	 * Given an input piece, returns whether or not the two pieces are equal
	 * 
	 * @param p
	 *            The input piece
	 * @return Are they equal?
	 */
	public boolean equals(Piece p) {
		if (p == null) {
			return false;
		}
		return p.x1 == x1 && p.y1 == y1 && p.width == width
				&& p.height == height;
	}
}
