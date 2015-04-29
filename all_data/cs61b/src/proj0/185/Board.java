public class Board{
	private int x;
	private int y;
	private Piece [] [] pc;
	private boolean shouldbeEmpty;
	private boolean turnfire=true;
	private boolean moved=false;
	private boolean fireplaying=true;
	private boolean pieceselected=false;
	private int xpos;
	private int ypos;
	private Piece selected = null;
	private static boolean[][] pieces;

	
	public Board(boolean shouldbeEmpty){
    	pc = new Piece [8][8];

    	if (!shouldbeEmpty)
    	{
    		for(int i=0; i<8;i++){
    		for(int j=0;j<8;j++){
    			
    			if ((i + j) % 2 == 0) {
    				if(j<3){
    					boolean isFire = true;
    					if(j==0){
    						pc[i][j]=new Piece(isFire,this,i,j,"pawn");
    					}
    					else if(j==1){
    						pc[i][j]=new Piece(isFire,this,i,j,"shield");
    					}
    					else if(j==2){
    						pc[i][j]=new Piece(isFire,this,i,j,"bomb");
    					}
    					else{
    						pc[i][j]=null;
    					}
    				}
    				else if(j>4){
    					boolean isFire = false;
    					if(j==7){
    						pc[i][j]=new Piece(isFire,this,i,j,"pawn");
    					}
    					else if(j==6){
    						pc[i][j]=new Piece(isFire,this,i,j,"shield");
    					}
    					else if(j==5){
    						pc[i][j]=new Piece(isFire,this,i,j,"bomb");
    					}
    					else{
    						pc[i][j]=null;
    					}
    				}
    				else{
    					pc [i][j]=null;
    				}

    			}
    			else{
    				pc [i][j]=null;
    			}
    			
    		}
    		}

    	}

	}
	private int countWaterPieces(){
		int count = 0;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8;j++){
				if (!(pc[i][j]==null)){
					if(!pc[i][j].isFire()){
					count ++;			
					}	
				}
		}
		}
		return count;
	}

	private int countFirePieces(){
		int count = 0;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8;j++){
				if(!(pc[i][j]==null)){
				if (pc[i][j].isFire()){
					count ++;
				}
				}					
			}

	}
	return count;
}

	public static void main(String args[]){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b=new Board(false);
		b.drawBoard();

        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                System.out.println(b.canSelect((int) x,(int)y));
                if(b.canSelect((int) x,(int)y)){

                	b.select((int)x,(int)y);
            	}

            	b.drawBoard();
            }
            if(StdDrawPlus.isSpacePressed()){
            	b.endTurn();
            }           
            StdDrawPlus.show(100);
        }
	}

	private void drawBoard() {
       	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                String img;
                
               	if(pc[i][j]==null){

               	}
                else if(pc[i][j].isFire()){
             		if(pc[i][j].isShield()){
             			img="shield-fire.png";
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+img, 1, 1);
             		}
             		else if(pc[i][j].isBomb()){
             			img="bomb-fire.png";
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+img, 1, 1);
             		}
             		else{
             			img="pawn-fire.png";
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+img, 1, 1);
             			}
             		}
             	else {
             		if(pc[i][j].isShield()){
             			img="shield-water.png";
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+img, 1, 1);
             		}
             		else if(pc[i][j].isBomb()){
             			img="bomb-water.png";
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+img, 1, 1);
             		}
             		else{
             			img="pawn-water.png";
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+img, 1, 1);
             			}
             		}

             	}
             
         }
               }

	public Piece pieceAt(int x, int y){
		if(x>7 || x<0 ){
			return null;
		}
		else if(y>7 || y<0){
			return null;
		}
		else{
			return pc[x][y];
		}

	}
	public boolean canSelect(int x, int y){


		//else if ((moved == true))
		if(!(pieceAt(x,y)==null)){
		System.out.println(fireplaying);
		if(fireplaying){
			if(!moved){
				if(pieceAt(x,y).isFire()){
					return true;
				}
				return false;
			}
			// else if (moved)
			// {
			// 	if (pieceAt(x, y) == selected)
			// 	{
			// 		return true;
			// 	}
			// }
			return false;
		}
		else if(!fireplaying){
			if(!moved){
				if(!pieceAt(x,y).isFire()){
				return true;
				}
				return false;
			}
			// else if (moved)
			// {
			// 	if (pieceAt(x, y) == selected)
			// 	{
			// 		return true;
			// 	}
			// }
			return false;
		}
	}
	else if((pieceAt(x,y))==null){
		if(selected==null){
			return false;
		}
		else{
			if(validMove(xpos,ypos,x,y)){
					if(moved&&selected.hasCaptured()){
						return false;
					}
					else if(moved){
						return true;
					}
					return false;
			}
			return false;
		}
	}
	return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){

	if(pieceAt(x,y).isKing() == false){
		
		if((xf==xi+1)&&(yf==yi+1)){
			System.out.println("breakpoint");
			return true;
		}
		else if((xf==xi+1)&&(yf==yi-1)){
				return true;
		}
		else if((xf==xi+2)&&(yf==yi+2)){
				if(!(pieceAt(xi+1,yi+1)==null)){
					if(!(pieceAt(xi+1,yi+1).isFire())){
						if(!(pieceAt(xi,yi).isFire())){
							return false;
						}
						return true;
					}
					else{
						if(pieceAt(xi,yi).isFire()){
							return false;
						}
						return true;

					}
				}
				return false;
			}
		else if((xf==xi+2)&&(yf==yi-2)){
				if(!(pieceAt(xi+1,yi-1)==null)){
					if(pieceAt(xi+1,yi-1).isFire()){
						if((pieceAt(xi,yi).isFire())){
							return false;
						}
						return true;
					}	
					else{
						if(pieceAt(xi,yi).isFire()){
							return true;
						}
						return false;

					}
			}

		return false;

		}	
	else{

		if(pieceAt(x,y).isFire()){

			if(((xf==xi+1)&&(yf==yi+1))||((xf==xi-1)&&(yf==yi+1))){
				return true;
			}
			else if((xf==xi+2)&&(yf==yi+2)){
				if(!(pieceAt(xi+1,yi+1)==null)){
					if(!(pieceAt(xi+1,yi+1).isFire())){
						return true;
					}
					return false;
				}
				return false;
			}
				return false;
		}
	else{
		if((xf==xi+1)&&(yf==yi-1)){
				return true;
			}
			else if((xf==xi+2)&&(yf==yi-2)){
				if(!(pieceAt(xi+1,yi-1)==null)){
					if(pieceAt(xi+1,yi-1).isFire()){
						return true;
					}
					return false;
				}
				return false;
			}
				return false;
	}
	}
}
return false;
}
	public void select(int x, int y){

		pieceselected=true;
		if(!(pieceAt(x,y)==null)){
			
			selected = pieceAt(x,y);
			System.out.println(selected);
			xpos=x;
			ypos=y;
		}

		else if(pieceAt(x,y)==null){
			pc[x][y]=selected;
			System.out.println("breakpoint");
			//selected.move(x,y);

			//place(selected,x,y);
		}
	}
	public void place(Piece p, int x, int y){
		pc[x][y]=p;
		remove(xpos, ypos);
		// if(!(y>7||x>7||x<0||y<0)){
		// 	for(int i=0; i<8; i++){
		// 		for(int j=0; j<8;j++){
		// 			if(pc[i][j]==p){
		// 				remove(i,j);
		// 			}
		// 		}
		// 	}
		// }
		moved=true;
	}
	public Piece remove(int x, int y){
		if(y>7||x>7||x<0||y<0){
			System.out.println("Enter correct bounds please!");
			return null;
		}
		else if(pieceAt(x,y)==null){
			System.out.println("please add a piece to move");
			return null;
		}
		else{
			Piece temp=pieceAt(x,y);
			pc[x][y]=null;
			return temp;
		}
	}
	//check whether works or not for both endTurn and canEndTurn
	//only need to call moved since if captured it implies move has occurred
	public boolean canEndTurn(){
		return moved;

	}
	public void endTurn(){
		if(canEndTurn()==true){
			if (!fireplaying){
				fireplaying=true;
			}
			else{ 
				fireplaying=false;
			}
		}
		moved=false;

	}
	public String winner(){

		int waterCount = countWaterPieces();
		int fireCount = countFirePieces();
		if((waterCount==0) && (fireCount==0)){
			return "No one";
		}
		else if(waterCount==0){
			return "Fire";
		}
		else if(fireCount==0){
			return "Water";
		}
		else{
			return null;
		}
	} 
}