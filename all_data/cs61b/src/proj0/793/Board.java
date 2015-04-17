public class Board {

    private static final int N = 8;

    private Piece[][] pieces;
    private boolean fireTurn;
    private Piece capturer;
    private Piece selected;
    private int selectedX, selectedY;
    private Piece moved;

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];

        fireTurn     = true;

        selected     = null;
        selectedX    =   -1;
        selectedY    =   -1;
        moved        = null;
        capturer     = null;

        if (! shouldBeEmpty) {
            placeDefault();
        }
    }

    // Returns the piece at x, y, or null if there isn't one
    public Piece pieceAt(int x, int y) {
        if (x >= N || y >= N || x < 0 || y < 0) {
            return null;
        }
        
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x,y);

        // If The square contains a piece
        if (piece != null) {
            // Make sure it's our turn
            if ((piece.isFire() && fireTurn) || (!piece.isFire() && !fireTurn)) {
                // If we haven't selected, or haven't moved, we're good
                if (selected == null || (moved == null)) {
                    return true;
                }
        }
        // If the square doesn't contain a piece
        } else {
            // If we've selected a piece already
            if (selected != null) {
                // If we haven't moved, check that the destintation is valid
                if (moved == null) {
                    return validMove(selectedX, selectedY, x, y);
                // If we have moved, check that we've made a capture, and that the
                // destination is valid
                } else {
                    return (selected.hasCaptured()) && (validMove(selectedX, selectedY, x, y)
                        && Math.abs(selectedX - x) == 2);
                }
            }
        }

        return false;
    }


    public void select(int x, int y) {
        Piece piece = pieceAt(x, y);
        // If there's a piece on the square, select it
        if (piece != null) {
            selected  = piece;
            selectedX = x;
            selectedY = y;
        // If it's an empty square, move the selected piece
        } else {
            // If this is a two-move, it's a capture
            if (Math.abs(x - selectedX) == 2) {
                capturer = selected;
            }

            selected.move(x, y);
            selectedX = x;
            selectedY = y;
            moved = selected;
        }
    }

    // Places piece p at position x, y. If another piece is there,
    // it is replaced.
    public void place(Piece p, int x, int y) {
        if (x < N && y < N && x >= 0 && y >= 0) {
            pieces[x][y] = p;
        }
    }

    // Removes the piece at x, y, and returns it.
    public Piece remove(int x, int y) {
        Piece piece = pieceAt(x,y);
        if (x >= N || y >= N) {
            System.out.println("Piece removal request out of bounds.");
            return null;
        } else if (piece == null) {
            System.out.println("No piece to remove at " + x + ", " + y + ".");
            return null;
        }

        pieces[x][y] = null;
        return piece;
    }

    // Tells you if the turn can be ended or not
    public boolean canEndTurn() {
        return (moved != null) || (capturer != null);
    }

    // Ends the turn
    public void endTurn() {
        String winner = winner();
        if (winner != null) {
            System.out.println("Game over! " + winner + " wins!");
        }

        // Reset turn-dependant variables
        selected  =  null;
        selectedX =     0;
        selectedY =     0;
        moved     =  null;

        if (capturer != null) {
            capturer.doneCapturing();
            capturer       =  null;
        } else {
        }

        fireTurn = !fireTurn;
    }

    // Returns a string representing the winner of the game
    public String winner() {
        int fireCount = countFire();
        int waterCount = countWater();
        if (fireCount == 0 && waterCount == 0) return "No one";
        else if (fireCount  == 0)              return "Water" ;
        else if (waterCount == 0)              return "Fire"  ;
        else                                   return  null   ;
    }

    
    ////////////////////
    // Helper methods //
    ////////////////////

    private boolean validMove(int xi, int yi, int xf, int yf) {

        // Make sure movement is in bounds
        if (xf < 0 || xf >= N || yf < 0 || yf >= N) {
            return false;
        }

        // Make sure destination is empty
        if (pieceAt(xf, yf) != null) {
            return false;
        }

        Piece piece = pieceAt(xi, yi);

        // Make sure there's a piece to move
        if (piece == null) {
            return false;
        }
        
        // Calculate change
        int dx = xf - xi;
        int dy = yf - yi;

        boolean isOneMove = false;
        boolean isTwoMove = false;
        boolean  hasEnemy = false;

        // Checks that the piece is moving in a valid direction
        if ((dy > 0 && piece.isFire()) || (dy < 0 && !piece.isFire()) || piece.isKing()) {
            // Checks for a one move
            if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
                isOneMove = true;
            }

            // Checks for a two move (capture)
            if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
                isTwoMove = true;
            }
        }

        Piece enemyPiece = pieceAt(xi + dx/2, yi + dy/2);

        // Check if there is a valid enemy piece
        hasEnemy = (enemyPiece != null) && (enemyPiece.isFire() ^ piece.isFire());

        
        // If there's an enemy, we're good
        return isOneMove || (isTwoMove && hasEnemy);
    }

    // Places pieces in a default configuration
    private void placeDefault() {
        for (int i = 0; i < N; i += 2) {
            // Pawns
            place(new Piece(true, this, i, 0, "pawn"), i, 0);
            place(new Piece(false, this, i + 1, N - 1, "pawn"), i + 1, N - 1);

            // Shields
            place(new Piece(true, this, i + 1, 1, "shield"), i + 1, 1);
            place(new Piece(false, this, i, N - 2, "shield"), i, N - 2);

            // Bombs
            place(new Piece(true, this, i, 2, "bomb"), i, 2);
            place(new Piece(false, this, i + 1, N - 3, "bomb"), i + 1, N - 3);
        }
    }

    // Counts the number of fire pieces
    private int countFire() {
        int count = 0;
        Piece piece = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                piece = pieceAt(i, j);
                if (piece != null) {
                    if (piece.isFire()) count += 1;
                }
            }
        }
        return count;
    }

    // Counts the number of water pieces
    private int countWater() {
        int count = 0;
        Piece piece = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                piece = pieceAt(i, j);
                if (piece != null) {
                    if (!piece.isFire()) count += 1;
                }
            }
        }
        return count;
    }

    ////////////////////
    // Static Methods //
    ////////////////////

    // Draws the board, rendering all pieces
    private static void drawBoard(Board board) {
        // Draw each square
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece piece = board.pieceAt(i, j);

                if (piece != null) {
                    if (piece == board.selected) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);   
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }

                    StdDrawPlus.picture(i + .5, j + .5, buildFilename(piece), 1, 1);
                }
            }
        }
    }


    // Builds the image filename for a given piece
    private static String buildFilename(Piece piece) {
        String pieceFile = "img/";

        // Build filename
        if (piece.isBomb())          pieceFile += "bomb";
        else if (piece.isShield())   pieceFile += "shield";
        else                         pieceFile += "pawn";

        if (piece.isFire())          pieceFile += "-fire";
        else                         pieceFile += "-water";

        if (piece.isKing())          pieceFile += "-crowned";

        pieceFile += ".png";

        return pieceFile;
    }

    // Launches GUI of the game
    public static void main (String [] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board board = new Board(false);

        while (true) {
            // Selection
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x,y)){
                    board.select(x, y);
                }
            }

            // End Turn
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }

            drawBoard(board);
            StdDrawPlus.show(15);
        }
    }

}
