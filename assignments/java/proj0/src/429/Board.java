/** 
 *  @author Raj Agrawal 
 */
 
public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private boolean firePlaying;
    private Piece hasBeenSelected;
    private boolean hasMoved;
    private boolean bombHasDied; 
    private int currPosX;
    private int currPosY; 

    public static void main (String[] args) {  
        /** Display a blank 8x8 board */
        Board b = new Board(false); 
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        b.drawBoard(8);
        while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                    b.endTurn();  //Switch sides!
                }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    b.select((int) x, (int) y); 
                }
            }            
            StdDrawPlus.show(10);
        } 
        }

    /**
    * The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. 
    * Otherwise, initializes a Board with the default configuration.
    **/
    public Board(boolean shouldBeEmpty) {
        /** Creates a blank 8x8 board */
        hasBeenSelected = null;
        firePlaying = true;
        pieces = new Piece[8][8]; 
        hasMoved = false;
        if (!shouldBeEmpty) {
            int col = 0; 
            while (col < 8) {
                pieces[col][0] = new Piece(true, this, col, 0, "pawn");
                pieces[col][2] = new Piece(true, this, col, 2, "bomb");
                pieces[col][6] = new Piece(false, this, col, 6, "shield");
                col += 2;
            }
            col = 1;
            while (col < 8) {
                pieces[col][1] = new Piece(true, this, col, 1, "shield");
                pieces[col][5] = new Piece(false, this, col, 5, "bomb");
                pieces[col][7] = new Piece(false, this, col, 7, "pawn");
                col += 2;
            }
        }
    }

    /** Additional methods */

    /**
    * Places p at (x, y). If (x, y) is out of bounds, does nothing. 
    * If another piece already exists at (x, y), p will replace that piece.
    **/
    public void place(Piece p, int x, int y) {
        pieces[x][y] = p;  
    }

    /**
    * Gets the piece at position (x, y) on the board, or null if there is no piece. 
    * If (x, y) are out of bounds, returns null.
    **/
    public Piece pieceAt(int x, int y) {
        if (x > -1 && x < 8 && y > -1 && y < 8) {
            return pieces[x][y];
        }
        return null;
    }

    private void drawBoard(int N) {  
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);  //TODO this seems like has to do w/select
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + getString(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    /** Gets image string name to use in drawBoard */
    private String getString(Piece p) { 
        String type = "pawn";
        String color = "water";
        if (p.isFire()) {
            color = "fire";
        }
        if (p.isBomb()) {
            type = "bomb";
        }
        if (p.isShield()) {
            type = "shield";
        }
        if (p.isKing()) {
            return type + "-" + color + "-" + "crowned.png";
        }
        return type + "-" + color + ".png";
    }
    
    /**
    * Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds, 
    * returns null and prints an appropriate message. If there is no piece at (x, y), returns null
    **/
    public Piece remove(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Out of Bounds");
            return null;
        } else {
            Piece almostRemoved = pieceAt(x, y);
            pieces[x][y] = null;
            return almostRemoved;
        }
    }

    /**
    * Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), 
    * strictly from a geometry/piece-race point of view.
    **/
    private boolean validMove(int xi, int yi, int xf, int yf) {  
        Piece currPiece = pieceAt(xi, yi);
        boolean onFireSide = currPiece.isFire();
        boolean isKinged = currPiece.isKing();
        int changeInX = Math.abs(xf - xi);
        int changeInY = Math.abs(yf - yi);
        int middleX = (xf + xi) / 2;
        int middleY = (yf + yi) / 2;
        if (pieceAt(xf, yf) != null) {  //Can't select an occupied spot!
            return false;
        }
        if (changeInX > 2 || changeInY > 2) {  //Can't skip over multiple pieces!
            return false;
        }
        if (changeInX == 1 && changeInY == 1 && !hasMoved) {  //Simple move (no capturing)
            if (isKinged) {
                return true; 
            }
            if (yf > yi && onFireSide || yi > yf && !onFireSide) {
                return true; 
            } else {
                return false; 
            }
        }
        if (changeInX == 2 && changeInY == 2 && pieceAt(middleX, middleY) != null && pieceAt(middleX, middleY).isFire() != onFireSide) {
            if (isKinged) {
                return true;
            }
            if (yf > yi && onFireSide || yi > yf && !onFireSide) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /** Returns true if the square at (x, y) can be selected. */
    public boolean canSelect(int x, int y) {
        if (bombHasDied) {  //Can't move a dead piece!
            return false;
        }
        if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == firePlaying && (hasBeenSelected == null || !hasMoved)) {
            return true;
        } else {
            if (hasBeenSelected != null && !hasMoved && validMove(currPosX, currPosY, x, y)) {
                return true;
            }
            if (hasBeenSelected != null && hasBeenSelected.hasCaptured() && validMove(currPosX, currPosY, x, y)) {
                return true;
            }
            return false;
        }
    }

    /**
    * Returns whether or not the the current player can end their turn. 
    * To be able to end a turn, a piece must have moved or performed a capture.
    **/
    public boolean canEndTurn() {
        if (bombHasDied) {
            return true;
            }
        if (hasMoved) {
            return true; 
        }
        return false; 
    }
    
    /**
    * Called at the end of each turn. Handles switching of players and 
    * anything else that should happen at the end of a turn.
    **/
    public void endTurn() {
        hasBeenSelected.doneCapturing(); 
        hasBeenSelected = null;
        hasMoved = false;
        bombHasDied = false; 
        if (firePlaying) {
            firePlaying = false;
        } else {
            firePlaying = true; 
        }
    }
    
    /** Returns the winner of the game: "Fire", "Water", "No one", or null. */ 
    public String winner() {
        int numberFirePieces = 0;
        int numberWaterPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece thisPiece = pieceAt(i, j);
                if (thisPiece != null && thisPiece.isFire()) { 
                    numberFirePieces += 1;
                }
                if (thisPiece != null && !thisPiece.isFire()) { 
                    numberWaterPieces += 1;
                }
            }
        }
        if (numberFirePieces + numberWaterPieces == 0) {
            return "No one";
        } else {
            if (numberWaterPieces == 0) {
                return "Fire";
            }
            if (numberFirePieces == 0) {
                return "Water";
            } else {
                return null;
            }
        } 
    }

    /** Selects the square at (x, y). This method assumes canSelect (x,y) returns true. */
    public void select(int x, int y) { 
        if (pieceAt(x, y) == null) {
            hasBeenSelected.move(x, y);
            if (hasBeenSelected.isBomb() && !hasBeenSelected.hasCaptured() || !hasBeenSelected.isBomb()) {
                place(hasBeenSelected, x, y);  //Make sure if it's a Bomb, it hasn't exploded
            }
            hasMoved = true;
            currPosX = x;
            currPosY = y;
            if (hasBeenSelected.isBomb() && hasBeenSelected.hasCaptured()) {
                bombHasDied = true;  
            }
        } else {
            hasBeenSelected = pieceAt(x, y);
            currPosX = x;
            currPosY = y;
        }
    }             
}
