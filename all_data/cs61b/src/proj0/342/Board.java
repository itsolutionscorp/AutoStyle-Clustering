public class Board {

	 /** Location of pieces. */
    private Piece[][] pieces;
    private static final int SIZE = 8;
    private int currentPlayer;
    private int currentPlaceX;
    private int currentPlaceY;
    private Piece currentPiece;
    private boolean pieceMoved;

    public Board(boolean isEmptyBoard) {
    	pieces = new Piece[SIZE][SIZE];
    	if (!isEmptyBoard)
    		addPieces();
    	currentPlayer = 0;
    	currentPlaceX = -1;
    	currentPlaceY = -1;
    }
    
    private void addPieces() {
    	boolean isFire = true;
    	for (int x = 0; x < SIZE; x++) {
    		isFire = true;
    		for (int y = 0; y < SIZE; y++) {
    			if (y == SIZE / 2)
        			isFire = false;
    			if ((x + y) % 2 == 0) {
                	if (y == 0 || y == SIZE - 1)
                		pieces[x][y] = new Piece(isFire, this, x, y, "pawn");
                	else if (y == 1 || y == SIZE - 2)
                		pieces[x][y] = new Piece(isFire, this, x, y, "shield");
                	else if (y == 2 || y == SIZE - 3)
                		pieces[x][y] = new Piece(isFire, this, x, y, "bomb");
                	else;	
    			}
    			
    		}
    		
    	}
    }
    
    private void drawBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == currentPlaceX && j == currentPlaceY)
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
               	Piece p = pieces[i][j];
               	String type = "";
               	if (p != null) {
               		if (p.isBomb())
               			type = "bomb";
               		else if (p.isShield())
               			type = "shield";
               		else
               			type = "pawn";
               		if (p.isFire())
               			type += "-fire";
               		else
               			type += "-water";
               		if (p.isKing())
               			type += "-crowned";
               		StdDrawPlus.picture(i + .5, j + .5, "img/" + type + ".png", 1, 1);      
               	}             
              }               
            }
    }

    public Piece pieceAt(int x, int y) {
    	if (!validPosition(x, y))
    		return null;
    	return pieces[x][y];
    }
    
    public void place(Piece p, int x, int y) {
    	if (!validPosition(x, y) || p == null)
    		return;
    	for(int c = 0; c < SIZE; c++) {
    		for(int r = 0; r < SIZE; r++) {
    			if (p == pieces[c][r])
    				pieces[c][r] = null;
    		}
    	}
    	pieces[x][y] = p;		
    }
    
    public Piece remove(int x, int y) {
    	if (x >= SIZE || y >= SIZE) {
    		System.out.println("(x,y) is out of bounds");
    		return null;
    	}
    	Piece p = pieces[x][y];
    	if (p == null) {
    		System.out.println("No piece is in this location");
    		return null;
    	}
    	pieces[x][y] = null;
    	return p;  	
    }
    
    public void select(int x, int y) {	
    	if (pieces[x][y] != null) {
    		currentPiece = pieces[x][y];
    	}
    	else {
    		currentPiece.move(x, y);
    		pieces[x][y] = currentPiece;
    		pieces[currentPlaceX][currentPlaceY] = null;
    		pieceMoved = true;
    		if (currentPiece.hasCaptured() && currentPiece.isBomb())
    			remove(x, y);
    	}
    	currentPlaceX = x;
    	currentPlaceY = y;	
    }
    
    public boolean canSelect(int x, int y) {
    	Piece p = pieces[x][y];
    	
    	if (p != null) {
    		if (p.side() != currentPlayer)
    			return false;
    		if (currentPiece == null || !pieceMoved)
    			return true;
    	}
    	else {
    		if (!validPosition(currentPlaceX, currentPlaceY) || !validPosition(x, y))
    			return false;
    		if (validMove(currentPlaceX, currentPlaceY, x, y)) {
    			if (!pieceMoved)
    				return true;
    			else if (Math.abs(y - currentPlaceY) == 2)
    				if (currentPiece.isBomb() && !currentPiece.isKing())
    					return false;
    				else
    					return true;
    			else
    				return false;
    		}			
    	}
    	return false;
    }
    
    private boolean validPosition(int x, int y) {
    	if (x >= SIZE || y >= SIZE || x < 0 || y < 0)
    		return false;
    	return true;
    }
    
    public boolean canEndTurn() {
//    	if (currentPiece == null)
//    		return true;
    	//System.out.println(currentPiece.x + " " + currentPiece.y);
    	return pieceMoved;
    }
    
    public void endTurn() {
    	currentPlayer = Math.abs(currentPlayer - 1);
    	System.out.println("next player: " + currentPlayer);
    	currentPlaceX = -1;
    	currentPlaceY = -1;
    	currentPiece.doneCapturing();
    	currentPiece = null;
    	pieceMoved = false;
    }
    
    private boolean validMove(int x1, int y1, int x2, int y2) {
    	Piece p = pieces[x1][y1];
    	if (pieces[x2][y2] != null)
    		return false;
    	if (p == null)
    		return false;
    	if (Math.abs(x2 - x1) != 1 && Math.abs(x2 - x1) != 2)
    		return false;
    	if (!p.isKing() && currentPlayer == 0 && y2 <= y1)
    		return false;   
    	if (!p.isKing() && currentPlayer == 1 && y2 >= y1)
			return false;  
    	if (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 1) 
    		return true;
    	if (Math.abs(x2 - x1) == 2 && Math.abs(y2 - y1) == 2) {
    		Piece p2 = pieces[(x1 + x2) / 2][(y1 + y2) / 2];
    		if (p2 != null && p2.side() != currentPlayer) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public String winner() {
    	int fire = 0;
    	int water = 0;
    	Piece p = pieces[0][0];
    	
    	for (int x = 0; x < SIZE; x++) {
    		for (int y = 0; y < SIZE; y++) {
    			p = pieces[x][y];
    			if (p != null ) {
    				if (p.isFire())
    					fire++;
    				else
    					water++;
    			}
    		}	
    	}
    	
    	if (fire == 0 && water == 0)
    		return "No one";
    	else if (water == 0)
    		return "Fire";
    	else if (fire == 0)
    		return "Water";
    	else
    		return null;
    				
    }
    
    
    public static void main(String[] args) {
        Board b = new Board(false);
//		Piece p1 = new Piece(false, b, SIZE - 3, SIZE - 3, "pawn");
//		Piece p2 = new Piece(false, b, SIZE - 5, SIZE - 3, "pawn");
//		Piece p3 = new Piece(true, b, 7, 5, "pawn");
//		Piece p4 = new Piece(true, b, 1, 1, "pawn");
//        b.place(p1, SIZE - 2, SIZE - 2);
//        b.place(p2, SIZE - 4, SIZE - 2);
//        b.place(p3, SIZE - 1, SIZE - 3);
//        b.place(p4, 1, 1);
        StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);
    	b.drawBoard();
    	int x; int y; 
    	
    	 while(b.winner() == null) {
    		 b.drawBoard();
             if (StdDrawPlus.mousePressed()) {
         		x = (int) StdDrawPlus.mouseX();
         		y = (int) StdDrawPlus.mouseY();
         		//System.out.println("Select: " + x + " " + y);
         		if (b.canSelect(x, y))
         			b.select(x, y);
             }
             if (StdDrawPlus.isSpacePressed()) {
            	 if(b.canEndTurn())
            		 b.endTurn();
             }
             StdDrawPlus.show(100);
         }
    	 b.drawBoard();
    	 StdDrawPlus.show(100);
    	 System.out.println(b.winner());

    }
}