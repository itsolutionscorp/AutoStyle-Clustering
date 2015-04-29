
public class Board {
	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private int size = 8;
	private boolean fireTurn = true;
	private boolean hasMoved = false;
	private Piece selected;
	private Piece moving;
	private int selectedX = 1000;
	private int selectedY = 1000;
	private boolean hasSelected;
	private int hoverX;
	private int hoverY;
	private boolean willCapture;

	/**
	 * Constructor for the game's board
	 * Calls initialPieces to instantiate original pieces if not empty
	 * @param empty determines whether or not the board is empty
	 */
	public Board(boolean empty) {
		shouldBeEmpty = empty;

		if (!shouldBeEmpty)
			pieces = initialPieces(size, this);
		else
			pieces = new Piece[size][size];        
	}    

	/**
	 * @param x indicates x coordinate on Board
	 * @param y indicates y coordinate on Board
	 * @return the piece at coordinates, or null if there is no piece
	 */
	public Piece pieceAt(int x, int y) {
		if ((x > size - 1 || x < 0) || (y > size - 1 || y < 0)) {
			return null;
		}
		else
			return pieces[x][y];
	}

	/**
	 * Helper for Select determines whether or not Player can select
	 * a space at (x, y).
	 * Calls validMove, handles capture and double capture situations
	 * @param x for x coordinate
	 * @param y for y coordinate
	 * @return true if the targeted space can be selected, else false
	 */
	public boolean canSelect(int x, int y) {
		Piece target = pieceAt(x, y);

		//cases for a Piece target
		if (target != null) {
			if (target.isFire() != fireTurn) {
				return false;
			} else if (!hasSelected) {
				return true;
			} else if (!hasMoved) {
				return true;
			} else {
				return false;
			}  

			// cases for empty space target
		} else if (!hasSelected){
			return false;
		} else {
//			in bomb case moving will == null
			if (moving == null){
				return false;
			} 
			if (moving.isBomb() && moving.hasCaptured()) {
				return false;
			}
			
			//if target square is not a valid move, false

			else if (!validMove(selectedX, selectedY, x, y)) {
				return false;
				
				//following cases mean target is a valid move    
			} else if (!hasMoved) {
				return true;
			} else if (willCapture){
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Changes Board's pieces array to place a piece there
	 * @param p is the piece to be placed
	 * @param x coordinate of destination
	 * @param y coordinate of destination
	 */
	public void place(Piece p, int x, int y) {
		if ((x > size) || ( y > size)) {
			return;
		}
		pieces[x][y] = p;
	}
	/**
	 * Selection of a piece or square for movement
	 * @param x coordinate of selected square
	 * @param y coordinate of selected square
	 */
	public void select(int x, int y){
		Piece target = pieceAt(x, y);
		selected = target;
		selectedY = y; 
		selectedX = x;
		// If a piece has already been selected, move.
		if (hasSelected && target == null) {
			moving.move(x, y);
			hasMoved = true;

			// If moving piece has not been selected, select it.
		} else {
			moving = selected;
			hasSelected = true;
		}
	}

	/**
	 * Removes a piece from the board and returns that piece.
	 * @param x coordinate of piece to be removed
	 * @param y coordinate of piece to be removed
	 * @return the piece being removed
	 */
	public Piece remove(int x, int y) {
		Piece currPiece = pieceAt(x, y);;
		pieces[x][y] = null;
		return currPiece;
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		selectedX = 1000;
		selectedY = 1000;
		hasSelected = false;
		hasMoved = false;
		moving.doneCapturing();
		selected = null;
		moving = null;
	}

	public String winner() {
		boolean hasFire = false;
		boolean hasWater = false;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Piece currPiece = pieceAt(i, j);

				if (currPiece != null) {
					if (currPiece.isFire()) {
						hasFire = true;
					} else {
						hasWater = true;
					}
				}

				if (hasFire && hasWater) {
					return null;
				}
			}
		}

		if (hasWater && !hasFire) {
			return "Water";
		}
		if (hasFire && !hasWater) {
			return "Fire";
		}
		if (!hasFire && !hasWater) {
			return "No one";
		}

		return null;
	}


	/* CHECKS WHETHER OR NOT A MOVE IS VALID
	 *  TAKES TYPE INTO ACCOUNT
	 *  CAPTURE SITUATIONS HANDLED
	 *  CROWNED PIECES HANDLED  */
	 private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((xf + yf) % 2 != 0 ) {
			return false;
		}
		if (!(xf >= 0 && xf <= size) || !(yf >= 0 && yf <= size)) {
			return false;
		}

		Piece finalSquare = pieceAt(xf, yf);
		Piece currPiece = pieceAt(xi, yi);

		//cannot select if final square contains a piece
		if (finalSquare != null) {
			return false;
		}

		// Can go up or down based on isKing or water/fire type
		if (yf > yi && !currPiece.isFire() && !currPiece.isKing()) {
			return false;
		} else if (yf < yi && !currPiece.isKing() && currPiece.isFire()) {
			return false;
		}

		//simple move: one square, no capture
		if (yf == yi + 1 || yf == yi -1) {
			if (xf == xi + 1 || xf == xi -1) {
				willCapture = false;
				return !currPiece.hasCaptured();
			}
		}

		//capture situations
		if (yf == yi + 2 || yf == yi - 2) {
			if (xf == xi + 2 || xf == xi - 2) {
				int capturedX = ((xf + xi) / 2);
				int capturedY = ((yf + yi) / 2);
				Piece capturedPiece = pieceAt(capturedX, capturedY);
				if (capturedPiece == null) {
					return false;
				} else if (capturedPiece.isFire() == currPiece.isFire()) {
					return false;
				} else {
					willCapture = true;
					return true;
				}
			}
		}
		return false;
	 }

