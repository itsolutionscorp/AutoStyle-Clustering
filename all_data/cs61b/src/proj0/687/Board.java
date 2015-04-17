public class Board {

	private Piece[][] pieces = new Piece[8][8];

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			populateBoard();
		}
	}

	private String penColor = "Gray";
	
	
	private void altSquareColor() {
		if (penColor.equals("Red")) { 
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			penColor = "Gray";
		}

		else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
			penColor = "Red";
		}
	}

	private void drawBoard() {
		for (int y = 0; y <= 7 ; y++) {
			altSquareColor();
			for (int x = 0; x <= 7; x ++) {
				altSquareColor();
				if (selectedSquare[0] != null) {
					if ((selectedSquare[0] == x) && (selectedSquare[1] == y)){
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					}
				}

				StdDrawPlus.filledSquare(x + 0.5 , y + 0.5 , 0.5);

				if (pieceAt(x,y) != null) {
					drawPiece(x, y);
				}
			}
		}
	}

	private void drawPiece(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p.isFire()) {
			if (!p.isKing()) {
				if (p.isBomb()) {
					StdDrawPlus.picture(x + .5 , y + .5, "img/bomb-fire.png", 1, 1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", 1, 1);
				}
			}

			else {
				if (p.isBomb()) {
					StdDrawPlus.picture(x + .5 , y + .5, "img/bomb-fire-crowned.png", 1, 1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire-crowned.png", 1, 1);
				}
			}	
		}

		else {
			if (!p.isKing()) {
				if (p.isBomb()) {
					StdDrawPlus.picture(x + .5 , y + .5, "img/bomb-water.png", 1, 1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", 1, 1);
				}
			}

			else {

				if (p.isBomb()) {
					StdDrawPlus.picture(x + .5 , y + .5, "img/bomb-water-crowned.png", 1, 1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water-crowned.png", 1, 1);
				}
			}	
		}
	}	

	private void populateBoard() {
		for (int y = 0; y <= 7; y ++) {
			for (int x = 0; x <= 7; x ++) {
				if ((y == 0) && (x % 2 == 0)) {
					pieces[x][y] = new Piece(true, this, x, y, "pawn");
				}

				if ((y == 1) && (x % 2 == 1)) {
					pieces[x][y] = new Piece(true, this, x, y, "shield");

				}

				if ((y == 2) && (x % 2 == 0)) {
					pieces[x][y] = new Piece(true, this, x, y, "bomb");

				}

				if ((y == 5) && (x % 2 == 1)) {
					pieces[x][y] = new Piece(false, this, x, y, "bomb");

				}

				if ((y == 6) && (x % 2 == 0)) {
					pieces[x][y] = new Piece(false, this, x, y, "shield");

				}

				if ((y == 7) && (x % 2 == 1)) {
					pieces[x][y] = new Piece(false, this, x, y, "pawn");
				}
			}
		}
	}

	private boolean pointValid(int x, int y) {
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
			return true;
		}
		else {
			System.out.println( "FATAL ERROR! POINT IS OUT OF RANGE!" );
			return false;
		}
	}

	private boolean pointValid(Piece p, int x, int y) {
		//System.out.println(x);
		//System.out.println(y);
		//System.out.println(p);
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0) && ( p != null)) {
			return true;
		}
		else if (p != null) {
			System.out.println( "FATAL ERROR! PIECE IS A LIE!" );
			return false;
		}

		else {
			System.out.println( "FATAL ERROR! POINT IS OUT OF RANGE!");
			return false;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (pointValid(x, y)) {
			return pieces[x][y];
		}

		return null;
	}

	private boolean hasMoved = false;
	private Piece selectedPiece = null;
	private boolean isFireTurn = true;
 
	public boolean canSelect(int x, int y) {
		if (pointValid(x, y)) {
			if (pieceAt(x, y) != null) {

				if (hasMoved || (pieceAt(x,y).isFire() != isFireTurn) ) {
					return false;
				}
				else {
					return true;
				}
			}

			else {
				if (selectedPiece != null) {
					int xcurr = selectedSquare[0];
					int ycurr = selectedSquare[1];

					if (selectedPiece.isFire()) {

						if (((x - 1 == xcurr) || (x + 1 == xcurr)) && (y - 1 == ycurr) && !hasMoved) {
							return true;
						}

						else if (y - 2 == ycurr) {

							if ((x - 2 == xcurr) && (pieceAt(x - 1, y - 1) != null) && (!pieceAt(x - 1, y - 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
								return true;
							}
							
							else if ((x + 2 == xcurr) && (pieceAt(x + 1, y - 1) != null) && (!pieceAt(x + 1, y - 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
								return true;
							}
						}

						else if (selectedPiece.isKing()) {
							if (((x - 1 == xcurr) || (x + 1 == xcurr)) && (y + 1 == ycurr) && !hasMoved ) {
								return true;
							}

							else if (y + 2 == ycurr) {

								if ((x - 2 == xcurr) && (pieceAt(x - 1, y + 1) != null) && (!pieceAt(x - 1, y + 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
									return true;
								}
								
								else if ((x + 2 == xcurr) && (pieceAt(x + 1, y + 1) != null) && (!pieceAt(x + 1, y + 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
									return true;
								}
							}
						}
					}

					else {

						if (((x - 1 == xcurr) || (x + 1 == xcurr)) && (y + 1 == ycurr) && !hasMoved) {
							return true;
						}

						else if (y + 2 == ycurr) {

							if ((x - 2 == xcurr) && (pieceAt(x - 1, y + 1) != null) && (pieceAt(x - 1, y + 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
								return true;
							}
							
							else if ((x + 2 == xcurr) && (pieceAt(x + 1, y + 1) != null) && (pieceAt(x + 1, y + 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
								return true;
							}
						}

						else if (selectedPiece.isKing()) {
							if (((x - 1 == xcurr) || (x + 1 == xcurr)) && (y - 1 == ycurr) && !hasMoved) {
								return true;
							}

							else if (y - 2 == ycurr) {

								if ((x - 2 == xcurr) && (pieceAt(x - 1, y - 1) != null) && (pieceAt(x - 1, y - 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
									return true;
								}
								
								else if ((x + 2 == xcurr) && (pieceAt(x + 1, y - 1) != null) && (pieceAt(x + 1, y - 1).isFire()) && (!hasMoved || selectedPiece.hasCaptured())) {
									return true;
								}
							}
						}
					}
				}
				return false;
			}
		}
		else {
			return false;
		}
	}

	private Integer[] selectedSquare = new Integer[2];
	public void select(int x, int y) {


			if (pieceAt(x,y) != null) {
				selectedPiece = pieceAt(x,y);
				selectedSquare[0] = x;
				selectedSquare[1] = y;
			}

			else if ((selectedSquare[0] != null) && (selectedSquare[1] != null)) {
				selectedSquare[0] = x;
				selectedSquare[1] = y;
				selectedPiece.move(x,y);
				hasMoved = true;
			}
		
	}


	private boolean isInBoard(Piece p) {
		if (p!= null) {
			for (int y = 0; y <= 7; y++) {
				for (int x = 0; x <= 7; x++) {
					if (p == pieceAt(x, y)) {
						return true;
					}
				}
			}
		}
		return false;
	}



	public void place(Piece p, int x, int y) {
		//System.out.println("got to place");

			pieces[x][y] = p;
	}



	public Piece remove(int x, int y) {
		//System.out.println("got to remove!");
		if (pointValid(pieceAt(x,y), x, y)) {

			Piece p = pieceAt(x, y);
			pieces[x][y] = null;

			if (p.isFire()) {
				countFire = countFire - 1;
			}

			else {
				countWater = countWater - 1;
			}

			return p;
		}

		return null;
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (canEndTurn()) {
			hasMoved = false;
			isFireTurn = !isFireTurn;
			selectedSquare[0] = null;
			selectedSquare[1] = null;
			selectedPiece.doneCapturing();
			selectedPiece = null;
		}

	}


	private int countFire = 12;
	private int countWater = 12;

	public String winner() {
		countWater = 0;
		countFire = 0;

		for (int y = 0; y <= 7 ; y++) {
			for (int x = 0; x <= 7; x ++) {
				if (pieceAt(x, y) != null) {
					if (pieceAt(x, y).isFire()) {
						countFire = countFire + 1;
					}
					else {
						countWater = countWater + 1;
					}
				}
			}
		}

		if ((countFire <= 0) && (countWater <= 0)) {
			return "No one";
		}

		else if (countFire <= 0) {
			return "Water";
		}

		else if (countWater <= 0) {
			return "Fire";
		}

		else {
			return null;
		}
	}

	public static void main(String[] args) {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        
		Board game = new Board(false);


		while (game.winner() == null) {
			game.drawBoard();
			if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();

                if (game.canSelect(x,y)) {
                	game.select(x, y);
                }
            }

            else if (StdDrawPlus.isSpacePressed()) {
            	if (game.canEndTurn()) {
            		game.endTurn();
            	}
            }
			StdDrawPlus.show(10);

		}
	System.out.println(game.winner());
	}



}