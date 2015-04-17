public class Board {
	private Piece[][] pieces; 
	private int nCols;
	private int nRows;
	private int selectedX;
	private int selectedY;
	private Piece selectedPiece;
	private boolean hasSelected;
	private boolean pieceMoved;
	private boolean fireTurn; 
	private int waterPiecesLeft;
	private int firePiecesLeft;
	private boolean boardIsEmpty;


	public Board (boolean shouldBeEmpty){
		hasSelected = false;
		pieceMoved = false;
		fireTurn = true;
		nCols = 8;
		nRows = 8;
		selectedX = nCols + 1;
		selectedY = nRows + 1;
		pieces = new Piece[8][8];
		firePiecesLeft = 0;
		waterPiecesLeft = 0;
		boardIsEmpty = true;
		
		if (!shouldBeEmpty) {
			boardIsEmpty = false;
			for (int row = 0; row < nRows; row += 1) {
				for (int col = 0; col < nCols; col += 1) {
					if ((row + col) % 2 == 0) {
						if (row == 0) {
							pieces[row][col] = new Piece(true, this, col, row, "pawn");
						}
						else if (row == 1) {
							pieces[row][col] = new Piece(true, this, col, row, "shield");
						}
						else if (row == 2) {
							pieces[row][col] = new Piece(true, this, col, row, "bomb");
						}
						else if (row == 5) {
							pieces[row][col] = new Piece(false, this, col, row, "bomb");
						}
						else if (row == 6) {
							pieces[row][col] = new Piece(false, this, col, row, "shield");
						}
						else if (row == 7){
							pieces[row][col] = new Piece(false, this, col, row, "pawn");
						}	
					}
				}
			}

			waterPiecesLeft = 12;
			firePiecesLeft = 12;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > nCols - 1 || x < 0 || y > nRows - 1|| y < 0) {
				return null;
		}

		else {
			
			return pieces[y][x];
		}

	}

	public boolean canSelect(int x, int y) {
		Piece currentPiece = pieceAt(x, y);

		if (currentPiece != null) {
			return pieceTurnMatch(currentPiece) && (!hasSelected || (hasSelected && !pieceMoved)); 
		}
		else {

			if (hasSelected && !pieceMoved && validMove(selectedX, selectedY, x, y)){
				return true;
			}

			else if (pieceMoved && hasSelected && selectedPiece.hasCaptured() && stillValidCapture(selectedPiece, selectedX, selectedY, x, y)){
				
				return true;
			}

			return false;
		}

	}

	public void select (int x, int y) {

		if (pieceAt(x, y) != null) {
			selectedX = x;
			selectedY = y;
			selectedPiece = pieceAt(x, y);
			hasSelected = true;

		}

		else { 
			pieceMoved = true;
			selectedPiece.move(x, y);
			selectedX = x;
			selectedY = y;

		}

	}

	private boolean canStillCapture(Piece p, int xi, int yi) {
		
		if (p.isFire()) {

			if (stillValidCapture(p, xi, yi, xi + 1, yi + 1) || stillValidCapture(p, xi, yi, xi - 1, yi + 1)) {
				return true;
			}

			if (p.isKing()) {
				if (stillValidCapture(p, xi, yi, xi + 1, yi - 1) || stillValidCapture(p, xi, yi, xi - 1, yi - 1)) {
					return true;
				}

			}

			return false;

		}

		else {
			
			if (stillValidCapture(p, xi, yi, xi + 1, yi - 1) || stillValidCapture(p, xi, yi, xi - 1, yi - 1)) {
				return true;
			}

			if (p.isKing()) {
				if (stillValidCapture(p, xi, yi, xi + 1, yi + 1) || stillValidCapture(p, xi, yi, xi - 1, yi + 1)) {
					return true;
				}

			}

			return false;
		}

		
	}

	private boolean pieceTurnMatch(Piece p) {
		return fireTurn && p.isFire() || !fireTurn && !p.isFire();
	}

	private boolean stillValidCapture(Piece p, int xi, int yi, int xf, int yf) {
		if (p.isBomb()) {return false;}
		if (p.isFire()) {
			if (isValidFireCapture(xi, yi, xf, yf)) {
				return true;
			}

			return false;
		}
		else {
			if (isValidWaterCapture(xi, yi, xf, yf)) {
				
				return true;
			}
			
			return false;
		}
		

	}

	private boolean isValidFireMove(int xi, int yi, int xf, int yf) {

		if (yf - yi == 1 && Math.abs(xf - xi) == 1) {
			return true;
		}

		if (pieceAt(xi, yi).isKing()) {
			if (yf - yi == -1 && Math.abs(xf - xi) == 1) {
				return true;
			}
		}
		return false;
	}
	private boolean isValidFireCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);

		if (yf - yi == 2) {
			if ((xf - xi == 2 && isEnemy(p, xi + 1, yi + 1)) || xi - xf == 2 && isEnemy(p, xi - 1, yi + 1)) {
				return true;
			}
		}
		if (p.isKing()) {

			if (yf - yi == -2) {
				if (xf - xi == 2 && isEnemy(p, xi + 1, yi - 1) || xi - xf == 2 && isEnemy(p, xi - 1, yi - 1)) {
					return true;
				}
			}	

		}

		else {
			return false;
		}
		return false;


	}

	private boolean isValidWaterMove(int xi, int yi, int xf, int yf) {

		if (yf - yi == -1 && Math.abs(xf - xi) == 1) {
			return true;
		}
		if (pieceAt(xi, yi).isKing()) {
			if (yf - yi == 1 && Math.abs(xf - xi) == 1) {
				return true;
			}
		return false;
		}
		return false; 
	}

	private boolean isValidWaterCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (yf - yi == -2){

			if ((xf - xi == 2 && isEnemy(p, xi + 1, yi - 1)) || (xi - xf == 2 && isEnemy(p, xi - 1, yi - 1))) {
				return true;
			}
		}

		if (p.isKing()) {			
			if (yf - yi == 2) {
				if (xf - xi == 2 && isEnemy(p, xi + 1, yi + 1) || xi - xf == 2 && isEnemy(p, xi - 1, yi + 1)) {
					return true;
				}
			}	

		}
		else {
			return false;
		}

		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (p != null) {
			if (p.isFire()) {
				return isValidFireMove(xi, yi, xf, yf) || isValidFireCapture(xi, yi, xf, yf);

			}

			else {

				return isValidWaterMove(xi, yi, xf, yf) || isValidWaterCapture(xi, yi, xf, yf);
				
			}
		}
		else {return false;}
	}

	private boolean isEnemy(Piece p,  int xE, int yE) {
		if (xE > nCols - 1 || xE < 0 || yE > nRows - 1 || yE < 0 ) {return false;}
		Piece enemyPiece = pieceAt(xE, yE);	

		if (enemyPiece != null && p != null) {
			if (p.isFire() && !enemyPiece.isFire() || !p.isFire() && enemyPiece.isFire()) {
				return true;
			}
			else {
				return false;
			}

		}
		else {

			return false;
		}
	}

	public void place(Piece p, int x, int y) {

		if (x < nCols && x >= 0 && y >= 0 && y < nRows && p != null) {


			pieces[y][x] = p;
			if (p.isFire()) {firePiecesLeft++;}
			else {waterPiecesLeft++;}
			if (boardIsEmpty) {boardIsEmpty = false;}
		}
	}


	public Piece remove(int x, int y) {

		if (x >= nCols || x < 0 || y >= nRows || y < 0) {
			System.out.println("Invalid x-y position, x and y must be between 0 and 7.");
			return null;
		}
		if (pieceAt(x, y) == null ) {
			System.out.println("No piece to remove at position: x = " + x + ", y = " + y +"." );
			return null;
		}
		Piece pieceRemoved = pieceAt(x, y);
		if (pieceRemoved.isFire()) {firePiecesLeft--;}
		else {waterPiecesLeft--;}
		pieces[y][x] = null;

		return pieceRemoved;


	}

	public boolean canEndTurn() {
		if (selectedPiece != null) {
			return pieceMoved || selectedPiece.hasCaptured();
		}
		else {
			return pieceMoved;
		}
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		hasSelected = false;
		pieceMoved = false;
		selectedX = nCols + 1;
		selectedY = nRows + 1;
		selectedPiece.doneCapturing();
		selectedPiece = null;
	}

	private void drawBoard(){
		StdDrawPlus.setXscale(0.0, nCols);
		StdDrawPlus.setYscale(0.0, nRows); 
		for (int i = 0; i < nRows ; i += 1){
			for (int j = 0; j < nCols; j += 1){	
				if (i == selectedX && j == selectedY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
			}
		}
		
	}

	private boolean isSquareSelected(int x, int y) {

		return x == selectedX && y == selectedY;
	}

	private void drawPieces() {
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				drawPiece(row, col);
			}
		}

	}

	private void drawPiece(int row, int col) {
		Piece currentPiece = pieces[row][col];
				if (currentPiece != null) {
					if (currentPiece.isBomb()) {
						if (currentPiece.isFire()) {

							if (currentPiece.isKing()) {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/bomb-fire-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/bomb-fire.png", 1.0, 1.0);
							}
						}

						else {
							if (currentPiece.isKing()) {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/bomb-water-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/bomb-water.png", 1.0, 1.0);
							}

						}
					}

					else if (currentPiece.isShield()) {
						if (currentPiece.isFire()) {

							if (currentPiece.isKing()) {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/shield-fire-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/shield-fire.png", 1.0, 1.0);
							}
						}

						else {
							if (currentPiece.isKing()) {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/shield-water-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/shield-water.png", 1.0, 1.0);
							}

						}
					}

					else {
						if (currentPiece.isFire()) {

							if (currentPiece.isKing()) {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/pawn-fire-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/pawn-fire.png", 1.0, 1.0);
							}
						}

						else {
							if (currentPiece.isKing()) {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/pawn-water-crowned.png", 1.0, 1.0);
							}

							else {
								StdDrawPlus.picture(col + 0.5, row + 0.5, "img/pawn-water.png", 1.0, 1.0);
							}

						}
					}
				}


	}


	public String winner() {
		
		if (firePiecesLeft == 0 && waterPiecesLeft == 0) {
			return "No one";
		}
		else if (firePiecesLeft == 0) {
			return "Water";
		}
		else if (waterPiecesLeft == 0) {
			return "Fire";
		}
		else {
			return null;
		}

		
	}

	public static void main (String[] args){
		
		Board b = new Board(false);

		
		while (b.waterPiecesLeft != 0 && b.firePiecesLeft != 0) {
			b.drawBoard();
			b.drawPieces();
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				int currentX = (int) x;
				int currentY = (int) y;


				if (b.canSelect(currentX, currentY)) {
					b.select(currentX, currentY);
				
				} 

			}


			if (StdDrawPlus.isSpacePressed()) {
				
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(10); 
		}
		b.drawBoard();
		b.drawPieces();
		StdDrawPlus.show(1);
		System.out.println(b.winner());
	}
}
