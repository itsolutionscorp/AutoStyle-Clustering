/* author: Priyanka Bhoj */

public class Board {
	private boolean shouldBeEmpty, hasSelected, moved, fireTurn;
	private Piece curr;
	private Piece[][] pieces;
	private int n, currX, currY, fireCount, waterCount;

	public static void main(String[] args) {
        /* Starts a GUI supported version of the game */
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.n);
        StdDrawPlus.setYscale(0, b.n);

        while(true) {
			b.drawBoard(b.n); 
			//continue the game as long as nobody has won
			if (b.winner() == null) {
	            if (StdDrawPlus.mousePressed()) {
	                double x = StdDrawPlus.mouseX();
	                double y = StdDrawPlus.mouseY();
	                if (b.canSelect((int) x, (int) y)) {
	                	b.select((int) x, (int) y);
	                }
	            }       
            } 
            //switch turns if the player has made a move    
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }
            //print out the winner if the game is over
            if (b.winner() != null) {
            	System.out.println(b.winner());
            }
            //reset for a new game if the current game is over
            if (StdDrawPlus.isNPressed() && b.winner() != null) {
				b = new Board(false);
            }
            //continually redraw the board
            StdDrawPlus.show(10);
        }
	}

	public Board(boolean shouldBeEmpty) {
		/* The constructor for Board. If shouldBeEmpty is true,
		initializes an empty Board. Otherwise, initializes a
		Board with the default configuration. Note that an empty
		Board could possibly be useful for testing purposes. */
		
		//initialize all of the class variables
		this.shouldBeEmpty = shouldBeEmpty;
		hasSelected = moved = false;
		currX = currY = n = 8;
		fireCount = waterCount = 0;
		fireTurn = true;
		pieces = new Piece[n][n];
		curr = null;

		//initialize a board with pieces
		if (!shouldBeEmpty) {
	        for (int x = 0; x < n; x++) {
	            for (int y = 0; y < n; y++) {
	            	if (x % 2 == 0) {
	            		pieces[x][0] = new Piece(true, this, x, 0, "pawn");
	            		fireCount++;
	            		pieces[x][2] = new Piece(true, this, x, 2, "bomb");
	            		fireCount++;
	            		pieces[x][n-2] = new Piece(false, this, x, n-2, "shield");
	            		waterCount++;
	            	}
	            	if (x % 2 == 1) {
	            		pieces[x][1] = new Piece(true, this, x, 1, "shield");
	            		fireCount++;
	            		pieces[x][n-3] = new Piece(false, this, x, n-3, "bomb");
	            		waterCount++;
	            		pieces[x][n-1] = new Piece(false, this, x, n-1, "pawn");
	            		waterCount++;
	            	}
	            }
	        }
	        fireCount = fireCount/n;
	        waterCount = waterCount/n;
        }		
	}

    private void drawBoard(int n) {
    	//draw the board and pieces
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
				if ((x + y) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (currX == x && currY == y && !(curr.isBomb() && curr.hasCaptured())) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }                  
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				String img = getImage(pieceAt(x, y));
				if (!img.equals("no image")) {
	            	StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
	            }
            }
        }
    }

	private String getImage(Piece p) {
		//determine the appropriate image to draw on the corresponding square
		if (p == null) {
			return "no image";
		}
		else if (p.isFire() && p.isBomb() && !p.isKing()) {
			return "img/bomb-fire.png";
		}
		else if (p.isFire() && p.isShield() && !p.isKing()) {
			return "img/shield-fire.png";
		}
		else if (p.isFire() && !p.isKing()) {
			return "img/pawn-fire.png";
		}
		else if (!p.isFire() && p.isBomb() && !p.isKing()) {
			return "img/bomb-water.png";
		}
		else if (!p.isFire() && p.isShield() && !p.isKing()) {
			return "img/shield-water.png";
		}
		else if (!p.isFire() && !p.isKing()) {
			return "img/pawn-water.png";
		}
		else if (p.isFire() && p.isBomb() && p.isKing()) {
			return "img/bomb-fire-crowned.png";
		}
		else if (p.isFire() && p.isShield() && p.isKing()) {
			return "img/shield-fire-crowned.png";
		}
		else if (p.isFire() && p.isKing()) {
			return "img/pawn-fire-crowned.png";
		}
		else if (!p.isFire() && p.isBomb() && p.isKing()) {
			return "img/bomb-water-crowned.png";
		}
		else if (!p.isFire() && p.isShield() && p.isKing()) {
			return "img/shield-water-crowned.png";
		}
		else if (!p.isFire() && p.isKing()) {
			return "img/pawn-water-crowned.png";
		}
		return "no image";
	}   

