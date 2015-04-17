public class Board {
    /** Location of pieces. */
    private Piece[][] gamers;
    private boolean empty;
    private Piece selected;
    private boolean redTurn;
    private boolean moved;
    private boolean multi;
    private Piece capturer;
    private int selectX, selectY;
    
    
    public Board(boolean shouldBeEmpty){
        int N = 8;
        redTurn = true;
        moved = false;
        empty = shouldBeEmpty;
        gamers = new Piece[N][N];
        if (shouldBeEmpty == false){
            // For loop didn't work, so did manual DONE2
            gamers[0][0] = new Piece(true, this, 0, 0, "pawn");
            gamers[2][0] = new Piece(true, this, 2, 0, "pawn");
            gamers[4][0] = new Piece(true, this, 4, 0, "pawn");
            gamers[6][0] = new Piece(true, this, 6, 0, "pawn");
            gamers[1][N-1] = new Piece(false, this, 1, N-1, "pawn");
            gamers[3][N-1] = new Piece(false, this, 3, N-1, "pawn");
            gamers[5][N-1] = new Piece(false, this, 5, N-1, "pawn");
            gamers[7][N-1] = new Piece(false, this, 7, N-1, "pawn");
            gamers[1][N-7] = new Piece(true, this, 1, N-7, "shield");
            gamers[3][N-7] = new Piece(true, this, 3, N-7, "shield");
            gamers[5][N-7] = new Piece(true, this, 5, N-7, "shield");
            gamers[7][N-7] = new Piece(true, this, 7, N-7, "shield");
            gamers[0][N-2] = new Piece(false, this, 0, N-2, "shield");
            gamers[2][N-2] = new Piece(false, this, 2, N-2, "shield");
            gamers[4][N-2] = new Piece(false, this, 4, N-2, "shield");
            gamers[6][N-2] = new Piece(false, this, 6, N-2, "shield");
            gamers[0][N-6] = new Piece(true, this, 0, N-6, "bomb");
            gamers[2][N-6] = new Piece(true, this, 2, N-6, "bomb");
            gamers[4][N-6] = new Piece(true, this, 4, N-6, "bomb");
            gamers[6][N-6] = new Piece(true, this, 6, N-6, "bomb");
            gamers[1][N-3] = new Piece(false, this, 1, N-3, "bomb");
            gamers[3][N-3] = new Piece(false, this, 3, N-3, "bomb");
            gamers[5][N-3] = new Piece(false, this, 5, N-3, "bomb");
            gamers[7][N-3] = new Piece(false, this, 7, N-3, "bomb");
        }
    }
    // Taken from Planets Implementation
    private String runTime(int N){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        // Taken from StdDrawDemo
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (!empty){
                    if (gamers[i][j] != null) {
                        StdDrawPlus.picture(i + .5, j + .5, picture(gamers[i][j]), 1, 1);
                    }
                }
            }
        }
        // Format from NBody
        while(true){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    if (!empty){
                        if (gamers[i][j] != null) {
                            StdDrawPlus.picture(i + .5, j + .5, picture(gamers[i][j]), 1, 1);
                        }
                    }
                }
            }
            if (StdDrawPlus.mousePressed()){
                if (canSelect(((int)StdDrawPlus.mouseX()),((int)StdDrawPlus.mouseY()))){
                    select(((int)StdDrawPlus.mouseX()), ((int)StdDrawPlus.mouseY()));
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(((int)StdDrawPlus.mouseX()) + .5, ((int)StdDrawPlus.mouseY()) + .5, .5);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                endTurn();
            }
            if (winner() != null){
                return winner();
            }
            StdDrawPlus.show(100);
        }
        
    }
    
    public void select(int x, int y){
        Piece t;
        if (pieceAt(x, y) == null){
            moved = true;
            selected.move(x,y);
            if (selected.hasCaptured()){
                multi = true;
                capturer = selected;
            }
            
        }
        t = pieceAt(x, y);
        selectX = x;
        selectY = y;
        selected = t;
        
    }
    // Took help in Office Hours (Can't remember name) MORE REDO
    private boolean validMove (int xi, int yi, int xf, int yf){
        if (xf > 7 || yf > 7){
            return false;
        }
        if (xf < 0 || yf < 0){
            return false;
        }
        int diffX = xf-xi;
        int diffY = yf-yi;
        if (pieceAt(xf, yf) != null){
            return false;
        }
        
        // If it's one step and the piece hasn't captured anything
        if (pieceAt(xi, yi) != null && (diffX == 1 || diffX == -1) && pieceAt(xi, yi).hasCaptured() == false){
            if (selected != null && selected.hasCaptured()){
                return false;
            }
            // If it's a king it can go any Y direction
            if(pieceAt(xi, yi).isKing()){
                if (diffX == 1 || diffX == -1){
                    return true;
                }
                else{
                    return false;
                }
            }
            // If it's a water has to go down (not king)
            if (pieceAt(xi, yi).isFire() != true){
                if (diffY == -1){
                    return true;
                }
                else{
                    return false;
                }
            }
            // If it's a fire has to go up (not king)
            else{
                if (diffY == 1){
                    return true;
                }
                else{
                    return false;
                }
                
            }
        }
        // If it's double step, must have captured something
        else{
            
            if (diffY > 2 || diffX > 2 || diffY < -2 || diffX < -2){
                return false;
            }
            // If there's nothing to be captured or on the same team
            if (pieceAt(((int) (xi+xf)/2),((int) (yi+yf)/2)) == null){
                return false;
            }
            if (pieceAt(xi, yi) == null){
                return false;
            }
            if (pieceAt(xi, yi).isFire() == pieceAt(((int) (xi+xf)/2),((int) (yi+yf)/2)).isFire()){
                return false;
            }
            // King Goes any direction
            if (pieceAt(xi, yi).isKing()){
                return true;
            }
            // If it's a water has to go down (not king)
            if (pieceAt(xi, yi).isFire() != true){
                if (diffY == -2){
                    return true;
                }
                else{
                    return false;
                }
            }
            // If it's a fire has to go up (not king)
            else{
                if (diffY == 2){
                    return true;
                }
                else{
                    return false;
                }
                
            }
        }
        
    }
    // POINT of interest
    // Took help in Office Hours (Can't remember name)
    public boolean canSelect(int x, int y){
        if (x > 7 || y > 7){
            return false;
        }
        if (x < 0 || y < 0){
            return false;
        }
        Piece p = gamers[x][y];
        if (selected == null){
            if ( (p != null) && (p.isFire() == redTurn)){
                return true;
            }
            else{
                return false;
            }
        }
        if (multi && capturer != null){
            if (selected.isBomb() || capturer.isBomb()){
                return false;
            }
            if (p != null && p != selected){
                return false;
            }
        }
        if (p == null){
            if (!validMove(selectX, selectY, x, y)){
                return false;
            }
        }
        if (p == null && (multi || !moved)){
            if (validMove(selectX, selectY, x, y)){
                return true;
            }
        }
        if ((p != null) && (p.isFire() == redTurn) && !moved){
            return true;
        }
        else{
            return false;
        }
    }
    
    // FINE
    public void place(Piece p, int x, int y){
        if (x > 7 || y > 7){
            return;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gamers[i][j] == p){
                    remove(i,j);
                }
            }
        }
        if (gamers[x][y] != null){
            remove(x,y);
        }
        gamers[x][y] = p;
        
    }
    // FINE
    public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7){
            return null;
        }
        else{
            return gamers[x][y];
        }
    }
    // FINE
    public Piece remove(int x, int y){
        Piece f = pieceAt(x,y);
        if (x > 7 || y > 7){
            System.out.print("Out of Bounds");
            return null;
        }
        else if (f == null){
            System.out.print("Empty!");
            return null;
        }
        else {
            gamers[x][y] = null;
        }
        return f;
    }
    // FINE
    public boolean canEndTurn(){
        if (selected == null){
            return false;
        }
        if ((moved == true) || multi == true || capturer != null){ //try no hasCaptured
            return true;
        }
        else{
            return false;
        }
    }
    // IFFY
    public void endTurn(){
        if (canEndTurn()){
            if (redTurn == true){
                redTurn = false;
            }
            else{
                redTurn = true;
            }
            if (selected != null){
                selected.doneCapturing(); // Take a look here
                selected = null;
                capturer = null;
            }
            moved = false;
            multi = false;
            
        }
    }
    // FINE
    public String winner(){
        boolean r = true;
        boolean b = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gamers[i][j] != null){
                    if (gamers[i][j].isFire() == true){
                        r = false;
                    }
                    if (gamers[i][j].isFire() == false){
                        b = false;
                    }
                }
                
            }
        }
        if(r && !b){
            return "Water";
        }
        if(b && !r){
            return "Fire";
        }
        if(r && b){
            return "No one";
        }
        else{
            return null;
        }
    }
    // Help from Michael
    private String picture(Piece p){
        String i = "";
        if(p.isBomb()){
            if(p.isFire()){
                if(p.isKing()){
                    i+="img/bomb-fire-crowned.png";
                }
                else{
                    i+="img/bomb-fire.png";
                }
            }
            else{
                if(p.isKing()){
                    i+="img/bomb-water-crowned.png";
                }
                else{
                    
                }
                i+="img/bomb-water.png";
            }
        }
        else if(p.isShield()){
            if(p.isFire()){
                if(p.isKing()){
                    i+="img/shield-fire-crowned.png";
                }
                else{
                    
                }
                i+="img/shield-fire.png";
            }
            else{
                if(p.isKing()){
                    i+="img/shield-water-crowned.png";
                }
                else{
                    
                }
                i+="img/shield-water.png";
            }
        }
        else{
            if(p.isFire()){
                if(p.isKing()){
                    i+="img/pawn-fire-crowned.png";
                }
                else{
                    
                }
                i+="img/pawn-fire.png";
            }
            else{
                if(p.isKing()){
                    i+="img/pawn-water-crowned.png";
                }
                else{
                    
                }
                i+="img/pawn-water.png";
            }
        }
        return i;
    }
    public static void main(String[] args) {
        Board b = new Board(false);
        System.out.println("Congratulations: "+b.runTime(8));
        
    }
}