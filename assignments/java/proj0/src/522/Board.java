/**
 * Project 0: Checkers61b
 * 
 * "We all love the classic game of Checkers. In this project, we add a twist by
 * introducing two new pieces: Bomb Pieces and Shield Pieces. For details on how
 * these pieces differ, see Rules of Checkers61b. Starting from scratch, you
 * will implement your own GUI-supported version of this game. You should use
 * the provided StdDraw library to implement the GUI."
 *
 * @author Sterling Engle
 * 
 * http://berkeley-cs61b.github.io/public_html/materials/proj/proj0/proj0.html
 *
 */

/*
 * Everything related to the checker board except each of the pieces in Piece
 */
public class Board {
    
    private final static int SIZE = 8;      // Board size
    private final static int FIRE = 0;      // Piece side() for fire
    private final static int WATER = 1;     // Piece side() for water
    private final static String PAWN = "pawn";
    private final static String BOMB = "bomb";
    private final static String SHIELD = "shield";
    
    /* Caches image filenames for fast look-up:
     *
     * bit 0 = fire(0)   || water(1)
     * bit 1 = pawn(0)   || bomb(1)
     * bit 2 = pawn(0)   || shield(1)
     * bit 3 = normal(0) || crowned(1)
     */
    private final static String[] imageFile = 
         { "img/pawn-fire.png",             // 0: 0000
            "img/pawn-water.png",           // 1: 0001
            "img/bomb-fire.png",            // 2: 0010
            "img/bomb-water.png",           // 3: 0011
            "img/shield-fire.png",          // 4: 0100
            "img/shield-water.png",         // 5: 0101
            null, null, 
            "img/pawn-fire-crowned.png",    // 8: 1000
            "img/pawn-water-crowned.png",   // 9: 1001
            "img/bomb-fire-crowned.png",    // 10: 1010
            "img/bomb-water-crowned.png",   // 11: 1011
            "img/shield-fire-crowned.png",  // 12: 1100
            "img/shield-water-crowned.png"  // 13: 1101
    };

    private boolean gui = false;            // set true to enable the GUI
    private Piece[][] board = null;         // stores the Piece objects
    private int[] pieceCount = { 0, 0 };    // number of pieces each side
    private int player = FIRE;              // current player's turn

    private Piece selectedPiece = null;     // piece user selected, if any
    private int selectedX = -1;             // location of X before moving
    private int selectedY = -1;             // location of Y before moving
    private int newX = -1;                  // new location of X to move to
    private int newY = -1;                  // new location of Y to move to

    private boolean pieceMoved = false;     // was a piece just moved?
    private boolean pieceCaptured = false;  // was a piece just captured?
    private boolean bombCapture = false;    // did a bomb just capture and blow up?


    /*
     * The constructor for Board. Note that an empty Board could possibly be
     * useful for testing purposes.
     * 
     * @param shouldBeEmpty if true, initializes empty Board, else default
     * configuration.
     * 
     * @return New Board object
     */
    public Board(boolean shouldBeEmpty) {
        
        board = new Piece[SIZE][SIZE];  // store Piece objects here

        if (!shouldBeEmpty) {
            for (int x = 0; x < SIZE; x += 2) {

                // fire side:
                setPieceAt(new Piece(true, this, x, 0, PAWN), x, 0);
                if (x < SIZE - 1)
                    setPieceAt(new Piece(true, this, x + 1, 1, SHIELD), x + 1,
                               1);
                setPieceAt(new Piece(true, this, x, 2, BOMB), x, 2);

                // water side:
                if (x < SIZE - 1) {
                    setPieceAt(new Piece(false, this, x + 1, SIZE - 1, PAWN),
                               x + 1, SIZE - 1);
                    setPieceAt(new Piece(false, this, x + 1, SIZE - 3, BOMB),
                               x + 1, SIZE - 3);
                    }
                setPieceAt(new Piece(false, this, x, SIZE - 2, SHIELD), x,
                           SIZE - 2);
            }
        }
    }

    /*
     * Gets the piece at position (x, y) on the board.
     * 
     * @param x x board position (0, 0) bottom left corner
     * @param y y board position (7, 7) top right corner
     * 
     * @return Piece at position (x, y) on the board, or null if no piece or
     * invalid (x, y)
     */
    public Piece pieceAt(int x, int y) {
        if (validSquare(x, y))
            return board[x][y];
        else
            return null;
    }

