public class Board {

	// private static int boardSize;
	private Piece[][] pieces;
	private Piece selected; // Selected piece
	private int selX, selY; // Coordinates of selected piece
	private boolean isFireTurn, canMove, hasMoved;
    private String winner;

	private static final int boardSize = 8;
	//starts a GUI supported version of the game.
	public static void main(String[] args){
		Board b = new Board(false);
       	StdDrawPlus.setXscale(0, b.boardSize);
        StdDrawPlus.setYscale(0, b.boardSize);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner == null || b.winner.equals("No one")) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xInt = (int) x;
                int yInt = (int) y;
                if (b.canSelect(xInt, yInt))
                	 b.select(xInt, yInt);
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
                b.endTurn();
            }          
            StdDrawPlus.show(20);
        }

	}

	//if empty
	//return empty Board
	//else
	//initialize Board with default configuration
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[boardSize][boardSize];
		canMove = true;
		isFireTurn = true;
        hasMoved = false;
        winner = "No one";
		if (!shouldBeEmpty){
			this.setup();
		}
	}

	//Gets the piece at position (x,y) on the board, or null if there is no piece.
	//If (x, y) are out of bounds, returns null
	public Piece pieceAt(int x, int y){
		if (!isValidPosition(x, y))
			return null;
		else
			return pieces[x][y];
	}

	/* Returns true if the square at (x, y) can be selected.
	 * A piece may be selected if it is the corresponding player’s turn and one of the following is true:
	 * The player has not selected a piece yet.
	 * The player has selected a piece, but did not move it.
	 * An empty square may be selected if one of the following is true:
	 * During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty 
	 * spot which is a valid move for the previously selected Piece.
	 * During this turn, the player has selected a Piece, captured, and has selected another valid capture 
	 * destination. When performing multi-captures, you should only select the active piece once; all other
	 * selections should be valid destination points.
	 */
	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x, y);
		if (!isValidPosition(x, y))
			return false;
		if (selected != null && canMove){
			if (validMove(selX, selY, x, y))
				return true;
			else if ((p != null) && (p.isFire() == selected.isFire()) && !hasMoved){
				return true;
			}
			else
				return false;
		}
		if (selected == null && canMove){
			if (p == null)
				return false;
			if (isFireTurn && p.isFire()){
				//TODO: more checks
				return true;
			} else if (!isFireTurn && !p.isFire()){
				//TODO: more checks
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (!isValidPosition(xi, yi) || !isValidPosition(xf, yf)){ // Not on board
			System.out.println("Invalid move");
			return false;
		}
		int xDist = xf - xi;
		int yDist = yf - yi;

		if (Math.abs(xDist) > 2 || Math.abs(yDist) > 2) // Can't move that far
			return false;

		Piece p = pieceAt(xi, yi); // Piece at origin
		Piece pf = pieceAt(xf, yf); // Piece at destination (if any)
		Piece pi = null; // Intermediate piece
		if (pf != null)
			return false;
		if (p != null){
            if (Math.abs(xDist) == 1 && Math.abs(yDist) == 1 && p.hasCaptured())
                return false;
			if (p.isKing()){
				if (Math.abs(xDist) == 1 && Math.abs(yDist) == 1)
					return true;
				if (Math.abs(xDist) == 2 && Math.abs(yDist) == 2){
					if (xDist == 2 && yDist == 2){
						pi = pieceAt(xi+1, yi+1);
						return (pi != null && (pi.isFire() != p.isFire()));
					}
					if (xDist == -2 && yDist == 2){
						pi = pieceAt(xi-1, yi+1);
						return (pi != null && (pi.isFire() != p.isFire()));
					}
					if (xDist == 2 && yDist == -2){
						pi = pieceAt(xi+1, yi-1);
						return (pi != null && (pi.isFire() != p.isFire()));
					}
					if (xDist == -2 && yDist == -2){
						pi = pieceAt(xi-1, yi-1);
						return (pi != null && (pi.isFire() != p.isFire()));
					}
				}
			}
			else if (p.isFire()) { // Fire piece
				if (Math.abs(xDist) == 1 && yDist == 1){
					return true;
				}
				if (xDist == 2 && yDist == 2){
					pi = pieceAt(xi+1, yi+1);
					return (pi != null && (pi.isFire() != p.isFire()));
				}
				if (xDist == -2 && yDist == 2){
					pi = pieceAt(xi-1, yi+1);
					return (pi != null && (pi.isFire() != p.isFire()));
				}
			}
			else {
				if (Math.abs(xDist) == 1 && yDist == -1){
					return true;
				}
				if (xDist == 2 && yDist == -2){
					pi = pieceAt(xi+1, yi-1);
					return (pi != null && (pi.isFire() != p.isFire()));
				}
				if (xDist == -2 && yDist == -2){
					pi = pieceAt(xi-1, yi-1);
					return (pi != null && (pi.isFire() != p.isFire()));
				}
			}
		}
		return false;
	}

	/* Selects the piece at (x, y) if possible. Optionally, it is recommended 
	 * to color the background of the selected square white on the GUI via the 
	 * pen color function. For any piece to perform a capture, that piece must 
	 * have been selected first. */
	public void select(int x, int y){

		Piece p = pieceAt(x, y);
		// if (canSelect(x, y)){
		if (selected != null && p == null){
			place(selected, x, y);
            selected.move(x, y);
			remove(selX, selY);
            if (!(selected.hasCaptured() && canCaptureAgain(x, y))){
                canMove = false;
                // selected.doneCapturing();
                // selected = null;
            }
            else if (Math.abs(selX-x) == 1 && Math.abs(selY-y) == 1){
                canMove = false;
                selected = null;
            }
            hasMoved = true;
		}
		else {
			selected = pieceAt(x, y);
		}
        selX = x;
        selY = y;
        winner = checkWinner();
		// }
	}

    private boolean canCaptureAgain(int x, int y){
        for (int i = -2; i <=2; i += 4){
            for (int j = -2; j <= 2; j += 4) {
                if (validMove(x, y, x+i, y+j))
                    return true;
            }
        }
        return false;
    }

	/* Returns true if x, y is a valid place on the board */
	private boolean isValidPosition(int x, int y){
		return ((x >= 0 && x < boardSize) && (y >= 0 && y < boardSize));
	}

	/* Places p at (x, y). If (x, y) is out of bounds, does nothing. If 
	 * another piece already exists at (x, y), p will replace that piece. 
	 * (This method is potentially useful for creating specific test circumstances.) */
	public void place(Piece p, int x, int y){
        winner = null;
		if (!isValidPosition(x, y))
			return;
		pieces[x][y] = p;
	}

	/* Executes a remove. Returns the piece that was removed. If the input (x, y) 
	 * is out of bounds, returns null and prints an appropriate message. If there 
	 * is no piece at (x, y), returns null and prints an appropriate message. */
	public Piece remove(int x, int y) {
		if (!isValidPosition(x, y)) {
			System.out.println("Error: Tried to remove a piece from outside board");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("Error: No piece at x=" + x + ", y=" + y);
			return null;
		}
		else {
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
	}

	/* Returns whether or not the the current player can end their turn. To be able 
	 * to end a turn, a piece must have moved or performed a capture. */
	public boolean canEndTurn(){
		return (!canMove || hasMoved);
	}

	/* Called at the end of each turn. Handles switching of players and anything 
	 * else that should happen at the end of a turn. */
	public void endTurn(){
        if (selected != null){
            selected.doneCapturing();
            selected = null;
        }
		isFireTurn = !isFireTurn;
        canMove = true;
        hasMoved = false;
        selected = null;
        // winner = checkWinner();
        // System.out.println("Current winner = " + winner);
	}

    private String checkWinner(){
        Piece p;
        int fCount = 0, wCount = 0;
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++) {
                p = pieceAt(i, j);
                if (p != null){
                    if (p.isFire())
                        fCount++;
                    else
                        wCount++;
                }
            }
        }
        if (fCount == 0){
            if (wCount == 0)
                return "No one";
            else
                return "Water";
        }
        else if (wCount == 0)
            return "Fire";
        else
            return null;
    }

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces 
	 * on the board), or null if the game is not yet over. Assume there is no 
	 * stalemate situation in which the current player has pieces but cannot legally 
	 * move any of them (In this event, just leave it at null). Determine the winner 
	 * solely by the number of pieces belonging to each team. */
	public String winner(){
		return winner;
	}

	private void drawBoard(){
		for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece curPiece = pieces[i][j];
                if (curPiece != null) {
                	if (curPiece == selected)
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	drawPiece(pieces[i][j], i, j);
                }
            }
        }
	}

	private void drawPiece(Piece p, int i, int j){
        if (p.isShield()){
    		if (p.isFire()) {
    			if (p.isKing())
    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
    		}
    		else {
    			if (p.isKing())
    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
    		}
        }

        else if (p.isBomb()){
    		if (p.isFire()) {
    			if (p.isKing())
    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    		}
    		else {
    			if (p.isKing())
    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
    		}
        }

        else {
    		if (p.isFire()) {
    			if (p.isKing())
    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
    		}
    		else {
    			if (p.isKing())
    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
    		}
        }
	}

	private void setup(){
		//bottom row of red pawns
		for (int i = 0; i <= 6; i = i + 2){
			place(new Piece(true, this, i, 0, "pawn"), i, 0);
		}

		//2nd from bottom row of red shields
		for (int i = 1; i <= 7; i = i + 2){
			pieces[i][1] = new Piece(true, this, i, 1, "shield");
		}

		for (int i = 0; i <= 6; i = i + 2){
			pieces[i][2] = new Piece(true, this, i, 2, "bomb");
		}

		for (int i = 1; i <= 7; i = i + 2){
			pieces[i][5] = new Piece(false, this, i, 5, "bomb");
		}

		for (int i = 0; i <= 6; i = i + 2){
			pieces[i][6] = new Piece(false, this, i, 6, "shield");
		}		

		for (int i = 1; i <= 7; i = i + 2){
			pieces[i][7] = new Piece(false, this, i, 7, "pawn");
		}


		System.out.print("Setup Complete.");
	}
}