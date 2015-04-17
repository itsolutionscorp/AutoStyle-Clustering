//@credits
//to my based GSI Brendan for helping me get the methods right so autograder won't screw me
//to my roommate austin (not even taking 61b but did java in high school) for helping me on understanding
//the logic behind valid move, (eg drawing a 8x8 board and showing what were valid moves and what weren't)
//and to elegant eric for helping me figure out to return a string winner rather than print

public class Board{
	
	private int currentX = -1; //initialized to -1, -1
	private int currentY = -1;
	private boolean hasSelection = false; 
	private Piece[][] arrayOfPieces = new Piece[8][8]; //checkerboard itself
	private int turn = 0; //if player0 turn, 0; if player1 turn, 1;
	private boolean whoseTurn = true;
	private boolean moved = false; 
	private boolean isNotOver = true;
	private Piece temporaryPiece; //do coordinates
	private boolean capturedState = false;
	private String winner = "No one";

	public static void main(String arg[]) 
	{
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
 		Board b = new Board(false);

 		while(b.isNotOver) {
            b.drawBoard();

            if (StdDrawPlus.mousePressed()) {
	            int x = (int)(StdDrawPlus.mouseX());
	            int y = (int)(StdDrawPlus.mouseY());
		        if (b.canSelect(x, y)){
			        b.select(x, y); 
			    }
	       	b.drawBoard();
	        }
            if (StdDrawPlus.isSpacePressed()){
        		if (b.canEndTurn()){
	        		b.endTurn();
	        		System.out.println("Turn has ended.");
	        		}
	        	}
	        StdDrawPlus.show(100); 
	    }     
    }

