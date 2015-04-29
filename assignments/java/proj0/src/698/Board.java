public class Board {
	private static final int SIZE = 8;

	private Piece[][] checker;
	private boolean moved = false;
	private boolean selected = false;
	private Piece selection = null;
	private int selectedX;
	private int selectedY;
	private boolean has_Fire_left = false;
	private boolean has_Water_left = false;
	private boolean is_fire_turn = true;

	public static void main(String[] args) {
		Board b = new Board(true);
		b.place(new Piece(true, b, 5, 5, "bomb"), 5, 5);
		b.place(new Piece(false, b, 6, 6, "pawn"), 6, 6);
		while(true) {
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
 			}
 			else if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
 				b.endTurn();
 			}
 			b.drawBoard();
 			StdDrawPlus.show(50);

		}
		
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, SIZE);
		StdDrawPlus.setYscale(0, SIZE);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (selected && i == selectedX && j == selectedY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if(checker[i][j] != null) {
					drawPiece(i, j, checker[i][j]);
				}
			}
		}
	}

	private void drawPiece(int x, int y, Piece p) {
		String img = "img/";

		if (p.isBomb()) {
			img += "bomb";
		}
		else if (p.isShield()) {
			img += "shield";
		}
		else {
			img += "pawn";
		}
		if (p.isFire()) {
			img += "-fire";
		}
		else {
			img += "-water";
		}
		if (p.isKing()) {
			img += "-crowned";
		}
		img += ".png";

		StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
	} 

	public Board(boolean shouldbeEmpty) {
		checker = new Piece[8][8];
		if (!shouldbeEmpty) {
			for (int i = 0; i < SIZE; i += 2) {
				checker[i][0] = new Piece(true, this, i, 0, "pawn");
				checker[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
				checker[i][2] = new Piece(true, this, i, 2, "bomb");
				checker[i + 1][SIZE - 1] = new Piece(false, this, i + 1, SIZE - 1, "pawn");
				checker[i][SIZE - 2] = new Piece(false, this, i, SIZE - 2, "shield");
				checker[i + 1][SIZE - 3] = new Piece(false, this, i + 1, SIZE - 3, "bomb");
			}
		}

	}

	private boolean OutofBound(int x, int y) {
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
			return true;
		}
		else {
			return false;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (OutofBound(x, y) || checker[x][y] == null) {
			return null;
		}
		else {
			return checker[x][y];
		}
	}

	public void place(Piece aPiece, int x, int y) {
		if (!OutofBound(x, y)) {
			checker[x][y] = aPiece;
		}
	}

	public boolean canSelect(int x, int y) {
		if (OutofBound(x, y)) {
			return false;
		}
		else if (checker[x][y] != null && is_fire_turn == checker[x][y].isFire() && (!selected || !moved)) {
			return true;
		}
		else if (checker[x][y] == null && selected) {
			if (!moved && validMove(selectedX, selectedY, x, y)) {
				return true;
			}
			else if (selection.hasCaptured() && validMove(selectedX, selectedY, x, y) && Math.abs(selectedX - x) == 2) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (OutofBound(xi, yi) || OutofBound(xf, yf) || checker[xf][yf] != null || p == null) {
			return false;
		}
		if (!p.isKing()) {
			if ((p.isFire() && yf < yi) || (!p.isFire() && yf > yi)) {
				return false;
			}
		}
		if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)){
			return true;
		}
		if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
			Piece p1 = pieceAt((xi + xf) / 2, (yi + yf) / 2);
			if (p1 != null && p1.isFire() != p.isFire()) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedX = x;
			selectedY = y;
			selected = true;
			selection = checker[x][y];
		}
		else {
			pieceAt(selectedX, selectedY).move(x, y);
			moved = true;
			selectedX = x;
			selectedY = y;
		}
	}

	public Piece remove(int x, int y) {
		Piece removed = checker[x][y];
		if (OutofBound(x, y) || pieceAt(x, y) == null) {
			return null;
		}
		else {
			checker[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn() {
		if (moved == true) {
			return moved;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		selected = false;
		if (selection != null) {
			selection.doneCapturing();
		}
		selection = null;
		moved = false;
		is_fire_turn = !is_fire_turn;
	}

	public String winner() {
		has_Fire_left = has_Water_left = false;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (checker[i][j] != null) {
					if (checker[i][j].isFire()) {
						has_Fire_left = true;
					}
					else {
						has_Water_left = true;
					}
				}
			}
		}
		if (has_Water_left == true && has_Fire_left == true) {
			return null;
		}
		if (has_Fire_left == true && has_Water_left == false) {
			return "Fire";
		}
		if (has_Fire_left == false && has_Water_left == true) {
			return "Water";
		}
		else {
			return "No one";
		}
	}
}