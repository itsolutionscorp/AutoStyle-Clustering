public class Board {
    /** Location of pieces. */
    private  Piece[][] pieceArray;
    private Piece selectedPiece = null;
    private int xsave = 10;
    private int ysave = 10;
    private int turn = 0;
    private int firecount = 0;
    private int watercount = 0;
    private boolean hasMoved = false;
  
 
    private String winnermark = null;

    public Board(boolean shouldBeEmpty){
        pieceArray = new Piece[8][8];
       if (shouldBeEmpty){
        return;
    }
    else{
   
        firecount = 12;
        watercount = 12;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

            //Fire Pieces
        if (j==2 && i % 2 ==0){
            pieceArray[i][j] = new Piece(true,this,i,j,"bomb");
    }
        if (j==1 && i % 2 !=0){
            pieceArray[i][j] = new Piece(true,this,i,j,"shield");
                    
                }
        if (j==0 && i % 2 ==0){
            pieceArray[i][j] = new Piece(true,this,i,j,"pawn");
                }

                //Water Pieces
         if (j==5 && i % 2 !=0){
            pieceArray[i][j] = new Piece(false,this,i,j,"bomb");
                }
        if (j==6 && i % 2 ==0){
            pieceArray[i][j] = new Piece(false,this,i,j,"shield");
                }
        if (j==7 && i % 2 !=0){
            pieceArray[i][j] = new Piece(false,this,i,j,"pawn");   
                }
                
            }
        }
    }

}
    
    private   void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0){
                 StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
             }
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);

                if(i==xsave && j == ysave){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

            if (pieceArray[i][j] != null){

                //Fire Pieces

                if (pieceArray[i][j].isBomb() && pieceArray[i][j].isFire() && pieceArray[i][j].isKing()==false ){
                     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
                if (pieceArray[i][j].isShield() && pieceArray[i][j].isFire()  && pieceArray[i][j].isKing()==false ){
                     StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                }

                if (pieceArray[i][j].isShield() == false && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isFire()  && pieceArray[i][j].isKing()==false){
                     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                }

                //Fire King

                if (pieceArray[i][j].isBomb() && pieceArray[i][j].isFire()  && pieceArray[i][j].isKing()){
                     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
                if (pieceArray[i][j].isShield() && pieceArray[i][j].isFire()  && pieceArray[i][j].isKing()){
                     StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                }
                
                if (pieceArray[i][j].isShield() == false && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isFire()  && pieceArray[i][j].isKing()){
                     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                }

                //Water Pieces
                if (pieceArray[i][j].isBomb() && pieceArray[i][j].isFire() == false  && pieceArray[i][j].isKing()==false){
                     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                }
                if (pieceArray[i][j].isShield() && pieceArray[i][j].isFire() == false  && pieceArray[i][j].isKing()==false){
                     StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                }
                if (pieceArray[i][j].isShield() == false && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isFire() == false  && pieceArray[i][j].isKing()==false){
                     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }

                //Water King

                 if (pieceArray[i][j].isBomb() && pieceArray[i][j].isFire()==false  && pieceArray[i][j].isKing()){
                     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                }
                if (pieceArray[i][j].isShield() && pieceArray[i][j].isFire()==false  && pieceArray[i][j].isKing()){
                     StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                }
                
                if (pieceArray[i][j].isShield() == false && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isFire()==false  && pieceArray[i][j].isKing()){
                     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                }
            }

            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (pieceArray[x][y] == null){
            return null;
        }
        else{
        return pieceArray[x][y];
    }

    }
    public boolean canSelect(int x, int y){
       // if ((playerturn==true && pieceArray[x][y].isFire()) || (playerturn == false && pieceArray[x][y].isFire() == false ){
    if (pieceArray[x][y]!= null && hasMoved == false){
        if (turn == 0){
            if (pieceArray[x][y].isFire()){
                return true;

            }
        }
        if (turn == 1){
            if (pieceArray[x][y].isFire()==false){
                return true;
        }
        
        }
    }
    else if (pieceArray[x][y] == null && selectedPiece != null) {
            if(!hasMoved){
                return validMove(xsave,ysave,x,y);
            }
            else{
                if(validMove(xsave,ysave,x,y)){
                    int delta = Math.abs(xsave-x);
                    if(delta == 2 && pieceArray[xsave][ysave].hasCaptured()){

                        return true;
                    }
                }
            }
    }
        return false;
    }

    
    private boolean validMove(int xi, int yi, int xf, int yf){
        //Fire
        if (pieceArray[xi][yi].isKing()==false){
            if (pieceArray[xi][yi].isFire()){
                if (xf - xi == 1 && yf - yi == 1 && pieceAt(xf,yf) == null){
            return true;
        }
                if (xf - xi == -1 && yf - yi == 1 && pieceAt(xf,yf) == null){
            return true;
        }
                 if (xf - xi == -2 && yf - yi == 2 && pieceAt(xi-1,yi+1) != null){
                 if(pieceArray[xi-1][yi+1].isFire() == false){
            return true;
        }
        }
                if (xf - xi == 2 && yf - yi == 2 && pieceAt(xi+1,yi+1) != null){
                if(pieceArray[xi+1][yi+1].isFire() == false){
            return true;
        }
        }
    }
         if (pieceArray[xi][yi].isFire()==false){
        //Water Pieces
        if (xf - xi == -1 && yf - yi == -1 && pieceAt(xf,yf) == null){
            return true;
        }
        if (xf - xi == 1 && yf - yi == -1 && pieceAt(xf,yf) == null){
            return true;
        }
        if (xf - xi == -2 && yf - yi == -2 && pieceAt(xi-1,yi-1) != null){
            if(pieceArray[xi-1][yi-1].isFire()){
            return true;
        }
        }
        if (xf - xi == +2 && yf - yi == -2 && pieceAt(xi+1,yi-1) != null){
            if(pieceArray[xi+1][yi-1].isFire()){
            return true;
        }
        }
    
}
}
else if (pieceArray[xi][yi].isKing()) {
                //king pieces
            if (xf - xi == -1 && yf - yi == -1 && pieceAt(xf,yf) == null){
            return true;
        }
        if (xf - xi == 1 && yf - yi == -1 && pieceAt(xf,yf) == null){
            return true;
        }
                if (xf - xi == 1 && yf - yi == 1 && pieceAt(xf,yf) == null){
            return true;
        }
                if (xf - xi == -1 && yf - yi == 1 && pieceAt(xf,yf) == null){
            return true;
        }
                 if (xf - xi == -2 && yf - yi == 2 && pieceAt(xi-1,yi+1) != null && pieceArray[xi][yi].isFire() ){
                 if(pieceArray[xi-1][yi+1].isFire() == false){
            return true;
        }
        }
                if (xf - xi == 2 && yf - yi == 2 && pieceAt(xi+1,yi+1) != null && pieceArray[xi][yi].isFire()){
                if(pieceArray[xi+1][yi+1].isFire() == false){
            return true;
        }
        }
        if (xf - xi == -2 && yf - yi == -2 && pieceAt(xi-1,yi-1) != null && pieceArray[xi][yi].isFire() ){
                 if(pieceArray[xi-1][yi-1].isFire() == false){
            return true;
        }
        }
                if (xf - xi == 2 && yf - yi == -2 && pieceAt(xi+1,yi-1) != null && pieceArray[xi][yi].isFire()){
                if(pieceArray[xi+1][yi-1].isFire() == false){
            return true;
        }
        }
         if (xf - xi == -2 && yf - yi == 2 && pieceAt(xi-1,yi+1) != null && pieceArray[xi][yi].isFire()  == false ){
                 if(pieceArray[xi-1][yi+1].isFire()){
            return true;
        }
        }
                if (xf - xi == 2 && yf - yi == 2 && pieceAt(xi+1,yi+1) != null && pieceArray[xi][yi].isFire()  == false){
                if(pieceArray[xi+1][yi+1].isFire()){
            return true;
        }
        }
        if (xf - xi == -2 && yf - yi == -2 && pieceAt(xi-1,yi-1) != null && pieceArray[xi][yi].isFire()==false ){
                 if(pieceArray[xi-1][yi-1].isFire()){
            return true;
        }
        }
                if (xf - xi == 2 && yf - yi == -2 && pieceAt(xi+1,yi-1) != null && pieceArray[xi][yi].isFire()==false){
                if(pieceArray[xi+1][yi-1].isFire()){
            return true;
        }
        }
    
}


        return false;
    }

    public void select(int x, int y){

        if (pieceArray[x][y] != null  ){ //if there's a piece here
            
            selectedPiece = pieceArray[x][y];
            hasMoved = false;
            xsave = x;
            ysave = y;
            

           // StdDrawPlus.filledSquare((double) x + 0.5, (double) y + 0.5, 0.5);
          //  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        }
        else{
            if (validMove(xsave,ysave,x,y) ){
                pieceArray[xsave][ysave].move(x,y);
                 int delta = Math.abs(xsave - x);
                xsave = x;
                ysave = y;
            hasMoved = true;
            selectedPiece = pieceArray[x][y];
           
            if (delta == 2 && pieceArray[xsave][ysave].isBomb()){
                System.out.println("helpp");
                selectedPiece = null;
                xsave = 10;
                ysave = 10;
                bombexplosion(x,y,pieceArray[x][y].isFire());
            }

        
        }
    
        }
    }
    

    public void place(Piece p, int x, int y){
        if (p!= null && bounds(x,y)==true){
        pieceArray[x][y] = p;
}
    }

    public Piece remove(int x, int y){
        if (pieceArray[x][y] == null){
            System.out.print("Cannot remove null");
            return null;

            //print an error message
        }
        else if(bounds(x,y)==false){
            System.out.print("Not within bounds");
            return null;
        }
        else{

            Piece tmp = pieceArray[x][y];

            pieceArray[x][y] = null;

            //StdDrawPlus.picture(2 + .5, 1 + .5, "img/bomb-water.png", 1, 1);
            return tmp;
        }
    }

    public boolean canEndTurn(){
        if (hasMoved == true){
            return true;
        }
        return false;
    }
    public void endTurn(){
        if (canEndTurn()){
            winner();
        hasMoved = false;
        selectedPiece = null;
        if (bounds(xsave,ysave) && pieceArray[xsave][ysave] != null){
            pieceArray[xsave][ysave].doneCapturing();
        }
        turn = (turn + 1) % 2;
        xsave = 10;
        ysave = 10;
    }
    }

    private boolean bounds(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    private void pieceCount(){
          firecount = 0;
          watercount = 0;
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if(pieceArray[i][j]!= null){
                    if(pieceArray[i][j].isFire()){

                        firecount ++;
                        
                    }
                    else{
                        watercount ++;
                      
                    }
                }
            }
        }
    }

    private void bombexplosion(int x, int y, boolean isFire) {
    for(int i=x-1;i<=x+1; i++) {
        for(int j=y-1; j<=y+1; j++) {
            if (bounds(i,j) && pieceArray[i][j] != null && pieceArray[i][j].isShield() == false ) {
                System.out.println("help");
                remove(i, j);
            }
        }
    }
    remove(x,y);
}


    public String winner(){
        pieceCount();
        if(firecount == 0 && watercount==0){
            winnermark = "No one";
        }
        else if(watercount == 0){
            winnermark = "Fire";
        }
        else if(firecount == 0){
            winnermark = "Water";
        }
        else{
            winnermark = null;
        }
        
        return winnermark;
    }


    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
    Board myBoard = new Board(false);
        while(true) {
            
            
            myBoard.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (myBoard.bounds((int) x, (int) y) && myBoard.canSelect((int) x, (int) y)){
                myBoard.select((int) x, (int) y);
                    }
            }

            if (StdDrawPlus.isSpacePressed()){
                if (myBoard.canEndTurn()){
                    myBoard.endTurn();
                }
            }
            StdDrawPlus.show(100);
        }
    }
}
