public class Board {

	//location of pieces
	private Piece[][] container;
	private int N = 8;
	private boolean fireTurn = true;
	private Piece selected;
	private boolean hasMoved = false;
	private int pieceSelectedX, pieceSelectedY;

	//set dimensions
	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board myboard = new Board(false);

		while (true) {
			drawBoard(N, myboard);
			if (myboard.winner() == null) {
				if (StdDrawPlus.mousePressed()) {
					int x = (int) StdDrawPlus.mouseX();
					int y = (int) StdDrawPlus.mouseY();
					Piece curr = myboard.pieceAt(x, y);
					if (myboard.canSelect(x, y)) {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(x + .5, y + .5, .5);
						myboard.select(x, y);
					}
				} else if (StdDrawPlus.isSpacePressed()) {
					if (myboard.canEndTurn()) {
						myboard.endTurn();
					}
				}
			} else {
				System.out.println(myboard.winner());
			}
			StdDrawPlus.show(25);
		}
	}

	private static void drawBoard(int N, Board b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
				else				  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
		b.drawPieces();
	}

	public Board(boolean shouldBeEmpty) {
		container = new Piece[N][N];
		if (shouldBeEmpty == false) {
			fillBoard();
		}
	}

	private void drawPieces() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					if (pieceAt(i, j) != null) {
						StdDrawPlus.picture(i + .5, j + .5, 
							drawPiece(pieceAt(i, j)), 1, 1);
					}
				}
			}
		}
	}

	private String drawPiece(Piece piece) {
		String source = "";
		if (piece.isFire() && piece.isBomb() == false && piece.isShield() == false && piece.isKing() == false) {
			source = "img/pawn-fire.png";
		} else if (piece.isFire() && piece.isBomb() == false && piece.isShield() == false && piece.isKing()) {
			source = "img/pawn-fire-crowned.png";
		} else if (piece.isFire() && piece.isBomb() && piece.isKing() == false) {
			source = "img/bomb-fire.png";
		} else if (piece.isFire() && piece.isBomb() && piece.isKing()) {
			source = "img/bomb-fire-crowned.png";
		} else if (piece.isFire() && piece.isShield() && piece.isKing() == false) {
			source = "img/shield-fire.png";
		} else if (piece.isFire() && piece.isShield() && piece.isKing()) {
			source = "img/shield-fire-crowned.png";
		} else if (piece.isFire() == false && piece.isBomb() && piece.isKing() == false) {
			source = "img/bomb-water.png";
		} else if (piece.isFire() == false && piece.isBomb() && piece.isKing()) {
			source = "img/bomb-water-crowned.png";
		} else if (piece.isFire() == false && piece.isShield() && piece.isKing() == false) {
			source = "img/shield-water.png";
		} else if (piece.isFire() == false && piece.isShield() && piece.isKing()) {
			source = "img/shield-water-crowned.png";
		} else if (piece.isFire() == false && piece.isBomb() == false && piece.isShield() == false && piece.isKing() == false) {
			source = "img/pawn-water.png";
		} else if (piece.isFire() == false && piece.isBomb() == false && piece.isShield() == false && piece.isKing()) {
			source = "img/pawn-water-crowned.png";
		}
		return source;
	}

	private void fillBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					if (j == 7) {
						place(new Piece(false, this, i, j, "pawn"), i, j);
					} else if (j == 6) {
						place(new Piece(false, this, i, j, "shield"), i, j);
					} else if (j == 5) {
						place(new Piece(false, this, i, j, "bomb"), i, j);
					} else if (j == 2) {
						place(new Piece(true, this, i, j, "bomb"), i, j);
					} else if (j == 1) {
						place(new Piece(true, this, i, j, "shield"), i, j);
					} else if (j == 0) {
						place(new Piece(true, this, i, j, "pawn"), i, j);
					}
				}
			}
		}
	}
	public Piece pieceAt(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y <= N) {
			return container[x][y];
		}
		return null;
	}

	// private void fillBoard2() {
	// 	container[2][2] = new Piece(false, this, 2, 2, "shield");
	// }

	public boolean canSelect(int x, int y) {
		Piece saidp = pieceAt(x, y);
		if (saidp != null) {
			if (fireTurn && saidp.isFire() || fireTurn == false && saidp.isFire() == false) {
				if (selected == null || hasMoved == false) {
					return true;
				}
			}
		} else {
			if (selected != null && hasMoved == false) {
				if (validMove(pieceSelectedX, pieceSelectedY, x, y)) {
					return true;
				}
			} else if (selected != null && hasMoved && selected.hasCaptured()) {
				if (validMove(pieceSelectedX, pieceSelectedY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece curr = pieceAt(xi, yi);
		if (!((0 <= xf && xf < N) && (0 <= yf && yf < N))) {
			return false;
		} else if (hasMoved == false || hasMoved == true && curr != null && curr.hasCaptured() == true) {
			return validMovePawn(xi, yi, xf, yf);
		}
		return false;
	}

	private boolean validMovePawn(int xi, int yi, int xf, int yf) {
		Piece curr = pieceAt(xi, yi);
		if (container[xf][yf] == null) {
			if (curr != null) {
				if (curr.isKing()) {
					if (((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) && hasMoved == false) {
						return true;
					} else if ((Math.abs(xf - xi) == 2 && (Math.abs(yf - yi) == 2) )) {
						if (hasMoved == false || hasMoved && curr.hasCaptured()) {
							int xpos = xi + ((xf - xi) / 2);
							int ypos = yi + ((yf - yi) / 2);
							Piece victim = pieceAt(xpos, ypos);
							if (victim != null && victim.isFire() != curr.isFire()) {
								return true;
							}
						}
					}
				} else if (fireTurn) {
					if (hasMoved == false && yf == (yi + 1) && (xf == (xi + 1) || xf == (xi - 1))) {
						return true;
					} else if (yf == (yi + 2) && xf == (xi - 2)) {
						Piece enemy = pieceAt(xi - 1, yi + 1);
						if (enemy != null && enemy.isFire() != curr.isFire()) {
							return true;
						}
					} else if (yf == (yi + 2) && xf == (xi + 2)) {
						Piece enemy = pieceAt(xi + 1, yi + 1);
						if (enemy != null && enemy.isFire() != curr.isFire()) {
							return true;
						}
					}
				} else {
					if (hasMoved == false && yf == (yi - 1) && (xf == (xi + 1) || xf == (xi - 1))) {
						return true;
					} else if (yf == (yi - 2) && xf == (xi - 2)) {
						Piece enemy = pieceAt(xi - 1, yi - 1);
						if (enemy != null && enemy.isFire() != curr.isFire()) {
							return true;
						}
					} else if (yf == (yi - 2) && xf == (xi + 2)) {
						Piece enemy = pieceAt(xi + 1, yi - 1);
						if (enemy != null && enemy.isFire() != curr.isFire()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		Piece curr = pieceAt(x ,y);
		if (curr != null && selected == null) {
			selected = curr;
			pieceSelectedY = y;
			pieceSelectedX = x;
		} else if (curr != null && selected != null) {
			selected = curr;
			pieceSelectedX = x;
			pieceSelectedY = y;
		} else if (curr == null && selected != null) {
			place(selected, x, y);
		}
	}

	public void place(Piece p, int x, int y) {
		int px = 0;
		int py = 0;
		boolean onBoard = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) == p) {
					onBoard = true;
					px = i;
					py = j;
				}
			}
		}
		if (p != null) {
			if (onBoard) {
				container[x][y] = p;
				p.move(x, y);
				pieceSelectedY = y;
				pieceSelectedX = x;
				remove(px, py);
				hasMoved = true;
			} else {
				container[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y) {
		Piece p = pieceAt(x, y);
		if (0 <= x && x < N && 0 <= y && y < N) {
			container[x][y] = null;
			return p;
		}
		System.out.println("No piece to remove at location: " + x + ", " + y);
		return null;
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		fireTurn = (!(fireTurn));
		selected.doneCapturing();
		selected = null;
		hasMoved = false;
		pieceSelectedX = 0;
		pieceSelectedY = 0;
	}

	public String winner() {
		int firecount = 0;
		int watercount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = container[i][j];
				if (p != null) {
					if (p.isFire()) {
						firecount += 1;
					} else {
						watercount += 1;
					}
				}
			}
		}
		if (firecount + watercount == 0) {
			return "No one";
		} else if (firecount == 0) {
			return "Water";
		} else if (watercount == 0) {
			return "Fire";
		} else {
			return null;
		}
	}	
}