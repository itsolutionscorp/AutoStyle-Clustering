import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Represents a tray of sliding blocks with a set configuration.
 * 
 * @author caseyolen
 *
 */
public class BoardConfiguration {

	private HashSet<Piece> myPieces;
	private HashMap<Point, Piece> mySquares;
	
	private String move;
	private BoardConfiguration myParent;
	private Point myDimensions;

	/**
	 * Creates a new empty BoardConfiguration with this many rows and columns
	 * 
	 * @param rows
	 *            the height of the board
	 * @param columns
	 *            the length of the board
	 */
	public BoardConfiguration(int rows, int columns) {
		mySquares = new HashMap<Point, Piece>();
		myPieces = new HashSet<Piece>();
		myDimensions = new Point(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				mySquares.put(new Point(i, j), null);
			}
		}
	}
//
//	public ArrayList<Point> getPoints(Piece p) {
//		return myPieces.get(p);
//	}

	public void printPath() {
		BoardConfiguration parent = this.myParent;
		Stack<String> paths = new Stack<String>();
		paths.push(this.move);
		if (parent != null) {
			while (parent.myParent != null) {
				paths.push(parent.move);
				parent = parent.myParent;
			}
			while (!paths.isEmpty()) {
				System.out.println(paths.pop());
			}
		}
	}
	public Point getDimensions(){
		return myDimensions;
	}

	/**
	 * If possible, adds a Piece object to this board.
	 * 
	 * @param p
	 *            the upper left corner of the Piece to be added
	 * @param q
	 *            the lower right corner of the Piece to be added
	 * @throws IllegalArgumentException
	 *             thrown if a different Piece already occupies the argument
	 *             squares, or if this board does not contain the argument
	 *             squares.
	 */
	public void addPiece(Point p, Point q) throws IllegalArgumentException {
		if (p.x < 0 || p.x >= myDimensions.x || p.y < 0
				|| p.y >= myDimensions.y || q.x < 0 || q.x >= myDimensions.x
				|| q.y < 0 || q.y >= myDimensions.y) {
			throw new IllegalArgumentException(
					"start/end point is not on this board");
		}
		Piece newPiece = new Piece(p, q, this);
		ArrayList<Point> squares = newPiece.squaresThatICover();
		for (Point o : squares) {
			if (!(mySquares.get(o) == null)) {
				throw new IllegalArgumentException(
						"pieces already occupy these squares");
			}
		}
		for (Point o : squares) {
			mySquares.put(o, newPiece);
		}
		myPieces.add(newPiece);
	}

	/**
	 * Creates a new board that is an identical copy of this BoardConfiguration.
	 * 
	 * @return a new copy of this board
	 */
	public BoardConfiguration copy() {
		BoardConfiguration rtn = new BoardConfiguration(myDimensions.x,
				myDimensions.y);
		for (Piece p : myPieces) {
			rtn.addPiece(p.getUpperLeftCorner(), p.getLowerRightCorner());
		}
		return rtn;
	}

	public int checkConfiguration(ArrayList<String> k) {
		int numberMatched = 0;
		for (String o : k){
			ArrayList<Integer> coords = Solver.lineParser(o);
			Point p = new Point(coords.get(0), coords.get(1));
			Point q = new Point(coords.get(2), coords.get(3));
			for (Piece t : myPieces) {
				if (t.getUpperLeftCorner().equals(p)
						&& t.getLowerRightCorner().equals(q)) {
					numberMatched ++;
				}
			}
		}
		return numberMatched;
	}

	public String toString() {
		String result = new String();
		result += "The number of pieces in this configuration is: "
				+ myPieces.size() + "\n";
		for (Piece piece : myPieces) {
			result += piece.toString();
		}
		return result;
	}

	/**
	 * Makes the square with coordinates given by Point p empty.
	 * 
	 * @param p
	 *            the coordinates of the square to be made empty.
	 */
	// public void setEmpty(Point p) {
	// mySquares.put(p, null);
	// }

	public HashMap<Point, Piece> getSquares() {
		return mySquares;
	}

	/**
	 * Moves a Piece object from a starting coordinate to an ending coordinate,
	 * if possible. Does not modify this BoardConfiguration, but instead moves
	 * the Piece on a copy and returns that copy.
	 * 
	 * @param start
	 *            Point representing the starting coordinate
	 * @param end
	 *            Point representing the ending coordinate
	 * @return the legal BoardConfiguration that results from moving the Piece
	 * @throws IllegalArgumentException
	 *             thrown if the starting point is the same as the ending point,
	 *             if the specified move is not a legal move, if the starting
	 *             Point does not specify the coordinates of a Piece object, or
	 *             if any of the arguments Points are not contained on this
	 *             board.
	 */
	public BoardConfiguration move(Point start, Point end)
			throws IllegalArgumentException {
		int changeInX = end.x - start.x, changeInY = end.y - start.y;
		if (changeInX == 0 && changeInY == 0) {
			throw new IllegalArgumentException(
					"start and end points are the same");
		} else {
			try {
				BoardConfiguration rtn = copy();
				Piece movingPiece = rtn.getSquares().get(start);
				while (!movingPiece.getUpperLeftCorner().equals(end)) {
					rtn.removePiece(movingPiece);
					if (changeInY == 0) {
						if (changeInX < 0) {
							movingPiece.translate(-1, 0);
						} else {
							movingPiece.translate(1, 0);
						}
					} else {
						if (changeInY < 0) {
							movingPiece.translate(0, -1);
						} else {
							movingPiece.translate(0, 1);
						}
					}
					rtn.addPiece(movingPiece.getUpperLeftCorner(),
							movingPiece.getLowerRightCorner());
				}
				return rtn;
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("not a legal move");
			}
		}
	}

	public HashSet<Piece> movablePieces() {
		HashSet<Piece> rtn = new HashSet<Piece>();
		ArrayList<Point> empties = new ArrayList<Point>();
		for (Point p : mySquares.keySet()) {
			if (mySquares.get(p) == null) {
				empties.add(p);
			}
		}
		for (Point o : empties) {
			for (int x = -1; x < 2; x++) {
				Point newPoint = new Point(o.x + x, o.y);
				if (mySquares.get(newPoint) != null) {
					// System.out.println(mySquares.get(newPoint));
					rtn.add(mySquares.get(newPoint));
				}
			}
			for (int x = -1; x < 2; x++) {
				Point newPoint = new Point(o.x, o.y + x);
				if (mySquares.get(newPoint) != null) {
					rtn.add(mySquares.get(newPoint));
				}
			}
		}
		return rtn;
	}

	public void removePiece(Piece p) {
		if (myPieces.contains(p)) {
			for (Point o : p.squaresThatICover()) {
				mySquares.put(o, null);
			}
			myPieces.remove(p);
		}
	}

	/**
	 * Checks if the move from Point p to Point q is a legal move.
	 * 
	 * @param p
	 *            starting coordinate of the Piece to move
	 * @param q
	 *            ending coordinate of the Piece after the move
	 * @return true if this move is a legal move, else false
	 */
	public boolean isLegalMove(Point p, Point q) {
		if ((q.x - p.x == 0) && (q.y - p.y == 0)) {
			return false;
		} else if (q.x < 0 || q.x >= myDimensions.x || q.y < 0 || q.y >= myDimensions.y){
			return false;
		} else if (((q.x - p.x != 0) && (q.y - p.y == 0))
				|| ((q.x - p.x == 0) && (q.y - p.y != 0))) {
			Piece o = new Piece(mySquares.get(p));
			Piece copy = new Piece(o);
			try {
				if (mySquares.get(q) == null || mySquares.get(q).equals(o)){
					removePiece(mySquares.get(p));
					copy.translate(q.x - p.x, q.y - p.y);
					addPiece(copy.getUpperLeftCorner(), copy.getLowerRightCorner());	
					removePiece(copy);
					addPiece(o.getUpperLeftCorner(), o.getLowerRightCorner());
					return true;
				} else {
					return false;
				}
				
			} catch (IllegalArgumentException e) {
				addPiece(o.getUpperLeftCorner(), o.getLowerRightCorner());
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Creates a string representation of the move from p to q.
	 * 
	 * @param p
	 *            the starting coordinate
	 * @param q
	 *            the ending coordinate
	 * @return A four digit string that represents p's x and y and q's x and y
	 */
	public String moveString(Point p, Point q) {
		int oldLeftX = p.x, oldLeftY = p.y, newLeftX = q.x, newLeftY = q.y;
		return "" + oldLeftX + " " + oldLeftY + " " + newLeftX + " " + newLeftY;

	}

	/**
	 * Creates a collection of all possible moves that can be made on this
	 * board.
	 * 
	 * @return an ArrayList of String representations of all of the legal moves
	 *         that can be made on this board.
	 */
	public HashSet<String> possibleMoves() {
		HashSet<String> rtn = new HashSet<String>();
		for (Piece p : movablePieces()) {
			for (Point o : p.getPossibleMoves()) {
				rtn.add(moveString(p.getUpperLeftCorner(), o));
			}
		}
		return rtn;
	}

	/**
	 * Returns an integer that is unique for every unique BoardConfiguration
	 * object.
	 */
	public int hashCode() {
		int hash = 1;
		for (Point p: mySquares.keySet()){
			if (mySquares.get(p) != null){
				hash += mySquares.get(p).toString().hashCode();
			} 
		}
		return hash;
	}

	/**
	 * Returns true if b represents the same configuration that this
	 * BoardConfiguration represents.
	 */
	public boolean equals(Object b) {
		if (b instanceof BoardConfiguration) {
			for (Piece p : ((BoardConfiguration) b).myPieces){
				if (mySquares.size() != ((BoardConfiguration) b).mySquares.size()){
					return false;
				}
				if (this.mySquares.get(p.myUpperLeftCorner) == null) {
					return false;
				}
				else if(!this.mySquares.get(p.myUpperLeftCorner).equals(p)){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Sets this configuration's unique ID number.
	 * 
	 * @param newID
	 *            integer that myID will be assigned to
	 */

	public BoardConfiguration getMyParent() {
		return myParent;
	}

	public void setMyParent(BoardConfiguration myParent) {
		this.myParent = myParent;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public static BoardConfiguration parseFile(File f)
			throws IllegalArgumentException {
		try {
			BufferedReader b = new BufferedReader(new FileReader(f));
			ArrayList<Integer> myDimensions = Solver.lineParser(b.readLine());
			if (myDimensions.size() != 2){
				b.close();
				throw new IllegalArgumentException("dimensions are not formatted correctly");
			}
			BoardConfiguration rtn = new BoardConfiguration(
					myDimensions.get(0), myDimensions.get(1));
			while (b.ready()) {
				ArrayList<Integer> current = Solver.lineParser(b.readLine());
				if (current.size() == 4) {
					Point p = new Point(current.get(0), current.get(1));
					Point q = new Point(current.get(2), current.get(3));
					rtn.addPiece(p, q);
				} else {
					b.close();
					throw new IllegalArgumentException("pieces are not formatted correctly");
				}
			}
			b.close();
			return rtn;
		} catch (IOException e) {
			throw new IllegalArgumentException("file cannot be parsed");
		}

	}
//	
//	public int compareTo(Object o){
//		BoardConfiguration b = (BoardConfiguration) o;
//		if (b.checkConfiguration()
//	}

	/**
	 * A Piece instance represents a single Piece in this BoardConfiguration
	 * 
	 * @author caseyolen
	 *
	 */
	private class Piece {
		private Point myUpperLeftCorner;
		private Point myLowerRightCorner;
		private BoardConfiguration myBoard;

		/**
		 * Creates a new Piece object that has its upper left corner at
		 * coordinate p and its lower right corner at coordinate q
		 * 
		 * @param p
		 *            upper left corner of this Piece
		 * @param q
		 *            lower right corner of this Piece
		 */
		private Piece(Point p, Point q, BoardConfiguration b) {
			myUpperLeftCorner = new Point(p.x, p.y);
			myLowerRightCorner = new Point(q.x, q.y);
			myBoard = b;
		}

		/**
		 * Gets this Piece's upper left corner coordinate.
		 * 
		 * @return Point coordinate stored in myUpperLeftCorner
		 */
		public Point getUpperLeftCorner() {
			return myUpperLeftCorner;
		}

		/**
		 * Gets this Piece's lower right corner coordinate.
		 * 
		 * @returnPoint coordinate stored in myLowerRightCorner
		 */
		public Point getLowerRightCorner() {
			return myLowerRightCorner;
		}

		/**
		 * Returns true if o represents the same Piece object that this Piece
		 * represents.
		 */
		public boolean equals(Object o) {
			if (o instanceof Piece) {
				return (o.toString().equals(toString()));
			}
			return false;
		}

		public ArrayList<Point> getPossibleMoves() {
			ArrayList<Point> rtn = new ArrayList<Point>();
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					Point newPoint = new Point(myUpperLeftCorner.x + i,
							myUpperLeftCorner.y + j);
					if (myBoard.isLegalMove(myUpperLeftCorner, newPoint)) {
						//System.out.println(myUpperLeftCorner + " " + newPoint);
						rtn.add(newPoint);
						
					}
				}

			}
			return rtn;
		}
		
		public Piece(Piece p){
			myUpperLeftCorner = new Point(p.getUpperLeftCorner().x, p.getUpperLeftCorner().y);
			myLowerRightCorner = new Point(p.getLowerRightCorner().x, p.getLowerRightCorner().y);
			
		}

		/**
		 * Gets a collection of all of the coordinates that this Piece occupies.
		 * 
		 * @return collection of Point coordinates that this Piece is covering
		 */
		public ArrayList<Point> squaresThatICover() {
			ArrayList<Point> rtn = new ArrayList<Point>();
			if (myUpperLeftCorner.equals(myLowerRightCorner)) {
				rtn.add(myUpperLeftCorner);
			} else {
				int startX = myUpperLeftCorner.x, startY = myUpperLeftCorner.y, endX = myLowerRightCorner.x, endY = myLowerRightCorner.y;
				for (int i = startX; i <= endX; i++) {
					for (int j = startY; j <= endY; j++) {
						rtn.add(new Point(i, j));
					}
				}
			}
			return rtn;
		}

		public void translate(int dx, int dy) {
			myUpperLeftCorner.translate(dx, dy);
			myLowerRightCorner.translate(dx, dy);
		}

		/**
		 * Returns an integer that is unique for every unique Piece object.
		 */
		public int hashCode() {
			return toString().hashCode();
		}

		public String toString() {
			String result = new String();
			result += Integer.toString(myUpperLeftCorner.x) + " ";
			result += Integer.toString(myUpperLeftCorner.y) + " ";
			result += Integer.toString(myLowerRightCorner.x) + " ";
			result += Integer.toString(myLowerRightCorner.y) + " ";
			result += "\n";
			return result;
		}

	}

}
