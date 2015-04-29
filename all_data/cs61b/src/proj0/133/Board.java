/* implementation of board class */

public class Board{

	private boolean shouldBeEmpty;
	private int N = 8;
    private Piece[][] pieces;
    private boolean[][] pieceSelect = new boolean[N][N];
    private boolean isFiresTurn = true;
    private boolean hasSelectedPiece = false;
    private int[] selectedPieceCoodinates = new int[2];
    //private boolean hasSelectedSquare = false;
    private boolean hasMoved = false; 
  

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty=shouldBeEmpty;
		if (!shouldBeEmpty){
			initializePieces(N);
		}
		else {
			pieces = new Piece[N][N];
		}
	}



    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceSelect[i][j]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
        drawPieces(N);
    }

    private void initializePieces(int N){
    	pieces = new Piece[N][N];
    	for (int i = 0; i < N; i++) {
    		for (int j=0; j < N; j++){
    			if ((i + j) % 2 == 0){
	    			switch (j){
	    				case 0: pieces[i][j]=new Piece(true, this, i, j, "pawn");
	    						break;
	    				case 1: pieces[i][j]=new Piece(true, this, i, j, "shield");
	    						break;
	    				case 2: pieces[i][j]=new Piece(true, this, i, j, "bomb");
	    						break;
	    				case 5: pieces[i][j]=new Piece(false, this, i, j, "bomb");
	    						break;
	    				case 6: pieces[i][j]=new Piece(false, this, i, j, "shield");
	    						break;
	    				case 7: pieces[i][j]=new Piece(false, this, i, j, "pawn");
	    						break;	
	    				default: pieces[i][j]=null;
	    						break;
	    			}
	    		}
    		}
    	}
    }
    private void drawPieces(int N){
		for (int i = 0; i < N; i++){
    		for (int j=0; j < N; j++){
    			Piece p = pieces[i][j];
				if (p!=null){
					if (p.isFire()){
						if (p.isBomb()){
							if (p.isKing()){
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
					    	} else {
					    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					   		} 
						} else if (p.isShield()){
							if (p.isKing()){
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
					    	} else {
					    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					   		} 
						} else {
							if (p.isKing()){
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
					    	} else {
					    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					   		}
						}
					} else {
						if (p.isBomb()){
							if (p.isKing()){
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					    	} else {
					    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					   		} 
						} else if (p.isShield()){
							if (p.isKing()){
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					    	} else {
					    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					   		} 
						} else {
							if (p.isKing()){
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
					    	} else {
					    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					   		}
						}
					}
				}
            }	
    	}
	}    
    

	public Piece pieceAt(int x, int y){
		if (outOfBound(x, y)){
			return null;
		} else{
			return pieces[x][y];
		}
	}

/** place a piece at x,y, if reaches the other end, crown it
 * also can place null at x,y 
*/
	public void place(Piece p, int x, int y){
		if (outOfBound(x, y)){
		} else {
			//if (p!=null) {
				//p.x=x;
				//p.y=y;
				//if (canCrown(p)) {
				//	crown(p);
				//}
			//}
			pieces[x][y]=p;
		}
	}

/** test if out of bound
*/

	private boolean outOfBound(int x, int y){
		return (x<0 || x>=N || y<0 || y>=N);
	}

/** Executes a remove. Returns the piece that was removed. 
 * If the input (x, y) is out of bounds, returns null and prints 
 * an appropriate message. If there is no piece at (x, y), returns
 * null and prints an appropriate message.
 */

	public Piece remove(int x, int y){
		if (outOfBound(x, y)){
			System.out.println("the coodinates (x, y) are outside the board!");
			return null;
		} else{
			Piece p = pieceAt(x, y);
			if (p==null) {
				System.out.println("no piece at coodinates (x, y)!");
			}
			place(null ,x, y);
			return p;
		}

	}

// /** whether can crown
// */
// 	private boolean canCrown(Piece p){
// 		boolean shouldReachEnd = ((p.y==(N-1) && p.isFire()) || (p.y==0) && !p.isFire());
// 		return ((!p.isKing() && shouldReachEnd));
// 	}

