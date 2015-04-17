
public class Board {

	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private int N;
	private boolean turn;
    private boolean hasSelected;
    private boolean moved;
    private Piece selectedPiece;
    private int selectedx;
    private int selectedy;

	public Board(boolean shouldBeEmpty) {
        this.moved = false;
        this.hasSelected = false;
        this.turn = true;
		this.N = 8;
		this.shouldBeEmpty = shouldBeEmpty;
        this.pieces = new Piece[N][N];
        if (!shouldBeEmpty) {
        	for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j ++) {
    				if ((i + j) % 2 == 0) {
    					if (j == 0) {
    						pieces[i][j] = new Piece(true, this, i, j, "pawn");
    					} else if (j == 1) {
    						pieces[i][j] = new Piece(true, this, i, j, "shield");
    					} else if (j == 2) {
    						pieces[i][j] = new Piece(true, this, i, j, "bomb");
    					} else if (j == 5) {
    						pieces[i][j] = new Piece(false, this, i, j, "bomb");
    					} else if (j == 6) {
    						pieces[i][j] = new Piece(false, this, i, j, "shield");
    					} else if (j == 7) {
    						pieces[i][j] = new Piece(false, this, i, j, "pawn");
    					}
    				}
    			}
    		}
        } else {
        }
	}

    public static void main(String[] args) {
        Board b = new Board(false);
        while (true) {
            if (b.spacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (b.clicked()) {
                int x = b.mouseX();
                int y = b.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }
            b.drawBoard(8);
            if (b.winner() != null) {
                System.out.println(b.winner());
            }
        }
    }

    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                if ((a + b) % 2 == 0) 
                    if (hasSelected && (a == selectedx) && (b == selectedy) && (pieceAt(selectedx, selectedy) != null)) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    } else StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(a + .5, b + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        drawPieces(N);
        StdDrawPlus.show(100);
    }

    private void drawPieces(int N) {
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {       
                if (pieces[i][j] != null) {
	        		if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire())) {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        } else {
	                       StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
	            	} else if ((pieces[i][j].isBomb()) && (!pieces[i][j].isFire())) {
	            		if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
	            	} else if ((pieces[i][j].isShield()) && (pieces[i][j].isFire())) {
                        if (pieces[i][j].isKing()) {
	            	        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
	            	} else if ((pieces[i][j].isShield()) && (!pieces[i][j].isFire())) {
                        if (pieces[i][j].isKing()) {
	            		    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
	            	} else if (!pieces[i][j].isShield() && (!pieces[i][j].isBomb()) && pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()) {
	            		    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
	            	} else if (!pieces[i][j].isShield() && (!pieces[i][j].isBomb()) && (!pieces[i][j].isFire())) {
                        if (pieces[i][j].isKing()) {
	            		    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
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
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            //System.out.println("out of bounds");
            return null;
        }
    	return pieces[x][y];
    }

    /* Returns true if the square at (x, y) can be selected. 
     */
    public boolean canSelect(int x, int y) {
        //System.out.println("going into canSelect");
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        if (pieceAt(x, y) != null) {
            if (((turn) && (pieceAt(x, y).isFire())) || ((!turn) && (!pieceAt(x, y).isFire()))) {
                if ((!hasSelected) || (!moved && hasSelected)) {
                    //System.out.println("clicking same type of piece or clicking first piece");
                    return true;
                }
            }
        } else if (pieceAt(x, y) == null) {
            //System.out.println(moved);
            if (validMove(selectedx, selectedy, x, y)) {
                if (hasSelected && (!moved) && validMove(selectedx, selectedy, x, y)) {
                    //System.out.println("getting into this first redundancy in canSelect");
                    return true;
                // } else if (hasSelected && validMove(selectedx, selectedy, x, y)) {
                //     System.out.println("getting into the second redundancy in canSelect");
                //     return true;
                }
                if (hasSelected) {
                    if (selectedPiece.hasCaptured()) {
                        if ((Math.abs(selectedx - x) == 2) && (Math.abs(selectedy - y) == 2)) {
                            return true;
                        }
                    }
                }
            }
        }
        //System.out.println("bottom of canSelect");
        return false; 
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) == null){
            //System.out.println("Piece is placed");
            selectedPiece.move(x, y);
        } else if (pieceAt(x, y) != null) {
            selectedx = x;
            selectedy = y;
            hasSelected = true;
            selectedPiece = pieceAt(x, y);
            //System.out.println("piece selected");
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        // initial piece exists
        //System.out.println("getting into validMove");
        if (!moved) {    
            if ((pieceAt(xf, yf) == null) && pieceAt(xi, yi) != null) {
                if (pieceAt(xi, yi).isKing()) {
                    //System.out.println("piece is king");
                    if ((Math.abs(yf - yi) == 1) && (Math.abs(xf - xi) == 1)) {
                        return true;
                        // jumping
                    } else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {
                        // moving right
                        if (pieceAt(xi, yi).isFire()) {
                            return ((pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (!pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire()));                            
                        } else if (!pieceAt(xi, yi).isFire()) {
                            return ((pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire()));  
                        }
                    }
                } else if (pieceAt(xi, yi).isFire()) {
                    if (((yf - yi) == 1) && (Math.abs(xf - xi) == 1)) {
                        return true;
                    } else if (((yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {
                        return ((pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (!pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire())); 
                    }
                } else if (!pieceAt(xi, yi).isFire()) {
                    if (((yi - yf) == 1) && (Math.abs(xf - xi) == 1)) {
                        //System.out.println("water moving one space");
                        return true;
                    } else if (((yi - yf) == 2) && (Math.abs(xf - xi) == 2)) {
                        return ((pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire()));                         
                    }
                }
            }
        } else if (moved && selectedPiece.hasCaptured()) {
            //System.out.println("getting here for double jump");
            if (selectedPiece.isKing()) {
                return ((Math.abs(selectedx - xf) == 2) && (Math.abs(selectedy - yf) == 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null));
            } else if (selectedPiece.isFire()) {
                //System.out.println("here");
                return ((Math.abs(xf - xi) == 2) && ((yf - yi) == 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null));
            } else if (!selectedPiece.isFire()) {
                return ((Math.abs(xi - xf) == 2) && ((yi - yf) == 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null));
            }
        }
        return false;
    }


    /* Places p at (x, y). If (x, y) is out of bounds, does nothing. If another piece
     * already exists at (x, y), p will replace that piece.
     */ 
    public void place(Piece p, int x, int y) {
        if ((x >= N) || (y >= N) || (x < 0) || (y < 0)) {
        } else {
		  pieces[x][y] = p;
          //System.out.println("setting move to true in place");
          selectedx = x;
          selectedy = y;
        }
    }

    /* Executes a remove. Returns the piece that was removed. If the input (x, y) is
     * out of bounds, returns null and prints an appropriate message. If there is no 
     * piece at (x, y), returns null and prints an appropriate message. 
     */
    public Piece remove(int x, int y) {
    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            //System.out.println("out of bounds in remove");
    		return null;
    	} else if (pieceAt(x, y) == null) {
            //System.out.println("its an empty square in remove");
    		return null;
    	} else {
            moved = true;
            Piece temp = pieceAt(x, y);
            //System.out.println("pieces[x][y] is null");
    		pieces[x][y] = null;
    		return temp;
    	}
    }

    /* Returns whether or not the current player can end their turn. To be able to end
     * a turn, a piece must have moved or performed a capture.
     */
    public boolean canEndTurn() {
        return moved;
    }

    /* Called at the end of each turn. Handles switching of players and anything else
     * that should happen at the end of a turn.
     */
    public void endTurn() {
        //System.out.println("Ended turn");
        hasSelected = false;
        turn = !turn;
        moved = false;
        selectedPiece.doneCapturing();
    }

    /* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on)
     * the board), or null if the game is not yet over. Assume there is no stalemate
     * situation in which the current player has peices but cannot legally move any
     * of them (In this event, just leave it at null). Determe the winner solely by
     * the numer of pieces belonging to each team.
     */
    public String winner() {
    	int waterPieces = 0;
    	int firePieces = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (pieceAt(i, j) != null) {
    				if (pieceAt(i, j).isFire()) {
    					firePieces += 1;
    				} else {
    					waterPieces += 1;
    				}
    			}
    		}
    	}
    	if ((waterPieces == 0) && (firePieces == 0)) {
    		return "No one";
    	} else if ((waterPieces > 0) && (firePieces == 0)) {
    		return "Water";
    	} else if ((waterPieces == 0) && firePieces > 0) {
			return "Fire";
		} else {
    	   return null;
    	}
    }

    private boolean spacePressed() {
        return StdDrawPlus.isSpacePressed();
    }

    private boolean clicked() {
        return StdDrawPlus.mousePressed();
    }

    private int mouseX() {
        return (int) StdDrawPlus.mouseX();
    } 

    private int mouseY() {
        return (int) StdDrawPlus.mouseY();
    }

}