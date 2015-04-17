public class Board{
    private boolean shouldBeEmpty;
    private Piece [][] pieces;
    private boolean[][] selected; 
    private int turn;
    private boolean piece_selected;
    private int [] loc;
    private boolean moved;    


    public Board(boolean shouldBeEmpty){
        this.shouldBeEmpty = shouldBeEmpty;
        pieces = new  Piece [8][8];
        selected = new boolean [8][8];
        turn = 0;
        moved = false;
        piece_selected = false;
        loc = new int [2]; //storing x at 0, and y at 1
        if (!shouldBeEmpty){
            initializeArray();
        } 

    }
    
    /*
    *   Draws the initial board that is empty
    */
    private void drawEmptyBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                redRawSquare(i, j);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(selected[i][j]){
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }

                
            }
        }
    }


    /*
    *   Draws the board with all the pieces set up as they are in the spec
    */
    private void drawBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                redRawSquare(i, j);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(selected[i][j]){
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieceAt(i, j)!=null){
                    Piece temp=pieceAt(i, j);
                    StdDrawPlus.picture(i + .5, j + .5, image(temp), 1, 1);
                }
                
            }
        }
    }

    /*
    *   Helper method to finding the location of the image
    */
    private String image(Piece p){
        String result = "img/";
        if (p.isBomb()){
            result += "bomb";;
        }
        else if (p.isShield()){
            result += "shield";
        }
        else{
            result += "pawn";
        }
        if(p.isFire()){
            result += "-fire";
        }
        else if (!p.isFire()){
            result += "-water";
        }
        if (p.isKing()){
            result += "-crowned.png";
        }
        else{
            result += ".png";
        }

        return result;
    }

    private boolean isWithinBounds(int x, int y){
        if ((x>=0 && x<8) && (y>=0 && y<8)){
            return true;
        }
        return false;
    }
    /*
    *   Returns a piece if it is in the coordinates, otherwise returns none
    */
    public Piece pieceAt(int x, int y){
        if (!isWithinBounds(x, y)){
            return null;
        }
        return pieces[x][y]; 
    }


    /*
    *   Redraws a solid square
    */
    private void redRawSquare(int x, int y){
        if ((x + y) % 2 == 0 ) {
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        }

        else{                  
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
        }
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }

    /*
    *   Places a piece on the board, replaces the piece if placed in the same field
    */
    public void place (Piece p, int x, int y){
        if(isWithinBounds(x, y)){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (pieceAt(i, j) == p){
                        remove(i, j);
                    }
                }
            }
            selected[x][y]=true;
            piece_selected = true;
            pieces[x][y] = p;
        }
    }   

    /*
    *   Returns true if there is a piece at (x, y) that can be selected
    *   Things to check:
    *   1. The player have not selected a piece yet
    *   2. The player has selected a piece, but has not moved it
    *   3. Can be an empty space, if it is a valid move for the piece selected
    *   4. If chaining captures
    */
    public boolean canSelect(int x, int y){
        Piece moving_piece =pieceAt(loc[0], loc[1]);
        if(pieceAt(x, y)!=null){
            if(!piece_selected && pieces[x][y].side() == turn && !moved ){
                return true;
            }
            else if((piece_selected) && (pieces[x][y].side() == turn) && (!moved)){
                selected[loc[0]][loc[1]]=false;
                return true;
            }
        }
        else if (piece_selected && validMove(loc[0], loc[1], x, y) && !moved ){
            return true;
        }
        else if (piece_selected && validChainMove(loc[0], loc[1], x, y) && moved && moving_piece.hasCaptured() && pieceAt((loc[0]+x)/2 , (loc[1]+y)/2)!=null){
            return true;
        }
    return false;
}

    /*
    *Checks for a valid chain move
    */
    private boolean validChainMove(int xi, int yi, int xf, int yf){
        Piece moving_piece = pieceAt(xi, yi);
        if (moving_piece!= null && isWithinBounds(xf, yf) && (Math.abs(xi-xf)==2) && (Math.abs(yi-yf)==2)){
            if(moving_piece.isKing()){
                if(pieceAt((xi + xf)/2, (yi + yf)/2)!=null && moving_piece.side()!= pieceAt((xi + xf)/2, (yi + yf)/2).side()){
                    return true;
                }
            }

            else if (!moving_piece.isKing()){
                if ((!moving_piece.isFire()) && (yf==yi-2)){
                    if (pieceAt(xi-1, yi-1)!=null && (pieceAt(xi-1, yi-1).isFire()) && (pieceAt(xf, yf)==null)) {
                        return true;
                    }
                    else if(pieceAt(xi+1, yi-1)!=null && (pieceAt(xi+1, yi-1).isFire() && (pieceAt(xf, yf) == null))) {
                        return true;
                    }
                }
                else if((moving_piece.isFire()) && (yf==yi+2)){
                    if (pieceAt(xi+1, yi+1)!=null && !(pieceAt(xi+1, yi+1).isFire() && pieceAt(xf, yf)==null)) {
                        return true;
                    }
                    else if (pieceAt(xi-1, yi+1)!=null && !(pieceAt(xi-1, yi+1).isFire()) && (pieceAt(xf,yf)==null)){
                        return true;
                    }
                }
            }   
        }
    return false;
    }

    /*
    *   Retruns true if the piece can either move to (xf, yf) or capture to (xf, yf)
    */
    private boolean validMove(int xi, int yi, int xf, int yf){
        if (isWithinBounds(xf, yf) && xi!=xf && yi!=yf){
            if(pieceAt(xi, yi)==null){
                return false;
            } 
            Piece moving_piece = pieceAt(xi, yi);
            if(moving_piece.isKing()){
                if ((Math.abs(xi-xf)==1) && (Math.abs(yi-yf)==1)){
                    return true;
                }
                else if ((Math.abs(xi-xf)==2) && (Math.abs(yi-yf)==2 && validChainMove(xi, yi, xf, yf))){
                    return true;
                }
            }
            if(!moving_piece.isKing()){
                if (Math.abs(xi - xf)==1){
                    if((moving_piece.isFire()) && (yf==yi+1)){
                        return true;}
                    else if((!moving_piece.isFire()) && (yf==yi-1)){
                        return true;
                        }
                    }
                else if(Math.abs(xi - xf) == 2 && validChainMove(xi, yi, xf, yf)){
                    return true;
                }
            }
        }
            return false;
    }

    /* 
    *   Selects a piece if possible, highlights that square
    */
    public void select(int x, int y){
        Piece temp = pieceAt(loc[0], loc[1]);
        if(temp!=null && piece_selected && pieceAt(x, y)==null){
            place(temp, x, y);
            temp.move(x, y);
            moved = true;
            selected[loc[0]][loc[1]]=false;

        }   
        loc[0]=x;
        loc[1]=y;
        piece_selected = true;
    }

    /*
    *   Removes the piece and returns it,
    *   If the input is out of bounds, warn the user.
    *   If there is nothing to remove, print a message.
    */
    public Piece remove (int x, int y){

        if (!isWithinBounds(x, y)){
            System.out.println("Out of bounds");
            return null;
        }
        
        if (pieces[x][y]==null){
            System.out.println("Nothing to remove");
            return null;
        }
        Piece removed = pieces[x][y];
        selected[x][y] = false;
        pieces[x][y]= null;
        return removed;

    }
    
    /*
    *   Checks if a piece has moved
    */
    public boolean canEndTurn(){

        if(moved){
            return true;
        }
        return false;
    }

    private  void switchPlayer(){
        if (turn == 0){
            turn = 1;
        }
        else{
            turn = 0;
        }
        piece_selected = false;
        moved = false;
    }

    /*
    *   Checks for space bar event and if the player can end turn
    *   then uses a helper method to switch between whos turn it is
    */
    public void endTurn(){
        switchPlayer();
        if (pieceAt(loc[0], loc[1])!=null){
            pieceAt(loc[0], loc[1]).doneCapturing();
        }   
        selected[loc[0]][loc[1]] = false;

    }   

    /*
    *   Returns the result of the game
    */
    public String winner(){
        int firePieces =0 ;
        int waterPieces= 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i, j)!=null){
                    if(pieceAt(i, j).isFire()){
                        firePieces+=1;
                    }
                    else if (!pieceAt(i, j).isFire()){
                        waterPieces+=1;
                    }
                }  
            }
        }
        if (firePieces==0 && waterPieces ==0){
            return ("No one");
        }
        if (firePieces==0 && waterPieces>0){
            return ("Water");
        }
        if (firePieces>0 && waterPieces==0){
            return ("Fire");
        }
        return null;
    }

    /*
    *   Fills up the array with initial values
    */
    private void initializeArray(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {                
                if (j==0 && i%2==0){
                    pieces[i][j] = new Piece (true, this, i, j , "pawn");
                }
                else if (j==1 && i%2!=0){
                    pieces[i][j] = new Piece (true, this, i, j, "shield");
                }
                else if (j==2 && i%2==0){
                    pieces[i][j] = new Piece (true, this, i, j, "bomb");
                }
                else if (j==5 && i%2!=0){
                    pieces[i][j] = new Piece (false, this, i, j, "bomb");
                }
                else if (j==6 && i%2==0){
                    pieces[i][j] = new Piece (false, this, i, j, "shield");
                }
                else if (j==7 && i%2!=0){
                    pieces[i][j] = new Piece (false, this, i, j, "pawn");
                }
            }
        }

    }
    /*
    *   Main method that is constantly running to redraw the board, or draw an empty board
    */
    private void play()
    {

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        if (shouldBeEmpty){
            drawEmptyBoard();
        }
        else{
            while(true){
                drawBoard();
                if (StdDrawPlus.mousePressed()) {
                    double x= StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    if (canSelect((int) x, (int) y)){
                        selected[(int) x][(int) y] = true;
                        if(pieceAt((int) x, (int) y)!=null){
                            loc[0] = (int)x;
                            loc[1] = (int)y;
                        }
                        select((int) x, (int) y);
                    }
                }
                if (StdDrawPlus.isSpacePressed() && piece_selected){
                    if (canEndTurn()){
                        endTurn();
                    }
                }
                StdDrawPlus.show(100);
            }
        }
    }
    public static void main(String[] args) {

        Board start = new Board(false);
        start.play();        
    }    
}