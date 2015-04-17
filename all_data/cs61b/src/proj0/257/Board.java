public class Board {

	private Piece[][] pieces;
	private int size;
	private boolean emptyBoard;
	private boolean isFireTurn;
	private int firePieces;
	private int waterPieces;
	private boolean movedPiece;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;

	/** Constructor for Board class. If shouldBeEmpty is true, initializes an
	* empty board. Otherwise, initializes a Board with the default config for
	* a game of checkers. 
	**/
	public Board(boolean shouldBeEmpty){
		emptyBoard = shouldBeEmpty;
		isFireTurn = true;
		movedPiece = false;
		selectedPiece = null;
		size = 8;
		waterPieces = 0;
		firePieces = 0;
		pieces = new Piece[size][size];
		if (!emptyBoard) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++ ) {
					if ((j < 3 || j > 4) && ((i+j) % 2 == 0)) {
						place(createPiece(i, j), i, j);
						}
					}
				}
			}
		}

	/**Draws this Board. */
	private void drawBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((i+j) % 2 == 0) { 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {				
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null) {
					if (pieces[i][j] == selectedPiece) {
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
					drawPiece(pieceAt(i, j), i, j);
					}
			}
		}
	}

	/**Creates the piece for a particular row. */
	private Piece createPiece(int i, int j) {
		if (j == 0) {
			return new Piece(true, this, i, j, "pawn");
		}
		if (j == 1) {
			return new Piece(true, this, i, j, "shield");
		}
		if (j == 2) {
			return new Piece(true, this, i, j, "bomb");
		}
		if (j == 5) {
			return new Piece(false, this, i, j, "bomb");
		}
		if (j == 6) {
			return new Piece(false, this, i, j, "shield");
		}
		return new Piece(false, this, i, j, "pawn");
	}

	/**Draws Piece p onto the Board. */
	private void drawPiece(Piece p, int x, int y) {
		StdDrawPlus.picture(x + .5, y + .5, getPieceImage(p), 1, 1);
	}

	/**Returns the String associated with a Piece's image. */
	private String getPieceImage(Piece P) {
		if (P.isFire()){
			if (P.isShield()) {
				if(P.isKing()){
					return "img/shield-fire-crowned.png";
				}
				return "img/shield-fire.png";
			}
			if (P.isBomb()) {
				if (P.isKing()) {
					return "img/bomb-fire-crowned.png";
				}
				return "img/bomb-fire.png";
			}
			if (P.isKing()) {
				return "img/pawn-fire-crowned.png";
			}
			return "img/pawn-fire.png";
		}
		if (P.isShield()) {
			if (P.isKing()) {
				return "img/shield-water-crowned.png";
			}
 			return "img/shield-water.png";
		}
		if (P.isBomb()) {
			if (P.isKing()) {
				return "img/bomb-water-crowned.png";
			}
			return "img/bomb-water.png";
		}
		if (P.isKing()) {
			return "img/pawn-water-crowned.png";
		}
		return "img/pawn-water.png";
	}

	/** Checks to see if (x, y) is out of bounds. */
	private boolean isOutOfBounds(int x, int y){
		return (x > 7 || y > 7 || x < 0 || y < 0);
	}

	/** Gets piece at position (x,y) on board. Returns null if out of bounds
	or no piece. */
	public Piece pieceAt(int x, int y) {
		if (isOutOfBounds(x, y) || pieces[x][y] == null){
			return null;
		}
		return pieces[x][y];
	}

	/** Checks to see if a Piece's side matches the current turn. */
	private boolean sideMatchesTurn(Piece p) {
		if (isFireTurn) {
			return p.isFire();
		}
		return !p.isFire();
	}

	/** Checks to see if a piece has been selected this turn. */
	private boolean hasSelectedPiece() {
		return (selectedPiece != null);
	}

	/** Checks to see if a piece has been moved this turn. */
	private boolean hasMoved() {
		return movedPiece;
	}

	/** Returns true if there is a Piece at (x,y) that can be selected. */
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x,y);
		if (p != null && sideMatchesTurn(p)) {
			return (!hasSelectedPiece() || (hasSelectedPiece() && !hasMoved()));
		}
		return ( (hasSelectedPiece() && !hasMoved() && validMove(selectedX, selectedY, x, y)) ||
				 (hasSelectedPiece() && selectedPiece.hasCaptured() && canCapture(selectedPiece, selectedX, selectedY, x, y))
				 );
	}

	/** Returns true if Piece at (xi, yi) can either move or capture to (xf, yf). */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece pf = pieceAt(xf, yf);
		if (isOutOfBounds(xi, yi) || isOutOfBounds(xf, yf) || p == null || pf != null) {
			return false;
		}
		return (canCapture(p, xi, yi, xf, yf) || canMove(p, xi, yi, xf, yf));
	}

	/**Checks to see if Piece a and Piece b are on the same side. */
	private boolean sameSide(Piece a, Piece b) {
		if (a.isFire()) {
			return b.isFire();
		}
		return !b.isFire();
	}

	/** Checks to see if Piece at (xi, yi) can capture to (xf, yf). */
	private boolean canCapture(Piece p, int xi, int yi, int xf, int yf){

		Piece capturedPiece = pieceAt(Math.min(xf, xi) +1, Math.min(yf,yi) +1);
		if (canMove(p, xi, yi, xf, yf) || Math.abs(xf - xi) != 2 || 
			capturedPiece == null || sameSide(p, capturedPiece)) {
			return false;
		}
		if (p.isKing()) {
			return (Math.abs(yf - yi) == 2);
		}
		if (p.isFire()) {
			return ((yf - yi) == 2);
		}
		return ((yf - yi) == -2);

	}

	/** Checks to see if Piece at (xi, yi) can move to (xf, yf). */
	private boolean canMove(Piece p, int xi, int yi, int xf, int yf) {

		if (Math.abs(xf - xi) > 1 || xf == xi) {
			return false;
		}
		if (p.isKing()){
			if (Math.abs(yf - yi) != 1) {
				return false;
			}
			return true;
		}
		if (p.isFire()) {
			if (yf <= yi  || yf - yi > 1) {
				return false;
			}
			return true;
		}
		if (yf >= yi || yi - yf > 1) {
			return false;
		}
		return true;
	}

	/**Selects piece at (x,y) if possible. */
	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
		}
		else {
			remove(selectedX, selectedY);
			selectedPiece.move(x, y);
			movedPiece = true;
		}
		selectedX = x;
		selectedY = y;
	}

	/** Places Piece p at (x, y). If (x,y) is out of bounds or p is null, 
	* does nothing. */
	public void place(Piece p, int x, int y) {
		if (!isOutOfBounds(x,y) && p != null) {
			if (p.isFire())
				firePieces += 1;
			else
				waterPieces += 1;
			pieces[x][y] = p;
		}

	}

	/** Removes and returns the Piece at (x,y) if it exists and is in bounds. */
	public Piece remove(int x, int y) {
		if (isOutOfBounds(x, y)) {
			System.out.println("Error: position out of bounds.");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("Error: no piece at given position.");
			return null;
		}
		Piece removed = pieces[x][y];
		if (removed.isFire())
			firePieces -= 1;
		else
			waterPieces -= 1;
		pieces[x][y] = null;
		return removed;
	}

	/** Returns whether or not the current player can end the turn. */
	public boolean canEndTurn() {
		if (movedPiece) {
			return true;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (pieces[i][j] != null && pieces[i][j].hasCaptured()) {
					return true;
				}
			}
		}
		return false;
	}

	/** Ends the current player's turn. */
	public void endTurn() {
		isFireTurn = !isFireTurn;
		movedPiece = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		winner();
	}

	/**
	* Returns the winner of the game: "Fire", if no fire pieces left,
	* "Water" if no water pieces left, or "No one" if no pieces on board.
	* Returns null if game is not yet over.
	**/
	public String winner() {
		if (firePieces == 0 && waterPieces == 0) {
			return "No one";
		}
		if (firePieces == 0){
			return "Water";
		}
		if (waterPieces == 0){
			return "Fire";
		}
		return null;
	}

	/** Starts a GUI supported version of checkers. */
	public static void main (String args[]) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board gameBoard = new Board(false);

		while(true) {
			gameBoard.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (gameBoard.canSelect(x, y)) {
					gameBoard.select(x,y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (gameBoard.canEndTurn()) {
					gameBoard.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
	}
}