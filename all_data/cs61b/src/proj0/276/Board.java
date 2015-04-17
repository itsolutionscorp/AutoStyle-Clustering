public class Board {
	private boolean isEmpty;
	private int width;
	private Piece[][] pieces;
	private int W;

	private boolean hasSelected;
	private boolean hasMoved;
	private int[] selected;
	private int turn;

	public Board(boolean shouldBeEmpty) {
		hasSelected = false;
		hasMoved = false;
		isEmpty = shouldBeEmpty;
		width = 8;
		W = width;
		pieces = new Piece[W][W];
		selected = new int[]{-1, -1};
		turn = 0;

        // Initialize board
        if (!shouldBeEmpty) {
            for (int i = 0; i < W; i += 2) {
                place(new Piece(true, this, i, 0, "pawn"), i, 0);
                place(new Piece(false, this, i, W-2, "shield"), i, W-2);
                place(new Piece(true, this, i, 2, "bomb"), i, 2);
            }
            for (int i = 1; i < W; i += 2) {
                place(new Piece(false, this, i, W-1, "pawn"), i, W-1);
                place(new Piece(true, this, i, 1, "shield"), i, 1);
                place(new Piece(false, this, i, W-3, "bomb"), i, W-3);
            }
        }

	}

    private void drawBoard() {
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < W; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if ((i == selected[0]) && (j == selected[1])) StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if ((isEmpty == false) && (pieceAt(i, j) != null))
                    StdDrawPlus.picture(i + .5, j + .5, pieceImage(i, j), 1, 1);
                }
            }
        }

    private String pieceImage(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p == null) return null;
        String str = "img/";
        if (p.isShield())    str += "shield-";
        else if (p.isBomb()) str += "bomb-";
        else                 str += "pawn-";
        if (p.isFire())      str += "fire";
        else                 str += "water";
        if (p.isKing())      str += "-crowned.png";
        else                 str += ".png";
        return str;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((xf < 0) || (yf < 0) || (xf >= W) || (yf >= W)) return false;
    	Piece init = pieceAt(xi, yi);
    	if (init == null) return false;
    	int dx = xf - xi;
    	int dy = yf - yi;
    	if (pieceAt(xf, yf) != null) return false;
    	if ((init.isFire()) || init.isKing()) {
    		if (dy == 1) {
    			if (init.hasCaptured()) return false;
    			if ((dx == 1) || (dx == -1)) return true;
    		}
    		if (dy == 2) {
    			if ((dx == 2) || (dx == -2)) {
    				if ( !killMove(xi, yi, xf, yf) ) return false;
    				else return true;
    			}
    		}
    	}
    	if ((init.isFire() == false) || init.isKing()) {
    		if (dy == -1) {
    			if (init.hasCaptured()) return false;
    			if ((dx == 1) || (dx == -1)) return true;
    		}
    		if (dy == -2) {
    			if ((dx == 2) || (dx == -2)) {
    				if ( !killMove(xi, yi, xf, yf) ) return false;
    				else return true;
    			}
    		}
    	}
    	return false;
    }

    private void deselect() {
    	if (selected[0] != -1) {
			if ((selected[0] + selected[1]) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	        StdDrawPlus.filledSquare(selected[0] + .5, selected[1] + .5, .5);
			if (pieceAt(selected[0], selected[1]) != null)
			StdDrawPlus.picture(selected[0] + .5, selected[1] + .5, pieceImage(selected[0], selected[1]), 1, 1);
		}
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            pieceAt(selected[0], selected[1]).move(x, y);
            hasMoved = true;
        }
        selected[0] = x;
        selected[1] = y;
        hasSelected = true;
    }


    private boolean killMove(int xi, int yi, int xf, int yf) {
    	Piece init = pieceAt(xi, yi);
    	int dx = xf - xi;
    	int dy = yf - yi;
		if ( !((dx == 2) || (dx == -2)) ) return false;
		Piece killPiece = pieceAt(xi + (dx/2), yi + (dy/2));
		if ((killPiece != null) && (killPiece.isFire() != init.isFire())) return true;
		return false;
    }

    public boolean canSelect(int x, int y) {
    	if ((x < 0) || (y < 0) || (x >= W) || (y >= W)) return false;
    	if ((hasSelected == false) && pieceAt(x, y) == null) return false;
    	if ((hasSelected == false) && pieceAt(x, y).side() != turn) return false;
    	if ((hasSelected == false) && pieceAt(x, y).side() == turn) return true;
        // Must have selected by this point.
    	int xi = selected[0];
	   	int yi = selected[1];
	   	Piece init = pieceAt(selected[0], selected[1]);

	   	if (init == null) return false;
	   	if ((hasMoved == true) && (init.hasCaptured() == false)) return false;
		if (validMove(xi, yi, x, y)) return true;
    	if ((hasMoved == false)) {
    		if (sameTeam(x, y)) return true;
    		else return false;
    	}
		else return false;
    }

    public Piece pieceAt(int x, int y) {
    	if ((x < 0) || (y < 0) || (x >= W) || (y >= W)) return null;
    	return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
    	pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
    	if ((x < 0) || (y < 0) || (x >= W) || (y >= W)) return null;
    	Piece toReturn = pieces[x][y];
    	pieces[x][y] = null;
    	return toReturn;
    }



    public boolean canEndTurn() {
    	return hasMoved;
    }

    public void endTurn() {
    	if (turn == 1) turn = 0;
    	else turn = 1;
    	hasSelected = false;
    	hasMoved = false;
    	if (pieceAt(selected[0], selected[1]) != null)
    		pieceAt(selected[0], selected[1]).doneCapturing();
    }

    private boolean canMove(int x, int y) {
    	if (pieceAt(x, y) == null) return false;
    	if (validMove(x, y, x+1, y+1)) return true;
    	if (validMove(x, y, x+1, y-1)) return true;
    	if (validMove(x, y, x+2, y+2)) return true;
    	if (validMove(x, y, x+2, y-2)) return true;
    	if (validMove(x, y, x-1, y+1)) return true;
    	if (validMove(x, y, x-1, y-1)) return true;
    	if (validMove(x, y, x-2, y+2)) return true;
    	if (validMove(x, y, x-2, y-2)) return true;
    	return false;
    }

    public String winner() {
    	int numFires = 0;
    	int numWaters = 0;
    	for (int i = 0; i < W; i++) {
            for (int j = 0; j < W; j++) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isFire()) numFires += 1;
                    else                        numWaters += 1;
                }
           	}
        }
        if ((numWaters != 0) && (numFires == 0)) return "Water";
		else if ((numWaters == 0) && (numFires != 0)) return "Fire";
        else if ((numWaters == 0) && (numFires == 0)) return "No one";
        else return null;
    }


    private boolean sameTeam(int x, int y) {
    	return (pieceAt(x, y) != null) && (pieceAt(x, y).side() == turn);
    }

	public static void main(String[] args) {
        Board theBOARD = new Board(false);
        StdDrawPlus.setXscale(0, theBOARD.W);
        StdDrawPlus.setYscale(0, theBOARD.W);

        // theBOARD.play();

        theBOARD.drawBoard();

        while (theBOARD.winner() == null) {
            int xClick = -2;
            int yClick = -2;
            while (xClick == -2) {
                if (StdDrawPlus.mousePressed()) {
                    xClick = (int) StdDrawPlus.mouseX();
                    yClick = (int) StdDrawPlus.mouseY();
                    while (StdDrawPlus.mousePressed()) {}
                }
                if (StdDrawPlus.isSpacePressed()) {
                    while (StdDrawPlus.isSpacePressed()) {}
                    if (theBOARD.canEndTurn()) theBOARD.endTurn();
                    xClick = -1;
                    yClick = -1;
                }
            }
            if (theBOARD.canSelect(xClick, yClick)) {
                theBOARD.select(xClick, yClick);
                theBOARD.drawBoard();

            }
        }

        System.out.println("GAME OVER");
        System.out.println(theBOARD.winner() + " wins");
        return;
    }
}