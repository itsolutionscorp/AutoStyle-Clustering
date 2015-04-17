public class Board {

    private Piece[][] pieces;
    private boolean hasMoved;
    private boolean isFireTurn;
    private boolean hasSelected;
    private boolean wasJustCrowned;
    private boolean isCrowned;
    private boolean hasCaptured;
    private Piece piece;
    private int pieceX;
    private int pieceY;

	// starts a GUI supported version of the game
	public static void main (String [] args) {
		// Board board = new Board(true);
		Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

		while(true) {
            board.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(board.canSelect((int) x, (int) y))
                	board.select((int) x, (int) y);
            }
            if (StdDrawPlus.isSpacePressed())
            	board.endTurn();
            StdDrawPlus.show(100);
        }
	}

	// The constructor for Board. If shouldBeEmpty is true, 
	// initializes an empty Board. Otherwise, initializes a 
	// Board with the default configuration. Note that an empty 
	// Board could possibly be useful for testing purposes.
	public Board(boolean shouldBeEmpty) {
		hasMoved = false;
		hasSelected = false;
		wasJustCrowned = false;
		isCrowned = false;
		hasCaptured = false;
		piece = null;
		pieceX = -1;
		pieceY = -1;
		isFireTurn = true;
		pieces = new Piece[8][8];
		if(!shouldBeEmpty) {
			int i = 0;
			int j = 0;
			boolean fire = true;
			String type = "";
			while(j < pieces.length){
				if(j % 2 == 0){
					i = 0;}
				else{
					i = 1;}
				while(i < pieces[j].length){
					if(j == 0 || j == 7) {
						type = "pawn";}
					else if(j == 1 || j == 6){
						type = "shield";}
					else{
						type = "bomb";}
					if(j == 3){
						fire = false;
						j += 2;}
					pieces[i][j] = new Piece(fire, this, i, j, type);
					i += 2;
				}
				j += 1;
			}
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if(hasSelected && i == pieceX && j == pieceY) 
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = pieceAt(i,j);
                if (piece != null) {
                	String team = "water";
                	String king = "";
                	String type = "pawn";
                	if(piece.isFire()){team = "fire";}
                	if(piece.isKing()){king = "-crowned";}
                	if(piece.isBomb()){type = "bomb";}
                	else if(piece.isShield()){type = "shield";}
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-" + team + king + ".png", 1, 1);
                }
            }
        }
    }

	// Gets the piece at position (x, y) on the board, or 
	// null if there is no piece. If (x, y) are out of bounds, 
	// returns null.
	public Piece pieceAt(int x, int y) {
		if(x >= 0 && x < 8 && y >= 0 && y < 8)
			return pieces[x][y];
		return null;
	}

	//returns true if the piece at (xi, yi) can either move to
	//(xf, yf) or capture to (xf, yf), strictly from a 
	//geometry/piece-race point of view. validMove does not 
	//need to take into consideration whose turn it is or if a 
	//move has already been made, but rather can.
	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece myPiece = pieceAt(xi, yi);
		Piece newPlace = pieceAt(xf, yf);
		if(newPlace == null && myPiece != null){
			int i = 0;
			int j = 0;
			if(myPiece.isFire() || myPiece.isKing()){
				//move
				if(!hasCaptured && yf - yi == 1){
					if(xf - xi == 1 || xi - xf == 1)
						return true;
				}
				//capture
				else if(yf - yi == 2){
					if(xi - xf == 2 || xf - xi == 2){
						j = yi + 1;
						if(xi > xf)
							i = xi - 1;
						else
							i = xi + 1;
						if(pieceAt(i, j) != null && pieceAt(i, j).isFire() != isFireTurn)
							return true;
			}}}
			if(!myPiece.isFire() || myPiece.isKing()){
				//move
				if(!hasCaptured && yi - yf == 1){
					if(xf - xi == 1 || xi - xf == 1)
						return true;
				}
				//capture
				if(yi - yf == 2){
					if(xi - xf == 2 || xf - xi == 2){
						j = yi - 1;
						if(xi > xf)
							i = xi - 1;
						else
							i = xi + 1;
						if(pieceAt(i, j) != null && !(pieceAt(i, j).isFire() == isFireTurn))
							return true;
		}}}}
		return false;
	}

	//  Returns true if the square at (x, y) can be selected.
	//  A square with a piece may be selected if it is the 
	//  corresponding player’s turn and one of the following 
	//  is true:
	// 		The player has not selected a piece yet.
	// 		The player has selected a piece, but did not move it.
	// An empty square may be selected if one of the 
	// following is true:
	// 		During this turn, the player has selected a Piece 
	// 		which hasn’t moved yet and is selecting an empty 
	// 		spot which is a valid move for the previously 
	// 		selected Piece.
	// 		During this turn, the player has selected a Piece, 
	// 		captured, and has selected another valid capture 
	// 		destination. When performing multi-captures, you 
	// 		should only select the active piece once; all other 
	// 		selections should be valid destination points.
	// autograder
	// Crowned piece should not be able to be selected or move 
	// on the same turn it was crowned unless a multi-capture 
	// is happening
	public boolean canSelect(int x, int y) {
		if(pieceAt(x, y) != null) {
			if(pieceAt(x, y).isFire() == isFireTurn && !hasCaptured && !hasMoved) {
				if(!wasJustCrowned)
						return true;
			}
		}
		else if(hasSelected) {
			if((!hasMoved && !wasJustCrowned) || hasCaptured) {
				if(validMove(pieceX, pieceY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	// Selects the square at (x, y). This method assumes 
	// canSelect (x,y) returns true. Optionally, it is 
	// recommended to color the background of the selected 
	// square white on the GUI via the pen color function.
	// For any piece to perform a capture, that piece must 
	// have been selected first. If you select a square with 
	// a piece, you are prepping that piece for movement. 
	// If you select an empty square (assuming canSelect 
	// returns true), you should move your most recently 
	// selected piece to that square.
	public void select(int x, int y) {
		if(pieceAt(x, y) == null) {
			hasMoved = true;
			piece.move(x, y);
			if(!isCrowned && piece.isKing())
				wasJustCrowned = true;
			hasCaptured = piece.hasCaptured();
		}
		else {
			piece = pieceAt(x, y);
			hasSelected = true;
			isCrowned = piece.isKing();
		}
		pieceX = x;
		pieceY = y;
	}

	// Places p at (x, y). If (x, y) is out of bounds or if p 
	// is null, does nothing. If another piece already exists 
	// at (x, y), p will replace that piece. (This method is 
	// potentially useful for creating specific test 
	// circumstances.)
	public void place(Piece p, int x, int y) {
		if(p != null && x < pieces.length && y < pieces[x].length){
			pieces[x][y] = p;
		}
	}

	// Executes a remove. Returns the piece that was removed. 
	// If the input (x, y) is out of bounds, returns null and 
	// prints an appropriate message. If there is no piece at 
	// (x, y), returns null and prints an appropriate message.
	public Piece remove (int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7){
			System.out.println("(" + x + ", " + y + ") is out of bounds");
			return null;
		}
		Piece piece = pieceAt(x, y);
		if(piece == null){
			System.out.println("No piece at (" + x + ", " + y + ")");
			return null;
		}
		pieces[x][y] = null;
		return piece;
	}

	// Returns whether or not the the current player can end 
	// their turn. To be able to end a turn, a piece must have 
	// moved or performed a capture.
	public boolean canEndTurn() {
		return hasMoved || (piece != null && piece.hasCaptured());
	}

	// Called at the end of each turn. Handles switching of 
	// players and anything else that should happen at the end 
	// a turn.
	public void endTurn() {
		if(canEndTurn()){
			hasMoved = false;
			hasSelected = false;
			wasJustCrowned = false;
			hasCaptured = false;
			piece.doneCapturing();
			piece = null;
			pieceX = -1;
			pieceY = -1;
			isFireTurn = !isFireTurn;
		}
	}

	// Returns the winner of the game. "Fire", "Water", "No 
	// one" (tie / no pieces on the board), or null if the 
	// game is not yet over. Assume there is no stalemate 
	// situation in which the current player has pieces but 
	// cannot legally move any of them (In this event, just 
	// 	leave it at null). Determine the winner solely by 
	// the number of pieces belonging to each team.
	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++) {
				Piece piece = pieceAt(i, j);
				if(piece != null){
					if(piece.isFire()){
						fireCount++;
					}
					else{
						waterCount++;
					}
				}
			}
		}
		if(fireCount == 0){
			if(waterCount == 0){
				return "No One";
			}
			return "Water";
		}
		if(waterCount == 0){
			return "Fire";
		}
		return null;
	}
}