/**
  * @author Yaqi Miao
  */

public class Board {
    private Piece[][] board;
    private int firePiece;
    private int waterPiece;
    private boolean fireIsPlaying;
    private boolean hasMoved;
    private boolean hasChosen;
    private int chosenX;
    private int chosenY;
    private int N=8;

    /** The constructor for Board.
      * If shouldBeEmpty is true, initializes an empty Board.
      * Otherwise, initializes a Board with the default configuration.*/
    public Board(boolean shouldBeEmpty) {
        board = new Piece[8][8];
        if (! shouldBeEmpty) {
            /** init with default configuration*/
            for (int i = 0; i < 8; i+=2) {
                board[i][0] = new Piece(true, this, i, 0, "pawn");
                board[i][2] = new Piece(true, this, i, 2, "bomb");
                board[i][6] = new Piece(false, this, i, 6, "shield");
            }

            for (int i = 1; i < 8; i+=2) {
                board[i][1] = new Piece(true, this, i, 1, "shield");
                board[i][5] = new Piece(false, this, i, 5, "bomb");
                board[i][7] = new Piece(false, this, i, 7, "pawn");
            }
        }
        fireIsPlaying = true;
        hasChosen = false;
        hasMoved = false;
        if (!shouldBeEmpty) {
            firePiece = 12;
            waterPiece = 12;
        } else {
            firePiece = 0;
            waterPiece = 0;
        }
    }

    /** Gets the piece at position (x, y) on the board, or null if there is no piece.
      * If (x, y) are out of bounds, returns null.*/
    public Piece pieceAt(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        return board[x][y];
    }

    /** Returns true if the square at (x, y) can be selected.
      * Four cases that can select:
      * has not chosen, choose a piece of mine
      * has chosen, not yet moved, choose another piece
      * has chosen, not yet moved, move
      * has chosen, has moved, also has captured, not a bomb, do another capture*/
    public boolean canSelect(int x, int y) {
        if (inBounds(x, y)) {
            if (pieceAt(x, y) != null) {
                if (pieceAt(x, y).isFire() == fireIsPlaying) {
                    if (!hasChosen) {
                        return true;
                    } else if (!hasMoved) {
                        return true;
                    }
                }
            } else if (hasChosen) {
                if (!hasMoved) {
                    return validMove(chosenX, chosenY, x, y);
                } else {
                    Piece cp = pieceAt(chosenX, chosenY);
                    if ((cp != null) && cp.hasCaptured()){
                        return validMove(chosenX, chosenY, x, y);
                    }
                }
            }
        }
        return false;
    }

    private int abs(int x) {
        if (x < 0) {
            return -x;
        }
        return x;
    }

