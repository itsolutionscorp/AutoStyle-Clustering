public class Board {
	
	// My own private pieces, booleans, and the like 
	// see bottom for citations

	private static int N = 8;
	private Piece[][] pieces;
	private boolean hasMoved = false;
	private Piece pieceSelected = null; 
	private Piece pieceCaptured = null;
	private int turn = 0;
	private double selectedX;
	private double selectedY;
	private boolean beenSelected = false;
	private int currentPieceX, currentPieceY;
	private int myX, myY;


///////////////////////////////////////////////////


	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty) this.pieces = new Piece[N][N];
		// creates an initial board
		else {
			this.pieces = new Piece[N][N];
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if (((i + j) % 2) == 0) {
	            		if (j == 0) privatePlace(new Piece(true, this, i, j, "pawn"), i, j);
	            		else if (j == 1) privatePlace(new Piece(true, this, i, j ,"shield"), i, j);
	            		else if (j == 2) privatePlace(new Piece(true, this, i, j ,"bomb"), i, j);
	            		else if (j == 5) privatePlace(new Piece(false, this, i, j ,"bomb"), i, j);
	            		else if (j == 6) privatePlace(new Piece(false, this, i, j ,"shield"), i, j);
	            		else if (j == 7) privatePlace(new Piece(false, this, i, j ,"pawn"), i, j);
					}				
				}
			}
		} 
	}
	private void drawBoard(int N) {
		// This function looks pretty ugly, sorry :/
	 	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
				if (((i + j) % 2) == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				if ((beenSelected) && (i == selectedX) && (j == selectedY)) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);  
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (pieceAt(i, j) != null) {
					if (pieces[i][j].isFire()) {
						if (pieces[i][j].isBomb()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);	
							}
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						}
						else if (pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);	
							}
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						}
						else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);	
							}
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					}
					else {
						if (pieces[i][j].isBomb()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);	
							}
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
						}
						else if (pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);	
							}
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						}
						else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);	
							}
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y) {
		// outside bounds? return null.
		if ((x > (N - 1)) || (y > (N - 1))) return null;
		else if (pieces[x][y] == null) return null;
		else return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		
		// general check to make sure we are selecting on the board
		if ((x < 0) || (y < 0) || (x > N - 1) || (y > N - 1)) return false;
		
		Piece p = pieceAt(x, y);
		
		if (p != null) { // aka there is a piece at the location
			if (turn == p.side()) {
				if (pieceSelected == null) return true;
				else if (pieceSelected != null) {
					if (hasMoved == false) return true;
				}
				else return false;
			}
		}
		
		else { // aka there is no piece at the location
			if (pieceSelected == null) return false; 
			// now we know that a piece has been selected previously
			if (!hasMoved)  {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if (pieces[i][j] == pieceSelected) {
							currentPieceX = i;
							currentPieceY = j;
						}
					}
				} 
				if (validMove(x, y, currentPieceX, currentPieceY)) return true;
				return false;
			}
			else if ((pieceSelected.hasCaptured() == true)) {
				// if I can perform a capture, then return true. 
				if (validMove(x, y, currentPieceX, currentPieceY)) return true;
				return false;
			}
			
		}
		return false;
	}
		

	private boolean validMove(int a, int b, int x, int y) {
		
		// am i selecting a square of the right color? 
		if ((a + b) % 2 == 1) return false;
		// is there a piece already in the spot I wish to move to?
		if (pieceAt(a, b) != null) return false;
		
		int dx = (a - x);
		int dy = (b - y);
		
		if ((dx > 2)  || (dx < -2) || (dy > 2) || (dy < -2)) 	return false;
		if ((dx == 0) || (dy == 0)) 							return false;
		
		Piece p = pieceAt(x, y);
		
		if ((dy == 1) || (dy == -1)) {
			if (p.isKing()) return true;
			if (( p.isFire()) && (dy ==  1)) return true;
			if ((!p.isFire()) && (dy == -1)) return true;
			return false;
		} 
		else {
			if (!p.isKing()) {
				if (( p.isFire()) && (dy !=  2)) return false;
				if ((!p.isFire()) && (dy != -2)) return false;
			}
			Piece pc = pieceAt(x + dx/2, y + dy/2);
			if (pc == null) return false;
			if (pc.isFire() != p.isFire()) {
				pieceCaptured = pc;
				//p.hasCaptured = true;
				return true; 
			}
			return false;	
		}
	}

	public void select(int x, int y) {
		// picking a piece  
		if (pieceAt(x, y) != null) {
			pieceSelected = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
			beenSelected = true;
			return;
		}
		else {
			// empty square
			place(pieceSelected, x, y);
			selectedX = x;
			selectedY = y;
			//beenSelected = true;
			
			if (pieceCaptured != null) {
				// if I intend to capture a piece
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if (pieces[i][j] == pieceCaptured) remove(i, j);
						if (pieces[i][j] == pieceSelected) {
							myX = i;
							myY = j;
						}
					}
				}
				pieceCaptured = null;
				if (pieceSelected.isBomb()) {
					// create a 3x3 square
					Piece p;
					pieceSelected = null;
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							p = pieceAt(x + i, y + j);
							if ((p != null) && (!p.isShield())) remove(x + i, y + j);
						}
					}	
				}
			}
		}
	}

	public void place(Piece p, int x, int y) {
		//if ((x > -1) && (x < N) && (y > -1) && (y < N)) {
			
		if (p != null) {
			this.pieces[x][y] = p;
			p.move(x, y);
			hasMoved = true;						
		}
	}
		
	private void privatePlace(Piece p, int x, int y) {
		if (p != null) {
				this.pieces[x][y] = p;
				//hasMoved = true;						
		}
	} 

	public Piece remove(int x, int y) {
		if ((x < N) && (y < N) && (x > -1) && (y > -1)) {
			if (pieceAt(x, y) != null) {
				Piece p = pieces[x][y];
				pieces[x][y] = null;
				return p;
			}
			else {
				System.out.println("There is no piece here!");
				return null;
			}
		}
		else {
			System.out.println("You're out of bounds!");
			return null;	
		}
	}

	public boolean canEndTurn() {
		return (hasMoved);
	}

	public void endTurn() {
		if (turn == 0) turn = 1;
		else turn = 0;
		pieceSelected = null;
		hasMoved = false;
		beenSelected = false;
		pieceCaptured = null;
	}


	public String winner() {
		// count the number of fire and water pieces left each turn.
		// when one reaches zero, return the other as the winner.
		// 
		int fireCount = 0, waterCount = 0;
		Piece p;
		int i = 0;
		while (i < N) {
			int j = 0;
			while (j < N) {
				p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) fireCount += 1;
					else            waterCount += 1;
				}
				j += 1;
			}
			i += 1;
		}

		if ((fireCount == 0) && (waterCount == 0)) 	return null;
		else if (fireCount == 0) 					return "Water";
		else if (waterCount == 0) 					return "Fire";
		else return null;
	}

	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
		


        while(true) {
			if (b.winner() != null) break;
			b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
            	int xInt = (int) x;
            	int yInt = (int) y;
            	if (b.canSelect(xInt, yInt)) {            
            		b.select(xInt, yInt);
            		//b.printVar();
            	}

        	}
        	if (StdDrawPlus.isSpacePressed()) {
        		if (b.canEndTurn()) b.endTurn();
        	}
        StdDrawPlus.show(100);
        }
        b.winner();
    }

}
	


/* CITATIONS
 * Declaring multiple variables in one line: http://stackoverflow.com/questions/20117500/java-one-line-variable-declaration
 * Exiting a while loop: http://stackoverflow.com/questions/7951690/how-do-i-exit-a-while-loop-in-java
 * Debugging help: 
 ** Lab assistant helped me with my canSelect function for an hour on Friday morning. 
 	He helped me change my drawBoard so that I placed the pieces instead of merely putting them in an array.  
 ** Lab assistant helped me write for-loops during Wednesday noon office hours. 
 	She helped me know how to write a for-loop, helped with the syntax, and corrected my mistakes 
 	before the compiler hadto yell at me.
 ** Lab assistant (my floormate Aditya, coincidentally) helped me during Thursday 9:00 lab/office hours. 
 	He helped me diagnose my null pointer exception error messages.  
 ** My roommate Valerie reminded me to use the hasCapture and doneCapturing that are already implemented 
 	in my Piece.java, not creating my own in Board.java. That cleared up a few errors. 
 *
 */