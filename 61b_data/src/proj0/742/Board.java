public class Board {
	private Piece[][] pieces;
	private int size = 8;
	private boolean fireTurn;
	private Piece selected = null;
	private boolean moved = false;
	private int selectedX;
	private int selectedY;


	/* Constructor of Board object. 
	 * If shouldBeEmpty is true, initializes an empty Board.
	 * Otherwise, initializes a Board with the default configuration.
	 */
	public Board(boolean shouldBeEmpty) {
		this.pieces = new Piece[this.size][this.size];
		this.fireTurn = true;
		this.selectedX = -1;
		this.selectedY = -1;
		if (!shouldBeEmpty) {
			this.pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
			this.pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
			this.pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
			this.pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

			this.pieces[1][1] = new Piece(true, this, 1, 1, "shield");
			this.pieces[3][1] = new Piece(true, this, 3, 1, "shield");
			this.pieces[5][1] = new Piece(true, this, 5, 1, "shield");
			this.pieces[7][1] = new Piece(true, this, 7, 1, "shield");

			this.pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
			this.pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
			this.pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
			this.pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

			this.pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
			this.pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
			this.pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
			this.pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

			this.pieces[0][6] = new Piece(false, this, 0, 6, "shield");
			this.pieces[2][6] = new Piece(false, this, 2, 6, "shield");
			this.pieces[4][6] = new Piece(false, this, 4, 6, "shield");
			this.pieces[6][6] = new Piece(false, this, 6, 6, "shield");

			this.pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
			this.pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
			this.pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
			this.pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		}
	}

	/* Gets the piece at position (x, y) on the board, or null if there 
	 * is no piece. If (x, y) are out of bounds, returns null
	 */
	public Piece pieceAt(int x, int y) {
		if (x >= size || y >= size)
			return null;
		return this.pieces[x][y];
	}

	/* Returns true if there is a piece the piece at (x, y) and it can be selected
	 */
	public boolean canSelect(int x, int y) { 
		if (x >= size || y >=size)
			return false;
		if (this.moved) {
			if (this.selected.hasCaptured() && this.validMove(x, y))
				return true;
			else
				return false;
		}
		else {
			if (this.selected == null && this.pieces[x][y] != null) { // select piece to move
				if (this.pieces[x][y].isFire() == this.fireTurn)
					return true;
			} else if (this.selected != null && this.pieces[x][y] == null) {// ready to do the move
				if (this.validMove(x, y))
					return true;
			} else if (this.selected != null && this.pieces[x][y] != null) {
				if (this.pieces[x][y].isFire() == this.fireTurn) // for changing piece to move
					return true;
			}
		}
		return false;
	}
	/* check if the selected piece can move to x,y position
	 */
	// Tested
	private boolean validMove(int x, int y) {
		if (pieces[x][y] != null)
			return false;
		if (this.moved && this.selected.hasCaptured())
			return this.validMoveToCapture(x, y);
		else {
			if ((y == this.selectedY + 1 || y == this.selectedY - 1) && (x == this.selectedX + 1 || x == this.selectedX - 1)) {
				if (this.selected.isKing()) {
					return true;
				} else {
					if (this.selected.isFire() && y == this.selectedY + 1)
						return true;
					if (!this.selected.isFire() && y == this.selectedY - 1)
						return true;
				}
			} else if (this.validMoveToCapture(x, y))
				return true;
		}
		return false;
		
	}

	private boolean validMoveToCapture(int x, int y) {
		if ((y == this.selectedY + 2 || y == this.selectedY - 2) && (x == this.selectedX + 2 || x == this.selectedX - 2)) {
			Piece in_between_piece = this.pieces[(x + this.selectedX) / 2][(y + this.selectedY) / 2];
			if (in_between_piece != null && in_between_piece.isFire() != this.selected.isFire())
				if (this.selected.isKing())
					return true; // this is the case that can capture
				else {
					if (this.selected.isFire() && y == this.selectedY + 2)
					return true;
					if (!this.selected.isFire() && y == this.selectedY - 2)
					return true;
			    }
		}
		return false;
	}

	public void select(int x, int y) {
		if (this.selected == null) {
			this.selected = this.pieces[x][y];
		} else if (this.pieces[x][y] != null) {
			this.selected = this.pieces[x][y]; // changing piece to select
		} else {
			this.selected.move(x, y);
			this.moved = true;	
		}
		this.selectedX = x;
		this.selectedY = y;
		
		

	}

	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.
	 */
	public void place(Piece p, int x, int y) {
		if (x >= size || y >= size || p == null)
			return;
		for (int i=0; i<size; i+=1)
			for (int h=0; h<size; h+=1) 
				if (pieces[i][h] == p)
					pieces[i][h] = null;
			
		pieces[x][y] = p;
	}

	/* Executes a remove. Returns the piece that was removed.
	 */
    public Piece remove(int x, int y) {
    	if (x >= size || y >= size) {
    		System.out.println("Error: out of boundary.");
    		return null;
    	}
    	if (pieces[x][y] == null) {
    		System.out.println("Error: there is no piece to remove.");
    		return null;
    	}
    	Piece removed = pieces[x][y];
    	pieces[x][y] = null;
    	return removed;
    }

    /* Returns whether or not the the current player can end their turn.
     * To be able to end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn() {
    	return this.moved;
    }

    public void endTurn() {
    	if (this.fireTurn)
    		this.fireTurn = false;
    	else
    		this.fireTurn = true;

    	this.selected.doneCapturing();
    	this.selected = null;
    	this.selectedX = -1;
    	this.selectedY = -1;
    	this.moved = false;
    }

    public String winner() {
    	boolean hasFire = false;
    	boolean hasWater = false;

    	for (int i = 0; i < this.size; i += 1)
    		for (int h = 0; h < this.size; h += 1)
    			if (this.pieces[i][h] != null)
    				if (this.pieces[i][h].isFire())
    					hasFire = true;
    				else
    					hasWater = true;

    	if (hasFire && hasWater)
    		return null;
    	else if (hasFire && !hasWater)
    		return "Fire";
    	else if (!hasFire && hasWater)
    		return "Water";
    	else
    		return "No one";
    }


    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (this.selected != null && this.selected == this.pieces[i][j])
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	else {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isKing()) {
                    	if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == true)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    	else if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == false)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    	else if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == true)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    	else if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == false)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    	else {
                    		if (pieces[i][j].isFire() == true)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    		else if (pieces[i][j].isFire() == false)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    	}
                	} else {
                		if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == true)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    	else if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == false)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    	else if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == true)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    	else if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == false)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    	else {
                    		if (pieces[i][j].isFire() == true)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    		else if (pieces[i][j].isFire() == false)
                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    	}
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
        	
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) 
                	b.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		System.out.println(b.fireTurn);
            		b.endTurn();
            		System.out.println(b.winner());
            	}
            }
            StdDrawPlus.show(100);
        }
    }







}