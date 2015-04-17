public class Board {

    private int boardSize = 8;
    private Piece[][] pieces;
    private boolean firePlayerTurn = true;
    private int[] selected;
    private Piece moved;
    private int firePieces = 0;
    private int waterPieces = 0;
    
    public Board(boolean shouldBeEmpty) {
	pieces = new Piece[boardSize][boardSize];
	if (!shouldBeEmpty) {
	    for (int j = 0; j < boardSize; j++) {
		for (int i = 0; i < boardSize; i++) {
		    switch (j) {
		    case 0:
			if (i % 2 == 0) place(new Piece(true, this, i, j, "pawn"), i, j);
			break;
		    case 1:
			if (i % 2 == 1) place(new Piece(true, this, i, j, "shield"), i, j);
			break;
		    case 2:
			if (i % 2 == 0) place(new Piece(true, this, i, j, "bomb"), i, j);
			break;
		    case 5:
			if (i % 2 == 1) place(new Piece(false, this, i, j, "bomb"), i, j);
			break;
		    case 6:
			if (i % 2 == 0) place(new Piece(false, this, i, j, "shield"), i, j);
			break;
		    case 7:
			if (i % 2 == 1) place(new Piece(false, this, i, j, "pawn"), i, j);
			break;
		    default:
			break; 
		    }
		}
	    }
	}
    }

    private void drawBoard() {
	for (int i = 0; i < boardSize; i++) {
	    for (int j = 0; j < boardSize; j++) {
		if (selected != null && selected[0] == i && selected[1] == j) {
		    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		} else if ((i + j) % 2 == 0) {
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		    
		} else {
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
		if (pieceAt(i, j) != null) StdDrawPlus.picture(i + 0.5, j + 0.5, getPicture(pieceAt(i, j)), 1, 1);
	    }
	}
    }

    public Piece pieceAt(int x, int y) {
	if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
	    return null;
	} else {
	    return pieces[x][y];
	}
    }

    public void place(Piece p, int x, int y) {
	if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
	    return;
	} else {
	    if (pieceAt(x, y) != null) {
		if (pieceAt(x, y).isFire()) {
		    firePieces--;
		} else {
		    waterPieces--;
		}
	    }
	    pieces[x][y] = p;
	    if (p.isFire()) {
		firePieces++;
	    } else {
		waterPieces++;
	    }
	}
    }

    public Piece remove(int x, int y) {
	if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
	    System.out.println("[Board.remove(" + x + ", " + y + ")]: ERROR - Invalid bounds");
	    return null;
	} else if (pieceAt(x, y) == null) {
	    System.out.println("[Board.remove(" + x + ", " + y + ")]: ERROR - No piece at this location");
	    return null;
	} else {
	    Piece returnPiece = pieceAt(x, y);
	    pieces[x][y] = null;
	    if (returnPiece.isFire()) {
		firePieces--;
	    } else {
		waterPieces--;
	    }
	    return returnPiece;
	}
    }

    private String getPicture(Piece p) {
	String directory = "img/";
	if (p.isBomb()) {
	    directory += "bomb";
	    
	} else if (p.isShield()) {
	    directory += "shield";
	    
	} else {
	    directory += "pawn";
	}
	if (p.isFire()) {
	    directory += "-fire";
	} else {
	    directory += "-water";
	}
	if (p.isKing()) directory += "-crowned";
	directory += ".png";
	return directory;
    }

    public boolean canSelect(int x, int y) {
	Piece currentPiece = pieceAt(x, y);
	if (currentPiece == null) {
	    if (selected != null && pieceAt(selected[0], selected[1]) != moved && validMove(selected[0], selected[1], x, y)) {
		return true;
	    } else if (selected != null && pieceAt(selected[0], selected[1]) != null && pieceAt(selected[0], selected[1]).hasCaptured()) {
		if (validCapture(selected[0], selected[1], x, y)) return true;
	    }
	} else if (currentPiece.isFire() == firePlayerTurn) {
	    if (selected == null && moved == null) {
		return true;
	    } else if (selected != null && pieceAt(selected[0], selected[1]) != moved) {
		return true;
	    }
	}
	return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
	if (pieceAt(xf, yf) != null || pieceAt(xi, yi) == null) {
	    return false;
	} else if (pieceAt(xi, yi).isKing() && Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
	    return true;
	} else if (pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 1 && (yf - yi) == 1) {
	    return true;
	} else if (!pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 1 && (yf - yi) == -1) {
	    return true;
	} else if (validCapture(xi, yi, xf, yf)) {
	    return true;
	} else {
	    return false;
	}
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
	if (pieceAt(xf, yf) != null) {
	    return false;
	} else if (pieceAt(xi, yi).isKing() && Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
	    int middleX = (xi + xf)/2;
	    int middleY = (yi + yf)/2;
	    if (pieceAt(middleX, middleY) != null && pieceAt(xi, yi).isFire() != pieceAt(middleX, middleY).isFire()) return true;
	} else if (pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 2 && (yf - yi) == 2) {
	    if (pieceAt((xi + xf)/2, yi + 1) != null && !pieceAt((xi + xf)/2, yi + 1).isFire()) return true;
	} else if (!pieceAt(xi, yi).isFire() && Math.abs(xf - xi) == 2 && (yf - yi) == -2) {
	    if (pieceAt((xi + xf)/2, yi - 1) != null && pieceAt((xi + xf)/2, yi - 1).isFire()) return true;
	}
	return false;
    }

    public void select(int x, int y) {
	if (selected == null) {
	    selected = new int[]{x, y};
	} else {
	    if (pieceAt(x, y) != null) {
		selected = new int[]{x, y};
	    } else {
		moved = pieceAt(selected[0], selected[1]);
		pieceAt(selected[0], selected[1]).move(x, y);
		selected[0] = x;
		selected[1] = y;
		if (pieceAt(selected[0], selected[1]) == null) selected = null;
	    }
	}
    }

    public boolean canEndTurn() {
	if (moved != null) {
	    return true;
	} else {
	    return false;
	}
    }

    public void endTurn() {
	if (moved.hasCaptured()) moved.doneCapturing();
	selected = null;
	moved = null;
	firePlayerTurn = !firePlayerTurn;
    }

    public String winner() {
	if (firePieces == 0 && waterPieces == 0) {
	    return "No one";
	} else if (firePieces == 0) {
	    return "Water";
	} else if (waterPieces == 0) {
	    return "Fire";
	} else {
	    return null;
	}
    }

    public static void main(String[] args) {
	Board gameBoard = new Board(false);
	StdDrawPlus.setXscale(0, gameBoard.boardSize);
	StdDrawPlus.setYscale(0, gameBoard.boardSize);
	while (gameBoard.winner() == null) {
	    gameBoard.drawBoard();
	    if (StdDrawPlus.mousePressed()) {
		int xClick = (int) StdDrawPlus.mouseX();
	        int yClick = (int) StdDrawPlus.mouseY();
		if (gameBoard.canSelect(xClick, yClick)) {
		    gameBoard.select(xClick, yClick);
		    gameBoard.drawBoard();
		    StdDrawPlus.show(10);
		    continue;
		}
	    }
	    if (StdDrawPlus.isSpacePressed()) {
		if (gameBoard.canEndTurn()) gameBoard.endTurn();
		gameBoard.drawBoard();
		StdDrawPlus.show(10);
		continue;
	    }
	    StdDrawPlus.show(10);
	}
    }
}
