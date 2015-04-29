public class Board {

	private Piece[][] pieces;
	private boolean boardType;;
	private boolean hasMoved;
	private int xSelected;
	private int ySelected;
	private Piece selected;
	private boolean fireTurn = true;

	/* Constructs a board */
	public Board(boolean shouldBeEmpty) {
		boardType = shouldBeEmpty;
		pieces = new Piece[8][8];
		if (boardType == true) {
			drawEmptyBoard(8);
		}
		else {
			drawBoard();
			drawPieces();
			hasMoved = false;
			xSelected = -2;
			ySelected = -2;
			selected = null;
			//fireTurn = true;
			fillArrayPieces();
		}
	}

	/* Main method. 
		1. Runs the clicking
		2. Updates graphics
		*/
	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		b.drawBoard();
		// System.out.println(b);
		// for (int i = 0; i < N; i++) {
		// 	for (int j = 0; j < N; j++) {
		// 		System.out.println(i + " " + j + " " + (b.pieces[i][j] == null));
		// 	}
		// }
        while(true) {
        	/* Pressing feature */
        	
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y) == true) {
                	b.select(x, y);
                }

                b.drawBoard();
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn() == true) {
            		b.endTurn();
            	}
            }                   
            StdDrawPlus.show(100);   
            } 
    }

	/* Draws the board and updates the pieces after every turn.
		Turns selected sqaure white. */
	private void drawBoard() {
		int N = 8;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 ==0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					if (pieces[i][j] != null) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, getImg(pieces[i][j]), 1, 1);
					} else {

					}
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);				 
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				if (i == xSelected && j == ySelected) { /* Implements selected square into white */
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
					if (pieces[i][j] != null) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, getImg(pieces[i][j]), 1, 1);
					}
				}
			}
		}
	}

	/* Gets the path of the images. */
	private String getImg(Piece p) {
		String toReturn = null;
		if ((p.isFire() == true) && (p.isBomb() == false) && (p.isShield() == false)) {
			toReturn = "img/pawn-fire.png";
		}
		else if ((p.isFire() == true) && (p.isBomb() == true) && (p.isShield() == false)) {
			toReturn = "img/bomb-fire.png";
		}
		else if ((p.isFire() == true) && (p.isBomb() == false) && (p.isShield() == true)) {
			toReturn = "img/shield-fire.png";
		}
		else if ((p.isFire() == false) && (p.isBomb() == false) && (p.isShield() == false)) {
			toReturn = "img/pawn-water.png";
		}
		else if ((p.isFire() == false) && (p.isBomb() == false) && (p.isShield() == true)) {
			toReturn = "img/shield-water.png";
		}
		else if ((p.isFire() == false) && (p.isBomb() == true) && (p.isShield() == false)) {
			toReturn = "img/bomb-water.png";
		}
		 /* isKing pieces */
		if ((p.isFire() == true) && (p.isBomb() == false) && (p.isShield() == false) && (p.isKing() == true)) {
			toReturn = "img/pawn-fire-crowned.png";
		}
		else if ((p.isFire() == true) && (p.isBomb() == true) && (p.isShield() == false) && (p.isKing() == true)) {
			toReturn = "img/bomb-fire-crowned.png";
		}
		else if ((p.isFire() == true) && (p.isBomb() == false) && (p.isShield() == true) && (p.isKing() == true)) {
			toReturn = "img/shield-fire-crowned.png";
		}
		else if ((p.isFire() == false) && (p.isBomb() == false) && (p.isShield() == false) && (p.isKing() == true)) {
			toReturn = "img/pawn-water-crowned.png";
		}
		else if ((p.isFire() == false) && (p.isBomb() == false) && (p.isShield() == true) && (p.isKing() == true)) {
			toReturn = "img/shield-water-crowned.png";
		}
		else if ((p.isFire() == false) && (p.isBomb() == true) && (p.isShield() == false) && (p.isKing() == true)) {
			toReturn = "img/bomb-water-crowned.png";
		}
		return toReturn;
	}

	 // Draws the pieces in starting location. 
	private void drawPieces() {
		/* Draws Fire Pieces */
		int N = 8;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if ((y == 0) && (x % 2 == 0)) {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
				if ((y == 1) && (x % 2 != 0)) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);					
				}
				if ((y == 2) && (x % 2 == 0)) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				}					
			}
		}
		/* Draws Water Pieces */
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if ((y == 5) && (x % 2 != 0)) {	
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				}
				if ((y == 6) && (x % 2 == 0)) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);					
				}
				if ((y == 7) && (x % 2 != 0)) {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}					
			}					
		}
	}
	

	/* Fills the array Pieces with all the pieces */
	private void fillArrayPieces() {
		for (int x = 0; x < 8; x += 1) {
			if (x % 2 == 0) {
				/* Fills fire pawn */
				pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
				pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
				pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
				pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
				/* Fills fire bomb */
				pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
				pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
				pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
				pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
				/* Fills water shield */
				pieces[0][6] = new Piece(false, this, 0, 6, "shield");
				pieces[2][6] = new Piece(false, this, 2, 6, "shield");	
				pieces[4][6] = new Piece(false, this, 4, 6, "shield");	
				pieces[6][6] = new Piece(false, this, 6, 6, "shield");								
			}
			if (x % 2 != 0) {
				/* Fills fire shield */
				pieces[1][1] = new Piece(true, this, 1, 1, "shield");
				pieces[3][1] = new Piece(true, this, 3, 1, "shield");	
				pieces[5][1] = new Piece(true, this, 5, 1, "shield");	
				pieces[7][1] = new Piece(true, this, 7, 1, "shield");	
				/* Fills water bomb */
				pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
				pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
				pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
				pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
				/* Fills water pawn */
				pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
				pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
				pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
				pieces[7][7] = new Piece(false, this, 7, 7, "pawn");							
			}
		}
	}

	/* Draws a board with no pieces */
	private static void drawEmptyBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 ==0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else 				 StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
		}
	}

	/* Grabs the piece on the (x, y) position. 
		Returns null if there is no piece */
	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}

	/* Returns true if there is a piece and if it can be selected. 
		A piece may be selected if it's the player's turn and one is true
			- Player has not picked a piece. 
			- Player selected but didn't move. 
		An empty square may be selected if one is true. 
			- During turn, player has not selected and empty spot is valid. 
			- During turn, player selected a Piece, captured, and has selected
				another valid capture destination. when performing multi-captures,
				you should only select active piece once, all other selections
				should be valid destination points. */
	public boolean canSelect(int x, int y) {
		// xSelected = x;
		// ySelected = y;
		// System.out.println("I'm in CanSelect");
		if (selected == null) {
			if (pieceAt(x,y) != null) {
				if (fireTurn == true && (pieceAt(x,y).isFire() == true)) {
					if (hasMoved == false) {
						return true;
					}
				}
				else if (fireTurn == false && (pieceAt(x,y).isFire() == false)) {;
					if (hasMoved == false) {
						return true;
					}
				}
			}
		}
		

		else if (selected != null) {
			if (hasMoved == false) {
				if (pieceAt(x,y) != null) {
					return true;
				}
				else {
					if (validMove(x, y) == true) {
						return true;
					}
				}
			}
		}

			// else if (hasMoved == true) {
			// 	if (pieceAt(x,y) == selected) {
			// 		return true;
			// 	}
			// 	return false;
			// }
		/* Checks if multicapture is a possibility */
		if (hasMoved == true && selected.hasCaptured() == true) {
			System.out.println("I am in can select multicature");
			return validMove(x,y);
		}
		return false;
	}	

	/* Retrieves the y value from a piece */
    private int getY(Piece p) {
    	int N = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) == p) {
                    return i;
                }
            }
        }    
        return -1;
    }

    /* Retrieves the x value from a piece */
    private int getX(Piece p) {
    	int N = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) == p) {
                    return j;
                }
            }
        }
        return -1;
    }

	/* Checks if move is valid.
		Basic functionality:
			- checks if selected spot isn't already occupied. 
			- checks to see that it's not moving too much  
	*/
	private boolean validMove(int x, int y) {
		// System.out.println("I'm in validMove");
		boolean result = false;
		boolean resultKingFire = false;
		boolean resultKingWater = false;
		// System.out.println("I am x: " + x);
		// System.out.println("I am y: " + y);
		/* King Movement */
		if (selected.isKing() == true) {
			System.out.println("the king wants to go");
			resultKingFire = validFireMove(x,y);
			resultKingWater = validWaterMove(x,y);
			if (resultKingFire == true) {
				return true;
			}
			else {
				return resultKingWater;
			}
		}
		if (selected.isFire() == true) {
			System.out.println("i am in select valid move - fire");
			result = validFireMove(x, y);
			return result;
		}
		else {
			System.out.println("i am in select valid move - water");
			result = validWaterMove(x, y);
			return result;
			}
	}

	private boolean validWaterMove(int x, int y) {
		Piece p = selected;
		// System.out.println("I am xSelected: " + xSelected);
		// System.out.println("I am ySelected: " + ySelected);
		// System.out.println("I am x: " + x);
		// System.out.println("I am y: " + y);
		if ( ((hasMoved == false) && (p.isFire() == false)) || ((p.isKing() == true) && (hasMoved == false)) ) {
			// System.out.println("I am xSelected: " + xSelected);
			// System.out.println("I am ySelected: " + ySelected);
			// System.out.println("I am x: " + x);
			// System.out.println("I am y: " + y);
			if ((xSelected - 1 == x) && (ySelected - 1 == y)) {
				System.out.println("Water: Down right");
				return true;
				}
			if ((xSelected + 1 == x) && (ySelected - 1 == y)) {
				System.out.println("Water: Down left");
				return true;
				}
			if ((xSelected - 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected - 1,ySelected - 1) != null)
			 	&& (pieceAt(xSelected - 1, ySelected - 1).isFire() == true)) {
				System.out.println("Water: Down left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected + 1,ySelected - 1) != null)
				&& (pieceAt(xSelected + 1, ySelected - 1).isFire() == true)) {
				System.out.println("Water: Down right Capture");
				return true;
				}
			if ((xSelected - 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected - 1,ySelected - 1) != null)
			 	&& (p.isKing() == true) && (p.isFire() == true) && (pieceAt(xSelected - 1, ySelected - 1).isFire() == false)) { //king reg captures left down
				System.out.println("King: Down left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected + 1,ySelected - 1) != null)
				&& (p.isKing() == true) && (p.isFire() == true) && (pieceAt(xSelected + 1, ySelected - 1).isFire() == false)) { // king capture down right
				System.out.println("King: Down right Capture");
				return true;
				}
		}
		if (hasMoved == true || p.isKing() == true) {
			System.out.println("I am in valid water move has moved");
			if ((xSelected - 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected - 1,ySelected - 1) != null)
			 	&& (pieceAt(xSelected - 1, ySelected - 1).isFire() == true)) {
				System.out.println("Water: Down left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected + 1,ySelected - 1) != null)
				&& (pieceAt(xSelected + 1, ySelected - 1).isFire() == true)) {
				System.out.println("Water: Down right Capture");
				return true;
				}
			if ((xSelected - 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected - 1,ySelected - 1) != null)
			 	&& p.isKing() == true && p.isFire() == true && (pieceAt(xSelected - 1, ySelected - 1).isFire() == false)) { //king reg captures left down
				System.out.println("King: Down left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected - 2 == y) && (pieceAt(xSelected + 1,ySelected - 1) != null)
				&& p.isKing() == true && p.isFire() == true && (pieceAt(xSelected + 1, ySelected - 1).isFire() == false)) { // king capture down right
				System.out.println("King: Down right Capture");
				return true;
				}
		}			
		return false;
	}


	private boolean validFireMove(int x, int y) {
		Piece p = selected;
		// System.out.println("I am xSelected: " + xSelected);
		// System.out.println("I am ySelected: " + ySelected);
		// System.out.println("I am x: " + x);
		// System.out.println("I am y: " + y);
		if ( ((hasMoved == false) && (p.isFire() == true)) || ((p.isKing() == true) && (hasMoved == false)) ) {
			if ((xSelected - 1 == x) && (ySelected + 1 == y)) {
				System.out.println("Fire: Up left");
				return true;
				}
			if ((xSelected + 1 == x) && (ySelected + 1 == y)) {
				System.out.println("Fire: Up right");
				return true;
				}
			if ((xSelected - 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected - 1,ySelected + 1) != null) 
				&& (pieceAt(xSelected - 1, ySelected + 1).isFire() != true)) {
				System.out.println("Fire: Up left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected + 1,ySelected + 1) != null) 
				&& (pieceAt(xSelected + 1, ySelected + 1).isFire() != true)) {
				System.out.println("Fire: Up right Capture");
				return true;
				}
			if ((xSelected - 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected - 1,ySelected + 1) != null) 
				&& p.isKing() == true && p.isFire() == false && (pieceAt(xSelected - 1, ySelected + 1).isFire() != false)) {
				System.out.println("Fire: Up left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected + 1,ySelected + 1) != null) 
				&& p.isKing() == true && p.isFire() == false && (pieceAt(xSelected + 1, ySelected + 1).isFire() != false)) {
				System.out.println("Fire: Up right Capture");
				return true;
				}

		}
		if (hasMoved == true) {
			System.out.println("I am in valid fire move has moved");
			if ((xSelected - 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected - 1,ySelected + 1) != null) 
				&& (pieceAt(xSelected - 1, ySelected + 1).isFire() != true)) {
				System.out.println("Fire: Up left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected + 1,ySelected + 1) != null) 
				&& (pieceAt(xSelected + 1, ySelected + 1).isFire() != true)) {
				System.out.println("Fire: Up right Capture");
				return true;
				}
			if ((xSelected - 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected - 1,ySelected + 1) != null) 
				&& p.isKing() == true && p.isFire() == false && (pieceAt(xSelected - 1, ySelected + 1).isFire() != false)) {
				System.out.println("Fire: Up left Capture");
				return true;
				}
			if ((xSelected + 2 == x) && (ySelected + 2 == y) && (pieceAt(xSelected + 1,ySelected + 1) != null) 
				&& p.isKing() == true && p.isFire() == false && (pieceAt(xSelected + 1, ySelected + 1).isFire() != false)) {
				System.out.println("Fire: Up right Capture");
				return true;
				}
		}
		return false;
	}	

	 // Selects the piece at (x, y) if possible. 
	public void select(int x, int y) {
		xSelected = x;
		ySelected = y;
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (pieces[x][y] != null) {
				selected = pieces[x][y];
			// System.out.println("Sampath");
			}
			if (pieces[x][y] == null) {
				selected.move(x,y);
				hasMoved = true;
			}
		}
	}

	/* Executes a removes. Returns piece that was removed. */
	public Piece remove(int x, int y) {
		Piece toReturn = pieces[x][y];
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Out of bounds");
			return null;
		}
		if (toReturn == null) {
			System.out.println("No piece at current location");
			return null;
		}
		pieces[x][y] = null;
		return toReturn;
	}

	/* Places p on (x, y) */
	public void place(Piece p, int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			pieces[x][y] = p;
		}
	}

	/* Returns whether or not a turn can end. */
	public boolean canEndTurn() {
		if (this.hasMoved == true || (selected != null && selected.hasCaptured())) {
			return true;
		}
		return false;
	}

	/* Handles switching of players. */
	public void endTurn() {
		if (fireTurn == true) {
			fireTurn = false;
		}
		else {
			fireTurn = true;
		}
		xSelected = -1;
		ySelected = -1;
		selected.hasCaptured();
		selected.doneCapturing();
		selected = null;
		hasMoved = false;
		// System.out.println(this);
	}

	/* Determines and returns the String of the winner */
    public String winner() {
        int waterCount = 0;
        int fireCount = 0; 
        int N = 8;      
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire() == true) {
                    	fireCount++;
                	}
                else if (pieces[i][j].isFire() == false) {
                    	waterCount++;
                	}
            	}
        	}
        }

        if ((fireCount == 0) && (waterCount == 0)) {
            return "No one";
        }
		else if (fireCount == 0) {
            return "Water";
        }
		else if (waterCount == 0) {
            return "Fire";
        }
        return null;     
    }

  // @Override
  //   public String toString() {
  //       StringBuilder s = new StringBuilder();
  //       for (int y = 7; y >= 0; y--) {
  //           for (int x = 0; x < pieces[y].length; x++) {
  //               if (pieces[y][x] == null) {
  //                   s.append(" [ " + x + "," + y + " ] ");
  //               } else {
  //                   s.append(pieces[y][x]); 
  //               }

  //           }
  //           s.append("\n");
  //       }
  //       return s.toString();
  //   }
 }