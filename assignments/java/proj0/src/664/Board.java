public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private int xPos = 0;
	private int yPos = 0;
	private int player = 0;
	private boolean pieceSelected = false; 
	private boolean capture = false; 
	private boolean turnComplete = false; 
	private boolean king = false;
	private boolean bomb = false;
	private boolean gameNotice = false;
	private int tempX = -1;
	private int tempY = -1;
	
	public static void main(String args[]) {
		//GUI
		Board b = new Board(false);
		b.refreshBoard();
		while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xTemp = (int) x;
                int yTemp = (int) y;
                if (b.pieceAt(b.xPos, b.yPos) != null){
            		b.king = b.pieceAt(b.xPos, b.yPos).isKing();
                	b.bomb = b.pieceAt(b.xPos, b.yPos).isBomb();
            	}
                if (b.canSelect(xTemp, yTemp)) {
                	b.select(xTemp, yTemp);
                	if (b.pieceAt(b.xPos, b.yPos) != null){
                		b.pieceAt(b.xPos, b.yPos).move(xTemp, yTemp);
                	} else if (b.tempX != -1) {
                		b.pieceAt(b.tempX, b.tempY).move(xTemp, yTemp);
                	}
                }
                b.refreshBoard();
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
            		if (b.pieceAt(b.xPos, b.yPos) != null){
            			b.pieceAt(b.xPos, b.yPos).doneCapturing();
            		}
            		b.win();
            		b.endTurn();
            		b.refreshBoard();
            	}
            }            
        }
	}

	public Board(boolean shouldBeEmpty) {
		//Sets up all the pieces in their starting positions
		pieces = new Piece[8][8];
		pieceSelected = false; 
		capture = false; 
		turnComplete = false; 
		king = false;
		bomb = false;
		gameNotice = false;
		player = 0;
		if (shouldBeEmpty == false){
		
			for (int i = 0; i <= 7; i += 2) {
				pieces[i][0] = new Piece(true, this , i, 0, "pawn");
			}
			for (int i = 1; i <= 7; i += 2) {
				pieces[i][1] = new Piece(true, this , i, 1, "shield");
			}
			for (int i = 0; i <= 7; i += 2) {
				pieces[i][2] = new Piece(true, this , i, 2, "bomb");
			}
			for (int i = 1; i <= 7; i += 2) {
				pieces[i][5] = new Piece(false, this , i, 5, "bomb");
			}
			for (int i = 0; i <= 7; i += 2) {
				pieces[i][6] = new Piece(false, this , i, 6, "shield");
			}
			for (int i = 1; i <= 7; i += 2) {
				pieces[i][7] = new Piece(false, this , i, 7, "pawn");
			}

		}
	}


	private void drawBoard() {
		// Draws the board backgound
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
            }
        }
	}

	private void refreshBoard() {
		//Refresh position of pieces 
		drawBoard();
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null){
					if (pieces[i][j].isFire()) {
						if (pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false){
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							} else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						} else if (pieces[i][j].isShield()){
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
						} else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
						}
					} else {
						if (pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false){
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						} else if (pieces[i][j].isShield()){
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
						} else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y ){
		//Returns the piece at location 
		for (int i = 0; i < 8; i += 1){
			for (int j = 0; j < 8; j += 1){
				if (pieces[i][j] != null && i == x && j == y) {
					return pieces[i][j];
				}
			}
		}
		return null;

	}

	public boolean canSelect(int x, int y) {
		//Returns true if you can select the location, calls validMove if a piece is already selected
		if (pieceAt(x, y) != null && pieceAt(x, y).side() == player && turnComplete == false){
			xPos = x;
			yPos = y;
			return true;
		}
		if (pieceAt(x, y) != null && pieceAt(x, y).side() != player) {
				return false;
			
		}
		if (pieceSelected && (turnComplete == false || capture)) {
			if (validMove(xPos, yPos, x, y)){
				if (pieces[xPos][yPos] != null) {
					Piece temp = pieces[xPos][yPos];
					pieces[xPos][yPos] = null;
					pieces[x][y] = temp;
					tempX = x;
					tempY = y;
				}
				return true;
			}
		}
		return false;

	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		//Returns true if move is valid, also removes pieces captured with the move or resulting explosion
		if (pieceAt(xi, yi) != null) {
			bomb = pieceAt(xi, yi).isBomb();
		}
		if (pieceAt(xf, yf) != null) {
				return false;
		}
		if (king) {
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 && capture == false){
				turnComplete = true;
				return true;
			} else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
				if(pieceAt((xi + xf) / 2, (yi + yf) / 2) != null && pieceAt((xi + xf) / 2, (yi + yf) / 2).side() != player){
					rm((xi + xf) / 2, (yi + yf) / 2);
					if (bomb){
						rm(xi, yi);
						explode(xf, yf);
					} else{
						capture = true;
					}
					turnComplete = true;
					return true;
				}
			} 
		} else if (player == 0) {
			if (Math.abs(xi - xf) == 1 && yf - yi == 1 && capture == false) {
				turnComplete = true;
				return true;
			} else if (Math.abs(xi - xf) == 2 && yf - yi == 2) {
				if(pieceAt((xi + xf) / 2, (yi + yf) / 2) != null && pieceAt((xi + xf) / 2, (yi + yf) / 2).side() != player){
					rm((xi + xf) / 2, (yi + yf) / 2);
					if (bomb){
						rm(xi, yi);
						explode(xf, yf);
					} else{
						capture = true;
					}
					turnComplete = true;
					return true;
				}
			} 
		} else {
			if (Math.abs(xi - xf) == 1 && yi - yf == 1 && capture == false){
				turnComplete = true;
				return true;
			} else if (Math.abs(xi - xf) == 2 && yi - yf == 2) {
				if(pieceAt((xi + xf) / 2, (yi + yf) / 2) != null && pieceAt((xi + xf) / 2, (yi + yf) / 2).side() != player){
					rm((xi + xf) / 2, (yi + yf) / 2);
					if (bomb){
						rm(xi, yi);
						explode(xf, yf);
					} else{
						capture = true;
					}
					turnComplete = true;
					return true;
				}
			} 
		}
		return false;

	}

	private void explode(int x, int y) {
		//removes all non-shield units in explosion radius
		for (int i = x - 1; i <= x + 1; i += 1){
			for(int j = y - 1; j <= y + 1; j += 1){
				if (pieceAt(i, j) != null && pieceAt(i, j).isShield() == false) {
					rm(i, j);
				}
			}
		}
	}

	public void select(int x, int y) {
		//Selects a piece
		if (pieceAt(x, y) != null){
			xPos = x;
			yPos = y;
			pieceSelected = true;
		} else if (pieceAt(xPos, yPos) != null) {
			pieceAt(xPos, yPos).move(x, y);
			xPos = x;
			yPos = y;
		}
	}

	public void place(Piece p, int x, int y) {
		//Places a piece and refreshes board
		pieces[x][y] = p;
			
	}

	public Piece remove(int x, int y) {
		//Removes piece and returns it
		if (x >= 8 || x < 0 || y >= 8 || y < 0){
			System.out.println("Error: Input coordinates out of bounds.");
			return null;
		}
		if (pieces[x][y] != null) {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
		System.out.println("Error: No piece found at designated coordinates.");
		return null;
	}

	private void rm(int x, int y) {
		//Removes piece without returns
		
		pieces[x][y] = null;
		
	}

	public boolean canEndTurn() {
		//Returns if the turn can end
		return turnComplete;

	}

	public void endTurn() {
		//Ends the turn
		if (player == 0){
			player = 1;
		} else {
			player = 0;
		}
		turnComplete = false;
		pieceSelected = false;
		capture = false;
		bomb = false;
		king = false;
		gameNotice = false;
		tempY = -1;
        tempX = -1;
	}

	private boolean fireExtinguished(){
		//Returns true if Fire has 0 units remaining and prints the number of units remaining if not
		int status = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null && pieces[i][j].isFire()) {
					status += 1;
				}
			}
		}
		if (status == 0){
			return true;
		} else {
			if (gameNotice == false){
				System.out.println("Team Fire has " + status + " units remaining.");
				gameNotice = true;
			}
			return false;
		}
	}

	private boolean waterEvaporated() {
		//Returns true if Water has 0 units remaining and prints the number of units remaining if not
		int status = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null && pieces[i][j].isFire() == false) {
					status += 1;
				}
			}
		}
		if (status == 0){
			return true;
		} else {
			System.out.println("Team Water has " + status + " units remaining.");
			return false;
		}
	}

	public String winner() {
		//Returns state of the game and prints it if the game is over
		if (fireExtinguished() && waterEvaporated()) {
			System.out.println("It's a draw.");
			return "No one";
		} else if (fireExtinguished()) {
			System.out.println("Water wins!");
			return "Water";
		} else if (waterEvaporated()) {
			System.out.println("Fire wins!");
				return "Fire";
		}
		return null;

	}

	private void win() {
		//Prints state of game and returns void
		if (fireExtinguished() && waterEvaporated()) {
			System.out.println("It's a draw.");
		} else if (fireExtinguished()) {
			System.out.println("Water wins!");
		} else if (waterEvaporated()) {
			System.out.println("Fire wins!");
		}

	}
}
