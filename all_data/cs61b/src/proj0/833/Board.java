import static org.junit.Assert.*;
import org.junit.Test;

public class Board {

	private static int N = 8;
	private boolean[][] pieces = new boolean[N][N];
	private Piece[][] pieceInfo = new Piece[N][N];
	private int player = 0; 

	private boolean hasSelected = false; 
	private boolean hasMoved = false;
	private int coordX = -1;
	private int coordY = -1;

	private int numRed = 0;
	private int numBlue = 0;


	private void drawBoard(int N) {
        	for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		if (i == coordX && j == coordY) {
            			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            		} else {
            			if ((i + j) % 2 == 0) {
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	} else {
                		StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	}               
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            		}
                	if (pieces[i][j]) {
                		// Draw the piece at (i, j). Find out what image to put.
                		String path = "";
                		String t = "";
                		String fireOrWater = "";
                		if (pieceInfo[i][j].isBomb()) {
                			t = "bomb";
                		} else if (pieceInfo[i][j].isShield()) {
                			t = "shield";
                		} else {
                			t = "pawn";
                		}
                		if (pieceInfo[i][j].isFire()) {
                			fireOrWater = "fire";
                		} else {
                			fireOrWater = "water";
                		}
                		if (pieceInfo[i][j].isKing()) {
                			path = "img/" + t + "-" + fireOrWater + "-" + "crowned" + ".png";
                		} else {
                			path = "img/" + t + "-" + fireOrWater + ".png";
                		}
                		StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
                	}
            	}
        	}
    	}


	// Methods above here are my methods and MUST BE PRIVATE.

	public Board(boolean shouldBeEmpty) {

		if (shouldBeEmpty == false) {
			// Initialize board with the default configuration.
			// CODE THIS: These loops are not scalable to an N x N board (optional)...
			String[] s = {"pawn", "shield", "bomb"};
			int[] a = {0, 1, 0};
			int[] b = {1, 0, 1};
			for (int j = 0; j < 3; j++) {
				for (int i = 0; i < N; i++) {
					if ((i % 2) == a[j]) {
						pieces[i][j] = true;
						Piece p = new Piece(true, this, i, j, s[j]);
						pieceInfo[i][j] = p;
					}
				}
			}
			for (int j = 5; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if ((i % 2) == b[7-j]) {
						pieces[i][j] = true;
						Piece p = new Piece(false, this, i, j, s[7-j]);
						pieceInfo[i][j] = p;
					}
				}
			}
			numRed = 12;
			numBlue = 12;
		}
	}

	public Piece pieceAt(int x, int y) {
		// Gets the piece at position (x, y) on the board, or null 
		// if there is no piece. If (x, y) are out of bounds, returns
		//  null.

		if (x >= N || x < 0 || y >= N || y < 0) {
			return null;
		}
		if (pieces[x][y]) {
			return pieceInfo[x][y];
		} else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		// Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.
		// If another piece already exists at (x, y), p will replace that piece. 
		// (This method is potentially useful for creating specific test circumstances.)
		if (x >= 8 || x < 0 || y >= 8 || y < 0 || p == null) {
			return;

		} else if (pieces[x][y]) {
			pieceInfo[x][y] = p;     
			if (p.isFire()) {
				numRed = numRed + 1;   // increment because move calls remove for noncaptures
			} else {
				numBlue = numBlue + 1;
			}
		} else {
			pieceInfo[x][y] = p;
			pieces[x][y] = true;
			if (p.isFire()) {
				numRed = numRed + 1;
			} else {
				numBlue = numBlue + 1;
			}
		}
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		// It is suggested you use this method to simplify your implementation of canSelect
		// Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to 
		// (xf, yf), strictly from a geometry/piece-race point of view. validMove does not 
		// need to take into consideration whose turn it is or if a move has already been
		// made, but rather can.

		if (xi < 0 || xi >= N || yi < 0 || yi >= N || xf < 0 || xf >= N || yf < 0 || yf >= N) {
			return false;
		}
		if (player == 0) { // red player
			if (pieces[xf][yf]) {
				return false;
			}
			if ((yf - yi == 1) && ((xf - xi == 1) || (xi - xf == 1)) ) {
				return true;
			}
			if ( (yf - yi == 2) && (xf - xi == 2) ) {
				if (pieces[xi+1][yi+1] && !(pieceInfo[xi+1][yi+1].isFire())) {
					return true;
				}
			}
			if ( (yf - yi == 2) && (xi - xf == 2) ) {
				if (pieces[xi-1][yi+1] && !(pieceInfo[xi-1][yi+1].isFire())) {
					return true;
				}
			}
			return false;
		} else { // blue player
			if (pieces[xf][yf]) {
				return false;
			}
			if ((yi - yf == 1) && ((xf - xi == 1) || (xi - xf == 1)) ) {
				return true;
			}
			if ( (yi - yf == 2) && (xf - xi == 2) ) {
				if (pieces[xi+1][yi-1] && pieceInfo[xi+1][yi-1].isFire()) {
					return true;
				}
			}
			if ( (yi - yf == 2) && (xi - xf == 2) ) {
				if (pieces[xi-1][yi-1] && pieceInfo[xi-1][yi-1].isFire()) {
					return true;
				}
			}
			return false;
		}
	}


	private boolean validMoveKing(int xi, int yi, int xf, int yf) {
		if (xi < 0 || xi >= N || yi < 0 || yi >= N || xf < 0 || xf >= N || yf < 0 || yf >= N) {
			return false;
		}
		if (player == 0) { // red king
			if (pieces[xf][yf]) {
				return false;
			}
			if ( ( (yi - yf == 1) || (yf - yi == 1) ) && ((xf - xi == 1) || (xi - xf == 1) ) ) {
				return true;
			}
			if ( (yf - yi == 2) && (xf - xi == 2) ) { // up right
				if (pieces[xi+1][yi+1] && !(pieceInfo[xi+1][yi+1].isFire())) {
					return true;
				}
			}
			if ( (yf - yi == 2) && (xi - xf == 2) ) { // up left
				if (pieces[xi-1][yi+1] && !(pieceInfo[xi-1][yi+1].isFire())) {
					return true;
				}
			}
			if ( (yi - yf == 2) && (xf - xi == 2) ) { // down right
				if (pieces[xi+1][yi-1] && !(pieceInfo[xi+1][yi-1].isFire())) {
					return true;
				}
			}
			if ( (yi - yf == 2) && (xi - xf == 2) ) { // down left
				if (pieces[xi-1][yi-1] && !(pieceInfo[xi-1][yi-1].isFire()) ) {
					return true;
				}
			}
			return false;
		} else { // blue king
			if (pieces[xf][yf]) {
				return false;
			}
			if ( ((yf - yi == 1) || (yi - yf == 1)) && ((xf - xi == 1) || (xi - xf == 1)) ) {
				return true;
			}
			if ( (yf - yi == 2) && (xf - xi == 2) ) { // up right
				if (pieces[xi+1][yi+1] && pieceInfo[xi+1][yi+1].isFire()) {
					return true;
				}
			}
			if ( (yf - yi == 2) && (xi - xf == 2) ) { // up left
				if (pieces[xi-1][yi+1] && pieceInfo[xi-1][yi+1].isFire()) {
					return true;
				}
			}
			if ( (yi - yf == 2) && (xf - xi == 2) ) { // down right
				if (pieces[xi+1][yi-1] && pieceInfo[xi+1][yi-1].isFire()) {
					return true;
				}
			}
			if ( (yi - yf == 2) && (xi - xf == 2) ) { // down left
				if (pieces[xi-1][yi-1] && pieceInfo[xi-1][yi-1].isFire()) {
					return true;
				}
			}
			return false;
		}
	}
	
		
	
	private boolean isValidMove(Piece p, int xi, int yi, int xf, int yf) {
		if (p.isKing()) {
			return validMoveKing(xi, yi, xf, yf);
		} else {
			return validMove(xi, yi, xf, yf);
		}
	}


	private boolean validCaptureMove(int xi, int yi, int xf, int yf) {
		if (validMove(xi, yi, xf, yf)) {
			if ((yf - yi == 2) || (yi - yf == 2)) {
				return true;
			}
		}
		return false;
	}



	private boolean validCaptureMoveKing(int xi, int yi, int xf, int yf) {
		if (validMoveKing(xi, yi, xf, yf)) {
			if ((yf - yi == 2) || (yi - yf == 2)) {
				return true;
			}
		}
		return false;
	}


	private boolean isValidCaptureMove(Piece p, int xi, int yi, int xf, int yf) {
		if (p.isKing()) {
			return validCaptureMoveKing(xi, yi, xf, yf);
		} else {
			return validCaptureMove(xi, yi, xf, yf);
		}
	}


	public boolean canSelect(int x, int y) {
		// Returns true if the square at (x, y) can be selected.

			// A square with a piece may be selected if it is the corresponding player’s 
			// turn and one of the following is true:

				//(1) The player has not selected a piece yet.
				//(2) The player has selected a piece, but did not move it.

			// An empty square may be selected if one of the following is true:

				//(1) During this turn, the player has selected a Piece which hasn’t moved
				// yet and is selecting an empty spot which is a valid move for the 
				// previously selected Piece.
				//(2) During this turn, the player has selected a Piece, captured, and has 
				// selected another valid capture destination. When performing 
				// multi-captures, you should only select the active piece once; all other
				// selections should be valid destination points.

		if (pieces[x][y] && ( (player == 0 && pieceInfo[x][y].isFire()) || 
							  (player == 1 && !(pieceInfo[x][y].isFire())) ) ) { // piece in square
			if (!hasSelected || (hasSelected && !hasMoved)) {
				return true;
			} else {
				return false;
			}
		} else { // empty square
			if (hasSelected && !hasMoved && !pieces[x][y] && 
						isValidMove(pieceInfo[coordX][coordY], 
									coordX, coordY, x, y)) {
				return true;
			} else if (hasSelected && pieceInfo[coordX][coordY].hasCaptured() 
							&& isValidCaptureMove(pieceInfo[coordX][coordY], 
									coordX, coordY, x, y)) {
				return true;
			} else {
				return false;
			}
		}
	}


	public void select(int x, int y) {
		// Selects the square at (x, y). This method assumes canSelect (x,y) returns true. 
		// Optionally, it is recommended to color the background of the selected square 
		// white on the GUI via the pen color function. For any piece to perform a capture,
		// that piece must have been selected first. If you select a square with a piece, 
		// you are prepping that piece for movement. If you select an empty square 
		// (assuming canSelect returns true), you should move your most recently selected 
		// piece to that square.
		
		// Recognize when x, y is different from coordX, coordY ************  <------
		

		if (coordX != x && coordY != y && !pieces[x][y]) {
			pieceAt(coordX, coordY).move(x, y);
			hasMoved = true;
		}

		coordX = x;
		coordY = y;	
		hasSelected = true;
		
	}

	
	public boolean canEndTurn() {
		// Returns whether or not the the current player can end 
		// their turn. To be able to end a turn, a piece must have
		// moved or performed a capture.

		if (hasMoved == true) {
			return true;
		} 
		if (pieceAt(coordX, coordY) != null && pieceAt(coordX, coordY).hasCaptured()) {
			pieceAt(coordX, coordY).doneCapturing();   
			return true;
		}
		return false;
	}

	public void endTurn() {
		// Called at the end of each turn. Handles switching of 
		// players and anything else that should happen at the end 
		// of a turn.
		
		hasSelected = false;
		hasMoved = false;
		if (pieceAt(coordX, coordY) != null) {
			pieceAt(coordX, coordY).doneCapturing();  // set to false regardless if didn't capture
		}
		coordX = -1;
		coordY = -1;
		player = 1 - player;
	}


	public Piece remove(int x, int y) {
		// Executes a remove. Returns the piece that was removed. 
		// If the input (x, y) is out of bounds, returns null and 
		// prints an appropriate message. If there is no piece at 
		// (x, y), returns null and prints an appropriate message.

		if (x < 0 || x >= N || y < 0 || y >= N) {
			System.out.println("inputs x or y are out of bounds in the remove method call");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("there is no piece at " + x + ", " + y + " to remove");
			return null;
		}
		Piece p = pieceAt(x, y);
		if (p.isFire()) {
			numRed = numRed - 1;
		} else {
			numBlue = numBlue - 1;
		}
		pieces[x][y] = false;
		return p;
	}

		
	public String winner() {
		// Returns the winner of the game. "Fire", "Water", "No one"
		// (tie / no pieces on the board), or null if the game is not
		// yet over. Assume there is no stalemate situation in which
		// the current player has pieces but cannot legally move any
		// of them (In this event, just leave it at null). Determine 
		// the winner solely by the number of pieces belonging to each
		// team.

		if (numRed > 0 && numBlue > 0) {
			return null;
		} else if (numRed > 0 && numBlue == 0) {
			return "Fire";
		} else if (numRed == 0 && numBlue > 0) {
			return "Water";
		} else {
			return "No one";
		}
	}

	public static void main(String[] args) {
		// Starts GUI supported version of the game and doesn't end 
		// until game is over

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
      
        Board b = new Board(false);
        String w;

        while (true) {
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
        	if (b.winner() != null) {
        		w = b.winner();
        		break;
        	}
        	StdDrawPlus.show(100);
        }
        System.out.println(w);
    } 


