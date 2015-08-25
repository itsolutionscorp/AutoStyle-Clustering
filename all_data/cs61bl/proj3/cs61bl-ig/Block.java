
import java.awt.Rectangle;
import java.util.*;

public class Block {
	
	public Point tL; //topLeft point
	public Point bR; //bottom right point
	
	/**
	 * Make a new block parameterized
	 * by the given coordinates
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Block(int x1, int y1, int x2, int y2) { 
		tL = new Point(x1, y1);
		bR = new Point(x2, y2);
		if (x2 < x1 || y2 < y1 || x1 < 0 || y1 < 0 || x2 > Tray.length || y2 > Tray.height) {
			//System.out.println(x2 + " " + x1 + " " + y2 + " " + y1 + "  width = " + Tray.length + ", height = " + Tray.height);
			System.out.println("Invalid init and/or goal file.");
		}
		ArrayList<Point> l = new ArrayList<Point>();
		for (int x = (int) tL.getX(); x <= bR.getX(); x++) {
			for (int y = (int) tL.getY(); y >= bR.getY(); y--) {
				l.add(new Point(x, y));
			}
		}
	}
	
	public boolean equals(Object o2) {
		Block b2 = (Block) o2;
		return tL.equals(b2.tL) &&
			   bR.equals(b2.bR);
	}
	
	/**
	 * Moves block to the location specified
	 * Mutates span
	 * @param x1 - for tL
	 * @param y1 - for tL
	 * @param x2 - for bR
	 * @param y2 - for bR
	 */
	public void move(int x1, int y1, int x2, int y2) {
		tL.setLocation(x1, y2);
		bR.setLocation(x2, y2);
	}
	
	/**
	 * 
	 * @param b2
	 * @return
	 */
	public boolean intersectBlock2(Block b2) {
		if(b2.equals(this)) return true;
		if((b2.bR.getX() > tL.getX() && b2.bR.getX() <= bR.getX()) || 
			(b2.tL.getX() > tL.getX() && b2.tL.getX() <=  bR.getX())) {
			if((b2.tL.getY() < tL.getY() && b2.bR.getY() >  tL.getY()) ||
			   (b2.tL.getY() < bR.getY() && b2.tL.getY() > bR.getY())) {
				//System.out.println(this + " and " + b2);
				return true;
				
			}
		}
		return false;
	}
	
	public boolean intersectOther(Block b2) {
		Rectangle r1 = new Rectangle(tL.getX(), tL.getY(), bR.getX() - tL.getX(), bR.getY() - tL.getY());
		Rectangle r2 = new Rectangle(b2.tL.getX(), b2.tL.getY(), b2.bR.getX() - b2.tL.getX(), b2.bR.getY() - b2.tL.getY());

		return r1.intersects(r2);
		//return b2.intersectBlock2(this) || intersectBlock2(b2);
	}
	
	
	public String toString() {
		return "[TL=" + tL.toString() + ", bR=" + bR.toString() + "]";
		
	}
	
	public static void main(String[] args) {
		Tray.height = 100;
		Tray.length = 100;
		
		Block b1 = new Block(2, 0, 4, 1);
		Block b2 = new Block(3, 0, 4, 1);
		System.out.println(b1.intersectOther(b2));
	}

}
