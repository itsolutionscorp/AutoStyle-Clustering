/**
 * The battlefield.
 * @author Steven
 *
 */
public class Board {
    private int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private boolean isFireTurn = true;
    private int moveState; //0:not moved; 1: regular moved; 2: jump moved
    private boolean pieceSelected = false;
    private Piece selectedPiece;
    private int selectedX, selectedY;


    /**
	 * If shouldBeEmpty is true, initializes an empty Board. 
	 * Otherwise, initializes a Board with the default configuration. 
	 * @param shouldBeEmpty
	 */ 
	public Board(boolean shouldBeEmpty) {
		//init(shouldBeEmpty);

		isFireTurn = true;
		moveState = 0;
		pieceSelected = false;
		selectedPiece = null; 
		
		if (shouldBeEmpty) {
			//DEFAULT ALL POSITIONS TO NULL
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					pieces[i][j] = null;
				}
			}
			//pieces[3][5] = new Piece(true, this, 3, 5, "pawn");
			//pieces[2][6] = new Piece(false, this, 2, 6, "pawn");
			
			/*pieces[3][3] = new Piece(true, this, 3, 3, "pawn");
			pieces[4][4] = new Piece(false, this, 4, 4, "pawn");
			pieces[6][6] = new Piece(false, this, 6, 6, "pawn");
			
			pieces[2][2] = new Piece(false, this, 2, 2, "pawn");
			pieces[0][0] = new Piece(false, this, 0, 0, "pawn");
			
			pieces[2][4] = new Piece(false, this, 2, 4, "pawn");
			pieces[2][6] = new Piece(false, this, 2, 6, "pawn");
			
			pieces[4][2] = new Piece(false, this, 4, 2, "pawn");
			pieces[6][4] = new Piece(false, this, 6, 4, "pawn"); */
			

		}
		else {
			for (int x = 0; x < N; x += 2) {
				//INITIALIZING FIRE PIECES 
				pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				pieces[x+1][1] = new Piece(true, this, x+1, 1, "shield");
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
				
				//INITIALIZING WATER PIECES
				pieces[x + 1][N-3] = new Piece(false, this, x+1, N-3, "bomb");
				pieces[x][N-2] = new Piece(false, this, x, N-2, "shield");
				pieces[x + 1][N-1] = new Piece(false, this, x+1, N-1, "pawn");
			}	
		}
		
	}

	/**
	 * Gets the piece at position (x, y) on the board, or null if there is no piece. 
	 * If (x, y) are out of bounds, returns null.
	 * @param x xPos
	 * @param y yPos
	 * @return Piece at x,y
	 */
	public Piece pieceAt(int x, int y) {
		if (x > (this.N - 1) || y > (this.N - 1) || x < 0 || y <0) {
			return null;
		}
		return pieces[x][y];
	}
	
	/**
	 * Returns true if there is a piece the piece at (x, y), 
	 * and it can be selected.
	 * @param x xPos
	 * @param y yPos
	 * @return if there's a piece at (x, y)
	 */
	public boolean canSelect(int x, int y) { //more to add
		if (x < 0 || y < 0 || x > N-1 || y > N-1)
			return false;
		
		if (pieceAt(x,y) != null) { //square with piece
			if (isFireTurn == pieceAt(x,y).isFire()) {
				if (!pieceSelected)
					return true;
				
				if (moveState == 0)
					return true;
			}
		}
	
		else { //empty square
			if (pieceSelected) {
				if (moveState == 0) {
					if (validMove(selectedX, selectedY, x, y))
						return true;
				}
				else if (moveState == 2) { //jump moved
					if (validMove(selectedX, selectedY, x, y)) {
						return true;
					}
				}

			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf) 
	 * in a valid fashion compatible with the rules.
	 * @param xi xPos
	 * @param yi yPos
	 * @param xf future xPos
	 * @param yf future yPos
	 * @return valid move
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		
		if (pieceAt(xi, yi) == null) 
			return false;
		
		if (xf == xi && yf == yi) 
			return false;
		
		//if (pieces[xi][yi] == null)  //initial position null: false
			//return false;
		
		//if (isFireTurn ^ pieces[xi][yi].isFire()) //Fireturn + Fire piece OR WaterTurn + Water piece: continue
			//return false;
		
		if (pieces[xf][yf] != null)  //final location taken: false
			return false;

		//IMMEDIATE DIAGONAL MOVEMENT
		if (Math.abs(xf - xi) == 1) {
			if (moveState != 0)
				return false;
			
			if (isFireTurn) {
				if (pieces[xi][yi].isKing()) {
					if (Math.abs(yf - yi) != 1)
						return false;
					else
						return true;
				}
				else {
					if ((yf - yi) != 1)
						return false;
					else
						return true;
				}
			}
			else { //WaterTurn
				if (pieces[xi][yi].isKing()) {
					if (Math.abs(yf - yi) != 1)
						return false;
					else
						return true;
				}
				else {
					if ((yf - yi) != -1)
						return false;
					else
						return true;
				}
			}
		}
		
		// examine jump capture movement 
		if (Math.abs(xf - xi) != 2) {
			return false;
		}

		if (isFireTurn) {
			if (pieces[xi][yi].isKing()) {
				if (Math.abs(yf - yi) != 2)
					return false;
			} 
			else {
				if ((yf - yi) != 2) 
					return false;
			}
		} 
		else { // WaterTurn
			if (pieces[xi][yi].isKing()) {
				if (Math.abs(yf - yi) != 2)
					return false;
			}
			else {
				if ((yf - yi) != -2)
					return false;
			}
		}

		int xm; //coordinates of piece to be captured
		int ym;
		if (xf > xi)
			xm = xi + 1;
		else
			xm = xi - 1;
		
		if (yf > yi)
			ym = yi + 1;
		else
			ym = yi - 1;
		
		if (pieces[xm][ym] == null) { //check if capture position has a piece
			return false;
		}
		
		if (pieces[xi][yi].isFire() == pieces[xm][ym].isFire()) { //If init piece and to-capture piece are same type: false
			return false;
		}
		
		return true;
	}
	
	/**
	 * Selects the piece at (x, y) if possible.
	 * @param x
	 * @param y
	 */
	public void select(int x, int y) {
		if (pieceSelected) {
			if (pieceAt(x, y) == null) {
				selectedPiece.move(x, y);

				if (selectedPiece.hasCaptured())
					moveState = 2;
				else
					moveState = 1;
			} 
			else
				selectedPiece = pieceAt(x, y);
		} 
		else {
			pieceSelected = true;
			selectedPiece = pieceAt(x, y);
		}

		selectedX = x;
		selectedY = y;
		
	}
	
	/**
	 * Places p at (x, y). 
	 * If (x, y) is out of bounds or if p is null, does nothing. If p already exists in the current Board, first removes it from its initial position. 
	 * If another piece already exists at (x, y), p will replace that piece.
	 * @param p
	 * @param x
	 * @param y
	 */
	public void place(Piece p, int x, int y) {
		  //x or y out of bounds 
		  if (x > (this.N-1) || y > (this.N-1) || x < 0 || y < 0) {
		   return;
		  }

		  //checking if there's a piece on that position
		  if (pieceAt(x, y) != null) {
		   remove(x, y);
		   pieces[x][y] = p;
		   return;
		  }
		  
		  for (int i = 0; i < N; i++) {
		   for (int j = 0; j < N; j++) {
		    if (pieces[i][j] == p) {
		     pieces[i][j] = null;
		    }
		   }
		  }
		  pieces[x][y] = p;
		  
		  return;
	}

	/**
	 * Executes a remove. Returns the piece that was removed. 
	 * If the input (x, y) is out of bounds, returns null and prints 
	 * an appropriate message. If there is no piece at (x, y), returns null 
	 * and prints an appropriate message.
	 * @param x
	 * @param y
	 * @return removed Piece
	 */
	public Piece remove(int x, int y) {
		if ( x > (this.N - 1) || y > (this.N - 1) || x < 0 || y < 0 ) {
			System.out.println( "(" + x + ", " + y + ") out of bounds.");
			return null;
		}
		
		if (pieces[x][y] == null) {
			System.out.println("No Piece at (" + x + ", " + y + ")");
			return null;
		}
		else {
			Piece p = pieceAt(x, y);
			pieces[x][y] = null;
			return p;
		}
	}
	
	/**
	 * Returns whether or not the the current player can end their turn. 
	 * To be able to end a turn, a piece must have moved or performed a capture.
	 * @return
	 */
	public boolean canEndTurn() {
		return moveState != 0;
	}
	
	/**
	 * Called at the end of each turn. 
	 * Handles switching of players and anything else that should happen at the end of a turn.
	 */
	public void endTurn() { 
		isFireTurn = !isFireTurn; //change turns
		moveState = 0;
		pieceSelected = false;
		selectedX = 0;
		selectedY = 0;
		selectedPiece.doneCapturing();
		selectedPiece = null;
	}

	
	/**
	 * Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null 
	 * if the game is not yet over. Assume there is no stalemate situation
	 * in which the current player has pieces but cannot legally move any of them 
	 * (In this event, just leave it at null). 
	 * Determine the winner solely by the number of pieces belonging to each team.
	 * @return winner
	 */
	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (pieces[x][y] != null) {
					if (pieces[x][y].isFire())
						fireCount +=1;
					else
						waterCount += 1;
				}
			}
		}
		
		if (fireCount == 0 || waterCount == 0) {
			if (fireCount > waterCount){
				//clearBoard();
				return "Fire";
			}
			else if (waterCount > fireCount) {
				//clearBoard();
				return "Water";
			}
			else
				return "No one";
		}
		return null; //not ended
	}
	
	
	private void clearBoard() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				pieces[x][y] = null;
				
			}
		}
	}
	
    /** Draws an N x N board. Adapted from:
    http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
    */
	private void drawBoard(int N) {
		//draw Background
	    for (int y = 0; y < N; y++) {
	        for (int x = 0; x < N; x++) {
	            if ((y + x) % 2 == 0)
	            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            else                  
	            	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	        }
	    }

	    if (pieceSelected) {
	    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5); 
	    }
	    
	    //draw pieces
	    for (int y = 0; y < N; y++) {
	        for (int x = 0; x < N; x++) {
	            if (pieces[x][y] != null) {
	            	StdDrawPlus.picture(x + .5, y + .5, getImage(pieces[x][y]), 1, 1);
	            }     
	        }
	    }
	}
	
	private String getImage(Piece p)
	{
		String strSide;
		String strType;

		if (p.isFire()) {
			if (p.isKing())
				strSide = "-fire-crowned";
			else
				strSide = "-fire";	
		}
		else {
			if (p.isKing())
				strSide = "-water-crowned";
			else
				strSide = "-water";			
		}
		
		if (p.isBomb())
			strType = "bomb";
		else if (p.isShield())
			strType = "shield";
		else
			strType = "pawn";
			
		return ("img/" + strType + strSide + ".png");
	}
		
	public static void main(String args[]) {
		int N = 8;
	    StdDrawPlus.setXscale(0, N);
	    StdDrawPlus.setYscale(0, N);
	    Board board = new Board(false);  //CHANGE THIS LINE'S BOOLEAN FOR A BLANK OR FULL BOARD 
	    boolean gameIsOver = false; 

	    while(true) {

		    while(!gameIsOver) {
		        board.drawBoard(N);
	            if (StdDrawPlus.mousePressed()) {
	                int x = (int) StdDrawPlus.mouseX();
	                int y = (int) StdDrawPlus.mouseY();
	                board.select(x,y);
//	                board.place(board.selectedPiece, x, y);
	            } 
	            
	            if (StdDrawPlus.isSpacePressed()) {
	            	if(board.canEndTurn()) {
	            		board.endTurn();
	            	}	
	            }
	           
		        StdDrawPlus.show(100);
		        
			    String s = board.winner();
			    if (s != null) {
			    	System.out.println(s + " wins!");
			    	gameIsOver = true;
			    	board.drawBoard(N);
			    }
		    }
		    
		    if (StdDrawPlus.isNPressed()) {
		    	gameIsOver = false;
		    	board = new Board(false);
		    }
		    StdDrawPlus.show(100);
	    }
	    
	}
	
}
