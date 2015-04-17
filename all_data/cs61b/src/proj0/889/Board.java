public class Board {
	private Piece[][] myBoard;
	private static final int MAXSIZE = 8;
	private boolean hasMoved = false;
	private boolean hasSelected = false;
	private Piece selectedPiece = null;
	private int selectedX;
	private int selectedY;
	private boolean onlyFireLeft = false;
	private boolean onlyWaterLeft = false;
	private boolean fireTurn = true;

	public static void main(String[] args) {
		Board b = new Board(false);
		while(true) {
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.select(x, y);
				}
 			}
 			else if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
 				b.endTurn();
 			}
 			b.drawBoard();
 			StdDrawPlus.show(100);

		}
		
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, MAXSIZE);
		StdDrawPlus.setYscale(0, MAXSIZE);
		for (int i = 0; i < MAXSIZE; i++) {
			for (int j = 0; j < MAXSIZE; j++) {
				if (hasSelected && i == selectedX && j == selectedY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if(myBoard[i][j] != null) {
					drawPiece(i, j, myBoard[i][j]);
				}
			}
		}
	}

	private void drawPiece(int x, int y, Piece p) {
		String img = "img/";

		if (p.isBomb()) {
			img += "bomb";
		}
		else if (p.isShield()) {
			img += "shield";
		}
		else {
			img += "pawn";
		}
		if (p.isFire()) {
			img += "-fire";
		}
		else {
			img += "-water";
		}
		if (p.isKing()) {
			img += "-crowned";
		}
		img += ".png";

		StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
	} 

	public Board(boolean shouldBeEmpty) {
		myBoard = new Piece[8][8];
		if (!shouldBeEmpty) {
			myBoard = new Piece[8][8];
			for (int i = 0; i < 8; i+=2){
				int j = 0;
				this.myBoard[i][j] = new Piece(true, this, i, j, "pawn");
			}
			for (int i = 1; i < 8; i+=2){
				int j = 1;
				this.myBoard[i][j] = new Piece(true, this, i, j, "shield");
			}
			for (int i = 0; i < 8; i+=2){
				int j = 2;
				this.myBoard[i][j] = new Piece(true, this, i, j, "bomb");
			}
			for (int i = 1; i < 8; i+=2){
				int j = 5;
				this.myBoard[i][j] = new Piece(false, this, i, j, "bomb");
			}
			for (int i = 0; i < 8; i+=2){
				int j = 6;
				this.myBoard[i][j] = new Piece(false, this, i, j, "shield");
			}
			for (int i = 1; i < 8; i+=2){
				int j = 7;
				this.myBoard[i][j] = new Piece(false, this, i, j, "king");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x >= 8 || x < 0 || y >= 8 || y < 0 ) {
			return null;
		}
		else {
			return myBoard[x][y];
		}
	}

	
	public boolean canSelect(int x, int y) {
		if (x >= 8 || x < 0 || y >= 8 || y < 0 ) {
			return false;
		}
		if (myBoard[x][y] != null && fireTurn == myBoard[x][y].isFire() && (!hasSelected || !hasMoved)) {
			return true;
		}
		else if (myBoard[x][y] == null) {
			if (hasSelected && !hasMoved && validMove(selectedX, selectedY, x, y)) {
				return true;
			}
			else if (hasSelected && selectedPiece.hasCaptured() && validMove(selectedX, selectedY, x, y) && Math.abs(selectedX - x) == 2) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p0 = myBoard[xi][yi];
		if ((xi >= 8 || xi < 0 || yi >= 8 || yi < 0 ) || (xf >= 8 || xf < 0 || yf >= 8 || yf < 0 )) {
			return false;
		} 
		if (myBoard[xf][yf] != null || p0 == null) {
			return false;
		}
		if (!p0.isKing()) {
			if ((p0.isFire() && yf < yi) || (!p0.isFire() && yf > yi)) {
				return false;
			}
		}
		if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)){
			return true;
		}
		if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
			Piece p1 = myBoard[(xi + xf) / 2][(yi + yf) / 2];
			if (p1 != null && p1.isFire() != p0.isFire()) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (myBoard[x][y] != null) {
			selectedX = x;
			selectedY = y;
			selectedPiece = myBoard[x][y];
			hasSelected = true;
		}
		else {
			pieceAt(selectedX, selectedY).move(x, y);
			selectedX = x;
			selectedY = y;
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            myBoard[x][y] = p;
        }
	}

	public Piece remove(int x, int y) {
		Piece removedPiece = myBoard[x][y];
		if (x >= 8 || x < 0 || y >= 8 || y < 0 ) { 
			return null;
		}
		if (myBoard[x][y] == null) {
			return null;
		}
		else {
			myBoard[x][y] = null;
			return removedPiece;
		}
	}

	public boolean canEndTurn() {
		if (hasMoved == true) {
			return hasMoved;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		hasSelected = false;
		hasMoved = false;
		fireTurn = !fireTurn;
	}

	public String winner() {
		onlyFireLeft = onlyWaterLeft = false;
		for (int i = 0; i < MAXSIZE; i++) {
			for (int j = 0; j < MAXSIZE; j++) {
				if (myBoard[i][j] != null) {
					if (myBoard[i][j].isFire()) {
						onlyFireLeft = true;
					}
					else {
						onlyWaterLeft = true;
					}
				}
			}
		}
		if (onlyFireLeft == true && onlyWaterLeft == true) {
			return null;
		}
		if (onlyFireLeft == true && onlyWaterLeft == false) {
			return "Fire";
		}
		if (onlyFireLeft == false && onlyWaterLeft == true) {
			return "Water";
		}
		else {
			return "No one";
		}
	}
}