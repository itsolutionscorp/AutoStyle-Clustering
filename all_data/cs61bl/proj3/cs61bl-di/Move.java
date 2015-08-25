public class Move {
	private int preX, preY, postX, postY;

	public Move(int preX, int preY, int postX, int postY) {
		// check for validity
		this.preX = preX;
		this.preY = preY;
		this.postX = postX;
		this.postY = postY;
	}

	/**
	 * @return The toString representation for this move.
	 */
	public String toString() {
		return new String("{(" + preX + ", " + preY + ") -> (" + postX + ", "
				+ postY + ")}");
	}

	/**
	 * Returns X coordinate before move
	 * 
	 * @return updated X
	 */
	public int getPreX() {
		return preX;
	}

	/**
	 * Returns Y coordinate before move
	 * 
	 * @return updated Y
	 */
	public int getPreY() {
		return preY;
	}

	/**
	 * Returns X coordinate after move
	 * 
	 * @return updated X
	 */
	public int getPostX() {
		return postX;
	}

	/**
	 * Returns Y coordinate after move
	 * 
	 * @return updated Y
	 */
	public int getPostY() {
		return postY;
	}

	/**
	 * Returns a new array containing the pre-coords
	 * 
	 * @return pre-coords
	 */
	public int[] getPreCoords() {
		return new int[] { preX, preY };
	}

	/**
	 * Returns the "post-cords" of the move.
	 * 
	 * @return Post coords of the move.
	 */
	public int[] getPostCoords() {
		return new int[] { postX, postY };
	}

	/**
	 * Flip of axes
	 * 
	 * @return toString for this move with a flip of axes included
	 */
	public String print() {
		return preY + " " + preX + " " + postY + " " + postX;
	}

	/**
	 * Normal toString
	 * 
	 * @return The normal toString
	 */
	public String printX() {
		return preX + " " + preY + " " + postX + " " + postY;
	}
}
