public class Board {
	private boolean empty = false;
	private Piece[][] allPieces = new Piece[8][8]; 
	private boolean moved = false;
	private int whoseTurn = 0; 
	private Piece selectedPiece; //need to get it selected
	private int selectedPieceX;
	private int selectedPieceY;
	private boolean captured = false;
	


	public static void main(String[] args) {
			Board b = new Board(false);
    	    StdDrawPlus.setXscale(0, 8);
    	    StdDrawPlus.setYscale(0, 8);
       
			while(true) {
	        	b.drawBoard(8);
	       		b.drawPieces(8);  				
           			if (StdDrawPlus.mousePressed()) {
               			int x = (int) StdDrawPlus.mouseX();
               			int y = (int) StdDrawPlus.mouseY();
            	   		if (b.canSelect(x, y)) {
               				b.select(x, y);
               			}
               		}
           			if (StdDrawPlus.isSpacePressed()) {
           				if (b.canEndTurn()){
           					b.endTurn();
     	      			}
         		}  
            StdDrawPlus.show(100);         		 
         	}             	
	}

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		if (!empty) {
			for (int j = 0; j < 8; j+=2) {
	     		allPieces[j][0] = new Piece(true, this, j, 0, "pawn");
	      	}
	      	for (int j = 1; j <= 7; j+=2) {
	       		allPieces[j][1] = new Piece(true, this, j, 1, "shield");
	       		      	}
	        for (int j = 0; j < 8; j+=2) {
	        	allPieces[j][2] = new Piece(true, this, j, 2, "bomb");
	        }
	        for (int j = 1; j <= 7; j+=2) {
	        	allPieces[j][5] = new Piece(false, this, j, 5, "bomb");
	        }
	        for (int j = 0; j < 8; j+=2) {
	        	allPieces[j][6] = new Piece(false, this, j, 6, "shield");
	        }
	        for (int j = 1; j <= 8; j+=2) {
	        	allPieces[j][7] = new Piece(false, this, j, 7, "pawn");
	        }
    	}

    }

	
	private void drawPieces(int N) {
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) {
				if (allPieces[i][j] != null) {
					if (allPieces[i][j].isFire() && allPieces[i][j].isKing() == false){
						if (allPieces[i][j].isBomb()){
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
						else if (allPieces[i][j].isShield()){
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					}
					else if (allPieces[i][j].isFire() == false && allPieces[i][j].isKing() == false) {
						if (allPieces[i][j].isBomb()){
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
						else if (allPieces[i][j].isShield()){
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					}
					else if (allPieces[i][j].isFire() && allPieces[i][j].isKing() == true) {
						if (allPieces[i][j].isBomb()){
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
						else if (allPieces[i][j].isShield()){
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
						}						
					}
					else {
						if (allPieces[i][j].isBomb()){
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
						else if (allPieces[i][j].isShield()){
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
						}
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
            }
        }
    }


	public Piece pieceAt(int x, int y) {
		return allPieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (x<=7 && y<=7) {
			if (allPieces[x][y]!= null && allPieces[x][y].side() == whoseTurn){
				if (selectedPiece == null) {
					return true;
				}
				else if (selectedPiece != null && !moved) {
						return true;
					}
			}
			else if (allPieces[x][y] == null) {
				if (validMove(selectedPieceX, selectedPieceY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Need to create a public validmove//
	public boolean validMove(int xi, int yi, int xf, int yf){
		if (selectedPiece != null) {
			if (selectedPiece.isKing() == false) {
				if (whoseTurn == 0) {
					if (selectedPiece.hasCaptured() == true){     
						if (selectedPiece.isBomb()) {   //if it's a bomb!!!!
							return false;
						}
						else if (Math.abs(xi - xf) == 2 && yf - yi == 2 
							&& allPieces[(xi+xf)/2][(yi+yf)/2] != null 
							&& allPieces[(xi+xf)/2][(yi+yf)/2].side() != whoseTurn) {	
							return true;
					}}
					else if (selectedPiece.hasCaptured() == false && !moved) {
	
						if (Math.abs(xi - xf) == 2 && yf - yi == 2 
							&& allPieces[(xi+xf)/2][(yi+yf)/2] != null 
							&& allPieces[(xi+xf)/2][(yi+yf)/2].side() != whoseTurn) {	
							return true;
							}
						else if (Math.abs(xi - xf) == 1 && yf - yi == 1) {								
							return true;
					}}}
				if (whoseTurn == 1) {
					if (selectedPiece.hasCaptured() == true){       //if it's a bomb!!!!
						if (selectedPiece.isBomb()) {
							return false;
						}
						if (Math.abs(xi - xf) == 2 && yi - yf == 2 
							&& allPieces[(xi+xf)/2][(yi+yf)/2] != null 
							&& allPieces[(xi+xf)/2][(yi+yf)/2].side() != whoseTurn) {	
							return true;
					}}
					else if (selectedPiece.hasCaptured() == false && !moved) {
						if (Math.abs(xi - xf) == 2 && yi - yf == 2 
							&& allPieces[(xi+xf)/2][(yi+yf)/2] != null 
							&& allPieces[(xi+xf)/2][(yi+yf)/2].side() != whoseTurn) {	
							return true;
							}
						else if (Math.abs(xi - xf) == 1 && yi - yf == 1) {	
							return true;
					}}}}			

			else if (selectedPiece.isKing() == true) {
				if (selectedPiece.hasCaptured() == true){
						if (Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2 
							&& allPieces[(xi+xf)/2][(yi+yf)/2] != null 
							&& allPieces[(xi+xf)/2][(yi+yf)/2].side() != whoseTurn) {	
							return true;
					}}
					else if (selectedPiece.hasCaptured() == false && !moved) {
						if (Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2 
							&& allPieces[(xi+xf)/2][(yi+yf)/2] != null 
							&& allPieces[(xi+xf)/2][(yi+yf)/2].side() != whoseTurn) {	
							return true;
							}
						else if (Math.abs(xi - xf) == 1 && Math.abs(yf - yi) == 1) {	
							return true;
					}}
		}
	}
			return false;
	}
	
	public void select(int x, int y) {
		if (allPieces[x][y] == null && selectedPiece != null) {
			selectedPiece.move(x, y);
			moved = true;
		}
		else if (allPieces[x][y] != null) {
		selectedPiece = allPieces[x][y];
		selectedPieceX = x;
		selectedPieceY = y;
		}
	}


    //haven't finished
	public void place(Piece p, int x, int y) { //no need to remove or change x,y
		if (x <= 7 && y <= 7 && p != null) {
			if (allPieces[x][y] != null) {
				remove(x, y);
			}
			allPieces[x][y] = p;
			selectedPieceX = x;
			selectedPieceY = y;
		}
	}
	

	public Piece remove(int x, int y) {
		if (allPieces[x][y] != null) {
			Piece copy = allPieces[x][y];
			allPieces[x][y] = null;
			return copy;
		}
		else if (x > 7 || y > 7) {
			return null;
		}
		else {
			return null;
		}

	}



	public boolean canEndTurn() {
		if (selectedPiece != null) {
			if (moved || selectedPiece.hasCaptured()) {
				return true;
			}
		}
		else if (selectedPiece == null) {
			if (moved) {
				return true;
			}
		}
			return false;

	}

	public void endTurn() {
		if (canEndTurn()) {
			moved = false;
			captured = false;
			selectedPiece.doneCapturing();			
			selectedPiece = null;
			selectedPieceX = 0;
			selectedPieceY = 0;
			whoseTurn = Math.abs(1-whoseTurn);
		}
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		String answer = "No one";
		for (int i = 0; i<allPieces.length; i++) {
			for (int j = 0; j < allPieces[i].length; j++) {
				if (allPieces[i][j] != null) {
					if (allPieces[i][j].side() == 0) {
						fire = fire + 1;
					}
					if (allPieces[i][j].side() == 1) {
						water = water + 1;
					}
					}
			}
		}
		if (fire == 0 && water != 0) {
			answer = "Fire";
			return answer;
			}
		else if (water == 0 && fire != 0) {
			answer = "Water";
			return answer;
			}
		else if (fire == water) {
			if (fire == 0) {
				return answer;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
}
