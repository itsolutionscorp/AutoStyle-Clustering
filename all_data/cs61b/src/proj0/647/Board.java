public class Board {
	// Board class instance variables
	private static boolean shouldBeEmpty = false;
	private Piece[][] pieces = new Piece[8][8];
	private int player = 0;
	private Piece selectedPiece;
	private Piece savePiece;
	private boolean moved = false;
	private boolean bombCapture = false;

	public static void main(String[] args) {
		//Create the Board
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board board = new Board(Board.shouldBeEmpty);
        String winner = board.winner();
        board.drawBoard();

        //Play a game
        while (winner == null) {
        	if (board.canEndTurn() && StdDrawPlus.isSpacePressed()) {
                board.endTurn();
                board.drawBoard();
            }
        	if (StdDrawPlus.mousePressed()) {
                double posX = StdDrawPlus.mouseX();
                double posY = StdDrawPlus.mouseY();
                int x = (int) posX;
                int y = (int) posY;
                if (board.canSelect(x, y)) {
                	board.select(x, y);
                	// if (board.selectedPiece != null)
                	// 	board.savePiece = board.selectedPiece;
                }
                winner = board.winner();
                board.drawBoard();
            }
            StdDrawPlus.show(100);            
        }
        System.out.println(winner + " wins!!");

	}

	/* Constructor for Board
	 * If shouldBeEmpty is true, initialize empty Board.
	 * Else initialize with default configuration.
	 */
	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
    		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if ((i + j) % 2 == 0) {
            			if (j == 0)
            				pieces[i][j] = new Piece(true, this, i, j, "pawn");
            			if (j == 1)
            				pieces[i][j] = new Piece(true, this, i, j, "shield");
            			if (j == 2)
            				pieces[i][j] = new Piece(true, this, i, j, "bomb");
            			if (j == 5) 
            				pieces[i][j] = new Piece(false, this, i, j, "bomb");
            			if (j == 6) 
            				pieces[i][j] = new Piece(false, this, i, j, "shield");
            			if (j == 7) 
            				pieces[i][j] = new Piece(false, this, i, j, "pawn");
            		}
            	}
            }
    	}
	}

	/* Draws an 8 x 8 board. Adapted from:
     * http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        if (selectedPiece != null) {
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
   			StdDrawPlus.filledSquare(getX(selectedPiece) + .5, getY(selectedPiece) + .5, .5);
    	}
        drawPieces();
    }

    /* Draws all the pieces in Piece[][] pieces on the board.
     */
    private void drawPieces() {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		Piece p = pieces[i][j];
            		if (p.isFire() && p.isBomb() && p.isKing())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            		else if (p.isFire() && p.isBomb())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            		else if (p.isFire() && p.isShield() && p.isKing()) 
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            		else if (p.isFire() && p.isShield()) 
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            		else if (p.isFire() && p.isKing()) 
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            		else if (p.isFire()) 
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            		else if (p.isBomb() && p.isKing()) 
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            		else if (p.isBomb())
            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            		else if (p.isShield() && p.isKing()) 
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            		else if (p.isShield())
            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            		else if (p.isKing())
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            		else 
            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            	}
            }
        }
    }

    //Gets the x coordinate of Piece p
    //Assumes that p exists in pieces
    private int getX(Piece p) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p)
            		return i;
            }
        }
        return 0;
    }

    //Gets the y coordinate of Piece p
    //Assumes that p exists in pieces
    private int getY(Piece p) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == p)
            		return j;
            }
        }
        return 0;
    }

    //Gets the type of Piece p
    private String getType(Piece p) {
    	if (p.isShield()) {
    		if (p.isKing())
    			return "shieldking";
    		return "shield";
    	}
    	else if (p.isBomb()) {
    		if (p.isKing())
    			return "bombking";
    		return "bomb";
    	}
    	else {
    		if (p.isKing())
    			return "pawnking";
    		return "pawn";
    	}
    }

	/* Returns the Piece at position (x, y).
	 * Returns null if no piece or out of bounds.
	 */
	public Piece pieceAt(int x, int y) {
		if (x>7 || y>7 || x<0 || y<0) 
			return null;
		else
			return pieces[x][y];
	}

	/* Returns true if the square at (x, y) can be selected.
	 * Returns false otherwise.
	 */
	public boolean canSelect(int x, int y) {
		boolean result = false;
		Piece p = pieceAt(x, y);
		if (p != null && p.side() == player && !bombCapture) {
			if ((selectedPiece == null) || (selectedPiece != null && !moved))
				result = true;
		}
		else if (selectedPiece != null && !bombCapture && 
				validMove(getX(selectedPiece), getY(selectedPiece), x, y)) {
			if (!moved)
				result = true;
			else if (moved && savePiece.hasCaptured())
				result = true;
		}
		return result;
	}

	/* ** OPTIONAL **
	 * Returns true if the Piece at (xi, yi) can move to (xf, yf) or
	   can capture a Piece at (xf, yf).
	 * Return false otherwise.
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean result = false;
		Piece p = pieceAt(xi, yi);
		Piece other = pieceAt(xf, yf);
		Piece pULeft = pieceAt(xi-1, yi+1);
		Piece pURight = pieceAt(xi+1, yi+1);
		Piece pDLeft = pieceAt(xi-1, yi-1);
		Piece pDRight = pieceAt(xi+1, yi-1);
		if (p != null && other == null) {
			if (!p.isKing()) {
				if (p.isFire()) {
					if (yf == (yi + 1) && (xf == (xi - 1) || xf == (xi + 1)) && !moved)
						result = true;
					else if (pULeft != null && !pULeft.isFire() && 
							yf == (yi + 2) && xf == (xi - 2))
						result = true;
					else if (pURight != null && !pURight.isFire() && 
							yf == (yi + 2) && xf == (xi + 2))
						result = true;
				}
				else {
					if (yf == (yi - 1) && (xf == (xi - 1) || xf == (xi + 1)) && !moved)
						result = true;
					else if (pDLeft != null && pDLeft.isFire() && 
							yf == (yi - 2) && xf == (xi - 2))
						result = true;
					else if (pDRight != null && pDRight.isFire() && 
							yf == (yi - 2) && xf == (xi + 2))
						result = true;
				}	
			}
			else {
				int side = p.side();
				if ((yf == (yi - 1) || yf == (yi + 1)) && 
					(xf == (xi - 1) || xf == (xi + 1)) && !moved)
					result = true;
				else if (pULeft != null && pULeft.side() != side 
						&& yf == (yi + 2) && xf == (xi - 2))
					result = true;
				else if (pURight != null && pURight.side() != side &&
						yf == (yi + 2) && xf == (xi + 2))
					result = true;	
				else if (pDLeft != null && pDLeft.side() != side && 
						yf == (yi - 2) && xf == (xi - 2))
					result = true;
				else if (pDRight != null && pDRight.side() != side && 
						yf == (yi - 2) && xf == (xi + 2))
					result = true;
			}
		}
		return result;
	}

	/* Selects the square at (x, y).
	 * Assumes canSelect(x, y) returns true.
	 * OPTIONALLY colors a square white when selected.
	 */
	public void select(int x, int y) {
		if (selectedPiece != null)
            savePiece = selectedPiece;
		selectedPiece = pieceAt(x, y);
		if (selectedPiece == null && savePiece != null && !bombCapture) {
        	if (savePiece.isBomb() && (x == getX(savePiece) + 2 || 
        		x == getX(savePiece) - 2))
        		bombCapture = true;
        	savePiece.move(x, y);
        	selectedPiece = pieceAt(x, y);
        	moved = true;
        }
	}

	/* Places Piece p at position (x, y).
	 * If p is null, do nothing.
	 * If another Piece exists at (x, y), p will replace it.
	 */
	public void place(Piece p, int x, int y) {
		if (p != null) {
			pieces[x][y] = p;
		}
	}

	/* Removes the piece at (x, y) and returns it.
	 * If (x, y) is out of bounds or there is no piece at (x, y), 
	   returns null and prints an appropriate message.
	 */
	public Piece remove(int x, int y) {
		if (x>7 || y>7 || x<0 || y<0) {
			System.out.println("Error: index out of bounds");
			return null;
		}
		Piece p = pieces[x][y];
		if (p == null)
			System.out.println("Error: no piece at (" + x + ", " + y + ")");
		pieces[x][y] = null;
		return p;
	}

	/* Returns whether or not the current player can end their turn.
	 * To end a turn, a Piece must be moved or have performed a capture.
	 */
	public boolean canEndTurn() {
		return moved;
	}

	/* Called at the end of each turn; handles switching and anything 
	   else that should happen at the end of a turn.
	 */
	public void endTurn() {
		player = 1 - player;
		savePiece.doneCapturing();
		selectedPiece = null;
		savePiece = null;
		bombCapture = false;
		moved = false;
	}

	/* Returns winner of the game: "Fire", "Watter", or "No one" (tie)
	 * Returns null if the game is not over yet.
	 */
	public String winner() {
		int numFire = 0;
		int numWater = 0;
		String result = null;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) != null) {
                	if (pieceAt(i, j).isFire()) 
                		numFire += 1;
                	else
                		numWater += 1;
                }	
            }
        }
        if (numFire == 0 & numWater == 0)
        	result = "No one";
        else if (numFire == 0)
        	result = "Water";
        else if (numWater == 0) 
        	result = "Fire";
        return result;
	}
}