// ----------------------------------------------------------------------------
// --------------------------------- TESTING ----------------------------------
// ----------------------------------------------------------------------------

public static class TestBoard {

	public Board emptyBoard;
	public Board startBoard;
	
	@Test
	public void testPieceAt() {
		startBoard = new Board(false);
		Piece p = startBoard.pieceAt(0, 0);
		assertEquals(true, p.isFire());
		assertEquals(false, p.isBomb());
		assertEquals(false, p.isShield());
		assertEquals(false, p.hasCaptured());
		assertEquals(false, p.isKing());

		assertEquals(null, startBoard.pieceAt(2, 1));
		assertEquals(null, startBoard.pieceAt(6, 7));
		assertEquals(null, startBoard.pieceAt(8, 8));
		assertEquals(null, startBoard.pieceAt(-1, 2));
		emptyBoard = new Board(true);
		assertEquals(null, emptyBoard.pieceAt(5, 5));
	}

	@Test
	public void testPlace() {
		// Add piece to empty spot
		emptyBoard = new Board(true);
		Piece p = new Piece(true, emptyBoard, 1, 1, "shield");
		emptyBoard.place(p, 1, 1);
		Piece q = emptyBoard.pieceAt(1, 1);
		assertEquals(true, q.isShield()); 

		// Add piece to spot already occupied
		startBoard = new Board(false);
		Piece initial = startBoard.pieceAt(0, 0);
		assertEquals(true, initial.isFire());
		assertEquals(false, initial.isShield());
		p = new Piece(true, startBoard, 0, 0, "shield");
		startBoard.place(p, 0, 0);
		q = startBoard.pieceAt(0, 0);
		assertEquals(true, q.isShield());
	}



