/** 
 *  @author Peijie Li
 */
public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private boolean hasmoved = false;
    private boolean iscurrentfire = true;
    private int selectx = -1;
    private int selecty = -1;

 
    //initialize Board
    public Board (boolean shouldbeEmpty) {
        if (shouldbeEmpty){
	    pieces = new Piece[8][8];
        } else {
	    pieces = new Piece[8][8]; //set pieces into board
		for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        if (j == 0) {
                            pieces[i][j] = new Piece (true, this, i, j, "pawn");
                        } else if (j == 1) {
                            pieces[i][j] = new Piece (true, this, i, j, "shield");
                        } else if (j == 2) {
                            pieces[i][j] = new Piece (true, this, i, j, "bomb");
                        } else if (j == 5) {
                            pieces[i][j] = new Piece (false, this, i, j, "bomb");
                        } else if (j == 6) {
                            pieces[i][j] = new Piece (false, this, i, j, "shield");
                        } else if (j == 7) {
                            pieces[i][j] = new Piece (false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
    }
    //drawboard method
    private  void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
    private void drawPieces(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    String fireorwater;
                    if (pieces[i][j].isFire()){
                        fireorwater = "fire";
                    } else {
                        fireorwater = "water";
                    }
                    String itstype;
                    if (pieces[i][j].isBomb()) {
                        itstype = "bomb";
                    } else if (pieces[i][j].isShield()) {
                        itstype = "shield";
                    } else {
                        itstype = "pawn";
                    }
                    if (pieces[i][j].isKing() == false) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + itstype+"-"+fireorwater+".png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + itstype+"-"+fireorwater+ "-crowned.png", 1, 1);
                    }
                }
            }
        }
    }
    //gets the piece at position(x,y)on the board.
    public Piece pieceAt(int x, int y) {
        if (x > 7 || x < 0 || y < 0 || y > 7) {
            return null;
	} else if (pieces[x][y] == null) {
            return null;
        } else {
            return pieces[x][y];
        }
    }
    //places the piece p at (x,y) **if p already exists on the board, this method does not remove p
    public void place(Piece p, int x, int y) {
        if (x > 7 || x < 0 || y < 0 || y > 7) {
        } else /*if (p != null)*/ {
            pieces[x][y] = p;
        }
    }
    //removes a piece and return the one removed.
    public  Piece remove(int x, int y) {
        if (x > 7 || x < 0 || y < 0 || y > 7) {
            System.out.println("x or y is out of bound" + x + y);
            return null;
        } else if (pieces[x][y] == null) {
            System.out.println("there is no piece at" + x + " , " + y);
            return null;
        } else {
            Piece copy = pieceAt(x, y);
            pieces[x][y] = null;
            return copy;
        }
    }


   
    // returns the winner of the game. 
    //fire/winter/No one/ null
    public String winner() {
        int waternum = 0;
        int firenum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire() == true) {
                        firenum = firenum + 1;
                    } else {
                        waternum = waternum + 1;
                    }
                }
            }
        }
        if (waternum > 0 && firenum > 0) {
            return null;
        } else if (waternum == 0 && firenum == 0) {
            return "No one";
        } else if (waternum == 0 && firenum > 0) {
            return "Fire";
        } else {
            return "Water";
        }
    }
    


    ///return if a square can be selected
    public boolean canSelect(int x, int y){//x could be 1-8
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            if (pieceAt(x, y) == null) {// an empty square
                ////has selected a piece but hasnot moved
                ///and is selecting an empty spot which is a valid move
		if (pieceAt(selectx, selecty) == null) {
		    return false;
		} else  if (pieceAt(selectx, selecty) != null && hasmoved == false && validMove(selectx, selecty, x, y)) {  
                    return true;
                } else if (pieceAt(selectx, selecty) != null && pieceAt(selectx, selecty).hasCaptured() && validMove(selectx, selecty, x, y)) {
                    return true;
                } else {
                    return false;
                }
            } else {//there is a piece
                if (iscurrentfire == pieceAt(x, y).isFire()) {//if current handle is fire and selecting a fire piece
                    if (pieceAt(selectx, selecty) == null && hasmoved == false) {//if has not selected a piece yet
                        return true;
                    } else if (pieceAt(selectx, selecty) != null && hasmoved == false) {//if has selected a piece but hasmoved is false
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
    private  boolean validMove(int xi, int yi, int xf, int yf){
        if (xf < 8 && xf >= 0 && yf < 8 && yf >= 0 && xi < 8 && xi >= 0&& yi < 8 && yi >= 0) {
	    if (pieceAt(xi, yi) == null) {
		return false;
	    } else {
		int midx = (xf + xi) / 2;
		int midy = (yf + yi) / 2;
		Piece p = pieceAt(xi, yi);
		if (pieceAt(xf, yf) == null) {
		    if (p.isFire()) {//isfire. y going up
			if (p.isKing()) {
			    if (Math.abs(yf-yi) == 1 && Math.abs(xi-xf) == 1 && p.hasCaptured() == false) {
				return true;
			    } else if (Math.abs(yf-yi) == 2 && Math.abs(xi-xf) == 2 && pieceAt(midx, midy) != null) {
				if (pieceAt(midx, midy).isFire() == false) {
                                    return true;
				} else {
				    return false;
				}
			    } else {
				return false;
			    }
			} else if (yf == yi + 1 && Math.abs(xi-xf) == 1 && p.hasCaptured() == false) {
			    return true;
			} else if (yf == yi + 2 && Math.abs(xi-xf) == 2 &&  pieceAt(midx, midy) != null) {
			    if (pieceAt(midx, midy).isFire() == false) {
                                return true;
			    } else {
				return false;
			    }
			} else { 
			    return false;}
		    } else { //not fire
			if (p.isKing()) {
			    if (Math.abs(yf-yi) == 1 && Math.abs(xi-xf) == 1 && p.hasCaptured() == false) {
				return true;
			    } else if (Math.abs(yf-yi) == 2 && Math.abs(xi-xf) == 2 && pieceAt(midx, midy) != null) {
				if (pieceAt(midx, midy).isFire() == true) {
                                    return true;
				} else {
				    return false;
				}
			    } else {	return false; }
			} else if (yf == yi - 1 && Math.abs(xi-xf) == 1 && p.hasCaptured() == false) {
			    return true;
			} else if (yf == yi - 2 && Math.abs(xi-xf) == 2 &&  pieceAt(midx, midy) != null) {
			    if (pieceAt(midx, midy).isFire() == true) {
                                return true;
			    } else {
				return false;
			    }
			} else {  
			    return false;
			}
		    }
		} else {
		    return false;
		}
	    }
	}	else {
	    return false;
	}
    }

    //selects the piece at (x, y) if possible
    public void select(int x, int y){
	if (x > 7 || x < 0 || y < 0 || y > 7) {
	} else if (pieceAt(x, y) != null) {
            selectx = x;
            selecty = y;
        } else {
            pieceAt(selectx, selecty).move(x, y);
            selectx = x;
            selecty = y;
            hasmoved = true;
        }
    }


    public  boolean canEndTurn() {
        return hasmoved;
    }

    public void endTurn() {
	iscurrentfire = !iscurrentfire;
	hasmoved = false;
	if (pieceAt(selectx, selecty) != null) {
	    pieceAt(selectx, selecty).doneCapturing();
	}
	selectx = -1;
	selecty = -1;
    }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board bo = new Board(false);
        while(true) {
            bo.drawBoard(N);
            bo.drawPieces(N);
            StdDrawPlus.show(100);
            if (StdDrawPlus.isNPressed()) {
                bo = new Board(false);
            }
            if (StdDrawPlus.mousePressed()) {
                int mx = (int) StdDrawPlus.mouseX();
                int my = (int) StdDrawPlus.mouseY();
                if (bo.canSelect(mx, my)) {      
                    bo.select(mx, my);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(mx + 0.5, my + 0.5, 0.5);
                    StdDrawPlus.show(100);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (bo.canEndTurn()) {
                    bo.endTurn();
                }
            }
            bo.winner();
        }
    }
}
