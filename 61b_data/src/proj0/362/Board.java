public class Board{
	private Piece[][] pieceArray = new Piece[8][8];
	private String image;
	private boolean moved, selected, captured;
	private int N = 8;
	private int sX, sY, turn;
	private int water;
	private int fire;


	public static void main(String[] args) {
		Board b = new Board(false);
        
        int colorX = 10;
        int colorY = 10;
		while (true) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
					colorX = x;
					colorY = y;
				}
			}
			 if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(colorX + 0.5, colorY + 0.5, 0.5);
		StdDrawPlus.show(15);

	}
}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++) {
				if ((row + col) % 2 != 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(col + .5, row + .5, .5);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                	StdDrawPlus.filledSquare(col + .5, row + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} if (pieceArray[col][row] != null) {
						image = drawPiece(pieceArray[col][row]);
                    	StdDrawPlus.picture(col + .5, row + .5, image, 1, 1);
                }
            }
		}
	}

	private String drawPiece(Piece p) {
		if (p.isFire()) {
			if (p.isBomb() && p.isKing()) {
				return "img/bomb-fire-crowned.png";
			} else if (p.isBomb()) {
				return "img/bomb-fire.png";
			} else if (p.isShield() && p.isKing()) {
				return "img/shield-fire-crowned.png";
			} else if (p.isShield()) {
				return "img/shield-fire.png";
			} else if (p.isKing()) {
				return "img/pawn-fire-crowned.png";
			} else return "img/pawn-fire.png";
		} else 
			if (p.isBomb() && p.isKing()) {
				return "img/bomb-water-crowned.png";
			} else if (p.isBomb()) {
				return "img/bomb-water.png";
			} else if (p.isShield() && p.isKing()) {
				return "img/shield-water-crowned.png";
			} else if (p.isShield()) {
				return "img/shield-water.png";
			} else if (p.isKing()) {
				return "img/pawn-water-crowned.png";
			} else return "img/pawn-water.png";
	}

	private void defaultPieces() {
		for (int col = 0; col < N; col += 1) {
			for (int row = 0; row < N; row += 1) {
				if ((row + col) % 2 == 0) {
					if (row == 0) {
						pieceArray[col][row] = new Piece(true, this, col, row, "pawn");
					} else if (row == 1) {
						pieceArray[col][row] = new Piece(true, this, col, row, "shield");
					} else if (row == 2) {
						pieceArray[col][row] = new Piece(true, this, col, row, "bomb");
					} else if (row == 5) {
						pieceArray[col][row] = new Piece(false, this, col, row, "bomb");
					} else if (row == 6) {
						pieceArray[col][row] = new Piece(false, this, col, row, "shield");
					} else if (row == 7) {
						pieceArray[col][row] = new Piece(false, this, col, row, "pawn");
					}
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			defaultPieces();
		}
		selected = false;
		moved = false;
		captured = false;
		turn = 0;
	}

	public Piece pieceAt(int x, int y) {
		if (!inBounds(x, y)) {
			return null;
		}
		return pieceArray[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null && (p.side() == turn)) {
			return (selected == false) || 
					((selected == true) && (moved == false));
		} else if (p == null) {
			if ((selected == true) && (!moved) && ValidMove(sX, sY, x, y)) {
				return true;
			} else if ((selected == true) && (pieceAt(sX, sY) != null) && (pieceAt(sX, sY).hasCaptured()) &&
					ValidMove(sX, sY, x, y) && (Math.abs(sX - x) == 2)) {
				return true;				
			} else {
				return false;
			}
		}
		return false;
	}


	private boolean ValidMove(int xi, int yi, int xf, int yf) {
		Piece pieceInitial = pieceAt(xi, yi);
		if (pieceInitial==null) {
			return false;
		}
		int deltaX = xf - xi;
		int deltaY = yf - yi;
		if ((Math.abs(deltaX) == 1) && (Math.abs(deltaY) == 1) && (!captured)) {
			if (pieceInitial.isKing()) {
				return true;
			} else if (pieceInitial.isFire() && (deltaY == 1)) {
				return true;
			} else if (!pieceInitial.isFire() && (deltaY == -1)) {
				return true;
			}
		} else if ((Math.abs(deltaX) == 2) && (Math.abs(deltaY) == 2)) {
			int midX = (xf + xi) / 2;
			int midY = (yf + yi) / 2;
			Piece pieceMid = pieceAt(midX, midY);
			if ((pieceMid != null) && (pieceMid.side() != pieceInitial.side())) {
				if (pieceInitial.isKing()) {
					return true;
				} else if (pieceInitial.isFire() && (deltaY == 2)) {
					return true;
				} else if (!pieceInitial.isFire() && (deltaY == -2)) {
					return true;
				}
			}
		} return false;
	}

	public void select(int x, int y) {
		if ((pieceAt(x, y) != null)) {
			sX = x;
			sY = y;
			selected = true;
		} else {
			pieceAt(sX, sY).move(x, y);
			sX = x;
			sY = y;
			moved = true;
		}
	}


	private boolean inBounds(int x, int y) {
		if (x >= N || y >= N) {
			return false;
		}
		return true;
	}

	public void place(Piece p, int x, int y) {
		if (inBounds(x, y) && p != null) {
			pieceArray[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (!inBounds(x, y)) {
			System.out.println("Coordinates out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece to remove.");
			return null;
		} else {
			Piece removed = pieceArray[x][y];
			pieceArray[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn() {
		if ((moved == true) || (captured == true)) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		selected = false;
		moved = false;
		captured = false;
		if (turn == 0) {
			turn = 1;
		}
		else {
			turn = 0;
		}
	}

	public String winner() {
		fire = 0;
		water = 0;
		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++) {
				if (pieceArray[col][row] != null) {
					if (pieceArray[col][row].isFire()) {
						fire += 1;
					} else {
						water += 1;
					}
				}
			}
		}
		if ((fire > 0) && (water == 0)) {
			return "Fire";
		} else if ((water > 0) && (fire == 0)) {
			return "Water";
		} else if ((water == 0) && (fire == 0)) {
			return "No one";
		}
		return null;
	}
}