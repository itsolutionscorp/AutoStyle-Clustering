/**
 * @(#)Board.java
 *
 *
 * @author 
 * @version 1.00 2015/2/9
 */

public class Board {
    private Piece[][] board;
    private boolean isFireTurn;
    
    private boolean movedPiece;
    
    private Piece prevPiece;
    private int prevx,prevy;
    private Piece curPiece;
    private int curx,cury;  
    /**
     * Creates a new instance of <code>Board</code>.
     */
    public Board(boolean shouldBeEmpty) {
    	if(shouldBeEmpty){
    		board=new Piece[8][8];
    	}
    	else{
    		board=new Piece[8][8];
    		for (int i=0;i<4;i++){
    			board[2*i+1][7]=new Piece(false,this,2*i+1,7,"pawn");
    			board[2*i][6]=new Piece(false,this,2*i,6,"shield");
    			board[2*i+1][5]=new Piece(false,this,2*i+1,5,"bomb");
    			board[2*i][2]=new Piece(true,this,2*i,2,"bomb");
    			board[2*i+1][1]=new Piece(true,this,2*i+1,1,"shield");
    			board[2*i][0]=new Piece(true,this,2*i,0,"pawn");
    		}
    	}
    	movedPiece=false;
    	curPiece=null;
    	isFireTurn=true;
    }
    
    public Piece pieceAt(int x, int y){
    	if (!(x<0 || x>7 || y<0 || y>7)){
    		return board[x][y];
    	}
    	return null;
    }
    
    public boolean canSelect(int x, int y){
    	//First check if x,y lie on the board
    	//then check is x,y lie on a grey tile(as no piece can move to a red tile)
    	//then check other conditions
    	if(x>7 || x<0 ||y>7 || y<0){
    		return false;
    	}
    	else if((x+y)%2==1){
    		return false;
    	}
    	if (board[x][y]!=null){//if the selected square is not empty
    		if (movedPiece==false && isFireTurn==board[x][y].isFire()){
    			//if a piece hasnt been moved yet and the team matches
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    	else{//if square selected is empty
    		if (curPiece!=null){//if already selected a piece
    			if (((y-cury>0) == isFireTurn) ||curPiece.isKing()){
    				//if the direction selected and the 'team' of the piece match
    				if (((x-curx)==1 || (x-curx)==-1)&&curPiece.hasCaptured()==false){//if the move is within 1
    					if (movedPiece==false){//if a piece has not been moved yet
    						return true;
    					}
    					return false;
    				}
    				if (((x-curx)==2 || (x-curx)==-2) && Math.abs(y-cury)==2 && board[(x+curx)/2][(y+cury)/2]!=null){//if the move is a capture
    					if (curPiece.isFire() != board[(x+curx)/2][(y+cury)/2].isFire()){
    						//and if the piece in between is capturable
    						return true;
    					}
    					return false;
    				}
    				return false;
    			}
    			return false;
    		}
    		else if (prevPiece!=null){
    			if ((((y-prevy>0) == isFireTurn) ||prevPiece.isKing())&& prevPiece.hasCaptured()==true){
    				if (((x-prevx)==2 || (x-prevx)==-2) && Math.abs(y-prevy)==2 && board[(x+prevx)/2][(y+prevy)/2]!=null){//if the move is a capture
    					if (prevPiece.isFire() != board[(x+prevx)/2][(y+prevy)/2].isFire()){
    						//and if the piece in between is capturable
    						return true;
    					}
    					return false;
    				}
    				return false;
    			}
    			return false;
    		}
    		return false;
    	}
    }

    public void select(int x, int y){
    	if (board[x][y]==null){
   			movedPiece=true;
   			if (curPiece!=null){
   				curPiece.move(x,y);
    			if (board[x][y]==curPiece){
   					prevPiece=curPiece;
   					prevx=x;
    				prevy=y;
    			}
    			curPiece=null;
    		}
    		else{
    			prevPiece.move(x,y);
    			prevx=x;
    			prevy=y;
    		}
    		
    	}
    	else{
    		curPiece=board[x][y];
    		curx=x;
    		cury=y;
    	}
    }
    
    public void place(Piece p, int x, int y){
    	if (!(x<0 || x>7 || y<0 || y>7 || p==null)){
    		board[x][y]=p;
    	}
    }
    
    public Piece remove(int x, int y){
    	if (x<0 || x>7 || y<0 || y>7){
    		System.out.println(x+", "+y+" is out of bounds");
    		return null;
    	}
    	else{
    		Piece tmp=board[x][y];
    		board[x][y]=null;
    		if(tmp==null){
    			System.out.println("There is no piece at "+x+", "+y);
    		}
    		return tmp;
    	}
    }
    
    
    public boolean canEndTurn(){
    	if (movedPiece==true){
    		return true;
    	}
    	return false;
    }
    
    public void endTurn(){
    	isFireTurn=!isFireTurn;
    	movedPiece=false;
    	if (prevPiece!=null){
    		prevPiece.doneCapturing();
    	}
    	curPiece=null;
    }
    
    public String winner(){
    	int isFire=0;
    	int isWater=0;
    	for (int i=0; i<8;i++){
    		for (int j=0; j<8; j++){
    			if(board[i][j] != null){
    				if(board[i][j].isFire()){
    					isFire++;
    				}
    				else{
    					isWater++;
    				}
    			}
    		}
    	}
    	if (isFire>isWater){
    		return "Fire";
    	}
    	else if (isFire<isWater){
    		return "Water";
    	}
    	else if(isFire!=0){
    		return null;
    	}
    	else{
    		return "No One";
    	}
    }
    
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (curPiece!=null){
                	if(i==curx && j==cury){
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (board[i][j]!=null) {
                	Piece tmp=board[i][j];
                	String kingname=".png";
                	String side="";
                	if(tmp.isKing()){
                		kingname="-crowned.png";
                	}
                	if (tmp.isFire()){
                		side="-fire";
                	}
                	else{
                		side="-water";
                	}
                	if (tmp.isKing()){
                		if(tmp.isBomb()){
                			StdDrawPlus.picture(i+.5, j+.5,"img/bomb"+side+"-crowned.png", 1, 1);
                		}
                		else if(tmp.isShield()){
                			StdDrawPlus.picture(i+.5, j+.5,"img/shield"+side+"-crowned.png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i+.5, j+.5,"img/pawn"+side+"-crowned.png", 1, 1);
                		}
                	}
                	else{
                		if(tmp.isBomb()){
                			StdDrawPlus.picture(i+.5, j+.5,"img/bomb"+side+".png", 1, 1);
                		}
                		else if(tmp.isShield()){
                			StdDrawPlus.picture(i+.5, j+.5,"img/shield"+side+".png", 1, 1);
                		}
                		else{
                			StdDrawPlus.picture(i+.5, j+.5,"img/pawn"+side+".png", 1, 1);
                		}
                	}                    
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board GameBoard=new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            GameBoard.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (GameBoard.canSelect((int)Math.round(x-.5),(int)Math.round(y-.5))){
                	GameBoard.select((int)Math.round(x-.5),(int)Math.round(y-.5));
                }
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (GameBoard.canEndTurn()){
            		GameBoard.endTurn();
            	}
            }            
            StdDrawPlus.show(50);
        }
    }
}
