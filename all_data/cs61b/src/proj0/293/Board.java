public class Board {
	private boolean shouldBeEmpty;
	private Piece pieces[][];
	private boolean didMove;
	private int turn; //1 = fire, 0 = water
	private int[] location = new int[2];

	/* Starts a GUI supported version of the game. */ //From StdDrawDemo
	public static void main(String[] args) {
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b.drawBoard(N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
        a new piece appears. */
        // while(b.winner() == null) {
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                	// b.drawBoard(N);
                }
            }        
            //draw
            b.drawBoard(N);
            StdDrawPlus.show(100);
            //end it!
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) { b.endTurn(); }
            if (b.winner() != null) { b.winner(); }
        }
	}

	/** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		if (pieces[i][j].isKing()) {
                			if (pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else if (pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                		} else {
                			if (pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			} else if (pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else {
                		if (pieces[i][j].isKing()) {
                			if (pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else if (pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                		} else {
                			if (pieces[i][j].isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			} else if (pieces[i][j].isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }
    }

	/* The constructor for Board. If shouldBeEmpty is true, initializes 
	an empty Board. Otherwise, initializes a Board with the default 
	configuration. Note that an empty Board could possibly be useful 
	for testing purposes. */
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		pieces = new Piece[8][8];
		didMove = false;
		turn = 0;
		if (!shouldBeEmpty) {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					if (j == 0 && (i % 2 == 0)) {
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					} else if (j == 1 && !(i % 2 == 0)) {
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					} else if (j == 2 && (i % 2 == 0)) {
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					} else if (j == 5 && !(i % 2 == 0)) {
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					} else if (j == 6 && (i % 2 == 0)) {
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					} else if (j == 7 && !(i % 2 == 0)) {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}	
		}
	}
	
	/* Gets the piece at position (x, y) on the board, or null if 
	there is no piece. If (x, y) are out of bounds, returns null. */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	/* Returns true if the square at (x, y) can be selected. */
	/* A square with a piece may be selected if it is the corresponding 
	player’s turn and one of the following is true:

	The player has not selected a piece yet.
	The player has selected a piece, but did not move it.
	An empty square may be selected if one of the following is true:

	During this turn, the player has selected a Piece which hasn’t moved 
	yet and is selecting an empty spot which is a valid move for the 
	previously selected Piece.

	During this turn, the player has selected a Piece, captured, and 
	has selected another valid capture destination. When performing 
	multi-captures, you should only select the active piece once; 
	all other selections should be valid destination points. */
	public boolean canSelect(int x, int y) {
		return false;
		
	}

	// /* Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	// or capture to (xf, yf), strictly from a geometry/piece-race point of 
	// view. validMove does not need to take into consideration whose turn 
	// it is or if a move has already been made, but rather can. Update (2/6): 
	// validMove is not required, and will not be tested. You may implement 
	// this however you want. It is suggested you use this method to simplify 
	// your implementation of canSelect. However, keep this method must be private. */
	// private boolean validMove(int xi, int yi, int xf, int yf) {
	// 	// check if isKing move all dir
	// 	// check if move in bounds
	// 	// check if move Update
	// 	// chec k if move more than 1
	// 	// check fire Update rain down
	// 	// if (xf > 7 || yf > 7 || xf < 0 || yf < 0) {
	// 	// 	return false;
	// 	// } else if (!piece[xi][yi].isKing()) {
	// 	// 	if ((piece[xi][yi].isFire() && yf < yi) || (piece[xi][yi].isFire() && yf > yi)) {
	// 	// 		return false;
	// 	// 	}
	// 	// } else if ()
	// 	// return false;

	// }

	/* Selects the square at (x, y). This method assumes canSelect (x,y) returns 
	true. Optionally, it is recommended to color the background of the selected 
	square white on the GUI via the pen color function. For any piece to perform 
	a capture, that piece must have been selected first. If you select a square 
	with a piece, you are prepping that piece for movement. If you select an empty 
	square (assuming canSelect returns true), you should move your most recently 
	selected piece to that square. */
	public void select(int x, int y) {

	}

	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
	nothing. If another piece already exists at (x, y), p will replace that 
	piece. (This method is potentially useful for creating specific test 
	circumstances.) */
	public void place(Piece p, int x, int y) {
		if (x < 8 || y < 8 || x >= 0 || y >= 0 || p != null) {
			pieces[x][y] = p;
			didMove = true;
		}
	}

	/* Executes a remove. Returns the piece that was removed. If the input 
	(x, y) is out of bounds, returns null and prints an appropriate message. If 
	there is no piece at (x, y), returns null and prints an appropriate message. */
	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("Input is out of bounds.");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("No piece located here to remove.");
			return null;
		} else {
			Piece temp = pieceAt(x, y);
			pieces[x][y] = null;
			return temp;
		}
	}
	
	/* Returns whether or not the the current player can end their turn. 
	To be able to end a turn, a piece must have moved or performed a capture. */
	public boolean canEndTurn() {
		return didMove;
	}
	
	/* Called at the end of each turn. Handles switching of players and anything 
	else that should happen at the end of a turn. */
	public void endTurn() {
		for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieceAt(i, j) != null) {
                    pieceAt(i, j).doneCapturing();
                }
            }
        }
		didMove = false;
		location = new int[2];
		turn = 1 - turn; // 1-1 = 0 fire 1-0 = 1 water
	}
	
	/* Returns the winner of the game: "Fire", "Water", "No one", or null. 
	If only fire pieces remain on the board, fire wins. If only water pieces 
	remain, water wins. If no pieces remain (consider an explosion capture), 
	no one wins. If the game is still in progress (ie there are pieces from 
	both sides still on the board) return null. Assume there is no stalemate 
	situation in which the current player has pieces but cannot legally move any 
	of them (In this event, just leave it at null). Determine the winner solely 
	by the number of pieces belonging to each team. */
	public String winner() {
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire()) {
            			countFire += 1;
            		} else if (!pieces[i][j].isFire()) {
            			countWater += 1;
            		} 
            	}
            }
        }

        if (countWater == 0 && countFire == 0) {
        	return "No one";
        } else if (countFire == 0) {
        	return "Water";
        } else if (countWater == 0) {
        	return "Fire";
        } else {
        	return null;
        }
	}

}






