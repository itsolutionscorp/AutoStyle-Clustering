import java.lang.Math;
/**
 * @author amy huynh with much help from cs61b staff + skeleton code
 */

public class Board {
  /** Location of pieces. */
  private boolean empty;
  private Piece[][] placedPiece;
  private int N;
  private int fireTurn;
  private int xSelected;
  private int ySelected;
  private boolean moved;
  private boolean captive;
  private Piece preppedPiece;
  private int fireSoldiers;
  private int waterSoldiers;

  public Board(boolean shouldBeEmpty){
    empty = shouldBeEmpty;
    N = 8;
    placedPiece = new Piece[N][N];
    fireTurn = 1;
    moved = false;
    xSelected = -1;
    ySelected = -1;
    captive = false;
    preppedPiece = null;

    if (empty) {
      fireSoldiers = 0;
      waterSoldiers = 0;
    }

    if (!empty) {
      fireSoldiers = 12;
      waterSoldiers = 12;
      for (int i = 0; i < 8; i++){ // i == x
        for (int j = 0; j < 8; j++){ //j == y
          if ( ((j == 0) && (i % 2 == 0))){
            placedPiece[i][j] = new Piece(true, this, i, j, "pawn");
          }
          else if ( ((j == 1) && (i % 2 != 0))){
            placedPiece[i][j] = new Piece(true, this, i, j, "shield");
          }
          else if ( ((j == 2) && (i % 2 == 0))){
            placedPiece[i][j] = new Piece(true, this, i, j, "bomb");
          }
          else if ( ((j == 5) && (i % 2 != 0))){
            placedPiece[i][j] = new Piece(false, this, i, j, "bomb");
          }
          else if ( ((j == 6) && (i % 2 == 0))){
            placedPiece[i][j] = new Piece(false, this, i, j, "shield");
          }
          else if ( ((j == 7) && (i % 2 != 0))){
            placedPiece[i][j] = new Piece(false, this, i, j, "pawn");
          }
        }
      }
    }
  }

