public class Board {
	public static Piece[][] pieces;
    private Piece selectedPiece;
    private double selectedX, selectedY;
    private boolean fireTurn = true,
                    selected = false,
                    moved = false;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j] != null) {
                    if (selectedPiece == pieces[i][j]) {
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                    Piece eachPiece = pieces[i][j];
                    if (eachPiece.isFire()) {
                        if (eachPiece.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else if (eachPiece.isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    } else {
                        if (eachPiece.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        } else if (eachPiece.isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false); // Was helped by friend, Andrea Liu, who informed me why I needed to instantiate a board here.

        while (true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                board.selectedX = StdDrawPlus.mouseX();
                board.selectedY = StdDrawPlus.mouseY();
                if (board.canSelect((int) board.selectedX, (int) board.selectedY)) {
                    board.select((int) board.selectedX, (int) board.selectedY);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            StdDrawPlus.show(10);
        }
    }


	public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (shouldBeEmpty) {
            for (int column = 0; column < 8; column++) {
                for (int row = 0; row < 8; row++) {
                    pieces[column][row] = null;
                }
            }
        } else if (!shouldBeEmpty) {
            for (int column = 0; column < 8; column += 2) {
                pieces[column][0] = new Piece(true, this, column, 0, "pawn");
                pieces[column][2] = new Piece(true, this, column, 2, "bomb");
                pieces[column][6] = new Piece(false, this, column, 6, "shield");
            } 
            for (int column = 1; column < 8; column += 2) {
                pieces[column][1] = new Piece(true, this, column, 1, "shield");
                pieces[column][5] = new Piece(false, this, column, 5, "bomb");
                pieces[column][7] = new Piece(false, this, column, 7, "pawn");
            } 
        }
	}

	public Piece pieceAt(int x, int y) {
        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            return null;
        } else if (pieces[x][y] == null) {
            return null;
        } else {
            return pieces[x][y];
        }
	}

	public boolean canSelect(int x, int y) {
        Piece wantedPiece = pieceAt(x, y);
        if (wantedPiece != null) {
            if ((wantedPiece.isFire()) == fireTurn) {
                if (!selected) { // The player has not selected a piece yet.
                    return true;
                } else if ((selected) && (!moved)) { // The player has selected a piece, but did not move it.
                    return true;
                }
                return false;
            }
            return false;
        } else if ((wantedPiece == null) && (selected)) {
            if (!moved) { // During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
                return validMove((int) selectedX, (int) selectedY, x, y);
            } else if (selectedPiece.hasCaptured()) { // During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
                return validMove((int) selectedX, (int) selectedY, x, y);
            }
            return false;
        }
        return false; 
	}

	public boolean validMove(int xi, int yi, int xf, int yf) {
        if (((xi < 0) || (xi > 7) || (yi < 0) || (yi > 7)) || ((xf < 0) || (xf > 7) || (yf < 0) || (yf > 7))) {
            return false;
        } else if (((xi + yi) % 2 == 1) || ((xf + yf) % 2 == 1)) {
            return false;
        } else if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1) && (pieceAt(xi, yi) != null) && (pieceAt(xf, yf) == null)) {
            return true;
        } else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2) && (pieceAt(xi, yi) != null) && (pieceAt(xf, yf) == null)) {
            int otherX = Math.max(xi, xf) - 1,
                otherY = Math.max(yi, yf) - 1;
            Piece otherPiece = pieceAt(otherX, otherY);
            if ((otherPiece != null) && (otherPiece.isFire() != pieceAt(xi, yi).isFire())) {
                return true;
            }
            return false;
        }
        return false;
    }

	public void select(int x, int y) {
        Piece piece = pieceAt(x, y);
        if (piece != null) {
            selectedPiece = piece;
            selectedX = x;
            selectedY = y;
            selected = true;
        } else if ((piece == null) & (selected)) { 
            remove((int) selectedX, (int) selectedY);
            selectedPiece.move(x, y);
            remove((int) selectedX, (int) selectedY);
            selectedX = x;
            selectedY = y;
            moved = true;
        }
	}

	public void place(Piece p, int x, int y) {
        if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8)) {
            if (p != null) {
                pieces[x][y] = p;
            }
        }
	}

	public Piece remove(int x, int y) {
        Piece piece = pieceAt(x, y); 
        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            System.out.println("(x, y) coordinates are out of bounds");
            return null;
        } else if (piece == null) {
            System.out.println("There is no piece to be removed at (" + x + ", " + y + ")");
            return null;
        } else {
            pieces[x][y] = null;
            return piece;
        }
	}

	public boolean canEndTurn() {
        if (selectedPiece == null) {
            return false;
        } else if ((moved == true) || (selectedPiece.hasCaptured())) {
            return true;
        }
        return false;
	}

	public void endTurn() {
        if (canEndTurn()) {
            fireTurn = !fireTurn;
            selectedPiece.doneCapturing();
            selectedPiece = null;
            selectedX = 0;
            selectedY = 0;
            selected = false;
            moved = false;
            winner();
        }
	}

	public String winner() {
        int firePiece = 0, waterPiece = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) == null) {
                    firePiece += 0;
                } else if (pieceAt(i, j).isFire()) {
                    firePiece += 1;
                } else if (!pieceAt(i, j).isFire()) {
                    waterPiece += 1; 
                }
            }
        }
        if ((firePiece > 0) && (waterPiece == 0)) {
            return "Fire";
        } else if ((waterPiece > 0) && (firePiece == 0)) {
            return "Water";
        } else if ((firePiece == 0) && (waterPiece == 0)) {
            return "No one";
        }
        return null;
	}

}