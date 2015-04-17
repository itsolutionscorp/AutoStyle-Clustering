public class Board {
    /** Location of pieces. */
    private static Piece[][] pieces;
    private static int N;


// fire pieces start
    private static boolean fireTurn = true;
    private Piece selected = null; // selected piece
    
    private static int selectx;
    private static int selecty;

    private static boolean moved = false; // true if the piece has been moved
    private static boolean captured = false; // captured piece




    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
     // kljjl asjdlkj

        
    // constructor
    public Board(boolean shouldBeEmpty){
        
        N = 8; // DON'T FORGET TO DELETE IT LATER!!!
        pieces = new Piece [N][N];
        if (shouldBeEmpty){
            return;
         } else {

            for (int j = 0; j<N; j++){
                for (int i = 0; i < N; i++){

                    if (j==0 && i%2==0){
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }  else if (j==1 && i%2==1){
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }  else if (j==2 && i%2==0){
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }  else if (j==5 && i%2==1){
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }  else if (j==6 && i%2==0){
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }  else if (j==7 && i%2==1){
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }     

                    
                }

            }
            

         }
        
    }



    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                

                // Why do we need the line below?
                //StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null){

                    if (pieces[i][j].isBomb() && pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                    } else if (pieces[i][j].isShield() && pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
        
                    } else if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                        
                    } else if (pieces[i][j].isBomb() && !pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                        
                    } else if (pieces[i][j].isShield() && !pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }

                        
                    } else if (!pieces[i][j].isFire()) {
                         if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                        
                    }






                }
                
            }
        }


    }


    public Piece pieceAt(int x, int y){
        if (x<N & y<N & x>=0 & y>=0){
            return pieces[x][y];
        } else {
            return null;
        }

    }
