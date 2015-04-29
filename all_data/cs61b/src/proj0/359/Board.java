public class Board {

    private static final int SIZE = 8;

    private Piece[][] pieces = null;
    private int currentPlayer = 0;
    private boolean pieceSelected = false;
    private boolean pieceMoved = false;
    private int selectedX = 0, selectedY = 0;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {
            pieces = new Piece[SIZE][SIZE];
            return;
        }

        pieces = new Piece[][] {
                    {new Piece(true, this, 0, 0, "pawn"), null, new Piece(true, this, 0, 2, "bomb"), null, null, null, new Piece(false, this, 0, 6, "shield"), null},
                    {null, new Piece(true, this, 1, 1, "shield"), null, null, null, new Piece(false, this, 1, 5, "bomb"), null, new Piece(false, this, 1, 7, "pawn")},
                    {new Piece(true, this, 2, 0, "pawn"), null, new Piece(true, this, 2, 2, "bomb"), null, null, null, new Piece(false, this, 2, 6, "shield"), null},
                    {null, new Piece(true, this, 3, 1, "shield"), null, null, null, new Piece(false, this, 3, 5, "bomb"), null, new Piece(false, this, 3, 7, "pawn")},
                    {new Piece(true, this, 4, 0, "pawn"), null, new Piece(true, this, 4, 2, "bomb"), null, null, null, new Piece(false, this, 4, 6, "shield"), null},
                    {null, new Piece(true, this, 5, 1, "shield"), null, null, null, new Piece(false, this, 5, 5, "bomb"), null, new Piece(false, this, 5, 7, "pawn")},
                    {new Piece(true, this, 6, 0, "pawn"), null, new Piece(true, this, 6, 2, "bomb"), null, null, null, new Piece(false, this, 6, 6, "shield"), null},
                    {null, new Piece(true, this, 7, 1, "shield"), null, null, null, new Piece(false, this, 7, 5, "bomb"), null, new Piece(false, this, 7, 7, "pawn")}
        };
    }

    private boolean validPosition(int x, int y) {
        return 0 <= x && x < SIZE && 0 <= y && y < SIZE;
    }

    public Piece pieceAt(int x, int y) {
        if (!validPosition(x, y)) {
            return null;
        }
        return pieces[x][y];
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi, yi);
        if (p == null || !validPosition(xf, yf) || pieceAt(xf, yf) != null) {
            return false;
        }

        if (!p.isKing()) {
            if (p.isFire()) {
                if (yf < yi) {
                    return false;
                }
            } else if (yf > yi) {
                return false;
            }
        }

        int dx = Math.abs(xf - xi), dy = Math.abs(yf - yi);
        if (dx != dy || (dx != 1 && dx != 2)) {
            return false;
        }
        if (dx == 2 && dy == 2) {
            int midX = (xi + xf) / 2, midY = (yi + yf) / 2;
            Piece midPiece = pieceAt(midX, midY);
            if (midPiece == null || midPiece.side() == p.side()) {
                return false;
            }
        }

        return true;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        return Math.abs(xf - xi) == 2 && validMove(xi, yi, xf, yf);
    }

    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);

        if (p != null) {
            if (p.side() == currentPlayer && !pieceMoved) {
                return true;
            }
        } else {
            if (pieceSelected &&
                    ((!pieceMoved && validMove(selectedX, selectedY, x, y)) ||
                     (pieceMoved && pieceAt(selectedX, selectedY) != null && pieceAt(selectedX, selectedY).hasCaptured() && validCapture(selectedX, selectedY, x, y)))) {
                return true;
            }
        }

        return false;
    }

    public void select(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p != null) {
            pieceSelected = true;
            selectedX = x;
            selectedY = y; 
        } else {
            // Move here
            pieceMoved = true;
            Piece selectedPiece = pieceAt(selectedX, selectedY);
            selectedPiece.move(x, y);
            if (pieceAt(x, y) == selectedPiece) {
                selectedX = x;
                selectedY = y;
            }
        }
    }

    public void place(Piece p, int x, int y) {
        if (validPosition(x, y)) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (!validPosition(x, y)) {
            System.out.printf("invalid position (%d, %d)%n", x, y);
            return null;
        }
        if (pieceAt(x, y) == null) {
            System.out.printf("no piece at position (%d, %d)%n", x, y);
            return null;
        }
        Piece old = pieceAt(x, y);
        place(null, x, y);
        return old;
    }

    public boolean canEndTurn() {
        return pieceMoved;
    }

    public void endTurn() {
        currentPlayer = 1 - currentPlayer;
        pieceSelected = pieceMoved = false;
        Piece p = pieceAt(selectedX, selectedY);
        if (p != null) {
            p.doneCapturing();
        }
    }

    public String winner() {
        int[] remaining = {0, 0};
        for (Piece[] row : pieces) {
            for (Piece p : row) {
                if (p != null) {
                    ++remaining[p.side()];
                }
            }
        }
        if (remaining[0] == 0 && remaining[1] > 0) {
            return "Water";
        } else if (remaining[0] > 0 && remaining[1] == 0) {
            return "Fire";
        } else if (remaining[0] == 0 && remaining[1] == 0) {
            return "No one";
        }
        return null;
    }

    private void draw() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (pieceSelected && selectedX == i && selectedY == j && pieceAt(selectedX, selectedY) != null) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieces[i][j] != null) {
                    StringBuilder sb = new StringBuilder("img/");
                    sb.append(pieces[i][j].isBomb() ? "bomb" : pieces[i][j].isShield() ? "shield" : "pawn");
                    sb.append(pieces[i][j].isFire() ? "-fire" : "-water");
                    sb.append(pieces[i][j].isKing() ? "-crowned.png" : ".png");
                    StdDrawPlus.picture(i + .5, j + .5, sb.toString(), 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);

        Board board = new Board(false);

        while(true) {
            if (StdDrawPlus.isNPressed()) { // New game
                board = new Board(false);
                continue;
            }
            
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                
                if (board.canSelect(x, y)) {
                    board.select(x, y);
                }
            } else if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
                board.endTurn();
            }

            board.draw();   

            StdDrawPlus.show(10);
        }
    }
}
