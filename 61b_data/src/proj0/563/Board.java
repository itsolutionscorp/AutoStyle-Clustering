public class Board{
	// need to be public when testing;
	private boolean shouldBeEmpty;
	private Piece[][] pieces = new Piece[8][8];
	private boolean fireTurn = true;
	

	private Piece selected = null;
	private int selectedX = 0;
	private int selectedY = 0;
	private boolean selectedMoved = false;

	private void drawBoard(int N){
		if (shouldBeEmpty)
		{
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                if ((i + j) % 2 != 0) StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            }
	        }
    	}

    	else
    	{
    		for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                if ((i + j) % 2 != 0) StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                
	                if (j==0 && !(i%2!=0))
	                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                if (j==1 && i%2!=0)
	                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                if (j==2 && !(i%2!=0))
	                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                if (j==5 && i%2!=0)
	                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                if (j==6 && !(i%2!=0))
	                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                if (j==7 && i%2!=0)
	                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                
	            }
	        }
		}
	}



	public Board(boolean myShouldBeEmpty) {
		int N = 8;
		shouldBeEmpty = myShouldBeEmpty;
		if (!shouldBeEmpty)
		{
			for (int i = 0; i < N; i++) {
		            for (int j = 0; j < N; j++) {
		                
		                if (j==0 && !(i%2!=0)){
		                	pieces[i][j] = new Piece(true, this, i, j, "pawn");}
		                if (j==1 && i%2!=0){
		                	pieces[i][j] = new Piece(true, this, i, j, "shield");}
		                if (j==2 && !(i%2!=0)){
		                	pieces[i][j] = new Piece(true, this, i, j, "bomb");}
		                if (j==5 && i%2!=0){
		                	pieces[i][j] = new Piece(false, this, i, j, "bomb");}
		                if (j==6 && !(i%2!=0)){
		                	pieces[i][j] = new Piece(false, this, i, j, "shield");}
		                if (j==7 && i%2!=0){
		                	pieces[i][j] = new Piece(false, this, i, j, "pawn");}
		                
		            }
		       	}
	    }
    	
	}

	public Piece pieceAt(int x, int y){
		if (x<0||x>7||y<0||y>7)
			return null;
		return pieces[x][y];
	}

	
	private boolean validDest(int x, int y){
		if (x<0||x>7||y<0||y>7)
			return false;
		// fire, not king
		if ((selected.isFire()) && (!selected.isKing()))
		{
			if (((y-selectedY==1) && (x-selectedX==1 || x-selectedX==-1)) ||
				(((y-selectedY==2) && (x-selectedX==2 || x-selectedX==-2)) 
					&& (pieces[(selectedX+x)/2][(selectedY+y)/2] != null) 
					&& (pieces[(selectedX+x)/2][(selectedY+y)/2].isFire() != fireTurn)))
				return true;
			return false;
		}
		// water, not king
		if ((!selected.isFire()) && (!selected.isKing()))
		{
			if (((y-selectedY==-1) && (x-selectedX==1 || x-selectedX==-1)) ||
				(((y-selectedY==-2) && (x-selectedX==2 || x-selectedX==-2))
					&& (pieces[(selectedX+x)/2][(selectedY+y)/2] != null)
					&& (pieces[(selectedX+x)/2][(selectedY+y)/2].isFire() != fireTurn)))
				return true;
			return false;
		}
		// KING !!!
		else 
		{
			if ((((x-selectedX==1) && (y-selectedY==1 || y-selectedY==-1)) ||
				((x-selectedX==-1) && (y-selectedY==1 || y-selectedY==-1)))    ||
				((((x-selectedX==2) && (y-selectedY==2 || y-selectedY==-2)) ||
				((x-selectedX==-2) && (y-selectedY==2 || y-selectedY==-2)))
					&& (pieces[(selectedX+x)/2][(selectedY+y)/2] != null)
					&& (pieces[(selectedX+x)/2][(selectedY+y)/2].isFire() != fireTurn)))
				return true;
			return false;
		}
	}


	public boolean canSelect(int x, int y){
		if (x<0||x>7||y<0||y>7)
			return false;
		
		

		// select a piece
		if (pieces[x][y]!=null)
		{	// check right player's turn
			if (fireTurn != pieces[x][y].isFire())
				return false;
			// not selected a piece yet
			if (selected==null)
				return true;
			// selected a piece yet not moved
			if (!selectedMoved)
				return true;
			return false;
		}     

		// select a empty square
		else 
		{	
			// single move or capture
			if ((selected!=null) && (!selectedMoved) && (!selected.hasCaptured()))
			{
				if (validDest(x,y))   
					return true;
			}

			// multiple capture
			// if ((selected!=null) && selectedMoved && selected.hasCaptured())
			// 	{if (validDest(x,y)) return true;}
			if ((selected!=null) && selectedMoved && selected.hasCaptured())
			{
				if (validDest(x,y)) 
					return true;
			}

			return false;
		}         

	}
	
	public void select(int x, int y){

		// select empty: capture or move;
		if (pieces[x][y]==null)
		{
			selected.move(x, y);
			selectedMoved = true;
			selectedX = x;
			selectedY = y;
		}
		// select a piece; 
		else
		{
			selected = pieces[x][y];
			selectedX = x;
			selectedY = y;
		}
	}

	public void place(Piece p, int x, int y){
		if (p!=null && x>=0&&x<=7 && y>=0&&y<=7)
			pieces[x][y]=p;
	}

	public Piece remove(int x, int y){
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}


	public boolean canEndTurn(){
		if ((selected!=null) && selectedMoved)
			return true;
		return false;
	}

	public void endTurn(){

		fireTurn = !fireTurn;
		selected.doneCapturing();
		selected = null;
		selectedMoved = false;

	}


	public String winner(){

		int numFire = 0;
		int numWater = 0;
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if (pieces[i][j]!=null){
					if (pieces[i][j].isFire())
						numFire+=1;
					else numWater+=1;
				}
			}
		}

		if (numWater==0 && numFire==0)
			return "No one";
		if (numFire==0)
			return "Water";
		if (numWater==0)
			return "Fire";
		return null;
	}


	public static void main(String args[]) {
		
        // Board b = new Board(false);
        // StdDrawPlus.setXscale(0,8);
        // StdDrawPlus.setYscale(0,8);
        // while(b.winner()==null){
        //     b.drawBoard(8);
        // if (StdDrawPlus.mousePressed()) {
        //         int x = (int)StdDrawPlus.mouseX();
        //         int y = (int)StdDrawPlus.mouseY();
        //         if (b.canSelect(x,y))
        //             b.select(x,y);
                
        // }
            

        // if (b.canEndTurn() && StdDrawPlus.isSpacePressed())
        //         b.endTurn();
         
        //     StdDrawPlus.show(100);
        // }
        // b.drawBoard(8);

       
	}


}











