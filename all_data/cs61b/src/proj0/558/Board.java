/* Board Class for cs61b Project 0
** @author Cameron Bailey
*/
/* Name Indexes:
** pawn = 0, crowned = 1, bomb = 2, bomb crowned = 3, shield = 4, shield crowned = 5
*/
public class Board {
    // String away with all the names
    private String[] fireNames = {"img/pawn-fire.png","img/shield-fire.png", "img/bomb-fire.png"};
    private String[] fireCrowned = {"img/pawn-fire-crowned.png", "img/shield-fire-crowned.png", "img/bomb-fire-crowned.png"};
    private String[] waterNames = {"img/pawn-water.png","img/shield-water.png","img/bomb-water.png"};
    private String[] waterCrowned = {"img/pawn-water-crowned.png","img/shield-water-crowned.png","img/bomb-water-crowned.png"};
    private String[] pieceTypes = {"pawn","shield", "bomb"};
    private Piece[][] pieceArray;
    private boolean isFireTurn = true; // return true if fire's turn. Fire starts so initialize to true
    private Piece selectedPiece; // Holds the most recently selected Piece object
    private int [] coord; // To hold coordinates of the selectedPiece (index 0 = x, 1 = y)
    private boolean isSelected = false; // Is there a selected piece? Set to false by default
    private boolean hasMoved = false; // keeps track if piece moved. false by default. 

    public Board (boolean shouldBeEmpty) {
        if (!shouldBeEmpty){
            putPieces();
        }
        else {
            pieceArray = new Piece[8][8];
        }
    }

