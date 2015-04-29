public class Board{

	private static Piece[][] pieces = new Piece[8][8];
	private static boolean[][] filled = new boolean[8][8];
	private int turn = 0; //0 if fire's turn, 1 if water's turn 
	private boolean selected = false;
	private boolean empty;
	private int x_selected ;	//x coordinate of selected piece
	private int y_selected ;	//y coordinate of selected piece
	private int x_captured = -1;	//x coordinate of captured piece
	private int y_captured = -1;	//y coordinate of captured piece
	private boolean  moved_without_capture = false;
	private boolean moved = false;

	//created private static method to draw board
	private void drawBoard(){


		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				//draw the board
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                //check to make sure the spot actually has a piece there
                if (pieceAt(i, j) != null){
                	//place the necessary pieces for fire
                	if (pieceAt(i, j).side() == 0){
                		if (pieceAt(i, j).isBomb()){

                			if (pieceAt(i, j).isKing() == true){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}

                		}
                		else if (pieceAt(i, j).isShield()){

                			if (pieceAt(i, j).isKing() == true){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                			
                		}
                		else{

                			if (pieceAt(i, j).isKing() == true){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                	
                		}
                	}
                	//places the necessary places for water
                	else{
                		if (pieceAt(i, j).isBomb()){

                			if (pieceAt(i, j).isKing() == true){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                			
                		}
                		else if (pieceAt(i, j).isShield()){

                			if (pieceAt(i, j).isKing() == true){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                			
                		}
                		else{

                			if (pieceAt(i, j).isKing() == true){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                			else{
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                			
                		}
                	}

                }


			}
		}
	}

	public static void main (String args[]){

		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		Board game_board = new Board(false);

		while (true){
			game_board.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				//System.out.println("mouse pressed");
                double x_temp = StdDrawPlus.mouseX();
                double y_temp = StdDrawPlus.mouseY();

                int x = (int) x_temp;
                int y = (int) y_temp;

                boolean result = game_board.canSelect(x, y);
                
                //System.out.println("result is after calling game_board.canSelect(x,y): " + result);
                if (result == true){
                	game_board.select(x, y);
                }
            }  
            if (StdDrawPlus.isSpacePressed()){
            	//System.out.println("isSpacePressed");
            	if (game_board.canEndTurn() == true){
            		game_board.endTurn();
            	}
            	if (game_board.winner() != null){
            		String result = game_board.winner();
            		System.out.println(result);
            		System.exit(0);
            	}
            }
			StdDrawPlus.show(100);
		}


	}

	private void default_Settings(){


		// pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
		// pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
		// pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
		// pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
		// pieces[1][1] = new Piece(true, this, 1, 1, "shield");
		// pieces[3][1] = new Piece(true, this, 3, 1, "shield");
		// pieces[5][1] = new Piece(true, this, 5, 1, "shield");
		// pieces[7][1] = new Piece(true, this, 7, 1, "shield");
		// pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
		// pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
		// pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
		// pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
		// pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
		// pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
		// pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
		// pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
		// pieces[0][6] = new Piece(false, this, 0, 6, "shield");
		// pieces[2][6] = new Piece(false, this, 2, 6, "shield");
		// pieces[4][6] = new Piece(false, this, 4, 6, "shield");
		// pieces[6][6] = new Piece(false, this, 6, 6, "shield");
		// pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
		// pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
		// pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
		// pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

		// StdDrawPlus.picture(0.5, 0.5, "img/pawn-fire.png", 1, 1);
		// StdDrawPlus.picture(2.5, 0.5, "img/pawn-fire.png", 1, 1);
		// StdDrawPlus.picture(4.5, 0.5, "img/pawn-fire.png", 1, 1);
		// StdDrawPlus.picture(6.5, 0.5, "img/pawn-fire.png", 1, 1);
		// StdDrawPlus.picture(1.5, 1.5, "img/shield-fire.png", 1, 1);
		// StdDrawPlus.picture(3.5, 1.5, "img/shield-fire.png", 1, 1);
		// StdDrawPlus.picture(5.5, 1.5, "img/shield-fire.png", 1, 1);
		// StdDrawPlus.picture(7.5, 1.5, "img/shield-fire.png", 1, 1);
		// StdDrawPlus.picture(0.5, 2.5, "img/bomb-fire.png", 1, 1);
		// StdDrawPlus.picture(2.5, 2.5, "img/bomb-fire.png", 1, 1);
		// StdDrawPlus.picture(4.5, 2.5, "img/bomb-fire.png", 1, 1);
		// StdDrawPlus.picture(6.5, 2.5, "img/bomb-fire.png", 1, 1);
		// StdDrawPlus.picture(1.5, 5.5, "img/bomb-water.png", 1, 1);
		// StdDrawPlus.picture(3.5, 5.5, "img/bomb-water.png", 1, 1);
		// StdDrawPlus.picture(5.5, 5.5, "img/bomb-water.png", 1, 1);
		// StdDrawPlus.picture(7.5, 5.5, "img/bomb-water.png", 1, 1);
		// StdDrawPlus.picture(0.5, 6.5, "img/shield-water.png", 1, 1);
		// StdDrawPlus.picture(2.5, 6.5, "img/shield-water.png", 1, 1);
		// StdDrawPlus.picture(4.5, 6.5, "img/shield-water.png", 1, 1);
		// StdDrawPlus.picture(6.5, 6.5, "img/shield-water.png", 1, 1);
		// StdDrawPlus.picture(1.5, 7.5, "img/pawn-water.png", 1, 1);
		// StdDrawPlus.picture(3.5, 7.5, "img/pawn-water.png", 1, 1);
		// StdDrawPlus.picture(5.5, 7.5, "img/pawn-water.png", 1, 1);
		// StdDrawPlus.picture(7.5, 7.5, "img/pawn-water.png", 1, 1);


		//draw board and initialize pieces if empty is false
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                place(null, i, j);

                if (empty == false){
                	if ((j == 0) & (i % 2 == 0)) {
                    	Piece temp = new Piece(true, this, i, j, "pawn");
                    	place(temp, i, j);
                	}
                	if ((j == 1) & (i % 2 == 1)){
                		Piece temp = new Piece(true, this, i, j, "shield");
                		place(temp, i, j);
                	}
                	if ((j == 2) & (i % 2 == 0)){
                		Piece temp = new Piece(true, this, i, j, "bomb");
                		place(temp, i, j);
                	}
                	if ((j == 5) & (i % 2 == 1)) {
                    	Piece temp = new Piece(false, this, i, j, "bomb");
                    	place(temp, i, j);
                	}
                	if ((j == 6) & (i % 2 == 0)){
                		Piece temp = new Piece(false, this, i, j, "shield");
                		place(temp, i, j);
                	}
                	if ((j == 7) & (i % 2 == 1)){
                		Piece temp = new Piece(false, this, i, j, "pawn");
                		place(temp, i, j);
                	}

        		}

            }
        }

	}

	public Board(boolean shouldBeEmpty){

		if (shouldBeEmpty == false){
			default_Settings();
		}
		else{
			for (int i = 0; i < 8; i++){
				for (int j = 0 ; j < 8; j ++){
					pieces[i][j] = null;
				}
			}
		}

	}


	public Piece pieceAt(int x, int y){
		
		if (x > 7 || x < 0 || y > 7 || y < 0){
			return null;
		}
		else if (pieces[x][y] != null){
			return pieces[x][y];
		}
		else{
			return null;
		}
	}

	//assume the original piece has already been selected
	private boolean validMove(int xi, int yi, int xf, int yf){

		int num_to_add;
		int num_to_go_through;

		if (pieceAt(xi, yi).isKing() == true){
			num_to_add = 1;
			num_to_go_through = 0;
		}
		else if (turn == 0){
			num_to_add = 1;
			num_to_go_through = 1;
		}
		else{
			num_to_add = -1;
			num_to_go_through = 1; 
		}

		while (num_to_go_through < 2){
			//test when you want to move one step diagonally to the left or the right
			if (  (    ((xf == xi + 1) & (yf == yi + num_to_add)) | ((xf == xi - 1) & (yf == yi + num_to_add))  && (pieceAt(xi, yi).hasCaptured() == false)  ) ){
				//REFINED: moved_without_capture = true;
				return true;
			}
			//test when you want to move two steps diagonally to the right to capture
			else if ((xf == xi + 2) & (yf == yi + 2 * num_to_add)) {
				if ( (pieceAt(xi + 1, yi + num_to_add) != null) && (pieceAt(xi + 1, yi + num_to_add).side() != turn) ){
					x_captured = xi + 1;
					y_captured = yi + num_to_add;
					return true;
				}
				else{
					return false;
				}
			}
			//test when you want to move two steps diagonally to the left to capture
			else if ((xf == xi - 2) & (yf == yi + 2 * num_to_add)){
				if ( (pieceAt(xi - 1, yi + num_to_add) != null) &&  (pieceAt(xi - 1, yi + num_to_add).side() != turn) ){
					x_captured = xi - 1;
					y_captured = yi + num_to_add;
					return true;
				}
				else{
					return false;
				}

			}
			//edit: got rid of the else here and put it outside while loop
			// else{
			// 	return false;
			// }
			//correct for if the piece is crowned
			num_to_go_through = num_to_go_through + 1;
			if (num_to_go_through == 1){
				num_to_add = -1;
			}
		}

		//to return false if it's not even close for both crowned and non-crowned situations
		return false;

	}

	public boolean canSelect(int x, int y){

		//if the piece has already made a valid diagonal move then it cannot move again and the next player's turn must start
		if (moved_without_capture == true){
			return false;
		}
		//0.55REFINE
		else if ((pieceAt(x,y) == null) && (selected == true) && (validMove(x_selected, y_selected, x, y) == true) ){
			return true;
		}
		else if ( (pieceAt(x, y) != null) && (pieceAt(x, y).side() == turn ) ) {
			//BOMB CHANGE: CANNOT HAVE PIECEAT() BEING CALLED ON AN X_SELECTED AND Y_SELECTED THAT POINT TO NOWHERE. TO SOLVE THE
			//PROBLEM OF X_SELECTED AND Y_SELECTED, INSTEAD OF DOING SOMETHING UNIQUE I MADE MOVED_WITHOUT_CAPTURE EQUAL TO TRUE, AS 
			//IF THE PIECE HAD MOVED WITHOUT CAPTURE. HOWEVER, THE X_SELECTED AND Y_SELECTED ARE STILL POINTING TO AN EMPTY SQUARE, THE
			//SPOT WHERE THE BOMB DETONATED. SO, WE CANNOT HAVE PIECEAT().HASCAPTURED() RUNNING ON NULL-THEREBY IT GAVE ME A NULL POINTER EXCEPTION
			//TO SOLVE THIS PROBLEM, I FIRST CHECKED TO SEE IF X_SELECTED AND Y_SELECTED WERE POINTING TO NOTHING; IF SO, THAT MEANS THAT A BOMB
			//HAD BEEN THERE BEFORE AND THE NEXT PLAYER IS SELECTING A PIECE TO BE MOVED, SO IT SHOULD GO DOWN TO THE ELSE STATEMENT
			if (pieceAt(x, y).hasCaptured() == true){
				return false;
			}
			if ( (pieceAt(x_selected, y_selected) != null) && (pieceAt(x_selected, y_selected).hasCaptured() == true) && (x != x_selected) && (y != y_selected) )  {
				return false;
			}
			else{
				//equivalent to having selected a piece to be moved
				return true;
			}
		}
		else{
			return false;
		}
	}

	//remember to unselect x and y after the turn later on somewhere
	//checks whether you are capturing because if so x_captured and y_captured would not be -1
	public void select(int x, int y){

		//System.out.println("x_selected from before: " + x_selected);
		//System.out.println("y_selected from before: " + y_selected);

		//BOMB CHANGE: SWITCHED THE ORDER OF THESE TWO STATEMENTS

		if ( (selected == true) && (pieceAt(x, y) == null) ){

			moved = true;
			//BOMB CHANGE: WE ARE TREATING IT AS IF MOVED_WITHOUT_CAPTURE IS TRUE AFTER BOMB EXPLODES SINCE THEN THE PLAYER MUST END HER TURN 

			if ( (x_selected - x == -1 && y_selected - y == -1) || (x_selected - x == -1 && y_selected - y == 1) || 
				(x_selected - x == 1 && y_selected - y == -1) || (x_selected - x == 1 && y_selected - y == 1)  ){
				moved_without_capture = true;
			}

			if ((pieceAt(x_selected, y_selected) != null) && (pieceAt(x_selected, y_selected).isBomb())){
				moved_without_capture = true;
			}

			if (pieceAt(x_selected, y_selected) != null){
				pieceAt(x_selected, y_selected).move(x ,y);
			}
			
		}

		selected = true; //REFINE: make select = true if you are capturing a piece
		
		x_selected = x;
		y_selected = y;
		//System.out.println("x_selected: " + x_selected);
		//System.out.println("y_selected: " + y_selected);
		
	}


	public void place(Piece p, int x, int y){

		if (x <= 7 && x >= 0 && y <= 7 && y >= 0){
			pieces[x][y] = p;
			//filled[x][y] = true;
		}

	}

	public Piece remove(int x, int y){

		if (x > 7 || x < 0 || y > 7 || y < 0){
			System.out.println("The position is out of bounds.");
			//System.out.println("x is: " + x);
			//System.out.println("y is: " + y);
			return null;
		}
		else{
			
			if (pieceAt(x, y) != null){
				Piece temp = pieceAt(x, y);
				place(null, x, y);
				//filled[x][y] = false; 
				//EDITED DUE TO THE AUTOGRADER BS
				return temp; 
				
			}
			else{
				System.out.println("There is no piece at this position");
				return null;
			}

		}

	}

	public boolean canEndTurn(){
		//BOMB CHANGE: THERE IS NO MORE PIECE AT X_SELECTED AND Y_SELECTED, SO WE NEED TO SPLIT UP THE TWO CONDITIONS, SINCE THE SECOND CONDITION CAN'T EXECUTE

		if (moved == true){
			return true;
		}
		//REFINED
		// else if (pieceAt(x_selected, y_selected).hasCaptured() == true){
		// 	System.out.println("pieceAt(x_selected, y_selected).hasCaptured() result is: " + pieceAt(x_selected, y_selected).hasCaptured());
		// 	return true;
		// }
		else{
			return false;
		}
	}

	public void endTurn(){
		if (turn == 0){
			turn = 1;
		}
		else{
			turn = 0;
		}
		selected = false;
		//BOMB CHANGE
		//the idea is that after a move, x_selected and y_selected should point to where the piece moved. If the piece was a bomb, then
		//x_selected and y_selected should be pointing to nothing
		if (pieceAt(x_selected, y_selected) != null){
			if (pieceAt(x_selected, y_selected).hasCaptured()){
				pieceAt(x_selected, y_selected).doneCapturing();
			}
		}

		moved_without_capture = false;
		moved = false;

	}

	public String winner(){
		int num_fire = 0;
		int num_water = 0;

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if ( (pieceAt(i, j) != null) && (pieceAt(i, j).side() == 0) ){
					num_fire = num_fire + 1;
				}
				if ( (pieceAt(i, j) != null) && (pieceAt(i, j).side() == 1) ){
					num_water = num_water + 1;
				}

			}
		}

		if (num_fire == 0){
			if (num_water == 0){
				return "No one";
			}
			return "Water";
		}
		else{
			if (num_water == 0){
				return "Fire";
			}
			return null;
		}
	}
}