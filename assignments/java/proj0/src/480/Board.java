/** Board where the game is played
 *  @author Raymond Chan
 */

public class Board {


	private Piece[][] board;
	private int numFirePieces = 0;
	private int numWaterPieces = 0;
	private Piece selectedPiece = null;
	private boolean selectedMoved = false;
	private boolean multiCapPossible = false;
	private static int dimension = 8;
	private int numOfTurns = 0;
	private  boolean fireTurn() {
		return (numOfTurns % 2 == 0);
	}


    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, dimension);
        StdDrawPlus.setYscale(0, dimension);
        Board mainboard = new Board(false);

        /** Monitors for mouse and space bar presses. */

        while(true) {
            mainboard.drawBoard(dimension);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if (mainboard.canSelect((int) x, (int) y)) {		
                	mainboard.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (mainboard.canEndTurn()) { 
            		mainboard.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }
    }


	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					if (pieceAt(i, j) == selectedPiece && selectedPiece != null) {
						if (selectedPiece.isBomb() && selectedPiece.hasCaptured()){
							StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
						} else {
							StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
							drawPiece(board[i][j], i, j);
						}
					} else if(pieceAt(i, j) != selectedPiece || selectedPiece == null) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		
				Piece piece = board[i][j];
				drawPiece(piece, i, j);
			}
		}
	}

	public Board (boolean shouldBeEmpty) {
		board = new Piece[dimension][dimension];
		if (!shouldBeEmpty) {
			initialPieces();
		}
	}

	/** Sets up pieces at the beginning of an 8 x 8 game. */
    private void initialPieces() {
    	for (int i = 0; i < dimension; i += 2) {
			board[i][0] = new Piece(true, this, i, 0, "pawn");
			board[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
			board[i][2] = new Piece(true, this, i, 2, "bomb");
			board[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
			board[i][6] = new Piece(false, this, i, 6, "shield");
			board[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
		}
		numWaterPieces = 12;
		numFirePieces = 12;
    }


    /** Draw piece to position (x, y) */
	private void drawPiece(Piece piece, int x, int y) {
		if (piece != null) {
		
			if (piece.isFire()) {
				if (piece.isBomb()) {
					if (piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire-crowned.png", 1, 1);
					} else if (!piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire.png", 1, 1);
					}
				} else if (piece.isShield()) {
					if (piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire-crowned.png", 1, 1);
					} else if (!piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", 1, 1);
					}
				} else if (!piece.isBomb() && !piece.isShield()) {
					if (piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire-crowned.png", 1, 1);	
					} else if (!piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", 1, 1);
					}
				}
			} else if (!piece.isFire()) {
				if (piece.isBomb()) {
					if (piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water-crowned.png", 1, 1);
					} else if (!piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water.png", 1, 1);
					}
				} else if (piece.isShield()) {
					if (piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water-crowned.png", 1, 1);
					} else if (!piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", 1, 1);
					}
				} else if (!piece.isBomb() && !piece.isShield()) {
					if (piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water-crowned.png", 1, 1);	
					} else if (!piece.isKing()) {
						StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", 1, 1);
					}
				}
			}
		}
	}


	public Piece pieceAt(int x, int y) {
		if ((x < dimension) && (y < dimension) && (x >= 0) && (y >= 0)) { 
			return board[x][y];
		} else {
			return null;
		}
	}


	public void place(Piece p, int x, int y) {
		if (((x < dimension) && (y < dimension) && (x >= 0) && (y >= 0)) && (p != null)) {
			Piece piece;
			for (int i = 0; i < dimension; i ++) {
				for (int j = 0; j < dimension; j ++) {
					piece = board[i][j];
					if (piece == p) {
						remove(i, j);
						selectedMoved = true;
					} 
				}
			}
			board[x][y] = p;
			if (p.isFire()) {
				numFirePieces += 1;
			} else if (!p.isFire()) {
				numWaterPieces += 1;
			}
		}
	}


	public Piece remove(int x, int y) {
		if ((x < dimension) && (y < dimension) && (x >= 0) && (y >= 0)) {
			Piece p = board[x][y];
			if (p == null) {
				System.out.println("Cannot remove a piece if it does not exist");
				return null;
			} else {
				board[x][y] = null;
				if (p.isFire()) {
					numFirePieces -= 1;
				} else if (!p.isFire()) {
					numWaterPieces -= 1;
				}
		 		return p;
			}
		} else {
			System.out.println("Cannot remove a piece that is not on the board.");
			return null;
		}
	}



	/** Selects sqaure at (x, y) assuming that canSelect is true */
	public void select(int x, int y) {

		if (board[x][y] != null) {
			selectedPiece = board[x][y];
		} else if (selectedPiece != null) {
			selectedPiece.move(x, y);
			
			if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {

			} else {
				board[x][y] = selectedPiece;
			}
		}
	}


	public boolean canSelect(int x, int y) {
		if ((x < dimension) && (y < dimension) && (x >= 0) && (y >= 0)) {
			if (board[x][y] != null) {
				
				if ((fireTurn() && board[x][y].isFire()) || (!fireTurn() && !board[x][y].isFire())) {
					if (selectedPiece == null) {
						return true;
					} else if (!selectedMoved) {
						return true;
					}
				}
			} else if (selectedPiece != null) {
				int xPos = 0;
				int yPos = 0;
				for (int i = 0; i < dimension; i += 1) {
					for (int j = 0; j < dimension; j += 1) {
						if (board[i][j] == selectedPiece) {
							xPos = i;
							yPos = j;
						}
					}
				}
				if (validMove(xPos, yPos, x, y)) {
					if (!selectedMoved || multiCapPossible) {
						if (selectedPiece.isKing()) {
							if ((xPos + 2 == x) && (yPos + 2 == y) && pieceAt((xPos + 1), (yPos + 1)) != null) {
								return board[xPos + 1][yPos + 1].isFire() == !fireTurn();
							} else if ((xPos + 2 == x) && (yPos - 2 == y) && pieceAt((xPos + 1), (yPos - 1)) != null) {
								return board[xPos + 1][yPos - 1].isFire() == !fireTurn();
							} else if ((xPos - 2 == x) && (yPos + 2 == y) && pieceAt((xPos - 1), (yPos + 1)) != null) {
								return board[xPos - 1][yPos + 1].isFire() == !fireTurn();
							} else if ((xPos - 2 == x) && (yPos - 2 == y) && pieceAt((xPos - 1), (yPos - 1)) != null) {
								return board[xPos - 1][yPos - 1].isFire() == !fireTurn();
							} else if (!multiCapPossible) { 
								return Math.abs(xPos - x) == 1 && Math.abs(yPos - y) == 1;
							}
						} else if (selectedPiece.isFire() && yPos < y) {
							if ((xPos + 2 == x) && (yPos + 2 == y) && pieceAt((xPos + 1), (yPos + 1)) != null) {
								return !board[xPos + 1][yPos + 1].isFire();
							} else if ((xPos - 2 == x) && (yPos + 2 == y) && pieceAt((xPos - 1), (yPos + 1)) != null) {
								return !board[xPos - 1][yPos + 1].isFire();
							} else if (!multiCapPossible) {
								return Math.abs(xPos - x) == 1 && Math.abs(yPos - y) == 1;
							}
						} else if (!selectedPiece.isFire() && yPos > y) {
							if ((xPos + 2 == x) && (yPos - 2 == y) && pieceAt((xPos + 1), (yPos - 1)) != null) {
								return board[xPos + 1][yPos - 1].isFire();
							} else if ((xPos - 2 == x) && (yPos - 2 == y) && pieceAt((xPos - 1), (yPos - 1)) != null) {
								return board[xPos - 1][yPos - 1].isFire();
							} else  if (!multiCapPossible) {
								return Math.abs(xPos - x) == 1 && Math.abs(yPos - y) == 1;
							}
						}
					} else if (selectedMoved && selectedPiece.hasCaptured() && !selectedPiece.isBomb()) {
						
						multiCapPossible = true;
						return canSelect(x, y);
					}
				}
			}
		}
		return false;
	}


	public boolean canEndTurn() {
		return (selectedPiece != null && selectedMoved);
	}

	public void endTurn() {
		selectedMoved = false;
		numOfTurns += 1;
		selectedPiece.doneCapturing();
		multiCapPossible = false;
		selectedPiece = null;
	}

	public String winner() {
		if (numFirePieces > 0 && numWaterPieces == 0) {
			return "Fire";
		} else if (numWaterPieces > 0 && numFirePieces == 0) {
			return "Water";
		} else if (numFirePieces == 0 && numWaterPieces == 0 ) {
			return "No one";
		} else {
			return null;
		}
	}	

	/** Checks whether a piece at (xi, yi) can either move to
	 *  or capture to (xj, yf) stricly from a geometry point of view.
	 */
	private boolean validMove (int xi, int yi, int xf, int yf) {
		if (Math.abs(yf - xf) % 2 == 0) {
			for (int i = 0; i < dimension; i += 1) {
				if ((xi + i == xf) && (yi + i == yf)) {
					return true;
				} else if ((xi + i == xf) && (yi - i == yf)) {
					return true;
				} else if ((xi - i == xf) && (yi + i == yf)) {
					return true;
				} else if ((xi - i == xf) && (yi - i == yf)) {
					return true;
				}
			}
		}
		return false;
	}


	/** For debugging and making sure the pieces on the current is right.
	 *  Must set Piece.java's type instance varaible to public from private.
	 */

    // private void printPiecesOnBoard() {
    // 	for (int i = 0; i < dimension; i++) {
    // 		for (int j = 0; j < dimension; j++) {
    // 			if (board[i][j] == null) {
    // 			} else {
    // 				System.out.println("At (" + i + ", " + j + ")" + " piece is a " + board[i][j].type + 
    // 								" and isFire " + board[i][j].isFire()  );
    // 			}
    // 		}
    // 	}
    // }



}