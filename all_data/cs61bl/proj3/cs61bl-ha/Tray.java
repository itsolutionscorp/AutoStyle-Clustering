import java.util.ArrayList;
import java.util.Arrays;

public class Tray {
	private int myYLength;
	private int myXLength;
	private Piece[][] myPieces; // Piece[y][x]
	private int[] moveMade;
	private Tray parent;
	private ArrayList<Tray> possibleMoves;
	private ArrayList<Piece> pieces;

	public Tray(int ylength, int xlength) {
		myYLength = ylength;
		myXLength = xlength;
		myPieces = new Piece[ylength][xlength];
		moveMade = null;
		parent = null;
		possibleMoves = new ArrayList<Tray>();
		pieces = new ArrayList<Piece>();
	}

	public Tray(int ylength, int xlength, int[] move, Tray p) {
		myYLength = ylength;
		myXLength = xlength;
		myPieces = new Piece[ylength][xlength];
		moveMade = move;
		parent = p;
		possibleMoves = new ArrayList<Tray>();
		pieces = new ArrayList<Piece>();
	}

	public void add(int[] coordinates) {
		if (coordinates[0] < 0 || coordinates[1] < 0 || coordinates[2] < 0
				|| coordinates[3] < 0 || coordinates[0] >= myYLength
				|| coordinates[2] >= myYLength || coordinates[1] >= myXLength
				|| coordinates[3] >= myXLength) {
			throw new IllegalArgumentException();
		}
		int y = coordinates[0];
		int x = coordinates[1];
		Piece p = new Piece(y, x, coordinates);
		pieces.add(p);
		for (int i = y; i < coordinates[2] + 1; i++) {
			for (int j = x; j < coordinates[3] + 1; j++) {
				myPieces[i][j] = p;
			}
		}
	}

	public int[] getMoveMade() {
		return moveMade;
	}

	public Piece[][] getPieces() {
		return myPieces;
	}

	public Piece getPiece(int y, int x) {
		return myPieces[y][x];
	}

	public Tray getParent() {
		return parent;
	}

	public ArrayList<Tray> neighbors() {
		return possibleMoves;
	}

	public boolean trayMatch(Tray goal) {
		for (int i = 0; i < myYLength; i++) {
			for (int j = 0; j < myXLength; j++) {
				if (goal.getPieces()[i][j] != null) {
					if (getPiece(i, j) != null) {
						Piece p1 = goal.getPiece(i, j);
						Piece p2 = getPiece(i, j);
						if (!p1.equals(p2)) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void getMoves() {
		for (Piece p : pieces) {
			if (canMoveUp(p)) {
				possibleMoves.add(moveUp(p));
			}
			if (canMoveDown(p)) {
				possibleMoves.add(moveDown(p));
			}
			if (canMoveLeft(p)) {
				possibleMoves.add(moveLeft(p));
			}
			if (canMoveRight(p)) {
				possibleMoves.add(moveRight(p));
			}
		}
	}

	public Tray moveUp(Piece p) {
		int[] coords = p.getCoords();
		int[] newCoords = new int[4];
		newCoords[0] = coords[0] - 1;
		newCoords[1] = coords[1];
		newCoords[2] = coords[2] - 1;
		newCoords[3] = coords[3];
		int[] move = { coords[0], coords[1], newCoords[0], newCoords[1] };
		return move(p, newCoords, move);
	}

	public Tray moveDown(Piece p) {
		int[] coords = p.getCoords();
		int[] newCoords = new int[4];
		newCoords[0] = coords[0] + 1;
		newCoords[1] = coords[1];
		newCoords[2] = coords[2] + 1;
		newCoords[3] = coords[3];
		int[] move = { coords[0], coords[1], newCoords[0], newCoords[1] };
		return move(p, newCoords, move);
	}

	public Tray moveLeft(Piece p) {
		int[] coords = p.getCoords();
		int[] newCoords = new int[4];
		newCoords[0] = coords[0];
		newCoords[1] = coords[1] - 1;
		newCoords[2] = coords[2];
		newCoords[3] = coords[3] - 1;
		int[] move = { coords[0], coords[1], newCoords[0], newCoords[1] };
		return move(p, newCoords, move);
	}

	public Tray moveRight(Piece p) {
		int[] coords = p.getCoords();
		int[] newCoords = new int[4];
		newCoords[0] = coords[0];
		newCoords[1] = coords[1] + 1;
		newCoords[2] = coords[2];
		newCoords[3] = coords[3] + 1;
		int[] move = { coords[0], coords[1], newCoords[0], newCoords[1] };
		return move(p, newCoords, move);
	}

	private Tray move(Piece p, int[] coordinates, int[] move) {
		Tray afterMove = new Tray(myYLength, myXLength, move, this);
		for (Piece piece : pieces) {
			if (piece == p) {
				afterMove.add(coordinates);
			} else {
				afterMove.add(piece.getCoords());
			}
		}
		return afterMove;
	}

	public boolean canMoveUp(Piece p) {
		int x = p.getX();
		int y = p.getY();
		if (y - 1 >= 0) {
			for (int i = 0; i < p.width(); i++) {
				if (myPieces[y - 1][x + i] != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean canMoveDown(Piece p) {
		int x = p.getX();
		int y = p.getY();
		if (y + p.height() < myYLength) {
			for (int i = 0; i < p.width(); i++) {
				if (myPieces[y + p.height()][x + i] != null) {
					return false;
				}
			}
			return true;

		}
		return false;
	}

	public boolean canMoveLeft(Piece p) {
		int x = p.getX();
		int y = p.getY();
		if (x - 1 >= 0) {
			for (int i = 0; i < p.height(); i++) {
				if (myPieces[y + i][x - 1] != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean canMoveRight(Piece p) {
		int x = p.getX();
		int y = p.getY();
		if (x + p.width() < myXLength) {
			for (int i = 0; i < p.height(); i++) {
				if (myPieces[y + i][x + p.width()] != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(myPieces);
	}

	@Override
	public boolean equals(Object o) {
		Tray compare = (Tray) o;
		Piece[][] compareBoard = compare.getPieces();
		for (int i = 0; i < myYLength; i++) {
			for (int j = 0; j < myXLength; j++) {
				if (myPieces[i][j] != null) {
					if (compareBoard[i][j] != null) {
						if (!myPieces[i][j].equals(compareBoard[i][j])) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	private class Piece {
		private int[] myCoords;
		private int myX, myY;

		public Piece(int y, int x, int[] coordinates) {
			myCoords = coordinates;
			myY = y;
			myX = x;
		}

		public int height() {
			return myCoords[2] - myY + 1;
		}

		public int width() {
			return myCoords[3] - myX + 1;
		}

		public int getY() {
			return myY;
		}

		public int getX() {
			return myX;
		}

		public int[] getCoords() {
			return myCoords;
		}

		@Override
		public int hashCode() {
			StringBuilder hash = new StringBuilder("");
			hash.append(myY);
			hash.append(height());
			hash.append(myX);
			hash.append(width());
			return Integer.parseInt(hash.toString());
		}

		@Override
		public boolean equals(Object o) {
			Piece p = (Piece) o;
			return myX == p.getX() && myY == p.getY() && height() == p.height()
					&& width() == p.width();
		}
	}
}