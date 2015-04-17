public class Board {
	boolean shouldBeEmpty;
	int turn = 1; // 1:Fire -1:Water
	static boolean doneMove; //Whether or not a move has done
	static int N = 8; //How big is the board?
	static Piece[][] pieces; //Whether or not a piece is at [x][y]

	//Constructor for board
	public Board(int turn, boolean doneMove, boolean shouldBeEmpty) {
		this.turn = turn;
		this.doneMove = doneMove;
		this.shouldBeEmpty = shouldBeEmpty;
	}
	
	public static void initialBoard(boolean shouldBeEmpty) {
		if(shouldBeEmpty == true) {
			drawInitial(N); // could possibly be useful for testing purposes
		} else {
			drawDefault(N);
			setDefaultBoard();
		}	    
	}

	//Initializes an default board (from drawBoard(int N))
	private static void drawInitial(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	//Initializes an default board (from drawBoard(int N))
	private static void drawDefault(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if ( (i == 0) && (j % 2 == 1) ) {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				}
				if ( (i == 1) && (j % 2 == 0) ) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
				}
				if ( (i == 2) && (j % 2 == 1) ) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
				}
				if ( (i == 5) && (j % 2 == 0) ) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				}
				if ( (i == 6) && (j % 2 == 1) ) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
				}
				if ( (i == 2) && (j % 2 == 1) ) {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				}
			}
		}
	}
	
	//Set pieces
	private static void setDefaultBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ( (i == 0) && (j % 2 == 1) ) {
					pieces[i][j] = new Piece(false, null, i, j, null);
				}
				if ( (i == 1) && (j % 2 == 0) ) {
					pieces[i][j] = new Piece(false, null, i, j, "Shield");
				}
				if ( (i == 2) && (j % 2 == 1) ) {
					pieces[i][j] = new Piece(false, null, i, j, "Bomb");
				}
				if ( (i == 5) && (j % 2 == 0) ) {
					pieces[i][j] = new Piece(true, null, i, j, "Bomb");
				}
				if ( (i == 6) && (j % 2 == 1) ) {
					pieces[i][j] = new Piece(true, null, i, j, "Shield");
				}
				if ( (i == 7) && (j % 2 == 0) ) {
					pieces[i][j] = new Piece(true, null, i, j, null);
				}
			}
		}
	}

	//Starts a GUI supported version of the game
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		initialBoard(false);

		while(winner() == null) {
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
			}
			
			int x = (int)x;
			int y = (int)y;

			PieceAt(x, y); //Gets the piece at position (x, y)
			select(x, y); //Selects the square at (x, y)
			place(p, x, y); //Places p at (x, y)
			remove(x, y); //Executes a remove
			endTurn(); //includes hasCaptured()
			doneCapturing();
			StdDrawPlus.show(100);
		}
		System.out.println("Winner:" + Winner);
	}

	public static Piece PieceAt(int x, int y) { 
		if(pieces[x][y] == null) {
			return null;
		} else if(x < 0 || x > 8 || y < 0 || y > 8) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public static boolean canSelect(int x, int y) {
		if(pieces[x][y] != null) {
			if(PieceAt(x, y) == null) {
				return true;
			} else if(doneMove == false) {
				return true;
			} else {
				return false;
			}
		} else /*empty square*/ {
			if(/*During this turn, the player has selected a Piece which hasn’t moved yet and
			 is selecting an empty spot which is a valid move for the previously selected Piece.*/
					) {
				return true;
			} else if(/*During this turn, the player has selected a Piece, captured, and has selected 
			another valid capture destination. When performing multi-captures, you should 
			only select the active piece once; all other selections should be valid destination points.*/
					) {
				return true;
			} else {
				return false;
			}
		}
	}

	//(NOT REQUIRED) private boolean validMove(int xi, int yi, int xf, int yf) {
	//	if(/*the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), strictly from a geometry/piece-race point of view.*/) {
	//	    return true;
	//	}
	//}

	/*5. Not yet. How to implement? */
	public static void select(int x, int y) {
		if(canSelect(x, y) == true) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE); //color background
			if (/*any piece to perform a capture*/) {
				//you are prepping that piece for movement.
			} else /*an empty square*/ {
				//move your most recently selected piece to that square.
			}
		}
	}

	public static void place(Piece p, int x, int y) {
		if( (x >= 0) && (x <= N) && (y >= 0) && (y <= N) && (p != null) ) {
			if (pieces[x][y] != null) {
				pieces[x][y] = null; //remove from initial position
			}
			if (/*another piece already exists at (x, y)*/) {
				//p will replace that piece
			}
		}
		doneMove = true; // Move completed this turn 
	}

	public static Piece remove(int x, int y) {
		if(x < 0 || x > 8 || y < 0 || y > 8) {
			return null;
			//System.out.println("This input is out of bounds...");
		} else if(pieces[x][y] == null) {
			return null;
			//System.out.println("There is no piece at (" + x + ", " + y + ")....");
		} else {
			pieces[x][y] = null;
		}
		return pieces[x][y];
	}

	boolean canEndTurn() {
		if(doneMove == true) {
			return true;
		} else if (hasCaptured() == true) {
			return true;
		} else {
			return false;
		}
	}

	public static void endTurn() {
		if(canEndTurn() == true) {
			//Handles switching of players and anything else that should happen at the end of a turn
			turn *= -1;
		}
	}

	public static String winner() {
		int num_fire = 0; 
		int num_water = 0;
		static String Winner = null;
		if(/*Finishes 2 turns without capturing*/) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(pieces[i][j] != null) {
						if(pieces[i][j].isFire == true) {
							num_fire++;
						} else {
							num_water++;
						}
					}
				}
			}
			if(num_fire > num_water) {
				Winner = "Fire";
			} else if(num_fire < num_water) {
				Winner = "Water";
			} else {
				Winner = "No One";
			}
			return Winner;
		} else {
			return null;
		}
	}

	
}
