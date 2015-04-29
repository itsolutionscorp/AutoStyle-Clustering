public class Board {

	private Piece[][] board = new Piece[8][8];
	private int currentPlayer = 0; // the fire player
	private Piece selectedPiece; // GGG;
	private int locationSelectedX;
	private int locationSelectedY;
	private boolean isPieceBeingSelected; // AAA;
	private boolean bEmpty;
	private boolean selectedPieceJustMoved = false;

	/*
	 * Board(boolean shouldBeEmpty) - The constructor for Board. If
	 * shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes
	 * a Board with the default configuration. Note that an empty Board could
	 * possibly be useful for testing purposes.
	 */
	public Board(boolean shouldBeEmpty) {
		bEmpty = shouldBeEmpty;
		if (shouldBeEmpty == false) {

			InitDefaultConfigBoard();
		}
	}

	private void InitDefaultConfigBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row % 2 == col % 2) {
					if (col == 0) {
						board[row][col] = new Piece(true, this, row, col,
								"pawn");
					}
					if (col == 1) {
						board[row][col] = new Piece(true, this, row, col,
								"shield");
					}
					if (col == 2) {
						board[row][col] = new Piece(true, this, row, col,
								"bomb");
					}
					if (col > 2 && col < 5) {
						board[row][col] = null;
					}
					if (col == 5) {
						board[row][col] = new Piece(false, this, row, col,
								"bomb");
					}
					if (col == 6) {
						board[row][col] = new Piece(false, this, row, col,
								"shield");
					}
					if (col == 7) {
						board[row][col] = new Piece(false, this, row, col,
								"pawn");
					}
				} else {
					board[row][col] = null;
				}
			}
		}
	}

	// Piece pieceAt(int x, int y) - Gets the piece at position (x, y) on the
	// board, or null if
	// there is no piece. If (x, y) are out of bounds, returns null.
	public Piece pieceAt(int x, int y) {

		// Check if indeces in range, if not, return false.
		if (IsXYOutOfBound(x, y)) {
			return null;
		} else {
			return board[x][y];
		}
	}

	private boolean IsXYOutOfBound(int x, int y) {

		return (x < 0) || (y < 0) || (x > 7) || (y > 7);
	}

	/*
	 * boolean canSelect(int x, int y) - Returns true if the square at (x, y)
	 * can be selected. A square with a piece may be selected if it is the
	 * corresponding player’s turn and one of the following is true:
	 * 
	 * The player has not selected a piece yet. The player has selected a piece,
	 * but did not move it.
	 * 
	 * An empty square may be selected if one of the following is true:
	 * 
	 * During this turn, the player has selected a Piece which hasn’t moved yet
	 * and is selecting an empty spot which is a valid move for the previously
	 * selected Piece. During this turn, the player has selected a Piece,
	 * captured, and has selected another valid capture destination. When
	 * performing multi-captures, you should only select the active piece once;
	 * all other selections should be valid destination points.
	 */

	// if not crowned, then can not move backward
	private boolean isMoveValid(int x, int y) {

		if (this.selectedPiece == null) {
			return true;
		} 
		else {
			if (this.locationSelectedX == x && this.locationSelectedY == y) {
				return true;
			}
			Piece p = pieceAt(x, y);
			if (p != null) {
				return true;
			}
			if (this.selectedPiece.isKing() == true) {
				return true;
			}
			if (this.selectedPiece.isFire()) {
				int ydiff = Math.abs(y - this.locationSelectedY);
				int xdiff = Math.abs(x - this.locationSelectedX);

				if ((ydiff == 1 || ydiff == 2) && (xdiff == 1 || xdiff == 2)
						&& (y > this.locationSelectedY)) {
					return true;
				}

				else {
					return false;
				}
			} else {
				int ydiff = Math.abs(y - this.locationSelectedY);
				int xdiff = Math.abs(x - this.locationSelectedX);

				if ((ydiff == 1 || ydiff == 2) && (xdiff == 1 || xdiff == 2)
						&& (y < this.locationSelectedY)) {
					return true;
				}

				else {
					return false;
				}
			}

		}

	}

	public boolean canSelect(int x, int y) {
		if ( isMoveValid(x, y) == false) {
			return false;
		}
		if (IsXYOutOfBound(x, y)) {
			return false;
		}

		Piece piece = pieceAt(x, y);

		if (selectedPiece == null) {
			// no selection

			if ((piece != null) && (piece.side() == currentPlayer)) {
				return true;
			} else {
				return false;
			}
		}

		// click an empty square
		if (piece == null) {

			if ((!this.isPieceBeingSelected) || (selectedPiece.hasCaptured())
					&& (ValidMove(locationSelectedX, locationSelectedY, x, y))) {
				return true;
			} else {
				return false;
			}
		}

		return (!this.isPieceBeingSelected)
				&& (piece.side() == this.currentPlayer);
	}

	private boolean ValidMove(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub

		if ((IsXYOutOfBound(x2, y2)) || (pieceAt(x2, y2) != null)) {
			return false;
		}

		Piece mylocalPiece = pieceAt(x1, y1);
		boolean bool = mylocalPiece.isKing();

		int i = Math.abs(x2 - x1);
		int j = y2 - y1;
		int k = Math.abs(j);

		if ((i == 1) && (!mylocalPiece.hasCaptured())) {
			if (bool) {
				return k == 1;
			}
			if (mylocalPiece.isFire()) {
				return j == 1;
			}
			return j == -1;
		}
		if (i == 2) {
			if (k != 2) {
				return false;
			}
			int m = (x2 + x1) / 2;
			int n = (y2 + y1) / 2;
			Piece mylocalPiece2 = remove(m, n);

			if ((mylocalPiece2 == null)
					|| (mylocalPiece2.side() == mylocalPiece.side())) {
				return false;
			}
			if (bool) {
				return true;
			}
			if (mylocalPiece.isFire()) {
				return j == 2;
			}
			return j == -2;
		}
		return false;
	}

	/*
	 * void select(int x, int y) - Selects the square at (x, y). This method
	 * assumes canSelect (x,y) returns true. Optionally, it is recommended to
	 * color the background of the selected square white on the GUI via the pen
	 * color function. For any piece to perform a capture, that piece must have
	 * been selected first. If you select a square with a piece, you are
	 * prepping that piece for movement. If you select an empty square (assuming
	 * canSelect returns true), you should move your most recently selected
	 * piece to that square.
	 */
	public void select(int x, int y) {

		if (pieceAt(x, y) == null) {

			selectedPiece.move(x, y);
			this.isPieceBeingSelected = true;
		}
		this.selectedPiece = pieceAt(x, y);

		// mark this location is selected
		locationSelectedX = x;
		locationSelectedY = y;
	}

	public Piece remove(int x, int y) {

		if (IsXYOutOfBound(x, y)) {
			System.out.println("Coordinates out of bounds.");
			return null;
		}

		Piece localPiece = board[x][y];

		if (localPiece == null) {
			// not piece here can not remove//
			System.out.println("No piece is here.");
			return null;
		}
		this.board[x][y] = null;
		return localPiece;

	}

	public void place(Piece p, int x, int y) {
		board[x][y] = p;
		selectedPieceJustMoved = true;
	}

	/*
	 * Returns whether or not the the current playercan end their turn. To be
	 * able to end a turn,a piece must have moved or performed a capture.
	 */
	public boolean canEndTurn() {

		// how to know the piece is moved?
		if (selectedPiece != null) {

			if ((selectedPiece.side() == this.currentPlayer)) {

				// Piece p = this.pieceAt(this.locationSelectedX,
				// this.locationSelectedY);
				if (selectedPieceJustMoved || selectedPiece.hasCaptured()) {
					// selectedPiece has been moved or has been captured
					// (exploded and killed)
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void endTurn() {
		if (canEndTurn()) {
			currentPlayer = ((currentPlayer + 1) % 2);
			isPieceBeingSelected = false;

			if (selectedPiece != null) {
				selectedPiece.doneCapturing();
				selectedPiece = null;
			}
			selectedPieceJustMoved = false;
		}
	}

	public String winner() {

		if (bEmpty == true) {
			return "No one";
		}

		int firePlayerPieces = 0;
		int waterPlayerPieces = 0;
		// loop through the board[x][y]
		// and count who has more pieces left on board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					if (board[i][j].isFire()) {
						firePlayerPieces++;
					} else {
						waterPlayerPieces++;
					}
				}
			}
		}

		// who wins
		if (firePlayerPieces > 0 && waterPlayerPieces == 0) {
			return "Fire";
		} else if (firePlayerPieces == 0 && waterPlayerPieces > 0) {
			return "Water";
		} else if (firePlayerPieces == 0 && waterPlayerPieces == 0) {
			return "No one";
		} else if ((firePlayerPieces > 0 && waterPlayerPieces > 0)) {
			return null;
		} else {
			return null;
		}
	}

	private void drawBoard(int N) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}

				if ((selectedPiece != null) && (i == locationSelectedX)
						&& (j == locationSelectedY)) {

					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}

				StdDrawPlus.filledSquare(i + 0.5D, j + 0.5D, 0.5D);

				Piece myPiece = this.board[i][j];
				if (myPiece != null) {
					StdDrawPlus.picture(i + 0.5D, j + 0.5D,
							myPiece.getImageFile(), 1.0D, 1.0D);
				}
			}
		}
	}

	private void runGame() {
		/** Monitors for mouse/spacebar presses event */
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		while (true) {
			drawBoard(N);

			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();

				if (canSelect(x, y)) {
					select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed() && (isPieceBeingSelected)) {
				if (canEndTurn()) {
					endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}

	public static void main(String[] args) {

		Board myBoard = new Board(false);
		myBoard.runGame();
	}
}