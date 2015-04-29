public class Board {
	private Piece[][] checkerPiece;
	private int fireCounter;
	private int waterCounter;
	private boolean fireTurn = true;

	private Piece selected = null;
	private int selectedX = 0;
	private int selectedY = 0;
	private boolean moved = false;

	// private int highlightX;
	// private int highlightY;
	private static final boolean FIRE = true;
	private static final boolean WATER = false;
	private static final String PAWN = "pawn";
	private static final String BOMB = "bomb";
	private static final String SHIELD = "shield";

	// image locations
	private static String fire_bomb_image = "img/bomb-fire.png";
	private static String fire_pawn_image = "img/pawn-fire.png";
	private static String fire_shield_image = "img/shield-fire.png";
	private static String water_bomb_image = "img/bomb-water.png";
	private static String water_pawn_image = "img/pawn-water.png";
	private static String water_shield_image = "img/shield-water.png";
	private static String king_fire_bomb_image = "img/bomb-fire-crowned.png";
	private static String king_fire_pawn_image = "img/pawn-fire-crowned.png";
	private static String king_fire_shield_image = "img/shield-fire-crowned.png";
	private static String king_water_bomb_image = "img/bomb-water-crowned.png";
	private static String king_water_pawn_image = "img/pawn-water-crowned.png";
	private static String king_water_shield_image = "img/shield-water-crowned.png";

	public Board(boolean shouldBeEmpty) {
		/**
		 * The constructor for Board. If shouldBeEmpty is true, initializes an
		 * empty Board. Otherwise, initializes a Board with the default
		 * configuration. Note that an empty Board could possibly be useful for
		 * testing purposes.*
		 */
		checkerPiece = new Piece[8][8];
		if (shouldBeEmpty == false) {
			initialBoard(8); // create a full board
		}
		return;
	}

	private void drawPieces(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (checkerPiece[i][j] == null)
					continue;

				if (checkerPiece[i][j].isFire() == FIRE) {

					if (!checkerPiece[i][j].isShield()
							&& !checkerPiece[i][j].isBomb()) {
						if (checkerPiece[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,
									king_fire_pawn_image, 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									fire_pawn_image, 1, 1);
						}
					}

					else if (checkerPiece[i][j].isShield()) {
						if (checkerPiece[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,
									king_fire_shield_image, 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									fire_shield_image, 1, 1);
						}

					}

					else if (checkerPiece[i][j].isBomb()) {
						if (checkerPiece[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,
									king_fire_bomb_image, 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									fire_bomb_image, 1, 1);
						}
					}

				} else if (checkerPiece[i][j].isFire() == WATER) {

					if (!checkerPiece[i][j].isShield()
							&& !checkerPiece[i][j].isBomb()) {
						if (checkerPiece[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,
									king_water_pawn_image, 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									water_pawn_image, 1, 1);
						}
					}

					if (checkerPiece[i][j].isShield()) {
						if (checkerPiece[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,
									king_water_shield_image, 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									water_shield_image, 1, 1);
						}
					}
					if (checkerPiece[i][j].isBomb()) {
						if (checkerPiece[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,
									king_water_bomb_image, 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,
									water_bomb_image, 1, 1);
						}
					}
				}

			}
		}
	}

	private void drawBoard(int N) {
		// draws an empty board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
	}

	private void initialBoard(int N) {
		// creates initial pieces
		// i is column and j is row
		// i=0, j=0 is the bottom left corner

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j == 7 && (i % 2 != 0)) {
					checkerPiece[i][j] = new Piece(WATER, this, i, j, PAWN);
					waterCounter += 1;
				}

				else if (j == 6 && (i % 2 == 0)) {
					checkerPiece[i][j] = new Piece(WATER, this, i, j, SHIELD);
					waterCounter += 1;
				}

				else if (j == 5 && (i % 2 != 0)) {
					checkerPiece[i][j] = new Piece(WATER, this, i, j, BOMB);
					waterCounter += 1;
				}

				else if (j == 0 && (i % 2 == 0)) {
					checkerPiece[i][j] = new Piece(FIRE, this, i, j, PAWN);
					fireCounter += 1;
				}

				else if (j == 1 && i % 2 != 0) {
					checkerPiece[i][j] = new Piece(FIRE, this, i, j, SHIELD);
					fireCounter += 1;
				}

				else if (j == 2 && (i % 2 == 0)) {
					checkerPiece[i][j] = new Piece(FIRE, this, i, j, BOMB);
					fireCounter += 1;
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		/*
		 * Gets the piece at position (x, y) on the board, or null if there is
		 * no piece. If (x, y) are out of bounds, returns null.*
		 */

		if (outOfBounds(x, y)) {
			return null;
		} else {
			return checkerPiece[x][y];
		}
	}

	public void place(Piece p, int x, int y) {
		/*
		 * Places p at (x, y). If (x, y) is out of bounds or if p is null, does
		 * nothing. If another piece already exists at (x, y), p will replace
		 * that piece. (This method is potentially useful for creating specific
		 * test circumstances.)*
		 */
		if (p == null) {
			return;
		}
		if (outOfBounds(x, y)) {
			System.out.println("Out of bounds");
			return;
		} else if (p != null) {
			checkerPiece[x][y] = p;

			if (checkerPiece[x][y].isFire()) {
				fireCounter += 1;
			} else {
				waterCounter += 1;
			}

		}
	}

	private boolean outOfBounds(int x, int y) {
		/*
		 * out of Bounds includes squares that are not on the board and red
		 * squares*
		 */

		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return true;
		}
		if (x == 7 && (y % 2 == 0)) {
			return true;
		}

		if (x == 6 && (y % 2 != 0)) {
			return true;
		}

		if (x == 5 && (y % 2 == 0)) {
			return true;
		}

		
		if (x == 4 && (y % 2 != 0)) {
			return true;
		}
		if (x == 3 && y % 2 == 0) {
			return true;
		}

		
		
		if (x == 2 && (y % 2 != 0)) {
			return true;
		}
		if (x == 1 && y % 2 == 0) {
			return true;
		}

		if (x == 0 && (y % 2 != 0)) {
			return true;
		}

		return false;
	}

	public boolean canSelect(int x, int y) {
		/**
		 * Returns true if the square at (x, y) can be selected.
		 * 
		 * A square with a piece may be selected if it is the corresponding
		 * player’s turn and one of the following is true:
		 * 
		 * The player has not selected a piece yet. The player has selected a
		 * piece, but did not move it. An empty square may be selected if one of
		 * the following is true:
		 * 
		 * During this turn, the player has selected a Piece which hasn’t moved
		 * yet and is selecting an empty spot which is a valid move for the
		 * previously selected Piece. During this turn, the player has selected
		 * a Piece, captured, and has selected another valid capture
		 * destination. When performing multi-captures, you should only select
		 * the active piece once; all other selections should be valid
		 * destination points.*
		 */

		// FIRST SELECT: select a piece
		if (checkerPiece[x][y] != null
				&& checkerPiece[x][y].isFire() == fireTurn) {
			// check whether null and if it is current player's piece

			if (selected == null) { // check if have not selected a piece yet
				return true;

			} else if (!moved) {
				// player has selected a piece but not moved it --
				// change to a different piece
				return true;
			}

		} else {
			// SECOND SELECT: select an empty square
			if (selected != null && !moved
					&& validMove(selectedX, selectedY, x, y)) {
				return true;
			} else if (selected != null && selected.hasCaptured()
					&& validCapture(selectedX, selectedY, x, y)) {
				return true;
			}

		}

		return false;
	}

	public void select(int x, int y) {
		/*
		 * Selects the square at (x, y). This method assumes canSelect (x,y)
		 * returns true. Optionally, it is recommended to color the background
		 * of the selected square white on the GUI via the pen color function.
		 * For any piece to perform a capture, that piece must have been
		 * selected first. If you select a square with a piece, you are prepping
		 * that piece for movement. If you select an empty square (assuming
		 * canSelect returns true), you should move your most recently selected
		 * piece to that square.*
		 */

		if (checkerPiece[x][y] == null) {
			place(selected, x, y);
			remove(selectedX, selectedY);
			selected.move(x, y);
			moved = true;
			System.out.println("you have moved");
			System.out.println("Fire Pieces: " + fireCounter);
			System.out.println("Water Pieces: " + waterCounter);

		} else {
			selected = checkerPiece[x][y];
		}
		selectedX = x;
		selectedY = y;

	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		/*
		 * validMove is not required, and will not be tested. You may implement
		 * this however you want. It is suggested you use this method to
		 * simplify your implementation of canSelect. However, keep this method
		 * must be private.*
		 */

		if (outOfBounds(xf, yf)) {
			return false;
		}
		if (checkerPiece[xi][yi] == null) {
			return false;
		}

		if (validCapture(xi, yi, xf, yf)) {
			return true;
		}

		if (checkerPiece[xi][yi].isFire() || checkerPiece[xi][yi].isKing()) {
			// fire pieces move up, kings can go anywhere
			if (xi - 1 == xf && yi + 1 == yf) {
				return true;
			} else if (xi + 1 == xf && yi + 1 == yf) {
				return true;
			}
		}

		if (!checkerPiece[xi][yi].isFire() || checkerPiece[xi][yi].isKing()) {
			// water pieces move down, kings can go anywhere
			if (xi - 1 == xf && yi - 1 == yf) {
				return true;
			} else if (xi + 1 == xf && yi - 1 == yf) {
				return true;
			}
		}

		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		/*
		 * Determines whether capture is allowed (jumping opponent's piece)
		 */

		if (outOfBounds(xf, yf)) { // check out of bounds
			return false;
		}
		if (checkerPiece[xi][yi] == null) {
			// cannot perform capture without a piece
			return false;
		}

		if ((checkerPiece[xi][yi].isFire() || checkerPiece[xi][yi].isKing())) {
			// fire pieces move up, kings can go anywhere
			if (xi + 2 == xf
					&& yi + 2 == yf
					&& checkerPiece[xi + 1][yi + 1] != null
					&& (checkerPiece[xi + 1][yi + 1].isFire() != checkerPiece[xi][yi]
							.isFire())) {
				return true;
			} else if (xi - 2 == xf
					&& yi + 2 == yf
					&& checkerPiece[xi - 1][yi + 1] != null
					&& (checkerPiece[xi - 1][yi + 1].isFire() != checkerPiece[xi][yi]
							.isFire())) {
				return true;
			}
		}

		if (!checkerPiece[xi][yi].isFire() || checkerPiece[xi][yi].isKing()) {
			// water pieces move down, kings can go anywhere
			if (xi + 2 == xf
					&& yi - 2 == yf
					&& checkerPiece[xi + 1][yi - 1] != null
					&& (checkerPiece[xi + 1][yi - 1].isFire() != checkerPiece[xi][yi]
							.isFire())) {
				return true;
			} else if (xi - 2 == xf
					&& yi - 2 == yf
					&& checkerPiece[xi - 1][yi - 1] != null
					&& (checkerPiece[xi - 1][yi - 1].isFire() != checkerPiece[xi][yi]
							.isFire())) {
				return true;
			}
		}

		return false;
	}

	public Piece remove(int x, int y) {
		/*
		 * Executes a remove. Returns the piece that was removed. If the input
		 * (x, y) is out of bounds, returns null and prints an appropriate
		 * message. If there is no piece at (x, y), returns null and prints an
		 * appropriate message.*
		 */
		if (checkerPiece[x][y] == null) {
			System.out.println("Error: No piece to remove");
			return null;
		}

		Piece piece = checkerPiece[x][y];
		if (checkerPiece[x][y].isFire()) {
			fireCounter -= 1;
		} else {
			waterCounter -= 1;
		}

		checkerPiece[x][y] = null;
		return piece;
	}

	public boolean canEndTurn() {
		/*
		 * Returns whether or not the the current player can end their turn. To
		 * be able to end a turn, a piece must have moved or performed a
		 * capture*
		 */

		if (selected == null) {
			return false;
		}

		if (moved || selected.hasCaptured()) {
			// or if captured is true, but captured should include move
			// moved = false;
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		/*
		 * Called at the end of each turn. Handles switching of players and
		 * anything else that should happen at the end of a turn.*
		 */
		fireTurn = !fireTurn;
		selected.doneCapturing();
		selected = null;
		moved = false;
	}

	public String winner() {
		/*
		 * Returns the winner of the game. "Fire", "Water", "No one" (tie / no
		 * pieces on the board), or null if the game is not yet over. Assume
		 * there is no stalemate situation in which the current player has
		 * pieces but cannot legally move any of them (In this event, just leave
		 * it at null). Determine the winner solely by the number of pieces
		 * belonging to each team.*
		 */
		if (waterCounter == 0 && fireCounter == 0) {
			return "No one";
		} else if (waterCounter == 0) {
			return "Fire";
		} else if (fireCounter == 0) {
			return "Water";
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		/*
		 * starts a GUI supported version of the game. *
		 */

		int N = 8;

		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		Board board = new Board(false); // create a full board

		// ***TESTING***TESTING****
		// Piece one = new Piece(false,board, 0, 1, PAWN);
		// Piece two = new Piece(false, board, 3, 4, PAWN);
		// Piece three = new Piece(false, board, 5, 5, PAWN);
		// Piece four = new Piece(true, board, 2, 2, PAWN);
		//
		// board.place(one, 0, 1);
		// board.place(two, 3, 4);
		// board.place(three, 0, 5); 
		// board.place(four, 2, 3);
		// ***TESTING***TESTING****

		while (true) {
			board.drawBoard(N);
			// // highlight selected square
			// if (board.selected != null) {
			// StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			// StdDrawPlus.filledSquare(board.highlightX + .5,
			// board.highlightY + .5, .5);
			// }

			board.drawPieces(N);
			board.winner();

			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				// board.highlightX = (int) x;
				// board.highlightY = (int) y;

				if (board.canSelect((int) x, (int) y)) {
					board.select((int) x, (int) y);
				}

				board.winner();
			}

			if (StdDrawPlus.isSpacePressed()) {
				// StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				// StdDrawPlus.filledSquare(board.highlightX + .5,
				// board.highlightY + .5, .5);
				if (board.canEndTurn()) {
					board.endTurn();
				}

			}

			StdDrawPlus.show(100);
		}
	}
}
