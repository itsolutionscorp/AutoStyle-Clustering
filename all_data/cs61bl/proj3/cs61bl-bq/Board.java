import java.util.*;
import java.awt.Point;


public class Board {
	private ArrayList<String> myMoves;
	HashSet<Point> myBoard;
	private HashMap<Point, Block> myBlocks;
	private int myHeight;
	private int myWidth;
	private int hashCode;

	public Board(Board anotherBoard) {
		this.myMoves = new ArrayList<String>(anotherBoard.moves());
		this.myBlocks = new HashMap<Point, Block>();
		for (Point p : anotherBoard.blocks().keySet()) {
			Block b = anotherBoard.blocks().get(p);
			Block block = new Block(b);
			myBlocks.put(p, block);
		}
		this.myHeight = anotherBoard.myHeight;
		this.myWidth = anotherBoard.myWidth;
		this.myBoard = new HashSet<Point>();
		myBoard = (HashSet<Point>) anotherBoard.myBoard.clone();
	}

	public Board(int width, int height) {
		myMoves = new ArrayList<String>();
		myBoard = new HashSet<Point>();
		myHeight = height;
		myWidth = width;
		myBlocks = new HashMap<Point, Block>();
	}

	public boolean equalsGoal(HashMap<Point, Block> goal) {
		for (Point p : goal.keySet()) {
			if (!myBlocks.containsKey(p)) {
				return false;
			} else if (!(myBlocks.get(p).equals(goal.get(p)))) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(Object o) {
		Board b = (Board) o;
		if (!myBlocks.equals(b.blocks())) {
			return false;
		}
		return (myWidth == b.width() && myHeight == b.height());
	}

	public void printMoves() {
		for (String move : myMoves) {
			System.out.println(move);
		}
	}

	public void setHashCode() {
		int h = 5;
		for (Block b : myBlocks.values()) {
			h += 97 * b.hashCode();
		}
		hashCode = h;
	}

	public int hashCode() {
		return hashCode;
	}

	public ArrayList<String> moves() {
		return myMoves;
	}

	public HashSet<Point> board() {
		return myBoard;
	}

	public int height() {
		return myHeight;
	}

	public int width() {
		return myWidth;
	}

	public HashMap<Point, Block> blocks() {
		return myBlocks;
	}

	public void setMove(String s) {
		myMoves.add(s);
	}

	public Board copy() {
		Board newBoard = this;
		return newBoard;
	}

	// Add in a check to see if it even can move first..//
	public ArrayList<Board> possibleMoves(TreeSet<Integer> seen) {
		ArrayList<Board> boards = new ArrayList<Board>();
		for (Block b : myBlocks.values()) {
			boards.addAll(possibleMoves(b, seen));
		}
		return boards;
	}

	/**
	 * Moves this piece to a new topleftX and topleftY on the current board and
	 * then returns an updated board
	 * 
	 * @param newX
	 * @param newY
	 * @param currentBoard
	 * @return
	 */

	public int hashPiece(Block b, int newX, int newY) {
		Block tempPiece = new Block(b);
		int oldHash = 97 * tempPiece.hashCode();
		tempPiece.myTopLeft = new Point(newX, newY);
		tempPiece.myBottomRight = new Point(newX + b.width(), newY + b.height());
		int newHash = 97 * tempPiece.hashCode();
		int newHashCode = hashCode - oldHash + newHash;
		return newHashCode;
	}

	public Board movePiece(Block b, int newX, int newY, TreeSet<Integer> seen) {
		int topY = (int) b.topLeft().getY();
		int leftX = (int) b.topLeft().getX();
		int hash = hashPiece(b, newX, newY);
		if (!seen.contains(hash)) {
			Block tempPiece = new Block(b);
			Board next = new Board(this);
			next.removeBlock(tempPiece);
			tempPiece.myTopLeft = new Point(newX, newY);
			tempPiece.myBottomRight = new Point(newX + b.width(), newY
					+ b.height());
			next.addBlock(tempPiece);
			next.setMove(topY + " " + leftX + " " + newY + " " + newX);
			next.setHashCode();
			return next;
		} else {
			return null;
		}
	}

	public static void addIfThere(ArrayList<Board> moves, Board board) {
		if (board != null) {
			moves.add(board);
		}
	}

	/**
	 * Returns a list of all possible moves for the given piece
	 * 
	 * @param currentBoard
	 * @return
	 */
	public ArrayList<Board> possibleMoves(Block b, TreeSet<Integer> seen) {
		ArrayList<Board> moves = new ArrayList<Board>();
		int topY = (int) b.topLeft().getY();
		int bottomY = (int) b.bottomRight().getY();
		int leftX = (int) b.topLeft().getX();
		int rightX = (int) b.bottomRight().getX();
		boolean moveUp = true;
		boolean moveDown = true;
		boolean moveLeft = true;
		boolean moveRight = true;
		if (bottomY + 1 < myHeight) {
			for (int i = leftX; i <= rightX; i++) {
				if (myBoard.contains(new Point(i, bottomY + 1))) {
					moveDown = false;
					break;
				}
			}
		} else {
			moveDown = false;
		}
		// can you move up?
		if (topY - 1 >= 0) {
			for (int i = leftX; i <= rightX; i++) {
				if (myBoard.contains(new Point(i, topY - 1))) {
					moveUp = false;
					break;
				}
			}
		} else {
			moveUp = false;
		}
		// can you move left?
		if (leftX - 1 >= 0) {
			for (int i = topY; i <= bottomY; i++) {
				if (myBoard.contains(new Point(leftX - 1, i))) {
					moveLeft = false;
					break;
				}
			}
		} else {
			moveLeft = false;
		}
		// can you move right?
		if (rightX + 1 < myWidth) {
			for (int i = topY; i <= bottomY; i++) {
				if (myBoard.contains(new Point(rightX + 1, i))) {
					moveRight = false;
					break;
				}
			}
		} else {
			moveRight = false;
		}
		// check for move, copy board, make move, add moved board
		if (moveDown) {
			addIfThere(moves, movePiece(b, leftX, topY + 1, seen));
		}
		if (moveUp) {
			addIfThere(moves, movePiece(b, leftX, topY - 1, seen));
		}
		if (moveLeft) {
			addIfThere(moves, movePiece(b, leftX - 1, topY, seen));
		}
		if (moveRight) {
			addIfThere(moves, movePiece(b, leftX + 1, topY, seen));
		}
		return moves;
	}

	/**
	 * Add this block to the current board at the given top left X and Y
	 * position
	 * 
	 * @param newX
	 * @param newY
	 * @param currentBoard
	 */

	public void addBlock(Block b) {
		myBlocks.put(b.myTopLeft, b);
		int rightX = (int) b.myBottomRight.getX();
		int bottomY = (int) b.myBottomRight.getY();
		for (int x = (int) b.myTopLeft.getX(); x <= rightX; x++) {
			for (int y = (int) b.myTopLeft.getY(); y <= bottomY; y++) {
				Point p = new Point(x, y);
				if (myBoard.contains(p)) {
					throw new IllegalStateException("overlapping piece");
				} else {
					myBoard.add(p);
				}
			}
		}
	}

	/**
	 * Removes this piece from the current board
	 * 
	 * @param currentBoard
	 * @return
	 */
	public void removeBlock(Block b) {
		myBlocks.remove(b.myTopLeft);
		int rightX = (int) b.myBottomRight.getX();
		int bottomY = (int) b.myBottomRight.getY();
		for (int x = (int) b.myTopLeft.getX(); x <= rightX; x++) {
			for (int y = (int) b.myTopLeft.getY(); y <= bottomY; y++) {
				myBoard.remove(new Point(x, y));
			}
		}
	}

}