// /** crown a piece
// */
// 	private void crown(Piece p){
// 		if (p.type.equals("pawn")){
// 			p.type = "pawn-king";
// 		}
// 		if (p.type.equals("shield")){
// 			p.type = "shield-king";
// 		}
// 		if (p.type.equals("bomb")){
// 			p.type = "bomb-king";
// 		}
// 	}



	public boolean canSelect(int x, int y){
		if (pieces[x][y]!=null){
			return canSelectPiece(x,y);
		} else {
			return canSelectSquare(x,y);
		}
	}
	/** return true if can select piece
	*/
	private boolean canSelectPiece(int x, int y){
		return ( isMyTurn(x,y) && ((!hasSelectedPiece) || hasSelectedPiece && (!hasMoved)));
	}

	/** return true if can select empty square
	*/
	private boolean canSelectSquare(int xf, int yf){
		if (!hasSelectedPiece){
			return false;
		} else {
			int xi=selectedPieceCoodinates[0];
			int yi=selectedPieceCoodinates[1];
			Piece p = pieceAt(xi, yi);
			if (p==null){
				return false; //bomb has been removed!! so no piece at (xi, yi)!!
			}
			if ((!hasMoved) && validMove(xi,yi,xf,yf)){
				return true;
			} else if (p.hasCaptured() && validMove(xi,yi,xf,yf) && (Math.abs(xi-xf)==2)) {
				return true;
			} else {
				return false;  
			}
		}
	}

	/** test if it is a valid move
	 * 1\ pieces[xf][yf] should be null;
	 * 2\ get value for dx=xf-xi and dy=yf-yi;
	 * 3\ if isFire, dy<0 && not king--->false;
	 * 4\ if abs(dy==1) && abs(dx==1)--->true;
	 * 5\ if abs(dx,dy==2), xi+dx,yi+dy, not fire --->true; 
	*/ 
	private boolean validMove(int xi, int yi, int xf, int yf){
		int dx=xf-xi;
		int dy=yf-yi;
		Piece p = pieces[xi][yi];
		if (pieces[xf][yf]!=null){
			return false;
		} else if ( ((p.isFire() && dy<=0) || (!p.isFire() && dy>=0 )) && (!p.isKing()) ){
			return false;
		} else if ((Math.abs(dx)==1)&&(Math.abs(dy)==1)){
			return true;
		} else if ((Math.abs(dx)==2)&&(Math.abs(dy)==2)){
			Piece pMiddle = pieces[xi+dx/2][yi+dy/2];
			if (pMiddle == null){
				return false;
			} else if (pMiddle.isFire()^p.isFire()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/* return true if it is the corresponding player’s turn 
	*/
	private boolean isMyTurn(int x, int y){
		return (!(pieces[x][y].isFire()^isFiresTurn));
	}

	public void select(int x, int y){
			pieceSelect = new boolean[N][N];
			pieceSelect[x][y]=true;
		if (pieces[x][y]!=null){
			hasSelectedPiece = true;
			selectedPieceCoodinates[0]=x;
			selectedPieceCoodinates[1]=y;
		} else {
			//hasSelectedSquare = true;
			int xi = selectedPieceCoodinates[0];
			int yi = selectedPieceCoodinates[1];
			Piece p = pieceAt(xi, yi);
			p.move(x, y); 
			hasMoved = true; 
			selectedPieceCoodinates[0]=x;
			selectedPieceCoodinates[1]=y;  //test if can work
		}
	}

	/** return true if can End my turn 
	*/

	public boolean canEndTurn(){
		if (hasMoved){
			return true;
		} else {
			return false;
		}
	}

	/** to end my turn 
	 * 1\ exchange turn 
	 * 2\ reset hasMoved
	 * 3\ reset hasSelectedPiece and hasSelectedSquare
	 * 4\ reset selectedPieceCoodinates
	 * 5\ reset pieceSelect
	*/ 
	public void endTurn(){
		isFiresTurn = (!isFiresTurn);
		hasMoved = false;
		hasSelectedPiece = false;
		//hasSelectedSquare = false;
		selectedPieceCoodinates = new int[2];
		pieceSelect = new boolean[N][N];	
	}
	/** Returns the winner of the game: "Fire", "Water", "No one",
	 * or null. If only fire pieces remain on the board, fire wins. 
	 * If only water pieces remain, water wins. If no pieces remain 
	 * (consider an explosion capture), no one wins. If the game is 
	 * still in progress (ie there are pieces from both sides still 
	 * on the board) return null. Assume there is no stalemate 
	 * situation in which the current player has pieces but cannot 
	 * legally move any of them (In this event, just leave it at null). 
	 * Determine the winner solely by the number of pieces belonging 
	 * to each team.
	 */
	public String winner(){
		if (existFire() && existWater()){
			return null;
		} else if (existWater()){
			return "Water";
		} else if (existFire()){
			return "Fire";
		} else {
			return "No one";
		}
	}
/** whether there is fire piece exist on the board
 */
	private boolean existFire(){
		for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
           		Piece p = pieceAt(i, j);
           		if (p!=null){
           			if (p.isFire()){
           				return true;
           			}
           		}
           	}
		}
		return false;
	}

/** whether there is water piece exist on the board
 */
	private boolean existWater(){
		for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
           		Piece p = pieceAt(i, j);
           		if (p!=null){
           			if (!p.isFire()){
           				return true;
           			}
           		}
           	}
		}
		return false;
	}

	private void mouseSelect(){
		if (StdDrawPlus.mousePressed()) {
		            double xDouble = StdDrawPlus.mouseX();
		            double yDouble = StdDrawPlus.mouseY();
		            int x = (int) xDouble;
		            int y = (int) yDouble;
		            if (this.canSelect(x, y)){
		            		this.select(x, y);
		            		this.drawBoard(this.N);
	            	}
		}
	}	

	public static void main(String[] args) {
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b.drawBoard(N);
        while (b.winner()==null){
        	while (!b.canEndTurn()){
        		b.mouseSelect();
        	} 
        	while (b.canEndTurn()){
        		if (StdDrawPlus.isSpacePressed()){
        			b.endTurn();
        	    } else {
        	    	b.mouseSelect();
        	    }
	        }
	    }
	}
    
}