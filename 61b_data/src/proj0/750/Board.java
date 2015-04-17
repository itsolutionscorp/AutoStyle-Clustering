
public class Board {
	
    private Piece[][] pieces;
    private int N;
    private boolean fireTurn;	// p1 is fire. If this is false it is p2's (ice's) turn
    private boolean hasMoved;
    private boolean hasMadeCapture;
    private Piece cPSelection;
    private int xLocation;
    private int yLocation;
    private boolean bombCondition;
    
    private void drawBoard(int N, boolean newBoard, boolean reset) {
    	if (newBoard) {
    		StdDrawPlus.setXscale(0, N);
		    StdDrawPlus.setYscale(0, N);
		    pieces = new Piece[N][N];
		    fireTurn = true;
		    hasMoved = false;
		    hasMadeCapture = false;
		    cPSelection = null;
		    xLocation = -1;
		    yLocation = -1;
		    bombCondition = false;
    	}
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        //Fun Test
        //pieces[3][3] = new Piece(false, this, 3, 3, "shield");
        //pieces[3][5] = new Piece(false, this, 3, 5, "shield");
        //pieces[3][4] = new Piece(false, this, 3, 4, "shield");
        //pieces[4][3] = new Piece(false, this, 4, 3, "shield");
        //pieces[3][5] = new Piece(false, this, 3, 5, "shield");
        //pieces[5][3] = new Piece(false, this, 5, 3, "shield");
        //pieces[5][4] = new Piece(false, this, 5, 4, "shield");
        //pieces[5][5] = new Piece(false, this, 5, 5, "shield");
        //pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        
        
        //Fun Test
        if (reset) {
    		int k = 0;
    		fireTurn = true;
    		hasMoved = false;
    		hasMadeCapture = false;
    		while (k < N) {
    			if ((k % 2) == 0) {
    				StdDrawPlus.picture(k + 0.5, 0.5, "img/pawn-fire.png", 1, 1);
    				StdDrawPlus.picture(k + 0.5, 2.5, "img/bomb-fire.png", 1, 1);
    				StdDrawPlus.picture(k + 0.5, 6.5, "img/shield-water.png", 1, 1);
    				pieces[k][0] = new Piece(true, this, k, 0, "pawn");
    				pieces[k][2] = new Piece(true, this, k, 2, "bomb");
    				pieces[k][6] = new Piece(false, this, k, 6, "shield");
    				}
    			else {
    				StdDrawPlus.picture(k + 0.5, 1.5, "img/shield-fire.png", 1, 1);
    				StdDrawPlus.picture(k + 0.5, 5.5, "img/bomb-water.png", 1, 1);
    				StdDrawPlus.picture(k + 0.5, 7.5, "img/pawn-water.png", 1, 1);
    				pieces[k][1] = new Piece(true, this, k, 1, "shield");
    				pieces[k][5] = new Piece(false, this, k, 5, "bomb");
    				pieces[k][7] = new Piece(false, this, k, 7, "pawn");
    				}
    			k += 1;
    		}
        }
    }

