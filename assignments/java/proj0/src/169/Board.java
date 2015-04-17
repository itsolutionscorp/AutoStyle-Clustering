/**
 * The Board class for checkers. The game runs in this class and 
 * keeps track of all game state information, 
 * including the number of pieces, the winner and whose turn it is. 
 * All GUI operations are in this class, as well.
 */

/**
 * @author Shafqat Dulal
 *
 */
public class Board {

    /**
     * Piece-related information for the Board to keep track of.
     */
    private Piece[][] pieces = new Piece[8][8];
    private int numFire = 0;
    private int numWater = 0;

    /**
     * Player-related information for the Board to keep track of.
     */
    private int turn = 0; // 0 for Fire, 1 for Water.
    private Piece selectedPiece = null;
    private int sPX;
    private int sPY;
    private boolean hasMoved;

    /**
     * Starts a GUI supported version of the game.
     * 
     * @param args
     *            command line arguments (there should be none)
     */
    public static void main(String args[]) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        Board checkers = new Board(false);

        while (true) {
            checkers.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (checkers.canSelect((int) x, (int) y))
                    checkers.select((int) x, (int) y);
            } else if (StdDrawPlus.isSpacePressed()) {
                if (checkers.canEndTurn())
                    checkers.endTurn();
            }
            StdDrawPlus.show(100);
        }
    }

    /**
     * The constructor for Board. If shouldBeEmpty is true, initializes an empty
     * Board. Otherwise, initializes a Board with the default configuration.
     * 
     * @param shouldBeEmpty
     *            indicates whether the board should have pieces or not
     */
    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            initBoardPieces(this);
        }
    }

    /**
     * This method provides the operation for drawing a standard-size checkers
     * board. It should be used only for the GUI, during the game.
     */
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j) == selectedPiece) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                    StdDrawPlus.picture(i + .5, j + .5,
                            getFileName(pieceAt(i, j)), 1, 1);
                }
            }
        }
    }

    /**
     * Initializes a full set of checkers pieces on the specified board. This
     * method fills the pieces array with Piece object.
     * 
     * @param board
     *            the board to assign the pieces to
     */
    private void initBoardPieces(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    if (j == 0 || j == 7) {
                        if (j == 0)
                            pieces[i][j] = new Piece(true, board, i, j, "pawn");
                        else
                            pieces[i][j] = new Piece(false, board, i, j, "pawn");
                    } else if (j == 1 || j == 6) {
                        if (j == 1)
                            pieces[i][j] = new Piece(true, board, i, j,
                                    "shield");
                        else
                            pieces[i][j] = new Piece(false, board, i, j,
                                    "shield");
                    } else if (j == 2 || j == 5) {
                        if (j == 2)
                            pieces[i][j] = new Piece(true, board, i, j, "bomb");
                        else
                            pieces[i][j] = new Piece(false, board, i, j, "bomb");
                    }
                }
            }
        }
        numFire = 12;
        numWater = 12;
    }

    /**
     * Gets the piece at position (x, y) on the board, or null if there is no
     * piece. If (x, y) are out of bounds, returns null.
     * 
     * @param x
     *            the x-position of the square on the board
     * @param y
     *            the y-position of the square on the board
     * @return the piece associated at position (x, y)
     */
    public Piece pieceAt(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0 || pieces[x][y] == null)
            return null;
        else
            return pieces[x][y];
    }

    /**
     * Returns true if the square at (x, y) can be selected.
     * 
     * A piece may be selected if it is the corresponding player’s turn and one
     * of the following is true:
     * 
     * The player has not selected a piece yet. The player has selected a piece,
     * but did not move it.
     * 
     * An empty square may be selected if one of the following is true:
     * 
     * During this turn, the player has selected a Piece which hasn’t moved yet
     * and is selecting an empty spot which is a valid move for the previously
     * selected Piece. During this turn, the player has selected a Piece,
     * captured, and has selected another valid capture destination. During
     * multi-captures, the active piece should be selected only once; all other
     * selections should be valid destination points.
     * 
     * @param x
     *            the x-position of the selected square
     * @param y
     *            the y-position of the selected square
     * @return true or false, depending on the validity of the selection
     */
    public boolean canSelect(int x, int y) {
        if (x > 7 || y > 7)
            return false;

        if (pieceAt(x, y) != null) {
            // Piece selected.
            Piece selection = pieceAt(x, y);
            if (selection.side() == turn) {
                if (selectedPiece == null || selection == selectedPiece) {
                    if (hasMoved) {
                        return false;
                    } else
                        return true;
                } else {
                    if (hasMoved) {
                        return false;
                    } else
                        return true;
                }
            } else {
                return false;
            }
        } else {
            // Empty Square selected.
            if (selectedPiece == null) {
                return false;
            } else {
                // Assumption: the person already selected a piece that's his.
                if (hasMoved) {
                    // Check if player captured a piece.
                    if (selectedPiece.hasCaptured()) {
                        return validMove(x, y, true);
                    } else {
                        return false;
                    }
                } else {
                    int[] coordinates = getCoordinates(selectedPiece);
                    if (Math.abs(coordinates[0] - x) == 2
                            && Math.abs(coordinates[1] - y) == 2)
                        return validMove(x, y, true);
                    else
                        return validMove(x, y, false);
                }
            }
        }
    }

    /**
     * Returns true if the piece can either move to (x, y) or capture to (x, y)
     * strictly from a geometry/piece-race point of view.
     * 
     * @param x
     *            the x-position of the destination
     * @param y
     *            the y-position of the destination
     * @param isCapturing
     *            indicates if the piece is trying to capture another piece
     * @return true or false, depending on the validity of the move
     */
    private boolean validMove(int x, int y, boolean isCapturing) {
        int level;
        if (isCapturing)
            level = 2;
        else
            level = 1;

        if (selectedPiece.isKing()) {
            if (pieceAt(x - level, y - level) == selectedPiece
                    || pieceAt(x + level, y - level) == selectedPiece
                    || pieceAt(x - level, y + level) == selectedPiece
                    || pieceAt(x + level, y + level) == selectedPiece) {
                if (level == 2)
                    return isValidCapture(sPX, sPY, x, y, selectedPiece.side());
                else
                    return true;
            } else {
                return false;
            }
        } else if (selectedPiece.isFire()) {
            if (pieceAt(x - level, y - level) == selectedPiece
                    || pieceAt(x + level, y - level) == selectedPiece) {
                if (level == 2)
                    return isValidCapture(sPX, sPY, x, y, selectedPiece.side());
                else
                    return true;
            } else {
                return false;
            }
        } else {
            if (pieceAt(x - level, y + level) == selectedPiece
                    || pieceAt(x + level, y + level) == selectedPiece) {
                if (level == 2)
                    return isValidCapture(sPX, sPY, x, y, selectedPiece.side());
                else
                    return true;
            } else {
                return false;
            }
        }
    }

    /**
     * This method checks if a given capture move is valid. Contingent upon the
     * player actually trying to make said move.
     * 
     * @param oldX
     *            the old x-position of the player's piece
     * @param oldY
     *            the old y-position of the player's piece
     * @param x
     *            the x-position that the player wants to move the piece to
     * @param y
     *            the y-position of the player wants to move the piece to
     * @param side
     *            the side of the player (0 if fire, 1 if water)
     * @return true or false depending on the validity of the capture move
     */
    private boolean isValidCapture(int oldX, int oldY, int x, int y, int side) {
        if ((x - oldX > 0) && (y - oldY > 0)) {
            if (pieceAt(x - 1, y - 1) != null
                    && pieceAt(x - 1, y - 1).side() != side)
                return true;
        } else if ((x - oldX < 0) && (y - oldY < 0)) {
            if (pieceAt(x + 1, y + 1) != null
                    && pieceAt(x + 1, y + 1).side() != side)
                return true;
        } else if ((x - oldX > 0) && (y - oldY < 0)) {
            if (pieceAt(x - 1, y + 1) != null
                    && pieceAt(x - 1, y + 1).side() != side)
                return true;
        } else {
            if (pieceAt(x + 1, y - 1) != null
                    && pieceAt(x + 1, y - 1).side() != side)
                return true;
        }
        return false;
    }

    /**
     * Selects the square at (x, y). This method assumes canSelect (x,y) returns
     * true. For any piece to perform a capture, that piece must have been
     * selected first. Selecting a square with a piece prepares that piece for
     * movement. Selecting an empty square (assuming canSelect returns true),
     * moves the most recently selected piece to that square.
     * 
     * @param x
     *            the x-position of the selected square
     * @param y
     *            the y-position of the selected square
     */
    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            selectedPiece = pieceAt(x, y);
            int[] coordinates = getCoordinates(selectedPiece);
            sPX = coordinates[0];
            sPY = coordinates[1];
        } else {
            // canSelect checked if its a valid move already.
            if (selectedPiece != null) {
                hasMoved = true;
                selectedPiece.move(x, y);
            }
        }
    }

    /**
     * Places p at (x, y). If (x, y) is out of bounds or if p is null, does
     * nothing. If p already exists in the current Board, first removes it from
     * its initial position. If another piece already exists at (x, y), p will
     * replace that piece.
     * 
     * @param p
     *            the piece to place at (x, y)
     * @param x
     *            the x-position of the destination square
     * @param y
     *            the y-position of the destination square
     */
    public void place(Piece p, int x, int y) {
        if (x <= 7 && y <= 7 && p != null) {
            if (pieceAt(x, y) != null) {
                remove(x, y);
            }

            if (p == selectedPiece) {
                remove(sPX, sPY);
                pieces[x][y] = p;
                if (p.hasCaptured()) {
                    if (p.isBomb()) {
                        doBombCapture(sPX, sPY, x, y, p.side());
                    } else {
                        doRegularCapture(sPX, sPY, x, y, p.side());
                    }
                }
                sPX = x;
                sPY = y;
            } else if (getCoordinates(p).length == 0) {
                pieces[x][y] = p;
            } else if (getCoordinates(p).length != 0) {
                int[] coordinates = getCoordinates(p);
                remove(coordinates[0], coordinates[1]);
                pieces[x][y] = p;
            }

            if (p.isFire())
                numFire++;
            else
                numWater++;
        }
    }

    /**
     * Executes the bomb explosion that results when the bomb captures a piece.
     * Removes all non-shield pieces within a 3x3 block centered at the bomb's
     * final position.
     * 
     * @param oldX
     *            the old x-position of the player's piece
     * @param oldY
     *            the old y-position of the player's piece
     * @param x
     *            the x-position that the player wants to move the piece to
     * @param y
     *            the y-position of the player wants to move the piece to
     * @param side
     *            the side of the player (0 if fire, 1 if water)
     */
    private void doBombCapture(int oldX, int oldY, int x, int y, int side) {
        doRegularCapture(oldX, oldY, x, y, side);

        Piece[] piecesToRemove = { pieceAt(x - 1, y - 1),
                pieceAt(x + 1, y - 1), pieceAt(x - 1, y + 1),
                pieceAt(x + 1, y + 1) };
        for (Piece p : piecesToRemove) {
            if (p != null && !p.isShield()) {
                int[] coordinates = getCoordinates(p);
                remove(coordinates[0], coordinates[1]);
            }
        }
        remove(x, y);
    }

    private void doRegularCapture(int oldX, int oldY, int x, int y, int side) {
        if ((x - oldX > 0) && (y - oldY > 0)) {
            if (pieceAt(x - 1, y - 1) != null
                    && pieceAt(x - 1, y - 1).side() != side)
                remove(x - 1, y - 1);
        } else if ((x - oldX < 0) && (y - oldY < 0)) {
            if (pieceAt(x + 1, y + 1) != null
                    && pieceAt(x + 1, y + 1).side() != side)
                remove(x + 1, y + 1);
        } else if ((x - oldX > 0) && (y - oldY < 0)) {
            if (pieceAt(x - 1, y + 1) != null
                    && pieceAt(x - 1, y + 1).side() != side)
                remove(x - 1, y + 1);
        } else {
            if (pieceAt(x + 1, y - 1) != null
                    && pieceAt(x + 1, y - 1).side() != side)
                remove(x + 1, y - 1);
        }
        remove(oldX, oldY);
    }

    /**
     * Gets the coordinates of a Piece p. If p does not exist on the board, no
     * coordinates are returned.
     * 
     * @param p
     *            the Piece object
     * @return an integer array that contains the coordinates of the Piece p
     *         (array length is 0 if p is not on the board)
     */
    private int[] getCoordinates(Piece p) {
        boolean found = false;
        int[] coordinates = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (p == pieceAt(i, j)) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    found = true;
                    break;
                }
            }
        }
        if (found)
            return coordinates;
        else
            return new int[0];
    }

    /**
     * Returns the appropriate file name for a given piece p. This takes into
     * account p's side, type, and whether or not it has been crowned.
     * 
     * @param p
     * @return a String describing the appropriate local file path for the
     *         piece.
     */
    private String getFileName(Piece p) {
        String side;
        if (p.side() == 0)
            side = "-fire";
        else
            side = "-water";

        String type;
        if (p.isShield())
            type = "shield";
        else if (p.isBomb())
            type = "bomb";
        else
            type = "pawn";

        String crowned;
        if (p.isKing())
            crowned = "-crowned";
        else
            crowned = "";

        String fileName = "img/" + type + side + crowned + ".png";
        return fileName;
    }

    /**
     * Executes a remove. Returns the piece that was removed. If the input (x,
     * y) is out of bounds, returns null and prints an appropriate message. If
     * there is no piece at (x, y), returns null and prints an appropriate
     * message.
     * 
     * @param x
     *            the x-position of the square that will have a piece removed
     * @param y
     *            the y-position of the square that will have a piece removed
     * @return the removed piece (null if no piece exists at the position)
     */
    public Piece remove(int x, int y) {
        if (pieceAt(x, y) == null) {
            return null;
        } else {
            Piece removed = pieceAt(x, y);
            pieces[x][y] = null;

            if (removed.isFire())
                numFire--;
            else
                numWater--;
            return removed;
        }
    }

    /**
     * Returns whether or not the the current player can end their turn. To be
     * able to end a turn, a piece must have moved or performed a capture.
     * 
     * @return true or false depending on whether or not the player can end
     *         their turn.
     */
    public boolean canEndTurn() {
        if (selectedPiece == null || hasMoved == false) {
            ;
            return false;
        } else {
            return true;
        }
    }

    /**
     * Called at the end of each turn. Handles switching of players and anything
     * else that should happen at the end of a turn.
     */
    public void endTurn() {
        turn = Math.abs(turn - 1);
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
            selectedPiece = null;
        }
        sPX = -1;
        sPY = -1;
        hasMoved = false;
    }

    /**
     * Returns the winner of the game. "Fire", "Water", "No one" (tie / no
     * pieces on the board), or null if the game is not yet over. Assume there
     * is no stalemate situation in which the current player has pieces but
     * cannot legally move any of them.
     * 
     * @return the winner of the game
     */
    public String winner() {
        if ((numFire == 0 && numWater == 0))
            return "No one";
        else if (numFire == 0)
            return "Water";
        else if (numWater == 0)
            return "Fire";
        else
            return null;
    }
}