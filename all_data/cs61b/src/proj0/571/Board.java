import java.lang.Math;
import java.util.*;

public class Board {

    private boolean shouldBeEmpty = false;
    private int [] lastMove;
    private Piece curPiece = null;
    private boolean moved = false;
    private ArrayList<Piece> pieceArray = new ArrayList<Piece>();
    private ArrayList<Piece> waterPieceArray = new ArrayList<Piece>();
    private ArrayList<Piece> firePieceArray = new ArrayList<Piece>();
    private ArrayList<Piece> removedArray = new ArrayList<Piece>();
    private Piece [][] pieces = new Piece [101][101];
    private String player = "Fire";
    private int numOfTurns = 0;
    private boolean capTured = false;
    private int capX;
    private int capY;
    private int fromX;
    private int fromY;


    //private fields//

    private static String getImgPath(Piece p){
        
        if ((p.isFire()) && (p.isKing()) && (p.isBomb())) return "img/bomb-fire-crowned.png";
        if ((p.isFire()) && (p.isKing()) && (!p.isBomb()) && (p.isShield())) return "img/shield-fire-crowned.png";
        if ((p.isFire()) && (p.isKing()) && (!p.isBomb()) && (!p.isShield())) return "img/pawn-fire-crowned.png";
        if ((p.isFire()) && (!p.isKing()) && (p.isBomb())) return "img/bomb-fire.png";
        if ((p.isFire()) && (!p.isKing()) && (!p.isBomb()) && (p.isShield())) return "img/shield-fire.png";
        if ((p.isFire()) && (!p.isKing()) && (!p.isBomb()) && (!p.isShield())) return "img/pawn-fire.png";
        if ((!p.isFire()) && (p.isKing()) && (p.isBomb())) return "img/bomb-water-crowned.png";
        if ((!p.isFire()) && (p.isKing()) && (!p.isBomb()) && (p.isShield())) return "img/shield-water-crowned.png";
        if ((!p.isFire()) && (p.isKing()) && (!p.isBomb()) && (!p.isShield())) return "img/pawn-water-crowned.png";
        if ((!p.isFire()) && (!p.isKing()) && (p.isBomb())) return "img/bomb-water.png";
        if ((!p.isFire()) && (!p.isKing()) && (!p.isBomb()) && (p.isShield())) return "img/shield-water.png";
        else return "img/pawn-water.png";
    }


    private int indexX (Piece p){

            if (p==null) return 100;
            else {
                int temp = 100;
                for (int i=0; i <8; i++){
                    for (int j=0; j <8; j ++){
                        if ((pieces[i][j] != null) && (pieces[i][j] ==p))
                            temp = i;
                    }
                }
                return temp;            
            }

        }       



    private int indexY (Piece p){

            if (p==null) return 100;
            else {
                int temp = 100;
                for (int i=0; i <8; i++){
                    for (int j=0; j <8; j ++){
                        if ((pieces[i][j] != null) && (pieces[i][j] ==p))
                            temp = j;
                    }
                }
                return temp;
        
        }

    }

    private boolean mustEndGame(){
        if ((waterPieceArray.size() == 0) || (firePieceArray.size() == 0)) return true;
        else{
            if (player == "Fire"){
                for (Piece p : firePieceArray){
                    if (hasValidMove(p)) return false;
                }
                return true;
            }
            else {
                for (Piece p : waterPieceArray){
                    if (hasValidMove(p)) return false;
                }
                return true;
            }
        }
    }

    private boolean hasValidMove(Piece p){
        if (p==null) return false;
        for (int i = 0;i < 8; i ++) {
            for (int j = 0; j < 8; j ++){
                if (canSelect (i,j)) return true;
            }
        }
        return false;
    }

    private boolean canMoveFire(Piece p, int x, int y){
        Piece p1 = pieceAt(x,y);
        if (p1!=null) return false;
        if (outOfBounds(x,y)) return false;
        if (p==null) return false;
        if (p.isFire()){
            if (p.isKing()){
                if ((Math.abs(indexX(p)-x) == 1)&&(Math.abs(indexY(p)-y)==1)) return true;
                else return false;
            }
            else{
                if ((Math.abs(indexX(p)-x) == 1)&&((indexY(p)-y)== -1)) return true;
                else return false;
            }
        }
        else return false;
    }

