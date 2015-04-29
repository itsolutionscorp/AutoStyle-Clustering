public class Board {
	private Piece[][] pieces;
	private boolean hasSelected=false;
	private boolean hasMoved=false;
	private Piece pieceSelected=null;
	private int tempX=0;
	private int tempY=0;
	private int teamRed;
	private int teamBlue;
	private boolean fireTurn=true;

	public static void main(String args[]){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        Board b= new Board(false);

        while(true) {    
        	
        	
 	   		b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if(b.canSelect(x,y)){
                	b.select(x,y);
                }
                
            }
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }            
            if(b.winner()!=null){
        		b.winner();
        	}
            StdDrawPlus.show(100);
        	
        }
        
	}
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		if(shouldBeEmpty==true){
			

			
		}
		else{
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	        		//draw fire pawns
	                if (i%2==0 && j==0 ) {
	                	Piece temp= new Piece(true, this, i, j, "pawn");
	                //	(boolean isFire, Board b, int x, int y, String type)
	                	this.place(temp,i,j);
	                    //StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                }
	                //draw fire shields
	                if (j==1 && i%2==1) {
	                	Piece temp= new Piece(true, this, i, j, "shield");
	                	this.place(temp,i,j);
	                    //StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                }
	                //draw fire bombs
	                if (j==2 && i%2==0) {
	                	Piece temp= new Piece(true, this, i, j, "bomb");
	                	this.place(temp,i,j);
	                    //StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                } 
	                //draw water bombs
	                if (j==5 && i%2==1) {
	                	Piece temp= new Piece(false, this, i, j, "bomb");
	                	this.place(temp,i,j);
	                    //StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                }
	                //draw water shields
	                if (j==6 && i%2==0) {
	                	Piece temp= new Piece(false, this, i, j, "shield");
	                	this.place(temp,i,j);
	                    //StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                }
	                //draw water pawns
	                if (j==7 && i%2==1) {
	                	Piece temp= new Piece(false, this, i, j, "pawn");
	                	this.place(temp,i,j);
	                    //StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                }
	            }
	        }
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if(pieces[i][j] !=null && !pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
  //              	teamRed=teamRed+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isKing() && !pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
    //            	teamRed=teamRed+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isShield() && pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
      //          	teamRed=teamRed+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isKing() && pieces[i][j].isShield() && pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
        //        	teamRed=teamRed+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isBomb() && pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
          //      	teamRed=teamRed+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isKing() && pieces[i][j].isBomb() && pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            //    	teamRed=teamRed+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isBomb() && !pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
              //  	teamBlue=teamBlue+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isKing() && pieces[i][j].isBomb() && !pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                //	teamBlue=teamBlue+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isShield() && !pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                //	teamBlue=teamBlue+1;
                }

                if(pieces[i][j] !=null && pieces[i][j].isKing() && pieces[i][j].isShield() && !pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                //	teamBlue=teamBlue+1;
                }

                if(pieces[i][j] !=null && !pieces[i][j].isBomb() && !pieces[i][j].isShield() && !pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                //	teamBlue=teamBlue+1;
                }
                if(pieces[i][j] !=null && pieces[i][j].isKing() && !pieces[i][j].isBomb() && !pieces[i][j].isShield() && !pieces[i][j].isFire()){
                	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                //	teamBlue=teamBlue+1;
                }
            }
        }
    }
   
	public Piece pieceAt(int x, int y){
		if(x > 7 || x<0 || y<0|| y>7|| pieces[x][y] == null){
			return null;
		}
		return pieces[x][y];

	}
	
	public boolean canSelect(int x, int y){

		if(pieceAt(x,y)!=null && ((pieceAt(x,y).isFire() && fireTurn) || (!pieceAt(x,y).isFire() && !fireTurn))
		 && (!hasSelected || (hasSelected && !hasMoved)) ) {
			return true;
		}
	/*	if(pieceSelected==null){
			return false;
		}*/
		else if(pieceAt(x,y)==null && pieceSelected!=null && ((hasSelected && !hasMoved && canMove(tempX,tempY,x,y, pieceSelected))
			|| (hasSelected && pieceSelected.hasCaptured() && canMove(tempX,tempY,x,y, pieceSelected)) )) {
			return true;
		}
		else{
			return false;
		}
		
	}
	private boolean canMove(int fromX, int fromY, int toX, int toY, Piece p){
		if(toX>7 || toX <0 || toY>7 || toY <0){ //can't move out of bounds
			return false;
		}

		else if (pieces[toX][toY]== null && p!=null){ //is selected space blank
			if(Math.abs(fromX-toX)==1 && !hasMoved){ //regular move
				if(p.isKing()){
					return true;
				}
				if(fireTurn && (toY-fromY==1)){
					return true;
				}
				if(!fireTurn && (fromY-toY)==1){
					return true;
				}
			}

			else if(Math.abs(fromX-toX)==2 && pieces[fromX][fromY]!=null && pieces[(toX+fromX)/2][(toY+fromY)/2]!=null){ //jump
				if(fireTurn && p.isKing() && !pieces[(toX+fromX)/2][(toY+fromY)/2].isFire()) {
					return true;
				}
				if(!fireTurn && p.isKing() && pieces[(toX+fromX)/2][(toY+fromY)/2].isFire()){
					return true;
				}
				if(fireTurn && ((toY-fromY)==2) && !pieces[(toX+fromX)/2][(toY+fromY)/2].isFire()){
					return true;
				}
				if(!fireTurn && ((fromY-toY)==2) && pieces[(toX+fromX)/2][(toY+fromY)/2].isFire()){
					return true;
				}
			}
		}
		return false;
	}
	public void select(int x, int y){
		if(pieceAt(x,y) != null){

			pieceSelected=pieceAt(x,y);
			tempX=x;
			tempY=y;
			this.hasSelected=true;
		}
		else{

			pieceSelected.move(x,y);

			if(pieceSelected.isFire()){
				teamRed=teamRed-1;
			}
			if(pieceSelected.isFire()){
			 	teamBlue=teamBlue-1;
			}
			pieces[tempX][tempY]=null;
			tempX=x;
			tempY=y;
			hasMoved=true;
		}
	}
	public void place(Piece p, int x, int y){
	//		System.out.println(teamRed + " " + teamBlue);

			if(p.isFire()){
				teamRed=teamRed+1;
			}
			if(p.isFire()){
				teamBlue=teamBlue+1;
			}
		if(p==null || x<0|| y<0|| x>7|| y>7 ){
			return;
		}
		else{
			pieces[x][y]=p;
		}
	}


	public Piece remove(int x, int y){
	//	System.out.println(teamRed + " " + teamBlue);
	//	System.out.println("remove");
	//	System.out.println(pieceAt(x,y).isFire());
		if(pieceAt(x,y).isFire()){
			teamRed=teamRed-1;
		}
		if(!pieceAt(x,y).isFire()){
			teamBlue=teamBlue-1;
		}
		Piece temp=pieceAt(x,y);
		// if(pieceSelected==pieces[x][y] && pieceSelected.isBomb()){
		// 	pieceSelected =null;
		// }
	/*	if(pieceSelected==temp){
			pieceSelected=null;
		}*/
		if(pieces[x][y]!=null){
			pieces[x][y]=null;
		}
		return temp;
	}
	public boolean canEndTurn(){
		if (pieceSelected==null){
			return false;
		}
		if(hasMoved || pieceSelected.hasCaptured()){
			return true;
		}
		return false;
	}
	public void endTurn(){

		pieceSelected.doneCapturing();
		hasMoved=false;
		hasSelected=false;
		tempX=0;
		tempY=0;
		pieceSelected=null;
		if(fireTurn){
			fireTurn=false;
		}
		else {
			fireTurn=true;
		}
	}
	public String winner(){
	//	System.out.println(teamRed + " " + teamBlue);
		if(teamBlue==0 && teamRed==0){
			return "No one";
		}
		if(teamRed==0){
			return "Water";
		}
		if(teamBlue==0){
			return "Fire";
		}

		return null;
	}
}
