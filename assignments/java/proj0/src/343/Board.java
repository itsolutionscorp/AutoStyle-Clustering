public class Board {
	private static final int boardSize = 8;
	private Piece[][] piece;
	private int currentPlayer;
	private int pieceSelectedX;
	private int pieceSelectedY;
	private boolean hasMoved;
	private int firePiece;
	private int waterPiece;
	
	/*
	constructor of Board
	If shouldBeEmpty is true, initializes an empty board.
	Otherwise, initializes a Board with the default configuration.
	Note that an empty Board could possibly be useful for testing purposes. 
	*/
	public Board(boolean shouldBeEmpty){
		piece = new Piece[boardSize][boardSize];
		currentPlayer = 0;	//0 means fire
		firePiece = 0;
		waterPiece = 0;
		hasMoved = false;
		pieceSelectedX = -1;
		pieceSelectedY = -1;


		if (!shouldBeEmpty) {
			firePiece = 12;
			waterPiece = 12;

			//create fire pieces
			for (int i = 0; i < boardSize ; i += 2) {
				piece[i][0] = new Piece(true, this, i, 0, "pawn");
			}
			for (int i = 1; i < boardSize ; i += 2) {
				piece[i][1] = new Piece(true, this, i, 1, "shield");
			}
			for (int i = 0; i < boardSize; i += 2) {
				piece[i][2] = new Piece(true, this, i, 2, "bomb");
			}

			//create water pieces
			for (int i = 1; i < boardSize ; i += 2) {
				piece[i][boardSize - 1] = new Piece(false, this, i, boardSize - 1, "pawn");
			}
			for (int i = 0; i < boardSize; i += 2) {
				piece[i][boardSize - 2] = new Piece(false, this, i, boardSize - 2, "shield");
			}
			for (int i = 1; i < boardSize; i += 2) {
				piece[i][boardSize - 3] = new Piece(false, this, i, boardSize - 3, "bomb");
			}
		}
	}

	/*
	Gets the piece at position (x, y) on the board, 
	or null if there is no piece. If (x, y) are out of bounds, returns null.
	*/
	public Piece pieceAt(int x, int y){
		if (outOfBound (x,y)) {
			return null;
		}
		else {
			return piece[x][y];
		}
	}

	private boolean outOfBound(int x, int y) {
		if (x >= boardSize || x < 0 || y >= boardSize || y < 0) {
			return true;
		}
		return false;
	}
	/*
	Returns true if there is a piece the piece at (x, y) and it can be selected.
	*/

	public boolean canSelect(int x, int y) {
		if (outOfBound(x, y)){
			return false;
		}
		if (pieceAt(x,y) != null){
			Piece chosen = pieceAt(x, y);
			if (chosen.side() != currentPlayer || hasMoved) {
				return false;
			}
			else if (!hasSelect() || (hasSelect() && !hasMoved) ){
				return true;
			}
			return false; 
		}
		else {
			if (hasSelect() && validMove(pieceSelectedX, pieceSelectedY, x, y)) {
				
				if (hasMoved && !pieceAt(pieceSelectedX, pieceSelectedY).hasCaptured()) {
					return false;
				}
				else {
					return true;
				}
				
			}
			return false;
		}
	}

	private boolean hasSelect(){
		if (pieceSelectedX >= 0 && pieceSelectedX <boardSize &&pieceSelectedY >= 0 && pieceSelectedY <boardSize  ) {
			return true;
		}
		return false;
	}

	/*
	 Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	 or capture to (xf, yf) in a valid fashion compatible with the rules.
	*/
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece thisPiece = pieceAt(xi, yi);
		if (thisPiece == null || pieceAt(xf,yf) != null || outOfBound(xi, yi) || outOfBound(xf, yf)) {
			return false;
		}

		if (Math.abs(xi - xf )> 2 || Math.abs (yi - yf) > 2 || xi == xf || yi == yf) {
			return false;
		}		

		if (thisPiece.isKing()){
			if (Math.abs(xi - xf) == 1 && Math.abs (yi - yf) == 1) {
				if (!thisPiece.hasCaptured() && !hasMoved){
					return true;
				}
				return false;
			}
			else {
				if (Math.abs(xi - xf) == 2 && Math.abs (yi - yf) == 2) {
					int midX = Math.min(xi, xf) + 1;
					int midY = Math.min(yi, yf) + 1;
					if (pieceAt(midX,midY) != null) {
						if(thisPiece.side() != pieceAt(midX, midY).side()) {
							return true;
						}
					}
				}
				return false;
			}
		}
		else {
			if (thisPiece.isFire()) {
				if (yf - yi == 1 && Math.abs(xf-xi) == 1) {
					if (!thisPiece.hasCaptured()  && !hasMoved){
						return true;
					}
					return false;
				}
				if (yf - yi == 2 && Math.abs(xf-xi) == 2) {
					int midX = Math.min(xi, xf) + 1;
					int midY = Math.min(yi, yf) + 1;
					if (pieceAt(midX,midY) != null) {
						if(thisPiece.side() != pieceAt(midX, midY).side()) {
							return true;
						}
					}
				}
				
				return false;
			}
			else {
				if (yi - yf == 1 && Math.abs(xf-xi) == 1) {
					if (!thisPiece.hasCaptured()  && !hasMoved){
						return true;
					}
					return false;
				}
				if (yi - yf == 2 && Math.abs(xf-xi) == 2) {
					int midX = Math.min(xi, xf) + 1;
					int midY = Math.min(yi, yf) + 1;
					if (pieceAt(midX,midY) != null) {
						if(thisPiece.side() != pieceAt(midX, midY).side()) {
							return true;
						}
					}
				}
				return false;
			}
		}
		
	}

	/* Selects the piece at (x, y) if possible. Optionally, it is recommended
	 to color the background of the selected square white on the GUI via the 
	 pen color function. For any piece to perform a capture, that piece must have 
	 been selected first.*/

	public void select (int x, int y){
	 	if(pieceAt(x,y) == null) {
	 		Piece p = pieceAt(pieceSelectedX, pieceSelectedY);
	 		hasMoved = true;
	 	 	p.move(x,y);
	 	}
	 	pieceSelectedX = x;
		pieceSelectedY = y;

	}

	/*
	 Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	 If p already exists in the current Board, first removes it from its initial 
	 position. If another piece already exists at (x, y), p will replace that piece. 
	 (This method is potentially useful for creating specific test circumstances.)
	*/
	public void place(Piece p, int x, int y) {
		if (!outOfBound(x,y) && p != null) {
			for (int i = 0; i < boardSize; i +=1) {
				for(int j = 0; j < boardSize; j+= 1) {
					if (pieceAt(i,j) == p) {
						remove(i,j);
					}
				}
			}
			if (pieceAt(x, y)!= null){
				remove(x, y);
			}
			piece[x][y] = p;

				
		}
		//else dont do anything
	}

	/*
	 Executes a remove. Returns the piece that was removed. If the input (x, y) 
	 is out of bounds, returns null and prints an appropriate message. If there
	 is no piece at (x, y), returns null and prints an appropriate message.
	*/
	public Piece remove(int x, int y){
		if (outOfBound(x, y)) {
			System.out.println("index out of bound");
			return null;
		} 
		else if (pieceAt(x,y) == null) {
			System.out.println("no piece at (" + x + ", " + y + ")");
			return null;
		} 
		else {
			Piece p = pieceAt(x, y);
			piece[x][y] = null;
			if (p.isFire()) {
				firePiece -= 1;
			}
			else {
				waterPiece -= 1;
			}
			return p;
		}
	}

	
	/*
	Returns whether or not the the current player can end their turn. 
	To be able to end a turn, a piece must have moved or performed a capture.
	*/
	public boolean canEndTurn(){
		return hasMoved;
	}

	
	// Called at the end of each turn. Handles switching of players 
	// and anything else that should happen at the end of a turn.
	
	public void endTurn(){
		if (currentPlayer == 0){
			currentPlayer = 1;
		}
		else {
			currentPlayer = 0;
		}
		if (pieceAt(pieceSelectedX, pieceSelectedY) != null) {
			pieceAt(pieceSelectedX, pieceSelectedY).doneCapturing();
		}
		pieceSelectedX = -1;
		pieceSelectedY = -1;
		hasMoved = false;

	}

	/*
	Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board),
	 or null if the game is not yet over. Assume there is no stalemate situation 
	 in which the current player has pieces but cannot legally move any of them 
	 (In this event, just leave it at null). 
	Determine the winner solely by the number of pieces belonging to each team.
	*/
	public String winner() {
		int noFire = 0;
		int noWater = 0;
		for (int i = 0; i < boardSize; i++) {
			for(int j = 0; j<boardSize; j++) {
				if (pieceAt(i,j) != null) {
					if (pieceAt(i,j).isFire()) {
						noFire += 1;
					}
					else {
						noWater +=1;
					}
				}
			}
		}


		if (noFire <= 0 && noWater <= 0) {
			return "No one";
		}
		else if (noFire <= 0){
			return "Water";
		}
		else if(noWater <= 0){
			return "Fire";
		}
		else {

			return null;
		}

	}


	/** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(int N, Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

            	if (i == b.pieceSelectedX && j == b.pieceSelectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.pieceAt(i,j) != null) {

                	String imgName = "img/";
                	Piece p = b.piece[i][j];
                	if (p.isBomb()) {
                		imgName = imgName.concat("bomb");
                	}
                	else if (p.isShield()) {
                		imgName = imgName.concat("shield");
                	}
                	else {
                		imgName = imgName.concat("pawn");
                	}

                	if (p.isFire()) {
                		imgName = imgName.concat("-fire");
                	}
                	else {
                		imgName = imgName.concat("-water");
                	}

                	if (p.isKing()) {
                		imgName = imgName.concat("-crowned");
                	}

                	imgName = imgName.concat(".png");
                    StdDrawPlus.picture(i + .5, j + .5, imgName, 1, 1);
                }
            }
        }
    }

	//starts a GUI version of the game
	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

		Board gameBoard= new Board(false);
		drawBoard(8, gameBoard);

		while (gameBoard.winner() == null) {
		 	drawBoard(8, gameBoard);
		 	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (gameBoard.canSelect((int)x,(int)y)){
                	gameBoard.select((int)x,(int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if(gameBoard.canEndTurn()){
            		gameBoard.endTurn();
            	}
            }            
            StdDrawPlus.show(100);

		}
		drawBoard(8, gameBoard);
		System.out.println(gameBoard.winner());

	}


}

