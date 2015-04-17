// Hi hi

public class Board {
	private static final int N = 8;
	private Piece[][] pieceAry;
	private int selX, selY, prevX, prevY; //coordinate of the selected square.
	private Piece selectedPiece = null; //at beginning, not a piece on the board.
	private Piece prevPiece = null;
	private boolean moved = false;
	private boolean someCaptured = false;
	private int turn = 1; //true means fire's turn, false means water.

	public Board(boolean shouldBeEmpty) {
		pieceAry = new Piece[N][N];
		if (!shouldBeEmpty) {
			for(int i = 0; i < N; i++) 
				for (int j = 0; j < N; j++) 
					if ((i + j) % 2 == 0) {
						if (j == 0)
							pieceAry[i][j] = new Piece(true, this, i, j, "pawn");
						else if (j == 1)
							pieceAry[i][j] = new Piece(true, this, i, j, "shield");
						else if (j == 2)
							pieceAry[i][j] = new Piece(true, this, i, j, "bomb");
						else if (j == (N - 3))
							pieceAry[i][j] = new Piece(false, this, i, j, "bomb");
						else if (j == (N - 2))
							pieceAry[i][j] = new Piece(false, this, i, j, "shield");
						else if (j == (N - 1))
							pieceAry[i][j] = new Piece(false, this, i, j, "pawn");
					}
		}
	}

	private boolean hasPiece(int x, int y) {
		return pieceAry[x][y] != null;
	}

	private int absolute(int x) {
		return x < 0 ? -x : x;
	}