	@Test
	public void testValidMove() {		
		startBoard = new Board(false);
		assertEquals(0, startBoard.player);
		assertEquals(true, startBoard.validMove(2, 2, 3, 3));
		assertEquals(true, startBoard.validMove(2, 2, 1, 3));
		assertEquals(true, startBoard.validMove(0, 2, 1, 3));   
		assertEquals(false, startBoard.validMove(0, 2, 2, 4)); 	
		assertEquals(true, startBoard.validMove(6, 2, 7, 3));	
		assertEquals(false, startBoard.validMove(1, 1, 2, 2));	
		assertEquals(false, startBoard.validMove(1, 1, 3, 3));	
		assertEquals(false, startBoard.validMove(2, 0, 5, 3));	
		assertEquals(false, startBoard.validMove(0, 2, -1, 3));	
		assertEquals(false, startBoard.validMove(7, 1, 8, 2));	
		// Try moving blue piece
		assertEquals(false, startBoard.validMove(1, 5, 0, 4));
		// Switch to blue player
		startBoard.player = 1;
		assertEquals(1, startBoard.player);
		assertEquals(true, startBoard.validMove(1, 5, 0, 4));
		assertEquals(true, startBoard.validMove(1, 5, 2, 4));
		assertEquals(false, startBoard.validMove(3, 5, 5, 3));
		assertEquals(true, startBoard.validMove(7, 5, 6, 4));
		assertEquals(false, startBoard.validMove(6, 6, 5, 5));
		assertEquals(false, startBoard.validMove(6, 6, 5, 5));
		assertEquals(false, startBoard.validMove(6, 6, 4, 4));
		assertEquals(false, startBoard.validMove(1, 7, 4, 4));
		assertEquals(false, startBoard.validMove(7, 5, 8, 4));
		assertEquals(false, startBoard.validMove(0, 6, -1, 5));
		// Try moving a red piece
		assertEquals(false, startBoard.validMove(2, 2, 3, 3));
		
		// Jumping on empty board
		emptyBoard = new Board(true);
		emptyBoard.player = 1;
		assertEquals(1, emptyBoard.player);
		Piece p = new Piece(false, emptyBoard, 6, 6, "shield");
		emptyBoard.place(p, 6, 6);
		p = new Piece(true, emptyBoard, 5, 5, "shield");
		emptyBoard.place(p, 5, 5);
		assertEquals(true, emptyBoard.validMove(6, 6, 4, 4));
	}
	

