
/**
 * BombCheckers by Nathan Braun-Krafft
 * 
 * BombCheckers is a spin-off of the popular checkers game.
 * Instead of each player having just pawns, players also get bombs
 * and shields. When a bomb jumps over another piece it will instantly
 * explode removing all of the pieces that surround it, excluding shields.
 * 
 * Rules:
 * 	1. Fire takes the first turn
 *  2. The game continues until one side is without pieces
 *  3. A player can only move a piece diagonally, and unless
 *  	the piece has been "Kinged" then it cannot move backward.
 *  4. To "King" a piece it must reach the very last row of the opposite side.
 *  5. Pieces an multi-capture other pieces.
 */
public class Board {
	private static final int BOARD_SIZE = 8;
	private static final double DX = 0.5, DY = 0.5;
	private Piece[][] pieces;
	private boolean firesTurn;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved;
	
	public static void main(String[] args) {
		Board board = new Board(false);
		board.play();
	}
		
	/**
	 * Creates a new checkers board and
	 * calls the working constructor, causing it to display instantly.
	 * @param shouldBeEmpty if the board should be constructed 
	 * without pieces but the board will not play normally. (Good for testing)
	 */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
		firesTurn = true;
		hasMoved = false;
		selectedX = -1;
		selectedY = -1;
		selectedPiece = null;
		
