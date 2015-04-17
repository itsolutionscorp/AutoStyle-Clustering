
public class Board {


    private Piece[][] pieces;
    private String winner;
    private String player;
    private String turn;
    private Piece selected;
    private int prevx;
    private int prevy;


    public static void main(String[] args) {
        int N = 8;
        boolean shouldBeEmpty = false;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(shouldBeEmpty);
        
        b.drawBoard(N);

        while(b.winner == null) {
            
            b.setBoard();
            
            if (b.winner() != null) {
                b.winner = b.winner();
                System.out.println("Winner is "+ b.winner);
            }

            if (StdDrawPlus.mousePressed()) {

                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int selectedx = (int) x;
                int selectedy = (int) y;

                    if (b.canSelect(selectedx, selectedy) == true) {
                        b.select(selectedx, selectedy);
                    
                    
                }
            }
               

         else if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn() == true) {
                    b.endTurn();
                }
            }        
            StdDrawPlus.show(100);
        }
    }
        

    public Board(boolean shouldBeEmpty) {
        winner = null;
        player = "Fire";
        turn = "Fire";
        if (shouldBeEmpty == true) {
            pieces = new Piece[8][8];
                }

        else {
            pieces = new Piece[8][8];
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 8; j += 1) {
                    if (j==0 && i%2 == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if (j==1 && i%2 == 1) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if (j==2 && i%2 == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    if (j==5 && i%2 == 1) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if (j==6 && i%2 == 0) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if (j==7 && i%2==1) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }      
            } }

        }} 


    private void drawBoard(int N) { 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }


    private void setBoard() {
        drawBoard(8);
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieces[i][j] != null) {
                    Piece set = pieces[i][j];
                    if (set.isFire() == true) {
                        if (set.isKing()) {
                            if (set.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1); }
                            else if (set.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1); }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1); }
                        }
                        else {
                            if (set.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1); }
                            else if (set.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); }
                        } }
                else {
                    if (set.isKing()) {
                            if (set.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1); }
                            else if (set.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1); }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1); }
                        }
                        else {
                            if (set.isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1); }
                            else if (set.isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1); }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1); }
                        }
                } } } } }

    

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7) {
            return null;
        }
        else {
            return pieces[x][y];
        } }



    public void place(Piece p, int x, int y) {
        if (x < 8 && y < 8) {
            pieces[x][y] = p;
        }            
        else {
            System.out.println("Out of bounds");
        }

   }

    public Piece remove(int x, int y) {
        if (check(x, y) == false) {
            System.out.println("The input is out of bounds.");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("There is no piece to remove.");
            return null;
        }
        Piece remover = pieceAt(x, y);
        pieces[x][y] = null;
        return remover;
    }

   

    public boolean canSelect(int x, int y) { 
        Piece possible = pieceAt(x, y);
        if (check(x, y) == false) {
            return false;
        }    

        if (selected != null) {

        if ((selected.hasCaptured() == false) && (turn.equals("moved"))) {
            return false;
        } }

        if ((selected != null) && (selected.hasCaptured() == true)) { 
            if (validMove(prevx, prevy, x, y) == true) {
                return true;
            }
            else {
                return false;}
            }

            if (player.equals("Fire")) {
                if ((player.equals(turn)) && (possible != null) && (possible.isFire() == true)) { //there is a piece there and it is the players turn
                        return true;
                }
                else if ((player.equals(turn) || turn.equals("moved")) && (possible == null) && (selected != null) && (selected.isFire() == true)) {
                    if (validMove(prevx, prevy, x, y) == true) {
                        return true;
                    }  
                }
            }
            if (player.equals("Water")) {
                if ((player.equals(turn)) && (possible != null) && (possible.isFire() != true)) {
                 
                    return true;
                
                }
                else if ((player.equals(turn) || turn.equals("moved")) && (possible == null) && (selected != null) && (selected.isFire() == false)) {
                    if (validMove(prevx, prevy, x, y) == true) {

                        return true;
                    } 
                } 
            }
    return false; }


    private boolean canCapture(int x, int y) {
        Piece temp = pieceAt(x, y);
        if ((turn.equals("moved") || (turn.equals("captured"))) && (selected.hasCaptured() == false)) {
            return false;
        }
        
        if (check(x, y) == true) {

            if (player.equals("Fire")) {
                int emptyx = x + 1;
                int emptyy = y - 1;
                if (selected != null) {
                if ((selected.isKing() == true) && (check(emptyx, emptyy) == true)) {
                if (temp != null) {
                if ((temp.isFire() != true) && (pieces[emptyx][emptyy] == null)) {
                    return true;
                } } } }
                emptyx = x - 1;
                emptyy = y - 1;
                if (selected != null) {
                if ((selected.isKing() == true) && (check(emptyx, emptyy) == true)) {
                    if (temp != null) {
                if ((temp.isFire() != true) && (pieces[emptyx][emptyy] == null)) {

                    return true;
                } } } }
               
                emptyx = x + 1;
                emptyy = y + 1;
                if (check(emptyx, emptyy) == true) {
                if ((temp != null) && (temp.isFire() != true) && (pieces[emptyx][emptyy] == null)) {
                    return true;
                } }
                emptyx = x - 1;
                emptyy = y + 1;
                if (check(emptyx, emptyy)) {
                if ((temp != null) && (temp.isFire() != true) && (pieces[emptyx][emptyy] == null)) {

                    return true;
                }
                


            } }
            if (player.equals("Water")) {
                int emptyx = x + 1;
                int emptyy = y + 1;
                if (selected != null) {
                if ((selected.isKing() == true) && (check(emptyx, emptyy) == true))  {
                    if (temp != null) {
                    if ((temp.isFire() != true) && (pieces[emptyx][emptyy] == null)) {
                        return true;
                } } } }
                emptyx = x - 1;
                emptyy = y + 1;
                if (selected != null) {
                if ((selected.isKing() == true) && (check(emptyx, emptyy) == true)) {
                    if (temp != null) {
                    if ((temp.isFire() == true) && (pieces[emptyx][emptyy] == null)) {
                        return true;
                } } } }
                
                emptyx = x - 1;
                emptyy = y - 1;
                if (check(emptyx, emptyy) == true) {
                if ((temp != null) && (temp.isFire() == true) && (pieces[emptyx][emptyy] == null)) {
                    return true;
                } }
                
                emptyx = x + 1;
                emptyy = y - 1;
                if ((check(emptyx, emptyy) == true)) {
                if ((temp != null) && (temp.isFire() == true) && (pieces[emptyx][emptyy] == null)) {
                    return true;
                } }
            
            }

        }
    selected.doneCapturing();
    return false;
}


    private boolean check(int x, int y) {
        if ((x > -1) && (y > -1) && (y < 8) && (x < 8)) {
            return true;
    } 
        else {return false;}}


    private boolean validMove(int xi, int yi, int xf, int yf) {
        
        if (check(xf, yf) == true) {
            if ((pieceAt(xi, yi).isFire() == true) && (pieceAt(xi, yi).isKing() == false) && (selected.hasCaptured() == false)) {
                if ((yf - 1 == yi) && ((xi - 1 == xf) || (xi + 1 == xf))) {
                    return true;
                }
            } 
        if ((pieceAt(xi, yi).isFire() == false) && (pieceAt(xi, yi).isKing() == false) && (selected.hasCaptured() == false)) {
            if ((yi - 1 == yf) && ((xi - 1 == xf) || (xi + 1 == xf))) {
                return true;
            }
        } 
        if ((pieceAt(xi, yi) != null) && (pieceAt(xf, yf) == null)) {
            if ((pieceAt(xi, yi).isKing() == true) && (selected.hasCaptured() == false)) {
            if ( ((yi - 1 == yf) || (yf - 1 == yi)) && ((xi - 1 == xf) || (xi + 1 == xf)) ) {
                return true;
            }
        }
        if ((selected.isKing() == true) || (selected.isFire() == true)) {
        if ((xi + 2 == xf) && (yi + 2 == yf) && (canCapture(xi + 1, yi + 1) == true)) {
            //captured = pieces[xi + 1][yi + 1];
            turn = "captured";

            return true;
        }
        if ((xi - 2 == xf) && (yi + 2 == yf) && (canCapture(xi - 1, yi + 1) == true)) {
            //captured = pieces[xi - 1][yi + 1];
            turn = "captured";

            return true;
        } }
        if ((selected.isKing() == true) || (selected.isFire() == false)) {

        if ((xi - 2 == xf) && (yi - 2 == yf) && (canCapture(xi - 1, yi - 1) == true)) {
            //captured = pieces[xi - 1][yi - 1];
            turn = "captured";

            return true;
        }
        if ((xi + 2 == xf) && (yi - 2 == yf) && (canCapture(xi + 1, yi - 1) == true)) {
            //captured = pieces[xi + 1][yi - 1];
            turn = "captured";

            return true;
        } }
    } }
    
        return false;
    } 



    public void select(int x, int y) {

        if (pieceAt(x, y) != null) {
            selected = pieceAt(x, y);
            prevx = x;
            prevy = y;
        }
        else {
            if (turn != "moved") {
            prevx = x;
            prevy = y;
            selected.move(x, y);
            turn = "moved";    
            selected = pieceAt(x, y);
        } }

    
        }


    public boolean canEndTurn() {
        if (turn.equals("moved")) {
            return true;
            }
        return false;
    } 

    
    public void endTurn() {
        if (selected != null) {
        selected.doneCapturing(); }
        selected = null;


        if (player.equals("Fire")) {
            player = "Water";
            turn = "Water";
        }
        else {
            player = "Fire";
            turn = "Fire";
        }
    }


    public String winner() {
        int firecount = 0;
        int watercount = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire() == true) {
                        firecount += 1;
                    }
                    else {
                        watercount += 1;
                    }
        }
    }}

    if ((firecount == 0) && (watercount == 0)) {
        return "No one";
    }
    if (firecount == 0) {
        return "Water";
    }
    if (watercount == 0) {
        return "Fire";
    }
    else {return null;}

    }

    

    





}
