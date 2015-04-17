public class Board {
/////
   //**make private after testing**\\
   private Piece[][] pieces;
   private boolean fireTurn;//tells whether it is the fireplayers turn
   private int numFire;//number of fire and water pieces on the board respectively
   private int numWater;
   private Piece selected;//piece that has been selected
   private boolean hasSelected;
   private int selectX,selectY;
   private boolean hasMoved;
   private boolean hasCaptured;

   public Board(boolean shouldBeEmpty){
       this.pieces = new Piece[8][8];
       this.fireTurn = true;
       this.numWater = 0;
       this.numFire = 0;
       this.hasSelected = false;
       this.hasMoved = false;
       if (!shouldBeEmpty) {
           for(int i = 0;i < 8;i++){
               if (i == 0){ 
                  this.pieces[0][0] = new Piece(true,this,0,0,"pawn");
                  this.pieces[2][0] = new Piece(true,this,2,0,"pawn");
                  this.pieces[4][0] = new Piece(true,this,4,0,"pawn");
                  this.pieces[6][0] = new Piece(true,this,6,0,"pawn");
               }
               else if(i == 1) {
                  this.pieces[1][1] = new Piece(true,this,1,1,"shield");
                  this.pieces[3][1] = new Piece(true,this,3,1,"shield");
                  this.pieces[5][1] = new Piece(true,this,5,1,"shield");
                  this.pieces[7][1] = new Piece(true,this,7,1,"shield");
               }
               else if(i == 2) { 
                  this.pieces[0][2] = new Piece(true,this,0,2,"bomb");
                  this.pieces[2][2] = new Piece(true,this,2,2,"bomb");
                  this.pieces[4][2] = new Piece(true,this,4,2,"bomb");
                  this.pieces[6][2] = new Piece(true,this,6,2,"bomb");
               }
               else if(i == 5) { 
                  this.pieces[1][5] = new Piece(false,this,1,5,"bomb");
                  this.pieces[3][5] = new Piece(false,this,3,5,"bomb");
                  this.pieces[5][5] = new Piece(false,this,5,5,"bomb");
                  this.pieces[7][5] = new Piece(false,this,7,5,"bomb");
               }
               else if(i == 6) { 
                  this.pieces[0][6] = new Piece(false,this,0,6,"shield");
                  this.pieces[2][6] = new Piece(false,this,2,6,"shield");
                  this.pieces[4][6] = new Piece(false,this,4,6,"shield");
                  this.pieces[6][6] = new Piece(false,this,6,6,"shield");
               }
               else if(i == 7) { 
                  this.pieces[1][7] = new Piece(false,this,1,7,"pawn");
                  this.pieces[3][7] = new Piece(false,this,3,7,"pawn");
                  this.pieces[5][7] = new Piece(false,this,5,7,"pawn");
                  this.pieces[7][7] = new Piece(false,this,7,7,"pawn");
              }
              this.numFire = 12;
              this.numWater = 12;
           }
       }
   }        
  //make private
  private static void drawBoard(int N,Piece[][] pieces){
       StdDrawPlus.setXscale(0, N);
       StdDrawPlus.setYscale(0, N);
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY); 
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                   if((pieces[i][j].isShield()==false)&&(pieces[i][j].isBomb()==false)&& (pieces[i][j].isFire()))
                       StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); 
                   else if((pieces[i][j].isShield() == true) && (pieces[i][j].isFire()))
                       StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1); 
                   else if((pieces[i][j].isBomb()==true) && (pieces[i][j].isFire()))
                       StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); 
                   else if((pieces[i][j].isBomb()) && (pieces[i][j].isFire()==false))
                       StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1); 
                   else if((pieces[i][j].isShield()==true) && (pieces[i][j].isFire()==false))
                       StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                   else if((pieces[i][j].isShield()==false)&&(pieces[i][j].isBomb()==false) && (pieces[i][j].isFire()==false))
                       StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1); 
                }
            }
        }
   }

   public Piece pieceAt(int x, int y) {
       if((x < 0)|| (x > 7)|| (y < 0)|| (y > 7))
           return null;
       return pieces[x][y];
   } 

   public boolean canSelect(int x, int y){     
       if(x < 0 || y<0 || x>7 || y>7)
           return false; 
       else if(pieces[x][y] != null && pieces[x][y].isFire()==this.fireTurn){
           if(!this.hasSelected || (this.hasSelected && !this.hasMoved))
               return true;
           else
               return false; 
       }
       else if(pieces[x][y] == null){ 
           if((!this.hasMoved && this.hasSelected && validMove(selectX,selectY,x,y)))
               return true;
           else if(hasSelected && validMove(selectX,selectY,x,y) && selected.hasCaptured())
               return true;
           else
               return false;
       }
       else {
          return false;
       }
   }

   private boolean validMove(int xi, int yi,int xf,int yf){
      if(xf < 0 || yf<0 || xf>7 || yf>7){
          return false;
      }
      if(pieces[xf][yf]==null){      
          if(pieces[xi][yi].isKing()){//kings can move up and down
              if(selected.hasCaptured())//if player has capture they can only do a capture move.
                 return ((xf == xi + 2 && yf == yi + 2)||(xf==xi-2 && yf==yi+2)||(xf==xi + 2 && yf==yi-2)||(xf==xi-2 && yf==yi-2));
              else if ((xf== xi + 1 && yf ==yi+1)||(xf==xi-1 && yf==yi+1))//must be diagonal move
                 return true;
              else if((xf== xi + 2 && yf==yi+2)||(xf==xi-2 && yf==yi+2)){//possible capture move
                 if(pieces[xi+1][yi+1] != null ||pieces[xi-1][yi+1]!=null||pieces[xi-1][yi-1]!= null||pieces[xi+1][yi-1]!=null)
                     return true;
                 return false;
              }
              else if ((xf== xi + 1 && yf==yi-1)||(xf==xi-1 && yf==yi-1))//must be diagonal move
                 return true;
              else if((xf==xi + 2 && yf==yi-2)||(xf==xi-2 && yf==yi-2))//possible capture move
                 return true;
              else
                 return false;
          }
          else if(pieces[xi][yi].isFire()){//fire pieces can only move up
              if(selected.hasCaptured())
                  return (xf== xi + 2 && yf==yi+2)||(xf==xi-2 && yf==yi+2);
              else if ((xf== xi + 1 && yf ==yi+1)||(xf==xi-1 && yf==yi+1))//must be diagonal move
                 return true;
              else if((xf== xi + 2 && yf==yi+2) || (xf==xi-2 && yf==yi+2)){//possible capture move
                 if(pieces[xi-1][yi+1] != null || pieces[xi+1][yi+1]!=null)
                     return true;
                 return false;   
              }
              else
                 return false;
          }
          else{//water pieces must move down
              if(selected.hasCaptured())
                  return (xf== xi + 2 && yf==yi-2)||(xf==xi-2 && yf==yi-2);
              if ((xf== xi + 1 && yf==yi-1)||(xf==xi-1 && yf==yi-1))//must be diagonal move
                 return true;
              else if((xf== xi + 2 && yf==yi-2)||(xf==xi-2 && yf==yi-2)){//possible capture move
                 if(pieces[xi-1][yi-1] != null || pieces[xi+1][yi-1]!=null)
                     return true;
                 return false;   
              }
              else
                 return false;
          }
      }
      return false;
   }
   public void select(int x, int y){
       if(pieces[x][y] == null){
           this.remove(selectX,selectY);
           selected.move(x,y);
           this.hasCaptured = true;
           this.hasMoved = true;
       }
       else
          this.selected = this.pieces[x][y];
          this.selectX = x;
          this.selectY = y;
          this.hasSelected = true;
   }

   public void place(Piece p, int x,int y){
       if((p !=null) && !((x > 7)||(x < 0)||(y > 7)||(y < 0))){
           pieces[x][y] = p;
       }
   }   

   public Piece remove(int x,int y){
       if((x > 7)||(x < 0)||(y > 7)||(y < 0)){
           System.out.println("Cannot remove piece from out of bounds");
           return null;
       }
       else if(pieces[x][y] == null){
           System.out.println("No piece to remove at given position.");
           return null;
       }
       else{
           Piece removedPiece = pieces[x][y];
           pieces[x][y] = null;
           if(removedPiece.isFire()){
               numFire--; 
           }
           else{
               numWater--;
           }
           return removedPiece;
        }
   }
   public boolean canEndTurn(){
       if(hasMoved && hasCaptured)
           return true;
       return false;
   }
   public void endTurn(){
        this.fireTurn = !this.fireTurn;//changes turn to the other player.
        this.hasSelected = false;
        this.hasMoved = false;
        selected.doneCapturing();
        this.winner();
   }
   public String winner(){
       if(numFire > 0 && numWater > 0)
           return null;
       if(numWater == 0 && numFire == 0)
           return "No one";
       else if(numFire == 0)
           return "Water";
       else
           return "Fire";
   }
   public static void main(String[] args){
       Board b = new Board(false);
       drawBoard(8,b.pieces);
   }
}
