public class Board {

    private Piece[][] pieces;
    private int N = 8;
    private int turn = 0;
    private Piece selected = null;
    private boolean moved = false;
    private int selectedX = -1;
    private int selectedY = -1;
    private int waterLives;
    private int fireLives;
    private String champion;

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[N][N];
        if (shouldBeEmpty){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    pieces[i][j] = null;
                }
            }
        }
        else{
            waterLives = 0;
            fireLives = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    pieces[i][j] = null;
                    if (j == 0){
                        if (i % 2 == 0){
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                            fireLives += 1;
                        }
                    }
                    if (j == 1){
                        if (i % 2 != 0){
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                            fireLives += 1;
                        }
                    }
                    if (j == 2){
                        if (i % 2 == 0){
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                            fireLives += 1;   
                        }
                    }
                    if (j == N-3){
                        if (i % 2 != 0){
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                            waterLives += 1;
                        }
                    }
                    if (j == N-2){
                        if (i % 2 == 0){
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                            waterLives += 1;
                        }
                    }
                    if (j == N-1){
                        if (i % 2 != 0){
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                            waterLives += 1;
                        }
                    }

                }
            }
        }
    }

    private void drawBoard(int N) {
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                    String img_file = "img/";
                    if (pieces[i][j].isBomb()){
                        img_file = img_file + "bomb-";
                    }
                    else if (pieces[i][j].isShield()){
                        img_file = img_file + "shield-";
                    }
                    else{
                        img_file = img_file + "pawn-";
                    }

                    if (pieces[i][j].isFire()){
                        img_file = img_file + "fire";
                    }
                    else{
                        img_file = img_file + "water";
                    }

                    if (pieces[i][j].isKing()){
                        img_file = img_file + "-crowned";
                    }
                    img_file = img_file + ".png";
                    StdDrawPlus.picture(i + .5, j + .5, img_file, 1, 1);
                }

            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x >= N || y >= N || x < 0 || y < 0){
            return null;
        }
        else{
            return pieces[x][y];
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        if (pieces[xi][yi].isKing()){
            if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2){
                int intermediateX = (xf + xi) / 2;
                int intermediateY = (yf + yi) / 2;
                if (pieces[intermediateX][intermediateY] != null && pieces[intermediateX][intermediateY].side() != pieces[xi][yi].side()){
                    return true;
                }
                return false;
            }
            else if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1 && !moved){
                if (pieces[xf][yf] == null){
                    return true;
                }
            }
        }
        else if (pieces[xi][yi].isFire()){
            if (Math.abs(xf-xi) == 2 && yf-yi == 2){
                int intermediateX = (xf + xi) / 2;
                int intermediateY = (yf + yi) / 2;
                if (pieces[intermediateX][intermediateY] != null && pieces[intermediateX][intermediateY].side() != pieces[xi][yi].side()){
                    return true;
                }
                return false;
            }
            else if (Math.abs(xf-xi) == 1 && yf-yi == 1 && !moved){
                if (pieces[xf][yf] == null){
                    return true;
                }
            }
        }
        else {
            if (Math.abs(xf-xi) == 2 && yf-yi == -2){
                int intermediateX = (xf + xi) / 2;
                int intermediateY = (yf + yi) / 2;
                if (pieces[intermediateX][intermediateY] != null && pieces[intermediateX][intermediateY].side() != pieces[xi][yi].side()){
                    return true;
                }
                return false;
            }
            else if (Math.abs(xf-xi) == 1 && yf-yi == -1 && !moved){
                if (pieces[xf][yf] == null){
                    return true;
                }
            }
        }
        return false;

    }

    public boolean canSelect(int x, int y){
        if (pieces[x][y] != null && pieces[x][y].side() == turn){
            if (selected == null || moved == false){
                return true;
            }
        }
        else if (selected != null) {
            if (moved == false){
                if (validMove(selectedX,selectedY,x,y)){ //how to get position?
                    return true;
                }
            }
            else if (selected.hasCaptured()){
                if (validMove(selectedX,selectedY,x,y)){
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y){
        if (pieces[x][y] != null){
            selected = pieces[x][y];
            selectedX = x;
            selectedY = y;
        }
        else{
            selected.move(x, y);
            selectedX = x;
            selectedY = y;
            moved = true;
            if (pieces[selectedX][selectedY] == null){
                selectedY = -1;
                selectedX = -1;
            }
        }
    }

    public void place(Piece p, int x, int y){
        if (x >= N || y >= N || x < 0 || y < 0 ||  p == null){
            return;
        }
        else{
            if (p.isFire()){
                fireLives += 1;
            }
            else{
                waterLives += 1;
            }
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y){
        if (x >= N || y >= N || x < 0 || y < 0){
            return null;
        }

        Piece removed = pieces[x][y];
        if (removed == null){
            return null;
        }
        
        if (removed.isFire()){
            fireLives -= 1;
        }
        else{
            waterLives -= 1;
        }

        pieces[x][y] = null;

        return removed;
    }

    public boolean canEndTurn(){
        if (moved == true){
            return true;
        }
        return false;
    }

    public void endTurn(){
        selected.doneCapturing();
        selected = null;
        moved = false;
        turn = 1 - turn;
        selectedX = -1;
        selectedY = -1;
    }

    public String winner(){
        if (fireLives == waterLives && fireLives == 0){
            champion = "No one";
        }
        else if (fireLives == 0){
            champion = "Water"; 
        }
        else if (waterLives == 0){
            champion = "Fire"; 
        }
        return champion;
    }

    public static void main(String[] args){
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {

            b.drawBoard(8);

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)){
                    b.select((int) x, (int) y);
                    
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if (b.canEndTurn()){
                    b.endTurn();
                }
            }            
            StdDrawPlus.show(100);
        }
    }
}