/*
 * @author Grace Lightner
 * debugging help from Bob Zhou
 */

public class Board {

	private Piece[][] pieces;
	private boolean turn; //true if fire's turn, false if water's turn
	private boolean hasMoved; //true if a player has moved a piece
	private Piece selectedPiece; //stores which piece has been selected
	private int selectedX;
	private int selectedY;
	private boolean[][] clicked;

	/* Board constructor;
	 * initializes all variables */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		turn = true; //sets first turn to fire
		hasMoved = false;
		selectedPiece = null;
		selectedX = 0;
		selectedY = 0;
		clicked = new boolean[8][8];
		if (shouldBeEmpty == false) {
			initializePieces(this);
		}

	}

	/* Implements graphics
	 * calls no methods that involve piece movement */
	private void drawBoard() {
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if ((i + j) % 2 == 0) {
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (clicked[i][j]) {
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				if (pieces [i][j] != null) {
					Piece toDraw = pieces[i][j];
					String team;
					String warrior;
					if (toDraw.isBomb() == true) {
						warrior = "bomb";
					}
					else if (toDraw.isShield() == true) {
						warrior = "shield";
					}
					else {
						warrior = "pawn";
					}
					if (toDraw.isFire() == true) {
						team = "fire";
					}
					else {
						team = "water";
					}
					if (toDraw.isKing() == false) {
						StdDrawPlus.picture(i + .5, j + .5, "img/" + warrior + "-" + team + ".png", 1, 1);
					}
					else {
						StdDrawPlus.picture(i + .5, j + .5, "img/" + warrior + "-" + team + "-crowned.png", 1, 1);
					}
				}			
			}
		}
	}

	private void initializePieces(Board b) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					if ((j <= 2) || (j >= 5)) {
						if (j == 0) {
							pieces[i][j] = new Piece(true, b, i, j, "pawn");
						}
						if (j == 1) {
							pieces[i][j] = new Piece(true, b, i, j, "shield");
						}
						if (j == 2) {
							pieces[i][j] = new Piece(true, b, i, j, "bomb");
						}
						if (j == 5) {
							pieces[i][j] = new Piece(false, b, i, j, "bomb");
						}
						if (j == 6) {
							pieces[i][j] = new Piece(false, b, i, j, "shield");
						}
						if (j == 7) {
							pieces[i][j] = new Piece(false, b, i, j, "pawn");
						}
					}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return false;
		}
		if (pieceAt(x, y) == null) {
			if (selectedPiece == null) {
				return false;
			}
			else {
				return validMove(selectedX, selectedY, x, y);
			}
		}
		else if (hasMoved == true) {
			return false;
		}
		if (turn == true) {
			if (pieceAt(x, y).isFire() == true) {
				return true;
			}
			return false;
		}
		else if (pieceAt(x, y).isFire() == false) {
			return true;
		}
		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		int diffX = Math.abs(xf - xi);
		int diffY = Math.abs(yf - yi);
		if ((diffX > 2) || (diffY > 2)) {
			return false;
		}
		if (diffX != diffY) {
			return false;
		}
		if (pieceAt(xf, yf) != null) {
			return false;
		}
		if (selectedPiece.isKing() == false) { //handles non-kings moving backwards
			if (turn == true) {
				if (yf < yi) {
					return false;
				}
			}
			else {
				if (yf > yi) {
					return false;
				}
			}
		}
		if (diffX == 1) { //prevents moving twice without capturing
			if (hasMoved == true) {
				return false;
			}
			return true;
		}
		if (diffX == 2) {
			int jumpedSquareX = ((xf + xi) / 2);
			int jumpedSquareY = ((yf + yi) / 2);
			Piece jumpedPiece = pieceAt(jumpedSquareX, jumpedSquareY);
			if (jumpedPiece == null) {
				return false;
			}
			if (jumpedPiece.isFire() == selectedPiece.isFire()) {
				return false;
			}
			return true;
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		}
		else {
			boolean makeNull = false;
			if ((selectedPiece.isBomb() == true) && (java.lang.Math.abs(selectedX - x) == 2)) {
				makeNull = true;
			}
			selectedPiece = remove(selectedX, selectedY);
			place(selectedPiece, x, y);
			selectedPiece.move(x, y);
			if (makeNull == true) {
				selectedPiece = null;
			}
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y) { 
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return;
		}
		selectedPiece = p;
		selectedX = x;
		selectedY = y;
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return null;
		}
		if (pieceAt(x, y) == null) {
			return null;
		}
		Piece removedPiece = pieceAt(x, y);
		pieces[x][y] = null;
		return removedPiece;
	}

	public boolean canEndTurn() {
		if (hasMoved == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		turn = !turn;
		hasMoved = false;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
	}

	public String winner() {
		boolean waterExists = false;
		boolean fireExists = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) { //determines if pieces are left on the board
					if (pieceAt(i, j).isFire() == false) {
						waterExists = true;
					}
					if (pieceAt(i, j).isFire() == true) {
						fireExists = true;
					}
				}
			}
		}
		if ((fireExists == true) && (waterExists == true)) {
			return null;
		}
		if ((fireExists == true) && (waterExists == false)) {
			return "Fire";
		}
		if ((fireExists == false) && (waterExists == true)) {
			return "Water";
		}
		return "No one";
	}

	public static void main(String[] args) {
		int squares = 8;
		StdDrawPlus.setXscale(0, squares);
		StdDrawPlus.setYscale(0, squares);
		Board b = new Board(false);
		while (b.winner() == null) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)) {
					b.clicked = new boolean[8][8];
					b.clicked[(int) x][(int) y] = true;
					b.select((int) x, (int) y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.clicked = new boolean[8][8];
					b.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
		System.out.print(b.winner());
	}	
}