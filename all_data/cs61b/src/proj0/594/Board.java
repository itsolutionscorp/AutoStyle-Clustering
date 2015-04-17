
/** 
 *  @author Kevin Munui
 */

public class Board {
    /** Location of pieces. */
    private static boolean moved = false;//initialize the move to false i.e not happened
    private static int selected = 1;//NOT  selected piece
    private static int turn = 0;//initialized to the fire who starts increment my one then find mod
    private static Piece[][] pieces = new Piece[8][8];//declaring the size of the array and creates the array as well
    private static Piece p;
    private static int px;
    private static int py;



    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                else  StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(selected == -1 && pieceAt(i,j)== p){
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                //modifed board
                if (pieces[i][j] != null ) {
                  if (pieces[i][j].isFire()){

                    if(pieces[i][j].isKing()){
                         if (pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }//print fire king bomb
                         else if (pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                         }//print fire king shield
                         else
                         {
                           StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                         }//print fire king pawn

                      }//if king
                      else
                      {
                        if (pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }//print fire not king bomb
                         else if (pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                         }//print fire not king shield
                         else
                         {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                         }//print fire not king pawn

                      }//not king
                      
                  }//is fire
                    
                      //what type 
                  else{
                    if(pieces[i][j].isKing()){
                         if (pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        }//print not fire king bomb
                         else if (pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                         }//print not fire king shield
                         else
                         {
                             StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                         }//print not fire king pawn

                      }//if king
                      else
                      {
                        if (pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }//print not fire not king bomb
                         else if (pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                         }//print not fire not king shield
                         else
                         {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                         }//print not fire not king pawn

                      }//not king

                  }//is water
                    
                }
            }
        }
    }
    public Board(boolean shouldBeEmpty){
      if (shouldBeEmpty == false){
        for (int i = 0; i < 8; i++) {
          for (int j = 0; j < 8; j++) {
              if ( j == 0 && i%2==0){
                pieces[i][j] = new Piece(true,this,i,j,"pawn");
              }
              else if( j == 1 && i%2!=0){
                pieces[i][j] = new Piece(true,this,i,j,"shield");
              }
              else if( j == 2 && i%2 ==0){
                pieces[i][j] = new Piece(true,this,i,j,"bomb");
              }// all the fire pieces

              if ( j == 7 && i%2!=0){
                pieces[i][j] = new Piece(false,this,i,j,"pawn");
              }
              else if( j == 6 && i%2==0){
                pieces[i][j] = new Piece(false,this,i,j,"shield");
              }
              else if( j == 5 && i%2 !=0){
                pieces[i][j] = new Piece(false,this,i,j,"bomb");
              }// all the water pieces

             } 
           }//loop on the whole board
      }//show an empty board 

      }//show a filled board


   
    public Piece pieceAt(int x, int y) {
      if(pieces[x][y] != null){
            return pieces[x][y];
          }
        return null;
    }//returns the position

    public boolean canSelect (int x,int y){
      if (pieceAt(x, y) == null) {
        if (selected == -1 && !moved){
          if (validMove(px, py, x, y)) {
            return true;
          }
        } else if (selected == -1 && p.hasCaptured()){
          if (validMove(px, py, x, y)) {
            return true;
          }
        }
      } else {
        if (pieceAt(x, y).side() == turn % 2) {
          return true;
        }
        if (selected == -1 && !moved){
          return true;
        }
      }
    return false;
  }

private boolean inBounds(int x, int y) {
  return (x < 8 && y<8 && x>=0 && y>=0 );
}

private boolean myTurn(int x, int y) {
  return turn %2 == pieces[x][y].side();
}

private boolean canCapture(int xi, int xf, int yi, int yf) {
  if (pieceAt(xi, yi) != null && pieceAt((xi + xf)/2, (yi +yf)/2) != null) {
    return (pieceAt((xi + xf)/2, (yi +yf)/2).side() != turn%2);
  }
  return false;
}

private boolean validMove(int xi, int yi, int xf, int yf) {
  if (pieces[xf][yf] == null) {
    if (validDestination(xi, yi, xf, yf)) {
      return true;
    }
  }
  return false;
}

