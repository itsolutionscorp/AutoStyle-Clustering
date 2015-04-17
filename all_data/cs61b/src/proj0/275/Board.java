public class Board{

    Piece[][] board = new Piece[8][8];
    boolean playerFire = true;
    boolean playerHasMoved = false;
    static int x_clicked, y_clicked;
    Piece selectedPiece = null;
    int x_coordinate, y_coordinate;
    int fire_pieces = 12;
    int water_pieces = 12;

    public Board(boolean shouldBeEmpty) {
        if(!shouldBeEmpty){
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 8; j+=2){
                    if (i == 0) {
                        board[j][i] = new Piece(true, this, j, i, "pawn");
                        board[j+1][5] = new Piece(false, this, j, 5, "bomb");  
                    } else if (i == 1) {
                        board[j+1][i] = new Piece(true, this, j, i, "shield");
                        board[j][6] = new Piece(false, this, j, 6, "shield");                             
                    } else {
                        board[j][i] = new Piece(true, this, j, i, "bomb");
                        board[j+1][7] = new Piece(false, this, j, 7, "pawn"); 
                    }
                // Piece test = new Piece(false, this, 4,4,"bomb");
                // test.isCrowned = true;
                // board[4][4] = test;
                }
            }
            drawBoard();
        } else {
            drawBoard();
        }
    }


    private void drawBoard(){
        //Maybe make another void method that just draws the board so that
        //it doesn't have to draw it every single time.
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i=0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((i + j) % 2 != 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY); }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY); }
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5); 
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if (pieceAt(i, j) != null){
                    if (pieceAt(i,j).isFire()){
                        if (pieceAt(i, j).isCrowned){
                            if (pieceAt(i, j).isShield()){
                                StdDrawPlus.picture(i +.5, j +.5, "img/shield-fire-crowned.png", 1, 1);
                            } else if (pieceAt(i, j).isBomb()){
                                StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png", 1,1);
                            } else {
                                StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1,1);
                            }
                        } else if (pieceAt(i, j).isShield()){
                            StdDrawPlus.picture(i +.5, j +.5, "img/shield-fire.png", 1, 1);
                        } else if (pieceAt(i, j).isBomb()){
                            StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire.png", 1,1);
                        } else {
                            StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire.png", 1,1);
                        }
                    } else {
                        if (pieceAt(i, j).isCrowned){
                            if (pieceAt(i, j).isShield()){
                                StdDrawPlus.picture(i +.5, j +.5, "img/shield-water-crowned.png", 1, 1);
                            } else if (pieceAt(i, j).isBomb()){
                                StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water-crowned.png", 1,1);
                            } else {
                                StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water-crowned.png", 1,1);
                            }
                        } else if (pieceAt(i, j).isShield()){
                            StdDrawPlus.picture(i +.5, j +.5, "img/shield-water.png", 1, 1);
                        } else if (pieceAt(i, j).isBomb()){
                            StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water.png", 1,1);
                        } else {
                            StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water.png", 1,1);
                        }
                    }
                }
            // StdDrawPlus.show(); //Don't know what this does. ask tomorrow.
            }
        } 
    }
    


    public Piece pieceAt(int x, int y){
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return null; } 
        else if (board[x][y] == null) {
            return null;
        } else { 
            return board[x][y]; 
        }
    }


    private boolean canCapture(int x, int y){
        if (((x-2 >= 0) &&  (y-2 >= 0)) && (pieceAt(x-2, y-2) == selectedPiece)) {
            //.isKing() || pieceAt(x-2,y-2).isFire()){
            if (pieceAt(x-1, y-1) == null) {
                return false;
            } else if (pieceAt(x, y) != null){
                return false;
            } else if (pieceAt(x-1, y-1).isFire() == pieceAt(x-2, y-2).isFire()) {
                return false;
            } else {
                return true;
            }
        } else if (((x+2<7) && (y-2>=0)) && (pieceAt(x+2, y-2) == selectedPiece)) {
            // if(pieceAt(x+2, y-2) == selectedPiece){ //.isKing() || pieceAt(x+2,y-2).isFire()){
                if (pieceAt(x+1, y-1) == null) {
                    return false;
                } else if (pieceAt(x, y) != null){
                    return false;
                } else if (pieceAt(x+1, y-1).isFire() == selectedPiece.isFire()) {
                    return false;
                } else {
                    return true;
                }
            // }            
        } else if (((x-2>=0) && (y+2<7)) && (pieceAt(x-2, y+2) == selectedPiece)) {
            // if(pieceAt(x-2, y+2) == selectedPiece){ //.isKing() || !pieceAt(x-2,y+2).isFire()){
                if (pieceAt(x-1, y+1) == null) {
                    return false;
                } else if (pieceAt(x, y) != null){
                    return false;
                } else if (pieceAt(x-1, y+1).isFire() == pieceAt(x-2, y+2).isFire()) {
                    return false;
                } else {
                    return true;
                }
            // }
        } else if (((x+2<7) && (y+2<7)) && (pieceAt(x+2, y+2) == selectedPiece)) {
            // if(pieceAt(x+2, y+2) == selectedPiece){ //.isKing() || !pieceAt(x+2,y+2).isFire()){
                if (pieceAt(x+1, y+1) == null) {
                    return false;
                } else if (pieceAt(x, y) != null){
                    return false;
                } else if (pieceAt(x+1, y+1).isFire() == pieceAt(x+2, y+2).isFire()) {
                    return false;
                } else {
                    return true;
                }
            }
        // }
        return false;
    }


    public boolean canSelect(int x, int y){
        if(((x + y) % 2 != 0) || (x < 0 || x > 7) || (y < 0 || y > 7)) {
            return false;
        } else if (selectedPiece == null) {
            if ((pieceAt(x, y) != null) && (pieceAt(x,y).isFire() == playerFire)){
                return true;
            } else {
                return false;
            }
        } else if (pieceAt(x,y) == null){
            if(selectedPiece.isFire() || selectedPiece.isKing()) {
                if(pieceAt(x-1, y-1) == selectedPiece){
                    return true;
                } else if (pieceAt(x+1, y-1)== selectedPiece) {
                    return true;
                } else if (canCapture(x,y)){
                    return true;
                } else{
                    return false;
                }
            } else if (!selectedPiece.isFire() || selectedPiece.isKing()) {
                if (pieceAt(x-1, y+1)== selectedPiece) {
                    return true;
                } else if (pieceAt(x+1, y+1) == selectedPiece) {
                    return true;
                } else if (canCapture(x,y)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void select(int x, int y){
        if (pieceAt(x, y) !=null){
            x_coordinate = x;
            y_coordinate = y;
            selectedPiece = pieceAt(x, y);
        } else {
            board[x][y] = selectedPiece;
            board[x_coordinate][y_coordinate] = null;
            drawBoard();
        }
    }

    public void place(Piece p, int x, int y) {
        if(((x < 0 || x > 7) || (y < 0 || y > 7)) || (p == null)) {
        } else{
            board[x][y] = p;
        }
    }

    public Piece remove(int x, int y){
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            System.out.println("input (" +x+ ","+y+" is out of bounds.");
            return null;
        } else if (pieceAt(x, y) == null){
            System.out.println("No piece at (" + x + "," + y + ").");
            return null;
        } else {
            Piece temp = pieceAt(x, y);
            if(temp.isFire()){
                fire_pieces --;
            } else{
                water_pieces --;
            }
            board[x][y] = null;
            return temp;
        }
    }

    public boolean canEndTurn(){
        if(playerFire && playerHasMoved){
            return true;
        } else{
            return false;
        }
    }

    public void endTurn(){
        if(playerFire){
           playerFire = false;
        } else {
            playerFire = true;
        }
        drawBoard();
    }

    public String winner() {
        if (fire_pieces == water_pieces && water_pieces ==0) {
            return "No one";
        } else if (water_pieces == 0){
            return "Fire";
        } else if (fire_pieces == 0){
            return "Water";
        } else{
            return null;
        }
    }


	public static void main(String[] args) {

        Board lilB = new Board(false);

	}
}
