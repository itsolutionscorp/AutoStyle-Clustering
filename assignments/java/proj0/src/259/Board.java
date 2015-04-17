public class Board {
	private static int N = 8;
	private Piece[][] pieces = new Piece[N][N];
	private Piece selected = null;
	private Piece moved = null;
	private int player = 0;
	private int oldX;
	private	int oldY;


	
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {
        	b.drawBoard(N);
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
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

	/* Draw NxN checker board */
	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j] != null) {
                	StdDrawPlus.picture(i + .5, j + .5, image(pieces[i][j]), 1, 1);
                }
			}
		}
		if (selected != null) {
                	StdDrawPlus.filledSquare(oldX + .5, oldY + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	if (pieceAt(oldX, oldY) != null) {
                		StdDrawPlus.picture(oldX + .5, oldY + .5, image(pieceAt(oldX, oldY)), 1, 1);
                	}
                }
	}

	/* Initialize the pieces array */
	private void defaultConfiguration() {
		String type = null;
		boolean isFire = false;

		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
				if (j >= 0 && j <= 2) {
					isFire = true;
				}
				if (j >= 5 && j <= 7) {
					isFire = false;
				}
				if ((j == 0) || (j == 7)) {
					type = "pawn";
				}
				if ((j == 1) || (j == 6)) {
					type = "shield";
				}
				if ((j == 2) || (j == 5)) {
					type = "bomb";
				}
				if (((j >= 0) && (j <= 2)) || ((j >= 5) && (j <= 7))) {
					pieces[i][j] = new Piece(isFire, this, i, j, type);
				}
			}
		}
	}
}

	/* Path to the image of each type */
	private String image (Piece p) {
		String img = null;
		if (p != null) {
		if (p.isFire()) {
			if (p.isBomb()) {
				if (p.isKing()){
					img = "img/bomb-fire-crowned.png";
				}
				else {img = "img/bomb-fire.png";}	
			}
			if (p.isShield()) {
				if (p.isKing()) {
					img = "img/shield-fire-crowned.png";
				}
				else {img = "img/shield-fire.png";}
			}
			if (!p.isShield() && !p.isBomb()) {
				if (!p.isKing()) { 
					img = "img/pawn-fire.png";
				}
				else {img = "img/pawn-fire-crowned.png";}
			}

		}
		else {
			if (p.isBomb()) {
				if (p.isKing()){
					img = "img/bomb-water-crowned.png";
				}
				else {img = "img/bomb-water.png";}	
			}
			if (p.isShield()) {
				if (p.isKing()) {
					img = "img/shield-water-crowned.png";
				}
				else {img = "img/shield-water.png";}
			}
			if (!p.isShield() && !p.isBomb()) {
				if (!p.isKing()) { 
					img = "img/pawn-water.png";
				}
				else {img = "img/pawn-water-crowned.png";}
			}
		}
	}
		return img;
	
}


	/* Board constructor */
	public Board(boolean shouldBeEmpty) {
		
		if (shouldBeEmpty == false) {
			defaultConfiguration();
			}

		}

	/* Return the piece at loacation (x, y) */
	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0 || x >= 8 || y >= 8) {
			return null;
		}
		if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		return null;
	}

	/* Place the piece p at the location (x, y) */
	public void place(Piece p, int x, int y) {
		if (!((p == null) || (x >= N) || (y >= N) || x < 0 || y < 0 )) {
		
		Piece p2 = pieceAt(x, y);
		if (p2 != null) {
			pieces[x][y] = null;
		}
		pieces[x][y] = p;
		oldX = x;
		oldY = y;
		if(p.isBomb() && p.hasCaptured()) {
			selected = null;
			moved = null;
		}
	}
	}

	/* Remove any existing piece at location (x, y) */
	public Piece remove(int x, int y) {
		if (x >= N || y >= N) {
			System.out.println("The input position is out of bound!");
			return null;
		}
		Piece p = pieceAt(x, y);
		if (p == null) {
			System.out.println("There is no piece at this position");
			return null;
		}
		pieces[x][y] = null;
		return p;

	}

	/* Check if a move from (xi, yi) to (xf, yf) is valid */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece p2 = pieceAt(xf, yf);
		int xDiff = Math.abs(xi - xf);
		int yDiff = yf - yi;
		Piece p3 = pieceAt((xi + xf) / 2, (yi + yf) / 2);

		if (p == null) {
			return false;
		}
		if (p2 != null) {
			return false;
		}
		if(p.hasCaptured() && xDiff == 1 && Math.abs(yDiff) == 1) {
			return false;
		} 
		if (xDiff != Math.abs(yDiff)) {
			return false;
		}
		if (xDiff > 2 || Math.abs(yDiff) > 2 || xDiff == 0 || yDiff == 0) {
			return false;
		}
		if (p.isFire() && !p.isKing() && (yDiff < 0)) {
			return false;
		}
		if (!p.isFire() && !p.isKing() && (yDiff > 0)) {
			return false;
		}
		if (xDiff == 2 && Math.abs(yDiff) == 2) {
			if ((p3 == null) || (p.side() == p3.side())) {
				return false;
			} 
		}
		return true;
	}

	/* Check whether or not is is valid to select a piece or an empty spot */
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);

		if (p != null) {

			if (player == p.side()) {
				if ((selected == null) || (moved == null)) {
					return true;
				}
				return false;
			}
			return false;
		}
		else { 
			if (selected != null) {
				if ((moved == null) && (validMove(oldX, oldY, x, y))) {
					return true;
				}
				if ((selected.hasCaptured()) && (validMove(oldX, oldY, x, y))) {
					return true;
				}
			}
			return false;
		}
	}

	/* After checking canSelect, select the piece and prepare for the move,
	/  if selecting an empty spot then check if the player already select a
	/  a piece and move the piece */
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		
		if (p != null) {
			selected = p;
			oldX = x;
			oldY = y;
		}
		if (p == null) {
			selected.move(x, y);
			moved = selected;
		}
	}

	/* when pressing spacebar, check to see if it is ok to end the turn */
	public boolean canEndTurn() {
		if (moved != null) {
			return true;
		}
		if ((selected != null) && (selected.hasCaptured())) {
			return true;
		}
		return false;
	}

	/* Switch the player when ending turn */
	private void switchTurn() {
		player += 1;
		if (player > 1) {
			player = 0;
		}
	}

	/* Call endTurn to refresh the board's state */
	public void endTurn() {
		switchTurn();
		if (moved.hasCaptured()) {
			moved.doneCapturing();
		}
		selected = null;
		moved = null;
		oldX = 0;
		oldY = 0;

	}

	/* Return the winner after the game is over or null when the board is empty */
	public String winner() {
		String winner;
		int fire = 0;
		int water =0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						fire += 1;
					}
					else {
						water += 1;
					}
				}
			}
		}
		if (fire != 0 && water != 0) {
			return null;
		}
		else if (fire == 0 && water == 0) {
			winner = "No one";
		}
		else if (fire == 0 && water != 0) {
			winner = "Water";
		}
		else {
			winner = "Fire";
		}
		return winner;
	}

}
