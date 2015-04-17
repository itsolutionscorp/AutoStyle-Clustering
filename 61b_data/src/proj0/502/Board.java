public class Board {

    private Board b; private static Piece[][] pieces; private static boolean shouldBeEmpty;
    private static boolean whosTurn; private static boolean hasMoved; private static Piece lastPiece;
    private int THEY; private int THEX; private static boolean moveAgain; private static int countFire; private static int countWater;

// DRAWING BLANK BOARD
    public static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else {
                    if (whosTurn == true) {
                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    }
                    else {
                        StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                    }
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        if (shouldBeEmpty == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pieces[i][j] != null) {
                        StdDrawPlus.picture(i+.5,j+.5,giveMePicture(pieces[i][j]),1,1);
                    }
                }
            }
        }
    }

    private static String giveMePicture(Piece p) {
        String A = "img/";
        if (p.isBomb())         A = A + "bomb-";
        else if (p.isShield())  A = A + "shield-";
        else                    A = A + "pawn-";
        if (p.isFire())         A = A + "fire";
        else                    A = A + "water";
        if (p.isKing())         return A + "-crowned.png";
        else                    return A + ".png";
    }


    //INITIALIZE CONSTRUCTOR
    public Board(boolean shouldBeEmpty0) {
        shouldBeEmpty = shouldBeEmpty0;
        whosTurn = true; hasMoved = false;
        lastPiece = null; moveAgain = false;
        THEY = 12; THEX = 12;

        pieces = new Piece[8][8];
        if (!shouldBeEmpty0) {
            pieces[0][0] = new Piece(true,this,0,0,"pawn");
            pieces[2][0] = new Piece(true,this,2,0,"pawn");
            pieces[4][0] = new Piece(true,this,4,0,"pawn");
            pieces[6][0] = new Piece(true,this,6,0,"pawn");
            pieces[1][1] = new Piece(true,this,1,1,"shield");
            pieces[3][1] = new Piece(true,this,3,1,"shield");
            pieces[5][1] = new Piece(true,this,5,1,"shield");
            pieces[7][1] = new Piece(true,this,7,1,"shield");
            pieces[0][2] = new Piece(true,this,0,2,"bomb");
            pieces[2][2] = new Piece(true,this,2,2,"bomb");
            pieces[4][2] = new Piece(true,this,4,2,"bomb");
            pieces[6][2] = new Piece(true,this,6,2,"bomb");
            pieces[1][5] = new Piece(false,this,1,5,"bomb");
            pieces[3][5] = new Piece(false,this,3,5,"bomb");
            pieces[5][5] = new Piece(false,this,5,5,"bomb");
            pieces[7][5] = new Piece(false,this,7,5,"bomb");
            pieces[0][6] = new Piece(false,this,0,6,"shield");
            pieces[2][6] = new Piece(false,this,2,6,"shield");
            pieces[4][6] = new Piece(false,this,4,6,"shield");
            pieces[6][6] = new Piece(false,this,6,6,"shield");
            pieces[1][7] = new Piece(false,this,1,7,"pawn");
            pieces[3][7] = new Piece(false,this,3,7,"pawn");
            pieces[5][7] = new Piece(false,this,5,7,"pawn");
            pieces[7][7] = new Piece(false,this,7,7,"pawn");
        }

    }

    public Piece pieceAt(int x, int y) {
        if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8) && (pieces[x][y] != null)) {
            THEY = y; THEX = x;
            return pieces[x][y];
        }
        return null;
    }

    private String whatType(Piece p) {
        if (p.isShield())   return "shield";
        else if (p.isBomb())return "bomb";
        else                return "pawn";
    }

    public void place(Piece p, int x, int y) {
        if (((x >= 0) && (x < 8) && (y >= 0) && (y < 9)) && (p != null)) {
            pieces[x][y] = p;
        }
    }

    private boolean rightDirection(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi,yi).isKing() == true) {
            return true;
        }
        else {
            if (pieceAt(xi,yi).isFire() && (yf > yi)) {
                return true;
            }
            else if (!pieceAt(xi,yi).isFire() && (yf < yi)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((Math.round(Math.abs(xi-xf)) == 1) && (Math.round(Math.abs(yi-yf)) == 1)) {
            if (pieceAt(xf,yf) == null) {
                return true;
            }
            else {
                return false;
            }
        }
        else if ((Math.round(Math.abs(xi-xf)) == 2) && (Math.round(Math.abs(yi-yf)) == 2)) {
            if ((xf < 8) && (yf < 8) && (xf >= 0) && (yf >= 0)) {
                if ((pieceAt(xf,yf) == null) && (pieceAt((xi+xf)/2,(yi+yf)/2) != null) && (pieceAt(xi,yi) != null)){
                    if (pieceAt((xi+xf)/2,(yi+yf)/2).isFire() != pieceAt(xi,yi).isFire()) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean canSelect(int x, int y) {
        if ((pieceAt(x,y) != null) && (pieceAt(x,y).isFire() == whosTurn) && !hasMoved) {
            return true;
        }
        else if ((pieceAt(x,y) == null) && (!hasMoved || moveAgain)) {
            if (lastPiece != null) {
                if (validMove(THEX,THEY,x,y) && rightDirection(THEX,THEY,x,y)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void select(int x, int y) {
        if ((lastPiece == null) || (pieceAt(x,y) != null)) {
            lastPiece = pieceAt(x,y);
        }
        else {
            if (lastPiece != pieceAt(x,y)) {
                lastPiece.move(x,y);
                hasMoved = true;
                    if (!lastPiece.isKing()) {
                        if (lastPiece.isFire()) {
                            if (validMove(x,y,x+2,y+2)) {
                                moveAgain = true;
                            }
                            else if (validMove(x,y,x-2,y+2)) {
                                moveAgain = true;
                            }
                            else {
                                moveAgain = false;
                            }
                        }
                        else {
                            if (validMove(x,y,x+2,y-2)) {
                                moveAgain = true;
                            }
                            else if (validMove(x,y,x-2,y-2)) {
                                moveAgain = true;
                            }
                            else {
                                moveAgain = false;
                            }
                        }
                    }
                    else {
                        if (validMove(x,y,x+2,y+2))      moveAgain = true;
                        else if (validMove(x,y,x-2,y+2)) moveAgain = true;
                        else if (validMove(x,y,x-2,y-2)) moveAgain = true;
                        else if (validMove(x,y,x+2,y-2)) moveAgain = true;
                        else                             moveAgain = false;
                    }
            }
            else {
                lastPiece.move(x,y);
            }
        }
    }    

    public boolean canEndTurn() {
        if (hasMoved) {
            return true;
        }
        return false;
    }

    public Piece remove(int x, int y) {
        Piece L = pieceAt(x,y);
        pieces[x][y] = null;
        return L;
    }

    public void endTurn() {
        if (whosTurn) {
            whosTurn = false;
        }
        else {
            whosTurn = true;
        }
        lastPiece.doneCapturing();
        hasMoved = false;
        lastPiece = null;
        moveAgain = false;
    }

    public String winner() {
        countFire = 0;
        countWater = 0;
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        countFire = countFire + 1;
                    }
                    else {
                        countWater = countWater + 1;
                    }
                }
            }
        }
        if ((countFire == 0) && (countWater == 0)) {
            return "No one";
        }
        else if (countFire == 0) {
            return "Water";
        }
        else if (countWater == 0){
            return "Fire";
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            drawBoard(N);
            StdDrawPlus.show(25);
            if (StdDrawPlus.mousePressed() && (!hasMoved || moveAgain)) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x,(int)y)) {
                    b.select((int)x,(int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (b.winner() != null) {
                break;
            }
        }
    }

}
