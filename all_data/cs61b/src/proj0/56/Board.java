/**
 * CHECKERS BOARD GAME
 *
 * Created for CS61B:
 * - implements GUI and required API
 * - implements "Bonus for Bosses" (n, undo, stalemate)
 *
 * Instructions:
 * CLICK to select, capture, move
 * SPACE to end turn
 * N to start new game
 * V to toggle verbose mode
 *
 * @author Alvin Wan
 */

public class Board {

    /*************************
     * INITIALIZATION 
     ************************* */

    /* size of square board */
    private int N;

    /* container for all pieces */
    private Piece[][] pieces;

    /* turn properties */
    private Piece selectedPiece;
    private int[] selectedCoords;
    private boolean hasMovedPiece;
    private boolean fireTurn;
    private boolean started;
    
    /**
     * Starts a GUI for the game, and implements click,
     * space, N, and V commands.
     */
    public static void main(String[] args) {

        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        Board board = new Board(false);
        String winner = null;
        
        while (winner == null) {
            
            if (StdDrawPlus.mousePressed()) {
                
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                
                if (board.canSelect(x, y)) board.select(x, y);
            }
            
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                    winner = board.winner();
                }
            }
            if (StdDrawPlus.isNPressed()) {
                board.initialize();
                board.setup();
            }

