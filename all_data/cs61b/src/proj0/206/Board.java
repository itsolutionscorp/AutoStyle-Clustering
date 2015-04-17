public class Board{

private Piece[][] pieces;
private Piece lastselected;
private boolean turnfire;

private int lastx;
private int lasty;
private boolean isDefault;
private boolean canEnd;
  public static void main(String[] args) {
        
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        board.drawBoard(N);/*initializes game state */


        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(board.canEndGame() == false){
        while(board.canEndTurn() == false) {
            
           
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(board.canSelect((int) x, (int) y) == true){
                    board.select((int) x, (int) y);
                    board.drawBoard(N);//TODO

            }            
            StdDrawPlus.show(1);
        }
    }
    if (StdDrawPlus.isSpacePressed() == true){
        board.endTurn();
    }
 
   }
   board.winner();
}

    
       
public Board (boolean shouldBeEmpty) {
    int k = 8;
    int n = 8;
    pieces = new Piece[k][n];
    
    if (shouldBeEmpty == true){
        for( int i=0; i<pieces.length; i++ )
    {
      for( int j=0; j<pieces[i].length; j++ )
      {   
        pieces[i][j] = null;
      }
    }
    isDefault = false;
   

    }

    if(shouldBeEmpty == false){
    
                pieces[0][0]= new Piece(true,this,0,0,"Pawn");
                pieces[2][0]= new Piece(true,this,2,0,"Pawn");
                pieces[4][0]= new Piece(true,this,4,0,"Pawn");
                pieces[6][0]= new Piece(true,this,6,0,"Pawn");
          
                pieces[1][1] = new Piece(true,this,1,1, "Shield");
                pieces[3][1] = new Piece(true,this,3,1, "Shield");
                pieces[5][1] = new Piece(true,this,5,1, "Shield");
                pieces[7][1] = new Piece(true,this,7,1, "Shield");
            
                pieces[0][2]= new Piece(true,this,0,2,"Bomb");
                pieces[2][2]= new Piece(true,this,2,2,"Bomb");
                pieces[4][2]= new Piece(true,this,4,2,"Bomb");
                pieces[6][2]= new Piece(true,this,6,2,"Bomb");
            

           
                pieces[1][7] = new Piece(false,this,1,7, "Pawn");
                pieces[3][7] = new Piece(false,this,3,7, "Pawn");
                pieces[5][7] = new Piece(false,this,5,7, "Pawn");
                pieces[7][7] = new Piece(false,this,7,7, "Pawn");
            

             
                pieces[0][6]= new Piece(false,this,0,6,"Shield");
                pieces[2][6]= new Piece(false,this,2,6,"Shield");
                pieces[4][6]= new Piece(false,this,4,6,"Shield");
                pieces[6][6]= new Piece(false,this,6,6,"Shield");
            

            
                pieces[1][5] = new Piece(false,this,1,5, "Bomb");
                pieces[3][5] = new Piece(false,this,3,5, "Bomb");
                pieces[5][5] = new Piece(false,this,5,5, "Bomb");
                pieces[7][5] = new Piece(false,this,7,5, "Bomb");
            


        turnfire = true;
        
        isDefault = true;
    
}

}

private boolean inBounds(int x, int y){
    if((x<0)|| (y < 0) || (x > 7) || (y > 7)){
        return false;
    }
    return true;
}
    

private boolean onSameTeam(Piece p){
    if (turnfire == true && p.isFire() == true){
        return true;
    }
    if (turnfire == false && p.isFire() == false){
        return true;
    }
    return false;

}


public Piece pieceAt(int x, int y) {

    if(0<=x && x<8 && 0<=y&& y<8){
        return pieces[x][y];

    }
    return null;
}


