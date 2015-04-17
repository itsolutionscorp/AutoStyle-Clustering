
public class Board {

    /** Location of pieces. */
    private boolean[][] pieces = new boolean[8][8];

    /** Piece Array. Stores pieces at location on board */
    private Piece[][] piecePlace = new Piece[8][8];

    /** Fields */
    private boolean boardEmpty;
    private boolean firePlayer = true;
    private boolean waterPlayer = false;
    private boolean pieceMoved = false;
    private boolean pieceCaptured = false;
    private boolean piecePicked = false;
    private boolean emptySelected = false;
    private boolean mustEndTurn = false;
    private boolean waterWon = false;
    private boolean fireWon = false;
    private boolean noOneWon = true;
    private int xSaved;
    private int ySaved;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    public static void main(String[] args) {
        /** Draws the Board
         */ 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }               
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }    
            if (b.winner() != null) {
                System.out.println(b.winner());
            }         
            StdDrawPlus.show(100);   
        }
        /** Board is drawn 
         */
    }

/** The constructor for Board. 
  * If shouldBeEmpty is true, initializes an empty Board. 
  * Otherwise, initializes a Board with the default configuration. 
  * Note that an empty Board could possibly be useful for testing purposes. */
    public Board(boolean shouldBeEmpty) {
        boardEmpty = shouldBeEmpty;
        if (boardEmpty) {
            pieces = new boolean[8][8];
            piecePlace = new Piece[8][8];
        }
        else {
            pieces = new boolean[8][8];
            piecePlace = new Piece[8][8];
            noOneWon = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                /** Sets up Fire pieces */
                    if ((i % 2 == 0) && (j == 0)) {
                        pieces[i][j] = true;
                        piecePlace[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    if ((i % 2 != 0) && (j == 1)) {
                        pieces[i][j] = true;
                        piecePlace[i][j] = new Piece(true, this, i, j, "shield");;
                    }
                    if ((i % 2 == 0) && (j == 2)) {
                        pieces[i][j] = true;
                        piecePlace[i][j] = new Piece(true, this, i, j, "bomb");;
                    }
                /** Sets up Water pieces */
                    if ((i % 2 != 0) && (j == 5)) {
                        pieces[i][j] = true;
                        piecePlace[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if ((i % 2 == 0) && (j == 6)) {
                        pieces[i][j] = true;
                        piecePlace[i][j] = new Piece(false, this, i, j, "shield");;
                    }
                    if ((i % 2 != 0) && (j == 7)) {
                        pieces[i][j] = true;
                        piecePlace[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    }

    private static void drawEmptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }   	
    }


    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                /** Sets up Fire pieces 
                 */
                if (pieces[i][j] && piecePlace[i][j].isFire() && piecePlace[i][j].isShield()) {
                    if (piecePlace[i][j].isKing()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                }
                if (pieces[i][j] && piecePlace[i][j].isFire() && piecePlace[i][j].isBomb()) {
                    if (piecePlace[i][j].isKing()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
                if (pieces[i][j] && piecePlace[i][j].isFire() && piecePlace[i][j].isBomb()==false && piecePlace[i][j].isShield()==false) {
                    if (piecePlace[i][j].isKing()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                }
                // water pieces
                if (pieces[i][j] && piecePlace[i][j].isFire()==false && piecePlace[i][j].isShield()==false && piecePlace[i][j].isBomb()==false) {
                    if (piecePlace[i][j].isKing()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
                if (pieces[i][j] && piecePlace[i][j].isFire()==false && piecePlace[i][j].isShield()) {
                    if (piecePlace[i][j].isKing()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                }
                if (pieces[i][j] && piecePlace[i][j].isFire()==false && piecePlace[i][j].isBomb()) {
                    if (piecePlace[i][j].isKing()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                }
            }
        }
    }

	/** Gets the piece at position (x, y) on the board, 
	  * or null if there is no piece. 
	  * If (x, y) are out of bounds, returns null. 
	 */
	public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }
		if (pieces[x][y]) {
			return piecePlace[x][y]; 
		}
		else {
			return null;
		}
	}


	public boolean canSelect(int x, int y) {
        /* empty square selection */
        if (mustEndTurn) {
            return false;
        }
        if (pieces[x][y]==false) {
            if (validMove(xSaved, ySaved, x, y)) {
                return true;
            }
            else {
                return false;
            }
        }
        /* checks if fire's turn and piece selected is fire */
        else if (piecePlace[x][y].isFire() && firePlayer) {
            return (piecePicked==false || pieceMoved==false);
        } 
        /* checks if water's turn and piece selected is water */       
        else if (piecePlace[x][y].isFire()==false && waterPlayer) {
            return (piecePicked==false || pieceMoved==false);
        } 
        else {
            return false;                   
        }
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
        // check later that if piece already captured, that only that piece can capture again
        if (xf < 0 || yf < 0 || xi < 0 || yi < 0 || xi > 7 || yi > 7|| xf > 7 || yf > 7) {
            return false;
        }
        else if (pieces[xi][yi] && (pieces[xf][yf]==false)) {
            if (firePlayer) {
                // if piece has already captured, can only capture
                if (piecePlace[xi][yi].hasCaptured()) {
                    if ((xf - xi)==(-2) && (yf-yi)==2) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf-1].isFire()));
                    }
                    else if ((xf - xi)==2 && (yf-yi)==2) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf-1].isFire()));
                    }
                    else if (piecePlace[xi][yi].isKing()) {
                        if ((xf - xi)==(-2) && (yf-yi)==(-2)) {
                            return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf+1].isFire()));
                        }
                        else if ((xf - xi)==2 && (yf-yi)==(-2)) {
                            return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf+1].isFire()));
                        }
                    }
                    else {
                        return false;
                    }
                }
                // because it hasn't captured, it can move one space or also capture
                else if ((xf - xi)==1 && (yf-yi)==1) {
                    return true;
                }
                else if ((xf - xi)==(-1) && (yf-yi)==1) {
                    return true;
                }
                // for capturing upwards
                else if ((xf - xi)==(-2) && (yf-yi)==2) {
                    return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf-1].isFire()));
                }
                else if ((xf - xi)==2 && (yf-yi)==2) {
                    return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf-1].isFire()));
                }
                // if it's king, it can capture down and move down too
                else if (piecePlace[xi][yi].isKing()) {
                    if ((xf - xi)==(-2) && (yf-yi)==(-2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf+1].isFire()));
                    }
                    else if ((xf - xi)==2 && (yf-yi)==(-2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf+1].isFire()));
                    }
                    else if ((xf - xi)==1 && (yf-yi)==(-1)) {
                        return true;
                    }  
                    else if ((xf - xi)==(-1) && (yf-yi)==(-1)) {
                        return true;
                    }
                }
                else {
                    if ((xf - xi)==(-2) && (yf-yi)==(-2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf+1].isFire()));
                    }
                    else if ((xf - xi)==2 && (yf-yi)==(-2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf+1].isFire()));
                    }
                }
            }
            else {
                // water's turn
                // if piece has already captured, can only capture
                if (piecePlace[xi][yi].hasCaptured()) {
                    if ((xf - xi)==(-2) && (yf-yi)==(-2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf+1].isFire()));
                    }
                    else if ((xf - xi)==2 && (yf-yi)==(-2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf+1].isFire()));
                    }
                    else if (piecePlace[xi][yi].isKing()) {
                        if ((xf - xi)==(-2) && (yf-yi)==(2)) {
                            return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf-1].isFire()));
                        }
                        else if ((xf - xi)==2 && (yf-yi)==(2)) {
                            return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf-1].isFire()));
                        }
                    }
                    else {
                        return false;
                    }
                }
                // because it hasn't captured, it can move one space or also capture
                else if ((xf - xi)==1 && (yf-yi)==(-1)) {
                    return true;
                }
                else if ((xf - xi)==(-1) && (yf-yi)==(-1)) {
                    return true;
                }
                // for capturing downwards
                else if ((xf - xi)==(-2) && (yf-yi)==(-2)) {
                    return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf+1].isFire()));
                }
                else if ((xf - xi)==2 && (yf-yi)==(-2)) {
                    return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf+1].isFire()));
                }
                // if it's king, it can capture up and move up too
                else if (piecePlace[xi][yi].isKing()) {
                    if ((xf - xi)==(-2) && (yf-yi)==(2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf-1].isFire()));
                    }
                    else if ((xf - xi)==2 && (yf-yi)==(2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf-1].isFire()));
                    }
                    else if ((xf - xi)==1 && (yf-yi)==(1)) {
                        return true;
                    }  
                    else if ((xf - xi)==(-1) && (yf-yi)==(1)) {
                        return true;
                    }
                }
                else {
                    if ((xf - xi)==(-2) && (yf-yi)==(2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf+1][yf-1].isFire()));
                    }
                    else if ((xf - xi)==2 && (yf-yi)==(2)) {
                        return (piecePlace[xi][yi].isFire() != (piecePlace[xf-1][yf-1].isFire()));
                    }
                }
                return false;
            }
            return false;
        }
        else {
            return false;
        }
    } 

	public void select(int x, int y) {
        // also add color background method
        //selects the empty square and places piece there
        if (emptySelected && piecePicked && validMove(xSaved, ySaved, x, y)) {
            piecePlace[xSaved][ySaved].move(xSaved, ySaved);
            piecePlace[xSaved][ySaved].move(x, y);
            pieces[xSaved][ySaved] = false;

            pieceMoved = true;

            if (pieces[x][y] == false) {
                pieceCaptured = true;
                piecePicked = false;
                xSaved = x;
                ySaved = y;
                mustEndTurn = true;
                emptySelected = false;

            }
            
            else if (piecePlace[x][y].hasCaptured()) {
                pieces[x][y] = true;
                emptySelected = true;
                pieceCaptured = true;
                xSaved = x;
                ySaved = y;
                mustEndTurn = false;
                piecePicked = true;
            }
            /* has not captured */
            else {
                pieces[x][y] = true;
                piecePlace[x][y].doneCapturing();
                emptySelected = false;
                piecePicked = false;
                pieceCaptured = false;
                xSaved = x;
                ySaved = y; 
                mustEndTurn = true;

            }
		}
        // selects the piece
        else {
            piecePicked = true;
            xSaved = x;
            ySaved = y;
            emptySelected = true;
        }
	}
	
	/** Places p at (x, y). 
	  * If (x, y) is out of bounds or if p is null, does nothing. 
	  * If p already exists in the current Board, 
	  * first removes it from its initial position. 
	  * If another piece already exists at (x, y), 
	  * p will replace that piece. 
     */
	public void place(Piece p, int x, int y) {
		for (int i = 0; i < pieces.length; i += 1) {
			for (int j = 0; j < pieces.length; j += 1) {
				if (piecePlace[i][j] == p) {
					piecePlace[i][j] = null;
					pieces[i][j] = false;
				}
			}
		}
		if (pieces[x][y] && piecePlace[x][y] != p) {
            noOneWon = false;
			piecePlace[x][y] = p;
            pieces[x][y] = true;
		} 
        noOneWon = false;
		piecePlace[x][y] = p;
		pieces[x][y] = true;
	}
