public class Board {
    private Piece[][] pieceList= new Piece[8][8];
    private int turn=0;
    private Piece selectedPiece=null;
    private boolean moved=false;
    private boolean captured=false;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){
                 StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                 if (selectedPiece==pieceList[i][j] && selectedPiece!=null){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                 }
             }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceList[i][j]!=null){
                    String name="img/";
                    name+=this.getType(pieceList[i][j]);
                    name+="-";
                    name+=this.getTeam(pieceList[i][j]);
                    if (pieceList[i][j].isKing()){
                        name+="-crowned";
                    }
                    name+=".png";
                    StdDrawPlus.picture(i + .5, j + .5, name, 1, 1);
                }
                }
            }
        }
    
    private String getType(Piece p){
        if (p.isBomb()){
            return "bomb";
        }
        if (p.isShield()){
            return "shield";
        }

        if (!p.isKing()){
            return "pawn";
        }
        else{
            return "king";
        }
    
    }

    private String getTeam(Piece p) {
        if (p.side()==0){
            return "fire";
        }
        if (p.side()==1){
            return "water";
        }
        return null;
    }

    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty==false){
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    if (i%2==0 && j==0) {
                    pieceList[i][j]=new Piece(true, this, i, j, "pawn");
            }
                if (i%2!=0 && j==1) {
                    pieceList[i][j]=new Piece(true, this, i, j, "shield");
                    
            }
                if (i%2==0 && j==2) {
                    pieceList[i][j]= new Piece(true, this, i, j, "bomb");
            }
                if (i%2!=0 && j==5) {
                    pieceList[i][j]= new Piece(false, this, i, j, "bomb");
                 
                   
            }
                if (i%2==0 && j==6) {
                    pieceList[i][j]= new Piece(false, this, i, j, "shield");
                    
                   
            }
                if (i%2!=0 && j==7) {
                    pieceList[i][j]= new Piece(false, this, i, j, "pawn");                    
            }
            }
        }
    }
    else{
        return;
    }
}
    private int getXCoord(Piece p){
        for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    if (pieceList[j][i] == p){
                        return j;
                    }
                }
            }
            return 0;
        }

    private int getYCoord(Piece p){
           for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    if (pieceList[j][i]== p){
                        return i;
                    }
    }
}
return 0;
}

    public Piece remove(int x, int y){
        Piece newPiece= pieceList[x][y];
        if (x>8 || y>8 || pieceList[x][y]==null){
            System.out.println("Invalid move");
        }
        if (pieceList[x][y]!=null){
            pieceList[x][y]=null;
        }
        return newPiece;
    }

     public Piece pieceAt(int x, int y){
        if (x>7 || y>7 || x<0 || y<0){
            return null;
        }
        if (pieceList[x][y]!=null){
            return pieceList[x][y];

        }
        else{
            return null;
        }
    }

        public void place(Piece p, int x, int y){
        if (p==null || x>8 || y>8 || x<0 || y<0){
            System.out.println("Cant do that");
        }
         for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    if (pieceList[i][j]==p){
                        pieceList[i][j]=null;
                    }
                }
            }
            pieceList[x][y]=p;
        }

    public boolean canEndTurn(){
        if (selectedPiece==null){
            return false;
        }
        if (moved==true || captured==true){
            return true;
        }
        else{
            return false;
        }
    }

    public void endTurn(){
            moved=false;
            captured=false;
            selectedPiece=null;
        if (turn==1){
            turn=0;

        }
        else{
            turn=1;

        }
}


    public boolean canSelect(int x, int y){
        if (pieceList[x][y]==null){
            if ((x+y)%2==0){
            if (selectedPiece != null) {
           if (pieceList[getXCoord(selectedPiece)][getYCoord(selectedPiece)].side()==turn){
            if (selectedPiece != null && moved == false && validMove(getXCoord(selectedPiece),getYCoord(selectedPiece),x,y)){
                return true;
            }

            if (moved==true && selectedPiece.hasCaptured()==true && validMove(getXCoord(selectedPiece),getYCoord(selectedPiece),x,y)) {
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
}
else{
    if (turn==pieceList[x][y].side() && (selectedPiece==null || (selectedPiece!=null && moved==false))){
        return true;
    }
}
return false;
}


    private boolean validMove(int xi, int yi, int xf, int yf){
        if (xf>=8 || yf>=8 || xi<0 || yi<0){
            return false;
        }
        Piece pi= pieceList[xi][yi];
        Piece pf= pieceList[xf][yf];
        if (pi==null && pf==null){
            return false;
        }
        if (pi!=null && pf==null){
            if (pi.isKing()){
                if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){
                if (moved==true){
                    return false;
                }
                if (getTeam(pi)=="fire"){
                    if (Math.abs(xi-xf)==1){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                if (getTeam(pi)=="water"){
                   if(Math.abs(xi-xf)==1){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
            if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){
                if (pieceList[(xi+xf)/2][(yi+yf)/2]==null){
                    return false;
                }
                if (turn==0){
                    if (pieceList[(xi+xf)/2][(yi+yf)/2].isFire()==false) {
                        return true;
                    }
                }
                if (turn==1){
                    if (pieceList[(xi+xf)/2][(yi+yf)/2].isFire()==true) {
                        return true;
            }
        }
    }
    else{
    return false;
    }
}
            if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){
                if (selectedPiece.hasCaptured()==true){
                    return false;
                }
                if (getTeam(pi)=="fire"){
                    if ((xf>xi && yf>yi) || (xf<xi && yf>yi)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                if (getTeam(pi)=="water"){
                    if ((xf>xi && yf<yi) || (xf<xi && yf<yi)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
            if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){
                if (pieceList[(xi+xf)/2][(yi+yf)/2]==null){
                    return false;
                }
                if (turn==0){
                    if ((yf-yi==2) && pieceList[(xi+xf)/2][(yi+yf)/2].isFire()==false) {
                        System.out.println("valid");
                        return true;
                    }
                }
                if (turn==1){
                    if ((yi-yf==2) && pieceList[(xi+xf)/2][(yi+yf)/2].isFire()==true) {
                        return true;
            }
        }
    }
    else{
    return false;
    }
}
return false;
}


    public void select(int x, int y){
    if (pieceList[x][y]==null && selectedPiece!=null){
        selectedPiece.move(x,y);
        moved=true;
     }
    if (pieceList[x][y]!=null){
        selectedPiece=pieceList[x][y];
     }
 }


    public String winner(){
        int fireCounter=0;
        int waterCounter=0;
        for (int counter=0; counter<8; counter++){
            for (int counter2=0; counter2<8; counter2++){
                if (pieceList[counter][counter2]!=null){
                    if (pieceList[counter][counter2].isFire()==true){
                        fireCounter=fireCounter+1;
                    }
                    if (pieceList[counter][counter2].isFire()==false){
                        waterCounter=waterCounter+1;
                    }
                }
            }
        }
        if (fireCounter==0 && waterCounter==0){
            return "No one";
        }
        if (fireCounter==0){
            return "water";
        }
        if (waterCounter==0){
            return "fire";
        }
        return null;
    }

        public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        boolean w= Boolean.valueOf(args[0]);
        Board b= new Board(w);
        while(b.winner()==null) {
            b.drawBoard(8);
            if (StdDrawPlus.isSpacePressed()){
                if (b.canEndTurn()){
                    b.endTurn();
                    System.out.println("derp");
                }
            }
                 if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY(); 
                    if (x>8 || y>8 || x<0 || y<0){
                        continue;
                    }
                    if (b.canSelect((int)x, (int)y)){
                        System.out.print("selected");
                        b.select((int)x, (int)y);

            }
        
        
        }
        StdDrawPlus.show(100);
    }

    }
}






 