public class Board {
    private static final int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private int turn = 0;
    private Piece selected;
    private int selX = -1;
    private int selY = -1;
    private boolean moved;
    
    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            setUpRow(true, 0, "pawn");
            setUpRow(true, 1, "shield");
            setUpRow(true, 2, "bomb");
            setUpRow(false, N - 3, "bomb");
            setUpRow(false, N - 2, "shield");
            setUpRow(false, N - 1, "pawn");
        }
    }

    public Piece pieceAt(int x, int y) {
        if (isValidLocation(x, y)) {
            return pieces[x][y];
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        Piece target = pieceAt(x, y);
        if (target != null) {
            if ((selected == null || !moved) && target.side() == turn) {
                return true;
            }
            return false;
        }
        else {
            if ((x % 2 != y % 2) || (!isValidLocation(x, y)) || selected == null) {
                return false;
            }
            else if (Math.abs(x - selX) == 1 && Math.abs(y - selY) == 1 && !moved) {
                return validMove(selected, x, y);
            }
            else if (Math.abs(x - selX) == 2 && Math.abs(y - selY) == 2) {
                return validCapture(selected, x, y);
            }
            else {
                return false;
            }
        }
    }

    private boolean validMove(Piece chosen, int x, int y) {
        int checker = chosen.side();
        if (chosen.isKing()) {
            return true;
        }
        else {
            if (checker == 0) {
                if ((y - selY) > 0) {
                    return true;
                }
                return false;
            }
            else {
                if ((y - selY) < 0) {
                    return true;
                }
                return false;
            }
        }
    }

    private boolean validCapture(Piece chosen, int x, int y) {
        int checker = chosen.side();
        Piece capture = pieceAt((selX + x) / 2, (selY + y) / 2);
        if (capture == null || capture.side() + checker != 1) {
            return false;
        }
        else if (chosen.isKing()) {
            return true;
        }
        else {
            if (checker == 0) {
                if ((y - selY) > 0) {
                    return true;
                }
                return false;
            }
            else {
                if ((y - selY) < 0) {
                    return true;
                }
                return false;
            }
        }
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {    
            selected = pieceAt(x, y);
        }
        else {
            selected.move(x, y);
            moved = true;
        }
        selX = x;
        selY = y;
    }

    public void place(Piece p, int x, int y) {
        if (!isValidLocation(x, y) || p == null) {
        }
        else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (p == pieces[i][j]) {
                        remove(i, j);
                    }
                }
            }
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (!isValidLocation(x, y)) {
            System.out.println("Cannot remove piece due to invalid location.");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("There is no piece at the given location to remove.");
            return null;
        }
        else {
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }
    }

    public boolean canEndTurn() {
        return moved;
    }

    public void endTurn() {
        turn = 1 - turn;
        moved = false;
        selected.doneCapturing();
        selected = null;
        selX = -1;
        selY = -1;
    }

    public String winner() {
        int numWater = 0;
        int numFire = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        numFire += 1;
                    }
                    else {
                        numWater += 1;
                    }
                }
            }
        }
        if (numWater > 0 && numFire == 0) {
            return "Water";
        }
        else if (numFire > 0 && numWater == 0) {
            return "Fire";
        }
        else if (numFire == 0 && numWater == 0) {
            return "No one";
        }
        else {
            return null;
        }
    }

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private void setUpRow(boolean isFire, int y, String type) {
        for (int x = 0; x < N; x++) {
            if ((x + y) % 2 == 0) {
                pieces[x][y] = new Piece(isFire, this, x, y, type);
            }
        }
    }

    private static String getPath(Piece piece) {
        String path = "img/";
        if (piece.isShield()) {
            path += "shield-";
        }
        else if (piece.isBomb()) {
            path += "bomb-";
        }
        else {
            path += "pawn-";
        }
        if (piece.isFire()) {
            path += "fire";
        }
        else {
            path += "water";
        }
        if (piece.isKing()) {
            path += "-crowned.png";
        }
        else {
            path += ".png";
        }
        return path;
    }

    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected != null && selX == i && selY == j) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, Board.getPath(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        while (true) {
            b.drawBoard();
            if (b.winner() != null) {
                System.out.println(b.winner());
                b.drawBoard();
                StdDrawPlus.show(500);
                break;
            }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(10);
        }
    }
}