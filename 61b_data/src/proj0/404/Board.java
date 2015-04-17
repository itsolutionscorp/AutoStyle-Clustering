public class Board {

    //ATTRIBUTES METHODS --------------------------------------------------------

    private int size = 8;
    private boolean is_empty; 
    private Piece pieces_array[][] = new Piece[size][size];
    private boolean fires_turn = true;
    private Piece selected_p = null;
    private int selected_x;
    private int selected_y;
    private boolean has_moved = false;

    //PRIMARY METHODS --------------------------------------------------------

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) {  
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x_click = StdDrawPlus.mouseX();
                double y_click = StdDrawPlus.mouseY();
                if (b.canSelect((int) x_click, (int) y_click) == true){
                    b.select((int) x_click, (int) y_click);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if (b.canEndTurn() == true) {
                    b.endTurn();
                    // if (b.winner().equals("Fire")){
                    //     System.out.println("Fire wins!");
                    //     break;
                    // }
                    // if (b.winner().equals("Water")){
                    //     System.out.println("Water wins!");
                    //     break;
                    // }
                    // if (b.winner().equals("No one")){
                    //     System.out.println("No one wins!!");
                    //     break;
                    // }
                }
            }
            StdDrawPlus.show(10);           
        }
    }
    
    public Board(boolean shouldBeEmpty) {
        this.is_empty = shouldBeEmpty;
        if (shouldBeEmpty != true){
            for (int x = 0; x < size; x = x + 2) {
                this.pieces_array[x][6] = new Piece(false, this, x, 6, "shield");
                this.pieces_array[x][2] = new Piece(true, this, x, 2, "bomb");
                this.pieces_array[x][0] = new Piece(true, this, x, 0, "pawn");
            }
            for (int x = 1; x < size; x = x + 2) {
                this.pieces_array[x][7] = new Piece(false, this, x, 7, "pawn");
                this.pieces_array[x][5] = new Piece(false, this, x, 5, "bomb");
                this.pieces_array[x][1] = new Piece(true, this, x, 1, "shield");
            }
        }

    }
 
    
    public Piece pieceAt(int x, int y) {        
        if (this.OutOfBounds(x, y) || this.pieces_array[x][y] == null)
            return null;
        else
            return this.pieces_array[x][y];         
    }
    
    public boolean canSelect(int x, int y) {
        if (this.OutOfBounds(x, y))
            return false;
        Piece p = this.pieceAt(x, y);

        // tests if the square is empty //
        if (p == null) {
            if (this.selected_p == null){
                return false;
            }
            else{
                if (this.has_moved == false && this.validMove(x,y)){
                    return true;
                }
                if (this.selected_p.hasCaptured() && this.validMove(x,y)
                    && Math.abs(x - this.selected_x) == 2){
                    return true;
                }

                return false;
            }
        } 
        else {       
            if ((p.isFire() && this.fires_turn) || (!p.isFire() && !this.fires_turn)){
                if (this.selected_p == null)
                    return true;
                if (this.selected_p != null && !this.has_moved)
                    return true;
                
                return false;
            }
            return false;
        }
    }   
   
    public void select(int x, int y) {
       if (this.selected_p == null){
           this.selected_p = this.pieceAt(x,y);
           this.selected_x = x;
           this.selected_y = y;  
       }
       else if (has_moved == false){
            if(this.pieceAt(x,y) == null){
                this.selected_p.move(x,y);
                this.selected_x = x;
                this.selected_y = y;
                this.has_moved = true;

            }
            else {
                this.selected_p = this.pieceAt(x,y);
                this.selected_x = x;
                this.selected_y = y;
            }
       }
       else{
            if (this.selected_p.hasCaptured() && this.validMove(x,y) 
                && Math.abs(x - selected_x) == 2){
                this.selected_p.move(x,y);
                this.selected_x = x;
                this.selected_y = y;
            }

       }
    }
    
    public void place(Piece p, int x, int y) { 
        if (this.OutOfBounds(x, y) == false && p != null) {
            this.pieces_array[x][y] = p;
        }
    }
    
    public Piece remove(int x, int y) {

        if (OutOfBounds(x,y) == true){
            System.out.println( "("+ x +" , "+ y +")" + " is out of bounds");
            return null;
        }

        Piece p = this.pieceAt(x,y);
        
        if(p == null){
            System.out.println("there is no piece to remove");
            return null;
        }
        this.pieces_array[x][y] = null;
        return p;
    
    }
    
    public boolean canEndTurn() {
        if (this.selected_p != null){
            if (this.selected_p.hasCaptured() || this.has_moved){
                return true;
            }
        }
        return false;
    }
    
    public void endTurn() {
        this.fires_turn = !this.fires_turn;
        this.selected_p.doneCapturing();
        this.selected_p = null;
        this.has_moved = false;

    }
    
    public String winner() {
        int total_fire = 0;
        int total_water = 0;
        for (int x = 0; x < this.size; x++){
            for (int y = 0; y < this.size; y++) {
                if (this.pieces_array[x][y]!= null){
                    if(this.pieces_array[x][y].isFire()) {
                        total_fire += 1;
                    } 
                    else {
                        total_water += 1;
                    }
                }        
             }
         }
         if (total_fire == 0 && total_water == 0) {
             return "No one";
         }
         else if (total_fire == 0 && total_water > 0) {
             return "Water";      
         } 
         else if (total_water == 0 && total_fire > 0) {
             return "Fire";
         }
         else {
             return null;
         }     
     }
  
    //SECONDARY METHODS --------------------------------------------------------
    
    private void drawBoard() {
        StdDrawPlus.setXscale(0, this.size);
        StdDrawPlus.setYscale(0, this.size);
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if ((x + y) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                }
                StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
            }
        }
        if (this.is_empty == false) {
            this.drawPieces();
        }
    }
    
    private void drawPieces() {
        Piece p;
        String img_path;
        for(int x = 0; x < this.size; ++x) {
            for (int y = 0; y < this.size; ++y) {
                p = this.pieces_array[x][y];
                if (this.pieces_array[x][y] != null) {
                    img_path = "img/";
                    if (p.isBomb()) {
                        img_path += "bomb";
                    } 
                    else if (p.isShield()) {
                        img_path += "shield";
                    }
                    else {
                        img_path += "pawn";
                    }
                    if (p.isFire()) {
                        img_path += "-fire";
                    }  
                    else {
                        img_path += "-water";
                    }
                    if(p.isKing()) {
                        img_path += "-crowned";
                    }
                    img_path += ".png";
                    StdDrawPlus.picture(x + 0.5, y + 0.5, img_path, 1, 1);
                }  
            }
        }
    }

    private boolean OutOfBounds(int x, int y){
        if (((x > (this.size - 1)) || x < 0) || ((y > (this.size - 1)) || y < 0)) {
            return true;
        }
        return false;
    }
    private boolean validMove(int xf, int yf){

        if ( this.OutOfBounds(xf, yf) == false && this.pieceAt(xf,yf) == null){

            // if this piece is a fire!
            if (this.fires_turn == true) {

                if (Math.abs(xf - this.selected_x) == 1 && yf - this.selected_y == 1) {
                    return true;   
                } 
                // capturing
                else if (Math.abs(xf - this.selected_x) == 2 && yf - this.selected_y == 2) {
                    if (this.pieceAt((xf + this.selected_x)/ 2, (yf + this.selected_y) / 2).isFire() == false){
                            return true;
                    }
                }                                         

                // if fire is king // 
                else if (this.selected_p.isKing() == true){
                    if (Math.abs(xf - this.selected_x) == 1 && Math.abs(yf - this.selected_y) == 1){
                        return true;
                    }
                    if (Math.abs(xf - this.selected_x) == 2 && Math.abs(yf - this.selected_y) == 2){
                        if (this.pieceAt((xf + this.selected_x) /2, (yf + this.selected_y) / 2).isFire() == false){
                            return true;
                        }
                    }
                }
            }
            else{
                // water
                // if it is not capturing //
                if (Math.abs(xf - this.selected_x) == 1 && Math.abs(this.selected_y - yf) == 1) {
                   return true;   
                }

                else if (Math.abs(xf - this.selected_x) == 2 && this.selected_y - yf == 2) {
                    if (this.pieceAt((xf + this.selected_x) /2, (yf + this.selected_y) / 2).isFire() == false){
                            return true;
                    }
                }
                // if water is a king 
                else if(this.selected_p.isKing() == true){
                    if (Math.abs(xf - this.selected_x) == 1 || Math.abs(yf - this.selected_y) == 1){
                        return true;
                    }
                    if (Math.abs(xf - this.selected_x) == 2 || Math.abs(yf - this.selected_y) == 2){
                        if (this.pieceAt((xf + this.selected_x) /2, (yf + this.selected_y) / 2).isFire() == false){
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }
    return false;
    }
}

