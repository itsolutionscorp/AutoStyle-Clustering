public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private boolean canEndTurn;

    private boolean isFireTurn;
    private Piece pieceSelected;
    private boolean movedThisTurn;
    
    public static void main(String[] args) {
	Board b = new Board(false);

	while (true) {
	    b.drawBoard();

	    // selection
	    if (StdDrawPlus.mousePressed()) {
		if ((StdDrawPlus.mouseX() >= 0) &&
		    (StdDrawPlus.mouseX() < 8) &&
		    (StdDrawPlus.mouseY() >= 0) &&
		    (StdDrawPlus.mouseY() < 8)) {
		    if (b.canSelect((int) StdDrawPlus.mouseX(),
				    (int) StdDrawPlus.mouseY()))
			b.select((int) StdDrawPlus.mouseX(),
				 (int) StdDrawPlus.mouseY());
		}
	    }

	    // if you can end your turn
	    if (b.canEndTurn()) {
		if (StdDrawPlus.isSpacePressed()){
		    b.endTurn();
		}
	    }

	    // if 'n' is pressed, starts a new game
	    if (StdDrawPlus.isKeyPressed(78))
		b = new Board(false);
	    
	    StdDrawPlus.show(25);
	}
    }

    // initializes an empty board if shouldBeEmpty is true
    // otherwise, initializes board in default configuration
    public Board(boolean shouldBeEmpty) {
	this.isFireTurn = true; // Fire always goes first
	this.canEndTurn = false; // Starts at beginning of a turn
	this.movedThisTurn = false; // Nothing has been moved yet
	
	if (!shouldBeEmpty) {
	    //these are the standard checkers pieces
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
	    pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
	    pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
	    pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
	    pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
	    pieces[0][6] = new Piece(false, this, 0, 6, "shield");
	    pieces[2][6] = new Piece(false, this, 2, 6, "shield");
	    pieces[4][6] = new Piece(false, this, 4, 6, "shield");
	    pieces[6][6] = new Piece(false, this, 6, 6, "shield");
	    pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
	    pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
	    pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
	    pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
	}
    }

    // NOTE: some of the code is very similar to StdDrawDemo provided
    private void drawBoard() {
	// draws an 8 x 8 board
	StdDrawPlus.setXscale(0, 8);
	StdDrawPlus.setYscale(0, 8);
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if ((i + j) % 2 == 0)
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		else
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(j + .5, i + .5, .5);
		// draw in the pieces
		if (pieces[j][i] != null) {
		    if (pieces[j][i] == pieceSelected) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(j + .5, i + .5, .5);
		    }
		    if (pieces[j][i].isFire()) {
			String s;
			if (pieces[j][i].isBomb()) {
				s = "img/bomb-fire";
			} else if (pieces[j][i].isShield()) {
				s = "img/shield-fire";
			} else {
				s = "img/pawn-fire";
			}
			if (pieces[j][i].isKing()) {
			    StdDrawPlus.picture(j + .5, i + .5,
						s + "-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(j + .5, i + .5,
						s + ".png", 1, 1);
			}
		    } else {
			String s;
			if (pieces[j][i].isBomb()) {
			    s = "img/bomb-water";
			} else if (pieces[j][i].isShield()) {
			    s = "img/shield-water";
			} else {
			    s = "img/pawn-water";
			}
			if (pieces[j][i].isKing()) {
			    StdDrawPlus.picture(j + .5, i + .5,
						s + "-crowned.png", 1, 1);
			} else {
			    StdDrawPlus.picture(j + .5, i + .5,
						s + ".png", 1, 1);
			}
		    }
		}
	    }
	}

	StdDrawPlus.show();
    }

    public Piece pieceAt(int x, int y) {
	if ((x >=0) && (x <= 7) && (y >= 0) && (y <= 7))
	    return pieces[x][y];
	return null;
    }

    public void place(Piece p, int x, int y) {
	if ((x > 7) || (x < 0) || (y > 7) || (y < 0))
	    return;

	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if (p == pieces[j][i])
		    pieces[j][i] = null;
	    }
	}

	pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
	if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
	    System.out.println("Position out of bounds");
	    return null;
	}

	if (pieces[x][y] == null) {
	    System.out.println("No piece in this position");
	    return null;
	}

	Piece s = pieces[x][y];
	pieces[x][y] = null;
	return s;
    }

    public boolean canSelect(int x, int y) {
	if (!movedThisTurn) {
	    if (isFireTurn) {
		if ((pieces[x][y] != null) && (pieceAt(x, y).isFire()))
		    return true;
	    } else { // it's Water's turn
		if ((pieces[x][y] != null) && (!pieceAt(x, y).isFire()))
		    return true;
	    }
	}
	if (pieceSelected != null) { // if something is selected
	    int xi = -1;
	    int yi = -1;
	    for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
		    if (pieceSelected == pieces[j][i]) {
			xi = j;
			yi = i;
		    }
		}
	    }
	    return validMove(xi, yi, x, y);
	}
	return false;
    }

    public void select(int x, int y) {
	if (pieces[x][y] == null) { // if it is an empty space
	    pieceSelected.move(x, y);
	    canEndTurn = true;
	    movedThisTurn = true;
	} else { // if there is a piece at that space
	    pieceSelected = pieces[x][y];
	}
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
	if ((xi < 0) || (xi > 7) || (yi < 0) || (yi > 7) ||
	    (xf < 0) || (xf > 7) || (yf < 0) || (yi > 7))
	    return false; // out of bounds

	if (pieces[xf][yf] != null) {
	    return false;
	}
	
	if (isFireTurn) { // move upwards unless king
	    if (!movedThisTurn) { // move cases
		if ((xf - 1 >= 0) && (yf - 1 >= 0)) {
		    // lower left case
		    if (pieces[xf - 1][yf - 1] == pieces[xi][yi])
			return true;
		}
		if ((xf + 1 <= 7) && (yf - 1 >= 0)) {
		    // lower right case
		    if (pieces[xf + 1][yf - 1] == pieces[xi][yi])
			return true;
		}
	    }
	    if ((!movedThisTurn) || (pieces[xi][yi].hasCaptured())) {
		//capture cases
		if ((xf - 2 >= 0) && (yf - 2 >= 0)) {
		    // lower left capture case
		    if ((pieces[xf - 2][yf - 2] == pieces[xi][yi]) &&
			(pieces[xf - 1][yf - 1] != null)) {
			if (!pieces[xf - 1][yf - 1].isFire())
			    return true;
		    }
		}
		if ((xf + 2 <= 7) && (yf - 2 >= 0)) {
		    // lower right capture case
		    if ((pieces[xf + 2][yf - 2] == pieces[xi][yi]) &&
			(pieces[xf + 1][yf - 1] != null)) {
			if (!pieces[xf + 1][yf - 1].isFire())
			    return true;
		    }
		}
	    }
	    if (pieces[xi][yi].isKing()) {
		// king cases
		if (!movedThisTurn) { // move case
		    if ((xf - 1 >= 0) && (yf + 1 <= 7)) {
			// upper left case
			if (pieces[xf - 1][yf + 1] == pieces[xi][yi])
			    return true;
		    }
		    if ((xf + 1 <= 7) && (yf + 1 <= 7)) {
			// upper right case
			if (pieces[xf + 1][yf + 1] == pieces[xi][yi])
			    return true;
		    }
		}
		if ((!movedThisTurn) || (pieces[xi][yi].hasCaptured())) {
		    // capture case
		    if ((xf - 2 >= 0) && (yf + 2 <= 7)) {
			// upper left capture case
			if ((pieces[xf - 2][yf + 2] == pieces[xi][yi]) &&
			    (pieces[xf - 1][yf + 1] != null)) {
			    if (!pieces[xf - 1][yf + 1].isFire())
				return true;
			}
		    }
		    if ((xf + 2 >= 0) && (yf + 2 <= 7)) {
			// upper right capture case
			if ((pieces[xf + 2][yf + 2] == pieces[xi][yi]) &&
			    (pieces[xf + 1][yf + 1] != null)) {
			    if (!pieces[xf + 1][yf + 1].isFire())
				return true;
			}
		    }
		}
	    }
	    return false;
	} else { // move downwards unless king
	    if (!movedThisTurn) { // move case
		if ((xf - 1 >= 0) && (yf + 1 <= 7)) {
		    // upper left case
		    if (pieces[xf - 1][yf + 1] == pieces[xi][yi])
			return true;
		}
		if ((xf + 1 <= 7) && (yf + 1 <= 7)) {
		    // upper right case
		    if (pieces[xf + 1][yf + 1] == pieces[xi][yi])
			return true;
		}
	    }
	    if ((!movedThisTurn) || (pieces[xi][yi].hasCaptured())) {
		// capture case
		if ((xf - 2 >= 0) && (yf + 2 <= 7)) {
		    // upper left capture case
		    if ((pieces[xf - 2][yf + 2] == pieces[xi][yi]) &&
			(pieces[xf - 1][yf + 1] != null)) {
			if (pieces[xf - 1][yf + 1].isFire())
			    return true;
		    }
		}
		if ((xf + 2 <= 7) && (yf + 2 <= 7)) {
		    // upper right capture case
		    if ((pieces[xf + 2][yf + 2] == pieces[xi][yi]) &&
			(pieces[xf + 1][yf + 1] != null)) {
			if (pieces[xf + 1][yf + 1].isFire())
			    return true;
		    }
		}
	    }
	    if (pieces[xi][yi].isKing()) {
		// king cases
		if (!movedThisTurn) { // move case
		    if ((xf - 1 >= 0) && (yf - 1 >= 0)) {
			// lower left case
			if (pieces[xf - 1][yf - 1] == pieces[xi][yi])
			    return true;
		    }
		    if ((xf + 1 <= 7) && (yf - 1 >= 0)) {
			// lower right case
			if (pieces[xf + 1][yf - 1] == pieces[xi][yi])
			    return true;
		    }
		}
		if ((!movedThisTurn) || (pieces[xi][yi].hasCaptured())) {
		    //capture case
		    if ((xf - 2 >= 0) && (yf - 2 >= 0)) {
			// lower left capture case
			if ((pieces[xf - 2][yf - 2] == pieces[xi][yi]) &&
			    (pieces[xf - 1][yf - 1] != null)) {
			    if (pieces[xf - 1][yf - 1].isFire())
				return true;
			}
		    }
		    if ((xf + 2 <= 7) && (yf - 2 >= 0)) {
			// lower right capture case
			if ((pieces[xf + 2][yf - 2] == pieces[xi][yi]) &&
			    (pieces[xf + 1][yf - 1] != null)) {
			    if (pieces[xf + 1][yf - 1].isFire())
				return true;
			}
		    }
		}
	    }
	    return false;
	}
    }

    public boolean canEndTurn() {
	return canEndTurn;
    }

    public void endTurn() {
	if (canEndTurn) {
	    pieceSelected.doneCapturing();
	    pieceSelected = null;
	    if (isFireTurn)
		isFireTurn = false;
	    else
		isFireTurn = true;
	    movedThisTurn = false;
	    canEndTurn = false;
	}
    }

    public String winner() {
	int firePieces = 0;
	int waterPieces = 0;	
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if (pieces[j][i] != null) {
		    if (pieceAt(j, i).isFire()) {
			firePieces += 1;
		    } else {
			waterPieces += 1;
		    }
		}
	    }
	}

	if ((waterPieces == 0) && (firePieces == 0))
	    return "No one";
	if (waterPieces == 0)
	    return "Fire";
	if (firePieces == 0)
	    return "Water";
	return null;
    }
    
}
