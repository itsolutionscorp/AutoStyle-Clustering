//import java.util.Arrays;

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private boolean shouldBeEmpty;
    private int N = 8;
    private boolean fireTurn;
    private boolean hasMoved;
    private boolean hasSelected;
    private int selectedX;
    private int selectedY;
    private Piece selectedPiece;
    private int fireLeft;
    private int waterLeft;
    private int turn = 0;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        fireTurn = true;
        hasMoved = false;
        hasSelected = false;
        selectedX = -1;
        selectedY = -1;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new Piece[N][N];
        if (shouldBeEmpty==true) {
            fireLeft = 0;
            waterLeft = 0;
        } else {
        fireLeft = 0;
        waterLeft = 0;
        for (int i = 0; i < N && i >= 0; i++) {
            for (int j = 0; j < N && j >= 0; j++) {
            if (i%2 == 0 && j==0) {
                pieces[i][j] = new Piece(true, this, i, j, "pawn");
                fireLeft += 1;
            }
            if (i%2 == 1 && j==1) {
                pieces[i][j] = new Piece(true, this, i, j, "shield");
                fireLeft += 1;
            }
            if (i%2 == 0 && j==2) {
                pieces[i][j] = new Piece(true, this, i, j, "bomb"); 
                fireLeft += 1;
            }
            if (i%2 == 1 && j==5) {
                pieces[i][j] = new Piece(false, this, i, j, "bomb");
                waterLeft += 1;
            }
            if (i%2 == 0 && j==6) {
                pieces[i][j] = new Piece(false, this, i, j, "shield");   
                waterLeft += 1;
            }
            if (i%2 == 1 && j==7) {
                pieces[i][j] = new Piece(false, this, i, j, "pawn");
                waterLeft += 1;
            }
        }
    }
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N && i >= 0; i++) {
            for (int j = 0; j < N && j >= 0; j++) {
                String img = "";
                if (hasSelected==true && i==selectedX && j==selectedY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j]!=null) {
                    if (pieces[i][j].isFire())
                    {
                        if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isBomb()) {
                                img = "img/bomb-fire-crowned.png";
                            }
                            else if (pieces[i][j].isShield()){
                                img = "img/shield-fire-crowned.png";
                            }
                            else {
                                img = "img/pawn-fire-crowned.png";
                            }
                        }
                        else {
                            if (pieces[i][j].isBomb()) {
                                    img = "img/bomb-fire.png";
                                }
                            else if (pieces[i][j].isShield()) {
                                img = "img/shield-fire.png";
                            }
                            else {
                                img = "img/pawn-fire.png";
                            }
                        }
                    }
                    else {
                        if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isBomb()) {
                                img = "img/bomb-water-crowned.png";
                            }
                            else if (pieces[i][j].isShield()) {
                                img = "img/shield-water-crowned.png";
                            }
                            else {
                                img = "img/pawn-water-crowned.png";
                            }
                        }
                        else {
                            if (pieces[i][j].isBomb()) {
                                img = "img/bomb-water.png";
                            }
                            else if (pieces[i][j].isShield()) {
                                img = "img/shield-water.png";
                            }
                            else {
                                img = "img/pawn-water.png";
                            }
                        }
                    }    
                }
                StdDrawPlus.picture(i+.5, j+.5, img, 1, 1);
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x < 0 || y < 0 || x > N || y > N) {
            return null;
        }
        else if (pieces[x][y]!=null) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if (fireTurn && pieces[x][y]!=null && pieces[x][y].isFire() && hasMoved==false) {
            // System.out.println("fire select");
            // hasSelected = true;
            // selectedX = x;
            // selectedY=y;
            return true; 
        }
        else if (!fireTurn && pieces[x][y]!=null && pieces[x][y].isFire()==false && hasMoved==false) {
            // System.out.println("water select");
            // hasSelected = true;
            // selectedX = x;
            // selectedY=y;
            return true;
        }
        //fire move
        else if (fireTurn && hasMoved==false && pieces[x][y]==null && ((x==selectedX+1 && y==selectedY+1) || (x==selectedX-1 && y==selectedY+1))) {
            System.out.println("space select");
            return true;
        }
        //fire King move
        else if (fireTurn && pieces[x][y]==null && pieceAt(selectedX, selectedY)!=null && pieceAt(selectedX, selectedY).isKing() && hasMoved==false && ((x==selectedX+1 && y==selectedY+1) || (x==selectedX-1 && y==selectedY+1) ||
            (x==selectedX-1 && y==selectedY-1) || (x==selectedX+1 && y==selectedY-1)
            )
            ) {
            System.out.println("space select");
            return true;
        }  
        //water move
        else if (!fireTurn && hasMoved==false && pieceAt(selectedX, selectedY)!=null && pieces[x][y]==null && ((x==selectedX-1 && y==selectedY-1) || (x==selectedX+1 && y==selectedY-1))) {
            System.out.println("space select");
            return true;
        }
        //water King move
        else if (!fireTurn && pieces[x][y]==null && pieceAt(selectedX, selectedY)!=null && hasSelected && pieceAt(selectedX, selectedY).isKing() && hasMoved==false && ((x==selectedX+1 && y==selectedY+1) || (x==selectedX-1 && y==selectedY+1) ||
            (x==selectedX-1 && y==selectedY-1) || (x==selectedX+1 && y==selectedY-1))) {
            System.out.println("space select");
            return true;
        }
        //capture jump for FIRE
        else if (fireTurn && pieceAt(selectedX, selectedY)!=null && (hasMoved==false || (hasMoved==true && pieceAt(selectedX, selectedY).hasCaptured())) && pieces[x][y]==null && 
            ((x==selectedX+2 && y==selectedY+2) || (x==selectedX-2 && y==selectedY+2)) && 
            pieceAt((selectedX+x)/2, (selectedY+y)/2)!=null && pieces[(selectedX+x)/2][(selectedY+y)/2].isFire()==false)  { 
            System.out.println("can fire jump");
            return true; 
        }
        //capture jump for FIRE KING
        else if (fireTurn && pieces[x][y]==null && pieceAt(selectedX, selectedY)!=null && pieceAt(selectedX, selectedY).isKing() && (hasMoved==false || (hasMoved==true && pieceAt(selectedX, selectedY).hasCaptured())) && 
            ((x==selectedX+2 && y==selectedY+2) || (x==selectedX-2 && y==selectedY+2) ||
                (x==selectedX+2 && y==selectedY-2) || (x==selectedX-2 && y==selectedY-2)
                ) && 
            pieceAt((selectedX+x)/2, (selectedY+y)/2)!=null && pieces[(selectedX+x)/2][(selectedY+y)/2].isFire()==false)  { 
            System.out.println("can fire jump");
            return true; 
        }
        //capture jump for WATER
        else if (!fireTurn && pieces[x][y]==null && pieceAt(selectedX, selectedY)!=null && pieceAt(selectedX, selectedY)!=null && (hasMoved==false || (hasMoved==true && pieceAt(selectedX, selectedY).hasCaptured())) && 
            ((x==selectedX+2 && y==selectedY-2) || (x==selectedX-2 && y==selectedY-2)) && 
            pieceAt((selectedX+x)/2, (selectedY+y)/2)!=null && pieces[(selectedX+x)/2][(selectedY+y)/2].isFire())  { 
            System.out.println("can water jump");
            return true; 
        }
        //capture jump for WATER KING
        else if (!fireTurn && pieces[x][y]==null && pieceAt(selectedX, selectedY)!=null && (hasMoved==false || (hasMoved==true && pieceAt(selectedX, selectedY).hasCaptured()))  && 
            ((x==selectedX+2 && y==selectedY-2) || (x==selectedX-2 && y==selectedY-2) ||
                (x==selectedX+2 && y==selectedY+2) || (x==selectedX-2 && y==selectedY+2)
                ) && 
            pieceAt((selectedX+x)/2, (selectedY+y)/2)!=null && pieces[(selectedX+x)/2][(selectedY+y)/2].isFire())  { 
            System.out.println("can water jump");
            return true; 
        }
        else {
            return false;
        }
    }

    public void select(int x, int y) {
        System.out.println("selected");
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if (pieces[x][y]!=null) {
            selectedX = x;
            selectedY = y;
            hasSelected = true; 
        } else if (pieces[x][y]==null && hasSelected) {
            pieces[selectedX][selectedY].move(x, y);
            if (pieces[x][y].isBomb() && pieces[x][y].hasCaptured()==true) {
                remove(x, y);
            }
            hasMoved=true;
            hasSelected=true;
            selectedX = x;
            selectedY = y;
        }
    }

    public void place(Piece p, int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N || p==null) {
        } else {
            pieces[x][y] = p;
            //System.out.println(Arrays.deepToString(pieces));
        }
    }

    public Piece remove(int x, int y) {
        Piece removedPiece; 
        System.out.println("remove coords" + "x" + x + "y" +y);
        if (pieces[x][y]!=null) {
            removedPiece = pieces[x][y];
            pieces[x][y] = null;
            if (removedPiece.isFire()) {
                fireLeft -= 1;
            } else {
                waterLeft -= 1;
            }
            return removedPiece;
        } else {
            return null;
        }
    }

    public boolean canEndTurn() {
        if (hasMoved) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        fireTurn = !fireTurn;
        hasMoved = false;
        hasSelected = false;
        turn += 1;
        if (pieceAt(selectedX, selectedY)!=null) {
            pieceAt(selectedX, selectedY).doneCapturing();            
        }
    }

    public String winner() {
        System.out.println(pieces.length);
        fireLeft = 0;
        waterLeft = 0;
        for (int i = 0; i < N && i >= 0; i++) {
                for (int j = 0; j < N && j >= 0; j++) { 
                    if (pieceAt(i, j)!=null && pieceAt(i, j).isFire()) {
                        fireLeft += 1;
                    } else if (pieceAt(i, j)!=null && pieceAt(i, j).isFire()==false) {
                        waterLeft += 1;
                    } else {
                    }
            }
        }
        System.out.println("fireLeft" + fireLeft);
        System.out.println("waterLeft" + waterLeft);
        if (waterLeft==0 && fireLeft!=0) {
            return "Fire";
        } else if (fireLeft==0 && waterLeft!=0) {
            return "Water";
        } else if (fireLeft==0 && waterLeft==0) {
            return "No one";
        } else if (shouldBeEmpty) {
            for (int i = 0; i < N && i >= 0; i++) {
                for (int j = 0; j < N && j >= 0; j++) {   
                    if (pieceAt(i ,j)!=null) {
                        return null;
                    }
                }
            }
        }
        else if (shouldBeEmpty==false) {
            return "No one";
        } 
        return "No one";
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        b.drawBoard(b.N);
        double x;
        double y;
        while(true) {
            b.drawBoard(b.N);
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (StdDrawPlus.mousePressed()) {
                x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();
                    if (x < 8 && x>=0 && y < 8 && y>=0 && b.canSelect((int) x, (int) y)) {
                        b.select((int )x, (int) y);
                    }
                }             
            StdDrawPlus.show(10);
            b.winner();
        }
    }
}