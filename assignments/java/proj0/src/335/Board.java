public class Board {

    //Initiate board and choosen piece boolean
    private Piece[][] pieces = new Piece[8][8];
    private boolean[][] select_pieces = new boolean[8][8];
    private boolean empty = false;
    //Switch Side
    private int currSide = 0;
    //Piece to Move
    private Piece toMove = null;
    private int[] toMove_loc;
    //Check boolean
    private boolean is_selected = false;;
    private boolean is_moved = false;
    //Pieces left for each
    private int fire_left;
    private int water_left;

    /**Constructor*/
    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty==true) {
            for (int i = 0; i < 8; i++ ){
                for (int j = 0; j < 8; j++){
                    pieces[i][j] = null;
                }
            }
            empty = true;
            fire_left = 0;
            water_left = 0;
        } else {
            for (int i = 0; i < 8; i++ ){
                for (int j = 0; j < 8; j++){
                    pieces[i][j] = pieceGenerate(i,j); 
                }
            }
            empty = false;
            fire_left = 12;
            water_left = 12;
        }
    }

    private Piece pieceGenerate(int x, int y){
        x = Math.abs(x);
        y = Math.abs(y);
        if (x >= pieces.length || y >= pieces.length) {
            return null;
        }
        else if(x%2==1 && y==7){
            return new Piece(false, this, x, y, "pawn");
        }
        else if(x%2==0 && y==6){
            return new Piece(false, this, x, y, "shield");
        }
        else if(x%2==1 && y==5){
            return new Piece(false, this, x, y, "bomb");
        }
        else if(x%2==0 && y==2){
            return new Piece(true, this, x, y, "bomb");
        }
        else if(x%2==1 && y==1){
            return new Piece(true, this, x, y, "shield");
        }
        else if(x%2==0 && y==0){
            return new Piece(true, this, x, y, "pawn");
        }
        else{
            return null;
        }
    }

    public Piece pieceAt(int x, int y){
        //get piece or return null
        if (x >= pieces.length || y >= pieces.length) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y){
        if(x>=8 || y>=8){
            return false;
        }
        
        Piece currPiece = pieces[x][y];

        //If Player selected a square with piece; switching pieces
        if (currPiece!=null && (currSide == currPiece.side())){
                if ( is_selected==false || (is_selected==true && is_moved==false)){
                    return true;
                }
                
        //If Player selected an empty square; move pieces
        }else if (currPiece == null){
            //Initial Move, one or two grid
            if( is_selected == true && is_moved==false && 
                validMove(toMove_loc[0], toMove_loc[1], x,y) ){
                return true;
            //Multiple Capture, is_moved must be true
            } else if ( is_selected == true && toMove.hasCaptured() 
                && toMove.isBomb() == false 
                && validMove(toMove_loc[0], toMove_loc[1], x,y) ){
                toMove.doneCapturing();
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece initial = pieces[xi][yi];
        int s = 1;
        int diff_y = yf-yi;
        int diff_x = Math.abs(xf-xi);
        
        //Check side
        if (currSide == 1){
            s = -1;
        }

        //Enable up and down
        if(initial!= null && initial.isKing()){  
            diff_y = Math.abs(diff_y);
            s = 1;
        }

        //Normal move
        if(diff_y==s && diff_x==1){
            return true;

        //Capture move
        } else if (diff_y==s*2 && diff_x == 2){
            return validCapture(xi, yi, xf, yf);
        }
        return false;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf){
        int mid_x = getMid(xi, xf);
        int mid_y = getMid(yi, yf);
        //No Piece in between: False
        if(pieces[mid_x][mid_y] == null){
            return false;
        }
        else if(pieces[mid_x][mid_y].side() != pieces[xi][yi].side()){
            return true;
        }
        return false;
    }

    private int getMid(int x1, int x2){
        if(x1>x2){
            return x1-1;
        }else{
            return x1+1;
        }
    }

    public void select(int x, int y){
        if(x>=8 || y>=8){
            return;
        }
        //Unhighlight previous
        if(toMove!=null){
            select_pieces[toMove_loc[0]][toMove_loc[1]] = false;
        }
        //Highlight Select
        select_pieces[x][y] = true;

        if(toMove==null || pieces[x][y]!=null){
            is_selected = true;
            toMove = pieces[x][y];
            toMove_loc = new int[]{x,y};
        }else{
            is_moved = true;
            //REMOVE, PLACE and UPDATE
            int prev_x = toMove_loc[0];
            int prev_y = toMove_loc[1];
            //Place to Move to desire grid
            place(toMove, x, y);
            //Modify the piece's x,y 
            toMove.move(x,y);
            remove(prev_x,prev_y);
            // pieces[prev_x][prev_y] = null;
            toMove_loc = new int[]{x,y};

            if(toMove.hasCaptured()){
                if(toMove.isBomb()){
                    bombAction(x,y);
                }
            }
        }
    }

    private void bombAction(int x, int y){
        remove(x,y);
        for(int i = x-1; i<=x+1; i++){
            for(int j= y-1; j<=y+1; j++){
                if(i<8 && j<8){
                    if(pieces[i][j]!=null && pieces[i][j].isShield()==false){
                        remove(i,j);
                    }
                }
            }
        }
        select_pieces[toMove_loc[0]][toMove_loc[1]] = false;
    }

    /** 
     *  Move Pieces
     */
    public void place(Piece p, int x, int y){
        if (empty = true){
            if(p.isFire()){
                fire_left+= 1;
            }else{
                water_left+=1;
            }
            empty = false;
        }
        if (x >= pieces.length || y >= pieces.length || p == null) {
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y){
        if (x >= pieces.length || y >= pieces.length) {
            System.out.println("Input (x,y) is out of bounds.");
            return null;
        } else if (pieces[x][y] == null){
            System.out.println("There is no piece in this grid");
            return null;
        } else {
            Piece toRemove = pieces[x][y];
            if(toRemove.isFire()){
                fire_left -= 1;
            }else{
                water_left -= 1;
            }
            pieces[x][y] = null;
            return toRemove;
        }
    }

    /** 
     *  Take Turns
     */
    public boolean canEndTurn(){
        if (is_moved == false) return false;
        else return true;
    }

    private void switchSide(){
        if(currSide == 0){
            currSide = 1; 
        }else{
            currSide = 0;
        }
    }

    public void endTurn(){
        switchSide();
        if(toMove_loc!=null){
            select_pieces[toMove_loc[0]][toMove_loc[1]] = false;
        }
        if(toMove!=null){
            toMove.doneCapturing();
        }
        toMove = null;
        toMove_loc = null;
        is_moved = false;
        is_selected = false;
    }

    /** 
     *  End Game
     */
    public String winner(){
        if (fire_left == 0 && water_left == 0 || empty==true){
            return "No one";
        }else if (fire_left == 0) {
            return "Water";
        } else if (water_left == 0) {
            return "Fire";
        } else {
            return null;
        }
    }

    /** 
     *  Image Handling
     */
    private String getType(Piece p){
        if (p.isBomb()) {
            return "bomb";
        } else if (p.isShield()) {
            return "shield";
        } else {
            return "pawn";
        }
    }

    private String getSide(Piece p){
        if (p.isFire()) {
            return "fire";
        } else {
            return "water";
        }
    }

    private String getImage(Piece p){
        String side = getSide(p);
        String type = getType(p);
        if (p.isKing()){
            return "img/"+type+"-"+side+"-crowned.png";
        }else{
            return "img/"+type+"-"+side+".png";
        }
    }

    /** 
     *  GUI
     */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (select_pieces[i][j]) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }

                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);
                }
            }
        }
    }
    
    /** 
     *  Main
     */
    public static void main (String [] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board e = new Board(false);

        while(true) {
            //Draw initial state
            e.drawBoard(N);

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (e.canSelect((int) x, (int) y) == true){
                    e.select((int) x, (int) y);
                } 
            }

            if (StdDrawPlus.isSpacePressed()){
                if(e.canEndTurn()) {
                    e.endTurn();
                };
            }
            //SWITCH PLAYER + UPDATE BOOLEAN MOVED            
            StdDrawPlus.show(100);
        }
    }

}
