/** 
 *  @author Zhongtian Wang
 */

public class Board {

    /** Set up broad properties for 8 x 8 non-empty board */
    private static final int N = 8;

    /** Location of pieces. */
    private Piece[][] pieces;

    /** Game statistics. */
    private boolean isFireTurn;
    private boolean notYetSelected;
    private boolean notYetMoved;
    private boolean bombExploded;

    private int currentSelX;
    private int currentSelY;

    /**
     * Constructs a new Board.
     *
     * @param shouldBeEmpty Initializes an empty Board if it's true
     */
    public Board(boolean shouldBeEmpty) {
        // Initialize pieces
        pieces = new Piece[N][N];
        
        if (!shouldBeEmpty) {
            // Initialize initial position
            // Fire side
            place(new Piece(true, this, 0, 0, "pawn"), 0, 0);
            place(new Piece(true, this, 2, 0, "pawn"), 2, 0);
            place(new Piece(true, this, 4, 0, "pawn"), 4, 0);
            place(new Piece(true, this, 6, 0, "pawn"), 6, 0);

            place(new Piece(true, this, 1, 1, "shield"), 1, 1);
            place(new Piece(true, this, 3, 1, "shield"), 3, 1);
            place(new Piece(true, this, 5, 1, "shield"), 5, 1);
            place(new Piece(true, this, 7, 1, "shield"), 7, 1);

            place(new Piece(true, this, 0, 2, "bomb"), 0, 2);
            place(new Piece(true, this, 2, 2, "bomb"), 2, 2);
            place(new Piece(true, this, 4, 2, "bomb"), 4, 2);
            place(new Piece(true, this, 6, 2, "bomb"), 6, 2);

            // Water side
            place(new Piece(false, this, 1, 5, "bomb"), 1, 5);
            place(new Piece(false, this, 3, 5, "bomb"), 3, 5);
            place(new Piece(false, this, 5, 5, "bomb"), 5, 5);
            place(new Piece(false, this, 7, 5, "bomb"), 7, 5);

            place(new Piece(false, this, 0, 6, "shield"), 0, 6);
            place(new Piece(false, this, 2, 6, "shield"), 2, 6);
            place(new Piece(false, this, 4, 6, "shield"), 4, 6);
            place(new Piece(false, this, 6, 6, "shield"), 6, 6);

            place(new Piece(false, this, 1, 7, "pawn"), 1, 7);
            place(new Piece(false, this, 3, 7, "pawn"), 3, 7);
            place(new Piece(false, this, 5, 7, "pawn"), 5, 7);
            place(new Piece(false, this, 7, 7, "pawn"), 7, 7);
        }

        // Initialize game statistics
        isFireTurn = true;
        notYetSelected = true;
        notYetMoved = true;
        bombExploded = false;
    }

    /**
     * Gets the piece at position (x, y) on the board.
     *
     * @param x x position for the board.
     * @param y y position for the board.
     * @return The piece at (x, y) on the board, or null if there is no piece or out of bound.
     */
    public Piece pieceAt(int x, int y) {
        // Check bounds
        if (x >= N || y >= N || x < 0 || y < 0) {
            return null;
        }

        return pieces[x][y];
    }

