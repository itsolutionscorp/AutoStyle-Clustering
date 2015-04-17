public class Board{

	private Piece[][] pieces = new Piece[8][8];
	private boolean fireTurn = true;
	private boolean shouldBeEmpty;
    private boolean hasSelected = false;
    private boolean hasMoved = false;
    private Piece selectedPiece;
    

    private int getX(Piece p){
       for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){
            if(pieces[i][j] == p){
                return i;
            }
        }
       } 
       return 0;
    }

    private int getY(Piece p){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(pieces[i][j] == p){
                    return j;
                }
            }
       }   
       return 0; 
    }
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    public Board(boolean sBE){
    	this.shouldBeEmpty = sBE;

    	//setting up initial board

    	if (!shouldBeEmpty){

	        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
	        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
	        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
	        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

	        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
	        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
	        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
	        pieces[7][1] = new Piece(true, this, 7, 1, "shield");

	        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
	        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
	        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
	        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

	        //water

	        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
	        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
	        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
	        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

	        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
	        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
	        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
	        pieces[6][6] = new Piece(false, this, 6, 6, "shield");

	        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
	        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
	        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
	        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

    	}
    	else{
    		//setting up a special testing board
            

    	}
    }

    public Piece pieceAt(int x, int y){
    	if (outOfBounds(x, y)){
    		return null;
    	}
    	else{
    		return pieces[x][y];
    	}
    }

    public void place(Piece p, int x, int y){
    	if(!outOfBounds(x, y)){
    		pieces[x][y] = p;
    	}
    }

    //when a piece gets removed, its x and y get set to -1, and piece[x][y]
    //gets set to null.
    public Piece remove(int x, int y){
    	if(outOfBounds(x, y)){
    		System.out.println("(" + x + "," + y + ") is out of bounds");
    		return null;
    	}
    	if(pieceAt(x, y) == null){
    		System.out.println("No piece at " + "(" + x + "," + y + ")");
    		return null;
    	}
    	else{
    		Piece ret = pieceAt(x, y);
    		pieces[x][y] = null;
    		return ret;
    	}
    }


    public boolean canSelect(int x, int y){
    	if(!outOfBounds(x, y)){
    	//handling a piece
	    	if(pieces[x][y] != null){
	    		if(pieces[x][y].isFire() == fireTurn){
	    			if(hasSelected == false && hasMoved == false){
	    				return true;
	    			}
	    			if(hasSelected == true && hasMoved == false){
	    				return true;
	    			}
	    		}
	    	}
	    //handling an empty square
	    	else{
	    		if(hasSelected == true && hasMoved == false){
	    			return validMove(getX(selectedPiece), getY(selectedPiece), x, y);
	    		}
	    		else if(hasSelected == true){
	    			if (selectedPiece.hasCaptured()){
	    				if(validMove(getX(selectedPiece), getY(selectedPiece), x, y)){
	    					return true;
	    				}
	    				return false;
	    			}

	    			return false;
	    		}
	    		return false;
	    	}
    	}

    	return false;
    }




    public void select(int x, int y){
        //assumes canSelect(x,y) returns true
	    //selecting a PIECE
        if(pieces[x][y] != null){
	   	   hasSelected = true;
	       selectedPiece = pieceAt(x, y);
        }
        else{
            //highlight the square
            if(selectedPiece != null){
                selectedPiece.move(x, y);
                hasMoved = true;
            }
        }
	}

    private boolean validMove(int xi, int yi, int xf, int yf){
        if(pieceAt(xi, yi) == null){
            return false;
        }
    	if(!pieceAt(xi, yi).isKing()){
            //add checks for if there's going to be a capture
            if(pieceAt(xi, yi).isFire() && !pieceAt(xi, yi).hasCaptured()){
                return validFireMove(xi, yi, xf, yf) || validFireCapture(xi, yi, xf, yf);
            }

            if(!pieceAt(xi, yi).isFire() && !pieceAt(xi, yi).hasCaptured()){
                return validWaterMove(xi, yi, xf, yf) || validWaterCapture(xi, yi, xf, yf);
            }

            if(pieceAt(xi, yi).isFire() && pieceAt(xi, yi).hasCaptured()){
                return validFireCapture(xi, yi, xf, yf);
            }

            if(!pieceAt(xi, yi).isFire() && pieceAt(xi, yi).hasCaptured()){
                return validWaterCapture(xi, yi, xf, yf);
            }


        }
        else{
            if(!pieceAt(xi, yi).isFire()  && !pieceAt(xi, yi).hasCaptured()){
                return validKingMove(xi, yi, xf, yf) || validWaterKingCapture(xi, yi, xf, yf);
            }
            else if(!pieceAt(xi, yi).isFire()  && pieceAt(xi, yi).hasCaptured()){
                return validWaterKingCapture(xi, yi, xf, yf);
            }
            else{
                if(!pieceAt(xi, yi).hasCaptured()){
                    return validKingMove(xi, yi, xf, yf) || validFireKingCapture(xi, yi, xf, yf);
                }
                else{
                    return validFireKingCapture(xi, yi, xf, yf);
                }
            }
        }
        return false;
    }


    private boolean validFireMove(int xi, int yi, int xf, int yf){
        if(xf == (xi - 1) && yf == (yi + 1)){
            return true;
        }
        if(xf == (xi + 1) && yf == (yi + 1)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validWaterMove(int xi, int yi, int xf, int yf){
        if(xf == (xi - 1) && yf == (yi - 1)){
            return true;
        }
        if(xf == (xi + 1) && yf == (yi - 1)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validWaterCapture(int xi, int yi, int xf, int yf){

        if (xf > xi){
            if(pieceAt(xi + 1, yi - 1) == null){
                return false;
            }
            if((xi + 2) != xf){
                return false;
            }
            if((yi - 2) != yf){
                return false;
            }
            if(!pieceAt(xi+1, yi-1).isFire()){
                return false;
            }
            if(pieceAt(xf, yf) == null){
                return true;
            }

        }
        if(xf < xi){
            if(pieceAt(xi - 1, yi - 1) == null){
                return false;
            }
            if((xi - 2) != xf){
                return false;
            }
            if((yi - 2) != yf){
                return false;
            }
            if(!pieceAt(xi-1, yi-1).isFire()){
                return false;
            }
            if(pieceAt(xf, yf) == null){
                return true;
            }
        }

        return false;  
    }

    private boolean validFireCapture(int xi, int yi, int xf, int yf){
      if (xf > xi){

            if(pieceAt(xi + 1, yi + 1) == null){
                return false;
            }
            if((xi + 2) != xf){
                return false;
            }
            if((yi + 2) != yf){
                return false;
            }
            if(pieceAt(xi+1, yi+1).isFire()){
                return false;
            }
            if(pieceAt(xf, yf) == null){
                return true;
            }

        }

        if(xf < xi){

            if(pieceAt(xi - 1, yi + 1) == null){
                return false;
            }
            if((xi - 2) != xf){
                return false;
            }
            if((yi + 2) != yf){
                return false;
            }
            if(pieceAt(xi - 1, yi+1).isFire()){
                return false;
            }
            if(pieceAt(xf, yf) == null){
                return true;
            }
        }

        return false;    
    }

    private boolean validKingMove(int xi, int yi, int xf, int yf){ 

        if(xf == (xi - 1) && yf == (yi + 1)){
            return true;
        }
        if(xf == (xi + 1) && yf == (yi + 1)){
            return true;
        }
        if(xf == (xi - 1) && yf == (yi - 1)){
            return true;
        }
        if(xf == (xi + 1) && yf == (yi - 1)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validWaterKingCapture(int xi, int yi, int xf, int yf){

        //going right
       if (xf > xi){
            //going down
            if(yf < yi){

                if(pieceAt(xi + 1, yi - 1) == null){
                    return false;
                }
                if((xi + 2) != xf){
                    return false;
                }
                if((yi - 2) != yf){
                    return false;
                }
                if(!pieceAt(xi+1, yi-1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
            //going up
            if(yf > yi){

                if(pieceAt(xi + 1, yi + 1) == null){
                    return false;
                }
                if((xi + 2) != xf){
                    return false;
                }
                if((yi + 2) != yf){
                    return false;
                }
                if(!pieceAt(xi+1, yi+1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
            
        }

        //going left
        if(xf < xi){
            //going down
            if(yf < yi){

                if(pieceAt(xi - 1, yi - 1) == null){
                    return false;
                }
                if((xi - 2) != xf){
                    return false;
                }
                if((yi - 2) != yf){
                    return false;
                }
                if(!pieceAt(xi-1, yi-1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
            //going up
            if(yf > yi){

                if(pieceAt(xi - 1, yi + 1) == null){
                    return false;
                }
                if((xi - 2) != xf){
                    return false;
                }
                if((yi + 2) != yf){
                    return false;
                }
                if(!pieceAt(xi-1, yi+1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
        } 
        return false;
    }

    private boolean validFireKingCapture(int xi, int yi, int xf, int yf){
         //going right
       if (xf > xi){
            //going down
            if(yf < yi){

                if(pieceAt(xi + 1, yi - 1) == null){
                    return false;
                }
                if((xi + 2) != xf){
                    return false;
                }
                if((yi - 2) != yf){
                    return false;
                }
                if(pieceAt(xi+1, yi-1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
            //going up
            if(yf > yi){

                if(pieceAt(xi + 1, yi + 1) == null){
                    return false;
                }
                if((xi + 2) != xf){
                    return false;
                }
                if((yi + 2) != yf){
                    return false;
                }
                if(pieceAt(xi+1, yi+1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
            
        }
        
        //going left
        if(xf < xi){
            //going down
            if(yf < yi){

                if(pieceAt(xi - 1, yi - 1) == null){
                    return false;
                }
                if((xi - 2) != xf){
                    return false;
                }
                if((yi - 2) != yf){
                    return false;
                }
                if(pieceAt(xi-1, yi-1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
            //going up
            if(yf > yi){

                if(pieceAt(xi - 1, yi + 1) == null){
                    return false;
                }
                if((xi - 2) != xf){
                    return false;
                }
                if((yi + 2) != yf){
                    return false;
                }
                if(pieceAt(xi-1, yi+1).isFire()){
                    return false;
                }
                if(pieceAt(xf, yf) == null){
                    return true;
                }
            }
        }

        return false;
    }


    public boolean canEndTurn(){

        if(selectedPiece == null){
            return hasMoved;
        }
        else{
    	   return (hasMoved || selectedPiece.hasCaptured());
        }
    }

    public void endTurn(){
        

        selectedPiece.doneCapturing();
        

        selectedPiece = null;
        hasMoved = false;
    	if (fireTurn){
    		fireTurn = false;
    	}
    	else{
    		fireTurn = true;
    	}
    }

    public String winner(){
    	int firePieces = 0;
    	int waterPieces = 0;
    	for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null){
	            	if(pieces[i][j].isFire()){
	            		firePieces += 1;
	            	}
	            	else{
	            		waterPieces += 1;
	            	}
            	}
            }
        }

        if(firePieces == 0 && waterPieces > 0){
        	return "Water";
        }
        if(firePieces > 0 && waterPieces == 0){
        	return "Fire";
        }
        if(firePieces == 0 && waterPieces == 0){
        	return "No one";
        }
        else{
        	return null;
        }
    }

    private boolean outOfBounds(int x, int y){
    	if (x < 0 || y < 0 || x > 7 || y > 7){
    		return true;
    	}
    	return false;
    }
    private String getPieceImage(Piece p){
    	String ret = "img/";

        if(p.isShield()){
            ret += "shield-";
        }
        else if(p.isBomb()){
            ret += "bomb-";
        }
        else{
            ret += "pawn-";
        }
    	if(p.isFire()){
    		ret += "fire";
    	}
    	else{
    		ret += "water";
    	}
    	if(p.isKing()){
    		ret += "-crowned";
    	}
    	return ret + ".png";
    }

    private void drawBoard(int N) {
 

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if ((i + j) % 2 == 0){

                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if(selectedPiece!=null){
                    if(getX(selectedPiece) == i && getY(selectedPiece) == j){
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                    }
                }


                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if(pieces[i][j] != null){
                		String pieceImage = getPieceImage(pieces[i][j]);
                    	StdDrawPlus.picture(i + .5, j + .5, pieceImage, 1, 1);
            	}
            }
        }
    }

    public static void main(String[] args) {
    	Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        int x;
        int y;
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                x = (int) StdDrawPlus.mouseX();
                y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x, y)){
                    b.select(x, y);
                } 

            }

            if (StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }  
            b.winner();
            StdDrawPlus.show(100);
        }
    }

}