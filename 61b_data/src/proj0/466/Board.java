/** 
 *  @author Natsuki Takahari
 */

public class Board {

	private int prevX;
	private int prevY;
	private Piece selected;
	private int player = 0;
	private int Moved = 0;
    private Piece[][] set = new Piece[8][8];

	/** Starts a GUI supported version of the game. */
	public static void main(String args[]) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);

        while(true) {
        	b.drawPieces();

            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();

                if (b.canSelect(x, y)) {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                	StdDrawPlus.filledSquare(x + .5, y + .5, .5);                	
                	b.select(x, y);
                }
            }
            else if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            	else if (b.winner() != null) {
					System.out.println(b.winner());
					break;
				}        	
            }
            
            StdDrawPlus.show(100);
        }
	}

	/** The constructor for Board.
		If shouldBeEmpty is true: initliaze empty Board,
		otherwise: initalize Board with default configuration.
		Note: empty Board useful for testing purposes. */
	public Board(boolean shouldBeEmpty) {
		// Initialize empty board.
		if (shouldBeEmpty == true) {
			emptyBoard(8);
		}

		// Initialize default board.
		else {
			for (int i = 0; i < 8; i += 1) {
				for (int j = 0; j < 8; j += 1) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
						if (j == 0) {
							set[i][j] = new Piece(true, this, i, j, "Default");
							// drawPieces(i, j);
						}
						if (j == 1) {
							set[i][j] = new Piece(true, this, i, j, "Shield");
							// drawPieces(i, j);
						}
						if (j == 2) {
							set[i][j] = new Piece(true, this, i, j, "Bomb");
							// drawPieces(i, j);
						}
						if (j == 5) {
							set[i][j] = new Piece(false, this, i, j, "Bomb");
							// drawPieces(i, j);;
						}
						if (j == 6) {
							set[i][j] = new Piece(false, this, i, j, "Shield");
							// drawPieces(i, j);
						}
						if (j == 7) {
							set[i][j] = new Piece(false, this, i, j, "Default");
							// drawPieces(i, j);
						}
					}
					else {
						StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
				}
			}
		}
	}

	private void emptyBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
            }
        }
    }

    /** A method to draw piece at set[x][y]. */
    private void drawPieces() {
    	for (int i = 0; i < 8; i += 1) {
    		for (int j = 0; j < 8; j += 1) {
		    	if (set[i][j] != null) {
		    		if (set[i][j].isFire() == true) {
		        		if (set[i][j].isBomb() == true) {
		            		if (set[i][j].isKing() == true) {
		             			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);               			
		            		}
		            		else {
		            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);                			
		            		}
		        		}
		        		else if (set[i][j].isShield() == true) {
		            		if (set[i][j].isKing() == true) {
		             			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);               			
		            		}
		            		else {
		            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);                			
		            		}
		        		}
		        		else {
		            		if (set[i][j].isKing() == true) {
		             			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		            		}
		            		else {
		            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);                			
		            		}
		        		}
		        	}
		        	else if (set[i][j].isFire() == false) {
		        		if (set[i][j].isBomb() == true) {
		            		if (set[i][j].isKing() == true) {
		             			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);               			
		            		}
		            		else {
		            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);                			
		            		}                			
		        		}
		        		else if (set[i][j].isShield() == true) {
		        			if (set[i][j].isKing() == true) {
		             			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);               			
		            		}
		            		else {
		            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);                			
		            		}
		        		}
		        		else {
		        			if (set[i][j].isKing() == true) {
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

	/** Gets the piece position (x, y) on the board OR
		null if there is no piece.
		If (x, y) are out of bounds, return null. */
	public Piece pieceAt(int x, int y) {
		if (x >= 0 && y >= 0 && x < 8 && y < 8) {
			return set[x][y];
		} return null;
	}

	/** Returns true if there is a piece at the position (x, y) and can be selected. */
	public boolean canSelect(int x, int y) {
		if ((x + y) % 2 == 0) {

			// Piece at position (x, y) exists and is the player's piece.
			if (set[x][y] != null && set[x][y].side() == player) {
				if (selected != set[x][y]) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);					
				}
				prevX = x;
				prevY = y;

				if (Moved == 0) {
					return true;
				} return false;
			}

			// Piece at position (x, y) is blank and previous piece can be moved there.
			else if (set[x][y] == null  && selected != null && validMove(prevX, prevY, x, y)) {
				prevX = x;
				prevY = y;				
				if (Moved == 0) {
					return true;
				}
				else {
					if (set[prevX][prevY].hasCaptured()) {
						if (Math.abs(prevX - x) == 2 && Math.abs(prevY - y) == 2) {
							return true;
						}
					}
				}
			}
		} return false;
	}

	/** Returns true if the piece at (xi, xi) can either
		1. move to (xf, yf) OR
		2. capture to (xf, yf). */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((xf + yf) % 2 == 0) {
			if (set[xi][yi] != null && set[xf][yf] == null && xi != xf) {
				// Moving to (xf, yf).
				if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 &&
					pieceAt(xi, yi).hasCaptured() == false) {
					if (set[xi][yi].isKing() == true) {
						return true;
					}
					else if (set[xi][yi].isFire() == true && yi < yf) {
						return true;
					}
					else if (set[xi][yi].isFire() == false && yi > yf) {
						return true;
					}
					return false;
				}

				// Capturing to (xf, yf).
				else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
					if (set[(xi + xf) / 2][(yi + yf) / 2] != null) {
						if (set[xi][yi].isFire() != set[(xi + xf) / 2][(yi + yf) / 2].isFire()) {
							if (set[xi][yi].isKing() == true) {
								return true;
							}
							else if (set[xi][yi].isFire() == true && yi < yf) {
								return true;
							}
							else if (set[xi][yi].isFire() == false && yi > yf) {
								return true;
							}
						}							
					}
				}
			}		
		} return false;
	}

	/** Selects the piece at (x, y) if possible. 
		Optional: color the background of the selected square
		via the pen color function. */
	public void select(int x, int y) {

			// Selecting an existing piece.
			if (set[prevX][prevY] != null && pieceAt(x, y) != null) {
				selected = set[x][y];
			}

			// Selecting a piece to move to.
			else if (pieceAt(x, y) == null && selected != null) {
				selected.move(x, y);
				Moved += 1;
				selected = set[x][y];		
			} 
			prevX = x;
			prevY = y;
		}

	/** Places p at (x, y). If (x, y) is out of bounds or if p is null, do nothing.
		If another pieces exists at (x, y), p will replace that piece. */
	public void place(Piece p, int x, int y) {

		// Out of bounds or null p.// Piece p exists in the current Board.
		if (x >= 0 && y >= 0 && x < 8 && x < 8 && p != null) {
			set[x][y] = p;
		} return;
	}

	/** Executes a remove. Returns the piece that was removed.
		If the input (x, y) is out of bounds OR there is no piece at (x, y)
		return null and print an appropriate message. */
	public Piece remove(int x, int y) {
		Piece removed;

		// (x, y) out of bounds.
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.out.println("Out of bounds.");
			return null;
		}

		// No piece at (x, y).
		else if (set[x][y] == null) {
			System.out.println("No piece available.");
			return null;
		}

		// Piece at (x, y) exists.
		else {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);

			removed = pieceAt(x, y);
			set[x][y] = null;		
			return removed;
		}
	}

	/** Returns whether or not current player can end turn. */
	public boolean canEndTurn() {
		if (Moved != 0) {
			return true;
		}
		return false;
	}

	/** Handles switching of players. */
	public void endTurn() {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);			
		StdDrawPlus.filledSquare(prevX + .5, prevY + .5, .5);
		// drawPieces(x, y);

		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (set[i][j] != null) {
					set[i][j].doneCapturing();
				}
			}
		}

		player = Math.abs(player - 1);
		Moved = 0;
		selected = null;
	}

	/** Returns the winner of the game. */
	public String winner() {
		int FirePieces = 0;
		int WaterPieces = 0;

		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieceAt(i, j) != null) {
					if (set[i][j].isFire() == true) {
						FirePieces += 1;
					}
					else if (set[i][j].isFire() == false) {
						WaterPieces += 1;
					}
				}
			}
		}

		if (WaterPieces == 0 && FirePieces == 0) {
			return "No one";
		}
		else if (WaterPieces < FirePieces && WaterPieces == 0) {
			return "Fire";
		}
		else if (WaterPieces > FirePieces && FirePieces == 0) {
			return "Water";
		} return null;
	}
}