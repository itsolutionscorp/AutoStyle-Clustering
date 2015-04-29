public class Board {

    private int fireScore;
    private int waterScore;
    private boolean gameOver;

    private Piece[][] pieces; /* 2D piece array. This is the non-GUI representation of the board. 
                                It contains all the piece objects currently on the board. */
    

    private int turn; // turn==0 means that it is Fire's turn. Turn==1 means that it is Water's turn
    
    private Piece selectedPiece; // null if no piece is selected. Otherwise references the currently selected piece

   
    private boolean moved;   // False if player hasn't moved this turn. True if they have.

    private int sx;   // x coordinate of the highlighted selected square
    private int sy;   // y coordinate of the highlighted selected square
    private boolean firstClick; //boolean that shows or hides the square with the coordinates in select



    private int spX; //the x coordinate of the selected piece
    private int spY; //the y coordinate of the selected piece







  
    

    /* Constructs an empty board if shouldBeEmpty is true. Constructs a board with the standard starting arrangement of pieces if true. 
    */
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (shouldBeEmpty) {
            }
            
        else {
            placePieces();
         }
        turn = 0;
        moved = false;
        selectedPiece = null;
        firstClick = false;

        gameOver = false;
        
        }
    /* Returns the Piece object at location (x,y). Returns null if (x,y) is out of bounds, or there is no piece at (x,y)
*/
    public Piece pieceAt(int x, int y) {
        if (inBounds(x,y) ) {
            if (pieces[x][y]!=null) {
                return pieces[x][y];
            }
        }
        
            return null;
        

    }
 //returns true if there is a piece at (x,y) and null otherwise
    private boolean pieceHere(int x, int y) {
        if (pieceAt(x,y)==null) {
            return false;
        }
        else {
            return true;
        }
    }

    /*places Piece p at (x,y) on pieces array  
    if (x,y) is out of bounds, does nothing. 
    */

    public void place(Piece p, int x, int y) {
        if (inBounds(x,y) ) {

        pieces[x][y] = p;


    }
}


    public Piece remove(int x, int y) {
        if (inBounds(x,y) ) {

            if (pieceAt(x,y) != null) {
                Piece p = pieceAt(x,y);
                pieces[x][y] = null;
                return p;
        }

    

        else {
            System.out.println("There is no piece to remove here silly");

            return null;
        }

    }
    else {
        System.out.println("Get back in bounds!!");
        return null;
    }


}


 /* if there is a piece p at (x, y), selected = p; 
 And, the square at (x,y) is colored white to show
 that a piece has been selected. 
    */
    public void select(int x, int y) {
        
        if (selectedPiece == null) {

            spX = x;
            spY = y;
            sx = x;
            sy = y;
            selectedPiece = pieces[x][y];
            firstClick = true;
        }
         else if (pieceAt(x,y)==null) {



            if (spX + 2 == x || spX - 2 == x ) {

                if(selectedPiece.isFire()) {
                    fireScore ++;
                }
                else {
                    waterScore++;
                }
            }

            selectedPiece.move(x,y);
            spX = x;
            spY = y;
                
            sx =x;
            sy =y;
            moved = true;




            }
        else {
            selectedPiece = pieceAt(x,y);
            spX = x;
            spY = y;
                
            sx =x;
            sy =y;

        }
    
}

    /*determines whether or not a square can be selected
    */

    public boolean canSelect(int x, int y) {




        if (!inBounds(x,y)) {
            return false;
        }
        Piece p = pieceAt(x, y);
        if (p != null) {


            if (p.side() == turn && (selectedPiece == null || !moved) ) {




                return true;

            }
        }
        else {



            if (selectedPiece != null   ) {

                    

                if (!moved || selectedPiece.hasCaptured()) {
 

                    if (validMove(spX, spY, x, y)) {
                        return true;
                    }
                }


            }


        }


        return false;

    }

     /*returns true if (x,y) is a valid move for the piece at (px, py)
 */
    private boolean validMove(int px, int py, int x, int y) {
 


        
        //simply diagonal move
        if (pieceHere(x, y) || !pieceHere(px, py) ) {


       
            return false;
            }
            Piece p = pieceAt(px, py);
            int s = p.side();
   if (p.isKing()|| s==0 ) {


        if ((px + 1 == x  || px - 1 == x) && (py + 1 == y ) && !p.hasCaptured()) {

            return true; 
            

        }
        else if (px + 2 == x && py +2 == y ) {
        
        
            if (pieceHere(px+1, py+1) ) {
                if (!(pieceAt(px+1, py+1).side()== p.side())) {
                    return true; 
                }
 
            }
        }
        else if (px - 2 == x && py +2 == y ) {
        
            if (pieceHere(px-1, py+1) ) {
                if (!(pieceAt(px-1, py+1).side()== p.side())) {
                    return true; 
                }

            }
        }
    

    }

    if (p.isKing() || s==1) {



        if ((px + 1 == x  || px - 1 == x) && (py - 1 == y ) && !p.hasCaptured()) {
            return true; 
            

        }
        else if (px + 2 == x && py -2 == y ) {
        
        
            if (pieceHere(px+1, py-1) ) {
                if (!(pieceAt(px+1, py-1).side()== p.side())) {
                    return true; 
                }

                
            }
        }
        else if (px - 2 == x && py -2 == y ) {

        
        
            if (pieceHere(px-1, py-1) ) {
                if (!(pieceAt(px-1, py-1).side()== p.side())) {
                    return true; 
                }

            }
        }
    } 

    return false;

}
            





    private void drawBoard() {


        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (sx == i && sy ==j && firstClick==true)   {                    
                    StdDrawPlus.filledSquare(i + .5 , j + .5, .5);
               




            /*if (b.pieceAt((int) x,(int) y) != null) {
            Piece p = b.pieceAt((int) x,(int) y);
            b.drawPiece(p, (int) x, (int) y);  }*/
       

                }
            if (pieces[i][j] != null) {
                            Piece p = pieceAt(i, j);
                            drawPiece(p,i,j );
                        }

            }
        }
    }
    




    public static void main(String[] args) {
    Board b = new Board(false);

    while (true ) {
    
    b.drawBoard();
  


    if (StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();

        int cx = (int) x;
        int cy = (int) y;
        if (b.canSelect(cx, cy) ) {

            b.select(cx,cy);
        }

        /*if (b.selectedPiece == null) {
            if (b.pieceAt((int) x,(int) y) != null) {



                b.selectedPiece = pieceAt((int) x,(int) y);

             }
            if (b.canSelect( (int) x, (int) y)) {
                b.selectedPiece.move( (int) x, (int) y); //we just moved the piece, now
                b.firstClick = false;                   // uncolor the selected square
                b.moved = true;

            



            }
        }
        else if (b.canSelect((int)x, (int)y)) {
            b.firstClick = true;*/
            
            }

     if (StdDrawPlus.isSpacePressed()) {

        if (b.canEndTurn()) {
            b.endTurn();
        }
    }
StdDrawPlus.show(100);
        }



}
  

   

           
            
    public boolean canEndTurn() {

        if (moved) {
            return true;
        }
        else {
            return false;
        }
    }

    public void endTurn() {



        if (turn ==0 ) {
            turn =1;
        }
        else {
            turn = 0; 
        }
        moved = false;
        firstClick = false;
        


        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
        }

        selectedPiece=null;






    }

    private boolean piecesOnBoard() {
    if (!(pieces==null)) {
     boolean firePieces = false;
     boolean waterPieces = false;
             for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                if (pieceAt(i, j)!=null) {


                    Piece p = pieceAt(i, j);

    
                    if (p.side() ==0) {


                        firePieces = true;
                    }
                    else {


                        waterPieces = true;
                    }
                }




            }

       }

          if (!firePieces && !waterPieces) {

        return false;
    }
    return true;
    }
    return false;
}

    public String winner(){
             boolean firePieces = false;
     boolean waterPieces = false;
    if (!(pieces==null)) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                if (pieceAt(i, j)!=null) {


                    Piece p = pieceAt(i, j);

    
                    if (p.side() ==0) {


                        firePieces = true;
                    }
                    else {


                        waterPieces = true;
                    }
                }




            }

       }
    }


        if (!firePieces || !waterPieces) {

        gameOver = true;

    }
        if (!piecesOnBoard()){
            return "No one";

        }
        if (gameOver) {

             

         if (fireScore > waterScore) {
            return "Fire";
        }
        else if (fireScore < waterScore) {
            return "Water";
        }
        else  {
            return "No one";

        }
    }
    
        return null;


    }

    /* returns true if (x,y) is on the board
    */
    private boolean inBounds(int x, int y) {
     if (x >= 0 && x <8 && y >= 0 && y <8 ) {
        return true;
     }
     else {
        return false;
     }


    }

    

    private void placePieces() {
        for (int j = 0; j < 8; j++) {
        if (( j) % 2 == 0)  {
            
                pieces [j][0] = new Piece(true, this, j, 0, "pawn");
                }
        else;

            }

    for (int j = 0; j < 8; j++) {
        if (j % 2 == 1)  {
          
            pieces [j][1] = new Piece(true, this, j, 1, "shield");
                }
        else;
          
            
        }

    for (int j = 0; j < 8; j++) {
        if (( j) % 2 == 0)  {

            pieces [j][2] = new Piece(true, this, j, 2, "bomb");
                }
        else;
          
            
        }

            for (int j = 0; j < 8; j++) {
        if (( j) % 2 == 1)  {
            pieces [j][7] = new Piece(false, this, j, 7, "pawn");
                }
        else;

            }

    for (int j = 0; j < 8; j++) {
        if (( j) % 2 == 0)  {

            pieces [j][6] = new Piece(false, this, j, 6, "shield");
                }
        else;
          
            
        }

    for (int j = 0; j < 8; j++) {
        if (( j) % 2 == 1)  {

            pieces [j][5] = new Piece(false, this, j, 5, "bomb");
                }
        else;
          
            
        }
    

    }




/* draws the piece p at the location (x,y)
*/
    private void drawPiece(Piece p, int x, int y ) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);


        if (inBounds(x, y) ) {
            
        
        String t = "";
        if (p.isKing()) {
            t = "king";


        }
        else if (p.isBomb()){
            t= "bomb";

        }
        else if (p.isShield()) {
            t = "shield";
        }

        else {
            t = "pawn";
        }
        String player = "";
        if (p.side()==0) {
            player = "fire";
        }
        else {
            player = "water";
        }
        String s = "img/";
        StringBuilder sb = new StringBuilder(s);
        sb.append(t);
        sb.append("-");
        sb.append(player);
        sb.append(".png");
        s = sb.toString();
        StdDrawPlus.picture(x + .5, y + .5, s, 1, 1);



        }



    }
}


















