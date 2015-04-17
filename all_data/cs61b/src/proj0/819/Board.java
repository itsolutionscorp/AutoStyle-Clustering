public class Board{
    private int turn = 0;
    private int fireTeam = 0;
    private int waterTeam = 0;
    private Piece selected = null;
    private int selectedX;
    private int selectedY;  
    private boolean moved = false;
    private Piece[][] pieces = new Piece[8][8];

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board board = new Board(false);
        while(true){
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int)x, (int)y)){
                    board.select((int)x, (int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed() && board.canEndTurn()){
                board.endTurn();
            }
            board.drawBoard();
            StdDrawPlus.show(100);
        }
    }  

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceAt(i, j) != null){
                    Piece p = pieceAt(i, j);
                    if (p.isFire()){
                        if (p.isKing()){
                            if(p.isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if(p.isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i +.5, j +.5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(p.isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if(p.isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else{
                        if (p.isKing()){
                            if(p.isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if(p.isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i +.5, j +.5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(p.isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if(p.isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (!(shouldBeEmpty) && ((i + j) % 2 == 0)){
                    if(j == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    else if(j == 1){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);   
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }   
                    else if(j == 2){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    else if (j == 5){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                    else if (j == 6){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    else if (j == 7){
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
                else{
                    pieces[i][j] = null; 
                }   
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x >= 8 || x < 0 || y >= 8 || y < 0){
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y){
        //selecting piece
        if (x >= 8 || x < 0 || y >= 8 || y < 0){
            return false; 
        }
        else if (pieces[x][y] != null) {
            if(pieces[x][y].side() == turn){
                if (selected != null){
                    if (!(selected.hasCaptured()) && !(moved)){
                        return true;
                    }
                    else{
                        return false; 
                    }
                }
                else{
                    return true; 
                }
            }
        }
        //selecting place to move or to land after capture
        else if (pieces[x][y] == null && selected != null && validMove(selectedX, selectedY, x, y)){
            if(!(moved)){
                return true;
            }
            else if(moved && selected.hasCaptured()){
                return true; 
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        if (selected.hasCaptured() == false && pieces[xf][yf] == null && (xf + yf) % 2 == 0 && Math.abs(xf - xi) == 1){
            if (selected.isKing() && Math.abs(yf - yi) == 1){
                return true;
            }
            else if (selected.isFire() && (yf - yi) == 1){
                return true;
            }
            else if (!(selected.isFire()) && (yf - yi) == -1){
                return true;
            }
        }
        else if (pieces[xf][yf] == null && (xf + yf) % 2 == 0 && Math.abs(xf - xi) == 2 && pieces[(xi + xf)/2][(yi + yf)/2] != null){
            if (selected.isKing() && Math.abs(yf - yi) == 2){
                if(pieces[(xi + xf)/2][(yi + yf)/2].isFire() != selected.isFire()){
                    return true;
                }
            }
            else if ((yf - yi) == 2 && pieces[(xi + xf)/2][(yi + yf)/2].isFire() != selected.isFire()){
                return true;
            }
            else if ((yf - yi) == -2 && pieces[(xi + xf)/2][(yi + yf)/2].isFire() != selected.isFire()){
                return true;
            }
        }
        return false;
    }   

    public void select(int x, int y){
        if (pieceAt(x, y) != null || selected == null){
            selected = pieces[x][y];
            selectedX = x; 
            selectedY = y;
        }
        else if (!(moved) || selected.hasCaptured()){
            selected.move(x, y);
            moved = true;
        }
    }

    public void place(Piece p, int x, int y){
        if (!((x >= 8 || x < 0 || y >= 8 || y < 0)) && p != null){
            pieces[x][y] = p;
            selectedX = x;
            selectedY = y;
        }
    }

    public Piece remove(int x, int y){
        if (!((x >= 8 || x < 0 || y >= 8 || y < 0))){
            if (pieceAt(x, y) == null){
                System.out.println("No piece at location");
                return null;
            }
            else{
                Piece piece = pieceAt(x, y);
                pieces[x][y] = null;
                return piece;
            }
        }
        else{
            System.out.println("Out of bounds");
            return null; 
        }
    }

    public boolean canEndTurn(){
        return moved;
    }
    
    public void endTurn(){
        if (turn == 0){
            turn = 1;
        }
        else{
            turn = 0;
        }
        selected.doneCapturing(); 
        moved = false;
        selected = null;
        winner();
    }

    public String winner(){
        fireTeam = 0; 
        waterTeam = 0; 
        for (Piece[] piece: pieces){
            for (Piece p: piece){
                if (p != null){
                    if (p.isFire()){
                        fireTeam += 1; 
                    }
                    else{
                        waterTeam += 1; 
                    }
                }
            }
        }
        if ((fireTeam == 0) && (waterTeam == 0)){
            return "No one"; 
        }
        else if (fireTeam == 0){
            return "Water";
        }
        else if (waterTeam == 0){
            return "Fire";
        }
        return null; 
    }
}