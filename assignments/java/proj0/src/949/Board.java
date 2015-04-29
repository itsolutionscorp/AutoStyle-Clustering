public class Board {
    private Piece[][] pieces = new Piece[8][8]; 
    private boolean fireteam = true;


    private int selected_x; 
    private int selected_y; 

    private Piece selected = null; 
    private int move = 0; 
    private Piece current; 
    private int removefire = 0;
    private int removewater = 0; 


    private boolean hasmoved = false;


    private int allpieces = 0; 
    private int ydiff = 0; 






    public Board(boolean shouldBeEmpty) { 
        if (shouldBeEmpty){

        }
        else{
            initpieces();
        }
        

    }


    private void drawBoard(int N) {
      
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                }
            }
        }
    

    private void initpieces(){
        int N = 8;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
               if ( row % 2 == col % 2 ) {
                  if (row == 0) {
                     pieces[col][row] = new Piece(true, this, col, row, "pawn");
                    }
                  if (row == 1) {
                     pieces[col][row] = new Piece(true, this, col, row, "shield" ); 
                    }
                  if (row == 2) {
                     pieces[col][row] = new Piece(true, this, col, row, "bomb" );
                    }
                  if (row == 5) {
                     pieces[col][row] = new Piece(false, this, col, row, "bomb" );
                    }
                  if (row == 6) {
                     pieces[col][row] = new Piece(false, this, col, row, "shield" );
                    }
                  if (row == 7) {
                     pieces[col][row] = new Piece(false, this, col, row, "pawn" );
                    }

                }   
                
                }
            }
    }
    private void drawpieces(int N) {

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                if (pieceAt(col, row) != null){
                    Piece p = pieceAt(col, row);
                    
                    if (p.isFire()){
                        if (p.isKing()){
                            if (p.isBomb()){
                                StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire-crowned.png", 1, 1);

                            }
                            else { 
                                StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire-crowned.png", 1, 1); 
                            }
                            }
                        
                        else {
                            
                            if (p.isBomb()){
                                StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire.png", 1, 1);}
                            
                            else { 
                                StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire.png", 1, 1); }
                            
                        }
                    }
                    else {
                        if (p.isKing()){
                            if (p.isBomb()){
                                StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(col + .5, row + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else { 
                                StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water-crowned.png", 1, 1); 
                            }
                        }
                        else {
                            if (p.isBomb()){
                                StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (p.isShield()) {
                                StdDrawPlus.picture(col + .5, row + .5, "img/shield-water.png", 1, 1);
                            }
                            else { 
                                StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water.png", 1, 1); 
                            }
                        }
                    }
                }
    //            if ( row % 2 == col % 2 ) {
    //               if (row == 0) {
    //                  pieces[col][row] = new Piece(true, this, col, row, "pawn");
    //              StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire.png", 1, 1); }
    //               if (row == 1) {
    //                  pieces[col][row] = new Piece(true, this, col, row, "shield" ); 
    //              StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire.png", 1, 1);}
    //               if (row == 2) {
    //                  pieces[col][row] = new Piece(true, this, col, row, "bomb" );
    //              StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire.png", 1, 1);}
    //               if (row == 5) {
    //                  pieces[col][row] = new Piece(false, this, col, row, "bomb" );
    //              StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water.png", 1, 1);}
    //               if (row == 6) {
    //                  pieces[col][row] = new Piece(false, this, col, row, "shield" );
    //              StdDrawPlus.picture(col+ .5, row + .5, "img/shield-water.png", 1, 1);}
    //               if (row == 7) {
    //                  pieces[col][row] = new Piece(false, this, col, row, "pawn" );
    //              StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water.png", 1, 1);}

                // }
                 
                
                }
            }
        }



    
       

    public Piece pieceAt(int x, int y) { 
        if (x<0||x>7||y<0||y>7) {
            return null; }
        else { 
            return pieces[x][y]; 
        }

    }

    private boolean checkposition(int x, int y) { 
        if (x<0||x>7||y<0||y>7) {
            return false; }
        else { 
            return true; 
        }
    }

    public void place(Piece p, int x, int y) { 
        if ((!((x<0)||(x>7)||(y<0)||(y>7)))) {
            move = move + 1;

             pieces[x][y] = p;
             allpieces = allpieces +1;   
             if (pieces[x][y].isFire()) {
                removefire = removefire + 1; } 
             else {
                removewater = removewater +1; 


             }


             }

        }
    


    public Piece remove(int x, int y) {
        Piece newpiece = pieceAt(x,y);
        if ((x<0||x>7||y<0||y>7) || (newpiece==null)) {
            System.out.println("Out of bounds or null");
            return null;
        } else {
            Piece removepiece = pieces[x][y];
            if (pieces[x][y].isFire()) {
            removefire = removefire -1;}
            if (!pieces[x][y].isFire()) {
            removewater = removewater -1;}

            pieces[x][y] = null;
            allpieces = allpieces - 1; 
            return removepiece;
        }
    }



private boolean validMove(int x, int y, int xf, int yf) {
    int xdiff = xf - x; 
    int ydiff = yf - y; 
    if ((pieces[x][y] != null) && (pieces[xf][yf] == null && checkposition(xf,yf))) {
                // this is king and does a regular move 
        if ((fireteam == true && pieces[x][y].isFire()) && (Math.abs(ydiff) == 1) && (pieces[x][y].isKing())) { 
            return true; 
        }


                // king and does a jump
        if ((fireteam == true) && (pieces[x][y].isFire()) && (pieces[x][y].isKing()) &&  (Math.abs(ydiff) == 2)  && ((pieceAt(x+1, y+1) != null && !pieces[x+1][y+1].isFire()) || (pieceAt(x-1, y-1) != null && !pieces[x-1][y-1].isFire()) || (pieceAt(x-1, y+1) != null && !pieces[x-1][y+1].isFire())|| ((pieceAt(x+1, y-1) != null) && !pieces[x+1][y-1].isFire()))) { 
            return true; 
        }

            // regular piece move
        if ((fireteam == true) && (pieces[x][y].isFire()) &&  ((xdiff == 1 ) || ((xdiff == -1))) && (ydiff == 1)) { 
            return true; 
        }


        // regular jump 
        if ((fireteam == true )&& (pieces[x][y].isFire()) && (ydiff == 2 )&& ((pieceAt(x+1, y+1) != null && (!pieces[x+1][y+1].isFire())) || ((pieceAt(x-1, y+1) != null ) &&  (!pieces[x-1][y+1].isFire())))) {
            return true; 
        }


        // water piece king move 
        if ((fireteam == false )&& (!pieces[x][y].isFire()) && (Math.abs(xdiff) == 1) && (Math.abs(ydiff) == 1 )&& (pieces[x][y].isKing())) { 
            return true; 
        }


        // water piece king and does a jump
        if ((fireteam == false) && (!pieces[x][y].isFire()) && (pieces[x][y].isKing()) &&  (Math.abs(ydiff) == 2)  && ((pieceAt(x+1, y-1) != null && pieces[x+1][y-1].isFire()) || (pieceAt(x-1, y-1) != null && pieces[x-1][y-1].isFire()) || (pieceAt(x-1, y+1) != null && pieces[x-1][y+1].isFire())|| ((pieceAt(x+1, y-1) != null) && pieces[x+1][y-1].isFire()))) { 
            return true; 
        }


        // water regular move
        if ((fireteam == false) && (!pieces[x][y].isFire()) && (ydiff == -1) && ((xdiff == -1) || (xdiff == 1)) ) { 
            return true; 
        }


        // water regular jump 
        if ((fireteam == false )&& (!pieces[x][y].isFire()) && (ydiff == -2 )&& ((pieceAt(x+1, y-1) != null && (pieces[x+1][y-1].isFire())) || ((pieceAt(x-1, y-1) != null ) &&  (pieces[x-1][y-1].isFire())))){
            return true; 
        }
        return false; 
    } else { 
        return false; 
    }
}


public boolean canSelect(int x, int y) { 
    ydiff = y - selected_y; 
    if (!checkposition(x,y)) {
        return false; 
    }
    
    if (pieceAt(x,y) != null) { 
        if (fireteam && pieces[x][y].isFire()){
            if (!hasmoved){
                return true;

            }
        }
        else if (!fireteam && !pieces[x][y].isFire()){
                return true;
        }

        

    } else {
            if (selected == null) {
                return false; 
            } 
            else if (selected.hasCaptured() && validMove(selected_x, selected_y,x,y) && Math.abs(ydiff) == 2) {
                return false; 
            }

            else  { 
                return validMove(selected_x, selected_y,x,y);
            }
    }
    return false; 

}   

public void select(int x, int y){ 

    current = pieceAt(x,y);
    if (current != null) {
        selected_x = x; 
        selected_y = y; 
        selected = current; } 
    else {
        selected.move(x,y);
        hasmoved = true; 
       
         
         

        }
    }


public void endTurn() { 
    hasmoved = false; 
    fireteam = !fireteam; 
    selected.doneCapturing();



}

//when do you end a turn?
// if it is the fire's turn and fire has moved and has not captured
// same thing goes with water
public boolean canEndTurn() {
     if ((hasmoved == true) && (fireteam == true)) {
        
    
        return true ; 
        
    }
    
    if ((hasmoved == true) && (fireteam == false)) { 
        

        return true; 
        
    }
    

    
    else { 
        return false; 
    }


}

public String winner() { 
    int N = 8; 
    int firecount = 0; 
    int watercount = 0; 
    for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                Piece p = pieceAt(col, row);
                if (pieceAt(col,row) != null && p.isFire()) { 
                    firecount += 1; }
                else if (pieceAt(col,row) != null && !p.isFire()){ 
                    watercount +=1;
                

                }
            }
        }
            if (firecount == 0 && watercount == 0) {
                    return "No one";

                
                }
                if (firecount == 0) {
                    return "Water";
                }
                if (watercount == 0) {
                    return "Fire";}
                 else {
                    return null; 
                }

                }


            
    


















    

    

        


    





   



    


    

    
    
    

    public static void main(String[] args) {
        Board b = new Board(false); 
        b.drawBoard(8);
        b.drawpieces(8);
        

        while(b.winner() != null) {
            b.drawBoard(8);
            b.drawpieces(8);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                System.out.println(b.pieceAt(x, y));
  
                if (b.canSelect(x,y)) { 
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus. filledSquare(x + .5, y + .5, .5);
                    b.select(x,y); 

                    
                }



            }   

            if (StdDrawPlus.isSpacePressed()){
                if (b.canEndTurn()){
                    b.endTurn();
                    //b.winner();
                    
                }
            }
            StdDrawPlus.show(100);       
            
        }
    }



     
        
     

     // while (true) { 
         



        
      

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        
        
    


}