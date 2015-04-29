

public class Board {
	
	private boolean canEndTurn=false;
	private boolean fireturn =true ;
	private boolean selected=false,hasMoved=false;
	private int selectedx=-1,selectedy=-1;
	
	private Piece [][] pieces = new Piece [8][8];
		
	public Board (boolean shouldBeEmpty){
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
		if (!shouldBeEmpty){
			for (int i = 0; i<N;i+=2){
				int j = 0;
				pieces[i][j]= new Piece (true,this,i,j,"pawn");
				j = 2;
				pieces[i][j]= new Piece (true,this,i,j,"bomb");
				j = 6;
				pieces[i][j]= new Piece (false,this,i,j,"shield");			
			}
			for (int i = 1; i<N;i+=2){
				int j = 1;
				pieces[i][j]= new Piece (true,this,i,j,"shield");
				j = 5;
				pieces[i][j]= new Piece (false,this,i,j,"bomb");
				j = 7;
				pieces[i][j]= new Piece (false,this,i,j,"pawn");		
			}
			for (int i = 0; i<N;i++){
				for (int j = 0;j<N;j++){
					drawPiece (i,j);
				}
			}
		}
	}
	
	public Piece pieceAt (int x, int y){
		if ((x<0)||(x>7)||(y<0)||(y>7)){
			return null;
		}
		return pieces [x][y];
	}
	
	public boolean canSelect (int x, int y){
		if (!selected){
			if (pieceAt (x,y)== null){
				return false;
			}
			if (fireturn){
				Piece selectedpiece = pieceAt (x, y);
				if(selectedpiece.isFire()) return true;
				else {
					//System.out.println("incorrect select");
					return false;
				}
			}
			else{
				Piece selectedpiece = pieceAt (x, y);
				if(!selectedpiece.isFire()) return true;
				else {
					//System.out.println("incorrect select");
					return false;
				}
			}
		}
		else {
			
			if (pieceAt (x,y)!= null){
				if (!hasMoved){
					if (fireturn){
						Piece selectedpiece = pieceAt (x, y);
						if(selectedpiece.isFire()) return true;
						else {
							//System.out.println("incorrect reselect");
							return false;
						}
					}
					else{
						Piece selectedpiece = pieceAt (x, y);
						if(!selectedpiece.isFire()) return true;
						else {
							//System.out.println("incorrect reselect");
							return false;
						}
					}
				}
				return false;
			}
			else {
				Piece selectedpiece = pieceAt (selectedx, selectedy);
				if (selectedpiece== null){
					//System.out.println("bomb exploded");
					return false;
				}
				if(!selectedpiece.isKing()){
					if (selectedpiece.isFire()&& y<selectedy){
						//System.out.println("cant move back");
						return false;
					}
					if (!selectedpiece.isFire()&& y>selectedy){
						//System.out.println("cant move back");
						return false;
					}
				}
				if ( (Math.abs(x-selectedx)==1)&&(Math.abs(y-selectedy)==1)){

					
					if (hasMoved) {
						//System.out.println("cant move 1 step after capturing");
						return false;
					}
					else {
						return true;
					}
				}
				else if ((Math.abs(x-selectedx)==2)&&(Math.abs(y-selectedy)==2)){
					if (selectedpiece.hasCaptured()== false && hasMoved==true ){
						//System.out.println("cant capture afer moving");
						return false;
					}
					int midx = (selectedx+x)/2;
					int midy = (selectedy+y)/2;
					Piece mid = pieceAt (midx,midy);
					if (mid == null) {
						return false;
						}
					if (selectedpiece.side()!=mid.side()){
						return true;
					}
				}
				
			}
		}
		//System.out.println("all else");
		return false;
	}
	
	public void select (int x, int y){
        if ((!selected)||(pieceAt(x,y)!=null)){
        	selectedx =x;
        	selectedy =y;
            selected = true;
            return;
        }
        else{
        	        	
        	Piece selectedpiece = pieceAt (selectedx, selectedy);
        	
        	selectedpiece.move (x,y);
        	
        	selectedx =x;
            selectedy =y;
           
            
            canEndTurn= true;
            hasMoved = true;
            return;            
        }
        
	}
	
