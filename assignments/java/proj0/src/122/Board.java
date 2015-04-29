import java.awt.Color;

public class Board {

	// <=====INSTANCE VARIABLES=====>
	private static Board newboard;
	private Piece[][] pieces;
	private static final Color WATER = new Color(102, 178, 255);
	private static final Color FIRE = new Color(255, 178, 102);
	private static final int N = 8;
	private int side = 1;
	private Piece selected;
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;


	// <====DRAWING THE MAIN BOARD====>
	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(WATER);
                else                  StdDrawPlus.setPenColor(FIRE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (newboard.pieces[i][j] == newboard.selected && newboard.pieces[i][j] != null) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (newboard.pieces[i][j] != null) {
                	drawPiece(newboard.pieces[i][j], i, j);
                }
            }
        }
    }


    // <===== BOARD CONSTRUCTOR =====>
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
			makePieces();
		}
	}


	// <===== INITIALIZING PIECES =====>
    private void makePieces() {

		// <=== FIRE ===>
		pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		pieces[6][2] = new Piece(true, this, 6, 2, "bomb");


		// <=== WATER ===>
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieces[1][5] = new Piece(false, this, 1, 5, "bomb");

    }
    private void makeTester() {

		// <=== FIRE ===>
		pieces[2][6] = new Piece(true, this, 2, 6, "pawn");

		// <=== WATER ===>
		pieces[4][4] = new Piece(false, this, 4, 4, "pawn");
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");

    }


    // <===== DRAWING PIECES =====>
	private static void drawPiece(Piece p, int x, int y) {

		String path = null;

		// <=== FIRE ===>
		if (p.isBomb() && p.isFire() && !p.isKing()) {
			path = "img/bomb-fire.png";
		} else if (p.isBomb() && p.isFire() && p.isKing()) {
			path = "img/bomb-fire-crowned.png";
		} else if (p.isShield() && p.isFire() && !p.isKing()) {
			path = "img/shield-fire.png";
		} else if (p.isShield() && p.isFire() && p.isKing()) {
			path = "img/shield-fire-crowned.png";
		} else if (p.isFire() && !p.isKing()) {
			path = "img/pawn-fire.png";
		} else if (p.isFire() && p.isKing()) {
			path = "img/pawn-fire-crowned.png";
		}

		// <=== WATER ===>
		else if (p.isBomb() && !p.isFire() && !p.isKing()) {
			path = "img/bomb-water.png";
		} else if (p.isBomb() && !p.isFire() && p.isKing()) {
			path = "img/bomb-water-crowned.png";
		} else if (p.isShield() && !p.isFire() && !p.isKing()) {
			path = "img/shield-water.png";
		} else if (p.isShield() && !p.isFire() && p.isKing()) {
			path = "img/shield-water-crowned.png";
		} else if (!p.isFire() && !p.isKing()) {
			path = "img/pawn-water.png";
		} else if (!p.isFire() && p.isKing()) {
			path = "img/pawn-water-crowned.png";
		}

		StdDrawPlus.picture(x + .5, y + .5, path, 1, 1);
	}



	// ABOVE - Drawing a board with initial parts.
	// =============================================================================
	// =============================================================================
	// BELOW - main functionalities.


	// <===== RETURNS A PIECE AT COORDINATES X, Y =====>
	public Piece pieceAt(int x, int y) {
		if ( x > 7 || x < 0 || y > 7 || y < 0 ) {
			System.out.println("Index out of bound");
			return null;
		}
		else if (pieces[x][y] != null) {
			return pieces[x][y];
		} return null;
	}


	// <===== RETURNS WHETHER A PLAYER CAN SELECT A SQUARE =====>
	public boolean canSelect(int x, int y) {

		// Selecting non-empty squares
		if (pieceAt(x, y) != null ) {
			if (side == pieceAt(x, y).side()) {
				if (selected == null || (oldX == newX && oldY == newY)) {
					return true;
				}
			}
		}

		// Selecting empty squares
		else if(pieceAt(x, y) == null ) {
			if (selected != null) {
				if (oldX == newX && oldY == newY) {
					if (validMove(oldX, oldY, x, y)) {
						return true;
					}
				}
				else if (selected.hasCaptured()) {
					if (validMove(newX, newY, x, y) && Math.abs(newX - x) == 2 && Math.abs(newY - y) == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}


	// <===== HELPER FOR CANSELECT =====>
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi) != null) {

			// NOT KINGS
			if (!pieceAt(xi, yi).isKing()) {

				// BASIC FIRE
				if (pieceAt(xi, yi).isFire()) {

					// EMPTY SQUARE
					if (pieceAt(xf, yf) == null) {

						// <== MOVING ==>
						if ((yf == (yi + 1)) && ((xf - xi == 1) || (xi - xf == 1))) {
							return true;
						}

						// <== CAPTURING ==>
						else if (yf == yi + 2) {

							if (xf == xi + 2) {
								if (pieceAt(xi+1, yi+1)!= null) {
									if (!pieceAt(xi+1, yi+1).isFire()) {
										return true;
									}
								}
							}

							else if (xf == xi - 2) {
								if (pieceAt(xi - 1, yi + 1)!= null) {
									if (!pieceAt(xi - 1, yi + 1).isFire()) {
										return true;
									}
								}
							}
						}
					} 
				}

				// BASIC WATER
				else if (!pieceAt(xi, yi).isFire()) {

					// EMPTY SQUARE
					if (pieceAt(xf, yf) == null) {

						// <== MOVING ==>
						if ((yf == (yi - 1)) && ((xf - xi == 1) || (xi - xf == 1))) {
							return true;
						}

						// <== CAPTURING ==>
						else if (yf == yi - 2) {

							if (xf == xi + 2) {
								if (pieceAt(xi + 1, yi - 1)!= null) {
									if (pieceAt(xi + 1, yi - 1).isFire()) {
										return true;
									}
								}
							}

							else if (xf == xi - 2) {
								if (pieceAt(xi - 1, yi - 1)!= null) {
									if (pieceAt(xi - 1, yi - 1).isFire()) {
										return true;
									}
								}
							}
						}
					} 
				}
			}

		
		
			// KINGS
			else if (pieceAt(xi, yi).isKing()) {
				// <== MOVING ==>
				if ((yf == (yi + 1) || yf == (yi - 1)) && ((xf - xi == 1) || (xi - xf == 1))) {
					if (pieceAt(xf, yf) == null) {
						return true;
					}
				}

				// <== CAPTURING ==>
				else if (pieceAt(xf, yf) == null) {
					if ((yf == yi + 2) && (xf == xi + 2) && (pieceAt(xi + 1, yi + 1) != null) && ((pieceAt(xi + 1, yi + 1).isFire() != pieceAt(xi, yi).isFire()))) {
						return true;
					}
					if ((yf == yi - 2) && (xf == xi + 2) && (pieceAt(xi + 1, yi - 1) != null) && ((pieceAt(xi + 1, yi - 1).isFire() != pieceAt(xi, yi).isFire()))) {
						return true;
					}
					if ((yf == yi + 2) && (xf == xi - 2) && (pieceAt(xi - 1, yi + 1) != null) && ((pieceAt(xi - 1, yi + 1).isFire() != pieceAt(xi, yi).isFire()))) {
						return true;
					}
					if ((yf == yi - 2) && (xf == xi - 2) && (pieceAt(xi - 1, yi - 1) != null) && ((pieceAt(xi - 1, yi - 1).isFire() != pieceAt(xi, yi).isFire()))) {
						return true;
					}
				} 
			}
		} return false;
	}




	// <===== SELECTING AN EMPTY OR NON-EMPTY SQUARE =====>
	public void select(int x, int y) {

		// <=== THE SQUARE CONTAINS A PIECE ===>
		if (pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
			oldX = x;
			oldY = y;
			newX = x;
			newY = y;
		} 

		// <=== THE SQUARE DOES NOT CONTAIN A PIECE, EXECUTE PLACE ===>
		else {
			selected.move(x, y);
		}
	}

	// <===== PLACING A PIECE P AT (X, Y) =====> 
	public void place(Piece p, int x, int y) {
		if (p != null) {
			pieces[x][y] = p;
			newX = x;
			newY = y;
		}
	}

	// <===== REMOVES ANY PIECE =====>
	public Piece remove(int x, int y) {
		if ( x > 7 || x < 0 || y > 7 || y < 0 ) {
			System.out.println("Index out of bound");
			return null;
		}
		Piece removed = pieceAt(x, y);
		pieces[x][y] = null;
		return removed;
	}


	public boolean canEndTurn() {
		if (selected != null) {
			if (selected.hasCaptured() || (oldX != newX || oldY != newY)) {
			 	return true;
			}
		}
		return false;
	}


	public void endTurn() {

		// <== SWITCHING PLAYERS ==>
		if (side == 0) {
			side = 1;
		} else {
			side = 0;
		}

		// 
		selected.doneCapturing();

		// <== EMPTYING SELECTED PIECE ==>
		oldX = -1;
		oldY = -1;
		newY = -1;
		newX = -1;
		selected = null;
	}


	// "Fire" or "Water" or "No one"
	public String winner() {
		int numOfWater = 0;
		int numOfFire = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						numOfFire += 1;
					}
					else if (!pieceAt(i, j).isFire()) {
						numOfWater += 1;
					}
				}
			}
		}
		if (numOfWater == 0 && numOfFire != 0) {
			return "Fire";
		}
		else if (numOfWater != 0 && numOfFire == 0) {
			return "Water";
		}
		else if (numOfWater == 0 && numOfFire == 0) {
			return "No one";
		}
		return null;
	}


	public static void main(String[] args) {
		newboard = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
              	if (newboard.canSelect(x, y)) {
              		newboard.select(x, y);
              	}
            }
            else if (StdDrawPlus.isSpacePressed()) {
            	if (newboard.canEndTurn()) {
            		newboard.endTurn();
            	}
            }
            else if (StdDrawPlus.isNPressed()) {
            	newboard = new Board(false);
            }            
            StdDrawPlus.show(100);
            newboard.winner();
        }

	}
}