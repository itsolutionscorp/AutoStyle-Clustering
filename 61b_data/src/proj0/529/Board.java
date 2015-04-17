public class Board {

	private boolean epty; 
	// which player is playing now(fire first)
	private int player=0; 
	// selected stores what have been selected
	private int selectedX;
	private int selectedY;
	private Piece selected;
	private boolean selectedTOF = false;
	private boolean moved = false;
	private int rNum_0 = 0;
	private int rNum_1 = 0;
	private Piece[][] pieceArray;
	private boolean cont = true;

	public static void main(String args[]){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		b.drawpiece(N);
		while (b.cont){
			if (StdDrawPlus.mousePressed()) {
			    double x = StdDrawPlus.mouseX();
			    double y = StdDrawPlus.mouseY();
			    StdDrawPlus.show(10);
			    if (b.canSelect((int) x,(int) y)){
					b.select((int) x, (int) y);
					b.blank();
					b.plusimage();
			    }            	
			}
			if (StdDrawPlus.isSpacePressed()) {
               	if(b.winner()!=null){
               		System.out.println(b.winner());
                	b.cont=false;
             	}
             	if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
			
		}
		
		
	}

	private void blank(){
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	}
        }
	}

	private void plusimage(){
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i,j)!=null){
                	StdDrawPlus.picture(i+ .5, j+ .5,imageTyp(pieceAt(i,j)),1,1);
                }
        	}
        }
	}

	private String imageTyp(Piece p){
		if(p.side()==0){
			if(p.isKing()){
				return "img/" +typ(p)+"-fire-crowned.png";
			}
			else{
				return "img/" +typ(p)+"-fire.png";
			}
		}
		else{
			if(p.isKing()){
				return "img/" + typ(p)+"-water-crowned.png";
			}
			else{
				return "img/" + typ(p)+"-water.png";
			}
		}
	}

	private String typ(Piece p){
		if(p.isBomb()){
			return "bomb";
		}
		if(p.isShield()){
			return "shield";
		}
		else{
			return "pawn";
		}
	}

	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == true) {
            pieceArray = new Piece[8][8];
        }
        else {
            pieceArray = new Piece[8][8];
            for (int i = 0; i < 7; i=i+2) {
				Piece a = new Piece(true,this,i,0,"pawn");
				Piece b = new Piece(true,this,i,2,"bomb");
				Piece c = new Piece(false,this,i,6,"shield");
				place(a,i,0);
				place(b,i,2);
				place(c,i,6);
				
			}
			for (int i = 1; i < 8; i=i+2) {
				Piece a= new Piece(true,this,i,1,"shield");
				Piece b= new Piece(false,this,i,5,"bomb");
				Piece c= new Piece(false,this,i,7,"pawn");
				place(a,i,1);
				place(b,i,5);
				place(c,i,7);
			}
            

        }
	}

	private void drawpiece(int N){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	}
        }
		for (int i = 0; i < N-1; i=i+2) {
			
			StdDrawPlus.picture(i+ .5, .5,"img/pawn-fire.png",1,1);
			StdDrawPlus.picture(i+ .5, 2.5,"img/bomb-fire.png",1,1);
			StdDrawPlus.picture(i+ .5, 6.5,"img/shield-water.png",1,1);


		}
		for (int i = 1; i < N; i=i+2) {
			
			StdDrawPlus.picture(i+ .5, 1.5,"img/shield-fire.png",1,1);
			StdDrawPlus.picture(i+ .5, 5.5,"img/bomb-water.png",1,1);
			StdDrawPlus.picture(i+ .5, 7.5,"img/pawn-water.png",1,1);

		}
	}

	public Piece pieceAt(int x, int y){
		if((x>7) || (y>7) ||(x<0)||(y<0)){
			return null;
		}
		else{return pieceArray[y][x];}
		
	}
	// This is to determine whether or not the move is valid.
	private boolean validMove(int xi, int yi, int xf, int yf){
		if (pieceAt(xi,yi).isKing()){
			if((Math.abs(xf-xi)==1)&&(Math.abs(yf-yi)==1)){
				if(pieceAt(xf,yf)==null){return true;}
				else{return false;}
			}
			if((Math.abs(xf-xi)==2)&&(Math.abs(yf-yi)==2)&&(pieceAt(xf,yf)==null)){
				if(pieceAt((xf+xi)/2, (yf+yi)/2)==null){
					return false;
				}
				else if(pieceAt(xi,yi).side()!= pieceAt((xf+xi)/2, (yf+yi)/2).side()){
					return true;
				}
				return false;
			}
			return false;
		}
		else if (pieceAt(xi,yi).isFire()){
			if((Math.abs(xf-xi)==1)&&(yf-yi==1)){
				if(pieceAt(xf,yf)==null){
					return true;
				}
				else{return false;}
			}
			if((Math.abs(xf-xi)==2)&&(yf-yi==2)&&(pieceAt(xf,yf)==null)){
				if(pieceAt((xf+xi)/2, (yf+yi)/2)==null){
					return false;
				}
				else if(pieceAt(xi,yi).side()!= pieceAt((xf+xi)/2, (yf+yi)/2).side()){
					return true;
				}
				return false;
			}
			return false;
		}
		else{
			if((Math.abs(xf-xi)==1)&&(yf-yi==-1)){
				if(pieceAt(xf,yf)==null){return true;}
				else{return false;}
			}
			if((Math.abs(xf-xi)==2)&&(yf-yi==-2)&&(pieceAt(xf,yf)==null)){
				if(pieceAt((xf+xi)/2, (yf+yi)/2)==null){
					return false;
				}
				else if(pieceAt(xi,yi).side()!= pieceAt((xf+xi)/2, (yf+yi)/2).side()){
					return true;
				}
				return false;
			}
			return false;
				
		}
	}
	// validMulCapture is used in canselect to determine 
	// whether or not the multicapture move is valid given 
	// the piece has finished one capture.
	private boolean validMulCapture(int xi, int yi, int xf, int yf){

		if (pieceAt(xi,yi).isKing()){
			if((Math.abs(xf-xi)==2)&&(Math.abs(yf-yi)==2)&&(pieceAt(xf,yf)==null)){
				if(pieceAt((xf+xi)/2, (yf+yi)/2)==null){
					return false;
				}
				else if(pieceAt(xi,yi).side()!= pieceAt((xf+xi)/2, (yf+yi)/2).side()){
					return true;
				}
				return false;
			}
			return false;
		}
		if (pieceAt(xi,yi).isFire()){
			if((Math.abs(xf-xi)==2)&&(yf-yi==2)&&(pieceAt(xf,yf)==null)){
				if(pieceAt((xf+xi)/2, (yf+yi)/2)==null){
					return false;
				}
				else if(pieceAt(xi,yi).side()!= pieceAt((xf+xi)/2, (yf+yi)/2).side()){
					return true;
				}
				return false;
			}
			return false;
		}
		else{
			if((Math.abs(xf-xi)==2)&&(yf-yi==-2)&&(pieceAt(xf,yf)==null)){
				if(pieceAt((xf+xi)/2, (yf+yi)/2)==null){
					return false;
				}
				else if(pieceAt(xi,yi).side()!= pieceAt((xf+xi)/2, (yf+yi)/2).side()){
					return true;
				}
				return false;
			}
			return false;
		}
	}
	public boolean canSelect(int x, int y){
		if(pieceAt(x,y)==null){
			if ((selectedTOF == true)&&(moved==false)){
				if(validMove(selectedX,selectedY,x,y)){return true;}
				else{return false;}
			}
			if ((selectedTOF==true)&&(moved==true)){
				if (selected.hasCaptured()){
					if (validMulCapture(selectedX,selectedY,x,y)){
						return true;
					}
					else{return false;}
				}
				else{return false;}
			}
			else{return false;}
		}
		if(pieceAt(x,y)!=null) {
			if(player != pieceArray[y][x].side()){
				return false;
			}
			else if(selected==null){
				return true;
			}
			else if((selected!=null)&&(moved== false )){
				return true;
			}
			else{return false;}
		}	
		else{return false;}
	}
	public void select(int x, int y){
			// if the place has no piece, given calselect is true, it is the only case that the piece 
			// in between selected piece and selected location should be removed.
			if (selected!=null){
				if(pieceAt(x,y)==null){
					int x1 = selectedX;
					int y1 = selectedY;
					if((Math.abs(x1-x)==1)){
					// place(selected,x,y);
						remove(x1,y1);
						selected.move(x,y);
						place(selected,x,y);
						moved=true;
					}
					if ((Math.abs(x1-x)==2)){
						int to_be_x = (x + selectedX)/2;
						int to_be_y = (y + selectedY)/2;

						if(selected.isBomb()){
							place(selected,x,y);
							moved = true;
							selected.move(x,y);
							remove(selectedX,selectedY);
							remove(to_be_x,to_be_y);
							selectedX = x;
							selectedY = y;
							if((selectedX==0)&&(selectedY==0)){
								remove(selectedX,selectedY);
								if (pieceAt(0,1)!=null){
									if (pieceAt(0,1).isShield()==false){
										remove(0,1);
									}
								}
								if (pieceAt(1,1)!=null){
									if (pieceAt(1,1).isShield()==false){
										remove(1,1);
									}
								}
								if (pieceAt(1,0)!=null){
									if (pieceAt(1,0).isShield()==false){
										remove(1,0);
									}
								}
							}
							if((selectedX==7)&&(selectedY==0)){
								remove(selectedX,selectedY);
								if (pieceAt(6,0)!=null){
									if (pieceAt(6,0).isShield()==false){
										remove(6,0);
									}
								}
								if (pieceAt(6,1)!=null){
									if (pieceAt(6,1).isShield()==false){
										remove(6,1);
									}
								}
								if (pieceAt(7,1)!=null){
									if (pieceAt(7,1).isShield()==false){
										remove(7,1);
									}
								}
							}
							if((selectedX==7)&&(selectedY==7)){
								remove(selectedX,selectedY);
								if (pieceAt(6,7)!=null){
									if (pieceAt(6,7).isShield()==false){
										remove(6,7);
									}
								}
								if (pieceAt(6,6)!=null){
									if (pieceAt(6,6).isShield()==false){
										remove(6,6);
									}
								}
								if (pieceAt(7,6)!=null){
									if (pieceAt(7,6).isShield()==false){
										remove(7,6);
									}
								}

							}
							if((selectedX==0)&&(selectedY==7)){
								remove(selectedX,selectedY);
								if (pieceAt(0,6)!=null){
									if (pieceAt(0,6).isShield()==false){
										remove(0,6);
									}
								}
								if (pieceAt(1,7)!=null){
									if (pieceAt(1,7).isShield()==false){
										remove(1,7);
									}
								}
								if (pieceAt(1,6)!=null){
									if (pieceAt(1,6).isShield()==false){
										remove(1,6);
									}
								}
							}
							for(int i=x-1;i<=x+1;i++){
								for(int j=y-1;j<=y+1;j++){
									if(pieceAt(i,j)==null){
										continue;
									}
									if (pieceAt(i,j).isShield()){
										continue;
									}
									else{
										remove(i,j);
									}
								}
							} 	
							selectedTOF = false;
							moved = true;
						}
						else{
							remove(to_be_x,to_be_y);
							place(selected,x,y);
							remove(selectedX,selectedY);
							selectedX=x;
							selectedY=y;
							selected.move(x,y);
							moved=true;
							selectedTOF = true;
						}
					}
				}
				else if(pieceAt(x,y).side()==player){
					selectedTOF=true;
					selected = pieceArray[y][x];
					selectedX = x;
					selectedY = y;
				}
			}
			else{
				selectedTOF=true;
				selected = pieceArray[y][x];
				selectedX = x;
				selectedY = y;
				moved=false;
			}
	}
	// #place put piece p on (x,y)
	public void place(Piece p, int x, int y){
		if((x>7) || (y>7)){
		}

		else {
			if (p.side()==0){
				rNum_0 = rNum_0 + 1;
			} 
			else{rNum_1 = rNum_1 + 1;}
			pieceArray[y][x] = p;
			// moved=true;
		}
	}

	public Piece remove(int x, int y){
		if((x>7)||(y>7)){
			System.out.println("Out of bound! the piece can not be removed!");
			return null;
		}
		else{
			Piece rPiece = pieceAt(x, y);
			if (pieceAt(x,y).side()==0){
				rNum_0 = rNum_0 - 1;
			} 
			else{rNum_1 = rNum_1 - 1;}
			pieceArray[y][x]= null;
			return rPiece;
		}
	}

	public boolean canEndTurn(){
		if (moved == true) {
            return true;
        }
        else {
            return false;
        }
	}
	public void endTurn() {
		
			if (player == 0){
				player = 1;
			}
			else{
				player = 0;
			}
			selected.doneCapturing();
			selected = null;
			selectedTOF = false;
			moved = false;
		
	}
    
	public String winner(){
		if((rNum_0==0)&&(rNum_1==0)){
			return "No one";
		}
		if((rNum_0==0)&&(rNum_1!=0)){
			return "Water";
		}
		if((rNum_1==0)&&(rNum_0!=0)){
			return "Fire";
		}
		else{
			return null;
		}

	}
	// determin whether there are pieces on board.
	private boolean pOnBoard(){
		for(int i = 0; i< 8; i++){
			for(int j = 0; j < 8; j++){
				if (pieceAt(i,j) != null){
					return true;
				}
			}
		}
		return false;
	}
}





