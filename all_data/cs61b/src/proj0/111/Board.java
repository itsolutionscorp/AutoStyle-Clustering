public class Board {

// Added this comment at 12:24 AM 14th Feb 2015

  private Piece[][] pieces;
  private int N;
  private int turn; 
  private boolean hasMoved = false;
  private boolean hasSelected = false;
  private int xi;
  private int yi;
  private Piece selectedPiece;
  private int selectedPieceX;
  private int selectedPieceY;

  public Board(boolean shouldBeEmpty) {
      N = 8;
      StdDrawPlus.setXscale(0, N);
      StdDrawPlus.setYscale(0, N);
      pieces = new Piece[N][N];
      drawDefaultBoard(N,shouldBeEmpty);
  }

    private void drawDefaultBoard(int N, boolean shouldBeEmpty) {
    //pieces = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
              if (!shouldBeEmpty) {
                if ((j == 0) && (i % 2 == 0)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                  Piece p = new Piece(true, this, i, j, "pawn");
                  //pieces[i][j] = p;
                  place(p, i, j);
                }
                if ((j == 1) && (i % 2 != 0)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                  Piece p = new Piece(true, this, i, j, "shield");
                  //pieces[i][j] = p;
                  place(p, i, j); 
                }
                if ((j == 2) && (i % 2 == 0)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                  Piece p = new Piece(true, this, i, j, "bomb");
                  //pieces[i][j] = p;
                  place(p, i, j); 
                }
                if ((j == 5) && (i % 2 != 0)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                  Piece p = new Piece(false, this, i, j, "bomb");
                  //pieces[i][j] = p;
                  place(p, i, j); 
                }
                if ((j == 6) && (i % 2 == 0)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                  Piece p = new Piece(false, this, i, j, "shield");
                  //pieces[i][j] = p;
                  place(p, i, j); 
                }
                if ((j == 7) && (i % 2 != 0)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                  Piece p = new Piece(false, this, i, j, "pawn");
                  //pieces[i][j] = p;
                  place(p, i, j); 
                }
              }
      }   
    }
  }


  public static void main(String[] args) {
      int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N); 
// moved above information about board to the constructor
        Board b = new Board(false); // initializes default board
       while(true) {
            

            if (StdDrawPlus.mousePressed()) { // check for mouse press 1
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY(); 
              if (b.canSelect((int) x, (int) y)) {
                  b.select((int) x, (int) y);
              } 
              else System.out.println("you're turn is over, press spacebar if you haven't already"); 
                  b.drawBoard(N);
                  StdDrawPlus.show(100);
                 if (b.winner() != null) {
                      System.out.println(b.winner());
                      break;
                  }
            }

            if (StdDrawPlus.isSpacePressed()) {
              if (b.canEndTurn()) {
                b.endTurn();
              }
              //else System.out.println("you cannot end your turn before making a move!");
            }
          }  
