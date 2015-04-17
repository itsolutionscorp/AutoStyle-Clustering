public class Board {

	private Piece[][] pieces;
	private boolean fireTurn = true;
	private Piece selected = null;
	private int[] coordinates = new int[2];
	private boolean hasMoved = false;

	public static void main(String args[]) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while (b.winner() == null) {
        	b.draw();
        	if(StdDrawPlus.mousePressed()) {
        		int mX = (int) StdDrawPlus.mouseX();
        		int mY = (int) StdDrawPlus.mouseY();
        		if (b.canSelect(mX, mY)) {
        			b.select(mX, mY);
        		}
        	} else if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
        		b.endTurn();
        	}
        	StdDrawPlus.show(10);
        }
        System.out.println(b.winner() + " wins!");
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i += 1) {
				for (int j = 0; j < 8; j += 1) {
					if (i % 2 == 0) {
						if (j == 0) {
							place(new Piece(true, this, i, j, "pawn"), i, j);
						} else if (j == 2) {
							place(new Piece(true, this, i, j, "bomb"), i, j);
						} else if (j == 6) {
							place(new Piece(false, this, i, j, "shield"), i, j);
						}
					} else {
						if (j == 1) {
							place(new Piece(true, this, i, j, "shield"), i, j);
						} else if (j == 5) {
							place(new Piece(false, this, i, j, "bomb"), i, j);
						} else if (j == 7) {
							place(new Piece(false, this, i, j, "pawn"), i, j);
						}
					}
				}
			}
		}
	}

	private void draw() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceAt(i, j) != null) {
                	if (pieceAt(i, j) == selected) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                	String img = "img/";
                	Piece p = pieceAt(i, j);
                	if (p.isBomb()) {
                		img += "bomb-";
                	} else if (p.isShield()) {
                		img += "shield-";
                	} else {
                		img += "pawn-";
                	}
                	if (p.isFire()) {
                		img += "fire";
                	} else {
                		img += "water";
                	}
                	if (p.isKing()) {
                		img += "-crowned";
                	}
                	img += ".png";
                	StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
	}

	public Piece pieceAt(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if ((fireTurn && pieceAt(x, y).isFire()) || (!fireTurn && !pieceAt(x, y).isFire())) {
				if (selected == null || !hasMoved) {
					return true;
				}
			}
		} else if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			if (selected != null) {
				if (validMove(coordinates[0], coordinates[1], x, y)) {
					if (!hasMoved || selected.hasCaptured()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	private boolean canMultiCapture() {
		if (selected == null) {
			return false;
		}
		int x = coordinates[0];
		int y = coordinates[1];

		if (validMove(x, y, x + 2, y + 2) || validMove(x, y, x + 2, y - 2)
			|| validMove(x, y, x - 2, y + 2) || validMove(x, y, x - 2, y - 2)) {
				return true;
		}

		return false;
	}
	*/

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null || xf > 7 || xf < 0 || yf > 7 || yf < 0) {
			return false;
		}
		if (!hasMoved && (xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)) {
			if (selected.isKing()) {
				return true;
			} else if (fireTurn && yf == yi + 1) {
				return true;
			} else if (!fireTurn && yf == yi - 1) {
				return true;
			}
		} else if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi - 2)) {
			if (!hasMoved || selected.hasCaptured()) {
				Piece p = null;
				int xx = xf - xi;
				int yy = yf - yi;
				if (selected.isKing()) {
					p = pieceAt(xi + (xx / 2), yi + (yy / 2));
				} else if (fireTurn && yy == 2) {
					p = pieceAt(xi + (xx / 2), yi + 1);
				} else if (!fireTurn && yy == -2) {
					p = pieceAt(xi + (xx / 2), yi - 1);
				}
				if (p != null && p.isFire() != fireTurn) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
		} else {
			selected.move(x, y);
			hasMoved = true;
		}
		coordinates[0] = x;
		coordinates[1] = y;
	}

	public void place(Piece p, int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0 && p != null) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (pieceAt(x, y) != null) {
			Piece temp = pieceAt(x, y);
			pieces[x][y] = null;
			return temp;
		}
		return null;
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (fireTurn) {
			fireTurn = false;
		} else {
			fireTurn = true;
		}
		hasMoved = false;
		selected.doneCapturing();
		selected = null;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						fire += 1;
					} else {
						water += 1;
					}
				}
			}
		}
		if (fire == 0 && water == 0) {
			return "No one";
		} else if (fire == 0) {
			return "water";
		} else if (water == 0) {
			return "fire";
		} else {
			return null;
		}
	}
}