    private boolean canMoveWater(Piece p, int x, int y){
        Piece p1 = pieceAt(x,y);
        if (p1!=null) return false;
        if (outOfBounds(x,y)) return false;
        if (!p.isFire()){
            if (p.isKing()){
                if ((Math.abs(indexX(p)-x) == 1)&&(Math.abs(indexY(p)-y)==1)) return true;
                else return false;
            }
            else{
                if ((Math.abs(indexX(p)-x) == 1)&&((indexY(p)-y)== 1)) return true;
                else return false;
            }
        }
        else return false;
    }

    private boolean outOfBounds(int x, int y){
        if (x> 7 || x <0 || y >7 || y <0) return true;
        else return false;
    }


    private boolean canCaptureSome(Piece p, Piece captured){

        int furX = 2* indexX(captured) - indexX(p);
        int furY = 2* indexY(captured) - indexY(p);
        Piece furtherPiece = pieceAt (furX, furY);


        if (outOfBounds(furX, furY)) return false;
        if (p==null || captured == null) return false;
        else if (indexX(p)>7 || indexY(p)>7 || indexX(captured)>7 || indexY(captured)>7) return false;

        else if (p.isKing() && (p.isFire() != captured.isFire())) {
            if ((Math.abs(indexX(p) - indexX(captured)) == 1) && (Math.abs(indexY(p) - indexY(captured)) == 1) && (furtherPiece == null)) return true;
            return false;
        }
        else if (p.isFire() && !captured.isFire()){
            if ((Math.abs(indexX(p) - indexX(captured)) == 1) && (indexY(captured) - indexY(p)) == 1 && (furtherPiece == null)) {
                return true;            
            } 

            else return false;
        }
        else if (!p.isFire() && captured.isFire()){
            if ((Math.abs(indexX(p) - indexX(captured)) == 1) && ((indexY(p) - indexY(captured)) == 1) && (furtherPiece == null)) return true;
            return false;
        }
        else return false;
    }

    private boolean canCaptureMove (Piece p, int xf, int yf){
        if (outOfBounds(xf,yf)) return false;
        int xi = indexX(p);
        int yi = indexY(p);
        if ((Math.abs(xi - xf) != 2) || (Math.abs(yi -yf) != 2)) return false;
        else{
            int tempX = (int) (xi + xf)/2;
            int tempY = (int) (yi + yf)/2;
            Piece temp = pieceAt(tempX, tempY);
            // System.out.println("try can capture some");
            return canCaptureSome(p, temp);
        }
    }

    private boolean canCaptureMore(Piece p){
        if (p == null) return false;
        else if (outOfBounds(indexX(p),indexY(p))) return false;
        else if (player.equals("Fire")){
            for (Piece captured: waterPieceArray){
                if (canCaptureSome (p, captured)) { 
                    return true;
                }
            }
            return false;           
        }
        else {
            for (Piece captured: firePieceArray){
                if (canCaptureSome (p, captured)) return true;
            }
            return false;
        }

    }

    private void swicthPlayer(){
        if (player == "Fire"){
            player = "Water";
            curPiece = null;
            moved = false;
        }
        else{
            player = "Fire";
            curPiece = null;
            moved = false;
        }
    }


