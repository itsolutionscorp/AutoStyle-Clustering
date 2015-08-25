public class Block {

	short x1, y1, x2, y2;
	short height, width;

	public Block(int x1, int y1, int x2, int y2){
		this.x1 = (short) x1;
		this.y1 = (short) y1;
		this.x2 = (short) x2;
		this.y2 = (short) y2;
		this.height = (short) (x2 - x1 + 1);
		this.width = (short) (y2 - y1 + 1);
	}
	
	public Block(Block blk, Move move) {
		this.x1 = (short) (blk.x1 + move.dx);
		this.y1 = (short) (blk.y1 + move.dy);
		this.x2 = (short) (blk.x2 + move.dx);
		this.y2 = (short) (blk.y2 + move.dy);
		this.height = (short) (x2 - x1 + 1);
		this.width = (short) (y2 - y1 + 1);
	}


	@Override
	public String toString(){
		return "(" + x1 + ", " + y1 + ") (" + x2 + ", " + y2 + ")";
	}
	
	@Override
	public int hashCode() {
		return ((((17 * 19 + x1) * 23 + y1) * 29 + x2) * 37 + y2);
	}

	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
}
