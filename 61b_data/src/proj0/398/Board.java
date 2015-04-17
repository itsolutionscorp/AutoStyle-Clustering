/* Author: Nikita Rau
 * CS 61B Project 0 */

public class Board{
	private boolean emptyBoard;
	private static Piece[][] pieces = new Piece[8][8];
	private int length=8;
	private boolean allowMove=false;
	private int player=1;
	private Piece selectedPiece;
	private boolean allowSelect = false, hasSelected = false, hasMoved=false;
	private int originalsX, originalsY;
	//private boolean allowSecondMove=false;

	public Board(boolean shouldBeEmpty){
		emptyBoard = shouldBeEmpty;
		if(emptyBoard==false){
			for(int x=0;x<length;x++){
				for(int y=0;y<length;y++){ 
					if ((x + y) % 2 == 0){
						if ((y==0)){
							pieces[x][y] = new Piece(true, this, x, y, "pawn");
						}
						if ((y==1)){
							pieces[x][y] = new Piece(true, this, x, y, "shield");;
						}
						if ((y==2)){
							pieces[x][y] = new Piece(true, this, x, y, "bomb");
						}
						if ((y==5)){
							pieces[x][y] = new Piece(false, this, x, y, "bomb");
						}
						if ((y==6)){
							pieces[x][y] = new Piece(false, this, x, y, "shield");
						}
						if ((y==7)){
							pieces[x][y] = new Piece(false, this, x, y, "pawn");
						}	
		}}}}
		else{
			for(int x=0;x<length;x++){
				for(int y=0;y<length;y++){
					pieces[x][y]=null;
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if (x>=length || y>=length || x<0 || y<0 || (pieces[x][y]==null)){
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y){
		if (!(x>=length) && !(y>=length) && !(x<0) && !(y<0) && (p!=null)){
			pieces[x][y] = p;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		//move is out of bounds for x or y 
		if ((xi<0) || (xi>=length) || (yi<0) || (yf>=8)){
			allowMove=false;		
		}
		//final position is already occupied
		else if (pieceAt(xf,yf)!=null){
			allowMove=false;
		}

		//fire piece case
		else if (pieceAt(xi,yi).isFire()) {
			//fire piece can only move upwards when it is not a king
			if(!(pieceAt(xi,yi).isKing())) {
				if((yf>yi)){
					if ((Math.abs(xf-xi)==1) && (yf-yi==1)) {
						allowMove=true;
					}

					//a capture move diagonally upwards for a fire piece that is not a king
					else if ((Math.abs(xf-xi)==2) && (yf-yi==2)) {
						if( (((pieceAt(xi-1,yi+1))!=null) && !((pieceAt(xi-1,yi+1)).isFire())) || (((pieceAt(xi+1,yi+1))!=null) && !((pieceAt(xi+1,yi+1)).isFire())) ){
							allowMove=true;
						}
					}
				}
			}
			//fire piece can move upwards and downwards when it is a king
			else if (pieceAt(xi,yi).isKing()) {
				//a single move diagonally
				if ((Math.abs(xf-xi)==1) && (Math.abs(yf-yi)==1)) {
					allowMove=true;
				}
				//a capture move diagonally for a fire king
				else if ((Math.abs(xf-xi)==2) && (Math.abs(yf-yi)==2)) {
					if( (((pieceAt(xi+1, yi+1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi-1,yi+1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi+1, yi-1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi-1, yi-1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) ){
						allowMove=true;
						// //check if double capture is possible
						// if( (((pieceAt(xi+1, yi+1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi-1,yi+1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi+1, yi-1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi-1, yi-1))!=null) && !((pieceAt(xi+1, yi+1)).isFire())) ) {
						// 	allowSecondMove=true;
						// }
					}
				}
			}
		}	

		//water piece case
		else if (!(pieceAt(xi,yi).isFire())){
			//water piece can only move downwards when it is not a king
			if(!(pieceAt(xi,yi).isKing())){
				if(yf<yi){
					if ((Math.abs(xf-xi)==1) && (yf-yi==-1)){			
						allowMove=true;
					}
					//a capture move diagonally downwards for a water piece that is not a king
					else if ((Math.abs(xf-xi)==2) && (yf-yi==-2)){
						if ( (((pieceAt(xi-1, yi-1))!=null) && ((pieceAt(xi-1, yi-1)).isFire())) || (((pieceAt(xi+1, yi-1))!=null) && ((pieceAt(xi+1, yi-1)).isFire())) ){
							allowMove=true;
						}
					}
				}
			}
			//water piece can move upwards and downwards when it is a king
			else if (pieceAt(xi,yi).isKing()){
				if ((Math.abs(xf-xi)==1) && (Math.abs(yf-yi)==1)){
					allowMove=true;
				}
				//a capture move diagonally for a water king
				else if ((Math.abs(xf-xi)==2) && (Math.abs(yf-yi)==2)) {
					if( (((pieceAt(xi+1, yi+1))!=null) && ((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi-1,yi+1))!=null) && ((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi+1, yi-1))!=null) && ((pieceAt(xi+1, yi+1)).isFire())) || (((pieceAt(xi-1, yi-1))!=null) && ((pieceAt(xi+1, yi+1)).isFire())) ){
						allowMove=true;
					}
				}
			}
		}
		return allowMove;
	}

	/*worked with Christine Munar (username: axi) at office hours in Bechtel for a day on canSelect*/
	public boolean canSelect(int x, int y) {
		boolean selectable = false;
		//when it is fire player's turn
		if (player==1) {
			if ((pieceAt(x,y) != null)) { //when there is a piece in there already
				if ((pieceAt(x,y).isFire() == true) && ((hasSelected == false) || (hasMoved == false))) {
					selectable = true;
				}
			}
			else if ((pieceAt(x,y) == null) && (selectedPiece!=null)) { //when the square is empty
				if ((selectedPiece.isFire() == true) && (hasSelected == true) && (hasMoved == false) && (this.validMove(originalsX, originalsY, x, y))) {
					originalsX = x;
					originalsY = y;
					selectable = true;
				}
			if ((selectedPiece.hasCaptured() == true)) {
				if(this!=null){
				if ( (hasSelected == true) &&  (this.validMove(originalsX, originalsY, x, y))) {
					selectable = true;
				}}
			}
		}
	}

		//when it is the water player's turn
		else if (player == -1){
			if ((pieceAt(x,y) != null)) { //when there is a piece in there already
				if ((pieceAt(x,y).isFire() == false) && ((hasSelected == false) || (hasMoved == false))) {
					selectable = true;
				}
			}
			else if ((pieceAt(x,y) == null) && (selectedPiece.isFire() == false)) { //when the square is empty
				if ((hasSelected == true) && (hasMoved == false) && (this.validMove(originalsX, originalsY, x, y))) {
					originalsX = x;
					originalsY = y;
					selectable = true;
				}
				if ((selectedPiece.hasCaptured() == true) && (hasSelected == true)) {
					if (this.validMove(originalsX, originalsY, x, y)) {
							selectable = true;
						}
					}
				}
			}
		return selectable;
	}

	private static int checkTurn (int num) {
		int playerSide = num*-1;
		return playerSide;
	}

	public void select(int x, int y) {
		hasSelected = true;
		if (pieceAt(x,y)!=null) {
			selectedPiece = pieceAt(x,y);
			originalsX=x;
			originalsY=y;
		}
		else if ((selectedPiece != null) && (pieceAt(x,y) == null)) {
			selectedPiece.move(x, y);
			hasMoved = true;
		}
	}

	public Piece remove(int x, int y){
		if(x>=length || y>=length || x<0 || y<0){
			System.out.println("spot is out of bounds");
			return null;
		}
		if(pieces[x][y]==null){
			System.out.println("no piece at (" + x + "," + y + ")");
			return null;
		}
		else{
			Piece copy = pieceAt(x, y);
			//remove
			pieces[x][y]=null;
			return copy;
		}
	}

	public boolean canEndTurn(){
		if((selectedPiece != null) && ((selectedPiece.hasCaptured() == true) || (hasMoved == true))) {
			return true;
		}
		else{
			return false;
		}
	}

	public void endTurn(){
		if(canEndTurn()==true){
			player=this.checkTurn(player);
			hasMoved=false;
			hasSelected=false;
		}
	}

	public String winner(){
		int numFires=0, numWaters=0;
		for(int i=0;i<length;i++){
			for(int j=0;j<length;j++){
				if(pieces[i][j]!=null){
					if (!(pieces[i][j].isFire())) {
						numWaters+=1;
					}
					else if (pieces[i][j].isFire()) {
						numFires+=1;
					}
				}
			}
		}
		System.out.println("numWaters " + numWaters);
		System.out.println("numFires " + numFires);
		if ((numWaters==0) && (numFires>0)){ //could just add an or case for if numWaters==0
			return "Fire";
		}
		else if ((numFires==0) && (numWaters>0)) { //could just add an or case for if numFires==0
			return "Water";
		}
		else if ((numFires==0) && (numWaters==0)) {
			return "No one";
		}
		else {
			return null;
		}
	}

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                // Piece apiece = pieceAt(i,j);
                // if(apiece!=null){
                // 	StdDrawPlus.picture(i+.5,j+.5,StdDrawPlus.picture(apiece), 1, 1);
                // 	if(apiece==(pieceAt(originalsX, originalsY))){
                // 		StdDrawPlus.filledSquare(originalsX+.5, originalsY+.5, .5);
                // 		StdDrawPlus.picture(i+.5,j+.5,StdDrawPlus.picture(apiece),1,1);
                // 	}
                // }
        }}}

    private static void drawPieces(int N) {
        for (int j=0; j < N; j++){
        	for (int i=0;i<N; i++){
        		if (((i+j) % 2 == 0) && ((j<3) || (j>4))){
        			Piece currPiece = pieces[i][j];
	                if ((currPiece.isFire()==true)){
	                	if (currPiece.isShield()){
	                		StdDrawPlus.picture(i+.5,j+.5,"img/shield-fire.png", 1, 1);
	                	}
	                	else if (currPiece.isBomb()){
	                		StdDrawPlus.picture(i+.5,j+.5,"img/bomb-fire.png", 1, 1);
	                	}
	                	else{
	                		StdDrawPlus.picture(i+.5,j+.5,"img/pawn-fire.png", 1, 1);
	                	}
	                }
	                if (currPiece.isFire()==false){
	                	if (currPiece.isShield()){
	                		StdDrawPlus.picture(i+.5,j+.5,"img/shield-water.png", 1, 1);
	                	}
	                	else if (currPiece.isBomb()){
	                		StdDrawPlus.picture(i+.5,j+.5,"img/bomb-water.png", 1, 1);
	                	}
	                	else{
	                		StdDrawPlus.picture(i+.5,j+.5,"img/pawn-water.png", 1, 1); 
	                	}

	                }
	    }}}}

	public static void main(String[] args){
		Board gameBoard = new Board(false);

		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        //pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	gameBoard.drawBoard(N);
        	gameBoard.drawPieces(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (gameBoard.canSelect(x, y)){
                	gameBoard.select(x, y); 	
                }
            }
 			if (StdDrawPlus.isSpacePressed()){
 				if (gameBoard.canEndTurn()){
 					gameBoard.endTurn();
 				}
 			}
 			StdDrawPlus.show(100);
            }
		}
    }