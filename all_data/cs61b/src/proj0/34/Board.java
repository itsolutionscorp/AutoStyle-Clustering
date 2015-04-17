public class Board {
    private static boolean[][] pieces;
    private Piece[][] piecearray;
    private int player;
    private int firepieces;
	private int waterpieces;
	private Piece selected;
	private int xcurr;
	private int ycurr;
	private boolean hasmoved;
	private boolean hasselected;
	private boolean isitempty;
	private boolean initial;

    private void drawBoard(int N) {
    	numPieces(N);
    	while (winner() == null) {
    		if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (pieceAt((int) x, (int) y) != null) {
                selected = pieceAt((int) x, (int) y);
            	}
            	if (pieceAt((int) x, (int) y) == null) {
            		selected = null;
            	}
                pieces[(int) x][(int) y] = true;
                Piece p = pieceAt((int) x, (int) y);
                if (canSelect((int) x, (int) y)) {
                	select((int) x, (int) y);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			StdDrawPlus.filledSquare(((int) x) + .5, ((int) y) + .5, .5);
                }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((!isitempty) && (pieceAt(i, j) != null)) {
                	StdDrawPlus.picture(i + .5, j + .5, getImage(piecearray[i][j]), 1, 1);
            	}
            }
        }
        }
            if (StdDrawPlus.isSpacePressed()) {
    		if (canEndTurn()) {
    	    	endTurn();
    	    	} 
            }
   	}
}

	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];
        Board board = new Board(false);
        board.drawInitial(8); 

        while(true) {
            board.drawBoard(N);           
        	StdDrawPlus.show(100);
        }
    }
    private void drawInitial(int N) {
    	if (initial == false) {
    		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY); }
                else        {StdDrawPlus.setPenColor(StdDrawPlus.RED); }
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	if (pieceAt(i, j) != null) {
                StdDrawPlus.picture(i + .5, j + .5, getImage(piecearray[i][j]), 1, 1);
            	}
            }
        }
        initial = true;
        }
    }

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {
			isitempty = true;
			piecearray = null;
		}
		else {
		isitempty = false;
		int player = 0; //fire
        Piece selected = null;
        int firepieces = 0;
        int waterpieces = 0;
        boolean hasselected = false;
        boolean hasmoved = false;
        boolean isitempty = false;
        boolean initial = false;
			piecearray = new Piece[8][8];
        	    piecearray[0][0] = new Piece(true, this, 0, 0, "pawn");
                piecearray[2][0] = new Piece(true, this, 2, 0, "pawn");
                piecearray[4][0] = new Piece(true, this, 4, 0, "pawn");
                piecearray[6][0] = new Piece(true, this, 6, 0, "pawn");
                piecearray[1][1] = new Piece(true, this, 1, 1, "shield");
                piecearray[3][1] = new Piece(true, this, 3, 1, "shield");
                piecearray[5][1] = new Piece(true, this, 5, 1, "shield");
                piecearray[7][1] = new Piece(true, this, 7, 1, "shield");
                piecearray[0][2] = new Piece(true, this, 0, 2, "bomb");
                piecearray[2][2] = new Piece(true, this, 2, 2, "bomb");
                piecearray[4][2] = new Piece(true, this, 4, 2, "bomb");
                piecearray[6][2] = new Piece(true, this, 6, 2, "bomb");
                piecearray[1][5] = new Piece(false, this, 1, 5, "bomb");
                piecearray[3][5] = new Piece(false, this, 3, 5, "bomb");
                piecearray[5][5] = new Piece(false, this, 5, 5, "bomb");
                piecearray[7][5] = new Piece(false, this, 7, 5, "bomb");
                piecearray[0][6] = new Piece(false, this, 0, 6, "shield");
                piecearray[2][6] = new Piece(false, this, 2, 6, "shield");
                piecearray[4][6] = new Piece(false, this, 4, 6, "shield");
                piecearray[6][6] = new Piece(false, this, 6, 6, "shield");
                piecearray[1][7] = new Piece(false, this, 0, 7, "pawn");
                piecearray[3][7] = new Piece(false, this, 2, 7, "pawn");
                piecearray[5][7] = new Piece(false, this, 4, 7, "pawn");
                piecearray[7][7] = new Piece(false, this, 6, 7, "pawn");
	}
	}

	public Piece pieceAt(int x, int y) {
		if ((x < 0 || y < 0) || (x > 7 || y > 7)) {
		return null;
	}
		if (piecearray[x][y] != null) {
			return piecearray[x][y];
		}
		return null;
	} 
	
	private int xcoord(Piece p) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]) {
                	if (pieceAt(i, j) == p) {
                		xcurr = i;
                	}
            	}
            }
            return xcurr;
        }
        return xcurr;
	}
	private int ycoord(Piece p) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]) {
                	if (pieceAt(i, j) == p) {
                		ycurr = j;
                	}
            	}
            }
            return ycurr;
        }
        return ycurr;
	}
	public boolean canSelect(int x, int y) {
		Piece p = this.pieceAt(x, y);
		if (p == null) {
			if (selected == null) {
				return false;
			}
			if ((((player == 0) && (p.isFire())) || ((player == 1) && (!p.isFire()))) && (selected != null)) {
			xcurr = xcoord(selected);
			ycurr = ycoord(selected);
			if ((hasmoved == false) && (this.validMove(selected, xcurr, ycurr, x, y) == true)) {
				return true;
			}
        	if (selected.hasCaptured() && (this.validMove(selected, xcurr, ycurr, x, y) == true)) {
        		return true;
        	}
        }
    }
		if (((player == 0) && (p.isFire())) || ((player == 1) && (!p.isFire()))) {
			if (!hasselected || (!hasmoved && hasselected)) {
				return true;
			}
		}
		return false;
    }

	private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
		Piece q = this.pieceAt(xf, yf);
		if (q == null) {
			if (((xi >= 0) && (xf >= 0) && (xi <= 7) && (xf <= 7) && (yi >= 0) && (yf >= 0) && (yi <= 7) && (yf <= 7)) && ((xf + yf) % 2 == 0) && (((xi + 1) == xf) || ((xi - 1) == xf)) && (((yi + 1) == yf) || ((yi - 1) == yf))) {
			return true;
		}
		}
		return false;
	}
	public void select(int x, int y) {
		hasselected = true;
		if (this.pieceAt(x, y) != null) {
			selected = this.pieceAt(x, y);
		}
		if (this.pieceAt(x,y) == null) {
			selected.move(x, y);
			hasmoved = true;
			}

	}
	public void place(Piece p, int x, int y) {
		if ((p == null) || (x < 0) || (y < 0) || (x > 7) || (y > 7)) {
		}
		else {
				piecearray[x][y] = p;
		}
	}
	public Piece remove(int x, int y) {
		if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {
			System.out.println("Input is out of bounds");
			return null;
		}
		if (this.pieceAt(x, y) == null) {
			return null;
		}
		Piece pieceRemoved = this.pieceAt(x, y);
		piecearray[x][y] = null;
		return pieceRemoved;
	}
	
	public boolean canEndTurn() {
		if ((hasmoved) || (selected.hasCaptured())) {
			return true;
		}
		return false;
	}
	public void endTurn() {
		if (player == 0) {
			player = 1;
		}
		if (player == 1) {
			player = 0;
		}
		hasselected = false;
		hasmoved = false;
	}
	public String winner() {
		if ((firepieces == 0) && (waterpieces == 0)) {
			return "No one";
		}
		if ((firepieces == 1) && (waterpieces == 0)) {
			return "Fire";
		}
		if ((firepieces == 0) && (waterpieces == 1)) {
			return "Water";
		}
		return null;
	}
	private void numPieces(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) != null) {
                	if (pieceAt(i, j).isFire()) {
                		firepieces = firepieces + 1;
                	}
                	if (!pieceAt(i, j).isFire()) {
                		waterpieces = waterpieces + 1;
                	}
            	}
            }
        }

	}
	private boolean hasmoved() {
		return hasmoved;
	}
	private boolean hasselected() {
		return hasselected;
	}
		private String getImage(Piece p) {
		if (p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-fire-crowned.png";
				}
				if (p.isShield()) {
					return "img/shield-fire-crowned.png";
				}
				return "img/pawn-fire-crowned.png";
			}
			if (!p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-fire.png";
				}
				if (p.isShield()) {
					return "img/shield-fire.png";
				}
				return "img/pawn-fire.png";
			}
		}
		if (!p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-water-crowned.png";
				}
				if (p.isShield()) {
					return "img/shield-water-crowned.png";
				}
				return "img/pawn-water-crowned.png";
			}
			if (!p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-water.png";
				}
				if (p.isShield()) {
					return "img/shield-water.png";
				}
				return "img/pawn-water.png";
			}
		}
		return null;
	}
}