/* AUTHOR: Muhammad Hassaan Shakeel
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] piece_array;
    private boolean turn_isFire;

    private Piece selectedPiece;
    private int N;
    private int selectX; // When a player selects the initial box, it saves the box in these variables
    private int selectY;
    private boolean hasMoved;

    private int fire_counter;
    private int water_counter;

	/* Constructor for the Board class 
	 */
	public Board(boolean shouldBeEmpty) {
		N = 8;
        hasMoved = false;
        turn_isFire = true;
        piece_array = new Piece[N][N];  // array stores piece objects so we can retrieve it later
        if (!shouldBeEmpty) {
            makeStartingPieces();
        }

	}

    /** Draws an N x N board. If shouldBeEmpty is False, draws all pieces
     */
    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

            }   
        }

    }

    /* Initializes the stating pieces for the game
     */
    private void makeStartingPieces() {
        fire_counter = 0;
        water_counter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {     
                if (j == 2 && i % 2 == 0) {
                    piece_array[i][j] = new Piece(true, this, i, j, "bomb");
                    fire_counter += 1;
                } else if (j == 1 && i % 2 != 0) {
                    piece_array[i][j] = new Piece(true, this, i, j, "shield");
                    fire_counter += 1;
                } else if (j == 0 && i % 2 == 0) {
                    piece_array[i][j] = new Piece(true, this, i, j, "pawn");
                    fire_counter += 1;
                } else if (j == 7 && i % 2 != 0) {
                    piece_array[i][j] = new Piece(false, this, i, j, "pawn");
                    water_counter += 1;
                } else if (j == 6 && i % 2 == 0) {
                    piece_array[i][j] = new Piece(false, this, i, j, "shield");
                    water_counter += 1;
                } else if (j == 5 && i % 2 != 0) {
                    piece_array[i][j] = new Piece(false, this, i, j, "bomb");
                    water_counter += 1;
                } else {
                }
            }
        }
    }

    /* draws the pieces onto the respective board tile 
     */
    private void drawPieces() {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        drawBoard(N);  // UNCOMMENT THIS IF YOU WANT TO SEE HIGHLIGHTED SQUARES!!!!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (piece_array[i][j] != null) {
                    Piece drawThisPiece = piece_array[i][j];
                    boolean isThisKing = drawThisPiece.isKing();
                    boolean isThisBomb = drawThisPiece.isBomb();
                    boolean isThisShield = drawThisPiece.isShield();
                    if (drawThisPiece.isFire()) {  // Fire piece
                        if (isThisKing) {  // King Piece
                            if (isThisBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else if (isThisShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        } else {
                            if (isThisBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            } else if (isThisShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }   
                        }
                    } else {  // Water Piece
                        if (isThisKing) {  // King Piece
                            if (isThisBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else if (isThisShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        } else {
                            if (isThisBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            } else if (isThisShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }   
                        } 
                    }
                }


            }
        }
    }
    /* Gets the piece at position (x, y) on the board, or null if there is no piece.
     * If (x, y) are out of bounds, returns null.
     */
    public Piece pieceAt(int x, int y) {
    	if (!locationValid(x, y) || piece_array[x][y] == null) {
    		return null;
    	} else {
    		return piece_array[x][y];
    	}
    }

    /* Returns true if there is a piece at this location.
     */
    private boolean pieceHere(int x, int y) {
        if (piece_array[x][y] != null) {
            return true;
        } else {
            return false;
        }
    }

    /* Makes sure the board values being called are valid
     */
    private boolean locationValid(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        } else {
            return true;
        }
    }

    /* Returns true if there is a piece the piece at (x, y) and it can be selected.
     */
    public boolean canSelect(int x, int y) {
        if (!locationValid(x, y)) {
            return false;
        }
    	if (pieceHere(x, y)) {
            // System.out.println("YHUP, IT IS A PIECE!");
            if (!hasMoved) { // HAS NOT MOVED YET)
                // System.out.println("IT ALSO HAS NOT MOVED!");
                Piece lolImSpecial = pieceAt(x, y);
                if (lolImSpecial.isFire() && turn_isFire) {  // Fire piece and fire turn
                    return true;
                } else if (!lolImSpecial.isFire() && !turn_isFire) {  // Water piece and not fire turn
                    return true;
                } else {
                    return false;
                }

            }

        } else {
            if (selectedPiece != null) {
                if (validMove(selectX, selectY, x, y)) {
                    return true;
                } else {
                    return false;
                }
            } 
        }
        System.out.println("WOW!");
        return false;
    }


    /* Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to 
     * (xf, yf) in a valid fashion compatible with the rules.
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (!locationValid(xf, yf)) {  // If the final location is out of bounds
            return false;
        }
        int middleX = xi + ((xf - xi) / 2);
        int middleY = yi + ((yf - yi) / 2);
        Piece currentPiece = pieceAt(xi, yi);
        int diffY;
        if (turn_isFire) {  // Accounts for the fact that top player can only move down, lower player can only move up! 
            diffY = yf - yi;
        } else {
            diffY = yi - yf;
        }

        if (currentPiece.isKing()) { // Is King
            if (!currentPiece.hasCaptured()) { // has not captured yet
                if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 && !pieceHere(xf, yf) && !hasMoved) {
                    return true;
                } else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2 && pieceHere(middleX, middleY) && pieceAt(xi, yi).isFire() != pieceAt(middleX, middleY).isFire() && !pieceHere(xf, yf)) {
                    return true;
                } else {
                    return false;
                }
            } else { // Has Captured
                if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2 && pieceHere(middleX, middleY) && pieceAt(xi, yi).isFire() != pieceAt(middleX, middleY).isFire() && !pieceHere(xf, yf)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else { // Not King
            if (!currentPiece.hasCaptured()) { // has not captured yet
                if (Math.abs(xi - xf) == 1 && diffY == 1 && !pieceHere(xf, yf) && !hasMoved) {  // Has not moved
                    return true;
                } else if (Math.abs(xi - xf) == 2 && diffY == 2 && pieceHere(middleX, middleY) && pieceAt(xi, yi).isFire() != pieceAt(middleX, middleY).isFire() && !pieceHere(xf, yf)) {
                    return true;
                } else {
                    return false;
                }
            } else {  // Has Captured
                if (Math.abs(xi - xf) == 2 && diffY == 2 && pieceHere(middleX, middleY) && pieceAt(xi, yi).isFire() != pieceAt(middleX, middleY).isFire() && !pieceHere(xf, yf)) {
                    return true;
                } else {
                    return false;
                }
            } 
        }
    }


    /* Selects the piece at (x, y) if possible. Optionally, it is recommended to color
     * the background of the selected square white on the GUI via the pen color function.
     * For any piece to perform a capture, that piece must have been selected first.
     */
    public void select(int x, int y) {
        // if (selectedPiece != null) {
        //     reDrawSquare(selectX, selectY); //(selectedPiece.x, selectedPiece.y);
        // }
        if (pieceHere(x, y)) {
            // Do nothing...
        } else {
            pieceAt(selectX, selectY).move(x, y);
            hasMoved = true;
        }

        selectX = x;
        selectY = y;
        selectedPiece = pieceAt(x, y);


        //StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        //StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        
    }   

    /* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
     * If p already exists in the current Board, first removes it from its initial position. 
     * If another piece already exists at (x, y), p will replace that piece. (This method is 
     * potentially useful for creating specific test circumstances.)
     */
    public void place(Piece p, int x, int y) {
        Piece nowPiece;
        if (!locationValid(x, y) || p == null) {
            // Does nothing
        } else if (pieceHere(x, y)) { //if there is a piece in the final destination (never going to happen but...)
            remove(x, y);  // Removes the piece in the final locaiton
            nowPiece = remove(selectX, selectY);
            piece_array[x][y] = nowPiece;
            //System.out.println("THIS SHOULD NEVER GET PRINTED: PLACE METHOD");
            hasMoved = true;
        } else {
            //nowPiece = remove(selectX, selectY);
            piece_array[x][y] = p;// pieceAt(selectX, selectY); // nowPiece;

        }

    }


    /* Executes a remove. Returns the piece that was removed. If the input (x, y) 
     * is out of bounds, returns null and prints an appropriate message. If there is 
     * no piece at (x, y), returns null and prints an appropriate message
     */
    public Piece remove(int x, int y) {
        if (!locationValid(x, y)) {
            System.out.println("Your removal was unsuccessful");
            return null;
        } else if (pieceHere(x, y)) {
            if (pieceAt(x, y).isFire()) {
                fire_counter -= 1;
            } else {
                water_counter -= 1;
            }
        	Piece removed = pieceAt(x, y);
            piece_array[x][y] = null;

            //reDrawSquare(x, y);
            return removed;
        }
        return null;
    }

    /* Returns whether or not the the current player can end their turn. To be able to 
     * end a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn() {
        if (hasMoved) {
            return true;
        } else if (pieceHere(selectX, selectY) && piece_array[selectX][selectY].hasCaptured()) {
            return true;
        } else {
            return false;
        }
    }

    /* Called at the end of each turn. Handles switching of players and anything
     * else that should happen at the end of a turn
     */
    public void endTurn() {
        if (turn_isFire) {
            turn_isFire = false;
        } else {
            turn_isFire = true;
        }

        if (selectedPiece == null) {
            // Do Nothing

        } else {
            selectedPiece = null;
            pieceAt(selectX, selectY).doneCapturing();
            hasMoved = false;
        }
        selectedPiece = null;
        hasMoved = false;

        //drawBoard(N); // Uncomment this if you want to see the highlighted squares!!!
    }

    /* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces 
     * on the board), or null if the game is not yet over.
     */
    public String winner() {
    	int fire_counter1 = 0;
    	int water_counter1 = 0;

    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			if (pieceHere(i, j)) {
    				if (piece_array[i][j].isFire()) {
    					fire_counter1 += 1;
    				} else {
    					water_counter1 += 1;
    				}
    			}
    		}
    	}
    	if (fire_counter1 == 0 && water_counter1 > 0) {
    		return "Water";
    	} else if (water_counter1 == 0 && fire_counter1 > 0) {
    		return "Fire";
    	} else if (water_counter1 == 0 && fire_counter1 == 0) {
    		return "No one";
    	} else {
    		return null;
    	}
    }

    private void reDrawSquare(int x, int y) {
            if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        }


    private void clickSquare() {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                // System.out.format(" x: %f, y: %f", x, y);
                // System.out.format(" x: %d, y: %d",(int) x, (int) y);
                boolean selectcan = canSelect((int) x, (int) y);
                if (selectcan) {
                    select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                boolean turnend = canEndTurn();
                if (turnend) {
                    endTurn();

                }
            }
    }



    /* Main method starts a game of checkers, and does not return anything 
     * until the game is over
     */
    public static void main(String[] args) {
    	Board newBoard = new Board(false); // false: make board with pieces
        newBoard.drawBoard(newBoard.N);
        while (newBoard.winner() == null) {
            newBoard.drawPieces();
            newBoard.clickSquare();
            StdDrawPlus.show(10);
        }
        //System.out.println("GAME IS OVER!!!!");
        newBoard.drawBoard(newBoard.N);
        newBoard.drawPieces();
    }
}