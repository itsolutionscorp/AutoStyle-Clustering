public class Board{
	private static Board playingBoard;
	private Piece[][] pieces;
	private Piece selectedPiece;
	private int lX,lY;
	private boolean moved;
	private boolean turn; //True for fire, False for water
	/*code from StdDrawDemo.java file by Josh Hug*/
	 private static void drawBoard(int N) {
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if(playingBoard.lX==i && playingBoard.lY==j && playingBoard.pieceAt(playingBoard.lX,playingBoard.lY) == playingBoard.selectedPiece)  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            	else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                String body;
	                if (playingBoard.pieceAt(i,j) != null) {
	                	body = "img/";
	                	Piece toDraw = playingBoard.pieceAt(i,j);
	                	if(toDraw.isBomb()) body = body.concat("bomb");
	                	else if(toDraw.isShield()) body = body.concat("shield");
	                	else body=body.concat("pawn");
	                	if(toDraw.isFire()) body= body.concat("-fire");
	                	else body = body.concat("-water");
	                	if(toDraw.isKing()) body=body.concat("-crowned");
	                	body=body.concat(".png");
	                    StdDrawPlus.picture(i + .5, j + .5, body, 1, 1);
	                }
	            }
	        }
	    }
	public static void main(String args[]){
		 int N = 8;
		 playingBoard=new Board(false);
	        StdDrawPlus.setXscale(0, N);
	        StdDrawPlus.setYscale(0, N);
	        /** Monitors for mouse presses. Wherever the mouse is pressed,
	            a new piece appears. */
	        while(true) {
	            drawBoard(N);
	            if (StdDrawPlus.mousePressed()) {
	                double x = StdDrawPlus.mouseX();
	                double y = StdDrawPlus.mouseY();
	                if(playingBoard.canSelect((int)x,(int)y)){
		                playingBoard.select((int)x,(int)y);
		            }
	            }
	            if(StdDrawPlus.isSpacePressed()){
	            	if(playingBoard.canEndTurn()){
	            		playingBoard.endTurn();
	            	}
	            }
	            if(playingBoard.winner()!=null){
	            	System.out.println(playingBoard.winner());
	            	break;
	            }
	            StdDrawPlus.show(100);
	           
	        }
	    }
	public Board (boolean shouldBeEmpty){
		int sides=8;
		lX=-10;
		lY=-10;
		if(shouldBeEmpty){
			this.pieces = new Piece[sides][sides];
		}
		else{
			this.pieces = new Piece[sides][sides];
			this.place(new Piece(true, this, 0, 0, "pawn"),0,0);
			this.place(new Piece(true, this, 2, 0, "pawn"),2,0);
			this.place(new Piece(true, this, 4, 0, "pawn"),4,0);
			this.place(new Piece(true, this, 6, 0, "pawn"),6,0);
			this.place(new Piece(true, this, 1, 1, "shield"),1,1);
			this.place(new Piece(true, this, 3, 1, "shield"),3,1);
			this.place(new Piece(true, this, 5, 1, "shield"),5,1);
			this.place(new Piece(true, this, 7, 1, "shield"),7,1);
			this.place(new Piece(true, this, 0, 2, "bomb"),0,2);
			this.place(new Piece(true, this, 2, 2, "bomb"),2,2);
			this.place(new Piece(true, this, 4, 2, "bomb"),4,2);
			this.place(new Piece(true, this, 6, 2, "bomb"),6,2);
			this.place(new Piece(false, this, 1, 7, "pawn"),1,7);
			this.place(new Piece(false, this, 3, 7, "pawn"),3,7);
			this.place(new Piece(false, this, 5, 7, "pawn"),5,7);
			this.place(new Piece(false, this, 7, 7, "pawn"),7,7);
			this.place(new Piece(false, this, 0, 6, "shield"),0,6);
			this.place(new Piece(false, this, 2, 6, "shield"),2,6);
			this.place(new Piece(false, this, 4, 6, "shield"),4,6);
			this.place(new Piece(false, this, 6, 6, "shield"),6,6);
			this.place(new Piece(false, this, 1, 5, "bomb"),1,5);
			this.place(new Piece(false, this, 3, 5, "bomb"),3,5);
			this.place(new Piece(false, this, 5, 5, "bomb"),5,5);
			this.place(new Piece(false, this, 7, 5, "bomb"),7,5);
		}
		moved=false;
		turn=true;
	}
	public Piece pieceAt(int x, int y){
		if(inBounds(x,y)) return pieces[x][y];
		return null;
	}
	public boolean canSelect(int x, int y){
		int s= 8;
		if(!inBounds(x,y)) return false;
		if (pieceAt(x,y)!= null && pieceAt(x,y).isFire()==turn){
			if(!moved){
				return true;
			}
		}
		else{
			if(pieceAt(lX,lY) != null && !moved && validMove(lX,lY,x,y)){
				return true;
			}
			else if(pieceAt(lX,lY) != null && moved && pieceAt(lX,lY).hasCaptured() && validCapture(lX, lY, x,y)){
				return true;
			}
		}
		return false;
	}
	private boolean validCapture(int xi, int yi, int xf, int yf){
		if(!properDirection(xi,yi,xf,yf)) return false;
		if(pieceAt(xi,yi)!=selectedPiece) return false;
		if((Math.abs(xi-xf)==2) && (Math.abs(yi-yf)==2)){//captures
			Piece centerPiece=center(xi,yi,xf,yf);
			if(centerPiece!=null){//something there
				if(centerPiece.isFire() != turn) return true; //enemy piece
				else return false;
			}
			else return false;
		}
		return false;		
	}
	private Piece center(int xi, int yi, int xf, int yf){
		return pieceAt((int)((xi+xf)/2),(int)((yi+yf)/2));
	}
	private boolean properDirection(int xi, int yi, int xf, int yf){
		Piece selectedPiece= pieceAt(xi,yi);
		if(selectedPiece == null) return false;
		if(selectedPiece.isKing()) return true;
		if(turn){//isFire
			if(yf-yi > 0){//moving up
				return true;
			}
			return false;
		}
		else{//isWater
			if(yf-yi > 0){//moving up
				return false;
			}
			return true;
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(!properDirection(xi,yi,xf,yf)) return false;
		if(pieceAt(xf,yf)!=null){//piece at final location
			return false;
		}
		if(validCapture(xi,yi,xf,yf)) return true;
		Piece active = pieceAt(xi,yi);
		if(active == null) return false;
		boolean isKing = active.isKing();
		if (isKing){
			if((Math.abs(xf-xi)==1) && (Math.abs(yf-yi)==1)){
				return true;
			}
		}
		else{//not a king
			if(Math.abs(xf-xi)==1 && Math.abs(yf-yi)==1){
				return true;
			}
		}
		return false;
	}
	public void select(int x, int y) {
		Piece sP= pieceAt(x,y);
		if(sP != null){
				selectedPiece=sP;
				lX=x;
				lY=y;
			}
		else{
				lX=x;
				lY=y;
				selectedPiece.move(x,y);
				moved=true;
			}
	}
	public void place(Piece p, int x, int y) {
		if(p == null || !inBounds(x,y)) return;
		pieces[x][y]=p;
	}
	private boolean inBounds(int x, int y){
		return x<8 && y<8 && x>=0 && y>=0;
	}
	public Piece remove(int x, int y) {
		Piece select= pieceAt(x,y);
		if(!inBounds(x,y)){
			System.out.println("Location out of bounds ("+x+", "+y+").");
		}
		else if(select==null){
			System.out.println("No piece at location ("+x+", "+y+").");
		}
		else{
			pieces[x][y]=null;
		}
		return select;
	}
	public boolean canEndTurn() {
		if(moved){
			return true;
		}
		return false;
	}

	public void endTurn() {
		turn = !turn;
		moved = false;
		for(int i = 0; i<8;i++){
			for(int j=0;j<8;j++){
				if(pieces[i][j]!=null) pieces[i][j].doneCapturing();
			}
		}
		selectedPiece=null;
		lX=-10;
		lY=-10;
	}
	public String winner() {
		int blueCount = 0;
		int redCount = 0;
		int N = 8;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	Piece select=pieceAt(i,j);
            	if(select!=null){
            		if(select.isFire()){
            			redCount+=1;
            		}
            		else{
            			blueCount+=1;
            		}
            	}
            }
		}
		if(blueCount==0 && redCount==0){
			return "No one";
		}
		else if(blueCount==0){
			return "Fire";
		}
		else if(redCount==0){
			return "Water";
		}
		else{
			return null;
		}
		}
}