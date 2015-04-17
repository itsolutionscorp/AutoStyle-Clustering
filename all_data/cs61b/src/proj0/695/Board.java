public class Board {
	
	private Piece[][] pieces;
    private int selectedx = -1;
    private int selectedy = -1;
    private boolean hasmoved = false;
    private boolean player1 = true;
    private Piece selectedpiece = null;


    private void drawBoard(int N) {


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) 
                    {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                        if (pieces[i][j]!= null){
                            if (selectedpiece == pieces[i][j]){
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);}
                            }
                        }
                else    {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (pieces[i][j] != null){
                if ((pieces[i][j].isFire() == true)){
                    if (pieces[i][j].isShield() == true){
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);}

                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);}
                    if (pieces[i][j].isBomb() == true){
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);}
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    }
                    if ((pieces[i][j].isShield() == false) && (pieces[i][j].isBomb() == false)){
                            if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);}
                    
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);    
                    }}
                
                if (pieces[i][j].isFire() == false) {
                    if (pieces[i][j].isShield() == true){
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);}
    
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);}
                    if (pieces[i][j].isBomb() == true){
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);}
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);}
                    if ((pieces[i][j].isShield() == false) && (pieces[i][j].isBomb() == false)){
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);}
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);}

                }}}
            
        }
    }
    public static void main(String[] args) {

        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false); 

     	while(true) {
  			b.drawBoard(N);
            int xvalue = (int)StdDrawPlus.mouseX();
            int yvalue = (int)StdDrawPlus.mouseY();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect(xvalue, yvalue)){
                    b.select(xvalue, yvalue);
                }
            }
            if ((StdDrawPlus.isSpacePressed()) && (b.canEndTurn())){
                b.endTurn();
            }
            b.drawBoard(8);
            StdDrawPlus.show(100);
            if(b.winner() != null){
                break;
            } }
            


                                   

        }

    public Board(boolean shouldBeEmpty){
        pieces =  new Piece[8][8];
        if (shouldBeEmpty == false) 
    	{  for (int k = 0 ; k < 8; k++){
                for (int b = 0; b < 8; b++){
                        if ((b == 0 ) && (k%2 == 0)){
                            pieces[k][b] = new Piece(true, this, k, b, "pawn");
                        }
                        if ((b == 1 ) && (k%2 != 0)){
                            pieces[k][b] = new Piece(true, this, k, b, "shield");
                        }
                        if ((b == 2 ) && (k%2 == 0)){
                            pieces[k][b] = new Piece(true, this, k, b, "bomb");
                        }
                        if ((b == 5 ) && (k%2 != 0)){
                            pieces[k][b] = new Piece(false, this, k, b, "bomb");
                        }
                        if ((b == 6) && (k%2 == 0)){
                            pieces[k][b] = new Piece(false, this, k, b, "shield");
                        }
                        if ((b == 7 ) && (k%2 != 0)){
                            pieces[k][b] = new Piece(false, this, k, b, "pawn");
                        }
                    }
                }
                drawBoard(8);}}  
    public Piece pieceAt(int x, int y){
    	if ((x > 8) || (y > 8) || (x < 0) || (y < 0)){
    		return null;
    	}
        else {
                if (pieces[x][y] != null){
                    return pieces[x][y];
                }
                else {return null;}
                }
            }
    
      public void place(Piece p, int x, int y){
        if ((p == null) || (x >= 8) || (y>=8) || (y < 0) || (x < 0 )){
            ;
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (pieces[i][j] == p){
                        pieces[i][j] = null;
                    }}}
                pieces[x][y] = p;

        }
      }

      public Piece remove(int x, int y){
        if ((x >= 8) || (y >=8) || (y < 0) || (x < 0)){
            System.out.println("The co-ordinates you have specified are out of bounds.");
            return null;
        }
        else if (pieces[x][y] == null){
           System.out.println("The piece is null so can't be placed");
           return null; 
        }
        else{
            Piece a = pieces[x][y];
            pieces[x][y] = null;
            return a;
        }
      }
      // Ideas were given by Nikita Rau (cs61b - api) so code might share similarities //
      private boolean validMove(int xi, int yi, int xf, int yf){
        if ((xf < 8) && (yf < 8) && (yf >= 0) && (xf >= 0)){
            if (pieces[xi][yi].isFire() == true){
                if (pieces[xi][yi].isKing() == true){
                    if (((Math.abs(xi - xf) == 1) || ((Math.abs(yf - yi)) == 1))){
                        return true;}
                    else if (pieces[xf][yf] == null){
                        return true;
                    }
                    else if (((Math.abs(xi - xf) == 2) || ((Math.abs(yf - yi)) == 2))){
                        if((pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi + 1].isFire() == false)){
                            return true;
                        }
                        if((pieces[xi - 1][yi + 1] != null) && (pieces[xi - 1][yi + 1].isFire() == false)){
                            return true;
                        }
                        if((pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi - 1].isFire() == false)){
                            return true;
                        }
                        if((pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi - 1].isFire() == false)){
                            return true;
                        }
                    }
                    else {return false;}
                    }

                
            else if ((Math.abs(xi - xf) == 1) && ((yf - yi) == 1)){
                    if (pieces[xf][yf] == null){
                        return true;}
                    else{ return false; }

                }
            else if ((Math.abs(xi - xf) == 2) && ((yf - yi) == 2)){
                        if((pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi + 1].isFire() == false)){
                            return true;}
                        if((pieces[xi + 1][yi + 1] != null) && (pieces[xi - 1][yi + 1].isFire() == false)){
                            return true;}

            }
            else { return false;}}
      
      if (pieces[xi][yi].isFire() == false){
            if (pieces[xi][yi].isKing() == true){
                    if (((Math.abs(xi - xf) == 1) || ((Math.abs(yf - yi)) == 1))){
                        return true;}
                    else if (pieces[xf][yf] == null){
                        return true;
                    }
                    else if (((Math.abs(xi - xf) == 2) || ((Math.abs(yf - yi)) == 2))){
                        if((pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi + 1].isFire() == true) ){
                            return true;
                        }
                        if((pieces[xi - 1][yi + 1] != null) && (pieces[xi - 1][yi + 1].isFire() == true)){
                            return true;
                        }
                        if((pieces[xi + 1][yi - 1] != null) && (pieces[xi + 1][yi - 1].isFire() == true)){
                            return true;
                        }
                        if((pieces[xi - 1][yi - 1] != null) && (pieces[xi - 1][yi - 1].isFire() == true) ){
                            return true;
                        }
                    }
                    else {return false;}
                    }

            else if ((Math.abs(xi - xf) == 1) && ((yf - yi) == -1)){
                    if (pieces[xf][yf] == null){
                        return true;}
                    else{ return false; }}
            else if ((Math.abs(xi - xf) == 2) && ((yf - yi) == 2)){
                        if((pieces[xi + 1][yi + 1] != null) && (pieces[xi + 1][yi + 1].isFire() == true) ){
                            return true;}
                        if((pieces[xi + 1][yi + 1] != null) && (pieces[xi - 1][yi + 1].isFire() == true)){
                            return true;}

            }
            else { return false;}

        
      }}
      return false;}
