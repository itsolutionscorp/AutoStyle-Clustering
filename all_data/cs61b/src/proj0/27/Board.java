/** Creates a board that simulates a checkers game. */

public class Board {

	private static final String[] imageNames = {"img\\pawn-fire.png", "img\\pawn-fire-crowned.png", "img\\shield-fire.png", "img\\shield-fire-crowned.png", "img\\bomb-fire.png", 
	"img\\bomb-fire-crowned.png", "img\\pawn-water.png", "img\\pawn-water-crowned.png", "img\\shield-water.png", "img\\shield-water-crowned.png", "img\\bomb-water.png", "img\\bomb-water-crowned.png"};

	private Piece[][] locations;	
	private boolean isEmpty;
	private int currentPlayer;
	private boolean hasMoved, hasSelected;
	private Piece selectedPiece;
	private int selectedX, selectedY;

	public Board(boolean shouldBeEmpty) {
		isEmpty = shouldBeEmpty;
		locations = new Piece[8][8];
		currentPlayer = 0;	
		hasMoved = false;
		hasSelected = false;
		selectedX = -1;
		selectedY = -1;	
		if (!isEmpty) {
			createPieces();
		}	
	}

	/** Creates a board instance and controls graphics and user I/O. */
	public static void main(String[] args) {
		Board board = new Board(false);
		StdDrawPlus.setCanvasSize();
		StdDrawPlus.setScale(0, 8); 
		board.setBoard();			
		while (true) {	
			board.setBoard();
			if (board.selectedX != -1 && board.selectedY != -1) {
				board.highlightSquare(board.selectedX, board.selectedY);
			}				
			board.drawPieces();				
			if (StdDrawPlus.mousePressed()) {
				int xCor = (int) StdDrawPlus.mouseX();
				int yCor = (int) StdDrawPlus.mouseY();						
				if (board.canSelect(xCor, yCor)) {					
					board.select(xCor, yCor);		
				}                
			}  
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
				}            		
			}      
			String winStr = board.winner();
			if (winStr != null) {
				System.out.println("Winner is " + winStr);
			}         	
			StdDrawPlus.show(10);
		}							
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	/** Performs turn end operations and changes players. */
	public void endTurn() {			
		currentPlayer = (currentPlayer + 1) % 2;
		hasMoved = false;
		hasSelected = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;		
		selectedX = -1;
		selectedY = -1;		
	}

	/** Selects a location and moves a piece if necessary. */
	public void select(int x, int y) {	
		if (notInBounds(x, y)) {
			return;
		}		
		selectedX = x;
		selectedY = y;
		if (pieceAt(x, y) != null) {			
			hasSelected = true;			
			selectedPiece = pieceAt(x, y);			
		}
		else if (pieceAt(x, y) == null) {											
			if (selectedPiece != null) {					
				hasMoved = true;				
				selectedPiece.move(x, y);
				if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
					selectedX = -1;
					selectedY = -1;									
				}				
			} 				
		} 
	}

	/** Determines if a location is a valid location for selection. */
	public boolean canSelect(int x, int y) {
		if (notInBounds(x, y)) {
			return false;
		}
		Piece piece = pieceAt(x, y);		
		if (piece != null) {				
			if (piece.side() != currentPlayer) {
				return false;
			}			
			else if (!hasSelected) {					
				return true;
			}
			else if (!hasMoved && hasSelected) {				
				return true;
			}			
			else {
				return false;
			}
		}
		else {			
			if (selectedPiece == null) {
				return false;
			}			
			else if (hasSelected && !hasMoved) {				
				return validMove(selectedX, selectedY, x, y);
			}			
			else if (hasSelected && selectedPiece.hasCaptured()) {					
				return validMove(selectedX, selectedY, x, y);
			}
			return false;
		}
	}
	
	/** 
		* Determines whether a piece at an initial location can make a valid move to a final location.*/
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (notInBounds(xi, yi) || notInBounds(xf, yf)) {
			return false;
		}
		Piece pieceI = pieceAt(xi, yi);
		Piece pieceF = pieceAt(xf, yf);	
		if (pieceI == null) {
			return false;
		}
		else if (pieceI.isKing()) {
			return validKingMove(xi, yi, xf, yf);
		}
		else {
			int yOff = yOffset(pieceI);
			if (yf - yi == yOff && (Math.abs(xf - xi) == 1)) {
				return (!pieceI.hasCaptured() && pieceF == null);
			}	
			else if (yf - yi == 2 * yOff && (Math.abs(xf - xi) == 2)) {
				pieceF = pieceAt(((xf - xi) / 2) + xi, yi + yOff);								
				if (pieceF == null) {
					return false;
				}
				return (pieceI.side() != pieceF.side());
			}
			return false;
		}	
	}

	/**
		* Determines if a king at an initial location can make a valid move to a final location. 
		*/
	private boolean validKingMove(int xi, int yi, int xf, int yf) {
		Piece pieceI = pieceAt(xi, yi);
		Piece pieceF = pieceAt(xf, yf);
		if (notInBounds(xi, yi) || notInBounds(xf, yf)) {
			return false;
		}
		else if (Math.abs(yf - yi) == 1 && (Math.abs(xf - xi) == 1)) {
			return (!pieceI.hasCaptured() && pieceF == null);
		}	
		else if (Math.abs(yf - yi) == 2 && (Math.abs(xf - xi) == 2)) {
			pieceF = pieceAt(((xf - xi) / 2) + xi, yi + ((yf - yi) / 2));
			if (pieceF == null) {
				return false;
			}
			return (pieceI.side() != pieceF.side());
		}
		return false;
	}

	/** Returns the outcome of a game, or null. */
	public String winner() {		
		int firePieces = 0;
		int waterPieces = 0;		
		Piece piece;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				piece = pieceAt(x, y);
				if (piece != null) {
					if (piece.isFire()) {
						firePieces++;
					}
					else {
						waterPieces++;
					}
				}				
			}
		}		
		if (firePieces != 0 && waterPieces != 0) {
			return null;
		}
		else if (firePieces != 0 && waterPieces == 0) {
			return "Fire";
		}
		else if (firePieces == 0 && waterPieces != 0) {
			return "Water";
		}
		else if (firePieces == 0 && waterPieces == 0) {
			return "No one";
		}
		else {
			return null;
		}
	}

	/** Determines the vertical direction a piece can move in. */
	private int yOffset(Piece p) {
		if (p.side() == 0) {
			return 1;
		} 
		return -1;
	}
	
	/** Returns the piece at a location. */
	public Piece pieceAt(int x, int y) {
		if (notInBounds(x, y)) {
			return null;
		}
		else {		
			return locations[x][y];
		}		
	}

	/** Places a piece at a new location, and removes it from its old one. */
	public void place(Piece p, int x, int y) {
		int oldX, oldY;		
		if (p == null || notInBounds(x, y)) {
			return;
		}
		else {			
			if (onBoard(p)) {
				oldX = getX(p);
				oldY = getY(p);
				locations[oldX][oldY] = null;	
			}			
			locations[x][y] = p;								
		}
	}

	/** Removes a piece at a given location. */
	public Piece remove(int x, int y) {
		if (notInBounds(x, y)) {
			System.out.println("Coordinates out of bounds");
			return null;
		}
		else {
			Piece piece = locations[x][y];
			if (piece == null) {				
				System.out.println("No piece at coordinates");
			}
			locations[x][y] = null;								
			return piece;
		}
	}

	private void highlightSquare(double x, double y) {
		if (notInBounds((int) x, (int) y)) {
			return;
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare((int) x + 0.5, (int) y + 0.5, 0.5);
	}



	private boolean notInBounds(int x, int y) {		
		return x < 0 || x > 7 || y < 0 || y > 7;
	}	

	private boolean onBoard(Piece piece) {
		int x = getX(piece);
		return (x != -1);
	}
	
	private int getX(Piece piece) {
		if (piece != null) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (locations[x][y] == piece) {
						return x;
					}
				}
			}			
		}
		return -1;
	}

	private int getY(Piece piece) {
		if (piece != null) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (locations[x][y] == piece) {
						return y;
					}
				}
			}			
		}
		return -1;
	}

	private void drawPieces()
	{	
		Piece p;
		int imageNum;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				p = pieceAt(x, y);
				if (p != null) {
					imageNum = getImageNum(p);
					StdDrawPlus.picture(x + 0.5, y + 0.5, imageNames[imageNum], 1, 1);
				}
			}
		}		
	}

	/** Returns the index of the appropriate image file in imageNums. */
	private int getImageNum(Piece p) {
		int imageNum, offset;
		if (p.isFire()) {
			offset = 0;
		}
		else {
			offset = 6;
		}
		imageNum = offset;
		if (p.isShield()) {
			imageNum += 2;
		}
		else if (p.isBomb()) {
			imageNum += 4;
		}
		if (p.isKing()) {
			imageNum += 1;
		}
		return imageNum;
	}

	/** Draws the squares of the checkers board. */
	private void setBoard() {
		boolean grayFirst;		
		for(int x = 0; x < 8; x++) {
			if (x % 2 == 0) {
				grayFirst = true;
			}			
			else {
				grayFirst = false;
			}			
			for (int y = 0; y < 8; y++) {
				if ((y % 2 == 0 && grayFirst) || (y % 2 != 0 && !grayFirst)) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
				StdDrawPlus.square(x + 0.5, y + 0.5, 0.5);
			}
		}
	}

	/** Creates the pieces of a board instance. */
	private void createPieces() {
		Piece piece;
		int typeNum = 0;
		int offset = 0;
		String[] types = {"pawn", "shield", "bomb"};
		boolean fire = true;
		for(int y = 0; y < 3; y++) {
			for (int x = 0; x < 8; x += 2) {				
				piece = new Piece(fire, this, x + offset, y, types[typeNum]);					
				locations[x + offset][y] = piece;	
			}
			typeNum++;
			offset = (offset + 1) % 2;
		}
		fire = false;
		typeNum = 0;
		for(int y = 7; y > 4; y--) {
			for (int x = 0; x < 8; x += 2) {
				piece = new Piece(fire, this, x + offset, y, types[typeNum]);					
				locations[x + offset][y] = piece;
			}
			typeNum++;
			offset = (offset + 1) % 2;
		}
	}			
}