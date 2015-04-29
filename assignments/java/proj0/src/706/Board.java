// Author: Cameron Bates
// Project 0, CS 61B

public class Board {
    private Piece[][] pieces = new Piece[8][8];
    private int turn;
    private int moves;
    private Piece selectedpiece = null;
    private int selectedpiecex;
    private int selectedpiecey;
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
        And then leeched by yours truly for this project!
     */

    private void DrawStuff(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { 
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            } 
            
            
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (pieces[k][l] != null) {
                    if (pieces[k][l].isFire() == true && pieces[k][l].isKing() == false && pieces[k][l].isBomb() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/bomb-fire.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == true && pieces[k][l].isKing() == false && pieces[k][l].isShield() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/shield-fire.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == true && pieces[k][l].isKing() == true && pieces[k][l].isBomb() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/bomb-fire-crowned.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == true && pieces[k][l].isKing() == true && pieces[k][l].isShield() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/shield-fire-crowned.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == true && pieces[k][l].isKing() == false && pieces[k][l].isBomb() == false && pieces[k][l].isShield() == false)
                        StdDrawPlus.picture(k + .5, l + .5, "img/pawn-fire.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == true && pieces[k][l].isKing() == true && pieces[k][l].isBomb() == false && pieces[k][l].isShield() == false)
                        StdDrawPlus.picture(k + .5, l + .5, "img/pawn-fire-crowned.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == false && pieces[k][l].isKing() == false && pieces[k][l].isBomb() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/bomb-water.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == false && pieces[k][l].isKing() == false && pieces[k][l].isShield() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/shield-water.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == false && pieces[k][l].isKing() == true && pieces[k][l].isBomb() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/bomb-water-crowned.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == false && pieces[k][l].isKing() == true && pieces[k][l].isShield() == true)
                        StdDrawPlus.picture(k + .5, l + .5, "img/shield-water-crowned.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == false && pieces[k][l].isKing() == false && pieces[k][l].isBomb() == false && pieces[k][l].isShield() == false)
                        StdDrawPlus.picture(k + .5, l + .5, "img/pawn-water.png", 1, 1);
                    
                    if (pieces[k][l].isFire() == false && pieces[k][l].isKing() == true && pieces[k][l].isBomb() == false && pieces[k][l].isShield() == false)
                        StdDrawPlus.picture(k + .5, l + .5, "img/pawn-water-crowned.png", 1, 1);
                }
                    
            }   
        }       
            
        }

    }  

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == false){
        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
        pieces[7][1] = new Piece(true, this, 7, 1, "shield");
        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
        pieces[6][6] = new Piece(false, this, 6, 6, "shield");
        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
        }
    }

    public Piece pieceAt(int x, int y){
        if (0 > x || x > 7 || 0 > y || y > 7)
            return null;
        if (pieces[x][y] == null)
            return null;
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y){
        // CURRENTLY BUGGED: If you have a multiple turn piece going and selct another, your turn ends.
        // Still need to decide whose turn it is and whether the second click is legal!
        // int c = 0;
        // for (int a = 0; a < 8; a++){
        //     for (int b = 0; b < 8; b++){
        //         if (validmove(x,y,a,b) == true){
        //             c = c + 1;
        //         }
        //     }    
        // } 
        // If you select a piece, does it have a valid move?   
        if (turn % 2 == 0 && pieceAt(x,y) != null && pieces[x][y].isFire() == true && moves == 0)
             // && c > 0
            return true;
        if (turn % 2 == 1 && pieceAt(x,y) != null && pieces[x][y].isFire() == false && moves == 0)
            // && c > 0
            return true;

        // What if you select a Blank Space #TSwift 5evr?
        
        if (pieceAt(x,y) == null && selectedpiece != null){
            if(validmove(selectedpiecex,selectedpiecey,x,y) && moves == 0)
                return true;
            if (moves > 0){
                    if (validcapture(selectedpiecex,selectedpiecey,x,y) && selectedpiece.hasCaptured() == true)
                        return true;
                else{
                    return false;
                }
            }       
        }          
        return false;
    }

    public void select(int x, int y){
        if (-1 < x && x < 8 && -1 < y && y < 8){
            if (pieceAt(x,y) != null){
                selectedpiece = pieces[x][y];
                selectedpiecex = x;
                selectedpiecey = y;
            }
            if (pieceAt(x,y) == null && selectedpiece != null){
                // if (validmove(selectedpiecex,selectedpiecey,x,y)){
                        moves = moves + 1;
                        selectedpiece.move(x,y);
                        selectedpiecex = x;
                        selectedpiecey = y;
                 //   }
            }       
        }    
    } 

    // Before move can be called, we have to make sure the move is valid!
    private boolean validcapture(int xi, int yi, int xf, int yf){
        if (xf < 0 || xf > 7 || yf < 0 || yf > 7){
           return false;
        }
        if (pieces[xi][yi] == null)
            return false;
        // 1. Fire piece, only moving up
        if (pieces[xi][yi].isFire() == true && pieces[xi][yi].isKing() == false){
            if (xf - xi == 2 && yf - yi == 2){
                if (pieces[xf - 1][yf - 1] != null){
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }    
                
            if (xf - xi == -2 && yf - yi == 2){    
                if (pieces[xf + 1][yf - 1] != null){
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
        }

         // 2. Water piece, only moving down
        if (pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing() == false){    
            if (xf - xi == 2 && yf - yi == -2){
                if (pieces[xf - 1][yf + 1] != null){        
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
            if(xf - xi == -2 && yf - yi == -2){
                if (pieces[xf + 1][yf + 1] != null){     
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            } 
        } 
        
        // 3. Fire, can now move up or down!
        // Moving Up
        if (pieces[xi][yi].isFire() == true && pieces[xi][yi].isKing() == true){   
             if (xf - xi == 2 && yf - yi == 2){ 
                if (pieces[xf - 1][yf - 1] != null){     
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }    
            if (xf - xi == -2 && yf - yi == 2){ 
                if (pieces[xf + 1][yf - 1] != null){    
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }   
            // Moving Down
             if (xf - xi == 2 && yf - yi == -2){     
                if (pieces[xf - 1][yf + 1] != null){ 
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf + 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }        
            if (xf - xi == -2 && yf - yi == -2){     
                if (pieces[xf + 1][yf + 1] != null){     
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf + 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }    
            }    
        }

         // 4. Water, moving up or down.
        // Moving Down
        if (pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing() == true){   
            if (xf - xi == 2 && yf - yi == -2){     
                if (pieces[xf - 1][yf + 1] != null){  
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
            if (xf - xi == -2 && yf - yi == -2){     
                if (pieces[xf + 1][yf + 1] != null){          
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){ 
                        return true;
                    }
                }
            }        
            // Moving Up 
            if (xf - xi == 2 && yf - yi == 2){     
                if (pieces[xf - 1][yf - 1] != null){  
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf - 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
            if (xf - xi == -2 && yf - yi == 2){     
                if (pieces[xf + 1][yf - 1] != null){          
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf - 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }                 
        }
        return false;    

    }

    private boolean validmove(int xi, int yi, int xf, int yf){
        //4 Cases:
        //1. Fire piece, not king.
        //2. Water piece, not king.
        //3. Fire piece, kinged.
        //4. Water piece, kinged.

        // 1. Fire piece, only moving up
        if (xf < 0 || xf > 7 || yf < 0 || yf > 7){
           return false;
        }
        if (pieces[xi][yi] == null){
            return false;
        }    
        if (pieces[xi][yi].isFire() == true && pieces[xi][yi].isKing() == false){
           // Non-capturing move
            if (xf - xi == 1 && yf - yi == 1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }    
            if (xf - xi == -1 && yf - yi == 1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }    
            // Capturing move
            // First check that your values are within the bounds. Then check that the region between the capture locations is not null. Then ensure that you are capturing the right kind of piece
            // This seems wrong. It should only look at cases after you give two mouse clicks. I'm getting errors after only the first. INVESTIGATE!
            if (xf - xi == 2 && yf - yi == 2){
                if (pieces[xf - 1][yf - 1] != null){
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }    
            if (xf - xi == -2 && yf - yi == 2){    
                if (pieces[xf + 1][yf - 1] != null){
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }    
        }
        // 2. Water piece, only moving down
        if (pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing() == false){
           // Non-capturing move
            if (xf - xi == 1 && yf - yi == -1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            if (xf - xi == -1 && yf - yi == -1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            // Capturing move
            if (xf - xi == 2 && yf - yi == -2){
                if (pieces[xf - 1][yf + 1] != null){        
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf + 1] != null && pieces[xf - 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
            if(xf - xi == -2 && yf - yi == -2){
                if (pieces[xf + 1][yf + 1] != null){     
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf + 1] != null && pieces[xf + 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }    
        }
        // 3. Fire, can now move up or down!
        // Moving Up
        if (pieces[xi][yi].isFire() == true && pieces[xi][yi].isKing() == true){
           // Non-capturing move
            if (xf - xi == 1 && yf - yi == 1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            if (xf - xi == -1 && yf - yi == 1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            // Capturing move
            if (xf - xi == 2 && yf - yi == 2){ 
                if (pieces[xf - 1][yf - 1] != null){     
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }    
            if (xf - xi == -2 && yf - yi == 2){ 
                if (pieces[xf + 1][yf - 1] != null){    
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf - 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }   
            // Moving Down
                if (xf - xi == 1 && yf - yi == -1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
                }
                if (xf - xi == -1 && yf - yi == -1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
                }
            // Capturing move
            if (xf - xi == 2 && yf - yi == -2){     
                if (pieces[xf - 1][yf + 1] != null){ 
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf + 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }        
            if (xf - xi == -2 && yf - yi == -2){     
                if (pieces[xf + 1][yf + 1] != null){     
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf + 1].isFire() == false && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }    
            }    
        }    
        // 4. Water, moving up or down.
        // Moving Down
        if (pieces[xi][yi].isFire() == false && pieces[xi][yi].isKing() == true){
            // Non-capturing move
            if (xf - xi == 1 && yf - yi == -1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            if (xf - xi == -1 && yf - yi == -1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            // Capturing move
            if (xf - xi == 2 && yf - yi == -2){     
                if (pieces[xf - 1][yf + 1] != null){  
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
            if (xf - xi == -2 && yf - yi == -2){     
                if (pieces[xf + 1][yf + 1] != null){          
                    if (pieces[xf][yf] == null && pieces[xf + 1][yf + 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){ 
                        return true;
                    }
                }
            }        
            // Moving Up        
            if (xf - xi == 1 && yf - yi == 1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            if (xf - xi == -1 && yf - yi == 1 && pieces[xf][yf] == null && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                return true;
            }
            // Capturing move
            if (xf - xi == 2 && yf - yi == 2){     
                if (pieces[xf - 1][yf - 1] != null){  
                    if (pieces[xf][yf] == null && pieces[xf - 1][yf - 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }
            if (xf - xi == -2 && yf - yi == 2){     
                if (pieces[xf + 1][yf - 1] != null){          
                    if (xf - xi == -2 && yf - yi == 2 && pieces[xf][yf] == null && pieces[xf + 1][yf - 1] != null && pieces[xf + 1][yf - 1].isFire() == true && xf > -1 && xf < 8 && yf > -1 && yf < 8){
                        return true;
                    }
                }
            }                 
        }
        return false;       
    }
   

    public void place(Piece p, int x, int y){
        if (-1 < x && x < 8 && -1 < y && y < 8 && p != null){
           for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (pieces[i][j] == p)
                        pieces[i][j] = null;
                }
            }
            pieces[x][y] = p;
        }

    }

    public Piece remove(int x, int y){
        Piece removal = pieces[x][y];
        pieces[x][y] = null;
        return removal;
    }
    public boolean canEndTurn(){
        if (moves > 0 )
            return true;
        else
            return false;
    }   

    public void endTurn(){
        if (selectedpiece != null)
            selectedpiece.doneCapturing();
        selectedpiece = null; 
        turn = turn + 1;
        moves = 0;
    }

    public String winner(){
        int a = 0;
        int b = 0;
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (pieces[i][j] != null){
                    if (pieces[i][j].isFire() == true)
                        a = a + 1;
                }
                if (pieces[i][j] != null){    
                    if (pieces[i][j].isFire() == false)
                        b = b + 1;
                }    
            }            
        }
        if (a == 0 && b > 0){
            System.out.println("Long live the water tribe!");
            return "Water";
        }    
        if (b == 0 && a > 0){
            System.out.println("The Fire Nation Scum have defeated us!");
            return "Fire";
        }
        if (a == 0 && b == 0){
            System.out.println("In a way, I guess we're both winners. That way being that we both lost.");
            return "No one";
        }
        else
            return null;                    
    }


    public static void main(String args[]){
        Board NewGame = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);      
        
        while(NewGame.winner() == null) {
            NewGame.DrawStuff();
            StdDrawPlus.show(100);
        
            if (StdDrawPlus.mousePressed()) {
                double xSelect = StdDrawPlus.mouseX();
                double ySelect = StdDrawPlus.mouseY();      
                    if (NewGame.canSelect((int) xSelect,(int) ySelect)){ 
                        NewGame.select((int) xSelect,(int) ySelect);
                    } 
                          
                    
            }
            if (StdDrawPlus.isSpacePressed()){   
                if (NewGame.canEndTurn()){ 
                    NewGame.endTurn();
                }    
            }

            

          
       }
       NewGame.DrawStuff();
       StdDrawPlus.show(100);
    }
}      