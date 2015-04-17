
public class Board {
    private Piece[][] pieces;
    private int N = 8;
    private boolean hasSelected;
    private int whoseTurn = 0; //should i set to equal 0, since fire always starts?
    private Piece selectedPiece;
    private boolean pieceMoved = false; //tracks any board movement for endTurn
    private String pieceType; //used for drawing
    private int selectedX; //location of selectedPiece
    private int selectedY;
    
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        boolean shouldBeEmpty;
        Board test = new Board(false);
        boolean active = true;

        
        while(true) {
        test.drawBoard(N);
        if (StdDrawPlus.mousePressed()) {
            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();
            int i = (int) x;
            int j = (int) y;
            if (test.canSelect(i, j) == true) {
                test.select(i, j);
                System.out.println("selected" + x + y);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.show(1000);
                }
            }
        if (StdDrawPlus.isSpacePressed() == true) {
                if (test.canEndTurn() == true) {
                    //should we end turn? what if multi-capture?
                    test.endTurn();
                }
            }
        StdDrawPlus.show(100); 
        } 
        
    
 }      


       
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.PINK);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isBomb() == true) {
                        pieceType = "bomb";
                    }
                    else if (pieceAt(i, j).isShield() == true) {
                        pieceType = "shield";
                    }
                    else {
                        pieceType = "pawn";
                    }
                    if (pieceAt(i, j).side() == 0) { //fire piece
                        if (pieceAt(i, j).isKing() == true){
                            StdDrawPlus.picture(i + .5, j + .5, 
                                    "img/" + pieceType + "-fire-crowned.png", 1, 1);
                        }
                        StdDrawPlus.picture(i + .5, j + .5, 
                                "img/" + pieceType + "-fire.png", 1, 1);
                    }
                    else { //water piece
                        if (pieces[i][j].isKing() == true){
                            StdDrawPlus.picture(i + .5, j + .5, 
                                    "img/" + pieceType + "-water-crowned.png", 1, 1);
                        }
                    StdDrawPlus.picture(i + .5, j + .5, 
                            "img/" + pieceType + "-water.png", 1, 1);
                }}
            }
        }
    }
   
    public Board(boolean shouldBeEmpty) 
    {
        if (shouldBeEmpty == true) { 
            pieces = new Piece[N][N];
            }
        else {
        pieces = new Piece[N][N];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                  if (j == 0 ) {
                      pieces[i][j] = new Piece(true, this, i, j, "pawn");
                  }
                  if (j == 2) {
                      pieces[i][j] = new Piece(true, this, i, j, "bomb");
                  }
                  if (j == 6) {
                      pieces[i][j] = new Piece(false, this, i, j, "shield");
                  }
                  if (j == 1 ) {
                      pieces[i][j] = new Piece(true, this, i, j, "shield");
                  }
                  if (j == 5) {
                      pieces[i][j] = new Piece(false, this, i, j, "bomb");
                  }
                  if (j == 7) {
                      pieces[i][j] = new Piece(false, this, i, j, "pawn");
                  }
          }}
      }
        }}

    
    public Piece pieceAt(int x, int y) {
        if ( x > 7 || x < 0 || y > 7 || y < 0) {
            return null;
        }
        else {
            return pieces[x][y]; 
        }
    }
    
