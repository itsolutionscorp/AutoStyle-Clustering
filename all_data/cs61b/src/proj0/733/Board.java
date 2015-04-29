public class Board {
  /** Location of pieces. */
  private Piece[][] pieces;
  private boolean turn = true; // fire goes first
  private Piece selected = null;
  private String winner = "No one";
  private boolean pieceWasMoved = false;
  private boolean pieceHasCaptured = false;
  private int selectX;
  private int selectY;


    public static void main(String[] args) {
    	int N = 8;
    	StdDraw.setXscale(0, N);
    	StdDraw.setYscale(0, N);
    	Board board = new Board(true);

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

  /**
  * Constructor for a Board.
  *
  * @param shouldBeEmpty  returns an empty board if true
  * @return               a Board
  */
	public Board(boolean shouldBeEmpty) {	
  		if (shouldBeEmpty == true){
  			this.pieces = new Piece[8][8];
  		}
      else {
        this.pieces = new Piece [8][8]; 
        for (int x = 0; x < 8; x+=2) pieces [0][x] = new Piece (true, this, x, 0, "pawn");
        for (int i = 1; i < 8; i+=2) pieces [1][i] = new Piece (true, this, i, 0, "shield");
        for (int j = 0; j < 8; j+=2) pieces [2][j] = new Piece (true, this, j, 0, "bomb");
        for (int k = 1; k < 8; k+=2) pieces [5][k] = new Piece (false, this, k, 0, "bomb");
        for (int l = 0; l < 8; l+=2) pieces [6][l] = new Piece (false, this, l, 0, "shield");
        for (int m = 1; m < 8; m+=2) pieces [7][m] = new Piece (false, this, m, 0, "pawn");
      }
  }

  /**
  * Returns the piece at (x, y) if there is a piece, or null otherwise.
  *
  * @param x, y  the coordinates to check
  * @return      the Piece at (x, y), or null
  */
	public Piece pieceAt(int x, int y) {
		// out of bounds
		if (outOfBounds(x, y)) {
			return null; 
		}	
		return this.pieces[x][y];
	}

	private boolean outOfBounds (int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return true;  
		} else {
			return false;
		}
	}



  /**
   * Returns true if there is a piece at (x, y) and it can be selected.  A piece
   * may be selected if it's the corresponding player's turn, and one of the
   * following is true:
   * - the player has not selected a piece yet
   * - the player has selected a piece, but did not move it. 
   *
   * an empty square may
   * then be selected if:
   * -- the player has selected a Piece which hasn't moved, and is selecting an
   * empty spot which is a valid move for the previously selected Piece
   * -- the player has selected a Piece, captured, and has selected another
   * valid capture destination
   *
   * @param x, y  the coordinates to check
   * @return      the Piece at (x, y), or null
   */

  // FINISH THIS ****
  public boolean canSelect(int x, int y) {
    Piece thePiece = pieceAt(x, y);    
    if (thePiece != null) { //there's a piece
      if (this.turn == thePiece.isFire()) { //if piece color = player color
        if(this.selected == null || !this.pieceWasMoved){
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else { //it's an empty square
      if(this.selected != null){
        if(!this.pieceWasMoved) { //selected a piece which hasn't moved
          //selecting an empty spot which is a valid move for the previously selected piece
          if(canMove(this.selectX, this.selectY, x, y)){
            return true;
          } else {
            return false;
          }
        } else { //piece has moved, but...
          if(this.pieceHasCaptured){ //the piece has also captured
            if(canCapture(selectX, selectY, x, y)){
              return true;
            } else {
              return false;
            }
          } else {
            return false;
          }
        }

      } else { //if there's been no previous selection
        return false;
      }
    }
  }


        

  /**
   * Returns whether the piece at (xi, yi) can perform a normal move to (xf,
   * yf).  This is false when:
   * - (xi, yi) or (xf, yf) are invalid locations
   * - there is no piece at (xi, yi)
   * - there is a piece at (xf, yf)
   * - the turn does not match the color of the piece at (xi, yi)
   * - the piece is not moving diagonally adjacent
   * - the piece is not a king, but it is trying to move backwards
   *
   * @param xi, yi    origin
   * @param xf, yf    destination
   * @return          if piece at origin can move to destination
   */
  private boolean canMove(int xi, int yi, int xf, int yf) {
  	if(outOfBounds(xi, yi) || outOfBounds(xf, yf)){
  		return false;
  	} else if(this.pieces[xi][yi] == null){
  		return false;
  	} else if(this.pieces[xf][yf] != null){
  		return false;
  	} else if(this.turn != this.pieces[xi][yi].isFire()){
  		return false;
  	} else if (!((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1))) { //if not moving diagonally adjacent (absolute values of x & y all should be 1)
  		return false;
  	} else if(!this.pieces[xi][yi].isKing()){ //not a king, but tryign to move backwards
  		if(this.turn){ //if isfire
  			if(yf < yi){
  				return false;
  			} 
  		} else { //if water
  			if(yf > yi){
  				return false;
  			}
  		}
  	}   		
  	return true;
  }

  /**
   * Returns whether the piece at (xi, yi) can perform a capture move to (xf,
   * yf).  This is false when:
   * - (xi, yi) or (xf, yf) are invalid locations
   * - there is no piece at (xi, yi)
   * - there is a piece at (xf, yf)
   * - the turn does not match the color of the piece at (xi, yi)
   * - the piece is not making a 2-step diagonal jump
   * - the piece is jumping over a piece of the same color
   * - the piece is not a king, but is trying to move backwards
   *
   * @param xi, yi    origin
   * @param xf, yf    destination
   * @return          if piece at origin can move to destination
   */
  private boolean canCapture(int xi, int yi, int xf, int yf) {
    
    Piece pieceJumpedOver = pieceAt(xf-xi, yf-yi);

  	if (outOfBounds(xi,yi) || outOfBounds(xf,yf)){
      return false;
    } else if(pieceAt(xi, yi) == null || pieceAt(xf,yf) != null){
      return false;
    } else if(pieceAt(xi, yi) != null && pieceAt(xi, yi).isFire() != this.turn){
      return false;
    } else if((Math.abs(xi-xf) + Math.abs(yi-yf)) != 4 ){ //if piece is not making a 2step diag jump
      return false;
    } else if(pieceJumpedOver != null && pieceJumpedOver.isFire() == pieceAt(xi, yi).isFire()){
      return false;
    } else if(!this.pieces[xi][yi].isKing()){ //not a king, but tryign to move backwards
      if(this.turn){ //if isfire
        if(yf < yi){
          return false;
        } 
      } else { //if water
        if(yf > yi){
          return false;
        }
      }
    } 
    return true;
    
  } 



  /**
   * Selects the piece at (x, y) if possible.  Should color the background of
   * the selected square
   *
   * @param x, y  the coordinates of the piece to select
   */
 public void select(int x, int y) {
    if (this.selected == null && canSelect(x, y)) { //if no previous selection |||||  && canSelect(x, y)
      this.selected = this.pieces[x][y];
      selectX = x;
      selectY = y;
    // } else { //if there's a previous selection... 
    //   if (pieces[x][y] == null) { // and if it's an empty square 
    //   	if (canCapture(selectX, selectY, x, y)){ //if you can capture a piece on the way
    //   		int a = selectX; 
    //       int b = selectY;
    //   		selectX = x;
    //   		selectY = y;
    //   		remove(a, b);
    //   		this.selected.move(x,y);
    //       this.pieceWasMoved = true;
    //       this.pieceHasCaptured = true;
    //     } else { //if you can't capture along the way
    //       // int a = selectX; 
    //       // int b = selectY;
    //       // selectX = x;
    //       // selectY = y;
    //       // remove(a, b);
    //       this.selected.move(x,y);
    //       this.pieceWasMoved = true;
    //     }
    //   } else { //if there's a previous selection but you're not selecting an empty square right now
    //     // this.selected = pieces[x][y];
    //     // selectX = x;
    //     // selectY = y;
    //   }
    }
  }

  /**
   * Places piece p at (x, y).  If (x, y) is out of bounds or p is null, does
   * nothing.  If p already exists in the current Board, first removes it from
   * its initial position.  If another piece already exists at (x, y), p will
   * replace that piece.
   *
   * @param p     the Piece to place
   * @param x, y  the coordinates to place p at
   */
  public void place(Piece p, int x, int y) {
  	if (!outOfBounds(x,y) && p != null){
  		remove(selectX,selectY);
  		this.pieces[x][y] = p;
  		selectX = x;
  		selectY = y;
  		this.selected = p; 
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
  	if (outOfBounds(x,y)){
  		System.out.println("error message");
  		return null;
  	}
  	else if (this.pieces[x][y] == null) {
  		return null;
  	}
  	else {
  		Piece temp = this.pieces[x][y];
  		this.pieces[x][y] = null;
  		return temp;
  	} 


  }

  /**
   * Returns whether or not the curent player can end their turn.  To do this, a
   * piece must have moved or performed a capture.
   *
   * @return if player can end turn
   */
  public boolean canEndTurn() {
  	if (this.pieceWasMoved || this.pieceHasCaptured){
  		return true;
  	}
  	else {
  		return false;
  	}
  }

  /**
   * Ends the turn.  Handles switching of player, and other cleanup tasks.
   */
  public void endTurn(){
  	if (canEndTurn()){
  		this.turn = !this.turn;
  		this.pieceWasMoved = false;
  		this.pieceHasCaptured = false; 
  		//DO YOU NEED TO ASK IF THERE'S A WINNER?!?!?!??! DONT FORGET
  	}
  	this.winner();
  }

  /**
   * Returns the winner of the game.
   *
   * @return "Fire", "Water", "No one" (if game is not over, or tie)
   */
  public String winner(){
  	int fires = 0;
  	int waters = 0;
  	for(int x = 0; x < 8; x++){
  		for(int y = 0; y < 8; y++){
  			if(this.pieces[x][y] != null){
  				if(this.pieces[x][y].isFire()){
  					fires++;
  				} else {
  					waters++;
  				}
  			} 
  		}
  	}
  	if(fires == 0 && waters == 0){
  		return "No one";
  	} else if(fires == 0){
  		return "Water";
  	} else if(waters == 0){
  		return "Fire";
  	} else {
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
        if (pieces[i][j] != null) {
          StdDraw.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        }
      }
    }
  }


}
