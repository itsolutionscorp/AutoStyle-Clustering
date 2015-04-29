public class Board {
	private static Piece[][] pieces = new Piece[8][8];
	private boolean isEmpty = true;
	private boolean turnOver = false;
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private boolean hasCaptured = false;
	private boolean gameOver = false;
	private boolean turnFire = true;
	private int xSelect;
	private int ySelect;

	public static void main(String args[]) {
		Board b = new Board(false);
		b.initPieces(pieces);
		b.drawBoard();
		b.drawAllPieces();
		while (!b.gameOver) {
			b.turnOver = false;
			while (!b.turnOver) {
	            if (StdDrawPlus.mousePressed()) {
	                int x = (int) StdDrawPlus.mouseX();
	                int y = (int) StdDrawPlus.mouseY();
	                if (b.canSelect(x, y)) {
	                	b.select(x, y);
	                	b.highlightPiece(b.xSelect, b.ySelect);
	                }
					b.drawBoard();
					b.drawAllPieces();
	            }
	            if (StdDrawPlus.isSpacePressed()) {
					b.highlightPiece(0, 1);
					b.drawBoard();
					b.drawAllPieces();
	            	if (b.canEndTurn()) {
	            		b.endTurn();
	            	}
	            }
	            StdDrawPlus.show(25);
            }
            if (b.winner() != null) {
            	System.out.println(b.winner());
            }
        }
	}

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			isEmpty = false;
		}		
	}

	// Initialize the starting piece layout
	private Piece[][] initPieces(Piece[][] pieces) {
		Piece piece;
		int N = 8;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == 0 && j % 2 == 0) {
            		piece = new Piece(true, this, 1, 1, "Pawn");
            		place(piece, j, i);
            	}
            	else if (i == 7 && j % 2 != 0) {
            		piece = new Piece(false, this, 1, 1, "Pawn");
            		place(piece, j, i);
            	}
            	else if (i == 1 && j % 2 != 0) {
            		piece = new Piece(true, this, 1, 1, "Shield");
            		place(piece, j, i);
            	}
            	else if (i == 6 && j % 2 == 0) {
            		piece = new Piece(false, this, 1, 1, "Shield");
            		place(piece, j, i);
            	}
            	else if (i == 2 && j % 2 == 0) {
            		piece = new Piece(true, this, 1, 1, "Bomb");
            		place(piece, j, i);
            	}
        		else if (i == 5 && j % 2 != 0) {
        			piece = new Piece(false, this, 1, 1, "Bomb");
            		place(piece, j, i);
            	}
            }
        }
        return pieces;
	}

	private void drawPiece(int i, int j) {
		if (pieceAt(i, j) == null) {
			return;
		}
		if (pieceAt(i, j).isFire()) {
			if (pieceAt(i, j).isBomb()) {
				if (pieceAt(i, j).isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				}
			}
			else if (pieceAt(i, j).isShield()) {
				if (pieceAt(i, j).isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
				}
			}
			else {
				if (pieceAt(i, j).isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				}
			}
		}
		else {
			if (pieceAt(i, j).isBomb()) {
				if (pieceAt(i, j).isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
				}
			}
			else if (pieceAt(i, j).isShield()) {
				if (pieceAt(i, j).isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
				}
			}
			else {
				if (pieceAt(i, j).isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
	}

	private void erasePiece(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    	StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
	}

	private void highlightPiece(int x, int y) {
		if (x == 0 && y == 1) {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
        	StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
		}
		else {
			if (pieceAt(x, y) != pieces[xSelect][ySelect]) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		    	StdDrawPlus.filledSquare((int) xSelect + .5, (int) ySelect + .5, .5);
		    	drawPiece(xSelect, ySelect);
			}
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		    StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
			drawPiece(x, y);
		}
	}

	private void explode(int x, int y) {
		if (pieceAt(x + 1, y + 1) != null && !pieceAt(x + 1, y + 1).isShield()) {
			erasePiece(x + 1, y + 1);
			remove(x + 1, y + 1);
		}
		if (pieceAt(x + 1, y) != null && !pieceAt(x + 1, y).isShield()) {
			erasePiece(x + 1, y);
			remove(x + 1, y);
		}
		if (pieceAt(x + 1, y - 1) != null && !pieceAt(x + 1, y - 1).isShield()) {
			erasePiece(x + 1, y - 1);
			remove(x + 1, y - 1);
		}
		if (pieceAt(x, y - 1) != null && !pieceAt(x, y - 1).isShield()) {
			erasePiece(x, y - 1);
			remove(x, y - 1);
		}
		if (pieceAt(x - 1, y - 1) != null && !pieceAt(x - 1, y - 1).isShield()) {
			erasePiece(x - 1, y - 1);
			remove(x - 1, y - 1);
		}
		if (pieceAt(x - 1, y) != null && !pieceAt(x - 1, y).isShield()) {
			erasePiece(x - 1, y);
			remove(x - 1, y);
		}
		if (pieceAt(x - 1, y + 1) != null && !pieceAt(x - 1, y + 1).isShield()) {
			erasePiece(x - 1, y + 1);
			remove(x - 1, y + 1);
		}
		if (pieceAt(x, y + 1) != null && !pieceAt(x, y + 1).isShield()) {
			erasePiece(x, y + 1);
			remove(x, y + 1);
		}
		erasePiece(x, y);
		remove(x, y);
	}

	private boolean canCapture(int xi, int yi) {
		if (turnFire) {
			if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 2, yi + 2) == null) {
				if (!pieceAt(xi + 1, yi + 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 2, yi + 2) == null) {
				if (!pieceAt(xi - 1, yi + 1).isFire()) {
					return true;
				}
			}
		}
		if (!turnFire) {
			if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 2, yi - 2) == null) {
				if (pieceAt(xi + 1, yi - 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 2, yi - 2) == null) {
				if (pieceAt(xi - 1, yi - 1).isFire()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canKingCapture(int xi, int yi) {
		if (turnFire) {
			if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 2, yi + 2) == null) {
				if (!pieceAt(xi + 1, yi + 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 2, yi + 2) == null) {
				if (!pieceAt(xi - 1, yi + 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 2, yi - 2) == null) {
				if (!pieceAt(xi + 1, yi - 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 2, yi - 2) == null) {
				if (!pieceAt(xi - 1, yi - 1).isFire()) {
					return true;
				}
			}
		}
		if (!turnFire) {
			if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 2, yi + 2) == null) {
				if (pieceAt(xi + 1, yi + 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 2, yi + 2) == null) {
				if (pieceAt(xi - 1, yi + 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 2, yi - 2) == null) {
				if (pieceAt(xi + 1, yi - 1).isFire()) {
					return true;
				}
			}
			else if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 2, yi - 2) == null) {
				if (pieceAt(xi - 1, yi - 1).isFire()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 0 || xi > 8 || xf < 0 || xf > 8 || yi < 0 || yi > 8 ||
		 yf < 0 || yf > 8 || Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2) {
		 	return false;
		}
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		if (!pieceAt(xi, yi).isKing()) {
			if (turnFire) {
				if (yf - yi == 1 && Math.abs(xf - xi) == 1) {
					return true;
				}
				if (yf - yi == 2) {
					if (xf - xi == 2) {
						if (canCapture(xi, yi)) {
							hasCaptured = true;
							erasePiece(xi + 1, yi + 1);
							remove(xi + 1, yi + 1);
							return true;
						}
					}
					if (xf - xi == -2) {
						if (canCapture(xi, yi)) {
							hasCaptured = true;
							erasePiece(xi - 1, yi + 1);
							remove(xi - 1, yi + 1);
							return true;
						}
					}
				}
			}
			if (!turnFire) {
				if (yf - yi == -1 && Math.abs(xf - xi) == 1) {
					return true;
				}
				if (yf - yi == -2) {
					if (xf - xi == 2) {
						if (canCapture(xi, yi)) {
							hasCaptured = true;
							erasePiece(xi + 1, yi - 1);
							remove(xi + 1, yi - 1);
							return true;
						}
					}
					if (xf - xi == -2) {
						if (canCapture(xi, yi)) {
							hasCaptured = true;
							erasePiece(xi - 1, yi - 1);
							remove(xi - 1, yi - 1);
							return true;
						}
					}
				}
			}
			return false;
		}
		if (pieceAt(xi, yi).isKing()) {
			if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1) {
				return true;
			}
			if (yf - yi == 2) {
				if (xf - xi == 2) {
					if (canKingCapture(xi, yi)) {
						hasCaptured = true;
						erasePiece(xi + 1, yi + 1);
						remove(xi + 1, yi + 1);
						return true;
					}
				}
				if (xf - xi == -2) {
					if (canKingCapture(xi, yi)) {
						hasCaptured = true;
						erasePiece(xi - 1, yi + 1);
						remove(xi - 1, yi + 1);
						return true;
					}
				}
			}
			if (yf - yi == -2) {
				if (xf - xi == 2) {
					if (canKingCapture(xi, yi)) {
						hasCaptured = true;
						erasePiece(xi + 1, yi - 1);
						remove(xi + 1, yi - 1);
						return true;
					}
				}
				if (xf - xi == -2) {
					if (canKingCapture(xi, yi)) {
						hasCaptured = true;
						erasePiece(xi - 1, yi - 1);
						remove(xi - 1, yi - 1);
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

	private void drawBoard() {
		// Draw an empty Board (done every time)
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
	}

	private void drawAllPieces() {
		for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
		        	if (pieceAt(i, j) != null) {
		        		drawPiece(i, j);
		            }
		        }
        	}
        	if (hasSelected) {
        		highlightPiece(xSelect, ySelect);
        	}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0 && pieces[x][y] != null) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if (pieceAt(x, y) != null) {
			return pieceAt(x, y).isFire() == turnFire;
		}
		if (canCapture(xSelect, ySelect) && pieceAt(xSelect, ySelect) != null || 
			!hasSelected || hasSelected && !hasMoved) {
			return true;
		}
		return false;
	}

	public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
        	xSelect = x;
        	ySelect = y;
    	}
    	else if (pieceAt(x, y) == null) {
    		if (hasSelected) {
    			if (validMove(xSelect, ySelect, x, y)) {
    				if (pieceAt(xSelect, ySelect).isBomb()) {
    					pieceAt(xSelect, ySelect).move(x, y);
    					if (hasCaptured) {
    						explode(x, y);
    					}
    				}
    				else { 
    					pieceAt(xSelect, ySelect).move(x, y);
    				}
    				xSelect = x;
    				ySelect = y;
    				hasMoved = true;
    			}
    		}
    		return;
    	}
		hasSelected = true;
	}

	public void place(Piece p, int x, int y) {
		if (x > 8 || x < 0 || y > 8 || y < 0 || p == null) {
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[j][i] == p) {
					remove(j, i);
				}
			}
		}
		if (pieceAt(x, y) != null) {
			remove(x, y);
			pieces[x][y] = p;
		}
		else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		Piece removedPiece;
		if (x < 8 && y < 8) {
			removedPiece = pieceAt(x, y);
			if (removedPiece == null) {
				return null;
			}
			pieces[x][y] = null;
			return removedPiece;
		}
		else {
			return null;
		}
	}

	public boolean canEndTurn() {
		if (hasSelected && hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		turnFire = !turnFire;
		hasSelected = false;
		hasMoved = false;
		hasCaptured = false;
		turnOver = true;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieceAt(i, j) != null) {
	            	if (pieceAt(i, j).isFire()) {
	            		fire += 1;
	            	}
	            	if (!pieceAt(i, j).isFire()) {
	            		water += 1;
	            	}
	            }
            }
        }
        if (fire == 0 && water > 0) {
        	gameOver = true;
        	return "Water";
        }
        else if (fire > 0 && water == 0) {
        	gameOver = true;
        	return "Fire";
        }
        else if (fire == 0 && water == 0) {
        	gameOver = true;
        	return "No one";
        }
        else {
        	return null;
        }
	}
}