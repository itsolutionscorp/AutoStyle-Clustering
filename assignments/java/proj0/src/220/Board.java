public class Board {
    private int width;
    private static Piece[][] pieces;

    // Keywords for check piece types
    private static final String fire = "fire";
    private static final String water = "water";

    private static final String pawn = "pawn";
    private static final String bomb = "bomb";
    private static final String shield = "shield";
    
    private boolean player1;
    private boolean selectedPiece;
    private boolean movedPiece;
    private boolean capturedPiece;

    private static int xi = -1;
    private static int yi = -1;

    public static void main(String[] args) {
        int width = 8;
        StdDrawPlus.setXscale(0, width);
        StdDrawPlus.setYscale(0, width);
        Board b = new Board(false);
        String winner = null;
        while (winner == null) {
            drawBoard(width);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            } else if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                    winner = b.winner();
                    if (winner != null) {
                        System.out.println(winner);
                    }
                }
                
            }
            StdDrawPlus.show(25);
        }
    }
    
    public Board(boolean shouldBeEmpty) {
        width = 8;
        pieces = new Piece[width][width];
        player1 = true;
        
        if (!shouldBeEmpty) {
            String s;
            for (int y = 0; y < width; ++y) {
                for (int x = 0; x < width; ++x) {
                    if ((x + y) % 2 == 0 && (y < 3 || y > width - 4)) {
                        if (y == 1 || y == width - 2) {
                            s = shield;
                        } else if (y == 0 || y == width - 1) {
                            s = pawn;
                        } else {
                            s = bomb;
                        }
                        pieces[x][y] = new Piece(y < 3, this, x, y, s);
                    }
                }
            }
        }
        
    }
    
    private static boolean checkBounds(int x, int y) {
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    private static void drawBoard(int width) {
        double xCenter;
        double yCenter;
        Piece piece;
        String image;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < width; ++y) {
                drawSquare(x, y, x == xi && y == yi);
            }
        }
    }
    
    private static void drawSquare(int x, int y, boolean selected) {
        if (!checkBounds(x, y)) {
            return;
        }
        Piece piece = pieces[x][y];
        double xCenter = 0.5 + (double) x;
        double yCenter = 0.5 + (double) y;
        if (selected)
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        else
            StdDrawPlus.setPenColor(((x + y) % 2 == 0) ? StdDrawPlus.GRAY : StdDrawPlus.RED);

        StdDrawPlus.filledSquare(xCenter, yCenter, 0.5);
        if (piece != null) {
            StdDrawPlus.picture(xCenter, yCenter, nameGen(piece), 1.0, 1.0); 
        }
    }

    private static String nameGen(Piece piece) {
        String image = "img/";
        if (piece.isBomb()) {
            image += bomb;
        } else if (piece.isShield()) {
            image += shield;
        } else {
            image += pawn;
        }
        image += "-";
        image += piece.isFire() ? fire : water;
        image += piece.isKing() ? "-crowned" : "";
        image += ".png";
        return image;
    }

    public Piece pieceAt(int x, int y) {
        if (!checkBounds(x, y)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x, y);
        if (piece == null && ((selectedPiece && !movedPiece) || capturedPiece)) {
            // selecting empty square to move to for a piece that has captured or has not moved
            return validMove(x, y);
        } else if (piece == null || piece.isFire() != player1) {
            // empty square but already moved or the piece is the wrong color
            return false;
        } else {
            // Select another piece if selected square is not empty
            return !movedPiece;
        }
    }

    private boolean validMove(int xf, int yf) {
        // checks for bounds
        if (!checkBounds(xf, yf) || !checkBounds(xi, yi))
            return false;
        else if (pieceAt(xf, yf) != null || pieceAt(xi, yi) == null)
            return false;

        Piece piece = pieces[xi][yi];
        int dx = xf - xi;
        int dy = yf - yi;

        // Flips dy for water pieces
        if (!piece.isFire())
            dy = -dy;

        // Checks for single moves
        if (!movedPiece && (dx == 1 || dx == -1) && (dy == 1 || (piece.isKing() && dy == -1)))  {
            return true;
        // Checks for jump captures
        } else if ((dx == 2 || dx == -2) && (dy == 2 || (piece.isKing() && dy == -2))) {
            Piece middlePiece = pieces[(xi + xf) / 2][(yi + yf) / 2];
            if (middlePiece != null)
                return middlePiece.isFire() != piece.isFire();
        }
        return false;
    }

    public void select(int x, int y) {
        Piece piece = pieceAt(xi, yi);
        if (pieceAt(x, y) == null && piece != null) {
            piece.move(x, y);
            movedPiece = true;
        }
        // remembers last coordinates
        xi = x;
        yi = y;
        selectedPiece = true;
    }

    public void place(Piece p, int x, int y) {
        if (p != null && checkBounds(x, y)) {
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (pieces[i][j] == p) {
                        pieces[i][j] = null;
                    }
                }
            }
            pieces[x][y] = p;

            // checks for captures or not
            capturedPiece = capturedPiece || p.hasCaptured();
        }
    }

    public Piece remove(int x, int y) {
        if (!checkBounds(x, y)) {
            System.out.println("Cannot remove out of bounds");
            return null;
        }
        Piece piece = pieces[x][y];
        if (piece == null) {
            System.out.println("No piece in square, cannot remove");
        } else {
            pieces[x][y] = null;
        }
        return piece;
    }

    public boolean canEndTurn() {
        return movedPiece || capturedPiece;
    }

    public void endTurn() {
        Piece piece = pieceAt(xi, yi);
        if (piece != null) {
            piece.doneCapturing();
        }
        xi = -1;
        yi = -1;
        player1 = !player1;
        selectedPiece = false;
        movedPiece = false;
        capturedPiece = false;
    }

    public String winner() {
        int fireCount = 0;
        int waterCount = 0;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < width; ++j) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        ++fireCount;
                    } else {
                        ++waterCount;
                    }
                }
            }
        }
        if (fireCount == 0 && waterCount == 0) {
            return "No one";
        } else if (fireCount == 0) {
            return "Water";
        } else if (waterCount == 0) {
            return "Fire";
        } else {
            return null;
        }
    }
}
