
public class Board {

	private Piece[][] pieces;
	private int BOARD_SIZE = 8;
	private int turn; // 0 for fire, 1 for water
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private int xSelected;
	private int ySelected;


	public static void main(String args[]){
		// set scale
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, b.BOARD_SIZE);
		StdDrawPlus.setYscale(0, b.BOARD_SIZE);
		
		
		// playing game
		b.drawBoard();
		while (b.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
				int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y) && !b.hasMoved) {
                	b.select(x, y);
                	b.drawBoard();

                }
			}
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
	        		b.endTurn();
	        		System.out.println("Has ended turn");
	        		b.drawBoard();
	            	
	        }
			StdDrawPlus.show(80);
		}

	}
	
	public Board(boolean shouldBeEmpty) {
		turn = 0;
		if (shouldBeEmpty) {
			/* initializes an empty Board */
			pieces = new Piece[8][8];
		}
		else {
			/* initializes default Board */
			pieces = new Piece[8][8];
			setDefaultBoard();
			// place all the pieces in their default positions

		}
	}
	
	// Draws the standard board 8x8
	// Colors: Gray, Red
	private void drawBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				} 
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}

				if (hasSelected && i == xSelected && j == ySelected) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(xSelected + 0.5, ySelected + 0.5, 0.5);
				}

                // Draw the images for the pieces
				if (pieces[i][j] != null) {
					Piece p = pieces[i][j];
					if (p.isFire()) {
						if (p.isBomb()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
						}
						else if (p.isShield()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
						} 
						else {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
						}
					}
					else {
						if (p.isBomb()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
						}
						else if (p.isShield()) {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
						} 
						else {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
	}

	private void setDefaultBoard(){
		String type = "";
		for (int row = 0; row < BOARD_SIZE; row += 1) {
			int start = 1;
			if(row % 2 == 0) {
				start = 0;
			}

			// Places Fire pieces
			if (row < 3) {
				for(int col = start; col < BOARD_SIZE; col += 2) {
					// Determine type
					if (row == 0) {
						type = "pawn";
					}
					else if (row == 1) {
						type = "shield";
					}
					else {
						type = "bomb";
					}
					place(new Piece(true, this, col, row, type), col, row);
				}
			}
			// Places Water pieces
			if (row > 4) {
				for (int col = start; col < BOARD_SIZE; col += 2) {
					// Determine type
					if (row == 7) {
						type = "pawn";
					}
					else if (row == 6) {
						type = "shield";
					}
					else {
						type = "bomb";
					}
					place(new Piece(false, this, col, row, type), col, row);
				}
			}
		}
	}


	public Piece pieceAt(int x, int y){
		if (x > BOARD_SIZE || y > BOARD_SIZE) {
			return null;
		}
		if (pieces[x][y] != null){
			return pieces[x][y];
		}
		else {
			return null;
		}
	}

	public boolean canSelect(int x, int y){
		// if piece in square
		if (pieces[x][y] != null) {
			if (turn == pieces[x][y].side()) {
			// for piece
				if (!hasSelected) {
					return true;
				}
				else if (hasSelected && !hasMoved) {
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
		// for empty square
		else {
			// if something is selected
			// initial coord(x,y) = the selected piece's coordinates
			if (hasSelected && !hasMoved && validMove(xSelected, ySelected, x, y)){
				return true;
			}
			else if (hasSelected && pieces[xSelected][ySelected].hasCaptured() && validMove(xSelected, ySelected, x, y)) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece i = pieces[xi][yi];
		Piece f = pieces[xf][yf];
		// always move to empty space
		if (f == null) {
			// if not king piece
			if (!i.isKing()) {
				int xChange = xf - xi;
				int yChange = yf - yi;

				// if fire, check if empty space is one up and one right or left
				if (Math.abs(xChange) == 1 && Math.abs(yChange) == 1) {
					if (i.side() == 0) {
						if ((yf - 1 == yi) && ((xf - 1 == xi) || (xf + 1 == xi))) {
							return true;
						}
						return false;
					}
					// if water, check if empty space is one down and one right or left
					else {
						if ((yf + 1 == yi) && ((xf - 1 == xi) || (xf + 1 == xi))) {
							return true;
						}
						return false;
					}
				}
				else if (Math.abs(xChange) == 2 && Math.abs(yChange) == 2) {
					// check if can capture
					if (pieces[xi + (xChange / 2)][yi + (yChange / 2)] != null
						&& pieces[xi + (xChange / 2)][yi + (yChange / 2)].side() != turn) {
						if (turn == 0) {
							// if fire, check that piece is moving up
							if (yf > yi) {
								return true;
							}
							return false;
						}
						else {
							// if water, check that piece is moving down
							if (yf < yi) {
								return true;
							}
							return false;
						}
						
					}
					return false;
				}
				else {
					return false;
				}
			}
			// if king piece
			else {
				int xChange = xf - xi;
				int yChange = yf - yi;
				if (Math.abs(xChange) == 1 && Math.abs(yChange) == 1) {
					return true;
				}
				else if (Math.abs(xChange) == 2 && Math.abs(yChange) == 2) {
					if (pieces[xi + (xChange / 2)][yi + (yChange / 2)] != null
						&& pieces[xi + (xChange / 2)][yi + (yChange / 2)].side() != turn) {
						return true;
					}
					return false;
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
	

	public void select(int x, int y){
		if (hasSelected && validMove(xSelected, ySelected, x, y)) {
			pieces[xSelected][ySelected].move(x, y);
			hasMoved = true;
			xSelected = x;
			ySelected = y;
		}
		else {
			xSelected = x;
			ySelected = y;
			hasSelected = true;
		}
		
	}

	
	public void place(Piece p, int x, int y) {
		if (x > BOARD_SIZE || y > BOARD_SIZE || p == null) {
			return;
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y){
		if (x > BOARD_SIZE || y >  BOARD_SIZE || pieces[x][y] == null) {
			return null;
		}
		Piece removed = pieces[x][y];
		pieces[x][y] = null;
		return removed;
	}

	
	public boolean canEndTurn() {
		if (hasMoved || pieces[xSelected][ySelected].hasCaptured()) {
			return true;
		}
		return false;
	}


	public void endTurn() {
		if (pieces[xSelected][ySelected] != null && pieces[xSelected][ySelected].hasCaptured()) {
			pieces[xSelected][ySelected].doneCapturing();
		}
		turn = 1 - turn;
		hasSelected = false;
		hasMoved = false;
	}

	public String winner(){
		int fireCount = 0;
		int waterCount = 0;

		for(int i = 0; i < BOARD_SIZE; i+=1){
			for (int j = 0; j < BOARD_SIZE; j += 1) {
				if (pieces[i][j] != null) {
					System.out.println("----------");
					if (pieces[i][j].isFire()) {
						fireCount += 1;
					}
					else {
						waterCount += 1;
					}
				}
			}
		}
		if (fireCount == 0 && waterCount == 0) {
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

	
}





