public class Board {
    private Piece[][] pieces;
    private boolean hasSelectedPiece, hasMovedPiece;
    private int sideToMove; // 0 if the side to move is fire, 1 if it is water
    private int selectedPieceX, selectedPieceY;
    
    public Board(boolean shouldBeEmpty) {
	pieces = new Piece[8][8];
	boolean shouldBeFire = false;
	String pieceType = "";
	sideToMove = 0;
	
	if (!shouldBeEmpty) {
	    for (int x = 0; x < 8; x++) {
		for (int y = 0; y < 8; y++) {
		    if ((x + y) % 2 == 0 && y != 3 && y != 4) {
			if (y == 0) {
			    shouldBeFire = true;
			    pieceType = "pawn";
			}
			if (y == 1) {
			    shouldBeFire = true;
			    pieceType = "shield";
			}
			if (y == 2) {
			    shouldBeFire = true;
			    pieceType = "bomb";
			}
			if (y == 7) {
			    shouldBeFire = false;
			    pieceType = "pawn";
			}
			if (y == 6) {
			    shouldBeFire = false;
			    pieceType = "shield";
			}
			if (y == 5) {
			    shouldBeFire = false;
			    pieceType = "bomb";
			}
			place(new Piece(shouldBeFire, this, x, y, pieceType), x, y);
		    }
		}
	    }
	}
    }

    public void place(Piece p, int x, int y) {
	if (!(x < 0 || x > 7 || y < 0 || y > 7 || p == null)) {
	    pieces[x][y] = p;
	}
    }

    public Piece remove(int x, int y) {
	if (x < 0 || x > 7 || y < 0 || y > 7) {
	    System.out.println("Board.remove: coordinates out of bounds");
	    return null;
	}
	Piece piece = pieceAt(x, y);
	if (piece == null) {
	    System.out.println("Board.remove: no piece at given coordinates");
	    return null;
	}
	pieces[x][y] = null;
	return piece;
    }

    public Piece pieceAt(int x, int y) {
	if (x < 0 || x > 7 || y < 0 || y > 7) {
	    return null;
	}
	return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
	Piece piece = pieceAt(x, y);

	if (piece != null) {
	    if (sideToMove != piece.side()) {
		return false;
	    }
	    if (!hasSelectedPiece || !hasMovedPiece) {
		return true;
	    }
	    return false;
	}
	if (hasSelectedPiece && !hasMovedPiece && validMove(selectedPieceX, selectedPieceY, x, y)) {
	    return true;
	}
	if (pieceAt(selectedPieceX, selectedPieceY) != null && pieceAt(selectedPieceX, selectedPieceY).hasCaptured() && validCaptureMove(selectedPieceX, selectedPieceY, x, y)) {
	    return true;
	}
	return false;
    }

    public void select(int x, int y) {
	if (pieceAt(x, y) != null) {
	    selectedPieceX = x;
	    selectedPieceY = y;
	    hasSelectedPiece = true;
	} else {
	    pieceAt(selectedPieceX, selectedPieceY).move(x, y);
	    selectedPieceX = x;
	    selectedPieceY = y;
	    hasMovedPiece = true;
	}
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
	Piece piece = pieceAt(xi, yi);
	if (piece == null || xf < 0 || xf > 7 || yf < 0 || yf > 7) {
	    return false;
	}
	int dy;
	if (piece.isFire()) {
	    dy = 1;
	} else {
	    dy = -1;
	}
	if (pieceAt(xf, yf) == null && ((yf - yi) == dy || (piece.isKing() && Math.abs(yf - yi) == 1)) && Math.abs(xf - xi) == 1) {
	    return true;
	}
	Piece possibleCapturedPiece = pieceAt((xi + xf) / 2, (yi + yf) / 2);
	if (((yf - yi) == 2 * dy || (piece.isKing() && Math.abs(yf - yi) == 2)) && Math.abs(xf - xi) == 2 && possibleCapturedPiece != null && possibleCapturedPiece.side() != piece.side()) {
	    return true;
	}
	return false;
    }

    private boolean validCaptureMove(int xi, int yi, int xf, int yf) {
	return validMove(xi, yi, xf, yf) && Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2;
    }

    public boolean canEndTurn() {
	return hasMovedPiece;
    }

    public void endTurn() {
	hasMovedPiece = false;
	if (hasSelectedPiece) {
	    Piece piece = pieceAt(selectedPieceX, selectedPieceY);
	    if (piece != null) { // it might have been a bomb that exploded
		piece.doneCapturing();
	    }
	}
	hasSelectedPiece = false;
	sideToMove = otherSide(sideToMove);
    }

    private int otherSide(int side) {
	return 1 - side;
    }

    public String winner() {
	int countFire = 0;
	int countWater = 0;

	Piece piece;
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 8; y++) {
		piece = pieceAt(x, y);
		if (piece != null) {
		    if (piece.isFire()) {
			countFire += 1;
		    } else {
			countWater += 1;
		    }
		}
	    }
	}
	if (countFire == 0 && countWater == 0) {
	    return "No one";
	}
	if (countWater == 0) {
	    return "Fire";
	}
	if (countFire == 0) {
	    return "Water";
	}
	return null;
    }

    private void drawBoard() {
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 8; y++) {
		if (hasSelectedPiece && x == selectedPieceX && y == selectedPieceY && pieceAt(selectedPieceX, selectedPieceY) != null) {
		    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		} else if ((x + y) % 2 == 0) {
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
	    }
	}
	
	Piece piece;
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 8;y ++) {
		piece = pieceAt(x, y);
		if (piece != null) {
		    StdDrawPlus.picture(x + 0.5, y + 0.5, "img/" + pieceToImageString(piece), 1, 1);
		}
	    }
	}
    }

    private String pieceToImageString(Piece piece) {
	StringBuilder imageName = new StringBuilder();
	if (piece != null) {
	    if (piece.isBomb()) {
		imageName.append("bomb");
	    } else if (piece.isShield()) {
		imageName.append("shield");
	    } else {
		imageName.append("pawn");
	    }
	    imageName.append("-");
	    if (piece.isFire()) {
		imageName.append("fire");
	    } else {
		imageName.append("water");
	    }
	    if (piece.isKing()) {
		imageName.append("-");
		imageName.append("crowned");
	    }
	    imageName.append(".png");
	}
	return imageName.toString();
    }

    public static void main(String args[]) {
	StdDrawPlus.setXscale(0, 8);
	StdDrawPlus.setYscale(0, 8);

	Board board = new Board(false);
	while (true) {
	    board.drawBoard();
	    if (StdDrawPlus.mousePressed()) {
		double x = StdDrawPlus.mouseX();
		double y = StdDrawPlus.mouseY();
		if (board.canSelect((int) x, (int) y)) {
		    board.select((int) x, (int) y);
		}
	    }
	    if (StdDrawPlus.isSpacePressed()) {
		if (board.canEndTurn()) {
		    board.endTurn();
		}
	    }
	    StdDrawPlus.show(100);
	}
    }
}