public class Board {
    private Piece[][] holder;
    private boolean turn = true; //true is fire
    private Piece selected = null;
    private int movedCount = 0;
    //private static boolean[][] pieces;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {
    	//Board constructor
    	holder = new Piece[8][8];
    	if (shouldBeEmpty == false) {
    		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
                	if ((i + j) % 2 == 0) {
                		if (j == 0) {
                			holder[i][j] = new Piece(true, this, i, j, "pawn");
                		}
                		else if (j == 1) {
                			holder[i][j] = new Piece(true, this, i, j, "shield");
                		}
              			else if (j == 2) {
              				holder[i][j] = new Piece(true, this, i, j, "bomb");
              			}
                		else if (j == 5) {
							holder[i][j] = new Piece(false, this, i, j, "bomb");
                		}
                		else if (j == 6) {
                			holder[i][j] = new Piece(false, this, i, j, "shield");
                		}
                		else if (j == 7) {
                			holder[i][j] = new Piece(false, this, i, j, "pawn");
                		}
                	}
            	}
        	}
    	}
    }

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //if (pieces[i][j]) {
                    //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    //StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    //pieces[i][j] = false;
                //}
            }
        }
    }


    private void drawPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece current = pieceAt(i, j);
                if (current != null) {
                	if (current.isKing()) {
                		if (current.isFire() == true) {
                			if (current.isBomb()) {
                				StdDrawPlus.picture(i+ .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);	              		}
                			else if (current.isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                		}
                		if (current.isFire() == false) {
                			if (current.isBomb()) {
                				StdDrawPlus.picture(i+ .5, j + .5, "img/bomb-water-crowned.png", 1, 1);	              		}
                			else if (current.isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                		}
                	}
                	else if (current.isFire() == true) {
                		if (current.isBomb()) {
                			StdDrawPlus.picture(i+ .5, j + .5, "img/bomb-fire.png", 1, 1);	
                		}
                		else if (current.isShield()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                	}
                	else{
                		if (current.isBomb()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);	
                		}
                		else if (pieceAt(i, j).isShield()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}	
                	}
            	}
            }
        }
    }

    public Piece pieceAt(int x, int y) {
    	if (x >= 8 || x < 0 || y > 8 || y < 0 || (x + y) % 2 != 0 || holder[x][y] == null){
    		return null;
    	} else {
    		return holder[x][y];
    	}
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	//Midpoint formula logic derived from help from n.alluri
    	if (holder[xi][yi] == null || xi >= 8 || xi < 0 || yi >= 8 || yi < 0 || (xi + yi) % 2 != 0 ||
    	holder[xf][yf] != null || xf >= 8 || xf < 0 || yf >= 8 || yf < 0 || (xf + yf) % 2 != 0) {
    		return false;
    	}
    	if (pieceAt(xi, yi).isFire() == true) {
    		if (yf == yi + 1 && Math.abs(xf - xi) == 1 && movedCount == 0) {
    			if (pieceAt(xf, yf) == null) {
    				return true;
    			}
    		}
    		else if (yf == yi + 2 && Math.abs(xf - xi) == 2 && pieceAt((xi + xf)/2, (yi + yf)/2) != null && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() != pieceAt(xi, yi).isFire()) {
    			return true;
    		}
    	}
    	if (pieceAt(xi, yi).isFire() == false) {
    		if (yf == yi - 1 && Math.abs(xf - xi) == 1 && movedCount == 0) {
    			if (pieceAt(xf, yf) == null) {
    				return true;
    			}
    		}
    		else if (yf == yi - 2 && Math.abs(xf - xi) == 2 && pieceAt((xi + xf)/2, (yi + yf)/2) != null && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() != pieceAt(xi, yi).isFire()) {
    			return true;
    		}
    	}
    	if (pieceAt(xi, yi).isKing() == true) {
    		if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1 && movedCount == 0) {
    			return true;
    		}
    		else if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2 && pieceAt((xi + xf)/2, (yi + yf)/2) != null && pieceAt((xi + xf)/2, (yi + yf)/2).isFire() != pieceAt(xi, yi).isFire()) {
    			return true;
    		}
    	} 

    	return false;
    }

    private int coordConvert(Piece toConvert, String coord) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece current = pieceAt(i, j);
                if (current == toConvert) {
                	if (coord == "x") {
                		return i;
                	} else {
                		return j;
                	}
                }
    		}
    	}
    	return 0;
    }

    public boolean canSelect(int x, int y) {
        if (pieceAt(x,y) == null && selected != null) {
        	int selectedY = coordConvert(selected, "y");
    		int selectedX = coordConvert(selected, "x");

    		if (selected.hasCaptured() == true){
    			return validMove(selectedX, selectedY, x, y);
    		}
    		else if (movedCount == 0) {
    			return validMove(selectedX, selectedY, x, y);
    		}
    	}	
    	if (pieceAt(x,y) != null && turn == pieceAt(x,y).isFire()) {
    		if (selected == null || movedCount == 0) {
    			return true;
    		}
    	} 
    return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
		}
		if ((selected != null) && (pieceAt(x, y) == null)) {
			selected.move(x, y);
			this.movedCount = this.movedCount + 1;
				//if (selected.hasCaptured() && canMultiCap(x, y) == true) {
					//this.moved = false;
				//} 
			}
		}
            //pieces[(int) x][(int) y] = true;
	

	 private boolean canMultiCap(int x, int y) {
		if (x + 2 < 8 && y + 2 < 8 && pieceAt(x + 2, y + 2) == null) {
			if (pieceAt(x + 1, y + 1) != null && pieceAt(x, y) != null) {
				if (pieceAt(x + 1, y + 1).isFire() != pieceAt(x, y).isFire()) {
					return true;
				}
			}
		}
		if (x + 2 < 8 && y - 2 >= 0 && pieceAt(x + 2, y - 2) == null) {
			if (pieceAt(x + 1, y - 1) != null && pieceAt(x, y) != null) {
				if (pieceAt(x + 1, y - 1).isFire() != pieceAt(x, y).isFire()) {
					return true;
				}
			}
		}
		if (x - 2 >= 0 && y + 2 < 8 && pieceAt(x - 2, y + 2) == null) {
			if (pieceAt(x - 1, y + 1) != null && pieceAt(x, y) != null) {
				if (pieceAt(x - 1, y + 1).isFire() != pieceAt(x, y).isFire()) {
					return true;
				}
			}
		}
		if (x - 2 >= 0 && y - 2 >= 0 && pieceAt(x - 2, y - 2) == null) {
			if (pieceAt(x - 1, y - 1) != null && pieceAt(x, y) != null) {
				if (pieceAt(x - 1, y - 1).isShield() == false) {
					return true;
				}
			}			
		}
		return false;
    }

    public void place(Piece p, int x, int y) {
    	if (x < 8 || x >= 0 || y < 8 || y >= 0 || p != null || (x + y) % 2 != 0) {
    		holder[x][y] = p;
    	} 
    }

    public Piece remove(int x, int y) {
    	if (x >= 8 || x < 0 || y >= 8 || y < 0 || (x + y) % 2 != 0){
    		System.out.println("Out of Bounds. Either x or y is off the board.");
    		return null;
    	} else {
    		if (holder[x][y] == null) {
    			System.out.println("No piece at x, y");
    			return null;
    		} else {
    			Piece removed = holder[x][y];
    			holder[x][y] = null;
    			return removed;
    		}
    	}
    }

    public boolean canEndTurn() {
		if (movedCount > 0) {
    		return true;
    	} 
    	else if (selected != null) {
    		if (selected.hasCaptured() == true && movedCount > 0) {
    			return true;
    		}

    	}
    	return false; 
    }

    public void endTurn() {
    	if (selected != null) {
    		selected.doneCapturing();
    		selected = null;
    	}
    	movedCount = 0;
    	if (turn == true) {
    		turn = false;
    	}
    	else if (turn == false) {
    		turn = true;
    	}
    }


    public String winner() {
    	int fireCount = 0;
    	int waterCount = 0;
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (holder[i][j] != null) {
            		if (holder[i][j].isFire() == true){
            			fireCount = fireCount + 1;
            		} else {
            			waterCount = waterCount + 1;
            		}
            	}
            }
        }
        if (fireCount > 0 && waterCount > 0) {
        	return null;
        }
        else if (fireCount > 0 && waterCount == 0) {
        	return "Fire";
        }
        else if (waterCount > 0 && fireCount == 0) {
        	return "Water";
        } else {
        	return "No one";
        }
    }

    public static void main(String[] args) {
    	Board play = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        //pieces = new boolean[8][8];

        /** Monitors for mouse presses. */
	    while(play.winner() == null) {
            play.drawBoard();
            play.drawPieces();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if ((int) x < 8 && (int) x >= 0 && (int) y >=0 && (int) y < 8) {
                   	if (play.canSelect((int) x, (int) y)){
                	play.select((int) x, (int) y);
                	}		
                }

            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (play.canEndTurn()) {
            		play.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }
    }
}


