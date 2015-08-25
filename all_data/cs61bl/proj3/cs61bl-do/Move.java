public class Move {
	Block blk;
	short dx, dy;

	public Move(Block blk, int i, int j) {
		this.blk = blk;
		this.dx = (short) i;
		this.dy = (short) j;
	}

	public String toString() {
		return "" + blk.x1 + " " + blk.y1 + " " + (int) (blk.x1 + dx) + " " + (int) (blk.y1 + dy);
	}
}
