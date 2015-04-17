public class Board {

	private Piece[][] pieces;
	private int who = 0;
	private Piece selected = null;
	private boolean hasMoved = false;
	private int xcoord; //the x coord of the selected piece
	private int ycoord;		

	public static void main(String[] args){ 
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);	  
		Board xx = new Board(false);
		xx.drawBoard();
		while (true){
			xx.drawBoard();
			if (StdDrawPlus.mousePressed()){
				double xl = StdDrawPlus.mouseX();
				double yl = StdDrawPlus.mouseY();
				int xlocation = (int) (xl);
				int ylocation = (int) (yl);
				if (xx.canSelect(xlocation, ylocation)){
					xx.select(xlocation, ylocation);
				}
			} else if (StdDrawPlus.isSpacePressed()){
				if (xx.canEndTurn()){
					xx.endTurn();
				}
			} 
			
			StdDrawPlus.show(10);
		} 
	}
	
	public Board(boolean shouldBeEmpty){
	    pieces = new Piece[8][8]; //creates an empty board
		if (shouldBeEmpty == false){ //add's pieces to the empty board
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (((j == 0) && (i % 2 == 0)) || ((j == 7) && (i % 2 != 0))){
					if (j < 3){
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					} else {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
				}	            		            	
			} else if (((j == 1) && (i % 2 != 0)) || ((j == 6) && (i % 2 == 0))){
				if (j < 3){
					pieces[i][j] = new Piece(true, this, i, j, "shield");	            		
				} else {
					pieces[i][j] = new Piece(false, this, i, j, "shield");
			}	            	
		} else if (((j == 2) && (i % 2 == 0)) || ((j == 5) && (i % 2 != 0))){
			if (j < 3){
				pieces[i][j] = new Piece(true, this, i, j, "bomb");	                	
			} else {
				pieces[i][j] = new Piece(false, this, i, j, "bomb");	            			
		} 
	} else {
		pieces[i][j] = null;
	}                
}}}}

