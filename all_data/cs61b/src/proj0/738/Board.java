public class Board {
	private Piece[][] piecesList;
	private Piece selectedPiece = null;
	private String currentPlayer = "fire";
	private boolean hasMoved = false;
	private boolean mayMove = true;
	private int xS = 0;
	private int yS = 0;

	public static void main(String[] args) {
		Board theBoard = new Board(false); //creates board instance
	    int i = 0;
		int j = 0;
		int x = 0;
		int y = 0;
		double mouseX;
		double mouseY;
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(true) { //refreshes board
			theBoard.drawBoard(8); //draws board
			if (StdDrawPlus.mousePressed()) {
				mouseX = StdDrawPlus.mouseX();
				mouseY = StdDrawPlus.mouseY();
				if (((int) mouseX < 8) && ((int) mouseY < 8) && ((int) mouseX > -1) && ((int) mouseY > -1)) {
					if (theBoard.canSelect((int) mouseX, (int) mouseY)) {
						theBoard.select((int) mouseX, (int) mouseY); //select the piece
					}
				}
			}			
			StdDrawPlus.show(100);
			if (StdDrawPlus.isSpacePressed()) {
				if (theBoard.canEndTurn()) {
					theBoard.endTurn();
				}
			}
		}
	}
	public Board(boolean shouldBeEmpty) {
		piecesList = new Piece[8][8];
		if (!shouldBeEmpty) {
			int i = 0;
			for (i = 0; i < 8; i += 2) {
				piecesList[i][0] = new Piece(true, this, i, 0, "pawn"); //fills piece array with fire pawns
				piecesList[i][2] = new Piece(true, this, i, 2, "bomb"); //fills piece array with fire bombs
				piecesList[i][6] = new Piece(false, this, i, 6, "shield"); //fills piece array with water sheilds
			}
			for (i = 1; i < 8; i += 2) {
				piecesList[i][1] = new Piece(true, this, i, 1, "shield"); //fills piece array with fire sheilds
				piecesList[i][5] = new Piece(false, this, i, 5, "bomb"); //fills piece array with water bombs
				piecesList[i][7] = new Piece(false, this, i, 7, "pawn"); //fills piece array with water pawns
			}
		}
	}
    private void drawBoard(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if ((selectedPiece != null) && (piecesList[i][j] == selectedPiece)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            	StdDrawPlus.square(i + .5, j + .5, .5);
                }
                if (piecesList[i][j] != null) {
					pieceDraw(i, j);
				}
        	}
        }
	}
	public Piece pieceAt(int x, int y) {
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
			if (piecesList[x][y] != null) {
				return piecesList[x][y];
			}
		}
		return null;
	}
	public boolean canSelect(int x, int y) {
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
			if (currentPlayer == "fire"){ //if the current player is fire
				if (pieceAt(x, y) != null) { //if there is a piece there & if that piece is on your team
					if (piecesList[x][y].isFire()) {
						if (selectedPiece == null) { //if there is not a selected piece
							return true; //you can select this piece
						}
						else {
							if (!hasMoved) { //if there is a selected piece but it hasn't moved
								return true; //you can select this piece
							}
						}
					}
				}
				else { //if there is not a piece there
					if ((selectedPiece != null) && (selectedPiece.isFire())) {
						if (validMove(selectedPiece, xS, yS, x, y)) {
							if ((mayMove)) { //double capture?
								return true; //you can select this place
							}
						}
					}
				}
			}
			else if (currentPlayer == "water") {
				if (pieceAt(x, y) != null) { //if there is a piece there & if that piece is on your team
					if (!piecesList[x][y].isFire()) {
						if (selectedPiece == null) { //if there is not a selected piece
							return true; //you can select this piece
						}
						else {
							if (!hasMoved) { //if there is a selected piece but it hasn't moved
								return true; //you can select this piece
							}
						}
					}
				}
				else { //if there is not a piece there
					if ((selectedPiece != null) && (selectedPiece.isFire())) {
						if (validMove(selectedPiece, xS, yS, x, y)) {
							if ((mayMove)) { //double capture?
								return true; //you can select this place
							}
						}
					}
				}
			}
		}
		return false;
	}
	public void select(int x, int y) {
		if (selectedPiece == null) {
			if (pieceAt(x, y) != null) {
				xS = x;
				yS = y;
				this.selectedPiece = pieceAt(x, y);
			}
		}
		else if (selectedPiece != null) {
			if (pieceAt(x, y) == null) {
				if (validMove(selectedPiece, xS, yS, x, y)) {//if this place can be moved to
					selectedPiece.move(x, y); //move there
					hasMoved = true; //player cannot move again if they have not captured
					if (selectedPiece.hasCaptured()) {
						mayMove = true;
					}
					else {
						mayMove = false;
					}
				}
			}
			else if (pieceAt(x, y) != null) {
				if (!hasMoved) {
					xS = x;
					yS = y;
					this.selectedPiece = pieceAt(x, y);
				}
			}
		}
	}
	public void place(Piece p, int x, int y) {
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
			piecesList[x][y] = p;
			pieceDraw(x, y);
		}
	}
	public Piece remove(int x, int y) {
		Piece q;
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			return null;
		}
		else {
			q = piecesList[x][y];
			piecesList[x][y] = null;
			return q;
		}
	}
	public boolean canEndTurn() {
		if ((selectedPiece != null) && (hasMoved)) {
			return true;
		}
		else {
			return false;
		}
	}
	public void endTurn() {
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		changeTurn(currentPlayer);
		hasMoved = false;
		mayMove = true;
		selectedPiece = null;
	}
	private boolean validMove(Piece p, int x, int y, int m, int n) {
		int i = 0;
		int j = 0;
		if (!p.hasCaptured()) {
			if (!p.isKing()) {
				if (p.isFire()) {
					if (y+2 == n) {
						if (x + 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x+1, y+1) != null) && (!pieceAt(x+1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x - 2 == m) { //to the left
							if ((pieceAt(x-1, y+1) != null) && (!pieceAt(x-1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y+1 == n) {
						if ((x + 1 == m) || (x - 1 == m)) {
							return true;
						}
					}
				}
				else {
					if (y-2 == n) {
						if (x - 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x + 2 == m) { //to the left
							if ((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y-1 == n) {
						if ((x - 1 == m) || (x + 1 == m)) {
							return true;
						}	
					}
				}
			}
			else {
				if (p.isFire()) {
					if (y+2 == n) {
						if (x + 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x+1, y+1) != null) && (!pieceAt(x+1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x - 2 == m) { //to the left
							if ((pieceAt(x-1, y+1) != null) && (!pieceAt(x-1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y+1 == n) {
						if ((x + 1 == m) || (x - 1 == m)) {
							return true;
						}
					}
					else if (y-2 == n) {
						if (x - 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x-1, y-1) != null) && (!pieceAt(x-1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x + 2 == m) { //to the left
							if ((pieceAt(x+1, y-1) != null) && (!pieceAt(x+1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y-1 == n) {
						if ((x - 1 == m) || (x + 1 == m)) {
							return true;
						}	
					}
				}
				else {
					if (y+2 == n) {
						if (x + 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x+1, y+1) != null) && (pieceAt(x+1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x - 2 == m) { //to the left
							if ((pieceAt(x-1, y+1) != null) && (pieceAt(x-1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y+1 == n) {
						if ((x + 1 == m) || (x - 1 == m)) {
							return true;
						}
					}
					else if (y-2 == n) {
						if (x - 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x + 2 == m) { //to the left
							if ((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y-1 == n) {
						if ((x - 1 == m) || (x + 1 == m)) {
							return true;
						}	
					}
				}
			}
		}
		else {
			if (!p.isKing()) {
				if (p.isFire()) {
					if (y+2 == n) {
						if (x + 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x+1, y+1) != null) && (!pieceAt(x+1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x - 2 == m) { //to the left
							if ((pieceAt(x-1, y+1) != null) && (!pieceAt(x-1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
				}
				else {
					if (y-2 == n) {
						if (x - 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x + 2 == m) { //to the left
							if ((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
				}
			}
			else {
				if (p.isFire()) {
					if (y+2 == n) {
						if (x + 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x+1, y+1) != null) && (!pieceAt(x+1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x - 2 == m) { //to the left
							if ((pieceAt(x-1, y+1) != null) && (!pieceAt(x-1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y-2 == n) {
						if (x - 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x-1, y-1) != null) && (!pieceAt(x-1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x + 2 == m) { //to the left
							if ((pieceAt(x+1, y-1) != null) && (!pieceAt(x+1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
				}
				else {
					if (y+2 == n) {
						if (x + 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x+1, y+1) != null) && (pieceAt(x+1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x - 2 == m) { //to the left
							if ((pieceAt(x-1, y+1) != null) && (pieceAt(x-1, y+1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
					else if (y-2 == n) {
						if (x - 2 == m) { //determines if they are trying to capture to the right
							if ((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
						else if (x + 2 == m) { //to the left
							if ((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire())) { //determines if there is an opponent piece to capture
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}


	private void pieceDraw(int x, int y) {
		if (piecesList[x][y].isFire()) {
			if (piecesList[x][y].isKing()) {
				if (piecesList[x][y].isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
				}
				else if (piecesList[x][y].isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
				}
			}
			else {
				if (piecesList[x][y].isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				}
				else if (piecesList[x][y].isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
			}
		}
		else {
			if (piecesList[x][y].isKing()) {
				if (piecesList[x][y].isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
				}
				else if (piecesList[x][y].isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
				}
			}
			else {
				if (piecesList[x][y].isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
				else if (piecesList[x][y].isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				}
				else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
	}

	private void changeTurn(String player) {
		if (currentPlayer == "fire") {
			currentPlayer = "water";
		}
		else {
			currentPlayer = "fire";
		}
	}
	public String winner() {
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (piecesList[i][j] != null) {
					if (piecesList[i][j].isFire() == true) {
						countFire = countFire + 1;
					}
					else {
						countWater = countWater + 1;
					}
				}
			}
		}
		if ((countWater == 0) && (countFire > 0)) {
			return "Fire";
		}
		else if ((countFire == 0) && (countWater > 0)) {
			return "Water";
		}
		else if ((countFire == 0) && (countWater == 0)) {
			return "No one";
		}
		else {
			return null;
		}
	}
}