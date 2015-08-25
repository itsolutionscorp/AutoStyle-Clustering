
public class Piece {

	private PointPlus topLeft;
	private PointPlus bottomRight;
	private int xDim; // top to bottom
	private int yDim; // left to right

	public Piece(int x1, int y1, int x2, int y2) {
		topLeft = new PointPlus(x1, y1);
		bottomRight = new PointPlus(x2, y2);
		xDim = 1 + (x2 - x1);
		yDim = 1 + (y2 - y1);
	}

	public Piece() {
		this(512, 512, 512, 512);
	}

	public PointPlus getTopLeft() {
		return topLeft;
	}

	public PointPlus getTopRight() {
		return new PointPlus(topLeft.X(), bottomRight.Y());
	}

	public PointPlus getBottomRight() {
		return bottomRight;
	}

	public PointPlus getBottomLeft() {
		return new PointPlus(bottomRight.X(), topLeft.Y());
	}

	public int getXDim() {
		return xDim;
	}

	public int getYDim() {
		return yDim;
	}

	@Override
	public int hashCode() {
		return ((topLeft.X() * -51) - (bottomRight.Y() * 97))
				+ ((topLeft.Y() * 43) - (bottomRight.X() * 37));
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else {
			try {
				Piece other = (Piece) object;
				return topLeft.equals(other.getTopLeft())
						&& bottomRight.equals(other.getBottomRight());
			} catch (ClassCastException e) {
				return false;
			}
		}
	}

	@Override
	public String toString() {
		return topLeft.toString() + "" + bottomRight.toString();
	}

}