//Starting game logic

	public Piece pieceAt(int x, int y) {
		/* Gets the piece at position (x, y) on the board, or
		null if there is no piece. If (x, y) are out of bounds,
		returns null. */
		if (x >= n || y >= n || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		/* Returns true if there is a piece at (x, y) and it
		can be selected. Otherwise, return false. */
		Piece p = pieceAt(x, y);

		if (checkPlayer(p) && !moved) {
			return true;
		}
		if (p == null && hasSelected && !(curr.isBomb() && curr.hasCaptured())) {
			return validMove(currX, currY, x, y);
		}
		return false;
	}

	private boolean checkPlayer(Piece p) {
		if (p != null) {
			return (fireTurn == p.isFire());
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		//if the piece is a king (can move in either direction)
		if (curr.isKing()) {
			//regular king movement
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) {
				return !moved;
			}
			//king peforming a capture
			else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
				return validCapture(xi, yi, xf, yf);
			}
			return false;
		}
		else {
			//regular fire piece movement
			if (fireTurn && Math.abs(xi - xf) == 1 && yf - yi == 1) {
				return !moved;
			}
			//regular water piece movement
			else if (!fireTurn && Math.abs(xi - xf) == 1 && yi - yf == 1) {
				return !moved;
			}
			//fire piece performing a capture
			else if (fireTurn && Math.abs(xi - xf) == 2 && yf - yi == 2) {
				return validCapture(xi, yi, xf, yf);
			}
			//water piece performing a capture
			else if (!fireTurn && Math.abs(xi - xf) == 2 && yi - yf == 2) {
				return validCapture(xi, yi, xf, yf);
			}
			return false;
		}
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		//check if there is a piece to capture adn it is of the right type
		int avgx = (xi + xf)/2;
		int avgy = (yi + yf)/2;
		Piece adjacent = pieceAt(avgx, avgy);
		if (adjacent == null || adjacent.isFire() == curr.isFire()) {
			return false;
		}
		return true;
	}

	public void select(int x, int y) {
		/* Selects the piece at (x, y) if possible. Optionally,
		it is recommended to color the background of the selected
		square white on the GUI via the pen color function. For
		any piece to perform a capture, that piece must have been
		selected first. */
		Piece p = pieceAt(x, y);
		//if you are selecting a piece
		if (p != null) {
			curr = p;
			currX = x;
			currY = y;
			hasSelected = true;	
		}
		//if you are selecting an empty spot to move to
		else if (p == null) {		
			curr.move(x, y);
			currX = x;
			currY = y;
			moved = true;
		}
		
	}

	public void place(Piece p, int x, int y) {
		/* Places p at (x, y). If (x, y) is out of bounds or if p
		is null, does nothing. If p already exists in the current
		Board, first removes it from its initial position. If another
		piece already exists at (x, y), p will replace that piece.
		(This method is potentially useful for creating specific
		test circumstances.) */
		//if attempting to place nothing or in an invalid spot
		if (x >= n || y >= n || p == null || x < 0 || y < 0) {
			return;
		}
		//remove the piece if it already exists somewhere else on the board
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (pieceAt(i, j) == p) {
					remove(i, j);
				}
			}
		}
		//placing the piece at the new spot
		pieces[x][y] = p;

		//increasing the count of the corresponding spot
		if (p.isFire()) {
			fireCount += 1;
		}
		else {
			waterCount += 1;
		}
	}

	public Piece remove(int x, int y) {
		/* Executes a remove. Returns the piece that was removed.
		If the input (x, y) is out of bounds, returns null and prints
		an appropriate message. If there is no piece at (x, y),
		returns null and prints an appropriate message. */
		if (x >= n || y >= n || x < 0 || y < 0) {
			System.out.println("Input out of bounds");
			return null;
		}

		Piece removed = pieceAt(x, y);
		//delete the piece
		pieces[x][y] = null;

		//update the piece count if a piece was removed
		if (removed != null) {
			if (removed.isFire()) {
				fireCount -= 1;
			}
			else {
				waterCount -= 1;
			}
		}
		//print an error message if there is no piece at the spot
		else {
			System.out.println("No piece at " + x + ", " + y);
		}
		return removed;
	}

	public boolean canEndTurn() {
		/* Returns whether or not the the current player can end their 
		turn. To be able to end a turn, a piece must have moved or 
		performed a capture. */
		if (curr != null) {
			if (moved || curr.hasCaptured()) {
				return true;
			}
		}
		return false;
	}

	public void endTurn() {
		/* Called at the end of each turn. Handles switching of players
		and anything else that should happen at the end of a turn. */
		curr.doneCapturing();
		curr = null;
		currX = currY = n;
		hasSelected = moved = false;
		fireTurn = !fireTurn;
	}

	public String winner() {
		/* Returns the winner of the game. "Fire", "Water", "No one"
		(tie / no pieces on the board), or null if the game is not yet
		over. Assume there is no stalemate situation in which the current
		player has pieces but cannot legally move any of them (In this
		event, just leave it at null). Determine the winner solely by the
		number of pieces belonging to each team. */
		if (fireCount == 0 && waterCount == 0) {
			return "No one";
		}
		else if (fireCount == 0) {
			return "Water";
		}
		else if (waterCount == 0) {
			return "Fire";
		}
		return null;
	}
}