import java.awt.Point;
import java.util.HashMap;

public class Block {

	Point myTopLeft, myBottomRight;
	int myWidth;
	int myHeight;
	HashMap<Integer, String> moves;
	
	public Block(int tlX, int tlY, int brX, int brY) {
		myTopLeft = new Point(tlX, tlY);
		myBottomRight = new Point(brX, brY);
		myWidth = Math.abs(tlX - brX);
		myHeight = Math.abs(tlY - brY);
		moves = new HashMap<Integer,String>();
	}
	
	public Block(Block b) {
		myTopLeft = b.topLeft();
		myBottomRight = b.bottomRight();
		myWidth = b.width();
		myHeight = b.height();
		moves = new HashMap<Integer,String>();
	}

	public Point topLeft() {
		return myTopLeft;
	}

	public Point bottomRight() {
		return myBottomRight;
	}

	public int height() {
		return myHeight;
	}

	public int width() {
		return myWidth;
	}

  public boolean almostEquals(Object o) {
    Block b = (Block) o;
    return (myWidth == b.width() && myHeight == b.height());
  }
  
	public boolean equals(Object o) {
		Block b = (Block) o;
		return (myTopLeft.equals(b.topLeft())
				&& myBottomRight.equals(b.bottomRight())
				&& (myWidth == b.width()) && (myHeight == b.height()));
	}

	public int hashCode() {
		int h = 7;
		h = (int) (13 * h + myTopLeft.getX()*myBottomRight.getX());
		h = (int) (67 * h + myTopLeft.getY()*myBottomRight.getY());
		h = (int) (19 * h + myBottomRight.getY()*myTopLeft.getX());
		h = (int) (11 * h + myBottomRight.getX()*myTopLeft.getY());
		h = 29 * h + myBottomRight.hashCode();
		h = 47 * h + myTopLeft.hashCode();
		h = 79 * h + myWidth;
		h = 61 * h + myHeight;
		return h;
	}

}
