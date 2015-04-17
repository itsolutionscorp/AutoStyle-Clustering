
public class Board {
	private Piece lastselected = null;
	private int lastselectedx;
	private int lastselectedy;
	private boolean hasmoved = false;
	private int[][] boardcolors;
	private Piece[][] pieces;
	private boolean Empty;
	private final static int Red = 0;
	private final static int White = 2;
	private final static int Gray = 1;
	private final static int N = 8; //map limit
	private int turn = 1;//If its 1, its fires turn if its 0 its waters turn
	public Board(boolean shouldBeEmpty){
		Empty = shouldBeEmpty;
		pieces = new Piece[N][N];
		boardcolors = new int[N][N];
		if(!shouldBeEmpty) pieceinitializer();
		EmptyBoardInitializer(N);
			
		
	}
	//creates a new array of pieces
	private void pieceinitializer() {
	       for (int i = 0; i < N; i++) {
	           for (int j = 0; j < N; j++) {
	               if( (i%2 == 0)&&j==0){
	            	   pieces[i][j] = new Piece(true,this,i,j,"pawn");//True is fire type
	               }
	               else if((i%2 ==1)&&(j==1)){
	            	   pieces[i][j] = new Piece(true,this,i,j,"shield");
	               }
	               else if((i%2==0)&&(j==2)){
	            	   pieces[i][j] = new Piece(true,this,i,j,"bomb");
	               }
	               else if( (i%2 == 1)&&j==7){
	            	   pieces[i][j] = new Piece(false,this,i,j,"pawn");//False is water type
	               }
	               else if((i%2 ==0)&&(j==6)){
	            	   pieces[i][j] = new Piece(false,this,i,j,"shield");
	               }
	               else if((i%2==1)&&(j==5)){
	            	   pieces[i][j] = new Piece(false,this,i,j,"bomb");
	               }
	           }
	       }
	}
	private void piecedrawer(){
		for (int i = 0; i < N; i++) {
	           for (int j = 0; j < N; j++) {
	        	   Piece current = pieces[i][j];//The current piece
	        	   if (current!=null){
	        		   if(current.isKing()){//if crowned
	        			   if(current.isFire()){//is it crowned fire or crowned water
	        				   if(current.isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	        				   else if(current.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	        				   else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	        			   }
	        			   else{//its crowned water
	        				   if(current.isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	        				   else if(current.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	        				   else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	        			   }
	        		   }
	        		   else{//its not crowned
	        			   if(current.isFire()){//is it fire or water
	        				   if(current.isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	        				   else if(current.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	        				   else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	        			   }
	        			   else{//its water
	        				   if(current.isBomb()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	        				   else if(current.isShield()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	        				   else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	        			   }
	        		   }
	        	  }
	          }
		 }
	}
    private void EmptyBoardInitializer(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) boardcolors[i][j] = Gray; 
                else                  boardcolors[i][j] = Red; 
                }
            }
        }
    private void drawEmptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (boardcolors[i][j] == Gray) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else if(boardcolors[i][j] == Red) StdDrawPlus.setPenColor(StdDrawPlus.RED);
                else StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
    //returns the piece object
    public Piece pieceAt(int x, int y){
    	if (x>(N-1) || y>(N-1) || x<0 || y<0) return null;
    	if (pieces[x][y] ==null) return null;
    	return pieces[x][y];
    }
    public boolean canSelect(int x, int y){
    	Piece p=pieceAt(x,y);
    	if(p!=null){//if the location has a piece
    		if( p.isFire() && turn==0) return false;
    		if( !p.isFire() && turn==1) return false;
    		
    		if(lastselected == null){
    			return !hasmoved;//if hasmoved is true, the place was a bomb, else it is first time selection
    		}
    		if(hasmoved ==false)//switching to select new piece
    			return true;
    		else return false;//the selected piece has been moved so waiting for space
    	}
    	if(lastselected != null){
    		if(hasmoved == false && 
    			validMove(lastselectedx,lastselectedy,x,y,true)){//if a previous piece has been selected and it hasnt moved yet and moving to position x,y is valid
    			return true;  		
    		}
			if(hasmoved == true && lastselected.hasCaptured()){
				if(validMove(lastselectedx,lastselectedy,x,y,false))//checking for multiple jumps
					return true;
			}
    	}
        return false;
    	
    }

    private boolean validMove(int xi, int yi, int xf, int yf,boolean d1moveallowed){
    	if(pieceAt(xi,yi)==null) return false; //there is no piece to move
        if(xf>(N-1)||yf>(N-1)||xf<0||yf<0) return false; //out of bounds destination
        if(pieceAt(xf,yf)!=null) return false; //final destination always has to be empty
        int xdif = xf - xi;
        int ydif = yf - yi;
        int ydirection;
        if(pieceAt(xi,yi).isFire())
        	ydirection=ydif;
        else ydirection = -ydif;

        if(pieceAt(xi,yi).isKing() ==false && ydirection <0)
            return false;
        if(Math.abs(xdif) == 1 && Math.abs(ydif)==1){//if its a d1moved
            return d1moveallowed;
        }
        if(Math.abs(xdif) == 2 && Math.abs(ydif)==2){
        	Piece pmid = pieceAt(xf-xdif/2,yf-ydif/2);
            if(pmid!=null && (pmid.isFire())!=(pieceAt(xi,yi).isFire()))//make sure the types are different
            	return true;
            else return false;
        }
        return false;
    }
    public void select(int x, int y){
    	if(lastselected == null) {//if no piece has been selected
    		lastselected = pieceAt(x,y);
    		lastselectedx = x;
    		lastselectedy= y;
    		boardcolors[x][y] = White;
    		return;
    	}
    	if(pieceAt(x,y)!=null && !hasmoved){
    		//switch pieces, choose another piece
    		lastselected = pieceAt(x,y);
    		boardcolors[lastselectedx][lastselectedy] = Gray;
        	lastselectedx = x;
        	lastselectedy= y;
        	boardcolors[x][y] = White;
        	return;
    	}
    	//capturing/move
        boardcolors[lastselectedx][lastselectedy]  = Gray;//change to gray
        lastselected.move(x, y);
       	boardcolors[x][y] = White;//change to white
       	hasmoved = true;
       	lastselected = pieceAt(x,y);
       	lastselectedx = x;
       	lastselectedy = y;  	
    }
    
    public void place(Piece p,int x, int y){
    	if(p == null || x>(N-1)||y>(N-1)||x<0||y<0) return;
    	pieces[x][y] = p;
    }
    public Piece remove(int x, int y){
    	//If the input (x, y) is out of bounds, returns null and prints an appropriate message.
    	//If there is no piece at (x, y), returns null and prints an appropriate message.
    	if(x>(N-1)||y>(N-1)||y<0||x<0){
    		System.out.println("Out of bounds");
    		return null;
    	}
    	if(pieces[x][y]==null){
    		System.out.println("Nothing to remove from "+x+","+y);
    		return null;
    	}
    	Piece ptemp = pieces[x][y];
    	pieces[x][y]=null;
    	return ptemp;
    }
    public boolean canEndTurn(){
    	//Returns whether or not the the current player can end their turn. 
    	//To be able to end a turn, a piece must have moved or performed a capture.
    	return hasmoved;
    }
    public void endTurn(){
    //Called at the end of each turn. 
    //Handles switching of players and anything else that should happen at the end of a turn.
    	if(canEndTurn()){
    		turn = 1 - turn;
    		hasmoved = false;
    		if(lastselected != null){
    			lastselected.doneCapturing();
    			lastselected = null;
    		}
    	}
    }
    
    public String winner(){
    	int i,j;
        Piece p;
        int watercount=0, firecount=0;
    	for ( i = 0; i < N; i++) {
	           for ( j = 0; j < N; j++) {
	        	   p=pieceAt(i,j);
	               if(p!=null){
	            	   if(p.isFire())
	            		   firecount++;
	            	   else
	            		   watercount++;
	               }
	           }
    	 }
    	if(watercount==0&& firecount==0) return "No one";
    	if(firecount==0) return "Water";
    	if(watercount==0) return "Fire";
    	return null;
    }
	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        double xp,yp; int x,y;
        String w;
        b.drawEmptyBoard(N); 
        while(true) {
        	b.drawEmptyBoard(N);
     		b.piecedrawer();      
            if (StdDrawPlus.mousePressed()) {
                      xp = StdDrawPlus.mouseX();
                      yp = StdDrawPlus.mouseY();
                      x=(int) xp; y=(int)yp;
                      //System.out.print("Click at "+x+", "+y);
                      if(b.canSelect(x, y)){
                    	 // System.out.println(" Select "+x+", "+y);
                    	  b.select(x,y);
                          w=b.winner();
                          if(w!=null) System.out.println(w);
                      }
            } else if (StdDrawPlus.isSpacePressed()){
            	      //System.out.print("Select space...");
                      if(b.canEndTurn()){
                    	  b.EmptyBoardInitializer(N);
                    	  //System.out.println("Endturn");
                    	  //System.out.println(1-b.turn);//0 means it is now water's turn.
                    	  b.endTurn();
                      }
                   }
                
            StdDrawPlus.show(5 );

        }
	
   }
}
