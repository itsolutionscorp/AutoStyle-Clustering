public class Board {
    /* Board(boolean shouldBeEmpty) - The constructor for Board. If 
    shouldBeEmpty is true, initializes an empty Board. Otherwise, 
    initializes a Board with the default configuration. Note that an 
    empty Board could possibly be useful for testing purposes. */
    public Board(boolean shouldBeEmpty) {
    	drawBoard();
    	if (shouldBeEmpty == true) {
	 	}
	 	else {
	 		drawPieces();
	 	}
    }

    private static Piece piecePos[][] = new Piece[8][8];
    private int player = 0;
    private Piece selectedPiece, capturedPiece, bombPiece;
    private boolean hasGone = false, captured = false, bomb = false;
	private String fireB = "img/bomb-fire.png"           , fireP = "img/pawn-fire.png",
		 		   fireS = "img/shield-fire.png"         , fireBK = "img/bomb-fire-crowned.png",
		 		   firePK = "img/pawn-fire-crowned.png"  , fireSK = "img/shield-fire-crowned.png",
		 		   waterB = "img/bomb-water.png"         , waterP = "img/pawn-water.png", 
		 		   waterS = "img/shield-water.png"       , waterBK = "img/bomb-water-crowned.png", 
		 		   waterPK = "img/pawn-water-crowned.png", waterSK = "img/shield-water-crowned.png";    

    private void drawPiece(Piece p) {
    	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    	StdDrawPlus.filledSquare(p.xPos + .5, p.yPos + .5, .5);
    	StdDrawPlus.picture(p.xPos + .5, p.yPos + .5, p.name, 1, 1);
    }
    private void drawPieces() {
    	for(int xCoord = 0; xCoord < 8; xCoord += 2) {
        	piecePos[xCoord][0] = new Piece(true, this, xCoord, 0, "img/pawn-fire.png");
        	drawPiece(piecePos[xCoord][0]);
        }
        for(int xCoord = 1; xCoord < 8; xCoord += 2) {
        	piecePos[xCoord][1] = new Piece(true, this, xCoord, 1, "img/shield-fire.png");
        	drawPiece(piecePos[xCoord][1]);
        }
        for(int xCoord = 0; xCoord < 8; xCoord += 2) {
           	piecePos[xCoord][2] = new Piece(true, this, xCoord, 2, "img/bomb-fire.png");
           	drawPiece(piecePos[xCoord][2]);
        }
        for(int xCoord = 1; xCoord < 8; xCoord += 2) {
          	piecePos[xCoord][5] = new Piece(false, this, xCoord, 5, "img/bomb-water.png"); 
          	drawPiece(piecePos[xCoord][5]);
        }
        for(int xCoord = 0; xCoord < 8; xCoord += 2) {
            piecePos[xCoord][6] = new Piece(false, this, xCoord, 6, "img/shield-water.png");
            drawPiece(piecePos[xCoord][6]);
        }
        for(int xCoord = 1; xCoord < 8; xCoord += 2) {
           	piecePos[xCoord][7] = new Piece(false, this, xCoord, 7, "img/pawn-water.png");
           	drawPiece(piecePos[xCoord][7]);
        }
    }


    private static void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
    /* starts a GUI supported version of the game. */
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        piecePos = new Piece[N][N];
        Board thisBoard = new Board(false);
        while(true) {
            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (thisBoard.canSelect((int) x, (int) y) == true) {
                	thisBoard.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed() == true) {
                	if (thisBoard.canEndTurn() == true) {
                		thisBoard.endTurn();
                }
            }

            StdDrawPlus.show(100);
        }
    }

    /* Gets the piece at position (x, y) on the board, 
    or null if there is no piece. If (x, y) are out of 
    bounds, returns null. */
    public Piece pieceAt(int x, int y) {
    	if (x > 7 || y > 7) {
    		return null;
    	}
    	return piecePos[x][y];
    }
    /*  Returns true if the square at (x, y) can be selected.

		A square with a piece may be selected if it is the 
		corresponding player’s turn and one of the following 
		is true:

			The player has not selected a piece yet.
			
			The player has selected a piece, but did not move it.
		
		An empty square may be selected if one of the following is true:

			During this turn, the player has selected a Piece which hasn’t
			moved yet and is selecting an empty spot which is a valid move 
			for the previously selected Piece.
			
			During this turn, the player has selected a Piece, captured, 
			and has selected another valid capture destination. When 
			performing multi-captures, you should only select the active 
			piece once; all other selections should be valid destination 
			points. */
    public boolean canSelect(int x, int y) {
    	if (capturedPiece != null) {
	   		remove(capturedPiece.xPos, capturedPiece.yPos);
    	}
	   	if (captured == true) {
	   		return canPieceCapture(selectedPiece, x, y);
	   	}
	   	if (piecePos[x][y] != null) {
	   		if (selectedPiece != null) {
	   			drawPiece(selectedPiece);
	   		}

	   		if (hasGone == false) {
	   			if (player == piecePos[x][y].side()) {
   	 				return true;
   				}

   				else {
   					System.out.println("Not your piece nub!");
   				}
   			}
   		}
   		else {
   			if (selectedPiece != null) {
   				if (hasGone == false) {
   			 		return availableSpotsWithJumping(selectedPiece, x, y);
   				}
   			}
      	}
      	return false;
    
    }
    private boolean canPieceCapture(Piece p, int x, int y) {
    	if (p.isBomb()) {
    		return false;
    	}
    	return canCapture(p, x, y);
    }
    private boolean availableSpotsWithJumping(Piece p, int x, int y) {
    	int xCoord = p.xPos;
    	int yCoord = p.yPos;
    	if (Math.abs(xCoord - x) == 1) {
	    	return availableSpots(p, x, y, 1);
    	}
    	return canCapture(p, x, y);
    }
    private boolean canCapture(Piece p, int x, int y) {
    	int possibleX = (p.xPos + x) / 2;
    	int possibleY = (p.yPos + y) / 2;
    	if (availableSpots(p, x, y, 2)) {
    		if (piecePos[possibleX][possibleY] == null) {
    			return false;
			}
			if (p.isBomb()) {
				bombPiece = p;
			}
    		if (p.isFire() != piecePos[possibleX][possibleY].isFire()) {
    			capturedPiece = piecePos[possibleX][possibleY];
    			return true;
    		}

    	}
    	return false;
    }
    private boolean availableSpots(Piece p, int x, int y, int range) {
    	int xCoord = p.xPos;
    	int yCoord = p.yPos;
    	if ((x + y) % 2 == 1) {
    		return false;
    	}
    	if (Math.abs(xCoord - x) == range) {
    		if (p.isKing()) {
    			return (Math.abs(y - yCoord) == range);
    		}
    		if (p.isFire()) {
    			return (y - yCoord == range);
    		}
    		if (p.isFire() == false) {
    			return (yCoord - y == range);
    		}
    	}
    	return false;
    }
    /* Selects the square at (x, y). This method assumes canSelect (x,y) 
    returns true. Optionally, it is recommended to color the background 
    of the selected square white on the GUI via the pen color function. 
    For any piece to perform a capture, that piece must have been 
    selected first. If you select a square with a piece, you are prepping 
    that piece for movement. If you select an empty square (assuming 
    canSelect returns true), you should move your most recently 
	selected piece to that square. */
    public void select(int x, int y) {
    	Piece p = piecePos[x][y];
    	if (p != null) {
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    		StdDrawPlus.picture(p.xPos + .5, p.yPos + .5, p.name, 1, 1);
    		selectedPiece = p;
    	}
    	else {
    		if (selectedPiece != null) {
    			place(selectedPiece, x, y);
    		}
    	}
    }
    /* Places p at (x, y). If (x, y) is out of bounds or if p is null, 
    does nothing. If another piece already exists at (x, y), p will replace 
    that piece. (This method is potentially useful for creating specific 
    test circumstances.) */
    public void place(Piece p, int x, int y) {
    	if (p == null) {

    	} 
    	if ( x > 7 ||
    		 x < 0 ||
    		 y > 7 ||
    		 y < 0) {

    	}
    	else {
    		int firstPosX = p.xPos;
    		int firstPosY = p.yPos;
    		p.move(x, y);
    		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    		StdDrawPlus.filledSquare(firstPosX + .5, firstPosY + .5, .5);
    		if (piecePos[x][y] != null) {
    			piecePos[x][y] = p;    		
    		}
    		drawPiece(p);
    		piecePos[x][y] = p;
    		remove(firstPosX, firstPosY);
    		hasGone = true;
    		captured = p.hasCaptured();

    	}


    }
    private void turnKing(Piece p) {
    	p.name = kingedVersion(p.name);
    	StdDrawPlus.picture(p.xPos + .5, p.yPos + .5, p.name, 1, 1);
    }
    private String kingedVersion(String s) {
    	if (s == fireP) {
    		return firePK;
    	}
    	if (s == fireB) {
    		return fireBK;
    	}
    	if (s == fireS) {
    		return fireSK;
    	}
    	if (s == waterP) {
    		return waterPK;
    	}
    	if (s == waterB) {
    		return waterBK;
    	}
    	if (s == waterS) {
    		return waterSK;
    	}
    	else {
    		return null;
    	}
    }
    /* Executes a remove. Returns the piece that was removed. If the input 
    (x, y) is out of bounds, returns null and prints an appropriate message.
    If there is no piece at (x, y), returns null and prints an appropriate 
    message.*/
    public Piece remove(int x, int y) {
    	if ( x > 7 ||
    		 x < 0 ||
    		 y > 7 ||
    		 y < 0) {
    		return null;
    	}
    	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
   		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    	piecePos[x][y] = null;
    	return null;
    }
    /* Returns whether or not the the current player can end their turn. To 
    be able to end a turn, a piece must have moved or performed a capture. */
    public boolean canEndTurn() {
    	if (hasGone == true) {
    		return true;
    	}
    	return false;
    }
    /* Called at the end of each turn. Handles switching of players and 
    anything else that should happen at the end of a turn. */
    public void endTurn() {
    	if (selectedPiece.isFire()) {
    		if (selectedPiece.yPos == 7) {
    			turnKing(selectedPiece);
    		}
    	}
    	if (selectedPiece.isFire() == false) {
    		if (selectedPiece.yPos == 0) {
    			turnKing(selectedPiece);
    		}
    	}
    	if (capturedPiece != null) {
	   		remove(capturedPiece.xPos, capturedPiece.yPos);
	   	}
	   	if (bombPiece != null) {
	   		int xCoord = bombPiece.xPos;
    		int yCoord = bombPiece.yPos;
    		remove(xCoord, yCoord);
    		if (piecePos[xCoord - 1][yCoord - 1] != null) {
    			if (piecePos[xCoord - 1][yCoord - 1].isShield() != true) {
    				remove(xCoord - 1, yCoord - 1);
    			}
    		}
    		if (piecePos[xCoord - 1][yCoord + 1] != null) {
    			if (piecePos[xCoord - 1][yCoord + 1].isShield() != true) {
    				remove(xCoord - 1, yCoord + 1);
    			}
    		}
    		if (piecePos[xCoord + 1][yCoord - 1] != null) {
    			if (piecePos[xCoord + 1][yCoord - 1].isShield() != true) {
    				remove(xCoord + 1, yCoord - 1);
    			}
    		}
    		if (piecePos[xCoord + 1][yCoord + 1] != null) {
    			if (piecePos[xCoord + 1][yCoord + 1].isShield() != true) {
    				remove(xCoord + 1, yCoord + 1);
    			}
    		}
    		bombPiece = null;
	   	}
    	System.out.println("Next turn!");
    	player = ((player + 1) % 2);
    	selectedPiece = null;
        capturedPiece = null;
        bombPiece = null;
    	hasGone = false;
    	captured = false;

    }
    /* Returns the winner of the game: "Fire", "Water", "No one", or null.
    If only fire pieces remain on the board, fire wins. If only water pieces
    remain, water wins. If no pieces remain (consider an explosion capture),
    no one wins. If the game is still in progress (ie there are pieces from
    both sides still on the board) return null. Assume there is no stalemate
    situation in which the current player has pieces but cannot legally move
    any of them (In this event, just leave it at null). Determine the winner 
    solely by the number of pieces belonging to each team. */
    public String winner() {
    	return null;
    }

}
