public class Board {
	
	private Piece[][] myBoard; 
	private Piece currPiece;
	private int pastX = 10, pastY = 10, distX, distY, turn = 0, moved = 0;
	// private static boolean[][] pieces;

	public static void main (String args[]) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        // pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        
        Board b = new Board(false);
                
        while(true) {    
        	b.drawBoard(N);    	
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                //pieces[(int) x][(int) y] = true;
               	if (b.canSelect((int) x, (int) y) == true) {
               		System.out.println("can select!");
                	b.select((int) x, (int) y);
                }
            }
            if ((StdDrawPlus.isSpacePressed()) && (b.canEndTurn())) {
            	b.endTurn();

            }          
            StdDrawPlus.show(100);

        }
	} 

	// check later if this needs to be amended
	private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (myBoard[i][j] != null) {
                	if (myBoard[i][j].side() == 0) {
                		if (myBoard[i][j].isBomb()) {
                			if (myBoard[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                    		else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    		}
                		}
                		else if (myBoard[i][j].isShield()) {
                			if (myBoard[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    		}
                		}
                		else {
                    		if (myBoard[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    		}
                		}
                	}
                	else {
						if (myBoard[i][j].isBomb()) {
                			if (myBoard[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                    		else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    		}
                		}
                		else if (myBoard[i][j].isShield()) {
                			if (myBoard[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    		}
                		}
                		else {
                    		if (myBoard[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                			else {
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    		}
                		}
                	}
            	}
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		myBoard = new Piece[8][8];
		if (shouldBeEmpty == true) {
		}
		else {
			//create loop to put in 3 types of pieces for 6 rows
			// int xCood = 0, yCood = 0;
			// myBoard[0][0] = new Piece(true, this, 0, 0, "pawn"); //test piece

			myBoard[0][0] = new Piece(true, this, 0, 0, "pawn");
			myBoard[2][0] = new Piece(true, this, 2, 0, "pawn"); 
			myBoard[4][0] = new Piece(true, this, 4, 0, "pawn");
			myBoard[6][0] = new Piece(true, this, 6, 0, "pawn");
			myBoard[1][1] = new Piece(true, this, 1, 1, "shield");
			myBoard[3][1] = new Piece(true, this, 3, 1, "shield"); 
			myBoard[5][1] = new Piece(true, this, 5, 1, "shield");
			myBoard[7][1] = new Piece(true, this, 7, 1, "shield");
			myBoard[0][2] = new Piece(true, this, 0, 2, "bomb");
			myBoard[2][2] = new Piece(true, this, 2, 2, "bomb"); 
			myBoard[4][2] = new Piece(true, this, 4, 2, "bomb");
			myBoard[6][2] = new Piece(true, this, 6, 2, "bomb");

			myBoard[1][5] = new Piece(false, this, 1, 5, "bomb");
			myBoard[3][5] = new Piece(false, this, 3, 5, "bomb"); 
			myBoard[5][5] = new Piece(false, this, 5, 5, "bomb");
			myBoard[7][5] = new Piece(false, this, 7, 5, "bomb");
			myBoard[0][6] = new Piece(false, this, 0, 6, "shield");
			myBoard[2][6] = new Piece(false, this, 2, 6, "shield"); 
			myBoard[4][6] = new Piece(false, this, 4, 6, "shield");
			myBoard[6][6] = new Piece(false, this, 6, 6, "shield");
			myBoard[1][7] = new Piece(false, this, 1, 7, "pawn");
			myBoard[3][7] = new Piece(false, this, 3, 7, "pawn"); 
			myBoard[5][7] = new Piece(false, this, 5, 7, "pawn");
			myBoard[7][7] = new Piece(false, this, 7, 7, "pawn");

			/* while (xCood <= 7) {
				if (xCood % 2 == 0) {
					yCood = 0;
				}
				else {
					yCood = 1;
				}
					while (yCood <= 7) {
						if (xCood == 0) {
							myBoard[xCood][yCood] = new Piece(true, this, xCood, yCood, "pawn");
							yCood += 2;
						}
						if (xCood == 1) {
							myBoard[xCood][yCood] = new Piece(true, this, xCood, yCood, "shield");
							yCood += 2;
						}
						if (xCood == 2) {
							myBoard[xCood][yCood] = new Piece(true, this, xCood, yCood, "bomb");
							yCood += 2;
						}
						if (xCood == 5) {
							myBoard[xCood][yCood] = new Piece(false, this, xCood, yCood, "bomb");
							yCood += 2;
						}
						if (xCood == 6) {
							myBoard[xCood][yCood] = new Piece(false, this, xCood, yCood, "shield");
							yCood += 2;
						}
						if (xCood == 7) {
							myBoard[xCood][yCood] = new Piece(false, this, xCood, yCood, "pawn");
							yCood += 2;
						}
					}
				xCood += 1;
			} */
		} 
	}

	//EDIT THIS LATER TOO!
	public Piece pieceAt(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7) || (myBoard[x][y] == null)) {
			return null;
		}
		else {
			return myBoard[x][y];
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		distX = xf - xi;
		distY = yf - yi;
		if ((Math.abs(distX) == 1) && (Math.abs(distY) == 1)) {
			return true;
		}
		else if ((Math.abs(distX) == 2) && (Math.abs(distY) == 2)) {
			return true;
		} 
		/*
		if ((Math.abs(distX)) == (Math.abs(distY))) {
			return true;
		}*/
		else {
			return false;
		}
		
	}

	/* private boolean validCapture(int xi, int yi, int xf, int yf) {
		distX = xf - xi;
		distY = yf - yi;
		Piece midPiece = myBoard[((xi + xf)/ 2)][((xi + xf)/ 2)];
		if (((Math.abs(distX)) == 2) && (midPiece != null) && (midPiece.side() != turn)) {
			if (currPiece.isKing() == true) {
				return true;
			}
			else if ((turn == 0) && (distY > 0)) {
				return true;
			}
			else if ((turn == 1) && (distY < 0)) {
				return true;
			}
		}
	}*/

	public boolean canSelect(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return false;
		}
		distX = x - pastX;
		distY = y - pastY;
		if ((myBoard[x][y] != null) && (myBoard[x][y].side() == turn)) { // && (currPiece.hasCaptured() == false)
			if (currPiece == null) {
				return true;
			}
			else {
				if (moved == 0) {
					return true;
				}
				return false;
			}
		}
		else if ((myBoard[x][y] == null) && (currPiece != null)) {
				if ((currPiece.hasCaptured() == true) && (Math.abs(distX) == 1)) {
					return false;
				}
				if ((validMove(pastX, pastY, x, y) == true)) {
					if ((moved == 0) || (currPiece.hasCaptured())) {
						Piece midPiece = myBoard[(pastX + (distX/2))][(pastY + (distY/2))];
						if (((Math.abs(distX)) == 2) && (midPiece != null) && (midPiece.side() != turn)) {
							if (currPiece.isKing() == true) {
								return true;
							}
							else if ((turn == 0) && (distY > 0)) {
					 			return true;
							}
							else if ((turn == 1) && (distY < 0)) {
								return true;
							}
						}
						else if (((Math.abs(distX)) == 1) && (moved == 0)) {
							if (currPiece.isKing() == true) {
								return true;
							}
							else if ((turn == 0) && (distY > 0)) {
								return true;
							}
							else if ((turn == 1) && (distY < 0)) {
								return true;
							}
						}	
					}	
				}
		}
		return false;
	}

	//still need to check
	public void select(int x, int y)  {
		if (myBoard[x][y] != null) {

			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			currPiece = myBoard[x][y];

			pastX = x;
			pastY = y;

			//how do you prep the piece for movement
			//goes back to main and wait for another click. 
		}
		else {
			currPiece.move(x, y);
			//place(myBoard[pastX][pastY], x, y); //put this in move method!!
			//System.out.println("LOOK HERE. IT'S GONNA CHANGE MOVED!!!");
			moved = 1;
			pastX = x;
			pastY = y;
			if (myBoard[x][y] != null) {
				currPiece = myBoard[x][y];
			}
		}		
	}
	
	public void place(Piece p, int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7) || (p == null)) {
		}
		else {
			myBoard[x][y] = p;
			//how do we remove p from its old position?
		}
	}
	
	public Piece remove(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7) || (myBoard[x][y] == null)) {
			System.out.println("There is no piece! / Out of bounds!");
			return null;
		}
		else {
			Piece removedPiece = myBoard[x][y];
			myBoard[x][y] = null;
			return removedPiece;
		}
	}
	
	public boolean canEndTurn() {
		if (moved != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		//myBoard[x][y].
		if (turn == 0) {
			turn = 1;
		}
		else {
			turn = 0;
		}
		currPiece.doneCapturing();
		currPiece = null;
		moved = 0;
	}
	
	public String winner() {
		int firePieces = 0, waterPieces = 0;
		for (int i = 0; i <= 7; i += 1) {
			for (int j = 0; j <= 7; j += 1) {
				if (myBoard[i][j] != null) {
					if (myBoard[i][j].side() == 0) {
						firePieces += 1;
					}
					else {
						waterPieces += 1;
					}
				}
			}
		}
		if ((firePieces > 0) && (waterPieces == 0)) {
			return "Fire";
		}
		if ((firePieces == 0) && (waterPieces > 0)) {
			return "Water";
		}
		if ((firePieces == 0) && (waterPieces == 0)) {
			return "No one";
		}
		else {
			return null;
		}
	}

	/* picture(int x, int y, String imgPath, int w, int h)


		if ((x<0) || (x>7) || (y<0) || (y>7)){
			return null;
			System.out.println("")
		} */

	
}