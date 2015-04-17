/**
 * @author Quang Anh Luong
 */

public class Board {
	
	private Piece[][] grid; // Contains a 2d array of the game pieces
	private int size; // Size of the board
	private int[] selection; // Coordinates of selection
	private int[] hover; // Coordinates of the square the mouse is hovering over
	private boolean moved; // False if player has not moved yet, true if moved
	private int turn; // Indicates who's turn it is, 0 for red, 1 for blue;

	public Board(boolean shouldBeEmpty) {
		size = 8;
		grid = new Piece[size][size];
		selection = new int[2];
		selection[0] = -1;
		selection[1] = -1;
		// If shouldBeEmpty is true, don't initialize any pieces
		// Otherwise, initialize with standard layout
		if (!shouldBeEmpty) {
			for (int i = 0; i < size; i+=2) {
				grid[i][0] = new Piece(true, this, i, 0, "pawn");
				grid[size-1-i][size-1] = new Piece(false, this, size-1-i, size-1, "pawn");
				grid[i+1][1] = new Piece(true, this, i+1, 1, "shield");
				grid[size-2-i][size-2] = new Piece(false, this, size-2-i, size-2, "shield");
				grid[i][2] = new Piece(true, this, i, 2, "bomb");
				grid[size-1-i][size-3] = new Piece(false, this, size-1-i, size-3, "bomb");
			}
		}
	}
	// Returns the piece at (x, y), or null if out of bounds
	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y)) return null;
		return grid[x][y];
	}
	// Determines if the square can be selected
	public boolean canSelect(int x, int y) {
		int xi = xCoord(selection);
		int yi = yCoord(selection);
		// Cannot select if piece is from wrong side
		if (pieceAt(x, y) != null && pieceAt(x, y).side() != turn) {
			return false;
		// Able to select a piece if no move has been made
		} else if (!moved && pieceAt(x, y) != null) {
			return true;
		// getSelection() makes sure that a selection has already been made
		} else if (getSelection() != null) {
			// If a selection has already been made, check to see if it can move
			// to the new selection
			return validMove(xi, yi, x, y);
		} else {
			return false;
		}
	}
	
	// Places a piece at (x, y), but does not set the piece's x and y
	public void place(Piece p, int x, int y) {
		if (!outOfBounds(x, y)) {
			int[] prevCoords = getPieceCoords(p);
			// Check if moving a piece or
			if(prevCoords != null) {
				remove(xCoord(prevCoords), yCoord(prevCoords));
			}
			grid[x][y] = p;
		}
	}
	// Removes a piece at (x, y)
	public Piece remove(int x, int y) {
		Piece toRemove = pieceAt(x, y);
		if (outOfBounds(x, y)) {
			System.out.println("Out of bounds!");
			return null;
		} else if (toRemove == null) {
			System.out.println("Nothing to remove!");
			return null;
		} else {
			if (x == xCoord(selection) && y == yCoord(selection)) {
				deselect();
			}
			grid[x][y] = null;
			return toRemove;
		}

	}
	// Determines if the turn can be ended
	public boolean canEndTurn() {
		return moved;
	}

	// Switches sides, resets moved, deselects selection
	public void endTurn() {
		if (getSelection() != null) {
			getSelection().doneCapturing();
		}
		turn = 1 - turn;
		moved = false;
		deselect();
	}
	// Returns who won
	public String winner() {
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < size; i += 1) {
			for (int j = 0 ; j < size; j += 1) {
				if (pieceAt(i, j) != null) {
					// Add to count of fire pieces if isFire()
					if (pieceAt(i, j).isFire()) {
						numFire += 1;
					// Else means water, so add 1 to numWater
					} else {
						numWater += 1;
					}	
				}
			}
		}
		// If numFire and numWater are both 0, no one wins
		if (numFire == 0 && numWater == 0) {
			return "No one";
		// If numWater is 0 and numFire is not 0, then Fire wins
		} else if (numWater == 0) {
			return "Fire";
		// If numFire is 0 and numWater is not 0, then Water wins
		} else if (numFire == 0) {
			return "Water";
		// There are still pieces from both sides, so nobody wins
		} else {
			return null;
		}
	}
	// Determines a valid move based on the game rules
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece pieceToMove = pieceAt(xi, yi);
		// Cannot move to a square that is occupied
		if (pieceAt(xf, yf)!=null) {
			return false;
		}
		// Cannot move if already moved without having captured anything
		if (!pieceToMove.hasCaptured() && moved) {
			return false;
		}
		// Check to see if piece is moving the wrong way if not king
		if (!pieceToMove.isKing()) {
			// Fire cannot go down
			if (pieceToMove.isFire() && yf < yi) {
				return false;
			// Water cannot go up
			} else if (!pieceToMove.isFire() && yf > yi) {
				return false;
			}
		}
		// Cannot move to square out of bounds
		if (outOfBounds(xf, yf)) {
			return false;
		// Move without capture: make sure the spot is 1 diagonal square
		// away, and that the piece has not captured anything yet
		} else if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1 && 
			 	   !pieceToMove.hasCaptured()) {
			return true;
		// Jump and capture: make sure that the spot is 2 diagonal squares
		// away, and that there is a piece that is being jumped that is
		// not on the same side as the player's turn
		} else if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2 && 
				   pieceAt(xi+(xf-xi)/2, yi+(yf-yi)/2) != null &&
				   pieceAt(xi+(xf-xi)/2, yi+(yf-yi)/2).side() != pieceAt(xi, yi).side()) {
			return true;
		} else {
			return false;
		}
	}
	// Returns the piece at the selected square
	private Piece getSelection() {
		return pieceAt(xCoord(selection), yCoord(selection));
	}
	// Deselects current selection; (-1 -1) will represent no selection
	private void deselect() {
		selection[0] = -1;
		selection[1] = -1;
	}
	// Checks to see if the square at (x, y) is out of bounds
	private boolean outOfBounds(int x, int y) {
		return !(x >= 0 && x < size && y >= 0 && y < size);
	}
	// Returns the location of the appropriate image for a piece
	private static String getImage(Piece p) {
		String image = "img/";
		if (p.isBomb()) {
			image += "bomb";
		} else if (p.isShield()) {
			image += "shield";
		} else {
			image += "pawn";
		}
		if (p.isFire()) {
			image += "-fire";
		} else {
			image += "-water";
		}
		if (p.isKing()) {
			image += "-crowned";
		}
		image += ".png";
		return image;
	}
	// Returns an array of integer positions of current mouse
	private int[] getMousePos() {
		int[] pos = {(int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY()};
		return pos;
	}
	// Returns an array of the x and y positions of a piece
	private int[] getPieceCoords(Piece p) {
		for(int i=0; i<size; i+=1) {
			for(int j=0; j<size; j+=1) {
				if (pieceAt(i, j) != null && pieceAt(i, j) == p) {
					int[] coords = {i, j};
					return coords;
				}
			}
		}
		return null;
	}
	// Return x coordinate of selection coordinates or hover coordinates
	private int xCoord(int[] s) {
		return s[0];
	}
	// Return y coordinate of selection coordinates or hover coordinates
	private int yCoord(int[] s) {
		return s[1];
	}
	// Update the selection if the clicked square is selectable
	private void updateSelection() {
    	int x = xCoord(getMousePos());
    	int y = yCoord(getMousePos());
		if (StdDrawPlus.mousePressed() && canSelect(x, y)) {
			select(x, y);
       	}
	}
	// Changes the selection coordinates to the arguments and makes a move if necessary
	public void select(int x, int y) {
		// Making a move from select, otherwise skip if not making a move
		if (validMove(xCoord(selection), yCoord(selection), x, y)) {
			getSelection().move(x, y);
			moved = true;
		}
		selection[0] = x;
		selection[1] = y;
		if (pieceAt(x, y) == null) {
			deselect();
		}
	}
	// Update the hover square
	private void updateHover() {
		hover = getMousePos();
	}
	// Draws the board
	private void drawBoard() {
		for (int i = 0; i < size; i += 1) {
			for (int j = 0; j < size; j += 1) {
				// Set the color of the selection square to white
				if (i == xCoord(selection) && j == yCoord(selection)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				// If the square is not selection and the mouse is hovering
				// over it, change it to the color of the side whose turn it is
				} else if (i == xCoord(hover) && j == yCoord(hover)) {
					if (turn == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
					} else {
						StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
					}
				// Dark squares are gray
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				// Light squares are light gray
				} else {
	                StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // Draw the image of the piece at the square it is on
             	if (pieceAt(i, j) != null) {
             		StdDrawPlus.picture(i + .5, j + .5, getImage(pieceAt(i, j)), 1, 1);
             	}

            }
		}
	}

	public static void main(String[] args) {
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.size);
        StdDrawPlus.setYscale(0, b.size);
        while(true) {
        	if (StdDrawPlus.isNPressed()) {
        		b = new Board(false);
        	}
        	b.updateSelection();
        	b.updateHover();
        	

        	if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
        		b.endTurn();
        	}
			b.drawBoard();
			StdDrawPlus.show(0);
		}
	}

}