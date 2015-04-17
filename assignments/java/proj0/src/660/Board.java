public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private boolean isFireTurn = true;
	private Piece selected = null;
	private int selectedX = -1;
	private int selectedY = -1;
	private boolean hasMoved = false;


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
			pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
			pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
			pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
			pieces[1][1] = new Piece(true, this, 1, 1, "shield");
			pieces[3][1] = new Piece(true, this, 3, 1, "shield");
			pieces[5][1] = new Piece(true, this, 5, 1, "shield");
			pieces[7][1] = new Piece(true, this, 7, 1, "shield");
			pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
			pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
			pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
			pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
			pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
			pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
			pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
			pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
			pieces[0][6] = new Piece(false, this, 0, 6, "shield");
			pieces[2][6] = new Piece(false, this, 2, 6, "shield");
			pieces[4][6] = new Piece(false, this, 4, 6, "shield");
			pieces[6][6] = new Piece(false, this, 6, 6, "shield");
			pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
			pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
			pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
			pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		}
	 }

	private static String findImage(Piece p) {
		StringBuilder img = new StringBuilder();
		img.append("img/");
		img.append(getType(p));
		img.append("-");
		if (p.isFire()) { img.append("fire"); }
		else { img.append("water"); }
		if (p.isKing()) { img.append("-"); img.append("crowned"); }
		img.append(".png");
		return img.toString(); }

	private static String getType(Piece p) {
		if (p.isBomb()) {
			return "bomb";
		}
		else if (p.isShield()) {
			return "shield";
		}
		else {
			return "pawn";
		}
	}


	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((selected != null) && (selected == pieceAt(i, j))) {
					StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
				}
				else if ((pieceAt(i, j) == null) && (canSelect(i, j))) {
					StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
				}
				else if ((i+j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.WHITE);} 
				else { StdDrawPlus.setPenColor(StdDrawPlus.BLACK); }
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				drawPiece(i, j);
			}
		}
	}


	private void drawPiece(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			StdDrawPlus.picture(x + .5, y + .5, findImage(p), 1, 1);
		}
	}

	public Piece pieceAt(int x, int y) {
		if (!validCoords(x, y)) {
			return null;
		}
		Piece p = pieces[x][y];
		return p;
	}

	public void place(Piece p, int x, int y) {
		if (!validCoords(x, y)) {
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (p == pieceAt(i, j)) {
					pieces[i][j] = null;
				}
			}
		}
		pieces[x][y] = p;
	}

	public boolean canSelect(int x, int y) {
		if (!validCoords(x, y)) {
			return false; }
		if (hasMoved && ((selected == null) || (!selected.hasCaptured()))) {
			return false;
		}
		Piece z = pieceAt(x, y);
		if ((z != null) && (z.isFire() == isFireTurn)) {
			if ((selected == null) || (!selected.hasCaptured())) {
				return true;
			}
			else {return false;}
		}
		else {
			if ((selected != null) && (validMove(selectedX, selectedY, x, y))) {
				return true;
			}
			else {return false;}
		}
	}

	private boolean validCoords(int x, int y) {
	//	boolean a = ((x + y)%2 == 0);
		boolean a = true;
		boolean b = ((x > -1) && (x < 8));
		boolean c = ((y > -1) && (y < 8));
		return ((a && b) && c);
	}

	private boolean validSimpleMove(int xi, int yi, int x, int y) {
		Piece p = pieceAt(xi, yi);
		if (p == null) {return false;}
		boolean a = (pieceAt(x, y) == null);
		boolean b = validCoords(x, y);
		boolean c = ((x == xi + 1) || (x == xi - 1));
		boolean d = ((y == yi + 1) && ((p.isFire()) || (p.isKing())));
		boolean e = ((y == yi - 1) && ((!p.isFire()) || (p.isKing())));
		boolean f = (d || e);
		return (a && (b && (c && f)));
	}

	private boolean validJump(int xi, int yi, int x, int y) {
		Piece p = pieceAt(xi, yi);
		if (p == null) {return false;}
		boolean a = (pieceAt(x, y) == null);
		boolean b = validCoords(x, y);
		boolean c = ((x == xi + 2) || (x == xi - 2));
		boolean d = ((y == yi + 2) && ((p.isFire()) || (p.isKing())));
		boolean e = ((y == yi - 2) && ((!p.isFire()) || (p.isKing())));
		boolean f = (d || e);
		int nx = (xi + x)/2;
		int ny = (yi + y)/2;
		Piece toKill = pieceAt(nx, ny);
		boolean g = ((toKill != null) && (toKill.isFire() != p.isFire()));
		return (a && (b && (c && (f && g))));
	}

	private boolean validMove(int xi, int yi, int x, int y) {
		Piece p = pieceAt(xi, yi);
		boolean a = (validSimpleMove(xi, yi, x, y) && (!p.hasCaptured()));
		boolean b = (validJump(xi, yi, x, y));
		return (a||b);
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
	//	if ((p != null) && (p.isFire() == isFireTurn)) {
		if (p != null) {
			selected = p;
			selectedX = x;
			selectedY = y;
		}
	//	if ((p == null) && (validMove(selectedX, selectedY, x, y))) {
		if (p== null) {
			selected.move(x, y);
			selectedX = x;
			selectedY = y;
			hasMoved = true;
		}
	}

	public Piece remove(int x, int y) {
		if (! validCoords(x, y)) {
			System.out.println("Remove arguments invalid; out of bounds or inaccessible square.");
			return null;
		}
		Piece p = pieces[x][y];
		if (p == null) {
			System.out.println("Remove arguments invalid; no piece at coordinates.");
		}
		pieces[x][y] = null;
		if (p == selected) {
			selected = null;
		}
		return p;
	}

	public boolean canEndTurn() {
		return (hasMoved || ((selected != null) && (selected.hasCaptured())));
	}

	public void endTurn() {
		isFireTurn = (!isFireTurn);
		if (selected != null) {selected.doneCapturing();}
		selected = null;
		hasMoved = false;
	}

	public String winner() {
		boolean firePiece = false;
		boolean waterPiece = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j ++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						firePiece = true;
					}
					else {
						waterPiece = true;
					}
			}
		}
		}
		if (firePiece && waterPiece) {
			return null;
		}
		else if (firePiece) {
			return "Fire";
		}
		else if (waterPiece) {
			return "Water";
		}
		else {
			return "No one";
		}
	}

	private void gameplay() {
		if (StdDrawPlus.isSpacePressed()) {
			if (canEndTurn()) {
				endTurn();
				if (winner() != null) {
					System.out.print(winner());
					System.out.println(" has won the game!");
				}
			}
		}
		else if (StdDrawPlus.mousePressed()) {
			int x = (int) StdDrawPlus.mouseX();
			int y = (int) StdDrawPlus.mouseY();
			if (canSelect(x, y)) {
				select(x, y);
				}
			}
		}



	public static void main(String[] args) {
		Board b = new Board(false);

		
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		
		while (true) {
			b.drawBoard();
			b.gameplay();
			StdDrawPlus.show(15);}}





}
