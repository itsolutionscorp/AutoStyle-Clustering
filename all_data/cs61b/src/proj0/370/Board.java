/** 
 *  @author Josh Hug
 */

public class Board {
    /** Location of pieces. */
    private static Piece[][] pieces;
    private boolean fire_turn = true;
    private Piece pieceSelected;
    private boolean pieceMoved = false;
    private boolean moveAgain = false;
    private int pieceSelected_x;
    private int pieceSelected_y;
    private int highlightX = 8;
    private int highlightY = -8;

    public Piece pieceAt(int x, int y){
        if ((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)){
            if (pieces[x][y] != null){
                return pieces[x][y];
            }
        }
        return null;
    }

    private boolean checkPlayerPiece(Piece piece, boolean turn){
        if (piece.isFire() == turn){
            return true;
        }
        return false;
    }


    private boolean validMoveFire(Piece piece, int x, int y){
        //check if it is king
        int xPos = pieceSelected_x;
        int yPos = pieceSelected_y;
        if ((x == xPos) || (y <= yPos)){
            return false;
        } else if (((x == xPos + 1)  || (x == xPos - 1)) && (y == yPos + 1) && !moveAgain){
            return true;
        } else if ((x == xPos + 2) && (y == yPos + 2)) {
            if ((pieceAt(xPos + 1, yPos + 1) != null) && (!pieceAt(xPos + 1, yPos + 1).isFire())) {
                return true;
            } else {
                return false;
            }
        } else if ((x == xPos - 2) && (y == yPos + 2)) {
            if ((pieceAt(xPos - 1, yPos + 1) != null) && (!pieceAt(xPos - 1, yPos + 1).isFire())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validMoveWater(Piece piece, int x, int y){
        int xPos = pieceSelected_x;
        int yPos = pieceSelected_y;
        if ((x == xPos) || (y >= yPos)){
            return false;
        } else if (((x == xPos + 1)  || (x == xPos - 1)) && (y == yPos - 1) && !moveAgain){
            return true;
        } else if ((x == xPos + 2) && (y == yPos - 2)) {
            if ((pieceAt(xPos + 1, yPos - 1) != null) && (pieceAt(xPos + 1, yPos - 1).isFire())) {
                return true;
            } else {
                return false;
            }
        } else if ((x == xPos - 2) && (y == yPos - 2)) {
            if ((pieceAt(xPos - 1, yPos - 1) != null) && (pieceAt(xPos - 1, yPos - 1).isFire())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validMoveKing(Piece piece, int x, int y, boolean is_fire){
        int xPos = pieceSelected_x;
        int yPos = pieceSelected_y;
        if (((x == xPos + 1)  || (x == xPos - 1)) && (y == yPos + 1) && !moveAgain) {
            return true;

        } else if (((x == xPos + 1)  || (x == xPos - 1)) && (y == yPos - 1) && !moveAgain) {
            return true;

        } else if ((x == xPos + 2) && (y == yPos + 2)) {
            if ((pieceAt(xPos + 1, yPos + 1) != null) && (pieceAt(xPos + 1, yPos + 1).isFire() != is_fire)) {
                return true;
            } else {
                return false;
            }
        } else if ((x == xPos - 2) && (y == yPos + 2)) {
            if ((pieceAt(xPos - 1, yPos + 1) != null) && (pieceAt(xPos - 1, yPos + 1).isFire() != is_fire)) {
                return true;
            } else {
                return false;
            }
        } else if ((x == xPos + 2) && (y == yPos - 2)) {
            if ((pieceAt(xPos + 1, yPos - 1) != null) && (pieceAt(xPos + 1, yPos - 1).isFire() != is_fire)) {
                return true;
            } else {
                return false;
            }
        } else if ((x == xPos - 2) && (y == yPos - 2)) {
            if ((pieceAt(xPos - 1, yPos - 1) != null) && (pieceAt(xPos - 1, yPos - 1).isFire() != is_fire)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean canSelect(int x, int y){
        if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
            return false;
        }
        if ((pieceAt(x, y) != null) && !moveAgain) {
            if (checkPlayerPiece(pieceAt(x, y), fire_turn) && !pieceMoved) {
                return true;
            } else {
                return false;
            }
        } else if ((!pieceMoved || moveAgain) && (pieceSelected != null)) {
            if (pieceSelected.isFire()){
                if (pieceSelected.isKing()) {
                    return validMoveKing(pieceSelected, x, y, true);
                } else {  
                    return validMoveFire(pieceSelected, x, y);
                }
            } else {
                if (pieceSelected.isKing()) {
                    return validMoveKing(pieceSelected, x, y, false);
                } else {
                    return validMoveWater(pieceSelected, x, y);
                }
            }
        }
        return false;   
    }

    public void select(int x, int y) {
        highlightX = x;
        highlightY = y;
        if (pieceAt(x, y) == null) {
            pieceSelected.move(x, y);
            pieceMoved = true;
            pieceSelected_x = x;
            pieceSelected_y = y;
            if (pieceSelected.hasCaptured() && !pieceSelected.isBomb()) {
                moveAgain = true;
            }
        } else {
            pieceSelected = pieceAt(x, y);
            pieceSelected_x = x;
            pieceSelected_y = y;
        }
        
    }

    public void place(Piece p, int x, int y){
         if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
            pieces[x][y] = p;
        } 
    }

    public Piece remove(int x, int y) {
        if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
            System.out.println("cant remove piece because out of bounds");
            return null;
        }
        if (pieces[x][y] != null){
            Piece tmp = pieces[x][y];
            pieces[x][y] = null;
            return tmp;
        } else {
            System.out.println("no piece at position given " + x + ' ' + y);
            return null;
        }
    }

    public boolean canEndTurn() {
        if (pieceMoved) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        if (fire_turn){
            fire_turn = false;
        } else {
            fire_turn = true;
        }
        pieceSelected.doneCapturing();
        pieceSelected = null;
        pieceMoved = false;
        highlightY = 8;
        highlightX = 8;
        moveAgain = false;
    }

    public String winner(){
        int fire = 0;
        int water = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null){
                    if (pieces[i][j].isFire()){
                        fire++;
                    } else {
                        water++;
                    }
                }
            }
        }
        if ((fire == 0) && (water == 0)) {
            return "No one";
        } else if ((fire == 0) && (water != 0)) {
            return "Water";
        } else if ((fire != 0) && (water == 0)) {
            return "Fire";
        } else {
            return null;
        }
    }

    private void generateStart(){
         //create red pawns
        for (int i = 0; i<8; i++) {
            if (i%2 == 0){
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            }
        }
        //create red bombs
        for (int i = 0; i<8; i++) {
            if (i%2 == 0){
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            }
        }
        //create red shields
        for (int i = 0; i<8; i++) {
            if (i%2 == 1){
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
            }
        }
        //create blue pawns
        for (int i = 0; i<8; i++) {
            if (i%2 == 1){
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }
        }
        //create blue bombs
        for (int i = 0; i<8; i++) {
            if (i%2 == 1){
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            }
        }
        //create blue shields
        for (int i = 0; i<8; i++) {
            if (i%2 == 0){
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (!shouldBeEmpty){
            generateStart();
        }
    }

    private static void drawBoard(Board board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((board.highlightX == i) && (board.highlightY == j)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);


                if (pieces[i][j] != null) {
                    Piece piece = pieces[i][j];
                    if (piece.isKing()) {
                        if (piece.isBomb()){
                            if (piece.isFire()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                        } else if (piece.isShield()){
                            if (piece.isFire()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                        } else {
                            if (piece.isFire()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }

                    } else {
                        if (piece.isBomb()){
                            if (piece.isFire()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        } else if (piece.isShield()){
                            if (piece.isFire()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        } else {
                            if (piece.isFire()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board board = new Board(false);
        while (true){
            drawBoard(board);

            if (StdDrawPlus.mousePressed()) {
                double xDouble = StdDrawPlus.mouseX();
                double yDouble = StdDrawPlus.mouseY();
                int x = (int) xDouble;
                int y = (int) yDouble;
                if (board.canSelect(x, y)){
                    board.select(x, y);
                }
            }      

            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }

            if (board.winner() != null){
                System.out.println(board.winner());
                return;
            }


            StdDrawPlus.show(10);

        }
    }
}