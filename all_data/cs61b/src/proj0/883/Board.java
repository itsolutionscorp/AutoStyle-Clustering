/**
*   @author Kitahara Haruki
*/
public class Board{
	private  Piece[][] mingjian=new Piece[8][8];
	private  boolean player=true;
    private  boolean havemove;
    private  boolean selecting;
    private  Piece selected;
    private int selectedX, selectedY;
  //  private boolean empty;
    public void select(int x, int y){//Let me show you what is THE WORST CODE UCB!!!
          if(x>7 || x<0 || y>7 || y<0)return;
            if((mingjian[x][y]!=null&&havemove==false)||selected==null){
              selected=mingjian[x][y];
              selectedX=x;
              selectedY=y;
            selecting=true;
            }
    else{
        /*      if(selecting==false){//drawpic(x, y); //selected=pieceAt(x, y); 
                  selecting=true;}
                 else
                  if(selecting==true){
                    if(mingjian[x][y]==null)*/
                      // if(validMove(selectedX, selectedY, x, y)){
                       //  System.out.printf("VMx1=%d, VMx2=%d\n", x, y);
                                                   selected.move(x, y);
                                                        remove(selectedX, selectedY);
    //  place(selectedPiece, x, y);

                           
 /*if(selected.hasCaptured())
  if(selected.isBomb())
    for(int i=-1;i<=1;i++)
      for(int j=-1; j<=1;j++)
        if(!((x+i)>7 || (x+i)<0 || (y+j)>7 || (y+j)<0))
        if(mingjian[x+i][y+j]!=null)
        if(mingjian[x+i][y+j].isShield()!=true)
       remove(x+i, y+j);*/
if (selected.hasCaptured()) {
        this.remove((x + selectedX) / 2, (y + selectedY) / 2);
    if (selected.isBomb()) {
          for (int i=x-1; i<=x+1; i++) {
            for (int j=y-1; j<=y+1; j++) {
              Piece piece=this.pieceAt(i, j);
              if (piece != null) {
                if (!piece.isShield()) {
                  this.remove(i, j);
     }  }}} } }
                 selectedX=x;
                   selectedY=y;
                           havemove=true; //comeBoard();
                         }
            }
    private void drawpic(int x, int y){
              /*  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
              if(mingjian[x][y]!=null){
                if(mingjian[x][y].isKing()){

                  if (mingjian[x][y].isFire()==true){ if(mingjian[x][y].isShield()) 
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                  else
                         if(mingjian[x][y].isBomb()) 
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                         else
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                  
                }


               if (mingjian[x][y].isFire()==false){ if(mingjian[x][y].isShield()) 
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                  else
                         if(mingjian[x][y].isBomb()) 
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                         else
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);

               }
}
else
{
                  if (mingjian[x][y].isFire()==true){ if(mingjian[x][y].isShield()) 
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                  else
                         if(mingjian[x][y].isBomb()) 
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                         else
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                  
                }


               if (mingjian[x][y].isFire()==false){ if(mingjian[x][y].isShield()) 
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                  else
                         if(mingjian[x][y].isBomb()) 
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                         else
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
               }}
}*/
 }
        private  void drawBoard(int N) {
     /*   for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               
            }
        }*/
    }

