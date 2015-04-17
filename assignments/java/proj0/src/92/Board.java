// Sony Theakanath
// CS 61B - Data Structures

public class Board {
	private static Piece[][] pieces;
	private final int BOARD_SIZE = 8;
	private boolean isFireTurn = true;
	private boolean hasMoved = false;
	private boolean multiCapture = false;
	private static Piece selectedPiece = null;
	
	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
       	Board b = new Board(false);
        while(b.winner() == null) {
        	drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if(b.canSelect(x, y)) 
                	b.select(x, y);
            }

            if(b.hasMoved && StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn())
            		b.endTurn();
            }
            StdDrawPlus.show(100);
        }
	}

	private static void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (selectedPiece == pieces[i][j]) {
                		 StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		 StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                    StdDrawPlus.picture(i + .5, j + .5, getName(pieces[i][j]), 1, 1);
                }
            }
        }
    }
	
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int i = 0; i < BOARD_SIZE; i++) {
	            for (int j = 0; j < BOARD_SIZE; j++) {
	                if ((i + j) % 2 == 0) {
	            		if (j == 7)
	            			pieces[i][j] = new Piece(false, this, i, j, "pawn");
	            		else if (j == 6)
	            			pieces[i][j] = new Piece(false, this, i, j, "shield");
	            		else if (j == 5)
	            			pieces[i][j] = new Piece(false, this, i, j, "bomb");
	            		else if (j == 2)
	            			pieces[i][j] = new Piece(true, this, i, j, "bomb");
	            		else if (j == 1)
	            			pieces[i][j] = new Piece(true, this, i, j, "shield");
	            		else if (j == 0)
	            			pieces[i][j] = new Piece(true, this, i, j, "pawn");
	         			else
	         				pieces[i][j] = null;
	                }
	            }
			}
		} else {
			for (int i = 0; i < BOARD_SIZE; i++) {
	            for (int j = 0; j < BOARD_SIZE; j++) {
	            	pieces[i][j] = null;
	            }
	        }
		}
	}

	public Piece pieceAt(int x, int y) {
		if (this.notOutBounds(x, y))
			return pieces[x][y];
		return null;
	}

	public boolean canSelect(int x, int y) {
		Piece temp = pieceAt(x, y);
		if (selectedPiece == null || !hasMoved) {
			if (temp != null && !hasMoved && (temp.isFire() == isFireTurn)) {
				return true;
			} else {
				if (selectedPiece != null && (selectedPiece.isFire() == isFireTurn) && !hasMoved && validMove(selectedPiece, x, y))
					return true;
				else if (selectedPiece != null  && (selectedPiece.isFire() == isFireTurn) && selectedPiece.hasCaptured() && validMove(selectedPiece, x, y))
					return true;
				return false;
			}
		} else if (selectedPiece != null && multiCapture) {
				if (selectedPiece != null && (selectedPiece.isFire() == isFireTurn) && validMove(selectedPiece, x, y))
					return true;
				return false;
		} else {
			return false;
		}
	}

	private boolean validMove(Piece p, int xf, int yf) {
		if (xf < BOARD_SIZE && yf < BOARD_SIZE && xf >= 0 && yf >= 0) {
			int xi = pieceLocation(p)[0];
			int yi = pieceLocation(p)[1];
			int dx = xf - xi;
			int dy = yf - yi;
			if (abs(dy) == 1 && abs(dx) == 1 && !multiCapture) {
				if (p.isKing())
					return (dx == 1 && dy == 1) || (dx == -1 && dy == -1) || (dx == -1 && dy == 1) || (dx == 1 && dy == -1);
				else if (isFireTurn)
					return (dx == 1 && dy == 1) || (dx == -1 && dy == 1);
				return (dx == -1 && dy == -1) || (dx == 1 && dy == -1);
			} else {
				//Handing double jump
				int dirX = (dx > 0) ? 1 : -1;
				int dirY = (dy > 0) ? 1 : -1;
				Piece middle = pieceAt(xi + dirX, yi + dirY);
				if (middle != null && p.isFire() != middle.isFire() && pieceAt(xf, yf) == null) {
					boolean works;
					if (p.isKing())
						return (dx == 2 && dy == 2) || (dx == -2 && dy == -2) || (dx == -2 && dy == 2) || (dx == 2 && dy == -2);
					else if (isFireTurn)
						return (dx == 2 && dy == 2) || (dx == -2 && dy == 2);
					else 
						return (dx == -2 && dy == -2) || (dx == 2 && dy == -2);
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			this.selectedPiece = p;
			place(p, x, y);
		} else {
			if(this.selectedPiece != null) {
				int[] loc = pieceLocation(this.selectedPiece);
				this.selectedPiece.move(x, y);
				if (selectedPiece.isBomb() && this.selectedPiece.hasCaptured()) {
					int xi = loc[0];
					int yi = loc[1];
					int dirX = (x - xi > 0) ? 1 : -1;
					int dirY = (y - yi > 0) ? 1 : -1;
					remove(xi + dirX, yi + dirY);
					bombCapture(x, y);
					hasMoved = true; //change this to another method that can return if a double capture possible, same for others below
					selectedPiece = null;
				} else if (this.selectedPiece.hasCaptured()) {
					int xi = loc[0];
					int yi = loc[1];
					int dirX = (x - xi > 0) ? 1 : -1;
					int dirY = (y - yi > 0) ? 1 : -1;
					remove(xi + dirX, yi + dirY);
					place(this.selectedPiece, x, y);
					multiCapture = true;
					hasMoved = true;
				} else {
					place(this.selectedPiece, x, y);
					hasMoved = true;
					selectedPiece = null;
				} 
				pieces[loc[0]][loc[1]] = null;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (this.notOutBounds(x, y) && p != null) {
			//if (this.pieceAt(x, y) != null)
			//	this.remove(x, y);
			pieces[x][y] = p;
			//p.move(x, y);
		}	
	}

	private static String getName(Piece p) {
		String file = "img/";
		if (p.isBomb())
			file = file.concat("bomb");
		else if (p.isShield())
			file = file.concat("shield");
		else 
			file = file.concat("pawn");
		if (p.isFire())
			file = file.concat("-fire");
		else
			file = file.concat("-water");
		if (p.isKing())
			file = file.concat("-crowned");
		return file.concat(".png");
	}

	public Piece remove(int x, int y) {
		if (this.notOutBounds(x, y)) {
			Piece temp = this.pieceAt(x, y);
			pieces[x][y] = null;
			return temp;
		}
		System.out.println(x + " and " + y + " are out of bounds!");
		return null;
	}

	private boolean notOutBounds(int x, int y) {
		return x < BOARD_SIZE && y < BOARD_SIZE && y >= 0 && x >= 0;
	}

	public boolean canEndTurn() {
		if (multiCapture) 
			return true;
		if (this.selectedPiece != null)
			return hasMoved || this.selectedPiece.hasCaptured() || multiCapture;
		return hasMoved;
	}

	public void endTurn() {
		isFireTurn = !isFireTurn;
		hasMoved = false;
		multiCapture = false;;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
			selectedPiece = null;
		}
	}

	private int[] pieceLocation(Piece p) {
		int[] arr = new int[2];
		for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
            	if (pieces[i][j] == p) {
            		arr[0] = i;
            		arr[1] = j;
            		return arr;
            	}
            }
        }
        return arr;
	}

	public String winner() {
		int firetotal = 0, watertotal = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
            	if (pieces[i][j] != null) {
	            	if (pieces[i][j].isFire())
	            		firetotal++;
	            	else
	            		watertotal++; 
	            }
            }
        }
        if (firetotal > 0 && watertotal == 0)
        	return "Fire";
        else if (watertotal > 0 && firetotal == 0)
        	return "Water";
        else if (watertotal + firetotal == 0)
        	return "No one";
        else 
        	return null;
	}

	private int abs(int a) {
        if (a < 0)
        	return a * -1;
        return a;
    }

    // Deletes all pieces in a 3 x 3 location.
    private void bombCapture(int x, int y) {
    	checkShield(x - 1, y - 1);
    	checkShield(x - 1, y);
    	checkShield(x - 1, y + 1);
    	checkShield(x, y - 1);
    	checkShield(x, y + 1);
    	checkShield(x + 1, y - 1);
    	checkShield(x + 1, y);
    	checkShield(x + 1, y + 1);
    	remove(x, y);
    }

    private void checkShield(int x, int y) {
    	Piece p = pieceAt(x, y);
    	if (p != null && !p.isShield())
    		remove(x, y);
    }
}