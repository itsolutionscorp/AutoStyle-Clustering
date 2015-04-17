public class Board {
	/* location of pieces */
	private boolean[][] ifPieces;
	private Piece[][] pieces;
	private boolean isEmpty;

	private boolean isFireTurn;
	private boolean hasPiece;
	private boolean hasMoved;
	private Piece chosenOne;

	// Constructor
    public Board(boolean shouldbeEmpty) {
    	ifPieces = new boolean[8][8];
		pieces = new Piece[8][8];
		isFireTurn = true;
	 	hasPiece = false;
		hasMoved = false;
		isEmpty = shouldbeEmpty;
		if (!isEmpty) {
			pieceArray();
		}
    }

    // fills ifPieces[][] and pieces[][]
    private void pieceArray() {
		int j = 0;
		int i = 0;    
    	// i: x-axis, j: y-axis	
    	// Construct pieces
		if (!isEmpty) {
    		// Fire Side pawns
			j = 0;
    		for (i = 0; i < 8; i += 2) {
    			ifPieces[i][j] = true;
    			pieces[i][j] = new Piece(true, this, i, j, "pawn");
    			}
    		// Fire Side shields
    		j = 1;
    		for (i = 1; i < 8; i += 2) {
    			ifPieces[i][j] = true;
    			pieces[i][j] = new Piece(true, this, i, j, "shield");
    			}
    		// Fire Side bombs
    		j = 2;
    		for (i = 0; i < 8; i += 2) {
    			ifPieces[i][j] = true; 
    			pieces[i][j] = new Piece(true, this, i, j, "bomb");
    			}
    		// Water Side Bombs
    		j = 5;
    		for (i = 1; i < 8; i += 2) {
    			ifPieces[i][j] = true;
    			pieces[i][j] = new Piece(false, this, i, j, "bomb");
    			}
     		// Water Side Shields
    		j = 6;
    		for (i = 0; i < 8; i += 2) {
    			ifPieces[i][j] = true;
    			pieces[i][j] = new Piece(false, this, i, j, "shield");
    			}  
    		// Water Side Pawns
    		j = 7;
    		for (i = 1; i < 8; i += 2) {
    			ifPieces[i][j] = true;
    			pieces[i][j] = new Piece(false, this, i, j, "pawn");
    			} 			
    	}
    }

    // draws board
    private void drawBoard() {
    	// draw board
    	for (int i = 0; i < 8; i+=1) {
            for (int j = 0; j < 8; j+=1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }  		
    	}
    }
    // draw pieces if !isEmpty
    private void drawPieces() {
        for (int i = 0; i < 8; i += 1) {
    		for (int j = 0; j < 8; j += 1) {
    			if (ifPieces[i][j]) {
    				drawPiece(i, j, getImage(pieces[i][j]));
    				}
    			}
    		}
    }

    // determines what image file to draw based on piece
    private String getImage(Piece p) {
		String image;
		if (p.isFire()) {
			if (p.isBomb()) {
				if (p.isKing()) {
					image = "img/bomb-fire-crowned.png";
				}
				else {
					image = "img/bomb-fire.png";
				}
			}
			else if (p.isShield()) {
				if (p.isKing()) {
					image = "img/shield-fire-crowned.png";
				}
				else {
					image = "img/shield-fire.png";
				}
			}
			else {
				if (p.isKing()) {
					image = "img/pawn-fire-crowned.png";
				}
				else {
					image = "img/pawn-fire.png";
				}
			}
		}
		else {
			if (p.isBomb()) {
				if (p.isKing()) {
					image = "img/bomb-water-crowned.png";
				}
				else {
					image = "img/bomb-water.png";
				}
			}
			else if (p.isShield()) {
				if (p.isKing()) {
					image = "img/shield-water-crowned.png";
				}
				else {
					image = "img/shield-water.png";
				}
			}
			else {
				if (p.isKing()) {
					image = "img/pawn-water-crowned.png";
				}
				else {
					image = "img/pawn-water.png";
				}
			}
		}
	return image;
    }

    // draws piece based on location
    private void drawPiece(int x, int y, String file) {
    	StdDrawPlus.picture(x + .5, y + .5, file, 1, 1);
    }

    // Gets the piece at position (x,y) on board, or null if there is no piece
    public Piece pieceAt(int x, int y) {
    	// check out of bounds
    	if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
    		return null;
    	}
    	if (ifPieces[x][y]) {
    		return pieces[x][y];
    	}
    	else {
    		return null;
    		}
    }
 
    // Checks if piece at (x,y) can move to (xf,yf) by capturing
    private boolean canCapture(int xi, int yi, int xf, int yf) {
    	// can only capture if piece in between
    	Piece p = pieceAt(xi,yi);
    	int i = (xf+xi)/2;
    	int j = (yf+yi)/2;
    	int xDis = xf-xi;
    	int yDis = yf-yi;
    	// check if piece in between 
    	Piece q = pieceAt(i,j);
    	if (q==null) {
    		return false;
    	}
    	// if Fire's turn, piece must move up unless King
    	if (isFireTurn) {
    		if (p.isKing()) {
    				if (q.side() != p.side()) {
    					return true;
		    		}
		    }			
    		else {
    			if (yDis==2) {
    				if (Math.abs(xDis) == 2) {
    					if (pieceAt(i,j).side() !=p.side()) {
    						return true;
    					}
    				}
    			}
    		}
    	}
    	// if Water's turn, piece must move down unless King
    	else {
    		if (p.isKing()) {
    				if (pieceAt(i,j).side() != p.side()) {
    					return true;
		    		}
		    }			
    		else {
    			if (yDis== -2) {
    				if (Math.abs(xDis) == 2) {
    					if (q.side() != p.side()) {
    						return true;
    					}
    				}
    			}
    		}
    	}
    	return false;
    }

    // returns true if piece at (xi,yi) can move to (xf, yf)
    // includes capture conditions
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if ((xf > 7) || (yf > 7) || (xf < 0) || (yf < 0)) {
    		return false;
    	}
    	if (ifPieces[xf][yf]) {
    		return false;
    	}
    	int xDif = xf - xi;
    	int yDif = yf-yi;
    	Piece p = pieces[xi][yi];
    	// if piece has captured before, can only capture again
    	if (p.hasCaptured()) {
    		if (p.isKing()) {
    			if ((Math.abs(xDif)==2) && (Math.abs(yDif)==2)) {
    				if (canCapture(xi,yi,xf,yf)) {
    					return true;
    				}
    			}
    		}
    		else {
    			// Fire pieces can only move up: yDif are positive
    			if (p.isFire()) {
	    			if (yDif == 2) {
    					if (Math.abs(xDif) == 2) {
    						if (canCapture(xi,yi,xf,yf)) {
    							return true;
    						}
    					}
    				}
    			}
    			// Water pieces can only move down: yDif are negative
    			else {
    			if (yDif == -2) {
    				if (Math.abs(xDif) == 2) {
    					if (canCapture(xi,yi,xf,yf)) {
    						return true;
    					}
    				}
    			}
    			}
    		}
    		p.doneCapturing();
    		return false;	
    	}

    	// Crowned pieces can move in any direction
    	if (p.isKing()) {
    		if ((Math.abs(xDif)==1) && (Math.abs(yDif)==1)) {
    			return true;
    		}
    		if ((Math.abs(xDif)==2) && (Math.abs(yDif)==2)) {
    			if (canCapture(xi,yi,xf,yf)) {
    				return true;
    			}
    		}
    	}
    	else {
    		// Fire pieces can only move up: yDif are positive
    		if (p.isFire()) {
    			if (yDif == 1) {
    				if (Math.abs(xDif) == 1) {
    					return true;
    				}
    			}
    			if (yDif == 2) {
    				if (Math.abs(xDif) == 2) {
    					if (canCapture(xi,yi,xf,yf)) {
    						return true;
    					}
    				}
    			}
    		}
    		// Water pieces can only move down: yDif are negative
    		else {
    			if (yDif == -1) {
    				if (Math.abs(xDif) == 1) {
    					return true;
    				}
    			}
    			if (yDif == -2) {
    				if (Math.abs(xDif) == 2) {
    					if (canCapture(xi,yi,xf,yf)) {
    						return true;
    					}
    				}
    			}
    		}
    	}
    	return false;
    }

    // returns true if Piece can be selected + is player's turn
    private boolean selectPiece(int i, int j) {
        // accounts for turn
    	if (isFireTurn) {
    		if (ifPieces[i][j]) {
    			if (pieces[i][j].isFire()) {
    				return true;
    			}
    		}
    	}
    	else {
    		if (ifPieces[i][j]) {
    			if (!pieces[i][j].isFire()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    // gets coordinates (x,y) of piece
    private int[] getCoordinates(Piece p) {
        int[] coord = new int[2];
        for (int i = 0; i < 8; i += 1) {
        	for (int j = 0; j < 8; j += 1) {
        		if (p == pieces[i][j]) {
        			coord[0] = i;
        			coord[1] = j;
        			break;
        		}
        	}
        }
        return coord;
    }

    // Returns true if square can be selected
    public boolean canSelect(int x, int y) {
    	// Check if (x,y) within bounds (0-7)
    	if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
    		return false;
    	}
    	// if (x,y) has piece
    	if (ifPieces[x][y]) {
    		if (hasMoved) {
    			return false;
    		}
    		// Check if player has already picked piece
    		if (!hasPiece) {
    			// check if piece is selectable
    			if (selectPiece(x, y)) {
    				return true;
    			}
    		}
    		else if (hasPiece) {
    			// can't select piece again
    			//if (chosenOne == pieceAt(x,y)) {
    			//	return false;
    			//}
    			if (!hasMoved) {
    				if (selectPiece(x, y)) {
    					return true;
    				}
    			}
    			else if (chosenOne.hasCaptured()) {
    				if (selectPiece(x,y)) {
    					return false;
    				}
    			}
    		}
    		return false;
    	}
    	// if (x,y) is empty
    	else {
    		// if piece selected
    		if (hasPiece) {
    			//chosenOne will have value, get coordinates to determine if it can move
				int[] start = getCoordinates(chosenOne);
    			// if piece has captured previously
    			if (chosenOne.hasCaptured()) {
    				if (validMove(start[0], start[1], x, y)) {
    					return true;
    				}
    			}
    			// if piece hasn't moved yet
    			if (!hasMoved) {
    				if (validMove(start[0], start[1], x, y)) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
	
	// selects square at (x,y) if canSelect true
	public void select(int x, int y) {

		if (ifPieces[x][y]) {
			if (!hasMoved) {
			chosenOne = pieceAt(x,y);
			hasPiece = true;
			}
		}
		// if able to select empty square, allow movement
		else {
			int[] oldCoords = getCoordinates(chosenOne);
			// if simply moving
			Piece p = remove(oldCoords[0], oldCoords[1]);
			place(p, x, y);
			chosenOne.move(x,y);
			pieces[oldCoords[0]][oldCoords[1]] = null;
			ifPieces[oldCoords[0]][oldCoords[1]] = false;
			//if captured a piece
			if (p.hasCaptured()) {
				if (!p.isBomb()) {
				chosenOne = pieceAt(x,y);
				hasPiece = true;
				hasMoved = true;
				}
				else if (p.isBomb()) {
					//reset variables
					chosenOne = null;
					hasPiece = false;
					hasMoved = true;
				}
			}
			else {
				chosenOne.doneCapturing();
				// reset variables
				chosenOne = null;
				hasPiece = false;
				hasMoved = true;
			}
		}
	}

	// places piece p at (x,y)
	public void place(Piece p, int x, int y) {
		// check out of bounds
    	if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {

    	}
    	else if (p==null) {

    	}
    	else {
    	pieces[x][y] = p;
    	ifPieces[x][y] = true;
    	}
	}

	// returns removed piece from (x,y)
	public Piece remove(int x, int y) {
		// check boundaries
    	if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
    		System.out.println("Out of bounds!");
    		return null;
    	}
    	else if (pieces[x][y] == null) {
    		System.out.println("No piece at (" + x + "," + y + ")");
    		return null;
    	}
    	Piece temp = pieceAt(x,y);
    	pieces[x][y] = null;
    	ifPieces[x][y] = false;
    	return temp;
    	
	}

	// returns whether current player can end turn
	public boolean canEndTurn() {
		// Movement includes capturing
		if (hasMoved) {
			return true;
		}
		return false;
	}

	// return whether game is over or not
	private boolean isGameOver() {
		int[] leading = countPieces();
		// Game ends when only 1 or no piece left
		if ((leading[0] <= 1) || (leading[1] == 0) || (leading[2] == 0)) {
			return true;
		}

		return false;
	}

	// if canEndTurn, called at end of each turn and switches player
	public void endTurn() {
		// switch side
		isFireTurn = !isFireTurn;
		// reset booleans
		hasPiece = false;
		hasMoved = false;
		chosenOne = null;
		// check if game ends
		if (isGameOver()) {
			System.out.println(winner());
		}
	}

	//Count pieces left & determines which side has more pieces 
	private int[] countPieces() {
		int sumFire = 0;
		int sumWater = 0;
		int sum = 0;
		// winner[0] = total sum of pieces left on board
		// winner[1] = total number of Fire pieces left
		// winner[2] = total number of Water pieces left
		int[] winner = new int[3];

		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (ifPieces[i][j]) {
					sum += 1;
					if (pieces[i][j].isFire()) {
						sumFire += 1;
					}
					else {
						sumWater += 1;
					}
				}
			}
		}

		winner[0] = sum;
		winner[1] = sumFire;
		winner[2] = sumWater;
		return winner;
	}

	// Returns winner of game based on number of pieces left
	public String winner() {
		int[] win = countPieces();
		if (win[0]==0) {
			return "No one";
		}
		if (win[1] == 0) {
			return "Water";
		}
		if (win[2] == 0) {
			return "Fire";
		}
		return null;
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
    	Board b = new Board(false);
    	int i = 0;
    	int j = 0;
    	while (true) {
    		b.drawBoard();
    	    b.drawPieces();
    		if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                i = (int) x;
                j = (int) y;
                if (b.canSelect(i, j)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			StdDrawPlus.square(i + .5, j + .5, .5);
                	b.select(i, j);
            	}
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
    	    StdDrawPlus.show(50);  
    	}
	}
}