import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.*;

public class Board {
	private Piece[][] pieces;
	private int playerTurn = 2;
	private int currentY = 10;
	private int currentX = 10;
	private boolean canEndTurn = false;
	private boolean regularMove = false;
	private boolean hasCaptured = false;

	// starts a GUI supported version of the game.
	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		Board b2 = new Board(true);
		b2.place(new Piece(false, b2, 1, 1, "shield"), 1, 1);
		b2.endTurn();
		b2.select(1, 1);
		b2.select(0, 0);
		b2.place(new Piece(true, b2, 1, 1, "bomb"), 1, 1);
		b2.place(new Piece(true, b2, 3, 3, "bomb"), 3, 3);
		b2.place(new Piece(true, b2, 3, 5, "bomb"), 3, 5);
		b2.endTurn();
		b2.endTurn();

		Piece p;
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				double dx = StdDrawPlus.mouseX();
				double dy = StdDrawPlus.mouseY();
				int x = (int) dx;
				int y = (int) dy;
				b2.select(x, y);
				// System.out.println("assertEquals(b2.canSelect(" + x + "," + y
				// + "), true);");
				System.out.println("b2.select(" + x + "," + y + ");");
				// System.out.println("assertNotNull(b2.pieceAt(" + x + "," + y
				// + "));");
			}
			if (StdDrawPlus.isSpacePressed()) {
				b2.endTurn();
			}
			StdDrawPlus.show(100);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					} else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					p = b2.pieceAt(i, j);

					if (p != null) {

						String type, img;
						if (p.isBomb()) {
							type = "bomb";
						} else if (p.isShield()) {
							type = "shield";
						} else {
							type = "pawn";
						}

						String side;
						if (p.isFire()) {
							side = "-fire";
						} else {
							side = "-water";
						}

						if (p.isKing()) {
							img = "img/" + type + side + "-crowned.png";
						} else {
							img = "img/" + type + side + ".png";
						}

						StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
					}
				}
			}
		}
	}

	// board constructor
	public Board(boolean shouldBeEmpty) {
		int N = 8;
		this.pieces = new Piece[N][N];
		// we take this out and put it in the main method
		// See notes for drawing board

		// this.drawBoard(N);
		if (shouldBeEmpty == false) {
			this.buildDefaultBoard(N);
		}
	}

	// gets the piece at a location
	public Piece pieceAt(int x, int y) {

		if (x > 7 | y > 7) {
			return null;
		} else if (x < 0 | y < 0) {
			return null;
		}
		Piece p = pieces[x][y];
		if (p == null) {
			return null;
		} else {
			return p;
		}

	}

	// - Places p at (x, y). If (x, y) is out of bounds or if p is null, does
	// nothing. If another piece already exists at (x, y), p will replace that
	// piece. (This method is potentially useful for creating specific test
	// circumstances.)
	public void place(Piece P, int x, int y) {
		// System.out.println(x + " + " + y);

		this.pieces[x][y] = P;
		if (this.currentX == 10 | this.currentY == 10) {
			return;
		} else {
			P.move(x, y);
		}

	}

	// Executes a remove. Returns the piece that was removed. If the input (x,
	// y) is out of bounds, returns null and prints an appropriate message. If
	// there is no piece at (x, y), returns null and prints an appropriate
	// message.
	public Piece remove(int x, int y) {
		if (this.validXY(x, y) == false) {
			System.out.println("Removal out of bounds.");
			return null;
		}
		Piece p = this.pieces[x][y];

		if (p == null) {
			System.out.println("Removal of null value.");
		}
		this.pieces[x][y] = null;
		// this.renderPiece(x, y, false);
		return p;

	}

	// Builds our default board
	private void buildDefaultBoard(int N) {
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				// pawn
				this.place(new Piece(true, this, i, 0, "pawn"), i, 0);
				// shield
				this.place(new Piece(false, this, i, 6, "shield"), i, 6);
				// bomb
				this.place(new Piece(true, this, i, 2, "bomb"), i, 2);
			} else {
				// pawn
				this.place(new Piece(false, this, i, 7, "pawn"), i, 7);
				// shield
				this.place(new Piece(true, this, i, 1, "shield"), i, 1);
				// bomb
				this.place(new Piece(false, this, i, 5, "bomb"), i, 5);
			}
		}
	}

	private int inBetweenSquare(int start, int end) {
		int change = end - start;
		if (change == 2) {
			return 1;
		} else if (change == -2) {
			return -1;
		} else {
			return -10;
		}
	}

	private boolean isVertIncrease(int start, int end) {
		int change = end - start;
		if (change >= 1) {
			return true;
		}
		return false;
	}

	private boolean checkSameTeam(Piece p1, Piece p2) {
		if (p1 == null | p2 == null) {
			return false;
		} else if (p1.isFire() == p2.isFire()) {
			return true;
		}
		return false;
	}

	private boolean validCapture(int startx, int starty, int endx, int endy) {
		boolean change = Math.abs(starty - endy) + Math.abs(startx - endx) == 4;
		int changeX = this.inBetweenSquare(startx, endx);
		int changeY = this.inBetweenSquare(starty, endy);
		Piece start = this.pieceAt(startx, starty);
		Piece end = this.pieceAt(endx, endy);
		Piece mid = this.pieceAt(startx + changeX, starty + changeY);
		boolean isSame = this.checkSameTeam(start, mid);

		if (!change | mid == null | end != null | isSame == true
				| start == null) {
			return false;
		}
		// box out fire
		if (changeY <= -1 & start.isKing() == false & start.isFire()) {
			return false;
		}
		// box out ice
		if (changeY >= 1 & start.isKing() == false & start.isFire() == false) {
			return false;
		}
		return true;
	}

	private boolean isCorrectTurn(int x, int y) {
		boolean pTurn = this.playerTurn % 2 == 0;
		Piece p = this.pieceAt(x, y);

		if (p == null) {
			p = this.pieceAt(this.currentX, this.currentY);
		}

		if (p == null) {
			return false;
		}

		if (p.isFire() == pTurn) {
			return true;
		}
		return false;
	}

	private boolean validXY(int x, int y) {
		if (x > 7 | y > 7) {
			return false;
		} else if (x < 0 | y < 0) {
			return false;
		}

		if ((x + y) % 2 == 1) {
			return false;
		}
		return true;
	}

	// Selects the piece at (x, y) if possible. Optionally, it is recommended to
	// color the background of the selected square white on the GUI via the pen
	// color function. For any piece to perform a capture, that piece must have
	// been selected first.

	public void select(int x, int y) {
		boolean isValid = this.validXY(x, y);
		// boolean canSelect = this.canSelect(x, y);
		boolean isCapture = this.validCapture(this.currentX, this.currentY, x,
				y);
		boolean isRegular = this.validMove(this.currentX, this.currentY, x, y);
		Piece p;

		if (!isValid) {
			return;
		}

		// if (!canSelect) {
		// return;
		// }

		if (isRegular) {
			p = this.remove(this.currentX, this.currentY);
			this.place(p, x, y);
			this.regularMove = true;
			this.canEndTurn = true;
			this.currentX = 10;
			this.currentY = 10;
			return;
		}

		if (isCapture) {
			p = this.remove(this.currentX, this.currentY);
			this.place(p, x, y);
			this.currentX = x;
			this.currentY = y;
			this.hasCaptured = true;
			this.canEndTurn = true;
			// this.renderPiece(x, y, true);
			return;
		}
		this.currentX = x;
		this.currentY = y;
		// this.renderPiece(x, y, true);

		// for (int i = 7; i >= 0; i--) {
		// for (int j = 0; j < 8; j++) {
		// if (this.pieces[j][i] != null) {
		// System.out.print(1 + " ");
		// } else {
		// System.out.print(0 + " ");
		// }
		// }
		// System.out.println("");
		// }
	}

	public boolean canSelect(int x, int y) {
		boolean isValid = this.validXY(x, y);
		boolean isCapture = this.validCapture(this.currentX, this.currentY, x,
				y);
		boolean current = this.validXY(this.currentX, this.currentY);
		boolean isRegular = this.validMove(this.currentX, this.currentY, x, y);
		boolean correctTurn = this.isCorrectTurn(x, y);
		boolean isSame = (this.currentX == x & this.currentY == y);
		boolean isSwitch = this.checkSameTeam(
				this.pieceAt(this.currentX, this.currentY), this.pieceAt(x, y));

		if (!isValid) {
			return false;
		}

		if (!correctTurn) {
			return false;
		}

		// if (isSame) {
		// return false;
		// }

		if (this.hasCaptured) {
			if (isCapture) {
				return true;
			} else {
				return false;
			}

		}

		if (isRegular) {
			return true;
		}

		if (isValid & isSwitch) {
			return true;
		}

		if (!current & this.regularMove == false) {
			return true;
		}
		if (isCapture) {
			return true;
		}

		return false;
	}

	// Returns true if there is a piece the piece at (x, y) and it can be
	// selected.
	// A piece may be selected if it is the corresponding player’s turn and one
	// of the following is true:
	// The player has not selected a piece yet.
	// The player has selected a piece, but did not move it.

	// Returns true if the piece at (xi, yi) can either move to (xf, yf) or
	// capture to (xf, yf) in a valid fashion compatible with the rules.
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece start = this.pieceAt(xi, yi);
		Piece end = this.pieceAt(xf, yf);
		int changeX = Math.abs(xi - xf);
		int changeY = Math.abs(yi - yf);
		boolean vert = this.isVertIncrease(yi, yf);

		// no starting piece for some reason
		if (start == null) {
			return false;
		}

		// is there a piece at the end
		if (end != null) {
			return false;
		}

		// too big
		if (changeX > 2 | changeY > 2) {
			return false;
		}

		// is it a regular move
		if (changeX + changeY == 2) {
			if (vert == start.isFire() & this.hasCaptured == false) {
				return true;
			}
			if (start.isKing()) {
				return true;
			}

		}
		return false;
	}

	private boolean canCaptureUp(int x, int y) {
		boolean validLeft = this.validMove(x, y, x - 2, y + 2);
		boolean validRight = this.validMove(x, y, x + 2, y + 2);
		if (validLeft | validRight) {
			return true;
		}
		return false;
	}

	private boolean canCaptureDown(int x, int y) {
		boolean validLeft = this.validMove(x, y, x - 2, y - 2);
		boolean validRight = this.validMove(x, y, x + 2, y - 2);
		if (validLeft | validRight) {
			return true;
		}
		return false;
	}

	private boolean canCapture(int x, int y) {
		Piece current = this.pieceAt(x, y);
		boolean canCaptureUp = this.canCaptureUp(x, y);
		boolean canCaptureDown = this.canCaptureDown(x, y);
		boolean isFire = current.isFire();
		if (current.isKing()) {
			if (canCaptureUp | canCaptureDown) {
				return true;
			}
			return false;
		}

		if (isFire & canCaptureUp) {
			return true;
		}

		if (isFire == false & canCaptureDown) {
			return true;
		}

		return false;
	}

	// Returns whether or not the the current player can end their turn. To be
	// able to end a turn, a piece must have moved or performed a capture.
	public boolean canEndTurn() {
		if (this.canEndTurn) {
			return true;
		}
		return false;
	}

	// Called at the end of each turn. Handles switching of players and anything
	// else that should happen at the end of a turn.
	public void endTurn() {
		// if (this.canEndTurn()) {
		// this.renderPiece(this.currentX, this.currentY, false);
		if (this.validXY(this.currentX,  this.currentY)){
			this.pieceAt(this.currentX, this.currentY).doneCapturing();	
		}
		this.currentX = 10;
		this.currentY = 10;
		this.playerTurn++;
		this.canEndTurn = false;
		this.regularMove = false;
		this.hasCaptured = false;
		// }
	}

	// Returns the winner of the game. "Fire", "Water", "No one" (tie / no
	// pieces on the board), or null if the game is not yet over. Assume there
	// is no stalemate situation in which the current player has pieces but
	// cannot legally move any of them (In this event, just leave it at null).
	// Determine the winner solely by the number of pieces belonging to each
	// team.
	public String winner() {
		Piece[] water = new Piece[12];
		Piece[] fire = new Piece[12];
		Piece temp;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				temp = this.pieces[i][j];
				if (temp != null) {
					if (temp.isFire()) {
						for (int i_ = 0; i_ < fire.length; i_++) {
							if (fire[i_] == null) {
								fire[i_] = temp;
								break;
							}
						}
					} else if (temp.isFire() == false) {

						for (int i_1 = 0; i_1 < water.length; i_1++) {
							if (water[i_1] == null) {
								water[i_1] = temp;
								break;
							}
						}

					}
				}
			}
		}
		int actual_water = 0;
		int actual_fire = 0;

		for (int i_1 = 0; i_1 < fire.length; i_1++) {
			if (fire[i_1] != null) {
				actual_fire++;
			}
		}
		for (int i_1 = 0; i_1 < water.length; i_1++) {
			if (water[i_1] != null) {
				actual_water++;
			}
		}
		if (actual_water == 0 & actual_fire == 0) {
			return "No one";
		} else if (actual_water > 0 & actual_fire == 0) {
			return "Water";
		} else if (actual_water == 0 & actual_fire > 0) {
			return "Fire";
		}
		return null;
	}
}
