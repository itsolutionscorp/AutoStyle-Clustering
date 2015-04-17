// #FINAL SUBMIT 10:19 PM 2/13/2015
public class Board {

    private Piece[][] skeleton; //Location of pieces.
    private static int boardSize = 8;
    private int turn; //0 for fire player; 1 for water player
    private int[] selected; //coordinates of the selected square; -1, -1 if nothing is selected
    private boolean moved;
    private boolean explosion;

    /** Starts a GUI supported version of the game. */
    public static void main (String args[]) {
        StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);
        Board b = new Board(false);
        //Place Testing Board Here:
        /*Board b = new Board(true);
        Piece wShield = new Piece(false, b, 3, 3, "shield");
        Piece fBomb = new Piece(true, b, 1, 1, "bomb");
        Piece wBomb = new Piece(false, b, 4, 6, "shield");
        Piece fpawn = new Piece(true, b, 4, 2, "pawn");
        Piece wPawn = new Piece(false, b, 7, 7, "pawn");
        b.place(wShield, 3, 3);
        b.place(fBomb, 1, 1);
        b.place(wBomb, 4, 6);
        b.place(fpawn, 4, 2);
        b.place(wPawn, 7, 7);*/
        while(true) {
            b.drawBoard(boardSize);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            } else if(StdDrawPlus.isSpacePressed()) {
                if(b.canEndTurn()) {
                    b.endTurn();
                    String win = b.winner();
                    if(win != null) {
                        System.out.println(win + " wins.");
                        return;
                    }
                }
            }
            StdDrawPlus.show(25);
        }
    }

    /** The constructor for Board. If shouldBeEmpty is true, initializes
      * an empty Board. Otherwise, initializes a Board with the default
      * configuration. Note that an empty Board could possibly be useful
      * for testing purposes. */
    public Board(boolean shouldBeEmpty) {
        turn = 0;
        selected = new int[]{-1, -1};
        moved = false;
        explosion = false;
        skeleton = new Piece[boardSize][boardSize];
        if(!shouldBeEmpty) {
            setUp();
        }
    }

    /** Places all the pieces in their default positions on the board. */
    private void setUp() {
        skeleton = new Piece[boardSize][boardSize];
        for(int x = 0; x < boardSize; x += 1) {
            for(int y = 0; y < boardSize; y += 1) {
                if(y == 0 && x%2 == 0) {
                    skeleton[x][y] = new Piece(true, this, x, y, "pawn");
                } else if(y == 1 && x%2 == 1) {
                    skeleton[x][y] = new Piece(true, this, x, y, "shield");
                } else if(y == 2 && x%2 == 0) {
                    skeleton[x][y] = new Piece(true, this, x, y, "bomb");
                } else if(y == 5 && x%2 == 1) {
                    skeleton[x][y] = new Piece(false, this, x, y, "bomb");
                } else if(y == 6 && x%2 == 0) {
                    skeleton[x][y] = new Piece(false, this, x, y, "shield");
                } else if(y == 7 && x%2 == 1) {
                    skeleton[x][y] = new Piece(false, this, x, y, "pawn");
                }
            }
        }
    }

    /* Uses StdDraw to draw the board and the pieces on it. */
    private void drawBoard(int N) {
        for (int x = 0; x < N; x += 1) {
            for (int y = 0; y < N; y += 1) {
                if ((x + y) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                if(this.inBounds(selected[0], selected[1])) {
                    Piece p = this.pieceAt(selected[0], selected[1]);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(selected[0] + .5, selected[1] + .5, .5);
                    if(p!= null) {
                        StdDrawPlus.picture(selected[0] + .5, selected[1] + .5, this.locateImg(p), 1, 1);  
                    }
                }
                if (skeleton[x][y] != null) {
                    Piece p = skeleton[x][y];
                    String img = this.locateImg(p);
                    StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
                }
            }
        }
    }

    /** Finds the image location for the Piece. */
    private String locateImg(Piece p) {
        String img = "img/";
        if(p.isBomb()) {
            img += "bomb-";
        } else if(p.isShield()) {
            img += "shield-";
        } else {
            img += "pawn-";
        }
        if(p.isFire()) {
            img += "fire";
        } else {
            img += "water";
        }
        if(p.isKing()) {
            img += "-crowned";
        }
        img += ".png";
        return img;
    }

    /** Gets the piece at position (x, y) on the board, or null if there
      * is no piece. If (x, y) are out of bounds, returns null. */
    public Piece pieceAt(int x, int y) {
        if(!this.inBounds(x, y)) {
            return null;
        }
        return skeleton[x][y];
    }

    /** Returns true if the square at (x, y) can be selected. */
    public boolean canSelect(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if(!this.inBounds(x, y) || (p!= null && turn != p.side()) || explosion) {
            return false;
        }
        Piece slctd = this.pieceAt(selected[0], selected[1]);
        if(p != null) {
            if(slctd == null) {
                return true;
            } else if(!moved) {
                return true;
            }
        } else if(p == null) {
            if(slctd != null && !moved && this.validMove(selected[0], selected[1], x, y)) {
                return true;
            } else if(slctd != null && slctd.hasCaptured() && this.validMove(selected[0], selected[1], x, y)) {
                return true;
            }
        }
        return false;
    }

    /** Returns whether or not the piece at (xi, yi) can move to (xf, yf) */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = this.pieceAt(xi, yi);
        int dx = xf-xi;
        int dy = yf-yi;
        if(Math.abs(dy) == Math.abs(dx) && Math.abs(dx) == 1 && !p.hasCaptured()) {
            if(p.isKing()) {
                return true;
            } else if(p.isFire() && dy == 1) {
                return true;
            } else if(!p.isFire() && dy == -1) {
                return true;
            }
        } else if(Math.abs(dy) == Math.abs(dx) && Math.abs(dx) == 2) {
            Piece target = this.pieceAt(xi + dx/2, yi + dy/2);
            if(target != null && target.side() != p.side()) {
                if(p.isKing()) {
                    return true;
                } else if(p.isFire() && dy == 2) {
                    return true;
                } else if(!p.isFire() && dy == -2) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Selects the square at (x, y). This method assumes canSelect
      * (x,y) returns true. Optionally, it is recommended to color
      * the background of the selected square white on the GUI via
      * the pen color function. For any piece to perform a capture,
      * that piece must have been selected first. If you select a
      * square with a piece, you are prepping that piece for movement.
      * If you select an empty square (assuming canSelect returns
      * true), you should move your most recently selected piece to
      * that square. */
    public void select(int x, int y) {
        Piece target = this.pieceAt(x, y);
        if(target == null) {
            Piece p = this.remove(selected[0], selected[1]);
            p.move(x, y);
            moved = true;
            //System.out.println("Piece captured: " + p.hasCaptured());
            if(p.isBomb() && p.hasCaptured()) {
                explosion = true;
                //System.out.println("Boom");
                return;
            }
        }
        selected[0] = x;
        selected[1] = y;
        //System.out.println("Selected is " + selected[0] + ", " + selected[1]);
        }

    /** Places p at (x, y). If (x, y) is out of bounds or if p is
      * null, does nothing. If another piece already exists at (x, y),
      * p will replace that piece. (This method is potentially useful
      * for creating specific test circumstances.) 
      * Alex's Note: Method assumes piece has proper coordinates already. */
    public void place(Piece p, int x, int y) {
        if(p == null || !this.inBounds(x, y)) {
            return;
        }
        skeleton[x][y] = p;
    }

    /** Returns if the coordinates are in the board. */
    private boolean inBounds(int x, int y) {
        if(x >= boardSize || y >= boardSize || x < 0 || y < 0){
            return false;
        }
        return true;
    }

    /** Executes a remove. Returns the piece that was removed. If
      * the input (x, y) is out of bounds, returns null and prints
      * an appropriate message. If there is no piece at (x, y),
      * returns null and prints an appropriate message. */
    public Piece remove(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if(!this.inBounds(x, y)) {
            System.out.println("ERROR: Attempted to remove from a square outside the board.");
            return null;
        } else if(p == null) {
            System.out.println("ERROR: Attempted to remove a piece that was not there.");
            return null;
        }
        skeleton[x][y] = null;
        return p;
    }

    /** Returns whether or not the the current player can end their
      * turn. To be able to end a turn, a piece must have moved or
      * performed a capture. */
    public boolean canEndTurn() {
        return moved;
    }

    /** Called at the end of each turn. Handles switching of players
      * and anything else that should happen at the end of a turn. */
    public void endTurn() {
        Piece p = this.pieceAt(selected[0], selected[1]);
        if(!explosion) {
            p.doneCapturing();
        }
        turn = 1 - turn;
        selected = new int[]{-1, -1};
        moved = false;
        explosion = false;
    }

    /** Returns the winner of the game. "Fire", "Water", "No one"
      * (tie / no pieces on the board), or null if the game is not yet
      * over. Assume there is no stalemate situation in which the current
      * player has pieces but cannot legally move any of them (In this
      * event, just leave it at null). Determine the winner solely by the
      * number of pieces belonging to each team. */
    public String winner() {
        int[] pieces = this.countPieces();
        if(pieces[0] == 0 && pieces[1] == 0) {
            return "No one";
        } else if(pieces[1] == 0) {
            return "Fire";
        } else if(pieces[0] == 0) {
            return "Water";
        } else {
            return null;
        }
    }

    /** Counts the number of pieces left on the board for each player
      * and returns it as an array of two values: the number of fire
      * pieces, and the number of water pieces. */
    private int[] countPieces() {
        int nFire = 0;
        int nWater = 0;
        for(int x = 0; x < boardSize; x += 1) {
            for(int y = 0; y < boardSize; y += 1) {
                Piece p = this.pieceAt(x, y);
                if(p != null && p.isFire()) {
                    nFire += 1;
                } else if(p != null && !p.isFire()) {
                    nWater += 1;
                }
            }
        }
        return new int[]{nFire, nWater};
    }
}