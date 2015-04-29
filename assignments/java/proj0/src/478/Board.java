import java.lang.Math;

public class Board {

	private Piece[][] array;
	private boolean shouldBeEmpty;
	private boolean haveMoved = false;
	private int whichPlayer = 0;
	private Piece selectedPiece;
	private int firePieceCounter;
	private int waterPieceCounter;

	public Board(boolean shouldBeEmpty) {
		
		this.shouldBeEmpty = shouldBeEmpty;

		int N = 8;
        array = new Piece[N][N];

		if (this.shouldBeEmpty == false) {
			
			for (int i = 0; i < 7; i += 2) {
				array[i][0] = new Piece(true, this, i, 0, "pawn");
			}
			
			for (int i = 1; i < 8; i += 2) {
				array[i][1] = new Piece(true, this, i, 1, "shield");
			}

			for (int i = 0; i < 7; i += 2) {
				array[i][2] = new Piece(true, this, i, 2, "bomb");
			}

			for (int i = 1; i < 8; i += 2) {
				array[i][5] = new Piece(false, this, i, 5, "bomb");
			}

			for (int i = 0; i < 7; i += 2) {
				array[i][6] = new Piece(false, this, i, 6, "shield");
			}

			for (int i = 1; i < 8; i += 2) {
				array[i][7] = new Piece(false, this, i, 7, "pawn");
			}
		}
	}

	/*Gets the piece at position (x, y) on the board, or null if there 
	is no piece. If (x, y) are out of bounds, returns null.
	*/
	public Piece pieceAt(int x, int y) {
	    
	    if (x > 7 || x < 0 || y > 7 || y < 0) {
	        return null;
	    } 
	    return array[x][y];
	    }

	public boolean canSelect(int x, int y) {
	    if (pieceAt(x, y) == null) {
	        if (haveMoved == false && selectedPiece != null && 
	            validMove(xInitialMove(selectedPiece), yInitialMove(selectedPiece), x, y)) {
	            return true;
	        } else if(haveMoved == true && selectedPiece.hasCaptured() && validMove(xInitialMove(selectedPiece), yInitialMove(selectedPiece), x, y)) {
	            return true;
	        } else if (selectedPiece != null && selectedPiece.hasCaptured() && 
	          validMove(xInitialMove(selectedPiece), yInitialMove(selectedPiece), x, y)) {
	            return true;
	        }
	    } else {
	        if (whichPlayer != pieceAt(x, y).side()) {
	            return false;
	        }
	        if (selectedPiece == null) {
	            return true;
	        } else if (haveMoved == false) {
	            return true;
	        }
	    } return false;
	}

	public void select(int x, int y) {
	    if (array[x][y] == null && selectedPiece != null) {
            selectedPiece.move(x, y);
            haveMoved = true;
	    } else {
	        selectedPiece = array[x][y];
	    }
	}
	