  public Piece pieceAt(int x, int y){
    if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
      return null;
    }
    else if (placedPiece[x][y] == null){
      return null;
    } else {
      return placedPiece[x][y];
    }
  }

  public void place(Piece p, int x, int y){
    if ( (x < 8) && (y < 8) && (x >= 0) && (y >= 0) && (p != null)) {
      placedPiece[x][y] = p;
      if (p.isFire()){
        fireSoldiers++;
      }
      waterSoldiers++;
    }
  }

  public Piece remove(int x, int y){
    if (placedPiece[x][y] == null){
      return null;
    }
    else if (x > 7 || y > 7){
      return null;
    } else {
      Piece removed = placedPiece[x][y];
      if (removed.isFire()){
        fireSoldiers--;
        System.out.println("hello i have " + fireSoldiers + " firrraahh remov");
      } else {
        waterSoldiers--;
        System.out.println("hello i have " + fireSoldiers + " watrrrr removed");
      }
      placedPiece[x][y] = null;
      return removed;
    }

  }

  private boolean validMove(int xi, int yi, int xf, int yf){ //MAKE PUBLIC
    if (xf > 7 || yf > 7 || xf < 0 || yf < 0){
      return false;
    }
    if (placedPiece[xf][yf] != null){
      return false;
    }
    int xDiff = xf - xi;
    int yDiff = yf - yi;
    if (Math.abs(xDiff) == Math.abs(yDiff)) {
      if (moved && !captive){
        return false;
      }
      if (placedPiece[xi][yi].isKing()){ //is a king
        if (xDiff <= 2 && xDiff >= -2){
          if ((Math.abs(xDiff) == 1) && (!captive)) {//singleton
            return true;
          }
          else if (Math.abs(xDiff) == 2) { //capturing
            if ( (xDiff < 0) && (placedPiece[xf - 1][yf + 1] != null)){
              boolean sameTeam = (placedPiece[xf - 1][yf + 1].side() ==
                                  placedPiece[xi][yi].side() );
              if (!sameTeam){
              return true;
              }
            }
            else if ( (xDiff > 0) && (placedPiece[xf - 1][yf - 1] != null)) {
              boolean sameTeam = (placedPiece[xf - 1][yf - 1].side() ==
                                  placedPiece[xi][yi].side() );
              if (!sameTeam){
              return true;
              }
            }
            else if ( (xDiff < 0) && (placedPiece[xf + 1][yf + 1] != null)){
              boolean sameTeam = (placedPiece[xf + 1][yf + 1].side() ==
                                  placedPiece[xi][yi].side() );
              if (!sameTeam){
              return true;
              }
            }
            else if ( (xDiff > 0) && (placedPiece[xf + 1][yf - 1] != null)) {
              boolean sameTeam = (placedPiece[xf + 1][yf - 1].side() ==
                                  placedPiece[xi][yi].side() );
              if (!sameTeam){
              return true;
              }
              return true;
            }
            return false;
          }
        }
      }
      else if (placedPiece[xi][yi].isFire()) { //is a fire piece
        if ((Math.abs(xDiff) == 1) && (yDiff > 0) &&
            (placedPiece[xf][yf] == null) && (!placedPiece[xi][yi].isKing())
            && (!captive)){
          return true;
        }
        else if ((Math.abs(xDiff) == 2) && (yDiff > 0)){
          if ( (xDiff < 0) && (placedPiece[xf + 1][yf - 1] != null)){
            //captures something
            return true;
          }
          else if ( (xDiff > 0) && (placedPiece[xf - 1][yf - 1] != null)) {
            return true;
          }
        }
      }
      else if (!placedPiece[xi][yi].isFire()){ //is water piece
        if ((Math.abs(xDiff) == 1) && (yDiff < 0) &&
            (placedPiece[xf][yf] == null) && (!placedPiece[xi][yi].isKing())
            && (!captive)){ // moves one away
          return true;
        }
        else if ((Math.abs(xDiff) == 2) && (yDiff < 0)){
          if ( (xDiff < 0) && (placedPiece[xf + 1][yf + 1] != null)){
            //captures something
            return true;
          }
        }
        else if ( (xDiff > 0) && (placedPiece[xf - 1][yf + 1] != null)) {
          captive = true;
          return true;
        }
      }
    }
    return false;
  }

  public void select(int x, int y){
    if (placedPiece[x][y] != null){ //prepping for movement
      xSelected = x;
      ySelected = y;
      preppedPiece = placedPiece[x][y];
    }

    else if (placedPiece[x][y] == null && preppedPiece != null){
      System.out.println("moving " + xSelected + " " + ySelected + "to "
                         + x +
                         " " + y);
      moved = true;
      preppedPiece.move(x, y);
    }
  }

  public boolean canSelect(int x, int y){
    if (placedPiece[x][y] != null){// selecting ur piece
      if (fireTurn == 1){ //fire's turn
        if (placedPiece[x][y].isFire()){ // //if fire selected fire piece
          if (preppedPiece == null){ //yet to select piece
            return true;
          }
          else if ((preppedPiece != null) && (!this.moved)){
            //selected, yet to move
            return true;
          }
        }
      } else { //water's turn
        if (!placedPiece[x][y].isFire()){
          if (preppedPiece == null){ //yet to select
            return true;
          }
          else if ((preppedPiece != null) && (!this.moved)){
            //selected, yet to move
            return true;
          }
        }
      }
    }
    else { //selecting place to go to
      if ((preppedPiece != null) && (!this.moved) &&
          (this.validMove(xSelected, ySelected, x, y))){
        return true;
      }
      else if ((preppedPiece != null) && (this.moved) &&
               (this.captive) && (this.validMove(xSelected, ySelected, x, y))){
        return true;
      }
    }
    return false;
  }

  public boolean canEndTurn(){
    if (this.moved || captive){
      return true;
    }
    return false;
  }

  public void endTurn(){
    if (this.canEndTurn()){
      fireTurn *= -1;
    }
    if (preppedPiece != null){
      preppedPiece.doneCapturing();
    }
    moved = false;
    xSelected = -1;
    ySelected = -1;
    captive = false;
    preppedPiece = null;
  }

  public String winner(){
    if ( (fireSoldiers == 0) && (waterSoldiers == 0)){
      return "No one";
    }
    else if (fireSoldiers > 0 && waterSoldiers == 0){
      return "Fire";
    }
    else if (waterSoldiers > 0  && fireSoldiers == 0){
      return "Water";
    }
    return null;
  }

  private void drawBoard() { //MAKE PUBLIC
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
      }}
    if (!this.empty) {
      for (int i = 0; i < 8; i++){ // i == x
        for (int j = 0; j < 8; j++){ //j == y
          if ( ((j == 0) && (i % 2 == 0))){
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
          }
          else if ( ((j == 1) && (i % 2 != 0))){
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
          }
          else if ( ((j == 2) && (i % 2 == 0))){
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
          }
          else if ( ((j == 5) && (i % 2 != 0))){
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
          }
          else if ( ((j == 6) && (i % 2 == 0))){
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
          }
          else if ( ((j == 7) && (i % 2 != 0))){
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
          }
        }
      }
    }
  }

  private String imageName(int x, int y){
    Piece current = placedPiece[x][y];
    String pieceName = "img/";
    if (current.isShield()){
      System.out.println(current);
      if (current.isFire()){
        pieceName += "shield-fire";
        System.out.println(current);
        if (current.isKing()){
          System.out.println(current);
          pieceName += "-king";
        }
      } else {
        pieceName += "shield-water";
        if (current.isKing()){
          pieceName += "-king";
        }
      }
    }
    else if (current.isBomb()){
      if (current.isFire()){
        pieceName += "bomb-fire";
        if (current.isKing()){
          pieceName += "-king";
        }
      } else {
        pieceName += "bomb-water";
        if (current.isKing()){
          pieceName += "-king";
        }
      }
    } else{
      if (current.isFire()){
        pieceName += "pawn-fire";
        if (current.isKing()){
          pieceName += "-king";
        }
      } else {
        pieceName += "pawn-water";
        if (current.isKing()){
          pieceName += "-king";
        }
      }
    }
    return pieceName += ".png";
  }


  private void drawContinuedBoard() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (placedPiece[i][j] != null) {
          String url = this.imageName(i, j);
          StdDrawPlus.picture(i + .5, j + .5, url, 1, 1);
        }
      }
    }
  }
  
  public static void main(String[] args) { //can't reference nonstatic methods
    int N = 8;
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);
    //if false draw pieces, if true... don't!! but how do we check this??!!!****
    Board b = new Board(false);
    b.drawBoard();
    while (true){
      if (StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();
        // if **space is pressed**
        if (b.canSelect((int) x, (int) y)){
          StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
          StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
          b.select((int) x, (int) y);
        }
      }
      else if (StdDrawPlus.isSpacePressed()){
        if (b.canEndTurn()){
          b.endTurn();
        }
      }
      StdDrawPlus.show(100);
      b.drawContinuedBoard();
    }
  }
}
