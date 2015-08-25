public class Piece {
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int high;
	public int wide;
	public int storePiece;

	public Piece(int x1, int y1, int x2, int y2, int n) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.high = y2 - y1 + 1;
		this.wide = x2 - x1 + 1;
		this.storePiece = x1 + y1 * n + x2 * n * n + y2 * n * n * n;
	}

	public int size() {
		return this.high * this.wide;
	}

	public boolean isSquare() {
		return high == wide;
	}

	public boolean isSame(Piece p) {
		return this.high == p.high && this.wide == p.high;
	}

	@Override
	public boolean equals(Object p) {
		return this.x1 == ((Piece) p).x1 && this.y1 == ((Piece) p).y1
				&& this.x2 == ((Piece) p).x2 && this.y2 == ((Piece) p).y2;
	}

	@Override
	public int hashCode() {
		return storePiece;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(storePiece).toString();
	}

}
