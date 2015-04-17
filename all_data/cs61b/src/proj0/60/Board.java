/**
 *  @author Evan Limanto
 */

public class Board {

	private Piece pieces[][]; 
	private boolean hasSelected = false, hasMovedOrCaptured = false;
	private int N = 8, playerTurn = 0,  selectedX, selectedY;
	
	private int validFireMoves[][] = new int[][]{{-1, 1}, {1, 1}};
	private int validWaterMoves[][] = new int[][]{{-1, -1}, {1, -1}};
	private int kingMoves[][] = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

	/** The constructor for Board. If shouldBeEmpty is true, 
		initializes an empty Board. Otherwise, initializes a Board 
		with the default configuration. Note that an empty Board could 
		possibly be useful for testing purposes.
	 */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				pieces[i][j] = null;	
			}
		}

		if (!shouldBeEmpty) {
			for(int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					// Blue shields
					place(new Piece(false, this, i, 6, "shield"), i, 6);
					
					// Red bombs
					place(new Piece(true, this, i, 2, "bomb"), i, 2);
					
					// Red pawns
					place(new Piece(true, this, i, 0, "pawn"), i, 0);
				}
				else {
					// Blue pawns
					place(new Piece(false, this, i, 7, "pawn"), i, 7);
					
					// Blue bombs
					place(new Piece(false, this, i, 5, "bomb"), i, 5);

					// Red shields
					place(new Piece(true, this, i, 1, "shield"), i, 1);
				}
			}
		}
	}

	/** Gets the piece at position (x, y) on the board, or null if there 
		is no piece. If (x, y) are out of bounds, returns null.
	 */
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return null;
		return pieces[x][y];
	}

	/** Returns true if the square at (x, y) can be selected.
	 */
	public boolean canSelect(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return false;

		if (pieceAt(x, y) != null) {
			// Square contains piece
			if ((!hasMovedOrCaptured || !hasSelected) && pieceAt(x, y).side() == playerTurn) return true;
		}
		else {		
			// Empty square, move piece or perform capture
			if (pieceAt(selectedX, selectedY) != null && hasSelected && validMove(selectedX, selectedY, x, y)) {
				return true;
			}				
		}

		return false;
	}

	/** Selects the square at (x, y). This method assumes canSelect (x,y) 
		returns true. Optionally, it is recommended to color the background 
		of the selected square white on the GUI via the pen color function. 
		For any piece to perform a capture, that piece must have been selected 
		first. If you select a square with a piece, you are prepping that piece 
		for movement. If you select an empty square (assuming canSelect returns 
		true), you should move your most recently selected piece to that square.
	 */
	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			// Empty square
			pieceAt(selectedX, selectedY).move(x, y);
			setHasMovedOrCaptured(true);
		}
		else {
			// Square contains a piece
			setHasSelected(true);
		}
		setSelectedCoordinates(x, y);
	}

	/** Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
		nothing. If p already exists in the current Board, first removes it 
		from its initial position. If another piece already exists at (x, y), 
		p will replace that piece. (This method is potentially useful for 
		creating specific test circumstances.)
	 */
	public void place(Piece p, int x, int y) {
		if (p == null || x < 0 || x >= N || y < 0 || y >= N) return;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (pieceAt(i, j) == p) {
					p = remove(i, j);
				}
			}
		}

		if (pieceAt(x, y) != null) remove(x, y);
		pieces[x][y] = p;
	}

	/** Executes a remove. Returns the piece that was removed. If the 
		input (x, y) is out of bounds, returns null and prints an 
		appropriate message. If there is no piece at (x, y), returns 
		null and prints an appropriate message.
	 */
	public Piece remove(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) {
			System.out.println("Input out of bounds, cannot remove piece!");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("There is no piece at (" + x + ", " + y + ")!");
			return null;
		}

		Piece p = pieceAt(x, y);
		pieces[x][y] = null;

		return p;
	}

	/** Returns whether or not the the current player can end their turn.
		To be able to end a turn, a piece must have moved or performed a 
		capture.
	 */
	public boolean canEndTurn() {
		return hasMovedOrCaptured;
	}

	/** Called at the end of each turn. Handles switching of players and
		anything else that should happen at the end of a turn.
	 */
	public void endTurn() {
		playerTurn = 1 - playerTurn;
		setHasSelected(false);
		setHasMovedOrCaptured(false);

		if (pieceAt(selectedX, selectedY) != null) 
			pieceAt(selectedX, selectedY).doneCapturing();
	}

	/** Returns the winner of the game. "Fire", "Water", "No one" 
		(tie / no pieces on the board), or null if the game is not yet 
		over. Assume there is no stalemate situation in which the current 
		player has pieces but cannot legally move any of them (In this 
		event, just leave it at null). Determine the winner solely by the 
		number of pieces belonging to each team.
	 */
	public String winner() {
		int fire = 0, water = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) != null) {
					if (pieces[i][j].isFire()) fire += 1;
					else water += 1;
				}
			}
		}

		if (fire == 0 || water == 0) {
			if(fire > water) return "Fire";
			else if(fire < water) return "Water";
			return "No one";
		}
		return null;
	}

	/** Sets hasSelected to denoting whether a piece has been selected.
	 */
	private void setHasSelected(boolean b) {
    	hasSelected = b;
    }

    /** Sets selectedX and selectedY to the coordinates of a selected piece.
     */
    private void setSelectedCoordinates(int x, int y) {
    	selectedX = x;
    	selectedY = y;
    }

    /** Sets hasMoved to whether or not a piece has moved or captured this turn.
     */
    private void setHasMovedOrCaptured(boolean b) {
    	hasMovedOrCaptured = b;
    }

    private void selectAndHighlight(int x, int y) {
	    if (pieceAt(x, y) != null) {
	    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	    }
    }

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (hasSelected && i == selectedX && j == selectedY && pieceAt(i, j) != null) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieces[i][j] != null) {
                	String directory = "img/";

                	if (pieces[i][j].isBomb()) directory += "bomb";
                	else if (pieces[i][j].isShield()) directory += "shield";
                	else directory += "pawn";

                	directory += "-";
                	if (pieces[i][j].isFire()) directory += "fire";
                	else directory += "water";

                	if (pieces[i][j].isKing()) directory += "-crowned";
                	directory += ".png";

                	StdDrawPlus.picture(i + .5, j + .5, directory, 1.0, 1.0);
                }
            }
        }
    }

    /** Returns true if the piece at (xi, yi) can either move to (xf, yf)
		or capture to (xf, yf) in a valid fashion compatible with the rules.
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi != selectedX && yi != selectedY) return false;

		int possibleMoves[][];
		if (pieceAt(xi, yi).isKing()) {
			possibleMoves = kingMoves;
		}
		else if (pieceAt(xi, yi).isFire()) {
			possibleMoves = validFireMoves;
		}
		else {
			possibleMoves = validWaterMoves;
		}

		for (int i = 0; i < possibleMoves.length; i++) {
			int oneStepX = xi + possibleMoves[i][0];
			int oneStepY = yi + possibleMoves[i][1];
			int twoStepX = oneStepX + possibleMoves[i][0];
			int twoStepY = oneStepY + possibleMoves[i][1];

			// No capturing.
			if (!hasMovedOrCaptured && oneStepX == xf && oneStepY == yf && pieceAt(xf, yf) == null) {
				return true;
			}

			// Capture a piece.
			if ((!hasMovedOrCaptured || pieceAt(xi, yi).hasCaptured()) && twoStepX == xf && twoStepY == yf && pieceAt(twoStepX, twoStepY) == null) {
				if (pieceAt(oneStepX, oneStepY) != null && pieceAt(oneStepX, oneStepY).side() != pieceAt(xi, yi).side()) {
					return true;
				}
			}
		}

		return false;
	}

	/** Starts a GUI supported version of the game. 
	 */
	public static void main(String[] args) {
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        
        while (true) {
            b.drawBoard();

            // If mouse is pressed
            if (StdDrawPlus.mousePressed()) {
            	// Extract coordinates
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();

                // Check if a piece can be selected
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                	b.selectAndHighlight(x, y);
                }
            }

            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()) b.endTurn();
            }

            StdDrawPlus.show(25);

            String gameStatus = b.winner();
            if (gameStatus != null) {
            	System.out.println(gameStatus);
            	System.exit(0);
            }
        }
	}
}