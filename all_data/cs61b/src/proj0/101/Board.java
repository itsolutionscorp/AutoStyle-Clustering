/** Board.java
 *
 *	1)   Class Variables:
 *		  1) ArrayList board: Holds state of board
 *	2)   void main(String args[]): starts GUI supported version of game
 *	3)   void Board(boolean shouldBeEmpty): If shouldBeEmpty is True initializes empty board.
 *									 Otherwise, initializes Board with default config.
 *	4)   Piece pieceAt(int x, int y): Gets piece at position (x,y) or null if none/off board.
 *	5)   boolean canSelect(int x, int y): Returns true if there is piece at (x,y) and can
 *										be selected.
 *	6)   boolean validMove(int xi, int yi, int xf, int yf): Returns true if piece can 
 *														  move/capture to (xf, xi).
 *	7)   void select(int x, int y): Selects piece at (x, y) if possible.
 *	8)   void place(Piece p, int x, int y): Places p at (x , y) and removes any 
 *											other instances
 *	9)   Piece remove(int x, int y): Removes and returns piece at (x, y)
 *	10)  boolean canEndTurn(): Returns whether or not player can end turn.
 *	11)  void endturn(): Handles player switch and and anything else needed at end of turn
 *	12)	 String winner(): Returns the winner of the game.
 *
 */

public class Board {

	private static int N = 8;
	private int turn = 0;
	private Piece[][] pieces = new Piece[N][N];
	private boolean hasMoved = false;
	private Piece selected = null;
  	private boolean noWinner = true;
  	private boolean validCapture = false;
 	private int[] selectedLocation = new int[2];

	private void drawImg(int x, int y, String imgPath) {
		StdDrawPlus.picture(x + 0.5, y + 0.5, imgPath, 1, 1);
	}

  	private void fillSquare(int x, int y) {
  		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
  		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
  	}

