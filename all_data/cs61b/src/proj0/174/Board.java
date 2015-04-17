/**
 * Created by gurpreet on 2/8/15.
 */
public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private int[] numPieces = {0, 0};
    private int n = 8;
    private int currentPlayer;
    private boolean hasMoved;
    private Piece selectedPiece;
    private int xi = -1;
    private int yi = -1;

    public static void main(String[] args) {

        Board b = new Board(false);

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }
            StdDrawPlus.show(10);
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }
        }
    }

    private void drawBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i == xi && j == yi) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String getImage(Piece p) {
        String team = p.isFire() ? "-fire" : "-water";
        String king = p.isKing() ? "-crowned" : "";
        String piece;
        if (p.isBomb())        piece = "bomb";
        else if (p.isShield()) piece = "shield";
        else                   piece = "pawn";
        return "img/" + piece + team + king + ".png";
    }

    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            for (int x = 0; x < 7; x += 2) {
                place(new Piece(true, this, x, 0, "pawn"), x, 0);
                place(new Piece(true, this, x, 2, "bomb"), x, 2);
                place(new Piece(false, this, x, 6, "shield"), x, 6);
            }
            for (int x = 1; x < 8; x += 2) {
                place(new Piece(true, this, x, 1, "shield"), x, 1);
                place(new Piece(false, this, x, 5, "bomb"), x, 5);
                place(new Piece(false, this, x, 7, "pawn"), x, 7);
            }
        }
        hasMoved = false;
        selectedPiece = null;
        currentPlayer = 0;
    }

    public void place(Piece p, int x, int y) {
        if(checkBounds(x, y) && p != null) {
            // If piece is on board, find its location and remove it.
            int [] onBoard = pieceOnBoard(p);
            if (onBoard[0] != -1) {
                  remove(onBoard[0], onBoard[1]);
            }
            pieces[x][y] = p;
            numPieces[p.side()] += 1;
        }
    }


    public Piece pieceAt(int x, int y) {
        if (checkBounds(x, y)) {
            return pieces[x][y];
        }
        return null;
    }

    public Piece remove(int x, int y) {
        if (checkBounds(x, y)) {
            Piece p = pieceAt(x, y);
            if (p != null) {
                numPieces[p.side()] -= 1;
                pieces[x][y] = null;
                return p;
            }
            System.out.println("There is no piece at those coordinates.");
        } else {
            System.out.println("The given coordinates were out of the bounds of the board");
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);
        return  p == null && selectedPiece != null && validMove(x, y) && (!hasMoved || (selectedPiece.hasCaptured() && validCapture(x,y))) ||                         //Selected empty square
                p != null && p.side() == currentPlayer && (selectedPiece == null || !hasMoved);  //Selected a piece
    }

    private boolean validMove(int xf, int yf) {
        int xChange = Math.abs(xf - xi);
        int yChange = Math.abs(yf - yi);
        boolean yDirection = selectedPiece.isKing() || (selectedPiece.side() == 0 ? yf > yi : yf < yi);

        if (xChange > 2 || yChange > 2 || xChange == 0 || yChange == 0 || xChange!= yChange) return false;
        if (xChange == 2) return validCapture(xf, yf) && yDirection;
        return yDirection && xChange == 1;
    }

    private boolean validCapture(int xf, int yf) {
        if (Math.abs(xf - xi) != 2 && Math.abs(yf - yi) != 2) return false;
        int pX = xf - xi < 0 ? xi - 1 : xi + 1;
        int pY = yf - yi < 0 ? yi - 1 : yi + 1;
        Piece capturePiece = pieceAt(pX, pY);
        return capturePiece != null && capturePiece.side() !=  selectedPiece.side();
    }

    public void select(int x, int y) {
        Piece p = pieceAt(x,y);
        if (!hasMoved && p != null) {
            xi = x; yi = y;
            selectedPiece = p;
        }

        boolean multiCapture = selectedPiece.hasCaptured() && validCapture(x, y) && !selectedPiece.isBomb();
        if ((!hasMoved || multiCapture) && pieceAt(x, y) == null) {
            selectedPiece.move(x, y);
            if (selectedPiece.hasCaptured() && selectedPiece.isBomb()) {
                xi = -1; yi =-1;
            } else {
                xi = x; yi = y;
            }
            hasMoved = true;
        }
    }

    public boolean canEndTurn() {
        return hasMoved;
    }

    public void endTurn() {
        currentPlayer = currentPlayer == 0 ? 1 : 0;
        hasMoved = false;
        selectedPiece.doneCapturing();
        selectedPiece = null;
        xi = -1;
        yi = -1;
        this.winner();
    }

    public String winner() {
        if (numPieces[0] == 0 && numPieces[1] == 0) return "No one";
        if (numPieces[0] == 0) return "water";
        if (numPieces[1] == 0) return "fire";
        return null;
    }

    private int[] pieceOnBoard(Piece p) {
        int[] pos = {-1,-1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pieces[i][j] == p) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        return pos;
    }

    private boolean checkBounds(int x, int y) {
        return x < n && y < n && x >= 0 && y >= 0;
    }
}
