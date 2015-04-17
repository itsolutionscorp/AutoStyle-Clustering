
public class Board {

    private Piece[][] thepieces;
    private static int N = 8;
    private boolean player1turn = true; // fire goes first
    private Piece selectedPiece;
    private int xCoord;
    private int yCoord;
    private boolean captureUpRight;
    private boolean captureUpLeft;
    private boolean captureDownRight;
    private boolean captureDownLeft;
    private boolean pieceHasMoved = false; // but move() is in the piece class, maybe change this when i call place()?
    private boolean winner = false;

    
    public Board(boolean shouldBeEmpty) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        thepieces = new Piece[N][N];
    	if (shouldBeEmpty) {
    		//initialize empty board
    		drawBoard(N);
    	}
    	else {
    		drawBeginning();
    	}
    	//initialize default conditions
    	//add more here?
    }
    
	public static void main(String[] args) {
        int x;
        int y;
        int[] coordinates;
        Board board = new Board(false);
        board.drawBeginning();
		while (!board.winner) {
			board.updateMyGui();
	        board.waitForClick();

			board.waitForSpace();

	    }

	}

	
	private void waitForClick() {
		int x = 0;
		int y = 0;
		int xi = 0;
		int yi = 0;
		while(true) {
            if (StdDrawPlus.mousePressed()) {
            	xi = x;
            	yi = y;
				x = (int) StdDrawPlus.mouseX();
	        	y = (int) StdDrawPlus.mouseY();
		        if (canSelect(x, y)) {
		        	select(x, y);
		        	drawBoard(N);
		        	highlightSquare(x, y);
		        	drawPieces();
		        	if (pieceHasMoved) { // what about double capture?
		        		if(!selectedPiece.hasCaptured() || (selectedPiece.hasCaptured() && selectedPiece.isBomb())) {
		        			return;
		        		}
		        		
		        		else {
		        			// break or check for double captures:
		        			// if i select another valid capture location, i can capture it
		        			// or i can choose to break if there's no other valid capture location 
		        			// if there is a valid capture location, i can choose to break
		    
//		        			validCapture(x, y, );
		        			if (captureUpRight || captureUpLeft || captureDownRight || captureDownLeft) {
		        				return;
		        			}
		        			
		        			else {
		        				return;
		        			}
		        			// make sure to tell main method that waitforspace has already been called so i dont call it again
		        		}
		        	}
		        }
            }
  
            StdDrawPlus.show(25);
		}
	}
	
