/* Aravind Sivakumar
25226962
Proj 0 Board Class
*/

public class Board {	
	/* Invariants:
		- the x-position refers to the column
		- the y-position refers to the row
	*/
	
	private Piece[][] pieces = new Piece[8][8];
	private boolean isFiresTurn;
	private boolean hasSelected;
	private boolean hasMoreMoves;
	private int selectedX;
	private int selectedY;
	//private boolean hasMoved;		
	private boolean canEndTurn;
	private boolean somePieceHasMoved;	

	//CONSTRUCTOR IS GOOD
	public Board(boolean shouldBeEmpty){
		// Constructor that assigns the correct pieces in the class variable arraylist Pieces, and initializes any necessary class variables.
		this.isFiresTurn = true;
		this.hasSelected = false;
		this.hasMoreMoves = true;
		//this.hasMoved = false;		
		this.canEndTurn = false;
		this.somePieceHasMoved = false;
		

		if (shouldBeEmpty == false) {

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

			this.pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
			this.pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
			this.pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
			this.pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

			this.pieces[0][6] = new Piece(false, this, 0, 6, "shield");
			this.pieces[2][6] = new Piece(false, this, 2, 6, "shield");
			this.pieces[4][6] = new Piece(false, this, 4, 6, "shield");
			this.pieces[6][6] = new Piece(false, this, 6, 6, "shield");

			this.pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
			this.pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
			this.pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
			this.pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		}
	}

