public class ShortPoint {

	short myX;
	short myY;
	
	public ShortPoint(short x, short y) {
		myX = x;
		myY = y;
	}
	
	public ShortPoint(int x, int y) {
		myX = (short) x;
		myY = (short) y;
	}
	
	public short getX() {
		return myX;
	}
	
	public short getY() {
		return myY;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof ShortPoint)) {
			return false;
		} else {
			ShortPoint p = (ShortPoint) obj;
			return myX == p.getX() && myY == p.getY();
		}
	}
	
	public void setLocation(int x, int y) {
		myX = (short) x;
		myY = (short) y;
	}

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 1;
		result = prime * result + myX;
		result = prime * result + myY;
		return result;
	}
	
	public ShortPoint clone() {
		return new ShortPoint(myX, myY);
	}
	
	public String toString() {
		String s = "";
		return s + myX + ", " + myY;
	}
	
}
