/** 
 *  @author Bret Frangipane
 */

public class Board {

	private boolean shouldBeEmpty; 
	private Piece[][] pieceLayout;
	private int size;
	private boolean turnIsFire; // designates water or fire's turn
	private boolean hasSelectedPiece; //designates whether a piece has been selected this turn
	private Piece selectedPiece; // designates which piece is selected. If no piece selected, null
	private boolean hasMovedPiece; //designates whether a piece has been moved this turn
	private boolean hasCapturedPiece; //designates whether a piece has been moved this turn 
	private int mouseX, mouseY; // location of mouse press
	private int prevMouseX, prevMouseY; // location of previous mouse press
	private boolean hasStartedTesting; // designates whether codes is being tested by ag/Junit or run by playGame()

	public Board (boolean shouldBeEmpty) {
		this.size = 8;
		this.shouldBeEmpty = shouldBeEmpty;
		this.pieceLayout = new Piece[size][size];
		this.turnIsFire = true;
		this.hasSelectedPiece = false;
		this.selectedPiece = null;
		this.hasMovedPiece = false;
		this.hasCapturedPiece = false;
		this.mouseX = 0;
		this.mouseY = 0;
		this.prevMouseX = 7;
		this.prevMouseY = 7;
		this.hasStartedTesting = false;
		if (!this.shouldBeEmpty) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j<size; j++) {
					if (i%2==0 && j==0) {
						pieceLayout[i][j] = new Piece(true, this, i, j, "pawn");
					} else if (i%2==1 && j==1) {
						pieceLayout[i][j] = new Piece(true, this, i, j, "shield");
					} else if (i%2==0 && j==2) {
						pieceLayout[i][j] = new Piece(true, this, i, j, "bomb");
					} else if (i%2==1 && j==5) {
						pieceLayout[i][j] = new Piece(false, this, i, j, "bomb");
					} else if (i%2==0 && j==6) {
						pieceLayout[i][j] = new Piece(false, this, i, j, "shield");
					} else if (i%2==1 && j==7) {
						pieceLayout[i][j] = new Piece(false, this, i, j, "pawn");
					}	
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		/** if x, y out of bounds, return null
		 * if no piece at x, y, return null
		 * else, return piece at position x,y */
		if (x<0 || y<0 || x>=size || y>=size) {
			return null;
		} else {
			return this.pieceLayout[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		/** piece can be selected if it is the corresponding player's turn and either
		 * the player has not selected a piece yet, or
		 * the player has selected a piece but has not moved it
		* empty square can be selected if during this turn, player has
		 * selected piece and is now selecting valid spot to move piece, or
		 * selected piece, captured, and has selected another valid capture destination 
		 * for multi captures, only select active piece once; all other spots are destination points **/ 

		if (this.pieceAt(x,y) == null) {
			if (this.hasSelectedPiece && this.validMove(this.prevMouseX, this.prevMouseY, x, y)) { 
				return true;
			}
		} else if (this.turnIsFire == this.pieceAt(x,y).isFire()) {
			if (!this.hasSelectedPiece || (this.hasSelectedPiece && !this.hasMovedPiece)) { 
				return true;
			} 
		} return false;
	} 

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// SORRY THIS METHOD IS SO VERBOSE
		if (xi<0 || yi<0 || xf<0 || yf<0 || xi>=size || yi>=size || xf>=size || yf>=size) { // out of bounds
			return false;
		} else if (this.pieceLayout[xf][yf] != null) { // can't move to an occupied spot
			return false;
		} else if ((xf == xi || yf == yi) && (this.prevMouseY != 0 && this.prevMouseY != 7)) { // must move diagonally, except at edges, during crownings + multicapture 
			return false;
		} else if (Math.abs((xf - xi)) != Math.abs((yf - yi))) { // move must be diagonal with slope 1 or -1
			return false; 
		} else if (this.selectedPiece.isFire() == true && (!this.selectedPiece.isKing()) && (yf < yi)) { // only crowned pieces can move backwards 
			return false;																						 
		} else if (this.selectedPiece.isFire() == false && (!this.selectedPiece.isKing()) && (yf > yi)) { // only crowned pieces can move backwards 
			return false;
		} else if (Math.abs(yf - yi) > 2) { // can't jump more than one space
			return false;
		} else if (this.hasMovedPiece && !this.hasCapturedPiece) { // can't move again after moving before but not capturing
			return false;
		} else if (this.hasCapturedPiece && Math.abs(yf-yi) == 1) { // can't move after move unless capturing again
			return false;  
		} else if ((xf%2==0 && yf%2==1) || (xf%2==1 && yf%2==0)) { // spaces not on checkered grid are off-limits. Fixes that one strange bug
			return false;
		} else if ((yf - yi) == 2 && (xf - xi) == 2){
			if (this.pieceLayout[xf-1][yf-1] == null) { // can't skip an unoccupied spot
				return false; 
			} else if (this.pieceLayout[xf-1][yf-1].isFire() == this.pieceLayout[xi][yi].isFire()) { // can't capture same team
				return false;
			}
		} else if ((yf - yi) == -2 && (xf - xi) == -2) {
			if (this.pieceLayout[xf+1][yf+1] == null) { // can't skip an unoccupied spot
				return false;
			} else if (this.pieceLayout[xf+1][yf+1].isFire() == this.pieceLayout[xi][yi].isFire()) { // can't capture same team
				return false;
			}
		} else if ((yf - yi) == 2 && (xf - xi) == -2) {
			if (this.pieceLayout[xf+1][yf-1] == null) { // can't skip an unoccupied spot
				return false;
			} else if (this.pieceLayout[xf+1][yf-1].isFire() == this.pieceLayout[xi][yi].isFire()) { // can't capture same team
				return false;
			}	
		} else if ((yf - yi) == -2 && (xf - xi) == 2) {
			if (this.pieceLayout[xf-1][yf+1] == null) { // can't skip an unoccupied spot
				return false;
			} else if (this.pieceLayout[xf-1][yf+1].isFire() == this.pieceLayout[xi][yi].isFire()) { // can't capture same team
				return false;
			}	
		} return true;
	}

	public void select(int x, int y) {
		/** selects piece at x, y
		 * optional, color square white on gui via pen function
		 * to capture, piece must be selected */

		if (this.pieceAt(x,y) != null) {
			this.selectedPiece = this.pieceAt(x, y);
			this.hasSelectedPiece = true;
		} else {				
			this.selectedPiece.move(x, y);
			this.hasMovedPiece = true; // checking this
		}
		this.prevMouseX = x;
		this.prevMouseY = y;
	}

	public void place(Piece p, int x, int y) {
		if (x<0 || y<0 || x>=size || y>=size || p==null) {
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (this.pieceLayout[i][j] == p) {

						this.pieceLayout[i][j] = null;
					}
				}
			}
			this.pieceLayout[x][y] = p;
			if (!this.hasStartedTesting) {
				this.mouseX = x;
				this.mouseY = y;
				this.hasStartedTesting = true;
			}
			this.prevMouseX = x;
			this.prevMouseY = y;
		}
	}

	public Piece remove(int x, int y) {
		//remove and return piece at x,y. 
		//If no piece, return null and print appropriate message.
		if (this.pieceLayout[x][y]==null) {
			System.out.println("no piece existed at this position.");
			return null;
		}
		Piece returnThisPiece = this.pieceLayout[x][y];
		this.pieceLayout[x][y] = null;
		this.hasCapturedPiece = true;
		return returnThisPiece;
	}

	public boolean canEndTurn() {
		/** returns whether player can end turn.
		 * to end turn, piece must have been moved or captured. */
		if (hasMovedPiece || hasCapturedPiece) {
			return true;
		} return false;
	}

	public  void endTurn() {
		// called after end of each turn.
		// Switches players, etc.
		this.turnIsFire = !turnIsFire;
		this.selectedPiece.doneCapturing();
		this.hasSelectedPiece = false;
		this.selectedPiece = null;
		this.hasMovedPiece = false;
		this.hasCapturedPiece = false;
	}

	public String winner() {
		// returns winner. "Fire", "Water", "No one" (tie or no pieces on board), null if game not over.
		// assume no stalemate, in which case you return null.
		// determine winner based on number of pieces on board for each team.
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (this.pieceLayout[i][j] == null) {
				} else if (this.pieceLayout[i][j].isFire()) {
					numFire += 1;
				} else if (!this.pieceLayout[i][j].isFire()) {
					numWater += 1;
				}
			}
		}
		if (numFire == 0 && numWater == 0) {
			return "No one";
		} else if (numFire > 0 && numWater > 0) {
			return null;
		} else if (numFire > numWater) {
			return "Fire";
		}
		return "Water";
	}

	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (!shouldBeEmpty) {
					if (this.pieceLayout[i][j] != null) {
						if (!this.pieceLayout[i][j].isKing()) {
							if (this.pieceLayout[i][j].isFire()) {
								if (this.pieceLayout[i][j].isBomb()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/bomb-fire.png", 1, 1);
								} else if (this.pieceLayout[i][j].isShield()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/shield-fire.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5,  j + .5, "img/pawn-fire.png", 1, 1);
								}
							} else {
								if (this.pieceLayout[i][j].isBomb()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/bomb-water.png", 1, 1);
								} else if (this.pieceLayout[i][j].isShield()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/shield-water.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5,  j + .5, "img/pawn-water.png", 1, 1);
								}
							}
						} else {
							if (this.pieceLayout[i][j].isFire()) {
								if (this.pieceLayout[i][j].isBomb()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/bomb-fire-crowned.png", 1, 1);
								} else if (this.pieceLayout[i][j].isShield()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/shield-fire-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5,  j + .5, "img/pawn-fire.png-crowned.png", 1, 1);
								}
							} else {
								if (this.pieceLayout[i][j].isBomb()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/bomb-water-crowned.png", 1, 1);
								} else if (this.pieceLayout[i][j].isShield()) {
									StdDrawPlus.picture(i + .5,  j + .5, "img/shield-water-crowned.png", 1, 1);
								} else {
									StdDrawPlus.picture(i + .5,  j + .5, "img/pawn-water-crowned.png", 1, 1);
								}
							}
						}
					}
				}
			}
		}
	}

	private void playGame() {
		/** Check for mouse press. 
		 * Select Piece
		 * Select Square
		 * Move
		 * Repeat
		 *  
		 */
		// Got help from Leo Steimetz. He suggested I run this code in a private method rather than in main, and gave me some guidance in debugging this method.
		while (true) {
			this.drawBoard(this.size); 
			if (StdDrawPlus.mousePressed()) {
				this.mouseX = (int) StdDrawPlus.mouseX();
				this.mouseY = (int) StdDrawPlus.mouseY();
				if (this.canSelect(this.mouseX, this.mouseY)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(mouseX + .5, mouseY + .5, .5);
					this.select(this.mouseX, this.mouseY);
				}
			}
			StdDrawPlus.show(25);
			if (StdDrawPlus.isSpacePressed()) {
				if (this.canEndTurn()) {
					this.endTurn();
				}
			}
			if (this.winner() != null) {
				System.out.println(this.winner());
				return;
			}
		}
	}

	public static void main (String args[]) {
		/** starts GUI */
		Board board = new Board(false);
		board.drawBoard(board.size);  
		if (StdDrawPlus.mousePressed()) {
			board.prevMouseX = (int) StdDrawPlus.mouseX();
			board.prevMouseY = (int) StdDrawPlus.mouseY();
		}
		board.playGame();
	}            
}
// Thanks to Leo Steimetz for general debugging tips
// Thanks to Rheza Hidajat, a recent Berkeley CS grad, who helped me with Java syntax (static, private, public, main, etc.) as I started the project. 