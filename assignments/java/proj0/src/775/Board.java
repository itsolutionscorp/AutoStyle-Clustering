public class Board {

	private Piece[][] board;
	private int selectedX, selectedY;
	private boolean isFiresTurn;
	private boolean playerDone, pieceHasMoved;

	private static final int DIMEN = 8;

	public Board(boolean shouldBeEmpty) {

		board = new Piece[DIMEN][DIMEN];
		isFiresTurn = true;
		pieceHasMoved = false;
		playerDone = false;
		selectedX = -1;
		selectedY = -1;

		if(!shouldBeEmpty) {

			String[] rows = {"pawn","shield", "bomb"};

			// For each of the first 3 / last 3 rows
			for(int i = 0; i < rows.length; i++) {
				// For each column
				for(int j = 0; j < DIMEN; j++) {
					// Fire pieces
					if ((i + j) % 2 == 0) {
						board[j][i] = new Piece(true, this, j, i, rows[i]);
					}
					// Water pieces
					else {
						board[j][DIMEN - i - 1] = new Piece(false, this, j, DIMEN - i - 1, rows[i]);
					}
				}
			}
		}
	}

	/**
	 * Gets the piece at position (x, y) on the board, 
	 * or null if there is no piece. 
	 * If (x, y) are out of bounds, returns null.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return piece at position (x,y)
	 */
	public Piece pieceAt(int x, int y) {
		if(inBounds(x, y))
			return board[x][y];
		return null;
	}

	/**
	 * Checks if user can select the square at (x,y)
	 * @param x x-coordinate of square to check for
	 * @param y y-coordinate of square to check for
	 * @return whether square can be selected
	 */
	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x,y);

		if(inBounds(x,y) && !playerDone) {

			// The square is empty
			if(piece == null) {
				// The player has selected a Piece already and the move is valid
				return inBounds(selectedX,selectedY) && validMove(selectedX, selectedY, x, y);
			}
			// If not, return whether its the clicked-piece's turn, and hasn't moved.
			return isTurn(piece) && !pieceHasMoved;
		}

		return false;
	}
	
	/**
	 * Selects the coordinates and calls move on the last selected
	 * coordinates prior to this particular invocation of the method
	 * if a blank square is clicked.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void select(int x, int y) {

		if(!inBounds(x, y))
			return;
		
		Piece previouslySelected = pieceAt(selectedX, selectedY);

		selectedX = x;
		selectedY = y;

		// If piece already selected before, and piece selected now is null
		if(previouslySelected != null && pieceAt(selectedX, selectedY) == null) { 

			previouslySelected.move(selectedX, selectedY);
			pieceHasMoved = true;
			playerDone = !previouslySelected.hasCaptured();
		
		}

	}

	/**
	 * Places p at (x, y) and removes from previous location (if applicable) on the board.
	 * Does NOT affect the (x,y) coordinate class variables of p.
	 */
	public void place(Piece p, int x, int y) {

		if(inBounds(x,y) && p != null) {

			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[0].length; j++) {
					if(pieceAt(i,j) == p) {
						remove(i,j);
					}
				}
			}
			board[x][y] = p;
		}

	}

	/**
	 * Removes piece at (x,y) from board
	 * @return the removed piece
	 */
	public Piece remove(int x, int y) {

		if(inBounds(x,y)) {
			Piece toRemove = pieceAt(x,y);
			board[x][y] = null;
			return toRemove;
		}
		System.out.println(x + ", " +y + " out of bounds");
		return null;
	}

	/**
	 * Checks if user can end turn - a player can end their turn 
	 * if they have moved their piece
	 */
	public boolean canEndTurn() {
		return pieceHasMoved;
	}

	/**
	 * Resets everything
	 */
	public void endTurn() {
		if(pieceAt(selectedX, selectedY) != null)
			pieceAt(selectedX,selectedY).doneCapturing();
		isFiresTurn = !isFiresTurn;
		playerDone = false;
		pieceHasMoved = false;
		selectedX = -1;
		selectedY = -1;
	}

	/** 
	 * Checks for a winner at the current
	 * point in the game
	 * @return a winner if there is one yet, otherwise null
	 */
	public String winner() {

		boolean fireExists = false, waterExists = false;
		for(int i = 0; i < DIMEN; i++) {
			for(int j = 0; j < DIMEN; j++) {
				Piece piece = pieceAt(i,j);
				if(piece != null) {
					if(piece.isFire())
						fireExists = true;
					else
						waterExists = true;
				}
			}
		}

		if(fireExists && !waterExists)
			return "Fire";
		else if(waterExists && !fireExists)
			return "Water";
		else if(!fireExists && !waterExists)
			return "No one";

		return null;
	}


	// ******************************* HELPER METHODS *******************************

	/**
	 * @param piece
	 * @return whether or not it is the piece's turn
	 */
	private boolean isTurn(Piece piece) {
		return (piece.isFire() && isFiresTurn) || (!piece.isFire() && !isFiresTurn);
	}

	/**
	 * @return whether (x,y) is in bounds of the board 
	 */
	private boolean inBounds(int x, int y) {
		return x < board.length && y < board[0].length && x >= 0 && y >= 0;
	}

	/**
	 * Checks if a move is valid
	 * @param xi initial x
	 * @param yi initial y
	 * @param xf final x
	 * @param yf final y
	 * @return whether the move from (xi,yi) to (xf,yf) is valid
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {

		Piece piece = pieceAt(xi,yi);
		int deltaX = Math.abs(xi - xf);
		int deltaY = Math.abs(yi - yf);
		int diffY = yf - yi;

		// Square empty
		if(pieceAt(xf, yf) == null) {

			// A piece has not been captured yet
			if(!piece.hasCaptured()) {
				if(piece.isKing()) {
					return deltaX == 1 && deltaY == 1 || (deltaX == 2 && deltaY == 2 && enemyInMiddle(xi,xf,yi,yf));
				}
				else {
					
					// if diagonal forward of length 1
					if(deltaX == 1 && ((diffY == 1 && piece.isFire()) || (diffY == -1 && !piece.isFire()))) {
						return true;
					}
					// if any diagonal of forward length 2, return true if enemy in middle
					if(deltaX == 2 && ((diffY == 2 && piece.isFire()) || (diffY == -2 && !piece.isFire()))) {
						return enemyInMiddle(xi,xf,yi,yf);
					}
				}	
			}
			else {

				boolean constraintY;

				if(piece.isKing()) {
					constraintY = deltaY == 2;
				}
				else {
					constraintY = piece.isFire()? diffY == 2 : diffY == -2;
				}

				// Enforce constraints
				return deltaX == 2 && constraintY && enemyInMiddle(xi,xf,yi,yf);

			}
		}
		return false;
	}

	/**
	 * Checks if enemy piece in middle of (xi,yi) and (xf,yf)
	 * @param xi initial-x
	 * @param xf final-x
	 * @param yi initial-y
	 * @param yf final-y
	 * @return whether or not the piece in the middle is an enemy piece
	 */
	private boolean enemyInMiddle(int xi, int xf, int yi, int yf) {

		int xMid = (xi + xf) / 2;
		int yMid = (yi + yf) / 2;
		Piece piece = pieceAt(xMid,yMid);

		return piece != null && !isTurn(piece);	

	}

	/**
	 * Makes the path for the piece parameter
	 * @return the path of the image of the piece
	 */
	private static String makePath(Piece piece) {

		String fileName = "";

		if(piece.isBomb())
			fileName += "bomb-";
		else if(piece.isShield())
			fileName += "shield-";
		else
			fileName += "pawn-";

		if(piece.isFire())
			fileName += "fire";
		else
			fileName += "water";

		if(piece.isKing())
			fileName += "-crowned";

		return "img/" + fileName + ".png";

	}

	private static void drawBoard(Board board) {

		for (int i = 0; i < DIMEN; i++) {
			for (int j = 0; j < DIMEN; j++) {

				if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
				else                  
					StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				StdDrawPlus.setXscale(0, DIMEN);
				StdDrawPlus.setYscale(0, DIMEN);

				String path = null;
				if(board.pieceAt(i, j) != null) {
					path = makePath(board.pieceAt(i,j));
					StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
				}

			}
		}
	}

	// ******************************* MAIN *******************************

	public static void main(String[] args) {

		Board board = new Board(false);

		while(true) {

			drawBoard(board);

			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();

				if(board.canSelect(x, y)) {
					board.select(x,y);
				}
			}            
			if (StdDrawPlus.isSpacePressed()) {

				if(board.canEndTurn()) {
					board.endTurn();

					if(board.winner() != null) {
						System.out.println(board.winner() + " won!");
						return;
					}
				}
			}
			StdDrawPlus.show(100);
		}

	}

}
