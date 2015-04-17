public class Board {
    int N = 8;
    String statement;
    private boolean hasSelected = false;
    private int a;
    private int b;
    private Piece chosenPiece;
    private Piece selectedSquare = null;

    boolean fireTurn;
    private boolean validMove = true;
    private boolean validCapture = true;
    private boolean hasMoved = false;
    

 



	 private static boolean [][] pieces;
     private static Piece[][] myPieces;

     public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty == true){
            myPieces = new Piece[N][N];
            for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                myPieces[i][j] = null; 
            

                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE); //Draws a Blank Board with no pieces

            }
        }
    }
            
        else {
            myPieces = new Piece [N][N];
            this.drawBoard(N);

        }
     }
     //Initializes the Board Object. 
     // If Board is true, initializes an empty Board.
     // If Board is false initializes a Board with the default configuration.
 


	 private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                myPieces[i][j] = null;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
        
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                for (int x = 0; x < N; x = x + 2) {
                StdDrawPlus.picture(x + .5, .5, "img/pawn-fire.png", 1, 1); //Places the basic fire pawn pieces in row 0
                myPieces[x][0] = new Piece(true, this, x, 0, "pawn");
                }

                for (int x = 1; x < N; x = x + 2){
                StdDrawPlus.picture(x + .5, 1.5, "img/shield-fire.png", 1, 1);
                myPieces[x][1] = new Piece(true, this, x, 0, "shield");
                }                 //Places the fire Shield pieces in row 1

                for (int x = 0; x < N; x = x + 2){
                StdDrawPlus.picture(x + .5, 2.5, "img/bomb-fire.png", 1, 1); // Places the fire Bomb pieces in row 2
                myPieces[x][2] = new Piece(true, this, x, 0, "bomb");
                }

                for (int x = 1; x < N; x = x + 2){
                StdDrawPlus.picture(x + .5, 7.5, "img/pawn-water.png", 1, 1); //Places the basic water pawn pieces in row 0
                myPieces[x][7] = new Piece(false, this, x, 7, "pawn");
                }


                for (int x = 0; x < N; x = x + 2){
                StdDrawPlus.picture(x + .5, 6.5, "img/shield-water.png", 1, 1); //Places the water Shield pieces in row 1
                myPieces[x][6] = new Piece(false, this, x, 6, "shield");
                }

                for (int x = 1; x < N; x = x + 2){
                StdDrawPlus.picture(x + .5, 5.5, "img/bomb-water.png", 1, 1);
                myPieces[x][5] = new Piece(false, this, x, 5, "bomb-water"); // Places the water Bomb pieces in row 2
            }
            }}}
            //Draws the Board wit default confiuguration


        public Piece pieceAt(int x, int y){
        if(x > 7 | x < 0){
            return null;
        }
        if(y > 7 | y < 0){
            return null;
        }
        else{
            return myPieces[x][y];
            
        }
    }
    //checks if there is a piece at the specified location

