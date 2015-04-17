/** 
 *  @author Ruomeng (Michelle) Yang
 *  Worked with Dannver Wu
 */

public class Board {
	private static int boardSize = 8;
	private Piece[][] pieces = new Piece[boardSize][boardSize];
	private boolean currentPlayer = true;
	private Piece selectedPiece;
	private int selectedX = -1, selectedY = -1;
	private int firePieces, waterPieces;
	private boolean hasMoved;

	//Starts GUI supported version of game
	public static void main(String args[]) {
		StdDrawPlus.setXscale(0, boardSize);
		StdDrawPlus.setYscale(0, boardSize);
		Board b = new Board(false);
		while(true) {
			b.setUp(boardSize);
			if (StdDrawPlus.mousePressed()) {
				int clickedX = (int) StdDrawPlus.mouseX();
				int clickedY = (int) StdDrawPlus.mouseY();
				if (b.canSelect(clickedX, clickedY)) {
					b.select(clickedX, clickedY);
				}
			}
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			if (b.winner() != null) {
				if (StdDrawPlus.isNPressed()) {
					b = new Board(false);
				}
			}
			StdDrawPlus.show(20);
		}
	}

	//Constructor
	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			initializePieces();
		}
	}

	//Sets up the board
	private void setUp(int size) {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == selectedX && y == selectedY && selectedPiece != null) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if ((x + y) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				if (pieceAt(x, y) != null) {
					StdDrawPlus.picture(x + .5, y + .5, "img/" + type(pieceAt(x,y)) + correctPNG(pieceAt(x, y)), 1, 1);
				}
			}
		}
	}

	//Initializes the pieces within the board
	private void initializePieces() {
		for (int x = 0; x < boardSize && x >= 0; x++) {
			for (int y = 0; y < boardSize && y >= 0; y++) {
				boolean shouldBeFire = true;
				if (y >= boardSize - 3) {
					shouldBeFire = false;
				}
				if ((y == 0 && x % 2 == 0) || (y == boardSize - 1 && x % 2 == 1)) {
					place(new Piece(shouldBeFire, this, x, y, "pawn"), x, y);
				} else if ((y == 1 && x % 2 == 1) || (y == boardSize - 2 && x % 2 == 0)) {
					place(new Piece(shouldBeFire, this, x, y, "shield"), x, y);
				} else if ((y == 2 && x % 2 == 0) || (y == boardSize - 3 && x % 2 == 1)) {
					place(new Piece(shouldBeFire, this, x, y, "bomb"), x, y);
				}
			}
		}
	}

	//Returns correct type of Piece
	private String type(Piece p) {
		String result = "";
		if (p.isBomb()) {
			result = "bomb";
		} else if (p.isShield()) {
			result = "shield";
		} else {
			result = "pawn";
		}
		return result;
	}

	//Returns correct address of PNG image
	private String correctPNG(Piece p) {
		String result = "-";
		if (p.isFire()) {
			result += "fire";
		} else {
			result += "water";
		}
		if (p.isKing()) {
			result += "-crowned";
		}
		return (result + ".png");
	}

	//Returns if within bounds
	private boolean withinBounds(int x, int y) {
		return (x < boardSize && y < boardSize && x >= 0 && y >= 0);
	}

	//Gets piece at (x, y). Returns null if there is no piece or coordinates out of bound
	public Piece pieceAt(int x, int y) {
		if (withinBounds(x, y))	{
			return pieces[x][y];
		}
		return null;
	}

	//Returns if square at (x, y) can be selected
	public boolean canSelect(int x, int y) {
		Piece testPiece = pieceAt(x, y);
		if (testPiece != null && currentPlayer == testPiece.isFire()) {
			return !hasMoved;
		} else if (testPiece == null && selectedPiece != null) {
			return validMove(selectedX, selectedY, x, y);
		}
		return false;
	}

	//Returns if move is valid for captures
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece midPiece = pieceAt((xi + xf) / 2, (yi + yf) / 2);
		if (pieceAt(xf, yf) != null) {
			return false;
		} else if (Math.abs(xi - xf) == 2 && midPiece != null && midPiece.isFire() != currentPlayer) {
			if (pieceAt(xi, yi).isKing()) {
				return (Math.abs(yi - yf) == 2);
			} else if (currentPlayer) {
				return (yf - yi == 2);
			} else {
				return (yi - yf == 2);
			}
		} else if (Math.abs(xi - xf) == 1 && !hasMoved) {
			if (pieceAt(xi, yi).isKing()) {
				return (Math.abs(yi - yf) == 1);
			} else if (currentPlayer) {
				return (yf - yi == 1);
			} else {
				return (yi - yf == 1);
			}
		}
		return false;
	}

	//Selects the piece at (x,y) if possible
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		selectedX = x;
		selectedY = y;
		if (p == null && selectedPiece != null) {
			selectedPiece.move(x, y);
			hasMoved = true;
		}
		selectedPiece = pieceAt(x, y);
	}

	//Places p at (x, y) if within bound
	public void place(Piece p, int x, int y) {
		if (withinBounds(x, y)) {
			pieces[x][y] = p;
		}
		if (p.isFire()) {
			firePieces += 1;
		} else {
			waterPieces += 1;
		}
	}

	//Remove piece if within bounds
	public Piece remove(int x, int y) {
		Piece removed = pieceAt(x, y);
		if (!withinBounds(x, y)) {
			System.out.println("Out of bounds.");
		} else if (removed == null) {
			System.out.println("No piece to remove.");
		} else {
			if (removed.isFire()) {
				firePieces -= 1;
			} else {
				waterPieces -= 1;
			}
			pieces[x][y] = null;
		}
		return removed;
	}

	//Returns if current player can end his/her turn
	public boolean canEndTurn() {
		return hasMoved;
	}

	//Handles switching players
	public void endTurn() {
		currentPlayer = !currentPlayer;
		hasMoved = false;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
			selectedPiece = null;
		}
		selectedX = -1;
		selectedY = -1;
		if (winner() != null) {
			setUp(boardSize);
		}
	}

	//Returns winner of game
	public String winner() {
		if (waterPieces == 0 && firePieces > 0) {
			return "Fire";
		} else if (firePieces == 0 && waterPieces > 0) {
			return "Water";
		} else if (firePieces == 0 && waterPieces == 0) {
			return "No one";
		}
		return null;
	}
}