	@Test
	public void testValidMoveKing() {
		emptyBoard = new Board(true);
		Piece p = new Piece(true, emptyBoard, 2, 2, "shield");
		emptyBoard.place(p, 2, 2);
		assertEquals(true, emptyBoard.validMoveKing(2, 2, 1, 1));
		assertEquals(false, emptyBoard.validMoveKing(2, 2, 0, 0));
		p = new Piece(false, emptyBoard, 1, 1, "shield");
		emptyBoard.place(p, 1, 1);
		assertEquals(true, emptyBoard.validMoveKing(2, 2, 0, 0));

		emptyBoard.player = 1;
		p = new Piece(false, emptyBoard, 5, 5, "shield");
		emptyBoard.place(p, 5, 5);
		assertEquals(true, emptyBoard.validMoveKing(5, 5, 6, 6));
		assertEquals(false, emptyBoard.validMoveKing(5, 5, 7, 7));
		p = new Piece(true, emptyBoard, 6, 6, "shield");
		emptyBoard.place(p, 6, 6);
		assertEquals(true, emptyBoard.validMoveKing(5, 5, 7, 7));
		
	}


/*  Need to make kinged field in Piece.java public for this test

	@Test
	public void testIsValidMove() {	
		emptyBoard = new Board(true);
		Piece p = new Piece(true, emptyBoard, 1, 1, "shield");
		emptyBoard.place(p, 1, 1);
		p.kinged = true;
		assertEquals(true, emptyBoard.isValidMove(p, 1, 1, 0, 0));
		
		emptyBoard.player = 1;
		p = new Piece(false, emptyBoard, 1, 5, "shield");
		emptyBoard.place(p, 1, 5);
		assertEquals(false, p.isKing());
		p.kinged = true;
		assertEquals(true, emptyBoard.isValidMove(p, 1, 5, 0, 6));
	}
*/

