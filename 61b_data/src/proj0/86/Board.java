/**
 * Created by tommytai on 2/6/15.
 */

public class Board {

    private static boolean[][] pexist; //does the piece exist?
    private static Piece[][] pstat; // attributes of the piece and the piece itself
    private static int fire;
    private static boolean selp;// selected piece if has been selected alrdy?
    private static int count; // same as step 2
    private static Piece tomove;
    private static int xinit;
    private static int yinit;
    private static int stepone; //first click done
    private static boolean capt; //checks if captured piece
    private static int moved;

    public static void main(String args[]) {

        Board bo = new Board(false);
        bo.startGame(bo, 8);
    }

    private static void startGame(Board bo, int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while (true) {
            bo.drawBoard(N);
            if (StdDrawPlus.mousePressed() && count == 0 && stepone == 0) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (bo.canSelect((int) x, (int) y)) { //checks if the piece can be selected
                    bo.select((int) x, (int) y);
                    bo.drawBoard(N); // redraw board again
                    stepone++;
                }
            }
            if (StdDrawPlus.mousePressed() && count == 0 && stepone == 1 && pexist[(int) StdDrawPlus.mouseX()][(int) StdDrawPlus.mouseY()]) {
                stepone -= 1;
                bo.drawBoard(N);
            }

            if (StdDrawPlus.mousePressed() && !pexist[(int) StdDrawPlus.mouseX()][(int) StdDrawPlus.mouseY()] && stepone == 1 && count == 0) { // if the mouse was pressed
                double xx = StdDrawPlus.mouseX(); // set the x coord
                double yy = StdDrawPlus.mouseY(); // set the y coord
                if (bo.canSelect((int) xx, (int) yy)) { //see if the move from the initial to the final can be done
                    bo.select((int) xx, (int) yy);
                    bo.drawBoard(N); //updates board
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (bo.canEndTurn()) {
                    bo.endTurn();
                    bo.drawBoard(N);
                    stepone = 0;
                    if (bo.winner() == "Water" || bo.winner() == "Fire" || bo.winner() == "No one") {
                        System.out.println(bo.winner() + " won the game!");
                        break;
                    }
                    System.out.println("End of Turn");
                }
            }
            StdDrawPlus.show(10);
        }
    }

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pexist[i][j] && pstat[i][j].isBomb() && pstat[i][j].isFire() && pstat[i][j].isKing() == false) { // if fire and bomb piece and not king
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isShield() && pstat[i][j].isFire() && pstat[i][j].isKing() == false) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isFire() && pstat[i][j].isKing() == false) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isBomb() && pstat[i][j].isFire() == false && pstat[i][j].isKing() == false) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isShield() && pstat[i][j].isFire() == false && pstat[i][j].isKing() == false) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isFire() == false && pstat[i][j].isKing() == false) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isBomb() && pstat[i][j].isFire() && pstat[i][j].isKing()) { //fire king bomb xx
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isShield() && pstat[i][j].isFire() && pstat[i][j].isKing()) { //fire king shield
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isKing() && pstat[i][j].isFire() && pstat[i][j].isBomb() == false && pstat[i][j].isShield() == false) { //fire king pawn
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isBomb() && pstat[i][j].isFire() == false && pstat[i][j].isKing()) {//water king bomb xx
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isShield() && pstat[i][j].isFire() == false && pstat[i][j].isKing()) { // water king shield xx
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                } else if (pexist[i][j] && pstat[i][j].isKing() && pstat[i][j].isFire() == false && pstat[i][j].isBomb() == false && pstat[i][j].isShield() == false) { //water king pawn
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty) { // Status: done!
        //refer to StdDrawDemo to create this board!
        int N = 8;
        fire = 0; //to check turn
        selp = false;
        count = 0;
        stepone = 0;
        capt = false;
        moved = 0;
        // create array space

        if (shouldBeEmpty == false) {
            pexist = new boolean[N][N];
            pstat = new Piece[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((j == 0) && (i % 2 == 0)) {
                        pexist[i][j] = true; // says that there exists a piece at this (i,j) coordinate
                        pstat[i][j] = new Piece(true, this, i, j, "pawn"); //store that piece
                    } else if ((j == 1) && ((i - 1) % 2 == 0)) {
                        pexist[i][j] = true;
                        pstat[i][j] = new Piece(true, this, i, j, "shield");
                    } else if ((j == 2) && ((i % 2) == 0)) {
                        pexist[i][j] = true;
                        pstat[i][j] = new Piece(true, this, i, j, "bomb");
                    } else if ((j == 5) && ((i - 1) % 2 == 0)) { // water
                        pexist[i][j] = true;
                        pstat[i][j] = new Piece(false, this, i, j, "bomb");
                    } else if ((j == 6) && (i % 2 == 0)) {
                        pexist[i][j] = true;
                        pstat[i][j] = new Piece(false, this, i, j, "shield");
                    } else if ((j == 7) && ((i - 1) % 2 == 0)) {
                        pexist[i][j] = true;
                        pstat[i][j] = new Piece(false, this, i, j, "pawn");
                    } else {
                        pexist[i][j] = false;
                        pstat[i][j] = null;
                    }
                }
            }
        } else {
            pexist = new boolean[N][N];
            pstat = new Piece[N][N];

        }
    }

    public Piece pieceAt(int x, int y) { //Status: conditionally done

        if (x < 0 || y < 0 || x > 8 || y > 8) {
            return null;
        } else if (pexist[x][y]) {
            return pstat[x][y];
        } else if (pexist[x][y] == false) {
            return null;
        } else {
            return null;
        }
    }

    private boolean fireturn(int fir) {
        if (fir % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean canSelect(int x, int y) { //Status: not done

        if (pexist[x][y] && count == 0 && capt == false) {
            if (fireturn(fire) && pstat[x][y].isFire()) {
                if (selp == false) { //has not selected a piece
                    return true;
                } else if (selp && count == 0) { //selected a piece but has not moved
                    return true;
                } else {
                    return false;
                }
            } else if (!fireturn(fire) && pstat[x][y].isFire() == false && capt == false) {
                if (selp == false) {
                    return true;
                } else if (selp && count == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (pexist[x][y] == false && count == 0) { //selected an empty spot
            if (selp && count == 0 && validMove(xinit, yinit, x, y) && capt == false) { //if selected a piece and has not move, and valid move, then execute
                if (capt == false && ((xinit + 1 == x && yinit + 1 == y) || (xinit + 1 == x && yinit - 1 == y) || (xinit - 1 == x && yinit + 1 == y) || (xinit - 1 == x && yinit - 1 == y))) {
                    count = 5;
                    return true;
                } else if (capt == false && ((xinit + 2 == x && yinit + 2 == y) || (xinit + 2 == x && yinit - 2 == y) || (xinit - 2 == x && yinit + 2 == y) || (xinit - 2 == x && yinit - 2 == y))) {
                    count = 5;
                    return true;
                } else {
                    return false;
                }
            } else if (selp && count == 0 && validMove(xinit, yinit, x, y) && capt) {
                if (capt && ((xinit + 2 == x && yinit + 2 == y) || (xinit + 2 == x && yinit - 2 == y) || (xinit - 2 == x && yinit + 2 == y) || (xinit - 2 == x && yinit - 2 == y))) {
                    count = 5;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean pcapt(Piece p) {

        if (p.hasCaptured()) {
            return true;
        } else {
            return false;
        }
    }


    public void select(int x, int y) { //Status: conditionally done

        if (pexist[x][y] && count == 0 && capt == false) {
            xinit = x;
            yinit = y;
            selp = true;// 1 is selected
            tomove = pieceAt(x, y);
        } else if (pexist[x][y] == false && selp) {
            tomove.move(x, y);
            moved = 5;
            if (pcapt(tomove)) {
                capt = true;
                count = 0;
                if (pexist[x][y]) {
                    int x2;
                    int y2;
                    int xmin;
                    int ymin;
                    xinit = x;
                    yinit = y;
                    tomove = pieceAt(x, y);
                        if ((x + 2 < 8 && y + 2 < 8 && (validMove(x, y, x + 2, y + 2)))) {
                            x2 = x + 2;
                            y2 = y + 2;
                            if (x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
                                if (validMove(x, y, x2, y2)) {
                                    count = 0;
                                    selp = true;
                                } else {
                                    count = 5;
                                    selp = false;
                                }
                            }
                        } else if ((x + 2 < 8 && y - 2 >= 0) && (validMove(x, y, x + 2, y - 2))) {
                            x2 = x + 2;
                            ymin = y - 2;
                            if (x2 >= 0 && x2 < 8 && ymin >= 0 && ymin < 8) {
                                if (validMove(x, y, x2, ymin)) {
                                    count = 0;
                                    selp = true;
                                } else {
                                    count = 5;
                                    selp = false;
                                }
                            }
                        } else if ((x - 2 >= 0 && y + 2 < 8) && (validMove(x, y, x - 2, y + 2))) {
                            xmin = x - 2;
                            y2 = y + 2;
                            if (xmin >= 0 && xmin < 8 && y2 >= 0 && y2 < 8) {
                                if (validMove(x, y, xmin, y2)) {
                                    count = 0;
                                    selp = true;
                                } else {
                                    count = 5;
                                    selp = false;
                                }
                            }
                        } else if ((x - 2 >= 0 && y - 2 >= 0) && (validMove(x, y, x - 2, y - 2))) {
                            xmin = x - 2;
                            ymin = y - 2;
                            if (xmin >= 0 && xmin < 8 && ymin >= 0 && ymin < 8) {
                                if (validMove(x, y, xmin, ymin)) {
                                    count = 0;
                                    selp = true;
                                } else {
                                    count = 5;
                                    selp = false;
                                }
                            }
                        } else {
                            count = 5;
                            selp = false;
                        }
                    } else if (!pexist[x][y]) {
                    count = 5;
                    selp = false;
                }
            } else if (capt == false) {
                count = 5;
                selp = false;
            }
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) { //Status: conditionally done
        if (xi >= 0 && xi < 8 && yi >= 0 && yi < 8 && xf >= 0 && xf < 8 && yf >= 0 && yf < 8) {
            //Case 1: fire not crowned
            if (pexist[xi][yi] && pstat[xi][yi].isFire()) { //check fire and existence
                if (((xi + 1 == xf) && (yi + 1 == yf)) || ((xi - 1 == xf) && (yi + 1 == yf))) { //checks if move is eligible length
                    if (pexist[xf][yf] && pstat[xf][yf].isFire()) { //if there is a piece that is a fire piece
                        return false;
                    } else if (pexist[xf][yf] == false) { //empty spot to move to
                        return true;
                    } else if (pexist[xf][yf]) { // there is a piece there, can't move
                        return false;
                    }
                } else if (((xi + 2 == xf) && (yi + 2 == yf)) && (!pexist[xf][yf])) { // capture move
                    if ((pexist[xi + 1][yi + 1] && pstat[xi + 1][yi + 1].isFire() == false)) { //if water to capture
                        return true;
                    } else {
                        return false;
                    }
                } else if (((xi - 2 == xf) && (yi + 2 == yf)) && (!pexist[xf][yf])) {
                    if (pexist[xi - 1][yi + 1] && pstat[xi - 1][yi + 1].isFire() == false) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            //Case 2: water not crowned
            if (pexist[xi][yi] && pstat[xi][yi].isFire() == false) { //check fire and existence
                if (((xi + 1 == xf) && (yi - 1 == yf)) || ((xi - 1 == xf) && (yi - 1 == yf))) { //checks if move is eligible length
                    if (pexist[xf][yf] && pstat[xf][yf].isFire() == false) { //if there is a piece that is a water piece
                        return false;
                    } else if (pexist[xf][yf] == false) {
                        return true;
                    } else if (pexist[xf][yf]) {
                        return false;
                    }
                } else if (((xi + 2 == xf) && (yi - 2 == yf)) && (!pexist[xf][yf])) { // capture move
                    if ((pexist[xi + 1][yi - 1] && pstat[xi + 1][yi - 1].isFire())) { //if water to capture
                        return true;
                    } else { //doesn't even capture
                        return false;
                    }
                } else if (((xi - 2 == xf) && (yi - 2 == yf)) && (!pexist[xf][yf])) {
                    if (pexist[xi - 1][yi - 1] && pstat[xi - 1][yi - 1].isFire()) {
                        return true;
                    } else { //doesn't even capture
                        return false;
                    }
                }
            }

            //Case 3: fire crowned

            if (pexist[xi][yi] && pstat[xi][yi].isFire() && pstat[xi][yi].isKing()) { //check fire and existence
                if (((xi + 1 == xf) && (yi + 1 == yf)) || ((xi - 1 == xf) && (yi + 1 == yf)) || ((xi + 1 == xf) && (yi - 1 == yf)) || ((xi - 1 == xf) && (yi - 1 == yf))) { //checks if move is eligible length
                    if (pexist[xf][yf] && pstat[xf][yf].isFire()) { //if there is a piece that is not a fire piece
                        return false;
                    } else if (pexist[xf][yf] == false) {
                        return true;
                    } else if (pexist[xf][yf]) {
                        return false;
                    }
                } else if (((xi + 2 == xf) && (yi + 2 == yf)) && (!pexist[xf][yf])) { // capture move
                    if ((pexist[xi + 1][yi + 1] && pstat[xi + 1][yi + 1].isFire() == false)) { //if water to capture
                        return true;
                    } else {
                        return false;
                    }
                } else if (((xi - 2 == xf) && (yi + 2 == yf)) && (!pexist[xf][yf])) {
                    if (pexist[xi - 1][yi + 1] && pstat[xi - 1][yi + 1].isFire() == false) {
                        return true;
                    } else {
                        return false;
                    }
                } else if ((((xi + 2 == xf) && (yi - 2 == yf))) && (!pexist[xf][yf])) { // capture move
                    if ((pexist[xi + 1][yi - 1] && pstat[xi + 1][yi - 1].isFire() == false)) { //if water to capture
                        return true;
                    } else { //doesn't even capture
                        return false;
                    }
                } else if (((xi - 2 == xf) && (yi - 2 == yf)) && (!pexist[xf][yf])) {
                    if (pexist[xi - 1][yi - 1] && pstat[xi - 1][yi - 1].isFire() == false) {
                        return true;
                    } else { //doesn't even capture
                        return false;
                    }
                }
            }

            //Case 4: Water crowned

            if (pexist[xi][yi] && pstat[xi][yi].isFire() == false && pstat[xi][yi].isKing()) { //check fire and existence
                if (((xi + 1 == xf) && (yi + 1 == yf)) || ((xi - 1 == xf) && (yi + 1 == yf)) || ((xi + 1 == xf) && (yi - 1 == yf)) || ((xi - 1 == xf) && (yi - 1 == yf))) { //checks if move is eligible length
                    if (pexist[xf][yf] && pstat[xf][yf].isFire() == false) { //if there is a piece that is not a fire piece
                        return false;
                    } else if (pexist[xf][yf] == false) {
                        return true;
                    }
                    else if (pexist[xf][yf]) {
                        return false;
                    }
                } else if (((xi + 2 == xf) && (yi + 2 == yf)) && (!pexist[xf][yf])) { // capture move
                    if ((pexist[xi + 1][yi + 1] && pstat[xi + 1][yi + 1].isFire())) { //if water to capture
                        return true;
                    } else {
                        return false;
                    }
                } else if (((xi - 2 == xf) && (yi + 2 == yf)) && (!pexist[xf][yf])) {
                    if (pexist[xi - 1][yi + 1] && pstat[xi - 1][yi + 1].isFire()) {
                        return true;
                    } else {
                        return false;
                    }
                } else if ((((xi + 2 == xf) && (yi - 2 == yf))) && (!pexist[xf][yf])) { // capture move
                    if ((pexist[xi + 1][yi - 1] && pstat[xi + 1][yi - 1].isFire())) { //if water to capture
                        return true;
                    } else { //doesn't even capture
                        return false;
                    }
                } else if (((xi - 2 == xf) && (yi - 2 == yf)) && (!pexist[xf][yf])) {
                    if (pexist[xi - 1][yi - 1] && pstat[xi - 1][yi - 1].isFire()) {
                        return true;
                    } else { //doesn't even capture
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }


    public void place(Piece p, int x, int y) { //Status: should be done

        if ((p == null) || (x < 0 || y <  0 || x >= 8 || y >= 8)) {
            return;
        }
        else if ((p != null) && (x >= 0 && y >= 0 && x < 8 && y < 8) && pexist[x][y] == false) {

            pstat[x][y] = p;
            pexist[x][y] = true;
        }
        else if ((p != null) && (x >= 0 && y >= 0 && x < 8 && y < 8) && pexist[x][y]) {
            pexist[x][y] = false;
            pstat[x][y] = null;
            pstat[x][y] = p;
            pexist[x][y] = true;
        }
    }

    public Piece remove(int x, int y) { //Status: otherwise done (?)

        Piece piecetemp;

        if (x<0 || y< 0 || x>8 || y>8) {
            System.out.println("This Coordinate does not Exist!");
            return null;
        }
        else if (pexist[x][y] == false) {
            System.out.println("This Piece does not Exist!");
            return null;
        }
        else if (pexist[x][y]) {
            piecetemp = pstat[x][y];
            pexist[x][y] = false;
            pstat[x][y] = null;
            return piecetemp;
        }
        else {
            return null;
        }
    }

    public boolean canEndTurn() { //Status:

        if (capt && moved == 5 ) {
            return true;
        }
        else if (capt == false && moved == 5) {
            return true;
        }
        else{
            System.out.println("You cannot end your turn yet!");
            return false;
        }
    }

    public void endTurn() { //Status: done (?)

        if (canEndTurn()) {
            tomove.doneCapturing(); //PROBLEM HERE when bomb captures
            capt = false;
            count = 0;
            moved = 0;
            selp = false;
            fire++;
            fireturn(fire);
        } else if (canEndTurn() == false) {
            System.out.println("You cannot end your turn yet!");
        }
    }

    public String winner() { //Status: done i think

        int numf = 0;
        int numw = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pexist[i][j] && pstat[i][j].isFire()) {
                    numf++;
                }
                else if (pexist[i][j] && pstat[i][j].isFire() == false) {
                    numw++;
                }
            }
        }

        System.out.println("Number of Fire Pieces left: " + numf);
        System.out.println("Number of Water Pieces left: " + numw);

        if (numf == 0 && numw != 0) {
            return "Water";
        }
        else if (numw == 0 && numf != 0) {
            return "Fire";
        }
        else if (numf == 0 && numw == 0) {
            return "No one";
        }
        else {
            return null;
        }
    }

}


