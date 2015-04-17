public class Board{

  private int player = 0;  //start from FireSide 

  public static void main(String[] args) {   
    int N = 8;
    int side = 0;  
    double x = 0;
    double y = 1;
    boolean valid = false;   
    Board b = new Board(false);
    b.drawBoard(N);  
     
    while (b.winner()==null) {  
    do{          
        if (StdDrawPlus.mousePressed()) {        
          x = StdDrawPlus.mouseX(); //take coordinates of first click
          y = StdDrawPlus.mouseY();
         
          if (x<8 && x>=0 && y <8 && y>=0) {          
            if (b.canSelect((int)x, (int)y)) {
              valid =true;
              b.select((int)x, (int)y);
              b.drawBoard(N);
            }
          }
        }  
        if (b.canEndTurn()) {
          if (StdDrawPlus.isSpacePressed()) {                
          b.endTurn();
          }
        } 
      } while(!valid);
      valid=false;            
      StdDrawPlus.show(100);
      b.drawBoard(N);
    }    
    b.winner();     
  }    

  private Piece[][] pieces = new Piece [8][8];   
  private int x_now=-1;
  private int y_now=-1;  
  private boolean selected = false;
  private boolean moved =false;
  private String winner=null;
   
  public Board(boolean shouldBeEmpty) {
  
    int N =8;  
    if (shouldBeEmpty ==false) {
     initial_position( N);   // empty_Board(N);
    } 
  }

  private void initial_position(int N){         
    String pawn = "pawn";
    String bomb = "bomb";
    String  shield = "shield";
    boolean isFire = true;

    for (int i =0; i<N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) {
          if (j==0) {
           pieces[i][j]=new Piece (isFire, this, i, j, pawn); 
          }
            if (j==1) {
              pieces[i][j]=new Piece (isFire, this, i, j, shield);                  
            }
            if (j==2 ) {
              pieces[i][j]=new Piece (isFire, this, i, j, bomb);            
            }
            if (j==7) {
              pieces[i][j]=new Piece (false, this, i, j, pawn);         
            }
            if (j==6) {            
              pieces[i][j]=new Piece (false, this, i, j, shield);                   
            }
            if (j==5) {            
              pieces[i][j]=new Piece (false, this, i, j, bomb);
            }
          }
        }
      }
    }

  private void drawBoard(int N) { 
    String fire ="fire";
    String water = "water";  
    String crowned = "";                
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
          else StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);  
            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 

            Piece piece = pieces[i][j];

            if(piece !=null ){  
            if(piece.isKing()){
              crowned = "-crowned";
            }    
                                                    
            if (piece.side()==0 ) {  //check if it is Fire                  
              if(piece.isShield()==true){                    
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-" + fire + crowned + ".png", 1, 1);
              } 
              else if(piece.isBomb()==true){
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-" + fire +  crowned +".png", 1, 1);
              }
              else{
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-" + fire +  crowned +".png", 1, 1);                    
              }
            }
              else{                 
                if(piece.isShield()==true){
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-" + water +  crowned +".png", 1, 1);
                } 
                else if(piece.isBomb()==true){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-" + water +  crowned +".png", 1, 1);
                }
                else {                        
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-" + water + crowned + ".png", 1, 1);                    
              }
          }
        }
        crowned ="";
      }
    }
  }     
     
// Gets the piece at position (x, y)
// on the board, or null if there is no piece. 
//If (x, y) are out of bounds, returns null.

  private boolean validMove(int xi, int yi, int xf, int yf){
//Returns true if the piece at (xi, yi) can either move
// to (xf, yf) or capture to (xf, yf), strictly from a 
//geometry/piece-race point of view. validMove does not 
//need to take into consideration whose turn it is or if 
  //a move has already been made, but rather can. Update 
  //(2/6): validMove is not required, and will not be tested. 
  //You may implement this however you want. It is suggested 
  //you use this method to simplify your implementation of canSelect. 
//However, keep this method must be private. 
    Piece pi= pieceAt(xi, yi);
    int isFire = pi.side();
    boolean isKing = pi.isKing();
   
    if (pieceAt(xf, yf)!=null) {
      return false;
    }
     return iscaptureMove(isKing, isFire, xi, yi, xf, yf) || smplMove(isKing,isFire, xi, yi, xf, yf); 
    }

  private boolean iscaptureMove(boolean isKing, int isFire, int xi, int yi, int xf, int yf) {

    if (!isKing) {
      if (isFire==0) {  //for fire
        if (yf-yi==2 && (xf-xi ==2 || xf-xi ==-2)) {
          if (xf>xi && pieceAt(xi+1, yi+1)!=null && pieceAt(xi+1, yi+1).side()!=isFire) {
            return true;
          }
          else if (xf<xi && pieceAt(xi-1, yi+1)!=null && pieceAt(xi-1, yi+1).side()!=isFire) {
            return true;
          }

          else return false;
          }
        }
          
        else if(isFire==1){  //for water
          if (yf-yi==-2 && (xf-xi ==2 || xf-xi ==-2)){
            if (xf>xi && pieceAt(xi+1, yi-1)!=null && pieceAt(xi+1, yi-1).side()!=isFire){
            return true;
            }
            else if (xf<xi && pieceAt(xi-1, yi-1)!=null && pieceAt(xi-1, yi-1).side()!=isFire){
              return true;
            }
            else return false;
            }
          }
        }
    if (isKing){              
      if (yf-yi==2 && (xf-xi ==2 || xf-xi ==-2)) {
        if (xf>xi && pieceAt(xi+1, yi+1)!=null && pieceAt(xi+1, yi+1).side()!=isFire) {
          return true;
        }

        else if (xf<xi && pieceAt(xi-1, yi+1)!=null && pieceAt(xi-1, yi+1).side()!=isFire) {
          return true;
        }
      }

      else if (yf-yi==-2 && (xf-xi ==2 || xf-xi ==-2)){
        if (xf>xi && pieceAt(xi+1, yi-1)!=null && pieceAt(xi+1, yi-1).side()!=isFire){
          return true;
        }
      else if (xf<xi && pieceAt(xi-1, yi-1)!=null && pieceAt(xi-1, yi-1).side()!=isFire){
        return true;
      }
    }
    else return false;
    }
    return false;
  } 

  private boolean smplMove(boolean isKing, int isFire, int xi, int yi, int xf, int yf) { 
    if (pieceAt(xi,yi).hasCaptured()==false) {
      if (!isKing) {
        if(isFire==0){
          if (yf-yi==1 && (xf-xi ==1 || xf-xi ==-1)) return true;
          else return false;
          }
          if (isFire==1) {
            if (yf-yi==-1 && (xf-xi ==1 || xf-xi ==-1)) return true;
            else return false;
          }
        }
      if(isKing){
        if((yf-yi==1 || yf-yi==-1 ) && (xf-xi ==1 || xf-xi ==-1) ) return true;
          else return false;
      }
    }  
    return false;
  }    
 //Gets the piece at position (x, y) on the board,
 // or null if there is no piece. If (x, y) are out of bounds, returns null.

  public Piece pieceAt(int x, int y){
    if (x<8 && x >=0 && y<8 && y >=0){
      Piece piece = pieces[x][y];
      if (piece ==null){
        return null;
      }
      else return piece;
      }
      else return null;
    }
