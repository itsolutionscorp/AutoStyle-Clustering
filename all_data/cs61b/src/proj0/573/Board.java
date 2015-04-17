public class Board{
	private boolean playerFire = true;
	private boolean playerWater = false;
	private Piece selectedpiece = null;
	private int initiallocofpiecex = 0;
	private int initiallocofpiecey = 0;
	private boolean hasmoved=false;
    private String typestring;
    private String crownstring="";
    private String isFstring;
	private static boolean firstboard =true;
	private Piece[][] pieces;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    
    private void drawBoard(int N) {

         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isKing()){
                            crownstring="-crowned";
                        }
                    if (pieces[i][j].isKing()==false){
                            crownstring="";
                        }
                    if (pieces[i][j].isShield()){
                        typestring = "shield";
                    }
                    else if(pieces[i][j].isBomb()){
                        typestring= "bomb";
                    }
                    else{
                        typestring="pawn";
                    }   	
                	if (pieces[i][j].isFire()){
                		isFstring = "-fire";    
                	}
                	else{
                		isFstring = "-water";
                	}
                    StdDrawPlus.picture(i + .5, j + .5, "img/"+ typestring+ isFstring +crownstring+".png", 1, 1);
                }

            }
        }
      }
private void drawEmptyBoard(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
  }
 }
}
 	

