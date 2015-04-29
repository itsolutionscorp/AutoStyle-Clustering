public class Board{ 
	private Piece[][] alphaArray = new Piece[8][8];
    private int selectedX = -1;
    private int selectedY = -1;
    private boolean moved = false;
    private int turn = 0;
    private boolean hasSelected = false;


// make a method that keeps track of whose turn it is
// you can make it switch between 0 and 1 to see if it is fair to move

    public static void main(String[] args){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        Board b = new Board(false);
        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int xClick = (int) (StdDrawPlus.mouseX());
                int yClick = (int) (StdDrawPlus.mouseY());
                if (b.canSelect(xClick, yClick)){
                    b.select(xClick, yClick);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                b.endTurn();
            }
            StdDrawPlus.show(100);
        }
    }

    public boolean canSelect(int x, int y){
        if ((alphaArray[x][y] != null) && (inBounds(x,y)) && (alphaArray[x][y].side() == turn)){
            if ((hasSelected == false) || (moved == false)){
                System.out.println("I have selected a piece.");
                return true;
            }
        }
        if ((alphaArray[x][y] == null) && (inBounds(x,y)) && (hasSelected)) {
            System.out.println("I have selected an empty spot.");
            if ((moved == false) && validMove(selectedX, selectedY, x, y)) { //I am not absolutely sure if this will work.
                return true;
            }
            if ((moved == true) && (alphaArray[selectedX][selectedY] != null) && (alphaArray[selectedX][selectedY].hasCaptured())) { //check alphaArray[selectedX][selectedY] != null because this will tell you if the piece is in the spot where it already landed from its first capture
                if ((Math.abs(y - selectedY)) == 2){ //this checks if the second capture is two y units away from original landing after inital capture
                    return validMove(selectedX, selectedY, x, y);
                } 
            }
        }
        return false;
    }

    public void select(int x, int y){
        if ((hasSelected == true) && validMove(selectedX, selectedY, x, y)) {
            Piece selectedPiece = pieceAt(selectedX, selectedY);
            selectedPiece.move(x, y);
            moved = true;
            if (alphaArray[x][y].isBomb() && alphaArray[x][y].hasCaptured()){
                explode(x,y, alphaArray[x][y].side());
                remove(x,y);
            }


        }
        if(hasSelected == false){
            // System.out.println("I called the select method and I don't have anything selected");
            selectedX = x;
            selectedY = y;
            System.out.println("My selectedX value is:" + selectedX);
            System.out.println("My selectedY value is:" + selectedY);
            // System.out.println("I just assigned the values to selectedX and selectedY");
            hasSelected = true;
        }
    }


    private boolean inBounds(int x, int y){
        if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        if (inBounds(xi, yi) && inBounds(xf, yf) && (alphaArray[xf][yf] == null) && (alphaArray[xi][yi] != null)) {

            if (alphaArray[xi][yi].isKing()){

                // Regular king movement
                if (((xi - 1 == xf) || (xi + 1 == xf)) && ((yi - 1 == yf) || (yi + 1 == yf))) {
                return true;
                }

                // Capture cases for kings regardless of color start.
                if (yi + 2 == yf){
                    if (xi + 2 == xf){
                        if ((alphaArray[xi+1][yi+1] != null) && (alphaArray[xi][yi].isFire() != alphaArray[xi + 1][yi + 1].isFire())){
                            return true;
                        }
                    }
                    if (xi - 2 == xf){
                        if ((alphaArray[xi-1][yi+1] != null) && (alphaArray[xi][yi].isFire() != alphaArray[xi - 1][yi + 1].isFire())){
                            return true;
                        }
                    }
                }
                if (yi - 2 == yf){
                    if (xi + 2 == xf){
                        if ((alphaArray[xi+1][yi-1] != null) && (alphaArray[xi][yi].isFire() != alphaArray[xi + 1][yi - 1].isFire())){
                            return true;
                        }
                    }
                    if (xi - 2 == xf){
                        if ((alphaArray[xi-1][yi-1] != null) && (alphaArray[xi][yi].isFire() != alphaArray[xi - 1][yi - 1].isFire())){
                            return true;
                        }
                    }
                }
                // Capture cases for kings regardless of color end.
            }


            if ((yi + 1 == yf) && alphaArray[xi][yi].isFire()){ /* regular movement of fire pieces*/
                if ((xi - 1 == xf) || (xi + 1 == xf)){
                    return true;
                }
            }

            if ((yi + 2 == yf) && alphaArray[xi][yi].isFire()){ /* regular capture movement of fire pieces*/
                if ((xi - 2 == xf) && (alphaArray[xi-1][yi+1] != null) && (alphaArray[xi-1][yi+1].isFire() == false)){
                    return true;
                }
                if ((xi + 2 == xf) && (alphaArray[xi+1][yi+1] != null) && (alphaArray[xi+1][yi+1].isFire() == false)){
                    return true;
                }
            }


            if ((yi - 1 == yf) && alphaArray[xi][yi].isFire() == false){ /* regular movement of water pieces*/
                if ((xi - 1 == xf) || (xi + 1 == xf)){
                    return true;
                }
            }

            if ((yi - 2 == yf) && alphaArray[xi][yi].isFire() == false){ /* regular capture movement of water pieces*/
                if ((xi - 2 == xf) && (alphaArray[xi-1][yi-1] != null) && (alphaArray[xi-1][yi-1].isFire())){
                    return true;
                }
                if ((xi + 2 == xf) && (alphaArray[xi+1][yi+1] != null) && (alphaArray[xi+1][yi-1].isFire())){
                    return true;
                }
            }


        }

        System.out.println("checked valid move");
        return false;
    }



    public Piece pieceAt(int x, int y){
        if (inBounds(x, y)){
            return alphaArray[x][y];
        }
        else{
            return null;
        }
    }

    public void place(Piece p, int x, int y){
        if (inBounds(x, y)){
            alphaArray[x][y] = p;
        }
    }

    public Piece remove(int x, int y){
        if (inBounds(x, y)){
            if (pieceAt(x,y) == null){
                System.out.println("There is no piece at (" + x + " "  + y + ")");
                return null;
            }
            Piece removedPiece = pieceAt(x,y);
            alphaArray[x][y] = null;
            return removedPiece;
        }
        else{
            System.out.println("The coordinate does not exist.");
            return null;
        }
    }

    public boolean canEndTurn(){
        return moved;
    }

    public void endTurn(){
        if (canEndTurn()){
            hasSelected = false;
            moved = false;
            selectedX = -1;
            selectedY = -1;
            if (turn == 0){
                System.out.println("Red's turn is over");
                turn = 1;
            }
            else if (turn == 1){
                turn = 0;
            }
        }
    }

    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty){
            return;
        }
        else{
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i % 2 == 1) && (j == 7)){ //blue pawn
                        alphaArray[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                    if ((i % 2 == 0) && (j == 6)){ //blue shield
                        alphaArray[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    if ((i % 2 == 1) && (j == 5)){ //blue bomb
                        alphaArray[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    if ((i % 2 == 0) && (j == 2)){ //red bomb
                        alphaArray[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    if ((i % 2 == 1) && (j == 1)){ //red shield
                        alphaArray[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    if ((i % 2 == 0) && (j == 0)){ //red pawn
                        alphaArray[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                }
            }
        }
    }

    private void explode(int x, int y, int side){
        for (int j = x - 1; j <= x + 1; j++){
            for (int k = y - 1; k <= y + 1; k++){
                if ((alphaArray[j][k] != null) && (alphaArray[j][k].isShield() == false)) {
                    if (inBounds(j,k)){
                        remove(j, k);
                    }
                }
            }
        }
    }

	private void drawBoard() { 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((selectedX == i) && (selectedY == j)){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (alphaArray[i][j] != null){
                    if (alphaArray[i][j].isKing()){
                        if (alphaArray[i][j].isFire()){
                            if (alphaArray[i][j].isBomb() && alphaArray[i][j].isFire()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            if (alphaArray[i][j].isShield() && alphaArray[i][j].isFire()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            if (alphaArray[i][j].isBomb() == false && alphaArray[i][j].isShield() == false && alphaArray[i][j].isFire()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        if (alphaArray[i][j].isFire() == false){
                            if (alphaArray[i][j].isBomb() && alphaArray[i][j].isFire() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            if (alphaArray[i][j].isShield() && alphaArray[i][j].isFire() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            if (alphaArray[i][j].isBomb() == false && alphaArray[i][j].isShield() == false && alphaArray[i][j].isFire() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }                        
                    }
                    if (alphaArray[i][j].isKing() == false){
                        if (alphaArray[i][j].isFire()){
                            if (alphaArray[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            if (alphaArray[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            if (alphaArray[i][j].isBomb() == false && alphaArray[i][j].isShield() == false){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                        if (alphaArray[i][j].isFire() == false){
                            if (alphaArray[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            if (alphaArray[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            if (alphaArray[i][j].isBomb() == false && alphaArray[i][j].isShield() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            } 
                        }                       
                    }
                }
            }
        }
    }
}