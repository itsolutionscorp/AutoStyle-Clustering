import java.awt.Point;

public class Block {
	private Point myTopLeft;
	private Point myBotRight;
	private int myHeight;
	private int myWidth;
	private int myShape;

	public Block(int TLX, int TLY, int BRX, int BRY) {
		myTopLeft = new Point(TLX, TLY);
		myBotRight = new Point(BRX, BRY);
		if (!isOK()) {
			throw new IllegalStateException();
		}
		myHeight = Math.abs(TLX - BRX) + 1;
		myWidth = Math.abs(TLY - BRY) + 1;
		myShape = 256 * myHeight + myWidth;
	}

	@Override
	public int hashCode() {
		int rv = myTopLeft.x;
		rv = 10 * rv + myTopLeft.y;
		rv = 10 * rv + myBotRight.x;
		rv = 10 * rv + myBotRight.y;
		if (myWidth > myHeight) {
			rv = -rv;
		}
		return rv;
	}

	public int shape() {
		return myShape;
	}

	public int getHeight() {
		return myHeight;
	}

	public int getWidth() {
		return myWidth;
	}

	public Point getTopLeft() {
		return myTopLeft;
	}

	public Point getBotRight() {
		return myBotRight;
	}

	private boolean isOK() {
		if (getTopLeft().x > getBotRight().x
				|| getTopLeft().y > getBotRight().y || getTopLeft().x < 0
				|| getTopLeft().y < 0 || getBotRight().x < 0
				|| getBotRight().y < 0) {
			return false;
		}
		return true;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this == o) {
			return true;
		} else {
			Block other = (Block) o;
			return myTopLeft.equals(other.myTopLeft)
					&& myBotRight.equals(other.myBotRight);
		}
	}
}
