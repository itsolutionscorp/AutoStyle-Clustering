public class Board {
	private static boolean[][] squares = new boolean[8][8];
	private static int N = 8;
	private Piece[][] b = new Piece[N][N];
	private boolean hasSelected = false;
	private Piece prevSelected = null;
	private int prevSelectedX, prevSelectedY;
	private int whoseTurn = 0;
	private boolean hasMoved = false;
	private boolean hasCaptured = false;
	private int numFire = 0;
	private int numWater = 0;
//git problems
	/* Starts a GUI supported version of the game */
	public static void main(String[] args) {
		Board b = new Board(false);
		
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int)y)) {
	                b.select((int)x, (int)y);
	        	}
	        }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }            
            StdDrawPlus.show(10);
        }
	}

	private void drawBoard(int N) {
		// Drawing a plain board
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }

        if (hasSelected) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
           	StdDrawPlus.filledSquare(prevSelectedX + .5, prevSelectedY + .5, .5);
        }

        // Drawing pieces
        for (int x = 0; x < 8; x++) {
        	for (int y = 0; y < 8; y++) {
        		Piece p = pieceAt(x, y);
        		if (p != null) {
	    			if (p.isFire() && p.isShield() && !p.isKing()) {
	    				StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
	    			}
	    			else if (p.isFire() && p.isBomb() && !p.isKing()) {
	    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
	    			}
	    			else if (!p.isFire() && p.isBomb() && !p.isKing()) {
	    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
	    			}
	    			else if (!p.isFire() && p.isShield() && !p.isKing()) {
	    				StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
	    			}
	    			else if (!p.isFire() && !p.isKing()) {
	    				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
	    			}
	    			else if (p.isFire() && !p.isKing()) {
	    				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
	        		}
	        		else if (p.isFire() && p.isBomb() && p.isKing()) {
	        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
	        		}
	        		else if (p.isFire() && p.isShield() && p.isKing()) {
	        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
	        		}
	        		else if (p.isFire() && p.isKing()) {
	        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
	        		}
	        		else if (!p.isFire() && p.isBomb() && p.isKing()) {
	        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
	        		}
	        		else if (!p.isFire() && p.isShield() && p.isKing()) {
	        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
	        		}
	        		else if (!p.isFire() && p.isKing()) {
	        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
	        		}
	        	}
        	}
        }
	}

	// Constructor for Board.
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			return;
		} else {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if ((x + y) % 2 == 0) {
						if (y == 0) {
							b[x][y] = new Piece(true, this, x, y, "pawn");
						}
						else if (y == 1) {
							b[x][y] = new Piece(true, this, x, y, "shield");
						}
						else if (y == 2) {
							b[x][y] = new Piece(true, this, x, y, "bomb");
						}
						else if (y == 5) {
							b[x][y] = new Piece(false, this, x, y, "bomb");
						}
						else if (y == 6) {
							b[x][y] = new Piece(false, this, x, y, "shield");
						}
						else if (y == 7) {
							b[x][y] = new Piece(false, this, x, y, "pawn");
						}
					}
				}
			} return;
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x >= 8 || y >= 8) || (x < 0) || (y < 0)) {
			return null;
		} else if (b[x][y] == null) {
			return null;
		}
		return b[x][y];
	}

	/* Returns true if the square at (x, y) can be selected. */
	public boolean canSelect(int x, int y) {
		Piece nextSquare = pieceAt(x, y);
		if (nextSquare != null) {
			if (hasCaptured){
				return false;
			}
			else if (hasSelected && nextSquare.isKing()) {				
				return false;
			} else if (!hasCaptured && whoseTurn == nextSquare.side()) {					
				return true;
			} else if (whoseTurn != nextSquare.side()) {											
				return false;
			} else if (hasSelected && !hasMoved) {
				return true;
			}
			else {
				return false;
			}
		}

		else {
			if (hasSelected && !hasMoved &&
				validMove(prevSelectedX, prevSelectedY, x, y)) {
				return true;
			} else if (hasSelected && prevSelected != null && prevSelected.hasCaptured() &&
				validMove(prevSelectedX, prevSelectedY, x, y)) {
				return true;
			} else {
				return false;
			}
		}
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		// [0, 2] --> [1, 3]
		// [4, 2] --> [3, 3] or [5, 3]
		// [1, 5] --> [0, 4] or [2, 4]
		// [4, 6] --> [3, 5] or [5, 5]
		Piece prevP = pieceAt(xi, yi);
		Piece nextP = pieceAt(xf, yf);

		if (prevP.isKing()) {
			if (((xf - xi) == 1 || (xi - xf) == 1) && ((yf - yi) == 1 || (yi - yf) == 1)) 
			{
				return true;
			} else 
			{
				return false;
			}
		}
		if (prevP.isFire()) 
		{
			if (((xf - xi) == 1 || (xi - xf) == 1) && (yf - yi) == 1) 
			{
				if (nextP != null && !nextP.isFire()) 
				{
					return false;
				}
				return true;
			} 
			else if ((xf - xi) == 2 && (yf - yi) == 2) 
			{
				Piece pBetween = pieceAt(xi+1, yi+1);
				if (pBetween != null && prevP.side() != pBetween.side()) 
				{
					return true;
				}
				return false;
			} 
			else if ((xi - xf) == 2 && (yf - yi) == 2) 
			{
				Piece pBetween = pieceAt(xi-1, yi+1);
				if (pBetween != null && prevP.side() != pBetween.side()) 
				{
					return true;
				}
			} else 
			{
				return false;
			}
		} else 
		{
			if (((xf - xi) == 1 || (xi - xf) == 1) && (yi - yf) == 1) 
			{
				if (nextP != null && nextP.isFire()) 
				{
					return false;
				}
				return true;
			} 
			else if ((xf - xi) == 2 && (yi - yf) == 2) 
			{
				Piece pBetween = pieceAt(xi+1, yi-1);
				if (pBetween != null && prevP.side() != pBetween.side()) 
				{
					return true;
				}
				return false;
			} 
			else if ((xi - xf) == 2 && (yi - yf) == 2) 
			{
				Piece pBetween = pieceAt(xi-1, yi-1);
				if (pBetween != null && prevP.side() != pBetween.side()) 
				{
					return true;
				}
			} 
			else 
			{
				return false;
			}
		}
		return false;
	}
	
	/* Selects the square at (x, y). */
	public void select(int x, int y) {
		Piece occupyingPiece = pieceAt(x, y);
		if (hasSelected) {
			if (occupyingPiece == null) {
				prevSelected.move(x, y);
				hasSelected = false;
				hasMoved = true;

				if (prevSelected.hasCaptured()) {
					hasCaptured = true;
				}
			}
		}
		prevSelectedX = x;
		prevSelectedY = y;
		prevSelected = pieceAt(prevSelectedX, prevSelectedY);
		hasSelected = true;
	}

	/* Places p at (x, y). */
	public void place(Piece p, int x, int y) {
		if (x >= 8 || y >= 8 || p == null) {
			return;
		} else {
			if (pieceAt(x, y) != null) {
				remove(x, y);
			}
			b[x][y] = p;
			if (p.isFire()) {
				numFire += 1;
			} else {
				numWater += 1;
			}
		}
	}
	
	/* Executes a remove. */
	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			System.out.println("(" + x + ", " + y + ") is out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece at (" + x + ", " + y + ").");
			return null;
		} else {
			prevSelected = pieceAt(x, y);
			b[x][y] = null;
			if (prevSelected.isFire()) {
				numFire -= 1;
			} else {
				numWater -= 1;
			}
			return prevSelected;
		}
	}

	/* Returns whether or not the current player can end their turn. */
	public boolean canEndTurn() {
		if (hasMoved || hasCaptured) {
			return true;
		}
		return false;
	}

	/* Called at the end of each turn. */
	public void endTurn() {
		if (whoseTurn == 0) {
			whoseTurn = 1;
		} else {
			whoseTurn = 0;
		}
		hasMoved = false;
		hasSelected = false;
		if (prevSelected != null) {
			prevSelected.doneCapturing();
		}
		prevSelected = null;
		prevSelectedX = 0;
		prevSelectedY = 0;
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie/ no pieces on the board),
	   or null if the game is not yet over. Assume there is no stalemate situation in which the
	   current player has pieces but cannot legally move any of them.
	   Determine the winner solely by the number of pieces belonging to each team. */
	public String winner() {
		if (numFire == 0 && numWater != 0) {
			return "Water";
		} else if (numWater == 0 && numFire != 0) {
			return "Fire";
		} else if (numWater == 00 && numFire == 0) {
			return "No one";
		}
		return null;
	}
}