/**
 *  @author Rayshone Fu
 *  Pieces
 */

public class Board {
	/** Location of pieces. */
	private Board b;
	private Piece[][] boardArray;
	private boolean myTurn = true; // Needs to switch true/false at end of each turn
	private boolean pieceSelected = false; // Needs to be set to false at end of each turn
	private int pieceSelectedX = -10; // Needs to reset after end of turn
	private int pieceSelectedY = -10; // Needs to reset after end of turn
	private boolean hasMoved = false; // reset to false every turn
	private Piece pointer;

	public static void main(String args[]) {
		/** Starts GUI of game */
		int N = 8;
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

		while (true) {
			drawBoard(N);
			Board b = new Board(false);
			if (StdDrawPlus.mousePressed()) {
                double xVar = StdDrawPlus.mouseX();
                double yVar = StdDrawPlus.mouseY();
                int x = (int) xVar;
                int y = (int) yVar;
                if (b.canSelect(x, y)) {
                	b.select(x, y);	
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
            if (StdDrawPlus.isSpacePressed() == true) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            b.updateGUI();
            b.winner();
			StdDrawPlus.show(20);
		}
	}

	private static void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {                 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
	}

	public Board(boolean shouldBeEmpty) {
		/** The constructor for Board. If true, 
		 * initializes an empty Board.
		 */
		boardArray = new Piece[8][8];
		if (shouldBeEmpty == false) {
			generatePieces();
			updateGUI();
		}
	}

	private void generatePieces() {
		for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if ((i + j) % 2 == 0) {
    // fill in the pieces in 2D array
			    	if (j == 0) {
			    		boardArray[i][j] = new Piece(true, this, i, j, "pawn");
			    	} else if (j == 1) {
			    		boardArray[i][j] = new Piece(true, this, i, j, "shield");
			    	} else if (j == 2) {
			    		boardArray[i][j] = new Piece(true, this, i, j, "bomb");
			    	} else if (j == 5) {
			    		boardArray[i][j] = new Piece(false, this, i, j, "bomb");
			    	} else if (j == 6) {
			    		boardArray[i][j] = new Piece(false, this, i, j, "shield");
			    	} else if (j == 7) {
			    		boardArray[i][j] = new Piece(false, this, i, j, "pawn");
			    	}
			    }
        	}
		}
	}

