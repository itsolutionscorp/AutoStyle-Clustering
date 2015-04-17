import java.lang.Math;

public class Board {
	
    private static Piece[][] pieces;
    private static int N = 8;
    
    private boolean isHighlighted = false;
    private int highlightedX, highlightedY = -1;
    
    private boolean moveMade = false;
    
    private int player = 0;
    
    
    
    public Board (boolean shouldBeEmpty) {
    	pieces = new Piece[N][N];
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                

            }
        }
        if (!shouldBeEmpty) {
        	setInitialPieces();
        }
    }
    
    private void setInitialPieces() {
        
    	for (int i = 0; i < N; i++) {
    		if (i % 2 == 0) {
    			pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                StdDrawPlus.picture(i + .5, 0 + .5, "img/pawn-fire.png", 1, 1);
                
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                StdDrawPlus.picture(i + .5, 2 + .5, "img/bomb-fire.png", 1, 1);
                
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
                StdDrawPlus.picture(i + .5, 6 + .5, "img/shield-water.png", 1, 1); 

    		}
    	}
    	for (int i = 0; i < N; i++) {
    		if (i % 2 == 1) {
    			pieces[i][1] = new Piece(true, this, i, 1, "shield");
                StdDrawPlus.picture(i + .5, 1 + .5, "img/shield-fire.png", 1, 1);
                
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
                StdDrawPlus.picture(i + .5, 5 + .5, "img/bomb-water.png", 1, 1); 

                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
                StdDrawPlus.picture(i + .5, 7 + .5, "img/pawn-water.png", 1, 1); 

    		}
    	}
    }
    
    public Piece pieceAt(int x, int y) {
    	return pieces[x][y];
    }
    
    public boolean canSelect(int x, int y) {
    	if (pieces[x][y] != null)
	    	if (player != pieces[x][y].side())
	    		return false;
    	if (x < 0 || x >= N)
    		return false;
    	if (y < 0 || y >= N)
    		return false;
    	if (highlightedX == -10 || highlightedY == -10)
    		return false;
    	//No Piece, checking if can move
    	if (pieces[x][y] == null) {
    		if (moveMade) {
    			if (pieces[highlightedX][highlightedY].hasCaptured())
    				if (validMove(highlightedX, highlightedY, x, y) == 2)
    					return true;
    			return false;
    		}
    		if (isHighlighted){
    			if (validMove(highlightedX, highlightedY, x, y) != -1)
    				return true;
    			return false;
    		}
    		return false;
    	} else {
    		if (moveMade)
    			return false;
    		return true;
    	}
    }
    private int validMove(int x1, int y1, int x2, int y2) {
    	int distX = Math.abs(x1 - x2);
    	int distY = Math.abs(y1 - y2);
    	if (distX == 1 && distY == 1)
    		return 1;
    	if (distX == 2 && distY == 2)
    		return 2;
    	return -1;
    }
    public void select(int x, int y) {

    	//I can select a piece
    	if (canSelect(x, y)) {
    		//Moving pieces all around
    		if (pieces[x][y] == null) {
        		int dist = validMove(highlightedX, highlightedY, x, y);
        		
        		//Jumped over a piece
        		if (dist == 2) {
        			Piece suspect = pieces[highlightedX][highlightedY];
        			suspect.hasCaptured();
        			int removedX = getMiddleCoord(highlightedX, x);
        			int removedY = getMiddleCoord(highlightedY, y);
        			//Are you a bomb?
        			this.remove(removedX, removedY);
        			if (suspect.isBomb()) {
        				//BOOM
        				explode(x, y);
        				this.remove(highlightedX,  highlightedY);
        				highlightedX = -10;
        				highlightedY = -10;
        				return;
        			}
        			
        		}
        		
        		pieces[highlightedX][highlightedY].move(x, y);
        		moveMade = true;
        		highlight(x, y);
        		

        	//I'm just changing highlighting of piece
    		} else {
		    	highlight(x, y);
    		}
    	}
    }
    private int getMiddleCoord(int a, int b) {
    	if (a < b)
    		return a + 1;
    	return b + 1;
    }
    //Will highlight a square and update the highlighted coordinates
    private void highlight(int x, int y) {
    	removeHighlight(highlightedX, highlightedY);
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        pieces[x][y].move(x, y);
        highlightedX = x;
        highlightedY = y;
        isHighlighted = true;
    }
    private void removeHighlight(int x, int y) {
    	if (highlightedX == -1 || highlightedY == -1)
    		return;
    	if (highlightedX == -10 || highlightedY == -10)
    		return;

    	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    	StdDrawPlus.filledSquare(highlightedX + .5,
    			highlightedY + .5, .5);
    	if (pieces[x][y] != null)
    		pieces[highlightedX][highlightedY].
					move(highlightedX, highlightedY);
    }
    
    public void place(Piece p, int x, int y) {
    	if (x < 0 || x >= N)
    		return;
    	if (y < 0 || y >= N)
    		return;
    	if (p == null)
    		return;
        pieces[x][y] = p;

    }
    
    public Piece remove(int x, int y) {
    	if (x < 0 || x >= N)
    		return null;
    	if (y < 0 || y >= N)
    		return null;
    	
        if ((x + y) % 2 == 0)
        	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else
        	StdDrawPlus.setPenColor(StdDrawPlus.RED);
        
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	
        Piece temp = pieces[x][y];
        pieces[x][y] = null;
        
    	return temp;
    }
    
    private void explode(int x, int y) {
    	for (int i = x - 1; i <= x + 1; i++) {
    		if (i == -1 || i == 8)
    			continue;
        	for (int j = y - 1; j <= y + 1; j++) {
        		if (j == -1 || j == 8)
        			continue;
        		if (pieces[i][j] != null)
        			if (!pieces[i][j].isShield()) {
        				this.remove(i, j);
        			}
        	}
    	}
		moveMade = true;

    }
    
    public boolean canEndTurn() {
    	return moveMade;
    }
    
    public void endTurn() {
    	player++;
    	player = player % 2;
    	removeHighlight(highlightedX, highlightedY);
    	if (highlightedX != -10 && highlightedX != -1 && 
    			highlightedY != -10 && highlightedY != -1 )
    		pieces[highlightedX][highlightedY].doneCapturing();
    	highlightedX = -1;
    	highlightedY = -1;
    	isHighlighted = false;
    	moveMade = false;
    }
    
    public String winner() {
    	int numWater = 0;
    	int numFire = 0;
    	for (Piece[] row: pieces) {
    		for (Piece elem: row) {
    			if (elem == null)
    				continue;
    			if (elem.isFire())
    				numFire++;
    			else
    				numWater++;
    		}
    	}
    	if (numWater == 0 && numFire == 0)
    		return "No one";
    	if (numWater == 0)
    		return "Fire";
    	if (numFire == 0)
    		return "Water";
    	return null;
    }
    
    public static void main (String args[]) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
//    	Board b = new Board(false);
        Board b = new Board(true);
        b.place(new Piece(true, b, 2, 2, "bomb"), 2, 2);
        b.place(new Piece(false, b, 3, 3, "bomb"), 3, 3);
        StdDrawPlus.picture(2 + .5, 2 + .5, "img/bomb-fire.png", 1, 1);
        StdDrawPlus.picture(3 + .5, 3 + .5, "img/bomb-water.png", 1, 1);
        b.endTurn();
    	
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.select((int) x, (int) y);
            }
            if (StdDrawPlus.isKeyPressed(32))
            	if (b.canEndTurn())
            		b.endTurn();
            	if (b.winner() != null) {
            		System.out.println("Congratulations " + b.winner());
            		break;
            	}
            StdDrawPlus.show(100);
        }
    }
}