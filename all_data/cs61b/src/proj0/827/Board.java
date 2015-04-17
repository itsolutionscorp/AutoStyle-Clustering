/** 
 *  @author Kunal Mishra
 * 	prox0 -- Creating the Board 
 */





public class Board {
	/** Checkers Board */
	private static int N = 8;
	private Piece[][] stateOfBoard; 									//Monitors the state of the board 
	
	private boolean haveSelectedPiece;
	private Piece selectedPiece;
	private int selectedXCoord, selectedYCoord, prevX, prevY;			//Monitors selection 

	private boolean fireTurn;
	private boolean waterTurn;

	private boolean turnTaken;
	private boolean secondJumpPossible;


	public static void main(String[] args) {
        Board checkerBoard = new Board(false); 			//Constructs a new Checkers Board filled with pieces in default positions
    }

    public Board(boolean shouldBeEmpty) {
    	stateOfBoard = new Piece[8][8];

    	fireTurn = true;					
    	waterTurn = false; 							// } Initializes important GUI variables 
    	haveSelectedPiece = false;		
    	turnTaken = false;
    	secondJumpPossible = false;	

    	if (!shouldBeEmpty) {
    		fillWithDefaultPieces(); 				//fills the array board with pieces in their default positions 
    		GUILoop();
    	}		
    }

    private void GUILoop() {
    	while (winner() != null) {
				drawBoard();							//Draws the board in its current state (helpers used: drawBoard, drawPiece)
				
				if (StdDrawPlus.mousePressed()) {
					
					setBox(); 							//Selects the box from the raw coordinates retrieved from a mouse press
					
					if ((canSelect(selectedXCoord, selectedYCoord)) && (!turnTaken || secondJumpPossible))
						select(selectedXCoord, selectedYCoord);

				} else if (StdDrawPlus.isSpacePressed() && canEndTurn()){
					endTurn(); 
					System.out.println("Fire's turn: " + fireTurn);
				}

				StdDrawPlus.show(25);
    		}
    }
    
    public void select(int x, int y) {
    	//Assumes canSelect has already returned true (don't need built-in redundancies) 
        setBox2(x,y); 
        Piece reference = pieceAt(x, y);

    	if (reference == null) {
    		//handles the case that I'm going to be moving my selectedPiece
    		selectedPiece.move(x, y);
			
			
    		if (!selectedPiece.hasCaptured()) {                        //I moved one space... Should be completely done
    			turnTaken = true;
                secondJumpPossible = false;
    		
    		} else {                                                    //I captured a piece by moving two
    			for(int x1 = -2; x1 <= 2 ; x1 += 4 ) {
    				for(int y1 = -2; y1 <= 2 ; y1 += 4) {
    		
    					if (validMove(x, y, x + x1, y + y1))
							secondJumpPossible = true;
    				}
    			}
    			turnTaken = true;
    		}

    	} else {
    		//handles the case that I'm currently selecting a piece to move
    		selectedPiece = reference;
    		haveSelectedPiece = true;
    	}
    }

    public boolean canSelect(int x, int y) {
        setBox2(x,y);                                           //Sets prevX and prevY and selectedXcoord and selectedYcoord so validMove will work

        System.out.println("Came into canSelect");

        Piece pieceBeingSelected = pieceAt(x, y);
        
        if (turnTaken && !secondJumpPossible) {

            return false;
        } else if (!haveSelectedPiece) {                               //If I haven't selected a piece yet 

            return ((pieceBeingSelected != null) &&                 //There must BE a piece at (x,y)
                   (pieceBeingSelected.isFire() == fireTurn));      //The piece at (x,y) is on the team of the selector
        
        } else if (haveSelectedPiece && 
                     (pieceBeingSelected != null) && 
                     (pieceBeingSelected.isFire() == fireTurn) &&
                     !turnTaken) {

            return true;
        }

        System.out.println("4 was the problem");            //HERE IS THE PROBLEM FOR TEST 14 IN VALID MOVE
        System.out.println("PrevX: " + prevX + " prevY: " + prevY);
        System.out.println("SelectedX: " + selectedXCoord + " SelectedY: " + selectedYCoord);
        System.out.println("This is a valid move: " + validMove(prevX, prevY, selectedXCoord, selectedYCoord));

        return validMove(prevX, prevY, selectedXCoord, selectedYCoord);                   //I have selected a piece... Check to make sure the move is a valid one
    }  

