// Yufan Hu 23063102

import java.util.Arrays;

public class Board {

    private Piece[][] pieces; 			// array of Pieces
    private Piece selectedP = null; 	// the selected piece
    private int selectedX = 10; 		// X Y coordinates of the selectedP
    private int selectedY = 10; 
    private boolean canET = false;		// canEnTurn boolean	
    private boolean hasMoved = false;	// Check if the player has moved a piece
    private boolean fireTurn = true;	// Tracks player turn
    private int fireCount = 12; 		// Number of fire pieces remaining
    private int waterCount = 12;		// Number of water pieces remaining

    private static void drawBoard(int N, Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	// Consider null cases
                if (b.pieces[i][j] == null) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
               		else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
               		// highlight piece if selected
               		if (b.selectedX == i && b.selectedY == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);                	
                } 
                else if (b.pieces[i][j].isFire()) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					// highlight piece if selected
					if (b.selectedX == i && b.selectedY == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	// draw pieces
	               	if (b.pieces[i][j].isBomb()) {
	               		// Consider case of Kings
	               		if (b.pieces[i][j].isKing()) {
	               			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	               		}
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	} else if (b.pieces[i][j].isShield()) {
                		if (b.pieces[i][j].isKing()) {
	               			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	               		}
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	} else {
                		if (b.pieces[i][j].isKing()) {
	               			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	               		}
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	} 
                } 
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	// highlight piece if selected
                	if (b.selectedX == i && b.selectedY == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	// draw pieces
                	if (b.pieces[i][j].isBomb()) {
                		if (b.pieces[i][j].isKing()) {
	               			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	               		}
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	} else if (b.pieces[i][j].isShield()) {
                		if (b.pieces[i][j].isKing()) {
	               			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	               		}
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	} else {
                		if (b.pieces[i][j].isKing()) {
	               			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	               		}
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	} 
                }
            }
        }
    }

	public static void main(String args[]) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false); 
		while(b.winner() == null) {
            drawBoard(8, b);
            if (StdDrawPlus.mousePressed()) {
                int mouseX = ((int) StdDrawPlus.mouseX());
                int mouseY = ((int) StdDrawPlus.mouseY());
                if (b.canSelect(mouseX, mouseY)) {
                	b.select(mouseX, mouseY);
                }
            } 
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }         
            StdDrawPlus.show(100);
        }
        if (b.winner() != null) {
            	System.out.println(b.winner());
            	System.exit(0);
        	}
    }

    /** Constructor for Board: initializes configurations 
     *  If shouldBeEmpty is true, initializes an empty Board. 
     *  Otherwise, initializes a Board with the default configuration. */
	public Board(boolean shouldBeEmpty) {
		// For empty board configuration
        if (shouldBeEmpty) {
        	pieces = new Piece[8][8];
        	fireCount = 0;
        	waterCount = 0;
        } 
        // For full board configuration
        else {
        	pieces = new Piece[8][8];
        	int[] odd = new int[] {1,3,5,7};
            int[] even = new int[] {0,2,4,6};
            int j = 0;
            for (int i : even) pieces[i][j] = new Piece(true, this, i, j, "pawn");
			j = 1;
            for (int i : odd) pieces[i][j] = new Piece(true, this, i, j, "shield");
			j = 2;
            for (int i : even) pieces[i][j] = new Piece(true, this, i, j, "bomb");
			j = 5;
            for (int i : odd) pieces[i][j] = new Piece(false, this, i, j, "bomb");
			j = 6;
            for (int i : even) pieces[i][j] = new Piece(false, this, i, j, "shield");
			j = 7;
            for (int i : odd) pieces[i][j] = new Piece(false, this, i, j, "pawn");
		}
	}



	/** Check is coordinates are out of bounds. */
	private boolean outOfBounds(int x, int y) {
		if (x < 0 || x >= 8 || y < 0 || y >= 8 || (x + y) % 2 !=0) { 
			return true;
		}
		return false;
	}

	/** Gets the piece at position (x, y) on the board, or null if there is no piece. 
	 *  If (x, y) are out of bounds, returns null. */
	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y)) { 				  // for out of bounds
			return null;
		} else {                                  // for valid piece or no piece
			return pieces[x][y];
		}
	}

	/** Returns true if the square at (x, y) can be selected.

	 *  1. A square with a piece may be selected if it is the corresponding player’s turn 
	 *  and one of the following is true:
	 *  -> 1.1 The player has not selected a piece yet.
	 *  -> 1.2 The player has selected a piece, but did not move it.

	 *  2. An empty square may be selected if one of the following is true:
	 *  -> 2.1 During this turn, the player has selected a Piece which hasn’t moved yet and
	 *     is selecting an empty spot which is a valid move for the previously selected Piece.
	 *  -> 2.2 During this turn, the player has selected a Piece, captured, and has selected 
	 *     another valid capture destination. When performing multi-captures, you should 
	 *     only select the active piece once; all other selections should be valid 
	 *     destination points. */
	public boolean canSelect(int x, int y) { // Assume (x, y) not out of bounds
		if (pieces[x][y] == null) {
			// 2.1
			if (selectedP != null && hasMoved == false && validMove(selectedX, selectedY, x, y)) { 
				return true;
			}
			// 2.2 
			else if (selectedP != null && selectedP.hasCaptured() == true && validMove(selectedX, selectedY, x, y)) {
				return true;
			}
			return false;
		}
		else if (pieces[x][y] != null) {
			if ((pieceAt(x, y).isFire() && fireTurn == true) || (!pieceAt(x, y).isFire() && fireTurn == false)) {
				// 1.1
				if (selectedP == null) {
					return true;
				}
				// 1.2
				else if (hasMoved == false) {
					return true;
				}
			}
		}
		return false;
	}

	/** Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
	 *  capture to (xf, yf), strictly from a geometry/piece-race point of view. 
	 *  validMove does not need to take into consideration whose turn it is or 
	 *  if a move has already been made, but rather can. */
	private boolean validMove(int xi, int yi, int xf, int yf) { // assumed that there is a piece starting at (xi, yi)
		// Check for out of bounds
		if (pieceAt(xi, yi) == null) { 
			System.out.println("No previous selected piece or out of bounds.");
			return false;
		}
		if (outOfBounds(xf, yf) || pieceAt(xf, yf) != null) {
			System.out.println("Selected destination out of bounds or already occupied.");
			return false;
		}
		int diffX = xf - xi;
		int diffY = yf - yi;
		// Check for Kings
		if (pieceAt(xi, yi).isKing()) {
			if (Math.abs(diffX) == 1 && Math.abs(diffY) == 1) { 		// 1-step move
				if (selectedP.hasCaptured() == true) {
					return false;
				}
				return true;
			}
			else if (Math.abs(diffX) == 2 && Math.abs(diffY) == 2) {	// 2-step capture
				int capX = xi + diffX / 2;
				int capY = yi + diffY / 2;
				if (pieceAt(capX, capY) != null && pieceAt(capX, capY).isFire() != pieceAt(xi, yi).isFire()) {
					return true;
				}
			}
			return false;
		}
		// Non-crowned pieces:
		// Fire pieces
		else if (pieceAt(xi, yi).isFire()) {
			if (Math.abs(diffX) == 1 && diffY == 1) { 	 				// 1-step move
				if (selectedP.hasCaptured() == true) {
					return false;
				}
				return true;
			}
			else if (Math.abs(diffX) == 2 && diffY == 2) {				// 2-step capture
				int capX = xi + diffX / 2;
				int capY = yi + diffY / 2;
				if (pieceAt(capX, capY) != null && !pieceAt(capX, capY).isFire()) {
					return true;
				}
			}
			return false;
		}
		// Water pieces
		else {
			if (Math.abs(diffX) == 1 && diffY == -1) { 	 				// 1-step move
				if (selectedP.hasCaptured() == true) {
					return false;
				}
				return true;
			}
			else if (Math.abs(diffX) == 2 && diffY == -2) {				// 2-step capture
				int capX = xi + diffX / 2;
				int capY = yi + diffY / 2;
				if (pieceAt(capX, capY) != null && pieceAt(capX, capY).isFire()) {
					return true;
				}
			}
			return false;
		}
	}

	/** Selects the square at (x, y). This method assumes canSelect (x,y) returns true. 
	 *  Optionally, it is recommended to color the background of the selected square 
	 *  white on the GUI via the pen color function. For any piece to perform a capture, 
	 *  that piece must have been selected first. If you select a square with a piece, 
	 *  you are prepping that piece for movement. If you select an empty square 
	 *  (assuming canSelect returns true), you should move your most recently selected 
	 *  piece to that square. */ 
	public void select(int x, int y) { 				// Assumed canSelect is true
		// Selected a position with a piece
		if (pieceAt(x, y) != null) { 
			selectedP = pieces[x][y];
			selectedX = x;
			selectedY = y;
			hasMoved = false;  		
			canET = false;
		}
		// Selected a blank position
		if (pieceAt(x,y) == null) {
			selectedP.move(x, y);
			selectedX = x;
			selectedY = y;
			hasMoved = true;
			canET = true;
		}
	}

	/** Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	 *  If another piece already exists at (x, y), p will replace that piece. 
	 *  (This method is potentially useful for creating specific test circumstances.) */
	public void place(Piece p, int x, int y) {
		if (outOfBounds(x, y)) { 				  // for out of bounds
			return;
		} 
		if (p == null){                           // for null p
			return; 
		}
		pieces[x][y] = p;						  // place p
		if (p.isFire()) {						  // Trace piece counts
			fireCount = fireCount + 1;
		}     
		if (!p.isFire()) {
			waterCount = waterCount + 1;
        }             
	}

	/** Executes a remove. Returns the piece that was removed. 
	 *  If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
	 *  If there is no piece at (x, y), returns null and prints an appropriate message. */
	public Piece remove(int x, int y) {
		if (outOfBounds(x, y)) { 				  // for out of bounds
			System.out.println("Input (" + x + ", " + y + ") is out of bounds.");
			return null;
		}
		if (pieces[x][y] == null) {               // no piece at (x, y)
			System.out.println("No piece at (" + x + ", " + y + ")."); 
			return null;
		}
		else {
			Piece r = pieces[x][y]; 			  // Trace piece counts
			if (r.isFire()) {
				fireCount = fireCount - 1;
			}     
			if (!r.isFire()) {
				waterCount = waterCount - 1;
			}         
			pieces[x][y] = null;                  // execute remove
			return r;                             // return removed piece
		}
	}

	/** Returns whether or not the the current player can end their turn. 
	 *  To be able to end a turn, a piece must have moved or performed a capture. */
	public boolean canEndTurn() {
		return canET;
	}

	/** Called at the end of each turn. Handles switching of players and anything 
	 *  else that should happen at the end of a turn. */
	public void endTurn() {
		fireTurn = !fireTurn;
		hasMoved = false;
		canET = false; 
		selectedP.doneCapturing();
		selectedP = null; 
		selectedX = 10;
		selectedY = 10;
	}

	/** Returns the winner of the game: "Fire", "Water", "No one", or null. 
	 *  If only fire pieces remain on the board, fire wins. If only water pieces remain, 
	 *  water wins. If no pieces remain (consider an explosion capture), no one wins. 
	 *  If the game is still in progress (ie there are pieces from both sides still on 
	 *  the board) return null. */
	public String winner() { 
		if (fireCount == 0 && waterCount == 0) {
			return "No one";
		}
		else if (fireCount == 0 && waterCount > 0) {
			return "Water";
		}
		else if (waterCount == 0  && fireCount > 0) {
			return "Fire";
		}
		else{
			return null;
		}
	}
}