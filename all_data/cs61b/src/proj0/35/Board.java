public class Board {

    private Piece[][] pieces;
    private boolean currentPlayerIsFire = true;
    private boolean isGameOver = false;
    private boolean isGameAlmostOver = false;
    private Piece pieceSelected = null;
    private int pieceSelectedX;
    private int pieceSelectedY;
    private boolean hasMoved = false;

    // Classmate Christine Chen explained the 2D array to me.

    // Classmate Nathan Pannell helped me a lot with debugging. 

	public static void main (String args[]){
		// starts a GUI supported version of the game.

		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while (!b.isGameOver) {
       		b.drawBoard(N);
        	if (StdDrawPlus.mousePressed()) {
        		int x = (int) StdDrawPlus.mouseX();
        		int y = (int) StdDrawPlus.mouseY();
        		if (b.canSelect(x, y)) {
	        		b.select(x, y);        			
	        	}
        	}

        	if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
        			b.endTurn();
        	}

       		if (b.isGameAlmostOver) {
       			b.isGameOver = true;
       		}

       		if (b.winner() != null) {
       			b.isGameAlmostOver = true;
       		}

       		StdDrawPlus.show(10);
       	}

       	System.out.println("Congratulations to the winner:" + b.winner());
	}

	public Board(boolean shouldBeEmpty) {
		// The constructor for Board. If shouldBeEmpty is true, 
		// initializes an empty Board. Otherwise, initializes a 
		// Board with the default configuration. Note that an empty 
		// Board could possibly be useful for testing purposes.
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			int y = 0;
			for (int x = 0; x < 8; x += 2) {
				pieces[x][y] = new Piece(true, this, x, y, "pawn");
			}

			y = 1;
			for (int x = 1; x < 8; x += 2) {
				pieces[x][y] = new Piece(true, this, x, y, "shield");
			}

			y = 2;
			for (int x = 0; x < 8; x += 2) {
				pieces[x][y] = new Piece(true, this, x, y, "bomb");
			}

			y = 7;
			for (int x = 1; x < 8; x += 2) {
				pieces[x][y] = new Piece(false, this, x, y, "pawn");
			}

			y = 6;
			for (int x = 0; x < 8; x += 2) {
				pieces[x][y] = new Piece(false, this, x, y, "shield");
			}

			y = 5;
			for (int x = 1; x < 8; x += 2) {
				pieces[x][y] = new Piece(false, this, x, y, "bomb");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		 // Gets the piece at position (x, y) on the board, or null 
		 // if there is no piece. If (x, y) are out of bounds, returns null.

		if ((x > 7) || (y > 7) || ((x + y) % 2 != 0) || x < 0 || y < 0) {
			return null;
		} 

		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		// Returns true if there is a piece the piece at (x, y) 
		// and it can be selected.

		// * A piece may be selected if it is the corresponding player’s 
		//   turn and one of the following is true:
		// 	* The player has not selected a piece yet.
		// 	* The player has selected a piece, but did not move it.
		// * An empty square may be selected if one of the following is true:

		// 	* During this turn, the player has selected a Piece which 
		// 	  hasn’t moved yet and is selecting an empty spot which is 
		// 	  a valid move for the previously selected Piece.
		// 	* During this turn, the player has selected a Piece, 
		// 	  captured, and has selected another valid capture destination. 
		// 	  When performing multi-captures, you should only select the 
		// 	  active piece once; all other selections should be valid 
		// 	  destination points.

		if (((x + y) % 2 != 0) || x > 7 || y > 7 || x < 0 || y < 0) {
			return false;
		}
		else if (pieceAt(x, y) != null) {
			if (currentPlayerIsFire != pieceAt(x, y).isFire()) {
				return false;
			}
			else if (hasMoved) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			if ((pieceSelected == null) || 
				(pieceSelected.isFire() != currentPlayerIsFire)) {
				return false;
			}

			else if (!validMove(pieceSelectedX, pieceSelectedY, x, y)) {
				return false;
			}
			return true;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// Returns true if the piece at (xi, yi) can either move to 
		// (xf, yf) or capture to (xf, yf), strictly from a geometry/
		// piece-race point of view. validMove does not need to take 
		// into consideration whose turn it is or if a move has already 
		// been made, but rather can. 

		// Update (2/6): validMove is not required, and will not be 
		// tested. You may implement his however you want. It is suggested 
		// you use this method to simplify your implementation of canSelect.

		if (Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2) {
			return false;
		}

		if (Math.abs(xf - xi) != Math.abs(yf - yi)) {
			return false;
		}

		if (pieceAt(xf, yf) != null) {
			return false;
		}

		if (Math.abs(xf - xi) == 2 && 
			(pieceAt((xi + xf) / 2, (yi + yf) / 2) == null)) {
			return false;
		}

		if (Math.abs(xf - xi) == 2 && 
			(pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() == currentPlayerIsFire)) {
			return false;
		}

		if (pieceSelected.isFire() && !pieceSelected.isKing()) {
			if (yf < yi) {
				return false;
			}
		}

		if (!pieceSelected.isFire() && !pieceSelected.isKing()) {
			if (yf > yi) {
				return false;
			}
		}

		if (!pieceSelected.hasCaptured() && hasMoved) {
			return false;
		}

		if (hasMoved && (Math.abs(xf - xi) == 1)) {
			return false;
		}

		return true;
	}

	public void select(int x, int y) {
		// Selects the piece at (x, y) if possible. Optionally, it is 
		// recommended to color the background of the selected square 
		// white on the GUI via the pen color function. For any piece 
		// to perform a capture, that piece must have been selected first.

		if (pieceAt(x, y) != null) {
			pieceSelected = pieceAt(x, y);
			pieceSelectedX = x;
			pieceSelectedY = y;
		}

		else {
			pieceSelected.move(x,y);
			hasMoved = true;
		}

	}

	public void place(Piece p, int x, int y) {
		// Places p at (x, y). If (x, y) is out of bounds, does nothing. 
		// If another piece already exists at (x, y), p will replace 
		// that piece. (This method is potentially useful for creating 
		// specific test circumstances.)

		if ((x + y) % 2 != 0 || x > 7 || y > 7) {
			return;
		}
		pieces[x][y] = p;
		pieceSelectedX = x;
		pieceSelectedY = y;
	}

	public Piece remove(int x, int y) {
		// Executes a remove. Returns the piece that was removed. If the 
		// input (x, y) is out of bounds, returns null and prints an 
		// appropriate message. If there is no piece at (x, y), returns 
		// null and prints an appropriate message.

		if ((x + y) % 2 != 0 || x > 7 || y > 7 || x < 0 || y < 0) {
			// System.out.println("Tried to remove, but (x, y) is out of bounds.");
			return null;
		}

		if (pieceAt(x, y) == null) {
			return null;
		}

		if (pieceAt(x, y) == pieceSelected && pieceSelected.isBomb() && pieceSelected.hasCaptured()) {
			pieceSelected = null;
			pieceSelectedX = -1;
			pieceSelectedY = -1;
		}

		Piece removed = pieceAt(x, y);
		pieces[x][y] = null;
		return removed;
	}

	public boolean canEndTurn() {
		// Returns whether or not the the current player can end their
		// turn. To be able to end a turn, a piece must have moved or 
		// performed a capture.

		return hasMoved;
	}

	public void endTurn() {
		// Called at the end of each turn. Handles switching of players 
		// and anything else that should happen at the end of a turn.
		if (pieceSelected != null) {
			pieceSelected.doneCapturing();
		}
		currentPlayerIsFire = !currentPlayerIsFire;
		pieceSelected = null;
		hasMoved = false;
		pieceSelectedX = -1;
		pieceSelectedY = -1;
	}

	public String winner() {
		// Returns the winner of the game. "Fire", "Water", "No one" 
		// (tie / no pieces on the board), or null if the game is 
		// not yet over. Assume there is no stalemate situation 
		// in which the current player has pieces but cannot legally 
		// move any of them (In this event, just leave it at null). 
		// Determine the winner solely by the number of pieces 
		// belonging to each team.

		int numOfFires = 0;
		int numOfWaters = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						numOfFires += 1;
					}
					else {
						numOfWaters += 1;
					}
				}
			}
		}

		if (numOfWaters == 0 && numOfFires == 0) {
			return "No one";
		}
		else if (numOfWaters == 0 && numOfFires > 0) {
			return "Fire";
		}
		else if (numOfFires == 0 && numOfWaters > 0) {
			return "Water";
		}
		else {
			return null;
		}
	}

	private void drawBoard(int N) {
		for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

            	if ((pieceAt(x, y) == pieceSelected) && (pieceSelected != null)) {
            		StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
            	}
                else if ((x + y) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        		if (pieceAt(x, y) != null) {
        			String picture = "img/";

        			if (pieceAt(x,y).isShield()) {
        				picture = picture + "shield-";
        			}
        			else if (pieceAt(x,y).isBomb()) {
        				picture = picture + "bomb-";
        			}
        			else {
        				picture = picture + "pawn-";
        			}

        			if (pieceAt(x,y).isFire()) {
        				picture = picture + "fire";
        			}
        			else {
        				picture = picture + "water";
        			}

        			if (pieceAt(x, y).isKing()) {
        				picture = picture + "-crowned";
        			}

        			picture = picture + ".png";
                    StdDrawPlus.picture(x + .5, y + .5, picture, 1, 1);
                }
            }
        }
	}

}