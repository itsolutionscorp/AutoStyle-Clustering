public class Board {

    //PRIVATE METHODS HERE
    private Piece[][] pieces;
    private Piece p;
    private Piece selectedPiece = null;
    private String selectedSpace = null;
    private Piece tempPiece = null;
    private int[] pCoordinates = new int[2]; 
    private int[] initCoord = new int[2]; 
    private boolean amSelectingPiece=true;
    private int[] finalCoord = new int[2]; 
    private boolean isValidMove;
    private int N = 8;
    private int numWater = 12;
    private int numFire = 12;
    private int numTurns=0; //starts when there's 1 turn. if odd, move fire piece. if even, move water
    private boolean hasMoved;

    public static void main(String[] args){
        Board base = new Board(false);
        int N = 8;
        while (true){
            base.drawBoard();
            //base.setPieces();
            StdDrawPlus.show(100);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (base.canSelect((int)x, (int)y)==true){
                    base.select((int)x, (int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if (base.canEndTurn()==true){
                    base.endTurn();
                }
            }
        }
                    //if (canEndTurn==true)
    }
                //base.pieceImage();
                
                //if canSelect==true
                    //call select. WHICH INCLUDES THE BELOW STUFF.
                        //if pieces[x][y]!=null
                        //else if pieces[x][y]==null
                            //isValidMove. 
                    //Move. <--------THESE SHOULD BE IN SELECT.

    public Board(boolean shouldBeEmpty){ //BOARD CONSTRUCTOR
        pieces = new Piece[N][N];
        if (shouldBeEmpty==true){
        }
        else{ //repeat for the board part. 
            setPieces();
        }
    }

    public Piece pieceAt(int x, int y){
        if ((x<=8)&&(x>=0)&&(y<=8)&&(y>=0)){ //if a valid x,y pos
            return pieces[x][y];
        }
        else{
            return null; //if out of bounds
        }
    }

    public boolean canSelect(int x, int y){

        //****to check if it's the right player&hasn't moved yet
        //(players are switched in endTurn)
        //numTurns will be incremented at the end of endTurn-->indicator of which player.
        /*NEED TO CHECK FOR 2 CASES.
        Case 1: If player has selected a piece and is selecting an empty spot which is a valid move
        Case 2: If player selected a piece, captured, and has selected another valid capture destination
                -->for multiple captures, only select next destinations.
        */
                //call pieceAt. 

        //CASE1************************************************************

        if (amSelectingPiece==true){
            if (pieceAt(x,y)!=null){
                selectedPiece = pieceAt(x,y);
                    if (numTurns%2==0){
                        if (pieceAt(x,y).isFire()==true){
                            amSelectingPiece=false;
                            tempPiece = selectedPiece;
                            return true;
                        }
                    }
                    else{
                        if (pieceAt(x,y).isFire()==false){
                            amSelectingPiece=false;
                            tempPiece = selectedPiece;
                            return true;
                        }
                    }
            }
        }
        else{ //this is selecting a space because amSelectingPiece==false;
            System.out.println("amSelectingPiece is now false, try to select a space");
            if (pieceAt(x,y)==null){ // if it's a space.
                if (isValidMove(initCoord[0], initCoord[1], x,y)){
                    if (selectedSpace==null){ //the space is currently null.
                        selectedSpace = "this has been selected";
                        return true;
                    }
                }
            }
            else{ //if a piece was already selected but the user clicks another piece.
                amSelectingPiece = true;
                if (pieceAt(x,y)!=null){
                selectedPiece = pieceAt(x,y);
                    if (numTurns%2==0){
                        if (pieceAt(x,y).isFire()==true){
                            amSelectingPiece=false;
                            tempPiece = selectedPiece;
                            return true;
                        }
                    }
                    else{
                        if (pieceAt(x,y).isFire()==false){
                            amSelectingPiece=false;
                            tempPiece = selectedPiece;
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

   public void select(int x, int y){ //NOT SURE WHAT TO PUT FOR X,Y VARIABLES HERE.
        //will handle calling the piece and moving it.
        //if spot is empty and you currently have piece selected. move.

        if ((tempPiece!=null)&&(selectedSpace==null)){ //someone clicks select, and if there is a piece there. but won't move until a space is also selected.
            initCoord[0] = x;
            initCoord[1] = y;
            System.out.println("Selected a piece but no space yet");
        }

        else if ((tempPiece!=null)&&(selectedSpace!=null)){ //can be a square that we own, or the square that we want to move to.
            //System.out.println("A piece and a space has been selected");
            finalCoord[0] = x;
            finalCoord[1] = y;
            System.out.println("Selected a piece and a space");
            tempPiece.move(finalCoord[0],finalCoord[1]);
                /*if (pieceAt(initCoord[0], initCoord[1]).hasCaptured()==true){ //if the piece has performed a capture.
                    if (pieceAt(initCoord[0], initCoord[1]).isFire()){ //if the initial piece is a fire piece.
                        numWater = numWater - 1;
                    }
                        numFire = numFire - 1;
                }*/
                remove(initCoord[0], initCoord[1]);
                hasMoved = true;
        }
    }


    public boolean canEndTurn(){
        //a piece must have moved or made a capture.
        if (hasMoved==true){
            return true;
        }
        return false;
    }


    public void endTurn(){
        if (canEndTurn()==true){
            System.out.println(numTurns);
            numTurns = numTurns + 1;
            selectedSpace = null;
            hasMoved = false;
            System.out.println(numTurns);
        }
    }

    public void place(Piece p, int x, int y){
        if ((x<=7)&&(x>=0)&&(y<=7)&&(y>=0)){ //if a valid x,y pos
            if (p!=null){
                pieces[x][y] = p;
            }
            else{
                pieces[x][y] = null;
            }
        }
         //if out of bounds
    }

    public Piece remove (int x, int y){
        if ((x>7)&&(x<0)&&(y>7)&&(y<0)){ //if out of bounds
            System.out.println("Input x,y is out of bounds");
            return null;
        }
        else if (pieces[x][y]==null){
            System.out.println("There is no piece here");
            return null;
        }
        Piece removed = pieces[x][y];
        pieces[x][y] = null; //will remove the piece.
        return removed;
    }

    public String winner(){
        //returns the winner of the game: fire, water, no one, or null
        //if no fire pieces, water wins
        //if no water pieces, fire wins
        //if no pieces , no one wins
        //if game is in progress, return null
        if ((numFire==0)&&(numWater!=0)){
            return "Water player wins!";
        }
        else if ((numFire!=0)&&(numWater==0)){
            return "Fire player wins!";
        }
        else if ((numFire==0)&&(numWater==0)){
            return "No one wins :(";
        }
        else{
            return null;
        }

    }

    private boolean isValidMove(int xi, int yi, int xf, int yf){ //add a comment
        int xdiff = xf - xi;
        int ydiff = yf - yi;
        int xmid = (xi+xf)/2;
        int ymid = (yi + yf)/2;
        if (pieces[xi][yi]!=null){
            if (pieces[xi][yi].isKing()==true){
                if (((xdiff==1)||(xdiff==-1))&&((ydiff==1)||(ydiff==-1))){
                    return true;
                }
            }
            else if (pieces[xi][yi].isFire()==true){
                if (((xdiff==1)&&(ydiff==1))||((xdiff==-1)&&(ydiff==1))){ //fire can only move diagonally up
                    return true;
                }
                else if (((xdiff==2)&&(ydiff==2))||((xdiff==-2)&&(ydiff==2))){
                    if (pieceAt(xmid, ymid).isFire()==false){
                        return true;
                    }
                }
            }
            else if (pieces[xi][yi].isFire()==false){
                if (((xdiff==-1)&&(ydiff==-1))||((xdiff==1)&&(ydiff==-1))){
                    return true;
                }
                else if (((xdiff==-2)&&(ydiff==-2))||((xdiff==2)&&(ydiff==-2))){
                    if (pieceAt(xmid, ymid).isFire()==true){
                        return true;
                    }
                }
            }   
        }
            return false;
    }

    private void drawBoard(){ //draws an empty board
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
                pieceImage();
    }

    private void setPieces(){ //sets the pieces
        for (int x = 0; x < N; x++){
                for (int y = 0; y < N; y++){
                    if (x%2==0){ //make pieces on odd squares (in x direction)
                        if (y==0){
                            pieces[x][y] = new Piece(true, this, x, y, "pawn");
                        }
                        else if (y==2){
                            pieces[x][y] = new Piece(true, this, x, y, "bomb");
                        }
                        else if (y==6){
                            pieces[x][y] = new Piece(false, this, x, y, "shield");
                            //pieceImage();
                        }
                    }
                    else if (x%2!=0){ //make pieces on even squares (in x direction)
                        if (y==1){
                            pieces[x][y] = new Piece(true, this, x, y, "shield");
                            //pieceImage();
                        }
                        else if (y==5){
                            pieces[x][y] = new Piece(false, this, x, y, "bomb");
                            //pieceImage();
                        }
                        else if (y==7){
                            pieces[x][y] = new Piece(false, this, x, y, "pawn");
                            //pieceImage();
                        }
                    }
                }
            }
        }


        private void pieceImage(){ //gives the pieces its image?
            for (int x = 0; x < N; x++){
                for (int y = 0; y < N; y++){
                    if (pieces[x][y]!=null){
                    //**********FOR FIRE PIECES*********
                        if (pieces[x][y].isFire() == true){ 
                            if (pieces[x][y].isBomb() == true){
                                if (pieces[x][y].isKing() == true){
                                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                                }
                            }

                            else if (pieces[x][y].isShield() == true){
                                if (pieces[x][y].isKing() == true){
                                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png",1,1);
                                }
                            }

                            else { //for PAWN cases
                                if (pieces[x][y].isKing() == true){
                                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png",1,1);
                                }
                                else {
                                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png",1,1);
                                }
                            }
                        }   

                        //**********FOR WATER PIECES**********
                        else{
                            if (pieces[x][y].isBomb() == true){
                                    if (pieces[x][y].isKing() == true){
                                        StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                                    }
                                    else{
                                        StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                                    }
                                }

                                else if (pieces[x][y].isShield() == true){
                                    if (pieces[x][y].isKing() == true){
                                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                                    }
                                    else{
                                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png",1,1);
                                    }
                                }

                                else { //for PAWN cases
                                    if (pieces[x][y].isKing() == true){
                                        StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png",1,1);
                                    }
                                    else {
                                        StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png",1,1);
                                    }
                                }
                            }
                    }    
                }
            }
        }


    }



    /*public static void Piece pieceAt(int x, int y){
        getPiece==
    }*/