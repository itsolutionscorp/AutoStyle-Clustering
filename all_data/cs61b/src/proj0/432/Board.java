// 
public class Board {	
    private boolean[][] isFilled;
    private Piece[][] content;
    private int N, fireNum, waterNum, currPlayer, xSelected, ySelected;
    private Piece pieceSelected;
    private boolean moved;
    
    public Board(boolean shouldBeEmpty){
    	this.N = 8;
		currPlayer = 0;
		pieceSelected = null;
		moved = false;
        isFilled = new boolean[N][N];
        content = new Piece[N][N];
        fireNum = 0;
        waterNum = 0;
        
		for (int i=0; i<N; i++)
			for (int j=0; j<N; j++)
				isFilled[i][j] = false;
		
		if (shouldBeEmpty == false){
			boardinit();
		}
    }

    private static void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);                
            }
        }
    }
    
    private void boardinit(){
    	fireNum = 12;
    	waterNum = 12;
    	for (int i = 0; i <= 3; i++){
    		this.isFilled[i*2+1][7] = true;
    		this.content[i*2+1][7] = new Piece(false, this, i*2+1, 7, "pawn");
    		
    		this.isFilled[i*2][6] = true;
    		this.content[i*2][6] = new Piece(false, this, i*2, 6, "shield");
    		
    		this.isFilled[i*2+1][5] = true;
    		this.content[i*2+1][5] = new Piece(false, this, i*2+1, 5, "bomb");
    		
    		this.isFilled[i*2][0] = true;
    		this.content[i*2][0] = new Piece(true, this, i*2, 0, "pawn");
    		
    		this.isFilled[i*2+1][1] = true;
    		this.content[i*2+1][1] = new Piece(true, this, i*2+1, 1, "shield");
    		
    		this.isFilled[i*2][2] = true;
    		this.content[i*2][2] = new Piece(true, this, i*2, 2, "bomb");
    	}
    }
    
    private static String getImagePath(Piece obj){
    	String ans = "img/";
    	if (obj.isBomb()) {
    		ans += "bomb-";
    	} else if (obj.isShield()){
    		ans += "shield-";
    	} else {
    		ans += "pawn-";
    	}
    	
    	if (obj.isFire()){
    		ans += "fire";
    	} else {
    		ans += "water";
    	}
    	
    	if (obj.isKing()){
    		ans += "-crowned";
    	}
    	ans += ".png";
    	return ans;
    }
    
    private void deploycontent(){
    	if (pieceSelected != null){
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	    StdDrawPlus.filledSquare(xSelected + .5, ySelected + .5, .5);
    	}
    	for (int i = 0; i < this.N; i++){
    		for (int j = 0; j< this.N; j++){
    			if (isFilled[i][j]){
    				String path = getImagePath(this.content[i][j]);
                    StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
    			}
    		}
    	}
        
    }

    private void printmap(){
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                if (isFilled[j][7-i]){
                    if (content[j][7-i].isFire()){
                        //System.out.print('F');
                    } else {
                        //System.out.print('W');
                    }
                    if (content[j][7-i].isBomb()){
                        //System.out.print('B');
                    } else if (content[j][7-i].isShield()){
                        //System.out.print('S');
                    } else {
                        //System.out.print('N');
                    }
                } else {
                    //System.out.print("EM");
                }
                //System.out.print(" ");
            }
            //System.out.println();
        }
        //System.out.println();
    }
    
    private static boolean valid(int x, int y){
    	if ((x >= 0) && (x <= 7) && (y >=0) && (y<=7)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    // not sure if this works
    public void place(Piece p, int x, int y){
    	if (valid(x, y) && (p != null)){
            //System.out.println(x + " " + y + "placed, the board now looks like");
            printmap();
    		p.move(x, y);
    		if (p.isFire()){
    			fireNum++;
    		} else {
    			waterNum++;
    		}
    		this.isFilled[x][y] = true;
    		this.content[x][y] = p;
    	}
    }
    
    public Piece remove(int x, int y){
    	if ((valid(x, y)) && (isFilled[x][y])){    		
    		Piece ans = this.content[x][y];
        	//System.out.println(x + " " + y + " removed. The board now looks like ");

    		if (ans.isFire()){
    			fireNum--;
    		} else {
    			waterNum--;
    		}    		
    		this.isFilled[x][y] = false;
    		this.content[x][y] = null;
            printmap();
    		return ans;
    	} else if (valid(x, y)) {
    		//System.out.println(x + ", " + y + " is empty");
    		return null;
    	} else {
    		//System.out.println(x + ", " + y + " is not a valid position");
    		return null;
    	}
    }
    
    public Piece pieceAt(int x, int y){
        if (!valid(x, y)){
            return null;
        }
    	if (this.isFilled[x][y]){
    		return this.content[x][y];
    	} else {
    		return null;
    	}
    }
    
    public String winner(){
        //System.out.println("Winner is tested when Fire: " + fireNum + " Water: "+ waterNum);
        printmap();
    	if (fireNum + waterNum == 0){
    		return "No one";
    	} else if (fireNum == 0){
    		return "Water";
    	} else if (waterNum == 0){
    		return "Fire";
    	} else {
    		return null;
    	}
    }
    
    private boolean validMove(int xStart, int yStart, int xEnd, int yEnd){
		////System.out.println("got here!"+xStart+yStart+xEnd+yEnd);

    	if ((Math.abs(yEnd - yStart) != Math.abs(xEnd - xStart)) || (Math.abs(xEnd-xStart) > 2))
    		return false;
    	
    	Piece ori = pieceAt(xStart, yStart);
    	if (ori.isKing() == false){
    		if (ori.isFire() && (yEnd < yStart))
    			return false;
    		if (!ori.isFire() && (yEnd > yStart))
    			return false;
    	}

    	if (Math.abs(yEnd-yStart) == 1){
    		return true;
    	} else {
    		int xMid = (int) ((xStart + xEnd) / 2);
    		int yMid = (int) ((yStart + yEnd) / 2);
    		Piece p = pieceAt(xMid, yMid);
    		if ((p != null) && (p.side() + pieceSelected.side() == 1)){
    			return true;
    		} else {
    			return false;
    		}
    	}
    		
    }
    
    public boolean canSelect(int x, int y){
        //System.out.println("Can we select " + x + " " + y);

        if (!valid(x, y))
            return false;

    	if (!isFilled[x][y]){
    		if ((pieceSelected != null) && (!moved) && (validMove(xSelected, ySelected, x, y))){
    			return true;
    		} else if ((pieceSelected != null) && (moved) && (pieceSelected.hasCaptured())
    				&& (!pieceSelected.isBomb()) && validMove(xSelected, ySelected, x, y)
                    && (Math.abs(xSelected-x) == 2)){
    			return true;
    		} else {
            	//System.out.println(x + " " + y + " can't select.");
    			return false;
    		}
    	} else {
    		if ((pieceSelected == null) || (!moved))
    			return (currPlayer == pieceAt(x, y).side());
        	//System.out.println(x + " " + y + " can't select.");
    		return false;
    	}
    }
    
    private void explode(int x, int y){
    	for (int i=-1; i<=1; i++)
    		for (int j=-1; j<=1; j++){
    			int x1 = x+i, y1 = y+j;
    			if (valid(x1,y1)){
    				Piece p = pieceAt(x1, y1);
    				if ((p != null) && (!p.isShield())){
    					remove(x1, y1);
    				}
    			}
    		}
    }
    
    public void select(int x, int y){
    	if (pieceAt(x, y) != null){
    		pieceSelected = pieceAt(x, y);
    		xSelected = x;
    		ySelected = y;
            //System.out.println(x + " " + y + " selected.");
    	} else {
    		moved = true;
            //System.out.println("hop to selected"+x + " " + y);
    		place(pieceSelected, x, y);
    		xSelected = x;
    		ySelected = y;  

    		if (pieceSelected.hasCaptured() && pieceSelected.isBomb()){
            	//System.out.println("explosion begins at "+ x + " " + y);
    			explode(x, y);
                printmap();
    		}
    	}
    }
    
    public boolean canEndTurn(){
    	return moved;
    }
    
    public void endTurn(){
    	currPlayer = 1 - currPlayer;
    	pieceSelected.doneCapturing();
    	pieceSelected = null;
    	moved = false;
    }
    
	public static void main(String[] args){		
		Board workboard = new Board(false);
		workboard.drawBoard(workboard.N);
		workboard.deploycontent();
		while (workboard.winner() == null){
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
            	//System.out.println(x + " " + y + " attempted.");
                if (valid(x, y) && (workboard.canSelect(x, y))){
                	workboard.select(x, y);
                }
            }            
            if (StdDrawPlus.isSpacePressed()){
            	if (workboard.canEndTurn())
            		workboard.endTurn();
            	//System.out.println("Water checkers:" + workboard.waterNum + " Fire checkers:" + workboard.fireNum);
            }
            StdDrawPlus.show(100);
    		workboard.drawBoard(workboard.N);
    		workboard.deploycontent();
		}
		//System.out.println(workboard.winner() + "wins");
	}
}
