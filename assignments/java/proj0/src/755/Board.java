/*
Board.java - A board that will represent the chessboard that holds stuff.
*/
import java.util.ArrayList;
public class Board {
    private static int N = 8;
    private boolean SBE = false;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private boolean whichTurn = true;
    public Board(boolean shouldBeEmpty){
        if(!SBE){
            setUpPieces();
        }
    }
    private void setUpPieces(){
        addPiece("pawn",0,0,true);
        addPiece("pawn",2,0,true);
        addPiece("pawn",4,0,true);
        addPiece("pawn",6,0,true);
        addPiece("shield",1,1,true);
        addPiece("shield",3,1,true);
        addPiece("shield",5,1,true);
        addPiece("shield",7,1,true);
        addPiece("bomb",0,2,true);
        addPiece("bomb",2,2,true);
        addPiece("bomb",4,2,true);
        addPiece("bomb",6,2,true);
        addPiece("pawn",0,7,false);
        addPiece("pawn",2,7,false);
        addPiece("pawn",4,7,false);
        addPiece("pawn",6,7,false);
        addPiece("shield",1,6,false);
        addPiece("shield",3,6,false);
        addPiece("shield",5,6,false);
        addPiece("shield",7,6,false);
        addPiece("bomb",0,5,false);
        addPiece("bomb",2,5,false);
        addPiece("bomb",4,5,false);
        addPiece("bomb",6,5,false);
    }
    
    public void drawInterface(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                for(int k = 0; k < pieces.size(); k++){
                    drawPiece(pieces.get(k));
                }
                
            }
        }
    }
    private void drawPiece(Piece piece){
         String path;
         if(piece.isFire()){
            if(piece.isShield()){
                if(!piece.isKing()){
                    path = "img/shield-fire.png";
                }
                else {
                    path = "img/shield-fire-crowned.png";
                }
            }
            else if(piece.isBomb()){
                if(!piece.isKing()){
                    path = "img/bomb-fire.png";
                }
                else {
                    path = "img/bomb-fire-crowned.png";
                }
            }
            else {
                if(!piece.isKing()){
                    path = "img/pawn-fire.png";
                }
                else {
                    path = "img/pawn-fire-crowned.png";
                }
            }
                
         }
         else {
             if(piece.isShield()){
                if(!piece.isKing()){
                    path = "img/shield-water.png";
                }
                else {
                    path = "img/shield-water-crowned.png";
                }
            }
            else if(piece.isBomb()){
                if(!piece.isKing()){
                    path = "img/bomb-water.png";
                }
                else {
                    path = "img/bomb-water-crowned.png";
                }
            }
            else {
                if(!piece.isKing()){
                    path = "img/pawn-water.png";
                }
                else {
                    path = "img/pawn-water-crowned.png";
                }
            }
             
         }
         StdDrawPlus.picture(piece.x + .5, piece.y + .5, path, 1, 1);
    }
    
    public void remove(int x, int y){
        Piece p = pieceAt(x,y);
        pieces.remove(p);
    }
    public Piece pieceAt(int x, int y){
        Piece p = null;
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).x == x && pieces.get(i).y == y){
                p = pieces.get(i);
            }
        }
        return p;
    }
    private void addPiece(String type, int x, int y, boolean team){
        if(x >= 0 && x <= 7 && y >= 0 && y <= 7){
            pieces.add(new Piece(team, this, x, y, type));
        }
    }
    
    public void place(Piece p, int x, int y){
        addPiece(p.type,x,y,p.isFire());
    }
    
    public boolean canSelect(int x, int y){
        if(x >= 0 && x <= N && y <= N && y >= 0){
            boolean ret = false;
            Piece p = pieceAt(x,y);
        if(p != null && allPiecesStable()){
            ret = (p.isFire() == whichTurn);
        }
        else if (p != null && !allPiecesStable()){
            ret = true;
        }
        else {
            //nothing
        }
        return ret;
        }
        else {
            return false;
        }
    }
    
    private Piece getSelectedPiece(){
        //assumes there is indeed a piece selected
        Piece p = null;
        for(int k = 0; k < pieces.size(); k++){
            if(pieces.get(k).isSelected){
                p = pieces.get(k);
            }
        }
        return p;
    }
    public void select(int x, int y){
        if(allPiecesStable()){
            pieceAt(x,y).isSelected = true;
        }
        else {
           
        }

        
    }
    public void endTurn(){
        whichTurn = !whichTurn;
    }
    private boolean allPiecesStable(){
        boolean ret = true;
        for(int k = 0; k < pieces.size(); k++){
            if(pieces.get(k).isSelected == true){
                ret = false;
            }
        }
        return ret;
    }
    private void update(){
        //draw the board and stuff
        drawInterface();
        
        if (StdDrawPlus.mousePressed()) {
            int x = (int)StdDrawPlus.mouseX();
            int y = (int)StdDrawPlus.mouseY();
            if(canSelect(x,y) ){
                select(x,y);
                
            }
            
         }     
          
         
         
    }
    public static void main(String[] args){
        Board b = new Board(true);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            b.update();
            StdDrawPlus.show(10);
        }
    }
    
}