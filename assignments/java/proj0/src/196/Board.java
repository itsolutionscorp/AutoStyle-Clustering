public class Board {
	private Piece[][] pieces;
	private Board b;
	private int player = 1;
	private boolean hasMoved = false;
	private boolean hasCaptured = false;
	private Piece removed_piece, selectedPiece, prep_piece;
	private int selectedX;
	private int selectedY;
	private boolean select_not_move = false;


	/* Starts GUI of game */
	public static void main (String args[]) {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		while(b.winner() == null) {
			b.drawBoard(8);
			if (StdDrawPlus.mousePressed()) {
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) { 
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}

	/* Colors board and attaches piece images to piece locations */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (prep_piece != null) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(selectedX + 0.5, selectedY + 0.5, .5);
                	if (pieces[selectedX][selectedY] != null) {
                	if (pieces[selectedX][selectedY].isFire() == true && pieces[selectedX][selectedY].isKing() == false 
            			&& pieces[selectedX][selectedY].isBomb() == false && pieces[selectedX][selectedY].isShield() == false) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/pawn-fire.png", 1, 1);
            		}                
            		if (pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isShield()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/shield-fire.png", 1, 1);
            		}
            		if (pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isBomb()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/bomb-fire.png", 1, 1);
            		}
            		if (!pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isBomb()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/bomb-water.png", 1, 1);
            		}if (!pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isShield()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/shield-water.png", 1, 1);
            		}
            		if (pieces[selectedX][selectedY].isFire() == false && pieces[selectedX][selectedY].isKing() == false 
            			&& pieces[selectedX][selectedY].isBomb() == false && pieces[selectedX][selectedY].isShield() == false) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/pawn-water.png", 1, 1);
            		}
            		/* Cases for crowned pieces */
            		if (pieces[selectedX][selectedY].isFire() == true && pieces[selectedX][selectedY].isKing() && 
            			pieces[selectedX][selectedY].isBomb() == false && pieces[selectedX][selectedY].isShield() == false) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/pawn-fire-crowned.png", 1, 1);
            		}
            		if (pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isKing() && 
            			pieces[selectedX][selectedY].isShield()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/shield-fire-crowned.png", 1, 1);
            		}
            		if (pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isKing() && 
            			pieces[selectedX][selectedY].isBomb()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/bomb-fire-crowned.png", 1, 1);
            		}
            		if (!pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isKing() && 
            			pieces[selectedX][selectedY].isBomb()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/bomb-water-crowned.png", 1, 1);
            		}
            		if (!pieces[selectedX][selectedY].isFire() && pieces[selectedX][selectedY].isKing() && 
            			pieces[selectedX][selectedY].isShield()) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/shield-water-crowned.png", 1, 1);
            		}
            		if (pieces[selectedX][selectedY].isFire() == false && pieces[selectedX][selectedY].isKing() && 
            			pieces[selectedX][selectedY].isBomb() == false && pieces[selectedX][selectedY].isShield() == false) {
            			StdDrawPlus.picture(selectedX + 0.5, selectedY + 0.5, "img/pawn-water-crowned.png", 1, 1);
            		}
            	}
            }
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire() == true && pieces[i][j].isKing() == false 
            			&& pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
            		}
            		if (pieces[i][j].isFire() && pieces[i][j].isShield()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
            		}
            		if (pieces[i][j].isFire() && pieces[i][j].isBomb()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
            		}
            		if (!pieces[i][j].isFire() && pieces[i][j].isBomb()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
            		}
            		if (!pieces[i][j].isFire() && pieces[i][j].isShield()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
            		}
            		if (pieces[i][j].isFire() == false && pieces[i][j].isKing() == false 
            			&& pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
            		}
            		/* Cases for crowned pieces */
            		if (pieces[i][j].isFire() == true && pieces[i][j].isKing() && 
            			pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
            		}
            		if (pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isShield()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
            		}
            		if (pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isBomb()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
            		}
            		if (!pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isBomb()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
            		}
            		if (!pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isShield()) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
            		}
            		if (pieces[i][j].isFire() == false && pieces[i][j].isKing() && 
            			pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false) {
            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
            		}
            	}
    		}
        }
    }

    /* Constructor for Board. If shouldBeEmpty is true, initializes empty Board.
    Otherwise initializes a board w/ default configuration */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false) {	
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
					if (j == 0 && i % 2 == 0) {
						pieces[i][j] = new Piece(true, b, i, j, "pawn");
					}
					if (j == 1 && i % 2 != 0) {
						pieces[i][j] = new Piece(true, b, i, j, "shield");
					}
					if (j == 2 && i % 2 == 0) {
						pieces[i][j] = new Piece(true, b, i, j, "bomb");
					}
					if (j == 5 && i % 2 != 0) {
						pieces[i][j] = new Piece(false, b, i, j, "bomb");
					}
					if (j == 6 && i % 2 == 0) {
						pieces[i][j] = new Piece(false, b, i, j, "shield");
					}
					if (j == 7 && i % 2 != 0) {
						pieces[i][j] = new Piece(false, b, i, j, "pawn");
					}
				}
			}
		}
	}

	/* Places p at (x, y). If (x, y) is out of bounds 
	or if p is null, does nothing. If another piece 
	already exists at (x, y), p will replace that piece. */
	public void place(Piece p, int x, int y) {
		if ((x >= 0 || y >= 0 || x <= 7 || y <= 7) || (p != null))  {
			pieces[x][y] = p;
		}
	}

	/* Gets piece at position (x, y) on board, or null if there 
    is no piece. If (x, y) out of bounds returns null. */
    public Piece pieceAt(int x, int y) {
    	if (x < 0 || y < 0 || x > 7 || y > 7) {
    		return null;
    	}
    	else { 
    		return pieces[x][y]; 
    	}
    }

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (hasMoved == false) {
			if ((p != null) && (player == p.side())) {
				return ((selectedPiece == null) || (select_not_move));
			}
			else if ((p != null) && (player != p.side())) {
				return false;
			}
			else if (p == null && !hasCaptured) {
				return ((select_not_move && validMove(selectedX, selectedY, x, y)));
			}
		}
		if (p == null && hasCaptured && canCapture(selectedX, selectedY, x, y)) {
			return (select_not_move && validMove(selectedX, selectedY, x, y));
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (player == 0) {
			boolean firstdiagonal = (yi + 1 == yf || yi - 1 == yf) && (xi + 1 == xf || xi - 1 == xf);
			boolean seconddiagonal = (yi + 2 == yf || yi - 2 == yf) && (xi + 2 == yf || xi - 2 == yf);
			return firstdiagonal || seconddiagonal || canCapture(xi, yi, xf, yf);
		}
		else {
			boolean firstdiagonal = (yi + 1 == yf || yi - 1 == yf) && (xi + 1 == xf || xi - 1 == xf);
			boolean seconddiagonal = (yi + 2 == yf || yi - 2 == yf) && (xi + 2 == yf || xi - 2 == yf);
			return firstdiagonal || seconddiagonal || canCapture(xi, yi, xf, yf);
		}
	}

	private boolean canCapture(int xi, int yi, int xf, int yf) {
		int xm = (xi + xf) >>> 1;
		int ym = (yi + yf) >>> 1;
		if ((pieces[xm][ym] != null) && (pieces[xm][ym].isFire() != pieces[xi][yi].isFire())) {
			return true;
		}
		return false;
	}

	private void capture(int xi, int yi, int xf, int yf) {
		int xm = (xi + xf) >>> 1;
		int ym = (yi + yf) >>> 1;
		/* Check to see if piece at (xi, yi) is a bomb first */
		if (pieces[xi][yi].isBomb() && (!pieces[xm][ym].isShield())) {
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	if (pieces[i][j] != null) {
		            	if ((Math.abs(xf - i) == 1 && Math.abs(yf - j) == 1) && (!pieces[i][j].isShield())
		            	 	&& (pieces[i][j].isFire() != pieces[xi][yi].isFire())) {
		            		remove(i, j);
		            	}
		            }
	            }
	        }
		}
		remove(xm, ym);	
	}

	public void select(int x, int y) {
		selectedPiece = pieceAt(x, y);
		if ((selectedPiece != null) && (hasCaptured == false)) {
			prep_piece = selectedPiece;
			selectedX = x;
			selectedY = y;
			select_not_move = true;
		}
		else if ((selectedPiece == null) && (prep_piece != null) 
				&& (right_direction(prep_piece, x, y))) {
			if (canCapture(selectedX, selectedY, x, y)) {
				capture(selectedX, selectedY, x, y);
				hasCaptured = true;
				remove(selectedX, selectedY); 
				selectedX = x;
				selectedY = y;
				place(prep_piece, x, y);
				prep_piece.move(x, y);
				if (prep_piece.isBomb()) { 
					remove(x, y);
				}
				hasMoved = true;
			}
			else if (Math.abs(selectedY - y) == 1) {
				remove(selectedX, selectedY); 
				place(prep_piece, x, y);
				prep_piece.move(x, y);
				prep_piece = null;
				hasMoved = true;
			}
			else {
				prep_piece = null;
				hasMoved = true;
			}
		} 
	}

	private boolean right_direction(Piece p, int x, int y) {	
		if (p.isFire() && (!p.isKing())) {
			if ((selectedY - y) < 0) {
				return true;
			}
			return false;
		}
		else if ((!p.isFire()) && (!p.isKing())) {
			if ((selectedY - y) > 0) {
				return true;
			}
			return false;
		}
		return true;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.out.println("The coordinates (x,y) are out of bounds");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("There is no piece at (x,y)");
			return null;
		}
		else {
			removed_piece = pieces[x][y];
			pieces[x][y] = null;
			return removed_piece;
		}
	}

	public boolean canEndTurn() {
		if (hasMoved == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (player == 0) {
			player = 1;
			prep_piece = null;
			hasMoved = false;
			hasCaptured = false;
			select_not_move = false;
		}
		else if (player == 1) {
			player = 0;
			prep_piece = null;
			hasMoved = false;
			hasCaptured = false;
			select_not_move = false;
		}
	}

	public String winner() {
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						countFire += 1;
					}
					else if (pieces[i][j].isFire() == false) {
						countWater += 1;
					}
				}
			}
		}
		if ((countFire != 0) && (countWater == 0)) {
			return "Fire";
		}
		else if ((countWater != 0) && (countFire == 0)) {
			return "Water";
		}
		else if ((countWater != 0) && (countFire != 0)) {
			return null;
		}
		else if (countWater == countFire) {
			return "No one";
		}
		else {
			return "No one";
		}
	}
}