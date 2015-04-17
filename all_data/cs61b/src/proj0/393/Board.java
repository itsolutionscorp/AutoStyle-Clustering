import java.lang.Math;

public class Board {

  private Piece[][] pieces;
  private int turn = 0; //alternate between 0 and 1 for player
  private Piece selectedPiece;
  private boolean pieceMovedThisTurn = false;
  private int selectedX;
  private int selectedY;

  public Board(boolean shouldBeEmpty){
    this.pieces = new Piece[8][8];
    if(shouldBeEmpty){
      return;
    }
    boolean currIsFire = false;
    String currType = "pawn";
    boolean makePiece = false;
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++){
        if(j <= 2){
          currIsFire = true;
        } else {
          currIsFire = false;
        }
        if(j == 0 || j == 7){
          currType = "pawn";
        }
        else if(j == 1 || j == 6){
          currType = "shield";
        }
        else if(j == 2 || j == 5){
          currType = "bomb";
        }
        else{
          makePiece = false;
        }
        if(j % 2 == 0 && i % 2 == 0 ){
          makePiece = true;
        }
        else if(j % 2 != 0 && i % 2 != 0){
          makePiece = true;
        }
        else{
          makePiece = false;
        }
        if(makePiece && j != 3 && j != 4){
          Piece newPiece = new Piece(currIsFire, this, i, j, currType);
          pieces[i][j] = newPiece;
        }
      }
    }
  }

  public static void main(String[] args){
    int N = 8;
    Board b = new Board(false);
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);

    while(true){
      b.drawBoard(N);
      if (StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();
        if(b.canSelect((int) x, (int) y)){
          b.select((int) x, (int) y);
          b.highlight((int) x, (int) y);
        }
      }
      if(StdDrawPlus.isSpacePressed()){
        if(b.canEndTurn()){
          b.endTurn();
        }
      }
      StdDrawPlus.show(25);
    }
  }

  private void highlight(int x, int y){
    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
  }

  private void drawBoard(int N) {
    Piece currPiece;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if (this.pieces[i][j] != null) {
          currPiece = this.pieces[i][j];
          if (currPiece.isKing()){
            if (currPiece.isFire()){
              if (currPiece.isBomb()){
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
              }
              else if(currPiece.isShield()){
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
              }
              else{
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
              }
            }
            else{
              if (currPiece.isBomb()){
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
              }
              else if(currPiece.isShield()){
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
              }
              else{
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
              }
            }
            }
            else{
              if (currPiece.isFire()){
                if (currPiece.isBomb()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
                else if(currPiece.isShield()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                }
                else{
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                }
              }
              else{
                if (currPiece.isBomb()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                }
                else if(currPiece.isShield()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                }
                else{
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
            }
            }
          }
        }
      }
    }

  public Piece pieceAt(int x, int y){
    if(x > 8 || y > 8 || x < 0 || y < 0){
      return null;
    }
    return pieces[x][y];
  }

  public void place(Piece p, int x, int y){
    if(x > 8 || y > 8 || x < 0 || y < 0 || p == null){
      return;
    }
    pieces[x][y] = p;
  }

  public Piece remove(int x , int y){
    if(x > 8 || y > 8 || x < 0 || y < 0){
      System.out.println("Error: Index out of bounds");
      return null;
    }
    Piece p = pieces[x][y];
    if(p == null){
      System.out.println("Error: No Piece at requested position");
      return null;
    }
    pieces[x][y] = null;
    return p;
  }

  public boolean canSelect(int x, int y){
    if( x > 7 || y > 7 || x < 0 || y < 0){
      return false;
    }
    Piece p = pieceAt(x, y);
    if(p != null){
      if(p.side() == turn && (selectedPiece == null || (selectedPiece != null && !pieceMovedThisTurn))){
        return true;
      }
    }
    if(p == null){
      if(selectedPiece != null && !pieceMovedThisTurn && validMove(selectedX, selectedY, x, y)) {
        return true;
      }
    }
    return false;
  }

  public void select(int x, int y){
    if(pieceAt(x, y) == null && selectedPiece != null){
      selectedPiece.move(x, y);
      pieceMovedThisTurn = true;
    }
    else{
      selectedPiece = pieceAt(x, y);
      selectedX = x;
      selectedY = y;
    }
  }

  private boolean validMove(int xi, int yi, int xf, int yf){
    if(xf > 8 || yf > 8 || xf < 0 || yf < 0) return false;
    boolean isValidMove = false;
    int xD = xf - xi;
    if(xD == 0) return false;
    int yD = yf - yi;
    int slope = yD/xD;
    Piece currentP = pieceAt(xi, yi);
    if(currentP != null && currentP.isBomb() && (xD > 2 || yD > 2)){
      return false;
    }
    int i = 0;
    int j = 0;
    boolean prevCaptured = false;
    if((slope != 1) && (slope != -1)){ return false; }
    if(yD > 0 && (currentP.isFire() || currentP.isKing())){ isValidMove = true;}
    else if(yD < 0 && (currentP.isFire() == false || currentP.isKing())){ isValidMove = true; }
    else{ isValidMove = false; }
    if(yD > 0 && slope == 1){
      i = xi + 1;
      j = yi + 1;
      while(i < xf && j < yf){
        currentP = pieceAt(i, j);
        if(prevCaptured){
          prevCaptured = false;
          if(currentP != null){
            return false;
          }
        }
        if(currentP == null){
          if(xD > 1 && i == xi + 1){
            return false;
          }
        }
        else {
          if(currentP.side() == turn){
            return false;
          }
          prevCaptured = true;
        }
        i += slope; j += slope;
      }
    }
    if(yD < 0 && slope == 1){
      i = xi - 1;
      j = yi - 1;
      while(i > xf && j > yf){
        currentP = pieceAt(i, j);
        if(prevCaptured){
          prevCaptured = false;
          if(currentP != null){
            return false;
          }
        }
        if(currentP == null){
          if(xD < 1 && i == xi - 1){
            return false;
          }
        }
        else {
          if(currentP.side() == turn){
            return false;
          }
          prevCaptured = true;
        }
        i += -1; j += -1;
      }
    }
    if(yD > 0 && slope == -1){
      i = xi - 1;
      j = yi + 1;
      while(i > xf && j < yf){
        currentP = pieceAt(i, j);
        if(prevCaptured){
          prevCaptured = false;
          if(currentP != null){
            return false;
          }
        }
        if(currentP == null){
          if(xD < 1 && i == xi - 1){
            return false;
          }
        }
        else {
          if(currentP.side() == turn){
            return false;
          }
          prevCaptured = true;
        }
        i += -1; j += 1;
      }
    }
    if(yD < 0 && slope == -1){
      i = xi + 1;
      j = yi - 1;
      while(i < xf && j > yf){
        currentP = pieceAt(i, j);
        if(prevCaptured){
          prevCaptured = false;
          if(currentP != null){
            return false;
          }
        }
        if(currentP == null){
          if(xD > 1 && i == xi + 1){
            return false;
          }
        }
        else {
          if(currentP.side() == turn){
            return false;
          }
          prevCaptured = true;
        }
        i += 1; j += -1;
      }
    }
    return isValidMove;
  }

  public boolean canEndTurn(){
    return pieceMovedThisTurn;
  }

  public void endTurn(){
    if(turn == 0){
      turn = 1;
    }
    else{
      turn = 0;
    }
    pieceMovedThisTurn = false;
    selectedPiece.doneCapturing();
    selectedPiece = null;
  }

  public String winner(){
    int fire = 0;
    int water = 0;
    Piece p = null;
    for(int i = 0; i < 8; i += 1){
      for(int j = 0; j < 8; j += 1){
        p = pieceAt(i, j);
        if(p != null){
          if(p.isFire()){
            fire += 1;
          }
          else{
            water += 1;
          }
        }
      }
    }
    if(fire > water){
      return "Fire";
    }
    else if(water > fire){
      return "Water";
    }
    else if(fire == 0 && water == 0){
      return "No one";
    }
    else{
      return null;
    }
  }
}
