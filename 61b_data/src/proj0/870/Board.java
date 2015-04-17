package proj0;

public class Board {
	private Piece[][] pieceLocations = new Piece[8][8];
	private boolean fireTurn = true; //fire team always starts
	private boolean moved = false;  
	private Piece alreadySelected = null;
	private int alreadySelectedx = 0; 
	private int alreadySelectedy = 0; 
	private boolean alreadyCaptured = false; 
	private boolean canCaptureMore = false; 
	
	private static void drawBoard(Board b) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece current = b.pieceLocations[i][j]; 
                if (current!= null){
                	if (current.isFire()){
                		if (current.isShield()){
                			if(current.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		} else if (current.isBomb()){
                			if(current.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		} else {
                			if(current.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else {
                		if (current.isShield()){
                			if(current.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		} else if (current.isBomb()){
                			if(current.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		} else {
                			if(current.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }     		}
	
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false); 
        drawBoard(b);
        boolean gameOver = false;
 
        while(!gameOver) { 
        	drawBoard(b);
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xSelected = (int) x; 
                int ySelected = (int) y; 
                System.out.println(xSelected);
                System.out.println(ySelected); 
                if (b.canSelect(xSelected, ySelected)){  
                	b.select(xSelected, ySelected);  
                }
        	}
            if (StdDrawPlus.isSpacePressed()){
            	System.out.println("space"); 
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            } 
           
            gameOver = b.canEndGame();
            StdDrawPlus.show(25); //adjust seconds
        }
        System.out.print(b.winner()); 
    }
	
	public Board (boolean shouldBeEmpty){
		if (shouldBeEmpty){
			return; 
		} else {
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if ((i + j) % 2 == 0) {
	                	if (j == 0){
	                		pieceLocations[i][j] = new Piece (true, this, i, j, "pawn"); 
	                	}
	                	if (j == 1){
	                		pieceLocations[i][j] = new Piece (true, this, i, j, "shield"); 
	                	}
	                	if (j == 2){
	                		pieceLocations[i][j] = new Piece (true, this, i, j, "bomb");
	                	}
	                	if (j == 7){
	                		pieceLocations[i][j] = new Piece (false, this, i, j, "pawn"); 
	                	}
	                	if (j == 6){
	                		pieceLocations[i][j] = new Piece (false, this, i, j, "shield");
	                	}
	                	if (j == 5){
	                		pieceLocations[i][j] = new Piece (false, this, i, j, "bomb");
	                	}
	                }
	            }
			}
		}
	}
	
	public Piece pieceAt (int x, int y){//check when moving 
		if (x>7 || y>7){
			return null; 
		} else {
			return pieceLocations[x][y]; 
		}
	}
	
	/**
	 * for select...
	 * DONE: ability to choose another piece if have not moved
	 * fix: make sure piece cannot move more after it has already moved (unless it can capture another piece...) 
	 * implement: multi-capture ability 
	 */
	
	public boolean canSelect(int x, int y){
		if (alreadySelected == null){
			if (pieceAt(x,y) == null){
				return false;
			} else if (!fireTurn && pieceAt(x,y).isFire()){
				return false; 
			} else if (fireTurn && !pieceAt(x,y).isFire()){
				return false; 
			} else {
				return true; 
			}
		} else { //alreadySelected is a piece (because moved, keeps hitting first loop 
			if (moved){
				if (alreadyCaptured) { //multi-capture 
					System.out.println("hit multi-capture"); 
					boolean one = canMove(x+2, y+2) || canMove(x-2, y-2); 
					boolean two = canMove(x+2, y-2) || canMove(x-2, y+2);
					System.out.println(one||two);
					canCaptureMore = one || two; 
					if (canCaptureMore){
						moved = false;  
					}
					return canCaptureMore; 
				} else {
					return false;
				}
			} else if(!moved && alreadyCaptured && !canCaptureMore){
				return false; 
			} else {
				if (pieceAt(x,y) != null){
					if (!fireTurn && pieceAt(x,y).isFire()){
						return false; 
					} else if (fireTurn && !pieceAt(x,y).isFire()){
						return false; 
					} else {
						return true;
					}
				} else {
					return canMove(x,y); 
				}
			}	
		}
	}
	
	private boolean canMove(int x, int y){ //helper function for movement 
		if (x > 7 || x < 0 || y > 7 || y < 0){ //double check just in case
			return false; 
		}
		int deltax = x - alreadySelectedx; 
		int deltay = y - alreadySelectedy;
		if (!alreadySelected.isKing() && alreadySelected.isFire() && (deltay) < 0){
			return false; //if not crowned, Fire can only move in positive y direction 
		}else if (!alreadySelected.isKing() && !alreadySelected.isFire() && (deltay) > 0){
			return false; //if not crowned, Water can only move in negative y direction
		}else if (Math.abs(deltax) != Math.abs(deltay)){
			return false; 
		}else if (Math.abs(deltax) == 2 && Math.abs(deltay) == 2 && pieceLocations[alreadySelectedx + (deltax/2)][alreadySelectedy + (deltay/2)] == null){
			return false; //if moving more than 1 space and no other piece in between new space and old space (can capture) 
		}else {
			return true; 
		}
	}
		
	public void select (int x, int y){
		if (pieceLocations[x][y] != null && !moved){
			alreadySelected = pieceLocations[x][y];
			alreadySelectedx = x; //keeps track of current selected piece's location 
			alreadySelectedy = y; 
		} else {
			alreadySelected.move(x, y);//assuming moving to x and y is valid 
			alreadySelectedx = x; 
			alreadySelectedy = y; 
			alreadyCaptured = alreadySelected.hasCaptured(); 
			canCaptureMore = false; 
			if (alreadySelected.hasCaptured()){//makes sure capturing is done
				alreadySelected.doneCapturing(); 
			}
			moved = true; //works 
			System.out.println(moved); 
			
		}
		
		System.out.println("select method hit");
		}
	//}
		//assumes canSelect is true
		//check if select has already happened, which pieces to move/capture...
		//piece.move...
			
	public void place (Piece p, int x, int y){
		if (p == null){ 
			return; 
		} else {
			pieceLocations[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		if (x > 7 || x < 0 || y > 7 || y < 0 ){
			return null; 
		} else {
			Piece removed = pieceLocations[x][y]; 
			pieceLocations[x][y] = null; 
			return removed; 
		}
	}
	
	public boolean canEndTurn(){
		return moved; //class variable that keeps track if players has moved 
	}
	
	private boolean canEndGame(){
		boolean noFire = true; 
		boolean noWater = true; 
		boolean empty = true; 
	    for (int i = 0; i < 7; i++) {
	            for (int j = 0; j < 7; j++) {
	            	if (pieceAt(i,j) != null){
	            		empty = false;
	            		if (!pieceLocations[i][j].isFire()){
	            			noWater = false; 
	            		}
	            		if (pieceLocations[i][j].isFire()){
	            			noFire = false; 
	            		}
	            	}
	            }
	    }  
	    if (empty){
	    	return true;  
	    }else if(!noFire && noWater){//only fire left
	    	return true; 
	    }else if(noFire && !noWater){//only water left
	    	return true; 
	    }else {
	    	return false; 
	    }
	}
	
	public void endTurn(){
		fireTurn = !fireTurn;
		System.out.println("turn ended");
		moved = false;
		alreadySelected = null; //reverts selected piece back to null and non-existent coordinates 
		alreadySelectedx = 10; 
		alreadySelectedy = 10; 
	}
	
	public String winner(){
		boolean noFire = true; 
		boolean noWater = true; 
		boolean empty = true; 
	    for (int i = 0; i < 7; i++) {
	            for (int j = 0; j < 7; j++) {
	            	if (pieceLocations[i][j] != null){
	            		empty = false;
	            		if (!pieceLocations[i][j].isFire()){
	            			noWater = false; 
	            		}
	            		if (pieceLocations[i][j].isFire()){
	            			noFire = false; 
	            		}
	            	}
	            }
	    }  
	    if (empty){
	    	return "No one"; 
	    }else if(!noFire && !noWater){
	    	return null; 
	    }else if(!noFire && noWater){
	    	return "Water"; 
	    }else{
	    	return "Fire"; 
	    }
	}
	

}