	//drawPieces IS GOOD
	private void drawPieces() {
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if (this.pieces[x][y] != null) {
					if (this.pieces[x][y].isFire()) {				// if pieces are fire
						if (this.pieces[x][y].isBomb()) {
							if (this.pieces[x][y].isKing() == false) {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							}
						}
						else if (this.pieces[x][y].isShield()) {
							if (this.pieces[x][y].isKing() == false) {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire-crowned.png", 1, 1);
							}
						}
						else {
							if (this.pieces[x][y].isKing() == false) {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}
					}

					else {											// if pieces are water
						if (this.pieces[x][y].isBomb()) {
							if (this.pieces[x][y].isKing() == false) {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water-crowned.png", 1, 1);
							}
						}
						else if (this.pieces[x][y].isShield()) {
							if (this.pieces[x][y].isKing() == false) {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water-crowned.png", 1, 1);
							}
						}
						else {
							if (this.pieces[x][y].isKing() == false) {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	//drawBoard IS GOOD
	private static void drawBoard(int N) {
		/*  for every square in an 8x8 grid, set the appropriate pen color (red or gray)
			Draw a filled square, centered at the center of the square, with a half-side-length of 0.5 (we assume each square to have length 1)
			Then reset the pen color to white, before going to the next square
		*/
		for (int row = 0; row < N; row += 1) {
			for (int column = 0; column < N; column += 1) {
				if ((row + column) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(row + 0.5, column + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	// piece IS GOOD
	public Piece pieceAt(int x, int y) {
		/*  Returns the piece at (x, y)
		If there's no piece there, or it's out of bounds, return null;
		*/
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {return null;}
		else {return this.pieces[x][y];}
	}
	
	// canSelect IS GOOD
	public boolean canSelect(int x, int y) {
		/* Returns whether or not the piece at (x, y) can be selected
		(x, y) must be in bounds
		It must be the corresponding player's turn
		Either he has not selected a piece yet, or has selected but not moved
		If empty piece:
			- either he's selected a piece and wants to select a destination (which could be a double capture spot)
		*/
		if (x >= 8 || y >= 8 || x < 0 || y < 0)	{
			return false;}		// if (x, y) out of bounds
		
		if (this.hasMoreMoves == false) 	{
			return false;}		// if the player has no more possible actions on his turn, return false
			
		if (this.pieces[x][y] == null) {		// if selected spot is empty
			if (this.pieces[selectedX][selectedY].hasCaptured() == true) {		// if the selected piece has captured already
				return selectedPieceCanCaptureHere(x, y);
			}

			else {		// if selected piece has not yet captured
				if (this.hasSelected == false) {		// if trying to select an empty square without having first selected a piece
					return false;
				}		
				
				else if (validMove(selectedX, selectedY, x, y) == false) {		// if the desired destination is not a valid move for the selected piece
					return false;
				}		
				
				else {
					return true;
				}
			}
		}
		
		// now all possibilities assume there is a piece at the desired (x, y)
		/*if (this.pieces[selectedX][selectedX].hasCaptured() == true) {
			return false;
		}*/
		
		if (this.isFiresTurn != this.pieces[x][y].isFire()) {	// if other player's turn
			return false;
		}		

		else {
			if (this.hasSelected) {
				if (this.pieces[selectedX][selectedY].hasCaptured() == true) {
					return false;
				}
			}
			return true;
		}
		
	}

	// selectedPieceCanCaptureHere IS GOOD
	private boolean selectedPieceCanCaptureHere(int x, int y) {
		/* returns whether the currently selected piece can capture at (x, y) */
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {return false;}

		if (this.pieces[selectedX][selectedY].isKing() == false) {
			return ( ((selectedX - x== 2) || (x - selectedX == 2)) && (y - selectedY == 2) && validMove(selectedX, selectedY, x, y) );
		}
		else {
			return ( ((selectedX - x == 2) || (x - selectedX == 2)) && ((y - selectedY == 2) || (selectedY - y == 2)) && validMove(selectedX, selectedY, x, y));	// only difference is kings can capture backwards
		}
	}

	// moreCapturesPossible IS GOOD
	private boolean moreCapturesPossible() {
		// returns whether the currently selected piece has more captures possible
		int right = selectedX + 2;
		int left = selectedX - 2;
		int forward = selectedY + 2;
		int back = selectedY - 2;
		
		if (this.pieces[selectedX][selectedY].isKing() == false) {			// if not king
			if ((selectedPieceCanCaptureHere(right, forward) || 
				selectedPieceCanCaptureHere(left, forward)) == true) {
				return true;
			}
			else {this.hasMoreMoves = false; return false;}
		}
		else {			// if king
			if ((selectedPieceCanCaptureHere(right, forward) || 
				selectedPieceCanCaptureHere(left, forward) || 
				selectedPieceCanCaptureHere(right, back) || 
				selectedPieceCanCaptureHere(left, back))) {
				return true;
			}
			else {this.hasMoreMoves = false; return false;}
		}
	}

	//validMove IS GOOD
	private boolean validMove(int xi, int yi, int xf, int yf) {
		/* Simply returns whether or not the piece at (xi, yi) can  move to (xf, yf)
		There must actually be a piece at the inital spot
		The destination must be empty
		and must be geometrically possible
		if there are any pieces in the way (aka jump move), they must be opposition pieces
		*/
		Piece movingPiece = this.pieces[xi][yi];
		if (movingPiece.isFire() != this.isFiresTurn) {		// if wrong player's turn, return false
			return false;
		}
		if (this.pieces[xf][yf] != null) {		// if destination is occupied, return false
			return false;
		}
		if ( ((xf - xi == 1) || (xi - xf == 1)) && (yf - yi == 1) ) {		// if simple upward diagonal move...
			if (this.isFiresTurn == true) {		// if fire's turn, return true
				return true;
			}	
			else {		// if water's turn return true only if king
				if (movingPiece.isKing() == true) {
					return true;
				}
				else {
					return false;
				}
			}
		}

		if ( ((xf - xi == 1) || (xi - xf == 1)) && (yi - yf == 1) ) {		// if simple downward diagonal move...
			if (this.isFiresTurn == false) {		// if water's turn, return true
				return true;
			}	
			else {		// if fire's turn return true only if king
				if (movingPiece.isKing() == true) {
					return true;
				}
				else {
					return false;
				}
			}
		}

		if ( ((xf - xi == 2) || (xi - xf == 2)) && (yf - yi == 2) ) {		// if upward "capture" move
			int middleX = (xf + xi) >>> 1;
			int middleY = (yf + yi) >>> 1;
			if (this.pieces[middleX][middleY] == null) {		// if middle spot is empty, return false
				return false;
			}
			else {		// if middle spot is occupied
				if (this.pieces[middleX][middleY].isFire() == movingPiece.isFire()) {	// if middle spot belongs to same team, return false
					return false;
				}
				else {		// if moving piece is a fire piece, return true, else return true only if moving piece is a king
					if (movingPiece.isFire()) {
						return true;
					}
					else {
						return movingPiece.isKing();
					}
				}
			}
		}

		if ( ((xf - xi == 2) || (xi - xf == 2)) && (yi - yf == 2) ) {		// if downward "capture" move		
			int middleX = (xf + xi) >>> 1;
			int middleY = (yf + yi) >>> 1;
			if (this.pieces[middleX][middleY] == null) {		// if middle spot is empty, return false
				return false;
			}
			else {		// if middle spot is occupied
				if (this.pieces[middleX][middleY].isFire() == movingPiece.isFire()) {	// if middle spot belongs to same team, return false
					return false;
				}
				else {		// if moving piece is a water piece, return true, else return true only if moving piece is a king
					if (movingPiece.isFire() == false) {		
						return true;
					}
					else {
						return movingPiece.isKing();
					}
				}
			}
		}
		
		return false;
	}

	// select IS GOOD
	public void select(int x, int y) {
		// Selects the piece at (x, y).  Optionally highlight that square in the GUI

		/*if (canSelect(x, y) == false) {		// if this piece cannot be selected, just return
			return;
		}*/		
		
		// everything from here on out implies that (x, y) can be selected.  and we will select it.
		//else {
			if (this.pieces[x][y] != null) {	// if we actually select a piece, then we take appropriate actions, and were good to return now
				hasSelected = true;
				selectedX = x;
				selectedY = y;
				return;
			}

			else {		// in the case that our selection is an empty spot
				this.pieces[selectedX][selectedY].move(x, y);
				hasSelected = true;
				selectedX = x;
				selectedY = y;
				this.canEndTurn = true;

				if (this.pieces[selectedX][selectedY] != null) {
					if (moreCapturesPossible() == false) {
						this.hasMoreMoves = false;
					}
				}
				
				else {
					this.hasMoreMoves =false;
				}

			}
		//}
	}

	// place IS GOOD
	public void place(Piece p, int x, int y) {
		/* get x and y of the piece
		if they're null, simply return
		otherwise, remove the piece from this position but save it in a temp variable
		check if theres a piece at the destination.  if there is, remove it
		set the destination's piece to be p
		*/
		if (p == null || x >= 8 || y >= 8 || x < 0 || y < 0) {
			return;
		} 
		else {
			int originX = xOfPiece(p);
			int originY = yOfPiece(p);
			
			if (originX < 8 && originY < 8) {
				remove(originX, originY);
			}
			
			if (this.pieces[x][y] != null) {
				remove(x, y);
			}
			this.pieces[x][y] = p;
			//this.hasMoved = true;
			this.somePieceHasMoved = true;	
		}
	}

	// xOfPiece IS GOOD
	private int xOfPiece(Piece p) {
		/* Return the x-value (column) of a given piece
		If piece not yet on board, return 9
		*/
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				//if (p.equals(this.pieces[row][column])) {			// info from javaworld.com
				if (this.pieces[x][y] == p) {
					return x;
				}
			}
		}
		return 9;
	}

	// yOfPiece is GOOD
	private int yOfPiece(Piece p) {
		/* Return the y-value (row) of a given piece
		If piece is not yet on the board, return 9
		*/	

		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if (this.pieces[x][y] == p) {			// info from javaworld.com
					return y;
				}
			}
		}
		return 9;
	}

	// remove IS GOOD
	public Piece remove(int x, int y) {
		/* Removes the piece at (x,y), and returns it
		if x,y out of bounds or there is no piece there, return null and print message */
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			return null;
		}
		
		else if (this.pieces[x][y] == null) {
			return null;
		}
		
		else {
			Piece returnPiece = this.pieces[x][y];
			this.pieces[x][y] = null;
			return returnPiece;
		}
	}

	
	// canEndTurn IS GOOD
	public boolean canEndTurn() {
		/* Returns whether or not a player can end turn
		Player must have either moved or captured
		*/
		return this.canEndTurn;
	}

	
	// endTurn IS GOOD
	public void endTurn() {
		/* Called at end of each turn
		Handles switching of player, and anything else necessary
		*/
		if (this.isFiresTurn == true) {
			this.isFiresTurn = false;
		}
		
		else {
			this.isFiresTurn = true;
		}

		this.hasSelected = false;
		this.hasMoreMoves = true;
		this.canEndTurn = false;
		if (this.pieces[selectedX][selectedY] != null) {
			this.pieces[selectedX][selectedY].doneCapturing();
		}
	}

	// firePiecesLeft IS GOOD
	private boolean firePiecesLeft() {
		/* Returns whether or not there are fire pieces left on the board */
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if (this.pieces[x][y] != null) {
					if (this.pieces[x][y].isFire() == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// waterPiecesLeft IS GOOD
	private boolean waterPiecesLeft() {
		/* Returns whether or not there are water pieces left on the board */
		for (int x = 0; x < 8; x += 1) {
			for (int y = 0; y < 8; y += 1) {
				if (this.pieces[x][y] != null) {
					if (this.pieces[x][y].isFire() == false) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	// winner IS GOOD
	public String winner() {
		/* Return "Fire", "Water", or "No one"
		return null if game not over yet
		*/
		if (this.firePiecesLeft() == true && this.waterPiecesLeft() == false) {
			return "Fire";
		}
		else if (this.firePiecesLeft() == false && this.waterPiecesLeft() == true) {
			return "Water";
		}
		else if (this.firePiecesLeft() == false && this.waterPiecesLeft() == false) {
			if (this.somePieceHasMoved == true) {
				return "No one";
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	// main IS GOOD
	public static void main(String[] args) {
		/* Executes game play loop and prints winner at the end */
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);					// Then set the X and Y scales to range from 0 to 8, so the board is displayed nicely
        StdDrawPlus.setYscale(0, 8);					

        b.drawBoard(8);					// Then draw the board
        b.drawPieces();					// and initially draw the pieces
        
        while (b.firePiecesLeft() == true && b.waterPiecesLeft() == true) {			// while the game is over ...
        	if (StdDrawPlus.mousePressed() == true) {		// if mouse is pressed ...
        		int x = (int) StdDrawPlus.mouseX();		// find x and y coordinate of mouse ...
        		int y = (int) StdDrawPlus.mouseY();
        		
        		if (b.canSelect(x, y)) {		// if spot is selectable, select it, and redraw the board with the new piece setup
        			b.select(x, y);
        			b.drawBoard(8);
        			b.drawPieces();
        			StdDrawPlus.show(50);		// briefly pause before continuing execution	
        		}
        	}

        	if (StdDrawPlus.isSpacePressed()) {		// if space is pressed, check if turn can end, and do so if it can
        		if (b.canEndTurn()) {
        			b.endTurn();
        		}
        	}

    	}

    	System.out.println(b.winner());		// print the winner at the end of the game
	}

}

// THE END!!!!!!