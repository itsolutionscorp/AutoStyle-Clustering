/* Name: Deepak Talwar
   SID: 22666800
   CS61B Spring 2015: Project 0
*/

public class Board{

  /* Make a 6 x 4 array of pieces, that will store all the Piece objects as
  they are created*/
  private Piece[][] pieces;

  /* To keep track of who's turn it is */
  private int who = 0;

  /* To check whether or not the player has selected a piece */
  private boolean selected = false;

  /* To keep track of whether or not the selected piece has moved */
  private boolean moved;

  /* Location variables for the current selected piece */
  private int xInitial;
  private int yInitial;

  /* Instance variable for the Board class */
  private boolean empty;

  /* Piece that is clicked (may be null) */
  private Piece pieceClicked;

  private Piece[][] defaultPieces;


	// Make a constructor
	public Board(boolean shouldBeEmpty){
    StdDrawPlus.setXscale(0, 8);
    StdDrawPlus.setYscale(0, 8);
    empty = shouldBeEmpty;
    pieces = new Piece[8][8];
    if (!shouldBeEmpty){
      this.piecesConstructor();
    }
    this.drawBoard(8, shouldBeEmpty);
	}

  private void piecesConstructor(){ // call place here
    for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
              if ((j == 0) & (i % 2 == 0)){
                    this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
                  }
                  else if ((j == 1) && (i % 2 == 1)){
                    this.pieces[i][j] = new Piece(true, this, i, j, "shield");
                  }
                  else if ((j == 2) && (i % 2 == 0)){
                    this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
                  }
                  else if ((j == 5) && (i % 2 == 1)){
                    this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
                  }
                  else if ((j == 6) && (i % 2 == 0)){
                    this.pieces[i][j] = new Piece(false, this, i, j, "shield");
                  }
                  else if ((j == 7) && (i % 2 == 1)){
                    this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
                  }
                }
              }
              defaultPieces = this.pieces;
  }

  public void place(Piece p, int x, int y){
    if ((p != null) && (x <= 7) && (y <= 7)){ // CHECK: add conditon to check placeAt function
      //p.x = x;
      //p.y = y;
      pieces[x][y] = p;
    }
  }

	private void drawBoard(int N, boolean shouldBeEmpty) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                String pieceType = "pawn";
                String pieceType2 = "";
                if (!shouldBeEmpty){
                  if (pieces[i][j] != null){
                    
                      if (pieces[i][j].isFire()){
                        if (pieces[i][j].isBomb()){
                          if (pieces[i][j].isKing()){
                            pieceType2 = "-crowned";
                          }
                          pieceType = "bomb";
                        }
                        else if (pieces[i][j].isShield()){
                          if (pieces[i][j].isKing()){
                            pieceType2 = "-crowned";
                          }
                          pieceType = "shield";
                        }
                        else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
                          if (pieces[i][j].isKing()){
                            pieceType2 = "-crowned";
                          }
                          pieceType = "pawn";
                        }
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-fire" + pieceType2 + ".png", 1, 1);
                      }
                      else {
                        
                        if (pieces[i][j].isBomb()){
                          if (pieces[i][j].isKing()){
                            pieceType2 = "-crowned";
                          }
                          pieceType = "bomb";
                        }
                        else if (pieces[i][j].isShield()){
                          if (pieces[i][j].isKing()){
                            pieceType2 = "-crowned";
                          }
                          pieceType = "shield";
                        }
                        else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())) {
                          if (pieces[i][j].isKing()){
                            pieceType2 = "-crowned";
                          }
                          pieceType = "pawn";
                        }
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-water" + pieceType2 + ".png", 1, 1);
                      }
                    }
                  }
                }/*
                else if (shouldBeDefault) { // FIX THIS
                  if (defaultPieces[j][i] != null){
                    if (defaultPieces[j][i].isFire()){
                      System.out.println("here");
                      String pieceType = defaultPieces[j][i].type;
                      StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-fire.png", 1, 1);
                    }
                    else {
                      String pieceType = defaultPieces[j][i].type;
                      StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType + "-water.png", 1, 1);
                    }
                  }

                }*/
            }
        }
      
    
    public Piece pieceAt(int x, int y){
      if ((x <= 7) & (y <= 7)){
        return this.pieces[x][y];
      }
      return null;
    }

    public boolean canSelect(int x, int y){
      Piece p = pieceAt(x, y);
      if ((p != null) && (p.side() == who)) { //CHECK: Assume the piece has not been selected yet
        if ((!selected) || (!moved)){
          System.out.println("Piece exits, your turn, not moved, canSelect-->True");
          this.xInitial = x;
          this.yInitial = y;
          return true;
        }
        else {
          System.out.println("Piece exists, your turn, either selected or moved, canSelect-->False");
          return false;
        }
      }
      else if (p == null) {
        if (selected && validMove(xInitial, yInitial, x, y)) {
            System.out.println("selected:"+selected);
          if ((!moved) || (moved && pieceClicked.hasCaptured())) {
            //System.out.println("Not moved, or moved and captured, canSelect-->True");
            return true;
          }
        }
        System.out.println("Invalid move, canSelect-->False");
        return false;
      }
      return false;
    }


    private boolean validMove(int xi, int yi, int xf, int yf){
      Piece pCurrent = pieceAt(xi, yi);
      Piece pFinal = pieceAt(xf, yf);
      int xd = xf - xi;
      int yd = yf - yi;
      if (pCurrent != null){
        if (!pCurrent.isKing()){
          if (pFinal == null){
            if(pCurrent.isFire()){
              System.out.println("Fire Piece previously clicked and selected");
              return validMoveFire(xi, yi, xf, yf, xd, yd, pCurrent, pFinal);
            }
            else{
              System.out.println("Water Piece previously clicked and selected");
              return validMoveWater(xi, yi, xf, yf, xd, yd, pCurrent, pFinal);
            }
          }
          return false;
        }
        else if (pCurrent.isKing()){
          if (pFinal == null){
            System.out.println("Crowned Piece previously clicked and selected");
            return validMoveKing(xi, yi, xf, yf, xd, yd, pCurrent, pFinal);
          }
          return false;
        }
      }
      return false;
    }

    private boolean validMoveFire(int xint, int yint, int xfin, int yfin, int xd, int yd, Piece pCurr, Piece pFin){
      if (((xd == 1) || (xd == -1)) && (yd == 1)){
        return true;
      }
      else if ((xd == 2) && (yd == 2)){
        Piece pBw = pieceAt(xfin - 1, yfin - 1);
        if ((pBw != null) && (!pBw.isFire())) {
          return true;
        }
        return false;
      }
      else if ((xd == -2) && (yd == 2)){
        Piece pBw = pieceAt(xfin + 1, yfin -1);
        if ((pBw != null) && (!pBw.isFire())) {
          return true;
        }
      }
      return false;
    }

    private boolean validMoveWater(int xint, int yint, int xfin, int yfin, int xd, int yd, Piece pCurr, Piece pFin){
      if (((xd == 1) || (xd == -1)) && (yd == -1)){
        return true;
      }
      else if ((xd == 2) && (yd == -2)){
        Piece pBw = pieceAt(xfin - 1, yfin + 1);
        if ((pBw != null) && (pBw.isFire())) {
          return true;
        }
        return false;
      }
      else if ((xd == -2) && (yd == -2)){
        Piece pBw = pieceAt(xfin + 1, yfin + 1);
        if ((pBw != null) && (pBw.isFire())) {
          return true;
        }
        return false;
      }
      return false;
    }

    private boolean validMoveKing(int xint, int yint, int xfin, int yfin, int xd, int yd, Piece pCurr, Piece pFin){
      if (((xd == 1) || (xd == -1)) && ((yd == -1) || (yd == 1))){
        return true;
      }
      else if ((xd == 2) && (yd == -2)){
        Piece pBw = pieceAt(xfin - 1, yfin + 1);
        if ((pBw != null) && (pBw.isFire() != pCurr.isFire())) {
          return true;
        }//
        return false;
      }
      else if ((xd == 2) && (yd == 2)){
        Piece pBw = pieceAt(xfin - 1, yfin - 1);
        if ((pBw != null) && (pBw.isFire() != pCurr.isFire())) {
          return true;
        }
        return false;
      }
      else if ((xd == -2) && (yd == -2)){
        Piece pBw = pieceAt(xfin + 1, yfin + 1);
        if ((pBw != null) && (pBw.isFire() != pCurr.isFire())) {
          return true;
        }
        return false;
      }
      else if ((xd == -2) && (yd == 2)){
        Piece pBw = pieceAt(xfin + 1, yfin - 1);
        if ((pBw != null) && (pBw.isFire() != pCurr.isFire())) {
          return true;
        }
        return false;
      }
      return false;
    }

    public void select(int x, int y){
      if (pieceAt(x, y) != null) {
        pieceClicked = pieceAt(x, y);
        selected = true;
        StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
      }
      else {
        pieceClicked.move(x,y);
        moved = true;
        this.xInitial = x;
        this.yInitial = y;
        StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        //selected = true;
      }
    }

    public Piece remove(int x, int y){
      if ((x < 8) && (y < 8)){
        Piece pieceToBeRem = pieceAt(x, y);
        if (pieceToBeRem != null){
          pieces[x][y] = null;
          return pieceToBeRem;
        }
        System.out.println("No piece found at ("+x+", "+y+"). Cannot be removed");
        return null;
      }
      System.out.println("("+x+", "+y+") is out of bounds. Cannot be removed");
      return null;
    }

    public boolean canEndTurn(){
      if (moved == true){
        return true;
      }
      return false;
    }

    public void endTurn(){
      if (this.canEndTurn()){
        if (who == 0){
          System.out.println("water's turn");
          who = 1;
        }
        else {
          System.out.println("fire's turn");
          who = 0;
        }
        moved = false;
        selected = false;
        pieceClicked.doneCapturing();
        pieceClicked = null;
      }
    }

    public String winner(){
      int numberWater = 0;
      int numberFire = 0;
      for (int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++){
          Piece pFound = pieceAt(i, j);
          if (pFound != null){
            if (pFound.isFire()){
              numberFire += 1;
            }
            else if (!pFound.isFire()){
              numberWater += 1;
            }
          }
        }
      }
      System.out.println("Fire Pieces:"+numberFire+"...Water Pieces:"+numberWater);
      if ((numberWater == 0) && (numberFire != 0)){
        return "Fire";
      }
      else if ((numberFire == 0) && (numberWater != 0)){
        return "Water";
      }
      else if ((numberFire == 0) && (numberWater == 0)){
        return "No one";
      }
      return null;
    }


   	public static void main(String[] args){
      Board b = new Board(false);
      boolean gameNotFinished = true;
      while (gameNotFinished){
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            int xM = (int) x;
            int yM = (int) y;
            System.out.println(b.pieceAt(xM, yM));
            if (b.canSelect(xM, yM)){
              b.select(xM, yM); // drawBoard inside second if statement of select()
              b.drawBoard(8, false);
              StdDrawPlus.show(100);
            }
          }
        if (StdDrawPlus.isSpacePressed()){
              System.out.println("Space Pressed, Who:"+ b.who);
              if (b.canEndTurn()){
                b.endTurn();
                b.winner();
                if (b.winner() != null){
                  gameNotFinished = false;
                }
              }
            }
          }
   	    }
    


}