    /**
     * sets the piece at (x, y) to p
     * 
     * @param p Piece object to place on the board
     * @param x
     * @param y
     */
    private void setPieceAt(Piece p, int x, int y) {
        if (validSquare(x, y)) {
            Piece old = pieceAt(x, y);
            if (old != null)    // AG piece the user can't put there
                pieceCount[old.side()]--; // or a piece is being removed
            board[x][y] = p;
            if (p != null)
                pieceCount[p.side()]++;
        }
        return;
    }

    /* 
     * Returns true if the square at (x, y) can be selected.
     * 
     * 
     * A square with a piece may be selected if it is the corresponding 
     * player's turn and one of the following is true:
     * 
     *   The player has not selected a piece yet.
     * 
     *   The player has selected a piece, but did not move it.
     * 
     * An empty square may be selected if one of the following is true:
     * 
     *   During this turn, the player has selected a Piece which hasn't 
     *   moved yet and is selecting an empty spot which is a valid move 
     *   for the previously selected Piece.
     *   
     *   During this turn, the player has selected a Piece, captured, and
     *   has selected another valid capture destination. When performing
     *   multi-captures, you should only select the active piece once; 
     *   all other selections should be valid destination points.
     * 
     * @param x
     * 
     * @param y
     * 
     * @return true if piece may be selected, else false
     */
    public boolean canSelect(int x, int y) {
        if (validSquare(x, y)) {
            if (bombCapture) // bomb was previously used to capture turn over
                return false;

            Piece p = pieceAt(x, y);

            if (selectedPiece == null) {    // haven't selected a piece yet?
                if (p == null || p.side() != player)
                    return false;   // no piece to select or wrong player's
                else
                    return true;

            } else if (p != null) {
                if (!pieceMoved && p.side() == player) // && p != selectedPiece)
                    return true;   // can select different or same piece if not moved yet
                else
                    return false;   // can't select because moved or is same one
            } else 
                return validMove(selectedPiece, selectedX, selectedY, p, x, y);
        } else
            return false;
    }

    /*
     * Returns true if the piece at (xi, yi) can either move to (xf, yf) or
     * capture to (xf, yf) in a valid fashion compatible with the rules.
     * 
     * @param xi initial x board location
     * 
     * @param yi initial y board location
     * 
     * @param xf final x board location to check for validity
     * 
     * @param yf final y board location to check for validity
     * 
     * @return Returns true if the piece at (xi, yi) can either move to (xf, yf)
     * or capture to (xf, yf) in a valid fashion compatible with the rules.
     * 
     * Suppose that we have a hero and an enemy that we're going to capture. Are
     * you sure that you're selecting the hero and selecting the empty space
     * behind the enemy to execute the capture? In order to execute a capture,
     * you will never actually click the victim.
     */
    private boolean validMove(Piece pI, int xi, int yi, Piece pF, int xf, int yf) {
        if (validSquare(xi, yi)) {  // validSquare(xf, yf) checked by canSelect()

            if (pI == null) // fix AG Test 11 gave us bad input?
                return false;
            if (pF != null)
                return false;

            int xDelta = xf - xi;
            int yDelta = yf - yi;
            int xAbsDelta = Math.abs(xDelta);
            int yAbsDelta = Math.abs(yDelta);

            if (xAbsDelta != yAbsDelta) // not a diagonal move?
                return false;

            // check if direction and length of move are both legal:
            if (pI.isKing()) {
                if ((xAbsDelta < 1 || yAbsDelta > 2))
                    return false;       // kings can move 1, 2 squares if capture
            } else if (pI.isFire()) {
                if (yDelta < 1 || yDelta > 2)
                    return false;       // fire pieces move up
            } else  // otherwise water
                if (yDelta < -2 || yDelta > -1)
                    return false;       // water pieces move down

            if (xAbsDelta == 2) {       // check for legal capture
                Piece pCapture = pieceAt(xi + (xDelta >> 1), yi + (yDelta >> 1));

                if (pCapture == null || pCapture.side() == pI.side())
                    return false;   // no piece to capture or it's same player
                else
                    return true;
            } else if (!pieceCaptured)
                return true;    // not capturing so OK to move
            else
                return false;   // already captured and this is not another one
        } else
            return false;       // square we're moving from is invalid
    }

