import java.awt.*;
//Final
public class Board {
	private Piece[][] pieces;
	private Piece selected = null;
	private static int N = 8;
	private int player;
	private int prevX, prevY;
	private int redPiecesLeft, bluePiecesLeft;
	private boolean moved = false;
	// private static String[] fonts =  GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	// private static Font endText = new Font(fonts[17], Font.PLAIN, 100);
	// private static double rotateText = 0, ani = 1;

	public static void main(String args[]) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        String champ = null;

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            champ = b.winner();
            if (champ != null) break;
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) b.select(x, y);
            }            
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) b.endTurn();
            }
            StdDrawPlus.show(100);
        }
        // StdDrawPlus.setFont(endText);
        // while (true) {
        // 	if (StdDrawPlus.isSpacePressed()) break;
        // 	if (rotateText >= 46) ani = -1;
        // 	if (rotateText <= -46) ani = 1;
        // 	rotateText += ani;
        // 	b.drawBoard(N);
        // 	StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        // 	StdDrawPlus.text(4.0, 4.0, champ + "Wins!", rotateText);
        // 	StdDrawPlus.show(50);
        // }
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceAt(i, j) != null) {
                	if (selected == pieceAt(i, j)) StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.picture(i + .5, j + .5, img(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String img(Piece p) {
    	String ret = "img/";
    	if (p.isBomb()) ret += "bomb";
    	else if (p.isShield()) ret += "shield";
    	else ret += "pawn";
    	if (p.isFire()) ret += "-fire";
    	else 			ret += "-water";
    	if (p.isKing()) ret += "-crowned";
    	return ret + ".png";
    }

	public Board(boolean shouldBeEmpty) {
		this.pieces = new Piece[N][N];
		this.player = 0;
		this.prevX = this.prevY = 0;
		this.redPiecesLeft = this.bluePiecesLeft = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				pieces[i][j] = null;
			}
		}
		//Testing end game animations
		// if (!shouldBeEmpty) {
		// 	this.redPiecesLeft = this.bluePiecesLeft = 1;
		// 	pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		// 	pieces[1][1] = new Piece(false, this, 1, 1, "pawn");
		// }
		if (!shouldBeEmpty) {
			this.redPiecesLeft = this.bluePiecesLeft = 12;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i + j) % 2 == 0) {
						if (j == 0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
						if (j == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
						if (j == 2) pieces[i][j] = new Piece(true, this, i, j, "bomb");
						if (j == 5) pieces[i][j] = new Piece(false, this, i, j, "bomb");
						if (j == 6) pieces[i][j] = new Piece(false, this, i, j, "shield");
						if (j == 7) pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
	}

	private boolean inBounds(int x, int y) {
		return ((x < N) && (x >= 0) && (y >= 0) && (y < N));
	}

	public Piece pieceAt(int x, int y) {
		if (inBounds(x, y)) return pieces[x][y];
		return null;
	}

	public boolean canSelect(int x, int y) {
		boolean ret = false;
		if ((selected == null) || (moved == false)) {
			Piece piece = pieceAt(x, y);
			if (piece != null) {
				if ((player == 0) && (piece.isFire())) ret = true;
				if ((player == 1) && (!piece.isFire())) ret = true;
			} else {
				if (validMove(prevX, prevY, x, y)) {
					ret = true;
				}
			}
		} else {
			if ((!selected.isBomb()) && (validMove(prevX, prevY, x, y))) {
				ret = true;
			}
		}
		return ret;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		int updown, leftright;
		if ((inBounds(xf, yf)) && (inBounds(xi, yi))) {

			if (pieceAt(xf, yf) != null) return false;
			if ((xf + yf) % 2 != 0) return false;

			updown = yf - yi;
			leftright = xf - xi;

			if ((updown > 2) || (leftright > 2) || (updown < -2) || (leftright < -2)) return false;
			if ((updown == 0) || (leftright == 0)) return false;

			if (updown == 1) {
				if ((leftright > 1) || (leftright < -1)) return false;
				if ((selected != null) && (!selected.isFire()) && (!selected.isKing())) return false;
				if (moved) return false;
			}

			if (updown == -1) {
				if ((leftright > 1) || (leftright < -1)) return false;
				if ((selected != null) && (selected.isFire()) && (!selected.isKing())) return false;
				if (moved) return false;
			}
			
			if (updown == 2) {
				if ((selected != null) && (!(selected.isFire())) && (!(selected.isKing()))) return false;
				if ((selected != null) && (!selected.hasCaptured()) && (moved)) return false;
				if (leftright == 2) {
					if (pieceAt(xi + 1, yi + 1) == null) return false;
					else {
						if (pieceAt(xi + 1, yi + 1).side() == player) return false;
					}
				} else if (leftright == -2) {
					if (pieceAt(xi - 1, yi + 1) == null) return false;
					else {
						if (pieceAt(xi - 1, yi + 1).side() == player) return false;
					}
				}
			} 

			if (updown == -2) {
				if ((selected != null) && (selected.isFire()) && (!(selected.isKing()))) return false;
				if ((selected != null) && (!selected.hasCaptured()) && (moved)) return false;
				if (leftright == 2) {
					if (pieceAt(xi + 1, yi - 1) == null) return false;
					else {
						if (pieceAt(xi + 1, yi - 1).side() == player) return false;
					}
				} else if (leftright == -2) {
					if (pieceAt(xi - 1, yi - 1) == null) return false;
					else {
						if (pieceAt(xi - 1, yi - 1).side() == player) return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selected = pieceAt(x, y);
			prevX = x;
			prevY = y;
		} else {
			selected.move(x, y);
			prevX = x;
			prevY = y;
			moved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if ((inBounds(x, y)) && (p != null)) {
			if (pieceAt(x, y) != null) remove(x, y);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (pieceAt(i, j) == p) remove(i, j);
				}
			}
			pieces[x][y] = p;
			updateCountPlus(p);
		}
	}

	private void updateCountPlus(Piece p) {
		if (p.isFire()) redPiecesLeft++;
		else 			bluePiecesLeft++; 
	}

	private void updateCountMinus(Piece p) {
		if (p.isFire()) redPiecesLeft--;
		else 			bluePiecesLeft--; 
	}

	public Piece remove(int x, int y) {
		Piece ret = null;
		if (!inBounds(x, y)) {
			System.out.println("Can not remove piece at " + x + ", " + y);
		} else {
			ret = pieceAt(x, y);
			if (ret == null) {
				System.out.println("No piece at " + x + ", " + y);
			} else {
				pieces[x][y] = null;
				updateCountMinus(ret);
			}		
		}
		return ret;
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		if (selected != null) selected.doneCapturing();
		moved = false;
		selected = null;
		player = 1 - player;
		prevX = prevY = -1;
	}

	public String winner() {
		if ((redPiecesLeft == 0) && (bluePiecesLeft == 0)) return "No one";
		if (redPiecesLeft == 0) return "Water";
		if (bluePiecesLeft == 0) return "Fire";
		return null;
	}

}