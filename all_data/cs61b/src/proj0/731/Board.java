public class Board {
	/** Location of pieces. */
   
    private Piece[][] pieceType;
    private boolean turn = true;
    private boolean hasSelected;
    private int y0;
    private int x0;
    private int yJumpTo;
    private int xJumpTo;
    private boolean move;
    private Piece movedPiece;
    //private static Board b;
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(int N, Piece[][] pieceType, Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                
                if( pieceType[i][j] != null){
                	Piece p = pieceType[i][j];
    	 			String team = "water";
    				 if(p.isFire()){
    	 				team = "fire";
    				 }
                     String type = "pawn";
                     if(p.isShield()){
                        type = "shield";
                     }else if(p.isBomb()){
                        type = "bomb";
                     }
                     
                     if(p.isKing()){
                        StdDrawPlus.picture(i + .5, j + .5, "img/" +type+ "-" + team + "-crowned" +".png", 1, 1);
                     }else{
					StdDrawPlus.picture(i + .5, j + .5, "img/" +type+ "-" + team + ".png", 1, 1);
                    }
                }
            }
        }
        if(b.hasSelected){
            int x = b.x0;
            int y = b.y0;
             Piece p = b.pieceAt(x,y);
             String team = "water";
             if(p != null && p.isFire()){
                team = "fire";
             }
             StdDrawPlus.filledSquare(x + .5, y + .5, .5);

        if(p != null){
            
          String type = "pawn";
                     if(p.isShield()){
                        type = "shield";
                     }else if(p.isBomb()){
                        type = "bomb";
                     }
            if(p.isKing()){
                StdDrawPlus.picture(x + .5, y + .5, "img/" +type+ "-" + team + "-crowned" +".png", 1, 1);
            }else{
                  StdDrawPlus.picture(x + .5, y + .5, "img/" +type+ "-" + team + ".png", 1, 1);
            }

        }
        }
    }

   public Board(boolean empty){
   		int N = 8;
        pieceType = new Piece[N][N];
    	if(!empty) { 
    		int[] k = {0,1,2,5,6,7};
    		
			
    		for(int i:k){
    			 int x = 1;
    			for(int j = 0; j<= 3; j++){
    				if(i%2 == 0){
    					x = 0;
    				}
    				if(i==0){
                		pieceType[x+2*j][i] = new Piece(true, this, x+2*j,i , "pawn");
                	}
                	else if( i == 1){
                		pieceType[x+2*j][i] = new Piece(true, this, x+2*j,  i,"shield");
                	}
                	else if(i== 2){
                		pieceType[x+2*j][i] = new Piece(true, this, x+2*j, i, "bomb");
                	}
                	else if( i== 7){
                		pieceType[x+2*j][i] = new Piece(false, this,  x+2*j,i, "pawn");
                	}
                	else if(i == 6){
                		pieceType[x+2*j][i] = new Piece(false, this,  x+2*j,i, "shield");
                	}
                	else if(i== 5){
                		pieceType[x+2*j][i] = new Piece(false, this,  x+2*j, i,"bomb");
                	}              	
    				
    			}
    		}

    		

    	}
    } 

    public Piece pieceAt(int x, int y){
        System.out.println("checked For Piece at " +x + ", " +y);
    	if(x<0||y<0||x>7||y>7){
    		return null;
    	}
    	Piece piece1 = pieceType[x][y];

        if(piece1!=null){
            String team = "water";
            String type = "Pawn";
            if(piece1.isFire()){
                team = "fire";
            }
            if(piece1.isBomb()){
                type = "Bomb";
            } else if(piece1.isShield()){
                type = "Shield";
            }
            System.out.println("You checked for a piece at "  + x +", " +y +", and you found a " + team + type+". And they are a king? survey says " + piece1.isKing());
        } else {
            System.out.println("checked For Piece at " +x + ", " +y + ", but only found a null there");
        }

    	return piece1;
    }

    public void place(Piece p, int x, int y){

        if(p!=null){
            String team = "water";
            String type = "Pawn";
            if(p.isFire()){
                team = "fire";
            }
            if(p.isBomb()){
                type = "Bomb";
            } else if(p.isShield()){
                type = "Shield";
            }
            System.out.println("You placed a " + team + type+ " at " + x +", " +y + ". And they are a king? survey says " + p.isKing());
        }
        
        if(p != null && x>=0 && x<=7 && y>=0 && y<=7){
    	   pieceType[x][y] = p; 	
        }
    }

    public Piece remove(int x, int y) {
    	if(x<0||y<0||x>7||y>7){
    		return null;
    	}
    	Piece p = pieceType[x][y];
    	pieceType[x][y] = null;
    	return p;
    }

    private boolean validMove(int x, int y, int xI, int yI){

    	// Has no Piece there
    	if(pieceAt(x,y) != null){
    		return false;
    	}
    	// Need to change this for double jump?
    	Piece p = pieceAt(xI,yI);

    	if(x<0||y<0||x>7||y>7){
    		return false;
    	}
    	// If the piece is a king
    	if(p.isKing()){
    		
    		if(Math.abs(xI-x) == 1 && Math.abs(yI- y) == 1 && !p.hasCaptured()){
    			return true;
    		}else if( Math.abs(xI-x) == 2 && Math.abs(yI- y) == 2){
    			int xAvg = (x + xI)/2;
    			int yAvg = (y + yI)/2;
    			Piece hopped = pieceAt(xAvg, yAvg);
    			if(hopped != null && hopped.isFire() != p.isFire()){
    				return true;
    			}
    		}
    	}

    	// If the piece is not a king
    	
    	if(p.isFire()){
    		if(Math.abs(xI-x) == 1 && (y- yI) == 1 && !p.hasCaptured()){
    			
    				return true;
    		}else if( Math.abs(xI-x) == 2 && (y- yI) == 2){
    			
    			int xAvg = (x + xI)/2;
    			int yAvg = (y + yI)/2;
    			Piece hopped = pieceAt(xAvg, yAvg);
    			if(hopped != null && hopped.isFire() != p.isFire()){
    				return true;
    			}
    		}
    	} else {
    		if(Math.abs(xI-x) == 1 && (yI- y) == 1 && !p.hasCaptured()){
    			
    				return true;
    		}else if( Math.abs(xI-x) == 2 && (yI- y) == 2){
    			
    			int xAvg = (x + xI)/2;
    			int yAvg = (y + yI)/2;
    			Piece hopped = pieceAt(xAvg, yAvg);
    			if(hopped != null && hopped.isFire() != p.isFire()){
    				return true;
    			}
    		}
    	}

    	
    	return false;
    }

    public boolean canSelect( int x, int y){
        
    	Piece p = pieceAt(x,y);

    	
    	// there is a piece there
    	if( p != null && turn == p.isFire()){
    		if(!hasSelected){
                System.out.println("Checked if you could Select " + x +", " +y +". And you could.");
    			return true;
    		} else if( !move){
                System.out.println(("Checked if you could Select " + x +", " +y +". And you could."));
    			return true;
    		}
    	} else{ // there is no piece there
            System.out.println("can select" + move +"     " +xJumpTo +"  " +yJumpTo) ;
    		if(hasSelected && !move && validMove(x,y,x0,y0) ){
    			xJumpTo = x;
    			yJumpTo = y;
                System.out.println(("Checked if you could Select " + x +", " +y +". And you could."));
    			return true;

    		} else if(pieceType[x0][y0] != null && pieceType[x0][y0].hasCaptured() && validMove(x,y,x0,y0) ){
                xJumpTo = x;
                yJumpTo = y;
                System.out.println(("Checked if you could Select " + x +", " +y +". And you could."));
    			return true;
    		}
		}

        System.out.println(("Checked if you could Select " + x +", " +y +". And you could not."));
    	return false;
    }

    public void select(int x, int y){
    	System.out.println("You have selected " + x +", " +y);
    	Piece p = pieceAt(x,y);
        System.out.println("selected" +x +", " +y);
    	if(p == null) {
            //int counter = 0;
            //counter++;
    		Piece old =pieceAt(x0,y0);
            System.out.println(x0 + "," +y0);
           // System.out.println(old);

    		old.move(x,y);
    		move = true;
            movedPiece = old;
            
    	}
    	 hasSelected = true;
    	 x0 = x;
    	 y0 = y;
    }

    public boolean canEndTurn(){
        if(move){
    	return true;
    }

    return false;
    }

    public void endTurn(){
    	turn = !turn;
    	hasSelected = false;
    	move = false;
        movedPiece.doneCapturing();
    }

    public String winner(){
        int N = 8;
        int numFire = 0;
        int numWater = 0;
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(pieceType[i][j] != null){
                    if(pieceType[i][j].isFire()){
                        numFire++;
                    }else{
                        numWater++;
                    }
                }
            }
        }

        if(numFire==0 && numWater == 0 ){
            return "No one";
        }else if(numFire == 0){
            return "Water";
        }else if(numWater == 0){
            return "Fire";
        }

        return null;
    }

    public static void main(String[] args) {
       Board b = new Board(false);
       int N = 8;
       StdDrawPlus.setXscale(0, N);
       StdDrawPlus.setYscale(0, N);
       drawBoard(N,b.pieceType,b);
       b.turn = true;
       b.hasSelected = false;
       while(true) {
         	b.winner();
            drawBoard(8,b.pieceType,b);
        	if (StdDrawPlus.mousePressed()) {
        		
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int) x, (int) y)){
                 	b.select((int) x, (int) y);	
               	}
            } 

            if(StdDrawPlus.isSpacePressed()){
         	   if(b.canEndTurn()){
                b.endTurn();
            }
        	}
            StdDrawPlus.show(100);
        }
    }
}