    /*
     * Selects the square at (x, y). This method assumes canSelect (x,y)
     * returns true. Optionally, it is recommended to color the background 
     * of the selected square white on the GUI via the pen color function. 
     * For any piece to perform a capture, that piece must have been selected 
     * first. If you select a square with a piece, you are prepping that piece
     * for movement. If you select an empty square (assuming canSelect returns
     * true), you should move your most recently selected piece to that square.
     * 
     * @param x x board position to select
     * 
     * @param y y board position to select
     */
    public void select(int x, int y) {

        Piece p = pieceAt(x, y);

        if (selectedPiece == null) {
            selectedPiece = p;
            selectedX = x;
            selectedY = y;
        } else if (p != null) {
            if (!pieceMoved && p.side() == player) { // can select different
                                                     // piece if not moved
                if (p != selectedPiece) {
                    drawSquare(selectedX, selectedY); // redraw piece
                    drawPiece(selectedX, selectedY);
                    selectedPiece = p; // select new piece
                    selectedX = x;
                    selectedY = y;
                } else {
                    return;
                }
            } else {
                return;     // can't select different piece because moved first one
            }
        } else if (validSquare(selectedX, selectedY) && validSquare(x, y)) {

            Piece pI = pieceAt(selectedX, selectedY); // assume correct side was
                                                      // checked
            if (pI == null) // fix AG Test 11 gave us bad input?
                return;

            int xAbsDelta = Math.abs
                    (x - selectedX);

            if (xAbsDelta == 2) {
                if (pI.isBomb()) {
                    bombCapture = true;
                } else {
                    pieceCaptured = true;
                }
                newX = x;
                newY = y;
            } else if (!pieceCaptured) {
                newX = x;
                newY = y;
            } else
                return;
        }

        if (newX != -1) {
            selectedPiece.move(newX, newY); // calls back for GUI
            pieceMoved = true;
            if (pieceCaptured) {
                selectedPiece = pieceAt(newX, newY);
                selectedX = newX;
                selectedY = newY;
                newX = newY = 0;
            }
        } else {    // show the selection
            drawSelectedSquare(x, y);
            drawPiece(x, y);
        }
        return;
    }

    /*
     * Places p at (x, y). Places p at (x, y). If (x, y) is out of bounds or if
     * p is null, does nothing. If another piece already exists at (x, y), p
     * will replace that piece. (This method is potentially useful for creating
     * specific test circumstances.) If p already exists in the current Board,
     * first removes it from its initial position. If another piece already
     * exists at (x, y), p will replace that piece. (This method is potentially
     * useful for creating specific test circumstances.)
     * 
     * @param p Piece to place
     * 
     * @param x x board position to place
     * 
     * @param y y board position to place
     */
    public void place(Piece p, int x, int y) {

        if (p != null && validSquare(x, y)) {
            setPieceAt(p, x, y);
            drawSquare(x, y);
            drawPiece(x, y);
        }
        return;
    }

    /*
     * Executes a remove. Returns the piece that was removed. If the input (x,
     * y) is out of bounds, returns null and (does not) print an appropriate
     * message. If there is no piece at (x, y), returns null and (does not)
     * print an appropriate message.
     * 
     * @param x
     * 
     * @param y
     */
    public Piece remove(int x, int y) {
        if (validSquare(x, y)) {
            Piece removedPiece = pieceAt(x, y);
            if (removedPiece != null) {
                setPieceAt(null, x, y);
                drawSquare(x, y);
                return removedPiece;
            }
         }
         return null;
    }

    /*
     * Returns whether or not the the current player can end their turn. To be
     * able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn() {
        if (pieceMoved || pieceCaptured || bombCapture)
            return true;
        else
            return false;
    }

    /**
     * 
     */
    private void unselectPiece() {
        pieceMoved = pieceCaptured = bombCapture = false;
        if (selectedPiece != null)
            selectedPiece.doneCapturing();
        selectedPiece = null;
        selectedX = selectedY = newX = newY = -1;
        return;
    }

