/*
Name: Wendy Tang
Date: 2/7/15
Assignment: proj0
*/

// Import Statements ?


public class Board {

	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private String image_name;
	private int N;
	private int mouse_X;
	private int mouse_Y;
	private boolean fireTurn;
	private int xi, yi, xf, yf;
	private Piece selectedPiece;
	private boolean hasMoved;
	private boolean hasCaptured;
	/* Constructor 
	   Check: when does shouldBeEmpty come into play? 
	*/
	public Board(boolean wouldbeempty){
		// Initialize configurations
		N = 8;
		pieces = new Piece[N][N];
		shouldBeEmpty = wouldbeempty;
		fireTurn = true;
		hasMoved = false;
		xi = -1;
		yi = -1;
		xf = -1;
		yf = -1;
		mouse_Y = -1;
		mouse_X = -1;
		if(!shouldBeEmpty){
			init_pieces();
		}
	}

	/* Taken from StdDrawDemo.java
	 * Draws the initial state of the board */
    private void drawBoard() {
    	//String image_name;
    	//boolean should_draw_bool;
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	//draw the board
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }                 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    /* Helper method isEven */
    private boolean isEven(int i){
    	int remain = i % 2;
    	if(remain == 0){
    		return true;
    	} else {
    		return false;                       
    	}
    }

    /* Helper method to initialize pieces array with starting positions 
       It also keeps track of inputs names with a string array.
    */
    private void init_pieces(){
    	for(int y=0; y < N; y++){
    		for(int x=0; x < N; x++){
    			if(y == 0 && isEven(x)){
    				pieces[x][y] = new Piece(true,this,x,y,"pawn");
    			}
    			if(y == 1 && !isEven(x)){
    				pieces[x][y] = new Piece(true,this,x,y,"shield");
    			}
    			if(y == 2 && isEven(x)){
    				pieces[x][y] = new Piece(true,this,x,y,"bomb");
    			}
    			if(y == 5 && !isEven(x)){
    				pieces[x][y] = new Piece(false,this,x,y,"bomb");
    			}
    			if(y == 6 && isEven(x)){
    				pieces[x][y] = new Piece(false,this,x,y,"shield");
    			}
    			if(y == 7 && !isEven(x)){
    				pieces[x][y] = new Piece(false,this,x,y,"pawn");
    			}
    		}
    	}
    }

    /* Helper Method to get image file path using pieces_name array */
    private void pieces_image(int x, int y) {
		if(pieces[x][y].isFire() && !pieces[x][y].isBomb() && !pieces[x][y].isShield()){
			if(pieces[x][y].isKing()){
				image_name = "img/pawn-fire-crowned.png";
			} else {
				image_name = "img/pawn-fire.png";
			}
		}
		if(pieces[x][y].isFire() && pieces[x][y].isShield()){
			if(pieces[x][y].isKing()){
				image_name = "img/shield-fire-crowned.png";
			} else {
				image_name = "img/shield-fire.png";
			}
		}
		if(pieces[x][y].isFire() && pieces[x][y].isBomb()){
			if(pieces[x][y].isKing()){
				image_name = "img/bomb-fire-crowned.png";			
			} else {
				image_name = "img/bomb-fire.png";
			}
		}
		if(!pieces[x][y].isFire() && pieces[x][y].isBomb()){
			if(pieces[x][y].isKing()){
				image_name = "img/bomb-water-crowned.png";
			} else {
				image_name = "img/bomb-water.png";
			}
		}
		if(!pieces[x][y].isFire() && pieces[x][y].isShield()){
			if(pieces[x][y].isKing()){
				image_name = "img/shield-water-crowned.png";
			} else {
				image_name = "img/shield-water.png";
			}	
		}
		if(!pieces[x][y].isFire() && !pieces[x][y].isBomb() && !pieces[x][y].isShield()){
			if(pieces[x][y].isKing()){
				image_name = "img/pawn-water-crowned.png";
			} else {
				image_name = "img/pawn-water.png";
			}
		}
	}

	/* Helper method
	 * returns boolean based on piece indicating whether or not to draw */
	private boolean should_draw(int x, int y) {
		if(pieces[x][y] == null){
			return false;
		} else {
			return true;
		}
	}

    /* Method to draw the pieces given instance variable pieces array */
    private void drawPieces(){
    	for(int y = 0; y < N ; y++){
    		for(int x = 0; x < N ; x++){
    			if(should_draw(x,y)){
    				pieces_image(x,y);
	    			StdDrawPlus.picture(x + .5, y + .5, image_name,1,1);
    			}
    		}
    	}
    }

    /* Helper method to get identity of a piece */
    private String pieceIdentity(Piece p){
    	if(p.isBomb()){
    		return "bomb";
    	} else if (p.isShield()){
    		return "shield";
    	}
   		return "pawn";
    }


