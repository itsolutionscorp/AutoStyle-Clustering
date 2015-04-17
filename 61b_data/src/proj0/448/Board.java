/** 
 *  @author Jiang Guo
 */

public class Board {
    /** Peace 2Pac **/
	
	private int N = 8;
	private Piece [][] pieces = new Piece[N][N];
	private boolean [][] mySelect = new boolean [N][N];
	private int turn = 0;
	
	private static boolean select;
	private boolean moved = false;
	private boolean capted = false;
	
	public Board(boolean shouldBeEmpty){
		boolean IsEmpty = shouldBeEmpty;
		
		// initialize all the pieces into place
    	if (!IsEmpty){
    		for (int i=0; i<N ; i++){
    			if (i%2 ==0 ) pieces[i][0] = new Piece(true, this, i, 0, "pawn");
    			else pieces[i][7] = new Piece(false, this, i, 7, "pawn");
    		}
    		
    		for (int i=0; i<N ; i++){
    			if (i%2 ==0 ) pieces[i][6] = new Piece(false, this, i, 6, "shield");
    			else pieces[i][1] = new Piece(true, this, i, 1, "shield");
    		}
    		
    		for (int i=0; i<N ; i++){
    			if (i%2 ==0 ) pieces[i][2] = new Piece(true, this, i, 2, "bomb");
    			else pieces[i][5] = new Piece(false, this, i, 5, "bomb");
    		}
    		
    		for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                	mySelect[i][j] = false;
                }
        	};
    	}else{
    		for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                	mySelect[i][j] = false;
                	pieces[i][j] = null;
                }
        	};
    	}
    	
    	select = false;
    	
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        this.drawBoard();
	}
	
    public static void main(String[] args) {
    	Board myBoard = new Board(false);
    	
        // this loop is the user input monitor, if input the triggered do some action
        while (true){
        	// the big mouse clicked part
        	if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                
                if (myBoard.canSelect(x, y)){
                	myBoard.select(x, y);
                }else{
                	select = false;
                }
                
                // keep updating the board and pieces
        		myBoard.drawBoard();
        	}
        	
        	// the space bar input part
        	if(StdDrawPlus.isSpacePressed()){
        		if (myBoard.canEndTurn()){
        			myBoard.endTurn();
        			myBoard.drawBoard();
        		}
        	}
        	
        	StdDrawPlus.show(100);
        	
        	String endWord = myBoard.winner();
        	
        	if (endWord != null){
        		System.out.println(endWord);
        	}
        }
    }
	
    private boolean checkCapted (Piece p, int x, int y){
    	if (pieces[x][y] == null){
    		return false;
    	}else if (p.hasCaptured()){
    		return true;
    	}
    	return false;
    }

	public Piece pieceAt(int x, int y){
    	if ( x >= 0 && x < N && y >= 0 && y < N) return pieces[x][y];
    	return null;
    }
	
	private int[] selectOneFinder(){
		int [] resu = new int [2]; 
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (mySelect[i][j]){
            		resu[0] = i;
            		resu[1]= j;
            		break;
            	}
            }
    	}; return resu;
	}
	
	private boolean canCapture(int x, int y){
		int seleX = 0, seleY = 0;
		seleX = selectOneFinder()[0];
    	seleY = selectOneFinder()[1];
    	
    	if (pieces[seleX][seleY].isKing()){
    		if (Math.abs(x-seleX) == 2 && Math.abs(y-seleY) == 2){
    			if (pieces[(x+seleX)/2][(y+seleY)/2] != null && pieces[x][y] == null){
    				if (pieces[(x+seleX)/2][(y+seleY)/2].side() == turn) return false;
    				return true;
    			}
    		}return false;
    	}else if (pieces[seleX][seleY].isFire()){
    		if (y-seleY == 2 && Math.abs(x-seleX) == 2){ // Diagonal capture \\ //
				if (pieces[(x+seleX)/2][(y+seleY)/2] != null && pieces[x][y] == null){
					if (pieces[(x+seleX)/2][(y+seleY)/2].side() == turn)return false;
					return true;
				}
			}return false;
    	}else{
    		if (seleY-y == 2 && Math.abs(x-seleX) == 2){ // Diagonal capture \\ //
				if (pieces[(x+seleX)/2][(y+seleY)/2] != null && pieces[x][y] == null){
					if (pieces[(x+seleX)/2][(y+seleY)/2].side() == turn)return false;
					return true;
				}
			}return false;
    	}
	}
	
    public boolean canSelect(int x, int y){
    	int seleX = 0, seleY = 0;
    	
    	// check if the user select a piece from the other side, and if have selected already
    	if (!select){
    		if (moved && !capted){
    			return false;
    		}
    		
    		Piece temp = pieceAt(x, y);
    		if (temp != null){
    			
    			if (temp.side() == turn) return true;
    		}return false;
    	}
    	
    	seleX = selectOneFinder()[0];
    	seleY = selectOneFinder()[1];
    	
    	// check if the user have the same piece again
    	if (mySelect[x][y]){
    		return false;
    	}
    	
    	// check if it is a valid capture moves
    	if (canCapture(x, y)){
    		return true;
    	}
    	
    	// here is the valid or invalid move tester
    	if (pieces[seleX][seleY].isKing()){
			if (Math.abs(x-seleX) == 1 && Math.abs(y-seleY) == 1){
				if (pieces[x][y] ==  null) return true;
			}
    	}else {
    		if (pieces[seleX][seleY].isFire()){ // check if it needs to go up.
    			if (y-seleY == 1 && Math.abs(x-seleX) == 1){ // Diagonal move \ /
    				if (pieces[x][y] == null)return true;
    			}
    		}else{
    			if (seleY-y == 1 && Math.abs(x-seleX) == 1){ // Diagonal move \ /
    				if (pieces[x][y] == null)return true;
    			}
    		}
    	}
    	
    	if (pieces[seleX][seleY].side() == pieces[x][y].side()) return true;
    	return false;
    }
    
    public void select(int x, int y){
    	if (!select){
    		mySelect[x][y] = true;
    		select = true;
    		turn = pieces[x][y].side();
    	}else{
    		int seleX = selectOneFinder()[0];
    	    int seleY = selectOneFinder()[1];
    	    
    	    if (x==seleX && y==seleY){
    	    	mySelect[x][y] = true;
        		select = true;
        		turn = pieces[x][y].side();
    	    	return;
    	    }
    	    
    		if (Math.abs(seleX-x)==2 || Math.abs(seleY-y)==2){
    			capted = true;
    			select = true;
    			pieces[seleX][seleY].move(x, y);
    			
    			if (pieces[x][y].isBomb()){
    				mySelect[seleX][seleY] = false;
    				privateBomb(x, y);
    				select = false;
    			}
    			remove((seleX+x)/2, (seleY+y)/2);
    			
    			mySelect[seleX][seleY] = false;
    			mySelect[x][y] = true;

	    	    moved = true;
	    	    drawBoard();
	    	    
	    	    return;
    		}
    		
    		// just simply move
    	    pieces[seleX][seleY].move(x, y);;
    	    mySelect[seleX][seleY] = false;
    	    select = false;
    	    
    	    moved = true;
    	    drawBoard();
    	}
    }
    
    private void drawBoard(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawPiece(pieces[i][j], i, j);
            }
        }
        
        if (select){
    		int seleX = selectOneFinder()[0];
    	    int seleY = selectOneFinder()[1];
    	    
    	    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(seleX + 0.5, seleY + 0.5, 0.5);
            drawPiece(pieces[seleX][seleY], seleX, seleY);	
    	}
    }
    
    private void privateBomb(int x, int y){
    	for (int i = 0; i<= 2; i++){
			for (int j = 0; j<= 2; j++){
				if(x-1+i >=0 && y-1+i >= 0){
					if (x-1+j < N && y-1+j < N){
						if (pieces[x-1+i][y-1+j] != null){
							if (!pieces[x-1+i][y-1+j].isShield() && pieces[x-1+i][y-1+j].side() != turn){
								remove(x-1+i, y-1+j);
							}
						}
					}
				}
			}
		}remove(x,y);
    }
    
    private void drawPiece(Piece p,int x,int y){
    	if (p==null){
    		return;
    	}
    	if (p.isShield()){
	    	if (p.isFire()){
				if (p.isKing()){
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
				}else{
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
				}
			}else{
				if (p.isKing()){
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
				}else{
					StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
				}
			}
    	}else if (p.isBomb()){
    		if (p.isFire()){
    			if (p.isKing()){
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
    			}else{
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
    			}
    		}else{
    			if (p.isKing()){
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
    			}else{
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
    			}
    		}
    	}else{
    		if (p.isFire()){
				if (p.isKing()){
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
				}else{
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
				}
			}else{
				if (p.isKing()){
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
				}else{
					StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
				}
			}
    	}
    }
    
    // this method only called when testing
    public void place (Piece p, int x, int y){
    	if ( x >= 0 && x < N && y >= 0 && y < N && p != null) pieces[x][y] = p;
    	pieces[x][y].isKing();
    }
    
    public Piece remove(int x, int y){
    	Piece temp = pieces[x][y];
    	pieces[x][y] = null;
    	return temp;
    }
    
    public boolean canEndTurn(){
    	return moved;
    }
    
    public void endTurn(){
    	if (turn == 1) turn = 0;
    	else turn = 1;
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	mySelect[i][j] = false;
            }
    	};
    	
    	int seleX = selectOneFinder()[0];
    	int seleY = selectOneFinder()[1];
    	
    	if (pieces[seleX][seleY] != null){
    		pieces[seleX][seleY].doneCapturing();
    	}
    	
    	select = false;
    	moved = false;
    	capted = false;
    }
    
    public String winner(){
    	int fireCounter = 0;
    	int waterCounter = 0;
    	
    	for (int i=0; i < N; i++){
    		for (int j=0; j < N; j++){
    			if (pieces[i][j]!= null){
    				if(pieces[i][j].isFire())	fireCounter++;
    				else						waterCounter++;
    			}
    		}
    	}
    	
    	if (waterCounter == 0 && fireCounter == 0){
    		return ("No one");
    	}else if (waterCounter == 0){
    		return ("Fire");
    	}else if (fireCounter == 0){
    		return ("Water");
    	}return null;
    }
}