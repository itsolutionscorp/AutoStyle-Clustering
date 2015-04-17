public class Board{
	/** main - starts GUI version of game
	  * constructor
	*/

	/** Location of pieces. */
    private Piece[][] pieces = new Piece[8][8];;
    private boolean playerIsFire = true;
    private Piece selectedPiece = null;
    private boolean moved = false;
    // private int firePiecesCaptured = 0;
    // private int waterPiecesCaptured = 0;

	/** Constructor
	  * if shouldBeEmpty is true, initialize an empty Board
	  * otherwise initialize Board with default configuration.
	*/
	public Board(boolean shouldBeEmpty){
		
		
		if(!shouldBeEmpty){
		int row = 0;
		int column = 0;
		boolean isFire = true;
		
		// fire pawns
		for(; column < 8; column = column + 2){
			pieces[column][row] = new Piece(isFire, this, column, row, "pawn");
		}
		row += 1;
		column = 1;
		// fire shields
		for(; column < 8; column = column + 2){
			pieces[column][row] = new Piece(isFire, this, column, row, "shield");
		}
		// fire bombs
		row += 1;
		column = 0;
		for(; column < 8; column = column + 2){
			pieces[column][row] = new Piece(isFire, this, column, row, "bomb");
		}
		row = 5;
		column = 1;
		isFire = false;
		// water bomb
		for(; column < 8; column = column + 2){
			pieces[column][row] = new Piece(isFire, this, column, row, "bomb");
		}
		row += 1;
		column = 0;
		// water shields
		for(; column < 8; column = column + 2){
			pieces[column][row] = new Piece(isFire, this, column, row, "shield");
		}
		// water pawn
		row += 1;
		column = 1;
		for(; column < 8; column = column + 2){
			pieces[column][row] = new Piece(isFire, this, column, row, "pawn");
		}  
		}      
	}
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(selectedPiece == pieces[i][j] && selectedPiece != null){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceAt(i, j) != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);
                }
            }
        }
    }
    private String getImage(Piece p){
    	String image = "";
		if(p.isBomb() == true){
			if(p.isFire()){
				if(p.isKing() == true){
					image = "img/bomb-fire-crowned.png";
				}else{
					image = "img/bomb-fire.png";
				}
			}
			else{
				if (p.isKing() == true){
					image = "img/bomb-water-crowned.png";
				}else{
					image = "img/bomb-water.png";
				}
			}
		}
		else if(p.isShield() == true){
			if(p.isFire()){
				if (p.isKing() == true){
					image = "img/shield-fire-crowned.png";
				}else{
					image = "img/shield-fire.png";
				}
			}
			else{
				if(p.isKing() == true){
					image = "img/shield-water-crowned.png";
				}else{
					image = "img/shield-water.png";
				}
			}
		}
		else{
			if(p.isFire()){
				if(p.isKing() == true){
					image = "img/pawn-fire-crowned.png";
				}else{
					image = "img/pawn-fire.png";
				}
			}
			else{
				if(p.isKing() == true){
					image = "img/pawn-water-crowned.png";
				}else{
					image = "img/pawn-water.png";
				}
			}
		
		}
		return image;
    }
	/** Gets the peice at position (x, y) on the board
	  * or null if there is no piece.
	  * If (x,y) is out of bounds, return null.
	*/
	public Piece pieceAt(int x, int y){
		return pieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		// check if out of Board
		if(xf < 0 && xf > 7 && yf < 0 && yf > 7){
			return false;
		}
		// check for same row or same colum, must always be different
		if(xi == xf || yi == yf){
			return false;
		}
		// check if lands on red square
		// Source: Yejia
		if((xf + yf) % 2 != 0){
			return false;
		}
		// check if Fire piece moves forward
		// found answer myself but got help debugging from Annalise
		if(pieceAt(xi,yi).isFire() == true && yf < yi){
			if(pieceAt(xi,yi).isKing() == false){
			return false;}
			return true;
		}
		// check if water piece moves down
		if(pieceAt(xi,yi).isFire() == false && yf > yi){
			if(pieceAt(xi,yi).isKing() == false){
				return false;
			}
			return true;
		}
		// check if there is a piece at xf, yf --> cannot move there
		if(pieceAt(xf, yf) != null){
			return false;
		}
		// check if jumping
		// if captured is true and not valid capture, then stop
		if(pieceAt(xi,yi).hasCaptured()){
			//can only do captures, not move
			if(Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2){
			return false;
		}
		if(Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2){
			return false;
		}
		// y diff and x diff is same
		// Source: Yejia
		if(Math.abs(yf - yi) != Math.abs(xf - xi)){
			return false;
		}
		//jump only if there is a piece that is not null
		if(Math.abs(xf - xi) == 2 && pieceAt((xf + xi) / 2 , (yf + yi) / 2) == null){
			return false;
		}
		//jump only if there is a piece that is not on our team
		if(Math.abs(xf - xi) == 2 && pieceAt((xf + xi) / 2 , (yf + yi) / 2).isFire() == playerIsFire){
			return false;
		}
		}
		// check for valid spots to jump
		//in order to jump/capture, must have selected piece
		if(Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2){
			return false;
		}
		// y diff and x diff is same
		// Source: Yejia
		if(Math.abs(yf - yi) != Math.abs(xf - xi)){
			return false;
		}
		//jump only if there is a piece that is not null
		if(Math.abs(xf - xi) == 2 && pieceAt((xf + xi) / 2 , (yf + yi) / 2) == null){
			return false;
		}
		//jump only if there is a piece that is not on our team
		if(Math.abs(xf - xi) == 2 && pieceAt((xf + xi) / 2 , (yf + yi) / 2).isFire() == playerIsFire){
			return false;
		}

		return true;
	}

	// gets (x,y) coordinates of p
	private int[] findPieceCoord(Piece p){
		int[] coord = new int[2];
		for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) { 
	            if(p == pieces[i][j]){
	            	coord[0] = i;
	            	coord[1] = j;
	            }            			
	        }
	    }
	    return coord;
	}
	/** Returns true if there is a piece at (x, y)
	  * and it can be selected.
	  *
	  * Can be selected if:
	  * playerIsFire has not selected a piece yet.
	  * playerIsFire selected a piece but did not move it.
	  * 
	  * An empty square can be slected if:
	  * During the turn, the playerIsFire has selected a 
	  * Piece which hasn't moved yet and is selecting
	  * an empty spot which is a valid move for the 
	  * previously selected Piece. 
	  * During this turn, the playerIsFire has selected a Piece,
	  * captured, and has selected another valid capture 
	  * destination. 
	*/
	public boolean canSelect(int x, int y){
		//there is a piece at x, y
		if(pieceAt(x,y) != null){
			// check if right playerIsFires turn and piece
			if(pieceAt(x,y).isFire() == playerIsFire){
			// if playerIsFire has not selected a piece yet
				if(selectedPiece == null){ 
					return true;
				}else{ // if playerIsFire selected a piece 
					// if playerIsFire has not moved selected piece
					if(moved == false){
						return true;
					}
				}
			}
			return false;
		}
		else{ // playerIsFire is selecting an empty square
			// playerIsFire has a selected Piece they will move
			if(selectedPiece != null){
				int[] coord = findPieceCoord(selectedPiece);
				//check if not moved
				if(moved == false){
					// check if x,y is a valid move
					if(validMove(coord[0], coord[1], x, y)){
						return true;
					}else{
						return false;
					}
				}
				else if(moved && selectedPiece.hasCaptured()){
					if(validMove(coord[0], coord[1], x, y)){
						return true;
					}else{
						return false;
					}
					// return true;
				}
			}
			
		}
		return false;
	}

	/** Selects the piece at (x,y) if possible
	  * (optional) Change the background color of the 
	  * selected square to white on the GUI via the 
	  * pen color funtion.
	  *
	  * For any piece to perform a capture, that piece must
	  * have been selected first.If you select a square with 
	  * a piece, you are prepping that piece for movement. 
	  * If you select an empty square (assuming canSelect returns 
	  * true), you should move your most recently selected piece 
	  * to that square.
	*/
	public void select(int x, int y){
		if(pieceAt(x,y) != null){
			selectedPiece = pieces[x][y];
		}
		else{
			selectedPiece.move(x,y);
			moved = true;
		}
	}
	/** Places p at (x, y).
	  * If (x, y) is out of bounds, or if p is null, do nothing.
	  * If another piece already exists at (x,y), p will replace that piece.
	*/
	public void place(Piece p, int x, int y){
		if(p != null && (x < 8 && y < 8)){
			// place p at new position
			pieces[x][y] = p;
		}
	}

	/** Executes a remove.
	  * Returns the piece that was removed.
	  * If input (x,y) is out of bounds, returns null and prints
	  * an appropriate message.
	*/
	public Piece remove(int x, int y){
		if((x >= 0 && x < 8) && (y >= 0 && y < 8)){
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
		System.out.println("Position out of bounds.");
		return null;
	}

	/** Returns whether or not the current playerIsFire can end their turn.
	  * To be able to end turn, piece must have moved or performed a
	  * capture.
	*/
	public boolean canEndTurn(){
		if(moved){
			return true;
		}
		return false;
	}

	/** Called at the end of each turn.
	  * Handles switching of playerIsFires and anything else that should 
	  * happen at end of a turn.
	*/
	public void endTurn(){
		selectedPiece = null;
    	moved = false;
		playerIsFire = !playerIsFire;
	}

	/** Returns winner of the game.
	  * "Fire", "Water", "No one"
	  * or null if the game is not yet over.
	*/
	public String winner(){
		String win = null;
		int firePiecesLeft = 0;
		int waterPiecesLeft = 0;
		for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) { 
	            if(pieceAt(i, j) != null){
	            	if(pieceAt(i,j).isFire()){
	            		firePiecesLeft += 1;
	            	}
	            	else{
	            		waterPiecesLeft += 1;
	            	}
	            }            			
	        }
	    }
	    if(firePiecesLeft == 0 && waterPiecesLeft == 0){
			win = "No one";
		}
	    else if(firePiecesLeft == 0 ){
	    	win = "Water";
	    }
	    else if(waterPiecesLeft == 0){
	    	win = "Fire";
	    }
	    else{
	    	win = win;
	    }
		return win;
	}
	// Starts a GUI supported version of the game
	public static void main(String[] args){
		int N = 8;
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        int xi;
        int yi;
        boolean gameNotOver = true;
    
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(gameNotOver) {
            b.drawBoard(N);
        
            StdDrawPlus.show(20);
            if (StdDrawPlus.mousePressed()) {
            	System.out.println(b.playerIsFire);
            	System.out.println(b.selectedPiece);
            	int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();

                if(b.canSelect(x, y)){
                	b.select(x,y);
                } 
            }
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }
            // Source: Annalise
            if(b.winner() != null){
            	gameNotOver = false;
            }
        }
	}
}