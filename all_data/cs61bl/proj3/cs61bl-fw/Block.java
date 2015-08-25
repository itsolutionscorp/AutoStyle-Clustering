public class Block implements Comparable, Cloneable {
	protected int topLeftY;
	protected int topLeftX;
	protected int bottomRightY;
	protected int bottomRightX;
		
	public Block(int ly, int lx, int by, int bx) {
		this.topLeftY = ly;
		this.topLeftX = lx;
		this.bottomRightY = by;
		this.bottomRightX = bx;
	}
	
	public Block clone() {
		int ly = this.topLeftY;
		int lx = this.topLeftX;
		int by = this.bottomRightY;
		int bx = this.bottomRightX;
		return new Block(ly, lx, by, bx);
	}
	
	public Block slide(int dx, int dy) {
		Block clone = clone();
		
		clone.topLeftX += dx;
		clone.topLeftY += dy;
		clone.bottomRightX += dx;
		clone.bottomRightY += dy;

		return clone;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Block)) {
			return false;
		} else {
			if (((Block)o).topLeftX == this.topLeftX
					&& ((Block)o).topLeftY == this.topLeftY
					&& ((Block)o).bottomRightY == this.bottomRightY 
					&& ((Block)o).bottomRightX == this.bottomRightX) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	@Override
	public int compareTo(Object arg0) {
		Block comp = (Block)arg0;
		return this.topLeftX != comp.topLeftX ? topLeftX - comp.topLeftX : topLeftY - comp.topLeftY;
	}

}
