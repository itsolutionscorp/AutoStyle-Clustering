import java.awt.Point;

public class Block {

	private Point tLEFT;
	private Point bRIGHT;
	private String repr;
	short height;
	short width;

	public Block(short tleftX, short tleftY, short brightX, short brightY) throws Exception {
		if (tleftX < 0 || tleftY < 0 || brightX < 0 || brightY < 0) {
			throw new Exception();
		}
		tLEFT = new Point(tleftX, tleftY);
		bRIGHT = new Point(brightX, brightY);
		height = (short) (brightX - tleftX);
		width = (short) (brightY - tleftY);
	}

	public Point getTopLeft() {
		return tLEFT;
	}

	public Point getBottomRight() {
		return bRIGHT;
	}

	public void changeTopLeft(Point p) {
		tLEFT = p;
	}

	public void changeBottomRight(Point p) {
		bRIGHT = p;
	}

	@Override
	public String toString() {
		repr = "Block at: " + (short) this.getTopLeft().getX() + " " + (short) this.getTopLeft().getY() + " "
				+ (short) this.getBottomRight().getX() + " " + (short) this.getBottomRight().getY();
		return repr;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (((Block) o).getTopLeft().x == this.getTopLeft().x && ((Block) o).getTopLeft().y == this.getTopLeft().y
				&& ((Block) o).getBottomRight().x == this.getBottomRight().x
				&& ((Block) o).getBottomRight().y == this.getBottomRight().y) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 11 + (int) this.getTopLeft().getX();
		hash = hash * 11 + (int) this.getTopLeft().getY();
		hash = hash * 11 + (int) this.getBottomRight().getX();
		hash = hash * 11 + (int) this.getBottomRight().getY();
		return hash;
	}

}
