public class Board {
 
    private Piece[][] pieces;
    private boolean selected, gameOver, captured, multiCapture;
    private boolean moved = false;
    private boolean playerTurn = true;
    private Piece selectedPiece;
    private int x, y;
    private static final int N = 8;
 
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
 
        /** Creates a new Board. */
        Board b = new Board(false);
 
        /** Monitors for mouse presses. */
        while(true) {
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
            StdDrawPlus.show(50);
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
        if (!shouldBeEmpty) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0 && j == 2) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb"); 
                    }
                    else if ((i + j) % 2 == 0 && j == 1) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield"); 
                    }
                    else if ((i + j) % 2 == 0 && j == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn"); 
                    }
                    else if ((i + j) % 2 == 0 && j == 5) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb"); 
                    }
                    else if ((i + j) % 2 == 0 && j == 6) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield"); 
                    }
                    else if ((i + j) % 2 == 0 && j == 7) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn"); 
                    }
                }
            }
        }
    }
 
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                 
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selectedPiece instanceof Piece && x == i && y == j) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                Piece item = pieceAt(i, j);
                if (item instanceof Piece) {
                    /* Drawing fire pieces. */
                    if (item.isFire()) {
                        if (item.isBomb()) {
                            if (item.isKing())
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            else
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                        else if (item.isShield()) {
                            if (item.isKing())
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            else
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        else {
                            if (item.isKing())
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            else
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }
                    /* Drawing water pieces. */
                    else if (item.isBomb()) {
                        if (item.isKing())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        else 
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    }
                    else if (item.isShield()) {
                        if (item.isKing())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    }
                    else {
                        if (item.isKing())
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                }                 
            }
        }
    }
 
    /** Gets the piece at position (x, y) on the board, 
      * or null otherwise. */
    public Piece pieceAt(int x, int y) {
        if ((x >= 0 && x < 8) && (y >= 0 && y < 8))
            return pieces[x][y];
        else
            return null;
    }
 
    /** Returns true if the square at (x, y) can be selected. */
    public boolean canSelect(int x, int y) {
        
        /* Clicked on a piece. */
        if (pieceAt(x,y) instanceof Piece) {
            /* If piece has already moved, then return false. */
            if (moved) 
                return false;
            /* If it is the correct player's turn. */
            else if (pieceAt(x,y).isFire() == playerTurn) {
                /* If a piece hadn't been selected yet. */
                if (!selected) {
                    System.out.println("First select!");
                    return true;
                }
                /* If a piece was selected by not moved yet. */
                if (selected && !moved) {
                    //System.out.println("Both are pieces, not valid moved!");
                    return true;
                }
                /* If the same spot is selected, return false. */
                else if (selected && this.x == x && this.y == y)
                    return false;
                else
                    return true;
            }
            else
                return false;
        }
        /* Selected an empty square. */
        else {
            /* If the piece has not captured another piece. */
            if (!(pieceAt(this.x, this.y).hasCaptured())) {
                System.out.println("Clicked on empty square.");
                return (selected && !moved) && validMove(this.x, this.y, x, y);
            }
            /* Piece has captured a piece, might captured another. */
            else 
                return selected && pieceAt(this.x, this.y).hasCaptured() && validMove(this.x, this.y, x, y);
        }
    }
     
 
    /** Returns true if the piece at (xi, yi)
      * can either move to or capture to (xf, yf),
      * strictly from a geometric/piece-race point of view. */
    private boolean validMove(int xi, int yi, int xf, int yf) {

        /* There is a fire piece. */
        if (pieces[xi][yi] instanceof Piece && pieces[xi][yi].isFire()) {
            /* It only moves up. Not counting kings for now. */
            if (yf - yi > 0) {
                System.out.println("Here! Line 178!");
                /* If there is a water piece diagonal right to current piece. */
                if (pieceAt(xi+1, yi+1) instanceof Piece && !pieceAt(xi+1, yi+1).isFire()) {
                    System.out.println("Here! Line 181!");
                    /* If there is an empty space 2 diagonal spaces away. */
                    if (!(pieceAt(xi+2, yi+2) instanceof Piece)) {
                        if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2) {
                            System.out.println("Line 185, I can move to the top-right!");
                            return true;
                        }
                    }
                }
                /* There is a water piece to the diagonal left. */
                if (pieceAt(xi-1, yi+1) instanceof Piece && !pieceAt(xi-1, yi+1).isFire()) {
                    /* There is an empty space 2 diagonal spaces away. */
                    if (!(pieceAt(xi-2, yi+2) instanceof Piece)) {
                        if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2) {
                            System.out.println("Line 199, I can move to the top-left!");
                            return true;
                        }
                    }
                }
                /* If the two diagonal pieces are fire, then return false. */
                if ((pieceAt(xi+1, yi+1) instanceof Piece && pieceAt(xi+1, yi+1).isFire()) &&
                    (pieceAt(xi-1, yi+1) instanceof Piece && pieceAt(xi-1, yi+1).isFire()))
                    return false;
                /* If there are no pieces in the way, can move only 1 spot over, diagonally. */
                if (!(pieceAt(xi+1, yi+1) instanceof Piece)) {
                    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1)
                        return true;
                }
                if (!(pieceAt(xi-1, yi+1) instanceof Piece)) {
                    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1)
                        return true;
                }
                return false;
            } 
            else {
                System.out.println("yf is less than yi!");
                return false;
            }
        }
        /* There is a water piece. */
        else if (pieceAt(xi,yi) instanceof Piece && !(pieceAt(xi,yi).isFire())) {
            /* It only moves down. Not counting kings for now. */
            if (yf - yi < 0) {
                System.out.println("Here! Line 236!");
                /* If there is a fire piece diagonal bottom left to current piece. */
                if (pieceAt(xi-1, yi-1) instanceof Piece && pieceAt(xi-1, yi-1).isFire()) {
                    System.out.println("Here! Line 239!");
                    /* If there is an empty space 2 diagonal spaces away. */
                    if (!(pieceAt(xi-2, yi-2) instanceof Piece)) {
                        if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2) {
                            System.out.println("Line 243, I can move to the bottom-left!");
                            return true;
                        }
                    }
                }
                /* There is a fire piece to the diagonal bottom right. */
                if (pieceAt(xi+1, yi-1) instanceof Piece && pieceAt(xi+1, yi-1).isFire()) {
                    /* There is an empty space 2 diagonal spaces away. */
                    if (!(pieceAt(xi+2, yi-2) instanceof Piece)) {
                        if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2) {
                            System.out.println("Line 257, I can move to the bottom-right!");
                            return true;
                        }
                    } 
                }
                /* If the two diagonal pieces are water, then return false. */
                if ((pieceAt(xi+1, yi-1) instanceof Piece && !(pieceAt(xi+1, yi-1).isFire())) && 
                    (pieceAt(xi-1, yi-1) instanceof Piece && !(pieceAt(xi-1, yi-1).isFire()))) {
                    return false;
                }
                /* If there are no pieces in the way, can move only 1 spot over, diagonally. */
                if (!(pieceAt(xi-1, yi-1) instanceof Piece)) {
                    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1)
                        return true;
                }
                if (!(pieceAt(xi+1, yi-1) instanceof Piece)) {
                    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1)
                        return true;
                }
            } 
            else {
                System.out.println("yf is greater than yi!");
                return false;
            }
        }
        return false;
    }
 
    /** Selects the piece at (x, y) if possible. */
    public void select(int x, int y) {
        /* Selected a piece. */
        if (pieceAt(x,y) instanceof Piece) {
            /* prep piece for movement. */
            this.x = x;
            this.y = y;
            selectedPiece = pieceAt(x,y);
            selected = true;
        }
        /* Selected an empty square. */
        else {
            /* move most recently selected piece to x,y */
            selectedPiece = remove(this.x, this.y);
            this.x = x;
            this.y = y;
            selectedPiece.move(x,y);
            moved = true;
        }
    }
 
    /** Places p at (x, y). Does nothing if (x, y)
      * is out of bounds. If another piece already exists
      * at (x, y), p will replace that piece. */
    public void place(Piece p, int x, int y) {
        if ((x >= 0 && x < 8) && (y >= 0 && y < 8))
            pieces[x][y] = p;
    }
 
    /** Returns the piece that was removed. 
      * Returns null if out of bounds or empty at (x, y). */
    public Piece remove(int x, int y) {
        Piece p = pieces[x][y];
        pieces[x][y] = null;
        return p;
    }
 
    /** Returns whether or not the the current player 
      * can end their turn. To be able to end a turn, 
      * a piece must have moved or performed a capture. */
    public boolean canEndTurn() {
        // if (selectedPiece)
        return moved || captured;
    }
 
    /** Called at the end of each turn. Handles switching of 
      * players and anything else that should happen at the end of a turn. */
    public void endTurn() {
        captured = false;
        moved = false;
        selectedPiece = null;
        selected = false;
        playerTurn = !playerTurn;
    }
 
    /** Returns the winner of the game. "Fire", "Water", "No one" 
      * (tie / no pieces on the board), or null if the game is not yet over. */
    public String winner() {
        int countBlue = 0;
        int countRed = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        countRed += 1;
                    }
                    else
                        countBlue += 1;
                }
            }
        }
        if (gameOver) {
            if (countBlue > countRed)
                return "Water";
            else if (countRed > countBlue)
                return "Fire";
            else
                return "No one";
        }
        else
            return null;
    }
}