public class Board {
	private Piece[][] pieces = new Piece[8][8];
    private Board b;
	//game information
	private int player=0;
	private int hasselected=0;
	private int hasmoved=0;
	//checker information
	private Piece selected;
    private int tryit=0;

  
    

	public static void main(String[] args){

        Board b=new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while(true){

         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.pieces[i][j]!=null) {
                   StdDrawPlus.picture(i + .5, j + .5, b.getimage(b.pieces[i][j]), 1, 1);
                 }
            }
        }


    if (StdDrawPlus.mousePressed()){
        int x=(int)StdDrawPlus.mouseX();
        int y=(int)StdDrawPlus.mouseY();
        b.tryit=1;

          if (b.canSelect(x,y)){
            b.select(x,y);  
        }   
    }


    if (StdDrawPlus.isSpacePressed()){
        if (b.canEndTurn()){
            b.endTurn();
        }
    }

    //if(b.winner()!=null){
      //   b.winner();
   // }

        StdDrawPlus.show(50);
    }



	}
    private void explosion(Piece p){
        int[] i=getxys(p);
        int x=i[0];
        int y=i[1];
        int[][] surround=new int[][]{{x,y},{x,y+1},{x-1,y+1},{x-1,y},{x-1,y-1},{x,y-1},{x+1,y-1},{x+1,y},{x+1,y+1}};
            for (int[] item: surround){
                if (pieceAt(item[0],item[1])!=null && pieceAt(item[0],item[1]).isShield()==false){
                    remove(item[0],item[1]);
                }
            }
    }

	public Board(boolean shouldBeEmpty){

        if (shouldBeEmpty==false){
    for (int j=0; j<8; j+=2) {
        pieces[j][0]=new Piece(true,b,j,0,"pawn");
        }
    for (int j=1; j<8; j+=2) {
        pieces[j][1]=new Piece(true,b,j,1,"shield");
        }
    for (int j=0; j<8; j+=2) {
        pieces[j][2]=new Piece(true,b,j,2,"bomb");
        }
    for (int j=1; j<8; j+=2) {
        pieces[j][5]=new Piece(false,b,j,5,"bomb");
        }
    for (int j=0; j<8; j+=2) {
        pieces[j][6]=new Piece(false,b,j,6,"shield");
        }
    for (int j=1; j<8; j+=2) {
        pieces[j][7]=new Piece(false,b,j,7,"pawn");
        }
	   }
    }

    private String getimage(Piece p){
        if (p.isFire()){
            if(p.isShield()&& p.isKing()){
                return "img/shield-fire-crowned.png";
            }else if(p.isShield()) {
                return "img/shield-fire.png";
            }else if(p.isBomb() && p.isKing()){
                return "img/bomb-fire-crowned.png";
            }else if(p.isBomb()){
                return "img/bomb-fire.png";
            }else if(p.isKing()){
                return "img/pawn-fire-crowned.png";
            }else if(p.isKing()==false){
                return "img/pawn-fire.png";
            }
        } else {
            if(p.isShield()&& p.isKing()){
                return "img/shield-water-crowned.png";
            }else if(p.isShield()){
                return "img/shield-water.png";
            }else if(p.isBomb() && p.isKing()){
                return "img/bomb-water-crowned.png";
            }else if(p.isBomb()){
                return "img/bomb-water.png";
            }else if(p.isKing()){
                return "img/pawn-water-crowned.png";
            }else if(p.isKing()==false){
                return "img/pawn-water.png";
            }else{
                return null;
            }
        } return null;
    } 


    public Piece pieceAt(int x, int y){
    	if(x>7||y>7||x<0||y<0){
    		return null;
    	} 
    	else{
            return pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y){
          if (x>7 || y>7){
            return false;
        }
        Piece p=pieceAt(x,y);

    	if (p!=null) {
    		if (p.side()==player) {
    			if(hasmoved==0){
    				return true;
    			}
    		}
    	} 
         else if(selected!=null){
                int[] initials = getxys(selected);

                if (hasmoved==0){
                if (validnormalmove(initials[0],initials[1],x,y) ||
                    validcapture(initials[0],initials[1],x,y)){
                    return true;}
            } 

            else if(checkpiece(selected) && selected.hasCaptured() && hasmoved==1){
            	if (validcapture(initials[0],initials[1],x,y)){
                return true;}
            }
        } 
        return false;
    }
    private boolean checkpiece(Piece p){
        for(int i=0;i<8;i+=1) {
            for (int j=0;j<8;j+=1){
                if (pieces[i][j]==p){
                    return true;
                }
            }
        } return false;
    }

    private int[] getxys(Piece p){
        int[] result = new int[]{8,8};
        for(int i=0;i<8;i+=1) {
            for (int j=0;j<8;j+=1){
                if (pieces[i][j]==p){
                    result=new int[]{i,j};
                    return result;
                }
            }
        } return result;
    }


    private boolean validnormalmove(int xi, int yi, int xf, int yf){
    	Piece p=pieceAt(xi,yi);
        if(p.isKing()){
            if((xf==xi+1 && yf==yi+1)||(xi==xf+1 && yf==yi+1)
                ||(xf==xi+1 && yi==yf+1)||(xi==xf+1 && yi==yf+1)){
                return true;
            }
        } else if (p.isFire()){
             if((xf==xi+1 && yf==yi+1)||(xi==xf+1 && yf==yi+1)){
                return true;
            }
        }else if (p.isFire()==false){
             if((xf==xi+1 && yf==yi-1)||(xi==xf+1 && yi==yf+1)){
                return true;
            }
        } return false; 
    }

    private boolean validcapture(int xi, int yi, int xf, int yf){
        Piece p=pieceAt(xi,yi);
       //Piece p2=pieceAt((xf-xi)/2+xi,(yf-yi)/2+yi);
        int side=p.side();

        if (p.isKing()){
            if (pieceAt(xi+1,yi+1)!=null){
                if (pieceAt(xi+1,yi+1).side()!=side && xf==xi+2 && yf==yi+2){
                    return true;
                }}
                if (pieceAt(xi-1,yi+1)!=null){
                if (pieceAt(xi-1,yi+1).side()!=side && xf==xi-2 && yf==yi+2){
                    return true;
            }}
            if (pieceAt(xi-1,yi-1)!=null) {
                if (pieceAt(xi-1,yi-1).side()!=side && xf==xi-2 && yf==yi-2){
                    return true;
        }}
         if (pieceAt(xi+1,yi-1)!=null){
          if (pieceAt(xi+1,yi-1).side()!=side && xf==xi+2 && yf==yi-2){
                    return true; 
                }}
            }


            else if (p.isFire()){
               if (pieceAt(xi+1,yi+1)!=null){
                if (pieceAt(xi+1,yi+1).side()!=side && xf==xi+2 && yf==yi+2){
                    return true;
                }}
                if (pieceAt(xi-1,yi+1)!=null){
                if (pieceAt(xi-1,yi+1).side()!=side && xf==xi-2 && yf==yi+2){
                    return true;
            }}
            } 

            else if (p.isFire()==false){
                
                 if (pieceAt(xi-1,yi-1)!=null) {
                if (pieceAt(xi-1,yi-1).side()!=side && xf==xi-2 && yf==yi-2){
                    return true;
                 }}
        if (pieceAt(xi+1,yi-1)!=null){
          if (pieceAt(xi+1,yi-1).side()!=side && xf==xi+2 && yf==yi-2){
                    return true; 
                }}
            } return false;
             }   



    public void select(int x, int y){
    	Piece p=pieceAt(x,y);
    	 if (tryit==0 && p!=null){
             player=p.side();
         }
    	 
        if (p!=null){
        	if(p.side()==player){
            selected=p;
            hasselected=1;}

        }
        else if (p==null){
            int[] i=getxys(selected);
            if (i[0]-x>1||x-i[0]>1){
                remove((i[0]-x)/2+x,(i[1]-y)/2+y);
            }
            place(selected,x,y);
            hasmoved=1;
            if (selected!=null && selected.isBomb() && selected.hasCaptured()){
                explosion(selected); 
                }
            }
    }


    public void place(Piece p, int x, int y){
        Piece p1=pieceAt(x,y);
        if (x<8 && y<8){
            if (p1!= null){
                remove(x,y);}
            int[] initials = getxys(p);
            if (pieceAt(initials[0],initials[1])!=null){
            remove(initials[0],initials[1]);}
            pieces[x][y]=p; 
            p.move(x, y);        
        }
    }

    public Piece remove(int x, int y){
        Piece p=pieceAt(x,y);
        if(x>7 | y>7){
            System.out.println("out of bounds!");
        }else if (p==null) {
            System.out.println("there is nothing to remove!");
        }
            removevalue(pieces,p);
            return p;
    }

    private void removevalue(Piece[][] lst, Piece p){
        for (int i=0;i<8;i+=1){
            for(int j=0;j<8;j+=1){
                if (lst[i][j]==p){
                    lst[i][j]=null;
                }
            }
        }
    }

  
    public boolean canEndTurn(){
        if (hasmoved==1){
            return true;
        }else{
            return false;
        }
    }

  
    public void endTurn(){
            if (selected!=null){
            selected.doneCapturing();}
            hasselected=0;
            hasmoved=0;
            selected=null;
            if(player==0){
                player=1;
            }else if (player==1){
                player=0;
            }
    }


    public String winner(){
        int countfire=0;
        int countwater=0;
        for (int i=0;i<8;i+=1){
        	for (int j=0;j<8;j+=1){
            if(pieces[i][j]!=null && pieces[i][j].isFire()) {
                countfire+=1;
            } else if (pieces[i][j]!=null && pieces[i][j].isFire()==false){
                countwater+=1;}
            }
        }
        if (countfire==0 && countwater==0){
            return "No one";
        } else if(countwater==0){
            return "Fire";
        } else if (countfire==0){
            return "Water";
        }return null;
    }
}


 

