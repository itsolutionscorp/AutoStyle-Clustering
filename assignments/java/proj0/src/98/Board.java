/**
 * Game board simulation for Bomb Checkers.
 * Finished. GRADE
 */

/**
 * @author Jerry Chen
 *
 */
public class Board {

    private static final int SIZE = 8; // Size of the board.
    private Piece[][] pieces; // Stores the piece at each location
    private int totalPieces;
    private int firePieces;
    private int waterPieces;
    private boolean firePlayer; // Determines current player. True if fire,
                                // false if water is the current player.
    private Piece curSelection; // The selected piece for this turn.
    private int xSelection; // x coordinate for the selected piece.
    private int ySelection; // y coordinate for the selected piece.
    private boolean hasMoved; // True if a piece has been moved this turn.
    private boolean highlightSquare; // True if highlighting should be on.
    private int xHLite;
    private int yHLite;

    /**
     * Constructor for the game board. Makes an empty board if shouldBeEmpty is
     * true.
     */
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[SIZE][SIZE];
        firePlayer = true;
        waterPieces = 0;
        firePieces = 0;
        if (shouldBeEmpty == false) {
            generateBoardPiece();
        }
        totalPieces = waterPieces + firePieces;
    }

    /**
     * Generates piece setup for the starting configuration.
     */
    private void generateBoardPiece() {
        /*
         * Generate Fire side.
         */
        // Generate Pawns.
        for (int i = 0; i < SIZE; i += 2) {
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            firePieces += 1;
        }
        // Generate Shields.
        for (int i = 1; i < SIZE; i += 2) {
            pieces[i][1] = new Piece(true, this, i, 1, "shield");
            firePieces += 1;
        }
        // Generate Bombs.
        for (int i = 0; i < SIZE; i += 2) {
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            firePieces += 1;
        }
        /*
         * Generate Water side.
         */
        // Generate Pawns.
        for (int i = SIZE - 1; i >= 0; i -= 2) {
            pieces[i][SIZE - 1] = new Piece(false, this, i, SIZE - 1, "pawn");
            waterPieces += 1;
        }
        // Generate Shields.
        for (int i = SIZE - 2; i >= 0; i -= 2) {
            pieces[i][SIZE - 2] = new Piece(false, this, i, SIZE - 2, "shield");
            waterPieces += 1;
        }
        // Generate Bombs.
        for (int i = SIZE - 1; i >= 0; i -= 2) {
            pieces[i][SIZE - 3] = new Piece(false, this, i, SIZE - 3, "bomb");
            waterPieces += 1;
        }
    }

    /**
     * Draws a board of NXN size. Also draws the pieces on each square.
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    // Draw the appropriate piece.
                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/"
                            + imgLink(pieces[i][j]), 1, 1);
                }
                if (highlightSquare == true) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(xHLite + .5, yHLite + .5, .5);
                    if (pieces[xHLite][yHLite] != null) {
                        // Draw the appropriate piece.
                        StdDrawPlus.picture(xHLite + 0.5, yHLite + 0.5, "img/"
                                + imgLink(pieces[xHLite][yHLite]), 1, 1);
                    }
                }
            }
        }
    }

    /**
     * Generates the appropriate link for a given piece.
     * 
     * @param P
     *            Piece to link.
     * @return Image file name for Piece P.
     */
    private String imgLink(Piece P) {
        String pieceType;
        // Determine type of piece.
        if (P.isBomb() == true) {
            pieceType = "bomb";
        } else if (P.isShield() == true) {
            pieceType = "shield";
        } else {
            pieceType = "pawn";
        }
        // Determine the side of the piece.
        if (P.isFire() == true) {
            pieceType = pieceType.concat("-fire");
        } else {
            pieceType = pieceType.concat("-water");
        }
        // Determine if the piece is crowned
        if (P.isKing()) {
            pieceType = pieceType.concat("-crowned");
        }
        // Finally png link
        return pieceType.concat(".png");
    }

    /**
     * Returns the piece at the given location
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return Piece if there exists one, null if out of bounds or no piece
     */
    public Piece pieceAt(int x, int y) {
        if (notOnBoard(x, y) == true) {
            return null;
        } else if (pieces[x][y] != null) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    /**
     * Checks if a position is on the board, else return false.
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return True if invalid, false if valid.
     */
    private boolean notOnBoard(int x, int y) {
        if (x >= SIZE || y >= SIZE) {
            return true;
        }
        else if (x < 0 || y < 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * <pre>
     * Determines if a square/piece is valid for selection. A piece can be
     * selected if one of the following is true:
     * 1. The player has not selected a piece.
     * 2. The player has selected a piece but has not moved it.
     * 
     * A square is valid for selection if one of the following is true: 
     * 1. A piece that hasn't moved yet is selected and the 
     *    square selected is a valid move point.
     * 2. A piece has been moved, has captured, 
     *    and another valid capture destination is selected.
     * </pre>
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return true if the coord can be selected, false if it cannot be
     *         selected.
     */
    public boolean canSelect(int x, int y) {
        Piece P = pieceAt(x, y);
        if (P == null) { // Checking square.
            if (hasMoved == true) { // Must be a valid capture.
                if (curSelection.hasCaptured() == false) { // Moved but hasn't
                                                           // captured.
                    return false;
                }
                if (curSelection.isBomb() == true) { // Bomb has captured, no move possible.
                    return false;
                }
                return validCapture(xSelection, ySelection, x, y);
            }
            else { // Must be a valid move or capture.
                return (validCapture(xSelection, ySelection, x, y) || validMove(
                        xSelection, ySelection, x, y));
            }
        }
        else { // Check piece
            if (hasMoved == true) {
                return false;
            }
            if (P.isFire() == firePlayer) { // Can never select opponents
                                            // pieces.
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Checks if a given move is valid.
     * 
     * @param xi
     *            Initial x position.
     * @param yi
     *            Initial y position.
     * @param xf
     *            Final x position.
     * @param yf
     *            Final y position.
     * @return True if the move is valid, false if the move is invalid.
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (notOnBoard(xf, yf) == true) {
            return false;
        }
        if (validDirection(xi, yi, xf, yf) == false) {
            return false;
        }
        // Can't move onto another piece.
        if (pieceAt(xf, yf) != null) {
            return false;
        }
        // Check if spacing is correct.
        if (Math.abs(xf - xi) != 1 || Math.abs(yf - yi) != 1) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Checks if a given capture is valid.
     * 
     * @param xi
     *            Initial x position.
     * @param yi
     *            Initial y position.
     * @param xf
     *            Final x position.
     * @param yf
     *            Final y position.
     * @return
     */
    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (notOnBoard(xf, yf) == true) {
            return false;
        }
        if (validDirection(xi, yi, xf, yf) == false) {
            return false;
        }
        // Can't move onto another piece.
        if (pieceAt(xf, yf) != null) {
            return false;
        }
        // Check if spacing is correct.
        if (Math.abs(xf - xi) != 2 || Math.abs(yf - yi) != 2) {
            return false;
        }
        int xCap = (xf + xi) / 2;
        int yCap = (yf + yi) / 2;
        Piece captured = pieceAt(xCap, yCap);
        // Nothing being captured.
        if (captured == null) {
            return false;
        }
        // Checks friendlies not being captured.
        if (captured.isFire() == curSelection.isFire()) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Checks if the current piece is moving in a valid direction.
     * 
     * @param xi
     * @param yi
     * @param xf
     * @param yf
     * @return
     */
    private boolean validDirection(int xi, int yi, int xf, int yf) {
        // Check if anything is selected.
        if (curSelection == null) {
            return false;
        }
        // Check if direction is correct.
        if (curSelection.isKing() == false) { // Kings can move in either
                                              // direction.
            if (curSelection.isFire() == true) {
                return (yf - yi > 0);
            }
            else {
                return (yf - yi < 0);
            }
        }
        else {
            return true; // King is always fine.
        }
    }

    /**
     * Select the piece on the given square. Alternatively, select the given
     * square if a piece is already queued up and move.
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    public void select(int x, int y) {
        highlightSquare(x, y);
        Piece select = pieceAt(x, y);
        // Make a move.
        if (select == null) {
            place(curSelection, x, y);
            remove(xSelection, ySelection);
            curSelection.move(x, y);
            hasMoved = true;
            xSelection = x;
            ySelection = y;
        }
        // Make new selection
        else {
            curSelection = select;
            xSelection = x;
            ySelection = y;
        }

    }

    /**
     * Highlights a given square. Redraw the underlying piece on top if
     * necessary. Does nothing if the square is not valid.
     * 
     * @param x
     *            x coordinate.
     * @param y
     *            y coordinate.
     */
    private void highlightSquare(int x, int y) {
        if (notOnBoard(x, y) == false) {
            highlightSquare = true;
            xHLite = x;
            yHLite = y;
        }
    }

    /**
     * Places piece p at position (x, y) if possible. Replaces piece at (x, y)
     * if any.
     * 
     * @param p
     * @param x
     * @param y
     */
    public void place(Piece p, int x, int y) {
        if (notOnBoard(x, y) == true) {
            return;
        }
        remove(x, y);
        pieces[x][y] = p;
        if (p.isFire() == true) {
            firePieces += 1;
        } else {
            waterPieces += 1;
        }
        totalPieces += 1;
    }

    /**
     * Remove a piece at the given location, if valid.
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return The removed piece, or null if invalid.
     */
    public Piece remove(int x, int y) {
        if (notOnBoard(x, y) == true) { // Can't remove off of board.
            return null;
        }
        Piece removed = pieces[x][y];
        if (removed == null) { // Nothing removed
            return null;
        } else {
            pieces[x][y] = null;
            if (removed.isFire() == true) {
                firePieces -= 1;
            } else {
                waterPieces -= 1;
            }
            totalPieces -= 1;
            return removed;
        }
    }

    /**
     * Checks if it is legal to end the turn.
     * 
     * @return True if ending turn is legal, false if illegal to end turn.
     */
    public boolean canEndTurn() {
        if (hasMoved == true) {
            return true;
        }
        return false;
    }

    /**
     * Takes care of ending the turn.
     */
    public void endTurn() {
        // Switches players.
        if (firePlayer == true) {
            firePlayer = false;
        } else {
            firePlayer = true;
        }
        hasMoved = false;
        highlightSquare = false;
        if (curSelection != null) { // Valid if not a bomb.
            curSelection.doneCapturing();
        }
        curSelection = null;
    }

    /**
     * Checks for a winner.
     * 
     * @return "Fire" if fire has won, "Water" if water has won, "No one" for a
     *         tie, or null if the game isn't over.
     */
    public String winner() {
        if (totalPieces == 0) {
            return "No one";
        } else if (firePieces == 0) {
            return "Water";
        } else if (waterPieces == 0) {
            return "Fire";
        }
        return null;
    }

    /**
     * Handles loading up the board game gui.
     * 
     * @param args
     *            command line args
     */
    public static void main(String[] args) {
        int N = 8;
        int mouseX; // x position of mouse click
        int mouseY; // y position of mouse click
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board B = new Board(false);
        while (B.winner() == null) { // Run while the game has not ended.
            B.drawBoard(N);
            // Handle selection
            if (StdDrawPlus.mousePressed() == true) {
                mouseX = (int) StdDrawPlus.mouseX();
                mouseY = (int) StdDrawPlus.mouseY();
                if (B.canSelect(mouseX, mouseY) == true) {
                    B.select(mouseX, mouseY);
                }
            }
            // Handle turn ending
            if (StdDrawPlus.isSpacePressed() == true) {
                if (B.canEndTurn() == true) {
                    B.endTurn();
                }
            }
            StdDrawPlus.show(10);
        }
    }
}