	@Test
	public void testValidCaptureMove() {
		emptyBoard = new Board(true);
		Piece p = new Piece(true, emptyBoard, 3, 3, "shield");
		emptyBoard.place(p, 3, 3);
		p = new Piece(false, emptyBoard, 4, 4, "shield");
		emptyBoard.place(p, 4, 4);
		assertEquals(true, emptyBoard.validCaptureMove(3, 3, 5, 5));
		assertEquals(false, emptyBoard.validCaptureMove(3, 3, 2, 4));

		emptyBoard.player = 1;
		p = new Piece(false, emptyBoard, 2, 2, "shield");
		emptyBoard.place(p, 2, 2);
		assertEquals(false, emptyBoard.validMove(2, 2, 0, 0));
		p = new Piece(true, emptyBoard, 1, 1, "shield");
		emptyBoard.place(p, 1, 1);
		assertEquals(true, emptyBoard.validMove(2, 2, 0, 0));
	}



	@Test
	public void testValidCaptureMoveKing() {
		emptyBoard = new Board(true);
		Piece p = new Piece(true, emptyBoard, 6, 6, "shield");
		emptyBoard.place(p, 6, 6);
		p = new Piece(false, emptyBoard, 5, 5, "shield");
		emptyBoard.place(p, 5, 5);
		assertEquals(true, emptyBoard.validCaptureMoveKing(6, 6, 4, 4));

		p = new Piece(true, emptyBoard, 3, 3, "shield");
		emptyBoard.place(p, 3, 3);
		assertEquals(false, emptyBoard.validCaptureMoveKing(3, 3, 2, 2));	

		emptyBoard.player = 1;
		p = new Piece(false, emptyBoard, 0, 0, "shield");
		emptyBoard.place(p, 0, 0);
		assertEquals(false, emptyBoard.validCaptureMoveKing(0, 0, 2, 2));		
		assertEquals(false, emptyBoard.validCaptureMoveKing(0, 0, 1, 1));
		p = new Piece(true, emptyBoard, 1, 1, "shield");
		emptyBoard.place(p, 1, 1);
		assertEquals(true, emptyBoard.validMoveKing(0, 0, 2, 2));
	}

