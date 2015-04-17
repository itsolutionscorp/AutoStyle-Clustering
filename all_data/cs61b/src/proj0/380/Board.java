public class Board {
	private boolean shouldBeEmpty;
	private Piece[][] pieces = new Piece[8][8];
	private boolean moved;
	private int selectedX;
	private int selectedY;
	private boolean selectedSomething = false;
	private Piece selectedPiece = null;
	private int isFireSide = 0;


	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		b.makeEmptyBoard();
		while (true) {
			b.drawPieces();
			if (StdDrawPlus.mousePressed()) {
				b.makeEmptyBoard();
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)) {
					b.select((int) x, (int) y);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					// StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					StdDrawPlus.filledSquare(((int) x) + .5, ((int) y) + .5, .5);
					// StdDrawPlus.picture(x + .5, y + .5, pieceString(pieces[x][y]), 1, 1);
				}
				b.drawPieces();
			}
			if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) {
				b.makeEmptyBoard();
				b.endTurn();
				b.drawPieces();
				}

			StdDrawPlus.show(100);
		}


	}
	
	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i += 2) {
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
				pieces[i+1][1] = new Piece(true, this, i, 1, "shield");
				pieces[i+1][5] = new Piece(false, this, i, 5, "bomb");
				pieces[i+1][7] = new Piece(false, this, i, 7, "pawn");
			}
			this.shouldBeEmpty = true;
		}
	}

	private void makeEmptyBoard() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
	}

	private void drawPieces() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		StdDrawPlus.picture(i + .5, j + .5, pieceString(pieces[i][j]), 1, 1);
            	}
        	}
		}
	}

	private String pieceString(Piece p) {
		String str = "img/";
		if (p.isBomb()) {
			str = str + "bomb";
		} else if (p.isShield()) {
			str = str + "shield";
		} else str = str + "pawn";
		if (p.isFire()) {
			str = str + "-fire";
		} else str = str + "-water";
		if (p.isKing()) {
			str = str + "-crowned";
		}
		str = str + ".png";
		return str;
	}

	private boolean outOfBounds(int x, int y) {
		if ((x >= 8) || (x < 0) || (y >= 8) || (y < 0)) {
			return true;
		} return false;
	}

	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x,y)) {
			return null;
		}
		return pieces[x][y];
	}
	
	public boolean canSelect(int x, int y) {
		if (outOfBounds(x, y)) {
			return false;
		}

		if (pieceAt(x, y) != null) {
			if (isFireSide != pieceAt(x, y).side()) {
				return false;
			}

			if (!selectedSomething) {
				return true;
			}
			if (selectedSomething && !moved) {
				return true;
			}
		}

		if (pieceAt(x, y) == null) {
			if (!moved && validMove(selectedX, selectedY, x, y)) {
				if ((selectedPiece != null) && selectedPiece.isKing()) {
					return true;
				}

				if ((selectedPiece != null) && !selectedPiece.isKing()) {
					if (selectedPiece.isFire() && y > selectedY) {
						return true;
					}

					if (!selectedPiece.isFire() && y < selectedY) {
						return true;
					}
				}
			}
			if (moved) {
				if (validMove(selectedX, selectedY, x, y) && diagonalLength(x - selectedX, y - selectedY) == 2 && selectedPiece.hasCaptured()) {
					return true;
				}
			}	

			
		}
		return false;
		// if (!selectedSomething) {
		// 	if (pieceAt(x,y) != null && pieceAt(x,y).side() == isFireSide) {
		// 		return true;
		// 	} return false;
		// } else {
		// 	if (selectedPiece != null && !selectedPiece.isKing() && (selectedX > x || selectedY > y)) {
		// 		return false;
		// 	}
		// 	if (!moved) {
		// 		if (pieceAt(x,y) != null && pieceAt(x,y).side() == isFireSide) {
		// 			return true;
		// 		} else if (validMove(selectedX, selectedY, x, y)) {
		// 			return true;
		// 		} 
		// 	}
		// 	if (moved && validMove(selectedX, selectedY, x, y) && diagonalLength(x-selectedX, y-selectedY) == 2) {
		// 		return true;
		// 	}
		// }
		// return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		int diffx = xf - xi;
		int diffy = yf - yi;
		if (diagonalLength(diffx, diffy) == 2) {
			if (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null && isFireSide != pieceAt((xi + xf) / 2, (yi + yf) / 2).side()) {
				return true;
			}
		}
		if (diagonalLength(diffx, diffy) == 1) {
			if (pieceAt(xf, yf) == null) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonal(int x, int y) {
		if ((Math.abs(x) == Math.abs(y))) {
			return true;
		} return false;
	}
	
	private int diagonalLength(int x, int y) {
		if (checkDiagonal(x,y)) {
			return Math.abs(x);
		} return -1;
	}



	public void select(int x, int y) {
		if (!selectedSomething) {
			if (pieceAt(x, y) != null) {
				selectedPiece = pieceAt(x, y);
				selectedX = x;
				selectedY = y;
				selectedSomething = true;
			}
		} 
		if (selectedSomething) {
			if (pieceAt(x, y) != null) {
				selectedPiece = pieceAt(x, y);
				selectedX = x;
				selectedY = y;
				selectedSomething = true;
			}
			if (pieceAt(x, y) == null) {
				selectedPiece.move(x, y);
				place(selectedPiece, x, y);
				if (selectedPiece.hasCaptured()) {
					int midX = (selectedX + x) / 2;
					int midY = (selectedY + y) / 2;
					remove(midX, midY);
					if (selectedPiece.isBomb()) {
						bombAttack(x, y);
						selectedPiece.doneCapturing();
					} 
				}
				selectedX = x;
				selectedY = y;
				moved = true;
			}

		}
	}





		// if (pieceAt(x, y) != null) {
		// 	selectedPiece = pieceAt(x, y);
		// 	selectedX = x;
		// 	selectedY = y;
		// 	selectedSomething = true;
		// } else {
		// 	pieceAt(selectedX, selectedY).move(x, y);
		// 	place(selectedPiece, x, y);
		// 	selectedX = x;
		// 	selectedY = y;
		// 	moved = true;
		// }
	
	private void bombAttack(int x, int y) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (pieceAt(x + i, y + j) != null && !pieceAt(x + i, y + j).isShield()) {
					remove(x + i, y + j);
				}
			}
		}
	}



	public void place(Piece p, int x, int y) {
		if ((!outOfBounds(x,y) && (p != null))) {
			// if (pieceAt(x, y) != null) {
			// 	remove(x,y);
			// }
			if (selectedSomething) {
				remove(selectedX, selectedY);
			}
			pieces[x][y] = p;

		}
	}
	
	public Piece remove(int x, int y) {
		if (outOfBounds(x,y)) {
			System.out.println("Out of bounds.");
			return null;
		}
		Piece savedPiece = pieces[x][y];
		this.pieces[x][y] = null;
		if (savedPiece == null) {
			System.out.println("No piece to remove.");
		}
		return savedPiece;
	}
	
	public boolean canEndTurn() {
		return moved;
	}
	
	public void endTurn() {
		isFireSide = 1 - isFireSide;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedX = -1;
		selectedY = -1;
		selectedSomething = false;
		moved = false;
	}
	
	public String winner() {
		int waterWin = 0;
		int fireWin = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire()) {
            			fireWin += 1;
            		} else waterWin += 1;
            	}
        	}
		}
		if (fireWin > 0 && waterWin > 0) {
			return null;
		}
		if (fireWin == 0 && waterWin == 0) {
			return "No one";
		}
		if (fireWin > waterWin) {
			return "Fire";
		}
		if (waterWin > fireWin) {
			return "Water";
		}
		return null;

	}
	
}