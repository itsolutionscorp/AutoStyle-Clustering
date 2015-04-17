public class Board {
    private Piece[][] board;
    private int selectionX;
    private int selectionY;
    private boolean fireToPlay;
    private boolean performedCapture;
    private boolean pieceMoved;

    public Board(boolean shouldBeEmpty) {
        board = new Piece[8][8];
        fireToPlay = true;
        selectionX = -1;
        selectionY = -1;

        if (!shouldBeEmpty) {
            initBoard();
        }
    }

    /**
     * Places pieces in their initial configuration.
     */
    private void initBoard() {
        for (int i = 0; i < 8; i += 2) {
            placePiece(i, 0, true, null);
            placePiece(i, 2, true, "bomb");
            placePiece(i, 6, false, "shield");

            placePiece(i + 1, 1, true, "shield");
            placePiece(i + 1, 5, false, "bomb");
            placePiece(i + 1, 7, false, null);
        }
    }

    private void placePiece(int x, int y, boolean isFire, String type) {
        Piece piece = new Piece(isFire, this, x, y, type);
        place(piece, x, y);
    }

    public void place(Piece p, int x, int y) {
        if (outOfBounds(x, y) || p == null) {
            return;
        }

        removeIfPresent(p);

        board[x][y] = p;
    }

    private void removeIfPresent(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == p) {
                    board[i][j] = null;
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x, y)) {
            return null;
        } else {
            return board[x][y];
        }
    }

    public Piece remove(int x, int y) {
        if (outOfBounds(x, y)) {
            return null;
        } else {
            Piece piece = pieceAt(x, y);
            board[x][y] = null;
            return piece;
        }
    }

    public boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x, y);

        if (piece == null) {
            // Empty square
            if (!hasSelection()) {
                return false;
            }

            if (validMove(selectionX, selectionY, x, y)) {
                if (Math.abs(x - selectionX) == 2) {
                    return true;
                } else {
                    return !pieceMoved;
                }
            }
        } else {
            if (hasSelection()) {
                // You can't switch selections once you start moving.
                return !pieceMoved;
            }

            // Occupied square
            return piece.isFire() == fireToPlay;
        }

        return false;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            getSelection().move(x, y);
            pieceMoved = true;
        }

        selectionX = x;
        selectionY = y;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece from = pieceAt(xi, yi);
        Piece to = pieceAt(xf, yf);

        if (from == null) {
            return false;
        }

        if (from.isFire() || from.isKing()) {
            if (yi + 1 == yf) {
                // this is a simple move
                boolean validDiag = (xi + 1 == xf) || (xi - 1 == xf);
                boolean toEmpty = to == null;

                return validDiag && toEmpty;
            } else if (yi + 2 == yf) {
                // this is a capture move
                Piece toCapture;
                if (xi + 2 == xf) {
                    toCapture = pieceAt(xi + 1, yi + 1);
                } else if (xi - 2 == xf) {
                    toCapture = pieceAt(xi - 1, yi + 1);
                } else {
                    return false;
                }

                return (toCapture != null) &&
                    (toCapture.isFire() != from.isFire());
            }
        }

        if (!from.isFire() || from.isKing()) {
            if (yi - 1 == yf) {
                // this is a simple move
                boolean validDiag = (xi + 1 == xf) || (xi - 1 == xf);
                boolean toEmpty = to == null;

                return validDiag && toEmpty;
            } else if (yi - 2 == yf) {
                // this is a capture move
                Piece toCapture;
                if (xi + 2 == xf) {
                    toCapture = pieceAt(xi + 1, yi - 1);
                } else if (xi - 2 == xf) {
                    toCapture = pieceAt(xi - 1, yi - 1);
                } else {
                    return false;
                }

                return (toCapture != null) &&
                    (toCapture.isFire() != from.isFire());
            }
        }

        return false;
    }

    public boolean canEndTurn() {
        return performedCapture || pieceMoved;
    }

    public void endTurn() {
        selectionX = -1;
        selectionY = -1;

        fireToPlay = !fireToPlay;
        performedCapture = false;
        pieceMoved = false;
    }

    public String winner() {
        int fireCount = 0;
        int waterCount = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = pieceAt(i, j);

                if (p != null) {
                    if (p.isFire()) {
                        fireCount++;
                    } else {
                        waterCount++;
                    }
                }
            }
        }

        if (fireCount != 0 && waterCount == 0) {
            return "Fire";
        } else if (fireCount == 0 && waterCount != 0) {
            return "Water";
        } else if (fireCount == 0 && waterCount == 0) {
            return "No one";
        } else {
            return null;
        }
    }

    private boolean hasSelection() {
        return getSelection() != null;
    }

    private Piece getSelection() {
        return pieceAt(selectionX, selectionY);
    }

    private boolean outOfBounds(int x, int y) {
        return !(x >= 0 && x <= 7 && y >= 0 && y <= 7);
    }

    public static void main(String[] args) {
        Board board = new Board(false);

        while (true) {
            display(board);

            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();

                if (board.canSelect(x, y)) {
                    board.select(x, y);
                }
            } else if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }

            StdDrawPlus.show(10);
        }
    }

    private static void display(Board board) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean isSelected = board.hasSelection() &&
                    board.selectionX == i && board.selectionY == j;

                if (isSelected) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }

                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                drawPiece(board, i, j);
            }
        }
    }

    private static void drawPiece(Board board, int i, int j) {
        Piece p = board.pieceAt(i, j);
        if (p != null) {
            String path = "img/";

            if (p.isShield()) {
                path += "shield";
            } else if (p.isBomb()) {
                path += "bomb";
            } else {
                path += "pawn";
            }

            path += "-";

            if (p.isFire()) {
                path += "fire";
            } else {
                path += "water";
            }

            if (p.isKing()) {
                path += "-crowned";
            }

            path += ".png";

            StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
        }
    }
}
