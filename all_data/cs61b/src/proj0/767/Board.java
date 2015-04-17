public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private static final int GRID_SIZE = 8;
    private int N; 
    private boolean isTurn = true; 
    private boolean hasSelectedPiece = false; 
    private boolean hasMovedPiece = false; 
    private boolean hasCapturedPiece = false;
    private int fireCheckers;
    private int waterCheckers; 
    private Piece selectedPiece;
    private int currX; 
    private int currY; 

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[GRID_SIZE][GRID_SIZE];
        if(!shouldBeEmpty) {
            for (int col = 0; col < GRID_SIZE; col ++) {
                if (col % 2 == 0){
                    pieces[col][0] = new Piece(true, this, col, 0, "pawn");
                    pieces[col][2] = new Piece(true, this, col, 2, "bomb");
                    pieces[col][6] = new Piece(false, this, col, 6, "shield");
                }
                else {
                    pieces[col][1] = new Piece(true, this, col, 1, "shield");
                    pieces[col][5] = new Piece(false, this, col, 5, "bomb");
                    pieces[col][7] = new Piece(false, this, col, 7, "pawn");
                }
            }
            fireCheckers = 12;
            waterCheckers = 12;
        } 
        else {
            fireCheckers = 0;
            waterCheckers = 0;
        }
    }

    public static void main(String[] args) {
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, GRID_SIZE);
        StdDrawPlus.setYscale(0, GRID_SIZE);
        board.drawBoard(GRID_SIZE);
        int x = 0; 
        int y = 0; 
        while (board.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                x = (int)StdDrawPlus.mouseX(); 
                y = (int)StdDrawPlus.mouseY();
                if (board.canSelect(x,y)) {
                    board.select(x,y); 
                    Piece piece = board.pieces[x][y];
                    if (piece != null) {
                        board.drawIcon(x,y);
                    }
                    if (board.hasMovedPiece) {
                        board.drawBoard(GRID_SIZE); 
                    }
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                    if (board.canEndTurn()) {
                        board.endTurn();
                        Piece piece = board.pieceAt(board.currX,board.currY);
                    }
            }
            StdDrawPlus.show(10);
            
        }
        System.out.println("winner");
    }    

    private void drawBoard (int N){
        for (int i = 0; i < N; i++) { //rows
            for (int j = 0; j < N; j++) { //columns
                if ((i + j) % 2 == 0){ 
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED); }
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5); 
                    drawIcon(i,j);
                }
            }
        }

        private void drawIcon(int i, int j) {
            Piece piece =  pieces[i][j];
            if (piece != null) {
                String fileName = "img/";
                if (piece.isBomb()) {
                    fileName += "bomb";
                } else if (piece.isShield()) {
                    fileName += "shield";
                } else {
                    fileName += "pawn";
                }
                fileName += "-";
                if (piece.isFire()){
                    fileName += "fire";
                } 
                else {
                    fileName += "water";
                }
                if (piece.isKing()) {
                    fileName += "-crowned";
                }
                StdDrawPlus.picture(i + .5, j + .5, fileName + ".png", 1, 1);
            }
        }

    private static void drawEmptyBoard() {
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY); }
                    else  {StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
}

    public Piece pieceAt(int x, int y){
       
        if (x >= GRID_SIZE || y >= GRID_SIZE || x < 0 || y < 0 || (x+y) %2 != 0) {
            return null; 
        }
         Piece piece = pieces[x][y];
        return piece;
    }
    
    public boolean canSelect(int x, int y) {
        Piece piece = pieceAt(x, y);
        if(x < 0 || y < 0 || x >= GRID_SIZE || y >= GRID_SIZE || (x+y)%2 != 0) {
            System.out.println("VALID MOVE MULTICAPTURE 1");
            return false;
        }    
        if (piece != null && (isTurn == piece.isFire())) { 
            System.out.println("VALID MOVE MULTICAPTURE 2");
            if (!hasSelectedPiece || (hasSelectedPiece && !hasMovedPiece)) {
                System.out.println("VALID MOVE MULTICAPTURE 3");
                return true; 
            }
        }
        if (piece == null) {
            System.out.println("VALID MOVE MULTICAPTURE 4");
            if (selectedPiece != null && selectedPiece.hasCaptured() && validMove(currX,currY, x,y)) {
                System.out.println("VALID MOVE MULTICAPTURE 5");
                return true; 
            }
            if (selectedPiece != null && !hasMovedPiece && validMove(currX,currY,x,y)) {
                System.out.println("VALID MOVE MULTICAPTURE 6");
                return true; 
            } 
            if (selectedPiece != null && selectedPiece.hasCaptured() && validMove(currX,currY,x,y)) {
                System.out.println("VALID MOVE MULTICAPTURE7");
                return true; 
            }
        }
        System.out.println("hasselectedpiece" + hasSelectedPiece); 
        System.out.println("VALID MOVE MULTICAPTURE 8");
        return false;
    }
 
    private boolean validMove(int xi, int yi, int xf, int yf) {  
        Piece pi = pieceAt(xi, yi);
        Piece pf = pieceAt(xf, yf);
        int differencey = yf - yi; 
        int differencex = Math.abs(xf - xi); 
        int differencexx = xf - xi; 

        if (pi != null && pi.isKing() && Math.abs(differencey) == 2 && differencex == 2 && pieceAt((xf+xi)/2, (yf+yi)/2) != null) {
            return true; 
        }
        else if (differencex == 1) {
            if (differencey == 1 && pi.isFire()) {
                return true; 
            }
            else if (differencey == -1  && !pi.isFire()) {
                return true; 
            }
            if (Math.abs(differencey) == 1  && pi.isKing()) {
                return true; 
            }
        }
        if (differencexx == 2) {
            if (pieces[xi + 1][yi + 1] != null && !pieces[xi+1][yi+1].isFire() && (differencey == 2) && pi.isFire()) {
                return true; 
            }
            if (pieces[xi + 1][yi -1] != null && pieces[xi+1][yi-1].isFire() && (differencey == -2) && !pi.isFire()) {
                return true; 
            }
        }
        else if (differencexx == -2) {
            if (pieces[xi - 1][yi + 1] != null && !pieces[xi - 1][yi + 1].isFire() && (differencey == 2) && pi.isFire()) {
                return true; 
            }
            if (pieces[xi - 1][yi - 1] != null && pieces[xi-1][yi-1].isFire() && (differencey == -2) && !pi.isFire()) {
                return true; 
            }
        }
        return false; 
    }

    public void select(int x, int y) {
        System.out.println("currX:" + x + "currY" + y);
        if (pieceAt(x,y) != null && !hasSelectedPiece) {
            System.out.println("problem here!");
            selectedPiece = pieceAt(x,y);
            currX = x; 
            currY = y;
            hasSelectedPiece = true; 
        } else if (pieceAt(x,y) == null) {
            System.out.println("problem HERE");
            if(selectedPiece != null)
                selectedPiece.move(x,y);
                currX = x; 
                currY = y;
                hasMovedPiece = true; 
        } 
        int startX = -1; 
        int endX = 1; 
        int startY = -1; 
        int endY = 1; 
        if (x == 0) {
        startX = 0;
        }
        if (x == GRID_SIZE - 1) {
            endX = 0; 
        }
        if (y == 0) {
            startY = 0;
        }
        if (y == GRID_SIZE - 1) {
            endY = 0; 
        }
        if (selectedPiece.hasCaptured() && (selectedPiece.isBomb())) {
            System.out.println("ENTERED BOMB STATEMENT");
            for (int i = startX; i <= endX; i ++) {
                for ( int j = startY; j <= endY; j ++) {
                    if (pieces[x+i][y+j] != null && !pieces[x + i][y + j].isShield()) {
                    remove(x + i, y + j); }
                }
            }
        }
}

    public void place(Piece p, int x, int y) {
            if (p.isFire()) {
                fireCheckers ++; 
            }
            else {
                waterCheckers ++; 
            }
            System.out.println("placed @ " + x + ", " + y);
            pieces[x][y] = p;
        }

    public Piece remove(int x, int y) {
        Piece p = pieces[x][y];
        if (p == null) {
            return null;
        }
        if (p.isFire()) {
            fireCheckers --; 
        }
        else {
            waterCheckers --; 
        }
        System.out.println("removed piece");
        System.out.println(x + ", " + y);
        pieces[x][y] = null;
        return p;

    }

    public boolean canEndTurn() {
        if (selectedPiece != null && (selectedPiece.hasCaptured() || hasMovedPiece)) {
            return true;
        }
        return false; 
    }

    public void endTurn(){
        if (isTurn) {
                        isTurn = false; 
                    }
            else{
                        isTurn = true; 
                    }
        System.out.println("PROBLEM HERE");
        currX = 0; 
        currY = 1;         
        selectedPiece.doneCapturing();  
        hasMovedPiece = false; 
        hasSelectedPiece = false;
        selectedPiece  = null; 

    }

    public String winner(){
        if (fireCheckers == 12 && waterCheckers == 12) {
            return null; 
        }
        if (fireCheckers + waterCheckers ==0) {
            return "No one";
        }
        else if (fireCheckers == 0){
            return "Water";
        }
        else if (waterCheckers == 0) {
            return "Fire";
        }
        return null;
    }

    
}
