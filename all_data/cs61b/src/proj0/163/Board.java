//GUI for the Checkers board

// @author Rachel Zoll 
public class Board {

//WILL NOT LET ME END TURN WHEN SOMETHING HAS CAPTURED AND THEN CAN CAPTURE NO MORE
	//need to check for kings
	//need to implement bomb piece
	// need to implement multi-capture

	//Board class's main method starts a new game of Checkers in GUI mode
	// and DOESN'T RETURN UNTIL THE GAME IS OVER


//MAKE SURE TO CHANGE THE STARTING LOCATION OF A PIECE THAT HAS JUST CAPTURED SOMETHING TO BE THE ENDING LOCATION OF THAT CAPTURE (used within canSelect)
	/*When a user presses spacebar:
	* 1. check if the current player is allowed to end their turn. (See endTurn in API)
	* 2. make any changes necessary in ending the turn (i.e. switching players).
	*/

	private String kinged = ""; //(either kinged or not kinged)
	private String teamName = ""; //(Fire or water)
	private String pieceType = ""; //(either bomb, shield, or pawn)
	private String filename;
	private static int N = 8;
	private Piece[][] pieces; 

	private boolean turn = true; // FALSE: indicates water team's turn -|- TRUE: fire team's turn
	private boolean has_selected = false;
	private boolean has_moved = false;
	private boolean has_captured = false;

	private int start_coordX = 0; 
	private int start_coordY = 0; 

	private int water_count = 0; //total number of water pieces on the board
	private int fire_count = 0; //total number of fire pieces on the board

	private double x; //storing location of mouseclicks
	private double y;


