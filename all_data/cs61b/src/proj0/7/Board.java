public class Board {
    private static final int N = 8;
    private final Piece[][] pieces;
    private int currPlayer;
    private Piece pieceSelected;
    private Point pieceSelectedPoint;
    private boolean pieceMoved;

    private static Board board;
    private static int highlightX;
    private static int highlightY;

    public Board(boolean shouldBeEmpty) {
        pieces = shouldBeEmpty ? new Piece[N][N] : initBoard();
        currPlayer = 0;
        pieceMoved = false;
    }

    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x, y)) {
            return null;
        }

        return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (p == null || outOfBounds(x, y)) {
            return;
        }

        Point point = findPiece(p);
        if (point != null) {
            remove(point.x, point.y);
        }

        pieces[x][y] = p;
        p.move(x, y);
    }

    public boolean canSelect(int x, int y) {
        if (outOfBounds(x, y)) {
            return false;
        }

        Piece piece = pieces[x][y];

        // Square with piece chosen
        if (piece != null) {
            return (pieceSelected == null || !pieceMoved)
                && piece.side() == currPlayer;
        }

        // Empty square; piece must be selected
        if (pieceSelected == null) {
            return false;
        }

        // Piece can be moved exactly once
        if (!pieceMoved && validMove(pieceSelectedPoint.x, pieceSelectedPoint.y, x, y)) {
            return true;
        }

        // Captures must be followed by more captures, and cannot be a bomb
        if (validCapture(pieceSelectedPoint.x, pieceSelectedPoint.y, x, y)
                && (!pieceMoved || pieceSelected.hasCaptured() && !pieceSelected.isBomb())) {
            return true;
        }

        // Otherwise, we cannot select the square
        return false;
    }

    public void select(int x, int y) {
        Piece piece = pieces[x][y];
        pieceSelectedPoint = new Point(x, y);

        // Select a new piece
        if (piece != null) {
            pieceSelected = piece;
            return;
        }

        // Move an already-selected piece
        place(pieceSelected, x, y);
        pieceMoved = true;
    }

    public Piece remove(int x, int y) {
        if (outOfBounds(x, y)) {
            System.out.println("Square (" + x + ", " + y + ") is out of bounds.");
            return null;
        }

        Piece piece = pieces[x][y];
        if (piece == null) {
            System.out.println("No piece could be found at (" + x + ", " + y + ").");
            return null;
        }

        pieces[x][y] = null;
        return piece;
    }

    public boolean canEndTurn() {
        return pieceMoved;
    }

    public void endTurn() {
        if (pieceSelected != null) {
            pieceSelected.doneCapturing();
        }
        pieceSelected = null;
        pieceSelectedPoint = null;
        pieceMoved = false;
        currPlayer = switchPlayers(currPlayer);
    }

    public String winner() {
        boolean hasFirePieces = false;
        boolean hasWaterPieces = false;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Piece piece = pieces[i][j];

                if (piece == null) {
                    continue;
                }

                if (piece.isFire()) {
                    hasFirePieces = true;
                } else {
                    hasWaterPieces = true;
                }

                if (hasFirePieces && hasWaterPieces) {
                    return null;
                }
            }
        }

        if (hasFirePieces) {
            return "Fire";
        }

        if (hasWaterPieces) {
            return "Water";
        }

        return "No one";
    }

    private Piece[][] initBoard() {
        Piece[][] pieces = new Piece[N][N];

        // Pieces on odd columns
        for (int i = 0; i < N; i += 2) {
            pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            pieces[i][6] = new Piece(false, this, i, 6, "shield");
        }

        // Pieces on even columns
        for (int i = 1; i < N; i += 2) {
            pieces[i][1] = new Piece(true, this, i, 1, "shield");
            pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            pieces[i][7] = new Piece(false, this, i, 7, "pawn");
        }

        return pieces;
    }

    private Point findPiece(Piece piece) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (pieces[i][j] == piece) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (outOfBounds(xi, yi) || outOfBounds(xf, yf)) {
            return false;
        }

        Piece piece = pieces[xi][yi];
        if (piece == null) {
            return false;
        }

        int dx = xf - xi;
        int dy = yf - yi;

        // Non-king pieces cannot move backwards
        if (!piece.isKing()) {
            if (piece.isFire() && dy < 0 || !piece.isFire() && dy > 0) {
                return false;
            }
        }

        // No pieces can be located on the final square
        Piece finalPiece = pieces[xf][yf];
        if (finalPiece != null) {
            return false;
        }

        // The move must be valid
        return Math.abs(dx) == 1 && Math.abs(dy) == 1;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (outOfBounds(xi, yi) || outOfBounds(xf, yf)) {
            return false;
        }

        Piece piece = pieces[xi][yi];
        if (piece == null) {
            return false;
        }

        int dx = xf - xi;
        int dy = yf - yi;

        // Non-king pieces cannot capture backwards
        if (!piece.isKing()) {
            if (piece.isFire() && dy < 0 || !piece.isFire() && dy > 0) {
                return false;
            }
        }

        // No pieces can be located on the final square
        Piece finalPiece = pieces[xf][yf];
        if (finalPiece != null) {
            return false;
        }

        // A piece must be captured
        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            Piece capturedPiece = pieces[xi + dx/2][yi + dy/2];
            return capturedPiece != null
                && pieceSelected.isFire() != capturedPiece.isFire();
        }

        return false;
    }

    public static void main(String[] args) {
        board = new Board(false);

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        highlightX = -1;
        highlightY = -1;

        listenForChanges();
    }

    private static void listenForChanges() {
        while (true) {
            drawBoard();

            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();

                if (board.canSelect(x, y)) {
                    board.select(x, y);
                    highlight(x, y);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                    removeHighlight();

                    String winner = board.winner();
                    if (winner != null) {
                        System.out.println("Winner: " + winner + ".");
                        System.exit(0);
                        return;
                    }
                }
            }

            if (StdDrawPlus.isNPressed()) {
                removeHighlight();
                board = new Board(false);
            }
        }
    }

    private static void highlight(int x, int y) {
        highlightX = x;
        highlightY = y;
    }

    private static void removeHighlight() {
        highlightX = -1;
        highlightY = -1;
    }

    private static void drawBoard() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                // Draw squares
                if (i == highlightX && j == highlightY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                // Draw pieces
                Piece piece = board.pieceAt(i, j);
                if (piece != null) {
                    String img = getPieceImg(piece);
                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }

        StdDrawPlus.show(100);
    }

    private static String getPieceImg(Piece piece) {
        String img = "img/";
        if (piece.isBomb()) {
            img += "bomb-";
        } else if (piece.isShield()) {
            img += "shield-";
        } else {
            img += "pawn-";
        }

        img += piece.isFire() ? "fire" : "water";
        img += piece.isKing() ? "-crowned" : "";
        img += ".png";

        return img;
    }

    private static boolean outOfBounds(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    private static int switchPlayers(int player) {
        return 1 - player;
    }

    private static final class Point {
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
