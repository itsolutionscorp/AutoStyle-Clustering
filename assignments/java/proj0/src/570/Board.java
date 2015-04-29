public class Board {
    private String[][] pieces = new String[8][8];
    private Piece[][] objects = new Piece[8][8];
    private boolean[][] selectedPieces = new boolean[8][8];
    private boolean emptiness;
    private int turn = 0;
    private Piece selectedPiece;
    private int selectedPieceX = 8;
    private int selectedPieceY = 8;
    private boolean pieceMoved = false;
    private boolean pieceCaptured = false;

    public Board(boolean shouldBeEmpty) {
    	emptiness = shouldBeEmpty;
    	int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new String[N][N];
        selectedPieces = new boolean[N][N];
    	if (!emptiness) {
    		for (int j = 0; j < 8; j++) {
                if (j < 3 || j > 4) {
                	int k = determineK(j);
                	String fileName = determineFile(j);
                	boolean fire = determineFire(j);
                	String type = determineType(j);
                	for (int i = k; i < 8; i += 2) {
                        pieces[i][j] = fileName;
                        objects[i][j] = new Piece(fire, this, i, j, type);
                    }
                }
            }
    	}
    }

    private void drawPieces() {
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(8);
            StdDrawPlus.show(100);
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, pieces[i][j], 1, 1);
                }
                if (StdDrawPlus.mousePressed()) {
			        double x = StdDrawPlus.mouseX();
			        double y = StdDrawPlus.mouseY();
			        selectedPieces[(int) x][(int) y] = true;
			    }            
                if (selectedPieces[i][j]) {
			    	if (canSelect(i, j)) {
			    		select(i, j);
			    	}
			    	else {
                        selectedPieces[i][j] = false;
                    }
			    }
                if (selectedPieces[i][j]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    				StdDrawPlus.picture(i + .5, j + .5, pieces[i][j], 1, 1);
    			}
    			if (StdDrawPlus.isSpacePressed()) {
			    	endTurn();
			    }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
    	if (!withinBounds(x, y)) {
    		return null;
    	}
    	//System.out.println("atPiece: " + pieces[x][y]);
    	//System.out.println("atObject: " + objects[x][y]);
    	return objects[x][y];
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((Math.abs(xi - xf) == 1)) {
            if (!pieceMoved) {
                if (((yi - yf) == 1) && (selectedPiece.isKing() || !selectedPiece.isFire())) {
                    return true;
                }
                if (((yf - yi) == 1) && (selectedPiece.isKing() || selectedPiece.isFire())) {
                    return true;
                }
            }
        }
        if (Math.abs(xi - xf) == 2) {
            Piece p = pieceAt((xi + xf) / 2, (yi + yf) / 2);
            if (p != null) {
                if ((selectedPiece.isFire() && !p.isFire()) || (p.isFire() && !selectedPiece.isFire())) {
                    if (!pieceMoved || (pieceMoved && selectedPiece.hasCaptured())) {
                        if (((yf - yi) == 2) && (selectedPiece.isKing() || selectedPiece.isFire())) {
                            return true;
                        }
                        if (((yi - yf) == 2) && (selectedPiece.isKing() || !selectedPiece.isFire())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean canSelect(int x, int y) {
    	//System.out.println("can" + pieceMoved);
    	Piece p = objects[x][y];
    	//System.out.println("object: " + p);
    	//System.out.println("piece: " + pieces[x][y]);
    	if (!withinBounds(x, y)) {
    		return false;
    	}
    	if (p == null) {
    		if (selectedPiece != null) {
    			if (validMove(selectedPieceX, selectedPieceY, x, y)) {
    				return true;
    			}
    			return false;
    		}
    		return false;
    	}
    	//System.out.println(turn);
    	//System.out.println("isfire" + p.isFire());
    	if (((turn == 0) && p.isFire()) || ((turn == 1) && !p.isFire())) {
    		if (pieceMoved) {
    			return false;
    		}
    		return true;
    	}
    	return false;
    }

    public void select(int x, int y) {
    	//System.out.println(pieceMoved);
		Piece p = pieceAt(x, y);
		if (p != null) {
			if (selectedPiece != null) {
				selectedPieces[selectedPieceX][selectedPieceY] = false;
			}
			selectedPiece = p;
			selectedPieceX = x;
			selectedPieceY = y;
		}
		else {
			if (selectedPiece != null) {
				Piece q = pieceAt(selectedPieceX, selectedPieceY);
				q.move(x, y);
				if (!q.isBomb() && q.isKing() && !pieces[x][y].contains("crowned")) {
					int period = pieces[x][y].indexOf(".");
					String kingedName = pieces[x][y].substring(0, period) + "-crowned" + pieces[x][y].substring(period);
					pieces[x][y] = kingedName;
				}
				if (!q.isBomb() && q.hasCaptured()) {
					selectedPieces[x][y] = true;
					selectedPiece = q;
					selectedPieceX = x;
					selectedPieceY = y;
					select(x, y);
					pieceCaptured = true;
				}
				pieceMoved = true;
			}
		}
    }

    private String printType(Piece p) {
		if (p.isBomb()) { 
			return "bomb";
		}
		if (p.isShield()) {
			return "shield";
		}
		return "pawn";
    }

    private String printElement(Piece p) {
    	if (p.isFire()) {
    		return "fire";
    	}
    	return "water";
    }

	public void place(Piece p, int x, int y) {
    	if (withinBounds(x, y)) {
	    	pieces[x][y] = "img/" + printType(p) + "-" + printElement(p) + ".png";
	    	objects[x][y] = p;
	    	if (selectedPiece == p) {
	    		selectedPiece = p;
	    		selectedPieceX = x;
	    		selectedPieceY = y;
	    		selectedPieces[x][y] = true;
	    	}
    	}
    }

    public Piece remove(int x, int y) {
    	if (!withinBounds(x, y)) {
    		return null;
    	}
    	Piece p = pieceAt(x, y);
    	if (p == null) {
    		return null;
    	}
    	pieces[x][y] = null;
    	objects[x][y] = null;
    	if (selectedPieces[x][y]) {
    		selectedPieces[x][y] = false;
    		selectedPiece = null;
    	}
    	return p;
    }

    public boolean canEndTurn() {
    	//System.out.println("moved" + pieceMoved);
    	//System.out.println("captured" + pieceCaptured);
    	if (pieceMoved || pieceCaptured) {
    		return true;
    	}
    	return false;
    }

    private void switchTurns() {
    	if (turn == 0) {
    		turn = 1;
    	}
    	else {
    		turn = 0;
    	}
    }

    public void endTurn() { 
    	if (canEndTurn()) {
    		selectedPieces = new boolean[8][8];
    		switchTurns();
    		if (selectedPiece != null && selectedPiece.hasCaptured()) {
    			selectedPiece.doneCapturing();
    		}
	    	selectedPiece = null;
	    	selectedPieceX = 8;
	    	selectedPieceY = 8;
	    	pieceMoved = false;
	    	pieceCaptured = false;
    	}
    }

    public String winner() {
    	boolean fire = false;
    	boolean water = false;
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
            		if (pieces[i][j].contains("fire")) {
			    		fire = true;
			    	}
			    	if (pieces[i][j].contains("water")) {
			    		water = true;
			    	}
                }
            }
        }
        if (fire) {
            if (water) {
                return null;
            }
            return "fire";
        }
        if (water) {
            return "water";
        }
        return "No one";
    }

    private boolean withinBounds(int x, int y) {
    	if ((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)) {
    		return true;
    	}
    	return false;
    }

    private String determineElement(int j) {
        if (j < 4) {
            return "fire";
        }
        else {
            return "water";
        }
    }

    private int determineK(int j) {
        if (j % 2 == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    private String determineFile(int j) {
        String side = determineElement(j);
        if (j == 0 || j == 7) {
            return "img/pawn-" + side + ".png";
        }
        else if (j == 1 || j == 6) {
            return "img/shield-" + side + ".png";
        }
        return "img/bomb-" + side + ".png";
    }

    private boolean determineFire(int j) {
        if (j < 4) {
            return true;
        }
        else {
            return false;
        }
    }

    private String determineType(int j) {
        if (j == 0 || j == 7) {
            return "pawn";
        }
        else if (j == 1 || j == 6) {
            return "shield";
        }
        return "bomb";
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        while (true) {
        	b.drawPieces();
        }
    }
}