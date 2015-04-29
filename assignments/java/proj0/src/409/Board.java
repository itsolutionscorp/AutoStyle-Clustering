public class Board { 


	// Location of the pieces on the board.

    private Piece[][] pieces;

    // Scale of the board.

    private int N = 8;

    // Player switching.

    private int playerTurn = 1;
    private boolean hasMoved = false;

    // Piece Selection.

    private boolean isPieceSelected;
    private int xSquareSelected;
    private int ySquareSelected;
    private int xPieceSelected = -1;
    private int yPieceSelected = -1;
    private Piece pieceSelected;

    // DrawBoard method, which draws – drumrolls please – the board!

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].side() == 0) {
                		if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		} else if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		} else if (pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		} else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	} else {
                		if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		} else if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		} else if (pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		} else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}
                }
            }
        }
        if (xSquareSelected != -1 && ySquareSelected != -1){
            StdDrawPlus.filledSquare(xSquareSelected + .5, ySquareSelected + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        }

    }

    // Have to make it so I get (x, y) from a click, then I call canSelect to see if the 
    // selection is valid, THEN actually select the square or Piece. select does not call
    // canSelect.


    // Main method.

	public static void main (String args[]){

		// Starts the GUI supported version of the game.

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        // The Game Board.

		Board b = new Board (false);
		int xSquareSelected;
		int ySquareSelected;

		while (true) {

            b.drawBoard(8);         
            StdDrawPlus.show(10);

            if (StdDrawPlus.mousePressed()) {
            	xSquareSelected = (int)StdDrawPlus.mouseX();
            	ySquareSelected = (int)StdDrawPlus.mouseY();
            	if (b.canSelect(xSquareSelected, ySquareSelected)) {
            		b.select(xSquareSelected, ySquareSelected);
            		StdDrawPlus.filledSquare(xSquareSelected + .5, ySquareSelected + .5, .5);
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	}
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }

            if (b.winner() != null) {
            	break;
            }
        }

        b.winner();
    }


	public Board (boolean shouldBeEmpty){

		/* The constructor for Board. If shouldBeEmpty is true, 
		 * initializes an empty Board. Otherwise, initializes a 
		 * Board with the default configuration. Note that an empty 
		 * Board could possibly be useful for testing purposes.
		 */

		int N = 8;	
        pieces = new Piece[N][N];

		// Block of Code that defines the pieces on the board.

		if (shouldBeEmpty == false){

			// Fire Pawns

			for (int B=0; B < 8; B+=1) {
				if (B % 2 == 0) {
					pieces[B][0] = new Piece (true, this, B, 0, "pawn");
				} else {
					pieces[B][0] = null;
				}
			}

			// Fire Shields.

			for (int B=0; B < 8; B+=1) {
				if (B % 2 != 0) {
					pieces[B][1] = new Piece (true, this, B, 1, "shield");
				} else {
					pieces[B][1] = null;
				}
			}

			// Fire Bombs.

			for (int B=0; B < 8; B+=1) {
				if (B % 2 == 0) {
					pieces[B][2] = new Piece (true, this, B, 2, "bomb");
				} else {
					pieces[B][2] = null;
				}
			}

			// Water Pawns.

			for (int B=0; B < 8; B+=1) {
				if (B % 2 != 0) {
					pieces[B][7] = new Piece (false, this, B, 7, "pawn");
				} else {
					pieces[B][7] = null;
				}
			}

			// Water Shields.

			for (int B=0; B < 8; B+=1) {
				if (B % 2 == 0) {
					pieces[B][6] = new Piece (false, this, B, 6, "shield");
				} else {
					pieces[B][6] = null;
				}
			}

			// Water Bombs.

			for (int B=0; B < 8; B+=1) {
				if (B % 2 != 0) {
					pieces[B][5] = new Piece (false, this, B, 5, "bomb");
				} else {
					pieces[B][5] = null;
				}
			}

		// Empty Board.

		} else {
        	pieces = new Piece[N][N];;
		}
	}
	
	
	public Piece pieceAt (int x, int y){

		/* Gets the piece at position (x, y) on the board, or null if 
		 * there is no piece. If (x, y) are out of bounds, returns null.
		 */ 

		if (x < 0 || x > 7 || y < 0 || y > 7 || pieces[x][y] == null){
			return null;
		} else {
			return pieces[x][y];
		}		
	}

	
	public boolean canSelect (int x, int y) {

		/* Returns true if there is a piece at (x, y) 
		 * and it can be selected.

		 * A piece may be selected if it is the corresponding player’s turn and 
		 * one of the following is true:

		 	– The player has not selected a piece yet.
		 	– The player has selected a piece, but did not move it.

		 * An empty square may be selected if one of the following is true:

		  	– During this turn, the player has selected a Piece which hasn’t moved 
		 	  yet and is selecting an empty spot which is a valid move for the 
		 	  previously selected Piece.

		  	– During this turn, the player has selected a Piece, captured, and has
		  	  selected another valid capture destination. When performing 
		  	  multi-captures, you should only select the active piece once; all 
		  	  other selections should be valid destination points.


		  	  **********
		  	  RIGHT NOW, MY IMPLEMENTATION HAS A BIG MISTAKE: IT ALLOWS FOR A ONE SQUARE
		  	  MOVE AFTER A CAPTURE, SOMETHING THAT SHOULDN'T BE POSSIBLE, AS THE ONLY POSSIBLE
		  	  MOVE AFTER DOING A CAPTURE IS ANOTHER CAPTURE OR END THE TURN.
		 */

		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.print("The selected area is out of bounds.");
			return false;


		} else if (pieceAt(x, y) != null) {

			if (playerTurn == 1 && pieceAt(x, y).isFire()) {
				if (isPieceSelected == false) {
					return true;
				} 
				else if (isPieceSelected == true && hasMoved == false) {
					return true;
				}
			}

			else if (playerTurn == -1 && pieceAt(x, y).isFire() == false) {
				if (isPieceSelected == false) {
					return true;
				} 
				else if (isPieceSelected == true && hasMoved == false) {
					return true;
				}
			}
		}


		else if (pieceAt(x, y) == null) {
			if (isPieceSelected == true && pieceAt(xPieceSelected, yPieceSelected).hasCaptured() && validCapture(xPieceSelected, yPieceSelected, x, y)) {
				return true;
			} 
			else if (isPieceSelected == true && hasMoved == false && validMove(xPieceSelected, yPieceSelected, x, y)) {
				return true;
			}
		}

		return false;
	}


	
	
	private boolean validMove(int xi, int yi, int xf, int yf){

		// ** OPTIONAL ** \\

		/* Returns true if the piece at (xi, yi) can either move to (xf, yf)
		 * or capture to (xf, yf), strictly from a geometry/piece-race point
		 * of view. validMove does not need to take into consideration whose turn
		 * it is or if a move has already been made, but rather can. It is suggested 
		 * you use this method to simplify your implementation of canSelect.
		 */ 

		if (xf > 7 || yf > 7 || xf < 0 || yf < 0) {	      // Checks if the destination square
			return false;							      // is within the boundaries.
		}

		else if (pieceAt(xi, yi) == null) {               // Check whether there is a piece in
			return false;							      // the initial square.
		}

		else if (pieceAt(xf, yf) != null) {               // Checks if there is a piece in the 
			return false;                                 // destination square, and if so, invalidates
		}                                                 // the move.

		else if (Math.abs(xi - xf) > 2 || Math.abs(yi - yf) > 2) {  // Checks to see if the attempted jump
			return false;									        // is greater than 2 squares.
		}

		else if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) {

			if (pieceAt(xi, yi).isKing()) {
				return true;
			} else if (pieceAt(xi, yi).isFire() && yf > yi) {
				return true;
			} else if (pieceAt(xi, yi).isFire() == false && yi > yf) {
				return true;
			}
		}

		else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {

			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi, yi).isFire()) {
					if (xf > xi && yf > yi && pieceAt(xi+1, yi+1).isFire() == false) {
						return true;
					} else if (xi > xf && yf > yi && pieceAt(xf+1, yi+1).isFire() == false) {
						return true;
					} else if (xf > xi && yi > yf && pieceAt(xi+1, yf+1).isFire() == false) {
						return true;
					} else if (xi > xf && yi > yf && pieceAt(xf+1, yf+1).isFire() == false) {
						return true;
					}

				} else if (pieceAt(xi, yi).isFire() == false) {
					if (xf > xi && yf > yi && pieceAt(xi+1, yi+1).isFire()) {
						return true;
					} else if (xi > xf && yf > yi && pieceAt(xf+1, yi+1).isFire()) {
						return true;
					} else if (xf > xi && yi > yf && pieceAt(xi+1, yf+1).isFire()) {
						return true;
					} else if (xi > xf && yi > yf && pieceAt(xf+1, yf+1).isFire()) {
						return true;
					}
				}
			}

			else if (pieceAt(xi, yi).isFire()) {
				if (xf > xi && yf > yi && pieceAt(xi+1, yi+1).isFire() == false) {
					return true;
				} else if (xi > xf && yf > yi && pieceAt(xf+1, yi+1).isFire() == false) {
					return true;
				}
			} else if (pieceAt(xi, yi).isFire() == false) {
				if (xf > xi && yi > yf && pieceAt(xi+1, yf+1).isFire()) {
					return true;
				} else if (xi > xf && yi > yf && pieceAt(xf+1, yf+1).isFire()) {
					return true;
				}
			}  
		} else {
			return false;
		}
		return false;
	}



	private boolean validCapture(int xi, int yi, int xf, int yf) {

		if (xf > 7 || yf > 7 || xf < 0 || yf < 0) {	      // Checks if the destination square
			return false;							      // is within the boundaries.
		}

		else if (pieceAt(xi, yi) == null) {               // Check whether there is a piece in
			return false;							      // the initial square.
		}

		else if (pieceAt(xf, yf) != null) {               // Checks if there is a piece in the 
			return false;                                 // destination square, and if so, invalidates
		}												  // the move.


		else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {

			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi, yi).isFire()) {
					if (xf > xi && yf > yi && pieceAt(xi+1, yi+1).isFire() == false) {
						return true;
					} else if (xi > xf && yf > yi && pieceAt(xf+1, yi+1).isFire() == false) {
						return true;
					} else if (xf > xi && yi > yf && pieceAt(xi+1, yf+1).isFire() == false) {
						return true;
					} else if (xi > xf && yi > yf && pieceAt(xf+1, yf+1).isFire() == false) {
						return true;
					}

				} else if (pieceAt(xi, yi).isFire() == false) {
					if (xf > xi && yf > yi && pieceAt(xi+1, yi+1).isFire()) {
						return true;
					} else if (xi > xf && yf > yi && pieceAt(xf+1, yi+1).isFire()) {
						return true;
					} else if (xf > xi && yi > yf && pieceAt(xi+1, yf+1).isFire()) {
						return true;
					} else if (xi > xf && yi > yf && pieceAt(xf+1, yf+1).isFire()) {
						return true;
					}
				}
			}

			else if (pieceAt(xi, yi).isFire()) {
				if (xf > xi && yf > yi && pieceAt(xi+1, yi+1).isFire() == false) {
					return true;
				} else if (xi > xf && yf > yi && pieceAt(xf+1, yi+1).isFire() == false) {
					return true;
				}
			} else if (pieceAt(xi, yi).isFire() == false) {
				if (xf > xi && yi > yf && pieceAt(xi+1, yf+1).isFire()) {
					return true;
				} else if (xi > xf && yi > yf && pieceAt(xf+1, yf+1).isFire()) {
					return true;
				}
			}  
		}

		else {
			return false;
		}
		return false;
	}


	
	public void select(int x, int y){

		/* Selects the piece at (x, y) if possible. Optionally, it is recommended 
		 * to color the background of the selected square white on the GUI via the 
		 * pen color function. For any piece to perform a capture, that piece must 
		 * have been selected first.
		 */

		
		if (isPieceSelected == false){
			pieceSelected = pieceAt(x, y);
			isPieceSelected = true;
			xPieceSelected = x;
			yPieceSelected = y;
		}

		else if (hasMoved == false) {
			if (pieceAt(x, y) == null) {
				pieceSelected.move(x, y);
				xPieceSelected = x;
				yPieceSelected = y;
				hasMoved = true;
			} else {
				xPieceSelected = x;
				yPieceSelected = y;
			}

		} else if (hasMoved) {
			if (pieceSelected.hasCaptured() && validCapture(xPieceSelected, yPieceSelected, x, y)) {
				pieceSelected.move(x, y);
				xPieceSelected = x;
				yPieceSelected = y;
			}
		}	
	}
	
	public void place (Piece p, int x, int y){

		/* Places p at (x, y). If (x, y) is out of bounds, does nothing. If another 
		 * piece already exists at (x, y), p will replace that piece. (This method 
		 * is potentially useful for creating specific test circumstances.)
		 */

		if (x < 0 || x > N - 1 || y < 0 || y > N - 1 || p == null){
			return;
		} 

		else {
			pieces[x][y] = p;
		}
	}


	
	public Piece remove(int x, int y){

		/* Executes a remove. Returns the piece that was removed. If the input 
		 * (x, y) is out of bounds, returns null and prints an appropriate message.
		 * If there is no piece at (x, y), returns null and prints an appropriate 
		 * message.
		 */

		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.print("The area you selected for remove is out of bounds.");
			return null;
		} 

		else if (pieceAt(x, y) == null) {
			System.out.print("There is no piece to remove here.");
			return null;
		} 

		else {
			Piece removed = pieceAt(x, y);
			pieces[x][y] = null;
			return removed;
		}

	}

	public boolean canEndTurn(){

		/* Returns whether or not the the current player can end their turn. To be 
		 * able to end a turn, a piece must have moved or performed a capture.
		 */

		if (hasMoved || pieceSelected.hasCaptured()) {
			return true;
		} else {
			return false;
		}
	}


	public void endTurn(){

		/* Called at the end of each turn. Handles switching of players and 
		 * anything else that should happen at the end of a turn.
		 */

		pieceSelected.doneCapturing();
		playerTurn *= -1;
 		hasMoved = false;
 		isPieceSelected = false;
 		xPieceSelected = -1;
		yPieceSelected = -1;
 		xSquareSelected = -1;
 		ySquareSelected = -1;
 		pieceSelected = null;
 		/* Still have to find a way to restore the hasCapture of the piece that
 		 * moved back to false if reverting it from doneCapturing in Piece.java
 		 * doesn't work.
 		 */
		

	}

	
	public String winner(){

		/* Returns the winner of the game. "Fire", "Water", "No one" 
		 * (tie / no pieces on the board), or null if the game is not yet over. 
		 * Assume there is no stalemate situation in which the current player has 
		 * pieces but cannot legally move any of them (In this event, just leave 
		 * it at null). Determine the winner solely by the number of pieces 
		 * belonging to each team.
		 */

		int waterPieces = 0;
		int firePieces = 0;
		for (int j = 0; j < N; j += 1) {
			for (int i = 0; i < N; i += 1) {
				if (pieces[j][i] != null) {
					if (pieces[j][i].isFire()) {
						firePieces += 1;
					} else {
						waterPieces += 1;
					}
				}
			}
		}

		if (waterPieces == 0 && firePieces == 0) {
			return "No one";
		} else if (waterPieces == 0 && firePieces > 0) {
			return "Fire";
		} else if (waterPieces > 0 && firePieces == 0){
			return "Water";
		} else {
			return null;
		}
	}
}