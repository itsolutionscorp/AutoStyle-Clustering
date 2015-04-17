public class Board{
    private static Piece pieces[][]=new Piece[8][8];
    private String img;
    private int turn=0;
    private Piece chosen;
    private boolean selected=false;
    private boolean moved=false;
    private int ax=0;
    private int ay=0;
    private boolean fireturn=false;

   
   
   
    
   
    

   
	public static void main(String[] args){

        Board b=new Board(false);
        StdDrawPlus.setXscale(0,8);
        StdDrawPlus.setYscale(0,8);
        for (int p=0; p<8; p++){
                for (int m=0; m<8; m++){
                    

                    if ((p + m) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(p + .5, m + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                    if (m==0 && p%2==0){
                        
                        StdDrawPlus.picture(p+ .5, m + .5, "img/pawn-fire.png", 1, 1);

                    }
                    else if (m==1 && p%2!=0){
                        
                         StdDrawPlus.picture(p+ .5, m + .5, "img/shield-fire.png", 1, 1);
                    }
                    else if (m==2 && p%2==0){
                        
                        StdDrawPlus.picture(p+ .5, m + .5, "img/bomb-fire.png", 1, 1);
                    }
                    else if (m==3){
                        continue;
                    }
                    else if (m==4){
                        continue;

                    }
                    else if (m==5 && p%2!=0){
                        
                         StdDrawPlus.picture(p+ .5, m + .5, "img/bomb-water.png", 1, 1);
                    }
                    else if (m==6 && p%2==0){
                        
                         StdDrawPlus.picture(p+ .5, m + .5, "img/shield-water.png", 1, 1);
                    }
                    else if (m==7 && p%2!=0){
                        
                         StdDrawPlus.picture(p+ .5, m + .5, "img/pawn-water.png", 1, 1);
                    }
                                        
                          
                } 

            }

        
       
        
    }
    public Piece pieceAt(int x, int y){
        if (x>7 || y>7){
            return null;
        }
        return pieces[x][y];
    }
    public Board (boolean shouldBeEmpty){
        if (shouldBeEmpty==true){
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    pieces[i][j]=null;
                }
            }
        }
        else{
            for (int p=0; p<8; p++){
                for (int m=0; m<8; m++){
                    
                    if (m==0 && p%2==0){
                        pieces[p][m] = new Piece(true, this, p, m, "pawn");
                        

                    }
                    else if (m==1 && p%2!=0){
                        pieces[p][m] = new Piece(true, this, p, m, "shield");
                        
                    }
                    else if (m==2 && p%2==0){
                        pieces[p][m] = new Piece(true, this, p, m, "bomb");
                        
                    }
                    else if (m==3){
                        pieces[p][m]=null;
                    }
                    else if (m==4){
                        pieces[p][m]=null;

                    }
                    else if (m==5 && p%2!=0){
                        pieces[p][m] = new Piece(false, this, p, m, "bomb");
                         
                    }
                    else if (m==6 && p%2==0){
                        pieces[p][m] = new Piece(false, this, p, m, "shield");
                        
                    }
                    else if (m==7 && p%2!=0){
                        pieces[p][m] = new Piece(false, this, p, m, "pawn");
                        
                    }
                    
                    
                          
                } 

            }
           
        }
    }
    public void place (Piece a, int x, int y){
        if ((a!=null) && (x>0) && (y>0) && (x<8) && (y<8)){
            pieces[x][y]=a;
        }
    }
    public Piece remove (int x,int y){
        if ((x<0) || (y<0) || (x>7) || (y>7)){
            System.out.println("Position out of bounds");
            return null;
        }
        else if (pieces[x][y]!=null){
            System.out.println("Selected position is empty");
            return null;
        }
        else{
            Piece taken=  pieces[x][y];
            pieces[x][y]=null;
            return taken;
        }
    }
    public boolean canEndTurn(){
        return selected;
    }
    public void endTurn(){
        turn=1-turn;
        selected=false;
        if (chosen.hasCaptured()){
            chosen.doneCapturing();
        }
        chosen=null;

    }

   
    public String winner(){
        int numw=0;
        int numf=0;

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
            
                if (pieces[i][j]==null){
                    continue;
                }
                if (pieces[i][j].isFire()==true){
                    numf+=1;
                }
                if (pieces[i][j].isFire()==false){
                    numw+=1;
                }
            }
        }
        if (numw==0 && numf>0){
            return "Fire";
        }
        if (numw==0 && numf<0){
            return "Water";
        }
        if (numw==0 && numf==0){
            return "No One";
        }
        return null;
    }
    public boolean canSelect(int x, int y){
        Piece a=pieceAt(x,y);
        if (a!=null){
            if (a.isFire()==fireturn){
                return !moved;
            }
            else{
                return false;
            }
        }
        Piece z=pieceAt(ax,ay);
        if (z==null){
            return false;
        }
        if (!z.isKing()){
            if (z.isFire() && y<=ay){
                return false;
            }
            else if(!z.isFire() && y>=ay){
                return false;
            }
        }
        if (Math.abs(ax-x)==1 && Math.abs(ay-y)==1){
            return !moved;
        }
        else if (Math.abs(ax-x)==2 && Math.abs(ay-y)==2){
            if (!z.hasCaptured() && moved){            
                return false;
            }
            int xcap=(ax+x)/2;
            int ycap=(ay+y)/2;
            return pieceAt(xcap,ycap).isFire()!=z.isFire();
        }
        return false;

    }
        
        
    public void select (int x, int y){
        if (canSelect(x,y)==true){
            if (pieceAt(x,y)==null){
                chosen.move(x,y);
                selected=false;
            }
            if (pieceAt(x,y)!=null){
                selected=true;
            }
            }
        }
    }


   