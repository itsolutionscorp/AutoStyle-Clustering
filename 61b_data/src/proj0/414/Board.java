public class Board {

	private Piece[][] pieces;
	private static int N = 8;
	private boolean fire, hasMoved;
	private Piece selected;

	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                b.select(x, y);
            }

            if (StdDrawPlus.isSpacePressed())
            	b.endTurn();

            StdDrawPlus.show(100);
        }

	}

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty)
			pieces = new Piece[N][N];
		else
			pieces = setupPieces();

		fire = true;
		hasMoved = false;
		selected = null;

	}

	private Piece[][] setupPieces() {
		Piece[][] result = new Piece[N][N];
		String[] type1 = {"pawn", "shield", "bomb"};
		String[] type2 = {"bomb", "shield", "pawn"};

		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				Boolean f = true;
				if (j > 2 && j < 5)
					continue;
				if ((i + j) % 2 != 0)
					continue;
				if (j > 4)
					f = false;
				if (f)
					result[i][j] = new Piece(f, this, i, j, type1[j]);
				else 
					result[i][j] = new Piece(f, this, i, j, type2[(j+1)%3]);
			}
		}

		return result;


	}

	private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected != null && pieces[i][j] == selected)
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieces[i][j] != null)
                	StdDrawPlus.picture(i + .5, j + .5, getImgFile(pieces[i][j]), 1, 1);
            }
        }
    }

    private String getImgFile(Piece p) {
    	String r = "img/";
    	
    	if (p.isShield())
    		r = r + "shield";
    	else if (p.isBomb())
    		r = r + "bomb";
    	else
    		r = r + "pawn";

    	if (p.isFire())
    		r = r + "-fire";
    	else
    		r = r + "-water";
    	if (p.isKing())
    		r = r + "-crowned";

    	return r + ".png";
    }

	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0 || x > N || y > N)
			return null;
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		int[] c = placeOf(selected);

		if (selected == null || !hasMoved) {
	    	if (p != null && !hasMoved && (p.isFire() == fire))
	        	return true;
	    	else
	      		if (selected != null && selected.isFire() == fire && !hasMoved && validMove(c[0], c[1], x, y))
	        		return true;
	        	return selected != null  && selected.isFire() == fire && selected.hasCaptured() && validMove(c[0], c[1], x, y);
	    } 
	    else if (selected != null && selected.hasCaptured())
	        return selected.isFire() == fire && validMove(c[0], c[1], x, y);
	    return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf >= N && yf >= N && xf < 0 && yf < 0) 
			return false;

     	int dx = xf - xi;
     	int dy = yf - yi;
     	Piece p = pieceAt(xi, yi);

     	if (dx*dx == 1 && dy*dy == 1 && !p.hasCaptured()) {
	        if (p.isKing())
	         	return dy == 1 || dy == -1;
	        else if (fire)
	         	return dy == 1;
	        else 
	        	return dy == -1;
     	} 
     	else {
	        Piece k = pieceAt(xi + dx/2, yi + dy/2);
	        if (k != null && p.isFire() != k.isFire() && pieceAt(xf, yf) == null) {
	         	if (p.isKing())
	        		return (dx == 2 || dx == -2) && (dy == 2 || dy == -2);
	         	else if (fire)
	        		return (dx == 2 || dx == -2) && dy == 2;
	        	else 
	        		return (dx == 2 || dx == -2) && dy == -2;
	        }
      	}

      	return false;
		
	}

	private int[] placeOf(Piece p) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (pieces[i][j] == p)
					return new int[]{i, j};
		return null;
	}

	public void select(int x, int y) {
		boolean can = canSelect(x, y);
		if (!can) {
			if (selected == pieceAt(x, y))
				selected = null;
			return;
		}

		if (pieces[x][y] != null)
			selected = pieces[x][y];
		else {
			int[] c = placeOf(selected);
			Piece temp = remove(c[0], c[1]);
			temp.move(x, y);
			place(temp, x, y);
			hasMoved = true;

			if (temp.hasCaptured() && temp.isBomb())
				blow(x, y);
		}

	}

	private void blow(int x, int y) {
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++) {
				int a = x + i;
				int b = y + j;
				if (a < 0 || b < 0 || a >= N || b >= N)
					continue;
				else if (pieceAt(a, b) != null && !pieceAt(a, b).isShield())
					remove(a, b);
			}

		selected = null;
	}

	public void place(Piece p, int x, int y) {
		if (x < 0 || y < 0)
			return;
		if (x >= N || y >= N)
			return;
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || y < 0) {
			System.out.println("remove: out of bounds");
			return null;
		}
		if (x > 7 || y > 7) {
			System.out.println("remove: out of bounds");
			return null;
		}

		Piece p = pieces[x][y];

		if (p == null) {
			System.out.println("remove: there is no piece here");
			return null;
		}

		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn() {
		return hasMoved || (selected != null && selected.hasCaptured());
	}

	public void endTurn() {
		fire = !fire;
		if (selected != null)
			selected.doneCapturing();
		selected = null;
		hasMoved = false;
	}

	public String winner() {
		int ftotal = 0;
		int wtotal = 0;
	    for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	             	if (pieces[i][j] != null) {
	               		if (pieces[i][j].isFire())
	                  		ftotal++;
	                	else
	                  		wtotal++; 
	             	}
	            }
	        }
        if (ftotal == 0 && wtotal == 0)
        	return "No One";
        else if (ftotal != 0 && wtotal != 0)
        	return null;
        else if (ftotal > 0)
        	return "Fire";
        else
        	return "Water";
	}
}