	@Test
	public void testIsValidCaptureMove() {
		emptyBoard = new Board(true);
		Piece p1 = new Piece(true, emptyBoard, 2, 2, "shield");
		emptyBoard.place(p1, 2, 2);
		Piece p2 = new Piece(true, emptyBoard, 3, 3, "shield");
		emptyBoard.place(p2, 3, 3);
		assertEquals(false, emptyBoard.isValidCaptureMove(p1, 2, 2, 4, 4));
		p2 = new Piece(false, emptyBoard, 3, 3, "shield");
		emptyBoard.place(p2, 3, 3);
		assertEquals(true, emptyBoard.isValidCaptureMove(p1, 2, 2, 4, 4));
	}

	@Test 
	public void testCanSelect() {
		
	}

	@Test
	public void testSelect() {

	}

	@Test
	public void testCanEndTurn() {

	}

	@Test
	public void testEndTurn() {
		
		emptyBoard = new Board(true);
		Piece p = new Piece(true, emptyBoard, 0, 0, "pawn");
		emptyBoard.place(p, 0, 0);
		p = new Piece(false, emptyBoard, 1, 1, "pawn");
		emptyBoard.place(p, 1, 1);
		assertEquals(false, emptyBoard.pieceAt(0, 0).hasCaptured());
		assertEquals(false, emptyBoard.hasMoved);
		//emptyBoard.pieceAt(0, 0).move(2, 2);  Movement is done via select, not move directly
		emptyBoard.select(0, 0);
		emptyBoard.select(2, 2);
		assertEquals(true, emptyBoard.hasMoved);
		assertEquals(true, emptyBoard.pieceAt(2,2).hasCaptured());
		assertEquals(true, emptyBoard.canEndTurn());
		emptyBoard.endTurn();
		assertEquals(1, emptyBoard.player);
		assertEquals(false, emptyBoard.pieceAt(2, 2).hasCaptured());


		// Simple piece movement
		emptyBoard = new Board(true);
		p = new Piece(true, emptyBoard, 0, 0, "pawn");
		emptyBoard.place(p, 0, 0);
		assertEquals(false, emptyBoard.pieceAt(0, 0).hasCaptured());
		assertEquals(false, emptyBoard.hasMoved);
		emptyBoard.select(0, 0);
		emptyBoard.select(1, 1);
		//emptyBoard.pieceAt(0, 0).move(1, 1);
		assertEquals(false, emptyBoard.pieceAt(1, 1).hasCaptured());
		assertEquals(true, emptyBoard.hasMoved);
		assertEquals(true, emptyBoard.canEndTurn());
		emptyBoard.endTurn();
		assertEquals(false, emptyBoard.pieceAt(1, 1).hasCaptured());
		assertEquals(false, emptyBoard.hasMoved);
	
	}

