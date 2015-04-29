/** 
 *  @author Emily Zhang
 */

public class Board {
    // global variables
    private Piece[][] pieces;
    private boolean fireplayer, piecemoved, piececaptured, bombexploded;
    private int xselect, yselect;

    // constructor, puts all of the starting pieces in place
    public Board(boolean shouldBeEmpty){
   		// ---- setting up some private variables
		pieces = new Piece[8][8];
		fireplayer = true;
		piecemoved = false;
        bombexploded = false;
        piececaptured = false;
		xselect = 100;
		yselect = 100;

		// ---- setting up the pieces array in the event that the board is not empty
    	if(!shouldBeEmpty){
    		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if ((i + j) % 2 == 0){
            			if (j == 0)
            				pieces[i][j] = new Piece(true, this, i, j, "pawn");
            			else if(j == 1)
            				pieces[i][j] = new Piece(true, this, i, j, "shield");
            			else if(j == 2)
            				pieces[i][j] = new Piece(true, this, i, j, "bomb");
            			else if (j == 5)
            				pieces[i][j] = new Piece(false, this, i, j, "bomb");
            			else if(j == 6)
            				pieces[i][j] = new Piece(false, this, i, j, "shield");
            			else if(j == 7)
            				pieces[i][j] = new Piece(false, this, i, j, "pawn");
            		}
            	}
        	}
    	}
    }

    // Returns the piece at a certain position (x, y) on the board
    public Piece pieceAt(int x, int y){
        if(!outOfBounds(x, y))
    	   return pieces[x][y];
        return null;
    }

    // checks if the given coordinates are out of the board's bounds or not
    private boolean outOfBounds(int x, int y){
        if(x < 0 || x >= 8 || y < 0 || y >= 8)
            return true;
        return false;
    }

    // checks to see if the square can be selected
    public boolean canSelect(int x, int y){
        if(!outOfBounds(x, y) && (x + y) % 2 == 0){
            if(!bombexploded){
                // ---- if the player has not yet selected a piece
                if(pieces[x][y] != null){
                    if(xselect == 100){
                        if((pieces[x][y].isFire() && fireplayer) || (!pieces[x][y].isFire() && !fireplayer))
                            return true;
                    }
                    if(!piecemoved){
                        xselect = 100;
                        yselect = 100;
                        if((pieces[x][y].isFire() && fireplayer) || (!pieces[x][y].isFire() && !fireplayer))
                            return true;
                    }
                }
                // ---- selecting an empty square to move to
                if(pieces[x][y] == null && !piecemoved){
                    if(xselect == 100)
                        return false;
                    if(pieces[xselect][yselect].isKing())
                        return kingMove(x, y);
                    return validMove(x, y);
                }
                else if(pieces[x][y] == null && piecemoved && piececaptured){
                    if(pieces[xselect][yselect].isKing())
                        return kingMove(x, y);
                    return validMove(x, y);
                }
                // ---- if the player has already selected a piece
                if(piecemoved && !piececaptured)
                    return false;
            }
        }
        return false;
    }

    // checks to see if the King Piece can be moved
    private boolean kingMove(int x, int y){
        if(xselect == 100)
            return false;
        Piece temp = pieces[xselect][yselect];
        if(temp != null && temp.isKing() && ((temp.isFire() && fireplayer) || (!temp.isFire() && !fireplayer))) {
            if(yselect + 1 == y && (xselect + 1 == x || xselect - 1 == x)  && !piecemoved)
                return true;
            if(yselect + 2 == y && (xselect + 2 == x || xselect - 2 == x)){
                if(pieceAt(xselect - 1, yselect + 1) != null){
                    if(temp.isFire() && !pieceAt(xselect - 1, yselect + 1).isFire())
                        return true;
                    if(!temp.isFire() && pieceAt(xselect - 1, yselect + 1).isFire())
                        return true;
                }
                if(pieceAt(xselect + 1, yselect + 1) != null){
                    if(temp.isFire() && !pieceAt(xselect + 1, yselect + 1).isFire())
                        return true;
                    if(!temp.isFire() && pieceAt(xselect + 1, yselect + 1).isFire())
                        return true;
                }
            }
            if(yselect - 1 == y && (xselect + 1 == x || xselect - 1 == x) && !piecemoved)
                return true;
            if(yselect - 2 == y && (xselect + 2 == x || xselect - 2 == x)) {
                if(pieceAt(xselect - 1, yselect - 1) != null){
                    if(!temp.isFire() && pieceAt(xselect - 1, yselect - 1).isFire())
                        return true;
                    if(temp.isFire() && !pieceAt(xselect - 1, yselect - 1).isFire())
                        return true;
                }
                if(pieceAt(xselect + 1, yselect - 1) != null){
                    if(!temp.isFire() && pieceAt(xselect + 1, yselect - 1).isFire())
                        return true;
                    if(temp.isFire() && !pieceAt(xselect + 1, yselect - 1).isFire())
                        return true;
                }
            }
            return false;
        }
        return false;
    }

    // sees if the selected piece has a valid move at (x, y)
    private boolean validMove(int x, int y){
        if(xselect == 100)
            return false;
        Piece temp = pieces[xselect][yselect];
        if(fireplayer) {
            if(yselect + 1 == y && (xselect + 1 == x || xselect - 1 == x) && !piecemoved)
                return true;
            if(yselect + 2 == y){
                if(xselect + 2 == x) {
                    if(pieceAt(xselect + 1, yselect + 1) != null){
                        if(temp.side() != pieceAt(xselect + 1, yselect + 1).side())
                            return true;
                    }
                }
                else if(xselect - 2 == x){
                    if(pieceAt(xselect - 1, yselect + 1) != null){
                        if(temp.side() != pieceAt(xselect - 1, yselect + 1).side())
                            return true;
                    }
                }
            }
            return false;
        }
        else if(!fireplayer) {
            if(yselect - 1 == y && (xselect + 1 == x || xselect - 1 == x) && !piecemoved)
                return true;
            if(yselect - 2 == y){
                if(xselect + 2 == x){
                    if(pieceAt(xselect + 1, yselect - 1) != null){
                        if(temp.side() != pieceAt(xselect + 1, yselect - 1).side())
                            return true;                    
                    }
                }
                else if(xselect - 2 == x){
                    if(pieceAt(xselect - 1, yselect - 1) != null){
                        if(temp.side() != pieceAt(xselect - 1, yselect - 1).side())
                            return true;
                    }
                }
            }
            return false;
        }
        return false;
    }


    // Selects a piece for movement
    public void select(int x, int y){
        if(xselect != 100){
            if(pieces[xselect][yselect] != null){
                Piece temp = pieces[xselect][yselect];
                temp.move(x, y);
                pieces[x][y] = temp;
                piecemoved = true;
                piececaptured = temp.hasCaptured();
                if(temp.isBomb() && piececaptured){
                    remove(x, y);
                    bombexploded = true;
                }
            }
        }
    	xselect = x;
    	yselect = y;
    }

    // Places a piece onto the board
    public void place(Piece p, int x, int y) {
        if(!outOfBounds(x, y)){
        	if(p != null){
                if(pieces[x][y] != null)
                    remove(x, y);
        		pieces[x][y] = p;
            }
        }

    }

    // Removes a piece from the board, returning the removed piece
    public Piece remove(int x, int y) {
    	if(outOfBounds(x, y)){
    		System.out.println("\nInput x: " + x +" and input y: " + y +" is out of bounds.");
    		return null;
    	}
    	else if(pieces[x][y] == null){
            pieces[x][y] = null;
    		System.out.println("\nThere is no piece at (" + x + ", " + y + ").");
    		return null;
    	}
    	Piece temp = pieces[x][y];
    	pieces[x][y] = null;
    	return temp;
    }

    // Checks to see if the turn can be ended
    public boolean canEndTurn() {
        if(piecemoved || piececaptured)
    	   return true;
        return false;
    }

    // Ends the current player's turn, resets various variables
    public void endTurn() {
    	if(fireplayer)
    		fireplayer = false;
    	else
    		fireplayer = true;

    	// ---- resetting some variables
    	piecemoved = false;
        piececaptured = false;
        bombexploded = false;
    	xselect = 100;
    	yselect = 100;

    	// ---- resets the pieces
    	for (int x = 0; x < 8; x++) {
	    	for (int y = 0; y < 8; y++) {
	    		if(pieces[x][y]!= null && pieces[x][y].hasCaptured()){
	    			pieces[x][y].doneCapturing();
	    		}
	    	}
		}

        // ---- checks to see if game has ended
        if(checkEndGame())
            System.out.print(winner());
    }

    // checks to see if the game can be ended
    private boolean checkEndGame(){
        int firecount = 0;
        int watercount = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if(pieces[x][y] != null){
                    if(pieces[x][y].isFire())
                        firecount++;
                    else
                        watercount++;
                }
            }
        }
        if(firecount == 0 || watercount == 0)
            return true;
        return false;
    }

    // Checks for the winner of the game
    public String winner(){
        // ---- counting the number of pieces left for each
        int firecount = 0;
        int watercount = 0;
		for (int x = 0; x < 8; x++) {
	    	for (int y = 0; y < 8; y++) {
	    		if(pieces[x][y] != null){
	    			if(pieces[x][y].isFire())
	    				firecount++;
	    			else
	    				watercount++;
	    		}
	    	}
		}

        // ---- conditional statements to see who won/if the game has ended
        if(firecount == 0 && watercount == 0)
            return "No one";
    	if(firecount > watercount)
    		return "Fire";
    	else if(watercount > firecount)
    		return "Water";
    	return null;
    }

    // Draws an N x N board. Adapted from:
    // http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if(xselect != 100 && i == xselect && j == yselect)
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
    	}
	}

    // draws the pieces onto the board
	private void drawPieces(){
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
		    	if (pieceAt(i, j) != null) {
                	Piece currentpiece = pieceAt(i, j);
                	if(currentpiece.isFire()){
                		if(currentpiece.isKing()){
                			if(currentpiece.isBomb())
                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                    	else if(currentpiece.isShield())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		}
                		else{
	                		if(currentpiece.isBomb())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                    	else if(currentpiece.isShield())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    	}
                    }
                    else if(!currentpiece.isFire()){
                		if(currentpiece.isKing()){
                			if(currentpiece.isBomb())
                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                    	else if(currentpiece.isShield())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		}
                		else{
	                		if(currentpiece.isBomb())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                    	else if(currentpiece.isShield())
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                    	else
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    	}
                    }
		        }
		    }
		}
	}

    // Main method, runs the game
    public static void main(String[] args) {
    	Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        
        // ---- running a while loop for the game
        while(true) {
            b.drawBoard();
            b.drawPieces();

            // ---- resets the game
            if (StdDrawPlus.isNPressed())
                b = new Board(false);

            // ---- takes care of selection of pieces, etc.
            if(StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int)x, (int)y)){
                	b.select((int)x, (int)y);
                }
            }

            // ---- ends turn, if the turn can be ended
            if(StdDrawPlus.isSpacePressed()) {
            	if(b.canEndTurn())
            		b.endTurn();
            }
            StdDrawPlus.show(25);
        }
    }
}



