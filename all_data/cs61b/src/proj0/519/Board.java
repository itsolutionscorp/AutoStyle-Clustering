public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private Piece pieceselected = null;
	private int xcoor;
	private int ycoor;
	private boolean fireTurn = true;
	private boolean hasMoved = false;
	private int firedeaths;
	private int waterdeaths;
	private boolean shouldBeEmpty;

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		if (shouldBeEmpty == false) {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					if ((j == 0) && (i % 2 == 0)) {
						this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
					}
					else if ((j == 1) && (i % 2 != 0)) {
						this.pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					else if ((j == 2) && (i % 2 == 0)) {
						this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
					else if ((j == 5) && (i % 2 != 0)) {
						this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
					else if ((j == 6) && (i % 2 == 0)) {
						this.pieces[i][j] = new Piece(false, this, i, j, "shield");
					}
					else if ((j == 7) && (i % 2 != 0)) {
						this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}		
			}
		}
	}
 
	public boolean canSelect(int x, int y) {
		if (hasMoved) {
			if (pieceselected != null) {
				if (pieceselected.hasCaptured()) {
					return AcceptableCapture(this.pieceselected, xcoor, ycoor, x, y);
				}
			}
		}
		if (pieces[x][y] != null) {
			if (pieceselected == null) {
				if (pieces[x][y].isFire() == fireTurn) {
					return true;
				}
				else return false;
			}
			else if (pieceselected != null) {
				if (!hasMoved) {
					if (pieces[x][y].isFire() == fireTurn) {
						return true;
					}
				}
				else return false;
			}
		}
		else if (pieces[x][y] == null) {
			if (pieceselected != null) {
				if (hasMoved == false) {
					return AcceptableMove(pieceselected, xcoor, ycoor, x, y);
				}
				else if (hasMoved) {
					return (pieceselected.hasCaptured() && AcceptableCapture(pieceselected, xcoor, ycoor, x, y));
				}
			}
			else return false;
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			System.out.println("You Have Selected a Square");
			StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			StdDrawPlus.picture(x+.5, y+.5, AccessImage(pieceselected), 1, 1);
			StdDrawPlus.show(25);
			pieceselected.move(x, y);
			xcoor = x;
			ycoor = y;
			hasMoved = true;
			return;
		}
		if (pieceAt(x, y) != null) {
			System.out.println("You Have Selected a Piece");
			pieceselected = pieceAt(x, y);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x+.5, y+.5, .5);
			StdDrawPlus.picture(x+.5, y+.5, AccessImage(pieceselected), 1, 1);
			StdDrawPlus.show(30);
			xcoor = x;
			ycoor = y; 
			return;
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0) || (p == null)) {
			return;
		}
		this.pieces[x][y] = p;
		if (p.isFire()) {
			firedeaths = firedeaths - 1;
		}
		if (!p.isFire()) {
			waterdeaths = waterdeaths - 1;
		}
		StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		StdDrawPlus.picture(x + .5, y + .5, AccessImage(p), 1, 1);
		StdDrawPlus.show(30);
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public String winner() {
		if ((firedeaths < 12) || (waterdeaths < 12)) {
			return null;
		}
		else if ((firedeaths == waterdeaths) && (firedeaths == 12)) {
			return "Nobody Wins";
		}
		else if (waterdeaths == 12) {
			return "Fire";
		}
		else if (firedeaths == 12) {
			return "Water";
		}
		else return null;
	}

	public Piece remove(int x, int y) {
		if ((x < 0) || (y < 0) || (x > 7) || (y >7)) {
			return null;
		}
		if (pieceAt(x, y) == pieceselected) {
			Piece placeholder = pieces[x][y];
			pieces[x][y] = null;
			StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
			StdDrawPlus.filledSquare(x+.5, y+.5, .5);
			StdDrawPlus.show(35);
			if (placeholder != null) {
				if (placeholder.isFire()) {
					firedeaths = firedeaths + 1;
				}
				else if (!placeholder.isFire()) {
					waterdeaths = waterdeaths + 1;
				}
			}
			return placeholder;
		}
		else {
			Piece placeholder = pieces[x][y];
			pieces[x][y] = null;
			StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
			StdDrawPlus.filledSquare(x+.5, y+.5, .5);
			StdDrawPlus.show(30);
			if (placeholder != null) {
				if (placeholder.isFire()) {
					firedeaths = firedeaths + 1;
				}
				if (!placeholder.isFire()) {
					waterdeaths = waterdeaths + 1;
				}
			}
			return placeholder;
		}
	}

	public void endTurn() {
		System.out.println("You Have Ended Your Turn");
		if (pieceselected != null) {
			pieceselected.doneCapturing();
		}
		pieceselected = null;
		if (fireTurn) {
			fireTurn = false;
			hasMoved = false;
			return;
		}
		else if (!fireTurn) {
			fireTurn = true;
			hasMoved = false;
			return;
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			return null;
		}
		else return pieces[x][y];
	}


