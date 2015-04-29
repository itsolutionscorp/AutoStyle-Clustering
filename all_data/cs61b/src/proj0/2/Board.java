import java.awt.Color;

/**
 * The Board class begins a special game of Checkers with three kinds of
 * pieces: pawn, shield, and bomb. Running the class initializes a game of
 * of Checkers.
 */
public class Board{

	//Array of Pieces that are initialized on the Board
	private Piece[][] pieces;

	private int size;
	private boolean fireTurn;
	private boolean hasMoved;
	private Piece selected;
	private int selectedX;
	private int selectedY;

	/**
	 * Starts the game of checkers with a supported GUI.
	 */
	public static void main (String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setScale(0, 8);

		while(true) {
			b.drawBoard();
			if(StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				int xp = (int) x;
				int yp = (int) y;
				if(b.canSelect(xp, yp)) {
					b.select(xp, yp);
				}
			}
			if(b.winner() != null){
				StdDrawPlus.show(10);
				break;
			}
			if(StdDrawPlus.isSpacePressed()){
				if(b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
		System.out.println(b.winner());
	}

	/**
	 * This is a helper method to the main method. It draws the board during
	 * turns and highlights a tile white if it has been clicked on.
	 * Specifcally, the white tile will be the tile that holds the selected
	 * piece. The board is an 8 x 8 board with alternating colors of gray and
	 * red with (0, 0) being gray. Finally, if the board is being initialized
	 * for the first time, then pieces will be initialized considering that the
	 * instance variable, initial, is false.
	 * @param b is the Board that is to be drawn.
	 */
	private void drawBoard() {
		for(int x = 0; x < size; x += 1) {
			for(int y = 0; y < size; y += 1) {
				if(x == selectedX && y == selectedY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if((x + y) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				if(pieces[x][y] != null) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, 
						getImage(pieces[x][y]), 1, 1);
				}
			}
		}
	}

	/**
	 * This is a helper method to the drawBoard method. This returns the file
	 * path to the image of a specific piece depending on certain instances of
	 * the piece.
	 * @param p is the Piece whose image we desire.
	 */
	private String getImage(Piece p) {
		String name = "img/";
		if(p.isBomb()) {
			name += "bomb-";
		} else if(p.isShield()) {
			name += "shield-";
		} else {
			name += "pawn-";
		}

		if(p.isFire()) {
			name += "fire";
		} else {
			name += "water";
		}

		if(p.isKing()) {
			name += "-crowned";
		}
		return name + ".png";
	}

	/**
	 * This is a helper method to the Board constructor. It initializes pieces
	 * Board if the board is not intended to be empty.
	 * @param b is the Board that the pieces are initialized on.
	 */
	private void initializePieces(Board b) {
		for(int x = 0; x < size; x += 1) {
			for(int y = 0; y < size; y += 1) {
				if((x + y) % 2 == 0) {
					switch(y) {
						case 0: pieces[x][y] = new Piece(true, b, x, y, "pawn");
								break;
						case 1: pieces[x][y] = new Piece(true, b, x, y, "shield");
								break;
						case 2: pieces[x][y] = new Piece(true, b, x, y, "bomb");
								break;
						case 5: pieces[x][y] = new Piece(false, b, x, y, "bomb");
								break;
						case 6: pieces[x][y] = new Piece(false, b, x, y, "shield");
								break;
						case 7: pieces[x][y] = new Piece(false, b, x, y, "pawn");
								break;
						default: break;
					}
				}
			}
		}
	}

	/**
	 * This is the constructor for Board. Initializes a Board of 64 squares and
	 * dimensions of 8 x 8. If shouldBeEmpty is True, no pieces are initialized.
	 * Otherwise, six rows of pieces are initialized similar to actual checkers
	 * except that specific rows have specific kinds of characters.
	 * @param shouldBeEmpty tells the method whether or not to initialize pieces
	 */
	public Board(boolean shouldBeEmpty) {
		size = 8;
		pieces = new Piece[size][size];
		if(!shouldBeEmpty) {
			initializePieces(this);
		}
		fireTurn = true;
		hasMoved = false;
		selected = null;
		selectedX = -1;
		selectedY = -1;
	}

	/**
	 * Checks if a there is Piece at a specific location on the Board.
	 * @param x is the x-coordinate of the location
	 * @param y is the y-coordinate of the location
	 * @return the Piece located at (x, y)
	 *		   null, otherwise
	 */
	public Piece pieceAt(int x, int y) {
		//Checks for out of bounds
		if(notValidPosition(x, y)) {
			return null;
		}
		return pieces[x][y];
	}

	/**
	 * canSelect checks whether or not a location on the Board can be selected
	 * or not depending on the current state of the players turn. If no piece
	 * has been selected yet then the method will only allow for the selection
	 * of a space with a piece in the space that corresponds to the current
	 * player's turn. Otherwise, if a piece has already been selected, then the
	 * method only approves of spaces that the piece can move to, capturing or
	 * not capturing a piece.
	 * @param x is the x-coordinate of the location being considered.
	 * @param y is the y-coordinate of the location being considered.
	 * @return true, if (x, y) is a valid selected location
	 *		   false, otherwise
	 */
	public boolean canSelect(int x, int y) {
		if(notValidPosition(x, y)) {
			return false;
		}
		Piece piece = pieceAt(x, y);
		//Square with a piece
		if(piece != null) {
			//Selecting piece for movement
			if(selected == null || !hasMoved) {
				return (fireTurn == piece.isFire());
			}
		} else {
			//Piece has been selected
			if(selected != null) {
				//Selecting empty square for movement
				if(!hasMoved && validMove(selectedX, selectedY, x, y)) {
					return true;
				} else if(hasMoved && selected.hasCaptured() && 
							validMove(selectedX, selectedY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a piece's move is valid. There are two cases for valid moves.
	 * Case 1) A diagonal move up one square and to the right one square or a
	 * 		   a diagonal move up one square and to the left one square. The
	 *		   final destination cannot have a piece on it already.
	 * Case 2) The piece captures a piece to one of its diagonals assuming that
	 *		   the position diagonally in front of the captured piece is empty.
	 * @param xi is the initial x-coordinate of the piece
	 * @param yi is the initial y-coordinate of the piece
	 * @param xf is the intended x-coordinate of the piece
	 * @param yf is the intended y-coordinate of the piece
	 * @return true, if (xf, yf) is a valid position to move to
	 *		   false, otherwise
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece initial = pieceAt(xi, yi);
		int xDistance = xf - xi;
		int yDistance = yf - yi;
		if(initial == null) {
			return false;
		}
		if(initial.isKing() || initial.isFire()) {
			if(yDistance == 1 && Math.abs(xDistance) == 1) {
				return (!initial.hasCaptured());
			} else if(yDistance == 2 && Math.abs(xDistance) == 2) {
				Piece captured = midPiece(xi, yi, xf, yf);
				return (captured != null && (captured.isFire() != initial.isFire()));
			}
		} 
		if(initial.isKing() || !initial.isFire()) {
			if(yDistance == -1 && Math.abs(xDistance) == 1) {
				return (!initial.hasCaptured());
			} else  if(yDistance == -2 && Math.abs(xDistance) == 2) {
				Piece captured = midPiece(xi, yi, xf, yf);
				return (captured != null && (captured.isFire() != initial.isFire()));
			}
		}
		return false;
	}

	private Piece midPiece(int xi, int yi, int xf, int yf) {
		int xMid = (xf + xi)/2;
		int yMid = (yf + yi)/2;
		return pieceAt(xMid, yMid);
	}

	/**
	 * This method conducts the actual selection of a piece or location. If
	 * we are selecting a piece, then then the piece is stored as the instance
	 * variable, selected. If we are selecting a location, the selected piece
	 * moves to that location, and conducts other necessary steps including
	 * captures and removes.
	 * Precondition: canSelect() has already been executed and (x, y) is a
	 *				 valid location that can be selected from.
	 * @param x is the x-coordinate of the space being selected
	 * @param y is the y-coordinate of the space being selected
	 */
	public void select(int x, int y) {
		Piece piece = pieceAt(x, y);
		selectedX = x;
		selectedY = y;
		if(piece != null) {
			selected = piece;
		} else if(selected != null && piece == null) {
			selected.move(x, y);
			place(selected, x, y);
			if(selected.isBomb() && selected.hasCaptured()) {
				remove(x, y);
			}
			hasMoved = true;
		}
	}

	/**
	 * Places a piece at a specific position on the board. The position needs
	 * to be valid, and if a current piece is in the position, the new piece
	 * takes the position, and the previous piece is removed.
	 * @param p is the Piece that will be placed in the position
	 * @param x is the x-coordinate of the position
	 * @param y is the y-coordinate of the position
	 */
	public void place(Piece p, int x, int y) {
		if(notValidPosition(x, y) || p == null) {
			return;
		}
		Piece current = pieceOnBoard(p);
		if(current == null) {
			pieces[x][y] = p;
		} else {
			pieces[x][y] = current;
		}
	}

	/**
	 * Checks if a piece is currently on the board and removes it. This is a
	 * helper method for the place method.
	 * @param p is the Piece that we are looking for on the Board
	 * @return the piece if it exists on the board after removing it
	 *		   otherwise, null
	 */
	private Piece pieceOnBoard(Piece p) {
		for(int x = 0; x < size; x += 1) {
			for(int y = 0; y < size; y += 1) {
				if(pieces[x][y] == p) {
					return remove(x, y);
				}
			}
		}
		return null;
	}

	/**
	 * Checks if there is a piece at a location on the board and removes the
	 * piece if it is there. The position must be a valid position. If there
	 * is no piece at the position or the position is not valid, the system
	 * will print out a message.
	 * @param x is the x-coordinate of the position
	 * @param y is the y-coordinate of the position
	 * @return the removed piece
	 */
	public Piece remove(int x, int y) {
		if(notValidPosition(x, y)) {
			System.out.println("Woah! In the words of Lindsay Lohan, the (limit) position does not exist.");
			return null;
		}
		Piece removed = pieceAt(x, y);
		if(removed == null) {
			System.out.println("Excuse me, sir/madam, you are currently attempting to remove nothing.");
			return removed;
		}
		pieces[x][y] = null;
		return removed;
	}

	/**
	 * Checks whether or not a player can end his turn. A player can end his
	 * turn if a piece has moved.
	 * @return true if the player can end his turn
	 *		   false, otherwise
	 */
	public boolean canEndTurn() {
		if(hasMoved) {
			return true;
		} else if(selected != null && selected.hasCaptured()) {
			return true;
		}
		return false;
	}

	/**
	 * This method ends a player's turn and switches the baord to the next
	 * player's turn. All selected pieces are return to null, and the hasMoved
	 * instance variable is false too.
	 */
	public void endTurn() {
		fireTurn = !fireTurn;
		selected.doneCapturing();
		selected = null;
		selectedX = -1;
		selectedY = -1;
		hasMoved = false;
	}

	/**
	 * This method not only checks if there is a winner in the current game,
	 * but if there is a winner, it returns the name of the winner of the game.
	 * The winner is solely determined by the number of fire and water pieces
	 * remaining on the board.
	 * @return "No one" if there is no winner for the game.
	 *		   "Water" if the player with water pieces wins.
	 *		   "Fire" if the player with fire pieces wins.
	 *		   "No one" if there are no remaining pieces and no one wins.
	 *		   null, if there is no winner yet.
	 */
	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for(int x = 0; x < size; x += 1) {
			for(int y = 0; y < size; y += 1) {
				if(pieceAt(x, y) != null) {
					if(pieceAt(x, y).isFire()) {
						firePieces += 1;
					} else {
						waterPieces += 1;
					}
				}
			}
		}
		if(firePieces == 0 && waterPieces == 0) {
			return "No one";
		} else if(firePieces == 0) {
			return "Water";
		} else if(waterPieces == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	/**
	 * Helper method that checks whether or not a position exists on the Board.
	 * @param x is the x-coordinate of the position
	 * @param y is the y-coordinate of the position
	 * @return true if the position is not valid
	 * 		   false if it is valid
	 */
	private boolean notValidPosition(int x, int y) {
		return (x >= size || y >= size || x < 0 || y < 0);
	}
}