// OLD AND USELESS CODE PLEASE IGNORE THANK YOU!!!! :D ALSO HAVE A NICE DAY!
/*
    // checks if the piece is movable
    private boolean canMove(int x, int y){
        System.out.println("\nCan move was checked for " + x + ", " + y);
        Piece temp = pieces[x][y];
        if(temp.isKing())
            return kingMove(x, y);
        else if(fireplayer)
            return fireMove(x, y);
        return waterMove(x, y);
    }

    // checks to see if the Fire Piece can be moved
    private boolean fireMove(int x, int y){
        Piece temp = pieces[x][y];
        Piece left, right;
        if(temp.isFire()){
            if(!outOfBounds(x + 1, y + 1)){
                right = pieceAt(x + 1, y + 1);
                if(right == null)
                    return true;
                else if(!outOfBounds(x + 2, y + 2) && pieceAt(x + 2, y + 2) == null){
                    if(!right.isFire())
                        return true;
                }
            }
            if(!outOfBounds(x - 1, y + 1)){
                left = pieceAt(x - 1, y + 1);
                if(left == null)
                    return true;
                else if(!outOfBounds(x - 2, y + 2) && pieceAt(x - 2, y + 2) == null){
                    if(!left.isFire())
                        return true;
                }
            }
        }
        return false;
    }

    // checks to see if the Water Piece can be moved
    private boolean waterMove(int x, int y){
        Piece temp = pieces[x][y];
        Piece left, right;
        if(!temp.isFire()){
            if(!outOfBounds(x + 1, y - 1)){
                right = pieceAt(x + 1, y - 1);
                if(right == null)
                    return true;
                else if(!outOfBounds(x + 2, y - 2) && pieceAt(x + 2, y - 2) == null){
                    if(right.isFire())
                        return true;
                }
            }
            if(!outOfBounds(x - 1, y - 1)){
                left = pieceAt(x - 1, y - 1);
                if(left == null)
                    return true;
                else if(!outOfBounds(x - 2, y - 2) && pieceAt(x - 2, y - 2) == null){
                    if(left.isFire())
                        return true;
                }
            }
        }
        return false;
    }
*/

    // OLD CODE FOR KINGMOVE, PLEASE IGNORE

