public class Board {
	private Piece[][] pieces;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private int numFire;
	private int numWater;
	private boolean canMove;
	private boolean hasMoved;
	private boolean turnFire;
	private boolean canEndTurn;

	public static void main(String[] args) {
		Board b = new Board(false);
		int mouseX;
		int mouseY;

		// set window scale for 8x8
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);

		// game loop
		while (b.winner() == null) {
			// mouse press selection handling
			if (StdDrawPlus.mousePressed()) {
				mouseX = (int) StdDrawPlus.mouseX();
				mouseY = (int) StdDrawPlus.mouseY();
				if (b.canSelect(mouseX, mouseY)) {
					b.select(mouseX, mouseY);
				}
			}

			// end turn handling
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			b.drawBoard();
			StdDrawPlus.show(1);
		}
		System.out.println(b.winner());
	}

	/* constructor */
	public Board(boolean shouldEmpty) {
		this.pieces = new Piece[8][8];
		this.selectedPiece = null;
		this.canEndTurn = false;
		this.canMove = true;
		this.hasMoved = false;
		this.selectedX = -1;
		this.selectedY = -1;
		this.turnFire = true;

		// setup only if shouldEmpty is false
		if (!shouldEmpty) {
			// setup the fire side
			for (int i = 0; i < 4; i += 1) {
				this.pieces[i * 2][0] = new Piece(true, this, i * 2, 0, "pawn");
			}
			for (int i = 0; i < 4; i += 1) {
				this.pieces[i * 2 + 1][1] = new Piece(true, this, (i * 2) + 1,
						1, "shield");
			}
			for (int i = 0; i < 4; i += 1) {
				this.pieces[i * 2][2] = new Piece(true, this, i * 2, 2, "bomb");
			}

			// setup the water side
			for (int i = 0; i < 4; i += 1) {
				this.pieces[(i * 2) + 1][7] = new Piece(false, this,
						(i * 2) + 1, 7, "pawn");
			}
			for (int i = 0; i < 4; i += 1) {
				this.pieces[i * 2][6] = new Piece(false, this, i * 2, 6,
						"shield");
			}
			for (int i = 0; i < 4; i += 1) {
				this.pieces[(i * 2) + 1][5] = new Piece(false, this,
						(i * 2) + 1, 5, "bomb");
			}
		}
	}

	/* drawing the board and images */
	private void drawBoard() {
		// drawing the 8x8 board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);

				// handles highlighting a selected square
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (i == this.selectedX && j == this.selectedY) {
					StdDrawPlus.filledSquare(this.selectedX + .5,
							this.selectedY + .5, .5);
				}

				// drawing type conditions
				if (this.pieces[i][j] != null) {
					if (this.pieces[i][j].isFire()) {
						if (this.pieces[i][j].isKing()) {
							if (this.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/bomb-fire-crowned.png", 1, 1);
							} else if (this.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/pawn-fire-crowned.png", 1, 1);
							}
						} else {
							if (this.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/bomb-fire.png", 1, 1);
							} else if (this.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/shield-fire.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/pawn-fire.png", 1, 1);
							}
						}
					} else {
						if (this.pieces[i][j].isKing()) {
							if (this.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/bomb-water-crowned.png", 1, 1);
							} else if (this.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/pawn-water-crowned.png", 1, 1);
							}
						} else {
							if (this.pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/bomb-water.png", 1, 1);
							} else if (this.pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/shield-water.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5,
										"img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	/* return the object at x, y on the board */
	public Piece pieceAt(int x, int y) {
		if (((0 <= x) && (x < 8)) && ((0 <= y) && (y < 8))) {
			return this.pieces[x][y];
		}
		return null;
	}

	/* return whether the position x, y can be selected */
	public boolean canSelect(int x, int y) {
		if (this.selectedPiece == null) {
			if (pieceAt(x, y) != null
					&& pieceAt(x, y).isFire() == this.turnFire) {
				return true;
			}
		} else {
			if ((!hasMoved && pieceAt(x, y) != null && pieceAt(x, y).isFire() == this.turnFire)
					|| (this.canMove && (validMove(this.selectedX,
							this.selectedY, x, y)))) {
				return true;
			}
		}
		return false;
	}

	/* when the placement is a movement, checks if the move is valid */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((0 <= xi) && (xi < 8) && (0 <= xf) && (xf < 8) && (0 <= yi)
				&& (yi < 8) && (0 <= yf) && (yf < 8)) {
			if ((pieceAt(xf, yf) == null)) {
				// handling for multiple captures
				if (hasMoved && canMove) {
					if (pieceAt(xi, yi).isKing()) {
						if (((xi + 2 == xf) && (yi + 2 == yf))
								&& (pieceAt(xi + 1, yi + 1) != null)
								&& (this.pieceAt(xi + 1, yi + 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						} else if (((xi - 2 == xf) && (yi + 2 == yf))
								&& (pieceAt(xi - 1, yi + 1) != null)
								&& (this.pieceAt(xi - 1, yi + 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						} else if (((xi + 2 == xf) && (yi - 2 == yf))
								&& (pieceAt(xi + 1, yi - 1) != null)
								&& (this.pieceAt(xi + 1, yi - 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						} else if (((xi - 2 == xf) && (yi - 2 == yf))
								&& (pieceAt(xi - 1, yi - 1) != null)
								&& (this.pieceAt(xi - 1, yi - 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						}
					} else {
						if (pieceAt(xi, yi).isFire()) {
							if (((xi + 2 == xf) && (yi + 2 == yf))
									&& (pieceAt(xi + 1, yi + 1) != null)
									&& (this.pieceAt(xi + 1, yi + 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							} else if (((xi - 2 == xf) && (yi + 2 == yf))
									&& (pieceAt(xi - 1, yi + 1) != null)
									&& (this.pieceAt(xi - 1, yi + 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							}
						} else {
							if (((xi + 2 == xf) && (yi - 2 == yf))
									&& (pieceAt(xi + 1, yi - 1) != null)
									&& (this.pieceAt(xi + 1, yi - 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							} else if (((xi - 2 == xf) && (yi - 2 == yf))
									&& (pieceAt(xi - 1, yi - 1) != null)
									&& (this.pieceAt(xi - 1, yi - 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							}
						}
					} // handling for regular captures
				} else if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {
					if (pieceAt(xi, yi).isKing()) {
						if (((xi + 1 == xf) && (yi + 1 == yf))
								|| ((xi - 1 == xf) && (yi + 1 == yf))
								|| ((xi + 1 == xf) && (yi - 1 == yf))
								|| ((xi - 1 == xf) && (yi - 1 == yf))) {
							return true;
						}
					} else {
						if (pieceAt(xi, yi).isFire()) {
							if (((xi + 1 == xf) && (yi + 1 == yf))
									|| ((xi - 1 == xf) && (yi + 1 == yf))) {
								return true;
							}
						} else {
							if (((xi + 1 == xf) && (yi - 1 == yf))
									|| ((xi - 1 == xf) && (yi - 1 == yf))) {
								return true;
							}
						}

					}
				} else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
					if (pieceAt(xi, yi).isKing()) {
						if (((xi + 2 == xf) && (yi + 2 == yf))
								&& (pieceAt(xi + 1, yi + 1) != null)
								&& (this.pieceAt(xi + 1, yi + 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						} else if (((xi - 2 == xf) && (yi + 2 == yf))
								&& (pieceAt(xi - 1, yi + 1) != null)
								&& (this.pieceAt(xi - 1, yi + 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						} else if (((xi + 2 == xf) && (yi - 2 == yf))
								&& (pieceAt(xi + 1, yi - 1) != null)
								&& (this.pieceAt(xi + 1, yi - 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						} else if (((xi - 2 == xf) && (yi - 2 == yf))
								&& (pieceAt(xi - 1, yi - 1) != null)
								&& (this.pieceAt(xi - 1, yi - 1).isFire() != this
										.pieceAt(xi, yi).isFire())) {
							return true;
						}
					} else {
						if (pieceAt(xi, yi).isFire()) {
							if (((xi + 2 == xf) && (yi + 2 == yf))
									&& (pieceAt(xi + 1, yi + 1) != null)
									&& (this.pieceAt(xi + 1, yi + 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							} else if (((xi - 2 == xf) && (yi + 2 == yf))
									&& (pieceAt(xi - 1, yi + 1) != null)
									&& (this.pieceAt(xi - 1, yi + 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							}
						} else {
							if (((xi + 2 == xf) && (yi - 2 == yf))
									&& (pieceAt(xi + 1, yi - 1) != null)
									&& (this.pieceAt(xi + 1, yi - 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							} else if (((xi - 2 == xf) && (yi - 2 == yf))
									&& (pieceAt(xi - 1, yi - 1) != null)
									&& (this.pieceAt(xi - 1, yi - 1).isFire() != this
											.pieceAt(xi, yi).isFire())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/* selects a piece at location (x, y) */
	public void select(int x, int y) {
		// player hasn't selected a piece yet
		if (this.selectedPiece == null
				|| (!this.hasMoved && (this.pieceAt(x, y) != null) && (this
						.pieceAt(x, y).isFire() == this.turnFire))) {
			this.selectedPiece = this.pieces[x][y];
			this.selectedX = x;
			this.selectedY = y;
		} else { // player has selected a piece and wants to move it
			// set rule variables
			this.canMove = false;
			this.hasMoved = true;
			this.canEndTurn = true;
			this.pieceAt(selectedX, selectedY).move(x, y);
			this.place(this.remove(this.selectedX, this.selectedY), x, y);
		}
	}

	/*
	 * places a piece, p, at position x, y on the board and handles a capture if
	 * it happens
	 */
	public void place(Piece p, int x, int y) {
		if ((((0 <= x) && (x < 8)) && ((0 <= y) && (y < 8))) && (p != null)) {
			// p.move(x, y);

			// check if p has captured something & handle accordingly.
			if (p.hasCaptured()) {
				if (p.isBomb()) {
					// reset selection variables due to explosion
					this.selectedX = -1;
					this.selectedY = -1;
				} else {
					// update the variables and then move to new capped position
					this.selectedX = x;
					this.selectedY = y;
					this.pieces[x][y] = p;
					this.canMove = true;
				}
			} else {
				// update the variables and then regular move
				this.selectedX = x;
				this.selectedY = y;
				this.pieces[x][y] = p;
			}
		}
	}

	/* handling the removal of pieces. removes a piece and returns it */
	public Piece remove(int x, int y) {
		// temporary storage in order to return the object
		Piece tempStore;

		if (this.pieceAt(x, y) != null) { // in bounds
			tempStore = this.pieces[x][y];
			this.pieces[x][y] = null;
			return tempStore;
		} else { // out of bounds or no piece
			if (this.pieces[x][y] == null) {
				System.out.println("No piece at this location!");
				return null;
			} else {
				System.out.println("Selected out of bounds!");
				return null;
			}
		}
	}

	/* return the boolean canEndTurn */
	public boolean canEndTurn() {
		return this.canEndTurn;
	}

	/* Handles ending the turn and resetting variables */
	public void endTurn() {
		this.turnFire = !this.turnFire;
		this.canMove = true;
		this.hasMoved = false;
		this.canEndTurn = false;
		this.selectedPiece = null;
		
		if ((pieceAt(selectedX, selectedY) != null) && pieceAt(selectedX, selectedY).hasCaptured()){
			pieceAt(selectedX, selectedY).doneCapturing();
		}
		
		this.selectedX = -1;
		this.selectedY = -1;
	}

	/* determines winner of the game */
	public String winner() {
		this.numFire = 0;
		this.numWater = 0;
		// calculate how many fire and water pieces are on the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((this.pieceAt(i, j) != null) && this.pieceAt(i, j).isFire()) {
					this.numFire += 1;
				} else if (this.pieceAt(i, j) != null) {
					this.numWater += 1;
				}
			}
		}

		if ((this.numFire == 0) && (this.numWater == 0)) {
			return "No one";
		} else if (this.numFire == 0) {
			return "Water";
		} else if (this.numWater == 0) {
			return "Fire";
		} else {
			return null;
		}
	}
}