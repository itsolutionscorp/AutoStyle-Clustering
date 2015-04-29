public class Board {
	private Piece[][] pieceArray;
	private int player;
	private int[] selectedPiecePos;
	private boolean hasMoved;
	private int fPieces;
	private int wPieces;
	
	public Board(boolean shouldBeEmpty) {
		pieceArray = new Piece[8][8];
		hasMoved = false;
		selectedPiecePos = new int[]{-1, -1};
		player = 0;
		if (!shouldBeEmpty) {
			fPieces = 12;
			wPieces = 12;
			setPieces(true, this);
			setPieces(false, this);
		}
		else {
			fPieces = 0;
			wPieces = 0;
		}
	}

	public static void main(String args[]) {
		Board b = new Board(false);
		int xInt;
		int yInt;
		double x;
		double y;
		Piece ptr;

		b.draw();
		while(b.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();
                xInt = (int)(x);
                yInt = (int)(y);
                if (b.canSelect(xInt, yInt)) {
					b.select(xInt, yInt);
            	}
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }
            StdDrawPlus.show(10);
            b.draw();
        }
        System.out.println("The winner is " + b.winner());
	}

	private void draw() {
		Piece p;
		StdDrawPlus.setXscale(0, 8);
    	StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		if (getSelectedPieceX() == i && getSelectedPieceY() == j) {
        			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        		}
            	else if ((i + j) % 2 == 0) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	}
            	else {
            		StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	p = pieceAt(i, j);
            	if (p != null) {
            		if (p.isFire()) {
            			if (p.isBomb()) {
            				if (p.isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            				}
	            		}
	            		else if (p.isShield()) {
	            			if (p.isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            				}
	            		}
	            		else {
	            			if (p.isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            				}
	            		}
            		}
            		else {
            			if (p.isBomb()) {
            				if (p.isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            				}
	            		}
	            		else if (p.isShield()) {
	            			if (p.isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            				}
	            		}
	            		else {
	            			if (p.isKing()) {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            				}
            				else {
            					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            				}
	            		}
            		}
                }
        	}
    	}
	}

	private Piece makePiece(boolean isFire, Board b, int x, int y, String type) {
		Piece result = new Piece(isFire, b, x, y, type);
		return result;
	}

	private void setPieces(boolean isFire, Board b) {
		int row;
		boolean onLeft;
		int column;
		String[] types;

		if (isFire) {
			types = new String[]{"pawn", "shield", "bomb"};
			row = 0;
			onLeft = true;
		}

		else {
			types = new String[]{"bomb", "shield", "pawn"};
			row = 5;
			onLeft = false;
		}

		for (int i = 0; i < 3; i++, row++)
		{
			if (onLeft) column = 0;
			else column = 1;

			for (; column < 8; column += 2) {
				pieceArray[column][row] = makePiece(isFire, b, column, row, types[i]);
			} 
			onLeft = !onLeft;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (!isInBounds(x, y)) {
			return null;
		}
		else if(pieceArray[x][y] != null) {
			return pieceArray[x][y];
		}
		return null;
	}
	
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			if (p.side() == player && !hasMoved) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (hasSelected()) {
				p = selectedPiece();
				if (validMove(p, getSelectedPieceX(), getSelectedPieceY(), x, y)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			Piece movingPiece = selectedPiece();
			if(movingPiece != null) {
				place(movingPiece, x, y);
				hasMoved = true;
				movingPiece.move(x, y);
				if (movingPiece.hasCaptured()) {
					if (movingPiece.isBomb()) {
						explosion(x, y);
						resetSelected();
					}
				}
			}
		}
		else {
			changeSPieceCoord(x, y);
		}
	}

	private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
		if (!isInBounds(xf, yf)) {
			return false;
		}
		else if (p == null) {
			return false;
		}
		else if (pieceAt(xf, yf) != null) {
			return false;
		}
		else if (!movingForward(p.isFire(), yi, yf) && !p.isKing()) {
			return false;
		}
		else if (Math.abs(xi - xf) != Math.abs(yi - yf)) {
			return false;
		}
		else if (Math.abs(xi - xf) != 2 && Math.abs(xi - xf) != 1) {
			return false;
		}
		else if (hasMoved && (!capturingMove(p, xi, xf, yi, yf) || !p.hasCaptured())) {
			return false;
		}
		else if (Math.abs(xi - xf) == 2 && !capturingMove(p, xi, xf, yi, yf)) {
			return false;
		}
		else {
			return true;
		}
	}
	

	public void place(Piece p, int x, int y) {
		if (isInBounds(x, y) && p != null) {
			pieceArray[x][y] = p;
			changeSPieceCoord(x, y);
		}
	}

	public Piece remove(int x, int y) {
		if (!isInBounds(x, y)) {
			System.out.println("Error: Out of bounds coordinates");
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("Error: No piece at coordinates");
			return null;
		}
		else {
			Piece result = pieceArray[x][y];
			pieceArray[x][y] = null;
			return result;
		}
	}

	private boolean isInBounds(int x, int y) {
		if (x > 7 || y > 7 || y < 0 || x < 0) {
			return false;
		}
		return true;
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		Piece p = selectedPiece();
		if (p != null) {
			p.doneCapturing();
		}
		player = player ^ 1;
		hasMoved = false;
		resetSelected();
	}

	public String winner() {
		scanBoard();
		if (fPieces == 0 && wPieces == 0) {
			return "No one";
		}
		else if (fPieces == 0) {
			return "Water";
		}
		else if (wPieces == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}

	private Piece selectedPiece() {
		return pieceAt(getSelectedPieceX(), getSelectedPieceY());
	}

	private void changeSPieceCoord(int x, int y) {
		selectedPiecePos[0] = x;
		selectedPiecePos[1] = y;
	}

	private int getSelectedPieceX() {
		return selectedPiecePos[0];
	}

	private int getSelectedPieceY() {
		return selectedPiecePos[1];
	}

	private void resetSelected() {
		changeSPieceCoord(-1, -1);
	}

	private boolean hasSelected() {
		return getSelectedPieceX() != -1;
	}

	private void explosion(int x, int y) {
		x = x - 1;
		y = y - 1;
		Piece p;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				p = pieceAt(x + i, y + j);
				if (p != null && !p.isShield()) {
					remove(x + i, y + j);
				}
			}
		}
	}

	private boolean movingForward(boolean isFire, int yi, int yf) {
		if (isFire) {
			return yf > yi;
		}
		else {
			return yf < yi;
		}
	}

	private void scanBoard() {
		fPieces = 0;
		wPieces = 0;
		Piece p;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						fPieces++;
					}
					else {
						wPieces++;
					}
				}
			}
		}
	}

	private boolean capturingMove(Piece p, int xi, int xf, int yi, int yf) {
		int x = (xi + xf) / 2;
		int y = (yi + yf) / 2;
		if (Math.abs(xi - xf) != 2) {
			return false;
		}
		else if (p == null) {
			return false;
		}
		else if (pieceAt(x, y) == null || pieceAt(x, y).side() == player) {
			return false;
		}
		else {
			return true;
		}
	}
}
