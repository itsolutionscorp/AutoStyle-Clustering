public class Board{
	private Piece[][] pieces= new Piece[8][8];
	private Piece removed_piece;
	private int turn=0;
	private boolean selected=false;
	private boolean moved=false;
	private Piece before_m;
	private Piece after_m;
	private int selected_x;
	private int selected_y;
	private int destination_x;
	private int destination_y;
	


	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b= new Board(false);
        b.drawBoard();
        b.drawPieces();
        

    }


    public Board(boolean shouldBeEmpty){
    	if(!shouldBeEmpty){
    		for (int i = 0; i < 8; i+=2){
    			pieces[i][0]= new Piece(true, this, i, 0, "pawn");
    			pieces[i][2]= new Piece(true, this, i, 2, "bomb");
    			pieces[i][6]= new Piece(false, this, i, 6, "shield");
    		}
    		for (int i = 1; i < 8; i+=2){
    			pieces[i][1]= new Piece(true, this, i, 0, "shield");
    			pieces[i][5]= new Piece(false, this, i, 5, "bomb");
    			pieces[i][7]= new Piece(false, this, i, 7, "pawn"); 
    		}

    	}
    }

	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private void drawPieces(){
    	for (int i=0; i<8; i++){
    		for (int j = 0; j < 8; j++){
    			if (pieces[i][j]!=null){	
    				StdDrawPlus.picture(i + .5, j + .5, getName(pieces[i][j]), 1, 1);
    			}
    		}
        }
    }

    private String getName(Piece a){
    	String testfire;
    	String testking;
    	String type;
    	if(a.isFire()) testfire= "-fire";
    	else           testfire= "-water";
    	if(a.isKing()) testking="-crowned";
    	else           testking="";
    	if(a.isBomb()){
    		type= "bomb";
    	}
    	else if(a.isShield()){
    		type= "shield";
    	}
    	else{
    		type="pawn";
    	}
    	return "img/"+type+testfire+testking+".png";			
    }

    
    public Piece pieceAt(int x, int y){
        if (x>8 || y >8 || x <0 || y<0 ){
            return null;
        }
    	return pieces[x][y];
    }

    public void place(Piece p, int x, int y){
    	if (!((x>7)||(y>7)||(p==null))){
    		pieces[x][y]=p;
    	}
    }

    public Piece remove(int x, int y){
    	if ((x>7)||(y>7)){
    		System.out.println("Error: input out of bounds");
    		return null;
    	}
    	else{
    		removed_piece=pieces[x][y];
    		pieces[x][y]=null;
    		return removed_piece;
    	}
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
    	before_m=pieceAt(xi,yi);
    	after_m=pieceAt(xf,yf);
    	if (before_m.isKing()){
    		if(Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1 && after_m==null){
    			return true;
    		}
    		else if(Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2 && after_m==null && pieceAt((xi+xf)/2,(yi+yf)/2)!=null){
    			return true;
    		}
    	}

    	else if(before_m.isFire()){
    		if(Math.abs(xi-xf)==1 && (yf-yi)==1 && after_m==null){
    			return true;
    	    }
    	    else if(Math.abs(xi-xf)==2 && (yf-yi)==2 && after_m==null && pieceAt((xi+xf)/2,(yi+yf)/2)!=null){
    	    	return true;
    	    }
    	}

    	else{
    		if(Math.abs(xi-xf)==1 && (yi-yf)==1 && after_m==null){
    			return true;
    	    }
    	    else if(Math.abs(xi-xf)==2 && (yi-yf)==2 && after_m==null && pieceAt((xi+xf)/2,(yi+yf)/2)!=null){
    	    	return true;
    	    }
    	}
    	return false;
    }

    public boolean canSelect(int x, int y){
    	
    	if(pieceAt(x,y)!=null){
    		if(turn==0){
    			if (pieceAt(x,y).isFire()){
                    if((!selected)||(!moved)){
                    	return true;
                    }
    			}
    		}
    		else if(turn==1){
    			if (!pieceAt(x,y).isFire()){
    				if ((!selected)||(!moved)){
    					return true;
    				}
    			}
    		}
    		return false;
    	}


         /*the case for no piece at that place*/
    	else{
    		if(selected && !moved && validMove(selected_x,selected_y,x,y)){
    			return true;
    		}
    		else if(pieceAt(destination_x,destination_y).hasCaptured() && validMove(destination_x,destination_y,x,y)){
    			return true;
    		}
    		return false;
    	}
    }




    public void select(int x, int y){
    	
    	if (pieceAt(x,y)!=null){
    		selected_x=x;
    		selected_y=y;
    		selected=true;
    	}
    	else{
    		pieceAt(selected_x,selected_y).move(x,y);
    		destination_x=x;
    		destination_y=y;
    		selected_x=x;
    		selected_y=y;
    		selected=true;
    		moved=true;
    	}
    }

    public boolean canEndTurn(){
        if(moved){
            return true;
        }
        else if(pieceAt(destination_x,destination_y)!=null && pieceAt(destination_x,destination_y).hasCaptured()){
            return true;
        }
        else{
            return false;
        }

    }

    public void endTurn(){
    	/*switch player*/
    	if (turn==0){
    		turn=1;
    	}
    	else if(turn==1){
    		turn=0;
    	}
    	/*update all info*/
    	selected=false;
    	moved=false;
    	pieceAt(destination_x,destination_y).doneCapturing();
    	destination_x=0;
    	destination_y=0;


    }

    public String winner(){
        int water_remain=0;
        int fire_remain=0;
    	for (int i=0; i<8; i++){
    		for (int j = 0; j < 8; j++){
    			if (pieceAt(i,j)!=null){
    				if (pieceAt(i,j).isFire()){
    					fire_remain+=1;
    				}
    				else{
    					water_remain+=1;
    				}
    			}
    		}
    	}

    	if (fire_remain==0 && water_remain==0){
    		return "No one";
    	}
    	else if(fire_remain==0){
    		return "Water";
    	}
    	else if(water_remain==0){
    		return "Fire";
    	}
    	else{
    		return null;
    	}

    }







}