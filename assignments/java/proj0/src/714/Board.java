// Author: @Bryan Alcorn

public class Board {

	private int boardSize;
	private Piece[][] pieceList;
	private boolean fireTurn;
	private boolean playerCaptured;
	private boolean playerMoved;
	private boolean playerSelected;
	private int xofContains;
	private int yofContains;
	private int xofSelected;
	private int yofSelected;
	private int fireNum;
	private int waterNum;



	public static void main(String args[]) {
		Board gameBoard = new Board(false);

		gameBoard.draw();

		while(gameBoard.winner() == null) {

			if ((StdDrawPlus.isSpacePressed()) && gameBoard.canEndTurn()) {
				gameBoard.endTurn();

			} else if (gameBoard.playerSelected) {
				if (StdDrawPlus.mousePressed()) {
					double xMove = StdDrawPlus.mouseX();
                	double yMove = StdDrawPlus.mouseY();
                	if ((gameBoard.canSelect((int) xMove, (int) yMove))) {
                		gameBoard.select((int) xMove, (int) yMove);
                		gameBoard.draw();
                	}
				}

			} else if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
               	double y = StdDrawPlus.mouseY();
               	if (gameBoard.canSelect((int) x, (int) y)) {
               		gameBoard.select((int) x, (int) y);
               	}
			}
		StdDrawPlus.show(100);
		}

	}

	private void draw() {

		StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);
		for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
            if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
         }}

        String kingImg = "";
        String fireWater = "";

		for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
        			if (pieceAt(i, j) != null) {
        				if (pieceAt(i,j).isKing()) {
        					kingImg = "-crowned";
        				}
        				if (pieceAt(i,j).side() == 0) {
        					fireWater = "fire";
        				} else {
        					fireWater = "water";
        				}

        				if (pieceAt(i,j).isShield()) {
        					StdDrawPlus.picture(i +.5, j + .5, "img/shield-" + fireWater + kingImg + ".png", 1, 1);
        				} else if (pieceAt(i, j).isBomb()) {
        					StdDrawPlus.picture(i +.5, j + .5, "img/bomb-" + fireWater + kingImg + ".png", 1, 1);
        				} else {
        					StdDrawPlus.picture(i +.5, j + .5, "img/pawn-" + fireWater + kingImg + ".png", 1, 1);
        				}
        				kingImg = "";
        				fireWater = "";
        			}			
        }}
	} 

	public Board(boolean shouldBeEmpty) {
		boardSize = 8;
		pieceList = new Piece[boardSize][boardSize];
		fireTurn = true;
		playerCaptured = false;
		playerMoved = false;
		playerSelected = false;
		fireNum = 0;
		waterNum = 0;
		
		if (!shouldBeEmpty) {
			fireNum = 12;
			waterNum = 12;
			for (int i = 0; i < boardSize; i+=2) {
        		pieceList[i][2] = new Piece(true, this, i, 2, "bomb-fire");
			}
       	
       		for (int i = 1; i < boardSize; i+=2) {
        		pieceList[i][1] = new Piece(true, this, i, 1, "shield-fire");
			}	
   	
       		for (int i = 0; i < boardSize; i+=2) {
        		pieceList[i][0] = new Piece(true, this, i , 0, "pawn-fire");
        	}

       		for (int i = 1; i < boardSize; i+=2) {
        		pieceList[i][7] = new Piece(false, this, i, 7, "pawn-water");
        	}

       		for (int i = 0; i < boardSize; i+=2) {
        		pieceList[i][6] = new Piece(false, this, i, 6, "shield-water");
        	}

       		for (int i = 1; i < boardSize; i+=2) {
        		pieceList[i][5] = new Piece(false, this, i, 5, "bomb-water");
        	}
		}

		   
	}

	public Piece pieceAt(int x, int y) {
		if (withinRange(x,y)) {
			return pieceList[x][y];
		} else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (withinRange(x,y)) {

			if (playerMoved && !playerCaptured) {
				return false;
			}

			if (pieceAt(x,y) == null) {
				if (playerSelected) {
					return validMove(xofSelected, yofSelected, x, y); 
				} else {
					return false;
				}
				
			}
			if (other(true, x,y)) {
				if (playerSelected && (!playerCaptured)) {
					return true;
				} else if (!playerSelected) {
					return true;
				}
				return false;
				
			}
			return false;
		} return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		int diffX = xf - xi;
		int diffY = yf - yi;

		if (pieceAt(xf, yf) != null || pieceAt(xi, yi) == null) {
			return false;
		}
		if (Math.abs(diffX) != 2 && playerCaptured) {
			return false;
		}
		if (!pieceAt(xi, yi).isKing()) {
			if (pieceAt(xi,yi).isFire()) {
				if (diffY <= 0) {
					return false;
				}
			} else {
				if (diffY >= 0) {
					return false;
				}
			}
		}

		if (Math.abs(diffX) > 2 || Math.abs(diffY) > 2) {
			return false;
		}

		if (withinRange(xi, yi) && withinRange(xf, yf)) {

			if (Math.abs(diffX) == Math.abs(diffY)) {

				if (Math.abs(diffX) == 2 && pieceAt(xi + diffX/2, yi + diffY/2) != null && other(false, xi + diffX/2, yi + diffY/2)) {
						return true;
					} 
				else if (Math.abs(diffX) == 1) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}
	} 

	public void select(int x, int y) {
		if (withinRange(x,y)) {
			if (playerSelected && pieceAt(x, y) == null) {
				pieceAt(xofSelected, yofSelected).move(x,y);
				playerMoved = true;
				xofSelected = x;
				yofSelected = y;

				if (pieceAt(xofSelected, yofSelected) != null && (pieceAt(xofSelected, yofSelected).hasCaptured()))  {
					playerCaptured = true; 
				}

			} else {
				playerSelected = true;
				xofSelected = x;
				yofSelected = y;
			}
		}
		 
 			
	}
	public void place(Piece p, int x, int y) {
		if (withinRange(x, y) && p != null) {
			if (contains(p)) {
				remove(xofContains, yofContains).move(x,y);
			} else {
				if (p.side() == 1) {
					waterNum += 1;
				} else {
					fireNum += 1;
				}
				pieceList[x][y] = p;
			}
			
		}
		
	}
		
	public Piece remove(int x, int y) {
		if (withinRange(x, y)) {
			if (pieceAt(x, y) == null) {
				return null;
			} else {
				if (pieceAt(x,y).side() == 1) {
					waterNum -= 1;
				} else {
					fireNum -= 1;
				}
				Piece removed = pieceAt(x,y);
				pieceList[x][y] = null;
				return removed;
			}

		} else {
			return null;
		}
		
	}

	public boolean canEndTurn() {
		if (playerMoved || playerCaptured) {
			return true;
		} else {
			return false;
		}
		

	}
	public void endTurn() {
		if (fireTurn) {
			fireTurn = false;
		} else {
			fireTurn = true;
		}
		playerMoved = false;
		playerSelected = false;
		if (playerCaptured) {
			pieceAt(xofSelected, yofSelected).doneCapturing();
		}
		playerCaptured = false;
		
	}
		
	public String winner() {
			if (fireNum > 0 && waterNum == 0) {
				return "Fire";
			} else if (fireNum == 0 && waterNum > 0) {
				return "Water";
			} else if (fireNum == 0 && waterNum == 0) {
				return "No one";
			} else {
				return null;
			}
	}

	private boolean withinRange(int x, int y) {
		if (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
			return true;
		} else 
			return false;
	}

	private boolean contains(Piece copycat) {
		if (copycat == null) {
			return false;
		} 
		for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
        	if (pieceAt(i, j) == copycat) {
        		xofContains = i;
        		yofContains = j;
        		return true;
        	}
        }}
        return false;
	}

	private boolean other(boolean same, int x, int y) {
		if (withinRange(x,y) && (pieceAt(x,y) != null)) {
			if (fireTurn) {
				if (!same) {
					if (pieceAt(x,y).side() == 1) {
						return true;
					} else {
					return  false;
					}
				} else {
					if (pieceAt(x,y).side() == 0) {
						return true;
					} else {
					return  false;
					}
				}
				
			} else {
				if (!same) {
					if (pieceAt(x,y).side() == 0) {
						return true;
					} else {
					return  false;
					}
				} else {
					if (pieceAt(x,y).side() == 1) {
						return true;
					} else {
					return  false;
					}
				}
			}
		} else {
			return false;
		}
	}
		
}
