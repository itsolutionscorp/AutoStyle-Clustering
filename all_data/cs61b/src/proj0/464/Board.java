public class Board {

	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private boolean fireTurn;
	private Piece selected;
    private int xSelected;
    private int ySelected;
	private boolean hasMoved;
    private String winner;
    private boolean hasCaptured;


    public static void main(String[] args) {
    	Board b = new Board(false);
    	b.fireTurn = true;
    	b.hasMoved = false;
        b.winner = null;
    	int N = 8;
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        if (b.shouldBeEmpty == false) {
        	b.addPieces(N);
        }
    	while(b.winner == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double xd = StdDrawPlus.mouseX();
                double yd = StdDrawPlus.mouseY();
                int x = (int)xd;
       	        int y = (int)yd;
       	        if (b.canSelect(x, y)) {
       	        	b.select(x, y);
       	        }
            }            
            StdDrawPlus.show(25);
            if (StdDrawPlus.isSpacePressed()) {
             	if (b.canEndTurn()) {
             		b.endTurn();
             	}   
            }
            StdDrawPlus.show(25);
        }

    }

    private void addPieces(int N) {	
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((j == 0) && ((i % 2) == 0)) {
					pieces[i][j] = new Piece(true, this, i, j, "pawn");
				}
				else if ((j == 7) && ((i % 2) != 0)) {
					pieces[i][j] = new Piece(false, this, i, j, "pawn");
				}
				else if ((j == 1) && ((i % 2) != 0)) {
					pieces[i][j] = new Piece(true, this, i, j, "shield");
				}
				else if ((j == 6) && ((i % 2) == 0)) {
					pieces[i][j] = new Piece(false, this, i, j, "shield");
				}
				else if ((j == 2) && ((i % 2) == 0)) {
					pieces[i][j] = new Piece(true, this, i, j, "bomb");
				}
				else if ((j == 5) && ((i % 2) != 0)) {
					pieces[i][j] = new Piece(false, this, i, j, "bomb");
				}
		}
	}
	}

    public Board(boolean shouldBeEmpty) {
    	this.shouldBeEmpty = shouldBeEmpty;
        this.pieces = new Piece[8][8];
  		
    }

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
            return null;
        }
    	if (pieces[x][y] == null) {
    		return null;
    	}
    	else {
    		return pieces[x][y];
    	}
    }

    public Piece remove(int x, int y) {
    	if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
    		System.out.println("Input out of bounds");
    		return null;
    	}
    	else if (pieces[x][y] == null) {
    		System.out.println("No piece to remove");
    		return null;
    	}
    	else {
    		Piece result = pieces[x][y];
    		pieces[x][y] = null;
    		return result;
		}
    }

    private boolean hasCaptured(Piece p) {
        if (p.hasCaptured()) {
            hasCaptured = true;
            return true;
        }
        else {
            return false;
        }
    }

    private int xGet(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].equals(p)) {
                        return i;
                    }
                }
            }
        }
    return 0;
    }

     private int yGet(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].equals(p)) {
                        return j;
                    }
                }
            }
        }
        return 0;
    }

    public boolean canSelect(int x, int y) {
        if (selected != null && selected.hasCaptured()) {
            xSelected = xGet(selected);
            ySelected = yGet(selected);
            if (validCapture(x, y)) {
                return true;
            }
        }
    	if (pieceAt(x, y) != null) {
    		if (selected == null || hasMoved == false) {
    			if (fireTurn && pieceAt(x, y).isFire()) {
                	return true;
            }
    			if (fireTurn == false && pieceAt(x, y).isFire() == false) {
                	return true;
            	}
        	}
    	}
    	else {
    		if (selected != null) {
    			if (validMove(x, y)) {
                    return true;
                } 
    		}
    	}
    	return false;
    }

    public void select(int x, int y) {
    	if (selected == null || (selected != null && hasMoved == false && pieceAt(x, y) != null)) {
    	selected = pieces[x][y];
        xSelected = x;
        ySelected = y;
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    	}
    	if (selected != null && pieceAt(x, y) == null) {
    		selected.move(x, y);
    		hasMoved = true;
    	}	
    }

    private boolean validMove(int x, int y) {
        if (selected.hasCaptured()) {
            validCapture(x, y);
        }
        if (hasMoved == false) {
            if (selected.isKing()) {
                    if (validKingMove(x, y)) {
                        return true;
                    }
                    if (validCapture(x, y)) {
                        return true;
                    }
                }
            if (selected.isFire()) {
                    if (validFireMove(x, y)) {
                        return true;
                    }
                    if (validCapture(x, y) && pieceAt(((xSelected + x) / 2), ((ySelected + y) / 2)).isFire() == false) {
                        return true;
                    }
                }
            if (selected.isFire() == false) { 
                    if (validWaterMove(x, y)) {
                        return true;
                    }
                    if (validCapture(x, y) && pieceAt(((xSelected + x) / 2), ((ySelected + y) / 2)).isFire() == true) {
                        return true;
                    }
                }
        }
        return false;
    }

    private boolean validCapture(int x, int y) {
    	if ((x == xSelected + 2 && y == ySelected + 2) || (x == xSelected - 2 && y == ySelected + 2) || (x == xSelected - 2 && y == ySelected - 2) || (x == xSelected + 2 && y == ySelected - 2)) {
    		int midX = (xSelected + x) / 2;
    		int midY = (ySelected + y) / 2;
    		if (pieceAt(midX, midY) != null) {
    			return true;
    		}
    	}
    return false;
    }

     private boolean validKingMove(int x, int y) {
        if ((x == xSelected + 1 && y == ySelected + 1) || (x == xSelected - 1 && y == ySelected + 1) || (x == xSelected - 1 && y == ySelected - 1) || (x == xSelected + 1 && y == ySelected - 1)) {
        return true;
    }
    return false;
}

    private boolean validFireMove(int x, int y) {
        if ((x == xSelected + 1 && y == ySelected + 1) || (x == xSelected - 1 && y == ySelected + 1)) {
            return true;
        }
    return false;
    }

    private boolean validWaterMove(int x, int y) {
        if ((x == xSelected - 1 && y == ySelected - 1) || (x == xSelected + 1 && y == ySelected - 1)) {
            return true;
        }
    return false;
    }

    public void place(Piece p, int x, int y) {
        	if ((x > 7) || (x < 0) || (y > 7) || (y < 0) || (p == null)) {
        		return;
        	}
            if (pieces[x][y] != null) {
                remove(x, y);
            }
        	StdDrawPlus.picture(x + .5, y + .5, imgFile(p), 1, 1);
            pieces[x][y] = p;
        }

    private String imgFile(Piece p) {
    	if (p.isKing() == true) {
    		return "img/" + getType(p) + "-" + getPlayer(p) + "-" + "crowned.png";
    	}
    	else {
    		return "img/" + getType(p) + "-" + getPlayer(p) + ".png";
    	}
    }

     private String getPlayer(Piece p) {
  		if (p.isFire() == true) {
  			return "fire";
  		}
  		else {
  			return "water";
  		}
    }

    private String getType(Piece p) {
    	if (p.isShield() == true) {
    		return "shield";
    	}
    	else if (p.isBomb() == true) {
    		return "bomb";
    	}
    	else {
    		return "pawn";
    	}
    }

    public boolean canEndTurn() {
		if (hasMoved == true) {
			return true;
            }
		return false;
	}

	public void endTurn() {
        winner();
		hasMoved = false;
		selected = null;
        hasCaptured = false;
		if (fireTurn == true) {
			fireTurn = false;
		}
		else {
			fireTurn = true;
		}
	}

	public String winner() {
		int firecount = 0;
		int watercount =0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieceAt(i, j) != null) {
            		if (pieceAt(i, j).isFire()) {
            			firecount += 1;
            		}
                }
            		else {
            			watercount += 1;
            		}
            }
        }
        if ((firecount > 0) && (watercount == 0)) {
            winner = "fire";
        	return "Fire";
        }
        else if ((watercount > 0) && (firecount == 0)) {
            winner = "water";
        	return "Water";
        }
        else if ((watercount == 0) && (firecount == 0)) {
            winner = "No one";
        	return "No one";
        }
        else {
        	return null;
        }
        }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    if (selected != null && pieces[i][j] == selected) {
                	   StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                    else {

                       StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    }
                }
                else {                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                	place(pieces[i][j], i, j);
                }
                
            }
            }
        }
    }
