
public class Point {
	
	private int myX;
	private int myY;
	
	public Point(int x, int y) {
		myX = x;
		myY = y;
	}
	
	public int getX() {
		return myX;
	}
	
	public int getY() {
		return myY;
	}
	
	public void addX(int x) {
		myX += x;
	}
	
	public void addY(int y) {
		myY += y;
	}
	
	public void setLocation(int x, int y) {
		myX = x;
		myY = y;
	}
	
	public String toString() {
		return "(" + myX + ", " + myY + ")"; 
	}
	
	public boolean equals(Object o2) {
		Point p2 = (Point) o2;
		return p2.myX == myX &&
			   p2.myY == myY;
	}

}
