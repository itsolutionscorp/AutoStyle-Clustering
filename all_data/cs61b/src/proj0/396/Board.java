public class Board{

    private Piece[][] pieces = new Piece[8][8];
    private boolean FireMove = true;
    private int FireSelectedX;
    private int FireSelectedY;
    private boolean FireSelected = false;
    private boolean WaterSelected = false;
    private int WaterSelectedX;
    private int WaterSelectedY;
    private boolean FireMoved = false;
    private boolean WaterMoved = false;
    private boolean BombCaptured = false;



    public Board(boolean ShouldBeEmpty){
        if (ShouldBeEmpty == false){
            placeFire();
            placeWater();
        }


    }

    private void placeFire(){
        for (int i = 0; i < 3; i++){
            if (i == 0){
                for (int j = 0; j < 8; j+=2){
                    Piece new_piece = new Piece(true, this, j, i, "pawn");
                    place(new_piece, j, i); 

                }
            }
            

            else if (i == 1){
                for (int j = 1; j < 8; j+=2){
                    Piece new_piece = new Piece(true, this, j, i, "shield");
                    place(new_piece, j, i); 
                }
            }

            else{
                for (int j = 0; j < 8; j+=2){
                    Piece new_piece = new Piece(true, this, j, i, "bomb");
                    place(new_piece, j, i); 
                }
            }
        }
    }

    private void placeWater(){
        for (int i = 7; i > 4; i--){
            if (i == 7){
                for (int j = 1; j < 8; j+=2){
                    Piece new_piece = new Piece(false, this, j, i, "pawn");
                    place(new_piece, j, i); 
                }
            }
            

            else if (i == 6){
                for (int j = 0; j < 8; j+=2){
                    Piece new_piece = new Piece(false, this, j, i, "shield");
                    place(new_piece, j, i); 
                }
            }

            else{
                for (int j = 1; j < 8; j+=2){
                    Piece new_piece = new Piece(false, this, j, i, "bomb");
                    place(new_piece, j, i); 
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7 || x < 0|| y < 0){
            return null;
        }
        return pieces[y][x];


    }

    public boolean canSelect(int x, int y){
        if (BombCaptured){
            System.out.println("cannot select after bomb explosion");
            return false;
        }

        if (FireMove){
            if (FireSelected){
                if (pieceAt(FireSelectedX, FireSelectedY).hasCaptured() == false){
                    System.out.println("no capture");
                    if (FireMoved == true)  return false;
                    else if (FireSelectedX == x && FireSelectedY == y)  return true;
                    else if (pieceAt(x, y) != null && pieceAt(x, y).isFire()){
                        System.out.println("select another piece");
                        return true;
                    }
                    return validMove(FireSelectedX, FireSelectedY, x, y);
                }
                else if (Math.abs(FireSelectedX-x) == 1 && Math.abs(FireSelectedY-y) == 1){
                    System.out.println("cannot move regularly after capturing");
                    return false;
                }
                else if (pieceAt(x, y) != null)   return false;
                else return validMove(FireSelectedX, FireSelectedY, x, y);

            }

            else{

                if (pieceAt(x, y) != null && pieceAt(x, y).isFire())               return true;
                
            }
        }

        else{
            if (WaterSelected){
                if (pieceAt(WaterSelectedX, WaterSelectedY).hasCaptured() == false){
                    if (WaterMoved)   return false;
                    else if (WaterSelectedX == x && WaterSelectedY == y)    return true;
                    else if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == false){
                        System.out.println("selected another piece");
                        return true;
                    }
                    return validMove(WaterSelectedX, WaterSelectedY, x, y);
                }
                else if (Math.abs(WaterSelectedX-x) == 1 && Math.abs(WaterSelectedY-y) == 1)      return false;
                else if (pieceAt(x, y) != null)   return false;
                return validMove(WaterSelectedX, WaterSelectedY, x, y);
            }
            else{
                if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == false)       return true;
            }
        }

        return false;
    }
    

    private boolean validMove(int xi, int yi, int xf, int yf){
        //check direction, only two directions for regular pieces and four directions for king pieces
        //make sure the pieces can onyl move in diagonals
        Piece capture_piece = pieces[(yf+yi)>>>1][(xi+xf)>>>1];
        if (((xi - xf)*(xi - xf) + (yi - yf)*(yi - yf)) < 2)  return false;

        else if (pieceAt(xi, yi).isKing() == false){
            if (pieceAt(xi, yi).isFire()){
                if ((yf-yi) < 0)  return false;
            }
            else if ((yf-yi) > 0)   return false;
        }

        if (pieces[yf][xf] == null){
            if (Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1)   return true;
            else if (Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2){
                if (capture_piece != null && capture_piece.side() == Math.abs(pieceAt(xi, yi).side() - 1)){
                    return true;
                }
            }
        }

        return false;
    }
    


    public void select(int x, int y){
        //only called when canSelect has passed!
        if (FireMove){

            if (FireSelected == false){
                if (pieceAt(x, y) != null){
                    FireSelectedX = x;
                    FireSelectedY = y;
                    FireSelected = true;
                }
            }

            else{
                if (Math.abs(x-FireSelectedX) == 1 && Math.abs(y-FireSelectedY) == 1)           FireMoved = true;
                if (pieceAt(FireSelectedX, FireSelectedY).isBomb() && Math.abs(x-FireSelectedX) == 2 && Math.abs(y-FireSelectedY) == 2){
                    System.out.println("Bomb captured");
                    BombCaptured = true;
                }
                if (FireSelectedX != x && FireSelectedY != y)         pieceAt(FireSelectedX, FireSelectedY).move(x, y);
                FireSelectedX = x;
                FireSelectedY = y;

            }
        }

        else{

            if (WaterSelected == false){
                if (pieceAt(x, y) != null){
                    WaterSelected = true;
                    WaterSelectedX = x;
                    WaterSelectedY = y;
                }
            }
            else{
                if (Math.abs(x-WaterSelectedX) == 1 && Math.abs(y-WaterSelectedY) == 1)          WaterMoved = true;
                if (pieceAt(WaterSelectedX, WaterSelectedY).isBomb() && Math.abs(x-WaterSelectedX) == 2 && Math.abs(y-WaterSelectedY) == 2){
                    System.out.println("Bomb captured");
                    BombCaptured = true;
                }
                if (WaterSelectedX != x && WaterSelectedY != y)    pieceAt(WaterSelectedX, WaterSelectedY).move(x, y);
                WaterSelectedX = x;
                WaterSelectedY = y;
            }
        }
    }


    public void place(Piece p, int x, int y){
        if (x > 7 || y > 7 || x < 0|| y < 0 || p == null){
            return;
        }

        pieces[y][x] = p;
        

    }

    public Piece remove(int x, int y){
        if (x > 7 || y > 7 || x < 0|| y < 0){
            System.out.println("Coordinates out of bounds!");
            return null;
        }
        else if (pieces[y][x] == null){
            System.out.println("You are trying to remove a non-existent piece at " + x + " " + y);
            return null;
        }
        Piece p = pieces[y][x];
        pieces[y][x] = null;
        return p;

    }

    public boolean canEndTurn(){

        if (FireMove){
            if (pieceAt(FireSelectedX, FireSelectedY) != null)         return (FireMoved || pieceAt(FireSelectedX, FireSelectedY).hasCaptured());
        }
        else{
            if (pieceAt(WaterSelectedX, WaterSelectedY) != null)       return (WaterMoved || pieceAt(WaterSelectedX, WaterSelectedY).hasCaptured());
        }
        System.out.println("bomb end turn");

        return true;
    }

    public void endTurn(){
        System.out.println("end turn");
        if (pieceAt(FireSelectedX, FireSelectedY) != null)            pieceAt(FireSelectedX, FireSelectedY).doneCapturing();
        if (pieceAt(WaterSelectedX, WaterSelectedY) != null)          pieceAt(WaterSelectedX, WaterSelectedY).doneCapturing();
        FireMoved = false;
        WaterMoved = false;
        FireSelected = false;
        WaterSelected = false;
        FireMove = !FireMove;
        BombCaptured = false;
        //WaterMove = !FireMove;
    }


    public String winner(){
        int fireCount = 0;
        int waterCount = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (pieceAt(i, j)!= null){
                    if (pieceAt(i, j).isFire())   fireCount += 1;
                    else                          waterCount += 1;
                }
            }
        }
        if (fireCount == 0 && waterCount == 0)   return "No one";
        else if (waterCount == 0)  return "Fire";
        else if (fireCount == 0)  return "Water";
        else return null;
    }