	public static void main(String[] args) {
		//Board currentBoard = new Board(true);
		Board currentBoard = new Board(false);

        while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int x_int = ((int) x);
                int y_int = ((int) y);
                if (currentBoard.canSelect(x_int, y_int)) {
                	currentBoard.select(x_int, y_int);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if ((currentBoard.canEndTurn())) {
            		currentBoard.endTurn();
            	}
            }
            if (currentBoard.winner() != null) {
            	String win = currentBoard.winner();
            	System.out.println(win);
            	break;
            }
            StdDrawPlus.show(40);
        }
	}
	
	public Board(boolean shouldBeEmpty) {
		N = 8;
		if (shouldBeEmpty) {
		    drawBoard(N, true, false);
		}
		else {
			drawBoard(N, true, true);
		}
	}
	

	
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}
	
	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null) {
			if ((((pieces[x][y].isFire()) == fireTurn) && (! hasMoved))) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if ((cPSelection != null) && (! bombCondition) && (xLocation != x) && (yLocation != y)) {
				boolean is_fire = cPSelection.isFire();
				if (cPSelection.isKing() && (! hasMoved)) {
					return pieceValidMoveKing(xLocation, yLocation, x, y);
				}
				else if (cPSelection.isKing() && hasMadeCapture) {
					return pieceValidCaptureKing(xLocation, yLocation, x, y);
				}
				else if (is_fire && (! hasMoved)) {
					return pieceValidMoveFire(xLocation, yLocation, x, y);
				}
				else if (is_fire && hasMadeCapture) {
					return pieceValidCaptureFire(xLocation, yLocation, x, y);
				}
				else if ((! is_fire) && (! hasMoved)) {
					return pieceValidMoveWater(xLocation, yLocation, x, y);
				}
				else if ((! is_fire) && hasMadeCapture) {
					return pieceValidCaptureWater(xLocation, yLocation, x, y);
				}
				else {
					return false;
				}
			}
		
			else {
				return false;
			}
		}
	}
	
	private boolean pieceValidMoveFire(int xi, int yi, int xf, int yf) {
		if ((xi+1 < N && yi+1 <N) && (pieces[xi+1][yi+1]==null) && xf==xi+1 && yf==yi+1) {
			return true;
		}
		else if ((xi-1 >= 0 && yi+1 < N) && (pieces[xi-1][yi+1]==null) && xf==xi-1 && yf==yi+1) {
			return true;
		}
		else if ((xi+2 < N && yi+2 < N) && (pieces[xi+2][yi+2]==null) && (pieces[xi+1][yi+1] != null) && (cPSelection.isFire() != pieces[xi+1][yi+1].isFire()) && xf==xi+2 && yf==yi+2) {
			return true;
		}
		else if ((xi-2 >= 0 && yi+2 < N) && (pieces[xi-2][yi+2]==null) && (pieces[xi-1][yi+1] != null) && (cPSelection.isFire() != pieces[xi-1][yi+1].isFire()) && xf==xi-2 && yf==yi+2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pieceValidCaptureFire(int xi, int yi, int xf, int yf) {
		if ((xi+2 < N && yi+2 < N) && (pieces[xi+2][yi+2]==null) && (pieces[xi+1][yi+1] != null) && (cPSelection.isFire() != pieces[xi+1][yi+1].isFire()) && xf==xi+2 && yf==yi+2) {
			return true;
		}
		else if ((xi-2 >= 0 && yi+2 < N) && (pieces[xi-2][yi+2]==null) && (pieces[xi-1][yi+1] != null) && (cPSelection.isFire() != pieces[xi-1][yi+1].isFire()) && xf==xi-2 && yf==yi+2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pieceValidMoveWater(int xi, int yi, int xf, int yf) {
		if ((xi-1 >= 0 && yi-1 >= 0) && (pieces[xi-1][yi-1]==null) && xf==xi-1 && yf==yi-1) {
			return true;
		}
		else if ((xi+1 < N && yi-1 >= 0) && (pieces[xi+1][yi-1]==null) && xf==xi+1 && yf==yi-1) {
			return true;
		}
		else if ((xi-2 >= 0 && yi-2 >= 0) && (pieces[xi-2][yi-2]==null) && (pieces[xi-1][yi-1] != null) && (cPSelection.isFire() != pieces[xi-1][yi-1].isFire()) && xf==xi-2 && yf==yi-2) {
			return true;
		}
		else if ((xi+2 < N && yi-2 >= 0) && (pieces[xi+2][yi-2]==null) && (pieces[xi+1][yi-1] != null) && (cPSelection.isFire() != pieces[xi+1][yi-1].isFire()) && xf==xi+2 && yf==yi-2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pieceValidCaptureWater(int xi, int yi, int xf, int yf) {
		if ((xi-2 >= 0 && yi-2 >= 0) && (pieces[xi-1][yi-1]==null) && (pieces[xi-1][yi-1] != null) && (cPSelection.isFire() != pieces[xi-1][yi-1].isFire()) && xf==xi-2 && yf==yi-2) {
			return true;
		}
		else if ((xi+2 < N && yi-2 >= 0) && (pieces[xi+2][yi-2]==null) && (pieces[xi+1][yi-1] != null) && (cPSelection.isFire() != pieces[xi+1][yi-1].isFire()) && xf==xi+2 && yf==yi-2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pieceValidMoveKing(int xi, int yi, int xf, int yf) {
		if ((xi+1 < N && yi+1 <N) && (pieces[xi+1][yi+1]==null) && xf==xi+1 && yf==yi+1) {
			return true;
		}
		else if ((xi-1 >= 0 && yi+1 < N) && (pieces[xi-1][yi+1]==null) && xf==xi-1 && yf==yi+1) {
			return true;
		}
		else if ((xi+2 < N && yi+2 < N) && (pieces[xi+2][yi+2]==null) && (pieces[xi+1][yi+1] != null) && (cPSelection.isFire() != pieces[xi+1][yi+1].isFire()) && xf==xi+2 && yf==yi+2) {
			return true;
		}
		else if ((xi-2 >= 0 && yi+2 < N) && (pieces[xi-2][yi+2]==null) && (pieces[xi-1][yi+1] != null) && (cPSelection.isFire() != pieces[xi-1][yi+1].isFire()) && xf==xi-2 && yf==yi+2) {
			return true;
		}
		else if ((xi-1 >= 0 && yi-1 >= 0) && (pieces[xi-1][yi-1]==null) && xf==xi-1 && yf==yi-1) {
			return true;
		}
		else if ((xi+1 < N && yi-1 >= 0) && (pieces[xi+1][yi-1]==null) && xf==xi+1 && yf==yi-1) {
			return true;
		}
		else if ((xi-2 >= 0 && yi-2 >= 0) && (pieces[xi-2][yi-2]==null) && (pieces[xi-1][yi-1] != null) && (cPSelection.isFire() != pieces[xi-1][yi-1].isFire()) && xf==xi-2 && yf==yi-2) {
			return true;
		}
		else if ((xi+2 < N && yi-2 >= 0) && (pieces[xi+2][yi-2]==null) && (pieces[xi+1][yi-1] != null) && (cPSelection.isFire() != pieces[xi+1][yi-1].isFire()) && xf==xi+2 && yf==yi-2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pieceValidCaptureKing(int xi, int yi, int xf, int yf) {
		if ((xi+2 <= N && yi+2 < N) && (pieces[xi+2][yi+2]==null) && (pieces[xi+1][yi+1] != null) && (cPSelection.isFire() != pieces[xi+1][yi+1].isFire()) && xf==xi+2 && yf==yi+2) {
			return true;
		}
		else if ((xi-2 >= 0 && yi+2 < N) && (pieces[xi-2][yi+2]==null) && (pieces[xi-1][yi+1] != null) && (cPSelection.isFire() != pieces[xi-1][yi+1].isFire()) && xf==xi-2 && yf==yi+2) {
			return true;
		}
		else if ((xi-2 >= 0 && yi-2 >= 0) && (pieces[xi-2][yi-2]==null) && (pieces[xi-1][yi-1] != null) && (cPSelection.isFire() != pieces[xi-1][yi-1].isFire()) && xf==xi-2 && yf==yi-2) {
			return true;
		}
		else if ((xi+2 < N && yi-2 >= 0) && (pieces[xi+2][yi-2]==null) && (pieces[xi+1][yi-1] != null) && (cPSelection.isFire() != pieces[xi+1][yi-1].isFire()) && xf==xi+2 && yf==yi-2) {
			return true;
		}
		else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (pieces[x][y] == null) {
			cPSelection.move(x,y);
			hasMoved = true;
			if (x-xLocation ==2 || xLocation-x ==2) {
				hasMadeCapture = true;
				if (cPSelection.isBomb()) {
					bombCondition = true;
					cPSelection = null;
					xLocation = -1;
					yLocation = -1;
				}
			}
			//reDrawOriginalSquare(xLocation, yLocation);
			xLocation = x;
			yLocation = y;
			reDraw();
		}
		else {
			cPSelection = pieces[x][y];
			xLocation = x;
			yLocation = y;
			reDraw();
		}
	}
	
	private void reDraw() {
		N = 8;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	
            	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	
                //if (cPSelection != null) {
                    //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    //StdDrawPlus.filledSquare(xLocation + .5, yLocation + .5, .5);
                //}
                if (pieces[i][j] != null) {
                	drawPiece(pieces[i][j], i, j);
                }
            }
		}
	}
	
	private void drawPiece(Piece p, int i, int j) {
		if ((p.isFire()) && (p.isBomb())) {
			if (p.isKing()) {
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
			}
		}
		else if (p.isFire() && p.isShield()) {
			if (p.isKing()) {
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
			}
		}
		else if (p.isFire() && (! p.isBomb()) && (! p.isShield())) {
			if (p.isKing()) {
				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
			}
		}
		else if ((! p.isFire()) && p.isBomb()) {
			if (p.isKing()) {
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			}
		}
		else if ((! p.isFire()) && p.isShield()) {
			if (p.isKing()) {
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
			}
		}
		else if ((! p.isFire()) && (! p.isBomb()) && (! p.isShield())) {
			if (p.isKing()) {
				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
			}
			else {
				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			}
		}
		
	}
	
	public void place(Piece p, int x, int y) {
		if ((p != null) && (x>=0) && (x<N) && (y>=0) && (y<N)) {
			pieces[x][y] = p;
			drawPiece(p, x, y);
		}
	}
	
	public Piece remove(int x, int y) {
		if ((x>=0) && (x<N) && (y>=0) && (y<N)) {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			reDraw();
			return temp;
		}
		else {
			reDraw();
			return null;
		}
	}
	
	
	public boolean canEndTurn() {
		return hasMoved;
	}
	
	public void endTurn() {
		hasMoved = false;
		hasMadeCapture = false;
		cPSelection = null;
		bombCondition = false;
		//reDrawOriginalSquare(xLocation, yLocation);
		xLocation = -1;
		yLocation = -1;
		fireTurn = !fireTurn;
	}
	
	public String winner() {
		if (checkNoOne()) {
			return "No One";
		}
		else if (fireCheckVictory()) {
        	return "Fire";
        }
        else if (waterCheckVictory()) {
        	return "Water";
        }
        else {
        	return null;
        }
	}
	
	private boolean waterCheckVictory() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if ((pieces[i][j] != null) && (pieces[i][j].isFire())) {
            		return false;
            	}
            }
        }
        return true;
	}
	
	private boolean fireCheckVictory() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if ((pieces[i][j] != null) && (! pieces[i][j].isFire())) {
            		return false;
            	}
            }
        }
        return true;
	}
	
	private boolean checkNoOne() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if ((pieces[i][j] != null)) {
            		return false;
            	}
            }
        }
        return true;
	}

}
