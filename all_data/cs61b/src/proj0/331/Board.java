public class Board {
	private Piece[][] pieces;
	private int N = 8; // Board size
	private boolean fire_turn = true;
	private Piece current_piece = null;
	private int current_x = -1;
	private int current_y = -1;
	private boolean moved_already = false;

	// Constructor
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		if (shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.pieces[j][i] = null;
				}
			}
		} else {
			boolean isFire;
			String type;
			Piece piece;
			for (int i = 0; i < N; i++) { // i is row index (i.e. y-value)
				for (int j = 0; j < N; j++) { // j is column index (i.e . x-value)
					if ((i + j) % 2 != 0) {
						continue; // Skip red squares
					}
					if (j == 0 || j == 1 || j == 2) {
						isFire = true;
					} else if (j == N-1 || j == N-2 || j == N-3) {
						isFire = false;
					} else {
						continue; // Skip rows with no initial pieces
					}
					if (j == 0 || j == N-1) {
						type = "pawn";
					} else if (j == 1 || j == N-2) {
						type = "shield";
					} else {
						type = "bomb";
					}
					pieces[i][j] = new Piece(isFire, this, i, j, type);
				}
			}
		}
	}

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
        Adapted from StdDrawDemo.java
     */
    private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (current_piece != null && current_x == i && current_y == j) {
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	} else if((i + j) % 2 == 0) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	} else {
            		StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    // Draw piece in given square
    private void drawPiece(Piece piece, int x, int y) {
    	if (piece != null) {
    	    String color = "water";
    		if (piece.isFire()) {
    			color = "fire";
    		}
    		String crown = "";
    		if (piece.isKing()) {
    			crown = "-crowned";
    		}
    		String type;
    		if (piece.isBomb()) {
    			type = "bomb";
    		} else if (piece.isShield()) {
    			type = "shield";
    		} else {
    			type = "pawn";
    		}

    		String f_name = "img/" + type + "-" + color + crown + ".png";
    		StdDrawPlus.picture(x + .5, y + .5, f_name, 1, 1);	
    	}
    }

    // Draw all pieces
    private void drawPieces() {
		for (int i = 0; i < N; i++) { // i is column index (i.e. x-value)
			for (int j = 0; j < N; j++) { // j is row index (i.e . y-value)
				drawPiece(pieceAt(i, j), i, j);
			}
		}
    }

	// Gets piece at position (x, y) on board, or null if no piece there
	// If (x, y) out of bounds, returns null
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) { // If (x, y) is out of bounds
			return null;
		}
		return pieces[x][y]; // Return the piece at (x, y)
	}

	// Returns true if a piece at (x, y) and can be selected, or empty space can be selected
	public boolean canSelect(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false; // Because (x, y) is out of bounds
		}

		Piece piece = pieceAt(x, y);

		// Deal with selecting a piece in (x, y)
		if (piece != null) { // A piece at (x, y)
			if (piece.isFire() == fire_turn) { // Piece belong to player whose move it is
				if (moved_already == false) { // If didn't move already
					return true;
				}
			}
		}

		// Deal with selecting an empty square
		if (piece == null) { // No piece at (x, y)
			if (current_piece != null) { // There is a currently selected piece
				if ((validMove(current_x, current_y, x, y))) { // If current_piece can validy move to (x, y)
					return true;
				}
			}
		}

		return false;
	}

	// Returns true if piece at (xi, yi) can more to, or capture to, (xf, yf) in a valid fashioin
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 0 || xi >= N || yi < 0 || yi >= N || xf < 0 || xf >= N || yf < 0 || yf >= N) {
			return false; // Because one of the coordinates is out of bounds
		}
		
		Piece pi = pieceAt(xi, yi);
		Piece pf = pieceAt(xf, yf);

		if (pi == null) { // If no piece at xi, yi for some reason....
			return false;
		}

		// Fire or kinged piece moving up to open space (piece didn't already capture)
		if ((pi.isFire() || pi.isKing()) && !moved_already) {
			if ((yf - yi == 1) && Math.abs(xi - xf) == 1) {
				if (pf == null) {
					return true;
				}
			}
		}

		// Water or kinged piece moving down to open space (piece didn't already capture)
		if ((!pi.isFire() || pi.isKing()) && !moved_already) {
			if ((yf - yi == -1) && Math.abs(xi - xf) == 1) {
				if (pf == null) {
					return true;
				}
			}
		}

		// Fire or kinged piece capturing up
		if (pi.isFire() || pi.isKing()) {
			if ((yf - yi == 2) && Math.abs(xi - xf) == 2) {
				// Intermediate piece at average coordinate value
				Piece pm = pieceAt((xf + xi)/2, (yf + yi)/2);
				if (pm == null) { // If no intermediate piece
					return false;
				}
				if (pf == null) {
					if (pi.isFire() != pm.isFire()) {
						return true;
					}
				}
			}
		}

		// Water or kinged piece capturing down
		if (!pi.isFire() || pi.isKing()) {
			if ((yf - yi == -2) && Math.abs(xi - xf) == 2) {
				// Intermediate piece at average coordinate value
				Piece pm = pieceAt((xf + xi)/2, (yf + yi)/2);
				if (pm == null) { // If no intermediate piece
					return false;
				}
				if (pf == null) {
					if (pi.isFire() != pm.isFire()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	// Select piece at (x, y) if possible
	public void select(int x, int y) {
		Piece piece = pieceAt(x, y);

		if (piece != null) {
			current_piece = piece;
		} else {
			current_piece.move(x, y);
			moved_already = true;
		}
		current_x = x;
		current_y = y;
		if (current_piece.hasCaptured()) { // If this piece captured this turn and is a bomb
            bombExplosion(current_x, current_y); // Deal with bomb explosion
        }
	}

	// Places p at (x, y)
	public void place(Piece p, int x, int y) {
		if (p != null) {
			if (x >= 0 && x < N && y >= 0 && y < N){ // Test if (x, y) is in bounds
				Piece[] piece = {p};
				System.arraycopy(piece, 0, pieces[x], y, 1);
			}
		}
	}

	// Removes a piece and returns it
	public Piece remove(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) { // Out of bounds
			System.out.println(String.format("(%d, %d) out of bounds", x, y));
			return null;
		}

		Piece[] piece = new Piece[1];
		System.arraycopy(pieces[x], y, piece, 0, 1); // To avoid problem with pointers changing before return
		if (piece[0] == null) { // No piece at (x, y)
			System.out.println(String.format("No piece at (%d, %d)", x, y));
			return null;
		}
		pieces[x][y] = null;
		return piece[0];
	}

	// Returns whether or not the current player can end their turn
	public boolean canEndTurn() {
		return moved_already; // Since this is true iff a piece has been moved this turn
	}

	// Called at end of each turn, handles switching of players and anything else that happens at end of turn
	public void endTurn() {
		if (current_piece != null) { // If there is still a current piece, i.e. a bomb that didn't explode
			current_piece.doneCapturing(); // Finish capturing with current piece
		}
		fire_turn = !fire_turn; // Changes current player's turn
		moved_already = false; // Since on the next turn, the player wouldn't have moved already
		current_piece = null; // Since no piece is selected for the next turn
	}

	// Returns the winner of the game
	public String winner() {
		boolean any_fire = false; // true iff there is a fire piece left
		boolean any_water = false; // true iff there is a water piece left
		for (int i = 0; i < N; i++) { /* Could use a while loop that quits if both are true, but I'm lazy, and not much faster on 8x8 board */
			for (int j = 0; j < N; j++) {
				if (pieces[j][i] != null) {
					if (pieces[j][i].isFire()) {
						any_fire = true;
					} else {
						any_water = true;
					}
				}
			}
		}

		if (any_fire && !any_water) { // Only fire left
			return "Fire";
		} else if (any_water && !any_fire) { // Only water left
			return "Water";
		} else if (!any_fire && !any_water) { // No pieces left
			return "No one";
		} else {
			return null;
		}
	}

	// Deals with bomb explosion if it captured this turn
	// Call wheh hasCaptured == true
	private void bombExplosion(int x, int y) {
		if (current_piece.isBomb()) {
			Piece piece_aoe;
			for (int x2 = x - 1; x2 <= x + 1; x2++) {
				if (x2 < 0 || x2 >= N) { // Skip if out of bounds
					continue;
				}
				for (int y2 = y - 1; y2 <= y + 1; y2++) { // Go through 3x3 grid around bomb
					if (y2 < 0 || y2 >= N) { // Skip if out of bounds
						continue;
					}
					piece_aoe = pieceAt(x2, y2); // Get piece in position (x, y)
					if (piece_aoe == null) { // Skip if no piece there
						continue;
					}
					if (!piece_aoe.isShield()) {
						pieces[x2][y2] = null;
					}
				}
			}
			current_piece = null;
		}
	}


	public static void main(String[] args) {
		Board b = new Board(false); // Initialize board
		//StdDrawPlus.setXscale(0, b.N);
        //StdDrawPlus.setYscale(0, b.N);
		b.drawBoard(b.N); // Draw the board
		b.drawPieces();
		StdDrawPlus.show(100);

		double start_x = -1.0;
		double start_y = -1.0;
		double end_x = -1.0;
		double end_y = -1.0;
		while (b.winner() == null) { // While the game is not over....
			b.drawBoard(b.N);
			b.drawPieces(); // Draw the current configuration
			StdDrawPlus.show(10);

			while (!b.moved_already) {
				while (b.current_piece == null) {
					if (StdDrawPlus.mousePressed()) { // If the mouse is pressed
                		start_x = StdDrawPlus.mouseX(); // x of chosen piece
                		start_y = StdDrawPlus.mouseY(); // y of chosen piece
                	}
                	if (b.canSelect((int) start_x, (int) start_y)){ // If the selection is valid
                		b.select((int) start_x, (int) start_y); // Select piece in given squar
                	}
				}

				b.drawBoard(b.N);
				b.drawPieces();
				StdDrawPlus.show(10);

				while (end_x < 0) {
					if (StdDrawPlus.mousePressed()) { // If the mouse is pressed
                		end_x = StdDrawPlus.mouseX(); // x of chosen tile
                		end_y = StdDrawPlus.mouseY(); // y of chosen tile
            		}
            	}
                if (b.canSelect((int) end_x, (int) end_y)){ // If the selection is valid
            		b.select((int) end_x, (int) end_y);
            	}
            	if (!b.moved_already) {
            		end_x = -1.0;
            		continue;
            	}
            }
			b.drawBoard(b.N);
			b.drawPieces();
			StdDrawPlus.show(10);

			while (!StdDrawPlus.isSpacePressed()) {
				if (b.current_piece != null) {
					if (StdDrawPlus.mousePressed()) { // If the mouse is pressed
	                	end_x = StdDrawPlus.mouseX(); // x of chosen tile
	                	end_y = StdDrawPlus.mouseY(); // y of chosen tile
	            	}
					if (b.canSelect((int) end_x, (int) end_y)) {
						b.select((int) end_x, (int) end_y);
	            	}
					b.drawBoard(b.N);
					b.drawPieces();
					StdDrawPlus.show(10);
				}
			}

            	start_x = -1.0;
            	end_x = -1.0;

				b.drawBoard(b.N); // Draw the board
				b.drawPieces();
				StdDrawPlus.show(100);

            b.endTurn(); // Ends turn
		}

		System.out.println(String.format("%s Won!!!", b.winner()));
	}

}