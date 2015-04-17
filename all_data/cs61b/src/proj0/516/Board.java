/**
 * Created by Vincent Escueta for cs61b
 * while doing Proj0. Spring 2015. Finished!
 */
public class Board {
    private Piece[][] pieces;
    private boolean createPieces;
    private Piece selectedPiece;
    private int selectedx;
    private int selectedy;
    private boolean pieceMoved;
    private int redturn = 0;



    public static void main(String args[]) {
        Board b = new Board(false);


        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            }
            if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) {
                b.endTurn();
                if (b.winner() != null) {
                    System.out.println(b.winner() + " wins!");
                    return;
                }
            }
            StdDrawPlus.show(100);
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selectedPiece != null && (selectedx == i && selectedy == j)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(selectedx + .5, selectedy + .5, .5);
                }
                if (pieceAt(i, j) != null) {
                    place(pieceAt(i, j), i, j);
                    StdDrawPlus.picture(i + .5, j + .5, pieceImage(i, j), 1, 1);
                }
            }
        }
        createPieces = true;
    }

    private String pieceImage(int x, int y) {
        String fireornot;
        String king;
        String type;
        if (pieceAt(x, y) != null) {
            if (pieceAt(x, y).isFire()) {
                fireornot = "-fire";
            } else {
                fireornot = "-water";
            }
            if (pieceAt(x, y).isKing()) {
                king = "-crowned.png";
            } else {
                king = ".png";
            }
            if (pieceAt(x, y).isBomb()) {
                type = "bomb";
            } else {
                if (pieceAt(x, y).isShield()) {
                    type = "shield";
                } else {
                    type = "pawn";
                }
            }
            return "img/" + type + fireornot + king;
        }
        return null;
    }

    public Board(boolean shouldBeEmpty) {
        createPieces = shouldBeEmpty;
        pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!createPieces) {
                    if (i % 2 == 0 && j == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if (i % 2 != 0 && j == 1) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if (i % 2 == 0 && j == 2) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    if (i % 2 != 0 && j == 5) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if (i % 2 == 0 && j == 6) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if (i % 2 != 0 && j == 7) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (x < 8 && y < 8 && x >= 0 && y >= 0) {
            if (pieceAt(x, y) != null && redturn == pieceAt(x,y).side()) {
                if (selectedPiece == null && !pieceMoved) {
                    return true;
                }
                if (!pieceMoved) {
                    return true;
                }
            }
            if (pieceAt(x, y) == null) {
                if (selectedPiece != null && !pieceMoved && (allowableSpots(x, y) || captureSpot(x, y))) {
                    return true;
                }
                if (selectedPiece != null && captureSpot(x, y) && selectedPiece.hasCaptured()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean allowableSpots(int x, int y) {
        if (pieceAt(x, y) == null && selectedPiece != null) {
            if (selectedPiece.isKing()) {
                if ((selectedx + 1 == x && selectedy + 1 == y) ||
                        (selectedx + 1 == x && selectedy - 1 == y) ||
                        (selectedx - 1 == x && selectedy + 1 == y) ||
                        (selectedx - 1 == x && selectedy - 1 == y)) {
                    return true;
                }
            }
            if (selectedPiece.isFire() &&
                    ((selectedx + 1 == x && selectedy + 1 == y) ||
                    (selectedx - 1 == x && selectedy + 1 == y))) {
                return true;
            }
            if (!selectedPiece.isFire() && ((selectedx + 1 == x && selectedy - 1 == y) ||
                    (selectedx - 1 == x && selectedy - 1 == y))) {
                return true;
            }
        }
        return false;
    }

    private boolean captureSpot(int x, int y) {
        if (pieceAt(x, y) == null && selectedPiece != null) {
            if (selectedPiece.isKing()) {
                if (selectedPiece.isFire() && (selectedx + 2 == x && selectedy + 2 == y && pieceAt(x - 1, y - 1) != null && !pieceAt(x - 1, y - 1).isFire()) ||
                        (selectedx + 2 == x && selectedy - 2 == y && pieceAt(x - 1, y + 1) != null && !pieceAt(x - 1, y + 1).isFire()) ||
                        (selectedx - 2 == x && selectedy + 2 == y && pieceAt(x + 1, y - 1) != null && !pieceAt(x + 1, y - 1).isFire()) ||
                        (selectedx - 2 == x && selectedy - 2 == y && pieceAt(x + 1, y + 1) != null && !pieceAt(x + 1, y + 1).isFire())) {
                    return true;
                }
                if (!selectedPiece.isFire() && (selectedx + 2 == x && selectedy + 2 == y && pieceAt(x - 1, y - 1) != null && pieceAt(x - 1, y - 1).isFire()) ||
                        (selectedx + 2 == x && selectedy - 2 == y && pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire()) ||
                        (selectedx - 2 == x && selectedy + 2 == y && pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire()) ||
                        (selectedx - 2 == x && selectedy - 2 == y && pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire())) {
                    return true;
                }
            }
            if (selectedPiece.isFire() &&
                    ((selectedx + 2 == x && selectedy + 2 == y && pieceAt(x - 1, y - 1) != null && !pieceAt(x - 1, y - 1).isFire()) ||
                            (selectedx - 2 == x && selectedy + 2 == y && pieceAt(x + 1, y - 1) != null && !pieceAt(x + 1, y - 1).isFire()))) {
                return true;
            }
            if (!selectedPiece.isFire() &&
                    ((selectedx + 2 == x && selectedy - 2 == y && pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire()) ||
                            (selectedx - 2 == x && selectedy - 2 == y && pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire()))) {
                return true;
            }
        }
        return false;
    }


    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            if (selectedPiece != null) {
                selectedPiece.move(x, y);
                pieceMoved = true;
            }
        }
        selectedPiece = pieceAt(x, y);
        selectedx = x;
        selectedy = y;
    }

    public void place(Piece p, int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0 || p == null) {
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (pieceAt(x, y) == null) {
            System.out.println("No Selection can be made!");
            return null;
        }
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("Selection is out of bounds!");
            return null;
        }
        if (pieceAt(x, y) != null) {
            Piece temp = pieceAt(x, y);
            pieces[x][y] = null;
            return temp;
        }
        return null;
    }

    public boolean canEndTurn() {
        return pieceMoved;
    }

    public void endTurn() {
        if (redturn == 0) {
            redturn = 1;
        } else {
            redturn = 0;
        }
        pieceMoved = false;
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
        }
        selectedPiece = null;
        selectedx = 10;
        selectedy = 10;
    }

    public String winner() {
        int firePieces = 0;
        int waterPieces = 0;
        for (int i = 0; i < pieces.length; i += 1) {
            for (int j = 0; j < pieces[i].length; j += 1) {
                if (pieceAt(i,j) != null) {
                    if (pieceAt(i, j).isFire()) {
                        firePieces += 1;
                    } else {
                        waterPieces += 1;
                    }
                }
            }
        }
        if (firePieces == 0 && waterPieces == 0) {
            return "No one";
        }
        if (firePieces != 0 && waterPieces == 0) {
            return "Fire";
        }
        if (firePieces == 0) {
            return "Water";
        }
    return null;
    }
}
