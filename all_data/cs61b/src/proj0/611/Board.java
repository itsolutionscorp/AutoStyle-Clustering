public class Board {
	
	private Piece[][] pieces;
	private int xScale;
	private int yScale;
	private boolean fireTurn;
	private Piece pieceSelected;
	private int currX;
	private int currY;
	private boolean moveMade;
	private boolean playerCaptured;
	
	
	public static void main (String[] args) {
		
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		
		
		Board gameBoard = new Board(false);
		
		int mouseX = 0;
		int mouseY = 0;
		boolean runGame = true;
		while (runGame) {
			while (gameBoard.winner() == null) {
				gameBoard.drawBoard();
				if (StdDrawPlus.mousePressed()) {
					mouseX = (int) StdDrawPlus.mouseX();
					mouseY = (int) StdDrawPlus.mouseY();
					
					
					if (gameBoard.canSelect(mouseX, mouseY)) {
						//System.out.println("Able to select the piece at this location -- attempting to do so");
						gameBoard.select(mouseX, mouseY);
					}
					else {
						//System.out.println("Unable to select piece at that location");
					}
				}
				//Checks whether the user is using spacebar to end their turn
				if (StdDrawPlus.isSpacePressed()) {
					if (gameBoard.canEndTurn()) {
						gameBoard.endTurn();
					}
				}
				StdDrawPlus.show(10);
				
			}
			runGame = false;
		}

		System.out.println(gameBoard.winner());
		
		
	}
	
	public Board (boolean shouldBeEmpty) {
		xScale = 8;
		yScale = 8;
		pieceSelected = null;
		fireTurn = true;
		moveMade = false;
		currX = -1;
		currY = -1;
		
		if (shouldBeEmpty) {
			pieces = new Piece[xScale][yScale];
		}
		else {
			pieces = new Piece[xScale][yScale];
			//TODO: Optional: make this constructor cleaner and less tedious using for loops and conditionals
			
			pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
			pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
			pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
			pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
			pieces[1][1] = new Piece(true, this, 1, 1, "shield");
			pieces[3][1] = new Piece(true, this, 3, 1, "shield");
			pieces[5][1] = new Piece(true, this, 5, 1, "shield");
			pieces[7][1] = new Piece(true, this, 7, 1, "shield");
			pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
			pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
			pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
			pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
					
			pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
			pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
			pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
			pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
			pieces[0][6] = new Piece(false, this, 0, 6, "shield");
			pieces[2][6] = new Piece(false, this, 2, 6, "shield");
			pieces[4][6] = new Piece(false, this, 4, 6, "shield");
			pieces[6][6] = new Piece(false, this, 6, 6, "shield");
			pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
			pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
			pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
			pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
					
		}
		
	}
	
	private void drawBoard() {		
		
		int rows = xScale;
		int columns = yScale;
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            	
            	if ((i + j) % 2 == 0) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	}
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                Piece currPiece = pieces[i][j];
                if (currPiece != null) {
                	if (currPiece.isFire()) {
                		if (currPiece.isKing()) {
                			if (currPiece.isBomb()) {
                    			//draw a crowned fire bomb
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    		}
                    		else if (currPiece.isShield()) {
                    			//draw a crowned fire shield
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    		}
                    		else {
                    			//draw a crowned fire pawn
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    		}
                		}
                		else {
	                		if (currPiece.isBomb()) {
	                			//draw a fire bomb
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                		else if (currPiece.isShield()) {
	                			//draw a fire shield
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	                		else {
	                			//draw a fire pawn
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
                		}
                	}
                	else {
                		if (currPiece.isKing()) {
                			if (currPiece.isBomb()) {
                    			//draw a crowned water bomb
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    		}
                    		else if (currPiece.isShield()) {
                    			//draw a crowned water shield
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    		}
                    		
                    		else {
                    			//draw a crowned water pawn
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    		}
                		}
                		else {
	                		if (currPiece.isBomb()) {
	                			//draw a water bomb
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                		else if (currPiece.isShield()) {
	                			//draw a water shield
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		}
	                		
	                		else {
	                			//draw a water pawn
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
                		}
                	}
                }
            }
        }
	}
	
	
	private boolean withinBounds(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public Piece pieceAt(int x, int y) {
		if (! withinBounds(x, y)) {
			return null;
		}
		
		return pieces[x][y];
	}
	
	
	public boolean canSelect (int x, int y) {
		
		if (! withinBounds(x, y)) {
			return false;
		}
		
		Piece potentialPiece = pieceAt(x, y);
		
		if (potentialPiece != null) {
			if (moveMade) {
				return false;
			}
			else if (potentialPiece.isFire() != fireTurn) {
				return false;
			}
			else if (pieceSelected == null || ! moveMade) {
				return true;
			}
		}
		else {
			if (pieceSelected == null) {
				return false;
			}
			else if (! moveMade) {
				boolean canMakeMove = validMove(currX, currY, x, y);
				
				if (canMakeMove) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (moveMade && ! pieceSelected.hasCaptured()) {
				return false;
			}
			else if (moveMade && pieceSelected.hasCaptured()) {
				boolean canMakeMove = validMove(currX, currY, x, y);
				boolean willMakeCapturingMove = willCapture(x, y);
				
				if (canMakeMove && willMakeCapturingMove) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		return false;
	}
	
	public void select(int x, int y) {
		if (withinBounds(x, y)) {
			Piece nextPiece = this.pieceAt(x, y);
			//boolean ableToSelect = this.canSelect(x, y);
			
			if (nextPiece != null) {
				pieceSelected = nextPiece;
				currX = x;
				currY = y;
			}
			else {
				this.place(pieceSelected, x, y);
			}
			
		}
	}
	
	private boolean validMove (int xi, int yi, int xf, int yf) {
		
		//If the current piece is not within bounds (i.e. hasn't been selected) or the next
		//potential piece is not in bounds, return false
		if (! withinBounds(xf, yf) || ! withinBounds(xi, yi)) {
			return false;
		}
		
		int signedDY = yi - yf;
		
		int dx = Math.abs(xi - xf);
		int dy = Math.abs(yi - yf);
		
		if (dx == 0 || dy == 0) {
			return false;
		}
		
		//This figures out whether the movement is in a diagonal manner, which is
		//the only allowed movement in checkers.
		if ((dx / dy) != 1) {
			return false;
		}
		
		//This checks to see whether the piece is moving in a legitimate direction
		//if the piece is not a king piece. (upwards if the piece is a fire piece, downward
		//if the piece is a water piece)
		if (!pieceSelected.isKing()) {
			if (pieceSelected.isFire() && (signedDY > 0)) {
				return false;
			}
			else if (!pieceSelected.isFire() && (signedDY < 0)) {
				return false;
			}
		}
		
		//Checking whether there is currently a piece at the location that the player is trying to move to
		Piece occupyingPiece = pieceAt(xf, yf);
		
		if (occupyingPiece != null) {
			return false;
		}
		//Checking that the player is moving somewhere between 1 and 2 spaces
		else if (dx < 1 || dy < 1 || dx > 2 || dy > 2) {
			return false;
		}
		else if (dx == 2 && dy == 2) {
				
			boolean hasIntermediatePiece = willCapture(xf, yf); 
				
			if (! hasIntermediatePiece) {
				return false;
			}
		}
		
		return true;
	}
	
	
	private boolean willCapture(int x, int y) {
			
		int xTracker = currX;
		int yTracker = currY;
		
		int dX = currX - x;
		int dY = currY - y;
		
		if (dX == 0 || dY == 0) {
			return false;
		}
		
		int stepX = Math.abs(currX - x) / dX;
		int stepY = Math.abs(currY - y) / dY;
		
		xTracker -= stepX;
		yTracker -= stepY;
		
		Piece capturedPiece = this.pieceAt(xTracker, yTracker);
		
		if (capturedPiece == null) {
			return false;
		}
		else if (capturedPiece.isFire() == pieceSelected.isFire()) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	
	private Piece determineCapture(int x, int y) {
		
		int xTracker = currX;
		int yTracker = currY;
		
		int dX = currX - x;
		int dY = currY - y;
		
		if (dX == 0 || dY == 0) {
			return null;
		}
		
		int stepX = Math.abs(currX - x) / dX;
		int stepY = Math.abs(currY - y) / dY;
		
		xTracker -= stepX;
		yTracker -= stepY;
		
		Piece capturedPiece = this.pieceAt(xTracker, yTracker);
		
		if (capturedPiece == null) {
			this.playerCaptured = false;
		}
		else {
			this.playerCaptured = true;
		}
		
		return capturedPiece;
	}
		
	
	
	
	public void place(Piece p, int x, int y) {
		if (withinBounds(x, y) && p != null) {
			
			if (pieceSelected == null) {
				pieces[x][y] = p;
			}
			else {
				Piece replacedPiece = determineCapture(x, y);
				
				p.move(x, y);
				
				pieces[x][y] = p;
				
				
				if(p.isBomb() && replacedPiece != null) {
					pieces[x][y] = null;
					playerCaptured = false;
				}
				
				currX = x;
				currY = y; 
				moveMade = true;
			}
		}
	}
	
	
	
	public Piece remove(int x, int y) {
		Piece removedPiece = null;
		if (withinBounds(x, y)) {
			removedPiece = pieces[x][y];
			pieces[x][y] = null;
			moveMade = true;
			return removedPiece;
		}
		else {
			System.out.println("The space to be removed is out of bounds");
			return null;
		}
	}
	
	
	
	public boolean canEndTurn() {
		if (pieceSelected == null) {
			if (moveMade) {
				return true;				
			}
			else {
				return false;
			}
		}
		else if (moveMade || pieceSelected.hasCaptured()) {
			return true;
		}
		else {
			if (moveMade) {
				return true;
			}
			else if (pieceSelected.hasCaptured()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void endTurn() {
		moveMade = false;
		playerCaptured = false;
		//Not the most efficient way to do it, but "safer"
		if (fireTurn) {
			fireTurn = false;
		}
		else {
			fireTurn = true;
		}
		pieceSelected.doneCapturing();
		pieceSelected = null;
		currX = -1;
		currY = -1;
	}
	
	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece thePiece = pieces[i][j];
				
				if (thePiece != null) {
					if (thePiece.isFire()) {
						firePieces += 1;
					}
					else {
						waterPieces += 1;
					}
				}
			}
		}

		
		if (firePieces != 0 && waterPieces != 0) {
			return null;
		}
		else if (firePieces == 0 && waterPieces != 0) {
			return "Water";
		}
		else if (waterPieces == 0 && firePieces != 0) {
			return "Fire";
		}
		else if (firePieces == 0 && waterPieces == 0) {
			return "No one"; 
		}
		else {
			return null;
		}
		
		//TODO: Implementation checking for whether there is a stalemate (neither player can move)
		//Not sure about the exact conditions for this to occur
	}
	
	
	private void drawBoardSpecificColumns(int rows, int columns) {
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece currPiece = pieces[j][i];
                if (currPiece != null) {
                	if (currPiece.isFire()) {
                		if (currPiece.isKing()) {
                			if (currPiece.isBomb()) {
                    			//draw a crowned fire bomb
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    		}
                    		else if (currPiece.isShield()) {
                    			//draw a crowned fire shield
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    		}
                    		
                    		else {
                    			//draw a crowned fire pawn
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    		}
                		}
                		else {
	                		if (currPiece.isBomb()) {
	                			//draw a fire bomb
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                		else if (currPiece.isShield()) {
	                			//draw a fire shield
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	                		
	                		else {
	                			//draw a fire pawn
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
                		}
                	}
                	else {
                		if (currPiece.isKing()) {
                			if (currPiece.isBomb()) {
                    			//draw a crowned water bomb
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    		}
                    		else if (currPiece.isShield()) {
                    			//draw a crowned water shield
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    		}
                    		
                    		else {
                    			//draw a crowned water pawn
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    		}
                		}
                		else {
	                		if (currPiece.isBomb()) {
	                			//draw a water bomb
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                		else if (currPiece.isShield()) {
	                			//draw a water shield
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		}
	                		
	                		else {
	                			//draw a water pawn
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
                		}
                	}
                }
            }
        }
	}
}