    /**
     * Returns true if the square at (x, y) can be selected.
     *
     * @param x x position for the board.
     * @param y y position for the board.
     * @return The piece at (x, y) on the board, or null if there is no piece or out of bound.
     */
    public boolean canSelect(int x, int y) {
        /**
        * A piece may be selected if it is the corresponding player’s turn and one of the following is true:
        * - The player has not selected a piece yet.
        * - The player has selected a piece, but did not move it.
        *
        * An empty square may be selected if one of the following is true:
        * - During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
        * - During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
        */
        
        if (bombExploded) {
            return false;
        }

        // If clicking on a piece (if it's a piece)
        Piece potentialPiece = pieceAt(x, y);
        
        // It's indeed a piece
        if (potentialPiece != null) {

            // If fire turn and selected a fire piece, or
            // if water turn and selected a water piece
            if ((isFireTurn && potentialPiece.isFire()) ||
                (!isFireTurn && !potentialPiece.isFire())) {

                // Return true if the piece is not yet selected or not yet moved
                if (notYetSelected || notYetMoved) {
                    // notYetSelected = false;
                    return true;
                } else {
                    return false;
                }
            }
            // Selected piece of another team
            else {
                return false;
            }
        }

        // It's not a piece
        else {
            // If the player has already selected a Piece and it's now selecting a position to move to
            // Either move or capture
            if (!notYetSelected && validMove(currentSelX, currentSelY, x, y)) {
                return true;
            }

            // Not a valid move or haven't yet select a place
            else {
                return false;
            }
        }
    }

    /**
     * Check if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf).
     * @param x Initial x position for the move.
     * @param y Initial y position for the move.
     * @param x Final x position for the move.
     * @param y Final y position for the move.
     * @return True if can move, false otherwise.
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        int dx = xf - xi;
        int dy = yf - yi;
        int dx_abs = Math.abs(dx);
        int dy_abs = Math.abs(dy);

        Piece piece = pieceAt(xi, yi);

        // Check if the piece has already king-ed
        boolean isKing = piece.isKing();

        // Only move forward if not king
        if (!isKing && ((isFireTurn && (dy < 0)) || !isFireTurn && (dy > 0))) {
            return false;
        }

        // If haven't moved yet and it's a move
        if (notYetMoved && dx_abs == 1 && dy_abs == 1) {
            return true;
        }

        // Capture
        else if (dx_abs == 2 && dy_abs == 2) {

            // If only moved, but haven't yet captured
            if (!notYetMoved && !piece.hasCaptured()) {
                return false;
            }

            // Check if capturing an enemy piece
            Piece toCapture = pieceAt(xi + dx / 2, yi + dy / 2);

            // If there is such piece
            if (toCapture != null && !toCapture.isFire() == isFireTurn) {
                return true;
            }
        }

        // Can't move that far
        return false;
    }

    /**
     * Selects the piece at (x, y) if possible.
     *
     * @param x x position to select.
     * @param y y position to select.
     */
    public void select(int x, int y) {

        // Selecting a destination
        if (pieceAt(x, y) == null) {
            Piece currentPiece = pieceAt(currentSelX, currentSelY);
            place(currentPiece, x, y);
            notYetMoved = false;

            // Actual move - handle explosion
            currentPiece.move(x, y);

            // Check if exploded
            if (pieces[x][y] == null) {
                bombExploded = true;
            }
        }

        // Selecting a new piece
        else {
            notYetSelected = false;
        }

        currentSelX = x;
        currentSelY = y;
    }

    /**
     * Places p at (x, y).
     *
     * @param p the Piece to place.
     * @param x x position to place.
     * @param y y position to place.
     */
    public void place(Piece p, int x, int y) {

        // If x, y is in bound and the piece is not null
        if (x < N && x >= 0 && y < N && y >= 0 && p != null) {

            // Find the piece in the 2D array
            // And set it to null
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    // Found a match
                    if (p == pieces[i][j]) {

                        // Set the original one 
                        pieces[i][j] = null;

                        // Add it to the array
                        pieces[x][y] = p;

                        // End so that we won't add a duplicate one
                        return;
                    }
                }
            }