/**  Executes a remove. Returns the piece that was removed. 
  * If the input (x, y) is out of bounds, 
  * returns null and prints an appropriate message. 
  * If there is no piece at (x, y), 
  * returns null and prints an appropriate message. */
	public Piece remove(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            System.out.println("out of bounds");
            return null;
        }
		if (pieces[x][y] == false) {
			System.out.println("No piece there, silly");
			return null;
		}
		else {
			Piece removed = piecePlace[x][y];
            removed.doneCapturing();
			piecePlace[x][y] = null;
			pieces[x][y] = false;
			return removed;
		}
	}

	public boolean canEndTurn() {
        return (pieceMoved || pieceCaptured || mustEndTurn);
    }

    private void clearBoard() {
        for (int i = 0; i < pieces.length; i += 1) {
            for (int j = 0; j < pieces.length; j += 1) { 
                piecePlace[i][j] = null;
                pieces[i][j] = false;
            }
        }
        noOneWon = true;
        waterWon = false;
        fireWon = false;
    }

	public void endTurn() {
        if (waterWon || fireWon || noOneWon) {
            mustEndTurn = true;
            emptySelected = false;
            piecePicked = false;
            pieceMoved = false;
            pieceCaptured = false;
            if (pieces[xSaved][ySaved]) {
                piecePlace[xSaved][ySaved].doneCapturing();
            }
            if (waterPlayer) {
                waterPlayer = false;
                firePlayer = true;
            }
            else if (firePlayer) {
                waterPlayer = true;
                firePlayer = false;
            }
        }
        else if (waterPlayer) {
            waterPlayer = false;
            firePlayer = true;
            pieceMoved = false;
            pieceCaptured = false;
            emptySelected = false;
            piecePicked = false;
            mustEndTurn = false;
            if (pieces[xSaved][ySaved]) {
                piecePlace[xSaved][ySaved].doneCapturing();
            }
        }
        else {
            waterPlayer = true;
            firePlayer = false;
            pieceMoved = false;
            pieceCaptured = false;
            emptySelected = false;
            piecePicked = false;
            mustEndTurn = false;
            if (pieces[xSaved][ySaved]) {
                piecePlace[xSaved][ySaved].doneCapturing();
            }
        }
    }

	public String winner() {
        Piece fire = null;
        Piece water = null;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (piecePlace[i][j] != null && piecePlace[i][j].isFire()) {
                    fire = piecePlace[i][j];
                }
                else if (piecePlace[i][j] != null && piecePlace[i][j].isFire()==false) {
                    water = piecePlace[i][j];
                }
            }
        }
        if (fire == null && water != null) {
            waterWon = true;
            return "Water";
        }
        else if (water == null && fire != null) {
            fireWon = true;
            return "Fire";
        }
        else if (fire == null && water == null) {
            noOneWon = true;
            return "No one";
        }
        else {
            noOneWon = false;
            return null;
        }
    }

}
