// CS61B Spring 2015 | 02-08-15
// Name: Crystal Yan | Login: cs61b-amp

public class Board{

	/* Starts Bomb Checkers GUI and monitors player responses
	*/
	public static void main(String[] args) {
		// Creates board window
		Board b = new Board(false);
		StdDrawPlus.setCanvasSize();
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        // Monitors for user responses and updates visual constantly
		while (b.winner() == null) { 
			drawCurrBoard(b);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                }
                StdDrawPlus.show(100);
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            	StdDrawPlus.show(100);
            }

            StdDrawPlus.show(20);
		}

		// Game is over
		String result = b.winner();
		System.out.println("Winner is " + result + "!");
	}

	/** Draws current state of 8x8 board. Edited from StdDrawDemo.java
     */
    private static void drawCurrBoard(Board b) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	// Colors squares of board
            	if (i == b.i && j == b.j)  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                       StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                // Adds appropriate image of game piece on tile
                Piece pc = b.pcs[i][j];
                if (pc != null) {
	                if (pc.isKing() && pc.isFire() && pc.isBomb()) {
	                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                }
	                else if (pc.isKing() && pc.isFire() && pc.isShield()) {
	                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                }
	                else if (pc.isKing() && pc.isFire()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
					}
					else if (pc.isFire() && pc.isBomb()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					}
					else if (pc.isFire() && pc.isShield()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					}
					else if (pc.isFire()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					}
					else if (pc.isKing() && pc.isBomb()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					}
					else if (pc.isKing() && pc.isShield()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					}
					else if (pc.isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
					}
					else if (pc.isBomb()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					}
					else if (pc.isShield()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					}
                }
            }
        }
    }

	private Piece[][] pcs;      // An array[x][y], from bottom left to top right, 
	                            // that can contain Pieces
	private int[] selected;     // Current piece's coordinates selected by played
	private boolean isFireTurn; // Keeps track of which player
	private boolean madeMove;   // Keeps track if current player has made first move 
	                            // in turn, so can't change selected
	private int i;				// Remembers selected x for inking as white- may be empty
	private int j;				// Remembers selected y for inking as white- may be empty

	/* Constructor of board for one game
	*/
	public Board(boolean shouldBeEmpty) {
		// Instantiation of game variables for play
		pcs = new Piece[8][8];
		selected = new int[2];
		selected[0] = -1;
		selected[1] = -1;
		isFireTurn = true;
		madeMove = false;
		i = -1;
		j = -1;

		// Puts pieces into default configuration
		if (!shouldBeEmpty) {
			for (int x = 0; x <= 7; x += 2) {
				pcs[x][0] = new Piece(true, this, x, 0, "pawn");
			}
			for (int x = 1; x <= 7; x += 2) {
				pcs[x][1] = new Piece(true, this, x, 1, "shield");
			}
			for (int x = 0; x <= 7; x += 2) {
				pcs[x][2] = new Piece(true, this, x, 2, "bomb");
			}
			for (int x = 1; x <= 7; x += 2) {
				pcs[x][5] = new Piece(false, this, x, 5, "bomb");
			}
			for (int x = 0; x <= 7; x += 2) {
				pcs[x][6] = new Piece(false, this, x, 6, "shield");
			}
			for (int x = 1; x <= 7; x += 2) {
				pcs[x][7] = new Piece(false, this, x, 7, "pawn");
			}
		}
	}

	/* Gets the piece at position (x, y) on the board, or null if there 
	 * is no piece. If (x, y) are out of bounds, returns null.
	 */
	public Piece pieceAt(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) return null;
		return pcs[x][y];
	}

	/* Places p at (x, y). If (x, y) is out of bounds, does nothing. If another 
	 * piece already exists at (x, y), p will replace that piece.
	*/
	public void place(Piece p, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			if (selected[0] != -1) {	// Assumes p = selected, removes from old location
				pcs[selected[0]][selected[1]] = null; 
			}
			pcs[x][y] = p;
			p.move(x, y);
		}
	}

	/* Helper method that returns an int array of x and y coordinates
	 * in between (assumed) 2 diagonal spaces.
	*/
	private int[] midCoor(int xCor, int yCor, int nwX, int nwY) {
		int[] coor = new int[2];
		if (nwX > xCor) {
			coor[0] = xCor + 1;
		}
		else {
			coor[0] = nwX + 1;
		}
		if (nwY > yCor) {
			coor[1] = yCor + 1;
		}
		else {
			coor[1] = nwY + 1;
		}
		return coor;
	}

	/* Helper method that returns 0 if geometrically invalid move,
	 * 1 if valid for a simple move, or 2 if valid for a capture move.
	 * Checks sides of pieces involved.
	*/
	private int validMove(int xi, int yi, int xf, int yf) {
		if (xi < 0 || yi < 0 || xi >= 8 || yi >= 8) {
			// Moving from off the board, or nothing remembered as selected
			return 0;
		}
		if (xf < 0 || yf < 0 || xf >= 8 || yf >= 8) {
			// Moving to off the board
			return 0;
		}
		Piece pc = pieceAt(xi, yi);
		if (pc == null || pc.isFire() != isFireTurn) {
			// Piece not found at selected, or selected belongs to foe
			return 0;
		}
		if (Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2) {
			// Moving too many spaces
			return 0;
		}

		else if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
			// Checking simple move
			if (pc.isKing()) {
				// Crowned moves any direction
				return 1;
			}
			else if (pc.isFire() && yf - yi == 1) {
				// Fire only moves up
				return 1;
			}
			else if (!pc.isFire() && yi - yf == 1) {
				// Water only moves down
				return 1;
			}
		}

		else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
			// Checking capture move
			int[] middle = midCoor(xi, yi, xf, yf);
			Piece prisoner = pieceAt(middle[0], middle[1]);
			if (prisoner == null || prisoner.isFire() == pc.isFire()) {
				// Captured piece must exist and not be an ally
				return 0;
			}
			if (pc.isKing()) {
				// Crowned can move any direction
				return 2;
			}
			else if (pc.isFire() && yf - yi == 2) {
				// Fire only moves up
				return 2;
			}
			else if (!pc.isFire() && yi - yf == 2) {
				// Water only moves down
				return 2;
			}
		}
		return 0; // Catch-all
	}

	/* Returns true if the square at (x, y) can be selected. Uses validMove,
	 * shown above
	 */
	public boolean canSelect(int x, int y) {
		if (x < 0 || y < 0 || x >= 8 || y >= 8) { 
		// Out of bounds
			return false;
		}

		Piece token = pieceAt(x, y);
		if (token != null) {
			// Piece being selected
			if (token.isFire() == isFireTurn && (selected[0] == -1 
				|| madeMove == false)) {
				return true;
			}
		}
		else {
			// Empty space being selected
			int moveType = validMove(selected[0], selected[1], x, y);
			if (moveType == 0) {
				// Completely invalid move
				return false;
			}
			Piece pc = pieceAt(selected[0], selected[1]);
			if (moveType == 1) {
				// Double-checking simple move
				if (madeMove == false && pc.hasCaptured() == false) {
					return true;
				}
			}
			else if (moveType == 2) {
				// Double-checking capture move
				if ((madeMove == false && pc.hasCaptured() == false) 
					|| (madeMove == true && pc.hasCaptured() == true)) {
					return true;
				}
			}
		}
		return false; // Catch-all
	}

	/* Selects the piece at (x, y) if possible.
	 */
	public void select(int x, int y) {
		// Remembers square to highlight
		i = x;
		j = y;

		Piece token = pcs[x][y];
		if (token != null) { 
		// Selecting/switching a new piece or double-clicked 
			selected[0] = x;
			selected[1] = y;
		}

		else {                   
		// Or is moving a piece to empty square
			if (selected[0] == -1) return; // No piece previously selected to move
			Piece pc = pcs[selected[0]][selected[1]];
			if (pc != null) {
				madeMove = true;
				place(pc, x, y);
				selected[0] = x;
				selected[1] = y;
			}
		}
	}

	/* Executes a remove. Returns the piece that was removed. If the input 
	 * (x, y) is out of bounds, returns null and prints an appropriate message. 
	 * If there is no piece at (x, y), returns null and prints an appropriate 
	 * message.
	*/
	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x >= 8 || y >= 8) { 
			System.out.println("Coordinates to remove are out of bounds!");
			return null;
		}
		else if (pieceAt(x, y) == null) { 
			System.out.println("No piece at given coordinates to remove!");
		}
		Piece removed = pcs[x][y];
		pcs[x][y] = null;
		return removed;
	}

	/* Returns whether or not the the current player can end their turn. To
	 * be able to end a turn, a piece must have moved or performed a capture.
	*/
	public boolean canEndTurn() {
		if (madeMove == true) return true;
		for (Piece[] row : pcs) {
			for (Piece pc : row) {
				if (pc != null && pc.hasCaptured() == true) {
					return true;
				}
			}
		}
		return false;
	}

	/* Called at the end of each turn. Handles switching of players and 
	 * anything else that should happen at the end of a turn.
	*/
	public void endTurn() {
		// Wipes out game variables for next player
		selected[0] = -1;
		selected[1] = -1;
		madeMove = false;
		i = -1;
		j = -1;
		for (Piece[] row : pcs) {
			for (Piece pc : row) {
				if (pc != null) {
					pc.doneCapturing();
				}
			}
		}

		// Swaps player
		if (isFireTurn) {
			isFireTurn = false;
		}
		else {
			isFireTurn = true;
		}
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie / 
	 * no pieces on the board), or null if the game is not yet over, or
	 * has a stalemate. Determine the winner solely by the number of pieces 
	 * belonging to each team.
	*/
	public String winner() {
		Piece first = null;  // First piece still present
		Piece second = null; // First piece of opposing side still present
		for (Piece[] row : pcs) {
			for (Piece pc : row) {
				if (pc != null && first == null) {
					first = pc;
				}
				else if (pc != null && second == null 
					&& pc.isFire() != first.isFire()) {
					second = pc;
				}
			}
		}
		if (first == null) { 
			// No more pieces on board
			return "No one";
		}
		if (first != null && second == null) { 
			// A piece belonging to other side was not found
			if (first.isFire()) {
				return "Fire";
			}
			return "Water";
		}
		return null;
	}
}