public class Board{
	private int N = 8;
	private Piece[][] ps = new Piece[N][N];
	private boolean player = true;
	private Piece selected = null;
	private boolean hasmoved = false;
  private int xselected;
  private int yselected;

   public Board(boolean shouldBeEmpty){
   	   
   		 if (!(shouldBeEmpty)){
   		 	Piece pawn1 = new Piece(true,this,0,0,"pawn");
   		 	Piece pawn2 = new Piece(true,this,2,0,"pawn");
   		 	Piece pawn3 = new Piece(true,this,4,0,"pawn");
   		 	Piece pawn4 = new Piece(true,this,6,0,"pawn");
   		 	place(pawn1,0,0);
   		 	place(pawn2,2,0);
   		 	place(pawn3,4,0);
   		 	place(pawn4,6,0);
   		 	Piece shield1 = new Piece(true,this,1,1,"shield");
   		 	Piece shield2 = new Piece(true,this,3,1,"shield");
   		 	Piece shield3 = new Piece(true,this,5,1,"shield");
   		 	Piece shield4 = new Piece(true,this,7,1,"shield");
   		 	place(shield1,1,1);
   		 	place(shield2,3,1);
   		 	place(shield3,5,1);
   		 	place(shield4,7,1);
   		 	Piece bomb1 = new Piece (true,this,0,2,"bomb");
   		 	Piece bomb2 = new Piece (true,this,2,2,"bomb");
   		 	Piece bomb3 = new Piece (true,this,4,2,"bomb");
   		 	Piece bomb4 = new Piece (true,this,6,2,"bomb");
   		 	place(bomb1,0,2);
   		 	place(bomb2,2,2);
   		 	place(bomb3,4,2);
   		 	place(bomb4,6,2);
   		 	Piece pawn1w = new Piece(false,this,1,7,"pawn");
   		 	Piece pawn2w= new Piece(false,this,3,7,"pawn");
   		 	Piece pawn3w = new Piece(false,this,5,7,"pawn");
   		 	Piece pawn4w = new Piece(false,this,7,7,"pawn");
   		 	place(pawn1w,1,7);
   		 	place(pawn2w,3,7);
   		 	place(pawn3w,5,7);
   		 	place(pawn4w,7,7);
   		 	Piece shield1w = new Piece(false,this,0,6,"shield");
   		 	Piece shield2w = new Piece(false,this,2,6,"shield");
   		 	 Piece shield3w = new Piece(false,this,4,6,"shield");
   		 	Piece shield4w = new Piece(false,this,6,6,"shield");
   		 	place(shield1w,0,6);
   		 	place(shield2w,2,6);
   		 	place(shield3w,4,6);
   		 	place(shield4w,6,6);
   		 	Piece bomb1w = new Piece (false,this,1,5,"bomb");
   		 	Piece bomb2w = new Piece (false,this,3,5,"bomb");
   		 	Piece bomb3w = new Piece (false,this,5,5,"bomb");
   		 	Piece bomb4w = new Piece (false,this,7,5,"bomb");
   		 	place(bomb1w,1,5);
   		 	place(bomb2w,3,5);
   		 	place(bomb3w,5,5);
   		 	place(bomb4w,7,5);   		 	

   		 	
        // StdDrawPlus.show(5);
            
        }
        
        }
private void draw(Piece[][] p){
	String x = "";
  for (int i = 0;i < N;i+=1){
		for (int j = 0;j< N;j+=1){
			if (ps[i][j] != null){

			if (ps[i][j].isFire()){

        if (ps[i][j].isBomb()){
          if (ps[i][j].isKing()){
          x = "-crowned";
        }
				StdDrawPlus.picture(i+.5,j+.5,"img/bomb-fire"+x +".png",1,1);
         x = "";}
        else if (ps[i][j].isShield()){
                  if (ps[i][j].isKing()){
          x = "-crowned";
        }
        StdDrawPlus.picture(i+.5,j+.5,"img/shield-fire"+x +".png",1,1);
        x = "";}
        else{
                  if (ps[i][j].isKing()){
          x = "-crowned";
        }
        StdDrawPlus.picture(i+.5,j+.5,"img/pawn-fire"+x +".png",1,1);
        x= "";}

			} else {if (ps[i][j].isBomb()){
                if (ps[i][j].isKing()){
          x = "-crowned";
        }
        StdDrawPlus.picture(i+.5,j+.5,"img/bomb-water"+x +".png",1,1);
        x= "";}
        else if (ps[i][j].isShield()){
                  if (ps[i][j].isKing()){
          x = "-crowned";
        }
        StdDrawPlus.picture(i+.5,j+.5,"img/shield-water"+x +".png",1,1);
        x= "";}
        else{
                  if (ps[i][j].isKing()){
          x = "-crowned";
        }
        StdDrawPlus.picture(i+.5,j+.5,"img/pawn-water"+x +".png",1,1);
        x = "";}
}
		}}
	}
}

private void drawboard(){
   int N = 8;
      StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else                  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                if (canSelect(i,j)){
                  StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);

                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                
            }
        }
}
public Piece pieceAt(int x, int y){
	if ((x < 8)&&(x>=0)&&(y<8)&&(y>=0)){
		return ps[x][y];
	}
	return null;
}

