public class Board {
	private int N;
	private Piece[][] pieces;
	private boolean fireTurn;
	private Piece selectedPiece;
	private int xSelected;
	private int ySelected;
	private boolean moved;

	public Board(boolean shouldBeEmpty) {
		N = 8;
		pieces = new Piece[N][N];
		fireTurn = true;
		selectedPiece = null;
		xSelected = 0;
		ySelected = 0;
		moved = false;
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) {
						createPiece(i, j);
					}
				}
			}
		}
	}

	/** Used to create the initial layout of the pieces. */
	private void createPiece(int x, int y) {
		Piece newPiece = null;
		if (y == 0) {
			newPiece = new Piece(true, this, x, y, "pawn");
		}
		else if (y == N - 1) {
			newPiece = new Piece(false, this, x, y, "pawn");
		}
		else if (y == 1) {
			newPiece = new Piece(true, this, x, y, "shield");
		}
		else if (y == N - 2) {
			newPiece = new Piece(false, this, x, y, "shield");	
		}
		else if (y == 2) {
			newPiece = new Piece(true, this, x, y, "bomb");
		}
		else if (y == N - 3) {
			newPiece = new Piece(false, this, x, y, "bomb");
		}
		place(newPiece, x, y);
	}

	/** Gets the piece at position (x, y) on the board,
	  * or null if there is no piece. */
	public Piece pieceAt(int x, int y) {
		if (x >= N || x < 0 || y >= N || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	/** Returns true if there is a piece
	  * at (x, y) and it can be selected
	  * or if an empty square can be selected. */
	public boolean canSelect(int x, int y) {
		Piece checkPiece = pieceAt(x, y);
		if (checkPiece != null) {
			if (fireTurn == checkPiece.isFire()) {
				if (selectedPiece == null) {
					return true;
				}
				else if (!moved) {
					return true;
				}
			}
		}
		else if (checkPiece == null && selectedPiece != null) {
			boolean valid = validMove(xSelected, ySelected, x, y);
			if (!moved && valid) {
				return true;
			}
			else if (selectedPiece.hasCaptured() && valid && Math.abs(x - xSelected) == 2) {
				return true;
			}
		}
		return false;
	}

	/** Returns true if the piece at (xi, yi) can either
	  * move to (xf, yf) or capture to (xf, yf). */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean valid = false;
		Piece checkPiece = pieceAt(xi, yi);
		int dx = xf - xi;
		int dy = yf - yi;
		if (xf >= N || xf < 0 || yf >= N || yf < 0 || pieceAt(xf, yf) != null || checkPiece == null) {
			valid = false;
		}
		else if (checkPiece.isKing()) {
			if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
				valid = true;
			}
			else if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
				Piece otherPiece = pieceAt(xi + (dx / 2), yi + (dy / 2));
				if (otherPiece != null && checkPiece.isFire() != otherPiece.isFire()) {
					valid = true;
				}
			}
		}
		else if (checkPiece.isFire()) {
			if (Math.abs(dx) == 1 && dy == 1) {
				valid = true;
			}
			else if (Math.abs(dx) == 2 && dy == 2) {
				Piece otherPiece = pieceAt(xi + (dx / 2), yi + 1);
				if (otherPiece != null && checkPiece.isFire() != otherPiece.isFire()) {
					valid = true;
				}
			}
		}
		else {
			if (Math.abs(dx) == 1 && dy == -1) {
				valid = true;
			}
			else if (Math.abs(dx) == 2 && dy == -2) {
				Piece otherPiece = pieceAt(xi + (dx / 2), yi - 1);
				if (otherPiece != null && checkPiece.isFire() != otherPiece.isFire()) {
					valid = true;
				}
			}
		}
		return valid;
	}

	/** Selects the piece at (x, y) if possible. */
	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
		}
		else {
			selectedPiece.move(x, y);
			moved = true;
		}
		xSelected = x;
		ySelected = y;
	}

	/** Places p at (x, y). */
	public void place(Piece p, int x, int y) {
		if (x < N && y < N) {
			pieces[x][y] = p;
		}
	}

	/** Executes a remove and returns the piece that was removed. */
	public Piece remove(int x, int y) {
		if (x >= N || x < 0 || y >= N || y < 0) {
			System.out.println("Piece to remove out of bounds.");
			return null;
		}
		Piece temp = pieceAt(x, y);
		if (temp == null) {
			System.out.println("No piece to remove at location.");
			return null;
		}
		place(null, x, y);
		return temp;
	}

	/** Returns whether or not the the current player can
	  * end their turn. */
	public boolean canEndTurn() {
		Boolean canEnd = false;
		if (moved) {
			canEnd = true;
		}
		return canEnd;
	}

	/** Called at the end of each turn. */
	public void endTurn() {
		selectedPiece.doneCapturing();
		selectedPiece = null;
		moved = false;
		fireTurn = (!fireTurn);
	}

	/** Returns the winner of the game. */
	public String winner() {
		int water = 0;
		int fire = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece temp = pieceAt(i, j);
				if (temp != null) {
					if (temp.isFire()) {
						fire += 1;
					}
					else {
						water += 1;
					}
				}
			}
		}
		if (water == 0 && fire == 0) {
			return "No one";
		}
		else if (water == 0) {
			return "Fire";
		}
		else if (fire == 0) {
			return "Water";
		}
		return null;
	}

	/** Draws an N x N board. */
	private void drawBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (selectedPiece != null && selectedPiece == pieceAt(i, j)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (pieces[i][j] != null) {
					String img = imagePath(pieces[i][j]);
					StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
				}
			}
		}
	}

	/** Returns the image path corresponding to a given piece. */
	private String imagePath(Piece p) {
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
		return img;
	}

	public static void main(String[] args) {
		Board b = new Board(false);

		StdDrawPlus.setXscale(0, b.N);
		StdDrawPlus.setYscale(0, b.N);

		while (b.winner() == null) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                		b.select(x, y);
                }
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
	}
}
