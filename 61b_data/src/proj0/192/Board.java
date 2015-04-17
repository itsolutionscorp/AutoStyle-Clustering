// author: Karen Chiao

public class Board {

	 /** Location of pieces. */
    private Piece[][] pieces;
    private int N = 8;
    private boolean turn = true;        // begin with fire's turn
    private boolean selected = false;   // if the piece has been selected
    private boolean moved = false;      // if the piece has been moved
    private int lastX = -1;               // track last piece
    private int lastY = -1;

    public static void main(String [] args) {
        // Starts a GUI supported version of the game
        Board board = new Board(false);
        board.play();
    }   

    private void play() {
    /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (canSelect((int)x, (int)y)) {
                    select((int)x,(int)y);
                }           
            }        
            if (StdDrawPlus.isSpacePressed()) {
                if (canEndTurn()) {
                    endTurn();
                }
            }   
            StdDrawPlus.show(100);
        }
    }

    // Draws an N x N board. Adapted from:
    // http://introcs.cs.princeton.ed/java/15inout/CheckerBoard.java.html
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieceAt(i,j)), 1, 1);
                }
            }
        }
    }

    private void highlight(int x, int y) {
        // for select method; highlights selected space white   
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.picture(x + .5, y + .5, getImage(pieces[x][y]), 1, 1);
    }

    private String getImage(Piece p) {
        if (p.isFire()) {
            if (p.isBomb()) {
                if (p.isKing()) {
                    return "img/bomb-fire-crowned.png";
                }
                return "img/bomb-fire.png";
            } else if (p.isShield()) {
                if (p.isKing()) {
                    return "img/shield-fire-crowned.png";
                }
                return "img/shield-fire.png";
            } else {
                if (p.isKing()) {
                    return "img/pawn-fire-crowned.png";
                }
                return "img/pawn-fire.png";
            }
        } else {
            if (p.isBomb()) {
                if (p.isKing()) {
                    return "img/bomb-water-crowned.png";
                }
                return "img/bomb-water.png";
            } else if (p.isShield()) {
                if (p.isKing()) {
                    return "img/shield-water-crowned.png";
                }
                return "img/shield-water.png";
            } else {
                if (p.isKing()) {
                    return "img/pawn-water-crowned.png";
                }
                return "img/pawn-water.png";
            }
        }
    }

	// Constructor
	public Board(boolean shouldBeEmpty) {
		// if shouldBeEmpty == True, initialize empty Board
		// else, initialize a Board w default configuration
		// Note: empty Board can help with testing

		if (shouldBeEmpty) {              
            pieces = new Piece[N][N];     // initialize empty
		} else {                          
            pieces = new Piece[N][N];     // initialize default config
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((j == 0) && ((i & 1)  == 0)) {  
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    } else if ((j == 1) && ((i & 1) == 1)) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    } else if ((j == 2) && ((i & 1)  == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    } else if ((j == 5) && ((i & 1)  == 1)) {
                         pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    } else if ((j == 6) && ((i & 1)  == 0)) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    } else if ((j == 7) && ((i & 1)  == 1)) {  
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");  
                    }
                }
            }		
        }
	}

	public Piece pieceAt(int x, int y) {
		// returns piece at position (x,y) on the board
		// returns null if there is no piece
		// returns null if (x,y) out of bounds

		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            return null;
        } else {
            return pieces[x][y];
        }
	}

	public boolean canSelect(int x, int y) {
		// returns True if square at (x,y) can be selected

        // A square with a piece may be selected if it is the corresponding player’s turn 
        // and one of the following is true:
        //     The player has not selected a piece yet.
        //     The player has selected a piece, but did not move it.
    
        if (this.pieceAt(x,y) != null) {     // Case 1.0 - square has piece
            if (turn) {                  // fire's turn
                if (this.pieceAt(x,y).isFire()) {
                    if (!selected || (selected && !moved)) {
                            selected = false;
                        return true;
                    } else {              
                        return false;    
                    }    
                } else {
                    return false;
                }     
            } else {                            // water's turn
                if (!this.pieceAt(x,y).isFire()) {
                    if (!selected || (selected && !moved)) {
                            // System.out.println("get in here");
                            selected = false;
                        return true;
                    } else {             
                        return false;
                    }
                } else {
                    return false;
                }    
            }
    	} else {                        // Case 2.0 - square is empty
            // An empty square may be selected if one of the following is true:
                // During this turn, the player has selected a Piece which hasn’t moved yet 
                // and is selecting an empty spot which is a valid move for the previously selected Piece.
            if (selected && !moved) {
                return validMove(lastX, lastY, x, y);
            } else if (selected && moved) {
                // During this turn, the player has selected a Piece, captured, and has selected another valid capture 
                // destination. When performing multi-captures, you should only select the active piece once; all other 
                // selections should be valid destination points.
                if ((Math.abs(lastX-x) == 2) && (Math.abs(lastY-y) == 2)) {
                    return validMove(lastX, lastY, x, y);
                } return false;
            } else {
                // System.out.println("Getting to canSelect final else case");
                // return validMove(lastX, lastY, x, y);        // in any other case
                return false;                                       // in any other case
            }
        } 
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        // returns true if the piece at (xi,yi) can either 
        //      move to (xf,yf) or capture to (xf,yf)  
        // does not need to consider who's turn, or
        //      if move has already been made

        // case: if not a king piece
        int interX = (int) (xi + xf) / 2;
        int interY = (int) (yi + yf) / 2;

        if (((xf >= 0) || (xf <= 7) || (yf >= 0) || (yf <= 7)) && (pieces[xf][yf] == null)) {     // within board range & open destination
            if ((xi == xf) && (yi == yf)) {   // if user selects same spot
                return false;
            } else if (!this.pieceAt(xi, yi).isKing()) {               // if not a king, fire move up, water move down
                if ((Math.abs(xf-xi) == 1) && (Math.abs(yf-yi) == 1)) {
                // to move to a spot
                    if ((this.pieceAt(xi,yi).isFire()) && (yf > yi)) {         // fire pieces move upwards only
                        return true;
                    } else if (!this.pieceAt(xi,yi).isFire() && (yf < yi)) {   // water pieces move downwards only
                        return true;
                    } else {
                        return false;
                    }     
                } else if ((Math.abs(xf-xi) == 2) && (Math.abs(yf-yi) == 2)) {
                // to a capture spot
                    if (this.pieceAt(interX, interY) == null) {             // there's a piece in the middle
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {                                                    // if a king, king moves forwards and backwards
                if ((Math.abs(xf-xi) == 1) && (Math.abs(yf-yi) == 1)) {
                // to move to spot
                    return true;
                } else if ((Math.abs(xf-xi) == 2) && (Math.abs(yf-yi) == 2)) {
                // to a capture spot
                    if (this.pieceAt(interX, interY) == null) {             // there's a piece in the middle
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }    
        } else {  
            return false;   // if out of range, occupied destination, or in any other case     
        }
    }

	public void select(int x, int y) {
		// selects the piece at (x,y) if possible 
		// change the background color to white on GUI via pen color func
		// For any piece to perform a capture,
		// that piece must have been selected first.

        if (!selected) {
            // System.out.println("Making a selection");
            selected = true;
            lastX = x;
            lastY = y;
        } else if (selected && !moved) {   
            // System.out.println("Moving to (x,y)");
            pieces[lastX][lastY].move(x, y);
            lastX = x;
            lastY = y;
            moved = true;
        } else if (selected && moved) {
            // System.out.println("Capturing multiple");
            if (pieceAt(lastX,lastY) != null) {
                if ((pieceAt(lastX, lastY).hasCaptured())) {
                    pieceAt(lastX,lastY).move(x, y);
                    lastX = x;
                    lastY = y; 
                }
            } 
        }	
        // highlight(x,y);     // change background to white
	}

	public void place(Piece p, int x, int y) {
		// places p at (x,y) 
		// if (x,y) out of bounds, do nothing
		// if another piece already exists at (x,y), 
		//		p replaces that piece
		// This method is useful for creating specific test circumstances.
        if ((x >= 0) || (x <= 7) || (y >= 0) || (y <= 7)) {
	       pieces[x][y] = p;
        } 
	}

	public Piece remove(int x, int y) {
		// executes a remove
		// returns piece that was removed
		// if (x,y) out of bounds, 
		// 		return null and print appropriate message
		// if there is no piece at (x,y),
		//		return null and print appropriate message

        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            System.out.println("Location is out of range to remove.");
            return null;
        } else if (pieceAt(x,y) == null) {
            System.out.println("Location has no piece to remove.");
            return null;
        } else {
            Piece temp = pieceAt(x,y);
            pieces[x][y] = null;
            return temp;
        }
	}

	public boolean canEndTurn() {
		// returns whether or not current player can end their turn
		// to be able to end a turn, a piece must have
		//		been moved or performed a capture
        return moved;
	}

	public void endTurn() {
		// called at the end of each turn
		// handles switching of players and
		// 		anything else that should happen at the end of a turns
        turn = !turn;
        selected = false; 
        moved = false;
        pieceAt(lastX, lastY).doneCapturing();
        lastX = -1;
        lastY = -1;
        this.winner();
	}

	public String winner() {
		// returns winner of the game: "Fire","Water","No one"
		// returns null if game is not yet over
		// Assume there is no stalemate situation where
		//		current player has piece but cant move them
		//		(leave it at null)
		// Determine winnder solely by # of pieces per team
        int ftotal = 0;         // track how many pieces of each team is on the board
        int wtotal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                 if (pieceAt(i,j) != null) {
                    if (pieceAt(i,j).isFire()) {
                        ftotal++;
                    } else {
                        wtotal++;
                    }
                }
            }
        }        
        if ((ftotal == 0) && (wtotal == 0)) {
            return "No one";
        } else if (wtotal == 0) {
            return "Fire";
        } else if (ftotal == 0) {
            return "Water";
        } else if (ftotal == wtotal) {
            return null;
        } else {
            return null;
        }
	}

}

// Old code:

    // private boolean canCapture(int xi, int yi, int xf, int yf) {   
    //         // if piece at (xi,yi) can capture to (xf,fi)

    //         // called by validMove
    //         // assume validMove checkes: board range, null (xf,yf), |dist| == 2
    //         // does not rely on turn

    //         int interX = (int) (xi + xf) / 2;
    //         int interY = (int) (yi + yf) / 2;
    //         if (this.pieceAt(interX, interY) == null) {             // there's a piece in the middle
    //             return false;
    //         }
    //         else
    //         {
    //             return true;
    //         }
    //         // } else if (!this.pieceAt(xi, yi).isKing()) {   // if not a king, fire move up, water move down
    //         //     if (this.pieceAt(xi,yi).isFire()) {             // a fire piece wants to capture
    //         //         if ((pieceAt(xf-1,yf+1) != null) && (!this.pieceAt(xf-1,yf+1).isFire())) {           // upper left has water
    //         //             return true;
    //         //         } else if ((pieceAt(xf+1,yf+1) != null) && (!this.pieceAt(xf+1,yf+1).isFire())) {    // upper right has water
    //         //             return true;
    //         //         } else {
    //         //             return false;
    //         //         }
    //         //     } else {                                        // a water piece wants to capture
    //         //         if (((pieceAt(xf-1,yf-1) != null) && this.pieceAt(xf-1,yf-1).isFire())) {            // lower left has fire
    //         //             return true;
    //         //         } else if ((pieceAt(xf+1,yf-1) != null) && (this.pieceAt(xf+1,yf-1).isFire())) {     // lower right has fire
    //         //             return true;
    //         //         } else {
    //         //             return false;
    //         //         }
    //         //     }  
    //         // } else {                                        // if a king, king moves forwards and backwards
    //         //     if (this.pieceAt(xi,yi).isFire()) {             // a fire piece wants to capture
    //         //         if (!this.pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {     // middle piece is water
    //         //             return true;
    //         //         } else {
    //         //             return false;
    //         //         }
    //         //     } else {                                        // a water piece wants to capture
    //         //         if (this.pieceAt((xi + xf) / 2, (yi + yf) / 2).isFire()) {      // middle piece is fire
    //         //             return true;
    //         //         } else {
    //         //             return false;
    //         //         }
    //         //     }
    //         // }
    //     }