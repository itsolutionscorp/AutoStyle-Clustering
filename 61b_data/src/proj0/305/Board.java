/**
 * Board class for the Checkers project.
 * 
 * @author Justin Hwang
 * @author cs61b-alt
 */
public class Board {
	private Piece[][] pieces;
	private static final int BOARD_SIZE = 8;
	private boolean hasSelected, hasMoved, hasCaptured = false;
	private boolean fireTurn = true;
	private Piece selected;
	private int selectedX, selectedY;

	/**
	 * starts a GUI supported version of the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, BOARD_SIZE);
		StdDrawPlus.setYscale(0, BOARD_SIZE);
		Board b = new Board(false);
		b.drawBoard(BOARD_SIZE);
		while (true) {
			if (b.winner() != null) {
				System.out.println(b.winner() + " won!");
				break;
			}

			// If the mouse is pressed
			if (StdDrawPlus.mousePressed()) {

				// get the coordinates
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();

				// if can select, then select and redraw
				if (b.canSelect(x, y)) {
					b.select(x, y);
					b.drawBoard(BOARD_SIZE);

					// highlight the piece
					if (b.selected.isBomb() && b.hasCaptured) {
					} else {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(x + .5, y + .5, .5);
						b.drawPiece(x, y);
					}

					// reshow
					StdDrawPlus.show(100);
				}
			}
			// If the mouse is clicked
			if (StdDrawPlus.isSpacePressed()) {

				// if can end, then end and redraw
				if (b.canEndTurn()) {
					b.endTurn();
					b.drawBoard(BOARD_SIZE);
				}
			}
			StdDrawPlus.show(100);
		}

	}

	/**
	 * The constructor for Board. If shouldBeEmpty is true, initializes an empty
	 * Board. Otherwise, initializes a Board with the default configuration.
	 * Note that an empty Board could possibly be useful for testing purposes.
	 * 
	 * @param shouldBeEmpty
	 */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
		if (!shouldBeEmpty) {
			initialSetup();
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
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				drawPiece(i, j);
			}
		}
	}

	private void drawPiece(int i, int j) {
		String imgName = "img/";
		if (pieces[i][j] != null) {
			Piece p = pieces[i][j];
			if (p.isBomb()) {
				imgName += "bomb";
			} else if (p.isShield()) {
				imgName += "shield";
			} else {
				imgName += "pawn";
			}
			if (p.isFire()) {
				imgName += "-fire";
			} else {
				imgName += "-water";
			}
			if (p.isKing()) {
				imgName += "-crowned";
			}
			imgName += ".png";
			StdDrawPlus.picture(i + .5, j + .5, imgName, 1, 1);
		}
	}

	private void initialSetup() {

		for (int x = 0; x < BOARD_SIZE; x++) {
			if (x % 2 == 0) {
				pieces[x][BOARD_SIZE - 2] = new Piece(false, this, x,
						BOARD_SIZE - 2, "shield");
				pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
			} else {
				pieces[x][BOARD_SIZE - 1] = new Piece(false, this, x,
						BOARD_SIZE - 1, "pawn");
				pieces[x][BOARD_SIZE - 3] = new Piece(false, this, x,
						BOARD_SIZE - 3, "bomb");
				pieces[x][1] = new Piece(true, this, x, 1, "shield");
			}
		}
	}

	/**
	 * Gets the piece at position (x, y) on the board, or null if there is no
	 * piece. If (x, y) are out of bounds, returns null.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece pieceAt(int x, int y) {
		if (x > BOARD_SIZE - 1 || y > BOARD_SIZE - 1) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	/**
	 * Returns true if there is a piece the piece at (x, y) and it can be
	 * selected.
	 * 
	 * A piece may be selected if it is the corresponding player’s turn and one
	 * of the following is true:
	 * 
	 * -The player has not selected a piece yet.
	 * 
	 * -The player has selected a piece, but did not move it.
	 * 
	 * An empty square may be selected if one of the following is true:
	 * 
	 * -During this turn, the player has selected a Piece which hasn’t moved yet
	 * and is selecting an empty spot which is a valid move for the previously
	 * selected Piece.
	 * 
	 * -During this turn, the player has selected a Piece, captured, and has
	 * selected another valid capture destination. (You may select as many
	 * captures in a row as long as they are all valid and from the same piece.)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canSelect(int x, int y) {
		if (x > BOARD_SIZE - 1 || y > BOARD_SIZE - 1 || x < 0 || y < 0) {
			return false;
		}
		// check if selected space has a piece
		if (pieces[x][y] != null
				&& (!hasSelected || (hasSelected && !hasMoved))) {
			return fireTurn == pieces[x][y].isFire();

		}
		// else if space is blank space
		else if (pieces[x][y] == null) {
			// selected a piece and want to move it
			if (hasSelected && !hasMoved) {
				return validMove(selectedX, selectedY, x, y);
			}
			// captured and trying to capture again.
			else if (hasSelected && hasCaptured && Math.abs(y - selectedY) == 2
					&& Math.abs(x - selectedX) == 2) {
				return validMove(selectedX, selectedY, x, y);
			}
		}
		return false;
	}

	/**
	 * Returns true if the piece at (xi, yi) can either move to (xf, yf) or
	 * capture to (xf, yf) in a valid fashion compatible with the rules.
	 * 
	 * @param xi
	 * @param yi
	 * @param xf
	 * @param yf
	 * @return
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieces[xi][yi] == null || xf > BOARD_SIZE - 1
				|| yf > BOARD_SIZE - 1 || pieces[xf][yf] != null) {
			return false;
		}
		Piece p = pieces[xi][yi];
		if (p.isKing()) { // king
			return kingMove(xi, yi, xf, yf);
		} else if (p.isFire()) {
			if (Math.abs(xf - xi) == 1 && yf - yi == 1) { // one
				return true;
			} else if (xf - xi == 2 && yf - yi == 2
					&& pieces[xf - 1][yf - 1] != null
					&& !pieces[xf - 1][yf - 1].isFire()) { // topRight
				return true;
			} else if (xi - xf == 2 && yf - yi == 2
					&& pieces[xi - 1][yf - 1] != null
					&& !pieces[xi - 1][yf - 1].isFire()) { // topLeft
				return true;
			}
		} else if (!p.isFire()) {
			if (Math.abs(xf - xi) == 1 && yi - yf == 1) { // one
				return true;
			} else if (xf - xi == 2 && yi - yf == 2
					&& pieces[xf - 1][yi - 1] != null
					&& pieces[xf - 1][yi - 1].isFire()) { // botRight
				return true;
			} else if (xi - xf == 2 && yi - yf == 2
					&& pieces[xi - 1][yi - 1] != null
					&& pieces[xi - 1][yi - 1].isFire()) { // botLeft
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	private boolean kingMove(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi];
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
			return true;
		} else if (xf - xi == 2 && yf - yi == 2 // topRight
				&& pieces[xf - 1][yf - 1] != null) {
			if (p.isFire()) {
				return !pieces[xf - 1][yf - 1].isFire(); // ifFire isCapWater
			} else {
				return pieces[xf - 1][yf - 1].isFire(); // ifWater isCapFire
			}
		} else if (xi - xf == 2 && yf - yi == 2 // topLeft
				&& pieces[xi - 1][yf - 1] != null) {
			if (p.isFire()) {
				return !pieces[xi - 1][yf - 1].isFire(); // ifFire isCapWater
			} else {
				return pieces[xi - 1][yf - 1].isFire(); // ifWater isCapFire
			}
		} else if (xf - xi == 2 && yi - yf == 2 // botRight
				&& pieces[xf - 1][yi - 1] != null) {
			if (p.isFire()) {
				return !pieces[xf - 1][yi - 1].isFire(); // ifFire isCapWater
			} else {
				return pieces[xf - 1][yi - 1].isFire(); // ifWater isCapFire
			}
		} else if (xi - xf == 2 && yi - yf == 2 // botLeft
				&& pieces[xi - 1][yi - 1] != null) {
			if (p.isFire()) {
				return !pieces[xi - 1][yi - 1].isFire(); // ifFire isCapWater
			} else {
				return pieces[xi - 1][yi - 1].isFire(); // ifWater isCapFire
			}
		}
		return false;
	}

	/**
	 * Selects the piece at (x, y) if possible. Optionally, it is recommended to
	 * color the background of the selected square white on the GUI via the pen
	 * color function. For any piece to perform a capture, that piece must have
	 * been selected first.
	 * 
	 * @param x
	 * @param y
	 */
	public void select(int x, int y) {
		if (hasSelected) {
			Piece p = pieces[x][y];
			if (p != null) {
				selectPiece(x, y);
			} else {
				selected.move(x, y);
				if (Math.abs(selectedX - x) == 2
						|| Math.abs(selectedY - y) == 2) {
					hasCaptured = true;
				}
				selectedX = x;
				selectedY = y;
				hasMoved = true;
			}
		} else if (!hasSelected) {
			selectPiece(x, y);
		}
	}

	private void selectPiece(int x, int y) {
		selectedX = x;
		selectedY = y;
		selected = pieces[x][y];
		hasSelected = true;
	}

	/**
	 * Places p at (x, y). If (x, y) is out of bounds or if p is null, does
	 * nothing. If p already exists in the current Board, first removes it from
	 * its initial position. If another piece already exists at (x, y), p will
	 * replace that piece. (This method is potentially useful for creating
	 * specific test circumstances.)
	 * 
	 * @param p
	 * @param x
	 * @param y
	 */
	public void place(Piece p, int x, int y) {
		if (p == null || x > BOARD_SIZE - 1 || y > BOARD_SIZE - 1 || x < 0
				|| y < 0) {
		} else {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (pieces[i][j] != null && pieces[i][j] == p) {
						pieces[i][j] = null;
					}
				}
			}
			pieces[x][y] = p;
		}
	}

	/**
	 * Executes a remove. Returns the piece that was removed. If the input (x,
	 * y) is out of bounds, returns null and prints an appropriate message. If
	 * there is no piece at (x, y), returns null and prints an appropriate
	 * message.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece remove(int x, int y) {
		if (x > BOARD_SIZE - 1 || y > BOARD_SIZE - 1 || x < 0 || y < 0
				|| pieces[x][y] == null) {
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	/**
	 * Returns whether or not the the current player can end their turn. To be
	 * able to end a turn, a piece must have moved or performed a capture.
	 * 
	 * @return
	 */
	public boolean canEndTurn() {
		return hasMoved || hasCaptured;
	}

	/**
	 * Called at the end of each turn. Handles switching of players and anything
	 * else that should happen at the end of a turn.
	 */
	public void endTurn() {
		hasSelected = false;
		hasMoved = false;
		hasCaptured = false;
		fireTurn = !fireTurn;
		selected.doneCapturing();
	}

	/**
	 * Returns the winner of the game. "Fire", "Water", "No one" (tie / no
	 * pieces on the board), or null if the game is not yet over. Assume there
	 * is no stalemate situation in which the current player has pieces but
	 * cannot legally move any of them (In this event, just leave it at null).
	 * Determine the winner solely by the number of pieces belonging to each
	 * team.
	 * 
	 * @return
	 */
	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Piece p = pieces[i][j];
				if (p != null)
					if (p.isFire()) {
						fire++;
					} else {
						water++;
					}
			}
		}
		if (fire == 0 && water == 0) {
			return "No one";
		} else if (fire == 0) {
			return "Water";
		} else if (water == 0) {
			return "Fire";
		} else {
			return null;
		}
	}
}