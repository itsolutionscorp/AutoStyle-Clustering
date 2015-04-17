public class Board {

  private Piece[][] pieces = new Piece[8][8];
  private boolean turn = true; // fire goes first
  private Piece selected = null;
  private String winner = "No one";
  private boolean pieceWasMoved = false;
  private boolean placed = false;
  private int selectedx;
  private int selectedy;
  private boolean shouldBeEmpty;
  
  public Board(boolean shouldBeEmpty) {
    this.shouldBeEmpty = shouldBeEmpty;
    if (!shouldBeEmpty) {
      for (int j = 0; j < 8; j++) {
        for (int i = 0; i < 8; i++) {
          if (j == 0) {
            if ((i % 2) == 0) {
                pieces[i][j] = new Piece(true, this, i, j, "pawn");
            }
          }
          else if (j == 1) {
            if ((i % 2) != 0) {
              pieces[i][j] = new Piece(true, this, i, j, "shield");
            }
          }
          else if (j == 2) {
            if ((i % 2) == 0) {
              pieces[i][j] = new Piece(true, this, i, j, "bomb");
            }
          }
          else if (j == 5) {
            if ((i % 2) != 0) {
              pieces[i][j] = new Piece(false, this, i, j, "bomb");
            }
          }
          else if (j == 6) {
            if ((i % 2) == 0) {
              pieces[i][j] = new Piece(false, this, i, j, "shield");
            }
          }
          else if (j == 7) {
            if ((i % 2) != 0) {
              pieces[i][j] = new Piece(false, this, i, j, "pawn");
            }
          }
          else {
            pieces[i][j] = null;
          }
        }
      }
    }
  }

  public Piece pieceAt(int x, int y) {
      if (x > 8 || y > 8) {
        return null;
      }
      return pieces[x][y];
  }


  public boolean canSelect(int x, int y) {
      if (turn) {
        if (selected == null) {
          if (pieces[x][y] != null && pieces[x][y].isFire()) {
            return true;
          }
          return false;
        } else {
          if (!placed && pieces[x][y] != null && pieces[x][y].isFire()) {
            return true;
          }
          else if (validMove(selectedx, selectedy, x, y)) {
            return true;
          }
          return false;
        }
      }   
      else {
        if (!turn) {
        if (selected == null) {
          if (pieces[x][y] != null && !pieces[x][y].isFire()) {
            return true;
          }
          return false;
        } else {
          if (!placed && pieces[x][y] != null && !pieces[x][y].isFire()) {
            return true;
          }
          else if (validMove(selectedx, selectedy, x, y)) {
            return true;
          }
          return false;
        }
      }
      } 
      return false;  
  }

  private boolean canCapture(int xi, int yi, int xf, int yf) {
    int x = xi + (xf-xi)/2;
    int y = yi + (yf-yi)/2;
    if (validMove(xi, yi, xf, yf) && pieces[x][y] != null) {
      if (xi > 8 || yi > 8 || xf > 8 || yf > 8 || pieceAt(xi, yi).isFire() == pieceAt(x,y).isFire()) {
        return false;
      }
      return true;
    }
    return false;
  }

  private boolean validMove(int xi, int yi, int xf, int yf) {
      Piece p = pieceAt(xi, yi); 

      if (p == null) {
        return false;
      }
      else if (p.isFire() && pieces[xf][yf] == null) {
        if (p.isKing() && xf < 8 && yf < 8 && xi != xf && yi != yf 
          && Math.abs(xf-xi) < 3 && Math.abs(yf-yi) < 3) {
          return true;
        }
        else if (yf > yi && xf < 8 && yf < 8 && xi != xf && yi != yf 
          && Math.abs(xf-xi) < 3 && Math.abs(yf-yi) < 3) {
          return true;
        }
      }
      else if (!p.isFire() && pieces[xf][yf] == null) {
        if (p.isKing() && xf < 8 && yf < 8 && xi != xf && yi != yf 
          && Math.abs(xf-xi) < 3 && Math.abs(yf-yi) < 3) {
          return true;
        }
        else if (yf < yi && xf < 8 && yf < 8 && xi != xf && yi != yf 
          && Math.abs(xf-xi) < 3 && Math.abs(yf-yi) < 3) {
          return true;
        }
      }
      return false;
  }

  public void select(int x, int y) {
    Piece p = pieces[x][y];
    if (selected != null && p == null) {
        selected.move(x,y);
        pieceWasMoved = true;
    }
    else if (selected == null && p != null) {
      selected = p;
      selectedx = x;
      selectedy = y;
    }
  }

  public void place(Piece p, int x, int y) {
    if (x > 8 || y > 8 || p == null) {
      return;
    }
    pieces[x][y] = p;

    placed = true;
  }

  public Piece remove(int x, int y) {
      if (x > 8 | y > 8) {
        System.out.println("The coordinates are out of bounds.");
        return null;
      }
      else if (pieces[x][y] == null) {
        System.out.println("There is no piece at this square.");
        return null;
      }
      else {
        Piece p = pieces[x][y];
        pieces[x][y] = null;
        return p;
      }
  }

  /**
   * Returns whether or not the curent player can end their turn.  To do this, a
   * piece must have moved or performed a capture.
   *
   * @return if player can end turn
   */
  public boolean canEndTurn() {
      if (pieceWasMoved == true || (selected != null && selected.hasCaptured() == true)) {
        return true;
      }
      return false;
  }

  /**
   * Ends the turn.  Handles switching of player, and other cleanup tasks.
   */
  public void endTurn() {
     turn = !turn;
     selected = null;
     pieceWasMoved = false;
     placed = false;
  }

  public String winner() {
      int fireCount = 0;
      int waterCount = 0;
      int pieceCount = 0;
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          if (pieces[i][j].isFire()) {
            fireCount = fireCount + 1;
            pieceCount = pieceCount + 1;
          }
          else if (!pieces[i][j].isFire()) {
            waterCount = waterCount + 1;
            pieceCount = pieceCount + 1;
          }
        }
      }

      if (pieceCount != 0 && fireCount == 0) {
        return "Water";
      }
      else if (pieceCount != 0 && waterCount == 0) {
        return "Fire";
      }
      else if (pieceCount == 0) {
        return "No one";
      }
      else {
        return null;
      }
  }

  private static void drawBoard(int N) {
   for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) {
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
          StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        }
        else     { 
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
              StdDrawPlus.filledSquare(i + .5, j + .5, .5);
             // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        }
          //if (pieces[i][j]) {
            //StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
        //  }
      }
    }
        for (int j = 0; j < 8; j++) {
          for (int i = 0; i < 8; i++) {
            if (j == 0) {
              if ((i % 2) == 0) {
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
              }
            }
            else if (j == 1) {
              if ((i % 2) != 0) {
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
              }
            }
            else if (j == 2) {
              if ((i % 2) == 0) {
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
              }
            }
            else if (j == 5) {
              if ((i % 2) != 0) {
                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
              }
            }
            else if (j == 6) {
              if ((i % 2) == 0) {
                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
              }
            }
            else if (j == 7) {
              if ((i % 2) != 0) {
                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
              }
            }
          }
        
}
  }

