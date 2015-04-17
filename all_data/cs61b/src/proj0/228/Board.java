public class Board {
	/* Some assistance provided by Justin Liu*/
	private Piece[][] pieces;
	private boolean[][] highlight;
    private int currPlayer;
    private boolean hasMoved;
    private Piece selectedPiece;
    private int selectedX;
    private int selectedY;

	public Board(boolean shouldbeEmpty) {
		int currPlayer = 0;
	    boolean hasMoved = false;
	    Piece selectedPiece = null;
	    int selectedX = -100;
	    int selectedY = -100;
	    pieces = new Piece[8][8];
	    highlight = new boolean[8][8];

		if (shouldbeEmpty) {
			//initialize an empty board
		}
		else {
			//initialize an default board
			for(int i = 0; i < 8; i = i + 2){
	        	pieces[i][0] = new Piece(true, this, i, 0, "pawn");
	        }
	        for(int i = 1; i < 8; i = i + 2){
	        	pieces[i][1] = new Piece(true, this, i, 1, "shield");
	        }
	        for(int i = 0; i < 8; i = i + 2){
	        	pieces[i][2] = new Piece(true, this, i, 2, "bomb");
	        }	
	        for(int i = 1; i < 8; i = i + 2){
	        	pieces[i][5] = new Piece(false, this, i, 5, "bomb");
	        }
	        for(int i = 0; i < 8; i = i + 2){
	        	pieces[i][6] = new Piece(false, this, i, 6, "shield");
	        }	
	        for(int i = 1; i < 8; i = i + 2){
	        	pieces[i][7] = new Piece(false, this, i, 7, "pawn");
	        }	
		}
	}


    private static void drawBoard(Board b) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (b.highlight[i][j]){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.pieceAt(i, j) != null){
	                if (b.pieceAt(i, j).isFire()){
	                	if (b.pieceAt(i, j).isKing()) {
	                		if (b.pieceAt(i, j).isBomb())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		else if(b.pieceAt(i, j).isShield())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		else
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                	}
	                	else{
	               			if (b.pieceAt(i, j).isBomb())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		else if(b.pieceAt(i, j).isShield())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		else
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                	}
	                }
	                else{
	                	if (b.pieceAt(i, j).isKing()) {
	                		if (b.pieceAt(i, j).isBomb())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		else if(b.pieceAt(i, j).isShield())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		else
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                	}
	                	else{
	               			if (b.pieceAt(i, j).isBomb())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		else if(b.pieceAt(i, j).isShield())
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		else
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                	}
	                }
                }
            }
        }
    }

	public static void main (String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
		while(true) {
			drawBoard(b);
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
        		if(b.canSelect((int) x, (int) y))
                	b.select((int) x, (int) y);
            }
            if (StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn())
            		b.endTurn();
            }
			StdDrawPlus.show(10);
		}
		
	}

	

	public Piece pieceAt(int x, int y) {
		if(x > 7 || y > 7)
			return null;
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y){
		if(p == null || x > 7 || y > 7){}
		else{
			pieces[x][y] = p;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece checkPiece = pieces[xi][yi];
		if(Math.abs(xi-xf) == 1 && Math.abs(yi - yf) == 1 && !hasMoved && !selectedPiece.hasCaptured()){
			if(checkPiece.isKing()){
					return true;
			}
			else if (checkPiece.isFire()){
				if(yf - yi == 1 && Math.abs(xi - xf) == 1) {
					return true;
				}
			} 
			else {
				if(yi - yf == 1 && Math.abs(xi - xf) == 1) {
					return true;
				}
			}
		} 
		else if(Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2 && !hasMoved){
			if (checkPiece.isKing() && checkPiece.isFire() ){
				if(xi < xf && yf > yi){
					if (pieceAt(xi + 1, yi + 1)!= null && !pieceAt(xi + 1, yi + 1).isFire())
						return true;
				}
				if(xi < xf && yf < yi){
					if (pieceAt(xi + 1, yi - 1) != null && !pieceAt(xi + 1, yi - 1).isFire())
						return true;
				}
				if(xi > xf && yf > yi){
					if (pieceAt(xi - 1, yi + 1)!= null && !pieceAt(xi - 1, yi + 1).isFire())
						return true;
				}
				else{
					if (pieceAt(xi - 1, yi - 1)!= null && !pieceAt(xi - 1, yi - 1).isFire())
						return true;
				}
			}
			else if(checkPiece.isKing() && !checkPiece.isFire()){
				if(xi < xf && yf > yi){
					if (pieceAt(xi + 1, yi + 1)!= null && pieceAt(xi + 1, yi + 1).isFire())
						return true;
				}
				if(xi < xf && yf < yi){
					if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire())
						return true;
				}
				if(xi > xf && yf > yi){
					if (pieceAt(xi - 1, yi + 1)!= null && pieceAt(xi - 1, yi + 1).isFire())
						return true;
				}
				else{
					if (pieceAt(xi - 1, yi - 1)!= null && pieceAt(xi - 1, yi - 1).isFire())
						return true;
				}
			}
			else if (yf > yi && checkPiece.isFire()){
				if(xi < xf){
					if (pieceAt(xi + 1, yi + 1)!=null && !pieceAt(xi+1, yi+1).isFire())
						return true;
				}
				else{
					if (pieceAt(xi - 1, yi + 1)!=null && !pieceAt(xi-1, yi+1).isFire())
						return true;
				}
			}

			else if (yf < yi && !checkPiece.isFire()){
				if(xi < xf){
					if (pieceAt(xi + 1, yi - 1)!=null && pieceAt(xi+1, yi-1).isFire())
						return true;
				}
				else{
					if (pieceAt(xi - 1, yi - 1)!=null && pieceAt(xi-1, yi-1).isFire())
						return true;
				}
			}	
		}
		return false;
	}


	public boolean canSelect(int x, int y){

		if (pieceAt(x, y)!= null){
			Piece selected = pieceAt(x, y);
			if(selectedPiece != null && selectedPiece.hasCaptured()){
				return false;
			}
			if(!hasMoved) {
				if(selected.isFire() && currPlayer == 0){
					return true;
				}
				else if(!selected.isFire() && currPlayer == 1){
					return true;
				}
			}
			return false;
		}
		else{
			if(selectedPiece != null){
				return validMove(selectedX, selectedY, x, y);
			}
			return false;
		}
	}

	public void select(int x, int y){
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		highlight[i][j] = false;
        	}
        }
        highlight[x][y] = true;
		if (selectedPiece != null && pieceAt(x, y) == null){
			remove(selectedX, selectedY);
			selectedPiece.move(x, y);
			place(selectedPiece, x, y);
			if(Math.abs(selectedX - x) == 2 && Math.abs(selectedY - y) == 2){
				remove((selectedX + x) /2, (selectedY + y)/2);
				if(selectedPiece.isBomb()){
					if(pieceAt(x - 1, y + 1) != null && !pieceAt(x - 1, y + 1).isShield()){
						remove(x - 1, y + 1);
					}
					if(pieceAt(x + 1, y + 1) != null && !pieceAt(x + 1, y + 1).isShield()){
						remove(x + 1, y + 1);
					}
					if(pieceAt(x - 1, y - 1) != null && !pieceAt(x - 1, y - 1).isShield()){
						remove(x - 1, y - 1);
					}
					if(pieceAt(x + 1, y - 1) != null && !pieceAt(x + 1, y - 1).isShield()){
						remove(x + 1, y - 1);
					}
					highlight[x][y] = false;
					remove(x, y);
					hasMoved = true;
				}
			}
			else{
				hasMoved = true;
			}

		}
		selectedX = x;
		selectedY = y;
		selectedPiece = pieceAt(x, y);
	}
	
	public Piece remove(int x, int y){
		if(x > 8 || y > 8){
			System.out.println("Coordinates are out of bounds!");
			return null;
		}
		else if(pieces[x][y] == null){
			System.out.println("No piece at " + x + ", " + y);
			return null;
		}
		else{
			Piece output = pieces[x][y];
			pieces[x][y] = null;
			return output;
		}
	}

	public boolean canEndTurn(){
		if(selectedPiece == null && !hasMoved)
			return false;
		else if(selectedPiece == null && hasMoved)
			return true;
		else if(selectedPiece!=null){
			if (selectedPiece.hasCaptured() && !hasMoved)
				return true;
			else if(selectedPiece.hasCaptured() && hasMoved)
				return true;
			else if(!selectedPiece.hasCaptured() && hasMoved)
				return true;
		}
		return false;
	}

	public void endTurn(){
		currPlayer = 1 - currPlayer;
		if(selectedPiece != null)
			selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedX = -100;
		selectedY = -100;
		hasMoved = false;
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		highlight[i][j] = false;
        	}
        }
	}

	public String winner(){
		boolean noFire = true;
		boolean noWater = true;
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Piece currPiece = pieceAt(i, j);
            	if(currPiece!=null){
            		if (currPiece.isFire()){
            			noFire = false;
            			numFire++;
            		}
            		else{
            			noWater= false;
            			numWater++;
            		}
            	}
            }
        }
        if(noFire && noWater){
        	return "No one";
        }
        else if(noFire)
        	return "Water";
        else if (noWater)
        	return "Fire";
        else
        	return null;
	}

}