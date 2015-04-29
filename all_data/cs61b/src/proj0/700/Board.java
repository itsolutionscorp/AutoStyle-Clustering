public class Board {

	private Piece[][] pieces;
	private int N = 8, fire_num = 0, water_num = 0;
	private boolean selected = false, moved = false, captured = false;
	private boolean turn = true; //when true it's fire's turn
	private double xi, yi;
	
	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0,N);

		Board board = new Board(false);

		while(true) {
			board.drawBoard(N);
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if (board.canSelect((int)x, (int)y)){
                	board.select((int)x,(int)y);
                }
            } 
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) board.endTurn();
			}
			StdDrawPlus.show(100);
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++){
					if ((j == 0) && ((i % 2) == 0)) pieces[i][j] = new Piece(true, this, i, j, "pawn");
					if ((j == 1) && ((i % 2) == 1)) pieces[i][j] = new Piece(true, this, i, j, "shield");
					if ((j == 2) && ((i % 2) == 0)) pieces[i][j] = new Piece(true, this, i, j, "bomb");
					if ((j == 5) && ((i % 2) == 1)) pieces[i][j] = new Piece(false, this, i, j, "bomb");
					if ((j == 6) && ((i % 2) == 0)) pieces[i][j] = new Piece(false, this, i, j, "shield");
					if ((j == 7) && ((i % 2) == 1)) pieces[i][j] = new Piece(false, this, i, j, "pawn");
				}
			}
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }

        if (selected) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(xi + .5, yi + .5, .5);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null){
                	if ((pieces[i][j].isShield()) && (pieces[i][j].isFire()) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	else if ((pieces[i][j].isShield()) && (pieces[i][j].isFire() != true) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire()) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire() != true) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);

                	else if ((pieces[i][j].isShield()) && (pieces[i][j].isFire()) && (pieces[i][j].isKing())) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                	else if ((pieces[i][j].isShield()) && (pieces[i][j].isFire() != true) && (pieces[i][j].isKing())) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire()) && (pieces[i][j].isKing())) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire() != true) && (pieces[i][j].isKing())) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                	
                	else {
                		if ((pieces[i][j].isFire()) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		else if ((pieces[i][j].isFire()) && (pieces[i][j].isKing())) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		else if ((pieces[i][j].isFire() != true) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		else if ((pieces[i][j].isFire() != true) && (!(pieces[i][j].isKing()))) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                	}
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
    	//System.out.printf("(%d, %d)%n", x, y);
    	if (!((x > N) || (y > N))) {
    		if (pieces[x][y] != null) {
    			return pieces[x][y];
    		}else {
    			return null;
    		}

    	}else {
    		return null;
    	}
    }

    public boolean canSelect(int x, int y) {
    	if (!((x > 7) || (y > 7))) {
	    	if (pieces[x][y] != null){
	    		if (((!selected) || ((selected) && !moved)) && (pieces[x][y].isFire() == turn)) {
	    			System.out.println("normal move");
	    			return true;
	    		} else {
	    			return false;
	    		}
	    	}else{
	    		if ((selected) && (validCapture((int) xi, (int) yi, x, y)) && (pieces[(int) xi][(int) yi].hasCaptured())) {
	    			System.out.println("multi capture");
	    			return true;
	    		}
	    		else if (((selected) && !(moved)) && (validMove((int)xi, (int) yi, x, y)) || (validCapture((int) xi, (int) yi, x, y)) ) {
	    			System.out.println("already selected and moving");
	    			return true;
	    		}
	    	}
	    }
	    System.out.println("non valid move");
    	return false;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
    	Piece orig = pieces[xi][yi];
    	Piece end = pieces[xf][yf];
    	int x_opp_p = xi + 1;
    	int y_opp_p = yi + 1;
    	int x_opp_s = xi - 1;
    	int y_opp_s = yi - 1;

    	if (!((xi > 7) || (xf > 7) || (yi > 7) || (yf > 7))) {

    		if ((orig != null) && (end == null)) {

		    	if (orig.isKing()) {
		    		if (((xf == x_opp_p + 1) && (yf == y_opp_p + 1)) && (pieces[x_opp_p][y_opp_p] != null)) {
		    			if ((pieces[x_opp_p][y_opp_p].isFire()) != orig.isFire()) return true;
		    		}
		    		else if (((xf == x_opp_s - 1) && (yf == y_opp_p + 1)) && (pieces[x_opp_s][y_opp_p] != null)) {
		    			if ((pieces[x_opp_s][y_opp_p].isFire()) != orig.isFire()) return true;
		    		}
		    		else if (((xf == x_opp_p + 1) && (yf == y_opp_s - 1)) && (pieces[x_opp_p][y_opp_s] != null)) {
		    			if ((pieces[x_opp_p][y_opp_s].isFire()) != orig.isFire()) return true;
		    		}
		    		else if (((xf == x_opp_s - 1) && (yf == y_opp_s - 1)) && (pieces[x_opp_s][y_opp_s] != null)) {
		    			if ((pieces[x_opp_s][y_opp_s].isFire()) != orig.isFire()) return true;
		    		}
		    	}

		    	else if (orig.isFire()) {
		    		if (((xf == x_opp_p + 1) && (yf == y_opp_p + 1)) && (pieces[x_opp_p][y_opp_p] != null)) {
		    			if ((pieces[x_opp_p][y_opp_p].isFire()) != orig.isFire()) return true;
		    		}
		    		else if (((xf == x_opp_s - 1) && (yf == y_opp_p + 1)) && (pieces[x_opp_s][y_opp_p] != null)) {
		    			if ((pieces[x_opp_s][y_opp_p].isFire()) != orig.isFire()) return true;
		    		}
		    	}

		    	else if (!(orig.isFire())) {
		    		if (((xf == x_opp_p + 1) && (yf == y_opp_s - 1)) && (pieces[x_opp_p][y_opp_s] != null)) {
		    			if ((pieces[x_opp_p][y_opp_s].isFire()) != orig.isFire()) return true;
		    		}
		    		else if (((xf == x_opp_s - 1) && (yf == y_opp_s - 1)) && (pieces[x_opp_s][y_opp_s] != null)) {
		    			if ((pieces[x_opp_s][y_opp_s].isFire()) != orig.isFire()) return true;
		    		}
		    	}

	    	}
	    }
    	return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	Piece orig = pieces[xi][yi];
    	Piece end = pieces[xf][yf];
    	int x_opp_p = xi + 1;
    	int y_opp_p = yi + 1;
    	int x_opp_s = xi - 1;
    	int y_opp_s = yi - 1;

    	if (!((xi > 7) || (xf > 7) || (yi > 7) || (yf > 7))) {

    		if ((orig != null) && (end == null)) {

		    	if (orig.isKing()) {
		    		if (((xf == x_opp_p) && (yf == y_opp_p)) || ((xf == x_opp_s) && (yf == y_opp_p)) || ((xf == x_opp_p) && (yf == y_opp_s)) || ((xf == x_opp_s) && (yf == y_opp_s))) {
		    			return true;
		    		}
		    	}

		    	else if (orig.isFire()) {
		    		if (((xf == x_opp_p) && (yf == y_opp_p)) || ((xf == x_opp_s) && (yf == y_opp_p))) {
		    			return true;
		    		}
		    	}

		    	else if (!(orig.isFire())) {
		    		if (((xf == x_opp_p) && (yf == y_opp_s)) || ((xf == x_opp_s) && (yf == y_opp_s))) {
		    			return true;
		    		}
		    	}

	    	}
	    }
    	return false;
    }

    public void select(int x, int y) {
    	if (pieces[x][y] != null) {
    		//System.out.println("1");
    		selected = true;
    		xi = x;
    		yi = y;
	    }else {
	    	//System.out.println("2");
    		pieces[(int)xi][(int)yi].move(x, y);
    		xi = x;
    		yi = y;
    		//selected = false;
    		moved = true; // need to check if next move is still valid
	    }
    }

    public void place(Piece p, int x, int y) {
    	if (!((x > 7 || y > 7 || x < 0 || y < 0) || (p == null))) {
    		if (pieces[x][y] == null) pieces[x][y] = p;
    		else { 
    			pieces[x][y] = null;
    			pieces[x][y] = p;
    		}
    	}
    }

    public Piece remove(int x, int y) {
    	if (!(x > 7 || y > 7 || x < 0 || y < 0)) {
    		if (pieces[x][y] == null) System.out.println("Nothing to remove.");
    		else {
	    		Piece tmp = pieces[x][y];
	    		pieces[x][y] = null;
	    		return tmp;
	    	}
	    	return null;
    	}
    	System.out.println("Cannot remove piece, out of range!");
    	return null;
    }

    public boolean canEndTurn() {
    	if ((moved) || captured) {// || implement has captured
    		return true;
    	}
    	return false;
    }

	public void endTurn() {
		turn = !turn;
		selected = false;
		moved = false;
		if (pieces[(int)xi][(int)yi] != null) pieces[(int)xi][(int)yi].doneCapturing();
		System.out.println("--------");
	}

	public String winner() {
		water_num = 0;
		fire_num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) fire_num += 1;
					else water_num += 1;
				}
			}
		}

		if ((water_num == 0) && (fire_num == 0)) return "No one";
		else if (water_num == 0) return "Fire";
		else if (fire_num == 0) return "Water";
		else return null;
	}
}




