public class Board {

    private Piece[][] pieces;
    private int turn = 0;
    private boolean moved = false;
    private Piece selected = null;
    private int selectedX, selectedY;

    public static void main (String args[]){
        /*starts a GUI supported version of the game.*/
        Board b = new Board(false);
        b.drawBoard(false);

        while(b.winner() == null){
            while(!StdDrawPlus.isSpacePressed() || !b.canEndTurn()){
                if(StdDrawPlus.mousePressed()){
                    int clickedX = (int)StdDrawPlus.mouseX();
                    int clickedY = (int)StdDrawPlus.mouseY();
                    if(b.canSelect(clickedX, clickedY)){
                        b.select(clickedX, clickedY);
                    }
                }
                b.drawBoard(false);
                StdDrawPlus.show(100);
            }
            b.endTurn();
        }
    }

    public Board(boolean shouldBeEmpty){
        /*The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes a Board with the default configuration. 
        Note that an empty Board could possibly be useful for testing purposes.*/
        pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
        if(!shouldBeEmpty){
            String[] allTypes = {"pawn", "shield", "bomb"};
            int row = 0;
            int fireX, waterX;
            for(String type:allTypes){
                for (int col = 0; col < 8; col+=2) {
                    if(row%2 == 0){
                        pieces[col][row] = new Piece(true, this, col, row, type);
                        pieces[col+1][7-row] = new Piece(false, this, col+1, 7-row, type);
                    }
                    else{
                        pieces[col+1][row] = new Piece(true, this, col+1, row, type);
                        pieces[col][7-row] = new Piece(false, this, col, 7-row, type);
                    }
                }
                row++;
            }
        }
    }

    private void drawBoard(boolean isEmpty){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
        }
        if(selected != null){
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
        }
        if(!isEmpty){
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if(pieces[x][y]!= null){
                        Piece p = pieces[x][y];
                        if(p.isFire()){
                            if(p.isBomb()){
                                if(p.isKing()){
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-fire-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-fire.png", 1, 1);
                                }

                            }
                            else if(p.isShield()){
                                if(p.isKing()){
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-fire-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-fire.png", 1, 1);
                                }
                            }
                            else{
                                if(p.isKing()){
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-fire-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-fire.png", 1, 1);
                                }
                            }
                        }
                        else{
                            if(p.isBomb()){
                                if(p.isKing()){
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-water-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/bomb-water.png", 1, 1);
                                }

                            }
                            else if(p.isShield()){
                                if(p.isKing()){
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-water-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/shield-water.png", 1, 1);
                                }
                            }
                            else{
                                if(p.isKing()){
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-water-crowned.png", 1, 1);
                                }
                                else{
                                    StdDrawPlus.picture(x+0.5, y+0.5, "img/pawn-water.png", 1, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        /*Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.*/
        if(x < 0 || y < 0 || x > 7 || y > 7){
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y){
        /*Returns true if there is a piece the piece at (x, y) and it can be selected.
        A piece may be selected if it is the corresponding player’s turn and one of the following is true:
        The player has not selected a piece yet.
        The player has selected a piece, but did not move it.
        An empty square may be selected if one of the following is true:
        During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously 
        selected Piece.
        During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, 
        you should only select the active piece once; all other selections should be valid destination points.*/
        if(pieceAt(x, y) != null){
            if(selected == null || !moved){
                if(pieceAt(x, y).isFire() && turn == 0){
                    return true;
                }
                else if(!pieceAt(x, y).isFire() && turn == 1){
                    return true;
                }
                return false;
            }
        }
        else if(pieceAt(x, y) == null){
            if(selected != null){
                if(validMove(selectedX, selectedY, x, y)){
                    if(!moved){
                        return true;
                    }
                    else if(selected.hasCaptured()){
                        if((y-selectedY) == 2 || (selectedY - y) == 2)
                            return true;
                    }
                }
            }
        }
        return false;
    }       

    private boolean validMove(int xi, int yi, int xf, int yf){
        /*Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), strictly from a geometry/piece-race point of view. 
        validMove does not need to take into consideration whose turn it is or if a move has already been made, but rather can. 
        Update (2/6): validMove is not required, and will not be tested. \You may implement this however you want. 
        It is suggested you use this method to simplify your implementation of canSelect.*/
        if(!moved && (xf == (xi + 1) || xf == (xi - 1))){
            if(yf == (yi + 1) && (selected.isFire() || selected.isKing())){
                return true;
            }
            else if(yf == (yi - 1) && (!selected.isFire() || selected.isKing())){
                return true;
            }
        }
        else if(xf == (xi + 2) || xf == (xi - 2)){
            int capturedX = xi + ((xf-xi)/2);
            if(yf == (yi + 2) && (selected.isFire() || selected.isKing())){
                if(pieceAt(capturedX, yi + 1) != null && pieceAt(capturedX, yi + 1).side() != selected.side()){
                    return true;
                }
            }
            else if(yf == (yi - 2) && (!selected.isFire() || selected.isKing())){
                if(pieceAt(capturedX, yi - 1) != null && pieceAt(capturedX, yi - 1).side() != selected.side()){
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y){
        /*Selects the piece at (x, y) if possible. Optionally, it is recommended to color the background of the selected square white on the GUI via the pen color 
        function. For any piece to perform a capture, that piece must have been selected first.*/
        selectedX = x;
        selectedY = y;
        if(selected == null){
            selected = pieceAt(x, y);
        }
        else if(pieceAt(x, y) != null && selected.side() == pieceAt(x, y).side()){
            selected = pieceAt(x, y);
        }
        else{
            selected.move(x, y);
            moved = true;
        }
    }

    public void place(Piece p, int x, int y){
        /*Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If another piece already exists at (x, y), p will replace that piece. (This method is potentially useful for creating specific 
        test circumstances.)*/
        if(x < 0 || y < 0 || x > 7 || y > 7 || p == null){
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y){
        /*Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
        If there is no piece at (x, y), returns null and prints an appropriate message.*/
        if(x < 0 || y < 0 || x > 7 || y > 7){
            System.out.println("Index out of bounds");
            return null;
        }
        if(pieceAt(x, y) == null){
            System.out.println("No piece found");
            return null;
        }
        /*if ((x + y) % 2 == 0) drawSquare(x, y, 2);
        else                  drawSquare(x, y, 3);*/
        Piece savePiece = pieceAt(x, y);
        pieces[x][y] = null;
        return savePiece;
    }

    public boolean canEndTurn(){
        /*Returns whether or not the the current player can end their turn. To be able to end a turn, a piece must have moved or performed a capture.*/
        return moved;
    }

    public void endTurn(){
        /*Called at the end of each turn. Handles switching of players and anything else that should happen at the end of a turn.*/
        if(turn == 0){
            turn = 1;
        }
        else{
            turn = 0;
        }
        moved = false;
        if(selected != null){
            selected.doneCapturing();
        }
        selected = null;
    }

    public String winner(){
        /*Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over. 
        Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them (In this event, just leave it at null). 
        Determine the winner solely by the number of pieces belonging to each team.*/
        int firePlayer = 0;
        int waterPlayer = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j]!= null){
                    if(pieces[i][j].isFire()){
                        firePlayer++;
                    }
                    else{
                        waterPlayer++;
                    }
                }
            }
        }
        if(firePlayer == 0 && waterPlayer == 0){
            return "No one";
        }
        else if(firePlayer == 0){
            return "Water";
        }
        else if(waterPlayer == 0){
            return "Fire";
        }
        return null;
    }
}
