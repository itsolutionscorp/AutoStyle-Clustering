/* 
 * @myname Neil Shankar (amo)
 * collaborated with Valentine Wallace (discussed general strategies)
 * all code has been written independently
 */

public class Board{

	private static final int SIDE_LENGTH = 8;
	private boolean isEmpty;
	private Piece[][] grid;
	private boolean fireTurn;
	private Piece selected;
	private int selected_x;
	private int selected_y;
	private boolean hasMoved;
	private int clickedRow;
	private int clickedCol;
	private boolean hasExploded;
	
	/* starts a GUI supported version of the game.
	 * credit to Valentine Wallace for idea to use b.___() to call
	 * nonstatic/instance methods */
	public static void main(String[] args){
        Board b = new Board(false);
        b.runGame(b);
	}

	/* GUI loop */
	private void runGame(Board b){
		StdDrawPlus.setXscale(0, SIDE_LENGTH);
        StdDrawPlus.setYscale(0, SIDE_LENGTH);

		while (b.winner() == null){
			StdDrawPlus.clear();
			b.handleClick(b);
			b.handleSpace(b);
			b.drawBoard(b);
			if (!isEmpty){
				b.drawPieces(b);
			}
			StdDrawPlus.show(20);
		}
	}

	/* handles mouse click event (to select pieces/locations) */
	private void handleClick(Board b){
		if (StdDrawPlus.mousePressed()){
			clickedCol = (int)StdDrawPlus.mouseX();
			clickedRow = (int)StdDrawPlus.mouseY();
			if(outOfBounds(clickedCol, clickedRow)){
				return;
			}
			if (b.canSelect(clickedCol, clickedRow)){
				b.select(clickedCol, clickedRow);
			}
		}
	}