private void drawBoard(){
	for (int i = 0; i < 8; i += 1) {
		for (int j = 0; j < 8; j += 1) {
			if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
		}}

		if (this.canSelect(xcoord, ycoord)){	
				StdDrawPlus.filledSquare(this.xcoord + .5, this.ycoord + .5, .5);
		}

		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j]!= null) {
					if (pieces[i][j].isFire() == true){
						if ((pieces[i][j].isBomb() != true) && (pieces[i][j].isShield() != true)){
							if (pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}	       
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);	               	            
						}
						else if (pieces[i][j].isBomb()){
							if (pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);	                    	
						}
						else if (pieces[i][j].isShield()){
							if (pieces[i][j].isKing()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);	                    	
						}}
						if (pieces[i][j].isFire() != true){
							if ((pieces[i][j].isBomb() != true) && (pieces[i][j].isShield() != true)){
								if (pieces[i][j].isKing()){
									StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
								}
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);	                    	
							}
							else if (pieces[i][j].isBomb()){
								if (pieces[i][j].isKing()){
									StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
								}
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);	                    	
							}
							else if (pieces[i][j].isShield()){
								if (pieces[i][j].isKing()){
									StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
								}
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);	                    	
							}
						} 
					}
				}} 
			}

	//gets the piece at position (x,y) on the board
	public Piece pieceAt(int x, int y){
		if (((x > 7) || (y > 7)) || ((x<0 || y<0))){ //if (x,y) is out of bounds
			return null;
		} else {	
			return pieces[x][y];
		}}

	//true if there is a piece that can be selected at (x,y)
	public boolean canSelect(int x, int y){
		if (((y % 2 == 0) && (x % 2 != 0)) || ((y % 2 != 0) && (x % 2 == 0))) { //the red squares
			return false;
		}

		//a piece can be selected if:
		if (pieces[x][y]!= null){

			if (this.who == pieces[x][y].side()) { //its the corresponding players turn
				if ((this.selected == null) || ((this.selected != null) && (this.hasMoved == false))){ //the player has not yet selected a piece or the player selected a piece, but it did not move
					return true;
			}
		}
	}
		// an empty square can be selected if:	 
	if (((this.selected != null) && (this.hasMoved == false)) && (this.pieceAt(x, y) == null)){ /*the player has selected a Piece which hasn’t moved yet and is selecting an empty spot*/	
		if (this.validMove(this.xcoord, this.ycoord, x, y)){  /*checking if the empty spot selected is a valid move for the piece in this.selected*/
			return true; 
		}
	}

		if (((this.selected != null) && (this.selected.hasCaptured() == true)) && (this.pieceAt(x, y) == null)){ //the player has selected a Piece, captured, and has selected an empty square.
				if ((this.validMove(this.xcoord, this.ycoord, x, y)) && (Math.abs(this.xcoord - x) == 2)) { //making sure the empty square selected is a valid spot in which it is capturing to get there
						if ((this.pieceAt((x + this.xcoord) / 2, (y + this.ycoord) / 2) != null) && (this.pieceAt((x + this.xcoord) / 2, (y + this.ycoord) / 2).side() == (1 - this.who))) {
							return true;
						}
				} else { 
					return false; 
				}
			}
			return false;
		}


	public void select(int x, int y){ 
		if ((pieces[x][y] == null) && (this.selected != null)){ //if it's selecting an empty square
			selected.move(x, y); //moves a piece to this empty square
			pieces[this.xcoord][this.ycoord] = null; //makes the piece's old spot empty
			this.hasMoved = true;
			
		} else /*if it's selecting a piece */ { //gets all the info for the selected piece
			this.selected = this.pieceAt(x, y);
			this.xcoord = x;
			this.ycoord = y; 
		} 
	}


	//returns whether X is in the 2D Array W
	private Boolean inpieces(Piece[][] w, Piece x){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (w[i][j] == x){
					return true;
				}}}
				return false;
			}

	//Places p at (x, y).
	public void place(Piece p, int x, int y){
		if ((p == null) || (((x > 7) || (y > 7)) || ((x<0 || y<0)))){ //If (x, y) is out of bounds or if p is null, does nothing.
			return;
		} else {
			pieces[x][y] = p;	//If another piece already exists at (x, y), p will replace that piece. 
		} 
	}

	//removes a piece and returns the piece that was removed
	public Piece remove(int x, int y){
		//If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
		if (((x > 7) || (y > 7)) || ((x < 0 || y < 0))){
			System.out.println("(x, y) is out of bounds");
			return null;
		}
		//If there is no piece at (x, y), returns null and prints an appropriate message.	
		else if (this.pieceAt(x, y) == null){
			System.out.println("There is no piece to remove from (x, y)");
			return null;
		} else { //remove the piece and return it
			Piece removed = this.pieceAt(x, y);
			pieces[x][y] = null;
			return removed;
		}
	}

	//Returns whether or not the the current player can end their turn.
	public boolean canEndTurn(){ 
		if ((this.selected != null) && (this.selected.hasCaptured())){ //a piece has performed a capture
			return true;
		} else if (hasMoved == true){ //a piece has moved
			return true;
		}
		return false;
	}

	/*Called at the end of each turn. 
	Handles switching of players and anything else that should happen at the end of a turn.*/
	public void endTurn(){ 
		this.who = 1 - this.who;
		this.hasMoved = false;
		this.selected.doneCapturing();
		this.selected = null;
	}

	//Returns the winner of the game based on how many pieces belong to each team/whoever has the most pieces left on the board?
	public String winner(){
	//"Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over.
		int watercount = 0;
		int firecount = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null){
					if (pieces[i][j].side() == 0){
						firecount += 1;
					} else {
						watercount += 1; 
					}
				}
			}
		}
		if ((firecount == 0) && (watercount == 0)){
			return "No one";
		} else if (firecount == 0){
			return "Water";
		} else if (watercount == 0){
			return "Fire";
		} else {
			return null;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (this.pieceAt(xf, yf) != null){
			return false; //not a valid move, cant move to a place that is already holding a piece
		} 

		if ((Math.abs(xf - xi) > 2) || (Math.abs(xf - xi) > 2)) {
				return false; //not a valid move, cant move more than 2 spaces
			} 

			if (pieces[xi][yi] != null){
		if ((pieces[xi][yi].side() == 0) && (pieces[xi][yi].isKing() == false)) { //fire piece
			if ((xf == xi) || (yf <= yi)){
				return false; //not a valid move, can only move up
			} else {
				return true; }
			} 

		if ((pieces[xi][yi].side() == 1) && (pieces[xi][yi].isKing() == false)){  //water piece
			if ((xi == xf) || (yi <= yf)){ 
				return false; //not a valid move can only move down
			} 
			else {
				return true; 
			}
		}
		if ((pieces[xi][yi].isBomb() == true) && (Math.abs(xf - xi)) == 2){ //a bomb piece that is capturing
			if ((this.pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (this.pieceAt((xf + xi) / 2, (yf + yi) / 2).side() == (1 - this.who))) {
				return true;
			} else {
				return false;
			}
		}
	}
	if ((Math.abs(xf - xi)) == 2){ //its hopping over something
		if ((this.pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (this.pieceAt((xf + xi) / 2, (yf + yi) / 2).side() == (1 - this.who))) {
			return true;
		} else {
			return false;
		}

	} else {
		return true;
	}

}
}











