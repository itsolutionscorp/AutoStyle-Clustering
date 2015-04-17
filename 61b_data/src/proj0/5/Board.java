//Currents goals/issues //
//multicapture
//validcaptureking

public class Board {
	private boolean empty;
	private boolean[][] pieces;
	private Piece[][] typepieces;
	//true = fire, false = water (fire has 1st turn)//
	private boolean fireturn;
	private Piece selectedpiece;
	private Piece placedpiece;
	private int watercounter;
	private int firecounter;
	private int placedx;
	private int placedy;
	
	
	public static void main(String args[]) {
		Board b = new Board(false);
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		while(true) {
			b.drawBoard(N);         
			StdDrawPlus.show(100);
	        //Placed piece does not exist or a piece has just captured
			if (b.placedpiece == null || b.placedpiece.hasCaptured()) { 
				if (StdDrawPlus.mousePressed() ) {
		            int x = (int) StdDrawPlus.mouseX();
		            int y = (int) StdDrawPlus.mouseY();
					if (b.placedpiece == null && b.canSelect(x, y)) {
						b.select(x, y);
					}
					//for the purpose of multiple captures
					else if (b.canSelect(x,y) && b.placedpiece != null) {
						b.select(x, y);
					}
					//If a multiple capture does not exist
					else if (b.canSelect(x,y) == false && b.placedpiece != null) {
						b.placedpiece.doneCapturing();
					}
				}
			}
			else if (b.canEndTurn()) {
				if (StdDrawPlus.isSpacePressed()) {
					b.endTurn();
				}
			}
		}
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i = i + 1) {
			for (int j = 0; j < N; j = j + 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j]) {
					if (typepieces[i][j].isFire()) {
						if (typepieces[i][j].isBomb() && typepieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
						}
						else if (typepieces[i][j].isBomb() && !typepieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						}
						else if (typepieces[i][j].isShield() && typepieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
						}
						else if (typepieces[i][j].isShield() && !typepieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						}
						else {
							if (typepieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
				}
				else {
					if (typepieces[i][j].isBomb() && typepieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					}
					else if (typepieces[i][j].isBomb() && !typepieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					}
					else if (typepieces[i][j].isShield() && typepieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					}
					else if (typepieces[i][j].isShield() && !typepieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					}
					else {
						if (typepieces[i][j].isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
			else {
				winner();
			}
		}
	}
}
	
	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty; 
		pieces = new boolean[8][8];
		typepieces = new Piece[8][8];
		fireturn = true;
		selectedpiece = null;
		placedpiece = null;
		watercounter = 0;
		firecounter = 0;
		placedx = 0;
		placedy = 0;
		if (empty) {
		}
		else {
			for (int i = 0; i < 8; i = i + 1) {
				for (int j = 0; j < 8; j = j + 1) {
					if (i % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = true;
							typepieces[i][j] = new Piece(true, this, i, j, "pawn");
						}
						else if (j == 2) {
							pieces[i][j] = true;
							typepieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
						else if (j == 6) {
							pieces[i][j] = true;
							typepieces[i][j] = new Piece(false, this, i, j, "shield");
						}
					}
					else if (Math.abs(i % 2) == 1) {
						if (j == 1) {
							pieces[i][j] = true;
							typepieces[i][j] = new Piece(true, this, i, j, "shield");
						}
						else if (j == 5) {
							pieces[i][j] = true;
							typepieces[i][j] = new Piece(false, this, i, j, "bomb");
						}
						else if (j == 7) {
							pieces[i][j] = true;
							typepieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
	}
}
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else if (pieces[x][y]) {
			return typepieces[x][y];
		}
		return null;
	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
			if ((xf < 8 && yf <8) && (xf >= 0 && yf >= 0)) {
				if (!pieceAt(xi, yi).isKing()) {
					if (pieceAt(xi, yi).isFire()) {
						if (pieces[xf][yf] == false) {
							if ((xi + 1 == xf && yi + 1 == yf) || (xi - 1 == xf && yi + 1 == yf)) {
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
					else {
						if (pieces[xf][yf] == false) {
							if ((xi + 1 == xf && yi - 1 == yf) || (xi - 1 == xf && yi - 1 == yf)) {
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
				else {
					if ((xi + 1 == xf && yi - 1 == yf) || (xi - 1 == xf && yi - 1 == yf) || (xi + 1 == xf && yi + 1 == yf) || (xi - 1 == xf && yi + 1 == yf)) {
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
		
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		if ((xf < 8 && yf < 8) && (xf >= 0 && yf >= 0)) {
			if (!typepieces[xi][yi].isKing()) {
				if (typepieces[xi][yi].isFire()) {
					if (pieces[(xi + xf) / 2][(yi + yf) / 2] && pieces[xf][yf] == false) {
						System.out.println("w");						
						return !typepieces[(xi + xf) / 2][(yi + yf) / 2].isFire();
						}
						else {
							return false;
						}
					}
				else {
					if (pieces[(xi + xf) / 2][(yi + yf) / 2] && !pieces[xf][yf]) {
						return typepieces[(xi + xf) / 2][(yi + yf) / 2].isFire();
					}
				}
			}
			else {
				return false;
			}//addkingstuff//
	}
	else {
		return false;
	}
	return false;
}
	
	public boolean canSelect(int x, int y) {
		if (selectedpiece == null && pieces[x][y]) {
			if (fireturn == typepieces[x][y].isFire()) {
				if (validMove(x, y, x + 1, y + 1) && (x < 7 && y < 7)) {
					return true;
				}
				else if (validMove(x, y, x - 1, y + 1) && (x >= 1 && y < 7)) {
					return true;
				}
				else if (validMove(x, y, x + 1, y - 1) && (x < 7 && y >= 1)) {
					return true;
				}
				else if (validMove(x, y, x - 1, y - 1) && (x >= 1 && y >= 1)) {
					return true;
				} 
				else if (typepieces[x][y].isFire()) {
					if ((validCapture(x, y, x + 2, y + 2) && (x <= 5 && y <= 5)) || (validCapture(x, y, x - 2, y + 2) && (x >= 2 && y <= 5))) {
						return true;
					}
				}
				else if (validCapture(x, y, x - 2, y - 2) && (x >= 2 && y >= 2)) {
					return true;
				}
				else if (validCapture(x, y, x + 2, y - 2) && (x <= 5 && y >=2)) {
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
		//Purpose of multiple captures
		else if (placedpiece != null && placedpiece.hasCaptured()) {
					if (placedpiece.isFire()) {
						System.out.println("d");
						if ((validCapture(placedx, placedy, placedx + 2, placedy + 2) && (placedx <= 5 && placedy <= 5)) || (validCapture(placedx, placedy, placedx - 2, placedy + 2) && (placedx >= 2 && placedy <= 5))) {
							System.out.println("yay");
							return true;
						}
					}
					else if (validCapture(placedx, placedy, placedx - 2, placedy - 2) && (x >= 2 && y >= 2)) {
						return true;
					}
					else if (validCapture(placedx, placedy, placedx + 2, placedy - 2) && (x <= 5 && y >=2)) {
						return true;
					}
					else {
						return false;
					}
				}
		else if (selectedpiece != null && !pieces[x][y]) {
			if (fireturn == selectedpiece.isFire()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (selectedpiece != null && pieces[x][y] && placedpiece == null) {
			if (fireturn == selectedpiece.isFire() && typepieces[x][y].isFire() == fireturn) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		return false;
	}
	

	public void select(int x, int y) {
			if (pieces[x][y]) {
				selectedpiece = pieceAt(x, y);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				if (selectedpiece.isFire()) {
					if (selectedpiece.isBomb() && selectedpiece.isKing()) {
						StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
					}
					else if (selectedpiece.isBomb() && !selectedpiece.isKing()) {
						StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
					}
					else if (selectedpiece.isShield() && selectedpiece.isKing()) {
						StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
					}
					else if (selectedpiece.isShield() && !selectedpiece.isKing()) {
						StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
					}
					else {
						if (selectedpiece.isKing()) {
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
						}
						else {
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
						}
					}
			}
			else {
				if (selectedpiece.isBomb() && selectedpiece.isKing()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
				}
				else if (selectedpiece.isBomb() && !selectedpiece.isKing()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				}
				else if (selectedpiece.isShield() && selectedpiece.isKing()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
				}
				else if (selectedpiece.isShield() && !selectedpiece.isKing()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
				else {
					if (selectedpiece.isKing()) {
						StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
					}
					else {
						StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
					}
				}
			}
				StdDrawPlus.show(100);
			}	
			else if (!pieces[x][y]) {
				selectedpiece.move(x,y);
			}
		}
		
	
	
	public void place(Piece p, int x, int y) {
	    if ((x > 7 || y > 7) || (x < 0 || y < 0)) {
			return;
		}
		else if (pieces[x][y]) {
			typepieces[x][y] = p;
		}
		else {
			placedpiece = p;
			pieces[x][y] = true;
			typepieces[x][y] = placedpiece;
		}		
	}
	
	public Piece remove(int x, int y) {
		Piece removedpiece = typepieces[x][y];
		pieces[x][y] = false;
		typepieces[x][y] = null;
		return removedpiece;
	}
	
	public boolean canEndTurn() {
		if (placedpiece != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void endTurn() {
		if (placedpiece.isFire() == true) {
			fireturn = false;
		}
		else {
			fireturn = true;
		}
		placedpiece.doneCapturing();
		selectedpiece = null;
		placedpiece = null;
		winner();
		watercounter = 0;
		firecounter = 0;
		System.out.println(winner());
		if (fireturn) {
			System.out.println("Fire's turn");
		}
		else {
			System.out.println("Water's turn");
		}
	}
	public String winner() {
		for (int x = 0; x < 8; x = x + 1) {
			for (int y = 0; y < 8; y = y + 1) {
				if (typepieces[x][y] != null) {
					if (typepieces[x][y].isFire()) {
						firecounter = firecounter + 1;
					}
					else {
						watercounter = watercounter + 1;
					}
				}
				else {
				}
			}
		}
		if (watercounter < 1 && firecounter < 1) {
			return "No one";
		}
		else if (watercounter < 1) {
			return "Fire";
		}
		else if (firecounter < 1) {
			return "Water";
		}
		else {
			return null;
		}
}
}