            // If the piece is not found in the array
            // Add it to the array
            pieces[x][y] = p;
        }
    }

    /**
     * Executes a remove. 
     *
     * @param x x position to remove.
     * @param y y position to remove.
     * @return the piece that was removed or null and print appropiate message.
     */
    public Piece remove(int x, int y) {
        if ((x >= N) || (y >= N)) {
            System.out.println("Coordinate out of bound!");
            return null;
        }

        Piece toRemove = pieceAt(x, y);

        if (toRemove == null) {
            System.out.println("There is no piece to remove at this coordinate");
            return null;
        }

        pieces[x][y] = null;

        return toRemove;
    }

    /**
     * Returns whether or not the current player can end their turn.
     *
     * @return True if the current player can end their turn, false otherwise.
     */
    public boolean canEndTurn() {
        return !notYetSelected && !notYetMoved;
    }

    /**
     * Called at the end of each turn. 
     * Handles switching of players and anything else that should happen at the end of a turn.
     */
    public void endTurn() {

        // Check if any piece captured another
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

                Piece currentPiece = pieceAt(x, y);

                if (currentPiece != null && currentPiece.hasCaptured()) {
                    currentPiece.doneCapturing();
                }
            }
        }

        // Switch team
        isFireTurn = !isFireTurn;

        // Reset flags
        notYetSelected = true;
        notYetMoved = true;
        bombExploded = false;
    }

    /**
     * Returns the winner of the game.
     *
     * @return  "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over.
     */
    public String winner() {
        int fireCount = 0;
        int waterCount = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

                Piece piece = pieces[x][y];
                if (piece != null) {
                    if (piece.isFire()) {
                        fireCount += 1;
                    } else {
                        waterCount += 1;
                    }
                }
            }
        }

        if (fireCount != 0 && waterCount != 0) {
            return null;
        }

        else if (fireCount != 0 && waterCount == 0) {
            return "Fire";
        }

        else if(fireCount == 0 && waterCount != 0) {
            return "Water";
        }

        return "No one";
    }


    /***********************************************************
     * GUI Methods
     ***********************************************************/

    /**
     * Draws an N x N board.
     * Adapted from Josh Hug - StdDrawDemo.java.
     * 
     * @param N length of side
     */
    private void drawBoard(int N) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    /**
     *  Draw current selection
     */
    private void drawSelection() {
        if (!notYetSelected && !bombExploded) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(currentSelX + .5, currentSelY + .5, .5);
        }
    }

    /**
     *  Draw pieces
     */
    private void drawPieces() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {

                Piece currentPiece = pieces[x][y];

                // If there is supposed to be a piece
                if (currentPiece != null) {

                    // ----- First, determine name for the image -----

                    String imgName = "img/";

                    // Determind type
                    if (currentPiece.isBomb())        imgName = imgName.concat("bomb-");
                    else if (currentPiece.isShield()) imgName = imgName.concat("shield-");
                    else                              imgName = imgName.concat("pawn-");

                    // Determine team
                    if (currentPiece.isFire()) imgName = imgName.concat("fire");
                    else                       imgName = imgName.concat("water");

                    // Determine crown status
                    if (currentPiece.isKing()) imgName = imgName.concat("-crowned.png");
                    else                       imgName = imgName.concat(".png");

                    // ----- Second, Draw the piece out -----

                    StdDrawPlus.picture(x + .5, y + .5, imgName, 1, 1);
                }
            }
        }
    }


    /***********************************************************
     * IO Events
     ***********************************************************/

    /**
     * Mouse listener
     *
     * @param b the current board
     */
    private static void pollMouseEvent(Board b) {
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            
            if (b.canSelect((int)x, (int)y)) {
                b.select((int)x, (int)y);    
            }
        }
    }

    /**
     * Keyboard listener
     *
     * @param b the current board
     */
    private static void pollKeyboardEvent(Board b) {
        if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            b.endTurn();
        }
    }

    /** 
     * Starts a GUI supported version of the game. 
     */
    public static void main(String[] args) {

        // Create new board
        Board b = new Board(false);

        // Main GUI
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {

            // ----- GUI -----

            // Draw out board
            b.drawBoard(N);

            // Handle color changes of the blocks
            b.drawSelection();

            // Draw out pieces
            b.drawPieces();


            // ----- IO -----

            // Capture mouse events
            pollMouseEvent(b);

            // Listen keyboard activity
            pollKeyboardEvent(b);
            

            StdDrawPlus.show(1);
        }
    }
}