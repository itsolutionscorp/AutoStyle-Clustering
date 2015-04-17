public class Board {

	// Need to make this array public for some tests.
	private Piece[][] pieces;
	private boolean fireTurn = true; //It is fire's turn when game begins. 
	private boolean hasMoved = false; //Player has not moved anything at start of turn. In canSelect method.
	private String filename;
	private boolean hasSelectedPiece = false; //Player has not selected a piece at start of turn. In canSelect method.
	private Piece chosen; //Used in canSelect and select to hold coordinates of the piece chosen to move.
	private Integer[] selectedSquare = new Integer[2];
	private int totalFire = 0;
	private int totalWater = 0;

	public static void main(String[] args) {
		Board currentGame = new Board(false);
		while (currentGame.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (currentGame.canSelect((int) x,(int) y)) {
                	currentGame.select((int) x, (int) y);
           
                	//if (currentGame.pieceAt(x,y).hasCaptured()) {
                		//currentGame.remove()
                	//}
					//StdDrawPlus.filledSquare(Math.floor(x) + .5, Math.floor(y) + .5, .5);
					//currentGame.drawPieces();
					}
                }
                StdDrawPlus.show(10);
                if (StdDrawPlus.isSpacePressed() && currentGame.canEndTurn()) {
					currentGame.endTurn();
				}
            currentGame.drawBoard();
            currentGame.drawPieces();
        }
        System.out.println(currentGame.winner());
	}
	

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			addPieces();
		}
		
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);
		for (int i = 0; i < 8.0; i++) {
			for (int j = 0; j < 8.0; j++) {
				
				if (((i + j) % 2.0) == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					//StdDrawPlus.filledSquare(i + .5, j + .5, 0.5);
					}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					//StdDrawPlus.filledSquare(i + .5, j + .5, 0.5);
				}
				if ((selectedSquare[0] != null) && selectedSquare[0].equals(i) && selectedSquare[1].equals(j)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}

				StdDrawPlus.filledSquare(i + .5, j + .5, 0.5);
			}
		}
	}

	/* Checks if position (x,y) is in the range of the board.
	 * If not, prints out an error message.
	 */

	private boolean inBounds(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			System.out.println("Error: index out of bounds.");
			return false;
		}
		return true;
	}

	/* Checks if there is a piece at position (x,y).
	 * If there is not, prints error message.
	 */

	private boolean isPiece(int x, int y) {
		if (pieces[x][y] == null) {
			System.out.println("Error: piece is null.");
			return false;
		}
		return true;
	}

	/* Removes the piece at (x,y) in the pieces array and returns it.
	 * If there is no piece there, it returns null */

	public Piece remove(int x, int y) {
		if (inBounds(x, y) && isPiece(x, y)) {
			Piece p = pieceAt(x,y);
			pieces[x][y] = null;
			return p;
		}
		else {
			return null;
		}
	} 

	// Returns the piece at (x, y), or null if there is no piece there.

	public Piece pieceAt(int x, int y) {
		if (!inBounds(x, y)) {
			return null;
		}
		return pieces[x][y];
	}

	// Puts piece p at position (x,y).

	public void place(Piece p, int x, int y) {
		if ((inBounds(x, y)) && (p != null)) {
			pieces[x][y] = p;
		}	
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		hasMoved = false;
		chosen.doneCapturing();
		chosen = null;
		selectedSquare[0] = null;
		selectedSquare[1] = null;
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (!inBounds(xf,yf)) {
			return false;
		}
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		if (chosen.isFire()) {
			if ((yf == (yi + 1)) && (!hasMoved) && !chosen.hasCaptured()) {
				if (xf == (xi + 1)) {
					return true;
				}
				if (xf == (xi - 1)) {
					return true;
				}
			}
			if ((yf == (yi + 2)) && (!hasMoved || chosen.hasCaptured())) {
				if (xf == (xi + 2)) {
					if ((pieceAt(xi + 1, yi + 1) != null) && !(pieceAt(xi + 1, yi + 1).isFire())) {
						return true;
					}
				}
				if (xf == (xi - 2)) {
					if ((pieceAt(xi - 1, yi + 1) != null) && !(pieceAt(xi - 1, yi + 1).isFire())) {
						return true;
					}
				}
			}
			if ((yf == (yi - 1)) && !hasMoved && !chosen.hasCaptured()) {
				if (chosen.isKing()) {
					if (xf == (xi + 1)) {
						return true;
				}
					if (xf == (xi - 1)) {
						return true;
					}
				}
			}
			if ((yf == (yi - 2)) && (!hasMoved || chosen.hasCaptured())) {
				if (chosen.isKing()) {
					if (xf == (xi + 2)) {
						if ((pieceAt(xi + 1, yi - 1) != null) && !(pieceAt(xi + 1, yi - 1).isFire())) {
							return true;
						}
					}
					if (xf == (xi - 2)) {
						if ((pieceAt(xi - 1, yi - 1) != null) && !(pieceAt(xi - 1, yi - 1).isFire())) {
							return true;
						}
					}
				}
			}
		}
		if (!chosen.isFire()) {
			if ((yf == (yi - 1)) && (!hasMoved)) {
				if (xf == (xi + 1)) {
					return true;
				}
				if (xf == (xi - 1)) {
					return true;
				}
			}
			if ((yf == (yi - 2)) && (!hasMoved || chosen.hasCaptured())) {
				if (xf == (xi + 2)) {
					if ((pieceAt(xi + 1, yi - 1) != null) && (pieceAt(xi + 1, yi - 1).isFire())) {
						return true;
					}
				}
				if (xf == (xi - 2)) {
					if ((pieceAt(xi - 1, yi - 1) != null) && (pieceAt(xi - 1, yi - 1).isFire())) {
						return true;
					}
				}
			}
			if ((yf == (yi + 1)) && !hasMoved) {
				if (chosen.isKing()) {
					if (xf == (xi + 1)) {
						return true;
				}
					if (xf == (xi - 1)) {
						return true;
					}
				}
			}
			if ((yf == (yi + 2)) && (!hasMoved || chosen.hasCaptured())) {
				if (chosen.isKing()) {
					if (xf == (xi + 2)) {
						if ((pieceAt(xi + 1, yi + 1) != null) && (pieceAt(xi + 1, yi + 1).isFire())) {
							return true;
						}
					}
					if (xf == (xi - 2)) {
						if ((pieceAt(xi - 1, yi + 1) != null) && (pieceAt(xi - 1, yi + 1).isFire())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire() == fireTurn) && !hasMoved) {
			return true;
		}
		else if ((chosen != null) && validMove(selectedSquare[0], selectedSquare[1] , x, y)) {
			return true;
		}
		return false;
	}

	public void select(int x, int y) {
		selectedSquare[0] = x;
		selectedSquare[1] = y;
		if (pieceAt(x, y) != null) {
			chosen = pieceAt(x,y);
		}
		else if (chosen != null && pieceAt(x, y) == null) {
			chosen.move(x,y);
			hasMoved = true;
		}
	}

	private void drawPiece(int x, int  y) {
		Piece p = pieceAt(x, y);
		if (!p.isKing()) {
			if (p.isFire()) {
				if (p.isBomb()) {
					filename = ("img/bomb-fire.png" );
				}
				else if (p.isShield()) {
					filename = ("img/shield-fire.png" );
				}
				else {
					filename = ("img/pawn-fire.png" );
				}
			}
			else {
				if (p.isBomb()) {
					filename = ("img/bomb-water.png" );
				}
				else if (p.isShield()) {
					filename = ("img/shield-water.png" );
				}
				else {
					filename = ("img/pawn-water.png" );
				}
			}
		}
		else {
			if (p.isFire()) {
				if (p.isBomb()) {
					filename = ("img/bomb-fire-crowned.png" );
				}
				else if (p.isShield()) {
					filename = ("img/shield-fire-crowned.png" );
				}
				else {
					filename = ("img/pawn-fire-crowned.png" );
				}
			}
			else {
				if (p.isBomb()) {
					filename = ("img/bomb-water-crowned.png" );
				}
				else if (p.isShield()) {
					filename = ("img/shield-water-crowned.png" );
				}
				else {
					filename = ("img/pawn-water-crowned.png" );
				}
			}
		}
		StdDrawPlus.picture(x + .5, y + .5, filename, 1, 1);
	}

	private void drawPieces() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (pieceAt(x,y) == null) {
					continue;
				}
				else {
					drawPiece(x,y);
				}			
			}
		}
	}

	private void addPieces() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((y == 0) && (((x + y) % 2) == 0)) {
					pieces[x][y] = new Piece(true, this, x, y, "pawn"); //img/pawn-fire.png
					//drawPiece(pieces[x][y]);
				}

				if ((y == 1) && (((x + y) % 2) == 0)) {
					pieces[x][y] = new Piece(true, this, x, y, "shield"); //img/shield-fire.png
					//drawPiece(pieces[x][y]);
				}

				if ((y == 2) && (((x + y) % 2) == 0)) {
					pieces[x][y] = new Piece(true, this, x, y, "bomb"); //img/bomb-fire.png
					//drawPiece(pieces[x][y], x, y);
				}			

				if ((y == 5) && (((x + y) % 2) == 0)) {
					pieces[x][y] = new Piece(false, this, x, y, "bomb"); //img/bomb-water.png
					//drawPiece(pieces[x][y], x, y);
				}

				if ((y == 6) && (((x + y) % 2) == 0)) {
					pieces[x][y] = new Piece(false, this, x, y, "shield"); //img/shield-water.png
					//drawPiece(pieces[x][y], x, y);
				}

				if ((y == 7) && (((x + y) % 2) == 0)) {
					pieces[x][y] = new Piece(false, this, x, y, "pawn"); //img/pawn-water.png
					//drawPiece(pieces[x][y], x, y);
				}				
			}
		}
	}
	public String winner() {
		totalFire = 0;
		totalWater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i,j) != null) {
					if (pieces[i][j].isFire()) {
						totalFire += 1;
					}
					if (!pieces[i][j].isFire()) {
						totalWater += 1;
					}
				}
			}
		}
		if (totalFire < 1 && totalWater < 1) {
			return ("No One");
		}
		if (totalFire < 1) {
			return ("Water");
		}
		if (totalWater < 1) {
			return ("Fire");
		}
	return null;
	}
}