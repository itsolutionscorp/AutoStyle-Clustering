
public class Board{

	private static Piece[][] pieces;
	private boolean isFireTurn= true; 
	private boolean isWaterTurn= false;
	private boolean pieceSelected= false; /*Checks if a piece is already selected or not; will remain true until turn is over*/
	private boolean pieceMoved= false; /*Checks if a piece is already moved or not; will remain true until turn is over*/
	private Piece selectedPiece; /*Piece that is selected and moved around*/
	private int selectedPieceX;
	private int selectedPieceY;
	private int highlightX = 8;
    private int highlightY = -8;

	public Piece pieceAt(int x, int y) {
		if ((0 <= x) && (x < 8) && (0 <= y) && (y < 8)) {
			return pieces[x][y];
		}
		return null;
	}

	public void place(Piece p, int x, int y) {
		if ((0 <= x) && (x < 8) && (0 <= y) && (y < 8)) {
			pieces[x][y]= p;
		}
	}

	/*making sure king can go both ways and normal pieces only one direction*/
	private boolean validMove (int xi, int yi, int xf, int yf) { 
		Piece movingPiece= pieceAt(xi, yi);
		if ((movingPiece!= null) && (0 <= xi) && (xi < 8) && (0 <= yi) && (yi < 8) && (0 <= xf) && (xf < 8) && (0 <= yf) && (yf < 8)){
			if (movingPiece.isKing() == true) {
				if (validMovement(xi, yi, xf, yf) == true) {
					return true;
				}
			}
			else {
				if (((movingPiece.isFire() == true) && ((yf - yi) > 0)) || ((movingPiece.isFire() == false) && ((yi - yf) > 0))) {
					if (validMovement(xi, yi, xf, yf) == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/* Helper for validMove
	* Make sure moves are either 2-2 capture moves over another piece or 1-1 simple moves */
	private boolean validMovement (int xi, int yi, int xf, int yf) {
		if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
			int capturedPieceX= (xi + xf)/ 2;
			int capturedPieceY= (yi + yf)/ 2;
			if ((pieceAt(capturedPieceX, capturedPieceY) != null) && (captureHelper(xi, yi, capturedPieceX, capturedPieceY) == true)) {
				return true;
			}
		}
		else if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {
			if (pieceAt(xf, yf) == null) {
				return true;
			}
		}
		return false;
	}

	/*Helper for validMovement to make sure capture is of opponents*/
	private boolean captureHelper (int xi, int yi, int xf, int yf) {
		if ((pieceAt(xi, yi).isFire() == true) && (pieceAt(xf, yf).isFire() == false)) {
			return true;
		}
		if ((pieceAt(xi, yi).isFire() == false) && (pieceAt(xf, yf).isFire() == true)) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public boolean canSelect (int x, int y) { 
		if (pieceAt(x, y) != null) { 
			/*Case for selecting a piece*/
			if (((pieceAt(x, y).isFire() == true) && (isFireTurn == true)) || ((pieceAt(x, y).isFire() == false) && (isWaterTurn == true))) {
				/*making sure its your turn*/
				if ((pieceSelected == false) || (pieceMoved == false)) {
					/*Haven't selected or haven't moved*/
					return true;
				}
			}
		}
		if ((pieceAt(x, y) == null) && (pieceSelected == true)) { 
			/*Case for selecting a space*/
			if (((selectedPiece.isFire() == true) && (isFireTurn == true))|| ((selectedPiece.isFire() == false) && (isWaterTurn == true))) {
				/*Making sure its your turn*/
				if (pieceMoved == false) {
					/*Haven't moved a piece but moving now*/
					if (validMove(selectedPieceX, selectedPieceY, x, y)) {
						return true;
					}
				}
				if (selectedPiece.hasCaptured() == true) {
					/*Captured a piece and capturing another*/
					if ((validMove(selectedPieceX, selectedPieceY, x, y)) && ((Math.abs(selectedPieceY - y) == 2) && (Math.abs(selectedPieceX - x) == 2))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y) {
		highlightX = x;
        highlightY = y;
		if (pieceAt(x, y) != null) {
			/*Case where an existing piece is selected*/
			selectedPiece= pieceAt(x, y);
			selectedPieceX= x;
			selectedPieceY= y;
			pieceSelected= true;
		}
		else {
			/*Case where an empty square is selected*/
			selectedPiece.move(x, y);
			pieceMoved= true;
			if (selectedPiece.isBomb()) {
				selectedPieceX= x;
				selectedPieceY= y;
			}
		}
	}

	public Piece remove(int x, int y) {
		if ((8 <= x) && (x < 0) && (8 <= y) && (y < 0)) {
			System.out.println("Out of Bounds");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece here");
			return null;
		}
		else{
			Piece removedPiece= pieces[x][y];
			pieces[x][y]= null;
			return removedPiece;
		}
	}

	public boolean canEndTurn() {
		if (pieceMoved == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		pieceSelected= false;
		pieceMoved= false;
		selectedPiece.doneCapturing();
		highlightX = 8;
        highlightY = 8;
		if (selectedPiece.isFire() == true) {
			isFireTurn = false;
			isWaterTurn = true;
		}
		else {
			isFireTurn = true;
			isWaterTurn = false;
		}
	}

	public String winner() {
		/*Check if there are existing pieces on the board*/
		boolean fireLost = true;
		boolean waterLost = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire() == true) {
						fireLost = false;
					} else {
						waterLost = false;
					}
				}
			}
		}
		if ((waterLost == true) && (fireLost == true)) {
			return "No one";
		} 
		else if ((fireLost == false) && (waterLost == false)) {
			return null;
		}
		else if (waterLost == false) {
			return "Water";
		}
		else {
			return "Fire";
		}
	}

	public Board (boolean shouldBeEmpty) {
		pieces= new Piece[8][8];
		if (shouldBeEmpty== false) {
			getPieces();
		} 
	}

	private void getPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i % 2) == 1) {
					if (j == 1) {
						pieces[i][j]= new Piece(true, this, i, j, "shield");
					}
					if (j == 5) {
						pieces[i][j]= new Piece(false, this, i, j, "bomb");
					}
					if (j == 7) {
						pieces[i][j]= new Piece(false, this, i, j, "pawn");
					}
				}
				if ((i % 2) == 0) {
					if (j == 0) {
						pieces[i][j]= new Piece(true, this, i, j, "pawn");
					}
					if (j == 2) {
						pieces[i][j]= new Piece(true, this, i, j, "bomb");
					}
					if (j == 6) {
						pieces[i][j]= new Piece(false, this, i, j, "shield");
					}
				}
			}
		}
	}

	private static void drawBoard(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if ((board.highlightX ==i) && (board.highlightY == j)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } 
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
	                if (pieces[i][j].isFire()) {
	                	if (pieces[i][j].isShield()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		} else {
	      						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	      					}
	                	}
	                	else if (pieces[i][j].isBomb()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                	}
	                	else {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
	                	}
	                }
	                else {
	                	if (pieces[i][j].isShield()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		} else {
	      						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	      					}
	                	}
	                	else if (pieces[i][j].isBomb()) {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                	}
	                	else {
	                		if (pieces[i][j].isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		} else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
	                	}
	                }
                }
            }
        }
    }

    public static void main (String args[]) {
		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);
		Board board= new Board(false);
		while (true) {
			drawBoard(board);
			if (StdDrawPlus.mousePressed()) {
                double xDouble = StdDrawPlus.mouseX();
                double yDouble = StdDrawPlus.mouseY();
                int x = (int) xDouble;
                int y = (int) yDouble;
                if (board.canSelect(x, y)){
                    board.select(x, y);
                }
            }      

            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }

            if (StdDrawPlus.isNPressed()) {
            	board= new Board(false);
            }

            if (board.winner() != null){
                System.out.println(board.winner());
                return;
            }
            StdDrawPlus.show(10);
		}
	}
}
