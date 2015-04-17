public class Board{
	private  Piece [][] piece;
    private boolean firesturn=true;
	private int boundry=8;
    private boolean moved;
    private boolean captured;
    private Piece spiece;
    private int selectx;
    private int selecty;
	
    public Board (boolean shouldBeEmpty) {
        piece= new Piece [8][8];
        String type = "pawn";
        boolean isFire;
        for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) 
                        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                 
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    
        if (shouldBeEmpty==false){

            if (j>4 && j<boundry){
                isFire=false;
            } 
            else {
                isFire=true;
            }
            if (j==0 || j==7){
                type="pawn";
            }
            if (j==1|| j==6){
                type="shield";
            }
            if (j==2 || j==5){
                type="bomb";
            }
           piece [i][j] =new Piece (isFire, this, i, j, type);
        }
        if ((i+j)%2==1){
            piece[i][j]=null;
        }
        else if (j<5 && j>2){
            piece[i][j]=null;
        }
    }
    }
    }
    public Piece pieceAt(int x, int y) {
        if (x>7 || y>7 || x<0 || y<0){
            return null;
        }
        else
            return piece[x][y];
    }
    public boolean canSelect(int x, int y){
        if ((x+y)%2==1){
            return false;
        }
        if (pieceAt(x,y)!=null && pieceAt(x,y).isFire()!=firesturn){
            return false;
        }
        if (spiece==null){
            if (piece[x][y]!=null && moved==false && piece[x][y].isFire()==firesturn){
                return true;
            }
            else {
                return false;
            }
        }
        
        else{

            if (moved==false ){
                if (pieceAt(x,y)!=null && spiece.isFire()==firesturn){
                    return true;
                }
                else if (canmove1(selectx,selecty,x,y)||cancap(selectx,selecty,x,y)){
                return true;
                }
                else {
                    return false;
                }
            }
            else if (moved==true && captured ==false){
                return false;
            }
            else if (moved==false && canmove1(selectx,selecty,x,y) ){
                return true;
            }
            else if (captured && cancap(selectx,selecty,x,y)){
                return true;
            }
            else{
                return false;
            } 
        }

    }

    private boolean cancap(int a, int b, int c, int d){
        if (moved==true && captured==false){
            return false;
        }
        if (a>7||b>7||c>7||d>7||a<0||b<0||c<0||d<0){
            return false;
        }
        if (Math.abs(a-c)!=2 ||Math.abs(b-d)!=2){
            return false;
        }
        if (piece[c][d]!=null){
            return false;
        }
        int e=(a+c)/2;
        int f=(b+d)/2;
        if (pieceAt(e,f)==null){
            return false;
        }

        if (pieceAt(a,b).isKing()){
            if (pieceAt(a,b).isFire()!=pieceAt(e,f).isFire()){
            return true;
            }
        }
        if (pieceAt(a,b).isFire()==pieceAt(e,f).isFire()){
            return false;
        }
         else if (pieceAt(a,b).isFire()){
            if (pieceAt(a,b).isKing()==false||pieceAt(a,b).isFire()!=pieceAt(e,f).isFire() && f>b){
                return true;
            }
         }
         else if (pieceAt(a,b).isFire()==false){
            if (pieceAt(a,b).isKing()==false||pieceAt(a,b).isFire()!=pieceAt(e,f).isFire() && f<b){
                return true;
            }
         }
         else{
            return false;
         }
         return false;
    }

    private boolean canmove1 (int a, int b, int c, int d){
        if (moved==true && captured==false){
            return false;
        }
        if (a>7||b>7||c>7||d>7||a<0||b<0||c<0||d<0){
            return false;
        }
        if (a==c||b==d){
            return false;
        }
        Piece mp=pieceAt(a,b);
        if (mp==null){
            return false;
        };
        if (pieceAt(c,d)!=null){
            return false;
        }
        if (cancap(a,b,c,d)){
            return true;
        }
        if (Math.abs(a-c)!=1 ||Math.abs(b-d)!=1){
            return false;
        }
        if (mp.isKing()){
            if (Math.abs(a-c)==1 && Math.abs(b-d)==1){
                return true;
            }
            else {
                return false;
            }
        }
        if(mp.isKing()==false){
            if (mp.isFire()){
               if (Math.abs(a-c)==1 && Math.abs(b-d)==1 && d>b){
                return true;
               }  
               else{
                return false;
               }
            }
            else if (mp.isFire()==false){
                if (Math.abs(a-c)==1 && Math.abs(b-d)==1 && d<b){
                return true;
               } 
            else{
                return false;
            }

            }
        else {
            return false;
        }    
        }
        return false;

    }


    public void select(int x, int y){
       if (pieceAt(x,y)!=null){
        spiece=pieceAt(x,y);
        selectx=x;
        selecty=y;
        }
       else{
        spiece.move(x,y);
        moved=true;
        if (Math.abs(selectx-x)==2 && Math.abs(selecty-y)==2){
            captured=true;
        }
        else{
            captured=false;
        }
        selectx=x;
        selecty=y;
       }
    }

    public void place(Piece p, int x, int y){
        if ( x>7 || y>7 || x<0 || y<0 || p==null){
            return;
        }
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if (p==piece[i][j]){
                    piece[i][j]=null;
                }
            }
        }
        if (p!=null){
           piece[x][y]=p;
        }

    }

    public Piece remove(int x, int y){

        if (x>7 || y>7 || x<0 || y<0){
            System.out.println("remove out of bounce");
            return null;   
        }
        else if (piece[x][y]==null){
            System.out.println("no piece at position");
            return null;
        }
        else {
            Piece p=piece [x][y];
            piece [x][y]=null;
            return p;
        }

    }

    public boolean canEndTurn(){
        if (moved==true){
            return true;
        }
        else {
            return false;
        }
    }
    public void endTurn(){
        if (pieceAt(selectx,selecty)!=null){
            pieceAt(selectx,selecty).doneCapturing();
        }
            firesturn=!firesturn;
            moved=false;
            captured=false;
            spiece=null;
    }

    public String winner(){
        int numfire=0;
        int numwater=0;
        for (int x=0; x<boundry; x++){
            for (int y=0; y<boundry;y++){
                if (piece[x][y]!=null&&piece[x][y].isFire()){
                    numfire++;
                }
                if (piece[x][y]!=null&&piece[x][y].isFire()==false){
                    numwater++;
                }
                
            }
        }
        if (numfire==0 && numwater!=0){
            return "Water";
        }
        else if (numwater==0 && numfire!=0){
            return "Fire";
        }
        else if (numwater==0 && numfire==0){
            return "No one";
        }
       else {
            return null;
       }
    
}



