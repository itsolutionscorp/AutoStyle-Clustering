// Author: Justin P. Chen, 
// Login : cs61b-aqg
// Project0

public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private boolean isFireBenderTurn = true; // Fire starts the game.
	private boolean hasMoved = false; 
	private boolean hasSelected = false; 
	private int refX; 
	private int refY; 
	private Piece selectedPiece;

	public Board(boolean shouldBeEmpty) {

		this.drawEmptyBoard(8);		
		if (shouldBeEmpty==false) {
			this.drawDefaultPieces(8);
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if ((i + j) % 2 == 0) {
	                	if (j==0) {    
	                		pieces[i][j] = new Piece(true, this, i, j, "pawn");
	                	}
	                	else if (j==1) {                		
	                		pieces[i][j] = new Piece(true, this, i, j, "shield");
	                	}
	                	else if (j==2) {                		
	                		pieces[i][j] = new Piece(true, this, i, j, "bomb");
	                	}
	                	else if (j==5) {                		
	                		pieces[i][j] = new Piece(false, this, i, j, "bomb");
	                	}
	                	else if (j==6) {                		
	                		pieces[i][j] = new Piece(false, this, i, j, "shield");
	                	}
	                	else if (j==7) {                		
	                		pieces[i][j] = new Piece(false, this, i, j, "pawn");
	                	}				
	                }
	                 
	            }
	        }	
		}		
	}

    private void drawEmptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                           
            }
        }
    }

    private void drawDefaultPieces(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	if (j==0) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);	   
                	}
                	else if (j==1) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);	                		
                	}
                	else if (j==2) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);	                		
                	}
                	else if (j==5) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);	                		
                	}
                	else if (j==6) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);	                		
                	}
                	else if (j==7) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);	                		
                	}				
                }
                 
            }

        }
    }	

	public Piece pieceAt(int x, int y) {
		if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
			return null;
		}
		else if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if ((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0) && (p != null)) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
			System.out.println("Index is out of bounds.");
			return null;
		}
		else if (pieceAt(x,y) == null) {
			System.out.println("There is no piece at that location.");
			return null;
		}
		else {
			Piece removed = pieceAt(x,y);
			pieces[x][y] = null;
			return removed;
		}
	}

	private boolean checkEdges(int xi, int yi, int xf, int yf) {

		if ((xi == 0) && (xf < 0)) {  // left edge
			return false;
		} 
		else if ((xi == 7) && (xf > 7)) { // right edge
			return false;
		}
		else if ((yi == 0) && (yf < 0)) { // bottom edge
			return false;
		}
		else if ((yi == 7) && (yf > 7)) { // top edge
			return false;
		}
		return true;	
	}

	private boolean validFirePeasantMove(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally up to the right, no capture.
		if ((pieceAt(xi+1,yi+1) == null) && (xf == (xi + 1)) && (yf == (yi + 1))) {
			return true;
		}
		// Move diagonally up to the right, performing a capture.
		if ( (pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() == false) && (pieceAt(xi+2,yi+2) == null) && (xf == (xi + 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally up to the left, no capture.
		if ((pieceAt(xi-1,yi+1) == null) && (xf == (xi - 1)) && (yf == (yi + 1))) {
			return true;
		}
		// Move diagonally up to the left, performing a capture.
		if ( (pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() == false) && (pieceAt(xi-2,yi+2) == null) && (xf == (xi - 2)) && (yf == (yi + 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validFirePeasantCapture(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally up to the right, performing a capture.
		if ( (pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() == false) && (pieceAt(xi+2,yi+2) == null) && (xf == (xi + 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally up to the left, performing a capture.
		if ( (pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() == false) && (pieceAt(xi-2,yi+2) == null) && (xf == (xi - 2)) && (yf == (yi + 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validWaterPeasantMove(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally down to the right, no capture.
		if ((pieceAt(xi+1,yi-1) == null) && (xf == (xi + 1)) && (yf == (yi - 1))) {
			return true;
		}
		// Move diagonally down to the right, performing a capture.
		if ( (pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() == true) && (pieceAt(xi+2,yi-2) == null) && (xf == (xi + 2)) && (yf == (yi - 2))) {
			return true;
		}
		// Move diagonally down to the left, no capture.
		if ((pieceAt(xi-1,yi-1) == null) && (xf == (xi - 1)) && (yf == (yi - 1))) {
			return true;
		}
		// Move diagonally down to the left, performing a capture.
		if ( (pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() == true) && (pieceAt(xi-2,yi-2) == null) && (xf == (xi - 2)) && (yf == (yi - 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validWaterPeasantCapture(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally down to the right, performing a capture.
		if ( (pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() == true) && (pieceAt(xi+2,yi-2) == null) && (xf == (xi + 2)) && (yf == (yi - 2))) {
			return true;
		}
		// Move diagonally down to the left, performing a capture.
		if ( (pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() == true) && (pieceAt(xi-2,yi-2) == null) && (xf == (xi - 2)) && (yf == (yi - 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validFireKingMove(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally up to the right, no capture.
		if ((pieceAt(xi+1,yi+1) == null) && (xf == (xi + 1)) && (yf == (yi + 1))) {
			return true;
		}
		// Move diagonally up to the right, performing a capture.
		if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() == false) && (pieceAt(xi+2,yi+2) == null) && (xf == (xi + 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally up to the left, no capture.
		if ((pieceAt(xi-1,yi+1) == null) && (xf == (xi - 1)) && (yf == (yi + 1))) {
			return true;
		}
		// Move diagonally up to the left, performing a capture.
		if ( (pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() == false) && (pieceAt(xi-2,yi+2) == null) && (xf == (xi - 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally down to the right, no capture.
		if ((pieceAt(xi+1,yi-1) == null) && (xf == (xi + 1)) && (yf == (yi - 1))) {
			return true;
		}
		// Move diagonally down to the right, performing a capture.
		if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() == false) && (pieceAt(xi+2,yi-2) == null) && (xf == (xi + 2)) && (yf == (yi - 2))) {
			return true;
		}
		// Move diagonally down to the left, no capture.
		if ((pieceAt(xi-1,yi-1) == null) && (xf == (xi - 1)) && (yf == (yi - 1))) {
			return true;
		}
		// Move diagonally down to the left, performing a capture.
		if ( (pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() == false) && (pieceAt(xi-2,yi-2) == null) && (xf == (xi - 2)) && (yf == (yi - 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validFireKingCapture(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally up to the right, performing a capture.
		if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() == false) && (pieceAt(xi+2,yi+2) == null) && (xf == (xi + 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally up to the left, performing a capture.
		if ( (pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() == false) && (pieceAt(xi-2,yi+2) == null) && (xf == (xi - 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally down to the right, performing a capture.
		if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() == false) && (pieceAt(xi+2,yi-2) == null) && (xf == (xi + 2)) && (yf == (yi - 2))) {
			return true;
		}
		// Move diagonally down to the left, performing a capture.
		if ( (pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() == false) && (pieceAt(xi-2,yi-2) == null) && (xf == (xi - 2)) && (yf == (yi - 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validWaterKingMove(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally up to the right, no capture.
		if ((pieceAt(xi+1,yi+1) == null) && (xf == (xi + 1)) && (yf == (yi + 1))) {
			return true;
		}
		// Move diagonally up to the right, performing a capture.
		if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() == true) && (pieceAt(xi+2,yi+2) == null) && (xf == (xi + 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally up to the left, no capture.
		if ((pieceAt(xi-1,yi+1) == null) && (xf == (xi - 1)) && (yf == (yi + 1))) {
			return true;
		}
		// Move diagonally up to the left, performing a capture.
		if ((pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() == true) && (pieceAt(xi-2,yi+2) == null) && (xf == (xi - 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally down to the right, no capture.
		if ((pieceAt(xi+1,yi-1) == null) && (xf == (xi + 1)) && (yf == (yi - 1))) {
			return true;
		}
		// Move diagonally down to the right, performing a capture.
		if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() == true) && (pieceAt(xi+2,yi-2) == null) && (xf == (xi + 2)) && (yf == (yi - 2))) {
			return true;
		}
		// Move diagonally down to the left, no capture.
		if ((pieceAt(xi-1,yi-1) == null) && (xf == (xi - 1)) && (yf == (yi - 1))) {
			return true;
		}
		// Move diagonally down to the left, performing a capture.
		if ((pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() == true) && (pieceAt(xi-2,yi-2) == null) && (xf == (xi - 2)) && (yf == (yi - 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validWaterKingCapture(int xi, int yi, int xf, int yf) {
		if (checkEdges(xi,yi,xf,yf) == false) {
			return false;
		}
		// Move diagonally up to the right, performing a capture.
		if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() == true) && (pieceAt(xi+2,yi+2) == null) && (xf == (xi + 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally up to the left, performing a capture.
		if ((pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() == true) && (pieceAt(xi-2,yi+2) == null) && (xf == (xi - 2)) && (yf == (yi + 2))) {
			return true;
		}
		// Move diagonally down to the right, performing a capture.
		if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() == true) && (pieceAt(xi+2,yi-2) == null) && (xf == (xi + 2)) && (yf == (yi - 2))) {
			return true;
		}
		// Move diagonally down to the left, performing a capture.
		if ((pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() == true) && (pieceAt(xi-2,yi-2) == null) && (xf == (xi - 2)) && (yf == (yi - 2))) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean canSelect(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return false; 
		}

		//Implement non-empty square first, followed by empty squares. Implement this for Fire first, then Water.

		//canSelect for Fire
		if (isFireBenderTurn) {
			if (hasSelected == false) {
				if ((pieceAt(x, y) != null) && (pieceAt(x,y).isFire())) {
					return true;
				}
			}
			else if (hasSelected == true) {

				// Piece is only allowed to capture again. A regular move is not allowed.
				if ( (pieceAt(refX,refY) != null) && (hasMoved == true) && (pieceAt(refX,refY).hasCaptured())) {
					if (pieceAt(refX, refY).isKing()) {
						return validFireKingCapture(refX, refY, x, y);
					}
					else {
						return validFirePeasantCapture(refX, refY, x, y);
					}
				}

				// Can only move once if the piece didn't capture.
				if (hasMoved == true) {
					return false;
				}

				// Determining whether canSelect another piece instead.
				if ((hasMoved == false) && (pieceAt(x, y) != null) && (pieceAt(x,y).isFire())) {
					return true;
				}

				// First move of a piece.
				if ((hasMoved == false) && (pieceAt(refX,refY) != null) && (pieceAt(x,y) == null)) {
					if (pieceAt(refX, refY).isKing()) {
						return validFireKingMove(refX, refY, x, y);
					}
					else {
						return validFirePeasantMove(refX, refY, x, y);
					}
				}


				
			}	
		}

		//canSelect for Water
		if (isFireBenderTurn == false) {
			if (hasSelected == false) {
				if ((pieceAt(x, y) != null) && (pieceAt(x,y).isFire() == false)) {
					return true;
				}
			}
			else if (hasSelected == true) {

				// Piece is only allowed to capture again. A regular move is not allowed.
				if ( (pieceAt(refX,refY) != null) && (hasMoved == true) && (pieceAt(refX,refY).hasCaptured())) {
					if (pieceAt(refX, refY).isKing()) {
						return validWaterKingCapture(refX, refY, x, y);
					}
					else {
						return validWaterPeasantCapture(refX, refY, x, y);
					}
				}

				// Can only move once if the piece didn't capture.
				if (hasMoved == true) {
					return false;
				}

				// Determining whether canSelect another piece instead.
				if ((hasMoved == false) && (pieceAt(x, y) != null) && (pieceAt(x,y).isFire() == false)) {
					return true;
				}

				// First move of a piece.
				if ((hasMoved == false) && (pieceAt(refX,refY) != null) && (pieceAt(x,y) == null)) {
					if (pieceAt(refX, refY).isKing()) {
						return validWaterKingMove(refX, refY, x, y);
					}
					else {
						return validWaterPeasantMove(refX, refY, x, y);
					}
				}


				
			}	
		}

		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x,y)!= null) {
			selectedPiece = pieceAt(x,y);
		}
		else {
			selectedPiece.move(x,y);
			hasMoved = true;
		}		
		refX = x;
		refY = y;
		hasSelected = true;
	}

	public boolean canEndTurn() {
		return hasMoved;
	}


	public void endTurn() {
		if (hasMoved) {
			selectedPiece.doneCapturing();
			if (isFireBenderTurn) {
				isFireBenderTurn = false;
			}
			else {
				isFireBenderTurn = true;
			}
		}
		hasMoved = false;
		hasSelected = false;
	
	}
	
	public String winner() {
		int firecounter = 0;
		int watercounter = 0;
		for (int i = 0; i < 8; i+=1) {
			for (int j = 0; j < 8; j+=1) {
				if (pieceAt(i,j)!=null) {
					if (pieceAt(i,j).isFire() == true) {
						firecounter += 1;
					}
					else {
						watercounter += 1;
					}
				}
			}
		}
		if ((watercounter == 0) &&  (firecounter > 0)) {
			return "Fire";
		}
		else if ((firecounter == 0) && (watercounter > 0)) {
			return "Water";
		}
		else if ((firecounter == 0) && (watercounter ==0)) {
			return "No one";
		}
		else {
			return null;
		}



	}

	public static void main(String args[]) {
		int N = 8;
		int intx = 100;
		int inty = 100;
		int drawx = 50;
		int drawy = 50;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board board = new Board(false);         	
	   
	    while(true) { 
	    	board.drawEmptyBoard(N); // need to udpate
	    	if ((intx != 100) && (inty != 100) && (drawx !=50) && (drawy != 50)) {
	    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        		StdDrawPlus.filledSquare(drawx + .5, drawy + .5, .5); 
          	
	    	}
	    	board.drawUpdatedPieces(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                intx = (int) x;
                inty = (int) y;
                if (board.canSelect(intx,inty)) {
                	board.select(intx,inty);
                	drawx = intx;
                	drawy = inty;  
                } 
            }
            StdDrawPlus.show(25);
   


            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()){
            		board.endTurn();
            		drawx = 50;
            		drawy = 50;
            	}
            }            
             
        }   
    	         
                
	}



	private void drawUpdatedPieces(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieceAt(i,j) != null) {
            		if (pieceAt(i,j).isFire()) {
            			if (pieceAt(i,j).isBomb()) {
            				if (pieceAt(i,j).isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);	
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            				}	
            			}
            			if (pieceAt(i,j).isShield()) {
            				if (pieceAt(i,j).isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);	
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            				}	
            			}
            			if ((pieceAt(i,j).isBomb() == false) && (pieceAt(i,j).isShield()==false)) {
            				if (pieceAt(i,j).isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);	
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);	
            				}            			
            			}
            		}

            		if (pieceAt(i,j).isFire()==false) {
            			if (pieceAt(i,j).isBomb()) {
            				if (pieceAt(i,j).isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);	
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            				}	
            			}
            			if (pieceAt(i,j).isShield()) {
            				if (pieceAt(i,j).isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);	
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            				}	
            			}
            			if ((pieceAt(i,j).isBomb() == false) && (pieceAt(i,j).isShield()==false)) {
            				if (pieceAt(i,j).isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);	
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);	
            				}            			
            			}
            		}	
            	}               
        	}
        }
    }	




}