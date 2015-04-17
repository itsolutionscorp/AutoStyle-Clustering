public class Board {
	private static final int N = 8;
	private boolean emptiness;
	private Piece[][] pieces;

	private int selectedPieceX;
	private int selectedPieceY;
	private Piece selectedPiece;
	private int squareX = -1;
	private int squareY = -1;

	private int ifExistsX;
	private int ifExistsY;

	private boolean hasSelected = false;
	private boolean hasMoved = false;

	private String winner = null;

	private int side = 0; //* 0 is fire. 1 is water *//

	public static void main(String[] args) {
		int xCoord = -1;
		int yCoord = -1;
		Board b = new Board(false);
        while (b.winner() == null) {
        	b.drawBoard(N);
        	b.winner();
        	if (StdDrawPlus.mousePressed()) {
	            xCoord = (int)StdDrawPlus.mouseX();
	            yCoord = (int)StdDrawPlus.mouseY();
	            if (b.canSelect(xCoord, yCoord)) {
	                b.select(xCoord, yCoord);
	            }
	        }  
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
        	StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
	}

	public Board(boolean shouldBeEmpty) {
		emptiness = shouldBeEmpty;
		initPieces();
	}

	private boolean outOfBounds(int x, int y) {
		if (x >= N || x < 0) {
			return true;
		}
		if (y >= N || y < 0) {
			return true;
		}
		return false;
	}
	
	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y)) {
			System.out.println("Piece at (" + x + ", " + y + ") out of bounds.");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("Square at (" + x + ", " + y + ") is empty.");
			return null;
		}
		System.out.println("Found piece at (" + x + ", " + y + ")");
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		System.out.println("Selected piece at (" + selectedPieceX + ", " + selectedPieceY + ")");
		System.out.println("Checking if you can select (" + x + ", " + y + ")");
		if (outOfBounds(x, y)) {
			System.out.println("Invalid select: Square out of bounds.");
			return false;
		}
		if (selectedPiece == null && pieces[x][y] == null) {
			System.out.println("Invalid select: Have not selected piece.");
			return false;
		}
		if (hasMoved == false) {
			if ((pieces[x][y] != null) && (side == 0) && (pieces[x][y].isFire() == true)) {
				System.out.println("Selecting another fire piece.");
				return true;
			}
			if ((pieces[x][y] != null) && (side == 1) && (pieces[x][y].isFire() == false)) {
				System.out.println("Selecting another water piece.");
				return true;
			}
			if (hasSelected == true && pieces[x][y] == null) {
				System.out.println("Have selected piece and now checking if move is valid.");
				return validMove(selectedPieceX, selectedPieceY, x, y);
			}
		}
		if ((selectedPiece!=null) && (selectedPiece.hasCaptured()) == true && (selectedPiece.isBomb() == false)) {
			System.out.println("Checking for multiple capture.");
			if (pieces[x][y] == null) {
				//If king//
				if (selectedPiece.isKing() == true) {
					int tempX = (x+squareX)/2;
					int tempY = (y+squareY)/2;
					if (side == 0) {
						System.out.println("Your piece is a fire king thinking about multiple capture.");
						if ((Math.abs(x-squareX) == 2) && (Math.abs(y-squareY) == 2)) {
							if ((pieces[tempX][tempY] == null) || (pieces[tempX][tempY].isFire())) {
								return false;
							}
							return true;
						}
					}	
					if (side == 1) {
						System.out.println("Your piece is a water king about to multiple capture.");
						if ((Math.abs(x-squareX) == 2) && (Math.abs(y-squareY) == 2)) {
							if ((pieces[tempX][tempY] == null) || (pieces[tempX][tempY].isFire() == false)) {
								return false;
							}
							return true;
						}
					}				
				}
				//If fire//
				if (side == 0) {
					System.out.println("Your piece is a fire piece about to multiple capture.");
					int tempX = (x+squareX)/2;
					int tempY = (y+squareY)/2;					
					if ((Math.abs(x-squareX) == 2) && ((y-squareY) == 2)) {
						if (((pieces[tempX][tempY]) == null) || (pieces[tempX][tempY].isFire())) {
							return false;
						}
						return true;
					}
				//If water//
				}
				if (side == 1) {
					System.out.println("Your piece is a water piece about to multiple capture.");
					int tempX = (x+squareX)/2;
					int tempY = (y+squareY)/2;					
					if ((Math.abs(x-squareX) == 2) && ((squareY-y) == 2)) {
						if ((pieces[tempX][tempY] == null) || (!pieces[tempX][tempY].isFire())) {
							return false;
						}
						return true;	
					}
				}
			}
		}
		System.out.println("Not a valid select.");
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((outOfBounds(xi, yi)) || (outOfBounds(xf, yf))) {
			System.out.println("Move is out of bounds.");
			return false;
		}
		if (pieces[xi][yi] == null) {
			System.out.println("Selected piece is out of bounds.");
			return false;
		}
		if (pieces[xf][yf] != null) {
			System.out.println("The square you want to move to is not empty.");
			return false;
		}
		if (pieces[xi][yi].isKing()) {
			System.out.println("King piece attempting to move.");
			if ((Math.abs(xf-xi) == 1) && (Math.abs(yf-yi) == 1)) {
				return true;
			}
			if ((Math.abs(xf-xi) == 2) && (Math.abs(yf-yi) == 2)) {
				int yPos = (yf+yi)/2;
				int xPos = (xf+xi)/2;
				if (pieces[xPos][yPos] == null) {
					return false;
				}
				return true;
			}
		}
		if (pieces[xi][yi].isFire()) {
			System.out.println("Fire piece attempting to move.");
			if ((Math.abs(xf-xi) == 1) && ((yf-yi) == 1)) {
				return true;
			}
			if ((Math.abs(xf-xi) == 2) && ((yf-yi) == 2)) {	
				int yPos = (yf+yi)/2;
				int xPos = (xf+xi)/2;
				if ((pieces[xPos][yPos] == null) || (pieces[xPos][yPos].isFire())) {
					return false;	
				}
				return true;
			}
		}
		if (pieces[xi][yi].isFire() == false) {
			System.out.println("Water piece attempting to move.");
			if ((Math.abs(xf-xi) == 1) && ((yi-yf) == 1)) {
				return true;
			}
			if ((Math.abs(xf-xi) == 2) && ((yi-yf) == 2)) {
				int yPos = (yf+yi)/2;
				int xPos = (xf+xi)/2;
				if ((pieces[xPos][yPos] == null) || (!pieces[xPos][yPos].isFire())) {
					return false;
				}
				return true;
			}
		}
		System.out.println("Not a valid move.");
		return false;
	}

	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selectedPiece = pieces[x][y];
			hasSelected = true;
			selectedPieceX = x;
			selectedPieceY = y;
			System.out.println("You have selected piece at (" + selectedPieceX + ", " + selectedPieceY + ")");
			}
		else {		
			selectedPiece.move(x, y);
			hasMoved = true;
			place(selectedPiece, x, y);
			remove(selectedPieceX, selectedPieceY);
			selectedPieceX = x;
			selectedPieceY = y;
			System.out.println("You have moved piece to (" + selectedPieceX + ", " + selectedPieceY + ")");
		}
		if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
			remove(x, y);
			System.out.println("You have removed bomb at (" + x + ", " + y + ")");
			squareX = -1;
			squareY = -1;
		}
		else {
			squareX = x;
			squareY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (outOfBounds(x, y)) {
			System.out.println("The location you want to place the piece (" + x + ", " + y + ") is out of bounds.");
		}
		else if (ifExists(p)==true) {
			remove(ifExistsX, ifExistsY);
			pieces[x][y] = p;
			System.out.println("The piece already exists at (" + ifExistsX + ", " + ifExistsY + ")");
			System.out.println("Moving it to (" + x + ", " + y + ")" );
		}
		else {
			pieces[x][y] = p;
		}
	}

	private boolean ifExists(Piece p) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
					if (pieces[i][j] == p) {
						ifExistsX = i;
						ifExistsY = j;
						return true;
					}
					else {
						return false;
					}
            	}		
			}	
		}
		return false;
	}	

	public Piece remove(int x, int y) {
		System.out.println("You are removing the piece at (" + x + ", " + y + ").");
		if (outOfBounds(x, y)) {
			System.out.println("Input is out of bounds.");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("No piece at (" + x + ", " + y + ")");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		drawBoard(N);
		return temp;
	}

	public boolean canEndTurn() {
		if (hasMoved == true) {
			System.out.println("You can end your turn");
			return true;
		}
		return false;
	}

	public void endTurn() {
		System.out.println("You are ending the turn");
		hasSelected = false;
		hasMoved = false;
		if (side == 0) {
			side = 1;
		}
		else {
			side = 0;
		}
		selectedPiece.doneCapturing();
		squareX = -1;
		squareY = -1;
	}

	public String winner() {
        boolean waterPieces = false;
        boolean firePieces = false;		
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
	            	if (pieces[i][j].isFire()) {
	            		firePieces = true;
            		}
            		if (!pieces[i][j].isFire()) {
            			waterPieces = true;
            		}
            	}
        	}
        }    
		if ((waterPieces == false) && (firePieces == false)) {
			System.out.println("The board is empty.");
			winner = "No one";
		}
		if ((waterPieces == false) && (firePieces == true)) {
			winner = "Fire";
		}
		if ((waterPieces == true) && (firePieces == false)) {
			winner = "Water";
		}
		else {
			winner = null;
		}
		return winner;
	}

	// GRAPHICS CODE //
	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            		if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                	if ((i == squareX) && (j == squareY)) {
	                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    					StdDrawPlus.filledSquare(squareX + .5, squareY + .5, .5);
	                	}

	            //* Checks if board should be empty */
	            if (emptiness == true) {
	            	pieces[i][j] = null;
	            }

            	if (pieces[i][j] == null) {
            	}
            	else {
            		boolean checkShield = pieces[i][j].isShield();
            		boolean checkBomb = pieces[i][j].isBomb();
            		boolean checkKing = pieces[i][j].isKing();
            		boolean checkSide = pieces[i][j].isFire();

	                if (checkSide) {
	                	if ((checkShield == true) && (checkKing == false)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                	}
	                	if ((checkBomb) && (checkKing == false)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                	}
	                	if ((checkBomb == false) && (checkShield == false) && (checkKing == false)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                	}
	                	//* Checking if piece is king */
	                	if ((checkShield == true) && (checkKing == true)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                	}
	                	if ((checkBomb) && (checkKing == true)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                	}
	                	if ((checkBomb == false) && (checkShield == false) && (checkKing == true)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                	}	                		                	
	                }
	                else {
	                	if ((checkShield) && (checkKing == false)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                	}
	                	if ((checkBomb) && (checkKing == false)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                	}
	                	if ((checkBomb == false) && (checkShield == false) && (checkKing == false)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                	//* Checking if piece is king */
	                	if ((checkShield == true) && (checkKing == true)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                	}
	                	if ((checkBomb) && (checkKing == true)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                	}
	                	if ((checkBomb == false) && (checkShield == false) && (checkKing == true)) {
	                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                	}	                	
	                }
                }
            }
        }
	}

	private void initPieces() {
		pieces = new Piece[N][N];

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

		pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

		pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		pieces[6][6] = new Piece(false, this, 6, 6, "shield");

		pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
	}

}