System.out.println("GAME IS OVER");
  }

  private void drawBoard(int N) {
    //pieces = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (i == selectedPieceX && j == selectedPieceY)
                  StdDrawPlus.filledSquare(i + .5, j + .5, .5);
              //if (!shouldBeEmpty) {
                if (pieceAt(i,j) != null) {
                  if (!pieces[i][j].isKing()) {
                    if (pieces[i][j].isFire()) {
                      if (pieces[i][j].isBomb()) {
                          StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                      }
                      else if (pieces[i][j].isShield()) {
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                      }
                      else {
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); 
                      }
                    }
                    else { // is water
                      if (pieces[i][j].isBomb()) {
                          StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                      }
                      else if (pieces[i][j].isShield()) {
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                      }
                      else {
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1); 
                      }
                    }
                  }  
                  else { // for king pieces
                    if (pieceAt(i,j).isFire()) {
                      if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                      }
                      else if (pieces[i][j].isShield()) {
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                      }
                      else {
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1); 
                      }
                    }
                    else {
                      if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                      }
                      else if (pieces[i][j].isShield()) {
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                      }
                      else {
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1); 
                      }
                    }
                  }
                } 
              //}   
            }     
        }
    }  
  public Piece pieceAt(int x, int y) {
    if ((x < 8) && (y < 8)) {
      return pieces[x][y];
    }
    return null;
  }

  public void place(Piece p, int x, int y) {
    //p.x = x;
    //p.y = y;
    pieces[x][y] = p;
  }

  public Piece remove(int x, int y) {
    if ((x > 7) || (y > 7)) {
      System.out.println("Index is off the board");
      return null;
    }
    if (pieceAt(x,y) != null) {
      Piece thePiece = pieces[x][y];
      pieces[x][y] = null;
      return thePiece;
    }
    // if pieces x y is null:
    else {
      System.out.println("no piece here");
      return null;
    }
  }

  public boolean canEndTurn() {
    if (hasMoved == true) 
      return true;
    else return false;
  }

  public void endTurn() {
    if (turn == 1) {
      System.out.println("it is now fire's turn");
      turn = 0;
    }
    else if (turn == 0) {
      System.out.println("it is now water's turn");
      turn = 1;
    }
    hasMoved = false;
    hasSelected = false;
    selectedPiece.doneCapturing();
  }


  public void select(int x, int y) {
    if (pieceAt(x,y) != null) {
      hasSelected = true;
      selectedPiece = pieceAt(x,y);
      selectedPieceX = x;
      selectedPieceY = y;
      drawBoard(N);
    }
    else { 
      selectedPiece.move(x,y);
      xi = x;
      yi = y;
      hasMoved = true;

      //selectedPiece = null;
    }
  }

  public String winner() {
    int fireRemain = 0;
    int waterRemain = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (pieceAt(i,j) != null) {
          if (pieceAt(i,j).isFire()) fireRemain++;
          else waterRemain++;
        } 
      }
    }
    if (fireRemain == 0 && waterRemain == 0) {
      return "No one";
    }
    else if (fireRemain == 0 && waterRemain != 0) {
      return "Water";
    }
    else if (fireRemain != 0 && waterRemain == 0) {
      return "Fire";
    }
    return null;
  }



  public boolean canSelect(int x, int y) {
      Piece p = pieceAt(x,y); // get the piece at the clicked position or null
      if ((p != null) && (p.side() == turn)) {  // is there a piece and is it of your type
        if (!hasSelected) { // has previously been selected
          xi = x;
          yi = y;
          return true;
        }
        else if (!hasMoved) { // if something has previously been selected, check if it has not already moved
          xi = x;
          yi = y;
          return true;
        }
        else {
          return false; // has been selected and has moved
        }
      }
      else if (p == null) { // empty spot selected
        if (hasSelected && validMove(xi,yi,x,y)) { // a piece has already been selected and moving to x y is a valid move
          if (!hasMoved) { 
            return true;
          }
          else if (hasMoved && selectedPiece.hasCaptured()) {
            System.out.println("multicapture should be working");
            return true;
          }
        }
        else return false; // hasn't selected a piece yet, or its not a valid move
      }
      return false; // clicking off board?
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
      Piece pcurr = pieceAt(xi,yi);
      Piece pdest = pieceAt(xf,yf);
      if (pcurr != null){
      if (!pcurr.isKing()) {    // check if king piece
        if (pcurr.isFire()) {   // check if non crowned fire type piece
          if (pdest == null) { // check if destination is empty
            if ((Math.abs(xi-xf) == 1) && (yf - yi == 1)) { // check if the empty slot is one above and one to left or right
              return true;
            }
            else if ((xi-xf == 2) && (yf - yi == 2)) { // check if capturing toward left diagonal
            if ((pieceAt(xi-1,yi+1) != null) && (!pieceAt(xi-1,yi+1).isFire())) {
              return true;
            }
            else return false; // no piece to capture or capturing your own piece 
            }
            else if ((xf-xi == 2) && (yf - yi == 2)) { // check if capturing toward right diagonal
            if ((pieceAt(xi+1,yi+1) != null) && (!pieceAt(xi+1,yi+1).isFire())) {
              return true;
          }
          else return false;   // no piece to capture or capturing your own piece
        }
            else return false; // you are clicking more than 2 blocks away  
          }
          return false; //destination not empty
        }
        else { // non-crowned water piece
          if (pdest == null) { // check if destination is empty
            if ((Math.abs(xi-xf) == 1) && (yi - yf == 1)) { // check if the empty slot is one above and one to left or right
              return true;
            }
            else if ((xi-xf == 2) && (yi - yf == 2)) { // check if capturing toward left diagonal
            if ((pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire())) {
              return true;
            }
            else return false; // no piece to capture or capturing your own piece 
            }
            else if ((xf-xi == 2) && (yi - yf == 2)) { // check if capturing toward right diagonal
            if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire())) {
              return true;
          }
          else return false;   // no piece to capture or capturing your own piece
        }
            else return false; // you are clicking more than 2 blocks away  
          }
          return false; // destination not empty
        } 
      }
      else { // for crowned pieces
          if (pdest == null) {
            if ( (Math.abs(xf-xi) == 1) && (Math.abs(yf-yi) == 1) ) { //check if diagonally moving to any of the 4 valid diagonal positions
              return true;
            }
            else if ( (xf-xi == 2) && (yf - yi == 2) ) {  // capturing on right top diagonal 
              if ( (pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() != pcurr.isFire()) ) { // there is a piece in between and it isn't your kind
              return true;
          }
          else return false;  
            }
            else if ( (xi-xf == 2) && (yf - yi == 2) ) {  // capturing on left top diagonal 
              if ( (pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() != pcurr.isFire()) ) { // there is a piece in between and it isn't your kind
              return true;
          }
          else return false;  
            }
            else if ( (xi-xf == 2) && (yi - yf == 2) ) {  // capturing on left bottom diagonal 
              if ( (pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() != pcurr.isFire()) ) { // there is a piece in between and it isn't your kind
              return true;
          }
          else return false;  
            }
            else if ( (xf-xi == 2) && (yi - yf == 2) ) {  // capturing on right bottom diagonal 
              if ( (pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() != pcurr.isFire()) ) { // there is a piece in between and it isn't your kind
              return true;
          }
          else return false; // clicking more than 2 blocks away
            }
          }
          else return false; // clicked spot is not null
      }
    }
      return false;
  } 

} 
