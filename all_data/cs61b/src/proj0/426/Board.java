
public class Board {
	//*constructor for Board
	private boolean isEmpty;
	private int N = 8;
	private static Piece[][] board;
	private boolean[][] pieces;
	private Piece pieceSelected = null;
	private String currentPlayer = "fire";
	private Piece pieceCaptured= null;
	private boolean moved = false;
	private int selectX;
	private int selectY;
	
	public Board(boolean shouldBeEmpty){
		isEmpty = shouldBeEmpty;
		board = new Piece[N][N];
		if (isEmpty == false) {
			//creating fire pieces
			for (int i = 0; i < board[0].length; i += 2) {
				place(new Piece(true, this, i, 0, "pawn"), i, 0);

			}
			for (int i = 1; i < board[1].length; i += 2) {
				place(new Piece(true, this, i, 1, "shield"), i, 1);
			}
			for (int i = 0; i < board[2].length; i += 2) {
				place(new Piece(true, this, i, 2, "bomb"), i, 2);
			}
			//creating water pieces
			for (int i = 1; i < board[5].length; i += 2) {
				place(new Piece(false, this, i, 5, "bomb"), i, 5);
			}
			for (int i = 0; i < board[6].length; i += 2) {
				place(new Piece(false, this, i, 6, "shield"), i, 6);
			}
			for (int i = 1; i < board[7].length; i += 2) {
				place(new Piece(false, this, i, 7, "pawn"), i, 7);
			}
		}

	}
	private static void drawBoard(int N){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (board[i][j] != null) {
                	if (board[i][j].isFire()) {
                		if (board[i][j].isBomb()){
                			if (board[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		else if (board[i][j].isShield()) {
                			if (board[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);

                		}
                		else if (board[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);

                			}
                		else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);

                		}
                	}
                	else {
                		if (board[i][j].isBomb()){
                			if (board[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);

                			}
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);

                			}
                		else if (board[i][j].isShield()) {
                			if (board[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);

                			}
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);

                		}
                		else if (board[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);

                			}
                		else {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}
                		}
                	}
                  
                }
            }
        }
	private boolean samePlayer(Piece p) {
		if (p == null) {
			return false;
		}
		if (currentPlayer == "fire" && p.isFire() || currentPlayer == "water" && !p.isFire()) {
			return true;
		}
		return false;
	}

	//*Gets the piece at position (x, y) on the board, or null if there is no piece. 
	/*If (x, y) are out of bounds, returns null.*/
	//-------------------------------DONE----------------------------------------
	public Piece pieceAt(int x, int y) {
		if ((x > 7 || x < 0 ) || (y > 7 || y < 0 )) {
			return null;
		}
		else {
			return board[x][y];
		}
		
	}
	//*Returns true if there is a piece the piece at (x, y) and it can be selected./
	public boolean canSelect(int x, int y){
		Piece piece = pieceAt(x, y);
		if (samePlayer(piece)) {
			if (pieceSelected == null ||  (pieceSelected != null && !moved)) {
				return true;
			}
		}
		if ((piece == null) & validMove(selectX, selectY, x, y)) { //empty spot to move piece to 
			if (pieceSelected != null && !moved) {
				return true;
			}
			else if (pieceSelected!= null && pieceSelected.hasCaptured() &&  canCapture(selectX, selectY, x, y)) {
				return true;
			}
		}
		
		return false;
//Either put capture method in valid move or can Capture or can Select. CHECK FOR OVERLAPPING CODE
	}
	//Returns true if possible capture
	private boolean canCapture(int xi, int yi, int xf, int yf) {
		int captureX = 0;
		int captureY = 0;
			if (Math.abs(xf - xi) == 2 && Math.abs(yf-yi) == 2) {
				if ( xf > xi) {
					captureX = xi+ 1;
				}
				else {
					captureX = xi -1;
				}
				if (yf > yi) {
					captureY = yi + 1;
				}
				else {
					captureY = yi -1;
				}
				
			if ((pieceAt(captureX, captureY) != null) && !samePlayer(pieceAt(captureX, captureY))) {
				return true;
			} 
		}
		return false;
	}

	//*Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	/*or capture to (xf, yf) in a valid fashion compatible with the rules.*/
	private boolean validMove(int xi, int yi, int xf, int yf){
		if (pieceSelected!= null) {
			if (pieceSelected.isKing()) {
				if (((xf - xi) == (yf - yi)) || ((xf - xi) == -(yf - yi))) { //King can move up and down
					if (Math.abs(xf - xi) == 1 || canCapture(xi, yi, xf, yf)) {
						return true;
					}
					
				}
			}
			
			else if (pieceSelected.isFire()){
				if (Math.abs(xf - xi) == Math.abs(yf - yi) && yf > yi) {
					if (Math.abs(xf-xi) == 1 || canCapture(xi, yi, xf, yf)) { //Fire can move up board
						return true;
					}
				}
			}
			else if (!pieceSelected.isFire()) {
				if (Math.abs(xf - xi) == Math.abs(yf - yi) && yf < yi) {							//Water moves down
					if (Math.abs(xf-xi) == 1 || canCapture(xi, yi, xf, yf)) { 
						return true;
					}
				}
			}
	}
		return false;
	}
	//*Selects the piece at (x, y) if possible
	//-------------------------------DONE----------------------------------------
	public void select(int x, int y) {
		// if (!samePlayer(pieceAt(x,y))) { //FIX , DONT NEED TO CLICK ON OPPONENTS PIECE TO CAPTURE FIRST
		// 	pieceCaptured = pieceAt(x,y);
		// }
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		if (pieceAt(x, y) != null) {
			pieceSelected = pieceAt(x,y);
			selectX = x;
			selectY = y;

		}
		else {
			pieceSelected.move(x,y);
			moved = true;
			selectX = x;
			selectY = y;
		}
	}
	//*Places p at (x, y)
	//-------------------------------DONE----------------------------------------
	public void place(Piece p, int x, int y){
		if (p == null || (x > 8 || x < 0 ) || (y > 8 || y < 0 )) { //p out of bounds
			return;
		}
		//if p already exists on the board
		for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
		        if (board[i][j] == p) {
		            remove(i, j); //removes p from board if its already on the board
		        }
		    }
		}
		if (pieceAt(x, y) != null) { //if another piece exists at the coodinates
			remove(x,y);
		}
		
		board[x][y] = p; //puts p on the board

	}

	//Removes and returns the piece that was removed
	public Piece remove(int x, int y) {
		if ((x > 8 || x < 0 ) || (y > 8 || y < 0 )) {
			System.out.println("Out of Bounds");
			return null;
		}
		else if (pieceAt(x,y)== null){
			System.out.println("No piece at coordinates selected");
			return null;
		}
		Piece temp = pieceAt(x,y);
		board[x][y] = null;
		return temp;
	}
	//Returns if the current player can end their turn
	public boolean canEndTurn() {
		if (pieceCaptured != null || moved){
			return true;
		}
		return false;
	}
	//Called at the end of each turn
	//Handles switching of players and anything else at the end of the turn
	public void endTurn(){
		if (currentPlayer == "fire") {
			currentPlayer = "water";
		}
		else{
			currentPlayer = "fire";
		}
		pieceSelected.doneCapturing();
		pieceSelected = null;
		selectX = 0;
		selectY = 0;
		moved = false;

	}
	//Returns the winner of the game
	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j]!= null) {
			        if (board[i][j].isFire()) {
			        	fire +=1;
			        }
			        if (!board[i][j].isFire()) {
			        	water +=1;
			        }
		    	}
		    }
		}
		if (water > 0 && fire == 0) {
			return "Water";
		}
		else if (fire > 0 && water == 0) {
			return "Fire";
		}
		else if (fire == 0 && water == 0) {
			return "No one";
		}
		else {
			return null;
		}
	}
	//* starts a GUI supported version of the game/
	public static void main(String[] args) {
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        // pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)){
                	b.select((int) x,(int) y);
                }

                	
                
            
                
                // pieces[(int) x][(int) y] = true;
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                	b.endTurn();
            if (b.winner() != null) {
            	return;
            }
            }           
            StdDrawPlus.show(100);
        }
    }
		
}


//CAN SELECT NOT WOrkKING
//END TURN IS NOT WORKING, needs to be called 
