public class Board {

	private Piece[][] pieces;   
	private int player = 0;  //0 player [fire], 1 player [water]
	private int numTurns = 0;  //number of turns
	private Piece selectedPiece = null;  //the currently selected piece
	private int sPX = 7;  //x coordinate of selected piece, set a random default -- shouldn't matter
	private int sPY = 7;  //y coordinate of selected piece, set a random default -- shouldn't matter
	private boolean hasMoved = false;   //whether selected piece has moved

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board board = new Board(false);
		while (board.winner() != null || board.winner() != "No one") {
			board.drawBoard();
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY(); 
                if (board.canSelect((int) x, (int) y)) {  //can select this square
                	board.select((int) x, (int) y);    //selects this square
            		board.mouse((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {  //switch players
           		board.endTurn();
            }
            if (board.numTurns % 2 == 0) { //player 1
            	board.player = 0;
            } else if (board.numTurns % 2 == 1) { //player 2
            	board.player = 1;
            }
	        StdDrawPlus.show(100);
		}
	}

	/** 
	 *  Board constructor.
	 *  If shouldBeEmpty is true, initializes empty Board, else initializes Board with default configuration.
	 **/
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (shouldBeEmpty) {     //makes empty board
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					pieces[i][j] = null;
				}	
			} 
		} else {                 //makes default board config
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (i % 2 == 0) {   //columns 0, 2, 4, 6
						if (j == 0) {   //row 0 (bottom)
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
						} else if (j == 2) {  //row 2
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
						} else if (j == 6) {  //row 6
							pieces[i][j] = new Piece(false, this, i, j, "shield");
						}
					} else {   //columns 1, 3, 5, 7
						if (j == 1) {   //row 1
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						} else if (j == 5) {   //row 5
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
						} else if (j == 7) {   //row 7 (top)
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}					
				}
			}
		}
	}

	/* Draws the Board. */
	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                addImg(i, j);       
            }
        }
    }

    /* Mouse stuff~~ */
	private void mouse(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);    
	}

	/* Adds various piece images. */
	private void addImg(int x, int y) {
		Piece p = pieces[x][y];
		if (p != null && p.isFire()) {
        	if (p.isBomb()) {        //bomb
        		if (p.isKing()) {
        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
        		} else {
            		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
            	}
            } else if (p.isShield()) {     //shield
            	if (p.isKing()) {
        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
        		} else {
            		StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
            	}
            } else {      //pawn
            	if (p.isKing()) {
        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
        		} else {
            		StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
            	}
            }
        } else if (p != null) {   //water
        	if (p.isBomb()) {
            	if (p.isKing()) {
        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
        		} else {
            		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
            	}
            } else if (p.isShield()) { //shield
            	if (p.isKing()) {
        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
        		} else {
            		StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
            	}
            } else {          //pawn
            	if (p.isKing()) {
        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
        		} else {
            		StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
            	}
            }
        }  
	}

	/**
     * Gets the piece at position (x, y) on board.
     * Null if no piece or if (x, y) are out of bounds.
     */
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || pieces[x][y] == null) {
			return null;
		}
		return pieces[x][y];
	}

	/**
     * True if (xi, yi) can capture a piece moving to (xf, yf).
     */
	private boolean canCapture(int xi, int yi, int xf, int yf) {
		if (player == pieceAt(xi, yi).side()) {     //fire player
			if (!pieces[xi][yi].isKing()) {         //NOT crowned King
				return fireCanCapture(xi, yi, xf, yf);
			} else {                                //piece IS crowned King
				boolean fKing = fireCanCapture(xi, yi, xf, yf) || waterCanCapture(xi, yi, xf, yf);
				return fKing;
			} 
		} else {                                    //water player
			if (!pieces[xi][yi].isKing()) {         //NOT crowned King
				return waterCanCapture(xi, yi, xf, yf);
			} else {                                //crowed King
				boolean wKing = fireCanCapture(xi, yi, xf, yf) || waterCanCapture(xi, yi, xf, yf);
				return wKing;
			}
		}
	}

	/**
     * True if fire (xi, yi) can capture a piece when moving to (xf, yf).
     */
	private boolean fireCanCapture(int xi, int yi, int xf, int yf) {
		if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {//((xf == (xi - 2)) || (xf == (xi + 2))) && (yf == (yi + 2))) {    //2-step diagonal
			if (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) {   //piece between  step
				if (pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
					return false;  //making sure piece in middle isn't own piece
				}
				return true;
			} 
			return false;     //no piece there
		} 
		return false;   //other # steps invalid
	}

	/**
     * True if water (xi, yi) can capture a piece when moving to (xf, yf).
     */
	private boolean waterCanCapture(int xi, int yi, int xf, int yf) {
		if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {            //2-step diagonal
			if (pieceAt((xi + xf) / 2, (yi + yf) / 2) != null) {           //piece between step
				if (!pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {
					return false;    //making sure piece in middle isn't own piece
				}
				return true;
			}
			return false;   //no piece there
		}
		return false;   //other # steps invalid	
	}

	/**
     * True if there is a piece at (x, y) and it can be selected.
     */
	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {      //square w/ piece
			if (player == pieceAt(x, y).side()) {    //player matches piece color
				if (selectedPiece == null || (selectedPiece != null && !hasMoved)) { //hasn't selected a piece yet or selected but didn't move
					return true;
				}
				return false;  
			}  
			return false;                              //player doesn't match piece color
		} else {               		                   //empty square
			if (selectedPiece != null && !hasMoved) {
				if (validMove(sPX, sPY, x, y)) {  
					return true;                        //valid move to (x, y)
				} 
				return false;
			} else if (selectedPiece != null && hasMoved && selectedPiece.hasCaptured()) { //has selected a piece & captured
				if (canCapture(sPX, sPY, x, y)) {
					return true;
				}
				return false;
			} 
			return false;
		}
	}

	/**
     * True if piece at (xi, yi) can either move to (xf, yf) or capture
     * to (xf, yf) in a valid fashion compatible with rules.
     * Strictly from a geometry/piece-race P.O.V.
     */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 8 && yi < 8 && xf < 8 && yf < 8) {
			Piece pO = pieces[xi][yi];
			if (pO == null || (xi == xf && yi == yf)) {   //to move piece is null or move to same spot
				return false; 
			} else if (player == pieceAt(xi, yi).side() && pO.isFire()) {  //fire's turn and right colored piece? (has to be red)               
				if (! pO.isKing()) {         							//reg piece -- NOT crowned King
					return fireDiagonals(xi, yi, xf, yf);
				} else {                							    //crowned King
					boolean fKing = fireDiagonals(xi, yi, xf, yf) || waterDiagonals(xi, yi, xf, yf);
					return fKing;
				}
			} else {                       					  //water's turn
				if (! pO.isFire()) {      				   //right colored piece? (has to be blue)
					if (! pO.isKing()) {    				 //reg piece -- NOT crowned King
						return waterDiagonals(xi, yi, xf, yf);
					} else {                				 //crowned King
						boolean wKing = fireDiagonals(xi, yi, xf, yf) || waterDiagonals(xi, yi, xf, yf);
						return wKing;
					}
				} else {  //wrong piece color
					return false;
				}
			}
		} else {    //x's or y's out-of-bounds
			return false;  
		}
	}	

	/**
     * Checks valid moves for regular fire pieces from (xi, yi) to (xf, yf).
     */
	private boolean fireDiagonals(int xi, int yi, int xf, int yf) {
		if (Math.abs(xi - xf) == 1 && (yi + 1) == yf) {    //1-step diagonal
			if (pieces[xf][yf] == null) {
				return true;  //empty 1 diagonal-step square
			} 
			return false;   //piece there
		} else {
			return fireCanCapture(xi, yi, xf, yf);
		}
	}

	/**
     * Checks valid moves for regular water pieces.
     */
	private boolean waterDiagonals(int xi, int yi, int xf, int yf) {
		if (Math.abs(xi - xf) == 1 && (yi - 1) == yf) {    //1-step diagonal
			if (pieces[xf][yf] == null) {
				return true;     //empty 1 diagonal-step square
			} 
			System.out.println("Invalid move: occupied square");
			return false;         //piece there
		} else { 				 //2-step diagonal
			return waterCanCapture(xi, yi, xf, yf);
		}
	}

	/**
     * Selects the piece at (x, y) if possible.
     */
	public void select(int x, int y) {
		if (selectedPiece == null) {
			selectedPiece = pieceAt(x, y);
			sPX = x;
			sPY = y;
		} else if (pieceAt(x, y) == null) {   //empty square
			selectedPiece.move(x, y);
			sPX = x;
			sPY = y;
			hasMoved = true;
		}
	}

	/**
     * Places p at (x, y).
     * If (x, y) are out of bounds or p is null, does nothing.
     * If p already exists in corrent Board, first remove from initial position.
     * If another piece already exists at (x, y), p will replace that piece.
     */
	public void place(Piece p, int x, int y) {
		if (p != null && x < 8 && y < 8 && x >= 0 && y >= 0) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (pieces[i][j] == p) {
						remove(i, j);
					}
				}
			} 
			pieces[x][y] = p;
		}
	}

	/**
     * Executes a remove and returns the removed piece.
     * If (x, y) out of bounds, returns null and prints message.
     * If no piece at (x, y), return null and prints message.
     */
	public Piece remove(int x, int y) {
		if (x > 7 || y > 7) {
			System.out.println("Position is out of bounds.");  //case1
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("Piece does not exist.");   //case2
			return null;
		} else {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}

	/**
     * Returns whether or not the current player can end their turn.
     * To end a turn, a piece must have moved or performed a capture.
     */
	public boolean canEndTurn() {
		if (selectedPiece != null) {    //no null pointer exceptions!
			if (hasMoved) {
				return true;
			} else if (selectedPiece.hasCaptured()) {  
				return true;
			}
			return false;    //hasn't moved or captured
		}
		return false;       //have not moved
	}

	/**
     * Handles switching of players and everything that should happen
     * at the end of each turn.
     */
	public void endTurn() {
		selectedPiece.doneCapturing();
		selectedPiece = null;
		numTurns += 1;
		hasMoved = false;
		if (numTurns % 2 == 0) { //player 0 (fire)
            player = 0;
        } else {                 //player 1 (water)
          	player = 1;
        }
	}

	/**
     * Returns winner of game.
     * To end a turn, a piece must have moved or performed a capture.
     */
	public String winner() {
		int numFires = 0;
		int numWaters = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						numFires += 1;
					} else if (!pieceAt(i, j).isFire()) {
						numWaters += 1;
					}
				}
			}
		}
		if (numFires == 0 && numWaters == 0) {
			return "No one";
		} else if (numFires == 0) {
			return "Water";
		} else if (numWaters == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

}