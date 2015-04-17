/* Import statements */

public class Board{

	private static int N = 8;
	private Piece[][] pieces;
	private boolean shouldBeEmpty;

	private boolean isFireTurn;
	//private boolean hasSelected;
	private Piece selectedPiece;
	private boolean hasMoved;
	private boolean hasExploded;
	
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[Board.N][Board.N];
		this.shouldBeEmpty = shouldBeEmpty;
		this.isFireTurn = true;
		//this.hasSelected = false;
		this.hasMoved = false;
		this.selectedPiece = null;
		this.hasExploded = false;

		if(!shouldBeEmpty){
		for(int i = 0; i <= 6; i+=2){
        	pieces[i][0] = new Piece(true, this, i, 0, "pawn");
        	pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
        	pieces[i][2] = new Piece(true, this, i, 2, "bomb");

        	pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb");
        	pieces[i][6] = new Piece(false, this, i, 6, "shield");
        	pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn");
        	}
        }

	}

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board checkersBoard = new Board(false);
       

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {

            if (StdDrawPlus.mousePressed()) {
               	double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(checkersBoard.canSelect((int) x,(int) y)){
                	checkersBoard.select((int) x, (int) y);
                }
            }  

            if (StdDrawPlus.isSpacePressed()){
            	if(checkersBoard.canEndTurn()){
            		checkersBoard.endTurn();
            	}
            }  

            if(checkersBoard.winner() != null){
            	break;
            }

            checkersBoard.drawBoard(N);
            if(!checkersBoard.shouldBeEmpty){
        		checkersBoard.drawPieces();       
        	}
            StdDrawPlus.show(100);
        }
        System.out.println(checkersBoard.winner());
        //System.out.println(checkersBoard.winner());
    }

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                /*if (pieces[i][j]) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }*/
            }
            if(selectedPiece != null){
            	//int xPos = selectedPiece.x;
            	//int yPos = selectedPiece.y;
            	for(int x=0; x <=7; x++){
            		for(int y=0; y<=7; y++){
            			if(pieces[x][y] == selectedPiece){
            				StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            			}
            		}
            	}
            	
            }
        }
    }    

    private void drawPieces(){
    	String imagePath;
    	for (int y = 0; y < Board.N; y++) {
            for (int x = 0; x < Board.N; x++) {
            	if(pieces[x][y] != null){
            		if(pieces[x][y].isShield()){
            			imagePath = getImagePath(pieces[x][y].isKing(), pieces[x][y].isFire(), "shield");
            		}
            		else if(pieces[x][y].isBomb()){
            			imagePath = getImagePath(pieces[x][y].isKing(), pieces[x][y].isFire(), "bomb");
            		}
            		else{
            			imagePath = getImagePath(pieces[x][y].isKing(), pieces[x][y].isFire(), "pawn");
            		}
            		
            		StdDrawPlus.picture(x + 0.5,y + 0.5,imagePath,1,1);
            	}
            }
        }
    }

    private String getImagePath(Boolean isKing, Boolean isFire, String type){
    	if(isFire){
    		if(type.equals("bomb")){
    			if(isKing){
    				return "img/bomb-fire-crowned.png";
    			}
    			else{
    				return "img/bomb-fire.png";
    			}
    		}
    		else if(type.equals("shield")){
    			if(isKing){
    				return "img/shield-fire-crowned.png";
    			}
    			else{
    				return "img/shield-fire.png";
    			}
    		}
    		else{
    			if(isKing){
    				return "img/pawn-fire-crowned.png";
    			}
    			else{
    				return "img/pawn-fire.png";
    			}
    		}
    	}
    	else{
    		if(type.equals("bomb")){
    			if(isKing){
    				return "img/bomb-water-crowned.png";
    			}
    			else{
    				return "img/bomb-water.png";
    			}
    			
    		}
    		else if(type.equals("shield")){
    			if(isKing){
    				return "img/shield-water-crowned.png";
    			}
    			else{
    				return "img/shield-water.png";
    			}
    		}
    		else{
    			if(isKing){
    				return "img/pawn-water-crowned.png";
    			}
    			else{
    				return "img/pawn-water.png";
    			}
    		}
    	}	
    }

	

	public Piece pieceAt(int x, int y){
		if((x>7 || y>7) || (x<0 || y<0)){
			return null;
		}
		else if(pieces[x][y] != null){
			return pieces[x][y];
		}
		else{
			return null;
		}
	}

	public boolean canSelect(int x, int y){
		if(x <= 7 && x >= 0 && y <= 7 && y >=0){
		if(pieces[x][y] != null && isFireTurn == pieces[x][y].isFire()){
			if(hasExploded == false && (selectedPiece == null || (hasMoved == false && selectedPiece.hasCaptured() == false))){
				return true;
			}
			else{
				return false;
			}
		}
		else if(hasExploded == false && selectedPiece != null && pieceAt(x,y) == null && hasMoved == false){
			if(validMove(selectedPiece, x, y)){
				return true;
			}
			else{
				return false;
			}
		}
	}
		return false;
	}

	private boolean validMove(Piece p, int x, int y){
		int xDist = 0;
		int yDist = 0;
		for(int xx=0; xx <=7; xx++){
            		for(int yy=0; yy<=7; yy++){
            			if(pieces[xx][yy] == selectedPiece){
            						xDist = x-xx;
									yDist = y-yy;
            			}
            		}
            	}

		int xDiff = Math.abs(xDist);
		int yDiff = Math.abs(yDist);
		//System.out.println("good validMove: " + x + " " + y);
		if(((isFireTurn && yDist < 0) || (!isFireTurn && yDist > 0)) && !p.isKing()){
			return false;
		}
		else if((xDiff-yDiff != 0) || (xDiff > 2 || xDiff < 1) || (yDiff > 2 || yDiff < 1)){
			return false;
		}
		else if((xDiff == 1) && (yDiff == 1) && pieces[x][y] == null && hasMoved == false && p.hasCaptured() == false){
			
			//System.out.println("wrong way in valid move: " + x + " " + y);
			if((isFireTurn && yDist < 0) || (!isFireTurn && yDist > 0)){
				if(p.isKing()){
					hasMoved = true;
					return true;
				}
				else{
					return false;
				}
			}
			else{
				hasMoved = true;
				return true;
			}
		}
		else if((xDiff == 2) && (yDiff == 2) && pieces[x][y] == null && hasMoved == false){
			//System.out.println("should work in valid move: " + x + " " + y);
			if(xDist < 0 && yDist > 0){
				if(pieces[x+1][y-1] != null && (pieces[x+1][y-1].isFire() != isFireTurn)){
					if(pieceAt(x+2,y-2).isBomb()){
					hasExploded = true;
					}
					return true;
				}
			}
			else if(xDist > 0 && yDist > 0){
				
				if(pieces[x-1][y-1] != null && (pieces[x-1][y-1].isFire() != isFireTurn)){
					if(pieceAt(x-2,y-2).isBomb()){
					hasExploded = true;
					}
					return true;
				}
			}
			else if(xDist > 0 && yDist < 0){
				
				if(pieces[x-1][y+1] != null && (pieces[x-1][y+1].isFire() != isFireTurn)){
					if(pieceAt(x-2,y+2).isBomb()){
					hasExploded = true;
					}
					return true;
				}
			}
			else if(xDist < 0 && yDist < 0){
				
				if(pieces[x+1][y+1] != null && (pieces[x+1][y+1].isFire() != isFireTurn)){
					if(pieceAt(x+2,y+2).isBomb()){
					hasExploded = true;
					}
					return true;
				}
			}
			else{
				return false;
			}
		}
		return false;
	}


	public void select(int x, int y){
		//System.out.println("good select: " + x + " " + y);
		
			if(pieces[x][y] != null){
				selectedPiece = pieces[x][y];

			}
			else{
				selectedPiece.move(x,y);
			}
	}


	public void place(Piece p, int x, int y){
		if(x <= 7 && y <= 7 && x >= 0 && y >= 0 && p!=null){
			pieces[x][y] = p;
			selectedPiece = pieces[x][y];
			}
	}

	public Piece remove(int x, int y){
		if((x>7 || y>7) || (x<0 || y<0)){
			System.out.println("Cannot remove piece. Out of bounds.");
			return null;
		}
		else if(pieces[x][y] == null){
			System.out.println("Cannot remove piece. No piece at indicated space.");
			return null;
		}
		else{
			Piece deletedPiece = pieces[x][y];
			pieces[x][y] = null;
			return deletedPiece;
		}
	}

	public boolean canEndTurn(){
		//System.out.println("a" + hasExploded);
		//System.out.println("b" + hasMoved);
		if(hasExploded 
			|| hasMoved ||
			(selectedPiece != null && 

				 selectedPiece.hasCaptured() == true)){
			return true;
		}
		return false;
	}

	public void endTurn(){
		selectedPiece.doneCapturing();
		//hasSelected = false;
		hasMoved = false;
		hasExploded = false;
		selectedPiece = null;
		isFireTurn = !isFireTurn;
	}

	public String winner(){
		int firePlayers = 0;
		int waterPlayers = 0;
		for(int x=0; x <=7; x++){
            		for(int y=0; y<=7; y++){
            			if(pieces[x][y] != null){
            				if(pieces[x][y].isFire()){
            					firePlayers += 1;
            				}
            				else{
            					waterPlayers +=1;
            				}
            			}
            		}
            	}
        if(firePlayers == 0 && waterPlayers == 0){
        	return "No one";
        }
        else if(firePlayers == 0){
        	return "Water";
        }
        else if(waterPlayers == 0){
        	return "Fire";
        }
   		else{
   			return null;
   		}
	}
}
