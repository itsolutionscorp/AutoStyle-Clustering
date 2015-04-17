public class Board {
	// Ignore this comment
	private static int maxX = 7;
	private static int maxY = 7;
	private static int minX = 0;
	private static int minY = 0;
	private Piece[][] pieces;
	private Piece hasSelected = null;
	private boolean hasMoved = false;
	private int turn = 0;
	private int xSelected;
	private int ySelected;

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		Piece p;
		if (!shouldBeEmpty) {
			for(int i = 0; i < 8; i += 2) {
				p = new Piece(true, this, i, 0, "pawn");
				place(p, i, 0);
			}
			for(int i = 1; i < 8; i += 2) {
				p = new Piece(true, this, i, 1, "shield");
				place(p, i, 1);
			}
			for(int i = 0; i < 8; i += 2) {
				p = new Piece(true, this, i, 2, "bomb");
				place(p, i, 2);
			}
			for(int i = 1; i < 8; i += 2) {
				p = new Piece(false, this, i, 5, "bomb");
				place(p, i, 5);
			}
			for(int i = 0; i < 8; i += 2) {
				p = new Piece(false, this, i, 6, "shield");
				place(p, i, 6);
			}
			for(int i = 1; i < 8; i += 2) {
				p = new Piece(false, this, i, 7, "pawn");
				place(p, i, 7);
			}
		}
	}

	/** Returns the piece at the given coordinates (X, Y) or null if 
	  * there is no piece or the coordinates are out of bounds */
	public Piece pieceAt(int x, int y) {
		if (x > maxX || x < minX || y > maxY || y < minY) {
			return null;
		}
		return pieces[x][y];
	}

	/** Returns true if the piece at (X, Y) can be selected, false otherwise */
	public boolean canSelect(int x, int y) {
		if (x > maxX || x < minX || y > maxY || y < minY) {
			return false;
		}
		Piece p = pieceAt(x, y);
		if (hasSelected == null) {
			if (p != null && p.side() == turn) {
				return true;
			}
			else {
				return false;
			}
		}
		if (p == null) {
			if ((!hasMoved || hasSelected.hasCaptured()) && validMove(xSelected , ySelected, x, y)) {
				return true;
			}
			else {
				return false;
			}
		}
		if (!hasMoved && p.side() == turn) {
			return true;
		}
		else {
			return false;
		}
	}

		

	/** Returns true if the piece at (Xi, Yi) can move to (Xf, Yf),
	  * false otherwise */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf > maxX || xf < minX || yf > maxY || yf < minY) {
			return false;
		}
		if (pieceAt(xi, yi) == null) {
			return false;
		}
		int moveBuffer = 1;
		if (!pieceAt(xi, yi).isFire()) {
			moveBuffer = -1;
		}
		if (xi + moveBuffer == xf && yi + moveBuffer == yf && pieceAt(xf, yf) == null 
			&& !pieceAt(xi, yi).hasCaptured()) {
			return true;
		}

		if (xi - moveBuffer == xf && yi + moveBuffer == yf && pieceAt(xf, yf) == null
			&& !pieceAt(xi, yi).hasCaptured()) {
			return true;
		}

		if (validCapture(xi, yi, xf, yf)) {
			return true;
		}

		if (pieceAt(xi, yi).isKing()) {
			if (validKingMove(xi, yi, xf, yf)) {
				return true;
			}
		}
		return false;
	}

	/** The same as validMove, but accounts for the reverse movement
	  * of kings */
	private boolean validKingMove(int xi, int yi, int xf, int yf) {
		int moveBuffer = 1;
		if (!pieceAt(xi, yi).isFire()) {
			moveBuffer = -1;
		}
		if (xi + moveBuffer == xf && yi - moveBuffer == yf && pieceAt(xf, yf) == null
			&& !pieceAt(xi, yi).hasCaptured()) {
			return true;
		}

		if (xi - moveBuffer == xf && yi - moveBuffer == yf && pieceAt(xf, yf) == null
			&& !pieceAt(xi, yi).hasCaptured()) {
			return true;
		}

		if (validKingCapture(xi, yi, xf, yf)) {
			return true;
		}
		return false;
	}

	/** Returns true if the piece at (Xi, Yi) can capture a piece by moving
	  * to (Xf, Yf), false otherwise */
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		int jumpBuffer = 2;
		if (!pieceAt(xi, yi).isFire()) {
			jumpBuffer = -2;
		}
		if (xi + jumpBuffer == xf && yi + jumpBuffer == yf && pieceAt(xf, yf) == null 
			&& pieceAt(xi + (jumpBuffer / 2), yi + (jumpBuffer / 2)) != null
			&& pieceAt(xi + (jumpBuffer / 2), yi + (jumpBuffer / 2)).side() != pieceAt(xi, yi).side()) {
			return true;
		}

		if (xi - jumpBuffer == xf && yi + jumpBuffer == yf && pieceAt(xf, yf) == null 
			&& pieceAt(xi - (jumpBuffer / 2), yi + (jumpBuffer / 2)) != null
			&& pieceAt(xi - (jumpBuffer / 2), yi + (jumpBuffer / 2)).side() != pieceAt(xi, yi).side()) {
			return true;
		}
		return false;
	}


	/** The same as validCapture, but accounts for backward movement
	  * of kings */
	private boolean validKingCapture(int xi, int yi, int xf, int yf) {
		int jumpBuffer = 2;
		if (!pieceAt(xi, yi).isFire()) {
			jumpBuffer = -2;
		}
		if (xi + jumpBuffer == xf && yi - jumpBuffer == yf && pieceAt(xf, yf) == null 
			&& pieceAt(xi + (jumpBuffer / 2), yi - (jumpBuffer / 2)) != null
			&& pieceAt(xi + (jumpBuffer / 2), yi - (jumpBuffer / 2)).side() != pieceAt(xi, yi).side()) {
			return true;
		}

		if (xi - jumpBuffer == xf && yi - jumpBuffer == yf && pieceAt(xf, yf) == null 
			&& pieceAt(xi - (jumpBuffer / 2), yi - (jumpBuffer / 2)) != null
			&& pieceAt(xi - (jumpBuffer / 2), yi - (jumpBuffer / 2)).side() != pieceAt(xi, yi).side()) {
			return true;
		}
		return false;
	}

	/** Selects the piece or tile at (X, Y) if it is allowed */
	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			hasSelected.move(x, y);
			hasMoved = true;
		}
		xSelected = x;
		ySelected = y;
		hasSelected = pieceAt(x, y);

	}

	/** Places piece P on the board at (X, Y) */
	public void place(Piece p, int x, int y) {
		pieces[x][y] = p;
	}


	/** If (X, Y) are valid coordinates pointing to a piece,
	  * the piece at (X, Y) is removed from the board and returned */
	public Piece remove(int x, int y) {
		if (x > maxX || x < minX || y > maxY || y < minY) {
			System.out.println("Given coordinates out of bounds");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece at given coordinates");
			return null;	
		}
		Piece piece = pieceAt(x, y);
		pieces[x][y] = null;
		return piece;
	}

	/** Returns true if the current player can end their turn,
	  * false otherwise */
	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	/** Ends the current player's turn swithces to the other player */
	public void endTurn() {
		turn = 1 - turn;
		hasMoved = false;
		if (hasSelected != null) {
			hasSelected.doneCapturing();
			hasSelected = null;
		}
	}

	/** Returns "Fire" if there are more fire pieces on the board, 
	  * "Water" if there are more water pieces, "No one" if there 
	  * are no pieces on the board or an equal number of fire and 
	  * water pieces */
	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						fireCount += 1;
					}
					else {
						waterCount += 1;
					}
				}
			}
		}
		if (fireCount != 0 && waterCount == 0) {
			return "Fire";
		}
		else if (waterCount != 0 && fireCount == 0) {
			return "Water";
		}
		else if (waterCount == 0 && fireCount == 0) {
			return "No one";
		}
		else {
			return null;
		}
	}

	/** Draws the checker board and pieces */
	private static void drawBoard(Board b) {
		for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (b.hasSelected != null && b.xSelected == i && b.ySelected == j) {
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            }
	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece piece = b.pieces[i][j];
                if (piece != null) {
                	String king = "";
                   	String team = "-water";
                   	String pieceName = "pawn";
                   	if (piece.isBomb()) {
                   		pieceName = "bomb";
                   	}
                   	if (piece.isShield()) {
                   		pieceName = "shield";
                   	}
                	if (piece.isKing()) {
                		king = "-crowned";
                	}
                	if (piece.isFire()) {
                		team = "-fire";
                	}
                	StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceName + team + king + ".png", 1, 1);
                	
                }
            }
        }
	}

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        while(true) {
        	drawBoard(b);
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xInt = (int) x;
                int yInt = (int) y;
                if (b.canSelect(xInt, yInt)) {
                	b.select(xInt, yInt);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(xInt + .5, yInt + .5, .5);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            if (b.winner() != null) {
            	System.exit(0);
            }
        	StdDrawPlus.show(100);
        }
	}
}
