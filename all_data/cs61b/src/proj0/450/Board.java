import java.io.File;

/**
 * Runs a game of checkers.
 * 
 * @author allenz
 */
public class Board {
    /** Whether to automatically end moves. */
    private static final boolean autoEnd = false;

    /** Board size. */
    private static final int N = 8;
    /** (x,y) from bottom left to top right */
    private Piece[][] pieces;

    /** position of current selection */
    private int selectX, selectY;

    /** game state */
    private boolean playerIsFire;
    private State state;

    private enum State {
        START, // no selection yet
        SELECTED, // piece selected, no move made
        MOVED, // piece moved
        CAPTURED, // piece captured
    }

    /**
     * If shouldBeEmpty is true, initializes an empty Board (for testing).
     * Otherwise, initializes a Board with the default configuration.
     */
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
        playerIsFire = true;
        state = State.START;
        if (!shouldBeEmpty)
            setUpBoard();
    }

    /**
     * Gets the piece at position (x, y) on the board, or null if there is no
     * piece. If (x, y) are out of bounds, returns null.
     */
    public Piece pieceAt(int x, int y) {
        return oob(x, y) ? null : pieces[x][y];
    }

    /**
     * Returns true if there is a piece at (x, y) and it can be selected
     */
    public boolean canSelect(int x, int y) {
        // can't select other player's piece
        if (pieceAt(x, y) != null && pieceAt(x, y).isFire() != playerIsFire)
            return false;
        switch (state) {
        case START: // must select a piece
            return pieceAt(x, y) != null;
        case SELECTED: // choose another piece or make a valid move
            return (pieceAt(x, y) != null && pieceAt(x, y).isFire() == playerIsFire)
                    || validMove(selectX, selectY, x, y);
        case MOVED: // can't move twice
            return false;
        case CAPTURED: // make another capture
            return validCapture(selectX, selectY, x, y);
        }
        throw new IllegalArgumentException(
                "canSelect entered an impossible state");
    }

    /**
     * @pre player controls the piece
     * @return whether the piece at (xi, yi) can either move to (xf, yf) or
     *         capture to (xf, yf).
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (oob(xf, yf) || pieces[xf][yf] != null || wrongDir(xi, yi, xf, yf))
            return false;
        if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1)
            return true;
        return validCapture(xi, yi, xf, yf);
    }

    /**
     * @pre player controls the piece
     * @return whether the piece at (xi, yi) can capture to (xf, yf).
     */
    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (pieces[xi][yi] == null) // possible after a bomb explodes
            return false;
        if (oob(xf, yf) || pieces[xf][yf] != null || wrongDir(xi, yi, xf, yf))
            return false;
        if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
            int capX = (xi + xf) >>> 1, capY = (yi + yf) >>> 1;
            return pieces[capX][capY] != null
                    && pieces[capX][capY].isFire() != playerIsFire;
        }
        return false;
    }

    /** @pre player controls the piece */
    private boolean wrongDir(int xi, int yi, int xf, int yf) {
        return !pieces[xi][yi].isKing()
                && ((playerIsFire && yf <= yi) || (!playerIsFire && yf >= yi));
    }

    /**
     * Selects the piece at (x, y) if possible. For any piece to perform a
     * capture, that piece must have been selected first.
     * 
     * @pre canSelect(x, y)
     */
    public void select(int x, int y) {
        switch (state) {
        case START:
            state = State.SELECTED;
            break;
        case SELECTED:
            if (pieces[x][y] == null) {
                state = validCapture(selectX, selectY, x, y) ? State.CAPTURED
                        : State.MOVED;
                pieces[selectX][selectY].move(x, y);
            }
            break;
        case CAPTURED:
            pieces[selectX][selectY].move(x, y);
            break;
        default: // should be unreachable assuming @pre
            throw new IllegalArgumentException(
                    "select entered an illegal state. Please check canSelect() first.");
        }
        selectX = x;
        selectY = y;
    }

    /**
     * Places p at (x, y). If (x, y) is out of bounds or if p is null, does
     * nothing. If another piece already exists at (x, y), p will replace that
     * piece.
     * 
     * @pre p.x == x && p.y == y
     */
    public void place(Piece p, int x, int y) {
        if (p != null && !oob(x, y))
            pieces[x][y] = p;
    }

    /**
     * Removes the piece at (x, y). If no such piece exists, returns null and
     * prints an appropriate message.
     * 
     * @return piece that was removed
     */
    public Piece remove(int x, int y) {
        if (oob(x, y)) {
            System.err.println("Cannot remove from (" + x + ", " + y + ")");
            return null;
        }
        Piece p = pieces[x][y];
        if (p == null) {
            System.err.println("No piece to remove at (" + x + ", " + y + ")");
            return null;
        }
        pieces[x][y] = null;
        return p;
    }

    /**
     * @return whether or not the the current player can end their turn. A piece
     *         must have moved or performed a capture to end the turn.
     */
    public boolean canEndTurn() {
        return state == State.MOVED || state == State.CAPTURED;
    }

    /** Reset state and switch players. Aside from test cases, @pre canEndTurn() */
    public void endTurn() {
        // must make null check in case we captured with a bomb
        if (state == State.CAPTURED && pieces[selectX][selectY] != null)
            pieces[selectX][selectY].doneCapturing();

        playerIsFire = !playerIsFire;
        state = State.START;
    }

    /**
     * @return winner "Fire", "Water", "No one" (tie / no pieces on the board),
     *         or null if the game is not yet over (including stalemate). The
     *         winner has the most pieces.
     */
    public String winner() {
        boolean fireHasPieces = false;
        boolean waterHasPieces = false;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                Piece p = pieces[x][y];
                if (p != null)
                    if (p.isFire())
                        fireHasPieces = true;
                    else
                        waterHasPieces = true;
            }
        }
        if (fireHasPieces && waterHasPieces)
            return null;
        if (!fireHasPieces && !waterHasPieces)
            return "No one";
        return fireHasPieces ? "Fire" : "Water";
    }

    /** @return whether (x,y) is out of bounds */
    private boolean oob(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    private void setUpBoard() {
        for (int i = 0; i < N; i += 2) {
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            pieces[i][N - 2] = new Piece(false, this, i, N - 2, "shield");
        }
        for (int i = 1; i < N; i += 2) {
            pieces[i][1] = new Piece(true, this, i, 1, "shield");
            pieces[i][N - 1] = new Piece(false, this, i, N - 1, "pawn");
            pieces[i][N - 3] = new Piece(false, this, i, N - 3, "bomb");
        }
    }

    /** Game loop for graphics and user input. */
    private static void run(Board b) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while (true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y))
                    b.select(x, y);

                // check for a win, and if so, end the game
                String winner = b.winner();
                if (winner == "Fire" || winner == "Water") {
                    System.out.println(winner + " wins.");
                    StdDrawPlus.show(5000);
                    System.exit(0);
                }

                if (autoEnd && b.canEndTurn()) {
                    boolean canMove = false;
                    for (int i = 0; i < N; i++)
                        for (int j = 0; j < N; j++)
                            if (b.canSelect(i, j))
                                canMove = true;
                    if (!canMove)
                        b.endTurn();
                }
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn())
                b.endTurn();
            if (StdDrawPlus.isNPressed())
                b = new Board(false);
            drawBoard(b);
            StdDrawPlus.show(25);
        }
    }

    private static void drawBoard(Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                // highlight selected square
                if (b.state != State.START && b.selectX == i && b.selectY == j)
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece piece = b.pieceAt(i, j);
                if (piece != null) {
                    String img = "img" + File.separatorChar;
                    img += piece.isBomb() ? "bomb"
                            : piece.isShield() ? "shield" : "pawn";
                    img += piece.isFire() ? "-fire" : "-water";
                    img += piece.isKing() ? "-crowned" : "";
                    StdDrawPlus.picture(i + .5, j + .5, img + ".png", 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Board.run(new Board(false));
    }
}