

public class Board {


	private Piece[][] pieces = new Piece[8][8]; // [x][y] where x,y are within 0-7
	private int selectedX = -1; // -1 to indicate unselected state
	private int selectedY = 0;
	private Piece movedPiece = null;
	private int currentSide = 0;

	public static void main(String[] args) {
		Board b = new Board(false);
		/*
		Piece p1 = new Piece(true, b, 3, 3, "pawn");
		Piece p2 = new Piece(false, b, 4, 4, "pawn");
		b.place(p1, 3, 3);
		b.place(p2, 4, 4);
		*/
		StdDrawPlus.setCanvasSize();
		StdDrawPlus.setScale(0, 8);
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				b.drawSquare(x, y, false);
			}
		}
		String result = null;
		while(result == null) {
			if(StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if(b.canSelect(x, y)) {
					b.select(x,y);
					b.drawBoard();
					StdDrawPlus.show();
				}
			}
			if(StdDrawPlus.isSpacePressed()) {
				if(b.canEndTurn()) {
					b.endTurn();
					b.drawBoard();
					StdDrawPlus.show();
				}
				result = b.winner();
			}
			StdDrawPlus.show(15);
		}
		System.out.println(result);
	}


	public Board(boolean shouldBeEmpty) {
		if(!shouldBeEmpty) {
			for(int x = 0; x < 8; x += 2) {
				place(new Piece(true, this, x, 0, "pawn"), x, 0);
				place(new Piece(true, this, x, 2, "bomb"), x, 2);
				place(new Piece(false, this, x, 6, "shield"), x, 6);
			}
			for(int x = 1; x < 8; x += 2) {
				place(new Piece(true, this, x, 1, "shield"), x, 1);
				place(new Piece(false, this, x, 5, "bomb"), x, 5);
				place(new Piece(false, this, x, 7, "pawn"), x, 7);
			}
		}
	}

	private void drawBoard() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				drawSquare(x,y,false);
			}
		}
		drawSquare(selectedX, selectedY, true);
	}

	private void drawSquare(int x, int y, boolean isSelected) {
		if(isSelected) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		} else if((x+y)%2 == 0) {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(x+0.5, y + 0.5, 0.5);
		Piece p = pieceAt(x,y);
		if(p != null) {
			StdDrawPlus.picture(x+0.5, y+0.5, getImagePath(p), 1, 1);
		}
	}

	private String getImagePath(Piece p) {
		StringBuilder str = new StringBuilder("img/");
		if(p.isBomb()) {
			str.append("bomb");
		} else if (p.isShield()) {
			str.append("shield");
		} else {
			str.append("pawn");
		}
		if(p.isFire()) {
			str.append("-fire");
		} else {
			str.append("-water");
		}
		if(p.isKing()) {
			str.append("-crowned");
		}
		str.append(".png");
		return str.toString();
	}
	public Piece pieceAt(int x, int y) {
		if(outOfBound(x,y)) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if(outOfBound(x,y)) {
			return false;
		}
		Piece p = pieceAt(x,y);
		if(p == null) {
			if(movedPiece == null) {
				return validMove(selectedX, selectedY, x, y, false);
			}
			if(movedPiece.hasCaptured()) {
				return validMove(selectedX, selectedY, x, y, true);
			}
			return false;
		} else {
			return movedPiece == null && p.side() == currentSide;
		}
	}

	public void select(int x, int y) {
		if(pieceAt(x,y) == null) {
			Piece pp = pieceAt(selectedX, selectedY);
			pp.move(x, y);
			movedPiece = pp;
		}
		selectedX = x;
		selectedY = y;
	}

	private boolean validMove(int x, int y, int xf, int yf, boolean mustCapture) {
		Piece p = pieceAt(x,y);
		if(p == null || pieceAt(xf, yf) != null) {
			return false;
		}
		int dx = Math.abs(xf - x);
		int dy = yf - y;
		if(p.isKing()) {
			dy = Math.abs(dy);
		} else {
			dy *= 1 - p.side()*2;
		}

		if(dx == 1 && dy == 1) {
			return !mustCapture;
		} else if (dx == 2 && dy == 2) {
			Piece capt = pieceAt((x+xf)/2,(y+yf)/2);
			return capt != null && capt.side() == 1 - p.side();
		}
		return false;
	}

	public void place(Piece p, int x, int y) {
		if(p == null || outOfBound(x,y) ) {
			return;
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if(outOfBound(x,y)) {
			System.out.println("("+x+", "+y+") is out of the board");
		}
		if(pieces[x][y] == null) {
			System.out.println("There is no piece at ("+x+", "+y+")");
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	private boolean outOfBound(int x, int y) {
		return x < 0 || y < 0 || x >= 8 || y >= 8;
	}

	public boolean canEndTurn() {
		return movedPiece != null;
	}

	public void endTurn() {
		movedPiece.doneCapturing();
		movedPiece = null;
		currentSide = 1 - currentSide;
		selectedX = -1;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				Piece p = pieceAt(x,y);
				if(p != null) {
					if(p.isFire()) {
						fire++;
					} else {
						water++;
					}
				}
			}
		}
		if(!(fire == 0||water == 0)) {
			return null;
		}
		if(fire == water) {
			return "No one";
		}
		if(fire == 0) {
			return "Water";
		}
		return "Fire";
	}
}
