import java.lang.Math;

public class Board {
// Main class for cs61b project 1 implementation

	// Stores size of NxN board
	private final static int boardSize = 8;
	private Piece[][] pieces;		// Rows go up i.e. lowest is 0; [row][column]
	private boolean fireTurn;
	private boolean moved;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private boolean multiCap;
	private int whiteSquare[];

	public static void main(String[] args) {
		// Setting up board
        StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);

		Board board = test(0);		// Change parameter to scenario number; 0 is default game
		startRunning(board); 
	}

	// Moves control out of main for testing reasons
	private static void startRunning(Board board) {
		while (board.winner() == null) {
			board.drawBoard();
			if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (board.canSelect(x, y))
            		board.select(x, y);
            }
            if (board.canEndTurn() && StdDrawPlus.isSpacePressed())
            	board.endTurn();
			StdDrawPlus.show(10);
		}
		board.drawBoard();
		StdDrawPlus.show(10);
	}

	// Method used for testing
	// 0 returns normal board
	private static Board test(int testing) {
		if (testing == 1) {
			Board b = new Board(true);
			b.place((new Piece(true, b, 1, 5, "pawn")), 1, 5);
			b.place((new Piece(false, b, 2, 6, "pawn")), 2, 6);
			b.place((new Piece(false, b, 4, 6, "pawn")), 4, 6);
			return b;
		}
		else if (testing == 2) {
			Board b = new Board(true);
			b.place((new Piece(true, b, 1, 3, "pawn")), 1, 3);
			b.place((new Piece(false, b, 2, 4, "pawn")), 2, 4);
			b.place((new Piece(false, b, 2, 6, "pawn")), 2, 6);
			b.place((new Piece(false, b, 4, 6, "pawn")), 4, 6);
			return b;
		}
		else if (testing == 3) {
			Board b = new Board(true);
			b.place((new Piece(true, b, 1, 1, "bomb")), 1, 1);
			b.place((new Piece(false, b, 2, 2, "pawn")), 2, 2);
			b.place((new Piece(false, b, 2, 4, "shield")), 2, 4);
			b.place((new Piece(false, b, 4, 2, "pawn")), 4, 2);
			b.place((new Piece(true, b, 3, 3, "pawn")), 3, 3);
			return b;
		}
		else if (testing == 4) {
			Board b = new Board(true);
			b.place((new Piece(false, b, 2, 2, "pawn")), 2, 2);			
			b.place((new Piece(true, b, 3, 1, "pawn")), 3, 1);
			b.place((new Piece(true, b, 5, 1, "pawn")), 5, 1);
			b.place((new Piece(true, b, 5, 5, "pawn")), 5, 5);
			return b;
		}
		else
			return (new Board(false));
	}

	// Constructor for Board class
	// shouldBeEmpty => start game with empty board
	public Board(boolean shouldBeEmpty) {
        // Setting up vars
        pieces = new Piece[boardSize][boardSize];
        fireTurn = true;
        moved = false;
        selectedPiece = null;
        multiCap = false;
        selectedX = 0;
        selectedY = 0;
        whiteSquare = new int[2];
        whiteSquare[0] = -1;
        whiteSquare[1] = -1;

        if(!shouldBeEmpty) {
	        // Add pawns
	        for(int i = 1; i < boardSize; i+=2)
	        {
	        	place((new Piece(true, this, i-1, 0, "pawn")), i-1, 0);
	        	place((new Piece(false, this, i, 7, "pawn")), i, 7);
	        }
	        // Add shields
	        for(int i = 0; i < boardSize; i+=2)
	        {
	        	place((new Piece(true, this, i+1, 1, "shield")), i+1, 1);
	        	place((new Piece(false, this, i, 6, "shield")), i, 6);
	        }
	        // Add bombs
	        for(int i = 1; i < boardSize; i+=2)
	        {
	        	place((new Piece(true, this, i-1, 2, "bomb")), i-1, 2);
	        	place((new Piece(false, this, i, 5, "bomb")), i, 5);
	        }
        }
	}

	// Actually draws the board
	private void drawBoard() {	
		for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
            	// Draw boxes
            	if (i == whiteSquare[0] && j == whiteSquare[1])
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);		// Handles selected box case
                else if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                // Draw pieces
                Piece p = pieceAt(i, j);
                if (pieceAt(i, j) != null) {
                	if (p.isFire()) {
                		if (p.isBomb()) {
                			if (p.isKing())
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    		else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    	}
                    	else if (p.isShield()) {
                    		if (p.isKing())
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    		else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    	}
                    	else {
                    		if (p.isKing())
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    		else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    	}
                	}
                	else {
                		if (p.isBomb()) {
                			if (p.isKing())
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    		else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    	}
                    	else if (p.isShield()) {
                    		if (p.isKing())
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    		else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    	}
                    	else {
                    		if (p.isKing())
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    		else
                    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    	}
                	}
                }
            }
        }
	}

	// Returns piece at (x, y) or null
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= boardSize || y < 0 || y >= boardSize)
			return null;		// Covers out of bounds case
		return pieces[y][x];
	}

	// Returns true if player can select a piece OR
	// if player has selected a piece and selects a valid move
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		// If player hasn't selected a piece yet and hasn't moved
		if (!moved && p != null)
		{
			if ((p.isFire() && fireTurn) || (!p.isFire() && !fireTurn))
				return true;
		}
		// If player has selected a piece and is trying to move it
		else if (p == null && (!moved || multiCap) && selectedPiece != null) {
			return validMove(x, y);
		}
		// Otherwise, can't select
		return false;
	}

	// Returns true if currently selected piece can move to (x, y)
	private boolean validMove(int x, int y) {
		// Check that the coords are valid
		if (x < 0 || x >= boardSize || y < 0 || y >= boardSize)
			return false;

		// Check if space is empty
		if (pieceAt(x, y) != null)
			return false;

		// Attempting capture
		if (Math.abs(y - selectedY) == 2 && Math.abs(x - selectedX) == 2) {
			// Check if moving forward if not king
			if (selectedPiece.isFire() && !selectedPiece.isKing())
				if (y < selectedY)
					return false;
			if (!selectedPiece.isFire() && !selectedPiece.isKing())
				if (y > selectedY)
					return false;

			// Check that there's a piece in the middle
			if (x > selectedX && y > selectedY)
				if (pieceAt(x-1, y-1) != null && pieceAt(x-1, y-1).isFire() != fireTurn)
					return true;
			if (x > selectedX && y < selectedY)
				if (pieceAt(x-1, y+1) != null && pieceAt(x-1, y+1).isFire() != fireTurn)
					return true;
			if (x < selectedX && y > selectedY)
				if (pieceAt(x+1, y-1) != null && pieceAt(x+1, y-1).isFire() != fireTurn)
					return true;
			if (x < selectedX && y < selectedY)
				if (pieceAt(x+1, y+1) != null && pieceAt(x+1, y+1).isFire() != fireTurn)
					return true;

			return false;
		}
		// Attempting movement
		else if (Math.abs(y - selectedY) == 1 && Math.abs(x - selectedX) == 1 && !multiCap) {
			// Check if moving forward if not king
			if (selectedPiece.isFire() && !selectedPiece.isKing())
				if (y < selectedY)
					return false;
			if (!selectedPiece.isFire() && !selectedPiece.isKing())
				if (y > selectedY)
					return false;

			return true;
		}
		// Invalid move
		else
			return false;
	}

	// Either selects a piece in a square
	// OR moves the currently selected piece
	// OPTIONAL: Highlight square white
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		// For moving pieces
		if (p == null) {
			selectedPiece.move(x, y);
			multiCap = false;
			selectedX = x;
			selectedY = y;
			moved = true;
			// Check if can multicapture if p is a fire piece
			if (!selectedPiece.isBomb() && selectedPiece.hasCaptured() && 
				((selectedPiece.isFire() && (validMove(x+2, y+2) || validMove(x-2, y+2))) || 
				(selectedPiece.isFire() && selectedPiece.isKing() && (validMove(x-2, y-2) || validMove(x+2, y-2))))) {
				multiCap = true;
				whiteSquare[0] = x;
				whiteSquare[1] = y;
			}
			// Check if can multicapture if p is a water piece
			else if (!selectedPiece.isBomb() && selectedPiece.hasCaptured() && 
					((!selectedPiece.isFire() && (validMove(x+2, y-2) || validMove(x-2, y-2))) || 
					(!selectedPiece.isFire() && selectedPiece.isKing() && (validMove(x-2, y+2) || validMove(x+2, y+2))))) {
				multiCap = true;
				whiteSquare[0] = x;
				whiteSquare[1] = y;
			}
			else {
				whiteSquare[0] = -1;
				whiteSquare[1] = -1;
			}
		}
		// For selecting a new piece
		else if (p != null) {
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
			whiteSquare[0] = x;
			whiteSquare[1] = y;
		}
	}
	// Place p at (x, y)
	// if out of bounds or p is null, do nothing
	// if spot already taken, replace old piece
	public void place(Piece p, int x, int y) {
		pieces[y][x] = p;
	}

	// Removes piece from board
	// And returns the piece
	// If no piece or out of bounds: return null and print error
	public Piece remove(int x, int y) {
		Piece p = pieceAt(x, y);
		place(null, x, y);
		if (p == null)
			System.out.println("ERROR: Failed to remove piece.  Either the index is out of bounds or there is no piece to remove");
		return p;
	}

	// Returns true if a piece has moved or captured
	public boolean canEndTurn() {
		return moved;
	}

	// Called when turns end
	// Switches players, etc.
	public void endTurn() {
		fireTurn = !fireTurn;
        moved = false;
        selectedPiece.doneCapturing();
        selectedPiece = null;
        multiCap = false;
        selectedX = 0;
        selectedY = 0;
		whiteSquare[0] = -1;
		whiteSquare[1] = -1;
	}

	// Returns winner: "Fire", "Water", "No one", or null if game is in progress
	public String winner() {
		boolean fireRemain = false;
		boolean waterRemain = false;
		// Check every square
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (pieceAt(i, j) != null && pieceAt(i, j).isFire())
					fireRemain = true;
				else if (pieceAt(i, j) != null && !pieceAt(i, j).isFire())
					waterRemain = true;
				if (fireRemain && waterRemain)
					return null;		// If both players still have pieces, we can stop checking
			}
		}
		// Win conditions
		if (fireRemain && !waterRemain) {
			System.out.println("Fire Wins!");
			whiteSquare[0] = -1;
			whiteSquare[1] = -1;
			return "Fire";
		}
		else if (!fireRemain && waterRemain) {
			System.out.println("Water Wins!");
			whiteSquare[0] = -1;
			whiteSquare[1] = -1;
			return "Water";
		}
		else if (!fireRemain && !waterRemain) {
			System.out.println("No one wins");
			whiteSquare[0] = -1;
			whiteSquare[1] = -1;
			return "No one";
		}
		return null;
	}
}