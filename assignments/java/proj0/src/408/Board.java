public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private boolean shouldBeEmpty;
    private int N = 8;
    private boolean fireTurn = true;
    private boolean moved = false;
    private int saveX = -1;
    private int saveY = -1;
    private Piece currPiece = null;
    private boolean outOfBounds = false;

    public Board(boolean empty) {
        shouldBeEmpty = empty;
        pieces = new Piece[N][N];
        if (shouldBeEmpty == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j == 0 && i % 2 == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    else if (j == 1 && i % 2 != 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    else if (j == 2 && i % 2 == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    else if (j == 5 && i % 2 != 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    else if (j == 6 && i % 2 == 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    else if (j == 7 &&  i % 2 != 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    }

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    if ((pieces[i][j] == currPiece) && (currPiece != null)) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece current = pieces[i][j];
                if (current != null) {
                    if (current.isKing()) {
                        if (current.isFire()) {
                            if (current.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (current.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else if (current.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1); 
                            }
                        else if (current.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                        else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                    }
                    else {
                        if (current.isFire()) {
                            if (current.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); 
                            }
                            else if (current.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                        else if (current.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1); 
                            }
                        else if (current.isShield()) {
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

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Piece[][] pieces = new Piece[N][N];
        Board board = new Board(Boolean.parseBoolean(args[0]));

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            board.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double xPos = StdDrawPlus.mouseX();
                double yPos = StdDrawPlus.mouseY();
                if (board.canSelect(((int) xPos), ((int) yPos))) {
                    board.select(((int) xPos), ((int) yPos));
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            StdDrawPlus.show(20);
        }
    }
    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }
        else if (pieces[x][y] != null) {
            return pieces[x][y];
        }
        else if (pieces[x][y] == null) {
            return null;
        }
        return null;
    }
    /* can still select another blank space (ONLY TO MAKE A CAPTURE) if recently captured and 
    there is a valid space for you to move from where you are after the capture.
    */
    public boolean canSelect(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <=7) {
            Piece current = pieces[x][y];
            if (moved == true) {
                if (current != null) {
                    return false;
                }
            }
            if (current != null) {
                if (fireTurn == true && current.isFire()) {
                    if ((current == null) || ((current != null) && (moved == false))) {
                        return true;
                    }
                    return false;
                }
                if (fireTurn == false && (!current.isFire())) {
                    if ((current == null) || ((current != null) && (moved == false))) {
                        return true;
                    }
                    return false;
                }
            }
            else if (current == null) {
                if ((currPiece != null) && (moved == false)) {
                    if (validMove(saveX, saveY, x, y)) {
                        return true;
                    }
                    return false;
                }
                // else if ((currPiece != null) && (currPiece.hasCaptured()) && (moved == true) && ((Math.abs(saveX - x) == 2))) { // multi-captures
                //     if (validMove(saveX, saveY, x, y)) {
                //         return true;
                //     }
                //     return false;
                // }
            }
            // System.out.println("---------------");
            // System.out.println("currPiece: " + saveX + " " + saveY);
            // System.out.println("current: " + x + " " + y);
            // System.out.println("moved: " + moved);
            return false;
        }
        return false;
    }

    public void select(int x, int y) {
        if (currPiece == null) {
            currPiece = pieces[x][y];
            saveX = x;
            saveY = y;
        }
        else if (currPiece != null) {
            if (moved == false) {
                if (pieces[x][y] != null) {
                    currPiece = pieces[x][y];
                    saveX = x;
                    saveY = y;
                }
                else {
                    currPiece.move(x, y);
                    moved = true;
                }
            }
        }
    }
// even though selecting empty space on the board, code checks all around first before actually moving
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece current = pieces[xi][yi];
        if (0 <= xf && xf <= 7 && 0 <= yf && yf <= 7) { // checks if (xf, yf) is in bounds
            if (pieces[xf][yf] == null) { // if space where we want to move is not already occupied
                if (current.isKing()) {
                    if ((xf == (xi + 1) && yf == (yi + 1)) || // not trying to capture but is legal move, doesn't matter which type of piece
                        (xf == (xi + 1) && yf == (yi - 1)) ||
                        (xf == (xi - 1) && yf == (yi + 1)) ||
                        (xf == (xi - 1) && yf == (yi - 1))) {
                        return true;
                    }
                    if (current.isFire()) {
                        if (((xi + 1) < 8) && ((yi + 1) < 8)) { // stay in bounds when checking if piece there is null
                            if ((pieces[xi + 1][yi + 1] != null) && (!pieces[xi + 1][yi + 1].isFire()) && (xf == xi + 2) && (yf == yi + 2)) {
                                return true;
                            }
                        }
                        if (((xi + 1) < 8) && ((yi - 1) > -1)) {
                            if ((pieces[xi + 1][yi - 1] != null) && (!pieces[xi + 1][yi - 1].isFire()) && (xf == xi + 2) && (yf == yi - 2)) {
                                return true;
                            }
                        }
                        if (((xi - 1) > -1) && ((yi + 1) < 8)) {
                            if ((pieces[xi - 1][yi + 1] != null) && (!pieces[xi - 1][yi + 1].isFire()) && (xf == xi - 2) && (yf == yi + 2)) {
                                return true;
                            }
                        }
                        if (((xi - 1) > -1) && ((yi - 1) > -1)) {
                            if ((pieces[xi - 1][yi - 1] != null) && (!pieces[xi - 1][yi - 1].isFire()) && (xf == xi - 2) && (yf == yi - 2)) {
                                return true;
                            }
                        }
                    }

                    else if (!current.isFire()) { // legal capture of fire piece by water piece
                        if (((xi + 1) < 8) && ((yi + 1) < 8)) { // stay in bounds when checking if piece there is null
                            if ((pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi + 1].isFire()) && (xf == xi + 2) && (yf == yi + 2)) {
                                return true;
                            }
                        }
                        if (((xi + 1) < 8) && ((yi - 1) > -1)) {
                            if ((pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi - 1].isFire()) && (xf == xi + 2) && (yf == yi - 2)) {
                                return true;
                            }
                        }
                        if (((xi - 1) > -1) && ((yi + 1) < 8)) {
                            if ((pieces[xi - 1][yi + 1] != null) && (pieces[xi - 1][yi + 1].isFire()) && (xf == xi - 2) && (yf == yi + 2)) {
                                return true;
                            }
                        }
                        if (((xi - 1) > -1) && ((yi - 1) > -1)) {
                            if ((pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi - 1].isFire()) && (xf == xi - 2) && (yf == yi - 2)) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                else if (!current.isKing()) {
                    if (current.isFire()) {
                        if (((xf == xi + 1) && (yf == yi + 1)) || ((xf == xi - 1) && (yf == yi + 1))) {
                            return true;
                        }
                        if (((xi + 1) < 8) && ((yi + 1) < 8)) { // stay in bounds when checking if piece there is null
                            if ((pieces[xi + 1][yi + 1] != null) && (!pieces[xi + 1][yi + 1].isFire()) && (xf == xi + 2) && (yf == yi + 2)) {
                                return true;
                            }
                        }
                        if (((xi - 1) > -1) && ((yi + 1) < 8)) {
                            if ((pieces[xi - 1][yi + 1] != null) && (!pieces[xi - 1][yi + 1].isFire()) && (xf == xi - 2) && (yf == yi + 2)) {
                                return true;
                            }
                        }
                    } 
                    else {
                        if (((xf == (xi + 1)) && (yf == (yi - 1))) || ((xf == xi - 1) && (yf == yi - 1))) {
                            return true;
                        }
                        if (((xi + 1) < 8) && ((yi - 1) > -1)) {
                            if ((pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi - 1].isFire()) && (xf == xi + 2) && (yf == yi - 2)) {
                                return true;
                            }
                        }
                        if (((xi - 1) > -1) && ((yi - 1) > -1)) {
                            if ((pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi - 1].isFire()) && (xf == xi - 2) && (yf == yi - 2)) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    public void place(Piece p, int x, int y) {
        if (x >= 0 && y >= 0 && x <= 7 && y <=7 && p != null) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x > 7 || x < 0) {
            System.out.println("Index x out of bounds.");
            return null;
        }
        if (y > 7 || y < 0) {
            System.out.println("Index y out of bounds.");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("No piece to remove.");
            return null;
        }
        else {
            Piece save = pieces[x][y];
            pieces[x][y] = null;
            return save;
        }
    }

    public boolean canEndTurn() {
        if (moved == true || ((currPiece != null) && currPiece.hasCaptured())) {
            return true;
        }
        return false;
    }
    public void endTurn() {
        if (!fireTurn) {
            fireTurn = true;
        }
        else if (fireTurn) {
            fireTurn = false;
        }
        String result = winner();
        if (result != null) {
            return;
        }
        moved = false;
        currPiece.doneCapturing();
        currPiece = null;
        saveX = -1;
        saveY = -1;
        outOfBounds = false;
    }

    public String winner() {
        int firePieces = 0;
        int waterPieces = 0;
        for (Piece[] arrows : pieces) {
            for (Piece item : arrows) {
                if (item != null) {
                    if (item.isFire()) {
                        firePieces += 1;
                    }
                    else {
                        waterPieces += 1;
                    }
                }
            }
        }
        if ((waterPieces == 0) && (firePieces == 0)) {
            return "No one";
        }
        else if ((waterPieces == 0) && (firePieces > 0)) {
            return "Fire";
        }
        else if ((firePieces == 0) && (waterPieces > 0)) {
            return "Water";
        }
        else {
            return null;
        }
    }
}