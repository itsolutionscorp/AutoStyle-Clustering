/**
*	@author Jagdeep Manik
*/

import java.awt.Color;

public class Board {
	private static final Color RED = new Color(140, 50, 50);
	private static final Color GRAY = new Color(50, 50, 50);
	private static final Color HIGHLIGHT = new Color(252, 255, 219);

	private Piece[][] pieces;
	private Piece selected;
	private int selectX; private int selectY;
	private int turn;
	private boolean pieceMoved;

	/* Check bounds */
	private boolean checkBounds(int x, int y) {
		return ((x > -1) && (x < 8) && (y > -1) && (y < 8));
	}

	/* Gets the path of the image for a piece */
	private String getPieceImage(Piece p) {
		String path = "img/";
		if (p.isBomb()) {
			path += "bomb";
		} else if (p.isShield()) {
			path += "shield";
		} else {
			path += "pawn";
		}
		if (p.isFire()) {
			path += "-fire";
		} else {
			path += "-water";
		}
		if (p.isKing())
			path += "-crowned";
		return (path + ".png"); 
	}

	/* Fills a square with the appropriate color. */
	private void fillSquare(int x, int y, boolean highlight) {
		if (highlight)
			StdDrawPlus.setPenColor(HIGHLIGHT);
		else if ((x + y) % 2 == 0) 
			StdDrawPlus.setPenColor(GRAY);
		else 				  
			StdDrawPlus.setPenColor(RED);

		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
	}

