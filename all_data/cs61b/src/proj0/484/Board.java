public class Board {
	private Piece[][] pieces = new Piece[8][8]; 
	private Piece selected = null;
	private boolean isFireTurn = true;
	private boolean hasActed = false;
	public int leftFire = 0, leftWater = 0;
	private int selected_x, selected_y;


	public static void main (String[] arg) {
	/**starts a GUI supported version of the game.*/
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);	
		while(true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y))
                	b.select(x,y);
            }
			b.drawBoard();

            if (StdDrawPlus.isSpacePressed() && b.canEndTurn())
            	b.endTurn(); 
            StdDrawPlus.show(10);
    	}

    }

    private boolean inRange(int x, int y){
		return(x<=7 && x>=0 && y<=7 && y>=0);
    }
    private void rowInitialization(int row_num, String type) {
		for (int i=0;i<8;i++) {
			if ((i + row_num) % 2 == 0) {
				pieces[i][row_num] = new Piece((row_num<4), this, i, row_num, type);
			}
		}
    }
	private void hasActed(){
		hasActed = true;
	}

	private void explosion(int xf, int yf) {
		for (int i=xf-1;i<=xf+1;i++){
			for (int j=yf-1;j<=yf+1;j++){
				if (inRange(i,j)){
					Piece pt = pieceAt(i,j);
					if (pt != null && !pt.isShield())
						remove(i,j);
				}
			}
		}
		selected = null;	
	}


	public Board(boolean shouldBeEmpty) {
	/** The constructor for Board. If shouldBeEmpty is true,
	  *initializes an empty Board. Otherwise, initializes a Board with 
	  *the default configuration. Note that an empty Board could possibly
	  *be useful for testing purposes.*/
		if (!shouldBeEmpty){
			rowInitialization(0, "pawn");
			rowInitialization(1, "shield");
			rowInitialization(2, "bomb");
			rowInitialization(7, "pawn");
			rowInitialization(6, "shield");
			rowInitialization(5, "bomb");
		}
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
				Piece p = pieceAt(i, j);	
       			if (p != null){
       				if(p.isFire())
       					leftFire += 1;
       				else
       					leftWater += 1;
	       		}
        	}
    	}    	
	}

	private void drawPiece(int x, int y) {
       	Piece p = pieceAt(x, y);	
       	if (p != null){
       		String type = "pawn";
       		if (p.isShield()) type = "shield";
       		if (p.isBomb()) type = "bomb";
        	String group = "water";
        	if (p.isFire()) group = "fire";
        	String isCrowned = "";
        	if (p.isKing()) isCrowned = "-crowned";
        	StdDrawPlus.picture(x + .5, y + .5, "img/"+type+"-"+group+isCrowned+".png", 1, 1);
		}
	}

	private void drawBoard() {		
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				if(selected != null && i == selected_x && j == selected_y)
											StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0)  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  		StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                drawPiece(i, j);
            }
            
        }
    }

    private boolean isOutOfBound(int x, int y) {
    	return !(x<=7 && x>=0 && y<=7 && y>=0);
    }
	private boolean isOccupied(int x, int y) {
		return (pieces[x][y] != null);
	}
	public Piece pieceAt(int x, int y) {
	/**Gets the piece at position (x, y) on the board, 
	  *or null if there is no piece.
	  *If (x, y) are out of bounds, returns null.*/
		if ((isOutOfBound(x,y))||(!isOccupied(x, y)))
			return null;
		return pieces[x][y];
	}

	private int abs(int x){
		return Math.abs(x);
	}
	private boolean isInCaptureRange(Piece p, int x, int y) {
		int dif_x = x - selected_x;
		int dif_y = y - selected_y;
		if ((abs(dif_x) != 2) || (abs(dif_y)!= 2))
			return false;
		int mid_x = (int)(x - selected_x)/2 + selected_x;
		int mid_y = (int)(y - selected_y)/2 + selected_y;
		Piece pm = pieceAt(mid_x,mid_y);
		Piece pf = pieceAt(x,y);
		if (pm == null || pf != null || (pm.side() == p.side()))
			return false;
		if (p.isKing()) 
			return true;
		if (p.isFire()) { 
			if (dif_y == 2)
				return true;
		}

		if (!p.isFire()) {
			if (dif_y == -2)
				return true;
		}
		return false;

	}

	private boolean isInMoveRange(Piece p, int x, int y){
		int dif_x = x - selected_x;
		int dif_y = y - selected_y;
		if ((abs(dif_x) != 1) || (abs(dif_y)!= 1))
			return false;
		Piece pf = pieceAt(x,y);
		if (pf != null)
			return false;
		if (p.isKing())
			return true;
		if (p.isFire()) {
			if(dif_y == 1)
				return true;
		}
		if (!p.isFire()){ 
			if(dif_y == -1)
				return true;	
		}
		return false;
	}
	// public boolean validMove(int xi, int yi, int xf, int yf) {
	// *Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	//   *or capture to (xf, yf), strictly from a geometry/piece-race point of view.
	//   *validMove does not need to take into consideration whose turn it is 
	//   *or if a move has already been made, but rather can.
	// Piece p = pieceAt(xi, yi);
	// if (p.moved && !p.captured)
	// 	return false;
	// return isInMoveRange(p, xf, yf) || isInCaptureRange(p, xf, yf);
	// }

	private boolean canSelectPiece(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null)
			return false;
		return p.isFire() == isFireTurn && !hasActed;
	}
	private boolean hasMoved(Piece p){
		if(hasActed && !p.hasCaptured())
			return true;
		return false;
	}
	private boolean canSelectPlace(int x, int y) {
		if (selected != null && (!hasMoved(selected))) {
			if (selected.hasCaptured())
				return isInCaptureRange(selected, x, y);
			else
				return isInMoveRange(selected, x, y) || isInCaptureRange(selected, x, y);
		}
		return false;
	}
	public boolean canSelect(int x, int y) {
	/**Returns true if the square at (x, y) can be selected.*/
		if (!inRange(x, y))
			return false;
		Piece p = pieceAt(x, y);
		if (p == null)
			return canSelectPlace(x, y);
		else
			return canSelectPiece(x, y);
	}


	public void select(int x, int y) {
	/**Selects the piece at (x, y) if possible. 
	  *Optionally, it is recommended to color the background of the selected square
	  *white on the GUI via the pen color function. For any piece to perform a capture,
	  *that piece must have been selected first.*/	
		Piece p = pieceAt(x, y);
		selected_x = x;
		selected_y = y;
		if (p!= null)
			selected = p;
		else{
    		selected.move(x,y);
    		hasActed();
    		if(selected.isBomb() && selected.hasCaptured())
    			explosion(x,y);
    	}
	}

	public void place(Piece p, int x, int y) {
	/**Places p at (x, y). If (x, y) is out of bounds, does nothing.
	  *If another piece already exists at (x, y), p will replace that piece.
	  *(This method is potentially useful for creating specific test circumstances.)*/
		if (isOutOfBound(x, y))
			return;
		pieces[x][y] = p;
		if(p.isFire())
			leftFire += 1;
		else
			leftWater += 1;
	}

	public Piece remove(int x, int y) {
	/**Executes a remove. Returns the piece that was removed.
	  *If the input (x, y) is out of bounds, returns null and
	  *prints an appropriate message. If there is no piece at (x, y),
	  *returns null and prints an appropriate message.*/
		if(isOutOfBound(x,y)){
			System.out.println("The intput place is out of bound.");
			return null;
		}
		
		if(!isOccupied(x,y)) {
			System.out.println("The intput place is empty.");
			return null;
		}
		Piece p = pieceAt(x, y);
		if (p.isFire())
			leftFire -= 1;
		else
			leftWater -= 1;
		pieces[x][y] = null;
		return p;

	}

	public boolean canEndTurn() {
	/**Returns whether or not the the current player can end their turn. 
	  *To be able to end a turn, a piece must have moved or performed a capture.*/
		return hasActed;
	}

	public void endTurn() {
	/**Called at the end of each turn. Handles switching of players 
	  *and anything else that should happen at the end of a turn.*/
		hasActed = false;
		isFireTurn = !isFireTurn;
		if (selected != null)
			selected.doneCapturing();
			selected = null;
        if (winner() != null){
        	return;
        }
	}

	public String winner() {
	/**Returns the winner of the game. "Fire", "Water", "No one" 
	  *(tie / no pieces on the board), or null if the game is not yet over.
	  *Assume there is no stalemate situation in which the current player has pieces 
	  *but cannot legally move any of them (In this event, just leave it at null).
	  * Determine the winner solely by the number of pieces belonging to each team.*/

	if (leftWater == 0 && leftFire == 0) 
		return "No one";
	if (leftWater == 0)
		return "Fire";
	if (leftFire == 0)
		return "Water";
	return null;
	}

}