//  STEP 6  
   public boolean canSelect(int x, int y){ //each time I try to click on a square, we use canSelect. 
       if (pieceAt(x, y) != null) { //we DO have a piece there
           if (pieceAt(x, y).side() != whoseTurn) { //can't select someone else's piece
               return false;
           }
           if (hasSelected == false) { //**do we check if it if your turn?
                return true; //cant select another players piece haha
           }
           else { //when hasSelected is true
               if (canEndTurn() == true) { //how else can we track if it hasn't been moved?
                   //we cannot use selectedPiece.hasMoved
                   return false;
               }
               return true; //you selected a piece but did not move it
               }
           }
        else { //empty square
            if (hasSelected == false) { //you haven't selected a piece, so you can't just click everywhere
                return false;
            }       
            else if (selectedPiece.hasCaptured() == true) { //same selected piece for multi-capture
                if (Math.abs(selectedX-x) == 1 && Math.abs(selectedY - y) == 1) {
                    return false; //you can't capture then scoot over
                }
                else if (validMove(selectedX, selectedY, x, y) == true) { //valid capture move
                    return true;
                }
                return false;  //you captured but can't move there
            }
            else if (pieceMoved == false && validMove(selectedX, selectedY, x, y) == true) { //haven't captured yet
                return true; //valid move
            }
            else {
                return false;
            }
        }
    }
    
   
   private boolean validMove(int xi, int yi, int xf, int yf) {
       if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
           return false; //out of bounds location
       }
       if (pieceAt(xi, yi) == null) {
           return false; //we can't move nothing to somewhere
       }
       if (pieceAt(xf, yf) != null) {
           return false; //we can't move if there is already piece there!
       }
       else if (pieceAt(xi, yi).isKing() == false ) { //not a king
           if (pieceAt(xi, yi).isFire() == true) { //we have a normal fire piece
               return validFire(xi, yi, xf, yf);
           }
           else { //we have a normal water piece
               return validWater(xi, yi, xf, yf);
           }}
        else if (pieceAt(xi, yi).isKing() == true) { //is a king
            return validWater(xi, yi, xf, yf) || validFire(xi, yi, xf, yf);
           }
       return true;
   }
   
   
   private boolean validFire(int xi, int yi, int xf, int yf) {
           if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi + 1, yi + 1) != null) { //if top right and top left occupied
               if (pieceAt(xi - 1, yi + 1).isFire() == true 
                       && pieceAt(xi + 1, yi + 1).isFire() == true) { //both occupied by own team
                   return false;
               }
               else if (pieceAt(xi - 1, yi + 1).isFire() == true 
                       && pieceAt(xi + 1, yi + 1).isFire() == false) { //top left is your own team, right is water
                       if (yf == yi + 2 && xf == xi + 2) { //our only valid move is top right of top right
                            //capture right
                           return true; }
                       return false; }
               else if (pieceAt(xi - 1, yi + 1).isFire() == false 
                       && pieceAt(xi + 1, yi + 1).isFire() == true) { // left is water, top right is your own team
                       if (yf == yi + 2 && xf == xi - 2) { //our only valid move is top left of top left
                            //capture left
                           return true; }
                       
                       return false; }
               else { //both top right and top left are opposing team
                   if ((yf == yi + 2 && xf == xi - 2) || (yf == yi + 2 && xf == xi + 2)) {
                        //capture left or right
                       return true;
                   }
                   
                   return false;
               }}         
           else if (pieceAt(xi - 1, yi + 1) != null  //top left occupied by opponent,
                   && pieceAt(xi - 1, yi + 1).isFire() == false) { //we know top right is empty!
               if (yf == yi + 2 && xf == xi - 2) { //we can move to top left of top left
                   
                   return true;
               }
               else if (yf == yi + 1 && xf == xi + 1) { //we can go to top right, know it's empty
                   
                   return true;
               }
               
               return false; //exhausted our 2 options
               }
           else if (pieceAt(xi + 1, yi + 1) != null  //top right occupied by opponent,
                   && pieceAt(xi + 1, yi + 1).isFire() == false) { //we know top left is empty!
               if (yf == yi + 2 && xf == xi + 2) { //we can move to top right of top right
                   
                   return true;
               }
               else if (yf == yi + 1 && xf == xi - 1) { //we can go to top left, know it's empty
                   
                   return true;
               }
               
               return false; //exhausted our 2 options
               }
           else { //both top left and top right unoccupied
               if ((yf == yi + 1 && xf == xi + 1) || (yf == yi + 1 && xf == xi - 1)) { 
                   
                   return true; //we only have 2 choices
               }
               
               return false;
               }}
   
   private boolean validWater(int xi, int yi, int xf, int yf) {
       if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi + 1, yi - 1) != null) { //if bottom right and bottom left occupied
           if (pieceAt(xi - 1, yi - 1).isFire() == true 
                   && pieceAt(xi + 1, yi - 1).isFire() == true) { //occupied by own team
               
               return false;
           }
           else if (pieceAt(xi - 1, yi - 1).isFire() == false 
                   && pieceAt(xi + 1, yi - 1).isFire() == true) { //bottom left is your own team, right is fire
                   if (yf == yi - 2 && xf == xi + 2) { //our only valid move is bottom right of bottom right
                       
                       return true; }
                   return false; }
           else if (pieceAt(xi - 1, yi - 1).isFire() == true 
                   && pieceAt(xi + 1, yi - 1).isFire() == false) { // left is fire, bottom right is your own team
                   if (yf == yi - 2 && xf == xi - 2) { //our only valid move is bottom left of bottom left
                       
                       return true; }
                   
                   return false; }
           else { //both bottom right and bottom left are opposing team
               if ((yf == yi - 2 && xf == xi - 2) || (yf == yi - 2 && xf == xi + 2)) {
                   
                   return true;
               }
               
               return false;
           }}         
       else if (pieceAt(xi - 1, yi - 1) != null  //bottom left occupied by opponent,
               && pieceAt(xi - 1, yi - 1).isFire() == true) { //we know bottom right is empty!
           if (yf == yi - 2 && xf == xi - 2) { //we can move to bottom left of bottom left
               
               return true;
           }
           else if (yf == yi - 1 && xf == xi + 1) { //we can go to bottom right, know it's empty
               
               return true;
           }
           
           return false; //exhausted our 2 options
           }
       else if (pieceAt(xi + 1, yi - 1) != null  //bottom right occupied by opponent,
               && pieceAt(xi + 1, yi - 1).isFire() == true) { //we know bottom left is empty!
           if (yf == yi - 2 && xf == xi + 2) { //we can move to bottom right of bottom right
               
               return true;
           }
           else if (yf == yi - 1 && xf == xi - 1) { //we can go to bottom left, know it's empty
               
               return true;
           }
           
           return false; //exhausted our 2 options
           }
       else { //both bottom left and bottom right unoccupied
           if ((yf == yi - 1 && xf == xi + 1) || (yf == yi - 1 && xf == xi - 1)) { 
               
               return true; //we only have 2 choices
           }
           
           return false;
           }}
   
   
   private int switchTurn(int x) {
       if (x == 0) {
           return 1;
       }
       else {
           return 0; 
           }
   }
   

  public void select(int x, int y) { //we do not have to check if canSelect is true
        hasSelected = true;
        if (pieceAt(x, y) != null) { //we have a piece there
            selectedPiece = pieceAt(x, y); //sets the one selected piece on the board during current turn
            selectedX = x;
            selectedY = y;
        } //don't move or switch turns yet
        else { //no piece there
            selectedPiece.move(x,y); //we assume we can move there
//            selectedPiece = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
            pieceMoved = true;
        }
      }
    
    public void place(Piece p, int x, int y){ //triggered when move is called or when we place something random
        if (p == null || x > 7 || x < 0 || y > 7 || y < 0) {
        }
        else {
            if (selectedPiece != null) { //or use selectedPiece?
                remove(selectedX, selectedY); //remove p if it already exists
            }
            if (pieceAt(x, y) != null) {
                remove(x, y);  //remove piece from new location
            }
            pieces[x][y] = p; //places p in new location 
            }
    }
    
    public Piece remove(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            System.out.println("input out of bounds, cannot remove piece");
            return null;
        }
        else if (pieceAt(x, y) == null)  {
            System.out.println("no piece at location " + "(x, y) to remove");
            return null;
        }
        else {
            Piece oldPiece = pieceAt(x, y);
            pieces[x][y] = null;
            return oldPiece;
        }
    }
    
    
    

    public boolean canEndTurn() { //piece still selected at end of move
        if (selectedPiece == null) { //if we selected nothing, nope
            return false;
        }
        if (selectedPiece.hasCaptured() == true) { //just captured, haven't done DoneCapture
            return true;
        }
        else if (pieceMoved == true) {
            return true;  //triggered by select
        }
        return false;
    }
    

    public void endTurn() { //end when spacebar is pressed. no checking if you can end turn
        whoseTurn = switchTurn(selectedPiece.side()); //after we moved, piece is deselected. fresh slate
        selectedPiece.doneCapturing(); 
        selectedPiece = null;
        selectedX = -1; //resetting to a random x
        selectedY = -1;
        hasSelected = false; //new player hasn't selected yet
        pieceMoved = false;
    }
   
  
    public String winner() {
        boolean firePresent = false;
        boolean waterPresent = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i,j) != null && pieceAt(i, j).isFire() == true) {
                    firePresent = true;
                }
                else if (pieceAt(i,j) != null && pieceAt(i, j).isFire() == false) {
                    waterPresent = true;
                }
            }}
        if (waterPresent && firePresent) {
            return null;
        }
        else if (firePresent) {
            return "Fire";
        }
        else if (waterPresent) {
            return "Water";
        }
        else {
            return "No one";
        }
    }
}
    