//	private int waitForClickX() {
//		int x;
//        if (StdDrawPlus.mousePressed()) {
//        	x = (int) StdDrawPlus.mouseX();
//        }
//        return x;
//	}
//	
//	private int waitForClickY() {
//		int y;
//        if (StdDrawPlus.mousePressed()) {
//        	y = (int) StdDrawPlus.mouseY();
//        }
//        return y;	
//	}
	
	private void waitForSpace() {
        while (true) {
	        if (StdDrawPlus.isSpacePressed()) {
	        	if (canEndTurn()) {
	        		endTurn();
	        		return;
	        	}
	        }
            StdDrawPlus.show(100);
        }	
	}
	
	private void highlightSquare(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);  
	}
	
	
	private void updateMyGui() {
		drawBoard(8);
		drawPieces();
		
	}
	
	public Piece pieceAt(int x, int y) { //gets the piece at position
		
		if (x >= N || x < 0 || y >= N || y < 0) {
			return null;
		}
		
		if (thepieces[x][y] != null) {
			return thepieces[x][y];
		}
		
		return null;
	}
	
	public void place(Piece p, int x, int y) { // Places p at (x, y)
		if (x >= N || x < 0 || y >= N || y < 0) {
			//don't do anything
		}
		
		else {
			thepieces[x][y] = p; // place p in x, y
//			pieceHasMoved = true; // not sure
		}
	}
	
	public Piece remove(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) { 
			System.out.println("Out of bounds");
			return null;
		}
		Piece removedPiece = pieceAt(x, y);
		if (removedPiece == null) {
			System.out.println("No piece here.");
			return null;
		}
		
		if (pieceAt(x, y) != null) {
			thepieces[x][y] = null;
			return removedPiece;
		}
		
		return null;
	}
	
	private void getCoords(Piece p) {
		for (int i = 0; i < N; i+=1) {
			for (int j = 0; j < N; j += 1) {
				if (thepieces[i][j] == p) {
					xCoord = i;
					yCoord = j;
				}
			}
		}
	}
	
	public boolean canSelect(int x, int y) {
		// A square with a piece may be selected if it is the corresponding player's turn and one of the following is true:
		// 
		Piece inSquare = pieceAt(x, y);
		if (inSquare != null) { // if it's a piece
			if (player1turn && inSquare.isFire()) {
				if (selectedPiece == null) { // no selected piece
					return true;
				}
			
				else if (selectedPiece != null && pieceHasMoved == false) { //a piece has been selected & hasn't been moved
					return true;
				}
				
			}
			
			else if (!player1turn && !inSquare.isFire()) { //if it's player 2's turn & a water piece
				if (selectedPiece == null) { // no selected piece
					return true;
				}
			
				else if (selectedPiece != null && pieceHasMoved == false) { // a piece has been selected & hasn't been moved
					return true;
				}
				
			}
		}
		
		if (inSquare == null) { // if it's an empty square
				if (selectedPiece != null) { // there's a piece selected
					getCoords(selectedPiece); //get coordinates of piece
					if (validMove(xCoord, yCoord, x, y) && !pieceHasMoved) { // a piece hasn't moved & it's a valid move
						return true;
					}

					if (selectedPiece.hasCaptured() && (! selectedPiece.isBomb())) { // a piece has captured & not a bomb
						captureUpRight = false;
						captureUpLeft = false;
						captureDownRight = false;
						captureDownLeft = false;
						validCapture(xCoord, yCoord, x, y); // see the valid captures
						if (captureUpRight || captureUpLeft || captureDownRight || captureDownLeft) {
							return true;
						}
						// the player has selected a piece , captured, and selected another valid CAPTURE destination
					}
				
				}
		}
		
		return false;
	}
	
	
	private void validCapture(int xi, int yi, int xf, int yf) { 

		// these are for fire: write for water too
		
		//if is a regular fire or a king fire
		if ((selectedPiece.isFire() && (! selectedPiece.isKing())) || (selectedPiece.isKing() && selectedPiece.isFire())) {
				if ((xi != 7 || yi!= 7) && pieceAt(xi+1, yi+1) != null && !pieceAt(xi+1, yi+1).isFire()) { // make sure this works for kin
					if ((xi+2) == xf && (yi+2) ==yf && pieceAt(xf, yf) == null) {
						captureUpRight = true;
					}
				}
				
				if ((xi != 0 || yi!= 7) && pieceAt(xi-1,yi+1) != null && !pieceAt(xi-1,yi+1).isFire()) {
					if ((xi-2) == xf && (yi+2) == yf && (pieceAt(xf, yf) == null)) {
						captureUpLeft = true;
					}
				}
		}
		
		// if is king water
		if (!selectedPiece.isFire() && selectedPiece.isKing()) {
			if ((xi != 7 || yi!= 7) && pieceAt(xi+1, yi+1) != null && pieceAt(xi+1, yi+1).isFire()) { // make sure this works for kin
				if ((xi+2) == xf && (yi+2) ==yf && pieceAt(xf, yf) == null) {
					captureUpRight = true;
				}
			}
			
			if ((xi != 0 || yi!= 7) && pieceAt(xi-1,yi+1) != null && pieceAt(xi-1,yi+1).isFire()) {
				if ((xi-2) == xf && (yi+2) == yf && (pieceAt(xf, yf) == null)) {
					captureUpLeft = true;
				}
			}
	}
		
		
		// if is a king water or regular water
		if ((! selectedPiece.isFire() && (! selectedPiece.isKing())) || selectedPiece.isKing()) {
				if ((xi != 7 || yi != 0) && pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1).isFire()) {
					if ((xi+2) == xf && (yi-2) == yf && pieceAt(xf, yf) == null) { // check for corner
						captureDownRight = true;
					}
				}
			
			if ((xi != 0 || yi != 0) && pieceAt(xi-1,yi-1) != null && pieceAt(xi-1,yi-1).isFire()) {
				if ((xi-2) == xf && (yi-2) == yf && pieceAt(xf, yf) == null) { // xi could be negative
					captureDownLeft = true;
				}
			}
			
			
		}
		
		if (selectedPiece.isFire() && selectedPiece.isKing()) {
			if ((xi != 7 || yi != 0) && pieceAt(xi+1, yi-1) != null && !pieceAt(xi+1, yi-1).isFire()) {
				if ((xi+2) == xf && (yi-2) == yf && pieceAt(xf, yf) == null) { // check for corner
					captureDownRight = true;
				}
			}
		
		if ((xi != 0 || yi != 0) && pieceAt(xi-1,yi-1) != null && !pieceAt(xi-1,yi-1).isFire()) {
			if ((xi-2) == xf && (yi-2) == yf && pieceAt(xf, yf) == null) { // xi could be negative
				captureDownLeft = true;
			}
		}
		
		
	}
		
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi) == null) {
			return false;
		}
		boolean isKing = pieceAt(xi, yi).isKing();
		boolean isFire = pieceAt(xi, yi).isFire();
		
		if ((isFire && ! isKing) || isKing) {
			if ((xf == xi - 1 || xf == xi + 1) && (yf == yi + 1)) {
				return true;
			}
		}
		
		if ((! isFire && ! isKing) || isKing) {
			if ((xf == xi - 1 || xf == xi + 1) && (yf == yi - 1)) { //it's a king
				return true;
			}
		}
		
		validCapture(xi, yi, xf, yf);
		if (captureUpRight || captureUpLeft || captureDownRight || captureDownLeft) {
			return true;
		}
		
		else {
			return false;
		}
		
		// can't jump over own piece, make sure space is null.
	}
	
	public void select(int x, int y) {
		//reference boolean selectedPiece and everything created from canselect :P 
		//color background of the selected square white on GUI
		Piece inSquare = pieceAt(x, y);
		if (inSquare != null) {
			// prepare for movement
			selectedPiece = inSquare;
		}
		//move your most recently selected piece to that square
		else {
			selectedPiece.move(x, y);
			pieceHasMoved = true;
		}
		
	}
	
	public boolean canEndTurn() {
		if (selectedPiece == null) {
			return false;
		}
		else if (selectedPiece.hasCaptured() || pieceHasMoved == true) { // piece has moved or performed a capture
			return true;
		}
		else {
			return false;
		}
	}
	
	public void endTurn() {
		selectedPiece.doneCapturing();
		captureUpRight = false;
		captureUpLeft = false;
		captureDownRight = false;
		captureDownLeft = false;
		selectedPiece = null;
		pieceHasMoved = false;
		player1turn = ! player1turn;
		
		
		// more changing variables back
		// create boolean for hasMoved; if it has moved, true? 
		
	}
	
	public String winner() {
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < N; i+=1) {
			for (int j = 0; j < N; j += 1) {
			Piece checkedpiece = thepieces[i][j];
			if (checkedpiece != null) {
				if (checkedpiece.isFire()) {
					numFire += 1;
				}
			
				else if (!checkedpiece.isFire()) {
					numWater += 1;
				}
			}
		}
		}
		
		if (numWater == 0 && numFire != 0) {
			winner = true;
			return "Fire";
		}
		
		else if (numWater!=0 && numFire == 0) {
			winner = true;
			return "Water";
		}
		
		else if (numWater == 0 && numFire == 0) {
			winner = true;
			return "No one";
		}
		else {
			return null;
		}
		
	}
		
		// if game not over, return false
		// no stalemate-- determine winner by # of pieces belonging to each
		// if shouldbeempty is true, stalemate (no pieces)

	
		
	private void drawPieces() {
		// loop through the list
		// check type & isKing & isFire
		for (int i = 0; i < N; i+=1) {
			for (int j = 0; j < N; j += 1) {
				Piece piece = pieceAt(i, j);
				if (piece != null) {
					if (!piece.isKing()) {
						if (piece.isFire()) {
							if (piece.isBomb()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/bomb-fire.png", 1, 1);
							}
						
							else if (piece.isShield()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/shield-fire.png", 1, 1);
							}
						
							else {
								StdDrawPlus.picture(i + .5,  j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
						
						else {
							if (piece.isBomb()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/bomb-water.png", 1, 1);
							}
							
							else if (piece.isShield()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/shield-water.png", 1, 1);
							}
							
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
					
					else {
						if (piece.isFire()) {
							if (piece.isBomb()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/bomb-fire-crowned.png", 1, 1);
							}
							
							else if (piece.isShield()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/shield-fire-crowned.png", 1, 1);
							}
							
							else {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/pawn-fire-crowned.png", 1, 1);
							}
						}
						
						else {
							if (piece.isBomb()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/bomb-water-crowned.png", 1, 1);
							}
							
							else if (piece.isShield()) {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/shield-water-crowned.png", 1, 1);
							}
							
							else {
								StdDrawPlus.picture(i + .5,  j + .5,  "img/pawn-water-crowned.png", 1, 1);
							}
						}
					}
				}
				}
			}
	}
	private void drawBeginning() {
        drawBoard(N);
        addStartPieces(N);
	}
	
	private void addStartPieces(int N) {
		for (int i = 0; i < N; i+=1) {
			for (int j = 0; j < N; j += 1) {
				
				if (i % 2 == 0) {
					if (j == N - 2) {
						thepieces[i][j] = new Piece(false, this, i, j, "shield");
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						
					}
					
					if (j == 2) {
						thepieces[i][j] = new Piece(true, this, i, j, "bomb");
						StdDrawPlus.picture(i + .5,  j + .5,  "img/bomb-fire.png", 1, 1);
					}
					
					if (j == 0) {
						thepieces[i][j] = new Piece(true, this, i, j, "pawn");
						StdDrawPlus.picture(i + .5,  j + .5,  "img/pawn-fire.png", 1, 1);
					}
				}
				
				if ((i % 2 != 0)) {
					if (j == N - 3) {
						thepieces[i][j] = new Piece(false, this, i, j, "bomb");
						StdDrawPlus.picture(i + .5,  j + .5,  "img/bomb-water.png", 1, 1);
					}
					
					if (j == N - 1) {
						thepieces[i][j] = new Piece(false, this, i, j, "pawn");
						StdDrawPlus.picture(i + .5,  j + .5,  "img/pawn-water.png", 1, 1);
					}
					
					if (j == 1) {
						thepieces[i][j] = new Piece(true, this, i, j, "shield");
						StdDrawPlus.picture(i + .5,  j + .5,  "img/shield-fire.png", 1, 1);
					}
				}
				
				
				}
			}
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
}


