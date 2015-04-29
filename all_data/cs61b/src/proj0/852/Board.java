public class Board {
	private static final int dim = 8;
	private static final int initPieces = 12;
	private int numF; // best to track this as game progresses
	private int numW; // best to track this as game progresses
	private Piece[][] gamePieces = new Piece[dim][dim];
	private boolean fireTurn;
	private boolean movedThisTurn;
	private boolean selectedPieceThisTurn;
	private int[][] sCoords = new int[2][2]; // lastPIECE:(x,y), lastSPACE:(x,y)

	public static void main(String[] args) {
		// construct standard board
		Board b = new Board(false);
		// draw normal 8x8 board
		b.drawNormalBoard(dim);
		// BEGIN GAME
		while (true) {
			// INFINITE GAME
			while (true) {
				b.drawNormalBoard(dim);
				// SINGLE-TURN-LOOP
				if (StdDrawPlus.mousePressed()) {
						int pollX = (int) StdDrawPlus.mouseX();
						int pollY = (int) StdDrawPlus.mouseY();
						boolean xOK = ((pollX >= 0) && (pollX < dim));
						boolean yOK = ((pollY >= 0) && (pollY < dim));
						boolean pollOK = (xOK && yOK);
						boolean selectOK = (pollOK && b.canSelect(pollX,pollY));
				 		if (selectOK) {
				 			b.select(pollX,pollY);
				 		}
				}
				if (StdDrawPlus.isSpacePressed()) {
					if (b.canEndTurn()) {
						b.endTurn();
						break; // break this while loop
					}
				}
				StdDrawPlus.show(100);
			}
			if (b.winner() != null) {
				System.out.println(b.winner());
				break; // end game loop
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			this.numF = 0;
			this.numW = 0;
			this.fireTurn = true;
			movedThisTurn = false;
			// make the first selection an invalid piece selection
			sCoords[0][0] = 1;
			sCoords[0][1] = 0;
			// these coordinates should cause any problems(?)
			sCoords[1][0] = 0;
			sCoords[1][1] = 0;
		} else {
			// construct board normally
			this.numF = initPieces;
			this.numW = initPieces;
			fireTurn = true;
			movedThisTurn = false;
			// make the first selection an invalid piece selection
			sCoords[0][0] = 1;
			sCoords[0][1] = 0;
			// these coordinates should cause any problems(?)
			sCoords[1][0] = 0;
			sCoords[1][1] = 0;
			
			int i = 0;
			boolean isFire = (i == 0);
			this.gamePieces[0][0] = new Piece(isFire, this, 0, 0, "pawn"); 
			this.gamePieces[2][0] = new Piece(isFire, this, 2, 0, "pawn");
			this.gamePieces[4][0] = new Piece(isFire, this, 4, 0, "pawn");
			this.gamePieces[6][0] = new Piece(isFire, this, 6, 0, "pawn");

			this.gamePieces[1][1] = new Piece(isFire, this, 1, 1, "shield");
			this.gamePieces[3][1] = new Piece(isFire, this, 3, 1, "shield");
			this.gamePieces[5][1] = new Piece(isFire, this, 5, 1, "shield");
			this.gamePieces[7][1] = new Piece(isFire, this, 7, 1, "shield");

			this.gamePieces[0][2] = new Piece(isFire, this, 0, 2, "bomb");
			this.gamePieces[2][2] = new Piece(isFire, this, 2, 2, "bomb");
			this.gamePieces[4][2] = new Piece(isFire, this, 4, 2, "bomb");
			this.gamePieces[6][2] = new Piece(isFire, this, 6, 2, "bomb");

			this.gamePieces[1][5] = new Piece(!isFire, this, 1, 5, "bomb");
			this.gamePieces[3][5] = new Piece(!isFire, this, 3, 5, "bomb");
			this.gamePieces[5][5] = new Piece(!isFire, this, 5, 5, "bomb");
			this.gamePieces[7][5] = new Piece(!isFire, this, 7, 5, "bomb");

			this.gamePieces[0][6] = new Piece(!isFire, this, 0, 6, "shield");
			this.gamePieces[2][6] = new Piece(!isFire, this, 2, 6, "shield");
			this.gamePieces[4][6] = new Piece(!isFire, this, 4, 6, "shield");
			this.gamePieces[6][6] = new Piece(!isFire, this, 6, 6, "shield");

			this.gamePieces[1][7] = new Piece(!isFire, this, 1, 7, "pawn"); 
			this.gamePieces[3][7] = new Piece(!isFire, this, 3, 7, "pawn");
			this.gamePieces[5][7] = new Piece(!isFire, this, 5, 7, "pawn");
			this.gamePieces[7][7] = new Piece(!isFire, this, 7, 7, "pawn");
		}
	}

	private void drawBlankBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i = i +1) {
            for (int j = 0; j < N; j = j + 1) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawNormalBoard(int N) {
    	drawBlankBoard(N);
    	// OKAY! Now need to place pieces.
    	for (int i = 0; i < N; i += 1) {
    		for (int j = 0; j < N; j += 1) {
    			if (gamePieces[i][j] != null) {
    				// if the piece exists, plot it!
	    			String fileString = constructFileString(gamePieces[i][j]);
	    			// have string, now add to board
	    			StdDrawPlus.picture(i + .5, j + .5, fileString, 1, 1);
    			}
    		}
    	}
    	if (selectedPieceThisTurn) {
    		newSelectionGFX(0,0,sCoords[0][0],sCoords[0][1]);
    	}
    }

    private String constructFileString(Piece myPiece) {
    	String fileS = "img/";
    	String typeS = "";
    	String teamS = "";
    	String royalS = "";
		// determine type
		if (myPiece.isShield()) {
			typeS = "shield-";
		} else if (myPiece.isBomb()) {
			typeS = "bomb-";
		} else {
			typeS = "pawn-";
		}
		// determine team
		if (myPiece.isFire()) {
			teamS = "fire";
		} else {
			teamS = "water";
		}
		// determine if royal
		if (myPiece.isKing()) {
			royalS = "-crowned";
			// if not royal then leave as empty string
		}
		fileS = fileS + typeS + teamS + royalS + ".png";
		return fileS;
    }

	private boolean moveLegal(Piece p, int xi, int yi, int xf, int yf) {
		 int mc = moveType(xi, yi, xf, yf);
		 if (mc == 3) {
		 	// move definitely illegal
		 	return false;
		 }
		 // the move is illegal until shown otherwise
		 boolean moveCase = false;
		 // construct requirements for moves
		 boolean oneStepReq  = false;
		 boolean twoStepReq = false;
		 boolean destinationOpen = (this.gamePieces[xf][yf] == null);
		 if (p.isKing()) {
		 	oneStepReq = (mc == 11 || mc == 12);
		 	twoStepReq = (mc == 21 || mc == 22);
		 } else if (p.isFire()) {
		 	oneStepReq = (mc == 11);
		 	twoStepReq = (mc == 21);
		 } else {
		 	oneStepReq = (mc == 12);
		 	twoStepReq = (mc == 22);
		 }
		 // hear the case for the move
		 if (destinationOpen) {
			 if (oneStepReq) {
			 	moveCase = true;
			 }	else if (twoStepReq) {
			 	boolean up = (yf - yi > 0);
			 	boolean right = (xf - xi > 0);
			 	if (up && right) {
		 			if (gamePieces[xi+1][yi+1] != null) {
		 				moveCase = (gamePieces[xi+1][yi+1].isFire() != p.isFire());
		 			}
		 		} else if (!up && right) {
		 			if (gamePieces[xi+1][yi-1] != null) {
		 				moveCase = (gamePieces[xi+1][yi-1].isFire() != p.isFire());
		 			}
		 		} else if (up && !right) {
		 			if (gamePieces[xi-1][yi+1] != null) {
		 				moveCase = (gamePieces[xi-1][yi+1].isFire() != p.isFire());
		 			}
		 		} else if (!up && !right) {
		 			if (gamePieces[xi-1][yi-1] != null) {
		 				moveCase = (gamePieces[xi-1][yi-1].isFire() != p.isFire());
		 			}
		 		}
		 		// we've let the piece make it's case!
		 	}
		 }
		 return moveCase;

	}

	private int moveType(int xi, int yi, int xf, int yf) {
		/* MOVE CODES for "moveType"
		 *		11 --> one step diagonal up
		 *		12 --> one step diagonal down
		 *		21 --> two step diagonal (jump) up
		 *		22 --> two step diagonal (jump) down
		 *		3 --> off-diagonal or out-of-bounds
		 */
		boolean xiOK = (0 <= xi) && (xi < dim);
		boolean xfOK = (0 <= xf) && (xf < dim);
		boolean yiOK = (0 <= yi) && (yi < dim);
		boolean yfOK = (0 <= yf) && (yf < dim);
		boolean moveWithinBounds = ((xiOK && xfOK) && (yiOK && yfOK));
		// from here, assume within bounds
		if (moveWithinBounds) {
			int absDX = Math.abs(xf - xi);
			int absDY = Math.abs(yf - yi);
			boolean up = (yf  - yi > 0);
			boolean oneStepDiag = ((absDX == 1) && (absDY == 1));
			boolean twoStepDiag = ((absDX == 2) && (absDY == 2));
			if (oneStepDiag && up) {
				return 11;
			} else if (oneStepDiag && !up) {
				return 12;
			} else if (twoStepDiag && up) {
				return 21;
			} else if (twoStepDiag && !up) {
				return 22;
			} else {
				// within bounds, but invalid move
				return 3;
			}	
		} else {
			// move wasn't within bounds
			return 3;
		}
	}

	public void select(int x, int y) {
		if (pieceAt(x,y) == null) {
			int oldX = sCoords[0][0];
			int oldY = sCoords[0][1];
			// assume that a piece is already selected
			Piece p = pieceAt(oldX,oldY);
			// trying to select an empty square, update emptyCoords
			this.sCoords[1][0] = x;
			this.sCoords[1][1] = y;
			// also, it must be that this executes a move.
			p.move(x,y);
			this.movedThisTurn = true;
			this.sCoords[0][0] = x;
			this.sCoords[0][1] = y;
		} else {
			this.sCoords[0][0] = x;
			this.sCoords[0][1] = y;
			this.selectedPieceThisTurn = true;
		}
	}

	public boolean canSelect(int x, int y) {
 		if (pieceAt(x,y) != null) {
 			// trying to select a piece, can only select own pieces
 			Piece p = pieceAt(x,y);
 			if ((p.isFire() == fireTurn)) {
 				if (!movedThisTurn) {
 					return true;
 				} else {
 					return false;
 				}
 			} else {
 				return false;
 			}
 		} else if (selectedPieceThisTurn) {
 			/* CANNOT select a blank space unless a piece already selected
 			 *	Did we select a piece already? Has the piece moved? 
 			 *	If it has moved, did it capture? is the proposed move
 			 *	a capture move?
 			 */ 
 			int px = sCoords[0][0];
 			int py = sCoords[0][1];
 			if (pieceAt(px,py) == null) {
 				return false;
 				// can't select a blank spot without selecting a piece first
 			} else {
 				// we HAVE selected a piece this turn. Call last (current?) piece.
 				Piece p = pieceAt(px, py);
 				// given the current piece, can we move it to the new location?
 				boolean moveOK = moveLegal(p, px, py, x, y);
 				int mc = moveType(px, py, x, y);
 				// it's a valid capture only if MOVE CODE == 21 or 22 and move is legal
 				boolean mcValid = (mc == 21 || mc == 22);
 				boolean capMove = (mcValid && moveOK);
	 			if (!movedThisTurn && moveOK) {
	 				return true;
	 			} else if (p.hasCaptured() && capMove) {
	 				return true;
	 			} else {
	 				return false;
	 			}
	 		}
 		} else {
 			return false;
 		}
	}

	public Piece pieceAt(int x, int y) {
		/* Gets the piece at position (x, y) on the board, 
		 * or null if there is no piece. If (x, y) are out of 
		 * bounds, returns null.
		 */
		boolean xOK = ((x >= 0) && (x < dim));
		boolean yOK = ((y >= 0) && (y < dim));
		boolean pieceExists = false;
		if (xOK && yOK) {
			pieceExists = (this.gamePieces[x][y] != null);
		}
		boolean allOK = (xOK && yOK && pieceExists);
		if (allOK) {
			return this.gamePieces[x][y];
		} else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		/* Places p at (x,y). Does nothing if x,y OOB or p = null.
		 * if there is an existing piece at (x,y), this method replaces that piece.
		 */
		boolean xOK = ((x >= 0) && (x < dim));
		boolean yOK = ((y >= 0) && (y < dim));
		boolean pReal = (p != null);
		if (xOK && yOK && pReal) {
			this.gamePieces[x][y] = p;
			if (p.isFire()) {
				this.numF = this.numF + 1;
			} else {
				this.numW = this.numW + 1;
			}
		}
	}

	public Piece remove(int x, int y) {
		/* removes a piece from the board.
		 * returns the piece removed.
		 * (x,y) OOB --> return null and print error message
		 */
		if (gamePieces[x][y] != null) {
			// get piece information
			boolean deadFire = this.gamePieces[x][y].isFire();
			String deadType = "";
			if (gamePieces[x][y].isShield()) {
				deadType = "shield";
			} else if (gamePieces[x][y].isBomb()) {
				deadType = "bomb";
			} else {
				deadType = "pawn";
			}
			// delete piece from board, decrement team count, and return.
			Piece deadPiece = new Piece(deadFire, this, x, y, deadType);
			gamePieces[x][y] = null;
			if (deadFire) {
				numF = numF - 1;
			} else {
				numW = numW - 1;
			}
			return deadPiece;
		} else {
			System.out.println("ERROR: Attempting to remove a piece that does not exist.");
			return null;
		}
	}

	public boolean canEndTurn() {
		/* return whether or not player can end turn
		 * a turn can only end when a piece is moved or performed a capture
		 */
		if (movedThisTurn) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		// need to de-highlight the last piece
		int x = sCoords[0][0];
		int y = sCoords[0][1];
		// fireTurn, movedThisTurn, capSomeoneThisTurn (piece) all need updating
		this.fireTurn = (!this.fireTurn);
		this.movedThisTurn = false;
		if (this.gamePieces[x][y] != null) {
			this.gamePieces[x][y].doneCapturing();
		}
		this.selectedPieceThisTurn = false;
	}

	public String winner() {
		/* Returns "Fire", or "Water", or "No one" as appropriate
		 * return null if game not over
		 * don't consider stalemate situation (return null)
		 * The winner is team with most number of pieces
		 */
		boolean fireGone = (numF == 0);
		boolean waterGone = (numW == 0);
		if (fireGone && waterGone) {
			return "No one";
		} else if (fireGone) {
			return "Water";
		} else if (waterGone) {
			return "Fire";
		} else {
			return null;
		}
	}

	private void oneStepGFX(int xi, int yi, int xf, int yf) {
		// first, make destination white
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(xf + 0.5, yf + 0.5, 0.5);
		// then place moving-piece-image on destination
		String s = constructFileString(pieceAt(xf,yf));
		StdDrawPlus.picture(xf + 0.5, yf + 0.5, s, 1, 1);
		// restore newly-nulled-source square
		restoreSquareGFX(xi, yi);
	}

	private void captureGFX(int xi, int yi, int xc, int yc, int xf, int yf) {
		// assumes newly captured piece's square now null
		// first, make destination white
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(xf + 0.5, yf + 0.5, 0.5);
		// then place moving-piece-image on destination
		String s = constructFileString(pieceAt(xf,yf));
		StdDrawPlus.picture(xf + 0.5, yf + 0.5, s, 1, 1);
		// restore newly-nulled-captured square
		restoreSquareGFX(xc, yc);
		// restore newly-nulled-source square
		restoreSquareGFX(xi, yi);
	}

	private void restoreSquareGFX(int x, int y) {
		// definitely fix color
		if (((x + y) % 2 == 0)) {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
		// if there is a piece there, put it back
		if (pieceAt(x,y) != null ) {
			String s = constructFileString(pieceAt(x,y));
			StdDrawPlus.picture(x + 0.5, y + 0.5, s, 1, 1);
		}
	}

	private void newSelectionGFX(int xO, int yO, int xN, int yN) {
		// switches highlighting from one square to another
		// pass xO = 0 and yO = 0 if making a first highlight
		restoreSquareGFX(xO, yO);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		if (pieceAt(xN, yN) != null) {
			String s = constructFileString(pieceAt(xN, yN));
			StdDrawPlus.filledSquare(xN + 0.5, yN + 0.5, 0.5);
			StdDrawPlus.picture(xN + 0.5, yN + 0.5, s, 1, 1);
		}
	}

}