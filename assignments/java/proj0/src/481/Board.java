public class Board{
	private static int N = 8;
	private int currentSide = 0;
    private Piece pieces[][] = new Piece[8][8];
	private int selectedXPosition;
	private int selectedYPosition;
	private Piece selectedPiece = null;
	private boolean hasMoved = false;

	public Board (boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			//Do nothing
		}
		else{
			//Setting Pieces
			for (int i = 0; i < 4; i ++) {
				pieces[i*2][0] = new Piece(true, this, i*2, 0, "pawn");
				pieces[i*2][2] = new Piece(true, this, i*2, 2, "bomb");
				pieces[i*2][6] = new Piece(false,  this, i*2, 6, "shield");
		
				pieces[2*i + 1][1] = new Piece(true, this, 2*i + 1, 1, "shield");
				pieces[2*i + 1][5] = new Piece(false, this,  2*i + 1, 5, "bomb");
				pieces[2*i + 1][7] = new Piece(false, this,  2*i + 1, 7, "pawn");
			}
		}
	}
	public static void main(String[] args) {
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		double xD = -1, yD = -1;
		int x, y;
		while (true) {
			b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                xD = StdDrawPlus.mouseX();
                yD = StdDrawPlus.mouseY();
            }            
			x = (int) xD;
			y = (int) yD;
			if (b.canSelect(x,y)) {
				b.select(x,y);
			}
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
				if (b.winner() != null){
					return;
				}
			}
            StdDrawPlus.show(10);
		}
	}
/*	Gets the piece at position (x, y) on the board, or null if there is no piece */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0 ) 
			return null;
		if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}

/* Places p at (x,y). If (x,y) is out of bounds or if p is null, does nothing
 * If another piece already exists at (x,y), p will replace that piece.
 */
	public void place(Piece p, int x, int y) {
		if (p == null || x > 7 || x < 0 || y > 7 || y < 0 ) 
			return;
		pieces[x][y] = p;
	}

/* Removes the pieces at (x,y). If there is no such piece, returns null */
	public Piece remove(int x, int y) {
		Piece oldPiece = pieceAt(x,y);
		if (oldPiece == null) {
			return null;
		}
		pieces[x][y] = null;
		return oldPiece;
	}

