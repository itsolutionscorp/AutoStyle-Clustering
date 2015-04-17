
public class Board {

	private Piece[][] pieces;
	private int player = 0;
	private int has_selected = 0; //0 if no piece selected, 1 otherwise
	private Piece selected; //current piece selected
	private int selx; // selecteds x and y
	private int sely;
	//private int nex;
	//private int ney;
	private Piece removedpiece;
	private boolean hasmoved = false;
	private int fire_pieces_lost = 0;
	private int water_pieces_lost = 0;



    public Board(boolean shouldBeEmpty) {
    	int N = 8;
       	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
       	pieces = new Piece[N][N];
    	if (shouldBeEmpty) {
            pieces = new Piece[N][N];
        	//drawBoard(N);
    	}
    	else {
    		for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		if ((i + j) % 2 == 0) {
            			if (j == 0) {
            			pieces[i][j] = new Piece(true, this, i, j, "pawn");
            			}
            			else if (j == 1) {
            				pieces[i][j] = new Piece(true, this, i, j, "shield");
            			}
            			else if (j == 2) {
            				pieces[i][j] = new Piece(true, this, i, j, "bomb");
            			}
            			else if (j == 5) {
            				pieces[i][j] = new Piece(false, this, i, j, "bomb");
            			}
            			else if (j == 6) {
            				pieces[i][j] = new Piece(false, this, i, j, "shield");
            			}
            			else if (j == 7) {
            				pieces[i][j] = new Piece(false, this, i, j, "pawn");
            			}
            		}
    			}
    		}
    		//drawBoard(N);
		}
	}

    public Piece pieceAt(int x, int y) {
    	if (((x > 7) || (x < 0)) || ((y < 0) || (y > 7))) {
    		return null;
    	}
    	if (pieces[x][y] != null) {
    		return pieces[x][y];
    	}
    	else {
    		return null;
    	}
    } 

    public boolean canSelect(int x, int y) {
    	if (((x > 7) || (x < 0)) || ((y < 0) || (y > 7))) {
    		return false;
    	}
    	if ((has_selected == 0) && (pieces[x][y] != null)) { //selecting piece on board
    		if (pieces[x][y].isFire() && ((player % 2) == 0)) {
    			return true;
    		}
    		if (!pieces[x][y].isFire() && ((player % 2) != 0)) {
    			return true;
    		}
    	}
    	if (has_selected == 1) {
    		if (pieces[x][y] != null) { // this checks to see if your gonna switch pieces or if you cheatin
    			if (hasmoved) { //you can't if you've already moved
    				return false;
    			}
    			if (pieces[x][y].isFire() && ((player % 2) == 0)) {
        			return true;
        		}
        		if (!pieces[x][y].isFire() && ((player % 2) != 0)) {
        			return true;
        		}
    		}
    		if ((hasmoved) && (!selected.hasCaptured())) {
    			return false;
    		}
    		if ((hasmoved) && (pieceAt(selx, sely) == null)) { //checks to see if we pointing at a piece
    			return false;
    		}
    		if (selected.isFire() || selected.isKing()) {
    			if ((x == (selx + 1)) && (y == (sely + 1))) {
    				if (hasmoved == true) {
    					return false;
    				}
    				else {
    					return true;
    				}
    			}
    			if ((x == (selx - 1)) && (y == (sely + 1))) {
    				if (hasmoved == true) {
    					return false;
    				}
    				else {
    					return true;
    				}
    			}
    			//two up right
    			if ((x == (selx + 2)) && (y == (sely + 2)) && (pieces[x-1][y-1] != null)) {
    				if ((!pieces[x-1][y-1].isFire()) && (selected.isFire())) {
    					return true;
    				}
    				if ((!selected.isFire()) && (pieces[x-1][y-1].isFire())) {
    					return true;
    				}
    				else {
    					return false;
    				}
    			} 
    			//two up left
    			if ((x == (selx - 2)) && (y == (sely + 2)) && (pieces[x+1][y-1] != null)) { //checks up two to left
    				if ((!pieces[x+1][y-1].isFire()) && (selected.isFire())) {
    					return true;
    				}
    				if ((!selected.isFire()) && (pieces[x+1][y-1].isFire())) {
    					return true;
    				}
    				else {
    					return false;
    				}
    			}
    		}
    		if (!selected.isFire() || selected.isKing()) {
    			if ((x == (selx + 1)) && (y == (sely - 1))) {
    				if (hasmoved == true) {
    					return false;
    				}
    				else {
    					return true;
    				}
    			}
    			if ((x == (selx - 1)) && (y == (sely - 1))) {
    				if (hasmoved == true) {
    					return false;
    				}
    				else {
    					return true;
    				}
    			}
    			if ((x == (selx + 2)) && (y == (sely - 2)) && (pieces[x-1][y+1] != null)) { // two down right
    				if ((!pieces[x-1][y+1].isFire()) && (selected.isFire())) { // if we fire but they not
    					return true;
    				}
    				if ((!selected.isFire()) && (pieces[x-1][y+1].isFire())) { // if we water and they not
    					return true;
    				}
    				else {
    					return false;
    				}
    			}
    			if ((x == (selx - 2)) && (y == (sely - 2)) && (pieces[x+1][y+1] != null)) { //two down left
    				if ((!pieces[x+1][y+1].isFire()) && (selected.isFire())) { // if we fire but they not
    					return true;
    				}
    				if ((!selected.isFire()) && (pieces[x+1][y+1].isFire())) { // if we water and they not
    					return true;
    				}
    				else {
    					return false;
    				}
    			}
    		}
    	}
    	return false;
    }
    /*public boolean validMove(int xi, int yi, int xf, int yf) {
    	
    } */

    public void select(int x, int y) {
    	if (pieces[x][y] != null) { // if we are picking a space with a piece
    		has_selected = 1;
    		selected = pieces[x][y];
    		selx = x;
    		sely = y;
    		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    	}
    	else { //picking a new space to move to
    		place(selected, x, y); //we already have a piece so we moving
    	}
    }

    public void place(Piece p, int x, int y) {
    	if (((x > 7) || (x < 0)) || ((y < 0) || (y > 7))) {
    		return;
    	}
    	if (pieces[x][y] != null) { // if something exists where we want to go, get rid of it
    		remove(x, y);
    	}
    	pieces[x][y] = p; //puts our current piece in new position
    	selx = x;
    	sely = y;
    	p.move(x, y); // delete old position perform jump/ explosion,
    	hasmoved = true;
    }

    public Piece remove(int x, int y) {
    	Piece[][] newpieces = new Piece[8][8];
    	if (((x > 7) || (x < 0)) || ((y < 0) || (y > 7))) {
    		return null;
    	}
    	for (int i = 0; i<8 ; i++ ) {
    		for (int j = 0; j<8; j++ ) {
    			if ((i != x) || (j != y)) {
    				newpieces[i][j] = pieces[i][j];
    			}
    			else {
    				removedpiece = pieces[x][y];
    			}
    		}
    	}
    	if (removedpiece.isFire()) {
    		fire_pieces_lost += 1;
    	}
    	else {
    		water_pieces_lost += 1;
    	}
    	pieces = newpieces;
    	return removedpiece;
    }

    public boolean canEndTurn() {
    	if ((has_selected == 1) && (selected.hasCaptured())) {
    		return true;
    	}
    	else if ((has_selected == 1) && (hasmoved == true)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public void endTurn() {
    	has_selected = 0;
    	selected.doneCapturing();
    	hasmoved = false;
    	player += 1;
    }

    public String winner() {
    	if ((fire_pieces_lost == 12) && (water_pieces_lost == 12)) {
    		return "No one";
    	}
    	else if (fire_pieces_lost == 12) {
    		return "Water";
    	}
    	else if (water_pieces_lost == 12) {
    		return "Fire";
    	}
    	else {
    		return null;
    	}
    }



	public void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if ((pieces[i][j].isShield()) && (pieces[i][j].isFire()) && (!pieces[i][j].isKing())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	}
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isFire()) && (!pieces[i][j].isKing())) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	}
                	else if ((pieces[i][j].isBomb()) && (!pieces[i][j].isFire()) && (!pieces[i][j].isKing())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	}
                	else if ((pieces[i][j].isShield()) && (!pieces[i][j].isFire()) && (!pieces[i][j].isKing())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	}
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                	}
                	else if ((pieces[i][j].isShield()) && (pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                	}
                	else if ((pieces[i][j].isBomb()) && (pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
               			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                	}
                	else if ((pieces[i][j].isShield()) && (pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
               			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                	}
                	else if ((pieces[i][j].isKing()) && (pieces[i][j].isFire())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                	}
                	else if (pieces[i][j].isFire()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	}
                	else if ((pieces[i][j].isKing()) && (!pieces[i][j].isFire())) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                	}
                	else if (!pieces[i][j].isFire()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}
               	}
           	}
       	}
    }
	public static void main(String[] args) {
	       
        Board b = new Board(false);
        b.drawBoard(8);
		while(true) {
			b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                }
            }
                if (StdDrawPlus.isSpacePressed()) {
                	if (b.canEndTurn()) {
                		b.endTurn();
                		b.winner();
                	}
                }
            StdDrawPlus.show(10);
        }
    } 

}

