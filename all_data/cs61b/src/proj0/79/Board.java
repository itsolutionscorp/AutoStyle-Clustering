public class Board {

	private boolean fireTurn;
	private boolean moved;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private Piece[][] board;

	public Board(boolean shouldBeEmpty) {
		board = new Piece[8][8];
		fireTurn = true;
		moved = false;
		selectedPiece = null;
		if (shouldBeEmpty == false) {
			for (int i = 0; i < 7; i += 2) {
				board[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
				board[i][6] = new Piece(false, this, i, 6, "shield");
				board[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
				board[i][2] = new Piece(true, this, i, 2, "bomb");
				board[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
				board[i][0] = new Piece(true, this, i, 0, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > -1 && x < 8 && y > -1 && y < 8) {
			return board[x][y];
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if ((pieceAt(x, y).isFire() ^ !fireTurn) && (selectedPiece == null || !moved)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (selectedPiece != null && validMove(selectedX, selectedY, x, y) && (!moved || selectedPiece.hasCaptured())) {
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi) != null && !moved && (Math.abs(xf - xi) == 1) && (pieceAt(xi, yi).isFire() && yf - yi == 1 || !pieceAt(xi, yi).isFire() && yf - yi == -1 || pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 1)) {
			return true;
		} else if (pieceAt(xi, yi) != null && (Math.abs(xf - xi) == 2) && (pieceAt(xi, yi).isFire() && yf - yi == 2 && (pieceAt((xi + xf) / 2, yf - 1) != null) && !pieceAt((xi + xf) / 2, yf - 1).isFire() || !pieceAt(xi, yi).isFire() && yf - yi == -2 && (pieceAt((xi + xf) / 2, yf + 1) != null) && pieceAt((xi + xf) / 2, yf + 1).isFire() || pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 2 && (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() != pieceAt(xi, yi).isFire())) {
			return true;
		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (selectedPiece == null || pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn) {
			selectedPiece = pieceAt(x, y);
		} else {
			moved = true;
			selectedPiece.move(x, y);
		}
		selectedX = x;
		selectedY = y;
	}

	public void place(Piece p, int x, int y) {
		if (p == null || x < 0 || x > 7 || y < 0 || y > 7) {
			return;
		}
		if (pieceAt(x, y) != null) {
			remove(x, y);
		}
		board[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("out of bounds");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("no piece at index " + x + " " + y);
			return null;
		} else {
			Piece tempPiece = pieceAt(x, y);
			board[x][y] = null;
			return tempPiece;
		}
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		moved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						firePieces += 1;
					} else {
						waterPieces += 1;
					}
				}
			}
		}
		if (firePieces == 0) {
			if (waterPieces == 0) {
				return "No one";
			} else {
				return "Water";
			}
		} else if (waterPieces == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		Board board = new Board(true);
		board.place(new Piece(false, board, 2, 2, "pawn"), 2, 2);
		board.place(new Piece(true, board, 1, 1, "pawn"), 1, 1);
		StdDrawPlus.setScale(0.0, 16.0);
		boolean finalPosition = false;
		while (finalPosition == false) {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(8, 8, 8);
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			for (int i = 1; i < 16; i += 2) {
				for (int j = 1; j < 16; j += 2) {
					if ((i + j) % 4 == 2) {
						StdDrawPlus.filledSquare(i, j, 1);
						Piece tempPiece = board.pieceAt((i - 1) / 2, (j - 1) / 2);
						if (tempPiece != null) {
							String path = "img/";
							if (tempPiece.isBomb()) {
								path += "bomb-";
							} else if (tempPiece.isShield()) {
								path += "shield-";
							} else {
								path += "pawn-";
							}
							if (tempPiece.isFire()) {
								path += "fire";
							} else {
								path += "water";
							}
							if (tempPiece.isKing()) {
								path += "-crowned";
							}
							path += ".png";
							StdDrawPlus.picture(i, j, path, 2, 2);
						}
					}
				}
			}
			StdDrawPlus.show(0);
			if (board.winner() == null) {
				if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
					board.endTurn();
				}
				if (StdDrawPlus.mousePressed()) {
					int mouseX = ((int) StdDrawPlus.mouseX()) / 2;
					int mouseY = ((int) StdDrawPlus.mouseY()) / 2;
					if (board.canSelect(mouseX, mouseY)) {
						board.select(mouseX, mouseY);
					}
				}
			} else {
				finalPosition = true;
			}
		}
		StdDrawPlus.show(0);
		System.out.println("Winner is " + board.winner());
		return;
	}
}