/** 
 *  Kevin Tian proj 0, Feb 8th
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces = new Piece[8][8];



    /************************
    * state variables
    ************************/

    private boolean turn = true;  // toggle between the two players

    private Piece piece_selected = null;

    private boolean piece_moved = false;
    
    private int[] selected = {-1,-1}; // which board square has been selected

    /** Draws an N x N board. with the pieces in teh right place
     *  Adapted from:
     *  http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(Board b) {
    	int N = 8; // 8x8 only please
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ( i == b.selected[0] && j == b.selected[1]) StdDrawPlus.setPenColor(StdDrawPlus.WHITE); // if you have something selected
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if(b.pieces[i][j] != null){ // first make sure it isn't a null piece

                	// normal fire pieces
	                if ( b.pieces[i][j].isFire() && !( b.pieces[i][j].isKing() ) ) {
	                	if( b.pieces[i][j].isShield()){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                	}else if (b.pieces[i][j].isBomb() ){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                	}else{
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                	}
	                }	
	                // fire kings!
	                if ( b.pieces[i][j].isFire() && b.pieces[i][j].isKing() ) {
	                	if( b.pieces[i][j].isShield()){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                	}else if (b.pieces[i][j].isBomb() ){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                	}else{
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                	}
	                }	

	                // normal water pieces
	                if ( !b.pieces[i][j].isFire() && !( b.pieces[i][j].isKing() ) ) {
	                	if( b.pieces[i][j].isShield()){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                	}else if (b.pieces[i][j].isBomb() ){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                	}else{
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                }	
	                // water kings!
	                if ( !b.pieces[i][j].isFire() && b.pieces[i][j].isKing() ) {
	                	if( b.pieces[i][j].isShield()){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                	}else if (b.pieces[i][j].isBomb() ){
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                	}else{
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                	}
	                }	


            	}
                
            }
        }
    }

    // helper method to check if the coordinates are within the board
  	private boolean inBounds(int x, int y){
  		if( x>7 || x<0 || y>7 || y<0){
  			return false;
  		}
  			return true;

  	}


    /*************************************
    * The constructor for Board. If shouldBeEmpty is true, 
    * initializes an empty Board. Otherwise, initializes a 
    * Board with the default configuration. Note that an 
    * empty Board could possibly be useful for testing purposes.
    ****************************************/
  	public Board(boolean shouldBeEmpty){
  		if(!shouldBeEmpty){ // make a filled board

  			// make pieces starting at far left
  			for (int i = 0; i<8 ;i+=2){
  				pieces[i][0] = new Piece(true,  this , i, 0, "pawn");
  				pieces[i][2] = new Piece(true,  this , i, 2, "bomb");
  				pieces[i][6] = new Piece(false, this , i, 6, "shield");

  				pieces[i+1][1] = new Piece(true, this ,  i+1, 1, "shield");
  				pieces[i+1][5] = new Piece(false, this , i+1, 5, "bomb");
  				pieces[i+1][7] = new Piece(false, this , i+1, 7, "pawn");
  				// pieces[i+1][1].crownMe(); // test for king-ing-ing
  			}

  		
  		}else{
  			// do nothing, empty board. pieces[][] is not populated

        // TODO: maybe use this to set up gui scenarios? 
  		}
  		
  	}

  	// Gets the piece at position (x, y) on the board,
  	// or null if there is no piece. If (x, y) are out of bounds, returns null.
  	public Piece pieceAt(int x, int y){
  		if (inBounds(x,y)){
  			if(pieces[x][y]!= null){
  				return pieces[x][y];
  			}
  		}
  			
		return null;
  		  		
  	}

    /* returns true if the square at (x,y) can be selected 
     * can select the piece, if it is teh correct players turn
     * can select the square, if the selected piece
    */
  	public boolean canSelect(int x, int y){
  		
      /* if you are selecting a piece. It is selectable if it is yours, and if
      * (1) haven't selected anything yet
      * (2) haven't moved the last thing you selected
      * 
      * if you are selecting an empty squre
      * (1)During this turn, the player has selected a Piece which hasn’t moved yet and 
      * is selecting an empty spot which is a valid move for the previously selected Piece.
      * 
      * (2) During this turn, the player has selected a Piece, captured, and has selected 
      * another valid capture destination. When performing multi-captures, you should 
      * only select the active piece once; all other selections should be valid destination points.
      **/

    
      if (inBounds(x,y) && pieces[x][y]!=null){ // selecting something with a piece on it
        if (turn == pieces[x][y].isFire()){ // if the player's turn matches the piece 
          if (!piece_moved){
            return true; // select a piece if you haven't moved anything yet
          }
        }
      }else if (inBounds(x,y) && pieces[x][y] == null){ // selecting a blank space,
        if(piece_selected == null){ // no piece selected? can't select a blank space 
          return false; 
        }else { // a piece is already selected


          boolean isMoveValid = validMove(selected[0],selected[1],x,y);
          if (!isMoveValid){
            return false;       // first check that the move is valid
          }

          if(Math.abs(selected[1] - y) == 2){// jumping over something (moving up or down by 2 spaces)
            int toBeEaten_Y = (selected[1] + y)/2;
            int toBeEaten_X = (selected[0] + x)/2;
            if(piece_selected.isFire() ^ pieces[toBeEaten_X][toBeEaten_Y].isFire()){ // if they are on different teams USE XOR!!!!!
              return true;
            }else{
              return false; // don't try and jump over your own team man... 
            }            
          }

          // you have a valid move, and are only moving by one space... 
          if(!piece_moved){
            return true; // if that piece hasn't moved yet...
          }
          
        }
      }

  		return false;
  	}

    // simplify canSelect 
    // Returns true if the piece at (xi, yi) can either 
    // move to (xf, yf) or capture to (xf, yf), strictly 
    // from a geometry/piece-race point of view. (can't jump over someone on your own team)
    private boolean validMove(int xi, int yi, int xf, int yf){ // TODO switch back to private after testing
      
      // is a king
      if (pieces[xi][yi].isKing()){

        // if your piece is a king, can move however
        if( Math.abs(yf-yi) == 1 && Math.abs( xf-xi) == 1   ){ // only going forward one space
          return true;
        }else if( (yf == (yi+2) || yf == (yi-2)) && (xf == xi+2 || xf == xi - 2) ){
          return true;
        }

      }else{ // not a king
        if (pieces[xi][yi].isFire()){ // fire pieces go up
          if( yf == (yi+1) && Math.abs( xf-xi) == 1 )  { // only going forward one space
            return true;
          }else if( yf == (yi+2) && Math.abs( xf-xi) == 2 ) {
            return true;
          }


        } else{ // water pieces go down
          if( yf == (yi-1) && Math.abs( xf-xi) == 1 ) { // only going forward one space
            return true;
          }else if( yf == (yi-2) && Math.abs( xf-xi) == 2 ) {
            return true;
          }
        }
      }// end else not king
        

      return false; // return false if you fall through
 
    }

    public void select(int x, int y){
      /* Selects the square at (x, y) if possible. Optionally, 
      * it is recommended to color the background of the selected 
      * square white on the GUI via the pen color function. For any 
      * piece to perform a capture, that piece must have been selected 
      * first. If you select a square with a piece, you are prepping 
      * that piece for movement. If you select an empty square 
      *(assuming canSelect returns true), you should move your most 
      * recently selected piece to that square.
      **************************************/

            
      if( pieces[x][y]== null){ // a blank space is selected 
        pieces[selected[0]][selected[1]].move(x,y);
        // System.out.println(selected[0] + " " + selected[1]);
        
        selected[0] = x; // highlight dat square yo
        selected[1] = y;

        piece_moved = true; 
        if(pieces[x][y] == null){// if the bomb killed itself, nothing is selected anymore.. :(
          selected[0] = -1;
          selected[1] = -1;
          piece_selected = null;
        }

      }else{ // a space with a piece on it is selected
        piece_selected = pieces[x][y];
        piece_moved = false;
        selected[0] = x; // highlight dat square yo
        selected[1] = y;
        // System.out.println("selected a piece!");
      }     

    }

  	// place a piece yo
  	public void place(Piece p, int x, int y){
  		if( inBounds(x,y)){
  			pieces[x][y] = p; 
  		}else{
  			// if not within bounds, do nothing
  			return; 
  		}

  	}

  	//Executes a remove. Returns the piece that was removed.
    // If the input (x, y) is out of bounds, returns 
    // null and prints an appropriate message. If there is 
    // no piece at (x, y), returns null and prints an appropriate message.

  	public Piece remove(int x, int y){
  		if (inBounds(x,y)){
        if(pieces[x][y]!= null){

          Piece ret = pieces[x][y];
          pieces[x][y] = null; // remove the reference from the array
          return ret;
        }
        else{
          System.out.println("nope, empty square");
            return null;
        }
        
      }else{
        // not in bounds 
        System.out.println("no no no, not in bounds");
        return null;

      }
  	}

	// Returns whether or not the the current player can end their turn. 
 	// To be able to end a turn, a piece must have moved or performed a capture.
  	public boolean canEndTurn(){
  		if (piece_moved){
        return true; // if the piece has captured, it has also moved
      } 
      return false;
  	}

  	// Called at the end of each turn. Handles switching of players and anything else that should happen at the end of a turn.
  	public void endTurn(){
      
      // toggle back all state variables:
      turn = !turn; // switch turns
      piece_selected.doneCapturing();
      piece_selected = null; // piece_selected is a piece object
      piece_moved = false;
      selected[0] = -1; // nothing is selected anymore :( 
      selected[1] = -1;


      // TODO: doneCapturing()? 
  	}

  	//Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
  	// or null if the game is not yet over. Assume there is no stalemate situation in which the 
  	// current player has pieces but cannot legally move any of them (In this event, 
  	// just leave it at null). Determine the winner solely by the number of pieces belonging to each team.
  	public String winner(){
      // check to see if one side has no pieces left
      int numFire = 0 ;
      int numWater = 0;
      for(int i = 0; i<8; i++){
        for(int j = 0; j<8; j++){
          if (pieces[i][j]!= null){ // don't try to .isFire for null pieces
            
            if (pieces[i][j].isFire()){
              numFire+=1;
            }else{
              numWater+=1;
            }
          }
        }
      }

      if (numFire == 0 && numWater ==0){
        return "No one";
      }else if(numFire == 0){
        return "Water";
      }else if(numWater == 0){
        return "Fire";
      }else {
        return null;
      }
  		
  	}

  	// starts the GUI version of the game
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        // call board constructor with non-empty board ()

		Board myBoard = new Board(false);
        

        /** Monitors for mouse presses and updates the state of the board
        **/ 

        while(true) {
            if (StdDrawPlus.isNPressed()){ // if n is pressed, start a new game
              myBoard = new Board(false);
            }

            drawBoard(myBoard);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                // check if that piece can be selected
                if( myBoard.canSelect((int) x, (int) y)) {
                    myBoard.select((int) x,(int) y);
                    

                };
            }       

            if (StdDrawPlus.isSpacePressed()){ //if the spacebar is pressed, user wants to switch turns...  but can you?
              if(myBoard.canEndTurn()){
                myBoard.endTurn();
              }

            }   

            if(myBoard.winner()!= null){
              System.out.println(myBoard.winner()); // print out the winner
              return;
            }
            StdDrawPlus.show(100);
        }
    }
}