public class Board {

	private int N = 8;
	private Piece[][] gameboard;
	/* whoseTurn is 0 for fire, 1 for water. */
	private int whoseTurn = 0;
	private boolean beenSelected = false;
	private boolean hasHadTurn = false;
	private int selectedX = 0;
	private int selectedY = 0;
	private int numFirePieces;
	private int numWaterPieces;

	/* Want to include these variables to track if the last piece in a turn captured
	 * or was a bomb and captured.
	 * If not a bomb and captured, will not allow you to select a square with a piece in it. 
	 * If a bomb and captured, will not allow you to select anything at all.
	 * Plan: when validating a valid move of a bomb, if it's a capture, change BOMBCAPTUREHAPPENED boolean (maybe CAPTURE HAPPENED as well)
	 *       when checking valid move for any piece, if it's a capture, change CAPTUREHAPPENED boolean
	 *       in endTurn, change both booleans back to false
	 * Also need to do some sort of checking behavior with BOMBCAPTUREHAPPENED & CAPTUREHAPPENED in
	 * canSelect & canSelectBlankSquare
	 * Probably also a good idea to make sure canEndTurn is working properly*/

	private boolean bombCaptureHappened = false;
	private boolean capturedHappened = false;

	/* Board class constuctor; if SHOULDBEEMPTY is true, initializes an empty
	 * board; else initializes a board with default configuration. */
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			gameboard = new Piece[N][N];
		}
		else {
			gameboard = new Piece[N][N];
			for (int i = 0; i < N ; i += 2) {
					gameboard[i][0] = new Piece(true, this, i, 0, "pawn");
					numFirePieces += 1;
				}
			for (int i = 1; i < N ; i += 2) {
					gameboard[i][1] = new Piece(true, this, i, 1, "shield");
					numFirePieces += 1;
				}
			for (int i = 0; i < N ; i += 2) {
					gameboard[i][2] = new Piece(true, this, i, 2, "bomb");
					numFirePieces += 1;					
				}
			for (int i = 1; i < N ; i += 2) {
					gameboard[i][7] = new Piece(false, this, i, 7, "pawn");
					numWaterPieces += 1;
				}
			for (int i = 0; i < N ; i += 2) {
					gameboard[i][6] = new Piece(false, this, i, 6, "shield");
					numWaterPieces += 1;
				}
			for (int i = 1; i < N ; i += 2) {
					gameboard[i][5] = new Piece(false, this, i, 5, "bomb");
					numWaterPieces += 1;
				}
			}
	}

	/* Gets the piece at position (x, y) on board, or null if no piece. 
	 * Returns null if (x, y) out of bounds. */
	public Piece pieceAt(int x, int y) {
		if ((((x >= N) || (y >= N)) || (x < 0)) || (y < 0)) {
			return null;
		}
		return gameboard[x][y];
	}

	/* Returns true if there is a piece at (x, y) and it can be selected.
	 * A piece can be selected on the player's turn if:
	 *    1. The player hasn't yet selected a piece, or
	 * 	  2. The player selected a piece but didn't move it.
	 * An empty square can be selected if:
	 *    1. The player previously selected a piece that hasn't moved yet,
	 *       and the selected spot it valid, or
	 *    2. The player selected a piece, captured, and has selected another
	 *       valid capture destination. Select only 1 piece at a time.  */
	public boolean canSelect(int x, int y) {
		if (((((x < N) && (y < N)) && (x >= 0)) && (y >= 0))) {
			if (bombCaptureHappened) {
				return false;
			}
			if (pieceAt(x, y) != null) {
				if (hasHadTurn) {
					return false;
				}
				return (canSelectPieceSquare(x, y)); //&& !pieceAt(x, y).hasCaptured()
			}
			return canSelectBlankSquare(x, y);
		}
		else {
			return false;
		}
	}

	/* For use within canSelect(); determines if selection in valid if a piece
	 * is on the square in question. */
	private boolean canSelectPieceSquare(int x, int y) {
		if (capturedHappened) {
			return false;
		}
		if (pieceAt(x, y).side() == whoseTurn) {
			if (pieceAt(x, y).hasCaptured()) {
				return false;
			}
			if (!beenSelected) {
				return true;
			}
			if (beenSelected) { // && ((x != selectedX) && (y != selectedY))
				// x = selectedX;
				// y = selectedY;
				return true;
			}
		}
		return false;
	}

	/* For use within canSelect(); determines if selection is valid on a 
	 * blank square. */
	private boolean canSelectBlankSquare(int x, int y) {
		if (beenSelected && validMove(selectedX, selectedY, x, y)) {
			return true;
		}
		if (!beenSelected && capturedHappened) {
			return validCapture(selectedX, selectedY, x, y);
		}
		return false;
	}

	/* Returns true if piece at (xi, yi) can either move to (xf, yf)
	 * or capture to (xf, yf) */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece curr = pieceAt(xi, yi);
		if (curr.isKing()) {
			if (numAwayUp(xi, yi, xf, yf, 1) || numAwayDown(xi, yi, xf, yf, 1)) {
				return true;
			}
			if (curr.isFire()) {
				if (numAwayUp(xi, yi, xf, yf, 2) || numAwayDown(xi, yi, xf, yf, 2)) { // capture
					int midX = ((xi + xf) / 2);
					int midY = ((yi + yf) / 2);
					if (pieceAt(midX, midY) == null) return false;
					if (pieceAt(midX, midY).isFire()) return false;
					if (curr.isBomb()) {
						bombCaptureHappened = true;
					}
					capturedHappened = true;
					return true;
				}
				return false;
			}
			else {
				if (numAwayUp(xi, yi, xf, yf, 2) || numAwayDown(xi, yi, xf, yf, 2)) { // capture
					int midX = ((xi + xf) / 2);
					int midY = ((yi + yf) / 2);
					if (pieceAt(midX, midY) == null) return false;
					if (!pieceAt(midX, midY).isFire()) return false;
					if (curr.isBomb()) {
						bombCaptureHappened = true;
					}
					capturedHappened = true;
					return true;
				}
				return false;
			}
		}
		else if (curr.isFire()) {
			if (numAwayUp(xi, yi, xf, yf, 1)) {
				return true;
			}
			if (numAwayUp(xi, yi, xf, yf, 2)) { // capture
				int midX = ((xi + xf) / 2);
				int midY = ((yi + yf) / 2);
				if (pieceAt(midX, midY) == null) return false;
				if (pieceAt(midX, midY).isFire()) return false;
				if (curr.isBomb()) {
					bombCaptureHappened = true;
				}
				capturedHappened = true;
				return true;
			}
			return false;
		}
		else {
			if (numAwayDown(xi, yi, xf, yf, 1)) {
				return true;
			}
			if (numAwayDown(xi, yi, xf, yf, 2)) { // capture
				int midX = ((xi + xf) / 2);
				int midY = ((yi + yf) / 2);
				if (pieceAt(midX, midY) == null) return false;
				if (!pieceAt(midX, midY).isFire()) return false;
				if (curr.isBomb()) {
					bombCaptureHappened = true;
				}
				capturedHappened = true;
				return true;
			}
			return false;
		}
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece curr = pieceAt(xi, yi);
		if (curr.isKing()) {
			if (curr.isFire()) {
				if (numAwayUp(xi, yi, xf, yf, 2) || numAwayDown(xi, yi, xf, yf, 2)) { // capture
					int midX = ((xi + xf) / 2);
					int midY = ((yi + yf) / 2);
					if (pieceAt(midX, midY) == null) return false;
					if (pieceAt(midX, midY).isFire()) return false;
					if (curr.isBomb()) {
						bombCaptureHappened = true;
					}
					capturedHappened = true;
					return true;
				}
				return false;
			}
			else {
				if (numAwayUp(xi, yi, xf, yf, 2) || numAwayDown(xi, yi, xf, yf, 2)) { // capture
					int midX = ((xi + xf) / 2);
					int midY = ((yi + yf) / 2);
					if (pieceAt(midX, midY) == null) return false;
					if (!pieceAt(midX, midY).isFire()) return false;
					if (curr.isBomb()) {
						bombCaptureHappened = true;
					}
					capturedHappened = true;
					return true;
				}
				return false;
			}
		}
		else if (curr.isFire()) {
			if (numAwayUp(xi, yi, xf, yf, 2)) { // capture
				int midX = ((xi + xf) / 2);
				int midY = ((yi + yf) / 2);
				if (pieceAt(midX, midY) == null) return false;
				if (pieceAt(midX, midY).isFire()) return false;
				if (curr.isBomb()) {
					bombCaptureHappened = true;
				}
				capturedHappened = true;
				return true;
			}
			return false;
		}
		else {
			if (numAwayDown(xi, yi, xf, yf, 2)) { // capture
				int midX = ((xi + xf) / 2);
				int midY = ((yi + yf) / 2);
				if (pieceAt(midX, midY) == null) return false;
				if (!pieceAt(midX, midY).isFire()) return false;
				if (curr.isBomb()) {
					bombCaptureHappened = true;
				}
				capturedHappened = true;
				return true;
			}
			return false;
		}
	}




	private boolean numAwayUp(int xi, int yi, int xf, int yf, int z) {
		if (((xf == xi - z) && (yf == yi + z)) ||
			((xf == xi + z) && (yf == yi + z))) {
			return true;
		}
		return false;
	}

	private boolean numAwayDown(int xi, int yi, int xf, int yf, int z) {
		if (((xf == xi - z) && (yf == yi - z)) ||
			((xf == xi + z) && (yf == yi - z))) {
			return true;
		}
		return false;
	}	



	/* Selects a piece at (x, y) if possible. MAY CHANGE COLOR OF SQUARE.
	 * Required for a piece to perform a capture. */
	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedX = x;
			selectedY = y;
			System.out.println("Selected (" + String.valueOf(x) + ", " + String.valueOf(y) + ") to move.");
			beenSelected = true;
			}
		else {
			System.out.println("Selected (" + String.valueOf(x) + ", " + String.valueOf(y) + ") to place.");
			gameboard[selectedX][selectedY].move(x, y);
			selectedX = x;
			selectedY = y;
			beenSelected = false;
			hasHadTurn = true;
		}
	}


	/* Places P at (x, y); if (x,y) out of bounds or P is null, does nothing. If 
	 * another piece already exists at (x, y), P will replace that piece. */
	public void place(Piece p, int x, int y) {
		System.out.println("entered place");
		if ((x >= N) || (y >= N)) {
			return;
		}
		if (p.isFire()) {
			numFirePieces += 1;
		}
		else {
			numWaterPieces += 1;
		}
		gameboard[x][y] = p;
		System.out.println("Placed (" + String.valueOf(x) + ", " + String.valueOf(x) + ")");
		return;
	}

	/* Executes a remove, and returns the piece that was removed.  If (x,y) is out
	 * of bounds or no piece at (x,y), returns  null & prints appropriate message*/
	public Piece remove(int x, int y) {
		if ((x >= N) || (y >= N)) {
			System.out.println("These coordinates are out of bounds.");
			return null;
		}
		Piece removed = pieceAt(x, y);
		gameboard[x][y] = null;
		if (removed.isFire()) {
			numFirePieces -= 1;
		}
		else {
			numWaterPieces -= 1;
		}
		return removed;
	}

	/* Returns whether or not current player can end their turn. */
	public boolean canEndTurn() {
		return hasHadTurn;
	}

	/* Called at end of each turn; switches players. */
	public void endTurn() {
		hasHadTurn = false;
		beenSelected = false;
		bombCaptureHappened = false;
		capturedHappened = false;
		if (whoseTurn == 1) {
			whoseTurn = 0;
		}
		else {
			whoseTurn = 1;
		}
	}

	/* Returns the winner of the game, "Fire", "Water", "No one", or null
	 * if game isn't over. Determined based on number of pieces left.*/
	public String winner() {
		if ((numFirePieces == numWaterPieces) && (numWaterPieces == 0)) {
			return "No one";
		}
		if (numWaterPieces == 0) {
			return "Fire";
		}
		if (numFirePieces == 0) {
			return "Water";
		}
		return null;
	}

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (gameboard[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, pieceImage(gameboard[i][j]), 1, 1);
                }
            }
        }
    }

    /*Returns the string required for each piece's image file */
    private String pieceImage(Piece p) {
    	String type = "pawn";
    	String player = "-water";
    	String crown = "no";
    	if (p.isBomb()) type = "bomb";
    	else if (p.isShield()) type = "shield";
    	if (p.isFire()) player = "-fire";
    	if (p.isKing()) crown = "-crowned";
    	if (crown != "no") {
    		return "img/" + type + player + crown + ".png";
    	}
    	return "img/" + type + player + ".png";
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                }
     		}
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                	b.endTurn();
                }
            }            
            StdDrawPlus.show(10);
        }
    }
}