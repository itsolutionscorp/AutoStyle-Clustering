import org.junit.Test;
import static org.junit.Assert.*;

public class Board {
	private Piece[][] playingBoard;
	private String turn;
	private Piece pieceSelected;
	private int pieceSelectedX;
	private int pieceSelectedY;
	private boolean madeMove;
	private boolean recentExplosion;




// ****** NECESSITIES *****

	public Board(boolean shouldBeEmpty) {
		playingBoard = new Piece[8][8];
		turn = "Fire";
		if (shouldBeEmpty == false) {
			this.setUpPlayingBoard();
		}
	}
	public Piece pieceAt(int x, int y) {
		if (x <= 7 && x >= 0 && y <= 7 && y >=0) {
			if (playingBoard[7 - y][x] != null) {
				return playingBoard[7 - y][x];
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
		private void setUpPlayingBoard() {
		for (int row = 0; row <= 7; row += 1) {
            for (int col = 0; col <= 7; col += 1) {
            	if ((row == 7) && (col % 2 == 0)) {
            		playingBoard[row][col] = new Piece(true, this, col, 7 - row, "pawn");
            	}
            	if ((row == 6) && (col % 2 == 1)) {
            		playingBoard[row][col] = new Piece(true, this, col, 7 - row, "shield");
            	}
            	if ((row == 5) && (col % 2 == 0)) {
            		playingBoard[row][col] = new Piece(true, this, col, 7 - row, "bomb");
            	}
            	if ((row == 2) && (col % 2 == 1)) {
            		playingBoard[row][col] = new Piece(false, this, col, 7 - row, "bomb");
            	}
            	if ((row == 1) && (col % 2 == 0)) {
            		playingBoard[row][col] = new Piece(false, this, col, 7-row, "shield");
            	}
            	if ((row == 0) && (col % 2 == 1)) {
            		playingBoard[row][col] = new Piece(false, this, col, 7-row, "pawn");
            	}
            }
        }
    }





// **** Basic game progression (turns & winner) ****

	public boolean canEndTurn() {
		if (madeMove) {
			return true;
		}
		else {
			return false;
		}
	}
	public void endTurn() {
		madeMove = false;
		pieceSelected.doneCapturing();
		pieceSelected = null;
		pieceSelectedY = 0;
		pieceSelectedY = 0;
		if (this.turn == "Fire") {
			this.turn = "Water";
		}
		else {
			this.turn = "Fire";
		}
	}
	public String winner() {
		int numWaterPieces = 0;
		int numFirePieces = 0;
		for (int row = 0; row <= 7; row += 1) {
            for (int col = 0; col <= 7; col += 1) {
            	if (playingBoard[row][col] != null) {
            		Piece curr = playingBoard[row][col];
            		if (curr.isFire()) {
            			numFirePieces += 1;
            		}
            		if (curr.isFire() == false) {
            			numWaterPieces += 1;	
            		}
            	}
            }
        }
		if ((numFirePieces == 0) && (numWaterPieces == 0)) {
			return "No one";
		}
		if (numFirePieces <= 0) {
			return "Water";
		}
		if (numWaterPieces <= 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}




// ***** This module takes care of main and StdDrawPlus Operations *****

	public static void main(String[] args) {
		Board newB = new Board(false);
		/* draw pieces is going to have to go somewhere else
		 * due to redrawing of peices after turns */
		//newB.drawGui();
		newB.draw();
	}
	private void draw() {
		while(true) {
			this.drawGui();
			if (StdDrawPlus.mousePressed()) {
				int xMouse = ((int) StdDrawPlus.mouseX());
				int yMouse = ((int) StdDrawPlus.mouseY());
				if (this.canSelect(xMouse, yMouse)) {
					this.select(xMouse, yMouse);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (this.canEndTurn()) {
					this.winner();
					this.endTurn();
				}
			}
			StdDrawPlus.show(50);
		}
	}
	private void drawGui() {
    	drawBoard(7);
    	drawPieces();
    }
    private void drawPieces() {
    	String typeToDraw;
    	Piece curr;
		for (int row = 0; row <= 7; row += 1) {
            for (int col = 0; col <= 7; col += 1) {
            	if (playingBoard[row][col] != null) {
            		curr = playingBoard[row][col];
            		typeToDraw = findtype(curr);
            		StdDrawPlus.picture(col + .5, 7-row + .5, typeToDraw, 1, 1);
            	}
           	}
        }
    }
	

	private String findtype(Piece p) {
		if (p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-fire-crowned.png";
				}
				if (p.isShield()) {
					return "img/shield-fire-crowned.png";
				}
				else {
					return "img/pawn-fire-crowned.png";
				}
			}
			else {
				if (p.isBomb()) {
					return "img/bomb-fire.png";
				}
				if (p.isShield()) {
					return "img/shield-fire.png";
				}
				else {
					return "img/pawn-fire.png";
				}
			}
		}
		else {
			if (p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-water-crowned.png";
				}
				if (p.isShield()) {
					return "img/shield-water-crowned.png";
				}
				else {
					return "img/pawn-water-crowned.png";
				}
			}
			else {
				if (p.isBomb()) {
					return "img/bomb-water.png";
				}
				if (p.isShield()) {
					return "img/shield-water.png";
				}
				else {
					return "img/pawn-water.png";
				}
			}
		}
	}
	private void drawBoard(int size) {
		StdDrawPlus.setScale(0, 8);
		for (int x = 0; x <= size; x += 1) {
            for (int y = 0; y <= size; y += 1) {
            	if ((x + y) % 2 == 0) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	}
            	else {
            		StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
            	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
        }
    }




// ***** This Module selects squares and pieces, and then makes sure those are valid selects ****

    /* dispatches to canSelectPiece if there is a Piece as x, y
     * dispatches to can selectSquare if a Piece is already selected */
	public boolean canSelect(int x, int y) {
		if (x <= 7 && x >= 0 && y <= 7 && y >=0) {
			if (pieceSelected != null) {
				if ((pieceSelected.isBomb()) && (pieceSelected.hasCaptured())) {
					return false;
				}
			}
			if (pieceAt(x, y) != null) {
				return canSelectPiece(x, y);
			}
			if (pieceSelected != null) {
				return canSelectSquare(x, y);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	private boolean canSelectSquare(int x, int y) {
		if (madeMove) {
			if (pieceSelected.hasCaptured()) {
				return validCapture(x, y);
			}
			else {
				return false;
			}
		}
		if (pieceSelected.isKing()) {
			return validKingMove(x, y);
		}
		return validMove(x, y);
	}
	private boolean canSelectPiece(int x, int y) {
		if (madeMove) {
			return false;
		}
		Piece localPiece = pieceAt(x, y);
		if (turn == "Fire") {
			if (localPiece.isFire()) {
				return true;
			}
		}
		if (turn == "Water") {
			if (localPiece.isFire() == false) {
				return true;
			}
		}
		return false;
	}
	/* return whether a move to x, y is valid for pieceSelected 
     * if piece is king dispatches to validKing Move
     * if move is aready made or capture is attempted, dispatches to valid capture*/
	private boolean validMove(int x, int y) {
		if((Math.abs(x - pieceSelectedX) == 2) && (Math.abs(y - pieceSelectedY) == 2)) {
			return validCapture(x, y);
			}
		else {
			if (pieceSelected.isFire()) {
				if (((y - pieceSelectedY) == 1) && (Math.abs(x - pieceSelectedX) == 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if (((y - pieceSelectedY) == -1) && (Math.abs(x - pieceSelectedX) == 1)) {
					return true;
				}
				else {
					return false;
					}
			}
		}
	}
	/* return true if peiceSelected would capture by moving to x, y*/
	private boolean validCapture(int x, int y) {
		int indicatorX = (x - pieceSelectedX);
		int indicatorY = (y - pieceSelectedY);
		int positionCaptureX;
		int positionCaptureY;
		if (pieceSelected.isKing()) {
			return validKingCapture(x, y, indicatorX, indicatorY);
		}
		/*set to water*/
		positionCaptureY = (-1 + pieceSelectedY);
		//unless Fire
		if (pieceSelected.isFire()) {
			positionCaptureY = (1 + pieceSelectedY);
		}
		positionCaptureX = (indicatorX / 2 + pieceSelectedX);
		if (pieceAt(positionCaptureX, positionCaptureY) != null) {
			if ((pieceAt(positionCaptureX, positionCaptureY).isFire() == false) && (pieceSelected.isFire())) {
					return true;
				}
				if ((pieceAt(positionCaptureX, positionCaptureY).isFire()) &&
					(pieceSelected.isFire() == false)) {
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
	    /* checks wheter a king move capture by moving to x, y
	     * dispatches to validKingcapture if move is already made and nother cab be
	     * or if a capture is being attempted */
	private boolean validKingMove(int x, int y) {
		if (Math.abs(x - pieceSelectedX) == 2) {
			return validCapture(x, y);
		}
		if (((Math.abs(x - pieceSelectedX)) == 1) && ((Math.abs(y - pieceSelectedY)) == 1)) {
			return true;
		}
		else {
			return false;
		}
	}

	/*checks whether a king can capture by moving to x, y */
	private boolean validKingCapture(int x, int y, int indicatorX, int indicatorY) {
		int positionCaptureX;
		int positionCaptureY;
		positionCaptureX = ((indicatorX / 2) + pieceSelectedX);
		positionCaptureY = ((indicatorY / 2) + pieceSelectedY);
		if (pieceAt(positionCaptureX, positionCaptureY) != null) {
				if ((pieceAt(positionCaptureX, positionCaptureY).isFire()) &&
					(pieceSelected.isFire() == false)) {
						return true;
				}
				if ((pieceAt(positionCaptureX, positionCaptureY).isFire() == false) &&
					(pieceSelected.isFire())) {
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




// **** Selections, movements and special movements: make king & Bomb (explosion at bottom due to length) *****
	
	public void select(int x, int y) {
		if ((pieceSelected == null) || ((madeMove == false) && (pieceAt(x, y) != null))) {
				pieceSelected = pieceAt(x, y);
				pieceSelectedX = x;
				pieceSelectedY = y;
		}
		else {
			madeMove = true;
			pieceSelectedX = x;
			pieceSelectedY = y;
			pieceSelected.move(pieceSelectedX, pieceSelectedY);				
		}
	}

	public void place(Piece p, int x, int y) {
		if (p != null && x <= 7 && x >= 0 && y <= 7 && y >=0) {
			playingBoard[7 - y][x] = p;
		}
	}
	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("Piece (" + x + ", " + y + ") out of bounds");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece at " + x + ", " + y);
			return null;
		}
		else{
			Piece toBail = playingBoard[7 - y][x];
			playingBoard[7 - y][x] = null;
			return toBail;
		}
	}
	private static class Boardtester { 
       @Test
        public void testBomb() {
            Board tester = new Board(true);
			tester.playingBoard[4][3] = new Piece(true, tester, 3, 3, "img/pawn-fire.png");
			tester.playingBoard[6][3] = new Piece(false, tester, 3, 1, "img/pawn-fire.png");
			Piece b = new Piece(true, tester, 2, 4, "img/bomb-water.png");
			tester.playingBoard[3][2] = b;
			tester.playingBoard[6][5] = new Piece(true, tester, 5, 1, "img/shield-fire.png");
			tester.select(2,4);
			assertEquals(b, tester.pieceSelected);
			assertEquals(tester.pieceSelectedX, 2);
			assertEquals(tester.pieceSelectedY, 4);
			tester.select(4,2);
			assertEquals(null, tester.pieceAt(2,4));
        }

        @Test
        public void testBombSimple() {
        	Board tester = new Board(true);
        	tester.playingBoard[4][3] = new Piece(true, tester, 3, 3, "img/bomb-fire.png");
        	tester.playingBoard[3][4] = new Piece(false, tester, 4, 4, "img/shield-water.png");
        	tester.playingBoard[1][6] = new Piece(false, tester, 6, 6, "img/pawn-water.png");
        	assertEquals(true, tester.pieceAt(3,3).isBomb());
        	assertEquals(true, tester.pieceAt(4,4).isShield());
        	assertEquals(false, tester.pieceAt(6,6).isFire());
        	tester.turn = "Fire";
        	if (tester.canSelect(3,3)) {
        		tester.select(3,3);
        	}
        	assertEquals(true, tester.pieceSelected.isFire());
        	if (tester.canSelect(5,5)) {
        		tester.select(5,5);
        	}
        	assertEquals(null, tester.pieceAt(4,4));
        	assertEquals(null, tester.pieceAt(6,6));


        }
        
        @Test
        public void testWinnerStale() {
        	Board tester = new Board(true);
        	tester.playingBoard[4][3] = new Piece(true, tester, 3, 3, "img/pawn-fire.png");
        	Piece b = new Piece(false, tester, 2, 4, "img/bomb-water.png");
			tester.playingBoard[3][2] = b;
			tester.select(2,4);
			tester.select(4,2);
			assertEquals("No one", tester.winner());
        }
        @Test
        public void testWinnerbeg() {
        	Board tester = new Board(true);
        	assertEquals("No one", tester.winner());
        	Piece newpawn = new Piece(true, tester, 3, 5, "img/pawn-fire.png");
        	Piece newpawn2 = new Piece(false, tester, 2, 6, "img/pawn-water.png");
        	tester.place(newpawn, 3, 5);
        	tester.place(newpawn2, 2, 6);
        	assertEquals(null, tester.winner());
        	tester.select(3,5);
        	tester.select(1,7);
        	assertEquals("Fire", tester.winner());
        	tester.endTurn();

        }
        @Test
        public void anotherWinner() {
        	Board tester = new Board(true);
        	tester.place(new Piece(true, tester, 1, 1, "img/pawn-fire.png"), 1,1);
        	tester.place(new Piece(false, tester, 3, 3, "img/pawn-water.png"), 3, 3);
        	tester.select(1,1);
        	tester.select(2,2);
        	tester.endTurn();
        	tester.select(3,3);
        	tester.select(1,1);
        	assertEquals("Water", tester.winner());
        }
        @Test
        public void suicide() {
        	Board tester = new Board(true);
        	tester.place(new Piece(true, tester, 2, 2, "img/bomb-fire.png"), 2, 2);
        	tester.place(new Piece(false, tester, 3, 3, "img/bomb-water.png"), 3, 3);
        	tester.select(3,3);
        	tester.select(1,1);
        	System.out.println(tester.winner());
        	assertEquals("No one", tester.winner());
        }
        @Test
         public void testWinnerPieces() {
        	Board tester = new Board(false);
        	assertEquals(null, tester.winner());

        }
        @Test
        public void testWinnerFire() {
            Board tester = new Board(true);
        	tester.playingBoard[4][3] = new Piece(true, tester, 3, 3, "img/bomb-fire.png");
        	tester.playingBoard[3][4] = new Piece(false, tester, 4, 4, "img/shield-water.png");
        	tester.playingBoard[1][6] = new Piece(false, tester, 6, 6, "img/pawn-water.png");
        	tester.playingBoard[7][7] = new Piece(true, tester, 6, 6, "img/pawn-fire.png");
        	assertEquals(true, tester.pieceAt(3,3).isBomb());
        	assertEquals(true, tester.pieceAt(4,4).isShield());
        	assertEquals(false, tester.pieceAt(6,6).isFire());
        	tester.turn = "Fire";
        	if (tester.canSelect(3,3)) {
        		tester.select(3,3);
        	}
        	assertEquals(true, tester.pieceSelected.isFire());
        	if (tester.canSelect(5,5)) {
        		tester.select(5,5);
        	}
        	assertEquals(null, tester.pieceAt(4,4));
        	assertEquals(null, tester.pieceAt(6,6));
        	assertEquals("Fire", tester.winner());
        }

        @Test
        public void testWaterWin() {
        	Board tester = new Board(true);
        	tester.playingBoard[4][3] = new Piece(true, tester, 3, 3, "img/pawn-fire.png");
        	tester.playingBoard[3][4] = new Piece(false, tester, 4, 4, "img/pawn-water.png");
        	tester.playingBoard[1][6] = new Piece(false, tester, 6, 6, "img/pawn-water.png");
        	tester.turn = "Water";
        	if (tester.canSelect(4,4)) {
        		tester.select(4,4);
        	}
        	assertEquals(false, tester.pieceSelected.isFire());
        	if (tester.canSelect(2,2)) {
        		tester.select(2,2);
        	}
        	assertEquals(null, tester.pieceAt(3,3));
        	assertEquals("Water", tester.winner());

        }

        @Test 
        public void testMakeKing() {
        	Board tester = new Board(true);
        	Piece p = new Piece(true, tester, 2, 6, "img/pawn-fire.png");
        	tester.playingBoard[1][2] = p;
        	tester.select(2,6);
        	tester.select(1,7);
        	assertEquals(true, p.isKing());
        }

        @Test 
        public void testPlaceNRemove() {
        	Board tester = new Board(true);
        	Piece p = new Piece(true, tester, 4, 4, "img/pawn-fire.png");
        	tester.place(p, 4, 4);
        	assertEquals(p, tester.pieceAt(4,4));
        	assertEquals(p, tester.remove(4,4));
        	assertEquals(null, tester.pieceAt(4,4));
        }

        @Test
        public void testKingWater() {
        	Board tester = new Board(true);
        	Piece p = new Piece(false, tester, 1, 1, "img/pawn-water.png");
        	tester.place(p, 1, 1);
        	tester.turn = "Water";
        	if (tester.canSelect(1,1)) {
        		tester.select(1,1);
        	}
        	assertEquals(p, tester.pieceAt(1,1));
        	if (tester.canSelect(2, 0)) {
        		tester.select(2, 0);
        	}
        	assertEquals(null, tester.pieceAt(1,1));
        	assertEquals(p, tester.pieceAt(2,0));
        	assertEquals(true, p.isKing());
        	assertEquals(true, tester.pieceAt(2,0).isKing());
        }
        @Test
        public void testkindNMove() {
        	Board tester = new Board(true);
        	Piece p = new Piece(false, tester, 1, 1, "img/pawn-water.png");
        	tester.place(p, 1, 1);
        	Piece t = new Piece(true, tester, 3, 1, "img/pawn-fire.png");
        	tester.place(t, 3, 1);
        	tester.turn = "Water";
        	if (tester.canSelect(1,1)) {
        		tester.select(1,1);
        	}
        	assertEquals(p, tester.pieceAt(1,1));
        	if (tester.canSelect(2, 0)) {
        		tester.select(2, 0);
        	}
        	assertEquals(null, tester.pieceAt(1,1));
        	assertEquals(p, tester.pieceAt(2,0));
        	assertEquals(true, p.isKing());
        	assertEquals(true, tester.pieceAt(2,0).isKing());
        	assertEquals(false, tester.canSelect(3,2));
        }
        @Test 
        public void testCrowning() {
        	Board tester = new Board(true);
        	Piece p = new Piece(true, tester, 0, 6, "img/pawn-fire-crowned.png");
        	tester.place(p, 0, 6);
        	tester.select(0,6);
        	assertEquals(p, tester.pieceSelected);
        	tester.select(1,7);
        	assertEquals(p, tester.pieceAt(1,7));
        	assertEquals(true, p.isKing());
        	assertEquals(true, tester.pieceAt(1,7).isKing());
        }

        @Test
        public void afterMath() {
        	Board tester = new Board(true);
        	tester.playingBoard[4][3] = new Piece(true, tester, 3, 3, "img/bomb-fire.png");
        	tester.playingBoard[3][4] = new Piece(false, tester, 4, 4, "img/shield-water.png");
        	tester.playingBoard[1][6] = new Piece(false, tester, 6, 6, "img/pawn-water.png");
        	tester.playingBoard[7][7] = new Piece(true, tester, 7, 0, "img/pawn-fire.png");
        	tester.turn = "Fire";
        	if (tester.canSelect(3,3)) {
        		tester.select(3,3);
        	}
        	assertEquals(true, tester.pieceSelected.isFire());
        	if (tester.canSelect(5,5)) {
        		tester.select(5,5);
        	}
        	assertEquals(false, tester.canSelect(7,0));

        }
       

        @Test 
        public void testBombShield() {
        	Board tester = new Board(true);
        	Piece a = new Piece(false, tester, 6, 6, "img/bomb-water.png");
        	Piece b = new Piece(true, tester, 3, 3, "img/shield-fire.png");
        	Piece c = new Piece(true, tester, 5, 5, "img/pawn-fire.png");
        	tester.place(a, 6 ,6);
        	tester.place(b, 3 ,3);
        	tester.place(c, 5 ,5);
        	tester.select(6,6);
        	tester.select(4,4);
        	assertEquals(null, tester.pieceAt(4,4));
        	assertEquals(null, tester.pieceAt(5, 5));
        	assertEquals(b, tester.pieceAt(3,3));
        }
        @Test
        public void testShieldBomb() {
            Board tester = new Board(true);
        	Piece a = new Piece(false, tester, 6, 6, "img/bomb-water.png");
        	Piece b = new Piece(true, tester, 3, 3, "img/pawn-fire.png");
        	Piece c = new Piece(true, tester, 5, 5, "img/shield-fire.png");
        	tester.place(a, 6 ,6);
        	tester.place(b, 3 ,3);
        	tester.place(c, 5 ,5);
        	tester.select(6,6);
        	tester.select(4,4);
        	assertEquals(null, tester.pieceAt(4,4));
        	assertEquals(null, tester.pieceAt(5, 5));
        	assertEquals(null, tester.pieceAt(3,3));
        }

        public static void runTests() {
            jh61b.junit.textui.runClasses(Boardtester.class);
        }
    }
}





