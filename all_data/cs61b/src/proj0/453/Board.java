/** 
 *  
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] gameBoard = new Piece[8][8];
    private Piece selected;
    private boolean moved;
    private int player; //0 if on side Fire, 1 if on side Water 
    private int selectedX = -1; //-1 if no spot is selected
    private int selectedY = -1; //-1 if no spot is selected
    private int _firePieces;
    private int _waterPieces;
    
    
    
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {
    	
    	if (shouldBeEmpty) {
    		
    	}
    	else {

    		int row = 0;
    		int col = 0;
    		while (row < 8) {
    			while (col < 8) {
    				if ((row+col) % 2 == 0) {
	    				if (row == 0) {
	    					gameBoard[col][row] = new Piece(true, this, col, row, "pawn");
	    					}
	    				if (row == 1) {
	    					gameBoard[col][row] = new Piece(true, this, col, row, "shield");
	    					}
	    				if (row == 2) {
	    					gameBoard[col][row] = new Piece(true, this, col, row, "bomb");
	    					
	    				}
	    				if (row == 5) {
	    					gameBoard[col][row] = new Piece(false, this, col, row, "bomb");
	    					}
	    				if (row == 6) {
	    					gameBoard[col][row] = new Piece(false, this, col, row, "shield");
	    					}
	    				if (row == 7) {
	    					gameBoard[col][row] = new Piece(false, this, col, row, "pawn");
	    					
	    				}
	    			}
    				col += 1; 
    			}
    			col = 0;
    			row += 1;
    			}
    		}
    }
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected != null && selected == pieceAt(i,j)) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (gameBoard[i][j] != null) {
                	
                    StdDrawPlus.picture(i + .5, j + .5, getPath(gameBoard[i][j]), 1, 1);
                }
            }
        }
    }
    
    /**takes in a piece and returns the path to the image of that piece's type*/
    private String getPath(Piece p) {
    	if (p.isKing()) {
    		if (p.isBomb()) {
	    		if (p.isFire()) {
	    			return "img/bomb-fire-crowned.png";
	    		}
	    		else {
	    			return "img/bomb-water-crowned.png";
	       		}
    		}
    		if (p.isShield()) {
    			if (p.isFire()) {
    				return "img/shield-fire-crowned.png";    				
    			}
    			else {
    				return "img/shield-water-crowned.png";
    			}
    		}
    		else {
    			if (p.isFire()) {
    				return "img/pawn-fire-crowned.png";
    			}
    		}
    	}
    	else {
    		if (p.isBomb()) {
    	
	    		if (p.isFire()) {
	    			return "img/bomb-fire.png";
	    		}
	    		else {
	    			return "img/bomb-water.png";
	       		}
			}
			if (p.isShield()) {
				if (p.isFire()) {
					return "img/shield-fire.png";    				
				}
				else {
					return "img/shield-water.png";
				}
			}
			else {
				if (p.isFire()) {
					return "img/pawn-fire.png";
				}
			else {
				return "img/pawn-water.png";
			}
		}
    	}
    	return null;
    }
    
    /**Gets the piece at position (x, y) on the board, or null 
     * if there is no piece. If (x, y) are out of bounds, returns null.*/
    public Piece pieceAt(int x, int y) {
    	if (x > 7 || y > 7 || x < 0 || y < 0) {
    		return null;
    	}
    	if ((x + y) % 2 == 1) {
    		return null;
    	}
    	
    	return gameBoard[x][y];
    	
    }
    
    //NEED DO
    public boolean canSelect(int x, int y) {
    	System.out.println(x);
    	System.out.println(y);
    	System.out.println(pieceAt(x,y));
    	Piece piece = pieceAt(x, y);
		if (piece != null) {
			System.out.println("1");
			if (piece.side() == player){
				System.out.println("2");
				if (selected == null || !moved){
					System.out.println("3");
					return true;
				}
			}
			System.out.println("4");
			return false;
		}
		else if (selected != null) {
			System.out.println("5");
			if ((!moved || selected.hasCaptured()) && validMove(selectedX, selectedY, x, y) ) {
				System.out.println("6");
				return true;
			}
			System.out.println("7");
		}
		System.out.println("8");
		return false;
    }
    			
    	
    
    public boolean _validBlock(int x, int y) {		
    	if (x > 7 || y > 7 || x < 0 || y < 0) {
    		return false;
    	}
    	
    	if ((x + y) % 2 == 1) {
    		return false;
    	}
    	return true;
    	
    }
    
    public boolean validMove(int xi, int yi, int xf, int yf) {
    	
    	if (!_validBlock(xf, yf)) {
    		return false;
    		}

    	if (pieceAt(xf, yf) != null) {
    		return false;
    	}
    	if (player == 0) {
    		if (Math.abs(xf - xi) == 1 && (yf - yi == 1)) {
    			return true;
    		}
    		if (Math.abs(xf - xi) == 2 && (yf - yi == 2)) {
    			Piece pieceMiddle = pieceAt((xf + xi)/2, (yf + yi)/2);
    			if (pieceMiddle == null) {
    				return false;
    			}
    			else {
    				if (pieceMiddle.side() != 0){
    					return true;
    				}
    				return false;
    			}
    		}
    		return false;
    	}
    	if (player == 1) {	
    		if (Math.abs(xf - xi) == 1 && (yf - yi == -1)) {
    			return true;
    		}
    		Piece pieceMiddle = pieceAt((xf + xi)/2, (yf + yi)/2);
			if (pieceMiddle == null) {
				return false;
			}
			else {
				if (pieceMiddle.side() != 1){
					return true;
				}
				return false;
			}
    	}
    	return false;
    }	
    	
    
    /**Selects the piece at (x, y) if possible. Optionally, it is recommended
     *  to color the background of the selected square white on the GUI via the
     *  pen color function. For any piece to perform a capture, that piece must have been selected first.*/    
    public void select(int x, int y) {
    	selected = gameBoard[x][y];
    	selectedX = x;
    	selectedY = y;
    	System.out.println("selected" + x + "," + y);
    }
    
    /**Places p at (x, y). If (x, y) is out of bounds, does nothing. If another piece already exists at (x, y),
     * p will replace that piece. (This method is potentially useful for creating specific test circumstances.)*/
    public void place(Piece p, int x, int y) {
    	if (p == null) {
    		return;
    	}
    	if (x > 7 || y > 7 || x < 0 || y < 0) {
    		return;
    	}
    	else {
    		if (p.isFire()) {
    			_firePieces += 1;
    		}
    		else{
    			_waterPieces += 1;
    		}
    		gameBoard[x][y] = p;
    	}
    }
    
    
    /** Returns whether or not the the current player can end their turn. To be able to end a turn, 
     * a piece must have moved or performed a capture.*/
    public boolean canEndTurn() {
    	return moved;
    }
 
    public void endTurn() {
    	if (player == 0) {
    		player = 1;
    	}
    	else {
    		player = 0;
    	}
    	moved = false;
    	selected = null;
    	selectedX = -1; //selectedX is -1 if nothing is selected
    	selectedY = -1;
    }
    
    /** Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds,
     * returns null and prints an appropriate message. If there is no piece at (x, y), returns null 
     * and prints an appropriate message.*/
    public Piece remove(int x, int y) {
    	if (x > 7 || y > 7) {
    		System.out.println("The location x, y is out of bounds. Appropriate values are 0 - 7.");
    		return null;
    	}
    	else if (pieceAt(x, y) == null) {
    		System.out.println("There is no piece at (x, y).");
    		return null;
    	}
    	else {
    		
    		Piece removedPiece = gameBoard[y][x];
    		if (removedPiece.isFire()) {
    			_firePieces -= 1;
    		} else{
    			_waterPieces -= 1;
    		}
    		gameBoard[y][x] = null;
    		return removedPiece;
    	}
    }
    
    public String winner() {
    	if (_firePieces > _waterPieces) {
    		return "Fire";
    	}
    	else if (_waterPieces > _firePieces) {
    			return "Water";
    		}
    	else {
    		return "no one";
    	}
    }
    public static void main(String[] args) {
    	
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board myGameBoard = new Board(false);
        
        while(true) {
            myGameBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (myGameBoard.canSelect((int) x, (int)y)) {
                	myGameBoard.select((int) x, (int) y);
                }
            }            
            StdDrawPlus.show(100);
        }
    }
    }
    
    
