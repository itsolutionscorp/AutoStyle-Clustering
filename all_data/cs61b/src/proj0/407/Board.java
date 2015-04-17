public class Board {
    private Piece[][] board;
    private boolean isFireTurn;
    private boolean pieceMoved;
    private Piece pieceSelected;
    private int xSelected;
    private int ySelected;

    public Board(boolean shouldBeEmpty) {
        board = new Piece[8][8];
        if (!shouldBeEmpty) {
            placePieces(0, true, "pawn", true);
            placePieces(1, false, "shield", true);
            placePieces(2, true, "bomb", true);

            placePieces(5, false, "bomb", false);
            placePieces(6, true, "shield", false);
            placePieces(7, false, "pawn", false);
        }
        isFireTurn = true;
        pieceSelected = null;
        pieceMoved = false;
        xSelected = -1;
        ySelected = -1;
    }

    private void placePieces(int row, boolean evens, String type, boolean isFire) {
        for (int i = 0; i < board.length; i++) {
            if ((evens && i % 2 == 0) || (!evens && i % 2 != 0)) {
                board[i][row] = new Piece(isFire, this, i, row, type);
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        try {
            return board[x][y];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void place(Piece p, int x, int y) {
        try {
            board[x][y] = p;
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x, y);
        if (piece != null) {
            if ((piece.isFire() && isFireTurn)
                    || (!piece.isFire() && !isFireTurn)) {
                return pieceSelected == null || !pieceMoved;
            }
        } else if (pieceSelected != null
                && ((pieceSelected.isFire() && isFireTurn) || (!pieceSelected
                        .isFire() && !isFireTurn))) {
            return (!pieceMoved && isValidMove(xSelected, ySelected, x, y,
                    pieceSelected))
                    || (pieceSelected != null && pieceSelected.hasCaptured() && isValidCapture(
                            xSelected, ySelected, x, y, pieceSelected));
        }
        return false;
    }

    private boolean isValidMove(int xi, int yi, int xf, int yf, Piece piece) {
        if (pieceAt(xi, yi) != null && pieceAt(xf, yf) == null
                && Math.abs(xi - xf) == 1) {
            if (piece.isKing() && Math.abs(yi - yf) == 1) {
                return true;
            } else if (piece.isFire() && yf - yi == 1) {
                return true;
            } else if (!piece.isFire() && yi - yf == 1) {
                return true;
            }
        }
        return isValidCapture(xi, yi, xf, yf, piece);
    }

    private boolean isValidCapture(int xi, int yi, int xf, int yf, Piece piece) {
        if (pieceAt(xi, yi) != null && pieceAt(xf, yf) == null && piece != null) {
            if (Math.abs(xi - xf) == 2) {
                if ((piece.isKing() && Math.abs(yi - yf) == 2)
                        || (piece.isFire() && yf - yi == 2)
                        || (!piece.isFire() && yi - yf == 2)) {
                    Piece captureTarget = pieceAt(xi + (xf - xi) / 2, yi + (yf - yi) / 2);
                    return captureTarget != null && (captureTarget.isFire() != piece.isFire());
                }
            }
        }
        return false;
    }

    private void fillSquare(int x, int y) {
        StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
    }

    public void select(int x, int y) {
        Piece piece = pieceAt(x, y);
        if (piece != null) {
            pieceSelected = piece;
        } else {
            if (pieceSelected != null) {
                pieceSelected.move(x, y);
                pieceMoved = true;
                if (board[xSelected][ySelected] != null) {
                    board[xSelected][ySelected] = null;
                    board[x][y] = pieceSelected;
                }
            }
        }
        xSelected = x;
        ySelected = y;
    }

    public Piece remove(int x, int y) {
        try {
            Piece piece = board[x][y];
            if (piece == null) {
                System.out.println("Cannot remove: no piece at (" + x + "," + y
                        + ")");
            }
            board[x][y] = null;
            return piece;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Cannot remove: (" + x + "," + y
                    + ") is out of bounds");
            return null;
        }
    }

    public boolean canEndTurn() {
        return pieceMoved
                || (pieceSelected != null && pieceSelected.hasCaptured());
    }

    public void endTurn() {
        isFireTurn = !isFireTurn;
        pieceMoved = false;
        if (pieceSelected != null) {
            pieceSelected.doneCapturing();
            pieceSelected = null;
        }
        xSelected = -1;
        ySelected = -1;
    }

    private void reColourSelected() {
        if ((xSelected + ySelected) % 2 == 0)
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(xSelected + 0.5, ySelected + 0.5, 0.5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    }

    public String winner() {
        boolean noWaterPieces = true;
        boolean noFirePieces = true;
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null) {
                    if (piece.isFire()) {
                        noFirePieces = false;
                    } else {
                        noWaterPieces = false;
                    }
                }
            }
        }

        if (noWaterPieces && noFirePieces) {
            return "No one";
        } else if (noWaterPieces) {
            return "Fire";
        } else if (noFirePieces) {
            return "Water";
        } else {
            return null;
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i != xSelected && j != ySelected) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = board[i][j];
                if (piece != null && piece.isFire()) {
                    if (piece.isBomb()) {
                        if (piece.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/bomb-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/bomb-fire.png", 1, 1);
                        }
                    } else if (piece.isShield()) {
                        if (piece.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/shield-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/shield-fire.png", 1, 1);
                        }
                    } else {
                        if (piece.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/pawn-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/pawn-fire.png", 1, 1);
                        }
                    }
                } else if (piece != null) {
                    if (piece.isBomb()) {
                        if (piece.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/bomb-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/bomb-water.png", 1, 1);
                        }
                    } else if (piece.isShield()) {
                        if (piece.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/shield-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/shield-water.png", 1, 1);
                        }
                    } else {
                        if (piece.isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/pawn-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5,
                                    "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while (board.winner() == null) {
            board.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)) {
                    board.reColourSelected();
                    board.select(x, y);
                    board.fillSquare(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.reColourSelected();
                    board.endTurn();
                }
            }
            StdDrawPlus.show(25);
        }
        
        System.out.println(board.winner());
    }
}
