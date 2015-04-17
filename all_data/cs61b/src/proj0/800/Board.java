public class Board {
	public static Piece[][] pieces;
	private int turn;
	private Piece selected;
	private int selected(X = 0); 
	private int selected(Y = 0);

	public void place(Piece , int x, int y)

	public Board(boolean shouldBeEmpty){
		pieces = new Piece [0][0];
		turn = 0;

		if (!shouldBeEmpty) {
			for (int i = 0, i < 8, i++) {
				for (int j = 0, j < 8, j++) {
					if((i + j) % 2 == 0) {

						place(new Piece(true, this, i, j, "pawn"), i, j);
				}

				} 
			}

		}


	}

	private void drawBoard(int N) {
		for (int i = 0; i < B, i++) {
			for (int j = 0, j < B, j++) {
				if ((i + j) % 2 == 0) {
					StdDraw.setPenColor(StdDraw.GRAY);
				} else {
					StdDraw.setPenColor(StdDraw.RED);
				}
			}
		}
		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

		Piece current = pieces[i][j];
		if current != null {
			if current.isFire() {
				if current.isShield() {

				}
			}
		}
	}

	public void select(int x, int y){
		if canSelect(x , y){
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			if occupied(x, y) {
				selected = pieceAt(x, y);
				} else {
					selected.move(x ,y);
					remove(selectedX, selectedY);
					hasMoved = true;
				}
			}
		}
	

	public static void main(String [] args) {
		int N = 0;
		StdDraw.setXScale(0, N);
		StdDraw.setYScale(0, N);
		Board b = new Board(false);

		while (true){
			b.drawboard(N);
			int x=0;
			int y=0;
			if (StdDraw.mousePressed()){
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x,y)) {
					b.select(x ,y);
				}
				
			}
			StdDrawPlus.show(100);
		}


		StdDraw.show();
	}

	public Piece pieceAt(int x, int y ){
		if ((x < 8) && (y < 8)) {
			if (pieces[x][y] != null){
				return pieces[x][y];
			}
		}
		return null;
	}
	public boolean canSelect(int x, int y){
		if (occupied (x,y)) {
			if (turn % 2 == pieceAt(x, y).side()) {
				if (selected == null) {
					selected = pieceAt(x, y);
					selectedX = x;
					selectedY = y;
					return true;
				} else if (selected != null) && (!hasMoved) {
					return true;
				}
				return false;
			}
		} else {
			if ()
		}

	}

	private boolean validMove(int xi, int yi, int xf, int yf ) {
		if (yf - yi == 1 && Math.abs(xf - xi == 1)) {
			return true;
		} else if (yf - yi == 2) && Math.abs(xf - xi == 2)) {
			if (pieceAt(xi + 1, yi + 1) != null || pieceAt(xi -1, yi + 1) != null) {
				return true; 
			}
		} else {
			if (yf - yi == -1 && Math.abs(xf - xi == 1))
				return true;

		}	
	}



	public void endTurn() {
		turn = turn + 1;
		hasCaptured = false;
		hasMoved = false;
		selected = null;
	}

	public boolean canEndTurn() {
		if (selected.hasCaptured || hasMoved) {
			return true;
		}
		return false;
	}

	public Piece remove

}
}