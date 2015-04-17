public class Board {
    private boolean shouldBeEmpty;
    private int turn = 0;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
    private boolean hasMoved;
    private int n = 8;
    private Piece[][] pieces = new Piece[n][n];

    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        if (!this.shouldBeEmpty) {
            setupBoard();
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.n);
        StdDrawPlus.setYscale(0, b.n);
        while (true) {
            b.drawBoard(b.n);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int i = (int) x;
                int j = (int) y;
                if (b.canSelect(i, j)) {
                    b.select(i, j);
                }
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }
            StdDrawPlus.show(10);
        }
    }

    private void drawBoard(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                /* Color the background of the selected piece white. */
                if (selectedPiece != null) {
                    StdDrawPlus.filledSquare(selectedPieceX + .5, selectedPieceY + .5, .5);
                    drawImage(pieceAt(selectedPieceX, selectedPieceY), selectedPieceX, selectedPieceY);
                }
                /* If a position in the array contains a piece, draw the piece's image. */
                if (pieceAt(i, j) != null) {
                    drawImage(pieceAt(i, j), i, j);
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public void place(Piece p, int x, int y) {
        if (((x < 0 || x > 7) || (y < 0 || y > 7)) && (p != null)) {
            return;
        }
        pieces[x][y] = p;
    }

    public boolean canSelect(int x, int y) {
        Piece clicked = pieceAt(x, y);
        /* If a piece has already been moved, another cannot be selected unless the previous move was a capture,
         * in which case the selected piece may capture again.
         */
        if (hasMoved) {
            if (selectedPiece == null) {
                return false;
            }
            if (!selectedPiece.hasCaptured()) {
                return false;
            } else {
                if ((Math.abs(selectedPieceX - x) == 2) && (validMove(x, y))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        /* Selecting a piece to move. */
        if (selectedPiece == null) {
            if (clicked == null) {
                return false;
            }
            if (clicked.side() == turn) {
                return true;
            } else {
                return false;
            }
        /* There is currently a piece selected. */
        } else {
            /* Reselect. */
            if (clicked != null) {
                if (clicked.side() == turn) {
                    return true;
                } else {
                    return false;
                }
            /* Selecting a space to which the piece will be moved. */
            } else {
                return validMove(x, y);
            }
        }
    }

    public void select(int x, int y) {
        /* Selecting a piece. */
        if (pieceAt(x, y) != null) {
            selectedPiece = pieceAt(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
        /* Move the selected piece to a new space. */
        } else {
            pieces[selectedPieceX][selectedPieceY] = null;
            selectedPieceX = x;
            selectedPieceY = y;
            place(selectedPiece, x, y);
            selectedPiece.move(x, y);
            hasMoved = true;
            if (selectedPiece.hasCaptured() && selectedPiece.isBomb()) {
                selectedPiece = null;
            }
        }
    }

    private boolean validMove(int x, int y) {
        Piece capturedPiece;
        /* The destination of the selected piece must be null. */
        if (pieceAt(x, y) != null) {
            return false;
        }
        int xDiff = x - selectedPieceX;
        /* Fire moves upward. And kings can move in either direction. */
        if (selectedPiece.side() == 0 || selectedPiece.isKing()) {
            /* Moving diagonally up one space. */
            if ((y == selectedPieceY + 1) && (Math.abs(xDiff) == 1)) {
                return true;
            }
            /* Moving diagonally up two spaces as a result of a capture. */
            if (y == selectedPieceY + 2) {
                if (xDiff == -2) {
                    capturedPiece = pieceAt(selectedPieceX - 1, selectedPieceY + 1);
                    if ((capturedPiece != null) && (capturedPiece.side() != turn)) {
                        return true;
                    }
                }
                if (xDiff == 2) {
                    capturedPiece = pieceAt(selectedPieceX + 1, selectedPieceY + 1);
                    if ((capturedPiece != null) && (capturedPiece.side() != turn)) {
                        return true;
                    }
                }
            }
        }
        /* Water moves downward. And kings can still move in either direction. */
        if (selectedPiece.side() == 1 || selectedPiece.isKing()) {
            /* Moving diagonally down one space. */
            if ((y == selectedPieceY - 1) && (Math.abs(xDiff) == 1)) {
                return true;
            }
            /* Moving diagonally down two spaces as a result of a capture. */
            if (y == selectedPieceY - 2) {
                if (xDiff == -2) {
                    capturedPiece = pieceAt(selectedPieceX - 1, selectedPieceY - 1);
                    if ((capturedPiece != null) && (capturedPiece.side() != turn)) {
                        return true;
                    }
                }
                if (xDiff == 2) {
                    capturedPiece = pieceAt(selectedPieceX + 1, selectedPieceY - 1);
                    if ((capturedPiece != null) && (capturedPiece.side() != turn)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Piece remove(int x, int y) {
        /* Replace the Piece object at a certain position with null. */
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            System.out.println(x + ", " + y + " is out of bounds.");
            return null;
        }
        if (pieceAt(x, y) == null) {
            System.out.println("There is no piece at " + x + ", " + y + ".");
            return null;
        }
        Piece p = pieceAt(x, y);
        pieces[x][y] = null;
        return p;
    }

    public boolean canEndTurn() {
        /* Must have moved or captured in order to end a turn. */
        if (hasMoved) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        /* Handles the switching of players, as well as reseting selectedPiece to null. */
        hasMoved = false;
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
            selectedPiece = null;
        }
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

    public String winner() {
        /* Returns the winner, either "Fire," "Water," or "No one." */
        boolean stillFire = false;
        boolean stillWater = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isFire()) {
                        stillFire = true;
                    } else {
                        stillWater = true;
                    }
                }
            }
        }
        if (stillFire && !stillWater) {
            return "Fire";
        } else if (!stillFire && stillWater) {
            return "Water";
        } else if (!stillFire && !stillWater) {
            return "No one";
        } else {
            return null;
        }
    }

    private void drawImage(Piece p, int x, int y) {
        String image;
        String type;
        if (p == null) {
            return;
        }
        if (p.isBomb()) {
            type = "bomb";
        } else if (p.isShield()) {
            type = "shield";
        } else {
            type = "pawn";
        }
        if (p.isFire()) {
            image = type + "-fire";
        } else {
            image = type + "-water";
        }
        if (p.isKing()) {
            image += "-crowned";
        }
        StdDrawPlus.picture(x + .5, y + .5, "img/" + image + ".png", 1, 1);
    }

    private void setupBoard() {
        /* Draw the initial configuration of the board on launch. */
        Piece p = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    if (j <= 2 || j >= 5) {
                        if (j <= 2) {
                            /* Fire pieces. */
                            if (j == 0) {
                                p = new Piece(true, this, i, j, "pawn");
                            } else if (j == 1) {
                                p = new Piece(true, this, i, j, "shield");
                            } else {
                                p = new Piece(true, this, i, j, "bomb");
                            }
                        } else if (j >= 5) {
                            /* Water pieces. */
                            if (j == 5) {
                                p = new Piece(false, this, i, j, "bomb");
                            } else if (j == 6) {
                                p = new Piece(false, this, i, j, "shield");
                            } else {
                                p = new Piece(false, this, i, j, "pawn");
                            }
                        }
                    }
                }
                pieces[i][j] = p;
                p = null;
            }
        }
    }
}