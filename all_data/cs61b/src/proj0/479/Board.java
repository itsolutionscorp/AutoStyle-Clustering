
public class Board {
	
	private Piece[][] pieces = new Piece[8][8];
	private int team = 0;

	private boolean selected = false;
	private Piece selected_p;
	private int selected_x = -1;
	private int selected_y = -1;
	private boolean moved = false;
	private boolean gotcha = false;





	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == false){
			for (int y = 0; 8 > y; y += 1){
				for (int x = 0; 8> x; x += 1){
					if ((y == 0) && ((x + y) % 2 == 0)){
						pieces[x][y] = new Piece(true, this, x, y, "pawn");
					}
					else if ((y == 1) && ((x + y) % 2 == 0)){
						pieces[x][y] = new Piece(true, this, x, y, "shield");
					}
					else if ((y == 2) && ((x + y) % 2 == 0)){
						pieces[x][y] = new Piece(true, this, x, y, "bomb");
					}
					else if ((y == 7) && ((x + y) % 2 == 0)){
						pieces[x][y] = new Piece(false, this, x, y, "pawn");
					}
					else if ((y == 6) && ((x + y) % 2 == 0)){
						pieces[x][y] = new Piece(false, this, x, y, "shield");
					}
					else if ((y == 5) && ((x + y) % 2 == 0)){
						pieces[x][y] = new Piece(false, this, x, y, "bomb");
					}
				}
			}
		}
	}

	/*
	Gets piece object or null in XY coordinate from pieces*/
	public Piece pieceAt(int x, int y){
		if (outbound(x,y)){
			return null;
		}
		else if (pieces[x][y] != null){
			return pieces[x][y];
		}
		else{
			return null;
		}
	}

	/*
	Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.*/
	public void place(Piece p, int x, int y){
		if (!outbound(x, y) && (p != null) && ((x + y) %2 == 0)){
			pieces[x][y] = p;
		}
	}

	/*
	Remove and return removed piece*/
	public Piece remove(int x, int y){
		if (outbound(x, y)){
			System.out.println("("+Integer.toString(x)+", "+Integer.toString(y)+") is out of bound");
			return null;
		}
		if (pieceAt(x, y) == null){
			System.out.println("Object is placed at ("+Integer.toString(x)+", "+Integer.toString(y)+")");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		System.out.println("Piece at ("+Integer.toString(x)+", "+Integer.toString(y)+")");
		return temp;
	}

	/*
	Returns true if the square at (x, y) can be selected.*/
	public boolean canSelect(int x, int y){
		//if valid position
		if ((x + y) % 2 == 0 && !outbound(x, y)){


			if (pieceAt(x, y) == null){
				if (selected){

					int dis_x = (x - selected_x);
					int dis_y = (y - selected_y);
					int mid_x = (x + selected_x);
					int mid_y = (y + selected_y);

					// if (Math.abs(dis_x) == 2 && Math.abs(dis_y) == 2){
						Piece mid = pieceAt((mid_x/2), (mid_y/2));
					// }

					//capturing another 
					if (gotcha || ( mid != null && team != mid.side()) ){
						if (selected_p.isKing()){
							return ((Math.abs(dis_x) == 2) && (Math.abs(dis_y) == 2));
						}
						else if (selected_p.isFire()){
							return ((Math.abs(dis_x) == 2) && (dis_y == 2));
						}
						else{
							return ((Math.abs(dis_x) == 2) && (dis_y == -2));
						}
					}

					//moving to empty position
					if (!moved && !gotcha){
						if (selected_p.isKing()){
							return ((Math.abs(dis_x) == 1) && (Math.abs(dis_y) == 1));
						}
						else if (selected_p.isFire()){
							return ((Math.abs(dis_x) == 1) && (dis_y == 1));
						}
						else{
							return ((Math.abs(dis_x) == 1) && (dis_y == -1));
						}
					}
				}
				return false;
			}

			else if (team == pieceAt(x, y).side()){
				if (!moved && !gotcha){
					return true;
				}
			}
		}
		return false;
	}

	/*  
	Assuming canSelect (x,y) returns true, Selects the square at (x, y).
	Outline what should happen when mouse is clicked in GUI*/
	public void select(int x, int y){
		//When valid piece
		if (pieceAt(x, y) != null){

			selected = true;
			selected_p = pieceAt(x, y);
			selected_x = x;
			selected_y = y;			
		}
		//Assumed already selected due to canSelect
		//When valid empty box
		else {
			//Move selected piece
			selected_p.move(x, y);
			place(selected_p ,x, y);

			//Remove original and captured
			remove(selected_x, selected_y);
			
			if (selected_p.hasCaptured()){
				remove(((selected_x+x) /2), ((selected_y+y)/2));
				if (selected_p.isBomb()){
					baaam(x, y);
					selected = false;
				}
				gotcha = true;
				}
		

			//Track selected XY coordinate
			selected_x = x;
			selected_y = y;
			moved = true;
		}
	}

	/*
	Returns true if the turn can end*/
	public boolean canEndTurn(){
		if (moved||gotcha){
			return true;
		}
		return false;
	}

	/*
	Changes turn and preps for next turn*/
	public void endTurn(){
		selected_p.doneCapturing();
		selected = false;
		selected_p = null;
		moved = false;
		gotcha = false;
		team = 1 - team;
	}

	/*
	Return who wins*/
	public String winner(){
		int fire = 0;
		int water = 0;

		for (int x = 0; 8 > x; x += 1){
			for (int y = 0; 8> y; y += 1){
				if (pieceAt(x, y) != null){
					if (pieceAt(x, y).isFire()){
						fire += 1;
					}
					else {
						water += 1;						
					}
				}
			}
		}

		if (null_game()){
			if (water > fire){
				return "Water";
			}
			else if (fire > water){
				return "Fire";
			}
			else if (fire == water){
				return "No one";
			}
		}
		
		if (water == fire && fire == 0){
			return "No one";
		}
		else if (water == 0){
			return "Fire";
		}
		else if (fire == 0){
			return "Water";
		}
		return null;
	}





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Public Methods




	//Private Methods

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	Check for null game*/
	private boolean null_game(){
		int temp = 0;

		for (int y = 0; y < 4; y += 1){
			for (int x = 0; 8> x; x += 1){
				if ((x + y) % 2 == 0){
					if (pieceAt(x, y) != null && canMove(x, y)){
						return false;
					}
				}	
			}
		}
		for (int y = 4; y < 8; y += 1){
			for (int x = 0; 8> x; x += 1){
				if ((x + y) % 2 == 0){
					if (pieceAt(x, y) != null && canMove(x, y)){
						return false;
					}
				}	
			}
		}
		return true;
	}

	/*
	Helper method of null_game*/
	private boolean canMove(int x, int y){
		Piece test = pieceAt(x, y);
		if (team == test.side()){
			
			if (test.isKing() || test.isFire()){

				if ((pieceAt(x+1, y+1) == null && !outbound(x+1, y+1)) 
				   || 
				    (pieceAt(x-1, y+1) == null && !outbound(x+1, y+1))){
					return true;
				}
				else if (pieceAt(x+2, y+2) == null && !outbound(x+2, y+2)){
					if ( (pieceAt(x+1, y+1) != null) && (team != pieceAt(x+1, y+1).side()) ){
						return true;
					}
				}
				else if (pieceAt(x-2, y+2) == null && !outbound(x-2, y+2)){
					if ( (pieceAt(x-1, y+1) != null) && (team != pieceAt(x-1, y+1).side()) ){
						return true;
					}
				}				
			}

			if (test.isKing() || !test.isFire()){

				if ((pieceAt(x+1, y-1) == null && !outbound(x+1, y-1)) 
				   || 
				    (pieceAt(x-1, y-1) == null && !outbound(x+1, y-1))){
					return true;
				}
				else if (pieceAt(x+2, y-2) == null && !outbound(x+2, y-2)){
					if ( (pieceAt(x+1, y-1) != null) && (team != pieceAt(x+1, y-1).side()) ){
						return true;
					}
				}
				else if (pieceAt(x-2, y-2) == null && !outbound(x-2, y-2)){
					if ( (pieceAt(x-1, y-1) != null) && (team != pieceAt(x-1, y-1).side()) ){
						return true;
					}
				}				
			}		
		}
		return false;
	}

	/*
	Side effect of Bomb's catch*/
	private void baaam(int x, int y){
		//suicide
		remove(x, y);

		//splash damage
		if (pieceAt(x+1, y+1) != null && !pieceAt(x+1, y+1).isShield()){
			remove(x+1, y+1);
		}
		if (pieceAt(x+1, y-1) != null && !pieceAt(x+1, y-1).isShield()){
			remove(x+1, y-1);
		}
		if (pieceAt(x-1, y+1) != null && !pieceAt(x-1, y+1).isShield()){
			remove(x-1, y+1);
		}
		if (pieceAt(x-1, y-1) != null && !pieceAt(x-1, y-1).isShield()){
			remove(x-1, y-1);
		}
	}

	/*
	Check if selected is in the board*/
	private boolean outbound(int x, int y){
		return (x >= 8|| y >= 8||x < 0||y < 0);
	}

	/*
	Draws out basic game board*/
	private static void drawBoard(int size){
		for (int x = 0; size > x; x += 1){
			for (int y = 0; size> y; y += 1){
		        if ((x + y) % 2 == 0){
		        	StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
		        }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                }
            	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
          	}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	public static void main (String args[]){
		//Setting up game's board and frame
		StdDrawPlus.setScale(0, 8);
		Board play = new Board(false);

		// Piece b1 = new Piece(true, play, 0, 0, "shield");
		// Piece b2 = new Piece(true, play, 0, 0, "shield");
		// Piece b3 = new Piece(true, play, 0, 0, "shield");
		// Piece b4 = new Piece(true, play, 0, 0, "shield");
		// Piece c1 = new Piece(false, play, 0, 0, "shield");
		// Piece c2 = new Piece(false, play, 0, 0, "shield");
		// Piece c3 = new Piece(false, play, 0, 0, "shield");
		// Piece c4 = new Piece(false, play, 0, 0, "shield");
	
		// play.place(b1, 0, 4);
		// play.place(b2, 2, 4);
		// play.place(b3, 4, 4);
		// play.place(b4, 6, 4);
		// play.place(c1, 1, 3);
		// play.place(c2, 3, 3);
		// play.place(c3, 5, 3);
		// play.place(c4, 7, 3);

		while (play.winner() == null){
			drawBoard(8);
			
			//if something selected
			if (play.selected){
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(play.selected_x + .5, play.selected_y + .5, .5);
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (play.canEndTurn()){
					play.endTurn();
				}
			}
			
			if (StdDrawPlus.mousePressed()) {
                int x_position = (int) StdDrawPlus.mouseX();
                int y_position = (int) StdDrawPlus.mouseY();

                if (play.canSelect(x_position, y_position)){
                	play.select(x_position, y_position);
                }
            }
			//Drawing pieces in right positions
			for (int y = 0; 8 > y; y += 1){
				for (int x = 0; 8> x; x += 1){
					if (play.pieces[x][y] != null){
						//fire pieces
						if (play.pieces[x][y].isFire()){
							if (play.pieces[x][y].isKing()){
								if (play.pieces[x][y].isBomb()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/bomb-fire-crowned.png", 1, 1);
								}
								else if (play.pieces[x][y].isShield()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/shield-fire-crowned.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/pawn-fire-crowned.png", 1, 1);
								}
							}
							else {
								if (play.pieces[x][y].isBomb()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/bomb-fire.png", 1, 1);
								}
								else if (play.pieces[x][y].isShield()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/shield-fire.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/pawn-fire.png", 1, 1);
								}
							}
						}
						//water pieces
						else {
							if (play.pieces[x][y].isKing()){
								if (play.pieces[x][y].isBomb()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/bomb-water-crowned.png", 1, 1);
								}
								else if (play.pieces[x][y].isShield()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/shield-water-crowned.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/pawn-water-crowned.png", 1, 1);
								}
							}
							else {
								if (play.pieces[x][y].isBomb()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/bomb-water.png", 1, 1);
								}
								else if (play.pieces[x][y].isShield()){
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/shield-water.png", 1, 1);
								}
								else {
									StdDrawPlus.picture(x+ 0.5, y+0.5, "img/pawn-water.png", 1, 1);
								}
							}
						}
					}
				}
			}

			//refresh every 10 mili-second
			StdDrawPlus.show(5);
		}
		System.out.println(play.winner());
	}


}







/* inspired by: UC Berkeley CS61B Spring 2015 hw1 - NBody.java
				UC Berkeley CS61B Spring 2015 proj0 - StdDrawDemo.java
				for rounding down numbers - http://stackoverflow.com/questions/10280520/convert-double-to-int-rounded-down
*/









