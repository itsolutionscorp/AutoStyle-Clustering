/**
 * Board.java - Creates a Checkers61b Board, with the respective methods.
 *
 * @author Sidd Karamcheti on 2/6/15.
 */
public class Board {
    /** Constructor and state fields. */
    private boolean isEmpty;
    private Piece[][] pieces = new Piece[8][8];
    private boolean isFireTurn;
    private boolean hasSelectedPiece;
    private int hasSelectedX;
    private int hasSelectedY;
    private boolean hasMoved;
    private boolean turnEnd;

    /** Board constructor, takes a single parameter as follows:
     *
     *  @param  shouldBeEmpty  Boolean value representing if the Board should be initialized
     *                         as empty (true), or with the default configuration (false).
     */
    public Board(boolean shouldBeEmpty) {
        this.isEmpty = shouldBeEmpty;
        this.isFireTurn = true;
        this.hasSelectedPiece = false;
        this.hasMoved = false;
        this.turnEnd = false;
        if (!this.isEmpty) this.populateDefaultPieces();
    }

    /** Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y)
     *  are out of bounds, returns null.
     *
     *  @param  x  Positional coordinate along x-axis.
     *  @param  y  Positional coordinate along y-axis.
     *
     *  @return    Return the piece at the specified coordinates.
     */
    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) return null;
        else                                  return pieces[x][y];
    }

    /** Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If another piece
     *  already exists at (x, y), p will replace that piece.
     *
     *  @param  p  Piece to be placed at specified coordinate location.
     *  @param  x  Positional coordinate along x-axis.
     *  @param  y  Positional coordinate along y-axis.
     */
    public void place(Piece p, int x, int y) {
        if (p != null && x < 8 && y < 8 && x > -1 && y > -1) {
            this.isEmpty = false;
            this.pieces[x][y] = p;
        }
    }

    /** Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds,
     *  returns null and prints an appropriate message. If there is no piece at (x, y), returns null
     *  and prints an appropriate message.
     *
     *  @param  x  Positional coordinate along x-axis.
     *  @param  y  Positional coordinate along y-axis.
     *
     *  @return    Return the piece that was removed from the board.
     */
    public Piece remove(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("Coordinates out of bounds!");
            return null;
        } else if (pieces[x][y] == null) {
            System.out.println("No piece at provided coordinates!");
            return null;
        } else {
            Piece p = pieces[x][y];
            pieces[x][y] = null;
            if (x == this.hasSelectedX && y == this.hasSelectedY) {
                this.hasSelectedPiece = false;
                this.hasSelectedX = -1;
                this.hasSelectedY = -1;
            }
            return p;
        }
    }

    /** Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null
     *  if the game is not yet over. Assume there is no stalemate situation in which the current player has
     *  pieces but cannot legally move any of them.
     *
     *  @return Return string of "Fire", "Water", "No one", or null depending on who wins the game.
     */
    public String winner() {
        int firePieceCount = 0;
        int waterPieceCount = 0;
        for (int i = 0; i < this.pieces.length; i++) {
            for (int j = 0; j < this.pieces[0].length; j++) {
                if (this.pieceAt(i, j) != null) {
                    if (this.pieceAt(i, j).isFire()) firePieceCount += 1;
                    else waterPieceCount += 1;
                }
                if (firePieceCount > 0 && waterPieceCount > 0) return null;
            }
        }
        if (firePieceCount == 0 && waterPieceCount == 0) return "No one";
        else if (firePieceCount == 0) return "Water";
        else return "Fire";
    }

    /** Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), strictly
     *  from a geometry/piece-race point of view. Note that multi-captures are NOT handled in this method.
     *
     *  @param  xi  Initial positional coordinate along x-axis.
     *  @param  yi  Initial positional coordinate along y-axis.
     *  @param  xf  Final positional coordinate along x-axis.
     *  @param  yf  Final positional coordinate along y-axis.
     *
     *  @return     Return boolean value representing the validity of specified movement.
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        /**
         * Check cases to immediately rule out validity:
         *       1) Check that there is a piece on current square.
         *       2) Check that final square is a valid square.
         *       3) Check that final square is within range.
         *       4) Check that final square lies on diagonal from current square.
         *       5) If only a single diagonal move, and there is a piece on final square.
         *       6) If a jump diagonal move (a capture), and there is a piece on the final square.
         *       7) If a move is against the flow of the side (i.e. Fire moves backwards) and piece is not crowned.
         *       8) If a move is a capture move, and the piece in the middle is of the same team.
         */
        if (this.pieceAt(xi, yi) == null || xf > 7 || yf > 7 || xf < 0 || yf < 0 || (xf + yf) % 2 != 0 || this.turnEnd) {
            return false; // Cases 1 - 2
        } else if (Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2) {
            return false; // Case 3
        } else if (Math.abs(xf - xi) != Math.abs(yf - yi) || (Math.abs(xf - xi) < 2 && this.pieceAt(xf, yf) != null)) {
            return false; // Cases 4 - 5
        } else if (Math.abs(xf - xi) == 2 && this.pieceAt(xf, yf) != null) {
            return false; // Case 6
        } else {
            Piece p1 = this.pieceAt(xi, yi);
            if (p1.isFire()) { // Check if a Fire Piece
                if ((yf - yi) < 0 && !p1.isKing()) {
                    return false; // Case 7
                } else if (Math.abs(yf - yi) == 2 && this.pieceAt((xi + xf) / 2, (yi + yf) / 2) != null &&
                        this.pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
                    return false; // Case 8
                } else if (Math.abs(yf - yi) == 2 && this.pieceAt((xi + xf) / 2, (yi + yf) / 2) == null) {
                    return false;
                } else {
                    return true;
                }
            } else { // Check if a Water Piece
                if ((yf - yi) > 0 && !p1.isKing()) {
                    return false; // Case 7
                } else if (Math.abs(yf - yi) == 2 && this.pieceAt((xi + xf) / 2, (yi + yf) / 2) != null &&
                        !this.pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
                    return false; // Case 8
                } else if (Math.abs(yf - yi) == 2 && this.pieceAt((xi + xf) / 2, (yi + yf) / 2) == null) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    /** Returns true if there is a piece the piece at (x, y) and it can be selected.
     *
     *  A piece may be selected if it is the corresponding player's turn and one of the following is true:
     *      1. The player has not selected a piece yet.
     *      2. The player has selected a piece, but did not move it.
     *
     *  An empty square may be selected if one of the following is true:
     *      1. During this turn, the player has selected a Piece which hasn't moved yet and is selecting an empty spot
     *         which is a valid move for the previously selected Piece.
     *      2. During this turn, the player has selected a Piece, captured, and has selected another valid capture
     *         destination. When performing multi-captures, you should only select the active piece once; all other
     *         selections should be valid destination points.
     *
     *  @param  x  Positional coordinate along x-axis.
     *  @param  y  Positional coordinate along y-axis.
     *
     *  @return    Return boolean value returning whether piece at (x, y) can be selected.
     */
    public boolean canSelect(int x, int y) {
        if (this.pieceAt(x, y) != null && this.pieceAt(x, y).isFire() != this.isFireTurn) {
            return false; // Check for valid player.
        } else if (!this.hasSelectedPiece && this.pieceAt(x, y) == null) {
            return false; // Check for valid board location.
        } else if (this.hasMoved && this.pieceAt(x, y) != null) {
            return false; // Check if piece selected after player has moved.
        } else if (this.hasMoved && (Math.abs(this.hasSelectedX - x) < 2 || Math.abs(this.hasSelectedY - y) < 2)) {
            return false; // Check if piece selected is moving regularly after capture (not multi-capture)
        } else {
            if (this.hasSelectedPiece && this.validMove(this.hasSelectedX, this.hasSelectedY, x, y)) {
                return true; // Check if valid move for selected piece (multi-capture).
            } else if (this.hasSelectedPiece && !this.hasMoved && this.pieceAt(x, y) != null) {
                return true; // Check if selecting another valid piece.
            } else if (!this.hasSelectedPiece && this.pieceAt(x, y) != null) {
                return true; // Check if selecting a valid piece.
            } else {
                return false; // Return false otherwise.
            }
        }
    }

    /** Selects the piece at (x, y) if possible. Optionally, it is recommended to color the background of the selected
     *  square white on the GUI via the pen color function. For any piece to perform a capture, that piece must have
     *  been selected first.
     *
     *  @param  x  Positional coordinate along x-axis.
     *  @param  y  Positional coordinate along y-axis.
     */
    public void select(int x, int y) {
        if (this.pieceAt(x, y) == null) {
            if (Math.abs(this.hasSelectedX - x) < 2) {
                this.pieceAt(this.hasSelectedX, this.hasSelectedY).move(x, y);
                this.hasMoved = true;
                this.turnEnd = true;
            } else {
                this.pieceAt(this.hasSelectedX, this.hasSelectedY).move(x, y);
                this.hasMoved = true;
            }
        }
        if (this.pieceAt(x, y) != null) {
            this.hasSelectedX = x;
            this.hasSelectedY = y;
            this.hasSelectedPiece = true;
        } else {
            this.hasMoved = true;
        }
    }

    /** Returns whether or not the the current player can end their turn. To be able to end a turn, a piece must have
     *  moved or performed a capture.
     *
     *  @return Return boolean value representing if current player can end their turn.
     */
    public boolean canEndTurn() {
        return this.hasMoved;
    }

    /** Called at the end of each turn. Handles switching of players and anything else that should happen at the end of
     *  a turn.
     */
    public void endTurn() {
        if (this.canEndTurn()) {
            if (this.pieceAt(this.hasSelectedX, this.hasSelectedY) != null) {
                this.pieceAt(this.hasSelectedX, this.hasSelectedY).doneCapturing();
            }
            this.hasMoved = false;
            this.turnEnd = false;
            this.hasSelectedPiece = false;
            this.hasSelectedX = -1;
            this.hasSelectedY = -1;
            this.isFireTurn = !this.isFireTurn;
        }
    }

    /** Draws an empty N x N board. Adapted from:
     *  http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawEmptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    /** Draws the default N x N board. Uses the pieces array to draw the individual piece images.
     *  Adapted from: http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawDefaultBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (this.hasSelectedPiece) {
                    StdDrawPlus.square(this.hasSelectedX + .5, this.hasSelectedY + .5, .5);
                }

                if (this.pieces[i][j] != null) {
                    Piece p = this.pieces[i][j];
                    if (p.isFire()) {
                        if (!p.isKing()) {
                            if (p.isBomb())        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            else if (p.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            else                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        } else {
                            if (p.isBomb())        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            else if (p.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            else                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                    } else {
                        if (!p.isKing()) {
                            if (p.isBomb())        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            else if (p.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            else                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        } else {
                            if (p.isBomb())        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            else if (p.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            else                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    /** Populate the pieces array with the default configuration. */
    private void populateDefaultPieces() {
        for (int i = 0; i < this.pieces.length; i++) {
            for (int j = 0; j < this.pieces[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    if (j == 0)      pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    else if (j == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
                    else if (j == 2) pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    else if (j == 5) pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    else if (j == 6) pieces[i][j] = new Piece(false, this, i, j, "shield");
                    else if (j == 7) pieces[i][j] = new Piece(false, this, i, j, "pawn");
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
            if (b.isEmpty) b.drawEmptyBoard(N);
            else           b.drawDefaultBoard(N);

            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) b.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();
            }
            if (b.winner() != null) {
                System.out.println(b.winner());
                if (b.isEmpty) b.drawEmptyBoard(N);
                else b.drawDefaultBoard(N);
                StdDrawPlus.show(100);
                break;
            }
            StdDrawPlus.show(100);
        }
    }
}
