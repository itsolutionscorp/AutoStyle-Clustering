public class Board {

	private Piece[][] pieces;
	private int xSelect, ySelect;
	private Piece selectedPiece;
	private boolean moved = false;
	private boolean fireTurn = true;

	public Board(boolean shouldBeEmpty) {
		/**Need to initiate pieces
		 *draws board but doesn't call show method or Activate
		 *does "activate" board to handle any user response 
		 */ 
		pieces = new Piece[8][8];
		if (shouldBeEmpty) {
		} else {
			int i, j;
			for(i = 0, j = 0; i < 8; i += 2) { //fire normal
				pieces[i][j] = new Piece(true, this, i, j, "pawn");
			}
			for(i = 1, j = 1; i < 8; i+= 2) { //fire shield
				pieces[i][j] = new Piece(true, this, i, j, "shield");
			}
			for(i = 0, j = 2; i < 8; i+= 2) { //fire bomb
				pieces[i][j] = new Piece(true, this, i, j, "bomb");
			}

			for(i = 1, j = 7; i < 8; i += 2) { //water normal
				pieces[i][j] = new Piece(false, this, i, j, "pawn");
			}
			for(i = 0, j = 6; i < 8; i+= 2) { //water shield
				pieces[i][j] = new Piece(false, this, i, j, "shield");
			}
			for(i = 1, j = 5; i < 8; i+= 2) { //fire bomb
				pieces[i][j] = new Piece(false, this, i, j, "bomb");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x < 0) || (y < 0) || (x >= 8) || (y  >= 8)) {
			return null;
		}
		return pieces[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if ((xf < 0) || (yf < 0) || (xf >= 8) || (yf  >= 8)) { //checks if trying to move outside of board
			return false;
		}
		Piece p = pieces[xi][yi];
		if (p.isFire()) { //fire pieces
			if (p.isKing()) {  //fire king
				if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {  //fire king tryin to move to adjacent
					return (pieces[xf][yf] == null);
				} else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) { //fire
					Piece middlePiece = pieces[(xi + xf) / 2][(yi + yf) / 2];
					return ((middlePiece != null) && !middlePiece.isFire());  //checks if the middle peice is there and water						
				} else {
					return false;
				}
			} else { // fire non-king
				if ((Math.abs(xi - xf) == 1) && ((yf - yi) == 1)) {
					return (pieces[xf][yf] == null);
				} else if ((Math.abs(xi - xf) == 2) && ((yf - yi) == 2)) {
					Piece middlePiece = pieces[(xi + xf) / 2][(yi + yf) / 2];
					return ((middlePiece != null) && !middlePiece.isFire());
				} else {
					return false;
				}
			}
		} else { //water pieces
			if (p.isKing()) {  //water king
				if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {  //water king tryin to move to adjacent
					return (pieces[xf][yf] == null);
				} else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) { //water king capturing
					Piece middlePiece = pieces[(xi + xf) / 2][(yi + yf) / 2];
					return ((middlePiece != null) && middlePiece.isFire());  //checks if the middle peice is fire					
				} else {
					return false;
				}
			} else { // water non-king
				if ((Math.abs(xi - xf) == 1) && ((yi - yf) == 1)) {
					return (pieces[xf][yf] == null);
				} else if ((Math.abs(xi - xf) == 2) && ((yi - yf) == 2)) {
					Piece middlePiece = pieces[(xi + xf) / 2][(yi + yf) / 2];
					return ((middlePiece != null) && middlePiece.isFire());
				} else {
					return false;
				}
			}
		}
	}

	public boolean canSelect(int x, int y){
		
		if (!moved) {  //temporarily no jumping allowed, after a move, its over
			if (fireTurn) {
				if (selectedPiece == null) { //trying to selecting a new piece
					return ((pieces[x][y] != null) && (pieces[x][y].isFire()));
				} else {  //tyring to move a piece
					return (validMove(xSelect, ySelect, x, y) || ((pieces[x][y] != null) && (pieces[x][y].isFire())));
				}
			} else {  
				if (selectedPiece == null) {
					return ((pieces[x][y] != null) && (!pieces[x][y].isFire()));
				} else {
					return (validMove(xSelect, ySelect, x, y) || ((pieces[x][y] != null) && (!pieces[x][y].isFire())));
				}
			}
		} else {
			if ((selectedPiece != null) && (selectedPiece.hasCaptured())) {  //check if it was bomb/null (after it explodes) and if it captured (and not just moved)
				return (validMove(xSelect, ySelect, x, y) && (Math.abs(xSelect - x) == 2) && (Math.abs(ySelect- y) == 2));  //prevents regular moves after capturing
			}
			return false;
		}

	}

	public void select (int x, int y){
		//TODO
		if (selectedPiece == null) {
			selectedPiece = pieces[x][y];
		} else {
			selectedPiece.move(x, y);
			if (selectedPiece.hasCaptured() && selectedPiece.isBomb()) {
				selectedPiece = null;
			}
			moved = true;
		}
		xSelect = x;//temporary selecting new piece or moving piece should change xSelect, and ySelect
		ySelect = y;
	}

	public void place(Piece p, int x, int y) {
	/*	selectedPiece = p;*/
		pieces[x][y] = p;
		xSelect = x;
		ySelect = y;
	}

	public Piece remove (int x, int y) {
/*		if ((xSelect == x) && (ySelect == y))
			selectedPiece = null;*/
		Piece p = pieceAt(x, y);
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		//TODO
		return moved;
	}

	public void endTurn() {
		//TODO hi
		fireTurn = !fireTurn;
		moved = false;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;

	}

	public String winner() {
		//TODO
		boolean hasFire = false;
		boolean hasWater = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = pieces[i][j];
				if (p != null) {
					if (p.isFire())
						hasFire = true;
					else
						hasWater = true;
				}
			}
		}
		if (!hasFire && hasWater)
			return "Water";
		if (hasFire && !hasWater)
			return "Fire";
		if (!hasFire && !hasWater)
			return "No one";
		return null;
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = pieces[i][j];
                if (p != null ) {
                	if(p.isFire()) {

                		if (p.isBomb()) {

                			if (p.isKing())
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			else
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);

                		} else if (p.isShield()){

                			if (p.isKing())
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			else
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                		} else {

                			if (p.isKing())
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			else
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
            		} else {          
                	
                		if (p.isBomb()) {

                			if (p.isKing())
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			else
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);

                		} else if (p.isShield()){

                			if (p.isKing())
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			else
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		} else {

                			if (p.isKing())
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			else
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}

                	}

                }
					
            }
        }
    }

	public static void main(String args[]) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        //from StDDraw, not completed
        while(true) {
            b.drawBoard(8);
	        if (StdDrawPlus.mousePressed()) {
	            int x = (int) StdDrawPlus.mouseX();
	            int y = (int) StdDrawPlus.mouseY();
	           	if (b.canSelect(x, y)) {
	            	b.select(x, y);
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



}