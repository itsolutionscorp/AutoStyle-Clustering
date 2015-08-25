import java.util.*;
import java.awt.Point;
import java.io.IOException;

public class Board implements Comparable<Board> {

	// boolean[][] myBoard;
	Piece[][] boardPieces;
	ArrayList<Piece> myPieces;
	int height;
	int width;
	int currID;
	String myMove;
	int priority;
	boolean full;
	private HashSet<Point> empty;

	public Board(int h, int w) {

		height = h;
		width = w;
		// myBoard = new boolean[height][width];
		boardPieces = new Piece[height][width];
		myPieces = new ArrayList<Piece>();
		currID = 0;
		myMove = null;
		priority = 0;
		full = false;
		empty = new HashSet<Point>();

	}

	public boolean isValidMove(Piece p, int newX1, int newY1) {
		int newX2 = p.myX2 + (newX1 - p.myX1);
		int newY2 = p.myY2 + (newY1 - p.myY1);
		if (newX1 >= width || newX1 < 0 || newX2 >= width || newX2 < 0 || newY1 >= height || newY1 < 0
				|| newY2 >= height || newY2 < 0) {
			// System.out.println("oob");
			return false;
		}

		for (int i = newY1; i <= newY2; i++) {
			for (int j = newX1; j <= newX2; j++) {
				if (boardPieces[i][j] != null) {
					if (boardPieces[i][j].myID != p.myID) {
						return false;
					}
				}
			}
		}
		// System.out.println("!!!!!");
		return true;
	}

	public void addPiece(int y1, int x1, int y2, int x2) throws IOException {
		Piece p = new Piece(x1, y1, x2, y2, currID);
		if (x1 > x2 || y1 > y2) {
			throw new IOException("1");
		}
		for (Point point : p.occupiedPoints) {
			if (point.x >= width || point.x < 0 || point.y >= height || point.y < 0) {
				throw new IOException("2");
			}
		}
		for (Point point : p.occupiedPoints) {
			if (boardPieces[point.y][point.x] != null) {
				throw new IOException("3");
			}
		}
		for (Point point : p.occupiedPoints) {
			if (!(point.x >= width || point.x < 0 || point.y >= height || point.y < 0)
					&& (boardPieces[point.y][point.x] == null)) {
				boardPieces[point.y][point.x] = p;

			}
		}
		currID++;
		myPieces.add(p);
	}

