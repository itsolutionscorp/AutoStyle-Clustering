/**
* REMINDER:
* 	make validMove a private method when submitting
*	make validMove a public method when testing
*/

/**
* Requred methods to implement:
*	canSelect(), select(), canEndTurn(), winner()
*/

public class Board {

	private boolean turnFire = true;

	// if selection hasn't occured, sel = -1
	private int xSel = -1;
	private int ySel = -1;

	private boolean piecesMoved = false;
	
	private static int n = 8;
	private Piece[][] pieces = new Piece[n][n];

	public static void main (String[] args) {
		
		StdDrawPlus.setXscale(0,n);
		StdDrawPlus.setYscale(0,n);
		Board b = new Board(false);

		while (true) {
			b.inputs();
			b.drawBoard();
			StdDrawPlus.show(25);
		}
	}

	/**
	 * draws an n x n board 
	 * adapted from StdDrawDemo.java
	 *
	 **/
	private void drawBoard() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// color cells
				if (i == xSel && j == ySel) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				// draw pieces
				if (pieces[i][j] != null) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, pieceImage(pieces[i][j]), 1, 1);
				}
			}
		}
	}

	private void initialPieces() {

		/* fire pawns */
		pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

		/* fire shields */
		pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		pieces[7][1] = new Piece(true, this, 7, 1, "shield");

		/* fire bombs */
		pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

		/* water bombs */
		pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

		/* water shields */
		pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");

		/* water pawns */
		pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
	}

	private String pieceImage(Piece pieces) {

		String type;
		String side;
		String king;

		if (pieces.isBomb() == true) {
			type = "bomb";
		} else if (pieces.isShield() == true) {
			type = "shield";
		} else {
			type = "pawn";
		}

		if (pieces.isFire() == true) {
			side = "-fire";
		} else {
			side = "-water";
		}

		if (pieces.isKing() == true) {
			king = "-crowned";
		} else {
			king = "";
		}

		return "img/" + type + side + king + ".png";
	}

	private void inputs() {

		if (StdDrawPlus.isSpacePressed()) {
			if (canEndTurn()) {
				endTurn();
			}
		} else if (StdDrawPlus.mousePressed()) {
			int x = (int)StdDrawPlus.mouseX();
			int y = (int)StdDrawPlus.mouseY();
			if (canSelect(x,y)) {
				select(x,y);
			}
		}
	}

	// defines class board
	public Board(boolean shouldBeEmpty) {
		// false = make board w/ initial positions

		if (!shouldBeEmpty){
			initialPieces();
		}
	}

	// board class methods

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		} else {
			return pieces[x][y]; // pieces[x][y] already equals null if no piece exists
		}
	}

	public boolean canSelect(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		}

		Piece p = pieceAt(x, y);
		if (p != null) { // selected a piece
			if (p.isFire() == turnFire) { // selected color same as current turn color
				if (xSel != -1 && ySel != -1) { // selection has been made
					if (piecesMoved) { // pieces has been moved
						return false;
					} else {
						return true;
					}
				} else { // selection has not been made
					return true;
				}
			} else {
				return false;
			}
		} else { // selected empty square
			if (xSel != -1 && ySel != -1) { // selected a piece
				if (!piecesMoved) { // hasn't moved, empty spot
					if (validMove(xSel, ySel, x, y)) { // valid spot
						return true;
					} else {
						return false; // invalid spot
					}
				} else { // has moved
					Piece q = pieceAt(xSel, ySel);
					if (q != null && q.hasCaptured() && validMove(xSel, ySel, x, y)) {
					// captured, and selected another capture position
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
	}
	

	// only called after selected piece
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi];

		// out of bounds
		if (xi < 0 || xi > 7 ||	yi < 0 || yi > 7 ||	xf < 0 || xf > 7 || yf < 0 || yf > 7) {
				return false;
		}

		// no piece at [xi][yi], red location, occupied final location
		if (p == null || (xf + yf) % 2 != 0 || pieces[xf][yf] != null) {
			return false;
		}

		// selected wrong color
		if (p.isFire() != turnFire) {
			return false;
		}

		// moving backwards for non king
		if (!p.isKing()) {

			// if fire and moving down
			if (p.side() == 0 && yf < yi){
				return false;
			}

			// if water and moving up
			if (p.side() == 1 && yf > yi) {
				return false;
			}
		}

		// not simple move and not capturing move
		if (Math.abs(xf-xi) > 2 || Math.abs(xf-xi) < 1 || Math.abs(yf-yi) > 2 || Math.abs(yf-yi) < 1) {
			return false;
		}

		if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2){ // if its a capturing move
			int xc = (xi + xf) >>> 1; // capture x position
			int yc = (yi + yf) >>> 1; // capture y position
			Piece q = pieces[xc][yc]; // piece in captured position
			if (q == null) { // no piece in captured position
				return false;
			} else if (p.side() == q.side()) { // piece in captured position is of same color
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x,y);

		if (p == null) { // no piece at selected location, already selected a piece
			Piece pSel = pieceAt(xSel, ySel);
			pSel.move(x, y);
			piecesMoved = true;
		}
		xSel = x;
		ySel = y;
	}

	public void place(Piece p, int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return;
		} else if (p == null) {
			return;
		} else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Location out of bounds.");
			return null;
		} else {
			Piece p = pieceAt(x,y);
			if (p == null) { 
				System.out.println("There is no piece at this position");
				return null;
			} else {
				pieces[x][y] = null;
				return p;
			}
		}	
	}

	public boolean canEndTurn() {
		return piecesMoved;
	}

	public void endTurn () {
		Piece p = pieceAt(xSel, ySel);
		if (p != null) {
			p.doneCapturing();
		}
		xSel = -1;
		ySel = -1;
		turnFire = !turnFire;
		piecesMoved = false;	
	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;
		Piece p;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						numFire = numFire + 1;
					} else {
						numWater = numWater + 1;
					}
				}
			}
		}

		if (numFire != 0 && numWater == 0) {
			return "Fire";
		} else if (numFire == 0 && numWater != 0) {
			return "Water";
		} else if (numFire == 0 && numWater == 0) {
			return "No one";
		} else {
			return null;
		}
	}
}