	/* Retrieves a piece's position. Have to use a linear search
	 * since we cannot place getters() in Piece.java, and I assume
	 * we aren't allowed to use list/map/dictionary. */
	private int[] getPiecePosition(Piece p) {
		int[] pos = {-1, -1};
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (p == pieces[x][y]) {
					pos[0] = x; pos[1] = y;
					return pos;
				}
			}
		}
		return null;
	}

	/* Draws a piece at the point (x, y) */
	private void drawPiece(Piece p, int x, int y) {
		if (p == null)
			return;
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.picture(x + .5, y + .5, getPieceImage(p), 1, 1);
	}

	/* Draws the board and its pieces. */
	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fillSquare(i, j, (selected != null) && (selectX == i) && (selectY == j));
                drawPiece(pieces[i][j], i, j);
            }
        }
	}

	/* Sets up the initial configuration. */
	private void setupInitialConfiguration() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (((y == 0) && (x % 2 == 0)) || ((y == 7) && (x % 2 != 0))) {
					pieces[x][y] = new Piece(y==0, this, x, y, "pawn");
				}
				if (((y == 1) && (x % 2 != 0)) || ((y == 6) && (x % 2 == 0))) {
					pieces[x][y] = new Piece(y==1, this, x, y, "shield");
				}
				if (((y == 2) && (x % 2 == 0)) || ((y == 5) && (x % 2 != 0))) {
					pieces[x][y] = new Piece(y==2, this, x, y, "bomb");
				}
			}
		}
	}

	/**
	*	Constructs an 8x8 board. If shouldBeEmpty, then the board
	*	will initialize without pieces. Else, it will add pieces
	*	according to the default configuration.
	*/
	public Board(boolean shouldBeEmpty) {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
        pieces   = new Piece[8][8];
        selected = null;
        selectX = -1; selectY = -1; 
        turn = 0;
        pieceMoved = false;
        
        if (!shouldBeEmpty)
        	setupInitialConfiguration();

        drawBoard();
	}

	/**
	*	Returns the Piece at (x, y).
	*	Returns null if there is no piece.
	*/
	public Piece pieceAt(int x, int y) {
		if (!checkBounds(x, y))
			return null;
		return pieces[x][y];
	}

	/**
	*	Returns true if (x, y) can be selected.
	*	Spot with piece: 
	*		- True if no piece selected yet
	*		- True if no piece moved yet
	*	Empty spot:
	*		- True if selected piece and valid move
	*		- True for multi-capture.
	*/
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);

		/* Check boundaries and if red square */
		if ((!checkBounds(x, y))) //|| ((x + y) % 2 != 0))
			return false;

		if (selected == null) {
			return ((p != null) && (p.side() == turn) && (!pieceMoved));
		} else if ((!pieceMoved) && (p != null) && (p.side() == turn)) {
			return true;
		} else {
			return (validMove(selectX, selectY, x, y));
		}
	}

	/* Checks if you can move the piece at (xi, yi) to (xf, yf). */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((!checkBounds(xf, yf)) || (!checkBounds(xi, yi)) || (pieceAt(xf, yf) != null))
			return false;

		Piece p = pieceAt(xi, yi);
		if (p == null)
			return false;
		
		/* Distance between prev and next point */
		int dx = xf - xi; int abs_dx = Math.abs(dx);
		int dy = yf - yi; int abs_dy = Math.abs(dy);

		/* Base cases */
		if ((abs_dx != abs_dy) || (abs_dx > 2) || (abs_dy > 2))
			return false;
		if ((dy < 0) && (p.isFire()) && (!p.isKing()))
			return false;
		if ((dy > 0) && (!p.isFire()) && (!p.isKing()))
			return false;

		if (abs_dx == 2) {
			Piece inbetween = pieceAt((xi+xf)/2, (yi+yf)/2);
			if ((inbetween == null) || (inbetween.side() == turn))
				return false;

			if (pieceMoved)
				return p.hasCaptured();
			return true;
		}

		return (!pieceMoved);
	}

	/**
	*	Selects the square at (x, y), assuming canSelect(x, y) is true.
	*	Sets color of background square to signify selection.
	*/
	public void select(int x, int y) {
		/* Selected same point. */
		if ((selectX == x) && (selectY == y))
			return;

		/* If you haven't moved yet, but you selected a piece. */
		if ((!pieceMoved) && (selected != null) && (pieceAt(x, y) != null)) {
			selected = null;
		}

		if (selected == null) {
			selected = pieceAt(x, y);
			selectX = x; selectY = y;
		} else {
			selected.move(x, y);
			selectX = x; selectY = y;
			pieceMoved = true;
		}

		if ((selected.hasCaptured()) && (selected.isBomb())) {
			selected = null;
			selectX = -1; selectY = -1;
		}
	}

	/**
	*	Places the piece at (xc, yc). Does nothing if p is null or if (x, y)
	*	is out of bounds. If a piece exists at (xc, yc), p will replace it.
	*/
	public void place(Piece p, int xc, int yc) {
		if ((p == null) || (!checkBounds(xc, yc)))
			return;

		/* Clear the old position. */
		int[] pos = getPiecePosition(p);
		if (pos != null) {
			remove(pos[0], pos[1]);
		}

		pieces[xc][yc] = p;
	}

	/**
	*	Removes a piece from (x, y) and returns it. Returns null and prints
	*	an error if (x, y) is out of bounds or if there is no piece at (x, y).
	*/
	public Piece remove(int x, int y) {
		if (!checkBounds(x, y)) {
			System.out.println("[Error][Board.remove] Attempted to remove piece outside bounds.");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("[Error][Board.remove] Attempted to remove null piece.");
			return null;
		}
		Piece result = pieces[x][y];
		pieces[x][y] = null;
		return result;
	}

	/**
	*	Returns whether the current player can end their turn.
	*	A piece must have moved or performed a capture.
	*/
	public boolean canEndTurn() {
		return (pieceMoved);
	}

	/**
	*	Called at the end of each turn. Switches players, etc.
	*/
	public void endTurn() {
		turn = (turn == 0) ? 1 : 0;
		if (selected != null) {
			selected.doneCapturing();
			selected = null;
			selectX = -1; selectY = -1;
		}
		pieceMoved = false;
	}

	/**
	*	Returns the winner of the game: "Fire", "Water", "No one", or null.
	*	- Fire pieces on board only: "Fire"
	*	- Water pieces on board only: "Water"
	*	- No pieces on board: "No one"
	*	- Game still in progress: null
	*/
	public String winner() {
		int[] pieceCount = {0, 0};
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (pieces[x][y] != null) {
					pieceCount[pieces[x][y].side()] += 1;
				}
			}
		}
		if ((pieceCount[0] > 0) && (pieceCount[1] > 0))
			return null;
		if ((pieceCount[0] > 0) && (pieceCount[1] == 0))
			return "Fire";
		if ((pieceCount[0] == 0) && (pieceCount[1] > 0))
			return "Water";
		return "No one";
	}

	/**
	*	Starts the GUI for the game.
	*/
	public static void main(String[] args) {
		Board base = new Board(false);

		while (true) {
			base.drawBoard();
        	if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
           		double y = StdDrawPlus.mouseY();
            	int xc = (int) x; int yc = (int) y;
            	if (base.canSelect(xc, yc)) {
                	base.select(xc, yc);
            	}
        	}
        	if ((StdDrawPlus.isSpacePressed()) && (base.canEndTurn())) {
        		base.endTurn();
        	}            
        	if ((StdDrawPlus.isNPressed())) {
        		base = new Board(false);
        	}
       		StdDrawPlus.show(20);
        }
	}
}