/*
    Piece left, right;
    //if((temp.isFire() && fireplayer) || (!temp.isFire() && !fireplayer)){
        if(!outOfBounds(x + 1, y + 1)){
            right = pieceAt(x + 1, y + 1);
            if(right == null)
                return true;
            else if(!outOfBounds(x + 2, y + 2) && pieceAt(x + 2, y + 2) == null){
                if((fireplayer && !right.isFire()) || (!fireplayer && right.isFire()))
                    return true;
            }
        }
        if(!outOfBounds(x - 1, y + 1)){
            left = pieceAt(x - 1, y + 1);
            if(left == null)
                return true;
            else if(!outOfBounds(x - 2, y + 2) && pieceAt(x - 2, y + 2) == null){
                if((fireplayer && !left.isFire()) || (!fireplayer && left.isFire()))
                    return true;
            }
        }
        if(!outOfBounds(x + 1, y - 1)){
            right = pieceAt(x + 1, y - 1);
            if(right == null)
                return true;
            else if(!outOfBounds(x + 2, y - 2) && pieceAt(x + 2, y - 2) == null){
                if((fireplayer && !right.isFire()) || (!fireplayer && right.isFire()))
                    return true;
            }
        }
        if(!outOfBounds(x - 1, y - 1)){
            left = pieceAt(x - 1, y - 1);
            if(left == null)
                return true;
            else if(!outOfBounds(x - 2, y - 2) && pieceAt(x - 2, y - 2) == null){
                if((fireplayer && !left.isFire()) || (!fireplayer && left.isFire()))
                    return true;
            }
        }
    //}
    return false;    */
