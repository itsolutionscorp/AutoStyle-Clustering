public class Board {
    private boolean shouldBeEmpty;
    private Piece[][] pieces; 
    private int N;
    
    private int turn;
    
    private Piece select1;
    private int[] select1xy;

    
    private boolean moved;
    private boolean captured;
    

    public Board(boolean shouldBeEmpty) {
        /*this.shouldBeEmpty = shouldBeEmpty;*/
        this.shouldBeEmpty = shouldBeEmpty;
        
        N = 8;
        pieces = new Piece[N][N];
        if (!this.shouldBeEmpty) {
            initialize();
        }
        
        turn = 0;
        select1 = null;
        select1xy = null;
        
        moved = false;
        captured = false;
    }


  /** i = x-coordinate of each box;
    * j = y-coordinte of each box
    * the method will finish one colume, and then move right by 1. */

    private void initialize() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                 
                if (j == 0 && i%2 == 0) {
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");
                }

                if (j == 1 && i%2 == 1) {
                    
                    pieces[i][j] = new Piece(true, this, i, j, "shield");
                }

                if (j == 2 && i%2 == 0) {
                    
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");
                }

                if (j == 5 && i%2 == 1) {
                    
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");
                }

                if (j == 6 && i%2 == 0) {
                    
                    pieces[i][j] = new Piece(false, this, i, j, "shield");
                }

                if (j == 7 && i%2 == 1) {
                    
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");
                }
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

                String team;
                String type;
                String crown;
                Piece current = pieces[i][j];

                if (pieces[i][j] != null) {
                    
                    if (current.side() == 0) {
                        team = "-fire";
                    }
                    else {
                        team = "-water";
                    }
                    
                    
                    if (current.isShield()) {
                        type = "shield";
                    }
                    else if (current.isBomb()) {
                        type = "bomb";
                    }
                    else {
                        type = "pawn";
                    }

                    if (current.isKing()) {
                        crown = "-crowned.";
                    }
                    else {
                        crown = ".";
                    }

                    StdDrawPlus.picture(i + .5, j + .5, "img/" + type + team + crown + "png", 1, 1);
                }                      
                
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x >= N || y >= N || x < 0 || y < 0) {
            return null;
        }

        else if (pieces[x][y] == null) {
            return null;
        }
        
        else return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (x <= (N-1) && y <= (N-1) && p != null) {
                
                Piece target = p; 
                if (target != null) {
                   pieces[x][y] = target;
                }   
             
        }   

    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (xi < N && yi < N && xf < N && yf < N) {
            Piece start = pieces[xi][yi];
            Piece destination = pieces[xf][yf];

            if (start != null && destination == null){
                if (start.isKing()) {
                    if (xi+2 == xf) {
                        if (yi-2 == yf) {
                            return pieces[xi+1][yi-1] != null && pieces[xi+1][yi-1].side() != start.side();
                        }
                        else if (yi+2 == yf) {
                            return pieces[xi+1][yi+1] != null && pieces[xi+1][yi+1].side() != start.side();
                        }
                        else return false;
                    }
                    else if (xi-2 == xf) {
                        if (yi-2 == yf) {
                            return pieces[xi-1][yi-1] != null && pieces[xi-1][yi-1].side() != start.side();
                        }
                        else if (yi+2 == yf) {
                            return pieces[xi-1][yi+1] != null && pieces[xi-1][yi+1].side() != start.side();
                        }
                        else return false;
                    }
                    else return false;
                }
                else if (start.side() == 0) {
                    if (yi+2 == yf) {
                        if (xi+2 == xf) {
                            return pieces[xi+1][yi+1] != null && pieces[xi+1][yi+1].side() != start.side();
                        }
                        else if (xi-2 == xf) {
                            return pieces[xi-1][yi+1] != null && pieces[xi-1][yi+1].side() != start.side();
                        }
                        else return false;
                    }                
                    else return false;
                }
                else {
                    if (yi-2 == yf) {
                        if (xi+2 == xf) {
                            return pieces[xi+1][yi-1] != null && pieces[xi+1][yi-1].side() != start.side();
                        }
                        else if (xi-2 == xf) {
                            return pieces[xi-1][yi-1] != null && pieces[xi-1][yi-1].side() != start.side();
                        }
                        else return false;
                    }                
                    else return false;
                }
            }
            else return false;

        }
        else return false;        
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
    if (xi < N && yi < N && xf < N && yf < N) {
        Piece start = pieces[xi][yi];
        Piece destination = pieces[xf][yf];

        if (start != null && destination == null && !start.hasCaptured()){
            if (start.isKing()) {
                if (xi+1 == xf || xi-1 == xf) {
                    return yi+1 == yf || yi-1 == yf;
                }
                else return false;
            }
            else if (start.side() == 0) {
                if (yi+1 == yf) {
                    return xi+1 == xf || xi-1 == xf;
                }
                else return false;
            }
            else { /* water */
                if (yi-1 == yf) {
                    return xi+1 == xf || xi-1 == xf;
                }
                else return false;
            }
        }
        else return false;    
    }
    else return false;
    }
    

    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p != null && p.side() == turn) { /* piece exists in the block */
            return !moved;
        }
        else { /* empty block */
            if (select1 != null && !moved) { /* has selected a piece */
                if (validMove(select1xy[0], select1xy[1], x, y)) {
                    
                    return true;
                }
                else if (validCapture(select1xy[0], select1xy[1], x, y)) {
                    
                    return true;
                }
                else return false;
            }
            else if (captured) { /* if captured, can only capture the second time */
                return validCapture(select1xy[0], select1xy[1], x, y);

            }
            else return false;        
        }
    }

    public void select(int x, int y) {
        Piece target = pieceAt(x, y);
        
        
        if (target != null) {
            select1 = target;
            select1xy = new int[] {x, y};
        }
        else {
            
            select1.move(x, y);
            moved = true;
            if (select1xy[1] - y == 2 || select1xy[1] - y == -2) {
                captured = true;
            }            
            
            select1 = pieceAt(x, y);
            select1xy[0] = x;
            select1xy[1] = y;
            
            
        }
        
    }

    public Piece remove(int x, int y) {
        if (x >= N || y >= N || x < 0 || y < 0) {
            System.out.print("out of bound!");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.print("no piece here!");
            return null;
        }
        else {
            Piece temp = pieces[x][y];
            pieces[x][y] = null;
            return temp;
        }
    }

    public boolean canEndTurn() {
        return moved; 
    }

    public void endTurn() {
        
            if (turn == 0) {
                turn = 1;
            }
            else {
                turn = 0;
            }
            
            if (select1 != null){
               select1.doneCapturing(); 
            }          
            select1 = null;
            select1xy = null;
            
            moved = false;
            captured = false;
        
    }

    public String winner() {
        int count = 0;
        int[] location = null;
        for (int i=0; i < N; i++) {
            for (int j=0; j < N; j++) {
                if (pieces[i][j] != null) {
                    count += 1;
                    location = new int[] {i, j};
                }
            }
        }
        
        if (count == 0) {
            return "No one";
        }
        else if (count == 1) {
            if (pieceAt(location[0], location[1]).isFire()) {
                return "Fire";
            }
            else {
                return "Water";
            }
        }
        else {
            return null;
        }

    }



    public static void main(String[] args) {
        
        
        Board b = new Board(false);
        
        
        StdDrawPlus.setXscale(0, b.N);
        StdDrawPlus.setYscale(0, b.N);     

        

        while (true) {
            b.drawBoard(b.N);
            b.winner();
            

            if (StdDrawPlus.mousePressed()) {
               double x = StdDrawPlus.mouseX();
               double y = StdDrawPlus.mouseY();
               
               if (b.canSelect((int) x, (int) y)) {

                b.select((int) x, (int) y);
               }           
                
               
            }
            
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn(); 
                }              
                
            }

           
            StdDrawPlus.show(10);
        }
        
    }

    
}