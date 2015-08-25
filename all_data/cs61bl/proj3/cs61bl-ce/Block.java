public class Block {
	private int upperLeftX;
	private int upperLeftY;
	private int lowerRightX;
	private int lowerRightY;
	private int myWidth;
	private int myHeight;

	/**
	 * The block constructor takes in 4 ints that represent the upper left and lower right coordinates
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public Block(int a, int b, int c, int d) {
		upperLeftX = a;
		upperLeftY = b;
		lowerRightX = c;
		lowerRightY = d;
		myHeight = d - b + 1;
		myWidth = c - a + 1;
	}

	public int width() {
		return myWidth;
	}

	public int height() {
		return myHeight;
	}

	public int upperLeftX() {
		return upperLeftX;
	}

	public int upperLeftY() {
		return upperLeftY;
	}

	public int lowerRightX() {
		return lowerRightX;
	}

	public int lowerRightY() {
		return lowerRightY;
	}

	/**
	 * Changes the coordinates of the block location based on the location of
	 * the new upper left coordinate of the block
	 * @param newLeftX
	 * @param newLeftY
	 */
	public void changeCoordinates(int newLeftX, int newLeftY) {
		upperLeftX = newLeftX;
		upperLeftY = newLeftY;
		lowerRightX = newLeftX + myWidth - 1;
		lowerRightY = newLeftY + myHeight - 1;
	}

	/**
	 * A Block is equal to another block if the coordinate of its upper left corner
	 * is the same, it has the same height and width, and that both of the blocks 
	 * are either empty or not
	 * @param otherBlock
	 * @return
	 */
	@Override
	public boolean equals(Object otherBlock) {
		if (otherBlock instanceof Block) {
			if (((Block) otherBlock).upperLeftX == this.upperLeftX && ((Block) otherBlock).upperLeftY == this.upperLeftY) {
				if (((Block) otherBlock).height() == this.myHeight && ((Block) otherBlock).width() == this.myWidth) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	/**
	 * Overrides hashcode method
	 */
	@Override 
	public int hashCode() {
		int result = 0;
		result += upperLeftX * 7;
		result += upperLeftY * 11;
		result += lowerRightX * 5;
		result += lowerRightY * 3;
		return result;
	}

	/**
	 * Overrides toString method using stringbuilder for runspeed
	 */
	@Override
	public String toString() {
		StringBuilder results = new StringBuilder("");
		results.append(upperLeftX);
		results.append(" ");
		results.append(upperLeftY);
		results.append(" ");
		results.append(lowerRightX);
		results.append(" ");
		results.append(lowerRightY);
		return results.toString();
	}

}