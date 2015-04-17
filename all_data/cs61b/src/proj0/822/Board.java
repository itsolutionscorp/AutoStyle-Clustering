public class Board{

	private boolean hasMoved;
	private boolean hasSelected;
	private int turn;
	private Piece selected;
	private int currentSelectX;
	private int currentSelectY;
	private Piece[][] listOfPieces;


	public static void main (String[] args){
		


		Board b = new Board(false);
		int N = 8;
		while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x,y))
                	b.select(x,y);
                
            }
            if(StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            }  
            StdDrawPlus.show(100);
        }
	}

	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if ( (currentSelectX == i) && (currentSelectY == j)) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                else if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY); 
                else 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece current = listOfPieces[i][j];
                if (current != null){
                	String crowned;
                	String type;
                	if (current.isBomb()){
                		type ="bomb";
                	}
                	else if (current.isShield()){
                		type = "shield";
                	}else{
                		type = "pawn";
                	}

                	if (current.isKing())
                		crowned = "-crowned";
                	else
                		crowned = "";


                	if (current.isFire()){
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-fire"+crowned+".png", 1, 1);
                	}
                	else{
                		StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-water"+crowned+".png", 1, 1);
                	}
                }
            }
        }
    }
	public Board(boolean shouldBeEmpty){
		this.hasMoved = false;
		this.hasSelected = false;
		this.currentSelectX = -1;
		this.currentSelectY = -1;
		this.turn = 0;
		int N = 8;
		listOfPieces = new Piece[N][N];

		if (!shouldBeEmpty){



			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if (j == 0 && (i%2 == 0)){
	            		listOfPieces[i][j] = new Piece(true, this, i, j, "pawn" );
	            	}
	            	else if ((j == 1) && (i%2 != 0) ){
	            		listOfPieces[i][j] = new Piece(true, this, i, j, "shield" );
	            	}
	            	else if ((j == 2) && (i%2 == 0) ){
	            		listOfPieces[i][j] = new Piece(true, this, i, j, "bomb" );
	            	}
	            	else if ((j == 5) && (i%2 != 0) ){
	            		listOfPieces[i][j] = new Piece(false, this, i, j, "bomb" );
	            	}
	            	else if ((j == 6) && (i%2 == 0) ){
	            		listOfPieces[i][j] = new Piece(false, this, i, j, "shield" );
	            	}
	            	else if ((j == 7) && (i%2 != 0) ){
	            		listOfPieces[i][j] = new Piece(false, this, i, j, "pawn" );
	            	}

	           }
	        }



	
	

    }
    

	}
	public Piece pieceAt(int x, int y){

		if ((x >7) || (y > 7) || (x < 0) || (y<0) || (listOfPieces[x][y]==null)) {
			return null;
		}
		else{
			return listOfPieces[x][y];
		}
	}

	public boolean canSelect(int x, int y){
		Piece current = pieceAt(x, y);
		
		if (current != null && current.side()== turn  && !hasMoved){
			
			return true;
		}
		else if(current == null && hasSelected && !hasMoved && validMove(currentSelectX,currentSelectY,x,y)){
			
		
			return true;
		}
		else if (hasSelected && current == null && pieceAt(currentSelectX,currentSelectY) != null 
			&& pieceAt(currentSelectX,currentSelectY).hasCaptured() && Math.abs(x- currentSelectX) == 2 && validMove(currentSelectX,currentSelectY,x,y) ){
			return true;
		}
		return false;


	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		int capy;
		int capx;


		if (yf > yi)
			capy = yi+1;
		else
			capy = yi-1;
		if (xf > xi)
			capx = xi+1;
		else
			capx = xi-1;
		Piece toCap = pieceAt(capx,capy);
		boolean distance = false;
		Piece last = pieceAt(xi,yi);
		if (last.isKing())
			distance = (Math.abs(xi-xf)==1 && Math.abs(yi-yf) == 1) || (toCap != null && Math.abs(xi-xf)==2 && Math.abs(xi-xf)==2);
		else if (last.side() == 0)
			distance = (Math.abs(xi-xf)==1 && (yi + 1) == yf) || (toCap != null && Math.abs(xi-xf)==2 && (yi + 2) == yf);
		else
			distance = (Math.abs(xi-xf)==1 && (yi - 1) == yf) || (toCap != null && Math.abs(xi-xf)==2 && (yi - 2) == yf);
		
		if(distance && (toCap == null || (toCap != null && (toCap.side() != pieceAt(xi,yi).side()))) && pieceAt(xf,yf) == null) 
				return true;
		return false;
	}

	public void select(int x, int y){

		Piece lastCurrent = pieceAt(currentSelectX, currentSelectY);
		Piece current = pieceAt(x,y);
		if (current != null  && !hasMoved){
			hasSelected = true;
			
			selected = current;
		}
		else if(current == null && hasSelected && !hasMoved && validMove(currentSelectX,currentSelectY,x,y)){

			hasMoved = true;
			lastCurrent.move(x,y);
			Piece capturer = pieceAt(x,y);
			if (capturer.isBomb() && capturer.hasCaptured())
				remove(x,y);
			
			
		}
		else if (hasSelected && current == null && lastCurrent != null 
			&& lastCurrent.hasCaptured() && Math.abs(x- currentSelectX) == 2 && validMove(currentSelectX,currentSelectY,x,y) ){

			lastCurrent.move(x,y);
		}
		currentSelectY = y;
		currentSelectX = x;


	}

	public void place(Piece p, int x, int y) {
		if ((x >7) || (y > 7) || (x < 0) || (y<0) || p == null ){

			return;
		}
		for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	if(pieceAt(i,j)==p){
	        		listOfPieces[i][j] = null;
	        		break;
	        	}
	        	
	        	
            }
        }
		
		listOfPieces[x][y] = p; 
		
		
}

	

	public Piece remove(int x, int y){


		if ((x >7) || (y > 7) || (x < 0) || (y<0) ){
			System.out.println("out of bounds");
			return null; 
		}
		else if(pieceAt(x,y) == null){
			System.out.println("no piece existing");
			return null;

		}
		else{

			Piece removed = pieceAt(x,y);
			

			listOfPieces[x][y] = null;
			return removed;

		}
	}
	public boolean canEndTurn(){
		if (hasMoved || (selected!=null && selected.hasCaptured()))
			return true;
		return false;
	}
	public void endTurn(){

		hasMoved = false;
		hasSelected = false;
		currentSelectX = -1;
		currentSelectY = -1;
		if (selected != null){
			selected.doneCapturing();
			selected = null;
		}

		if (turn == 0)
			turn = 1;
		else
			turn = 0;
	}
	public String winner(){
		int nFire = 0;
		int nWater = 0;
		for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	Piece current = pieceAt(i,j);
	        	if (current != null){
	        		if (current.isFire())
	        			nFire+=1;
	        		else
	        			nWater+=1;
	        	}
}
}
		if ((nFire == 0) && (nWater == 0)){
			return "No one";
		}
		else if(nFire == 0){
			return "Water";
		}
		else if (nWater == 0){
			return "Fire";
		}
		return null;
	}

}