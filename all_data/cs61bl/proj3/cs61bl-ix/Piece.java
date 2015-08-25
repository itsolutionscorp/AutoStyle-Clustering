/*
 * Piece.java
 * 
 * CS61BL @ Berkeley
 */
import java.awt.Point;
import java.util.*;

public class Piece {
	/*
	 * A piece is represented my two coordinates, top left and bottom right
	 * The Piece class is an abstract representation of a piece. 
	 * 
	 * The important variable is two points; the height and width is
	 * calculated at the constructor for convenience. 
	 */
	
	private Point myTopLeft; // The top left coordinate of the piece
	private Point myBottomRight; // The bottom right coordinate of the piece
	
	private int myHeight; // The height
	private int myWidth; // The width
	
	private HashSet<Point> occupied;
	
	// Constructor #1: take in an array of coordinates(4)
	public Piece(int[] coordinates) {
		this(new Point(coordinates[1], coordinates[0]), new Point(coordinates[3], coordinates[2]));
	}
	
	// Constructor #2: take in two Point: (the main constructor)
	public Piece(Point topLeft, Point bottomRight) {
		myTopLeft = topLeft;  // assign the top left coordinate
		myBottomRight = bottomRight; // assign the bottom right corner
		
		myWidth = myBottomRight.x - myTopLeft.x + 1; // width - 
		myHeight = myBottomRight.y - myTopLeft.y + 1; // height - 
		
		occupied = new HashSet<Point>(); //fill in the "occupied" points
		for (int i = myTopLeft.x; i <= myBottomRight.x; i++) {
			for (int j = myTopLeft.y; j <= myBottomRight.y; j++) {
				occupied.add(new Point(i, j));
			}
		}
	}
	
	// Constructor #3: take in the top left coordinate, 
	// width and height, figure out the bottom right coordinate. 
	public Piece(Point topLeft, int width, int height) {
		this(topLeft, new Point(topLeft.x + width - 1, topLeft.y + height - 1));
	}
	
	// All the getter methods
	public int getHeight() {
		return myHeight;
	}

	public int getWidth() {
		return myWidth;
	}

	public Point topLeft() {
		return myTopLeft;
	}
	
	public Point bottomRight() {
		return myBottomRight;
	}
	
	public Set<Point> getOccupied() {
		return occupied;
	}
	
	// There is no setter methods goes in this class. To modify a piece, 
	// the move() method should be called. 
	public Piece move(int[] move) {
		/*
		 * The move method moves a piece and return a moved piece. 
		 * A "Move" is described as an integer array
		 * The length of the array is 4, with the content:
		 * [a, b, c, d], in which (b, a) is the original
		 * top left coordinate and (d, c) is the current. 
		 * We don't really to specify (b, a) though
		 * 
		 * The move method return a new Piece object, without
		 * modifying the original. 
		 */
		return new Piece(new Point(move[3], move[2]), myWidth, myHeight);
	}
	
	
	// Generated hashCode() and equals() method
	// Eclipse AUTO Generated!!!
	// No need to understand
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myBottomRight == null) ? 0 : myBottomRight.hashCode());
		result = prime * result + ((myTopLeft == null) ? 0 : myTopLeft.hashCode());
		return result;
	}
	
	// Two Pieces are equal if two coordinates are equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (myBottomRight == null) {
			if (other.myBottomRight != null)
				return false;
		} else if (!myBottomRight.equals(other.myBottomRight))
			return false;
		if (myTopLeft == null) {
			if (other.myTopLeft != null)
				return false;
		} else if (!myTopLeft.equals(other.myTopLeft))
			return false;
		return true;
	}

	// String representation of the Piece: "(x, y), (x, y)"
	@Override
	public String toString() {
		return String.format("(%d, %d), (%d, %d)", myTopLeft.x, myTopLeft.y, myBottomRight.x, myBottomRight.y); 
	}
	
	// Make a copy of the piece. 
	public Piece copy() {
		Point topLeft = new Point(myTopLeft.x, myTopLeft.y); // Copy the top left coordinate
		return new Piece(topLeft, myHeight, myWidth); //return the copy
	}

	//return moves in four directions without checking validity
	public List<int[]> moves() {
		List<int[]> temp = new ArrayList<int[]>();
		int[] move1 = { myTopLeft.y, myTopLeft.x, myTopLeft.y - 1, myTopLeft.x }; // move up
		int[] move2 = { myTopLeft.y, myTopLeft.x, myTopLeft.y, myTopLeft.x + 1 }; // move right
		int[] move3 = { myTopLeft.y, myTopLeft.x, myTopLeft.y + 1, myTopLeft.x }; // move down
		int[] move4 = { myTopLeft.y, myTopLeft.x, myTopLeft.y, myTopLeft.x - 1 }; // move left
		temp.add(move1); // add them to the list
		temp.add(move2);
		temp.add(move3);
		temp.add(move4);
		return temp;
	}

	public boolean isValid(Board b) {
		if (myTopLeft.y < 0 || myTopLeft.x < 0 || myBottomRight.y < 0 || myBottomRight.x < 0) {
			return false;
		}
		if (myTopLeft.y >= b.getHeight() || myBottomRight.y >= b.getHeight()) {
			return false;
		}
		if (myTopLeft.x >= b.getWidth() || myBottomRight.x >= b.getWidth()) {
			return false;
		}
		return true;
	}
	
	public static void main(String args[]) {
		Piece p = new Piece(new Point(0, 0), 3, 3);
		for (Point point : p.getOccupied()) {
			System.out.println(point.x + ", " + point.y);
		}
	}
}