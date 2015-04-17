//Final Submission :) 5:00pm

public class Board{
	private static int S = 8;
	private Piece[][] pieces = new Piece[S][S];
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private boolean cap = false;
	private int oldX, oldY;
	private int turn;
	private int fireCount = 0;
	private int  waterCount = 0;
	// private static boolean[][] boolpieces;
	private Piece storedPiece = null;



	public static void main(String[] args) { // DONE!!!
		

		Board b = new Board(false); // initialize a new board
		StdDrawPlus.setXscale(0, S);
        StdDrawPlus.setYscale(0, S);
        // boolpieces = new boolean[S][S];

		while (b.winner() == null){ //keep lioping as long as winner is null
			b.drawBoard(8);
			b.drawPieces();
            if (StdDrawPlus.mousePressed()) { // if the mouse is pressed:
                double x = StdDrawPlus.mouseX(); //get mouse x
                double y = StdDrawPlus.mouseY(); // get mouse y
                
            if (b.canSelect((int) x, (int) y)) { //if you can select them..
            	// System.out.println("sdaslkjdfksl;fs");
            	b.select((int) x, (int) y);// call select on ints
            	} 
             	// b.drawBoard(S); //re-draw gui

            }
            if(StdDrawPlus.isSpacePressed()){ // if the space bar is pressed
            	if (b.canEndTurn()) {
            		b.endTurn();
            		
            	}
            }
        StdDrawPlus.show(100);
        }


    }


	public Board (boolean shouldBeEmpty){ 
			if (shouldBeEmpty == false){
				//initialize empty board
				// sbe = true;
				drawBoard(8);
				piecesLauncher();

			}
			else{
				// drawBoard(8);
				//initialize board with default config
			}

		}

    private void piecesLauncher(){ //DONE!!
    	for (int column = 0; column < 8; column++){
    		for (int row = 0; row < 8; row++){
 				if ((row == 0) && (column % 2 == 0)){
 			    	pieces[column][row] = new Piece(true, this, column, row, "pawn");
 			    }
 				if ((row == 1) && (column % 2 == 1)){
 				    pieces[column][row] = new Piece(true, this, column, row, "shield");
 				}
 				if ((row == 2) && (column % 2 == 0)){
 					pieces[column][row] = new Piece(true, this, column, row, "bomb");
 				}
 				if ((row == 7) && (column % 2 == 1)){
 			     pieces[column][row] = new Piece(false, this, column, row, "pawn");
 			    }
 				if ((row == 6) && (column % 2 == 0)){
 				    pieces[column][row] = new Piece(false, this, column, row, "shield");
 				}
 				if ((row == 5) && (column % 2 == 1)){
 				pieces[column][row] = new Piece(false, this, column, row, "bomb");
 				}
    		}
    	}
   	 }

