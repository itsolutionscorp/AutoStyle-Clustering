public class Board {
  private boolean[][] pieces;
  private Piece[][] markers;
  private int N;
  private int turn; // 0 for fire, 1 for water
  private boolean hasMoved;
  private Piece selected;
  private int x, y;
  private Boolean wasBomb;
  
  public static void main(String[] args) {
    Board b = new Board(false);
    b.run();
  }
  
  private void run() {
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);

    /** Monitors for mouse presses. Wherever the mouse is pressed,
      a new piece appears. */
    while(true) {
      drawBoard(N);
      // Handle many things here
      
      // Cases:
      
      // Water's turn
      // Fire's turn
      
      // piece not selected (so select a piece)
      // - cannot select
      // - can select
      // piece selected
      // - can move to
      // - cannot move to
      // - exit w/ spacebar
      // - select another
      // piece moved
      // - cannot move to
      // - exit w/ spacebar
      
      if (StdDrawPlus.mousePressed()) {
        int x = (int) StdDrawPlus.mouseX();
        int y = (int) StdDrawPlus.mouseY();
        
        if (canSelect(x, y)) {
          select(x, y);
          // initially: if it's the opposite side's, you must take turns
        }
        
        /*canSelect(x, y);
        select(x, y);
        pieceAt(x, y);
        place(p, x, y);
        remove(x, y);*/
        
        
        //if (pieces[(int) x][(int) y] )
        
        //pieces[(int) x][(int) y] = true;
      }      
      
      if (StdDrawPlus.isSpacePressed()) {
        // canEndTurn()
        if (canEndTurn()) {
          endTurn();
        }
      }
      StdDrawPlus.show(50);
    }
  }
  
  private void drawBoard(int N) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (markers[i][j] != null && markers[i][j] == selected) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else          StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if (pieces[i][j]) {
          String side;
          if (markers[i][j].isFire()) {
            side = "fire";
          } else {
            side = "water";
          }
          String type = "pawn";
          if (markers[i][j].isBomb()) {
            type = "bomb";
          } else if (markers[i][j].isShield()) {
            type = "shield";
          }
          String king = "";
          if (markers[i][j].isKing()) {
            king = "-crowned";
          }
          StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-" + side + king + ".png", 1, 1);
        }
      }
    }
  }
  
  public Board(boolean shouldBeEmpty) {
    // CRITICAL
    N = 8;
    pieces = new boolean[N][N];
    markers = new Piece[N][N];
    turn = 0;
    hasMoved = false;
    selected = null;
    x = -1;
    y = -1;
    wasBomb = false;
    
    if (shouldBeEmpty) {
      for (int i = 0; i < N; ++i) {
        for (int y = 0; y < N; ++y) {
          pieces[i][y] = false;
          markers[i][y] = null;
        }
      }
    } else {
      for (int i = 0; i <= 6; i += 2) {
        pieces[i][0] = true; // pawn
        markers[i][0] = new Piece(true, this, i, 0, "pawn");
        pieces[i][2] = true; // bomb
        markers[i][2] = new Piece(true, this, i, 2, "bomb");
        pieces[i][6] = true; // shield (other side)
        markers[i][6] = new Piece(false, this, i, 6, "shield");
      }
      for (int i = 1; i <= 7; i += 2) {
        pieces[i][1] = true; // shield
        markers[i][1] = new Piece(true, this, i, 1, "shield");
        pieces[i][5] = true; // bomb (other side)
        markers[i][5] = new Piece(false, this, i, 5, "bomb");
        pieces[i][7] = true; // pawn (other side)
        markers[i][7] = new Piece(false, this, i, 7, "pawn");
      }
    }
  }
  
  public Piece pieceAt(int x, int y) {
    if (unbounded(x, y)) {
      return null;
    }
    return markers[x][y];
  }
  
  
  public boolean canSelect(int x, int y) {
    boolean res = false;
    // determine whether it is water or fire's turn
    if (unbounded(x, y)) {
      return res;
    }
    
    if (pieces[x][y] != false) {
      if (selected == null && wasBomb) {
        res = false;
      } else if (selected == null && pieceAt(x, y).side() == turn) {
        res = true;
      } else if (selected != null && !hasMoved && pieceAt(x, y).side() == turn) {
        res = true;
      }
    } else {
      if (selected != null && !hasMoved) {
        res = validMove(this.x, this.y, x, y);
      } else if (selected != null && selected.hasCaptured()) {
        res = validMove(this.x, this.y, x, y);
      }
    }
    
    return res;
  }
  
  private boolean validMove(int xi, int yi, int xf, int yf) {
    boolean res = false;
    if (!pieces[x][y]) {
      return res;
    }
    Piece p = markers[xi][yi];
    
    int direction;
    if (p.isFire()) {
      direction = 1;
    } else { // is water
      direction = -1;
    }
    
    if (unbounded(xf, yf)) {
      return res;
    } else if (p.isKing() && abs(xf, xi) == 1 && abs(yf, yi) == 1 && !pieces[xf][yf]) {
      res = true;
    } else if (p.isKing() && abs(xf, xi) == 2 && abs(yf, yi) == 2
                && pieces[xf-((xf-xi)/2)][yf-((yf-yi)/2)] && !pieces[xf][yf]) {
      res = true;
    } else if (abs(xf, xi) == 1 && yf - yi == direction && !pieces[xf][yf]) {
      res = true;
    } else if (abs(xf, xi) == 2 && yf - yi == 2*direction
                && pieces[xf-((xf-xi)/2)][yf-direction] && !pieces[xf][yf]) {
      res = true;
      // cover multi-capture
      // actually, does really need to be?
    }
    
    return res;
  }
  
  private int abs(int a, int b) {
    int c = a - b;
    if (c < 0)
      c = -c;
    return c;
  }
  
  public void select(int x, int y) {
    // depends on whether empty or not
    if (unbounded(x, y)) {
      return;
    }
    
    if (pieces[x][y] == false) {
      place(selected, x, y); // or use move() ?
    } else {
      // true, we are selecting a square with a piece
      selected = markers[x][y];
      wasBomb = true;
    }
    this.x = x;
    this.y = y;
  }
  
  private boolean inBoard(Piece p) {
    for (int i = 0; i < 8; ++i) {
      for (int y = 0; y < 8; ++y) {
        if (markers[i][y] == p) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void place(Piece p, int x, int y) {
    if (p == null || unbounded(x, y) || (((x + y) % 2) != 0)) {
      return;
    }
    
    // handle pieces[][]
    int oldx = -1;
    int oldy = -1;
    for (int i = 0; i < 8; ++i) {
      for (int j = 0; j < 8; ++j) {
        if (markers[i][j] == p) {
          oldx = i;
          oldy = j;
        }
      }
    }
    if (oldx == -1) {
      pieces[x][y] = true;
      markers[x][y] = p;
    } else {
      pieces[oldx][oldy] = false;
      pieces[x][y] = true;
      markers[oldx][oldy] = null;
      markers[x][y] = p;
      hasMoved = true;
    
      p.move(x, y);
    }
  }
  
  public Piece remove(int x, int y) {
    Piece res;
    if (unbounded(x, y)) {
      res = null;
    } else if (!pieces[x][y]) {
      res = null;
    } else {
      res = markers[x][y];
      markers[x][y] = null;
      pieces[x][y] = false;
    }
    
    return res;
  }
  
  public boolean canEndTurn() {
    return hasMoved || (selected != null && selected.hasCaptured());
  }
  
  public void endTurn() {
    // and ??
    selected.doneCapturing();
    
    // CRITICAL
    // reset bools
    wasBomb = false;
    hasMoved = false;
    selected = null;
    // 0 for fire, 1 for water
    if (turn == 0) {
      turn = 1;
    } else {
      turn = 0;
    }
    x = -1;
    y = -1;
  }
  
  public String winner() {
    String res;
    int countFire = 0;
    int countWater = 0;
    for (int i = 0; i < 8; ++i) {
      for (int j = 0; j < 8; ++j) {
        if (pieces[i][j]) {
          if (markers[i][j].isFire()) {
            ++countFire;
          } else {
            ++countWater;
          }
        }
      }
    }
    if (countFire == 0 && countWater == 0) {
      res = "No one";
    } else if (countFire > 0 && countWater == 0){
      res = "Fire";
    } else if (countWater > 0 && countFire == 0) {
      res = "Water";
    } else {
      res = null;
    }
    return res;
  }
  
  private boolean unbounded(int x, int y) {
    return x < 0 || y < 0 || x > 7 || y > 7;
  }
}

// 400 expect