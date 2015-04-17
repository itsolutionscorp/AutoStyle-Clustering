public class Board {
	/** Location of pieces. */
    private Piece[][] piecesObjects = new Piece[8][8];
    private boolean isFireTurn = true;
    private int previouslySelectedLocationX = 999;
    private int previouslySelectedLocationY = 999;
    private Piece selectedPiece;
    private int numberOfMoves = 0;
    
    public static void main(String[] args) {
    	Board board = new Board(false);
    	
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	board.redrawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) {
                    board.select((int) x, (int) y);
                }
            }            
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            		board.winner();
            	}
            }
          
            StdDrawPlus.show(100);
        }
    }
    
    /* 
	* The constructor for Board. 
	* If shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes a Board with the default configuration. 
	* Note that an empty Board could possibly be useful for testing purposes.
    */
    public Board (boolean shouldBeEmpty) {
    	setupBoard(shouldBeEmpty);
    }
    
    private void setupBoard (boolean shouldBeEmpty) {
        for (int x = 0; x < 8; x++) {
        	for (int y = 0; y < 8; y++) {
                if (!shouldBeEmpty) {
                	//firePiecesRemaining = 12;
                	//waterPiecesRemaining = 12;
                	if (y < 3 || y > 4) {
            			boolean evenRows = y % 2 == 0 && x % 2 == 0;
            			boolean oddRows = y % 2 != 0 && x % 2 != 0;
            			
                		if (evenRows || oddRows) {
                			String type = "";
                			boolean isFire = y < 3;
                			if (y == 0 || y == 7) {
                				type = "pawn";
                			} else if (y == 1 || y == 6) {
                				type = "shield";
                			} else if (y == 2 || y == 5){
                				type = "bomb";
                			}
                			piecesObjects[x][y] = new Piece(isFire, this, x, y, type);
                		}
            		} 
            	} else {
            		//test kinging
//            		Piece toBeKing = new Piece(true, this, 0, 6, "pawn");
//            		place(toBeKing, 0, 6);
//            		
//            		Piece dummyWater = new Piece(false, this, 2, 6, "pawn");
//            		place(dummyWater, 2, 6);
//            		
//            		Piece dummyWater2 = new Piece(false, this, 4, 6, "pawn");
//            		place(dummyWater2, 4, 6);
//            		
//            		Piece dummyWater3 = new Piece(false, this, 4, 4, "pawn");
//            		place(dummyWater3, 4, 4);
//            		
//            		Piece dummyWater4 = new Piece(false, this, 6, 4, "pawn");
//            		place(dummyWater4, 6, 4);
            	}
        	}
        }
    }
    
    public Piece pieceAt(int x, int y) {
    	if (x < 8 && x >= 0 && y < 8 && y >= 0) {
        	return piecesObjects[x][y];
    	} else {
    		return null;
    	}
    }
    
    public boolean canSelect(int x, int y) {
    	Piece piece = pieceAt(x, y);
    	if (piece != null) {
    		if (piece.isFire() == isFireTurn) {
    			boolean validLocation = numberOfMoves == 0;
        		if (selectedPiece == null || validLocation) {
        	    	return true;
        		}
        	}
    	} else if ((x + y) % 2 == 0){
    		if (selectedPiece != null) {
    			boolean validLocation = numberOfMoves == 0;
    			
    			//handles multi capture
    			int deltaSpaces;
    	    	if (isFireTurn) {
    	    		deltaSpaces = y - previouslySelectedLocationY;
    	    	} else {
    	    		deltaSpaces = previouslySelectedLocationY - y;
    	    	}
    	    	
    	    	if (selectedPiece.isKing()) {
    	    		deltaSpaces = Math.abs(deltaSpaces);
    	    	}
    	    	if (deltaSpaces == 2) {
    	    		for (int _x = x - 1; _x <= x + 1; _x = _x + 2) {
    					for (int _y = y - 1; _y <= y + 1; _y = _y + 2) {
    						Piece p = pieceAt(_x, _y);
    						if (p != null && p.isFire() != selectedPiece.isFire()) {
    							validLocation = true;
    						}
    					}
    				}
    	    	}
    	    	
    			if (validLocation) {
    				if (validMove(previouslySelectedLocationX, previouslySelectedLocationY, x, y)) {
        		    	return true;
        			}
    			}
    		}
    	} 
    	return false;
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {    	
    	//handle king shit
    	int spacesForward;
    	if (isFireTurn) {
    		spacesForward = yf - yi;
    	} else {
    		spacesForward = yi - yf;
    	}
    	Piece p = pieceAt(xi, yi);
    	if (p != null && p.isKing()) {
    		spacesForward = Math.abs(spacesForward);
    	}
    	
    	boolean capturing = false;
    	if (spacesForward == 2) {
    		int yInBetween = (yf + yi)/2;
    		int xInBetween = (xf + xi)/2;
    		Piece pieceInBetween = pieceAt(xInBetween, yInBetween);
    		if (pieceInBetween != null && pieceInBetween.isFire() != isFireTurn) {
    			capturing = true;
    		} else {
    			capturing = false;
    		}
    	}
    	if ((xf + yf) % 2 == 0 && spacesForward == 1 || capturing) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void select(int x, int y) {
    	
        Piece pieceAtSelectedLocation = pieceAt(x, y);
        if (pieceAtSelectedLocation == null && selectedPiece != null) {
        	Piece removedPiece = remove(previouslySelectedLocationX, previouslySelectedLocationY);

        	selectedPiece.move(x, y);
        	numberOfMoves = numberOfMoves + 1;
        } else {
        	selectedPiece = pieceAtSelectedLocation;
        }
        
        previouslySelectedLocationX = x;
    	previouslySelectedLocationY = y;
    }
    
    public void place(Piece p, int x, int y) {
    	boolean withinBounds = x >= 0 && x < 8 && y >= 0 && y < 8;
    	if (withinBounds && p != null) {
        	piecesObjects[x][y] = p;
    	}
    }
    
    public Piece remove(int x, int y) {
    	Piece p = pieceAt(x, y);
    	piecesObjects[x][y] = null;
    	return p;
    }
    
    public boolean canEndTurn() {
    	if (numberOfMoves > 0) {
        	return true;
    	} else {
        	return false;
    	}
    }
    
    public void endTurn() {          
    	isFireTurn = !isFireTurn;
    	numberOfMoves = 0;
    	previouslySelectedLocationX = 999;
    	previouslySelectedLocationY = 999;
    	selectedPiece.doneCapturing();
    }
    
    private void redrawBoard() {
    	int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
    	for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
            	if ((x + y) % 2 != 0) {
                  	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                
                if (pieceAt(previouslySelectedLocationX, previouslySelectedLocationY) != null) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(previouslySelectedLocationX + .5, previouslySelectedLocationY + .5, .5);
            	}

                if (piecesObjects[x][y] != null) {
                	Piece piece = piecesObjects[x][y];
                	String name = "";
                	String type = "";
                	if (piece.isBomb()) {
                		type = "bomb";
                	} else if (piece.isShield()) {
                		type = "shield";
                	} else {
                		type = "pawn";
                	}
                	
                	if (piece.isFire()) {
                		name = type + "-fire";
                	} else {
                		name = type + "-water";
                	}
                	
                	if (piece.isKing()) {
                		name = name + "-crowned";
                	}
                	
                    StdDrawPlus.picture(x + .5, y + .5, "img/" + name + ".png", 1, 1);
                }
            }
        }
    }
    
    public String winner() {
    	int firePiecesRemaining = 0;
    	int waterPiecesRemaining = 0;
    	
    	for (int x = 0; x < 8; x++) {
    		for (int y = 0; y < 8; y++) {
    			Piece p = pieceAt(x, y);
    			if (p != null) {
    				if (p.isFire()) {
						firePiecesRemaining = firePiecesRemaining + 1;
					} else {
						waterPiecesRemaining = waterPiecesRemaining + 1;
					}
					
				}
    		}
    	}
    	
    	if (waterPiecesRemaining <= 0 && firePiecesRemaining <= 0) {
    		return "No one";
    	}
    	
    	if (waterPiecesRemaining == 0) {
			return "Fire";
		} else if (firePiecesRemaining == 0) {
			return "Water";
		} else {
			return null;
		}
    }
}