	public Board makeMove(int y1, int x1, int y2, int x2) throws IOException {
		if (!isValidMove(boardPieces[y1][x1], x2, y2)) {
			return null;
		}
		HashSet<Point> empty = new HashSet<Point>();
		Board toReturn = new Board(height, width);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Piece toCopy = boardPieces[j][i];
				if (toCopy != null) {
					if (toReturn.boardPieces[j][i] == null) {
						toReturn.addPiece(toCopy.myY1, toCopy.myX1, toCopy.myY2, toCopy.myX2);
					}
				} else {
					empty.add(new Point(i, j));
				}
			}
		}

		Piece toMove = toReturn.boardPieces[y1][x1];
		for (Point p : toMove.occupiedPoints) {
			toReturn.boardPieces[p.y][p.x] = null;
			empty.add(p);
		}
		toMove.myX2 = x2 + toMove.myWidth - 1;
		toMove.myY2 = y2 + toMove.myLength - 1;
		toMove.myX1 = x2;
		toMove.myY1 = y2;

		ArrayList<Point> newPoints = new ArrayList<Point>();
		for (int i = x2; i < x2 + toMove.myWidth; i++) {
			for (int j = y2; j < y2 + toMove.myLength; j++) {
				Point toAdd = new Point(i, j);
				newPoints.add(toAdd);
				empty.remove(toAdd);
				toReturn.boardPieces[j][i] = toMove;
			}
		}
		toMove.occupiedPoints = newPoints;
		toReturn.empty = empty;
		toReturn.myMove = new String(y1 + " " + x1 + " " + y2 + " " + x2);
		return toReturn;
	}

	public String toString() {
		StringBuilder toReturn = new StringBuilder("");
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (boardPieces[j][i] != null) {
					toReturn.append("1 ");
				} else {
					toReturn.append("0 ");
				}
			}
			toReturn.append("\n");
		}
		return toReturn.toString();
	}

	public boolean equals(Object o) {
		if (o instanceof Board) {
			Board b = (Board) o;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Piece thisPiece = boardPieces[j][i];
					Piece thatPiece = b.boardPieces[j][i];
					if (thisPiece == null && thatPiece == null) {
						continue;
					}
					if ((thisPiece == null && thatPiece != null) || (thisPiece != null && thatPiece == null)) {
						return false;
					}
					if (!thisPiece.sameSize(thatPiece)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean isGoal(Board goal) {
		for (int i = 0; i < goal.width; i++) {
			for (int j = 0; j < goal.height; j++) {
				if (goal.boardPieces[j][i] != null) {
					Piece toCompare = goal.boardPieces[j][i];
					Piece thisOne = boardPieces[j][i];
					if (thisOne == null) {
						return false;
					}
					if (!thisOne.sameSize(toCompare)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public ArrayList<Board> possibleMoves() throws IOException {
		ArrayList<Board> toReturn = new ArrayList<Board>();
		Board move;
		int count = 1;
		if (full) {
			return possibleMoves2();
		}

		if (height > 6 && width > 6) {
			for (Piece p : myPieces) {
				move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + count);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + count);
				}

				count = 1;
				move = makeMove(p.myY1, p.myX1, p.myY1 + count, p.myX1);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1 + count, p.myX1);
				}
				count = 1;
				move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - count);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - count);
				}
				count = 1;
				move = makeMove(p.myY1, p.myX1, p.myY1 - count, p.myX1);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1 - count, p.myX1);
				}
				count = 1;
			}
		} else {
			for (Piece p : myPieces) {
				move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + count);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + count);
				}
				count = 1;
				move = makeMove(p.myY1, p.myX1, p.myY1 + count, p.myX1);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1 + count, p.myX1);
				}
				count = 1;
				move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - count);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - count);
				}
				count = 1;
				move = makeMove(p.myY1, p.myX1, p.myY1 - count, p.myX1);
				while (move != null && count < 9) {
					toReturn.add(0, move);
					count++;
					move = makeMove(p.myY1, p.myX1, p.myY1 - count, p.myX1);
				}
			}
		}
		return toReturn;
	}

	public ArrayList<Board> possibleMoves2() throws IOException {
		ArrayList<Board> toReturn = new ArrayList<Board>();
		Board move;
		for (Piece p : myPieces) {
			if (isValidMove(p, p.myX1 + 1, p.myY1)) {
				move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + 1);
				if (move != null) {

					toReturn.add(move);
				}
			}
			if (isValidMove(p, p.myX1, p.myY1 + 1)) {
				move = makeMove(p.myY1, p.myX1, p.myY1 + 1, p.myX1);
				if (move != null) {

					toReturn.add(move);
				}
			}
			if (isValidMove(p, p.myX1 - 1, p.myY1)) {
				move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - 1);
				if (move != null) {

					toReturn.add(move);
				}
			}
			if (isValidMove(p, p.myX1, p.myY1 - 1)) {
				move = makeMove(p.myY1, p.myX1, p.myY1 - 1, p.myX1);
				if (move != null) {

					toReturn.add(move);
				}
			}

		}

		return toReturn;
	}

	public ArrayList<Board> possibleMoves3() throws IOException {
		ArrayList<Board> toReturn = new ArrayList<Board>();
		Board move;
		if (empty.isEmpty()) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (boardPieces[j][i] == null) {
						if (j + 1 < height) {
							if (boardPieces[j + 1][i] != null) {
								Piece p = boardPieces[j + 1][i];
								move = makeMove(p.myY1, p.myX1, p.myY1 - 1, p.myX1);
								if (move != null) {
									toReturn.add(move);
								}
							}
						}
						if (j - 1 >= 0) {
							if (boardPieces[j - 1][i] != null) {
								Piece p = boardPieces[j - 1][i];
								move = makeMove(p.myY1, p.myX1, p.myY1 + 1, p.myX1);
								if (move != null) {
									toReturn.add(move);
								}
							}
						}
						if (i + 1 < width) {
							if (boardPieces[j][i + 1] != null) {
								Piece p = boardPieces[j][i + 1];
								move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - 1);
								if (move != null && !toReturn.contains(move)) {
									toReturn.add(move);
								}
							}
						}
						if (i - 1 >= 0) {
							if (boardPieces[j][i - 1] != null) {
								Piece p = boardPieces[j][i - 1];
								move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + 1);
								if (move != null) {
									toReturn.add(move);
								}
							}
						}
					}
				}
			}
		} else {

			for (Point pnt : empty) {
				// System.out.println("hi");
				int i = pnt.x;
				int j = pnt.y;

				if (boardPieces[j][i] == null) {
					if (j + 1 < height) {
						if (boardPieces[j + 1][i] != null) {
							Piece p = boardPieces[j + 1][i];
							move = makeMove(p.myY1, p.myX1, p.myY1 - 1, p.myX1);
							// move = makeMove(p.myY1, p.myX1, p.myY1,
							// p.myX1 -
							// 1);
							if (move != null) {
								toReturn.add(move);
							}
						}
					}
					if (j - 1 >= 0) {
						if (boardPieces[j - 1][i] != null) {
							Piece p = boardPieces[j - 1][i];
							move = makeMove(p.myY1, p.myX1, p.myY1 + 1, p.myX1);
							if (move != null) {
								toReturn.add(move);
							}
						}
					}
					if (i + 1 < width) {
						if (boardPieces[j][i + 1] != null) {
							Piece p = boardPieces[j][i + 1];
							move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 - 1);
							if (move != null && !toReturn.contains(move)) {
								toReturn.add(move);
							}
						}
					}
					if (i - 1 >= 0) {
						if (boardPieces[j][i - 1] != null) {
							Piece p = boardPieces[j][i - 1];
							move = makeMove(p.myY1, p.myX1, p.myY1, p.myX1 + 1);
							if (move != null) {
								toReturn.add(move);
							}
						}
					}
				}

			}
		}

		// System.out.println(toReturn);
		return toReturn;
	}

	public int hashCode() {
		return toString().hashCode();
	}

	public class Piece {
		int myX1;
		int myY1;
		int myX2;
		int myY2;
		int myLength;
		int myWidth;
		ArrayList<Point> occupiedPoints;
		int myID;

		public Piece(int x1, int y1, int x2, int y2, int ID) {
			myX1 = x1;
			myY1 = y1;
			myX2 = x2;
			myY2 = y2;
			myLength = Math.abs(y1 - y2) + 1;
			myWidth = Math.abs(x1 - x2) + 1;
			myID = ID;

			occupiedPoints = new ArrayList<Point>();
			for (int i = x1; i <= x2; i++) {
				for (int j = y1; j <= y2; j++) {
					Point p = new Point(i, j);
					occupiedPoints.add(p);
				}
			}

		}

		public boolean sameSize(Object o) {
			if (((Piece) o).myLength == myLength && ((Piece) o).myWidth == myWidth) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			// return myLength * 1000 + myWidth * 100 ;
			return myX1 * 1000 + myY1 * 100 + myX2 * 10 + myY2;
		}

		public String toString() {
			return new String("Top left: (" + myX1 + ", " + myY1 + ") ; Bottom right: (" + myX2 + ", " + myY2 + ")");
		}
	}

	void setPriority(Board goal, Board init) {
		// TODO Auto-generated method stub
		HashMap<Piece, Integer> goals = new HashMap<Piece, Integer>();
		priority = 0;
		//for (Piece i : init.myPieces) {
			for (Piece g : goal.myPieces) {
				for (Piece p : myPieces) {
					if (p.sameSize(g)) {
						Integer gSoFar = goals.get(g);
						Integer currP =  ((p.myX1 - g.myX1) * (p.myX1 - g.myX1)
										+ (p.myY1 - g.myY1) * (p.myY1 - g.myY1))
								^ (1 / 2);
						if (gSoFar == null) {
							goals.put(g, currP);
						} else if (gSoFar > currP) {
							goals.put(g, currP);
						}
						// priority +=
					}
				}
			//}
		}

		for (Piece p : goals.keySet()) {
			priority += goals.get(p);
		}
	}

	public int compareTo(Board o) {
		// TODO Auto-generated method stub
		return priority - o.priority;
	}

}