public boolean canSelect(int x, int y){
if(inBounds(x,y) == false){ /* out of bounds */
    return false;
}
if (this.isDefault == true){
    if(pieces[x][y]!= null && onSameTeam(pieces[x][y]) == true && lastselected == null){
    return true;}
    if(pieces[x][y]!= null && onSameTeam(pieces[x][y]) == true && lastselected != null && lastselected.hasCaptured() == false){
    return true;} 
    if (pieces[x][y] == null &&  lastselected != null && onSameTeam(lastselected) == true &&lastselected.hasCaptured() == false){
    return true; /*blank square after piece has been selected*/
}
if (pieces[x][y]== null && lastselected != null && onSameTeam(lastselected) == true && lastselected.hasCaptured() == true /*&& pieces[lastx][lasty] != null*/){
    return true; /* blank square if last piece has executed a capture and is not a bomb*/
}
}
    
if (this.isDefault == false){
     if(pieces[x][y]!= null && lastselected == null){
    return true;}
    if(pieces[x][y]!= null&& lastselected != null && lastselected.hasCaptured() == false){
    return true;} 
    if (pieces[x][y] == null &&  lastselected != null  &&lastselected.hasCaptured() == false){
    return true; /*blank square after piece has been selected*/
}
if (pieces[x][y]== null && lastselected != null  && lastselected.hasCaptured() == true /*&&  pieces[lastx][lasty] != null*/){
    return true; /* blank square if last piece has executed a capture and is not a bomb*/
}
}
    
/*square with piece and the game state has not changed yet*/


return false;
}

public void select(int x, int y) {
    
/*if (this.canSelect(x,y)==true){ */
    if (pieces[x][y]!=null){
        lastselected = pieces[x][y];
        lastx = x;
        lasty = y;
        return;
    }
    if(pieces[x][y] == null && lastselected != null){

        
        lastselected.move(x,y);

        lastselected.doneCapturing();
        this.remove(lastx,lasty);
        this.canEnd = true;
       
        return;
        
    }

/*}*/
}
public void place(Piece p, int x, int y){
 
    
    if(0<=x && x<8 && 0<=y&& y<8){
        pieces[x][y] = p;
        return;
     
    
    }

    System.out.println("Out of Bounds");

return;
}


public Piece remove(int x, int y) {
   Piece temp = pieces[x][y];
   this.pieces[x][y] = null;
   return temp;
}
public boolean canEndTurn(){
    if(this.canEnd == true){
        return true;
    }
return false;
}

public void endTurn(){
    if(this.canEndTurn() == true && turnfire == true){

        this.turnfire = false;
        canEnd = false;
        lastselected=null;
        return;}
    
    if(this.canEndTurn() == true && turnfire == false){
        this.turnfire = true;
        canEnd = false;
        lastselected=null;
        return;
        
    }
    
        

   
}
public String winner() {
    int firecount = 0;
    int watercount = 0;
    for(int i = 0; i<pieces.length;i++){
        for( int j=0; j<pieces[i].length; j++ ){
            if(pieces[i][j] != null){
            if (pieces[i][j].isFire() == true){
                firecount ++;
            }
            if (pieces[i][j].isFire() == false){
                watercount ++;
            }
        }
        }
    }
    if (watercount == 0 && firecount>0){
        return "Fire";
    }
    if(firecount == 0 && watercount>0){
        return "Water";
    }
    if(firecount ==0 && watercount == 0){
    return "No one";
}
    return null;
}

private boolean canEndGame() {
    int firecount = 0;
    int watercount = 0;
    for(int i = 0; i<pieces.length;i++){
        for( int j=0; j<pieces[i].length; j++ ){
            if(pieces[i][j] != null){
            if (pieces[i][j].isFire() == true){
                firecount ++;
            }
            if (pieces[i][j].isFire() == false){
                watercount ++;
            }
        }
        }
    }
    if (watercount== 0 || firecount == 0){
        return true;
    }
    return false;
}








    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire() == true){
                        if(pieces[i][j].isBomb() == true){
                            if(pieces[i][j].isKing() == true){

                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            }
            if(pieces[i][j].isShield() == true){
                            if(pieces[i][j].isKing() == true){

                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                }
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            }
            if(pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false){
           
                            if(pieces[i][j].isKing() == true){

                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                }
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            }

        }
              if (pieces[i][j].isFire() == false){
                        if(pieces[i][j].isBomb() == true){
                            if(pieces[i][j].isKing() == true){

                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                }
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            }
            if(pieces[i][j].isShield() == true){
                            if(pieces[i][j].isKing() == true){

                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                }
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            }
            if(pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false){
           
                            if(pieces[i][j].isKing() == true){

                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                }
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            }

        }
    }


   


}
}
}
}