    /** Optional: help canSelect.
       * has chosen, not yet moved, move
       * has chosen, has moved, also has captured, not a bomb, do another capture.*/
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece cp = pieceAt(xi, yi);
        if (cp.isKing()) {
            //kings move in a ceratin way
            if ((abs(yi - yf) == 1) && (abs(xi - xf) == 1)) {
                return !hasMoved;
            }
            if ((abs(yi - yf) == 2) && (abs(xi - xf) == 2)) {
                Piece p = pieceAt((xi + xf) / 2, (yi + yf) / 2);
                return ((p != null) && (p.isFire() != fireIsPlaying));
            }
        }
        if (cp.isFire()) {
            if ((yf == yi + 1) && (abs(xi - xf) == 1)) {
                return !hasMoved;
            }
            if ((yf == yi + 2) && (abs(yi - yf) == 2)) {
                Piece p = pieceAt((xi + xf) / 2, (yi + yf) / 2);
                return ((p != null) && (p.isFire() != fireIsPlaying));
            }
        } else {
            if ((yf == yi - 1) && (abs(xi - xf) == 1)) {
                return !hasMoved;
            }
            if ((yf == yi - 2) && (abs(yi - yf) == 2)) {
                Piece p = pieceAt((xi + xf) / 2, (yi + yf) / 2);
                return ((p != null) && (p.isFire() != fireIsPlaying));

            }
        }
        return false;
    }

    /** Selects the square at (x, y). This method assumes canSelect (x,y) returns true.
      * Optionally, it is recommended to color the background of the selected square white on the GUI via the pen color function.
      * For any piece to perform a capture, that piece must have been selected first.
      * If you select a square with a piece, you are prepping that piece for movement.
      * If you select an empty square (assuming canSelect returns true), you should move your most recently selected piece to that square.*/
    public void select(int x, int y) {
        if (inBounds(x, y)) {
            if (pieceAt(x, y) != null) {
                if (pieceAt(x, y).isFire() == fireIsPlaying) {
                    if (!hasChosen) {
                        choose(x, y);
                    } else if (!hasMoved) {
                        updateSquare(chosenX, chosenY, false);
                        choose(x, y);
                    }
                }
            } else {
                Piece cp = pieceAt(chosenX, chosenY);
                board[x][y] = cp;
                cp.move(x, y);
                choose(x, y);
                hasMoved = true;
            }
        }
    }

    private void choose(int x, int y) {
        hasChosen = true;
        chosenX = x;
        chosenY = y;
        updateSquare(chosenX, chosenY, true);
    }

    private boolean inBounds(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }
        return true;
    }

    private void cleanUp(Piece p) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (board[i][j] == p) {
                    board[i][j] = null;
                }
             }
        }
    }

    /** Places p at (x, y).
      * If (x, y) is out of bounds or if p is null, does nothing.
      * If p already exists in the current Board, first removes it from its initial position.
      * If another piece already exists at (x, y), p will replace that piece.*/
    public void place(Piece p, int x, int y) {
        if (inBounds(x, y)) {
            cleanUp(p);
            board[x][y] = p;
        }
    }

    /** Executes a remove.
      * Returns the piece that was removed.
      * If the input (x, y) is out of bounds, returns null and prints an appropriate message.
      * If there is no piece at (x, y), returns null and prints an appropriate message.*/
    public Piece remove(int x, int y) {
        if (inBounds(x, y)) {
            Piece p = board[x][y];
            if (p != null) {
                board[x][y] = null;
                updateSquare(x, y, false);
                return p;
            } else {
                System.out.println("Warning: trying to remove a piece from an empty square (" + x +", "+ y+").");
            }
        }
        return null;
    }

    /** Returns whether or not the the current player can end their turn.
      * To be able to end a turn, a piece must have moved or performed a capture.*/
    public boolean canEndTurn() {
        return hasMoved;
    }

    /** Called at the end of each turn.
      * Handles switching of players and anything else that should happen at the end of a turn.*/
    public void endTurn() {
        if (pieceAt(chosenX, chosenY) != null) {
            pieceAt(chosenX, chosenY).doneCapturing();
            updateSquare(chosenX, chosenY, false);
        }
        hasChosen = false;
        hasMoved = false;
        fireIsPlaying = !fireIsPlaying;
        // System.out.println("Turn ended.");
    }

    /** Count the remaining pieces on the board and update firePiece and waterPiece. Called at the end of each turn.*/
    private void countRemain() {
        int f = 0;
        int w = 0;
        for (int i = 0; i < N; ++i) {
            for (int j =0; j < N; ++j) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isFire()) {
                        f++;
                    } else {
                        w++;
                    }
                }
            }
        }
    }

    /** Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over. Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them (In this event, just leave it at null). Determine the winner solely by the number of pieces belonging to each team.*/
    public String winner() {
        countRemain();
        if (firePiece != 0 && (waterPiece != 0)) {
            return null;
        }
        if (firePiece > waterPiece) {
            return "Fire";
        } else if (waterPiece > firePiece) {
            return "Water";
        }
        return "No one";
    }

    /** GUI helper functions.*/
    private String img(Piece p) {
        String s = "img/";
        if (p.isBomb()) {
            s += "bomb-";
        } else if (p.isShield()) {
            s += "shield-";
        } else {
            s += "pawn-";
        }
        if (p.isFire()) {
            s += "fire";
        } else {
            s += "water";
        }
        if (p.isKing()) {
            s += "-crowned";
        }
        s += ".png";
        return s;
    }
    private void updateSquare(int x, int y, boolean selected) {
        if (selected) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        } else if ((x + y) % 2 == 0) {
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        } else {
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
        }
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if (board[x][y] != null) {
                StdDrawPlus.picture(x + .5, y + .5, img(board[x][y]), 1, 1);
        }
    }

    private void drawBoard() {
        /** original board*/
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                updateSquare(i, j, false);
            }
        }
        StdDrawPlus.show(25);
    }



    /** starts a GUI supported version of the game.*/
    public static void main(String args[]) {
        Board game = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        game.drawBoard();
        while (true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (game.canSelect(x, y)) {
                    // System.out.println(x+" "+y);
                    game.select(x, y);
                    StdDrawPlus.show(25);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (game.canEndTurn()) {
                    game.endTurn();
                    System.out.println(game.winner());
                }
            }

        }
    }

}