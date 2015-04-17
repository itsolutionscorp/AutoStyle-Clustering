
public class Board {
	private static int N = 8;
    private Piece[][] pieces; //keep track of positions of the pieces
    private boolean shouldBeEmpty;
    private int turn; // 0 is fire, 1 is water
 	private int numberOfFire; //count the number of total fire pieces
	private	int numberOfWater; //count the number of total water pieces
	
    private boolean selected; //whether has selected a piece, no matter null or not
    private boolean moved; // immediately turns true when we select a null piece, indicating a move
    private boolean canCapture; // whether the active piece can capture if it moves

    private Piece activePiece; //keep track of the active piece
    private int oldx; //the x of the piece
    private int oldy; //the y of the piece

	public static void main(String[] args) {
		Board b = new Board(false); //draw the initial board
		b.drawBoard(N); 
		int turn = 0; //start the game with fire
		boolean moved = false;
		boolean selected = false;
		

		while (b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)){
               		b.select((int) x, (int) y);
               		 /*if we can select the piece, then we select and it will automatically move/capture/do whatever should do;
               		 */
               	}
            }            

            if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){ 
            		b.endTurn();//if we can end the turn, we end the turn;
            	}
            }

            StdDrawPlus.show(100);
            b.winner(); //check if the game has ended
        } 

        System.out.println("The winner is " + b.winner()); //after the game has ended, announce the winner
	}

	private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (oldx ==i && oldy ==j){
       				/*
                	if (StdDrawPlus.isSpacePressed()){
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                	*/
                	StdDrawPlus.setPenColor(StdDrawPlus.BLUE); //when a piece is selected, the square becomes blue
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);  	
                }
            }
        }

        // The following draws the pieces according to its type and whether it is a king piece;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if(pieces[i][j]!= null){
	            	if (pieces[i][j].isFire()){
	            		if (pieces[i][j].isBomb()){
	            			if (!pieces[i][j].isKing()){
	            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	            			}
	            			else{ // if it is a king piece, we draw it differently
	            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	            			}
	            		}
	            		else if (pieces[i][j].isShield()){
	            			if (!pieces[i][j].isKing()){
	            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	            			}
	            			else{ // if it is a king piece, we draw it differently
	            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	            			}
	            		}
	            		else {
	            			if (!pieces[i][j].isKing()){
	            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	            			}
	            			else{ // if it is a king piece, we draw it differently
	            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	            			}
	            		}
	            	}

	            	else if (!pieces[i][j].isFire()){
	            		if (pieces[i][j].isBomb()){
	            			if (!pieces[i][j].isKing()){
	            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	            			}
	            			else{// if it is a king piece, we draw it differently
	            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	            			}
	            		}
	            		else if (pieces[i][j].isShield()){
	            			if (!pieces[i][j].isKing()){
	            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            			}
	            			else{  // if it is a king piece, we draw it differently
	            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	            			}
	            		}
	            		else {
	            			if (!pieces[i][j].isKing()){
	            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	            			}
	            			else{ // if it is a king piece, we draw it differently
	            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	            			}

	            		}
	            	}     
            	}   
			}
        }        
    }
    
	//This creates the initial board.
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[N][N];
		if (shouldBeEmpty){ //if should be empty, just draw the board and every piece is null;
			turn = 0;
			moved = false;
			selected = false;

			for (int i= 0; i < N; i++){
				for (int j= 0; j < N; j++){
					pieces[i][j] = null;
				}
			}
		} 
		else{ //draw the pieces according to its initial position
			for (int i= 0; i < N; i++){
				if (i % 2 == 0){
					pieces[i][0] = new Piece(true, this, i, 0, "pawn" );
					pieces[i][2] = new Piece(true, this, i, 2, "bomb" );
					pieces[i][6] = new Piece(false, this, i, 6, "shield" );
				}
				else{
					pieces[i][1] = new Piece(true, this, i, 1, "shield" );
					pieces[i][5] = new Piece(false, this, i, 5, "bomb" );
					pieces[i][7] = new Piece(false, this, i, 7, "pawn" );
				}
			}
		}	
	}
	
	
	/* Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
	*/
	public Piece pieceAt(int x, int y){
		if (x > N-1 || x < 0 || y > N-1 || y < 0 ){
			return null;
		}
		else{ //if x and y are within range	
			if (pieces[x][y] == null){
				return null;
			}
			else{
				return pieces[x][y];
			}
			
		}
	}

	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If another piece already exists at (x, y), p will replace that piece. (This method is potentially useful for creating specific test circumstances.)
	*/

	public void place(Piece p, int x, int y){
		if (x < N && x >= 0 && y < N && y >= 0 && p!= null){
			for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {	
            		if (pieces[i][j] == p) { //if the piece already at another position, change to null at that position;
            			pieces[i][j] = null;
            		}		
            	}
            }
			pieces[x][y] = p;     		
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (xi>=0 && xi<N && yi>=0 && yi<N && xf>=0 && xf<N && yf>=0 && yf<N){
			Piece p = pieceAt(xi, yi);
			if (p!=null){
				if (pieceAt(xf, yf) == null){ //if the new position is empty
					if (p.isFire()){
						if (yf == yi + 1 && (xf == xi +1 || xf == xi -1)) { 
							canCapture=false;//if just move, can't capture
							return true;
						}
						else if (yf ==yi + 2 && xf == xi + 2 && (pieceAt(xi+1, yi+1)!=null) && (!pieceAt(xi+1, yi+1).isFire())){ // if two positions away and has another piece to be captured, can capture
							canCapture=true;
							return true;
						}
						else if (yf ==yi + 2 && xf == xi - 2 && (pieceAt(xi-1, yi+1)!=null) && (!pieceAt(xi-1, yi+1).isFire())){ // if two positions away and has another piece to be captured, can capture
							canCapture=true;
							return true;
						}
						//if is King, can move more freely
						if (p.isKing()){
							if ((yf == yi-1 || yf== yi+1)&& (xf == xi +1 || xf == xi -1)){ 
								canCapture=false;
								return true;
							}
							else if (xf == xi + 2 && yf ==yi - 2 && (pieceAt(xi+1, yi-1)!=null) && (!pieceAt(xi+1, yi-1).isFire())){
								canCapture=true;
								return true;
							}
							else if (yf ==yi - 2 && xf == xi - 2 && pieceAt(xi-1, yi-1)!=null && (!pieceAt(xi-1, yi-1).isFire())){
								canCapture=true;
								return true;
							}
						}
					}
					//if the piece is Water
					else if (!p.isFire()){
						if (yf == yi - 1 && (xf == xi + 1 || xf == xi -1)) {
							canCapture=false; //if just move, can't capture
							return true;
						}
						else if (xf == xi + 2 && yf ==yi - 2 && pieceAt(xi+1, yi-1)!=null && pieceAt(xi+1, yi-1).isFire()){
							canCapture=true;
							return true;
						}
						else if (xf == xi - 2 && yf ==yi - 2 && pieceAt(xi-1, yi-1)!=null && pieceAt(xi-1, yi-1).isFire()){
							canCapture=true;
							return true;
						}
						//if is King, can move more freely
						if (p.isKing()){
							if ((yf == yi-1 || yf== yi+1) && (xf == xi + 1 || xf == xi -1)) {
								canCapture=false;
								return true;
							}
							else if (xf == xi + 2 && yf ==yi + 2  && pieceAt(xi+1, yi+1)!=null &&  pieceAt(xi+1, yi+1).isFire()){
								canCapture=true;
								return true;
							}
							else if ( xf == xi - 2 && yf ==yi + 2 && pieceAt(xi-1, yi+1)!=null && pieceAt(xi-1, yi+1).isFire()){
								canCapture=true;
								return true;
							}
						}
					}	
				}
			}
		}
		canCapture = false;
		return false;
	}

	// Returns true if the square at (x, y) can be selected.
	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x, y);

		/*
		A non-empty piece may be selected if it is the corresponding player’s turn and one of the following is true:
			-The player has not selected a piece yet.
			-The player has selected a piece, but did not move it.*/
		if (p!=null){
			if (selected ==false || (selected == true && moved==false)){
				if (turn == 0 && p.isFire()){
					return true;
				}
				else if (turn ==1 && !p.isFire()){
					return true;
				}
			}
		} 	
		//if we have selected a non-empty piece and try to select an empty piece as destination
		else if (selected ==true){ 
			boolean ifValid = validMove(oldx, oldy, x, y);
			if(ifValid){	
				if (!activePiece.hasCaptured()){ 
					if (!moved){ //if the piece has not captured nor move, we can move
						return true;
					}
				}
				else if (canCapture==true){ //if the piece has captured but can capture again, we can capture
					return true;
					
				}
				else if (canCapture == false){ //if the piece has captured and cannot capture again, we are done
					activePiece.doneCapturing();
					return false;
				}		
			}
		}	
		//Otherwise, we can't select	
		return false;		
	}
	

	//Select the piece at (x,y)
	public void select(int x, int y){	
		selected=true;
		//if select an empty piece, should move the active piece there, and update its position info
		if (pieceAt(x,y)==null){
			moved = true;
			activePiece.move(x,y);
			oldx=x;
			oldy=y;
		}
		else { //if select a non-empty piece, that piece becomes the active piece and we update its position info
			activePiece = pieceAt(x,y);
			oldx=x;
			oldy=y;
		}

	}
	
	
	// remove the piece at (x, y) and return it
	public Piece remove(int x, int y){
		if (x>7 || x < 0 ||y>7 ||y<0){
			System.out.println("Coordinates is out of the board");
			return null;
		}
		else{
			Piece p = pieceAt(x,y);
			if (p==null){
				System.out.println("No piece to remove");
				return null;
			}
			else{
				pieces[x][y] = null;
				return p;
			}
		}
	}

	// if has moved once, can end turn
	public boolean canEndTurn(){
		if (moved==true){
			return true;
		}
		return false;		
	}
	
	//end the turn, switch side, change everything to false;
	public void endTurn(){
		if(turn == 0){
			turn =1;
		}
		else{
			turn =0;
		}
		selected = false;
		moved = false;
		canCapture=false;
		activePiece.doneCapturing();
	}

	// return the winner
	public String winner(){
		numberOfFire=0;
		numberOfWater=0;
		//count the number of pieces from each side
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {	
            	Piece x = pieceAt(i,j);
            	if (x!= null){
            		if (x.isFire()){
            			numberOfFire = numberOfFire+1;
            		}
            		else{
            			numberOfWater = numberOfWater+1;
            		}
            	} 
            }
            
        }

        // return the winner
		if (numberOfWater > 0 && numberOfFire ==0){
			return "Water";
		}
		else if (numberOfWater == 0 && numberOfFire > 0){
			return "Fire";
		}
		else if (numberOfWater == 0 && numberOfFire ==0){
			return "No one";
		}
		//otherwise, return null and game continues
		return null;	
	}
	
}