
public class Board{
    
    private Piece[][] piecearray = new Piece[8][8];
    private int selectX;
    private int selectY;
    private boolean has_moved;
    private boolean fire_turn;
    private int difX;
    private int difY;
    private int capX;
    private int capY;
    private Piece select_piece;
    


    public static void main(String[] args) {
        Board b = new Board(false);
        int mouseX = 10;
        int mouseY = 10;
        while(true){
            b.drawBoard(8);
            if(StdDrawPlus.mousePressed()){
                int x_coordinate = (int) StdDrawPlus.mouseX();
                int y_coordinate = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x_coordinate,y_coordinate)){
                    b.select(x_coordinate,y_coordinate);
                    mouseX = x_coordinate;
                    mouseY = y_coordinate;
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn())
                    b.endTurn();
                    mouseX = 10;
                    mouseY = 10; 
                }
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(mouseX + .5, mouseY + .5, .5);
            b.drawPieces(b);
            StdDrawPlus.show(15);
        } 
    }


            
    private void drawBoard(int N){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                }else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawPieces(Board board){
        for(int m=0;m<8;m++){
            for(int n=0;n<8;n++){
                if (piecearray[m][n] != null) {
                    if(piecearray[m][n].isKing()){
                        if(piecearray[m][n].isFire()){
                            if(piecearray[m][n].isShield()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/shield-fire-crowned.png", 1, 1);                
                            }else if(piecearray[m][n].isBomb()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }else{
                                StdDrawPlus.picture(m + .5, n + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }else{
                            if(piecearray[m][n].isBomb()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/bomb-water-crowned.png", 1, 1);
                            }else if(piecearray[m][n].isShield()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/shield-water-crowned.png", 1, 1);
                            }else{
                                StdDrawPlus.picture(m + .5, n + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                    }else{
                        if(piecearray[m][n].isFire()){
                            if(piecearray[m][n].isShield()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/shield-fire.png", 1, 1);                
                            }else if(piecearray[m][n].isBomb()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/bomb-fire.png", 1, 1);
                            }else{
                                StdDrawPlus.picture(m + .5, n + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }else{
                            if(piecearray[m][n].isBomb()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/bomb-water.png", 1, 1);
                            }else if(piecearray[m][n].isShield()){
                                StdDrawPlus.picture(m + .5, n + .5, "img/shield-water.png", 1, 1);
                            }else{
                                StdDrawPlus.picture(m + .5, n + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }
    

    public Board(boolean shouldBeEmpty){
        has_moved = false;
        fire_turn = true;
        selectX = 10;
        selectY = 10;
        select_piece = null;
        if (!shouldBeEmpty){
        Piece a1 = new Piece(true,this,0,0,"pawn");
        Piece a2 = new Piece(true,this,2,0,"pawn");
        Piece a3 = new Piece(true,this,4,0,"pawn");
        Piece a4 = new Piece(true,this,6,0,"pawn");
        Piece b1 = new Piece(true,this,1,1,"shield");
        Piece b2 = new Piece(true,this,3,1,"shield");
        Piece b3 = new Piece(true,this,5,1,"shield");
        Piece b4 = new Piece(true,this,7,1,"shield");
        Piece c1 = new Piece(true,this,0,2,"bomb");
        Piece c2 = new Piece(true,this,2,2,"bomb");
        Piece c3 = new Piece(true,this,4,2,"bomb");
        Piece c4 = new Piece(true,this,6,2,"bomb");
        Piece d1 = new Piece(false,this,1,5,"bomb");
        Piece d2 = new Piece(false,this,3,5,"bomb");
        Piece d3 = new Piece(false,this,5,5,"bomb");
        Piece d4 = new Piece(false,this,7,5,"bomb");
        Piece e1 = new Piece(false,this,0,6,"shield");
        Piece e2 = new Piece(false,this,2,6,"shield");
        Piece e3 = new Piece(false,this,4,6,"shield");
        Piece e4 = new Piece(false,this,6,6,"shield");
        Piece f1 = new Piece(false,this,1,7,"pawn");
        Piece f2 = new Piece(false,this,3,7,"pawn");
        Piece f3 = new Piece(false,this,5,7,"pawn");
        Piece f4 = new Piece(false,this,7,7,"pawn");

        piecearray[0][0]=a1;
        piecearray[2][0]=a2;
        piecearray[4][0]=a3;
        piecearray[6][0]=a4;
        piecearray[1][1]=b1;
        piecearray[3][1]=b2;
        piecearray[5][1]=b3;
        piecearray[7][1]=b4;
        piecearray[0][2]=c1;
        piecearray[2][2]=c2;
        piecearray[4][2]=c3;
        piecearray[6][2]=c4;
        piecearray[1][5]=d1;
        piecearray[3][5]=d2;
        piecearray[5][5]=d3;
        piecearray[7][5]=d4;
        piecearray[0][6]=e1;
        piecearray[2][6]=e2;
        piecearray[4][6]=e3;
        piecearray[6][6]=e4;
        piecearray[1][7]=f1;
        piecearray[3][7]=f2;
        piecearray[5][7]=f3;
        piecearray[7][7]=f4;
        }
    }

    public Piece pieceAt(int x, int y){
        if(x<=7 && y<=7 && x>=0 && y>=0){
            if(piecearray[x][y]==null){
                return null;
            }else{
                return piecearray[x][y];
            }
        }else{
            return null;
        }
    }

    public boolean canSelect(int x, int y){

        if (piecearray[x][y] != null) {
            if(((pieceAt(x,y).isFire()) && (!fire_turn)) || ((!pieceAt(x,y).isFire()) && (fire_turn))){
                return false;
            }
           if(selectX == 10){
                return true;
            }else if(!has_moved){
                return true;
            }else{
                return false;
            }
        }else {
            if((pieceAt(selectX,selectY)!=null) && (!has_moved) && (validMove(selectX,selectY,x,y))){
                return true;
            }else if((pieceAt(selectX,selectY)!=null) && (pieceAt(selectX,selectY).hasCaptured())&&(has_moved) && (validMove(selectX,selectY,x,y))){
                return true;
            }else{
                return false;
            }
    }
}   
                
        
        
    public void select(int x, int y){
        if(piecearray[x][y]!=null){
            selectX=x;
            selectY=y;
            select_piece = piecearray[x][y];
        }else{
            pieceAt(selectX,selectY).move(x,y);
            has_moved = true;
            selectX = x;
            selectY = y;
        }
    }


    public void place(Piece p,int x, int y){
        if(x<=8 && y<=8 && x>=0 && y>=0 && p!=null){
            piecearray[x][y]=p;
        }
    }


    public Piece remove(int x, int y){
        if(x<=8 && y<=8){
            if(pieceAt(x,y)==null){
                System.out.println("no piece");
                return null;
            }else{
                Piece piece_returned = pieceAt(x,y);
                piecearray[x][y]=null;
                return piece_returned;
            }
        }else{
            System.out.println("out of bound");
            return null;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        difX =xf-xi;
        difY =yf-yi;
        capX =xi+difX/2;
        capY =yi+difY/2;  
        if(Math.abs(difX)==2 && Math.abs(difY)==2){
            if(pieceAt(capX,capY)!=null && pieceAt(xi,yi)!=null){
                if(pieceAt(xi,yi).isFire()!=pieceAt(capX,capY).isFire()){
                    if(pieceAt(xi,yi).isKing()){
                        return true;
                    }else{
                        if(pieceAt(xi,yi).isFire()){
                            if(difY>0)
                                return true;
                            return false;
                        }else{
                            if(difY<0)
                                return true;
                            return false;
                        }
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }  

        else if(Math.abs(difX)==1 && Math.abs(difY)==1){     
            if(pieceAt(xi,yi)!=null){   
                if(pieceAt(xi,yi).hasCaptured()){
                    return false;
                }else{
                    if(pieceAt(xi,yi).isKing()){
                        return true;
                    }else{
                        if(pieceAt(xi,yi).isFire()){
                            if(difY>0)
                                return true;
                            return false;
                        }else{
                            if(difY<0)
                                return true;
                            return false;
                        }
                    }
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    

        
    public boolean canEndTurn(){
        if((pieceAt(selectX,selectY)==null)){
            if ((select_piece!=null) && (select_piece.isBomb())){
                return true;
            }else{
                return false;
            }
        }
        if(has_moved || (pieceAt(selectX,selectY).hasCaptured())){
                return true;
            }
        return false;
    }


    public void endTurn(){        
        if(fire_turn){
            fire_turn=false;
        }else{
            fire_turn=true;
        }
        selectX=10;
        selectY=10;
        has_moved=false;
        if(select_piece!=null){
            select_piece.doneCapturing();
        }
        select_piece = null;
    }

    public String winner(){
        int num_of_fire=0;
        int num_of_water=0;       
        for(int p=0;p<8;p++){
            for(int q=0;q<8;q++){
                if(piecearray[p][q]!=null){
                    if(piecearray[p][q].isFire()){
                        num_of_fire += 1;
                    }else{
                        num_of_water += 1;
                    }
                }
            }
        }
        if(num_of_water==0 && num_of_fire==0){
            return "No one";
        }else if(num_of_fire==0){
            return "Water";
        }else if(num_of_water==0){
            return "Fire"; 
        }else{
            return null;
        }
    }
}


