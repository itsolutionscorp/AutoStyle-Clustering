public class Board {

	private Piece[][] pieces;
	private boolean empty;
	private int size;
	private boolean fireTurn;
	private boolean pieceMoved;
	private Piece selectedPiece;
	private int selectedPiecex;
	private int selectedPiecey;
	private boolean fireExists;
	private boolean waterExists;

	public static void main(String[] args) {

		Board b = new Board(false);
		StdDrawPlus.setXscale(0, b.size);
	    StdDrawPlus.setYscale(0, b.size);

		while(true) {
			b.drawBoard(b.size);
			if(StdDrawPlus.mousePressed()) {
				if (b.canSelect((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY())) {
					b.selectedPiecex = (int) StdDrawPlus.mouseX();
					b.selectedPiecey = (int) StdDrawPlus.mouseY();
					b.select(b.selectedPiecex, b.selectedPiecey);
				}
			}

			if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
				b.winner();
			}

			StdDrawPlus.show(100);
		}

	}

	public Board (boolean shouldBeEmpty) {

		empty = shouldBeEmpty;
		size = 8;
		pieces = new Piece[size][size];
		fireTurn = true;
		selectedPiece = null;
		selectedPiecex = -1;
		selectedPiecey = -1;
		pieceMoved = false;
		waterExists = false;
		fireExists = false;

		if (!empty) {
			for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	            	if (j == 0 && i % 2 == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					}
					else if (j == 1 && i % 2 == 1) {
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					else if (j == 2 && i % 2 == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
					else if (j == 5 && i % 2 == 1) {
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
					else if (j == 6 && i % 2 == 0) {
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					}
					else if (j == 7 && i % 2 == 1) {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}

	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selectedPiecex == i && selectedPiecey == j)  StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece temp = pieceAt(i, j);
	            if (!empty) {   
	                if (temp != null) {
	                	if (temp.isBomb()) {
		                	if (temp.isFire() && temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		                    else if (temp.isFire() && !temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		                    else if (!temp.isFire() && temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		                    else if (!temp.isFire() && !temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                    }
	                	else if (temp.isShield()) {
		                    if (temp.isFire() && temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		                    else if (temp.isFire() && !temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		                    else if (!temp.isFire() && temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		                    else if (!temp.isFire() && !temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                	}
	                	else {
	                		if (temp.isFire() && temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		                    else if (temp.isFire() && !temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		                    else if (!temp.isFire() && temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		                    else if (!temp.isFire() && !temp.isKing())
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                }
	            }
            }
        }
    }

	public Piece pieceAt(int x, int y) {

		if (x >= size || y >= size || x < 0 || y < 0)
			return null;
		return pieces[x][y];

	}

	public boolean canSelect(int x, int y) {

		if (fireTurn) {
			if (pieceAt(x, y) != null) {
				if (pieceAt(x, y).isFire()) {
					if (selectedPiece == null && !pieceMoved) {
						return true;
					}
					else {
						if (!pieceMoved) {
							return true;
						}
						else {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}

			else {

				if (selectedPiece != null) {
					if (validMove(selectedPiecex, selectedPiecey, x, y) && (!pieceMoved || (selectedPiecex - 2 == x || selectedPiecex + 2 == x))) {
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
		}

		//water turn
		else {
			if (pieceAt(x, y) != null) {
				if (!pieceAt(x, y).isFire()) {
					if (selectedPiece == null && !pieceMoved) {
						return true;
					}
					else {
						if (!pieceMoved) {
							return true;
						}
						else {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}

			else {

				if (selectedPiece != null) {
					if (validMove(selectedPiecex, selectedPiecey, x, y) && (!pieceMoved || (selectedPiecex - 2 == x || selectedPiecex + 2 == x))) {
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
		}

	}

	private boolean validMove(int xi, int yi, int xf, int yf) {

		Piece temp = pieceAt(xi, yi);
		if (pieceAt(xf, yf) == null && temp != null) {
			if (temp.isFire()) {
				if ((xf - xi == 1 || xi - xf == 1) && (yf - yi == 1)) {
					return true;
				}
				else if ((xf - xi == 2) && (yf - yi == 2) && (pieceAt(xf - 1, yf - 1) != null) && (!pieceAt(xf - 1, yf - 1).isFire())) {
					return true;
				}
				else if ((xi - xf == 2) && (yf - yi == 2) && (pieceAt(xi - 1, yf - 1) != null) && (!pieceAt(xi - 1, yf - 1).isFire())) {
					return true;
				}
				// havent implemented if crowned yet
				else if ((temp.isKing() && (xf - xi == 1 || xi - xf == 1) && (yi - yf == 1))) {
					return true;
				}
				else if (temp.isKing() && (xf - xi == 2) && (yi - yf == 2) && (pieceAt(xf - 1, yi - 1) != null) && (!pieceAt(xf - 1, yi - 1).isFire())) {
					return true;
				}
				else if (temp.isKing() && (xi - xf == 2) && (yi - yi == 2) && (pieceAt(xi - 1, yi - 1) != null) && (!pieceAt(xi - 1, yi - 1).isFire())) {
					return true;
				}

				else {
					return false;
				}
			}
			// water pieces
			else {
				if ((xf - xi == 1 || xi - xf == 1) && (yi - yf == 1)) {
					return true;
				}
				else if ((xf - xi == 2) && (yi - yf == 2) && (pieceAt(xf - 1, yi - 1) != null) && (pieceAt(xf - 1, yi - 1).isFire())) {
					return true;
				}
				else if ((xi - xf == 2) && (yi - yf == 2) && (pieceAt(xi - 1, yi - 1) != null) && (pieceAt(xi - 1, yi - 1).isFire())) {
					return true;
				}
				// havent implemented if crowned yet
				else if ((temp.isKing() && (xf - xi == 1 || xi - xf == 1) && (yf - yi == 1))) {
					return true;
				}
				else if (temp.isKing() && (xf - xi == 2) && (yf - yi == 2) && (pieceAt(xf - 1, yf - 1) != null) && (pieceAt(xf - 1, yf - 1).isFire())) {
					return true;
				}
				else if (temp.isKing() && (xi - xf == 2) && (yf - yi == 2) && (pieceAt(xi - 1, yf - 1) != null) && (pieceAt(xi - 1, yf - 1).isFire())) {
					return true;
				}

				else {
					return false;
				}

			}

		}
		else {
			return false;
		}
		
	}

	public void select(int x, int y) {

		if (pieceAt(x, y) == null && selectedPiece != null) {
			selectedPiece.move(x, y);
			selectedPiecex = x;
			selectedPiecey = y;
			if(!selectedPiece.hasCaptured()) 
				selectedPiece = null;
		}
		else {
			selectedPiece = pieceAt(x, y);
			selectedPiecex = x;
			selectedPiecey = y;
		}

	}

	public void place(Piece p, int x, int y) {

		if (!(x >= size || y >= size || x < 0 || y < 0 || p == null)) {
			pieces[x][y] = p;
		}

	}

	public Piece remove(int x, int y) {

		if (x >= size || y >= size || x < 0 || y < 0) {
			System.out.println("Selected place is out of bounds");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("No piece in selected place");
			return null;
		}
		else {
			Piece temp = pieceAt(x, y);
			pieces[x][y] = null;
			pieceMoved = true;
			return temp;

		}
		

	}

	public boolean canEndTurn() {

		if (pieceMoved)
			return true;
		return false;

	}

	public void endTurn() {

		fireTurn = !fireTurn;
		selectedPiece = null;
		selectedPiecex = -1;
		selectedPiecey = -1;
		pieceMoved = false;
		waterExists = false;
		fireExists = false;
		for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	        	if (pieceAt(i, j) != null) {
	        		pieceAt(i, j).doneCapturing();
	        	}
	        }
	    }

	}

	public String winner() {

		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (pieceAt(i, j) != null) {
            		if (pieceAt(i, j).isFire())
            			fireExists = true;
            		else
            			waterExists = true;
            	} 
        	}
       	}

       	if (waterExists && fireExists)
       		return null;
       	else if (waterExists)
       		return "Water";
       	else if (fireExists)
       		return "Fire";
       	else
       		return "No one";



	}

}