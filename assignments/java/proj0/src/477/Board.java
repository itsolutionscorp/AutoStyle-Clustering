public class Board {

	//need to implement bomb and shield cases

	private Piece[][] pieces;
	private int turn;
	private Piece selectedPiece;
	private int startx;
	private int starty;
	private int selectedx;
	private int selectedy;

	public static void main(String args[]) {
		Board a = new Board(false);
		int x;
		int y;
		a.drawBoard();
		a.drawPieces(a.pieces);
		while (a.winner() == null) {
			if (StdDrawPlus.isSpacePressed() && a.canEndTurn()) {
					a.endTurn();
					StdDrawPlus.show(200);
				}
			if (StdDrawPlus.mousePressed()) {
				x = (int)StdDrawPlus.mouseX();
				y = (int)StdDrawPlus.mouseY();
				if (a.canSelect(x, y)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					a.select(x, y);
					StdDrawPlus.show(20);
					
				}
				a.drawBoard();
				a.drawPieces(a.pieces);
				StdDrawPlus.show(20);
			}
		}
		System.out.println(a.winner());
	}


	private void drawBoard() {
		StdDrawPlus.setScale(0, 8);
		int p = 1;
		for (int y = 0; y < 8; y += 1){
			if (p % 2 == 0) {
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
			}
			else { 
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			}
			int x = 0;
			p += 1;
			while (x < 8) {
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				x += 2;
			}
		}
		int k = 1;
		for (int y = 0; y < 8; y += 1){
			if (k % 2 == 0) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			}
			else { 
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
			}
			int x = 1;
			k += 1;
			while (x < 8) {
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				x += 2;
			}
		}
	}

	private void startPieces(Piece[][] pieces) {
		for (int y = 0; y < 8; y += 1) {
			for (int x = 0; x < 8; x += 1) {
				if (y == 0) {
					if (x % 2 == 0) {
						pieces[x][y] = new Piece(true, this, x, y, "normal");
					}
				}
				else if (y == 1) {
					if (x % 2 != 0) {
						pieces[x][y] = new Piece(true, this, x, y, "shield");
					}
				}
				else if (y == 2) {
					if (x % 2 == 0) {
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
					}
				}
				else if (y == 5) {
					if (x % 2 != 0){
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
					}
				}
				else if (y == 6) {
					if (x % 2 == 0) {
						pieces[x][y] = new Piece(false, this, x, y, "shield");
					}
				}
				else if (y == 7) {
					if (x % 2 != 0) {
						pieces[x][y] = new Piece(false, this, x, y, "normal");
					}
				}
			}
		}
	}

	private String getPieceImage(Piece p) {
		if (p.isFire()) {
			if (p.isBomb()) {
				if (p.isKing()) {
					return "img/bomb-fire-crowned.png";
				}
				else {
					return "img/bomb-fire.png";
				}
			}
			else if (p.isShield()) {
				if (p.isKing()) {
					return "img/shield-fire-crowned.png";
				}
				else {
					return "img/shield-fire.png";
				}
			}
			else {
				if (p.isKing()) {
					return "img/pawn-fire-crowned.png";
				}
				else {
					return "img/pawn-fire.png";
				}
			}
		}
		else {
			if (p.isBomb()) {
				if (p.isKing()) {
					return "img/bomb-water-crowned.png";
				}
				else {
					return "img/bomb-water.png";
				}
			}
			else if (p.isShield()) {
				if (p.isKing()) {
					return "img/shield-water-crowned.png";
				}
				else {
					return "img/shield-water.png";
				}
			}
			else {
				if (p.isKing()) {
					return "img/pawn-water-crowned.png";
				}
				else {
					return "img/pawn-water.png";
				}
			}
		}
	}

	private void drawPieces(Piece[][] pieces) {
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieces[i][j] != null){
					StdDrawPlus.picture(i + .5, j + .5, getPieceImage(pieces[i][j]), 1, 1);
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false){
			startPieces(pieces);
			selectedPiece = null;
			turn = 0;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (checkInBoard(x, y)){
			return pieces[x][y];
		}
		else{
			return null;
		}
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi];
		if (checkInBoard(xf, yf) == false) {
			return false;
		}
		else if (pieces[xf][yf] == null) {
			if (p.side() == 0) {
				if (p.isKing()) {
					if (((xi + 2 == xf) && (yi + 2 == yf)) && (pieces[xi + 1][yi + 1] != null)) {
						if (pieces[xi + 1][yi + 1].side() == 1) {
							return true;
						}
						else {return false;}
					}
					else if (((xi - 2 == xf) && (yi + 2 == yf)) && (pieces[xi - 1][yi + 1] != null)){
						if (pieces[xi - 1][yi + 1].side() == 1) {
							return true;
						}
						else {return false;}
					}
					else if (((xi - 2 == xf) && (yi - 2 == yf)) && (pieces[xi - 1][yi - 1] != null)){
						if (pieces[xi - 1][yi - 1].side() == 1) {
							return true;
						}
						else {return false;}
					}
					else if (((xi + 2 == xf) && (yi - 2 == yf)) && (pieces[xi + 1][yi - 1] != null)){
						if (pieces[xi + 1][yi - 1].side() == 1) {
							return true;
						}
						else {return false;}
					}
					else {return false;}
				}
				else {
					if (((xi + 2 == xf) && (yi + 2 == yf)) && (pieces[xi + 1][yi + 1] != null)) {
						if (pieces[xi + 1][yi + 1].side() == 1) {
							return true;
						}
						else {return false;}
					}
					else if (((xi - 2 == xf) && (yi + 2 == yf)) && (pieces[xi - 1][yi + 1] != null)){
						if (pieces[xi - 1][yi + 1].side() == 1) {
							return true;
						}
						else {return false;}
					}
					else {return false;}
				}
			}
			else{
				if (p.isKing()) {
					if (((xi + 2 == xf) && (yi + 2 == yf)) && (pieces[xi + 1][yi + 1] != null)) {
						if (pieces[xi + 1][yi + 1].side() == 0) {
							return true;
						}
						else {return false;}
					}
					else if (((xi - 2 == xf) && (yi + 2 == yf)) && (pieces[xi - 1][yi + 1] != null)){
						if (pieces[xi - 1][yi + 1].side() == 0) {
							return true;
						}
						else {return false;}
					}
					else if (((xi - 2 == xf) && (yi - 2 == yf)) && (pieces[xi - 1][yi - 1] != null)){
						if (pieces[xi - 1][yi - 1].side() == 0) {
							return true;
						}
						else {return false;}
					}
					else if (((xi + 2 == xf) && (yi - 2 == yf)) && (pieces[xi + 1][yi - 1] != null)){
						if (pieces[xi + 1][yi - 1].side() == 0) {
							return true;
						}
						else {return false;}
					}
					else {return false;}
				}
				else {
					if (((xi + 2 == xf) && (yi - 2 == yf)) && (pieces[xi + 1][yi - 1] != null)) {
						if (pieces[xi + 1][yi - 1].side() == 0) {
							return true;
						}
						else {return false;}
					}
					else if (((xi - 2 == xf) && (yi - 2 == yf)) && (pieces[xi - 1][yi - 1] != null)){
						if (pieces[xi - 1][yi - 1].side() == 0) {
							return true;
						}
						else {return false;}
					}
					else {return false;}
				}
			}
		}
		else {return false;}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi];
		if (checkInBoard(xf, yf)) {
			if (p.isKing()) {
				if ((xi + 1 == xf || xi - 1 == xf) && (yi + 1 == yf || yi - 1 == yf)) {
					if (pieces[xf][yf] == null) {
						return true;
					}
					else { return false; }
				}
				else if (validCapture(xi, yi, xf, yf)) {
					return true;
				}
				else { return false; }
			}
			else if (p.side() == 0) {
				if ((xi + 1 == xf || xi - 1 == xf) && (yi + 1 == yf)) {
					if (pieces[xf][yf] == null) {
						return true;
					}
					else { return false; }
				}
				else if (validCapture(xi, yi, xf, yf)) {
					return true;
				}
				else { return false; }
			}
			else {
				if ((xi + 1 == xf || xi - 1 == xf) && (yi - 1 == yf)) {
					if (pieces[xf][yf] == null) {
						return true;
					}
					else { return false; }
				}
				else if (validCapture(xi, yi, xf, yf)) {
					return true;
				}
				else { return false; }
			}
		}
		else { return false; }
	}	

	public boolean canSelect(int x, int y) {
		if (pieces[x][y] == null) {
			if (selectedPiece != null) {
				if ((startx == selectedx && starty == selectedy)) {
					return validMove(startx, starty, x, y);
				}
				else if (selectedPiece.hasCaptured() == true){
					if (selectedPiece.isBomb() == false){
						return validCapture(selectedx, selectedy, x, y);
					}
					else {return false;}
				}
				else {return false;}
			}
			else {return false;}
		}
		else {
			if (pieces[x][y].side() == turn) {
				if (selectedPiece == null) {
					return true;
				}
				else if ((startx == selectedx) && (starty == selectedy)) {
					return true;
				}
				else {return false;}
			}
			else {return false;}
		}
	}


	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selectedPiece = pieces[x][y];
			startx = x;
			starty = y;
			selectedx = x;
			selectedy = y;
			}
		else if (pieces[x][y] == null){
			selectedPiece.move(x, y);
			selectedx = x;
			selectedy = y;
		}
	}

	private boolean checkInBoard(int x, int y) {
		if (x > -1) {
			if (y > -1) {
				if (x < 8) {
					if (y < 8) {
						return true;
					}
					else { return false; }
				}
				else { return false; }
			}
			else { return false; }
		}
		else { return false; }
	}

	public void place(Piece p, int x, int y) {
		if (checkInBoard(x, y)) {
			pieces[x][y] = p;
			selectedx = x;
			selectedy = y;
		}
	}

	public Piece remove(int x, int y) {
		if (checkInBoard(x, y)) {
			Piece i = pieces[x][y];
			pieces[x][y] = null;
			return i;
		}
		else {
			return null;
		}
	}
	

	public boolean canEndTurn() {
		if (startx != selectedx) {
			return true;
		}
		else if (starty != selectedy) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		if (turn == 0) {
			turn = 1;
			selectedPiece.doneCapturing();
			selectedPiece = null;
			startx = 0;
			starty = 0;
			selectedx = 0;
			selectedy = 0;
		}
		else {
			turn = 0;
			selectedPiece.doneCapturing();
			selectedPiece = null;
			startx = 0;
			starty = 0;
			selectedx = 0;
			selectedy = 0;
		}
	}

	public String winner() {
		int f = 0;
		int w = 0;
		for (int y = 0; y < 8; y += 1) {
			for (int x = 0; x < 8; x += 1) {
				if (pieces[x][y] != null) {
					if (pieces[x][y].isFire() == true){
						f += 1;
					}
					else{
						w += 1;
					}
				}
			}
		}
		if (f < w && f == 0){
			return "Water";
		}
		if (f > w && w == 0) {
			return "Fire";
		}
		if (f == 0 && w == 0) {
			return "No one";
		}
		return null;
	}
}