public class Board{
    private Piece[][] pieces;
    private int N;
    private boolean firefox = true;
    private Piece selected;
    private int selectedx;
    private int selectedy;
    private boolean moved = false;
    public static void main(String[] args) {        
        int N = 8;
        
        Board b = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
                if (StdDrawPlus.mousePressed()) {
                    int x = (int) StdDrawPlus.mouseX();
                    int y = (int) StdDrawPlus.mouseY();
                    if (b.canSelect(x, y)){
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                        b.select(x, y);
                    }

                }
                if (StdDrawPlus.isSpacePressed()){
                    if (b.canEndTurn()){
                        System.out.println("End Turn");
                        b.endTurn();
                    }
                }
            StdDrawPlus.show(100); 
            if (b.winner() != null){
                break; 
            }  
        }
        System.out.println(b.winner());
    }
    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){StdDrawPlus.setPenColor(StdDrawPlus.GRAY);    
                }
                else                   StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece p = pieces[i][j];
                if (p != null){
                    if (p.isKing()){
                        if (p.isFire() && p.isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        if (p.isFire() && p.isShield())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        if (!p.isFire() && p.isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        if (!p.isFire() && p.isShield())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        if (p.isFire() && !p.isShield() && !p.isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        if (!p.isFire() && !p.isShield() && !p.isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                    else{
                    if (p.isFire() && p.isBomb())
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    if (p.isFire() && p.isShield())
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    if (!p.isFire() && p.isBomb())
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    if (!p.isFire() && p.isShield())
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    if (p.isFire() && !p.isShield() && !p.isBomb())
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    if (!p.isFire() && !p.isShield() && !p.isBomb())
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                    
                }
            }
        }
        
    }

    public Board(boolean shouldBeEmpty){
        int N = 8;
        pieces = new Piece[N][N];
        if (shouldBeEmpty == false){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0){
                        if (j == 0){
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                        if (j == 1){
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        if (j == 2){
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        if (j == 5){
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        if (j == 6){
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        if (j == 7){
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                }
                }
        }
    }
    }

    public Piece pieceAt(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7)
            return null;
        return pieces[x][y];

    }
    public boolean canSelect(int x, int y){
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7){   
                // if you havent selected a piece yet and you havent moved, return True
                if ((pieceAt(x, y)) != null && selected == null && !moved) {
                    if (firefox && (pieceAt(x, y)).isFire() || !firefox && !((pieceAt(x, y)).isFire())){
                        return true;
                    }
                }
                
                // if you have selected a piece, then check if it is a valid move
                if (selected != null && !moved){
                    if (validMove(selectedx, selectedy, x, y)){
                            return true;
                        }
                }
                // Double jump?
                if (selected != null && selected.hasCaptured()){
                    if (validMove(selectedx, selectedy, x, y)){
                            if(Math.abs(selectedx - x) == 2 && Math.abs(selectedy - y) == 2){
                                return true;
                            }
                            else{
                                return false;
                            }
                    }
                    
                }
                // if you have selected but havent moved, return true
                if (selected != null && !moved && (pieceAt(x, y)) != null){
                    if (firefox && (pieceAt(x, y)).isFire() || !firefox && !((pieceAt(x, y)).isFire())){
                        return true;
                    }
                }   
            
        
        }return false;
    }
    private boolean validMove(int xi, int yi, int xf, int yf){
        if (pieceAt(xi, yi) != null){
        if (firefox){
            if (pieceAt(xi, yi).isKing()){
                if (pieceAt(xi - 1, yi - 1) == null || pieceAt(xi + 1, yi - 1)  == null){
                    if(((xi - 1) == xf && (yi - 1) == yf) || ((xi + 1) == xf && (yi - 1) ==yf))
                        return true;
                }
                if (pieceAt(xi - 1, yi - 1) != null){
                    if (!pieceAt(xi - 1, yi - 1).isFire()){
                        if(xf == xi - 2 && yf == yi - 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
                if (pieceAt(xi + 1, yi - 1) != null){
                    if (!pieceAt(xi + 1, yi - 1).isFire()){
                        if(xf == xi + 2 && yf == yi - 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
            }
            if (pieceAt(xi, yi).isFire()){
                if (pieceAt(xi + 1, yi + 1) == null || pieceAt(xi - 1, yi + 1)  == null){
                    if(((xi + 1) == xf && (yi + 1) == yf) || ((xi - 1) == xf && (yi + 1) ==yf)){
                        return true;
                    }
                }
                if (pieceAt(xi + 1, yi + 1) != null){
                    if (!pieceAt(xi + 1, yi + 1).isFire()){
                        if(xf == xi + 2 && yf == yi + 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
                if (pieceAt(xi - 1, yi + 1) != null){
                    if (!pieceAt(xi - 1, yi + 1).isFire()){
                        if(xf == xi - 2 && yf == yi + 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
            }

        } 
        else{
            if (pieceAt(xi, yi).isKing()){
                if (pieceAt(xi + 1, yi + 1) == null || pieceAt(xi - 1, yi + 1)  == null){
                    if(((xi + 1) == xf && (yi + 1) == yf) || ((xi - 1) == xf && (yi + 1) ==yf)){
                        return true;
                    }
                }
                if (pieceAt(xi + 1, yi + 1) != null){
                    if (pieceAt(xi + 1, yi + 1).isFire()){
                        if(xf == xi + 2 && yf == yi + 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
                if (pieceAt(xi - 1, yi + 1) != null){
                    if (pieceAt(xi - 1, yi + 1).isFire()){
                        if(xf == xi - 2 && yf == yi + 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
            }
            if (!pieceAt(xi, yi).isFire()){
                if (pieceAt(xi - 1, yi - 1) == null || pieceAt(xi + 1, yi - 1)  == null){
                    if(((xi - 1) == xf && (yi - 1) == yf) || ((xi + 1) == xf && (yi - 1) ==yf))
                        return true;
                }
                if (pieceAt(xi - 1, yi - 1) != null){
                    if (pieceAt(xi - 1, yi - 1).isFire()){
                        if(xf == xi - 2 && yf == yi - 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
                if (pieceAt(xi + 1, yi - 1) != null){
                    if (pieceAt(xi + 1, yi - 1).isFire()){
                        if(xf == xi + 2 && yf == yi - 2 && pieceAt(xf, yf) == null)
                            return true;
                    }
                }
            }
        }
    }
        return false;

    }
    public void select(int x, int y){
        // have not selected piece yet
        if (selected == null && pieceAt(x, y) != null){
                selectedx = x;
                selectedy = y;
                selected = pieceAt(x, y);
        }
        // selected piece, havent moved, use this to reselect
        if (selected != null && !moved && pieceAt(x, y) != null){
            selectedx = x;
            selectedy = y;
            selected = pieceAt(x, y);
        }

        // if you selected a piece and (x,y) is empty;
        if (selected != null && !moved && pieceAt(x, y) == null && !selected.hasCaptured()){
            selected.move(x, y);
            selectedx = x;
            selectedy = y;   
        }
        // Double jump
        if (selected != null && moved && selected.hasCaptured() && pieceAt(x, y) == null){            
            selected.move(x, y);
            selectedx = x;
            selectedy = y;
        }
        

    } 
    public void place(Piece p, int x, int y){
        pieces[x][y] = p;
    }
    public Piece remove(int x, int y){
        Piece p = pieces[x][y];
        pieces[x][y] = null;
        moved = true;
        return p;
    }

    
    public boolean canEndTurn(){
        if (moved){
            return true;
        }
        return false;
    }
    public void endTurn(){
        firefox = !firefox;
        if (selected != null)
            selected.doneCapturing();
        selected = null;
        moved = false;

    }
    public String winner(){
        int N = 8;
        int fireCount = 0;
        int waterCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece p = pieces[i][j];
                if (p != null){
                    if (p.isFire()){
                        fireCount+=1;
                    }
                    else{
                        waterCount +=1;
                    }
                }
            }
        }
        if (fireCount == 0 || waterCount == 0){
                if (fireCount + waterCount == 0){
                    return "No one";
                }
                if (fireCount == 0){
                    return "Water";
                }
                if (waterCount == 0){
                    return "Fire";
                }

        }
       return null;
    } 
}