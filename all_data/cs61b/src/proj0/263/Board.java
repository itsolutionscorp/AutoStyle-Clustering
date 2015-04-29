public class Board {
    /** Location of pieces. */
    private static Piece[][] pieces;
    private static Board board1;
    private static Piece pieceselected;
    private boolean p1turn = true;
    private boolean pieceselected2 = false;
    private boolean moved = false;
    private boolean hascaptured = false;
    private int prevx;
    private int prevy;
    private int selectedX;
    private int selectedY;
    

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void pichelper(int i, int j) {
        if(pieces[i][j] != null) {
            StdDrawPlus.picture (i + 0.5, j + 0.5, returnimage(pieces[i][j]), 1 ,1);
        }

    }


    private static void drawBoard(int N, int number, int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + 0.5, j + 0.5, .5);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                pichelper(i, j);
            }
        }
        if (number == 1) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5); 
            pichelper(x,y);
        }

    }


    private static String returnimage(Piece p) {
        if (p.isKing()){
            if (p.isFire() == true && p.isBomb() == true && p.isKing() == true) {
             return "img/bomb-fire-crowned.png";
         } else if (p.isFire() == false && p.isBomb() == true && p.isKing() == true) {
             return "img/bomb-water-crowned.png";
         } else if (p.isFire() == true && p.isShield() == false && p.isBomb() == false && p.isKing() == true) {
             return "img/pawn-fire-crowned.png";
         } else if (p.isFire() == false && p.isShield() == false && p.isBomb() == false && p.isKing() == true) {
             return "img/pawn-water-crowned.png";
         } else if (p.isFire() == true && p.isShield() == true && p.isKing() == true) {
             return "img/shield-fire-crowned.png";
         } else if (p.isFire() == false && p.isShield() == true && p.isKing() == true) {
             return "img/shield-water-crowned.png";
        }
    }
        if (p.isFire() == true && p.isShield() == false && p.isBomb() == false) {
            return "img/pawn-fire.png";
        } else if (p.isFire() == true && p.isShield() == true) {
            return "img/shield-fire.png";
        } else if (p.isFire() == true && p.isBomb() == true) {
            return "img/bomb-fire.png";
        } else if (p.isFire() == false && p.isShield() == false && p.isBomb() == false) {
            return "img/pawn-water.png";
        } else if (p.isFire() == false && p.isShield() == true) {
            return "img/shield-water.png";
        } else if (p.isFire() == false && p.isBomb() == true) {
            return "img/bomb-water.png";
        }

        else {
            return null;
        }
    }



    public static void main(String[] args) {
        int N = 8;
        Board board1 = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        board1.drawBoard(N, 0, 0, 0);
       
        while(true) {
            StdDrawPlus.show(100);
            if (StdDrawPlus.mousePressed()) {
                double xx = StdDrawPlus.mouseX();
                double yy = StdDrawPlus.mouseY();
                int x = (int) xx;
                int y = (int) yy;

                if (board1.canSelect(x, y) == true) {
                    board1.drawBoard(N, 1, x, y);
                    board1.select(x, y);
                    board1.drawBoard(N, 1, board1.selectedX, board1.selectedY);

                }         
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (board1.canEndTurn()) {
                    board1.endTurn();
                    board1.drawBoard(N, 0, 0, 0);

                    }
                }

            board1.winner();
        }
    }

    public Board(boolean shouldBeEmpty) { 
        if (shouldBeEmpty == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    pieces = new Piece[8][8];
                    pieces[i][j] = null;
                }
            }
        }
        else {
        pieces = new Piece[8][8];
        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
        pieces[7][1] = new Piece(true, this, 7, 1, "shield");

        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
        pieces[6][6] = new Piece(false, this, 6, 6, "shield");

        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
        }
    }

    public Piece pieceAt(int x, int y) {  
        if (y < 0 || y > 7 || x > 7 || x < 0) {  
            return null;
        } else if (pieces[x][y] == null) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public void place(Piece p, int x, int y) {
        if (y < 0 || y > 7 || x > 7 || x < 0) {
            return;
        } else if (pieceAt(x, y) != null) {
            remove (x, y);
            pieces[x][y] = p;
        } else {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {  
        if (y < 0 || y > 7 || x > 7 || x < 0) {
            System.out.println("Out of bounds!");
            return null;
            
        } else if (pieceAt(x,y) == null) {
            System.out.println("No piece there!");
            return null;

        } else {
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }
        
    }


    private boolean canIMultiFire(int x, int y) {
        Piece now = pieces[prevx][prevy];
        Piece mid = pieces[Math.abs(x + prevx)/2][Math.abs(y + prevy)/2];
        if(now != null){
        
            if (now.isKing() == true) {
                if ((prevx - 2 >= 0 && prevy + 2 <= 7) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                    return true;
                }
                else if ((prevx + 2 <= 7 && prevy + 2 <= 7) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                    return true;
                }
                else if ((prevx + 2 <= 7&& prevy - 2 >= 0) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                    return true;
                } 
                else if ((prevx - 2 >= 0 && prevy - 2 >= 0) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                    return true;
                }
        }
            else if (now.isKing() == false) {
                if (((prevx - 2  >= 0 && prevy + 2 <= 7)) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                    return true;
                }
                else if (((prevx + 2 <= 7 && prevy + 2 <= 7)) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                    return true;
                }

        }
    }
    return false;
}

    private boolean canIMultiWater(int x, int y) {
        Piece now = pieces[prevx][prevy];
        Piece mid = pieces[Math.abs(x + prevx)/2][Math.abs(y + prevy)/2];
        if(now != null){

        if (now.isKing() == true) {
            if ((prevx - 2 >= 0 && prevy + 2 <= 7) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                return true;
            }
            else if ((prevx + 2 <= 7 && prevy + 2 <= 7) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                return true;
            }
            else if ((prevx + 2 <= 7 && prevy - 2 >= 0) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                return true;
            } 
            else if ((prevx - 2 >= 0 && prevy - 2 >= 0) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                return true;
            }
        }
        else if (now.isKing() == false) {
            if (((prevx - 2 >= 0) && (prevy - 2 >= 0)) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                return true;
            }
            else if (((prevx + 2 <= 7) && (prevy - 2 >=0)) && mid != null && (now.isFire() != mid.isFire()) && validMove(prevx, prevy, x, y)) {
                return true;
            }

        }
    }
            return false;

}


    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece now = pieces[xi][yi];

        Piece mid = pieces[Math.abs(xi + xf)/2][Math.abs(yi + yf)/2];

        if (now.isKing() == true) {
            if (((xf == xi - 2 || xf == xi + 2) && (yf == yi - 2 || yf == yi + 2)) && now != null && mid != null && (now.isFire() != mid.isFire()) ) {
                return true;
            }
            else if ((xf == xi - 1 || xf == xi + 1) && (yf == yi - 1 || yf == yi + 1)) {
                return true;
            }
        }
        else if (now.isFire() == true) {
            if (((xf == xi - 2 && yf == yi + 2) || (xf == xi + 2 && yf == yi + 2)) && now != null && mid != null && (now.isFire() != mid.isFire())) {
                return true;
            }
            else if ((xf == xi - 1 && yf == yi + 1) || (xf == xi + 1 && yf == yi + 1)) {
                return true;
            }
        }
        else {
            if (((xf == xi - 2 && yf == yi - 2) || (xf == xi + 2 && yf == yi - 2)) && now != null && mid != null && (now.isFire() != mid.isFire())) {
                return true;
            }
            else if ((xf == xi - 1 && yf == yi - 1) || (xf == xi + 1 && yf == yi - 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean canSelect (int x, int y) { 

        Piece pce = pieces[x][y];

        if (p1turn == true) {
    
            if (pce != null && pce.isFire() == true) {
                if (pieceselected2 == false) {
                    return true;
                }
                else if (pieceselected2 == true && moved == false) {
                    return true;
                }
            }
            else if (pce == null) {


                if (pieceselected2 == true && moved == false && pieces[prevx][prevy]!= null && validMove(prevx, prevy, x, y)) {

                    return true;
                }
                else if (pieceselected2 == true && moved == false && pieces[prevx][prevy]!= null && pieces[prevx][prevy].hasCaptured() == true) {

                    return true;
                }
                else if (pieces[prevx][prevy]!=null && pieces[prevx][prevy].hasCaptured() == true && canIMultiFire(x, y) == true) {
                    return true;
            }

            }

        }


        else if (p1turn == false) {       

            if (pce != null && pce.isFire() == false) {
                if (pieceselected2 == false) {
                    return true;
                }
                else if (pieceselected2 == true && moved == false) {
                    return true;
                }

            }
            else if (pce == null) {
                if (pieceselected2 == true && moved == false && pieces[prevx][prevy]!= null && validMove(prevx, prevy, x, y)) {
                    return true;
                }
                else if (pieceselected2 == true && moved == false && pieces[prevx][prevy]!= null && pieces[prevx][prevy].hasCaptured() == true) {
                    return true;
                }
                else if (pieces[prevx][prevy]!= null && pieces[prevx][prevy].hasCaptured()== true && canIMultiWater(x, y) == true) {
                    return true;
                }
            }

        }

        return false;

    }


    public void select(int x, int y) {
        selectedX = x;
        selectedY = y;

        if (pieceselected2 != false && pieces[prevx][prevy] != null && pieceAt(x,y) == null) {

            pieces[prevx][prevy].move(x, y);
            moved = true;


    }
        else {
            pieceselected = pieceAt(x,y);
            pieceselected2 = true; 
            
        }
        prevx = x;
        prevy = y;
    }


    public boolean canEndTurn() {
        if (moved == true || hascaptured == true) {
            return true;
        }
        else {
            return false;
        }
    }

      

    public void endTurn() {
        if (p1turn == true) {
            p1turn = false;
        }
        else {
            p1turn = true;
        }

        pieceselected2 = false;
        if (pieces[prevx][prevy] != null) {
            pieces[prevx][prevy].doneCapturing();
        }
        pieceselected2 = false;
        hascaptured = false; 
        moved =false;
    }



    public String winner() {
        int water = 0;
        int fire = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = pieces[i][j];
                if (p != null) {
                    if (p.isFire() == true) {
                        fire += 1;
                    }
                    else {
                        water += 1;
                    }
                }
            }
        }
        if (water > 0 && fire == 0) {
            return "Water";
        }
        if (fire == 0 && water == 0) {
            return "No one";
        }
        if (fire > 0 && water == 0) {
            return "Fire";
        }
        else {
            return null;
        }
    }

}