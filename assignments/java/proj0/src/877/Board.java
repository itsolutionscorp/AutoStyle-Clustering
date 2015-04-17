public class Board {

	private Piece[][] pieces;
	//private static Board b;
	private boolean selected;
	private boolean hasMoved;
	private Piece activePiece;
	private boolean fireTurn;
	private int xpos;
	private int ypos;



	/*starts a GUI supported version of the game.*/
	public static void main (String[] args) {

        int N = 8;
        double x;
        double y;
        Piece a;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
 		Board b = new Board(false);

        while(true) { // runs the game
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            for (int i = 0; i < N; i++) {
        		for (int j = 0; j < N; j++) {
            		if (b.pieceAt(j,i) != null) {
            			a = b.pieceAt(j,i);
            			/*if (b.activePiece == a) {
            				StdDrawPlus.filledSquare(j + .5, i + .5, .5);
            			}*/
                    	StdDrawPlus.picture(j + .5, i + .5, b.piecetype(a), 1, 1);
                    }
                    else if((i + j) % 2 == 0) {
                    	StdDrawPlus.filledSquare(j + .5, i + .5, .5);
                    }
                    /*else if ((i + j) % 2 == 0) {
        				StdDrawPlus.filledSquare(j + .5, i + .5, .5);	
                    }*/
                }
                if (StdDrawPlus.mousePressed()) {
                	x = StdDrawPlus.mouseX();
                	y = StdDrawPlus.mouseY();
                	if (b.canSelect((int)x,(int)y)) {
                		b.select((int)x,(int)y);
                	}

            	}
            }
            StdDrawPlus.show(100);
        }
	}

	/*The constructor for Board. If shouldBeEmpty 
	is true, initializes an empty Board. Otherwise, 
	initializes a Board with the default configuration. 
	Note that an empty Board could possibly be useful 
	for testing purposes. */
	public Board(boolean shouldBeEmpty) {

		int N = 8;
		StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
		StdDrawPlus.filledSquare(4, 4, 4.5);
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		pieces = new Piece[N][N];
		fireTurn = true;
		for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		if ((i + j) % 2 == 0) {
        			StdDrawPlus.filledSquare(j + .5, i + .5, .5);	
	        		if (!shouldBeEmpty) {
						if ( i == 0 && j % 2 == 0) {
							place(new Piece(true,this,j,i,"img/pawn-fire-crowned.png"),j,i);
						}
						if ( i == 1 && j % 2 == 1) {
							place(new Piece(true,this,j,i,"img/shield-fire.png"),j,i);
						}
						if ( i == 2 && j % 2 == 0) {
							place(new Piece(true,this,j,i,"img/bomb-fire.png"),j,i);
						}
						if ( i == 4 && j % 2 == 0) {
							place(new Piece(false,this,j,i,"img/bomb-water.png"),j,i);
						}
						if ( i == 6 && j % 2 == 0) {
							place(new Piece(false,this,j,i,"img/shield-water.png"),j,i);
						}
						if ( i == 7 && j % 2 == 1) {
							place(new Piece(false,this,j,i,"img/pawn-water.png"),j,i);
						}
					}
				}

        		if ((i + j) % 2 == 0) {
        			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        		}
        	}
        }
	}


	/* Gets the piece at position (x, y) on the board, 
	or null if there is no piece. If (x, y) are out of 
	bounds, returns null. */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		if (pieces[y][x] == null) {
			return null;
		}
		return pieces[y][x];
	}

	/* Returns true if the square at (x, y) can be selected.
	A square with a piece may be selected if it is the corresponding 
	player’s turn and one of the following is true:
		The player has not selected a piece yet.
		The player has selected a piece, but did not move it.
	An empty square may be selected if one of the following is true:
		During this turn, the player has selected a Piece which hasn’t
			moved yet and is selecting an empty spot which is a valid 
			move for the previously selected Piece.
		During this turn, the player has selected a Piece, captured, 
		and has selected another valid capture destination. When 
		performing multi-captures, you should only select the active 
		piece once; all other selections should be valid destination points.*/
	public boolean canSelect(int x, int y) {
		if (pieceAt(x,y) != null) {
			if (pieceAt(x,y).isFire() ^ fireTurn) {
				return false;
			}
			if (hasMoved && !selected) {
				return false;
			}
		} 
		else {
			if (!selected || hasMoved || !validMove(xpos,ypos,x,y)) {
				return false;
			}
		}
		return true;
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf)
	 or capture to (xf, yf), strictly from a geometry/piece-race point 
	 of view. validMove does not need to take into consideration whose 
	 turn it is or if a move has already been made, but rather can*/
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if(checkMove(activePiece.isFire(),xi,yi,xf,yf)) {
			return true;
		}
		if (activePiece.isKing()) {
			return checkMove(true,xi,yi,xf,yf) || checkMove(false,xi,yi,xf,yf);
		}
		return false;
	}

	/** purpose of checkMove is to see if the move for this piece is valid,
	 boolean true if its a fire piece or false if its a water piece**/
	private boolean checkMove(boolean isFire, int xi, int yi, int xf, int yf) {
		int mod;
		if (pieceAt(xf,yf) != null) {
			return false;
		}
		if (isFire) {
			mod = 1;
		}
		else {
			mod = -1;
		}
		if (yf - yi == mod && Math.abs(xi - xf) == 1) {
			return true;
		}
		if (yf - yi == mod * 2 && Math.abs(xi - xf) == 2) {
			int a = (xf + xi)/2;
			int b = (yf + yi)/2;
			if (pieceAt(a,b) != null) {
				return true;
			}
		}
		return false;

	}

	/* Selects the square at (x, y). This method assumes canSelect (x,y)
	 returns true. Optionally, it is recommended to color the background 
	 of the selected square white on the GUI via the pen color function.
	 For any piece to perform a capture, that piece must have been selected
	 first. If you select a square with a piece, you are prepping that piece
	 for movement. If you select an empty square (assuming canSelect returns true),
	 you should move your most recently selected piece to that square.*/
	public void select(int x, int y) {
		if (canSelect(x,y)) {
			if (activePiece != null) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(xpos + .5,ypos + .5,.5);
				StdDrawPlus.picture(xpos + .5, ypos + .5, piecetype(activePiece), 1, 1);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				StdDrawPlus.picture(x + .5, y + .5, piecetype(activePiece), 1, 1);
			}
			if(pieceAt(x,y) != null) {
				activePiece = pieces[y][x];
				xpos = x;
				ypos = y;
				selected = true;
			}
			else if (activePiece != null) {
				activePiece.move(x,y);
			}

		}
	}

	/* Places p at (x, y). If (x, y) is out of bounds or if p is null,
	 does nothing. If p already exists in the current Board, first 
	 removes it from its initial position. If another piece already 
	 exists at (x, y), p will replace that piece. (This method is 
	 potentially useful for creating specific test circumstances.)*/
	public void place(Piece p, int x, int y) {
		if (p == null) {
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (p == pieceAt(j,i)) {
					remove(j,i);
				}
			}
		} 
		pieces[y][x] = p;
	}

	/* Executes a remove. Returns the piece that was removed.
	If the input (x, y) is out of bounds, returns null and 
	prints an appropriate message. If there is no piece at (x, y),
	returns null and prints an appropriate message.*/
	public Piece remove(int x, int y) {
		Piece a = pieceAt(x,y);
		pieces[y][x] = null;
		return a;
	}

	/* Returns whether or not the the current player can end 
	their turn. To be able to end a turn, a piece must have 
	moved or performed a capture.*/
	public boolean canEndTurn() {
		return false;
	}

	/* Called at the end of each turn. Handles switching 
	of players and anything else that should happen at 
	the end of a turn.*/
	public void endTurn() {

	}

	/*  Returns the winner of the game. "Fire", "Water", 
	"No one" (tie / no pieces on the board), or null if 
	the game is not yet over. Assume there is no stalemate
	situation in which the current player has pieces but 
	cannot legally move any of them (In this event, just 
	leave it at null). Determine the winner solely by 
	the number of pieces belonging to each team.*/
	public String winner() {
		return "Yolo";
	}

	private String piecetype(Piece a) {
		String temp; 
		if (a.isFire()) {
			temp = "-fire";
		}
		else {
			temp = "-water";
		}
		if (a.isKing()) {
			temp = temp + "-crowned";
		}
		if (a.isBomb()) {
			temp = "bomb" + temp;
		}
		else if (a.isShield()) {
			temp = "shield" + temp;
		}
		else {
			temp = "pawn" + temp;
		}
		temp = "img/" + temp + ".png";
		return temp;
	}
}