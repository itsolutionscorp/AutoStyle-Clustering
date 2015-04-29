public class Board {

	private boolean emptyBoard;
	private Piece[][] newThing;
	private boolean turn;
	private boolean pieceIsSelected; //NEW
	private Piece selectedPiece; //NEW

	public Board(boolean shouldBeEmpty) {
		emptyBoard = shouldBeEmpty;
		newThing = new Piece[8][8];
		turn = true;
		pieceIsSelected = false;

		if (emptyBoard == false) {
			//initializes default config board
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0) {
						if (j == 0) {
							newThing[i][j] = new Piece(true, this, i, j, "pawn");
						}
						else if (j == 1) {
							newThing[i][j] = new Piece(true, this, i, j, "shield");
						}
						else if (j == 2) {
							newThing[i][j] = new Piece(true, this, i, j, "bomb");
						}
						else if (j == 5) {
							newThing[i][j] = new Piece(false, this, i, j, "bomb");
						}
						else if (j == 6) {
							newThing[i][j] = new Piece(false, this, i, j, "shield");
						}
						else if (j == 7) {
							newThing[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				//white pen? set pen and fill square again if pieceAt matches piece selected
				if (newThing[i][j] != null) {
						if (newThing[i][j].isFire()) {
							if (newThing[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
							else if (newThing[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
						else {
							if (newThing[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
							else if (newThing[i][j].isShield()) {
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

	public Piece pieceAt(int x, int y) {
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)) {
			if (newThing[x][y] != null) {
				return newThing[x][y];
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		// int currPX = selectedPiece.xPos;
		// int currPY = selectedPiece.yPos;

	// 	if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7) && ((x + y) % 2 == 0)) {

	// 		if /*it's the player's turn */ {
	// 			if ((newThing[x][y] == null) && (pieceIsSelected == true) {
	// 				if (selectedPiece.crowned == false) {
	// 					if (((currPX + 1 == x) && (currPY + 1 == y)) || ((currPX - 1 == x) && (currPY + 1 == y))) {
	// 						return true;
	// 					}
	// 					else return false;
	// 				}
	// 				else if (selectedPiece.crowned == true) {
	// 					if (((currPX + 1 == x) && (currPY + 1 == y)) || ((currPX - 1 == x) && (currPY + 1 == y)) || 
	// 						((currPX + 1 == x) && (currPY - 1 == y)) || ((currPX - 1 == x) && (currPY - 1 == y))) {
	// 						return true;
	// 					}
	// 					else return false;
	// 				}
	// 			}
	// 			else if /* implement stuff for multi-capture */ {

	// 			}
	// 			else if ((newThing[x][y] != null) && pieceIsSelected == false) {
	// 				return true;
	// 			}
	// 			else if (())


	// 			if pieceIsSelected = false {

	// 			}
	// 			else if ((selectedPiece.xPos == x) && (selectedPiece.yPos == y)) {

	// 			}

	// 				/* square has a piece on it */

	// 				/* a piece is not selected OR piece is selected but not moved yet */ {

	// 			}
	// 		}

	// 	else {
	// 		return false;
	// 	}
	// }
	return false;
	}

	public void select(int x, int y) {
		
	}

	public void place(Piece p, int x, int y) {
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7) && (p != null)) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (newThing[i][j] == p) {
						newThing[i][j] = null;
					}
				}
			}
			newThing[x][y] = p;		
		}
	}

	public Piece remove(int x, int y) {
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)) {
			if (newThing[x][y] == null) {
				System.out.println("There is no piece here to be removed.");
			}
			else {
				Piece removedPiece = newThing[x][y];
				newThing[x][y] = null;
				return removedPiece;
			}
		}
		else {
			System.out.println("The input (x, y) is out of bounds!");
		}
	}

	public boolean canEndTurn() {
		return false;
	}

	public void endTurn() {
		
	}

	public String winner() {
		return null;
	}
	
	public static void main(String[] args) {
		//starts GUI supported version of game 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board newInit = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            newInit.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
            }            
            StdDrawPlus.show(100);
        }
	
	}

}


