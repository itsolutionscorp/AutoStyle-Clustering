public class Board {
	

	private Piece[][] pieces;
	private Piece selected_piece;
	private boolean moved = false;
	private int player = 1;
	private int highlight_x = 0;
	private int highlight_y = 0;
	private boolean start_highlighting = false;

	/** 
	* Each board should probably contain a dictionary of pieces? 
	* The keys should be the coordinates, the entry is the piece
	* are we allowed to use these structures?
	**/
	public Board(boolean shouldBeEmpty) {
		
		if (shouldBeEmpty) {
			pieces = new Piece[8][8];
		}
		else {
			pieces = new Piece[8][8];
			for (int row = 0; row < 8; row++) {
				for (int column = 0; column < 8; column++)  {
					if (row % 2 == 0) {
						if (column % 2 == 0) {
							if (row == 0) {
								pieces[row][column] = 
								new Piece(true, this, column, row, "pawn");
							}
							if (row == 2) {
								pieces[row][column] = 
								new Piece(true, this, column, row, "bomb");
							}
							if (row == 6) {
								pieces[row][column] = 
								new Piece(false, this, column, row, "shield");
							}
						}

					}
					if (row % 2 != 0) {
						if (column % 2 != 0) {
							if (row == 1) {
								pieces[row][column] = 
								new Piece(true, this, column, row, "shield");
							}
							if (row == 5) {
								pieces[row][column] = 
								new Piece(false, this, column, row, "bomb");
							}
							if (row == 7) {
								pieces[row][column] = 
								new Piece(false, this, column, row, "pawn");
							}

						}
					}
				}
			}
		}
	}

		/**
	*if there is a piece at the selected coordinates, return that piece
	*Returns null if there is non piece or if the coords are out of bounds.
	*do I need to make a datastructure for pieces?
	**/
	public Piece pieceAt(int x, int y) {
		if (x > 8 || y > 8)	{
			return null;
		}
		return this.pieces[y][x];
	}

	/**
	* Places p at (x, y). If (x, y) is out of bounds or if p is null, 
	* does nothing. If p already exists in the current Board, first 
	* removes it from its initial position. If another piece already exists 
	* at (x, y), p will replace that piece. (This method is potentially 
	* useful for creating specific test circumstances.)
	**/
	//(b.place(shield, 3, 3)
	public void place(Piece p, int x, int y) {
		if ((p != null) && !(x > 8 || y > 8)) {
			this.pieces[y][x] = p;
		}
	}

	/** Executes a remove. Returns the piece that was removed. If 
	* the input (x, y) is out of bounds, returns null and prints 
	* an appropriate message. If there is no piece at (x, y), 
	* returns null and prints an appropriate message.
	**/
	public Piece remove(int x, int y){
		if (pieceAt(x, y) == null) {
			System.out.println("No Piece at (" + x + ", " + y + ").");
			return null;
		}

		if (x > 8 || y > 8) {
			System.out.println("Input is out of bounds.");
			return null;
		}
		else {
			Piece temp = this.pieces[y][x];
			this.pieces[y][x] = null;
			return temp;
		}
	}	



	/**
	* you can select a piece if: you have not selected a piece yet, if your
	* selected piece has not yet made a move.
	* you can select an empty square if: the player has selected a piece which 
	* has not moved yet and is selecting an empty spot which is a valid move 
	* for the previously selected piece, if this turn the player has selected 
	* a piece, captured, and is selecting another valid capture designation. 
	*
	* I'm so sorry that this code is so shitty. I figured out I could save 
	* the coordinates of the mouse press like way too late to make this pretty.
	* At least it works? Please forgive me. Here's a joke to make things better.
	* A man walks into a library, goes up to the receptionist's desk, and asks,
	* "can I have a cheeseburger?" The librarian replies, "Sir, this is a library."
	* The man, shocked, apologizes profusely, and then whispers "Can I have a cheeseburger?"
	* This joke works a lot better in spoken form.
	**/
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if ((p == null) && (selected_piece == null)) {
			return false;
		}
		if ((selected_piece == null) && (p != null)) {
			if (player == 1) {
				if (p.isFire()) {
					return true;
				}
				return false;
			}
			if (player == 2) {
				if (p.isFire()) {
					return false;
				}
				return true;
			}
		}
		if ((selected_piece != null) && (moved == false) && (p != null)) {
			return true;
		}
		if ((p == null)  && (selected_piece != null) && (moved == false)) { 
				if(player == 1) {
					try {
						if (pieceAt(x + 1 , y - 1) == selected_piece) {
							return true;
						}
					} catch (IndexOutOfBoundsException e) {
						if (y==0) {
							return false;
						}
						if (selected_piece == pieceAt(x - 1, y - 1)) {
							return true;
						}
					}
					try {
						if (pieceAt(x - 1 , y - 1) == selected_piece) {
							return true;
						}
					} catch (IndexOutOfBoundsException e) {
						if (y==0) {
							return false;
						}
						if (selected_piece == pieceAt(x + 1, y - 1)) {
							return true;
						}
					}
					try{
						if (pieceAt(x + 2, y - 2) == selected_piece) {
							if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y-1).isFire() != true)) {
								return true;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						if (y < 2) {
							return false;
						}
						if (selected_piece == pieceAt(x - 2, y - 2)) {
							if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y-1).isFire() != true)) {
							return true;
							}
						}
					}
					try {
						if (selected_piece == pieceAt(x - 2, y - 2)) {
							if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y-1).isFire() != true)) {
								return true;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						if (y < 2) {
							return false;
						}
						if (pieceAt(x + 2, y - 2) == selected_piece) {
							if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y-1).isFire() != true)) {
								return true;
							}
						}
					}
					if (selected_piece.isKing()) {
						try {
							if (pieceAt(x + 1 , y + 1) == selected_piece) {
								return true;
							}
						} catch (IndexOutOfBoundsException e) {
							if (y == 7) {
								return false;
							}
							if (selected_piece == pieceAt(x - 1, y + 1)) {
								return true;
							}
						}
						try {
							if (pieceAt(x - 1 , y + 1) == selected_piece) {
								return true;
							}
						} catch (IndexOutOfBoundsException e) {
							if (y == 7) {
								return false;
							}
							if (selected_piece == pieceAt(x + 1, y + 1)) {
								return true;
							}
						}
						try {
							if (pieceAt(x + 2, y + 2) == selected_piece) {
								if 	((pieceAt(x+1, y + 1) != null) && (pieceAt(x+1, y + 1).isFire() != true)){
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y > 5) {
								return false;
							}
							if (selected_piece == pieceAt(x - 2, y + 2)) {
								if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x-1, y + 1).isFire() != true)) {
									return true;
								}
							}
						}
						try {
							if (selected_piece == pieceAt(x - 2, y + 2)) {
								if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x-1, y + 1).isFire() != true)) {
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y > 5) {
								return false;
							}
							if (pieceAt(x + 2, y + 2) == selected_piece) {
								if 	((pieceAt(x+1, y + 1) != null) && (pieceAt(x+1, y + 1).isFire() != true)){
									return true;
								}
							}
						}
					}
				}
				if(player == 2) {
					try {
						if (pieceAt(x + 1, y + 1) == selected_piece) {
								return true;
						}
					} catch (IndexOutOfBoundsException e) {
						if (y == 7){
							return false;
						}
						if (selected_piece == pieceAt(x - 1, y + 1)) {
							return true;
						}
					}
					try {
						if (pieceAt(x - 1 , y + 1) == selected_piece) {
							return true;
						}
					} catch (IndexOutOfBoundsException a) {
						if (y==7) {
							return false;
						}
						if (selected_piece == pieceAt(x + 1, y + 1)) {
							return true;
						}
						
					}
					try {
						if (pieceAt(x + 2, y + 2) == selected_piece) {
							if 	((pieceAt(x+1, y + 1) != null) && (pieceAt(x+1, y + 1).isFire() == true)){
									return true;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						if (y > 5) {
							return false;
						}
						if (selected_piece == pieceAt(x - 2, y + 2)) {
							if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x-1, y + 1).isFire() == true)) {
								return true;
							}
						}
					}
					try {
						if (selected_piece == pieceAt(x - 2, y + 2)) {
							if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x-1, y + 1).isFire() == true)) {
								return true;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						if (y > 5) {
							return false;
						}
						if (pieceAt(x + 2, y + 2) == selected_piece) {
							if 	((pieceAt(x+1, y + 1) != null) && (pieceAt(x+1, y + 1).isFire() == true)){
								return true;
							}
						}
					}
					if (selected_piece.isKing()) {
						try {
							if (pieceAt(x + 1 , y - 1) == selected_piece) {
								return true;
							}
						} catch (IndexOutOfBoundsException e) {
							if (y == 0) {
								return false;
							}
							if (selected_piece == pieceAt(x - 1, y - 1)) {
								return true;
							}
						}
						try {
							if (pieceAt(x - 1 , y - 1) == selected_piece) {
								return true;
							}
						} catch (IndexOutOfBoundsException e) {
							if (y == 0) {
								return false;
							}
							if (selected_piece == pieceAt(x + 1, y - 1)) {
								return true;
							}
						}
						try {
							if (pieceAt(x + 2, y -2) == selected_piece) {
								if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y - 1).isFire() == true)){
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y <2) {
								return false;
							}
							if (selected_piece == pieceAt(x - 2, y -2)) {
								if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y - 1).isFire() == true)) {
									return true;
								}
							}
						}
						try {
							if (selected_piece == pieceAt(x - 2, y -2)) {
								if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y - 1).isFire() == true)) {
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y <2) {
								return false;
							}
							if (pieceAt(x + 2, y -2) == selected_piece) {
								if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y - 1).isFire() == true)){
									return true;
								}
							}
						}
					}
				}
		
			
		}
		if ((p == null)  && (selected_piece != null) && selected_piece.hasCaptured()) {
			if (p == null) { 
				if(player == 1) {
					try {
						if (pieceAt(x + 2, y - 2) == selected_piece) {
							if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y - 1).isFire() != true)) {
								return true;
							}
						}
					} 
					catch (IndexOutOfBoundsException e) {
						if (y <2) {
								return false;
						}
						if (selected_piece == pieceAt(x - 2, y - 2)) {
							if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y - 1).isFire() != true)) {
							return true;
							}
						}
					}
					try {
						if (selected_piece == pieceAt(x - 2, y - 2)) {
							if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y - 1).isFire() != true)) {
								return true;
							}
						}
					} catch (IndexOutOfBoundsException e){
						if (y <2) {
							return false;
						}
						if (pieceAt(x + 2, y - 2) == selected_piece) {
							if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y - 1).isFire() != true)) {
								return true;
							}
						}

					}
					if (selected_piece.isKing()) {
						try {
							if (pieceAt(x + 2, y + 2) == selected_piece) {
								if 	((pieceAt(x+1, y + 1) != null) && (pieceAt(x+1, y + 1).isFire() != true)){
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y > 5) {
								return false;
							}
							if (selected_piece == pieceAt(x - 2, y + 2)) {
								if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x-1, y + 1).isFire() != true)) {
									return true;
								}
							}
						}
						try {
							if (selected_piece == pieceAt(x - 2, y + 2)) {
								if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x-1, y + 1).isFire() != true)) {
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y > 5) {
								return false;
							}
							if (pieceAt(x + 2, y + 2) == selected_piece) {
								if 	((pieceAt(x+1, y + 1) != null) && (pieceAt(x+1, y + 1).isFire() != true)){
									return true;
								}
							}
						}
						
					}
				}
				if(player == 2) {
					try {
						if (pieceAt(x + 2, y + 2) == selected_piece) {
							if 	((pieceAt(x + 1, y + 1) != null) && (pieceAt(x + 1, y + 1).isFire() == true)) {
								return true;
							}
						}
					}
					catch (IndexOutOfBoundsException e) {
						if (y > 5) {
							return false;
						}
						if (selected_piece == pieceAt(x - 2, y + 2)) {
							if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x - 1, y + 1).isFire() == true)) {
								return true;
							}
						}
					}
					try {
						if (selected_piece == pieceAt(x - 2, y + 2)) {
							if ((pieceAt(x-1, y + 1) != null) && (pieceAt(x - 1, y + 1).isFire() == true)) {
								return true;
							}
						}
					} catch (IndexOutOfBoundsException e){
						if (y > 5) {
							return false;
						}
						if (pieceAt(x + 2, y + 2) == selected_piece) {
							if 	((pieceAt(x + 1, y + 1) != null) && (pieceAt(x + 1, y + 1).isFire() == true)) {
								return true;
							}
						}
					}
					if (selected_piece.isKing()) {
						try {
							if (pieceAt(x + 2, y - 2) == selected_piece) {
								if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y - 1).isFire() == true)){
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y < 2) {
								return false;
							}
							if (selected_piece == pieceAt(x - 2, y -2)) {
								if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y - 1).isFire() == true)) {
									return true;
								}
							}
						}
						try {
							if (selected_piece == pieceAt(x - 2, y -2)) {
								if ((pieceAt(x-1, y - 1) != null) && (pieceAt(x-1, y - 1).isFire() == true)) {
									return true;
								}
							}
						} catch (IndexOutOfBoundsException e) {
							if (y < 2) {
								return false;
							}
							if (pieceAt(x + 2, y -2) == selected_piece) {
								if 	((pieceAt(x+1, y - 1) != null) && (pieceAt(x+1, y - 1).isFire() == true)){
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	* Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	* or capture to (xf, yf) in a valid fashion compatible with the rules.
	* relies on canSelect a lot
	**/
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece temp = pieceAt(xi, yi);
		if (temp != null) { // if there is a piece
			if (canSelect(xi, yi)) { //if you can select the piece
				if (canSelect(xf, yf)) { //if you can select the place you want to move those
					return true;
				}
			}
		}
		return false;

	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selected_piece = pieceAt(x, y);
		}
		else {
			selected_piece.move(x, y);
			moved = true;
			
		}

	}


	public boolean canEndTurn() {
		return moved;
	}

	/**
	* Called at the end of each turn. Handles switching of players 
	* and anything else that should happen at the end of a turn.
	**/
	public void endTurn() {
		if (player == 1 && moved) {
			player = 2;
			moved = false; 
		}
		if (player == 2 && moved) {
			player = 1;
			moved = false;
		}
		selected_piece.doneCapturing();
		selected_piece = null;

		

	}


	/**
	* Returns the winner of the game. "Fire", "Water", "No one" 
	* (tie / no pieces on the board), or null if the game is not 
	* yet over. Assume there is no stalemate situation in which 
	* the current player has pieces but cannot legally move any 
	* of them (In this event, just leave it at null). Determine 
	* the winner solely by the number of pieces belonging to each team.
	**/
	public String winner() {
		int firepieces = 0;
		int waterpieces = 0;
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
    			if (pieces[row][column] != null) {
    				if (pieces[row][column].isFire()) {
    					firepieces += 1;
    				}
    				else {
    					waterpieces += 1;
    				}
    			}
			}
		}
		if ((firepieces != 0) && (waterpieces != 0)) {
			return null;
		}
		else {
			if (firepieces > waterpieces) {
				return "Fire";
			}
			if (firepieces < waterpieces) {
				return "Water";
			}
			if (firepieces == waterpieces) {
				return "No one";
			}
		}
		return null;
	}

	private void drawBoard(int N) {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if ((row + column) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(column + .5, row + .5, .5);
            }
        }
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (this.pieces[row][column] != null) {
                    if (pieces[row][column].isFire()) {
                    	if (pieces[row][column].isKing()) {
                    		if (pieces[row][column].isShield()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/shield-fire-crowned.png", 1, 1);
                    		}
                    		else if (pieces[row][column].isBomb()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/bomb-fire-crowned.png", 1, 1);
                    		}
                    		else {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/pawn-fire-crowned.png", 1, 1);
                    		}
                    	}
                    	else {
                    		if (pieces[row][column].isShield()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/shield-fire.png", 1, 1);
                    		}
                    		else if (pieces[row][column].isBomb()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/bomb-fire.png", 1, 1);
                    		}
                    		else {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/pawn-fire.png", 1, 1);
                    		}
                    	 }

                	}
                	if (pieces[row][column].isFire() == false) {
                		if (pieces[row][column].isKing()) {
                    		if (pieces[row][column].isShield()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/shield-water-crowned.png", 1, 1);
                    		}
                    		else if (pieces[row][column].isBomb()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/bomb-water-crowned.png", 1, 1);
                    		}
                    		else {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/pawn-water-crowned.png", 1, 1);
                    		}
                    	}
                    	else {
                    		if (pieces[row][column].isShield()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/shield-water.png", 1, 1);
                    		}
                    		else if (pieces[row][column].isBomb()) {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/bomb-water.png", 1, 1);
                    		}
                    		else {
                    			StdDrawPlus.picture(column + .5, row + .5, 
                    				"img/pawn-water.png", 1, 1);
                    		}
                    	}
                	}
 	           }
    	    }
    	}
	}

	private void drawPiece(Piece p) {
		if (p.isFire()) {
        	if (p.isKing()) {
        		if (p.isShield()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/shield-fire-crowned.png", 1, 1);
        		}
        		else if (p.isBomb()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/bomb-fire-chrowned.png", 1, 1);
        		}
        		else {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/pawn-fire-crowned.png", 1, 1);
        		}
        	}
        	else {
        		if (p.isShield()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/shield-fire.png", 1, 1);
        		}
        		else if (p.isBomb()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/bomb-fire.png", 1, 1);
        		}
        		else {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/pawn-fire.png", 1, 1);
        		}
        	 }

    	}
    	if (p.isFire() == false) {
    		if (p.isKing()) {
        		if (p.isShield()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/shield-water-crowned.png", 1, 1);
        		}
        		else if (p.isBomb()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/bomb-water-crowned.png", 1, 1);
        		}
        		else {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/pawn-water-crowned.png", 1, 1);
        		}
        	}
        	else {
        		if (p.isShield()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/shield-water.png", 1, 1);
        		}
        		else if (p.isBomb()) {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/bomb-water.png", 1, 1);
        		}
        		else {
        			StdDrawPlus.picture(highlight_x + .5, highlight_y + .5, 
        				"img/pawn-water.png", 1, 1);
        		}
        	}
    	}
	}

	private void highlight(double x, double y) {
		if (start_highlighting) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        	if (pieceAt((int)x, (int)y)!= null) {
        		drawPiece(pieceAt((int)x, (int )y));
        	}
        }
	}


	/**
	* Runs a game
	**/
	public static void main(String args[]) {
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(b.winner()== null) {
        	if (StdDrawPlus.isSpacePressed()){
        		if (b.canEndTurn()) {
        			System.out.println("player " + b.player +" has ended their turn.");
        			b.endTurn();
        		}
        	}

            b.drawBoard(N);    
            while (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.start_highlighting = true;
                	b.select((int) x, (int) y);
                	b.highlight_x = (int) StdDrawPlus.mouseX();
                	b.highlight_y = (int) StdDrawPlus.mouseY();
                }
            }
            b.highlight(b.highlight_x, b.highlight_y);
            StdDrawPlus.show(100);

		}
		System.out.println(b.winner() + " has won!");
	}
}