public boolean canSelect(int x, int y){
    if ((x >= 8) || (y >= 8) || (x< 0) || (y <0)){
        return false;
    } 
    if (player1){
        if (pieces[x][y] != null){
            if (!(pieces[x][y].isFire())){
                return false;
            }
            if ((pieces[x][y].isFire()) && (selectedpiece ==  null)){
                return true;
            }

            if (hasmoved == false){
                return true;
            }
            else {return false;}
        }
        else{
            if (selectedpiece == null){
                return false;
            }
            if ((selectedx == -1) && (selectedy == -1) ){
                return true;
            }
            if ((hasmoved == false) && (validMove(selectedx, selectedy, x,y))){
                return true;
            }
            if (selectedpiece != null){
            if ((selectedpiece.hasCaptured()) && validMove(selectedx, selectedy, x, y) ){
                return true;}}
            return false;
        }
    }
    if (player1 == false){
        if (pieces[x][y] != null){
            if (pieces[x][y].isFire()){
                return false;}
            if (selectedpiece == null){
                return true;
            }

            if (hasmoved == false){
                return true;
            }
            else {return false;}
        }
        else{
            if (selectedpiece == null){
                return false;
            }
            if ((selectedx == -1) && (selectedy == -1)){
                return true;
            }
            if ((hasmoved == false) && (validMove(selectedx, selectedy, x,y))){
                return true;
            }
            if (selectedpiece != null){
            if ((selectedpiece.hasCaptured()) && validMove(selectedx, selectedy, x, y) ){
                return true;}}
            return false;
        }
    }
    return false;
}
  public void select(int x, int y){
    selectedy = y;
    selectedx = x;
    if ((pieces[x][y] == null) && (selectedpiece != null)){
        selectedpiece.move(selectedx, selectedy);
        hasmoved = true;
    }

    else{

        selectedpiece = pieces[x][y];
    }

  }
  public boolean canEndTurn(){
    if (selectedx != -1 && selectedy != -1){
    if ((hasmoved) || (pieces[selectedx][selectedy].hasCaptured()) ){
        return true;
    }}
        return false;
  }
  public void endTurn(){
    if (canEndTurn()){
    hasmoved = false;
    player1 = !player1;
    selectedpiece.doneCapturing();
    selectedpiece = null;
    selectedy = -1;
    selectedx = -1;}
    
  }
  public String winner(){
    int firepieces = 0;
    int waterpieces = 0;
    for (int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++){
            if (pieces[i][j] != null){
                if(pieces[i][j].isFire()){
                    firepieces = firepieces + 1;
                }
                else {
                    waterpieces = waterpieces +1;
                }
            }
        }
    }
    if ((firepieces > 0) && (waterpieces == 0)){
        return "Fire";
    }
    else if ((waterpieces > 0) && (firepieces == 0)){
        return "Water";
    }
    else if ((waterpieces == 0) && (firepieces == 0)){
        return "No one";
    }
    else {
        return null;
    }
  }
}





