public class Board {

	private boolean shouldBeEmpty = true;
	private int N;
	private Piece[][] board_pieces = new Piece[8][8];	
	private boolean fire_turn = true;
	private boolean water_turn = false;
	private Piece selected_p = null;
	private boolean has_moved = false;



	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
 				
            }
        }
    }

	public static void main(String[] args){
		int N = 8;
		StdDrawPlus.setXscale(0,N);
		StdDrawPlus.setYscale(0,N);


		Board new_Game = new Board(false);
		drawBoard(N);
		new_Game.printPiece();


		// testing the coordinate 
		// for (int i = 0; i<new_Game.board_pieces.length; i++){
		// 	for (int j = 0; j<new_Game.board_pieces[i].length; j++){
		// 		if (new_Game.board_pieces[i][j]!=null){
		// 		System.out.println("("+Integer.toString(i)+" "+Integer.toString(j)+")");
		// 		}
		// 	}
		// }

		while (new_Game.winner()==null){
			/*
			select process
			*/
			if (StdDrawPlus.mousePressed() && new_Game.selected_p == null){
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (new_Game.canSelect((int)x, (int)y)==true){

					new_Game.select((int)x, (int)y);

				}
			}

			if (StdDrawPlus.mousePressed() && new_Game.selected_p != null){
				double x1 = StdDrawPlus.mouseX();
				double y1 = StdDrawPlus.mouseY();
				/* 
				made a wrong choice
				*/
				if (new_Game.pieceAt((int) x1,(int) y1) != null){
					if (new_Game.canSelect((int)x1,(int)y1)==true){
						new_Game.select((int)x1,(int)y1);					
					}
				}
				else {
					if (new_Game.canSelect((int)x1,(int)y1)==true){
						new_Game.select((int) x1,(int)y1);
						new_Game.drawBoard(N);
						new_Game.printCPiece();
					}

				}

			}

			if (StdDrawPlus.isSpacePressed()){
				if (new_Game.canEndTurn()==true){
					new_Game.endTurn();
					System.out.println("End Turn!");
					for (int i = 0; i<new_Game.board_pieces.length; i++){
						for (int j = 0; j<new_Game.board_pieces[i].length; j++){
							if (new_Game.board_pieces[i][j]!=null){
								System.out.println(Integer.toString(i)+" "+Integer.toString(j));
							}
						}
					}
					if (new_Game.water_turn==true){
						System.out.println("Water's turn.");
					}
					else {
						System.out.println("Fire's turn.");
					}
				}

			}


		}

		System.out.println("Game Over. The winner is "+new_Game.winner());




		 	}
				

	private int getX (Piece p){

		for (int i = 0;i<board_pieces.length;i++){
			for (int j = 0;j<board_pieces.length;j++){
				if (p == board_pieces[i][j]){
					return i;
				}
			}
		}
	return -1;

	}


	private int getY (Piece p){

		for (int i = 0;i<board_pieces.length;i++){
			for (int j = 0;j<board_pieces.length;j++){
				if (p == board_pieces[i][j]){
					return j;
				}
			}
		}

	return -1;

	}					


	

	public Board (boolean shouldBeEmpty){


		this.shouldBeEmpty = shouldBeEmpty;
		if (shouldBeEmpty == false){
			
			addPiece();

		}

	}

	public Piece pieceAt(int x, int y){
		if (x>7 || y>7 || x<0 || y<0){
			return null;
			}
		return board_pieces[x][y];
	}



	public void select (int x, int y){

		if (pieceAt(x,y)!=null){	
			this.selected_p = pieceAt(x,y);		
		}

		else if (pieceAt(x,y)==null){
			if (this.selected_p != null){
				this.selected_p.move(x,y);
				this.has_moved=true;
			}

		}

	}





	public boolean canSelect(int x, int y){
		if (x<8 && y<8 && x>=0 && y>=0){
			if (pieceAt(x,y)!=null){
				if (pieceAt(x,y).isFire()==fire_turn){
					if (this.selected_p == null){
						return true;
					}
					else if (this.has_moved==false){
						return true;
					}
					else{
						System.out.println("1");
						return false;
					}
				}
			}
			// selecting an empty space
			else{
				if (this.selected_p!=null){
					if ((x + y)%2==0){
						int intermediate_x = (x + getX(this.selected_p))/2;
	 					int intermediate_y = (y + getY(this.selected_p))/2;
	 					int difference_x = x - getX(this.selected_p);
	 					int difference_y = y - getY(this.selected_p);
	 					if (this.has_moved==false){
	 						if (Math.abs(difference_x)==1 && Math.abs(difference_y)==1){
	 							if (this.selected_p.isFire()==true){
	 								if (this.selected_p.isKing()==true){
	 									return true;
	 								}

	 								else if (difference_y<0){
	 									return false;
	 								}

	 								else if (difference_y>0){
	 									return true;
	 								}

	 								else{
	 									return false;
	 								}
	 							}
	 							else if (this.selected_p.isFire()==false){
	 								if (this.selected_p.isKing()==true){
	 									return true;
	 								}

	 								else if (difference_y>0){
	 									return false;
	 								}

	 								else if (difference_y<0){
	 									return true;
	 								}

	 								else{
	 									return false;
	 								}
	 							}
	 							else{
	 								System.out.println("2");
	 								return false;
	 							}
	 						}

	 						else if (Math.abs(difference_x)<3 && Math.abs(difference_y)<3){
	 							if (pieceAt(intermediate_x,intermediate_y)!=null){
	 								if (pieceAt(intermediate_x,intermediate_y).isFire()!=fire_turn){
	 									if (pieceAt(intermediate_x,intermediate_y).isFire()!=true){
	 										if (this.selected_p.isKing()==true){
	 											return true;
	 										}
	 										else if (difference_y<0){
	 											return false;
	 										}
	 										else if (difference_y>0){
	 											return true;
	 										}
	 										else{
	 											return false;
	 										}
	 									}
	 									else if (pieceAt(intermediate_x,intermediate_y).isFire()!=false){
	 										if (this.selected_p.isKing()==true){
	 											return true;
	 										}
	 										else if (difference_y>0){
	 											return false;
	 										}
	 										else if (difference_y<0){
	 											return true;
	 										}
	 										else {
	 											return false;
	 										}
	 									}
	 									else{
	 										System.out.println("3");
	 										return false;
	 									}
	 								}
	 								System.out.println("4");
	 								return false;
	 							}
	 							return false;
	 						}
	 					}

	 					else if (this.has_moved==true && this.selected_p.hasCaptured()==true){
	 						if (Math.abs(difference_x)<3 && Math.abs(difference_y)<3){
	 							if (pieceAt(intermediate_x,intermediate_y)!=null){
	 								if (pieceAt(intermediate_x,intermediate_y).isFire()!=fire_turn){
	 									if (pieceAt(intermediate_x,intermediate_y).isFire()!=true){
	 										if (this.selected_p.isKing()==true){
	 											return true;
	 										}
	 										else if (difference_y<0){
	 											return false;
	 										}
	 										else if (difference_y>0){
	 											return true;
	 										}
	 										else {
	 											return false;
	 										}
	 									}
	 									else if (pieceAt(intermediate_x,intermediate_y).isFire()!=false){
	 										if (this.selected_p.isKing()==true){
	 											return true;
	 										}
	 										else if (difference_y>0){
	 											return false;
	 										}
	 										else if (difference_y<0){
	 											return true;
	 										}
	 										else{
	 											return false;
	 										}
	 									}
	 									else{
	 										System.out.println("5");
	 										return false;
	 									}
	 									
	 								}
	 								System.out.println("6");
	 								return false;
	 						    }
	 						    System.out.println("7");
	 						    return false;	
	 						}
	 						System.out.println("8");
	 						return false;


 						}

 					else if (this.has_moved == true && this.selected_p.hasCaptured()==false){
 						System.out.println("has_moved: "+this.has_moved);
 						System.out.println("9");
 						System.out.println("hasCaptured: "+selected_p.hasCaptured());
 						return false;
 					}

 					else {
 						System.out.println("10");
 						return false;
 					}

 				}
 				System.out.println("11");
 				return false;
			}
			System.out.println("12");
			return false;
		}


	}
	System.out.println("13");
	return false;
}






	public void place(Piece p, int x, int y){
		if (x<8 && y<8 && x>=0 && y>=0){
			if(p!=null){
				boolean exist = false;
				int xi = 0;
				int yi = 0;
				for (int i = 0; i<board_pieces.length; i++){
					for (int j = 0; j<board_pieces[i].length; j++){
						if (board_pieces[i][j]==p){
							exist = true;
							xi = i;
							yi = j;
						}
					}
				}
				if (exist == true){
					this.remove(xi,yi);
				}
				this.board_pieces[x][y]=p;
			}
		}


	 }


	public Piece remove(int x, int y){
		
		if (x>7 || y>7 || x<0 || y<0 || pieceAt(x,y)==null){
			System.out.println("Im-po-ssi-ble.");
			return null;
		}

		else {
			System.out.println("removed.");

	 		Piece temp = board_pieces[x][y];
	 		board_pieces[x][y] = null;
	 		return temp;
			}
		}
	


	public boolean canEndTurn(){

		if (this.has_moved == true) {

			return true;
		}
		return false;
	}

	public void endTurn(){
		if (this.canEndTurn() == true){
			this.has_moved = false;
			this.selected_p.doneCapturing();
			this.selected_p = null;
			fire_turn = !fire_turn;
			water_turn = !water_turn;
		}
	}


	public String winner(){
		if (this.typeEmpty("fire")==true && this.typeEmpty("water")){
			return "No one";
		}
		else if (this.typeEmpty("fire")==true){
			return "Water";
		}
		else if (this.typeEmpty("water")){
			return "Fire";
		}
		else{
			return null;
		}
	}
	
	private void addPiece(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j<8; j ++){
				if ((i + j)%2 == 0){
		
						if (j==0){
						
							board_pieces[i][j] = new Piece(true,this,i,j,"pawn");
			
						}
						else if (j == 1){
					
							board_pieces[i][j] = new Piece(true,this,i,j,"shield"); 
		
						}
						else if (j == 2){
					
							board_pieces[i][j] = new Piece(true,this,i,j,"bomb"); 
			
						}
						else if (j == 5){
					
							board_pieces[i][j] = new Piece(false,this,i,j,"bomb");
		
						}
						else if (j == 6){
						
							board_pieces[i][j] = new Piece(false,this,i,j,"shield"); 
	
						}
						else if (j == 7){
			
							board_pieces[i][j] = new Piece(false,this,i,j,"pawn");
			
						}

					

				}
			}


		}
	}

	private void printCPiece(){
		for (int i = 0; i<this.board_pieces.length; i++){
			for (int j = 0; j<this.board_pieces[i].length; j++){
				if (this.board_pieces[i][j]!=null){
					if (this.board_pieces[i][j].isFire()==true){
						if (this.board_pieces[i][j].isBomb()==true){
							if (this.board_pieces[i][j].isKing()==true){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png",1,1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png",1,1);
							}
						}
						else if (this.board_pieces[i][j].isShield()==true) {
							if(this.board_pieces[i][j].isKing()==true){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png",1,1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",1,1);
							}
							
						}
						else{
							if(this.board_pieces[i][j].isKing()==true){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png",1,1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png",1,1);
							}
						}
					}
					else{
						if (this.board_pieces[i][j].isBomb()==true){
							if (this.board_pieces[i][j].isKing()==true){
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png",1,1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",1,1);
							}
						}
						else if (this.board_pieces[i][j].isShield()==true) {
							if (this.board_pieces[i][j].isKing()==true){
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png",1,1);
							}
							else{
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png",1,1);
							}
							
						}
						else{
							if (this.board_pieces[i][j].isKing()==true){
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png",1,1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png",1,1);
							}
						}
					}
				}
			}
		}
	}

	private void printPiece(){
	for (int i = 0; i < 8; i++){
		for (int j = 0; j<8; j ++){
			if ((i + j)%2 == 0){
	
					if (j==0){
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png",1,1);

					}
					else if (j == 1){
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",1,1);

					}
					else if (j == 2){
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png",1,1);
		
					}
					else if (j == 5){
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",1,1);
	
					}
					else if (j == 6){
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png",1,1);

					}
					else if (j == 7){
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png",1,1);

					}

				

			}
		}


		}
	}

	private boolean typeEmpty(String type){
		if (type == "fire"){
			for (int i =0; i<this.board_pieces.length; i++){
				for (int j=0; j<this.board_pieces[i].length; j++){
					if (this.board_pieces[i][j]!=null){
						if (this.board_pieces[i][j].isFire()==true){
							return false;
						}
					}	
				}
			}
			return true;
		}
		else if (type == "water"){
			for (int i =0; i<this.board_pieces.length; i++){
				for (int j=0; j<this.board_pieces[i].length; j++){
					if (this.board_pieces[i][j]!=null){
						if (this.board_pieces[i][j].isFire()!=true){
							return false;
						}
					}
				}
			}
			 return true;

		}
		else{
			System.out.println("Wrong type.");
			return false;
		}
	}








}