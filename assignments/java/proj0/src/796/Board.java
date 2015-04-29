//STEP3:Let it display a blank 8*8 board.

//STEP4: Then modify it to be with the configuration of first img on spec. NO JUNIT test.

//Tthings needed to be done: add StdDrawPlus.isSpacePressed...
//  Delete all the print.

public class Board{
	
	
	// Initialization
	
	//each space on the board contains the info of one piece
	//private static Piece[][] pieces;
	//private static Piece[][] pieces;
	private boolean shouldBeEmpty;

	private Board thisBoard;



 	private  int N = 8;

 	private  Piece[][] pieces = new Piece[N][N];

 	// private  boolean[][] hasSelected;

 	private  boolean[][] hasSelectedPieces = new boolean[N][N];
 	
 	private  boolean isRedTurn = true;

 	private Piece mostRecentlySelected; 

 	private boolean isMoved = false;



	//refactor it later!!!!!!!
	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		
		//difault configuration
		if(!shouldBeEmpty){
			// draw it out!!
			for(int i=0; i<N; i++){
	    		for(int j=0; j<N; j++){
	    			if((i + j) % 2 == 0){
	    				if(i == 0){
	    					pieces[j][i] = new Piece(true, this, j, i, "pawn");
	    				}
	    				else if(i == 1){
	    					pieces[j][i] = new Piece(true, this,  j, i, "shield");
	    				}
	    				else if(i == 2){
	    					pieces[j][i]= new Piece(true, this,  j, i, "bomb");
	    				}
	    				else if(i == 5){
	    					pieces[j][i]= new Piece(false, this,  j, i, "bomb");
	    				}
	    				else if(i == 6){
	    					pieces[j][i] = new Piece(false, this,  j, i, "shield");
	    				}
	    				else if(i == 7){
	    					pieces[j][i] = new Piece(false, this,  j, i, "pawn");
	    				}
	    				else{
	    					pieces[j][i] = null;
	    				}
	    			}
	    		}
    		}
		} //end of if

