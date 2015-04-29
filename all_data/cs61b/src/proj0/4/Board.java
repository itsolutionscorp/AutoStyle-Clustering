
public class Board {
    // INSTANCE VARIABLES
    private Piece[][] pieces;           // Grid with Pieces in its corresponding location
    private boolean fireTeamsTurn;      // If true, it is Fire team's turn.  If false, it is Water team's turn
    private boolean hasSelected;        // Whether the current player has selected a piece or not
    private boolean hasMoved;           // Whether the current player has moved or not
    private boolean hasCaptured;        // Whether the current player has captured or not
    private int currentXLoc;            // Currently selected X coordinate of Piece
    private int currentYLoc;            // Currently selected Y coordinate of Piece
    
    // FINAL VARIABLE
    private static final int BOARDSIZE = 8;        // Side Length of the board
    
    /** Starts a GUI supported version of the game. */
    public static void main (String args[]) {
        StdDrawPlus.setXscale(0, BOARDSIZE);
        StdDrawPlus.setYscale(0, BOARDSIZE);
        Board game = new Board(false);

        while(game.winner() == null) {
            game.drawBoard(BOARDSIZE);
            // Mouse pressed
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (game.canSelect(x, y))
                    game.select(x, y);
            }         
            // Spacebar pressed
            if (StdDrawPlus.isSpacePressed()) {
                game.endTurn();
            }
            StdDrawPlus.show(20);
        }
        System.out.println("The winner is " + game.winner() + "!");
    }
    
    /** Constructor for the Board 
     *  If shouldBeEmpty is true, initializes an empty Board.
     *  Otherwise, initializes a Board with the default configuration.
     */
    public Board(boolean shouldBeEmpty) {
        // Board with no pieces and initialize variables
        pieces = new Piece[BOARDSIZE][BOARDSIZE];
        fireTeamsTurn = true;
        hasSelected = false;
        hasMoved = false;
        hasCaptured = false;
        currentXLoc = -1;
        currentYLoc = -1;
        
        
        // Add pieces if the board should not be empty
        if (!shouldBeEmpty) {
            // Fire Pieces
            for (int y = 0; y < 3; y ++) {
                for (int x = 0; x < pieces[y].length; x++) {
                    if (y == 0 && x % 2 == 0)
                        pieces[x][y] = new Piece(true, this, x, y, "pawn");
                    else if (y == 1 && x % 2 == 1)
                        pieces[x][y] = new Piece(true, this, x, y, "shield");
                    else if (y == 2 && x % 2 == 0)
                        pieces[x][y] = new Piece(true, this, x, y, "bomb");
                }
            }
            // Water Pieces
            for (int y = 5; y < 8; y ++) {
                for (int x = 0; x < pieces[y].length; x++) {
                    if (y == 5 && x % 2 == 1)
                        pieces[x][y] = new Piece(false, this, x, y, "bomb");
                    else if (y == 6 && x % 2 == 0)
                        pieces[x][y] = new Piece(false, this, x, y, "shield");
                    else if (y == 7 && x % 2 == 1)
                        pieces[x][y] = new Piece(false, this, x, y, "pawn");
                }
            }
        } 
    }

    /** Gets the piece at position(x, y) on the board, or null if there is no piece.
     *  If (x, y) are out of bounds, returns null.
     */
    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x, y))
            return null;
        return pieces[x][y];
    }
   
    /** Returns true if the the piece at (x, y) can be selected and moves the pieces if valid.
     *  A piece may be selected if it is the corresponding player’s turn and one of the following is true:
     *      The player has not selected a piece yet.
     *      The player has selected a piece, but did not move it.
     *  An empty square may be selected if one of the following is true:
     *      During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot 
     *          which is a valid move for the previously selected Piece.
     *      During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. 
     *          When performing multi-captures, you should only select the active piece once.
     *          All other selections should be valid destination points.
     */
    public boolean canSelect(int x, int y) {
        Piece selected = pieceAt(x, y);
        // If a piece is selected
        if (selected != null) {
            // If piece chosen is the corresponding player's turn
            if (selected.isFire() == fireTeamsTurn) {
                // The player has not selected a piece yet.
                if (!hasSelected)
                    return true;
                // The player has selected a piece, but did not move it.
                if (hasSelected && !hasMoved)
                    return true;
            }
        } else {
            // The player has previously selected a piece and is now selecting a tile to move the piece
            if (hasSelected) {
                // The player hasn't moved yet and is selecting a valid move
                if (!hasMoved && validMove(currentXLoc, currentYLoc, x, y))
                    return true;
                // The player has captured, and has selected another valid capture destination.
                if (hasCaptured && validMove(currentXLoc, currentYLoc, x, y) && Math.abs(currentXLoc - x) == 2)
                    return true; 
            }
        }

        return false;
    }

    /** Selects the piece at (x, y) if possible.
     *  Optionally, it is recommended to color the background of the selected square white on the GUI via the pen color function. 
     *  For any piece to perform a capture, that piece must have been selected first.
     */
    public void select(int x, int y) {
        if (hasSelected && pieceAt(x, y) == null) {
            // move the piece
            place(pieceAt(currentXLoc, currentYLoc), x, y);
            hasMoved = true;
        }
        hasSelected = true;
        currentXLoc = x;
        currentYLoc = y;
    }

    /** Places p at (x, y). 
     *      If (x, y) is out of bounds or if p is null, does nothing.
     *      If p already exists in the current Board, first removes it from its initial position.
     *      If another piece already exists at (x, y), p will replace that piece.
     */
    public void place(Piece p, int x, int y) {
        // If (x, y) is out of bounds or if p is null, does nothing.
        if (outOfBounds(x, y) || p ==null) 
            return;
        
        // If p already exists in the current Board, first removes it from its initial position.
        for (int i = 0; i < BOARDSIZE; i ++) {
            for (int j = 0 ; j < BOARDSIZE; j ++) {
                if (pieceAt(i, j) == p) {
                    remove(i, j);
                    i = j = BOARDSIZE;
                }
            }
        }
        
        // If another piece already exists at (x, y), p will replace that piece.
        pieces[x][y] = p;
        
        p.move(x, y);
        if (p.hasCaptured())
            hasCaptured = true;
    }
    
    /** Executes a remove and returns the piece that was removed. 
     *  If the input (x, y) is out of bounds, returns null and prints an appropriate message.
     *  If there is no piece at (x, y), returns null and prints an appropriate message.
     */
    public Piece remove(int x, int y) {
        // If the input (x, y) is out of bounds, returns null and prints an appropriate message.
        if (outOfBounds(x, y)) {
            System.out.println("ERROR: input (" + x + ", " + y + ") is out of bounds"); 
            return null;
        }
        
        // If there is no piece at (x, y), returns null and prints an appropriate message.
        Piece currentPiece = pieceAt(x, y);
        if (currentPiece == null) {
            System.out.println("ERROR: there is no piece at (" + x + ", " + y + ")"); 
            return null;
        }
        
        pieces[x][y] = null;
        return currentPiece;
    }

    /** Returns whether or not the the current player can end their turn. 
     *  To be able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn() {
        if (hasMoved || hasCaptured)
            return true;
        return false;
    }

    /** Handles switching of players and anything else that should happen at the end of a turn.
     *  Called at the end of each turn. 
     */
    public void endTurn() {
        if (canEndTurn()) {
            fireTeamsTurn = !fireTeamsTurn;
            hasSelected = false;
            hasMoved = false;
            hasCaptured = false;
            currentXLoc = -1;
            currentYLoc = -1;
            
            for (int i = 0; i < BOARDSIZE; i++) {
                for (int j = 0; j < BOARDSIZE; j++) {
                    if (pieceAt(i, j) != null)
                        pieceAt(i, j).doneCapturing();
                }
            }
        }
    }

    /** Returns the winner of the game. 
     *  "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over.
     *  Assumes there is no stalemate situation in which the current player has pieces but cannot legally move any of them 
     *      (In this event, just leave it at null).
     *  Determine the winner solely by the number of pieces belonging to each team.
     */
    public String winner() {
        int firePieces = 0;
        int waterPieces = 0;
        
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (p.isFire())
                        firePieces += 1;
                    else
                        waterPieces += 1;
                }
            }
        }
        
        if (firePieces == 0 && waterPieces  == 0)
            return "No one";
        if (firePieces == 0)
            return "Water";
        if (waterPieces == 0)
            return "Fire";
        
        return null;
    } 

    
    // ADDITIONAL PRIVATE HELPER METHODS
    /** Draws an N x N board with each Piece at its corresponding location. 
     *  Adapted from: http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == currentXLoc && j == currentYLoc)
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // Draws in the Piece at location i, j if there is one
                Piece p = pieceAt(i, j);
                if (p != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getPieceImageLocation(p), 1, 1);
                }
            }
        }
    }

    /** Returns a String of the directory path to the image */
    private String getPieceImageLocation(Piece p) {
        // Directory location
        String fileName = "img/";

        // Bomb, Shield, or Pawn
        if (p.isBomb())
            fileName += "bomb";
        else if (p.isShield())
            fileName += "shield";
        else
            fileName += "pawn";
        
        // Fire or Water
        if (p.isFire())
            fileName += "-fire";
        else
            fileName += "-water";
        
        // King or not
        if (p.isKing())
            fileName += "-crowned";
        
        // Add image type
        fileName += ".png";
        
        return fileName;
    }

    /** Returns a boolean on whether the coordinates are out of bounds */
    private boolean outOfBounds(int x, int y) {
        return (x < 0 || x >= BOARDSIZE || y < 0 || y >= BOARDSIZE );
    }
    
    /** Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf)
     *  Strictly from a geometry/piece-race point of view. 
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi, yi);
        int xDiff = xf - xi;
        int yDiff = yf - yi;
        int midX = (xi + xf) / 2 ;
        int midY = (yi + yf) / 2 ;
        
        if (p != null && (Math.abs(xDiff) == Math.abs(yDiff))) {
            if (p.isKing()) {
                // Regular move for King
                if (Math.abs(yDiff) == 1 && pieceAt(xf, yf) == null)
                    return true;
                // Capture move for King
                if (Math.abs(yDiff) == 2 && pieceAt(midX, midY) != null && pieceAt(midX, midY).isFire() != p.isFire())
                    return true;
            } else if (p.isFire()) {
                // Regular move for Fire
                if (yDiff == 1 && pieceAt(xf, yf) == null)
                    return true;
                // Capture move for Fire
                if (yDiff == 2 && pieceAt(midX, midY) != null && pieceAt(midX, midY).isFire() != p.isFire())
                    return true;
            } else {
                // Regular move for Water
                if (yDiff == -1 && pieceAt(xf, yf) == null)
                    return true;
                // Capture move for Water
                if (yDiff == -2 && pieceAt(midX, midY) != null && pieceAt(midX, midY).isFire() != p.isFire())
                    return true;
            }
        }
        
        return false;
    }
}