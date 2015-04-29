public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private boolean pieceSelected = false; //update in select
	private boolean moved = false; //update in select and shit
	private boolean captured = false; // update in select and shit
	private Piece removedpiece = null;
	private boolean turnFire = true;
	private int currx = 0; //update in select
	private int curry = 0; // update in select

	//in select you call move and them move calls remove and place
	// update moved, captured, and pieceselcted in Select
	// after calling move from slect update hasmoved();


	public Board(boolean shouldBeEmpty){

		if (shouldBeEmpty){

			drawBoard(8);
		}

		if (!shouldBeEmpty){

			drawBoard(8);
			this.drawPieces();
		}
	}



	private void drawPieces(){
		for (int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				if((j==0) && (i%2==0)){
					pieces[i][j]= new Piece(true, this, i, j, "pawn");
				}

				if((j==1) && (i%2==1)){
					pieces[i][j] = new Piece(true, this, i, j, "shield");

				}
				if((j==2) && (i%2==0)){
					pieces[i][j]= new Piece(true, this, i, j, "bomb");
				}

				if((j==5) && (i%2==1)){
					pieces[i][j] = new Piece(false, this, i, j, "bomb");

				}
				if((j==6) && (i%2==0)){
					pieces[i][j]= new Piece(false, this, i, j, "shield");
				}

				if((j==7) && (i%2==1)){
					pieces[i][j]= new Piece(false, this, i, j, "pawn");
				}
			}
		}


	}






	private void imgPieces(){
		for(int i=0; i<8;i++){
			for(int j=0; j<8;j++){
				if(pieces[i][j] != null){

					if(pieces[i][j].isFire()){
						if (pieces[i][j].isKing()){
							if (pieces[i][j].isBomb()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							if (pieces[i][j].isShield()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield()) ) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}

						else{
							if (pieces[i][j].isBomb()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
							if (pieces[i][j].isShield()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
							else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}

					else {
						if (pieces[i][j].isKing()){
							if (pieces[i][j].isBomb()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							if (pieces[i][j].isShield()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						}

						else {
							if (pieces[i][j].isBomb()){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
							if (pieces[i][j].isShield()){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
							else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}

				}
					
			}
		}
	}




    private void drawBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                imgPieces();

                }
            }
        }
 


	public Piece pieceAt(int x, int y){
		if ((x<0) || (x>7) || (y<0) || (y>7)){
			return null;
		}
		return pieces[x][y];


	}



	public boolean canSelect(int x, int y){
		if(!withinBounds(x,y)){
			return false;
		}

		if (pieces[x][y] == null){
			if (this.pieceSelected && validMove(this.currx, this.curry, x, y) && !this.moved){
					return true;
				
			}

			else if (this.pieceSelected && this.captured && validMove(this.currx, this.curry ,x,y)){
				return true;
			}

			return false;
		} 


		else if (this.pieces[x][y] != null && !this.moved){

			if (this.turnFire==true && pieceAt(x,y).isFire() == false) {

				return true;
			}

			else if (this.turnFire==false && pieceAt(x,y).isFire() == true){

				return true;
			}
		}

		return false;
	}





    private boolean withinBounds(int x,int y){
    	if(x<8 && x>0 && y>0 && y<8){
    		return true;
    	}

    	return false;
    }



    private boolean isEnemy(boolean isfire, Piece p){
    	if(p.isFire() && isfire){
    		return false;
    	}

    	if(p.isFire() && !isfire){
    		return true;
    	}

    	if(p.isFire() && isfire){
    		return false;
    	}

    	if(!p.isFire() && !isfire){
    		return true;
    	}

    	return false;
    }


    private boolean isMiddleFire(int x1, int y1, int x2, int y2){
    	
    	int xm= (x1+x2)/2;
    	int ym= (y1+y2)/2;

    	Piece p =this.pieces[xm][ym];
    	if(p.isFire()){
    		return true;
    	}
    	if (!p.isFire()){
    		return false;
    	}

    	return false;

    }



	private boolean validMove(int xi, int yi, int xf, int yf){
		if(!withinBounds(xf,yf)){
			return false;
		}


		if (this.pieces[xf][yf]== null){



			if(this.pieces[xi][yi].isKing()){
				if((Math.abs(yf-yi)==1) && (Math.abs(xf-xi)==1)){
						return true;
					}

				else if (!this.moved || this.pieces[xi][yi].hasCaptured()){
					if((Math.abs(yf-yi)==2) && (Math.abs(xf-xi)==2)){
						if (isMiddleFire(xi,yi,xf,yf) && !pieceAt(xi,yi).isFire()){

							return true;
						}

						else if (!isMiddleFire(xi,yi,xf,yf) && pieceAt(xi,yi).isFire()){

							return true;
						}
					}

				}

					return false;
			}

			else if(this.pieces[xi][yi].isFire()){
					if((yf-yi==1) && (Math.abs(xf-xi)==1)){
						return true;
					}


					else if (!this.moved || this.pieces[xi][yi].hasCaptured()){
						if((yf-yi==2) && (Math.abs(xf-xi)==2)){
							if (!isMiddleFire(xi,yi,xf,yf)){

							return true;
							}
						}	

					}
					return false;
			}

			else if(this.pieces[xi][yi].isFire() == false){
					if((yf-yi==-1) && (Math.abs(xf-xi)==1)){
						return true;
					}

					else if (!this.moved || this.pieces[xi][yi].hasCaptured()){
						if((yf-yi==-2) && (Math.abs(xf-xi)==2)){
							if (isMiddleFire(xi,yi,xf,yf)){
							return true;
							}
						}	

					}

					return false;
			}
		}



		//if capturing 
		else if (this.pieces[xf][yf] != null) {

		// 	if(this.pieces[xi][yi].isKing()){

		// 		if((yf-yi==1) && (Math.abs(xf-xi)==1) && pieces[xf+1][yf+1]==null ){
		// 			return true;
		// 		}
		// 		return false;
		// 	}


		// 	else if(this.pieces[xi][yi].isFire()){
		// 		if((yf-yi==1) && (Math.abs(xf-xi)==1) && (pieces[xf+1][yf+1]==null) ){
		// 			if(this.pieces[xf][yf].isFire() == false){
		// 				return true;
		// 			}
		// 		}
		// 		return false;
		// 	}

		// 	else if(!this.pieces[xi][yi].isFire()){
		// 		if((yf-yi==-1) && (Math.abs(xf-xi)==1) && (pieces[xf+1][yf-1]==null ) ){
		// 			if(this.pieces[xf][yf].isFire()){
		// 				return true;
		// 			}
		// 		}
		// 		return false;

		// 	}
		// }


			return false;
		}

		return false;

	}






	public void select(int x, int y){

		this.currx=x;
		this.curry=y;

		if(this.pieceAt(x,y) !=null){
			this.moved = true;
			pieceAt(currx, curry).move(x,y);
		}


		else if(this.pieceAt(x,y)==null){
			pieceSelected = true;
		}

		if(this.pieceAt(x,y).isBomb() && this.captured==true){ 

			if(isEnemy(pieceAt(x,y).isFire(), pieceAt(x+1,y+1)) && !pieceAt(x+1,y+1).isShield()){
				this.remove(x+1, y+1);

			}

			if(isEnemy(pieceAt(x,y).isFire(), pieceAt(x-1,y+1)) && !pieceAt(x+1,y+1).isShield()){
				this.remove(x+1, y+1);

			}

			if(isEnemy(pieceAt(x,y).isFire(),pieceAt(x+1,y-1)) && !pieceAt(x+1,y+1).isShield()){
				this.remove(x+1, y+1);

			}

			if(isEnemy(pieceAt(x,y).isFire(),pieceAt(x-1,y-1)) && !pieceAt(x+1,y+1).isShield()){
				this.remove(x+1, y+1);

			}

		}



	}




	public void place(Piece p, int x, int y){
		if((p!=null) && (x<8) && (x>=0) && (y>=0) && (y<8)){
			this.pieces[x][y]= p;
		}	
	}




	public Piece remove(int x, int y){

		if(!withinBounds(x,y)){
			System.out.println("The coordinate is not within bounds");
			return null;
		}

		if(pieces[x][y]== null){
			System.out.println("There is no piece to remove here");
			return null;
		}

		else{
			Piece p = pieces[x][y];
			pieces[x][y]= null;
			return p;
		}
		

	}



	public boolean canEndTurn(){

		if (this.moved || this.captured){
			return true;
		}

		return false;

	}




	public void endTurn(){

		if(this.turnFire==true){
			this.turnFire = false;
		}
		else{
			this.turnFire = true;
		}

		this.moved = false;
		this.pieceSelected =false;
		pieceAt(currx, curry).doneCapturing();
		this.removedpiece = null;

	}




	public String winner(){
		int fs=0;
		int ws=0;

		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(this.pieces[i][j].isFire()){
					fs=fs+1;
				}
				else if(!this.pieces[i][j].isFire()){
					ws=ws+1;
				}
			}
		}

		if(fs>0 && ws==0){
			return "Fire";
		}

		else if(ws>0 && fs==0){
			return "Water";
		}
		else if(ws==0 && fs==0){
			return "No one";
		}

		else if (ws>0 && fs>0){
			return null;
		}

		return "No one";
	}	




    public static void main(String[] args) {
        int N = 8;
        Board b = new Board(false);
        b.drawPieces();
        //pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                
                if (b.canSelect((int) x, (int) y) == true) {
                    b.select((int) x, (int) y);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn())
                {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(100);
        }
        // while(true) {
        //     b.drawBoard(N);
        //     if (StdDrawPlus.mousePressed()) {
        //         double x = StdDrawPlus.mouseX();
        //         double y = StdDrawPlus.mouseY();
        //     }            
        }


}