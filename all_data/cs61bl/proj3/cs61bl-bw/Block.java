public class Block { // @Jennifer
	private int myID;
	private int x1; // upper left row.
	private int y1; // upper left col.
	private int x2; // lower right row.
	private int y2; // lower right grecol.
	private boolean[] possibleMoves;

	public Block(int ID, int x1, int y1, int x2, int y2) { // @Jennifer
		myID = ID;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		possibleMoves = new boolean[] { true, true, true, true };
	}

	/*
	 * direction corresponds to the index of possibleMoves 0 = North 1 = South 2
	 * = East 3 = West
	 * 
	 * @param direction
	 */
	public void setDirFalse(int direction) {
		possibleMoves[direction] = false;

	}

	public boolean[] getDirections() {
		return possibleMoves;
	}

	/*
	 * returns an int[] of size 4, storing the location of the block. first two
	 * indexes are the coordinates of upperLeft last two indexes are the
	 * coordinates of lowerRight
	 */
	public int[] getLocation() { // @Jennifer
		int[] rtn = new int[4];
		rtn[0] = x1;
		rtn[1] = y1;
		rtn[2] = x2;
		rtn[3] = y2;
		return rtn;
	}

	public int getID() {
		return myID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x1;
		result = prime * result + x2;
		result = prime * result + y1;
		result = prime * result + y2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Block other = (Block) obj;
		if (x1 != other.x1)
			return false;
		if (x2 != other.x2)
			return false;
		if (y1 != other.y1)
			return false;
		if (y2 != other.y2)
			return false;
		return true;
	}

}
