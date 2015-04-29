import java.lang.Math;

public class Board {

	private Piece[][] pieces;
	private boolean shouldBeEmpty = false;
	private int turn = 1; // 1 for fire, -1 for water
	private int lastSelectedX;
	private int lastSelectedY;
	private Piece lastSelected; // last selected piece!
	private boolean didMove = false;
	private boolean init = true;
	private String imager = "img/";
	private boolean alreadySelected = false;
	private int N = 8;

	public static void main(String[] args) {
		Board game = new Board(false);
		// pieces = new Piece[N][game.N];
		StdDrawPlus.setXscale(0, game.N);
		StdDrawPlus.setYscale(0, game.N);
		while (true) {
			game.drawBoard(game.N);

			if (StdDrawPlus.mousePressed()) {
				System.out.println("Mouse pressed");
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if ((x > 7) || (y > 7)) {
				}  if (game.canSelect(x, y)) {
					game.select(x, y);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					System.out.println("Running through a mousePressed loop.");
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					game.drawPiece(x, y);
					
				}
			}

			if (StdDrawPlus.isSpacePressed()) {
				if (game.canEndTurn()) {
					game.endTurn();
					System.out.println("Turn ended.");
				}
				if (game.winner() != null) {
					System.out.println(game.winner());
					break;
				}
			}

			game.drawBoard(game.N);
			StdDrawPlus.show(100);
		}
	}

	private void drawPiece(int x, int y) {
		Piece current = pieces[x][y];
		if (current == null) {
		}
		/**
		 * begin image selecting suite
		 */
		else {
			if (current.isBomb()) {
				if (current.isFire()) {
					imager += "bomb-fire";
				} else {
					imager += "bomb-water";
				}
			} else if (current.isShield()) {
				if (current.isFire()) {
					imager += "shield-fire";
				} else {
					imager += "shield-water";
				}
			} else if (!current.isShield() && (!current.isBomb())) {
				if (current.isFire()) {
					imager += "pawn-fire";
				} else {
					imager += "pawn-water";
				}
			}
			if (current.isKing()) {
				imager += "-crowned.png";
			} else {
				imager += ".png";
			}

		}

		/**
		 * end image selecting suite
		 * 
		 */
		// drawBoard(N);
		// imager += ".png";
		StdDrawPlus.picture(x + 0.5, y + 0.5, imager, 1, 1);
		imager = "img/";
	}