 private void comeBoard(){
            int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        drawBoard(N);

     for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              if(mingjian[j][i]!=null){
                  if (mingjian[j][i].isFire()==true){ if(mingjian[j][i].isShield()) 
                        StdDrawPlus.picture(j + .5, i + .5, "img/shield-fire.png", 1, 1);
                  else
                         if(mingjian[j][i].isBomb()) 
                                StdDrawPlus.picture(j + .5, i + .5, "img/bomb-fire.png", 1, 1);
                         else
                                StdDrawPlus.picture(j + .5, i + .5, "img/pawn-fire.png", 1, 1);
                  
                }


               if (mingjian[j][i].isFire()==false){ if(mingjian[j][i].isShield()) 
                        StdDrawPlus.picture(j + .5, i + .5, "img/shield-water.png", 1, 1);
                  else
                         if(mingjian[j][i].isBomb()) 
                                StdDrawPlus.picture(j + .5, i + .5, "img/bomb-water.png", 1, 1);
                         else
                                StdDrawPlus.picture(j + .5, i + .5, "img/pawn-water.png", 1, 1);

               }

}

}

}

     for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              if(mingjian[j][i]!=null){
                if(mingjian[j][i].isKing()){

                  if (mingjian[j][i].isFire()==true){ if(mingjian[j][i].isShield()) 
                        StdDrawPlus.picture(j + .5, i + .5, "img/shield-fire-crowned.png", 1, 1);
                  else
                         if(mingjian[j][i].isBomb()) 
                                StdDrawPlus.picture(j + .5, i + .5, "img/bomb-fire-crowned.png", 1, 1);
                         else
                                StdDrawPlus.picture(j + .5, i + .5, "img/pawn-fire-crowned.png", 1, 1);
                  
                }


               if (mingjian[j][i].isFire()==false){ if(mingjian[j][i].isShield()) 
                        StdDrawPlus.picture(j + .5, i + .5, "img/shield-water-crowned.png", 1, 1);
                  else
                         if(mingjian[j][i].isBomb()) 
                                StdDrawPlus.picture(j + .5, i + .5, "img/bomb-water-crowned.png", 1, 1);
                         else
                                StdDrawPlus.picture(j + .5, i + .5, "img/pawn-water-crowned.png", 1, 1);

               }
}
}

}

}
 }


   public static void main(String[] args) {
     Board game1= new Board(false);
    
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
     //   game1.drawBoard(8);
      //  game1.defaultBoard(game1);

     //game1.work();
     {
   while(game1.winner()!=null){
     if (StdDrawPlus.mousePressed()) {
                double x1 = StdDrawPlus.mouseX();
                double y1 = StdDrawPlus.mouseY();
                int x=(int) x1;
                int y=(int) y1;
               // System.out.printf("x1=%d, x2=%d\n", x, y);
              //  if(selected!=null)
             //   System.out.printf("SELECTEDx1=%d, SELECTEDx2=%d\n", selected.a, selected.b);
                if(game1.canSelect(x, y)){//System.out.printf("SELECTEDx1=%d, SELECTEDx2=%d\n", x, y); 
                game1.select(x, y); }

       }         
      if(StdDrawPlus.isSpacePressed()){
           if(game1.canEndTurn()){
                 game1.endTurn();
           }
      }

            StdDrawPlus.show(20);
   
   }
   }

}

   private  void defaultBoard(){
   	        int N = 8;
   	 for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
              if(j>=0)
             if (i==0) if(j==0||j==2||j==4||j==6) {
                  //  StdDrawPlus.picture(j + .5, i + .5, "img/pawn-fire.png", 1, 1);
                    mingjian[j][i]=new Piece(true, this, j, i, "pawn");
                }
                if(j>=0)
             if (i==1) if(j==1||j==3||j==5||j==7) {
                 //   StdDrawPlus.picture(j + .5, i + .5, "img/shield-fire.png", 1, 1);
                    mingjian[j][i]=new Piece(true, this, j, i, "shield");
                }
                if(j>=0)
                if (i==2) if(j==0||j==2||j==4||j==6) {
                //    StdDrawPlus.picture(j + .5, i + .5, "img/bomb-fire.png", 1, 1);
                    mingjian[j][i]=new Piece(true, this, j, i, "bomb");
                }

}

}
   	 for (int i = 5; i < 8; i++) {
            for (int j = 0; j < N; j++) {
             if (i==7)  if(j>=0)if(j==1||j==3||j==5||j==7) {
               //     StdDrawPlus.picture(j + .5, i + .5, "img/pawn-water.png", 1, 1);
                    mingjian[j][i]=new Piece(false, this, j, i, "pawn");
                }
             if (i==6) if(j>=0) if(j==0||j==2||j==4||j==6) {
              //      StdDrawPlus.picture(j + .5, i + .5, "img/shield-water.png", 1, 1);
                         mingjian[j][i]=new Piece(false, this, j, i, "shield");
                }
                if (i==5) if(j>=0) if(j==1||j==3||j==5||j==7) {
              //      StdDrawPlus.picture(j + .5, i + .5, "img/bomb-water.png", 1, 1);
                         mingjian[j][i]=new Piece(false, this, j, i, "bomb");
                }

}

}
   }
