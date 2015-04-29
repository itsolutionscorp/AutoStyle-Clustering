public class Board {
    private int turn = 0; //0 means fire 1 means rain
	private static Piece[][] map; // try static 
    private int sx = 0;
    private int sy = 0;
    private boolean empty = false;
    private static Piece sp = null; // try static 
    // remember to set it to null at the end of each turn//
    private boolean moved = false; 
    // remember to set it to false at the end of each turn, set it to true when ever moves// 
	public static void main (String args[]){
	  StdDrawPlus.setXscale(0, 8);
      StdDrawPlus.setYscale(0, 8);
      Board b = new Board(false);
      if (b.empty == false){
            for(int x = 0; x < 8; x = x + 2){
                StdDrawPlus.picture(x + .5,  .5, "img/pawn-fire.png", 1, 1);
            }
            for(int x = 1; x < 8; x = x + 2){
                StdDrawPlus.picture(x + .5,  1.5, "img/shield-fire.png", 1, 1);
            }
            for(int x = 0; x < 8; x = x + 2){
                StdDrawPlus.picture(x + .5,  2.5, "img/bomb-fire.png", 1, 1);
            }
            for(int x = 1; x < 8; x = x + 2){
                StdDrawPlus.picture(x + .5,  7.5, "img/pawn-water.png", 1, 1);
            }
            for(int x = 0; x < 8; x = x + 2){
                StdDrawPlus.picture(x + .5,  6.5, "img/shield-water.png", 1, 1);
            }
            for(int x = 1; x < 8; x = x + 2){
                StdDrawPlus.picture(x + .5,  5.5, "img/bomb-water.png", 1, 1);
            }
            
        }
    //-----------------------------play part------------------
    while(true) {
     
  
            setBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if(b.canSelect(x, y)) 
                    b.select(x, y);
            }

            if(b.moved && StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn())
                    b.endTurn();
            }
            StdDrawPlus.show(100);
           
        }

	}

       
    private static void setBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                if (map[i][j]!= null){
                  Piece lol = map[i][j];
                  String name = reader(lol);
                  StdDrawPlus.picture(i + .5,  j + .5, name , 1, 1);  
                }}
            }
        }
	public Board(boolean shouldBeEmpty){
        this.empty = shouldBeEmpty;
        map = new Piece[8][8];
		if (shouldBeEmpty == false){
            for(int x = 0; x < 8; x = x + 2){
                map[x][0] = new Piece(true,this,x,0,"pawn");
            }
            for(int x = 1; x < 8; x = x + 2){
                map[x][1] = new Piece(true,this,x,1,"shield");
            }
            for(int x = 0; x < 8; x = x + 2){
                map[x][2] = new Piece(true,this,x,2,"bomb");
            }
            for(int x = 1; x < 8; x = x + 2){
                map[x][7] = new Piece(false,this,x,7,"pawn");
            }
            for(int x = 0; x < 8; x = x + 2){
                map[x][6] = new Piece(false,this,x,6,"shield");
            }
            for(int x = 1; x < 8; x = x + 2){
                map[x][5] = new Piece(false,this,x,5,"bomb");
			
        }
		
		

		}} 
    private static String reader(Piece haha){
        String middle = "";
        String front = "";
        if (haha.isFire()){
            middle = "-fire";
             if (haha.isShield()){
            front = "shield";
        }
        else if(haha.isBomb()){
            front = "bomb";
        }
        else{
            front = "pawn";
        }
        }
        else{
            middle = "-water";
             if (haha.isShield()){
            front = "shield";
        }
        else if(haha.isBomb()){
            front = "bomb";
        }
        else{
            front = "pawn";
        }

        }
      //  System.out.println("img/"+front+middle+".png");
        return "img/"+front+middle+".png";

    }
    
    
   
    public Piece pieceAt(int x, int y){
    	if (x < 8 && y < 8 && x >= 0 && y >= 0 && map[x][y] != null){
            return map[x][y];
        }
        else{
            return null;
        }
    }

    public boolean canSelect(int x, int y){
        if (x < 8 && y < 8 && x >= 0 && y >= 0){
        if (map[x][y] != null){
            if ((map[x][y].isFire() && this.turn == 0) || (map[x][y].isFire()==false && this.turn == 1)){
            if (this.sp == null){
                return true;
            }
            else if(this.sp != null && this.moved == false){
                return true;
            }
            else{
                return false;
            }}
            return false;
        }
        else if (map[x][y] == null){
            if(this.sp != null && this.moved == false && validMove(this.sx,this.sy,x,y)){
                return true;
            }
            else if(this.sp != null && this.moved == true && this.sp.hasCaptured() && multicheck()){
                return true;
            }
            else {
                return false;
            }

        }}
    
            return false;
    

    }	
    private boolean validMove(int xi,int yi,int xf, int yf){
        if (sp != null){
        if (sp.isKing()){
            if ((xi-1 == xf && yi+1 ==yf ) || (xi+1 == xf && yi+1 == yf)){
              if (pieceAt(xf,yf) == null){
                return true;
              }
              else{
                return false;
              }
           }
           else if ((xi-2 == xf && yi+2 ==yf ) || (xi+2 == xf && yi+2 == yf)){
              if (pieceAt(xi-1,yi+1)!=null ){
                if(pieceAt(xi-1,yi+1).isFire() != sp.isFire()){
                    return true;
                }
                else{
                    return false;
                }
              }
               if (pieceAt(xi+1,yi+1)!=null){
                if(pieceAt(xi+1,yi+1).isFire() != sp.isFire()){
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
             
           if ((xi-1 == xf && yi-1 ==yf ) || (xi+1 == xf && yi-1 == yf)){
              if (pieceAt(xf,yf) == null){
                return true;
              }
              else{
                return false;
              }
           }
           else if ((xi-2 == xf && yi-2 ==yf ) || (xi+2 == xf && yi-2 == yf)){
              if (pieceAt(xi-1,yi-1)!=null ){
                if(pieceAt(xi-1,yi-1).isFire() != sp.isFire()){
                    return true;
                }
                else{
                    return false;
                }
              }
               if (pieceAt(xi+1,yi-1)!=null){
                if(pieceAt(xi+1,yi-1).isFire() != sp.isFire()){
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

        }
        if ((sp.isFire() && sp.isKing() == false)){
            if ((xi-1 == xf && yi+1 ==yf ) || (xi+1 == xf && yi+1 == yf)){
              if (pieceAt(xf,yf) == null){
                return true;
              }
              else{
                return false;
              }
           }
           else if ((xi-2 == xf && yi+2 ==yf ) || (xi+2 == xf && yi+2 == yf)){
              if (pieceAt(xi-1,yi+1)!=null ){
                if(pieceAt(xi-1,yi+1).isFire() != sp.isFire()){
                    return true;
                }
                else{
                    return false;
                }
              }
               if (pieceAt(xi+1,yi+1)!=null){
                if(pieceAt(xi+1,yi+1).isFire() != sp.isFire()){
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
        }
        else if ((sp.isFire() == false && sp.isKing() == false )){
          
           if ((xi-1 == xf && yi-1 ==yf ) || (xi+1 == xf && yi-1 == yf)){
              if (pieceAt(xf,yf) == null){
                return true;
              }
              else{
                return false;
              }
           }
           else if ((xi-2 == xf && yi-2 ==yf ) || (xi+2 == xf && yi-2 == yf)){
              if (pieceAt(xi-1,yi-1)!=null ){
                if(pieceAt(xi-1,yi-1).isFire() != sp.isFire()){
                    return true;
                }
                else{
                    return false;
                }
              }
               if (pieceAt(xi+1,yi-1)!=null){
                if(pieceAt(xi+1,yi-1).isFire() != sp.isFire()){
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
        }}
        return false;

    }

    public void select(int x, int y){
         if (map[x][y] != null){
            this.sp = map[x][y];
            this.sx = x;
            this.sy = y;
         }
         else if (map[x][y] == null && this.sp !=null){
            sp.move(x,y);
            exploding(x,y);
            this.sx = x;
            this.sy = y;
            this.moved = true;

         }

    }

  private void exploding(int x,int y){
    if (sp.isBomb() && sp.hasCaptured()){
      for (int xt = x-1; xt < x+2; xt++) {
            for (int yt = y-1; yt < y+2; yt++) {
              if (pieceAt(xt,yt) != null){
               if (pieceAt(xt,yt).isShield() == false){
                   remove(xt,yt);
               }}
         }}
    }
         
  }
    public void place(Piece p, int x, int y){
    	if (p != null && x < 8 && y < 8){
    		map[x][y] = p;
    	}

    }
    public Piece remove(int x, int y){
      Piece holder = map[x][y];
    	if (x > 8 || y > 8){
    		System.out.println("X OR Y IS OUT OF BOUND!!");
    	}
    	else if (map[x][y] ==null){
    		System.out.println("THERE IS NO PIECE HERE DUDE!");
    	}
      else{
    	this.map[x][y] = null;}
    	return holder;
      
    }
    private boolean multicheck(){
        int a = this.sx -2;
        int b = this.sx + 2;
        int c = this.sy - 2;
        int d = this.sy + 2;
        if (this.moved == true && sp.hasCaptured() == true){
           if (validMove(this.sx,this.sy,b,d)){
            return true; 
           }
           else if(validMove(this.sx,this.sy,a,d)){
            return true;
           }
           else if(validMove(this.sx,this.sy,a,c)){
            return true;
           }
           else if(validMove(this.sx,this.sy,b,c)){
            return true;
           }
           else{
            return false;
           }
        }
        return false;
    }
    public boolean canEndTurn(){
        if (this.moved == true){
            if (multicheck() == true){
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }
    public void endTurn(){
        if (canEndTurn() == true){
        turn = turn ^ 1;
        sp.doneCapturing();
        this.sp = null;
        this.moved = false;

    }

    }
    
    public String winner() {
        int fireleft = 0; 
        int waterleft = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] != null) {
                    if (map[i][j].isFire())
                        fireleft = fireleft + 1;
                    else
                        waterleft = waterleft + 1; 
                }
            }
        }
        if (fireleft > 0 && waterleft == 0)
            return "Fire";
        else if (waterleft > 0 && fireleft == 0)
            return "Water";
        else if (waterleft == 0 && fireleft == 0)
            return "No one";
        else 
            return null;}
	
   
}