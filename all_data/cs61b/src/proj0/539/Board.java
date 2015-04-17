public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private int firePieces = 0;
    private int waterPieces = 0;
    //0 is fire, 1 is water.
    private Piece selected;
    private int currentPlayer = 0;
    private boolean moveMade = false;
    private int xSelected;
    private int ySelected;
    private boolean gameOver = false;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
    **/
    private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                Piece p = pieces[i][j];
                if ((p != null) && (p == selected)){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {                 
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (p != null) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + getType(p) + getSide(p) + getKing(p) + ".png", 1, 1);
                }
    		}
    	}
    }

    private String getType(Piece p) {
        if (p.isBomb()) {
            return "bomb";
        }
        else if (p.isShield()) {
            return "shield";
        }
        return "pawn";
    }

    private String getSide(Piece p) {
        if (p.side() == 0) {
            return "-fire";
        }
        return "-water";
    }


    private String getKing(Piece p) {
        if (p.isKing()) {
            return "-crowned";
        }
        return "";
    }


    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
       	while(!b.gameOver) {   
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x ,y)) {
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
            }
            b.drawBoard(8); 
            StdDrawPlus.show(10);
        }
        
    }


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			for (int i = 0; i < 8; i +=2) {
				place(new Piece(true, this, i, 0, "pawn"), i, 0);
            }
			for (int i = 1; i < 8; i +=2) {
				place(new Piece(true, this, i, 1, "shield"), i, 1);
            }
			for (int i = 0; i < 8; i +=2) {
				place(new Piece(true, this, i, 2, "bomb"), i, 2);
            }
			for (int i = 1; i < 8; i +=2) {
				place(new Piece(false, this, i, 5, "bomb"), i, 5);
            }
			for (int i = 0; i < 8; i +=2) {
				place(new Piece(false, this, i, 6, "shield"), i ,6);
            }
			for (int i = 1; i < 8; i +=2) {
				place(new Piece(false, this, i, 7, "pawn"), i, 7);
            }
		}
	}


	public Piece pieceAt(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return null;
        }
        return pieces[x][y];
	}


	public boolean canSelect(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return false;
        }
        Piece p = pieces[x][y];
        if (p != null && p.side() == currentPlayer && !moveMade) {
            return true;
        }
        else if (p == null && selected != null && !moveMade && validMove(xSelected, x, ySelected, y)) {
            return true;
        }
        else if (p == null && selected != null && selected.hasCaptured() && validMove(xSelected, x, ySelected, y)) { 
            return true;
        }
        return false;
	}


    private boolean validMove(int xi, int xf, int yi, int yf) {
        if ((xf - xi == 1 || xf - xi == -1) && !selected.hasCaptured()) {
            return validMoveHelper(xi, xf, yi, yf, 1);
        }
        else if ((xf - xi == 2 || xf - xi == -2) && pieceAt((xf + xi)/2, (yf+yi)/2) != null && pieceAt((xf + xi)/2, (yf+yi)/2).side() != currentPlayer) {
            return validMoveHelper(xi, xf, yi, yf, 2);
        }
        return false;
    }


    private boolean validMoveHelper(int xi, int xf, int yi, int yf, int n) {
        if (pieces[xi][yi].isFire() && (yf - yi == n)) {
            return true;
        }
        else if (!pieces[xi][yi].isFire() && (yf - yi == -n)) {
            return true;
        }
        else if (pieces[xi][yi].isKing() && ((yf - yi == n) || (yf - yi == -n))) {
            return true;
        }
        return false;
    }


	public void select(int x, int y) {
        Piece p = pieces[x][y];
        if (p == null && selected != null && (!moveMade || selected.hasCaptured())) {
            selected.move(x, y);
            moveMade = true;
        }
        else if (p != null && !moveMade) {
            selected = pieceAt(x, y);
        }
        xSelected = x;
        ySelected = y;
	}


	public void place(Piece p, int x, int y) {
        if (p != null && x < 8 && x >= 0 && y < 8 && y >= 0) {
            pieces[x][y] = p;
            pieceCounter(p, 1);
        }
	}


	public Piece remove(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            System.out.println("Invalid Coordinates.");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("No piece in that spot.");
            return null;
        }
        else {
            Piece toReturn = pieces[x][y];
            pieceCounter(toReturn, -1);
            pieces[x][y] = null;
            return toReturn;
        }
	}

    private void pieceCounter(Piece p, int i) {
        if (p.isFire()) {
            firePieces += i;
        }
        else {
            waterPieces += i;
        }
    }


	public boolean canEndTurn() {
        if (moveMade) {
            return true;
        }
        return false;
	}


	public void endTurn() {
        currentPlayer = 1 - currentPlayer;
        selected.doneCapturing();
        selected = null;
        moveMade = false;
        if (winner() == "Fire" || winner() == "Water") {
            gameOver = true;
        }  
	}


	public String winner() {
        if (firePieces == 0 && waterPieces == 0) {
            return "No one";
        }
        else if (firePieces == 0) {
            return "Water";
        }
        else if (waterPieces == 0) {
            return "Fire";
        }
        return null;
	}


}