private boolean validDestination(int xi, int yi, int xf, int yf) {
  if (!canCapture(xi, yi, xf, yf)) {
    if (pieceAt(xi, yi).isKing()){
      if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
        return true;
      }
    }
    if (pieceAt(xi, yi).isFire()) {
      if (Math.abs(xf - xi) == 1 && yf - yi == 1 && inBounds(xf, yf)) {
        return true;
      }
    } else {
      if (Math.abs(xf - xi) == 1 && yf - yi == -1 && inBounds(xf, yf)) {
        return true;
      }
    }
  } else {
    if (pieceAt(xi, yi).isKing()){
      if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
        return true;
      }
    }
    if (pieceAt(xi, yi).isFire()) {
      if (Math.abs(xf - xi) == 2 && yf - yi == 2 && inBounds(xf, yf)) {
        return true;
      }
    } else {
      if (Math.abs(xf - xi) == 2 && yf - yi == -2 && inBounds(xf, yf)) {
        return true;
      }
    }
  } 
  return false;
}

     
    public void select(int x,int y){
      System.out.println("meow");

        if (selected == 1 ){
          if(pieces[x][y]==null){
            System.out.println("HEY 101");
          return;    
          }//NOT present piece
          else if(pieces[x][y] != null){
            p = pieces[x][y];//hold the whike piece
            px = x;//hold the x value of the piece
            py = y;//hold the y value of the piece 
            selected = selected * -1;///trigger to switch the variable from -ve to positve
            System.out.println("HEY 201");
          }//no piece present
          
        }//not selected
        else if (selected == -1){
          if(pieces[x][y]==null && p!=null ){
            p.move(x,y);//      this piece
            moved =true;
            selected = selected * -1;
            //p = null;
            System.out.println("HEY 3000");
            //canEndTurn();//switch turns
          }//present piece
          
        }//is selected    
      } 

    public void place(Piece p, int x, int y){
      System.out.println("do you run?");
      if (p == null || x>7||y>7 || x <0 || y<0){
         System.out.println("HEY 1000");
         return;
      }//do nothing
      else //if(this.pieceAt(x,y)!= null) //if there is a piece where we movin
      {
        System.out.println("HEY 2ß");
        pieces[x][y] = p;//REPLACE THIS PIECE HERE
      }//piece here
      // else{
      //   System.out.println("HEY 3");
      //   pieces[x][y] = p;

      // }
    } 
    public Piece remove(int x, int y){
      if (x<7 || y<7 || x >0 || y>0){
        
        System.out.println("did i call you??");
        if (pieceAt(x,y) == null){
              
              System.out.println("this is empty");
              return null;
           }//the place is empty
        else{
          System.out.println("removed!");
              Piece pe = pieceAt(x,y) ;

              pieces[x][y] = null;
              return pe;
            }//if not empty

        }//in bounds
        else{
         
          System.out.println("this is out of bounds");
          return null;
          }//out of bound
    }
    public boolean canEndTurn() {
      if (moved){
        //endTurn();//SWITCH turns
        return true;
      }
      return false;
    }

    public void endTurn(){
      selected = 1;
      p.doneCapturing();
      p = null;
      moved = false;
      turn = turn +1;//check who's turn this is for
    }
    public String winner(){
      int fire = 0;
      int water = 0;
      for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
              if (pieces[i][j].isFire())
                  {
                    fire=fire +1;
                  }
              if (pieces[i][j].isFire()==false)
              {
                water=water+1;
              }
            }
          }
          if(fire == 0 && water > 0){
            return "Water";
          }
          if(fire >0 && water ==0){
            return "Fire";
          }
          if (fire == 0 && water == 0){
            return "No one";
          }
          else
            return null;
    
    }

    private void mouser(){
      if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                System.out.println(this.canSelect((int)x,(int)y));
               if(this.canSelect((int)x,(int)y)){

                  this.select((int)x,(int)y);

               }
            }            
            StdDrawPlus.show(10);
    }
    private void spacy(){

       if(StdDrawPlus.isSpacePressed()){
        System.out.println("space pressed"); 
            if(canEndTurn())
            {
              System.out.println("we can do it"); 
              endTurn();
            }
          }
        }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board game = new Board(false);
        game.drawBoard(N);
        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            game.mouser();
            game.drawBoard(N);
            game.spacy();
        }
       
        

        }
    
}








