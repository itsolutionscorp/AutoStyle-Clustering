public class Board {
  private static final int N = 8;
  private Piece[][] board = new Piece[N][N];
  private int player; // 1 = Fire, 0 = Water.
  private int selectX;
  private int selectY;
  private boolean move;

  private static void drawBoard(boolean empty) {
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);
    // i == x and j == y
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if(empty) {
          continue;
        }
        if(i % 2 == 0) {
          if(j == 0) {
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
          }
          else if(j == 2) {
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
          }
          else if(j ==  6) {
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
          }
        }
        else if(i % 2 == 1) {
          if(j == 1) {
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
          }
          else if(j == 5) {
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
          }
          else if(j == 7) {
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
          }
        }
      }
    }
  }

  private static void drawSquare(int x, int y) {
    if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
  }
  private static String getImg(Piece p) {
    String img_type;
    String king = ".png";
    String color = "-water";
    if(p.isFire()) {
      color = "-fire";
    }
    if(p.isKing())
      king = "-crowned.png";

    if(p.isShield()) {
      img_type = "img/shield" + color + king;
    }
    else if(p.isBomb()) {
      img_type = "img/bomb" + color + king;
    }
    else {
      img_type = "img/pawn" + color + king;
    }
    return img_type;
  }

  public Board(boolean shouldBeEmpty) {
    player = 1;
    move = false;
    selectX = -1;
    selectY = -1;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if(shouldBeEmpty) {
          board[i][j] = null;
          continue;
        }
        if(i % 2 == 0) {
          if(j == 0) {
            board[i][j] = new Piece(true, this, i, j, "pawn");
          }
          else if(j == 2) {
            board[i][j] = new Piece(true, this, i, j, "bomb");
          }
          else if(j ==  6) {
            board[i][j] = new Piece(false, this, i, j, "shield");
          }
        }
        else if(i % 2 == 1) {
          if(j == 1) {
            board[i][j] = new Piece(true, this, i, j, "shield");
          }
          else if(j == 5) {
            board[i][j] = new Piece(false, this, i, j, "bomb");
          }
          else if(j == 7) {
            board[i][j] = new Piece(false, this, i, j, "pawn");
          }
        }
      }
    }
  }

  public Piece pieceAt(int x, int y) {
    if((x > 7 || x < 0) || (y > 7 || y < 0)) {
      return null;
    }
    return board[x][y];
  }

  public Piece remove(int x, int y) {
    if((x > 7 || x < 0) || (y > 7 || y < 0)) {
      System.out.println("Invalid remove! Out of bounds!");
      return null;
    }
    if(board[x][y] == null) {
      System.out.println("Invalid remove! No piece at this coordinate!");
      return null;
    }
    Piece temp = board[x][y];
    board[x][y] = null;
    return temp;
  }

  private boolean validKingMove(int xi, int yi, int xf, int yf, Piece temp) {
    if(((xf == xi + 1) || (xf == xi - 1)) && yf == yi + 1 && !(temp.hasCaptured())) {
      return true;
    }
    else if(((xf == xi + 1) || (xf == xi - 1)) && yf == yi - 1 && !(temp.hasCaptured())) {
      return true;
    }

    if(temp.isFire()) {
      if(yf == yi + 2) {
        if(xf == xi + 2 && (board[xi + 1][yi + 1] != null) && (!board[xi + 1][yi + 1].isFire())) {
          return true;
        }
        else if(xf == xi - 2 && (board[xi - 1][yi + 1] != null) && (!board[xi - 1][yi + 1].isFire())) {
          return true;
        }
      }
      else if(yf == yi - 2) {
        if(xf == xi + 2 && (!board[xi + 1][yi - 1].isFire())) {
          return true;
        }
        else if(xf == xi - 2 && (board[xi - 1][yi - 1] != null) && (!board[xi - 1][yi - 1].isFire())) {
          return true;
        }
      }

    }
    else if(!temp.isFire()) {
        if(yf == yi - 2) {
          if(xf == xi + 2 && (board[xi + 1][yi - 1] != null) && board[xi + 1][yi - 1].isFire()) {
            return true;
          }
          else if(xf == xi - 2 && (board[xi - 1][yi - 1] != null) && board[xi - 1][yi - 1].isFire()) {
            return true;
          }
        }
        if(yf == yi + 2) {
          if(xf == xi + 2 && (board[xi + 1][yi + 1] != null) && board[xi + 1][yi + 1].isFire()) {
            return true;
          }
          else if(xf == xi - 2 && (board[xi - 1][yi + 1] != null) && board[xi - 1][yi + 1].isFire()) {
            return true;
          }
        }
    }
    return false;
  }

  private boolean validMove(int xi, int yi, int xf, int yf) {
    Piece temp = board[xi][yi];
    if(temp == null) {
      return false;
    }
    if(!move || temp.hasCaptured()) {
      if(temp.isKing()) {
        return validKingMove(xi, yi, xf, yf, temp);
      }
      else if(temp.isFire()) {
        if(((xf == xi + 1) || (xf == xi - 1)) && yf == yi + 1 && (!temp.hasCaptured())) {
          return true;
        }
        else if(yf == yi + 2) {
          if(xf == xi + 2 && (board[xi + 1][yi + 1] != null) && (!board[xi + 1][yi + 1].isFire())) {
            return true;
          }
          else if(xf == xi - 2 && (board[xi - 1][yi + 1] != null) && (!board[xi - 1][yi + 1].isFire())) {
            return true;
          }
        }
      }
      else if(!temp.isFire()) {
        if(((xf == xi + 1) || (xf == xi - 1)) && yf == yi - 1 && (!temp.hasCaptured())) {
          return true;
        }
        else if(yf == yi - 2) {
          if(xf == xi + 2 && (board[xi + 1][yi - 1] != null) && board[xi + 1][yi - 1].isFire()) {
            return true;
          }
          else if(xf == xi - 2 && (board[xi - 1][yi - 1] != null) && board[xi - 1][yi - 1].isFire()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean canSelect(int x, int y) {
    if((x > 7 || x < 0) || (y > 7 || y < 0)) {
      return false;
    }
    if(board[x][y] == null) {
      if(selectX != -1 && selectY != -1 && validMove(selectX, selectY, x, y)) {
        return true;
        }
      return false;
    }
    else if(((board[x][y].isFire() && player == 1) || ((!board[x][y].isFire()) && player == 0)) && (!this.move)) {
      return true;
    }
    return false;
  }

  public void select(int x, int y) {
    if(selectX == -1 && selectY == -1) {
      selectX = x;
      selectY = y;
    }
    else if(selectX != -1 && selectY != -1) {
      Piece prev =  board[selectX][selectY];

      if(board[x][y] == null) {
        board[x][y] = prev;
        remove(selectX, selectY);
        move = true;
        prev.move(x, y);
        selectX = x;
        selectY = y;
      }
      else {
        selectX = x;
        selectY = y;
      }
    }
  }

  public void place(Piece p, int x, int y) {
    if((x > 7 || x < 0) || (y > 7 || y < 0) || (p == null)) 
      return;
    board[x][y] = p;
  }
  public boolean canEndTurn() {
    return this.move;
  }

  public void endTurn() {
    if(canEndTurn()) {
      this.move = false;
      if(board[selectX][selectY] != null) {
        board[selectX][selectY].doneCapturing();
      }
      this.selectX = -1;
      this.selectY = -1;
      this.player = 1 - this.player;
    }
  }

  public String winner() {
    int fire = 0;
    int water = 0;
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        if(pieceAt(i, j) == null) {
          continue;
        }
        if(pieceAt(i, j).isFire()) {
          fire++;
        }
        else {
          water++;
        }
      }
    }
    if(fire == 0 && water == 0) {
      return "No one";
    }
    if(fire == 0) {
      return "Water";
    }
    else if(water == 0) {
      return "Fire";
    }
    else {
      return null;
    }
  }

  public static void main(String[] args) {
    boolean shouldBeEmpty = false;
    Board b = new Board(shouldBeEmpty);
    drawBoard(shouldBeEmpty);

    while(true) {
      if(b.winner() != null) {
        System.out.println(b.winner());
        return;
      }
      if(StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();
        boolean can_select = b.canSelect((int) x, (int) y);
        if(can_select) {
          b.select((int) x, (int) y);
          Piece new_p = b.pieceAt((int) x, (int) y);
          if(new_p != null) {
            String img = Board.getImg(new_p);
            StdDrawPlus.picture(((int) x) + .5, ((int) y) + .5, img, 1, 1);
          }
          for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
              if(b.pieceAt(i, j) == null) {
                drawSquare(i, j);
              }
            }
          }
        }
      }
      else if(StdDrawPlus.isSpacePressed()) {
        b.endTurn();
      }
      StdDrawPlus.show(30);
    }
  }
}