  	private void drawBoard() {
    	for (int i = 0; i < N; i++) {
      		for (int j = 0; j < N; j++) {
        		if ((i + j) % 2 == 0) {
          			StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
          		} else {
            		StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
        	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
       		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	if (this.pieceAt(i, j) != null) {
          		String imgPath = "img/";
          		Piece p1 = pieceAt(i, j);
          			if (p1.isBomb()) {
           				imgPath = imgPath + "bomb";
          			} else if (p1.isShield()) {
            			imgPath = imgPath + "shield";
          			} else {
           				imgPath = imgPath + "pawn";
          			}
          			if (p1.side() == 0) {
            			imgPath = imgPath + "-fire";
          			} else {
            			imgPath = imgPath + "-water";
          			}
          			if (p1.isKing()) {
            			imgPath = imgPath + "-crowned";
            		}
          			this.drawImg(i, j, imgPath + ".png");
          		}
        	}
      	}
    }

	public void place(Piece p, int x, int y) {
   		this.pieces[x][y] = p;
   	}

	public Piece pieceAt(int x, int y) {
   		if ((0 <= x) && (0 <= y) && (x < N) && (y < N)) {
   			return this.pieces[x][y];
   		}
   		return null;
   	}

    public void select(int x, int y) {
    	if (this.selected == null) {
    		this.selected = pieceAt(x, y);
    		this.selectedLocation[0] = x;
    		this.selectedLocation[1] = y;
    	} else {
    		this.selected.move(x, y);
    		this.hasMoved = true;
    		this.selected = this.pieceAt(x, y);
    		this.selectedLocation[0] = x;
    		this.selectedLocation[1] = y;
    	}
    }

   	private boolean upMove(int xi, int yi, int xf, int yf) {
   		if (((yi + 1) == yf) && (((xi + 1) == xf) || ((xi - 1) == xf))) {
  			return true;
  		} else if (((yi + 2) == yf) && ((xi + 2) == xf)) {
  			if (this.pieceAt(xi + 1, yi + 1) != null) {
  				if (this.pieceAt(xi + 1, yi + 1).side() != this.turn) {
  					this.validCapture = true;
  					return true;
  				}
  			} 
  		} else if (((yi + 2) == yf) && ((xi -2) == xf)) {
  			if (this.pieceAt(xi - 1, yi + 1) != null) { 
                if (this.pieceAt(xi - 1, yi + 1).side() != this.turn) {
                	this.validCapture = true;
  					return true;
  				}
  			}
  		}
  		return false;
   	}

   	private boolean downMove(int xi, int yi, int xf, int yf) {
   		if (((yi - 1) == yf) && (((xi + 1) == xf) || ((xi - 1) == xf))) {
  			return true;
  		} else if (((yi - 2) == yf) && ((xi + 2) == xf)) {
  			if (this.pieceAt(xi + 1, yi - 1) != null) {
  				if (this.pieceAt(xi + 1, yi - 1).side() != this.turn) {
  					this.validCapture = true;
  					return true;
  				}
  			}
  		} else if (((yi - 2) == yf) && ((xi - 2) == xf)) {
  			if (this.pieceAt(xi - 1, yi - 1) != null) { 
                if (this.pieceAt(xi - 1, yi - 1).side() != this.turn) {
                	this.validCapture = true;
  					return true;
  				}
  			}
  		}
  		return false;
   	}

  	private boolean validMove(int xf, int yf) {
  		int xi = this.selectedLocation[0];
  		int yi = this.selectedLocation[1];
  		if (this.selected.isFire()) {
  			if (this.upMove(xi, yi, xf, yf)) {
  				return true;
  			}
  		} else {
  			if (this.downMove(xi, yi, xf, yf)) {
  				return true;
  			}
  		}
  		if (this.selected.isKing()) {
  			if (this.selected.isFire()) {
  				if (this.downMove(xi, yi, xf, yf))
  					return true;
  			} else {
  				if (this.upMove(xi, yi, xf, yf)) {
  					return true;
  				}
  			}
  		}
  		return false;
  	}

  public boolean canSelect(int x, int y) {
  	if ((x < 0) || (y < 0) || (x > N) || (y > N)) {
  		return false;
  	} else if (this.selected == null) {
  		if (!(this.hasMoved)) {
  			if (this.pieceAt(x, y) != null) {
  				if (this.pieceAt(x, y).side() != this.turn) {
  					return false;
  				} else {
  					return true;
  				}
  			}
  		}
  		return false;
  	} else {
  		if (!(this.hasMoved)) {
  			if (this.pieceAt(x, y) != null) {
  				if (this.pieceAt(x, y).side() == this.turn) {
  					this.selected = null;
  					return true;
  				}
  			} else if (this.validMove(x, y)) {
  				return true;
  			}
  			return false;
  		} else if (this.selected.hasCaptured()) {
  			this.validCapture = false;
  			if (this.validMove(x, y)) {
  				if (this.validCapture) {
  					return true;
  				}
  			}
  		}
  	}
  	return false;
  }

   	public Piece remove(int x , int y) {
   		if ((x < 0) || (y < 0) || (x > N) || (y > N)) {
   			System.out.println("Error (" + x + ", " + y + ") is out of bounds!");
  			return null;
  		} else if (this.pieceAt(x, y) == null) {
  			System.out.println("No piece at (" + x + ", " + y + ")!");
  			return null;
  		} else {
   			Piece temp = pieceAt(x, y);
   			this.place(null, x, y);
   			return temp;
   		}
   }

   public boolean canEndTurn() {
   	if (this.hasMoved) {
   		return true;
   	} else if (this.selected != null) {
   		if (this.selected.hasCaptured()) {
   			return true;
   		}
   	}
   	return false;
   }

    public void endTurn() {
    	if (this.canEndTurn()) {
   			if (this.selected != null) {
   				this.selected.doneCapturing();
   				this.selected = null;
   			}
   			this.validCapture = false;
   			this.hasMoved = false;
   			if (this.turn == 0) {
   				this.turn = 1;
   			} else {
   				this.turn = 0;
   			}
   		}
   	}
   
   	public String winner() {
   		int fire = 0;
   		int water = 0;
   		Piece compare = null;
   		this.noWinner = false;
   		for (int i = 0; i < N; i++) {
       	     for (int j = 0; j < N; j++) {
        	    	compare = this.pieceAt(i, j);
            	  	if (compare != null) {
              			if (compare.isFire()) {
              				fire = fire + 1;
              			} else if (!(compare.isFire())) {
              				water = water + 1;
              			}
        			}
    	    	}
	    	}
	    	if ((fire == 0) && (water == 0)) {
	    		return "No one";
	    	} else if ((fire != 0) && (water != 0)) {
	    		this.noWinner = true;
	    		return null;
	    	} else if ((fire != 0) && (water == 0)) {
	    		return "Fire";
	    	} else if ((water != 0) && (fire == 0)) {
	    		return "Water";
	    	}
	    	this.noWinner = true;
	    	return null;
   		}

	 public Board(boolean shouldBeEmpty) {
	 	 for (int i = 0; i < N; i++) {
        	 for (int j = 0; j < N; j++) {
           		 if ((i + j) % 2 == 0) {
           		   if (!shouldBeEmpty) {
           			if ((i + j) % 2 == 0) {
           				switch (j) {
                    case 0: this.place(new Piece(true, this, i, j, "pawn"), i, j);
                            break;
                    case 1: this.place(new Piece(true, this, i, j, "shield"), i, j);
                            break;
                    case 2: this.place(new Piece(true, this, i, j, "bomb"), i, j);
                            break;
                    case 5: this.place(new Piece(false, this, i, j, "bomb"), i, j);
                            break;
                    case 6: this.place(new Piece(false, this, i, j, "shield"), i, j);
                            break;
                    case 7: this.place(new Piece(false, this, i, j, "pawn"), i, j);
                            break;
                    default: break;
           				 }
         		    }
              	}
   		    }
   	     }
     	}
    }

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, N);
   		StdDrawPlus.setYscale(0, N);
    	Board b = new Board(false);
    	while (b.noWinner) {
     	    b.drawBoard();
     	 	if (StdDrawPlus.mousePressed()) {
        		int x = (int) StdDrawPlus.mouseX();
        		int y = (int) StdDrawPlus.mouseY();
        		b.fillSquare(x, y);
        		if (b.canSelect(x, y)) {
          			b.select(x,y);
           		}
        	} else if (StdDrawPlus.isSpacePressed()) {
       				b.endTurn();
       			String win = b.winner();
       			if (win != null) {
       				b.noWinner = false;
       			}
        	}
        	StdDrawPlus.show(100);
    	}
	}
}