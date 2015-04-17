public class Board{
	private Piece[][] pieces;
	private int currentTurn = 0; //The game starts with fire's turn
	private int firepieces;
	private int waterpieces;
	private int selectedPieceX;
	private int selectedPieceY;
	private Piece selectedPiece = null;
	private boolean moved = false;
	private boolean madeCapture = false;

	//For testing purposes
	// public Piece[][] pieces;
	// public int currentTurn = 0;
	// public boolean moved = false;

	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == true){
			pieces = new Piece[8][8]; //Initialize an board with no pieces
			firepieces = 0;
			waterpieces = 0;
		} else {
			pieces = new Piece[8][8];

			//Setting up fire pieces
			setUpPieces(0, 3, 0, 2, true, "pawn", "bomb", "shield");
			//Setting up water pieces
			setUpPieces(5, 8, 5, 7, false, "bomb", "pawn", "shield");
		}
	}

	private void setUpPieces(int row_start, int row_end, int first_row, int third_row, boolean side, String type1, String type2, String type3){
		for (int i = 0; i < 8; i++){ //deals with which vertical "column" we want to work with
				for (int j = row_start; j < row_end; j++){ //deals with each "element in a column," but each element is its own "horizontal row"
					String type;
					if (j == first_row){
						type = type1;
					} else if (j == third_row){
						type = type2;
					} else {
						type = type3;
					}
					if ( ((i+j) % 2) == 0 ){
						pieces[i][j] = new Piece(side, this, i, j, type);
						if (pieces[i][j].isFire()) {
							firepieces += 1;
						} else {
							waterpieces += 1;
						}
					}
				}
			}
	}

	public Piece pieceAt(int x, int y){
		if (x >= 8 || y >= 8 || x < 0 || y < 0){
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){
		Piece square = pieceAt(x, y);
		if ( (square != null) && (square.side() == currentTurn) ) {
			if (selectedPiece == null && moved == false) {
				return true;
			} else {
				if (moved == false) {
					return true;
				}
				return false;
			}
		} else {
			if ( (selectedPiece != null) && (moved == false) && (validMove(selectedPieceX, selectedPieceY, x, y) == true) ) {
				return true;
			} else if ( (selectedPiece != null) && (selectedPiece.isBomb() == false) && (selectedPiece.hasCaptured()) && (moved) && (validMove(selectedPieceX, selectedPieceY, x, y)) ) {
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){ 

		Piece leftCaptureSquare;
		Piece rightCaptureSquare;

		if (pieceAt(xf, yf) != null){
			return false;
		} else if (selectedPiece.isKing()) {
			Piece leftUpCaptureSquare = pieceAt(xi - 1, yi + 1);
			Piece leftDownCaptureSquare = pieceAt(xi - 1, yi - 1);
			Piece rightUpCaptureSquare = pieceAt(xi + 1, yi + 1);
			Piece rightDownCaptureSquare = pieceAt(xi + 1, yi - 1);

			if ( ( (xf == xi + 1) || (xf == xi - 1) ) && ( (yf == yi + 1) || (yf == yi - 1) ) ) {
				return true;
			} else {
				if ( (xf == xi - 2) && (yf == yi + 2) && (leftUpCaptureSquare != null) && (leftUpCaptureSquare.side() != currentTurn) ) {
					return true;
				} else if ( (xf == xi + 2) && (yf == yi + 2) && (rightUpCaptureSquare != null) && (rightUpCaptureSquare.side() != currentTurn) ) {
					return true;
				} else if ( (xf == xi - 2) && (yf == yi - 2) && (leftDownCaptureSquare != null) && (leftDownCaptureSquare.side() != currentTurn) ) {
					return true;
				} else if ( (xf == xi + 2) && (yf == yi - 2) && (rightDownCaptureSquare != null) && (rightDownCaptureSquare.side() != currentTurn) ) {
					return true;
				} else {
					return false;
				}
			}
		} else if (currentTurn == 0){ 
		//If it is fire's turn, they should only move up
			leftCaptureSquare = pieceAt(xi - 1, yi + 1); 
			rightCaptureSquare = pieceAt(xi + 1, yi + 1);

			if ( ((xf == xi + 1) || (xf == xi - 1)) && (yf == yi + 1) && (moved == false) ) {
				return true;
			} else if ( (xf == xi - 2) && (yf == yi + 2) && (leftCaptureSquare != null) && (leftCaptureSquare.side() != currentTurn) ) {
				return true;
			} else if ( (xf == xi + 2) && (yf == yi + 2) && (rightCaptureSquare != null) && (rightCaptureSquare.side() != currentTurn) ) {
				return true;
			} else {
				return false;
			}
		} else {
		//If it is water's turn, they should only move down
			leftCaptureSquare = pieceAt(xi - 1, yi - 1); 
			rightCaptureSquare = pieceAt(xi + 1, yi - 1);

			if ( ((xf == xi + 1) || (xf == xi - 1)) && (yf == yi - 1) && (moved == false) ){
				return true;
			} else if ( (xf == xi - 2) && (yf == yi - 2) && (leftCaptureSquare != null) && (leftCaptureSquare.side() != currentTurn) ) {
				return true;
			} else if ( (xf == xi + 2) && (yf == yi - 2) && (rightCaptureSquare != null) && (rightCaptureSquare.side() != currentTurn) ) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void select(int x, int y){
			if (pieceAt(x, y) == null && selectedPiece != null){
				pieces[selectedPieceX][selectedPieceY] = null; //Removes selected piece from old place
				pieces[x][y] = selectedPiece;
				selectedPiece.move(x, y);
				capturing(selectedPieceX, selectedPieceY, x, y); //Updates board state (and consequently gui.) 
																 //Should work whether there's something to capture or not.
				selectedPieceX = x;
				selectedPieceY = y;
				moved = true;
		} else {
			selectedPieceX = x;
			selectedPieceY = y;
			selectedPiece = pieceAt(x, y);
		}		
	}

	private void capturing(int xi, int yi, int xf, int yf) {
		Piece leftCaptureSquare;
		Piece rightCaptureSquare;

		if (selectedPiece.isKing()) {
			kingCapture(xi, yi, xf, yf);
		} else if (currentTurn == 0) { 
			leftCaptureSquare = pieceAt(xi - 1, yi + 1); 
			rightCaptureSquare = pieceAt(xi + 1, yi + 1);

			if ( (xf == xi - 2) && (yf == yi + 2) && (leftCaptureSquare != null) && (leftCaptureSquare.side() != currentTurn) ) {
				if (selectedPiece.isBomb()) {
					bombCapture(xf, yf);
				} else {
					remove(xi - 1, yi + 1);
					madeCapture = true;
					waterpieces -= 1;
				}
			} else if ( (xf == xi + 2) && (yf == yi + 2) && (rightCaptureSquare != null) && (rightCaptureSquare.side() != currentTurn) ) {
				if (selectedPiece.isBomb()) {
					bombCapture(xf, yf);
				} else {
					remove(xi + 1, yi + 1);
					madeCapture = true;
					waterpieces -= 1;
				}
			}
		} else {
			leftCaptureSquare = pieceAt(xi - 1, yi - 1); 
			rightCaptureSquare = pieceAt(xi + 1, yi - 1);

			if ( (xf == xi - 2) && (yf == yi - 2) && (leftCaptureSquare != null) && (leftCaptureSquare.side() != currentTurn) ) {
				if (selectedPiece.isBomb()) {
					bombCapture(xf, yf);
				} else {
					remove(xi - 1, yi - 1);
					madeCapture = true;
					firepieces -= 1;
				}
			} else if ( (xf == xi + 2) && (yf == yi - 2) && (rightCaptureSquare != null) && (rightCaptureSquare.side() != currentTurn) ) {
				if (selectedPiece.isBomb()) {
					bombCapture(xf, yf);
				} else {
					remove(xi + 1, yi - 1);
					madeCapture = true;
					firepieces -= 1;
				}
			}
		}
	}

	private void bombCapture(int xf, int yf){
		int killed = 0;
		Piece leftUpCaptureSquare = pieceAt(xf - 1, yf + 1);
		Piece leftDownCaptureSquare = pieceAt(xf - 1, yf - 1);
		Piece rightUpCaptureSquare = pieceAt(xf + 1, yf + 1);
		Piece rightDownCaptureSquare = pieceAt(xf + 1, yf - 1);

		if (leftUpCaptureSquare != null && leftUpCaptureSquare.isShield() == false){
			remove(xf - 1, yf + 1);
			killed += 1;
		}
		if (leftDownCaptureSquare != null && leftDownCaptureSquare.isShield() == false) {
			remove(xf - 1, yf - 1);
			killed += 1;
		}
		if (rightUpCaptureSquare != null && rightUpCaptureSquare.isShield() == false) {
			remove(xf + 1, yf + 1);
			killed += 1;
		}
		if (rightDownCaptureSquare != null && rightDownCaptureSquare.isShield() == false) {
			remove(xf + 1, yf - 1);
			killed += 1;
		}
		madeCapture = true;
		remove(xf, yf);
		selectedPiece = null;
		if (currentTurn == 0) {
			firepieces -= 1;
			waterpieces -= killed;
		} else {
			waterpieces -= 1;
			firepieces -= killed;
		}
	}

	private void kingCapture(int xi, int yi, int xf, int yf){
		Piece leftUpCaptureSquare = pieceAt(xi - 1, yi + 1);
		Piece leftDownCaptureSquare = pieceAt(xi - 1, yi - 1);
		Piece rightUpCaptureSquare = pieceAt(xi + 1, yi + 1);
		Piece rightDownCaptureSquare = pieceAt(xi + 1, yi - 1);

		if ( (xf == xi - 2) && (yf == yi + 2) && (leftUpCaptureSquare != null) && (leftUpCaptureSquare.side() != currentTurn) ) {
			remove(xi - 1, yi + 1);
			if (currentTurn == 0) {
				madeCapture = true;
				waterpieces -= 1;
			} else {
				madeCapture = true;
				firepieces -= 1;
			}
		} else if ( (xf == xi + 2) && (yf == yi + 2) && (rightUpCaptureSquare != null) && (rightUpCaptureSquare.side() != currentTurn) ) {
			remove(xi + 1, yi + 1);
			if (currentTurn == 0) {
				madeCapture = true;
				waterpieces -= 1;
			} else {
				madeCapture = true;
				firepieces -= 1;
			}
		} else if ( (xf == xi - 2) && (yf == yi - 2) && (leftDownCaptureSquare != null) && (leftDownCaptureSquare.side() != currentTurn) ) {
			remove(xi - 1, yi - 1);
			if (currentTurn == 0) {
				madeCapture = true;
				waterpieces -= 1;
			} else {
				madeCapture = true;
				firepieces -= 1;
			}
		} else if ( (xf == xi + 2) && (yf == yi - 2) && (rightDownCaptureSquare != null) && (rightDownCaptureSquare.side() != currentTurn) ) {
			pieces[xi + 1][yi - 1] = null;
			if (currentTurn == 0) {
				madeCapture = true;
				waterpieces -= 1;
			} else {
				madeCapture = true;
				firepieces -= 1;
			}
		}
	}

	public void place(Piece p, int x, int y){
		if (pieceAt(x, y) == p){ //using == to check that they're pointing to same object
			pieces[x][y] = null; //removed p from its initial position
		}
		if (x < 8 && y < 8 && p != null){
			pieces[x][y] = p;
			if (pieces[x][y].isFire()){
				firepieces += 1;
			} else {
				waterpieces += 1;
			}
		}
	}

	public Piece remove(int x, int y){
		Piece removedPiece = null;
		if (x > 8 || y > 8 || x < 0 || y < 0){
			System.out.println("Out of bounds, mate!");
		} else if (pieces[x][y] == null){
			System.out.println("No piece here, buddy!");
		} else{
			removedPiece = pieces[x][y];
			pieces[x][y] = null;
		} 
		return removedPiece;
	}

	public boolean canEndTurn(){
		if (moved || madeCapture){
			return true;
		} else {
			return false;
		}
	}

	public void endTurn(){	
		if (currentTurn == 0){
			currentTurn = 1;
		} else {
			currentTurn = 0;
		}
		moved = false;
		madeCapture = false;
		if (selectedPiece != null){
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
	}

	public String winner(){
		if (firepieces == 0 && waterpieces == 0){
			return "No one";
		} else if (firepieces == 0){
			return "Fire";
		} else if (waterpieces == 0){
			return "Water";
		} else {
			return null;
		}
	}

	////////////////////////ALL MY GUI METHODS///////////////////////////////////
	private void drawBoard(int N){ //N represents width and height of board
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (((i + j) % 2) == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else { 
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				//The .5 allows us to go into the middle of the square?
			}
		}
	}

	private void drawPieces(){
		String side;
		String type;
		String firepawn = "img/pawn-fire.png";
		String fireshield = "img/shield-fire.png";
		String firebomb = "img/bomb-fire.png";
		String firepawnK = "img/pawn-fire-crowned.png";
		String fireshieldK = "img/shield-fire-crowned.png";
		String firebombK = "img/bomb-fire-crowned.png";
		String waterpawn = "img/pawn-water.png";
		String watershield = "img/shield-water.png";
		String waterbomb = "img/bomb-water.png";
		String waterpawnK = "img/pawn-water-crowned.png";
		String watershieldK = "img/shield-water-crowned.png";
		String waterbombK = "img/bomb-water-crowned.png";
		Piece pieceToDraw;

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				pieceToDraw = pieces[i][j];
				if (pieceToDraw != null) {
					if (pieceToDraw.side() == 0){
						if (pieceToDraw.isShield()){
							if (pieceToDraw.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, fireshieldK, 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, fireshield, 1, 1);
							}
						} else if (pieceToDraw.isBomb()){
							if (pieceToDraw.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, firebombK, 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, firebomb, 1, 1);
							}
						} else {
							if (pieceToDraw.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, firepawnK, 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, firepawn, 1, 1);
							}
						}
					} else {
						if (pieceToDraw.isShield()){
							if (pieceToDraw.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, watershieldK, 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, watershield, 1, 1);
							}
						} else if (pieceToDraw.isBomb()){
							if (pieceToDraw.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, waterbombK, 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, waterbomb, 1, 1);
							}
						} else {
							if (pieceToDraw.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, waterpawnK, 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, waterpawn, 1, 1);
							}
						}
					}
				}
			}
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String args[]){
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board board = new Board(false);

		double x;
		double y;
		boolean gameStillGoing = true;

		while(gameStillGoing){
			board.drawBoard(8);
			if (board.selectedPiece != null) {
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare((int) board.selectedPieceX + .5, (int) board.selectedPieceY + .5, .5);
			}
			board.drawPieces();
			if (StdDrawPlus.mousePressed()) {
				x = StdDrawPlus.mouseX();
				y = StdDrawPlus.mouseY();
				if (board.canSelect((int) x, (int) y)) {
					board.select((int) x, (int) y);
				}
			}
			
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()){
					if (board.winner() != null) {
					gameStillGoing = false;
				}
					board.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
		board.drawBoard(8);
		board.drawPieces();
	}
}