//_________________________________________________________________________________________________________
//GUI stuff

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }                 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        for (int y = 0; y < 8; y ++){
            for (int x = 0; x < 8; x++){
                if (pieces[y][x] != null)          StdDrawPlus.picture(x + .5, y + .5, image_file(pieces[y][x]), 1, 1);
            }
        }
    }

    private void CheckClick(){
        if (StdDrawPlus.mousePressed()){
            int x = (int) StdDrawPlus.mouseX();
            int y = (int) StdDrawPlus.mouseY();
            System.out.println("Select");
            // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            // StdDrawPlus.filledSquare(x + .5, y + .5, .5);

            if (canSelect(x, y)){
                select(x, y);
            }
            
        }
    }

    private void CheckSpace(){

        if (StdDrawPlus.isSpacePressed()){
            if (canEndTurn()){
                endTurn();
            }

        }

    }

    private String image_file(Piece p){
        String name;
        if (p.isShield())         name = "shield";
        else if (p.isBomb())      name = "bomb";
        else                      name = "pawn";



        if (p.isFire()){
            if (p.isKing()){
                return "img/" + name + "-fire-crowned.png";
            }
            return "img/" + name + "-fire.png";  
            
        }

        else{
            if (p.isKing())           return "img/" + name + "-water-crowned.png";
            
            return "img/" + name + "-water.png";
        }
        
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        while(true){
            b.CheckClick();
            b.CheckSpace();

            b.drawBoard();

            if (b.winner() != null)              return; 

            StdDrawPlus.show(100);
        }
    }
}