	@Test
	public void testRemove() {
		
	}

	@Test
	public void testWinner() {
		emptyBoard = new Board(true);
		assertEquals(0, emptyBoard.numRed);
		startBoard = new Board(false);
		assertEquals(12, startBoard.numRed);

		assertEquals("No one", emptyBoard.winner());

		Piece p = new Piece(true, emptyBoard, 3, 3, "pawn");
		emptyBoard.place(p, 3, 3);
		assertEquals(1, emptyBoard.numRed);
		p = new Piece(false, emptyBoard, 4, 4, "pawn");
		emptyBoard.place(p, 4, 4);
		assertEquals(1, emptyBoard.numRed);
		assertEquals(1, emptyBoard.numBlue);
		assertEquals(null, emptyBoard.winner());
		emptyBoard.select(3, 3);
		emptyBoard.select(5, 5);
		emptyBoard.endTurn();
		assertEquals("Fire", emptyBoard.winner());

		emptyBoard = new Board(true);
		emptyBoard.player = 1;
		p = new Piece(false, emptyBoard, 4, 4, "pawn");
		emptyBoard.place(p, 4, 4);
		p = new Piece(true, emptyBoard, 3, 3, "pawn");
		emptyBoard.place(p, 3, 3);
		emptyBoard.select(4, 4);
		emptyBoard.select(2, 2);
		emptyBoard.endTurn();
		assertEquals("Water", emptyBoard.winner());

		// Two bombs left, fire jumps water
		emptyBoard = new Board(true);
		p = new Piece(true, emptyBoard, 3, 3, "bomb");
		emptyBoard.place(p, 3, 3);
		p = new Piece(false, emptyBoard, 4, 4, "bomb");
		emptyBoard.place(p, 4, 4);
		emptyBoard.select(3, 3);
		emptyBoard.select(5, 5);
		emptyBoard.endTurn();
		assertEquals("No one", emptyBoard.winner());

		// Two bombs left, water jumps fire
		emptyBoard = new Board(true);
		emptyBoard.player = 1;
		p = new Piece(true, emptyBoard, 3, 3, "bomb");
		emptyBoard.place(p, 3, 3);
		p = new Piece(false, emptyBoard, 4, 4, "bomb");
		emptyBoard.place(p, 4, 4);
		emptyBoard.select(4, 4);
		emptyBoard.select(2, 2);
		emptyBoard.endTurn();
		assertEquals("No one", emptyBoard.winner());

	}

	public static void runTests() {
            jh61b.junit.textui.runClasses(TestBoard.class);
    }
}

}