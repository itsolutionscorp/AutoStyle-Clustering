public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean fireDeterminant = true;
	private boolean selected;
	private boolean moved;
	private int prevX;
	private int prevY;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			for (int row = 0; row < 8; row++) {
				for (int column = 0; column < 8; column++) {
					if (row == 0 && (column + row) % 2 == 0) {
						pieces[row][column] = new Piece(true, this, column, row, "pawn");
					}
					else if (row == 1 && (column + row) % 2 == 0) {
						pieces[row][column] = new Piece(true, this, column, row, "shield");
					}
					else if (row == 2 && (column + row) % 2 == 0) {
						pieces[row][column] = new Piece(true, this, column, row, "bomb");
					}
					else if (row == 5 && (column + row) % 2 == 0) {
						pieces[row][column] = new Piece(false, this, column, row, "bomb");
					}
					else if (row == 6 && (column + row) % 2 == 0) {
						pieces[row][column] = new Piece(false, this, column, row, "shield");
					}
					else if (row == 7 && (column + row) % 2 == 0) {
						pieces[row][column] = new Piece(false, this, column, row, "pawn");
					}
					else {
						pieces[row][column] = null;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		b.drawingBoard();
		b.drawingPieces();
		while (b.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
				double mX = StdDrawPlus.mouseX();
				double mY = StdDrawPlus.mouseY();
				int newMX = (int)mX;
				int newMY = (int)mY;
				if (b.canSelect(newMX, newMY)) {
					b.select(newMX, newMY);
					b.drawingBoard();
					StdDrawPlus.filledSquare(newMX + .5, newMY + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					b.drawingPieces();
					StdDrawPlus.show(100);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
		}
		System.out.println(b.winner());
	}

	private void drawingPieces() {
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if (pieceAt(column, row) != null) {
					if (pieceAt(column, row).isFire()) {
						if (pieceAt(column, row).isBomb()) {
							if (pieceAt(column, row).isKing()){
								StdDrawPlus.picture(column + .5, row + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(column + .5, row + .5, "img/bomb-fire.png", 1, 1);
							}
						}
						else if (pieceAt(column, row).isShield()) {
							if (pieceAt(column, row).isKing()){
								StdDrawPlus.picture(column + .5, row + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(column + .5, row + .5, "img/shield-fire.png", 1, 1);
							}
						}
						else {
							if (pieceAt(column, row).isKing()){
								StdDrawPlus.picture(column + .5, row + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(column + .5, row + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					if (pieceAt(column, row).isFire() == false) {
						if (pieceAt(column, row).isBomb()) {
							if (pieceAt(column, row).isKing()){
								StdDrawPlus.picture(column + .5, row + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(column + .5, row + .5, "img/bomb-water.png", 1, 1);
							}
						}
						else if (pieceAt(column, row).isShield()) {
							if (pieceAt(column, row).isKing()){
								StdDrawPlus.picture(column + .5, row + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(column + .5, row + .5, "img/shield-water.png", 1, 1);
							}
						}
						else {
							if (pieceAt(column, row).isKing()){
								StdDrawPlus.picture(column + .5, row + .5, "img/pawn-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(column + .5, row + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	private void drawingBoard() {
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if ((row + column) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  		 StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(column + .5, row + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
			return null;
		}
		else if (pieces[y][x] == null) {
			return null;
		}
		else{
			return pieces[y][x];
		}

	}

	public boolean canSelect(int x, int y) {
		Piece target = pieceAt(x, y);
		if (target != null && target.isFire() == fireDeterminant) {
			if (!selected || !moved) {
				return true;
			}
		}
		if (target == null) {
			if ((selected && !moved && validMove(prevX, prevY, x, y)) ||
				(pieceAt(prevX, prevY) != null && pieceAt(prevX, prevY).hasCaptured() && validMove(prevX, prevY, x, y))) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 8 && xi >= 0 && xf < 8 && xf >= 0 && yi < 8 && yi >= 0 && yf < 8 && yf >= 0) {
			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi, yi).hasCaptured()) {
					if ((xf == xi + 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
						 pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() != fireDeterminant) {
					return true;
					}
					else if ((xf == xi + 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() != fireDeterminant) {
						return true;
					}
					else if ((xf == xi - 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() != fireDeterminant) {
						return true;
					}
					else if ((xf == xi - 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() != fireDeterminant) {
						return true;
					}
				}
				else {

					if ((xf == xi + 1 && yf == yi + 1) && pieceAt(xf, yf) == null) {
						return true;
					}
					else if ((xf == xi + 1 && yf == yi - 1) && pieceAt(xf, yf) == null) {
						return true;
					}
					else if ((xf == xi - 1 && yf == yi + 1) && pieceAt(xf, yf) == null) {
						return true;
					}
					else if ((xf == xi - 1 && yf == yi - 1) && pieceAt(xf, yf) == null) {
						return true;
					}
					else if ((xf == xi + 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() != fireDeterminant) {
						return true;
					}
					else if ((xf == xi + 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() != fireDeterminant) {
						return true;
					}
					else if ((xf == xi - 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() != fireDeterminant) {
						return true;
					}
					else if ((xf == xi - 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
							 pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() != fireDeterminant) {
						return true;
					}
				}
			}
			else {
				if (pieceAt(xi, yi).isFire()) {
					if (pieceAt(xi, yi).hasCaptured()) {
						if ((xf == xi + 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
						 	pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() == false) {
							return true;
						}
						else if ((xf == xi - 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
							 	 pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() == false) {
							return true;
						}
					}
					else {
						if ((xf == xi + 1 && yf == yi + 1) && pieceAt(xf, yf) == null) {
							return true;
						}
						else if ((xf == xi - 1 && yf == yi + 1) && pieceAt(xf, yf) == null) {
							return true;
						}	
						else if ((xf == xi + 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
							 	 pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() == false) {
							return true;
						}
						else if ((xf == xi - 2 && yf == yi + 2) && pieceAt(xf, yf) == null &&
							 	 pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() == false) {
							return true;
						}
					}
				}
				else {
					if (pieceAt(xi, yi).hasCaptured()) {
						if ((xf == xi + 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
						 	pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() == true) {
							return true;
						}
						else if ((xf == xi - 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
						 		 pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() == true) {
							return true;
						}
					}
					else {
						if ((xf == xi + 1 && yf == yi - 1) && pieceAt(xf, yf) == null) {
							return true;
						}
						else if ((xf == xi - 1 && yf == yi - 1) && pieceAt(xf, yf) == null) {
							return true;
						}
						else if ((xf == xi + 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
							 	pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() == true) {
							return true;
						}
						else if ((xf == xi - 2 && yf == yi - 2) && pieceAt(xf, yf) == null &&
							 	pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() == true) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			moved = false;
			prevX = x;
			prevY = y;
		}
		else{
			pieceAt(prevX, prevY).move(x, y);
			selected = false;
			moved = true;
			prevX = x;
			prevY = y;
		}
		selected = true;
	}

	public void place(Piece p, int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0 && p != null){
			for (int row = 0; row < 8; row++){
				for (int column = 0; column < 8; column++) {
					if (pieces[row][column] == p) {
						remove(column, row);
					}
				}
			}
			pieces[y][x] = p;
		}
	}

	public Piece remove(int x, int y){
		if (x >= 8 || x < 0 || y >= 8 || y < 0) {
			System.out.println("The position (x,y) is out of bound.");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("There is not any piece on this position.");
			return null;
		}
		else {
			Piece temp = pieces[y][x];
			pieces[y][x] = null;
			return temp;
		}
	}

	public boolean canEndTurn() {
		if (moved || pieceAt(prevX, prevY) == null || pieceAt(prevX, prevY).hasCaptured()) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		fireDeterminant = !fireDeterminant;
		moved = false;
		selected = false;
		if (pieceAt(prevX, prevY) != null) {
			pieceAt(prevX, prevY).doneCapturing();
		}
	}

	public String winner() {
		int f = 0;
		int w = 0;
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if (pieceAt(column, row) != null) {
					if (pieceAt(column, row).isFire()) {
						f += 1;
					}
					else {
						w += 1;
					}
				}
			}
		}
		if (f == 0 && w == 0) {
			return "No One";
		}
		else if (f == 0) {
			return "Water";
		}
		else if (w == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}
}

















