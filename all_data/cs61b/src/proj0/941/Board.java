public class Board{

    private boolean[][] pieces;
    private boolean shouldBeEmpty;
    private Piece[][] pieceArr;
    private int N;
    private boolean firesTurn = true;
    private boolean isSelected = false;
    private boolean moved = false;
    private int csX;
    private int csY;
    private boolean hasCaptured = false;

    public Board(boolean shouldBeEmpty) {
    	 N = 8;
 		if (shouldBeEmpty == true){
 			pieceArr = new Piece[N][N];
 		}else{
            this.shouldBeEmpty = shouldBeEmpty;
           
            pieceArr = new Piece[N][N];
            for(int x = 0; x < N; x++){
                for( int y = 0; y < N; y++){    
                    int spots = (x + y) % 2;
                    if (spots == 0 && y == 0){
                        pieceArr[x][y]= new Piece(true, this, x, y, "pawn");
                    }

                    else if (spots == 0 && y == 1){
                        pieceArr[x][y] = new Piece(true, this, x, y, "shield");
                    }

                    else if (spots == 0 && y == 2) {
                        pieceArr[x][y] = new Piece(true, this, x, y, "bomb");
                    }

                    else if (spots == 0 && y == 5) {
                        pieceArr[x][y] = new Piece(false, this, x, y, "bomb");
                    }

                    else if (spots == 0 && y == 6) {
                        pieceArr[x][y] = new Piece(false, this, x, y, "shield");
                    }

                    else if (spots == 0 && y == 7) {
                        pieceArr[x][y] = new Piece(false, this, x, y, "pawn");
                    }
                    else {
                        pieceArr[x][y] = null;
                    }
                }
            } 
        }
    }
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                if ((i + j) % 2 == 0) {    
                    if (pieceArr[i][j] != null ){
                    
                        if(pieceArr[i][j].isKing() == false){
                            if (pieceArr[i][j].isFire()) {
                                if (pieceArr[i][j].isBomb() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);    
                                }
                                else if (pieceArr[i][j].isShield() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                                }else{
                                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                                }
                            } 

                                        
                            else if (pieceArr[i][j].isFire() == false) {         
                                if (pieceArr[i][j].isShield() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                                }
                                else if (pieceArr[i][j].isBomb() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);   
                                }
                            }
                        }
                        else{
                            if (pieceArr[i][j].isFire() == true) {
                                if ((pieceArr[i][j]).isBomb() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);    
                                }
                                else if ((pieceArr[i][j]).isShield() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                                }else{
                                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                                }
                            }    
                                        
                            if ((pieceArr[i][j]).isFire() == false) {         
                                if ((pieceArr[i][j]).isShield() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                                }
                                else if ((pieceArr[i][j]).isBomb() == true) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                                }
                                else{

                                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);   
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if( x > N-1 || y > N-1  || x < 0 || y < 0){
            return null;
        }
        else if (pieceArr[x][y] == null){
            return null;
        }
        else{
            return pieceArr[x][y];
        }
    }

    public boolean canSelect(int x, int y){
    	if( x > N-1 || y > N-1  || x < 0 || y < 0){
    		return false;
    	}
        if(pieceArr[x][y] != null) {
            if((pieceArr[x][y]).isFire() == firesTurn) {
                if (isSelected ==false  || (isSelected && !moved)){
                    return true;
                }
            }
        }
        else{
            if(isSelected == true && moved == false && validMove(csX, csY, x, y)){
                return true;
            }
            else if (isSelected == true && pieceArr[csX][csY].hasCaptured() && validMove(csX, csY, x, y)) {
                return true;   
            }       
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
	//First check for out of bounds in all directions
		if ( xi > N-1  || yi > N-1 || xf > N-1 || yf > N-1 || xi < 0 || yi < 0 || xf < 0 || yf < 0) {
	        return false;
	    }
	//verify there is a piece at xy initial and final is empty
	    if( pieceArr[xi][yi] == null || pieceArr[xf][yf] != null) {
	    	return false;
	    }
	//make sure the player is trying to move his pieces
	    if(pieceArr[xi][yi].isFire() != firesTurn) {
	    	return false;
	    }
	//make the variables you'll use to check for the direction you are moving in
	//this will resolve the out of bounds error because it will only call in direction
	//of final move and wont go passed it
	    int xdir = xf- xi;
	    int ydir = yf - yi;
	//make sure its moving in the right direction (fire then water)
	    if(pieceArr[xi][yi].isKing() != true) {
	    	if(pieceArr[xi][yi].isFire() && ydir < 0 ){
	    		return false;
	    	}
	    	if(!pieceArr[xi][yi].isFire() && ydir > 0 ){
	    		return false;
	    	}
	    }
	//make sure its not trying to move straight up/down/right/left
	    if(xdir == 0 || ydir == 0) {
	    	return false;
	    }
	//if its moving one space then xy diff is 1 and that spot is empty(null)

	    if(Math.abs(xdir) == 1 && Math.abs(ydir) == 1){
	    	return true;
	    }
	//this should now take into account 
		if (xdir > 0 && ydir > 0) {
			if(pieceArr[xi + 1][ yi + 1] != null && pieceArr[xi + 1][yi + 1].isFire() != firesTurn) {
				return true;
			} 		
		}
				
		if (xdir < 0 && ydir > 0) {
			if(pieceArr[xi - 1][ yi + 1] != null && pieceArr[xi - 1][yi + 1].isFire() != firesTurn) {
				return true;
			} 	
		}

		if (xdir < 0 && ydir < 0) {
			if(pieceArr[xi - 1][ yi - 1] != null && pieceArr[xi - 1][yi - 1].isFire() != firesTurn) {
				return true;
			} 
		}

		if (xdir >0 && ydir < 0){
			if(pieceArr[xi + 1][ yi - 1] != null && pieceArr[xi + 1][yi - 1].isFire() != firesTurn) {
				return true;
			} 
		}

		return false;

     
    }

    public void select(int x, int y) {
    	if(isSelected == false) {
    		csX = x;
            csY = y; 
            isSelected = true;
    	}
    	else if(isSelected){
    		if(moved){
    			if(pieceArr[csX][csY].hasCaptured() == true){
    				pieceArr[csX][csY].move(x, y);
    				csX = x;
    				csY = y;
    			}
    		}
    		else{

    			if (pieceArr[x][y] != null){
    				csX = x;
    				csY = y;

    			}
    			else{
    				pieceArr[csX][csY].move(x, y);
    				csX = x;
    				csY = y;
    				moved = true;
    			}
    		}
    	}
    }

    			
    	
   

    public void place(Piece p, int x, int y) {
        if(x > N-1 || y > N-1 || x < 0 || y < 0 || p == null) {
            return;
        }else{
            pieceArr[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if(x > N-1 || y > N-1 || x < 0 || y < 0){
            System.out.println("Error!: Out of bounds");
            return null;
        }
        else if (pieceArr[x][y] == null) {
            System.out.println("Error!: It is an empty space");
            return null;
        }
        else{
            Piece removeIt = pieceArr[x][y];
            pieceArr[x][y] = null;
            return removeIt;
        }

    }

    public boolean canEndTurn() {

    	if( isSelected == false){
    		return false;
    	}

        else if (pieceArr[csX][csY].hasCaptured() == true || moved == true ) {
            return true;
        }

        return false;

        
    }

    public void endTurn() {
        if (canEndTurn()) {

            isSelected = false;
            moved = false;
            hasCaptured = false; 
            pieceArr[csX][csY].doneCapturing();
                       
            if (firesTurn == true) {
                firesTurn = false;
            }
            else if (firesTurn == false) {
                firesTurn = true;        
            }

        }
    }

    public String winner() {
        int red = 0;
        int blue = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(pieceArr[i][j] != null){
                    if ((pieceArr[i][j]).isFire() == true) {
                        red += 1;
                    }
                    if ((pieceArr[i][j]).isFire() == false) {
                        blue += 1;
                    }
                }
            }
        }


        if (blue == 0 && red != 0) {
            return "Fire";
        }
        else if (blue != 0 && red == 0) {
            return "Water";
        }        
        else if (red == 0 && blue == 0) {
            return "No one";
        }
        else{
            return null;
        }
    }

	public static void main(String[] args){
        Board checkers = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        int xmo = -1;
        int ymo = -1;


        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            checkers.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                xmo = (int) x;
                ymo = (int) y;

                if(checkers.canSelect(xmo, ymo)){
                	checkers.select(xmo, ymo);
				}	
            }
        	if(checkers.isSelected == true){
<<<<<<< HEAD
        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);	
=======
>>>>>>> submit/proj0
            	StdDrawPlus.filledSquare(xmo + .5, ymo + .5, .5);
            	
        	}
            //pieces[(int) x][(int) y] = true;
         

        	if(StdDrawPlus.isSpacePressed()){
        		if (checkers.canEndTurn()) {
        			checkers.endTurn();	
        		}
        	}          
            StdDrawPlus.show(20);
        }
	}   
}