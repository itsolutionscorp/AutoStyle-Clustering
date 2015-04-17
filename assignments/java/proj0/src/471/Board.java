/**
 * @author Alice Zhang
 */

public class Board {
	private Piece selectedPiece,
				  removedPiece;
	private Piece[][] pieces;
	private boolean has_selected,
				   has_moved,
				   has_captured,
				   startTrackingWinner;
	private int turn, /** Fire = 0, Water = 1. Corresponds with Piece.side() */
			   	firePiecesLeft,
				waterPiecesLeft,
				selectedX,
				selectedY;


	public Board(boolean shouldBeEmpty) {
		has_selected = false;
		has_moved = false;
		has_captured = false;
		turn = 0;
		firePiecesLeft = 0;
		waterPiecesLeft = 0;
		startTrackingWinner = false;

		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			setOrigPieces(8, this);
		}
	}

	/** HELPER: ORIGINAL PIECE PLACEMENT
	  * Set original configuration of game pieces */

	private void setOrigPieces(int N, Board b) {
		firePiecesLeft = 12;
		waterPiecesLeft = 12;
		startTrackingWinner = true;

		for (int x = 0; x < 8; x++) {
        	if (x % 2 == 0) {
        		pieces[x][0] = new Piece(true, b, x, 0, "pawn");
      			pieces[x][2] = new Piece(true, b, x, 2, "bomb");
       			pieces[x][6] = new Piece(false, b, x, 6, "shield");
        	} else {
        		pieces[x][7] = new Piece(false, b, x, 7, "pawn");
       			pieces[x][5] = new Piece(false, b, x, 5, "bomb");
       			pieces[x][1] = new Piece(true, b, x, 1, "shield");
       		}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) { //out of bounds
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		/** Selecting empty square */
		if (pieceAt(x, y) == null) {
			/** If can move to this new square. */
			if (validMove(selectedX, selectedY, x, y) && has_selected) {
				/** If has selected but hasn't moved or has moved & captured (multicapture) */
				if (!has_moved) {
					return true;
				} else if ((selectedPiece != null) && has_moved && selectedPiece.hasCaptured()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		/** Selecting piece */
		else { 
			/** If selecting piece of right side (fire/water depending on turn) */
			if (pieceAt(x, y).side() == turn) {
				/** If hasn't selected yet OR has selected but hasn't moved (switching pieces) */
				if ((has_selected == false) || 
					((has_selected == true) && (has_moved == false))) {
					return true;
				}
			}
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((pieces[xi][yi] != null) && (pieces[xf][yf] == null)) {

			/** Fire Capturing */
			if (yf - yi == 2 && Math.abs(xf - xi) == 2) {
				int xM = ((xf + xi) / 2);
				int yM = ((yf + yi) / 2);
				if (pieces[xM][yM] != null && pieces[xM][yM].side() != pieces[xi][yi].side()) {
					return true;
				} else {
					return false;
				}
			/** Water Capturing */
			} else if (yf - yi == 2 && Math.abs(xf - xi) == 2) {
				int xM = ((xf + xi) / 2);
				int yM = ((yf + yi) / 2);
				if (pieces[xM][yM] != null && pieces[xM][yM].side() != pieces[xi][yi].side()) {
					return true;
				} else {
					return false;
				}
			/** King Capturing */
			} else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
				int xM = ((xf + xi) / 2);
				int yM = ((yf + yi) / 2);
				if (pieces[xM][yM] != null && pieces[xM][yM].side() != pieces[xi][yi].side()) {
					return true;
				} else {
					return false;
				}
			/** King adjacent move */
			} else if (pieces[xi][yi].isKing()) {
				if ((Math.abs(yf - yi) == 1) && Math.abs(xf - xi) == 1) {return true;} else {return false;}
			/** Fire adjacent move */
			} else if (pieces[xi][yi].isFire()) {
				if ((yf - yi == 1) && Math.abs(xf - xi) == 1) {return true;} else {return false;}
			/** Water adjacent move */
			} else {
				if ((yi - yf == 1) && Math.abs(xf - xi) == 1) {return true;} else {return false;}
			}

		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
		} else {
			selectedPiece.move(x, y);
			has_moved = true;
		}
		has_selected = true;
		selectedX = x;
		selectedY = y;
	}

	public void place(Piece p, int x, int y) {
		/** Check x & y not out of bounds */
		if ( !(x < 0 || x > 7 || y < 0 || y > 7) ) {
			if (pieceAt(x, y) != null) { 
				remove(x, y);
			}
			pieces[x][y] = p;
			selectedPiece = p;
			if (p.isFire()) {
				firePiecesLeft++;
			} else {
				waterPiecesLeft++;
			}
			startTrackingWinner = true;
		}
	}

	public Piece remove(int x, int y) {
		/** Out of bounds */
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Piece position out of bounds.");
			return null;
		/** No piece at position */
		} else if (pieceAt(x, y) == null) {
			System.out.println("Piece not valid.");
			return null;

		} else {
		removedPiece = pieceAt(x, y);

		if (removedPiece.isFire()) {
			firePiecesLeft = firePiecesLeft - 1;
		} else {
			waterPiecesLeft = waterPiecesLeft - 1;
		}

		pieces[x][y] = null;
		return removedPiece;
		}
	}

	public boolean canEndTurn() {
		return (has_moved || has_captured); 
	}

	public void endTurn() {
		/** Switch players */
		if (turn == 0) {
			turn = 1;
		} else {
			turn = 0;
		}
		selectedPiece.doneCapturing();
		selectedPiece = null;
		has_selected = false;
		has_moved = false;
	}

	public String winner() {
		if (startTrackingWinner == true) {
			if (firePiecesLeft == 0 && waterPiecesLeft == 0) {
				return "No one";
			} else if (firePiecesLeft == 0) {
				return "Water";
			} else if (waterPiecesLeft == 0) {
				return "Fire";
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}


	/** HELPER 1: DRAWS BOARD
	* i => x position, j => y position */

    private void drawBoard(int N) {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        	}
        }
    }

	/** HELPER 2: PIECE IMG PATHS */

	private String getImage(Piece p) {
		String pieceType,
			   img_path;

		if (p.isBomb()) {pieceType = "bomb";}
		else if (p.isShield()) {pieceType = "shield";}
		else {pieceType = "pawn";}

		if (p.isFire()) {
			img_path = pieceType + "-fire";
		} else {
			img_path = pieceType + "-water";
		}
		
		if (p.isKing()) {
			img_path = img_path + "-crowned";
		}

		img_path = "img/" + img_path + ".png";

		return img_path;
	}


	public static void main(String[] args) {
		int size = 8;


		StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);


		//initiates new board
		Board b = new Board(false); 
		b.drawBoard(size);

        //runs game, refreshes
		while (b.winner() == null) {

			/** Selecting */
			if (StdDrawPlus.mousePressed()) {
				int mouse_x = (int) StdDrawPlus.mouseX();
				int mouse_y = (int) StdDrawPlus.mouseY();

				if (b.canSelect(mouse_x, mouse_y) == true) {
					b.select(mouse_x, mouse_y);
					b.drawBoard(size);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(mouse_x + .5, mouse_y + .5, .5);
				}
			}

			/** End turn */
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
				b.drawBoard(size);
			}

			/** Draw all pieces */
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (b.pieceAt(x, y) != null) {
						StdDrawPlus.picture(x + .5, y + .5, b.getImage(b.pieceAt(x, y)), 1, 1);
					}
				}
			}
		}
	}
}