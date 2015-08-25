class SquareBlock {
	int fX, fY, sX, sY;
	boolean goUp, goDown, goLeft, goRight;
	int hashCode;

	public SquareBlock(int x, int y, int x2, int y2) {
		fX = x;
		fY = y;
		sX = x2;
		sY = y2;
		hashCode = (fX + "" + sX + " " + fY + "" + sY).hashCode();
	}
	public int hashCode() {
		return hashCode;
	}
	

	public boolean equals(Object obj) {
		return obj.hashCode() == this.hashCode;
	}
}