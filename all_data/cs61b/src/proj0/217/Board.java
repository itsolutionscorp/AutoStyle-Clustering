public class Board {

    private Piece[][] pieces;
    private int N = 8;
    private boolean hasMoved = false;
    private Piece selected = null;
    private int selectedx;
    private int selectedy;
    private boolean bisFire = true;
    private boolean isEmpty;


    public Board(boolean shouldBeEmpty) {
        /* Constructor for Board. If shouldBeEmpty is true, initializes an empty
        ** Board. Else, initializes a Board with the default configuration. Note
        ** that an empty Board could be useful for testing purposes. */
        isEmpty = shouldBeEmpty;
        Init();
    }

    private void Init() {
        pieces = new Piece[N][N];
        if (isEmpty == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i+j) % 2 == 0) {
                        if (j == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        } else if (j == 1) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        } else if (j == 2) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        } else if (j == 5) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        } else if (j == 6) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        else if (j == 7) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                if (selected != null && selected == pieces[i][j]) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if ((i + j) % 2 == 0) {
                    if (pieces[i][j] != null && pieces[i][j].isFire()) {
                        if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    } else if (pieces[i][j] != null) {
                        if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        } else if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }


    public Piece pieceAt(int x, int y) {
        /* Gets the piece at position (x, y) on the board, or null if there is
        ** no piece. If (x, y) are out of bounds, returns null. */
        if (x < N && y < N) {
            return pieces[x][y];
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        /* Returns true if the square at (x, y) can be selected.
        ** A piece may be selected if it is the corresponding player's turn and
        ** one of the following is true:
        **      - The player has not selected a piece yet.
        **      - The player has selected a piece, but did not move it.
        ** An empty square may be selected if one of the following is true:
        **      - During this turn, the player has selected a Piece which
        **        hasn't moved yet and is selecting an empty spot which is a
        **        valid move for the previously selected Piece.
        **      - During this turn, the player has selected a piece,
        **        captured, and has selected another valid capture
        **        destination. When performing multi-captures, you should only
        **        select the active piece once; all other selections should be
        **        valid destination points.*/
        if (x < N && y < N) {
            if (pieces[x][y] != null && pieces[x][y].isFire() == bisFire) {
                if (selected == null) {return true;}
                else if (selected.hasCaptured() == false && hasMoved == false) {
                    return true;
                    }
                }
            else if (selected != null) {
                if (selected.hasCaptured() && canCapture(selectedx, selectedy, x, y)) {
                    return true;
                } else if (hasMoved == false && validMove(selectedx, selectedy, x, y)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        /* Returns true if the piece at (xi, yi) can either move to (xf, yf
        ** or capture to (xf, yf) in a valid fashion campatible with the rules. */
        if (pieceAt(xf, yf) != null || pieceAt(xi, yi) == null) {return false;}
        if (canCapture(xi, yi, xf, yf)) {return true;}
        if ((Math.abs(xi - xf)) == 1) {
            if ((yi - yf) == 1) {
                if(pieceAt(xi, yi).isFire() == false || pieceAt(xi, yi).isKing()) {
                    return true;
                }
            }
            else if ((yf - yi) == 1) {
                if(pieceAt(xi, yi).isFire() || pieceAt(xi, yi).isKing()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canCapture(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) == null) {return false;}
        if (pieceAt(xi, yi).isBomb() && pieceAt(xi, yi).hasCaptured()) {return false;}
        if (xf < 0 || xf >= N || yf < 0 || yi >= N) {return false;}
        /* water piece */
        if (pieceAt(xi, yi).isFire() == false) {
            if ((xi - xf) == 2) {
                if ((yi - yf) == 2) {
                    if (pieceAt(xi-1, yi-1) != null && pieceAt(xi-1, yi-1).isFire() == true) {
                        return true;
                    }
                } else if ((yf - yi) == 2 && pieceAt(xi, yi).isKing()) {
                    if (pieceAt(xi-1, yi+1) != null && pieceAt(xi-1, yi+1).isFire() == true) {
                        return true;
                    }
                }
            } else if ((xf - xi) == 2) {
                if ((yi - yf) == 2) {
                    if (pieceAt(xi+1, yi-1) != null && pieceAt(xi+1, yi-1).isFire() == true) {
                        return true;
                    }
                } else if ((yf - yi) == 2 && pieceAt(xi, yi).isKing()) {
                    if (pieceAt(xi+1, yi+1) != null && pieceAt(xi+1, yi+1).isFire() == true) {
                        return true;
                    }
                }
            }
        }
        /* fire piece */
        else if (pieceAt(xi, yi).isFire()) {
            if ((xi - xf) == 2) {
                if ((yf - yi) == 2) {
                    if (pieceAt(xi-1, yf-1) != null&& pieceAt(xi-1, yf-1).isFire() == false) {
                        return true;
                    }
                } else if ((yi - yf) == 2 && pieceAt(xi, yi).isKing()) {
                    if (pieceAt(xi-1, yf+1) != null&& pieceAt(xi-1, yf+1).isFire() == false) {
                        return true;
                    }
                }
            } else if ((xf - xi) == 2) {
                if ((yf - yi) == 2) {
                    if (pieceAt(xi+1, yf-1) != null && pieceAt(xi+1, yf-1).isFire() == false) {
                        return true;
                    }
                } else if ((yi - yf) == 2 && pieceAt(xi, yi).isKing()) {
                    if (pieceAt(xi+1, yf+1) != null && pieceAt(xi+1, yf+1).isFire() == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        /* Selects the square at (x, y). This method assumes canSelect (x,y) returns
        ** true. Optionally, it is recommended to color the background of the selected
        ** square white on the GUI via the pen color function. For any piece to
        ** perform a capture, that piece must have been selected first. If you
        ** select a square with a piece, you are prepping that piece for movement.
        ** If you select an empty square (assuming canSelect returns true), you
        ** should move your most recently selected piece to that square. */
        if (pieceAt(x, y) != null) {
            selected = pieces[x][y];
        } else if (selected != null) {
            selected.move(x, y);
        }
        selectedx = x;
        selectedy = y;
    }

    public void place(Piece p, int x, int y) {
        /* Places p at (x, y). if (x, y) is out of bounds or if p is null, does
        ** nothing. If p already exists in the current Board, first removes it
        ** from its initial position. If another piece already exists at (x, y),
        ** p will replace that piece. (This method is potentially useful for
        ** creating specific test circumstances.) */
        if (x < N && y < N && p != null) {
            if (pieceAt(x, y) != null) {
                pieces[x][y] = null;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pieces[i][j] == p) {
                        pieces[i][j] = null;
                        hasMoved = true;
                    }
                }
            }
            pieces[x][y] = p;
            selected = p;
        }
    }

    public Piece remove(int x, int y) {
        /* Executes a remove. Returns the piece that was removed. If the input
        ** (x, y) is out of bounds, returns null and prints an appropriate message.
        ** If there is no piece at (x, y), returns null and prints an appropriate
        ** message. */
        if (x < N && y < N) {
            Piece temp = pieceAt(x, y);
            pieces[x][y] = null;
            if (temp == null) {
                System.out.println("no piece at selected removal location");
            }
            return temp;
        }
        System.out.println("Error: input out of bounds");
        return null;
    }

    public boolean canEndTurn() {
        /* Returns whether or not the the current player can end their turn. To
        ** be able to end a turn, a piece must have moved or performed a capture. */
        return (hasMoved || (selected != null && selected.hasCaptured()));
    }

    public void endTurn() {
        /* Called at the end of each turn. Handles switching of players and
        ** anything else that should happen at the end of a turn. */
        selected.doneCapturing();
        selected = null;
        hasMoved = false;
        bisFire = !bisFire;
    }

    private boolean canMove(int x, int y) {
        if (canCapture(x, y, x+2, y+2) || canCapture(x, y, x-2, y-2)
        || canCapture(x, y, x+2, y-2) || canCapture(x, y, x-2, y+2)) {
            return true;
        }
        else if (validMove(x, y, x+1, y+1) || validMove(x, y, x-1, y-1)
        || validMove(x, y, x+1, y-1) || validMove(x, y, x-1, y+1)) {
            return true;
        }
        return false;
    }

    public String winner() {
        /* Returns the winner of the game. "Fire", "Water", "No one" (tie / no
        ** pieces on the board), or null if the game is not yet over. Assume there
        ** is no stalemate situation in which the current player has pieces but
        ** cannot legally move any of them (In this event, just leave it at null).
        ** Determine the winner solely by the number of pieces belonging to each
        ** team. */
        int Water = 0;
        int Fire = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        Fire += 1;
                    } else {
                        Water += 1;
                    }
                }
            }
        }
        if (Fire != 0 && Water != 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pieces[i][j] != null) {
                        if (canMove(i, j)) {
                            return null;
                        }
                    }
                }
            }
        }
        if (Fire > Water) {
            return "Fire";
        } else if (Water > Fire) {
            return "Water";
        } else if (Fire == 0 && Water == 0) {
            return "No one";
        } else {
            return null;
        }
    }


    public static void main (String args[]) {
        /* starts a GUI supported version of the game. */

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        b.drawBoard(8);

        while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int ix = (int) x;
                int iy = (int) y;
                if (b.canSelect(ix, iy)) {
                    b.select(ix, iy);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {b.endTurn();}
            }
            StdDrawPlus.show(10);
        }
    }
}
