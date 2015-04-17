public class Board {

	private static Piece[][] pieceArray;
	private static boolean fireturn;
	private static Piece selectedPiece;
	private static int selectedx = 8;
	private static int selectedy = 8;
	private static boolean hasMoved;
	private static boolean canMultipleCapture;


	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0	, N);
		StdDrawPlus.setYscale(0, N);
		pieceArray = new Piece[N][N];

		Board board = new Board(false);
		fireturn = true;
		selectedPiece = null;
		hasMoved = false;
		canMultipleCapture = false;

		while(true) {
			board.drawBoard(N);

			if(StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (board.canSelect((int) x, (int)y)) {
					board.select((int) x, (int) y);
				}
			}

			if(StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
				}
			}

			StdDrawPlus.show(10);
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieceArray = new Piece[8][8];
		if (!shouldBeEmpty) {
			setPieces();
		}
	}

	private boolean inBounds(int x, int y) {
		return (!(x < 0 || x > 7 || y < 0 || y > 7));
	}

	private void setPieces() {
		pieceArray[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieceArray[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieceArray[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieceArray[6][0] = new Piece(true, this, 6, 0, "pawn");
		pieceArray[1][1] = new Piece(true, this, 1, 1, "shield");
		pieceArray[3][1] = new Piece(true, this, 3, 1, "shield");
		pieceArray[5][1] = new Piece(true, this, 5, 1, "shield");
		pieceArray[7][1] = new Piece(true, this, 7, 1, "shield");
		pieceArray[0][2] = new Piece(true, this, 0, 2, "bomb");
		pieceArray[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieceArray[4][2] = new Piece(true, this, 4, 2, "bomb");
		pieceArray[6][2] = new Piece(true, this, 6, 2, "bomb");

		pieceArray[1][5] = new Piece(false, this, 1, 5, "bomb");
		pieceArray[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieceArray[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieceArray[7][5] = new Piece(false, this, 7, 5, "bomb");
		pieceArray[0][6] = new Piece(false, this, 0, 6, "shield");
		pieceArray[2][6] = new Piece(false, this, 2, 6, "shield");
		pieceArray[4][6] = new Piece(false, this, 4, 6, "shield");
		pieceArray[6][6] = new Piece(false, this, 6, 6, "shield");
		pieceArray[1][7] = new Piece(false, this, 1, 7, "pawn");
		pieceArray[3][7] = new Piece(false, this, 3, 7, "pawn");
		pieceArray[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieceArray[7][7] = new Piece(false, this, 7, 7, "pawn");
	}

	private static void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == selectedx && j ==selectedy) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(selectedx + .5, selectedy + .5, .5);
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);

				if (pieceArray[i][j] != null) {
					Piece current = pieceArray[i][j];
					if(current.isFire()) {
						if (current.isShield() && current.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
						} else if (current.isBomb() && current.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
						} else if (current.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
						} else if (current.isShield()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						} else if (current.isBomb()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					} else {
						if (current.isShield() && current.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
						} else if (current.isBomb() && current.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
						} else if (current.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
						} else if (current.isShield()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						} else if (current.isBomb()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if(!inBounds(x, y)) {
			return null;
		}
		return pieceArray[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece pieceToSelect = pieceAt(x, y);
		//select a piece
		if(!hasMoved && pieceToSelect != null) {
			if(fireturn && pieceToSelect.isFire()) {
				return true;
			}
			if(!fireturn && !pieceToSelect.isFire()) {
				return true;
			}
		//select a destination
		} else if (pieceToSelect == null) {
			if (selectedPiece != null && validMove(selectedx, selectedy, x, y)) {
				if (hasMoved && !canMultipleCapture) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece piecei = pieceAt(xi, yi);
		Piece piecef = pieceAt(xf, yf);
		//square will always be empty, check if can capture or can move
		//so check if king, fire, water
		//sorry for the redundant code but this is due in 3 hours

		//check for capture
		if ((Math.abs(xi - xf) == 2) && (Math.abs(yi-yf) == 2)) {
			if (piecei.isKing()) {
				if ((xi == xf - 2 || xi == xf + 2) && (yi == yf - 2 || yi == yf + 2)) {
					if(pieceAt(xf, yf) == null) {
						return true;
					} else {
						return false;
					}
				}
			} else if (piecei.isFire()) {
				if ((xi == xf - 2 || xi == xf + 2) && (yi == yf - 2)) {
					if(pieceAt(xf, yf) == null) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				if ((xi == xf - 2 || xi == xf + 2) && (yi == yf + 2)) {
					if(pieceAt(xf, yf) == null) {
						return true;
					} else {
						return false;
					}
				}
			}
		//check for normal move
		} else if(piecei.isKing()) {
			if ((xi == xf - 1 || xi == xf + 1) && (yi == yf - 1 || yi == yf + 1)) {
				return true;
			}
		} else if (piecei.isFire()) {
			if ((xi == xf - 1 || xi == xf + 1) && (yi == yf - 1)) {
				return true;
			}
		} else {
			if ((xi == xf -1 || xi == xf + 1) && (yi == yf + 1)) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		//if piece is selected and target is not on same team then move it
		//else set selectedPiece, selectedx, selectedy
		if((selectedPiece != null) && (pieceAt(x, y) == null)) {
			if(Math.abs(x - selectedx) == 2) {
				canMultipleCapture = true;
			}
			selectedPiece.move(x, y);

			//allows us to multiple capture
			//also, fixes selection to update to new position before turn passes
			selectedPiece = pieceAt(x, y);
			selectedx = x;
			selectedy = y;

			hasMoved = true;
		} else {
			selectedPiece = pieceAt(x, y);
			selectedx = x;
			selectedy = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (inBounds(x, y) && p != null) {
			pieceArray[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (!inBounds(x, y)) {
			System.out.println("Tried to remove out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("Tried to remove null piece.");
			return null;
		}
		Piece temp = pieceArray[x][y];
		pieceArray[x][y] = null;
		return temp;
	}

	public boolean canEndTurn() {
		if(hasMoved == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		fireturn = !fireturn;
		selectedPiece = null;
		selectedx = 8;
		selectedy = 8;
		hasMoved = false;
		canMultipleCapture = false;
		selectedPiece.doneCapturing();
	}

	public String winner() {
		int firepieces = 0;
		int waterpieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = pieceAt(i, j);
				if(p != null) {
					if(p.isFire()) {
						firepieces += 1;
					} else {
						waterpieces += 1;
					}
				}
			}
		}
		if (firepieces > waterpieces) {
			return "Fire";
		} else if (waterpieces > firepieces) {
			return "Water";
		} else if (waterpieces == firepieces && waterpieces == 0) {
			return "No one";
		}
		return null;
	}
}