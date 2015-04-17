public class Board {

	/******************************************************************
	*                       A LOVELY TO-DO LIST                       *
	******************************************************************/


	/* Things to do:
	*
	* 0. Restrict where pieces can move to (DONE)
	* 1. Jumping/capturing pieces(DONE)
	*	a. allowing you to jump (DONE)
	*	b. removing jumped piece (DONE)
	* 	c. Letting player who has jumped continue jumping (DONE)
	*	
	*  		  NOTE: SHOULD CHECK TO SEE WHETHER THERE IS A PIECE BETWEEN DESTINATION AND CURRENT PLACE, LIKE IN MOVE...
	*		  ALSO NEED TO SEE WHETHER THE PIECE IS THE SAME COLOR OR A DIFFERENT COLOR
	*		  IF IT'S THE SAME COLOR, CAN'T JUMP, IF IT'S DIFFERENT, YOU CAN JUMP.
	*
	*		  MAYBE MAKE A HELPER THAT ASSEMBLES A LIST OF NEARBY PIECES????
	*
	* 2. Bombs & Shields & explosions (DONE)
	* 		NOTE: SEEMS LIKE IT WOULD BE SIMILAR TO THE JUMPING CODE--FIND OUT IF THERE ARE NEARBY PLAYERS
	* 3. Who wins, and what message is displayed? (DONE)
	* 4. Crowning kings. (DONE) 
	*	 a. Need to make sure ability to jump does not transfer to other pieces (DONE)



	/******************************************************************
	*              ALL OF OUR LOVELY PRIVATE VARIABLES                *
	******************************************************************/


	private Piece[][] pieces;
	private int startX = 0;
	private int startY =  0;
	private int finishX = 0;
	private int finishY = 0;
	private int fireCount = 0;
	private int waterCount = 0;
	private boolean fireTurn = true;
	private boolean selectedPiece = false;
	private boolean selectedSpace = false;
	private boolean moved = false;
	private boolean firstMove = true;
	private boolean someoneHasJumped = false;


	/************************************************************************
	* ALL OF THESE ARE PRIVATE HELPER FUNCTIONS--USED IN MAIN() & CANSELECT *
	************************************************************************/


	private void drawGray(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5);
	}

	private void drawRed(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5);
	}

	private void drawSelectWhite(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5);
	}

	private void drawBlankBoard() {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		pieces = new Piece[8][8];
		for (int row = 0; row < 8; row += 1) {
			for (int column = 0; column < 8.0; column += 1) {
				if ((row + column) % 2 == 0) {
					drawGray(column, row);
				} else {
					drawRed(column, row);
				}
			}
		}
	}

	private void drawFilledBoard() {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);

		for (int row = 0; row < 8; row += 1) {
			for (int column = 0; column < 8.0; column += 1) {
				if ((row + column) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(column + 0.5, row + 0.5, .5);
					if (row == 0) {
						StdDrawPlus.picture(column + 0.5, row + 0.5, "img/pawn-fire.png", 1, 1);
					} else if (row == 1) {
						StdDrawPlus.picture(column + 0.5, row + 0.5, "img/shield-fire.png", 1, 1);
					} else if (row == 2) {
						StdDrawPlus.picture(column + 0.5, row + 0.5, "img/bomb-fire.png", 1, 1);	
					} else if (row == 7) {
						StdDrawPlus.picture(column + 0.5, row + 0.5, "img/pawn-water.png", 1, 1);
					} else if (row == 6) {
						StdDrawPlus.picture(column + 0.5, row + 0.5, "img/shield-water.png", 1, 1);
					} else if (row == 5) {
						StdDrawPlus.picture(column + 0.5, row + 0.5, "img/bomb-water.png", 1, 1);	
					}			
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(column + 0.5, row + 0.5, .5);
				}
			}
		}
	}

	private void pieceMissing(int x, int y) {
		System.out.println("There is no piece there.");
	}

	private void pieceOutOfBounds(int x, int y) {
		System.out.println("Why'd you click all the way over there?");
	}

	private void reDrawPieces(int x, int y) {
		if (pieceAt(x, y) == null) {
			return;
		}
		if (pieceAt(x, y).isKing() == true) {
			if (pieceAt(x, y).isFire() == false) {
				if (pieceAt(x, y).isBomb() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water-crowned.png", 1, 1);
				} else if (pieceAt(x, y).isShield() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water-crowned.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water-crowned.png", 1, 1);
				}
			} else {
				if (pieceAt(x, y).isBomb() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire-crowned.png", 1, 1);
				} else if (pieceAt(x, y).isShield() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire-crowned.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire-crowned.png", 1, 1);
				}
			}
		} else {
			if (pieceAt(x, y).isFire() == false) {
				if (pieceAt(x, y).isBomb() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water.png", 1, 1);
				} else if (pieceAt(x, y).isShield() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", 1, 1);
				}
			} else {
				if (pieceAt(x, y).isBomb() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire.png", 1, 1);
				} else if (pieceAt(x, y).isShield() == true) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", 1, 1);
				}
			}
		}
	}


	/* These check to see whether there are pieces adjacent to the selected piece at (OriginX, OriginY).
	* They know whether the piece is a king or not */

	private boolean checkNormalFireJump(Piece p, int originX, int originY, int endX, int endY) {
		if ((endX == originX + 2) && (endY == originY + 2)) { // checks space to the upper right
			if ((pieceAt(originX + 1, originY + 1) != null) && (pieceAt(originX + 1, originY + 1).isFire() == false)) {
				return true;
			}
		} else if ((endX == originX - 2) && (endY == originY + 2)) { // checks space to the upper left
			if ((pieceAt(originX - 1, originY + 1) != null) && (pieceAt(originX - 1, originY + 1).isFire() == false)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	private boolean checkNormalWaterJump(Piece p, int originX, int originY, int endX, int endY) {
		if ((endX == originX - 2) && (endY == originY - 2)) { // checks space to the lower left
			if ((pieceAt(originX - 1, originY - 1) != null) && (pieceAt(originX - 1, originY - 1).isFire() == true)) {
				return true;
			}
		} else if ((endX == originX + 2) && (endY == originY - 2)) { // checks space to the lower right
			if ((pieceAt(originX + 1, originY - 1) != null) && (pieceAt(originX + 1, originY - 1).isFire() == true)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	private boolean checkKingJump(boolean fire, Piece p, int originX, int originY, int endX, int endY) { 
		if (fire) {
			if (checkNormalFireJump(p, originX, originY, endX, endY)) {
				return true;
			} else if ((endX == originX - 2) && (endY == originY - 2)) { // checks space to the lower left
				if ((pieceAt(originX - 1, originY - 1) != null) && (pieceAt(originX - 1, originY - 1).isFire() == false)) {
					return true;
				}
			} else if ((endX == originX + 2) && (endY == originY - 2)) { // checks space to the lower right
				if ((pieceAt(originX + 1, originY - 1) != null) && (pieceAt(originX + 1, originY - 1).isFire() == false)) {
					return true;
				}
			} else {
				return false;
			}
		} else {
			if (checkNormalWaterJump(p, originX, originY, endX, endY)) {
				return true;
			} else if ((endX == originX + 2) && (endY == originY + 2)) { // checks space to the upper right
				if ((pieceAt(originX + 1, originY + 1) != null) && (pieceAt(originX + 1, originY + 1).isFire() == true)) {
					return true;
				}
			} else if ((endX == originX - 2) && (endY == originY + 2)) { // checks space to the upper left
				if ((pieceAt(originX - 1, originY + 1) != null) && (pieceAt(originX - 1, originY + 1).isFire() == true)) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}



	/******************************************************************
	*          THIS MARKS THE START OF NON-PRIVATE CODE               *
	******************************************************************/


	public static void main(String args[]) {

		boolean test = false;
		Board b = new Board(test);
		b.drawFilledBoard();
		
		while (true) {
			if (b.winner() == null) {
				if (StdDrawPlus.mousePressed()) {
					int yPos = (int) (StdDrawPlus.mouseY());
					int xPos = (int) (StdDrawPlus.mouseX());

					if (b.canSelect(xPos, yPos)) {
						if (b.pieceAt(xPos, yPos) != null) {
							// If we click different piece of the same color, allows Select to be called to move the second piece
							if ((b.selectedPiece == true) && (((b.pieceAt(xPos, yPos).isFire() == true) && (b.fireTurn)) || ((b.pieceAt(xPos, yPos).isFire() == false) && (b.fireTurn == false)))) {
								System.out.println("We're changing pieces.");
								b.selectedPiece = false;
							}
						}
						if (b.selectedPiece == false) {
							// Redraws the starting square when we select a different piece of the same type
							if ((b.startX + b.startY) % 2 == 0) {
								b.drawGray(b.startX, b.startY);
								b.reDrawPieces(b.startX, b.startY);
							}
							b.select(xPos, yPos);
						} else if ((b.pieceAt(xPos, yPos) == null) && (b.selectedPiece)) {
							b.select(xPos, yPos);
							System.out.println("We've finished selecting a blank at " + xPos + ", " + yPos);
						}
					}
				}

				if (StdDrawPlus.isSpacePressed()) {
					b.endTurn();
				}

				if (b.selectedPiece == true) {
					b.drawSelectWhite(b.startX, b.startY);
					b.reDrawPieces(b.startX, b.startY);
				}

				StdDrawPlus.show(15);
				for (int row = 0; row < 8; row += 1) {
					for (int column = 0; column < 8.0; column += 1) {
						if ((row + column) % 2 == 0) {
							b.drawGray(column, row);
							b.reDrawPieces(column, row);
						} else {
							b.drawRed(column, row);
							b.reDrawPieces(column, row);
						}
					}
				}
			} else {
				System.out.println(b.winner());
				return;
			}
		}
	}


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			pieces = new Piece[8][8];
			for (int row = 0; row < 8; row += 1) {
				for (int column = 0; column < 8; column += 1) {
					pieces[column][row] = null;
				}
			}
		} else {
			pieces = new Piece[8][8];
			for (int row = 0; row < 8; row += 1) {
				for (int column = 0; column < 8; column += 1) {
					if ((row + column) % 2 == 0) {
						pieces[column][row] = null;
						if (row == 0) {
							pieces[column][row] = new Piece(true, this, column, row, "pawn");
						} else if (row == 1) {
							pieces[column][row] = new Piece(true, this, column, row, "shield");
						} else if (row == 2) {
							pieces[column][row] = new Piece(true, this, column, row, "bomb");
						} else if (row == 7) {
							pieces[column][row] = new Piece(false, this, column, row, "pawn");
						} else if (row == 6) {
							pieces[column][row] = new Piece(false, this, column, row, "shield");
						} else if (row == 5) {
							pieces[column][row] = new Piece(false, this, column, row, "bomb");
						}			
					} else {
						pieces[column][row] = null;
					}
				}
			}
		}
	}


	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || y < 0 || x < 0 || (pieces[x][y] == null)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}


	public boolean canSelect(int x, int y) {
		if ((pieceAt(x, y) != null) && (someoneHasJumped == false) && (selectedSpace == false)) {
			System.out.println("We're thinking about moving the piece at " + x + ", " + y);
			if (((fireTurn) && (pieceAt(x, y).isFire())) || ((fireTurn == false) && (pieceAt(x, y).isFire() == false))) {
				System.out.println("Yay, we can select this piece.");
				return true;
			} else {
				return false;
			}
		} else if ((pieceAt(x, y) == null) && ((x + y) % 2 == 0)) { // Restricts movement to gray spaces
			if ((selectedPiece == true) && (selectedSpace == false)) {
				System.out.println("We're checking a blank at " + x + ", " + y + " from " + startX + ", " + startY);
				if ((fireTurn) && (pieceAt(startX, startY).isFire())) { // if this is FIRE's turn
					if (((x == startX + 1) || (x == startX - 1)) && (y == startY + 1) && (pieceAt(startX, startY).hasCaptured() == false)) { // normal turn, unkinged, not jumping
						System.out.println("I am normal fire, looking to MOVE from " + startX + ", " + startY);
						return true;
					} else if (checkNormalFireJump(pieceAt(startX, startY), startX, startY, x, y)) { // checking if we can jump with a normal piece
						System.out.println("I have fire jumped from " + startX + ", " + startY);
						return true;
					} else if (pieceAt(startX, startY).isKing() == true) { // turn with kinged piece
						System.out.println("I am a fire king, looking to MOVE from " + startX + ", " + startY);
						if (((x == startX + 1) || (x == startX - 1)) && ((y == startY - 1) || (y == startY + 1)) && (pieceAt(startX, startY).hasCaptured() == false)) { // normal move with king
							System.out.println("I am a fire king, MOVING from " + startX + ", " + startY);
							return true;
						} else if (checkKingJump(true, pieceAt(startX, startY), startX, startY, x, y)) { // king jumping
							System.out.println("I am a fire king, JUMPING from " + startX + ", " + startY);
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if ((fireTurn == false) && (pieceAt(startX, startY).isFire() == false)) { // if it's WATER's turn
					if (((x == startX + 1) || (x == startX - 1)) && (y == startY - 1) && (pieceAt(startX, startY).hasCaptured() == false)) { // normal turn, no jumps, unkinged
						System.out.println("I am normal water, looking to MOVE from " + startX + ", " + startY);
						return true;
					} else if (checkNormalWaterJump(pieceAt(startX, startY), startX, startY, x, y)) { // checking if we can jump with a normal piece
						System.out.println("I have water JUMPED from " + startX + ", " + startY);
						return true;
					} else if (pieceAt(startX, startY).isKing() == true) { // turn with kinged piece
						System.out.println("I am a water king, looking to MOVE from " + startX + ", " + startY);
						if (((x == startX + 1) || (x == startX - 1)) && ((y == startY - 1) || (y == startY + 1)) && (pieceAt(startX, startY).hasCaptured() == false)) { // normal move with king
							System.out.println("I am a water king, MOVING from " + startX + ", " + startY);
							return true;
						} else if (checkKingJump(false, pieceAt(startX, startY), startX, startY, x, y)) { // king jumping
							System.out.println("I am a water king, JUMPING from " + startX + ", " + startY);
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void select(int x, int y) {

		if (pieceAt(x, y) != null) {
			// If we click different piece of the same color, allows Select to be called to move the second piece
			if ((selectedPiece == true) && (((pieceAt(x, y).isFire() == true) && (fireTurn)) || ((pieceAt(x, y).isFire() == false) && (fireTurn == false)))) {
				System.out.println("We're changing pieces.");
				selectedPiece = false;
			}
		}

		if (selectedPiece == false) {
			startX = x;
			startY = y;
			System.out.println("We're selecting a piece at " + startX + ", " + startY);
			selectedPiece = true;
			selectedSpace = false;
			firstMove = false;
		} else {
			System.out.println("We're selecting a blank at " + x + ", " + y);
			finishX = x;
			finishY = y;
			selectedSpace = true;
			selectedPiece = false;
			pieceAt(startX, startY).move(x, y);
			moved = true;

			if (pieceAt(x, y) != null) {
				if (pieceAt(x, y).hasCaptured() == true) { //if we've captured a piece, we can select another space
					System.out.println("We've captured a piece, and can continue selecting from " + startX + ", " + startY);
					startX = x;
					startY = y;
					selectedSpace = false;
					selectedPiece = true;
					someoneHasJumped = true;
				}
			}
		}
	}


	public void place(Piece p, int x, int y) {
		if ((p == null) || x > 7 || y > 7 || x < 0 || y < 0) {
			return;
		} else {
			System.out.println("We're trying to place a piece at " + x + ", " +y);
			if ((p.isBomb()) && (p.hasCaptured())) {
				System.out.println("But that piece is a bomb, so we can't.");
				pieces[x][y] = null;
			} else {
				if (pieceAt(x, y) != null) {
					remove(x, y);
				}

				if (firstMove == false) {
					pieces[startX][startY] = null;
				}
				pieces[x][y] = p;
			}
		}
	}


	public Piece remove(int x, int y) {
		if (pieceAt(x, y) == null) {
			pieceMissing(x, y);
			return null;
		} else if (x > 7 || y > 7 || x < 0 || y < 0) {
			pieceOutOfBounds(x, y);
			return null;
		} else {
			Piece removedPiece = pieceAt(x, y);
			pieces[x][y] = null;
			return removedPiece;
		}
	}


	public boolean canEndTurn() {
		if (moved == true) {
			System.out.println("We can end our turn.");
 			return true;
 		} else {
 			return false;
 		}
	}


	public void endTurn() {
		if (canEndTurn()) {
			if (fireTurn == true) {
				fireTurn = false;
			} else {
				fireTurn = true;
			}

			moved = false;
			selectedSpace = false;
			selectedPiece = false;
			someoneHasJumped = false;

			if (pieceAt(finishX, finishY) != null) {
				pieceAt(finishX, finishY).doneCapturing();
			}
		}
	}


	public String winner() {
		fireCount = 0;
		waterCount = 0;
		for (int row = 0; row < 8; row += 1) {
			for (int column = 0; column < 8; column += 1) {
				if (pieceAt(column, row) != null) {
					if (pieceAt(column, row).isFire()) {
						fireCount += 1;
					} else {
						waterCount += 1;
					}
				}
			}
		}
		if ((waterCount == 0) && (fireCount > 0)) {
			return "Fire";
		} else if ((fireCount == 0) && (waterCount > 0)) {
			return "Water";
		} else if ((fireCount == 0) && (waterCount == 0)) {
			return "No one";
		} else {
			return null;
		}
	}
}