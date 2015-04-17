public class Board {
   private int N = 8;
   private Piece[][] location = new Piece[this.N][this.N];
   private boolean[][] pieces = new boolean[this.N][this.N];
   private boolean moved = false; 
   private Piece previous = null;
   private int[] prevCords = {this.N, this.N};
   private int turn = 0; 
   
   public static void main(String[] args) {
     Board start = new Board(false);
     StdDrawPlus.setXscale(0, start.N);
     StdDrawPlus.setYscale(0, start.N);
     start.drawBoard();
     while (start.winner() == null) {
       start.drawBoard();
       start.drawPieces();
       if (StdDrawPlus.mousePressed()) {
          double x = StdDrawPlus.mouseX();
          double y = StdDrawPlus.mouseY();
          if (start.canSelect((int) x, (int) y)) start.select((int) x, (int) y);
       }
       if (StdDrawPlus.isSpacePressed()) if (start.canEndTurn()) start.endTurn();
      }
   System.out.println(start.winner());
   }
   
   private void drawBoard(){
     for (int i = 0; i < this.N; i++) {
       for (int j = 0; j < this.N; j++) {
         if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
         else StdDrawPlus.setPenColor(StdDrawPlus.RED);
         StdDrawPlus.filledSquare(i + .5, j + .5, .5);
       }
     }
   }

   private void initialState() {
     for (int i = 0; i < this.N; i++) {
       if (i % 2 == 0) {
          this.location[i][0] = new Piece(true, this, i, 0, "pawn");
          this.pieces[i][0] = true;
          this.location[i][2] = new Piece(true, this, i, 2, "bomb");
          this.pieces[i][2] = true;
          this.location[i][this.N-2] = new Piece(false, this, i, this.N-2, "shield");
          this.pieces[i][this.N-2] = true;
       }
       else {
          this.location[i][1] = new Piece(true, this, i, 1, "shield");
          this.pieces[i][1] = true;
          this.location[i][this.N-1] = new Piece(false, this, i, this.N-1, "pawn");
          this.pieces[i][this.N-1] = true;
          this.location[i][this.N-3] = new Piece(false, this, i, this.N-3, "bomb");
          this.pieces[i][this.N-3] = true;
       }
     }
   }

   private void drawPieces() {
     for (int i=0; i < this.N; i++) {
       for (int j = 0; j<this.N; j++) {
          if (this.pieces[i][j]) {
             Piece one = this.pieceAt(i,j);
             if (one.isFire()) {
               if (one.isKing()) {
                 if (one.isShield()) StdDrawPlus.picture(i+.5,j+.5, "img/shield-fire-crowned.png", 1, 1);         
                 else {
                   if (one.isBomb()) StdDrawPlus.picture(i+.5,j+.5, "img/bomb-fire-crowned.png", 1, 1);   
                   else StdDrawPlus.picture(i+.5,j+.5, "img/pawn-fire-crowned.png", 1, 1);         
                 }
               }
               else {
                  if (one.isShield()) StdDrawPlus.picture(i+.5,j+.5, "img/shield-fire.png", 1, 1);
                  else {
                    if (one.isBomb()) StdDrawPlus.picture(i+.5,j+.5, "img/bomb-fire.png", 1, 1);
                    else StdDrawPlus.picture(i+.5, j+ .5, "img/pawn-fire.png", 1, 1);
                  }
               }
             }
             else {
               if (one.isKing()) {
                  if (one.isShield()) StdDrawPlus.picture(i+.5,j+.5, "img/shield-water-crowned.png", 1, 1);         
                  else {
                    if (one.isBomb()) StdDrawPlus.picture(i+.5,j+.5, "img/bomb-water-crowned.png", 1, 1);   
                    else StdDrawPlus.picture(i+.5,j+.5, "img/pawn-water-crowned.png", 1, 1);         
                  }
               }
               else {
                 if (one.isShield()) StdDrawPlus.picture(i+.5,j+.5, "img/shield-water.png", 1, 1);         
                 else {
                   if (one.isBomb()) StdDrawPlus.picture(i+.5,j+.5, "img/bomb-water.png", 1, 1);         
                   else StdDrawPlus.picture(i+.5,j+.5, "img/pawn-water.png", 1, 1);         
                 }
               }  
             }  
          }
        }
      }
   StdDrawPlus.show(10);
   }

   public Board(boolean shouldBeEmpty) {
     if (!shouldBeEmpty) initialState();
   }

   public Piece pieceAt(int x, int y) {
      if (x > this.N-1 || x < 0 || y > this.N-1 || y < 0) {
         return null;
      }
      else {
        if (this.pieces[x][y]) return this.location[x][y];
        else return null;
      }
   }

   private boolean validMove(int xi, int yi, int xf, int yf) {
      if (this.pieces[xi][yi] && !(this.pieces[xf][yf])) {
         if (this.pieceAt(xi, yi).isKing()) {
            if (!this.moved && ((xi+1==xf&&(yi+1==yf || yi -1==yf)) || (xi-1==xf&&(yi+1==yf || yi-1==yf)))) return true;
            if ((xi < this.N-1)&&(yi<this.N-1)&&(yi >= 0)&& (xi >= 0))
               if (this.pieces[xi+1][yi+1]) if (xi+2==xf&&yi+2==yf&&this.pieceAt(xi+1,yi+1).side() != this.pieceAt(xi,yi).side()) return true; 
            if ((xi < this.N-1)&&(yi<this.N)&&(yi >= 1)&& (xi >= 0))
               if (this.pieces[xi+1][yi-1]) if (xi+2==xf&&yi-2==yf&&this.pieceAt(xi+1,yi-1).side() != this.pieceAt(xi,yi).side()) return true; 
            if ((xi < this.N)&&(yi<this.N-1)&&(yi >= 0)&& (xi >= 1))
               if (this.pieces[xi-1][yi+1]) if (xi-2==xf&&yi+2==yf&&this.pieceAt(xi-1,yi+1).side() != this.pieceAt(xi,yi).side()) return true; 
            if ((xi < this.N)&&(yi<this.N)&&(yi >= 1)&& (xi >= 1))
               if (this.pieces[xi-1][yi-1]) if (xi-2==xf&&yi-2==yf&&this.pieceAt(xi-1,yi-1).side() != this.pieceAt(xi,yi).side()) return true;
            else return false; 
         }
         if (this.pieceAt(xi, yi).side() == 0) {
            if (!this.moved && ((xi + 1 == xf && yi + 1 == yf) || (xi - 1 == xf && yi + 1 == yf))) return true;
            if ((xi < this.N-1)&&(yi<this.N-1)&&(yi >= 0)&& (xi >= 0))
               if (this.pieces[xi+1][yi+1]) if (xi + 2 == xf && yi + 2 == yf && this.pieceAt(xi+1, yi+1).side() != this.pieceAt(xi,yi).side()) return true; 
            if ((xi < this.N)&&(yi<this.N-1)&&(yi >= 0)&& (xi >= 1))
               if (this.pieces[xi-1][yi+1]) if (xi - 2 == xf && yi + 2 == yf && this.pieceAt(xi-1, yi+1).side() != this.pieceAt(xi,yi).side()) return true;
            else return false;
         }
         else {
            if (!this.moved && ((xi + 1 == xf && yi - 1 == yf) || (xi - 1 == xf && yi - 1 == yf))) return true;
            if ((xi < this.N-1)&&(yi<this.N)&&(yi >= 1)&& (xi >= 0)) 
               if (this.pieces[xi+1][yi-1]) if (xi + 2 == xf && yi - 2 == yf && this.pieceAt(xi+1, yi-1).side() != this.pieceAt(xi, yi).side()) return true; 
            if ((xi < this.N)&&(yi<this.N)&&(yi >= 1)&& (xi >= 1))
               if (this.pieces[xi-1][yi-1]) if (xi - 2 == xf && yi - 2 == yf && this.pieceAt(xi-1, yi-1).side() != this.pieceAt(xi, yi).side()) return true;
            else return false;   
         }          
      }
      return false;
   }

   public boolean canSelect(int x, int y) {
      if ((x < this.N)&&(y<this.N)&&(y >= 0)&& (x >= 0)) {
         if (this.pieces[x][y]) {
            Piece one = this.location[x][y];
            if (one.side() == this.turn) {
               if (!this.moved) return true;
               else return false;
            }
            else return false;
         }
         else {
            if (this.previous != null) {
               if (this.validMove(this.prevCords[0], this.prevCords[1], x, y))
                  return true; 
               else return false;
         }
         else return false;
         }
      }
      else return false;
   }
   
   public void select (int x, int y) {
      if (this.pieces[x][y]) {
         this.previous = this.location[x][y];
         this.prevCords[0] = x;
         this.prevCords[1] = y;
      }
      else {
         if (this.previous != null) {
            this.previous.move(x, y);
            this.moved = true;
            if (this.location[x][y] != null) {
            this.prevCords[0] = x;
            this.prevCords[1] = y;
            this.previous = this.location[x][y];
            }
         }
      }
   }

   public void place (Piece p, int x, int y) {
     if (x>=0&&y>=0&&x<this.N&&y<this.N&&p!=null) { 
         if (this.pieces[x][y]) this.remove(x,y);
         this.location[x][y] = p;
         this.pieces[x][y] = true;
         p = null;
      }
   }

   public Piece remove(int x, int y) {
     if (x<0||y<0||x>=this.N||y>=this.N) { 
        System.out.println("Out of Bounds");
        return null;
     }
     if (this.pieces[x][y]) {
         this.pieces[x][y] = false;
         Piece one = this.location[x][y];
         this.location[x][y] = null;
         return one;
      }
      else {
         System.out.println("No Piece Here");
         return null;
      }
   }

   public boolean canEndTurn() {
      if (this.moved) return true;
      if (this.previous != null) {
         if (this.previous.hasCaptured()) return true;
         else return false;
      }
      else return false;
   }

   public void endTurn() {
      this.turn = this.turn ^ 1;
      this.previous.doneCapturing();
      this.previous = null;
      this.moved = false;
   } 

   public String winner() {
      int countFire = 0;
      int countWater = 0;
      for (int i =0; i < this.N; i++) {
         for (int j=0; j < this.N; j++) {
            if (this.pieces[i][j]) {
               Piece one = this.pieceAt(i, j);
               if (one.isFire()) countFire += 1;
               else countWater += 1;
            }
         }
      }
      if (countFire == 0 && countWater == 0) return "No one";
      if (countFire == 0) return "Water";
      if (countWater == 0) return "Fire";
      return null;
   } 
}
