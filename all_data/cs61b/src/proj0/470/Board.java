public class Board {

    private Piece[][] pieces;
    private boolean isFireTurn = true;
    private Piece selected = null;
    private int selectedX, selectedY;
    private boolean moved = false;

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];

        if (shouldBeEmpty) {
            // pieces stays empty, does nothingg
            return;
        }

        else// starting with initial config
        {
            for (int i = 0; i < 8; i += 2) {
                int j = 0;
                Piece p = new Piece(true, this, i, j, "pawn");
                Piece q = new Piece(true, this, i + 1, j + 1, "shield");
                Piece r = new Piece(true, this, i, j + 2, "bomb");

                place(p, i, j);
                place(q, i + 1, j + 1);
                place(r, i, j + 2);
            }

            for (int i = 0; i < 8; i += 2)// place the water
            {
                int j = 5;
                Piece p = new Piece(false, this, i + 1, j + 2, "pawn");
                Piece q = new Piece(false, this, i, j + 1, "shield");
                Piece r = new Piece(false, this, i + 1, j, "bomb");

                place(p, i + 1, j + 2);
                place(q, i, j + 1);
                place(r, i + 1, j);
            }

            moved = false; // so moved resets from all the place methods above
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7)// out of bound
        {
            return null;
        }

        return pieces[x][y];
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) != null) {
            boolean geoGood = geoValid(xi, yi, xf, yf);
            boolean captureGood = capValid(xi, yi, xf, yf);

            if (moved == false) // so it is the first time its moving/capturing
            {
                if (geoGood || captureGood) {
                    return true;
                }

                else {
                    return false;
                }
            }

            else if (pieceAt(xi, yi).hasCaptured()) // captured already, possibly a second capture
            {
                if (captureGood) {
                    return true;
                }
                return false;
            }
        }

        return false;

    }

    private boolean geoValid(int xi, int yi, int xf, int yf) {
        int absXDiff = Math.abs(xf - xi);
        int absYDiff = Math.abs(yf - yi);
        int xDiff = xf - xi;
        int yDiff = yf - yi;

        boolean goodMove = (absXDiff == 1 && absYDiff == 1);
        boolean isKing = pieces[xi][yi].isKing();
        boolean isFire = pieces[xi][yi].isFire();
        boolean targetEmpty = (pieces[(xi + xDiff)][(yi + yDiff)] == null);

        if (yDiff < 0 && isFire) {
            if (isKing && goodMove && targetEmpty) {
                return true;
            }

            return false;
        }

        else if (yDiff < 0 && isFire == false) {
            if (goodMove && targetEmpty) {
                return true;
            }

            return false;
        }

        else if (yDiff > 0 && isFire == false) {
            if (isKing && goodMove && targetEmpty) {
                return true;
            }

            return false;
        }

        else // yDiff > 0 && isFire
        {
            if (goodMove && targetEmpty) {
                return true;
            }

            return false;
        }
    }

    private boolean capValid(int xi, int yi, int xf, int yf) {
        int absXDiff = Math.abs(xf - xi);
        int absYDiff = Math.abs(yf - yi);
        int xDiff = xf - xi;
        int yDiff = yf - yi;

        boolean isFire = pieces[xi][yi].isFire();
        boolean goodMove = (absXDiff == 2 && absYDiff == 2);
        boolean isKing = pieces[xi][yi].isKing();
        boolean targetEmpty = (pieces[(xi + xDiff)][(yi + yDiff)] == null);
        boolean canCap = ((pieces[(xi + xDiff / 2)][(yi + yDiff / 2)] != null) && (pieces[(xi + xDiff / 2)][(yi + yDiff / 2)]
                .isFire() != isFire));

        if (yDiff < 0 && isFire && isFireTurn) {
            if (isKing && goodMove && canCap && targetEmpty) {
                return true;
            }

            return false;
        }

        else if (yDiff < 0 && isFire == false && isFireTurn == false) {
            if (goodMove && canCap && targetEmpty) {
                return true;
            }

            return false;
        }

        else if (yDiff > 0 && isFire == false && isFireTurn == false) {
            if (isKing && goodMove && canCap && targetEmpty) {
                return true;
            }

            return false;
        }

        else // yDiff > 0 && isFire
        {
            if (goodMove && canCap && targetEmpty && isFireTurn) {
                return true;
            }

            return false;
        }
    }

    public boolean canSelect(int x, int y) {
        Piece tempSelect = pieceAt(x, y);

        if ((x + y) % 2 != 0)// selecting a red square
        {
            return false;
        }

        else if (tempSelect != null)// theres a piece in the selected square
        {
            if (selected == null)// havent selected anything yet
            {
                if (tempSelect.isFire() && isFireTurn) {
                    return true;
                }

                else if (tempSelect.isFire() == false && isFireTurn == false) {
                    return true;
                }

                return false;
            }

            else// already selected another piece
            {
                if (tempSelect.isFire() && selected.isFire() && isFireTurn
                        && (moved == false)) {
                    return true;
                }

                else if (tempSelect.isFire() == false
                        && selected.isFire() == false && isFireTurn == false
                        && moved == false) {
                    return true;
                }

                return false;
            }
        }

        else // wants to select an empty square
        {
            if (selected == null)// havent selected anything yet
            {
                return false;
            }

            else// selected a piece
            {
                boolean goodMove = validMove(selectedX, selectedY, x, y);
                if (moved == false && goodMove) {
                    return true;
                }

                else if (pieceAt(selectedX, selectedY) != null)// in case the
                                                               // bomb explodes
                                                               // and is removed
                {
                    if (pieceAt(selectedX, selectedY).hasCaptured() && goodMove) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    public void select(int x, int y) {
        Piece tempSelect = pieceAt(x, y);

        if (tempSelect == null && selected != null) {
            selected.move(x, y);
            moved = true;
            selectedX = x;
            selectedY = y;
        }

        else if (tempSelect != null) {
            selected = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
        }
    }

    public void place(Piece p, int x, int y) {
        if (x > 7 || y > 7)// out of bound
        {
            return;
        }

        Piece temp = p;

        outerloop: // go through the whole loop to remove the old piece
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == temp) {
                    remove(i, j);
                    break outerloop;
                }
            }
        }

        pieces[x][y] = temp;
    }

    public Piece remove(int x, int y) {
        if (x > 7 || y > 7)// out of bound
        {
            System.out.println("Out of Bound");
            return null;
        }

        else if (pieces[x][y] == null) {
            System.out.println("Nothing there");
            return null;
        }

        Piece temp = pieces[x][y];
        pieces[x][y] = null;
        return temp;
    }

    public boolean canEndTurn() {
        return moved;
    }

    public void endTurn() {
        isFireTurn = !isFireTurn;
        if (selected != null && selected.hasCaptured()) {
            selected.doneCapturing();
        }
        selected = null;
        moved = false;
        selectedX = 0;
        selectedY = 0;
    }

    public String winner() {
        boolean fireLeft = anyFire(pieces);
        boolean waterLeft = anyWater(pieces);
        if (fireLeft && waterLeft) {
            return null;// no winner yet
        }

        else if (fireLeft) {
            return "Fire";
        }

        else if (waterLeft) {
            return "Water";
        }

        else {
            return "No one";
        }
    }

    private boolean anyFire(Piece[][] pieces) {
        // check if there is any fire left
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null && pieces[i][j].isFire()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean anyWater(Piece[][] pieces) {
        // check if there is any water left
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null && pieces[i][j].isFire() == false)// if
                                                                           // theres
                                                                           // water
                                                                           // piece
                                                                           // left
                {
                    return true;
                }
            }
        }

        return false;
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == selectedX && j == selectedY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }

                else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }

                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-fire-crowned.png", 1, 1);
                            }

                            else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-fire.png", 1, 1);
                            }

                        }

                        else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-fire-crowned.png", 1, 1);
                            }

                            else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-fire.png", 1, 1);
                            }

                        }

                        else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-fire-crowned.png", 1, 1);
                            }

                            else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-fire.png", 1, 1);
                            }

                        }
                    }

                    else// its water
                    {
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-water-crowned.png", 1, 1);
                            }

                            else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/shield-water.png", 1, 1);
                            }

                        }

                        else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-water-crowned.png", 1, 1);
                            }

                            else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/bomb-water.png", 1, 1);
                            }

                        }

                        else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-water-crowned.png", 1, 1);
                            }

                            else {
                                StdDrawPlus.picture(i + .5, j + .5,
                                        "img/pawn-water.png", 1, 1);
                            }

                        }
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while (true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if ((x >= 0 && x < 8) && (y >= 0 && y < 8)
                        && (b.canSelect((int) x, (int) y)))// not out of bound
                {
                    b.select((int) x, (int) y);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.selected != null && b.canEndTurn())// to make sure the
                                                         // player has selected
                                                         // something first;
                                                         // avoid calling
                                                         // canSelect too may
                                                         // times
                {
                    b.endTurn();
                }
            }

            StdDrawPlus.show(10);
        }
    }

}