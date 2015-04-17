public class Board{

	private Piece[][] pieces;
	private int xSelect = -1;
	private int ySelect = -1;
	private boolean selected = false;
	private boolean moved = false;
	private boolean captured = false;
	private boolean playerFire = true;
	private boolean started = false;

	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			fillArray();
		}		
	}
	
	// Draw method
	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (xSelect == i && ySelect == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
					if (pieces[i][j].isShield() && pieces[i][j].isFire() && !pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					}
					else if (pieces[i][j].isShield() && pieces[i][j].isFire() && pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isBomb() && pieces[i][j].isFire() && !pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					}
					else if (pieces[i][j].isBomb() && pieces[i][j].isFire() && pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isShield() && pieces[i][j].isFire() == false && !pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					}
					else if (pieces[i][j].isShield() && pieces[i][j].isFire() == false && pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isBomb() && pieces[i][j].isFire() == false && !pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					}
					else if (pieces[i][j].isBomb() && pieces[i][j].isFire() == false && pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isFire() && !pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					}
					else if (pieces[i][j].isFire() && pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
					}
					else if (pieces[i][j].isFire() == false && !pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					}
					else if (pieces[i][j].isFire() == false && pieces[i][j].isKing()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
					}
				}
            }
        }
	}

	private void fillArray() {
		pieces = new Piece[8][8];
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
               	if ((j == 0) && (i % 2 == 0)) {
                	pieces[i][j] = new Piece(true, this, i, j, "pawn");
               	}
               	if ((j == 1) && (i % 2 != 0)) {
               		pieces[i][j] = new Piece(true, this, i, j, "shield");
               	}
               	if ((j == 2) && (i % 2 == 0)) {
               		pieces[i][j] = new Piece(true, this, i, j, "bomb");
               	}
               	if ((j == 5) && (i % 2 != 0)) {
               		pieces[i][j] = new Piece(false, this, i, j, "bomb");
               	}
               	if ((j == 6) && (i % 2 == 0)) {
               		pieces[i][j] = new Piece(false, this, i, j, "shield");
               	}
               	if ((j == 7) && (i % 2 != 0)) {
               		pieces[i][j] = new Piece(false, this, i, j, "pawn");
               	}
			}
		}
	}

	

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		if (pieces[x][y] == null) return null;
		if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0 || p == null) {
			return;
		}
		if (pieces[x][y] != null) {
			pieces[x][y] = null;
			pieces[x][y] = p;
		}
		if (pieces[x][y] == null) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("Point out of bounds");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("No piece at this point");
			return null;
		}
		else {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}
	private boolean validMove(int xf, int yf) {
		if (captured && pieces[xf][yf] == null) {
			if (xSelect == -1 || ySelect == -1) return false;
			int pXI = (xSelect + xf)/2;
			int pYI = (ySelect + yf)/2;
			if (xf > 7 || yf > 7) return false;
			if (pieces[xSelect][ySelect].isKing() && pieces[xSelect][ySelect].isFire()) {				// King moves
				if (pieces[pXI][pYI] != null) {
					if (Math.abs(xf - xSelect) == 2 && Math.abs(yf - ySelect) == 2 && (!pieces[pXI][pYI].isFire())) return true;  // Capture move
				}
				else return false;
			}
			if (pieces[xSelect][ySelect].isKing() && (!pieces[xSelect][ySelect].isFire())) {			// King moves
				if (pieces[pXI][pYI] != null) {
					if (Math.abs(xf - xSelect) == 2 && Math.abs(yf - ySelect) == 2 && (pieces[pXI][pYI].isFire())) return true;  // Capture move
				}
				else return false;
			}
			if (pieces[xSelect][ySelect].isFire()) {
				if (pieces[pXI][pYI] != null) {
					if (yf - ySelect == 2 && Math.abs(xf - xSelect) == 2 && (!pieces[pXI][pYI].isFire())) {
						return true;
					}
				}
				else return false;
			}	
			if (!pieces[xSelect][ySelect].isFire()) {
				if (pieces[pXI][pYI] != null) {
					if (yf - ySelect == -2 && Math.abs(xf - xSelect) == 2 && (pieces[pXI][pYI].isFire())) return true;
				}
				else return false;
			}

			else {
				return false;
			}
		}
		if (xSelect == -1 || ySelect == -1) return false;
		int pXI = (xf + xSelect)/2;
		int pYI = (yf + ySelect)/2;
		if (xf > 7 || yf > 7) return false;
		if (pieces[xSelect][ySelect].isKing() && pieces[xSelect][ySelect].isFire()) {				// King moves
			if (Math.abs(xf - xSelect) == 1 && Math.abs(yf - ySelect) == 1) return true;  // Normal move
			if (pieces[pXI][pYI] != null) {
				if (Math.abs(xf - xSelect) == 2 && Math.abs(yf - ySelect) == 2 && (!pieces[pXI][pYI].isFire())) return true;  // Capture move
			}
			else return false;
		}
		if (pieces[xSelect][ySelect].isKing() && (!pieces[xSelect][ySelect].isFire())) {			// King moves
			if (Math.abs(xf - xSelect) == 1 && Math.abs(yf - ySelect) == 1) return true;  // Normal move
			if (pieces[pXI][pYI] != null) {
				if (Math.abs(xf - xSelect) == 2 && Math.abs(yf - ySelect) == 2 && (pieces[pXI][pYI].isFire())) return true;  // Capture move
			}
			else return false;
		}
		if (pieces[xSelect][ySelect].isFire()) {
			if (yf - ySelect == 1 && Math.abs(xf - xSelect) == 1) return true;
			if (pieces[pXI][pYI] != null) {
				if (yf - ySelect == 2 && Math.abs(xf - xSelect) == 2 && (!pieces[pXI][pYI].isFire())) {
					return true;
				}
			}
			else return false;
		}
		if (!pieces[xSelect][ySelect].isFire()) {
			if (yf - ySelect == -1 && Math.abs(xf - xSelect) == 1) return true;
			if (pieces[pXI][pYI] != null) {
				if (yf - ySelect == -2 && Math.abs(xf - xSelect) == 2 && (pieces[pXI][pYI].isFire())) return true;
			}
			else return false;
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null) {
			if (pieces[x][y].isFire() == playerFire) {
				if (!selected) return true;
				if (selected && !moved) return true;
			}
			else return false;
		}
		else if (pieces[x][y] == null) {
			if (selected && !moved && validMove(x, y) && !captured) return true;
			if (selected && captured && moved && validMove(x, y)) return true;
			else return false;
		}
		return false;
	}

	public void select(int x, int y) {
		if (canSelect(x, y)) {
			if (pieces[x][y] != null && !captured) {
				xSelect = x;
				ySelect = y;
				selected = true;
			}
			else if (pieces[x][y] == null) {
				if (pieces[xSelect][ySelect].isBomb()) {
					captured = false;
				}
				else if (!pieces[xSelect][ySelect].isBomb() && Math.abs(xSelect - x) > 1) captured = true;
				pieces[xSelect][ySelect].move(x, y);
				pieces[x][y] = pieces[xSelect][ySelect];
				started = true;
				moved = true;
				pieces[xSelect][ySelect] = null;
				xSelect = x;
				ySelect = y;
				if (captured) { 
					boolean canCap1 = false;
					boolean canCap2 = false;
					boolean canCap3 = false;
					boolean canCap4 = false;
					if (x - 2 >= 0 && y - 2 >= 0) {
						canCap1 = canSelect(x - 2, y - 2);
					}
					if (x + 2 <= 7 && y - 2 >= 0) {
						canCap2 = canSelect(x + 2, y - 2);
					}
					if (x + 2 <= 7 && y + 2 <= 7) {
						canCap3 = canSelect(x + 2, y + 2);
					}
					if (x - 2 >= 0 && y + 2 <= 7) {
						canCap4 = canSelect(x - 2, y + 2);
					}
					boolean canCaptureAgain = (canCap1 || canCap2 || canCap3 || canCap4);
					xSelect = x;
					ySelect = y;
				}
				else {
				xSelect = -1;
				ySelect = -1;
				}
			}
		}
	}

	public boolean canEndTurn() {
		if (moved || captured) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		playerFire = !playerFire;
		selected = false;
		moved = false;
		captured = false;
		if (pieceAt(xSelect, ySelect) != null) pieceAt(xSelect, ySelect).doneCapturing();
		xSelect = -1;			//comment something up. have to change donecapturing at end turn
		ySelect = -1;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						fire += 1;
						}
					else {
						water += 1;
					}
				}
			}
		}
		// if (!started) return null;
		if (water == 0 && fire == 0) {
			return "No one";
		}
		else if (fire == 0 && water > 0) {
			return "Water";
		}
		else if (water == 0 && fire > 0) {
			return "Fire";
		}
		// else if (water > 0 && fire > 0) return null;
		else return null;
	}


	public static void main(String args[]) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
		while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.select((int) x, (int) y);
            }
            if(b.canEndTurn() && StdDrawPlus.isSpacePressed()) b.endTurn();
            if (b.winner() != null) b.winner();
			StdDrawPlus.show(25);
		}
	}
}