public class Board{
    private boolean shouldBeEmpty;

    public Board(boolean shouldbeEmpty) {

        shouldBeEmpty = shouldbeEmpty;
        if (!shouldBeEmpty) {
            setPieces();
        }
    }
    private boolean[][] positions = new boolean[8][8];
    private Piece[][] pieces = new Piece[8][8];
    private Piece piece;
    private int player = 0;
    private int fire_count;
    private int water_count;
    private Piece selected;
    private int selecty;
    private int selectx;
    private boolean canEnd;

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (positions[i][j]) {
                    piece = pieces[i][j];
                    drawPiece(piece, i, j);
                }

                }
            }
        if ((selected != null) && (positions[selectx][selecty])) {
            StdDrawPlus.filledSquare(selectx + .5, selecty + .5, .5);
            drawPiece(selected, selectx, selecty);

        }
        }

    private void drawPiece(Piece piece, int x, int y) {
        if (piece == null) {
            return;
        }
        String type;
        String element;
        if (piece.isBomb()) {
            type = "bomb";
        }
        else if (piece.isShield()) {
            type = "shield";
        }

        else {
            type = "pawn";
        }
        if (piece.isFire()) {
            element = "fire";
        }
        else {
            element = "water";
        }
        if (piece.isKing()) {
            StdDrawPlus.picture(x + .5, y + .5, "img/" + type + "-" + element + "-crowned.png", 1, 1);
        }
        else {
            StdDrawPlus.picture(x + .5, y + .5, "img/" + type + "-" + element + ".png", 1, 1);
        }
    }

    private void setPieces() {
        int i = 0;
        fire_count = 12;
        water_count = 12;
        while (i < 8) {
            if (i % 2 == 0) {
                positions[i][0] = true;
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                positions[i][2] = true;
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                positions[i][6] = true;
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
                i++;
            }
            else {
                positions[i][1] = true;
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
                positions[i][5] = true;
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
                positions[i][7] = true;
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
                i++;
            }
        }

    }
    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            return null;
        }
            return pieces[x][y];

        }

    public void place(Piece p, int x, int y) {
        boolean existsInBoard = false;
        int a = 999;
        int b = 999;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                if (pieces[i][j] == p) {
                    a = i;
                    b = j;
                    existsInBoard = true;
                }
            }
        }
        if (existsInBoard) {
            pieces[a][b] = null;
            positions[a][b] = false;
            if (p.isFire()) {
                fire_count --;
            }
            else {
                water_count --;
            }
        }
        pieces[x][y] = p;
        positions[x][y] = true;
        if (p.isFire()) {
            fire_count ++;
        }
        else {
            water_count ++;
        }
    }

    public Piece remove(int x, int y) {
        if ((x>7) || (y>7) || (x<0) || (y<0)) {
            System.out.println("Out of bounds!");
            return null;
        }
        if (!positions[x][y]) {
            System.out.println("There's no piece here!");
            return null;

        }
        piece = pieces[x][y];
        pieces[x][y] = null;
        positions[x][y] = false;
        if ((x == selectx) && (y == selecty)) {
            selected = null;
            selectx = 999;
            selecty = 999;
        }

        if (piece.isFire()) {
            fire_count --;
        }
        else {
            water_count --;
        }
        return piece;



    }

    public boolean canEndTurn() {

        return canEnd;
    }

    public void endTurn() {
        if (player == 1) {
            player = 0;
        }
        else {
            player = 1;
        }
        if (selected != null) {
            selected.doneCapturing();
            selected = null;
            selectx = 999;
            selecty = 999;

        }

        canEnd = false;
    }

    public String winner() {
        if ((fire_count == 0) || (water_count == 0)) {
            if (fire_count > water_count) {
                return "Fire";
            }
            if (water_count > fire_count) {
                return "Water";
            }
            return "No one";

        }
        return null;

    }

    private int Direction() {
        if (player == 0) {
            return 1;
        }
        return -1;
    }


    private boolean validMove(int xi, int yi, int xf, int yf, int direction) {
        if (((xf - xi == 1) || (xi - xf ==1)) && (yf - yi == direction)) {
            return true;
        }
        return false;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf, int direction) {
        if ((((xf - xi == 2) || (xi - xf == 2)) && (yf - yi == 2*direction)) && (positions[xi + (xf - xi)/2][yi + direction]) && (pieces[xi + (xf - xi)/2][yi + direction].side() != player)) {
            return true;
        }
        return false;
    }

    public boolean canSelect(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            return false;
        }

        if ((positions[x][y]) && (player == pieces[x][y].side())) {
            if ((selected == null) || (!selected.hasCaptured())) {
                return true;
            }
        }
        else if ((!positions[x][y]) && (selected != null)) {
            if (!canEnd) {
                if (validMove(selectx, selecty, x, y, Direction()) || validCapture(selectx, selecty, x, y, Direction())) {
                    return true;
                }
                if (selected.isKing()) {
                    if (validMove(selectx, selecty, x, y, -Direction()) || validCapture(selectx, selecty, x, y, -Direction())) {
                        return true;
                    }
                }
            }
            else {
                if (validCapture(selectx, selecty, x, y, Direction())) {
                    return true;
                }
                if (selected.isKing()) {
                    if (validCapture(selectx,selecty, x, y, -Direction())) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public void select(int x, int y) {

        if (positions[x][y]) {
            selected = pieces[x][y];
        }
        else if (selected != null) {
            Piece pieceholder = selected;
            selected.move(x, y);
            if (positions[x][y]) {
                selected = pieceholder;
            }

            canEnd = true;
        }
        selectx = x;
        selecty = y;
        if (selected == null) {
            selectx = 999;
            selecty = 999;
        }


    }
    public static void main(String[] args) {
        Board b = new Board(false);

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while (true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if ((x >= 0) && (x <= 8) && (y >= 0) && (y <= 8)) {
                    if (b.canSelect((int) x, (int) y)) {
                        b.select((int) x, (int) y);
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                    }
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }

            if ((b.fire_count == 0) || (b.water_count == 0)) {
                System.out.println(b.winner());
            }

            StdDrawPlus.show(100);

        }

    }


}

