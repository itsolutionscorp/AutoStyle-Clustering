public class Board {
//main method starts a new game of checkers61b in GUI mode and doe not return until the game is over
//gui coordinate system: (0,0) is bottom left corner and (7, 7) is the top right corner
//When a user clicked on a square: 1. extract the coordinates of the piece 2. check if you can select a piece using canSelect in API 3. if allowed, then call select on its coordinates
//When a user pressed spacebar: 1. check if current player is allowed to end their turn using endTurn in API 2. If so, make any changes necessary in ending the turn e.g. switching players

	private  Piece[][] pieces;
	private  boolean player = true;
	private  Piece heldPiece = null;
	private  int xSelected = -1;
	private  int ySelected = -1;;
	private  int xHeld;
	private  int yHeld;
	private static int N = 8;
	private  boolean canMove = true;



    public static void main (String[] args) {
        Board b = new Board(false);
    	//Yes, pieces is an array of Piece, so if there is no Piece it must be null.
		//To clarify, pieces should be a instance variable for Board rather than a local variable in its constructor, so that it is accessible from other methods.
    	
        b.drawBoard(8);
		//Your main method is the default way to run your program, so it should always set up a non-empty board (and run the GUI)
		//always just set it to false in the constructor
	}
	public Board(boolean shouldBeEmpty) {
		//instantiates all the Pieces using pieces[x][y] = new Piece(...)
		if (shouldBeEmpty) {
			//initialize empty Board (useful for testing purposes)
			//create an empty array of pieces
			pieces = new Piece[8][8];
			// drawBoard(8);

		}
		else {
			pieces = new Piece[8][8];
			defaultConfig();
			//drawBoard(8);

		//initializeas a board with the default configuration
		}
	}
//use this from inside your Board constructor when you create Piece objects.  This shouldn't be done in Board's main()
	 private void drawBoard(int N) {
	 	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
	

///////////////////////Draws Board 
	    while(true) {
//////////////////////Click
	    for (int xc = 0; xc < N; xc++) {
	        for (int yc = 0; yc < N; yc++) {

	            if ((xc + yc) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            StdDrawPlus.filledSquare(xc + .5, yc + .5, .5);
	            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            StdDrawPlus.filledSquare(xSelected + .5, ySelected + .5, .5);
	        }
	    }
	     if (StdDrawPlus.mousePressed()) {
	         int xS = (int) StdDrawPlus.mouseX();
	         int yS = (int) StdDrawPlus.mouseY();
	         if (canSelect(xS, yS)) {
	         	xSelected = xS;
	         	ySelected = yS;
	         	select(xSelected, ySelected);
	         	// StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	         	// StdDrawPlus.filledSquare(xSelected + .5, ySelected + .5, .5);
	         }
	     }
//////////////////////Space
	     if (StdDrawPlus.isSpacePressed()) {
	     	if (canEndTurn()) {
	     	   endTurn(); 
	     	}
	 	 }
///////////////////Winner
     		//winner();
 /////////////////////Draw Pieces 
    for (int xc = 0; xc < N; xc++) {
        for (int yc = 0; yc < N; yc++) {
            Piece curPiece = pieces[xc][yc];
            if (curPiece != null) {
	        	if (curPiece.side() == 0) {
	        		if (curPiece.isBomb()) {
	        			if (curPiece.isKing()) {
	        				StdDrawPlus.picture(xc + .5, yc + .5, "img/bomb-fire-crowned.png", 1, 1);
	        			}
	        			else StdDrawPlus.picture(xc + .5, yc + .5, "img/bomb-fire.png", 1, 1);
	        		}
	        		else if (curPiece.isShield()) {
	        			if (curPiece.isKing()) {
	        				StdDrawPlus.picture(xc + .5, yc + .5, "img/shield-fire-crowned.png", 1, 1);
	        			}
	        			else StdDrawPlus.picture(xc + .5, yc + .5, "img/shield-fire.png", 1, 1);
	        		}
	        		else {
	        			if (curPiece.isKing()) {
	        				StdDrawPlus.picture(xc + .5, yc + .5, "img/pawn-fire-crowned.png", 1, 1);
	        			}
	        			else StdDrawPlus.picture(xc + .5, yc + .5, "img/pawn-fire.png", 1, 1);
	        		}
	        	}
	        	if (curPiece.side() == 1) {
	        		if (curPiece.isBomb()) {
	        			if (curPiece.isKing()) {
	        				StdDrawPlus.picture(xc + .5, yc + .5, "img/bomb-water-crowned.png", 1, 1);
	        			}
	        			else StdDrawPlus.picture(xc + .5, yc + .5, "img/bomb-water.png", 1, 1);
	        		}
	        		else if (curPiece.isShield()) {
	        			if (curPiece.isKing()) {
	        				StdDrawPlus.picture(xc + .5, yc + .5, "img/shield-water-crowned.png", 1, 1);
	        			}
	        			else StdDrawPlus.picture(xc + .5, yc + .5, "img/shield-water.png", 1, 1);
	        		}
	        		else {
	        			if (curPiece.isKing()) {
	        				StdDrawPlus.picture(xc + .5, yc + .5, "img/pawn-water-crowned.png", 1, 1);
	        			}
	        			else StdDrawPlus.picture(xc + .5, yc + .5, "img/pawn-water.png", 1, 1);
	        		}
	        	}
        	}
        }
    }
    StdDrawPlus.show(10);
}
}

	//Piece(boolean isFire, Board b, int x, int y, String type)
	private void defaultConfig() {
    for (int xc = 0; xc < 8; xc++) {
        for (int yc = 0; yc < 8; yc++) {
            if (((xc % 2) == 0) && yc == 0) {
            	pieces[xc][yc] = new Piece(true, this, xc, yc, "pawn");
            }
            else if ((((xc + 1) % 2) == 0) && yc == 1) {
            	pieces[xc][yc] = new Piece(true, this, xc, yc, "shield");
            }
			else if (((xc % 2) == 0) && yc == 2) {
				pieces[xc][yc] = new Piece(true, this, xc, yc, "bomb");
			}
			else if ((((xc + 1) % 2) == 0) && yc == 5) {
				pieces[xc][yc] = new Piece(false, this, xc, yc, "bomb");
			}
			else if (((xc % 2) == 0) && yc == 6) {
				pieces[xc][yc] = new Piece(false, this, xc, yc, "shield");
			}
			else if ((((xc + 1) % 2) == 0) && yc == 7) {
				pieces[xc][yc] = new Piece(false, this, xc, yc, "pawn");
	    }}}
	}

	public  Piece pieceAt(int x, int y) {
		//returnss the piece at position (x, y) on the board, or null if there is no piece. if (x, y) out of bounds, retrn null;
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			return pieces[x][y];
		}
		return null;
	}

	public  void place(Piece p, int x, int y) {
//places p at (x, y). If (x, y) is out of bounds, does nothing.
//if another piece already exists at (x, y), p will replace that piece. 
//(this method is potentially useful for creating specific test circumstances)
		if (p != null) {
			pieces[x][y] = p;
		}
	}

	public  Piece remove(int x, int y) {
 	if (pieceAt(x, y) == null) {
 		System.out.print("Piece is out of bounds");
	 	return null;
	 }
	 else if (pieceAt(x, y) == null) {
	 	System.out.print("No piece at position (x, y)");
		return null;
	 }
	 else {
		Piece removedPiece = pieces[x][y];
		pieces[x][y] = null;
		return removedPiece;
	}
	}

///CANSELECT MUST BE MODIFIED
	public  boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) { //there is no piece in square
			if (heldPiece != null && canMove == true && validMove(xHeld, yHeld, x, y)) 
			//IS holding a piece && can still move && move is a valid
			//valid move for previously selected piece ) 
			{
				return true;
			}
			if (heldPiece != null && heldPiece.hasCaptured() && validMove(xHeld, yHeld, x, y))//p is a valid capture destination) 
			//IS holding a pice && DID capture && move is valid
			{
				return true;
			}
		}
		if (p != null) { //if there is a piece in square
			if (player == p.isFire()) {
				if (heldPiece == null && canMove == true) { //The player has not selected a piece yet
					return true;
				}
				else if (heldPiece != null && canMove == true) { //The player has selected a piece, but did not move it
					return true;
				}
			}
		}

		//wrong player 
			return false;
	}

	private  boolean validMove(int xi, int yi, int xf, int yf) {
		////KING Can Move Anywhere
		if (pieceAt(xi,yi).isKing()) {
			if (((yf == (yi + 1) && (xf == (xi - 1) || xf == (xi + 1))) || (yf == (yi - 1) && (xf == (xi - 1) || xf == (xi + 1)))) && (pieceAt(xi, yi).hasCaptured() == false)) { //diagonal
				return true;
			}
			else if ((yf == (yi + 2) && (xf == (xi - 2) || xf == (xi + 2))) || (yf == (yi - 2) && (xf == (xi - 2) || xf == (xi + 2)))) {
				Piece midP = pieces [((xi + xf) / 2)][((yi + yf) / 2)];
				if (midP != null) {
					if (midP.isFire() != player) {
						return true;
					}
					return false;
				}
				return false;
			}
			else return false;
		}

		///FIRE moves up
		else if (player == true) { 
			if ((yf == (yi + 1) && (xf == (xi - 1) || xf == (xi + 1))) && (pieceAt(xi, yi).hasCaptured() == false)) { //diagonal
				return true;
			}
			else if (yf == (yi + 2) && (xf == (xi - 2) || xf == (xi + 2))) {
				Piece midP = pieces [((xi + xf) / 2)][((yi + yf) / 2)];
				if (midP != null) {
					if (midP.isFire() != player) {
						return true;
					}
					return false;
				}
				return false;
			}
			else return false;

		}
/////////////POSSIBLE ERROR HERE ELSE STATEMENT IS REDUNDANT???????????????????
		///WATER Falls Down 
		else if (player == false) { //water falls down
			if ((yf == (yi - 1) && (xf == (xi - 1) || xf == (xi + 1))) && (pieceAt(xi, yi).hasCaptured() == false)) { //diagonal
				return true;
			}
			else if (yf == (yi - 2) && (xf == (xi - 2) || xf == (xi + 2))) {
				Piece midP = pieces [((xi + xf) / 2)][((yi + yf) / 2)];
				if (midP != null) {
					if (midP.isFire() != player) {
						return true;
					}
					return false;
				}
				return false;
			}
			else return false;
		}

		return false;
}

