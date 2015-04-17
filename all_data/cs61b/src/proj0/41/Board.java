public class Board {

//Need to change the variables to private

    /** Location of pieces. */
    private boolean[][] boolPieces;
    /** matrix of piece objects */
    private Piece[][] pieces;
    private boolean[][] whitePiece;
    /** whether we want to initialize an empty board. */
    private boolean shouldBeEmpty;
    /** whether a piece can be selected. */
    private boolean selected = false;
    /** whether a piece has been moved in a turn. */
    private boolean moved = false;
    /* whether the player has captured in a turn.*/
    private boolean captured = false;
    /** Coordinates of current selected piece. */
    private int currentX;
    private int currentY;
    private Piece currentPiece = null;
    /* player0:fire; player1: water */
    private int player = 0;

	public Board(boolean shouldBeEmpty0) {
		/* Constructor for Board. If shouldBeEmpty is true,
		** initialize an empty board. Otherwise, initialize
		** a board with default configuration.*/
		shouldBeEmpty = shouldBeEmpty0;
        int N = 8;
        boolPieces = new boolean[N][N];
        pieces = new Piece[N][N];
        whitePiece = new boolean[N][N];
        if (shouldBeEmpty == false) {
            int j = 0;
            for (int i = 0; i < 8; i += 2) {
                Piece p = new Piece(true, this, i, j, "pawn");
                place(p, i, j);
            }
            j = 1;
            for (int i = 1; i < 8; i += 2) {
                Piece p = new Piece(true, this, i, j, "shield");
                place(p, i, j);
            }
            j = 2;
            for (int i = 0; i < 8; i += 2) {
                Piece p = new Piece(true, this, i, j, "bomb");
                place(p, i, j);
            }
            j = 5;
            for (int i = 1; i < 8; i += 2) {
                Piece p = new Piece(false, this, i, j, "bomb");
                place(p, i, j);         
            }
            j = 6;
            for (int i = 0; i < 8; i += 2) {
                Piece p = new Piece(false, this, i, j, "shield");
                place(p, i, j);         
            }
            j = 7;
            for (int i = 1; i < 8; i += 2) {
                Piece p = new Piece(false, this, i, j, "pawn");
                place(p, i, j); 
            }
        }
    }

	public Piece pieceAt(int x, int y) {
		/* Gets the piece at (x, y) on the board, or return null
		** if there is no piece. If (x, y) are out of bounds, return
		** null */

		if (x < 8 && y <8 && x >= 0 && y >= 0) {
			return pieces[x][y];
		} else {
            return null;
        }
	}

	public void place(Piece p, int x, int y) {
		/* Place p at (x, y). If (x, y) is out of bounds does
		** nothing. If another piece already exists at (x, y)
		** p will replace that piece. */

		if (x < 8 && y <8 && x >= 0 && y >= 0) {
			boolPieces[x][y] = true;
			pieces[x][y] = p;
            currentPiece = p;
		}
	}

    public Piece remove(int x, int y) {
        /* Remove a piece and return the piece that was removed
        ** If (x, y) is out of bounds, return null and print
        ** an appropriate message. If there is no piece at (x,y)
        ** return null and print an appropriate message.*/
        if (x < 8 && y <8 && x >= 0 && y >= 0) {
            if (boolPieces[x][y]) {
                Piece removed = pieceAt(x, y);
                pieces[x][y] = null;
                boolPieces[x][y] = false;
                return removed;
            } else {
                System.out.println("There is no piece at (" + x + ", " + y + ").");
                return null;
            }
        }
        else {
            System.out.println("(" + x + ", " + y + ") is out of bounds.");
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        /* Return true if the square at (x, y) can be selected
        ** 
        ** A square with a piece can be selcted if it is the corresponding
        ** player's turn and one of the following is true:
        ** 1. The player has not selected a piece yet.
        ** 2. The player has selected a piece, but did not move
        ** it.
        ** An empty square may be selected if one of the 
        ** following is true:
        ** 1. During this turn, the player has selected a piece
        ** which hasn't been moved yet and is selecting an
        ** empty spot which is a valid move for the previously
        ** selected Piece
        ** 2. During this turn, the player has selected a Piece,
        ** captured, and has selected another valid capture
        ** destination. When performing multi-captures, you
        ** should only select the active piece once; all other
        ** selections should be valid destination points.

        ** Need to first check if it's the right player's turn.
         */
        if (boolPieces[x][y] == true) {
            if (player != pieces[x][y].side()) {
                return false;
            }
        }
        if (boolPieces[x][y]) {
            if (selected == true && moved == false) {
                return true;
            } else if (selected == false) {
                return true;
            }
        } else {
            if (selected == true && moved == false) {
                if (validMove(x, y)) {
                    return true;
                }
            }
            else if (captured && validCapture(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int x, int y) {
        return (validMoveToEmpty(x, y) || validCapture(x, y));
    }

    private boolean validCapture(int x, int y) {
        /*Returns true if moving to (x,y) can capture an intermediate piece. Assuming (x, y) is within the bounds.*/
        int a1, a2, b1, b2;
        if (currentPiece.isFire()) {
            a1 = 1; a2 = 2; b1 = -1; b2 = -2;
        } else {
            a1 = -1; a2 = -2; b1 = 1; b2 = 2;
        }
        int xa1 = currentX + a1;
        int xb1 = currentX + b1;
        int ya1 = currentY + a1;
        int yb1 = currentY + b1;

        if (y == currentY + a2) {
            if (x == currentX + a2 && boolPieces[xa1][ya1] == true && pieceAt(xa1, ya1).isFire() != currentPiece.isFire()) {
                return true;
            } else if (x == currentX + b2 && boolPieces[xb1][ya1] == true && pieceAt(xb1, ya1).isFire() != currentPiece.isFire()) {
                return true;
            }
        }
        else if (currentPiece.isKing() && y == currentY + b2) {
            if (x == currentX + a2 && boolPieces[xa1][yb1] && pieceAt(xa1, yb1).isFire() != currentPiece.isFire()) {
                return true;
            } else if (x == currentX + b2 && boolPieces[xb1][yb1] && pieceAt(xb1, yb1).isFire() != currentPiece.isFire()) {
                return true;
            }
        }
        return false;
    }

    private boolean validMoveToEmpty(int x, int y) {
        /* Returns true if (x, y) empty and is a valid move for the current piece.*/
        int a1, a2, b1, b2;
        if (currentPiece.isFire()) {
            a1 = 1; a2 = 2; b1 = -1; b2 = -2;
        } else {
            a1 = -1; a2 = -2; b1 = 1; b2 = 2;
        }
        if (x == currentX + a1 || x == currentX + b1){
            if (y == currentY + a1 && boolPieces[x][y] == false) {
                return true;
            }
            else if (currentPiece.isKing() && y == currentY + b1 && boolPieces[x][y] == false) {
                return true;
            }
        }
        return false;
    }

    public void select(int x, int y) {
        /* Select the square at (x, y), assuming canSelect(x, y)
        ** returns true. Recommended to color the background of 
        ** the selected square white on the GUI via the pen color
        ** function. For any piece to perform a capture, that
        ** piece must have been selected first. If you select an
        ** empty square, you should move your most recently
        ** selected piece to that square.
         */
        if (selected == false) {
            /* Select a non-empty square only when nothing has been
            ** selected in this turn. */
            whitePiece[x][y] = true;
            setCurrent(x, y);
            selected = true;
        } else if (selected == true) {
            if (moved == false && boolPieces[x][y]) {
                /* select a non-empty squre when a piece is selected
                ** but not moved. */
                whitePiece[currentX][currentY] = false;
                whitePiece[x][y] = true;
                setCurrent(x, y);
            } else {
                /* Select and move to an empty square assuming validMoveToEmpty or validCapture */
                    if (validCapture(x, y)) {
                        captured = true;
                    }
                    currentPiece.move(x, y);
                    whitePiece[x][y] = true; 
                    moved = true;
                    currentX = x;
                    currentY = y;
            }
        }
    }

    private void setCurrent(int x, int y) {
        currentPiece = pieceAt(x, y);
        currentX = x;
        currentY = y;
    }


    private void drawPiece(Piece p, int x, int y) {
        if (pieces[x][y] == null) {
            System.out.println("no piece at (" + x + ", " + y + ")");
        }
        if (whitePiece[x][y]) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);         
        }
        if (pieces[x][y].isKing() == false) {
            if (p.isBomb() == false && p.isShield() == false) {
                if (p.isFire()) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                } else {
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                }
            } else if (p.isBomb()) {
                if (p.isFire()) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                } else {
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                }
            } else if (p.isShield()) {
                if (p.isFire()) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                } else {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                }
            }
        } else if (pieces[x][y].isKing()) {
            if (p.isBomb() == false && p.isShield() == false) {
                if (p.isFire()) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                } else {
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                }
            } else if (p.isBomb()) {
                if (p.isFire()) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                } else {
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                }
            } else if (p.isShield()) {
                if (p.isFire()) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                } else {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                }
            }
        }
    }

    public boolean canEndTurn() {
        /*Return whether or not the current player can end
        ** their turn. To end a turn, a piece must have moved
        ** or performed a capture.
        ** Don't think it's necessary to return (moved || captured). */
        return moved;
        
    }

    public void endTurn() {
        /* Called at the end of each turn. Handles switching
        ** players and anything else that should happen at the
        ** end of a turn.
        */
        player = 1 - player;
        selected = false;
        moved = false;
        captured = false;
        currentPiece.doneCapturing();
        currentPiece = null;
        whitePiece = new boolean[8][8];
    }

    public String winner() {
        /* Return the winner of the game. "Fire", "Water", 
        ** "No one", or null if the game is not over.
        ** Assume there is no stalemate situstion in which
        ** current player has pieces but cannot legally move
        ** any of them. Determine the winner solely be the 
        ** number of pieces belonging to each team.
        */
        int fire = 0;
        int water = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boolPieces[i][j]) {
                    if (pieces[i][j].isFire()) {
                        fire = fire + 1;
                    } else {
                        water = water + 1;
                    }
                }
            }
        }
        if (fire == 0 && water == 0) {
            return "No one";
        } else if (fire == 0) {
            return "Water";
        } else if (water == 0) {
            return "Fire";
        } else {
            return null;
        }
    }

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (boolPieces[i][j]) {
                    drawPiece(pieces[i][j], i, j);
                }
	        }
	    }
	}
    
    public static void main(String args[]) {
		//start a GUI version of the game
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);        
        Board b = new Board(false);
        while(b.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    if (b.currentPiece.hasCaptured()) {
                        b.currentPiece.doneCapturing();
                    }
                    b.endTurn();
                }
            }
            b.drawBoard(N);
            StdDrawPlus.show(40);
        }
        System.out.print(b.winner());
	}
}