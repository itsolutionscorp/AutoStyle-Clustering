public class Board {
    private boolean fireypieces = true;
    private boolean empty;
    private Piece stored;
    private int storedxpos;
    private int storedypos;
    private Piece temporary;
    private int firenumber;
    private int waternumber;
    private Board updateBoard;
    private Piece[][] pieces;
    private boolean moved = false;
    private int N = 8;
    private boolean captured;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {
        firenumber = 0;
        waternumber = 0;
        pieces = new Piece[8][8];
        empty = shouldBeEmpty;
        if (empty == false) {
            firenumber = 12;
            waternumber = 12;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((j == 0) && ((i % 2) == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if ((j == 1) && ((i % 2) == 1)) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if ((j == 2) && ((i % 2) == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    if ((j == 5) && ((i % 2) == 1)) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if ((j == 6) && ((i % 2) == 0)) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if ((j == 7) && ((i % 2) == 1)) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    }

    private void drawBoard(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
        /*draw pieces */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((j == 0) && ((i % 2) == 0)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                }
                if ((j == 1) && ((i % 2) == 1)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                }
                if ((j == 2) && ((i % 2) == 0)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
                if ((j == 5) && ((i % 2) == 1)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                }
                if ((j == 6) && ((i % 2) == 0)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                }
                if ((j == 7) && ((i % 2) == 1)) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
            }
        }
    }


    private void updateBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isKing()) {
                        if (pieces[i][j].isFire()){
                            if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }                            
                        } else {
                            if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }                            
                    } else { /*not king */
                        if (pieces[i][j].isFire()){
                            if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            } else if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);                 
                            }
                        }
                    }
                } else {

                }
            }
        }
    }
           

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }   
        if (pieces[x][y] != null) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    public void place (Piece p, int x, int y) {
        if (p != null) {
            if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
            pieces[x][y] = p;
            }
            if (p.isFire()) {
                firenumber += 1;
            } else {
                waternumber += 1;
            }
        }
    }

    public Piece remove(int x, int y) {
        if ((x > 7 || y > 7 || x < 0 || y < 0)) {
            System.out.println ("Out of bounds");
            return null;
        } if (pieces[x][y] == null) {
            System.out.println ("No piece is removed");
            return null;
        } else {
            if (pieceAt(x, y).isFire()) {
                firenumber -= 1;
            } else {
                waternumber -= 1;
            }
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieces[xi][yi].isFire() == true) {
            if (yf == yi + 1) {
                if ((xf == xi + 1) || (xf == xi - 1)) {
                    return true;
                }
            }
            if (yf == yi + 2) { /*captures*/
                if (xf == xi + 2) {
                    if ((pieces[xi + 1][yi + 1].isFire() == false) && (pieces[xi + 1][yi + 1]!= null)) { 
                        return true;
                        }
                    else{
                        return false;
                        }
                    }
                if (xf == xi - 2) {
                    if ((pieces[xi - 1][yi + 1].isFire() == false) && (pieces[xi - 1][yi+1]!= null)) { 
                        return true;
                        }                  
                    }   
                }
            }

        if (pieces[xi][yi].isFire() == false) {
            if (yf == yi - 1) {
                if ((xf == xi + 1) || (xf == xi - 1)) {
                    return true;
                }
            }
            if (yf == yi - 2) { /*captures*/
                if (xf == xi + 2) {
                    if ((pieces[xi + 1][yi - 1].isFire() == true) && (pieces[xi + 1][yi -1] != null)){ 
                        return true;
                        }
                    else{
                        return false;
                        }
                    }
                if (xf == xi - 2) {
                    if ((pieces[xi - 1][yi - 1].isFire() == true) && (pieces[xi -1][yi-1] != null)) { 
                        return true;
                    }else{
                        return false;
                        }         
                    }   
                }  
            }
        return false;
    }


    // private boolean validCapture(int xi, int yi) {
    //     if (pieces[xi][yi].isFire() == true) {
    //         int yf = yi + 2;
    //         int xf = xi + 2;
    //         if ((pieces[xi + 1][yi + 1].isFire()) != null) {
    //             if ((pieces[xi + 1][yi + 1].isFire()) == false) { 
    //                 return true;
    //                 }
    //             int xf = xi - 2;
    //         if ((pieces[xi + 1][yi + 1].isFire()) != null) {
    //             if ((pieces[xi + 1][yi - 1].isFire()) == false) { 
    //                 return true;
    //                 }                  
    //             }   
    //     if (pieces[xi][yi].isFire() == false) {
    //         int yf = yi - 2; /*captures*/
    //         int xf = xi + 2;
    //         if ((pieces[xi + 1][yi - 1].isFire()) == true) { 
    //             return true;
    //             }
    //         int xf == xi - 2;
    //         if ((pieces[xi - 1][yi - 1].isFire()) == true) { 
    //             return true;
    //             }                    
    //     } else {
    //         return false
    //     }
    // }

    public boolean canSelect(int x, int y) {
        if (x <= 7 && y <= 7 && x >= 0 && y >= 0) {
            if (pieceAt(x, y) != null) {/*if you clicked a  piece */
                if (fireypieces == pieces[x][y].isFire()) {
                    if (moved == false) {
                        return true;
                    }else {
                        return false;
                        }
                    }
            } else {/*clicked a space */
                if (stored == null) {/*didnt click before */ 
                    return false;
                } else {
                    return validMove(storedxpos, storedxpos, x, y);
                }
            }
        }
        return false;
        }
    //     if (pieceAt(x, y) != null) { /*if you clicked a  piece */
    //         if (captured == true) {
    //             return false;
    //         }if (stored == null) /*did click before */ {
    //             return fireypieces == pieces[x][y].isFire();
    //         }else 
    //             {return false;
    //                 }
    //         }
    //     if (pieceAt(x, y) == null) { /*clicked a space */
    //         if (stored != null) {
    //             return validMove(storedxpos, storedypos, x, y);
    //         }
    //         else {
    //             return false;
    //         }
    //     } else {
    //         return false;
    //     }
    // }


    public void select(int x, int y) { /*assume you can select */
        temporary = pieceAt(x, y);
        if (temporary != null) { /*if you clicked on a piece */
            storedxpos = x;
            storedypos = y;
            stored = temporary;                
        } else { /*if you clicked on a space */
                moved = true;
                stored.move(x, y);
            // while (validMove(storedxpos, storedypos, 
            // }
        }
    }

    public boolean canEndTurn() {
        // if (stored != null) {
        //     if (stored.hasCaptured()) {
        //         return true;
        //     } 
        if (moved = true) {
            return true;
        } else {
            return false;
        }
    }


    public void endTurn() {
        fireypieces = !fireypieces;
        moved = false;
        if (stored != null) {
            stored.doneCapturing();
            }
        stored = null;
        captured = false;
        }

    public String winner() {
        if ((firenumber == 0) && (waternumber == 0)) {
            return "No one";
        } if ((firenumber == 0) && (waternumber >0)) {
            return "Water";
        } if ((waternumber == 0) && (firenumber > 0)) {
            return "Fire";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        board.drawBoard(N);
        while (board.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                    if (board.canSelect(x, y)) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                        board.select(x, y);
                    }
             }else if (StdDrawPlus.isSpacePressed()) {
                    if (board.canEndTurn()) {
                        board.endTurn();
                    }
                }
            StdDrawPlus.show(100);
            board.updateBoard();
        }            
    }  
}
