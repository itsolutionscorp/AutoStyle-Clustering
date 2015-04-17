

public class Board {

	private int N = 8;
	private int w = 1;
	private int h = 1;
	private Piece[][] pieces;
	private int currentPlayer = 0; //fire player is 0, water player is 1
	private boolean hasBeenSelected = false; //change per turn
	private boolean playerHasMoved = false; //change per turn
	private int selectedPieceX, selectedPieceY;
	private boolean playerHasCapturedB = false; //in capture change this value per turn
	
	//STILL NEED TO SWITCH PLAYERS AND REMOVE PIECES AND GET PLAYER OUT OF CORNER

	public static void main(String args[]) {
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		while (true) {
			b.boardHelper(N);
        	if (StdDrawPlus.mousePressed()) {
            	int x = (int)StdDrawPlus.mouseX();
            	int y = (int)StdDrawPlus.mouseY();
            	b.select(x, y);
        	}          
        	StdDrawPlus.show(100);
        }
	}


	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		 if (shouldBeEmpty == false) {
		 	pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		 	pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		 	pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		 	pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		 	pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		 	pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		 	pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		 	pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		 	pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		 	pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		 	pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		 	pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

		 	pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		 	pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		 	pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		 	pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		 	pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		 	pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		 	pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		 	pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		 	pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		 	pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		 	pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		 	pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		}
		playerHasMoved = false;
	}

	private void boardHelper(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if ((i == selectedPieceX) && (j == selectedPieceY) && hasBeenSelected) { 
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
        		if (pieces[i][j] != null) {
            		if (pieceAt(i, j).isFire()) {
	            		if (pieceAt(i, j).isShield()) {
	            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", w, h);
	            		}
	            		else if (pieceAt(i, j).isBomb()) {
	            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", w, h);
	            		}
	            		else {
	            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", w, h);
	            		}
	            	}
	            	else {
	            		if (pieceAt(i, j).isShield()) {
	            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", w, h);
	            		}
	            		else if (pieceAt(i, j).isBomb()) {
	            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", w, h);
	            		}
	            		else {
	            			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", w, h);
	            		}
	            	}	
            	}
            	else {
            		if ((i + j) % 2 == 0) {
            			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            		}
            		else {
            			StdDrawPlus.setPenColor(StdDrawPlus.RED);
            			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            		}
            		
            	}
            }
        }
    }
    


	//gives piece at given coordinates
	public Piece pieceAt(int x, int y) {
		if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {
			return null;
		}
		else if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		// if (pieceAt(x, y) != null) {
		// 	if ((currentPlayer % 2 == 0) && (pieceAt(x, y).isFire() == false)) {
		// 		return false;
		// 	}
		// 	if ((currentPlayer % 2 != 0) && (pieceAt(x, y).isFire())) {
		// 		return false;
		// 	}
		// }	
		if (!hasBeenSelected) {
			if (pieces[x][y] == null) {
				return false;
			}
			else {
				Piece thisPiece = pieceAt(x, y);
				if ((thisPiece.side() % 2) != (currentPlayer % 2)) {
					return false;
				}
			}
			return true;
		}
		else {
			if (!playerHasMoved) {
				Piece select = pieceAt(selectedPieceX, selectedPieceY);
				if (pieces[x][y] != null) {
					Piece nextSelect = pieceAt(x, y);
					if ((select != null) && (nextSelect.side() == select.side())) {
						return true;
					}
					return false;
				}
				else {
					return validMove(selectedPieceX, selectedPieceY, x , y);
				}
			}
			else {
				return validMove(selectedPieceX, selectedPieceY, x, y);
			}
		}
	}	

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((playerHasMoved) && (playerHasCapturedB == false)) {
			return false;
		}
		if ((xf < 0) || (xf > 7) || (yf < 0) || (yf > 7)) {
			return false;
		}
		else if (pieces[xi][yi] == null) {
			return false;
		}
		else if (pieces[xf][yf] != null) {
			return false;
		}
		else {
			if (pieceAt(xi, yi).isFire()) {
				return validFireMove(xi, yi, xf, yf);
			}
			else {
				return validWaterMove(xi, yi, xf, yf);
			}
		}
	}

	private boolean validFireMove(int xi, int yi, int xf, int yf) {
		if (currentPlayer % 2 != 0) {
			return false;
		}
		if (pieceAt(xi, yi).isKing()) {
			return validKingMove(xi, yi, xf, yf);
		}
		
		if (!pieceAt(xi, yi).hasCaptured()) {
			if (((xi - xf == -1) || (xi - xf == 1)) && yi - yf == -1) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if ((xi - xf == -2) && (yi - yf == -2)) {
				if (pieces[xi + 1][yi + 1] != null) {
					return true;
				}				
			}
			else if ((xi - xf == 2) && (yi - yf == -2)) {
				if (pieces[xi - 1][yi + 1] != null) {
					return true;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

	private boolean validWaterMove(int xi, int yi, int xf, int yf) {
		if (currentPlayer % 2 == 0) {
			return false;
		}

		if (pieceAt(xi, yi).isKing()) {
			return validKingMove(xi, yi, xf, yf);
		}
		if (!pieceAt(xi, yi).hasCaptured()) {
			if (((xi - xf == -1) || (xi - xf == 1)) && yi - yf == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if ((xi - xf == -2) && (yi - yf == 2)) {
				if (pieces[xi + 1][yi - 1] != null) {
					return true;
				}				
			}
			else if ((xi - xf == 2) && (yi - yf == 2)) {
				if (pieces[xi - 1][yi - 1] != null) {
					return true;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

	private boolean validKingMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi).isFire() && currentPlayer % 2 != 0) {
			return false;
		}
		if ((pieceAt(xi, yi).isFire() == false) && currentPlayer % 2 == 0) {
			return false;
		}
		if (pieces[xf][yf] != null) {
			return false;
		}
		else {
			if (!pieceAt(xi, yi).hasCaptured()) {
				if (((xi - xf == -1) || (xi - xf == 1)) && ((yi - yf == -1) || (yi - yf == 1))) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if ((xi - xf == -2) && (yi - yf == 2)) {
					if (pieces[xi + 1][yi - 1] != null) {
						return true;
					}				
				}
				else if ((xi - xf == 2) && (yi - yf == 2)) {
					if (pieces[xi - 1][yi - 1] != null) {
						return true;
					}
				}
				else if ((xi - xf == -2) && (yi - yf == -2)) {
					if (pieces[xi + 1][yi + 1] != null) {
						return true;
					}				
				}
				else if ((xi - xf == 2) && (yi - yf == -2)) {
					if (pieces[xi - 1][yi + 1] != null) {
						return true;
					}
				}
				return false;
			}
		}
	}

	public void select(int x, int y) {

		if ((canSelect(x, y) && hasBeenSelected)) {
			Piece select = pieceAt(selectedPieceX, selectedPieceY);
			selectedPieceX = x;
			selectedPieceY = y;
			select.move(x, y);
			pieces[x][y] = select;
		}
		else if ((canSelect(x, y) && hasBeenSelected == false)) {
			hasBeenSelected = true;
			selectedPieceX = x;
			selectedPieceY = y;
		}
		System.out.println(selectedPieceX);
		System.out.println(selectedPieceY);
	}
	

	public void place(Piece p, int x, int y) {
		if ((x > 8) || (y > 8) || (x < 0) || (y < 0)) {
			return;
		}
		else if (p == null) {
			return;
		}
		else {
			pieces[x][y] = p;		}
	}

	public Piece remove(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			System.out.println("Coordinates out of bounds");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("No piece on selected space");
			return null;
		}
		else {
			Piece removedPiece = pieces[x][y];
			pieces[x][y] = null;
			playerHasMoved = true;
			System.out.println("hola");
			return removedPiece;
		}
	}

	public boolean canEndTurn() {
		if (StdDrawPlus.isSpacePressed()) {
			if (playerHasCapturedB) {
				return true;
			}
			else if (playerHasMoved) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		if (canEndTurn()) {
			currentPlayer += 1;
			hasBeenSelected = false;
			playerHasMoved = false;
			playerHasCapturedB = false;
		}
	}

	public String winner() {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] == null) {
            		return "No one";
            	}
            	else if (pieceAt(i, j).isFire()) {
            		return "Fire";
            	}
            	else if (pieceAt(i, j).isFire() == false) {
            		return "Water";
            	}
            }	            
		}
		return null;
	}

}