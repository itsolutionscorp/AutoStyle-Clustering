public class Board {

	private Piece[][] pieces;
	private int turn;
	private int xSel, ySel;
	private Piece pieceSelected;
	private boolean moved;
	private boolean captured;


	public static void main(String[] args) {
		StdDrawPlus.setScale(0, 8);
        Board bNew = new Board(false); 
        while (bNew.winner() == null) {
        	if (StdDrawPlus.mousePressed()) {
				int xCurr = (int) StdDrawPlus.mouseX();
				int yCurr = (int) StdDrawPlus.mouseY();
				if (bNew.canSelect(xCurr, yCurr)) {
					bNew.select(xCurr, yCurr);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (bNew.canEndTurn()) {
					bNew.endTurn();
				}
			}		
			bNew.drawBoard();
			StdDrawPlus.show(20); 
        }
	}


	public Board(boolean shouldBeEmpty) {
		captured = false;
		moved = false;
		pieceSelected = null;
		turn = 1;
		xSel = -1;
		ySel = -1;
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false) {
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if ((i+j)%2 == 0) {
            			if (j == 0) {
            				pieces[i][j] = new Piece(true, this, i, j, "pawn");
            			} if (j == 1) {
            				pieces[i][j] = new Piece(true, this, i, j, "shield");
                		} if (j == 2){
            				pieces[i][j] = new Piece(true, this, i, j, "bomb");
            			} if (j == 5) {
            				pieces[i][j] = new Piece(false, this, i, j, "bomb");            				
                		} if (j == 6) {
            				pieces[i][j] = new Piece(false, this, i, j, "shield");
            			} if (j == 7) {
            				pieces[i][j] = new Piece(false, this, i, j, "pawn");
                		}
                	}
				}
			}
		} 
	}


	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
            	if (i == xSel && j == ySel) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY); 
            	else StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
            	
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);

				if (pieces[i][j] != null) {
					if (pieces[i][j].side() == 0) { 
						if (pieces[i][j].isKing() == true) {
							if (pieces[i][j].isBomb() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							} else if (pieces[i][j].isShield() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						} else {
							if (pieces[i][j].isBomb() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							} else if (pieces[i][j].isShield() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					} else if (pieces[i][j].side() == 1) {
						if (pieces[i][j].isKing() == true) {
							if (pieces[i][j].isBomb() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							} else if (pieces[i][j].isShield() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						} else {
							if (pieces[i][j].isBomb() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							} else if (pieces[i][j].isShield() == true) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
            	}	
        	}
		}
	}
	

	public Piece pieceAt(int x, int y) {
		// gets the piece at (x,y) on the board, or null if no piece is there 
		// or x, y, is out of bounds
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0) || (pieces[x][y] == null)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	private boolean validMove (int xi, int yi, int xf, int yf, Piece p) {
		// // return true if...
		if ((p.isKing() == true) || ((p.side() == 0) && (yi < yf)) ||  ((p.side() == 1) && (yi > yf))) {
			if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1) {
				return true;
			} 
		}
		return false;
	}

	private boolean validCapture (int xi, int yi, int xf, int yf, Piece p) {
		// // return true if...
		if ((p.isKing() == true) || ((p.side() == 0) && (yi < yf)) ||  ((p.side() == 1) && (yi > yf))) {
			if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2) {
				if (pieceAt((xi+xf)/2, (yf+yi)/2) != null && pieceAt((xi+xf)/2, (yf+yi)/2).side() != p.side()) {
					return true;
				}
			} 
		}
		return false;
	}


	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) { // square with a piece
			if ((pieceSelected == null) || ((pieceSelected != null) && (moved == false))) {
				if ( ((turn == 1) && (pieces[x][y].isFire() == true)) || ((turn == -1) && (pieces[x][y].isFire() == false))) {
					return true;
				}
			}
		} else if (pieceSelected != null){ // square without a piece	
			if ((moved == false) && (validMove(xSel, ySel, x, y, pieceSelected) || validCapture(xSel, ySel, x, y, pieceSelected))) {
				return true;
			} else if ((pieceSelected.hasCaptured() == true) && validCapture(xSel, ySel, x, y, pieceSelected)) {
				return true;

			}
		} 
		return false;
	}

	public void select(int x, int y) {
		// selects square at (x, y)
		if (pieceAt(x, y) != null) {
			pieceSelected = pieceAt(x, y);
		}
		else {
			pieceSelected.move(x,y);
			moved = true;
			if (pieceSelected.hasCaptured()) {
				captured = true;
			}
		}
		xSel = x;
		ySel = y;
	}

	public void place(Piece p, int x, int y) {
		// places p at (x,y)
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0) && p != null) {
			pieces[x][y] = p;
		}
	}
	

	public Piece remove(int x, int y) {
		if ((x > 8) || (y > 8) || (x < 0) || (y < 0)) {
			System.out.println("This piece is out of bounds.");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("There is no piece at this position.");
			return null;
		} else {
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		turn = (turn * -1);
		moved = false;
		captured = false;
		pieceSelected.doneCapturing();
		pieceSelected = null;
		xSel = -1;
		ySel = -1;
	}

	public String winner() {
		int totalFire = 0;
		int totalWater = 0;
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		if(pieceAt(i, j) != null) {
        			if (pieceAt(i, j).side() == 0) { 
	        			totalFire = totalFire + 1;
	        		} else if (pieceAt(i, j).side() == 1) { 
	        			totalWater = totalWater + 1;
	        		}
        		}
        	}
        }
		if (totalWater == 0 && totalFire == 0) {
			return "No one";
		} else if (totalWater == 0) {
			return "Fire";
		} else if (totalFire == 0) {
			return "Water";
		} else {
			return null;
		}
	}

}