import java.awt.Color;

public class Board {
    private final static int Fire = 0;
    private final static int Water = 1;
    private final static String Pawn = "pawn";
    private final static String Bomb = "bomb";
    private final static String Shield = "shield";
    private static final int N = 8;

    private Piece[][] pieces;
    private int currentPlayer;
    private Piece selectedPiece;
    private int selectedX;
    private int selectedY;
    private boolean hasMoved;
    private boolean bombCaptured;


    // Starts a GUI supported version of the game.
    public static void main(String[] args) {
        Board b = new Board(false);
        b.runGUI();
    }

    // The constructor for the Board. If shouldBeEmpty is true,
    // initializes an empty Board. Otherwise intializes a Board
    // with the default configuration. Note that an empty Board
    // could be useful for testing purposes
    public Board(boolean shouldBeEmpty) {
        this.pieces = new Piece[Board.N][Board.N];
        this.currentPlayer = Board.Fire;
        if (!shouldBeEmpty) this.placeInitialPieces();
    }

    // GUI Private {{{

    private void placeInitialPieces() {
        place(new Piece(true, this, 0, 0, Pawn), 0, 0);
        place(new Piece(true, this, 2, 0, Pawn), 2, 0);
        place(new Piece(true, this, 4, 0, Pawn), 4, 0);
        place(new Piece(true, this, 6, 0, Pawn), 6, 0);

        place(new Piece(true, this, 1, 1, Shield), 1, 1);
        place(new Piece(true, this, 3, 1, Shield), 3, 1);
        place(new Piece(true, this, 5, 1, Shield), 5, 1);
        place(new Piece(true, this, 7, 1, Shield), 7, 1);

        place(new Piece(true, this, 0, 2, Bomb), 0, 2);
        place(new Piece(true, this, 2, 2, Bomb), 2, 2);
        place(new Piece(true, this, 4, 2, Bomb), 4, 2);
        place(new Piece(true, this, 6, 2, Bomb), 6, 2);

        place(new Piece(false, this, 1, 7, Pawn), 1, 7);
        place(new Piece(false, this, 3, 7, Pawn), 3, 7);
        place(new Piece(false, this, 5, 7, Pawn), 5, 7);
        place(new Piece(false, this, 7, 7, Pawn), 7, 7);

        place(new Piece(false, this, 0, 6, Shield), 0, 6);
        place(new Piece(false, this, 2, 6, Shield), 2, 6);
        place(new Piece(false, this, 4, 6, Shield), 4, 6);
        place(new Piece(false, this, 6, 6, Shield), 6, 6);

        place(new Piece(false, this, 1, 5, Bomb), 1, 5);
        place(new Piece(false, this, 3, 5, Bomb), 3, 5);
        place(new Piece(false, this, 5, 5, Bomb), 5, 5);
        place(new Piece(false, this, 7, 5, Bomb), 7, 5);
    }

