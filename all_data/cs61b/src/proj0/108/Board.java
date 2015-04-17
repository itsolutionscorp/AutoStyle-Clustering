public class Board {

	private int boardSize = 8;
	//private static Board board;   // Double check: used static here!!
	private Piece[][] pieces;
	private boolean playerTurn = true; // true for Fire Pieces; false for Water Pieces
	private boolean hasSelected = false;
	private boolean moved = false;
	private boolean captured = false;
	private int xPre;
	private int yPre;
	private int fireCounter = 0;
	private int waterCounter = 0;
	private String finalWinner = null;

	public static void main(String[] args) {
		Board board = new Board(false);

        while (board.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();

				if (board.canSelect(x, y) == true){
                	board.select(x, y);
                	StdDrawPlus.show(20);
            	}
       		}

            if (board.canEndTurn()) {
            	if (StdDrawPlus.isSpacePressed()) {
            	    board.endTurn();
               		StdDrawPlus.show(20);        
	   			} 
            }
        }
        System.out.println(board.finalWinner);
	}

    private void drawBoard(int N) {
    	pieces = new Piece[N][N];
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        int counter = 0;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                	if (counter < 4) {
                		Piece pawnFire = new Piece(true, this, i, j, "pawn");
                		this.pieces[i][j] = pawnFire;                		
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	} else if (counter < 8) {
                		Piece shieldFire = new Piece(true, this, i, j, "shield");
                		this.pieces[i][j] = shieldFire;
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	} else if (counter < 12) {
                		Piece bombFire = new Piece(true, this, i, j, "bomb");
                		this.pieces[i][j] = bombFire;
                 		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	} else if (counter < 20) {
                		this.pieces[i][j] = null;
                	} else if (counter < 24) {
                		Piece bombWater = new Piece(false, this, i, j, "bomb");
                		this.pieces[i][j] = bombWater;
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	} else if (counter < 28) {
						Piece shieldWater = new Piece(false, this, i, j, "shield");
						this.pieces[i][j] = shieldWater;
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	} else if (counter < 32) {
                		Piece pawnWater = new Piece(false, this, i, j, "pawn");
                		this.pieces[i][j] = pawnWater;
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);                		
                	}
                	counter += 1;
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private void drawEmptyBoard(int N){
    	pieces = new Piece[N][N];
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

    	for (int j = 0; j < N; j += 1) {
    		for (int i = 0; i < N; i += 1) {
    			pieces[i][j] = null;

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

    private void drawPiece(int x, int y) {
    	Piece piece = pieces[x][y];
    	if (piece != null) {
    		if (piece.isFire()){
    			if (piece.isBomb()) {
    				if (piece.isKing()){
                		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);    				
    				} else {
                		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                	}
    			} else if (piece.isShield()) {
    				if (piece.isKing()) {
                		StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);    				
    				} else {
                		StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
    				}
    			} else if (piece.isKing()) {  // pawn case
                		StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);    				
    			} else {
                		StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
    			}
    		} else {
    			if (piece.isBomb()) {
    				if (piece.isKing()){
                		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);    				
    				} else {
                		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                	}
    			} else if (piece.isShield()) {
    				if (piece.isKing()) {
                		StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);    				
    				} else {
                		StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
    				}
    			} else if (piece.isKing()) {  // pawn case
                		StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);    				
    			} else {
                		StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
    			}
    		} 
    	}
    }

	public Board (boolean shouldBeEmpty) {
		if (shouldBeEmpty == true) {
			// initializes an empty board
			drawEmptyBoard(boardSize);
		} else {
			// initializes a Board with the default configuration
			drawBoard(boardSize);
		}
	}

	private boolean checkBound(int x, int y) {
		if (x >= boardSize || y >= boardSize || x < 0 || y < 0) {
			return false;
		} else {
			return true;
		}
	}

	public Piece pieceAt (int x, int y) {
		if (checkBound(x, y)) {
			return pieces[x][y];
		} else {
			return null;
		}
	}

	// not yet implemented
	public boolean canSelect(int x, int y) {

		Piece targetSquare = pieceAt(x, y);
		// Can Select a sqaure with piece  and  it's corresponding player's turn
		if (targetSquare != null && targetSquare.isFire() == playerTurn) {
			if (hasSelected == false || (hasSelected == true && moved == false))  {
				return true;
			} else {
				return false;
			}		
		} else if (targetSquare == null) {
			if (hasSelected == true && moved == false && pieceAt(xPre, yPre) != null && validMove(xPre, yPre, x, y)) {
				return true;
			// Multi-captures: 
			} else if (moved == true && pieceAt(xPre, yPre) != null && pieces[xPre][yPre].hasCaptured() && validMove(xPre, yPre, x, y)) {
				 if (Math.abs(x - xPre) == 1) {
				 	return false;
				 } else {
					return true;
				 }
			} else {
				// System.out.println("Multi-captures.............");
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (checkBound(xf, yf) == false) {
			System.out.println("Error: input position (x, y) out of bounds.");
			return false;
		} else {
			Piece piece = pieceAt(xi, yi);
			// Fire Piece
			if (piece.isFire() == true) {
				// General move
				if (xf == xi + 1 || xf == xi - 1) {
					// King case
					if ((piece.isKing() == true) && (yf == yi + 1 || yf == yi - 1)) {
						return true;
					// Normal case
					} else if ((piece.isKing() == false) && (yf == yi + 1)) {
						return true;
					// Invalid y position
					} else {
						return false;
					}
				// Capture move
				} else if (xf == xi + 2 || xf == xi - 2) {
					// King case
					if ((piece.isKing() == true) && (yf == yi + 2 || yf == yi - 2) && pieceAt((xi + xf) /2, (yi + yf) / 2) != null && pieceAt((xi + xf) /2, (yi + yf) / 2).isFire() == false) {
						captured = true;
						return true;

					// Normal case
					} else if ((piece.isKing() == false) && (yf == yi + 2)
								&& pieceAt((xi + xf) /2, (yi + yf) / 2) != null && pieceAt((xi + xf) /2, (yi + yf) / 2).isFire() == false) {
						captured = true;
						return true;
					// Invalid y position
					} else {
						captured = false;
						return false;
					}
				// Invalid x position	
				} else {
					return false;
				}
			// Water Pieces
			} else if (piece.isFire() == false) {
				// General move
				if (xf == xi + 1 || xf == xi - 1) {
					// King case
					if ((piece.isKing() == true) && (yf == yi + 1 || yf == yi - 1)) {
						return true;
					// Normal case
					} else if ((piece.isKing() == false) && (yf == yi - 1)) {
						return true;
					// Invalid y position
					} else {
						return false;
					}
				// Capture move
				} else if (xf == xi + 2 || xf == xi - 2) {
					// King case
					if ((piece.isKing() == true) && (yf == yi + 2 || yf == yi - 2) && pieceAt((xi + xf) / 2, (yi + yf) / 2) != null && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() == true) {
						captured = true;
						return true;
					// Normal case
					} else if ((piece.isKing() == false) && (yf == yi - 2)
					           && pieceAt((xi + xf) /2, (yi + yf) / 2) != null && pieceAt((xi + xf) /2, (yi + yf) / 2).isFire() == true) {
						captured = true;
						return true;
					// Invalid y position
					} else {
						captured = false;
						return false;
					}
				// Invalid x position	
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public void select(int x, int y) {
		// Color the piece background to white when selected
		Piece target = pieces[x][y];
		// Select a square with piece:
		if (target != null){
			// First time select a piece during a turn
			if (hasSelected == false) {
    			drawWhiteSquare(x, y);
    			drawPiece(x, y);
				recordPositon(x, y);
			// Second time select a piece during the same turn
			} else if (hasSelected == true) {
				// Clear previous selection
    			drawGraySquare(xPre, yPre);
	   			drawPiece(xPre, yPre);
	   			hasSelected = false;	
	   			recordPositon(x, y);
	   		}
        // Select an empty square	              		
		} else if (target == null) {
			(pieceAt(xPre, yPre)).move(x, y);
			moved = true;

			// Draw the target
			drawWhiteSquare(x, y);
	        drawPiece(x, y);
	        recordPositon(x, y);
		} 
	}

	private void recordPositon(int x, int y) {
		xPre = x;
		yPre = y;
		hasSelected = true;
	}

	// not yet implemented
	public void place(Piece p, int x, int y) {
		if (checkBound(x, y) == false || p == null) {
			// Do nothing
		} else if (pieces[x][y] != null) {
			// if p already exists in the current Board, first removes it from its initial position
			remove(x, y); // what to do with it?
			pieces[x][y] = p;
			drawPiece(x, y);
		} else if (pieces[x][y] == null) {
			pieces[x][y] = p;
			drawPiece(x, y);
		}
	}

	public Piece remove(int x, int y) {
		if (checkBound(x, y) == false) {
			System.out.println("Error: input position (x, y) out of bounds.");
			return null;
		} else if (pieces[x][y] == null){
			System.out.println("Error: no piece at positoin (x, y) to remove.");
			return null;
		} else {
			Piece removedPiece = pieces[x][y];
			pieces[x][y] = null;
			drawGraySquare(x, y);		
			return removedPiece;
		}
	}

	public boolean canEndTurn(){
		if (moved) {
			return true;
		} else {
			return false;
		}
	}
	
	public void endTurn(){
		if(playerTurn == true){
			playerTurn = false;
		}else{
			playerTurn = true;
		}
		// Handle anything elese that should happen at the end of a turn
		drawGraySquare(xPre, yPre);

		drawPiece(xPre, yPre);  // null pointer exception

		moved = false;
		hasSelected = false;
        fireCounter = 0;
        waterCounter = 0;

		for (int j = 0; j < boardSize; j += 1) {
			for (int i = 0; i < boardSize; i += 1) {
				if (pieceAt(i, j) != null){
					pieceAt(i, j).doneCapturing();
				}
			}
		}
	}

	private void drawGraySquare(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	   	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	   	//System.out.println("Gray Square Drawn");

	}

	private void drawWhiteSquare(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	   	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	   	//System.out.println("White Square Drawn");
	}

	public String winner() {
		// System.out.println("Fire: " + Integer.toString(fireCounter));
		// System.out.println("Water: " + Integer.toString(waterCounter));
		for (int j = 0; j < boardSize; j += 1) {
			for (int i = 0; i < boardSize; i += 1) {
				if (pieceAt(i, j) != null){
					if (pieceAt(i, j).isFire()) {
						fireCounter += 1;
					} else {
						waterCounter += 1;
					}
				}
			}
		}

		if (waterCounter == 0 && fireCounter != 0) {
			finalWinner = "Fire";
			return "Fire";
		} else if (waterCounter !=0 && fireCounter == 0) {
			finalWinner = "Water";
			return "Water";
		} else if (waterCounter == 0 && fireCounter == 0) {
			finalWinner = "No one";
			return "No one";
		} else {
			return null;
		}
	}
}