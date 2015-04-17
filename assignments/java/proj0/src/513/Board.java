public class Board{

    private  Piece[][] pieces;
    private Piece selectedPiece;
    private boolean isFireTurn;
    private int xCoord;
    private int yCoord;
    private boolean actionTaken;


    public static void main(String[] args){

      StdDrawPlus.setXscale(0,8);
      StdDrawPlus.setYscale(0,8);
      Board b = new Board(false);


      while(true){
        StdDrawPlus.show(100);


        b.drawBoard();

        if (StdDrawPlus.mousePressed()){
          int x = (int) StdDrawPlus.mouseX();
          int y =(int) StdDrawPlus.mouseY();
          if (x<8 && y<8 && x>=0 && y>=0 && b.canSelect(x,y))
            b.select(x ,  y);
        }

        if (StdDrawPlus.isSpacePressed()){
          b.endTurn();
        }

      }
    }


    private void drawBoard(){

      for (int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++){
          if (selectedPiece != null && pieces[i][j]==selectedPiece)
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
          else if ((i+j) % 2 == 0)
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
          else
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
          StdDrawPlus.filledSquare(i+.5, j + .5, .5);
          StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
          if (pieces[i][j] != null){
            StdDrawPlus.picture(i + .5, j + .5, getImgLink(pieces[i][j]), 1 ,1);

          }
        }
      }
    }

    private String getImgLink(Piece p){


    String link = "img/";
    if (p.isBomb()){
      link+="bomb-";
      if (p.isFire())
        link+="fire";
      else
        link+="water";
    }
    else if (p.isShield()){
      link+="shield-";
      if (p.isFire())
        link+="fire";
      else
        link+="water";
    } else {
      link+="pawn-";
      if (p.isFire()){
        link+="fire";
      }else{
        link+="water";
      }
    }

    if (p.isKing())
      link+="-crowned";
    return link+".png";

    }


    public Board(boolean shouldBeEmpty){
      pieces = new Piece[8][8];
      selectedPiece = null;
      isFireTurn = true;

      if (!shouldBeEmpty){
        for (int i = 0; i < 8; i++){
          for (int j = 0; j < 8; j++){
            if ((i + j) % 2 == 0 && j != 3 && j != 4 ){
              Piece pointer = null;
              if (j == 0){
                pointer = new Piece(true,this,i,j,"pawn");
                pieces[i][j] = pointer;

              }else if (j == 1){
                pointer = new Piece(true,this,i,j,"shield");
                pieces[i][j] = pointer;

              }else if (j ==2){
                pointer = new Piece(true,this,i,j,"bomb");
                pieces[i][j] = pointer;
              }else if (j == 5){
                pointer = new Piece(false, this,i,j,"bomb");
                pieces[i][j]=pointer;
              }else if (j ==6){
                pointer = new Piece(false, this, i, j, "shield");
                pieces[i][j] = pointer;
              }else{
                pointer = new Piece(false,this, i, j, "pawn");
                pieces[i][j] = pointer;
              }



            }
          }
        }
      }


    }


    public Piece pieceAt(int x, int y){
      if (x>=0 && x<8 && y>=0 && y<8)
        return pieces[x][y];
      return null;
    }

    public void place(Piece p, int x, int y){
      if (x > 7 || y > 7)
        return;
      pieces[x][y]= p;

    }

    public Piece remove(int x, int y){
      if (x> 7 || y > 7){
        System.out.println("Out of bounds.");
        return null;
      }
      if (pieceAt(x,y) == null){
        System.out.println("No piece at these coordinates!");
        return null;
      }
      Piece p = pieceAt(x,y);
      pieces[x][y]=null;
      return p;
    }

    public String winner(){
      int fire = 0;
      int water = 0;
      for (int i = 0; i < 8; i ++){
        for (int j = 0; j < 8; j++){
          Piece p = pieces[i][j];
          if(p != null){
            if(p.isFire())
              fire++;
            else
              water++;
          }

        }
      }

      if (water == 0 && fire == 0){
        return "No one";
      }else if (water == 0){
        return "Fire";
      }else if (fire == 0){
        return "Water";
      }

      return null;
    }

    public boolean canSelect(int x, int y){
      if (pieces[x][y] !=null){
        if (pieces[x][y].isFire() == isFireTurn){
          if(!actionTaken){
            return true;
          }
        }
        else
          return false;
      }
      else{
        if (selectedPiece != null){
          return validMove(xCoord,yCoord,x,y,selectedPiece.isKing());
        }


      }
      return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf, boolean isKing){
      // http://stackoverflow.com/questions/19106350/explanation-of-the-safe-average-of-two-numbers
      // for safer average

      if (isKing){
        if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1 && !actionTaken){

            return true;

        }
        else if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2){
          if ( pieces[(xf+xi) >>> 1][(yf+yi) >>> 1].isFire() != pieces[xi][yi].isFire()){
            return true;
          }
        }
        return false;
      }
      else{
        if (Math.abs(xf-xi) == 1 && ((yf-yi==1 && isFireTurn) || (yi-yf == 1 && !isFireTurn)) && !actionTaken){

            return true;

        }
        else if (Math.abs(xf-xi) == 2 && ((yf-yi==2 && isFireTurn) || (yi-yf == 2 && !isFireTurn)) && pieces[(xf+xi) >>> 1][(yf+yi) >>> 1] != null){
          if (pieces[(xf+xi) >>> 1][(yf+yi) >>> 1].isFire() != pieces[xi][yi].isFire()){
            return true;
          }
          }


      }
      return false;

    }


    public void select(int x, int y){

        if (pieceAt(x,y)!= null){
          selectedPiece = pieceAt(x,y);

        }
        else{
          selectedPiece.move(x,y);
          actionTaken = true;
        }
        xCoord = x;
        yCoord = y;

    }

    public boolean canEndTurn(){
      return actionTaken;
    }

    public void endTurn(){
      if (canEndTurn()){
       selectedPiece.doneCapturing();
        selectedPiece = null;
        isFireTurn = !isFireTurn;
        actionTaken = false;
      }
    }
}
