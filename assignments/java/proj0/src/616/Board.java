public class Board {
	private Piece[][] pieces;
	private static int N = 8; //size of board
	private boolean isFireTurn;
	private Piece selected_p;
	private int selected_p_x;
	private int selected_p_y;
	private boolean moved_selected_p;

	/** pieces[i][j] --> (i, j)
	*	(0, 0) to (7, 7)
	*	A piece on (0,0) lies on the bottom left corner. 
	*	A piece on (7, 7) lies on the top right corner.
	*/

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		this.isFireTurn = true;
		//intializes a Board with the default config if not supposed to be empty
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i += 1) {
	            for (int j = 0; j < N; j += 1) {
	                if ((i + j) % 2 == 0) {
	                	//drawing the fire side
	                	if (j == 0) {
	                		pieces[i][j] = new Piece(true, this, i ,j, "pawn");
	                	}
	                	else if (j == 1) {
	                		pieces[i][j] = new Piece(true, this, i, j, "shield");
	                	}
	                	else if (j == 2) {
	                		pieces[i][j] = new Piece(true, this, i, j, "bomb");
	                	}
	                	//drawing the water side
	                	if (j == 7) {
	                		pieces[i][j] = new Piece(false, this, i ,j, "pawn");
	                	}
	                	else if (j == 6) {
	                		pieces[i][j] = new Piece(false, this, i, j, "shield");
	                	}
	                	else if (j == 5) {
	                		pieces[i][j] = new Piece(false, this, i, j, "bomb");
	                	}
					}
	            }
	        }
		}
	}

	//retruns true when (x, y) is out of bounds
	private boolean outOfBounds(int x, int y) {
		if (x >= N || y >= N) {
			return true;
		}
		return false;
	}
	
	/** Gets the piece at position (x, y) on the board, 
	*	or null if there is no piece. If (x, y) are out of bounds, returns null.
	*/
	public Piece pieceAt(int x, int y) {
		if (this.outOfBounds(x, y)) {
			return null;		
		}
		return this.pieces[x][y];
	}

	/** Returns true if there is a piece the piece at (x, y) and it can be selected
	*/
	public boolean canSelect(int x, int y) {
		//checks out of bounds
		if (this.outOfBounds(x, y)) {
			return false;	
		}
		Piece p = this.pieceAt(x,y);
		//not an empty square
		if (p != null) {
			//checks it's the right player's turn
			if ((p.isFire() && this.isFireTurn) || (!p.isFire() && !this.isFireTurn)) {
				//have not selected a piece yet
				if (this.selected_p == null) {
					return true;
				}
				//selected a piece but did not move it.
				else if (!this.moved_selected_p) {
					return true;
				}
			}	
		}
		//empty square & has selected Piece to choose empty
		else if (this.selected_p != null) {
			//if the selected piece has not moved yet 
			if (!this.moved_selected_p) {
				return validMove(this.selected_p, this.selected_p_x, this.selected_p_y, x, y); //figure out how to call xi and yi
			}
			//multi-capture check with hasCaptured()
			else if (selected_p.hasCaptured()) {
				return validMove(this.selected_p, this.selected_p_x, this.selected_p_y, x, y); //figure out how to get location
			} 
		}
		return false;
	}

	/** Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture 
	*	to (xf, yf), strictly from a geometry/piece-race point of view. validMove 
	*	does not need to take into consideration whose turn it is or if 
	*	a move has already been made, but rather can.
	*/
	private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
		//cannot select red square
		if (((xi + yi) % 2 != 0) || ((xf + yf) % 2 != 0)) {
			return false;
		}
		//checks whether the up/down direction is correct for the PIECE.
		if (!p.isKing()) {
			//fire has to move up
			if (p.isFire() && yf - yi < 0) {
				return false;
			}
			//water has to move down.
			else if (!p.isFire() && yf - yi > 0) {
				return false; 
			}
		}
		//if the piece has already captured, you cannot do single moves only captures.
		if (p.hasCaptured()) {
			if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1) {
				return false;
			}
		}
		//restrict movement to diagonal
		if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1) {
			return true;
		}
		//restrict skip one if there is one between.
		if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2) {
			int between_x = (int)((xf + xi)/2);
			int between_y = (int)((yf + yi)/2);
			Piece between_p = this.pieceAt(between_x, between_y);
			//check if there is a piece in between and is the opposite team as PIECE p;
			if (between_p != null && (p.isFire() != between_p.isFire())) {
				return true;
			}
		}
		return false;
	}

	/** Selects the piece at (x, y) if possible. Optionally, it is recommended to 
	*	color the background of the selected square white on the GUI via the pen color 
	*	function. For any piece to perform a capture, that piece must have been selected first.
	*/
	public void select(int x, int y) {
		//Selected square is empty, so the piece must move.
		//check for capturing.
		if (this.pieceAt(x, y) == null) {
			this.selected_p.move(x, y);
			this.selected_p_x = x;
			this.selected_p_y = y;
			this.moved_selected_p = true;			
		}
		//Select a piece.
		else {
			this.selected_p = this.pieceAt(x,y);
			this.selected_p_x = x;
			this.selected_p_y = y;
			this.moved_selected_p = false;
			}
	}

	/** Places PIECE P at (x, y). If (x, y) is out of bounds, does nothing. 
	*	If another piece already exists at (x, y), p will replace that piece. 
	*	(This method is potentially useful for creating specific test circumstances.)
	*/
	public void place(Piece p, int x, int y) {
		//out of bounds does nothing
		if (this.outOfBounds(x, y)) {
		}
		//replace a piece or null
		else {
			this.pieces[x][y] = p;
		}
	}

	/** Executes a remove. Returns the piece that was removed. If the input (x, y) is out of 
	*	bounds, returns null and prints an appropriate message. If there is no piece 
	*	at (x, y), returns null and prints an appropriate message.
	*/
	public Piece remove(int x, int y) {
		//check for out of bounds
		if (this.outOfBounds(x, y)) {
			System.out.println("Cannot remove piece from board when x or y are out of bounds: (" + x + ", " + y + ").");
			return null;
		}

		Piece removed = this.pieceAt(x,y);
		//check if (x, y) is empty
		if (removed == null) {
			System.out.println("Cannot remove a piece when (x, y) is empty: (" + x + ", " + y + ").");
			return null;
		}
		else {
			this.pieces[x][y] = null;
			return removed;
		}
		
	}

	/** Returns whether or not the the current player can end their turn. 
	*	To be able to end a turn, a piece must have moved or performed a capture.
	*/
	public boolean canEndTurn() {
		if (this.moved_selected_p) {
			return true;
		}
		return false;
	}

	/** Called at the end of each turn. Handles switching of players and 
	*	anything else that should happen at the end of a turn.
	*/
	public void endTurn() {
		if (this.canEndTurn()) {
			this.isFireTurn = !this.isFireTurn;
			this.selected_p.doneCapturing();
			this.selected_p = null;
			this.selected_p_x = 8; // make error out if called without a selected_p
			this.selected_p_y = 8;
			this.moved_selected_p = false;
		}
	}

	/** Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
	*	or null if the game is not yet over. Assume there is no stalemate situation 
	*	in which the current player has pieces but cannot legally move any of them 
	*	(In this event, just leave it at null). Determine the winner solely by the 
	*	number of pieces belonging to each team.
	*/
	public String winner() {
		if (this.fireNumber() == 0 && this.waterNumber() == 0) {
			return "No one";
		}
		else if (this.waterNumber() == 0) {
			return "Fire";
		}
		else if (this.fireNumber() == 0) {
			return "Water";
		}
		return null;
	}

	//Number of fire pieces on the board
	private int fireNumber() {
		int sum = 0;
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				Piece p = this.pieceAt(i, j);
				if (p != null && p.isFire()) {
					sum += 1;
				}
			}
		}
		return sum;
	}

	//number of water piece on board
	//redundant
	private int waterNumber() {
		int sum = 0;
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				Piece p = this.pieceAt(i, j);
				if (p != null && !p.isFire()) {
					sum += 1;
				}
			}
		}
		return sum;
	}

	//starts a GUI supported version of the game.
	public static void main (String args[]) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

		while (b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y);	
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	b.endTurn();
            }    
            StdDrawPlus.show(25);
        }
        System.out.println(b.winner());
	}

	/** Gets the image name as string for given PIECE. Checks for type, side, crowned.
	*/
	private String getImageType(Piece p) {
		String img = "img/";
		StringBuilder sb_img = new StringBuilder(img);
		//getting type
		if (p.isShield()) {
			sb_img.append("shield");
		}
		else if (p.isBomb()) {
			sb_img.append("bomb");
		}
		else {
			sb_img.append("pawn");
		}
		//getting side
		if (p.isFire()) {
			sb_img.append("-fire");
		}
		else {
			sb_img.append("-water");
		}
		//checking if King
		if (p.isKing()) {
			sb_img.append("-crowned");
		}
		sb_img.append(".png");
		return sb_img.toString();
	}

	/** draws the board. Adapted from StdDrawDemo.java
	*/
    private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
            	Piece p = this.pieceAt(i, j);
                if ((i + j) % 2 == 0) {
                	if (p != null && p == this.selected_p) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                	else{
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (p != null) {
					StdDrawPlus.picture(i + .5, j + .5, this.getImageType(p), 1, 1);
				}
            }
        }
    }	
}





















