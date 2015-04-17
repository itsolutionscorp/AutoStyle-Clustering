/** 
 *  Much of this was taken directly from StdDrawDemo.java
 */

public class Board {
    /** Location of pieces. */
    private  Piece[][] pieces = new Piece[8][8];
    private  boolean fireTurn;
    private boolean hasMoved;
    private Piece pieceSelected;
    private int xSel, ySel;
    private boolean hasExploded = false;

    public Board(boolean shouldBeEmpty) {
	fireTurn = true;
        hasMoved = false;
        pieceSelected = null;
        xSel = -1;
	ySel = -1;
	if (shouldBeEmpty) return;
	pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
	pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
	pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
	pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
	pieces[1][1] = new Piece(true, this, 1, 1, "shield");
	pieces[3][1] = new Piece(true, this, 3, 1, "shield");
	pieces[5][1] = new Piece(true, this, 5, 1, "shield");
	pieces[7][1] = new Piece(true, this, 7, 1, "shield");
	pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
	pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
	pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
	pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
	pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
	pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
	pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
	pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
	pieces[0][6] = new Piece(false, this, 0, 6, "shield");
	pieces[2][6] = new Piece(false, this, 2, 6, "shield");
	pieces[4][6] = new Piece(false, this, 4, 6, "shield");
	pieces[6][6] = new Piece(false, this, 6, 6, "shield");
	pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
	pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
	pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
	pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
    }
    private boolean pieceExists(Piece p) {
	boolean pieceIn = false;
	for(int i = 0; i < 8; i++) {
	    for(int j = 0; j < 8; j++) {
		if (p == pieceAt(i, j)) pieceIn = true;
	    }
	}
	return pieceIn;
    }

