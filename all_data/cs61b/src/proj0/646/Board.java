public class Board {

	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private boolean isFireTurn = true;
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private int xSelected = -1;
	private int ySelected = -1;

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		this.initializePieces();
	}

	private void initializePieces() {
		pieces = new Piece[8][8];
		if (shouldBeEmpty) {
			return;
		}
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i = i + 2) {
				if (j == 0) {
					pieces[i][j] = new Piece(true,this,i,j,"pawn");
				} else if (j == 2) {
					pieces[i][j] = new Piece(true,this,i,j,"bomb");
				} else if (j == 6) {
					pieces[i][j] = new Piece(false,this,i,j,"shield");
				}
			}
			for (int i = 1; i < 8; i = i +2) {
				if (j == 1) {
					pieces[i][j] = new Piece(true,this,i,j,"shield");
				} else if (j == 5) {
					pieces[i][j] = new Piece(false,this,i,j,"bomb");
				} else if (j == 7) {
					pieces[i][j] = new Piece(false,this,i,j,"pawn");
				}
			}
		}
	}

// bottom left = (0,0). top right = (7,7)
	private void drawBoard() {
		int i;
		int j;
		String type;
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				if ((i + j)%2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if ((i == xSelected)&&(j == ySelected)) {
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				if (pieces[i][j] != null) {
					if (pieces[i][j].isBomb()) {
						type = "bomb";
					} else if (pieces[i][j].isShield()) {
						type = "shield";
					} else {
						type = "pawn";
					}
					if (pieces[i][j].isFire()) {
						if (pieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,String.format("img/%s-fire-crowned.png",type), 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,String.format("img/%s-fire.png",type), 1, 1);
						}
					} else {
						if (pieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5,String.format("img/%s-water-crowned.png",type), 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5,String.format("img/%s-water.png",type), 1, 1);
						}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x>7||x<0||y>7||y<0) {
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if (p == null) {
			System.out.printf("ERROR: Attempted to place a null piece at (%d, %d).%n",x,y);
			return;
		}
		if (x>7||x<0||y>7||y<0) {
			System.out.printf("ERROR: Attempted to place piece out of bounds at (%d, %d).%n",x,y);
			return;
		}
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (pieces[i][j] == p) pieces[i][j] = null;
			}
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x>7||x<0||y>7||y<0) {
			System.out.printf("ERROR: Remove at (%d, %d) out of bounds. Null piece returned.%n",x,y);
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.printf("ERROR: Attempted to remove null piece at (%d, %d). Null piece returned.%n",x,y);
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieces[xi][yi].isKing()||(pieces[xi][yi].isFire() ^ (yf-yi<0))) {
			if (!(pieces[xi][yi].hasCaptured())&&(xf - xi == 1 || xf - xi == -1)&&(yf - yi == 1 || yf - yi == -1)) {
				if (pieces[xf][yf] == null) {
					return true;
				}
				return false;
			}
			if ((xf - xi == 2 || xf - xi == -2)&&(yf - yi == 2 || yf - yi == -2)) {
				if (pieces[xf][yf] != null || pieces[(xf + xi)/2][(yf + yi)/2] == null) {
					return false;
				}
				if (pieces[xi][yi].isFire() != pieces[(xf + xi)/2][(yf + yi)/2].isFire()) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if (x<0||x>7||y<0||y>7) return false;
		if (pieces[x][y] == null) {
			if (hasSelected) {
				if (hasMoved) {
					if (pieces[xSelected][ySelected].hasCaptured()) {
						return validMove(xSelected,ySelected,x,y);
					}
					return false;
				}
				return validMove(xSelected,ySelected,x,y);
			}
			return false;
		}
		if (hasMoved) {
			return false;
		}
		return (pieces[x][y].isFire() == isFireTurn);
	}

	public void select(int x, int y) {
		if (hasSelected && validMove(xSelected,ySelected,x,y)) {
			pieces[xSelected][ySelected].move(x,y);
			hasMoved = true;
			if (pieces[x][y] == null) {
				xSelected = -1;
				hasSelected = false;
				return;
			}
		}
		xSelected = x;
		ySelected = y;
		hasSelected = true;
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (pieceAt(xSelected,ySelected) != null) pieces[xSelected][ySelected].doneCapturing();
		isFireTurn = !isFireTurn;
		hasSelected = false;
		hasMoved = false;
		xSelected = -1;
		ySelected = -1;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						fire = fire + 1;
					} else {
						water = water + 1;
					}
				}
			}
		}
		if (fire == 0) {
			if (water == 0) {
				return "No One";
			}
			return "Water";
		} else if (water == 0) {
			return "Fire";
		}
		return null;
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);

		Board b = new Board(false);

		boolean loop = true;
		while(loop) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (x >= 8) x = 7.5;
				if (y >= 8) y = 7.5;
				if (x <= 0) x = 0.5;
				if (y <= 0) y = 0.5;
				if (b.canSelect((int) x, (int) y)) b.select((int) x,(int) y);
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) b.endTurn();
				if (b.winner() != null) {
					System.out.println(b.winner());
					loop = false;
				}
			}
			StdDrawPlus.show(20);
		}
		StdDrawPlus.show(5000);
		System.exit(0);
	}
}