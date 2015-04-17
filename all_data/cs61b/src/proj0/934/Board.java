

public class Board{
    
    private boolean isEmpty;
    
    private Piece[][] pieces;
    private boolean currentPlayerIsFire;
    private boolean hasMoved;
    private int N;
    private int waterPlayers;
    private int firePlayers;
    private Piece selectedPiece;
    private int xSelected=-1;
    private int ySelected=-1;
 

    public Board(boolean shouldBeEmpty){
    	currentPlayerIsFire=true;
    	N = 8;
    	pieces=new Piece[N][N];
    	isEmpty=shouldBeEmpty;
  		if (isEmpty==false) {
  			constructDefaultPieces();
  		}
	}

	/*
	This sets the nested of array pieces to the default game configuration
	*/
	private void constructDefaultPieces(){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	pieces[i][j]=constructPiece(i,j);
			}	
		}
	}
	
	private Piece constructPiece(int x, int y){
		String type="pawn";
    	boolean isFire=y<3 ? true : false;
		if((x+y)%2!=0 || (y>=3 && y<=4)){ 
			return null;
    	}
    	if(y==1 || y==6){ 
    		type="shield";
    	} else if(y==2 || y==5) {
    		type="bomb";
    	} else {
    		type="pawn";
    	}
    	return new Piece(isFire, this, x, y, type);
	}

	/*
	draws an empty checker board and is only called once
	*/
	private void drawEmptyBoard() {
 		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
 		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){ 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        	}
        }
 	}

 	/*
 	draws the pieces onto the board continuously to show progress of the game 
 	*/
 	private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if ((i+j)%2==0) {
                drawPiece(i, j);
            	}
            }
        }
	}

	private String getImage(Piece piece){
		String teamString= piece.isFire() ? "-fire" : "-water";
		String kingString= piece.isKing() ? "-crowned" : "";
		String pieceType="pawn";
		if (piece.isBomb()) {
			pieceType="bomb";
		}
		if (piece.isShield()) {
			pieceType="shield";
		}
		return "img/"+ pieceType + teamString + kingString + ".png";
	}

	private void drawPiece(int x, int y) {	
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Piece p=pieces[x][y];
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		if (p==null) {
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			return;
		}
		else if (p==selectedPiece){
			StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		} 
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    	StdDrawPlus.picture(x + .5, y + .5, getImage(p) , 1, 1);
	}

	public Piece pieceAt(int x, int y){
		if (!onBoard(x,y)){
			return null;
		} else {
			return pieces[x][y];
		}
	}

	private boolean isCorrectDirection(int yi, int yf){
		if (selectedPiece.isKing()){
			return true;
		} else if (yi>=yf){
				return !selectedPiece.isFire();
		} else {
				return selectedPiece.isFire();
		} 
	}

	private boolean isCorrectDistance(int x, int y) {
		int xDistance=Math.abs(x-xSelected);
		int yDistance=Math.abs(y-ySelected);
		return xDistance==yDistance && xDistance<=2;
	}

	private boolean validMove(int x, int y) { 
		
		if (!onBoard(x,y) || !onBoard(xSelected, ySelected)){
			return false;
		} 
		int xDistance=Math.abs(x-xSelected);
		if (!isCorrectDistance(x,y) || !isCorrectDirection(ySelected, y)){
			return false;
		} else if (xDistance==2) {
			Piece p=pieceAt((x+xSelected)/2, (y+ySelected)/2);
			return p!=null && p.isFire()!=currentPlayerIsFire;
		} else if (xDistance==1){
			return !selectedPiece.hasCaptured();
		} else {
			return false;
		}
	} 
	public boolean canSelect(int x, int y){
		Piece p=pieceAt(x, y);
		if (p!=null && hasMoved==false){
			return currentPlayerIsFire==p.isFire();
		} else if (p==null && selectedPiece!=null && (hasMoved==false || (hasMoved==true && selectedPiece.hasCaptured()))){
			return validMove(x,y);
		} else {
			return false;
		}
	}

	public void select(int x, int y){
		Piece p=pieceAt(x,y);
		if(p!=null) {	
			selectedPiece=p;
			xSelected=x;
			ySelected=y;
		} else {
			selectedPiece.move(x, y);
			if (selectedPiece.hasCaptured() && !selectedPiece.isBomb()){
				xSelected=x;
				ySelected=y;
			} else {
				selectedPiece=null;
				xSelected=-1;
				ySelected=-1;
			}
			hasMoved=true;
		}
	}

	private boolean onBoard(int x, int y) {
		return x<N && y<N && x>=0 && y>=0;
	}

	public Piece remove(int x, int y){
		Piece removedPiece=pieceAt(x,y);
		if (!onBoard(x,y)){
			System.out.println("This is not a location on the checkerboard");
			return null;
		} else if(removedPiece!=null){
			pieces[x][y]=null;
			return removedPiece;
		} else {
			System.out.println("There is no piece on that square");
			return null;
		}	
	}

	public void place(Piece p, int x, int y){
		if (!onBoard(x,y)){
			return;
		} else {
			pieces[x][y]=p;
		}
	}

	public void endTurn(){
		if (canEndTurn()==true) {
			if (selectedPiece!=null){
				selectedPiece.doneCapturing();
				selectedPiece=null;	
			}
			hasMoved=false;	
			ySelected=-1;
			xSelected=-1;
			currentPlayerIsFire=!currentPlayerIsFire;
		} 
		
	}

	public boolean canEndTurn() {
		return hasMoved || (selectedPiece!=null && selectedPiece.hasCaptured());
	}
	

	public String winner(){
    	countPieces();
    	if (waterPlayers!=0 && firePlayers!=0) {
    		return null;
    	} else if (waterPlayers==firePlayers){
    	 	return "No one";
    	} else {
    		return (waterPlayers>firePlayers) ? "Water" : "Fire";
    	}
    }

	private void countPieces(){
		waterPlayers=0;
		firePlayers=0;
		for (int i=0; i<N; i++){
    		for(int j=0;j<N; j++){
    			if (pieces[i][j]!=null && pieces[i][j].isFire()){
    				firePlayers+=1;
    			} else if (pieces[i][j]!=null){
    				waterPlayers+=1;
    			}
    		}
    	}
	}

   

    public static void main(String[] args) {
        
        Board board = new Board(false);
        board.drawEmptyBoard();
     
        
        while(board.winner()==null) {
        	
        	int x=(int)StdDrawPlus.mouseX();
        	int y=(int)StdDrawPlus.mouseY();
        	if (StdDrawPlus.isSpacePressed() && board.canEndTurn()){
        		board.endTurn();
        	}
        	if (StdDrawPlus.mousePressed() && board.canSelect(x,y)){
        		board.select(x,y);
        	}
        	board.drawBoard();
            StdDrawPlus.show(100);
        }
    }
}