	private void updateGUI() {
		for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			// itterate throughout whole board and draw each piece
    			if (boardArray[i][j] != null) {
	    			if (boardArray[i][j].isFire()) {
	    				// if fire:
	    				if (boardArray[i][j].isBomb()) {
	    					if (boardArray[i][j].isKing()) {
	    						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	    					}
		    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	    				} else if (boardArray[i][j].isShield()) {
	    					if (boardArray[i][j].isKing()) {
	    						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	    					}
	    					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	    				} else {
	    					if (boardArray[i][j].isKing()) {
	    						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	    					}
	    					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	    				}
	    			} else {
	    				//if water:
	    				if (boardArray[i][j].isBomb()) {
	    					if (boardArray[i][j].isKing()) {
	    						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	    					}
		    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	    				} else if (boardArray[i][j].isShield()) {
	    					if (boardArray[i][j].isKing()) {
	    						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	    					}
	    					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	    				} else {
	    					if (boardArray[i][j].isKing()) {
	    						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	    					}
	    					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	    				}
	    			}
	       		}
	       	}
    	}
	}

	public Piece pieceAt(int x, int y) {
		// Gets piece at position (x, y) on the board.
		// null if there is no piece or (x,y) is out of bounds.
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			return null;
		}
		return boardArray[x][y];
	}

	public boolean canSelect(int x, int y) {
		// First check if the square at (x, y) is empty or has a piece
		if (x < 0 || x > 8 || y < 0 || y > 8) {
			return false;
		}
		if (boardArray[x][y] == null) {
			if (pieceSelected && hasMoved == false && validMove(pieceSelectedX, pieceSelectedY, x, y)) {
				return true;
			} else if (pieceSelected && validMove(pieceSelectedX, pieceSelectedY, x, y) &&
					   boardArray[pieceSelectedX][pieceSelectedY].hasCaptured() &&
					   (boardArray[x][y] == boardArray[pieceSelectedX + 2][pieceSelectedY + 2] ||
					   	boardArray[x][y] == boardArray[pieceSelectedX + 2][pieceSelectedY - 2] ||
					   	boardArray[x][y] == boardArray[pieceSelectedX - 2][pieceSelectedY - 2] ||
					   	boardArray[x][y] == boardArray[pieceSelectedX - 2][pieceSelectedY + 2])) {
				return true;
			} else {
				return false;
			}
		} else if (boardArray[x][y] != null && myTurn == boardArray[x][y].isFire() &&
			      (pieceSelected == false || (pieceSelected == true && hasMoved == false))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validMove(int xi, int yi, int xf, int yf) {
		// true if piece at (xi, yi) can move to (xf, yf) or
		// capture to (xf, yf)
		if (xf < 0 || xf > 7 || yf < 0 || yf > 7 || boardArray[xf][yf] != null) {
			return false;
		} else if (boardArray[xi][yi].isKing()) { // checking valid moves for king pieces
			if ((xf == xi - 1 || xf == xi + 1) && (yf == yi - 1 || yf == yi + 1)) {
				return true;
			} else if ( (xf == xi + 2 && yf == yi + 2) && (boardArray[xi + 1][yi + 1] != null) &&
						(boardArray[xi][yi].isFire() != boardArray[xi + 1][yi + 1].isFire()) ) {
				return true;
			} else if ( (xf == xi + 2 && yf == yi - 2) && (boardArray[xi + 1][yi - 1] != null) &&
						(boardArray[xi][yi].isFire() != boardArray[xi + 1][yi - 1].isFire()) ) {
				return true;
			} else if ( (xf == xi - 2 && yf == yi - 2) && (boardArray[xi - 1][yi - 1] != null) &&
						(boardArray[xi][yi].isFire() != boardArray[xi - 1][yi - 1].isFire()) ) {
				return true;
			} else if ( (xf == xi - 2 && yf == yi + 2) && (boardArray[xi - 1][yi + 1] != null) &&  
						(boardArray[xi][yi].isFire() != boardArray[xi - 1][yi + 1].isFire()) ) {
				return true;
			}
		} else if ( ((xf == xi - 1 || xf == xi + 1) && yf == yi + 1 && boardArray[xi][yi].isFire()) || // checking valid moves for non-king
			        ((xf == xi - 1 || xf == xi + 1) && yf == yi - 1 && boardArray[xi][yi].isFire() == false) ) {
			return true;
		} else if ( (xf == xi + 2 && yf == yi + 2) && (boardArray[xi + 1][yi + 1] != null) &&
					(boardArray[xi][yi].isFire() != boardArray[xi + 1][yi + 1].isFire())   &&
					(boardArray[xi][yi].isFire())) {
			return true;
		} else if ( (xf == xi + 2 && yf == yi - 2) && (boardArray[xi + 1][yi - 1] != null) &&
					(boardArray[xi][yi].isFire() != boardArray[xi + 1][yi - 1].isFire())   &&
					(boardArray[xi][yi].isFire() == false)) {
			return true;
		} else if ( (xf == xi - 2 && yf == yi - 2) && (boardArray[xi - 1][yi - 1] != null) &&
					(boardArray[xi][yi].isFire() != boardArray[xi - 1][yi - 1].isFire())   &&
					(boardArray[xi][yi].isFire() == false)) {
			return true;
		} else if ( (xf == xi - 2 && yf == yi + 2) && (boardArray[xi - 1][yi + 1] != null) &&  
					(boardArray[xi][yi].isFire() != boardArray[xi - 1][yi + 1].isFire()) &&
					(boardArray[xi][yi].isFire())) {
			return true;
		}
		return false;
	}


	public void select(int x, int y) {
		// Selects piece at position if possible.
		// Selected piece is highlighted.
			// StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			// StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			if (pieceSelected == false || (pieceSelected && hasMoved == false)) {
				pieceSelected = true;
				pieceSelectedX = x;
				pieceSelectedY = y;
			} else if (pieceSelected && hasMoved == false &&
					   boardArray[x][y] == null) {
				hasMoved = true;
				boardArray[pieceSelectedX][pieceSelectedY].move(x, y);
			} else if (pieceSelected && hasMoved && 
					   boardArray[pieceSelectedX][pieceSelectedY].hasCaptured()) {
				boardArray[pieceSelectedX][pieceSelectedY].move(x, y);
			}
	}

	public void place(Piece p, int x, int y) {
		// Places p at the given coordinates.
		// Does nothing if out of bounds or p is null
		if (x >= 0 && x < 8 && y >= 0 && y < 8 && p != null) {
			boardArray[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		// Removes a piece from play. Returns value is the piece.
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("OUT OF BOUNDS, REMOVE DOES NOT APPLY.");
		} else if (boardArray[x][y] == null) {
			System.out.println("NO PIECE TO REMOVE.");
		}
		pointer = boardArray[x][y];
		boardArray[x][y] = null;
		return pointer;
	}

	public boolean canEndTurn() {
		/** Checks to see if player can end turn.
		 *  A piece must have moved or performed a capture.
		 */
		if (hasMoved || boardArray[pieceSelectedX][pieceSelectedY].hasCaptured()) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		/** Called at end of each turn.
		 *  Switches players turn and anythings else that should
		 *  happen at the end of a turn.
		 */
		myTurn = false;
		boardArray[pieceSelectedX][pieceSelectedY].doneCapturing();
		pieceSelectedX = 8;
		pieceSelectedY = 8;
		hasMoved = false;
		pieceSelected = false;

	}

	public String winner() {
		/** Returns the winner of game: "Fire" or "Water"
		 *  Can result in tie "No one"
		 *  null if game is not over yet.
		 */
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardArray[i][j] != null) {
					if (boardArray[i][j].isFire()) {
						numFire += 1;
					} else {
						numWater += 1;
					}
				}
			}
		}
		if (numWater == 0 && numFire == 0) {
			return "No one";
		} else if (numWater == 0) {
			return "Fire";
		} else if (numFire == 0) {
			return "Water";
		} else {
			return null;
		}
	}
}
















