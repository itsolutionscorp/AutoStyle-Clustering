public class Board {

	private int size;
	private int turn; 
	private Piece selectedPiece; 
	private int selectedPiecex; 
	private int selectedPiecey;
	private boolean hasMoved; 

	//citation: http://stackoverflow.com/questions/12231453/creating-two-dimensional-array

	private Piece[][] pieces;

	public Board(boolean shouldBeEmpty) {
		size = 8;	
		pieces = new Piece[size][size];
		turn = 0; 
		selectedPiece = null;
		selectedPiecex = 0;
		selectedPiecey = 0; 
		hasMoved = false;
		if (! shouldBeEmpty) {
			makePieces();
		}
		
	}

	private void drawBoard() {

		for (int x = 0; x < size; x += 1) {

			for (int y = 0; y < size; y += 1) {

				if ((x + y) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}

				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}

				StdDrawPlus.filledSquare(x + .5, y + .5, .5);

				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				if ((pieces[x][y] != null) && (pieces[x][y] == selectedPiece)) {
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				}

				Piece piece = pieces[x][y];

				if (piece != null) {

					if (piece.isFire()) {

						if (piece.isBomb()) {

							if (piece.isKing()) {

								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
							}

                    		else {

                    			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                    		}
                    	}

                    	else if (piece.isShield()) {

							if (piece.isKing()) {

								StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
							}

                    		else {

                    			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                    		}
                    	}

                    	else {

							if (piece.isKing()) {

								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
							}

                    		else {

                    			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                    		}
                    	}
                    }

                    else {

						if (piece.isBomb()) {

							if (piece.isKing()) {

								StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
							}

                    		else {

                    			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                    		}
                    	}

                    	else if (piece.isShield()) {

							if (piece.isKing()) {

								StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
							}

                    		else {

                    			StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                    		}
                    	}

                    	else {

							if (piece.isKing()) {

								StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
							}

                    		else {

                    			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                    		}
                    	}                    
					}				              
                }
			}
		}
	}




	private void makePieces() {
		for (int x = 0; x < size; x += 1) {

			for (int y = 0; y < size; y += 1) {

				if ((x + y) % 2 == 0) {

					if (y == 0) {
						pieces[x][y] = new Piece (true, this, x, y, "pawn"); 
					}

					else if (y == 1) {
						pieces[x][y] = new Piece (true, this, x, y, "shield"); 
					}

					else if (y == 2) {
						pieces[x][y] = new Piece (true, this, x, y, "bomb"); 
					}

					else if (y == 5) {
						pieces[x][y] = new Piece (false, this, x, y, "bomb"); 
					}

					else if (y == 6) {
						pieces[x][y] = new Piece (false, this, x, y, "shield"); 
					}

					else if (y == 7) {
						pieces[x][y] = new Piece (false, this, x, y, "pawn"); 
					}

				}
			}
		}
	}

	private boolean outOfBounds(int x, int y) {
		if ((x < 0) || (y < 0) || (x >= size) || (y >= size)) {
			return true;
		}

		return false;
		
	}

	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y)) {
			return null; 
		}

		return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if (outOfBounds(x, y)) {
			return;
		}

		if (p == null) {
			return;
		}

		Piece tbr = pieceAt(x, y);

		if (tbr != null) {
			remove(x, y);
		}
			
		p.move(x, y);
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		Piece tbr = pieceAt(x, y);

		if (outOfBounds(x, y)) {
			System.out.println("Coordinates of (" + Integer.toString(x) + "," + Integer.toString(y) + ") out of bounds; no piece removed.");
		}

		else if (tbr == null) {
			System.out.println("No piece to be removed at coordinates (" + Integer.toString(x) + "," + Integer.toString(y) + ").");
		}

		else {
			pieces[x][y] = null;
		}

		return tbr; 

	}

	public void select(int x, int y) {
		Piece nextPiece = pieceAt(x, y); 
		if (nextPiece != null) {
			selectedPiece = nextPiece;
			selectedPiecex = x;
			selectedPiecey = y; 	
		}

		else {
			selectedPiece.move(x, y);
			pieces[selectedPiecex][selectedPiecey] = null;
			selectedPiecex = x;
			selectedPiecey = y;
			pieces[x][y] = selectedPiece;
			hasMoved = true;

			if ((selectedPiece.hasCaptured()) && (selectedPiece.isBomb())) {
				remove(x, y);
			}
		}	 
	}

	public boolean canSelect(int x, int y) {
		if (outOfBounds(x, y)){
			return false;
		}

		Piece potentialSelect = pieceAt(x, y);

		if (potentialSelect != null) {

			if (((turn % 2) == potentialSelect.side()) && ((selectedPiece == null) || (!hasMoved))) {

				return true; 				
			} 
		}

		else {

			if ((selectedPiece != null) && (!hasMoved)) {
				return validMove(selectedPiecex, selectedPiecey, x, y); 
			}
			
			if ((selectedPiece != null) && (selectedPiece.hasCaptured()))
				return validCapture(selectedPiecex, selectedPiecey, x, y);
		}

		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean onestep = validNStepMove(xi, yi, xf, yf, 1);
		boolean capture = validCapture(xi, yi, xf, yf);
		return (onestep || capture); 
	}

	//Assumes there is a piece at (xi, yi) and (xf, yf) is an empty location on the grid. 	
	private boolean validNStepMove(int xi, int yi, int xf, int yf, int n) {
		Piece mover = pieceAt(xi, yi); 

		if (Math.abs(xf - xi) == n) {
			if (mover.isKing()) {
				if (Math.abs(yf - yi) == n) {
					return true; 
				}
			}

			else if (mover.isFire()) {
				if (yf - yi == n) {
					return true;
				}
			}

			else {
				if (yi - yf == n) {
					return true;
				}
			}
		}
		return false; 
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece mover = pieceAt(xi, yi);
		if (validNStepMove(xi, yi, xf, yf, 2)) {
			int capturedx = ((xi + xf) / 2); 
			int capturedy = ((yi + yf) / 2);
			Piece captured = pieceAt(capturedx, capturedy); 
			if (captured == null) {
				return false;
			}

			if (mover.isFire() != captured.isFire()) {
				return true;
			}
		}
		return false; 
	}

	public boolean canEndTurn() {
		if (selectedPiece != null) {
			if ((hasMoved) || (selectedPiece.hasCaptured())) {
				return true;
			}
		}

		return false;
	}

	public void endTurn() {
		turn += 1; 
		selectedPiece.doneCapturing(); 
		selectedPiece = null; 
		selectedPiecex = 0;
		selectedPiecey = 0;
		hasMoved = false;
		winner();  
	}

	public String winner() {
		int waterCount = 0;
		int fireCount = 0;
		for (int x = 0; x < size; x += 1) {
			for (int y = 0; y < size; y += 1) {
				Piece piece = pieces[x][y];
				if (piece != null) {
					if (piece.isFire()) {
						fireCount += 1;
					}

					else {
						waterCount += 1;
					}
				} 				
			}
		}

		if ((fireCount == 0) && (waterCount == 0)) {
			return "No one";
		}

		else if (fireCount == 0) {
			return "Water";
		}

		else if (waterCount == 0) {
			return "Fire";
		}

		else {
			return null; 
		}
	}

	private void handleMouse() {
		double X = StdDrawPlus.mouseX();
		double Y = StdDrawPlus.mouseY();
		int x = (int) X;
		int y = (int) Y; 
		if (canSelect(x, y)) {
			select(x, y);
		}
		System.out.println("Clicked at (" + Integer.toString(x) + "," + Integer.toString(y) + ").");
	}

	private void handleSpacebar() {
		if (canEndTurn()) {
			endTurn();
		}
		System.out.println("Pressed spacebar.");
	}

	public static void main(String[] args) {
		Board b = new Board(false); 
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		while(true) {
            b.drawBoard(); 
            if (StdDrawPlus.mousePressed()) {
                b.handleMouse(); 
            } 
            if (StdDrawPlus.isSpacePressed()) {
            	b.handleSpacebar();
            }      
            StdDrawPlus.show(100); 
		}
	}
}
