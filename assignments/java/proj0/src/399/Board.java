public class Board {
	private Piece[][] pieces;
	private int s;
	private int turn;
	private boolean[][] selected;
	private boolean moved;
	private boolean captured;
	private int xSelected;
	private int ySelected;

	public static void main(String[] args) {
		Board b = new Board(false);
		int n = 8;
		StdDrawPlus.setXscale(0, n);
		StdDrawPlus.setYscale(0, n);

		while(true) {
			b.drawBoard(n);
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}

	}

	public Board(boolean shouldBeEmpty) {
		moved = false;
		captured = false;
		xSelected = -1;
		ySelected = -1;
		turn = 0;
		s = 8;
		pieces = new Piece[s][s];
		selected = new boolean[s][s];
		if (!shouldBeEmpty) {
			placePieces(s);
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x >= s || y >= s) {
			return null;
		}
		if (x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null){
			if (p.side() == turn && haveSelected() == false) {
				return true;
			}
			if (p.side() == turn && moved == false) {
				return true;
			}
		}
		if (p == null) {
			if (haveSelected() == true && moved == false && validMove(xSelected, ySelected, x, y) == true) {
				return true;
			}
			if (haveSelected() == true && captured == true && validCap(xSelected, ySelected, x, y) == true && pieceAt(xSelected, ySelected) != null){
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null && haveSelected() == true) { 
			selected[xSelected][ySelected] = false;
			selected[x][y] = true;
			xSelected = x;
			ySelected = y;
		}
		else if (pieceAt(x, y) != null) {
			selected[x][y] = true;
			xSelected = x;
			ySelected = y;
		}
		else {
			pieceAt(xSelected, ySelected).move(x, y); //call move
			if (pieceAt(x, y).hasCaptured() == true) {
				captured = true;
				if (pieceAt(x, y).isBomb()) {
					remove(x, y);
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (0 <= i && i < s && 0 <= j && j < s){
								if (pieces[i][j] != null) {
									if (pieces[i][j].isShield() == false) {
										remove(i, j);
									}
								}
							}
						}
					}
				}
			}
			moved = true;
			xSelected = x;
			ySelected = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x >= s || y >= s) {
			return;
		}
		if (x < 0 || y < 0) {
			return;
		}
		else if (p == null) {
			return;
		}
		else {

			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x >= s || y >= s) {
			System.out.println("input out of bounds");
			return null;
		}
		else if (x < 0 || y < 0) {
			System.out.println("input out of bounds");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("no piece at input");
			return null;
		}
		else {
			Piece temp = pieceAt(x, y);
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn(){
		if (moved == true || captured == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (pieceAt(xSelected, ySelected) != null) {
			pieceAt(xSelected, ySelected).doneCapturing();
		}
		if (turn == 0) {
			turn = 1;
		}
		else if (turn == 1) {
			turn = 0;
		}
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				selected[i][j] = false;
			}
		}
		xSelected = -1;
		ySelected = -1;
		moved = false;
		captured = false;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				Piece p = pieces[i][j];
				if (p != null) {
					if (p.isFire()) {
						fire += 1;
					}
					if (p.isFire() == false) {
						water += 1;
					}
				}
			}
		}
		if (water == 0 && fire == 0) {
			return "No one";
		}
		if (water == 0) {
			return "Fire";
		}
		if (fire == 0) {
			return "Water";
		}
		return null;
	}

	private boolean haveSelected() {
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				if (selected[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf >= s || yf >= s) { //out of bounds --> bad
			return false;
		}
		else if (xf < 0 || yf < 0) { //out of bounds --> bad
			return false;
		}
		else if (pieceAt(xf, yf) != null) { //theres a piece where youre trying to move --> bad
			return false;
		}
		else if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)) {   //one step away --> good?
			Piece p = pieceAt(xSelected, ySelected);
			if (p.isKing()) {																//is a king --> can move however --> good
				return true;
			}
			else if (p.isFire() && yf == yi + 1) {											//is a fire --> move up --> good
				return true;
			}
			else if (p.isFire() == false && yf == yi - 1) {									//is a water --> move down --> good
				return true;
			}
			else {																			//moving in wrong direction --> bad
				return false;
			}
		}
		else if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi - 2)) {   //two steps away --> good?
			int xm = (xi + xf) / 2;
			int ym = (yi + yf) / 2;
			if (pieceAt(xm, ym) == null) { 	//no piece to hop over --> bad
				return false;
			}
			else if (pieceAt(xm, ym).side() != turn) {  //piece to hop over is enemy --> good
				Piece p = pieceAt(xSelected, ySelected);
				if (p.isKing()) {					//is a king --> can move however --> good
					return true;
				}
				else if (p.isFire() && yf == yi + 2) {			//is a fire --> move up --> good
					return true;
				}
				else if (p.isFire() == false && yf == yi - 2) {		//is a water --> move down --> good
					return true;
				}
				else {			//moving in wrong direction --> bad
					return false;
				}				
			}
			else {	//piece to hop over is friendly --> bad
				return false;
			}
		}
		else {				//square not within one or two spaces --> no good
			return false;
		}

	}

	private boolean validCap(int xi, int yi, int xf, int yf) {
		if (xf >= s || yf >= s) {
			return false;
		}
		else if (xf < 0 || yf < 0) {
			return false;
		}
		else if (pieceAt(xf, yf) != null) {
			return false;
		}
		else if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)) {
			return false;															//only difference between this and validMove
		}
		else if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi - 2)) {   //two steps away --> good?
			int xm = (xi + xf) / 2;
			int ym = (yi + yf) / 2;
			if (pieceAt(xm, ym) == null) { 	//no piece to hop over --> bad
				return false;
			}
			else if (pieceAt(xm, ym).side() != turn) {  //piece to hop over is enemy --> good
				Piece p = pieceAt(xSelected, ySelected);
				if (p.isKing()) {					//is a king --> can move however --> good
					return true;
				}
				else if (p.isFire() && yf == yi + 2) {			//is a fire --> move up --> good
					return true;
				}
				else if (p.isFire() == false && yf == yi - 2) {		//is a water --> move down --> good
					return true;
				}
				else {			//moving in wrong direction --> bad
					return false;
				}				
			}
			else {	//piece to hop over is friendly --> bad
				return false;
			}
		}
		else {
			return false;
		}

	}

	private void placePieces(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i % 2 == 0) && (j % 2 == 0)){
					if (j == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					}
					if (j == 2) {
						pieces[i][j] = new Piece(true, this, i, j, "bomb");	
					}
					if (j == 6) {
						pieces[i][j] = new Piece(false, this, i, j, "shield");	
					}
				}
				else if ((i % 2 != 0) && (j % 2 != 0)) {
					if (j == 1) {
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					if (j == 7) {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
					if (j == 5) {
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
				}
			}
		}
	}

	private void drawBoard(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i % 2 == 0) && (j % 2 == 0)){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				else if ((i % 2 != 0) && (j % 2 != 0)) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				}
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						if (pieces[i][j].isKing()) {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}
						else {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					else {
						if (pieces[i][j].isKing()) {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
						else {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
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
	}
}