    public Board(boolean shouldBeEmpty)
	{
		if (shouldBeEmpty == true){
			return;
		}
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((j == 0) && (i % 2 == 0)) {
					arrayOfPieces[i][j] = new Piece(true, this, i, j, "pawn");
				}
				if ((j == 1) && (i % 2 == 1)){
					arrayOfPieces[i][j] = new Piece(true, this, i, j, "shield");
				}
				if ((j == 2) && (i % 2 == 0)){
					arrayOfPieces[i][j] = new Piece(true, this, i, j, "bomb");
				}
				if ((j == 5) && (i % 2 == 1)){
					arrayOfPieces[i][j] = new Piece(false, this, i, j, "bomb");
				}
				if ((j == 6) && (i % 2 == 0)){
					arrayOfPieces[i][j] = new Piece(false, this, i, j, "shield");
				}
				if ((j == 7) && (i % 2 == 1)){
					arrayOfPieces[i][j] = new Piece(false, this, i, j, "pawn");
				}
			}	
		}
	}

	public Piece pieceAt(int x, int y)
	{
		if (errorBound(x, y)) {
			return arrayOfPieces[x][y];
		}
		else{
			return null;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{	
		//some documentation: regular movement is done at the end, king capturing is done with both cases, one for fire, one for water, both hard coded in
		//all this geometry is done here.
		//strictly geometric
		int deltaY = yf - yi;
		int deltaX = xf - xi;
		if (errorBound(xf, yf) && (errorBound(xi, yi))) {
			if (arrayOfPieces[xf][yf] == null){
				if (arrayOfPieces[xi][yi] == null){
						return false;
					} //king exception for moving
					//fire
					if ((arrayOfPieces[xi][yi].isKing()) && (arrayOfPieces[xi][yi].side() == 0)) {
						if ((Math.abs(deltaY) == 1) && (moved == false)){
							if (((xi + 1) == xf) || (xi - 1 == xf)){
								return true;
							}
						}
					//king capturing
						if ((xi + 2 == xf) && (yi + 2 == yf)){ //checking if piece it's capturing is not the same side
							if (arrayOfPieces[xi+1][yi+1] != null && (arrayOfPieces[xi+1][yi+1].side() != arrayOfPieces[xi][yi].side())) {
								return true;
							}
						}
						if ((xi - 2 == xf) && (yi + 2 == yf)){
							if (arrayOfPieces[xi-1][yi+1] != null && (arrayOfPieces[xi-1][yi+1].side() != arrayOfPieces[xi][yi].side())){
								return true;
							}
						}
						if ((xi + 2 == xf) && (yi - 2 == yf)){ 
							if (arrayOfPieces[xi+1][yi-1] != null && (arrayOfPieces[xi+1][yi-1].side() != arrayOfPieces[xi][yi].side())) {
								return true;
							}
						}
						if ((xi - 2 == xf) && (yi - 2 == yf)){ //checking if piece it's capturing is not the same side
							if (arrayOfPieces[xi-1][yi-1] != null && (arrayOfPieces[xi-1][yi-1].side() != arrayOfPieces[xi][yi].side())) {
								return true;
							}
						}
					}
					//water
					else{
							if ((arrayOfPieces[xi][yi].isKing()) && (arrayOfPieces[xi][yi].side() == 1)) {
								if ((Math.abs(deltaY) == 1) && (moved == false)){
									if (((xi + 1) == xf) || (xi - 1 == xf)){
										return true;
									}
								}
							//king capturing
								if ((xi + 2 == xf) && (yi + 2 == yf)){ //checking if piece it's capturing is not the same side
									if (arrayOfPieces[xi+1][yi+1] != null && (arrayOfPieces[xi+1][yi+1].side() != arrayOfPieces[xi][yi].side())) {
										return true;
									}
								}
								if ((xi - 2 == xf) && (yi + 2 == yf)){
									if (arrayOfPieces[xi-1][yi+1] != null && (arrayOfPieces[xi-1][yi+1].side() != arrayOfPieces[xi][yi].side())){
										return true;
									}
								}
								if ((xi + 2 == xf) && (yi - 2 == yf)){ 
									if (arrayOfPieces[xi+1][yi-1] != null && (arrayOfPieces[xi+1][yi-1].side() != arrayOfPieces[xi][yi].side())) {
										return true;
									}
								}
								if ((xi - 2 == xf) && (yi - 2 == yf)){ //checking if piece it's capturing is not the same side
									if (arrayOfPieces[xi-1][yi-1] != null && (arrayOfPieces[xi-1][yi-1].side() != arrayOfPieces[xi][yi].side())) {
										return true;
									}
								}
							}
						}
					//basic movement checking
					if (((arrayOfPieces[xi][yi].side() == 0) && (yi + 1) == yf)) {
						if (((xi + 1) == xf) || (xi - 1 == xf)){
							return true;
						}
					}
					if (((arrayOfPieces[xi][yi].side() == 1) && (yi - 1) == yf)) { //water
						if (((xi + 1) == xf) || (xi - 1 == xf)){
							return true;
						}
					}
					//basic capturing
					if (arrayOfPieces[xi][yi].side() == 0){
						if ((xi - 2 == xf) && (yi + 2 == yf)){
							if (arrayOfPieces[xi-1][yi+1] != null && (arrayOfPieces[xi][yi].side() != arrayOfPieces[xi-1][yi+1].side())){
								return true;
							}
						}
						if ((xi + 2 == xf) && (yi + 2 == yf)){
							if (arrayOfPieces[xi+1][yi+1] != null && (arrayOfPieces[xi][yi].side() != arrayOfPieces[xi+1][yi+1].side())){
								return true;
							}
						}
					}
					if(arrayOfPieces[xi][yi].side() == 1){
						if ((xi + 2 == xf) && (yi - 2 == yf)){
							if (arrayOfPieces[xi+1][yi-1] != null && (arrayOfPieces[xi][yi].side() != arrayOfPieces[xi+1][yi-1].side())){
								return true;
							}
						}
						if ((xi - 2 == xf) && (yi - 2 == yf)){
							if (arrayOfPieces[xi-1][yi-1] != null && (arrayOfPieces[xi][yi].side() != arrayOfPieces[xi-1][yi-1].side())){
								return true;
							}
						}
					}
				}
			}
		return false;
	}

	public boolean canSelect(int x, int y)
	{/* don't forget
	During this turn, the player has selected a Piece, captured, 
	and has selected another valid capture destination. 
	When performing multi-captures, you should only select the active piece once; 
	all other selections should be valid destination points. */

	// 	if ((errorBound(x, y) == true)) {
	// 		if ((!hasSelection) && (arrayOfPieces[x][y] != null)){
	// 			if ((arrayOfPieces[x][y].side() == turn)){
	// 				return true;
	// 			}
	// 		}
	// 		if((hasSelection) && (arrayOfPieces[x][y] != null) && (moved == false)){
	// 			if ((arrayOfPieces[x][y].side() == turn)){
	// 				return true;
	// 			}
	// 		}
	// 		else if (validMove(currentX, currentY, x, y)) {
	// 			return true;
	// 		}
	// 		else{
	// 			if ((arrayOfPieces[x][y] != null) && (moved == false)){
	// 				if ((arrayOfPieces[x][y].side() == turn)){
	// 					return true;
	// 				}
	// 			}
	// 			if ((arrayOfPieces[x][y] != null) && (validMove(currentX, currentY, x, y))) {
	// 				if ((arrayOfPieces[x][y].side() == turn)){
	// 					return true;
	// 				}
	// 			}
	// 			//captured condition
	// 			else if ((moved == true) && arrayOfPieces[currentX][currentY] != null && arrayOfPieces[currentX][currentY].hasCaptured()){
	// 				if (Math.abs(currentY - y) == 2){
	// 					if (validMove(currentX, currentY, x, y)){
	// 						return true;
	// 					}
	// 				}
	// 			}

	// 		}
	// 	}
	// 	return false;
	// }
		if ((errorBound(x, y) == true)) {
			if ((!hasSelection) && (arrayOfPieces[x][y] != null)){
				if ((arrayOfPieces[x][y].side() == turn)){
					return true;
				}
			}
			else if((hasSelection) && (arrayOfPieces[x][y] != null) && (moved == false)){
				if ((arrayOfPieces[x][y].side() == turn)){
					return true;
				}
			}
			//empty square selection 
			if ((hasSelection) && (moved == false)){
				if (validMove(currentX, currentY, x, y)){
					return true;
				}
			}
			if ((moved == true) && arrayOfPieces[currentX][currentY] != null && arrayOfPieces[currentX][currentY].hasCaptured()){
				if (Math.abs(currentY - y) == 2){
					if (validMove(currentX, currentY, x, y)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) 
	{ 
		//1. to capture must be selected
		//2. if a square is selected with a piece, it is being made ready for movement
		//3. if it is an empty square, move it to that spot


		//note to self, don't forget to do bomb stuff
		// if (!hasSelection){
		// 	if (arrayOfPieces[x][y] != null){
		// 		currentX = x;
		// 		currentY = y;
		// 		hasSelection = true;
		// 	}
		// }
		// else{
		if(arrayOfPieces[x][y] != null){
			currentX = x; 
			currentY = y;
			hasSelection = true; 
		}
		else{
			arrayOfPieces[currentX][currentY].move(x, y);
			currentX = x;
			currentY = y;
			moved = true;
		}
	}
	// }	

	public void place (Piece p, int x, int y)
	{
		if (errorBound(x, y) == true){
			if (p != null){
				remove(x,y);
				arrayOfPieces[x][y] = p;
			}
			arrayOfPieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y)
	{
		Piece temporaryHolder = arrayOfPieces[x][y];
		if (errorBound(x, y) == true) {
			arrayOfPieces[x][y] = null;
			return temporaryHolder;
		}
		if (arrayOfPieces[x][y] == null){
			System.out.println("There is no piece selected.");
			return null;
		}
		else{
			System.out.println("Selection is out of bounds.");
			return null;
		}
	}

	public boolean canEndTurn()
	{
		if (moved){
			return true;
		}
		else{
			return false;
		}
	}

	public void endTurn()
	{
		if (canEndTurn() == true){
			playerTurn(turn);
			moved = false;
			whoseTurn = !whoseTurn;
			hasSelection = false;
			if (arrayOfPieces[currentX][currentY] != null){
				if (arrayOfPieces[currentX][currentY].isBomb() == false){
				arrayOfPieces[currentX][currentY].doneCapturing();
				}
			}
			currentX = -1;
			currentY = -1;
		}
		if (isNotOver){
			winner();
		}
		else{
			return;
		}
	}

	public String winner()
	{	
		countPieces();
		return winner;
	}

	private void countPieces()
	{
		int countT = 0;
		int countCT = 0;
		int defusedCount = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (arrayOfPieces[i][j] == null){
            		defusedCount++;
            	} 
            	else if (arrayOfPieces[i][j].isFire() == true){
            		countT++;
            	}
            	else if (arrayOfPieces[i][j].isFire() == false){
            		countCT++;
            	}
			}
		}
		if ((countT == 0) && (countCT == 0)) {
			isNotOver = false;
			winner = "No one";
		} 
		else if ((countT == 0)){
			winner = "Water";
			isNotOver = false;
		}
		else if ((countCT == 0)){
			isNotOver = false;
			winner = "Fire";
		}
		else{
			winner = null;
		}
	}

	private boolean errorBound(int x, int y)
	{
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)){
			return true;
		}
		return false;
	}

	private int playerTurn(int turn)
	{
		this.turn = 1 - turn;
		return this.turn;
	}

	private void drawBoard() 
	{
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if ((hasSelection) && (i == currentX) && (j == currentY) && (arrayOfPieces[currentX][currentY] != null)) { 
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            		
            	}  
	            else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        
	                    if (arrayOfPieces[i][j] != null){

	                		if (arrayOfPieces[i][j].isBomb()) { //for bomb type
	                			if(arrayOfPieces[i][j].side() == 0){
	                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
			                		if (arrayOfPieces[i][j].isKing() ) {
			                			StdDrawPlus.picture(i  + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
			                		}
			                	}
		                		if (arrayOfPieces[i][j].side() == 1){
		                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			                		if (arrayOfPieces[i][j].isKing()) {
			                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
			                		}
			                	}
		                	}
		                	if (arrayOfPieces[i][j].isShield()) { //for shield type
	                			if(arrayOfPieces[i][j].side() == 0){
	                					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		                		 	if (arrayOfPieces[i][j].isKing()) {
		                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		                			}
		                		}
		                		if (arrayOfPieces[i][j].side() == 1){
		                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		                			if (arrayOfPieces[i][j].isKing()) {
		                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		                			}
		                		}//else it should be a pawn, which is all of this
		                	}

		                	if ((arrayOfPieces[i][j].isShield() != true ) && (arrayOfPieces[i][j].isBomb() != true)){
	                			if(arrayOfPieces[i][j].side() == 0){
	                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
			                		if (arrayOfPieces[i][j].isKing()) {
			                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
			                		}
			                	}
		                		if (arrayOfPieces[i][j].side() == 1){
		                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			                		if (arrayOfPieces[i][j].isKing()) {
			                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
			                		}
			                	}
		                	}
	                	}	
	                }
	            }
	        }
}




