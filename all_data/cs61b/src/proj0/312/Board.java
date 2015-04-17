public class Board{
	
    /** Location of pieces. */
    private Piece[][] mypieces = new Piece[8][8];
    private boolean selectedpiece = false;
    private boolean playersmove = false;
    private boolean playerone = true;
    private int coordOfSelectedPiecex = -1;
    private int coordOfSelectedPiecey = -1;
    public static void main(String[] args) {
        int N = 8;
        Board b = new Board(false);
        
        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                
                if (b.canSelect((int) x, (int) y) == true) {
                    b.select((int) x, (int) y);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn())
                {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(15);
        }
    }

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // if (pieces[i][j]) {
                drawingPieces();

                //     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                // }
            }
        }
    }

	public Board(boolean shouldBeEmpty){
		//initializes board//
		if(shouldBeEmpty){
			drawBoard(8);

		} else{
             drawBoard(8);
             keeptrackofpieces();
         }

	}



    private void keeptrackofpieces(){ //puts stuff on rows and what not//
        for(int x = 0; x<8; x++){
            for(int y = 0; y < 8; y++){//y is my row//
                if((y == 0) && (x % 2 == 0)){
                    mypieces[x][y] = new Piece(true, this, x,y, "pawn");
                }
                if((y == 1) && (x % 2 == 1)){//row 1//
                    mypieces[x][y] = new Piece(true, this, x, y,"shield");
                }
                if((y ==2 ) && (x % 2 == 0)){//row2//
                    mypieces[x][y] = new Piece(true,this, x, y, "bomb");
                }
                if((y == 5) && (x % 2 == 1)){//row3//
                    mypieces[x][y] = new Piece(false,this, x, y, "bomb");
                }
                if((y == 6) && (x % 2 == 0)){//row4//
                    mypieces[x][y] = new Piece(false, this, x, y, "shield");
                }
                if((y == 7) && (x % 2 == 1)){//row5//
                    mypieces[x][y] = new Piece(false, this, x,y, "pawn");
                }

            }
        }
    }


    private void drawingPieces(){  //gets pieces to show up// //my board is glitchy af//
        for(int x = 0; x<8; x++){
            for(int y = 0; y < 8; y++){
                if(mypieces[x][y] != null){
                    if(mypieces[x][y].isFire()){
                        if(mypieces[x][y].isBomb()){
                            if(mypieces[x][y].isKing()){
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);

                                }
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                                }
                            else if(mypieces[x][y].isShield()){
                                if (mypieces[x][y].isKing()){
                                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                                }
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                            }

                            else {
                                if(mypieces[x][y].isKing()){
                                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                                }
                                 StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                                }
                    }

                if(mypieces[x][y].isFire() == false){
                    if(mypieces[x][y].isBomb()){
                            if(mypieces[x][y].isKing()){
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                                 StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);

                        }
                        else if(mypieces[x][y].isShield()){
                            if (mypieces[x][y].isKing()){
                            StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                        }
                           StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                        }

                     else {
                        if(mypieces[x][y].isKing()){
                            StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                    }
                }

                }
            }
        }
    }


	public Piece pieceAt(int x, int y){
		//gets position at (x,y), or null if no piece//
        if (((x < 0 ) || (x > 7)) || ((y < 0) || (y > 7))){
            return null;
        }
        else{
        
        return mypieces[x][y];
        
        }
	}

    public void place(Piece p, int x, int y){
        if (((x >= 0 ) && (x <= 7)) && ((y >= 0) && (y <= 7))){
            mypieces[x][y] = p;

        }
        
    }


    private boolean validMove(int xi, int yi, int xf, int yf){
        int ydif = Math.abs(yf-yi);
        int xdif = Math.abs(xf-xi);
        int xadd = (xi + xf) / 2;
        int yadd = (yi + yf) / 2;
        boolean iamaking = pieceAt(xf,yf).isKing();


        if((xi > 7) || (yi > 7) || (xi < 0) || (yi < 0)){
            return false;
        }


        if(pieceAt(xi, yi) == null && pieceAt(xf,yf).isFire()){
            if ((ydif == 2) && (xdif == 2)){
                if((pieceAt(xadd, yadd) != null)){
                    if((yi == 2+ yf) || (iamaking == true)){
                        if ((pieceAt(xadd, yadd).isFire() == false)) {
                            if(playersmove == false || pieceAt(xf,yf).hasCaptured() == true){ 
                                return true;
                            }
                        }
                    }
                }
            }

            if ((ydif == 1) && (xdif == 1)) {
                if(pieceAt(xi, yi) == null){
                    if((yi == 1+ yf) || (iamaking == true)){
                        if(playersmove == false){

                    
                           return true;
                        }
                    }
                }
            }
        }

        if(pieceAt(xi, yi) == null && pieceAt(xf,yf).isFire() == false){
            if ((ydif == 2) && (xdif == 2)){
                if((pieceAt(xadd, yadd) != null)){
                    if((yi == yf - 2) || (iamaking == true)){
                        if ((pieceAt(xadd, yadd).isFire() == true)) {
                            if(playersmove == false || pieceAt(xf,yf).hasCaptured() == true){  
                               return true;
                           }
                        }
                    }
                }
            }

            if ((ydif == 1) && (xdif == 1)) {
                if(pieceAt(xi, yi) == null){
                    if((yi == yf - 1) || (iamaking == true)){
                        if(playersmove == false){

                    
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }



            

            

        



	public boolean canSelect(int x, int y){
		// returns true if square at (x,y) can be selected//

        if(x > 7 || x < 0 || y > 7 || y < 0){
            return false;
        }

		if(mypieces[x][y] != null && playersmove == false){
            if(playerone == true && pieceAt(x,y).isFire() == true){
                return true;

            }
            else if(playerone == false && pieceAt(x,y).isFire() == false){
                return true;

            }
		}

        else if(pieceAt(x,y) == null && selectedpiece == true){
            if(validMove(x,y,coordOfSelectedPiecex, coordOfSelectedPiecey)){
                return true;

            }
        }
        return false;
	}

    public void select(int x, int y){
        //selects square at (x,y)//
        if(pieceAt(x, y) == null){
            pieceAt(coordOfSelectedPiecex,coordOfSelectedPiecey).move(x,y);
            playersmove = true;
            coordOfSelectedPiecex = x;
            coordOfSelectedPiecey = y;
            if (pieceAt(coordOfSelectedPiecex, coordOfSelectedPiecey).isBomb() == true){
                if (pieceAt(coordOfSelectedPiecex, coordOfSelectedPiecey).hasCaptured() == true){
                    explode(coordOfSelectedPiecex, coordOfSelectedPiecey);
                }
            }

        }
        else{
            coordOfSelectedPiecex = x;
            coordOfSelectedPiecey = y;
            selectedpiece = true;

        }

    }

    private void explode(int x, int y){
        if (pieceAt(x + 1, y + 1) != null){
            if(pieceAt(x + 1,y + 1).isShield() == false){
                remove(x + 1, y + 1);
            }
        }
        if (pieceAt(x + 1, y - 1) != null){
            if(pieceAt(x + 1,y - 1).isShield() == false){
                remove(x + 1, y - 1);
            }
        }
        if (pieceAt(x - 1, y - 1) != null){
            if(pieceAt(x - 1,y - 1).isShield() == false){
                remove(x + 1, y + 1);
            }
        }
        if (pieceAt(x - 1, y + 1) != null){
            if(pieceAt(x - 1,y + 1).isShield() == false){
                remove(x - 1, y + 1);
            }
        }

        remove(x, y);
        selectedpiece = false;
    }

    public Piece remove(int x, int y){ //finish this//
        if((x<0)|| (x > 7)|| (y <0) || (y > 7)){ //check if outofbound//
            System.out.println("Out of bounds");
            
        }
    
        
        if(mypieces[x][y] == null){
            System.out.println("Nothing to remove here"); //when theres nothing to get rid of//
            return null;

        }
        else{

            Piece p = mypieces[x][y];
            mypieces[x][y] = null;

            return p;
        }
    }

    public boolean canEndTurn(){  
        if(playersmove == true){
            return true;
        }
        else{
            return false;
        }
    }

    

    public void endTurn(){
        if(playerone == true){
            playerone = false;
        }
        else if(playerone == false){
            playerone = true;
        }
        selectedpiece = false;
        playersmove = false;
        if (pieceAt(coordOfSelectedPiecex,coordOfSelectedPiecey) != null){
            pieceAt(coordOfSelectedPiecex,coordOfSelectedPiecey).doneCapturing();
        }

    }

     public String winner(){

        int waterCount = 0;
        int fireCount = 0;
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                if(pieceAt(x,y) != null){
                    if(pieceAt(x,y).isFire()){
                        fireCount += 1;
                    }
                    else if (pieceAt(x,y).isFire() == false){
                        waterCount += 1;
                    }

                }
            }
        }
        if (fireCount == 0){
            if (waterCount == 0){
                return "No one";
            }
            else if (waterCount != 0){
                return "Water";
            }
        }
        else if (fireCount != 0){
            if (waterCount == 0){
                return "Fire";
            }
            else if (waterCount != 0){
                return null;
            }
        }
        return null;
    }



}