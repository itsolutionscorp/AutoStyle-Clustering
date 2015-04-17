public class Board {

	private static Board current_board;
	private boolean empty_board;
	private Piece[][] pieces;
	private int player = 0; 
	private Piece current_piece = null;
	private int current_x = -1; //So that no square is highlighted until valid select
	private int current_y;
	private boolean moved = false;
	private int fire_pieces;
	private int water_pieces;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (i == current_x && j == current_y) {
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                StdDrawPlus.filledSquare(current_x + .5, current_y + .5, .5);
                }
                Piece p = this.pieces[i][j];
                if (p != null) {
                	if (p.isFire()) {
                		if (p.isBomb()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
            			else if (p.isShield()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		}
                		else {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}                			
                		}
                	}
                	else {
                		if (p.isBomb()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		}
            			else if (p.isShield()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		}
                		else {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}                			
                		}
                	}
                }
           	}
        }
    }
	
    public static void main(String[] args) {
        if (args.length == 0) {
        	current_board = new Board(false);
        }
        else if (args[0].equals("empty")) {
        	current_board = new Board(true);
        }
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) { 
        	current_board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int int_x = (int)x;
                int int_y = (int)y;
                if (current_board.canSelect(int_x, int_y)) {
                    current_board.select(int_x, int_y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (current_board.canEndTurn()) {
            		current_board.endTurn();
            	}
            }         
            StdDrawPlus.show(10);
        }
    }

    public Board(boolean shouldBeEmpty) {
        this.pieces = new Piece[8][8];
    	if (shouldBeEmpty) {
    		empty_board = true;
    	}
    	else {
    		empty_board = false;
    	}
        if (!this.empty_board) {
        	this.fire_pieces = 12;
        	this.water_pieces = 12;
	        for (int i = 0; i < 8; i += 1) {
	            for (int j = 0; j < 8; j += 1) {
	                if ((i + j) % 2 == 0) {
	                	if (j == 0) {
	                		pieces[i][j] = new Piece(true, this, i, j, "pawn");
	                	}
	                	if (j == 1) {
	                		pieces[i][j] = new Piece(true, this, i, j, "shield"); 
	                	}
	                	if (j == 2) {
	                		pieces[i][j] = new Piece(true, this, i, j, "bomb"); 
	                	}
	                	if (j == 5) {
	                		pieces[i][j] = new Piece(false, this, i, j, "bomb"); 
	                	}
	                	if (j == 6) {
	                		pieces[i][j] = new Piece(false, this, i, j, "shield"); 
	                	}
	                	if (j == 7) {
	                		pieces[i][j] = new Piece(false, this, i, j, "pawn"); 
	                	}
	                }
	            }
	        }        	
        }

    }

    public Piece pieceAt(int x, int y) {
    	if (x > 7 || x < 0 || y > 7 || y < 0) {
    		return null;
    	}
    	return this.pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
    	this.pieces[x][y] = p;
    	if (p.isFire()) {
    		fire_pieces += 1;
    	}
    	else {
    		water_pieces += 1;
    	}
    }

    public Piece remove(int x, int y) {
    	if (x > 7 || x < 0 || y > 7 || y < 0) {
    		System.out.println("These coordinates are out of bounds");
    		return null;
    	}
    	Piece proxy = this.pieces[x][y];
    	if (proxy == null) {
    		System.out.println("There is no piece to remove here");
    	}
    	else {
    		if (proxy.isFire()) {
    			this.fire_pieces -= 1;
    		}
    		else {
    			this.water_pieces -= 1;
    		}
    		this.pieces[x][y] = null;
    	}
    	return proxy;
    }

    public boolean canEndTurn() {
		return moved;
    }

    public void endTurn() {
    	player = 1 - player;
    	moved = false;
    	if (current_piece!= null) {
    		current_piece.doneCapturing();
    		current_piece = null;
    	}
    	current_x = -1;
    }

    public String winner() {
    	if (fire_pieces > 0 && water_pieces > 0) {
    		return null;
    	}
    	if (fire_pieces > 0) {
    		return "Fire";
    	}
    	if (water_pieces > 0) {
    		return "Water";
    	}
    	return "No one";
    }

    public void select(int x, int y) {
    	//select a piece if you can select it and there is a piece @ x, y
    	if (this.pieceAt(x, y) != null) {
    		this.current_piece = this.pieceAt(x, y);
    		this.current_x = x;
    		this.current_y = y;
    	}
    	//move a piece or capture if you have selected a piece and the move is valid
    	else {
    		current_piece.move(x, y);
    		this.current_x = x;
    		this.current_y = y;
    		moved = true;
    	}
    }

    //checks if you can select a piece or empty square
    public boolean canSelect(int x, int y) {
    	if (x < 0 || x > 7 || y < 0 || y > 7) {
    		return false;
    	}
    	// true if there is a piece, it is yours, and you have not already moved
    	if (this.pieceAt(x, y) != null && (this.pieceAt(x, y).side() == player) && moved == false) {
    		return true;
    	}
    	// false if you select an empty square when you havent selected a piece
    	if (this.current_piece == null) {
    		return false;
    	}
    	// checks empty square if you haven't moved or if the current piece has captured
    	if (this.pieceAt(x, y) == null && this.validMove(current_x, current_y, x, y) &&
    			(moved == false || this.current_piece.hasCaptured())) {
    		return true;
    	}
    	return false;
    }

