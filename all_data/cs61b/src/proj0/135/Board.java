
/**
 *
 * @author nagalluri
 */
public class Board {

	private Piece[][] pieces;
	private Piece removed;
	private int turn = 0;
	private int moved = 0;
	private Piece selected = null;
	private boolean capture = false;
	private static boolean[][] p;


	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                	if (j == 0) {
                		pieces[i][j] = new Piece(true, this, i, j, "pawn");
                	}
                	else if (j == 1) {
                		pieces[i][j] = new Piece(true, this, i, j, "shield");
                	}
                	else if (j == 2) {
                		pieces[i][j] = new Piece(true, this, i, j, "bomb");
                	}
                	else if (j == 5) {
                		pieces[i][j] = new Piece(false, this, i, j, "bomb");
                	}
                	else if (j == 6) {
                		pieces[i][j] = new Piece(false, this, i, j, "shield");
                	}
                	else if (j == 7) {
                		pieces[i][j] = new Piece(false, this, i, j, "pawn");
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
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (p[i][j]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                	}	
                }	
            }
        }

    private void drawPieces() {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isShield()) {
            			if (pieces[i][j].isFire()) {
            				if (pieces[i][j].isKing()){
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            				}
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            			}
            			else {
            				if (pieces[i][j].isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            				}
            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            			}
            		}
            		else if (pieces[i][j].isBomb()) {
            			if (pieces[i][j].isFire()) {
            				if (pieces[i][j].isKing()){
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            				}
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            			}
            			else {
            				if (pieces[i][j].isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            				}
            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            			}
            		}
            		else {
            			if (pieces[i][j].isFire()) {
            				if (pieces[i][j].isKing()){
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            				}
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            			}
            			else {
            				if (pieces[i][j].isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            				}
            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            			}
            		}
        		}
    		}
   	 	}
   	}	
    
    public Piece pieceAt(int x, int y) {
    	if ((x > 7) || (x < 0) || (y > 7) || (y < 0) || (pieces[x][y] == null) || ((x + y) % 2 != 0)) {
    		return null;
    	} else {
    		return pieces[x][y];
    	}
    }

    public void place(Piece p, int x, int y) {
    	if ((p != null) && (x < 8) && (x >= 0) && (y < 8) && (y >= 0) && ((x + y) % 2 == 0)) {
    		pieces[x][y] = p;

    	} 
    }


    public Piece remove(int x, int y) {
    	if ((x > 7) || (x < 0) || (y > 7) || (y < 0) || ((x + y) % 2 != 0)) {
    		System.out.println("Out of Bounds!");
    		return null;
    	} else if (pieces[x][y] == null) {
    		System.out.println("There is no piece here!");
    		return null;
    	} else
    		removed = pieces[x][y];
    		pieces[x][y] = null;
    		return removed;
    	}

    public String winner() {
    	int firenum = 0;
    	int waternum = 0;
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		firenum += 1;
                	} else {
                		waternum += 1;
                	}
                }
            }
        }
        if ((waternum == 0) && (firenum == 0)) {
        	return "No one";
        } 
        else if ((waternum > 0) && (firenum == 0)) {
        	return "Water";
        }
        else if ((waternum == 0) && (firenum > 0)) {
        	return "Fire";
        } else {
        	return null;
        }
    }

    public boolean canSelect(int x, int y) {
    	if (pieceAt(x,y) != null && turn == pieceAt(x,y).side()) {
    		if (selected == null || moved == 0) {
    			return true;
    		}
    	} 
    	if (pieceAt(x,y) == null && selected != null) {
    		if (selected.hasCaptured()){
    			return validMove(PieceX(selected), PieceY(selected), x, y);
    		}
    		else if (moved != 1) {
    			return validMove(PieceX(selected), PieceY(selected), x, y);
    		}
    	}
    return false;
	}


    public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
		} else if ((selected != null) && (pieceAt(x, y) == null)) {
			selected.move(x, y);
			this.moved += 1;
			}
		}

    public boolean canEndTurn() {
    	if (moved != 0) {
    		return true;
    	} else if (selected != null) {
    		if (selected.hasCaptured() == true || moved == 1) {
    			return true;
    		} 
    	}
    	return false;
    }

    public void endTurn() {
    	selected.doneCapturing();
    	selected = null;
    	moved = 0;
    	if (turn == 0) {
    		turn = 1;
    	} else if (turn == 1) {
    		turn = 0;
    	}
    }

    private int PieceX(Piece p) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p) {
            		return i;
            	}
            }
        }
    	return 0;
    }


    private int PieceY(Piece p) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p) {
            		return j;
            	}
            }
        }
    	return 0;
    }



	private boolean validMove(int xi, int yi, int xf, int yf) {
		
		if ((pieces[xf][yf] != null) || (xf > 7) || (yf > 7) || (xf < 0) || (yf < 0) || ((xf + yf) % 2 != 0) || (pieces[xi][yi] == null)) {
				return false;
		}
		else if ((pieces[xi][yi].side() == 0)) { 
		 	if ((pieces[xi][yi].isKing() == false)) {
				if (((yf - yi) == 1) && ((xf - xi) == 1) && (moved == 0) && (selected.hasCaptured() == false)) {
					return true;
				} else if (((yf - yi) == 1) && ((xf - xi) == -1) && (moved == 0) && (selected.hasCaptured() == false)) { 
					return true;
				} else if ((yf - yi == 2) && (Math.abs(xf - xi) == 2) && (pieces[(xi + xf)/2][(yi + yf)/2] != null) && (pieces[(xi + xf)/2][(yi + yf)/2].side() != pieces[xi][yi].side())) {
					this.moved = 1;
					return true;
				} else {
					return false;
				}
			} 
			else if ((pieces[xi][yi].isKing() == true)) {
				if ((Math.abs(yi - yf) == 1) && (Math.abs(xi - xf) == 1) && (moved == 0) && (selected.hasCaptured() == false)) {
					return true;
				} else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2) && (pieces[(xi + xf)/2][(yi + yf)/2] != null) && (pieces[(xi + xf)/2][(yi + yf)/2].side() != pieces[xi][yi].side())) {
					this.moved = 1;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if ((pieces[xi][yi].side() == 1)) {
			if ((pieces[xi][yi].isKing() == false)) {
				if (((yf - yi) == -1) && ((xf - xi) == -1) && (moved == 0) && (selected.hasCaptured() == false)) {
					return true;
				} else if (((yf - yi) == -1) && ((xf - xi) == 1) && (moved == 0) && (selected.hasCaptured() == false)) { 
					return true;
				} else if ((yf - yi == -2) && (Math.abs(xf - xi) == 2) && (pieces[(xi + xf)/2][(yi + yf)/2] != null) && (pieces[(xi + xf)/2][(yi + yf)/2].side() != pieces[xi][yi].side())) {
					this.moved = 1;					
					return true;
				} else {
					return false;
				}
			} 
			else if ((pieces[xi][yi].isKing() == true)) {
				if ((Math.abs(yi - yf) == 1) && (Math.abs(xi - xf) == 1) && (moved == 0) && (selected.hasCaptured() == false)) {
					return true;
				} else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2) && (pieces[(xi + xf)/2][(yi + yf)/2] != null) && (pieces[(xi + xf)/2][(yi + yf)/2].side() != pieces[xi][yi].side())) {
					this.moved = 1;					
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
    

	public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        p = new boolean[N][N];
	
        while(b.winner() == null) {
            b.drawBoard(N);
            b.drawPieces();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
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
}


