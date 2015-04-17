public class Board { 

	private static Piece[][] pieces;
	private int N;

	private boolean hasWinner = false;
	private boolean playerIsFire = true;
	private boolean playerHasSelected = false;
	private boolean playerHasMoved = false;

	private Piece selectedPiece;
	private Piece selectedSquare;
	private int previousX;
	private int previousY;

	private int xSelect;
	private int ySelect;

	private String winner = "No one";

	/** Constructor for Board
	  * Initialize a board with default configurations,
	  * unless shouldBeEmpty is true.
	  * If shouldBeEmpty, initialize an empty board. */
	public Board(boolean shouldBeEmpty) {
		// size of a side on the board
		Board b = this;
		N = 8;

		pieces = new Piece[N][N];

		if (!shouldBeEmpty) {
			drawPieces(b, N);
		}
	}

	private static void drawBoard(Board b, int N) {
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	if (b.selectedPiece != null && pieces[b.pieceXValue(b.selectedPiece)][b.pieceYValue(b.selectedPiece)] == pieces[i][j]) {
	        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        	}
	            else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
	            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

	            if (pieces[i][j] != null) {
	            	Piece currentPiece = pieces[i][j];
	            	if (currentPiece.isFire()) {
            			if (currentPiece.isKing()) {
            				if (currentPiece.isBomb()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            				}
            				else if (currentPiece.isShield()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            				}
            			}
            			else if (currentPiece.isBomb()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            			}
            			else if (currentPiece.isShield()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            			}
            			else {
            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            			}
	            	}
	            	else {
	            		if (currentPiece.isKing()) {
	            			if (currentPiece.isBomb()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            				}
            				else if (currentPiece.isShield()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            				}
	            		}
	            		else if (currentPiece.isBomb()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	            		}
	            		else if (currentPiece.isShield()) {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            		}
	            		else {
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	            		}
	            	}
	            }
	        }
	    }
	}

	private static void drawPieces(Board b, int N) {
		for (int x = 0; x < N; x += 1) {
			for (int y = 0; y < N; y += 1) {
				/* Row 0 : fire pawns */
				if (y == 0 && x % 2 == 0) {
					pieces[x][y] = new Piece(true, b, x, y, "pawn");
				}
				/* Row 1 : fire shield */
				if (y == 1 && x % 2 == 1) {
					pieces[x][y] = new Piece(true, b, x, y, "shield");
				}
				/* Row 2 : fire bomb */
				if (y == 2 && x % 2 == 0) {
					pieces[x][y] = new Piece(true, b, x, y, "bomb");
				}
				/* Row N - 1 : water pawn */
				if (y == N - 1 && x % 2 == 1) {
					pieces[x][y] = new Piece(false, b, x, y, "pawn");
				}
				/* Row N - 2 : water shield */
				if (y == N - 2 && x % 2 == 0) {
					pieces[x][y] = new Piece(false, b, x, y, "shield");
				}
				/* Row N - 3 : water bomb */
				if (y == N - 3 && x % 2 == 1) {
					pieces[x][y] = new Piece(false, b, x, y, "bomb");
				}
			}
		}
	}

	private int pieceXValue(Piece p) {
		for (int x = 0; x < N; x += 1) {
			for (int y = 0; y < N; y += 1) {
				if (pieces[x][y] == p) {
					return x;
				}
			}
		}
		return 0;
	}

	private int pieceYValue(Piece p) {
		for (int x = 0; x < N; x += 1) {
			for (int y = 0; y < N; y += 1) {
				if (pieces[x][y] == p) {
					return y;
				}
			}
		}
		return 0;
	}

	/** Returns a piece object at specified position
	  * returns null if no piece at position, or if
	  * (x, y) is out of bounds */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}

	/** Returns true if there is a piece at (x, y)
	  * and it can be selected.
	  *
	  * A square with a piece may be selected if it's the player's turn
	  * and one of the following is true:
	  * 	* the player has not selected a piece yet
	  * 	* the player has selected a piece, but did not move it
	  *
	  * An empty square may be selected if one is true:
	  *		* the player has selected a piece which hasn't moved and
	  *		  is selecting an empty spot which is a valid move for the piece
	  *		* the player has selected a piece, captured, and has selected
	  *		  another valid capture destination.
	  *		* When performing multi-captures, you should only select the
	  *		  active piece once; all other selections should be valid
	  *		  destination points. */
	public boolean canSelect(int x, int y) {
		/** Square with a piece */
		if (pieceAt(x, y) != null) {
			if (playerHasMoved) {
				return false;
			}
			if (playerIsFire && !pieceAt(x, y).isFire()) {
				return false;
			}
			else if (!playerIsFire && pieceAt(x, y).isFire()) {
				return false;
			}
			if (playerHasSelected == false || playerHasMoved == false) {
				return true;
			}
		}
		/** Empty square */
		else {
			int xf = this.xSelect;
			int yf = this.ySelect;
			Piece p = pieceAt(previousX, previousY);
			if (playerHasSelected == true && playerHasMoved == false) {
				if (validMove(p, previousX, previousY, x, y)) {
					return true;
				}
			}
			else if (p != null && playerHasSelected && p.hasCaptured()) {
				int xDistance = Math.abs(previousX - x);
				int yDistance = Math.abs(previousY - y);
				/** If you're trying to move normally after capture, return false */
				if (xDistance == 1 && yDistance == 1) {
					return false;
				}
				else if (validMove(p, previousX, previousY, x, y)) {
					return true;
				}
			}
			else if (pieceAt(previousX, previousY) != null) {
				pieceAt(previousX, previousY).doneCapturing();
				return false;
			}
		}
		return false;
	}

	/** Returns true if the piece at (xi, yi) can either
	  * move to (xf, yf) or
	  * capture to (xf, yf) in a valid fashion */
	private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
		if (xf > 7 || yf > 7) {
			return false;
		}
		int xDistance = xf - xi;
		int yDistance = yf - yi;

		if (Math.abs(xDistance) == 1 && Math.abs(yDistance) == 1) {
			if (p.isKing()) {
				return true;
			}
			else {
				/** If not King, then fire pieces must move UP and
				  * water pieces must move DOWN.
			      */
				if (p.isFire()) {
					if (yDistance > 0) {
						return true;
					}
					else {
						return false;
					}
				}
				else if (!p.isFire()) {
					if (yDistance < 0) {
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		/*************************/
		/** JUMPING OVER A PIECE */
		/*************************/
		if (Math.abs(xDistance) == 2 && Math.abs(yDistance) == 2) {
			int newX = 0;
			int newY = 0;
			if (p.isKing()) {
				/** If isKing, then fire pieces must move DOWN and
				  * water pieces must move UP.
			      */
				if (yDistance < 0) {
					if (xDistance > 0) {
						newX = xi + 1;
						newY = yi - 1;
					}
					else if (xDistance < 0) {
						newX = xi - 1;
						newY = yi - 1;
					}
				}
				else if (yDistance > 0) {
					if (xDistance > 0) {
						newX = xi + 1;
						newY = yi + 1;
					}
					else if (xDistance < 0) {
						newX = xi - 1;
						newY = yi + 1;
					}
				}
			}
			else {
				/** If not King, then fire pieces must move UP and
				  * water pieces must move DOWN.
			      */
				if (p.isFire()) {
					if (yDistance > 0) {
						if (xDistance > 0) {
							newX = xi + 1;
							newY = yi + 1;
						}
						else if (xDistance < 0) {
							newX = xi - 1;
							newY = yi + 1;
						}
					}
					else {
						return false;
					}
				}
				else if (!p.isFire()) {
					if (yDistance < 0) {
						if (xDistance > 0) {
							newX = xi + 1;
							newY = yi - 1;
						}
						else if (xDistance < 0) {
							newX = xi - 1;
							newY = yi - 1;
						}
					}
					else {
						return false;
					}
				}
			}

			Piece jumpOverPiece = pieceAt(newX, newY);
			if (pieceAt(newX, newY) != null) {
				if (p.isFire()) {
					if (jumpOverPiece.isFire()) {
						return false;
					}
					else {
						return true;
					}
				}
				else if (!p.isFire()) {
					if (jumpOverPiece.isFire()) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

	/** Selects the piece or moves to location (x, y) */
	public void select(int x, int y) {
		selectedSquare = pieceAt(x, y);
		previousX = x;
		previousY = y;
		if (selectedSquare != null) {
			selectedPiece = pieceAt(x, y);
			playerHasSelected = true;
		}
		else if (selectedSquare == null) {
			selectedPiece.move(x, y);
			playerHasMoved = true;
		}
	}

	/** Places p at (x, y).
	  * If (x, y) is out of bounds or if p is null, does nothing.
	  * If p is already on the board (if p is in pieces[][]),
	  	remove from initial position.
	  * If another piece is at (x, y), replace that piece. */
	public void place(Piece p, int x, int y) {
		if (p == null || x > N || y > N || x < 0 || y < 0) {
			return;
		}
		else if (onBoard(p)) {
			int xi = pieceXValue(p);
			int yi = pieceYValue(p);
			remove(xi, yi);
		}
		pieces[x][y] = p;
	}

	/********* SET TO PRIVATE BEFORE SUBMIT! ***********/
	private boolean onBoard(Piece p) {
		if (p == null) {
			return false;
		}
		for (int x = 0; x < N; x += 1) {
			for (int y = 0; y < N; y += 1) {
				if (p == pieceAt(x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/** Remove Piece at (x, y). Return the piece that was removed.
	  * If (x, y) is out of bounds, return null and print message.
	  * If there is no piece at (x, y), return null and print message. */
	public Piece remove(int x, int y) {
		if (x > N || y > N) {
			System.out.println("Tried to remove but (x, y) out of bounds.");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("Tried to remove, no piece at ("+x+  ", " +y+ ").");
			return null;
		}
		else {
			Piece tempPiece = pieces[x][y];
			if (pieceAt(x, y).hasCaptured() && pieceAt(x, y).isBomb()) {
				endTurn();
			}
			pieces[x][y] = null;
			return tempPiece;
		}
	}

	public boolean canEndTurn() {
		return playerHasMoved;
	}

	/** Called at the end of each turn
	  * Handles switching of players and more */
	public void endTurn() {
		playerIsFire = !playerIsFire;
		playerHasMoved = false;
		playerHasSelected = false;
		if (selectedPiece!= null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		trackPieces();
		if (hasWinner) {
			winner();
		}
	}

	/** Private method to count pieces after each turn and
	  * during each call to winner().
	  * Sets variable winner accordingly */
	private void trackPieces() {
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				Piece p = pieces[i][j];
				if (p != null && p.isFire()) {
					numFire += 1;
				}
				else if (p != null && !p.isFire()) {
					numWater += 1;
				}
			}
		}
		if (numFire > 0 && numWater > 0) {
			winner = null;
			hasWinner = false;
		}
		else if (numFire == 0 && numWater == 0) {
			winner = "No one";
			hasWinner = false;
		}
		else if (numFire == 0) {
			winner = "Water";
			hasWinner = true;
		}
		else if (numWater == 0) {
			winner = "Fire";
			hasWinner = true;
		}
	}

	/** Returns the winner of the game.
	  * Empty board return "no one"
	  * Game in progress returns null
	  * Else, winner is side still with pieces
	  * Assume no stalemate */
	public String winner() {
		trackPieces();
		return winner;
	}

	/** Main Method
	  * Starts a GUI supported version of the game
	  * Plays the game */
	public static void main(String[] args) {
		Board b = new Board(false);

		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		pieces = new Piece[N][N];

		/* Place all the pieces in the right position */
		drawPieces(b, N);
		drawBoard(b, N);

		/** Monitors for mouse presses. Wherever the mouse is pressed,
		    a new piece appears. */
		while(!b.hasWinner) {
		    /** When a user clicks on a square:
		      * 1. Extract the coordinates of the piece.
		      * 2. Check if you can select a piece (canSelect())
		      * 3. If #2 returns true, then call select() on its coordinates*/
	    	if (StdDrawPlus.mousePressed()) {
	    		/** Extract the coordinates of the selection */
	    	    int x = (int) StdDrawPlus.mouseX();
	    	    int y = (int) StdDrawPlus.mouseY();
	    	    b.xSelect = x;
	    	    b.ySelect = y;

	    	    if (b.canSelect(x, y)) {
	    	    	b.select(x, y);
	    	    }
	    	}
	    	/** When a user clicks on space */
	    	if (StdDrawPlus.isSpacePressed()) {
	    		if (b.canEndTurn()) {
	    			b.endTurn();
	    		}
	    	}
	    	StdDrawPlus.show(50);
	    	drawBoard(b, N);
		}
		System.out.println(b.winner());
	}
}