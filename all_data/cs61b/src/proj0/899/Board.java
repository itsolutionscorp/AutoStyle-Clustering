//import proj0.Piece;

public class Board {

    Piece[] pieces = new Piece[24];
    private int last_x = 9;
    private int last_y = 9;
    private Piece last_select = null;
    private int move_count = 0;
    private boolean fireTurn = true;

    private void drawBoard(){
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

            }
        }
    }

    private void drawPieces(){
        for(int i = 0; i < 24; i +=1){
                Piece curr = pieces[i];
                if(curr != null){
                if(curr.isKing()){
                   if(curr.type == "pawn"){
                    if(curr.isFire()){
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/pawn-fire-crowned.png", 1, 1);
                }else{
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/pawn-water-crowned.png", 1, 1);
                }
                }
                if(curr.type == "bomb"){
                if(curr.isFire()){
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/bomb-fire-crowned.png", 1, 1);
                }else{
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/bomb-water-crowned.png", 1, 1);
                }
                }
                if(curr.type == "shield"){
                if(curr.isFire()){
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/shield-fire-crowned.png", 1, 1);
                }else{
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/shield-water-crowned.png", 1, 1);
                }
                } 
                } else{
                if(curr.type == "pawn"){
                    if(curr.isFire()){
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/pawn-fire.png", 1, 1);
                }else{
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/pawn-water.png", 1, 1);
                }
                }
                if(curr.type == "bomb"){
                if(curr.isFire()){
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/bomb-fire.png", 1, 1);
                }else{
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/bomb-water.png", 1, 1);
                }
                }
                if(curr.type == "shield"){
                if(curr.isFire()){
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/shield-fire.png", 1, 1);
                }else{
                    StdDrawPlus.picture(curr.x + .5, curr.y + .5, "img/shield-water.png", 1, 1);
                }
                }
                
                

            } }}

    }

    public Board(boolean shouldBeEmpty) {

        drawBoard();

        if(shouldBeEmpty != true){
            int counter = 0;
            for(int column = 0; column < 8; column +=1){
               for(int row = 0; row < 8; row += 1){
                if((row + column) % 2 == 0){
                    if(row == 7){
                        pieces[counter] = new Piece(false, this, column, row, "pawn");
                        counter +=1;
                    }else if(row == 6){
                        pieces[counter] =  new Piece(false, this, column, row, "shield");
                        counter +=1;
                    }else if(row == 5){
                        pieces[counter] =  new Piece(false, this, column, row, "bomb");
                        counter +=1;
                    }else if(row == 2){
                        pieces[counter] = new Piece(true, this, column, row, "bomb");
                        counter +=1;
                    }else if(row == 1){
                        pieces[counter] = new Piece(true, this, column, row, "shield");
                        counter +=1;
                    }else if(row == 0){
                        pieces[counter] = new Piece(true, this, column, row, "pawn");
                        counter +=1;
                    }
                }

            } 
            }
            drawPieces();
        }
    }

    public Piece remove(int x, int y){
        Piece curr = pieceAt(x , y);
        for(int i = 0; i < 24; i +=1){
            if (pieces[i] != null && curr != null) {
            if(pieces[i].y == curr.y && pieces[i].x == curr.x){
                pieces[i] = null;

            }
        }}
        return curr;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        if(pieceAt(xf, yf) == null && pieceUnder(xi, yi, xf, yf) != null 
            && pieceUnder(xi, yi, xf, yf) != last_select && 
            pieceAt(xi, yi).isFire() != pieceUnder(xi, yi, xf, yf).isFire()){
                return true;
            }else if((xi - xf == 1 || xi - xf == -1)
                    && (yi - yf == 1 || yi - yf == -1) && move_count < 1 
                    && pieceAt(xf, yf) == null ){
                return true;
            }
        return false;
    }

    public boolean canSelect(int x, int y){
        if ((y + x) % 2 != 0){
            return false;
        }
        if(pieceAt(x , y) != null && (last_select == null)){
                if(fireTurn == pieceAt(x , y).isFire()){
                    return true;
                    
                }
        }
        if(pieceAt(x , y) != null && (last_select != null) && move_count == 0){
                if(fireTurn == pieceAt(x , y).isFire()){
                    last_select = null;
                    return true;
                    
                }
                return false;
            
        }
        if(last_select != null && validMove(last_select.x, last_select.y, x, y) ){
            
            if(last_select.isFire() && !last_select.isKing() && last_select.y < y){
                return true;
            }
            if(!last_select.isFire() && !last_select.isKing() && last_select.y > y){
                return true;
            }
            if(last_select.isKing()){
                return true;
            }
        }
        return false;
    }
    private void capturing(Piece p, int xi, int xf, int yi, int yf){

        
            if((xi - xf > 1 || xi - xf < -1)
                    && (yi - yf > 1 || yi - yf < -1) ){
                remove(pieceAt(((xi+xf)/2), ((yi+yf)/2)).x, pieceAt(((xi+xf)/2), ((yi+yf)/2)).y);
            if(p.isBomb()){
            Piece upperright = pieceAt(xf+1, yf+1);
            Piece upperleft = pieceAt(xf-1, yf+1);
            Piece lowerright = pieceAt(xf+1, yf-1);
            Piece lowerleft = pieceAt(xf-1, yf-1);
            if (upperright != null && !upperright.isShield() && p.isFire() != upperright.isFire()) {
                remove(xf + 1, yf + 1);
            }
            if (upperleft != null && !upperleft.isShield() && p.isFire() != upperleft.isFire()) {
                remove(xf - 1, yf + 1);
            }
            if (lowerright != null && !lowerright.isShield() && p.isFire() != lowerright.isFire()) {
                remove(xf + 1, yf - 1);
            }
            if (lowerleft != null && !lowerleft.isShield() && p.isFire() != lowerleft.isFire()) {
                remove(xf - 1, yf - 1);
            }
            remove(p.x , p.y);
        }
            }
        

    }

    public void select(int x, int y){

        last_x = x;
        last_y = y;
        if(last_select == null){
            last_select = pieceAt(x,y);
            drawBoard();
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
           drawPieces();
           

        }else{
            int pre_x = last_select.x;
            int pre_y = last_select.y;
            place(last_select, x, y);
            move_count += 1;
            capturing(last_select, pre_x, x, pre_y, y);
            drawBoard();
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            drawPieces();
        }
        


    

    } 

    private Piece pieceUnder(int xi, int yi, int xf, int yf){
        return pieceAt(((xi+xf)/2), ((yi+yf)/2));

    }
    public Piece pieceAt(int x, int y){
    if(x < 0 || x > 8 || y < 0 || y > 8){
        return null;
    }

    for(int i = 0; i < 24; i +=1){
            if(pieces[i] != null && pieces[i].x == x && pieces[i].y == y){
                return pieces[i];
            }
        }
            return null;
        
    }


    public void place(Piece p, int x, int y){

        if(p != null && x > -1 && x < 8 && y > -1 && y < 8){
            p.move(x , y);
        }

    }

    public boolean canEndTurn(){
        if(move_count > 0){
            return true;
        }
        return false;
    }

    public void endTurn(){
        last_select = null;
        move_count = 0;
        drawBoard();
        drawPieces();
        if(fireTurn){
            fireTurn = false;
        }else{
            fireTurn = true;
        }
    }

    public String winner(){
        boolean fire_left = false;
        boolean water_left = false;
        for(int i = 0; i < 24; i +=1){
            Piece curr = pieces[i];
            if(curr != null){
                if(curr.isFire()){
                    fire_left = true;
                }
                if(!curr.isFire()){
                    water_left = true;
                }
            }
        }
        if(fire_left && !water_left){
            return "Fire";
        }
        if(!fire_left && water_left){
            return "Water";
        }
        if(!fire_left && !water_left){
            return "No one";    
        }
        return null;
        
    }

    public static void main(String[] args) {

        int x;
        int y;
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board c_board = new Board(false);
        while(true){
            if (StdDrawPlus.mousePressed()) {
                x = (int)StdDrawPlus.mouseX();
                y = (int)StdDrawPlus.mouseY();
                if(c_board.canSelect(x,y)){
                    c_board.select(x , y);    
                }
            } 
            if(StdDrawPlus.isSpacePressed()){
                c_board.last_select.doneCapturing();
                if(c_board.canEndTurn()){
                    c_board.endTurn();                    
                }
                System.out.println(c_board.winner());

            }

            
        }
         
    }

}