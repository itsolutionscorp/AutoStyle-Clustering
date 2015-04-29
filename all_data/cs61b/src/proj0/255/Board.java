import java.awt.*;

public class Board {
	private int turn = 0;
	private String winner = "No one yet";
	private int[][] validPlaces = new int[8][8];
	private Piece[] blueSide;
	private Piece[] redSide;

	public Board(boolean shouldBeEmpty) {
			StdDrawPlus.setCanvasSize(595, 595);
			StdDrawPlus.setXscale(0, 7);
			StdDrawPlus.setYscale(0, 7);
			makeCheckered(0);
			makeCoords();
		if (!shouldBeEmpty) {
			makeDefault();
		}
	}

	private void makeDefault() {
		blueSide = new Piece[12];
		redSide = new Piece[12];
		int coord;
		int redcount = 0;
		int bluecount = 0;

		for(int i = 0; i<=7; i++) {
			for(int j = 0; j<=7; j++) {
				coord = validPlaces[i][j];
				if (convertCoordx(coord) == 0) {
					if (j % 2 == 0) {
						redSide[redcount] = new Piece(true, this, i, j, "pawn");
						place(redSide[redcount], i, j);
						redcount++;
					}
				} else if (convertCoordx(coord) == 1) {
					if (j % 2 == 1) {
						redSide[redcount] = new Piece(true, this, i, j, "shield");
						place(redSide[redcount], i, j);
						redcount++;
					}
				} else if (convertCoordx(coord) == 2) {
					if (j % 2 == 0) {
						redSide[redcount] = new Piece(true, this, i, j, "bomb");
						place(redSide[redcount], i, j);
						redcount++;
					}
				} else if (convertCoordx(coord) == 5) {
					if (j % 2 == 1) {
					blueSide[bluecount] = new Piece(false, this, i, j, "bomb");
					place(blueSide[bluecount], i, j);
					bluecount++;
					}
				} else if (convertCoordx(coord) == 6) {
					if (j % 2 == 0) {
					blueSide[bluecount] = new Piece(false, this, i, j, "shield");
					place(blueSide[bluecount], i, j);
					bluecount++;
					}
				} else if (convertCoordx(coord) == 7) {
					if (j % 2 == 1) {
					blueSide[bluecount] = new Piece(false, this, i, j, "pawn");
					place(blueSide[bluecount], i, j);
					bluecount++;
					}
				}
			}
		}

	}

	private void makeCoords() {
		for(int i = 0; i <=7; i++) {
			for(int j = 0; j <= 7; j++) {
				validPlaces[i][j] = i*8 + j;
			}
		}
	}

	private int convertCoordx(int x) {
		return x / 8;
	}

	private int convertCoordy(int x) {
		return x % 8;
	}

	private int convertCoord(int x, int y) {
		return x * 8 + y;
	}

	private void chooseColor(double x, double y) {
		if (x % 2 == 0 && y % 2 ==0) {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else if (x % 2 == 1 && y % 2 == 1) {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
	}

	private void makeCheckered(double row) {
		double column = 0;
		if (row > 7) {
			return;
		}
		double r = .5;
		while (column <=7) {
			chooseColor(row, column);
			StdDrawPlus.filledSquare(row, column, r);
			column++;
		}
		makeCheckered(row + 1);
	}

	public Piece pieceAt(int x, int y) {
		Piece p;
		for(int i = 0; i < 11; i++) {
			p = redSide[i];
			if (p.x == y && p.y == x) {
				return p;
			}
			p = blueSide[i];
			if (p.x == y && p.y ==x) {
				return p;
			}
		}
		return null;

	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) == null) {
			return false;
		} else if (pieceAt(x, y).side() == turn) {
			return true;
		} else {
			return false;
		}
	}

	public void select(int x, int y) {

	}

	public void place(Piece p, int x, int y) {
		if (p.type.equals("pawn") && p.isFire) {
			if (p.isKing()) {
				StdDrawPlus.picture(y, x, "img/pawn-fire-crowned.png",1,1);
			}
			StdDrawPlus.picture(y, x, "img/pawn-fire.png", 1, 1);
		} else if(p.isBomb() && p.isFire) {
			if (p.isKing()) {
				StdDrawPlus.picture(y, x, "img/bomb-fire-crowned.png",1,1);
			}
			StdDrawPlus.picture(y, x, "img/bomb-fire.png",1,1);
		} else if(p.isShield() && p.isFire) {
			if (p.isKing()) {
				StdDrawPlus.picture(y, x, "img/shield-fire-crowned.png",1,1);
			}
			StdDrawPlus.picture(y, x, "img/shield-fire.png",1,1);
		} else if (p.type.equals("pawn") && p.isFire == false) {
			if (p.isKing()) {
				StdDrawPlus.picture(y, x, "img/pawn-water-crowned.png",1,1);
			}
			StdDrawPlus.picture(y, x, "img/pawn-water.png", 1, 1);
		} else if(p.isBomb() && p.isFire == false) {
			if (p.isKing()) {
				StdDrawPlus.picture(y, x, "img/bomb-water-crowned.png",1,1);
			}
			StdDrawPlus.picture(y, x, "img/bomb-water.png",1,1);
		} else if(p.isShield() && p.isFire == false) {
			if (p.isKing()) {
				StdDrawPlus.picture(y, x, "img/shield-water-crowned.png",1,1);
			}
			StdDrawPlus.picture(y, x, "img/shield-water.png",1,1);
		}
	}

	public Piece remove(int x, int y) {
		Piece p = null;
		return p;
	}

	public boolean canEndTurn() {
		return true;
	}

	public void endTurn() {
		if (turn == 1) {
			turn = 0;
		} else {
			turn = 1;
		}
	}

	public String winner() {
		return winner;
	}

	private boolean checkPieces(Piece[] p) {
		for(int i = 0; i < p.length; i++) {
			if (p[i] != null) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Board b = new Board(false);


	}


}