    private void putPieces(){
        int k2 = 0; // Add Couter to loop through pieceType array properly
        pieceArray = new Piece[8][8];
        // Add Fire Pieces
        for (int i = 0; i < 3; i+=1) {
            for (int j = 0; j < 8; j+=1) {
                if ((i+j) % 2 == 0) {
                    pieceArray[j][i] = new Piece(true, this, j, i, pieceTypes[k2]);
                }
            }
            k2 += 1;
        }
        //k = 0;
        k2 = 0; // reset counter
        // Add Water Pieces
        for (int i = 7; i >4; i-=1) {
            for (int j = 0; j < 8; j+=1) {
                if ((i+j) % 2 == 0) {
                    pieceArray[j][i] = new Piece(false, this, j, i, pieceTypes[k2]);
                }
             }
             k2 += 1;
         }
    }

    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) // out of bounds
            return null;
        return pieceArray[x][y];
    }

    public void place(Piece p, int x, int y) {
        if ((x > 7) || (y > 7) || (y < 0) || (x < 0))
            return;
        if (p == null)
            return;
        pieceArray[x][y] = p;
    }


    public boolean canSelect(int x, int y) { // Under Construction 
        // First check to see if move is valid based solely on geometry
        // take care of selecting piece first
        /* Note that valid move does not check to see if king moves are available
        ** In other words, canSelect needs to check that piece is moving in correct direction
        ** if not a king
        */
        if ((x > 7) || (y > 7)) {
            return false;
        }
        if (pieceArray[x][y] == null) {
            if ((isSelected) && (validMove(coord[0],coord[1],x,y)) && (selectedPiece != null)) {
                if ((selectedPiece.isKing()) == false) {
                    return rightDirection(x,y);
                }
                return true;
            }
            return false;
        }
        
        // For Square with a Piece
        if (pieceArray[x][y].isFire() && isFireTurn) {
            return canSelectHelper();
        }

        if ((pieceArray[x][y].isFire() == false) && (isFireTurn == false)) {
            return canSelectHelper();
        }

        return false;

    }

    // Check to see if non king piece is moving in right direction
    // x and y are the coordinates piece should move to
    // Assuming a piece is already selected and that piece is not kinged
    private boolean rightDirection(int x, int y) {
        if ((selectedPiece.isFire()) && (y > coord[1])) {
            return true;
        }
        if ((selectedPiece.isFire() == false) && (y < coord[1])) {
            return true;
        }

        return false;
    }
                
    // Returns true if no piece selected or selected piece hasn't moved
    private boolean canSelectHelper() {
        if ((isSelected == false) || (hasMoved == false)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /* Assuming that after moving, piece can only capture... not peform any regular moves.
    ** Hence the hasMoved == false argument in the first if statement
    */
    // Edit: Modified to only allow the 2 unit move if there is intermediate enemy piece
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1) && (hasMoved == false)) {
            return true;
        }
        if ((Math.abs(xi-xf) == 2) && (Math.abs(yi-yf) == 2)) {
            switch (difference(xf, xi, yf, yi)) {
                case 0: {
                    if (isNull((xi-1),(yi-1))) {
                        return false;
                    }
                    if (isFireTurn && ((pieceAt((xi-1),(yi-1)).isFire() == false))) {
                        return true;
                    }
                    if ((isFireTurn == false) && (pieceAt((xi-1),(yi-1)).isFire())) {
                        return true;
                    }
                    break;
                }
                case 1: {
                    if (isNull((xi+1), (yi-1))) {
                        return false;
                    }
                    if (isFireTurn && (pieceAt((xi+1),(yi-1)).isFire() == false)) {
                        return true;
                    }
                    if ((isFireTurn == false) && (pieceAt((xi+1),(yi-1)).isFire())) {
                        return true;
                    }
                    break;
               }
               case 2: {
                     if (isNull((xi-1),(yi+1))) {
                        return false;
                    }
                    if (isFireTurn && (pieceAt((xi-1),(yi+1)).isFire() == false)) {
                        return true;
                    }
                    if ((isFireTurn == false) && (pieceAt((xi-1),(yi+1)).isFire())) {
                        return true;
                    }
                    break;
               }
                case 3: {
                     if (isNull((xi+1),(yi+1))) {
                        return false;
                    }
                    if (isFireTurn && (pieceAt((xi+1),(yi+1)).isFire() == false)) {
                        return true;
                    }
                    if ((isFireTurn == false) && (pieceAt((xi+1),(yi+1)).isFire())) {
                        return true;
                    }
                    break;
               }
               case 4: break;
               default: break;     
            }
        }
        return false;
    }    
    // returns true if null at desired coordinates (no piece at coordinates)
    private boolean isNull(int x1, int x2) {
        if (pieceAt(x1,x2) == null) {
            return true;
        }
        else {
            return false;
        }
    }


    /* return 0 if difference between x1-x2 and y1-y2 is negative
    ** return 1 if (x1-x2)> 0, and (y1-y2) < 0
    ** return 2 if (x1-x2) < 0, and (y1-y2) > 0
    ** return 3 if (x1- x2) > 0 and (y1 -y2) > 0
    */
    private int difference(int x1, int x2, int y1, int y2) { //x1 = xf, y1 = yf
        if (((x1 - x2) < 0) && ((y1 - y2) < 0)) {
            return 0;
        }
        else if (((x1 - x2) > 0) && ((y1 - y2) < 0)) {
            return 1;
        }
        else if (((x1 - x2) < 0) && ((y1 - y2) > 0)) {
            return 2;
        }
        else if (((x1-x2) > 0) && ((y1-y2) > 0)) {
            return 3;
        }
        else {
            return 4;
        }
    }

    public void select(int x, int y) {
        if (pieceArray[x][y] == null) {
            selectedPiece.move(x,y);
            hasMoved = true; // Since this piece has now moved
        }
        else {
            selectedPiece = pieceArray[x][y];
            isSelected = true;
        }
        coord = new int[]{x,y};
    }

    /** Helper Method for drawing pieces on board
     ** Extracts the proper image name based on piece type
     */
    private String getImgName(Piece p) {
        if (p.isBomb()) {
            if (p.isFire()) {
                if (p.isKing()) {
                    return fireCrowned[2];
                }
                return fireNames[2]; // location of bomb
            }
            else {
                if (p.isKing()) {
                    return waterCrowned[2];
                }
                return waterNames[2];
            }
        }
        else if (p.isShield()) {
            if (p.isFire()) {
                if (p.isKing()) {
                    return fireCrowned[1];
                }
                return fireNames[1]; // location of bomb
            }
            else {
                if (p.isKing()) {
                    return waterCrowned[1];
                }
                return waterNames[1];
            }
        }
        else {
            if (p.isFire()) {
                if (p.isKing()) {
                    return fireCrowned[0];
                }
                return fireNames[0];
            }
            else {
                if (p.isKing()) {
                    return waterCrowned[0];
                }
                return waterNames[0];
            }
        }
     }
                                               
    public Piece remove(int x, int y) {
        if ((x > 7) || (y > 7)) {
            System.out.println("Sorry. Out of Bounds");
            return null;
        }
        else if (pieceAt(x,y) == null) {
            System.out.println("Sorry. No piece to remove!");
            return null;
        }
        else {
            Piece removedPiece = pieceAt(x,y);
            pieceArray[x][y] = null;
            return removedPiece;
        }
   }

   public boolean canEndTurn() {
       if(hasMoved) {
           return true;
       }
       return false;
  }

  public void endTurn() {
      if (canEndTurn()) {
          isSelected = false;
          hasMoved = false;
          if (selectedPiece.isFire()) {
              isFireTurn = false;
          }
          else {
              isFireTurn = true;
          }
          selectedPiece.doneCapturing(); // Not using this but for autograder
          selectedPiece = null;
      }
      return;
  }
    // loop through all pieces and check for pieces
    // Keep counter of fire pieces and water pieces

  public String winner() {
       int remainingFire = 0;
       int remainingWater = 0;
       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               if ((pieceAt(i,j) != null)) {
                   if (pieceAt(i,j).isFire()) {
                       remainingFire += 1;
                   }
                   else {
                       remainingWater += 1;
                   }  
               } 
           }
       }
       if ((remainingFire == 0) || (remainingWater == 0)) {
           if (remainingFire > remainingWater) {
               return "Fire";
           }
           else if (remainingFire < remainingWater) {
               return "Water";
           }
           else {
               return "No one";
           }
       }
       return null; //gets to this statement if still fire and water pieces

  }


      /*
      if ((capturedFire ==12) && (capturedWater == 12)) {
          return "No one";
      }
      else if (capturedFire == 12) {
          return "Fire";
      }
      else if (capturedWater == 12) {
          return "Water";
      }
      else {
        return null; // finish after move implemented
     }
  }*/


    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceAt(i,j) != null) {
                    String picReference = getImgName(pieceAt(i,j));
                    if ((isSelected) && (pieceAt(i,j) == selectedPiece)){
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                    StdDrawPlus.picture(i + .5, j + .5, picReference, 1, 1);
                } // if bracket
        }
    }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new boolean[N][N];
        Board thisBoard = new Board(false);
        
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            thisBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int x1 = (int) x;
                int y1 = (int) y;
                if (thisBoard.canSelect(x1,y1)) {
                    thisBoard.select(x1, y1);
                }
            }
            if(StdDrawPlus.isSpacePressed()) {
                if(thisBoard.canEndTurn()) {
                    thisBoard.endTurn();
                }
            }            
            StdDrawPlus.show(20);
            if (thisBoard.winner() != null) {
                return;
            }
          
        }
    }
}
