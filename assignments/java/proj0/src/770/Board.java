public class Board {

	private Piece[][] pieces;
	private int boardSize;
	private int player; // 0 for fire, 1 for water
	private Piece selectedPiece;
	private boolean moved;
	private int selectedX;
	private int selectedY;

	public static void main(String[] args) {
        Board gameBoard = new Board(false);
        StdDrawPlus.setXscale(0, gameBoard.boardSize);
        StdDrawPlus.setYscale(0, gameBoard.boardSize);

        while(true) {
        	gameBoard.drawBoard();
	        if (StdDrawPlus.mousePressed()) {
	            double x = StdDrawPlus.mouseX();
	            double y = StdDrawPlus.mouseY();
	            if (gameBoard.canSelect((int) x, (int) y)) {
	              	gameBoard.select((int) x, (int) y);
	            }
	        } if (StdDrawPlus.isSpacePressed()) {
	        	if (gameBoard.canEndTurn()) {
	        		gameBoard.endTurn();
	        	}
            }
            StdDrawPlus.show(100);
        }
	}
	
	public Board(boolean shouldBeEmpty) {
		this.boardSize = 8;
		this.player = 0;
		this.pieces = new Piece[boardSize][boardSize];
		this.selectedPiece = null;
		this.moved = false;
		if (!shouldBeEmpty) {
			this.defaultBoard();
		}
		
	}

	private void defaultBoard() {
		/** Sets default configuration for board. */
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				if (y == 0 && x % 2 == 0) {
					pieces[y][x] = new Piece(true, this, x, y, "pawn");
				} else if (y == 1 && x % 2 == 1) {
					pieces[y][x] = new Piece(true, this, x, y, "shield");
				} else if (y == 2 && x % 2 == 0) {
					pieces[y][x] = new Piece(true, this, x, y, "bomb");
				} else if (y == 5 && x % 2 == 1 ) {
					pieces[y][x] = new Piece(false, this, x, y, "bomb");
				} else if (y == 6 && x % 2 == 0) {
					pieces[y][x] = new Piece(false, this, x, y, "shield");
				} else if (y == 7 && x % 2 == 1) {
					pieces[y][x] = new Piece(false, this, x, y, "pawn");
				}
			}
		}
	}

	private void drawBoard() {
	    for (int x = 0; x < this.boardSize; x++) {
	        for (int y = 0; y < this.boardSize; y++) {
	            if ((y + x) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[y][x] != null) {
                	String type = "pawn-";
                	String team = "water";
                	String kinged = ".png";
                	if (pieces[y][x].isFire()) {
                		team = "fire";
                	} if (pieces[y][x].isBomb()) {
                		type = "bomb-";
                	} if (pieces[y][x].isShield()) {
                		type = "shield-";
                	} if (pieces[y][x].isKing()) {
                		kinged = "-crowned.png";
                	}
                	StdDrawPlus.picture(x + .5, y + .5, "img/" + type + team + kinged, 1, 1);
                }
	        }
	    }
	}



	public Piece pieceAt(int x, int y) {
		if (x >= this.boardSize || y >= this.boardSize || x < 0 || y < 0) {
			return null;
		}
		return this.pieces[y][x];
	}
	

	public boolean canSelect(int x, int y) {
		if (this.player == 0) {
			if (this.pieceAt(x, y) != null && this.pieceAt(x, y).isFire()) {
				if (this.selectedPiece == null) {
					return true;
				} if (!this.moved) {
					return true;
				}
			}
		} if (this.player == 1) {
			if (this.pieceAt(x, y) != null && !this.pieceAt(x, y).isFire()) {
				if (this.selectedPiece == null) {
					return true;
				} if (!this.moved) {
					return true;
				}
			}
		} if (this.selectedPiece != null) {
			if (!this.moved && this.validMove(selectedX, selectedY, x, y) && !this.selectedPiece.hasCaptured()) {
				return true;
			} else if (this.selectedPiece.hasCaptured() && this.validMove(selectedX, selectedY, x, y)) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieces[yi][xi] == null) {
			return false;
		}
		Piece pieceI = pieces[yi][xi];
		
		if (!moved) {
			if ( (xf == (xi + 1) || xf == (xi - 1)) && (yf == (yi + 1)) && pieces[yf][xf] == null) {
				if (pieceI.isFire() && !pieceI.isKing()) {
					return true;
				}	
			} if ( (xf == (xi + 1) || xf == (xi - 1)) && (yf == (yi - 1)) && pieces[yf][xf] == null)  {
				if (!pieceI.isFire() && !pieceI.isKing()) {
					return true;
				}
			} if ( (xf == (xi + 1) || xf == (xi - 1)) && (yf == (yi - 1) || yf == (yi + 1)) && pieces[yf][xf] == null) {
				if (pieceI.isKing()) {
					return true;
				}
			}
		}
		
		if ( (xf == (xi + 2) || xf == (xi - 2)) && (yf == (yi + 2)) && pieces[yf][xf] == null) {
<<<<<<< HEAD
			if (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi] != null) {
				if (pieceI.isFire() && !pieceI.isKing()) {
					if (!pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire()) {
						return true;
					}
				}
			}
		} if ( (xf == (xi + 2) || xf == (xi - 2)) && (yf == (yi - 2)) && pieces[yf][xf] == null) {
			if (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi] != null) {
				if (!pieceI.isFire() && !pieceI.isKing()) {
					if (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire()) {
						return true;
					}
				}
			}
		} if ( (xf == (xi + 2) || xf == (xi - 2)) && (yf == (yi + 2) || yf == (yi - 2)) && pieces[yf][xf] == null) {
			if (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi] != null) {	
				if (pieceI.isKing()) {
					if ( (!pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire() && pieceI.isFire()) || (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire() && !pieceI.isFire())) {
						return true;
					}
=======
			if (pieceI.isFire() && !pieceI.isKing()) {
				if (!pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire()) {
					return true;
				}
			}
		} if ( (xf == (xi + 2) || xf == (xi - 2)) && (yf == (yi - 2)) && pieces[yf][xf] == null) {
			if (!pieceI.isFire() && !pieceI.isKing()) {
				if (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire()) {
					return true;
				}
			}
		} if ( (xf == (xi + 2) || xf == (xi - 2)) && (yf == (yi + 2) || yf == (yi - 2)) && pieces[yf][xf] == null) {
			if (pieceI.isKing()) {
				if ( (!pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire() && pieceI.isFire()) || (pieces[(yf-yi)/2 + yi][(xf-xi)/2 + xi].isFire() && !pieceI.isFire())) {
					return true;
>>>>>>> master
				}
			}
		}
		
		return false;
	}

	public void select(int x, int y) {
		if (pieces[y][x] != null) {
			selectedPiece = pieces[y][x];
<<<<<<< HEAD
=======
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
>>>>>>> master
		} else {
			selectedPiece.move(x, y);
			moved = true;
		}
		selectedX = x;
		selectedY = y;
		
	}

	public void place(Piece p, int x, int y) {
		if (x < this.boardSize && y < this.boardSize) {
			if (this.pieceAt(x, y) != null) {
				this.remove(x, y);
			}
			this.pieces[y][x] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x >= this.boardSize || y >= this.boardSize) {
			System.out.println("Out of bounds");
			return null;
		} else if (this.pieceAt(x, y) == null) {
			System.out.println("No piece there");
			return null;
		} else {
			Piece result = this.pieces[x][y];
			this.pieces[y][x] = null;
			return result;
		}
	}

	public boolean canEndTurn() {
		if (selectedPiece != null) {
			return moved || selectedPiece.hasCaptured();	
		}
		return false;
	}

	public void endTurn() {
		player = (player - 1) * -1;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		moved = false;
		
	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int x = 0; x < this.boardSize; x++) {
			for (int y = 0; y < this.boardSize; y++) {
				if (this.pieces[y][x] != null) {
					if (this.pieces[y][x].isFire()) {
						fireCount++;
					} else {
						waterCount++;
					}
				}
			}
		}
		if (fireCount != 0 && waterCount == 0) {
			return "Fire";
		} else if (fireCount == 0 && waterCount != 0) {
			return "Water";
		} else if (fireCount == 0 && waterCount == 0) {
			return "No one";
		}
		return null;	
	}

}