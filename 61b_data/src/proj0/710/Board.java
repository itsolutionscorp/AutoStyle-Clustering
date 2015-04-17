public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private int x = -1;
	private int y = -1;
	private boolean hasMoved = false;
	private boolean fTurn = true;


	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.x == i && this.y == j) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }   
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                String pieceLocation = "img/";
                Piece a = pieces[i][j];
                	
                if (pieces[i][j] != null) {
                	if (a.isBomb()) {
                	pieceLocation += "bomb";
                	} else if (a.isShield()){
                	pieceLocation += "shield";
                	} else {
                	pieceLocation += "pawn";
                	}

                	if (a.isFire()) {
	                	pieceLocation += "-fire";
	                } else {
	                	pieceLocation += "-water";
	                }

	                if (a.isKing()) {
	                	pieceLocation += "-crowned";
	                }
	                pieceLocation += ".png";
                    StdDrawPlus.picture(i + .5, j + .5, pieceLocation, 1, 1);

                }
            }
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
		while(b.winner() == null) {
        	b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y))
                {
                	b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
            	 if (b.canEndTurn())
                {
                	b.endTurn();
                }
            }           
            StdDrawPlus.show(10);
        }
        b.drawBoard();
        System.out.println(b.winner() + " wins!");
    }

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty){
			int i = 0;
			while (i < 8) {
				this.pieces[i][0] = new Piece(true, this, i, 0, "pawn");
		      	this.pieces[(i + 1)][1] = new Piece(true, this, i + 1, 1, "shield");
		      	this.pieces[i][2] = new Piece(true, this, i, 2, "bomb");
		      	this.pieces[(i + 1)][7] = new Piece(false, this, i + 1, 7, "pawn");
		      	this.pieces[i][6] = new Piece(false, this, i, 6, "shield");
		      	this.pieces[(i + 1)][5] = new Piece(false, this, i + 1, 5, "bomb");
		      	i += 2;
			}
		}
			
	}
	private boolean withinBounds(int x, int y){
		if (x > 7 || y > 7 || x < 0 || y <0) {
			return false;
		}
		return true;
	}

	public Piece pieceAt(int x, int y){
		if (withinBounds(x, y)) {
			return this.pieces[x][y];
		}
		return null;
	}
	
	public void select(int x, int y) {
		Piece q = pieceAt(this.x, this.y);
		this.x = x;
		this.y = y;
		if (pieceAt(x, y) == null) {
			hasMoved = true;
			q.move(x, y);
		}
	}

	public void place(Piece p, int x, int y) {
		if ((p != null) && withinBounds(x, y)) {
			pieces[x][y] = p;
		}
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null){
			if (p.isFire() == fTurn){
				return !hasMoved;
			}
			else return false;
		}
		Piece q = pieceAt(this.x, this.y);
		if (q == null) {
			return false;
		}
		if (!q.isKing()){
			if (q.isFire() && y <= this.y) {
				return false;
			}
			else if (!q.isFire() && y >= this.y) {
				return false;
			}
		}

		if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1) {
			return !hasMoved;
		}
		else if (Math.abs(this.x-x) == 2 && Math.abs(this.y-y) == 2){
			if (!q.hasCaptured() && hasMoved) {
				return false;
			}
			int xCapture = (this.x + x) / 2;
			int yCapture = (this.y + y) / 2;
			if (pieceAt(xCapture, yCapture) == null) {
				return false;
			}
			return pieceAt(xCapture, yCapture).isFire() != q.isFire();
		}
		return false; 
	}
    			

	public Piece remove(int x, int y) {
		if (!withinBounds(x, y)) {
			System.out.println("Error: Out of Bounds!");
			return null;
		}
		Piece p = pieces[x][y];
		if (p != null){
			pieces[x][y] = null; 
			return p;
		}
		else {
			System.out.println("Error: No piece found!");
			return null;
		}
	}

	public boolean canEndTurn(){
		return hasMoved;
	}
	public void endTurn(){

		Piece q = pieceAt(this.x, this.y);
		fTurn = !fTurn;
		x = -1;
		y = -1;
		if (q != null){
			q.doneCapturing();
		}
		hasMoved = false;
	}
	public String winner() {
		int numFire = 0;
	    int numWater = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Piece p = pieces[i][j];
            	if (p != null){
	            	if (pieces[i][j].isFire()){
	            		numFire += 1;
	            	}
	                if (!pieces[i][j].isFire()) {
	                	numWater += 1;
	                }
            	}
            }
        }
      	if (numFire > 0 && numWater == 0){
                	return "Fire";
                } else 
                if (numFire == 0 && numWater > 0){
                	return "Water";
                }
				else if (numFire == 0 && numWater == 0) {
                	return "No one";
                }
		return null;
	}

}