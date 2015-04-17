public class Board {
	//some of this will need to be set to private
  private static int SIZE = 8;
	private static Piece[][] pieces;
	private static boolean ISEMPTY = false; //set to false if you want a real board
	private boolean fireTurn;
	private boolean canEndTurn;
	private static int selected[]; //tells us the selected x-y coordinates

	private Piece selectedPiece; //lets us know the currently selected piece
	private boolean fullTurnLeft; //will let us know if the player can still do a full turn

  public static void main(String[] args) {
		//your code here
    StdDrawPlus.setXscale(0, SIZE);
		StdDrawPlus.setYscale(0, SIZE);
    Board ourBoard = new Board(ISEMPTY);
		while(true) {
      //keep drawing new state of the board
			ourBoard.drawBoard();
			StdDrawPlus.show(15);
		}
  }

  public Board(boolean shouldBeEmpty) { //initializes where all the pieces start
		//empty board does nothing
		this.pieces = new Piece[SIZE][SIZE];
		this.selected = new int[2]; //will list x and y of selected square;
		this.selected[0] = -10; //this will make sure 0, 0 isn't default selected
		this.selected[1] = -10;
		this.fireTurn = true;
		this.fullTurnLeft = true;
		//if it is default though...
		if (!shouldBeEmpty) {
		 for(int x = 0; x < SIZE; x += 1) {
			 for(int y = 0; y < SIZE; y += 1) {
         if ((x+y)%2 == 0) { //pieces are only put on gray
					 if (y == 0) { //fire pawns
						 pieces[x][y] = new Piece(true, this, x, y, "pawn");
					 }else if (y == 1) { //fire shields
						 pieces[x][y] = new Piece(true, this, x, y, "shield");
					 }else if (y == 2) { //fire bombs
						 pieces[x][y] = new Piece(true, this, x, y, "bomb");
					 }else if (y == 5) { //water bombs
						 pieces[x][y] = new Piece(false, this, x, y, "bomb");
					 }else if (y == 6) { //water shields
						 pieces[x][y] = new Piece(false, this, x, y, "shield");
					 }else if (y == 7) { //water pawns
						 pieces[x][y] = new Piece(false, this, x, y, "pawn");
					 }
				  }
			  }
	    }
		  this.canEndTurn = false;
		}
	}

  private void drawBoard() {
		for (int x = 0; x < SIZE; x += 1) {
			for (int y = 0; y < SIZE; y += 1) {

      //all the square stuff
				if (this.isSelected(x, y)) {//if the square is selected
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}else if ((x + y)%2 == 0) { // we want these squares to be gray
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
			  }
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);

			//all the select (mousePressed) stuff
			  if (StdDrawPlus.mousePressed()) {
					int slctX = (int) StdDrawPlus.mouseX();
					int slctY = (int) StdDrawPlus.mouseY();
					if (this.canSelect(slctX, slctY)) {
					  this.select(slctX, slctY);
					}
				}

			//all the endTurn (spacebar) stuff
			  if (StdDrawPlus.isSpacePressed()) {
          if (this.canEndTurn()) {
						this.endTurn();
					}
				}

			//all the piece stuff
				if (!(this.pieces[x][y] == null)) { //if a piece exists there
					Piece curPiece = pieces[x][y];

					if (curPiece.isBomb()) {
						if (curPiece.isFire()) { //fire
							if (curPiece.isKing()) { //king
                StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							}else{
                StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire.png", 1, 1);
							}
						}else{ //water
							if (curPiece.isKing()) {//king
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water.png", 1, 1);
							}
						}

					}else if (curPiece.isShield()) {
						if (curPiece.isFire()) {//fire
							if (curPiece.isKing()) { //king
                StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire-crowned.png", 1, 1);
							}else{
                StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", 1, 1);
							}
						}else{ //water
							if (curPiece.isKing()) {//king
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", 1, 1);
							}
						}

					}else{ //means it must be a pawn
						if (curPiece.isFire()) {//fire
							if (curPiece.isKing()) { //king
                StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}else{
                StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", 1, 1);
							}
						}else{ //water
							if (curPiece.isKing()) {//king
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}else{
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
    //your code here
		if (this.outOfBounds(x, y)) { //if out of bounds
      return null;
		}else if (this.pieces[x][y] == null) { //if there is no piece
			return null;
		}else{
			return this.pieces[x][y]; //should return null for null piece, but ah well!
																//aka previous "else if" may be unnecessary
		}
	}

  private boolean outOfBounds(int x, int y) {
		if ((x < 0)|(x > SIZE)|(y < 0)|(y > SIZE)) { //if out of bounds
			return true;
		}else{
			return false;
		}
	}

  public boolean canSelect(int x, int y) {
		//something to let pieces with hasCaptured capture again
		if ((x+y)%2==1) { //if it's a red square (can't select)
			return false;
		}else if (outOfBounds(x, y)) { //can't select out of bounds
		  return false;
		}
		else if (!(this.pieceAt(x, y) == null)) { // if this space has a piece
			if (!(this.fullTurnLeft)) {
				return false;
			}else if ((this.fireTurn)&&(!(this.pieceAt(x, y).isFire()))) {//can't select enemy
        return false;
			}else if ((!(this.fireTurn))&&(this.pieceAt(x, y).isFire())) { //other side's again
				return false;
			}else if ((!(this.selectedPiece == null))|  //if they haven't selected a piece yet
		       (this.fullTurnLeft)) { //or if they have selected a piece but not moved
				return true;
		  }else{ //can't select now
				return false;
			}
	  }
		else if ((this.pieceAt(x, y) == null) //if this space is empty
				&&(!(this.selectedPiece == null))) { //and a piece has been selected
      int xi = selected[0];
			int yi = selected[1]; //gives us the x-y coordinates of selected piece
			if (this.fullTurnLeft) { //if the player can still move
				if (this.validMove(xi, yi, x, y)) {
					return true;
				}else{
					return false;
				}
			}else if (!(this.fullTurnLeft)) { //if the player moved
        if (this.selectedPiece.hasCaptured()) { //and the piece captured
				  if (this.validMove(xi, yi, x, y)) { //this should check if the full turn is left
					  return true;
				  }else{
						return false;
					}
				}else{
					return false;
				}
			}
		}
    return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((this.outOfBounds(xi, yi))|(this.outOfBounds(xf, yf))) { //make sure nothing is out of bounds
			return false;
		}else if (this.pieceAt(xi, yi)==null){ //if there's no piece at xi-yi
      return false;
		}else if (!(this.pieceAt(xf, yf)==null)) { //if there's already a piece at xf-yf
			return false;
	  }else if ((Math.abs(xi-xf) == 1)&&(Math.abs(yi-yf) == 1)) { //means it's trying to move
			if (!(this.fullTurnLeft)) { //if it doesn't have a full turn left
				return false;
			}else if (pieces[xi][yi].isKing()) { //if it's king, it can
				return true;
			}else if (pieces[xi][yi].isFire()) { //if it's fire
        if (yi > yf) { //if it's moving down and not a king
					return false;
				}else{
					return true;
				}
			}else{ //if it's water
        if (yi < yf) { //if it's moving up and not a king
					return false;
				}else{
					return true;
				}
			}
		}else if ((Math.abs(xi-xf) == 2)&&(Math.abs(yi-yf) == 2)) { //means it's trying to capture
      int midX = (xi+xf)/2;
			int midY = (yi+yf)/2;
			if (pieces[midX][midY] == null) { //if there's not a piece between
				return false;
			}else if (pieces[xi][yi].isFire() == pieces[midX][midY].isFire()){ 
				//if you're trying to capture a friendly piece
        return false;
			}else if (pieces[xi][yi].isKing()){ // if it's a king
				return true;
			}else if (pieces[xi][yi].isFire()){ // if it's fire
        if (yi > yf) { //if it's moving down and not a king
					return false;
				}else{
					return true;
				}
			}else{ //if it's water
        if (yi < yf) { //if it's moving up and not a king
					return false;
				}else{
					return true;
				}
			}
		}else{ //maybe xi and yi are not in a line
	    return false;
		}
	}


  public void select(int x, int y) {
		//determine whether we've selected a piece or not
    if (!(this.pieceAt(x, y) == null)) { //if there is a piece there
      this.selectedPiece = this.pieceAt(x, y); //make this our selectedPiece
	  }else{ //if there is no piece here
	  // check that we can move here
			selectedPiece.move(x, y); //then moves us there
			this.fullTurnLeft = false;
			this.canEndTurn = true;
		}
	  selected[0] = x;
	  selected[1] = y;
	}

//	private int pieceX(Piece p) {
//		 for (int i = 0; i < SIZE; i += 1) {
//			 for (int j = 0; j < SIZE; j += 1) {
//				 if (this.pieceAt(i, j) == p) {
//           return i;
//				 }
//			 }
//		 }
//		 System.out.println("ERROR: pieceX - piece not found");
//		 return -1;
//  }
//
//  private int pieceY(Piece p) {
//		 for (int i = 0; i < SIZE; i += 1) {
//			 for (int j = 0; j < SIZE; j += 1) {
//				 if (this.pieceAt(i, j) == p) {
//           return j;
//				 }
//			 }
//		 }
//		 System.out.println("ERROR: pieceX - piece not found");
//		 return -1;
//  }

	private boolean isSelected(int x, int y) {
    if ((selected[0] == x)&&(selected[1] == y)) {
			return true;
		}else{
		return false;
		}
	}

  public void place(Piece p, int x, int y) {
		//your code here
		if (!((this.outOfBounds(x, y))|(p == null))) { //not out of bounds or null piece
			for (int i = 0; i < SIZE; i += 1) {
				for (int j = 0; j < SIZE; j += 1) {
          if (pieces[i][j] == p) {
						pieces[i][j] = null;
					}
				}
			}
      pieces[x][y] = p;
		}
	}

  public Piece remove (int x, int y) {
		//your code here
		if (this.outOfBounds(x, y)) {
			System.out.println("ERROR: remove(x, y) - x, y out of bounds");
			System.out.format("x was: %d and y was: %d\n", x, y);
		  return null;
		}else if (pieces[x][y] == null) {
			System.out.println("ERROR: remove(x, y) - no piece at x, y");
			System.out.format("x was: %d and y was: %d\n", x, y);
      return null;
		}else{
		  Piece returnPiece = pieces[x][y];
		  pieces[x][y] = null;
			return returnPiece;
		}
	}

  public boolean canEndTurn() {
		//your code here
		if (this.canEndTurn) {
			return true;
		}
		return false;
	}

  public void endTurn() {
	  if (this.fireTurn) { //if it's fire's turn
			this.fireTurn = false; //it's now water's turn
		}else{
			this.fireTurn = true; //else it's fire's turn
	  }
		if (!(this.selectedPiece == null)) {
	    this.selectedPiece.doneCapturing();
		  this.selectedPiece = null;
		}
		this.selected[0] = -10;
		this.selected[1] = -10; //resets the coordinates of the selected piece
		this.fullTurnLeft = true;
		this.canEndTurn = false;
	}

  public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < SIZE; i += 1) {
			for (int j = 0; j < SIZE; j += 1) {
				if (!(pieces[i][j] == null)) { //if a piece exists there
          if (pieces[i][j].isFire()){
						fireCount += 1;
					}else{
						waterCount += 1;
					}
				}
			}
		}
		if ((!(waterCount == 0))&&(!(fireCount == 0))) { // if neither is zero
			return null; //no one has won yet
		}else if ((waterCount == 0)&&(fireCount == 0)) {
			return "No one"; //there was a tie
		}else if (waterCount == 0) {
			return "Fire";
		}else{
			return "Water";
		}
	}

}