    private void runGUI() {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            StdDrawPlus.clear();
            drawBoard();
            if (selectedPiece != null) drawSquare(selectedX, selectedY, StdDrawPlus.WHITE);
            drawPieces();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                mouseDown((int)x, (int)y);
                // emit event
            }
            if (StdDrawPlus.isSpacePressed()) {
                spaceDown();
            }
            StdDrawPlus.show(100);
            if (winner() != null) {
                System.out.println("Winner: " + winner());
                break;
            }
        }
    }

    private void mouseDown(int x, int y) {
        // System.out.println("Mouse Down (" + x + ", " + y + ")");
        if (canSelect(x, y)) {
            // System.out.println("Can select true");
            select(x, y);
        }
    }

    private void spaceDown() {
        // System.out.println("Space down");
        if (canEndTurn()) endTurn();
    }

    private void drawBoard() {
        for (int i = 0; i < Board.N; i++) {
            for (int j = 0; j < Board.N; j++) {
                Color c;
                if ((i+j) % 2 == 0)
                    c = StdDrawPlus.GRAY;
                else
                    c = StdDrawPlus.RED;

                drawSquare(i, j, c);
            }
        }
    }

    private void drawSquare(int x, int y, Color c) {
        StdDrawPlus.setPenColor(c);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }

    private void drawPieces() {
        for (int i = 0; i < Board.N; i++) {
            for (int j = 0; j < Board.N; j++) {
                Piece p = pieces[i][j];
                if (p != null)
                    StdDrawPlus.picture(i + .5, j + .5, imagePathFor(p), 1, 1);
            }
        }
    }

    private String imagePathFor(Piece p) {
        StringBuilder img = new StringBuilder();

        img.append("img/");

        if (p.isBomb()) {
            img.append("bomb");
        } else if (p.isShield()) {
            img.append("shield");
        } else {
            img.append("pawn");
        }
        img.append("-");

        if (p.isFire())
            img.append("fire");
        else
            img.append("water");

        if (p.isKing())
            img.append("-crowned");

        img.append(".png");

        return img.toString();
    }

    // GUI Private }}}

    // Public Spec API {{{

    // Gets the piece at position (x, y) on the board, or null
    // if there is no piece. if (x,y) are out of bounds, returns
    // null
    public Piece pieceAt(int x, int y) {
        if (validCoords(x, y)) return pieces[x][y];
        else                   return null;
    }


    // Returns true if the square at (x, y) can be selected.
    public boolean canSelect(int x, int y)  {
        if (!validCoords(x, y)) return false;

        // this is the weird orphan case
        if (bombCaptured)
            return false; // don't even bother checking

        Piece p = pieceAt(x, y);

        // modeled after spec for clarity
        if (p != null && p.side() == currentPlayer) { // meaning we clicked on a piece
            if (selectedPiece == null)
                return true;

            if (selectedPiece != null && hasMoved == false)
                return true;
        // meaning we clicked on a blank space and have a piece ready to move
        } else if (selectedPiece != null && validMove(selectedX, selectedY, x, y)) {
            if (hasMoved == false)
                return true;

            if (hasMoved == true && selectedPiece.hasCaptured())
                return true;
        }

        return false;
    }

    // got axed from the spec
    private boolean validMove(int ix, int iy, int fx, int fy) {
        // So there's a piece on x or y, can't move on top of it
        if (pieceAt(fx, fy) != null) return false;

        Piece p = this.pieceAt(ix, iy);

        int ddy = fy - iy;
        int ddx = fx - ix;
        int dx = Math.abs(ddx);
        int dy = Math.abs(ddy);

        if (dy > 2 || dx > 2) return false; // can never move more than two
        if (dy != dx) return false; // can never move lopsided

        if (!p.isKing())  {
            if (p.isFire() && ddy < 0) // fire tryna move down, can't if not crowned
                 return false;
            else if (!p.isFire() && ddy > 0) // water trying to move up, can't if not crowned
                return false;
        }

        // capture!!
        if (dx == 2) {
            ddx = ddx / 2;
            ddy = ddy / 2;

            int capx = ix + ddx;
            int capy = iy + ddy;
            Piece potentialCapture = this.pieceAt(capx, capy);

            if (potentialCapture == null) return false; // can't double jump less capture
            if (potentialCapture.side() == p.side()) return false; // can't jump a teamate
        }

        if (p.hasCaptured() && dx != 2) return false; // can't move regulary after capture
        if (dx == 0) return false; // can move to the same square

        return true;
    }


    // Selects the piece at (x,y) if possible. Optionaly, it is
    // recommende to color the background of the selected square
    // white on the GUI viea the pen color function. For any piece
    // to perform a capture, that piece must have been selected first
    public void select(int x, int y) {
        if (!validCoords(x, y)) return;

        Piece pieceToSelect = pieceAt(x, y);

        if (selectedPiece == null || (selectedPiece != null && pieceToSelect != null)) {
            this.selectedPiece = pieceToSelect;
        } else {
            if (selectedPiece == pieceAt(x, y)) // don't move a piece to it's same location
                return;

            Piece p = selectedPiece; // we might lose the selectedPiece with remove
            p.move(x, y);
            hasMoved = true;
            if (p.isBomb() && p.hasCaptured())
                bombCaptured = true;
        }

        this.selectedX = x;
        this.selectedY = y;
    }

    // Places p at (x,y). If (x, y) is out of bounds or if pi is
    // null, does nothing. If p already exists in the current Board
    // first removes it from its inital position. If another piece
    // already exists at (x,) - p will replace that piece.
    public void place(Piece p, int x, int y) {
        if (!validCoords(x, y)) return;

        int[] coords = coords(p);
        int pX = coords[0];
        int pY = coords[1];
        if (pX != -1 && pY != -1) { // we want to move p
            Piece preservedSelectedPiece = selectedPiece; // don't want to lose selectedPiece
            remove(pX, pY);
            if (p == preservedSelectedPiece)
                selectedPiece = p;
        }

        this.pieces[x][y] = p;
    }

    private int[] coords(Piece p) {
        int[] x = {-1, -1};
        for (int i = 0; i < Board.N; i++) {
            for (int j = 0; j < Board.N; j++) {
                if (pieceAt(i, j) == p) {
                    x[0] = i;
                    x[1] = j;
                    return x;
                }
            }
        }

        return x;
    }

    // Executes a remove. Returns thie piece that was removed. If
    // the input (x, y) is out of bounds, returns null and prints
    // an appropriate message. If there is no piece at (x,y) returns
    // null and prints an paprorotate message
    public Piece remove(int x, int y) {
        if (!validCoords(x, y)) {
            System.out.printf("Tried to remove a piece but (%d,%d) is out of bounds", x, y);
            return null;
        }

        Piece pop = this.pieces[x][y];
        this.pieces[x][y] = null;

        if (pop == null)
            System.out.printf("Tried to remove a piece but no piece there");

        if (pop == selectedPiece)
            selectedPiece = null;

        return pop;
    }

    // Returns whether or not the current player can end theri turn.
    // To be able to end their turn, a piece must have moved or performed
    // a capture
    public boolean canEndTurn() {
        return hasMoved;
    }

    // Called at the end of each turn. Handles switching of plaers
    // and anything else that should happen at the end of a turn
    public void endTurn() {
        if (canEndTurn())
            this.switchPlayers();
    }

    // Returns the winner of the game. "Fire," "Water" or "No one"
    // (tie / no pieces on the board), or null if the game is not yet
    // over. Assume there is no staleate situation in which the current
    // player has pieces but can not legaly move them (In this even, ust
    // leave it at null. Determin the winner solly by the numebr of pieces
    // belonging to each team.
    public String winner() {
        int fires = countPieces(Fire);
        int waters = countPieces(Water);

        if (waters > 0 && fires > 0) return null;

        if (fires > waters)
            return "Fire";
        else if (waters > fires)
            return "Water";
        else
            return "No one";
    }

    // Public Spec API }}}

    // Private Helpers {{{

    private boolean validCoords(int x, int y) {
        return !outOfRange(x) && !outOfRange(y);
    }

    private boolean outOfRange(int n) {
        return (n > 7) || (n < 0);
    }


    private void switchPlayers() {
        if (this.currentPlayer == Board.Fire)
            this.currentPlayer = Board.Water;
        else
            this.currentPlayer = Board.Fire;

        resetTurnManagement();
    }

    private void resetTurnManagement() {
        if (this.selectedPiece != null) this.selectedPiece.doneCapturing();
        selectedPiece = null;
        hasMoved = false;
        bombCaptured = false;
    }

    private int countPieces(int side) {
        int total = 0;
        for (Piece[] col : pieces) {
            for (Piece p : col) {
                if ( p != null) {
                    if (p.side() == side)
                        total++;
                }
            }
        }

        return total;
    }

    // Private Helpers }}}

}
