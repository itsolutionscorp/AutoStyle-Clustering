public class Board {
	
	private Piece[][] piece_array = new Piece[8][8];
	private boolean selected = false;
	private int a;
	private int c;
	private Piece selected_piece = null;
	private String turn = "Fire";
	private boolean moved = false;

	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == true){
			CreateBoard();
			}
		else{
			CreateBoard();
			DrawPieces();
		}


		}

	private void CreateBoard(){
		StdDrawPlus.setPenColor(StdDrawPlus.RED);
		int x = 0;
		int y = 0;
		for (int i = 0; i < 8; i++){
			for (int h = 0; h < 8; h++){
				if (((i + h) %2) == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
 				StdDrawPlus.filledSquare(x+.5,y+.5,.5);
				y = y + 1;

			}

			y = 0;
			x = x + 1;
				}
	}
		
	


	

	private void CreatePieces(){
		int i = 0;
		int j = 0;
		if (selected_piece != null){
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(a+0.5,c+0.5,0.5);
		}
		while (i <= 7){
			while (j <= 7){

			Piece the_piece = piece_array[i][j];
			if (the_piece == null){}
			
			else if (the_piece.isFire() && the_piece.isBomb() && the_piece.isKing()){StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire-crowned.png",1,1);}
			else if (the_piece.isFire() && the_piece.isShield() && the_piece.isKing()){StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire-crowned.png",1,1);}
			else if (the_piece.isFire() && the_piece.isKing()){StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire-crowned.png",1,1);}
			
			else if (the_piece.isBomb() && the_piece.isKing()){StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water-crowned.png",1,1);}
			else if (the_piece.isShield() && the_piece.isKing()){StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water-crowned.png",1,1);}
			else if (the_piece.isKing()){StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water-crowned.png",1,1);}

			else if (the_piece.isBomb() && the_piece.isFire()){StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire.png",1,1);}
			/* Checks if piece is a fire bomb */
			else if (the_piece.isShield() && the_piece.isFire()){StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire.png",1,1);}
			/* Checks if piece is a fire shield */

			else if (the_piece.isFire()){StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire.png",1,1);}

			/* Checks if piece is a fire pawn */
			else if (the_piece.isBomb()){StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water.png",1,1);}	
			/* Checks if a piece is a water bomb*/
			else if (the_piece.isShield()){StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water.png",1,1);}
			/* Checks if a piece is a water shield*/
			else if (the_piece.isFire() != true && the_piece != null){StdDrawPlus.picture(i+0.5,j+0.5,
				"img/pawn-water.png",1,1);}
			/* Checks if a piece is a water pawn*/
				the_piece = null;
				j = j + 1;
			}
			i = i + 1;
			j = 0;
		}
	}
		

private void DrawPieces(){
		
		for (int j = 0; j <= 3;j++){
		Piece pc1 = new Piece(true,this,2*j,0,"pawn");
		piece_array[2*j][0] = pc1;
		Piece pc2 = new Piece(true,this,2*j,2,"bomb");
		piece_array[2*j][2] = pc2;

		Piece pc3 = new Piece(true,this,2*j+1,1,"shield");
		piece_array[2*j+1][1] = pc3;

		Piece pc4 = new Piece(false,this,2*j+1,5,"bomb");
		piece_array[2*j+1][5] = pc4;

		Piece pc5 = new Piece (false,this,2*j,6,"shield");
		piece_array[2*j][6] = pc5;

		Piece pc6 = new Piece(false,this,2*j + 1,7,"pawn");
		piece_array[2*j+1][7] = pc6;

	}
			
			/* Checks if a piece is a water pawn*/
			
	}
	public Piece pieceAt(int x,int y){
		if (OutofBounds(x,y)) {return null;}
		else {return piece_array[x][y];}
	
	}

	public void place(Piece p, int x, int y){

		if ((x > 7 || y > 7) || (p == null)){}
		else if (piece_array[x][y] == null){
		piece_array[x][y] = p;
		}

	}

	public boolean canSelect(int x, int y){
		if ((selected == false) && ((OutofBounds(x,y)) || (pieceAt(x,y) == null))) {return false;}
		/* handles out of bounds situations */
		/* and handles if a player has moved a piece already */

		if (moved == false && selected_piece != null && pieceAt(x,y) != null){
			if (pieceAt(x,y).side() == 0 && turn == "Fire"){
				return true;}
			else if (pieceAt(x,y).side() == 1 && turn == "Water"){
				return true;
			}
		}

		if (selected_piece != null){
			if (selected_piece.isKing()){
				
				if (selected_piece.hasCaptured() && (hasAvailableCaptures(a,c,0) ||
				hasAvailableCaptures(a,c,1))) {
					if (!validMove(a,c,x,y)){
						return false;
					}

					if (selected_piece.side() == 0 && turn == "Fire" && pieceAt(x,y) == null){
						return true;

					}

					if (selected_piece.side() == 1 && turn == "Water" && pieceAt(x,y) == null){
						return true;
					}
					
			}
	}
}
		if (selected_piece != null && selected_piece.hasCaptured() && hasAvailableCaptures(a,c,0) && turn == "Fire"){
			if (pieceAt(x,y) == null && validMove(a,c,x,y)){
				return true;}
		
		}
		if (selected_piece != null && selected_piece.hasCaptured() && hasAvailableCaptures(a,c,1) && turn == "Water"){
			if (pieceAt(x,y) == null && validMove(a,c,x,y)) {
				return true;}
		
		}

		else {
		if (turn == "Fire"){
			if (pieceAt(x,y) != null){
				if (pieceAt(x,y).side() == 1)
					{return false;}}

			if (!validMove(a,c,x,y) && selected_piece != null){return false;}
			if (moved == false)
				{return true;}
			
		}
		if (turn == "Water"){
			if (pieceAt(x,y) != null){
				if (pieceAt(x,y).side() == 0)
					{return false;}}
			if (!validMove(a,c,x,y) && selected_piece != null){return false;}

			if (moved == false)
				{return true;}
			
		}}
		
		return false;
	}

	public void select(int x, int y){
		if (selected_piece == null){
			/* Make sure a piece has not been selected yet*/
			selected_piece = pieceAt(x,y);
			/* Selects the piece */
			a = x;
			c = y;
			
			selected = true;}

		else {
			if (validMove(a,c,x,y)){
			
			selected_piece.move(x,y);
			/* Moves the piece to x,y*/
			moved = true;

			a = x;
			c = y;
			if (selected_piece.hasCaptured() && selected_piece.isBomb()){
				selected_piece = null;
			}
					
			}

			else{
				if (pieceAt(x,y) != null && selected_piece.hasCaptured() == false){
					selected_piece = pieceAt(x,y);
					a = x;
					c = y;}
				}
			}
			
	}
	

		
		




	private boolean OutofBounds(int x, int y){
		if ((x > 7) || (y > 7) || (x < 0)|| (y < 0)){return true;}
		else {return false;}
	}
	public Piece remove(int x, int y){
		if (pieceAt(x,y) == null){System.out.println("No piece on this square");
								  return null;}
		if (OutofBounds(x,y)){System.out.println("Out of bounds!");
							return null;}

		else {
			Piece pie = pieceAt(x,y);
			piece_array[x][y] = null;
			
			return pie;}
	}
								

	private boolean validMove(int xi, int yi, int xf, int yf){

		if (OutofBounds(xf,yf)){return false;}
		if (piece_array[xf][yf] != null){return false;}
		if (pieceAt(xi,yi) != null){
		if (piece_array[xi][yi].side() == 0){
			if (selected_piece.hasCaptured() == false){
			if (((xi + 1 == xf) || (xi - 1 == xf)) && (yi + 1 == yf)){
				return true;}}

			if (piece_array[xi][yi].isKing()){
				/* Valid move for Kings */
				if (moved == true && pieceAt(xi,yi).hasCaptured() == false){return false;}
				if ((pieceAt(xi,yi).hasCaptured() == false) || (pieceAt(xi,yi).hasCaptured() == true && moved == false)){
				if (((xi + 1 == xf) || (xi - 1 == xf)) && (yi - 1 == yf)){
					return true;
				}}
				if (canCapture(xi,yi,xf,yf,0,true)){
					return true;
				}}
			
		
			if (canCapture(xi,yi,xf,yf,0,false)) {
				return true;
			}	

			}

		else if (pieceAt(xi,yi).side() == 1)
			{
			if (selected_piece.hasCaptured() == false){
			if (((xi + 1 == xf) || (xi - 1 == xf)) && (yi - 1 == yf)){
				return true;
			}}

			if (piece_array[xi][yi].isKing()){
				/* Valid move for Kings */
				if ((pieceAt(xi,yi).hasCaptured() == false) || (pieceAt(xi,yi).hasCaptured() == true && moved == false)){

				if (((xi + 1 == xf) || (xi - 1 == xf)) && (yi + 1 == yf)){
				return true;
			}
				if (canCapture(xi,yi,xf,yf,1,true)) {

				return true;
				}}
		}
			if (canCapture(xi,yi,xf,yf,1,false)){
				return true;
			}
		}
	}
	return false;}

	private boolean canCapture(int xi, int yi, int xf, int yf, int side,boolean king){

		if (side == 0){
			if (!OutofBounds(xi+1,yi+1)){
			if (((xi + 2 == xf) && (yi + 2 == yf)) && (piece_array[xi+1][yi+1] != null)){
				if (pieceAt(xi+1,yi+1).side() == 1){
				return true;
			}
			}}

			if (!OutofBounds(xi-1,yi+1)){

			if (((xi - 2 == xf) && (yi + 2 == yf)) && (piece_array[xi-1][yi+1] != null)){
				if (pieceAt(xi-1,yi+1).side()  == 1){
				return true;
			}			
				}}

			if (king == true){

				if (!OutofBounds(xi+1,yi-1)){

				if (((xi + 2 == xf) && (yi - 2 == yf)) && (piece_array[xi+1][yi-1] != null)){
				if (pieceAt(xi+1,yi-1).side() == 1){
				return true;
			}		
				}}

			if (!OutofBounds(xi-1,yi-1)){

			if (((xi - 2 == xf) && (yi - 2 == yf)) && (piece_array[xi-1][yi-1] != null)){
				if (pieceAt(xi-1,yi-1).side() == 1){
				return true;
			}
				}}

			}}
		else if (side == 1){
			if (!OutofBounds(xi+1,yi-1)){

			if (((xi + 2 == xf) && (yi - 2 == yf)) && (piece_array[xi+1][yi-1] != null)){
				if (pieceAt(xi+1,yi-1).side() == 0){
				return true;
			}		
				}}

			if (!OutofBounds(xi-1,yi-1)){


			if (((xi - 2 == xf) && (yi - 2 == yf)) && (piece_array[xi-1][yi-1] != null)){
				if (pieceAt(xi-1,yi-1).side() == 0){
				return true;
			}		
				}}

			if (king == true){

				if (!OutofBounds(xi+1,yi+1)){

				if (((xi + 2 == xf) && (yi + 2 == yf)) && (piece_array[xi+1][yi+1] != null)){
				if (pieceAt(xi+1,yi+1).side() == 0){
				return true;
			}		
				}}

			if (!OutofBounds(xi-1,yi+1)){

			if (((xi - 2 == xf) && (yi + 2 == yf)) && (piece_array[xi-1][yi+1] != null)){
				if (pieceAt(xi-1,yi+1).side() == 0){
				return true;
			}		
				}}

			}
		}
			
			return false;
	}

	private boolean hasAvailableCaptures(int xi, int yi,int side){

		if (side == 0){
			if (OutofBounds(xi+2,yi+2)){}

			else if ((piece_array[xi+2][yi+2] == null) && (piece_array[xi+1][yi+1] != null)){
				return true;}
			
			
			if (OutofBounds(xi-2,yi+2)){}

			else if ((piece_array[xi-2][yi+2] == null) && (piece_array[xi-1][yi+1] != null)){

				return true;}
			}
				
		else if (side == 1){

			if (OutofBounds(xi+2,yi-2)) {}

			else if ((piece_array[xi+2][yi-2] == null) && (piece_array[xi+1][yi-1] != null)) {
				return true;}
			

			if (OutofBounds(xi-2,yi-2)){}

			else if ((piece_array[xi-2][yi-2] == null) && (piece_array[xi-1][yi-1] != null)){
				return true;}
			}
				
			return false;
		}

	public boolean canEndTurn(){
		if (moved == true){
			return true;}

		else {
			return false;
		}
	}


	public void endTurn(){

		if (turn == "Fire"){
			turn = "Water";
		}

		else {
			turn = "Fire";
		}
		moved = false;
		if (selected_piece != null){
		selected_piece.doneCapturing();}
		selected = false;

		selected_piece = null;
	}
	public String winner(){
		int i = 0;
		int j = 0;
		int fire = 0;
		int water = 0;
		while (i <= 7){
			while (j <= 7){
				if (piece_array[i][j] == null){
				
				}
				else if (piece_array[i][j].side() == 0){
					fire = fire + 1;
				}

				else if (piece_array[i][j].side() == 1){
					water = water + 1;
				}
				else {}
				j++;

			
		} 
			i++;
			j = 0;

	}
	if (fire == 0 && water == 0){return "No one";}
	else if (water == 0){return "Fire";}
	else if (fire == 0){return "Water";}

	return null;}
	private void GUI(){
		
        /* Checks if a piece has been selected */
      	/* tracker variable, tracks the position of the last mouseclicker*/
      	boolean moved = false;
        while (true){
        	CreateBoard();
        	CreatePieces();
	        if (StdDrawPlus.mousePressed()){
	        	if (canSelect((int)(StdDrawPlus.mouseX()),(int)(StdDrawPlus.mouseY()))){
	        	select((int)(StdDrawPlus.mouseX()),(int)(StdDrawPlus.mouseY()));}
	        	else {}}

	        if (StdDrawPlus.isSpacePressed()){
	        	if (canEndTurn()){
	        		endTurn();
	        		System.out.println("Turn has ended");

	        	}
	        	else{}
	        }
        	StdDrawPlus.show(50);}
    }
	
	public static void main(String[] args){
    StdDrawPlus.setXscale(0,8);
    StdDrawPlus.setYscale(0,8);
   	Board b = new Board(false);
   	b.GUI();}
}

     
