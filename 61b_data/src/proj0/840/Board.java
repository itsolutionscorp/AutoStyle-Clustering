
public class Board {
	
	private Piece[][] pieceArray;
	private boolean hasMoved;
	private boolean hasSelected;	
	private Piece selectedPiece;
	private int selectedPieceX;
	private int selectedPieceY;
	private int playerTurn;		
	private boolean bombCaptured;
		
	
	/* Constructor for Board object. If shouldBeEmpty is true, initializes empty Board. 
	 * Otherwise initialize a board with default settings.
	 */
	public Board(boolean shouldBeEmpty) {
		bombCaptured = false;		
		playerTurn = 0;
		hasSelected = false;		
		hasMoved = false;
		selectedPiece = null;
		selectedPieceX = -1;
		selectedPieceY = -1;
		pieceArray = new Piece[8][8];	
		
		/* Fills the two dimensional array of Pieces */
		if(!shouldBeEmpty) {			
			for(int col = 1; col <= 7; col += 2) {				
				pieceArray[col][7] = new Piece(false, this, col, 7, "pawn");			
				pieceArray[col - 1][6] = new Piece(false, this, col - 1, 6, "shield");		
				pieceArray[col][5] = new Piece(false, this, col, 5, "bomb");			
				pieceArray[col - 1][2] = new Piece(true, this, col - 1, 2, "bomb");				
				pieceArray[col][1] = new Piece(true, this, col, 1, "shield");			
				pieceArray[col - 1][0] = new Piece(true, this, col - 1, 0, "pawn");			
			}
		}
			
	}
	

