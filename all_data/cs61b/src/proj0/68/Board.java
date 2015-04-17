public class Board {
	private static int size = 8;
	private Piece[][] pieces = new Piece[size][size];
	private int firePieces = 0, waterPieces = 0;
	private boolean currentPlayer = true;
	private Piece selected;
	private int selectedX = -1, selectedY = -1;
	private boolean hasMoved = false;

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);
		Board b = new Board(false);
		while(true) {
			b.drawBoard();
			b.drawPieces();
			if (StdDrawPlus.mousePressed()) {
				int tempX = (int) StdDrawPlus.mouseX();
				int tempY = (int) StdDrawPlus.mouseY();
				if (b.canSelect(tempX, tempY)) {
					b.select(tempX, tempY);
				}
			}
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			StdDrawPlus.show(25);
			if (b.winner() != null) {
				if (StdDrawPlus.isNPressed()) {
					b = new Board(false);
				}
			}
		}
	}

	/* Draw a blank sizexsize board */
	private void drawBoard() {
		for (int x = 0; x < size; x+=1) {
			for (int y = 0; y < size; y+=1) {
				if (selectedX == x && selectedY == y && selected != null) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if ((x + y) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
			}
		}
	}

	/* Fill the board with pieces */
	private void drawPieces() {
		for (int y = 0; y < size; y+=1) {
			for (int x = 0; x < size; x+=1) {
				if (pieceAt(x, y) != null) {
					String type;
					Piece p = pieceAt(x, y);
					/* Check type */
					if (p.isBomb()) {
						type = "bomb";
					} else if (p.isShield()) {
						type = "shield";
					} else {
						type = "pawn";
					}
					/* Check side */
					if (p.isFire()) {
						type += "-fire";
					} else {
						type += "-water";
					}
					/* Check kingness */
					if (p.isKing()) {
						type += "-crowned";
					}
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/" + type + ".png", 1, 1);
				}
			}
		}
	}

	/* Initialize all of the pieces */
	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int y = 0; y < size; y+=1) {
				for (int x = 0; x < size; x+=1) {
					if (y==0 && (x%2==0)) {
						place(new Piece(true, this, x, y, "pawn"), x, y);
					} else if (y==1 && (x%2!=0)) {
						place(new Piece(true, this, x, y, "shield"), x, y);
					} else if (y==2 && (x%2==0)) {
						place(new Piece(true, this, x, y, "bomb"), x, y);
					} else if (y==size-3 && (x%2!=0)) {
						place(new Piece(false, this, x, y, "bomb"), x, y);
					} else if (y==size-2 && (x%2==0)) {
						place(new Piece(false, this, x, y, "shield"), x, y);
					} else if (y==size-1 && (x%2!=0)) {
						place(new Piece(false, this, x, y, "pawn"), x, y);
					}
				}
			}
		}
	}

	/* Return the piece at location (x, y) */
	public Piece pieceAt(int x, int y) {
		if (x < size && y < size && x >= 0 && y >= 0) {
			return pieces[x][y];
		} return null;
	}

	/* Return true if location (x, y) can be selected */
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null && !hasMoved) {
			return (p.isFire() == currentPlayer);
		} else if (selected != null) {
			if (!hasMoved) {
				return (validMove(selectedX, selectedY, x, y));
			} else {
				return (selected.hasCaptured() && validMove(selectedX, selectedY, x, y));
			}
		} else {
			return false;
		}
	}

	/* Return true if location (xf, yf) is a valid move from (xi, yi) */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (pieceAt(xf, yf) != null) {
			return false;
		} else {
			if (p.isKing()) {
				if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) {
					Piece x = pieceAt((xf + xi) / 2, (yf + yi) / 2);
					return (x != null && (x.isFire() != p.isFire()));
				} return ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1) && !hasMoved);
			} else {
				if (p.isFire()) {
					if ((Math.abs(xf - xi) == 2) && ((yf - yi) == 2)) {
						Piece x = pieceAt((xf + xi) / 2, (yf + yi) / 2);
						return (x != null && !x.isFire());
					} return ((Math.abs(xf - xi) == 1) && ((yf - yi) == 1) && !hasMoved);
				} else {
					if ((Math.abs(xf - xi) == 2) && ((yi - yf) == 2)) {
						Piece x = pieceAt((xf + xi) / 2, (yf + yi) / 2);
						return (x != null && x.isFire());
					} return ((Math.abs(xf - xi) == 1) && ((yi - yf) == 1) && !hasMoved);
				}
			}
		}
	}

	/* Select location (x, y) */
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		selectedX = x;
		selectedY = y;
		if (p == null) {
			if (selected != null) {
				selected.move(x, y);
				hasMoved = true;
			}
			selected = pieceAt(x, y);
		} else {
			selected = p;
		}
	}

	/* Place piece p at location (x, y) */
	public void place(Piece p, int x, int y) {
		pieces[x][y] = p;
		if (p.isFire()) {
			firePieces += 1;
		} else {
			waterPieces += 1;
		}
	}

	/* Remove the piece at location (x, y) */
	public Piece remove(int x, int y) {
		if (!(x < size && y < size && x >= 0 && y >= 0)) {
			System.out.println("Location is out of bounds.");
			return null;
		} else {
			Piece temp = pieceAt(x, y);
			pieces[x][y] = null;
			if (temp.isFire()) {
				firePieces -= 1;
			} else {
				waterPieces -= 1;
			}
			return temp;
		}
	}

	/* Return true if the current turn can be ended */
	public boolean canEndTurn() {
		return hasMoved;
	}
	/* End the current turn */
	public void endTurn() {
		currentPlayer = !currentPlayer;
		hasMoved = false;
		if (selected != null) {
			selected.doneCapturing();
		}
		selected = null;
		selectedX = -1;
		selectedY = -1;
		drawBoard();
		drawPieces();
	}

	/* Return the winner */
	public String winner() {
		if (firePieces == 0 && waterPieces == 0) {
			System.out.println("No one wins.");
			return "No one";
		} else if (firePieces == 0) {
			System.out.println("Water wins!");
			return "Water";
		} else if (waterPieces == 0) {
			System.out.println("Fire Wins!");
			return "Fire";
		} else {
			return null;
		}
	}
}