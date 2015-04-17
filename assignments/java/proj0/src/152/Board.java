public class Board {

	private boolean[][] pieces;

	private Piece[][] gamePiece;

	private static final String PAWN = "pawn";
	private static final String BOMB = "bomb";
	private static final String SHIELD = "shield";
	private static final int FIRE = 0;
	private static final int WATER = 1;
	private int selectedX = -1;
	private int selectedY = -1;
	private boolean hasMoved = false;
	private int player;
	private static final int N = 8;
	private boolean shouldBeEmpty;
	private boolean hasMovedOneSpace = false;
	private boolean hasMovedTwoSpaces = false;

	/**
	 * Draws an N x N board. Adapted from:
	 * http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
	 */

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					pieces[i][j] = true;
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					pieces[i][j] = false;
				}

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				drawPiece(gamePiece[i][j], i, j);
			}
		}
	}

	// can get called with every click
	private void drawUpdatedBoard(int N) {
		// draw initial red/grey squares
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					pieces[i][j] = true;
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					pieces[i][j] = false;
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		// draw updated pieces
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = gamePiece[i][j];
				if (p != null) {
					drawPiece(p, i, j);
				}
			}
		}
		// draw selected piece in white
		if (selectedX != -1) {
			Piece p = gamePiece[selectedX][selectedY];
			if (p != null) {
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
				drawPiece(p, selectedX, selectedY);
			}
		}
	}

	private void drawPiece(Piece p, int i, int j) {
		if (p == null) {
			return;
		}

		if (!shouldBeEmpty) {
			if (p.isFire()) {
				if (p.isBomb() && p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5,
							"img/bomb-fire-crowned.png", 1, 1);
				} else if (p.isBomb() && !p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1,
							1);
				} else if (p.isShield() && p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5,
							"img/shield-fire-crowned.png", 1, 1);
				} else if (p.isShield() && !p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",
							1, 1);
				} else if (p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5,
							"img/pawn-fire-crowned.png", 1, 1);
				} else {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1,
							1);
				}
			} else {
				if (p.isBomb() && p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5,
							"img/bomb-water-crowned.png", 1, 1);
				} else if (p.isBomb() && !p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",
							1, 1);
				} else if (p.isShield() && p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5,
							"img/shield-water-crowned.png", 1, 1);
				} else if (p.isShield() && !p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png",
							1, 1);
				} else if (p.isKing()) {
					StdDrawPlus.picture(i + .5, j + .5,
							"img/pawn-water-crowned.png", 1, 1);
				} else {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png",
							1, 1);
				}
			}
		}
	}

	private void play() {
		player = FIRE;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		drawBoard(N);
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				int x1 = (int) x;
				int y1 = (int) y;
				System.out.println("x, y: " + x1 + " " + y1);
				if (canSelect(x1, y1)) {
					System.out.println("canSelect x, y: " + x1 + " " + y1);
					select(x1, y1);
				} else {
					System.out.println("cannot Select x, y: " + x1 + " " + y1);
				}
				drawUpdatedBoard(N);
			}

			if (StdDrawPlus.isSpacePressed()) {
				if (canEndTurn()) {
					System.out.println("ending turn");
					endTurn();
					if (winner() != null) {
						winner();
					}
				} else {
					System.out.println("cannot end turn");
				}
			}
			StdDrawPlus.show(25);
		}
	}

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		gamePiece = new Piece[N][N];
		pieces = new boolean[N][N];

		if (shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					pieces[i][j] = false;
					gamePiece[i][j] = null;
				}
			}
		} 
		else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) {
						pieces[i][j] = true;
					} else {
						pieces[i][j] = false;
					}
					switch (j) {
					case 0:
						if (pieces[i][j]) {
							gamePiece[i][j] = new Piece(true, this, i, j, PAWN);
						}
						break;
					case 1:
						if (pieces[i][j]) {
							gamePiece[i][j] = new Piece(true, this, i, j, SHIELD);
						}
						break;
					case 2:
						if (pieces[i][j]) {
							gamePiece[i][j] = new Piece(true, this, i, j, BOMB);
						}
						break;
					case 5:
						if (pieces[i][j]) {
							gamePiece[i][j] = new Piece(false, this, i, j, BOMB);
						}
						break;
					case 6:
						if (pieces[i][j]) {
							gamePiece[i][j] = new Piece(false, this, i, j, SHIELD);
						}
						break;
					case 7:
						if (pieces[i][j]) {
							gamePiece[i][j] = new Piece(false, this, i, j, PAWN);
						}
						break;
					default:
						gamePiece[i][j] = null;
						break;
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0) {
			return null;
		}
		if (y > 7 || y < 0) {
			return null;
		}
		return gamePiece[x][y];
	}

	public boolean canSelect(int x, int y) {
		// check out of bounds
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("out of bounds");
			return false;
		}
		// is a red square
		if ((x + y) % 2 != 0) {
			System.out.println("red square");
			return false;
		}
		if (hasMovedOneSpace) {
			return false;
		}


		Piece n = gamePiece[x][y];

		if (hasMoved && !hasMovedTwoSpaces && n == null) {
			return false;
		}

		if (hasMoved && n!= null && !n.equals(pieceAt(x, y))) { //cant click on a different piece after something has moved
			return false;
		}


		if (player == FIRE) {
			if (n == null) {
				// first picking an empty square w/o picking previous piece
				if (selectedX == -1) {
					System.out.println("first picked empty square");
					return false;
				}
				// if empty square and valid move to the empty square
				if (validMove(selectedX, selectedY, x, y)) {
					return true;
				} else {
					System.out.println("not valid move");
					return false;
				}
			} else {
				if (!n.isFire()) {
					System.out.println("water player but supposed to be fire");
					return false;
				}
				return true;
			}
		}

		if (player == WATER) {
			if (n == null) {
				// first picking an empty square w/o picking previous piece
				if (selectedX == -1) {
					System.out.println("first picked empty square");
					return false;
				}
				// if empty square and valid move to the empty square
				if (validMove(selectedX, selectedY, x, y)) {
					return true;
				} else {
					System.out.println("not valid move");
					return false;
				}
			} else {
				if (n.isFire()) {
					System.out.println("fire player but supposed to be water");
					return false;
				}
				return true;
			}
		}
		return false;

	}

	// check for kinged pieces and plain
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf < 0 || xf > 7 || yf < 0 || yf > 7) {
			System.out.println("final out of bounds in valid move");
			return false;
		}
		if (xi < 0 || xi > 7 || yi < 0 || yi > 7) {
			System.out.println("initial out of bounds in valid move");
			return false;
		}
		// is a red square
		if ((xf + yf) % 2 != 0) {
			System.out.println("red square in valid move");
			return false;
		}
		// if space is greater than 2 away
		if (((xf - xi) > 2) || ((xi - xf) > 2)) {
			System.out.println("x space more than 2 away in valid move");
			return false;
		}
		if (((yf - yi) > 2) || ((yi - yf) > 2)) {
			System.out.println("y space more than 2 away in valid move");
			return false;
		}
		// cannot move if piece exists at final pos
		Piece finalP = pieceAt(xf, yf);
		if (finalP != null) {
			System.out.println("in valid move, piece exists at final pos");
			return false;
		}

		Piece currentP = pieceAt(xi, yi);
		if (currentP == null) {
			return false; // cannot happen
		}

		if (player == FIRE) {
			if (!currentP.isKing()) {
				// move 1 space diagonally
				if (!currentP.hasCaptured()) { // cannot move 1 space if already
												// captured (moved 2 spaces)
					if ((yf - yi) == 1
							&& (((xf - xi) == 1) || ((xi - xf) == 1))) {
						return true;
					}
				}
				// move 2 spaces diagonally to up right
				if (((yf - yi) == 2) && ((xf - xi) == 2) && (yi != 7)
						&& (xi != 7)) {
					Piece middle = pieceAt(xi + 1, yi + 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (middle.isFire()) { // can't jump over your team
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}

				// move 2 spaces diagonally up left
				if (((yf - yi) == 2) && ((xi - xf) == 2) && (yi != 7)
						&& (xi != 0)) {
					Piece middle = pieceAt(xi - 1, yi + 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}
			} else { // if fire piece is king
						// move 1 space diagonally
				if (!currentP.hasCaptured()) { // cannot move 1 space if already
												// captured (moved 2 spaces)
					if ((((xf - xi) == 1) || ((xi - xf) == 1))
							&& (((yf - yi) == 1) || ((yi - yf) == 1))) {
						return true;
					}
				}
				// move 2 spaces diagonally to up right
				if (((xf - xi) == 2) && ((yf - yi) == 2) && (yi != 7)
						&& (xi != 7)) {
					Piece middle = pieceAt(xi + 1, yi + 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}

				// move 2 spaces diagonally up left
				if (((xi - xf) == 2) && ((yf - yi) == 2) && (yi != 7)
						&& (xi != 0)) {
					Piece middle = pieceAt(xi - 1, yi + 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}
				// move 2 spaces diagonally to down left
				if (((xi - xf) == 2) && ((yi - yf) == 2) && (yi != 0)
						&& (xi != 0)) {
					Piece middle = pieceAt(xi - 1, yi - 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}

				// move 2 spaces diagonally down right
				if (((xf - xi) == 2) && ((yi - yf) == 2) && (yi != 0)
						&& (xi != 7)) {
					Piece middle = pieceAt(xi + 1, yi - 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}
			}
		}
		// player is water
		else {
			if (!currentP.isKing()) { // plain water piece
				// move 1 space diagonally down
				if (!currentP.hasCaptured()) { // cannot move 1 space if already
												// captured (moved 2 spaces)
					if ((yi - yf) == 1
							&& (((xf - xi) == 1) || ((xi - xf) == 1))) {
						return true;
					}
				}
				// move 2 spaces diagonally to down right
				if (((yi - yf) == 2) && ((xf - xi) == 2) && (yi != 0)
						&& (xi != 7)) {
					Piece middle = pieceAt(xi + 1, yi - 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (!middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}

				// move 2 spaces diagonally down left
				if (((xi - xf) == 2) && ((yi - yf) == 2) && (yi != 0)
						&& (xi != 0)) {
					Piece middle = pieceAt(xi - 1, yi - 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (!middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}
			} else { // if water piece is king
						// move 1 space diagonally
				if (!currentP.hasCaptured()) { // cannot move 1 space if already
												// captured (moved 2 spaces)
					if ((((xf - xi) == 1) || ((xi - xf) == 1))
							&& (((yf - yi) == 1) || ((yi - yf) == 1))) {
						return true;
					}
				}
				// move 2 spaces diagonally to down right
				if (((xf - xi) == 2) && ((yi - yf) == 2) && (yi != 0)
						&& (xi != 7)) {
					Piece middle = pieceAt(xi + 1, yi - 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (!middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}

				// move 2 spaces diagonally down left
				if (((xi - xf) == 2) && ((yi - yf) == 2) && (yi != 0)
						&& (xi != 0)) {
					Piece middle = pieceAt(xi - 1, yi - 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (!middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}
				// move 2 spaces diagonally to up left
				if (((xi - xf) == 2) && ((yf - yi) == 2) && (yi != 7)
						&& (xi != 0)) {
					Piece middle = pieceAt(xi - 1, yi + 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (!middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}

				// move 2 spaces diagonally up right
				if (((xf - xi) == 2) && ((yf - yi) == 2) && (yi != 7)
						&& (xi != 7)) {
					Piece middle = pieceAt(xi + 1, yi + 1);
					if (middle == null) { // no middle piece
						System.out.println("no middle piece, can't capture");
						return false;
					}
					if (!middle.isFire()) {
						System.out.println("can't jump over your team");
						return false;
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (selectedX == -1) {
			selectedX = x;
			selectedY = y;
		}
		else { //else null pointer when piece is created
			Piece p = pieceAt(selectedX, selectedY);
			if ((selectedX != x) && (selectedY != y)) {
				p.move(x, y);
				hasMoved = true;
			}
			if ((selectedY - y) == 1 || (y - selectedY) == 1) {
				hasMovedOneSpace = true;
				selectedX = -1;
				selectedY = -1;
			}
			else {
				if (hasMoved) {
					hasMovedTwoSpaces = true;
				}
				selectedX = x;
				selectedY = y;
			}
		}
	}

	// in move method, we are using to update board, so maybe we can use
	// use game piece to redraw
	public void place(Piece p, int x, int y) {
		// out of bounds
		if (x > 7 || x < 0) {
			return;
		}
		if (y > 7 || y < 0) {
			return;
		}
		gamePiece[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Out of bounds");
			return null;
		}
		Piece n = pieceAt(x, y);
		if (n == null) {
			System.out.println("No piece here");
			return null;
		}
		gamePiece[x][y] = null;
		return n;
	}

	public boolean canEndTurn() {
		Piece n = pieceAt(selectedX, selectedY);
		if (n != null && n.hasCaptured()) {
			return true;
		}
		if (hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		String s;
		Piece p = pieceAt(selectedX, selectedY);
		if (p != null) {
			p.doneCapturing();
		}
		resetSelected();
		if (player == FIRE) {
			player = WATER;
			s = "water";
		} else {
			player = FIRE;
			s = "fire";
		}

		System.out.println("now player is " + s);

	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = gamePiece[i][j];
				if (p != null) {
					if (p.isFire()) {
						fireCount++;
					} else {
						waterCount++;
					}
				}
			}
		}
		if (fireCount > waterCount && waterCount == 0) {
			return "Fire";
		} else if (fireCount < waterCount && fireCount == 0) {
			return "Water";
		} else if (fireCount == waterCount && fireCount == 0) {
			return "No one";
		}
		else {
			return null;
		}
	}

	private boolean checkWinner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = gamePiece[i][j];
				if (p != null) {
					if (p.isFire()) {
						fireCount++;
					} else {
						waterCount++;
					}
				}
			}
		}
		if (fireCount == 0 || waterCount == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	private void resetSelected() {
		selectedX = -1;
		selectedY = -1;
		hasMoved = false;
		hasMovedOneSpace = false;
		hasMovedTwoSpaces = false;
	}

	public static void main(String[] args) {
		boolean shouldBeEmpty = true;
		/**
		 * Monitors for mouse presses. Wherever the mouse is pressed, a new
		 * piece appears.
		 */

		if (args.length == 0 || args[0].equals("false")) {
			shouldBeEmpty = false;
		}
		Board board = new Board(shouldBeEmpty);
		board.play();
	}

}