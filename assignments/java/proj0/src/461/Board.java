import java.lang.Math;

public class Board{
	private static boolean FIRE;
	
	private static int boardSize = 8;  // Default to 8
	private Piece[][] pieces = new Piece[boardSize][boardSize];

	private Piece chosenPiece;
	private boolean hasMoved = false;

	private int sourcePosX;
	private int sourcePosY;
		
	public Board(boolean shouldBeEmpty){
		chosenPiece = null;
		sourcePosX = 0;
		sourcePosY = 0;
		FIRE = true;
		
		if (!shouldBeEmpty){
			// Make all pieces null so that the draw method does not draw the pieces.
		  	setDefaultPieces();
		  }
	}
//--------------------------------------------------------------------------------
	/**
	 * Sets the pieces at the initial position
	 * (boolean isFire, Board b, int x, int y, String type)
	 */
	private void setDefaultPieces(){
		// 0th row - Fire Pawn
		pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		
		// 1st row - Fire Shield
		pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		pieces[3][1] = new Piece(true, this, 1, 3, "shield");
		pieces[5][1] = new Piece(true, this, 1, 5, "shield");
		pieces[7][1] = new Piece(true, this, 1, 7, "shield");
		
		// 2nd row - Fire Bomb
		pieces[0][2] = new Piece(true, this, 2, 0, "bomb");
		pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		pieces[4][2] = new Piece(true, this, 2, 4, "bomb");
		pieces[6][2] = new Piece(true, this, 2, 6, "bomb");
		
		// 7th row - Water Pawn
		pieces[1][7] = new Piece(false, this, 7, 1, "pawn");
		pieces[3][7] = new Piece(false, this, 7, 3, "pawn");
		pieces[5][7] = new Piece(false, this, 7, 5, "pawn");
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		
		// 6th row - Water Shield
		pieces[0][6] = new Piece(false, this, 6, 0, "shield");
		pieces[2][6] = new Piece(false, this, 6, 2, "shield");
		pieces[4][6] = new Piece(false, this, 6, 4, "shield");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		
		// 5th row - Water Bomb
		pieces[1][5] = new Piece(false, this, 5, 1, "bomb");
		pieces[3][5] = new Piece(false, this, 5, 3, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, this, 5, 7, "bomb");	
	}
//--------------------------------------------------------------------------------
	
	public Piece pieceAt(int x, int y){
		if ( (0 <= x) && (x < this.boardSize) && (0 <= y) && (y < this.boardSize) ){
			return pieces[x][y];
		}
		return null;
	}
	
	private String getImageFileForPiece(Piece p){
		String imageFile = "img/";
		if ( p.isBomb()){
			imageFile = imageFile + "bomb-";
		} else if ( p.isShield() ){
			imageFile = imageFile + "shield-";
		} else {
			imageFile = imageFile + "pawn-";
		}
			
		if ( p.isFire() ){
			imageFile = imageFile + "fire";
		} else {
			imageFile = imageFile + "water";
		}
		
		if ( p.isKing() ){
			imageFile = imageFile + "-crowned";
		}
		return imageFile + ".png";
	}
//----------------------------------------------------------------------------------------------------	
	
	public boolean canSelect(int x, int y){
		Piece clickedOn = this.pieceAt(x, y);
		if ( (clickedOn != null) && (clickedOn.isFire() == FIRE) ){ 
			if(chosenPiece == null || !hasMoved ){
				return true;
			} else return false;
		} else if (clickedOn == null){
			if (chosenPiece != null && ((!hasMoved && validMove(sourcePosX, sourcePosY, x, y)) || (chosenPiece.hasCaptured() && validMove(sourcePosX, sourcePosY, x, y)))) {
				return true;
			} 
			return false;		
		}
		return false;
	}

