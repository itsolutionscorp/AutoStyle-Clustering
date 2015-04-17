public class Board {

    private Piece[][] pieces;
    private boolean empty;
    private boolean fireTurn = true; 
    private Piece selected = null;
    private boolean moved = false; 
    private int N = 8; 
    private int moveNumber = 0; 

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                    if (pieces[i][j].isBomb()) {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }  
                    }
                    else if (pieces[i][j].isShield()) {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                    }

                    else {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }

                }
                else {
                    if (pieces[i][j].isBomb()) {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                    }
                    else if (pieces[i][j].isShield()) {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                    }

                    else {
                        if (pieces[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
            }
        }
    }
    

    public Board(boolean shouldBeEmpty){
        empty = shouldBeEmpty;
        pieces = new Piece[N][N];


        if (empty == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((j == 0) && ((i+j) % 2 == 0)) {
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");  
                    }

                    else if ((j == 1) && ((i+j) % 2 == 0)) {
                    pieces[i][j] = new Piece(true, this, i, j, "shield");  
                    }

                    else if ((j == 2) && ((i+j) % 2 == 0)) {
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");  
                    }
            
                    else if ((j == 5) && ((i+j) % 2 == 0)) {
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");  
                    }
            
                    else if ((j == 6) && ((i+j) % 2 == 0)) {
                    pieces[i][j] = new Piece(false, this, i, j, "shield");  
                    }
            
                    else if ((j == 7) && ((i+j) % 2 == 0)) {
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");  
                    }                   
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N) {
                return null; 
            }

        if (pieces[x][y] != null) {
            return pieces[x][y]; 
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
                System.out.println("Invalid move! Out of bound.");
                return false; 
        }
        if (pieces[x][y] != null) {
            if (fireTurn){
                if (pieces[x][y].isFire()){
                    if (selected == null){
                        return true;
                    }
                    else {
                        if (moved) {
                            return false;
                        }
                    return true;
                    }
                }
                return false;
            }

            else if (fireTurn == false) {
                if (pieces[x][y].isFire()){
                    return false;
                }
                else {
                    if (selected == null) {
                        return true;
                    }
                    else {
                        if (moved) {
                            return false;
                        }
                    return true;
                    }
                }
            }
        }

        else if (pieces[x][y] == null) {
            if (selected!= null) {
                }

            if (selected != null) {
                if (selected.hasCaptured()) {
                    moved = false;
                }
                if (moved == false) {
                    /*check if it's fire*/
                    if (selected.isFire()) {
                        /*check if it's king*/
                        if (selected.isKing()) {
                            
                            if ((pieceAt(x-1, y-1) == selected) || (pieceAt(x+1, y-1) == selected) || (pieceAt(x+1, y+1) == selected) || (pieceAt(x-1, y+1) == selected)) {
                                if (moveNumber == 0) {
                                    return true;
                                }
                                return false; 
                            }
                            /*check if it's two steps*/
                            else if ((pieceAt(x-2, y-2) == selected)) {
                                if (((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire() != true))) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }
                            else if ((pieceAt(x+2, y-2) == selected)) {
                                if (((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire() != true))) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }
                            else if ((pieceAt(x+2, y+2) == selected)) {
                                if (((pieceAt(x+1, y+1) != null) && (pieceAt(x+1, y+1).isFire() != true))) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }

                            else if ((pieceAt(x-2, y+2) == selected)) {
                                if (((pieceAt(x-1, y+1) != null) && (pieceAt(x-1, y+1).isFire() != true))) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }    
                        }

                        else if (selected.isKing() == false) { 
                            
                            if ((pieceAt(x-1, y-1) == selected) || (pieceAt(x+1, y-1) == selected)) {
                                if (moveNumber == 0) {
                                    return true;
                                }
                                return false; 
                            }
                            /*check if it's two steps*/
                            else if ((pieceAt(x-2, y-2) == selected)) {
                                if (((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire() != true))) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }

                            else if ((pieceAt(x+2, y-2) == selected)) {
                                if ((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire() != true)) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }
                        }
                        return false; 
                    }   
                        /*water*/       
                    else {
                        if (selected.isKing()) {
                            
                            if ((pieceAt(x-1, y-1) == selected) || (pieceAt(x+1, y-1) == selected) || (pieceAt(x+1, y+1) == selected) || (pieceAt(x-1, y+1) == selected)) {
                                if (moveNumber == 0) {
                                    return true;
                                }
                                return false; 
                            }
                            /*check if it's two steps*/
                            else if ((pieceAt(x-2, y-2) == selected)) {
                                if ((pieceAt(x-1, y-1) != null) && (pieceAt(x-1, y-1).isFire())) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }
                            else if ((pieceAt(x+2, y-2) == selected)) {
                                if ((pieceAt(x+1, y-1) != null) && (pieceAt(x+1, y-1).isFire())) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }
                            else if ((pieceAt(x+2, y+2) == selected)) {
                                if ((pieceAt(x+1, y+1) != null) && (pieceAt(x+1, y+1).isFire())) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }

                            else if ((pieceAt(x-2, y+2) == selected)) {
                                if ((pieceAt(x-1, y+1) != null) && (pieceAt(x-1, y+1).isFire())) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }
                        
                        }

                        else if (selected.isKing() == false) { 
                         
                            if ((pieceAt(x+1, y+1) == selected) || (pieceAt(x-1, y+1) == selected)) {
                                if (moveNumber == 0) {
                                    return true;
                                }
                                return false; 
                            }
                                /*check if it's two steps*/
                            else if ((pieceAt(x+2, y+2) == selected)) {
                                if (((pieceAt(x+1, y+1)) != null) && (pieceAt(x+1, y+1).isFire())) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }

                            else if ((pieceAt(x-2, y+2) == selected)) {
                                if (((pieceAt(x-1, y+1)) != null) && (pieceAt(x-1, y+1).isFire())) {
                                    if (moveNumber >= 0) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false; 
                            }  
                        }
                        return false;
                    }
                }
                return false; 
            }
            return false; 
        }
        return false; 
    }

    public void select(int x, int y) {
        
        if (pieces[x][y] == null) {
            if (selected != null) {
                selected.move(x, y);
                moved = true; 
            }
        }
        else if (pieces[x][y] != null) {
            selected = pieces[x][y];
        }

    }


    public void place(Piece p, int x, int y) {
        if (p == null) {
        }
        else { 
            if (p != null) {
                pieces[x][y] = p; 
            }
        }
    }

    public Piece remove(int x, int y) {
        if (pieces[x][y] != null) {
            Piece ghostPiece = pieces[x][y];
            pieces[x][y] = null; 
            return ghostPiece; 
        }
        return null;
    }

/*    */
    public boolean canEndTurn(){
        if (moved || (selected != null && selected.hasCaptured())) {
            return true;
        }
        else {
            if (moveNumber > 0) {
                return true;
            }
            return false; 
        }
    }

    public String winner(){
        int fireCount = 0; 
        int waterCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        fireCount += 1; 
                    }
                    else {
                        waterCount += 1;
                    }
                }
            }
        }
        if (fireCount == 0) {
            if (waterCount == 0) {
                return "No one";
            }
            return "Water";
        }
        else if (waterCount == 0) {
            return "Fire";
        }
        else {
            return null;
        }
    }

/*    */
    public void endTurn(){
        if (selected == null){
            moved = false; 
            fireTurn = !fireTurn;
            moveNumber = 0; 
            winner();
        }
        else {
            selected.doneCapturing();
            selected = null; 
            moved = false; 
            fireTurn = !fireTurn;
            moveNumber = 0; 
            winner();
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board checker = new Board(false);        
        
        while(true) {
            checker.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (checker.selected != null) {
                    if (checker.selected.hasCaptured()) {
                        checker.moved = false;
                        checker.moveNumber += 1;
                    }
                }                

                if (checker.canSelect((int) x, (int) y) == true) {
                    checker.select((int) x, (int) y);
                }

            }

            if (StdDrawPlus.isSpacePressed()){
                if (checker.canEndTurn()) { 
                    checker.endTurn();
                }
            }        
            StdDrawPlus.show(100);
        }
    }
}