private static void reDraw(Board b) {
   for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) {
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
          StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        }
        else     { 
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
              StdDrawPlus.filledSquare(i + .5, j + .5, .5);
             // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        }
          //if (pieces[i][j]) {
            //StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
        //  }
      }
    }

  for (int j = 0; j < 8; j++) {
      for (int i = 0; i < 8; i++) {
         if (b.pieces[i][j] != null) {
          if (b.pieces[i][j].isFire()) {
             if (b.pieces[i][j].isKing()) {
                if (b.pieces[i][j].isBomb()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
                else if (b.pieces[i][j].isShield()) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                }
                else {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                }
             }
             else if (!b.pieces[i][j].isKing()) {
                if (b.pieces[i][j].isBomb()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
                else if (b.pieces[i][j].isShield()) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                }
                else {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                }
            }
          }
          else if (!b.pieces[i][j].isFire()) {
            if (b.pieces[i][j].isKing()) {
                if (b.pieces[i][j].isBomb()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                }
                else if (b.pieces[i][j].isShield()) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                }
                else {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crown.png", 1, 1);
                }
             }
             else if (!b.pieces[i][j].isKing()) {
                if (b.pieces[i][j].isBomb()){
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                }
                else if (b.pieces[i][j].isShield()) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                }
                else {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
            }
          }
        }
     }
  }
}

  public static void main(String[] args) {
        Board b = new Board(false);

        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        drawBoard(N);
        
    while (true) {
      if (b.shouldBeEmpty == false) {
        reDraw(b);
            
      if (StdDrawPlus.mousePressed()) {
            // System.out.println("mouse pressed");          
            Double xx = StdDrawPlus.mouseX();
            Double yy = StdDrawPlus.mouseY();
            int x = xx.intValue();
            int y = yy.intValue();
            if (b.canSelect(x,y) == true) {
              b.select(x,y);
              // System.out.println(b.selected);
              if (b.selected != null) {
                b.selected.move(x,y);
                if (b.selected.hasCaptured()) {
                    b.selected.doneCapturing();
                }
              }
            } 
      }

      else if (StdDrawPlus.isSpacePressed() == true) {
        // System.out.println("space pressed");
        if (b.canEndTurn()) {
          b.endTurn();
         // System.out.println("turn ended");
        }
      }

      else if (b.winner.equals("Fire") || b.winner.equals("Water")) {
        return;
      }
    StdDrawPlus.show(100);
    }
  }
}
}