	public static void main(String[] args) {

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        //updates what pieces should show up, at what locations
		
		Board b = new Board(false); //initializes a new board

		//loop through until somebody wins
    	while (b.winner() == null) { 

    		//capture the x and y coordinates of the mouseclick
    		if (b.has_captured == true || b.has_moved == false) {
		        if (StdDrawPlus.mousePressed()) {
		         	b.x = StdDrawPlus.mouseX();
		            b.y = StdDrawPlus.mouseY();		        	

		        	//Choosing a piece to move // location to move to
			        if (b.canSelect((int) b.x, (int) b.y)) {
			        	b.select((int) b.x, (int) b.y);

			        	//can now select another (empty) square to move to; starting coordinates are where we ended	
			        }
			   	}
			}

		    if (StdDrawPlus.isSpacePressed()) {
			        if (b.canEndTurn()) { //can only end turn if (has_moved == true) and (has_captured == false)
	
			        	if (b.has_captured == true) {
				        	b.x = b.start_coordX; //final ending location for pieces that captured; set so that we can 
				        	b.y = b.start_coordY; //change these pieces' has_captured to false in endTurn()
				        }

			        	b.endTurn(); //sets has_selected, has_captured, has_moved to false, changes player turn
			        }
			}  		   	
			b.drawBoard(N);  
		}

		StdDrawPlus.show(10);

        //Doesn't return until the game is over
        return;
    }	

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j] != null){ //if there is a Piece there

                	if (pieces[i][j].isBomb() == true) {
			    		pieceType = "bomb";
			    	} else if (pieces[i][j].isShield() == true) {
			    		pieceType = "shield";
			    	} else {
			    		pieceType = "pawn";
			    	}

			    	if (pieces[i][j].isKing() == true) {
			    		kinged = "-crowned";
			    	} else {
			    		kinged = "";
			    	}

			    	if (pieces[i][j].isFire() == true){
			    		teamName = "-fire";
			    	} else {
			    		teamName = "-water";
			    	}

			    
			    	filename = "img/" + pieceType + teamName + kinged + ".png";
                   	StdDrawPlus.picture(i + .5, j + .5, filename, 1, 1);
                }
            }
        }      
    }


	public Board(boolean shouldBeEmpty) {
		//According to the instructors, NEVER call drawBoard from the constructor. GUI and image updates should happen 
		// in their own loop (drawBoard), which updates automatically. All that I need to do is change which images to output. 
		
		pieces = new Piece[N][N];
		if (shouldBeEmpty == true) { //initialize an empty board
			return;
		} else {
			for (int i = 0; i < N; i++) { //x-coord
				for (int j = 0; j < N; j++) { //y-coord
					if ((i + j) % 2 == 0) {  //we can put a piece down on this spot

						if (j == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn"); //fire, pawn
						}
						if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield"); //fire, shield
						}
						if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb"); //fire, bomb
						}

						if (j == (N - 1)) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn"); //water, pawn
						}
						if (j == (N - 2)) {
							pieces[i][j] = new Piece(false, this, i, j, "shield"); //water, shield
						}
						if (j == (N - 3)) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb"); //water, bomb
						}
					}
				}				
			}
		}
	}

	//Gets the piece at position (x, y) on the board, or null if there isn't a piece. 
	// If (x, y) are out of bounds, return null
	public Piece pieceAt(int x, int y) {
        if ((x < N) && (x >= 0) && (y < N) && (y >= 0) && (pieces[x][y] != null)) { //if the position is in-bounds, and a piece is @ that pos
	         	return pieces[x][y];
	    }
        return null;
	}

	public boolean canSelect(int x, int y) {
		//selecting a square with a piece to start the turn; 
		if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire() == turn)) { //current player is selecting a piece of their own side
			if (((has_selected == false) || (has_moved == false)) && (has_captured == false)) { 
				return true;
			}
		}
		
		if ((pieceAt(x, y) == null)) {
			//already selected one square, haven't moved yet, the place we intend to move to is valid
			if (has_selected == false) {
				return false;
			}
			if ((has_selected == true) && (has_moved == false) && (validMove(start_coordX, start_coordY, x, y) == true)) {

				return true;
			}

			//THERE WAS AN ERROR HERE: FAILING SUBMIT TEST 11. MORE CAN SELECTS AFTER A CAPTURE; GETTING A NULL POINTER EXCEPTION
			//already selected, already captured, currently selecting another capture destination.
			if ((has_selected == true) && (has_captured == true) && (validMove(start_coordX, start_coordY, x, y) == true)) {
				return true;
			}
		}
		return false;
	}
 
	//NOTE: validMove (this method) is not required, and won't be tested. Suggested, however, to simplify implementation of canSelect
	//Returns true if piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf)
	private boolean validMove(int xi, int yi, int xf, int yf) {

			if (pieceAt(xi, yi).isKing()) { 
				return (validMoveUp(xi, yi, xf, yf) || validMoveDown(xi, yi, xf, yf));
			}
			//can only move up if it's a non-crowned fire piece

//THIS LINE IS CAUSING ERRORS --> TEST 11
			if (pieceAt(xi, yi).isFire()) {
				return ((yi < yf) && validMoveUp(xi, yi, xf, yf));
			}

			//can only move down if it's a non-crowned water piece
			 else {
				return ((yi > yf) && validMoveDown(xi, yi, xf, yf));
			}

	}

	private boolean validMoveUp(int xi, int yi, int xf, int yf) {
		//NO CAPTURE: only trying to move diagonally by one; no piece at our final location
		if ((pieceAt(xf, yf) == null) && (((xi + 1 == xf) && (yi + 1 == yf)) || ((xi - 1 == xf) && (yi + 1 == yf))) && (has_captured == false)) {
			return true;
		} 
		//CAPTURE: intial piece's team is opposite that of the diagonal piece's team; no piece at our final location
		if ((pieceAt(xi + 1, yi +1) != null) && (((xi + 2 == xf) && (yi + 2 == yf)) && (pieceAt(xi, yi).isFire() != pieceAt(xi + 1, yi + 1).isFire())) && (pieceAt(xf, yf) == null)) {//moving RIGHT + UP
			return true;
		}

		if ( (pieceAt(xi - 1, yi + 1) != null) && (((xi - 2 == xf) && (yi + 2 == yf)) && (pieceAt(xi, yi).isFire() != pieceAt(xi - 1, yi + 1).isFire())) && (pieceAt(xf, yf) == null)) {  //moving LEFT + UP
			return true;
		}
		return false;
	}


	private boolean validMoveDown(int xi, int yi, int xf, int yf) {
		//NO CAPTURE: only trying to move diagonally by one; no piece at our final location
		if ((((xi + 1 == xf) && (yi - 1 == yf)) || ((xi - 1 == xf) && (yi - 1 == yf))) && (pieceAt(xf, yf) == null) && (has_captured == false)) {					
			return true;
		} 

//ERROR HERE FOR TEST 11: MORE CAN SELECTS AFTER A CAPTURE
		//CAPTURE: intial piece's team is opposite that of the diagonal piece's team; no piece at our final location
		if ((pieceAt(xi + 1, yi - 1) != null) && (((xi + 2 == xf) && (yi - 2 == yf)) && (pieceAt(xi, yi).isFire() != pieceAt(xi + 1, yi - 1).isFire())) && (pieceAt(xf, yf) == null)) {//moving RIGHT + DOWN
			return true;
		}

		if ((pieceAt(xi - 1, yi - 1) != null) && (((xi - 2 == xf) && (yi - 2 == yf)) && (pieceAt(xi, yi).isFire() != pieceAt(xi - 1, yi - 1).isFire())) && (pieceAt(xf, yf) == null)) {  //moving LEFT + DOWN
			return true;
		}
		return false;
	}

	//selects the piece at (x, y) if possible.
	// OPTIONAL: color the background of selected square WHITE on GUI via pen color function. 
	// To perform a capture, piece must have been selected first. 
	public void select(int x, int y) {

		//We're selecting an empty square to move to! (second coordinate)
		if (pieceAt(x, y) == null) { 
	    	
	    	pieceAt(start_coordX, start_coordY).move(x, y);
	    	has_moved = true;
	    	if (pieceAt(x, y) != null) { //it wasn't a bomb piece that captured --it still exists
	    		has_captured = pieceAt(x, y).hasCaptured();
	    	} 

	    	//unnecessary because default of has_captured is false

	    	/*
	    	else {
	    		has_captured = false;
	    	}

			*/

	    	if (has_captured == true) {
        		start_coordX = x;
        		start_coordY = y; //set the start coordinates for the next move
        	} 

    // All of this should take place in the endTurn method!
    /*    	
        	//want to end our doneCapturing if we didn't capture anything!
        	//sets hasCaptured back to false
        	else {
        		if (pieceAt(x, y) != null) {
	        		pieceAt(x, y).doneCapturing();
	        		has_captured = pieceAt(x, y).hasCaptured(); //board's has_captured to false
	        	}
        	}


    */    	
	    }
	    else { //We're selecting which piece to move (first coordinate)
			start_coordX = x;
			start_coordY = y;  
			has_selected = true;
		}
	}

	//Places p at (x, y). If (x, y) is out of bounds or if p is null --> does nothing.
	// If p already exists in the current Board, first removes it from initial position. 
	// If another piece already exists at (x, y), p will replace that piece. 

	//USEFUL FOR CREATING SPECIFIC TEST CIRCUMSTANCES
	public void place(Piece p, int x, int y) {
		if ((x < N) && (y < N) && (p != null)) {
			outerloop: 
			for (int i = 0; i < N; i++) { //x-coord
				for (int j = 0; j < N; j++) { //y-coord
					if (p == pieces[i][j]) { //the piece already exists somewhere in the board
						p = remove(i, j);
						break outerloop;
					}
				}
			}
			pieces[x][y] = p; //replaces whatever was in that position with the removed piece
		}
		return;
	}

	//Executes a remove. Returns the piece that was removed. 
	// If input (x, y) is out of bounds, returns null and prints appropriate message. 
	// If there is no piece at (x, y), returns null and prints an appropriate message. 
	public Piece remove(int x, int y) {
        if ((x < N) & (y < N)) { //if the position is in-bounds
        	if (pieces[x][y] != null) { // there is a piece at that position
        		Piece removed_piece = pieces[x][y];	
        		pieces[x][y] = null;
		     	return removed_piece;
			} else {
				return null;
			}
		}
		return null;
	}

	//Whether or not the current player can end their turn. To be able to end a turn, piece 
	// must have moved or performed a capture.
	public boolean canEndTurn() {
		if (has_moved == true || has_captured == true) {
			return true;
		}

		return false;
	}

	//Called at the end of each turn. Handles switching of players and anything
	// else that should happen at the end of a turn
	public void endTurn() {

//ERROR: SUBMIT TEST 13; SHIELD CAPTURE SHIELD TEST: PIECE SHOULD HAVE HASCAPTURED RESET TO FALSE AFTER ENDTURN()
		//reset has_moved and has_selected so that we can use these variables for the next turn
		has_moved = false;
		has_selected = false;
		turn = !turn; //change turns so that it's the other team's turn

		//Reset has_captured boolean to false
		if (pieceAt((int) x, (int) y) != null) { //meaning it wasn't a bomb piece that exploded itself
			System.out.println("--------------------------------------------");
			System.out.println("Done capturing (before) : piece " + pieceAt((int) x, (int) y) + " is " + Boolean.toString(pieceAt((int) x, (int) y).hasCaptured()));
			pieceAt((int) x, (int) y).doneCapturing(); //for pieces that have already captured once, coords are in start_x and start_y 
			System.out.println("Done capturing (after) : piece " + pieceAt((int) x, (int) y) + " is " + Boolean.toString(pieceAt((int) x, (int) y).hasCaptured()));
			System.out.println("--------------------------------------------");
		}

		has_captured = false; //reset Board's has_captured to false
	}

	//Returns winner of game ("Fire", "Water", "No one") or null if game isn't over yet. 
	//Assume no stalemate situation in which current player has pieces but can't legally move any of them. 
	//Determine winner solely by number of pieces belonging to each team.

	//NOTE: keep track of the number of pieces each player has.  
	public String winner() {
		fire_count = 0;
		water_count = 0;
		for (int i = 0; i < N; i++) { //x-coord
				for (int j = 0; j < N; j++) { //y-coord
					if (pieces[i][j] != null) {
						if (pieces[i][j].isFire()) {
							fire_count += 1;
						} else {
							water_count += 1;
						}
					}
				}
		}

		if (water_count == 0 && fire_count == 0) {
			return "No one";
		} else if (fire_count == 0){
			return "Water";
		} else if (water_count == 0) {
			return "Fire";
		} else {
			return null;
		}
	}	
}

