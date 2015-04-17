public class Board {
	private boolean hasSelected = false;
	private Piece selectedPiece;
	private boolean hasAlreadyMoved;
	private Piece[][] pieces = new Piece [8][8];
	private boolean shouldBeEmpty;
    private boolean drawnCheck = false;
	private int selectedX;
	private int selectedY;
	public boolean fireTurn = true;

	public Board (boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
	}

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        b.setPieces();
        while(b.winner() == null) {
            b.drawBoard(N);
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
            StdDrawPlus.show(10);
        }
    }

	private void setPieces() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0 && i % 2 == 0) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    pieces [i][j] = new Piece (true, this, i, j, "pawn");
                }
                if (j == 1 && i % 2 == 1) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    pieces [i][j] = new Piece (true, this, i, j, "shield");
                }
                if (j == 2 && i % 2 == 0) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    pieces [i][j] = new Piece (true, this, i, j, "bomb");
                }
                if (j == 5 && i % 2 == 1) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    pieces [i][j] = new Piece (false, this, i, j, "bomb");
                }
                if (j == 6 && i % 2 == 0) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    pieces [i][j] = new Piece (false, this, i, j, "shield");
                }
                if (j == 7 && i % 2 == 1) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    pieces [i][j] = new Piece (false, this, i, j, "pawn");
                }
            }
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (hasSelected && selectedX == i && selectedY == j) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
                }                 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (!shouldBeEmpty){
                	if (pieces [i][j] != null) {
                		draw(pieces[i][j], i, j);
                	}
            	}
            }
        }
    }

    private void draw(Piece p, int i, int j) {
    	if (p.isFire()) {
    		if (p.isBomb() && !p.isKing()) {
    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	    	}
	    	if (!p.isBomb() && !p.isShield() && !p.isKing()) {
	    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	    	}
	    	if (p.isShield() && !p.isKing()) {
	    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	    	}
            if (p.isBomb() && p.isKing()) {
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            }
            if (!p.isBomb() && !p.isShield() && p.isKing()) {
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            }
            if (p.isShield() && p.isKing()) {
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            }
    	}
    	else {
    		if (p.isBomb() && !p.isKing()) {
    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	    	}
	    	if (!p.isBomb() && !p.isShield() && !p.isKing()) {
	    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	    	}
	    	if (p.isShield() && !p.isKing()) {
	    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	    	}
            if (p.isBomb() && p.isKing()) {
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            }
            if (!p.isBomb() && !p.isShield() && p.isKing()) {
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            }
            if (p.isShield() && p.isKing()) {
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            }
    	}

   
    }

    private boolean outOfBounds(int x, int y) {
    	if (x > 7 || x < 0 || y > 7 || y < 0) {
    		return true;
    	}
    	return false;
    }

    public Piece pieceAt(int x, int y) {
    	if (outOfBounds(x,y)) {
    		return null;
    	}
    	if (pieces[x][y] != null){
    		return pieces[x][y];
    	}
    	return null;
    }

    public boolean canSelect(int x, int y) {
        if(outOfBounds(x, y)) {
            return false;
        }
    	if (pieces[x][y] != null) {
	    	if (fireTurn) {
		    	if(pieces[x][y].isFire()) {
		    		return checkSelect(x,y);
		    	}
		    	return false;
			}
			else if (!fireTurn) {
				if(!(pieces[x][y].isFire())) {
					return checkSelect(x,y);
				}
				return false;
			}
		}
		if (pieces[x][y] == null){
			return checkSelect(x, y);
		}
    	return false;
    }

    private boolean checkSelect(int x, int y) {
    	if (pieces[x][y] != null) { 
	    		if (!hasSelected) {
	    			return true;
	    		}
	    		if (hasSelected && !hasAlreadyMoved) {
	    			return true;
	    		}
	    	}
    	else {
    		if (fireTurn && selectedY > y && hasSelected && !selectedPiece.isKing()) {
    				return false;
    			}
            if (!fireTurn && selectedY < y && hasSelected && !selectedPiece.isKing()) {
                return false;

            }
            if (fireTurn && selectedY > y && hasSelected && selectedPiece.isKing()) {
                    if(!hasAlreadyMoved && validMove(selectedX, selectedY, x,y)) {
                        return true;
                    }
                    if (hasAlreadyMoved && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x,y)) {
                        return true;
                    }
                }

            if (!fireTurn && selectedY < y && hasSelected && selectedPiece.isKing()) {
                if (!hasAlreadyMoved && validMove(selectedX, selectedY, x,y)) {
                    return true;
                }
                if (hasAlreadyMoved && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x,y)) {
                    return true;
                }
            }
    		if (hasSelected && !hasAlreadyMoved && validMove(selectedX, selectedY, x,y)) {
    			return true;
    		}
    		if (hasSelected && hasAlreadyMoved && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x,y)) {
    			return true;
    		}
    	}
	    return false;
    }

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (outOfBounds(xf, yf)) {
			return false;
		}
        if (xi == xf && yi == yf) {
            return false;
        }
		if (xi == xf - 1 && yi == yf - 1 && (pieceAt(xf,yf) == null)) {
			return true;
		}
		if (xi == xf + 1 && yi == yf + 1 && (pieceAt(xf,yf) == null)) {
			return true;
		}
		if (xi == xf - 1 && yi == yf + 1 && (pieceAt(xf,yf) == null)) {
			return true;
		}
		if (xi == xf + 1 && yi == yf - 1 && (pieceAt(xf,yf) == null)) {
			return true;
		}

		if (xi == xf + 2 && yi == yf + 2 && (pieceAt(xf+1,yf+1) != null)) {
			return true;
		}
		if (xi == xf + 2 && yi == yf - 2 && (pieceAt(xf+1,yf-1) != null)) {
			return true;
		}
		if (xi == xf - 2 && yi == yf + 2 && (pieceAt(xf-1,yf+1) != null)) {
			return true;
		}
		if (xi == xf - 2 && yi == yf - 2 && (pieceAt(xf-1,yf-1) != null)) {
			return true;
		}

		return false;
	}

	public void select (int x, int y) {
		if (!hasSelected && pieces[x][y] != null) {
			hasSelected = true;
			selectedPiece = pieces[x][y];
			selectedX = x;
			selectedY = y;
            System.out.print(selectedX);
            System.out.print(selectedY);
            System.out.print(" selected");
            //System.out.print(" | " + hasSelected);	
            System.out.print(" | x = " + x + " | ");
            System.out.println("y = " + y + " ");	
        }
        else if (hasSelected && pieces[x][y] != null && 
                ((selectedPiece.isFire() && pieces[x][y].isFire()) ||
                (!selectedPiece.isFire() && !pieces[x][y].isFire()))) {
                        //hasSelected = true;
                        selectedPiece = pieces[x][y];
                        selectedX = x;
                        selectedY = y;
                        //System.out.println(2); 
        }                               
        else if (hasSelected && pieces[x][y] == null && selectedPiece != null && selectedX != x && selectedY != y) { 
            selectedX = x;
            selectedY = y;
            selectedPiece.move(x,y);
        	hasAlreadyMoved = true;
            System.out.print(selectedX);
            System.out.print(selectedY);   
            System.out.print(" moved");
            System.out.print(" | x = " + x + " | ");
            System.out.println("y = " + y + " ");
            System.out.println("-------------------------------");

            
        }
	}

	public void place(Piece p, int x, int y) {
		if (!(p == null || outOfBounds(x,y))) {
			if(exists(p)) {
				remove(existsX(p),existsY(p));
			}
			if (pieces[x][y] != null) {
				remove(x,y);
			}
			pieces[x][y] = p;
		}
	}

	private boolean exists (Piece p) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p) {
            		return true;
            	}
            }
        }
        return false;
    }

    private int existsX (Piece p) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p) {
            		return i;
            	}
            }
        }
        return 0;
    }

    private int existsY (Piece p) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p) {
            		return j;
            	}
            }
        }
        return 0;
    }

	public Piece remove(int x, int y) {
		if (outOfBounds(x,y)) {
			System.out.println("Error: Coordinates out of bounds");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("Error: Coordinates do not contain a piece");
			return null;
		}
        Piece el = pieceAt(x,y);
        pieces[x][y] = null;
        return el;
		
	}

	public boolean canEndTurn() {
        if (hasAlreadyMoved){
            return true;
        }
		return false;
	}

	public void endTurn() {
		if (canEndTurn()) {
			fireTurn = !fireTurn;
            hasAlreadyMoved = false;
            hasSelected = false;
            selectedPiece.doneCapturing();
            selectedPiece = null;
            selectedX = -1;
            selectedY = -1;
		} 
	}

	public String winner() {
        boolean fire = false;
        boolean water = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        fire = true;
                    }
                    if (!pieces[i][j].isFire()) {
                        water = true;
                    }
                }
            }
        }
        if (water && fire) {
            return null;
        }
		if (water && !fire) {
            return "Water";
        }
        if(!water && fire) {
            return "Fire";
        }
        if (!water && !fire) {
            return "No one";
        }
        return null;

	}

}