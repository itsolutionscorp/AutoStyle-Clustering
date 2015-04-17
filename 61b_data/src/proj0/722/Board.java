public class Board {
	
	private Piece[][] board;
	private static final int size = 8;
	private boolean hasMoved = false;
	private int player = 0; // true when FIRE turn, false if WATER
	private int selectedX;
	private int selectedY;
	private Piece chosenPiece;
	// private int fireRemaining;
	// private int waterRemaining;
	

	public static void main(String args[]) {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);

		Board board = new Board(false);

		while (true) {
			board.drawBoard();
			board.drawPieces();

			if (StdDrawPlus.mousePressed()) {

				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (board.canSelect(x, y)) {
					
					// Testing.
					System.out.println("Player is " + board.player);
					System.out.println("SelectedX is " + board.selectedX);
					System.out.println("SelectedY is " + board.selectedY);

					if (board.chosenPiece != null)
						System.out.println("ChosenPiece is not null");

					board.select(x, y);
				}
			}

			else if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
					board.endTurn();
			}


			if (board.winner() != null) {
				System.out.println(board.winner());
				break;
			}
			StdDrawPlus.show(20);
		}
	}

	private void drawBoard() {

		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
	}

	private void drawPieces() {

		String pieceType = null;
		String team = null;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if ((i + j) % 2 == 0) {

					if (board[i][j] != null) {
						if (board[i][j].isFire()) {
							team = "fire";
						} else if (board[i][j].isFire() == false) {
							team = "water";
						}

						if (board[i][j].isShield()) {
							pieceType = "shield";
						} else if (board[i][j].isBomb()) {
							pieceType = "bomb";
						} else {
							pieceType = "pawn";
						}

						if (board[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-" + team + "-crowned.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-" + team + ".png", 1, 1);
						}
					}
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty) {

		if (shouldBeEmpty) {
			board = new Piece[size][size];
		} else {
			board = new Piece[size][size];
			for (int i = 0; i < size; i += 1) {
				for (int j = 0; j < size; j += 1) {
					if ((i + j) % 2 == 0) {
						if (j == 0) {
							board[i][j] = new Piece(true, this, i, j, "pawn");
						} else if (j == 1) {
							board[i][j] = new Piece(true, this, i, j, "shield");
						} else if (j == 2) {
							board[i][j] = new Piece(true, this, i, j, "bomb");
						} else if (j == 5) {
							board[i][j] = new Piece(false, this, i, j, "bomb");
						} else if (j == 6) {
							board[i][j] = new Piece(false, this, i, j, "shield");
						} else if (j == 7) {
							board[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}

	/** Gets the piece at position (x, y) on the board, or null if
	/*  there is no piece. If (x, y) are out of bounds, returns null. */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0)
			return null;
		else
			return board[x][y];
	}
	/** Returns true if there is a piece the piece at (x, y)
	/*  and it can be selected. */
	public boolean canSelect(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0)
			return false;

		Piece clickedSquare = pieceAt(x, y);

		/** Verifies that the player has clicked on a piece and is ready 
		/*  to move it to a desired location. Must be player's turn. */
		if (clickedSquare != null) {

			if (clickedSquare.side() == this.player) {
				if (this.chosenPiece == null) {
					if (!hasMoved)
						return true;
				} else if (!hasMoved) {
					return true;
				} else if (this.chosenPiece.hasCaptured()) {
					return true;
				}
			}

		} else {
			if (this.chosenPiece != null) {
				if (!hasMoved) {
					if (validMove(this.selectedX, this.selectedY, x, y))
						return true;
				} else if (validMove(this.selectedX, this.selectedY, x, y)) {
					if (this.chosenPiece.hasCaptured())
						return true;
				} else if (this.chosenPiece != null && this.chosenPiece.hasCaptured() && validMove(this.selectedX, this.selectedY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/** Returns true if the piece at (xi, yi) can either move to
	/*  (xf, yf) or capture to (xf, yf) in a valid fashion compatible
	/*  with the rules. */
	private boolean validMove(int xi, int yi, int xf, int yf) {

		if (xf > 7 || yf > 7 || xf < 0 || yf < 0)
			return false;
		if (pieceAt(xf, yf) != null)
			return false;

		Piece selectedPiece = pieceAt(xi, yi);

		if (Math.abs(xf - xi) == 1) {

			if (!(selectedPiece.hasCaptured())) {

				if (selectedPiece.isKing()) {
					if (Math.abs(yf - yi) == 1) {
						return true;
					} else {
						return false;
					}
				} else if (selectedPiece.isFire()) {
					if ((yf - yi) == 1) {
						return true;
					} else {
						return false;
					}
				} else if (selectedPiece.isFire() != true) {
					if ((yf - yi) == -1) {
						return true;
					} else {
						return false;
					}
				}
			}

		} else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {

			Piece potentialCapture = pieceAt((xi + xf) / 2, (yi + yf) / 2);

			if (potentialCapture != null) {

				if (potentialCapture.isFire() == selectedPiece.isFire()) {
					return false;
				}

				if (selectedPiece.isKing()) {
					return true;
				} else if (selectedPiece.hasCaptured()) {
					if (selectedPiece.isFire() && (yf - yi) == 2) {
						return true;
					} else if (!selectedPiece.isFire() && (yf - yi) == -2) {
						return true;
					} else {
						return false;
					}
				} else if (selectedPiece.isFire()) {
					if ((yf - yi) == 2) {
						return true;
					} else {
						return false;
					}
				} else {
					if ((yf - yi) == -2) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {

		if (!(x > 7 || y > 7 || x < 0 || y < 0)) {

			Piece clickedPiece = pieceAt(x, y);


			if (clickedPiece == null) {
				if (this.chosenPiece != null) {
					this.chosenPiece.move(x, y);
					this.hasMoved = true;
				}
			} else if (clickedPiece != null) {
				this.chosenPiece = clickedPiece;
			}
			this.selectedX = x;
			this.selectedY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (!(x > 7 || y > 7 || x < 0 || y < 0)) {
			if (p != null) {
				if (pieceAt(x, y) != null)
					this.remove(x, y);
				board[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("Your input is out of bounds.");
			return null;
		} else if (board[x][y] == null) {
			System.out.println("There is no piece to remove.");
			return null;
		} else {
			Piece removed = this.pieceAt(x, y);
			board[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn() {
		return this.hasMoved;
	}

	public void endTurn() {
		this.player = (this.player + 1) % 2;
		this.hasMoved = false;
		for (int i = 0; i < size; i += 1) {
			for (int j = 0; j < size; j += 1) {
				if (board[i][j] != null)
					board[i][j].doneCapturing();
			}
		}
		this.chosenPiece = null;
	}

	public String winner() {
		int fireRemaining = 0;
		int waterRemaining = 0;
		for (int i = 0; i < size; i += 1) {
			for (int j = 0; j < size; j += 1) {
				if (board[i][j] != null) {
					if (board[i][j].isFire()) {
						fireRemaining += 1;
					} else {
						waterRemaining += 1;
					}
				}
			}
		}

		if (fireRemaining == 0 && fireRemaining == waterRemaining) {
			return "No one";
		} else if (waterRemaining == 0) {
			return "Fire";
		} else if (fireRemaining == 0) {
			return "Water";
		} else {
			return null;
		}
	}
}