	private void drawBoard(int N) { // calls drawpiece
		for (int i = 0; i < N; i++) { // init the board
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0)
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				if ((alreadySelected == true) && (i == lastSelectedX)
						&& (j == lastSelectedY)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				drawPiece(i, j);
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		if (shouldBeEmpty == true) {

		} else if (init == true) { // initialize the board
			init = false;
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N; row++) {
					if (col % 2 == 0) {
						if (row == 0) {
							Piece piece = new Piece(true, this, col, row,
									"pawn");
							pieces[col][row] = piece;
						} else if (row == 2) {
							Piece piece = new Piece(true, this, col, row,
									"bomb");
							pieces[col][row] = piece;
						} else if (row == 6) {
							Piece piece = new Piece(false, this, col, row,
									"shield");
							pieces[col][row] = piece;
						}
					} else {
						if (row == 1) {
							Piece piece = new Piece(true, this, col, row,
									"shield");
							pieces[col][row] = piece;
						} else if (row == 5) {

							Piece piece = new Piece(false, this, col, row,
									"bomb");
							pieces[col][row] = piece;
						} else if (row == 7) {
							Piece piece = new Piece(false, this, col, row,
									"pawn");
							pieces[col][row] = piece;

						}
					}
				}
			}
		}
	}

	/*
	 * void place(Piece p, int x, int y) - Places p at (x, y). If (x, y) is out
	 * of bounds, does nothing. If another piece already exists at (x, y), p
	 * will replace that piece. (This method is potentially useful for creating
	 * specific test circumstances.) This will supplement our Piece.move()
	 * method.
	 */
	public void place(Piece p, int x, int y) {
		if ((x > N) || y > N) {
		}

		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++) {
				if (p == pieceAt(col, row)) {
					remove(col, row);
				}
			}
		}
		pieces[x][y] = p;

	}

	public boolean canSelect(int x, int y) {
		if (didMove == true) {
			if (validMove(lastSelectedX, lastSelectedY, x, y)) {
				return true;
			}
			return false;
		}

		else if ((pieces[x][y] != null) && (alreadySelected) && (!didMove)) {
			return true;
		}

		else if (pieces[x][y] != null && pieces[x][y].side() == turn) {
			if (alreadySelected == false) {
				return true;
			}
			if ((alreadySelected == true) && (didMove = false)) {
				if (pieces[x][y].side() == turn) {
					return true;
				}
			}
		}

		else if ((pieces[x][y] == null) && (alreadySelected) && (!didMove)) {
			if (validMove(lastSelectedX, lastSelectedY, x, y)) {
				return true;
			}
		}

		else if ((alreadySelected == true)
				&& (pieceAt(x, y).hasCaptured() == true)
				&& (Math.abs(lastSelectedY - y) == 2)
				&& (validMove(lastSelectedX, lastSelectedY, x, y))) {

			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// optional method, helps canSelect()
		// Check if fire, check if king
		Piece current = pieceAt(xi, yi);
		Piece next = pieceAt(xf, yf);
		int yTemp = (yf + yi) / 2;
		int xTemp = (xf + xi) / 2;
		Piece capture = pieceAt(xTemp, yTemp);
		if ((xi == xf) || (yi == yf)) {
			return false;
		}
		if (next == null) // check for just 1 square movement and also 2 square
							// movement (capture)
		{
			/**
			 * 2 Square movement
			 */
			if (didMove) {
				if (captureTwo(xi, yi, xf, yf)
						&& (pieceAt(xi, yi).hasCaptured())) {
					return true;
				}
				return false;
			}

			/**
			 * 1 SQUARE MOVEMENT
			 */
			if (current.isKing()) { // 1 SQUARE MOVEMENT, KING
				// Check if jumping, piece in between is not yours.
				if (Math.abs(yf - yi) == 1) {
					if (Math.abs(xf - xi) == 1) {
						return true;
					}
				} else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)
						&& (capture != null)) // 2 PIECE MOVEMENT, CAPTURE,KING
				{
					if (turn != capture.side()) {
						return true;
					}
					return false;
				}
				return false;
			}

			else if (current.isFire()) // 1 SQUARE MOVEMENT, FIRE, NOT KING
			{
				if (yf - yi == 1) { // 1 SQUARE MOVEMENT
					if (Math.abs(xf - xi) == 1) {
						return true;
					}
				} else if (Math.abs(yf - yi) == 2) { // CAPTURE, JUMP 2 X SPACES
					if ((yf - yi == 2) && (capture != null)) {
						if (turn != capture.side()) {
							return true;
						}
					}
				}
				return false;
			} else if (!current.isFire()) { // TYPE WATER
				if (yi - yf == 1) { // 1 Square movement
					if (Math.abs(xf - xi) == 1) {
						return true;
					}
				} else if (Math.abs(xf - xi) == 2) {
					if ((yi - yf == 2) && (capture != null)) {
						if (turn != capture.side()) {
							return true;
						}
					}
				}
				return false;
			}
		}

		return false;
	}

	private boolean captureTwo(int xi, int yi, int xf, int yf) {
		int xMid = (xi + xf) / 2;
		int yMid = (yi + yf) / 2;
		if ((Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2)) {
			if ((pieceAt(xi,yi) == null) || (pieceAt(xMid,yMid) == null)){
				return false;
			}
			if (pieceAt(xi, yi).side() != pieceAt(xMid, yMid).side()) {
				return true;
			}
		}
		return false;
	}

	public Piece remove(int x, int y) {
		Piece temp;
		if ((pieces[x][y] == null) || (x > N - 1) || (y > N - 1) || (x < 0)
				|| (y < 0)) {
			System.out.println("Invalid remove");
			return null;
		}
		temp = pieces[x][y];
		pieces[x][y] = null;
		didMove = true;
		return temp;
	}

	public boolean canEndTurn() {
		// System.out.println("Current side is " + turn + " "
		// + didMove);
		// System.out.println("x and y are " + lastSelectedX + " " +
		// lastSelectedY);
		// System.out.println("Can end?" + );
		return didMove;
	}

	public void select(int x, int y) {
		System.out.println("Select===========================");
		if (this.shouldBeEmpty){
			if (alreadySelected == false){
				alreadySelected = true; // first time setting this to true
				lastSelected = pieces[x][y];
				lastSelectedX = x;
				lastSelectedY = y;
			}
			else {
				pieces[lastSelectedX][lastSelectedY] = null;
				pieces[x][y] = lastSelected;

				lastSelected.move(x, y);
				// lastSelected.doneCapturing();
				lastSelectedX = x;
				lastSelectedY = y;
				didMove = true;
			}
			
		}
		// check if it's in bounds
		if ((pieces[x][y] == null) && (alreadySelected == true)){
			
//				&& (validMove(lastSelectedX, lastSelectedY, x,y))) { // new
			// square
			pieces[lastSelectedX][lastSelectedY] = null;
			pieces[x][y] = lastSelected;

			lastSelected.move(x, y);
			// lastSelected.doneCapturing();
			lastSelectedX = x;
			lastSelectedY = y;
			didMove = true;
		} else if ((pieces[x][y] != null) && (alreadySelected == true)
				&& (!didMove)) {
			lastSelected = pieces[x][y];
			lastSelectedX = x;
			lastSelectedY = y;
			System.out.println("Selecting another piece on your team.");

		}

		else if ((pieces[x][y] != null) && (alreadySelected == false)) {
			alreadySelected = true; // first time setting this to true
			lastSelected = pieces[x][y];
			lastSelectedX = x;
			lastSelectedY = y;
		}
	}

	public void endTurn() {

		if (turn == 1) {
			turn = 0;
		} else {
			turn = 1;
		}
		lastSelected.doneCapturing();
		didMove = false;
		System.out.println("didMove at end is " + didMove);
		alreadySelected = false;
		lastSelected = null;
		lastSelectedX = 0;
		lastSelectedY = 0;
		winner();

	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int col = 0; col < N; col++) {
			for (int row = 0; row < N; row++) {
				if (pieceAt(col, row) == null) {

				} else if (!pieceAt(col, row).isFire()) {
					water++;
				} else if (pieceAt(col, row).isFire()) {
					fire++;
				}
			}
		}

		System.out.println("water is " + water);
		System.out.println("fire is " + fire);
		if ((fire == 0) && (water == 0)) {
			return "No one";
		} else if (fire == 0) {
			return "Water";
		} else if (water == 0) {
			return "Fire";
		} else {
			return null;
		}

	}

	public Piece pieceAt(int x, int y) {
		if ((x >= 8) || (y >= 8)) {
			return null;
		}

		if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];

	}

}