	 /* DRAWS THE CURRENT STATE OF THE BOARD 
	  * HANDLES HOVER COLOR CHANGE 
	  * CALLS drawPiece(int N, Board b) FOR PIECES */
	 private void drawBoard(int N) {
		 StdDrawPlus.setXscale(0, size);
		 StdDrawPlus.setYscale(0, size);
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 if (i == hoverX && j == hoverY) {
					 StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				 } else if (i == selectedX && j == selectedY) {
					 StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				 } else if ((i + j) % 2 == 0) {
					 StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				 }  else {                 
					 StdDrawPlus.setPenColor(StdDrawPlus.RED);
				 }
				 StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				 StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				 Piece currPiece = pieceAt(i, j);

				 if (currPiece != null) {
					 drawPiece(currPiece, i, j);
				 }
			 }
		 }
	 }

	 /* DRAWS PIECES, HANDLES CROWNED */
	 private void drawPiece(Piece p, int x, int y) {
		 String pType;
		 String side;
		 String suffix;

		 if (p.isBomb()) {
			 pType = "bomb";

		 } else if (p.isShield()) {
			 pType = "shield";
		 } else {
			 pType = "pawn";
		 }  
		 if (p.isFire()){
			 side = "fire";
		 } else {
			 side = "water";
		 }
		 if (p.isKing()) {
			 suffix = "-crowned.png";
		 } else  {
			 suffix = ".png";
		 }
		 String pImage = pType + "-" + side + suffix;

		 StdDrawPlus.picture(x + .5, y + .5, "img/" + pImage, 1, 1);
	 }

	 private Piece[][] initialPieces(int N, Board b) {
		 String type = null;
		 Piece[][] initialPieces = new Piece[N][N];
		 boolean isFire;
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 if ((i + j) % 2 == 0) { //only draw on valid tiles

					 /*Determine type of initial initialPieces */
					 if ((j == 0) || (j == N - 1)) {
						 type = "pawn";
					 } else if ((j == 1 || j == N - 2)) {
						 type = "shield";
					 } else if (j == 2 || j == N - 3) {
						 type = "bomb";
					 } else {
						 type = null;
					 }


					 /* determine side of initial initialPieces */
					 if (j < N / 2) {
						 isFire = true;
					 } else {
						 isFire = false;
					 }

					 /* initializes initial initialPieces where they exist */
					 if (type != null)     
						 initialPieces[i][j] = new Piece(isFire, b, i, j, type);
					 else
						 initialPieces[i][j] = null;
				 }
			 }
		 }
		 return initialPieces;           
	 }



	 public static void main(String[] args) {
		 Board b = new Board(false);
		 while (true) {
			 b.drawBoard(b.size);
			 b.hoverX = (int) StdDrawPlus.mouseX();
			 b.hoverY = (int) StdDrawPlus.mouseY();
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
			 if (StdDrawPlus.isNPressed()) {
//			 if (StdDrawPlus.isNPressed() && StdDrawPlus.isKeyPressed(KeyEvent.VK_E) && StdDrawPlus.isKeyPressed(KeyEvent.VK_W)) {

				 b = new Board(false);
			 }
			 
			 if (b.winner() != null) {
				 b.drawBoard(b.size);
				 System.exit(0);
			 }
			 StdDrawPlus.show(10);
		 }        
	 }

}
