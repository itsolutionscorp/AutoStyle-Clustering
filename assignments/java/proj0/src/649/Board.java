public class Board {

	private static final int BOARD_SIZE = 8;
	private static final String BOMB_TAG = "bomb";
	private static final String SHIELD_TAG = "shield";
	private static final String NORMAL_TAG = "pawn";   

	private Piece selectedPiece;
	private int spX, spY;
	private boolean hasMovedPiece;
	private boolean hasDefaultMoved;

	private Piece[][] state = new Piece[BOARD_SIZE][BOARD_SIZE];
	private boolean isFireTurn = true;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			initPieces();
		}
	}

	private void initPieces() {
		//Initialize Team Fire
		for (int i = 0; i < BOARD_SIZE; i += 2) {
			place(new Piece(true, this, i, 0, NORMAL_TAG), i, 0);
			place(new Piece(true, this, i+1, 1, SHIELD_TAG), i+1, 1);
			place(new Piece(true, this, i, 2, BOMB_TAG), i , 2);
		}

		//Initialize Team Water
		for (int i = 0; i < BOARD_SIZE; i += 2) {
			place(new Piece(false, this, i+1, BOARD_SIZE-1, NORMAL_TAG), i+1, BOARD_SIZE-1);
			place(new Piece(false, this, i, BOARD_SIZE-2, SHIELD_TAG), i, BOARD_SIZE-2);
			place(new Piece(false, this, i+1, BOARD_SIZE-3, BOMB_TAG), i+1, BOARD_SIZE-3);
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < BOARD_SIZE && x >= 0 && y < BOARD_SIZE && y >= 0) {
			return state[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE || !areEven(x, y)) {
			return false;
		}
		Piece p = pieceAt(x, y);

		// Can choose one of your piece if you have not moved 
		if (p != null && p.isFire() == isFireTurn && !hasMovedPiece) {
			return true;
		}

		// Can choose empty space if you have a piece and it's a valid move
		if (p == null && selectedPiece != null && pieceAt(spX, spY) != null && validMove(spX, spY, x, y) && !hasDefaultMoved) {
			return true;
		}

		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (!areEven(xf, yf) || pieceAt(xf, yf) != null || yf < 0 || yf > BOARD_SIZE || xf > BOARD_SIZE || xf < 0) {
			return false;
		}

		int xDif = xf - xi;
		int yDif = yf - yi; 
		Piece p = pieceAt(xi, yi);
		Piece pk = pieceAt(xi + xDif/2, yi + yDif/2);
		Piece pf = pieceAt(xi + xDif/2, yi + 1);
		Piece pw = pieceAt(xi + xDif/2, yi - 1);

		if (hasDefaultMoved && Math.abs(xDif) != 2) {
			return false;
		}

		// King
		if (!hasMovedPiece && p.isKing() && Math.abs(xDif) == 1 && Math.abs(yDif) == 1 && pieceAt(xf, yf) == null ||
			p.isKing() && Math.abs(xDif) == 2 && Math.abs(yDif) == 2 && pk != null && pk.isFire() != p.isFire()) {
			return true;
		}

		// Fire
		if (!hasMovedPiece && p.isFire() && Math.abs(xDif) == 1 && yDif == 1 && pieceAt(xf, yf) == null || 
			p.isFire() && Math.abs(xDif) == 2 && yDif == 2 && pf != null && pf.isFire() != p.isFire()) {
			return true;
		}

		// Water
		if (!hasMovedPiece && !p.isFire() && Math.abs(xDif) == 1 && yDif == -1 && pieceAt(xf, yf) == null ||
			!p.isFire() && Math.abs(xDif) == 2 && yDif == -2 && pw != null && pw.isFire() != p.isFire()) {
			return true;
		}

		return false;
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) {
			if (Math.abs(x - spX) == 1) {
				hasDefaultMoved = true;
			}
			selectedPiece.move(x, y);
			hasMovedPiece = true;
		} else {
			selectedPiece = p;
		}
		spX = x;
		spY = y;
	}

	/**
	 * If x, y are valid, places p into state
	*/
	public void place(Piece p, int x, int y) {
		if (p != null && x < BOARD_SIZE && y < BOARD_SIZE && x >= 0 && y >= 0) {
			state[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x >= BOARD_SIZE || y >= BOARD_SIZE || x < 0 || y < 0) {
			System.out.println("OUT OF BOUNDS");
			return null;
		}
		Piece p = pieceAt(x, y);
		if (p == null) {
			System.out.println("NOTHING AT (" + x + ", " + y + ")");
		} else if (p.equals(selectedPiece) && p.isBomb() && p.hasCaptured()) {
			selectedPiece = null;
		}

		state[x][y] = null;
		return p;
	}

	public boolean canEndTurn()  {
		if (hasMovedPiece) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		isFireTurn = !isFireTurn;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		hasMovedPiece = false;
		hasDefaultMoved = false;

		// leave spX and spY alone since only used
		// when there is selectedPiece
	}

	public String winner() {
		int fire = 0;
		int water = 0;

		for (Piece[] pcs : state) {
			for (Piece p : pcs) {
				if (p != null) {
					if (p.isFire()) {
						fire++;
					} else {
						water++;
					}
				}
			}
		}

		if (fire > 0 && water == 0) {
			return "Fire";
		} else if (water > 0 && fire == 0) {
			return "Water";
		} else if (fire == 0 && water == 0) {
			return "No one";
		} else {
			return null;
		}

	}

	private boolean areEven(int i, int j) {
		return ((i + j) & 1) == 0;
	}

	private static void drawEmptyBoard(Board b) {
		StdDrawPlus.setXscale(0, BOARD_SIZE);
		StdDrawPlus.setYscale(0, BOARD_SIZE);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (b.areEven(i, j)) {
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
				}
				double x = (double)(i) + 0.5;
				double y = (double)(j) + 0.5;
				StdDrawPlus.filledSquare(x, y, 0.5);
			}
		}
	}
	private static void drawPieces(Board b) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				b.drawPiece(b.pieceAt(i, j), ((double)(i) + 0.5), ((double)(j) + 0.5));
			}
		}
	}

	private static void drawPiece(Piece p, double x, double y) {
		if (p == null) {
			return;
		}

		String team = (p.isFire()) ? "-fire" : "-water";
		String crown = (p.isKing()) ? "-crowned.png" : ".png";
		String type;
		if (p.isBomb()) {
			type = BOMB_TAG;
		} else if (p.isShield()) {
			type = SHIELD_TAG;
		} else {
			type = NORMAL_TAG;
		}

		StdDrawPlus.picture(x, y, "img/" + type + team + crown, 1, 1);
	}

	private static void update(Board b, boolean hasSelected, int x, int y) {
		drawEmptyBoard(b);
		if (hasSelected) {
			StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
			StdDrawPlus.filledSquare((double)(x)+0.5, (double)(y)+0.5, 0.5);
		}
		drawPieces(b);
		StdDrawPlus.show(25);
	}
	
	public static void main(String[] args) {
		Board b = new Board(false);
		update(b, false, 0, 0);

		while (b.winner() == null) {
			int x = -1;
			int y = -1;
			if (StdDrawPlus.mousePressed()) {
				StdDrawPlus.show(25);
                x = (int) StdDrawPlus.mouseX();
                y = (int) StdDrawPlus.mouseY();
            }
            if (b.canSelect(x, y)) {
            	b.select(x, y);
            	update(b, true, x, y);
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            	update(b, false, 0, 0);
            }
		}
		System.out.println(b.winner());
	}
}