import java.lang.Math;

/**
 * 
 * @author Mathew Pang
 * University of California, Berkeley
 * CS 61B
 * Project 0
 * Due 2/13/15
 * 
 * The Board class for checkers61b. Contains the GUI as well. Uses the Piece class. 
 * 
 * The Junit tests for this class can be found in TestBoard.java
 *
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private int length = 8;
    private int currentPlayer = 0; //0 if Fire's turn, 1 if Water's turn.
    private int selectedX;
    private int selectedY;
    private boolean currentlySelected = false;
    private boolean hasMoved = false;
    private boolean hasCaptured = false;
    private int numFirePieces = 0;
    private int numWaterPieces = 0;
	
    /**
     * Constructor for the Board class. 
     * 
     * 
     * @param shouldBeEmpty	boolean that toggles the board initializing empty or in the default configuration.
     */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[length][length];
		if (!shouldBeEmpty) {
			for (int x = 0; x < length; x++) {
				for (int y = 0; y < length; y++) {
					/** Create Fire's Pawns */
					if ((x % 2 == 0) && (y == 0)) {
						pieces[x][y] = new Piece(true, this, x, y, "pawn");
						numFirePieces = numFirePieces + 1;
					}
					/** Create Fire's Shields */
					else if ((x % 2 != 0) && (y == 1)) {
						pieces[x][y] = new Piece(true, this, x, y, "shield");
						numFirePieces = numFirePieces + 1;
					}
					/** Create Fire's Bombs */
					else if ((x % 2 == 0) && (y == 2)) {
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
						numFirePieces = numFirePieces + 1;
					}
					/** Create Water's Bombs */
					else if ((x % 2 != 0) && (y == length - 3)) {
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
						numWaterPieces = numWaterPieces + 1;
					}
					/** Create Waters's Shields */
					else if ((x % 2 == 0) && (y == length - 2)) {
						pieces[x][y] = new Piece(false, this, x, y, "shield");
						numWaterPieces = numWaterPieces + 1;
					}
					/** Create Waters's Bombs */
					else if ((x % 2 != 0) && (y == length - 1)) {
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
						numWaterPieces = numWaterPieces + 1;
					}
					else {
						pieces[x][y] = null;
					}
				}
			}
		}
		else{
			for (int x = 0; x < length; x++) {
				for (int y = 0; y < length; y++) {
					pieces[x][y] = null;
				}
			}
		}
	}
	
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else                  {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (currentlySelected && (i == this.selectedX) && j == (this.selectedY)){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieces[i][j] != null) {
                    this.drawPiece(pieces[i][j], i, j);
                }
            }
        }
    }
    
    /**
     * This method draws Piece p on square (x,y)
     * @param p		Piece to be drawn
     * @param x		x-coordinate of the piece to be drawn 
     * @param y 	y-coordinate of the piece to be drawn
     */
    private void drawPiece(Piece p, int x, int y) {
    	String imagePath = "img/";
    	/* Check Piece Type */
    	if (p.isBomb()) {
    		imagePath = imagePath + "bomb";
    	}
    	else if (p.isShield()) {
    		imagePath = imagePath + "shield";
    	}
    	else {
    		imagePath = imagePath + "pawn";
    	}
    	
    	/* Check team */
    	if (p.isFire()) {
    		imagePath = imagePath + "-fire";
    	}
    	else {
    		imagePath = imagePath + "-water";
    	}
    	
    	/* Check if Piece is crowned */
    	
    	if(p.isKing()) {
    		imagePath = imagePath + "-crowned";
    	}
    	
    	imagePath = imagePath + ".png";
    	StdDrawPlus.picture(x + .5, y + .5, imagePath, 1, 1);
    }
    
    /**
     * Returns the piece on the board at position (x, y) or null if no piece is there.
     * 
     * @param x		x-coordinate on the board
     * @param y		y-coordinate on the board
     * @return		returns the piece at x,y or null if no piece is there.
     */
    public Piece pieceAt(int x, int y) {
    	if ((x < length) && (y < length)) {
    		return pieces[x][y];
    	}
    	return null;
    }
    
    /**
     * Places Piece p at location (x,y). Does not remove Piece is 
     * it already exists on board. Does nothing if p is null.
     * 
     * @param p		Piece to be placed
     * @param x		x-coordinate for the Piece to be placed.
     * @param y		y-coordinate for the Piece to be placed.
     */
    public void place(Piece p, int x, int y) {
    	if ((p != null) && ((x < length) & (y < length))) {
    		pieces[x][y] = p;
    		if (p.isFire()) {
    			numFirePieces = numFirePieces + 1;
    		}
    		if (!p.isFire()) {
    			numWaterPieces = numWaterPieces + 1;
    		}
    	}
    }
    
    /**
     * Removes a piece p from the board and returns it. Returns null if
     * the piece is out of bounds or if there is no piece at the specified
     * location.
     * 
     * @param x 	x-coordinate of the piece to be removed.
     * @param y		y-coordinate of the piece to be removed.
     * @return		piece if the piece is removed or null if there is no piece or if the location is out of bounds.
     */
    public Piece remove(int x, int y) {
    	if ((x >= length) || (y >= length)) {
    		System.out.println("That location is out of bounds!");
    		return null;
    	}
    	else if (this.pieceAt(x, y) == null){
    		System.out.println("There is no piece at that location!");
    		return null;
    	}
    	else {
    		Piece p = this.pieceAt(x, y);
    		if (p.isFire()) {
    			numFirePieces = numFirePieces - 1;
    		}
    		if (!p.isFire()) {
    			numWaterPieces = numWaterPieces - 1;
    		}
    		pieces[x][y] = null;
    		return p;
    	}
    }
    
    /**
     * Selects the piece or square at (x,y) and
     * 		moves it if the square is empty and a piece has already been selected.
     * 		selects the new piece if the square is occupied and the previously selected piece has not moved.
     * 		selects the piece if the square is occupied and no piece has been selected.
     * @param x 	x-coordinate of piece or square to be selected.
     * @param y		y-coordinate of piece or square to be selected.
     */
    public void select(int x, int y) {
		if (this.currentlySelected == true) {
			// This if block checks if the user is selecting another piece, and then changes the selection to that piece.
			if (this.pieceAt(x, y) != null) {
				this.selectedX = x;
				this.selectedY = y;
			}
			else {
				Piece p = this.pieceAt(selectedX,selectedY);
				p.move(x,y);
				this.selectedX = x;
				this.selectedY = y;
				this.hasMoved = true;
			}
		}
		else {
			this.currentlySelected = true;
			this.selectedX = x;
			this.selectedY = y;
		}
    }
    
    /**
     * Checks to see if a piece or square can be selected.
     * We can select a piece:
     * 		if the player owns that piece
     * 			no other pieces have been chosen
     * 			another piece has been chose but the player has not moved.
     * We can select an empty square:
     * 		if the player has selected a piece previously 
     * 			it hasn't moved yet and that empty spot is a valid move or capture
     * 			it has made a capture, and the empty spot is a valid capture destination.
     * 			
     * 		
     * @param x		x-coordinate of the piece or square to be checked.
     * @param y		y-coordinate of the piece or square to be checked.
     * @return		true if square or piece can be selected, false otherwise.
     */
    
    public boolean canSelect(int x, int y) {
    	if ((x >= this.length) || (y >= this.length)) {
    		return false;
    	}
    	Piece p;
    	if (this.pieceAt(x, y) != null){
    		p = this.pieceAt(x, y);
    		if (this.ownedByCurrentPlayer(p)){
    			if ((!this.currentlySelected)||((this.currentlySelected) && (!this.hasMoved))) {
    				return true;
    			}
    		}
    	}
    	if (this.pieceAt(x, y) == null){
    		if(this.currentlySelected) {
    			p = this.pieceAt(this.selectedX, this.selectedY);
    			if (p != null) {
    				if (!this.hasMoved) {
	    				return (this.validMove(this.selectedX, this.selectedY, x, y) || (this.validCapture(p, this.selectedX, this.selectedY, x, y)));
	    			}
	    			if (p.hasCaptured()){
	    				return this.validCapture(p, this.selectedX, this.selectedY, x, y);
	    			}
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Checks to see if a move would be valid given initial and final positions.
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	Piece p = this.pieceAt(xi, yi);
    	int xDistance = xf - xi;
    	int yDistance = yf - yi;
    	if ((xf >= this.length) || (yf >= this.length)) {
    		return false;
    	}
    	if (this.pieceAt(xf,yf) != null) {
    		return false;
    	}
    	if (!p.isKing()) {
    		if (p.isFire()) {
    			if ((Math.abs(xDistance) == 1) && (yDistance == 1)) {
    				return true;
    			}
    		}
    		else {
    			if ((Math.abs(xDistance) == 1) && (yDistance == -1)) {
    				return true;
    			}
    		}
    	}
    	else if (p.isKing()) {
    		if(p.isFire()) {
				if ((Math.abs(xDistance) == 1) && ((Math.abs(yDistance) == 1))) {
					return true;
				}
			}
			else {
				if ((Math.abs(xDistance) == 1) && (Math.abs(yDistance) == 1)) {
					return true;
				}
			}
    	}
    	return false;    	
    }
    
    /** Checks to see if a Piece p can capture a piece by moving to a final location.
     * 
     */
    private boolean validCapture(Piece p, int xi, int yi, int xf, int yf) {
    	if ((xf >= this.length) || (yf >= this.length))
    		return false;
    	if (pieceAt(xf, yf) != null) {
    		return false;
    	}
    	int xDistance = xf - xi;
    	int yDistance = yf - yi;
    	int xCap;
    	int yCap;
    	Piece alpha;
    	if (Math.abs(xDistance) == 2) {
    		if (p.isFire() && ((yDistance == 2) || (Math.abs(yDistance) == 2) && p.isKing())) {
    	    	xCap = (xf + xi)/2;
    	    	yCap = (yf + yi)/2;
    	    	alpha = pieceAt(xCap, yCap);
    			if ((alpha != null) && (!alpha.isFire())) {
    				return true;
    			}
    		}
    		else if (!p.isFire() && ((yDistance== -2) || (Math.abs(yDistance) == 2) && p.isKing())) {
    	    	xCap = (xf + xi)/2;
    	    	yCap = (yf + yi)/2;
    	    	alpha = pieceAt(xCap, yCap);
    			if ((alpha != null) && (alpha.isFire())) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Checks to see if Piece p is owned by the player's whose move it is now.
     * 
     */
    private boolean ownedByCurrentPlayer(Piece p) {
    	if (p.isFire() && currentPlayer == 0) {
    		return true;
    	}
    	else if (!p.isFire() && currentPlayer == 1) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Checks to see if the player can end the turn, if the player has moved or captured a piece this turn.
     * @return true if player has moved or captured a piece this turn, false otherwise.
     */
    public boolean canEndTurn() {
    	if (this.hasMoved || this.hasCaptured) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Ends the turn and resets all game state variables to their initial state.
     */
    public void endTurn() {
    	this.hasMoved = false;
    	this.hasCaptured = false;
    	this.currentlySelected = false;
    	if (this.pieceAt(this.selectedX, this.selectedY) != null) {
    		this.pieceAt(this.selectedX, this.selectedY).doneCapturing();
    	}
    	if (this.currentPlayer == 0) {
    		this.currentPlayer = 1;
    	}
    	else {
    	this.currentPlayer = 0;
    	}
    }
    
    /**
     * Checks to see if the game has ended and returns the name of the winner "Fire" or "Water". Returns "No one" if
     * tied. Returns null if the game is not over or if there is a stalemate.
     * 
     * @return		"Fire" if fire won, "Water" if water won, "No one" if tied, and null if the game is not yet over.
     */
    public String winner() {
    	if ((numFirePieces == 0) && (numWaterPieces == 0)) {
    		return "No one";
    	}
    	if (numFirePieces == 0) {
    		return "Water";
    	}
    	if (numWaterPieces == 0) {
    		return "Fire";
    	}
    	return null;
    }
    
    public static void main(String[] args) {
        int N = 8;
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(b.winner() == null) {
            b.drawBoard(N);  
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(100);
        }
        b.drawBoard(N);
        StdDrawPlus.show(100);
        System.out.println("Winner is: " + b.winner());
    }
    
}
