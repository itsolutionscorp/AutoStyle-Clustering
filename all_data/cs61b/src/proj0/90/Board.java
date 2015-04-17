public class Board{

    private static Piece[][] pieces;
    private Board b;
    private static int N = 8;
    private String player;
    private String isItAKing;
    private String win  = null;
    private static int playerID  = 0;
    private Piece selected_piece == null;
    private boolean hasMoved = false;

    private void drawBoard(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j]  == null){
                    ;
                }
                else{
                    if (pieces[i][j].isFire) {
                        player  = "-fire";
                    }
                    else{
                        player  = "-water";
                    }
                    if (pieces[i][j].isKing()) {
                        isItAKing  = "-crowned";
                        // System.out.println(this.isItAKing);
                    }
                    else{
                         isItAKing  = "" ;
                        // System.out.println(this.isItAKing);
                    }
                StdDrawPlus.picture(i + .5, j + .5, "img/" + pieces[i][j].type + player + isItAKing + ".png", 1, 1);
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    pieces[i][j] = null;
                }
            }
        }
        else{
            // System.out.println(N);
            for (int i = 0; i < N; i++) {
                        // System.out.println(i+"i");
                for (int j = 0; j < N; j++) {
                        // System.out.println(j+"j");
                    if (i%2==0 && j==0) {
                        pieces[i][j] = new Piece(true, this, i,j,"pawn");
                        // System.out.println(pieces[i][j].b);
                    }
                    else if (i%2==1 && j==1) {
                        pieces[i][j] = new Piece(true, this, i,j,"shield");
                    }
                    else if (i%2==0 && j==2) {
                        pieces[i][j] = new Piece(true, this, i,j,"bomb");
                    }
                    else if (i%2==1 && j==5) {
                        pieces[i][j] = new Piece(false, this, i,j,"bomb");
                    }
                    else if (i%2==0 && j==6) {
                        pieces[i][j] = new Piece(false,this, i,j,"shield");
                    }
                    else if (i%2==1 && j==7) {
                        pieces[i][j] = new Piece(false,this, i,j,"pawn");
                    }
                    else{
                        pieces[i][j] = null;
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if ( ( (0 <= x) && (x <= 7) ) && ( (0 <= y) && (y <= 7) ) ){
            // Is this the right type to return?
            return pieces[x][y];
        }
        else {
            return null;
        }
    }


    public void place(Piece p, int x, int y){
        if (p == null){
            ;
        }
        else if (((0 <= x) && (x <= 7)) && ((0 <= y) && (y <= 7))) {
            pieces[x][y] = p;
            pieces[x][y].x = x;
            pieces[x][y].y = y;
        }
        else{
            ;
        }
    }

    Piece remove(int x, int y){
        if (((0 <= x) && (x <= 7)) && ((0 <= y) && (y <= 7))) {
            if (pieces[x][y] == null){
                System.out.println("No piece here.");
                return null;
            }
            else{
                Piece ptr = pieces[x][y] ;
                pieces[x][y] = null;
                return ptr;
            }
        }
        else{
            System.out.println("Out of bounds.");
            return null;
        }
    }


    private int turn(int playerID){
        playerID = (1 - playerID);
        return playerID;
    }


    boolean canSelect(int x, int y){
        // TODO: create playerID variable (0 or 1)
        // create turn variable and set default as 0 and use it to check whose turn it is
        //
        // if isFire is TRUE, allow player 0 to select and return TRUE if cond met

        // player 0's moves
        if (playerID  == 0){
            if (selected_piece == null){
                return true;
            }
            else{

            }
                /* TODO: selected piece but didn't move it and this is a valid move
                selected OR hasCaptured is true, and  destination is valid */
        }
        // player 1's moves
        else {
            //TODO: make decision tree
        }
    }

    public String winner(){
            private int count0 = 0;
            private int count1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] == null){
                    ;
                }
                else if (pieces[i][j].isFire) {
                    count0 = count0 + 1 ;
                }
                else {
                    count1 = count1 + 1 ;
                }
            }
        }
        // POSSIBLE BUG: check if counts are returned outside for loop
        System.out.println(count0) ;
        if ( (count0 == 0) && (count1 == 0) ){
            win = "No one";
            return win;
        }
        else if ( (count0 > 0) && (count1 == 0) ) {
            win = "Fire" ;
            return win;
        }
        else if ( (count0 == 0) && (count1 > 0) ) {
            win = "Water" ;
            return win;
        }
        //  no else because no other possibilities
    }

    private boolean validMove(int x_i,int y_i, int x_f, int y_f){
        if ((y_i > N) || (y_i < 0) || (x_i > N) || (x_i < 0) || y_f > N) || (y_f < 0) || (x_f > N) || (x_f < 0)) {
            return false;
        }
        else if (pieces[x_i][y_i] == null){
            return false;
        }
        else if (pieces[x_f][y_f] != null){
            return false;
        }
        else if (pieces[x_i][y_i].isKing) {
            if ((Math.abs(x_i - x_f) == 1) && (Math.abs(y_i - y_f) == 1)){
                return true;
            }
            else {
                return false;
            }
        }
        else if (pieces[x_i][y_i].isKing != true){
            if (isFire){
                if ((Math.abs(x_i - x_f) == 1) && ((y_f - y_i) == 1)){
                return true;
                }
                else {
                return false;
                }
            }
            else{
                if ((Math.abs(x_i - x_f) == 1) && ((y_f - y_i) == (-1) ){
                return true;
                }
                else {
                return false;
                }
            }
        }
    }






    public static void main(String[] args) {
        // draws board
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        // sets size array of pieces and sets board
        pieces = new Piece[N][N];
        Board b  = new Board(false);

        // draws board
        b.drawBoard(N);

        // sets first player
        playerID = 0;

        // TODO: while win is null, make loop
        while (win == null){

        }
    }
}

