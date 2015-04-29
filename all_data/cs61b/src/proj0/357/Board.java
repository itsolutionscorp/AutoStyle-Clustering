public class Board {

	// Test

	private static int size = 8;
	private Piece[][] boardPieces = new Piece[size][size];
	private boolean turnFire;
	private boolean moved = false;
	private Piece picked;
	private int pickedX;
	private int pickedY;

	public static void main(String args[]) {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);
		Board b = new Board(false);
		b.drawPieces();
		while(true) {
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)) b.select((int) x, (int) y);
				b.drawPieces();
				// if (b.picked == null) {b.redraw((int) x, (int) y, false);}
				// 	else {
				// 		b.redraw((int) x, (int) y, true);
				// 		b.redraw(b.pickedX, b.pickedY, true);
				// 	}
			}
			if (StdDrawPlus.isSpacePressed()) {
				b.endTurn();
			}
			if (b.winner() != null) {
				System.out.println(b.winner());
				return;
			}
		}
	}

	private void populateBoardSet(Board b) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((i + j) % 2 == 0) {
					if (j == 7) {
						b.boardPieces[i][j] = new Piece(false, b, i, j, "pawn");
					}
					else if (j == 6) {
						b.boardPieces[i][j] = new Piece(false, b, i, j, "shield");
					}
					else if (j == 5) {
						b.boardPieces[i][j] = new Piece(false, b, i, j, "bomb");
					}
					else if (j == 2) {
						b.boardPieces[i][j] = new Piece(true, b, i, j, "bomb");
					}
					else if (j == 1) {
						b.boardPieces[i][j] = new Piece(true, b, i, j, "shield");
					}
					else if (j == 0) {
						b.boardPieces[i][j] = new Piece(true, b, i, j, "pawn");
					}
					else {
						b.boardPieces[i][j] = null;
					}
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		picked = null;
		pickedX = 0;
		pickedY = 0;
		turnFire = true;
		if (shouldBeEmpty == false) populateBoardSet(this);
	}

	private void drawPieces() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				Piece p = boardPieces[i][j];
				if (p != null) {
					String picture = "img/";
					if (p.isBomb()) {
						picture = picture + "bomb";
					} else if (p.isShield()) {
						picture = picture + "shield";
					} else {
						picture = picture + "pawn";
					}
					if (p.isFire()) {
						picture = picture + "-fire";
					} else {
						picture = picture + "-water";
					}
					if (p.isKing()) {
						picture = picture + "-crowned";
					}
					StdDrawPlus.picture(i + .5, j + .5, picture + ".png", 1, 1);
				// } else if (p == null) {
				// 	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
				}
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	private void redraw(int x, int y, boolean select) {
		if ((x + y) % 2 ==0) {
			Piece p = boardPieces[x][y];
			if (select == false) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			} else StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			if (p != null) {
				String picture = "img/";
				if (p.isBomb()) {
					picture = picture + "bomb";
				} else if (p.isShield()) {
					picture = picture + "shield";
				} else {
					picture = picture + "pawn";
				}
				if (p.isFire()) {
					picture = picture + "-fire";
				} else {
					picture = picture + "-water";
				}
				if (p.isKing()) {
					picture = picture + "-crowned";
				}
				StdDrawPlus.picture(x + .5, y + .5, picture + ".png", 1, 1);
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x >= 0 && x < size && y >= 0 && y < size) {
			return boardPieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == turnFire) {
			if (picked == null || moved == false) {
				return true;
			} else return false;
		} else if (pieceAt(x, y) == null) {
			if ((picked != null && validMove(pickedX, pickedY, x, y)) || (picked != null && picked.hasCaptured() && validMove(pickedX, pickedY, x, y))) {
				return true;
			} return false;
		} return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (picked.isKing()) {
			if ((xf - xi == 2 || xi - xf == 2) && (yf - yi == 2 || yi - yf == 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire() != turnFire)) {
				if (moved && picked.hasCaptured() == false) {
					return false;
				} else return true;
			}
			if ((xf - xi == 1 || xi - xf == 1) && (yf - yi == 1 || yi - yf == 1)) {
				if (moved) {
					return false;
				} else return true;
			}
		} else if (picked.isFire()) {
			if ((xf - xi == 2 || xi - xf == 2) && (yf - yi == 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire() != turnFire)) {
				if (moved && picked.hasCaptured() == false) {
					return false;
				} else return true;
			}
			if ((xf - xi == 1 || xi - xf == 1) && (yf - yi == 1)) {
				if (moved) {
					return false;
				} else return true;
			}
		} else {
			if ((xf - xi == 2 || xi - xf == 2) && (yi - yf == 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire() != turnFire)) {
				if (moved && picked.hasCaptured() == false) {
					return false;
				} else return true;
			}
			if ((xf - xi == 1 || xi - xf == 1) && (yi - yf == 1)) {
				if (moved) {
					return false;
				} else return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (picked == null) {
			picked = pieceAt(x, y);
			pickedX = x;
			pickedY = y;
		} else {
			if (pieceAt(x, y) == null) {
				picked.move(x, y);
				pickedX = x;
				pickedY = y;
				moved = true;
			} else {
				picked = pieceAt(x, y);
				pickedX = x;
				pickedY = y;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (x >= 0 && x < size && y >= 0 && y < size && p != null) {
			for (int i = 0; i < size; i ++) {
				for (int j = 0; j < size; j++) {
					if (boardPieces[i][j] == p) {
						boardPieces[i][j] = null;
					}
				}
			}
			boardPieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Out of bounds!");
			return null;
		}
		Piece p = pieceAt(x, y);
		if (p == null) {
			System.out.println("No piece there!");
			return null;
		}
		boardPieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		if (picked == null) {
			return false;
		}
		return (moved || picked.hasCaptured());
	}

	public void endTurn() {
		if (canEndTurn()) {
			if (turnFire) {turnFire = false;}
			else {turnFire = true;}
			moved = false;
			picked.doneCapturing();
			picked = null;
		}
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (boardPieces[i][j] != null && boardPieces[i][j].isFire()) {
					fire++;
				}
				if (boardPieces[i][j] != null && boardPieces[i][j].isFire() == false) {
					water++;
				}
			}
		}
		if (fire > 0 && water == 0) {
			return "Fire";
		} else if (water > 0 && fire == 0) {
			return "Water";
		} else if (fire == 0 && water == 0) {
			return "No one";
		}
		return null;
	}
}