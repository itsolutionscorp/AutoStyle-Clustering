public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private Piece selectedPiece = null;
	private int selectedXpos;
	private int selectedYpos;
	private boolean fireTurn = true;
	private boolean hasMoved = false;
	private int fire_deaths;
	private int water_deaths;
	private boolean shouldBeEmpty;

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		if (!shouldBeEmpty) {
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

	public static void main(String[] args) {
		Board game_board = new Board(false);
		game_board.drawBoard();
		if (!game_board.shouldBeEmpty) {
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					if ((j == 0) && (i % 2 == 0)) {
						StdDrawPlus.picture(i + .5, j + .5, game_board.image(game_board.pieces[i][j]), 1, 1);
					}
					else if ((j == 1) && (i % 2 != 0)) {
						StdDrawPlus.picture(i + .5, j + .5, game_board.image(game_board.pieces[i][j]), 1, 1);
					}
					else if ((j == 2) && (i % 2 == 0)) {
						StdDrawPlus.picture(i + .5, j + .5, game_board.image(game_board.pieces[i][j]), 1, 1);
					}
					else if ((j == 5) && (i % 2 != 0)) {
						StdDrawPlus.picture(i + .5, j + .5, game_board.image(game_board.pieces[i][j]), 1, 1);
					}
					else if ((j == 6) && (i % 2 == 0)) {
						StdDrawPlus.picture(i + .5, j + .5, game_board.image(game_board.pieces[i][j]), 1, 1);
					}
					else if ((j == 7) && (i % 2 != 0)) {
						StdDrawPlus.picture(i + .5, j + .5, game_board.image(game_board.pieces[i][j]), 1, 1);
					}
				}		
			}
			StdDrawPlus.show(25);
		}
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				int k = (int) StdDrawPlus.mouseX();
				int e = (int) StdDrawPlus.mouseY();
				if (game_board.canSelect(k , e)) {
					game_board.select(k, e);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (game_board.canEndTurn()) {
					game_board.endTurn();
				}
			}
		}
	}

	public String winner() {
		if ((fire_deaths < 12) || (water_deaths < 12)) {
			return null;
		}
		else if ((fire_deaths == water_deaths) && (fire_deaths == 12)) {
			return "No one";
		}
		else if (water_deaths == 12) {
			return "Fire";
		}
		else if (fire_deaths == 12) {
			return "Water";
		}
		else return null;
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			return null;
		}
		else return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0) || (p == null)) {
			return;
		}
		this.pieces[x][y] = p;
		if (p.isFire()) {
			fire_deaths = fire_deaths - 1;
		}
		if (!p.isFire()) {
			water_deaths = water_deaths - 1;
		}
		placeDraw(p, x, y);	
	}

	public boolean canSelect(int x, int y) {
		if (hasMoved) {
			if (selectedPiece != null) {
				if (selectedPiece.hasCaptured()) {
					return validCapture(this.selectedPiece, selectedXpos, selectedYpos, x, y);
				}
			}
		}
		if (pieces[x][y] != null) {
			if (selectedPiece == null) {
				if (pieces[x][y].isFire() == fireTurn) {
					return true;
				}
				else return false;
			}
			else if (selectedPiece != null) {
				if (!hasMoved) {
					if (pieces[x][y].isFire() == fireTurn) {
						return true;
					}
				}
				else return false;
			}
		}
		else if (pieces[x][y] == null) {
			if (selectedPiece != null) {
				if (!hasMoved) {
					return validMove(selectedPiece, selectedXpos, selectedYpos, x, y);
				}
				else if (hasMoved) {
					return (selectedPiece.hasCaptured() && validCapture(selectedPiece, selectedXpos, selectedYpos, x, y));
				}
			}
			else return false;
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			System.out.println("selected square");
			placeDraw(selectedPiece, x, y);
			selectedPiece.move(x, y);
			selectedXpos = x;
			selectedYpos = y;
			hasMoved = true;
			return;
		}
		if (pieceAt(x, y) != null) {
			System.out.println("selected piece");
			selectedPiece = pieceAt(x, y);
			selectDraw(selectedPiece, x, y);
			selectedXpos = x;
			selectedYpos = y; 
			return;
		}
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public Piece remove(int x, int y) {
		if ((x < 0) || (y < 0) || (x > 7) || (y >7)) {
			return null;
		}
		if (pieceAt(x, y) == selectedPiece) {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			removeDraw(x, y);
			if (temp != null) {
				if (temp.isFire()) {
					fire_deaths = fire_deaths + 1;
				}
				else if (!temp.isFire()) {
					water_deaths = water_deaths + 1;
				}
			}
			return temp;
		}
		else {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			removeDraw(x, y);
			if (temp != null) {
				if (temp.isFire()) {
					fire_deaths = fire_deaths + 1;
				}
				if (!temp.isFire()) {
					water_deaths = water_deaths + 1;
				}
			}
			return temp;
		}
	}

	public void endTurn() {
		System.out.println("Ended turn");
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
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



//private methods below

private void drawBoard() {
		int Size = 8;
		int n = 0;
		int m = 0;
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		while (m < Size) {
			while (n < Size) {
				if ((m + n) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
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
		Board game_board = new Board(false);
		
	}
private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
	int xdiff = xf - xi;
	int ydiff = yf - yi;
	if (Math.abs(xdiff) == Math.abs(ydiff)) { //first condition, must move in diagonal
		if (p.isKing()) {
			if (Math.abs(xdiff) == 1) { //kings can move in any direction
				return true;
			}
			else if (Math.abs(xdiff) == 2) { //checking if he can hop
				if (xf > xi) { //moving right
					if (yf > yi) { //moving up
						if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+1, yi+1).isFire() != p.isFire())) {
							return true; 
						}
						else return false;
					}
					else if (yi > yf) { //moving down
						if ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+1, yi-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
				if (xi > xf) { //moving left
					 if (yf > yi){ //moving up
						if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-1, yi+1).isFire() != p.isFire())) {
							return true; 
						}
						else return false;
					}
					else if (yi > yf) { //moving down
						if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-1, yi-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
			}	 
		}
		else {
			if (p.isFire()) {
				if (ydiff == 2) {
					if (xi > xf) {
						if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-1, yi+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
					else if (xf > xi) {
						if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+1, yi+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
				else if (ydiff == 1) {
					return true;
				}
			}
			else if (!p.isFire()) {
				if (ydiff == -2) {
					if (xi > xf) {
						if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-1, yi-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
					if (xf > xi) {
						if ((pieceAt(xf-1, yi-1) != null) && (pieceAt(xf-1, yi-1).isFire() != p.isFire())) {
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

private boolean validCapture(Piece p, int xi, int yi, int xf, int yf) {
	int xdiff = xf - xi;
	int ydiff = yf - yi;
	if ((xdiff == 0) ||  (ydiff == 0)) {
		return false;
	}
	if (Math.abs(xdiff) == Math.abs(ydiff)) { //first condition, must move in diagonal
		if (p.isKing()) {
			if (Math.abs(xdiff) == 1) { //kings can move in any direction
				return false;
			}
			else if (Math.abs(xdiff) == 2) { //checking if he can hop
				if (xf > xi) { //moving right
					if (yf > yi) { //moving up
						if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+1, yi+1).isFire() != p.isFire())) {
							return true; 
						}
						else return false;
					}
					else if (yi > yf) { //moving down
						if ((pieceAt(xi+1, yi-1) != null) && (pieceAt(xi+1, yi-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
				if (xi > xf) { //moving left
					 if (yf > yi){ //moving up
						if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-1, yi+1).isFire() != p.isFire())) {
							return true; 
						}
						else return false;
					}
					else if (yi > yf) { //moving down
						if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-1, yi-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
			}	 
		}
		else {
			if (p.isFire()) {
				if (ydiff == 2) {
					if (xi > xf) {
						if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-1, yi+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
					else if (xf > xi) {
						if ((pieceAt(xi+1, yi+1) != null) && (pieceAt(xi+1, yi+1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
				}
				else if (ydiff == 1) {
					return false;
				}
			}
			else if (!p.isFire()) {
				if (ydiff == -2) {
					if (xi > xf) {
						if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-1, yi-1).isFire() != p.isFire())) {
							return true;
						}
						else return false;
					}
					if (xf > xi) {
						if ((pieceAt(xf-1, yi-1) != null) && (pieceAt(xf-1, yi-1).isFire() != p.isFire())) {
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



private String image(Piece p1) {
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
			else if (!p1.isKing()) {
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
	 	if (!p1.isFire()) {
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
			else if (!p1.isKing()) {
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
	 return "bruh";
	}	

	




private void placeDraw(Piece p, int x, int y) {
	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
	StdDrawPlus.filledSquare(x+.5, y+.5, .5);
	StdDrawPlus.picture(x+.5, y+.5, image(p), 1, 1);
	StdDrawPlus.show(25);
}

private void removeDraw(int x, int y) {
	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
	StdDrawPlus.filledSquare(x+.5, y+.5, .5);
	StdDrawPlus.show(25);
}

private void selectDraw(Piece p, int x, int y) {
	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	StdDrawPlus.filledSquare(x+.5, y+.5, .5);
	StdDrawPlus.picture(x+.5, y+.5, image(p), 1, 1);
	StdDrawPlus.show(25);
}

private void pictureDraw(Piece p, int x, int y) {
	StdDrawPlus.picture(x+.5, y+.5, image(p), 1, 1);
	StdDrawPlus.show(25);
}










}