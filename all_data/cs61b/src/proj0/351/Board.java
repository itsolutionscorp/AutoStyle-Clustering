/** 
 *  @author Jiayi Yao
 */

public class Board {
    /** Location of pieces. */
    private static int N = 8;
    private String[][] boards;
    private static Piece[][] pieces;
    private boolean moved = false; // Default is this turn hasn't moved
    private boolean selected = false; // Whether the player has selected a piece 
    private int selectX, selectY;
    private boolean turnIsFire = true; // Default start turn is fire.
    private int firePiece, waterPiece;

    /* For -->TEST<-- */
    // public static Piece[][] pieces;
    // public boolean moved = false;
    // public boolean selected = false;
    // public int selectX, selectY;

    public Board(boolean shouldBeEmpty) {

    	// this.drawBoard();
    	pieces = new Piece[N][N];
    	boards = new String[N][N];
    	firePiece = 0;
    	waterPiece = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if ((i + j) % 2 == 0) {
    				boards[i][j] = "GRAY";
    			} else {
    				boards[i][j] = "RED";
    			}
    		}
    	}

    	/* If shouldBeEmpty is true, initializes an empty Board.
     	* Otherwise, initializes a Board with the default configuration */
    	if (shouldBeEmpty != true) {
    		/* Initial pieces */
    		firePiece = 12;
    		waterPiece = 12;
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				if (j == 0 && i % 2 == 0) {
    					pieces[i][j] = new Piece(true, this, i, j, "pawn");
    				}
    				if (j == 1 && i % 2 != 0) {
    					pieces[i][j] = new Piece(true, this, i, j, "shield");
    				}
    				if (j == 2 && i % 2 == 0) {
    					pieces[i][j] = new Piece(true, this, i, j, "bomb");
    				}
    				if (j == 5 && i % 2 != 0) {
    					pieces[i][j] = new Piece(false, this, i, j, "bomb");
    				}
    				if (j == 6 && i % 2 == 0) {
    					pieces[i][j] = new Piece(false, this, i, j, "shield");
    				}
    				if (j == 7 && i % 2 != 0) {
    					pieces[i][j] = new Piece(false, this, i, j, "pawn");
    				}
    				// if (pieces[i][j] != null) {
    				// 	drawPiece(i, j);
    				// }
    			}
    		}
    	}
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (boards[i][j] == "GRAY") {
    				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    			} else if (boards[i][j] == "RED") {
    				StdDrawPlus.setPenColor(StdDrawPlus.RED);
    			} else if (boards[i][j] == "WHITE") {
    				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    			}
    			StdDrawPlus.filledSquare(i + .5, j+ .5, .5);
    			if (pieces[i][j] != null) {
    				this.drawPiece(i, j);
    			}
    		}
    	}
    }

    /* Helper method: draw a piece. */
    private void drawPiece(int x, int y) {
    	String king, type, fireWater;
    	Piece p = pieces[x][y];

    	if (p.isKing()) {
    		king = "-crowned";
    	} else {
    		king = "";
    	}

    	if (p.isFire()) {
    		fireWater = "-fire";
    	} else {
    		fireWater = "-water";
    	}

    	if (p.isBomb()) {
    		type = "bomb";
    	} else if(p.isShield()) {
    		type = "shield";
    	} else {
    		type = "pawn";
    	}
    				
    	StdDrawPlus.picture(x + .5, y + .5, "img/" + type + fireWater + king + ".png", 1, 1);
    }

    /* Gets the piece at position (x, y) on the board, or null if there is no piece.
     * If (x, y) are out of bounds, return null. */
    public Piece pieceAt(int x, int y) {
    	if (x >=0 && x < N && y >= 0 && y < N && (pieces[x][y] != null)) { 
    	// x,y in bounds and there is a piece
    		return pieces[x][y];
    	}
    	return null;
    }

    /* Place p at(x,y). If (x,y) out of bounds or if p is null, does nothing.
     * If another piece already exists at (x, y), p will replace that piece.
     * (This method is potentially useful for creating specific test circumstatances.) */
    public void place(Piece p, int x, int y) {
    	if (x >= 0 && x < 8 && y >= 0 && y < 8 && p != null) {
    		pieces[x][y] = p;
    		if (p.isFire()) {
    			firePiece += 1;
    		} else {
    			waterPiece +=1;
    		}
    		// drawPiece(x, y);
    	}
    }

    public boolean canSelect(int x, int y) {
    	if (pieceAt(x, y) != null) {
    	// A square with a piece may be selected. -> First step: must with a piece
    		if (pieces[x][y].isFire() == this.turnIsFire) {
    		// If it is the corresponding player's turn
    			if (this.selected == false || (this.selected == true && this.moved == false)) {
    			/* 1. The player has not selected a piece yet
    			 * 2. or The player has selected a piece, but did not move it. */
    				return true; // This square can be selected
    			}
    		}
    	} else {
    	// An empty square may be selected. 
    		if (this.selected == true && this.moved == false) {
    		// 1. The player has selected a piece which hasn't moved yet;
    			if (this.validMove(selectX, selectY, x, y)) {
    			// This empty square is a valid move for the previously selected Piece.
    				return true;
    			}
    		}
    		if (this.moved == true && (pieces[selectX][selectY] != null) && pieces[selectX][selectY].hasCaptured()) {
    		// 2.The player has selected a piece, captured;
    			if (this.validMove(selectX, selectY, x, y)) {
    			// This empty square is a valid move for the previously selected Piece.
    				return true;
    			}
    		}
    	}
    	return false;
    }

	/* MUST KEEP THIS METHOD -->PRIVATE<-- */
    private boolean validMove(int xi, int yi, int xf, int yf) {
    // Whether the piece at(xf, yf) is a valid move for previous selected piece[xi][yi].
    	if (((xf == xi + 1) || (xf == xi -1)) && ((yf == yi + 1) || (yf == yi - 1)) && this.moved == false) {
    		if (pieceAt(xi, yi).isKing()) {
    		// The selected piece is king so can move diagonally uptowards and backtowards.
    			return true;
    		} else if (this.turnIsFire == true && (yf == yi + 1)) {// Fire turn
    			return true;
    		} else if ( this.turnIsFire == false && (yf == yi - 1)) {// Water turn 
    			return true;
    		}
    	} else if (((xf == xi - 2) || (xf == xi + 2)) && ((yf == yi + 2) || (yf == yi - 2))) {
    		int cX = (xf + xi) / 2;
    		int cY = (yf + yi) / 2;
    		if (pieceAt(cX, cY) != null && pieceAt(cX, cY).isFire() != this.turnIsFire) {
    			if (pieceAt(xi, yi).isKing()) {
    				return true;
    			} else if (this.turnIsFire == true && (yf == yi + 2)) {
    				return true;
    			} else if ( this.turnIsFire == false && (yf == yi - 2)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    public void select(int x, int y) {
    	if (this.pieceAt(x, y) == null) {
    		// IF select an empty square, move recently selected piece to that square.
    		pieces[selectX][selectY].move(x, y);
    		this.clearSelect(selectX, selectY);
    		// this.selected = true;
    		this.moved = true;
    		this.selectX = x;
    		this.selectY = y;
    		if (pieces[this.selectX][this.selectY] != null) {
    			drawSelect(selectX, selectY);
    		}

    	} else {
    		// If select a square with a piece, prep for movement;
    		this.clearSelect(selectX, selectY);
    		this.selected = true;
    		this.moved = false;
    		this.selectX = x;
    		this.selectY = y;	
    		this.drawSelect(x, y);
    	}
    }

    private void drawSelect(int x, int y) {
    	this.boards[x][y] = "WHITE";
    }
    private void clearSelect(int x, int y) {
    	this.boards[x][y] = "GRAY";
    }

    public Piece remove(int x, int y) {
    	if (x >= 0 && x < 8 && y >= 0 && y < 8) {
    		if (pieces[x][y] != null) {
    			Piece p = pieces[x][y];
    			pieces[x][y] = null;
    			if (p.isFire()) {
    				firePiece -= 1;
    			} else {
    				waterPiece -= 1;
    			}
    			return p;
    		} else {
    			System.out.println("No piece to be removed.");
    			return null;
    		}
    	} else {
    		System.out.println("Out of bounds.");
    		return null;
    	}
    }

    public boolean canEndTurn() {
    	if (this.moved) {
    		return true;
    	}
    	return false;
    }

    public void endTurn() {
    	this.turnIsFire = !this.turnIsFire;
    	this.selected = false;
    	this.moved = false;
    	if (pieces[selectX][selectY] != null) {
    	    pieces[selectX][selectY].doneCapturing();
    	    clearSelect(selectX, selectY);
    	}
    }

    public String winner() {
    	if (this.firePiece == 0 && this.waterPiece == 0) {
    		return "No one";
    	} else if (this.firePiece == 0) {
    		return "Water";
    	} else if (this.waterPiece == 0) {
    		return "Fire";
    	}
    	return null;
    }

    public static void main(String[] args) {  
    	// Board b = new Board(true);
    	// firePiece = 2;
    	// waterPiece = 2;
    	// pieces[3][3] = new Piece(true, b, 3, 3, "pawn");
    	// pieces[3][1] = new Piece(true, b, 3, 1, "pawn");
    	// pieces[6][6] = new Piece(false, b, 6, 6, "pawn");
    	// pieces[4][4] = new Piece(false, b, 4, 4, "pawn");
        Board b = new Board(false);
        while(true) {
        	b.drawBoard();
        	
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
        			if (b.winner() != null) {
        				break;
        			}
        		}
        	}
        	StdDrawPlus.show(25);
        }
    }
}