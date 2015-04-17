public class Board {

    private Piece[][] pieces;
    private boolean check_board;
    private int N = 8;
    private Piece selected;
    private int playerTurn = 0;
    private boolean ifMoved;
    private boolean ifCaptured;
    private int fireCounter;
    private int waterCounter;
    private int storeX;
    private int storeY;

    public Board(boolean shouldBeEmpty) {
        check_board = shouldBeEmpty;
        pieces = new Piece[8][8];
        if (check_board == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((j == 0) && ((i == 0) || (i == 2) || (i == 4) || (i == 6))) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if ((j == 1) && ((i == 1) || (i == 3) || (i == 5) || (i == 7))) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if ((j == 2) && ((i == 0) || (i == 2) || (i == 4) || (i == 6))) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    if ((j == 5) && ((i == 1) || (i == 3) || (i == 5) || (i == 7))) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if ((j == 6) && ((i == 0) || (i == 2) || (i == 4) || (i == 6))) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if ((j == 7) && ((i == 1) || (i == 3) || (i == 5) || (i == 7))) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (selected != null) {
                    if (pieceAt(i, j) == selected) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                }
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (!pieces[i][j].isKing() && !pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                         StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                        if (!pieces[i][j].isKing() && !pieces[i][j].isBomb() && pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    }
                        if (!pieces[i][j].isKing() && pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    }
                }
                    if (!pieces[i][j].isFire()) {
                        if (!pieces[i][j].isKing() && pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    }
                        if (!pieces[i][j].isKing() && !pieces[i][j].isBomb() && pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    }
                        if (!pieces[i][j].isKing() && !pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                }
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing() && !pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                         StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    }
                        if (pieces[i][j].isKing() && !pieces[i][j].isBomb() && pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    }
                        if (pieces[i][j].isKing() && pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    }
                }
                    if (!pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing() && pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    }
                        if (pieces[i][j].isKing() && !pieces[i][j].isBomb() && pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    }
                        if (pieces[i][j].isKing() && !pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
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
        Board board = new Board(false);
        while(true) {
            board.drawBoard(N); 
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int)x, (int)y)) {
                    board.select((int)x, (int)y);
                }
            }
                if (StdDrawPlus.isSpacePressed()) {
                    board.endTurn();
            }
            StdDrawPlus.show(10);
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x > 7 || y > 7 || x < 0 || y < 0) || (pieces[x][y] == null)) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        if (pieces[x][y] == null) {
            if (selected != null && validMove(storeX, storeY, x, y)) {
                if (!ifMoved) {
                    return true;
                } else {
                    return (((Math.abs(storeX - x) == 2) && Math.abs(storeY - y) == 2) && (ifCaptured));
                }
            } else {
                return false;
            }
        } else {
            return ((pieceAt(x, y).side() == playerTurn) && (ifMoved == false));
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
            return false;
        }
         if ((Math.abs(xi-xf) == 1) && ((yf-yi) == 1) && selected.isFire()) {
            return true;
        }
         if ((Math.abs(xi-xf) == 2) && ((yf-yi) == 2) && selected.isFire() && (pieces[(xi + xf) / 2][(yi + yf) / 2] != null) && !pieces[(xi + xf) / 2][(yi + yf) / 2].isFire()) {
            return true;
         }
        if (((Math.abs(xi-xf) == 1) && ((yi-yf) == 1)) && !selected.isFire()) {
            return true;
        }
        if ((Math.abs(xi-xf) == 2) && ((yf-yi) == -2) && !selected.isFire() && (pieces[(xi + xf) / 2][(yi + yf) / 2] != null) && pieces[(xi + xf) / 2][(yi + yf) / 2].isFire()) {
            return true;
         }
        if (selected.isKing() && (Math.abs(xf-xi) == 1) && (Math.abs(yf-yi) == 1)) {
            return true;
        }
        if ((Math.abs(xi-xf) == 2) && (Math.abs(yf-yi) == 2) && selected.isKing() && (pieces[(xi + xf) / 2][(yi + yf) / 2] != null) && selected.isFire() != (pieces[(xi + xf) / 2][(yi + yf) / 2].isFire())) {
            return true;
         }
        else {
            return false;
        }
    }

    public void select(int x, int y) {
        if (pieces[x][y] == null && selected != null) {
            if (validMove(storeX, storeY, x, y) == true) {
                selected.move(x, y);
                ifMoved = true;
                if (selected.hasCaptured()) {
                    storeX = x;
                    storeY = y;
                    ifCaptured = true;
                }
            }
        }
        else {
            selected = pieces[x][y];
            storeX = x;
            storeY = y;
            }
        }

    public void place(Piece p, int x, int y) {
        if ((x < 8 || y < 8 || x > -1 || y > -1) && (p != null)) {
                pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("input (x,y) is out of bounds");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("There is not a piece at that location");
            return null;
        } else {
            Piece temp = pieces[x][y];
            pieces[x][y] = null;
            return temp;
        }
    }

    public boolean canEndTurn() {
        return ifMoved;
   }

    public void endTurn() {
        if (canEndTurn() == true) {
            selected = null;
            ifMoved = false;
            ifCaptured = false;
            if (playerTurn == 0) {
                playerTurn = 1;
            } else {
                playerTurn = 0;
            }
        }
    }

    public String winner() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((pieces[i][j] != null) && (pieces[i][j].isFire())) {
                    fireCounter += 1;
                }
                if ((pieces[i][j] != null) && (!pieces[i][j].isFire())) {
                    waterCounter += 1;
                }
            }
        }
        if ((fireCounter == 0) && (waterCounter == 0)) {
            return "No one";
        }
        else if (fireCounter == 0) {
            return "Water";
        }
        else if (waterCounter == 0) {
            return "Fire";
        }
        else {
            return null;
        }
    }
}