	private boolean validMove(int initialX, int initialY, int finX, int finY) {
		if (chosenPiece.isKing()) {
			int midX = (finX + initialX)/2;
			int midY = (finY + initialY)/2;
			Piece p = pieceAt(midX, midY);
			if ((Math.abs(initialX - finX) == 2) && (Math.abs(initialY - finY) == 2) && (p!= null) && (p.isFire() != FIRE)) {
				if (chosenPiece.hasCaptured() == false && hasMoved) {
					return false;
				} else return true;
			}
			if ((Math.abs(initialX - finX) == 1) && (Math.abs(initialY - finY) == 1)) {
				if (hasMoved) {
					return false;
				} else return true;
			}
		} 
		else if (!chosenPiece.isFire()) {
			int midX = (finX + initialX)/2;
			int midY = (finY + initialY)/2;
			Piece p = pieceAt(midX, midY);
			if ((Math.abs(finX - initialX) == 2) && (initialY - finY == 2) && (p != null) && (p.isFire() != FIRE)) {
				if (chosenPiece.hasCaptured() == false && hasMoved) {
					return false;
				} else return true;
			}
			if ((Math.abs(finX - initialX) == 1) && (initialY - finY == 1)) {
				if (hasMoved) {
					return false;
				} else return true;
			}
		} 
		else {
			int midX = (finX + initialX)/2;
			int midY = (finY + initialY)/2;
			Piece p = pieceAt(midX, midY);
			if ((Math.abs(initialX - finX) == 2) && (finY - initialY == 2) && (p != null) && (p.isFire() != FIRE)) {
				if (chosenPiece.hasCaptured() == false && hasMoved) {
					return false;
				} else return true;
			}
			if ((Math.abs(finX - initialX) == 1) && (finY - initialY == 1)) {
				if (hasMoved) {
					return false;
				} else return true;
			}
		}
		return false;
	}
		
//-----------------------------------------------------------------------------------------------------
	
	/**
	 * Draws the board according to the pieces setup by the Board settings.
	 */
    private void drawBoard() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
 
                if (this.pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, this.getImageFileForPiece(pieces[i][j]), 1, 1);
                    
                }
            }
        }
    }	
    
    public void place(Piece p, int x, int y){
    	for (int i = 0; i < this.boardSize; i++) {
    		for (int j = 0; j < this.boardSize; j++) {
    			if (pieces[i][j] == p) {
    				pieces[i][j] = null;
    			}
    		}
    	}
    	pieces[x][y] = p;
    }

    public Piece remove(int x, int y){
    	Piece pieceAtXY = pieceAt(x,y);
    	if((x > 7) || (x < 0)){
    		return null;
    	}
    	else if((y > 7) || (y < 0)){
    		return null;
    	} else if(pieceAtXY == null){
    		return null;
    	}
    	else{
    		this.pieces[x][y] = null;
    		return pieceAtXY;
    	}
    }

    public boolean canEndTurn(){
    	if(chosenPiece == null) return false;
    	return (hasMoved || chosenPiece.hasCaptured());
    }
    
    public void endTurn(){
		// Change the player turn
		FIRE = !(FIRE);
		this.hasMoved = false;
		chosenPiece.doneCapturing();
		chosenPiece = null;
    }

    public void select(int x, int y){
    	Piece pieceAtXY = pieceAt(x,y);
    	if((chosenPiece != null) && (pieceAtXY != null)){
    		chosenPiece = pieceAtXY;
    		sourcePosX = x;
    		sourcePosY = y;
    	} else if((chosenPiece != null) && (pieceAtXY == null)){
    		chosenPiece.move(x,y);
    		sourcePosX = x;
    		sourcePosY = y;
    		this.hasMoved = true;
    	} else if(chosenPiece == null){
    		chosenPiece = pieceAtXY;
    		sourcePosX = x;
    		sourcePosY = y;
    	}
    	
    }

    public String winner(){
    	int numFire = 0;
    	int numWater = 0;
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if(pieces[i][j] != null){
    				if(pieces[i][j].isFire()){
    					numFire++;
    				}
    				else{
    					numWater++;
    				}
    			}
    		}
    	}
    	if((numFire == 0) && (numWater != 0)){
    		return "Water";
    	}
    	else if((numFire != 0 ) && (numWater == 0)){
    		return "Fire";
    	}
    	else if((numFire == 0) && (numWater == 0)){
    		return "No one";
    	}
    	else{
    		return null;
    	}
    }
//--------------------------------------------------------------------------------
	public static void main(String args[]){

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.boardSize);
        StdDrawPlus.setYscale(0, b.boardSize);
        
        while(true) {  // TODO:  Change it to game not over.  Wait to implement logic.
            b.drawBoard();
            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                boolean result = b.canSelect((int) x, (int) y);
                System.out.print("X = " + (int) x + " Y = " + (int) y + "   ");
                System.out.println("Can select? " + result);
            }       
            
            if( StdDrawPlus.isSpacePressed() ){
            	if ( b.canEndTurn() ){
            		b.endTurn();
            	}
            }
            
            StdDrawPlus.show(100);
        }
    }
}