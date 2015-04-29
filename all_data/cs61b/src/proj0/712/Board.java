public class Board {
	

	private static Piece [][] pieces;
	private boolean fireTurn;
	private boolean pieceSelected;
	private boolean moved;
	private Piece movedPiece;
	private int x1;
	private int y1;

	
	


	public Board(boolean shouldBeEmpty){
		pieceSelected = false;
		moved = false;
		fireTurn = true;
		

		if (!shouldBeEmpty){
			pieces = new Piece [8][8];
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if ((i+j) % 2 != 0){
						pieces [i][j] = null;
					}
					else if (((i+j) % 2 == 0) && (j<=2)){
						if (j == 0){
							pieces[i][j] = new Piece(true, this, i, j, "pawn");	
						}
						else if (j == 1){
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						}
						else{
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
					}
					else if (((i+j) % 2 == 0) && (j>=5)){
						if (j==7){
							pieces[i][j] = new Piece(false, this, i, j, "pawn");	
						}
						else if (j ==6){
							pieces[i][j] = new Piece(false, this, i, j, "shield");

						}
						else{
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
						}
					}
					else{
						pieces[i][j] = null;
					}


				}
			}


		}
		else
			pieces = new Piece[8][8];

	}


	private void drawBoard(int N) {

	
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else{                 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if((pieces[i][j] == movedPiece)&&(pieceSelected)){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }

             	StdDrawPlus.filledSquare(i + .5, j + .5, .5);


                if(pieces[i][j] != null){
                    if(((pieces[i][j].isShield()) == false) && ((pieces[i][j].isBomb()) == false)&&(pieces[i][j].isKing())&&(pieces[i][j].isFire())){

                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    }
                    else if ((pieces[i][j].isShield() == true)&&(pieces[i][j].isKing())&&(pieces[i][j].isFire())){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    }
                    else if ((pieces[i][j].isBomb() == true)&&(pieces[i][j].isKing())&&(pieces[i][j].isFire())){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    }
                    else if (((pieces[i][j].isShield()) == false) && ((pieces[i][j].isBomb()) == false)&&(pieces[i][j].isKing())&&(pieces[i][j].isFire() == false)){

                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                    else if ((pieces[i][j].isShield() == true)&&(pieces[i][j].isKing())&&(pieces[i][j].isFire() == false)){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    }
                    else if ((pieces[i][j].isBomb() == true)&&(pieces[i][j].isKing())&&(pieces[i][j].isFire() == false)){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    }
	            	else if ((pieces[i][j].isFire()) && (pieces[i][j].isBomb() == true)){
	            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	            	}
	            	else if(((pieces[i][j].isFire() == false) && (pieces[i][j].isBomb()))){
	            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	            	}
	            	else if(((pieces[i][j].isFire()) && (pieces[i][j].isShield()) == true)){
	            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	            	}
	            	else if(((pieces[i][j].isFire() == false) && (pieces[i][j].isShield()))){
	            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            	}
	            	else if(((pieces[i][j].isFire()) && (pieces[i][j].isShield()) == false) && ((pieces[i][j].isBomb()) == false)){
	            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	            	}
	            	else if(((pieces[i][j].isFire() == false) && (pieces[i][j].isShield()) == false) && ((pieces[i][j].isBomb()) == false)){
	            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	            	}
	            	
	            }

            }
        }
    }

    public Piece pieceAt(int x, int y){
    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
    		return null;
    	}
    	else if (pieces[x][y] == null){
    		return null;
    	}
    	else{
    		return pieces[x][y];
    	}

    }

    public void place(Piece p, int x, int y){
    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
    		return;
    	}
    	if (p == null){
    		return;
    	}
    	if (this.pieceAt(x,y) != null){
    		moved = true;
    		pieces[x][y] = p;
            x1 = x;
            y1 =y;

    	}
    	else{
    		moved = true;
    		pieces[x][y] = p;
            x1 = x;
            y1 = y;

    	}
    }
    public Piece remove(int x, int y){
    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)){
    		System.out.println("Input is out of bounds.");
    		return null;
    	}
    	if (this.pieceAt(x,y) == null){
    		System.out.println("No piece on this space.");
    		return null;
    	}
    	if ((movedPiece != null)&&(movedPiece.hasCaptured())){

    		Piece deletion = pieces[x][y];
    		pieces[x][y] = null;
    		return deletion;
    	}
        else{
    	Piece removed = pieces[x1][y1];
    	pieces[x1][y1] = null;
    	return removed;
    }
    }

    public boolean canSelect(int x, int y){
    	

    	if(fireTurn){
    		
    		if ((pieceSelected == false) && (pieces[x][y] == null)){
    			return false;
    		}
    		else if ((pieceSelected == true) && (pieces[x][y] == null) && (!this.validMove(x1, y1,x,y))){
    			return false;
    		}
            else if((pieceSelected) && (movedPiece.hasCaptured()) && (this.validMove(x1,y1, x, y)) && (!movedPiece.isBomb())){
                
                return true;
            }

    		else if ((pieceSelected == false) && (pieces[x][y].isFire())){
    			x1 = x;
    			y1 = y;
    			return true;
    		}
    		else if ((this.pieceAt(x,y) == null) && (moved == false) && (this.validMove(x1, y1, x, y))&&(pieceSelected)) {
                moved = true;
    			return true;
    		}
    		
    		else if ((pieceSelected == true)&&(moved == false)&& (pieces[x][y].isFire())){
    			x1 = x;
    			y1 = y;
    			return true;
    		}
    		
    		else{
    			return false;
    		}
    	}
    	else{
    		if ((pieceSelected == false) && (pieces[x][y] == null)){

    			return false;
    		}
    		else if ((pieceSelected == true) && (pieces[x][y] == null) && (!this.validMove(x1,y1,x,y))){
    			return false;
    		}

    		else if ((pieceSelected == false) && (pieces[x][y].isFire() == false)){
    			x1 = x;
    			y1 = y;
    			return true;
    		}
    		
    		else if ((this.pieceAt(x,y) == null) && (moved == false) && (this.validMove(x1, y1, x, y)) && (pieceSelected)) {

                moved = true;
    			return true;
    		}

    		else if((pieceSelected) && (movedPiece.hasCaptured()) && (this.validMove(x1,y1, x, y)) && (!movedPiece.isBomb())){
    			return true;
    		}
    		else if ((pieceSelected == true)&&(moved == false)&& (pieces[x][y].isFire() == false)){
    			
    			return true;
    		}
    		else{
    			return false;
    		}
    		
    	}
    } 

    private boolean validMove(int xi, int yi, int xf, int yf){
    	if (movedPiece.isKing()){

            
	    	if ((this.pieceAt(xf, yf) == null) && ((xf - xi) == 1) && ((yf - yi) == 1)){
	    		return true;
	    	}
	    	else if((this.pieceAt(xf, yf) == null) && ((xi - xf) == 1) && ((yf - yi) == 1)){
	    		return true;
	    	}
	    	if ((this.pieceAt(xf, yf) == null) && ((xf - xi) == 1) && ((yi - yf) == 1)){
	    		return true;
	    	}
	    	else if((this.pieceAt(xf, yf) == null) && ((xi - xf) == 1) && ((yi - yf) == 1)){
	    		return true;
	    	}
	    	if ((this.pieceAt(xi - 1, yi - 1) != null) || (this.pieceAt(xi + 1, yi + 1) != null)){
    			if ((this.pieceAt(xf, yf) == null)&&(Math.abs(xf - xi) == 2) && (Math.abs(yf-yi) ==2)){
    				return true;
    			}
    			else 
    				return false;
    	}
            if ((this.pieceAt(xi + 1, yi - 1) != null) || (this.pieceAt(xi - 1, yi + 1) != null)){

                if ((this.pieceAt(xf, yf) == null)&&(Math.abs(xf - xi) == 2) && (Math.abs(yf-yi) ==2)){
                    return true;
                }
                else 
                    return false;
        }
   		}
   		if ((!movedPiece.isKing())&&(movedPiece.isFire())){

            if ((movedPiece.hasCaptured())&&(!movedPiece.isBomb())){
                if ((this.pieceAt(xf, yf) == null)&&(Math.abs(xf - xi) == 2) && ((yf-yi) ==2)){
                    
                    return true;
                }
            }
   			if ((this.pieceAt(xf, yf) == null) && ((xf - xi) == 1) && ((yf - yi) == 1)){
	    		return true;
	    	}
	    	else if((this.pieceAt(xf, yf) == null) && ((xi - xf) == 1) && ((yf - yi) == 1)){
	    		return true;
	    	}
          
	    	if ((this.pieceAt(xi - 1, yi + 1) != null) || (this.pieceAt(xi + 1, yi + 1) != null)){
    	    		if ((this.pieceAt(xf, yf) == null)&&(Math.abs(xf - xi) == 2) && ((yf-yi) ==2)){
    	    			return true;
    	    		}
    	    		else 
    	    			return false;
    	}
   		}

   		if ((!movedPiece.isKing())&&(!movedPiece.isFire())){

            
   			if ((this.pieceAt(xf, yf) == null) && ((xi - xf) == 1) && ((yi - yf) == 1)){
	    		return true;
	    	}
	    	else if((this.pieceAt(xf, yf) == null) && ((xf - xi) == 1) && ((yi - yf) == 1)){
	    		return true;
	    	}
            if ((moved == true)&&(movedPiece.hasCaptured())&&(!movedPiece.isBomb())){
                if ((this.pieceAt(xf, yf) == null)&&(Math.abs(xf - xi) == 2) && ((yi-yf) ==2)){
                    return true;
                }
            }
	    	if ((this.pieceAt(xi + 1, yi - 1) != null) || (this.pieceAt(xi - 1, yi - 1) != null)){
    	    	if ((this.pieceAt(xf, yf) == null)&&(Math.abs(xf - xi) == 2) && ((yi-yf) ==2)){
    	    		return true;
    	   		}
    	 		else 
        			return false;
	    	}
   		}
    	return false;
    }

    public void select(int x, int y){

            
    	if (this.pieceAt(x, y) != null){
    		pieceSelected = true;
    		movedPiece = pieces[x][y];
    		moved = false;
    		System.out.println("-> piece at ("+ x +", "+ y + ") selected");
    	}
   		else if((pieceSelected)&&(this.pieceAt(x,y) == null)&& (this.validMove(x1,y1, x, y))) {
            if ((movedPiece.hasCaptured())&&(this.validMove(x1, y1, x, y))){
                System.out.println("x1 is: " + x1 + " // " + "y is: " + y1);
                System.out.println("x is: " + x + " // " + "y is: " + y);
                pieces[x1][y1].move(x,y);
                System.out.println("--> piece moved to (" + x + ", " + y + ")");
                moved = true;
                x1 = x;
                y1 = y;

            }
	   		pieces[x1][y1].move(x,y);
	   		System.out.println("--> piece moved to (" + x + ", " + y + ")");
               			
            moved = true;
            x1 = x; 
            y1 = y;
            System.out.println("x is: " + x + " // " + "y is: " + y);
    }
   	}




    public boolean canEndTurn(){

    	if (moved){

    		return true;
    	}
    	else{
    		return false;
    	}

    }

    public void endTurn(){
    	
    	if (canEndTurn()){
	    	if (fireTurn){
	    		movedPiece.doneCapturing();
	    		moved = false;
    			pieceSelected = false;
    			movedPiece = null;

	    		System.out.println("* Water player's turn");
	    		fireTurn = false;
	    	}
	    	else if (!fireTurn){
	    		movedPiece.doneCapturing();
	    		moved = false;
    			pieceSelected = false;
    			movedPiece = null;
	    		System.out.println("* Fire player's turn");
	    		fireTurn = true;
	    	}
    	}
    }


    

    public String winner(){
    	int fireCount = 0;
    	int waterCount = 0;
    	for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieces[i][j] != null){
					if(pieces[i][j].isFire() == true){
						fireCount++;
					}
					else if (pieces[i][j].isFire() == false){
						waterCount++;
					}
				}

			}
		}
		if ((fireCount > 0) && (waterCount == 0)){
			return "Fire";
		}
		else if ((fireCount == 0) && (waterCount > 0)){
			return "Water";
		}
		else if ((fireCount > 0) && (waterCount > 0)){
			return null;
		}
        else if ((fireCount == waterCount)){
            return "No one";
        }
		else
			return "No one";
	
	

    }



    public static void main (String [] args){
    	int N = 8;
    	StdDrawPlus.setXscale(0, N);
    	StdDrawPlus.setYscale(0, N);
    	Board b = new Board(false);
    	
    	b.drawBoard(N);

    	while(b.winner() == null){
    		b.drawBoard(N);
    		if (StdDrawPlus.mousePressed()) {
                double movex = StdDrawPlus.mouseX();
                double movey = StdDrawPlus.mouseY();
               
                if(b.canSelect((int)movex,(int) movey)){
                	b.select((int)movex, (int)movey);
                	
                }
            } 
            if(StdDrawPlus.isSpacePressed()){
                b.endTurn();                	
			}
            
            StdDrawPlus.show(100);
    	}
        b.drawBoard(N);

        System.out.println(b.winner());
    	


    }

}

