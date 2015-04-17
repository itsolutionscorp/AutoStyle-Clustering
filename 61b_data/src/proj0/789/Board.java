public class Board {

	private Piece lastSelectedPiece = null;
	private Piece[] pieces = new Piece[24];
	private Piece[][] board = new Piece[8][8];

	private String[][] img = new String[8][8];

	private boolean fireTurn = true,
	                  canEnd = false,
	                 mustEnd = false;

	private int lspCurrentX,
	            lspCurrentY;

	public static void main(String[] args) {
		Board b = new Board(false);
		while(true) {
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x,(int) y)) {
                	b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	b.endTurn(); 
            }
		}
	}

	public Board(boolean shouldBeEmpty) {
		for(int i = 0; i < 24; i++) {
			pieces[i] = null;
		}
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = null;
				img[i][j] = null;
			}
		}
		if (!shouldBeEmpty) {
			for(int i = 0; i < 4; i++) {
				pieces[i] = new Piece(true, this, i + i, 0, "pawn");
				img[i+ i][0] = "img/pawn-fire.png";
				board[i + i][0] = pieces[i];
			}
			for(int i = 0; i < 4; i++) {
				pieces[i + 4] =  new Piece(true, this, i + i + 1, 1, "shield");
				img[i + i + 1][1] = "img/shield-fire.png";
				board[i + i + 1][1] = pieces[i + 4];
			}
			for(int i = 0; i < 4; i++) {
				pieces[i + 8] = new Piece(true, this, i + i, 2, "bomb");
				img[i + i][2] = "img/bomb-fire.png";
				board[i + i][2] = pieces[i + 8];
			}
			for(int i = 0; i < 4; i++) {
				pieces[i + 12] = new Piece(false, this, i + i + 1, 5, "bomb");
				img[i + i + 1][5] = "img/bomb-water.png";
				board[i + i + 1][5] = pieces[i + 12];
			}
			for(int i = 0; i < 4; i++) {
				pieces[i + 16] = new Piece(false, this, i + i, 6, "shield");
				img[i + i][6] = "img/shield-water.png";
				board[i + i][6] = pieces[i + 16];
			}
			for(int i = 0; i < 4; i++) {
				pieces[i + 20] = new Piece(false, this, i + i + 1, 7, "pawn");
				img[i + i + 1][7] = "img/pawn-water.png";
				board[i + i + 1][7] = pieces[i + 20];
			}
		}
		drawBoard();
	}

	public Piece pieceAt(int x, int y) {
		return board[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (mustEnd) { return false; }
		else if (lastSelectedPiece == null) {
			if (pieceAt(x, y) == null) { return false; }
			else if (fireTurn == pieceAt(x, y).isFire()) {
				return true;
			}
			else{ return false; }
		}
		else if (lastSelectedPiece.hasCaptured() && pieceAt(x, y) != null) {
			return false;
		}
		else if (pieceAt(x, y) != null && lastSelectedPiece.isFire() == pieceAt(x, y).isFire()) {
			return true;
		}
		else if (canEnd && lastSelectedPiece.hasCaptured()) {
			if (lastSelectedPiece == pieceAt(x, y)) { return true; }
			else if (pieceAt(x, y) != null) { return false; }
			else if (lastSelectedPiece.isFire() && !lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) && (y - lspCurrentY == 2) && 
						 (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (!pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else if (!lastSelectedPiece.isFire() && !lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) && (y - lspCurrentY == -2) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else if (lastSelectedPiece.isFire() && lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) &&
					     ((y - lspCurrentY == 2) || (y - lspCurrentY == -2)) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (!pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else if (!lastSelectedPiece.isFire() && lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) &&
					     ((y - lspCurrentY == 2) || (y - lspCurrentY == -2)) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else { return false; }
		}
		else {
			if (pieceAt(x, y) != null) { return false; }
			if (lastSelectedPiece.isFire() && !lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 1) || (x - lspCurrentX == -1)) && (y - lspCurrentY == 1)) {
					return true;
				}
				else if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) && (y - lspCurrentY == 2) && 
						 (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (!pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else if (!lastSelectedPiece.isFire() && !lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 1) || (x - lspCurrentX == -1)) && (y - lspCurrentY == -1)) {
					return true;
				}
				else if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) && (y - lspCurrentY == -2) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else if (lastSelectedPiece.isFire() && lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 1) || (x - lspCurrentX == -1)) &&
					((y - lspCurrentY == 1) || (y - lspCurrentY == -1))) {
					return true;
				}
				else if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) &&
					     ((y - lspCurrentY == 2) || (y - lspCurrentY == -2)) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (!pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
			else if (!lastSelectedPiece.isFire() && lastSelectedPiece.isKing()) {
				if (((x - lspCurrentX == 1) || (x - lspCurrentX == -1)) &&
					((y - lspCurrentY == 1) || (y - lspCurrentY == -1))) {
					return true;
				}
				else if (((x - lspCurrentX == 2) || (x - lspCurrentX == -2)) &&
					     ((y - lspCurrentY == 2) || (y - lspCurrentY == -2)) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2) != null) && 
					     (pieceAt((x + lspCurrentX) / 2, (y + lspCurrentY) / 2).isFire())) {
					return true;
				}
				else { return false; }
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			lastSelectedPiece = board[x][y];
			lspCurrentX = x;
			lspCurrentY = y;
			drawBoard();
		}
		else { 
			lastSelectedPiece.move(x, y);
			lastSelectedPiece = pieceAt(x, y);
			lspCurrentX = x;
			lspCurrentY = y;
			canEnd = true;
			if (lastSelectedPiece == null) { mustEnd = true; }
			if (!canGoAgain(x, y)) { mustEnd = true; }
			drawBoard();
		}
	}

	private boolean canGoAgain(int x, int y) {
		if (mustEnd == true) { return false; }
		else if (lastSelectedPiece == null) { return false; }
		else if (lastSelectedPiece.hasCaptured() == false) { return false; }
		else {
			if (lastSelectedPiece.isFire() && !lastSelectedPiece.isKing()) {
				if (y + 2 >= 8) {
					return false;
				}
				else if (x - 2 < 0) {
					return (canSelect(x + 2, y + 2));
				}
				else if (x + 2 >= 8) {
					return (canSelect(x - 2, y + 2));
				}
				return (canSelect(x - 2, y + 2) || canSelect(x + 2, y + 2));
			}
			else if (!lastSelectedPiece.isFire() && !lastSelectedPiece.isKing()) {
				if (y - 2 < 0) {
					return false;
				}
				else if (x - 2 < 0) {
					return (canSelect(x + 2, y - 2));
				}
				else if (x + 2 >= 8) {
					return (canSelect(x - 2, y - 2));
				}
				return (canSelect(x - 2, y - 2) || canSelect(x + 2, y - 2));
			}
			else{
				if (y - 2 < 0) {
					if (x - 2 < 0) {
						return (canSelect(x + 2, y + 2));
					}
					else if (x + 2 >= 8) {
						return (canSelect(x - 2, y + 2));
					}
					return (canSelect(x - 2, y + 2) || canSelect(x + 2, y + 2));
				}
				else if (y + 2 >= 8) {
					if (x - 2 < 0) {
						return (canSelect(x + 2, y - 2));
					}
					else if (x + 2 >= 8) {
						return (canSelect(x - 2, y - 2));
					}
					return (canSelect(x - 2, y - 2) || canSelect(x + 2, y - 2));
				}
				else if (x - 2 < 0) {
					return (canSelect(x + 2, y - 2) || canSelect(x + 2, y + 2));
				}
				else if (x + 2 >= 8) {
					return (canSelect(x - 2, y - 2) || canSelect(x - 2, y + 2));
				}
				return (canSelect(x - 2, y + 2) || canSelect(x + 2, y + 2) ||
					    canSelect(x - 2, y - 2) || canSelect(x + 2, y - 2));
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if ((p != null) && ((0 <= x) && (x < 8)) && ((0 <= y) && (y < 8))) {
			if (pieceAt(x, y) != null) {
				remove(x, y);
			}
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if (p == board[i][j]) {
						remove(i, j);
					}
				}
			}
			board[x][y] = p;
			if (p.isFire()) {
				if (!p.isKing()) {
					if (p.isBomb()) {
						img[x][y] = "img/bomb-fire.png";
					}
					else if (p.isShield()) {
						img[x][y] = "img/shield-fire.png";
					}
					else {
						img[x][y] = "img/pawn-fire.png";
					}
				}
				else {
					if (p.isBomb()) {
						img[x][y] = "img/bomb-fire-crowned.png";
					}
					else if (p.isShield()) {
						img[x][y] = "img/shield-fire-crowned.png";
					}
					else {
						img[x][y] = "img/pawn-fire-crowned.png";
					}
				}
			}
			else {
				if (!p.isKing()) {
					if (p.isBomb()) {
						img[x][y] = "img/bomb-water.png";
					}
					else if (p.isShield()) {
						img[x][y] = "img/shield-water.png";
					}
					else {
						img[x][y] = "img/pawn-water.png";
					}
				}
				else {
					if (p.isBomb()) {
						img[x][y] = "img/bomb-water-crowned.png";
					}
					else if (p.isShield()) {
						img[x][y] = "img/shield-water-crowned.png";
					}
					else {
						img[x][y] = "img/pawn-water-crowned.png";
					}
				}
			}
			if (lspCurrentX >= 0 && lspCurrentY >= 0) {
				lspCurrentX = x;
				lspCurrentY = y;
			}
			drawBoard();
		}
	}

	public Piece remove(int x, int y) {
		Piece returnPiece = board[x][y];
		board[x][y] = null;
		img[x][y] = null;
		return returnPiece;
	}

	public boolean canEndTurn() {
		return canEnd;
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		canEnd = false;
		mustEnd = false;
		if (lastSelectedPiece != null) {
			lastSelectedPiece.doneCapturing();
		}
		lastSelectedPiece = null;
		lspCurrentX = -1;
		lspCurrentY = -1;
		drawBoard();
		if (checkForEnd()) { winner(); }
	}

	private boolean checkForEnd() {
		int firePieces = 0,
		   waterPieces = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) { firePieces = firePieces + 1; }
					else { waterPieces = waterPieces + 1; }
				}
			}
		}
		if (firePieces == 0 || waterPieces == 0) { return true; }
		return false;
	}

	public String winner() {
		int firePieces = 0,
		   waterPieces = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) { firePieces = firePieces + 1; }
					else { waterPieces = waterPieces + 1; }
				}
			}
		}
		if (firePieces == 0 && waterPieces == 0) { return "No one"; }
		else if (waterPieces == 0) { return "Fire"; }
		else if (firePieces == 0)  { return "Water"; }
		else return null;
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		if (lastSelectedPiece != null) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(lspCurrentX + .5, lspCurrentY + .5, .5);
		}
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					StdDrawPlus.picture(i + .5, j + .5, img[i][j], 1, 1);
				}
			}
		}
	}
}