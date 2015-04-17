public class Board {

	private int size = 8;
	private Piece[][] pieces = new Piece[size][size];
	private int selected_x = -1;
	private int selected_y = -1;
	private boolean fire_turn = true;
	private boolean moved = false;
	
	public static void main (String[] args) {
		Board b = new Board(false);

		while(true) {

			b.drawBoard();

			if ((StdDrawPlus.mousePressed())) {

				double x_mouse = StdDrawPlus.mouseX();
				double y_mouse = StdDrawPlus.mouseY();
				int x = (int) x_mouse;
				int y = (int) y_mouse;

				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
			}

			if (StdDrawPlus.isSpacePressed()) {

				if(b.canEndTurn()) {
					b.endTurn();
				}
			}	
			StdDrawPlus.show(25);
		}	
	} 

	private void drawBoard() {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);

		for (int i = 0; i < size; i++) {				
			for (int j = 0; j < size; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} 
				else  {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				if (i == selected_x && j == selected_y) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				Piece p = pieceAt(i, j);

				/* Gets the image for each piece and draws the 
				 * pieces into the board */
				if (p != null) {
					String image = "";
					String side = "";
					if (p.isFire()) {
						side = "fire";
					}
					else {
						side = "water";
					}
					if(!p.isShield() && !p.isBomb()) {
						image = "img/pawn-".concat(side);
					}

					if(p.isBomb()) {
						image = "img/bomb-".concat(side);
					}
					if(p.isShield()) {
						image = "img/shield-".concat(side);
					}

					/* Checks if the piece is a king. If the piece is a 
					 * king, changes the piece's image to a king image */
					if(p.isKing()) {
						image = image.concat("-crowned");
					}

					image = image.concat(".png");

					StdDrawPlus.picture(i + .5, j + .5, image, 1, 1);
				}
			}
		}
	}

	/* Gets the coordinates of a piece */
	private int[] getXY(Piece p) {
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j++) {
				Piece piece = pieceAt(i, j);
				if (piece == p) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	public Board(boolean shouldBeEmpty) {
		/* Puts all the pieces in an array with their 
		 * starting positions */
		if (!shouldBeEmpty) {
			for (int i = 0; i < size; i++) { 
				if (i % 2 == 0) {
					pieces[i][0] = new Piece(true, this, i, 0, "pawn");  
					pieces[i][2] = new Piece(true, this, i, 2, "bomb");  
					pieces[i][6] = new Piece(false,this, i, 6, "shield");
				} 
				else {
					pieces[i][1] = new Piece(true, this, i, 1, "shield");
					pieces[i][5] = new Piece(false, this, i, 5, "bomb");  
					pieces[i][7] = new Piece(false, this, i, 7, "pawn");  
				}
			}
		}
	} 

	public Piece pieceAt(int x, int y) {
		if (!boundsCheck(x, y) || pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y); 
		/* Checks to see if there is a pieceat x and y. If there is no piece, 
		 * checks to see if the player can move to the selected square */       
		if (p == null) {
			return canSelectSquare(x, y);
		} 
		/* Checks to see if the piece can by selected */
		else {
			return canSelectPiece(p, x, y);
		}
	}

	/* Checks to see if the player has moved yet and if 
	 * the player is moving a piece that is on the same side */
	private boolean canSelectPiece(Piece p, int x, int y ) {
    	if (!moved && (fire_turn == p.isFire())) {
    		return true;
        }
        return false;
    }

    private boolean canSelectSquare(int x, int y ) {

    	Piece p = pieceAt(selected_x, selected_y);

    	/* Checks to see if the player has already selected a piece */
    	if((selected_x != -1) && (selected_y != -1)) {

    		/* Checks if the player has moved or not. If the player hasn't moved, 
    		 * checks if the move is valid or not */
    		if (!moved) {
    			return validMove(selected_x, selected_y, x, y);
    		}

    		/* Checks if the player has moved and has captured a piece */
    		if (p != null) {
    			if(moved && p.hasCaptured() && (Math.abs(selected_x - x) == 2) && (Math.abs(selected_y - y) == 2)) {
    				return validMove(selected_x, selected_y, x, y);
    			}
    		}
    	}
    	return false;
    }

    /* Checks if a selected spot is in the board */
    private boolean boundsCheck(int x, int y) {
    	if(x >= 0 && x < size && y >= 0 && y < size) {
    		return true;
    	}
    	return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {

    	/* Immediately returns false if the selected spot is not in the board */
    	if (boundsCheck(xi, yi) == false || boundsCheck(xf, yf) == false) {
    		return false;
    	}

		/* Gets the piece at xi and yi */
		Piece p = pieceAt(xi, yi);

		/* Checks if there is already a piece at xf and yf */
		if (p == null || pieceAt(xf, yf) != null) {
			return false;
		}


		int x_gap = xf - xi;
		int y_gap = yf - yi;
		int x_abs = Math.abs(x_gap);
		int y_abs = Math.abs(y_gap);

		/* Checks if the player is moving the piece in a square 
		 * and not moving more than two spaces. If not, returns false */
		if(x_abs != y_abs || x_abs > 2 || y_abs > 2) {
			return false;
		}

		/* Checks a regular move. Makes sure the piece is a king, which can move in 
		 * any direction, or that a fire piece is moving in the positive y direction 
		 * or that a water piece is moving in the negative y direction. */
		if(y_abs == 1) {
			return (p.isKing() || (p.isFire() && (y_gap == 1)) || (!p.isFire() && (y_gap == -1)));
		}

		/* Checks a jump move */
		if(y_abs == 2) {

			/* Gets the piece that is being captured */
			int x_capture = (xi + xf)/2;
			int y_capture = (yi + yf)/2;
			Piece captured = pieceAt(x_capture, y_capture);

			/* If there is no piece being captured or the piece is not on 
			 * the player's side, returns false */
			if((captured == null) || (captured.isFire() == p.isFire())) {
				return false;
			}

			return ((p.isKing()) || (p.isFire() && y_gap == 2) || (!p.isFire() && (y_gap == -2)));

		}
		return false;
	}

	public void select(int x, int y) {
		if (boundsCheck(x, y)) {
			if (pieceAt(x, y) != null) { 
				selected_x = x;
				selected_y = y;	
			}
			else {
				Piece p = pieceAt(selected_x, selected_y);
				p.move(x, y);
				moved = true;
				selected_x = x;
				selected_y = y;
			} 
		}
	}

	public void place(Piece p, int x, int y) {
		/* Checks that x and y are within the board */
		if (boundsCheck(x, y) && p != null) {
			/* Gets the current position of p */
			int[] coordinates = getXY(p);
			if (coordinates != null) {
				/* If there is a piece, removes the piece 
				 * and places it at x, y */
				remove(coordinates[0], coordinates[1]);
			}
			pieces[x][y] = p;
		}
	}


	public Piece remove(int x, int y) {
		if (boundsCheck(x, y) == true) {
			if (pieceAt(x, y) == null) {
				System.out.print("Pick a piece");
				return null;
			}
			else {
				Piece p = pieceAt(x, y);
				pieces[x][y] = null;
				return p;
			} 
		} 
		else {
			System.out.print("Pick a spot inside the board");
			return null;
		}
	}

	public boolean canEndTurn() {
		if(moved == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		fire_turn = !fire_turn;
		moved = false;
		if(pieceAt(selected_x, selected_y) != null) {
			Piece p = pieceAt(selected_x, selected_y);
			p.doneCapturing(); 
			selected_x = -1;
			selected_y = -1;
		}
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		/* Finds all the water and fire pieces on the boars */
		for(int i=0; i < size; i++) {
			for(int j=0; j < size; j++) {
				Piece examine = pieceAt(i, j);
				if(examine != null) {
					if(examine.isFire()) {
						fire += 1;
					}
					else {
						water += 1;
					}
				}
			}
		}
		
		if(fire == 0 && water > 0) {
			return "Water";
		}
		if(water == 0 && fire > 0) {
			return "Fire";
		}
		if(water == 0 && fire == 0) {
			return "No one";
		}
		return null;
	}
}