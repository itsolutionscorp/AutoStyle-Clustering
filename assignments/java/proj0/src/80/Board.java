public class Board{

    private static final int NO_SELECTION = 10;
    private static final int N = 8;

    private boolean shouldBeEmpty; 
    private Piece[][] pieces;
    private boolean turn;  
    private int moves;
    private int selectXPointer = NO_SELECTION; 
    private int selectYPointer = NO_SELECTION; 
    
    /**
       * The constructor for Board. If shouldBeEmpty is true,
       * initializes an empty Board. Otherwise, initializes a Board
       * with the default configuration. Note that an empty Board 
       * could possibly be useful for testing purposes. 
       */
    public Board(boolean shouldBeEmpty){
      this.turn = true;
      this.moves = 0;
      this.selectXPointer = NO_SELECTION; 
      this.selectYPointer = NO_SELECTION; 
      this.shouldBeEmpty = shouldBeEmpty; 
      this.pieces = new Piece[N][N];
      this.drawBoard(N);
    }

    /**
     * Draws N x N board with default setup unless the 
     * user specifies for an empty board. 
     */
    private void drawBoard(int size) {
      if (shouldBeEmpty == false){
        for (int i = 0; i < size; i++) {
          for (int j = 0; j < size; j++) {
            if ((i + j) % 2 == 0){
              StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
              StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
              if (j == 0) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                  pieces[i][j] = new Piece(true, this, i, j, "pawn");
                } else if (j == 1) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                  pieces[i][j] = new Piece(true, this, i, j, "shield");
                } else if (j == 2) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                  pieces[i][j] = new Piece(true, this, i, j, "bomb");
                } else if (j == (size-1)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                  pieces[i][j] = new Piece(false, this, i, j, "pawn");
                } else if (j == (size-2)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                  pieces[i][j] = new Piece(false, this, i, j, "shield");
                } else if (j == (size-3)) {
                  StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                  pieces[i][j] = new Piece(false, this, i, j, "bomb"); 
                } else {
                  pieces[i][j] = null;
                }
            } else {
              StdDrawPlus.setPenColor(StdDrawPlus.RED);
              StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
              pieces[i][j] = null;
            }   
          }
        }
      } else{
        for (int i = 0; i < size; i++) {
              for (int j = 0; j < size; j++) {
                  if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    pieces[i][j] = null;
                  } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                  }
                  StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
              }
          }
      }  
    }

    private static boolean inBounds(int x){
      if (x >= 0 && x < N){
        return true; 
      } else{
        return false; 
      }
    }
    /**
     * Gets the piece at position (x, y) on the board, or null
     * if there is no piece. If (x, y) are out of bounds, returns null.
     */
    public Piece pieceAt(int x, int y){
      if (pieces.length > 0){
        if ((inBounds(x)) && (inBounds(y)) && (pieces[x][y]!= null)){
          return pieces[x][y]; 
        } else {
          return null; 
        }
      }
      return null; 
    }

    /**
     * Places p at (x, y). If (x, y) is out of bounds
     * or if p is null, does nothing. If another piece already
     * exists at (x, y), p will replace that piece. (This method 
     * is potentially useful for creating specific test circumstances.)
     * MUST SPECIFIY PEN COLOR BEFORE IMPLEMENTING PLACE METHOD
     */
    public void place(Piece p, int x, int y){
      if (pieces.length > 0){
        if (((x < N) && (y < N)) && (p!=null)){
            StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
            pieces[x][y] = p; 
            if (p.isFire()){
              if (p.isBomb()){
                if (!p.isKing()){
                  StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                } else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
              } else if (p.isShield()){
                if (!p.isKing()){
                  StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                } else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                }
              } else{
                if (!p.isKing()){
                  StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                } else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                }
              }
            } else {
              if (p.isBomb()){
                if (!p.isKing()){
                  StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                } else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                }
              } else if (p.isShield()){
                if (!p.isKing()){
                  StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                } else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                }           
              } else{
                if (!p.isKing()){
                  StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                } else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                }
              }
            }
          }
        }
      }


    /**
     * validMove is not required, and will not be tested. You may implement this however 
     * you want. It is suggested you use this method to simplify your implementation 
     * of canSelect. However, keep this method must be private.
     */
    private boolean validMove (int xi, int yi, int xf, int yf){
      boolean valid = false; 
      if ((xf < N) && (yf < N) && (xi >= 0) && (yi >= 0)&&
        (xf >= 0) && (yf >= 0) && (xi < N) && (yi < N)){
        Piece p = pieceAt(xi, yi); 
        Piece d = pieceAt(xf, yf);
        if (d==null){
          if ((Math.abs(xi-xf)==1) && !p.hasCaptured()){
            if ((p.isFire() && (yf-yi == 1)) || ((!p.isFire()&&p.isKing()) && (yf-yi == 1))){
              valid = true; 
            } else if ((!p.isFire() && (yf-yi == -1)) || ((p.isFire()&&p.isKing()) && (yf-yi == -1))){
              valid = true; 
            }
          } else if (Math.abs(xi-xf)==2){
            if ((p.isFire() && (yf-yi == 2) && (pieceAt(((xf+xi)/2), ((yf+yi)/2)) != null) && !(pieceAt(((xf+xi)/2), ((yf+yi)/2)).isFire())) ||
              (!p.isFire() && p.isKing() && (Math.abs(yf-yi) == 2) && (pieceAt(((xf+xi)/2), ((yf+yi)/2)) != null) && (pieceAt(((xf+xi)/2), ((yf+yi)/2)).isFire()))){
              valid = true; 
            }
          else if ((!p.isFire() && ((yf-yi) == -2) && (pieceAt(((xf+xi)/2), ((yf+yi)/2)) != null) && (pieceAt(((xf+xi)/2), ((yf+yi)/2)).isFire())) ||
              (p.isFire() && p.isKing() && (Math.abs(yf-yi) == 2) && (pieceAt(((xf+xi)/2), ((yf+yi)/2)) != null) && !(pieceAt(((xf+xi)/2), ((yf+yi)/2)).isFire()))){
              valid = true; 
            }
          }
        }
      } 
        return valid; 
    }


    /**
     * Returns true if the square at (x, y) can be selected.
     */
    public boolean canSelect(int x, int y){
      boolean selector = false; 
      if (pieces.length > 0) {
        if ((x<N) && (y<N)) {
          boolean validFireSelect = (pieces[x][y]!=null) && pieceAt(x,y).isFire() && turn;
          boolean validWaterSelect = (pieces[x][y]!=null) && !pieceAt(x,y).isFire() && !turn;
          if (moves==0){
            if (validFireSelect || validWaterSelect){
              selector = true;  
            //An empty square can be selected only if it is a valid destination
            } else if (validMove(selectXPointer,selectYPointer,x, y)){
              selector = true; 
            }
          } else if ((pieces[x][y]==null) && validMove(selectXPointer,selectYPointer,x, y) && pieces[selectXPointer][selectYPointer].hasCaptured()){
              selector = true; 
          }
        }
      } 
      return selector;      
    }


    /**
     * Selects the square at (x, y). This method assumes canSelect (x,y) returns true.
     * Optionally, it is recommended to color the background of the selected square 
     * white on the GUI via the pen color function. For any piece to perform a capture,
     * that piece must have been selected first. If you select a square with a piece,
     * you are prepping that piece for movement. If you select an empty square (assuming
     * canSelect returns true), you should move your most recently selected piece to that square.
     */
    private void doSelection(int x, int y){
      Piece p = pieceAt(x, y);
      StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
      StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
      this.place(p, x, y); 
      if (p == null){
        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
      }
      selectXPointer = x; 
      selectYPointer = y; 
    }

    public void select(int x, int y) {
      if (this.pieceAt(x,y)!=null){
        if (!this.canEndTurn()){
          this.resetSelect(); 
        }
        this.doSelection(x, y);
      } else if (this.validMove(selectXPointer, selectYPointer, x, y)){
        Piece current = this.pieceAt(selectXPointer, selectYPointer);
        current.move(x,y);
        //System.out.println(this.pieceAt(selectXPointer, selectYPointer)); 
        this.doSelection(x,y); 
        moves += 1;  
      }
    }

    private void resetSelect(){
      if (selectXPointer!=NO_SELECTION && selectYPointer!=NO_SELECTION){
        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        StdDrawPlus.filledSquare(selectXPointer + 0.5, selectYPointer + 0.5, 0.5);
        Piece p = pieceAt(selectXPointer, selectYPointer);
        this.place(p, selectXPointer, selectYPointer);
      }
    }
    /**
     * Executes a remove. Returns the piece that was removed. If the input (x, y) 
     * is out of bounds, returns null and prints an appropriate message. If there 
     * is no piece at (x, y), returns null and prints an appropriate message.
     */
    public Piece remove(int x, int y){
      if (((x < N) && (y < N))){
        if (pieceAt(x,y)!=null){
          Piece p = pieceAt(x, y); 
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
          StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
          pieces[x][y] = null; 
          return p; 
        } else {
          System.out.println("No piece at this spot."); 
          return null; 
        }
      } else {
        System.out.println("Spot is out of bounds."); 
        return null; 
      }
    }

    /**
     * Returns whether or not the the current player can end their turn.
     * To be able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn(){
      if (moves > 0){
        return true; 
      } else{
        return false; 
      }
    }



     /**
     * Called at the end of each turn. Handles switching of players
     * and anything else that should happen at the end of a turn.
     */   
    public void endTurn(){
      Piece p = pieceAt(selectXPointer, selectYPointer);
      this.resetSelect(); 
      moves = 0;
      if (p!=null){
        p.doneCapturing(); 
      }
      turn = !turn; 
      selectXPointer = NO_SELECTION; 
      selectYPointer = NO_SELECTION;
    }

     /**
     * Returns the WINNER of the game: "Fire", "Water", "No one", or null.
     * If only fire pieces remain on the board, fire WINS. If only water pieces
     * remain, water wins. If no pieces remain (consider an explosion capture),
     * no one wins. If the game is still in progress (ie there are pieces from both
     * sides still on the board) return null. Assume there is no stalemate situation in
     * which the current player has pieces but cannot legally move any of them (In this event, 
     * just leave it at null). Determine the winner solely by the number of pieces 
     * belonging to each team.
     */ 
    public String winner(){
      int water_count = 0; 
      int fire_count = 0; 
      for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            if (pieceAt(i,j)!=null){
              if (pieceAt(i,j).isFire()){
                fire_count += 1; 
              } else{
                water_count += 1; 
              }
            }
          }
      }
      if ((fire_count > 0) && (water_count ==0)){
        return "Fire"; 
      } else if ((fire_count == 0) && (water_count > 0)){
        return "Water"; 
      } else if ((fire_count ==0) && (water_count==0)){
        return "No one"; 
      } else {
        return null; 
      }
    }


  /**
     * Starts a GUI supported version of the game.  
     */
  public static void main(String[] args) {
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);
    Board b = new Board(false);

    while (b.winner()==null) {
        //user clicks somewhere on the board
        if (StdDrawPlus.mousePressed()) {
          double pointX = StdDrawPlus.mouseX();
          double pointY = StdDrawPlus.mouseY(); 
          int x = (int) pointX; 
          int y = (int) pointY; 

          if (b.canSelect(x,y)){
              b.select(x, y);
          }
          StdDrawPlus.show(100);
        }
        //user presses the space bar 
        if (StdDrawPlus.isSpacePressed()){
          if (b.canEndTurn()){
            b.endTurn(); 
          }
        StdDrawPlus.show(100);
      }
    }
  }
}