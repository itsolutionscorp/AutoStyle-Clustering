public class Board {

	private int sideLength = 8;
	private Piece[][] pieces = new Piece[sideLength][sideLength];
	private boolean fireTurn = true;
	private boolean shouldBeEmpty;
	private boolean squareSelected = false;
	private Piece selectedPiece;
	private int ySelected;
	private int xSelected;
	private boolean gameState = true;
	private boolean hasMoved = false;
	private int tempX;
	private int tempY;
	private boolean canEndTurn;
	private int xHighlighted = -1;
	private int yHighlighted = -1;
	
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		if (!this.shouldBeEmpty) {
			for (int i = 0; i < sideLength; i += 2) {
				this.pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				this.pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
				this.pieces[i][2] = new Piece(true, this, i, 2, "bomb");
				this.pieces[i + 1][5] = new Piece(false, this, 1 + 1, 5, "bomb");
				this.pieces[i][6] = new Piece(false, this, i, 6, "shield");
				this.pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
			}
		}
	}

	public static void main (String[] args) {
		Board newBoard = new Board(false);
		if (!newBoard.shouldBeEmpty) {
			newBoard.drawPieces();
		}

		newBoard.drawBoard();
		while(true) {
			if (!newBoard.gameState) {
				System.out.println(newBoard.winner() + "wins!");
				return;
			}
			if (StdDrawPlus.mousePressed()) {
				newBoard.tempX= (int) StdDrawPlus.mouseX();
				newBoard.tempY = (int) StdDrawPlus.mouseY();
				if (newBoard.canSelect(newBoard.tempX, newBoard.tempY)) {
					newBoard.select(newBoard.tempX, newBoard.tempY);
				}
				newBoard.drawBoard();
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (newBoard.canEndTurn()) {
					newBoard.hasMoved = false;
					newBoard.endTurn();
				}
				newBoard.drawBoard();
			}
			StdDrawPlus.show(100);
		}
	}

	private void drawGame() {
		StdDrawPlus.setXscale(0, sideLength);
        StdDrawPlus.setYscale(0, sideLength);

		for (int i = 0; i < sideLength; i++) {
			for(int j = 0; j < sideLength; j++) {
				if(pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						if (pieces[i][j].isBomb()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png");
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
						} else if (pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
						} else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					} else {
						if (pieces[i][j].isBomb()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png");
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
						} else if (pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
						} else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	private void drawPieces() {
		StdDrawPlus.setXscale(0, sideLength);
        StdDrawPlus.setYscale(0, sideLength);
		for (int i = 0; i < sideLength; i += 2) {
			this.pieces[i][0] = new Piece(true, this, i, 0, "pawn");
			this.pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
			this.pieces[i][2] = new Piece(true, this, i, 2, "bomb");
			this.pieces[i + 1][5] = new Piece(false, this, 1 + 1, 5, "bomb");
			this.pieces[i][6] = new Piece(false, this, i, 6, "shield");
			this.pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
		}
	}
	
	private void drawBoard() {
		StdDrawPlus.setXscale(0, sideLength);
        StdDrawPlus.setYscale(0, sideLength);
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if ((i == xHighlighted) && (j == yHighlighted)) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		drawGame();
	}

	public Piece pieceAt(int x, int y) {
		if ((x < 0 || y < 0) || (x > 7 || y > 7)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}
	
	public boolean canSelect(int x, int y) {
		if ((x < 0 || y < 0) || (x > sideLength || y > sideLength)) {
			return false;
		}
		if (pieceAt(x, y) != null) {
			if (pieceAt(x, y).isFire() == fireTurn) {
				if (!squareSelected || (squareSelected && !hasMoved)) {
					return true;
				}
			}
		} else {
			if (((squareSelected && !hasMoved) && (validMove(xSelected, ySelected, x, y))) || ((squareSelected && selectedPiece.hasCaptured()) && (validMove(xSelected, ySelected, x, y)))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((Math.abs(xf - xi) == Math.abs(yf - yi) && Math.abs(xf - xi) < 3)) {
			if (Math.abs(xf - xi) == 2) {
				if (pieceAt( ((xf + xi) / 2), ((yf - yi) / 2) ) != null) {
 					return validCapture(xi, yi, (xf + xi) / 2, (yf + yi) / 2);
 				}
			} else if (Math.abs(xf - xi) == 1 && pieceAt(xf, yi) == null) {
				if (pieceAt(xi, yi).isKing()) {
					return true;
				} else if (pieceAt(xi, yi).isFire() && yi < yf) {
					return true;
				} else if (!pieceAt(xi, yi).isFire() && yi > yf) {
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
		return false;
	}

	private boolean validCapture(int x, int y, int xCaptured, int yCaptured) {
		if (pieceAt(x, y).isFire() != pieceAt(xCaptured, yCaptured).isFire()) {
			if (pieceAt(x, y).isKing()) {
				return true;
			} else if (pieceAt(x, y).isFire()) {
				if (yCaptured > y) {
					return true;
				}
			} else {
				if (y > yCaptured) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (!squareSelected) { //if no square is selected
			selectedPiece = pieces[x][y];
			xSelected = x;
			xHighlighted = x;
			ySelected = y;
			yHighlighted = y;
			squareSelected = true;
		} else if (pieceAt(x, y) != null) { //if a square is already selected, selecting a different piece
			selectedPiece = pieces[x][y];
			xSelected = x;
			xHighlighted = x;
			ySelected = y;
			yHighlighted = y;
		} else {
			pieceAt(xSelected, ySelected).move(x, y);
			xSelected = x;
			xHighlighted = x;
			ySelected = y;
			yHighlighted = y;
			hasMoved = true;
			canEndTurn = true;
			squareSelected = false;
		}

	}
	
	public void place(Piece p, int x, int y) {
		if (p != null) {
			try {
				this.pieces[x][y] = p;
			} catch (IndexOutOfBoundsException e) {}
		}
	}

	public Piece remove(int x, int y) {
		Piece removedPiece = pieceAt(x, y);
		pieces[x][y] = null;
		return removedPiece;
	}
	
	public boolean canEndTurn() {
		return canEndTurn;
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		squareSelected = false;
		canEndTurn = false;
		hasMoved = false;
		xHighlighted = -1;
		pieceAt(xSelected, ySelected).doneCapturing();
		yHighlighted = -1;
	}

	public String winner() {
		int water = 0;
		int fire = 0;
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if (pieceAt(i, j).isFire()) {
					fire += 1;
				} else {
					water += 1;
				}
			}
		}
		if (water == 0 && fire != 0) {
			return "Water";
		} else if (fire == 0 && water != 0) {
			return "Fire";
		} else {
			return null;
		}
	}
}