    /*
     * Long HUI 
     * CS 61B (Jhug) Project0: Checker61b
     * due on Friday.
     */

public class Board {

    private int N = 8;
    private int turn, numF, numW;
    private Piece[][] pieces;
    private Piece p,q;
    private int intiaX, intiaY;
    private boolean shouldBeEmpty, hasMoved, hasSelected, hasRemoved; //hasMoved, hasSelected, learned in Lab. 

    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        pieces = new Piece[N][N];
        if (shouldBeEmpty) { }
        else {    
            numF = 12 ; numW = 12 ;       
            for(int i = 0; i < N; i += 2) {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                pieces[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                pieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
                pieces[i + 1][5] = new Piece(false, this, i + 1, 5, "bomb");
            }
        }
    }

    private void drawPiece() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i,j) != null) {
                    if (pieceAt(i,j).isFire() && pieceAt(i,j).isShield() ) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    if (pieceAt(i,j).isFire() && pieceAt(i,j).isBomb() ) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    if (pieceAt(i,j).isFire() && pieceAt(i,j).isShield() ==false && pieceAt(i,j).isBomb() == false ) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    if ( !pieceAt(i,j).isFire() && pieceAt(i,j).isShield() ) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    if ( !pieceAt(i,j).isFire() && pieceAt(i,j).isBomb() ) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    if ( !pieceAt(i,j).isFire() && !pieceAt(i,j).isShield() && pieceAt(i,j).isBomb() == false ) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
////////////////////// KING ///////////////////////
                    if (pieceAt(i,j).isFire() && pieceAt(i,j).isKing() && pieceAt(i,j).isShield() ) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    if (pieceAt(i,j).isFire() && pieceAt(i,j).isKing() && pieceAt(i,j).isBomb() ) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    if (pieceAt(i,j).isFire() && pieceAt(i,j).isKing() && !pieceAt(i,j).isShield() && pieceAt(i,j).isBomb() == false ) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    if ( !pieceAt(i,j).isFire() && pieceAt(i,j).isKing() && pieceAt(i,j).isShield() ) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    if ( !pieceAt(i,j).isFire() && pieceAt(i,j).isKing() && pieceAt(i,j).isBomb() ) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    if (!pieceAt(i,j).isFire() && !pieceAt(i,j).isShield()  && pieceAt(i,j).isKing() && !pieceAt(i,j).isBomb() ) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                } { }
            }       
        }
    }

    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);  
                drawPiece();
                selecting();
                StdDrawPlus.show(100);
            }
        }              
    }
    
    private void selecting() {
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            StdDrawPlus.filledSquare((int)x + .5, (int)y + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.GREEN);  
            select( (int) x, (int) y );
        }
    }
   
    
    //simplier
    private boolean outOfB(int x, int y) {
        return ( x<0 || x> N-1 ||  y<0 || y> N-1);
    }

    //got idea in Lab session.
    private boolean isOccupied(int x, int y) {
        if (outOfB(x,y)) return false;
        if (pieces[x][y] != null ) return true;
        else return false; 
    } 


        

    public Piece pieceAt(int x, int y) {
        if (outOfB(x,y)) return null;
        else return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (!isOccupied(x,y)) {
            if (hasSelected && !hasMoved && validMove(intiaX, intiaY , x,y) ) return true;
            if (hasSelected && pieceAt(intiaX,intiaY).hasCaptured() && validMove(intiaX, intiaY, x, y) ) return true;
            else return false;
        } 
        if (isOccupied(x,y)) {
            if (hasSelected && !hasMoved) return true;
            if (!hasSelected) return true;
            else return false;
        } 
        else return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xi == xf || yi == yf || outOfB(xi, yi) || outOfB(xf,yf)) return false;
        if ( Math.abs(xf - xi)<=2 && Math.abs(yf - yi)<=2 && !isOccupied(xf,yf) && (  ((xf- xi+ yf-yi) % 2 == 0) || ((xi- xf+ yi-yf) % 2 == 0) )) return true; 
        else return false;
    } //hasCaptured()
    

    public void select(int x, int y) {
        intiaX = x;
        intiaY = y;
        hasSelected = true;
        if (isOccupied(x,y)) hasRemoved = false; Piece old = pieceAt(intiaX,intiaY); System.out.println("isO");
        if (!isOccupied(x,y)) place(old,x,y); System.out.println("isnotO");
        
        /*if (selectedstuff = therightstuff) {
            selectedPieec.move();
        }*/
    }

    public void place(Piece p, int x , int y){
        if (outOfB(x,y) || p == null) { }
//        pieceAt(x,y) = p;    
            pieces[x][y] = p;
    }


    public Piece remove(int x, int y) {
        if (outOfB(x,y)) {
            System.out.println("out of bound");
            return null;
        }
        if ( !isOccupied(x,y)) {
            System.out.println("empty box");
            return null;        
        } else {
            Piece p = pieces[x][y];
            if (pieces[x][y].isFire())  numF = numF -1;
            if (!pieces[x][y].isFire()) numW = numW -1;
            hasRemoved = true; 
            pieces[x][y] = null; 
            return p;
        }
    }

    public boolean canEndTurn() {
        if (hasMoved || hasRemoved) return true;
        else return false;
    }

    public void endTurn() {
        turn = (turn + 1 ) % 2 ;
        hasRemoved = false;
        hasSelected = false;
    }// 0 is Fire; 1 is Water.
    
    public String winner() {
        if (numW == 0) System.out.println("Fire");
        if (numF == 0) System.out.println("Water");
        if (numW ==0 && numF == 0 ) System.out.println("No one");
        return "yo";
    } 
    
//private boolean gameOver() { }

    public static void main(String[] args) {
        Board creation = new Board(false);
        while (true) {
            creation.drawBoard(8);
            
        }
    }
}//eoclass