//canSelect() checks if the x, y can be selected and returns true if yes, else false.  
	//Only do that.  Do not assume (x, y) will be selected, 
	//start selecting it or taking any other action, or update any private "game state" variables 
//in this method!


		//*A piece may be selected if it is the corresponding player’s turn and one of the following is true:

// The player has not selected a piece yet.
// The player has selected a piece, but did not move it.
// An empty square may be selected if one of the following is true:

// During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
// During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
// **//
	

// // boolean validMove(int xi, int yi, int xf, int yf) - 
// // Returns true if the piece at (xi, yi) can either move to (xf, yf) 
// // or capture to (xf, yf), strictly from a geometry/piece-race point of view. 
// // validMove does not need to take into consideration whose turn it is or if 
// // a move has already been made, but rather can. Update (2/6): validMove is 
// // not required, and will not be tested. You may implement this however you want. 
// // It is suggested you use this method to simplify your implementation of canSelect.

	public  void select(int x, int y) {
//if possible, selected the piece at (x, y). Recommended to color the background of th eselcted 
		//square white on the GUI via the pen color function.
//For any piece to perform a capture, that piece must have been selected first.

//select() DOES NOT call canSelect() like it did in an early spec.  
//The latest spec says it assumes you can select the (x, y).  
//select() does the actual selection with the help of Piece's move method to update it.

        if (pieces[x][y] != null) { //there is something is selected place)
        	heldPiece = pieces[x][y];
        	xHeld = x;
        	yHeld = y;
        	canMove = true;

        }
        else { //there is nothing in selected place SO move heldPiece to new place
        	heldPiece.move(x, y);
        	canMove = false;
        	heldPiece = pieces[x][y];
        	xHeld = x;
        	yHeld = y;
        }
	}
	public  boolean canEndTurn() {
		return !canMove;
	}

	public void endTurn() {
			if (heldPiece != null) {
			heldPiece.doneCapturing();
		}
			player = !player;
			canMove = true;		

	}

	public String winner() {
		//returns the winner of the game: "Fire" "water" "No One" or nul
		//if only fire pieces remain on board, "fire"
		//if only water pieces remain on board, "water"
		//if no pieces remain, no one wins
		//if game still in progress, (pieces are present from both sides still on board), return null 
		//Assume there is no stalemate situation in which the current player haspieces but cannt legally move any of them (null) 
		//winner solely determined by the number of pieces belonging to each team. 
		int f = 0;
		int w = 0;
		for (int xc = 0; xc < 8; xc++) {
        	for (int yc = 0; yc < 8; yc++) {
        		if (pieceAt(xc, yc) != null) {
	            	if (pieceAt(xc, yc).isFire()) {
	            		f = f + 1;
	            	}
	            	else if (!pieceAt(xc, yc).isFire()) {
	            		w = w + 1;
	            	}
	            }
            }
        }
        if (w == 0 && f == 0) {
        	return "No One";
        }
        else if (w == 0) {
        	return "Fire";
        }
        else if (f == 0) {
        	return "Water";
        }
        else {
        	return null;
        }
    }
}
	