	private boolean isValid(int x, int y) {
		return (x >= 0 && x < N) && (y >= 0 && y < N);
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece cur = pieceAt(xi, yi);
		Piece des = pieceAt(xf, yf);
		if (isValid(xi, yi) && isValid(xf, yf)) {
			if (des == null && cur != null) { // moving to empty square.
				if (cur.isFire() && !cur.isKing()) { // if it's a fire but non king piece.
					if (yf - yi == 1 && absolute(xf - xi) == 1 && !cur.hasCaptured()) { // valid move.
						return true;
					}else if (yf - yi == 2 && xf - xi == 2) { // valid capture.
						if (pieceAt(xi + 1, yi + 1) != null && !pieceAt(xi + 1, yi + 1).isFire())
							return true;
					}else if (yf - yi == 2 && xi - xf == 2) { // valid capture.
						if (pieceAt(xi - 1, yi + 1) != null && !pieceAt(xi - 1, yi + 1).isFire())
							return true;
					}
				}else if (cur.isFire() && cur.isKing()) { // if it's a fire king piece
					if (absolute(yf - yi) == 1 && absolute(xf - xi) == 1 && !cur.hasCaptured()) { // valid move.
						return true;
					}else if (yf - yi == 2 && xf - xi == 2) { // valid capture.
						if (pieceAt(xi + 1, yi + 1) != null && !pieceAt(xi + 1, yi + 1).isFire())
							return true;
					}else if (yf - yi == 2 && xi - xf == 2) { // valid capture.
						if (pieceAt(xi - 1, yi + 1) != null && !pieceAt(xi - 1, yi + 1).isFire())
							return true;
					}else if (yi - yf == 2 && xi - xf == 2) { // valid capture.
						if (pieceAt(xi - 1, yi - 1) != null && !pieceAt(xi - 1, yi - 1).isFire())
							return true;
					}else if (yi - yf == 2 && xf - xi == 2) { // valid capture.
						if (pieceAt(xi + 1, yi - 1) != null && !pieceAt(xi + 1, yi - 1).isFire())
							return true;
					}
				}
				else if (!cur.isFire() && !cur.isKing()) { // if it's a water but non king piece.
					if (yi - yf == 1 && absolute(xf - xi) == 1 && !cur.hasCaptured()) { // valid move.
						return true;
					}else if (yi - yf == 2 && xf - xi == 2) { // valid capture.
						if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire())
							return true;
					}else if (yi - yf == 2 && xi - xf == 2) { // valid capture.
						if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire())
							return true;
					}
				}else if (!cur.isFire() && cur.isKing()) { // if it's a water king piece.
					if (absolute(yi - yf) == 1 && absolute(xf - xi) == 1 && !cur.hasCaptured()) { // valid move.
						return true;
					}else if (yi - yf == 2 && xf - xi == 2) { // valid capture.
						if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire())
							return true;
					}else if (yi - yf == 2 && xi - xf == 2) { // valid capture.
						if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire())
							return true;
					}else if (yf - yi == 2 && xi - xf == 2) { // valid capture.
						if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire())
							return true;
					}else if (yf - yi == 2 && xf - xi == 2) { // valid capture.
						if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire())
							return true;
					}
				}
			}
		}

		return false;
	}

	public Piece pieceAt(int x, int y) {
		if (!isValid(x, y) || !hasPiece(x, y))
			return null;
		return pieceAry[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) == null) { // if empty square.
			if (prevPiece != null && !moved && validMove(selX, selY, x, y))
				return true;
			else if (prevPiece != null && prevPiece.hasCaptured() && validMove(selX, selY, x, y))
				return true;
		}else {
			if ((turn == 1 && pieceAt(x, y).isFire()) || (turn == 0 && !pieceAt(x, y).isFire())) { //checking if it's the right player's turn.
				if (prevPiece == null) {
						return true;
				}else if (prevPiece != null && !moved) {
						return true;
				}
			}
		}

		return false;
	}

	public void select(int x, int y) {
		selectedPiece = pieceAt(x, y);
		if (selectedPiece != null) { // selecting a piece.
			prevPiece = selectedPiece;
			selX = x;
			selY = y;
		}else if (selectedPiece == null && prevPiece != null) { // moving previously selected piece to position (x, y).
			prevX = selX;
			prevY = selY;
			selX = x;
			selY = y;
			pieceAry[x][y] = prevPiece;
			prevPiece.move(x, y);
			pieceAry[prevX][prevY] = null;
			moved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (p != null && isValid(x, y))
			pieceAry[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (!isValid(x, y)) {
			System.out.println("Given position of x and y is not a valid position.");
			return null;
		}else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece at the give position.");
			return null;
		}
		Piece temp = pieceAt(x, y);
		pieceAry[x][y] = null;

		return temp;
	}

	public boolean canEndTurn() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) != null && pieceAt(i, j).hasCaptured())
					someCaptured = true;
			}
		}
		return moved || someCaptured;
	}

	public void endTurn() {
		prevPiece = null;
		selectedPiece = null;
		moved = false;
		someCaptured = false;
		selX = -1;
		selY = -1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (pieceAt(i, j) != null)
					pieceAt(i, j).doneCapturing();

		turn = turn == 1 ? 0 : 1;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire())
						fire += 1;
					else
						water += 1;
				}
			}
		}
		if (fire == 0 && water == 0)
			return "No one";
		else if (water == 0)
			return "Fire";
		else if (fire == 0)
			return "Water";
		else
			return null;
	}

	private void drawBoard(Board b, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (selX == i && selY == j && prevPiece == pieceAt(i, j))
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				if (b.hasPiece(i, j)) {
					if (pieceAt(i, j).isFire()) {
						if (pieceAt(i, j).isBomb()) {
							if (pieceAt(i, j).isKing())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
						}else if (pieceAt(i, j).isShield()) {
							if (pieceAt(i, j).isKing())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
						}else{
							if (pieceAt(i, j).isKing())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							else							
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
						}
					}else {
						if (pieceAt(i, j).isBomb()) {
							if (pieceAt(i, j).isKing())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
						}else if (pieceAt(i, j).isShield()) {
							if (pieceAt(i, j).isKing())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
						}else {
							if (pieceAt(i, j).isKing())
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 8;
		Board b = new Board(false);
		StdDrawPlus.setScale(0, n);

		while (true) {
			b.drawBoard(b, n);
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			StdDrawPlus.show(10);
		}
	}
}