public class Board {

    private static int N = 8;
    private static Board b;
    private static Piece selected;
    private static int selectX;
    private static int selectY;
    private static boolean captureCount;
    private static boolean moved;
    private static int who;
    private static int[][] pieces;
    private static Piece[][] piecearray;

    public static void main (String args[]){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        piecearray=new Piece[N][N];
        pieces = new int[N][N];
        who=0; //side
        captureCount=false;
        moved=false;
        Board b=new Board(false);
        while(b.winner()==null) {
            if (StdDrawPlus.mousePressed()) {
                System.out.println("pressed");
                int m_X = (int)StdDrawPlus.mouseX();
                int m_Y = (int)StdDrawPlus.mouseY();
                System.out.println("pressed: x " + m_X + " y  " + m_Y);
                if (b.canSelect(m_X,m_Y)){
                    b.select(m_X,m_Y);  
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if (b.canEndTurn()){
                    b.endturn();
                }
            }            
            StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
    //starts GUI version of game    
    }

    public Board (boolean shouldBeEmpty){
        if (shouldBeEmpty==true){
        //initialize empty board
            drawEmptyBoard();
        }
        else{
            drawBoard();
        //initialize regular board
        }
    }

    private static void other(){
        who=1-who;
    }

    private static void drawEmptyBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private void drawWhite(int i, int j) {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        drawPic(i, j);
    }

    private void drawPic(int x, int y){
        Piece current = piecearray[x][y];

        if (who==0 && current != null){
            if(current.isBomb()){
                if(current.isKing()){
                    StdDrawPlus.picture(x+.5,y+.5,"img/bomb-fire-crowned.png",1,1);
                } else{
                StdDrawPlus.picture(x+.5,y+.5,"img/bomb-fire.png",1,1);
                }
            } else if(current.isShield()){
                if(current.isKing()){
                    StdDrawPlus.picture(x+.5,y+.5,"img/shield-fire-crowned.png",1,1);
                } else{
                StdDrawPlus.picture(x+.5,y+.5,"img/shield-fire.png",1,1);
                }
            } else if(pieces[x][y]==0){
                if(current.isKing()){
                    StdDrawPlus.picture(x+.5,y+.5,"img/pawn-fire-crowned.png",1,1);
                } else{
                StdDrawPlus.picture(x+.5,y+.5,"img/pawn-fire.png",1,1);
                }
            }
        } else if (who==1 && current != null){ 
            if(current.isBomb()){
                if(current.isKing()){
                    StdDrawPlus.picture(x+.5,y+.5,"img/bomb-water-crowned.png",1,1);
                } else{
                StdDrawPlus.picture(x+.5,y+.5,"img/bomb-water.png",1,1);
                }
            } else if(current.isShield()){
                if(current.isKing()){
                    StdDrawPlus.picture(x+.5,y+.5,"img/shield-water-crowned.png",1,1);
                } else{
                StdDrawPlus.picture(x+.5,y+.5,"img/shield-water.png",1,1);
                }
            } else if(pieces[x][y] == 1){
                if(current.isKing()){
                    StdDrawPlus.picture(x+.5,y+.5,"img/pawn-water-crowned.png",1,1);
                } else{
                StdDrawPlus.picture(x+.5,y+.5,"img/pawn-water.png",1,1);
                }
            }
        }  
    }  

    private void redraw(int x, int y){
        if ((x + y) % 2 == 0) {
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        } else {
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
        }
        StdDrawPlus.filledSquare(x+.5,y+.5,.5);

        drawPic(x, y);  
    }
    
    private void explode(int x, int y){
        pieces[selectX][selectY] = 2;
        piecearray[selectX][selectY] = null;
        redraw(selectX, selectY);
        int xmax = (x+1 <= 7)? x+1:7;
        int xmin = (x-1 >= 0)? x-1:0;
        int ymax = (y+1 <= 7)? y+1:7;
        int ymin = (y-1 >= 0)? y-1:0;

        for (int i = xmin; i <= xmax; i++) {
            for (int j = ymin; j <= ymax; j++) {
                if(pieces[i][j] != 2 ){
                    Piece current = piecearray[i][j];
                    if( ! current.isShield()){
                        pieces[i][j] = 2;
                        piecearray[i][j] = null;
                        redraw(i,j);
                    }
                }
            }
        }
    }

    private void do_capture(int x, int y){
        captureCount=true;
        selected.move(-1,-1);

        Piece tmp = piecearray[(x+selectX)/2][(y+selectY)/2];
        Piece ttt = piecearray[selectX][selectY];
        if( tmp.isBomb() && ttt.isBomb()){
           explode(x, y);
        } else {
            //move to
            Piece t = remove(selectX,selectY);
            place(t, x, y); 
            if ((who == 0 && y == 7) || (who == 1 && y == 0)){
                selected.move(8,8);
            }
            //capture
            pieces[(x+selectX)/2][(y+selectY)/2] = 2;
            piecearray[(x+selectX)/2][(y+selectY)/2] = null;
            redraw((x+selectX)/2, (y+selectY)/2);
            selected=piecearray[x][y];
            selectX=x;
            selectY=y;

            //continue to capture if OK
            if(canSelect(x-2, y-2)){
                do_capture(x-2,y-2);
            } else if(canSelect(x+2, y+2)){
                do_capture(x+2,y+2);
            } else if(canSelect(x-2, y+2)){
                do_capture(x-2,y+2);
            } else if(canSelect(x+2, y-2)){
                do_capture(x+2,y-2);
            }
        }
    }

    private static void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
            for (int k=0;k<8;k++){
                pieces[i][k] = 2;
                if (((i+k)%2==0 && k!=3)&& k!=4){
                    if (k==0){
                        piecearray[i][k]=new Piece(true,b,i,k,"pawn");
                        StdDrawPlus.picture(i+.5,k+.5,"img/pawn-fire.png",1,1);
                        pieces[i][k] = 0;
                    }
                    if (k==1){
                        piecearray[i][k]=new Piece(true,b,i,k,"shield");
                        StdDrawPlus.picture(i+.5,k+.5,"img/shield-fire.png",1,1);
                        pieces[i][k] = 0;
                    }
                    if (k==2){
                        piecearray[i][k]=new Piece(true,b,i,k,"bomb");
                        StdDrawPlus.picture(i+.5,k+.5,"img/bomb-fire.png",1,1);
                        pieces[i][k] = 0;
                    }
                    if (k==5){
                        piecearray[i][k]=new Piece(false,b,i,k,"bomb");
                        StdDrawPlus.picture(i+.5,k+.5,"img/bomb-water.png",1,1);
                        pieces[i][k] = 1;
                    }
                    if (k==6){
                        piecearray[i][k]=new Piece(false,b,i,k,"shield");
                        StdDrawPlus.picture(i+.5,k+.5,"img/shield-water.png",1,1);
                        pieces[i][k] = 1;
                    }
                    if (k==7){
                        piecearray[i][k]=new Piece(false,b,i,k,"pawn");
                        StdDrawPlus.picture(i+.5,k+.5,"img/pawn-water.png",1,1);
                        pieces[i][k] = 1;
                    }  
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        //Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
        //Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
       if (((x>=0)&&(x<N))&&((y>=0)&&(y<N))&&(pieces[x][y] == 0 || pieces[x][y] == 1)){
            return piecearray[x][y];
        }
        else{
            return null;
        }
    }

    public boolean canSelect(int x, int y){
        if (pieceAt(x,y)!=null){
            //destination has a piece
            if (pieces[x][y] == who && (selected==null||moved==false)){
                //initial selection - select its own side piece
                //who is the current player 0, or 1
                return true;
            }
        }
        else{
            //select an empty place
            if(selected != null && moved==false && validMove(selectX,selectY,x,y)){
                return true;
            }
            else{
                if(selected!=null&&selected.hasCaptured()&&validMove(selectX,selectY,x,y)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        //king can move backward, fire move up, water move down
        //need to check if yi < yf
        int xj=Math.abs(xf-xi);
        int yj=Math.abs(yf-yi);
        if ((xi>=0&&xi<N)&&(xf>=0&&xf<N)&&(yi>=0&&yi<N)&&(yf>=0&&yf<N)) {
            if ((xj==1) && (yj==1)){
                if((who == 0 && (yf - yi) > 0) || (who == 1 && (yf - yi) < 0) || selected.isKing()){
                    return true;
                }
            }
            else{
                if ((xj==2) && (yj==2)){
                    if((who == 0 && (yf - yi) > 0) || (who == 1 && (yf - yi) < 0) || selected.isKing()){
                        if((who == 0 && pieces[(xi+xf)/2][(yi+yf)/2] == 1 && pieces[xf][yf] == 2) ||
                           (who == 1 && pieces[(xi+xf)/2][(yi+yf)/2] == 0 && pieces[xf][yf] == 2) ){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y){
        if(canSelect(x,y)){ 
            if(pieceAt(x,y)!=null){ 
                if (selected==null||(selected!=null && moved==false)){
                    //prepare to move - initial selection
                    selected=piecearray[x][y];
                    selectX=x;
                    selectY=y;
                    drawWhite(x,y);
                }
            }
            else{
                //move to empty place or capture
                if (Math.abs(y - selectY) == 2){
                    // select to capture
                    do_capture(x, y);
                    endturn();
                } else if (Math.abs(y - selectY) == 1){
                    // select to move
                    Piece tmp = remove(selectX,selectY);
                    place(tmp, x, y);
                    if ((who == 0 && y == 7) || (who == 1 && y == 0)){
                        selected.move(8,8);
                    }
                    selected=piecearray[x][y];
                    selectX=x;
                    selectY=y;
                    endturn();

                //move to empty place or capture
                //if (Math.abs(y-selectY)==2){
                    // select to capture
                    //selected.move(x, y);
                    //selected=piecearray[x][y];
                //} else { 
                    //// select to move
                    //selected.move(x,y);
                    //selected=piecearray[x][y];
                }
            }
        }
    }

    public void place(Piece p, int x, int y){
        if (((x>=0)&&(x<N))&&((y>=0)&&(y<N))&&(p!=null)&&(selectX!=x&&selectY!=y)){
            pieces[x][y]=pieces[selectX][selectY];
            piecearray[x][y]=p;
            piecearray[selectX][selectY] = null;
            pieces[selectX][selectY] = 2;
            if ((who==0 && y==7)||(who==1 && y==0)){
                selected.move(8,8);
            }
            redraw(selectX, selectY);
            redraw(x,y);
            moved=true;
        }
    }

    public Piece remove(int x, int y){
        if(x<0||y>8){
                System.out.println("Out of bounds");
                return null;
        }
        if(pieceAt(x,y)==null){
                System.out.println("No piece at spot");
                return null;
        }
         else{
            Piece a = piecearray[x][y];
            return a;
        }
    }

    public boolean canEndTurn(){
        if ((moved==true)||(captureCount==true)){
            return true;
        }
        else{
            return false;
        }
    }

    public void endturn(){
        moved=false;
        captureCount=false;
        if (selected != null){
            selected.doneCapturing();
            selected=null;
        }
        other();
        //if can end turn return true
        //if winner end turn
        //if no one can move
    }

    public String winner(){
        int numf = 0;
        int numw = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if (pieces[i][j] == 0){
                    numf=numf +1;
                } else if (pieces[i][j] == 1){
                    numw = numw +1;
                }
            }
        }
        if( numw==0 && numf==0){
            return "No one";
        } else if(numw==0){
            return "fire";
        } else if(numf==0){
            return "water";
        } else {
            return null;
        }   
    }
}