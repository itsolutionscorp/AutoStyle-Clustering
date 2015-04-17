public class Board{

	//length and width of the board
	private int x_length = 8;
	private int y_length = 8;

	// 1 is Fire player 1's turn
	// -1 is Water player 2's turn
	private int turn = 1;

	//Matrix of the board, including all the pieces
	private Piece [][] piece = new Piece[8][8];

	//Which piece has been selected by the player
	private Piece selectedPiece = null;


	//Records the state of whether or not a player has moved a piece yet
	private boolean moved = false;


	//Returns whether the player has performed a capture
	private boolean has_capturedBoard = false;

	//Number of moves made this turn
	private int num_moves = 0;

	//SelectedPiece's x and y coordinates
	private int x_c;
	private int y_c;

	

//---------------------------------------------------------------------------
//---------------------------------------------------------------------------

	//Creating all the pieces

	//Fire pieces:
	//Pawn
	private Piece pawnfire1 = new Piece(true, this, 0, 0, "pawn");
	private Piece pawnfire2 = new Piece(true, this, 2, 0, "pawn");
	private Piece pawnfire3 = new Piece(true, this, 4, 0, "pawn");
	private Piece pawnfire4 = new Piece(true, this, 6, 0, "pawn");

	//Shield
	private Piece shieldfire1 = new Piece(true, this, 1, 1, "shield");
	private Piece shieldfire2 = new Piece(true, this, 3, 1, "shield");
	private Piece shieldfire3 = new Piece(true, this, 5, 1, "shield");
	private Piece shieldfire4 = new Piece(true, this, 7, 1, "shield");

	//Bomb
	private Piece bombfire1 = new Piece(true, this, 0, 2, "bomb");
	private Piece bombfire2 = new Piece(true, this, 2, 2, "bomb");
	private Piece bombfire3 = new Piece(true, this, 4, 2, "bomb");
	private Piece bombfire4 = new Piece(true, this, 6, 2, "bomb");


	//Water Pieces
	//Pawn
	private Piece pawnwater1 = new Piece(false, this, 1, 7, "pawn");
	private Piece pawnwater2 = new Piece(false, this, 3, 7, "pawn");
	private Piece pawnwater3 = new Piece(false, this, 5, 7, "pawn");
	private Piece pawnwater4 = new Piece(false, this, 7, 7, "pawn");

	private Piece shieldwater1 = new Piece(false, this, 0, 6, "shield");
	private Piece shieldwater2 = new Piece(false, this, 2, 6, "shield");
	private Piece shieldwater3 = new Piece(false, this, 4, 6, "shield");
	private Piece shieldwater4 = new Piece(false, this, 6, 6, "shield");

	private Piece bombwater1 = new Piece(false, this, 1, 5, "bomb");
	private Piece bombwater2 = new Piece(false, this, 3, 5, "bomb");
	private Piece bombwater3 = new Piece(false, this, 5, 5, "bomb");
	private Piece bombwater4 = new Piece(false, this, 7, 5, "bomb");

//---------------------------------------------------------------------------
//---------------------------------------------------------------------------


	//Constructor
	public Board(boolean shouldBeEmpty){

		if(shouldBeEmpty){
			drawBoardBlank(8);
		}else{
			
			//Place all the pieces within the array
			//Placing fire pieces
			this.place(pawnfire1, 0, 0);
			this.place(pawnfire2, 2, 0);
			this.place(pawnfire3, 4, 0);
			this.place(pawnfire4, 6, 0);
			
			this.place(shieldfire1, 1, 1);
			this.place(shieldfire2, 3, 1);
			this.place(shieldfire3, 5, 1);
			this.place(shieldfire4, 7, 1);
			
			this.place(bombfire1, 0, 2);
			this.place(bombfire2, 2, 2);
			this.place(bombfire3, 4, 2);
			this.place(bombfire4, 6, 2);
			
			//Placing water pieces
			this.place(pawnwater1, 1, 7);
			this.place(pawnwater2, 3, 7);
			this.place(pawnwater3, 5, 7);
			this.place(pawnwater4, 7, 7);

			this.place(shieldwater1, 0, 6);
			this.place(shieldwater2, 2, 6);
			this.place(shieldwater3, 4, 6);
			this.place(shieldwater4, 6, 6);
			
			this.place(bombwater1, 1, 5);
			this.place(bombwater2, 3, 5);
			this.place(bombwater3, 5, 5);
			this.place(bombwater4, 7, 5);
			
			drawBoardFull(8);

		}
	}

	//Draw a blank board
	private void drawBoardBlank(int length){
		StdDrawPlus.setXscale(0, length);
        StdDrawPlus.setYscale(0, length);
		for(int i=0; i<length; i++){
			for(int j = 0; j < length; j++){
				if((i+j)%2==0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i+0.5, j+0.5, 0.5);
			}
		}
	}

	//Draw a full board with all the pieces in place
	private void drawBoardFull(int length){

		StdDrawPlus.setXscale(0, length);
        StdDrawPlus.setYscale(0, length);
		
		for(int i=0; i <length; i++){
			for(int j = 0; j < length; j++){

				if((i+j)%2==0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i+0.5, j+0.5, 0.5);

				//Drawing all the pieces
				Piece p = pieceAt(i, j);

				if(p!=null){
					//Fire element
					if(p.isFire()){
						if(!p.isBomb() && !p.isShield()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-fire.png", 1, 1);
						}
						if(p.isShield()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-fire.png", 1, 1);
						}
						if(p.isBomb()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-fire.png", 1, 1);
						}
						if(!p.isBomb() && !p.isShield() && p.isKing()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-fire-crowned.png", 1, 1);
						}
						if(p.isShield() && p.isKing()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-fire-crowned.png", 1, 1);
						}
						if(p.isBomb() && p.isKing()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-fire-crowned.png", 1, 1);
						}
					}

					//Water elements
					if(!p.isFire()){
						if(!p.isBomb() && !p.isShield()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-water.png", 1, 1);
						}
						if(p.isShield()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-water.png", 1, 1);
						}	
						if(p.isBomb()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-water.png", 1, 1);
						}				
						if(!p.isBomb() && !p.isShield() && p.isKing()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/pawn-water-crowned.png", 1, 1);
						}	
						if(p.isShield() && p.isKing()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/shield-water-crowned.png", 1, 1);
						}	
						if(p.isBomb() && p.isKing()){
							StdDrawPlus.picture(i+0.5, j+0.5, "img/bomb-water-crowned.png", 1, 1);
						}	
					}
				}
				
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if(x >= x_length || y >= y_length || y < 0 || x < 0){
			return null;
		}
		if(piece[x][y] == null){
			return null;
		}
		return piece[x][y];
	}


	public boolean canSelect(int x, int y){
			   	
		//Checking bounds
		if(OutOfBounds(x,y)){
			return false;
		}

		//If the player hasn't selected a piece yet, 
		//The space that the player selected is empty.
		if(selectedPiece==null && this.pieceAt(x,y)==null){
			return false;
		}

		//**********************************
		//Selecting a square with a piece.
		//**********************************

		//Player hasn't selected a piece yet
		if(selectedPiece==null && moved == false){

			if(this.pieceAt(x,y)!= null){

				//Fire's turn
				if(turn>0){
					if(this.pieceAt(x,y).isFire()){
						if(selectedPiece == null){
							return true;
						}
					}
				}

				//Water's turn
				if(turn<0){
					if(!this.pieceAt(x,y).isFire()){
						if(selectedPiece == null){
							return true;
						}						
					}
				}
			}
		}
		
		//Player has already selected a piece
		//and is re-selecting another piece
		if(selectedPiece!=null && moved==false){

			//Fire's turn
			if(turn>0){
				if(this.pieceAt(x,y)!=null){
					if(this.pieceAt(x,y).isFire()){
						return true;
					}
				}
			}

			//Water's turn
			if(turn<0){
				if(this.pieceAt(x,y)!=null){
					if(!this.pieceAt(x,y).isFire()){
						return true;
					}
				}
			}						
		}

		if(selectedPiece!=null){ 
			return helperCapture(x, y);
		}
		return false; 
	}	

	public void select(int x, int y){ 

		//Player is selecting a piece, prepping that piece for movement. 
		if(this.pieceAt(x,y)!=null){
			selectedPiece = this.pieceAt(x,y);
		}

		//Player selects an empty square for the selectedPiece to move to.
		if(this.pieceAt(x,y)==null && selectedPiece!=null){
			selectedPiece.move(x, y);  
			moved = true;
		}
	}


	public void place(Piece p, int x, int y){
		if(x>x_length || y > y_length){
			return;
		}
		piece[x][y] = p;

	}

	public Piece remove(int x, int y){

		if(x > x_length || y > y_length){
			StdDrawPlus.text(x, y, "Index out of Bounds");
			return null;
		}
		if(this.pieceAt(x,y) == null){
			StdDrawPlus.text(x+0.5, y+0.5, "No Piece at Coordinates");
			return null;
		}
		Piece temp = this.pieceAt(x,y);
		this.place(null, x, y);
		return temp;
	}

	public boolean canEndTurn(){
		//Fire's turn: moved has to be changed to -1 
		if(moved==true){
			return true;
		}
		return false;
	}


	public void endTurn(){
		turn = -turn;
		has_capturedBoard = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		moved = false;
		num_moves = 0;
		
	}


	public String winner(){
		int water = 0;
		int fire = 0;

		if(piece==null){
			return "No one";
		}

		for(int i=0; i< x_length; i++){
			for(int j = 0; j < x_length; j++){
				if(piece[i][j]!=null){
					Piece temp = piece[i][j];
					if(temp.isFire()){
						fire++;
					}else{
						water++;
					}
				}
			}
		}

		if(fire==0 && water==0){
			return "No one";
		}
		if(water==0){
			return "Fire";
		}
		if(fire==0){
			return "Water";
		}
		
		return null;
	}

//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------

	//Helper Methods
	private boolean OutOfBounds(int x, int y){ 
		if(x >= 8 || y >= 8){ 
			return true;
		}
		return false;
	}

	//HelperMethod for Capture in CanSelect
	private boolean helperCapture(int x, int y){ 
		
		for(int i=0; i<8; i++){
			for(int j = 0; j < 8; j++){
				if(piece[i][j]==selectedPiece){
					x_c = i; //SelectedPiece's x coordinate
					y_c = j; //SelectedPiece's y coordinate
				} 
			}
		}

		//Possible locations for SelectedPiece to move to without capture
			
		/** x_lu --> left up, x coordinate
		    x_ru --> right up, x coordinate
		    y_ld     left down, y coordinate
		    y_rd     right down, y coordinate
		 */

		int x_lu = x_c - 1; 
		int y_lu = y_c + 1;

		int x_ru = x_c + 1;
		int y_ru = y_c + 1;

		int x_ld = x_c - 1;
		int y_ld = y_c - 1;

		int x_rd = x_c + 1;
		int y_rd = y_c - 1;


		//Possible locations for selectedPiece to move to with capture
		/** x_lu_c --> left up, x coordinate
		    x_ru_c --> right up, x coordinate
		    y_ld_c     left down, y coordinate
		    y_rd_c     right down, y coordinate
		 */

		int x_lu_c = x_c - 2;
		int y_lu_c = y_c + 2;

		int x_ld_c = x_c - 2;
		int y_ld_c = y_c - 2;

		int x_ru_c = x_c + 2;
		int y_ru_c = y_c + 2;

		int x_rd_c = x_c + 2;
		int y_rd_c = y_c - 2;

		//*****************************************
		//Selecting empty square, without capture. 
		//*****************************************
	
		if(this.pieceAt(x,y)==null && moved==false){
			
			//Fire's turn
			if(turn>0){
				
				//Uncrowned piece can only move up
				if(!selectedPiece.isKing()){

					if(x==x_lu && y == y_lu){
						return true;
					}
					if(x==x_ru && y == y_ru){
						return true;
					}
					
				}else{
				//Crowned piece can move up and down
					if(x==x_lu && y == y_lu){
						
						return true;
					}
					if(x==x_ld && y == y_ld){
						
						return true;
					}
					if(x==x_ru && y == y_ru){
						
						return true;
					}
					if(x==x_rd && y == y_rd){
						
						return true;
					}
				}
				
			}//end of fire's turn

			//Water's turn
			if(turn<0){
				
				//Uncrowned piece can only move down
				if(!selectedPiece.isKing()){

					if(x==x_ld && y == y_ld){			
						return true;
					}
					if(x==x_rd && y == y_rd){
						return true;
					}
					
				}else{
				//Crowned piece can move up and down
					if(x==x_lu && y == y_lu){
						return true;
					}
					if(x==x_ld && y == y_ld){			
						return true;
					}
					if(x==x_ru && y == y_ru){
						return true;
					}
					if(x==x_rd && y == y_rd){					
						return true;
					}
				}
				
			}//end of waters turn
			
		}

		//**********************************
		//Selecting a square with capture
		//**********************************

		if(this.pieceAt(x,y) == null && (moved==false || has_capturedBoard==true)){
			
			has_capturedBoard = true;

			//Fire's turns
			if(this.pieceAt(x,y)!=null){
				
				this.pieceAt(x,y).hasCaptured();
				num_moves=1;
				
			}
			
			if(turn>0){

				//Regular piece
				if(!selectedPiece.isKing()){
					if(x==x_lu_c && y == y_lu_c){ //if there's an empty square 2 squares across
						if(this.pieceAt(x_lu, y_lu)!=null){ //if there's a piece in the square across
							if(!this.pieceAt(x_lu, y_lu).isFire()){ //if the piece is Fire(enemy)
								return true;
							}
						}
					}

					if(x==x_ru_c && y == y_ru_c){
						if(this.pieceAt(x_ru, y_ru)!=null){
							if(!this.pieceAt(x_ru, y_ru).isFire()){
								return true;
							}
						}
					}

				}

				//Crowned piece
				if(selectedPiece.isKing()){
					if(x==x_lu_c && y == y_lu_c){ 
						if(this.pieceAt(x_lu, y_lu)!=null){ 
							if(!this.pieceAt(x_lu, y_lu).isFire()){ 
								return true;
							}
						}
					}

					if(x==x_ld_c && y == y_ld_c){
						if(this.pieceAt(x_ld, y_ld)!=null){
							if(!this.pieceAt(x_ld, y_ld).isFire()){
								return true;
							}
						}
					}

					if(x==x_ru_c && y == y_ru_c){
						if(this.pieceAt(x_ru, y_ru)!=null){
							if(!this.pieceAt(x_ru, y_ru).isFire()){
								return true;
							}
						}
					}

					if(x==x_rd_c && y == y_rd_c){
						if(this.pieceAt(x_rd, y_rd)!=null){
							if(!this.pieceAt(x_rd, y_rd).isFire()){						
								return true;
							}
						}
					}
				}
																
			} //closes off fire's turn

			//Water's turn
			if(turn<0){

				//Regular piece
				if(!selectedPiece.isKing()){
					if(x==x_ld_c && y == y_ld_c){ 
						if(this.pieceAt(x_ld, y_ld)!=null){ 
							if(this.pieceAt(x_ld, y_ld).isFire()){ 
								return true;
							}
						}
					}

					if(x==x_rd_c && y == y_rd_c){
						if(this.pieceAt(x_rd, y_rd)!=null){
							if(this.pieceAt(x_rd, y_rd).isFire()){			
								return true;
							}
						}
					}

				}

				//Crowned piece
				if(selectedPiece.isKing()){
					if(x==x_lu_c && y == y_lu_c){ 
						if(this.pieceAt(x_lu, y_lu)!=null){ 
							if(this.pieceAt(x_lu, y_lu).isFire()){ 
								return true;
							}
						}
					}

					if(x==x_ld_c && y == y_ld_c){
						if(this.pieceAt(x_ld, y_ld)!=null){
							if(this.pieceAt(x_ld, y_ld).isFire()){								
								return true;
							}
						}
					}

					if(x==x_ru_c && y == y_ru_c){
						if(this.pieceAt(x_ru, y_ru)!=null){
							if(this.pieceAt(x_ru, y_ru).isFire()){							
								return true;
							}
						}
					}

					if(x==x_rd_c && y == y_rd_c){
						if(this.pieceAt(x_rd, y_rd)!=null){
							if(this.pieceAt(x_rd, y_rd).isFire()){					
								return true;
							}
						}
					}		
				}
			} // closes off water's turn
			
		}

		return false;
	}

	public static void main(String [] args){
		
		Board b = new Board(false);
		b.turn = 1;

		while(b.winner()==null){
			int curr_turn = b.turn;
			b.drawBoardFull(8);
			
			if(StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int c = (int)x;
                int d = (int)y;

                if(b.canSelect(c,d)){
                	b.select(c,d);
                }
            }
        	
            if(StdDrawPlus.isSpacePressed()){
            	System.out.println("Changed turns");
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }

            StdDrawPlus.show(100);

		}
	
	}

}