	/* Gets the piece at position (x, y) on the board, 
	 * or null if there is no piece. If (x, y) are out of bounds, returns null 
	// Check: need to take care of case when x,y > 7? */
	public Piece pieceAt(int x, int y){
		if((x < 0) || (y < 0) ||(x > N-1)||(y > N-1)){
			return null;
		} else {
			return pieces[x][y];
		}
	}
	/*Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.
	 If p already exists in the current Board, first removes it from its initial position.
	 If another piece already exists at (x, y), p will replace that piece. 
	 (This method is potentially useful for creating specific test circumstances.) */	
	public void place(Piece p, int x, int y){
		// Check: x,y out of bound or p is null
		for(int j = 0; j < N ; j++){
    		for(int i = 0; i < N ; i++){
    			if(p == pieces[i][j]){
    				pieces[i][j] = null;
    			}
    		}
    	}
	    // Place piece in respective position
	    pieces[x][y] = p;
	}
	
	/*Executes a remove. Returns the piece that was removed. 
	If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
	If there is no piece at (x, y), returns null and prints an appropriate message. */
	public Piece remove(int x, int y){
		// Check: conditions listed
		if(!((x < 0) || (y < 0) ||(x > N-1)||(y > N-1))){
			if(pieces[x][y] == null){
				return null;
			} else {
				Piece removed_piece = pieces[x][y];
				pieces[x][y] = null;
				return removed_piece;
			}
		} else {
			return null;
		}
	}

	/* Helper method vaildCapture 
	   Will indicate if the piece at xi,yi can move to xf,yf and capture the piece in between*/
	private boolean validCapture(int xi, int yi, int xf, int yf){
		// Define x and y as the location of the piece we're going to capture
		int x_mean = (xi + xf)/2;
		int y_mean = (yi + yf)/2;
		Piece potential_Capture = pieceAt(x_mean,y_mean);

		if(pieces[xf][yf] != null){
			return false;
		}
		// Check if potential Capture exists
		// Check if potential Capture is on the other team
		// Check if it is Water's turn and potential Capture is Fire
		if(potential_Capture == null){
			return false;
		} else if(!fireTurn && potential_Capture.isFire()){
			return true;
			
		} else if(fireTurn && !potential_Capture.isFire()){
			return true;

		} else {   // Check: Don't know what case this would be.
			return false;
		}
	}

	/* - Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf),
	 strictly from a geometry/piece-race point of view. 
	 validMove does not need to take into consideration whose turn it is or if a move has already been made, 
	 but rather can.*/
	private boolean validMove(int xi, int yi, int xf, int yf){
		// Create difference variables
		int x_diff = xf - xi;
		int y_diff = yf - yi;
		// Check if move is in bounds
		if(xf < N && yf < N ){
			// Check if move is one diagonal, if so easy, that's true
			// Though fire can only move one up at a time, and water can only move one down at a time
		 	if(pieces[xi][yi].isKing()){
		 		if( ( x_diff == 1 || x_diff == -1) && (y_diff == 1 || y_diff == -1) ){
		 			return true;
		 		} else if( ( x_diff == 2 || x_diff == -2) && (y_diff == 2 || y_diff == -2)){
		 			if(validCapture(xi,yi,xf,yf)){
		 				return true;
		 			} else{
		 				return false;
		 			}
		 		}
		 	} else
		 	if(!pieces[xi][yi].isKing() && fireTurn) {
		 		if(( x_diff == 1 || x_diff == -1 ) && y_diff == 1 ){
		 			return true;
		 		}
		 		// Check: if player wants to go two diagonal, if it is a validCapture
		 		if((x_diff == 2 || x_diff == -2) && (y_diff == 2 )){
			 		if(validCapture(xi,yi,xf,yf)){
						return true;
					} else {
						return false;
			 		}
		 		}
		 		// Trying to move in another direction
		 		return false;
		 	}
		 	if(!pieces[xi][yi].isKing() && !fireTurn) {
		 		if(( x_diff == 1 || x_diff == -1 ) && y_diff == -1 ){
		 			return true;
		 		}
		 		// Check: if player wants to go two diagonal, if it is a validCapture
		 		if((x_diff == 2 || x_diff == -2) && (y_diff == -2 )){
			 		if(validCapture(xi,yi,xf,yf)){
						return true;
					} else {
						return false;
			 		}
		 		}
		 		// Trying to move in another direction
		 		return false;
		 	}
		}
		 //out of bounds
		 return false;
	}

