/** 
 *  @author Allen Cao
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private boolean turn;
    private Piece sel;
    private int selx;
    private int sely;

    private boolean hasMoved;
    private boolean explosion;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean ShouldBeEmpty){
        pieces = new Piece[8][8];
        turn = true;
        sel = null;
        selx = -1;
        sely = -1;
        hasMoved = false;
        explosion = false;
        /** Creates initial pieces on board **/
        if (!ShouldBeEmpty){
            for (int x = 0; x < 7; x+=2){
                pieces[x][0] = new Piece(true, this, x, 0, "pawn");
                pieces[x+1][1] = new Piece(true, this, x+1, 1, "shield");
                pieces[x][2] = new Piece(true, this, x, 2, "bomb");
            }
            for (int x = 0; x < 7; x+=2){
                pieces[x+1][5] = new Piece(false, this, x+1, 5, "bomb");
                pieces[x][6] = new Piece(false, this, x, 6, "shield");
                pieces[x+1][7] = new Piece(false, this, x+1, 7, "pawn");
            }    
        }   
    }

    public Piece pieceAt(int x, int y){
        if (x<8 && y<8 && x>=0 && y>=0){
            return pieces[x][y];
        }
        return null;
    }

    public void place(Piece p, int x, int y){
        Piece cur = pieceAt(x, y);
        if (cur!=null){
            this.remove(x, y);
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y){
        Piece cur = pieceAt(x, y);
        if (x < 8 && y < 8){
            if (cur != null){
                pieces[x][y] = null;
                return cur;
            }
            System.out.println("Cannot remove piece that is not here.");
            return null;
        }
        System.out.println("Cannot remove piece from place outside of board.");
        return null;
    }

    public boolean canSelect(int x, int y){
        Piece cur  = pieceAt(x, y);
        if (cur != null && cur.isFire() == turn){
            if ((sel == null || !hasMoved) && !explosion){
                return true;
            }
        }
        else if (cur == null){
            if (sel != null && validMove(selx, sely, x, y) && !hasMoved || sel != null && sel.hasCaptured() && hasMoved && validMove(selx, sely, x, y)){
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece move = pieceAt(xi, yi);
        Piece dest = pieceAt(xf, yf);
        if (dest == null && !move.isKing()){
            int width = xi-xf;
            if (move.isFire() && Math.abs(width) == yf - yi){
                if (yf - yi == 1 && !move.hasCaptured())
                    return true;
                else if (yf - yi == 2){
                    Piece inter = pieceAt((xi+xf)/2, (yi+yf)/2);
                    if (inter!=null && inter.isFire()!=move.isFire()){
                        return true;
                    }
                }
                    
            }
            else if (!move.isFire() && Math.abs(width) == yi - yf){
                if (yi - yf == 1 && !move.hasCaptured())
                    return true;
                else if (yi-yf == 2){
                    Piece inter = pieceAt((xi+xf)/2, (yi+yf)/2);
                    if (inter!=null && inter.isFire()!=move.isFire()){
                        return true;
                    }
                }      
            }
        }
        else if (dest == null){
            if (Math.abs(xi-xf)==Math.abs(yf-yi) && Math.abs(xi-xf) == 1 && !move.hasCaptured()){
                return true;
            }
            else if(Math.abs(xi-xf)==Math.abs(yf-yi) && Math.abs(xi-xf) == 2){
                Piece inter = pieceAt((xi+xf)/2, (yi+yf)/2);
                if (inter!=null && inter.isFire()!=move.isFire()){
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y){
        if (sel != null && pieceAt(x, y)== null){
            sel.move(x, y);
            hasMoved = true;
        }

        if (sel!= null && sel.hasCaptured() && sel.isBomb()){
            sel = null;
            selx = -1;
            sely = -1;
            explosion = true;
        }
        else{
            sel = pieceAt(x, y);
            selx = x;
            sely = y;
        } 
    }

    public boolean canEndTurn(){
        if (sel != null && sel.hasCaptured() || hasMoved){
            return true;
        }
        return false;
    }

    public void endTurn(){
        if (sel != null){
            sel.doneCapturing();
        }
        sel = null;
        selx = -1;
        sely = -1;
        hasMoved = false;
        turn = !turn;
        explosion = false;
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        String path = "img/";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == selx && j == sely) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece thisP = pieceAt(i, j);
                if (thisP != null){
                    path = "img/";
                    if (thisP.isBomb()) {
                        path += "bomb";
                    }
                    else if (thisP.isShield()) {
                        path += "shield";
                    }
                    else {
                        path += "pawn";
                    }

                    if (thisP.isFire()){
                        path += "-fire";
                    }
                    else {
                        path += "-water";
                    }

                    if (thisP.isKing()){
                        path += "-crowned";
                    }
                    path += ".png";
                    StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
                } 
            }
        }
        StdDrawPlus.show(25);
    }

    public String winner(){
        int f = 0;
        int w = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j)!=null){
                    if (pieceAt(i, j).isFire())
                        f+=1;
                    else
                        w+=1;
                }
            }
        }
        if (f==0 && w==0){
            return "No One";
        }
        else if (f==0){
            return "Water";
        }
        else if (w==0){
            return "Fire";
        }
        else{
            return null;
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        b.drawBoard();

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner()==null) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int)y)){
                    b.select((int)x, (int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
                b.endTurn();
            }
            b.drawBoard();           
        }
        System.out.println(b.winner() + " wins the game!");
        System.exit(0);
    }
}