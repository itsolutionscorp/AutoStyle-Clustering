/** 
 * The Board Class for Bomb Checkers
 * @author Domenic Bottini
 */

public class Board {

	private Piece[][] board;
	private Piece currentPiece;
	private int curX, curY;
	private boolean fireturn, hasselected, hasmoved;

	public Board(boolean shouldBeEmpty) {
		this.board = new Piece[8][];
		for (int i = 0; i < 8; i++) {
			this.board[i] = new Piece[8];
		}
		if (!shouldBeEmpty) {
			// boolean isFire, Board b, int x, int y, String type
			new Piece(false, this, 1, 7, "pawn");
			new Piece(false, this, 3, 7, "pawn");
			new Piece(false, this, 5, 7, "pawn");
			new Piece(false, this, 7, 7, "pawn");
			new Piece(false, this, 0, 6, "shield");
			new Piece(false, this, 2, 6, "shield");
			new Piece(false, this, 4, 6, "shield");
			new Piece(false, this, 6, 6, "shield");
			new Piece(false, this, 1, 5, "bomb");
			new Piece(false, this, 3, 5, "bomb");
			new Piece(false, this, 5, 5, "bomb");
			new Piece(false, this, 7, 5, "bomb");
			new Piece(true, this, 0, 0, "pawn");
			new Piece(true, this, 2, 0, "pawn");
			new Piece(true, this, 4, 0, "pawn");
			new Piece(true, this, 6, 0, "pawn");
			new Piece(true, this, 1, 1, "shield");
			new Piece(true, this, 3, 1, "shield");
			new Piece(true, this, 5, 1, "shield");
			new Piece(true, this, 7, 1, "shield");
			new Piece(true, this, 0, 2, "bomb");
			new Piece(true, this, 2, 2, "bomb");
			new Piece(true, this, 4, 2, "bomb");
			new Piece(true, this, 6, 2, "bomb");
		}
		this.fireturn = true;
		this.hasselected = false;
		this.hasmoved = false;
		this.curX = -1;
		this.curY = -1;
	}

	public Piece pieceAt(int x, int y) {
		if (!this.onBoard(x, y)) {
			return null;
		}
		else {
			return board[x][y];
		}
	}
	private boolean isPiece(int x, int y) {
		return (pieceAt(x, y) != null);
	}
	private boolean onBoard(int x, int y) {
		return !(x > 7 || y > 7 || x < 0 || y < 0);
	}

	public boolean canSelect(int x, int y) {
		if (!this.onBoard(x, y)) {
			return false;
		}
		else if (isPiece(x, y) && (!this.hasselected || !this.hasmoved)) {
			return this.pieceAt(x, y).isFire() == this.fireturn;
		}
		else if (!isPiece(x, y) && !this.hasmoved && this.hasselected) {
			return this.validMove(this.curX, this.curY, x, y);
		}
		else {
			return (!this.hasmoved || this.currentPiece.hasCaptured()) 
				&& !isPiece(x, y) && this.validMove(this.curX, this.curY, x, y);
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (!this.isPiece(xi, yi)) {
			return false;
		}
		Piece p = this.pieceAt(xi, yi);
		if (!p.hasCaptured() && (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1
			&& !this.isPiece(xf, yf))) {
			if (!p.isKing()) {
				if (p.isFire()) {
					return yf - yi == 1;
				}
				else {
					return yf - yi == -1;
				}
			}
			else {
				return true;
			}
		}
		else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 
			&& this.isPiece((xi + xf)/2, (yi+ yf)/2)
			&& this.pieceAt((xi + xf)/2,(yi+ yf)/2).isFire() != p.isFire()) {
			if (!p.isKing()) {
				if (p.isFire()) {
					return yf - yi == 2;
				}
				else {
					return yf - yi == -2;
				}
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (!this.hasselected) {
			this.currentPiece = this.pieceAt(x, y);
			this.curX = x;
			this.curY = y;
			this.hasselected = true;
		}
		else if (!this.hasmoved) {
			if (this.isPiece(x, y)) {
				this.hasselected = false;
				this.select(x, y);
			}
			else {
				this.currentPiece.move(x, y);
				this.curX = x;
				this.curY = y;
				this.hasmoved = true;
			}
		}
		else if (this.currentPiece.hasCaptured()) {
			this.currentPiece.move(x, y);
			this.curX = x;
			this.curY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (p == null || !this.onBoard(x, y)) {
			return;
		}
		for (int i = 0; i <= 7; i ++) {
			for (int j = 0; j <= 7; j ++) {
				if (this.board[i][j] == p) {
					this.remove(i, j);
				}
			}
		}
		if (this.pieceAt(x, y) != null) {
			this.remove(x, y);
		}
		this.board[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (!this.onBoard(x, y) || !this.isPiece(x, y)) {
			return null;
		}
		else {
			Piece p = this.board[x][y];
			this.board[x][y] = null;
			return p;
		}
	}

	public boolean canEndTurn() {
		return this.hasmoved;
	}

	public void endTurn() {
		this.fireturn = !this.fireturn;
		this.currentPiece.doneCapturing();
		this.currentPiece = null;
		this.hasselected = false;
		this.hasmoved = false;
		this.currentPiece = null;
		this.curX = -1;
		this.curY = -1;
		if (this.winner() != null) {
          	System.out.println(this.winner());
        }
	}

	public String winner() {
		int fire = 0, water = 0;
		Piece p;
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				p = this.pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						fire++;
					}
					if (!p.isFire()) {
						water++;
					}
				}
			}
		}
		if (fire == 0 && water == 0) {
			return "No one";
		}
		else if (fire == 0) {
			return "Water";
		}
		else if (water == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}

	public static void main(String[] args) {
		Board myBoard = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Piece p;
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            myBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (myBoard.canSelect((int) x, (int) y)) {
                	myBoard.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed() && myBoard.canEndTurn()) {
            	myBoard.endTurn();
            }
            StdDrawPlus.show(100);
        }
	}

	private  void drawBoard(int N) {
		String path = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                this.highlightSquare(i, j);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = this.pieceAt(i, j);
                if (p != null) {
                	path = "img/";
                	if (p.isBomb()) {
                		path = path.concat("bomb-");
                	}
                	else if (p.isShield()) {
                		path = path.concat("shield-");
                	}
                	else {
                		path = path.concat("pawn-");
                	}
                	if (p.isFire()) {
                		path = path.concat("fire");
                	}
                	else {
                		path = path.concat("water");
                	}
                	if (p.isKing()) {
                		path = path.concat("-crowned");
                	}
                	path = path.concat(".png");
                	StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
                }
            }
        }
    }
    private void highlightSquare(int i, int j) {
    	if (this.hasmoved && !this.currentPiece.hasCaptured()) {
    		return;
    	}
    	else if (this.isPiece(i, j) && this.pieceAt(i, j) == this.currentPiece) {
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	}
    	else if (this.canSelect(i, j)) {
    		StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
    	}
    }
}