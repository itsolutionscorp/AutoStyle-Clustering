public class Board {
   /** Location of pieces. */
    private static boolean[][] pieces;
    private Piece[][] pieceMatrix;
    private boolean fireTurn = true;
    private Piece selected = null;
    private boolean canCapture = false;
    private Piece pieceToMove;
    private boolean moved = false;
    private int xCoord, yCoord;
    private boolean hasWon = false;
    private boolean fireExists = true;
    private boolean waterExists = true;
    private boolean canEndTurn = false;
    private boolean canSelect = true;
    


    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {
       if (shouldBeEmpty) {
            pieceMatrix = new Piece[8][8]; 
        } else {
            pieceMatrix = new Piece[8][8];

            for (int n = 0; n <= 7; n++) {
                for (int m = 0; m <= 7; m++){
                    if (m == 7) {
                        if (n % 2 != 0) {
                            pieceMatrix[n][m] = new Piece(false, this, n, m, "pawn");
                        }
                    }

                    if (m == 6) {
                        if (n % 2 == 0) {
                            pieceMatrix[n][m] = new Piece(false, this, n, m, "shield");
                        }
                    }

                    if (m == 5) {
                        if (n % 2 != 0) {
                            pieceMatrix[n][m] = new Piece(false, this, n, m, "bomb");
                        }
                    }

                    if (m == 2) {
                        if (n % 2 == 0) {
                            pieceMatrix[n][m] = new Piece(true, this, n, m, "bomb");
                        }
                    }

                    if (m == 1) {
                        if (n % 2 != 0) {
                            pieceMatrix[n][m] = new Piece(true, this, n, m, "shield");
                        }
                    }

                    if (m == 0) {
                        if (n % 2 == 0) {
                            pieceMatrix[n][m] = new Piece(true, this, n, m, "pawn");
                        }
                    }

                }
            }
        }
    }

    private void drawBoardAndPieces(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceMatrix[i][j] != null) {
                    if (pieceMatrix[i][j].isFire()) {
                        if (pieceMatrix[i][j].isShield()) {
                            if (pieceMatrix[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else if (pieceMatrix[i][j].isBomb()) {
                            if (pieceMatrix[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else {
                            if (pieceMatrix[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    } else if (!pieceMatrix[i][j].isFire()) {
                        if (pieceMatrix[i][j].isShield()) {
                            if (pieceMatrix[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        } else if (pieceMatrix[i][j].isBomb()) {
                            if (pieceMatrix[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        } else {
                            if (pieceMatrix[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }
    

        
        
    

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j]) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
            }
        }
    }

    private void createBoard() {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];

        if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                select((int) x, (int) y);
        }

    }

    public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        pieces = new boolean[N][N];

        b.createBoard();
        while(true) {
    
            b.drawBoardAndPieces(N);
                    
            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            } else if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();
            }
            StdDrawPlus.show(25);

        }
    }

    public void place(Piece p, int x, int y) {
        if (p == null || x > 7 || y > 7){

        } else {
            pieceMatrix[x][y] = p;
            storeXAndY(x, y);
        }    
    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0){
            return null;
        }
        return pieceMatrix[x][y];
    } 

    public Piece remove(int x, int y) {
        Piece toReturn = pieceMatrix[x][y];
        pieceMatrix[x][y] = null;
        return toReturn;
    }

    public boolean canSelect(int x, int y) {
        if (canSelect == false) {
            return false;
        }
        if (selected == null) {
            if (pieceMatrix[x][y] != null) {
                if (fireTurn == pieceMatrix[x][y].isFire()) {
                    return true;
                } else return false;
            } else return false;
        } else {
            if (pieceMatrix[x][y] == null) {
                if (selected.isKing() && Math.abs(x - xCoord) < 2 && Math.abs(y - yCoord) < 2) {
                    if (xCoord != x && yCoord != y) {
                        return true;
                    } else return false;
                } else if (Math.abs(x - xCoord) < 2 && xCoord != x) {
                    if (selected.isFire() && yCoord + 1 == y) {
                        return true;
                    } else if (!selected.isFire() && yCoord - 1 == y) {
                        return true;
                    } else return false;
                } else return false;
            } 
            else if (canCapture(x, y)) return true;
            else if (kingCanCapture(x, y)) return true;
        if (pieceMatrix[x][y].side() == selected.side() && !moved) return true;
        else return false; 
        }
    } 
    

    private boolean canCapture(int x , int y) {
        if (pieceAt(x, y) == null) {
            if (pieceAt(x + 1, y + 1) != null && pieceAt(x + 2, y + 2) != null) {
                if (pieceAt(x + 1, y + 1).isFire() && !pieceAt(x + 2, y + 2).isFire()) {
                    return true;
                }
            } else if (pieceAt(x - 1, y + 1) != null && pieceAt(x - 2, y + 2) != null) {
                if (pieceAt(x - 1, y + 1).isFire() && !pieceAt(x - 2, y + 2).isFire()) {
                    return true;
                }
            } else if (pieceAt(x + 1, y - 1) != null && pieceAt(x + 2, y - 2) != null) {
                if (!pieceAt(x + 1, y - 1).isFire() && pieceAt(x + 2, y - 2).isFire()) {
                    return true;
                }
            } else if (pieceAt(x - 1, y - 1) != null && pieceAt(x - 2, y - 2) != null) {
                if (!pieceAt(x - 1, y - 1).isFire() && pieceAt(x - 2, y - 2).isFire()) {
                    return true;
                }
            }
        }
        
        return false;
                
    }

    private boolean kingCanCapture(int x, int y) {
        if (pieceAt(x, y) == null) {
            if (pieceAt(x + 2, y + 2).isKing() && pieceAt(x + 2, y + 2).isFire()) {
                if (pieceAt(x + 1, y + 1) != null && !pieceAt(x + 1, y + 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x - 2, y + 2).isKing() && pieceAt(x - 2, y + 2).isFire()) {
                if (pieceAt(x - 1, y + 1) != null && !pieceAt(x - 1, y + 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x + 2, y - 2).isKing() && pieceAt(x + 2, y - 2).isFire()) {
                if (pieceAt(x + 1, y - 1) != null && !pieceAt(x + 1, y - 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x - 2, y - 2).isKing() && pieceAt(x - 2, y - 2).isFire()) {
                if (pieceAt(x - 1, y - 1) != null && !pieceAt(x - 1, y - 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x + 2, y + 2).isKing() && !pieceAt(x + 2, y + 2).isFire()) {
                if (pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x - 2, y + 2).isKing() && !pieceAt(x - 2, y + 2).isFire()) {
                if (pieceAt(x - 1, y + 1) != null && pieceAt(x - 1, y + 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x + 2, y - 2).isKing() && !pieceAt(x + 2, y - 2).isFire()) {
                if (pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire()) {
                    return true;
                }
            } else if (pieceAt(x - 2, y - 2).isKing() && !pieceAt(x - 2, y - 2).isFire()) {
                if (pieceAt(x - 1, y - 1) != null && pieceAt(x - 1, y - 1).isFire()) {
                    return true;
                }
            } else return false;
        } 
        return false;
         
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (!pieceMatrix[xi][yi].isKing()) {
            if (pieceAt(xi, yi).isFire()) {
                if (yf - yi == 1 && (xf - xi == 1 || xi - xf == 1)) {
                    if (pieceAt(xf, yf) == null) {
                        return true;
                    }
                } else {
                    return false;
                }
            } else if (!pieceAt(xi, yi).isFire()) {
                if (yf - yi == -1 && (xf - xi == 1 || xi - xf == 1)) {
                    if (pieceAt(xf, yf) == null) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else if (pieceMatrix[xi][yi].isKing()) {
            if ((yf - yi == 1 || yi - yf == 1) && (xf - xi == 1 || xi - xf == 1)) {
                    if (pieceAt(xf, yf) == null) {
                        return true;
                    }
            } else{
                return false;
            }
        }   
        return false;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            selected.move(x, y);
            if (selected.isKing() && !selected.hasCaptured()) {
                canSelect = false;
            }
            moved = true;
            canEndTurn = true;
        } 
        else selected = pieceAt(x, y);
        storeXAndY(x, y);
    
    }

    public boolean canEndTurn() {
        return this.canEndTurn;
    }

    public void endTurn() {
        if (fireTurn == true) {
            fireTurn = false;
        } else if (fireTurn != true) {
            fireTurn = true;
        }
        moved = false;
        selected = null;
        canEndTurn = false;
        canSelect = true;
    }

    private void storeXAndY(int x, int y) {
        xCoord = x;
        yCoord = y;
    }


    public String winner() {
        for (int n = 0; n <= 7; n++) {
            for (int m = 0; m <= 7; m++) {
                if (pieceMatrix[n][m].side() == 0) {
                    fireExists = true;
                } else {
                    fireExists = false;
                } 

                if (pieceMatrix[n][m].side() == 1) {
                    waterExists = true;
                } else {
                    waterExists = false;
                }
            }
        }
        if (waterExists == false) {
            return "Fire";
        } else if (fireExists == false) {
            return "Water";
        } else {
            return "No one";
        }
    }
}