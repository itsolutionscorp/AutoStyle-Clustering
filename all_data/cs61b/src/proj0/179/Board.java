public class Board {

	private Piece[][] pieces;

	private int currentPlayer = 0;

	private int currentX = -1;
	private int currentY = -1;
	private int currentPieceX = -1;
	private int currentPieceY = -1;

	private boolean pieceSelected = false;
	private boolean pieceMoved = false;

	private Piece currentPiece;

	public static void main(String args[]) {
		Board b = new Board(false);
		b.drawBoard(8);
		b.defaultBoard();

		while (true) {
			b.updateBoard();

			if (StdDrawPlus.mousePressed()) {			
				StdDrawPlus.show(30);
				double x = StdDrawPlus.mouseX();
            	double y = StdDrawPlus.mouseY();
            	b.currentX = (int)Math.round(x);
            	b.currentY = (int)Math.round(y);
            	
            	if (b.canSelect(b.currentX, b.currentY)) {
            		b.select(b.currentX, b.currentY);
            	}
            	StdDrawPlus.show(100);
			}

			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}	
			}

			if (b.winner() != null) {
				System.out.println("Winner: " + b.winner());
			}
					
			StdDrawPlus.show(100); 
			
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int x = 0; x <8; x = x + 2) {
				pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
				pieces[x][6] = new Piece(false, this, x, 6, "shield");

			}
			for (int x = 1; x <8; x = x + 2) {
				pieces[x][1] = new Piece(true, this, x, 1, "shield");
				pieces[x][5] = new Piece(false, this, x, 5, "bomb");
				pieces[x][7] = new Piece(false, this, x, 7, "pawn");
			}
		}
	}

	private void drawBoard(int N) {
        StdDrawPlus.setXscale(-.5, 7.5);
        StdDrawPlus.setYscale(-.5, 7.5);
      
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.PINK);
                }
                else {                  
                    StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                }
                StdDrawPlus.filledSquare(i , j, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
          }
      }
    }

	private void defaultBoard() {
		for (int x = 0; x <8; x = x + 2) {
			StdDrawPlus.picture(x, 0, "img/pawn-fire.png", 1, 1);
			StdDrawPlus.picture(x, 2, "img/bomb-fire.png", 1, 1);
			StdDrawPlus.picture(x, 6, "img/shield-water.png", 1, 1);
		}

		for (int x = 1; x <8; x = x + 2) {
			StdDrawPlus.picture(x, 1, "img/shield-fire.png", 1, 1);
			StdDrawPlus.picture(x, 5, "img/bomb-water.png", 1, 1);
			StdDrawPlus.picture(x, 7, "img/pawn-water.png", 1, 1);
		}
	}

	private void blankSquare(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.PINK);
		StdDrawPlus.filledSquare(x , y, 0.5);
	}

	private void updateBoard() {
		for (int x = 0; x < 8; x = x + 1) {
			for (int y = 0; y < 8; y = y + 1) {
				if (pieces[x][y] != null) {
					StdDrawPlus.setPenColor(StdDrawPlus.PINK);
					if (x == currentPieceX && y == currentPieceY) {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					}
					StdDrawPlus.filledSquare(x , y, 0.5);
					drawPiece(pieces[x][y], x, y);
				}

				else if (pieces[x][y] == null && ((x + y) % 2 == 0)) {
					blankSquare(x, y);
				}
			} 
		}		
	}

	private void drawPiece(Piece p, int x, int y) {
		if (p.isFire()) { //fire pieces
			if (p.isKing()) { //crowned pieces
				if (p.isBomb()) {
					StdDrawPlus.picture(x, y, "img/bomb-fire-crowned.png", 1, 1);
                }
                else if (p.isShield()) {
                	StdDrawPlus.picture(x, y, "img/shield-fire-crowned.png", 1, 1);
                }
                else {
                    StdDrawPlus.picture(x, y, "img/pawn-fire-crowned.png", 1, 1);
                }
			}

			else {
				if (p.isBomb()) {
					StdDrawPlus.picture(x, y, "img/bomb-fire.png", 1, 1);
                }
                else if (p.isShield()) {
                	StdDrawPlus.picture(x, y, "img/shield-fire.png", 1, 1);
                }
                else {
                	StdDrawPlus.picture(x, y, "img/pawn-fire.png", 1, 1);
                }				
			}
		}

		else { //water pieces
			if (p.isKing()) { //crowned pieces
				if (p.isBomb()) {
                	StdDrawPlus.picture(x, y, "img/bomb-water-crowned.png", 1, 1);
                }
                 else if (p.isShield()) {
                 	StdDrawPlus.picture(x, y, "img/shield-water-crowned.png", 1, 1);
                }
                else {
                	StdDrawPlus.picture(x, y, "img/pawn-water-crowned.png", 1, 1);
                }
			}

			else {
				if (p.isBomb()) {
                	StdDrawPlus.picture(x, y, "img/bomb-water.png", 1, 1);
                }
                else if (p.isShield()) {
                	StdDrawPlus.picture(x, y, "img/shield-water.png", 1, 1);
                }
                else {
                	StdDrawPlus.picture(x, y, "img/pawn-water.png", 1, 1);
                }
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}

	private boolean validMove (int xi, int yi, int xf, int yf) {
		boolean valid = false;

		int xd = Math.abs(xi - xf); //difference in positions
		int yd = Math.abs(yi - yf);

		if (canCapture(xi, yi, xf, yf)) {
			valid = true;
		}

		else if (xd == 1 && yd == 1 && //checking it's just regular move
			pieceAt(xf, yf) == null) { //checking that the final space is empty
			valid = true;
		}

		else {
			valid = false;
		}
		
		return rightDirection(xi, yi, xf, yf) && valid;
	}

	private boolean canCapture (int xi, int yi, int xf, int yf) {
		int xd = Math.abs(xi - xf); //difference in positions
		int yd = Math.abs(yi - yf);

		int xc = Math.abs((xi + xf) / 2); //x coordinate if capturing
		int yc = Math.abs((yi + yf) / 2); //y coordinate if capturing

		if (xd == 2 && yd == 2 &&  //capture 
			pieceAt(xc, yc) != null && //checking there is a piece to capture
			pieceAt(xc, yc).side() != currentPlayer && //check piece is opponent's
			pieceAt(xf, yf) == null) { //making sure final space is empty
			return true;
		}
		else {
			return false;
		}
	}

	private boolean rightDirection(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi).isKing()) {
			return true;
		}

		else if (pieceAt(xi, yi).side() == 0 && yf > yi) {
			return true;
		}
		
		else if (pieceAt(xi, yi).side() == 1 && yi > yf) {
			return true;
		}

		else {
			return false;
		}
	}
	
	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null && pieceAt(x, y).side() != currentPlayer) {
			//if the piece is the wrong side
			return false;
		}

		else if (pieceSelected && pieceMoved && pieceAt(currentPieceX, currentPieceY) == null) {
			return false;
		}

		else if (pieceSelected && pieceMoved && currentPiece.hasCaptured() 
			&& validMove(currentPieceX, currentPieceY, x, y) 
			&& canCapture(currentPieceX, currentPieceY, x, y)) {
			// Multicapture
			return true;
		}

		else if (pieceSelected && pieceMoved && currentPiece.hasCaptured() 
			&& validMove(currentPieceX, currentPieceY, x, y) 
			&& !canCapture(currentPieceX, currentPieceY, x, y)) {
			// Capture then trying to keep moving
			return false;
		}

		else if(pieceSelected && pieceMoved 
			&& (pieceAt(currentPieceX, currentPieceY) == null)) {
			return false;
			//if pieceselected is true and piece moved is true
		}

		else if (pieceAt(x, y) == null && !pieceSelected) { 
		//if it's an empty square and a piece has not been selected
			return false;
		}

		else if (pieceMoved && !currentPiece.hasCaptured()) {
			// has not captured
			return false;
		}

		else if (pieceAt(x, y) == null && pieceSelected 
				&& validMove(currentPieceX, currentPieceY, x, y)) {  
				// if it's an empty square and a piece has been selected
				//and it can move to that empty square
			return true;
		}
		
		else if (pieceAt(x, y) == null && pieceSelected 			
				&& !validMove(currentPieceX, currentPieceY, x, y)) {  
				//if it's an empty square and a piece has been selected
				//and it can't move to that empty square
			return false;
		}

		else if (pieceAt(x, y) != null && !pieceSelected) { //selecting a piece
			return true;
		}

		else if (pieceSelected && !pieceMoved && pieceAt(x, y) != null
			&& pieceAt(x, y).side() == currentPlayer) { 
			//piece hasn't been selected or piece has been selected 
			// but hasn't been moved. ie changing piece
			return true;
		}

		else {
			return false;
		}
	}

	public void select(int x, int y) {

		if (pieceAt(x, y) == null && pieceSelected) { //moving a selected to an empty square
			currentPiece.move(x, y);
			currentPieceX = x;
			currentPieceY = y;
			pieceMoved = true;
		}

		else if (pieceAt(x, y) != null) { //selecting a piece
			pieceSelected = true;
			currentPiece = pieceAt(x, y);
			currentPieceX = x;
			currentPieceY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (pieceAt(x, y) == null) { //moving a piece to empty place
			pieces[x][y] = p;
		}

		else if (x > 7 || y > 7 || y < 0 || x < 0) {
			return;
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else if (pieceAt(x, y) == null) {
			return null;
		}
		else {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn() {
		return pieceMoved;
	}

	public void endTurn() {
		currentPiece.doneCapturing();
		currentPlayer = 1 - currentPlayer;
		pieceMoved = false;
		currentPiece = null;
		pieceSelected = false;
		currentPieceX = -1;
		currentPieceY = -1;

	}
	public String winner() {
		int fire = 0;
		int water = 0;

		for (int x = 0; x < 8; x = x + 1) {
			for (int y = 0; y < 8; y = y + 1) {
				if (pieces[x][y] != null && pieces[x][y].side() == 0) {
					fire ++;
				}

				else if (pieces[x][y] != null && pieces[x][y].side() == 1) {
					water ++;
				}
			} 
		}

		if (fire > water && water == 0) { //fire wins
			return "Fire";
		}

		else if (fire < water && fire == 0) { //water wins
			return "Water";
		}

		else if (fire > 0 && water > 0) { //both still have pieces left
			return null; 
		}

		else if (fire == water) { //empty board
			return "No one";
		}

		else {
			return null;
		}
		
	}
}