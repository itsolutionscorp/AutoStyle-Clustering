/* @author Alice Wang
*
*/
public class Board{

	// private boolean shouldBeEmpty;
	private Piece [][] pieces = new Piece[8][8];
	private int turn = 0;
	private Piece selected = null;
	private int xSelected;
	private int ySelected;
	private int ifMoved;

	/* starts a GUI supported version of the game. */
	public static void main(String args[]){
		Board checkers = new Board(false);
					while(true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (x < 8 && y <8){
                	if(checkers.canSelect(x,y)){
                		checkers.select(x, y);
                	}
                checkers.drawBoard(8);
            	}
            }
            else if (StdDrawPlus.isSpacePressed()){
            	checkers.endTurn();
            }            
            StdDrawPlus.show(150);
		// for (int x = 0; x<8; x++){
		// 		for (int y = 0; y<8; y++){
		// 			if(checkers.pieces[x][y] != null){
		// 				counter+=1;
		// 			}
		// 		}
		// 	}
		// 	if(counter != 0){
		// 		System.out.println(checkers.winner());
		// 	}
            if(checkers.winner()!= null){
            	System.out.println(checkers.winner());
       
            }
        }

	}


	/*  If shouldBeEmpty is true, initializes an empty Board. 
	 *  Otherwise, initializes a Board with the default configuration.*/
	public Board (boolean shouldBeEmpty){
		// this.shouldBeEmpty = shouldBeEmpty;

		if (shouldBeEmpty == true){
			drawEmptyBoard(8);
		}
		else {
			for (int x = 0; x<8; x++){
				for (int y = 0; y<8; y++){
					if (y == 0 && x%2 == 0){
						pieces[x][y] = new Piece(true, this, x, y, "Pawn");
					}
					else if (y == 1 && x%2 != 0){
						pieces[x][y] = new Piece(true, this, x, y, "Shield");
					}
					else if (y == 2 && x%2 == 0){
						pieces[x][y] = new Piece(true, this, x, y, "Bomb");
					}
					else if (y == 7 && x%2 != 0){
						pieces[x][y] = new Piece(false, this, x, y, "Pawn");
					}
					else if (y == 6 && x%2 == 0){
						pieces[x][y] = new Piece(false, this, x, y, "Shield");
					}
					else if (y == 5 && x%2 != 0){
						pieces[x][y] = new Piece(false, this, x, y, "Bomb");
					}
				}
			}
			drawBoard(8);

		}
	}

	private void drawEmptyBoard(int N) {
	    StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.PINK);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
            }
        }
    }

    private void drawBoard(int N) {

    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        String imgType;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
    	    	if ((i + j) % 2 == 0) {
    	    		StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
    	    	} else { 
    	    		StdDrawPlus.setPenColor(StdDrawPlus.PINK);
    	    	}
            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire() == false) {
                		if (pieces[i][j].isShield() == true){
							if (pieces[i][j].isKing() == true){
                				imgType = "img/shield-water-crowned.png";
								StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                			}
                			imgType = "img/shield-water.png";
							StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                		}
                		else if (pieces[i][j].isBomb() == true){
							if (pieces[i][j].isKing() == true){
                				imgType = "img/bomb-water-crowned.png";
								StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                			}
                			imgType = "img/bomb-water.png";
							StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                		}
                		else {
							if (pieces[i][j].isKing() == true){
                				imgType = "img/pawn-water-crowned.png";
								StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                			}
                			imgType = "img/pawn-water.png";
							StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                		}
                	}
                	else {
                		if (pieces[i][j].isShield() == true){
							if (pieces[i][j].isKing() == true){
                				imgType = "img/shield-fire-crowned.png";
								StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                			}
                			imgType = "img/shield-fire.png";
							StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                		}
                		else if (pieces[i][j].isBomb()== true){
							if (pieces[i][j].isKing() == true){
                				imgType = "img/bomb-fire-crowned.png";
								StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                			}
                			imgType = "img/bomb-fire.png";
							StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                		}
                		else {
							if (pieces[i][j].isKing() == true){
                				imgType = "img/pawn-fire-crowned.png";
								StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
                			}
                			imgType = "img/pawn-fire.png";
							StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);

                	}
                }
            }
        }
    }
}
    
   private void drawAgain(int i, int j){
   		String imgType;
   		if(pieces[i][j] == null){
   			StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
        	StdDrawPlus.filledSquare(i+ .5, j+ .5, .5);
   		}
   		else if (pieces[i][j] == null && validMove(xSelected,ySelected,i,j)){
   			drawAgain(i,j);
   		}
   		else if (pieces[i][j] == null && validMove(xSelected,ySelected,i,j) != true){
	return;
	}
   		else {

               if (pieces[i][j].isFire() == false) {
        		if (pieces[i][j].isShield() == true){
					if (pieces[i][j].isKing() == true){
        				imgType = "img/shield-water-crowned.png";
						StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        			}
        			imgType = "img/shield-water.png";
					StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        		}
        		else if (pieces[i][j].isBomb() == true){
					if (pieces[i][j].isKing() == true){
        				imgType = "img/bomb-water-crowned.png";
						StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        			}
        			imgType = "img/bomb-water.png";
					StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        		}
        		else {
					if (pieces[i][j].isKing() == true){
        				imgType = "img/pawn-water-crowned.png";
						StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        			}
        			imgType = "img/pawn-water.png";
					StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        		}
        	}
        	else {
        		if (pieces[i][j].isShield() == true){
					if (pieces[i][j].isKing() == true){
        				imgType = "img/shield-fire-crowned.png";
						StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        			}
        			imgType = "img/shield-fire.png";
					StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        		}
        		else if (pieces[i][j].isBomb()== true){
					if (pieces[i][j].isKing() == true){
        				imgType = "img/bomb-fire-crowned.png";
						StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        			}
        			imgType = "img/bomb-fire.png";
					StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        		}
        		else {
					if (pieces[i][j].isKing() == true){
        				imgType = "img/pawn-fire-crowned.png";
						StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);
        			}
        			imgType = "img/pawn-fire.png";
					StdDrawPlus.picture(i + .5, j + .5, imgType, 1, 1);

        	}
        }
    }
   }

	/*	Gets the piece at position (x, y) on the board */
	public Piece pieceAt(int x, int y){
		if (x<8 && y<8 && pieces[x][y] != null){
			return pieces[x][y];
		}
		else {
		return null;
		}
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	 * or capture to (xf, yf) in a valid fashion compatible with the rules. */
private boolean validMove(int xi, int yi, int xf, int yf) {
  	if ((xf + yf) % 2 == 0) {
   		if (pieces[xi][yi] != null) {
    		if (pieces[xf][yf] == null) {
     			if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {
      				if (pieces[xi][yi].isKing() == true) {
       					return true;
      					}
      				else if ((pieces[xi][yi].isFire() == true) && (xi != xf) && (yi < yf)) {
       					return true;
      					}
      				else if ((pieces[xi][yi].isFire() == false) && (xi != xf) && (yi > yf)) {
       					return true;
     					}
      				else {
      					return false;
      					}
     				}
     			else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
      				if (pieces[(xi + xf) / 2][(yi + yf) / 2] != null) {
       					if (pieces[xi][yi].isFire() != pieces[(xi + xf) / 2][(yi + yf) / 2].isFire()) {
        					if (pieces[xi][yi].isKing() == true) {
         						return true;
        						}
        					else if ((pieces[xi][yi].isFire() == true) && (xi != xf) && (yi < yf)) {
         						return true;
        						}
        					else if ((pieces[xi][yi].isFire() == false) && (xi != xf) && (yi > yf)) {
         						return true;
        						}
        						else {
        							return false;
        						}    
      						}
     					}
     			else {
     				return false;
     				}
    			}
   			}
		}
	}
   return false;   
 }


	/* Returns true if there is a piece the piece at (x, y) 
	 * and it can be selected. */

