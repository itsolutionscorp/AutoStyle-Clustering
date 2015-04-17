public class Board {
    private Piece[][] board;
    private int currTurn = 0; //0 = fire, 1 = water  
    private boolean hasMoved = false;
    private int currX;
    private int currY;
    private Piece selectedPiece;

    public static void main (String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(b.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y); 
            	}           
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn())
               		b.endTurn();
            }

            b.drawBoard();
        	StdDrawPlus.show(50);

            }
        }

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (board[i][j] != null) {
            		Piece p = pieceAt(i, j);
            		if (p.isFire()) {
                		if (p.isBomb() && p.isKing())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
                		else if (p.isShield() && p.isKing())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
                		else if (p.isKing())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
                		else if (p.isBomb())
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
				else if (p.isShield())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
                		else
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
            		}
                	if (!p.isFire()) {
                		if (p.isBomb() && p.isKing())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
                		else if (p.isShield() && p.isKing())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
                		else if (p.isKing())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
                		else if (p.isBomb())
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
				else if (p.isShield())
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                		else
                			StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
            		}
        	}
    	    }
    	}
    }

    public Board(boolean shouldBeEmpty) {
        board = new Piece[8][8];
        if (shouldBeEmpty == false) { 
            for (int i = 0; i < 8; i+=2) {
                new Piece(true, this, i, 0, "pawn");
            }

            for (int i = 1; i < 8; i+=2) {
                new Piece(true, this, i, 1, "shield");
            }

            for (int i = 0; i < 8; i+=2) {
                new Piece(true, this, i, 2, "bomb");
            }
            
            for (int i = 1; i < 8; i+=2) {
                new Piece(false, this, i, 7, "pawn");
            }

            for (int i = 0; i < 8; i+=2) {
                new Piece(false, this, i, 6, "shield");
            }

            for (int i = 1; i < 8; i+=2) {
                new Piece(false, this, i, 5, "bomb");
            }
        }
    }


    public Piece pieceAt(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        else if (x >= 0 || x <= 7 || y >= 0 || y <= 7) {
            return board[x][y];
        }
        else {
            return null;
        }
    }

    
    public boolean canSelect(int x, int y) {
    	Piece p = pieceAt(x, y);
        if (p != null){       	
            if (currTurn == p.side() && hasMoved == false)
               return true;
        }

        else {
            if ((selectedPiece != null) && (hasMoved == true))  {
            	if (selectedPiece.hasCaptured() && Math.abs(currX - x) == 2 && (validMove(currX, currY, x, y)))
                	return true;
            }
            else if ((selectedPiece != null) && hasMoved == false) {
            	if (validMove(currX, currY, x, y))
            		return true;
            }
        }     
        return false;
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
    	int xDiff = Math.abs(xf-xi);
    	int yDiff = Math.abs(yf-yi);
    	int xPos = (xi + xf)/2;
    	int yPos = (yi + yf)/2;

    	if (xi < 0 || xi > 7 || yi < 0 || yi > 7 || xf < 0 || xf > 7 || yf < 0 || yf > 7)
    		return false;

    	if (xDiff == 2 && yDiff == 2) {
        	Piece p = pieceAt(xi, yi);
        	if (p.isFire() && !p.isKing()) {
        		return (yf > yi);
        	}
        	else if (!p.isFire() && !p.isKing())
        		return (yf < yi);
        	Piece p1 = pieceAt(xPos, yPos);
        	if (p1 != null && p1.side() != p.side())
        		return true;
        }

        else if (xDiff == 1 && yDiff == 1) {
        	Piece p = pieceAt(xi, yi);
            if (p.isFire() && !p.isKing()){
                return (yf > yi);
            }
            if (!p.isFire() && !p.isKing()) {
                return (yf < yi);
            }
        return (pieceAt(xf, yf) == null);
        }
        return false;
    }

    public void select(int x, int y) {
    	if (selectedPiece != null && pieceAt(x, y) == null) {
    		selectedPiece.move(x, y);
    		hasMoved = true;
    	}
    	else {
    		selectedPiece = pieceAt(x, y);
    	}
    	currX = x;
    	currY = y;
    }
    
    public void place(Piece p, int x, int y) {
		if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8)) {
            for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (board[i][j] == p)
            			board[i][j] = null;
            	}
            }
         board[x][y] = p;
    	}
    }
    
    public Piece remove(int x, int y) {
        if (board[x][y] == null) {
            System.out.println("There is no piece there!");
            return null;
        }
        else if (x < 0 || x > 7 || y < 0 || y > 7){
            System.out.println("Out of bounds!");
            return null;
        }
        else {
        	Piece p = board[x][y];
        	board[x][y] = null;
            return p;
        }
    }
    
    public boolean canEndTurn() {
        return hasMoved;
    }
    
    public void endTurn() {
    	 if (currTurn == 0)
            currTurn = 1;
        else
            currTurn = 0;
        
    	if (selectedPiece != null)
        	selectedPiece.doneCapturing();
        hasMoved = false;
        selectedPiece = null;
        currX = -1;
        currY = -1;
    }
    
    public String winner() {
        int water = 0;
        int fire = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                	if (board[i][j].isFire()){
                    	fire += 1;
                	}
                	else if (!board[i][j].isFire()){
                    	water += 1;
                	}
            	}
        	}
        }
        if (fire > 0 && water > 0){
            return null;
        }
        if (fire == 0) {
            return "Fire";
        }
        if (water == 0) {
            return "Water";
        }
        if (fire == 0 && water == 0){
            return "No one";
        }
        return null;   
    }
}
            





