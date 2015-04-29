public class Board{
    private Piece[][] grid;
    private int selx = -1, sely = -1;
    private boolean selectedPiece = false, movedPiece = false;//, capturedPiece = false;
    private boolean fireTurn = true;

    public Board(boolean shouldBeEmpty){
        grid = new Piece[8][8];
        if(!shouldBeEmpty){
            String[] types = {"Pawn", "Shield", "Bomb"};
            for(int y = 0; y < 3; y++)
            for(int x = y % 2; x < 8; x+=2)
                place(new Piece(true, this, x, y, types[y]), x, y);

            for(int y = 0; y < 3; y++)
            for(int x = y % 2; x < 8; x+=2)
                place(new Piece(false, this, 7-x, 7-y, types[y]), 7-x, 7-y);
        }
    }

    public Piece pieceAt(int x, int y){
        if(x >= 0 && x < 8 && y >= 0 && y < 8)
            return grid[x][y];
        return null;
    }

    public boolean canSelect(int x, int y){ 
        if(x < 0 || x >= 8 || y < 0 || y >= 8) return false;
        Piece sel = grid[x][y];
        if(sel != null){
            if(sel.isFire() != fireTurn) return false;
            if(!selectedPiece) return true;
            if(selectedPiece && !movedPiece) return true;
        }
        else{
            if(selectedPiece && !movedPiece){
                return validMove(selx, sely, x, y, true);
            }
            if(selectedPiece && grid[selx][sely] != null && grid[selx][sely].hasCaptured()){
                return validMove(selx, sely, x, y, false);
            }
        }
        return false;
    }
    
    private boolean killable(Piece p, boolean mycolor){
        return p != null && p.isFire() == !mycolor;
    }
    private boolean validMove(int xi, int yi, int xf, int yf, boolean allmoves){
        if(Math.abs(xi - xf) > 2 || Math.abs(yi - yf) > 2) return false;
        if(grid[xf][yf] != null) return false;
        Piece p = grid[xi][yi];
        if(p == null){
            return false;
        }
        boolean king = p.isKing();
        boolean isFire = p.isFire();
        int dx = xf-xi, dy = yf-yi;
        if(king){
            if(Math.abs(dx) == 1 && Math.abs(dy) == 1 && allmoves) return true;
            if(Math.abs(dx) == 2 && Math.abs(dy) == 2) return killable(grid[xi+dx/2][yi+dy/2], isFire);
            return false;
        }

        if(isFire){
            if(Math.abs(dx) == 1 && dy == 1 && allmoves) return true;
            if(Math.abs(dx) == 2 && dy == 2) return killable(grid[xi+dx/2][yi+dy/2], isFire);
            return false;
        }
        
        if(!isFire){
            if(Math.abs(dx) == 1 && dy == -1 && allmoves) return true;
            if(Math.abs(dx) == 2 && dy == -2) return killable(grid[xi+dx/2][yi+dy/2], isFire);
            return false;
        }
        return false;
    }

    public void select(int x, int y){
        //if(!canSelect(x,y)) return;
        if(pieceAt(x,y) != null){
            selx = x;
            sely = y;
            selectedPiece = true;
        }
        else {
            if(selectedPiece){
                grid[selx][sely].move(x,y);
                movedPiece = true;
                selx = x;
                sely = y;
                /*if(y - sely == 2){ 
                    //capturedPiece = true;
                    selx = x;
                    sely = y;
                }
                else {
                    //selectedPiece = false; //TODO::::MAKE SURE THIS CHANGE IS GOOD!!!!!!!!!!!!
                    selx = x;
                    sely = y;
                }*/
            }
        }

    }

    public void place(Piece p, int x, int y){
        if(x >= 0 && x < 8 && y >= 0 && y < 8){
            grid[x][y] = p;
        }
    }

    public Piece remove(int x, int y){
        if(x < 0 || x >= 8 || y < 0 || y >= 8){
            System.err.println("remove out of bounds: (x,y) = (" + x + "," + y + ")");
            return null;
        }
        if(grid[x][y] == null){
            System.err.println("unable to remove null: (x,y) = (" + x + "," + y + ")");
            return null;
        }
        Piece t = grid[x][y];
        grid[x][y] = null;
        return t;
    }

    public boolean canEndTurn(){
        return movedPiece || (selx != -1 && sely != -1 && grid[selx][sely] != null && grid[selx][sely].hasCaptured());
    }

    public void endTurn(){
        selectedPiece = false;
        movedPiece = false;
        if(selx != -1 && sely != -1 && grid[selx][sely]!=null) grid[selx][sely].doneCapturing();
        fireTurn = !fireTurn;
        selx = -1;
        sely = -1;
    }

    public String winner(){
        int firecount = 0, watercount = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(grid[i][j] != null && grid[i][j].isFire()){
                    firecount++;
                }
                else if(grid[i][j] != null){
                    watercount++;
                }
            }
        }
    
       if(firecount == 0 && watercount == 0) return "No one";
       if(firecount == 0) return "Water";
       if(watercount == 0) return "Fire";
       return null;
    }

    private void drawBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (selx == i && sely == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if((i+j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else               StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(grid[i][j] != null){
                    Piece p = grid[i][j];
                    String path = "img/";
                    if(p.isBomb()) path += "bomb";
                    else if(p.isShield()) path += "shield";
                    else path += "pawn";
                    path += "-";
                    if(p.isFire()) path += "fire";
                    else path += "water";
                    if(p.isKing()) path += "-crowned";
                    path += ".png";
                    StdDrawPlus.picture(i + 0.5, j + 0.5, path, 1, 1);
                }
            }
        }
    }

    public static void main(String[] args){
        Board game = new Board(false);
        StdDrawPlus.setXscale(0,8);
        StdDrawPlus.setYscale(0,8);
        
        while(true){
            game.drawBoard();
            int[] m = clickGrab(game);
            if(game.canSelect(m[0],m[1]))
                game.select(m[0],m[1]);
        }
    }


    private static int[] clickGrab(Board game){
        while(!StdDrawPlus.mousePressed()){
            StdDrawPlus.show(20);
            if(StdDrawPlus.isSpacePressed() && game.canEndTurn())
                game.endTurn();
            game.drawBoard();
        }
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();
        return new int[] {(int)x, (int)y};
    }
}