    private static void picturePieceAt(int i, int j, Piece p){
        if (p != null) 
        StdDrawPlus.picture(i + .5, j + .5, Board.getImgPath(p), 1, 1);
    }

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                }
            }
        }

    private boolean canMoveAny(Piece p){
        for (int i = 0;i < 8; i ++) {
            for (int j = 0; j < 8; j ++){
                if (canMoveWater (p, i,j) || canMoveFire (p,i,j)) return true;
            }
        }
        return false;
    }

    //public fields//

    public static void main(String[] args) {

        Board game = new Board(false);

//      while ((.shouldBeEmpty == false) && (Board.mustEndGame() == false)) {
        while (game.winner() == null){

            int posX = 50;
            int posY = 50;
            // System.out.println("new turn ");
            while (! game.canSelect (posX, posY)) {
                if (StdDrawPlus.mousePressed()){
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    posX = (int) x;
                    posY = (int) y;
                }
            }
    
            game.select(posX, posY);
            System.out.println("selected at "+ posX + " " + posY);
            if ((game.fromX > -1) && (game.fromX < 8) && (game.fromY > -1) && (game.fromY < 8)) {
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(game.fromX + .5, game.fromY + .5, .5);
            }
            for (Piece p : game.pieceArray){
                game.picturePieceAt(game.indexX(p), game.indexY(p), p);
            }
            for (Piece p : game.removedArray){
                game.picturePieceAt(game.indexX(p), game.indexY(p), p);
            }


            // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.show(100);

            if (game.canEndTurn()){

                while (true){
                    if (StdDrawPlus.isSpacePressed()) break;
                }
                game.endTurn();
                System.out.println("turn ended"); 
            }
        }
        System.out.println("THE WINNER IS " + game.winner());
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                StdDrawPlus.picture(i + .5, j + .5, "img/squirrel.gif", 1, 1);
            }
        }
    }


    public Board(boolean shouldBeEmpty) {

        if (shouldBeEmpty == true) {
            int N = 8;
            StdDrawPlus.setXscale(0, N);
            StdDrawPlus.setYscale(0, N);
            pieces = new Piece[8][8];
            curPiece = null;
        }
        
        else {
            int N = 8;
            StdDrawPlus.setXscale(0, N);
            StdDrawPlus.setYscale(0, N);
            pieces = new Piece[8][8];
            drawBoard(8);

            for (int i =0; i< 8; i += 2){
                pieces[i][0] = new Piece (true, this, i, 0, "Pawn");
                firePieceArray.add(pieces[i][0]);
                pieceArray.add(pieces[i][0]);
            }
            for (int i =0; i< 8; i += 2){
                pieces[i+1][1] = new Piece (true, this, i+1, 1, "shield");
                firePieceArray.add(pieces[i+1][1]);
                pieceArray.add(pieces[i+1][1]);
            }
            for (int i =0; i< 8; i += 2){
                pieces[i][2] = new Piece (true, this, i, 2, "bomb");
                firePieceArray.add(pieces[i][2]);
                pieceArray.add(pieces[i][2]);

            }
            for (int i =0; i< 8; i += 2){
                pieces[i+1][5] = new Piece (false, this, i+1, 5, "bomb");
                waterPieceArray.add(pieces[i+1][5]);
                pieceArray.add(pieces[i+1][5]);
            }
            for (int i =0; i< 8; i += 2){
                pieces[i][6] = new Piece (false, this, i, 6, "shield");
                waterPieceArray.add(pieces[i][6]);
                pieceArray.add(pieces[i][6]);
            }
            for (int i =0; i< 8; i += 2){
                pieces[i+1][7] = new Piece (false, this, i+1, 7, "Pawn");
                waterPieceArray.add(pieces[i+1][7]);
                pieceArray.add(pieces[i+1][7]);
            }

            for (int i=0; i<8; i++) {
                for (int j =0; j <8 ; j ++) {
                    picturePieceAt(i, j, pieces[i][j]);                 
                }
            }
            
            curPiece = null;

            // }
        }
    }

    public Piece pieceAt(int x, int y){

        if (outOfBounds(x,y)){
            return null;
        }
        else{
            return pieces[x][y];    
        }
    }

    public boolean canSelect(int x, int y){

        Piece p = pieceAt(x, y);
        if (outOfBounds(x,y)) return false;
        if ((x+y)%2 == 1) return false;
        if (moved) {
            if ((capTured) && (canCaptureMore(curPiece))){
                return true;
            } 
            else return false;
        }

        else if (curPiece == null)  {
            if (p != null) {
                if ((player.equals("Fire"))&&(p.isFire())) {
                    
                    if (canMoveAny(p)) return true;
                    if (canCaptureMore(p)) {
                        return true;
                    }
                    else return false;
                }
                else if ((player.equals("Water")&&(!p.isFire()))){
                    if (canMoveAny(p)) return true;
                    else if (canCaptureMore(p)) { 
                        return true;
                    }
                    else return false;
                }

                else if ((capTured) && (p == pieceAt (capX, capY))) {
                    return true;    
                }           
                else return false;
            }
            else return false;
        }
        else if (curPiece != null) {
            if (p==null) {
                if (player.equals("Fire") && canMoveFire (curPiece, x, y)) return true;
                else if (player.equals("Fire") && canCaptureMove (curPiece, x, y)) return true;
                else if (player.equals("Water") && canMoveWater (curPiece, x, y)) return true;
                else if (player.equals("Water") && canCaptureMove (curPiece, x, y)) return true;
                else return false;
            }
            else{
                if ((player.equals("Fire")) && (p.isFire())) return true;
                else if ((player.equals("Water")) && (!p.isFire())) return true;
                else return false;
            }
        }
        else return false;
    }



    public void select(int x, int y){
        if (outOfBounds(x,y)) {};
        Piece p = pieceAt(x, y);
        if ((capTured) && (canCaptureMove(curPiece, x, y))) {
            place (curPiece, x, y);
            curPiece.move(x,y);
        }
        else if (curPiece== null){
            curPiece = pieceAt (x, y);  
        }
        else if (curPiece!=null){
            if ((player.equals("Fire")) && (p != null) && (p.isFire())){
                curPiece = p;   
            }
            else if ((player.equals("Water")) && (p != null) && (!p.isFire())){
                curPiece = p;   
            }
            else {
                place(curPiece, x, y);
                curPiece.move(x,y);
            } 
        }

    }

    public void place(Piece p, int x, int y){

        int tempX = indexX(p);
        int tempY = indexY(p);
        int middleX = (int) (indexX(p) + x)/2;
        int middleY = (int) (indexY(p) + y)/2;
        ArrayList<Piece> nearBy = new ArrayList<Piece>();       

        if (p == null) {}
        else if (outOfBounds(x,y)){}
        else if (outOfBounds(tempX, tempY)) {
            pieces[x][y] = p;
            pieceArray.add(p);
            if (p.isFire()) firePieceArray.add(p);
            if (!p.isFire()) waterPieceArray.add(p);
            picturePieceAt(x, y, p);
        }
        else {

            pieces[tempX][tempY] = null;
            pieces[x][y] = p;
            curPiece = p;
            fromX = tempX;
            fromY = tempY;
            moved = true;

            System.out.println("curPiece at " + indexX(p) + " " + indexY(p));

            // System.out.println("placed at " + indexX(curPiece) + " " + indexY(curPiece));

            if ((pieceAt(middleX,middleY) != null) && (Math.abs(tempX-x)== 2) && (Math.abs(tempY- y)== 2)) {
                capTured = true;
                removedArray.add(remove(middleX, middleY));
                System.out.println("captured at " + middleX + " " + middleX);

                if (p.isBomb()) {
                    nearBy.add(pieceAt(x, y));
                    nearBy.add(pieceAt(x-1, y-1));
                    nearBy.add(pieceAt(x, y-1));
                    nearBy.add(pieceAt(x-1, y));
                    nearBy.add(pieceAt(x+1, y+1));
                    nearBy.add(pieceAt(x, y+1));
                    nearBy.add(pieceAt(x+1, y));
                    nearBy.add(pieceAt(x-1, y+1));
                    nearBy.add(pieceAt(x+1, y-1));
                    nearBy.add(pieceAt(x, y-1));
                    for (Piece exPlode : nearBy){
                        if (exPlode!=null && !exPlode.isShield()) {
                            removedArray.add(remove(indexX(exPlode), indexY(exPlode)));
                        }
                    }
                }
            }
        }
    }

    public Piece remove(int x, int y){

        Piece tempPiece = pieceAt(x, y);

        if (outOfBounds(x,y)){
            return null;
        }
        else if (tempPiece == null) return null;
        else{
            pieces [x][y] = null;
            pieceArray.remove(tempPiece);
            if (tempPiece.isFire()) firePieceArray.remove(tempPiece);
            else waterPieceArray.remove(tempPiece);

            System.out.println("removed at " + x + " " + y);
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            return tempPiece;
            }                                                  //* not done yet *//
    }


    public boolean canEndTurn(){
        if (moved == false) return false;
        else return true;
    }

    public void endTurn(){
        String temp = player;
        if (capTured && canCaptureMore (curPiece)) {
            capX = indexX(curPiece);
            capY = indexY(curPiece);
            removedArray.clear();
            System.out.println("water " + waterPieceArray.size() + " ");
            System.out.println("Fire " + firePieceArray.size() + " ");
            System.out.println("The past player is " + temp);
        }
        else {
            capTured = false;
            this.swicthPlayer();
            curPiece = null;
            moved = false;
            removedArray.clear();
            System.out.println("water " + waterPieceArray.size() + " ");
            System.out.println("Fire " + firePieceArray.size() + " ");
            System.out.println("The past player is " + temp);
        }

    }
        // numOfTurns ++;


    public String winner(){
        if ((firePieceArray.size()==0)&&(waterPieceArray.size() == 0)) return "No One";
        else if (firePieceArray.size() == 0) return "Water";
        else if (waterPieceArray.size() == 0) return "Fire";
        else if ((capTured) && canCaptureMore((pieceAt(capX, capY)))) return null;      
        else if (firePieceArray.size() == waterPieceArray.size() && mustEndGame()) return "NO One";
        else return null;

    }

}