	private void drawPiece (int x, int y){
		Piece p = pieceAt(x,y);
		if (p == null){
			if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			return;
		}
		if (p.isFire()){
			if (p.isShield()) {
				if (!p.isKing()) StdDrawPlus.picture(x + .5,  y + .5, "img/shield-fire.png", 1, 1);
				else StdDrawPlus.picture(x + .5,  y + .5, "img/shield-fire-crowned.png", 1, 1);
			}
			
			else if (p.isBomb()){
				if (!p.isKing()) StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-fire.png", 1, 1);
				else StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-fire-crowned.png", 1, 1);
			}
			else{
				if (!p.isKing()) StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-fire.png", 1, 1);
				else StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-fire-crowned.png", 1, 1);
			}
		}
		else{
			if (p.isBomb()){
				if (!p.isKing()) StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-water.png", 1, 1);
				else StdDrawPlus.picture(x + .5,  y + .5, "img/bomb-water-crowned.png", 1, 1);
			}
			else if (p.isShield()) {
				if (!p.isKing()) StdDrawPlus.picture(x + .5,y + .5, "img/shield-water.png", 1, 1);
				else StdDrawPlus.picture(x + .5,  y + .5, "img/shield-water-crowned.png", 1, 1);
			}
			else{
				if (!p.isKing()) StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-water.png", 1, 1);
				else StdDrawPlus.picture(x + .5,  y + .5, "img/pawn-water-crowned.png", 1, 1);
			}
		}
	}
	
	
	
	
	public void place (Piece p, int x, int y){
		if ((x<0)||(x>7)||(y<0)||(y>7)||p==null){
			return;
		}
		else{
			pieces[x][y]=p;
		}
	}
	
	
	
	public Piece remove (int x, int y){
		Piece removal = pieces [x][y];
		pieces[x][y]= null;
		return removal;
	}
	
	public boolean canEndTurn(){
		return canEndTurn;
	}
	
	public void endTurn(){
		if (canEndTurn()){
			selected = false;
			canEndTurn = false;
			hasMoved = false;
			Piece selectedpiece = pieceAt(selectedx ,selectedy);
			if (selectedpiece!=null){
				selectedpiece.doneCapturing();
			}
			if (fireturn) fireturn=false; 
			else fireturn =true;
		}
	}
	
	public String winner(){
		int N=8;
		int numFire=0;
		int numWater=0;
		for (int i = 0; i<N;i++){
			for (int j = 0;j<N;j++){
				Piece p = pieceAt (i,j);
				if (p!= null){
					if (p.isFire()){
						numFire++;
					}
					else{
						numWater++;
					}
				}
			}
		}
		if (numFire ==0 &&numWater==0){
			return "No one";
		}
		else if (numFire==0){
			return "Water";
		}
		else if (numWater==0){
			return "Fire";
		}
		else{
			return null;
		}
	}
	public static void main (String [] args){
		Board board = new Board(false);
		int N=8;
		while(true){
			int oldx = board.selectedx;
            int oldy = board.selectedy;
			if (StdDrawPlus.mousePressed()) {
                double mx = StdDrawPlus.mouseX();
                double my = StdDrawPlus.mouseY();
                int x = (int)mx;
                int y = (int)my;
                //System.out.println(board.canSelect(x,y));
                if (board.canSelect(x, y)){
                	board.select(x,y);
                
	                if (oldx !=-1){
	                	if ((oldx+ oldy) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	        			StdDrawPlus.filledSquare(oldx + .5, oldy + .5, .5);
	                }
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	                for (int i = 0; i<N;i++){
	    				for (int j = 0;j<N;j++){
	    					board.drawPiece (i,j);
	    				}
	    			}
	                
                }
                
			}
			if (StdDrawPlus.isSpacePressed()){
				board.endTurn();
				if (oldx !=-1){
                	if ((oldx+ oldy) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else StdDrawPlus.setPenColor(StdDrawPlus.RED);
        			StdDrawPlus.filledSquare(oldx + .5, oldy + .5, .5);
                	board.drawPiece(oldx,oldy);
                }
			}
			
			if (StdDrawPlus.isNPressed()){
				board = new Board(false);
				board.canEndTurn=false;
				board.fireturn =true ;
				board.selected=false;
				board.hasMoved=false;
				board.selectedx=-1;
				board.selectedy=-1;
			}
			
			StdDrawPlus.show(25);
		}
	}
}
