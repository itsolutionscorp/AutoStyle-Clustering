public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private boolean fireTurn = true;
	private boolean hasSelectedPiece = false;
	private boolean hasMovedPiece = false;
	private int lastSelectedX;
	private int lastSelectedY;
	private int capturedX;
	private int capturedY;
	private boolean captureMode = false;

	private void makePieces(){

		pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
		pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		pieces[1][7]= new Piece(false, this, 1, 7, "pawn");
		pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
	}

	public Board(boolean shouldBeEmpty){


	    for (int i = 0; i < pieces.length; i += 1){
	    	for (int k = 0; k < pieces.length; k += 1){
	    		pieces[i][k] = null;
	    	}
	    }
		
		

		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);

		this.drawBoard();

		for (int i = 0; i < pieces.length; i += 1){
	    	for (int k = 0; k < pieces.length; k += 1){
	    		pieces[i][k] = null;
	    	}
	    }

		if (shouldBeEmpty == false){
			makePieces();

		}
	}

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }

    }

    private void drawPieces(){
    	for (int i = 0; i < pieces.length; i += 1){
	    	for (int k = 0; k < pieces.length; k += 1){
		    	if (pieces[i][k] != null){
		    		if (pieces[i][k].isFire()){
		    			if(pieces[i][k].isKing()){
							if (pieces[i][k].isBomb())
								StdDrawPlus.picture(i + .5, k + .5, "img/bomb-fire-crowned.png", 1, 1);
							else if (pieces[i][k].isShield())
								StdDrawPlus.picture(i + .5, k + .5, "img/shield-fire-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + .5, k + .5, "img/pawn-fire-crowned.png", 1, 1);
		    			}
		    			else{
							if (pieces[i][k].isBomb())
								StdDrawPlus.picture(i + .5, k + .5, "img/bomb-fire.png", 1, 1);
							else if (pieces[i][k].isShield())
								StdDrawPlus.picture(i + .5, k + .5, "img/shield-fire.png", 1, 1);
							else
								StdDrawPlus.picture(i + .5, k + .5, "img/pawn-fire.png", 1, 1);
						}
					}
					else{
		    			if(pieces[i][k].isKing()){
							if (pieces[i][k].isBomb())
								StdDrawPlus.picture(i + .5, k + .5, "img/bomb-water-crowned.png", 1, 1);
							else if (pieces[i][k].isShield())
								StdDrawPlus.picture(i + .5, k + .5, "img/shield-water-crowned.png", 1, 1);
							else
								StdDrawPlus.picture(i + .5, k + .5, "img/pawn-water-crowned.png", 1, 1);
		    			}
		    			else{
							if (pieces[i][k].isBomb())
								StdDrawPlus.picture(i + .5, k + .5, "img/bomb-water.png", 1, 1);
							else if (pieces[i][k].isShield())
								StdDrawPlus.picture(i + .5, k + .5, "img/shield-water.png", 1, 1);
							else
								StdDrawPlus.picture(i + .5, k + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}	
	    	}
	    }
    }

    private void drawSelect(int x, int y){
    	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }

	public static void main(String[] args) {

		Board newBoard = new Board(false);
		newBoard.drawBoard();
		newBoard.drawPieces();




        while(true) {
        	newBoard.drawPieces();

            if (StdDrawPlus.mousePressed()) {
            	newBoard.drawBoard();
            	newBoard.drawPieces();
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (((int) x < 8) && ((int) y < 8) && ((int) x >= 0) && ((int) y >= 0)){
                	if (newBoard.canSelect((int) x, (int) y)){
                		newBoard.drawBoard();
                		newBoard.drawSelect((int) x, (int) y);
                		newBoard.drawPieces();
	                	newBoard.select((int) x, (int) y);
	                }
	                // System.out.println(x + ", " + y);
	            }
            } 

            if (StdDrawPlus.isSpacePressed()){
            	if (newBoard.canEndTurn())
            		newBoard.endTurn();
            }

            StdDrawPlus.show(50);
          
            
        }

	}


	public Piece pieceAt(int x, int y){

		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
			return null;
		}
		
		return pieces[x][y];


	}

	public boolean canSelect(int x, int y){
		if ((x > 8) || (x < 0) || (y > 8) || (y < 0))
			return false;

		if ((hasSelectedPiece != true) || (hasMovedPiece != true)){
		

		if ((hasSelectedPiece == true) && (captureMode == false)){
			if ((x == lastSelectedX) && (y == lastSelectedY)){
				hasSelectedPiece = false;
				return true;
			}
		}

		if ((pieceAt(x,y) != null) && (captureMode == false)){
			if (fireTurn == pieceAt(x,y).isFire()){
				if (hasSelectedPiece == false)
					return true;
				if (hasMovedPiece == false){
					hasSelectedPiece = false;
					return true;
				}
				return false;
			}
			else
				return false;
		}


		else{
			//FIRE TURN
			if ((fireTurn) && (hasSelectedPiece)){
				if ((pieces[lastSelectedX][lastSelectedY] != null) && (pieces[lastSelectedX][lastSelectedY].isKing())){
					if (canSelectUp(x, y)){
						return canSelectUp(x,y);
					}
					if (canSelectDown(x,y)){
						return canSelectDown(x,y);
					}
					else
						return false;
				}
				else{
					return canSelectUp(x, y);
				}
			}
			//WATER TURN
			if ((fireTurn == false) && (hasSelectedPiece)){
				if ((pieces[lastSelectedX][lastSelectedY] != null) && (pieces[lastSelectedX][lastSelectedY].isKing())){
					System.out.println("king move");
					if (canSelectUp(x, y)){
						return canSelectUp(x,y);
					}
					if (canSelectDown(x,y)){
						return canSelectDown(x,y);
					}
					else
						return false;
				}
				else{
					return canSelectDown(x, y);
				}
			}
			return false;
		}

	}

	return false;

	}


	private boolean canSelectUp(int x, int y){

		if (pieceAt(x,y) != null) //if there is a piece there then return false
			return false;
								
		if ((x == lastSelectedX + 1) && (y == lastSelectedY + 1) && (captureMode == false))
			return true;
		if ((x == lastSelectedX -1) && (y == lastSelectedY + 1) && (captureMode == false))
			return true;
						
						
		if ((lastSelectedX + 1 < 8) && (lastSelectedY + 1 < 8)){
			if ((pieces[lastSelectedX + 1][lastSelectedY + 1] != null) && (pieces[lastSelectedX + 1][lastSelectedY + 1].isFire() != fireTurn)){  
				if ((x == lastSelectedX + 2) && (y == lastSelectedY + 2)){  // and the place is to the right diagonal up of the water piece
					// capturedX = lastSelectedX + 1;
					// capturedY = lastSelectedY + 1;  									
					return true;
				}	
			}
		}
		if ((lastSelectedX - 1 > 0) && (lastSelectedY + 1 < 8)){ //if X is not 0
			if ((pieces[lastSelectedX -1][lastSelectedY +1] != null) && (pieces[lastSelectedX -1][lastSelectedY +1].isFire() != fireTurn)) // and there is a water piece at the left diagonal up
				if ((x == lastSelectedX - 2) && (y == lastSelectedY + 2)){ // and the place is to the left diagonal up of the water piece
					// capturedX = lastSelectedX - 1;
					// capturedY = lastSelectedY + 1;  								
					return true;
				}
			}
		

		return false;

	}

	private boolean canSelectDown(int x, int y){
		if (pieceAt(x,y) != null){ //if there is a piece there then return false
			return false;
		}
		 //if the place is blank and the place is to the right diagonal down then return true
						
		if ((x == lastSelectedX + 1) && (y == lastSelectedY - 1) && (captureMode == false))
			return true;
		if ((x == lastSelectedX -1) && (y == lastSelectedY - 1) && (captureMode == false))
			return true;
						
						
		if ((lastSelectedX + 1 < 8) && (lastSelectedY - 1 >= 0)){
			if ((pieces[lastSelectedX + 1][lastSelectedY - 1] != null) && (pieces[lastSelectedX + 1][lastSelectedY - 1].isFire() != fireTurn)){  //if there is a fire piece at right diagonal down
				if ((x == lastSelectedX + 2) && (y == lastSelectedY - 2)){
					// capturedX = lastSelectedX + 1;
					// capturedY = lastSelectedY - 1;  
					return true;	
				}
			}
		}
		if ((lastSelectedX - 1 >= 0) && (lastSelectedY - 1 >= 0)){ //if X is not 0
			if ((pieces[lastSelectedX -1][lastSelectedY -1] != null) && (pieces[lastSelectedX -1][lastSelectedY -1].isFire() != fireTurn)) // and there is a fire piece at the left diagonal down
				if ((x == lastSelectedX - 2) && (y == lastSelectedY - 2)){ // and the place is to the left diagonal down of the water piece
					// capturedX = lastSelectedX - 1;
					// capturedY = lastSelectedY - 1;  
					return true;
				}
		}
			

		return false;

	}

	private void setCapture(int prevX, int prevY, int newX, int newY){
		if ((prevX + 2 == newX) && (prevY + 2 == newY)){
			capturedX = prevX + 1;
			capturedY = prevY + 1;
		}
		if ((prevX - 2 == newX) && (prevY + 2 == newY)){
			capturedX = prevX - 1;
			capturedY = prevY + 1;
		}
		if ((prevX - 2 == newX) && (prevY - 2 == newY)){
			capturedX = prevX - 1;
			capturedY = prevY - 1;
		}
		if ((prevX + 2 == newX) && (prevY - 2 == newY)){
			capturedX = prevX + 1;
			capturedY = prevY - 1;
		}
	}

	public void select(int x, int y){


		if (hasSelectedPiece == true){
			captureMode = false;
			setCapture(lastSelectedX, lastSelectedY, x, y);
			pieces[lastSelectedX][lastSelectedY].move(x, y);
			remove(lastSelectedX, lastSelectedY);
			lastSelectedX = x;
			lastSelectedY = y;

			if (pieceAt(x,y).hasCaptured()){
				if (pieceAt(x,y).isKing())
					pieceCaptureKing(x, y);
				else
					pieceCapture(x,y);
				// StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			}

		
			if (captureMode)
				hasMovedPiece = false;
			else
				hasMovedPiece = true;

			// drawBoard();
			// drawPieces();
		}


		if (hasSelectedPiece == false){
			hasSelectedPiece = true;

			// drawBoard();
			// // StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			// drawPieces();

			lastSelectedX = x;
			lastSelectedY = y;
		}

	}

	private void pieceCapture(int x, int y){

		remove(capturedX, capturedY);
		System.out.println("piece at " + capturedX + ", " + capturedY + " captured");
		System.out.println("piece is " +  pieceAt(capturedX, capturedY));
		System.out.println("this piece is " + pieceAt(x, y));







		if (pieceAt(x,y).isBomb()){

			for (int i = -1; i <= 1; i += 1){
				for (int k = -1; k <= 1; k += 1){
					if ((pieceAt(x + i, y + k) != null) && (pieceAt(x + i, y + k).isShield() != true)){
						remove(x + i, y + k);
					}
				}
			}
			hasMovedPiece = true;
			System.out.println("can't multicapture");
		}


		else if (pieceAt(x,y).isFire()){
			System.out.println("fire");
				if ((x + 1 < 8) && (y + 1 < 8) && (x + 2 < 8) && (y + 2 < 8)){
					if (pieceAt(x+1,y+1) != null){
						if ((pieces[x + 1][y + 1].isFire() != true) && (pieces[x+2][y+2] == null)){
							System.out.println("can multicapture");
							captureMode = true;
						}
					}
				}

				if ((x - 1 >= 0) && (y + 1 < 8) && (x - 2 >= 0) && (y + 2 < 8)){
					if (pieces[x - 1][y + 1] != null){
						if ((pieces[x - 1][y + 1].isFire() != true) && (pieces[x-2][y+2] == null)){
							System.out.println("can multicapture");
							captureMode = true;
						}
					}
				}

				if (captureMode == false){
					pieceAt(x,y).doneCapturing();
					hasMovedPiece = true;
					System.out.println("can't multicapture");
				}
				
			
		}

		else{

			System.out.println("water");

		
				if ((x + 1 < 8) && (y - 1 >= 0) && (x + 2 < 8) && (y - 2 >= 0)){
					if (pieces[x + 1][y - 1] != null){
						if ((pieces[x + 1][y - 1].isFire() == true) && (pieces[x+2][y-2] == null)){
							System.out.println("can multicapture");
							captureMode = true;
						}
					}
				}
				if ((x - 1 >= 0) && (y - 1 >= 0) && (x - 2 >= 0) && (y - 2 >= 0)){
					if (pieces[x - 1][y - 1] != null){
						if ((pieces[x - 1][y - 1].isFire() == true) && (pieces[x-2][y-2] == null)){
							System.out.println("can multicapture");
							captureMode = true;
						}	
					}
				}
				if (captureMode == false){
					pieceAt(x,y).doneCapturing();
					hasMovedPiece = true;
					System.out.println("can't multicapture");

				}
			
		}



	}


	private void pieceCaptureKing(int x, int y){
		remove(capturedX, capturedY);
		System.out.println("piece at " + capturedX + ", " + capturedY + " captured");
		System.out.println("king capture");


		if (pieceAt(x,y).isBomb()){
			for (int i = -1; i <= 1; i += 1){
				for (int k = -1; k <= 1; k += 1){
					if ((pieceAt(x + i, y + k) != null) && (pieceAt(x + i, y + k).isShield() != true)){
						remove(x + i, y + k);
					}
				}
			}
			hasMovedPiece = true;
			System.out.println("can't multicapture");
		}

		if ((x + 1 < 8) && (y - 1 >= 0) && (x + 2 < 8) && (y - 2 >= 0)){
			if (pieces[x + 1][y - 1] != null){
				if ((pieces[x + 1][y - 1].isFire() != fireTurn) && (pieces[x+2][y-2] == null)){
					System.out.println("can capture");
					captureMode = true;
				}
			}
		}
		if ((x - 1 >= 0) && (y - 1 >= 0) && (x - 2 >= 0) && (y - 2 >= 0)){
			if (pieces[x - 1][y - 1] != null){
				if ((pieces[x - 1][y - 1].isFire() != fireTurn) && (pieces[x-2][y-2] == null)){
					System.out.println("can capture");
					captureMode = true;
				}	
			}
		}
		if ((x + 1 < 8) && (y + 1 < 8) && (x + 2 < 8) && (y + 2 < 8)){
			if (pieceAt(x+1,y+1) != null){
				if ((pieces[x + 1][y + 1].isFire() != fireTurn) && (pieces[x+2][y+2] == null)){
					System.out.println("can capture");
					captureMode = true;
				}
			}
		}

		if ((x - 1 >= 0) && (y + 1 < 8) && (x - 2 >= 0) && (y + 2 < 8)){
			if (pieces[x - 1][y + 1] != null){
				if ((pieces[x - 1][y + 1].isFire() != fireTurn) && (pieces[x-2][y+2] == null)){
					System.out.println("can capture");
					captureMode = true;
				}
			}
		}
		if (captureMode == false){
					if (pieceAt(x,y) != null)
						pieceAt(x,y).doneCapturing();
					hasMovedPiece = true;
					System.out.println("can't capture");


		}
	}

	public void place(Piece p, int x, int y){
		if  ((x > -1) && (x < 8) && (y > -1) && (y < 8)){

				pieces[x][y] = p;
					
		}
	}


	// }

	public Piece remove(int x, int y){
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
			System.out.println("Coordinates out of bound");
			return null;
		}
		if (pieces[x][y] == null){
			System.out.println("No piece found");
			return null;
		}

		else{
			// Piece temp = pieces[x][y];
			// pieces[x][y] = null;
			Piece temp = pieceAt(x,y);
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn(){


		if ((hasSelectedPiece) && (hasMovedPiece))
			return true;
		if ((hasSelectedPiece) && (captureMode))
			return true;

		return false;

	}

	public void endTurn(){

		if (fireTurn)
			fireTurn = false;
		else
			fireTurn = true;

		hasSelectedPiece = false;
		hasMovedPiece = false;
		captureMode = false;
		
	}

	public String winner(){

		int numPieces = 0;
		int numSide = 0;

		for (int i = 0; i < pieces.length; i += 1){
	    	for (int k = 0; k < pieces.length; k += 1){
	    		if (pieces[i][k] != null){
	    			numPieces += 1;
	    			numSide += pieces[i][k].side();
	    		}
	    	}
	    }

	    if ((numSide == 0) && (numPieces == 0)){
	    	return "No one";
	    }
	    else if ((numSide == 0) && (numPieces > 0)){
	    	return "Fire";
	    }
	    else if ((numSide == numPieces)){
	    	return "Water";
	    }
	    else{
	    	return null;
	    }

	}
}