// MAKING CHANGES in place
    public void place(Piece p, int x, int y){
        if (p!=null && x<N && y<N && x>=0 && y>=0){
            
            //p.x = x;
            //p.y = y;
            pieces[x][y] = p;

        } else {
            return;
        }

    }

    public Piece remove(int x, int y){
        Piece p = pieceAt(x, y);
        if (p!=null && x<N && y<N && x>=0 && y>=0){

            pieces[x][y] = null;

        } else if (x>=N || y>=N || x<0 || y<0){
            System.out.println("out of bounds");
            
        } else {
            System.out.println("no piece at (" + x + ", " +y +")");
            
        }
        return p;
    }

    private boolean ValidMove(int xi, int yi, int xf, int yf){

        Piece p = pieceAt(xi,yi);
        Piece p2 = pieceAt(xf,yf);
        boolean output = false;
        // KING
        if (p.isKing() && p2 == null) {         
            //  King's moving
            if ( captured == false && ( xf == xi+1 || xf == xi-1) && (yf == yi+1 || yf == yi-1 ) ) {
                    output = true;
            //  King's capturing
            } else if (xf == xi+2 && yf == yi+2 && pieceAt(xi+1,yi+1)!=null){
                if (pieceAt(xi+1,yi+1).isFire() != p.isFire()) {
                    output = true;
                }
            } else if (xf == xi-2 && yf == yi-2 && pieceAt(xi-1,yi-1)!=null){
                if (pieceAt(xi-1,yi-1).isFire() != p.isFire()) {
                    output = true;
                }

            } else if (xf == xi+2 && yf == yi-2 && pieceAt(xi+1,yi-1)!=null){
                if (pieceAt(xi+1,yi-1).isFire() != p.isFire()) {
                    output = true;
                }
            }  else if (xf == xi-2 && yf == yi+2 && pieceAt(xi-1,yi+1)!=null){
                if (pieceAt(xi-1,yi+1).isFire() != p.isFire()) {
                    output = true;
                }
            }         
        // NON-KING FIRE
        }  else if (!p.isKing() && p.isFire() && p2 == null) {         
            // Fire piece's moving
            if (captured == false && (xf == xi+1 || xf == xi-1) && (yf == yi+1) ) {
                output = true;
            // Fire piece's capturing
            } else if (xf == xi+2 && yf == yi+2 && pieceAt(xi+1,yi+1)!=null){
                if (!pieceAt(xi+1,yi+1).isFire()) {
                    output = true;
                }
            } else if (xf == xi-2 && yf == yi+2 && pieceAt(xi-1,yi+1)!=null){
                if (!pieceAt(xi-1,yi+1).isFire()) {
                    output = true;
                }
            }
        // NON-KING WATER
        }  else if (!p.isKing() && !p.isFire() && p2 == null) {         
            // water piece's moving
            if (captured ==false && (xf == xi+1 || xf == xi-1) && (yf == yi-1) ) {
                output = true;
            // water piece's capturing
            } else if (xf == xi+2 && yf == yi-2 && pieceAt(xi+1,yi-1)!=null){
                if (pieceAt(xi+1,yi-1).isFire()) {
                    output = true;
                }
            } else if (xf == xi-2 && yf == yi-2 && pieceAt(xi-1,yi-1)!=null){
                if (pieceAt(xi-1,yi-1).isFire()) {
                    output = true;
                }
            } 
        }
        return output;

    }

    
    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x, y);
        boolean output1 = false;

        // if there is a piece at (x y)
        if (p!=null) {
            if (p.isFire() == fireTurn && (selected == null || (selected != null && moved==false)))
            output1 = true;
        // if there is no piece at (x y)
        } else {
            if (selected != null && ValidMove(selectx, selecty, x, y) ){
                output1 = true;
            } 

        

        }
        return output1;

    }




    public void select (int x, int y){
        
        
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);

        //--------------------------- WORKING AREA
        if (selected == null){
                     selected = pieceAt(x,y);
                    selectx = x;
                    selecty = y;;
                     
    
        } else if (selected !=null) {
                        if (pieceAt(x,y)==null) {

                            selected.move(x, y);
                            moved = true;
                            selectx = x;
                            selecty = y;

                            if (!selected.isBomb()){
                                captured = selected.hasCaptured();
                            } else {
                                if(selected.hasCaptured()){
                                    if (pieceAt(x+1,y+1)!=null && !pieceAt(x+1,y+1).isShield()) {
                                            remove(x+1,y+1);    
                                    }
                                    if (pieceAt(x-1,y-1)!=null && !pieceAt(x-1,y-1).isShield()) {
                                             remove(x-1,y-1); 
                                    }
                                    if (pieceAt(x+1,y-1)!=null && !pieceAt(x+1,y-1).isShield()) {
                                            remove(x+1,y-1); 
                                    }
                                    if (pieceAt(x-1,y+1)!=null && !pieceAt(x-1,y+1).isShield()) {
                                            remove(x-1,y+1);   
                                    }
                                               
                                    
                                    selected.doneCapturing();


                                }
                            }

                    

                        // if piece == null;
                            //this is finalized
                        } else if (pieceAt(x, y)!=null){
                                selected = pieceAt(x,y);
                                selectx = x;
                                selecty = y;;
                        }

                    } // else if selected != null
                        //--------------------------------WORKING AREA
















        //if (selected != null){
        //System.out.println(selected.x +"  " + selected.y + "   " +selected.type+"   "+ selected.isFire());

        //}
    }

    public boolean canEndTurn(){    
        return moved;
    }

    public void endTurn(){
        fireTurn = !fireTurn;
        selected = null;
        moved = false;
        captured = false;
    }

    public String winner() {
        String win = null;
        int numFire = 0;
        int numWater = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j]!=null){
                    if (pieces[i][j].isFire()){
                        numFire = numFire+1;
                    } else {
                        numWater = numWater+1;
                    }
                }
            }
        }

        if (numFire==0 && numWater==0){
            win = "No one";
            System.out.println(win);
        } else if (numFire==0){
            win = "Water";
            System.out.println(win);

        } else if (numWater==0){
            win = "Fire";
            System.out.println(win);

        }

        return win;

    }


    public static void main(String[] args) {
        
            N = 8;
            StdDrawPlus.setXscale(0, N);
            StdDrawPlus.setYscale(0, N);
            

            Board b = new Board(false);

           // placement of pieces starts here!!!
           //--------------------------------------------------------------------
/*

            Piece p1 = new Piece(true, b, 0, 0, "pawn");
            b.place(p1, 1, 1);


            Piece p2 = new Piece(false, b, 2, 2, "bomb");
            b.place(p2,2,2);

            Piece p3 = new Piece(false, b, 4, 4, "bomb");
            b.place(p3,2,4); 


            //--------------------------------------------------------------------

            // placement of pieces ends here!!!


        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        
         while(true) {
             drawBoard(N);

             b.winner();
            
             /*if (StdDrawPlus.isSpacePressed()) {
                    if (b.canEndTurn()){
                            b.endTurn();
                    }     
             } */
            


             if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int x1 = (int)x;
                int y1 = (int)y;



               if (moved==false || captured==true){

                if (b.canSelect(x1,y1)){    
                    
                    
                    b.select(x1, y1);
                     

                
                 } // if canSelect


            
             } //if moved once 



             //if mousePressed
            } else if (StdDrawPlus.isSpacePressed()) {
                        if (b.canEndTurn()){
                                 b.endTurn();
                         }
            } 



            StdDrawPlus.show(100); 
        } //while
    } //main
}