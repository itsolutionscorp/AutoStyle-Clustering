public class Board {
	private static int N = 8;

	private boolean FireTurn = true;
	private boolean selected = false;
	private Piece selectedPiece;
	private int selectedx;
	private int selectedy;
	private boolean moved = false;

	//starts a GUI supported version of the game
	public static void main (String args[]) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
            	int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            //if clicked, but stay white until another valid square quicked          
            StdDrawPlus.show(100);
        }
	}

	/* The constructor for Board. If shouldBeEmpty is true, initializes 
	*an empty Board. Otherwise, initializes a Board with the default 
	*configuration. Note that an empty Board could possibly be useful for 
	*testing purposes. */
	
	private Piece[][] pieces = new Piece[N][N];

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N]; 
		if (!shouldBeEmpty) {
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
			pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
			pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
			pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
			pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
			pieces[0][6] = new Piece(false, this, 0, 6, "shield");
			pieces[2][6] = new Piece(false, this, 2, 6, "shield");
			pieces[4][6] = new Piece(false, this, 4, 6, "shield");
			pieces[6][6] = new Piece(false, this, 6, 6, "shield");
			pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
			pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
			pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
			pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		}

	}

    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);                
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
          
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected && i == selectedx && j == selectedy) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		if (pieces[i][j].isBomb()) { 
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); 
                	 	}
                		else if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1); 
                			}
                			else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1); 
                		}
        
                		else {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                	}

                	//water pieces
                	else {
                		if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		}

                		else if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}

                		else {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                			else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                }
	            }
	        }
		}
	}

	// /* Gets the piece at position (x, y) on the board, or null if there is 
	// *no piece. If (x, y) are out of bounds, returns null.*/
	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	/* Returns true if the square at (x, y) can be selected.
	*A square with a piece may be selected if it is the corresponding player’s 
	*turn and one of the following is true:
		*-The player has not selected a piece yet.
		*-The player has selected a piece, but did not move it.
	*An empty square may be selected if one of the following is true:
		*-During this turn, the player has selected a Piece which hasn’t moved 
		*yet and is selecting an empty spot which is a valid move for the 
		*previously selected Piece.
		*-During this turn, the player has selected a Piece, captured, and has 
		*selected another valid capture destination. When performing 
		*multi-captures, you should only select the active piece once; all 
		*other selections should be valid destination points. */
	public boolean canSelect(int x, int y) {
		if (winner() != null) return false;
		Piece p = pieceAt(x, y);
		if (p != null) { //piece on x, y
			if (p.isFire() == FireTurn) {
				if (!selected || selected && !moved) return true;
			}
		}

		//empty square selection
		else {
			if (selected && !moved && validMove(selectedx, selectedy, x, y)) {
				return true;
			}
			if (selected && selectedPiece.hasCaptured() && validMove(selectedx, selectedy, x, y)) {
				return true;
			}
		}
		return false;
	}


	private boolean validMove(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		Piece p = pieceAt(x1, y1);
		if (p == null || pieceAt(x2, y2) != null) return false; //if piece at x, y
		if (!p.isKing() && !p.isFire() && dy > 0) return false;
		if (!p.isKing() && p.isFire() && dy < 0) return false;
		if (Math.abs(dx) == 1 && Math.abs(dy) == 1) return true; //move normally w/o capture
		if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
			Piece capturedPiece = pieceAt((x1 + x2) / 2, (y1 + y2) /2);
			if (capturedPiece != null && capturedPiece.isFire() != p.isFire()) {
				return true;
			}
		}
		return false;
	}

	/* Selects the square at (x, y). This method assumes canSelect (x,y) 
	*returns true. Optionally, it is recommended to color the background of 
	*the selected square white on the GUI via the pen color function. For any 
	*piece to perform a capture, that piece must have been selected first. If 
	*you select a square with a piece, you are prepping that piece for movement. 
	*If you select an empty square (assuming canSelect returns true), you should 
	*move your most recently selected piece to that square.*/
	public void select(int x, int y) {
		selectedx = x;
		selectedy = y;
		if (pieceAt(x, y) != null) { //piece at square
			selected = true;
			selectedPiece = pieceAt(x, y);
		}
		else { //empty square
			if (selected) {
				selectedPiece.move(x, y);
				if (pieceAt(x, y) == null) selected = false;
				moved = true;
			}
		}
	}

	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
	*nothing. If p already exists in the current Board, first removes it from 
	*its initial position. If another piece already exists at (x, y), p will 
	*replace that piece. (This method is potentially useful for creating 
	*specific test circumstances.)*/
	public void place(Piece p, int x, int y) {
		if (x >= N || x < 0 || y >= N || y < 0 || p == null) {
			return;
		}
		for (int i = 0; i < N; i++) { //finding x and y of p to remove it
			for (int j = 0; j < N; j++) {
				if (pieces[i][j] == p) {
					remove(i, j);
				}
			}
		}
		pieces[x][y] = p;
	}


	/* Executes a remove. Returns the piece that was removed. If the input 
	*(x, y) is out of bounds, returns null and prints an appropriate message. 
	*If there is no piece at (x, y), returns null and prints an appropriate 
	*message.*/
	public Piece remove(int x, int y) {
		if (pieceAt(x, y) != null) {
			Piece p = pieces[x][y];
			pieces[x][y] = null;
			return p;
		}
		System.out.println("Input is out of bounds or there is no piece at (x, y)");
		return null;
	}

	/* Returns whether or not the the current player can end their turn. 
	*To be able to end a turn, a piece must have moved or performed a capture.*/
	public boolean canEndTurn() {
		return moved;
	}


	/* Called at the end of each turn. Handles switching of players and anything 
	*else that should happen at the end of a turn. */

	public void endTurn() {
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selected = false;
		moved = false;
		FireTurn = !FireTurn;
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces 
	*on the board), or null if the game is not yet over. Assume there is no 
	*stalemate situation in which the current player has pieces but cannot 
	*legally move any of them (In this event, just leave it at null). Determine 
	*the winner solely by the number of pieces belonging to each team.*/
	public String winner() {
		int fireAmount = 0;
		int waterAmount = 0;
		for (Piece[] x: pieces) {
			for (Piece p: x) {
				if (p != null && p.isFire()) fireAmount++;
				else if (p!= null && !p.isFire()) waterAmount++;
			}
		}
		if (fireAmount == 0 && waterAmount > 0) return "Water";
		if (waterAmount == 0 && fireAmount > 0) return "Fire";
		if (fireAmount == 0 && waterAmount == 0) return "No one";
		return null;
	}
}