		//else initialize every spot with null
		else{
			for(int i=0; i<N; i++){
	    		for(int j=0; j<N; j++){
	    			pieces[i][j] = null;
	    		}
	    	}
		}
	}

	public Piece pieceAt(int x, int y){
		// check if out of boundecho
		if(x < 0 || x > N-1 || y < 0 || y > N-1)
			return null;
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		//if it's a piece
		if( pieces[x][y] != null ){
			//if it's right player's turn, fist when it's red's turn
			if(isRedTurn){
				boolean hasNotSelected = true;
				for(int i=0; i<N; i++){
					for(int j=0; j<N; j++){
						//chekc if the player already selected a piece or not.
						//System.out.println(hasSelectedPieces);
						if(hasSelectedPieces[i][j] == true)
							hasNotSelected = false;
					}
				}
				////if the Fire player has not selected a piece and the piece is a Fire
				if(hasNotSelected && pieces[x][y].isFire()){
					return true;
				}

				//they player has selected a piece, but didnt move it yet
				else if(!hasNotSelected && !isMoved){
					return true;
				}
					//return true;	
			}
			
			//else when it's blue's turn
			else{
				boolean hasNotSelected = true;
				for(int i=0; i<N; i++){
					for(int j=0; j<N; j++){
						if(hasSelectedPieces[i][j] == true)
							hasNotSelected = false;
					}
				}
				////if the Water player has not selected a piece and the piece is a water
				if(hasNotSelected && !(pieces[x][y].isFire()) ){
					return true;
				}

				//they player has selected a piece, but didnt move it yet
				
				//they player has selected a piece, but didnt move it yet
				else if(!hasNotSelected && !isMoved){
					return true;
				}				
			}
			//else return false
			return false;
		}		

		//empty square
		else{
			//Case1: During this turn, the player has selected a Piece which hasn’t moved yet 
			//and is selecting an empty spot which is a valid move for the previously selected Piece.


			boolean hasNotSelected = true;
			int pieceX = -1;
			int pieceY = -1;
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					if(hasSelectedPieces[i][j] == true){
						hasNotSelected = false;
						pieceX = i;
						pieceY = j;
					}
						
				}
				
			}
			
			//System.out.println("189: " + validMove(pieceX,pieceY,x,y));
			if(!hasNotSelected){
				if(!isMoved && validMove(pieceX,pieceY,x,y)){
					return true;
				}

				//Case 2: During this turn, the player has selected a Piece, captured, 
				//and has selected another valid capture destination. 
				//When performing multi-captures, you should only select the active piece once; 
				//all other selections should be valid destination points.
				
				else if(pieces[pieceX][pieceY] != null){
					//make sure it's validCapture, not valid move~
					if(pieces[pieceX][pieceY].hasCaptured() && validCapture(pieceX,pieceY,x,y)){
					
						return true;
					}	
				}
			} 	
		}
		return false;
	}


	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Change to Private later!!
	//a helper function for canSelect
	private boolean validMove(int xi, int yi, int xf, int yf){
		//Returns true if the piece at (xi, yi) can either move to (xf, yf) 

		//base case, if xf and yf is out of bound, return fasle
		if(xf<0 || yf<0 || xf > N-1 || yf>N-1){
			return false;
		}

		//else if the position at xf and yf are already taken. return false
		else if(pieces[xf][yf] != null){
			// System.out.print(pieces[xf][yf].isFire());
			return false;
		}

		//then, make sure the type of piece.
		//Fire case
		else if (pieces[xi][yi].isFire()){
			//Option: Move Or Capture

			//if xf and yf are on the left or right diagno,return true
			if( yf == yi + 1 && (xf == xi - 1  || xf == xi + 1) ){
				return true;
			} 

			//capture, if they are 2 squares away from each other 
			else if(yf == yi + 2 && (xf == xi - 2 || xf == xi + 2)){
				//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
				//which in this case should be a water type
				

				if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
				
					if( !( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
						
						return true;
					}	
				}
				
			}

			// else if it's a king, it can even be on the lower left and right diagno
			else if(pieces[xi][yi].isKing()){
				if(yf == yi - 1 &&  (xf == xi - 1  || xf == xi + 1) ){
					return true;
				}

				//it can also capture in lower side
				else if(yf == yi - 2 && (xf == xi - 2 || xf == xi + 2)){
					//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
					//which in this case should be a water type
					if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
						if( !( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
							return true;
						}	
					}
				}
			}
		}

		//Water Case
		else if( !pieces[xi][yi].isFire() ){
			if( yf == yi -1  && (xf == xi - 1  || xf == xi + 1) ){
				return true;
			} 

			//capture, if they are 2 squares away from each other 
			else if(yf == yi - 2 && (xf == xi - 2 || xf == xi + 2)){
				//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
				//which in this case should be a fire type
				if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
					if( ( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
						return true;
					}		
				}
			
			}


			// else if it's a king, it can even be on the lower left and right diagno
			else if(pieces[xi][yi].isKing()){
				if(yf == yi + 1 &&  (xf == xi - 1  || xf == xi + 1) ){
					return true;
				}

				else if(yf == yi + 2 && (xf == xi - 2 || xf == xi + 2)){
					//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
					//which in this case should be a fire type
					if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
						if( ( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
							return true;
						}	
					}
					
				}

			}
		}
		return false;
		
	}

	//this is redundant compared to vaildMove
	private boolean validCapture(int xi, int yi, int xf, int yf){
			//Returns true if the piece at (xi, yi) can either move to (xf, yf) 

		//base case, if xf and yf is out of bound, return fasle
		if(xf<0 || yf<0 || xf > N-1 || yf>N-1){
			return false;
		}

		//else if the position at xf and yf are already taken. return false
		else if(pieces[xf][yf] != null){
			// System.out.print(pieces[xf][yf].isFire());
			return false;
		}

		//then, make sure the type of piece.
		//Fire case
		else if (pieces[xi][yi].isFire()){
			//Option: Move Or Capture
		
			//capture, if they are 2 squares away from each other 
			if(yf == yi + 2 && (xf == xi - 2 || xf == xi + 2)){
				//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
				//which in this case should be a water type
				if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
				
					if( !( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
						
						return true;
					}	
				}
				
			}

			// else if it's a king, it can even be on the lower left and right diagno
			else if(pieces[xi][yi].isKing()){
			
				//it can also capture in lower side
				if(yf == yi - 2 && (xf == xi - 2 || xf == xi + 2)){
					//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
					//which in this case should be a water type
					if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
						if( !( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
							return true;
						}	
					}
				}
			}
		}

		//Water Case
		else if( !pieces[xi][yi].isFire() ){

			//capture, if they are 2 squares away from each other 
			if(yf == yi - 2 && (xf == xi - 2 || xf == xi + 2)){
				//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
				//which in this case should be a fire type
				if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
					if( ( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
						return true;
					}		
				}
			
			}

			// else if it's a king, it can even be on the lower left and right diagno
			else if(pieces[xi][yi].isKing()){

				if(yf == yi + 2 && (xf == xi - 2 || xf == xi + 2)){
					//and there is a different type in between pieces[xi][yi] and pieces[xf][yf]
					//which in this case should be a fire type
					if(pieces[(xi+xf)/2][(yi+yf)/2] != null){
						if( ( pieces[(xi+xf)/2][(yi+yf)/2].isFire() ) ){
							return true;
						}	
					}	
				}

			}
		}
		return false;

	}

	public void select(int x, int y){
		//this method assumes canSelect returns true
		

	    //Case 1: if there is a piece exising at pieces[x][y], then 
	    //we are prepping that piece for movement
		
		//so we need to select it, which is to put it in our bool Array
		////update most recently selected
		if(pieces[x][y] != null){
			hasSelectedPieces[x][y] = true;
			mostRecentlySelected = pieces[x][y];
			//System.out.println("a");
		}
			
	     //Case 2: if pieces[i][j] is an empty square, we move most recently selected piece to that square
	     else if(pieces[x][y] == null){
	     	mostRecentlySelected.move(x, y);
	     	isMoved = true;
	     	setToFalse();
	     	hasSelectedPieces[x][y] = true;
	     	//System.out.println("back");
	     	// thisBoard.drawChess(N);
	     }

	    
	    
	}

	public void place(Piece p, int x, int y){
		// remember currentX and currentY is private, so Piece P cant 
		// get access to it. Sad..

		if (!(x < 0 || x > N-1 || y < 0 || y > N-1) || p == null){
			//if another piece already exists at (x,i), p will replace that piece
			//System.out.println("place");
			pieces[x][y] = p;	
		}

		//If another piece already exists at (x, y), p will replace that piece.
		else if(pieces[x][y] != null){
			//to replace, first of all set the pieces[x][y] to null, then reassign p to it
			pieces[x][y] = null;
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if(x > N-1 || x < 0 || y > N-1 || y < 0){
			System.out.println("Remove Out of Bound");
			return null;
		}
		else if(pieces[x][y] == null){
			System.out.println("the piece at (" + x + ", " + y + ") is empty, couldnt be removed");
			return null;		
		}
		else{
			Piece original = pieces[x][y];
			//first of all, get the type
			String type;
			if(original.isShield()){
				type = "shield";
			}
			else if(original.isBomb()) {
				type = "bomb";
			}
			else{
				type = "pawn";
			}
			Piece copy = new Piece(original.isFire(), thisBoard, x, y, type);
			//officially delete it.
			pieces[x][y] = null;
			return copy; 		
		}
	}

	//

	public boolean canEndTurn(){
		//be able to end a turn, a piece must captured 
		for(int i=0; i<N; i++){
    		for(int j=0; j<N; j++){
    			if(pieces[i][j] != null){
    				if(pieces[i][j].hasCaptured()){		
    					// pieces[i][j].doneCapturing();
    					return true;	
    				}	
    			}
    			
    		}
    	}		
		
    	//or has moved
		if(isMoved){
			return true;
		}
		//or performed a capture.
		
		return false;
	}

	public void endTurn(){
		//Handles switching of players and anything else that should happen at the end of a turn.
		for(int i=0; i<N; i++){
    		for(int j=0; j<N; j++){
    			if(pieces[i][j] != null){
    				if(pieces[i][j].hasCaptured()){		
    					pieces[i][j].doneCapturing();
    						
    				}	
    			}
    		}
    	}	

		isRedTurn = !isRedTurn;	
		isMoved = false;
		setToFalse();
		
		//call setToFault so that there is no any white space!
	}


	public String winner(){
		boolean isTie = true;
		boolean isFireExist = false;
		boolean isWaterExist = false;
		//default to false 
		for(int i=0; i<N; i++){
	    	for(int j=0; j<N; j++){
	    		if(pieces[i][j] != null){
	    			isTie = false;
	    			if(pieces[i][j].isFire()){
	    				isFireExist = true;
	    			}
	    			else{
	    				isWaterExist = true;
	    			}
	    		}
	    	}
	    }
	    if(isTie){
	    	return "No one";
	    }
	    //there is only one or more type of chess left
	    else if(isFireExist && !isWaterExist){
	    	return "Fire";
	    }

	    else if(!isFireExist && isWaterExist){
	    	return "Water";
	    }

	    //the gama is not over yet.
	    else if(isFireExist && isWaterExist){
	    	return null;
	    }

	    //the following line seems useless..
	    return null;
	}



    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //if the square gets clicked and there's somthing there
                //if ( hasSelected[i][j] && pieces[i][j] != null) {
                if ( hasSelectedPieces[i][j] ) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                //reset
                         
            }
        }
    }

    private String getImg(Piece p){
    	//Fire Case
    	if ( p.isFire() ){
    		//fire bomb
    		if( p.isBomb() ){
    			if( p.isKing() )
    				return "img/bomb-fire-crowned.png";
				return "img/bomb-fire.png";	
    		}

    		//fire shield
	    	else if(p.isShield()){
	    		if(p.isKing())
	    			return "img/shield-fire-crowned";
	    		return "img/shield-fire.png";
	    	}

	    	// fire pawn
	    	else{
	    		if( p.isKing() )
	    			return "img/pawn-fire-crowned.png";
	    		return "img/pawn-fire.png";
	    	}
    	}

    	//Water Case
    	else{
    		if( p.isBomb() ){
    			if( p.isKing() )
    				return "img/bomb-water-crowned.png";
				return "img/bomb-water.png";	
    		}

    		//fire shield
	    	else if(p.isShield()){
	    		if(p.isKing())
	    			return "img/shield-water-crowned";
	    		return "img/shield-water.png";
	    	}

	    	// fire pawn
	    	else{
	    		if( p.isKing() )
	    			return "img/pawn-water-crowned.png";
	    		return "img/pawn-water.png";
	    	}
    	}
    }

    private  void drawChess(int N){
    	for(int i=0; i<N; i++){
    		for(int j=0; j<N; j++){
    			if(pieces[i][j] != null){
    				StdDrawPlus.picture(i + .5, j + .5, getImg(pieces[i][j]), 1, 1);
    			}
    		}
    	}
    }

    // this is to make sure there is only one white square in each turn
    private  void setToFalse(){
    	for(int i = 0; i <N; i++){
    		for(int j = 0; j<N; j++){
    			//hasSelected[i][j] = false;
    			hasSelectedPieces[i][j] = false;
    		}
    	}
    }

    //change to non-static, so it can call other non-static functions.
    public static void main(String args[]) {
     	//In your Board class, the main method starts a new game of Checkers61b in GUI mode and doesn’t return until the game is over. 
    
	 	int N = 8;
	 	//hasSelectedPieces  = new boolean[N][N];
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new Piece[N][N];
        Board thisBoard = new Board(false);
        // System.out.println(thisBoard + "asdfasdfasdf");
        //hasSelected = new boolean[N][N];
        // hasSelectedPieces = new boolean[N][N];

        while(true) {
            thisBoard.drawBoard(N);
            if(! thisBoard.shouldBeEmpty)
            	thisBoard.drawChess(N);    
            
            //consider about multi-capturing
            if (StdDrawPlus.mousePressed()) {
	            double x = StdDrawPlus.mouseX();
	            double y = StdDrawPlus.mouseY();
	            //if there is a piece at x,y
	          
	            // if(thisBoard.pieces[(int) x][(int) y] != null){
	            // 	// thisBoard.setToFalse();
	            // 	// mostRecentlySelected = pieces[(int) x][(int) y]; 
	          		// thisBoard.hasSelectedPieces[(int) x][(int) y] = true;
	            // }

	            // if(thisBoard.canSelect( (int) x, (int) y )){
            	// 	thisBoard.select((int) x, (int) y);
            	// }

	         
	            if(thisBoard.canSelect( (int) x, (int) y )){
            	
	          		thisBoard.hasSelectedPieces[(int) x][(int) y] = true;
            		thisBoard.select((int) x, (int) y);
            	}

	     		//hasSelected[(int) x][(int) y] = true;
            	
            }
        	if(StdDrawPlus.isSpacePressed()) {
        		if(thisBoard.canEndTurn()){		
        			thisBoard.endTurn();
		        	if(thisBoard.winner() != null){
		        		thisBoard.winner();
		        	}
        		}
        	}

            	
           

            

            //winner();

            StdDrawPlus.show(50);
        	}
        
        }
}

