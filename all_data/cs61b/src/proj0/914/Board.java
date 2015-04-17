public class Board{
    //Final Commit Pls
    private boolean shouldBeEmpty = false;
    private Piece[][] pieces = new Piece[8][8];
    private boolean moved = false;
    private int placesMoved = 0;
    private Piece pieceSelect = null;
    private int xpos;
    private int ypos;

    //Controls which player's turn it is true==Fire false==Water
    private boolean turn = true;

    //The constructor for Board
    public Board(boolean shouldBeEmpty){
        this.shouldBeEmpty = shouldBeEmpty;
        if(!this.shouldBeEmpty){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0){
                        if(j==0){
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }   
                        else if (j==1){
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        else if (j==2)
                        {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        else if (j==5){
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        else if (j==6){
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        else if (j==7){
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
    }

    //Gets the piece at position (x, y) on the board
    public Piece pieceAt(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7)
        {
            return null;
        }
        return pieces[x][y];   
    }

   private int counter = 0;
    //Returns true if the square at (x, y) can be selected
    public boolean canSelect(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7)
        {
            return false;
        }
        if(pieceAt(x,y)!=null && pieceAt(x,y).isFire()==turn && !moved){
            return true;
        }
        else if(pieceAt(x,y)==null && pieceSelect!=null){
            if(pieceSelect.isFire()){
                if(!pieceSelect.isKing()){
                    if((x==xpos+1 || x==xpos-1) && y==ypos+1 && placesMoved==0){
                        if(pieceSelect.hasCaptured()){
                            return false;
                        }
                        counter++;
                        return true;
                    }

                    else if(x==xpos+2 && y==ypos+2 && pieceAt(xpos+1,ypos+1)!=null && (placesMoved%2==0)){
                        if(!pieceAt(xpos+1,ypos+1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos-2 && y==ypos+2 && pieceAt(xpos-1,ypos+1)!=null && (placesMoved%2==0)){
                        if(!pieceAt(xpos-1,ypos+1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                }
                else{
                    if((x==xpos+1 || x==xpos-1) && (y==ypos+1 || y==ypos-1) && placesMoved==0){
                        if(pieceSelect.hasCaptured()){
                            return false;
                        }
                        counter++;
                        return true;
                    }
                    else if(x==xpos+2 && y==ypos+2 && pieceAt(xpos+1,ypos+1)!=null && (placesMoved%2==0)){
                        if(!pieceAt(xpos+1,ypos+1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos-2 && y==ypos+2 && pieceAt(xpos-1,ypos+1)!=null && (placesMoved%2==0)){
                        if(!pieceAt(xpos-1,ypos+1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos+2 && y==ypos-2 && pieceAt(xpos+1,ypos-1)!=null && placesMoved%2==0){
                        if(!pieceAt(xpos+1,ypos-1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos-2 && y==ypos-2 && pieceAt(xpos-1,ypos-1)!=null && placesMoved%2==0){
                        if(!pieceAt(xpos-1,ypos-1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                }
            }
            if(!pieceSelect.isFire()){
                if(!pieceSelect.isKing()){
                    if((x==xpos+1 || x==xpos-1) && y==ypos-1 && placesMoved==0){
                        if(pieceSelect.hasCaptured()){
                            return false;
                        }
                        counter++;
                        return true;
                    }
                    else if(x==xpos+2 && y==ypos-2 && pieceAt(xpos+1,ypos-1)!=null && placesMoved%2==0){
                        if(pieceAt(xpos+1,ypos-1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos-2 && y==ypos-2 && pieceAt(xpos-1,ypos-1)!=null && placesMoved%2==0){
                        if(pieceAt(xpos-1,ypos-1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                }
                else if(pieceSelect.isKing()){
                    if((x==xpos+1 || x==xpos-1) && (y==ypos+1 || y==ypos-1) && placesMoved==0){
                        if(pieceSelect.hasCaptured()){
                            return false;
                        }
                        counter++;
                        return true;
                    }
                    else if(x==xpos+2 && y==ypos+2 && pieceAt(xpos+1,ypos+1)!=null && (placesMoved%2==0)){
                        if(pieceAt(xpos+1,ypos+1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos-2 && y==ypos+2 && pieceAt(xpos-1,ypos+1)!=null && (placesMoved%2==0)){
                        if(pieceAt(xpos-1,ypos+1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos+2 && y==ypos-2 && pieceAt(xpos+1,ypos-1)!=null && placesMoved%2==0){
                        if(pieceAt(xpos+1,ypos-1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                    else if(x==xpos-2 && y==ypos-2 && pieceAt(xpos-1,ypos-1)!=null && placesMoved%2==0){
                        if(pieceAt(xpos-1,ypos-1).isFire()){
                            counter = counter + 2;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //private boolean bombCap = false;
    //Selects the square at (x, y)
    public void select(int x, int y){
        if(pieces[x][y]==null && pieceSelect!=null){
            moved = true;
            //pieces[x][y] = pieceSelect;
            //place(pieceSelect,x,y);
            pieceSelect.move(x,y);
            //remove(xpos, ypos);
            //pieces[xpos][ypos] = null;
            placesMoved = counter;
            xpos = x;
            ypos = y;

        }
        else{
            pieceSelect = pieces[x][y];
            xpos = x;
            ypos = y;
        }   
    }

    //Places p at (x, y)
    public void place(Piece p, int x, int y){
        pieces[x][y] = p;
    }

    //Executes a remove and returns the piece that was removed
    public Piece remove(int x, int y){
        if(pieceAt(x,y)!=null){
            Piece z = pieceAt(x,y);
            pieces[x][y]=null;
            return z;
        }
        else{
            System.out.println("Null or Out of Bounds");
            return null;
        }
    }

    //Returns whether or not the the current player can end their turn
    public boolean canEndTurn(){
        return moved;
    }

    //Called at the end of each turn
    public void endTurn(){
        if(turn){
            turn = false;
        }
        else{
            turn = true;
        }
        moved = false;
        placesMoved = 0;
        counter = 0;
        pieceSelect.doneCapturing();
        pieceSelect = null;
    }
    private int calcFire(){
        int fire = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i,j)!=null){
                    if(pieceAt(i,j).isFire()){
                        fire++;
                    }
                }
            }
        }
        return fire;
    }

    private int calcWater(){
        int water = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i,j)!=null){
                    if(!pieceAt(i,j).isFire()){
                        water++;
                    }
                }
            }
        }
        return water;
    }

    //Returns the winner of the game. "Fire", "Water", "No one"
    public String winner(){
        int firepieces = this.calcFire();
        int waterpieces = this.calcWater();
        
        if(firepieces > 0 && waterpieces == 0){
            String result = "Fire";
            return result;
        }
        else if(firepieces == 0 && waterpieces > 0){
            String result = "Water";
            return result;
        }
        else if(firepieces == 0 && waterpieces == 0){
            String result = "No one";
            return result;
        }
        else{
            return null;
        }
    }


    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{                  
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = pieces[i][j];
                if (piece!=null) {
                    if((piece).isFire()){
                        if((piece).isBomb()){
                            if((piece).isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                        else if((piece).isShield()){
                            if((piece).isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }   
                        }
                        else{
                            if((piece).isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }   
                        }
                    }
                    else{
                        if((piece).isBomb()){
                            if((piece).isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else if((piece).isShield()){
                            if((piece).isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }   
                        }
                        else{
                            if((piece).isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
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
    //starts a GUI supported version of the game.
    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        //Continual Draw Method
        while(true) {
            b.drawBoard(8);
            if(StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int) x, (int) y)){
                    b.select((int) x, (int) y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }
            b.winner();
            StdDrawPlus.show(10);
        }
    }
}