private void dBoard() {
		int Size = 8;
		int n = 0;
		int m = 0;
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		while (m < Size) {
			while (n < Size) {
				if ((m + n) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
				}
				StdDrawPlus.filledSquare(n + .5, m + .5, .5);
				n = n+ 1;
			}
			n = 0;
			m = m + 1;
		}
		n = 0;
		m = 0;
		StdDrawPlus.show(25);
		Board checkerboard = new Board(false);
		
	}
private boolean AcceptableMove(Piece p, int xinitial, int yinitial, int xfinal, int yfinal) {
	int xdiff = xfinal - xinitial;
	int ydiff = yfinal - yinitial;
	if (Math.abs(xdiff) == Math.abs(ydiff)) { 
		if (p.isKing()) {
			if (Math.abs(xdiff) == 1) {
				return true;
			} else if (Math.abs(xdiff) == 2) { 
				if (xfinal > xinitial) { 
					if (yfinal > yinitial) { 
						if ((pieceAt(xinitial+1, yinitial+1) != null) && (pieceAt(xinitial+1, yinitial+1).isFire() != p.isFire())) {
							return true; 
						}
						else return false;
					}
					else if (yinitial > yfinal) { 
						if ((pieceAt(xinitial+1, yinitial-1) != null) && (pieceAt(xinitial+1, yinitial-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				} if (xinitial > xfinal) { 
					 if (yfinal > yinitial){ 
						if ((pieceAt(xinitial-1, yinitial+1) != null) && (pieceAt(xinitial-1, yinitial+1).isFire() != p.isFire())) {
							return true; 
						}
						else return false;
					}
					else if (yinitial > yfinal) { 
						if ((pieceAt(xinitial-1, yinitial-1) != null) && (pieceAt(xinitial-1, yinitial-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
			}	 
		} else {
			if (p.isFire()) {
				if (ydiff == 2) {
					if (xinitial > xfinal) {
						if ((pieceAt(xinitial-1, yinitial+1) != null) && (pieceAt(xinitial-1, yinitial+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					} else if (xfinal > xinitial) {
						if ((pieceAt(xinitial+1, yinitial+1) != null) && (pieceAt(xinitial+1, yinitial+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				} else if (ydiff == 1) {
					return true;
				}
			}else if (!p.isFire()) {
				if (ydiff == -2) {
					if (xinitial > xfinal) {
						if ((pieceAt(xinitial-1, yinitial-1) != null) && (pieceAt(xinitial-1, yinitial-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
					if (xfinal > xinitial) {
						if ((pieceAt(xfinal-1, yinitial-1) != null) && (pieceAt(xfinal-1, yinitial-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
				else if (ydiff == -1) {
					return true;
				}
			}
		}
	}
	return false;	
}

private boolean AcceptableCapture(Piece p, int xinitial, int yinitial, int xfinal, int yfinal) {
	int xdiff = xfinal - xinitial;
	int ydiff = yfinal - yinitial;
	if ((xdiff == 0) ||  (ydiff == 0)) {
		return false;
	}
	if (Math.abs(xdiff) == Math.abs(ydiff)) { 
		if (p.isKing()) {
			if (Math.abs(xdiff) == 1) { 
				return false;
			} else if (Math.abs(xdiff) == 2) { 
				if (xfinal > xinitial) { 
					if (yfinal > yinitial) { 
						if ((pieceAt(xinitial+1, yinitial+1) != null) && (pieceAt(xinitial+1, yinitial+1).isFire() != p.isFire())) {
							return true; 
						} else return false;
					}
					else if (yinitial > yfinal) { 
						if ((pieceAt(xinitial+1, yinitial-1) != null) && (pieceAt(xinitial+1, yinitial-1).isFire() != p.isFire())) {
							return true;
						} else return false;
					}
				}
				if (xinitial > xfinal) { 
					 if (yfinal > yinitial){ 
						if ((pieceAt(xinitial-1, yinitial+1) != null) && (pieceAt(xinitial-1, yinitial+1).isFire() != p.isFire())) {
							return true; 
						} else return false;
					}
					else if (yinitial > yfinal) { //moving down
						if ((pieceAt(xinitial-1, yinitial-1) != null) && (pieceAt(xinitial-1, yinitial-1).isFire() != p.isFire())) {
							return true;
						} else return false;
					}
				}
			}	 
		}
		else {
			if (p.isFire()) {
				if (ydiff == 2) {
					if (xinitial > xfinal) {
						if ((pieceAt(xinitial-1, yinitial+1) != null) && (pieceAt(xinitial-1, yinitial+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					} else if (xfinal > xinitial) {
						if ((pieceAt(xinitial+1, yinitial+1) != null) && (pieceAt(xinitial+1, yinitial+1).isFire() != p.isFire())) {
							return true;
						} else return false;
					}
				}
				else if (ydiff == 1) {
					return false;
				}
			}
			else if (p.isFire() == false) {
				if (ydiff == -2) {
					if (xinitial > xfinal) {
						if ((pieceAt(xinitial-1, yinitial-1) != null) && (pieceAt(xinitial-1, yinitial-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
					if (xfinal > xinitial) {
						if ((pieceAt(xfinal-1, yinitial-1) != null) && (pieceAt(xfinal-1, yinitial-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
				else if (ydiff == -1) {
					return false;
				}
			}
		}
	}
	return false;
}



private String AccessImage(Piece p1) {
	if (p1.isFire()) {
		if (p1.isKing()) {
			if (p1.isShield()) {
	 			return "img/shield-fire-crowned.png";
			}
 			else if (p1.isBomb()) {
 				return "img/bomb-fire-crowned.png";
 			}
 			else {
 				return "img/pawn-fire-crowned.png";
 			}
		}
		else if (p1.isKing() == false) {
			if (p1.isShield()) {
 				return "img/shield-fire.png";
 			}
 			else if (p1.isBomb()) {
 				return "img/bomb-fire.png";
 			}
 			else {
 				return "img/pawn-fire.png";
 			}
 		}
 	}
	if (p1.isFire() == false) {
		if (p1.isKing()) {
 			if (p1.isShield()) {
 				return "img/shield-water-crowned.png";
 			}
 			else if (p1.isBomb()) {
 				return "img/bomb-water-crowned.png";
 			}
 			else {
 				return "img/pawn-water-crowned.png";
 			}
		}
		else if (p1.isKing() == false) {
			if (p1.isShield()) {
 				return "img/shield-water.png";
 			}
 			else if (p1.isBomb()) {
 				return "img/bomb-water.png";
 			}
 			else {
 				return "img/pawn-water.png";
 			}
 		}
 	}
 return "WHAT IS GOING ON";
}	

	public static void main(String[] args) {
		Board checkerboard = new Board(false);
		checkerboard.dBoard();
		if (checkerboard.shouldBeEmpty == false) {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					if ((j == 0) && (i % 2 == 0)) {
						StdDrawPlus.picture(i + .5, j + .5, checkerboard.AccessImage(checkerboard.pieces[i][j]), 1, 1);
					}
					else if ((j == 1) && (i % 2 != 0)) {
						StdDrawPlus.picture(i + .5, j + .5, checkerboard.AccessImage(checkerboard.pieces[i][j]), 1, 1);
					}
					else if ((j == 2) && (i % 2 == 0)) {
						StdDrawPlus.picture(i + .5, j + .5, checkerboard.AccessImage(checkerboard.pieces[i][j]), 1, 1);
					}
					else if ((j == 5) && (i % 2 != 0)) {
						StdDrawPlus.picture(i + .5, j + .5, checkerboard.AccessImage(checkerboard.pieces[i][j]), 1, 1);
					}
					else if ((j == 6) && (i % 2 == 0)) {
						StdDrawPlus.picture(i + .5, j + .5, checkerboard.AccessImage(checkerboard.pieces[i][j]), 1, 1);
					}
					else if ((j == 7) && (i % 2 != 0)) {
						StdDrawPlus.picture(i + .5, j + .5, checkerboard.AccessImage(checkerboard.pieces[i][j]), 1, 1);
					}
				}		
			}
			StdDrawPlus.show(35);
		}
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				int u = (int) StdDrawPlus.mouseX();
				int o = (int) StdDrawPlus.mouseY();
				if (checkerboard.canSelect(u , o)) {
					checkerboard.select(u, o);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (checkerboard.canEndTurn()) {
					checkerboard.endTurn();
				}
			}
		}
	}

}