	/* handles space bar pressed event (to end turn) */
	private void handleSpace(Board b){
		if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
			b.endTurn();
		}
	}

	/* draws empty board */
	private void drawBoard(Board b){
        for (int col = 0; col < SIDE_LENGTH; col++) {
            for (int row = 0; row < SIDE_LENGTH; row++) {
                if ((row + col) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }else{               
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }if (row == b.selected_y && col == b.selected_x){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(col + 0.5, row + 0.5, 0.5);
            }
        }
	}

	/* draws all pieces on the board */
	private void drawPieces(Board b){
    	for (int col = 0; col < SIDE_LENGTH; col++) {
            for (int row = 0; row < SIDE_LENGTH; row++) {
            	Piece p = b.pieceAt(col, row);
            	if (p != null){
            		String path = b.constructImagePath(p);
            		StdDrawPlus.picture(col + 0.5, row + 0.5, 
            							path, 1, 1);
            	}
           	}
        }
	}

	/* returns path to desired image */
    private String constructImagePath(Piece p){
		String imgpath = "img/";
		if (p.isBomb()){
			imgpath += "bomb-";
		}else if (p.isShield()){
			imgpath += "shield-";
		}else{
			imgpath += "pawn-";
		}
		if (p.isFire()){
			imgpath += "fire";
		}else{
			imgpath += "water";
		}
		if (p.isKing()){
			imgpath += "-crowned";
		}
		imgpath += ".png";
		return imgpath;
    }

	/* The constructor for Board. If shouldBeEmpty is true, 
	 * initializes an empty Board. Otherwise, initializes a Board 
	 * with the default configuration. Note that an empty Board 
	 * could possibly be useful for testing purposes. */
	public Board(boolean shouldBeEmpty){
		grid = new Piece[SIDE_LENGTH][SIDE_LENGTH];
		this.isEmpty = shouldBeEmpty;
		initVars();
		if (!isEmpty){
			initializeGrid();
		}
	}

	/* Initializes the board with the pieces in starting position */
	private void initializeGrid(){
		for (int col = 0; col < SIDE_LENGTH; col+=2){

			new Piece(true, this, col, 0, "pawn");
			new Piece(true, this, col+1, 1, "shield");
			new Piece(true, this, col, 2, "bomb");
			new Piece(false, this, col+1, SIDE_LENGTH-3, "bomb");
			new Piece(false, this, col, SIDE_LENGTH-2, "shield");
			new Piece(false, this, col+1, SIDE_LENGTH-1, "pawn");

			// place(new Piece(true, this, col, 0, "pawn"), col, 0);
			// place(new Piece(true, this, col+1, 1, "shield"), col+1, 1);
			// place(new Piece(true, this, col, 2, "bomb"), col, 2);
			// place(new Piece(false, this, col+1, SIDE_LENGTH-3, "bomb"), col+1, SIDE_LENGTH-3);
			// place(new Piece(false, this, col, SIDE_LENGTH-2, "shield"), col, SIDE_LENGTH-2);
			// place(new Piece(false, this, col+1, SIDE_LENGTH-1, "pawn"), col+1, SIDE_LENGTH-1);
		}
	}

	/* sets initival values of global variables */
	private void initVars(){
		fireTurn = true;
		selected = null;
		selected_x = -1;
		selected_y = -1;
		hasMoved = false;
		hasExploded = false;
	}

	/* Gets the piece at position (x, y) on the board, or null 
	 * if there is no piece. If (x, y) are out of bounds, returns null. */
	public Piece pieceAt(int x, int y){
		if (outOfBounds(x, y)){
			return null;
		}
		return grid[x][y];
	}

	/* returns true if position is out of bounds */
	private boolean outOfBounds(int x, int y){
		if (x < 0 || y < 0 || x > SIDE_LENGTH-1 || y > SIDE_LENGTH-1){
			return true;
		}
		return false;
	}

	/* Returns true if the square at (x, y) can be selected.
	 * A piece may be selected if it is the corresponding player’s 
	 * turn and one of the following is true:
	 *     The player has not selected a piece yet.
	 *     The player has selected a piece, but did not move it.
	 * An empty square may be selected if one of the following is true:
	 *     During this turn, the player has selected a Piece which hasn’t 
	 *     moved yet and is selecting an empty spot which is a valid move 
	 *     for the previously selected Piece.
	 *     During this turn, the player has selected a Piece, captured, and 
	 *     has selected another valid capture destination. When performing 
	 *     multi-captures, you should only select the active piece once; all 
	 *     other selections should be valid destination points. */
	public boolean canSelect(int x, int y){
		if (hasExploded){
			return false;
		}
		Piece p = pieceAt(x, y);
		if (p != null && p.isFire() == fireTurn){
			if (selected == null || (selected != null && !hasMoved)){
				return true;
			}
		}else if (p == null){
			if (selected != null && validMove(selected_x, selected_y, x, y)){
				if (!hasMoved || (selected.hasCaptured() && !selected.isBomb())){
					return true;
				}
			}
		}
		return false;
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	 * or capture to (xf, yf), strictly from a geometry/piece-race point 
	 * of view. validMove does not need to take into consideration whose 
	 * turn it is or if a move has already been made, but rather can. 
	 * Update (2/6): validMove is not required, and will not be tested. 
	 * You may implement this however you want. It is suggested you use 
	 * this method to simplify your implementation of canSelect. */
	private boolean validMove(int xi, int yi, int xf, int yf){
		int xdist = Math.abs(xf - xi);
		int ydist;
		if (fireTurn){
			ydist = yf - yi;
		}else{
			ydist = yi - yf;
		}if (pieceAt(xi, yi).isKing()){
			ydist = Math.abs(yf - yi); //can move backwards
		} 

		if (xdist == 1 && ydist == 1 && !selected.hasCaptured()){
			return true;
		}else if (xdist == 2 && ydist == 2){
			int oppX = (xi+xf)/2;
			int oppY = (yi+yf)/2;
			Piece opp = pieceAt(oppX, oppY);
			if (opp != null && opp.isFire() != selected.isFire()){
				return true;
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
		if (p != null){
			selected = p;
			selected_x = x;
			selected_y = y;
			return;
		}
		if (selected != null){
			selected.move(x, y);
		}
	}

	/* Places p at (x, y). If (x, y) is out of bounds, does nothing. If 
	 * another piece already exists at (x, y), p will replace that piece. 
	 * (This method is potentially useful for creating specific test 
	 * circumstances.) */
	public void place(Piece p, int x, int y){
		if (outOfBounds(x, y)){
			return;
		}
		if (pieceAt(x, y) != null){
			remove(x, y);
		}

		grid[x][y] = p;

		if (selected != null){
			hasMoved = true;
		}
		if (!hasMoved){
			return;
		}

		if (p.isBomb() && p.hasCaptured()){
			selected_x = -1;
			selected_y = -1;
			selected.doneCapturing();
			selected = null;
			hasExploded = true;
		}else{
			selected_x = x;
			selected_y = y;
		}
	}

	/* Executes a remove. Returns the piece that was removed. If the 
	 * input (x, y) is out of bounds, returns null and prints an 
	 * appropriate message. If there is no piece at (x, y), returns null 
	 * and prints an appropriate message. */
	public Piece remove(int x, int y){
		if (outOfBounds(x, y)){
			System.out.println("(" + x + ", " + y + ") is out of bounds.");
			return null;
		}
		if (pieceAt(x, y) == null){
			System.out.println("No piece at (" + x + ", " + y + ")");
			return null;
		}
		Piece removed = pieceAt(x, y);
		grid[x][y] = null;
		return removed;
	}

	/* Returns whether or not the the current player can end their turn. 
	 * To be able to end a turn, a piece must have moved or performed a 
	 * capture. */
	public boolean canEndTurn(){
		if ((selected != null && selected.hasCaptured()) || hasMoved){
			return true;
		}
		return false;
	}

	/* Called at the end of each turn. Handles switching of players and anything 
	 * else that should happen at the end of a turn. */
	public void endTurn(){
		if (selected != null){
			selected.doneCapturing();
		}
		selected = null;
		selected_x = -1;
		selected_y = -1;
		hasMoved = false;
		fireTurn = !fireTurn;
		hasExploded = false;
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie / 
	 * no pieces on the board), or null if the game is not yet over. Assume 
	 * there is no stalemate situation in which the current player has 
	 * pieces but cannot legally move any of them (In this event, just 
	 * leave it at null). Determine the winner solely by the number of pieces 
	 * belonging to each team. */
	public String winner(){
		int numFire = 0;
		int numWater = 0;
		for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
            	Piece p = pieceAt(col, row);
            	if (p != null){
            		if (p.isFire()){
            			numFire++;
            		}else{
            			numWater++;
            		}
            	}
            }
        }
        if (numFire == 0 && numWater == 0){
        	return "No one";
        }else if (numFire == 0){
        	return "Water";
        }else if (numWater == 0){
        	return "Fire";
        }else {
        	return null;
        }
	}

}
