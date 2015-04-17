/**
  * @author Xin Yu Tan
  * @date 02/12/15
  *
  * Class Board.java
  * Represents a checker board that allows the 61b version of
  * checkers to be played in GUI format.
  */

public class Board {
	private static final int BOARD_SIZE = 8;

	private Piece[][] pieces;
	private boolean fireTurn;
	private boolean hasSelectedPiece;
	private boolean hasMoved;
	private int xi, yi;

	/** Starts a GUI supported version of the game.
	  */
	public static void main(String[] args) {
		Board board = new Board(false);
		board.drawBoard(BOARD_SIZE);

		String winner = null;
        while (winner == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)) {
					board.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            		winner = board.winner();
            	}
            }
            if (StdDrawPlus.isNPressed()) {
            	board = new Board(false);
            }
            board.drawBoard(BOARD_SIZE);
            StdDrawPlus.show(10);
        }
    }

    /** Constructor for a Board
      * @param shouldBeEmpty creates empty board if true, creates default board if false
      */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
		fireTurn = true;
		hasSelectedPiece = false;
		hasMoved = false;
		xi = yi = -1;

		if (shouldBeEmpty) { 	// Initialize an empty board for testing
			/* do nothing */
		} else {				// Initialize a default board for playing
			resetPieces();
		}
	}

	/** Resets the board to display the starting configuration of pieces
	  */
	private void resetPieces() {
		for (int i = 0; i < BOARD_SIZE; i += 2) {
			pieces[i]  [0] = new Piece(true , this, i  , 0, "pawn");
			pieces[i+1][1] = new Piece(true , this, i+1, 1, "shield");
			pieces[i]  [2] = new Piece(true , this, i  , 2, "bomb");
			pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb");
			pieces[i]  [6] = new Piece(false, this, i  , 6, "shield");
			pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn");
		}
	}

	/** Checks whether or not an (x, y) location is in bounds
	  * @param x the x-coordinate of the location
	  * @param y the y-coordinate of the location
	  */
	private boolean isInBounds(int x, int y) {
		return (0 <= x && x < BOARD_SIZE) && (0 <= y && y < BOARD_SIZE);
	}

	/** Gets the piece at location (x, y)
	  * @param x the x-coordinate of the location
	  * @param y the y-coordinate of the location
	  * @return	the piece at location (x, y), null if (x, y) out of bounds or the piece does not exist
	  */
	public Piece pieceAt(int x, int y) {
		if (!isInBounds(x, y))
			return null;
		return pieces[x][y];
	}

	/** Places a piece at location (x, y) and replaces any existing piece there
	  * Does nothing if (x, y) out of bounds or the piece is null
	  * @param piece the piece to place
	  * @param x the x-coordinate of the location
	  * @param y the y-coordinate of the location
	  */
	public void place(Piece piece, int x, int y) {
		if (piece == null || !isInBounds(x, y))
			return;
		pieces[x][y] = piece;
	}

	/** Removes the piece at location (x, y) and returns it
	  * @param x the x-coordinate of the location
	  * @param y the y-coordinate of the location
	  * @return the piece that is removed, null if (x, y) out of bounds or the piece does not exist
	  */
	public Piece remove(int x, int y) {
		if (!isInBounds(x, y)) {
			System.out.printf("ERROR: Failed to remove piece " + 
					"at location (%d, %d). INDEX OUT OF BOUNDS.", x, y);
			return null;
		}

		Piece piece = pieces[x][y];
		if (piece == null) {
			System.out.printf("ERROR: Failed to remove piece " + 
					"at location (%d, %d). No such piece exists.", x, y);
			return null;
		}
		pieces[x][y] = null;
		return piece;
	}

	/** Checks whether or not the location (x, y) can be selected
	  * One of the following coniditions must be true for this method to return true:
	  * - A square with a piece may be selected if it is the corresponding player’s turn and one of the following is true:
	  * 	- The player has not selected a piece yet
	  * 	- The player has selected a piece, but did not move it yet
	  * - An empty square may be selected if one of the following is true:
	  * 	- During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which 
	  		  is a valid move for the previously selected Piece
	  * 	- During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. 
	    	  When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points
	  * @param x the x-coordinate of the location
	  * @param y the y-coordinate of the location
	  * @return true if the location can selected, false otherwise
	  */
	public boolean canSelect(int x, int y) {
		if (!isInBounds(x, y))
			return false;
		Piece piece = pieces[x][y];
		if (piece != null) {	// Selecting a piece
			if (piece.isFire() == fireTurn &&
					(!hasSelectedPiece || (hasSelectedPiece && !hasMoved)))
				return true;
		} else {				// Selecting an empty square
			if ((hasSelectedPiece && !hasMoved && validMove(xi, yi, x, y))
					|| (hasSelectedPiece && pieces[xi][yi] != null &&
					pieces[xi][yi].hasCaptured() && validCapture(xi, yi, x, y)))
				return true;
		}
		return false;
	}

	/** Checks if moving the piece at (xi, yi) to (xf, yf)
	  * is a valid move or not assuming xi, yi, xf, yf are in bounds.
	  */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		return validStep(xi, yi, xf, yf) || validCapture(xi, yi, xf, yf);
	}

	/** Checks whether or not moving the piece at (xi, yi) to (xf, yf)
	  * is a valid step (single square move).
	  * Assumes xi, yi, xf, yf are in bounds.
	  */
	private boolean validStep(int xi, int yi, int xf, int yf) {
		if (pieces[xf][yf] != null)	// cannot move to occupied square
			return false;

		int dx = xf - xi;
		int dy = yf - yi;
		if (Math.abs(dx) != 1 || Math.abs(dy) != 1) {
			return false;			
		}

		Piece piece = pieces[xi][yi];
		return (piece.isKing() || (piece.isFire() && dy == 1) 
				|| (!piece.isFire() && dy == -1));
	}

	/** Checks whether or not moving the piece at (xi, yi) to (xf, yf)
	  * is a valid capture (two square move).
	  * Assumes xi, yi, xf, yf are in bounds.
	  */
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		if (pieces[xf][yf] != null)	// cannot move to occupied square
			return false;

		int dx = xf - xi;
		int dy = yf - yi;
		if (Math.abs(dx) != 2 && Math.abs(dy) != 2) {
			return false;
		}

		Piece piece = pieces[xi][yi];
		Piece middle = pieces[(xi+xf)/2][(yi+yf)/2];
		if (middle == null || middle.isFire() == piece.isFire())
			return false;
		return (piece.isKing() || (piece.isFire() && dy == 2) 
				|| (!piece.isFire() && dy == -2));
	}

	/** Selects the square at (x, y), assuming canSelect(x, y)
	  * returns true.
	  * First select a piece (or reselect another piece),
	  * then select the square you wish to move it to.
	  */
	public void select(int x, int y) {
		if (pieces[x][y] != null) {	// Selecting a piece
			hasSelectedPiece = true;
		} else {					// Selecting an empty square
			pieces[xi][yi].move(x, y);
			hasMoved = true;
		}
		xi = x;
		yi = y;
	}

	/** Returns whether or not current player can end turn
	  * A piece must have moved or performed a capture.
	  */
	public boolean canEndTurn() {
		return hasMoved;
	}

	/** Handles switching of players, etc., at end of turn.
	  */
	public void endTurn() {
		if (pieces[xi][yi] != null)
			pieces[xi][yi].doneCapturing();
		hasSelectedPiece = false;
		hasMoved = false;
		xi = yi = -1;
		fireTurn = !fireTurn;
	}

	/** Returns the winner of the game: "Fire", "Water", "No one"
	  * ("No one" for tie / no pieces on board) or null if game
	  * is not over yet.
	  * Determine winner by number of pieces belonging to each team.
	  */
	public String winner() {
		int fireCount = 0, waterCount = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Piece piece = pieces[i][j];
				if (piece != null) {
					if (piece.isFire())
						fireCount++;
					else
						waterCount++;
				}
			}
		}
		if (fireCount == 0 && waterCount == 0)
			return "No one";
		else if (fireCount == 0)
			return "Water";
		else if (waterCount == 0)
			return "Fire";
		else
			return null;
	}

	private void drawBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == xi && j == yi)
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String getImage(Piece piece) {
    	boolean fire = piece.isFire();
    	boolean king = piece.isKing();
    	boolean shield = piece.isShield();
    	boolean bomb = piece.isBomb();
    	
    	if (fire && king && shield)
    		return "img/shield-fire-crowned.png";
    	if (fire && king && bomb)
    		return "img/bomb-fire-crowned.png";
    	if (fire && king)
    		return "img/pawn-fire-crowned.png";
    	if (fire && !king && shield)
    		return "img/shield-fire.png";
    	if (fire && !king && bomb)
    		return "img/bomb-fire.png";
    	if (fire && !king)
    		return "img/pawn-fire.png";
    	if (!fire && king && shield)
    		return "img/shield-water-crowned.png";
    	if (!fire && king && bomb)
    		return "img/bomb-water-crowned.png";
    	if (!fire && king)
    		return "img/pawn-water-crowned.png";
    	if (!fire && !king && shield)
    		return "img/shield-water.png";
    	if (!fire && !king && bomb)
    		return "img/bomb-water.png";
    	if (!fire && !king)
    		return "img/pawn-water.png";
    	return "ERROR: Piece <" + piece + "> has no image.";
    }
}