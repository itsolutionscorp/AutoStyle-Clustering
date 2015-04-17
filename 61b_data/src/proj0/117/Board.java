public class Board {
  private Piece current = null;
  private int enemyEatenX;
  private int enemyEatenY;
  private int side = 0; //keeps track of which side can go
  private boolean selected = false;
  private boolean moved = false;
  private int size = 8;
  private Piece[][] pieces;
  private int mouseCoorX;
  private int mouseCoorY;
  private int mouseCoorXnew;
  private int mouseCoorYnew;
  private String name;
  private Piece selection;
  private boolean valid = false;
  private static boolean isEmpty = true;
  private static boolean useGUI = false;

  public static void main(String args[]){
    if(args.length > 0){
       isEmpty = false;
    }
    useGUI = true;
    Board game = new Board(isEmpty);
    game.playCycle(isEmpty);
  }

  public Board(boolean shouldBeEmpty){
    pieces = new Piece[size][size];
    if(!shouldBeEmpty){ //Initializes pieces if NOT bool is false.
      for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
          if((i+j)%2 ==0){
            if(j==0){
              pieces[(int) i][(int) j] = new Piece(true, this, i, j, "pawn");
            } else if(j==1){
              pieces[(int) i][(int) j] = new Piece(true, this, i, j, "shield");
            } else if(j==2){
              pieces[(int) i][(int) j] = new Piece(true, this, i, j, "bomb");
            } else if(j==size - 3){
              pieces[(int) i][(int) j] = new Piece(false, this, i, j, "bomb");
            } else if(j==size - 2){
              pieces[(int) i][(int) j] = new Piece(false, this, i, j, "shield");
            } else if(j==size - 1){
              pieces[(int) i][(int) j] = new Piece(false, this, i, j, "pawn");
            }
          }
        }
      }
    }
  }

  private void playCycle(boolean notShowPieces){
    drawBoard(!notShowPieces);
    if(notShowPieces == false){
      while(true){
        if(selected == false && StdDrawPlus.mousePressed() && moved == false){
          mouseCoorX = (int) StdDrawPlus.mouseX();
          mouseCoorY = (int) StdDrawPlus.mouseY();
          if(canSelect(mouseCoorX,mouseCoorY)){
            select(mouseCoorX,mouseCoorY);
          }
        } else if (StdDrawPlus.mousePressed() && selected && moved == false){
          mouseCoorXnew = (int) StdDrawPlus.mouseX();
          mouseCoorYnew = (int) StdDrawPlus.mouseY();
          if(mouseCoorX != mouseCoorXnew && mouseCoorY!=mouseCoorYnew){
            if(canSelect(mouseCoorXnew,mouseCoorYnew)){
              select(mouseCoorXnew,mouseCoorYnew);
            }
          }

        } else if(StdDrawPlus.mousePressed() && selected && current != null && current.hasCaptured()){
          mouseCoorX = mouseCoorXnew;
          mouseCoorY = mouseCoorYnew;
          fillHole(mouseCoorX, mouseCoorY);
          if(useGUI){
            StdDrawPlus.picture(mouseCoorX, mouseCoorY, nameAssembler(current),1,1);
          }
          mouseCoorXnew = (int) StdDrawPlus.mouseX();
          mouseCoorYnew = (int) StdDrawPlus.mouseY();
          if(canSelect(mouseCoorXnew,mouseCoorYnew)){
            if(mouseCoorX != mouseCoorXnew && mouseCoorY!=mouseCoorYnew){
              place(current, mouseCoorXnew,mouseCoorYnew);
            }
          }

          }

          if(StdDrawPlus.isSpacePressed() && moved){
            if(canEndTurn()){
              endTurn();
            }
          }

        StdDrawPlus.show(23);

      }
    }
  }

  private void drawBoard(boolean includePieces){
    StdDrawPlus.setScale(0,size);
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        fillHole(i,j);
        if(includePieces){
          if((i+j)%2 ==0){
            if(j==0){
              StdDrawPlus.picture(i+0.5,j+0.5, "img/pawn-fire.png" ,1,1);
            } else if(j==1){
              StdDrawPlus.picture(i+0.5,j+0.5, "img/shield-fire.png" ,1,1);
            } else if(j==2){
              StdDrawPlus.picture(i+0.5,j+0.5, "img/bomb-fire.png" ,1,1);
            } else if(j==size - 3){
              StdDrawPlus.picture(i+0.5,j+0.5, "img/bomb-water.png" ,1,1);
            } else if(j==size - 2){
              StdDrawPlus.picture(i+0.5,j+0.5, "img/shield-water.png" ,1,1);
            } else if(j==size-1){
              StdDrawPlus.picture(i+0.5,j+0.5, "img/pawn-water.png" ,1,1);
            }
          }
        }
      }
    }
  }

  private void fillHole(int x, int y){
    if((x+y)%2 == 0){
      StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    } else {
      StdDrawPlus.setPenColor(StdDrawPlus.RED);
    }
    StdDrawPlus.filledSquare(x+0.5,y+0.5,0.5);
  }


  public Piece pieceAt(int x, int y){
    if(x < size && y < size && x >= 0 && y >= 0 && pieces[x][y] != null){
      return pieces[x][y];
    } return null;
  }

  private boolean validDirection(int xi, int yi, int xf, int yf){
    Piece checkingPiece = pieceAt(xi, yi);
    for(int n = 1; n<3; n++){
        if(Math.abs((int) (yf - yi)) == n && Math.abs((int) (xi - xf)) == n){
          if(checkingPiece.isKing()){
              valid = true;
          } else {
            if(checkingPiece.side() == 1 && yf < yi){
              //Fire check
              valid = true;
            } else if (checkingPiece.side() == 0 && yf > yi){
              //Water Check
              valid = true;
            }
          }
          if(n > 1){
            valid = canCapture(xi, yi, xf, yf);
          }
      }

    } return valid; //False after all checks have failed
  }

  private void eatOther(int xi, int yi, int xf, int yf){
    if(canCapture(xi, yi, xf, yf)){
      if(current.isBomb()){
        for(int n = -1; n<0; n++){
          for(int m = -1; m<0; m++){
            Piece victim = pieceAt(xf + n, yf + m);
            if(victim != null && !victim.isShield()){
              remove(xf + n, yf + m);
            }
          }
        }
      }
      remove(enemyEatenX,enemyEatenY);
    }
  }

  private boolean canCapture(int xi, int yi, int xf, int yf){
    if(pieceAt(xf, yf) == null){
      boolean xDiffbool = (xf - xi) > 0;
      boolean yDiffbool = (yf - yi) > 0;
      int xDiff;
      int yDiff;
      if(xDiffbool){
        xDiff = 1;
        if(yDiffbool){
          yDiff = 1;
        } else {
          yDiff = -1;
        }
        } else {
          xDiff = -1;
          if(yDiffbool){
            yDiff = 1;
          } else {
            yDiff = -1;
          }
        }
        enemyEatenX = xi + xDiff;
        enemyEatenY = yi + yDiff;
        if(enemyEatenX < 0 || enemyEatenY < 0 || enemyEatenX > size || enemyEatenY >= size){
          return false;
        }
        if(pieceAt(enemyEatenX,enemyEatenY) == null){ //if nothing is there, its false!
          return false;
        } else if(pieceAt(enemyEatenX,enemyEatenY).side() == pieceAt(xi,yi).side()){ //if same side, no capture!
          return false;
        } else {
          return pieceAt(enemyEatenX, enemyEatenY) != null;
        }
      }

    return false; //cannot capture
  }

  private boolean canMove(int x, int y){ //checks if piece can move on fresh turn
    if(pieceAt(x,y).isKing()){
      return validDirection(x, y, x+1, y+1) || validDirection(x, y, x+1, y-1) || validDirection(x, y, x-1, y-1) || validDirection(x, y, x-1, y+1);
    } else if (pieceAt(x,y).isFire()){
      if((x + 1 < size && pieceAt(x+1, y+1) == null) || (x - 1 >= 0 && pieceAt(x-1, y+1) ==null)){
        return true;
      } else {
        return (x + 2 < size && validDirection(x, y, x+2, y+2)) || (x - 2 >= 0 && validDirection(x, y, x-2, y+2));
      }
    } else {
      if((x + 1 < size && pieceAt(x+1, y-1) == null) || (x - 1 >= 0 && pieceAt(x-1, y-1) ==null)){
        return true;
      } else {
        return (x + 2 < size && validDirection(x, y, x+2, y-2)) || (x - 2 >= 0 && validDirection(x, y, x-2, y-2));
      }
    }
  }

  public boolean canSelect(int x, int y){
    selection = pieceAt(x,y);
    //Sanity checks:
    if(x > size || y > size || 0 > x || 0 > y){ //checks to ensure in bounds
      return false;
    }

    //Selecting piece checks
    if(selection != null && side == selection.side()){
      if(selected == false){
          return true;
      }
      if(selected && moved == false){ //checks to make sure that the object has not moved
          return true;
      }
    //Selecing move candidate checks
    } else if (selection == null){
      if(current == null){ //checks to make sure that current is there
        return false;
      }
      if(moved == false || current.hasCaptured()){
        if(validDirection(mouseCoorX, mouseCoorY, x,y) && canMove(mouseCoorX,mouseCoorY)){
          return true;
      } else if(selected && current.hasCaptured()){
          return true;
      }
  }
}
  return false;
}

  private boolean needEat(int xi, int yi, int xf, int yf){
    if(Math.abs(xi - xf) > 1 || Math.abs(yi - yf) > 1){
      return true;
    }
    return false;
  }

  public void select(int x, int y){
    selection = pieceAt(x,y);
    if(selection != null){
      if(!useGUI){
        mouseCoorX = x;
        mouseCoorY = y;
      }
      current = selection;
      selected = true;
      if(useGUI){
        drawPieceSelect(current, x, y, true);
      }
    } else if (selection == null){
      if(useGUI){
        drawPieceSelect(current, x, y, false);
      }
        current.move(x,y);
        moved = true;
    }
  }

  private void drawPieceSelect(Piece p, int x, int y, boolean selecting){
    if(selecting){
      StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
      StdDrawPlus.filledSquare(x+0.5,y+0.5,0.5);
    } else {
      fillHole(x,y);
    }
    StdDrawPlus.picture(x+0.5, y+0.5, nameAssembler(current), 1,1);
  }

  private String nameAssembler(Piece p){
    name = "";
    //type
    if(p ==null){
      return "";
    }
    if(p.isBomb()){
      name += "bomb-";
    } else if (p.isShield()){
      name += "shield-";
    } else {
      name += "pawn-";}
    //team
    if(p.isFire()){
      name += "fire";
    } else {
      name += "water";}
    if(p.isKing()){
      name += "-crowned";
    }
    return "img/" + name + ".png";
  }

  public void place(Piece p, int x, int y){
    if(x < size  && y < size && x >= 0 && y >= 0 && p!=null){
        pieces[x][y]=p;
        if(useGUI){
          drawPieceSelect(p, x, y, true);
        }
        if(p.isBomb()&&p.hasCaptured()){
          remove(x,y);
          current = null;
          if(useGUI){
            fillHole(x,y);
          }
        }
        if(useGUI){
          fillHole(mouseCoorX,mouseCoorY);
        }
    }
  }

  public Piece remove(int x, int y){
    Piece removePiece = pieceAt(x, y);
    if(x < size && y < size){
      if(removePiece!=null){
        pieces[x][y] = null;
        if(useGUI){
          fillHole(x,y);
        }
        return removePiece;
      } else {
        return null;
      }
    }
    return null;
  }

  public boolean canEndTurn(){
    if(current == null) { //for use when bomb has exploded or nothing has been selected
      return moved; //returns current side has moved
    }
    return (moved || current.hasCaptured()); //returns current side has moved or current has captured
  }

  public void endTurn(){
    if(side == 0){
      side = 1;
    } else {
      side = 0;
    }
    moved = false;
    selected = false;
    valid = false;
    try{
    if(current != null){
      current.doneCapturing();
      if(useGUI){
        fillHole(mouseCoorXnew,mouseCoorYnew);
        StdDrawPlus.picture(mouseCoorXnew+0.5, mouseCoorYnew+0.5, nameAssembler(current), 1,1);
      }
      current = null;
    }
    } catch (NullPointerException e){
      if(useGUI){
        fillHole(mouseCoorXnew,mouseCoorYnew);
      }
    }
  }

  public String winner(){
    int water = 0;
    int fire = 0;
    for(int n = 0; n < size; n++){
      for(int m = 0; m < size; m++){
        Piece temp = pieceAt(n,m);
        if(temp != null){
          if(temp.isFire()){
            fire += 1;
          } else {
            water += 1;
          }
        }
      }
    }
    if(water == 0 && fire > water) {
      return "Fire";
    } else if (fire == 0 && fire < water){
      return "Water";
    } else if(water == 0 && water == fire){
      return "No one";
    }
    return null;
  }

}
