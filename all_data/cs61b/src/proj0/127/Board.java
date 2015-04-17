public class Board{
   private Piece[][] pieces;
   private int turn;
   private int selectx, selecty;
   private boolean select;
   private boolean capture;
   private boolean move;
   private int firePiece, waterPiece;

   public static void main(String[] args) {
      Board b = new Board(false);
      StdDrawPlus.setXscale(0, 8);
      StdDrawPlus.setYscale(0, 8);
      while (true){
         b.drawBoard();
         int x =(int)StdDrawPlus.mouseX();
         int y = (int)StdDrawPlus.mouseY();
         if (StdDrawPlus.mousePressed()){
            if (b.canSelect(x,y))
               b.select(x,y);
         }
         if (StdDrawPlus.isSpacePressed()){
            if (b.canEndTurn()){
                b.endTurn();
            }

         }
         if (StdDrawPlus.isNPressed()){
            System.out.println("new game");
            b = new Board(false);
            b.drawBoard();
         }
         StdDrawPlus.show(10);
      }
   }

   public Board(boolean shouldBeEmpty){
      pieces = new Piece[8][8];
      turn = 0;
      select = false;
      capture = false;
      move = false;
      firePiece= 0;
      waterPiece=0;   
      if(!shouldBeEmpty){
          //places appropriate pieces in pieces[][]
         turn = 0;
         pieces = new Piece[8][8];
         pieces = initPieces(pieces, this);
         }
   }

   public Piece pieceAt(int x, int y){
      if (x>7 || y>7 || x<0 || y < 0){
         return null;}

      return pieces[x][y];
   
   }

   public void select(int x, int y){
      if (x<=7 && y <= 7 && x>=0 && y >=0){
            if (pieceAt(x,y)==null) //if you are selecting a empty space
            { 
            pieceAt(selectx,selecty).move(x,y);
            move = true;
            }
            selectx=x; //update select location
            selecty=y; //update select location
         if(pieceAt(x,y)!=null)
             capture = pieceAt(x,y).hasCaptured();
         }
   }

   public boolean canSelect(int x, int y)
   {
      if (!move && pieceAt(x,y)!=null){ //selecting pieces before moving. or selecting first piece
         if (pieceAt(x,y).side()!=turn) //if piece is on correct team
            return false;
         select = true;
         return true;}
      else if ((pieceAt(x,y)==null && (capture || !move) && validMove(selectx,selecty,x,y) && pieceAt(selectx,selecty).side()==turn))
      //empty space and have selected piece and valid move
      {
            select=true;
            return true;
      }
      return false;
   }

   private boolean validMove(int xi, int yi, int xf, int yf){
      int dx=Math.abs(xf-xi); //change in x
      int dy= Math.abs(yf-yi); //change in y
      if (pieceAt(xi,yi)==null){
         return false;
      }
      boolean direction = (((yf-yi)>0 && pieceAt(xi,yi).isFire())||((yf-yi)<0 && !pieceAt(xi,yi).isFire()));
      if(dx!=dy)//not moving diag proportionally
        return false;
      if (dx>2 || dy>2) // moving too far
        return false;
     if (capture && dx ==1) // can't move one space after cap
         return false;
      if (!pieceAt(xi,yi).isKing() && (!direction)) // moving in the wrong direction
        return false;
      if (dx ==2 && (pieceAt(xi+(xf-xi)/2,yi+(yf-yi)/2)==null || pieceAt(xi+(xf-xi)/2,yi+(yf-yi)/2).side()==turn))
        return false;

      return true;
   }
   public Piece remove(int x, int y){
      if (x>=0 && y>=0 && x<=7 && y<=7){
         if (pieceAt(x,y)==null){
            System.out.println("No piece at "+x+", "+y);
            return null;
         }
         else{
            Piece temp = pieceAt(x,y);
               if (temp.isFire())
                  firePiece--;
               else
                  waterPiece--;

            pieces[x][y]=null;
            return temp;
         }
      }
      System.out.println("Out of bound");
      return null;
   } 

   public void place(Piece p, int x, int y){

        if (x>=0 && x<8 && y>=0 && y<8 && p!=null)
            pieces[x][y]=p;
         if (p.isFire())
            firePiece++;
         else
            waterPiece++;

   }

   public void endTurn()
   { //0 is fire, 1 is water
      if (turn ==0)  
         turn =1;
      else
         turn =0;
     select =false;
     move =false;
     capture = false;
     if(pieceAt(selectx,selecty)!=null)
         pieceAt(selectx,selecty).doneCapturing();
   }


   public boolean canEndTurn(){
    if (move||capture) 
        return true;
    else
        return false;
   }

   public String winner(){
      if (firePiece==0 && waterPiece==0)
         return "No one";
      
      else if (firePiece==0)
         return "Water";

      else if (waterPiece==0) 
         return "Fire";
      return null;

   }





//Private helper methods


   private void drawBoard() {
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            drawSpot(i,j);
         }            
      }
               if (select) {
               StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               StdDrawPlus.filledSquare(selectx + .5, selecty + .5, .5);
               if (pieces[selectx][selecty]!= null) {
               StdDrawPlus.picture(selectx + .5, selecty + .5, imgPath(pieces[selectx][selecty]), 1, 1);
            }
            }
   
   }
   private void drawSpot(int i, int j){
            if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            if (pieces[i][j]!= null) {
               StdDrawPlus.picture(i + .5, j + .5, imgPath(pieces[i][j]), 1, 1);
            }
   }
   private Piece[][] initPieces(Piece[][] p, Board b){
      for (int x=0; x<8; x+=2){
         place(new Piece(true,b,x,0,"pawn"),x,0);
         place(new Piece(false,b,x,6,"shield"),x,6);
         place(new Piece(true,b,x,2,"bomb"),x,2);
         
      }
      for (int x=1; x<8; x+=2){
         place(new Piece(true,b,x,1,"shield"),x,1);
         place(new Piece(false,b,x,7,"pawn"),x,7);
         place(new Piece(false,b,x,5,"bomb"),x,5);
      }
   
      return p;
   }

   private String imgPath(Piece p){
      String img = "img/";
      if (p.isBomb())
         img+="bomb";
      else if (p.isShield())
         img+="shield";
      else
         img+="pawn";
      if (p.isFire()) 
         img+="-fire";
      else  
         img+="-water";
      if(p.isKing())
         img+="-crowned.png";
      else
         img+=".png";
      return img;
   }

}