//During this turn, the player has selected a Piece, captured, 
//  and has selected another valid capture destination. 
//  When performing multi-captures, you should only select the active piece once; 
//all other selections should be valid destination points.
  public boolean canSelect(int x, int y){    
    if (pieceAt(x,y)!=null && pieceAt(x,y).side()==player){         //if square is not empty  and none selected before
      if(selected==false || (selected == true && moved==false)){
        return true;
      }
    }
    else{
      if(selected == true && moved ==false && 
        pieceAt(x,y)==null && validMove(x_now, y_now, x, y)){
        return true;
      }
      if(selected ==true&&(pieceAt(x_now, y_now)!=null && pieceAt(x_now, y_now).hasCaptured() == true) && validMove(x_now, y_now, x, y) ){
        return true;
      }
    }      
  return false;
  }
//Selects the square at (x, y). This method assumes canSelect 
//(x,y) returns true. Optionally, it is recommended to color the 
//background of the selected square white on the GUI via the pen 
//color function. For any piece to perform a capture, that piece 
//must have been selected first. If you select a square with a piece,   
// you are prepping that piece for movement. If you select an empty square 
//(assuming canSelect returns true), 
//you should move your most recently selected piece to that square.

  public void select(int x, int y){ //does not call can select
    if (pieceAt(x,y)==null){     
      pieceAt(x_now, y_now).move(x,y);
      x_now = x;
      y_now=y;
      moved= true;
    }
    else {
      x_now = x;
      y_now=y;
      selected=true;      
    }
  }        
  public void place(Piece p, int x, int y){
    Piece old;
    if(p==null){ 
    }
    else{
      if (p!=null){
        pieces[x][y]=p;
      }
    }
  }
 //Places p at (x, y). If (x, y) 
    //is out of bounds or if p is null, 
//does nothing. If p already exists in the current Board, 
//first removes it from its initial position. If another piece already exists 
//at (x, y), p will replace that piece. 
//(This method is potentially useful for creating specific test circumstances.)
  public Piece remove(int x, int y){
    Piece old = pieceAt(x,y);  
    if (x<8 && x >=0 && y>= 0 && y<8  && (old!=null)){        
      pieces[x][y]=null;
      return old;
    }
    else{
      System.out.println("Piece is out of bound");
      return null;
    }
  }
//boolean canEndTurn() - Returns whether or not the the 
//current player can end their turn. To be able to end a turn, 
//a piece must have moved or performed a capture.
  public boolean canEndTurn() {
    if (moved || (moved && pieceAt(x_now, y_now)!=null && pieceAt(x_now, y_now).hasCaptured() ) ){
      return true;
    }   
    return false;   
  }
 //Returns whether or not the the current player can 
 //end their turn. To be able to end a
 // turn, a piece must have moved or performed a capture.
  public void endTurn() {      
    moved =false;    
    selected=false;
    if (player ==0) {
      player =1;
    }
    else if (player ==1){
      player =0;
    }
    if (x_now>0 && pieceAt(x_now,y_now) !=null) {      
      pieceAt(x_now,y_now).doneCapturing();
    }  
  }
//Called at the end of each turn. 
//Handles switching of players and anything else 
//that should happen at the end of a turn.
  public String winner() {   
    int fire=0;
    int water=0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) {
          if (pieces[i][j]!=null){
            if  (pieces[i][j].side()==0){
              fire+=1;
            } 
            else if ((pieces[i][j].side()==1)) {
              water +=1;
            }
          }
        } 
      }
    }
    if (water ==0 && fire != 0) winner= "Fire";   
    else if (water !=0 && fire ==0) winner= "Water";
    else if(water==0 && fire == 0) winner="No one";
    else if(water> 0 && fire > 0) winner=null;
    return winner;
  }
}

//}
// Returns the winner of the game. 
//"Fire", "Water", "No one" (tie / no pieces on the board),
// or null if the game is not yet over. Assume there is no 
//stalemate situation in which the current player has pieces
// but cannot legally move any of them (In this event, just 
//leave it at null). Determine the winner solely by the number
// of pieces belonging to each team.