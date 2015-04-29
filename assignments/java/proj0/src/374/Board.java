public class Board {

	private Piece[][] pieces;

	private boolean empty;
    private Piece selected;
    private boolean moved;
    private boolean redTurn = true;
    

    private int numFire;
    private int numWater;

    //new TO DO
    private int xselect;
    private int yselect;
    //private double xclick;
    //private double yclick;

	public Board(boolean shouldBeEmpty){
        empty = shouldBeEmpty;
        pieces = new Piece[8][8];
        if (empty == false){
            setUp();
        }
	}
	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board checkboard = new Board(false);
        
        while(true) {
			checkboard.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(x > -1 && x < 8 && y > -1 && y < 8){
                    if(checkboard.canSelect((int) x, (int) y)){
                        checkboard.select((int) x,(int) y);
                    }
                }
                
            }
            if(StdDrawPlus.isSpacePressed()){
                if(checkboard.canEndTurn()){
                    checkboard.endTurn();
                }
            }
            StdDrawPlus.show(100);
            String outcome = checkboard.winner();
            if(outcome != null){
                System.out.println(outcome);
                return;
            }
        }

	}
    private void setUp(){
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j == 0){
                    if((i + j) % 2 == 0){
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        numFire += 1;
                    }
                }

                if(j == 1){
                    if((i + j) % 2 == 0){
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                        numFire += 1;
                    }
                }

                if(j == 2){
                    if((i + j) % 2 == 0){
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        numFire += 1;
                    }
                }

                if(j == 5){
                    if((i + j) % 2 == 0){
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        numWater += 1;
                    }
                }
                if(j == 6){
                    if((i + j) % 2 == 0){
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                        numWater += 1;
                    }
                }
                if(j == 7){
                    if((i + j) % 2 == 0){
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        numWater += 1;
                    }
                }

            }
        }
    }
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    if(pieces[i][j].isFire()){
                        if(pieces[i][j].isShield()){
                            if(pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        }
                        else if(pieces[i][j].isBomb()){
                            if(pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else{
                        if(pieces[i][j].isShield()){
                            if(pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else if(pieces[i][j].isBomb()){
                            if(pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
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

	
	public Piece pieceAt(int x, int y){
        if(x > -1 && x < 8 && y > -1 && y < 8 && pieces[x][y] != null){
            return pieces[x][y];
        }
        return null;
        

	}
	public boolean canSelect(int x, int y){
        //REDTURN
        if(redTurn == true){
            if(pieces[x][y] != null && pieces[x][y].isFire()){
                if(selected == null){
                    return true;
                }
                else if(moved == false){
                    return true;
                }
                return false;
            }
            else{
                if(selected != null && moved == false && pieces[x][y] == null){


                    if(xselect + 1 == x && yselect + 1 == y){
                        
                        return true;
                    }
                    else if(xselect - 1 == x && yselect + 1 == y){
                       
                        return true;
                    }
                    else if(selected.isKing() && xselect + 1 == x && yselect - 1 == y){
                       
                        return true;
                    }
                    else if(selected.isKing() && xselect - 1 == x && yselect - 1 == y){
                       
                        return true;
                    }
                    //JUMP CHECKERS TO DO
                    else if((x+1) < 8 && (y-1) > -1 && xselect - 2 == x && yselect + 2 == y && pieces[x+1][y-1] != null){
                        
                        return true;
                    }
                    else if((x-1) > -1 && (y-1) > -1 && xselect + 2 == x && yselect + 2 == y && pieces[x-1][y-1] != null){
                        
                        return true;
                    }
                    else if(selected.isKing() && (x+1) < 8 && (y+1) < 8 && xselect - 2 == x && yselect - 2 == y && pieces[x+1][y+1] != null){
                        return true;
                    }
                    else if(selected.isKing() && (x-1) > -1 && (y+1) < 8 && xselect + 2 == x && yselect - 2 == y && pieces[x-1][y+1] != null){
                        return true;
                    }

                }
                else if(selected != null && selected.hasCaptured() && pieces[x][y] == null){
                    
                    //jump checkers
                    if((x+1) < 8 && (y-1) > -1 && xselect - 2 == x && yselect + 2 == y && pieces[x+1][y-1] != null){
                        
                        return true;
                    }
                    else if((x-1) > -1 && (y-1) > -1 && xselect + 2 == x && yselect + 2 == y && pieces[x-1][y-1] != null){
                      
                        return true;
                    }
                    else if(selected.isKing() && (x+1) < 8 && (y+1) < 8 && xselect - 2 == x && yselect - 2 == y && pieces[x+1][y+1] != null){
                        return true;
                    }
                    else if(selected.isKing() && (x-1) > -1 && (y+1) < 8 && xselect + 2 == x && yselect - 2 == y && pieces[x-1][y+1] != null){
                        return true;
                    }
                }
                return false;
            }
        }
        //WATER TURN
        else{
            if(pieces[x][y] != null && pieces[x][y].isFire() == false){
                if(selected == null){
                    return true;
                }
                else if(moved == false){
                    return true;
                }
                return false;
            }
            else{
                if(selected != null && moved == false && pieces[x][y] == null){
                    

                    if(xselect + 1 == x && yselect - 1 == y){
                        
                        return true;
                    }
                    else if(xselect - 1 == x && yselect - 1 == y){
                        
                        return true;
                    }
                    else if(selected.isKing() && xselect + 1 == x && yselect + 1 == y){
                        
                        return true;
                    }
                    else if(selected.isKing() && xselect - 1 == x && yselect + 1 == y){
                        
                        return true;
                    }
                    else if((x+1) < 8 && (y+1) < 8 && xselect - 2 == x && yselect - 2 == y && pieces[x+1][y+1] != null){
                        
                        return true;
                    }
                    else if((x-1) > -1 && (y+1) < 8 && xselect + 2 == x && yselect - 2 == y && pieces[x-1][y+1] != null){
                        
                        return true;
                    }    
                    else if(selected.isKing() && (x+1) < 8 && (y-1) > -1 && xselect - 2 == x && yselect + 2 == y && pieces[x+1][y-1] != null){
                        
                        return true;
                    }
                    else if(selected.isKing() && (x-1) > -1 && (y-1) > -1 && xselect + 2 == x && yselect + 2 == y && pieces[x-1][y-1] != null){
                        
                        return true;
                    }
                }

                else if(selected != null && selected.hasCaptured() && pieces[x][y] == null){
                    // if(xselect + 1 == x && yselect - 1 == y){
                    //     return true;
                    // }
                    // else if(xselect - 1 == x && yselect - 1 == y){
                    //     return true;
                    // }
                    if((x+1) < 8 && (y+1) < 8 && xselect - 2 == x && yselect - 2 == y && pieces[x+1][y+1] != null){
                       
                        return true;
                    }
                    else if((x-1) > -1 && (y+1) < 8 && xselect + 2 == x && yselect - 2 == y && pieces[x-1][y+1] != null){
                        
                        return true;
                    }
                    else if(selected.isKing() && (x+1) < 8 && (y-1) > -1 && xselect - 2 == x && yselect + 2 == y && pieces[x+1][y-1] != null){
                        
                        return true;
                    }
                    else if(selected.isKing() && (x-1) > -1 && (y-1) > -1 && xselect + 2 == x && yselect + 2 == y && pieces[x-1][y-1] != null){
                        
                        return true;
                    }
                }
                return false;
            }
        }

	}

	public void select(int x, int y){
        if(pieces[x][y] == null){
            if(selected != null){
                selected.move(x,y);
                xselect = x;
                yselect = y;
                moved = true;
          
            }

        }
        else{
            selected = pieces[x][y];
            xselect = x;
            yselect = y;
            
            //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            //StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        }

	}

    public void place(Piece p, int x, int y){
        if(!(p == null || x > 8 || y > 8)){
            pieces[x][y] = p;
            if(pieces[x][y].isFire()){
                numFire += 1;
            }
            else{
                numWater += 1;
            }
        }
    }
    public Piece remove(int x, int y){
        if(!(x > 8 || y > 8)){
            if(pieces[x][y] != null){
                Piece temp = pieces[x][y];
                if(pieces[x][y].isFire()){
                    numFire -= 1;
                }  
                else{
                    numWater -= 1;
                }
                pieces[x][y] = null;
                return temp;
            }
            System.out.println("No piece");
            return null;
        }
        System.out.println("Out of bounds");
        return null;
    }
    public boolean canEndTurn(){
        if(moved == true || (selected != null && selected.hasCaptured())){

            return true;
        }
        return false;
    }

    public void endTurn(){
        
        moved = false;
        if(selected != null){
            selected.doneCapturing();
        }
       
        
        selected = null;
        redTurn = !redTurn;
        

    }
    public String winner(){
        if(numFire == 0 && numWater == 0){
            return "No one";
        }
        else if(numFire == 0){
            return "Water";
        }
        else if(numWater == 0){
            return "Fire";
        }
        else{
            return null;
        }
        
    }
	

}