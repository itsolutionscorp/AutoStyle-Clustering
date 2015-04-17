public class Board {


//LINE 136 CHECK IF ITS A KING
	private Piece[][] pieces;
	private boolean shouldBeEmpty;
	private boolean newturn; 
	private boolean fireturn;

	private boolean hasselect;
	private boolean hasmove;
	private Piece removed;

	//VERY IMPORTANT = IF EMPTY BOARD, SET ALL PIECES TO NULL 

	private Piece selected;
	private int firecount;
	private int watercount;
	private int currentx;
	private int currenty;

	/* piece.x and piece.y */
	/* change piece attributes in move */ 

	public Board(boolean shouldBeEmpty) {
		shouldBeEmpty = shouldBeEmpty;
		hasselect = false;
		hasmove = false;
		pieces = new Piece[8][8];
		fireturn = true;
		currentx = -1;
		currenty = -1;
		DefaultConfig(shouldBeEmpty);
	}

	private void DefaultConfig(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
		        for (int i = 0; i < 8; i++) {
		            for (int j = 0; j < 8; j++) {
		             	pieces[i][j] = null;
		             	}
		           }
        } else {
         	for (int i = 0; i <= 6; i += 2){
         		pieces[i][0] = new Piece(true, this, i, 0, "normal");
         	}
         	for (int i = 1; i <=7; i += 2){
         		pieces[i][1] = new Piece(true, this, i, 1, "shield");
         	}
         	for (int i = 0; i <=6; i += 2){
         		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
         	}
         	for (int i = 1; i <= 7; i += 2){
         		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
         	}
         	for (int i = 0; i <= 6; i += 2){
         		pieces[i][6] = new Piece(false, this, i, 6, "shield");
         	}
         	for (int i = 1; i <= 7; i += 2){
        		pieces[i][7] = new Piece(false, this, i, 7, "normal");
         	}
        }
	}

	private boolean errorOutofBounds(int x, int y) {
		if ((x < 0) || (x > 8) || (y < 0) || (y > 8)){
			System.out.println("Error: Out of Bounds location");
			return true;
		} else if (((x % 2 == 0) && (y % 2 != 0)) || ((x % 2 != 0) && (y % 2 == 0))) {
			System.out.println("Error: Out of Bounds location");
			return true;
		} else {
			return false;
		}
	}

	private void startturn() {
		newturn = true;
	}
    
    public Piece pieceAt(int x, int y){
    	if ((!errorOutofBounds(x, y)) && (pieces[x][y] != null))  {
    		return pieces[x][y];
    	} else {
    		return null;
    	}
    }

    private boolean ispieceAt(int x, int y){
    	return (pieceAt(x, y) != null);
    }

    public boolean canSelect(int x, int y){
    	if (!errorOutofBounds(x, y)) {
	    	if (pieceAt(x, y) != null) { //CHECK IF square with a piece
	    		if (fireturn) {
	    			if (pieces[x][y].isFire()){ //IF CORRECT FIRETURN
	    				return (!hasselect || !hasmove);
	    			} else {
	    				return false;
	    			}
	    		} else {
	    			if (pieces[x][y].isFire() != true){ // IF CORRECT WATER TURN
	    				return (!hasselect || !hasmove);
	    			} else {
	    				return false;
	    			}
	    		}
	    	} else { // IF EMPTY SQUARE
	    		if (hasselect) { //if something has already been selected this turn
	    			if (fireturn) {
	    				if (selected.isFire()){
	    					if (!hasmove || (selected.hasCaptured() && (!selected.isBomb()))) {
	    						return (validMove(currentx, currenty, x, y));
	    					} else {
	    						return false;
	    					}
	    				} else {
	    					return false;
	    				}
	    			} else { // if water turn
	    				if (!selected.isFire()){
	    					if (!hasmove || (selected.hasCaptured() && (!selected.isBomb()))) {
	    						return (validMove(currentx, currenty, x, y));
	    					} else {
	    						return false;
	    					}
	    				} else {
	    					return false;
	    				}
	    			}
	    		} else {
	    			return false;
	    		}
	    	}
	    } else {
	    	return false;
	    }
    }

    private boolean FireKingvalidMove(int xi, int yi, int xf, int yf){
    	if (pieces[xi][yi].hasCaptured()) {
			if ((yf == yi + 2) && (xf == xi + 2)){
				return ((ispieceAt(xi + 1, yi + 1)) && (!pieces[xi + 1][yi + 1].isFire()));
			} else if ((yf == yi + 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi + 1)) && (!pieces[xi - 1][yi + 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi + 2)) {
				return ((ispieceAt(xi + 1, yi - 1)) && (!pieces[xi + 1][yi - 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi - 1)) && (!pieces[xi - 1][yi - 1].isFire()));
			} else {
				return false;
	    	}
	    } else {
	    	if ((yf == yi + 2) && (xf == xi + 2)){
				return ((ispieceAt(xi + 1, yi + 1)) && (!pieces[xi + 1][yi + 1].isFire()));
			} else if ((yf == yi + 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi + 1)) && (!pieces[xi - 1][yi + 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi + 2)) {
				return ((ispieceAt(xi + 1, yi - 1)) && (!pieces[xi + 1][yi - 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi - 1)) && (!pieces[xi - 1][yi - 1].isFire()));
			} else if (yf == yi + 1) { //if its moving adjavent
				return ((xf == xi + 1) || (xf == xi - 1));
			} else  if (yf == yi - 1) {
				return ((xf == xi + 1) || (xf == xi - 1));
			} else {
				return false;
			}
	    }
    }

    private boolean WaterKingvalidMove(int xi, int yi, int xf, int yf){
    	if (pieces[xi][yi].hasCaptured()) {
			if ((yf == yi + 2) && (xf == xi + 2)){
				return ((ispieceAt(xi + 1, yi + 1)) && (pieces[xi + 1][yi + 1].isFire()));
			} else if ((yf == yi + 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi + 1)) && (pieces[xi - 1][yi + 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi + 2)) {
				return ((ispieceAt(xi + 1, yi - 1)) && (pieces[xi + 1][yi - 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi - 1)) && (pieces[xi - 1][yi - 1].isFire()));
			} else {
				return false;
	    	}
	    } else {
	    	if ((yf == yi + 2) && (xf == xi + 2)){
				return ((ispieceAt(xi + 1, yi + 1)) && (pieces[xi + 1][yi + 1].isFire()));
			} else if ((yf == yi + 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi + 1)) && (pieces[xi - 1][yi + 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi + 2)) {
				return ((ispieceAt(xi + 1, yi - 1)) && (pieces[xi + 1][yi - 1].isFire())); 
			} else if ((yf == yi - 2) && (xf == xi - 2)) {
				return ((ispieceAt(xi - 1, yi - 1)) && (pieces[xi - 1][yi - 1].isFire()));
			} else if (yf == yi + 1) { //if its moving adjavent
				return ((xf == xi + 1) || (xf == xi - 1));
			} else  if (yf == yi - 1) {
				return ((xf == xi + 1) || (xf == xi - 1));
			} else {
				return false;
			}
	    }
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
    	if ((!errorOutofBounds(xf, yf)) && (!errorOutofBounds(xi, yi))) {
	    	if (!ispieceAt(xf, yf)) { // checking if space is empty
		    	if (fireturn) { // if it is fire's turn
			    	if ((pieceAt(xi, yi) != null) && (pieces[xi][yi].hasCaptured())) { // if we justcaptured
			    		if (pieceAt(xi, yi).isKing()) { // if piece is a king, check possible captures
			    			return FireKingvalidMove(xi, yi, xf, yf);
			    		} else { // if piece isn't a king
			    			if (yf == yi + 2) {
			    				if (xf == xi + 2) {
			    					return ((ispieceAt(xi + 1, yi + 1)) && (!pieces[xi + 1][yi + 1].isFire()));
			    				} else if (xf == xi - 2) {
			    					return ((ispieceAt(xi - 1, yi + 1)) && (!pieces[xi - 1][yi + 1].isFire()));
			    				}
			    			} else 
			    				return false;
			    		}
			    	} else { // if piece hasn't just captured
			    		if ((pieceAt(xi, yi) != null) && (pieces[xi][yi].isKing())) {
			    			return FireKingvalidMove(xi, yi, xf, yf);
			    		} else {
			    		if (yf == yi + 2) {
			    			if (xf == xi + 2) {
			    				return ((ispieceAt(xi + 1, yi + 1)) && (!pieces[xi + 1][yi + 1].isFire()));
			    			} else if (xf == xi - 2) {
			    				return ((ispieceAt(xi - 1, yi + 1)) && (!pieces[xi - 1][yi + 1].isFire()));
			    			}
			    		} else if (yf == yi + 1) {
			    			return ((xf == xi + 1) || (xf == xi - 1));
			    		} else {
			    			return false;
			    		}
			    		}
			    	}
			    } else { // if it is water's turn
			    	if (pieceAt(xi, yi).hasCaptured()) { // check if water justcaptured
			    		if ((ispieceAt(xi, yi)) && (pieceAt(xi, yi).isKing())) { //check if piece is king PLEASE CHECK IF PIECES XI IS INBOUNDS
			    			return WaterKingvalidMove(xi, yi, xf, yf);
			    		} else { //if piece is not king
			    			if (yf == yi - 2) {
			    				if (xf == xi + 2) {
			    					return ((ispieceAt(xi + 1, yi - 1)) && (pieces[xi + 1][yi - 1].isFire()));
			    				} else if (xf == xi - 2) {
			    					if ((ispieceAt(xi - 1, yi + 1)) && (pieces[xi - 1][yi + 1].isFire()));
			    				}
			    			} else {
			    				return false;
			    			}
			    		}
			    	} else { // is piece did not just capture
			    		if ((ispieceAt(xi, yi)) && (pieces[xi][yi].isKing())) {
			    			return WaterKingvalidMove(xi, yi, xf, yf);
			    		} else {
			    		if (yf == yi - 2) {
			    			if (xf == xi + 2) {
			    				return ((ispieceAt(xi + 1, yi - 1)) && (pieces[xi + 1][yi - 1].isFire()));
			    			} else if (xf == xi - 2) {
			    				return ((ispieceAt(xi - 1, yi - 1)) && (pieces[xi - 1][yi - 1].isFire()));
			    			}
			    		}
			    		 else if (yf == yi - 1) {
			    			return ((xf == xi + 1) || (xf == xi - 1));
			    		}
			    	}
			    	}
			    }
			return false;
			}
		} 
		return false;
	}
	
    public void select(int x, int y) {
    	if (ispieceAt(x, y)){
    		selected = pieces[x][y];
    		hasselect = true;
    		currentx = x;
    		currenty = y;
    	} else {
    		hasmove = true;
			currentx = x;
    		currenty = y;
    		if (selected != null) {
    			selected.move(x, y);
    		}
    		 // check when selected is null
    	}
    }

	public void place(Piece p, int x, int y){
		if ((!errorOutofBounds(x, y)) && (p != null)) {
			if (pieces[x][y] == null){
				pieces[x][y] = p;
			} else {
				remove(x, y);
				pieces[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y){
		if (!errorOutofBounds(x, y)) {
			if (pieces[x][y] != null) {
				removed = pieces[x][y];
				pieces[x][y] = null;
				return removed;
			} else {
				System.out.println("Error: No piece at location");
				return null;
			}
		} else {
			errorOutofBounds(x, y);
			return null;
		}
	}

	public boolean canEndTurn(){
		return ((selected != null) && hasmove); //if captured, then hasmove should be true
	}

	public void endTurn(){
		hasselect = false;
		hasmove = false;
		newturn = false;
		if (selected != null){
			selected.doneCapturing();
		}
		selected = null;
		currentx = -1;
		currenty = -1;
		if (fireturn == true){
			fireturn = false;
		} else {
			fireturn = true;
		}
	}

	public String winner(){
		firecount = 0;
		watercount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {	
            	if (pieces[i][j] != null){
            		if (pieces[i][j].isFire()) {
            			firecount = firecount + 1;
            		} else {
            			watercount = watercount + 1;
            		}
           		}
			}
		}
		if (firecount == 0 && watercount == 0) {
			return "No One";
		} else if (firecount == 0) {
			return "Water";
		} else  if (watercount == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	//SECOND TIME YOU CLICK THE MOUSE, IT SHOULD STILL BE MYBOARD.SELECT
	//if hasselect is not null, move it

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == currentx && j == currenty) {
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	} else if ((i + j) % 2 == 0) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	} else { 
            		StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
        }


    private void drawPieces(int N){
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
                	if (pieces[i][j].isBomb()) {
						if (pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					    }
					} else if (pieces[i][j].isShield()){
						if (pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						}
					} else {
						if (pieces[i][j].isFire()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
    				}
    			}
    		}
    	}
    }

	public static void main(String[] args) {
		Board newboard = new Board(false);
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //newboard.pieces = new Piece[N][N];
 //        /* if (StdDrawPlus.mousePressed()) {
 //            double x = StdDrawPlus.mouseX();
 //            double y = StdDrawPlus.mouseY();
 //        }  */          

 //        /* use coordinates to check for pieces */
 //        drawPieces(N);
 //        drawBoard(N);
         while (newboard.winner() == null) {
         	newboard.drawBoard(N);
         	newboard.drawPieces(N);

         	newboard.startturn();
        	while(newboard.newturn) {
         		if (StdDrawPlus.mousePressed()) {
         			double x = StdDrawPlus.mouseX();
         			double y = StdDrawPlus.mouseY();

         			if (newboard.canSelect((int) x, (int) y)) {
         				newboard.select((int) x, (int) y);
         				newboard.drawBoard(N); 
         				newboard.drawPieces(N); 

         			if (newboard.hasmove){
         				newboard.drawBoard(N); 
         				newboard.drawPieces(N);       			
         			}
         		}
         		newboard.drawPieces(N);  
         		} 
 //        			//if (newboard.hasselect) {
 //        			//	newboard.place(newboard.checkselect(), x, y);
 //        			//	newboard.remove(newboard.xCoordinate((int) newboard.checkselect()), (int) newboard.yCoordinate(newboard.checkselect()));
 //        			//	newboard.hasmove = true;
 //        			//	}
 //        		}
         		while (StdDrawPlus.isSpacePressed()){
         			if (newboard.canEndTurn()){
         				newboard.endTurn();
         			}
         		}
         		StdDrawPlus.show(100);
	    	}
		}
		newboard.winner();
	}
}