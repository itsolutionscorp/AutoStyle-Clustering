public class Board {
   private boolean shouldBeEmpty;
    private Piece[][] pieces;
    private int N;
    private boolean playerTurn = true;
    private boolean isSelected = false;
    private boolean isMoved = false;
    private int xSelect;
    private int ySelect;


    /** This intializes a new board, if shouldBeEmpty is true,
    this means the Board has no pieces, if it is false,
    then the board has pieces on it.
    */
   public Board(boolean shouldBeEmpty) {
       this.shouldBeEmpty = shouldBeEmpty;
       N = 8;
       pieces = new Piece[N][N];
       if(this.shouldBeEmpty == false) {
           for (int i = 0; i < N; i++) {
               for (int j = 0; j < N; j++) {
                   if((i + j) % 2 == 0) {
                       if (j == 0) {
                           pieces[i][j] = new Piece(true, this, i, j, "pawn");
                       }
                       if (j == 1) {
                           pieces[i][j] = new Piece(true, this, i, j, "shield");
                       }
                       if (j == 2) {
                           pieces[i][j] = new Piece(true, this, i, j, "bomb");
                       }
                       if (j == 5) {
                           pieces[i][j] = new Piece(false, this, i, j, "bomb");
                       }
                       if (j == 6) {
                           pieces[i][j] = new Piece(false, this, i, j,"shield");
                       }
                       if (j == 7) {
                           pieces[i][j] = new Piece(false, this, i, j, "pawn");
                       }
                   }    
               } 
           }
       }
   }


   /** This method takes as parameters two integers (x,y)
   If this position is out of bounds or null, then it returns null.
   If not, it returns the piece at that postion. */
   public Piece pieceAt(int x, int y) {
        if (x > N - 1 || y > N - 1|| x < 0 || y < 0) {
           return null;
        }  
        else if (pieces[x][y] == null) {
           return null;
        } else {
           return pieces[x][y];
        }    
   }
   
   public void place(Piece p, int x, int y) {
        if (x > N - 1 || y > N - 1|| x < 0 || y < 0) {
           return;
        } 
        if (p == null) {
           return;
        } else {
           for (int i = 0; i < N; i++) {
               for(int j = 0; j < N; j++) {
                   if(pieces[i][j] == p) {
                       pieces[i][j] = null;
                   }
               }
           }
           pieces[x][y] = p;
        }
    }

   public boolean canSelect(int x, int y) {
        if(x < 0 || y < 0 || x > N -1 || y > N -1) {
            return false;
        }       
        if(pieces[x][y] != null) {
           if (playerTurn == pieces[x][y].isFire()) {
               if ((isSelected == false) || ((isSelected == true) && isMoved)) {
                   return true;
                }
               return false;
            }
        } else {
           if (isSelected && isMoved && validMove(xSelect, ySelect, x,y)) {
                   return true;
           } else if (isSelected && pieces[xSelect][ySelect].hasCaptured() && validMove(xSelect,ySelect,x,y)) {
               return true;
           }
            return false;
        }
        return false;
    }

    public void select(int x, int y) {
       if (pieces[x][y] != null) {
           xSelect = x;
           ySelect = y;
           isSelected = true;
       } else {
           pieces[x][y] = pieces[xSelect][ySelect];
       }
    }

    public Piece remove(int x, int y) {
       if (x > N - 1 || y > N - 1|| x < 0 || y < 0) {
           System.out.println("Out of bounds!");
           return null;
        } else if (pieces[x][y] == null) {
           System.out.println("There is no piece!");
           return null;
        } else {
           Piece removedPiece = pieces[x][y];
           pieces[x][y] = null;
           return removedPiece;
        }
    }

    public boolean canEndTurn() {
        if (isMoved || pieces[xSelect][ySelect].hasCaptured()) {
           return true;
        } else {
           return false;
        }
    }

    public void endTurn() {
        isSelected = true;
        isMoved = true;
        if (pieces[xSelect][ySelect].isFire()){
            playerTurn = false;
          
        } else {
           playerTurn = true;
        }
    }

    public String winner() {
       int fireCounter = 0;
       int waterCounter = 0;
       for(int i = 0; i < N; i++) {
           for (int j = 0;j < N; j++) {
               if (pieces[i][j] != null) {
                   if(pieces[i][j].isFire()) {
                       fireCounter += 1;
                   } else {
                       waterCounter += 1;
                   }
               }
           }
       }
       if ((fireCounter > 0) && (waterCounter == 0)) {
           return "Fire";
       } else if ((waterCounter > 0) &&  (fireCounter == 0)) {
           return "Water";

       } else if (waterCounter == 0 && fireCounter == 0){
           return "No one";
        } else {
           return null;
        }
    }


   private boolean validMove(int xi, int yi, int xf, int yf) {
       if (xi > N || xi < 0 || yi > N || yi < 0 || xf > N 
           || xf < 0 || yf > N || yf < 0) {
           return false; 
       }
       else if (pieces[xi][yi] != null && pieces[xf][yf] == null) {
           if(pieces[xi][yi].isFire()) {
               if (isValidUpMove(xi,yi,xf,yf)) {
                   return true;
               } 
               if ((isValidUpCapture(xi,yi,xf,yf) && (pieces[xf-1][yf-1].isFire() == false))
                          || (isValidUpCapture(xi,yi,xf,yf) && pieces[xf+1][yf-1].isFire() == false))
                   return true;
               if (pieces[xi][yi].isKing()) {
                   if (isValidUpMove(xi,yi,xf,yf)) {
                       return true;
                   }
                   if (isValidDownMove(xi,yi,xf,yf)) {
                       return true;
                   }
                   if ((isValidUpCapture(xi,yi,xf,yf) && (pieces[xf-1][yf-1].isFire() == false))
                          || (isValidUpCapture(xi,yi,xf,yf) && pieces[xf+1][yf-1].isFire() == false))
                       return true;
                   if ((isValidDownCapture(xi,yi,xf,yf) && (pieces[xi +1][yi-1].isFire() == false)) || (isValidDownCapture(xi,yi,xf,yf) &&
                              (pieces[xi-1][yi-1].isFire() == false)))
                       return true;
                   }
                   return false;
               } else {
                   if(isValidDownMove(xi,yi,xf,yf)) {
                       return true;
                   }
                   if ((isValidDownCapture(xi,yi,xf,yf) && (pieces[xi +1][yi-1].isFire())) || (isValidDownCapture(xi,yi,xf,yf) &&
                              (pieces[xi-1][yi-1].isFire()))) {
                       return true;
                   }
                   if (pieces[xi][yi].isKing()) {
                       if (isValidUpMove(xi,yi,xf,yf)) {
                           return true;
                       }
                       if (isValidDownMove(xi,yi,xf,yf)) {
                           return true;
                       }
                       if ((isValidUpCapture(xi,yi,xf,yf) && (pieces[xf-1][yf-1].isFire()))
                          || (isValidUpCapture(xi,yi,xf,yf) && pieces[xf+1][yf-1].isFire())) {
                           return true;
                       }
                       if ((isValidDownCapture(xi,yi,xf,yf) && (pieces[xi +1][yi-1].isFire())) || (isValidDownCapture(xi,yi,xf,yf) &&
                              (pieces[xi-1][yi-1].isFire()))) {
                           return true;
                       }
                       return false;
                   }
               }
       }
       return false;
   }       

    private boolean isValidUpMove(int xi, int yi, int xf, int yf) {
   if ((xf - xi == 1) && (yf - yi == 1)) {
       return true;
   } 
    if ((yf - yi == -1) && (xf - xi == 1)) { 
           return true;
   } 
    
    return false;
    }

// take care of simple 
    private boolean isValidDownMove(int xi, int yi, int xf, int yf) {
   if ((xi - xf == 1) && (yi - yf == 1)) {
       return true;
   }
   if ((xf - xi == 1) && (yf- yi == -1)) {
       return true;
   }
   return false;     
    }

   private boolean isValidDownCapture(int xi, int yi, int xf, int yf) {
       if (xi == 0) {
           if(pieces[xi +1][yi-1] != null && xi - xf == -2 && yi - yf == 2) {
               return true;
       } else if (xi == 7) {
           if(pieces[xi-1][yi-1] != null && xi - xf == 2 && yi - yf == 2) {
               return true;
           } else {
               if ((pieces[xi-1][yi-1] != null) && (xi - xf == 2) && (yi - yf ==2)) {
                   return true;
               }
               if ((pieces[xi+1][yi-1] != null) && (xf - xi == 2) && (yi - yf == 2)) {
                   return true;
               }
           }     
       }
       }
       return false;
   }


   private boolean isValidUpCapture (int xi, int yi, int xf, int yf) {
       if (xi == 0) {
           if(pieces[xf - 1][yf-1] != null && yf - yi == 2 && xf - xi == 2) {
               return true;
           } else if (xi == 7) {
               if(pieces[xf+1][yf-1] != null && yf - yi == 2 && xf - xi == -2) {
                   return true;
           }
           } else {
               if(pieces[xf - 1][yf-1] != null && yf - yi == 2 && xf - xi == 2) {
                   return true;
               }    
               if(pieces[xf+1][yf-1] != null && yf - yi == 2 && xf - xi == -2) {
                   return true; 
               }
           }
       }
       return false;
   }
       



    
   // This method draws an empty board without pieces. 
   private void drawBoard(int N) {
        if (this.shouldBeEmpty) {
           for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
           }
           }
        }
        else {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);   
                if((i + j) % 2 == 0) {
                    if (pieces[i][j] != null) {
                       if (pieces[i][j].isFire()) {
                           if(pieces[i][j].isKing()) {
                               if (pieces[i][j].isBomb()) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png",1,1);
                               } else if(pieces[i][j].isKing() && pieces[i][j].isShield()) {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png",1,1);
                               } else {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png",1,1);
                               }
                           } else {
                               if (pieces[i][j].isBomb()) {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png",1,1);
                                } else if (pieces[i][j].isShield()) {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",1,1);
                                } else {
                                   StdDrawPlus.picture(i + .5, j+ .5, "img/pawn-fire.png",1,1);
                                }
                            }
                        } 
                        else {
                           if(pieces[i][j].isKing()) {
                               if (pieces[i][j].isBomb()){
                                   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png",1,1);
                               } else if (pieces[i][j].isShield()) {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png",1,1);
                               } else {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png",1,1);
                               }
                           }    
                            else {
                               if (pieces[i][j].isBomb()){
                                   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",1,1);
                               } else if (pieces[i][j].isShield()) {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png",1,1);
                               } else {
                                   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png",1,1);
                               }
                            }
                        }
                    }
                }
           }
        }
    }
    }    
    
       public static void main(String[] args) {
        Board mainBoard = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            mainBoard.drawBoard(N);

            if (StdDrawPlus.mousePressed()) {
                double xva =  StdDrawPlus.mouseX();
                double yva =  StdDrawPlus.mouseY();
                int xval = (int)xva;
                int yval = (int)yva;
                if (mainBoard.canSelect(xval, yval)) {
                    mainBoard.select(xval, yval);
                    StdDrawPlus.filledSquare(xval + .5, yval + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
            if(StdDrawPlus.isSpacePressed()) {
                if(mainBoard.canEndTurn()) {
                    mainBoard.endTurn();
                }
            }

            StdDrawPlus.show(100);
         mainBoard.winner();        
        }
    }
}
   

