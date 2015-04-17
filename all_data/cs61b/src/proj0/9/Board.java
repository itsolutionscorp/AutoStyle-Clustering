public class Board {
	private Piece[][] pieces;
	private String playerTurn = "fire";
	private int selectedPieceX;
	private int selectedPieceY;
	private int designatedX;
	private int designatedY;
	private boolean hasMoved = false;
	private boolean hasSelected = false;
	private boolean hasBeenCaptured = false;
	//private String winner = "";
	private int middleX;
	private int middleY;
	private int z = 0;
	private int numbFire = 12;
	private int numbWater = 12;
	private int firePlaceCount = 0;
	private int fireRemoveCount = 0;
	private int waterPlaceCount = 0;
	private int waterRemoveCount = 0;
	private String winner = null;
	private boolean bombExploded = false;
	/* 
	 * starts a GUI supported version of the game.
	 */
	public static void main(String[] args) {	
		Board c = new Board(false);
		StdDrawPlus.setXscale(0, 8);
     	StdDrawPlus.setYscale(0, 8);
          c.drawBoard();
          //System.out.println("beforedrawingwhite");
          while (c.winner() == null ) {
          	//System.out.println("before true");
          	
          		//c.drawBoard();
	          	if (StdDrawPlus.mousePressed()) {
	          		System.out.println("mouse has pressed");
	          		
		               double x = StdDrawPlus.mouseX();
		               double y = StdDrawPlus.mouseY();               
		               //c.select((int) x, (int) y);
		      

		               int intX = (int) x;
		               int intY = (int) y;
		               
			               if (c.canSelect(intX, intY)) {
			               	c.hasSelected = true;
			               	
			               	System.out.println("entered canSelect");
			               	c.select(intX, intY);
				               c.drawBoard();

				               StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				               StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
				               if (c.pieceAt(intX, intY) != null) {
				               	StdDrawPlus.picture((int) x + .5, (int) y + .5, "img/" + c.imageConcat(intX, intY) + ".png", 1, 1);
				               	
				               } 
				               // else if (c.pieceAt(intX, intY) == null) {
				               // 	c.endTurn();
				               // }	

				               System.out.println("draawwwwwww");
			               
			          	}
			          
	               }
	               if (StdDrawPlus.isSpacePressed()) { 
	               	System.out.println("space is pressed"); 
		               if (c.canEndTurn()) { 			               
			               	c.endTurn();
			               	
			               	System.out.println("ended turn");

			               
		          	}
	          	} 
	          	if (c.winner() != null) {
	          		System.out.println("winner = " + c.winner);
	        			
	        			c.drawBoard();
	          		break;
	          	}
	          	StdDrawPlus.show(150);
 	         	
          	
	     }
	}
	/*
	 * Drawing board by pulling info from pieces array
	 */
	private void drawBoard() {
	
		for (int i = 0; i < 8; i = i + 1) {
            	for (int j = 0; j < 8; j = j + 1) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	//StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                	if (pieces[i][j] != null) {  
                	//System.out.println("not null");                      	
                		StdDrawPlus.picture(i + .5, j + .5, "img/" + imageConcat(i, j) + ".png", 1, 1);
        			} else if (pieces[i][j] == null) {
        				//System.out.println("is null");
        			}

        	}
		}
	}

	private String imageConcat(int i, int j) {
		String image = "";
		//for (int i = 0; i < 8; i = i + 1) {
            	//for (int j = 0; j < 8; j = j + 1) {
            		if (pieces[i][j] == null) {
            			//System.out.println("is null.");
            		}
				else if (pieces[i][j] != null) {
					//System.out.println("is not null");
					if (pieces[i][j].isFire()) {
	            		//if (true) {
			    			image = "fire";
				    		if (pieces[i][j].isBomb()) {
				    			image = "bomb-" + image;
				    		} else if (pieces[i][j].isShield()) {
				    			image = "shield-" + image;
				    		} else {
				    			image = "pawn-" + image;
				    		}
				    		//System.out.println(image);
				    	} else {
				    		image = "water";
				    		if (pieces[i][j].isBomb()) {
				    			image = "bomb-" + image;
				    		} else if (pieces[i][j].isShield()) {
				    			image = "shield-" + image;
				    		} else {
				    			image = "pawn-" + image;
				    		}
				    		//System.out.println(image);
		    			}
		    			if (pieces[i][j].isKing()) {
		    				image = image + "-crowned";
		    				System.out.println("has been crowned");
		    				System.out.println("is it still bomb + " + pieces[i][j].isBomb());
		    			}	
		    		}
		    		//System.out.println(image);	
	    		//}
    		//}	
    	return image;
	}
	
	/*
 	 * The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. 
 	 * Otherwise, initializes a Board with the default configuration. 
 	 * Note that an empty Board could possibly be useful for testing purposes. creating pieces array here.
 	 */
	public Board(boolean shouldBeEmpty) {
		//playerTurn = "fire"; 
		pieces = new Piece[8][8];
		if (shouldBeEmpty) {	
		} else if (!shouldBeEmpty) {
			//Piece[][] pieces = new Piece[8][8];
			for (int i = 0; i < 8; i = i + 1) {
				for (int j = 0; j < 8; j = j + 1) {
					if (j == 0 && (i % 2 == 0)) {
						Piece firePawn = new Piece(true, this, i, j, "pawn");
						pieces[i][j] = firePawn;
					}
					if (j == 1 && (i % 2 == 1) && i > 0) {
						Piece fireShield = new Piece(true, this, i, j, "shield");
						pieces[i][j] = fireShield;
					}
					if (j == 2 && (i % 2 == 0)) {
						Piece fireBomb = new Piece(true, this, i, j, "bomb");
						pieces[i][j] = fireBomb;
					}
					if (j == 5 && (i % 2 == 1) && i > 0) {
						Piece waterBomb = new Piece(false, this, i, j, "bomb");
						pieces[i][j] = waterBomb;
					}
					if (j == 6 && (i % 2 == 0)) {
						Piece waterShield = new Piece(false, this, i, j, "shield");
						pieces[i][j] = waterShield;
					}
					if (j == 7 && (i % 2 == 1) && i > 0) {
						Piece waterPawn = new Piece(false, this, i, j, "pawn");
						pieces[i][j] = waterPawn;
					}
					//System.out.println(pieces[i][j]);
					//System.out.println(i);
					//System.out.println(j);
				}
			}
		}			
	}


	public Piece pieceAt(int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0) {
			return pieces[x][y];
		} else {
			return null;
		}
	}	

		
	public void place(Piece p, int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0 && p != null) {
			pieces[x][y] = p;
			if (p.isFire()) {
				firePlaceCount = firePlaceCount + 1;
			} else if (!p.isFire()) {
				waterPlaceCount = waterPlaceCount + 1;
			}		
		}
		numbWater = numbWater + waterPlaceCount;
		numbFire = numbFire + firePlaceCount;
		firePlaceCount = 0;
		waterPlaceCount = 0;
	}

	public Piece remove(int x, int y) {
		Piece piece = null;
		if (x < 8 && y < 8 && x >= 0 && y >= 0) {
		piece = pieces[x][y];
		pieces[x][y] = null;
			if (piece.isFire()) {
				fireRemoveCount = fireRemoveCount + 1;
			} else if (!piece.isFire()) {
				waterRemoveCount = waterRemoveCount + 1;
			}
		} else if (pieces[x][y] == null) {
			System.out.println("No piece to remove");
			return null;
		} else if (x >= 8 || x < 0 || y >= 8 || y < 0) {
			System.out.println("Piece to remove out of bounds");
			return null;
		}
		numbWater = numbWater - waterRemoveCount;
		numbFire = numbFire - fireRemoveCount;
		fireRemoveCount = 0;
		waterRemoveCount = 0;

		return piece;

	}

	public boolean canSelect(int x, int y) {
		boolean z = false;
			if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			
				//System.out.println("playerTurn = " + playerTurn);
				if (playerTurn.equals("fire")) {
					if (pieces[x][y] != null && pieces[x][y].isFire()) {				
							if (hasSelected && (!hasMoved)) {						
								z = true;						
							} else if (!hasSelected) {									
								z = true;
							}
					} else if (pieces[x][y] == null && hasSelected && !hasMoved && validMove(selectedPieceX, selectedPieceY, x, y)) {
							z = true;
					} else if (pieces[x][y] == null && hasSelected && /* pieces[selectedPieceX][selectedPieceY].hasCaptured()*/hasBeenCaptured 
						&& validMove(selectedPieceX, selectedPieceY, x, y) && (Math.abs(selectedPieceX - x) == 2)) {
							z = true;
					} else {
							z = false;
					}
				} else if (!playerTurn.equals("fire")) {
					if (pieces[x][y] != null && (!pieces[x][y].isFire())) {
						if (hasSelected && (!hasMoved)) {					
							z = true;						
						} else if (!hasSelected) {					
							z = true;
						}
					} else if (pieces[x][y] == null && hasSelected && !hasMoved && validMove(selectedPieceX, selectedPieceY, x, y)) {
						System.out.println("has selected null space");
						z = true;
					} else if (pieces[x][y] == null && hasSelected && /* pieces[selectedPieceX][selectedPieceY].hasCaptured()*/ hasBeenCaptured
						&& validMove(selectedPieceX, selectedPieceY, x, y) && (Math.abs(selectedPieceX - x) == 2)) {
						z = true;
					} else {
						z = false;
					}
				} else {
					z = false;
				}
				System.out.println("z  = " + z);
			}
				return z;
		}
		
	

	private boolean validMove(int xi, int yi, int xf, int yf) {
		System.out.println("xi = " + xi);
		System.out.println("yi = " + yi);
		System.out.println("xf = " + xf);
		System.out.println("yf = " + yf);
		System.out.println("entered validMove");
		boolean x = false;
		int xmiddle, ymiddle;
		xmiddle = (xi + xf) / 2;
		ymiddle = (yi + yf) / 2;
		System.out.println("Pieces middle = " + pieces[xmiddle][ymiddle]);
		System.out.println("initial pieces = " + pieces[xi][yi]);
		if (xi >= 0 && xi < 8 && yi >= 0 && yi < 8 && xf >= 0 && xf < 8 && yf >= 0 && yf < 8) {
			if (pieces[xi][yi].isKing()) {
				System.out.println("isKing");
				if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
					x = true;
				} else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
					if (pieces[xmiddle][ymiddle] == null || (pieces[xmiddle][ymiddle].isFire() && (pieces[xi][yi].isFire())) || (!pieces[xmiddle][ymiddle].isFire() && (!pieces[xi][yi].isFire()))) {
						x = false;
					} else if (pieces[xmiddle][ymiddle].isFire() ^ (pieces[xi][yi].isFire())) {
						x = true;
					} 
				}
			} else if (!pieces[xi][yi].isKing() && Math.abs(xf-xi) == 2 && (yf - yi) == 2 && pieces[xi][yi].isFire() && pieces[xmiddle][ymiddle] != null && !pieces[xmiddle][ymiddle].isFire()) {
				x = true;
				System.out.println("entereed correct");
			} else if (!pieces[xi][yi].isKing() && Math.abs(xf-xi) == 1 && (yf - yi) == 1 && pieces[xi][yi].isFire()) {
				x = true;
			} else if (!pieces[xi][yi].isKing() && Math.abs(xf-xi) == 2 && (yf - yi) == -2 && !pieces[xi][yi].isFire() && pieces[xmiddle][ymiddle] != null && pieces[xmiddle][ymiddle].isFire()) {
				x = true;
			} else if (!pieces[xi][yi].isKing() && Math.abs(xf-xi) == 1 && (yf - yi) == -1 && !pieces[xi][yi].isFire()) {
				x = true;
			} else {
				System.out.println("entered else");
				x = false;
			}

				

		
		
		}
		return x;
	}

	public void select(int x, int y) {
			if (pieces[x][y] != null) {
			selectedPieceX = x;
			selectedPieceY = y;
			//hasSelected = true;
			} else if (pieces[x][y] == null) {
				// check whether checked something has been selected already;
				designatedX = x;
				designatedY = y;
				//System.out.println("desigx = " + designatedX);
				
				pieces[selectedPieceX][selectedPieceY].move(designatedX, designatedY);
				if (Math.abs(designatedX - selectedPieceX) == 2) {
					hasBeenCaptured = true;
				}
				//hasSelected = true;
				selectedPieceX = designatedX;
				selectedPieceY = designatedY;
				hasMoved = true;
				System.out.println("set hasMoved to true");
				if (pieces[selectedPieceX][selectedPieceY] == null) {
					bombExploded = true;
				}
			} 

		//numbWater = numbWater - waterRemoveCount + waterPlaceCount;
		//numbFire = numbFire - fireRemoveCount + firePlaceCount; 

	}

	public boolean canEndTurn() {
		boolean e = false;
		if (selectedPieceX != 9 && selectedPieceY != 9) {
			System.out.println("enter canendturn loop");
			System.out.println("has Moved = " + hasMoved);
			if (hasMoved || hasBeenCaptured) {
				System.out.println("canEndTurn verified");
				e = true;
			} else {
				e = false;
			}
		}return e;
	}

	public void endTurn() {
		if (canEndTurn()) {
			String turn = "";
			z = z + 1;
			if (z % 2 == 0) {
				turn = "fire";
			} else if (z % 2 == 1) {
				turn = "water";
			}
			playerTurn = turn;
			System.out.println("selectedpiecey = " + selectedPieceY + "selectedPieceX = " + selectedPieceX);
			System.out.println("pieces = " + pieces[selectedPieceX][selectedPieceY]);
			hasMoved = false;
			hasSelected = false;
			System.out.println(z);
			selectedPieceX = 9;
			selectedPieceY = 9;
			designatedX = 9;
			designatedY = 9;
			System.out.println("playerTurn = " + playerTurn);
			hasBeenCaptured = false;
		}
		//numbWater = numbWater - waterRemoveCount + waterPlaceCount;
		//numbFire = numbFire - fireRemoveCount + firePlaceCount;

	}

	public String winner() {
		winner = null;
		//numbWater = numbWater - waterRemoveCount + waterPlaceCount;
		//numbFire = numbFire - fireRemoveCount + firePlaceCount;
		if (numbWater != 0 && numbFire != 0) {
			winner = null;
		} else if (numbWater != 0 && numbFire <= 0) {
			winner = "Water";
		} else if (numbWater <= 0 && numbFire != 0) {
			winner = "Fire";
		} else if (numbWater <= 0 && numbFire <= 0) {
			winner = "No one";
		}
		//System.out.println("numbWater + numbFire = " + numbWater + " " +  numbFire);
		return winner;
	}

	
}	

	