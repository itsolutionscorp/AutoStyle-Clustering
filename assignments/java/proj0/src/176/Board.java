public class Board {
    private Piece[][] pieces = new Piece[8][8];
    private int taketurn = 0;
    private boolean hasselected = false;
    private boolean hasmoved = false;
    private int fireleft = 0;
    private int waterleft = 0;
    private int selectX;
    private int selectY;
    private int movetoX;
    private int movetoY;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty!=true) {
        pieces = new Piece[8][8];
        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
        pieces[0][2] = new Piece(true, this, 2, 0,"pawn");
        pieces[0][4] = new Piece(true, this, 4, 0, "pawn");
        pieces[0][6] = new Piece(true, this, 6, 0, "pawn");
        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
        pieces[1][3] = new Piece(true, this, 3, 1, "shield");
        pieces[1][5] = new Piece(true, this, 5, 1, "shield");
        pieces[1][7] = new Piece(true, this, 7, 1, "shield");
        pieces[2][0] = new Piece(true, this, 0, 2, "bomb");
        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        pieces[2][4] = new Piece(true, this, 4, 2, "bomb");
        pieces[2][6] = new Piece(true, this, 6, 2, "bomb");
        pieces[7][1] = new Piece(false, this, 1,7,"pawn");
        pieces[7][3] = new Piece(false, this, 3,7,"pawn");
        pieces[7][5] = new Piece(false, this, 5,7,"pawn");
        pieces[7][7] = new Piece(false, this, 7,7,"pawn");
        pieces[6][0] = new Piece(false, this, 0,6,"shield");
        pieces[6][2] = new Piece(false, this, 2,6,"shield");
        pieces[6][4] = new Piece(false, this, 4,6,"shield");
        pieces[6][6] = new Piece(false, this, 6,6,"shield");
        pieces[5][1] = new Piece(false, this, 1,5,"bomb");
        pieces[5][3] = new Piece(false, this, 3,5,"bomb");
        pieces[5][5] = new Piece(false, this, 5,5,"bomb");
        pieces[5][7] = new Piece(false, this, 7,5,"bomb");
        }
    }

    public static void main(String[] args) {  
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        b.drawBoard(8);

        while(true) {
            if (StdDrawPlus.mousePressed()) {
                double xx = StdDrawPlus.mouseX();
                double yy = StdDrawPlus.mouseY();
                int x = (int) xx;
                int y = (int) yy; 
                if (b.pieceAt(x, y) == null) {
                    if (b.canSelect(x, y)) {
                        b.select(x, y);
                        b.hasselected=true;
                        b.drawBoard(8);
                    }
                }
            
                if (StdDrawPlus.isSpacePressed()) {
                   if (b.canEndTurn()) {
                        if (b.winner()!=null){
                            b.winner();
                        }
                        b.endTurn();
                    }
                }
            }
            StdDrawPlus.show(100);
        }
    }

    private boolean testifisinbound(int x, int y){
        return ((x>=0)&&(x<8)&&(y>=0)&&(y<8));
    }

    public Piece pieceAt(int x, int y) { 
        if (!testifisinbound(x,y)){
            return null;
        }
        else {
            return pieces[y][x];
        }
    }

    public void place(Piece p, int x, int y) {
        if (!(testifisinbound(x,y))||(p==null)){
            return;
        }
        if ((testifisinbound(x,y))&&(pieceAt(x, y)!= null)){
            remove(x,y);
            pieces[y][x]=p;
            movetoX=x;
            movetoY=y;
        }
        else{
            pieces[y][x]=p;
            movetoX=x;
            movetoY=y;
        }
    }

    public Piece remove(int x, int y) {
        Piece removepiece=pieceAt(x, y);
        if ((!testifisinbound(x,y))||(removepiece==null)){
            String printwhenoutofbound = "ERROR: out of bound or no piece.";
            System.out.println(printwhenoutofbound);
            return null;
        }
        else{
            pieces[y][x] = null;
            return removepiece;
        }
    }

    public boolean canEndTurn() {
        if (hasmoved){
            return true;
        }

        else {
            return false;
        }
    }

    public void endTurn(){
        if (pieceAt(movetoX,movetoY)!=null) {
            pieces[movetoY][movetoX].doneCapturing();
        }
        hasmoved=false;
        hasselected=false;
        if (taketurn==0){
            taketurn=1;
        }
        else{
            taketurn=0;
        }
    } 

    public String winner(){
        for (int i=0; i<8; i++){
            for (int j = 0; j < 8; j++){
                if (pieces[i][j]!=null){
                    if (pieces[i][j].isFire()==true){
                        fireleft=fireleft+1;
                    }
                    if (pieces[i][j].isFire()==false){
                        waterleft=waterleft+1;
                    }
                }
            }
        }
        if ((waterleft==0) && (fireleft>0) ){
            return "Fire";
        }
        else if ((fireleft==0)&& (waterleft>0)){

            return "Water";
        }
        else if ((waterleft==0)&&(fireleft==0)){

            return "No one";
        }
        else{
            return null;
        }
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if ((pieceAt(xf,yf)==null) && (pieceAt((xi+xf)/ 2,(yi+yf)/ 2)!= null)
            &&((pieceAt(xi,yi).side()!= pieceAt((xi+xf)/2,(yi+yf)/2).side()))){
                // king
            if ((pieceAt(xi,yi).isKing())&& ((Math.abs(xi-xf)==2) && (Math.abs(yi-yf)==2))){
                return true;
            }
                //fire,up.
            if ((pieceAt(xi, yi).isFire())&&((Math.abs(xi-xf) == 2) && (yf-yi==2))) {
                return true;
            }
                // water,down.
            else{
                if ((Math.abs(xi-xf) == 2) && (yi-yf==2)) {
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf>7||yf >7||xf<0||yf<0) {
            return false;
        }
        if (pieceAt(xf, yf) == null){
            if (pieceAt(xi, yi).isKing()) {
                if (((Math.abs(xi-xf)==1) && (Math.abs(yi-yf) == 1))) {
                    return true;
                }
                if (((Math.abs(xi-xf) == 2) && (Math.abs(yi-yf) == 2)) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    return true;
                }
            }
            if (pieceAt(xi, yi).isFire()) {
                if ((Math.abs(xi-xf) == 1) && (yf-yi==1)){
                    return true;
                }
                if ((((Math.abs(xi-xf) == 2) && (yf-yi==2)) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null))
                  &&((pieceAt(xi, yi).side() != pieceAt((xi+xf)/2, (yi+yf)/2).side()))) {
                    return true;
                } 
            }
            else {
                if ((Math.abs(xi-xf) == 1) && (yi-yf==1)) {
                    return true;
                }
                if (((Math.abs(xi-xf) == 2) && (yi-yf==2))&&(pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    return true;  
                }
            }
        }
        return false;
    }            
     
    public boolean canSelect(int x, int y) {
        if (!testifisinbound(x,y)) {
            return false;
        }
        if (pieceAt(x,y) == null) {
            if (((!hasmoved)&&(hasselected))&&validMove(selectX, selectY, x, y)) {
                    return true;
            }
            if ((hasselected)&&(hasmoved)&&(pieceAt(movetoX, movetoY)!= null)
               && (pieceAt(movetoX,movetoY).hasCaptured()) 
               && (validCapture(movetoX, movetoY, x, y))){
                    return true;
            }
            else {
                return false;
            }    
        } 
        if (taketurn==pieceAt(x,y).side()) {
            if (hasselected==false) {
                return true;
            }
            if ((hasselected==true) && (hasmoved==false)) {
                return true;                
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
  
    public void select(int x, int y) {
        if (pieceAt(x,y)!=null){
            hasselected=true;
            selectX=x;
            selectY=y;
        }
        else{
            pieceAt(selectX,selectY).move(x,y);
            hasmoved=true;
            hasselected = true;
            selectX=x;
            selectY=y;
        }
    }
    
    private void drawBoard(int N) {
        String picture; 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2==0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[j][i] != null) {
                    if (pieces[j][i].isBomb()){
                        picture = "bomb";
                    }
                    if (pieces[j][i].isShield()){
                        picture = "shield";
                    }
                    else{
                        picture = "pawn";
                    }
                    if (pieces[j][i].isFire()){
                        picture += "-fire";
                    }
                    if (!pieces[j][i].isFire()){
                        picture += "-water";
                    }
                    if (pieces[j][i].isKing()){
                        picture = picture+"-crowned";
                    }
                    StdDrawPlus.picture(i + .5,j + .5, "img/"+picture+".png",1, 1);
                }
            }
        }
    }

}