    private boolean validMove(int xi, int yi, int xf, int yf) {

        if ((xf < 0) || (xf > 7) || (yf < 0) || (yf > 7))
    		return false;

    	Piece destination = stateOfBoard[xf][yf];
    	int xDist = Math.abs(xf - xi);
    	int yDist = Math.abs(yf - yi);

    	if ((destination != null) || (xDist != yDist) || (selectedPiece.hasCaptured() && xDist != 2))		//The destination square must be empty
    		return false;
    	
    	else if ((xDist == 1) && selectedPiece.isKing())
    		return true;									//The destination is both empty and one square diagonal away... Standard, valid move in all directions for kings 

    	else if ((xDist == 1) && selectedPiece.isFire() && (yf > yi))
    		return true; 									//Fire pieces can only move in the positve y direction (upwards)

    	else if ((xDist == 1) && !selectedPiece.isFire() && (yf < yi))
    		return true; 									//Water pieces can only move in the negative y direction (downwards)

    	else if (xDist == 2) {
    		//This block will handle cases for jumping
    		
    		int yMid = (yf + yi) >>> 1;						//Used for finding the diagonal midpoint between the two (an average)
    		int xMid = (xf + xi) >>> 1; 

    		if ((pieceAt(xMid, yMid) == null) || (stateOfBoard[xMid][yMid].isFire() == selectedPiece.isFire()))
    			return false; 								//A piece has to jump a piece of the opposing team to move 2 in the x and y direction 

    		else if (selectedPiece.isKing())
    			return true; 								//Kings can jump in any direction

    		else if (selectedPiece.isFire() && (yf > yi))
    			return true;								//Fire pieces can only jump in the positve y direction (upwards)

    		else if (!selectedPiece.isFire() && (yf < yi))
    			return true;								//Water pieces can only move in the negative y direction (downwards)

    		else 
    			return false;								

    	} else 
    		return false;
    }

    public void place(Piece p, int x, int y) {
    	//Data Abstraction for placing pieces in the board's state array
    	if ((p != null) && (x >= 0) && (y >= 0) && (x < 8) && (y < 8)) 
    		stateOfBoard[x][y] = p;	
    }

    public Piece pieceAt(int x, int y) {
    	//Data Abstraction for getting pieces from the piece array at coordinates (x,y)
    	
    	if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) 		//Index out of bounds
    		return null;