public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
 		if(shouldBeEmpty == true){
 			return;
 		}
 		else{
 			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++){
            		if (j==0 && i%2==0){
            			pieces[i][j]= new Piece(true,this,i,j,"pawn");
            		}
            		else if (j==1 && i%2!=0) {
            			pieces[i][j]= new Piece(true,this,i,j,"shield");
            		}
            		else if (j==2 && i%2==0) {
            			pieces[i][j]= new Piece(true,this,i,j,"bomb");
            		}
            		else if (j==5 && i%2!=0 ) {
            			pieces[i][j]= new Piece(false,this,i,j,"bomb");
            		}
            		else if (j==6 && i%2==0) {
            			pieces[i][j]= new Piece(false,this,i,j,"shield");
            		}
            		else if (j==7 && i%2!=0) {
            			pieces[i][j]= new Piece(false,this,i,j,"pawn");
            		}

            		
            	}

 		}
 	}
 }

 	public Piece pieceAt(int x, int y){

 		if (x>7 || y>7 || x<0 || y<0){
 			return null;
 		}
 		else if (pieces[x][y]== null){
 			return null;
 		}
 		else{
 			return pieces[x][y];
 		}

 	}

 	public void place(Piece p, int x, int y){
 		if (x>7 || y>7 || x<0 || y<0){
 			return;
 		}
 		else if (p == null){
 			return;
 		}
 		else{
 			pieces[x][y]=p;

 		}
 	}
 	

 	public Piece remove(int x, int y){
 		if (x>7 || y>7){
 			System.out.println("Input is out of bounds!");
 			return null;
 		}
 		else if(pieces[x][y]==null){
 			System.out.println("There is no piece at (x,y)!");
 			return null;
 		}
 		else{
 			Piece removedpiece = pieces[x][y];
 			pieces[x][y]=null;
 			return removedpiece;
 		}

 	}
 	private boolean validMove(int xi, int yi, int xf, int yf){
 		if (pieces[xi][yi] != null){
 			Piece movingpiece = pieces[xi][yi];
 			int distancex=xf-xi;
 			int distancey=yf-yi;
            int averagex = (xi+xf)/2;
            int averagey = (yi+yf)/2;
 			if (movingpiece.isFire()){
 				if (movingpiece.isKing()==false && (distancey==1 && (distancex==1 || distancex==-1))){
 					return true;
 				}
 				else if (movingpiece.isKing()==false && (distancey==2 && (distancex==2 || distancex==-2)) && pieces[averagex][averagey].isFire()==false) {
 					movingpiece = pieces[xf][yf];
 					return true;
 				}
 				else if (movingpiece.isKing() && (distancey==1 ||distancey==-1) && (distancex==1 || distancex==-1) ) {
 					return true;
 				}
 				else if (movingpiece.isKing() && (distancey==2 ||distancey==-2) && (distancex==2 || distancex==-2) && pieces[averagex][averagey].isFire()==false) {
 					movingpiece = pieces[xf][yf];
 					return true;
 				}
 			}

 			else if (movingpiece.isFire()==false) {
 				if (movingpiece.isKing()==false && (distancey==-1 && (distancex==1 || distancex==-1))){
 					return true;
 				}
 				else if (movingpiece.isKing()==false && (distancey==-2 && (distancex==2 || distancex==-2)) && pieces[(xi+xf)/2][(yi+yf)/2].isFire()) {
 					movingpiece = pieces[xf][yf];
 					return true;
 				}
 				else if (movingpiece.isKing() && (distancey==1 ||distancey==-1) && (distancex==1 || distancex==-1) ) {
 					return true;
 				}
 				else if (movingpiece.isKing() && (distancey==2 ||distancey==-2) && (distancex==2 || distancex==-2) && pieces[(xi+xf)/2][(yi+yf)/2].isFire()) {
 					movingpiece = pieces[xf][yf];
 					return true;
 				}

 				
 			}
 		}
 		return false;
}

 	public boolean canSelect(int x, int y){
 	
 		if (pieces[x][y] !=null){
 			if ((pieces[x][y].isFire() && playerFire==true) || (pieces[x][y].isFire()==false && playerWater==true)) {
 				if (selectedpiece ==null){
 					return true;
 				}
 				else if (hasmoved==false){
 					return true;
 				}
 				else{
 					return false;
 				}
 			  }
 			}
 		
 		else if (pieces[x][y] ==null && selectedpiece !=null) {
 			if ((selectedpiece.isFire() && playerFire==true) || (selectedpiece.isFire()==false && playerWater==true)) {
 				if (hasmoved ==false && validMove(initiallocofpiecex,initiallocofpiecey,x,y)){
 					return true;
 				}
 				else if (selectedpiece.hasCaptured() && validMove(initiallocofpiecex,initiallocofpiecey,x,y) && Math.abs(initiallocofpiecex-x)==2 && Math.abs(initiallocofpiecey-y)==2){
 					return true;
 				}
 				else{
 					return false;
 				}
 			}
 			else{
 				return false;
 			}
 		  
 		}
 		
 			return false;
 		
 	}
 



 	public void select(int x, int y){
 		if 	(pieces[x][y] != null){
 			selectedpiece = pieces[x][y];
 			initiallocofpiecex = x;
 			initiallocofpiecey = y;
 		}
 		else{
            initiallocofpiecex = x;
            initiallocofpiecey = y;
 			selectedpiece.move(x,y);
 			hasmoved=true;
 		}
 		
 	}

 	public boolean canEndTurn(){
 		if (selectedpiece !=null &&(selectedpiece.hasCaptured() || (hasmoved==true))) {
 			return true;
 	}
 		return false;
 	}

 	public void endTurn(){
        selectedpiece.doneCapturing();
 		selectedpiece=null;
 		hasmoved=false;
 		initiallocofpiecex = 0;
	    initiallocofpiecey = 0;
 		if (playerFire==true){
 			playerWater=true;
 			playerFire=false;
 		}
 		else{
 			playerWater=false;
 			playerFire=true;
 		}
    
    
 	  
 	}

 	public String winner(){
 		int sumFire=0;
 		int sumWater=0;
 		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++){
          		if (pieces[i][j]!=null && pieces[i][j].isFire()){
          			sumFire +=1;
          		}
          		else if(pieces[i][j]!=null && pieces[i][j].isFire()==false){
          			sumWater +=1;
          		}
            }
        }
        if (sumFire !=0 && sumWater==0){
        	return "Fire";
        }
        else if(sumWater != 0 && sumFire==0){
        	return "Water";
        }
        else if(sumWater==0 && sumFire==0){
        	return "No one";
        }
        else{
            return null;
        }

 	}
   

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	board.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect ((int) x, (int) y)) {
                	board.select((int) x, (int) y);
                }
            }
            else if (StdDrawPlus.isSpacePressed()){
                if(board.canEndTurn()){
                    board.endTurn();
                }
            }
         
            StdDrawPlus.show(100);            
            
        }

        
    }

}