    private  String getImg(Piece p) {
	String img = "";
	if (p.isFire()) {
	    if (p.isShield()) img = "img/shield-fire.png";
	    else if (p.isBomb()) img =  "img/bomb-fire.png";
	    else img = "img/pawn-fire.png";
	}
	else {
	    if (p.isShield()) img = "img/shield-water.png";
	    else if (p.isBomb()) img =  "img/bomb-water.png";
	    else img = "img/pawn-water.png";
	}
	return img;
    }
    private  void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (i == xSel && j == ySel) StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
		else StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImg(pieceAt(i, j)), 1, 1);
                }
            }
        }
    }

    public  Piece pieceAt(int x, int y) {
	if (x > 7 || y > 7) return null;
	return pieces[x][y];		
    }

    public  void place(Piece p, int x, int y) {
	if (x > 7 || y > 7 || p == null) return;
	if (pieceExists(p)) {
	    remove(xSel, ySel);
	    pieces[x][y] = p;
	    hasMoved = true;
	    if (Math.abs(ySel - y) == 2 && p.isBomb()) hasExploded = true;
	    xSel = -1;
	    ySel = -1;
	}
	else {
	    pieces[x][y] = p;
	}
	return;
    }
	
   public String winner() {
	int allFire = 0;
	for(int i = 0; i < 8; i++) {
	    for(int j = 0; j < 8; j++) {
	        if (pieces[i][j] != null) allFire += pieces[i][j].side();
	    }
	}
	boolean someFire = false;
	for(int i = 0; i < 8; i++) {
	    for(int j = 0; j < 8; j++) {
	       if (pieces[i][j] != null && pieces[i][j].isFire()) {
	        someFire = true;
		break;
	       }   
	    }
	}
	if (allFire == 0 && someFire == false) return "No one";	
	if (allFire == 0) return "Fire";
	if (someFire == false) return "Water";	
	return null;
   }
 
   public  Piece remove(int x, int y) {
	if (x > 7 || y > 7) {
	    System.out.println("Location out of bounds");	
	    return null;
	}
	if (pieces[x][y] == null){
	    System.out.println("No piece at "+x+", "+y);
	    return null;
	}	
	Piece removeP = pieceAt(x, y);
	pieces[x][y] = null;
	return removeP;
    }

    //this is a monster	
    private  boolean validMove(int x, int y, boolean multiCapture) {
	if (multiCapture) {
	    if (pieceSelected.isKing()) {
	    if (y == ySel + 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel+1] != null && pieceAt(xSel+1, ySel+1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel+1] != null && pieceAt(xSel-1, ySel+1).isFire() != fireTurn;
		}

	    }
	    else if (y == ySel - 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel-1] != null && pieceAt(xSel+1, ySel-1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel-1] != null && pieceAt(xSel-1, ySel-1).isFire() != fireTurn;
		}
	    }

	}
	else if (pieceSelected.isFire()) {
	    if (y == ySel + 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel+1] != null && pieceAt(xSel+1, ySel+1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel+1] != null && pieceAt(xSel-1, ySel+1).isFire() != fireTurn;
		}

	    }
	}
	else {
	    if (y == ySel - 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel-1] != null && pieceAt(xSel+1, ySel-1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel-1] != null && pieceAt(xSel-1, ySel-1).isFire() != fireTurn;
		}

	    }
	}
	return false;
	}
	else {
	if (pieceSelected.isKing()) {
	    if (y == ySel + 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel+1] != null && pieceAt(xSel+1, ySel+1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel+1] != null && pieceAt(xSel-1, ySel+1).isFire() != fireTurn;
		}

	    }
	    else if (y == ySel - 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel-1] != null && pieceAt(xSel+1, ySel-1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel-1] != null && pieceAt(xSel-1, ySel-1).isFire() != fireTurn;
		}

	    }
	    else {
	 	if ((y == ySel - 1 || y == ySel + 1) && (x == xSel + 1 || x == xSel - 1)) return true;	
	    }

	}
	else if (pieceSelected.isFire()) {
	    if (y == ySel + 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel+1] != null && pieceAt(xSel+1, ySel+1).isFire() != fireTurn;
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel+1] != null && pieceAt(xSel-1, ySel+1).isFire() != fireTurn;
		}

	    }
	    else {
	 	return(y == ySel + 1 && (x == xSel + 1 || x == xSel - 1));	
	    }
	}
	else {
	    if (y == ySel - 2) {
		if (x == xSel + 2) {
 		    return pieces[xSel+1][ySel-1] != null && (pieceAt(xSel+1, ySel-1).isFire() != fireTurn);
		}
		if (x == xSel - 2) {
 		    return pieces[xSel-1][ySel-1] != null && pieceAt(xSel-1, ySel-1).isFire() != fireTurn;
		}

	    }
	    else {
	 	return (y == ySel - 1 && (x == xSel + 1 || x == xSel - 1));	
	    }
	}
	return false;
	}
    }

    public  boolean canSelect(int x, int y) {
	if (pieces[x][y] != null) {
	    if (fireTurn == pieceAt(x, y).isFire()) {
		if ((!hasMoved || pieceSelected == null) && hasExploded == false) return true;
	    }
	}
	else {
	    if (pieceSelected != null && hasMoved == false) return validMove(x, y, false);
	    if (pieceSelected != null && pieceSelected.hasCaptured() && hasMoved == true) {
		return validMove(x, y, true);
	    }
	}
	return false;	
    }
	
    public  void select(int x, int y) {
	    if (pieceSelected != null && pieces[x][y] == null) {
		pieceSelected.move(x, y);
		pieceSelected = null;
	    }
	    if (pieces[x][y] != null) {
		pieceSelected = pieceAt(x, y);	
		xSel = x;
		ySel = y;
	    }
    }

    public boolean canEndTurn() {
	return hasMoved;
    }

    public  void endTurn () {
	fireTurn = !fireTurn;
        hasMoved = false;
        pieceSelected = null;
        xSel = -1;
	ySel = -1;
	hasExploded = false;
	for(int i = 0; i < 8; i++) {
	    for(int j = 0; j < 8; j++) {
	        if (pieces[i][j] != null) pieces[i][j].doneCapturing();
	    }
	}

	
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board gameBoard = new Board(false);
        while(true) {
            gameBoard.drawBoard(8);
	    StdDrawPlus.show(15);
	    //check for mouseclick to do select
	    if (StdDrawPlus.mousePressed()) {
		int x = ((int) StdDrawPlus.mouseX());
		int y = ((int) StdDrawPlus.mouseY());
		if (gameBoard.canSelect(x, y)) gameBoard.select(x, y);
	    }
	    //check for space bar to end turn
	    if (StdDrawPlus.isSpacePressed()) {
		if (gameBoard.canEndTurn()) {
		    gameBoard.endTurn();
		    if (gameBoard.winner() != null && !gameBoard.winner().equals("No one")) {
			System.out.println(gameBoard.winner());
			return;
		    }
		}
	    }
        }
    }
}