		if(!shouldBeEmpty) {
			fillRow(0, true, "Pawn", 0);
			fillRow(1, true, "Shield", 1);
			fillRow(2, true, "Bomb", 0);
			
			fillRow(5, false, "Bomb", 1);
			fillRow(6, false, "Shield", 0);
			fillRow(7, false, "Pawn", 1);
		}
	}
	
	/**
	 * Fills a row in the grid with a specific type of pieces
	 * @param row the row to generate
	 * @param isFire if the pieces are fire or water
	 * @param type "Bomb", "Shield", or "Pawn"
	 * @param offset if the pieces should be shifted to the right
	 */
	private void fillRow(int row, boolean isFire, String type, int offset) {
		if(!isWithinBounds(row, row))
			return;
		
		for(int i = offset; i < pieces.length; i+=2) {
			pieces[i][row] = new Piece(isFire, this, i, row, type);
		}
	}
	
	/**
	 * Starts a loop that continuously draws the
	 * pieces onto the board. This method handles any
	 * key/mouse action listening.
	 */
	private void play() {
		StdDrawPlus.setScale(0, BOARD_SIZE);
		while(winner() == null) {
            if(StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                
                if(canSelect(x, y)) {
                	select(x, y);
                }
            	
            }        
            else if(StdDrawPlus.isSpacePressed() && canEndTurn()) {
            	endTurn();
            }
            draw();
            StdDrawPlus.show(50);
		}
	}
	
	/**
	 * Attempts to redraw the entire board,
	 * all of the pieces on the board, and
	 * handle coloring a tile white if it had 
	 * already been selected.
	 */
	private void draw() {
		for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                
                StdDrawPlus.filledSquare(i + DX, j + DY, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	StdDrawPlus.picture(i + DX, j + DY, getImage(pieces[i][j]), 1, 1);
                }
            }
        }
		
		/* If a bomb has already captured then it has disappeared */
        if(selectedPiece != null && !(selectedPiece.isBomb() && selectedPiece.hasCaptured())) {
        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(selectedX + DX, selectedY + DY, 0.5);
        	StdDrawPlus.picture(selectedX + DX, selectedY + DY, getImage(selectedPiece), 1, 1);
        }
	}
	
	/**
	 * Returns the piece at x y
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return a piece or null
	 */
	public Piece pieceAt(int x, int y) {
		if(!isWithinBounds(x, y))
			return null;
		return pieces[x][y];
	}
	
	/**
	 * Determines if the piece at x y can be selected
	 * for a future move. A piece can be selected if they
	 * have not previously selected a piece, or they had selected one
	 * but had not moved it.
	 * 
	 * An empty space can be selected if the player has not moved
	 * yet, and the selected spot is a valid move for the
	 * currently selected piece.
	 * 
	 * Or an empty space can be selected if the player had
	 * already moved a piece, captured an enemy, and is moving
	 * this piece to capture another enemy.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if it can be selected
	 */
	public boolean canSelect(int x, int y) {
		if(!isWithinBounds(x, y))
			return false;
		Piece piece = pieceAt(x, y);
		if(piece != null) {
			if(firesTurn == piece.isFire()) {
				if(selectedPiece == null || !hasMoved)
					return true;
			}
		}
		else {
			if(selectedPiece != null && !hasMoved && validMove(selectedX, selectedY, x, y))
				return true;
			else if(selectedPiece != null 
					&& hasMoved 
					&& selectedPiece.hasCaptured() 
					&& validMove(selectedX, selectedY, x, y))
				return true;
		}
		return false;
 	}
	
	/**
	 * Checks if the piece located at (xi,yi) is able
	 * to move to the space located at (xf,yf) according
	 * to the rules of BombCheckers.
	 * @param xi the 1st x coordinate
	 * @param yi the 1st y coordinate
	 * @param xf the 2nd x coordinate
	 * @param yf the 2nd y coordinate
	 * @return true if the piece can move.
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if(!isWithinBounds(xi, yi) || !isWithinBounds(xf, yf))
			 return false;
		if(selectedPiece == null)
			return false;
		if(xi == xf || yi == yf)
			return false;
		
		int diffX = xf - xi;
		int diffY = yf - yi;
		Piece oldPiece = pieceAt(xi, yi);
		Piece newPiece = pieceAt(xf, yf);
		
		// They should never move from a a blank space, or directly into a piece
		if(oldPiece == null || newPiece != null)
			return false;
		
		// The diagonal movement must be acceptable with in 1 or 2 spaces
		if(Math.abs(diffX) != Math.abs(diffY))
			return false;
		
		if(Math.abs(diffY) > 2)
			return false;
		
		// If a piece has already captured, it can only attempt to jump again
		if(oldPiece.hasCaptured() && Math.abs(diffY) != 2)
			return false;
		
		// They must be trying to jump a piece and remove it
		if(Math.abs(diffY) == 2) {
			int midX = (xf + xi) / 2;
			int midY = (yf + yi) / 2;
			Piece midPiece = pieceAt(midX, midY);
			if(midPiece == null || midPiece.isFire() == firesTurn)
				return false;
		}
			
		if(!oldPiece.isKing()) {
			if(firesTurn && diffY < 0)
				return false;
			else if(!firesTurn && diffY > 0)
				return false;				
		}
		return true;
	}
	
	/**
	 * Selects the tile or piece that is at coordinate x, y.
	 * If the tile contains a piece then that piece is now the
	 * selected piece. If the tile was empty then we attempt and
	 * we have a selectedTile, then we attempt to move that piece.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	public void select(int x, int y) {
		if(!isWithinBounds(x, y))
			return;
		Piece locPiece = pieceAt(x, y);
		if(locPiece != null) {
			selectedPiece = locPiece;
			selectedX = x;
			selectedY = y;
			return;
		}
		else if(selectedPiece != null) {
			selectedPiece.move(x, y);
			Piece movedPiece = pieceAt(x, y);
			
			// It must have been a bomb explosion
			if(movedPiece != null) {
				selectedPiece = movedPiece;
				selectedX = x;
				selectedY = y;
			}
			hasMoved = true;
		}
	}
	
	/**
	 * Places a piece at a specific x y location on the board.
	 * If an old piece exists at that location then it is removed.
	 * @param p the piece to place
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void place(Piece p, int x, int y) {
		if(p == null || !isWithinBounds(x, y))
			return;
		pieces[x][y] = p;
	}
	
	/**
	 * Attempts to remove a piece at x, y from the board.
	 * Will print error messages if the location is out of
	 * bounds, or the piece is null.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the piece that was removed or null
	 */
	public Piece remove(int x, int y) {
		if(!isWithinBounds(x, y)) {
			System.out.println("Remove: can't remove from an out of bounds index");
			return null;
		}
		
		Piece piece = pieceAt(x, y);
		if(piece == null) {
			System.out.println("Remove: can't remove a null piece");
		}
		pieces[x][y] = null;
		return piece;
	}
	
	/**
	 * Determines if the current player is able
	 * to end their turn. To be able to end the turn
	 * the player must have actually moved a piece.
	 * @return
	 */
	public boolean canEndTurn() {
		if(hasMoved)
			return true;
		return false;
	}
	
	/**
	 * Ends the current players turn and resets
	 * the currently selectedpiece to null.
	 */
	public void endTurn() {
		firesTurn = !firesTurn;
		if(selectedPiece != null)
			selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedX = -1;
		selectedY = -1;
		hasMoved = false;
	}
	
	/**
	 * Determines the winner of the game.
	 * @return "Fire", "Water", "No one", or null if the game is still in progress.
	 */
	public String winner() {
		boolean hasFire = false, hasWater = false;
		for(Piece[] row : pieces) {
			for(int i = 0; i < row.length; i++) {
				Piece piece = row[i];
				if(piece != null) {
					if(piece.isFire())
						hasFire = true;
					else
						hasWater = true;
				}
			}
		}
		
		if(hasFire && !hasWater)
			return "Fire";
		else if(hasWater && !hasFire)
			return "Water";
		else if(!hasFire && !hasWater)
			return "No one";
		// Water and Fire pieces are on board.
		else
			return null;
	}
	
	/**
	 * Checks if a location is within the bounds
	 * of this board.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if x and y are within bounds.
	 */
	private boolean isWithinBounds(int x, int y) {
		return x >= 0 && y >= 0 && x <= BOARD_SIZE && y < BOARD_SIZE;
	}
	
	/**
	 * Returns the corresponding image for a piece.
	 * @param piece the board piece
	 * @return a string containing the location to the image.
	 */
	private String getImage(Piece piece) {
		if(piece == null)
			return "";
		String img = "img/";
		
		if(piece.isBomb())
			img += "bomb-";
		else if(piece.isShield())
			img += "shield-";
		else
			img += "pawn-";
		
		if(piece.isFire())
			img += "fire";
		else
			img += "water";
		
		if(piece.isKing())
			img += "-crowned";
		img += ".png";	
		return img;
	}
}
