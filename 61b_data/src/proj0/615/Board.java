public class Board {
	private static final int FINALN = 8;
	private Piece[][] piece;
	private int player = 0; // 0 is fire, 1 is water.
	private boolean hasMoved, hasSelected, empty;
	private Piece selectedPiece = null;
	private int selectedX, selectedY;

	public Board(boolean shouldBeEmpty) {
		this.empty = shouldBeEmpty;

		if (empty == true) {
			this.piece = new Piece[FINALN][FINALN];
			return;
		}

		if (!empty) {
			this.piece = new Piece[FINALN][FINALN];
			for (int i = 0; i < piece.length; i++) {
				if (i % 2 != 0) {
					Piece waterPawn = new Piece(false, this, i, 7, "pawn");
					this.place(waterPawn, i, 7);

					Piece waterBomb = new Piece(false, this, i, 5, "bomb");
					this.place(waterBomb, i, 5);

					Piece fireShield = new Piece(true, this, i, 1, "shield");
					this.place(fireShield, i, 1);

				} else {
					Piece fireBomb = new Piece(true, this, i, 2, "bomb");
					this.place(fireBomb, i, 2);

					Piece waterShield = new Piece(false, this, i, 6, "shield");
					this.place(waterShield, i, 6);

					Piece firePawn = new Piece(true, this, i, 0, "pawn");
					this.place(firePawn, i, 0);
				}
			}
		}
	}

	/*
	 * Draws a blank checker board if isEmpty is true, default checker board
	 * otherwise.
	 */
	private void drawBoard() {
		for (int i = 0; i < FINALN; i++) {
			for (int j = 0; j < FINALN; j++) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				if (piece[i][j] != null && piece[i][j].isFire()) {
					if (piece[i][j].isBomb() && !piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/bomb-fire.png", 1, 1);
					} else if (piece[i][j].isBomb() && piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/bomb-fire-crowned.png", 1, 1);
					} else if (piece[i][j].isShield() && !piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/shield-fire.png", 1, 1);
					} else if (piece[i][j].isShield() && piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/shield-fire-crowned.png", 1, 1);
					} else if (piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/pawn-fire-crowned .png", 1, 1);
					} else {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/pawn-fire.png", 1, 1);
					}
				}
				if (piece[i][j] != null && !piece[i][j].isFire()) {
					if (piece[i][j].isBomb() && piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/bomb-water-crowned.png", 1, 1);
					} else if (piece[i][j].isBomb() && !piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/bomb-water.png", 1, 1);
					} else if (piece[i][j].isShield() && piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/shield-water-crowned.png", 1, 1);
					} else if (piece[i][j].isShield() && !piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/shield-water.png", 1, 1);
					} else if (piece[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/pawn-water-crowned.png", 1, 1);
					} else {
						StdDrawPlus.picture(i + .5, j + .5,
								"img/pawn-water.png", 1, 1);
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0) {
			return null;
		}
		if (x >= piece.length || y >= piece.length) {
			return null;
		} else if (piece[x][y] != null) {
			return piece[x][y];
		} else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x > piece.length || y > piece.length) {
			return;
		}

		for (int i = 0; i < piece.length; i++) {
			for (int k = 0; k < piece.length; k++) {
				if (piece[i][k] == p && piece[i][k] != null) {
					remove(i, k);
					piece[x][y] = p;
					return;
				}
			}
		}

		piece[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x > piece.length || y > piece.length) {
			System.out.println("Out of range");
			return null;
		}

		Piece removed = piece[x][y];
		if (removed == null) {
			System.out.println("No piece at this location");
			return null;
		} else {
			piece[x][y] = null;
			return removed;
		}
	}

	public void select(int x, int y) {

		if (piece[x][y] != null) {
			selectedPiece = piece[x][y];
			selectedX = x;
			selectedY = y;
			hasSelected = true;
		} else if (piece[x][y] == null && selectedPiece != null) {
			selectedPiece.move(x, y);
			hasMoved = true;
		}
	}

	public boolean canSelect(int x, int y) {

		if (selectedPiece != null && selectedPiece.hasCaptured()
				&& piece[x][y] != null) {
			return false;
		}

		if (selectedPiece != null && piece[x][y] == null && hasMoved == false
				&& validMove(selectedX, selectedY, x, y)) {
			return true;
		}

		if (selectedPiece != null && piece[x][y] == null
				&& selectedPiece.hasCaptured()
				&& validMove(selectedX, selectedY, x, y)) {
			return true;
		} else if (piece[x][y] == null) {
			return false;
		}

		if (piece[x][y].isFire() && player == 0
				|| (!piece[x][y].isFire() && player == 1)) {
			if (hasSelected == false) {
				return true;
			}

			if ((selectedPiece != null || hasSelected == true)
					&& hasMoved == false) {
				return true;
			}
		}
		return false;
	}

	/*
	 * A method that assess the validity of a move based on game rules. Takes in
	 * coordinates from selected piece, and the place the player is attempting
	 * to move to.
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {

		int xCoord = (xi + xf) / 2;
		int yCoord = (yi + yf) / 2;

		if ((Math.abs(xi - xf) > 2 || Math.abs(yi - yf) > 2)
				&& piece[xCoord][yCoord] == null) {
			return false;
		}
		if (xi == xf || yi == yf) {
			return false;
		}

		if (selectedPiece != null && !selectedPiece.isFire()
				&& !selectedPiece.isKing()) {
			if (yf > yi) {
				return false;
			}
		}

		// Assess fire side. Can move from [0,0] -> [1,1] etc.
		if (selectedPiece != null && selectedPiece.isFire()
				&& !selectedPiece.isKing()) {
			if (yf < yi) {
				return false;
			}
		}

		// returns false if you try to move over a null space
		if (pieceAt(xCoord, yCoord) == null
				&& ((Math.abs(xi - xf) == 2 || Math.abs(yi - yf) == 2))) {
			return false;
		}

		// return false if you try to move over your own piece
		if (pieceAt(xCoord, yCoord) != null
				&& ((Math.abs(xi - xf) == 2 || Math.abs(yi - yf) == 2))
				&& pieceAt(xCoord, yCoord).isFire() == selectedPiece.isFire()) {
			return false;
		}
		return true;
	}

	public boolean canEndTurn() {

		if (selectedPiece != null && hasMoved == false) {
			return false;
		}
		if (selectedPiece != null && hasMoved == false && !empty) {
			return false;
		}
		if (selectedPiece != null
				&& (hasMoved == true || selectedPiece.hasCaptured() || empty == true)) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		selectedPiece.doneCapturing();
		selectedPiece = null;
		player = (player + 1) % 2;
		hasMoved = false;
	}

	public String winner() {
		int numFires = 0;
		int numWaters = 0;

		if (piece == null) {
			return "No one";
		}

		for (int i = 0; i < FINALN; i++) {
			for (int k = 0; k < FINALN; k++) {
				if (piece[i][k] != null) {
					if (piece[i][k].isFire()) {
						numFires += 1;
					} else {
						numWaters += 1;
					}
				}
			}
		}

		if (numFires > 0 && numWaters > 0) {
			return null;
		} else if (numFires == 0 && numWaters == 0) {
			return "No one";
		} else if (numFires > 0 && numWaters == 0) {
			return "Fire";
		} else {
			return "Water";
		}
	}

	public static void main(String[] args) {

		Board B = new Board(false);
		StdDrawPlus.setXscale(0, FINALN);
		StdDrawPlus.setYscale(0, FINALN);

		/**
		 * Monitors for mouse presses. Wherever the mouse is pressed, a new
		 * piece appears.
		 */
		while (true) {
			B.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				double xi = StdDrawPlus.mouseX();
				double yi = StdDrawPlus.mouseY();
				if (B.canSelect((int) xi, (int) yi)) {
					B.select((int) xi, (int) yi);
				}
			}

			if (StdDrawPlus.isSpacePressed() && B.canEndTurn()) {
				B.endTurn();

				if (B.winner() != null) {
					B.winner();
				}
			}

			StdDrawPlus.show(100);
		}
	}

}
