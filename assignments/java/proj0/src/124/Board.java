public class Board {

    private Piece[][] arrayP;
    private boolean changeHappened;
    private int side = 0; //Fire side = 0; Water side = 1
    private Piece chosenOne;
    private boolean hasMoved;
    private int fireNum = 0;
    private int waterNum= 0;
    
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board myBoard = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
        a new piece appears. */
        while(myBoard.winner() == null) {
            myBoard.drawBoard(N);
            if (StdDrawPlus.isSpacePressed()){ //end turn
           		if (myBoard.canEndTurn()){
            		myBoard.endTurn();
            	}
            }
            if (StdDrawPlus.mousePressed()) { //select a spot
               double x = StdDrawPlus.mouseX();
               double y = StdDrawPlus.mouseY();
               if (myBoard.canSelect((int)x,(int)y)){
            	   myBoard.select((int)x, (int)y);
                }
            }
	        StdDrawPlus.show(100);
        }
        System.out.println(myBoard.winner());
        myBoard.drawBoard(N);
    }
    public Board (boolean shouldBeEmpty){
        arrayP = new Piece[8][8];
        changeHappened = false;
        hasMoved = false;
        if (!shouldBeEmpty) {
          for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if ((i + j) % 2 == 0) {
		              if (j ==0){ 
		                arrayP[i][j] = new Piece (true,this,i,j,"pawn"); 
		              }
		              else if (j ==1){
		                arrayP[i][j] = new Piece (true,this,i,j,"shield"); 
		              }
		              else if (j ==2){
		                arrayP[i][j] = new Piece (true,this,i,j,"bomb"); 
		              }
		              else if (j ==5){
		                arrayP[i][j] = new Piece (false,this,i,j,"bomb"); 
		              }
		              else if (j ==6){
		                arrayP[i][j] = new Piece (false,this,i,j,"shield"); 
		              }
		              else if (j ==7){
		                arrayP[i][j] = new Piece (false,this,i,j,"pawn"); 
		              }
            	}
            }
          }
        }
    }
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	if (pieceExists(chosenOne)){
	                	if ((xpos(chosenOne) == i) && (ypos(chosenOne)) == j){
	                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                	}
                	}
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (arrayP[i][j]!=null){
                    if (arrayP[i][j].isFire()){
                        if (arrayP[i][j].isShield()){
                            if (arrayP[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1,1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1,1);
                            }
                        }
                        else if (arrayP[i][j].isBomb()){
                            if (arrayP[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1,1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1,1);
                            }
                        }
                        else{
                            if (arrayP[i][j].isKing()) {
                               StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1,1);
                            }
                            else { 
                               StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1,1);
                           }
                        }                        
                    }
                    else { //waterside
                        if (arrayP[i][j].isShield()){
                            if (arrayP[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1,1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1,1);
                            }
                        }
                        else if (arrayP[i][j].isBomb()){
                            if (arrayP[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1,1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1,1);
                            }
                        }
                        else {
                            if (arrayP[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1,1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1,1);
                            }
                        }                        
                    }
                }
            }
        }
    }
    public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7 || x < 0 || y < 0){
            System.out.print("Selected spot out of bounds");
            return null;
        }
        else if (arrayP[x][y] != null){
            return arrayP[x][y];
        }
        else {
            return null;      
        }
    }
    public boolean canSelect(int x, int y){
        if (arrayP[x][y] != null){ //selecting piece
        	if (changeHappened == true){
        		return false;        		
        	}
        	else {
        		if (arrayP[x][y].side() == side){
                    return true;
                }
                else {
                    return false;
                }
        	}
        }
        else { //selecting place   
        	if (pieceExists(chosenOne)){
        		if (chosenOne.side() == side) {
	        		if (chosenOne.hasCaptured()){ // piece can only CAPTURE again
		        		if (validCapture(chosenOne,x,y)){
		        			return true;
		        		}
		        		else {
		        			return false;
		        		}
		            }
		        	else if (hasMoved == false){ //
		        		if ((validMove(chosenOne,x,y)) || (validCapture(chosenOne,x,y))){
		        			return true;
		        		}
		    	        else {
		    	        	return false;
		    	        }
		        	}
		        	else { //has moved is true
		        		return false;
		        	}
        		}
        		else {
        			return false;
        		}
        	}
        }
        return false;
    }
    public void select(int x, int y){
    	if (arrayP[x][y] != null){ //clicking first piece
    		chosenOne = arrayP[x][y];
    	}
    	else if (pieceExists(chosenOne)){ //clicking the spot
    		chosenOne.move(x, y);
    		changeHappened = true;
    		hasMoved = true;
    	}
    }
    
    private int fireNum(){
    	fireNum = 0;
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if ((pieceAt(i,j) != null) && (pieceAt(i,j).isFire()))  {
            		fireNum += 1;
            	}
            }
    	}
    	return fireNum;
    }
    
    private int waterNum(){
    	waterNum = 0;
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if ((pieceAt(i,j) != null) && (pieceAt(i,j).isFire() == false)){
            		waterNum += 1;
            	}
            }
    	}
    	return waterNum;
    }

    private boolean validCapture(Piece p, int x, int y){ //see if Piece p can move to (x,y))
        if ((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0)){
	        if (pieceExists(p)){    
        		if (arrayP[xpos(p)][ypos(p)].isKing()){ //KING PIECE
	                //TWO PLACE, CHECK "JUMPED" SPOT TO SEE IF ENEMY PIECE
	                if ((((x - xpos(p) == 2)) || ((xpos(p) - x == 2))) && (((y - ypos(p) == 2)) ||  ((ypos(p) - y == 2)))) {// up or down 2; left or right 2
	                	if (arrayP[x][y] == null){
	                		if (y > ypos(p)){
	                			if (x > xpos(p)){ //check up 1, right 1
	                				if ((arrayP[xpos(p)+1][ypos(p)+1] != null) && (arrayP[xpos(p)+1][ypos(p)+1].side() != side)){
	                					return true;
	                				}
	                				return false;
	                			}
	                			else{ //check up 1, left 1
	                				if ((arrayP[xpos(p)-1][ypos(p)+1] != null) && (arrayP[xpos(p)-1][ypos(p)+1].side() != side)){
	                					return true;
	                				}
	                				return false;
	                			}
	                		}
	                		else {
	                			if (x > xpos(p)){ //check down 1, right 1
	                				if ((arrayP[xpos(p)+1][ypos(p)-1] != null) && (arrayP[xpos(p)+1][ypos(p)-1].side() != side)){
	                					return true;
	                				}
	                				return false;
	                			}
	                			else{ //check down 1, left 1
	                				if ((arrayP[xpos(p)-1][ypos(p)-1] != null) && (arrayP[xpos(p)-1][ypos(p)-1].side() != side)){
	                					return true;
	                				}
	                				return false;
	                			}
	                		}
	                    }
	                	else { // there's something two away, not null
	                		return false;
	                	}
	                }
	                else {
	                	return false;
	                }
	            }
	            else { // not a king piece
	            	if (side == 0) {//fireside 
		            	if ((((x - xpos(p) == 2)) || ((xpos(p) - x == 2))) && (((y - ypos(p) == 2)))) {// up 2; left or right 2
		                	if (arrayP[x][y] == null){
		               			if (x > xpos(p)){ //check up 1, right 1
		               				if ((arrayP[xpos(p)+1][ypos(p)+1] != null) && (arrayP[xpos(p)+1][ypos(p)+1].side() != side)){
		               					return true;
		               				}
		               				return false;
		               			}
		               			else{ //check up 1, left 1
		               				if ((arrayP[xpos(p)-1][ypos(p)+1] != null) && (arrayP[xpos(p)-1][ypos(p)+1].side() != side)){
		               					return true;
		               				}
		               				return false;
		               			}
		                    }
		                	else { // there's something two away, not null
		                		return false;
		                	}
		                }
	            	}
	            	else { //waterside
	            		if ((((x - xpos(p) == 2)) || ((xpos(p) - x == 2))) && (((ypos(p) - y == 2)))) {// down 2; left or right 2
		                	if (arrayP[x][y] == null){
		               			if (x > xpos(p)){ //check down 1, right 1
		               				if ((arrayP[xpos(p)+1][ypos(p)-1] != null) && (arrayP[xpos(p)+1][ypos(p)-1].side() != side)){
		               					return true;
		               				}
		               				return false;
		               			}
		               			else{ //check down 1, left 1
		               				if ((arrayP[xpos(p)-1][ypos(p)-1] != null) && (arrayP[xpos(p)-1][ypos(p)-1].side() != side)){
		               					return true;
		               				}
		               				return false;
		               			}
		                    }
		                	else { // there's something two away, not null
		                		return false;
		                	}
		                }
	            	}
	            }
        	}
        }
        else { //not within bounds
        	return false;
        }
        return false;
    }
    
    private boolean validMove(Piece p, int x, int y){ //see if Piece p can move to (x,y))
        if ((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0)){
        	if (pieceExists(p)) {
	            if (arrayP[xpos(p)][ypos(p)].isKing()){ //KING PIECE
	                //ONE PLACE, JUST CHECK IF EMPTY
	            	if ((((x - xpos(p) == 1)) || ((xpos(p) - x == 1))) && (((y - ypos(p) == 1)) ||  ((ypos(p) - y == 1)))) {// up or down 1; left or right 1
	                	if (arrayP[x][y] == null){
	                    	return true;
	                    }
	                    return false;
	                }
	            }
	            else { // not a king piece
	            	if (side == 0){ //fireside
		            	if ((((x - xpos(p) == 1)) || ((xpos(p) - x == 1))) && (((y - ypos(p) == 1)))) {// up 1; left or right 1
		                	if (arrayP[x][y] == null){
		                    	return true;
		                    }
		                    return false;
		                }
	            	}
	            	else { //waterside
	            		if ((((x - xpos(p) == 1)) || ((xpos(p) - x == 1))) && (((ypos(p) - y == 1)))) {// down 1; left or right 1
		                	if (arrayP[x][y] == null){
		                    	return true;
		                    }
		                    return false;
		                }
	            	}
	            }
        	}
        }
        else { //not within bounds
        	return false;
        }
        return false;
    }
    
    private int xpos (Piece p){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (arrayP[i][j] == p){
                    return i;
                }
            }
        }
        return 2; 
    }
    
    private boolean pieceExists(Piece p){
    	if (p==null) {
    		return false;
    	}
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (arrayP[i][j] == p){
                    return true;
                }
            }
        }
        return false;
    }
    
    private int ypos (Piece p){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (arrayP[i][j] == p){
                    return j;
                }
            }
        }
        return 2;
    }
    public void place(Piece p, int x,int y){
        if (((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0)) && (p != null)){
        	for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                	if (arrayP[i][j] == p){
                		arrayP[i][j] = null;
                	}
                }
        	}
        	arrayP[x][y] = p;
        } 
    }
    public Piece remove(int x, int y){
        if (x > 7 || y > 7 || x < 0 || y < 0){
            System.out.print("Selected spot out of bounds");
            return null;
        }
        else if (arrayP[x][y] == null){
            System.out.print("No piece to remove");
            return null;
        }
        else {
            Piece p = arrayP[x][y];
            arrayP[x][y] = null;
            changeHappened = true;
            return p;
        }
    }
    
    public boolean canEndTurn(){
        if (changeHappened){ //either a piece moved or captured 
            return true;
        }
        else {
            return false;
        }
    }
    public void endTurn(){
        side = 1 - side; //switches side
        changeHappened = false;
        hasMoved = false;
        chosenOne.doneCapturing();
        chosenOne = null;
    }    
    public String winner(){
    	if ((fireNum() == waterNum()) && (waterNum() == 0)){
    		return "No one";
    	}
    	else if (fireNum() == 0){
    		return "Water";
    	}
    	else if (waterNum() == 0){
    		return "Fire";
    	}
    	else{
    		return null;
    	}
    }
}
