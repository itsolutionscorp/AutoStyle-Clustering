public class Board {

    private Piece[][] pieces;
    private boolean firesTurn;
    private Piece selectedPiece;
    private int selectedX;
    private int selectedY;
    private boolean hasMoved; //SWITCH BACK TO PRIVATE
    private int numFire;
    private int numWater;
    
    
    public Board(boolean shouldBeEmpty) {
        hasMoved = false;
        firesTurn = true;
        numFire = 0;
        numWater = 0;
        pieces = new Piece[8][8];
        selectedX = -1;
        selectedY = -1;
        selectedPiece = null;
        

        
        if (!shouldBeEmpty) {    
            hasMoved = false;
            firesTurn = true;
            numFire = 8;
            numWater = 8;
            pieces = new Piece[8][8];
            selectedX = -1;
            selectedY = -1;
            selectedPiece = null;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {      
                    if ((i + j) % 2 == 0) {
                        if (j == 0) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                            pieces[i + 1][7 - j] = new Piece(false, this, i + 1, 7 - j, "pawn");
                        }
                        else if (j == 1) { 
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                            pieces[i - 1][7 - j] = new Piece(false, this, i - 1, 7 - j, "shield");
                        }
                        else if (j == 2) {                   
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                            pieces[i + 1][7 - j] = new Piece(false, this, i + 1, 7 - j, "bomb");
                        }   
                    } 
                    
                }
            }
                        
        } 
    }


    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                if (i == selectedX && j == selectedY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                
                else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);          
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }
    
    private void insertPics(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                
                              
                if (isPiece(i, j)) {
                
                    boolean isKing = pieces[i][j].isKing();
                    boolean isFire = pieces[i][j].isFire();
                    boolean isBomb = pieces[i][j].isBomb();
                    boolean isShield = pieces[i][j].isShield();
                
                
                    if (isKing) {
                        if (isFire) { //fire king
                            if (isBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\bomb-fire-crowned.png", 1, 1);
                            } else if (isShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\pawn-fire-crowned.png", 1, 1);
                            }
                        } else { //water king
                            if (isBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\bomb-water-crowned.png", 1, 1);
                            } else if (isShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\pawn-water-crowned.png", 1, 1);
                            }
                        }
                    } else {
                        if (isFire) { //fire unkinged
                            if (isBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\bomb-fire.png", 1, 1);
                            } else if (isShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\shield-fire.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\pawn-fire.png", 1, 1);
                            }
                            
                        } else { //water unkinged
                            if (isBomb) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\bomb-water.png", 1, 1);
                            } else if (isShield) {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\shield-water.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img\\pawn-water.png", 1, 1);
                            }
                        }
                        
                    }
                }


            }
        }
        
    }
    
    private boolean inBounds(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public Piece pieceAt(int x, int y) {
         if (this.inBounds(x, y)) {
             return pieces[x][y];
         } else {
             return null;
         }
    }
    
    private boolean isPiece(int x, int y) {
        if (pieceAt(x, y) == null) {
            return false;
        } else{
            return true;
        }
    }
    
    private boolean rightTurn(int x, int y) {
        if ((firesTurn && pieceAt(x, y).isFire()) || (!firesTurn && !pieceAt(x, y).isFire())) {
            return true;
        } else {
            return false;
         }
    }
    
    private boolean selectCondition1(int x, int y) {
        if (pieceAt(x, y) == null && selectedPiece != null) { //selecting spot with no pieces and piece has already been selected
            
            if (!hasMoved && this.validMove(selectedX, selectedY, x, y)) {
                return true;
            } else if (selectedPiece.hasCaptured() && this.isCapturing(selectedX, selectedY, x, y)) { //HAVE TO MAKE SURE NEW MOVE IS TO CAPTURE ANOTHER PIECE
                return true;
            }
             else {
                return false;
            }
        }
        
        else {
            return false;
        }
    }
    
    
    private boolean selectCondition2(int x, int y) { //selecting square with piece
            
        if (pieceAt(x, y) != null) {
            if (rightTurn(x, y)) {
                if (selectedPiece == null) {
                    return true;
                } else if (!hasMoved) {
                    return true;                  
                }  else {
                    return false;
                }
                
            } 
          else {
               return false;
          }
        } else {
            return false;
        }
    
    
    }
    
    
    public boolean canSelect(int x, int y) {
        if (selectCondition1(x, y) || selectCondition2(x, y)) {
            return true;
            
        } else {
            return false;
        }
                                            
    }
  //checks if there *is* a piece at (xf, yf) and if the piece belongs to the current player
    private boolean validCapture(int xf, int yf) {
        if (!isPiece(xf, yf)) {
            return false;            
        } else if (pieceAt(xf, yf).isFire() == firesTurn) {
            return false;
        } else {
            return true;
        }
    }
    
    
    
    private boolean validMove(int xi, int yi, int xf, int yf) { 
        Piece move_piece = pieceAt(xi, yi);
        
       
        if (this.isPiece(xf, yf)) { 
            return false;
        } else if (!this.inBounds(xf, yf)) {
            return false;
        }
        
        boolean fire = move_piece.isFire();
        boolean water = !move_piece.isFire();
        boolean fire_king = (move_piece.isFire() && move_piece.isKing());
        boolean water_king = (!move_piece.isFire() && move_piece.isKing());
       
        if ((yf == yi + 1) && ((xf == xi + 1) || (xf == xi - 1)) && (fire || water_king)) { //xf, yf is diagonal-up to xi, yi; no capture
            return true;
        }
        
     
        if ((yf == yi - 1) && ((xf == xi + 1) || (xf == xi - 1)) && (water || fire_king)) { //xf, yf is diagonal-down to xi, yi; no capture
            return true;
        }
        
        if (isCapturing(xi, yi, xf, yf)) {
            return true;
        }
  
        else {
            return false;
        }
        
    }
    
    private boolean isCapturing(int xi, int yi, int xf, int yf) {
        Piece move_piece = pieceAt(xi, yi);
        boolean fire = move_piece.isFire();
        boolean water = !fire;
        boolean fire_king = (fire && move_piece.isKing());
        boolean water_king = (water && move_piece.isKing());
        
        
        if (validCapture(xi + 1, yi + 1) && (xf == xi + 2 && yf == yi + 2) && (fire || water_king)) { //capture to the front right
            return true;
        } 
    
        if (validCapture(xi - 1, yi + 1) && (xf == xi - 2 && yf == yi + 2) && (fire || water_king)) { //capture to the front left
            return true;
        }
        if (validCapture(xi + 1, yi - 1) && (xf == xi + 2 && yf == yi - 2) && (water || fire_king)) { //capture to the back right
            return true;
        }
        if (validCapture(xi - 1, yi - 1) && (xf == xi - 2 && yf == yi - 2) && (water || fire_king)) { //capture to the back left
            return true;
        }
        else {
            return false;
        }
 
    }
    
    public void select(int x, int y) {
        Piece selection = this.pieceAt(x, y);
        if (selection != null) {
            selectedPiece = selection;
            selectedX = x;
            selectedY = y;
        } else {
            selectedPiece.move(x, y);
            selectedX = x;
            selectedY = y;
            this.hasMoved = true; 
            
            
        }
        
    }
    
    
    private void pieceIncrementerRemove(int x, int y) {
        if (pieceAt(x, y).isFire()) {
            numFire -= 1;
        } else {
            numWater -= 1;
        }
    }
    
    private void pieceIncrementerPlace(int x, int y) {
        if (pieceAt(x, y).isFire()) {
            numFire += 1;
        } else {
            numWater += 1;
        }
    }
    
    
    public void place(Piece p, int x, int y) {
        
        
        if (!inBounds(x, y) || p == null) {
            return;
        } else {
            pieces[x][y] = p;
            pieceIncrementerPlace(x, y);
        }
    }
    
    
    
    public Piece remove(int x, int y) {
        if (!inBounds(x, y)) {
            System.out.println("Piece position out of bounds");
            return null;
        } else {
            if (pieceAt(x, y) == null) {
                System.out.println("No piece to remove");
                return null;
            } else {
                pieceIncrementerRemove(x, y);
                Piece removedPiece = pieceAt(x, y);
                pieces[x][y] = null;
                return removedPiece;
            }
        }
    }
    
    public boolean canEndTurn(){
        if (hasMoved == true) {
            return true;
        } else {
            return false;
        }
    }
    
    public void endTurn() {
        selectedPiece.doneCapturing();
        firesTurn = !firesTurn;       
        selectedPiece = null;
        selectedX = -1;
        selectedY = -1;
        hasMoved = false;
    }
    
    public String winner() {
        if (numFire == 0 && numWater == 0) {
            return "No one";
        } else if (numFire == 0) {
            return "Water";           
        } else if (numWater == 0) {
            return "Fire";
        } else {
            return null;
        }
    }
    
   
    
    public static void main (String args[]) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while (b.winner() == null) {
            b.drawBoard(8);
            b.insertPics(8);
            
                                
            /* ERRORS OUT WITH slow-clicks of
             * fire: (2, 2) -> (3, 3)
             * water: (1, 5) -> (2, 4)
             * fire: (3, 3) -> (1, 5) <------------SLOW CLICK errors out, FAST CLICK is alright
             * what happens is the while loops runs super fast; if you hold onto the mouse click it'll loop thoruhg, go into
             * StdDrawPlus.mousePressed() again and then error out when you try to select
             * This is because endTurn hasn't been called -> hasMoved is still True -> select conditions error out
             */
            
        
                if (StdDrawPlus.mousePressed()) {                
                    int x = (int) StdDrawPlus.mouseX();
                    int y = (int) StdDrawPlus.mouseY();
                    if (b.canSelect(x, y)) {
                        b.select(x, y);        
                        b.insertPics(8);
                    }
                 
                }   
            
             
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {                    
                    b.endTurn();
 
               
                }
            }            
            StdDrawPlus.show(0);
            
        }
        
        System.out.println(b.winner());
        
        



    }
}
