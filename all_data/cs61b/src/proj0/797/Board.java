public class Board {
   private  static boolean[][] pieces;
   private  static Piece [][] pieceBox;
   private boolean moveComplete = false;
   private  boolean[][] vulnerable;
   private  int N = 8; 
   private int x; 
   private int y; 
   private int dx; 
   private int dy; 
   private int x0; 
   private int y0; 
   private Piece chosen = null;
   private boolean noMovesyet = true; 
   private int waterc;
   private int firec; 

   private boolean isFireTurn;
   

   
  
  public Board(boolean shouldBeEmpty){
  		int N = 8; 
		pieces = new boolean[N][N];
		pieceBox = new Piece [N][N];
		isFireTurn = true; 

  		if (shouldBeEmpty == true){

  		}

  		else {

		x = 0; 
		y = 0; 

		  while( x <= N-1 ){
			place (new Piece (true, this , x, y, "pawn"), x, y);
			x = x+2 ; 			
        }

        x = 1; 
        y = 7; 

         while( x <= N-1 ){
            place (new Piece (false, this , x, y, "pawn"), x, y);
			x = x+2 ; 			
        }

        x = 0; 
        y = 6;

         while( x <= N-1 ){
            place (new Piece (false, this , x, y, "shield"), x, y);
			x = x+2 ; 		
        }

        x = 1; 
        y = 5;
        
         while( x <= N-1 ){
            place (new Piece (false, this , x, y, "bomb"), x, y);
			x = x+2 ; 			
        }


        x = 1; 
        y = 1;
        
         while( x <= N-1 ){
            place (new Piece (true, this , x, y, "shield"), x, y);
			x = x+2 ; 			
        }

        x = 0; 
        y = 2;
        
         while( x <= N-1 ){
            place (new Piece (true, this , x, y, "bomb"), x, y);
			x = x+2 ; 		
        }
    }

  }



	private void drawBoard(int N){

		String team;

		for(int i = 0; i < N ; i=i+1){
			for (int j = 0; j < N ; j = j+1 ){
				if ((i+j) % 2 == 0 ) StdDrawPlus.setPenColor(StdDrawPlus.KD_GREEN2);//KD_GREEN2
                else       StdDrawPlus.setPenColor(StdDrawPlus.KD_GREEN1);//1
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if ( pieces[i][j] ) {
    				if ( pieceBox[i][j].isFire()) team = "fire" ; 
    				else team = "water" ; 

    				if ( chosen!=null && pieceAt(i,j) == chosen) {
    					StdDrawPlus.setPenColor(StdDrawPlus.PINK);//Pink
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}


    				if (pieceBox[i][j].isKing()) { 
    					if (pieceBox[i][j].isBomb()){
    						StdDrawPlus.picture(i + .5, j + .5, "img/bomb" + "-" + team + "-crowned.png" , 1, 1);
    					}
    					else if (pieceBox[i][j].isShield()){
    						StdDrawPlus.picture(i + .5, j + .5, "img/shield" + "-" + team + "-crowned.png" , 1, 1);
    					}
    					else
    					StdDrawPlus.picture(i + .5, j + .5, "img/pawn" + "-" + team + "-crowned.png" , 1, 1);
    				}

    				else if (pieceBox[i][j].isBomb()){
    					StdDrawPlus.picture(i + .5, j + .5, "img/bomb" + "-" + team + ".png" , 1, 1);
    				}
    				else if (pieceBox[i][j].isShield()){
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield" + "-" + team + ".png" , 1, 1);
                }
                   else StdDrawPlus.picture(i + .5, j + .5, "img/pawn" + "-" + team + ".png" , 1, 1);
				}
            }
		}
	}

	public Piece pieceAt(int x, int y){
		if (x > 7 || y > 7) return null; 
		if (pieces[x][y]) return pieceBox[x][y]; 
		else return null; 
	}

	public void place(Piece p, int x, int y){
		//remove(x , y);
		pieceBox[x][y] = p;
		pieces[x][y] = true;
		
	}

	public Piece remove(int x, int y){
		Piece byePiece = pieceAt(x,y);
		pieces[x][y] = false;
		pieceBox[x][y] = null;
		

		if (byePiece!= null && byePiece.isBomb() && byePiece.hasCaptured()){
			for (int i= -1 ; i<2 ; i=i+1){
       			for (int j = -1 ; j<2 ; j=j+1){
          			if(pieceAt(i+x,j+y) != null && !pieceAt(i+x , j+y).isShield()){
          				pieces[i+x][j+y] = false; 
          				pieceBox[i+x][j+y] = null;
          			}
        		}
      		}
    	}
    	return byePiece;
  	}
		
	

	public boolean canEndTurn(){
		if (moveComplete == true ){
			return true;
		}
		else return false;
	}

	public void endTurn(){
		if (canEndTurn()){
			dx = 0; 
			dy = 0; 
			chosen.doneCapturing(); 
			chosen = null; 
			winner();


			if (isFireTurn){
				isFireTurn = false;
				moveComplete = false; 
		}

		else if (!isFireTurn){
		isFireTurn = true; 
		moveComplete = false; 
	}
	}

	}

	public void select(int x, int y){
			if ( chosen != null && ((chosen.isFire() && !isFireTurn) || (!chosen.isFire() && isFireTurn))){
				chosen = null;

			}


				
			if (pieces[x][y]  && pieceBox[x][y] != null && ((pieceBox[x][y].isFire() && isFireTurn) || !pieceBox[x][y].isFire() && !isFireTurn)){	 
				chosen = pieceBox[x][y];
				x0 = x; 
				y0 = y; 
				return;
			}

			if (!pieces[x][y]  && chosen != null) {
				//int x0 = chosen.x; 
				//int y0 = y0; 

				chosen.move( x , y );
				x0 = x; 
				y0 = y; 
				capturable(chosen); 
				noMovesyet = false; 
			
				moveComplete = true; 

			
		}
	}	


	private  boolean[][] capturable (Piece p){
		vulnerable = new boolean[2][2]; 

		/* FIRE PIECE */
		if (p.isFire()){

			if ( x0 < N-1 && y0 < N-1 && pieces[x0 + 1][y0 + 1] && !pieceBox[x0 + 1][y0 + 1].isFire()){ // water right
					vulnerable[1][0] = true; 

				}
				if ( x0 > 0 && y0 < N-1 && pieces[x0 - 1][y0 +1] && !pieceBox[x0 - 1][y0 +1].isFire()){ // water left
					vulnerable[0][0] = true;
				}
				
			
			if (p.isKing()){ 
				if (y0 > 0 && x0 < N-1 && pieces[x0 +1][y0 -1] && !pieceBox[x0 + 1][y0 -1].isFire()){ // water down right
					vulnerable[1][1] = true;
				}
				if (y0 > 0 && x0 > 0 && pieces[x0 - 1][y0 - 1] && !pieceBox[x0 - 1][y0 - 1].isFire()){ // water down left
				vulnerable[0][1] = true;
			}
				
			} 

			return vulnerable;
		}



		/* WATER PIECE */
		if (!p.isFire()){ 
			if (x0 < N-1 && y0 > 0 && pieces[x0 +1][y0 -1] && pieceBox[x0 + 1][y0 -1].isFire()){ // fire right
				vulnerable[1][1] = true;
			}
			if (x0 > 0 && y0 > 0 && pieces[x0 - 1][y0 - 1] && pieceBox[x0 - 1][y0 - 1].isFire()){ // fire left					
				vulnerable[0][1] = true;
			}
			
			if (p.isKing()){
				if ( x0 < N-1 && y0 < N-1 && pieces[x0 + 1][y0 + 1] && pieceBox[x0 + 1][y0 + 1].isFire()){ // fire down right
						vulnerable[1][1] = true;
				}
				if ( x0 > 0 && y0 < N-1 && pieces[x0 - 1][y0 +1] && pieceBox[x0 - 1][y0 +1].isFire()){ // fire down left
						vulnerable[0][1] = true;
				}
			}
			
		}

		return vulnerable;


	}


	public boolean canSelect(int x, int y){

		if ( ( x%2 == 0 && y%2 == 0) || ( y%2 != 0 &&  x%2 != 0) ){
		
			/* FIRE TURN */
			if (isFireTurn){
				/* ALLOW SELECT FIRE PIECE */
				if ( pieces[x][y] && !moveComplete && ( chosen == null || !chosen.hasCaptured()) ){
					if (pieceBox[x][y].isFire()){
						return true;
					} 
					else return false;	
				}
				/* IF PIECE IS CHOSEN */
				if (chosen != null && chosen.isFire() &&  ( !moveComplete || chosen.hasCaptured())){
					/* IF NOT TRYING TO CAPTURE (PEASANT OR KING) */
					if ( !moveComplete && ( y - y0 == 1 || ( chosen.isKing() && Math.abs(y0 - y) == 1 ))){  // || (( !pieceBox[chosen.x+1][y0+1].isFire() && pieces[chosen.x+1][y0+1]) && (Math.abs(chosen.x-x)==2)))){
					return true;
					}
					/* IF CAPTURE AVAILABLE */

					else {
						for (int j = 0 ; j<=1 ; j++){
							for (int i = 0 ; i<=1 ; i++){
								if (i == 0 && capturable(chosen)[i][j] && x0 - x == 2){
									return true;
								}
								if (i == 1 && capturable(chosen)[i][j] && x - x0 == 2){
									return true; 
								}
							}
						}
						return false;
					}
				}		
			}
		


			/* WATER TURN*/ 
			if (!isFireTurn){
				/* IF NO PIECE HAS BEEN CHOSEN THUS FAR */
				if ( pieces[x][y] && !moveComplete && (chosen == null || !chosen.hasCaptured())){
				if (!pieceBox[x][y].isFire()){
							return true;
					}
					else return false; 
				}
				/* IF PIECE IS CHOSEN */
				if (chosen != null && !chosen.isFire() && (!moveComplete || chosen.hasCaptured())){
					/* IF NOT TRYING TO CAPTURE (PEASANT OR KING) */
					if ( !moveComplete && (y0 - y == 1 || ( chosen.isKing() && Math.abs(y0 - y) == 1))){
						return true;
					}

					/* IF CAPTURE AVAILABLE */
					else {
						for (int j = 0 ; j<=1 ; j++){
							for (int i = 0 ; i<=1 ; i++){
								if (i == 0 && capturable(chosen)[i][j] && x0 -x  == 2){
									return true;
								}
								if (i == 1 && capturable(chosen)[i][j] && x - x0 == 2){
									return true; 
								}
							}
						}
						return false;
					}

				}
				return false;
			}
		return false;	
	}
	return false;
}

	

	

	

	public String winner(){
		 firec = 0 ; 
		 waterc = 0 ; 


		for (int j=0; j<N; j++){ 
		for (int i=0; i<N; i++){
			if (pieceBox[i][j]!=null){
			if (pieceBox[i][j].isFire()){
				firec ++;}  
			else { waterc ++;
			}
			} 
		}
		}

		if (waterc == 0 && firec !=0) return "Fire";
		if (firec == 0 && waterc !=0) return "Water";
		if (firec == 0 && waterc==0 && !noMovesyet) return "No one";

		else return null;

	}

	

	

	public static void main(String[] args){
		int N = 8; 

		StdDrawPlus.setXscale(0 , N);
		StdDrawPlus.setYscale(0 , N);

		Board b = new Board(false);
		b.drawBoard(N);
		b.isFireTurn = true;


		StdDrawPlus.show(10);

		while ( (b.firec >= 0  ||  b.waterc >=0 ) ) { 
			b.drawBoard(N);
			StdDrawPlus.show(10);
      	
        	if (StdDrawPlus.mousePressed()){
        	
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();  	

        			if (b.canSelect(x,y)){
        				b.select( x , y); 
        				b.noMovesyet = false; 
        				continue;
        			}
    		}

	        if (StdDrawPlus.isSpacePressed()){
	        	 if  (b.canEndTurn())
	        	 b.endTurn();
	        	 continue;
	        }

   		 }
    }
}

      


            
	

