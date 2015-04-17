public class Board{
	private Piece[][] pieces;
	private boolean drawPieces;
	private int N = 8;
	private boolean fireTurn;
	private int xCoord;
	private int yCoord;
	private boolean hasMoved;
	private Piece selectedPiece;
	private int newXCoord;
	private int newYCoord;
	private boolean canCapture;
	private int xBefore;
	private int yBefore;
	// private boolean hasCaptured;

	public Board(boolean shouldBeEmpty){
		drawPieces = !(shouldBeEmpty);
		fireTurn = true;
		pieces = new Piece[N][N];

		xCoord = 9;
		yCoord = 9;
		newXCoord = -10;
		newYCoord = -10;
		xBefore = -10;
		yBefore = -10;
		hasMoved = false;
		selectedPiece = null;
		canCapture = false;

		if (drawPieces){
	    	for (int i = 0; i < 7; i+=2){ // Fire pawns
	    		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
	    	}
	    	for (int i = 1; i < 8; i +=2){ // Fire Shields
	    		pieces[i][1] = new Piece(true, this, i, 1, "shield");
	    	}
	    	for (int i = 0; i < 7; i+=2){ // Fire Bombs
	    		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
	    	}
	     	for (int i = 1; i < 8; i+=2){ // Water Bombs
	    		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
	    	}
	    	for (int i = 0; i < 7; i+=2){ // Water Shields
	    		pieces[i][6] = new Piece(false, this, i, 6, "shield");
	    	}
	    	for (int i = 1; i < 8; i+=2){ // Water Pawns
	    		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
	    	}
	    }
	}

	private boolean outOfBound(int x, int y){
		if (x > 7 || x < 0){
			return true;
		} else if (y > 7 || y < 0){
			return true;
		} else {
			return false;
		}
	}

	public Piece pieceAt(int x, int y){
		if (outOfBound(x, y)){
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y){
		if (outOfBound(x, y)){
			return false;
		}

		Piece toBeSelected = pieceAt(x, y);

		if (selectedPiece == null){ // Haven't selected piece yet
			if (toBeSelected == null){ // Trying to select an empty space
				return false;
			}		
		}

		if (fireTurn){ // Fire's Turn
			if (hasMoved){
				if (selectedPiece.hasCaptured()){
					if (validCapture(newXCoord, newYCoord, x, y)){
						newXCoord = x;
						newYCoord = y;
						return true;
					}
				} return false;
			}

			if (toBeSelected != null){ // Selecting a Piece
				if (toBeSelected.isFire()){ // Can only select Fire Pieces
					xCoord = x;
					yCoord = y;
					return true;
				} else {
					return false;
				}
			} else { // Selecting empty space and preparing for move or capture
				if (validMove(xCoord, yCoord, x, y)){
					return true;
				} else if (validCapture(xCoord, yCoord, x, y)){
					newXCoord = x;
					newYCoord = y;
					return true;
				} else {
					return false;
				}
			}

		} else { // Water's Turn
			if (hasMoved){
				if (validCapture(newXCoord, newYCoord, x, y)){
					newXCoord = x;
					newYCoord = y;
					return true;
				} return false;
			}

			if (toBeSelected != null){ // Selecting a Piece
				if (!(toBeSelected.isFire())){ // Cannot select Fire pieces
					xCoord = x;
					yCoord = y;
					return true;
				} else {
					return false;
				}
			} else { // Selecting empty space for move
				if (validMove(xCoord, yCoord, x, y)){
					return true;
				} else if (validCapture(xCoord, yCoord, x, y)){
					newXCoord = x;
					newYCoord = y;
					return true;
				} else {
					return false;
				}
			} 
	 	}
	}

	private boolean validCapture(int xi, int yi, int xf, int yf){
		canCapture = false;

		if (outOfBound(xf, yf) || outOfBound(xi, yi)){
			return false;
		}

		boolean firePiece = selectedPiece.isFire();
		boolean kingPiece = selectedPiece.isKing();
		if (!(outOfBound(xf, yf))){
			if (pieceAt(xf, yf) == null){ // Making sure destination is empty space
				if (firePiece || kingPiece){
					if ((xi - 2 == xf) && (yi + 2 == yf)){ // Capturing upper left square
						if (pieceAt(xi-1, yi+1) != null){
							if (pieces[xi][yi].isFire() != pieces[xi-1][yi+1].isFire()){ 
								canCapture = true;
							}
						}
					} else if ((xi + 2 == xi) && (yi + 2 == yf)){ // Capturing upper right square
						if (pieceAt(xi+1, yi+1) != null){
							if (pieces[xi][yi].isFire() != pieces[xi+1][yi+1].isFire()){ 
								canCapture = true;
							}
						}
					}

				} else if ((!firePiece) || kingPiece){
					if ((xi - 2 == xf) && (yi - 2 == yf)){ // Capturing lower left square
						if (pieceAt(xi-1, yi-1) != null){
							if (pieces[xi][yi].isFire() != pieces[xi-1][yi-1].isFire()){ 
								if (pieceAt(xf, yf) == null){
									canCapture = true;
								}
							}
						}
					} else if ((xi + 2 == xf) && (yi - 2 == yf)){ // Capturing lower right square
						if (pieceAt(xi+1, yi-1) != null){
							if (pieces[xi][yi].isFire() != pieces[xi+1][yi-1].isFire()){ 
								canCapture = true;
							}
						}
					}
				}

			}
		} return canCapture;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		boolean canMove = false;
		if (!(outOfBound(xf, yf))){
			if (pieces[xf][yf] == null){ // Moving once diagonally
				if ((xi + 1 == xf) || (xi - 1 == xf)) {
					if (pieces[xi][yi].isKing()){
						if ((yi + 1 == yf) || (yi - 1 == yf)){ // King pieces can move in all 4 directions
							canMove = true;
						}
					} else if (pieces[xi][yi].isFire()){
						if (yi + 1 == yf){ // Fire pieces can only move up
							canMove = true;
						}
					} else {
						if (yi - 1 == yf) { // Water pieces can only move down
							canMove = true; 
						}
					}
				} 
			} else { // Capture
				if ((xi + 2 == xf) || (xi - 2 == xf)){
					if (pieces[xi][yi].isKing()){
						if ((yi + 2 == yf) || (yi - 2 == yf)){
							canMove = true;
						}
					} else if (pieces[xi][yi].isFire()){
						if (yi + 2 == yf){
							canMove = true;
						}
					} else {
						if (yi - 2 == yf){
							canMove = true;
						}
					}
				}
			}
		} return canMove;
	}

	public void select(int x, int y){
		if (pieceAt(x, y) != null){ // first selecting a piece
			selectedPiece = pieceAt(x, y);
			xBefore = x;
			yBefore = y;
		} else { // selecting empty space for move
			if (selectedPiece != null){
				selectedPiece.move(x, y);
				if ((xBefore != x) && (yBefore != y)){
					hasMoved = true;
				}
			}
		}
	}

	public void place(Piece p, int x, int y){ 
		if (outOfBound(x, y) || (p == null)){
			return;
		} else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		if (outOfBound(x, y)){
			System.out.println("Chosen coordinates are out of bounds!");
			return null;
		} else if (pieces[x][y] == null){
			System.out.println("No piece at (" + x + ", " + y + ") to remove!");
			return null;
		} else {
			Piece returnedPiece = pieces[x][y];
			pieces[x][y] = null;
			return returnedPiece;
		}
	}


	public boolean canEndTurn(){ 
		if (hasMoved){
			return true;
		} else {
			return false;
		}
	}

	public void endTurn(){
		fireTurn = !(fireTurn);
		hasMoved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		System.out.println("End turn");
	}

	private int fireNumPieces(){
		int count = 0;
		for (int i = 0; i < N; i+=1){
			for (int j = 0; j < N; j+=1){
				if (pieces[i][j] != null){
					if (pieces[i][j].isFire()){
						count += 1;
					}
				}
			}
		} return count;
	}

	private int waterNumPieces(){
		int count = 0;
		for (int i = 0; i < N; i+=1){
			for (int j = 0; j < N; j+=1){
				if (pieces[i][j] != null){
					if (!(pieces[i][j].isFire())){
						count += 1;
					}
				}
			}
		} return count;
	}

	public String winner(){
		if ((fireNumPieces() == 0) && (waterNumPieces() == 0)){
			return "No one";
		} else if ((fireNumPieces() == 0) && (waterNumPieces() > 0)){
			return "Water";
		} else if ((waterNumPieces() == 0) && (fireNumPieces() > 0)){
			return "Fire";
		} else {
			return null;
		}
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == xCoord && j == yCoord) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                 StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	if (drawPieces){
                		if (pieces[i][j] != null){
	                		if (pieces[i][j].isFire()){
				                if (pieces[i][j].isBomb()){
				                	if (pieces[i][j].isKing()){
				                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
				                	} else {
				                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				                	}
				                } else if (pieces[i][j].isShield()){
				                	if (pieces[i][j].isKing()){
				                		StdDrawPlus.picture(i + .5, j +.5, "img/shield-fire-crowned.png", 1, 1);
				                	} else {
										StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
									}
				                } else {
				                	if (pieces[i][j].isKing()){
				                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
				                	} else {
				                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				                	}
				                }
			                } else { 
			                	if (pieces[i][j].isBomb()){
			                		if (pieces[i][j].isKing()){
			                			StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water-crowned.png", 1, 1);
			                		} else {
			                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			                		}
			                	} else if (pieces[i][j].isShield()){
			                		if (pieces[i][j].isKing()){
			                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
			                		} else {
			                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
			                		}
			               		} else {
			               			if (pieces[i][j].isKing()){
			               				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
			               			} else {
			                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			                		}
				                }
				            }
	            	}
				}        
	    	}
	    }
	}

	public static void main(String args[]){
		int N = 8;
	    StdDrawPlus.setXscale(0, N);
	    StdDrawPlus.setYscale(0, N);
	    Board b = new Board(false);

		    while(b.winner() == null) {
		        b.drawBoard(N);
		        if (StdDrawPlus.mousePressed()) {
		            double x = StdDrawPlus.mouseX();
		            double y = StdDrawPlus.mouseY();
		            if (b.canSelect((int) x, (int) y)){
		            	b.select((int) x, (int) y);
                	}
		        }            
		        if (StdDrawPlus.isSpacePressed()){
		        	if (b.canEndTurn()){
		        		b.endTurn();
		        	}
		        }
		        StdDrawPlus.show(15);
			}
	}

}