//- starts a GUI supported version of the game.
public Board(boolean shouldBeEmpty){
   if (shouldBeEmpty == false) {
    this.defaultBoard();
    }
   // else 
     // empty=false;

}
// - The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes a Board with the default configuration. Note that an empty Board could possibly be useful for testing purposes.
public  Piece pieceAt(int x, int y){
if(x>7 || x<0 || y>7 || y<0)return null;
if (mingjian[x][y]==null)return null;
return mingjian[x][y];
} 
//- Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
public  boolean canSelect(int x, int y){
  if(x>7 || x<0 || y>7 || y<0)return false;
	if(mingjian[x][y]!=null) 
		if(mingjian[x][y].isFire()==player)
if(selecting==false ||havemove==false)
	return true;

  if (mingjian[x][y]==null) {
  	if ((havemove==false&&selected!=null))
    if (validMove(selectedX, selectedY, x, y))
  		return true;
    if(selected!=null&&havemove==true&&selected.hasCaptured()==true)
    if (validCapture(selectedX, selectedY, x, y))
      return true;      
  }
  return false;
}
/* - Returns true if there is a piece the piece at (x, y) and it can be selected.

A piece may be selected if it is the corresponding player’s turn and one of the following is true:

The player has not selected a piece yet.
The player has selected a piece, but did not move it.
An empty square may be selected if one of the following is true:

During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. (You may select as many captures in a row as long
 as they are all valid and from the same piece.)*/
private  boolean validMove(int xi, int yi, int xf, int yf){
	if(mingjian[xi][yi]==null)return false;
	if(mingjian[xf][yf]!=null)return false;  
  if(xf>7 || xf<0 || yf>7 || yf<0)return false;
   if(xi>7 || xi<0 || yi>7 || yi<0)return false;
	/*if(mingjian[xi][yi].isKing()){//Worst Code UCB 
	    if(Math.abs(xi-xf)==1&&Math.abs(yi-yf)==1&&mingjian[xi][yi].hasCaptured()==false)return true;
	    if((xi-xf)==2&&(yi-yf)==2)if(mingjian[xi-1][yi-1]!=null)
             if(mingjian[xi-1][yi-1].isFire()!=player)
	             return true;

	    if((xi-xf)==-2&&(yi-yf)==2)if(mingjian[xi+1][yi-1]!=null)
             if(mingjian[xi+1][yi-1].isFire()!=player)
	             return true;

	    if((xi-xf)==-2&&(yi-yf)==-2)if(mingjian[xi+1][yi+1]!=null)
             if(mingjian[xi+1][yi+1].isFire()!=player)
	             return true;

	    if((xi-xf)==2&&(yi-yf)==-2)if(mingjian[xi-1][yi+1]!=null)
             if(mingjian[xi-1][yi+1].isFire()!=player)
	             return true;
	}
    else
    {
      if(mingjian[xi][yi].isFire())
    {
          if(Math.abs(xi-xf)==1&&(yi-yf)==-1&&mingjian[xi][yi].hasCaptured()==false)return true;
	    if((xi-xf)==2&&(yi-yf)==-2)if(mingjian[xi-1][yi+1]!=null)
             if(mingjian[xi-1][yi+1].isFire()!=player)
	             return true;

	    if((xi-xf)==-2&&(yi-yf)==-2)if(mingjian[xi+1][yi+1]!=null)
             if(mingjian[xi+1][yi+1].isFire()!=player)
	             return true;
    }
        if(!mingjian[xi][yi].isFire())
    {
          if(Math.abs(xi-xf)==1&&(yi-yf)==1&&mingjian[xi][yi].hasCaptured()==false)return true;
      if((xi-xf)==2&&(yi-yf)==2)if(mingjian[xi-1][yi-1]!=null)
             if(mingjian[xi-1][yi-1].isFire()!=player)
               return true;

      if((xi-xf)==-2&&(yi-yf)==2)if(mingjian[xi+1][yi-1]!=null)
             if(mingjian[xi+1][yi-1].isFire()!=player)
               return true;
    }
  }*/

  Piece piece = this.pieceAt(xi, yi);//Thanks for the discussion with Yide Shentu, I got easiler way to solve this method.
    if (piece==null) {
      return false;}
    int ab=piece.isFire()?1:-1;
    if ((xf==xi+ab&&yf==yi+ab)||(xf==xi-ab&&yf==yi+ab)) {
      if (pieceAt(xf,yf)==null) {
        return true;
      }
    }
    if (piece.isKing()) {
      if ((xf==xi-ab&&yf==yi-ab)||(xf==xi+ab&&yf==yi-ab)) {
        if (pieceAt(xf, yf) == null) {
          return true;
        }
      }
    }

return validCapture(xi, yi, xf, yf);
}
private boolean validCapture(int xi, int yi, int xf, int yf){
    if(mingjian[xi][yi]==null)return false;
  if(mingjian[xf][yf]!=null)return false;  
  if(xf>7 || xf<0 || yf>7 || yf<0)return false;
   if(xi>7 || xi<0 || yi>7 || yi<0)return false;
    Piece piece=this.pieceAt(xi, yi);
    if (piece==null) {
      return false;}
    int cab=piece.isFire()?2:-2;
    int half=cab/2;
    if (xf==xi+cab&&yf==yi+cab) {
      Piece enemy=pieceAt(xi+half,yi+half);
      if (enemy!= null && enemy.isFire()!=piece.isFire()) {
        if (pieceAt(xf, yf) == null) {
          return true;
        }
      }
    }
    if (xf == xi - cab && yf == yi + cab) {
      Piece enemy = pieceAt(xi - half, yi + half);
      if (enemy != null && enemy.isFire()!=piece.isFire()) {
        if (pieceAt(xf, yf) == null) {
          return true;
        }
      }
    }
    if (piece.isKing()) {
      if (xf == xi - cab && yf == yi - cab) {
        Piece enemy = pieceAt(xi - half,yi - half);
        if (enemy != null && enemy.isFire()!=piece.isFire()) {
          if (pieceAt(xf, yf) == null) {
            return true;
          }
        }
      }
      if (xf == xi + cab && yf == yi - cab) {
        Piece enemy = pieceAt(xi + half, yi - half);
        if (enemy != null &&enemy.isFire()!=piece.isFire()) {
          if (pieceAt(xf, yf) == null) {
            return true;
          }
        }
      }
    }

return false;
} 
//- Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf) in a valid fashion compatible with the rules.

