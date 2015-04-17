public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean isFireTurn = true;
	private boolean hasMoved = false;
	private boolean shouldBeEmpty;
	private Piece selectedPiece = null;
	private int selectedPieceX;
	private int selectedPieceY;
	
    // Constructor
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					if ((i + j) % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
						} else if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						} else if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
						} else if (j == 5) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
						} else if (j == 6) {
							pieces[i][j] = new Piece(false, this, i, j, "shield");
						} else if (j == 7) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}
	
	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    	getImage(8);
    }
    
    private void getImage(int N) {
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if ((i + j) % 2 == 0) { // gray spaces only
    				if (pieces[i][j] != null) {
    					if (pieces[i][j].isFire() == true) {  // is it a fire piece?
    						if (pieces[i][j].isKing() == true) { // is it crowned?
    							if (pieces[i][j].isBomb() == true) { // is it a bomb?
    								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
    							} else if (pieces[i][j].isShield() == true) { // is it a shield?
    								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
    							} else {
    								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
    							}
    						} else { // not crowned
    							if (pieces[i][j].isBomb() == true) { // is it a bomb?
    								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    							} else if (pieces[i][j].isShield() == true) { // is it a shield?
    								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
    							} else { // it is a pawn
    								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
    							}
    						}
    					} else { // it is a water piece
    						if (pieces[i][j].isKing() == true) { // is it crowned?
    							if (pieces[i][j].isBomb() == true) { // is it a bomb?
    								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
    							} else if (pieces[i][j].isShield() == true) { // is it a shield?
    								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
    							} else {
    								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
    							}
    						} else { // not crowned
    							if (pieces[i][j].isBomb() == true) { // is it a bomb?
    								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
    							} else if (pieces[i][j].isShield() == true) { // is it a shield?
    								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
    							} else { // it is a pawn
    								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    private static void getDefaultImage(int N) {
    	for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if ((i % 2 == 0) && (j == 0)) {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				} else if ((i % 2 == 1) && (j == 1)) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
				} else if ((i % 2 == 0) && (j == 2)) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				} else if ((i % 2 == 1) && (j == 5)) {
					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
				} else if ((i % 2 == 0) && (j == 6)) {
					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
				} else if ((i % 2 == 1) && (j == 7)) {
					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
    }
	
    public Piece pieceAt(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return null;
		} else if (pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}
	
	public boolean canSelect(int x, int y) {
		if (pieceAt(x,y) != null) { // is there a piece in the space?
			if ((isFireTurn == true) && (pieceAt(x,y).isFire() == true)) { // fire's turn and is a fire piece
				if (selectedPiece == null) { // is this the first time selecting a piece?
					return true;
				} else { // previous piece has already been selected
					if (this.hasMoved == false) { // has the player moved the piece?
						return true;
					}
				}
			} else if ((isFireTurn == false) && (pieceAt(x,y).isFire() == false)){ // water's turn and is a water piece
				if (selectedPiece == null) { // has a piece been selected previously?
					return true;
				} else { // previous piece has already been selected
					if (this.hasMoved == false) { // has the player moved the piece?
						return true;
					}
				}
			} 
		} else { // space is empty
			if (selectedPiece != null) { // piece has previously been selected
				if (this.hasMoved == false) { // piece has not been moved yet
					if (validMove(selectedPieceX,selectedPieceY,x,y)) { 
						return true;
					}
				} else if (this.hasMoved == true) { // piece has moved already
					if (selectedPiece.hasCaptured() == true) { // has the piece captured another piece this turn?
						if (validCapture(selectedPieceX,selectedPieceY,x,y)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi]; // initial piece
		if ((xi > -1) && (xi < 8) && (yi > -1) && (yi < 8) && (xf > -1) && (xf < 8) && (yf > -1) && (yf < 8)) { // is it within the boundaries of the board?
			if ((p != null) && (pieces[xf][yf] == null)) { // is the target space empty?
				if (p.isKing() == true) { // is it crowned?
					if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2) { // is the target space two spaces away from p?
						if (pieces[Math.abs(xf+xi)/2][Math.abs(yf+yi)/2] != null){ // is there a piece in between p and target space?
							if ((pieces[Math.abs(xf+xi)/2][Math.abs(yf+yi)/2].isFire() == true) && (p.isFire() == false)) { // is the intermediate piece a fire piece?
								return true;
							} else if ((pieces[Math.abs(xf+xi)/2][Math.abs(yf+yi)/2].isFire() == false) && (p.isFire() == true)) {
								return true;								
							}
						}
					}
				} else if (p.isKing() == false) { // not crowned
					if (p.isFire() == true) { // is p fire?
						if (Math.abs(xf-xi) == 2 && (yi + 2 == yf)) { // is the target space two diagonal spaces above p?
							if (pieces[Math.abs(xf+xi)/2][yi+1] != null) { // is there a piece in between p and target space?
								if (pieces[Math.abs(xf+xi)/2][yi+1].isFire() == false) { // is the intermediate piece water?
									return true;
								}
							}
						}
					} else { // p is water
						if (Math.abs(xf-xi) == 2 && (yi - 2 == yf)) { // is the target space two diagonal spaces below p?
							if (pieces[Math.abs(xf+xi)/2][yi-1] != null) { // is there a piece in between p and target space?
								if (pieces[Math.abs(xf+xi)/2][yi-1].isFire() == true) { // is the intermediate piece fire?
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi]; // initial piece
		if ((xi > -1) && (xi < 8) && (yi > -1) && (yi < 8) && (xf > -1) && (xf < 8) && (yf > -1) && (yf < 8)) { // is it within the boundaries of the board?
			if ((p != null) && (pieces[xf][yf] == null)) { // is the target space empty?
				if (p.isKing() == true) { // is it crowned?
					if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1) { // is the target one space away from p?
						return true;
					} else if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2) { // is the target two spaces away from p?
						if (pieces[Math.abs(xf+xi)/2][Math.abs(yf+yi)/2] != null) { // is there a piece in between p and target space?
							if ((pieces[Math.abs(xf+xi)/2][Math.abs(yf+yi)/2].isFire() == true) && (p.isFire() == false)) { // is the intermediate piece fire?
								return true;
							} else if ((pieces[Math.abs(xf+xi)/2][Math.abs(yf+yi)/2].isFire() == false) && (p.isFire() == true)) { // is the intermediate piece water?
								return true;
							}
						}
					} 
				} else if (p.isKing() == false) { // not crowned
						if (p.isFire() == true) { // is p fire?
							if (Math.abs(xf-xi) == 1 && (yf-yi) == 1) { // is the target one diagonal space above p?
								return true;
							} else if (Math.abs(xf-xi) == 2 && (yi + 2 == yf)) { // is the target space two diagonal spaces above p?
								if (pieces[Math.abs(xf+xi)/2][yi+1] != null) { // is there a piece in between p and target space?
									if (pieces[Math.abs(xf+xi)/2][yi+1].isFire() == false) { // is the intermediate piece water?
										return true;
									}
								}
							}
						} else { // p is not fire --> water
							if ((Math.abs(xf-xi) == 1 && (yi-yf) == 1)) { // is the target one diagonal space below p?
								return true;
							} else if (Math.abs(xf-xi) == 2 && (yi - 2 == yf)) { // is the target space two diagonal spaces below p?
								if (pieces[Math.abs(xf+xi)/2][yi-1] != null) { // is there a piece in between p and target space?
									if (pieces[Math.abs(xf+xi)/2][yi-1].isFire() == true) { // is the intermediate piece fire?
										return true;
									}
								}
							} 
						}
					}
				} 
		} 
		return false;
	}
	
	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selectedPiece = pieces[x][y];
			selectedPieceX = x;
			selectedPieceY = y;
		} else if (selectedPiece != null) {
			selectedPiece.move(x, y);
			selectedPieceX = x;
			selectedPieceY = y;
			this.hasMoved = true;
		}
	}
	
	public void place(Piece p, int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return;
		} else if (p == null) {
			return;
		}
		pieces[x][y] = p;
	}
	
	public Piece remove(int x, int y) {
		Piece temp = pieces[x][y];
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("There is no piece at that position");
			return null;
		}
		pieces[x][y] = null;
		return temp;
	}
	
	public boolean canEndTurn() {
		return this.hasMoved;
	}
	
	public void endTurn() {
		if (isFireTurn == true) {
			isFireTurn = false;
		} else {
			isFireTurn = true;
		}
		this.hasMoved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
	}
	
	public String winner() {
		int numOfFire = 0;
		int numOfWater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) { 
					if (pieces[i][j].isFire() == true) { // piece is fire
						numOfFire = numOfFire + 1;
					} else if (pieces[i][j].isFire() == false) { // piece is water
						numOfWater = numOfWater + 1;
					}
				} 
			}
		} 
		if ((numOfFire == 0) && (numOfWater != 0)) {
			return "Water";
		} else if ((numOfWater == 0) && (numOfFire != 0)) {
			return "Fire";
		} else if ((numOfWater == 0) && (numOfFire == 0)) {
			return "No one";
		} else {
			return null;
		}
	}
	
	public static void main (String args[]) {
		Board b = new Board(false);
		b.drawBoard(8);
		b.getDefaultImage(8);

		while (true) {
			b.drawBoard(8);
			StdDrawPlus.show(100);
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int)x, (int)y) == true) {
					b.select((int)x,(int)y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn() == true) {
					b.endTurn();
					System.out.println("The turn has ended");
				} else {
					System.out.println("Cannot end turn");
				}
			}
		}
	}
	
}