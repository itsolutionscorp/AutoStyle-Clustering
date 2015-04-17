public class Board{
    private Piece[][] pieces;
    private int size;
    private int fire_player; // 0 if fire player's turn, 1 if water player's turn
    private boolean selected_piece; // whether piece has been selected
    private Piece select_piece; // piece that is selected
    private int selectedX; // coordinate of selected piece
    private int selectedY;
    private boolean moved_piece; // true if piece has moved on current turn,
                                 // including captures, false otherwise
    private boolean captured_piece; // true if piece has captured on current turn, 
                                    // false otherwise 
                                    // seemed easier to do than using hasCaptured()
    private int clickedX = -1; // for drawing
    private int clickedY = -1;

    public static void main(String[] args){
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, board.size);
        StdDrawPlus.setYscale(0, board.size);
        while (true){
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)){
                    board.select(x, y);
                    board.clickedX = x;
                    board.clickedY = y;
                }
            }    
            if (StdDrawPlus.isSpacePressed() && board.canEndTurn()){
                board.endTurn();
            }  
            board.drawBoard(board.size);      
            StdDrawPlus.show(80);
        }        
    }

    public Board(boolean shouldBeEmpty){
        size = 8;
        fire_player = 0;
        selected_piece = false;       
        moved_piece = false;
        captured_piece = false;
        pieces = new Piece[size][size];
        if (shouldBeEmpty){
        }
        else{
            for(int i = 0; i <= 7; i+=2){
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
            for(int i = 1; i <= 7; i+=2){
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x > 7 || x < 0 || y > 7 || y < 0){
            return null;
        }
        return pieces[x][y];
    }

    public String winner(){
        int water_pieces = 0;
        int fire_pieces = 0;
        Piece temp;
        //iterates through the entire board and counts pieces
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                temp = pieces[i][j];
                if (temp != null){
                    if (temp.isFire()){
                        fire_pieces += 1;
                    }
                    else{
                        water_pieces += 1;
                    }
                }
            }
        }
        if (fire_pieces > 0 && water_pieces > 0){
            return null;
        }
        else if (fire_pieces > 0){
            return "Fire";
        }   
        else if (water_pieces > 0){
            return "Water";
        }
        return "No one";
    }

    public boolean canSelect(int x, int y){
        if (x > 7 || x < 0 || y > 7 || y < 0){
            return false;
        }
        Piece target = pieces[x][y];
        //testing if a space can be selcted
        if (target == null){
            if (selected_piece == false){
                return false;
            }
            if (moved_piece && !captured_piece){
                return false;
            }
            // bomb explodes, should not be able to move
            if (captured_piece && select_piece == null){
                return false;
            }
            // Testing for possible captures
            // Possible if captured before or if not yet moved
            if (validMove(selectedX, selectedY, x, y, 2)){
                int capturedX = selectedX + (x - selectedX) / 2;  
                int capturedY = selectedY + (y - selectedY) / 2;
                Piece capture_piece = pieces[capturedX][capturedY];
                if (capture_piece == null){
                    return false;
                }
                else if (capture_piece.side() != select_piece.side()){
                    return true;
                }
                else{
                    return false;
                }
            }
            //captured pieces can no longer move without capturing
            else if (captured_piece){
                return false;
            }
            else{
            //simple move (not capture)
                if (validMove(selectedX, selectedY, x, y, 1)){
                    return true;
                }
                return false;
            }
        }
        // testing if a piece can be selcted
        else{
            int is_fire = target.side();
            if (fire_player != is_fire){
                return false;
            }
            else if (selected_piece == false){
                return true;
            }
            else if (selected_piece == true && moved_piece == false){
                return true;
            }
            else{
                return false;
            }
        }         
    }

    public void select(int x, int y){
        Piece select = pieces[x][y];
        // Selecting an empty space
        if (select == null){
            int spaceX = x;
            int spaceY = y;
            if (selected_piece){
                select_piece.move(spaceX, spaceY);
                moved_piece = true;
                if (Math.abs(spaceX - selectedX) == 2){
                    captured_piece = true;
                    selectedX = spaceX;
                    selectedY = spaceY;
                }
            }
        }
        else{
            selected_piece = true;
            select_piece = select;
            selectedX = x;
            selectedY = y;
        }
    }

    public void place(Piece p, int x, int y){
        if (x > 7 || x < 0 || y > 7 || y < 0 || p == null){
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y){
        if (x > 7 || x < 0 || y > 7 || y < 0){
            System.out.println("Input out of bounds");
            return null;
        }
        if (pieces[x][y] == null){
            System.out.println("No piece at location");
            return null;
        }
        Piece removed = pieces[x][y];
        pieces[x][y] = null;
        return removed;
    }

    public boolean canEndTurn(){
        return moved_piece;
    }

    public void endTurn(){
        fire_player = 1 - fire_player;
        selected_piece = false;
        moved_piece = false;
        captured_piece = false;
        select_piece.doneCapturing();
    }

    /** Idea found on piazza
     * Draws the board */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (clickedX == i && clickedY == j && selected_piece){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null){
                    StdDrawPlus.picture(i + .5, j + .5, getPieceImage(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    /** Returns the maximum of four integers */
    private int max(int a, int b, int c, int d){
        return Math.max(Math.max(Math.max(a, b), c), d);
    }

    /** Returns the minimum of four integers */
    private int min(int a, int b, int c, int d){
        return Math.min(Math.min(Math.min(a, b), c), d);
    }

    /** Tests if move is mathematically possible, considering
    if piece is king and side of piece 
    dist is the diagonal distance*/

    private boolean validMove(int xi, int yi, int xf, int yf, int dist){
        //can probably use absolute value
        if(max(xi, yi, xf, yf) > 7 || min(xi, yi, xf, yf) < 0){
            return false;
        }
        if (pieces[xi][yi] == null){
            return false;
        }
        Piece test = pieces[xi][yi];
        if (fire_player == 0){
            if (test.isKing()){
                if (yf == yi - dist && Math.abs(xf - xi) == dist){
                    return true;
                }   
            }
            if (yf == yi + dist && Math.abs(xf - xi) == dist){
                return true;
            }
        }
        else{
            if (test.isKing()){
                if (yf == yi + dist && Math.abs(xf - xi) == dist){
                    return true;
                }   
            }
            if (yf == yi - dist && Math.abs(xf - xi) == dist){
                return true;
            }
        }
        return false;
    }

    /** Returns String for image of a piece */
    private String getPieceImage(Piece p){
        String type;
        String side;
        if (p.isBomb()){
            type = "bomb";
        }
        else if (p.isShield()){
            type = "shield";
        }
        else{
            type = "pawn";
        }
        if (p.isFire()){
            side = "fire";
        }
        else{
            side = "water";
        }
        if (p.isKing()){
            return "img/" + type + "-" + side + "-crowned.png";
        }
        else{
            return "img/" + type + "-" + side + ".png";
        }
    }    
}