//- Selects the piece at (x, y) if possible. Optionally, it is recommended to color the background of the selected square white on the GUI via the pen color function. 
//For any piece to perform a capture, that piece must have been selected first.
public  void place(Piece p, int x, int y){
 // int xgoal=0, ygoal=0;
if(x>7 || x<0 || y>7 || y<0) return;
if(p==null)
  return;
  /*for (int i=0; i<8; i++) {
    for (int j=0; j<8; j++) {
      if(mingjian[i][j]==p){xgoal=i; ygoal=j;break;}

    }
  }

if(mingjian[xgoal][ygoal]!=null){
	remove(xgoal, ygoal); 
}*/
	mingjian[x][y]=p;
}
// - Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.
// If p already exists in the current Board, first removes it from its initial position. 
//If another piece already exists at (x, y), p will replace that piece. (This method is potentially useful for creating specific test circumstances.)
public  Piece remove(int x, int y){
	if(x>7 || x<0 || y>7 || y<0)return null;
if(mingjian[x][y]==null){//StdDrawPlus.picture(0, 0, "img/xiaowu.jpg", 1, 1); 
return null;}
else
{
Piece baba=mingjian[x][y];
mingjian[x][y]=null;
//System.out.printf("RMx1=%d, RMx2=%d\n", x, y);
/*if(mingjian[x][y]!=null)
{System.out.printf("mingjian stil exist!\n");}*/ //Ming jian no longer need to exist---233333 ---H.Kitahara
return baba;
}
} 
//- Executes a remove. Returns the piece that was removed. 
//If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
//If there is no piece at (x, y), returns null and prints an appropriate message.
public boolean canEndTurn() { 
	if(havemove==true)return true;
return false;
}
//- Returns whether or not the the current player can end their turn.
//To be able to end a turn, a piece must have moved or performed a capture.
public void endTurn(){

{
   if(player==true)player=false;
   else
   if(player==false)player=true;
     havemove=false;
  
   selecting=false;
 /* for(int i=0;i<8;i++)//Not the best solution, but simple and effective.---H.Kitahara
    for(int j=0;j<8;j++)
      if(mingjian[i][j]!=null)
        mingjian[i][j].doneCapturing();*/
selected.doneCapturing();//This one is better ---H.Kitahara
 selected=null;
}

}
 //- Called at the end of each turn. Handles switching of players and anything else that should happen at the end of a turn.
public String winner(){
	int recordfire=0, recordwater=0;

    for(int i=0;i<8;i++)
    	for(int j=0;j<8;j++){
    		Piece piece = this.pieceAt(i, j);
        if (piece == null) {
          continue;
        }
        if (piece.isFire()) {
          recordfire += 1;
        } else {
          recordwater += 1;
        }
      }  
    
    if(recordwater==0&&recordfire==0){return "No one";}
    else
    if(recordwater==0){return "Fire";}
  else
    if(recordfire==0){return "Water";}
else
return null;

}
// - Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over.
// Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them (In this event, just leave it at null). 
//Determine the winner solely by the number of pieces belonging to each team.



}


