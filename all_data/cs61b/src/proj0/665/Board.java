/** THINGS TO DO
0. Come up with more tests. Do not implement any more until thoroughly tested.
	a. Write smaller tests.
	c. Ending turns
	f. Win game progression is correct.
5. Submit and hope you pass all the autograder tests.
*/

public class Board {

	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private int playerTurn = 0; //Player 0 is fire, player 1 is water. Player 0 goes first.
	private int[] numPieces = {0,0}; 	// Stores each player's number of pieces in a container. There are only ever two players. 
	private int selectedSquareX = -1; // Values when no square selected are -1,-1. Else, should be a valid board position. ABSTRACT LATER?????
	private int selectedSquareY = -1;
	private Piece pieceMovedThisTurn = null;

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
		pieces = new Piece[8][8]; 	/** I know the board is always 8x8, but I wish the dimensions of the board 
										could be controlled with the constructor and not separately */
		if (!shouldBeEmpty) initializePieces();
	}

	private int boardWidth() {
		return pieces.length;
	}

	private int boardHeight() {
		return pieces[0].length;
	}

	private int numPieces(int player) {
		return numPieces[player];
	}

	/** Determine whether the given coordinates exist on the board. */
	private boolean isValidBoardPosition(int x, int y){
		return (x > -1 && y > -1 && x < boardWidth() && y < boardHeight());
	}

	/** Determine if a piece exists at coordinates (x, y) */
	private boolean pieceExists(int x, int y) {
		if (isValidBoardPosition(x,y) && pieceAt(x,y) != null) return true;
		return false;
	}

	/** return the image corresponding to piece p.
		Got this idea after reading posts on piazza by Allen Zhu
		... and also realizing that I didn't need more methods or variables in the the Piece class
		...	and that the existing methods had all the information I needed to deduce the image.
			... What was I thinking?! */
	private String getImage(Piece p){
		String type = "img/";
		
		//piece type
		if (p.isBomb()) type = type + "bomb";
		else if (p.isShield()) type = type + "shield";
		else type = type + "pawn";
		
		//piece side
		if (p.isFire()) type = type + "-fire";
		else type = type + "-water";

		if (p.isKing()) type = type + "-crowned";

		return type + ".png";
	}

	/** Add pieces to the game board in their starting positions. */
	private void initializePieces() {
		for (int i = 0; i < boardWidth(); i += 1) {
			for (int j = 0; j < boardHeight(); j += 1){
				if ((i + j) % 2 == 0) { 		// skip red spaces. no pieces should be on red spaces
					if (j == 0) {
						place(new Piece(true, this, i,j,"pawn"), i,j);
					}
					if (j == 1) {
						place(new Piece(true, this, i,j,"shield"),i,j);
					}
					if (j == 2) {
						place(new Piece(true, this, i,j,"bomb"),i,j);
					}
					if (j == 5) {
						place(new Piece(false, this, i,j,"bomb"),i,j);
					}
					if (j == 6) {
						place(new Piece(false, this, i,j,"shield"),i,j);
					}
					if (j == 7) {
						place(new Piece(false, this, i,j,"pawn"),i,j);
					}
				}
			}
		}
	}

	private void drawBoard() {
		for (int i = 0; i < boardWidth(); i++) {
			for (int j = 0; j < boardHeight(); j++){
				if (i == selectedSquareX && j == selectedSquareY)		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0)								StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else				  									StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieceExists(i, j)) {
					StdDrawPlus.picture(i + .5, j + .5, getImage(pieceAt(i,j)), 1, 1);
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if (isValidBoardPosition(x,y)) return pieces[x][y];
		return null;
	}

	public boolean canSelect(int x, int y){
		if(pieceMovedThisTurn == null){ //if no piece has yet moved this turn then...
			if(pieceExists(x,y) && pieceAt(x,y).side() == playerTurn) return true; //can select one of your own pieces at any time
			else if(validMove(selectedSquareX,selectedSquareY,x,y))   return true; //can select a space if your previously selected piece can move there
		}
		else if((selectedPiece() == pieceMovedThisTurn) && pieceMovedThisTurn.hasCaptured()) {
			if(validMultiCapture(selectedSquareX,selectedSquareY,x,y)) return true;
		}
		return false;
	}

	/** Return true if (xf,yf) is an empty space two diagonally from (xi,yi), 
		there is a piece in between those spaces, and piece exists at (xi,yi). 
		Only to be used for multicaptures. 
		I'd love to see a more elegant implementation. Mine is so... euwgh */
	private boolean validMultiCapture(int xi, int yi, int xf, int yf){
		if(isValidBoardPosition(xi,yi) && isValidBoardPosition(xf, yf)) {
			Piece movingPiece = pieceAt(xi,yi);
			Piece capturedPiece = pieceAt((xi + xf)/2,(yi + yf)/2);
			if(movingPiece != null && capturedPiece != null && pieceAt(xf,yf) == null){
				if((yf == yi + 2) && ((xf == xi + 2) || (xf == xi - 2))) {
					if(movingPiece.isFire() || movingPiece.isKing()){
						if(movingPiece.isFire() ^ capturedPiece.isFire()){
							return true;
						}
					}
				}
				if((yf == yi - 2) && ((xf == xi + 2) || (xf == xi - 2))) {
					if(!movingPiece.isFire() || movingPiece.isKing()){
						if(movingPiece.isFire() ^ capturedPiece.isFire()){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

// 
	/** determines whether moving piece from (xi,xf) to (xf,yf) is a valid first move.*/
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (isValidBoardPosition(xi,yi) && isValidBoardPosition(xf, yf)) {
			if(pieceAt(xi,yi) != null && pieceAt(xf,yf) == null){ //piece at (xi, yi) and no piece at (xf,yf)
				Piece pieceMoving = pieceAt(xi,yi);
				if((yf == yi + 1) && (pieceMoving.isKing() || pieceMoving.isFire())) { //piece moving up one square
					if ((xf == xi + 1) || (xf == xi -1)){ //space is adjacent
						return true;
					} 
				}
				if((yf == yi - 1) && (pieceMoving.isKing() || !pieceMoving.isFire())) { //piece moving down one square
					if ((xf == xi + 1) || (xf == xi -1)) {//piece moving adjacent
						return true;
					} 
				}
				if((yf == yi + 2) && (pieceMoving.isFire() || pieceMoving.isKing())) { 
					if ((xf == xi + 2) && (pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire() ^ pieceMoving.isFire())) return true;
					if ((xf == xi - 2) && (pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire() ^ pieceMoving.isFire())) return true;
				}
				
				if((yf == yi - 2) && ((!pieceMoving.isFire()) || pieceMoving.isKing())) { 
					if ((xf == xi + 2) && (pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire() ^ pieceMoving.isFire())) return true;
					if ((xf == xi - 2) && (pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire() ^ pieceMoving.isFire())) return true;
				}
			}
		}
		return false;
	}

	/** If possible to select a piece at (x,y) or move a piece to (x,y)
		Highlights and selects piece and its current square at (x,y) OR
		If a piece has already been selected, causes that piece to move or capture to (x,y) */
	public void select(int x, int y){
		if((selectedPiece() != null) && (pieceAt(x,y) == null)) {
			pieceMovedThisTurn = selectedPiece();
			pieceMovedThisTurn.move(x,y);
		}
		selectedSquareX = x; 
		selectedSquareY = y;
	}

	private Piece selectedPiece(){
		if(pieceExists(selectedSquareX,selectedSquareY)){
			return pieceAt(selectedSquareX,selectedSquareY);
		} 
		return null;
	}



	public void place(Piece p, int x, int y){
		if (isValidBoardPosition(x, y)) {
			pieces[x][y] = p;
			numPieces[p.side()] += 1;
		}
	}

	public Piece remove(int x, int y){
		if (!isValidBoardPosition(x,y)) {
			System.out.println("(" + x + "," + y + ") is not a valid board position.");
			return null;
		}
		
		Piece removed = pieceAt(x,y);
		if (removed == null) {
			System.out.println("There is no piece to remove at (" + x + "," + y + ").");
			return null;
		}

		pieces[x][y] = null;
		numPieces[removed.side()] -= 1;
		return removed;
	}

	private void switchTurns(){ 
		playerTurn = 1 - playerTurn;//I remembered this kind of turn tracking from the 61A hog project!!!!!
		pieceMovedThisTurn = null;
		selectedSquareX = -1;
		selectedSquareY = -1;
	}

	public boolean canEndTurn(){
		return pieceMovedThisTurn != null;
	}

	public void endTurn(){
			playerTurn = 1 - playerTurn;//I remembered this kind of turn tracking from the 61A hog project!!!!!
			if(pieceMovedThisTurn!=null){
				pieceMovedThisTurn.doneCapturing();
				pieceMovedThisTurn = null;
			}
			selectedSquareX = -1;
			selectedSquareY = -1;
	}

	public String winner(){
		if(numPieces(0) == 0 && numPieces(1) == 0) return "No one";
		else if (numPieces(0) == 0) return "Water";
		else if (numPieces(1) == 0) return "Fire";
		return null;
	}

	//Launches a board GUI
	public static void main (String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);

		while (true){
			b.drawBoard();
			
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if(b.canSelect((int) x, (int) y)){
					b.select((int) x, (int) y);
				}
			}

			if(StdDrawPlus.isSpacePressed()){
				if(b.canEndTurn()) {
					if (b.winner() != null){
						System.out.println(b.winner() + " is the winner.");
						return;
					}
					b.endTurn();
	
				}
			}
			StdDrawPlus.show(25);
		}

	}
}