public class Board {
	private Piece[][] pieces; // remember to make it private!
	private boolean hasSelected, hasMoved, fireTurn, waterTurn;
	private int xcurr, ycurr;

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0) {
						if (i == 0) {
							pieces[i][j] = new Piece(true, this, j, i, "pawn");
						} else if (i == 1) {
							pieces[i][j] = new Piece(true, this, j, i, "shield");
						} else if (i == 2) {
							pieces[i][j] = new Piece(true, this, j, i, "bomb");
						} else if (i == 5) {
							pieces[i][j] = new Piece(false, this, j, i, "bomb");
						} else if (i == 6) {
							pieces[i][j] = new Piece(false, this, j, i, "shield");
						} else if (i == 7) {
							pieces[i][j] = new Piece(false, this, j, i, "pawn");
						}
					}
				}
			}
		}
		hasSelected = false;
		hasMoved = false;
		fireTurn = true;
		waterTurn = false;
	}

	public Piece pieceAt(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return null;
		} else {
			return pieces[y][x];
		}
	}

	public boolean canSelect(int x, int y) {
		Piece curr = pieceAt(x, y);
		if (curr != null) {
			if (((curr.isFire()) && (fireTurn)) || ((!curr.isFire()) && (waterTurn))) {
				if (!hasSelected) {
					return true;
				} else if (!hasMoved) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if ((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)) {
			if ((pieceAt(xcurr, ycurr) != null) && (((pieces[ycurr][xcurr].isFire()) && (fireTurn)) || ((!pieces[ycurr][xcurr].isFire()) && (waterTurn)))) {
				if ((hasSelected) && (!hasMoved)) {
					return validMove(xcurr, ycurr, x, y);
				} else if ((hasSelected) && (pieces[ycurr][xcurr].hasCaptured())) {
					if (((x == xcurr + 2) && (y == ycurr + 2)) || ((x == xcurr - 2) && (y == ycurr + 2)) || ((x == xcurr - 2) && (y == ycurr - 2)) || ((x == xcurr + 2) && (y == ycurr - 2))) {
						return validMove(xcurr, ycurr, x, y);
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

	private boolean validMove(int xi, int yi, int xf, int yf) { // make it private
		Piece initialPiece = pieceAt(xi, yi);
		Piece finalPiece = pieceAt(xf, yf);
		if ((initialPiece != null) && (finalPiece == null)) { // the initial coordinate is not out of bound and there is a piece at the initial coordinate, as well as the final position
			if ((xf >= 0) && (xf <= 7) && (yf >= 0) && (yf <= 7)) { // the final position is not out of bound
				if (initialPiece.isKing()) { // if it is a king, then 4 directions
					if ((xf == xi + 1) && (yf == yi + 1)) {
						return true;
					} else if ((xf == xi + 1) && (yf == yi - 1)) {
						return true;
					} else if ((xf == xi - 1) && (yf == yi + 1)) {
						return true;
					} else if ((xf == xi - 1) && (yf == yi - 1)) {
						return true;
					} else if ((xf == xi + 2) && (yf == yi + 2) && (pieceAt(xi + 1, yi + 1) != null) && ((pieceAt(xi + 1, yi + 1).side() ^ initialPiece.side()) == 1)) {
						return true;
					} else if ((xf == xi + 2) && (yf == yi - 2) && (pieceAt(xi + 1, yi - 1) != null) && ((pieceAt(xi + 1, yi - 1).side() ^ initialPiece.side()) == 1)) {
						return true;
					} else if ((xf == xi - 2) && (yf == yi + 2) && (pieceAt(xi - 1, yi + 1) != null) && ((pieceAt(xi - 1, yi + 1).side() ^ initialPiece.side()) == 1)) {
						return true;
					} else if ((xf == xi - 2) && (yf == yi - 2) && (pieceAt(xi - 1, yi - 1) != null) && ((pieceAt(xi - 1, yi - 1).side() ^ initialPiece.side()) == 1)) {
						return true;
					} else {
						return false;
					}
				} else if (initialPiece.isFire()) {
					if ((xf == xi + 1) && (yf == yi + 1)) {
						return true;
					} else if ((xf == xi - 1) && (yf == yi + 1)) {
						return true;
					} else if ((xf == xi + 2) && (yf == yi + 2) && (pieceAt(xi + 1, yi + 1) != null) && ((pieceAt(xi + 1, yi + 1).side() ^ initialPiece.side()) != 0)) {
						return true;
					} else if ((xf == xi - 2) && (yf == yi + 2) && (pieceAt(xi - 1, yi + 1) != null) && ((pieceAt(xi - 1, yi + 1).side() ^ initialPiece.side()) != 0)) {
						return true;
					} else {
						return false;
					}
				} else if (!initialPiece.isFire()) {
					if ((xf == xi + 1) && (yf == yi - 1)) {
						return true;
					} else if ((xf == xi - 1) && (yf == yi - 1)) {
						return true;
					} else if ((xf == xi + 2) && (yf == yi - 2) && (pieceAt(xi + 1, yi - 1) != null) && ((pieceAt(xi + 1, yi - 1).side() ^ initialPiece.side()) != 0)) {
						return true;
					} else if ((xf == xi - 2) && (yf == yi - 2) && (pieceAt(xi - 1, yi - 1) != null) && ((pieceAt(xi - 1, yi - 1).side() ^ initialPiece.side()) != 0)) {
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
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			xcurr = x;
			ycurr = y;
			hasSelected = true;
		} else {
			pieces[ycurr][xcurr].move(x, y);
			xcurr = x;
			ycurr = y;
			hasSelected = true;
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7) || (p == null)) {
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] == p) {
					remove(j, i);
				}
			}
		}
		pieces[y][x] = p;
	}

	public Piece remove(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			System.out.println("The coordinate is out of bound");
			return null;
		} else if (pieces[y][x] == null) {
			System.out.println("There is no piece at this position");
			return null;
		} else {
			Piece getRemoved = pieces[y][x];
			pieces[y][x] = null;
			return getRemoved;
		}
	}

	public boolean canEndTurn() {
		if ((hasMoved) || ((pieces[ycurr][xcurr] != null) && (pieces[ycurr][xcurr].hasCaptured()))) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		if (fireTurn) {
			fireTurn = false;
			waterTurn = true;
		} else {
			fireTurn = true;
			waterTurn = false;
		}
		if (pieces[ycurr][xcurr] != null) {
			pieces[ycurr][xcurr].doneCapturing();
		}
		hasSelected = false;
		hasMoved = false;
	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						numFire += 1;
					} else if (!pieces[i][j].isFire()) {
						numWater += 1;
					}
				}
			}
		}

		if ((numFire == 0) && (numWater == 0)) {
			return "No one";
		} else if (numFire == 0) {
			return "Water";
		} else if (numWater == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	private void drawBoard(int n, int x, int y) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i == y) && (j == x)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(j + 0.5, i + 0.5, 0.5);
				Piece currPiece = pieceAt(j, i);
				if (currPiece != null) {
					if (currPiece.isKing()) {
						if (currPiece.isFire()) {
							if (currPiece.isBomb()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							} else if (currPiece.isShield()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
						} else {
							if (currPiece.isBomb()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/bomb-water-crowned.png", 1, 1);
							} else if (currPiece.isShield()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
					} else {
						if (currPiece.isFire()) {
							if (currPiece.isShield()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/shield-fire.png", 1, 1);
							} else if (currPiece.isBomb()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/bomb-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/pawn-fire.png", 1, 1);
							}
						} else {
							if (currPiece.isBomb()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/bomb-water.png", 1, 1);
							} else if (currPiece.isShield()) {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/shield-water.png", 1, 1);
							} else {
								StdDrawPlus.picture(j + 0.5, i + 0.5, "img/pawn-water.png", 1, 1);
							}
						}
					}	 
				}
			}
		}
	}

	public static void main(String args[]) {
		int n = 8;
		Board gameBoard = new Board(false);
		StdDrawPlus.setXscale(0, n);
        StdDrawPlus.setYscale(0, n);

        gameBoard.drawBoard(n, -1, -1);
        while (gameBoard.winner() == null) {

        	while (!gameBoard.canEndTurn()) {
        		if (StdDrawPlus.mousePressed()) {
                	double x = StdDrawPlus.mouseX();
                	double y = StdDrawPlus.mouseY();
                	if (gameBoard.canSelect((int) x, (int) y)) {
                		gameBoard.select((int) x, (int) y);
                		StdDrawPlus.show(10);
                		gameBoard.drawBoard(n, (int) x, (int) y);
                		StdDrawPlus.show(10);
                	}
            	}
        	}
        	if (StdDrawPlus.isSpacePressed()) {
            	gameBoard.endTurn();
            	StdDrawPlus.show(10);
            	gameBoard.drawBoard(n, -1, -1);
            	StdDrawPlus.show(10);
            } else {
            	if (StdDrawPlus.mousePressed()) {
                	double x = StdDrawPlus.mouseX();
                	double y = StdDrawPlus.mouseY();
                	if (gameBoard.canSelect((int) x, (int) y)) {
                		gameBoard.select((int) x, (int) y);
                		StdDrawPlus.show(10);
                		gameBoard.drawBoard(n, (int) x, (int) y);
                		StdDrawPlus.show(10);
                	}
            	}
            }
        }
        System.out.println(gameBoard.winner());
	}
}