public class Board {

	private Piece[][] pieces = new Piece[8][8];  //Location of pieces
	private Piece selectedPiece;
	private int player;  // 0 if fire, 1 if water
	private boolean moved;
	private int selectedX, selectedY, numFire, numWater;

	public Board(boolean shouldBeEmpty) {
		selectedPiece = null;
		selectedX = 0;
		selectedY = 0;
		player = 0;
		numFire = 0;
		numWater = 0;
		moved = false;
		if (shouldBeEmpty) {
			return;
		}
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		if ((j == 0) && (i % 2 == 0)) {
        			place(new Piece(true, this, i, j, "pawn"), i, j);
        		}
        		if ((j == 1) && (Math.abs(i) % 2 == 1)) {
        			place(new Piece(true, this, i, j, "shield"), i, j);
        		}
        		if ((j == 2) && (i % 2 == 0)) {
        			place(new Piece(true, this, i, j, "bomb"), i, j);
        		}
        		if ((j == 7) && (Math.abs(i) % 2 == 1)) {
        			place(new Piece(false, this, i, j, "pawn"), i, j);
        		}
        		if ((j == 6) && (i % 2 == 0)) {
        			place(new Piece(false, this, i, j, "shield"), i, j);
        		}
        		if ((j == 5) && (Math.abs(i) % 2 == 1)) {
        			place(new Piece(false, this, i, j, "bomb"), i, j);
        		}
        	}
        }
	}

	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if ((selectedPiece != null) && (pieces[j][i] == selectedPiece) )
            		 StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(j + .5, i + .5, .5);

                /* Draw the pieces*/
                if (pieceAt(j, i) != null) {
                	Piece p = pieceAt(j, i);
                	if (p.isFire()) {
                		if (p.isKing()) {
                			if (p.isShield()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else if (p.isBomb()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                		}
                		else {
                			if (p.isShield()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/shield-fire.png", 1, 1);
                			}
                			else if (p.isBomb()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/bomb-fire.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	}
                	else {
                		if (p.isKing()) {
                			if (p.isShield()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else if (p.isBomb()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                		}
                		else {
                			if (p.isShield()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/shield-water.png", 1, 1);
                			}
                			else if (p.isBomb()) {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/bomb-water.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(j+ .5, i + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}
                }
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		/* Returns the piece at position (x, y) on the board, or null */

		// Check if out of bounds
		if (outOfBound(x, y)) {
			return null;
		}
		return pieces[x][y];
	}

	private boolean outOfBound(int x, int y) {
		/* Returns whether the position (x, y) is out of bounds */

		return ((x < 0) || (x > 7) || (y < 0) || (y > 7));
	}

	public void place(Piece p, int x, int y) {
		/* Places p at (x, y) */

		if ((p == null) ||  (outOfBound(x, y))) {
			return;
		}

		/* If p already exists, removes it */
		if (onBoard(p)) {
			remove(p);
		}

		pieces[x][y] = p;

		if (p.isFire()) {
			numFire += 1;
		}
		else {
			numWater += 1;
		}

	}

	private boolean onBoard(Piece p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) == p) {
					return true;
				}
			}
		}
		return false;
	}

	private void remove(Piece p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) == p) {
					remove(i, j);
					return;
				}
			}
		}
	}

	public Piece remove(int x, int y) {
		/* Removes the piece at (x, y) and returns it */

		if (outOfBound(x, y)) {
			System.out.println("Target is out of bounds.");
			return null;
		}

		Piece p = pieceAt(x, y);

		if (p == null) {
			System.out.println("There is no piece there.");
			return null;
		}

		pieces[x][y] = null;

		if (p.isFire()) {
			numFire -= 1;
		}
		else {
			numWater -= 1;
		}

		return p;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) == null) {
			/* Can the empty square be selected? */
			if (selectedPiece != null) {
				if ((moved == false) && (validMove(selectedX, selectedY, x, y))) {
					return true;
				}	
				else if ((selectedPiece.hasCaptured() == true)
					&& (validCapture(selectedX, selectedY, x, y))) {
					return true;
				}
			}
			return false;
		}

		else {
			/* Can the piece be selected? */
			if (moved == false) {
				if (pieceAt(x, y).side() == player) {
					return true;
				}
			}
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (outOfBound(xf, yf)) {
			return false;
		}

		boolean king = pieceAt(xi, yi).isKing();
		boolean fire = pieceAt(xi, yi).isFire();

		if (pieceAt(xf, yf) == null) {
			if (Math.abs(xi - xf) == 1) {
				if ((king) && (Math.abs(yi - yf) == 1)) {
					// Kings can move in all directions
					return true;
				}
				else if ((fire) && (yf - yi == 1)) {
					// Fire pieces can only move up
					return true;
				}
				else if ((! fire) && (yi - yf == 1)) {
					// Water pieces can only move down
					return true;
				}
			}
		}
		if (validCapture(xi, yi, xf, yf)) {
			return true;
		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		boolean king = selectedPiece.isKing();
		boolean fire = selectedPiece.isFire();

		if (pieceAt(xf, yf) == null) {
			if ((Math.abs(xi - xf) == 2)) {
				int i = Math.min(xi, xf) + 1;
				int j = Math.min(yi, yf) + 1;
				if ((pieceAt(i, j) != null) && (fire != pieceAt(i, j).isFire())) {
					if ((king) && (Math.abs(yi - yf) == 2)) {
						// Kings can move in all directions
						return true;
					}
					else if ((fire) && (yf - yi == 2)) {
						// Fire pieces can only move up
						return true;
					}
					else if ((! fire) && (yi - yf == 2)) {
						// Water pieces can only move down
						return true;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		// Assumes (x, y) can be selected
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		}
		else if (pieceAt(x, y) == null) {
            selectedPiece.move(x, y);
            if ((selectedPiece.isBomb()) && (selectedPiece.hasCaptured())) {
            	selectedPiece = null;
            }
            selectedX = x;
            selectedY = y;
            moved = true;
        }
	}

	public boolean canEndTurn() {
		/* Returns whether the current player can end their turn */

		if (moved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		moved = false;
		selectedPiece = null;
		player = (player + 1) % 2;
	}

	public String winner() {
		if ((numFire == 0) && (numWater == 0)) {
			return "No one";
		}
		else if (numFire == 0) {
			return "Water";
		}
		else if (numWater == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}


	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        b.drawBoard();

        while (b.winner() == null) {
        	b.drawBoard();

        	if (StdDrawPlus.mousePressed()) {
                double dx = StdDrawPlus.mouseX();
                int x = (int) dx;
                double dy = StdDrawPlus.mouseY();
                int y = (int) dy;
                if (b.canSelect(x, y)) {
                	b.select(x, y);
            	}
        	}

        	if (StdDrawPlus.isSpacePressed()) {
        		if (b.canEndTurn()) {
        			b.endTurn();
        		}
        	}
        	StdDrawPlus.show(1);
		}

		// Game over
		b.drawBoard();
		StdDrawPlus.show(1);
		System.out.println(b.winner() + " has won!");
	}
}