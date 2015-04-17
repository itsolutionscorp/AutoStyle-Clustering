
// The GUI 

public class Board{
	// i borrowed all of this from StdDrawDemo.java
	private Piece[][] places;
	private int player=0;
	private Piece selected=null;
	private boolean moved=false;

    private void drawBoard() {
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (places[i][j] != null) {
                    if (places[i][j].isFire()){
                    	firePiece(places[i][j], i, j);
                    }
                    else {
                    	waterPiece(places[i][j],i, j);
                    }
                }
            }
        }
    }	

    //these be helper functions
    private void insertInitialPawn(){
    	for (int i=0; i<8; i++){
    		if (i%2 == 0){
    			places[i][0]= new Piece(true, this, i, 0, "pawn" );
    		}
    		if ((7+i)%2==0){
    			places[i][7]= new Piece(false, this, i, 7, "pawn");
    		}
    	}
    }

    private void insertInitialShield(){
    	for (int i=0; i<8; i++){
    		if ((1+i)%2 == 0){
    			places[i][1]= new Piece(true, this, i, 1, "shield" );
    		}
    		if ((6+i)%2==0){
    			places[i][6]= new Piece(false, this, i, 6, "shield");
    		}
    	}
    }

    private void insertInitialBomb(){
    	for (int i=0; i<8; i++){
    		if ((2+i)%2 == 0){
    			places[i][2]= new Piece(true, this, i, 2, "bomb" );
    		}
    		if ((5+i)%2==0){
    			places[i][5]= new Piece(false, this, i, 5, "bomb");
    		}
    	}
    }

    private void firePiece(Piece p, int i, int j){
    	if (p.isBomb()){
    		if (p.isKing()){
    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);}
    		else {StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);}
    	} else if (p.isShield()){
    		if (p.isKing()){
    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);}
    		else {StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);} 
    	} else if (p.isKing()){
    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);}
    		else {StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);}
    }

    private void waterPiece(Piece p, int i, int j){
    	if (p.isBomb()){
    		if (p.isKing()){
    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);}
    		else {StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);}
    	} else if (p.isShield()){
    		if (p.isKing()){
    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);}
    		else {StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);} 
    	} else if (p.isKing()){
    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);}
    		else {StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);}
    }


    // puts in initial 
    private void initialSetup(){
    	insertInitialBomb();
    	insertInitialShield();
    	insertInitialPawn();
    }


	// Starts GUI supported version of Board
	public static void main(String[] args){
		// also borrowed this from StdDrawDemo.java
		Board b=new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        String winner=null;

        while(true){
        	b.drawBoard();
        	if(StdDrawPlus.mousePressed()){
        		double x=StdDrawPlus.mouseX();
        		double y=StdDrawPlus.mouseY();
        		if(b.canSelect((int)x,(int)y)){
        			b.select((int)x,(int)y);
        		}
        	
        	}
        	if(StdDrawPlus.isSpacePressed()){
        		if(b.canEndTurn()){
        			winner=b.winner();
        			b.endTurn();
        		}
        	}
        }

	}
	// Constructor
	public Board(boolean shouldbeEmpty){
		if (shouldbeEmpty){
			places= new Piece[8][8];
		}else{
			places= new Piece[8][8];
			initialSetup();
		}
	}
	// Get Piece
	public Piece pieceAt(int x, int y){
		if (x>=places.length || y>=places[0].length){
			return null;
		}
		return places[x][y];
	}	
	// can Select?
	public boolean canSelect(int x, int y){
		Piece p=places[x][y];
		if (p==null){
			if (selected!=null && validMove(selected.x, selected.y, x, y) ){
				if (moved==false){return true;}
				if(selected.captured==true){return true;}
			}
			return false;
		}
		if(moved==true){
			return false;
		}
		if(p.side()==player){
			if (selected!=null || moved==false){return true;}
			
		}
		return false;
	}

	//more helper functions
	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece p= selected;
		if (p.isKing()){
			return kingMove(xi, yi, xf, yf);
		}
		if (p.isFire()){
			return fireMove(xi, yi, xf, yf);
		}
		else{return waterMove(xi, yi, xf, yf);}
	}

	private boolean fireMove(int xi, int yi, int xf, int yf){
		if(xf==(xi+1) || (xf==(xi-1))){
			if (yf==(yi+1)){
				return true;
			}
			return false;
		}
		if (xf==(xi+2) && yf==(yi+2)) {return (places[xi+1][yi+1]!=null);}
		if (xf==(xi-2) && yf==(yi+2)){return (places[xi-1][yi+1]!=null);}
		return false;
	}

	private boolean waterMove(int xi, int yi, int xf, int yf){
		if (xf==(xi+1) || xf==(xi-1)){
			if (yf==(yi-1)){
				return true;
			}
			return false;
		}
		if (xf==(xi+2) && yf==(yi-2)) {return (places[xi+1][yi-1]!=null);}
		if (xf==(xi-2) && yf==(yi-2)){return (places[xi-1][yi-1]!=null);}
		return false;
	}

	private boolean kingMove(int xi, int yi, int xf, int yf){
		return (fireMove(xi, yi, xf, yf) || waterMove(xi, yi, xf, yf));
	}


	// highlights square of the piece you clicked?
	public void select (int x, int y){

		if (places[x][y]!=null){ //if its a piece than it just draws it
			selected=places[x][y];
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			if(selected.side()==0){
				firePiece(selected, x, y);
				}
			else{
				waterPiece(selected, x, y);}
			return;
		} 
		selected.move(x,y);
		moved=true;
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}
	// puts piece in a place
	public void place(Piece p, int x, int y){
		if (x<places.length && y<places[0].length && p!=null){
			for (int i=0; i<places.length; i++){ 
				for (int j=0; j<places[0].length; j++){
					if (places[i][j]!= null && places[i][j].equals(p)){
						places[i][j]=null;
					}
				}
			} 
			places[x][y]=p;
			p.x=x;
			p.y=y;

			
		}
	}
	// take away a piece and returns it or mesage

	public Piece remove(int x, int y) {
		if (x>=places.length || y>=places[0].length){
			System.out.println("This is not a valid space on the Board.");
			return null;
		}
		if (places[x][y]!=null){
			Piece p=places[x][y];
			places[x][y]=null;
			return p;
		}
		System.out.println("There is no piece here.");
		return null;
	}
	//end turn?
	public boolean canEndTurn(){
		return (moved==true);
	}
	//ends turn
	public void endTurn(){
		if (player==0){player=1;}
		else{player=0;}
		if (selected!=null){
			selected.doneCapturing();}
		selected=null;
		moved=false;
	}
	// crowns the championn with legitimacy in the form of a String
	public String winner(){
		int water=0;
		int fire=0;
		for (int i=0; i<places.length; i++){
			for (int j=0; j<places[0].length; j++){
				Piece p=places[i][j];
				if(p!=null){
					if(p.isFire()){fire++;}
					else{water++;}
				}
			}
		}
		if(water==fire){return null;}
		if (water==0 && fire==0){return "No one";}
		if(water==0){return "Fire";}
		if (fire==0){return "Water";}
		return null;

	}

}