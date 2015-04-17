public class Board {
	private static Piece[][] pieces;
	private boolean turn;
	private Piece p_selected;
	private boolean hasNotMoved;
	private int[] p_coord;

	// public static boolean emptyBoard;

	public Board(boolean shouldBeEmpty) {
		turn = true; //true correspond to fire's turn
  		p_selected = null;
		hasNotMoved = true;
		p_coord = new int[2];


		if (shouldBeEmpty) {
			for (int i = 0; i < 8; i ++) {
				for (int j = 0; j < 8; j ++){
					if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
			}
		} else {
			for (int i = 0; i < 8; i ++) {
				for (int j = 0; j < 8; j ++) {
					if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					if ((i + j) % 2 == 0) {
						System.out.println(i);
						System.out.println(j);
						if (i == 0) pieces[j][i] = new Piece(true, this, i, j, "pawn");
						else if (i == 1) pieces[j][i] = new Piece(true, this, i, j, "shield");
						else if (i == 2) pieces[j][i] = new Piece(true, this, i, j, "bomb");
						else if (i == 5) pieces[j][i] = new Piece(false, this, i, j, "bomb");
						else if (i == 6) pieces[j][i] = new Piece(false, this, i, j, "shield");
						else if (i == 7) pieces[j][i] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
	}

	private static void drawBoard(int N) {
		for (int i = 0; i < N; i ++) {
			for (int j = 0; j < N; j ++) {
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null) {
					Piece curr = pieces[i][j];
					if (curr.isFire()) {
						if (curr.isBomb()) {
							if (curr.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1); 
							else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						} else if (curr.isShield()) {
							if (curr.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1); 
							else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1); 
						} else {
							if (curr.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					} else {
						if (curr.isBomb()) {
							if (curr.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);  
						} else if (curr.isShield()) {
							if (curr.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						} else {
							if (curr.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);  
						}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7) return null;
		else if (pieces[x][y] == null) return null;
		else return pieces[x][y];
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7) {
			System.out.println("Out of bound parameters");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("No piece at selected position");
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if (x > 7 || y > 7 || p == null);
		else pieces[x][y] = p;
	}

	public boolean canSelect(int x, int y) {
		int quad; //which quadrant is the piece at x, y in
		if (x > 7 || y > 7) return false;
		else if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == turn) {
			if (p_selected == null) return true;
			else if (p_selected != null && hasNotMoved) return true;
			return false;
		} 

		Piece curr = pieceAt(x, y);
		if (p_selected == null) return false;
		if (curr == null && (hasNotMoved || p_selected.hasCaptured()) ) {

			if (p_selected.hasCaptured() && (x != p_coord[0] || y != p_coord[1])) return false;

			if (x > p_coord[0] && y >p_coord[1]) quad =1;	//establishes which quadrant relative to the previously selected piece is the piece at x, y
			else if (x < p_coord[0] && y > p_coord[1]) quad = 2;
			else if (x < p_coord[0] && y < p_coord[1]) quad = 3;
			else quad = 4;

			System.out.println(Math.abs(p_coord[0] - x));
			if (p_selected.isFire() && Math.abs(p_coord[0] - x) == 2) {
				if (quad == 1) {
					if (pieceAt(x + 1, y + 1).isFire() != turn) return true;
				} else if (quad == 2) {
					if (pieceAt(x - 1, y + 1).isFire() != turn) return true;
				} else if (quad == 3) {
					if (p_selected.isKing()) {
						if (pieceAt(x - 1, y - 1).isFire() != turn) return true;
						else return false;
					} else return false;
				} else if (quad == 4) {
					if (p_selected.isKing()) {
						if (pieceAt(x + 1, y - 1).isFire() != turn) return true;
						else return false;
					} else return false;
				}
			} else if (p_selected.isFire() && Math.abs(p_coord[0] - x) == 1) {
				if (quad == 1 || quad == 2) return true;
				else if (p_selected.isKing()) return true;
				return false; 
			}

			if (!p_selected.isFire() && Math.abs(p_coord[0] - x) == 2) {
				if (quad == 4) {
					if (pieceAt(x + 1, y - 1).isFire() == turn) return true;
				} else if (quad == 3) {
					if (pieceAt(x - 1, y - 1).isFire() == turn) return true;
				} else if (quad == 2) {
					if (p_selected.isKing()) {
						if (pieceAt(x - 1, y + 1).isFire() == turn) return true;
						else return false;
					} else return false;
				} else if (quad == 1) {
					if (p_selected.isKing()) {
						if (pieceAt(x + 1, y + 1).isFire() == turn) return true;
						else return false;
					} else return false;
				}
			} else if (!p_selected.isFire() && Math.abs(p_coord[0] - x) == 1) {
				if (quad == 4 || quad == 3) return true;
				else if (p_selected.isKing()) return true;
				return false;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		System.out.println(canSelect(x, y));
		if (canSelect(x, y)) {
			if (pieceAt(x, y) == null) {
				hasNotMoved = false;
				p_selected.move(x, y);
			}
			p_coord[0] = x;
			p_coord[1] = y;
			p_selected = pieceAt(x, y);
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}

	public boolean canEndTurn() {
		boolean wellCanYou = true;
		if (p_selected == null || hasNotMoved || !p_selected.hasCaptured()) return false;
		for (int i = -2; i < 3; i ++) {
			for (int j = -2; j < 3; j ++) {
				wellCanYou = wellCanYou && canSelect(p_coord[0] + i, p_coord[1] + j);
			}
		}
		return wellCanYou;
	}

	public void endTurn() {
		p_selected.doneCapturing();
		p_selected = null;
		p_coord = new int[2];
		turn = !turn;
	}

	public String winner() {
		int wSum = 0;
		int fSum = 0;
		for (int i = 0; i < 8; i ++ ){
			for (int j = 0; j < 8; j ++ ){
				if (pieceAt(i, j).isFire()) fSum += 1;
				if (pieceAt(i, j).isFire()) wSum += 1;
			}
		}
		if (wSum == 0 && fSum != 0) return "Fire";
		else if (fSum == 0 && wSum != 0) return "Water";
		else if (fSum == 0 & wSum == 0) return "No one";
		else return null;
	}

	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new Piece[N][N];
  		Board board = new Board(false);

		while (true){
			drawBoard(N);
			if (StdDrawPlus.mousePressed()) {
				double a = StdDrawPlus.mouseX();
				double b = StdDrawPlus.mouseY();
				int x = (int)(a);
				int y = (int)(b);
				if (board.canSelect(x, y)) board.select(x, y);
			}

			if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) board.endTurn();
			StdDrawPlus.show(10);
		}
	}
}