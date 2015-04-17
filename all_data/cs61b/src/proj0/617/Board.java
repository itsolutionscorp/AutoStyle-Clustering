import java.awt.*;

/**
 * Created by Andrew Zhou on 2/12/2015.
 */
public class Board {
    private Piece[][] pieces=new Piece[8][8];

    //Boolean to keep true of whose turn it is. True = fire, False = water
    private boolean playerTurn;

    //Tracks the number of times a selection has been made
    private int selects;

    //Tracks which pieces is the currently selected
    private Piece selected;

    //Tracks the number of times a move has been made
    private int moves;


    private int[] origin = new int[2];
    private Piece capturer;

    /** Draws an N x N board. Adapted from:
     http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);


            }

        }
        if (shouldBeEmpty==false) {
            //fire pawns
            Piece firePawn1 = new Piece(true, this, 0, 0, "pawn");
            place(firePawn1, 0, 0);


            Piece firePawn2 = new Piece(true, this, 2, 0, "pawn");
            place(firePawn2, 2, 0);


            Piece firePawn3 = new Piece(true, this, 4, 0, "pawn");
            place(firePawn1, 4, 0);


            Piece firePawn4 = new Piece(true, this, 6, 1, "pawn");
            place(firePawn1, 6, 0);


            //fire shields
            Piece fireShield1 = new Piece(true, this, 1, 1, "shield");
            place(fireShield1, 1, 1);


            Piece fireShield2 = new Piece(true, this, 3, 1, "shield");
            place(fireShield2, 3, 1);


            Piece fireShield3 = new Piece(true, this, 5, 1, "shield");
            place(fireShield3, 5, 1);


            Piece fireShield4 = new Piece(true, this, 7, 1, "shield");
            place(fireShield4, 7, 1);


            //fire bombs
            Piece fireBomb1 = new Piece(true, this, 0, 2, "bomb");
            place(fireBomb1, 0, 2);


            Piece fireBomb2 = new Piece(true, this, 2, 2, "bomb");
            place(fireBomb2, 2, 2);


            Piece fireBomb3 = new Piece(true, this, 4, 2, "bomb");
            place(fireBomb3, 4, 2);


            Piece fireBomb4 = new Piece(true, this, 6, 2, "bomb");
            place(fireBomb4, 6, 2);


            //water pawns
            Piece waterPawn1 = new Piece(false, this, 1, 7, "pawn");
            place(waterPawn1, 1, 7);


            Piece waterPawn2 = new Piece(false, this, 3, 7, "pawn");
            place(waterPawn2, 3, 7);


            Piece waterPawn3 = new Piece(false, this, 5, 7, "pawn");
            place(waterPawn3, 5, 7);


            Piece waterPawn4 = new Piece(false, this, 7, 7, "pawn");
            place(waterPawn4, 7, 7);


            //water shields
            Piece waterShield1 = new Piece(false, this, 0, 6, "shield");
            place(waterShield1, 0, 6);


            Piece waterShield2 = new Piece(false, this, 2, 6, "shield");
            place(waterShield2, 2, 6);


            Piece waterShield3 = new Piece(false, this, 4, 6, "shield");
            place(waterShield3, 4, 6);


            Piece waterShield4 = new Piece(false, this, 6, 6, "shield");
            place(waterShield3, 6, 6);


            //water bombs
            Piece waterBomb1 = new Piece(false, this, 1, 5, "bomb");
            place(waterBomb1, 1, 5);


            Piece waterBomb2 = new Piece(false, this, 3, 5, "bomb");
            place(waterBomb2, 3, 5);


            Piece waterBomb3 = new Piece(false, this, 5, 5, "bomb");
            place(waterBomb3, 5, 5);


            Piece waterBomb4 = new Piece(false, this, 7, 5, "bomb");
            place(waterBomb4, 7, 5);
        }

        //Initialize with Fire's turn
        playerTurn=true;

    }

    public Piece pieceAt(int x, int y){
        if (x>=0 && x<8 && y>=0 && y<8){
        return pieces[x][y];}
        else return null;

    }


    public boolean canSelect(int x, int y){
        if (pieces[x][y]==null){
            if (selected==null){
                return false;
        }
            else if (moves==0){
                return true;
            }
            else if (selected!=null
                    &&moves!=0
                    &&selected.hasCaptured()
                    &&((x-origin[0]==2&&y-origin[1]==2&&pieces[x-1][y-1]!=null&&pieces[x-1][y-1].isFire()!=selected.isFire())
                        ||(x-origin[0]==2&&y-origin[1]==-2&&pieces[x-1][y+1]!=null&&pieces[x-1][y+1].isFire()!=selected.isFire())
                        ||(x-origin[0]==-2&&y-origin[1]==2&&pieces[x+1][y-1]!=null&&pieces[x+1][y-1].isFire()!=selected.isFire())
                        ||(x-origin[0]==-2&&y-origin[1]==-2&&pieces[x+1][y+1]!=null&&pieces[x+1][y+1].isFire()!=selected.isFire())
                    )) {
                return true;}
            else if (selected!=null
                    &&moves!=0
                    &&selected.hasCaptured())return false;
            else return false;
        }
        else if ((pieces[x][y].isFire()&&playerTurn)||(!pieces[x][y].isFire()&&!playerTurn)) {
            if (moves>0){
                return false;
            }
            else return true;
        }
        else return false;
    }

    private boolean validMove(int xi, int xf, int yi, int yf){
        if (xi>=0 && xf>=0 && yi>=0 && yf>=0 && xi<=7 && xf<=7 && yi<=7 && yf<=7&&((pieces[xi][yi].isFire()&&playerTurn)||(!pieces[xi][yi].isFire() && !playerTurn))){
            if ((!pieces[xi][yi].isKing()&&pieces[xi][yi].isFire()&&(yf-yi==xf-xi||xf-xi==yi-yf)&&yf>=yi)
                    ||(!pieces[xi][yi].isKing()&&!pieces[xi][yi].isFire()&&(yf-yi==xf-xi||xf-xi==yi-yf)&&yi>=yf)
                    ||(pieces[xi][yi].isKing()&&((yf-yi==xf-xi)||(xf-xi==yi-yf)))){
                if ((xf-xi==1||xf-xi==-1)&&pieces[xf][yf]==null){return true;}
                else if ((((xf-xi==2) && (yf-yi==2)&&(pieces[xf-1][yf-1]!=null&&(pieces[xf-1][yf-1].isFire()!=pieces[xi][yi].isFire())))||((xf-xi==2) && (yf-yi==-2)&&(pieces[xf-1][yf+1]!=null&&(pieces[xf-1][yf+1].isFire()!=pieces[xi][yi].isFire())))||(xf-xi==-2 && yf-yi==2 &&(pieces[xf+1][yf-1]!=null&&(pieces[xf+1][yf-1].isFire()!=pieces[xi][yi].isFire())))||(xf-xi==-2 && yf-yi==-2 &&(pieces[xf+1][yf+1]!=null&&(pieces[xf+1][yf+1].isFire()!=pieces[xi][yi].isFire()))))&&(pieces[xf][yf]==null)){return true;}
                else return false;
                }
            else return false;
            }
        else return false;
    }

    public void select(int x, int y){
        if (canSelect(x,y) && pieces[x][y]!=null){
            selects+=1;

            StdDrawPlus.setPenColor(Color.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            place(pieces[x][y],x,y);
            pieces[x][y].move(x,y);
            if (moves==0&&selected!=null && selected!=pieces[x][y]){
                if ((origin[0] + origin[1]) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                StdDrawPlus.filledSquare(origin[0] + .5, origin[1] + .5, .5);
                place(selected,origin[0],origin[1]);}

            selected=pieces[x][y];
            origin[0]=x;
            origin[1]=y;

        }

        else if (canSelect(x,y)&&pieces[x][y]==null){
            if (selected!=null){
                if (validMove(origin[0],x,origin[1],y)){
                        pieces[x][y]=selected;
                    remove(origin[0],origin[1]);
                        pieces[x][y].move(x,y);
                        place(pieces[x][y],x,y);
                        if (!pieces[x][y].isBomb()&&x-origin[0]==2 && y-origin[1]==2){remove(x-1,y-1);moves++; capturer=pieceAt(x,y);selected=pieces[x][y];origin[0]=x;origin[1]=y;}
                        else if (!pieces[x][y].isBomb()&&x-origin[0]==2 && y-origin[1]==-2){remove(x-1,y+1);moves++;capturer=pieceAt(x,y);selected=pieces[x][y];origin[0]=x;origin[1]=y;}
                        else if (!pieces[x][y].isBomb()&&x-origin[0]==-2 && y-origin[1]==2){remove(x+1,y-1);moves++;capturer=pieceAt(x,y);selected=pieces[x][y];origin[0]=x;origin[1]=y;}
                        else if (!pieces[x][y].isBomb()&&x-origin[0]==-2 && y-origin[1]==-2){remove(x+1,y+1);moves++;capturer=pieceAt(x,y);selected=pieces[x][y];origin[0]=x;origin[1]=y;}
                        else{moves+=1;}

                        if (pieces[x][y].isBomb()&&x-origin[0]==2 && y-origin[1]==2){remove(x-1,y-1);}
                        else if (pieces[x][y].isBomb()&&x-origin[0]==2 && y-origin[1]==-2){remove(x-1,y+1);}
                        else if (pieces[x][y].isBomb()&&x-origin[0]==-2 && y-origin[1]==2){remove(x+1,y-1);}
                        else if (pieces[x][y].isBomb()&&x-origin[0]==-2 && y-origin[1]==-2){remove(x+1,y+1);}

                       if (pieces[x][y].isBomb()&&x-origin[0]==2 && y-origin[1]==2){
                            if (x-1>=0&&y-1>=0&&x-1<=7&&y-1<=7&&pieces[x-1][y-1]!=null&&!pieces[x-1][y-1].isShield()){remove(x-1,y-1);}
                            if (x-1>=0&&y+1>=0&&x-1<=7&&y+1<=7&&pieces[x-1][y+1]!=null&&!pieces[x-1][y+1].isShield()){remove(x-1,y+1);}
                            if (x+1>=0&&y-1>=0&&x+1<=7&&y-1<=7&&pieces[x+1][y-1]!=null&&!pieces[x+1][y-1].isShield()){remove(x+1,y-1);}
                            if (x+1>=0&&y+1>=0&&x+1<=7&&y+1<=7&&pieces[x+1][y+1]!=null&&!pieces[x+1][y+1].isShield()){remove(x+1,y+1);}
                            remove(x,y);
                            moves+=1;}
                        else if (pieces[x][y].isBomb()&&x-origin[0]==2 && y-origin[1]==-2){
                            if (x-1>=0&&y-1>=0&&x-1<=7&&y-1<=7&&pieces[x-1][y-1]!=null&&!pieces[x-1][y-1].isShield()){remove(x-1,y-1);}
                            if (x-1>=0&&y+1>=0&&x-1<=7&&y+1<=7&&pieces[x-1][y+1]!=null&&!pieces[x-1][y+1].isShield()){remove(x-1,y+1);}
                            if (x+1>=0&&y-1>=0&&x+1<=7&&y-1<=7&&pieces[x+1][y-1]!=null&&!pieces[x+1][y-1].isShield()){remove(x+1,y-1);}
                            if (x+1>=0&&y+1>=0&&x+1<=7&&y+1<=7&&pieces[x+1][y+1]!=null&&!pieces[x+1][y+1].isShield()){remove(x+1,y+1);}
                            remove(x,y);
                            moves+=1;}
                        else if (pieces[x][y].isBomb()&&x-origin[0]==-2 && y-origin[1]==2){
                            if (x-1>=0&&y-1>=0&&x-1<=7&&y-1<=7&&pieces[x-1][y-1]!=null&&!pieces[x-1][y-1].isShield()){remove(x-1,y-1);}
                            if (x-1>=0&&y+1>=0&&x-1<=7&&y+1<=7&&pieces[x-1][y+1]!=null&&!pieces[x-1][y+1].isShield()){remove(x-1,y+1);}
                            if (x+1>=0&&y-1>=0&&x+1<=7&&y-1<=7&&pieces[x+1][y-1]!=null&&!pieces[x+1][y-1].isShield()){remove(x+1,y-1);}
                            if (x+1>=0&&y+1>=0&&x+1<=7&&y+1<=7&&pieces[x+1][y+1]!=null&&!pieces[x+1][y+1].isShield()){remove(x+1,y+1);}
                            remove(x,y);
                            moves+=1;}
                        else if (pieces[x][y].isBomb()&&x-origin[0]==-2 && y-origin[1]==-2){
                            if (x-1>=0&&y-1>=0&&x-1<=7&&y-1<=7&&pieces[x-1][y-1]!=null&&!pieces[x-1][y-1].isShield()){remove(x-1,y-1);}
                            if (x-1>=0&&y+1>=0&&x-1<=7&&y+1<=7&&pieces[x-1][y+1]!=null&&!pieces[x-1][y+1].isShield()){remove(x-1,y+1);}
                            if (x+1>=0&&y-1>=0&&x+1<=7&&y-1<=7&&pieces[x+1][y-1]!=null&&!pieces[x+1][y-1].isShield()){remove(x+1,y-1);}
                            if (x+1>=0&&y+1>=0&&x+1<=7&&y+1<=7&&pieces[x+1][y+1]!=null&&!pieces[x+1][y+1].isShield()){remove(x+1,y+1);}
                            remove(x,y);
                            moves+=1;}



                }
            }
        }}



    public void place(Piece p, int x,int y){
        if (x<0 || x>7 ||y<0 ||y>7){}
        else if (p.isBomb() && p.isKing() && p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5, "img/bomb-fire-crowned.png",1,1);

        }
        else if (p.isBomb() && !p.isKing() && p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5, "img/bomb-fire.png",1,1);

        }
        else if (p.isBomb() && p.isKing()&& !p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5,"img/bomb-water-crowned.png",1,1);
        }
        else if (p.isBomb() && !p.isKing()&& !p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5,"img/bomb-water.png",1,1);
        }
        else if (p.isShield() && p.isKing() && p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5, "img/shield-fire-crowned.png",1,1);
        }
        else if (p.isShield() && !p.isKing() && p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5, "img/shield-fire.png",1,1);

        }
        else if (p.isShield() && p.isKing()&& !p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5,"img/shield-water-crowned.png",1,1);
        }
        else if (p.isShield() && !p.isKing()&& !p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5,"img/shield-water.png",1,1);
        }
        else if (p.isKing() && p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5, "img/pawn-fire-crowned.png",1,1);
        }
        else if (!p.isKing() && p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5, "img/pawn-fire.png",1,1);

        }
        else if (p.isKing()&& !p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5,"img/pawn-water-crowned.png",1,1);
        }
        else if (!p.isKing()&& !p.isFire()){
            StdDrawPlus.picture(x+.5,y+.5,"img/pawn-water.png",1,1);
        }

        else {if ((x + y) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
              else {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);}
        pieces[x][y]=p;
    }
    public Piece remove(int x, int y){
        if (x<0|| x>7 || y<0 || y>7){
            return null;

        }
        else if (pieces[x][y]!=null){
            Piece removed = pieces[x][y];
            pieces[x][y]=null;
            {if ((x + y) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
            else {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);}
            return removed;

        }
        else return null;
    }
    public boolean canEndTurn(){
        if (moves>0||capturer!=null) return true;
        else return false;
    }
    public void endTurn(){
        if (canEndTurn()){
            if (playerTurn==true){playerTurn=false;}
            else {playerTurn=true;}
            moves=0;
            selects=0;
            if (capturer!=null){capturer.doneCapturing();}
        selected=null;}
    }

    public String winner(){
        int fireCounter= 0;
        int waterCounter = 0;
        int i = 0;
        while (i<8){
            int j=0;
            while (j<8){
            if (pieces[i][j]!=null && pieces[i][j].isFire()){
                fireCounter+=1;
            }
            else if (pieces[i][j]!=null && !pieces[i][j].isFire())
            {
                waterCounter+=1;
            }
            else {}
            j++;}
        i++;}
    if (fireCounter==0 && waterCounter==0){return "No one";}
    else if (fireCounter==0 && waterCounter!=0){return "Water";}
    else if (fireCounter!=0 && waterCounter==0){return "Fire";}
    else return null;
    }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board checkersBoard = new Board(false);
        while(checkersBoard.winner()==null) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                checkersBoard.select((int) x, (int) y);
            }
            if (StdDrawPlus.isSpacePressed()){
                checkersBoard.endTurn();

            }
            StdDrawPlus.show(25);
        }
        System.out.println(checkersBoard.winner());

}
}


