/*
 * Board.java
 * 
 * CS61BL @ Berkeley
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

public class Board {
	private int myWidth; // The width of the board
	private int myHeight; // The height of the board
	private HashMap<Point, Piece> myPieces; // upper left coordinate to piece object
	List<int[]> moves; // The move history. Used to trace back all the moves
	
	// Constructor #1 : ordinary constructor, with height, width, and a list
	// of pieces in the format specified by the spec
	public Board(int width, int height, List<int[]> pieces) {
		/* have to check that width and height is non negative
		* check each line in pieces has 4 arguments, check that each coordinate
		* in the bounds, check everything is Integer.
		*/
		myPieces = new HashMap<Point, Piece>(); //construct an empty HashMap
		myWidth = width; //assign the width variable
		myHeight = height; //assign the height variable
		for (int[] i : pieces) { //iterate through the pieces specified
			myPieces.put(new Point(i[1], i[0]), new Piece(i)); //add to the map, creating piece object
		}
		moves = new LinkedList<int[]>(); //initialize the history list
	}
	
	// Constructor #2 : special constructor, construct directly with a map
	public Board(int width, int height, HashMap<Point, Piece> map) {
		myWidth = width;
		myHeight = height;
		myPieces = map;
		moves = new ArrayList<int[]>();
	}
	
	// Getters of width and height
	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}

	//return a list of integer arrays representing a "move"
	// All moves returned should be valid
	public List<int[]> getAllMoves() {
		List<int[]> moves = new LinkedList<int[]>(); //a temporary list
		boolean[][] occupied = new boolean[myWidth][myHeight];
		for (Piece p : myPieces.values()) {
			for (Point point : p.getOccupied()) {
				occupied[point.x][point.y] = true;
			}
		}
		
		for (Piece i : myPieces.values()) { // Go through all the pieces
			for (int[] j : i.moves()) { // check 4 directions of each piece
				//obvious overlap, instant reject
				if (myPieces.get(new Point(j[3], j[2])) != null) {
					continue;
				}
				
				// out of bounds
				Piece movedPiece = i.move(j);
				if (!movedPiece.isValid(this)) {
					continue;
				}
				
				// test for overlap
				boolean overlap = false;
				for (Point point : movedPiece.getOccupied()) {
					if (!i.getOccupied().contains(point) && occupied[point.x][point.y]) {
						overlap = true;
						break;
					}
				}
				if (!overlap) {
					moves.add(j);
				}
				
			}
		}
		return moves;
	}

	// make a new move, return the new board
	public Board move(int[] move) {
		//copy a instance of hashmap
		HashMap<Point, Piece> newMap = (HashMap<Point, Piece>) (myPieces.clone());
		Point from = new Point(move[1], move[0]); // original coordinate
		Point to = new Point(move[3], move[2]); // coordinate to move to
		
		Piece toMove = newMap.remove(from); // the piece to move (remove it from the map)
		newMap.put(to, toMove.move(move)); // make a move and put it back to the map
		
		Board toReturn = new Board(myWidth, myHeight, newMap); //the board to return
		for (int[] i : moves) { //copy the history of the move
			toReturn.moves.add(i);
		}
		toReturn.moves.add(move); //append a new history to it
		return toReturn; // return the newly constructed Board
	}
	
	// Generated HashCode method
	// Eclipse AUTO Generated!!!
	// No need to understand how it works. 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (myHeight != other.myHeight)
			return false;
		if (myPieces == null) {
			if (other.myPieces != null)
				return false;
		} else if (!myPieces.equals(other.myPieces))
			return false;
		if (myWidth != other.myWidth)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myHeight;
		result = prime * result + ((myPieces == null) ? 0 : myPieces.hashCode());
		result = prime * result + myWidth;
		return result;
	}
	
	//Check whether we reached our goal
	public boolean containsPiece(List<int[]> p) {
		for (int[] i : p) {
			Point upperLeft = new Point(i[1], i[0]);
			if (!myPieces.containsKey(upperLeft)) {
				return false;
			}
			Point bottomRight = myPieces.get(upperLeft).bottomRight();
			if (bottomRight.x != i[3] || bottomRight.y != i[2]) {
				return false;
			}
		}
		return true;
	}
}
