import java.awt.Point;

public class PointPlus extends Point {

	private static final long serialVersionUID = 1L;

	public PointPlus(int x, int y) {
		super(x, y);
	}

	public int X() {
		return (int) super.getX();
	}

	public int Y() {
		return (int) super.getY();
	}

	@Override
	public int hashCode() {
		return (53 * (X() - Y()) + (43 * (Y() - X())));
	}

	@Override
	public String toString() {
		return X() + "" + Y();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else {
			try {
				PointPlus other = (PointPlus) object;
				return (X() == other.X()) && (Y() == other.Y());
			} catch (ClassCastException e) {
				return false;
			}
		}
	}
}
