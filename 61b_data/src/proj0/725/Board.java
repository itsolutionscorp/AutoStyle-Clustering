public class Board {

	private Piece[][] pieces;
	private Piece selectedPiece = null;
	// The position of the SelectedPiece
	private int[] selectedPosition = new int[2]; {
	selectedPosition[0] = 99;
	selectedPosition[1] = 99; }
	
	private int whosTurn = 0;
	private int numMoves = 0;
    private int N = 8;
    
    // Initializes the board.
    public Board(boolean shouldBeEmpty) {

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        pieces = new Piece[N][N];
        

        if (shouldBeEmpty) {
        	pieces = new Piece[N][N];
        } else {
        	// Here, place the pieces for the starting condition.
        	for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                	if ((j == 0) && (i % 2 == 0)) {
                		pieces[i][j] = new Piece(true, this, i, j, "pawn");
                	} else if ((j == 1) && (i % 2 == 1)) {
                		pieces[i][j] = new Piece(true, this, i, j, "shield");
                	} else if ((j == 2) && (i % 2 == 0)) {
                		pieces[i][j] = new Piece(true, this, i, j, "bomb");
                	} else if ((j == 5) && (i % 2 == 1)) {
                		pieces[i][j] = new Piece(false, this, i, j, "bomb");
                	} else if ((j == 6) && (i % 2 == 0)) {
                		pieces[i][j] = new Piece(false, this, i, j, "shield");
                	} else if ((j == 7) && (i % 2 == 1)) {
                		pieces[i][j] = new Piece(false, this, i, j, "pawn");
                	}
        	
                }
        	}
        }
        drawBoard(N);
    }

    // Draws the entire board, every space and piece.
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                }
                Piece currPiece = pieces[i][j];
                // If there is a Piece at (I, J), draw the image.
                if (currPiece != null) {
                	drawPiece(currPiece, i, j);
                }

            }
        }
        if (selectedPiece != null) {
        	int x = selectedPosition[0];
        	int y = selectedPosition[1];
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        	drawPiece(selectedPiece, x, y);
        }
    }
    
    // Draws the Piece at (X, Y).
    private void drawPiece(Piece currPiece, int x, int y) {
    	String imgRoute = "/bin/img/";
    	
    	// Type?
    	if (currPiece.isBomb()) {
    		imgRoute += "bomb-";
    	} else if (currPiece.isShield()) {
    		imgRoute += "shield-";
    	} else {
    		imgRoute += "pawn-";
    	}
    	
    	// Fire or Water?
    	if (currPiece.isFire()) {
    		imgRoute = imgRoute + "fire";
    	} else {
    		imgRoute = imgRoute + "water";
    	}
    	
    	// Crowned?
    	if (currPiece.isKing()) {
    		imgRoute = imgRoute + "-crowned.png";
    	} else {
    		imgRoute = imgRoute + ".png";
    	}
    	StdDrawPlus.picture((double) x + 0.5, (double) y + 0.5, imgRoute, 1, 1);
    }
  
    // Starts a GUI supported version of the game.
    public static void main(String[] args) {
    	Board b = new Board(false);
    	
    	// Constantly checks for mouse clicks, space bar presses, and winning conditions.
    	while (true) {
    		if (StdDrawPlus.mousePressed()) {
    			int currX = (int) StdDrawPlus.mouseX();
    			int currY = (int) StdDrawPlus.mouseY();
    			if (b.canSelect(currX, currY)) {
    				b.select(currX, currY);
    			}
    			
    		} else if (StdDrawPlus.isSpacePressed()) {
    			if (b.canEndTurn()) {
    				b.endTurn();
    			}
    		}
    		if(b.winner() != null) {
    			StdDrawPlus.text((double) b.N/2, (double) b.N/2, b.winner() + " wins!");
    			return;
    		}
    	}

    }

	// Gets the piece at position (x, y) on the board, or null if there is no piece. 
	// If (x, y) are out of bounds, returns null.
    public Piece pieceAt(int x, int y) {
    	// Check to see if indices are out of bounds.
    	if (x >= N || y >= N || x < 0 || y < 0) {
    		return null;
    	} else if (pieces[x][y] != null) {
    		return pieces[x][y];
    	} else {
    		return null;
    	}  	
    }
    
    // Returns true if the square at (X,Y) can be selected.
    public boolean canSelect(int x, int y) {

    	// Is there a piece here?
    	Piece currPiece = pieces[x][y];
    	if(currPiece != null) {
    		// There is a piece here.
    		// Is it the right color?
    		if(whosTurn == currPiece.side()) {
    			// It is the right color.
    			// Have I moved yet?
    			if (numMoves < 1) {
    				// I haven't moved yet.
    				// Return True.
    				return true;
    			} else {
    				// I have moved. Don't change the selected piece.
    				return false;
    			}
    		} else {
    			// It is the wrong color.
    			return false;
    		}
    	} else {
    		// There is no piece here.
    		// Have I selected a piece yet?
    		if (selectedPiece != null) {
    			// I have selected a piece.
    			return validMove(numMoves, x, y);
    		} else {
    			// I haven't selected a piece yet.
    			return false;
    		}
    	} 	
    }
    
    // Given the NUM of moves so far and whether or not it is a king, 
    // returns whether or not the move is valid.
    private boolean validMove(int num, int x, int y) {
    	int advancingDir = 1;
    	int runTimes = 1;
    	
    	// If it is a king, set runTimes to 2 and advancingDir to 1.
    	if (selectedPiece.isKing()) {
    		runTimes += 1;
    	} else if (whosTurn == 1) {
    		// Which direction is forward?
    		advancingDir = -1;
    	}
    	
    	// If it is a King, run with advancingDir as 1 and as -1.
    	// Else, only run once
    	while (runTimes > 0) {
    	
	    	if (num == 0) {
	    		// Does the move advance by one?
	    		if (y == selectedPosition[1] + advancingDir) {
	    			// From canSelect, I know that there are no other pieces here.
	    			// does it move diagonally?
	    			if (Math.abs(x - selectedPosition[0]) ==  1) {
	    				return true;
	    			} else {
	    				return false;
	    			}
	    		}
	    	}
    		// Does the move capture?
    		if (y == selectedPosition[1] + 2*advancingDir) {
    			// Is it also a 2 space move in the x-direction?
    			if (Math.abs(x - selectedPosition[0]) == 2) {
    				// Is there an opposing piece in between?
    				if (pieces[(x + selectedPosition[0]) / 2][(y + selectedPosition[1]) / 2] != null) {
    					if (pieces[(x + selectedPosition[0]) / 2][(y + selectedPosition[1]) / 2].side() != whosTurn) {
    						// Remove the opposing piece.
    						return true;
    					} else {
    						// The piece is on the same side.
    						return false;
    					}
    				}
    			} else {
    				// It moves forward by two, but not sideways by two.
    				return false;
    			}
    		}
    		runTimes -= 1;
    		advancingDir -= 2;
	    }
    	return false;
    }
    
    // Selects the square at (X,Y), assuming CanSelect.
    public void select(int x, int y) {
    	
    	// Remove the previously colored square.
    	drawBoard(N);
    	// Color the background of the selected square.
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    	
    	if (pieces[x][y] != null) {
    		// Select the piece and its position.
    		selectedPiece = pieceAt(x, y);
    		selectedPosition[0] = x;
    		selectedPosition[1] = y;
    		drawPiece(selectedPiece, x, y);
    	} else {
    		// We assumed CanSelect, so this is a movement.
    		
    		// If it is a capture, the piece must stay selected after the movement.
    		if (Math.abs(selectedPosition[0] - x) == 2) {
    			selectedPosition[0] = x;
    			selectedPosition[1] = y;
    			// Let the user know that the piece is still selected.
    	        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	    	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    
    		} else {
    		// If not, the piece should not stay selected.
    			selectedPosition[0] = 99;
    			selectedPosition[1] = 99;
    		}
    		selectedPiece.move(x, y);
    		numMoves += 1;
    	}


    	
    }
    
    // Places P at (X, Y) regardless of what is at (X, Y).
    public void place(Piece p, int x, int y) {
    	if (p == null || x < 0 || y < 0 || x >= N || y >= N) {
    		return;
    	} else {
    		for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                	if (pieces[i][j] == p) {
                		remove(i, j);
                		break;
                	}
                }
    		}
    		pieces[x][y] = p;
    		drawBoard(N);
    	}   	
    }
    
    // Removes a piece and returns the piece that is removed.
    // Draws the board after the piece is removed.
    public Piece remove(int x, int y) {
    	if (pieces[x][y] == null) {
    		System.out.println("No piece to remove.");
    		return null;
    	}
    	if (x < 0 || y < 0 || x >= N || y >= N) {
    		System.out.println("Out of bounds.");
    		return null;
    	}
    	Piece temp = pieces[x][y];
    	pieces[x][y] = null;
    	drawBoard(N);
    	return temp;
    }
    
    // Returns whether or not the current player can end their turn.
    public boolean canEndTurn() {
    	// A piece must have moved or performed a capture.
    	return (numMoves > 0);
    }

    // Ends each turn.
    public void endTurn() {
    	// Call DoneCapturing.
    	selectedPiece.doneCapturing();
    	// Deselect Pieces, update board, switch turn, reset moves
    	selectedPiece = null;
    	selectedPosition[0] = 99;
    	selectedPosition[1] = 99;
    	numMoves = 0;  
    	drawBoard(N);
    	if (whosTurn == 0) {
    		whosTurn = 1;
    	} else {
    		whosTurn = 0;
    	}
    }
    
    // Returns the winner solely based on number of remaining pieces.
    // Returns None if pieces from both teams are on the board.
    public String winner() {
    	int fireRemaining = 0;
    	int waterRemaining = 0;
    
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire()) {
            			fireRemaining += 1;
            		} else {
            			waterRemaining += 1;
            		}
            	}
            }
    	}
    	if (fireRemaining == 0) {
    		if (waterRemaining == 0) {
    			return "No one";
    		} else {
    			return "Water";
    		}
    	} else if (waterRemaining == 0) {
    		return "Fire";
    	} else {
    		return null;
    	}
    }
    	




}
