public class Board {
    private Piece[][] board_pieces = new Piece[8][8];
    private boolean fire_turn = true;
    private boolean water_turn = false;
    private boolean has_selected = false;
    private boolean moved = false;
    private int selectedpiecex, selectedpiecey;
    private boolean aftercapture = false;

    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty){
            drawBoard(8);
        }
        else{
        drawBoard(8);
        beginpieces();
        drawpieces();
        }
    }


    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               }
            }
        }
    

    private void beginpieces(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0){
                    if (j == 0 || j == 7){
                        board_pieces[i][j] = new Piece((j == 0), this, i, j, "pawn");
                    }
                    else if (j == 1 || j == 6){
                        board_pieces[i][j] = new Piece((j == 1), this, i, j, "shield");
                    }
                    else if (j == 2 || j == 5){
                        board_pieces[i][j] = new Piece((j == 2), this, i, j, "bomb");
                    }


                }
            }
        }
    }


    private void drawpieces () {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {   
                if( board_pieces[i][j] != null ){
                    if (board_pieces[i][j].isFire()){
                        if (board_pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                        else if (board_pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        else
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                    else{
                        if (board_pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                        else if (board_pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        else 
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }

            }
        }

    public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7 || x< 0 || y < 0){
            return null;
        } 
        else {
            return board_pieces[x][y];
        } 
    }

    public void place(Piece p, int x, int y) {
        board_pieces[x][y] = p;}

    public Piece remove(int x, int y){
        if(x > 7 && y > 7 && x < 0 && y < 0){
            System.out.println("this spot is out of bounds");
            return null;
        }
        else if(board_pieces[x][y] == null){
            System.out.println("there is no piece here");
            return null;
        }
        else{
            Piece old_piece = board_pieces[x][y];
            board_pieces[x][y] = null;
            return old_piece;

        }
    }

    
    public boolean canSelect(int x, int y){
        if(board_pieces[x][y] != null){
            if(moved != true){
                if((water_turn) && board_pieces[x][y].isFire() != true){
                    return true;
                }
                else if(fire_turn && board_pieces[x][y].isFire()){
                    return true;
                }
            }
        }
        else{
            if(aftercapture && Math.abs(selectedpiecex - x) == 2 && Math.abs(selectedpiecey - y) == 2){
                return validMove(selectedpiecex, selectedpiecey, x, y);
            }
            else if(has_selected && moved != true){
                return validMove(selectedpiecex, selectedpiecey, x, y);
            }
        } 
    return false;
    }

    private boolean validMove (int xi, int yi, int xf, int yf){
        Piece initial = board_pieces[xi][yi];
        if(board_pieces[xf][yf]==null && xf < 8 && yf < 8 && xf > -1 && yf > -1){
            if(Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1){
                if (initial.isKing()){
                    return true;
                }
                else{ 
                    if (yf - yi == 1 && initial.isFire()){
                        return true;
                    }else if (yf -yi == -1 && (initial.isFire() != true)){
                        return true;
                    }
                }
            }
            /** dealing with the capture case**/
            else if(Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2){
                if(xi + 2 == xf && yi + 2 == yf && (initial.isFire() || initial.isKing())) {
                    if( (initial.isFire() && board_pieces[xi +1][yi +1] != null && (board_pieces[xi + 1][yi +1]).isFire() !=true) 
                    || (initial.isFire() != true && board_pieces[xi +1][yi +1] != null && ((board_pieces[xi + 1][yi +1]).isFire()))) {
                        return true;
                    }
                }
                else if(xi + 2 == xf && yi - 2 == yf && (initial.isFire() !=true || initial.isKing())) {
                    if( (initial.isFire() && board_pieces[xi +1][yi -1] != null && ((board_pieces[xi + 1][yi -1]).isFire() !=true)) 
                    || (initial.isFire() != true && board_pieces[xi +1][yi -1] != null && ((board_pieces[xi + 1][yi -1]).isFire()))){
                        return true;
                    }
                }    
                else if(xi - 2 == xf && yi - 2 == yf && (initial.isFire() != true || initial.isKing())) {
                    if( (initial.isFire() && board_pieces[xi -1][yi -1] != null && ((board_pieces[xi - 1][yi -1]).isFire() !=true)) 
                        || (initial.isFire() != true && board_pieces[xi -1][yi -1] != null && ((board_pieces[xi - 1][yi -1]).isFire()))){
                        return true;
                    }
                }
               else if(xi - 2 == xf && yi + 2 == yf && (initial.isFire() || initial.isKing())) {
                    if( (initial.isFire() && board_pieces[xi -1][yi +1] != null && ((board_pieces[xi - 1][yi +1]).isFire() !=true)) 
                        || (initial.isFire() != true && board_pieces[xi -1][yi +1] != null && ((board_pieces[xi - 1][yi +1]).isFire()))){
                        return true;
                    }                    
                }
            }            
        }
        return false;
    }

    public void select(int x, int y) {
        if( board_pieces[x][y] != null){
            selectedpiecey = y;
            selectedpiecex = x;
            has_selected = true;
        }
        else{
            place(board_pieces[selectedpiecex][selectedpiecey], x, y);
            board_pieces[selectedpiecex][selectedpiecey].move(x, y);
            if (board_pieces[x][y] !=null && board_pieces[x][y].hasCaptured()) {
                if(board_pieces[x][y].isBomb()){
                    remove(x, y);
                }
                else{
                aftercapture = true;
            }
            }
            moved = true;
        }

    }

    public boolean canEndTurn(){
        if(board_pieces[selectedpiecex][selectedpiecey] != null && board_pieces[selectedpiecex][selectedpiecey].hasCaptured() || moved){
            return true;
        }
        return false;
    }

    public void endTurn(){
        if( board_pieces[selectedpiecex][selectedpiecey] != null){
        board_pieces[selectedpiecex][selectedpiecey].doneCapturing();
        }        
        if(fire_turn){
            fire_turn = false;
            water_turn = true;
            has_selected = false;
            moved = false;
        }
        else{
            fire_turn = true;
            water_turn = false;
            has_selected = false;
            moved = false;
        }
    }

    public String winner(){
        int f = 0;
        int w = 0;
        for (int x = 0; x<8; x ++){
            for (int y = 0; y< 8; y ++){
                if( board_pieces[x][y] != null){
                    if(board_pieces[x][y].isFire() ){
                        f += 1;
                    }
                    else{
                        w += 1;
                    }
                }
            }
        }
        if( f > w){
            return "Fire";
        } 
        else if(w > f){
            return "Water";
        }
        else if( w == 0 && f == 0){
            return "No one";
        }
        else{
            return null;
        }
    }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            Board x = new Board(false);
            StdDrawPlus.show(100);
            if(StdDrawPlus.isSpacePressed()){
                if(x.canEndTurn()){
                    if(x.winner() != null){
                        x.winner();
                    }
                    else{
                    x.endTurn();
                }
                }
            }
            if (StdDrawPlus.mousePressed()) {
                int xcoor = (int) StdDrawPlus.mouseX();
                int ycoor = (int) StdDrawPlus.mouseY();
                if( x.canSelect(xcoor, ycoor) ){
                    x.select(xcoor, ycoor);
                }
            }
        }
    }
}
