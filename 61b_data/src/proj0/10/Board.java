public class Board {

	private Piece[][] layout;
	private boolean p1Turn;
	private boolean moved;
	private Piece selected;
	private int selX;
	private int selY;
	private boolean capturing;

	public Board(boolean shouldBeEmpty) {
		layout = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int i = 0; i < layout[0].length; i++) {
            	for (int j = 0; j < layout.length; j++) {
                	if ((i + j) % 2 == 0) {
                		String type = "";
                		boolean isFire = false;
                		if (j < 3) isFire = true;
                		if (j == 0 || j == 7) type = "pawn";
                		if (j == 1 || j == 6) type = "shield";
                		if (j == 2 || j == 5) type = "bomb";
                		if (j < 3 || j > 4) layout[i][j] = new Piece(isFire, this, i, j, type);
                	}
                	else layout[i][j] = null;
                }
            }
		}
		p1Turn = true;
		selected = null;
		moved = false;
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7) return null;

		return layout[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
			
		if (p != null) {
			if (p.isFire() != p1Turn) return false;
			return (selected == null) || !moved;
		}
		else return validMove(x, y);
	}

	private boolean validMove(int x, int y) {
		if (selected != null) {
			int turn;
			Piece q = null;
			int xDiff = java.lang.Math.abs(x - selX);
			int yDiff = java.lang.Math.abs(y - selY);

			capturing = ((xDiff == 2) && (yDiff == 2));

			if (capturing)
				q = pieceAt((selX + x)/2, (selY + y)/2);

			if (q != null) {
				if (q.isFire() == selected.isFire()) return false;
			}
			else capturing = false;
			

			if (p1Turn) turn = 1;
			else turn = -1;
			

			if (capturing) turn *= 2;
			if (((xDiff == 1) && !moved) || capturing) {
				if ((y - selY) == turn) return true;
				else if (selected.isKing() && ((y - selY) == -1*turn)) return true;
			}
		}
		return false;
	}


	public void select(int x, int y) {
		if (layout[x][y] == null) {
			// place(selected, x, y);
			selected.move(x, y);
			moved = true;
			if (selected.isBomb() && selected.hasCaptured()) remove(x, y);	
		}
		else selected = pieceAt(x, y);
		selX = x;
		selY = y;
	}

	public void place(Piece p, int x, int y) {
		if (p == null) return;
		if (x >= 0 && y >= 0 && x < 8 && y < 8)
			layout[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x >= layout[0].length || y >= layout.length) {
			System.out.println("Index off board");
			return null;
		}
		else if(layout[x][y] == null) {
			System.out.println("Index does not contain a piece");
			return null;
		}
		Piece removed = layout[x][y];
		layout[x][y] = null;
		return removed;
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		p1Turn = !p1Turn;
		moved = false;
		if (selected != null) selected.doneCapturing();
		selected = null;
	}

	public String winner() {
		Piece p;
		int fire = 0;
		int water = 0;

		for (int i = 0; i < layout[0].length; i++)
            for (int j = 0; j < layout.length; j++) {
            	p = layout[i][j];
            	if (p != null) {
	            	if (p.isFire()) fire++;
	            	else water++;
	            }
            }
       	if (fire == 0 && water == 0) return "No one";
       	else if (fire == 0) return "water";
       	else if (water == 0) return "fire";
		return null;
	}

	private void drawBoard(int N) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                if (selected != null && i == selX && j == selY) {
                	if (p1Turn) StdDrawPlus.setPenColor(StdDrawPlus.RED);
					else StdDrawPlus.setPenColor(StdDrawPlus.BLUE); 
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece p = layout[i][j];
                if (p != null) {
                	String s = "";
                	if (p.isBomb()) s += "bomb-";
                    else if (p.isShield()) s += "shield-";
                    else s += "pawn-";
                    if (p.isFire()) s += "fire";
                    else s += "water";
                    if (p.isKing()) s += "-crowned";
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + s + ".png", 1, 1);
                }
            }
        }
    }

	public static void main(String args[]) {
		Board b = new Board(false);


		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        while(true) {

            b.drawBoard(N);
            if (b.winner() != null) break;

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) b.select((int) x, (int) y);
            }
            
            StdDrawPlus.show(50);
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn())
            	b.endTurn();
            
        }
        System.out.println(b.winner());
        return;
	}
}