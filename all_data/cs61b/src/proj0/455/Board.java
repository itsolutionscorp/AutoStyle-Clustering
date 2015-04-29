import java.util.Arrays;
public class Board {   
private Piece[][] pieces;
private boolean activeplayer;
private Piece selectedpiece;
private int selectedx;
private int selectedy;
private boolean canEndTurn;
 
    public Board(boolean shouldBeEmpty) {
        activeplayer = true;
        canEndTurn = false;
        if (shouldBeEmpty == true) {
            pieces = new Piece[8][8];
        }
        else {
           int N = 8;
           pieces = new Piece[N][N];
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < N; j++) {
                     if ((i + j) % 2 == 0) {
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
                             pieces[i][j] = new Piece(false, this, i, j, "shield");
                         }
                         if (j == 7) {
                             pieces[i][j] = new Piece(false, this, i, j, "pawn");
                         }
                     }
                 }           
             }
        }
    }
    
    private String getpic(Piece p) {
        String result = "";
        if (p.isFire() == true) {
            if ((p.isBomb() == false) && (p.isShield() == false)) {
                result = "img/pawn-fire";
            }
            if (p.isBomb() == true) {
                result = "img/bomb-fire";
            }
            if (p.isShield() == true) {
                result = "img/shield-fire";
            }
        }
        
        else {
            if ((p.isBomb() == false) && (p.isShield() == false)) {
                result = "img/pawn-water";
            }
            if (p.isBomb() == true) {
                result = "img/bomb-water";
            }
            if (p.isShield() == true) {
                result = "img/shield-water";
            }
        }
        if(p.isKing() == true) {
            result += "-crowned.png";
        } else{
            result +=".png";
        }
        return result;
    }
    
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        System.out.print(Arrays.deepToString(b.pieces));
        while (true) {
            b.drawBoard(N);  
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.canSelect((int) x, (int) y);
                if (b.canSelect((int) x, (int) y) == true) {
                    b.select((int) x, (int) y);
                }
            }
            
            if (StdDrawPlus.isNPressed()) {
                System.out.print("Play " + b.activeplayer + "----");
                System.out.println("CET? " + b.canEndTurn);
                
            }
            
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn() == true) {
                    b.endTurn();
                }
            }
            
            StdDrawPlus.show(10);
        }
        
    }
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);                 
            }
        }
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                Piece currentpiece = this.pieces[k][l];
                if (currentpiece != null) {
                    StdDrawPlus.picture(k + 0.5, l + 0.5, this.getpic(currentpiece), 1, 1);
                }
                if (this.selectedpiece != null) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.square(selectedx + 0.5, selectedy + 0.5, 0.5);
                }
                
            }
        }
        
    }
    
    public Piece pieceAt(int x, int y) {
        if (x < 0 || x > 8 || y < 0 || y > 8) {
            return null;
        }
        if (pieces[x][y] != null) {
            return pieces[x][y];
        }
        else {
            return null;
        }
        
    }
    
    public void place(Piece p, int x, int y) {
        if (p == null) {
            return;
        }
        if (x < 0 || x > 8 || y < 0 || y > 8) {
            return;
        } 
        if (this.pieces != null) {
            this.pieces[x][y] = p;
        }
        else {
            
        }
    }
    
    public boolean canSelect(int x, int y) { 
       if (x > 7 || x < 0 || y > 7 || y < 0) {
         return false;
        }
        if ((pieceAt(x, y)) != null) {
                if ((this.selectedpiece == null) || 
                   ((this.selectedpiece != null) && (this.canEndTurn() == false))) {
                    if (this.pieceAt(x, y).isFire() == this.activeplayer) {
                        return true;
                    }
                }
            } 
            else {
               if (((this.selectedpiece != null) &&
                       (this.canEndTurn()) == false) && 
                           (this.validmove(selectedx, selectedy, x, y) == true) ||
                  ((this.selectedpiece != null) &&
                       (this.selectedpiece.hasCaptured() == true) &&
                           ((this.validmove(selectedx, selectedy, x, y) == true) && ((x - selectedx == 2) || (x - selectedx == -2))))) {
                               if (this.selectedpiece.isFire() == activeplayer) {
                                   return true;
                               }
                       }
               
            }
            return false;
            
        }

    private boolean validmove(int xi, int yi, int xf, int yf) {
        
        // Checks for Fire pieces
        if (activeplayer == true) {
            if ((yf - yi == 1) && ((xf - xi == 1) || (xf - xi == -1))) {
                return true;
            }
            if ((((yf - yi == -1) && ((xf - xi == 1) || (xf - xi == -1))) && (pieceAt(xi ,yi).isKing() == true))) {
                return true;
            }
            //Capturing Up
            if ((yf - yi) == 2) {
                if ((xf - xi == 2)) {
                    if ((pieceAt(xi + 1, yi + 1)) != null) {  
                        if ((pieceAt(xi + 1, yi + 1)).isFire() == false) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                if ((xf - xi == -2)) {
                    if ((pieceAt(xi - 1, yi + 1)) != null) {
                        if ((pieceAt(xi - 1, yi + 1)).isFire() == false) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
            //Capturing Down
            if ((yf - yi) == -2) {
                if (pieceAt(xi, yi).isKing()) {
                    if ((xf - xi == 2)) {
                        if ((pieceAt(xi + 1, yi - 1)).isFire() == false) {
                            return true;
                        } else {
                            return false;
                        }
                     }
                     if ((xf - xi == -2)) {
                         if ((pieceAt(xi - 1, yi - 1)).isFire() == false) {
                             return true;
                         } else {
                             return false;
                         }
                     }  
                } else {
                    return false;
                }
            }   
        }
        
        if (activeplayer == false) {
            if ((yf - yi == -1) && ((xf - xi == 1) || (xf - xi == -1))) {
                return true;
            }
            if ((((yf - yi == 1) && ((xf - xi == 1) || (xf - xi == -1))) && (pieceAt(xi ,yi).isKing() == true))) {
                return true;
            }
            
          //Capturing Down
            if ((yf - yi) == -2) {
                if ((xf - xi == 2)) {
                   if ((pieceAt(xi + 1, yi - 1)) != null) { 
                       if ((pieceAt(xi + 1, yi - 1)).isFire() == true) {
                           return true;
                       } else {
                           return false;
                       }
                   }
                }
                if ((xf - xi == -2)) {
                    if ((pieceAt(xi - 1, yi - 1)) != null) {
                        if ((pieceAt(xi - 1, yi - 1)).isFire() == true) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
            //Capturing Up
            if ((yf - yi) == 2) {
                if (pieceAt(xi, yi).isKing()) {
                    if ((xf - xi == 2)) {
                        if ((pieceAt(xi + 1, yi + 1)).isFire() == true) {
                            return true;
                        } else {
                            return false;
                        }
                     }
                     if ((xf - xi == -2)) {
                         if ((pieceAt(xi - 1, yi + 1)).isFire() == true) {
                             return true;
                         } else {
                             return false;
                         }
                     }  
                } else {
                    return false;
                }
            }   
        }
        
        if (xf < 0 || xf > 8 || yf < 0 || yf > 8) {
            return false;
        }
        return false;
    }
        
    public void select(int x, int y) {
        if ((this.pieceAt(x, y)) != null) {
           selectedpiece = this.pieceAt(x, y);
           selectedx = x;
           selectedy = y;
        } else {
            if (selectedpiece != null) {
                selectedpiece.move(x, y);
                if (selectedpiece.hasCaptured() == true) {
                    this.canEndTurn = true;
                }
                this.canEndTurn = true;
                
            } else {
                return;
            }
        }
    }
      
    public Piece remove(int x, int y) {
        if (x < 0 || x > 8 || y < 0 || y > 8) {
            System.out.println("Coordinates out of bounds!");
            return null;
        }
        if (this.pieceAt(x, y) == null) {
            System.out.println("No piece located at coordinates" + "(" + Integer.toString(selectedx) + ", " + Integer.toString(selectedy) + ")");
            return null;
        }
        if (this.pieceAt(x, y) != null) {
            Piece a = this.pieceAt(x, y);
            this.pieces[x][y] = null;
            return a;
        }
        return null;
    }
    
    public boolean canEndTurn() {
        return this.canEndTurn;
    }
    
    public void endTurn() {
      selectedpiece.doneCapturing();
      selectedpiece = null;
      this.canEndTurn = false;
      playerswitch();
    }
    
    private void playerswitch() {
        if (this.activeplayer == true) {
            this.activeplayer = false;
        } else {
            this.activeplayer = true;
        }
        
    }
    
    public String winner() {
        if (this.pieces == null) {
            return "No one";
        }
        int firecount = 0;
        int watercount = 0;
        
        /*Two lines of code below iterates through two-dimensional array. Inspiration from stackoverflow*/
        
        for (int i = 0; i < this.pieces.length; i++) {
            for (int j = 0; j < this.pieces[i].length; j++) {
                if ((this.pieceAt(i, j)) != null) {
                   if ((this.pieces[i][j].side()) == 0) {
                       firecount += 1;
                   }
                   if ((this.pieces[i][j].side()) == 1) {
                       watercount += 1;
                   } 
                }  
            }
        }
        if ((watercount == 0) && (firecount > 0)) {
            return "Fire";
        }
        if ((firecount == 0) && (watercount > 0)) {
            return "Water";
        }
        if ((firecount == 0) && (watercount == 0)) {
            return "No one";
        }
        if ((firecount > 0) && (watercount > 0)) {
            return null;
        } else {
            return ""; 
        }
        
    }
    
    
    
    
    
    
}