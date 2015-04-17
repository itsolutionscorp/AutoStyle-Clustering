public class Board {

    private static final int N = 8;
	private Piece[][] pieces = new Piece[N][N];;
    private int xi = -Integer.MIN_VALUE, yi = -Integer.MIN_VALUE, player = 0;
    private boolean moved = false;

	 /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
        Code received from StdDrawDemo.java
     */

	private void draw(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {

                    /** This code highlights every other square
                      * on the board. By default the color is gray,
                      * but if space [i][j] is selected, it is white. */
                    if (i == xi && j == yi) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    else                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);

                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    Piece p = pieceAt(i, j);
                    String img = "img/" + (p.isBomb() ? "bomb" : p.isShield() ? "shield" : "pawn") + (p.isFire() ? "-fire" : "-water") + (p.isKing() ? "-crowned" : "") + ".png";
                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
    }

    /** Resets the values of the selected position.
      * Used as a baseline. */

    private void reset() {
        xi = yi = -Integer.MIN_VALUE;
    }

    /** Determines if the player has already selected
      * a game piece to move. */

    private boolean moving() {
        return xi != -Integer.MIN_VALUE;
    }

    /** Sets up the game board based on the row J
      * which comes from the constructor. */

    private String pickPiece(int r) {

        return (r == 0 || r == 7 ? "pawn" :
               (r == 1 || r == 6 ? "shield" : "bomb")) + "-" + 
               (r < 4 ? "fire" : "water");
    }

	public static void main(String args[]) {
		/** Initialization of the game board. */
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
            b.draw(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX(),
                    y = (int) StdDrawPlus.mouseY();

                /** If the player has not yet moved,[potentially]
                  * allow him/her to select the current block. */
                boolean canMove = b.canSelect(x, y);
                if (canMove) b.select(x, y);
            }

            /** Listen for the spacebar to be pressed. */
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) b.endTurn();
            }

            StdDrawPlus.show(100);
        }
	}

	public Board(boolean shouldBeEmpty) {
        if ( ! shouldBeEmpty) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0 && (j > 4 || j < 3)) {
                        pieces[i][j] = new Piece((j < 3 ? true : false), this, i, j, "img/" + pickPiece(j) + ".png");
                    }
                }
            }
        }
	}

    /** Return Piece found at X and Y.
      * return null if X or Y are out of bounds
      * or if the spot at X and Y is unnocupied. */
	
	public Piece pieceAt(int x, int y) {
        try {
            return pieces[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
	}

    /** Determines if X is within R range of XI
      * and makes another comparison with Y and YI.
      */

    private boolean withinRange(int x, int y, int r) {
        return ((Math.abs(x - xi) == r) && (Math.abs(y - yi) == r));
    }

    private boolean wouldCapture(Piece target) {
        return (target != null && target.side() != player);
    }

    /** Determines if the clicked square can 
      * be selected. */
    
	public boolean canSelect(int x, int y) {
        /** Make sure players can only select
          * gray squares. */
        if ((x + y) % 2 != 0)
            return false;

        /* The piece (if one exists) at X and Y. */
        Piece current = pieceAt(x, y);

        if (moving()){
            Piece b = pieceAt(xi, yi);
            if (b == null) {
                // bomb exploded
                return false;
            }
            if (b.side() != player){
              return false;
            }
        }
        else if (current != null){
            if (current.side() != player){
               return false;
            }
        }

        /** Determine if we have already selected
          * a piece to move or if we are doing that
          * now. */
        if (moving()) {
            /** Check if this spot at X and Y is 
              * currently occupied. */ 
            if (current == null) {
                Piece control = pieceAt(xi, yi);
                if (canEndTurn() && ! control.hasCaptured()) return false;
                if (withinRange(x, y, 1)) {
                    if (control.isKing()){ // consider kings
                        return true; 
                    }
                    else if ((control.side() == 1 && y < yi) || (control.side() == 0 && y > yi)){ // consider non-kings
                        return true;
                    }
                }
                if (withinRange(x, y, 2)) {
                    // capture potential. check if the spot 1 away is a piece.

                    if (wouldCapture(pieceAt(x + (x > xi ? -1 : 1), y + (y > yi ? -1 : 1)))) {
                        if (control.isKing()) { // consider kings 
                            return true; 
                        }
                        else if ((control.side() == 1 && y < yi) || (control.side() == 0 && y > yi)){ // consider non-kings
                            return true;
                        }
                    }
                }
                return false; // didn't meet formailities
            } else {
                if (canEndTurn() && ! current.hasCaptured()) {
                    return false;
                } else if (current.side() == player && xi == x && yi == y && ! current.hasCaptured()) {
                    reset();
                    return true;
                }
                return false;
            }
        } else {
            /** Spot is unoccupied. Players cannot select
              * a blank space as their "piece" to move. */
            if (current == null) {
                return false;
            } else if (canEndTurn() && ! current.hasCaptured()) {
                return false;
            } else {
                return true;
            }  
        }
    }
    
    /** Stores the selected position into XI and YI.
      * Moves pieces around the board, kinging them 
      * if applicable. */

	public void select(int x, int y) {
        /** If we have already selected a piece, we must 
          * (again) figure out if the space at X and Y is 
          * occupied or free. */
        if (moving())
        {
            /** Get the piece that the player previously selected. */
            Piece p = pieceAt(xi, yi);
            place(p, x, y);

            pieces[xi][yi] = null;
            p.move(x, y);
            moved = true;
        }
        xi = x;
        yi = y;
	}

    /** Places Piece P to position X and Y. */
    
	public void place(Piece p, int x, int y) {
        try {
            pieces[x][y] = p;
        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (NullPointerException e) {
            /** Trying to place a piece outside of bounds.
              * Acc. proj0 spec - "does nothing."
              */
        }
	}

    /** Removes a piece from the board at position X and Y.
      * Returns the Piece that was at X and Y. */

	public Piece remove(int x, int y) {
		try {
            Piece removed = pieceAt(x, y);
            if (removed == null) {
                System.out.println("Removed Piece was null.");
            } else {
                pieces[x][y] = null;
            }
            return removed;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException: Cannot remove piece at (" + x + ", " + y + ").");
            return null;
        }
	}

    /** Checks if user can end turn. Occurs once a move
      * has happened. */
    
	public boolean canEndTurn() {
		return moved;
	}

    /** Switches players, resets selected positions */

	public void endTurn() {
        Piece p = pieceAt(xi, yi);
        if (p != null) p.doneCapturing();

        player = 1 - player;
        moved = false;
        reset();
	}

	public String winner() {
        int c_Fire = 0, c_Water = 0;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if (pieceAt(i, j) != null)
                {
                    if (pieceAt(i, j).isFire()) c_Fire++;
                    else c_Water++;
                }
            }
        }

		return (c_Fire > 0 && c_Water > 0 ? null : c_Fire == 0 && c_Water == 0 ? "No one" : c_Water == 0 ? "Fire" : c_Fire == 0 ? "Water" : "");
	}
	
}