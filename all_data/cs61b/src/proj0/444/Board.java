/** 
 *  @author Angela Ko
 */

public class Board {
    private static Piece[][] pieces;
    private int turn = 0;
    private boolean moved = false;
    private Piece temp_p;        
    private int tempx = -1;
    private int tempy = -1;
    
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (shouldBeEmpty == false) {
                    if (j == 0 && (i % 2 == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if (j == 1 && (i % 2 != 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if (j == 2 && (i % 2 == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    if (j == 5 && (i % 2 != 0)) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if (j == 6 && (i % 2 == 0)) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if (j == 7 && (i % 2 != 0)) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    } //end board

    private void drawBoard(int N) {
            for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                }            
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                }
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);        //each square is 0.5 wide
                if (pieces[i][j] != null) {
                    Piece p = pieces[i][j];
                    if (pieces[i][j] == this.temp_p && temp_p != null) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                    if (p.isFire()) {
                        if (p.isBomb()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                        else if (p.isShield()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
                            }
                        }
                        else {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (p.isBomb()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else if (p.isShield()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }   //end if
            }
        }
    } // end draw board

    public Piece pieceAt(int x, int y) {
       try {
           Piece p = pieces[x][y];
           return p;
       }
       catch (Exception e) {
            return null;
        }
    }

    public void place(Piece p, int x, int y) {
        if (((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) && (p != null)) {               
            pieces[x][y] = p;   
        }
    } //end place

    public Piece remove(int x, int y) {
        if ((x > 7) || (y > 7) || x < 0 || y < 0) {
            System.out.println("range is out of bounds");
            return null;
        } 
        if (pieceAt(x, y) == null) {
            System.out.println("There is no piece present.");
            return null;
        }
        Piece p = pieceAt(x, y);
        pieces[x][y] = null;
        return p;
    }
    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y); 
            if (p != null && this.moved == false) {
                if (this.turn % 2 == 0) {
                    if (p.isFire()) {
                        return true;
                    }
                }
                else {
                    if (p.isFire() == false) {
                        return true;
                    }
                }
            }
            else {
                if (this.temp_p != null) {
                    if (this.moved == false && validmove(this.tempx, this.tempy, x, y)) {
                        return true;
                    }
                    else if (this.temp_p.hasCaptured() && validmove(this.tempx, this.tempy, x, y)) {
                        return true;
                    }
                    // else if (this.temp_p.isKing() && validmove(this.tempx, this.tempy, x, y)) {
                    //     return true;
                    // }
                }
            }
        // }
        System.out.println("invalid selection");
        return false;
    }

    public void select(int x, int y) {
        Piece p = pieces[x][y];
        if (p != null) {
            this.temp_p = p;
            this.tempx = x;
            this.tempy = y;
        }
        else {
            this.temp_p.move(x, y);
            this.tempx = x;
            this.tempy = y;
            this.moved = true;
        }
    }

    private boolean validmove(int xi, int yi, int xf, int yf) {
        if (temp_p.isKing()) {
            if (((Math.abs(xf - xi)) == 1) && (Math.abs(yf - yi) == 1)) {
                return true;
            }
            if (((Math.abs(xf - xi)) == 2) && (Math.abs(yf - yi) == 2)) {
                Piece q = pieceAt((Math.max(xf, xi)) - 1, (Math.max(yf, yi)) - 1); 
                if (q != null) {
                    if (q.side() != this.temp_p.side()) {
                        return true;
                    }
                }
            }
        }
        if (this.temp_p.side() == 0 || temp_p.isKing() == true) { //fire pieces
            System.out.println(Math.abs(xf - xi));
            if (((Math.abs(xf - xi)) == 1) && (yf - yi) == 1) {
                System.out.println("ok");
                return true;
            }
            if (((Math.abs(xf - xi)) == 2) && (yf - yi) == 2) {
                Piece q = pieceAt((Math.max(xf, xi)) - 1, yf - 1); 
                if (q != null) {
                    if (q.side() != this.temp_p.side()) {
                        return true;
                    }
                }
            }
        }
        else if (this.temp_p.side() == 1 || temp_p.isKing() == true) { //water pieces
            if (((Math.abs(xf - xi)) == 1) && (yi - yf) == 1) {
                return true;
            }
            if (((Math.abs(xf - xi)) == 2) && (yi - yf) == 2) {
                Piece q = pieceAt((Math.max(xf, xi)) - 1, yf + 1); 
                if (q != null) {
                    if (q.side() != this.temp_p.side()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canEndTurn() {
        if (this.moved) {
            return true;
        } 
        return false;
    }

    public void endTurn() {
        if (this.temp_p != null) {
            if (this.temp_p.hasCaptured()) {
                this.temp_p.doneCapturing();
            }
        }
        this.turn = this.turn + 1;
        this.moved = false;
        this.temp_p = null;
        this.tempx = -1;
        this.tempy = -1;
        winner();            
    }

    private int gameover() {
        int firepieces = 0;
        int waterpieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = pieceAt(i, j);
                if (temp != null) {
                    if (temp.isFire()) {
                        firepieces = firepieces + 1;
                    }
                    else {
                        waterpieces = waterpieces + 1;
                    }
                }
            }
        }
        if (firepieces == 0 && waterpieces == 0) {
            return 0;
        }  
        else if (waterpieces == 0) {
            return 1;
        }
        else if (firepieces == 0) {
            return 2;
        }
        else {
            return 3;
        }
    }

    public String winner() {
        int a = gameover();
        if (a == 0) {
            return "No one";
        }
        else if (a == 1) {
            return "Fire";
        }
        else if (a == 2) {
            return "Water";
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board current = new Board(false);
            while(true) {
                current.drawBoard(N);
                if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    Piece p = current.pieceAt((int) x, (int) y);
                    if (p != null && current.temp_p == null) {
                        if (current.canSelect((int) x, (int) y)) {
                                current.temp_p = p;
                                current.tempx = (int) x;
                                current.tempy = (int) y;
                                current.select((int) x, (int) y);
                            }
                    }
                    else if (p != null && current.temp_p != null) {
                        current.temp_p = null;
                    }
                    else if (p == null) {
                        if (current.canSelect((int) x, (int) y)) {
                            current.select((int) x, (int) y);
                        }
                    }
                } // if mouse pressed first time
                if (StdDrawPlus.isSpacePressed()) {
                    if (current.canEndTurn()) {
                        current.endTurn();       
                    }
                    else {
                        System.out.println("Cannot end turn");
                    }             
                }
                StdDrawPlus.show(100);
            }
    } // end main args
}




