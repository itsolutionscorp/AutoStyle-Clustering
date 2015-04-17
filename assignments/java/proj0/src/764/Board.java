public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private String turn = "fire";
	private boolean selected; 
	private boolean moved;
	private int[] selectedXY = new int[2];
	private Piece selectedPiece;
	private int countFire;
	private int countWater;

	private void generator(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		if (x >= 8 || y >= 8) {
			pieces[x][y] = null;
		}
		else if (y == 0 && x % 2 == 0) {
            pieces[x][y] = new Piece(true, this, x, y, "pawn");
        }
        else if (y == 1 && x % 2 == 1) {
        	pieces[x][y] = new Piece(true, this, x, y, "shield");
        }
        else if (y == 2 && x % 2 == 0) {
        	pieces[x][y] = new Piece(true, this, x, y, "bomb");
        }
        else if (y == 5 && x % 2 == 1) {
        	pieces[x][y] = new Piece(false, this, x, y, "bomb");;
        }
        else if (y == 6 && x % 2 == 0) {
        	pieces[x][y] = new Piece(false, this, x, y, "shield");
        }
        else if (y == 7 && x % 2 == 1) {
        	pieces[x][y] = new Piece(false, this, x, y, "pawn");
        }
        else {
        	pieces[x][y] = null;
        }
    }

    private String getPath(Piece[][] pieces, int x, int y) {
    	if (pieces[x][y].isKing()) {
    		if (pieces[x][y].isFire()) {
    			return "img/" + getType(pieces, x, y) + "-fire-crowned.png";
    		}  
    		else {
    			return "img/" + getType(pieces, x, y) + "-water-crowned.png"; 
    		}
    	}
    	else if (pieces[x][y].isFire()) {
    		return "img/" + getType(pieces, x, y) + "-fire.png";
    	}
    	else {
    		return "img/" + getType(pieces, x, y) + "-water.png";
    	}
    }

    private String getType(Piece[][] pieces, int x, int y) {
    	if (pieces[x][y].isBomb()) {
    		return "bomb";
    	}
    	else if (pieces[x][y].isShield()) {
    		return "shield";
    	}
    	else {
    		return "pawn";
    	}
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	int i;
    	int deltaY = yf - yi;
    	if (pieces[xi][yi] != null && pieces[xi][yi].isFire()) {
    		i = 1;
    	} 
    	else {
    		i = -1;
    		deltaY = -deltaY;
    	}
    	if (pieces[xi][yi] != null && pieces[xi][yi].isKing()) {
    		deltaY = Math.abs(deltaY);
    	}

    	if (xf == (xi+i) || xf == (xi-i) || deltaY == 2) {
    		//System.out.println("validMove, yf: " + yf + " deltaY: " + deltaY);
    		if (pieces[xi][yi] != null && pieces[xi][yi].isKing()) {
    			if (yf == (yi-i) || canCapture(pieces[xi][yi], xf, yf)) {
    				return true;
    			}
    		}
    		else if (yf == (yi+i) || canCapture(pieces[xi][yi], xf, yf)) {
    			return true;
    			}
    	}
    	return false;
    }

    private boolean canCapture(Piece p, int x, int y) {
    	int i = 1;
    		//System.out.println("line 95 - canCapture");
    		if (x >= 8 || y >= 8) {
    			return false;
    		}
	    	else if (p != null && p.isKing()) {
	    		//System.out.println("line 98 - canCapture, king mode");

	    			if ((pieces[x-i][y+i] != null && pieces[x-i][y+i].isFire() == false && x-i >= 0) || (pieces[x+i][y+i] != null && pieces[x+i][y+i].isFire() == false && x+i <= 7)) {
	    				return true;
	    			}
	    			if ((pieces[x-i][y-i] != null && pieces[x-i][y-i].isFire() && x-i >= 0) || (pieces[x+i][y-i] != null && pieces[x+i][y-i].isFire() && x+i <= 7)) {
	    				return true;
	    			}
	    	}
	    	else if (p != null && p.isKing() == false) {
	    		if (p.isFire()) {
	    			if ((pieces[x-i][y-i] != null && pieces[x-i][y-i].isFire() == false && x-i >= 0) || (pieces[x+i][y-i] != null && pieces[x+i][y-i].isFire() == false && x+i <= 7)) {
	    				return true;
	    			}
	    		}
	    		else if (p.isFire() == false) {
	    			if ((pieces[x-i][y+i] != null && pieces[x-i][y+i].isFire() && x-i >= 0) || (pieces[x+i][y+i] != null && pieces[x+i][y+i].isFire() && x+i <= 7)) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
    }

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                //StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	

                if (pieces[i][j] != null) {
                	StdDrawPlus.picture(i + .5, j + .5, getPath(pieces, i, j), 1, 1);
            	}
            }
        }
    }

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        Board b = new Board(false);
        while(true) {
            b.drawBoard(N);
            
            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                	//b.highlight[(int) x][(int) y] = true;
                }
            } 
            
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }

            StdDrawPlus.show(100);
        }
    }

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false){
			countFire = 8;
			countWater = 8;
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					generator(x, y);
				}

			}
		}
		else {
			countFire = 0;
			countWater = 0;
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					pieces[x][y] = null;
				}
			}
		}
		
	}

	public Piece pieceAt(int x, int y) {
		if ((x >= 8 || y >= 8) || (pieces[x][y] == null)) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if (x >= 8 || y >= 8) {
			return false;
		}
		if (pieces[x][y] != null) {
			if (pieces[x][y].isFire() && turn == "fire") {
				if ((selected == false) || (selected && moved == false)) {
					return true;
				}
			}
			else if (pieces[x][y].isFire() == false && turn == "water") {
				if ((selected == false) || (selected && moved == false)) {
					return true;
				}
			}
		}
		else if (pieces[x][y] == null) {
			if (selected && moved == false && validMove(selectedXY[0], selectedXY[1], x, y)) {
				return true;
			}
			else if (selected && moved && selectedPiece.hasCaptured() && validMove(selectedXY[0], selectedXY[1], x, y)) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		//System.out.println("select, line 229" + pieces[x][y]);
		if (pieces[x][y] != null) {
			//System.out.println("inside if: " + pieces[x][y]);
			selected = true;
			selectedPiece = pieces[x][y];
			selectedXY[0] = x;
			selectedXY[1] = y;
		} else {
			//System.out.println("select, line 231");
			selectedPiece.move(x, y);
			moved = true; 
			place(selectedPiece, x, y);
			remove(selectedXY[0], selectedXY[1]);
			//highlight[x][y] = false;
			if (selectedPiece.hasCaptured()) {
				selectedXY[0] = x;
				selectedXY[1] = y;
			}
			//check if bomb for explosion (remove self)
			if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
				//System.out.println("bomb it");
				remove(x, y);
				for (int i = x-1; i <= x+1; i++) {
					for (int j = y-1; j <= y+1; j++) {
						if (i <= 7 && j <= 7) { 
							if (pieces[i][j] != null) {
								if (pieces[i][j].isShield() == false) {
									remove(i, j);
								}
							}
						}
					}
				}
			}
		}
	}
	public void place(Piece p, int x, int y) {
		if (p == null || (x >= 8 || y >= 8)) {
			//System.out.println("Can't place there");
			return;
		}
		else {
			pieces[x][y] = p;
			if (pieces[x][y].isFire()) {
				countFire += 1;
			}
			else if (pieces[x][y].isFire() == false) {
				countWater += 1;
			}
			//System.out.println("placed at " + x + y);
		}
	}

	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8) {
			System.out.println("Cannot remove an out-of-bounds piece");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("There is no piece at (x, y)");
			return null;
		}
		else {	
			if (pieces[x][y].isFire()) {
				countFire -= 1;
			}
			else if (pieces[x][y].isFire() == false) {
				countWater -= 1;
			}
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
			}
	}

	public boolean canEndTurn() {
		if (moved || (selectedPiece != null && selectedPiece.hasCaptured())) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		selected = false;
		moved = false;
		if (turn == "fire") {
			turn = "water";
		}
		else if (turn == "water") {
			turn = "fire";
		}
		if (selectedPiece != null && selectedPiece.hasCaptured()) {
			selectedPiece.doneCapturing();
		}
	}

	public String winner() {
		if (countWater == 0 && countFire == 0) {
			return "No one";	
		}
	
		else if (countWater == 0) {
			return "Fire";
		}
		else if (countFire == 0) {
			return "Water";
		}
		else {
			return null;
		}
	}
}
