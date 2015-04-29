public class Board {
    
    private Piece[][] pieces;
    private static int STATIC_BOARD_SIZE = 8;
    private final int BOARD_SIZE = 8;
    private Piece selectedPiece;
    private int sPieceX, sPieceY;
    private boolean fireTurn, moved, captured;
    
    /**The constructor for Board. If shouldBeEmpty is true, 
     * initializes an empty Board. Otherwise, initializes a 
     * Board with the default configuration. Note that an 
     * empty Board could possibly be useful for testing purposes.
     */
    public Board(boolean shouldBeEmpty) {
        initializePieces(shouldBeEmpty); 

        selectedPiece = null;
        fireTurn = true;
        moved = false;
        captured = false;
        sPieceX = -1;
        sPieceY = -1;
    }
    
    private void initializePieces(boolean empty) {
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];

        if (!empty) {
            for (int i=0; i<BOARD_SIZE; i++) {
                for (int j=0; j<BOARD_SIZE; j++){
                    if ((i + j) % 2 == 0) {
                        switch (j) {
                            case 0: pieces[i][j] = new Piece(true, this, i, j, "pawn"); break;
                            case 1: pieces[i][j] = new Piece(true, this, i, j, "shield");break;
                            case 2: pieces[i][j] = new Piece(true, this, i, j, "bomb");break;
                            case 5: pieces[i][j] = new Piece(false, this, i, j, "bomb");break;
                            case 6: pieces[i][j] = new Piece(false, this, i, j, "shield");break;
                            case 7: pieces[i][j] = new Piece(false, this, i, j, "pawn");break;
                            default:pieces[i][j] = null;
                        }
                    }
                }
            }
        }
    }
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == sPieceX && j == sPieceY)
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                if (pieces[i][j] != null) {
                    Piece p = pieces[i][j];
                    String image = "";
                    
                    if (p.isBomb())
                        image += "bomb";
                    else if (p.isShield())
                        image += "shield";
                    else
                        image += "pawn";
                    if (p.isFire())
                        image += "-fire";
                    else
                        image += "-water";
                    if (p.isKing())
                        image += "-crowned";  
                    
                    StdDrawPlus.picture(i + .5, j + .5, "img/"+image+".png", 1, 1);
                }
            }
        }
    }
    
    /**Gets the piece at position (x, y) on the board, or 
     * null if there is no piece. If (x, y) are out of bounds, 
     * returns null.
     */
    public Piece pieceAt(int x, int y) {
        if (x >= BOARD_SIZE || y >= BOARD_SIZE || y < 0 || x < 0 || pieces[x][y] == null) {
            return null;
        }
        return pieces[x][y];
    }
    
    /**Returns true if there is a piece the piece at (x, y) 
     * and it can be selected.
     * See additional documentation.
     */
    public boolean canSelect(int x, int y) {
        if ((pieceAt(x, y)!= null && pieceAt(x, y).isFire() == fireTurn) && (selectedPiece == null || !moved))
            return true;
        else if (pieceAt(x, y) == null) 
            if (selectedPiece != null && (!moved || (captured && Math.abs(sPieceX - x) == 2)) && validMove(sPieceX, sPieceY, x, y))
                return true;
        return false;
        
    }
    /**Returns true if the piece at (xi, yi) can either 
     * move to (xf, yf) or capture to (xf, yf) in a valid 
     * fashion compatible with the rules.
     */
    private boolean validMove(int xi, int yi, int xf, int yf) {
       Piece curr = pieceAt(xi, yi);
       Piece dest = pieceAt(xf, yf);
       //trying to move nothing
       if (curr == null)
           return false;
       //clicked on an empty space
       else if (dest == null){
           int xDiff = xf - xi;
           int yDiff = yf - yi;
           //moving one space
           if (Math.abs(xDiff) == 1) {
               if (yDiff == 1 && curr.isFire() || yDiff == -1 && !curr.isFire() || curr.isKing())
                   return true;
           }
           //trying to jump
           else if (Math.abs(xDiff) == 2 ) {
              if ((yDiff == 2 && curr.isFire()) || (yDiff == -2 && !curr.isFire()) ||curr.isKing())
            	  if (pieceAt(xi+xDiff/2, yi+yDiff/2)!= null && pieceAt(xi+xDiff/2, yi+yDiff/2).isFire() != curr.isFire())
                      return true;
           }
       }
       //if there is a piece at xf, yf
       return false;
    }
    
    /**Selects the piece at (x, y) if possible. Optionally, 
     * it is recommended to color the background of the 
     * selected square white on the GUI via the pen color 
     * function. For any piece to perform a capture, that piece 
     * must have been selected first.
     */
    public void select(int x, int y){
    	if (pieceAt(x,y) != null) {
    		selectedPiece = pieceAt(x, y);
    	}
    	else {
    		selectedPiece.move(x, y);
    		moved = true;
    		captured = selectedPiece.hasCaptured();
    	}
    	sPieceX = x;
    	sPieceY = y;
    	
    	/*if (pieceAt(x, y) == null && selectedPiece!=null) {
    		selectedPiece.move(x, y);
    		moved = true;
    		captured = selectedPiece.hasCaptured();
    	}
    	else if (fireTurn == pieceAt(x, y).isFire()) {
            selectedPiece = pieceAt(x, y);
            //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            //StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        }
        sPieceX = x;
        sPieceY = y;*/
    }
    
    /**Places p at (x, y). If (x, y) is out of bounds or if p is 
     * null, does nothing. If p already exists in the current Board, 
     * first removes it from its initial position. If another piece 
     * already exists at (x, y), p will replace that piece. (This 
     * method is potentially useful for creating specific test 
     * circumstances.)*/
    public void place(Piece p, int x, int y) {
        if (p == null || x > BOARD_SIZE || y > BOARD_SIZE || y < 0 || x < 0) {
            return;
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
        	for (int j = 0; j < BOARD_SIZE; j++) {
        		if (pieces[i][j] == p) 
        			remove(i, j);
        	}
        }
        pieces[x][y] = p;
    }
    
    /**Executes a remove. Returns the piece that was removed. If 
     * the input (x, y) is out of bounds, returns null and prints 
     * an appropriate message. If there is no piece at (x, y), 
     * returns null and prints an appropriate message.*/ 
    public Piece remove(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p == null){
            System.out.println("There isn't a piece there to remove");
            return null;
        }     
        pieces[x][y] = null;
        return p;
    }
    
    /**Returns whether or not the the current player can end their 
     * turn. To be able to end a turn, a piece must have moved 
     * or performed a capture.*/
    public boolean canEndTurn() {
        if (moved)
            return true;
        return false;
    }
    
    /**Called at the end of each turn. Handles switching of players 
     * and anything else that should happen at the end of a turn.*/ 
    public void endTurn() {
        fireTurn = !fireTurn;
        moved = false;
        selectedPiece.doneCapturing();
        selectedPiece = null;
        sPieceX = -1;
        sPieceY = -1;
    }
    /**Returns the winner of the game. "Fire", "Water", "No one" 
     * (tie / no pieces on the board), or null if the game is not yet 
     * over. Assume there is no stalemate situation in which the current 
     * player has pieces but cannot legally move any of them (In this 
     * event, just leave it at null). Determine the winner solely by the 
     * number of pieces belonging to each team.*/
    public String winner() {
    	boolean hasFire = false;
    	boolean hasWater = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
        	for (int j = 0; j < BOARD_SIZE; j++) {
        		if (pieceAt(i, j) == null){
        			continue;
        		}
        		else if (pieceAt(i, j).isFire()) 
        			hasFire = true;
        		else
        			hasWater = true;
        	}
        }
        //hello
        if (hasWater && !hasFire){
        	return "Water";
        }
        else if (!hasWater && hasFire){
        	return "Fire";
        }
        else if (!hasWater && !hasFire){
        	return "No one";
        }
    	return null;
    }
    
    public static void main(String[] args) {
        
        StdDrawPlus.setXscale(0, STATIC_BOARD_SIZE);
        StdDrawPlus.setYscale(0, STATIC_BOARD_SIZE);
    	Board b = new Board(false);
    	String gameStatus = null;
        while(gameStatus == null || !StdDrawPlus.isSpacePressed()) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xCoord = (int) x;
                int yCoord = (int) y;
                if (b.canSelect(xCoord, yCoord)) {
                    b.select(xCoord, yCoord);      
                }
            }
            else if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }
            gameStatus = b.winner();
            StdDrawPlus.show(100);
        }
        b.drawBoard(8);
        System.out.println(gameStatus);
    }
}