	/* Returns true if the square at (x, y) can be selected.
A square with a piece may be selected if it is the corresponding player’s turn and one of the following is true:
The player has not selected a piece yet.
The player has selected a piece, but did not move it.
An empty square may be selected if one of the following is true:
During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
	*/	
	public boolean canSelect(int x, int y){
		// If have not selected a piece yet and now selecting a non-empty piece
		// Else if you have selected a piece and you want to move it to an empty space, return true
		// First check for capture mode: 
		if(hasCaptured){
			if(validMove(xi,yi,x,y) && validCapture(xi,yi,x,y)){
				return true;
			} else{
				return false;
			}
		}
		if(selectedPiece == null && pieces[x][y] != null && !hasMoved){
			// Check if piece belongs to player
			if(pieces[x][y].isFire() == fireTurn){
				return true;
			} else if(!pieces[x][y].isFire() == !fireTurn){
				return true;
			}
			// Check if the player wants to select another piece
		} else
		if(selectedPiece != null && pieces[x][y] != null && !hasMoved){
			if(pieces[x][y].isFire() == fireTurn){
				return true;
			} else if(!pieces[x][y].isFire() == !fireTurn){
				return true;
			}
			// Check if exists a selected Piece, and the next destination is empty
		} else
		if(selectedPiece != null && pieces[x][y] == null && !hasMoved){
			// Check if it is a valid move!
			if(validMove(xi,yi,x,y)){
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/* Selects the square at (x, y). 
	This method assumes canSelect (x,y) returns true. 
	Optionally, it is recommended to color the background of the selected square white on the GUI  
	via the pen color function. 
	For any piece to perform a capture, that piece must have been selected first. 
	If you select a square with a piece, you are prepping that piece for movement. 
	If you select an empty square (assuming canSelect returns true),
	 you should move your most recently selected piece to that square.
	*/
	 
	 public void select(int x, int y){
	 	// If you make a piece selection for the first time, set selectedPiece, xi, and yi as so.
	 	// Else if you have selected a piece and now are selecting an empty space, set xf, yf.

	 	// If have not selected a piece yet and now selecting a non-empty piece
	 	if(selectedPiece== null){
	 		selectedPiece = pieces[x][y];
	 		xi = x;
	 		yi = y;
		// Check if the player wants to select another piece
	 	} else if(selectedPiece != null && pieces[x][y] != null) {
	 		selectedPiece = pieces[x][y];
	 		xi = x;
	 		yi = y;
		// Check if exists a selected Piece, and the next destination is empty
	 	} else if(selectedPiece != null && pieces[x][y] == null){
	 		xf = x;
	 		yf = y;
	 		// Now implement move!
	 		// Check if it is trying to capture or not
	 		int x_diff = xi - xf;
	 		int y_diff = yi - yf;
	 		if((x_diff == 2 || x_diff == -2) && (y_diff == 2 || y_diff == -2)){
	 			if(!pieces[xi][yi].isBomb()){
	 				pieces[xi][yi].move(xf,yf);
	 				hasCaptured = true;
	 				hasMoved = true;
		 			xi = xf;
		 			yi = yf;
		 			xf = -1;
	 				yf = -1;
	 			} else {
		 			pieces[xi][yi].move(xf,yf);
		 			hasMoved = true;
	 			}
	 		// Non captures
	 		} else {
		 		pieces[xi][yi].move(xf,yf);
		 		hasMoved = true;
	 		}
	 	}
	 }

	 /* Helper method 
	 	1- playTurn will invoke a piece to move from xi,yi to xf,yf
	 	2- next, it will check for regular captures and remove pieces there
	 	3- then, it will handle the cases for bombs and shields */

	/* Returns whether or not the the current player can end their turn. 
	To be able to end a turn, a piece must have moved or performed a capture. */
	public boolean canEndTurn(){
		return hasMoved;
	}
	/* Called at the end of each turn. 
	Handles switching of players and anything else that should happen at the end of a turn. */
	public void endTurn(){
		// Revert to beginning of turn settings
		selectedPiece = null;
		hasMoved = false;
		hasCaptured = false;
		fireTurn = !fireTurn;
		xi = -1;
		yi = -1;
		xf = -1;
		yf = -1;
	}
	/* Helper method to revert settings back to capture mode
	 * Under this setting, the only valid moves for a piece is to capture another piece */
	
	public String winner(){
		boolean nonempty_board = false;  //
		int pieces_counter = 0;
		int fire_counter = 0;
		int water_counter = 0;
		for(int y = 0; y < N ; y++){
			for(int x=0; x < N; x++){
				if(!(pieces[x][y] == null)){
					nonempty_board = true;
					pieces_counter +=1;
					if(pieces[x][y].isFire()){
						fire_counter += 1;
					} else {
						water_counter +=1;
					}
				}
			}
		}
		if(nonempty_board == false){
			return "No one";
		} else
		if(fire_counter == 1 && water_counter ==0){
			return "Fire";
		} else 
		if(water_counter == 1 && fire_counter == 0){
			return "Water";
		} 
		return null;
	}
	

	private void playGame(){
		while(true){
			drawBoard();
			drawPieces();
			// Clicks mode
	    	if(StdDrawPlus.mousePressed() ) {
                mouse_X = (int) StdDrawPlus.mouseX();
                mouse_Y = (int) StdDrawPlus.mouseY();
                // Now check if selection and moves can be made
                // Sets xi,yi,xf,yf, and selectedPiece
                if(canSelect(mouse_X,mouse_Y)){
   	                select(mouse_X, mouse_Y);
		    	}
   	    	}

   	    	if(StdDrawPlus.isSpacePressed()){
	           	if(canEndTurn()){
					endTurn();
  		   		}
  		   	}
			StdDrawPlus.show(10);
		}
	}

	public static void main(String[] args) {
		// Initialize board
		Board b = new Board(false);
		b.drawBoard();
		b.drawPieces();
		b.playGame();
	}
}





