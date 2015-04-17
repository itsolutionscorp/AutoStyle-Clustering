import static java.lang.Math.*;

public class Board {
	
	private boolean fireTurn = true, canEndTurnBool = false;
	private Piece[][] allPieces;
	private Piece selectedPiece = null;
	private int selectedPieceX = -1, selectedPieceY = -1, capturedPieceX = -1, capturedPieceY = -1;

	private static Piece[][] guiPieces;

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        
		Board b = new Board(true);

        b.place(new Piece(true, b, 3, 5, "pawn"), 3, 5);
        b.place(new Piece(false, b, 2, 6, "pawn"), 2, 6);


		while(true) {
			guiPieces = b.allPieces;
			drawBoard(8, b);
			for (int i = 0; i < 8; i++) {
				for (int k = 0; k < 8; k++) {
					String whichPlayer;
					if (guiPieces[i][k] != null) {
						if (guiPieces[i][k].isFire())
							whichPlayer = "fire";
						else
							whichPlayer = "water";
						String pieceType = "pawn";
						if (guiPieces[i][k].isBomb())
							pieceType = "bomb";
						if (guiPieces[i][k].isShield())
							pieceType = "shield";
						String isPieceCrowned = "";
						if (guiPieces[i][k].isKing())
							isPieceCrowned = "-crowned";
						StdDrawPlus.picture(i + .5, k + .5, "img/" + pieceType + "-" + whichPlayer + isPieceCrowned + ".png", 1, 1);
					}
				}
			}

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.select((int)x, (int)y);
            }        
            if (StdDrawPlus.isSpacePressed())
            	b.endTurn();    
            StdDrawPlus.show(100);
        }
	}

	private static void drawBoard(int N, Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (b.selectedPieceX == i && b.selectedPieceY == j) StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

	public Board (boolean shouldBeEmpty) {
		allPieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int i = 0; i < 4; i++) {
				allPieces[i * 2][0] = new Piece(true, this, i * 2, 0, "pawn");
				allPieces[i * 2 + 1][1] = new Piece(true, this, i * 2 + 1, 1, "shield");
				allPieces[i * 2][2] = new Piece(true, this, i * 2, 2, "bomb");
				allPieces[i * 2 + 1][5] = new Piece(false, this, i * 2 + 1, 5, "bomb");
				allPieces[i * 2][6] = new Piece(false, this, i * 1, 6, "shield");
				allPieces[i * 2 + 1][7] = new Piece(false, this, i * 2 + 1, 7, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7)
			return null;
		return allPieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece pieceToSelect = pieceAt(x, y);
		if (pieceToSelect != null) {
			if (pieceAt(x, y) == selectedPiece && canEndTurn())
				return false;
			if (canEndTurn())
				return false;
			if (pieceToSelect.isFire() != fireTurn)
				return false;
			if (selectedPiece != null && selectedPiece.hasCaptured())
				return false;
			if (selectedPiece == null || selectedPiece.hasCaptured() == false)
				return true;
			return false;
		} else {
			if (selectedPiece != null && validMove(selectedPieceX, selectedPieceY, x, y)) 
				return true;
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		capturedPieceX = -1;
		capturedPieceY = -1;
		if (pieceAt(xf, yf) != null) { // Return false if piece already on target place
			return false;
		}
		if (xi == xf && yi == yf) { // Return false if selects piece's square
			return false;
		}
		if (Math.abs(xi - xf) != Math.abs(yi - yf)) { // Return false if not diagonal
			return false;
		}
		if (!selectedPiece.isKing()) {
			if (selectedPiece.isFire() && yf - yi < 0) { // Return false if non-king fire tries to move backward
				return false;
			}
			if (!selectedPiece.isFire() && yf - yi > 0) { // Return false if non-king water tries to move backward 
				return false;
			}
		}
		if (Math.abs(xi - xf) == 2) { // Tests for capture attempt
			Piece potentialCapture = pieceAt(((xf - xi) / 2) + xi, ((yf - yi) / 2) + yi);
			capturedPieceX = ((xf - xi) / 2) + xi;
			capturedPieceY = ((yf - yi) / 2) + yi;
			if (potentialCapture == null || potentialCapture.isFire() == selectedPiece.isFire()) {
				return false;
			}
			return true;
		}
		if (selectedPiece.hasCaptured() || canEndTurnBool) {
			pr(canEndTurnBool);
			return false;
		}
		return true;
	}

	public void select(int x, int y) {
		if (canSelect(x, y)) {
			if (pieceAt(x, y) != null) {
				selectedPiece = pieceAt(x, y);
				selectedPieceX = x;
				selectedPieceY = y;
			} else {
				place(selectedPiece, x, y);
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (!(x < 0 || y < 0 || x > 7 || y > 7) && p != null && selectedPiece != null) { // If move not out of bounds or place is null
			
			if (p == selectedPiece) {
				canEndTurnBool = true;
				selectedPiece.move(x, y);
				remove(selectedPieceX, selectedPieceY);
				selectedPieceX = x;
				selectedPieceY = y;
				allPieces[x][y] = selectedPiece;

				if (p.hasCaptured() && p.isBomb()) {
					pr("Boom!");
					bombAction(x, y);
				}

				if (capturedPieceX != -1) {
					remove(capturedPieceX, capturedPieceY);
				}
			}
		} else if (selectedPiece == null) {
			allPieces[x][y] = p;
		}
	}

	private void bombAction(int x, int y) {
		for (int ix = x - 1; ix <= x + 1; ix++) {
			for (int iy = y - 1; iy <= y + 1; iy++) {
				if (canBombCapturePiece(ix, iy))
					remove(ix, iy);
			}
		}
		selectedPieceX = -1;
		selectedPieceY = -1;
		allPieces[x][y] = null;
		selectedPiece = null;
	}

	private boolean canBombCapturePiece(int x, int y) {
		if (pieceAt(x, y) == null || pieceAt(x, y).isShield())
			return false;
		return true;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.out.println("Remove peice was out of bounds!");
		}
		Piece removePiece = pieceAt(x, y);
		allPieces[x][y] = null;
		return removePiece;
	}

	public boolean canEndTurn() {
		return canEndTurnBool;
	}

	public void endTurn() {
		if (canEndTurn()) { 
			if (selectedPiece != null)
				selectedPiece.doneCapturing();
			fireTurn = !fireTurn;
			selectedPieceY = -1;
			selectedPieceX = -1;
			selectedPiece = null;
			canEndTurnBool = false;
			/*if (fireTurn)
				pr("Fire's Turn!");
			else
				pr("Water's Turn!");*/

			if (winner() != null) {
				pr(winner() + " Wins!");
			}
		}
	}

	public String winner() {
		boolean fireAlive = false;
		boolean waterAlive = false;

		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				if (pieceAt(i, k) != null) {
					if (pieceAt(i, k).isFire()) {
						fireAlive = true;
					}
					else {
						waterAlive = true;
					}
				}
			}
		}
		if (fireAlive && !waterAlive)
			return "Fire";
		if (!fireAlive && waterAlive)
			return "Water";
		if (!fireAlive && !waterAlive)
			return "No one";
		return null;
	}

	private void pr(String str) {
		System.out.println(str);
	}
	private void pr(int i) {
		System.out.println(i);
	}
	private void pr(boolean b) {
		System.out.println(b);
	}
}