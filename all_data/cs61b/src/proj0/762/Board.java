public class Board {
    private Piece[][] board;
    private boolean fireturn = true; // Determines who is currently moving.
    private boolean inHand = false; // Selecting a piece.
    private boolean hasMoved = false;
    private boolean hasCaptured = false; //did i capture last turn
    private int selectedx;
    private int selectedy;
    private Piece selected;
  	private Piece removed;
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */ 
    public Board(boolean shouldBeEmpty) {
    	board = new Piece[8][8];
    	if (shouldBeEmpty) {
    		for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    board[x][y] = null;
                }
            }
    	}
    	else {
    		for (int x = 0; x < 8; x++) {
	    		for (int y = 0; y < 8; y++) {
		    		if ((y == 0) && (x % 2 == 0)) {
		    			board[x][y] = new Piece(true, this, x, y, "Pawn");
		            }
		            if ((y == 1) && (x % 2 != 0)) {
		    			board[x][y] = new Piece(true, this, x, y, "Shield");
		        	}
		        	if ((y == 2) && (x % 2 == 0)) {
		    			board[x][y] = new Piece(true, this, x, y, "Bomb");
		        	}
		        	if ((y == 5) && (x % 2 != 0)) {
		    			board[x][y] = new Piece(false, this, x, y, "Bomb");
		        	}	
		        	if ((y == 6) && (x % 2 == 0)) {
	    				board[x][y] = new Piece(false, this, x, y, "Shield");
		        	}
		        	if ((y == 7) && (x % 2 != 0)) {
		    			board[x][y] = new Piece(false, this, x, y, "Pawn");
		        	}	    			
	    		}
	    	}
    	}
    }

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0 ) || (y < 0)) {
            return null;
        }
		if (board[x][y] != null) {
            Piece piece = board[x][y];
			return piece;
        }
        return null;
    }

    public void place(Piece p, int x, int y) {
    	if ((x > 7) || (y > 7) || (x < 0 ) || (y < 0)) {
    		return;
    	}
        if (this.board == null) {
            return;
        }
        if (p == null) {
            return;
        }
        else {
            board[x][y] = p;
  
        }
    }

    private boolean validMoveKing(int xi, int yi, int xf, int yf) {
    	int dx = xf - xi;
    	int dy = yf - yi;
		if ((Math.abs(dx) == 1) && Math.abs(dy) == 1) {
			if (pieceAt(xf, yf) == null) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (dx == 2 && dy == 2) {
				if ((board[xi+1][yi+1] != null) && !(board[xi+1][yi+1].isFire())) {
					removed = board[xi+1][yi+1];
					return true;
    			}
    			else {
    				return false;
				}
			}
			if (dx == -2 && dy == 2) {
    			if ((board[xi-1][yi+1] != null) && !(board[xi-1][yi+1].isFire())) {
    				removed = board[xi-1][yi+1];
    				return true;
    			}
    			else {
    				return false;
    			}
			}
			if (dx == 2 && dy == -2) {
				if ((board[xi+1][yi-1] != null) && !(board[xi+1][yi-1].isFire())) {
					removed = board[xi+1][yi-1];
					return true;
    			}
    			else {
    				return false;
				}
			}
			if (dx == -2 && dy == -2) {
    			if ((board[xi-1][yi-1] != null) && !(board[xi-1][yi-1].isFire())) {
    				removed = board[xi-1][yi-1];
    				return true;
    			}
    			else {
    				return false;
    			}
			}
		}
		return false;
	}

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	Piece currentpiece = pieceAt(xi, yi);
    	if ((xf > 7) || (yf > 7) || (xf < 0) || (yf < 0)) {
    		return false;
    	}
    	int dy = yf - yi;
    	int dx = xf - xi;

    	if (currentpiece.isFire()) {		
	     	if ((Math.abs(dx) == 1) && dy == 1) {
	    		if (pieceAt(xf, yf) == null) {
	    			return true;
	    		} else {
	    			return false;
	    		}
	    	} 
	    	else { // Determine whether a jump is available
	    		if (dx == 2 && dy == 2) {
	    			if ((pieceAt(xi+1, yi+1) != null) && !(pieceAt(xi+1, yi+1).isFire())) {
	    				removed = pieceAt(xi+1, yi+1);
	    				return true;
	    			}
	    			else {
	    				return false;
    				}
				}
    			if (dx == -2 && dy == 2) {
	    			if ((pieceAt(xi-1, yi+1) != null) && !(pieceAt(xi-1, yi+1).isFire())) {
	    				removed = pieceAt(xi-1,yi+1);
	    				return true;
	    			}
	    			else {
	    				return false;
	    			}
    			}
			}
			if (currentpiece.isKing()) {
				return validMoveKing(xi, yi, xf, yf);
			}
			return false;
		}
    	else {
	     	if ((Math.abs(dx) == 1) && dy == -1) {
	    		if (pieceAt(xf, yf) == null) {
	    			return true;
	    		} else {
	    			return false;
    			}
			}
	    	if (dx == 2 && dy == -2) {
    			if ((this.pieceAt(xi+1, yi-1) != null) && ((this.pieceAt(xi+1, yi-1).isFire()))) {
    				removed = board[xi+1][yi-1];
    				return true;
    			}
    			else {
    				return false;
				}
			}
    		if (dx == -2 && dy == -2) {
    			if ((this.pieceAt(xi-1,yi-1) != null) && (this.pieceAt(xi-1, yi-1).isFire())) {
    				removed = this.pieceAt(xi-1, yi-1);
    				return true;
    			}
    			else {
    				return false;
    			}
			}
			if (currentpiece.isKing()) {
				return validMoveKing(xi, yi, xf, yf);
			}
			return false;  		
    	}
	}

    public boolean canSelect(int x, int y) {
    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
    		return false;
    	}
    	if (fireturn) {
    		if (!inHand) {
    			return (this.pieceAt(x, y) != null && this.pieceAt(x, y).isFire());
			}
			else if (!hasMoved) {
				return (this.pieceAt(x, y) != null && this.pieceAt(x, y).isFire()) || validMove(selectedx, selectedy, x,y);
			}
			else if (hasCaptured) {
				return false;
			}
			else {
				return false;
			}		
    	}
    	else {
    		if (!inHand) {
    			return (this.pieceAt(x, y) != null && !this.pieceAt(x, y).isFire());
			}
			else if (!hasMoved) {
				return (this.pieceAt(x, y) != null && !this.pieceAt(x, y).isFire()) || validMove(selectedx, selectedy, x,y);
			}
			else if (hasCaptured) {
				return false;
			}
			else {
				return false;
			}		
    	}
	}

	public Piece remove(int x, int y) {
        if (board == null) {
            return null;
        }
		if ((x > 7) || (y > 7)) {
			System.out.println("Removed piece is out of bounds!");
			return null;
		}
		if (this.pieceAt(x, y) == null) {
			System.out.println("Piece does not exist!");
			return null;
		}
        else {
            removed = board[x][y];
            board[x][y] = null;
            return removed;           
        }
	}
	
    public void select(int x, int y) {
    	if (!inHand) {
            if (this.pieceAt(x, y) == null) {
                return;
            }
            if (this.pieceAt(x, y) != null) {
            selected = this.pieceAt(x, y);
            selectedx = x;
            selectedy = y;
            inHand = true;              
            }
        }
    	else if (board[x][y] != null) {
    		selected = this.pieceAt(x, y);
    		return;
    	} 
    	else {
            selected.move(x,y);
  			hasMoved = true;
    	}
	}

    public boolean canEndTurn() {
    	return hasMoved || hasCaptured;
    }

    public void endTurn() {
    	removed = null;
    	hasMoved = false;
    	hasCaptured = false;
    	inHand = false;
    	if (fireturn) {
    		fireturn = false;
    	}
    	else {
    		fireturn = true;
    	}
    	winner();
    }

    private void drawBoard(int N) {
    	StdDrawPlus.clear(); 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
       	for (int x = 0; x < 8; x++) {
    		for (int y = 0; y < 8; y++) {
    			if (board[x][y] != null) {
    				if (board[x][y].isFire()) {
    					if (board[x][y].isShield()) {
    	                    if (board[x][y].isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);    
                            }
                            else {
                            StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                            }
                        }
    					else if (board[x][y].isBomb()) {
                            if (board[x][y].isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);    
                            }
                            else {
    						StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                            }
    					}
                        else {
                            if (board[x][y].isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);    
                            }
                            else {
                            StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }

    				}
    				else {
                        if (board[x][y].isShield()) {
                            if (board[x][y].isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);    
                            }
                            else {
                            StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else if (board[x][y].isBomb()) {
                            if (board[x][y].isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);    
                            }
                            else {
                            StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else {
                            if (board[x][y].isKing()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);    
                            }
                            else {
                            StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                            }
                        } 					
                    }
				}	
    		}
		}
	}

	public String winner() {
		boolean firewin = false;
        int emptyPieces = 0;
        if (board == null) {
            return null;
        }
        search:
    		for (int x = 0; x < 8; x ++) {
    			for (int y = 0; y < 8; y ++) {
    				if (board[x][y] == null) {
                        emptyPieces+= 1;
                        continue;
    				}
    				else if (board[x][y].isFire()) {
    					firewin = true;
    					break search; 
    				}
    				else {
    					firewin = false;
    					break search;
    				}
    			}
	        }
        if (emptyPieces == 64) {
            System.out.println("Tie");
            return "No one";
        }
		if (firewin) {
			for (int x = 0; x < 8; x ++) {
				for (int y = 0; y < 8; y ++) {
					if (board[x][y] == null) {
						continue;
					}			
					if (!(board[x][y].isFire())) {
						return null;
					}
				}
			}
            return "Fire";
		}
		else {
			for (int x = 0; x < 8; x ++) {
				for (int y = 0; y < 8; y ++) {
					if (board[x][y] == null) {
						continue;
					}			
					if (board[x][y].isFire()) {                                            
						return null;
					}
				}
			}
			return "Water";
		}





	}
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board startboard = new Board(false);
        startboard.drawBoard(8);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xcoord = (int)x;
                int ycoord = (int)y;
                if (startboard.canSelect(xcoord, ycoord)) {
                	startboard.select(xcoord, ycoord);
                	startboard.drawBoard(8);
                }
            } 
            if (StdDrawPlus.isSpacePressed()) {
            	if (startboard.canEndTurn()) {
            		startboard.endTurn();
            	}
            if (startboard.winner() != null) {
                System.out.println(startboard.winner());
                return;
            }
            }       
            StdDrawPlus.show(100);
        }
    }
}