    	return stateOfBoard[x][y];
    }

    public boolean canEndTurn() {
    	//Determines if the player can end his turn or not
    	return turnTaken;
    }

    public Piece remove(int x, int y) {
    	//Removes a piece from (x,y) and returns it 
    	//Data Abstraction for removing pieces in the board's state array

    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
    		//System.out.println("Error: Index out of Bounds"); 	
    		return null;
    	}

    	Piece reference = pieceAt(x,y);

    	if (reference == null) {
    		//System.out.println("Error, no piece found.");
    		return null;
    	}

    	stateOfBoard[x][y] = null;
    	return reference;
    }

    public void endTurn() {
    	//Changes whose turn it currently is and resets turnTaken to false
    	fireTurn = !fireTurn;
    	waterTurn = !waterTurn;
    	turnTaken = false;
    	secondJumpPossible = false;

    	//Resets selection of player 

    	haveSelectedPiece = false;
    	selectedPiece.doneCapturing();
		selectedPiece = null;
    }

    public String winner() {
    	boolean wasAFire = false;
    	boolean wasAWater = false;

    	for(int x = 0; x <= 7 ; x++) {
    		for(int y = 0; y <= 7 ; y++) {
    			
    			Piece reference = pieceAt(x,y);

    			if ((reference != null) && (reference.isFire()))
    				wasAFire = true;
    			else if ((reference != null) && (!reference.isFire()))
    				wasAWater = true;

    		}
    	}

    	if (!wasAFire && !wasAWater) 
    		return "No one";

    	else if (wasAFire && wasAWater)
    		return null;
    	
    	else if (wasAFire)
    		return "Fire";
    	
    	else 
    		return "Water";
    }

    private void setBox() {
    	//When the mouse is pressed, stores the (x,y) coordinates of the box the mouse was pointed 
    	//at and Makes them accessible to other methods of Board
    	double x = StdDrawPlus.mouseX();
		double y = StdDrawPlus.mouseY();
		
		prevX = selectedXCoord;
		prevY = selectedYCoord; 		//Stores these away for reference

		selectedXCoord = (int) x;		//Updates new selection 
		selectedYCoord = (int) y;
	}

    private void setBox2(int x, int y) {
        //Used for changing prevX, prevY, selectedXcoord, and selectedYcoord when mouse is NOT clicked but select is still called

        prevX = selectedXCoord;
        prevY = selectedYCoord;

        selectedXCoord = x;
        selectedYCoord = y;
    }

    private void fillWithDefaultPieces() {
    	//Fills the stateOfBoard Piece array with pieces in their default positions with xy coordinates

    	for(int y = 0; y < N; y++) {
    		for(int x = 0; x < N; x++) {
    			assert(x >= 0);
    			assert(y >= 0);

		    	if ((y == 0) && (x % 2 == 0)) {
		    		stateOfBoard[x][y] = new Piece(true, this, x, y, "pawn");
		    	
		    	} else if ((y == 1) && (x % 2 == 1)) {
		    		stateOfBoard[x][y] = new Piece(true, this, x, y, "shield");	
		    	
		    	} else if ((y == 2) && (x % 2 == 0)) {
		    		stateOfBoard[x][y] = new Piece(true, this, x, y, "bomb");
		    	
		    	} else if ((y == 5) && (x % 2 == 1)) {
		    		stateOfBoard[x][y] = new Piece(false, this, x, y, "bomb");
		    	
		    	} else if ((y == 6) && (x % 2 == 0)) {
		    		stateOfBoard[x][y] = new Piece(false, this, x, y, "shield");
		    	
		    	} else if ((y == 7) && (x % 2 == 1)) {
		    		stateOfBoard[x][y] = new Piece(false, this, x, y, "pawn");
		    	
		    	} else {
		    		stateOfBoard[x][y] = null;
		    	}
		    	
    		}
    	}
    }

    private void drawBoard() {
    	StdDrawPlus.setXscale(0, 8);				//Scales the drawing window 
        StdDrawPlus.setYscale(0, 8);

    	//0 will be grey, 1 will be red
    	int chooseColor = 0; 										//Will determine the color of the square being drawn 
    	
    	for(int x = 0; x < N; x++) { 								//Draws one row per iteration
    		for(int y = 0; y < N; y++) { 							//Draws one square per iteration
    			if (chooseColor == 0)
    				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    			else 
    				StdDrawPlus.setPenColor(StdDrawPlus.RED);

    			StdDrawPlus.filledSquare(x + .5, y+ .5, .5);		
    			
    			drawPiece(x, y);								//Draws a piece at the coordinates if there is supposed to be a piece there

    			chooseColor = Math.abs(chooseColor - 1); 			//Changes the color of the pen 
    		}

    	chooseColor = Math.abs(chooseColor - 1); 					//Keeps the last color of the last row drawn the 
    																	//same as the first color of the next row
    	}
    }
 
    private void drawPiece(int x, int y) {
    	Piece reference = pieceAt(x, y);

    	StringBuilder starter = new StringBuilder("img/");
    	
    	if (reference != null) {
    		//Constructs a image path based on the object's attributes

    		if (reference.isBomb())
    			starter.append("bomb-");
    		else if (reference.isShield())
    			starter.append("shield-");
    		else 
    			starter.append("pawn-");


    		if (reference.isFire())
    			starter.append("fire");
    		else
    			starter.append("water");

    		if (reference.isKing())
    			starter.append("-crowned");

    		starter.append(".png");

    		StdDrawPlus.picture(x + .5, y + .5, starter.toString(), 1, 1);
      	} 
    }
}