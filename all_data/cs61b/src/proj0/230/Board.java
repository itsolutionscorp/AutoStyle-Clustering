public class Board{

	private Piece[][] boardp;
	private static final int N=8;
	private int playturn;
    private Piece piecetomove;
    private boolean hasMoved;
    private double x, y;
    private boolean highlight;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(highlight){
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare((int)x + .5, (int)y + .5, .5);
            }
                drawingPieces(i,j);
                }
    	}
	}
	private void drawingPieces(int i, int j){
		if (boardp[i][j]!=null) {
	        /*StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);*/
	        Piece player= boardp[i][j];
	        if(player==null){
	        	return;
	        }else{
	        	if(player.isFire()){
	      			if(player.isShield()){
	        			
	        			if(player.isKing()){
	        			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	        			}else{
	        			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	        			}
	        		}else{
	        			if(player.isBomb()){
		    			if(player.isKing()){
		    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		    			}else{
		    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		    			}
	      			}else{
	      			if(player.isKing()){
	        			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	    			}else{
	        		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	        	}}

	    		}}else{
		    		if(player.isShield()){
		        		if(player.isKing()){
		        			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		        		}else{
		      		        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);

		        		}
		        	}else{
		      			if(player.isBomb()){
		    				if(player.isKing()){
		    					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		    				}else{
		    					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);

		    				}
		      			}else{
		      				if(player.isKing()){
		        			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		    				}else{
		        			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	        }}
            }
    			}
    		}
    	}
	}
    public Board(boolean shouldBeEmpty){
        hasMoved=false;
    	playturn=0;
    	if(shouldBeEmpty){
    		boardp=new Piece[N][N];
    	}else{
            boardp=new Piece[N][N];
    		for(int i=0;i<8;i++){
    			for(int j=0;j<3;j++){
                    if((i+j)%2==0){
        				if(j==0){
        					boardp[i][j]=new Piece(true, this, i,j,"pawn"); 
        				}
        				if(j==1){
        					boardp[i][j]=new Piece(true, this, i,j, "shield");
        				}
        				if(j==2){
        					boardp[i][j]=new Piece(true, this, i,j,"bomb");
        				}
                    }
    			}
    		}
    		for(int i=0;i<8;i++){
    			for(int j=5;j<8;j++){
                    if((i+j)%2==0){
        				if(j==7){
        					boardp[i][j]=new Piece(false, this, i,j,"pawn"); 
        				}
        				if(j==6){
        					boardp[i][j]=new Piece(false, this, i,j, "shield");
        				}
        				if(j==5){
        					boardp[i][j]=new Piece(false, this, i,j,"bomb");
        				}
                    }
    			}
    		}
    	}
    	}

    public Piece pieceAt(int x, int y){
    	if(x>7 || y>7 ||x<0 || y<0){
    		return null;
    	}
    	if(boardp[x][y]!=null){
    		return boardp[x][y];
    	}else{
    		return null;
    	}
    }
    //helper for CanSelect
    private boolean normalFirePieceMove(int x, int y){
        if(piecetomove.hasCaptured()){
            if(y==this.piecepositiony()+2){
                if(this.canCapture(x,y)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            if(y==this.piecepositiony()+1){
                return true;
            }else{
                if(y==this.piecepositiony()+2){
                    if(this.canCapture(x,y)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
    }
    //helper for CanSelect
    private boolean multiCapture(int x, int y){
        if(piecetomove==null){
            return false;
        }
        if(piecetomove.isKing()){
            if(x==this.piecepositionx()+2||x==this.piecepositionx()-2){
                if(this.canCapture(x,y)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            if(piecetomove.isFire()){
                if(y==this.piecepositiony()+2){
                    if(this.canCapture(x,y)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                if(y==this.piecepositiony()-2){
                    if(this.canCapture(x,y)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }     
            }
        }
    }
    //helper for CanSelect
    private boolean normalWaterPieceMove(int x, int y){
        if(piecetomove.hasCaptured()){
            if(y==this.piecepositiony()-2){
                if(this.canCapture(x,y)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            if(y==this.piecepositiony()-1){
                return true;
            }else{
                if(y==this.piecepositiony()-2){
                    if(this.canCapture(x,y)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
    }
    //helper for CanSelect
    private boolean kingPieceMove(int x, int y){
        if(!piecetomove.hasCaptured()){
            if(x==this.piecepositionx()+1||x==this.piecepositionx()-1){
                return true;
            }else{
                if(x==this.piecepositionx()+2||x==this.piecepositionx()-2){
                    if(this.canCapture(x,y)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }else{
            if(x==this.piecepositionx()+2||x==this.piecepositionx()-2){
                if(this.canCapture(x,y)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }
    public  boolean canSelect(int x, int y){

        if((x+y)%2==0&&!hasMoved){
            if(pieceAt(x,y)==null){
                if(piecetomove==null){
                    return false;
                }else{

                    if(piecetomove.hasCaptured()){
                        return multiCapture(x,y);
                    }else{
                        if(hasMoved){
                            return false;
                        }else{
                            if(piecetomove.isKing()){
                                return kingPieceMove(x, y);
                            }else{
                                if(piecetomove.isFire()){
                                    return normalFirePieceMove(x,y);
                                }else{
                                    return normalWaterPieceMove(x,y);
                                }
                            }
                        }
                    }
                }
            }else{
                if(piecetomove!=null){
                    if(piecetomove.hasCaptured()){
                        return false;
                    }else{
                        if(pieceAt(x,y).side()==playturn){
                            return true;
                        }else{
                            return false; 
                        }
                    }
                }else{
                    if(pieceAt(x,y).side()==playturn){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }else{

            return false;
        }
    }

    //helper method
    private int piecepositionx(){
        int xpos=-1;
        int ypos=-1;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(piecetomove==pieceAt(i,j)){
                    xpos=i;
                    ypos=j;
                }
            }
        }
        return xpos;
    }
    private int piecepositiony(){
        int xpos=-1;
        int ypos=-1;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(piecetomove==boardp[i][j]){
                    xpos=i;
                    ypos=j;
                }
            }
        }
        return ypos;
    }
    //helper method for can select
    private boolean canCapture(int x, int y){
        Piece capturing= boardp[(int)((x+this.piecepositionx())/2)][(int)((y+this.piecepositiony())/2)];
        if(capturing==null){
            return false;
        }else{
            if(capturing.side()==piecetomove.side()){
                return false;
            }else{
                return true;
            }
        }
    }

    public void select(int x, int y){
        if(pieceAt(x,y)==null){
            
                place(piecetomove, x, y);
            
        }else{
            
                piecetomove=boardp[x][y];
            
        }
    }

    public boolean canEndTurn(){
        if (hasMoved){
            return true;
        }else{
            if(piecetomove==null){
                return false;
            }else{
                if(piecetomove.hasCaptured()){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    public void endTurn(){
        if(playturn==0){
            hasMoved=false;
            if(piecetomove!=null){
                piecetomove.doneCapturing();
            }
            piecetomove=null;
            playturn+=1;
        }else{
            hasMoved=false;
            if(piecetomove!=null){
                piecetomove.doneCapturing();
            }
            piecetomove.doneCapturing();
            piecetomove=null;
            playturn-=1;
        }
    }

    public void place(Piece p, int x, int y){
    	if( p==null|| x>7||y>7||x<0||y<0){
    		return;
    	}else{
            int xpos=-1;
            int ypos=-1;
            
    		for (int i=0;i<8;i++){
    			for(int j=0;j<8;j++){
    				if(pieceAt(i,j)== p){
                        xpos=i;
                        ypos=j;
    					p= this.remove(i,j);
    				}
    			}
    		}
            if(xpos==-1){
                p.move(x,y);
                boardp[x][y]=p;
            }else{
                if(x==xpos+1||x==xpos-1){
                    hasMoved=true;
                    p.move(x,y);
                    boardp[x][y]=p;
                }else{
                    if(p.isBomb()){
                        this.remove((int)(xpos+x)/2, (int)(ypos+y)/2);
                        p.move(x,y);
                        boardp[x][y]=p;
                        this.explosion(x,y);
                        this.remove(x,y);
                    }else{
                        this.remove((int)(xpos+x)/2, (int)(ypos+y)/2);
                        p.move(x,y);
                        boardp[x][y]=p; 
                        piecetomove=p;
                   }
                }
            }
    	}
    }

    //helpermethod for bomb
    private void explosion(int x, int y){
        for(int i=-1;i<2;i=i+2){
            Piece temp= pieceAt(x+i, y+i);
            Piece temp2=pieceAt(x+i, y-i);
            if(temp!=null){
                if(!temp.isShield()){
                    remove(x+i,y+i);
                }
            }
            if(temp2!=null){
                if(!temp2.isShield()){
                    remove(x+i,y-i); 
                }
            }
        }

    } 


    public Piece remove(int x, int y){
    	if(x>7||y>7||x<0||y<0){
    		System.out.println("Input out of bounds");
    		return null;
    	}
    	if(boardp[x][y]!=null){
    		Piece temp=pieceAt(x,y);
    		boardp[x][y]=null;
    		return temp;
    	}else{
    		System.out.println("No piece at given input");
    		return null;
    	}
    }
    public String winner(){
        int fire=0;
        int water=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece temp=boardp[i][j];
                if(temp!=null){
                    if(temp.side()==1){
                        water+=1;
                    }else{
                        if(temp.side()==0){
                            fire+=1;
                        }
                    }
                }
            }
        }
        if(fire==0&&water==0){
            return "No One";
        }else{
            if(fire==0){
                return "Water";
            }else{
                if(water==0){
                    return "Fire";
                }else{
                    return null;
                }
            }
        }
    }
    public static void main(String[] args) {
        Board b= new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        /*Board b= new Board(true);
        Piece pawn= new Piece(true, b, 0,0, "pawn");
        Piece bomb= new Piece(false, b, 1,1, "bomb");
        Piece shield= new Piece(false, b, 3,3, "shield");
        b.place(pawn,0,0);
        b.place(bomb,1,1);
        b.place(shield,3,3);*/
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.highlight=false;
            if (StdDrawPlus.mousePressed()) { 

                b.x = StdDrawPlus.mouseX();
                b.y = StdDrawPlus.mouseY();
                /*if((int)(a)+(int)(c)%2==0){
                    x=a;
                    y=c;
                }*/
                if(b.canSelect((int)b.x, (int)b.y)){
                    b.highlight=true;
                    b.select((int)b.x, (int)b.y);
                }
            }
           

            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
                b.endTurn();
            }
            b.drawBoard(N);
            StdDrawPlus.show(100);
            b.winner();
        }
    }
}

