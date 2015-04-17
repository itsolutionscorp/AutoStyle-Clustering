public class Board{
    private  Piece[][] pieces;
    private static int size = 8;
    private boolean hasMoved = false;
    private boolean hasSelected = false;
    private boolean isFireTurn = true;
    private int curX, curY;

    public static void main(String args[]){
        StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
	boolean isempty = false;
        Board boa = new Board(isempty);

        while (isempty || boa.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
		int x = (int) StdDrawPlus.mouseX();                
		int y = (int) StdDrawPlus.mouseY();
		if (x >= 0 && x < size && y >= 0 && y < size) {
		    if (boa.canSelect(x, y)) {
			boa.select(x, y);
		    }
		}
		boa.drawBoard(size);
		//boa.dumpBoard();
            } else if (StdDrawPlus.isSpacePressed()) {
		if (boa.canEndTurn()) {
		    boa.endTurn();
		}

		boa.drawBoard(size);
		//boa.dumpBoard();
	    }

	}   
    }

    private void dumpBoard() {
	System.out.println("isFireTurn:" + isFireTurn);
	for (int i = 0; i < size; ++i) {
	    for (int j = 0; j < size; ++j) {
		if (pieces[i][j] != null) {
		    System.out.println(i + " " + j);
		    System.out.println(" king:" + pieces[i][j].isKing() + 
				       " fire:" + pieces[i][j].isFire());
		}
	    }
	}
    }

    //      /** Draws an N x N board. Adapted from:
    //         http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
    //      */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { 
		if ((i + j) % 2 == 0) {
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		if (hasSelected) {
		    if (i == curX && j == curY) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		    }
		}
		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		if (pieces[i][j] != null && pieces[i][j].isFire()) {
		    if (pieces[i][j].isShield()) {
			if (pieces[i][j].isKing()) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
			}
		    } else if (pieces[i][j].isBomb()) {
			if (pieces[i][j].isKing()) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
			}
		    } else {
			if (pieces[i][j].isKing()) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
			}
		    } 
		} else if(pieces[i][j]!=null && !pieces[i][j].isFire()) {
		    if (pieces[i][j].isShield()) {
			if (pieces[i][j].isKing()) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
			}
		    } else if (pieces[i][j].isBomb()) {
			if (pieces[i][j].isKing()) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			}
		    } else {
			if (pieces[i][j].isKing()) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			}
		    }
		}
	    } 
	}

	StdDrawPlus.show(100);
    }
    
    private void drawEmptyBoard(int N) {   
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		pieces[i][j] = null;
	    }
	}

	//pieces[0][4] = new Piece(false, this, 0, 4, "pawn");
	//pieces[2][2] = new Piece(false, this, 2, 2, "Pawn");
	drawBoard(size);
    }

    public Board(boolean shouldBeEmpty) {  
	Board board = this;
	board.pieces = new Piece[size][size];
 	if (shouldBeEmpty == true) {
	    drawEmptyBoard(size);
	} else {
	    for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++){
		    if ((i + j) % 2 == 0) {
			if (j == 0) {
			    Piece piece = new Piece(true, board, i, j, "pawn");
			    pieces[i][j] = piece;
			} else if (j == 1) {
			    Piece piece = new Piece(true, board, i, j, "bomb");
			    pieces[i][j]= piece;
			} else if (j == 2) {
			    Piece piece = new Piece(true, board, i, j, "shield");
			    pieces[i][j] = piece;
			} else if (j == 5) {
			    Piece piece = new Piece(false, board, i, j, "shield");
			    pieces[i][j] = piece;
			} else if (j == 6) {
			    Piece piece = new Piece(false, board, i, j, "bomb");
			    pieces[i][j]= piece;
			} else if (j == 7) {
			    Piece piece = new Piece(false, board, i, j, "pawn");
			    pieces[i][j] = piece; 
			} else {
			    pieces[i][j] = null;
			}
		    }
		}
	    }

	    drawBoard(size);
	}
    }

    public Piece pieceAt(int x, int y) {
	if ((x > 7) || (x < 0) || (y < 0) || (y > 7)) {
	    return null;
	} else {
 	    return pieces[x][y];
	}
    }

    public void place(Piece p, int x, int y) {
	if (x < 0 || x > 7 || y < 0 || y > 7 || p == null) {
	    return;
	}
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (pieces[x][y] == null || x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("No piece to remove");
            return null;
        } else {
	    Piece p = pieces[x][y];
	    pieces[x][y] = null;
            return p;
        }
    }

    public boolean canEndTurn(){
	return hasMoved;
    }

    public String winner() {
        int fireNum = 0;
        int waterNum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
		if (pieces[i][j] != null) {
		    if (!pieces[i][j].isFire()) {
			waterNum += 1;
		    } else if (pieces[i][j].isFire()) {
			fireNum += 1;
		    }
		}
            }
        }

        if (waterNum == 0 && 0 < fireNum) {
            return "Fire";
        } else if (fireNum == 0 && 0 < waterNum) {
            return "Water";
        } else if (waterNum == 0 && fireNum == 0 ) {
            return "No one";
        } else {
            return null;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
	int midX = (xf + xi) / 2;
	int midY = (yf + yi) / 2;
	if (pieces[xf][yf] != null || pieces[xi][yi] == null) {
	    return false;
	} else if (pieces[xi][yi].isKing()) {
	    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1) {
		return true;
	    } else if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2 && 
		       pieces[midX][midY] != null &&
		       (pieces[midX][midY].isFire() != pieces[xi][yi].isFire())) {
		return true;
	    } 
	} else if (pieces[xi][yi].isFire()) {
	    if ((yf - yi) == 1 && Math.abs(xf - xi) == 1) {
		return true;
	    } else if (pieces[midX][midY] != null && !pieces[midX][midY].isFire() && 
		       (yf - yi) == 2 && Math.abs(xf - xi) == 2) {
		return true;
	    }
	} else if (!pieces[xi][yi].isFire()) {
	    if ((yf - yi) == -1 && Math.abs(xf - xi) == 1) {
		return true;
	    } else if (pieces[midX][midY] != null && pieces[midX][midY].isFire() && 
		       (yf - yi) == -2 && Math.abs(xf - xi) == 2) {
		return true;
	    }
	} 
	return false;
    }

    public boolean canSelect(int x, int y) {
	if (x < 0 || x > 7 || y < 0 || y > 7) {
	    return false;
	}
	if (!hasSelected && hasMoved) {
	    return false; // last move must have been a bomb exploding.
	}

	if (pieces[x][y] != null) {
	    if (isFireTurn != pieces[x][y].isFire()) {
		return false; 
	    } else if (!hasSelected || (hasSelected && !hasMoved)) {
		return true;
	    } else {
		return false;
	    }
	} else {
	    if (hasSelected && !hasMoved && validMove(curX, curY, x, y)) {
		return true;
	    } else if (hasSelected && hasMoved && pieces[curX][curY].hasCaptured() 
		       && validMove(curX, curY, x, y)) {
		// in multicapture, allow this only if
		// its another big jump
		return Math.abs(x - curX) == 2; 
	    } else {
		return false;
	    }
	} 
    }

    public void select(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }

        if (pieces[x][y] == null) {
            pieces[curX][curY].move(x, y);
	    hasMoved = true;
        }

	if (pieces[x][y] != null) {
	    hasSelected = true;
	} else { 
	    // the last move might be a bomb capturing which will explode
	    // and there would be no selected piece.
	    hasSelected = false;
	}
        curX = x;
        curY = y;
    }
     
    public void endTurn() {
	hasMoved = false;
	isFireTurn = !isFireTurn;
	hasSelected = false;
	if (pieces[curX][curY] != null) {
	    pieces[curX][curY].doneCapturing();
	}
    }
}




 


