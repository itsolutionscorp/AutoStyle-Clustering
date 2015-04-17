public class Board{
	private Piece[][] piece;
	private int[] selectedplace;
	private int turn;//fire==0, water==1
	
	private boolean actioned;
		//***helper functions***	



	private void changeturn(){
		if (turn==0){
			turn=1;
		}
		else{
			turn=0;
		}
		
	}

	private void drawBoard(int N){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                
                }
        	}
        if (selectedplace[0]>=0){
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(selectedplace[0] + .5, selectedplace[1] + .5, .5);
                
            
        }
    }
	
	private void drawPiece(){
		for (int k = 0; k < 8; k++) {
			for (int h = 0; h< 8; h++){
				Piece thepiece = piece[k][h];
                	if (thepiece!= null){
                		StdDrawPlus.picture(k+ 0.5, h+ 0.5, getimage(thepiece), 1, 1);
                	}
			}
            
        }
	}
	private String getimage(Piece p){
		String type="pawn";
		if (p.isBomb()){
			type="bomb";
		}
		else if (p.isShield()){
			type="shield";
		}
		if (p.isKing()){
			if (p.isFire()){
				return "img/"+type+"-fire-crowned.png";
			}
			return "img/"+type+"-water-crowned.png";
		}
		else if (p.isFire()){
			return "img/"+ type+"-fire.png";
		}
		return "img/"+ type+ "-water.png";

	}

	//***public methods***

	public static void main(String[] args){
		Board b = new Board(false);
		b.turn=0;

		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		while (true){
			
			b.drawBoard(N);
			b.drawPiece();
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
               	double y = StdDrawPlus.mouseY();
               	if (b.canSelect((int) x, (int) y)) {
                	b.select((int)x,(int) y);
            	}
            } 
            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
					b.endTurn();

            	}
            	            }
			StdDrawPlus.show(25);
			if (b.winner()!=null){
				System.out.println(b.winner());
				return ;
			}
		}

	}

	public Board(boolean shouldBeEmpty) {
		selectedplace=new int[2];
		selectedplace[0]=-1;
		selectedplace[1]=-1;
		actioned=false;
		piece=new Piece[8][8];
		if (shouldBeEmpty) {
			
			}
		else {
			
			//set the selected place to be negative if no place is chosen;
			
			piece[0][0]=new Piece(true, this, 0, 0, "pawn");
			piece[2][0]=new Piece(true, this, 2, 0,"pawn");
			piece[4][0]=new Piece(true, this,4, 0, "pawn");
			piece[6][0]=new Piece(true,this,6, 0,"pawn");
			piece[1][1]=new Piece(true,this, 1, 1, "shield");
			piece[3][1]=new Piece(true,this, 3, 1, "shield");
			piece[5][1]=new Piece(true,this,5, 1, "shield");
			piece[7][1]=new Piece(true,this, 7, 1, "shield");
			piece[0][2]=new Piece(true,this, 0, 2, "bomb");
			piece[2][2]=new Piece(true,this, 2, 2, "bomb");
			piece[4][2]=new Piece(true,this, 4, 2, "bomb");
			piece[6][2]=new Piece(true,this, 6, 2, "bomb");
			piece[1][5]=new Piece(false,this,1, 5, "bomb");
			piece[3][5]=new Piece(false,this, 3, 5, "bomb");
			piece[5][5]=new Piece(false,this, 5, 5, "bomb");
			piece[7][5]=new Piece(false,this, 7, 5, "bomb");
			piece[0][6]=new Piece(false,this, 0, 6, "shield");
			piece[2][6]=new Piece(false,this, 2, 6, "shield");
			piece[4][6]=new Piece(false,this, 4, 6, "shield");
			piece[6][6]=new Piece(false,this, 6, 6, "shield");
			piece[1][7]=new Piece(false,this, 1, 7, "pawn");
			piece[3][7]=new Piece(false,this, 3, 7, "pawn");
			piece[5][7]=new Piece(false,this, 5, 7, "pawn");
			piece[7][7]=new Piece(false,this, 7, 7, "pawn");
		}
		
			
			
		

	}
		
		
	
	
	public Piece pieceAt(int x, int y){
		if (x>7||y>7||x<0||y<0){
			return null;
		}
		if (piece[x][y]!=null){
			return piece[x][y];
		}
		return null;
	}
	public boolean canSelect(int x, int y){
		//the place selected is a piece
		if (pieceAt(x, y)!=null){
			Piece thepiece1=pieceAt(x, y);
			//hasn't moed any chess
			if (!actioned){
				//the piece is for this turn
				if (thepiece1.side()==turn){
					//has selected a chess
					return true;
				}
			}	
		}
		//the place selected is an empty place
		else{
			//only can slect an empty place when already have a chess selected
			if (selectedplace[0]>=0){
				Piece thepiece2=pieceAt(selectedplace[0], selectedplace[1]);

				//if the chess has moved and captured, it may perform a multi capture
				if (actioned){
					if (thepiece2.hasCaptured()){
						return validJump(selectedplace[0], selectedplace[1], x, y);
					}


				}
				//the chess has not moved and thus can both move and jump to another place
				else{
					int xposi = selectedplace[0];
					int yposi= selectedplace[1];
					return (validJump(xposi, yposi, x, y)||validMove(xposi, yposi, x, y));

				}
				

			}
			
		}
			return false;
			
	}
	private boolean validMove( int xi, int yi, int xf, int yf){
		Piece thepiece=pieceAt(xi, yi);
		if (thepiece!=null&&thepiece.isKing()){
			//four direction both forward and backward
			//walk but not capture
			if ((xf==xi-1||xf==xi+1)&&(yf==yi+1||yf==yi-1)){
				
				if (pieceAt(xf, yf)==null){
					return true;
				}
			}	

			
		}
		else{
			//two direction only forward and no family block the way
			if (thepiece!=null&&thepiece.isFire()){
				//fire only moves up
				//walk not capture
				if((xf==xi-1||xf==xi+1)&&(yf==yi+1)){
					if (pieceAt(xf, yf)==null){
						return true;
					}
				}
			
				

			}
			else if (thepiece!=null&& !thepiece.isFire()){
				//water only moves down
				//walk not capture
				if((xf==xi-1||xf==xi+1)&&(yf==yi-1)){
					if (pieceAt(xf, yf)==null){
						return true;
					}
				}
				
			}
		}
		return false;
	}




	private boolean validJump(int xi, int yi, int xf, int yf){
		Piece thepiece=pieceAt(xi, yi);
		if (thepiece!=null&&thepiece.isKing()){
			//four direction both forward and backward
			

			//capture
			if ((xf==xi-2||xf==xi+2)&&(yf==yi+2||yf==yi-2)){
				if ((pieceAt((xi+xf)/2, (yi+yf)/2)!=null)&&(pieceAt((xi+xf)/2, (yi+yf)/2).side()!=turn)){
					if (pieceAt(xf, yf)==null){
						return true;
					}

					


				}
			}
			
		}
		else{
			//two direction only forward and no family block the way
			if (thepiece!=null&&thepiece.isFire()){
				//fire only moves up
			
				
				//capture
				if ((xf==xi-2||xf==xi+2)&&(yf==yi+2)){
					if ((pieceAt((xi+xf)/2, (yi+yf)/2)!=null) &&(pieceAt((xi+xf)/2, (yi+yf)/2).side()!=turn)){
						if (pieceAt(xf, yf)==null){
							return true;
						}
					}
				}

			}
			else{
				//water only moves down
				
				//capture
				if ((xf==xi-2||xf==xi+2)&&(yf==yi-2)){
					if ((pieceAt((xi+xf)/2, (yi+yf)/2)!=null) &&(pieceAt((xi+xf)/2, (yi+yf)/2).side()!=turn)){
						if (pieceAt(xf, yf)==null){
							return true;
						}
					}
				


				}
			}
		}
		return false;
	}





	



	public void select(int x, int y){
		//has one selected
		if (selectedplace[0]>=0){
			Piece pieceahead=pieceAt(x, y);

			if (pieceahead==null){
				pieceAt(selectedplace[0], selectedplace[1]).move(x, y);
				actioned=true;
				//check if bomb survives
				if (pieceAt(x, y)!=null){
					selectedplace[0]=x;
					selectedplace[1]=y;
				}
				else if(pieceAt(x, y)==null){
					selectedplace[0]=-1;
					selectedplace[1]=-1;
				}
				
				
			}
			else{
				selectedplace[0]=x;
				selectedplace[1]=y;
			}
		}
		else{
			selectedplace[0]=x;
			selectedplace[1]=y;
		}
		
	}
	
		
	public void place(Piece p, int x, int y){
		//kill the enemy on the way and put the chess there instead
		if (p!=null&&0<=x&&0<=y&&x<=7&&y<=7){
			piece[x][y]=p;
			
			

		}

	}

	public Piece remove(int x, int y){
		//deal with bomb which killes itself
		Piece temp=pieceAt(x, y);
		if(temp==null){
			System.out.println("no piece to remove!");
			return null;
			
		}
		else if (x>7||y>7||x<0||y<0){
			System.out.println("out of bounds");
			return null;
		}
		// else {
		// 	if(selectedplace[0]==x&&selectedplace[1]==y){
		// 		selectedplace[0]=-1;
		// 		selectedplace[1]=-1;
		// 	}
			
			
		piece[x][y]=null;
		return temp;

		//}
		
			
		
	}
	public boolean canEndTurn(){
		
		if (actioned){
			return true;
		}
		return false;
	}
	public void endTurn(){
		
		Piece temppiece=pieceAt(selectedplace[0], selectedplace[1]);
		if (temppiece != null){
			temppiece.doneCapturing();
		}
		
		selectedplace[0]=-1;
		selectedplace[1]=-1;
		actioned = false;
		changeturn();
		
		
	}
	public String winner(){
		int firewin=0;
		int waterwin=0;
		for (int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				if (piece[i][j]!=null){
					if (piece[i][j].isFire()){
						firewin+=1;
					}
					else {
						waterwin+=1;
					}
				}
			}
		}
		if (firewin==0&&waterwin!=0){
			return "Water";
		}
		else if (firewin!=0&& waterwin==0){
			return "Fire";
		}
		else if (firewin==0&&waterwin==0){
			return "No one";
		}
		else{
			return null;
		}


	}










}