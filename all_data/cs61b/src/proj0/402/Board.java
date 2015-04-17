public class Board {
	
	/* array of type Piece with null if there is no piece present at
	 * that location.
	 */
	private Piece[][] pieces;
	private boolean turn; //if turn == true, it's fire's turn
	private Piece selected; //piece has been selected for movement, null at beginning of a turn
	private boolean moved; //is true if a piece has moved during the player's turn
	
	public static void main(String[] args) {					
		Board b = new Board(false);
		int N = 8;
		
		/* GUI implementation */
		while (b.winner() == null || b.winner() == "No One") {
			StdDrawPlus.setXscale(0, N);
			StdDrawPlus.setYscale(0, N);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else	              StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (b.pieces[i][j] != null) {
						if (b.pieces[i][j].isFire() == true ){
							if (b.pieces[i][j].isShield() == true) {
								if (b.pieces[i][j].isKing() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
								}
								else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
								}
							}
							else if (b.pieces[i][j].isBomb() == true) {
								if (b.pieces[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
								}
								else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
								}
							}
							else { //for fire pawns
								if (b.pieces[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
								}
							}
						}
						else {
							if (b.pieces[i][j].isShield() == true) {
								if (b.pieces[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
								}
							}
							else if (b.pieces[i][j].isBomb() == true) {
								if (b.pieces[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
								}
							}
							else {
								if (b.pieces[i][j].isKing() == true) {
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
								}
								else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
								}
							}
						}
					}
				}
			}
			StdDrawPlus.show(1);
			if (StdDrawPlus.mousePressed()){
				int dx = (int) StdDrawPlus.mouseX();
				int dy = (int) StdDrawPlus.mouseY();
				if (b.canSelect(dx, dy)){
					b.select(dx, dy);
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
		}
	}
	
	/* Constructor:
	 * Determines whether or not to initialize the Board to
	 * be empty or set up in the default configuration.
	 */
	public Board(boolean shouldBeEmpty) {	
		turn = true;
		if (shouldBeEmpty == false){
			pieces = new Piece[8][8];
			for (int k = 0; k < 3; k = k + 1) {
				if (k == 0) {
					for (int m = 0; m < 7; m = m + 2) {
						pieces[m][k] = new Piece(true, this, m, k, "pawn");
					}
				}
				if (k == 1) {
					for (int m = 1; m < 8; m = m + 2) {
						pieces[m][k] = new Piece(true, this, m, k, "shield");
					}
				}
				if (k == 2) {
					for (int m = 0; m < 7; m = m + 2) {
						pieces[m][k] = new Piece(true, this, m, k, "bomb");
					}
				}
			}
			for (int k = 5; k < 8; k = k + 1) {
				if (k == 5) {
					for (int m = 1; m < 8; m = m + 2) {
						pieces[m][k] = new Piece(false, this, m, k, "bomb");
					}
				}
				if (k == 6) {
					for (int m = 0; m < 7; m = m + 2) {
						pieces[m][k] = new Piece(false, this, m, k, "shield");
					}
				}
				if (k == 7) {
					for (int m = 1; m < 8; m = m + 2) {
						pieces[m][k] = new Piece(false, this, m, k, "pawn");
					}
				}
			}
		}
		else {
			pieces = new Piece[8][8];	
		}
	}

	/* Gets the current position of the piece, or null if
	 * there is no piece/piece is out of bounds
	 */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y < 0 || y > 7){
			return null;
		}
		return pieces[x][y];
	}

	/* determines if a piece/square can be selected
	 */
	public boolean canSelect(int x, int y) {
		boolean toReturn = false;
		if (pieces[x][y] == null) { //empty square being selected
			if (selected != null) { //has selected a piece to move
				if (selected.isFire() == true && turn == true) { //for fire pieces 
					if (selected.isKing() == false) { //not crowned
						if ((selected == pieceAt(x - 2, y - 2) && pieceAt(x - 1, y - 1) != null  && pieceAt(x - 1, y - 1).isFire() == false)
							|| (selected == pieceAt(x + 2, y - 2) && pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire() == false)){ //capturing
							toReturn = true;
						}
						else if (selected == pieceAt(x - 1, y - 1) || selected == pieceAt(x + 1, y - 1) ) { //the piece is just moving
							if (!moved) {
								toReturn = true;
							}
						}
						else {
							toReturn = false;
						}
					}
					else if (selected.isKing() == true) { //crowned
						if ((selected == pieceAt(x - 2, y - 2) && pieceAt(x - 1, y - 1) != null && pieceAt(x - 1, y - 1).isFire() == false)
								  || (pieces[x][y] == pieceAt(x + 2, y - 2) && pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire() == false)
							      || (selected == pieceAt(x - 2, y + 2) && pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire() == false) 
								  || (selected == pieceAt(x + 2, y + 2) && pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire() == false)) { //capturing
							toReturn = true;
						}
						else if (!moved) {	
							if (selected == pieceAt(x - 1, y - 1) || selected == pieceAt(x + 1, y - 1) || 
								selected == pieceAt(x - 1, y + 1) || selected == pieceAt(x + 1, y + 1)) { //the piece is just moving
								toReturn = true;
							}
						}
						else {
							toReturn = false;
						}
					}
				}
				else if (!selected.isFire() && !turn) { //water's turn on empty square and has selected a piece
					if (selected.isKing() == false) {	
						if ((selected == pieceAt(x - 2, y + 2) && pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire() == true)
								  || (selected == pieceAt(x + 2, y + 2) && pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire() == true)){ //capturing
							toReturn = true;
						}
						else if (!moved) {
							if (selected == pieceAt(x - 1, y + 1) || selected == pieceAt(x + 1, y + 1) ) { //the piece is just moving
								toReturn = true;
							}
						}
						else {
							toReturn = false;
						}
					}
					else if (selected.isKing() == true) {
						if ((selected == pieceAt(x - 2, y - 2) && pieceAt(x - 1, y - 1) != null && pieceAt(x - 1, y - 1).isFire() == true)
								  || (pieces[x][y] == pieceAt(x + 2, y - 2) && pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire() == true)
								  || (selected == pieceAt(x - 2, y + 2) && pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire() == true) 
								  || (selected == pieceAt(x + 2, y + 2) && pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire() == true)) { //capturing
							toReturn = true;
						}
						else if (!moved) {
							if (selected == pieceAt(x - 1, y - 1) || selected == pieceAt(x + 1, y - 1) || 
								selected == pieceAt(x - 1, y + 1) || selected == pieceAt(x + 1, y + 1)) { //the piece is just moving
								toReturn = true;
							}
						}
						else {
							toReturn = false;
						}
					}
				}				
			}
		}
		else { //pieces[x][y] != null ie piece being selected
			if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == turn) {
				if (selected != null && moved == false) { //player selected but hasn't moved yet
					toReturn = true;
				}
				else if (selected == null && moved == false) { //player hasn't selected a piece yet
					toReturn = true;
				}
				else {
					toReturn = false;
				}
			}
		}
		return toReturn;
	}
	
	/* select the piece at x,y
	 */
	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selected = pieces[x][y];
		}
		else { //if (pieces[x][y] == null) ie empty square is being selected
			if (selected != null) {
				pieces[x][y] = selected;
				selected.move(x, y);
				moved = true;
				if (selected.isBomb() && selected.hasCaptured()) {
				}
				else {
					selected = pieceAt(x, y);
				}
			}
		}
	}
	
	/* places piece p at x,y
	 */
	public void place(Piece p, int x, int y) {
		if (x < 8 && x >= 0 && y >= 0 && y < 8 && p != null) {	
			pieces[x][y] = p;
		}
	}
	 
	/* removes piece from the board 
	 */
	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y < 0 || y > 7) {
			System.out.println("Please select a square within the dimension of this 8x8 Board");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece to remove here. Please select a piece to remove.");
			return null;
		}
		else {
			Piece removed = pieces[x][y]; 
			pieces[x][y] = null;
			return removed;
		}
	}
	
	/* determines if a player can end their turn
	 */
	public boolean canEndTurn() {
		if (moved == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* handles switching players when turn is ended and 
	 * other things that should happen at the end of a turn
	 */
	public void endTurn() {
		turn = !turn;
		moved = false;
		selected.doneCapturing();
		selected = null;
	}
	
	/* returns winner of the game - Fire, Water, No one, or null */
	public String winner() {
		String toReturn = null;
		int firecount = 0;
		int watercount = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire() == true) {
						firecount = firecount + 1;
					}
					else {
						watercount = watercount + 1;
					}
				}
			}
		}
		if (firecount == 0 && watercount == 0) {
			toReturn = "No One";
		}
		else if (watercount == 0) {
			toReturn = "Fire";
		}
		else if (firecount == 0){
			toReturn = "Water";
		}
		else {
			toReturn = null;
		}
		return toReturn;
	}
}