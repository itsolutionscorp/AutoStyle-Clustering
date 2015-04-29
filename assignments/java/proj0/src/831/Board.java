	public class Board{
		private Piece[][] pieces = new Piece[8][8];
		private boolean fireTurn;
		private boolean hasMoved;
		private boolean canCapture;

		private Piece selected;
		private int N =8;
		
		/*
		Constructor for Board
		If shouldBeEmpty is true, it initializes an empty board.

		Otherwise a board with default configuration.
		*/
		
		public Board(boolean shouldBeEmpty){ 

			hasMoved = false;
			//hasSelected = false;
			fireTurn = true;
			selected = null;
			canCapture = false;
			if(shouldBeEmpty == true){
				for(int i =0; i<N; i++){
					for (int j = 0; j<N; j++){
						pieces[i][j] = null;
					}
				}
			}
		
			else{
				for (int i = 0; i < N; i++) {
            		for (int j = 0; j < N; j++) {

		                if (((j<=3)||(j>=5))){
		                	if((j==0) && (i%2 == 0)){
		                		pieces[i][j] = new Piece(true, this, i, j, "pawn");
		                	}

		                	else if ((j == 1) && (i%2 !=0)){
		                		pieces[i][j] = new Piece(true, this, i, j, "shield");
		                	}

		                	else if ((j==2) && (i%2 == 0)){
		                		pieces[i][j] = new Piece(true, this, i, j, "bomb");
		                	}

		                	else if((j==5) && (i%2 !=0)) {
		                		pieces[i][j] = new Piece(false, this, i, j, "bomb");
		                	}

		                	else if((j==6) && (i%2 == 0)){
		                		pieces[i][j] = new Piece(false, this, i, j, "shield");
		                	}

		                	else if ((j==7) && (i%2 !=0)){
		                		pieces[i][j] = new Piece(false, this, i, j, "pawn");
		                	}

		                }

        			}
       			}
			}
		
		}

		
		/*Private method that returns the piece type*/

		private String pieceType(Piece p){
			if(p.isShield())
				return "shield";
			

			else if(p.isBomb())
				return "bomb";
			

			else
				return "pawn";
			
		}

		/*Checks to see if x is inside the board*/
		private boolean insideField(int x){
			return ((x>=0) && (x<=N-1));
		}


		/*Private method that returns the x value of Piece p
		Returns -1 if p cannot be found*/
		private int getX(Piece p){
			if(p == null)
				return -1;
			
			for(int i = 0; i< N; i++){
				for (int j = 0; j<N; j++){
					if (pieces[i][j] == p)
						return i;	
				}
			}
			return -1;
		}

		/*Private method that returns the y value of Piece p
		Returns -1 if p cannot be found*/
		private int getY(Piece p){

			if(p == null)
				return -1;

			for (int i = 0; i <N; i++){
				for (int j = 0; j< N; j++){
					if (pieces[i][j] == p)
						return j;		
				}
			}
			return -1;
		}	

		/*Gets piece at position (x,y) or returns null.
		Also returns null if x and y are out of bounds
		*/
		public Piece pieceAt(int x, int y){
			if ((x>7) || (x<0) || (y <0) || (y>7))
				return null;
			
			return pieces[x][y];
		}

		/*
		Returns true if square at (x,y) can be selected.
		Square with piece can be selected if:
			1) It is corresponding player's turn
			2) Player has not selected piece yet
			3) Player has selected a piece but has not moved it

		Empty square can be selected if:
			1)Player has selected a piece which hasn't moved yet and is selecting  an empty 
			spot that is a valid move
			2)During this turn, player has selected a piece, captured, and selected another valid capture destination.
		*/

		public boolean canSelect(int x, int y){
			if((x<0) || (y<0) || (x>7) || (y>7))
				return false;
			
			Piece p = pieceAt(x, y);




			//If it is an empty space...
			if (p == null){
				if(selected == null) //..and you have not selected any piece previously..
					return false;

				//If you ahve already moved and you are trying to move by anyhting other than 2:
				if ((hasMoved == true) && ((Math.abs(getX(selected)-x) != 2) || (Math.abs(getY(selected) - y) !=2))) { 
					return false;
				}

				if (hasMoved == true && selected.hasCaptured() == false)
					return false;

				/*If you are trying to click on a space that is 2 spaces away from the selected piece*/
				else if((Math.abs(getX(selected) - x) == 2) && (Math.abs(getY(selected) -y) == 2)) 
					return canCapture(getX(selected), getY(selected), x, y, selected.isKing(), selected.isFire());

				/*If you are allowed to multi-capture....*/
				else if(canCapture == true){  
					/*if ((x == getX(selected)) && (y == getY(selected))) //You are allowed to just click on the same position
						return true;*/
					if ((Math.abs(getX(selected) - x) == 2) && (Math.abs(getY(selected) -y) == 2))
						return canCapture(getX(selected), getY(selected), x, y, selected.isKing(), selected.isFire());
					else
						return false;
				}

				return canMove(getX(selected), getY(selected), x, y, selected.isKing(), selected.isFire());
			}

			//If the space has a piece on it..
			else{
				if((p.isFire() != fireTurn) ||(hasMoved == true))
					return false;
				return true;
			} 
		}

		/*	Private method that checks to see if a given capture is legal*/
		private  boolean canCapture(int origX, int origY, int x, int y, boolean king, boolean fire){
			int checkX = (origX + x)/2;
			int checkY = (origY + y)/2;

			if(checkX < 0 || checkY <0 || checkX >7 || checkY >7)
				return false;

			if(pieces[checkX][checkY] == null)
				return false;

			if (pieces[checkX][checkY].isFire()== fire)  //Checks to see if the piece to be capture is opponent
				return false;
			
			return canMove(checkX, checkY, x, y, king, fire); 
		}

		
		/* Private method that checks to see if a given move is legal*/
		private  boolean canMove(int origX, int origY, int x, int y, boolean king, boolean fire){
			if((x>7) || (y>7) || (y<0) || (x<0))
				return false;
			if(pieces[x][y] != null)
				return false;
			
			if(king){
				if(((Math.abs(origX -x)) == 1) && (Math.abs(origY - y) == 1)) 
					return true;
				return false;
			}

			else{
				if(Math.abs(origX-x) !=1)
					return false;
				
				if(fire){
					if(y - origY == 1)
						return true;
					return false;
				}

				else{
					if(y - origY == -1)
						return true;
					return false;
				}
			}
		}

		/*
		Selects square at (x, y). Assumes canSelect returns true. 

		If you select a square with a piece, you are setting that piece for movement.
		If you select an empty square, you should move most receently selected piece to square.
		*/
		public void select(int x, int y){
			if(pieceAt(x,y) == null){    //Empty Square 
				selected.move(x,y);
				hasMoved = true;
			} 

			else
				selected = pieceAt(x, y);  //Select the Piece			
		}


		/*
		Places p at (x, y). If (x,y) out of bounds or if p is null, do nothing. 
		If p already exists in current Board, first remove its initial position. 
		If another piece exists at (x,y), p will replace that piece.
		*/
		public void place(Piece p, int x, int y){
			if ((p == null) || (x<0) || (x>7) || (y<0) || (y>7))
				return;
			
			int origX = getX(p);
			int origY = getY(p);

			if((origX != -1) && (origY!=-1)) 
				pieces[origX][origY] = null; //make the orignal piece location null
			
			pieces[x][y] = p;  //move p to new location

			if (p.hasCaptured() && p.isBomb() == false){  //Handles double capturing
				if (canCapture(x, y, x+2, y+2, p.isKing(), p.isFire()) || canCapture(x, y, x+2, y-2, p.isKing(), p.isFire()) 
					|| canCapture(x, y, x-2, y+2, p.isKing(), p.isFire()) || canCapture(x, y, x-2, y-2, p.isKing(), p.isFire()) )
					canCapture = true;
				else{
					canCapture = false;
				}
			}

			if(p.isBomb() && p.hasCaptured()){  //If the piece is a bomb and it has just captured
				pieces[x][y] = null;  //Makes the bomb disappear(explode)

			if(insideField(x+1) && insideField(y+1) && pieces[x+1][y+1] != null && pieces[x+1][y+1].isShield() == false)  //Destroys everything around
				pieces[x+1][y+1] = null;

			if (insideField(x+1) && insideField(y-1) && pieces[x+1][y-1] != null && pieces[x+1][y-1].isShield() == false)
				pieces[x+1][y-1] = null;

			if (insideField(x-1) && insideField(y+1) && pieces[x-1][y+1] != null && pieces[x-1][y+1].isShield() == false)
				pieces[x-1][y+1] = null;

			if (insideField(x-1) && insideField(y-1) && pieces[x-1][y-1] != null && pieces[x-1][y-1].isShield() == false)
				pieces[x-1][y-1] = null;

			}

		}


		/* Executes a remove. Returns the piece that was removed. 
		If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
		If there is no piece at (x, y), returns null and prints an appropriate message.*/
		public Piece remove(int x, int y){
			if((x<0) || (y<0) || (x>7) || (y>7)){
				System.out.println("Out of bounds");
				return null;
			}
			Piece temp = pieces[x][y];
			pieces[x][y] = null;	
			return temp;
		}	

		public boolean canEndTurn(){
			return hasMoved;
		}

		/*
		Called at ending of turn. Handles switching of players.
		*/
		public void endTurn(){
			fireTurn = !fireTurn;
			for (int i = 0; i < N; i++){
				for (int j = 0; j<N; j++){
					if(pieces[i][j] != null)
						pieces[i][j].doneCapturing();
				}
			}
			hasMoved = false;
			selected = null;
			canCapture = false;
		}

		

		/*
		Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
		or null if the game is not yet over. Assume there is no stalemate situation in which the current player 
		has pieces but cannot legally move any of them 
		(In this event, just leave it at null). 
		Determine the winner solely by the number of pieces belonging to each team.
		*/
		public String winner(){
			boolean hasWater = false;
			boolean hasFire = false;
			boolean allNull = true;

			for (int i = 0; i < N; i++){
				for (int j = 0; j<N; j++){
					if(pieces[i][j]!=null){
						allNull = false;
						if(pieces[i][j].isFire())
							hasFire = true;
						else
							hasWater = true;
					}
				}

			}
			if(allNull == true)
				return "No one";

			if (hasWater == true && hasFire == false)
				return "Water";
			else if (hasFire == true && hasWater == false)
				return "Fire";
			else if (hasFire == false && hasWater == false)
				return "No one";

			return null;
		}





		/* Methods for drawing */



		/*Private methd that draws the board and colors the given color white*/
		private void drawBoard(int x, int y){

			for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);                
             }
        	}

        	if((x>=0) && (x<8) && (y>=0) && (y<8)){
        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        		StdDrawPlus.filledSquare(x+.5, y+.5, .5);
			}
		}

		/*Private method that draws all the pieces*/
		private void drawPieces(){
			for(int i =0; i < N; i++){
				for (int j = 0; j < N; j++){
					
					drawPiece(pieces[i][j], i, j);

				}
			
			}
		}


		/*Private method that draws the pieces*/
		private void drawPiece(Piece p, int x, int y){

			if(p == null)
				return;
			double rescale = 1.0;
			if (p.isFire()){
				if(p.isKing()){
					StdDrawPlus.picture(x +0.5, y+0.5, "img/" + pieceType(p)+"-fire-crowned.png",rescale, rescale);
				}

				else{
					StdDrawPlus.picture(x+0.5, y+0.5, "img/" + pieceType(p) + "-fire.png",rescale, rescale);
				}
			}

			else{

				if(p.isKing()){
					StdDrawPlus.picture(x+0.5, y+0.5, "img/" + pieceType(p) + "-water-crowned.png", rescale, rescale);
				}

				else{
					StdDrawPlus.picture(x+0.5, y+0.5, "img/" + pieceType(p) +"-water.png",rescale,rescale);

				}

			}
		}

		/*
		Updates the board
		*/
		private void update(int x, int y){

			drawBoard(x, y);
			for(int i = 0; i<8; i++){
 				for(int j = 0; j<8; j++){
 					if(pieces[i][j] != null){

 						drawPiece(pieces[i][j], i, j);
 					}
 				}
 			}

		}


		public static void main(String[] args) {	
		
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
     	Board b= new Board(false);
     	b.drawBoard(-1, -1);
     	b.drawPieces();
     	

     		while(b.winner() == null){

     			//MouseListener
     			int x = -1;
     			int y = -1;

     			if (StdDrawPlus.mousePressed()) {
                double xClick = StdDrawPlus.mouseX();
                double yClick = StdDrawPlus.mouseY();
                x = (int)(xClick);
                y = (int)(yClick);
                }

                if(b.canSelect(x, y)){
                	b.select(x, y);	
                	b.update(x, y);
                }

                if(StdDrawPlus.isSpacePressed()){
                	if (b.canEndTurn()){
                		b.endTurn();
                		b.update(-1,-1);
                	}
                }

     		}

     	System.out.println( b.winner());
		}
}	