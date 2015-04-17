public class Board {
	private Piece[][] pieces;
	private boolean fireTurn = true;
	private int xSelected = -1;
	private int ySelected = -1;
	private Piece selected = null;
	private boolean hasMoved = false;

	public static void main(String args[]) {
        Board b = new Board(false);
        StdDrawPlus.setCanvasSize();
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
        while(true) {
            b.drawBoard();
            if (b.winner() != null) {
    			break;
			}
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                }
                StdDrawPlus.show(100);
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            	StdDrawPlus.show(100);
            }
            StdDrawPlus.show(25);
        }
        System.out.println(b.winner());
    }

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			placeInitial();
		}
	}

	private void placeInitial() {
		Piece p = null;
		for (int j = 0; j < 8; j += 1) {
			for (int i = 0; i < 8; i += 1) {
				if ((i + j) % 2 == 0) {
					switch (j) {
						case 0:
							p = new Piece(true, this, i, j, "pawn");
							place(p, i, j);
							break;
						case 1:
							p = new Piece(true, this, i, j, "shield");
							place(p, i, j);
							break;
						case 2:
							p = new Piece(true, this, i, j, "bomb");
							place(p, i, j);
							break;
						case 5:
							p = new Piece(false, this, i, j, "bomb");
							place(p, i, j);
							break;
						case 6:
							p = new Piece(false, this, i, j, "shield");
							place(p, i, j);
							break;
						case 7:
							p = new Piece(false, this, i, j, "pawn");
							place(p, i, j);
							break;
						default:
							break;	
					}
				}
			}
		}
	}

	private void drawBoard() {
		for (int j = 0; j < 8; j += 1) {
			for (int i = 0; i < 8; i += 1) {
				if (i == xSelected && j == ySelected) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				if (pieceAt(i, j) != null) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, imgPath(pieceAt(i, j)), 1, 1);
				}
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (!(x < 0 || x > 7 || y < 0 || y > 7) && p != null) {
			pieces[x][y] = p;
		}
	}

	private String imgPath(Piece p) {
		String imgPath = "img/";
		if (p.isBomb()) {
			imgPath = imgPath + "bomb";
		} else if (p.isShield()) {
			imgPath = imgPath + "shield";
		} else {
			imgPath = imgPath + "pawn";
		}
		if (p.isFire()) {
			imgPath = imgPath + "-fire";
		} else {
			imgPath = imgPath + "-water";
		}
		if (p.isKing()) {
			imgPath = imgPath + "-crowned";
		}
		imgPath = imgPath + ".png";
		return imgPath;
	}

	public Piece pieceAt(int x, int y) {
		Piece toRet = null;
		if (!(x < 0 || x > 7 || y < 0 || y > 7)) {
			if (pieces[x][y] != null) {
				toRet = pieces[x][y];
			}
		}
		return toRet;
	}

	public void select(int x, int y) {
		xSelected = x;
		ySelected = y;
		if (pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
		} else {
			selected.move(x, y);
			hasMoved = true;
		}
	}

	public boolean canSelect(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		}
		if (pieceAt(x, y) != null) {
			if (pieceAt(x, y).isFire() == fireTurn) {
				if (!hasMoved) {
					return true;
				}
			}
		} else {
			if (selected != null) {
				if (validMove(x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int x, int y) {
		Piece p = selected;
		if (pieceAt(x, y) != null) {
			return false;
		}
		if (p.isKing() || p.isFire()) {
			if (Math.abs(x - xSelected) == 1 && y - ySelected == 1) {
				if (!hasMoved) {
					return true;
				}
			}
			if (p.hasCaptured() || !hasMoved) {
				if (x - xSelected == 2 && y - ySelected == 2 && (pieceAt(x - 1, y - 1) != null && pieceAt(x - 1, y - 1).isFire() == !p.isFire())) {
					return true;
				}
				if (x - xSelected == -2 && y - ySelected == 2 && (pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire() == !p.isFire())) {
					return true;
				}
			}
		}
		if (p.isKing() || !p.isFire()) {
			if (Math.abs(x - xSelected) == 1 && y - ySelected == -1) {
				if (!hasMoved) {
					return true;
				}
			}
			if (p.hasCaptured() || !hasMoved) {
				if (x - xSelected == 2 && y - ySelected == -2 && (pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire() == !p.isFire())) {
					return true;
				}
				if (x - xSelected == -2 && y - ySelected == -2 && (pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire() == !p.isFire())) {
					return true;
				}
			}
		}
		return false;
	}

	public void endTurn() {
		xSelected = -1;
        ySelected = -1;
        selected.doneCapturing();
        selected = null;
        fireTurn = !fireTurn;
        hasMoved = false;
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public Piece remove(int x, int y) {
		Piece toRet = pieceAt(x, y);
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Tried to remove piece from out of bounds");
			return null;
		}
		if (toRet == null) {
			System.out.println("Tried to remove piece from empty square");
			return null;
		}
		pieces[x][y] = null;
		return toRet;
	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;
		String toRet = null;
		Piece p = null;
		for (int j = 0; j < 8; j += 1) {
			for (int i = 0; i < 8; i += 1) {
				p = pieceAt(i, j);
				if (p != null && p.isFire()) {
					numFire += 1;
				} else if (p != null && !p.isFire()) {
					numWater += 1;
				}
			}
		}
		if (numFire == 0 && numWater == 0) {
			toRet = "No one";
		} else if (numFire == 0) {
			toRet = "Water";
		} else if (numWater == 0) {
			toRet = "Fire";
		}
		return toRet;
	}

}