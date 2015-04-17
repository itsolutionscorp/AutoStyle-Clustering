/**
 * @author  David Huang
 */

public class Board {

    private static int BOARD_SIZE = 8;

    private Piece[][] pieces;
    private int whoseTurn;
    private int currentX;
    private int currentY;
    private boolean moved;

    public static void main(String[] args) {
        Board board = new Board(false);

        String result = board.winner();
        while (true && result == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)) {
                    board.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            StdDrawPlus.show(50);
            result = board.winner();
            board.drawBoard();
        }
        System.out.println("Winner: " + result);
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, BOARD_SIZE);
        StdDrawPlus.setYscale(0, BOARD_SIZE);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (i == currentX && j == currentY) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }

                Piece p = pieces[i][j];
                if (p != null) {
                    // FIXME: should have a private method to draw each piece
                    //        also handle isKing situation
                    if (p.isKing()) {
                        if (p.isFire()) {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (p.isFire()) {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                        else {
                            if (p.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        whoseTurn = 0;
        currentX = -1;
        currentY = -1;
        moved = false;
        if (!shouldBeEmpty) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    // Set the fire checkers
                    if (i % 2 == 0 && j == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if (i % 2 != 0 && j == 1) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if (i % 2 == 0 && j == 2) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }

                    // Set the water checkers
                    if (i % 2 != 0 && j == (BOARD_SIZE-1)) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                    if (i % 2 == 0 && j == (BOARD_SIZE-2)) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if (i % 2 != 0 && j == (BOARD_SIZE-3)) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x >= BOARD_SIZE || x < 0 || y >= BOARD_SIZE || y < 0) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);
        Piece recentSelected = pieceAt(currentX, currentY);

        // Handle a bomb after explosion
        if (moved && recentSelected == null) {
            return false;
        }
        // Already chose a piece and then choose a empty grid
        if (p == null && recentSelected != null) {
            if (validMove(currentX, currentY, x, y)) {
                if (!moved) {
                    return true;
                }
                else {
                    if (recentSelected.hasCaptured()) {
                        int dx = x - currentX;
                        int dy = y - currentY;
                        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
                            return true;
                        }
                    }
                }
            }
        }
        else if (p != null && p.side() == whoseTurn) {
            if (recentSelected == null || !moved) {
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        // FIXME: didn't consider capture situation
        //        should use avg to get the moving to point.
        Piece srcPiece = pieceAt(xi, yi);
        Piece dstPiece = pieceAt(xf, yf);
        if (dstPiece != null) {
            return false;
        }

        int dx = (xf - xi);
        int dy = (yf - yi);
        // Move diagonally 1 grid
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            if (srcPiece.isKing()) {
                return true;
            }
            // If it's fire and not a king, it can only move upward.
            else if (srcPiece.isFire() && dy > 0) {
                return true;
            }
            else if (!srcPiece.isFire() && dy < 0) {
                return true;
            }
        }
        // Move diagonally 2 grid, possible by capturing
        else if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            // The following might be overflow, considering use subtraction
            // to get the direction.
            // Piece midPiece = pieceAt((xi+xf)/2, (yi+yf)/2);
            Piece midPiece = pieceAt(xi + dx/2, yi + dy/2);
            if (midPiece != null && midPiece.side() != srcPiece.side()) {
                return true;
            }
        }

        return false;
    }

    public void select(int x, int y) {
        // Shouldn't call canSelect in this method
        Piece currentSelected = pieceAt(x, y);
        Piece recentSelected = pieceAt(currentX, currentY);
        currentX = x;
        currentY = y;
        if (currentSelected == null) {
            recentSelected.move(x, y);
            moved = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if (x >= BOARD_SIZE || y >= BOARD_SIZE || p == null) {
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p == null) {
            System.out.printf("Error: try to remove a null piece @ (%d, %d)%n", x, y);
            return null;
        }
        pieces[x][y] = null;
        if (x == currentX && y == currentY) {
            currentX = -1;
            currentY = -1;
        }
        return p;
    }

    public boolean canEndTurn() {
        if (moved) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        Piece recentSelected = pieceAt(currentX, currentY);
        // Since recentSelected may be a bomb and kill itself already
        if (recentSelected != null) {
            recentSelected.doneCapturing();
        }
        currentX = -1;
        currentY = -1;
        moved = false;
        // Change whoseTurn
        if (whoseTurn == 0) {
            whoseTurn = 1;
        }
        else {
            whoseTurn = 0;
        }
    }

    public String winner() {
        boolean hasFire = false;
        boolean hasWater = false;
        for (int x = 0; x < BOARD_SIZE; x += 1) {
            for (int y = 0; y < BOARD_SIZE; y += 1) {
                Piece p = pieces[x][y];
                if (p != null) {
                    if (p.isFire()) {
                        hasFire = true;
                    }
                    else {
                        hasWater = true;
                    }
                }

                if (hasFire && hasWater) {
                    return null;
                }
            }
        }

        if (hasFire && !hasWater) {
            return "Fire";
        }
        else if (!hasFire && hasWater) {
            return "Water";
        }

        return "No one";
    }
}
