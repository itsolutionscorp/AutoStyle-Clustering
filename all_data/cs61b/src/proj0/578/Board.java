public class Board{
	private boolean isEmpty;
	private Piece[][] pieces;
	private boolean playerTurn = true;
	private int[] hasSelected = {-1, -1};
	private boolean canEnd = false;
	private int numFire = 0, numWater = 0;

	public Board(boolean shouldBeEmpty) {
		isEmpty = shouldBeEmpty;
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			boolean isFire;
			String type = "";
			for (int i = 0; i < 8; i++) {
				if (i == 0 || i == 7) {
					type = "pawn";
				}
				if (i == 1 || i == 6) {
					type = "shield";
				}
				if (i == 2 || i == 5) {
					type = "bomb";
				}
				if (i < 4) {
					isFire = true;
				} else {
					isFire = false;
				}
				for (int j = 0; j < 8; j++) {
					if (i != 3 && i != 4 && (i + j) % 2 == 0) {
						place(new Piece(isFire, this, j, i, type), j, i);
					}
				}
			}
		}
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		String type = "", side = "", crowned = "";
		Piece target;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == hasSelected[0] && j == hasSelected[1]) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);

				target = pieceAt(i, j);
				if (!isEmpty && target != null) {
					if (target.isBomb()) {
						type = "bomb";
					} else if (target.isShield()) {
						type = "shield";
					} else {
						type = "pawn";
					}

					if (target.isFire()) {
						side = "-fire";
					} else {
						side = "-water";
					}

					if (target.isKing()) {
						crowned = "-crowned";
					} else {
						crowned = "";
					}

					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + type + side + crowned + ".png", 1, 1);
				}
			}
		}
	}

	private boolean isOutofBounds(int x, int y) {
		return x < 0 || x >= 8 || y < 0 || y >= 8;
	}

	private boolean somethingSelected() {
		return hasSelected[0] != -1;
	}

	public Piece pieceAt(int x, int y) {
		if (isOutofBounds(x, y)) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x, y);
		if (piece != null && piece.isFire() == playerTurn) {
			return !canEndTurn();
		}
		if (somethingSelected()) {
			return validMove(hasSelected[0], hasSelected[1], x, y);
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece piece = pieceAt(xi, yi);
		if (piece == null || pieceAt(xf, yf) != null || (canEndTurn() && !piece.hasCaptured())) {
			return false;
		}
		int[] dir = new int[2];
		if (piece.isKing()) {
			dir[0] = 1;
			dir[1] = -1;
		} else if (piece.isFire()) {
			dir[0] = 1;
			dir[1] = 1;
		} else {
			dir[0] = -1;
			dir[1] = -1;
		}
		boolean[] answers = new boolean[2];
		Piece potentialCapture;
		for (int i = 0; i < 2; i++) {
			if (yf == yi + dir[i] && Math.abs(xf - xi) == 1 && !canEndTurn()) {
				answers[i] = true;
			} else if (yi + dir[i] == yf - dir[i] && Math.abs(xf - xi) == 2) {
				potentialCapture = pieceAt((xi + xf)/2, yi + dir[i]);
				answers[i] = (potentialCapture != null && potentialCapture.isFire() != playerTurn);
			} else {
				answers[i] = false;
			}
		}
		return answers[0] || answers[1];
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			Piece desired = pieceAt(hasSelected[0], hasSelected[1]);
			if (desired != null) {
				desired.move(x, y);
				canEnd = true;
			}
		}
		hasSelected[0] = x;
		hasSelected[1] = y;
	}

	private int[] findCoords(Piece p) {
		int[] result = {-1, -1};
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) == p) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}

	public void place(Piece p, int x, int y) {
		if (isOutofBounds(x, y) || p == null) {
			return;
		}
		int[] coords = findCoords(p);
		if (pieceAt(coords[0], coords[1]) != null) {
			remove(coords[0], coords[1]);
		}
		pieces[x][y] = p;
		if (p.isFire()) {
			numFire = numFire + 1;
		} else {
			numWater = numWater + 1;
		}
	}

	public Piece remove(int x, int y) {
		if (isOutofBounds(x, y)) {
			System.out.println("Coordinates are out of bounds.");
			return null;
		}
		Piece target = pieceAt(x, y);
		if (target == null) {
			System.out.println("No piece at desired coordinates.");
			return null;
		}
		if (target.isFire()) {
			numFire = numFire - 1;
		} else {
			numWater = numWater - 1;
		}
		pieces[x][y] = null;
		return target;
	}

	public boolean canEndTurn() {
		return canEnd;
	}

	public void endTurn() {
		playerTurn = !playerTurn;
		Piece piece = pieceAt(hasSelected[0], hasSelected[1]);
		if (piece != null) {
			piece.doneCapturing();
		}
		hasSelected[0] = -1;
		hasSelected[1] = -1;
		canEnd = false;
	}

	public String winner() {
		if (numFire == 0 && numWater == 0) {
			return "No one";
		}
		if (numFire == 0) {
			return "Water";
		}
		if (numWater == 0) {
			return "Fire";
		}
		return null;
	}

	public static void main(String[] args) {
		Board board = new Board(false);
		String winner;
		while (true) {
			board.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int)(StdDrawPlus.mouseX());
				int y = (int)(StdDrawPlus.mouseY());
				if (board.canSelect(x, y)) {
					board.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
					winner = board.winner();
					if (winner != null) {
						System.out.println(winner + " won!");
						System.exit(0);
					}
				}
			}
			if (StdDrawPlus.isNPressed()) {
				board = new Board(false);
			}
			StdDrawPlus.show(20);
		}
	}
}