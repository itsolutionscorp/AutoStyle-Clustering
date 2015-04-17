// Alankrita Dayal (CS61B bey)
// - DSP Student, extremely sick and was able to submit only now, in contact with head GSI, thanks so much

public class Board {
	private Piece[][] checkersBoard;
	private boolean fireTeamMove = true; // in all new games, fire team makes the first move
	//coordinates, no set selection at first 
	private int xPosition; 
	private int yPosition; 
	private Piece whiteSquare = null; // square selected on a turn // static?
	// default false at beginning of game when no moves are made and waiting for fireteam to go
	private boolean clickToSelect = false; 
	private boolean turnMade = false; 

	public static void main(String[] args) {
    	StdDrawPlus.setXscale(0, 8);
    	StdDrawPlus.setYscale(0, 8);
		Board boardb = new Board(false); // if Board(true), empty board will result
 
		while (true) {
			boardb.drawBoard();
			if (StdDrawPlus.mousePressed() == true) {
            	double x = StdDrawPlus.mouseX();
            	double y = StdDrawPlus.mouseY();
            	if (boardb.canSelect((int) x, (int) y) == true) {
            		boardb.select((int) x, (int) y);
            	}
			}
			if (StdDrawPlus.isSpacePressed() == true) {
				boardb.endTurn();
			}
			StdDrawPlus.show(100);
			if (boardb.winner() != null) {
				break;
			}
			// exiting loop when Board is empty
//			if (!boardb.winner().equals("No One")) { 
//				break;
//			}
			if (StdDrawPlus.isNPressed() == true) { // will allow game to restart once when N is pressed whenever
				main(args); // completely new game
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		checkersBoard = new Piece[8][8]; // initialize an empty Board if shouldBeEmpty true
		if (shouldBeEmpty == false) { 	// otherwise if false, initialize Board with default configuration for board items
			for (int i = 0; i < 8; i += 2) { // even rows fill
				checkersBoard[i][0] = new Piece(true, this, i, 0, "pawn"); // fire pawn pieces
				checkersBoard[i][2] = new Piece(true, this, i, 2, "bomb"); // fire bomb pieces
				checkersBoard[i][6] = new Piece(false, this, i, 6, "shield"); // water shield pieces
			}
			for (int i = 1; i < 8; i += 2) { // odd rows fill
				checkersBoard[i][1] = new Piece(true, this, i, 1, "shield"); // fire shield pieces
				checkersBoard[i][5] = new Piece(false, this, i, 5, "bomb"); // water bomb pieces
				checkersBoard[i][7] = new Piece(false, this, i, 7, "pawn"); // water pawn pieces
			}
		}
	}

	private void drawBoard() {
		int row = 0;
		while (row < 8) {
			int column = 0;
			while (column < 8) {
				if ((column + row) % 2 == 1) { // will not work for negative sum :) (returns -1)
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
                if ((whiteSquare != null ) && (whiteSquare == checkersBoard[row][column])) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE); // white highlight the selected piece on the GUI
                }
            	StdDrawPlus.filledSquare(row + .5, column + .5, .5);
            	// for non-empty Board: 
                if (pieceAt(row, column) != null) {
                	String file = "";
                	//  draw bombs
                	if (checkersBoard[row][column].isBomb() == true) {
                		file += "bomb";
                		if (checkersBoard[row][column].isFire() == true) {
                			file += "-fire";
                		} else { file += "-water"; }
                		if (checkersBoard[row][column].isKing() == true) { file += "-crowned";}
                		StdDrawPlus.picture(row + .5, column + .5, "img/" + file + ".png", 1, 1);
                	}
                	// draw shields
                	else if (checkersBoard[row][column].isShield() == true) {
                		file += "shield";
                		if (checkersBoard[row][column].isFire() == true) {
                			file += "-fire";
                		} else { file += "-water"; }
                		if (checkersBoard[row][column].isKing() == true) { file += "-crowned";}
                		StdDrawPlus.picture(row + .5, column + .5, "img/" + file + ".png", 1, 1);
                	} 
                	// draw pawns
                	else {
                		file += "pawn";
                		if (checkersBoard[row][column].isFire() == true) {
                			file += "-fire";
                		} else { file += "-water"; }
                		if (checkersBoard[row][column].isKing() == true) { file += "-crowned";}
                		StdDrawPlus.picture(row + .5, column + .5, "img/" + file + ".png", 1, 1);
                	}
        		}
				column++;
			}
			row++; 
		}     
    }
 
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0 || checkersBoard[x][y] == null) {
			return null;
		}
		else if (checkersBoard[x][y] != null) {
			return checkersBoard[x][y];
		}
		return null;
	}
 
	public boolean canSelect(int x, int y) {
		if (x <= 7 && y <= 7 && x >= 0 && y >= 0) { // in bounds
			// return true if (player's turn and (no selection OR selection but not moved)) = true
			boolean trueTurn = (checkersBoard[x][y] != null && ((checkersBoard[x][y].isFire() && fireTeamMove) || (!checkersBoard[x][y].isFire() && !fireTeamMove))); 
			if (trueTurn == true && ((clickToSelect == false) || (clickToSelect == true && turnMade == false))) {
					return true;
			}
			// if selecting an empty square
			if (whiteSquare != null) {
				boolean emptySquare = (checkersBoard[x][y] == null); 
				boolean sameConditions = ((whiteSquare.isFire() == true && fireTeamMove == true) || (whiteSquare.isFire() == false && fireTeamMove == false));
				if (emptySquare == true && sameConditions == true) {
					// can't select a square 2 spaces away if there is no piece to capture in between
					if ((x + xPosition) % 2 == 0 && (y + yPosition) % 2 == 0) {
						if (pieceAt(((x + xPosition) / 2), ((y + yPosition) / 2)) == null || pieceAt(((x + xPosition) / 2), ((y + yPosition) / 2)).isFire() == whiteSquare.isFire()) {
							return false;
						}
					}
					boolean validSpot = (validMove(xPosition, yPosition, x, y) == true); 
					// player has selected a piece which hasn't been moved yet
					if ((clickToSelect == true && turnMade == false && validSpot)) { // implies hasCaptured is still false as well
			 			return true;
					}
					if (clickToSelect == true && whiteSquare.hasCaptured() == true && validSpot) {
						return true;
					}
				}
			}
		}
		return false;
	}
 
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ( (checkersBoard[xf][yf] != null) || (checkersBoard[xi][yi] == null) ) { // a piece can't move
			return false;
		}
		else if (xi > 7 || yi > 7 || xi < 0 || yi < 0 || xf > 7 || yf > 7 || xf < 0 || yf < 0) { // out of bounds
			return false; 
		}
		boolean oneSpace = ( (xf - xi  == 1 || xf - xi == - 1) && (yf - yi == 1 || yf - yi == - 1) );
		boolean twoSpaces = ( (xf == xi + 2 || xi == xf + 2) && (yf == yi + 2 || yi == yf + 2) && (checkersBoard[((xi + xf) / 2)][((yi + yf) / 2)] != null) ); 
		if ((oneSpace == true && whiteSquare.hasCaptured() == false) || twoSpaces == true) {
			if (whiteSquare.hasCaptured() == true && twoSpaces == true) {
				if ( (checkersBoard[xi][yi].isFire() == true) && (yi < yf) ) {
					return true;
				}
				else if ( (checkersBoard[xi][yi].isFire() == false) && (yi > yf) ) {
					return true;
				}
				if (checkersBoard[xi][yi].isKing() == true) {
					return true;
				}
			}
			else {
				if ( (checkersBoard[xi][yi].isFire() == true) && (yi < yf) ) {
					return true;
				}
				else if ( (checkersBoard[xi][yi].isFire() == false) && (yi > yf) ) {
					return true;
				}
				if (checkersBoard[xi][yi].isKing() == true) {
					return true;
				} 
			}
		}
		return false;
	}

	public void select(int x, int y) { // assuming canSelected returns true
		// Step 1 of a turn: if you select a square with a piece, PREPARE the piece for movement (Step 2)
		if (checkersBoard[x][y] != null) { 
			xPosition = x; yPosition = y;
			whiteSquare = checkersBoard[x][y];
			clickToSelect = true;
		}
		// Step 2 of a turn: if you then select an empty square, MOVE most recently selected piece (Step 1) to that xy square
		else if (checkersBoard[x][y] == null && clickToSelect == true && whiteSquare != null) { // validMove
				whiteSquare.move(x, y); // move same piece 1 space as before 
				turnMade = true; // now canEndTurn
				xPosition = x; yPosition = y;
				if (whiteSquare.hasCaptured() == true && whiteSquare.isBomb() == true) {
					whiteSquare.doneCapturing();
					whiteSquare = null; // bomb piece removed
				}
		}
	}
 
	public void place(Piece p, int x, int y) {
		if (x <= 7 && x >= 0 && y <= 7 && y >= 0 && p != null) { // if in bounds and p is an actual piece
			if (checkersBoard[x][y] != null) { // if another piece already exists at (x, y)
				checkersBoard[x][y] = p; // p will replace that piece
			} else {
				checkersBoard[x][y] = p; // p placed at (x, y) regardless
			}
		}
	}
 
	public Piece remove(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			System.out.println("Cannot be removed as selection is out of bounds. Remove not possible.");
			return null;
		}
		if (checkersBoard[x][y] == null) {
			System.out.println("There is no piece at this position. Remove not possible.");
			return null;
		}
		Piece removedPiece = checkersBoard[x][y];
		checkersBoard[x][y] = null;
		return removedPiece;
	}
 
	public boolean canEndTurn() {
		if (turnMade == true) {
			return true;
		} else {return false;}
	}
 
	public void endTurn() {
		if (canEndTurn() == true) {
			if (whiteSquare != null && whiteSquare.hasCaptured() == true) {
				whiteSquare.doneCapturing();
			}
			whiteSquare = null;
			fireTeamMove = !fireTeamMove;
			clickToSelect = false;
			turnMade = false;
		}
	}
 
	public String winner() { // based solely by the number of pieces belonging to each team
		int fireCount = 0;
		int waterCount = 0; 
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if (checkersBoard[row][column] != null) {
					if (checkersBoard[row][column].isFire() == true) {
						fireCount += 1; 
					} else {
						waterCount += 1; 
					}
				}
			}
		}
		if (fireCount > 0 && waterCount == 0) {
			return "Fire";
		} else if (waterCount > 0 && fireCount == 0) {
			return "Water";
		} else if (fireCount == 0 && waterCount == 0) {
			return "No One";
		} else {
			return null; 
		}
	}
 
}