public void place(Piece p,int x, int y){
	if ((x < 8)&&(x>=0)&&(y<8)&&(y>=0)){
		 ps[x][y] = p;
	}

}
public Piece remove(int x, int y){
	if ((x > 7)||(x<0)||(y>7)||(y<0)){
    return null;
  }
  Piece result = ps[x][y];
	ps[x][y] = null;
	return result;
}

public boolean canSelect(int x, int y){
// 	if ((ps[x][y]!= null)&&(ps[x][y].isFire() == player)){
// 		if (selected == null){
// 			return true;
// 		}else {return !hasmoved;}
// 	}
// 	if (selected != null){
		
//     if (!hasmoved && validMove(xselected,yselected,x,y)){
// 			return true;
// 		}
// 		if (selected.hasCaptured() && validMove(xselected,yselected,x,y) && (Math.abs(y-yselected)==2)){
// 			return true;
// 		}
// 		return false;
// 	}
// 	return false;
// }
/// selecting piece//
   if ((x<0)||(y<0)||(x>7)||(7>7)){
    return false;
   }
  if ((ps[x][y]!= null)&&(ps[x][y].isFire()== player)){
    if (selected == null){
      return true;
    }
    else if (hasmoved== false){return true;}
    else{return false;} 
  }
  //selecting empty square
  else if (ps[x][y] == null){
    
    if (selected == null){
      return false;
    }
    // else if ((selected!=null)&&(!hasmoved)){
    //   return true;
    // }
    else if ((selected!=null)&&!hasmoved&&validMove(xselected,yselected,x,y)){
      return true;
    }
    else if(selected.hasCaptured() & validMove(xselected,yselected,x,y)&&(Math.abs(y-yselected)==2)){
      return true;

    }
    else{return false;}
  }else{return false;}
}


public void select(int x, int y){
	
  if (ps[x][y] != null){
     
		selected = ps[x][y];
    xselected = x;
    yselected = y;

    
    

	}
	else{
		
    
    selected.move(x,y);
    xselected = x;
    yselected = y;
		
    hasmoved = true;
     

	}


}
private boolean validMove(int xi, int yi, int xf , int yf){
	if (ps[xf][yf] == null){
    int x = 1;
    int dif = yf-yi;
    if (!selected.isFire()){
      x = -1;
    }
    if (selected.isKing()){
      dif = Math.abs(dif);
      x = 1;
    }
		if ((Math.abs(xf-xi) == 1)&&((dif)== x)){
			return true;
		}
		if  ((Math.abs(xf-xi) == 2)&&((dif)==2 * x )){
			Piece f = ps[(xi+xf)>>>1][(yi+yf)>>>1];
      if (f==null){
        return false;
      }
			return f.isFire()!=player;
		}

   }
		return false;
}
public boolean canEndTurn(){
  if (hasmoved){
    return true;
  }
  return false;
}
public void endTurn(){
  player = !player;
  hasmoved = false;
  selected =null; }
  // selected.doneCapturing();
public String winner(){
  int waterpiece = 0;
  int firepiece = 0;
  for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (ps[i][j]!= null){
        if (ps[i][j].isFire()){
          firepiece+=1;
        } else{
          waterpiece +=1;
        }       
}

      }}
      if ((firepiece == 0)&&(waterpiece != 0)){
        return "Water";
      }
      else if ((waterpiece == 0)&&(firepiece!=0)){
        return "Fire";
      }
      else if ((waterpiece == 0)&&(firepiece ==0)){
        return "No one";
      }
      else{
        return null;
      }

}



  public static void main(String[] args) {
  		int N = 8;
     
    //   int N = 8;
  		// StdDrawPlus.setXscale(0, N);
    //     StdDrawPlus.setYscale(0, N);
        

    //     for (int i = 0; i < N; i++) {
    //         for (int j = 0; j < N; j++) {
    //             if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
    //             else                  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
    //             StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                
    //         }
    //     }
       
   		Board q = new Board(true);
      q.drawboard();
   		q.draw(q.ps);

  		
  		while (q.winner() != null){
        q.drawboard();
        q.draw(q.ps);
        StdDrawPlus.show(30);
        if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                //System.out.print(q.canSelect((int)x,(int)y));
                if (q.canSelect((int)x,(int)y)){
                	q.select((int)x,(int)y);
                   
                

                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare((int)x+.5,(int)y+.5,0.5);
                

                }
               
                }

         if (StdDrawPlus.isSpacePressed()){
                  // debugDump(q);
                  
                  if (q.canEndTurn()){
                    q.endTurn();


                  }      
            }
         	
  }
  	}

  // public static void debugDump(Board b) {
  //   for (int i = 0; i < 8; i++) {
  //     for (int j = 0; j < 8; j++) {
  //       Piece p = b.pieceAt(j, i);
  //       if (p == null) {
  //         continue;
  //       }
  //       System.out.printf("(%d, %d) fire: %b, shield: %b, bomb: %b%n", j, i, p.isFire(), p.isShield(), p.isBomb());
  //     }
  //   }
  // }
  // }
	
}