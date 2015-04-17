
public class Board {

	private Piece[][] pieceArray;
	private int sumFire;
	private int sumWater;
	private Piece selectedSquare;
	private boolean hasMoved;
	private boolean capturedVar;
	private boolean colorWhite;
	private int whiteX;
	private int whiteY;
	private int selectedX;
	private int selectedY;
	private int whoseTurn;
	private int i;

	public static void main(String[] args) {
		Board b = new Board(false);

		while (true) {
			b.drawGUI();
			StdDrawPlus.show(15);
		    if (StdDrawPlus.mousePressed()) {
	            double x = StdDrawPlus.mouseX();
	            double y = StdDrawPlus.mouseY();
	            if (b.canSelect((int) x, (int) y)) {
	            	b.select((int) x, (int) y);
	            }
	   		}
	   		if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn() == true) {
					b.endTurn();
				}
			}

		}
    }


	   	
	

	

	public Board(boolean shouldBeEmpty) {
		int N = 8;
		pieceArray = new Piece[N][N];
		whoseTurn = 2;
		i = 0;

		if (shouldBeEmpty == false) {
			for (int i = 0; i < N; i += 2) {
				Piece p = new Piece(true, this, i, 0, "pawn");
				pieceArray[i][0] = p;
			}
			for (int i = 1; i < N; i += 2) {
				Piece p = new Piece(true, this, i, 1, "shield");
				pieceArray[i][1] = p;
			}
			for (int i = 0; i < N; i += 2) {
				Piece p = new Piece(true, this, i, 2, "bomb");
				pieceArray[i][2] = p;
			}
			for (int i = 1; i < N; i += 2) {
				Piece p = new Piece(false, this, i, 5, "bomb");
				pieceArray[i][5] = p;
			}
			for (int i = 0; i < N;i += 2) {
				Piece p = new Piece(false, this, i, 6, "shield");
				pieceArray[i][6] = p;
			}
			for (int i = 1; i < N; i += 2) {
				Piece p = new Piece(false, this, i, 7, "pawn");
				pieceArray[i][7] = p;
			}
		}
	}

    private void drawBlankBoard(int N) {	
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        }
    }
    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    if (colorWhite == true) {
        StdDrawPlus.filledSquare(whiteX + .5, whiteY + .5, .5);
            }
	}

	private void drawGUI() {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        drawBlankBoard(N);
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
            	if (pieceArray[i][j] != null) {
            		if (pieceArray[i][j].isFire()) {
            			if (pieceArray[i][j].isBomb()) {
            				if (pieceArray[i][j].isKing()) {
            					StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);
            				}
            				if (!pieceArray[i][j].isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            				}
            			}
            			else if (pieceArray[i][j].isShield()) {
            				if (pieceArray[i][j].isKing()) {
            					StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire-crowned.png", 1, 1);
            				}
            				if (!pieceArray[i][j].isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            				}
            			}
            			else {
            				if (pieceArray[i][j].isKing()) {
            					StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);
            				}
            				if (!pieceArray[i][j].isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            				}
            			}
            		}

            		if (pieceArray[i][j].isBomb() && !pieceArray[i][j].isFire()) {
            			if (pieceArray[i][j].isKing()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            			}
            			if (!pieceArray[i][j].isKing()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            			}
            		}
            		else if (pieceArray[i][j].isShield() && !pieceArray[i][j].isFire()) {
            			if (pieceArray[i][j].isKing()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            			}
            			if (!pieceArray[i][j].isKing()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            			}
            		}
            		if (!pieceArray[i][j].isFire() && !pieceArray[i][j].isBomb() && !pieceArray[i][j].isShield()) {
            			if (pieceArray[i][j].isKing()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            			}
            			if (!pieceArray[i][j].isKing()) {
            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            			}
            		}
            	}
            }
        }
    }

    

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		return pieceArray[x][y];
	}

	public void place(Piece p, int x, int y) {
		pieceArray[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x > 8 || x < 0) {
			System.out.println("Input x must be 0 <= x <= 8");
			return null;
		}
		if (y > 8 || y < 0) {
			System.out.println("Input y must be 0 <= y <= 8");
			return null;
		}
		if (pieceArray[x][y] == null) {
			System.out.println("There is no piece at (" + x + "," + y + ")");
			return null;
		}
		Piece removed = pieceArray[x][y];
		pieceArray[x][y] = null;
		return removed;
	}

	public String winner() {
		sumFire = 0;
		sumWater = 0;
		for (int j = 0; j < 8; j++) {
	        for (int i = 0; i < 8; i++) {
	        	if (pieceArray[i][j] != null) {
	        		if (pieceArray[i][j].isFire()) {
	        			sumFire += 1;
	        		}
	        		else if (!pieceArray[i][j].isFire()) {
	        			sumWater += 1;
	        	}
	    	}
	    }
	}

        if (sumWater > sumFire && sumFire == 0) {
        	return "Water";
        }
        else if (sumFire > sumWater && sumWater == 0) {
        	return "Fire";	    
        }
        else if (sumFire == 0 && sumWater == 0) {
        	return "No one";
        }
        return null;
	}



	public boolean canSelect(int x, int y) {

		if (pieceArray[x][y] != null) {

			//Checks correct player's turn
			if (pieceArray[x][y].isFire() == true && whoseTurn % 2 == 0 ||
				pieceArray[x][y].isFire() == false && whoseTurn % 2 != 0) {
				//Hasn't selected a piece yet
				if (selectedSquare == null) {
					return true;
				}
				//Selected square hasn't moved yet
				if (hasMoved == false) {
					return true;
				}
				return false;
			}
		}

		//Checks if piece has captured on same turn
		if (selectedSquare != null) {
			if (selectedSquare.hasCaptured() == true) {
				capturedVar = true;
			}
		}

		
		//Checks turn again
		if (pieceArray[x][y] == null && selectedSquare != null) {
			if (selectedSquare.isFire() == true && whoseTurn % 2 == 0 ||
					selectedSquare.isFire() == false && whoseTurn % 2 != 0) {
				if (selectedSquare == null) {
						return false;
					}

				if (hasMoved == false) {
					if (validMove(selectedX, selectedY, x, y) == true) {
						return true;
					}
					return false;
				}
				if (capturedVar == true) {
					if (Math.abs(x - selectedX) == 2 && Math.abs(y - selectedY) == 2) {
						if (validMove(selectedX, selectedY, x, y) == true) {
							return true;
						}
						return false;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false; 
	}

	

	public void select(int x, int y) {

		if (pieceAt(x, y) == null) {
			if (selectedSquare != null) {
				selectedSquare.move(x, y);
				hasMoved = true;
				if (selectedSquare.hasCaptured()) {
					capturedVar = true;
				}
			}
		}
		if (pieceArray[x][y] != null) {
			selectedX = x;
			selectedY = y;
			highlight(x, y);
			selectedSquare = pieceArray[x][y];
		}	
	}

	private void highlight(int x, int y) {
		whiteX = x;
		whiteY = y;
		colorWhite = true;
	}

	public boolean canEndTurn() {
		if (hasMoved == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		capturedVar = false;
		hasMoved = false;
		whoseTurn += 1;
		selectedSquare.doneCapturing();
		selectedSquare = null;
		colorWhite = false;
		winner();
	}

/*  Private methods to help with move checking.
	1. validMove (think of it as the "main" move checking method).
	2. validKingMove (called from validMove).
	3. validTake (called from validMove, handles non-king takes). */

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf > 7 || yf > 7) {
			return false;
		}
		if (pieceArray[xi][yi] == null) {
			return false;
		}
		if (pieceArray[xf][yf] != null) {
			return false;
		}
		//If piece is a King
		if (pieceArray[xi][yi].isKing()) {
			return validKingMove(xi, yi, xf, yf);
		}
		//Normal Fire move
		if (yf - yi == 1) {
			if (xf - xi == 1 || xf - xi == -1) {
				if (pieceArray[xi][yi].isFire()) {
					return true;
				}
				return false;
			}
		}
		//Normal Water move
		if (yf - yi == -1) {
			if (xf - xi == 1 || xf - xi == -1) {
				if (!pieceArray[xi][yi].isFire()) {
					return true;
				}
				return false;
			}
		}
		//Normal Fire take
		if (pieceArray[xi][yi].isFire()) {
			if (yf - yi == 2 && xf - xi == 2 || xf - xi == -2) {
					return validFireTake(xi, yi, xf, yf);
				}
			return false;
			}
		//Normal Water take
		if (!pieceArray[xi][yi].isFire()) {
			if (yf - yi == -2 && xf - xi == 2 || xf - xi == -2) {
				return validWaterTake(xi, yi, xf, yf);
			}
			return false;
		}
		return false;
	}


	private boolean validKingMove(int xi, int yi, int xf, int yf) {

		//First implement basic moving
		if (((int) Math.abs(xf - xi)) == 1 && ((int) Math.abs(yf - yi)) == 1) {
			if (pieceArray[xf][yf] == null) {
				return true;
			}
			return false;
		}

		//Fire King Take
		if (pieceArray[xi][yi].isFire() == true) {

			if (xf - xi == 2 && yf - yi == 2) {
				if (pieceArray[xi+1][yi+1] == null) {
					return false;
				}
				if (pieceArray[xi+1][yi+1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
			if (xf - xi == 2 && yf - yi == -2) {
				if (pieceArray[xi+1][yi-1] == null) {
					return false;
				}
				if (pieceArray[xi+1][yi-1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
			if (xf - xi == -2 && yf - yi == 2) {
				if (pieceArray[xi-1][yi+1] == null) {
					return false;
				}
				if (pieceArray[xi-1][yi+1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
			if (xf - xi == -2 && yf - yi == -2) {
				if (pieceArray[xi-1][yi-1] == null) {
					return false;
				}
				if (pieceArray[xi-1][yi-1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
		}

		//Water King Piece	
		if (pieceArray[xi][yi].isFire() == false) {
			if (xf - xi == 2 && yf - yi == 2) {
				if (pieceArray[xi+1][yi+1] == null) {
					return false;
				}
				if (!pieceArray[xi+1][yi+1].isFire()) {
					return false; 
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
			if (xf - xi == 2 && yf - yi == -2) {
				if (pieceArray[xi+1][yi-1] == null) {
					return false;
				}
				if (!pieceArray[xi+1][yi-1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
			if (xf - xi == -2 && yf - yi == 2) {
				if (pieceArray[xi-1][yi+1] == null) {
					return false;
				}
				if (!pieceArray[xi-1][yi+1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
			if (xf - xi == -2 && yf - yi == -2) {
				if (pieceArray[xi-1][yi-1] == null) {
					return false;
				}
				if (!pieceArray[xi-1][yi-1].isFire()) {
					return false;
				}
				if (pieceArray[xf][yf] != null) {
					return false;
				}
				return true;
			}
		}
		return false;
	}


	private boolean validFireTake(int xi, int yi, int xf, int yf) {
		if (xf - xi == 2) {
			if (pieceArray[xi+1][yi+1] == null) {
				return false;
			}
			if (pieceArray[xi+1][yi+1].isFire()){
				return false;
			}
			if (pieceArray[xf][yf] != null) {
				return false;
			}
			return true;
		}
		if (xf - xi == -2) {
			if (pieceArray[xi-1][yi+1] == null) {
				return false;
			}
			if (pieceArray[xi-1][yi+1].isFire()){
				return false;
			}
			if (pieceArray[xf][yf] != null) {
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean validWaterTake(int xi, int yi, int xf, int yf) {
		if (xf - xi == 2) {
			if (pieceArray[xf-1][yf+1] == null) {
				return false;
			}
			if (pieceArray[xf-1][yf+1].isFire() == false){
				return false;
			}
			if (pieceArray[xf][yf] != null) {
				return false;
			}
			return true;
		}
		if (xf - xi == -2) {
			if (pieceArray[xf+1][yf+1] == null) {
				return false;
			}
			if (pieceArray[xf+1][yf+1].isFire() == false){
				return false;
			}
			if (pieceArray[xf][yf] != null) {
				return false;
			}
			return true;
		}
		return false;
	}
}



	

 





     













