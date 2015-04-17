/**
* Majority of checkerboard code taken from StdDrawDemo.java, written by Josh Hug.
* @author Brian Khau
* final
*/
public class Board {
	/** Location of pieces. */
	private Piece[][] pieces;
	private static int N = 8;
	private boolean fireTurn = true;
	private boolean pieceSelected = false;
	private boolean pieceMoved = false;
	private int xSquare = 8;
	private int ySquare = 8;
	private Piece chosenPiece;
	private int xCoord = 0;
	private int yCoord = 0;

	public Board(boolean empty) {
		pieces = new Piece[N][N];
		fireTurn = true;
		beginBoard(N, empty);
	}

	public static void main(String [] args) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		b.drawBoard(b.pieces);
		while(true) {
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)) {
					b.select((int) x, (int) y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
					if (b.winner() != null) {
						break;
					}
				}
			}
			StdDrawPlus.show(50);
			b.drawBoard(b.pieces);
		}
	}

	/** Instantiate an array of Piece objects.
	* @param N describes length of checkerboard.
	* @param isEmpty draws empty board if true
	* @param b describes board for placing pieces
	*/
	private void beginBoard(int N, boolean isEmpty) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((!isEmpty) && ((i + j) % 2 == 0)) {
					if (j == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					} else if (j == 1) {
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					} else if (j == 2) {
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					} else if (j == 5) {
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					} else if (j == 6) {
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					} else if (j == 7) {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
	}

	/** Draws the current state of the board given a piece array.
	* @param pieceArray is a 2D array of pieces.
	**/
	private void drawBoard(Piece[][] pieceArray) {
		for (int i = 0; i < pieceArray.length; i++) {
			for (int j = 0; j < pieceArray[0].length; j++) {
				if ((i == xSquare) && (j == ySquare)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				Piece currPiece = pieceArray[i][j];
				if (currPiece != null) {
					String imgPath = getFilePath(currPiece);
					StdDrawPlus.picture(i + 0.5, j + 0.5, imgPath, 1, 1);
				}
			}
		}
	}

	/** Returns a string containing the filepath to the image corresponding
	* to the piece. Assuming path is valid.
	* @param p is the piece to be drawn
	* @return the images file path
	**/
	private String getFilePath(Piece p) {
		String imgPath = "img/";
		if (p.isBomb()) {
			imgPath = imgPath + "bomb-";
		} else if (p.isShield()) {
			imgPath = imgPath + "shield-";
		} else {
			imgPath = imgPath + "pawn-";
		}
		if (p.isFire()) {
			imgPath = imgPath + "fire";
		} else {
			imgPath = imgPath + "water";
		}
		if (p.isKing()) {
			imgPath = imgPath + "-crowned";
		}
		imgPath = imgPath + ".png";
		return imgPath;
	}

	/** Places piece p at (x,y) in the pieces array.
	* @param p is the piece to be placed
	* @param x is the x position of the piece to be drawn
	* @param y is the y position of the piece to be drawn
	**/
	public void place(Piece p, int x, int y) {
		if ((x > N - 1) || (y > N - 1)) {
			return;
		}
		pieces[x][y] = p;
	}

	/** Gets the piece at position (x, y) on the board, or null if there is no piece.
	* @param x is the x-position of the location to be checked
	* @param y is the y-position of the location to be checked
	* @return a Piece object if location is not empty, null otherwise
	**/
	public Piece pieceAt(int x, int y) {
		if ((x > N - 1) || (y > N - 1)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	/** Removes a piece from the board and returns the piece that was removed.
	* If the location passed in is out-of-bounds, return null and print out an
	* appropriate message. If there is no piece at the location return null and
	* print out an appropriate message.
	* @param x is the x-position of the location to be checked
	* @param y is the y-position of the location to be checked
	* @return a Piece object at position (x, y) if valid, null otherwise.
	**/
	public Piece remove(int x, int y) {
		Piece currPiece = pieceAt(x,y);
		if ((x > N - 1) || (y > N - 1)) {
			System.out.println("Position is out-of-bounds!");
			return null;
		} else if (currPiece == null) {
			System.out.println("No piece to remove here.");
			return null;
		} else {
			pieces[x][y] = null;
			return currPiece;
		}
	}

	/** Returns true if the square at (x,y) can be selected.
	* @param x is the x-position of the location to be checked
	* @param y is the y-position of the location to be checked
	* @return boolean if the square at (x,y) can be selected.
	**/
	public boolean canSelect(int x, int y) {
		if ((chosenPiece != null) && (chosenPiece.hasCaptured())) {
			xCoord = xSquare;
			yCoord = ySquare;
		}
		if (pieceMoved && !chosenPiece.hasCaptured()) {
			return false;
		}
		if (pieceAt(x,y) != null) {
			if (rightSide(x,y)) {
				if (chosenPiece != null && chosenPiece.hasCaptured()) {
					if (validMove(xCoord, yCoord, x, y)) {
						return true;
					}
					return false;
				}
				return true;
			}
		} else {
			if (pieceSelected) {
				if (validMove(xCoord, yCoord, x, y)) {
					return true;
				}
				if (chosenPiece.hasCaptured() && validMove(xCoord, yCoord, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/** Returns true if the piece being moved is a valid move.
	* @param xi is the initial x-position of the piece being moved.
	* @param yi is the initial y-position of the piece being moved.
	* @param xf is the final x-position of the piece being moved.
	* @param yf is the final y-position of the piece being moved.
	* @return boolean for whether the move is valid.
	**/
	private boolean validMove(int xi, int yi, int xf, int yf) {
	// if a piece exists at the position to select, return false.
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		if (chosenPiece.isKing()) {
			if ((canMoveForward(xi, yi, xf, yf)) || (canMoveBackward(xi, yi, xf, yf))) {
				if (!pieceMoved) {
					return true;
				}
			}
		}
		if (chosenPiece.isFire() && canMoveForward(xi, yi, xf, yf)) {
			return true;
		}
		if (!chosenPiece.isFire() && canMoveBackward(xi, yi, xf, yf)) {
			return true;
		}
		return false;
	}

	// canMoveForward and canMoveBackward can be combined for efficient coding

	/** Returns true if the piece can be moved forward from its initial position.
	* @param xi is the initial x-position of the piece being moved.
	* @param yi is the initial y-position of the piece being moved.
	* @param xf is the final x-position of the piece being moved.
	* @param yf is the final y-position of the piece being moved.
	* @return boolean for whether the move is valid.
	**/
	private boolean canMoveForward(int xi, int yi, int xf, int yf) {
		int xDist = xf - xi;
		int yDist = yf - yi;
		if (yDist < 0) {
			return false;
		}
		if ((yDist == 1) && (Math.abs(xDist) == 1)) {
			if (chosenPiece.hasCaptured()) {
				return false;
			}
			return true;
		}
		if ((yDist == 2) && (Math.abs(xDist) == 2)) {
			if (xDist == 2) {
				if ((pieceAt(xi + 1, yi + 1) != null) && !pieceAt(xi + 1, yi + 1).isFire()) {
					return true;
				}
			} else {
				if ((pieceAt(xi - 1, yi + 1) != null) && !pieceAt(xi - 1, yi + 1).isFire()) {
					return true;
				}
			}
		}
		return false;
	}

	/** Returns true if the piece can be moved backward from its initial position.
	* @param xi is the initial x-position of the piece being moved.
	* @param yi is the initial y-position of the piece being moved.
	* @param xf is the final x-position of the piece being moved.
	* @param yf is the final y-position of the piece being moved.
	* @return boolean for whether the move is valid.
	**/
	private boolean canMoveBackward(int xi, int yi, int xf, int yf) {
		int xDist = xf - xi;
		int yDist = yf - yi;
		if (yDist > 0) {
			return false;
		}
		if ((yDist == -1) && (Math.abs(xDist) == 1)) {
			if (chosenPiece.hasCaptured()) {
				return false;
			}
			return true;
		}
		if ((yDist == -2) && (Math.abs(xDist) == 2)) {
			if (xDist == 2) {
				if ((pieceAt(xi + 1, yi - 1) != null) && pieceAt(xi + 1, yi - 1).isFire()) {
					return true;
				}
				if ((pieceAt(xi + 1, yi - 1) != null) && chosenPiece.isKing()) {
					return true;
				}
			} else {
				if ((pieceAt(xi - 1, yi - 1) != null) && pieceAt(xi - 1, yi - 1).isFire()) {
					return true;
				}
				if ((pieceAt(xi - 1, yi - 1) != null) && chosenPiece.isKing()) {
					return true;
				}
			}
		}
		return false;
	}

	/** Assumes that there is a piece at (x,y). Checks if piece selected is on the right side.
	* @param x is the initial x-position of the piece being moved.
	* @param y is the initial y-position of the piece being moved.
	**/
	private boolean rightSide(int x, int y) {
		if ((pieceAt(x,y) == null) && (!pieceAt(x,y).hasCaptured())) {
			return false;
		}
		return fireTurn == pieceAt(x,y).isFire();
	}

	/** If the square (x,y) can be selected, select it for use during
	* piece movement.
	* @param x is the x-position of the location to be checked
	* @param y is the y-position of the location to be checked
	**/
	public void select(int x, int y) {
		xSquare = x;
		ySquare = y;
		if (pieceAt(x,y) != null) {
			xCoord = x;
			yCoord = y;
			chosenPiece = pieceAt(x,y);
			pieceSelected = true;
		} else {
			pieceMoved = true;
			chosenPiece.move(x, y);
		}
	}

	/** Checks whether the player can end their turn
	* @return a boolean returning whether the player can end their turn
	*/
	public boolean canEndTurn() {
		if (pieceMoved) {
			return true;
		} else if ((chosenPiece != null) && (chosenPiece.hasCaptured())) {
			return true;
		} else {
			return false;
		}
	}

	/** Causes player switching, ends current player's turn, handles all
	* end-of-turn actions.
	*/
	public void endTurn() {
		fireTurn = !fireTurn;
		if ((chosenPiece !=null) && (chosenPiece.hasCaptured())) {
			chosenPiece.doneCapturing();
		}
		chosenPiece = null;
		pieceMoved = false;
		pieceSelected = false;
		xCoord = 0;
		yCoord = 0;
		xSquare = 8;
		ySquare = 8;
	}

	/** Returns a string declaring the winner of the game, or a message
	* stating that there is no winner.
	* @return String as stated above.
	*/
	public String winner() {
		int pieceNumFire = 0;
		int pieceNumWater = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						pieceNumFire += 1;
					} else {
						pieceNumWater += 1;
					}
				}
			}
		}
		if ((pieceNumFire == 0) && (pieceNumWater == 0)) {
			return "No one";
		}
		if (pieceNumFire == 0) {
			return "Water";
		} else if (pieceNumWater == 0) {
			return "Fire";
		} else {
			return null;
		}
	}
}