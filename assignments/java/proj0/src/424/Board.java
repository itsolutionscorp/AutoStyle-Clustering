public class Board {

	private Piece[][] pieces; 
	private boolean player = true; 
	// fire's turn if player is true
	// water's turn if player is false
	private Piece selectedPiece = null;
	private boolean moved = false;
	private int px = -1; // x-coords of selectedPiece
	private int py = -1; // y-coords of selectedPiece

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty != true) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (y == 0 && (x % 2 == 0)) {
						pieces[x][y] = new Piece(true, this, x, y, "pawn");
 					} else if (y == 1 && (x % 2 != 0)) {
 						pieces[x][y] = new Piece(true, this, x, y, "shield");
					} else if (y == 2 && (x % 2 == 0)) {
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
					} else if (y == 5 && (x % 2 != 0)) {
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
					} else if (y == 6 && (x % 2 == 0)) {
						pieces[x][y] = new Piece(false, this, x, y, "shield");
					} else if (y == 7 && (x % 2 != 0)) {
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
					}
				}
			}
		}
	}

	private boolean outOfBounds(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return true;
		} else {
			return false;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y) || pieces[x][y] == null) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public void place(Piece p, int x, int y) {
		if (p != null && !outOfBounds(x, y)) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) { 
					if (pieces[i][j] == p) {
						pieces[i][j] = null;
					}
				}
			}

			pieces[x][y] = p;

		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (selectedPiece != null) {
			if (!selectedPiece.isKing()) {
				if (selectedPiece.isFire()) {
					if (((xf == xi + 1) || (xf == xi - 1)) && (yf == yi + 1)) { // regular fire move
						return true;
					} else if (((xf == xi + 2) || (xf == xi - 2)) && (yf == yi + 2)) { // capture fire move
						if ((pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) && !pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					if (((xf == xi + 1) || (xf == xi - 1)) && (yf == yi - 1)) { // regular water move
						return true;				
					} else if (((xf == xi + 2) || (xf == xi - 2)) && (yf == yi - 2)) { // capture water move
						if ((pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			} else {
				if (((xf == xi + 1) || (xf == xi - 1)) && ((yf == yi + 1) || (yf == yi - 1))) { // regular king move
					return true;
				} else if (((xf == xi + 2) || (xf == xi - 2)) && ((yf == yi + 2) || (yf == yi - 2))) { // capture king move
					if (selectedPiece.isFire()) {
						if ((pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) && !pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
							return true;
						} else {
							return false;
						}		
					} else {
						if ((pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private boolean canCaptureAgain(int x, int y) {
		if ((x == px + 2 || x == px - 2) && (y == py + 2 || y == py - 2)) {
			return validMove(px, py, x, y);
		} else {
			return false;
		}
	}

	public boolean canSelect(int x, int y) {
		if (outOfBounds(x, y)) {
			return false;
		} else {
			// SQUARE WITHOUT A PIECE -- MUST HAVE SELECTED ALREADY!
			if (pieces[x][y] == null) {
				if (selectedPiece == null) {
					return false;
				} else {
					if (((selectedPiece.isFire() && player == true) || (!selectedPiece.isFire() && player == false)) && moved == false) {
						return validMove(px, py, x, y);
					} else {
						return (moved && canCaptureAgain(x, y));
					}
				}
			} else {
				// SQUARE WITH A PIECE -- HAVE NOT SELECTED YET
				if (selectedPiece != null) {
					if (player == true && moved == false && pieces[x][y].isFire()) {
						return true;
					} else if (player == false && moved == false && !pieces[x][y].isFire()) {
						return true;
					} else {
						return false;
					}
				} else {
					if (player == true && pieces[x][y].isFire()) {
						return true;
					} else if (player == false && !pieces[x][y].isFire()) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

	public void select(int x, int y) {
		if (selectedPiece != null) {
			if (pieces[x][y] == null) { // selected empty square
				selectedPiece.move(x, y);
				moved = true;
				if (selectedPiece.hasCaptured()) {
					px = x;
					py = y;
				}
			} else {
				selectedPiece = pieces[x][y];
				px = x;
				py = y;
			}
		} else {
			selectedPiece = pieces[x][y];
			px = x;
			py = y;

		}
	}

	public Piece remove(int x, int y) {
		if (outOfBounds(x, y)) {
			System.out.println("Position selected is out of bounds.");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("There is no piece there!");
			return null;
		} else {
			Piece removedPiece = pieces[x][y];
			pieces[x][y] = null;
			return removedPiece;
		}
	}

	public boolean canEndTurn() {
		if (moved) {
			return true;
		} else {
			return false;
		}
	}

	private void switchPlayer() {
		if (player == true) {
			player = false;
		} else {
			player = true;
		}
	}

	public void endTurn() {
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		moved = false;
		px = -1;
		py = -1;
		switchPlayer();
	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (Piece[] itemArray : pieces) {
			for (Piece item : itemArray) {
				if (item != null) {
					if (item.isFire()) {
						fireCount += 1;
					} else {
						waterCount += 1;
					}
				}
			}
		}

		if (waterCount == 0 && fireCount == 0) {
			return "No one";
		} else if (fireCount == 0) {
			return "Water";
		} else if (waterCount == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	if (pieces[i][j] == selectedPiece && selectedPiece != null) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }               
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece item = pieces[i][j];

                if (item != null) {
		            if (item.isFire()) {
		                if (item.isBomb()) {
		                	if (item.isKing()) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		                	} else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		                	}
		                } else if (item.isShield()) {
		                	if (item.isKing()) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		                	} else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		                	}
		                } else {
		                	if (item.isKing()) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		                	} else {
		               			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		            		}
		              	}
		            } else {
		           		if (item.isBomb()) {
		           			if (item.isKing()) {
	              				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		                	} else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		                	}
		                } else if (item.isShield()) {
		                	if (item.isKing()) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		                	} else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		                	}
		                } else {
		                	if (item.isKing()) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		                	} else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
                		}
                	}
            	} 
        	}  	  	
  		}
  	}

	public static void main(String args[]) {
		Board gameBoard = new Board(Boolean.parseBoolean(args[0]));
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {

            gameBoard.drawBoard(N);

            if (gameBoard.winner() != null) {
            	gameBoard.drawBoard(N);
            	StdDrawPlus.show(10);
            	System.out.println(gameBoard.winner());
            	return;
            }

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (gameBoard.canSelect((int) x, (int) y)) {
                	gameBoard.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (gameBoard.canEndTurn()) {
            		gameBoard.endTurn();
            	}
            }
                      
            StdDrawPlus.show(10);

		}			
	}
}
		