            board.drawBoard();

        }

        System.out.println(winner);

        return;
    }

    /**
     * Board constructor that initializes StdDraw.
     * @param shouldBeEmpty: boolean
     */
    public Board(boolean shouldBeEmpty) {
        N = 8;
        started = false;

        initialize();

        if (shouldBeEmpty != true) setup();
    }

    /**
     * Initialize by resetting all turn and game
     * properties. Note that this will not setup
     * the default congiration for a checkers game,
     * instead only providing state-variable resets. 
     */
    private void initialize() {
        fireTurn = true;
        resetTurn();
        pieces = new Piece[N][N];
    }

    /**
     * Setup by adding all pieces according to default
     * configuration. There are 6 rows, one row on each
     * side for each piece type: (1) pawn, (2) shield,
     * and (3) king.
     */
    private void setup() {
        // add Water pieces
        placePieces(false, N - 1, 1, "pawn");
        placePieces(false, N - 2, 0, "shield");
        placePieces(false, N - 3, 1, "bomb");

        // add Fire pieces
        placePieces(true, 0, 0, "pawn");
        placePieces(true, 1, 1, "shield");
        placePieces(true, 2, 0, "bomb");
    }

    /******************************
     * GUI - ANIMATIONS 
     ******************************/

    /**
     * Draws the board, and adds all pieces. It
     * performs no game logic. The selected square
     * -- according to the GUI -- is determined
     * using the selectedPiece. 
     */
    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece piece = pieceAt(i, j);

                if (piece != null &&
                    selectedPiece == piece)  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0)  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (piece != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getPieceImagePath(piece), 1, 1);
                }
            }
        }
        StdDrawPlus.show(20);
    }

    /** Assembles image path for piece based on type */
    private String getPieceImagePath(Piece piece) {
        
        StringBuilder str = new StringBuilder("img/");
        
        if (piece.isBomb()) str.append("bomb"); // Determine piece type
        else if (piece.isShield()) str.append("shield");
        else str.append("pawn");
        
        if (piece.isFire()) str.append("-fire"); // Determine piece side
        else str.append("-water");
        
        if (piece.isKing()) str.append("-crowned"); // Determine if crowned

        return str.append(".png").toString(); // add file extension, then build
    }

    /** Takes average of two integers. */
    private int average(int a, int b) {
        return (a+b)/2;
    }

    /** Tests if one value is between two others. */
    private boolean between(int gt, int term, int lt) {
        return gt <= term && term <= lt;
    }

    /* **************************************
    * BASIC BOARD FUNCTIONS
    * *************************************** */

    /**
     * Return piece at position, or return null if (a) there is no
     * piece or (b) the position is out of bounds.
     * @param x: column
     * @param y: row
     * @return Piece at the position
     */
    public Piece pieceAt(int x, int y) {
        return !inBound(x, y) ? null : pieces[x][y];
    }

    /**
     * Add specified piece at the indicated position, removing any
     * other piece at the target position.
     * @param p: Piece
     * @param x: column
     * @param y: row
     */
    public void place(Piece p, int x, int y) {
        
        started = true;

        if (!inBound(x, y)) { // if out of bounds, kill operation
            System.out.println("Error: Coordinates not in bound.");
            return;
        }
        
        if (pieceAt(x, y) != null) { // if piece already there, remove it
            remove(x, y);
        }

        pieces[x][y] = p; // add copy to new slot
    }

    /** Removes piece by position.
     * @param x: column
     * @param y: row
     */
    public Piece remove(int x, int y) {
        
        if (!inBound(x, y)) {
            System.out.println("Error: Coordinates not in bound.");
            return null;
        }
        
        Piece piece = pieceAt(x, y);

        if (piece == null) {
            System.out.println("Error: No piece exists at specified coordinates.");
            return null;
        }

        pieces[x][y] = null;

        return piece;
    }

    /** Adds a row of pieces to the board at a specified offset.
     * @param isFire: is or is not Fire player
     * @param row: integer for row ID
     * @param offset: integer for ofset from left
     * @param type: String for piece type
     */
    private void placePieces(boolean isFire, int row, int offset, String type) {
        for (int i=offset; i<N; i+=2) {
            Piece p = new Piece(isFire, this, i, row, type);
            place(p, i, row);
        }
    }

    /* ***************************************
    * BASIC GAME FUNCTIONS
    *  *************************************** */

    /** Resets turn properties */
    private void resetTurn() {
        selectedCoords = new int[2];
        selectedPiece = null;
        hasMovedPiece = false;
    }

    /** Test if coordinates are within bondaries, specified by N.*/
    private boolean inBound(int x, int y) {
        return between(0, x, N-1) && between(0, y, N-1);
    }

    /**
     * Selects a square, without checking for validity.
     * If the square contains a piece, the piece is
     * prepared for movement. If the square does not
     * contain a piece, the previously-selected piece
     * is moved to the new location. 
     * @param x: column
     * @param y: row
     */
    public void select(int x, int y) {

        Piece piece = pieceAt(x, y);

        if (piece != null) {
            selectedPiece = piece;
            selectedCoords = new int[]{x, y};
        } else if (selectedPiece != null) {
            hasMovedPiece = true;
            selectedPiece.move(x, y);
            selectedCoords = new int[]{x, y};
        }
    }

    /**
     * Determine if the player can select a slot at the
     * given coordinates. If piece exists, select it. If
     * empty square is selected, move your most recently
     * selected piece. 
     * @param x: column
     * @param y: row
     * @return boolean value
     */
    public boolean canSelect(int x, int y) {
        
        boolean can = false;

        Piece piece = pieceAt(x, y);
        if ((piece != null && isOwned(piece) &&
            (   selectedPiece == null ||
                hasMovedPiece == false
            )) ||
            (piece == null &&
                ((selectedPiece != null && hasMovedPiece == false && selectedCoords != null && validMove(selectedCoords[0], selectedCoords[1], x, y)) ||
                (selectedPiece != null && selectedPiece.hasCaptured() && validCapture(selectedCoords[0], selectedCoords[1], x, y))))
            ) {
                can = true;
            }

        return can;
    }

    /* ***************************************
    * ADVANCED GAME FUNCTIONS
    *  *************************************** */

    /**
     * Get number of jumps from one slot to another.
     * @param xi: original column
     * @param yi: original row
     * @param xf: target column
     * @param yf: target row
     */
    private int jumps(int xi, int yi, int xf, int yf) {
        return Math.abs(yf-yi);
    }

    /**
     * Get the piece jumped by original and final slots.
     * @param xi: original column
     * @param yi: original row
     * @param xf: target column
     * @param yf: target row
     * @return Piece jumped
     */
    private Piece jumpedPiece(int xi, int yi, int xf, int yf) {
        return pieceAt(average(xi, xf), average(yi, yf));
    }

    /**
     * Check if target is diagonal to original
     * position.
     * @param xi: original column
     * @param yi: original row
     * @param xf: target column
     * @param yf: target row
     */
    private boolean isDiagonal(int xi, int yi, int xf, int yf) {
        return Math.abs(xf-xi) == Math.abs(yf-yi);
    }

    /**
     * Check if target is a move "forward".
     * @param yi: original row
     * @param yf: target row
     * @return boolean
     */
    private boolean isForward(int yi, int yf, Piece piece) {
        return piece != null && piece.isKing() ? true : (fireTurn ? yf > yi : yi > yf);
    }

    /**
     * Determine if the current player controls
     * or "owns" the piece. 
     * @param piece: target piece
     * @return boolean
     */
    private boolean isOwned(Piece piece) {
        return piece.isFire() == fireTurn;
    }

    /**
     * Check if the capture is valid for the piece:
     *   - if target is diagonal
     *   - if piece exists in between
     *   - if piece is other player
     * @param x: column
     * @param y: row
     * @param piece: piece
     * @return boolean
     */
    private boolean validCapture(int xi, int yi, int x, int y) {
        Piece jumped = jumpedPiece(xi, yi, x, y);

        return (isDiagonal(xi, yi, x, y) &&
                jumps(xi, yi, x, y) == 2 &&
                jumped != null &&
                !isOwned(jumped)) ? true : false;
    }

    /**
     * Check if the move or capture is valid for the piece:
     *   - check if target is in bound
     *   - check for stacking of pieces   
     *   - check if move is diagonal
     *   - check if move is "forward" (relative to player and piece crowned status)
     *   - check if one space or valid capture
     * @param x: column
     * @param y: row
     * @return boolean
     */
    private boolean validMove(int xi, int yi, int x, int y, Piece piece) {
        return (inBound(x, y) &&
                pieceAt(x, y) == null &&
                isDiagonal(xi, yi, x, y) &&
                isForward(yi, y, piece) &&
                (jumps(xi, yi, x, y) == 1 || validCapture(xi, yi, x, y))) ? true : false;
    }
    
    private boolean validMove(int xi, int yi, int x, int y) {
        return validMove(xi, yi, x, y, selectedPiece);
    }

    /** Whether or not the current player can end their turn. */
    public boolean canEndTurn() {
        return hasMovedPiece;
    }

    /**
     * Ends the turn and resets parameters, but first
     * checks for endTurn capability. Then:
     *  - players are switched
     *  - the selectedPiece resets its capturing log
     *  - default turn variables are reset
     *  - test for a winner 
     */
    public void endTurn() {
        if (selectedPiece != null)
            selectedPiece.doneCapturing();
        fireTurn = !fireTurn;
        resetTurn();
    }

    /**
     * Returns the winner or null. Checks if:
     * - either player or bother players have 0 pieces
     *   on the board
     * - if current player has no valid moves for all
     *   pieces
     */
    public String winner() {
        
        if (!started) {
            return "No one";
        }
            
        int waterCount = 0;
        int fireCount = 0;
        boolean stalemate = true;

        for (int i=0;i<pieces.length;i++) {
            for (int j = 0; j < pieces[i].length;j++) {
                Piece piece = pieceAt(i, j);
                if (piece != null) {
                    if (piece.isFire()) fireCount += 1;
                    else waterCount += 1;
                    if (piece.isFire() == fireTurn && canMove(piece, i, j)) {
                        stalemate = false;
                    }
                }
            }
        }

        if (fireCount == 0 && waterCount == 0) return "No one";
        else if (fireCount == 0) return "Water";
        else if (waterCount == 0) return "Fire";
        else if (stalemate) System.out.println("Stalemate");

        return null;
    }

    /* ***************************************
    * BONUS FOR BOSSES
    *  *************************************** */

    /**
     * Test if a specific piece has a move option
     * in any of the 8 possible squares.
     * @param piece: target piece
     * @param x: column
     * @param y: row
     * @return boolean
     */
    private boolean canMove(Piece piece, int x, int y) {
        int xi = Math.max(x-1, 0);
        int yi = Math.max(y-1, 0);
        int xf = Math.min(x+1, N);
        int yf = Math.min(y+1, N);
        int xio = Math.max(x-2, 0);
        int yio = Math.max(y-2, 0);
        int xfo = Math.min(x+2, N);
        int yfo = Math.min(y+2, N);

        int[][] coords = {
                {xi, yi},
                {xi, yf},
                {xf, yf},
                {xf, yi},
                {xio, yio},
                {xio, yfo},
                {xfo, yfo},
                {xfo, yio}
        };

        for (int[] coord: coords) {
            int cx = coord[0];
            int cy = coord[1];

            if (validMove(x, y, cx, cy, piece)) return true;
        }

        return false;
    }
}