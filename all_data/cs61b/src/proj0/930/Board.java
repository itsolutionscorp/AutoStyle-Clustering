/** 
 *  @author Kevin Bai
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces = new Piece[N][N];
	private static int N = 8;
	private boolean selected;
	private int xs, ys;
	private boolean MadeMove;
	private boolean FireTurn = true;
	private int fpieces = 0;
	private int wpieces = 0;
	

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
	 
	public Board(boolean shouldBeEmpty) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
				pieces[i][j] = null;
			}
		}
		if (shouldBeEmpty != true) {
			for (int i = 0; i < N; i+=2) {
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
			}
			for (int i = 1; i < N; i+=2) {
				pieces[i][1] = new Piece(true, this, i, 1, "shield");
			}
			for (int i = 0; i < N; i+=2) {
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");
			}
			for (int i = 1; i < N; i+=2) {
				pieces[i][5] = new Piece(false, this, i, 5, "bomb");
			}
			for (int i = 0; i < N; i+=2) {
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
			}
			for (int i = 1; i < N; i+=2) {
				pieces[i][7] = new Piece(false, this, i, 7, "pawn");
			}
			fpieces = 12;
			wpieces = 12;
		}
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean k = pieces[xi][yi].isKing();
		boolean s = pieces[xi][yi].isFire();
		boolean c = false;
		boolean ct = false;
		if (pieces[xf][yf] == null) {
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 && MadeMove != true) {
				if (((yi - yf) == 1 && s == false) || ((yi - yf) == -1 && s == true)) {
					return true;
				} else if (k) {
					return true;
				}
				return false;
			} else if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2 && (MadeMove != true || pieces[xi][yi].hasCaptured())) {
				if (pieces[(xi + xf) >>> 1][(yi + yf) >>> 1] != null) {
					ct = pieces[(xi + xf) >>> 1][(yi + yf) >>> 1].isFire();
					if (s != ct) {
						if (((yi - yf) == 2 && s == false) || ((yi - yf) == -2 && s == true)) {
							return true;
						} else if (k) {
							return true;
						}
					}
				}
				return false;
			}
		}
		return false;
	}
	
	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null) {
			if (pieces[x][y].isFire() == FireTurn) {
				if (MadeMove) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (selected) {
			return validMove(xs, ys, x, y);
		}
		return false;
	}

	public Piece pieceAt(int x, int y) {
		if (x >= N || x < 0 || y >= N || y < 0) {
			return null;
		}
		return pieces[x][y];
	}
	
	public void select(int x, int y) {
		if (selected && pieceAt(x,y) == null) {
			pieceAt(xs, ys).move(x,y);
			MadeMove = true;
		}
		xs = x;
		ys = y;
		selected = true;
		if (pieceAt(x,y) == null) {
			selected = false;
		}
	}
	
	public void place(Piece p, int x, int y) {
		if (x >= N || x < 0 || y >= N || y < 0) {
			return;
		}
		if (pieceAt(x,y) != null){
			remove(x,y);
		}
		pieces[x][y] = p;
		if (p.isFire()) {
			fpieces++;
		} else {
			wpieces++;
		}
	}
	
	public Piece remove(int x, int y) {
		if (pieces[x][y] == null) {
			System.out.println("There is no piece here to remove");
			return null;
		}
		if (pieces[x][y].isFire()) {
			fpieces--;
		} else {
			wpieces--;
		}
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}
	
	public boolean canEndTurn() {
		return MadeMove;
	}
	
	public void endTurn() {
		if (FireTurn) {
			FireTurn = false;
		} else {
			FireTurn = true;
		}
		selected = false;
		MadeMove = false;
		if (pieceAt(xs, ys) != null) {
			pieceAt(xs, ys).doneCapturing();
		}
		
	}
	
	public String winner() {
		if (fpieces <= 0 && wpieces <= 0) {
			return "No one";
		}
		if (fpieces <= 0) {
			return "Water";
		}
		if (wpieces <= 0) {
			return "Fire";
		}
		return null;
	}
	
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (i == xs && j == ys && selected == true){
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
                if (pieces[i][j] != null) {
					drawPiece(pieces[i][j], i, j);
                }
            }
        }
    }
	
	private void drawPiece(Piece P, int x, int y) {
		if (P.isFire()) {
			if (P.isKing()) {
				if (P.isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
				} else if(P.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
				}
			} else {
				if (P.isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
				} else if(P.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
				
			}
		} else {
			if (P.isKing()) {
				if (P.isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
				} else if(P.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
				}
			} else {
				if (P.isBomb()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
				} else if(P.isShield()) {
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				} else {
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}
				
			}
		}
	}
	
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board PlayingBoard = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(PlayingBoard.winner() == null) {
            PlayingBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (PlayingBoard.canSelect(x, y)) {
					PlayingBoard.select(x,y);
				}
            }
			if (StdDrawPlus.isSpacePressed() && PlayingBoard.canEndTurn()) {
				PlayingBoard.endTurn();
			}
            StdDrawPlus.show(100);
        }
    }
}