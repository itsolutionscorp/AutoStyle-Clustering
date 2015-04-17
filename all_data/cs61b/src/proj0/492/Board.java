public class Board {
    private Piece[][] pieces;
    private boolean Empty;
    private boolean fireTurn = true;
    private boolean waterTurn = false;
    private boolean fHasSelected = false;
    private boolean wHasSelected = true;
    //when fire has moved, this value corresponds to true... how do i make this more general?
    private boolean hasMoved = false;
    private int[] selectedPiece = new int[2];
    private Piece activePiece;
    private double xcoord = 8;
    private double ycoord = 8; 
    public Board(boolean shouldBeEmpty) {
	Empty = shouldBeEmpty;
	StdDrawPlus.setXscale(0, 8);
	StdDrawPlus.setYscale(0, 8);
	pieces = new Piece[8][8];
	if (!Empty) {
	    InitializePieces();
	}
    }
    private void InitializePieces() {
	for (int i = 0; i < 8; i++) {
	       for (int j = 0; j < 8; j++) {
		   if ((j + 1 == 8) && (i % 2 != 0)) {
		       pieces[i][j] = new Piece(false, this, i, j, "pawn");
		    }
		    if ((j + 2 == 8) && (i % 2 == 0)) {
			pieces[i][j] = new Piece(false, this, i, j, "shield");
		    }
		    if ((j + 3 == 8) && (i % 2 != 0)) {
			pieces[i][j] = new Piece(false, this, i, j, "bomb");
		    }
		    if ((j == 0) && (i % 2 == 0)) {
			pieces[i][j] = new Piece(true, this, i, j, "pawn");
		    }
		    if ((j == 1) && (i % 2 != 0)) {
			pieces[i][j] = new Piece(true, this, i, j, "shield");
		    }
		    if ((j == 2) && (i % 2 == 0)) {
			pieces[i][j] = new Piece(true, this, i, j, "bomb");
		    }
	       }
	   } 
    }
    public static void main(String[] args) {	
	Board b = new Board(false); 
	   while(b.winner() == null) {
	       b.drawBoard(8);
	       if (StdDrawPlus.mousePressed()) {
		   b.xcoord = StdDrawPlus.mouseX();
		   b.ycoord = StdDrawPlus.mouseY();
	       }
	       if (b.canSelect((int) b.xcoord, (int) b.ycoord)) {
		   b.select((int) b.xcoord, (int) b.ycoord);
	       }
	       if (StdDrawPlus.isSpacePressed()) {
		   if (b.canEndTurn()) {
		       b.endTurn();
		   }
	       }
	       StdDrawPlus.show(100);
	   }
	   b.winner(); 
    }

    private void drawBoard(int N) {
	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		if ((Empty == false) && (pieces[i][j] != null)) {
		    boolean fireKing = (pieces[i][j].isFire() && pieces[i][j].isKing());
		    boolean waterKing = ((!pieces[i][j].isFire()) && pieces[i][j].isKing());
		    boolean fireImg = pieces[i][j].isFire();
		    boolean bombImg = pieces[i][j].isBomb();
		    boolean shieldImg = pieces[i][j].isShield();
		    if (fireKing) {
			if (bombImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
			} else if (shieldImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
			}
		    } else if (waterKing) {
			if (bombImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
			} else if (shieldImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
			}
		    } else if (fireImg) {
			if (bombImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
			} else if (shieldImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
			}
		    } else {
			if (bombImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			} else if (shieldImg) {
			    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
			} else {
			    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			}
		    }
			
		}
            }
        }
    }
    public Piece pieceAt(int x, int y) {
	//sets it equal to i
	int columns = pieces.length;
	//sets it equal to j
	int rows = pieces[0].length;
	if ((x > columns - 1) || (y > rows - 1) || (x < 0) || (y < 0)) {
	    return null;
	} else if (pieces[x][y] == null) {
	    return null;
	} else {
	    return pieces[x][y];
	}
    }
    //make tests for this
    //see if it works
    //can you have return statements in a void method?
    public void place(Piece p, int x, int y) {
	int columns = pieces.length;
	int rows = pieces[0].length;
	//DON'T FORGOT TO CHECK IF THE (X,Y) IS A RED SQUARE
	//checks out of bounds
	if((x > columns - 1) || (y > rows - 1) || (x < 0) || (y < 0)) {
	    return;
	}
	//checks if it is null
	if (p == null) {
	    return;
	}
	//place p at (x, y)
	if (pieces[x][y] != null) {
	    remove(x, y);
	}
	pieces[x][y] = p;
    }
    //make tests
    public Piece remove(int x, int y) {
	int columns = pieces.length;
	int rows = pieces[0].length;
	if((x > columns - 1) || (y > rows - 1) || (x < 0) || (y < 0)) {
	    System.out.println("The position " + x + " , " + y + " is not on the board.");
	    return null;
	} else if (pieces[x][y] == null) {
	    System.out.println("There is no piece at " + x + " , " + y + ".");
	    return null;
	}
	Piece p = pieces[x][y];
	//removes it from the array
	pieces[x][y] = null;
	//return the piece
	return p;
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
	int columns = pieces.length;
	int rows = pieces[0].length;
	//if xf or yf are not on the board, not valid
	if((xf > columns - 1) || (yf > rows - 1)) {
	    return false;
	}
	if((xf < 0) || (yf < 0)) {
	    return false;
	}
	//if there is no piece at initial, not valid
	if (pieces[xi][yi] == null) {
	    return false;
	}
	//initial piece must be that of the turn type
	if (fireTurn && (!pieces[xi][yi].isFire())) {
	    return false;
	}
	if (waterTurn && pieces[xi][yi].isFire()) {
	    return false;
	}
	//if there is already a piece there, not valid
	if (pieces[xf][yf] != null) {
	    return false;
	}

	//multicapture 
	   if (fireTurn && ((pieces[xi][yi].hasCaptured()))) {
	      if ((yf == yi + 2) && (xf == xi + 2) && (pieces[xi + 1][yi + 1] != null) && (!pieces[xi + 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi + 2) && (xf == xi - 2) && (pieces[xi - 1][yi + 1] != null) && (!pieces[xi - 1][yi+1].isFire())) {
		return true;
	    }
	    }
	
	   if (waterTurn && ((pieces[xi][yi].hasCaptured()))) {
	       if ((yf == yi - 2) && (xf == xi + 2) && (pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi-1].isFire())) {
		  return true;
	    } else if ((yf == yi - 2) && (xf == xi - 2) && (pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi-1].isFire())) {
		return true;
	    }
	    }
	   //multicapture for kings
	   if (fireTurn && ((pieces[xi][yi].hasCaptured())) && pieces[xi][yi].isKing() == true) {
	      if ((yf == yi + 2) && (xf == xi + 2) && (pieces[xi + 1][yi + 1] != null) && (!pieces[xi + 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi + 2) && (xf == xi - 2) && (pieces[xi - 1][yi + 1] != null) && (!pieces[xi - 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi + 2) && (pieces[xi + 1][yi - 1] != null) && (!pieces[xi + 1][yi - 1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi - 2) && (pieces[xi - 1][yi - 1] != null) && (!pieces[xi - 1][yi - 1].isFire())) {
		return true;
	    }
	   }
	   if (waterTurn && ((pieces[xi][yi].hasCaptured())) && pieces[xi][yi].isKing() == true) {
	      if ((yf == yi + 2) && (xf == xi + 2) && (pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi + 2) && (xf == xi - 2) && (pieces[xi - 1][yi + 1] != null) && (pieces[xi - 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi + 2) && (pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi - 1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi - 2) && (pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi - 1].isFire())) {
		return true;
	    }
	   }
	//return true if the space is one diagonal away
	if (fireTurn && ((!pieces[xi][yi].hasCaptured()))) {
	    if ((yf == yi + 1) && ((xf == xi + 1) || (xf == xi - 1))) {
		return true;
	    }
	} else if (waterTurn && ((!pieces[xi][yi].hasCaptured()))) {
	    if ((yf == yi - 1) && ((xf == xi + 1) || (xf == xi - 1))) {
		return true;
	    }
	}
	//if the piece is a king, return true if the space is one diagonal away
	//can move up or down
	if (fireTurn && ((!pieces[xi][yi].hasCaptured())) && pieces[xi][yi].isKing() == true) {
	    if (((yf == yi + 1) || (yf == yi - 1)) && ((xf == xi + 1) || (xf == xi - 1))) {
		return true;
	    }
	} else if (waterTurn && ((!pieces[xi][yi].hasCaptured())) && pieces[xi][yi].isKing() == true) {
	    if (((yf == yi + 1) || (yf == yi - 1)) && ((xf == xi + 1) || (xf == xi - 1))) {
		return true;
	    }
	}
	//if it is two diagonals away, there must be a piece OF THE OPPOSITE TYPE in between
	if (fireTurn) {
	    if ((yf == yi + 2) && (xf == xi + 2) && (pieces[xi + 1][yi + 1] != null) && (!pieces[xi + 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi + 2) && (xf == xi - 2) && (pieces[xi - 1][yi + 1] != null) && (!pieces[xi - 1][yi+1].isFire())) {
		return true;
	    }
	} else if (waterTurn) {
	    if ((yf == yi - 2) && (xf == xi + 2) && (pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi-1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi - 2) && (pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi-1].isFire())) {
		return true;
	    }
	}
	//if the piece is a king and it is two diagonals away, there must be a piece OF THE OPPOSITE TYPE in between
	//can move up or down
	if (fireTurn && pieces[xi][yi].isKing() == true) {
	    if ((yf == yi + 2) && (xf == xi + 2) && (pieces[xi + 1][yi + 1] != null) && (!pieces[xi + 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi + 2) && (xf == xi - 2) && (pieces[xi - 1][yi + 1] != null) && (!pieces[xi - 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi + 2) && (pieces[xi + 1][yi - 1] != null) && (!pieces[xi + 1][yi - 1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi - 2) && (pieces[xi - 1][yi - 1] != null) && (!pieces[xi - 1][yi - 1].isFire())) {
		return true;
	    }
	} else if (waterTurn && pieces[xi][yi].isKing() == true) {
	    if ((yf == yi + 2) && (xf == xi + 2) && (pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi + 2) && (xf == xi - 2) && (pieces[xi - 1][yi + 1] != null) && (pieces[xi - 1][yi+1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi + 2) && (pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi - 1].isFire())) {
		return true;
	    } else if ((yf == yi - 2) && (xf == xi - 2) && (pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi - 1].isFire())) {
		return true;
	    }
	}
	//if it hasn't returned true by now, it isn't valid 
	return false;
    }
    public boolean canSelect(int x, int y) {
	int columns = pieces.length;
	int rows = pieces[0].length;
	//check if out of bounds
	if((x > columns - 1) || (y > rows - 1) || (x < 0) || (y < 0)) {
	    return false;
	}
	//check if empty space can be selected
	if(pieces[x][y] == null) {
	    if((selectedPiece != null) && (!hasMoved) && (validMove(selectedPiece[0], selectedPiece[1], x, y))) {
		return true;
	    }
	    //return false;
	}
	//handle multiple captures
	if(pieces[x][y] == null) {
	    if(activePiece != null && activePiece.hasCaptured()) {
		return (validMove(selectedPiece[0], selectedPiece[1], x, y));
	    }
	    return false;
	}
	//has not selected a piece yet
	if(pieces[x][y].isFire() && fHasSelected == false && fireTurn) {
	    return true;
	} else if ((!pieces[x][y].isFire()) && wHasSelected == false && waterTurn) {
	    return true;
	}
	//has selected but not moved
	if(pieces[x][y].isFire() && fireTurn && (!hasMoved)) {
	    return true;
	} else if ((!pieces[x][y].isFire()) && waterTurn && (!hasMoved)) {
	    return true;
	}
	return false;
    }
    public void select(int x, int y) {
	//select the piece if there is a piece there
	if(pieces[x][y] != null) {
	    selectedPiece[0] = x;
	    selectedPiece[1] = y;
	    activePiece = pieces[x][y];
	    if (fireTurn) {
		fHasSelected = true;
	    } else {
		wHasSelected = true;
	    }
	    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	} else {
	    //call move method
	    selectedPiece[0] = x;
	    selectedPiece[1] = y;
	    activePiece.move(x, y);
	    hasMoved = true;
	}
    }
    public boolean canEndTurn() {
	if(activePiece == null) {
	    return false;
	}       
	if (hasMoved || activePiece.hasCaptured()) {
	    return true;
	}
	return false;
    }
    public void endTurn() {
	if (fireTurn) {
	    fireTurn = false;
	    waterTurn = true;
	    //check to make sure it didn't self destruct
	    if (activePiece != null) {
		activePiece.doneCapturing();
	    }
	    //change selected piece to null
	    fHasSelected = false;
	    hasMoved = false;
	    activePiece = null;
	} else if (waterTurn) {
	    fireTurn = true;
	    waterTurn = false;
	    //check to make sure it didn't self destruct
	    if (activePiece != null) {
		activePiece.doneCapturing();
	    }
	    wHasSelected = false;
	    hasMoved = false;
	    activePiece = null;
	}
    }
    public String winner() {
	boolean fireRemains = false;
	boolean waterRemains = false;
	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
		if (pieces[i][j] != null) {
		    if(pieces[i][j].isFire()) {
			fireRemains = true;
		    } else {
			waterRemains = true;
		    }
		}
	    }
	}
	if (waterRemains && fireRemains) {
	    return null;
	} else if (waterRemains) {
	    return "Water";
	} else if (fireRemains) {
	    return "Fire";
	} else {
	    return "No one";
	}
    }
}

