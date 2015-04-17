
public class Board {
    private Piece[][] all_pieces = new Piece[8][8];
    private boolean current_player_is_fire = true;
    private boolean selected = false;
    private boolean moved = false;
    private boolean captured = false;
    private boolean exploded = false;
    private int selected_piece_x;
    private int selected_piece_y;
    private Piece selected_piece;
    private int num_fire = 0;
    private int num_water = 0;
    public static void main (String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int int_x = (int) x;
                int int_y = (int) y;
                if (board.canSelect(int_x,int_y)) {
                    board.select(int_x,int_y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                board.endTurn();
            }
            StdDrawPlus.show(10);
            if (board.num_fire == 0 || board.num_water ==0){
                board.winner();
            }
        }
    }
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (all_pieces[i][j] != null) {
                    drawpiece(i,j);
                }
            }
        }
        if (selected == true) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selected_piece_x + .5, selected_piece_y + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            drawpiece(selected_piece_x,selected_piece_y);
        }
    }
    
    private void drawpiece(int i, int j) {
        if (all_pieces[i][j].isFire()) {
            if (all_pieces[i][j].isBomb()) {
                if (all_pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
                else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            }
            else if (all_pieces[i][j].isShield()) {
                if (all_pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                }
                else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            }
            else {
                if (all_pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                }
                else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            }
        }
        else {
            if (all_pieces[i][j].isBomb()) {
                if (all_pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                }
                else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            }
            else if (all_pieces[i][j].isShield()) {
                if (all_pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                }
                else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            }
            else {
                if (all_pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                }
                else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            }
        }
    }
    
    public Board(boolean shouldBeEmpty) {
        int i = 0;
        int j = 0;
        if (shouldBeEmpty) {            
        }
        else {
            //initialize fire pieces
            for (i=0, j=0; i<8; i=i+2) {
                Piece p = new Piece(true,this,i,j,"pawn");
                place(p,i,j);
            }
            for (i=1, j=1; i<8; i=i+2) {
                Piece p = new Piece(true,this,i,j,"shield");
                place(p,i,j);
            }
            for (i=0, j=2; i<8; i=i+2) {
                Piece p = new Piece(true,this,i,j,"bomb");
                place(p,i,j);
            }
            //initialize water pieces
            for (i=1, j=7; i<8; i=i+2) {
                Piece p = new Piece(false,this,i,j,"pawn");
                place(p,i,j);
            }
            for (i=0, j=6; i<8; i=i+2) {
                Piece p = new Piece(false,this,i,j,"shield");
                place(p,i,j);
            }
            for (i=1, j=5; i<8; i=i+2) {
                Piece p = new Piece(false,this,i,j,"bomb");
                place(p,i,j);
            }
        }
    }
    
    
    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7)) {
            return null;
        }
        if (all_pieces[x][y] != null) {
            return all_pieces[x][y];
        }
        return null;
    }
    
    public boolean canSelect(int x, int y) {
        Piece selectxy = pieceAt(x,y);
        // if an piece is at (x,y)
        if (exploded) return false;
        if ((selectxy != null)) {
            if (selected == true) {
                if (moved == true) return false;
                else {
                    // check the same player's piece is being selected, if it's true, the selection is valid.
                    if (current_player_is_fire == selectxy.isFire()) return true;
                    else return false;
                }
            }
            else {
                if (current_player_is_fire == selectxy.isFire()) return true;
                else return false;
            }
        }
        // if it's an empty space at (x,y)
        else {
            if (selected == true) {
                if (moved == true) {
                    if (selected_piece.hasCaptured() == true) {
                        // bug here. after a capture the piece can still move
                        // check if it's another capture. |dx| > 1 mean another capture
                        int dx = x - selected_piece_x;
                        if (validMove(selected_piece_x,selected_piece_y,x,y) && Math.abs(dx)!=1) return true;
                        else return false;
                    }
                    else return false;
                }
                else {
                    if (validMove(selected_piece_x,selected_piece_y,x,y)) return true;
                    else return false;
                }
            }
        }
        return false;
    }
    
    private boolean validMove(int xi, int yi, int xf,int yf) {
        int dx = xf - xi;
        int dy = yf - yi;
        // check if the same spot is selected
        if (dx==0 || dy==0 || Math.abs(dx)>2 || Math.abs(dy)>2) return false;
        // check if dx==dy. If not, it's not diagonal move, which is invalid.
        if (Math.abs(dx) != Math.abs(dy)) return false;
        
        // check if the piece is king. And the move is valid for both up and down if it is king.
        if (selected_piece.isFire()==true && selected_piece.isKing()==false && dy<0)  return false;
        if (selected_piece.isFire()==false && selected_piece.isKing()==false && dy>0) return false;
            // if |dx| and |dy| is 1, the selected space must be empty in order to make the move valid.
        if (Math.abs(dx) == 1) {
            if (all_pieces[xf][yf] == null) {
                return true;
            }
            else return false;
        }
        
        // if dx or dy is greater than 1, all pieces between the final position and initial position must be the other side.
        else {
            int unit_dx = dx / Math.abs(dx);
            int unit_dy = dy / Math.abs(dy);
            int i;
            int j;  
            for (i=xi+unit_dx, j=yi+unit_dy; i!=xf; i=i+unit_dx, j=j+unit_dy ) {
                if (all_pieces[i][j] == null || all_pieces[xi][yi].isFire() == all_pieces[i][j].isFire()) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public void place(Piece p, int x, int y) {
        // if (x,y) is out of bound or p is null
        if (x>7 || y>7 || x<0 || y<0 || p==null);
        else {
            for (int i=0; i<8; i=i+1) {
                for (int j=0; j<8; j=j+1) {
                    if (all_pieces[i][j] == p) {
                        remove(i,j);
                    }
                }
            }
            all_pieces[x][y] = p;
            if (p.isFire()) num_fire = num_fire + 1;
            else num_water = num_water + 1;
        }
    }
    
    public void select(int x, int y) {
        int dx = x - selected_piece_x;
        //int dy = y - selected_piece_y;
        if (selected==true) {
            // select another piece
            if (all_pieces[x][y]!=null && selected_piece.isFire() == all_pieces[x][y].isFire()) {
                selected_piece_x = x;
                selected_piece_y = y;
                selected_piece = all_pieces[x][y];
            }
            // moving to an empty space
            else if (Math.abs(dx) == 1) {
                captured = false;
                selected_piece.doneCapturing();
                moved = true;
                selected_piece.move(x,y);
                selected_piece_x = x;
                selected_piece_y = y;
            }
            // dx > 0 => capture
            else {
                if (selected_piece.isBomb()) {
                    selected = false;
                    exploded = true;
                }
                selected_piece.move(x, y);
                moved = true;
                selected_piece_x = x;
                selected_piece_y = y;
                captured = true;
            }
            
        }
        //if nothing is selected
        else {
            // your selected_piece xy must set to something else so that the draw function will not try to draw it.
            selected = true;
            selected_piece_x = x;
            selected_piece_y = y;
            selected_piece = all_pieces[x][y];            
        }
    }
    
    public Piece remove(int x, int y) {
         if (x>7 || y>7 || x<0 || y<0) {
             System.out.println("x,y value(s) out of bound");
             return null;
         }
         else {
             if (all_pieces[x][y] == null) {
                 System.out.println("no piece at (x,y)");
                 return null;
             }
             else {
                 Piece removed = all_pieces[x][y];
                 if (all_pieces[x][y].isFire() == true) {
                     num_fire = num_fire - 1;
                 }
                 else {
                     num_water = num_water - 1;
                 }
                 all_pieces[x][y] = null;
                 return removed;
             }
         }
    }
    
    public boolean canEndTurn() {
        if (moved == true) return true;
        else return false;
    }
    
    public void endTurn() {
        if (canEndTurn()) {
            current_player_is_fire = !(current_player_is_fire);
            selected = false;
            moved = false;
            captured = false;
            selected_piece.doneCapturing();
            exploded = false;
            
        }
        else {
            System.out.println("you need to make a move before you can end the turn");
        }
    }
    
    public String winner() {
        if (num_fire > num_water && num_water == 0) return "Fire";
        else if (num_fire == 0 && num_water == 0) return "No one";
        else if (num_water > num_fire && num_fire == 0) return "Water";
        else return null;
    }
}