	//make sure to change validMove to private!!!!!!!!!!!!!!!!!!
	private boolean validMove(int xi, int yi, int xf, int yf) {
	    if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
	        return false;
	    } else if (array[xf][yf] != null) {
	        return false;
	    } else if ((xf + yf) % 2 != 0) {
	        return false;
	    } else if (array[xf][yf] == null) { 
	            if ((Math.abs(yf - yi) == 1) && Math.abs(xf - xi) == 1) { //checking for movement to one space
	                System.out.println(array[xi][yi]);
	                if (array[xi][yi].isKing()){ //if king, move to any space that's not occupied
	                    return true;
	                } else if (array[xi][yi].side() == 0 && (yf - yi) == 1) { //if fire, can only move up
	                        return true;
	                } else if ((array[xi][yi].side() == 1 && (yf - yi) == -1)){ //if water, can only move down
	                        return true;
	                    } return false;
	                } if ((Math.abs(yf - yi) == 2) && Math.abs(xf - xi) == 2) { //checking for capturing scenarios
	                    if (array[xi][yi].isKing()) { //if king, can jump both up and down the board
	                        if ((xf - xi == 2) && (yf - yi == 2)) {
	                            if (array[xf - 1][yf - 1] != null && array[xf - 1][yf - 1].side() != array[xi][yi].side()) {
	                                return true;
	                            }
	                        } else if ((xf - xi == -2) && (yf - yi == 2)) {
	                            if (array[xf + 1][yf - 1] != null && array[xf + 1][yf - 1].side() != array[xi][yi].side()){
	                                return true;
	                                }
	                        } else if ((xf - xi == 2) && (yf - yi == -2)) {
	                            if (array[xf - 1][yf + 1] != null && array[xf - 1][yf + 1].side() != array[xi][yi].side()) {
	                                return true;
	                            } 
	                        } else if ((xf - xi == -2) && (yf - yi == -2)) {
	                            if (array[xf + 1][yf + 1] != null && array[xf + 1][yf + 1].side() != array[xi][yi].side()){
	                                return true;
	                            }
	                        } 
	                    } else if (array[xi][yi].side() == 0) { //if piece that's capturing is fire, should move upward
	                        if ((xf - xi == 2) && (yf - yi == 2)) {
	                            if (array[xf - 1][yf - 1] != null && array[xf - 1][yf - 1].side() != array[xi][yi].side()) {
	                                return true;
	                            } 
	                        } else if ((xf - xi == -2) && (yf - yi == 2)) {
	                                if (array[xf + 1][yf - 1] != null && array[xf + 1][yf - 1].side() != array[xi][yi].side()){
	                                    return true;
	                                }
	                            } return false;
	                        } else if (array[xi][yi].side() == 1) { //if piece that's capturing is water, should move downward
	                            if ((xf - xi == 2) && (yf - yi == -2)) {
	                                if (array[xf - 1][yf + 1] != null && array[xf - 1][yf + 1].side() != array[xi][yi].side()) {
	                                    return true;
	                                }
	                        } else if ((xf - xi == -2) && (yf - yi == -2)) {
	                            if (array[xf + 1][yf + 1] != null && array[xf + 1][yf + 1].side() != array[xi][yi].side()){
	                                return true;
	                            }
	                        } return false;
	                    }
	                } 
	    } return false;
	}
	
	private int xInitialMove(Piece p) {
	    int tempX = 0;
	    for (int i = 0; i < array[0].length; i += 1) {
            for (int j = 0; j < array[0].length; j += 1) {
                if (p == array[i][j]) {
                  tempX = i;  
                }
            }
	    }
	    return tempX;
	}
	
	private int yInitialMove(Piece p) {
	    int tempY = 0;
        for (int i = 0; i < array[0].length; i += 1) {
            for (int j = 0; j < array[0].length; j += 1) {
                if (p == array[i][j]) {
                  tempY = j;  
                }
            }
        }
        return tempY;
	}
	
	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
	 * nothing. If p already exists in the current Board, first remove it
	 * from its initial position. If another piece already exists at (x, y), 
	 * p will replace that piece. (This method is potentially useful for 
	 * creating specific test circumstances.) 
	 */
	public void place(Piece p, int x, int y) {
	    if (p == null) {
	        return;
	    } for (int i = 0; i < array[0].length; i += 1) {
	        for (int j = 0; j < array[0].length; j += 1) {
	            if (array[i][j] == p) {
	                array[i][j] = null; //iterate through array to find Piece p and remove it
	            }
	        }
	    }
	    array[x][y] = p;
	}

	public Piece remove(int x, int y) {
	    if (pieceAt(x, y) == null) {
	        System.out.println("Coordinates out of bounds!"); //print statement?
	        return null;
	    }
	    if (array[x][y] == null) {
	        System.out.println("No piece to return!"); //more print statements??
	        return null;
	    }
	    Piece temp = array[x][y];
	    array[x][y] = null;
	    return temp;
	    }
	
	public boolean canEndTurn() {
	    if (selectedPiece != null) {
	        if (haveMoved == true || selectedPiece.hasCaptured()) {
	            return true;
	    } else {
	        return false;
	    }
	    } return false;
	}

	public void endTurn() {
	    haveMoved = false;
	    selectedPiece = null;
	    if (whichPlayer == 1) {
	        whichPlayer = 0;
	        System.out.println(whichPlayer);
	    } else {
	        whichPlayer = 1;
	        System.out.println(whichPlayer);
	    }
	}

	public String winner() {
	    if (waterPieceCounter == 0 && firePieceCounter == 0) {
	        return "No one";
	    } else if (waterPieceCounter == 0) {
	        return "Fire";
	    } else if (firePieceCounter == 0) {
	        return "Water";
	    } else {
	        return null;
	    }
	}

	private static String fireChecker(Piece p) {
		if (p.isFire()) {
			return "fire";
		} else {
			return "water";
		}
	}
	
	private static String isBombShield(Piece p) {
	    
	    if (p.isBomb()) {
	        return "bomb";
	    }
	    
	    if (p.isShield()) {
	        return "shield";
	    }
	    
	    return "pawn";
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	if (array[i][j] == selectedPiece) {
                	    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (array[i][j] != null) {
                	StdDrawPlus.picture(i + .5, j + .5, "img/" + 
                	isBombShield(array[i][j]) + "-" + fireChecker(array[i][j]) + ".png", 1, 1);
                }
            }
        }
    }

	public static void main(String args[]) {
		Board board = new Board(Boolean.parseBoolean(args[0]));
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		board.drawBoard(N);
		while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x,(int) y)) {
                    board.select((int) x, (int) y);
                    System.out.println("poop");
                }
            }
            StdDrawPlus.show(100);
		}
	}
}