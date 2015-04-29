/*
 *   CS61B Proj0
 *   @Author Wei You
 *  Login: cs61b-azh
 *
 *
*/
public class Board{
    /**from drawdemo*/
//    private static boolean[][] pieces;
    private final int N = 8;
    /**class definition */
    private boolean isEmpty;
    private Piece[][] pieces = new Piece[N][N];
    private boolean moved = false;  /** need to change back to private!*/
    private int[] selected = {-1, -1}; /** need to change back to private!*/
    private int fireCount = 0;  /** need to change back to private!*/
    private int waterCount = 0;  /** need to change back to private!*/
    private int player = 0;  /**0 is fire player, 1 is water player.*/
    private boolean cancapture = false;  /** need to change back to private!*/



    public Board(boolean shouldBeEmpty){
        isEmpty = shouldBeEmpty; 
        moved = false;
        if (!shouldBeEmpty){
		waterCount = 12;
		fireCount = 12;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    switch(j){
                        case 0: if(i % 2 == 0){
                                    pieces[i][j] = new Piece(true, this, i, j, "pawn");}
                                break;
                        case 1: if(i % 2 == 1){
                                    pieces[i][j] = new Piece(true, this, i, j, "shield");}
                                break;
                        case 2: if(i % 2 == 0){
                                    pieces[i][j] = new Piece(true, this, i, j, "bomb");}
                                break;
                        case 5: if(i % 2 == 1){
                                    pieces[i][j] = new Piece(false, this, i, j, "bomb");}
                                break;
                        case 6: if(i % 2 == 0){
                                    pieces[i][j] = new Piece(false, this, i, j, "shield");}
                                break;
                        case 7: if(i % 2 == 1){
                                    pieces[i][j] = new Piece(false, this, i, j, "pawn");}
                                break;
                        default: break;
                    }
                }
            }
        }

    }

    public Piece pieceAt(int x, int y){
        if(x > 7 || x < 0 || y > 7 || y < 0){
            return null;
        }
        return pieces[x][y];
    }

    public void place(Piece p, int x, int y){
        if(0<= x && x< N && 0 <= y && y< N && p!= null){
            p.move(x, y);
            pieces[x][y] = p;

            if(p.side() == 0){
                fireCount++;
            }
            
            if(p.side() == 1){
                waterCount++;
            }    
        }

    }

    public Piece remove(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N){
            System.out.println("(" + x + ", " + y + ") is out of bound!" );
            return null;
        }
        if (pieceAt(x, y) == null){
            System.out.println("There is not such a piece at (" + x + ", " + y + ")");
            return null;
        }


        String type;
        Piece temp = pieceAt(x, y);
        if (temp.isShield()){
            type = "shield";
        }
        else if (temp.isBomb()){
            type = "bomb";
        }
        else{
            type = "pawn";
        }
        Piece p = new Piece(temp.isFire(), this, x, y, type);
         pieces[x][y] = null;
        if(p.side() == 0){
            fireCount--;
        }
        if(p.side() == 1){
            waterCount--;
        }
        return p;

    }


    private boolean validMove(int xi, int yi, int xf, int yf){
        if (pieces[xf][yf] != null){
            return false;
        }
        
        Piece p = pieceAt(xi, yi);

        if (canCapture(xi, yi, xf, yf)){
            return true;
        }

        if(p.side() == 0){
            if (yf - yi== 1 && (xf - xi == 1 || xf - xi == -1)){
                return true;
            }
        }

        if (p.side() == 1){
            if (yf - yi == -1 && (xf - xi == 1 || xf - xi == -1)){
                return true;
            }
        }
    
        if (p.isKing()){
            if(yf - yi == -1 && (xf - xi == -1 || xf - xi == 1)){
                return true;
            }
        }

        return false;
    }

    private boolean canCapture(int xi, int yi, int x, int y){ 
        int xt = xi + (x - xi) / 2;
        int yt = yi + (y - yi) / 2;
        Piece target = pieceAt(xt, yt);
        if (target == null){
            return false;
        }

        Piece p = pieceAt(xi, yi);

    	if(p.side() == 0){
            if((y - yt) > 0 && (y - yt == yt - yi) && (x - xt == 
                xt - xi) && p.side()!= target.side()){
                cancapture = true;
                return true;
            }}

    	if(p.side() == 1){
            if((y - yt) < 0 && (y - yt == yt - yi) && (x - xt == 
                xt - xi) && p.side()!= target.side()){
                cancapture = true;
                return true;
            }}


        if(p.isKing()){
            if(y - yt == yt - yi && x - xt == xt - xi && p.side()!= target.side()){
                cancapture = true;
                return true;
            }
        }
        return false;
    }

    public boolean canSelect(int x, int y){
        if(x > 7 || x < 0 || y > 7 || y < 0){
            return false;
            }


        else if (pieceAt(x, y) != null && pieceAt(x, y).side() == player){
            if (!moved){
                return true;
            }
        }

        else if(pieceAt(x, y) == null && pieceAt(selected[0], selected[1]) != null && (!moved || pieceAt(selected[0], selected[1]).hasCaptured())) {
            Piece temp = pieceAt(selected[0], selected[1]);
            if(player == 0){
                if(!temp.hasCaptured()){
                if ((x - selected[0] == 1 || x - selected[0] == -1) && y - selected[1] == 1 ){
                    return true;
                }}

                if((x - selected[0] == 2 || x - selected[0] == -2) && y - selected[1] == 2 ){
                    if(pieceAt(selected[0] + (x - selected[0]) / 2, selected[1] + (y - selected[1]) / 2) != null
                        && pieceAt(selected[0] + (x - selected[0]) / 2, selected[1] + (y - selected[1]) / 2).side() != player) {
                        return true;
                    } 
                }
            }
        
            if(player == 1){
                if(!temp.hasCaptured()){
                if ((x - selected[0] == 1 || x - selected[0] == -1) && y - selected[1] == -1 ){
                    return true;
                }}

                if((x - selected[0] == 2 || x - selected[0] == -2) && y - selected[1] == -2 ){
                    if(pieceAt(selected[0] + (x - selected[0]) / 2, selected[1] + (y - selected[1]) / 2) != null
                        && pieceAt(selected[0] + (x - selected[0]) / 2, selected[1] + (y - selected[1]) / 2).side() != player) {
                        return true;
                    } 
                }
            }

            else if (temp.isKing()){
                if ((x - selected[0] == 1 || x - selected[0] == -1) && (y - selected[1] == 1 || y - selected[1] == -1) ) {
                    return true;
                }

                if((x - selected[0] == 2 || x - selected[0] == -2) && (y - selected[1] == 2 || y - selected[1] == -2) ){
                    if(pieceAt(selected[0] + (x - selected[0]) / 2, selected[1] + (y - selected[1]) / 2) != null
                        && pieceAt(selected[0] + (x - selected[0]) / 2, selected[1] + (y - selected[1]) / 2).side() != player) {
                        return true;
                    } 
                }
            }
        }
        return false;
        }




    public void select(int x, int y){
        if(pieceAt(selected[0], selected[1]) != null){
            if(pieceAt(x, y) == null){
                pieces[x][y] = pieceAt(selected[0], selected[1]);
                if(pieceAt(x, y).side() == 0){
                fireCount++;
                }
            
                if(pieceAt(x, y).side() == 1){
                waterCount++;
                } 
                pieceAt(selected[0], selected[1]).move(x, y);
                moved = true;
            }
        }

        selected[0] = x;
        selected[1] = y;
        

    }

    public boolean canEndTurn(){
        if (moved){
            return true;
        }
        return false;

    }

    public void endTurn(){


        if (canEndTurn()){
            if(player == 0){
                player = 1;
            }
            else{
                player = 0;
            }
        

        moved = false;
        selected[0] = -1;
        cancapture = false;
    }


    }


    public String winner(){
        if(fireCount == 0 && waterCount != 0){
            return "Water";
        }

        else if( waterCount == 0 && fireCount != 0){
            return "Fire";
        }

        else if(waterCount == 0 && fireCount == 0){
            return "No one";
        }

        else{
            return null;
        }

    }

    private void draw(){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //    Piece p = pieces[i][j];
                //    if(pieceAt(selected[0], selected[1]) != null){
                        StdDrawPlus.filledSquare(selected[0] + .5, selected[1] + .5, .5);
                //    }

            Piece p = pieces[i][j];
            String type;
            if(p != null){
                if (p.isBomb()){
                    type = "bomb";
                }
                else if(p.isShield()){
                    type = "shield";
                }
                else{
                    type = "pawn";
                } 
                
            if(p.isFire()){
    			if(!p.isKing()){
                    StdDrawPlus.picture(2*(i/2) + (j%2) + 0.5, j + .5, "img/" + type + 
                        "-fire.png", 1, 1);
    			}
                    
			    if(p.isKing()){
			     StdDrawPlus.picture(2*(i/2) + (j%2) + 0.5, j + .5, "img/" + type + 
			     "-fire-" + "crowned.png", 1, 1);
			     }
            }

            else if(!p.isFire()){
        		if(!p.isKing()){
                    StdDrawPlus.picture(2*(i/2) + (j%2) + 0.5, j + .5, "img/" + type + 
                        "-water.png", 1, 1);}
        		
        		if(p.isKing()){
        		StdDrawPlus.picture(2*(i/2) + (j%2) + 0.5, j + .5, "img/" + type + 
                    "-water-" + "crowned.png", 1, 1);
        		}
            }}
        }
        }       
    }

/**Copy from StdDrawPlus.java to implement the GUI. */
    public static void main(String[] args){
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
    	Board b = new Board(false);
        double m;
        double n;

        while(true){
            if (StdDrawPlus.mousePressed()) {
                m = StdDrawPlus.mouseX();
                n = StdDrawPlus.mouseY();
                if(b.canSelect((int)m, (int)n)){
                    b.select((int)m, (int)n);}
            }

            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                    b.winner();}
            }

            b.draw();
            StdDrawPlus.show(50);
        }
    }
}