    /*
     * Called at the end of each turn. Handles switching of players and anything
     * else that should happen at the end of a turn.
     */
    public void endTurn() {
        if (canEndTurn()) {
            if (player == FIRE)
                player = WATER;
            else
                player = FIRE;
            unselectPiece();
        }
        return;
    }

    /*
     * Returns the winner of the game. "Fire", "Water", "No one" (tie / no
     * pieces on the board), or null if the game is not yet over.
     * 
     * @return winner of the game, "Fire", "Water", "No one" (tie / no pieces on
     * board) or null if game not yet over.
     */
    public String winner() {
        if (pieceCount[FIRE] == 0 && pieceCount[WATER] == 0)
            return "No one";
        else if (pieceCount[FIRE] == 0)
            return "Water";
        else if (pieceCount[WATER] == 0)
            return "Fire";
        else
            return null;
    }

    /**
     * validSquare returns true if x, y are a valid square on the board
     * 
     * @param x
     * @param y
     * @return true if it is a valid gray square on the board
     */
    private boolean validSquare(int x, int y) {
        if (0 <= x && x < SIZE && 0 <= y && y < SIZE && ((x + y) % 2 == 0))
            return true;
        else
            return false;
    }

    /*
     * returns the image file path for Piece p
     * 
     * @param bit 0 = water(0) / fire(1)
     * 
     * @param bit 1 = pawn(0) / bomb(1)
     * 
     * @param bit 2 = pawn(0) / shield(1)
     * 
     * @param bit 3 = normal(0) / crowned(1)
     */
    private String getImageFile(Piece p) {
        if (p == null)
            return null;
        else
            return imageFile[(p.isKing() ? 8 : 0) + (p.isShield() ? 4 : 0)
                    + (p.isBomb() ? 2 : 0) + p.side()];
    }

    /**
     * draws a selected square at x, y
     * 
     * @param x
     * @param y
     */
    private void drawSelectedSquare(int x, int y) {
        if (gui) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5);
        }
    }

    /**
     * draws an empty square at x, y
     * 
     * @param x
     * @param y
     */
    private void drawSquare(int x, int y) {
        if (gui) {
            if ((x + y) % 2 == 0)
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else
                StdDrawPlus.setPenColor(StdDrawPlus.RED);

            StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5);
        }
        return;
    }
    /**
     * draws a Piece, if any, at x, y
     * 
     * @param x
     * @param y
     */
    private void drawPiece(int x, int y) {
        if (gui) {
            Piece p = pieceAt(x, y);
            if (p != null) {
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.picture(x + 0.5, y + 0.5, getImageFile(p), 1, 1);
            }
        }
        return;
    }

    /**
     * draws the Pieces on the Board
     * 
     * @param withPieces
     *            including the pieces if true
     */
    private void drawPieces() {
        if (gui) {
            for (int i = 0; i < SIZE; i++)
                for (int j = 0; j < SIZE; j++)
                    place(pieceAt(i, j), i, j);
        }
        return;
    }

    /**
     * draws empty board
     */
    private void drawEmptyBoard() {
        if (gui) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    drawSquare(i, j); // draw empty square
                }
            }
        }
    }

    /**
     * starts a GUI supported version of the game.
     * 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * crowning a piece doesn't work with variable board size because Piece
         * doesn't know the size of the board. if (args.length == 1) { SIZE =
         * Integer.valueOf(args[0]).intValue(); } if (SIZE < 6) {
         * System.err.format("Board size %d must be at least 6.%n", SIZE);
         * System.exit(1); } StdDrawPlus.show(1); if (SIZE <= 16)
         * StdDrawPlus.setCanvasSize(SIZE * 64, SIZE * 64); else
         * StdDrawPlus.setCanvasSize(1024, 1024);
         */
        StdDrawPlus.setCanvasSize(512, 512);
        StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);

        StdDrawPlus.show(1);
        Board b = new Board(false);
        b.gui = true;
        b.drawEmptyBoard();
        b.drawPieces();
        String result = null;
        StdDrawPlus.show(1);

        while ((result = b.winner()) == null) {

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y))
                    b.select((int) x, (int) y);
            } else if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();    // it calls canEndTurn() now
            }
            StdDrawPlus.show(25);
        }
        System.out.format("Winner: %s%n", result);
        StdDrawPlus.show(5000);
        System.exit(0);
    }
}