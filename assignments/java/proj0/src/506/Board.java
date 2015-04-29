public class Board {

    private static int BOARD_SIZE = 8;
    private static boolean FIRE_PLAYER = false;
    private static boolean WATER_PLAYER = true;

    private boolean shouldBeEmpty;
    private Piece[][] pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
    private boolean currentPlayer = FIRE_PLAYER;

    private Piece selectedPiece;
    private boolean pieceMoved = false;
    private int selectedX = -1;
    private int selectedY = -1;

    // API

    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;

        if (!shouldBeEmpty) {
            setupPieces();
        }
    }

    public Piece pieceAt(int x, int y) {
        if (isWithinBounds(x,y)) {
            return pieces[x][y];
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        if (!isWithinBounds(x,y)) {
            return false;
        } else {
            Piece currentPiece = pieces[x][y];
            if (currentPiece != null &&
                (currentPlayer == FIRE_PLAYER && currentPiece.isFire() ||
                 currentPlayer == WATER_PLAYER && !currentPiece.isFire()
                 )
                ) {
                if (selectedPiece == null ||
                    selectedPiece != null && !pieceMoved
                    ) {
                    return true;
                }
            } else if (currentPiece == null) {
                if (this.selectedPiece != null && validMove(x,y)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public void select(int x, int y) {
        Piece currentPiece = pieces[x][y];
        selectedX = x;
        selectedY = y;
        if (this.selectedPiece != null && currentPiece == null) {
            this.selectedPiece.move(x,y);
            pieceMoved = true;
        } else if (currentPiece != null) {
            this.selectedPiece = currentPiece;
        }
    }

    public void place(Piece p, int x, int y) {
        if (isWithinBounds(x,y)) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (isWithinBounds(x,y)) {
            Piece piece = pieces[x][y];
            if (piece == null) {
                System.out.println("No piece exists at (" + x + ", " + y + ")");
                return null;
            } else {
                pieces[x][y] = null;
                return piece;
            }
        } else {
            System.out.println("Position out of bounds");
            return null;
        }
    }

    public boolean canEndTurn() {
        return pieceMoved;
    }

    public void endTurn() {
        currentPlayer = !currentPlayer;
        if (selectedPiece != null) {
            if (selectedPiece.hasCaptured()) {
                selectedPiece.doneCapturing();
            }
        }
        selectedPiece = null;
        selectedX = -1;
        selectedY = -1;
        pieceMoved = false;
    }

    public String winner() {
        int fireCount = 0;
        int waterCount = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece p = pieces[i][j];
                if (p != null) {
                    if (p.isFire()) {
                        fireCount += 1;
                    }
                    else {
                        waterCount += 1;
                    }
                }
            }
        }
        if (waterCount == 0 && fireCount > 0) {
            return "Fire";
        }
        if (fireCount == 0 && waterCount > 0) {
            return "Water";
        }
        if (fireCount == 0 && waterCount == 0) {
            return "No one";
        }
        return null;
    }

    // HELPER METHODS

    private boolean validMove(int x, int y) {
    // Assume bounds are valid and selectedPiece exists

        // Make sure the pieces are going in the correct definitions
        if (y - selectedY > 0 && !selectedPiece.isFire() && !selectedPiece.isKing() ||
            y - selectedY < 0 && selectedPiece.isFire() && !selectedPiece.isKing()
            ) {
            return false;
        }
        int xDiff = Math.abs(selectedX - x);
        int yDiff = Math.abs(selectedY - y);
        if (xDiff ==  yDiff) {
            if (xDiff == 1 && !selectedPiece.hasCaptured() && !pieceMoved) {
                return true;
            }
            Piece otherPiece = pieces[(selectedX + x)/2][(selectedY + y)/2];
            if (xDiff == 2 && otherPiece!= null && selectedPiece.isFire() != otherPiece.isFire()) {
                return true;
            }
            return false;
        }
        return false;
    }

    // PIECES METHODS

    private Piece[][] getPieces() {
        return this.pieces;
    }

    private void setupPieces() {

        // DRAW FIRE PAWNS
        for (int i = 0; i < BOARD_SIZE; i += 2) {
            Piece newPiece = new Piece(true, this, i, 0, "pawn");
            pieces[i][0] = newPiece;
            drawPiece(newPiece, i, 0);
        }

        // DRAW FIRE SHIELDS
        for (int i = 1; i < BOARD_SIZE; i += 2) {
            Piece newPiece = new Piece(true, this, i, 1, "shield");
            pieces[i][1] = newPiece;
            drawPiece(newPiece, i, 1);
        }

        // DRAW FIRE BOMBS
        for (int i = 0; i < BOARD_SIZE; i += 2) {
            Piece newPiece = new Piece(true, this, i, 2, "bomb");
            pieces[i][2] = newPiece;
            drawPiece(newPiece, i, 2);
        }

        // DRAW WATER PAWNS
        for (int i = 1; i < BOARD_SIZE; i += 2) {
            int y = BOARD_SIZE - 1;
            Piece newPiece = new Piece(false, this, i, y, "pawn");
            pieces[i][y] = newPiece;
            drawPiece(newPiece, i, y);
        }

        // DRAW WATER SHIELDS
        for (int i = 0; i < BOARD_SIZE; i += 2) {
            int y = BOARD_SIZE - 2;
            Piece newPiece = new Piece(false, this, i, y, "shield");
            pieces[i][y] = newPiece;
            drawPiece(newPiece, i, y);
        }

        // DRAW WATER BOMBS
        for (int i = 1; i < BOARD_SIZE; i += 2) {
            int y = BOARD_SIZE - 3;
            Piece newPiece = new Piece(false, this, i, y, "bomb");
            pieces[i][y] = newPiece;
            drawPiece(newPiece, i, y);
        }
    }

    // BOUND CHECK

    private static boolean isWithinBounds(int x, int y) {
        return 0 <= x && x <= BOARD_SIZE - 1 && 0 <= y && y <= BOARD_SIZE - 1;
    }

    // DRAWING METHODS

    private static void drawPiece(Piece piece, int x, int y) {
        String type = "pawn";
        if (piece.isBomb()) { type = "bomb"; }
        if (piece.isShield()) { type = "shield"; }

        String side = "fire";
        if (!piece.isFire()) { side = "water"; }

        String king = "";
        if (piece.isKing()) { king = "-crowned"; }

        StdDrawPlus.picture(x + 0.5, y + 0.5, "img/" + type + "-" + side + king + ".png", 1, 1);
    }

    private static void drawBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private static void drawAllPieces(Board b) {
        Piece[][] pieces = b.pieces;

        // Fill in Rows (y-positions)
        for (int i = 0; i < pieces.length; i++) {

            // Fill in Columns (x-positions)
            for (int j = 0; j < pieces[i].length; j++) {
                Piece currentPiece = pieces[i][j];
                if (currentPiece != null) {
                    drawPiece(currentPiece, i, j);
                }
            }
        }
    }
    private static void drawWhite(int x, int y) {
        if (isWithinBounds(x,y)) {
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        }
    }

    // SETUP METHODS

    private static void setScale(int scale) {
        StdDrawPlus.setXscale(0, scale);
        StdDrawPlus.setYscale(0, scale);
    }

    private static void pause(int t) {
        StdDrawPlus.show(t);
    }

    public static void main(String[] args) {
        setScale(BOARD_SIZE);
        Board board = new Board(false);

        while(board.winner() == null) {
            drawBoard();
            drawWhite(board.selectedX, board.selectedY);
            drawAllPieces(board);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x,y)) {
                    board.select(x,y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            pause(10);
        }
    }
}