
public class Board {

    private static Piece[][] piecesList = new Piece[8][8];
    private boolean wasSelected = false;
    private Piece selectedPiece;
    private boolean hasMoved = false;
    private int player = 0; //0 is Fire, 1 is Water
    private int xSelect;
    private int ySelect;

	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

		Board gameBoard = new Board(false);
		gameBoard.drawBoard(N);
		gameBoard.drawPieces(N);   

		while (gameBoard.canEndTurn() == false) {
			if (StdDrawPlus.mousePressed()) {
				double xD = StdDrawPlus.mouseX();
				int x = (int)xD;
		        double yD = StdDrawPlus.mouseY();
		        int y = (int)yD;
		        gameBoard.select(x, y);
		        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		        // gameBoard.drawPieces(N); 
		        if (StdDrawPlus.mousePressed()) {
		        	double xfD = StdDrawPlus.mouseX();
					int xf = (int)xfD;
			        double yfD = StdDrawPlus.mouseY();
			        int yf = (int)yfD;
		        	gameBoard.select(xf, yf);
		        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		        	gameBoard.drawPieces(N); 
		        }
		        StdDrawPlus.show(2);
		    }
		}

	}

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			int N = 8;
	        for (int i = 0; i < N; i++) {
	        	for (int j = 0; j < N; j++) {
		        	if ((i + j) % 2 == 0) {
			                if (j == 0) {
			                    piecesList[i][0] = new Piece(true, this, i, 0, "pawn");
			                }
			                if (j == 1) {
			                    piecesList[i][1] = new Piece(true, this, i, 1, "shield");
			                }
			                if (j == 2) {
			                    piecesList[i][2] = new Piece(true, this, i, 2, "bomb");
			                }
			                if (j == 5) {
			                    piecesList[i][5] = new Piece(false, this, i, 5, "bomb");
			                }
			                if (j == 6) {
			                    piecesList[i][6] = new Piece(false, this, i, 6, "shield");
			                }
			                if (j == 7) {
			                   piecesList[i][7] = new Piece(false, this, i, 7, "pawn");
			                }
			        }
	            }
	        }
		}
		else {
			int N = 8;
	        for (int i = 0; i < N; i++) {
	        	for (int j = 0; j < N; j++) {
	        		piecesList[i][j] = null;
	        	}
	        }
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((piecesList[x][y] == null) && (x >= 8) && (y >= 8) && (x < 0) && (y < 0)) {
			return null;
		}
		return piecesList[x][y];
	}

	public boolean canSelect(int x, int y) {
		boolean canSelect = false;
		//IF PLAYER 0 FIRE//
		if (player == 0) {
		//Square with piece//

		if ((piecesList[x][y] != null) && (piecesList[x][y].isFire() == true)) {
			if ((wasSelected == false) || (hasMoved == false)) {
				canSelect = true;
			}
		}

		//Empty Square//
		else if ((piecesList[x][y] == null) && (selectedPiece.isFire() == true)) {
			if ((wasSelected == true) && (hasMoved == false) && (this.validMove(xSelect, ySelect, x, y))) {
				xSelect = x;
				ySelect = y;
				canSelect = true;

			}
			if ((selectedPiece.hasCaptured() == true) && (wasSelected == true)) {
				if (this.validMove(xSelect, ySelect, x, y)) {
						canSelect = true;
					}
			}
		}
		}

		//IF PLAYER 1 WATER//
		else if (player == 1) {
		//Square with piece//
		if ((piecesList[x][y] != null) && (piecesList[x][y].isFire() == false)) {
			if ((wasSelected == false) || (hasMoved == false)) {
				canSelect = true;
			}
		}

		//Empty Square//
		else if ((piecesList[x][y] == null) && (selectedPiece.isFire() == false)) {
			if ((wasSelected == true) && (hasMoved == false) && (this.validMove(xSelect, ySelect, x, y))) {
				xSelect = x;
				ySelect = y;
				canSelect = true;
			}
			if ((selectedPiece.hasCaptured() == true) &&  (wasSelected == true)) {
				if (this.validMove(xSelect, ySelect, x, y)) {
						canSelect = true;
					}
			}
		}
		}
		return canSelect;
	}

	public void select(int x, int y) {
		// if (this.canSelect(x, y)) {
		wasSelected = true;
		if (piecesList[x][y] != null) {
			selectedPiece = piecesList[x][y];
			xSelect = x;
			ySelect = y;
		}
		else if ((selectedPiece != null) && (piecesList[x][y] == null)) {
				selectedPiece.move(x, y);
				hasMoved = true;
			}
		// }
	}

	public void place(Piece p, int x, int y) {
		if ((p != null) && (x < 8) && (y < 8) && (x >=0) && (y >= 0)) {
			piecesList[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if ((x >= 8) || (x < 0) || (y >= 8) || (y < 0)) {
			return null;
		}
		if (piecesList[x][y] == null) {
			return null;
		}
		else {
			Piece removedPiece = pieceAt(x, y);
			piecesList[x][y] = null;
			return removedPiece;
		}
	}

	public boolean canEndTurn() {
		if ((selectedPiece != null) && ((selectedPiece.hasCaptured() == true) || (hasMoved == true))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void endTurn() {
		if (canEndTurn()) {
			player = this.playerTurn(player);
			wasSelected = false;
			hasMoved = false;
		}
	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;
		for (int x = 0; x < 8; x++) {
    		for (int y = 0; y < 8; y++) {
    			if (piecesList[x][y] != null) {
    				if (piecesList[x][y].isFire() == true) {
    					numFire = numFire + 1;
    				}
    				else if (piecesList[x][y].isFire() == false) {
						numWater = numWater + 1;
					}
				}
    		}
    	}

    	if ((numFire>0) && (numWater==0)) {
    		return "Fire";
    	}
    	else if ((numWater>0) && (numFire==0)) {
    		return "Water";
    	}
    	else if ((numFire==0) && (numWater==0)) {
    		return "No one";
    	}
    	else {
    		return null;
    	}
	}

	private static int playerTurn(int i) {
		return 1 - i;
	}

    private static void drawBoard(int N) {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private static void drawPieces(int N) {
        for (int x = 0; x < N; x++) {
    		for (int y = 0; y < N; y++) {
    			if ((x + y) % 2 == 0 && ((y <= 2) || (y >= 5))) {
	            if (piecesList[x][y].isFire() == true) {
	            	if (piecesList[x][y].isShield()) StdDrawPlus.picture(x +.5, y + .5, "img/shield-fire.png", 1, 1);
	            	else if (piecesList[x][y].isBomb()) StdDrawPlus.picture(x +.5, y + .5, "img/bomb-fire.png", 1, 1);
	            	else StdDrawPlus.picture(x +.5, y + .5, "img/pawn-fire.png", 1, 1);
            	}

	            if (piecesList[x][y].isFire() == false) {
	            	if (piecesList[x][y].isShield()) StdDrawPlus.picture(x +.5, y + .5, "img/shield-water.png", 1, 1); 
	            	else if (piecesList[x][y].isBomb()) StdDrawPlus.picture(x +.5, y + .5, "img/pawn-water.png", 1, 1); 
	            	else StdDrawPlus.picture(x +.5, y + .5, "img/bomb-water.png", 1, 1);  
            	}
            }
        	}
        }

    }

    private static boolean validMove(int xi, int yi, int xf, int yf) {
    	boolean canMove = false;
    	int yDiff = (yf - yi)/2;
		int xDiff = (xf - xi)/2;
		int capturedX = xi + xDiff;
		int capturedY = yi + yDiff;
    	//Checks first if out of bounds or selected piece is null, return false//
    	if ((xi >= 8) || (yi >= 8) || (xf >= 8) || (yf >= 8)
    		|| (xi < 0) || (yi < 0) || (xf < 0) || (yf < 0) || (piecesList[xi][yi] == null)) {
    		canMove = false;
    	}

    	//Checks valid moves if playing piece is KING//
    	else if (piecesList[xi][yi].isKing() == true) {
			//Checks if there is an empty diagonal in any direction//
    		if ((piecesList[xf][yf] == null) && 
    			((Math.abs(xf-xi)==1) && (Math.abs(yf-yi)==1))) {
    			canMove = true;
    		}
    		//Checks if there is a capture available//
    		else if (((Math.abs(xf-xi)==2) && (Math.abs(yf-yi)==2)) //If final destination is 1 jump over
    				&& (piecesList[xf][yf] == null) //If final destination is null
    				&& ((piecesList[xi][yi].isFire() == true) //If other piece is of opposite player
    					&& (piecesList[capturedX][capturedY].isFire() == false))
    				|| ((piecesList[xi][yf].isFire() == false)
    					&& (piecesList[capturedX][capturedY].isFire() == true))) {
    			canMove = true;
    		}
       		else {
    			canMove = false;
    		}
    	}

    	//Checks valid moves if playing piece is FIRE//
    	else if (piecesList[xi][yi].isFire() == true) {
    		//Checks if move goes up//
    		if (yf <= yi) {
    			canMove = false;
    		}
    		//Checks if there is an empty diagonal for fire piece//
    		else if ((piecesList[xf][yf] == null) && ((Math.abs(xf-xi)==1) && (Math.abs(yf-yi)==1))) {
    			canMove = true;
    		}
    		//Checks if there is a capture is available//
    		else if (((Math.abs(xf-xi)==2) && (Math.abs(yf-yi)==2))
    				&& (piecesList[capturedX][capturedY].isFire() == false) //If other piece is water
    		 		&& (piecesList[xf][yf] == null)) {		  //If final destination is exactly 1 jump over
    			canMove = true;
    		}
    		else {
    			canMove = false;

    		}
    	}

    	//Checks valid moves if playing piece is WATER//
    	else if (piecesList[xi][yi].isFire() == false) {
    		//Checks if move goes down//
    		if (yf >= yi) {
    			canMove = false;
    		}
    		//Checks if there is an empty diagonal for water piece//
    		else if ((piecesList[xf][yf] == null) && ((Math.abs(xf-xi)==1) && (Math.abs(yf-yi)==1))) {
    			canMove = true;
    		}
    		//Checks if there is a capture is available//

    		else if (((Math.abs(xf-xi)==2) && (Math.abs(yf-yi)==2))
    				&& (piecesList[capturedX][capturedY].isFire() == true) //If other piece is fire
    		 		&& (piecesList[xf][yf] == null)) {	    //If final destination is exactly 1 jump over
    			canMove = true;
    		}
    		else {
    			canMove = false;
    		}
    	}
    	else {
    		canMove = false;
    	}
    	return canMove;
    }

	
}
