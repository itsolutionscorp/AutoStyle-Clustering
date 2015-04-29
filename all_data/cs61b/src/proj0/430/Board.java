/**
 * 
 * @author Kaveh Karbasi
 *
 */

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private int selectedX = -10;
    private int selectedY = -10;
    private boolean fireTurn = true;
    private boolean hasMoved = false;
    private boolean hasSelected = false;
    private boolean hasCaptured = false;

    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            for (int i = 0; i < 8; i += 2) {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }

        }
    }

    /**
     * Gets the piece at position (x, y) on the board, or null if there is no
     * piece. If (x, y) are out of bounds, returns null.
     * 
     * @param x
     * @param y
     * @return Piece at (x,y)
     */
    public Piece pieceAt(int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    /**
     * Places p at (x, y). If (x, y) is out of bounds, does nothing. If another
     * piece already exists at (x, y), p will replace that piece.
     * 
     * @param P
     *            : Piece to be placed at (x,y)
     * @param x
     *            : integer
     * @param y
     *            : integer
     */
    public void place(Piece P, int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {

            pieces[x][y] = P;
            if (canChain(x, y)) {
                System.out.println("can chain");
                select(x, y);

            }

        }

        selectedX = x;
        selectedY = y;
    }

    /**
     * Executes a remove. Returns the piece that was removed.
     * 
     * @param x
     *            int
     * @param y
     *            int
     * @return Piece
     */

    /**
     * Helper method, returns type of a given Piece
     * 
     * @param P
     * @return
     */
    private String getType(Piece P) {
        if (P.isBomb())
            return "bomb";
        else if (P.isShield())
            return "shield";
        else
            return "pawn";

    }

    public Piece remove(int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            if (pieces[x][y] != null) {
                hasMoved = true;
                Piece t = new Piece(pieces[x][y].isFire(), this, x, y,
                        getType(pieces[x][y]));
                pieces[x][y] = null;
                selectedX = -10;
                selectedY = -10;
                return t;
            } else {
                System.out.println("Tried to remove a non-existing piece!!");
                return null;
            }
        } else {
            System.out.println("Tried to access out-of-board index!!");
            return null;
        }

    }

    public boolean canSelect(int x, int y) {
        System.out.println(selectedX + " , " + selectedY);
        if (pieceAt(x, y) != null) {
            if (pieces[x][y].isFire() != fireTurn) {
                System.out.println("wrong player");
                return false;
            }

            if (!hasSelected) {
                System.out
                        .println("The player has not selected yet -- > can be selected");
                return true;
            } else if (!hasMoved) {
                System.out.println("The player has selected but not moved");
                return true;
            }

        } else {
            if (hasSelected && !hasMoved) {
                if (validMove(selectedX, selectedY, x, y)) {
                    System.out.println("move is valid");
                    return true;
                }

            }
            if (canChain(selectedX, selectedY)) {
                if (validMove(selectedX, selectedY, x, y)) {
                    System.out.println("--------------can chain in canSelect");
                    return true;
                }
            }

        }

        return false;
    }

    private boolean canChain(int x, int y) {
        if (hasCapturableNeighbor(x, y) && pieceAt(x, y).hasCaptured())
            return true;
        return false;
    }

   

    private boolean hasCapturableNeighbor(int x, int y) {
        if (pieceAt(x, y) != null)
            if (!pieceAt(x, y).isKing()) {

                if (pieceAt(x, y).isFire()) {
                    if (pieceAt(x + 1, y + 1) != null
                            && pieceAt(x + 2, y + 2) == null
                            && pieceAt(x + 1, y + 1).isFire() != pieceAt(x, y)
                                    .isFire())
                        return true;
                    if (pieceAt(x - 1, y + 1) != null
                            && pieceAt(x - 2, y + 2) == null
                            && pieceAt(x - 1, y + 1).isFire() != pieceAt(x, y)
                                    .isFire())
                        return true;
                } else {
                    if (pieceAt(x + 1, y - 1) != null
                            && pieceAt(x + 2, y - 2) == null
                            && pieceAt(x + 1, y - 1).isFire() != pieceAt(x, y)
                                    .isFire())
                        return true;
                    if (pieceAt(x - 1, y - 1) != null
                            && pieceAt(x - 2, y - 2) == null
                            && pieceAt(x - 1, y - 1).isFire() != pieceAt(x, y)
                                    .isFire())
                        return true;
                }

            } else {
                if (pieceAt(x + 1, y + 1) != null
                        && pieceAt(x + 2, y + 2) == null
                        && pieceAt(x + 1, y + 1).isFire() != pieceAt(x, y)
                                .isFire())
                    return true;
                if (pieceAt(x - 1, y + 1) != null
                        && pieceAt(x - 2, y + 2) == null
                        && pieceAt(x - 1, y + 1).isFire() != pieceAt(x, y)
                                .isFire())
                    return true;

                if (pieceAt(x + 1, y - 1) != null
                        && pieceAt(x + 2, y - 2) == null
                        && pieceAt(x + 1, y - 1).isFire() != pieceAt(x, y)
                                .isFire())
                    return true;
                if (pieceAt(x - 1, y - 1) != null
                        && pieceAt(x - 2, y - 2) == null
                        && pieceAt(x - 1, y - 1).isFire() != pieceAt(x, y)
                                .isFire())
                    return true;

            }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {

        
        if (pieceAt(xf, yf) != null
                && pieceAt(xf, yf).isFire() == pieceAt(xi, yi).isFire()) {
            System.out.println("validMove from " + xi + " , " + yi + " to "
                    + xf + " , " + yf + "at 1st if");
            return false;
        }

        if (pieceAt(xi, yi).isKing()) {
            if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1)
                return true;

            if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2)
                if (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null
                        && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() != pieceAt(
                                xi, yi).isFire()) {

                    return true;
                }

        }
        if (pieceAt(xf, yf) != null)
            return false;
        if (!canChain(xi,yi) && pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 1
                && (yf - yi) == 1)
            return true;
        if (!canChain(xi,yi) && !pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 1
                && (yf - yi) == -1)
            return true;
        if (pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 2
                && (yf - yi) == 2)
            if (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null
                    && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() != pieceAt(
                            xi, yi).isFire()) {

                return true;
            }

        if (!pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 2
                && (yf - yi) == -2)
            if (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null
                    && pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire() != pieceAt(
                            xi, yi).isFire()) {

                return true;
            }

        return false;
    }

    public void select(int x, int y) {

        if (pieceAt(x, y) != null) {

            selectedX = x;
            selectedY = y;

            hasSelected = true;
        } else {
            Piece p = pieceAt(selectedX, selectedY);
            p.move(x, y);
            if (p.hasCaptured())
                hasCaptured = true;

        }

    }

    public boolean canEndTurn() {
        if (hasCaptured || hasMoved)
            return true;

        return false;

    }

    public void endTurn() {
        fireTurn = !fireTurn;
        hasMoved = false;
        hasSelected = false;
        System.out.println("at endTurn:  " + selectedX + " , " + selectedY);
        if (pieceAt(selectedX, selectedY) != null)
            pieceAt(selectedX, selectedY).doneCapturing();
        selectedX = -10;
        selectedY = -10;
        this.hasCaptured = false;

    }

    /**
     * Draws an N x N board. Adapted from:
     * http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (!pieces[i][j].isKing()) {
                        if (pieces[i][j].isFire()) {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-fire.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-fire.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-fire.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-water.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-water.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-water.png", 1, 1);
                            }
                        }

                    } else {
                        if (pieces[i][j].isFire()) {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-fire-crowned.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-fire-crowned.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-water-crowned.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-water-crowned.png", 1, 1);
                            }
                        }

                    }

                }
            }
        }

    }

    /**
     * Initialize the board configuration
     * 
     * @param N
     *            : board size
     */
    private void drawInit() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == selectedX && j == selectedY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);

                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    public String winner() {

        boolean fireExists = false;
        boolean waterExists = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieceAt(i, j).isFire())
                        fireExists = true;

                    if (!pieceAt(i, j).isFire())
                        waterExists = true;

                }
            }
        }

        if (fireExists && !waterExists)
            return "Fire";
        if (!fireExists && waterExists)
            return "Water";
        if (!fireExists && !waterExists)
            return "No one";

        return null;
    }

    public static void main(String[] args) {
        int N = 8;
        Board b = new Board(false);
        StdDrawPlus.setScale(0, N);
        b.drawInit();
        int x, y;
        while (true) {
            b.drawInit();
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                x = (int) StdDrawPlus.mouseX();
                y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {

                    System.out.println("in click = 0");
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                System.out.println("space pressed");
                if (b.canEndTurn()) {
                    System.out.println("turn changed");
                    b.endTurn();
                }
            }
            if (StdDrawPlus.isNPressed())
                b = new Board(false);

            StdDrawPlus.show(70);
        }
    }

}
