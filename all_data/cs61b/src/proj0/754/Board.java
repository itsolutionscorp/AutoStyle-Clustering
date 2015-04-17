public class Board {
	private Piece[][] pieceObjects;
	private boolean hasMoved;
	private boolean waterTurn;
	private Piece currSelect;
	private int currSelectX;
	private int currSelectY;
	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawPieces(i, j);
            }
        }
    }
    private void drawPieces(int i, int j) {
    	String img = "img/";
    	Piece drawPiece = pieceAt(i, j);	   
    	if (drawPiece != null) { 
    		if (drawPiece.isShield()) {
    			img += "shield-";
    		}
    		else if (drawPiece.isBomb()) {
    			img += "bomb-";
    		}
    		else {
    			img += "pawn-";
    		}
    		if (drawPiece.isFire()) {
    			img += "fire";
    		}
    		else {
    			img += "water";
    		}
    		if (drawPiece.isKing()) {
    			img += "-crowned";
    		}
    		img += ".png";
    		StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
    	}
    }
    private void drawSelected() {
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	if (currSelect != null) {
        	StdDrawPlus.filledSquare(currSelectX + .5, currSelectY + .5, .5);
        }
        drawPieces(currSelectX, currSelectY);
    }
        
    
    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        b.drawBoard();
        while(true) {
        	if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            	b.drawBoard();
            }                  
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x,(int) y); 
                	b.drawBoard();       	
                	b.drawSelected();
            	}
            }    
            StdDrawPlus.show(100);
            if (b.winner() != null) {
        		System.out.println(b.winner() + " is the winner.");
        		break;
        	}
        }
    }
	public Board(boolean shouldBeEmpty) {
		pieceObjects = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
					if (i % 2 == 0 && j == 0) {
			            pieceObjects[i][j] = new Piece(true, this, i, j, "pawn");
			        }
			    	else if (i % 2 != 0 && j == 1) {
			    		pieceObjects[i][j] = new Piece(true, this, i, j, "shield");
			    	}
			    	else if (i % 2 == 0 && j == 2) {
			    		pieceObjects[i][j] = new Piece(true, this, i, j, "bomb");
			    	}
			    	else if (i % 2 != 0 && j == 7) {
			    		pieceObjects[i][j] = new Piece(false, this, i, j, "pawn");
			    	}
			    	else if (i % 2 == 0 && j == 6) {
			    		pieceObjects[i][j] = new Piece(false, this, i, j, "shield");
			    	}
			    	else if (i % 2 != 0 && j == 5) {
			    		pieceObjects[i][j] = new Piece(false, this, i, j, "bomb");
			    	}
			    }
		    }
		}
	}
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null; // piece is out of bounds
		}
		else if (pieceObjects[x][y] == null) {
			return null; // no piece
		}
		else {
			return pieceObjects[x][y];
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean king = pieceAt(xi, yi).isKing();
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		else if (waterTurn) {
			if ((xi + 1 == xf || xi - 1 == xf) && yi - 1 == yf && !pieceAt(xi, yi).hasCaptured()) {
				return true;
			}	
			else if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() && xi + 2 == xf && yi - 2 == yf) {
				return true;
			}
			else if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() && xi - 2 == xf && yi - 2 == yf) {
				return true;
			}
			else if (king && (xi + 1 == xf || xi - 1 == xf) && yi + 1 == yf && !pieceAt(xi, yi).hasCaptured()) {
				return true;
			}	
			else if (king && pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() && xi + 2 == xf && yi + 2 == yf) {
				return true;
			}
			else if (king && pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() && xi - 2 == xf && yi + 2 == yf) {
				return true;
			}		
		}
		else if (!waterTurn) {
			if ((xi + 1 == xf || xi - 1 == xf) && yi + 1 == yf && !pieceAt(xi, yi).hasCaptured()) {
				return true;
			}	
			else if (pieceAt(xi + 1, yi + 1) != null && !pieceAt(xi + 1, yi + 1).isFire() && xi + 2 == xf && yi + 2 == yf) {
				return true;
			}
			else if (pieceAt(xi - 1, yi + 1) != null && !pieceAt(xi - 1, yi + 1).isFire() && xi - 2 == xf && yi + 2 == yf) {
				return true;
			}
			else if (king && (xi + 1 == xf || xi - 1 == xf) && yi - 1 == yf && !pieceAt(xi, yi).hasCaptured()) {
				return true;
			}
			else if (pieceAt(xi + 1, yi - 1) != null && !pieceAt(xi + 1, yi - 1).isFire() && xi + 2 == xf && yi - 2 == yf) {
				return true;
			}
			else if (pieceAt(xi - 1, yi - 1) != null && !pieceAt(xi - 1, yi - 1).isFire() && xi - 2 == xf && yi - 2 == yf) {
				return true;
			}	
		}
		return false;
	}
	public boolean canSelect(int x, int y) {
		Piece selected = pieceAt(x, y);
		boolean correctPlayer = (selected != null && waterTurn != selected.isFire());
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		else if (currSelect == null && hasMoved) {	
            return false;
		}
		else if (currSelect != null && !hasMoved && correctPlayer) {
			return true;
		}
		else if (currSelect == null && correctPlayer && !hasMoved) {
			return true;
		}
		else if (selected == null && currSelect != null && !hasMoved && validMove(currSelectX, currSelectY, x, y)) {
			return true;
		}
		else if (selected == null && currSelect != null && currSelect.hasCaptured() && validMove(currSelectX, currSelectY, x, y) && (currSelectY + 2 == y || currSelectY - 2 == y)) {
			return true;
		}
		return false;	
	}
	public void select(int x, int y) {
		if (pieceAt(x, y) == null)
			currSelect.move(x, y);
		currSelect = pieceAt(x, y);
		currSelectX = x;
		currSelectY = y;
	}
	public void place(Piece p, int x, int y) {
		boolean existing = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (p == pieceAt(i, j) && i != x && j != y) {
					pieceObjects[i][j] = null;
					existing = true;
				}
			}
		}
		if (p != null && x <= 7 && x >= 0 && y <= 7 && y >= 0 && pieceAt(x, y) != p) {
			pieceObjects[x][y] = p;
			if (existing) { 
				hasMoved = true;
			}
		}
	}
	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			System.out.println("Out of bounds");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("No piece");
			return null;
		}
		else {
			Piece toReturn = pieceAt(x, y);
			pieceObjects[x][y] = null;
			return toReturn;
		}
	}
	public boolean canEndTurn() {	
		if (hasMoved) {
			return true;
		}
		return false;
	}
	public void endTurn() {
		if (currSelect != null) {
			currSelect.doneCapturing();
		}
		currSelect = null;
		currSelectX = 8;
		currSelectY = 8;
		waterTurn = !waterTurn;
		hasMoved = false;

		
	}
	public String winner() {
		int reds = 0;
		int blues = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire())
						reds += 1;
					else {
						blues += 1;
					}
				}
			}
		}
		if (reds == 0 && blues == 0) {
			return "No one";
		}
		else if (reds == 0) {
			return "Water";
		}
		else if (blues == 0) {
			return "Fire";
		}
		return null;
	}
}