public boolean canSelect(int x, int y){
	if ((x + y) % 2 == 0 && pieces[x][y] != null && ifMoved < 1){
		if(selected == null && pieces[x][y] != null
			&& pieces[x][y].side()== turn){
			return true;
		}
		else if(selected != null ){
			return true;
		}
		else {
			return false;
		}
	}
	else if (selected != null && pieces[x][y] == selected &&ifMoved == 1 && selected.hasCaptured() == true
			&& validMove(xSelected, ySelected, x, y)== true){
		return true;
	}
	else if ((x + y) % 2 == 0 && pieces[x][y] == null
			&& selected != null && ifMoved <1 ){
		if(validMove(xSelected, ySelected, x, y) == true){
			return true;
		}
		else {
			return false;
		}
	}
	else if((x + y) % 2 == 0 && pieces[x][y] == null
			&& selected != null && ifMoved ==1 && validMove(xSelected, ySelected, x, y)==true){
		if(selected.hasCaptured()== true){
			return true;
		}
		else{
			return false;
		}
	}
	else {
	return false;
	}
}

	/* Selects the piece at (x, y) if possible. */
public void select(int x, int y){
	if(selected == null && (x + y) % 2 == 0 && pieces[x][y] == null){
		return;
	}
	else if(selected == null && (x + y) % 2 == 0 && pieces[x][y].side() == turn){
	    StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
    	StdDrawPlus.filledSquare(x+ .5, y + .5, .5);
    	drawAgain(x, y);

            StdDrawPlus.show(150);
    	selected = pieces[x][y];
    	xSelected = x;
    	ySelected = y;
	}
	else if(selected != null && pieces[x][y] != null && pieces[x][y].isFire() == selected.isFire()
					&& ifMoved<2){
		StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
       		StdDrawPlus.filledSquare(x+ .5, y + .5, .5);
			drawAgain(x, y);

            StdDrawPlus.show(150);
        	StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
        	StdDrawPlus.filledSquare(xSelected+ .5, ySelected + .5, .5);
        	drawAgain(xSelected, ySelected);

		    selected = pieces[x][y];
		    xSelected = x;
		    ySelected = y;
	}
	else if(selected != null && pieces[x][y] == null && (x + y) % 2 == 0 &&
			validMove(xSelected, ySelected, x, y) == true && ifMoved <2){
		
		StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
    	StdDrawPlus.filledSquare(xSelected+ .5, ySelected + .5, .5);
    	pieces[xSelected][ySelected].move(x,y);
	    StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
   		StdDrawPlus.filledSquare(x+ .5, y + .5, .5);
		drawAgain(x,y);

            StdDrawPlus.show(150);
    	
    	selected = pieces[x][y];
    	xSelected = x;
    	ySelected = y;
    	ifMoved+=1;
	}
	else{
		return;
	}

	// System.out.println(selected );
}

	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, 
	 * does nothing. If p already exists in the current Board, 
	 * first removes it from its initial position. 
	 * If another piece already exists at 
	 * (x, y), p will replace that piece. */
	public void place(Piece p, int x, int y){
		Piece pieceRemoved;
		if (x>=8 || y>=8 || p == null || x<0 || y<0){
			return;
		}
		else if (p != null){
			pieceRemoved = p;
			if (pieces[x][y] != null){
				remove(x,y);
				pieces[x][y] = pieceRemoved;
			}
			else {
				pieces[x][y] = pieceRemoved;
			}
		}
		//   if (x >= 0 && y >= 0 && x < 8 && x < 8 && p != null) {
  //  for (int i = 0; i < 8; i += 1) {
  //   for (int j = 0; j < 8; j += 1) {
  //    if (pieceAt(i, j) == p) {
  //     remove(i, j);
  //    }
  //   }
  //  }
  //  if (pieceAt(x, y) != null) {
  //   remove(x, y);
  //  }
  //  pieces[x][y] = p;
  // } return;

	}

	/* Executes a remove. Returns the piece that was removed. */
	public Piece remove(int x, int y){
		if (x>=8 || y>=8 || x<0 || y<0){
			System.out.println("Position out of bounds.");
			return null; 
		}
		else if (pieces[x][y]==null){
			System.out.println("No piece at position.");
			return null;
		}
		else {
			pieces[x][y]= null;
			Piece removedPiece = pieces[x][y];
			return removedPiece;
		}
		// Piece removed;

  // // (x, y) out of bounds.
  // if (x < 0 || y < 0 || x > 7 || y > 7) {
  //  System.out.println("Out of bounds.");
  //  return null;
  // }

  // // No piece at (x, y).
  // else if (set[x][y] == null) {
  //  System.out.println(x + ", " + y);
  //  System.out.println("No piece available.");
  //  return null;
  // }

  // // Piece at (x, y) exists.
  // else {
  //  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
  //  StdDrawPlus.filledSquare(x + .5, y + .5, .5);

  //  removed = pieceAt(x, y);
  //  set[x][y] = null;
  //  return removed;
  // }
	}

	/* Returns whether or not the the current player can end their turn. */
	public boolean canEndTurn(){
		if (ifMoved ==0){
			return false;
		}
		else if(ifMoved ==1){
			if (selected == null){
				return true;
			}
			else if(selected.hasCaptured() == true && canSelect(xSelected,ySelected) == true){
				return false;
			}
			else if(selected.hasCaptured() == true && canSelect(xSelected,ySelected) != true){
				return true;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	/*  Called at the end of each turn.*/
	public void endTurn(){
		System.out.println(ifMoved);
		if(selected!= null){
		System.out.println(selected.hasCaptured());
		}
		if (canEndTurn()){
		StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
    	StdDrawPlus.filledSquare(xSelected+ .5, ySelected + .5, .5);
    	drawAgain(xSelected,ySelected);
    		if(selected!= null){
				if(turn == 0){
					if(selected.hasCaptured() == true && canSelect(xSelected,ySelected) == true){
					turn = 0;
					ifMoved = 1;
					select(xSelected,ySelected);
					}
				else {
					turn = 1;
					ifMoved =0;
					selected = null;
					System.out.println("Turn: Water");
					}
				}
				else {
					if(selected.hasCaptured() == true && canSelect(xSelected,ySelected) == true){
					turn = 1;
					ifMoved = 1;
					select(xSelected,ySelected);
					}
				else {
					turn = 0;
					ifMoved =0;

					selected = null;
					System.out.println("Turn: Fire");
					}
				}
    		}
    		else {
    			if(turn == 0){
    				turn = 1;
    				ifMoved = 0;
    			}
    			else {
    				turn = 0;
    				ifMoved = 0;
    			}
    		}
		}
		return;
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" 
	 * (tie / no pieces on the board), or null if the game is not yet over.*/
	public String winner(){
		int countFire = 0;
		int countWater = 0;
		for (int x = 0; x<8; x++){
				for (int y = 0; y<8; y++){
					if(pieces[x][y] != null){
						if (pieces[x][y].isFire() == true){
							countFire +=1;
						}
						else {
							countWater+=1;
						}
					}
				}
			}
		if (countWater == 0 && countFire > countWater) {
			return "Fire";
		}
		else if (countFire == 0 && countWater > countFire){
			return "Water";
		}
		else if (countFire == 0 && countWater == 0){
			return "No one";
		}
		else {
			return null;
		}
	}

}








