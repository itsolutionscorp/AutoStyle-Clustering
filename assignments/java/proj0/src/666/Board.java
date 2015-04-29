/**
  * Author: Guangzhao Yang
  * Login: cs61b-azq
  */

public class Board{
	// Store all pieces on the board.
	private Piece[][] pieces = new Piece[8][8];

	// Store the selected piece's location.
	private int selectedX = -1;
	private int selectedY = -1;

	// Whether the selected piece has been moved or not.
	private boolean hasMoved = false;

	// Decide whose turn it is.
	private boolean currentFire = true;


	// Initialize the board.
	public Board (boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = new Piece (true, this, i, j, "pawn");
						} else if (j == 1) {
							pieces[i][j] = new Piece (true, this, i, j, "shield");
						} else if (j == 2) {
							pieces[i][j] = new Piece (true, this, i, j, "bomb");
						} else if (j == 5) {
							pieces[i][j] = new Piece (false, this, i, j, "bomb");
						} else if (j == 6) {
							pieces[i][j] = new Piece (false, this, i, j, "shield");
						} else if (j == 7) {
							pieces[i][j] = new Piece (false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}

	// Draw the board.
	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	// Draw the pieces.
	private void drawPieces() {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		String type;
		String color;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					// Which type?
					if (pieces[i][j].isBomb()) {
						type = "bomb";
					} else if (pieces[i][j].isShield()) {
						type = "shield";
					} else {
						type = "pawn";
					}
					// Which color?
					if (pieces[i][j].isFire()) {
						color = "fire";
					} else {
						color = "water";
					}
					// Has the piece been crowned?
					if (pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + type + "-" + color + "-crowned.png", 1, 1);
					} else if (pieces[i][j].isKing() == false) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + type + "-" + color + ".png", 1, 1);
					}
				}
			}
		}
	}

	// Get the piece at a certain position (x, y).
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		} else if (pieces[x][y] == null) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	// Returns true if the square at (x, y) can be selected.
	public boolean canSelect(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		} else if (pieces[x][y] != null) {
			if (currentFire == pieces[x][y].isFire()) {
				if (selectedX < 0 && selectedY < 0) {
					return true;
				} else if (pieces[selectedX][selectedY] != null && hasMoved == false) {
					return true;
				} else {
					return false;
				}
			} else { // if (currentFire != pieces[x][y].isFire())
				return false;
			}
		} else { // if (pieces[x][y] == null)
			if (selectedX < 0 && selectedY < 0) {
				return false;
			} else if (pieces[selectedX][selectedY] != null) {
				if (hasMoved == false && (validMove(selectedX, selectedY, x, y) || validCapture(selectedX, selectedY, x, y))) {
					return true;
				} else if (hasMoved && pieces[selectedX][selectedY].hasCaptured() && validCapture(selectedX, selectedY, x, y)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	// Determine whether the piece at (xi, yi) can move (not capture) to (xf, yf).
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 0 || xi > 7 || yi < 0 || yi > 7 || xf < 0 || xf > 7 || yf < 0 || yf > 7) {
			return false;
		} else if (pieceAt(xi, yi) == null || pieceAt(xf, yf) != null || pieceAt(xi, yi).hasCaptured()) {
			return false;
		} else {
			Piece currentPiece = pieceAt(xi, yi);
			if (currentPiece.isFire()) {
				if (currentPiece.isKing()) {
					if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
						return true;
					} else {
						return false;
					}
				} else { // if (currentPiece.isKing() == false)
					if (Math.abs(xf - xi) == 1 && yf == yi + 1) {
						return true;
					} else {
						return false;
					}
				}
			} else { // if (currentPiece.isFire() == false)
				if (currentPiece.isKing()) {
					if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
						return true;
					} else {
						return false;
					}
				} else { // if (currentPiece.isKing() == false)
					if (Math.abs(xf - xi) == 1 && yf == yi - 1) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

	// Determine whether the piece at (xi, yi) can capture to (xf, yf).
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		if (xi < 0 || xi > 7 || yi < 0 || yi > 7 || xf < 0 || xf > 7 || yf < 0 || yf > 7) {
			return false;
		} else if (pieceAt(xi, yi) == null || pieceAt(xf, yf) != null) {
			return false;
		} else {
			Piece currentPiece = pieceAt(xi, yi);
			int xMiddle = (xi + xf) / 2;
			int yMiddle = (yi + yf) / 2;
			if (currentPiece.isFire()) {
				if (currentPiece.isKing()) {
					if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieceAt(xMiddle, yMiddle) != null && pieceAt(xMiddle, yMiddle).isFire() == false) {
						return true;
					} else {
						return false;
					}
				} else { // if (currentPiece.isKing() == false)
					if (Math.abs(xf - xi) == 2 && yf == yi + 2 && pieceAt(xMiddle, yMiddle) != null && pieceAt(xMiddle, yMiddle).isFire() == false) {
						return true;
					} else {
						return false;
					}
				}
			} else { // if (currentPiece.isFire() == false)
				if (currentPiece.isKing()) {
					if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieceAt(xMiddle, yMiddle) != null && pieceAt(xMiddle, yMiddle).isFire()) {
						return true;
					} else {
						return false;
					}
				} else { // if (currentPiece.isKing() == false)
					if (Math.abs(xf - xi) == 2 && yf == yi - 2 && pieceAt(xMiddle, yMiddle) != null && pieceAt(xMiddle, yMiddle).isFire()) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

	// Select the square at location (x, y).
	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selectedX = x;
			selectedY = y;
		} else if (pieces[x][y] == null) {
			pieceAt(selectedX, selectedY).move(x, y);
			selectedX = x;
			selectedY = y;
			hasMoved = true;
		}
	}

	// Place a piece at location (x, y).
	public void place(Piece p, int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7 || p == null) {
		} else {
			pieces[x][y] = p;
		}
	}

	// Remove a piece from the board. Return that piece.
	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("The position is out of bounds!");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("No piece can be removed!");
			return null;
		} else {
			Piece tempPiece = pieceAt(x, y);
			pieces[x][y] = null;
			return tempPiece;
		}
	}

	// Return whether or not the current player can end his turn.
	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		} else {
			return false;
		}
	}

	// End the current player's turn.
	public void endTurn() {
		if (currentFire) {
			currentFire = false;
		} else if (currentFire == false) {
			currentFire = true;
		}
		hasMoved = false;
		if (pieces[selectedX][selectedY] != null) {
			pieces[selectedX][selectedY].doneCapturing();
		}
		selectedX = -1;
		selectedY = -1;
	}

	// Decide whether there is a winner at the end of each turn.
	public String winner() {
		int totalFirePieces = 0;
		int totalWaterPieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						totalFirePieces += 1;
					} else {
						totalWaterPieces += 1;
					}
				}
			}
		}
		if (totalFirePieces == 0 && totalWaterPieces == 0) {
			return "No one";
		} else if (totalFirePieces == 0 && totalWaterPieces > 0) {
			return "Water";
		} else if (totalFirePieces > 0 && totalWaterPieces == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	// Game on!
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
	    StdDrawPlus.setYscale(0, 8);
	    Board board = new Board(false);
		while (true) {
			board.drawBoard();
			board.drawPieces();
			StdDrawPlus.show(100);
			if (StdDrawPlus.mousePressed()) {
				int clickX = (int) StdDrawPlus.mouseX();
				int clickY = (int) StdDrawPlus.mouseY();
				if (board.canSelect(clickX, clickY)) { 
					board.select(clickX, clickY);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(clickX + 0.5, clickY + 0.5, 0.5);
					StdDrawPlus.show(100);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
				}
			}
			board.winner();
		}
	}
}