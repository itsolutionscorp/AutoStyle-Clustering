public class Board {
	private static Piece[][] onBoard;
	private static int xOriginal;
	private static int yOriginal;
	private static int myTurn;
	private boolean selected;
	private boolean moved;
	private int firecount;
	private int watercount;
	private int N;

	//what this this do?// how does this array get on the board?//
	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (onBoard[i][j] != null) {
                    if (onBoard[i][j].side() == 0) {
                    	if (onBoard[i][j].isBomb()) {
                    		if (onBoard[i][j].isKing()) {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    		} else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    		}	
                    	} else if (onBoard[i][j].isShield()) {
                    		if (onBoard[i][j].isKing()) {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    		} else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    		}
                    	} else {
                    		if (onBoard[i][j].isKing()) {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    		} else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    		}
                    	}
                    	
                    } else {
                    	if (onBoard[i][j].isBomb()) {
                    		if (onBoard[i][j].isKing()) {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    		} else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);	
                    			}
                    		} else if (onBoard[i][j].isShield()) {
                    			if (onBoard[i][j].isKing()) {
                    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    			} else {
                    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    			}
                    		} else {
                    			if (onBoard[i][j].isKing()) {
                    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    			} else {
                    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    			}
                    		}
                    	}
                    }
     
                }
            }
        }

	public Board(boolean shouldBeEmpty) {
		selected = false;
		moved = false;
		myTurn = 0;
		N = 8;
		onBoard = new Piece [8][8];  //explain//
		if (shouldBeEmpty == true) {
		}
		else {
			onBoard[0][0] = new Piece (true, this, 0, 0, "pawn");
			onBoard[0][2] = new Piece (true, this, 0, 2, "bomb");
			onBoard[1][1] = new Piece (true, this, 1, 1, "shield");
			onBoard[2][0] = new Piece (true, this, 2, 0, "pawn");
			onBoard[2][2] = new Piece (true, this, 2, 2, "bomb");
			onBoard[3][1] = new Piece (true, this, 3, 1, "shield");
			onBoard[4][0] = new Piece (true, this, 4, 0, "pawn");
			onBoard[4][2] = new Piece (true, this, 4, 2, "bomb");
			onBoard[5][1] = new Piece (true, this, 5, 1, "shield");
			onBoard[6][0] = new Piece (true, this, 6, 0, "pawn");
			onBoard[6][2] = new Piece (true, this, 6, 2, "bomb");
			onBoard[7][1] = new Piece (true, this, 7, 1, "shield");

			onBoard[0][6] = new Piece (false, this, 0, 6, "shield");
			onBoard[1][5] = new Piece (false, this, 1, 5, "bomb");
			onBoard[1][7] = new Piece (false, this, 1, 7, "pawn");
			onBoard[2][6] = new Piece (false, this, 2, 6, "shield");
			onBoard[3][5] = new Piece (false, this, 3, 5, "bomb");
			onBoard[3][7] = new Piece (false, this, 3, 7, "pawn");
			onBoard[4][6] = new Piece (false, this, 4, 6, "shield");
			onBoard[5][5] = new Piece (false, this, 5, 5, "bomb");
			onBoard[5][7] = new Piece (false, this, 5, 7, "pawn");
			onBoard[6][6] = new Piece (false, this, 6, 6, "shield");
			onBoard[7][5] = new Piece (false, this, 7, 5, "bomb");
			onBoard[7][7] = new Piece (false, this, 7, 7, "pawn");
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7 || onBoard[x][y] == null) {
			return null;
		} else {
			return onBoard[x][y];
		}
	}

	public void place(Piece p, int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7 || p == null) {			
		} else if (onBoard[x][y] != null) {
			onBoard[x][y] = p;      //check this//
			} else {
				onBoard[x][y] = p;
			}
		}

	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7 || onBoard[x][y] == null) {
			System.out.println("Piece out of bounds or no piece");
			return null;
		} else {
			onBoard[x][y] = null;
			return onBoard[x][y];
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		int lfy = yf - yi;
		int distX = Math.abs(xf - xi);
		int distY = Math.abs(yf - yi);
		int lfx = xf - xi; 
		if (onBoard[xf][yf] == null) {
			if (onBoard[xi][yi].isFire()) {
				if (onBoard[xi][yi].isKing()) {
					if ((distX == 1) && (distY == 1)) {
						return true;
					} else if ( (((lfx == 2) && (lfy == 2)) && ((pieceAt((xf - 1), (yf - 1))).isFire() == false))
								|| (((lfx == -2) && (lfy == 2)) && ((pieceAt((xf + 1), (yf - 1))).isFire() == false))
								|| (((lfx == 2) && (lfy == -2)) && ((pieceAt((xf - 1), (yf + 1))).isFire() == false))
								|| (((lfx == -2) && (lfy == -2)) && ((pieceAt((xf + 1), (yf + 1))).isFire() == false)) )  {
						return true;
					}
				} else {
					if ((lfy == 1) && (distX == 1)) {
						return true;
					} else if ((((lfx == 2) && (lfy == 2)) && ((pieceAt((xf - 1), (yf - 1))).isFire() == false))
								|| (((lfx == -2) && (lfy == 2)) && ((pieceAt((xf + 1), (yf - 1))).isFire() == false))) {
						return true;
						}
					}
				} else {
					if (onBoard[xi][yi].isKing()) {
						if ((distX == 1) && (distY == 1)) {
							return true;
						} else if ((((lfx == 2) && (lfy == 2)) && ((pieceAt((xf - 1), (yf - 1))).isFire()))
								|| (((lfx == -2) && (lfy == 2)) && ((pieceAt((xf + 1), (yf - 1))).isFire()))
								|| (((lfx == 2) && (lfy == -2)) && ((pieceAt((xf - 1), (yf + 1))).isFire()))
								|| (((lfx == -2) && (lfy == -2)) && ((pieceAt((xf + 1), (yf + 1))).isFire()))) {
							return true;
						}
					} else {
						if ((lfy == -1) && (distX == 1)) {
							return true;
						} else if ((((lfx == 2) && (lfy == -2)) && ((pieceAt((xf - 1), (yf + 1))).isFire()))
								|| (((lfx == -2) && (lfy == -2)) && ((pieceAt((xf + 1), (yf + 1))).isFire()))) {
							return true;
						}
					}
				}
			} 
			return false;
		}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if (pieceAt(x, y).side() == myTurn) {
				if (moved == false) {
					xOriginal = x;
					yOriginal = y;
					return true;
				} 
			}
		} else {
			if (selected) {
				if (moved == false) {
					if (validMove(xOriginal ,yOriginal, x, y)) {
						return true;
					}
				} else {
					if (pieceAt(xOriginal,yOriginal).hasCaptured()) {
						if (validMove(xOriginal, yOriginal, x, y)) {
							return true;
						}
					} return false;
			}
		}
			
		} 
		return false;
	}

	public void select (int x, int y) {
		if (pieceAt(x, y) != null) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			selected = true;

			xOriginal = x;
			yOriginal = y;
		} else if (selected) {
			onBoard[xOriginal][yOriginal].move(x, y);
			//remove(xOriginal, yOriginal); 
			//place(pieceAt(xOriginal,yOriginal), x, y);
			moved = true;
		}
	}

	public boolean canEndTurn() {
		if (moved) {
			return true;
		}
			return false;
	}

	public void endTurn() {
		if (myTurn == 0) {
			myTurn = 1;
		} else {
			myTurn = 0;
		}
		moved = false;
		selected = false;
	}

	public String winner() {
		firecount = 0;
		watercount = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (onBoard[i][j] != null) {
            		if (onBoard[i][j].isFire()) {
            			firecount = firecount + 1;
            		} else {
            			watercount = watercount + 1;
            		}
            	}
			
		}
	}
	if (firecount == 0) {
		return "Water";
	} else if (watercount == 0) {
		return "Fire";
	} else if ((watercount == 0) && (firecount == 0)) {
		return "No One";
	} else {
		return null;
	}
}

	 
	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board (false);
        
        while(true) {    
        	drawBoard(N);    	
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

               	if (b.canSelect((int) x, (int) y) == true) {
                	b.select((int) x, (int) y);
                }
            }
            if ((StdDrawPlus.isSpacePressed()) && (b.canEndTurn())) {
            	b.endTurn();
            	b.winner();

            }          
            StdDrawPlus.show(100);
	
        } 
    }
}
	