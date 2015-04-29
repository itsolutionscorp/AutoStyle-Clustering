/** 
 *  @author Thomas Tahk
 *  help from Piazza posts @1145
 */

public class Board {
	private static Piece[][] pieces; //not boolean, instead Piece[][]
	private static Board b; // I'm not sure if this is necessary
	private boolean fires_turn = true;
	private Piece selected;
	private boolean hasMoved = false;

	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		if (pieces[i][j].isShield()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}	
                		}
                		else if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                		else {
        					if (pieces[i][j].isKing()) {
        						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
        					}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	}
                	else if (pieces[i][j].isShield()) {
                		if (pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}
                	}
                	else if (pieces[i][j].isBomb()) {
                		if (pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		}
                	}
                	else {
                		if (pieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                	}
                }
            }
        }
    }

	public static void main (String args[]) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new Piece[N][N]; should this be here?

        Board b = new Board(false); // instantiate board object, true if empty, false if not.

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */

        while(b.winner() == null) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
               	int x_int = (int) x;
               	int y_int = (int) y;
                if (b.canSelect(x_int, y_int)) {
                	b.select(x_int, y_int);
                }
   
                // for now, print where I clicked CHANGE later
                // pieces[(int) x][(int) y] = true;
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }
    }

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			pieces = new Piece[8][8];
		}
		else {
			pieces = new Piece[8][8];
			pieces[0][0] = new Piece(true, b, 0, 0, "pawn");
			pieces[2][0] = new Piece(true, b, 2, 0, "pawn");
			pieces[4][0] = new Piece(true, b, 4, 0, "pawn");
			pieces[6][0] = new Piece(true, b, 6, 0, "pawn");

			pieces[1][1] = new Piece(true, b, 1, 1, "shield");
			pieces[3][1] = new Piece(true, b, 3, 1, "shield");
			pieces[5][1] = new Piece(true, b, 5, 1, "shield");
			pieces[7][1] = new Piece(true, b, 7, 1, "shield");

			pieces[0][2] = new Piece(true, b, 0, 2, "bomb");
			pieces[2][2] = new Piece(true, b, 2, 2, "bomb");
			pieces[4][2] = new Piece(true, b, 4, 2, "bomb");
			pieces[6][2] = new Piece(true, b, 6, 2, "bomb");

			pieces[1][5] = new Piece(false, b, 1, 5, "bomb");
			pieces[3][5] = new Piece(false, b, 3, 5, "bomb");
			pieces[5][5] = new Piece(false, b, 5, 5, "bomb");
			pieces[7][5] = new Piece(false, b, 7, 5, "bomb");

			pieces[0][6] = new Piece(false, b, 0, 6, "shield");
			pieces[2][6] = new Piece(false, b, 2, 6, "shield");
			pieces[4][6] = new Piece(false, b, 4, 6, "shield");
			pieces[6][6] = new Piece(false, b, 6, 6, "shield");

			pieces[1][7] = new Piece(false, b, 1, 7, "pawn");
			pieces[3][7] = new Piece(false, b, 3, 7, "pawn");
			pieces[5][7] = new Piece(false, b, 5, 7, "pawn");
			pieces[7][7] = new Piece(false, b, 7, 7, "pawn");
			// manually set the initial configuration. maybe I could have done a for loop but I don't know how.
		}
	}
	public Piece pieceAt(int x, int y) {
		if ((x>7) || (x < 0) || (y < 0) || (y > 7) || (pieces[x][y] == null)) {
			return null;
		}
		return pieces[x][y];	
	}

	public void place(Piece p, int x, int y) {
		if ((x>7) || (x < 0) || (y < 0) || (y>7) || (p == null))  {
			return;
		}
		if (pieceAt(x, y) != null) {
			p.x = x;
			p.y = y;
			pieces[x][y] = p; 
		}
	}
	
	public boolean canSelect(int x, int y) {
		if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire() == fires_turn)) {
			if ((selected == null) || (hasMoved == false)) {
				return true;
				}
			}
		else if (pieceAt(x, y) == null) {
			if ((selected != null) && (hasMoved == false)) {
				if (validMove(selected.x, selected.y, x, y)) {
					return true;
				}
			}
			if ((selected != null) && (selected.hasCaptured())) {
				if (validMove(selected.x, selected.y, x, y)) {
					return true;
				}
			}
		}
		return false;
	}
			

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece pi = pieceAt(xi, yi);
		Piece pf = pieceAt(xf, yf);
		int x_track = xi;
		int y_track = yi;
		if ((pi == null) || (xf < 0) || (xf > 7) || (yf < 0) || (yf > 7)) {
			return false;  // for out of boundary cases
		}
		else if (pf == null) {
			if (Math.abs(xf-xi) == 1) {   // emptyspace nearby
				if (yf-yi == 1) {
					if ((pi.isFire()) || (pi.isKing())) {
						return true;
					}
				}
				if (yi-yf == 1) {
					if ((!pi.isFire()) || (pi.isKing())) {
						return true;
					}
				}
			}
			else if (Math.abs(xf-xi) == 2) {  // one capture distance
				if (yf-yi == 2) {
					if ((pi.isFire()) || (pi.isKing())) {
						return nearPiece(xi, yi);
					}
				} 
				if (yi-yf == 2) {
					if ((!pi.isFire()) || (pi.isKing())) {
						return nearPiece(xi, yi);
					}
				}
			}
			else if (((xf-xi) % 2 == 0) && ((yf-yi) % 2 == 0)) { //more than one capture distance
				return multiCap(xi, xf, yi, yf);	
			}
		}
		return false;
	}

	private boolean nearPiece(int x, int y) {
		Piece p = pieceAt(x, y);
		Piece p_upright = pieceAt(x+1, y+1);
		Piece p_downright = pieceAt(x+1, y-1);
		Piece p_upleft = pieceAt(x-1, y+1);
		Piece p_downleft = pieceAt(x-1, y-1);
		if (p.isFire()) {
			if ((p_upright != null) || (p_upleft != null)) {
				if ((p_upright.side() == 1) || (p_upleft.side() == 1)) {
					return true;
				}
			}
		}
		if (p.isFire() == false) {
			if ((p_downright != null) || (p_downleft != null)) {
				if ((p_downright.side() == 0) || (p_downleft.side() == 0)) {
					return true;
				}
			}
		}
		return false;
	}
	

	private boolean multiCap(int xi, int xf, int yi, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece pf = pieceAt(xf, yf);
		while (nearPiece(xi, yi) && ((xi != xf) && (yi != yf))) {
			if (direction(xi, xf, yi, yf) == "plusplus") {
				xi += 2;
				yi += 2;
			}
			else if (direction(xi, xf, yi, yf) == "minusplus") {
				xi -= 2;
				yi += 2;
			}
			else if (direction(xi, xf, yi, yf) == "plusminus") {
				xi += 2;
				yi -= 2;
			}
			else if (direction(xi, xf, yi, yf) == "minusminus") {
				xi -= 2;
				yi -= 2;
			}
		}

		return p == pf;
	}

	private String direction(int xi, int xf, int yi, int yf) {
		if ((Math.signum(xf - xi) == 1) && (Math.signum(yf - yi) == 1)) {
			return "plusplus";
		}
		else if ((Math.signum(xf - xi) == 1) && (Math.signum(yf - yi) == -1)) {
			return "plusminus";
		}
		else if ((Math.signum(xf - xi) == -1) && (Math.signum(yf - yi) == 1)) {
			return "minusplus";
		}
		else if ((Math.signum(xf - xi) == -1) && (Math.signum(yf - yi) == -1)) {
			return "minusminus";
		}
		return null;
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) {
			place(selected, x, y);
			selected.move(x, y);
		}	
		if (p != null) {
			selected = pieceAt(x, y);
		}
	}

	public Piece remove(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
			System.out.println("out of bounds");
		}
		else if (pieces[x][y] == null) {
			System.out.println("no piece at (x, y)");
		}
		Piece removed = pieceAt(x, y);   // could need debugging
		pieces[x][y] = null;
		return removed;
	}

	public boolean canEndTurn() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((pieces[i][j].hasCaptured()) || (hasMoved)) {
					return true;
				}
			}
		}
		return false;
	}

	public void endTurn() {
		fires_turn = !fires_turn;
	}

	public String winner() {
		int count_Fire = 0;
		int count_Water = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						count_Fire += 1;
					}
					else {
						count_Water += 1;
					}	
				}			
			}
		}
		if (count_Water == 0) {
			return "Fire";
		}
		else if (count_Fire == 0) {
			return "Water";
		}
		else if ((count_Fire == 0) && (count_Water == 0)) {
			return "No one";
		} 
		else {
			return null;
		}	
	}


}