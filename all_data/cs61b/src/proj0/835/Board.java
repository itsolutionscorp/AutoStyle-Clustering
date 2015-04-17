public class Board {
	private boolean emptyBoard = false;
	private Piece[][] pieces = new Piece[8][8];
	// defalt size-8 board
	private int board_size = 8;
	// // Record the status whether or not current player selected a piece.
	// public boolean selected = false; 
	// Record the status whether or not current moved a piece.
	private boolean moved = false;
	// side of current player, the defalt is true because fire side start first.
	private int cur_side = 0;
	// Assign a piece when it is selected. Set to null when current turn end.
	private Piece selected_piece;

	private int selected_x;
	private int selected_y;

	private static void drawBoard_configured(Board b, int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                b.draw_piece(b.pieces[i][j], i, j);
            }
        }
    }

    private static void drawBoard_defalt(Board b, int N) {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
    	}	
	}
	//  starts a GUI supported version of the game.
	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		// Subject to change
		while (true) {
			if (b.emptyBoard == true) {
				b.drawBoard_defalt(b, N);
			}else if (b.emptyBoard == false) {
				b.drawBoard_configured(b, N);
				if (StdDrawPlus.mousePressed()) {
                	double x = StdDrawPlus.mouseX();
                	double y = StdDrawPlus.mouseY();
                	b.select((int)x, (int)y);
            
				}
			}
            if (b.selected_piece != null) {
    		StdDrawPlus.filledSquare(b.selected_x + .5, b.selected_y + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			// System.out.print(b.selected_x);
			// System.out.print(", ");
			// System.out.println(b.selected_y);
		}
			b.draw_piece(b.selected_piece, b.selected_x, b.selected_y);
			if(StdDrawPlus.isSpacePressed()){
				b.endTurn();
				b.winner();
			}

			StdDrawPlus.show(100);
		}

	}
	/* The constructor of Board.
	 * initialize an empty Board if souldBeEmpty is true,
	 * otherwise initialize an defalt configured Board.
	 */
	public Board(boolean shouldBeEmpty) {
		this.emptyBoard = shouldBeEmpty;
		if (shouldBeEmpty == false) {
			this.init_position(this.board_size);
		}
	}
	/* Gets the piece at position (x, y) on the board.
	 * Return null if there is no piece,
	 * or (x, y) are out of bounds.
	 */
	public Piece pieceAt(int x, int y) {
		if (outOfBound(x, y)) {
			return null;
		}

		if (pieces[x][y] == null) {
			return null;
		}

		return pieces[x][y];
	}
	/* Return true if the square at (x, y) can be selected.
	 */
	public boolean canSelect(int x, int y) {
		if (outOfBound(x, y)) {
			return false;
		}
		Piece p = pieceAt(x, y);
	  /* SELECTING A SQUARE WITH PIECE
	   * Current player has not selected a piece yet.
	   * Or current player has selected a piece but has not moved it.
	   */
		if ((p != null) &&
			// checking if is selecting a current side piece
			(p.side() == cur_side)) { 
			if (selected_piece == null) {
				return true;
			}
			if ((selected_piece != null) && 
				(moved == false))       {
				return true;
			}
		}
		else if ((p != null) && (p.side() != cur_side)) {
			return false;
		}
		/* SELECTING AN EMPTY SQUARE
		(1)During this turn, the player has selected a Piece 
		   which hasn’t moved yet and is selecting an empty 
		   spot which is a valid move for the previously 
		   selected Piece.
		(2) During this turn, the player has selected a Piece,
		    captured, and has selected another valid capture
			destination. When performing multi-captures, 
			should only select the active piece once;
			all other selections should be valid destination points.

		 checkpoints:
		 (1) if the selected_piece performed a capture.
		 	- if it performed a capture, 
		 	   * check if the destination satisfies another capture or move.
		 (2) if the selected_piece is allow move up or down or both
		 		then check the validMove for up or down or both.
		*/
		 else if (selected_piece != null) {
		 	// Finding the direction of Move
		 	String direction;
		 	if (selected_piece.isKing()) {
		 		direction = "any";
		 	} else if (selected_piece.isFire()){
		 		direction = "up";
		 	} else {
		 		direction = "down";
		 	}

		 	if ((!selected_piece.hasCaptured()) && (!moved)) {
		 		if ((validMove(selected_x, 
		 				      selected_y, 
		 					  x, y, direction)) ||
		 		  (validCapure(selected_x, 
		 				      selected_y, 
		 					  x, y, direction))) {
		 			return true;
		 		}
		 	}else if(selected_piece.hasCaptured()) {
		 		if (validCapure(selected_x, 
		 					    selected_y, 
		 					    x, y, direction)) {
		 			return true;
		 		}
		 	}
		 }
		 return false;
	}


	/* Select the square at (x, y) 
	 */
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (canSelect(x, y)) {
			if (p != null) {
				selected_piece = p;
				selected_x = x;
				selected_y = y;
			} else {
				moved = true;
				selected_piece.move(x, y);
				selected_x = x;
				selected_y = y;

			}
		}

	}
	/* Place Piece p at sqaure (x, y)
	 */
	public void place(Piece p, int x, int y) {
		/* Places p at (x, y). If (x, y) is out of bounds
		   or if p is null, does nothing. If another piece 
		   already exists at (x, y), p will replace that piece. 
		   (This method is potentially useful for creating 
		   	specific test circumstances.)
		 */

		if (!outOfBound(x, y)) {
			pieces[x][y] = p;
		}
	}

	/* Excute a remove
	 */
	public Piece remove(int x, int y) {
		/* Executes a remove. Returns the piece that was removed. If 
		   the input (x, y) is out of bounds, returns null and prints
		   an appropriate message. If there is no piece at (x, y), 
		   returns null and prints an appropriate message. */
		   if (outOfBound(x, y)) {
		   	System.out.println("Location out of bounds");
		   	return null;
		   }
		   if (pieceAt(x, y) == null) {
		   	System.out.println("Invalid: no piece to remove");
		   	return null;
		   }
		   Piece p = pieceAt(x, y);
		   place(null, x, y);

		return p;
	}
	/* Returns whether or not the the 
	 * current player can end their turn.
	 */
	public boolean canEndTurn() {
		if (moved) {
			return true;
		}
		return false;

	}
	/* Called at the end of each turn. 
	 * Handles switching of players and 
	 * anything else that should happen 
	 * at the end of a turn.
	 */
	public void endTurn() {
		if (canEndTurn()) {
			if (selected_piece != null){
			selected_piece.doneCapturing();
		}
			moved = false;
			cur_side = Math.abs(cur_side - 1); // switch side.
			selected_piece = null;
			// Ending the turn reset all related variable.
		}

	}
	/* Returns the winner of the Board.
	 */
	public String winner() {
		int num_fire_left = 0;
		int num_water_left = 0;
		Piece p;
		for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
            	p = pieceAt(i, j);
            	if (p != null) {
            		if (p.isFire()) {
            			num_fire_left += 1;
            		} else {
            			num_water_left += 1;
            		}
            	}

            }
        }
        if ((num_water_left == 0) && (num_fire_left > 0)){
        	return "Fire";
        } else if ((num_water_left > 0) && num_fire_left == 0) {
        	return "Water";
        } else if (((num_water_left == 0) && (num_fire_left == 0))) {
        	return "Tie";
        } else{
        	return null;
        }
	}

	private void init_position(int N) {
		pieces = new Piece[N][N];
		Piece p;
		board_size = N;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

				if 		((i % 2 == 0) && (j == 0)) {
					p = new Piece(true, this, i, j, "pawn");
				} // put pawn-fire at initial position.
				else if ((i % 2 != 0) && (j == 1)) {
					p = new Piece(true, this, i, j, "shield");
				} // put shield-fire at initial position
				else if ((i % 2 == 0) && (j == 2)) {
					p = new Piece(true, this, i, j, "bomb");
				} // put bomb-fire at initial position
				else if ((i % 2 != 0) && (j == (N - 3))) {
					p = new Piece(false, this, i, j, "bomb");
				} // put bomb-water at initial position
				else if ((i % 2 == 0) && (j == (N - 2))) {
					p = new Piece(false, this, i, j, "shield");
				} // put shield-water at initial position
				else if ((i % 2 != 0) && (j == (N - 1))) {
					p = new Piece(false, this, i, j, "pawn");
				} // put pawn-water at initial position
            }
        }
	}
	private void draw_piece(Piece p, int i, int j) {
		if (p != null) {
			String type;
			if (p.isBomb()) {
				type = "bomb";
			} else if (p.isShield()) {
				type = "shield";
			} else {
				type = "pawn";
			}
			boolean isFire = p.isFire();
			boolean isKing = p.isKing();
			String img_address = "img/";
			img_address += type;
			if (isFire) {
				img_address += "-fire";
			} else {
				img_address += "-water";
			}
			if (isKing) {
				img_address += "-crowned";
			}
			img_address += ".png";
			StdDrawPlus.picture(i + .5, j + .5, img_address, 1, 1);

		}
	}

	private boolean validCapure(int xi, int yi, int xf, int yf, String direction) {
		// return true if is a valid capture.
		int dx = xf - xi;
		int dy = yf - yi;
		if ((Math.abs(dx) == 2) && (Math.abs(dy) == 2))  {
			if ((pieces[xi + dx/2][yi + dy/2] != null) && 
				((pieces[xi + dx/2][yi + dy/2].side() != cur_side))) { 
				if ((dy == 2) && (direction.equals("up"))) {
					return true;
				} else if ((dy == -2) && (direction.equals("down") )) {
					return true;
				} else if (direction.equals("any")) {
					return true;
				}
			}
		}
		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf, String direction) {
		// return true if is a valid move.
		int dx = xf - xi;	
		int dy = yf - yi;
		  if ((Math.abs(dx) == 1) && (Math.abs(dy) == 1)) {
			if  (pieces[xi + dx][yi + dy] == null) { 
				if ((dy == 1) && (direction.equals("up"))) {
					return true;
				} else if ((dy == -1) && (direction.equals("down"))) {
					return true;
				} else if (direction.equals("any")) {
					return true;
				}
			}	
		}

	return false;
	}
	private boolean outOfBound(int x, int y) {
		if ((x < board_size) 	   	&&
			(y < board_size)       	&&
			(x >= 0) && (y >= 0)) {
			return false;
		} 
		return true;

	}
}