    private void drawBoard(int N) { //DONE!!!
    
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);}
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
						                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
						                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // if (boolpieces[i][j]){
                // StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // }
            }
           }
   	 }

  	private void drawPieces(){ //DONE!!
  		for(int i = 0; i < 8; i++){
  			for (int j = 0; j < 8; j++){
  				if (pieces[i][j] != null){
	  				if (pieces[i][j].isFire()){ // now we consider fire pieces
	  					if (pieces[i][j].isBomb()){
	  						if (pieces[i][j].isKing()){
		  				  		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);

	  						}
	  						else{
	  							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	  						}
	  					}
	  					else if (pieces[i][j].isShield()){
	  						if (pieces[i][j].isKing()){
	  							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	  						}
	  						else{
	  							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	  						}
	  					}
	  					else{
	  						if (pieces[i][j].isKing()) {
	  							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	  						}
	  						else{
	  							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	  						}
	  					}
	  				}


	  				if (pieces[i][j].isFire() == false){
	  					if (pieces[i][j].isBomb()){
	  						if (pieces[i][j].isKing()){
	  							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	  						}
	  						else{
	  							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	  						}
	  					}
	  					else if (pieces[i][j].isShield()){
	  						if (pieces[i][j].isKing()){
	  							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	  						}
	  						else{
	  							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	  						}
	  					}
	  					else{
	  						if (pieces[i][j].isKing()){
	  							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	  						}
	  						else{
	  							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	  						}
	  					}
	  					}
  					}
  				}
  			}
  		}
  	
	public  Piece pieceAt(int x, int y){ //DONE!!!

			if (((x >= 8) || (x < 0)) || ((y >= 8) || (y < 0))){
				return null;
			}
			if(pieces[x][y] == null){
				return null;
			}
			return pieces[x][y];
		}


	public  void place(Piece p, int x, int y){ //DONE!!!
			if (p != null || x < 8 || x >= 0 || y < 8 || y >= 0){
				pieces[x][y] = p;
			}
		}

	private boolean validMove(int x1, int y1, int x2, int y2){ //DONE!!!
		int dY = y2-y1 ;
		int dX = x2 -x1 ;

		int midpointx = (x1 + x2) / 2;
		int midpointy = (y1 + y2) / 2;
		// int slope = Math.abs(dY/dX); 
		

		if (((x2 > 7) || (x2 < 0)) || ((y2 > 7) || (y2 < 0))){
			System.out.println("outta bounds");
			return false;
		}
		if (pieceAt(x1, y1) != null){
		if((pieceAt(x1, y1).isFire() == true) || (pieceAt(x1,y1).isKing() == true)) { //now we do the methods for a fire piece
			if (((dX == 2) || (dX == -2))  && (dY == 2)) { // hecking if it can move positions
				if (pieceAt(midpointx, midpointy) != null) { // if its null, then it can move
					if ((pieceAt(midpointx, midpointy) != null) && 
						(pieceAt(midpointx, midpointy).isFire() == false)){ //now, if its a WATER piece, then we can safely jump over it
						if (pieceAt(x2, y2) == null){
							return true;
						}
						return false;
					}
					return false;
				}
				return false;
			}
			if (((dX == 1) || (dX == -1))  && (dY == 1)){
				if (pieceAt(x2, y2) == null){
					return true;
				}
				return false;
			} 
		}

		else if((pieceAt(x1, y1).isFire() == false) || (pieceAt(x1,y1).isKing() == true)){ // shub methods for the water pieces
			if (((dX == 2) || (dX == -2)) && (dY == -2)){
				if (pieceAt(midpointx, midpointy) != null){
					if((pieceAt(midpointx, midpointy) != null) && 
					   (pieceAt(midpointx, midpointy).isFire())){
						if (pieceAt(x2, y2) == null){
							return true;
						}
						return false;
					}
					return false;
				}
				return false;
			}
			else if (((dX == 1) || (dX == -1))  && (dY == -1)){
				if (pieceAt(x2, y2) == null){
					return true;
				}
				return false;
			}
		}
	}

		return false;
	}
		
	
	public  boolean canSelect(int x, int y){ 

 
		if ((pieceAt(x, y) != null) && (pieceAt(x, y).side() == turn)){
		// if (pieceAt(x, y) != null){
			// if (pieceAt(x, y).side() == 0){// this means this is fire's turn
				// if (pieceAt(x, y).side().isFire() == true){
					if (hasSelected == false){ 
						return true;
					}  
					else if ((hasSelected == true) && (hasMoved == false)) {
						return true;
					}
				// }
				else{
					return false;
				}
			}
		if ((pieceAt(x, y) == null) && ((x + y) % 2 == 0)) {
			// if (pieceAt(x, y).side() == 0){// this means this is fire's turn
				if (((hasSelected == true) && (hasMoved == false)) && (validMove(oldX, oldY, x, y))){
					return true;
				}

				else if (((hasSelected == true) && (cap == true)) && (validMove(oldX, oldY, x, y))) {
					return true;

				}
			}
		return false;
	}


	public  void select(int x, int y){ //TO DO

		if (((storedPiece != null) && (pieceAt(x,y) == null))) {
			storedPiece.move(x,y);
			hasMoved = true;
		}

	
		else if ((storedPiece == null) || (pieceAt(x, y) != null)){

			oldY =x;
			oldX =y;
			hasSelected = true;

			storedPiece = pieceAt(x, y);
		}
	}
		



	public  Piece remove(int x, int y){ 

		int removedx = x;
		int removedy = y;
		Piece xss = pieceAt(removedx, removedy);


		if (((x > 7) || (x < 0)) || ((y > 7) || (y < 0))){
			System.out.println("Piece out of bounds.");
			return null;
		}
		else{
			if (pieceAt(x,y) == null){
				return null;
			}
			else{
				pieces[x][y] = null;
			return xss;
			}
		}
		
	}


	public  boolean canEndTurn(){ 
		if ((hasMoved == true) || (cap == true)){
			return true;
		}
		else{
			return false;
		}
	}

	public  void endTurn(){ //change player D:
		 // Called at the end of each turn. Handles switching of players and 
		 // * anything else that should happen at the end of a turn. 

		if (turn == 0) {
			turn = 1;
		}
		else{
			turn = 0;
		}
				hasSelected = false;
		hasMoved = false;
	}


	public  String winner(){ // DONE?!
		fireCount = 0;
		waterCount = 0;
		for(int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				if (pieces[x][y] != null){
					if (pieces[x][y].isFire() == true){
						fireCount += 1; //add one to the count if its a fire piece
					}
					else if (pieces[x][y].isFire() == false){
						waterCount += 1; //add one to the count if its a water piece
					}
				}
			}
		}

	
		if ((fireCount == 0) && (waterCount == 0)){
			return "No one";
		}
		else if((fireCount >= 1) && (waterCount == 0)){
			return "Fire";
		}
		else if ((fireCount == 0) && (waterCount >=1)){
			return "Water";
		}
		else if ((fireCount > 0)&&(waterCount > 0)){
			return null;
		}
		return null;
	}
}