/* Returns true if the square at (x, y) can be selected */
	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x,y);
		if (piece != null) {
			if (hasMoved == true)
				return false;
			if (piece.side() == currentSide) {
				if (selectedPiece == null ){
					return true;
				}
				if (hasMoved == false && selectedPiece.hasCaptured() == false){
					return true;
				}
				return false;
			}
			return false;

		}
		else {
			// selected an empty space
			if (selectedPiece == null){
				//can't select an empty space by itself
				return false;
			}
			if (!validMove(selectedPiece, selectedXPosition, selectedYPosition, x, y)) {
				// checking invalid geometry (IE too far, capture, no capture etc)
				return false;
			}
			//These next two might need to be modified in cases of captures
			if (hasMoved == false) {
				//Valid move, valid selected piece, hasn't moved, => true
				return true;
			}
			else {
				return selectedPiece.hasCaptured() ? true : false; //Might need to modify in Piece.java
			} 
		}
	}


	/* ASSUMPTIONS: the piece at xf, yf, is null. Parameter piece is not null*/
	private boolean validMove(Piece piece, int xi, int yi, int xf, int yf) {
		if (xf > 7 || xf < 0 || yf > 7 || yf < 0 ) 
			return false;
		if (piece == null)
			return false;
		int xDistance = xi - xf;
		int yDistance = yi - yf;
		if (Math.abs(xDistance) == 1) {
			//Movement to empty space with no pieces between
			if (selectedPiece.hasCaptured()){
				//Can't capture and move to empty space
				return false;
			}
			if (piece.isKing()) {
				if (xDistance * yDistance == 1 || xDistance*yDistance == -1) 
					return true;
				return false;
			}
			if (piece.side() == 0) {
				if (xDistance == 1 && yDistance == -1)
					return true;
				if (xDistance == -1 && yDistance == -1)
					return true;
				return false;
			}
			else {
				if (xDistance == 1 && yDistance == 1)
					return true;
				if (xDistance == -1 && yDistance == 1)
					return true;
				return false;
			}
		}
		else if (Math.abs(xDistance) == 2)  {
			//Check for valid capture
			//May want to add tag about capturing here
			//	if (Math.abs(yDistance) == 2){
			//		return true;
			//	}
			if (piece.isKing()) {
				if (xDistance == 2 && yDistance == -2){
					return checkSide(xf + 1, yf - 1);
//					capturedPiece = pieceAt(xf + 1, yf - 1)
//					if (pieceAt(xf+1, yf-1) != null ){
//						return true;
//					}
//					return false;
				}
				else if (xDistance == -2 && yDistance == -2){
					return checkSide(xf - 1, yf - 1);
//					if (pieceAt(xf-1, yf-1) != null) {
//						return true;
//					}
//					return false;
				}
				else if (xDistance == 2 && yDistance == 2){
					return checkSide(xf + 1, yf + 1);
//					if (pieceAt(xf+1, yf+1) != null ){
//						return true;
//					}
//					return false;
				}
				if (xDistance == -2 && yDistance == 2){
					return checkSide(xf - 1, yf + 1);
//					if (pieceAt(xf-1, yf+1) != null) {
//						return true;
//					}
//					return false;
				}
				return false;
			}
			else if (piece.side() == 0) {
				if (xDistance == 2 && yDistance == -2){
					return checkSide(xf + 1, yf - 1);
//					if (pieceAt(xf+1, yf-1) != null ){
//						return true;
//					}
//					return false;
				}
				if (xDistance == -2 && yDistance == -2){
					return checkSide(xf - 1, yf - 1);
//					if (pieceAt(xf-1, yf-1) != null) {
//						return true;
//					}
//					return false;
				}
				return false;
			}
			else {
				if (xDistance == 2 && yDistance == 2){
					return checkSide(xf + 1, yf + 1);
///					if (pieceAt(xf+1, yf+1) != null ){
///						return true;
///					}
///					return false;
				}
				if (xDistance == -2 && yDistance == 2){
					return checkSide(xf - 1, yf + 1);
				}
				return false;
			}
		}
		return false;
	}
	private boolean checkSide(int x, int y){
		Piece capturedPiece = pieceAt(x,y);
		if (capturedPiece != null && capturedPiece.side() != currentSide){
			return true;
		}
		return false;
		}

	/* This method assumes canSelect returns true */
	public void select(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		String type, side, kinged, path;

		Piece piece = pieceAt(x, y);

		if (selectedPiece != null){
			if (pieceAt(x,y) != null && selectedPiece.side() != pieceAt(x,y).side()){
				//Checking if capture move
				selectedPiece.move(x,y);
				hasMoved = true;
			}
			else if (pieceAt(x,y) == null){
				//Empty space
				selectedPiece.move(x,y);
				hasMoved = true;
			}
		}

		if (piece != null) {
			//draw white square
			if (piece.isBomb())
				type = "bomb";
			else if (piece.isShield()) 
				type = "shield";
			else
				type = "pawn";
			side = piece.side() == 0 ? "fire" : "water";
			kinged = piece.isKing() ? "-crowned" : "";
			path = "img/" + type + "-" + side + kinged + ".png";
			StdDrawPlus.picture(x + .5, y + .5, path, 1, 1);
		}
	
		selectedPiece = pieces[x][y];
		selectedXPosition = x;
		selectedYPosition = y;
	}

    private void drawBoard() {
		String type, side, kinged, path;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null){
					if (pieces[i][j].isBomb())
						type = "bomb";
					else if (pieces[i][j].isShield()) 
						type = "shield";
					else
						type = "pawn";
					side = pieces[i][j].side() == 0 ? "fire" : "water";
					kinged = pieces[i][j].isKing() ? "-crowned" : "";
					path = "img/" + type + "-" + side + kinged + ".png";
					StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
				}
			}
		}
	}

	public boolean canEndTurn() {
		if (hasMoved == true || (selectedPiece != null && selectedPiece.hasCaptured() == true))
			return true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece piece = pieceAt(i, j);
				if (piece != null) {
					if (selectedPiece != null && selectedPiece.hasCaptured() == true){
						return true;
					}
				}
			}
		}
		return false;
	}

	public void endTurn() {
		if ( !canEndTurn() ) {
			return;
		}
		else {
			if (selectedPiece != null)
				selectedPiece.doneCapturing();
			currentSide = (currentSide == 1)? 0: 1;
			selectedPiece = null;
			hasMoved = false;
			return;
		}
	}

	/* Determines winner by counting total pieces*/
	public String winner() {
			int firePiecesLeft = countPieces(0);
			int waterPiecesLeft = countPieces(1);
			if (firePiecesLeft <= 0) {
				if (waterPiecesLeft <= 0)
					return "No one";
				return "Water";
			}
			if (waterPiecesLeft <= 0) {
				return "Fire";
			}
			return null;
	}
	/* Counts pieces left on side. */
	private int countPieces(int side) {
		int piecesLeft = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece piece = pieceAt(i, j);
				if (piece != null) {
					if (piece.side() == side){
						piecesLeft++;
					}
				}
			}
		}
		return piecesLeft;
	}

}