// Below is a bunch of private methods to check directions for validMove.











    // checks if an empty square is a valid move
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if (this.current_piece.hasCaptured()) {
    		if (this.current_piece.isKing()) {
    			return this.captureUp(xi, yi, xf, yf) || this.captureDown(xi, yi, xf, yf);
    		}
    		if (this.current_piece.isFire()) {
    			return this.captureUp(xi, yi, xf, yf);
    		}
    		return this.captureDown(xi, yi, xf, yf);
    	}
    	if (this.current_piece.isKing()) {
    		return this.diagonalUp(xi, yi, xf, yf) || this.diagonalDown(xi, yi, xf, yf) ||
    			   this.captureUp(xi, yi, xf, yf) || this.captureDown(xi, yi, xf, yf);
    	}
    	if (this.current_piece.isFire())
    		return this.diagonalUp(xi, yi, xf, yf) || this.captureUp(xi, yi, xf, yf);
    	else {
    		return this.diagonalDown(xi, yi, xf, yf) || this.captureDown(xi, yi, xf, yf);
    	}
    }




    // checks if x and y are a diagonal upward
    private boolean diagonalUp(int xi, int yi, int xf, int yf) {
		if (yi + 1 > 7) {
			return false;
		}
		if (yf == yi + 1) {
			if (xf == xi + 1 && xf < 8) {
				return true;
			}
			if (xf == xi - 1 && xf > -1) {
				return true;
			}
		}
		return false;
    }
    // checks if x and y are a valid upward capture of opponent piece
    private boolean captureUp(int xi, int yi, int xf, int yf) {
		if (yi + 2 > 7) {
			return false;
		}
		if (yf == yi + 2) {
			if (xf == xi + 2 && xf < 8) {
		    	Piece prisoner = this.pieceAt(xi + 1, yi + 1);
		    	if (this.pieceAt(xf, yf) == null && (prisoner != null) && prisoner.side() != this.current_piece.side()) {		
					return true;
				}
			}
			if (xf == xi - 2 && xf > -1) {
		    	Piece prisoner = this.pieceAt(xi - 1, yi + 1);
		    	if (this.pieceAt(xf, yf) == null && (prisoner != null) && prisoner.side() != this.current_piece.side()) {		
					return true;
				}
			}
		}
		return false;
    }


    // checks if x and y are a diagonal downward
    private boolean diagonalDown(int xi, int yi, int xf, int yf) {
		if (yi - 1 < 0) {
			return false;
		}
		if (yf == yi - 1) {
			if (xf == xi + 1 && xf < 8) {
				return true;
			}
			if (xf == xi - 1 && xf > -1) {
				return true;
			}
		}
		return false;
    }

    // checks if x and y are a valid downward capture of opponent piece
    private boolean captureDown(int xi, int yi, int xf, int yf) {
		if (yi - 2 < 0) {
			return false;
		}
		if (yf == yi - 2) {
			if (xf == xi + 2 && xf < 8) {
		    	Piece prisoner = this.pieceAt(xi + 1, yi - 1);
		    	if (this.pieceAt(xf, yf) == null && (prisoner != null) && prisoner.side() != this.current_piece.side()) {		
					return true;
				}
			}
			if (xf == xi - 2 && xf > -1) {
		    	Piece prisoner = this.pieceAt(xi - 1, yi - 1);
		    	if (this.pieceAt(xf, yf) == null && (prisoner != null) && prisoner.side() != this.current_piece.side()) {		
					return true;
				}
			}
		}
		return false;
    }
}

