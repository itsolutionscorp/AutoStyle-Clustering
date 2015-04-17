public class Board {
    private int N = 8;
    private Piece previous;
    private Piece[][] pieces;
    private boolean[][] selected;
    private int prevX, prevY;
    private boolean hasMoved, hasSelected, hasCaptured, fireTurn;

    /** Starts a GUI supported version of the game. */
    public static void main (String args[]) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        /** Monitors for mouse and spacebar presses. */
        while (true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
               } 
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }          
            if (StdDrawPlus.isNPressed()) {
                b = new Board(false);
            }
            StdDrawPlus.show(10);
        }
    }

    /** Returns the type of the piece. */
    private String getType(int x, int y) {
        if (pieceAt(x, y).isShield()) {
            return "shield";
        } else if (pieceAt(x, y).isBomb()) {
            return "bomb";
        } else {
            return "pawn";
        }
    }

    /** 
      * Constructor for Board.
      * @param shouldBeEmpty - initializes an empty Board if true,  
      * otherwise, initializes a Board with the default configuration. 
      *  
      **/
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
        selected = new boolean[N][N];
        if (shouldBeEmpty) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    pieces[i][j] = null;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    selected[i][j] = false;
                    if (i % 2 != 0) {
                        if (j == N - 1) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");                       
                        } else if (j == N - 3) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        } else if (j == 1) { 
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                    } else if (i % 2 == 0) {
                        if (j == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        } else if (j == N - 2) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        } else if (j == 2) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        } 
                    }
                }
            }
        }
        hasMoved = false;
        hasCaptured = false;
        hasSelected = false;
        fireTurn = true;
    }
    
    /** Updates the state of the board. */ 
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                   if (selected[i][j]) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                } else {
                    if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieceAt(i, j) != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/" + getType(i, j) + "-fire-crowned.png", 1, 1);
                        else  StdDrawPlus.picture(i + .5, j + .5, "img/" + getType(i, j) + "-fire.png", 1, 1);
                    } else if (!pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/" + getType(i, j) + "-water-crowned.png", 1, 1);
                        else StdDrawPlus.picture(i + .5, j + .5, "img/" + getType(i, j) + "-water.png", 1, 1);
                    }
                }
            }
        }
    }

    /**
      * Gets the piece at position (x, y) on the board, or null if there is no piece.
      * @param x is the row position of the piece
      * @param y is the column position of the piece
      **/
    public Piece pieceAt(int x, int y) {
        if (x >= N || y >= N || x < 0 || y < 0 || pieces[x][y] == null) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    /**
      * Returns true if there is a piece at (x, y) and it can be selected.
      * @param x is the row position of the piece
      * @param y is the column position of the piece
      **/
    public boolean canSelect(int x, int y) {
        if (pieceAt(x, y) != null) {
            if ((fireTurn && pieces[x][y].isFire()) && (!hasSelected || (hasSelected && !hasMoved))) {
                System.out.println("Fire's turn: Piece selected.");
                return true;
            } else if ((!fireTurn && !pieces[x][y].isFire()) && (!hasSelected || (hasSelected && !hasMoved))) {
                System.out.println("Water's turn: Piece selected.");
                return true;
            } else {
                System.out.println("Empty piece selected.");
                return false;
            }
        } else {
            if (hasSelected && !hasMoved && validMove(prevX, prevY, x, y)) {
                return true;
            } else if (anotherCapture(prevX, prevY, x, y) && validMove(prevX, prevY, x, y) && previous.hasCaptured()) { 
                return true;
            }  else {
                return false;
            }
        }
    }

    /* Checks if Piece can make another valid capture for multicapture. */
    private boolean anotherCapture(int xi, int yi, int xf, int yf) {
        int dx = xf - xi;
        int dy = yf - yi;
        int xm = (xf + xi) / 2;
        int ym = (yf + yi) / 2;
        if (dx * dx + dy * dy == 8) {
            if (pieceAt(xm, ym) == null) {
                return false;
            } else {
                if (pieceAt(xi, yi).isFire()) {
                    if (pieceAt(xm, ym).isFire()) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    if (pieceAt(xm, ym).isFire()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }

    /**
      * Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
      * capture to (xf, yf) in a valid fashion compatible with the rules.
      * @param xi is the starting row position of the piece
      * @param yi is the starting column position of the piece
      * @param xf is the final row position of the piece
      * @param yf is the final column position of the piece
      **/
    private boolean validMove(int xi, int yi, int xf, int yf) {
        int dx = xf - xi;
        int dy = yf - yi;
        if (pieces[xi][yi] != null) {
            /* Restrict the diagonal directions that each side can move. */
            if (pieces[xi][yi].isKing()) {
                /* No restrictions in direction if piece is crowned. */
            } else if (pieces[xi][yi].isFire()) {
                if (dy < 0) {
                    return false;
                }
            } else if (!pieces[xi][yi].isFire()) {
                if (dy > 0) {
                    return false;
                } 
            }
        }

        /* Cases when the piece can either move forward or capture middle piece. */
        if (pieceAt(xf, yf) == null && pieceAt(xi, yi) != null) {
            if (dx * dx + dy * dy == 2) {
                return true;
            } else if (dx * dx + dy * dy == 8) {
                /* Takes care of pieces between starting and end positions. */
                int xm = (xi + xf) / 2;
                int ym = (yi + yf) / 2;
                if (this.pieceAt(xm, ym) != null) {
                    if (this.pieceAt(xi, yi).isFire()) {
                        if (this.pieceAt(xm, ym).isFire()) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        if (this.pieceAt(xm, ym).isFire()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            else{
                return false;
            }
        } 
        else {
            return false;
        }
    }

    /** 
      * Selects the piece at (x, y) if possible.
      * @param x is the row position of the piece
      * @param y is the column position of the piece
      **/
    public void select(int x, int y) {        
        /* Updates the state of the selected piece. */
        hasMoved = false;
        selected[prevX][prevY] = false;    
        if (pieceAt(x, y) != null) {
        /* Preparing the piece for movement by storing its location for later. */
        prevY = y;
        prevX = x;
        previous = pieceAt(x, y);
        pieces[x][y] = previous;
        selected[x][y] = true;
        hasSelected = true;
        } else {
            /* Move the selected piece to (x, y). */ 
            previous.move(x, y);
            /* Updates the state of the moved piece */ 
            prevX = x;
            prevY = y;
            hasMoved = true;
            /* Unhighlights a bomb piece if it explodes. */
            if (previous.isBomb() && previous.hasCaptured()) {
            	/* do nothing */
            } else {
            	selected[x][y] = true;
            }
        }
        hasSelected = false;
        selected[prevX][prevY] = false;    
        if (pieceAt(x, y) != null) {
        /* Preparing the piece for movement by storing its location for later. */
        prevY = y;
        prevX = x;
        previous = pieceAt(x, y);
        pieces[x][y] = previous;
        selected[x][y] = true;
        hasSelected = true;
        } else {
            /* Move the selected piece to (x, y). */ 
            previous.move(x, y);
            /* Updates the state of the moved piece */ 
            prevX = x;
            prevY = y;
            hasMoved = true;
            /* Unhighlights a bomb piece if it explodes. */
            if (previous.isBomb() && previous.hasCaptured()) {
            	/* do nothing */
            } else {
            	selected[x][y] = true;
            }
        }
    }

    /** 
      * Places p at (x, y). 
      * @param p is the piece to be placed at (x, y)
      * @param x is the end row position of the piece
      * @param y is the end column position of the piece
      **/
    public void place(Piece p, int x, int y) {
        /* Does nothing if (x, y) is out of bounds or if p is null. */
        if (x >= N || x < 0 || y < 0 || y >= N || p == null) { }
        else {
            pieces[x][y] = p;
        }
    }

    /** 
      * Executes a remove. Returns the piece that was removed. 
      * @param p is the piece to be placed at (x, y)
      * @param x is the end row position of the piece
      * @param y is the end column position of the piece
      **/
    public Piece remove(int x, int y) {
        if (x >= N || x < 0 || y < 0 || y >= N) { 
            System.out.println("Input is out of bounds.");
            return null; 
        } 
        String str = "Piece captured at (";
        StringBuilder s = new StringBuilder(str);
        if (pieceAt(x, y) == null) {
            System.out.println(s.append(x + ", " + y + ")"));
            return null;    
        } else {
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }
    }

    /**
      * Returns whether or not the the current player can end their turn. 
      * To be able to end a turn, a piece must have moved or performed a capture.
      **/ 
    public boolean canEndTurn() {
        return hasMoved || hasCaptured;
    }

    /* Resets the highlighted piece for the other player. */
    private void reset() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                selected[i][j] = false;
            }
        }
    }

    /** 
      * Called at the end of each turn. Handles switching of players 
      * and anything else that should happen at the end of a turn.
      **/ 
    public void endTurn() {
        this.reset();
        previous.doneCapturing();
        hasSelected = false;
        hasMoved = false;
        if (fireTurn) {
            fireTurn = false;
        } else {
            fireTurn = true;
        }
    }

    /** 
      *  Returns the winner of the game. "Fire", "Water", "No one" 
      * (tie / no pieces on the board), or null if the game is not yet over.
      **/
    public String winner() {
        /* Count the remaining number of fire and water pieces on the board. */
        int firePieces = 0;
        int waterPieces = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) == null) {
                } else if (pieceAt(i, j).isFire()) {
                    firePieces += 1;
                } else if (!pieceAt(i, j).isFire()) {
                    waterPieces += 1;
                } 
            }
        }

        if (firePieces + waterPieces == 0) {
            return "No one";
        } else if (firePieces == 0) {
            return "Water";
        } else if (waterPieces == 0){
            return "Fire";
        } else {
            return null;
        }
    }
}