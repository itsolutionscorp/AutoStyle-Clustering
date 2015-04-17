public class Board {
   /** Location of pieces. */
    private Piece[][] piece;
    private boolean turn = true; // fire goes first therefore turn==true is fire's turn
    private Piece selected = null;
    private boolean pieceWasMoved = false;
    //private boolean canCapture= false;
    private int selectx;
    private int selecty;

  /**
  * Constructor for a Board.
  *
  * @param shouldBeEmpty  returns an empty board if true
  * @return               a Board
  */
    public Board(boolean shouldBeEmpty) {
      if (shouldBeEmpty){
        piece = new Piece[8][8];
      } else {
        piece = new Piece[8][8];

        // set up board

        for (int i=0; i<8; i++){
          if (i%2==0) piece[i][0]= new Piece (true, this, i, 0, "pawn");
        }

        for (int i=0; i<8; i++){
          if (i%2==1) piece[i][1]= new Piece (true, this, i, 1, "shield");
        }

        for (int i=0; i<8; i++){
          if (i%2==0) piece[i][2]= new Piece (true, this, i, 2, "bomb");
        }

        for (int i=0; i<8; i++){
          if (i%2==1) piece[i][5]= new Piece (false, this, i, 5, "bomb");
        }

        for (int i=0; i<8; i++){
          if (i%2==0) piece[i][6]= new Piece (false, this, i, 6, "shield");
        }

        for (int i=0; i<8; i++){
          if (i%2==1) piece[i][7]= new Piece (false, this, i, 7, "pawn");
        }
      }
    }

  /**
  * Returns the piece at (x, y) if there is a piece, or null otherwise.
  *
  * @param x, y  the coordinates to check
  * @return      the Piece at (x, y), or null
  */
    public Piece pieceAt(int x, int y) {
      if (outOfBounds(x,y)){  //outofbounds
        return null;
      } else { //inbound
        return piece[x][y];
      }
    } 

    private boolean outOfBounds (int x, int y){
      if (x>7 || x<0 || y>7 || y<0){
        return true;
      } else {
        return false;
      }
    }
  /** validMove method */

    private boolean canCapture(int xi, int yi, int xf, int yf){
      int x= (xi+xf)/2;
      int y= (yi+yf)/2;
      if (pieceAt(xi,yi)!=null){
        if (validMove(xi,yi,xf,yf)&&pieceAt(x,y)!=null){
          if (pieceAt(xi,yi).isFire()){
            if (!pieceAt(x,y).isFire()){
              return true;
            } else {
              return false;
            }
          }else{
            if (pieceAt(x,y).isFire()){
              return true;
            } else {
              return false;
            }
          }
        } else {
          return false;
        }
      } else {
        return false;
      }
    }

    private boolean validMove (int xi, int yi, int xf, int yf){
      System.out.print("asdfasf");
      if (pieceAt(xi,yi)!=null){
        System.out.println("hiadsfasdfdasfas");
        if ((piece[xi][yi].isFire() == this.turn)){ // it the right turn
          System.out.print("hi");
         if (!this.outOfBounds(xf, yf) && this.piece[xf][yf]==null){ //if inbound and empty
          if (!pieceAt(xi,yi).isKing()){ //piece is NOT King
            if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){//jumping over something
              if (pieceAt(xi,yi).isFire() && yf>yi) { //is Fire and only moves upwards
                if (pieceAt((xf+xi)/2,yf-1)!=null && !pieceAt((xf+xi)/2,yf-1).isFire() ){ //piece in between is water and NOT empty
                  return true;
                } else { //the piece inside is NOT water or is empty
                  return false;
                }
              } else if (!pieceAt(xi,yi).isFire() && yf<yi) { //is Water and only moves downwards
                if (pieceAt((xf+xi)/2,yf+1)!=null && pieceAt((xf+xi)/2,yf+1).isFire()){ //there is a piece in between and is fire
                  return true;
                } else { //the piece is either not fire, or square is empty
                  return false;
                }
              } else { //either is water and moves up or is fire and moves down
                return false;
              }
            } else if(Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){ //next to
              if (pieceAt(xf,yf)==null) {//is empty
                if (pieceAt(xi,yi).isFire()&& yf>yi){ //is fire and goes up
                  return true;
                } else if (!pieceAt(xf,yf).isFire() && yf<yi){ //is water and goes down
                  return true;
                } else { //either is fire but goes down or is water and goes up
                  return false;
                }
              } else { // square you want to move to is taken
                return false;
              }
            } else { // jumping too far
              return false;
            }
          } else { //is King
            if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){//jumping over something
              if (pieceAt(xi,yi).isFire()) {//is Fire
                if (!pieceAt((xi+xf)/2, (yi+yf)/2).isFire()){ //middle piece is water
                  //canCapture(xi,xf,yi,yf)=true;
                  return true;
                } else {
                  return false;
                }
              } else { //is water
                if (pieceAt((xi+xf)/2, (yi+yf)/2).isFire()){ //middle piece is Fire
                  return true; 
                } else {
                  return false;
                }
              }
            } else if ((Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1)){ //next to
              if (pieceAt(xf,yf)!=null) { // empty
                return true;
              } else { //not Empty
                return false;
              }
            } else { //jump too far
              return false;
            }
          }
        } else { // out of bounds or has a piece
          return false;
        }
      } else { // it is not your turn
        return false;
      }
    } else{
      return false;
    }
  }

      /** 1. Fire moves up diagonally
        2. Water moves down diagonally
        3. King can move in all diagonal directions
      return true; //for now. DO NOT FORGET YOU IDIOT.
    }
    */

  /**
   * Returns true if the square at (x, y) can be selected.  
   * A square or a piece may be selected if it's the corresponding player's turn, and one of the
   * following is true:
   * 1)
   * - the player has not selected a piece yet
   * - the player has selected a piece, but did not move it. 
   * 2) an empty square may be selected if:
   * -- the player has selected a Piece which hasn't moved, and is selecting an
   * empty spot which is a valid move for the previously selected Piece
   * -- the player has selected a Piece, captured, and has selected another
   * valid capture destination
   *
   * @param x, y  the coordinates to check
   * @return      the Piece at (x, y), or null
   */

    public boolean canSelect(int x, int y) {
      Piece selectedP=this.piece[x][y]; //@t=0 what you are checking to select
      if (this.turn==true){ //Fire's turn
        if (selectedP!=null){ //clicking on a piece
          if ((selectedP.isFire()) /*selected Fire piece*/  &&  selected==null || this.pieceWasMoved==false){
            return true;
                } else {
                  return false;
                }
        } else { //if an empty space
          if (this.selected!=null /*selected a piece */ && !this.pieceWasMoved && validMove(this.selectx, this.selecty, x,y)){
            return true;
          } else if (this.selected!=null /* selected a piece */&& this.selected.hasCaptured() && canCapture(this.selectx,this.selecty,x,y)) {
            return true;
          } else {
            return false;
          }
        }
      } else { //water's turn
        if (selectedP!=null){ //if not an empty space
          if (!(selectedP.isFire()) /*selected Water piece*/  && this.pieceWasMoved==false){
            return true;
                } else {
                  return false;
                }
        } else { //if an empty space
          if (this.selected!=null && !this.pieceWasMoved && validMove(this.selectx, this.selecty, x,y)){
            return true;
          } else if (selected!=null /* selected a piece*/ && this.selected.hasCaptured() && canCapture(this.selectx, this.selecty, x,y)) {
            return true;
          } else {
            return false;
          }
        }
      }
    }


  /**
   * Selects the piece at (x, y) if possible.  Should color the background of
   * the selected square
   *
   * @param x, y  the coordinates of the piece to select
   */
    public void select(int x, int y) {
      
        if (pieceAt(x,y)!=null){ //not an empty square
          this.selected=piece[x][y];
          this.selectx=x;
          this.selecty=y;
        } else { //empty square
          if (selected != null) {
            selected.move(x,y);
            this.selectx=x;
            this.selecty=y;
            this.pieceWasMoved=true;
          }
        }
    }

  /**
   * Places piece p at (x, y).  If (x, y) is out of bounds or p is null, does
   * nothing.  If another piece already exists at (x, y), p will replace that piece. 
   *(This method is potentially useful for creating specific test circumstances.)
   * @param p     the Piece to place
   * @param x, y  the coordinates to place p at
   */
    public void place (Piece p, int x, int y) {
        if (p != null && !outOfBounds(x,y)) {
          piece[x][y]=p;
        }
     }

  /**
  * Removes piece at (x, y) and returns it.  If (x, y) is out of bounds, returns
  * null and prints error message.  If there is no piece at (x, y), returns null
  * and prints error message.
  *
  * @param x, y  the coordinates to remove a piece from
  */
    public Piece remove(int x, int y) {
      Piece p=this.piece[x][y];
      if (this.outOfBounds(x, y)==true){
        System.out.println("THIS IS FUCKING OUT OF BOUNDS.");
        return null;
      } else if (p==null){
        System.out.println("THIS IS INVALID YOU FUCK.");
        return null;
      } else {
        this.piece[x][y]=null;
        return p;
      }
    }

  /**
   * Returns whether or not the current player can end their turn.  To do this, a
   * piece must have moved or performed a capture.
   *
   * @return if player can end turn
   */
    public boolean canEndTurn() {
      if (this.selected != null && (this.selected.hasCaptured()==true || this.pieceWasMoved==true)){
        return true;
      } else {
        return false;
      }
    }

  /**
   * Ends the turn.  Handles switching of player, and other cleanup tasks.
   */
    public void endTurn() {
      if (canEndTurn()){
        if (this.turn==true){
         this.turn=false;
        } else {
         this.turn=true;
        }
      this.selected=null;
      this.pieceWasMoved=false;
      this.winner();
      }
    }

  /**
   * Returns the winner of the game.
   *
   * @return "Fire", "Water", "No one" (if game is not over, or tie)
   */
    public String winner() {
      int fireCount = 0;
      int waterCount = 0;
      for (int x=0; x<=7; x++){
        for (int y=0; y<=7; y++){
          if (this.piece[x][y]!=null) { //there is a piece
            if (this.piece[x][y].isFire()==true){ //piece is fire
              fireCount = fireCount + 1; //add fire count
            } else { //piece is water
              waterCount = waterCount + 1; //add water count
            }
          }
        }
      }

      if (fireCount==0 && waterCount!=0){
        return "Water";
      } else if (fireCount!=0 && waterCount==0){
        return "Fire";
      } else if (fireCount==0 && waterCount==0){
        return "No one";
      }else{
        return null;
      }
    }

    private void drawBoard(int N) {
      for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            if ((i + j) % 2 == 0) StdDraw.setPenColor(StdDraw.GRAY);
            else                  StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledSquare(i + .5, j + .5, .5);
            StdDraw.setPenColor(StdDraw.WHITE);
            if (piece[i][j] != null) {
              StdDraw.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            }
          }
      }
    }

    public static void main(String[] args) {
      int N = 8;
      StdDraw.setXscale(0, N);
      StdDraw.setYscale(0, N);
      Board board = new Board(false);

    /** Monitors for mouse presses. Wherever the mouse is pressed,
    a new piece appears. */
      while(true) {
        board.drawBoard(N);
          if (StdDraw.mousePressed()) {
            int x = (int) StdDraw.mouseX();
            int y = (int) StdDraw.mouseY();
          if (board.canSelect(x, y)) {
            board.select(x, y);
          }
        }
        StdDraw.show(100);
      }
    }  
}
