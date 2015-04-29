/** Board class for exploding checkers game
 *  author: Arshi Aggarwal
 */
import java.util.*;

public class Board {
	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private int N = 8;
	private boolean hasMoved = false;
	private boolean fireMove = true;
	private int selectedX = N;
	private int selectedY = N;
	private Piece selectedPiece;

	/*starts a GUI supported version of the game.
	 */
	public static void main (String args[]) {
		Board b = new Board(false);
		if (b.shouldBeEmpty) {
			b.drawBoard(b.N);
		} else {
			b.redraw();
		}

		while(b.winner() == null) {
            b.redraw();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(10);
        }
	}

	/*The constructor for Board. If shouldBeEmpty is true, initializes an
	 * empty Board. Otherwise, initializes a Board with pieces in starting position.
	 */
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;

		/* Initially enters the pieces onto the board.
         */
		pieces = new Piece[N][N];
        if (!shouldBeEmpty) { 
	        for (int i = 0; i < N; i++) {
	        	for (int j = 0; j < N; j++) {
	        		if (i % 2 == 0) {
	                	new Piece(true, this, i, 0, "pawn");
	                	new Piece(true, this, i, 2, "bomb");
	                	new Piece(false, this, i, N-2, "shield");
	                }
	                if (i >= 0) {
		                if (i % 2 == 1) {
		                	new Piece(true, this, i, 1, "shield");
		                	new Piece(false, this, i, N-3, "bomb");
		                	new Piece(false, this, i, N-1, "pawn");
		                }
		            }
		        }
        	}
	    }		
	}



//GUI
	/* Draws an empty checker board.
	 */
	private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (selectedX == i && selectedY == j) {
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

	/* Creats the image path to find which image needs to be drawn.
	 */
	private String pathBuilder(Piece p) {
		String type = "pawn-";
		if (p.isBomb()) {
			type = "bomb-";
		}
		if (p.isShield()) {
			type = "shield-";
		}
		String brand = "water";
		if (p.isFire()) {
			brand = "fire";
		}
		String king = "";
		if (p.isKing()) {
			king = "-crowned";
		}
		return "img/" + type + brand + king + ".png";
	}

	/* Redraws the entire board.
	 */
	private void redraw() {
		drawBoard(this.N);
		for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
    			Piece p = pieceAt(i, j);
    			if (p != null) {
    				StdDrawPlus.picture(i + .5, j + .5, pathBuilder(p), 1, 1);
        		}
        	}
        }
	}



//GAME LOGIC
	/* Gets the piece at position (x, y) on the board.
	 * Checks for out of bounds also.
	 */
	public Piece pieceAt(int x, int y) {
		if (x > N - 1 || y > N -1 || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	/* Checks to see if the square or piece at x, y 
	 * can be selected.
	 */
	public boolean canSelect(int x, int y) {
		if (x > N - 1 || y > N -1 || x < 0 || y < 0) {
			return false;
		}
		Piece p = pieceAt(x, y);
		// if there is a piece at x, y
		if (p != null) {
			if (p.isFire() == fireMove) {
				if (!hasMoved) {
					return true;
				} else {
					return false;
				}
			}
		// if trying to select an empty square to move to
		} else {
			if (!hasMoved) {
				if (validMove(selectedX, selectedY, x, y) || validCapture(selectedX, selectedY, x, y)) {
					return true;
				} else {
					return false;
				}
			} else {
				if (selectedPiece.hasCaptured() && validCapture(selectedX, selectedY, x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf)
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece f = pieceAt(xf, yf);
		if (p != null && f == null) {
			if (fireMove && Math.abs(xf - xi) == 1 && yf - yi == 1) {
				return true;
			}
			if (!fireMove && Math.abs(xf - xi) == 1 && yf - yi == -1) {
				return true;
			}
			if (p.isKing() && Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {
				return true;
			}
		}
		return false;
	}

	/* Returns true if the piece at (xi, yi) can capture to (xf, yf).
	 */
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece f = pieceAt(xf, yf);
		Piece c = pieceAt((xf + xi)/2, (yf + yi)/2);
		if (p != null && f == null && c != null && p.isFire() != c.isFire()) {
			if (fireMove && Math.abs(xf - xi) == 2 && yf - yi == 2) {
				return true;
			}
			if (!fireMove && Math.abs(xf - xi) == 2 && yf - yi == -2) {
				return true;
			}
			if (p.isKing() && Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
				return true;
			}
		}
		return false;
	}

	/* Selects the piece/empty square at (x, y) if possible. 
	 */
	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) { // place is an empty square
			selectedPiece.move(x, y);
			hasMoved = true;
			selectedX = x;
			selectedY = y;
		} else { // save selected piece
			selectedPiece = p;
			selectedX = x;
			selectedY = y;
		}
	}

	/* Places p at (x, y). Checks for out of bounds also. If another
	 * piece already exists at (x, y), p will replace that piece.
	 */
	public void place(Piece p, int x, int y) {
		if (x > N - 1 || y > N -1 || x < 0 || y < 0) {
			return;
		}
		pieces[x][y] = p;
	}

	/* Executes a remove. Returns the piece that was removed. 
	 * Checks for out of bounds also.
	 */
	public Piece remove(int x, int y) {
		if (x > N - 1) {
			System.err.println("x out of bounds");
			return null;
		} else if (y > N -1) {
			System.err.println("y out of bounds");
			return null;
		} else {
			Piece p = pieceAt(x, y);
			if (p == null) {
				System.out.println("no piece present at " + x + ", " + y);
				return null;
			} else {
				pieces[x][y] = null;
				return p;
			}
		}
	}

	/* Returns whether or not the the current player can end their turn;
	 * a piece must have moved. 
	 */
	public boolean canEndTurn() {
		return hasMoved;
	}

	/* Handles switching of players and anything else that should happen
	 * at the end of a turn. 
	 */
	public void endTurn() {
		hasMoved = false;
		selectedX = N;
		selectedY = N;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		if (fireMove) {
			fireMove = false;
		} else {
			fireMove = true;
		}
	}

	/* Returns the winner of the game. "Fire", "Water", "No one" (tie/no pieces on the board), 
	 * or null if the game is not yet over.
	 */
	public String winner() {
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	Piece p = pieceAt(i, j);
            	if (pieceAt(i, j) != null) {
            		if (p.isFire()) {
            			countFire++;
            		} else {
            			countWater++;
            		}
            	}
            }
        }
        if (countFire == 0 && countWater == 0) {
        	return "No one";
        } else if (countWater == 0) {
        	return "Fire";
        } else if (countFire == 0) {
        	return "Water";
        } else {
        	return null;
        }
	}
}