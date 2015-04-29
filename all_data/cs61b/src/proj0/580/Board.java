public class Board {

    private Piece[][] pieces;
    private int N = 8;
    private boolean firesTurn;
    private Piece select = null;
    private boolean moved = false;
    private int selectx;
    private int selecty;

    /*drawBoard based on StdDrawDemo.java*/
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(select!=null&&i==selectx&&j==selecty){
                StdDrawPlus.filledSquare(selectx + .5, selecty + .5, .5);
                }
                if(pieces[i][j]!=null){
                    if (pieces[i][j].isFire()) {
                        if(pieces[i][j].isKing()){
                            if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else{
                            if(pieces[i][j].isKing()){
                                if(pieces[i][j].isBomb()){
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                                }
                                else if(pieces[i][j].isShield()){
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                                }
                                else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                                }
                            }
                            else{
                                if(pieces[i][j].isBomb()){
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                                }
                                else if(pieces[i][j].isShield()){
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                                }
                                else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                                }
                            }
                    }

                }
                
            }
        }
    }

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[N][N];
        if(!shouldBeEmpty){       
            for(int i = 0; i<8; i+=2){
                pieces[i][0]= new Piece(true, this, i, 0, "pawn");                
                pieces[i][2]= new Piece(true, this, i, 2, "bomb");
                pieces[i][6]= new Piece(false, this, i, 6, "shield");
                
            }
            for(int i = 1; i<8; i+=2){
                pieces[i][1]= new Piece(true, this, i, 1, "shield");
                pieces[i][5]= new Piece(false, this, i, 5, "bomb");                
                pieces[i][7]= new Piece(false, this, i, 7, "pawn");
            }
            
        }
        this.firesTurn = true;

    }

    public Piece pieceAt(int x, int y){
        if(x>N||y>N){
            return null;
        }
        return pieces[x][y];
    }

    public void place(Piece p, int x, int y){
        if(x>N||y>N||p==null){
            return;
        }
        pieces[x][y]=p;
    }

    public boolean canSelect(int x, int y){
        if(pieces[x][y]!=null){
            if (firesTurn&&pieces[x][y].isFire()){
                if(!moved){
                   
                    return true;
                }
            } 
            else if(!pieces[x][y].isFire()&&!firesTurn){
                if(!moved){
                  
                    return true;
                }
            }

        }
        else{
            if(select!=null&&validMove(selectx,selecty,x,y)){
              
                return true;
            } 
        }
        return false;
      
        
    }
    private boolean validMove(int x1, int y1, int x2, int y2){
        if(pieces[x2][y2]==null){
            if(pieces[x1][y1].isKing()){
                
                if(Math.abs(x2-x1)==1&&Math.abs(y2-y1)==1&&!pieces[x1][y1].hasCaptured()&&!moved){return true;}
                if(Math.abs(x2-x1)==2 && Math.abs(y2-y1)==2){
                    if(pieces[(x2+x1)/2][(y2+y1)/2]!=null&&(pieces[(x2+x1)/2][(y2+y1)/2].isFire()!=pieces[x1][y1].isFire())&&(!moved||select.hasCaptured()))
                        {return true;}
                }
                return false;
            }
            if(pieces[x1][y1].isFire()){
                
                if((x2==x1+1||x2==x1-1)&&(y2==y1+1)&&!pieces[x1][y1].hasCaptured()&&!moved){
                    
                    return true;
                 }
                if(Math.abs(x2-x1)==2 && y2-y1==2){
                    if(pieces[(x2+x1)/2][(y2+y1)/2]!=null&&!pieces[(x2+x1)/2][(y2+y1)/2].isFire()&&(!moved||select.hasCaptured())){
                        
                        return true;
                        }
                }
                return false;
            }
            if(!pieces[x1][y1].isFire()){
                if((x2==x1+1||x2==x1-1)&&(y2==y1-1)&&!pieces[x1][y1].hasCaptured()&&!moved){return true;}
                if(Math.abs(x2-x1)==2 && (y2-y1)==-2){
                    if(pieces[(x2+x1)/2][(y2+y1)/2]!=null&&pieces[(x2+x1)/2][(y2+y1)/2].isFire()&&(!moved||select.hasCaptured())){return true;}
                }
                return false;
            }

                        
        }
        System.out.println("invalid move");
        return false;
    }

    public void select(int x, int y){
        
            
            if(pieces[x][y]!=null){
            select = pieces[x][y];
            selectx = x;
            selecty = y;
            }
            else{
                select.move(x,y);
                select = pieces[x][y];  
                selectx =x;
                selecty =y; 
                moved = true;        
            }
        
        
    }

    public Piece remove(int x, int y){
        Piece r = pieces[x][y];
        pieces[x][y]=null;
        return r;

    }

    public boolean canEndTurn(){
        if(select == null){
            return moved;
        }
        else{
            return moved||select.hasCaptured();
        }
        
    }

    public void endTurn(){
        if(this.canEndTurn()){
            if(firesTurn){
                firesTurn=false;
                if(select!=null){
                    select.doneCapturing();
                }
            select=null;
            }
            else{
                firesTurn=true;
                if(select!=null){select.doneCapturing();}
                select=null;
            }
        moved = false;
        }
        

    }

    public String winner(){
        int numfire=this.numPieces()[0];
        int numwater=this.numPieces()[1];
        if(numfire>0&&numwater==0){
            return "Fire";
        }
        if(numfire==0&&numwater>0){
            return "Water";
        }
        if((numfire==0&&numwater==0)){
            return "No one";
        }
        return null;
    }

    private int[] numPieces(){
        int numfire=0;
        int numwater=0;
        for(Piece[] col : pieces){
            for(Piece p: col){
                if(p!=null){
                    if(p.isFire()){
                        numfire+=1;
                    }
                    else{numwater+=1;}
                }
            }
        }
        int[] result = {numfire,numwater};
        return result;
    }

    /*GUI structure in main method based on StdDrawDemo.java*/
    public static void main(String[] args) {
        
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.N);
        StdDrawPlus.setYscale(0, b.N);
        
        
        while(true) {
            b.drawBoard(b.N);
            
            StdDrawPlus.show(20);

            if(b.numPieces()[0]==0||b.numPieces()[1]==0){
                System.out.println(b.winner());
                return;
            }
            if(StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(x<=b.N&&y<=b.N&&b.canSelect((int)x,(int)y)){
                b.select((int) x,(int) y);
                }

            }
            if(StdDrawPlus.isSpacePressed()){
                b.endTurn();
            } 

            
            
            
            
        }
    }
}