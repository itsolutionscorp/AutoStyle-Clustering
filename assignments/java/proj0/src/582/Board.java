
public class Board {
	private boolean shouldBeEmpty;
    private Piece[][] pieces;
    private int N = 8;
    private int turnCount = 0;
    private boolean selected = false;
    private boolean moved = false;
    private int row = 0;
    private int column = 0;
    private Piece globalPiece = null;
    private int globalCoordX = 0;
    private int globalCoordY = 0;
    private int pieceCountFire = 0;
    private int pieceCountWater = 0;
    private String gameEnd;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean empty){
        shouldBeEmpty = empty;
        pieces = new Piece[N][N];    
        if (shouldBeEmpty == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j == 0) {
                        if ((i + j) % 2 == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                    }
                    if (j == 1) {
                        if ((i + j) % 2 == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                    }
                    if (j == 2) {
                        if ((i + j) % 2 == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                    }
                    if (j == 5) {
                        if ((i + j) % 2 == 0) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                    }
                    if (j == 6) {
                        if ((i + j) % 2 == 0) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                    }
                    if (j == 7) {
                        if ((i + j) % 2 == 0) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
    
        }
    }
    

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
             return null;
         }
        if (pieces[x][y] != null) {
            return pieces[x][y];
        }else{
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
            return false;
        }
        if (shouldBeEmpty == true) {
            return false;
        }
        if (turnCount % 2 == 0) {
            if (pieces[x][y] != null) {
                if (pieceAt(x, y).isFire()) {
                    if (moved == true) {
                        return false;
                    }
                    globalPiece = pieces[x][y];
                    globalCoordX = x;
                    globalCoordY = y;
                    selected = true;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (selected == true && globalPiece.isFire()) {
                    return validMove(globalCoordX, globalCoordY, x, y);
                } else {
                    return false;
                }
            }
        } else {
            if (pieces[x][y] != null) {
                if (!pieceAt(x, y).isFire()) {
                    if (moved == true) {
                        return false;
                    }
                    globalPiece = pieces[x][y];
                    globalCoordX = x;
                    globalCoordY = y;
                    selected = true;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (selected == true && !globalPiece.isFire()) {
                    return validMove(globalCoordX, globalCoordY, x, y);
                } else {
                    return false;
                }
            }
        }
    }

            

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xi > 7) || (yi > 7) || (xf < 0) || (yf < 0)) {
            return false;
        } 
        int dx = xf - xi;
        int dy = yf - yi;

        if (pieces[xf][yf] != null) {
            return false;
        }
        if (pieces[xf][yf] == null && moved == true) {
            return false;
        }
        if (!pieces[xi][yi].isKing()) {
            if (pieces[xi][yi].isFire()) {
                if (dy == 1) {
                    if (dx == 1) {
                        return true;
                    }
                    else if (dx == -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                else if (dy == 2) {
                    if (dx == 2) {
                        if ((pieces[xi+1][yi+1]) != null && (!pieces[xi+1][yi+1].isFire())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (dx == -2) {
                        if ((pieces[xi-1][yi+1]) != null && (!pieces[xi-1][yi+1].isFire())) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } 
            } else { // for non-king waters
                if (dy == -1) {
                    if (dx == 1) {
                        return true;
                    }
                    else if (dx == -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                else if (dy == -2) {
                    if (dx == 2) {
                        if ((pieces[xi+1][yi-1]) != null && (pieces[xi+1][yi-1].isFire())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (dx == -2) {
                        if ((pieces[xi-1][yi-1]) != null && (pieces[xi-1][yi-1].isFire())) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        } else{
            if (dy == 1) {
                if (dx == 1) {
                    return true;
                }
                else if (dx == -1) {
                    return true;
                } else {
                    return false;
                }
            } else if (dy == -1) {
                if (dx == 1) {
                    return true;
                }
                else if (dx == -1) {
                    return true;
                } else {
                    return false;
                }
            } else if (dy == 2) {
                if (dx == 2) {
                    if ((pieces[xi+1][yi+1]) != null && (pieces[xi+1][yi+1].isFire() != globalPiece.isFire())) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (dx == -2) {
                    if ((pieces[xi-1][yi+1]) != null && (pieces[xi-1][yi+1].isFire() != globalPiece.isFire())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if (dy == -2) {
                if (dx == 2) {
                    if ((pieces[xi+1][yi-1]) != null && (pieces[xi+1][yi-1].isFire() != globalPiece.isFire())) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (dx == -2) {
                    if ((pieces[xi-1][yi-1]) != null && (pieces[xi-1][yi-1].isFire() != globalPiece.isFire())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    // public void getSpot(int x, int y) {
    //     System.out.println(pieces[x][y]);
    // }

    public void select(int x, int y) {
        row = x;
        column = y;
        if (pieces[x][y] != null && (moved == false)) {
            globalPiece = pieces[x][y];
            globalCoordX = x;
            globalCoordY = y;
        }
        if (selected && !moved && (pieces[x][y] == null)) {
            if (validMove(globalCoordX, globalCoordY, x, y)) {
                globalPiece.move(x, y);
                if (!globalPiece.isBomb()) {
                    pieces[x][y] = globalPiece;
                }
                moved = true;
            }
        }
    }


    public void place(Piece p, int x, int y) {
        if (!(x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pieces[i][j] == p) {
                        pieces[i][j] = null;
                    }
                }
            }
        }
        pieces[x][y] = p;

    }


    public Piece remove(int x, int y) {
        if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {
            System.out.println("Out of bounds.");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("There is no piece on that square.");
            return null;
        }
        if (pieces[x][y] != null) {
            Piece temp = pieces[x][y];
            pieces[x][y] = null;
            return temp;
        }
        return null;
    }

    public  boolean canEndTurn() {
        if ((selected == true) && (moved == true)) {
            return true;
        }else{
            return false;
        }
    }

    public void endTurn() {
        if (canEndTurn()) {
            turnCount = turnCount + 1;
            selected = false;
            moved = false;
        }
        if (gameEnd != null) {
            return;
        }
    }

    public String winner() { 
        pieceCountFire = 0;
        pieceCountWater = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {    
                    if (pieces[i][j].isFire()) {
                        pieceCountFire = pieceCountFire + 1;
                    }
                    if (!pieces[i][j].isFire()) {
                        pieceCountWater = pieceCountWater + 1;
                    }
                }
            }
        }
        if ((pieceCountFire == 0) && (pieceCountWater == 0)){
            gameEnd = "No one";
            return "No one";
        } 
        if (pieceCountFire == 0) {
            gameEnd = "water";
            return "Water";
            }
        if (pieceCountWater == 0) {
            gameEnd = "Fire";
            return "Fire";
        }
        return null;
    }
        
    

    private void drawBoard(int N) {  
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i+j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    if (pieces != null && selected) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(row + .5, column + .5, .5); 
                    }
                    if (pieces[row][column] == null) {
                        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                        StdDrawPlus.filledSquare(row + .5, column + .5, .5);
                    }
                    // Checks for non-king pieces both water and fire
                    // System.out.println(pieces[row][column]);
                    if (pieces[i][j] != null) {
                        if ((!pieces[i][j].isShield()) && (!pieces[i][j].isBomb()) && (!pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                        if ((pieces[i][j].isShield()) && (pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);   
                        }
                        if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);   
                        }
                        if ((!pieces[i][j].isShield()) && (!pieces[i][j].isBomb()) && (!pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);   
                        }
                        if ((pieces[i][j].isShield()) && (!pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);   
                        }
                        if ((pieces[i][j].isBomb()) && (!pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);   
                        }
                        
                        // Checks for king pieces both fire and water
                        if ((!pieces[i][j].isShield()) && (!pieces[i][j].isBomb()) && (pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                        if ((pieces[i][j].isShield()) && (pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        if ((pieces[i][j].isBomb()) && (pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }
                        if ((!pieces[i][j].isShield()) && (!pieces[i][j].isBomb()) && (pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                        if ((pieces[i][j].isShield()) && (pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        }
                        if ((pieces[i][j].isBomb()) && (pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        }
                    }
                    if (pieces[row][column] != null) {
                        if ((!pieces[row][column].isShield()) && (!pieces[row][column].isBomb()) && (!pieces[row][column].isKing()) && (pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/pawn-fire.png", 1, 1);
                        }
                        if ((pieces[row][column].isShield()) && (pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/shield-fire.png", 1, 1);   
                        }
                        if ((pieces[row][column].isBomb()) && (pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/bomb-fire.png", 1, 1);   
                        }
                        if ((!pieces[row][column].isShield()) && (!pieces[row][column].isBomb()) && (!pieces[row][column].isKing()) && (!pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/pawn-water.png", 1, 1);   
                        }
                        if ((pieces[row][column].isShield()) && (!pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/shield-water.png", 1, 1);   
                        }
                        if ((pieces[row][column].isBomb()) && (!pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/bomb-water.png", 1, 1);   
                        }
                        
                        // Checks for king pieces both fire and water
                        if ((!pieces[row][column].isShield()) && (!pieces[row][column].isBomb()) && (pieces[row][column].isKing()) && (pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                        if ((pieces[row][column].isShield()) && (pieces[row][column].isKing()) && (pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        if ((pieces[row][column].isBomb()) && (pieces[row][column].isKing()) && (pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }
                        if ((!pieces[row][column].isShield()) && (!pieces[row][column].isBomb()) && (pieces[row][column].isKing()) && (!pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                        if ((pieces[row][column].isShield()) && (pieces[row][column].isKing()) && (!pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/shield-water-crowned.png", 1, 1);
                        }
                        if ((pieces[row][column].isBomb()) && (pieces[row][column].isKing()) && (!pieces[row][column].isFire())) {
                            StdDrawPlus.picture(row + .5, column + .5, "img/bomb-water-crowned.png", 1, 1);
                        }
                    }
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
    }    
    //     for (int i = 0; i < N; i++) {
    //         for (int j = 0; j < N; j++) {
    //             if ((i + j) % 2 == 0) {
    //                 StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    //             }else{ 
    //                 StdDrawPlus.setPenColor(StdDrawPlus.RED);
    //             }
    //             StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    //             StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    //             if (pieces != null) {    
    //                 if (selected == true) {
    //                     StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    //                     StdDrawPlus.filledSquare(row + .5, column + .5, .5);
    //                     if (pieces[row][column] == null) {
    //                         if ((i + j) % 2 == 0) {
    //                             StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    //                         }else{ 
    //                             StdDrawPlus.setPenColor(StdDrawPlus.RED);
    //                         }
    //                         StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    //                         StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    //                     } else if (pieces[row][column].isFire()) {
    //                         if (pieces[row][column].isKing()) {
    //                             if (pieces[row][column].isBomb()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/bomb-fire-crowned.png", 1, 1);
    //                             }
    //                             if (pieces[row][column].isShield()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/shield-fire-crowned.png", 1, 1);
    //                             }else{
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/pawn-fire-crowned.png", 1, 1);
    //                             }
    //                         }else{
    //                             if (pieces[row][column].isBomb()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/bomb-fire.png", 1, 1);
    //                             }
    //                             if (pieces[row][column].isShield()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/shield-fire.png", 1, 1);
    //                             }
    //                             if (!pieces[row][column].isBomb() && !pieces[row][column].isShield()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/pawn-fire.png", 1, 1);
    //                             }
    //                         }
    //                     }else{
    //                         if (pieces[row][column].isKing()) {
    //                             if (pieces[row][column].isBomb()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/bomb-water-crowned.png", 1, 1);
    //                             }
    //                             if (pieces[row][column].isShield()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/shield-water-crowned.png", 1, 1);
    //                             }else{
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/pawn-water-crowned.png", 1, 1);
    //                             }
    //                         }else{
    //                             if (pieces[row][column].isBomb()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/bomb-water.png", 1, 1);
    //                             }
    //                             if (pieces[row][column].isShield()){
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/shield-water.png", 1, 1);
    //                             }
    //                             if (!pieces[row][column].isBomb() && !pieces[row][column].isShield()) {
    //                                 StdDrawPlus.picture(row + .5, column + .5, "img/pawn-water.png", 1, 1);
    //                             }
    //                         }
    //                     }
    //                 }
    //                 Piece currentPiece = pieces[i][j];
    //                 if (currentPiece != null) {    
    //                     if (currentPiece.isFire()) {
    //                         if (currentPiece.isKing()) {
    //                             if (currentPiece.isBomb()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
    //                             }
    //                             if (currentPiece.isShield()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
    //                             }else{
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
    //                             }
    //                         }else{
    //                             if (currentPiece.isBomb()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    //                             }
    //                             if (currentPiece.isShield()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
    //                             }
    //                             if (!currentPiece.isBomb() && !currentPiece.isShield()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
    //                             }
    //                         }
    //                     }else{
    //                         if (currentPiece.isKing()) {
    //                             if (currentPiece.isBomb()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
    //                             }
    //                             if (currentPiece.isShield()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
    //                             }else{
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
    //                             }
    //                         }else{
    //                             if (currentPiece.isBomb()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
    //                             }
    //                             if (currentPiece.isShield()){
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
    //                             }
    //                             if (!currentPiece.isBomb() && !currentPiece.isShield()) {
    //                                 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
    //                             }
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N); 
        Board board = new Board(Boolean.parseBoolean(args[0]));

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int newX = (int) x / 1;
                int newY = (int) y / 1;
                if (board.canSelect(newX, newY)) {
                    board.select(newX, newY);
                }
            }            
            StdDrawPlus.show(100);
            if (StdDrawPlus.isSpacePressed()) {
                board.endTurn();
            }
        }
    }
}