
public class Board {

	/**
	 * @param args
	 */
	private Piece[][] pieces;
	private boolean playersTurn; //true if it is fire's turn, false if it is water's turn
	private boolean pieceSelected;
	private boolean pieceMoved;
	private int selectedX;
	private int selectedY;
	private Piece selectedPiece;
	private static final int BOARD_SIZE = 8;
	private static final String WINNER_FIRE = "Fire";
	private static final String WINNER_WATER = "Water";

	public static void main(String[] args) {
		Board b = new Board(false);
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		/** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
		while(true) {
			drawBoard(N, b);         
			StdDrawPlus.show(10);
			if (StdDrawPlus.mousePressed()) {
				int xsel = (int) StdDrawPlus.mouseX();
				int ysel = (int) StdDrawPlus.mouseY();
				if (b.canSelect(xsel, ysel)) {
					b.select(xsel, ysel);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
		}
	}

	private static void drawBoard(int N, Board b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (b.pieceSelected) {
					if (b.selectedX == i && b.selectedY == j)
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				String filename;

				if (b.pieceAt(i, j) != null) {
					if (b.pieceAt(i, j).isBomb()) {
						if (b.pieceAt(i, j).isFire())
							filename = "img/bomb-fire.png";
						else
							filename = "img/bomb-water.png";

					}
					else if (b.pieceAt(i, j).isShield()) {
						if (b.pieceAt(i, j).isFire())
							filename = "img/shield-fire.png";
						else
							filename = "img/shield-water.png";

					}
					else {
						if (b.pieceAt(i, j).isFire())
							filename = "img/pawn-fire.png";
						else
							filename = "img/pawn-water.png";
					}
					if (b.pieceAt(i, j).isKing()) {
						filename = filename.replaceAll(".png", "-crowned.png");
					}
					StdDrawPlus.picture(i + .5, j + .5, filename, 1, 1);
				}
			}
		}
		
	}

	public Board (boolean shouldBeEmpty) {
		pieces = new Piece[BOARD_SIZE][BOARD_SIZE]; 
		if (shouldBeEmpty) {
			for (int xC = 0; xC < BOARD_SIZE; xC++) {
				for (int yC = 0; yC < BOARD_SIZE; yC++) {
					pieces[xC][yC] = null;
				}
			}
		}
		else {
			for (int xC = 0; xC < BOARD_SIZE; xC+=2) {
				pieces[xC][0] = new Piece(true, this, xC, 0, "pawn");
				pieces[xC+1][1] = new Piece(true, this, xC+1, 1, "shield");
				pieces[xC][2] = new Piece(true, this, xC, 2, "bomb");
				pieces[xC+1][BOARD_SIZE-3] = new Piece(false, this, xC+1, 5, "bomb");
				pieces[xC][BOARD_SIZE-2] = new Piece(false, this, xC, 6, "shield");
				pieces[xC+1][BOARD_SIZE-1] = new Piece(false, this, xC+1, 7, "pawn");
			}
		}
		playersTurn = true; //fire starts the game
		pieceSelected = false;
		pieceMoved = false;
		selectedPiece = null;
	}

	/**
	 * Returns the piece at the (x, y) position.
	 * Returns null if index out of bounds.
	 * 
	 * @param x the x coordinate of the piece, increasing left to right
	 * @param y the y coordinate of the piece, increasing bottom to top
	 * @return the piece, or null
	 */
	public Piece pieceAt(int x, int y) {
		if (!(x > 7 || y > 7 || x < 0 || y < 0) && pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}
	}

	/**
	 * Returns whether or not you can select this checker box.
	 * Returns true if selecting a new piece that is your own, 
	 * or if performing a legal move on an already selected piece.
	 * 
	 * @param x x coordinate of the checker box
	 * @param y y coordinate of the checker box
	 * @return whether or not you can perform actions on this checker box
	 */
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			if (playersTurn == p.isFire() && !pieceMoved) return true;
		}
		else {
			if (validMove(selectedX, selectedY, x, y) && pieceSelected) return true;
		}
		return false;
	}

	/**
	 * Helper method for canSelect. Returns whether a move is valid or not.
	 * A move is NOT valid if:
	 * 		1. (xf, yf) out of bounds
	 * 		2. (xf, yf) is occupied
	 * 		3. (xi, yi) is empty
	 * A move is valid if, in addition to satisfying none of the above conditions,
	 * satisfies 1 && 2 or 1 && 3 of these conditions:
	 * 		1. (xf, yf) is empty
	 * 		2. abs(xi-xf) == 1 and abs(yi-yf) == 1
	 * 		3. abs(xi-xf) == 2 and abs(yi-yf) == 2, and there is an enemy piece in
	 * 			between at ((xi+xf) >>> 1, (yi+yf) >>> 1)
	 * Also performs king checking. A king may move backwards or
	 * forwards, but dirty peasants can only move in one way:
	 * fire can only move up (yf > yi), and water can only move down (yf < yi).
	 * 
	 * @param xi where piece is moving from, x
	 * @param yi where piece is moving from, y
	 * @param xf where piece is moving to, x
	 * @param yf where piece is moving to, y
	 * @return whether this move is valid or not
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf > 7 || yf > 7) return false;
		if (pieceAt(xf, yf) != null) return false;
		if (pieceAt(xi, yi) == null) return false;
		if (!pieceAt(xi, yi).isKing()) {
			if (pieceAt(xi, yi).isFire()) {
				if (yi > yf) return false;
			}
			else {
				if (yf > yi) return false;
			}
		}

		if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) return true;
		return isValidCapturingMove(xi, yi, xf, yf);
	}

	/**
	 * Helper method for validMove. Returns whether a move from
	 * (xi, yi) to (yi, yf) is a valid capturing move.
	 * @return whether this is a valid capturing move or not
	 */
	private boolean isValidCapturingMove(int xi, int yi, int xf, int yf) {
		if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
			int meanX = (xi + xf) >>> 1;
			int meanY = (yi + yf) >>> 1;
			Piece meanP = pieces[meanX][meanY];
			if (meanP != null && meanP.isFire() != playersTurn) return true;
		}
		return false;
	}

	/**
	 * Returns whether a piece at (xi, yi) can capture any piece at all.
	 * Puts all two (if king, four) possible locations that this piece could capture in
	 * a list, then checks if any of them are enemies. If any of them are, return true.
	 * @param xi starting x-coordinate
	 * @param yi starting y-coordinate
	 * @return if this piece is able to capture another piece
	 */
	private boolean canCapture(int xi, int yi) {
		Piece p = pieceAt(xi, yi);
		if (p == null) return false;
		Piece[] availableVictims = 
			{pieceAt(xi - 1, yi - 1), pieceAt(xi + 1, yi - 1),
				pieceAt(xi - 1, yi + 1), pieceAt(xi + 1, yi + 1)};
		if (!p.isKing()) {
			if (p.isFire()) {
				availableVictims[0] = null;
				availableVictims[1] = null;
			}
			else {
				availableVictims[2] = null;
				availableVictims[3] = null;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (availableVictims[i] != null &&
					availableVictims[i].isFire() != p.isFire()) {
				
				switch (i) {
				case 0: return validMove(xi, yi, xi-2, yi-2);
				case 1: return validMove(xi, yi, xi+2, yi-2);
				case 2: return validMove(xi, yi, xi-2, yi+2);
				case 3: return validMove(xi, yi, xi+2, yi+2);
				}
			}
		}
		return false;
	}

	/**
	 * Selects a checkerboard box to perform an action on.
	 * This method should always be preceded with a check from canSelect().
	 * If box is empty, move selected piece to box.
	 * If box is not empty, select the piece in the box.
	 * Deselect p. If p can capture again, and is not a bomb, select p again.
	 * @param x
	 * @param y
	 */
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) {
			if (selectedPiece != null) {
				selectedPiece.move(x, y);
				pieceMoved = true;
				if (selectedPiece.hasCaptured() && canCapture(x, y)) {
					selectPiece(x, y);
				}
				else {
					pieceSelected = false;
					selectedPiece = null;
				}
			}
		}
		else {
			selectPiece(x, y);
		}
	}

	/**
	 * Puts p in the correct pieces[][] box.
	 * Does nothing if out of bounds or p is null.
	 * @param p the piece to place
	 * @param xf x-coordinate to move it to
	 * @param yf y-coordinate to move it to
	 */
	public void place(Piece p, int xf, int yf) {
		if (p == null || xf > 7 || yf > 7 || xf < 0 || yf < 0) {
			return;
		}
		pieces[xf][yf] = p;
	}

	private void selectPiece(int x, int y) {
		pieceSelected = true;
		selectedPiece = this.pieceAt(x, y);
		selectedX = x;
		selectedY = y;
	}

	/**
	 * Removes a Piece from the Board and returns it.
	 * @param x x-coordinate of Piece to remove
	 * @param y y-coordinate of Piece to remove
	 * @return removed Piece
	 */
	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("you went out of bounds");
		} 
		if (pieceAt(x, y) == null) {
			System.out.println("there's nothing there");
		}
		Piece removed = pieces[x][y];
		pieces[x][y] = null;
		return removed;
	}
	public boolean canEndTurn() {
		return pieceMoved;
	}

	/**
	 * Ends the current player's turn.
	 * Initialize all pieceMoved, selectedPiece, pieceSelected and hasCaptured values.
	 */
	public void endTurn() {
		pieceMoved = false;
		selectedPiece = null;
		pieceSelected = false;
		for (int xc = 0; xc < BOARD_SIZE; xc++) {
			for (int yc = 0; yc < BOARD_SIZE; yc++) {
				if (pieceAt(xc, yc) != null)
					pieceAt(xc, yc).doneCapturing();
			}
		}
		playersTurn = !playersTurn;
	}

	/**
	 * Returns a String representation of the winner.
	 * If there are no water pieces remaining, fire has won.
	 * If there are no fire pieces remaining, water has won.
	 * If there are both or no pieces remaining, no one has won and null is returned.
	 * @return the winner
	 */
	public String winner() {
		int fireCount = 0;
		int waterCount = 0;

		for (int xC = 0; xC < BOARD_SIZE; xC++) {
			for (int yC = 0; yC < BOARD_SIZE; yC++) {
				if (pieces[xC][yC] != null) {
					if (pieces[xC][yC].isFire()) {
						fireCount++;
					}
					else {
						waterCount++;
					}
				}
			}
		}
		if (fireCount == 0 && waterCount == 0) {
			return null;
		}
		if (fireCount == 0) {
			return WINNER_WATER;
		}
		if (waterCount == 0) {
			return WINNER_FIRE;
		}
		return null;
	}
}