private void drawBoard(int N) { //using someone else's drawBoard for debugging
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                  if (spiece != null){
                    if (selectx == i && selecty == j){
                      StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                  }
                }
                
                else {
                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (piece[i][j]!=null){
                    if (piece[i][j].isFire()){
                        if (piece[i][j].isShield()){
                            if (piece[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1,1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1,1);
                        }
                        else if (piece[i][j].isBomb()){
                            if (piece[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1,1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1,1);
                        }
                        else{
                            if (piece[i][j].isKing()) {
                               StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1,1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1,1);
                        }                        
                    }
                    else { //waterside
                        if (piece[i][j].isShield()){
                            if (piece[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1,1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1,1);
                        }
                        else if (piece[i][j].isBomb()){
                            if (piece[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1,1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1,1);
                        }
                        else {
                            if (piece[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1,1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1,1);
                        }                        
                    }
                }
            }
        }
    }

   public static void main(String[] args) {
    int mouseX;
    int mouseY;
    StdDrawPlus.setXscale(0,8);
    StdDrawPlus.setYscale(0,8);
    Board b = new Board(false);
        while(b.winner() == null){
          b.drawBoard(8);
          if (StdDrawPlus.mousePressed()){
            mouseX = (int) StdDrawPlus.mouseX();
            mouseY = (int) StdDrawPlus.mouseY();
            if(b.canSelect(mouseX, mouseY)){
              b.select(mouseX, mouseY);
            }
          }
          if(StdDrawPlus.isSpacePressed()){
            if(b.canEndTurn()){
              b.endTurn();
            }
          }
          StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
    }
}