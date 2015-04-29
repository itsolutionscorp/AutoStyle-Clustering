public class Board {
	private Piece[][] pieces;
	private Board board;
	private int x_val;
	private int y_val;
	private int x_val_curr;
	private int y_val_curr;
	private int x_val_prev;
	private int y_val_prev;
	private int player = 1;  
	private boolean moved;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	private Piece selectedpiece;
	private boolean selected;
	private Piece old_piece;
	private int selectedpiece_x;
	private int selectedpiece_y;
	private int cap_x;
	private int cap_y;
	private int sum_fire;
	private int sum_water;
    private int side;
    private boolean empty=true;
	public static void main (String[] args)
	{
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board board = new Board(false);
		board.moved = false;
		board.selectedpiece = null;
		while(board.winner() == null) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                 board.x_val = (int)StdDrawPlus.mouseX();
                 board.y_val = (int)StdDrawPlus.mouseY();
                 if (board.canSelect(board.x_val, board.y_val))
                 	board.select(board.x_val, board.y_val);
                 board.updateBoard();
                 board.x_val_prev = board.x_val;
                 board.y_val_prev = board.y_val; 
                 board.empty = false;
                }            
            if (StdDrawPlus.isSpacePressed())
                 	{if (board.canEndTurn())
                 		{
                 		board.endTurn();}}
            StdDrawPlus.show(100); 
        	
        	}
	}
	public Board (boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		if (shouldBeEmpty == false) {
		    for (int i = 0; i <  8; i++) {
            	for (int j = 0; j < 8; j++) {
        				if (j == 0 && (i % 2 == 0))
        					pieces[i][j] = new Piece(true, this, i, j, "pawn");
        				if (j == 1 && (i % 2 != 0))
        					pieces[i][j] = new Piece(true, this, i, j, "shield");
        				if (j == 2 && (i % 2 == 0))
        					pieces[i][j] = new Piece(true, this, i, j, "bomb");
        				if (j == 5 && (i % 2 != 0))
        					pieces[i][j] = new Piece(false, this, i, j, "bomb");
        				if (j == 6 && (i % 2 == 0))
        					pieces[i][j] = new Piece(false, this, i, j, "shield");
        				if (j == 7 && (i % 2 != 0))
        					pieces[i][j] = new Piece(false, this, i, j, "pawn"); 
            }}}}

		

	
	private void drawBoard(int N) {
        for (int i = 0; i <  N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((i + j) % 2 == 0) 
                		StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
                	else                  
                		StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			if (pieces[i][j] != null){
        				if (pieces[i][j].isFire() && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false && pieces[i][j].isKing() == false)
        					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        				if (pieces[i][j].isFire() && pieces[i][j].isShield())
        					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
        				if (pieces[i][j].isFire() && pieces[i][j].isBomb())
        					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
        				if ((pieces[i][j].isFire() == false) && pieces[i][j].isBomb())
        					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
        				if ((pieces[i][j].isFire() == false) && pieces[i][j].isShield())
        					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
        				if (pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false )
        					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
        				if (pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isBomb())
        					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
        				if (pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isShield())
        					StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
        				if (!pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false )
        					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
        				if (!pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isShield())
        					StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
        				if (!pieces[i][j].isFire() && pieces[i][j].isKing() && pieces[i][j].isBomb())
        					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
        				if ((pieces[i][j].isFire() == false) && pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false && pieces[i][j].isKing() == false)
        					StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);         } }
            }}
    public Piece pieceAt(int x, int y){
    	if (x < 0 || y < 0 || x > 7 || y > 7)
    		return null;
    	return pieces[x][y];
    }

    public boolean canSelect(int x, int y)
    {		
   		if ((pieceAt(x,y) != null) && pieceAt(x,y).side() == side && moved == false)
    			{
                return true;}
    		if ((selectedpiece != null) && pieceAt(x,y) != null && moved == false && selectedpiece.side() == side && !selectedpiece.hasCaptured()) {
    					return true;}
    		
    		if ((pieceAt(x,y) == null)  && selectedpiece != null && selectedpiece.hasCaptured() == false 
                && validMove(selectedpiece_x, selectedpiece_y, x, y)
    			&& moved == false && selectedpiece.side() == side)
    					 {

    				return true;}
    		if ((pieceAt(x,y) == null) && selectedpiece != null &&
                validCapture(x_val_prev, y_val_prev, x, y) 
    			&& moved == true && selectedpiece.hasCaptured() && selectedpiece.isBomb() == false )
    			{return true;}
    		return false;
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (selectedpiece != null && selectedpiece.isKing())
		{
			return true;
		}
		if (selectedpiece != null && (yf >= yi) && selectedpiece.isFire())
		{
            return true;
		}
		if (selectedpiece != null && (yf <= yi) && selectedpiece.isFire() == false)
		{
            return true;
		}
		return false;
	}
    private boolean validCapture(int xi, int yi, int xf, int yf){
        cap_x = (xf + xi)/2;
        cap_y = (yf + yi)/2;
        if (Math.abs(xf - xi) >= 2 && Math.abs(yf - yi) >= 2  
            && selectedpiece != null && selectedpiece.isKing() && selectedpiece.hasCaptured()
            )
            return true;
        if (Math.abs(xf - xi) >= 2 && Math.abs(yf - yi) >= 2 
            && selectedpiece != null && (yf > yi) && selectedpiece.isFire() && selectedpiece.hasCaptured()
            )
            return true;
        if (Math.abs(xf - xi) >= 2 && Math.abs(yf - yi) >= 2  
            && selectedpiece != null && (yf < yi) && !selectedpiece.isFire() && selectedpiece.hasCaptured()
           )
            return true;

        return false;
    }
    public void select(int x, int y){
        if (pieceAt(x,y) != null ) {
    		selectedpiece = pieceAt(x,y);
    		selectedpiece_x = x;
    		selectedpiece_y = y;
            selected = true;

    		
    		}
    	if (pieceAt(x,y) == null ) {
    		selectedpiece.move(x,y);	
    		moved = true;
    		
    	}

    }
	
	public void place(Piece p, int x, int y)
	{
		if ( p != null && (x >= 0 && x <= 7) &&  (y >= 0 && y <= 7))
		{	
			for (int i = 0; i <  8; i++) {
            	for (int j = 0; j < 8; j++) {
					if (p == pieces[i][j]) {
						remove(i,j);}}}
			
			pieces[x][y] = p;
				
			}
		}
	
	public Piece remove(int x, int y){
		if (x < 0 || y < 0 || x > 7 || y > 7)
    		{System.out.println("parameters out of bounds");
    		return null;}
		if (pieceAt(x,y) == null)
			{System.out.println("No piece to remove");
			return null;}
		Piece p = pieceAt(x,y);
		pieces[x][y] = null;
		return p;
	}

	
	private void updateBoard(){
		drawBoard(8);
	}

	public boolean canEndTurn(){
		if (moved == true)
			return true;
		return false;
	}
	public void endTurn(){
		player *= -1;
		moved = false;
        selectedpiece.doneCapturing();
		if (side == 0)
            {side = 1;
            return;}
        if (side == 1)
            side = 0;
		
	}
	public String winner(){
		sum_fire = 0;
		sum_water = 0;
		for (int i = 0; i <  8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (pieces[i][j] != null && pieces[i][j].isFire())
            			sum_fire += 1;
            		if (pieces[i][j] != null && pieces[i][j].isFire() == false)
            			sum_water += 1;}}
		if (sum_water == 0 && sum_fire != 0)
			return "Fire";
		if (sum_fire == 0 && sum_water != 0)
            {return "Water";}
	    if (sum_fire == 0 && sum_water == 0)
	    	return "No one";
	    return null;
	}
}