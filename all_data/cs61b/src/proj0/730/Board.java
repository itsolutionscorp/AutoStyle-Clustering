public class Board {
	private Piece[][] boardPieces = new Piece[8][8];
	private boolean fireTurn = true;
	private boolean selected = false;
	private boolean moved = false;
	private int selectedX;
	private int selectedY;
	private Piece selectedPiece;


	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			pieceArray(boardPieces);
		}
	}

	private void pieceArray(Piece[][] lst) {
		for (int i=0; i<8; i++) {
			if (i==0) {
				for (int j=0; j<8; j+=2) {
					lst[j][i] = new Piece(true, this, j, i, "pawn");
				}
			}
			if (i==1) {
				for (int j=1; j<8; j+=2) {
					lst[j][i] = new Piece(true, this, j, i, "shield");
				}
			}
			if (i==2) {
				for (int j=0; j<8; j+=2) {
					lst[j][i] = new Piece(true, this, j, i, "bomb");
				}
			}
			if (i==5) {
				for (int j=1; j<8; j+=2) {
					lst[j][i] = new Piece(false, this, j, i, "bomb");
				}
			}
			if (i==6) {
				for (int j=0; j<8; j+=2) {
					lst[j][i] = new Piece(false, this, j, i, "shield");
				}
			}
			if (i==7) {
				for (int j=1; j<8; j+=2) {
					lst[j][i] = new Piece(false, this, j, i, "pawn");
				}
			}
		}
	}

	private void drawB(int N) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                if (selectedPiece!=null && selectedPiece==boardPieces[i][j]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (boardPieces[i][j]!=null) {
                	String name = "";
                	if (boardPieces[i][j].isBomb()) {
                		name += "bomb";
                	}
                	else if (boardPieces[i][j].isShield()) {
                		name += "shield";
                	} else {
                		name += "pawn";
                	}
                	if (boardPieces[i][j].isFire()) {
                		name += "-fire";
                	} else {
                		name += "-water";
                	}
                	if (boardPieces[i][j].isKing()) {
                		name += "-crowned";
                	}
        			StdDrawPlus.picture(i + .5, j + .5, "img/" + name + ".png", 1, 1);
        		}
            }
        }          
    }


	public Piece pieceAt(int x, int y) {
		if (x>7 || y>7 || x<0 || y<0 || boardPieces[x][y]==null) {
			return null;
		} else {
			return boardPieces[x][y];
		}
	}

	
	public boolean canSelect(int x, int y) {
		if (x>=0 && y>=0 && x<8 && y<8) {
			if (boardPieces[x][y]!=null && ((boardPieces[x][y].isFire() && fireTurn) || (!boardPieces[x][y].isFire() && !fireTurn))) {
				if (!selected || (selected && !moved)) {
					return true;
				}
			}
			if (selectedPiece != null && boardPieces[x][y]==null && ((selectedPiece.isFire() && fireTurn) || (!selectedPiece.isFire() && !fireTurn))) {
				if (selected && !moved && validMove(selectedX, selectedY, x, y)) {
			 		return true;
				}
				if (selected && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (boardPieces[xf][yf]==null && boardPieces[xi][yi]!=null && xf<8 && yf<8 && xf>=0 && yf>=0) {
			if (((xi==xf+1 || xi==xf-1) && (yi==yf+1 || yi==yf-1)) || ((xi==xf+2 || xi==xf-2) && (yi==yf+2 || yi==yf-2)
				&& boardPieces[(xf+xi)/2][(yf+yi)/2]!=null)) {
				if (boardPieces[xi][yi].isKing()) {
					return true;
				}
				if (boardPieces[xi][yi].isFire() && yf>yi) {
					return true;
				}
				if (!boardPieces[xi][yi].isFire() && yi>yf) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (boardPieces[x][y]!=null) {
			selected = true;
			selectedX = x;
			selectedY = y;
			selectedPiece = boardPieces[x][y];
		} else {
			if (selectedPiece != null) {
				moved = true;
				selectedPiece.move(x, y);
				selectedX = x;
				selectedY = y;
				if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
					selectedPiece = null;
				}
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (x<8 && y<8 && x>=0 && y>=0 && p!=null) {
			boardPieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		Piece returned = boardPieces[x][y];
		if (x>7 || y>7 || x<0 || y<0) {
			System.out.println("Piece out of bounds.");
			return null;
		}
		if (boardPieces[x][y]==null) {
			System.out.println("No piece at location.");
			return null;
		} else {
			boardPieces[x][y] = null;
			return returned;
		}
	}

	public boolean canEndTurn() {
		if (moved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (canEndTurn()) {
			fireTurn = !fireTurn;
			moved = false;
			selected = false;
			selectedPiece = null;
		}
	}

	public String winner() {
		boolean fireLeft = false;
		boolean waterLeft = false;
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (boardPieces[i][j]!=null && boardPieces[i][j].isFire()) {
					fireLeft = true;
				}
				if (boardPieces[i][j]!=null && !boardPieces[i][j].isFire()) {
					waterLeft = true;
				}
			}
		}
		if (fireLeft && !waterLeft) {
			return "Fire";
		}
		if (!fireLeft && waterLeft) {
			return "Water";
		}
		if (!fireLeft && !waterLeft) {
			return "No One";
		}
		return null;
	}

	public static void main(String[] args) {
    	StdDrawPlus.setXscale(0, 8);
    	StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);

		while (b.winner()==null) {
			b.drawB(8);
			if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
            	double y = StdDrawPlus.mouseY();
            	if (b.canSelect((int) x, (int) y)) {
            		b.select((int) x, (int) y);
            	}
			}
			if (StdDrawPlus.isSpacePressed()) {
				b.endTurn();
			}
			StdDrawPlus.show(20);
		}
	}

}