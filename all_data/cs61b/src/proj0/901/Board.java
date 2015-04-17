
public class Board {
	public  Piece[][] pieces;
	private static int N;
	private boolean fireTurn = true; 
	private boolean hasMoved = false;
	public int selectedx = -1;
	public int selectedy = -1;
	private boolean hasCaptured;
	private boolean validSelect = true;
	
	public Board(boolean empty){
		N = 8;
		pieces = new Piece[N][N];
		if(!empty){
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (((i + j) % 2 == 0) && j != 3 && j != 4){
						
						if (j==0) pieces[i][j] = new Piece(true, this, j, i, "pawn");
						else if (j == 1)pieces[i][j] = new Piece(true, this, j, i, "shield");
						else if (j == 2) pieces[i][j]= new Piece(true, this, j, i, "bomb");
						else if (j == 5) pieces[i][j] = new Piece(false, this, j, i, "bomb");
						else if (j == 6)pieces[i][j] = new Piece(false, this, j, i, "shield");
						else if (j == 7) pieces[i][j]= new Piece(false, this, j, i, "pawn");
					}
					else{
						pieces[i][j]= null;
					}
		        }
			}
		}
	}
	
	public Piece pieceAt(int x, int y){
		return pieces[x][y];
	}
	
	public void place(Piece p, int x, int y){
		if (x < N && y < N && p == null){
			pieces[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		if (x >= N || y >= N){
			System.out.println("Error x,y out of bounds");
			return null;
		}
		else if (pieces[x][y] == null){
			System.out.println("Error Piece does not exist: " + x + ", " + y );
			return null;
		}
		else{
			Piece p = pieces[x][y];
			pieces[x][y] = null;
			return p;
		}
	}
	
	public boolean canSelect(int x, int y){
		
		if(selectedx == -1 && pieces[x][y] == null) return false;
		else if (selectedx == -1 && pieces[x][y].isFire() != fireTurn) return false;
		else if (selectedx != -1 && !hasMoved) return true;
		else if (selectedx != -1 && !validMove(selectedx,selectedy,x,y))
			return false;
		return true;
	}
	
	public void select (int x, int y){
		
			if (pieces[x][y] != null){ 
				selectedx = x;
				selectedy = y;
				
			}
			else if (selectedx != -1 && validMove(selectedx,selectedy, x, y) ){
				System.out.println(selectedx + ", " + selectedy + "lala");
				pieces[selectedx][selectedy].move(x,y);
	    		selectedx = -1;
	    		selectedy = -1;
				
			}
			

	
	}
	


	
	private boolean validMove (int xi, int yi, int xf, int yf){
		Piece p = pieces[xi][yi];
		Piece q = pieces[xf][yf];
		int N = 1;
		if (!p.isFire()) N = -1;
		
		if(validCapture(xi,yi,xf,yf, N)){
			hasCaptured = true;;
			return true; 
		}
		else if (Math.abs(xi - xf) == 1 && yi + N == yf && q == null) return true; 
		else if (p.isKing()){
			if (Math.abs(xi - xf) == 1 && yi - N == yf && q == null) return true; 
		
		}
		System.out.println("nope" + Math.abs(yi - yf) + ", " + xi + ",, " +xf);
		return false;
	}
	
	private boolean validCapture (int xi,int yi, int xf, int yf, int N){
		Piece p = pieces[xi][yi];
		Piece q = pieces[xf][yf];
		
		if (Math.abs(yi - yf) == 2  && xi + 2*N == xf && pieces[xi + N][yi + 1]!= null
				&& pieces[xi + N][yi - 1] != null && q == null) return true;
		
		else if (p.isKing() && Math.abs(xi - xf) == 2  && yi - 2 == yf && pieces[xi - N][yi + 1]!= null
				&& pieces[xi - N][yi - 1] != null && q == null) return true;
		
		return false;
	}
	
	
	
	
	
	
    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (i == selectedx && j == selectedy)highlightSquare(i,j);
                drawPicture(i,j);
                
            }
        }
    }
    
    public boolean canEndTurn(){
    	if (hasMoved || hasCaptured)
    		return true;
    	return false;
    }
    
    public void endTurn(){
    	if (canEndTurn()){
    		hasMoved = false;
    		hasCaptured = false;
    		fireTurn = !fireTurn;

    	}
    }
    
    private void drawPicture(int i, int j){
    	if (pieceAt(i,j)!= null) {
        	Piece x = pieces[i][j];
        	if (x.isBomb()&& x.isFire())StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
        	else if (x.isBomb()&& !x.isFire())StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
        	else if (x.isShield()&& x.isFire())StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
        	else if (x.isShield()&& !x.isFire())StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
        	else if (x.isFire())StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        	else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
        	

        }
    	
    }
    private void highlightSquare(int i, int j){
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	StdDrawPlus.filledSquare(i + .5, j + .5, .5); 
    }
    
    
    public static void main(String args[]){
    	Board b = new Board(false);
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        int i;
        int j;
    	while(true){
    		b.drawBoard();
    		 if (StdDrawPlus.mousePressed()) {
                 Double x = StdDrawPlus.mouseX();
                 Double y = StdDrawPlus.mouseY();
                 i = x.intValue(); j = y.intValue();
                 System.out.println("clicky" + i + ", " + j);
                 if(b.canSelect(i,j)){
	    			 b.select(i,j);
	    		 }
    		 }
    		 
    		StdDrawPlus.show(10);
    	}
    	
    }
}