private boolean validCapture(int xi, int yi, int xf, int yf){
        Piece thisPiece = myPieces[xi][yi];
        if(fireTurn && thisPiece.isFire){
            if(thisPiece.isaKing){
                 for (int i = xi -1; i < xi + 1; i++) {
                    for (int j = yi -1; j < yi + 1; j++) {
                        if (myPieces[i][j].isFire == false){
                            validCapture = true;
                            return true;
                        }
                        validCapture = false;
                        return false;
                    }
                    validCapture = false;
                    return false;
                }
            }
            else if(thisPiece.isaKing == false){
                for (int i = xi; i < xi + 1; i++) {
                    for (int j = yi; j < yi + 1; j++) {
                        if (myPieces[i][j].isFire == false){
                            validCapture = true;
                            return true;
                        }
                        validCapture = false;
                        return false;
                    }
                    validCapture = false;
                    return false;
                }
            }
               
        }
        if((fireTurn == false) && (thisPiece.isFire == false)){
            if(thisPiece.isaKing){
                 for (int i = xi -1; i < xi + 1; i++) {
                    for (int j = yi -1; j < yi + 1; j++) {
                        if (myPieces[i][j].isFire == true){
                            validCapture = true;
                            return true;
                        }
                        validCapture = false;
                        return false;
                    }
                    validCapture = false;
                    return false;
                }
            }
            else if(thisPiece.isaKing == false){
                for (int i = xi; i < xi - 1; i++) {
                    for (int j = yi; j < yi - 1; j++) {
                        if (myPieces[i][j].isFire == true){
                            validCapture = true;
                            return true;
                        }
                        validCapture = false;
                        return false;
                    }
                    validCapture = false;
                    return false;
                }
                validCapture = false;
                return false;
            }
            validCapture = false;
            return false; 
        }
        validCapture = false;
        return false;
}

    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece selectedPiece = myPieces[xi][yi];
        if(myPieces[xf][yf] != null){ // if there is a piece on the spot you are trying to move to then you can't go there
        validMove = false;
        return false;
    }

    if(myPieces[xi][yi].type == "Bomb" && hasMoved){
        return false;
    }

    if(myPieces[xf][yf] == null){
        if(myPieces[xf][yf].isFire && fireTurn){
            if(selectedPiece.isKing() != true){
                    if(((Math.abs(xf-xi)) == 1) && (yf - yi) == 1){ //the distance you are moving has to be a diagonal movement so deltx = delty, furthermore you can't more more than 1
                    validMove = true;
                    return true;
                }
                validMove = false;
                return false;
            }
            else if(selectedPiece.isKing()){
                if (((Math.abs(xf-xi)) == 1) && (Math.abs(yf - yi) == 1)){
                    validMove = true;
                    return true;
                }
            }
        }

        else if((myPieces[xf][yf].isFire == false) && (fireTurn == false)){
            if(selectedPiece.isKing() != true){
                if(((Math.abs(xf-xi)) == 1) && (yi - yf) == 1){ //the distance you are moving has to be a diagonal movement so deltx = delty, furthermore you can't more more than 1
                    validMove = true;
                    return true;
                }
                validMove = false;
                return false;
            }
            else if(selectedPiece.isKing()){
                if (((Math.abs(xf-xi)) == 1) && (Math.abs(yf - yi) == 1)){
                    validMove = true;
                    return true;
                }
            }

            }
    }
    validMove = false;
    return false;

    }


      
    public boolean canSelect(int x, int y){
        if (myPieces[x][y] != null){ //if selecting a piece
            if(((fireTurn) && (myPieces[x][y].isFire)) || ((fireTurn != true) && (myPieces[x][y].isFire == false))){ //and if that piece is of the corresponding type
                if((hasSelected == false) || ((hasSelected) && (hasMoved == false))){ //and if nothing has yet been selected. Or if it has been selected and nothing has moved
                    return true;
                }
            return false;
            }
        return false;
        }
        if (myPieces[x][y] == null){
            if((hasMoved == false) && (hasSelected == true) && (validMove)){
                return true;
            }
            if((hasSelected == true) && (hasMoved) && (myPieces[x][y].hasCaptured()) && (validCapture == true)){
                return true;
            }
            return false;
            }
            return false;
        }



        

    private void updateBoard(){
        for(int i = 9; i < N; i ++){
            for(int j = 0; j<N; j++){
                if(myPieces[i][j] != null){
                    StdDrawPlus.picture(myPieces[i][j].x+0.5, myPieces[i][j].y + 0.5, "img/"+myPieces[i][j].type+".png", 1, 1);
                }
            }
        }
    }
    
            
    
    

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean [N][N];
        myPieces = new Piece[N][N];
        Board myBoard = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
           
                
                
                    //StdDrawPlus.show(250);
                    //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    //StdDrawPlus.filledSquare(x + .5, y + .5, .5);


                
         
    while(myBoard.winner() == null){
            
            if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    Piece selectedSquare = myPieces[(int) x][(int) y];
            
        
                    if(myBoard.canSelect((int) x, (int) y)){
                        myBoard.select((int) x, (int) y);
                    }
                    if(myBoard.canEndTurn()){
                                myBoard.endTurn();
                                myBoard.updateBoard();

                            }
                    }
                
            }
                }
        
          
      





    public void place(Piece p, int x, int y){
        if (x>7 | x<0){
        }
        if (y > 7 | y < 0){
        }
        if (p == null){
        }
        else{
            hasMoved = true;
            myPieces[x][y] = p;
        }
    }

    public void select(int x, int y){
        int initialXPosition = x;
        int initialYPosition = y;
        Piece selectedSquare = myPieces[x][y];
        //StdDrawPlus.show(250);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);

        if(myPieces[x][y] != null){
        initialXPosition = selectedSquare.x;
        initialYPosition = selectedSquare.y;

       
        
        }
        else if(myPieces[x][y] == null){
            selectedSquare.move(selectedSquare.x, selectedSquare.y);
            //How do I save a square on the board if there is no piece there? What Kind of object do I make my variable.
        }

    }

    public Piece remove(int x, int y){  //How do I save the information contained in the array before I remove it?
        if (x>7 | x<0){
            System.out.println("This is out of bounds ya silly rabbit");
            return null;

        }
        if (y > 7 | y < 0){
            System.out.println("This is out of bounds ya silly rabbit");
            return null;
        }
        else{
            Piece pieceRemoved1 = myPieces[x][y];
            Boolean isItFire = pieceRemoved1.isFire;
            String desiredType = pieceRemoved1.type;
            Piece pieceRemoved = new Piece(isItFire, this, x, y, desiredType);
            myPieces[x][y] = null;
            return pieceRemoved;
        }

    }

    public void endTurn(){
        fireTurn = false;
    }


    public String winner(){
        Boolean fireList = true;
        Boolean waterList = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(myPieces[i][j] != null){
                    if(myPieces[i][j].isFire == true){
                        fireList = true;
                    }
                    else if (myPieces[i][j].isFire != true){
                        waterList = true;
                    }

                }
            }
        }
        if (waterList == false && fireList == false){
             statement = "No One";
        }
        else if (waterList == false){
            statement = "Fire";
        }
        else if (fireList == false){
            statement = "water";
        }
        return statement;
    }

    //



    public boolean canEndTurn(){
        if(selectedSquare != null){
            if ((hasMoved == true) || (selectedSquare.hasCaptured() == true)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
} 











 //end of code