
public class Board {
	private static Piece[][] pieces;
	private static int N = 8;
	boolean shouldBeEmpty;
	private Piece selected;
	private boolean hasMoved; 
	private int turn;
	private int prevX;
	private int prevY;
	
	public Board(boolean shouldBeEmpty) {
		shouldBeEmpty = false;
		hasMoved = false;
		turn = 0;
		pieces = new Piece[N][N];
		if (shouldBeEmpty == false) {
            makePieces(pieces);
		}
		else {
			drawBoard(N);
		}
	}
	
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }
    
    private void drawPieces(Piece[][] p) {
    	String element = "";
    	String type = "";
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (p[i][j] != null) {
            		if (p[i][j].isKing() != true) {
            			if (p[i][j].isFire() == true) {
    	            		element = "fire";
    	            	}
    	            	if (p[i][j].isFire() == false) {
    	            		element = "water";
    	            	}
    	            	if (p[i][j].isBomb() == true) {
    	            		type = "bomb";
    	            	}
    	            	if (p[i][j].isShield() == true) {
    	            		type = "shield";
    	            	}
    	            	if (p[i][j].isBomb() == false && p[i][j].isShield() == false) {
    	            		type = "pawn";
    	            	}
    	            	StdDrawPlus.picture(i+.5, j+.5, "img/" + type + "-" + element + ".png", 1, 1);
            		}
            		else {
            			if (p[i][j].isFire() == true) {
    	            		element = "fire";
    	            	}
    	            	if (p[i][j].isFire() == false) {
    	            		element = "water";
    	            	}
    	            	if (p[i][j].isBomb() == true) {
    	            		type = "bomb";
    	            	}
    	            	if (p[i][j].isShield() == true) {
    	            		type = "shield";
    	            	}
    	            	StdDrawPlus.picture(i+.5, j+.5, "img/" + type + "-" + element + "-crowned.png", 1, 1);
            		}
	            	
	            	
            	}
            }
    	}
    }
    
    private void makePieces(Piece[][] p) {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                  if (j == 0 && i % 2 == 0) {
                        p[i][j] = new Piece(true, this, i, j, "pawn");
                  }
                  if (j == 1 && i % 2 != 0) {
                        p[i][j] = new Piece(true, this, i, j, "shield");
                  }
                  if (j == 2 && i % 2 == 0) {
                        p[i][j] = new Piece(true, this, i, j, "bomb");
                  }
                  if (j == 7 && i % 2 != 0) {
                        p[i][j] = new Piece(false, this, i, j, "pawn");
                  }
                  if (j == 6 && i % 2 == 0) {
                        p[i][j] = new Piece(false, this, i, j, "shield");
                  }
                  if (j == 5 && i % 2 != 0) {
                        p[i][j] = new Piece(false, this, i, j, "bomb");
                  }
            }
    	}
    }
    
    public Piece pieceAt(int x, int y) {
    	if (pieces[x][y] == null || x > N || y > N) {
    		return null;
    	}
    	else {
    		return pieces[x][y];
    	}
    }
    
    
	public boolean canSelect(int x, int y) {
		if (x >= N || y >= N || x < 0 || y < 0) {
			return false;
		}
		Piece p = pieces[x][y];
		if (hasMoved == false && pieceAt(x, y) != null) {
			if (pieceAt(x, y).side() == turn) {
				return true;
			}
		}
		if (!hasMoved && p == null && selected != null && validMove(prevX, prevY, x, y)) {
			return true;
		}		
		if (hasMoved == false) {
			if (selected != null) {
				if (validMove(prevX, prevY, x, y) == true) {
					return true;
				}
				if (validCapture(prevX, prevY, x, y) == true) {
					return true;
				}
			}
		}
		
		if (p != null && p.hasCaptured() != true) {
			if (validCapture(prevX, prevY, x, y) == true) {
				return true;
			}
		}
		return false;
	}	
	
	
	public void select(int x, int y) {
		  prevX = x;
		  prevY = y;
		  if (pieces[x][y] != null) {
			  selected = pieces[x][y];
		  }
		  else {
			  if (selected != null) {
				  selected.move(x, y);
				  hasMoved = true;
			  }
		  }
	  }
	  
	private boolean validCapture(int xi, int yi, int xf, int yf) {
	  int dx = Math.abs(xi-xf);
	  int dy = Math.abs(yi-yf);
	  
	  Piece captured = pieces[(xi+xf)/2][(yi+yf)/2];

	  	if (dx != 2 && dy != 2) {
	  		return false;
	  	}
	    if (captured == null) {
	      return false;
	    }
	    if (selected.isFire() && !captured.isFire() && yf > yi && !selected.isKing()) {
	      return true;
	    }
	    if (!selected.isFire() && captured.isFire() && yf < yi && !selected.isKing()) {
	      return true;
	    }
	    if (selected.isKing() && (selected.isFire() != captured.isFire())) {
	      return true;
	    }
	    
	 return false;
  }
    	
  	private boolean validMove(int xi, int yi, int xf, int yf) {
    int changeX = Math.abs(xi - xf);
    int changeY = Math.abs(yi - yf);
   
    if (pieces[xf][yf] != null) {
    	return false;
    }  
    if (changeX != 1 && changeY != 1) {
    	return false;
    }    
    if (selected.side() == 0 && yf > yi && selected.isKing() != true) {
    	return true;
    }    
    if (selected.side() == 1 && yf < yi && selected.isKing() != true) {
    	return true;
    }    
    if (selected.isKing()) {
    	return true;
    }        
    return false;
  }
  
    public void place(Piece p, int x, int y) {
        if (p == null) {
              return;
        }
        else if (x > N || y > N) {
              return;
        }
        else {
              pieces[x][y] = p;
        }
      }
    
    public Piece remove(int x, int y) {
        Piece removed = pieces[x][y];
        pieces[x][y] = null;
        return removed;
      }
    
    public boolean canEndTurn() {
    	return hasMoved;
    }
    
    public void endTurn() {
    	selected.doneCapturing();
    	selected = null;
    	hasMoved = false;
    	prevX = -1;
    	prevY = -1;
    	
    	if (turn == 0) {
    		turn = 1;
    	}
    	else {
    		turn = 0;
    	}
    }
    
    private void play() {
    	if (StdDrawPlus.mousePressed()) {
    		double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            int xM = (int) x;
            int yM = (int) y;
            
            if (canSelect(xM, yM)) {
            	select(xM, yM);
            }
    	}
    	
    	if (StdDrawPlus.isSpacePressed()) {
    		if (canEndTurn()) {
    			endTurn();
    		}
    	}
    }
    
    public String winner() {
    	int fire = 0;
    	int water = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		fire += 1;
                	}
                	if (!pieces[i][j].isFire()) {
                		water += 1;
                	}
            	}
            }
        }
        
        if (fire == 0 && water == 0) {
        	return "No one.";
        }
        
        else if (fire == 0) {
        	return "Water";
        }
        else if (water == 0) {
        	return "Fire";
        }
        return null;
    }

    
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
        	b.drawBoard(N); 
            b.drawPieces(pieces);
            StdDrawPlus.show(100);
            b.play();
           }
    }
}