	/* Draws the board based on the pieceArray */
	private void drawBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(row + .5, col + .5, .5);                
                if (pieceAt(row, col) != null) {                	
                	if(row == selectedPieceX && col == selectedPieceY) {                		
                		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
            			StdDrawPlus.setPenRadius(0.006);
            			StdDrawPlus.square(row + 0.5, col + 0.5, 0.47);
            			StdDrawPlus.setPenRadius();
                	}                	
                    StdDrawPlus.picture(row + .5, col + .5, pieceFilePath(pieceAt(row, col)), 1, 1);
                }
            }
        }
        StdDrawPlus.show(15);
    }
	
	/* pieceAt() returns the piece at the given (x, y) coordinate */
	public  Piece pieceAt(int x, int y) {
		if(x >7 || x < 0 || y > 7 || y < 0) {
			return null;
		}
		return pieceArray[x][y];
	}
	
	/* Returns the file path of a given Piece */
	private String pieceFilePath(Piece p) {	
		String imagePath = "img/";		
		/* Adds appropriate type to imagePath */
		if(p.isBomb()) {
			imagePath += "bomb-";
		}
		else if(p.isShield()) {
			imagePath += "shield-";
		}
		else {
			imagePath += "pawn-";
		}
		/* Adds appropriate team to imagePath */
		if(p.isFire()) {
			imagePath += "fire";
		}
		else {
			imagePath += "water";
		}
		/* Adds king to imagePath if necessary */ 
		if(p.isKing()) {
			imagePath += "-crowned";
		}
		/* Completes imagePath */
		imagePath += ".png";
		return imagePath;
	}
	
	/* canSelect() checks if the piece at (x, y) is selectable.
	 * It is selectable if it is a piece where:
	 * - The player has not yet selected a piece
	 * - The player has selected a piece, but has not moved it
	 * It is an empty space where:
	 * - A piece has been selected but not moved and the space is a valid move
	 * - The player has captured a piece and can move to the spot by capturing another piece
	 */
	public boolean canSelect(int x, int y) {	
		if(x >7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		/* Cases for when location checked contains a piece */
		if(pieceArray[x][y] != null) {
			// If the piece selected is not one the right team, return false
			if(pieceArray[x][y].side() != playerTurn) {
				return false;
			}
			// If a piece has not been selected yet, return true
			if(!hasSelected) {
				return true;
			}
			// If a piece has been selected but not moved, return true
			else if(!hasMoved) {
				return true;
			}
			// If neither condition is satisfied, return false
			return false;
		}
		/* Cases for when location checked is empty */
		else {
			// If a piece has been selected but has not been moved, and the location is a valid move, return true 
			if(hasSelected && !hasMoved && validMove(selectedPieceX, selectedPieceY, x, y, selectedPiece)) {				
				return true;				
			}
			// If a piece has moved and has captured, and the location is a valid move, return true
			else if(selectedPiece != null && hasMoved && selectedPiece.hasCaptured() && validMove(selectedPieceX, selectedPieceY, x, y, selectedPiece)) {
				return true;
			}
			return false; 
		}
	}
	
	/* validMove() returns if a piece at (xi, yi) can move to a position (xf, yf) */ 
	private boolean validMove(int xi, int yi, int xf, int yf, Piece p) {
		if(xf >7 || xf < 0 || yf > 7 || yf < 0) {
			return false;
		}
		else if(Math.abs(xf - xi) != Math.abs(yf - yi)) {
			return false;
		}		
		else if(yf - yi < 0 && p.isFire() && !p.isKing()) {
			return false;
		}
		else if(yf - yi > 0 && !p.isFire() && !p.isKing()) {
			return false;
		}
		else if(Math.abs(xf - xi) == 1) {
			if(selectedPiece.hasCaptured()) {
				return false;
			}
			if(pieceArray[xf][yf] == null) {
				return true;
			}
			return false;
		} 
		else if(Math.abs(xf - xi) == 2) { 
			double dXf = xf;
			double dYf = yf;
			double dXi = xi;
			double dYi = yi;
			double midX = (dXf / 2) + (dXi / 2);
			double midY = (dYf / 2) + (dYi / 2);
			Piece mid = pieceArray[(int)midX][(int)midY];
			if((pieceArray[xf][yf] == null) && (mid != null) && (mid.isFire() != p.isFire()) && !bombCaptured) { 
				return true;
			}
			return false;
		}
		return false;
	}
	
	/* Selects a piece at (x, y) */
	public void select(int x, int y) {		
		hasSelected = true;
		// If there is a piece at (x, y), then select that piece
		if(pieceAt(x, y) != null) {				
			selectedPiece = pieceArray[x][y];
			selectedPieceX = x;
			selectedPieceY = y;
		}
		// If the selected location is empty, move the selected piece to that location
		else {
			hasMoved = true;
			selectedPiece.move(x, y);						
			selectedPiece = pieceAt(x, y);
			selectedPieceX = x;
			selectedPieceY = y;
			if(selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
				bombCaptured = true;
				remove(x, y);
			}						
		}
	}
	
	/* Places piece p at (x, y). If (x, y) is out of bounds it does nothing.
	 * If another piece is at (x, y) it replaces the piece
	 */
	public void place(Piece p, int x, int y) {
		if( x >7 || x < 0 || y > 7 || y < 0) {
			//DO NOTHING
		}
		else {								
			pieceArray[x][y] = p;			
		}
	}
	
	/* Removes the piece at (x, y) and returns the piece removed
	 * If (x, y) is OB it returns null and prints a message 
	 * If (x, y) is empty it returns null and prints a message	 
	 */
	public Piece remove(int x, int y) {
		if( x >7 || x < 0 || y > 7 || y < 0) {
			System.out.println("Out of Bounds");
			return null;
		}
		if(pieceAt(x,y) == null) {
			System.out.println("No Piece at this location"); 
			return null;
		}
		Piece pieceRemoved = pieceArray[x][y];
		pieceArray[x][y] = null;				
		return pieceRemoved;		
	}
	
	/* canEndTurn() checks if the player can end his/her turn:
	 * The player must have moved or captured to end his/her turn
	 */
	public boolean canEndTurn() {
		if(bombCaptured) {
			return true;
		}
		if(selectedPiece == null) {
			return false;
		}
		if(hasMoved || selectedPiece.hasCaptured()) {
			return true;
		}
		return false;
	}
	
	/* endTurn() is called at the end of the term and handles switching turns */
	public void endTurn() {
		hasMoved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		hasSelected = false;
		playerTurn = (playerTurn +1) % 2;
		selectedPieceX = -1;
		selectedPieceY = -1;
		bombCaptured = false;
	}
	
	/* Returns the winner of the game: "Fire", "Water", or "No one" */
	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for(int col = 0; col <= 7; col ++) {
			for(int row  = 0; row <= 7; row++) {
				if(pieceAt(col, row) != null) {
					Piece p = pieceAt(col, row);
					if(p.isFire()) {
						fireCount ++;
					}
					else {
						waterCount ++;
					}
				}
			}
		}
		if(fireCount == 0 && waterCount == 0) {
			return "No one";
		}
		else if(fireCount != 0 && waterCount != 0) {
			return null;
		}
		else if(fireCount == 0) {
			return "Water";
		}
		return "Fire";
	}
	
	/* Runs the game */
	private void gameRunner() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		drawBoard();
		boolean gameOver = false;
		while(!gameOver) {
			drawBoard();			
			if(StdDrawPlus.mousePressed()) {
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();				
				if(canSelect(x, y)) {
					select(x, y);								
				}			
			}			
			/* Checks if the player can end his/her turn when space bar is pressed. Ends turn if true */
			else if(StdDrawPlus.isSpacePressed()) {
				if(canEndTurn()) {
					endTurn();
				}
			}	
			if(winner() != null) {
				gameOver = true;
			}
		}
		drawBoard();
		System.out.println(winner());
	}
	
	public static void main(String[] args) {
		Board b = new Board(false);
		b.gameRunner();			
		}
}
