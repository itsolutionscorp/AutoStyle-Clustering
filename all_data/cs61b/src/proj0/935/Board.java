public class Board {
	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private Piece selected;
	private int selectedX;
	private int selectedY;
	private boolean fireTurn;
	private boolean moved;
	private boolean captured;
	


	public static void main(String[] args) {
		Board b = new Board(false);
		b.drawBoard();

		// while(true) {
  //           if (StdDrawPlus.mousePressed()) {
  //               double x = StdDrawPlus.mouseX();
  //               double y = StdDrawPlus.mouseY();
  //               if (canSelect((int) x, (int) y)) {
  //               	select((int) x, (int) y);
  //               }
  //           } else if (StdDrawPlus.isSpacePressed()) {
  //           	if (canEndTurn()) {
  //           		endTurn();
  //           	}
  //           }           
  //           StdDrawPlus.show(100);
  //       }

	}

	private void drawBoard() {
		int N = 8;
		// from StdDrawDemo.java
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        if(!shouldBeEmpty) {
        	for(double i = .5; i < 8; i += 2) {
        		StdDrawPlus.picture(i, .5, "img/pawn-fire.png", 1, 1);
      //  		pieces[(int)(i-.5)][0] = new Piece(true, this, (int)(i-.5), 0, "pawn");
        	}
        	for(double i = 1.5; i < 8; i += 2) {
        		StdDrawPlus.picture(i, 1.5, "img/shield-fire.png", 1, 1);
        //		pieces[(int)(i-.5)][1] = new Piece(true, this, (int)(i-.5), 1, "shield");
        	}
        	for(double i = .5; i < 8; i += 2) {
        		StdDrawPlus.picture(i, 2.5, "img/bomb-fire.png", 1, 1);
        //		pieces[(int)(i-.5)][2] = new Piece(true, this, (int)(i-.5), 2, "bomb");
        	}
        	for(double i = 1.5; i < 8; i += 2) {
        		StdDrawPlus.picture(i, 7.5, "img/pawn-water.png", 1, 1);
        //		pieces[(int)(i-.5)][7] = new Piece(false, this, (int)(i-.5), 7, "pawn");
        	}
        	for(double i = .5; i < 8; i += 2) {
        		StdDrawPlus.picture(i, 6.5, "img/shield-water.png", 1, 1);
        //		pieces[(int)(i-.5)][6] = new Piece(false, this, (int)(i-.5), 6, "shield");
        	}
        	for(double i = 1.5; i < 8; i += 2) {
        		StdDrawPlus.picture(i, 5.5, "img/bomb-water.png", 1, 1);
        //		pieces[(int)(i-.5)][5] = new Piece(false, this, (int)(i-.5), 5, "bomb");
        	}



       }

       		while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (canSelect((int) x, (int) y)) {
                	select((int) x, (int) y);
                }
            } else if (StdDrawPlus.isSpacePressed()) {
            	if (canEndTurn()) {
            		endTurn();
            	}
            }           
            StdDrawPlus.show(100);
        }
    }
	

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		fireTurn = true;
		moved = false;
		captured = false;
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			
			for(double i = .5; i < 8; i += 2) {
        		
        		pieces[(int)(i-.5)][0] = new Piece(true, this, (int)(i-.5), 0, "pawn");
        	}
        	for(double i = 1.5; i < 8; i += 2) {
        	
        		pieces[(int)(i-.5)][1] = new Piece(true, this, (int)(i-.5), 1, "shield");
        	}
        	for(double i = .5; i < 8; i += 2) {
       
        		pieces[(int)(i-.5)][2] = new Piece(true, this, (int)(i-.5), 2, "bomb");
        	}
        	for(double i = 1.5; i < 8; i += 2) {
        	
        		pieces[(int)(i-.5)][7] = new Piece(false, this, (int)(i-.5), 7, "pawn");
        	}
        	for(double i = .5; i < 8; i += 2) {
        	
        		pieces[(int)(i-.5)][6] = new Piece(false, this, (int)(i-.5), 6, "shield");
        	}
        	for(double i = 1.5; i < 8; i += 2) {
       
        		pieces[(int)(i-.5)][5] = new Piece(false, this, (int)(i-.5), 5, "bomb");
			}

		}
	}

	public Piece pieceAt(int x, int y) {
		if (x>8 || y>8) {
			return null;
		} else {
			return pieces[x][y];
		}

	}

	public boolean canSelect(int x, int y) {
		Piece p = pieces[x][y];
		if (p != null) {
			
			if (p.isFire() == fireTurn) {
				if (selected == null) {
					return true;
				} else if (!moved) {
					return true;
				}

			}

			// if (selected == null || !moved) {
			// 	return true;
			// }
		} else {
			if (!moved && validMove(selectedX, selectedY, x, y)) {
				return true;
			} else if (captured && validCapture(selectedX, selectedY, x, y) ) { //check if next is capture pos
				return true;

			}


		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		int deltaX = xi - xf;
		int deltaY = yi - yf;

		if (selected.isKing()) {
			if (deltaY == 2 && deltaX == 2) {
				Piece middle = pieces[xi-1][yi-1];
				if (middle != null && (selected.isFire() != middle.isFire())) {
					return true;
				}
				//return false;

			} else if (deltaY == -2 && deltaX == -2) {
				Piece middle = pieces[xi + 1][yi + 1];
				if (middle != null && (selected.isFire() != middle.isFire())) {
					return true;
				}
			//	return false;
			}
		} else if (selected.isFire() && deltaX == -2 && deltaY == -2) {
			 Piece middle = pieces[xi + 1][yi + 1];
			 if (middle != null && !middle.isFire()) {
			 	return true;
			 }
		//	 return false;

		} else if (!selected.isFire() && deltaX == 2 && deltaY == 2) {
			Piece middle = pieces[xi - 1][yi - 1];
			if (middle != null && middle.isFire()) {
				return true;
			}
		//	return false;
		}
		return false;

	}

    /**
     * This method is optional.
     */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		//tests if move is diagonal	
		int deltaX = xi-xf;
		int deltaY = yi-yf;
		if (selected.isKing()) {	
			if (deltaX == deltaY) {
				return true;
			} else {
				return false;
			}
		} else if (selected.isFire()) {
			if (deltaX < 0 && deltaY < 0 && deltaY == deltaX) {
				return true;
			} else {
				return false;
			}
		} else {
			if (deltaX > 0 && deltaY > 0 && deltaX == deltaY) {
				return true;
			} else {
				return false;
			}
		}
	
	}

	public void select(int x, int y) {
		
		moved = false;
		selectedX = x;
		selectedY = y;
		Piece old_selected = selected;
		selected = pieces[x][y];
		if (selected == null) {
			old_selected.move(x, y);
			if (old_selected.hasCaptured()) {
				captured = true;
			}
			moved = true;
		}

	}

	public void place(Piece p, int x, int y) {
		if (x<=8 && y<=8 && p!=null) {
			pieces[x][y] = p;

		}
	}

public Piece remove(int x, int y) {
	if (x>8 || y>8) {
		System.out.println("Out of bounds.");
		return null;
	} else if (pieces[x][y]==null) {
		System.out.println("No piece at ("+x+","+y+")");
	}	
	Piece temp = pieces[x][y];
	pieces[x][y]=null;
	return temp;
}

	public boolean canEndTurn() {
		if (moved || captured) {
			return true;
		} else {
			return false;
		}
		

	}

	public void endTurn() {
		fireTurn = (!fireTurn);
		moved = false;
		captured = false;

	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				Piece current = pieces[i][j];
				if (current != null) {
					if (current.isFire()) {
						numFire += 1;
					} else {
						numWater += 1;
					}
				}
			}
		}

		if (numWater>0 && numFire>0) {
			return null;
		} else if (numWater==0 && numFire==0) {
			return "No one";
		} else if (numFire>numWater) {
			return "Fire";
		} else {
			return "Water";
		}
		


	}
 }