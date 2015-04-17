public class Board{

	private Piece [][] pieces;
    private int N = 8;
    
    private Piece selected = null;
    private int selectedX = -1, selectedY = -1;

    private int currentTurn = 0;
    private boolean playermoved = false;
    private int numberOfFirePieces = 0;
    private int numberOfWaterPieces = 0;

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[N][N];
        if (!shouldBeEmpty){
            initDefaultBoard();
        }
    }

    private void increasePiece(Piece p){
        if (p.isFire()){
            numberOfFirePieces++;
        }
        else{
            numberOfWaterPieces++;
        }
    }

    private void decreasePiece(Piece p){
        if (p.isFire()){
            numberOfFirePieces -=1;
        }
        else{
            numberOfWaterPieces -=1;
        }
    }

    private void initDefaultBoard(){ 
        // i goes ----->, j goes ^
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (j == 0 && (i %2) == 0){
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    increasePiece(pieces[i][j]);
                }
                if (j == 1 && (i % 2) == 1){
                    pieces[i][j] = new Piece(true, this, i, j, "shield");
                    increasePiece(pieces[i][j]);
                }
                if (j == 2 && (i % 2) == 0){
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    increasePiece(pieces[i][j]);
                }
                if (j == N-1 && (i % 2) == 1){
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    increasePiece(pieces[i][j]);
                }
                if (j == N-2 && (i % 2) == 0){
                    pieces[i][j] = new Piece(false, this, i, j, "shield");
                    increasePiece(pieces[i][j]);
                }
                if (j == N-3 && (i % 2) == 1){
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    increasePiece(pieces[i][j]);
                }
            }
        }
    }

    /* Checks if pieces at (x,y) is null */
    private boolean isEmpty(int x, int y){
        return inBound(x, y) && pieces[x][y] == null;
    }

    private void drawBoard(){
        drawJustBoard();
        drawPieces();
    }

	private void drawJustBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawPieces(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isEmpty(i, j)){
                    if (pieces[i][j].isShield() && pieces[i][j].isFire()){
                        if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                    }
                    if (pieces[i][j].isShield() && (!pieces[i][j].isFire())){
                         if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                    }
                    if (pieces[i][j].isBomb() && pieces[i][j].isFire()){
                         if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                    }
                    if (pieces[i][j].isBomb() && !pieces[i][j].isFire()){
                         if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        }
                        else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                    }
                    if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j].isFire()){
                         if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }
                    if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && !pieces[i][j].isFire()){
                         if (pieces[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        else{
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (inBound(x, y) && !isEmpty(x, y)){
            return pieces[x][y];
        }
        return null;
    }

    /* Checks if coordinate is part of the chess board
    */
    private boolean inBound(int x, int y){
        if (x >= N || x < 0 || y >= N || y < 0){
            return false;
        }
        return true;
    }

    /*  If no piece or x,y not in board, does nothing.
        If the piece is already in board, it will be placed in the new coordinate.
    */
    public void place(Piece p, int x, int y){
        if (p == null || !inBound(x, y)){
            return;
        }


        // search for piece
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] == p){
                    decreasePiece(pieces[i][j]);
                    pieces[i][j] = null;
                }
            }
        }

        if (pieces[x][y] != null){
            decreasePiece(pieces[x][y]);
        }
        pieces[x][y] = p;
        increasePiece(pieces[x][y]);
    }

    public Piece remove(int x, int y){
        if (!inBound(x, y)){;
            System.out.println("Out of bounds, brah!");
            return null;
        }
        if (pieces[x][y] == null){
            System.out.println("Nuthin to see here");
            return null;
        }
        Piece temp = pieces[x][y];
        decreasePiece(temp);
        pieces[x][y] = null;
        return temp;
    }

    public void select(int x, int y){
        if (inBound(x, y)){
            if (selected != null){
                if (pieces[x][y] == null){
                    selected.move(x, y);
                    playermoved = true;
                    selectedX = x;
                    selectedY = y;
                }
            }
            if (!isEmpty(x,y)){
                selected = pieces[x][y];
                selectedX = x;
                selectedY = y;
            }
        }
        
    }

    private void fillSquareWhite(int x, int y){
        drawJustBoard();
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        drawPieces();
    }


    /* Accounted for: multi-capturing, selected a piece that has not moved
        moving to an empty square
    */
    public boolean canSelect(int x, int y){
        if (!inBound(x, y)){
            return false;
        }
        if (pieces[x][y] != null){ // piece exists
            if (currentTurn == 0){
                return pieces[x][y].isFire() && (selected == null || playermoved == false);
            }
            if (currentTurn == 1){
                return !pieces[x][y].isFire() && (selected == null || playermoved == false);
            }
            return false;
        } 
        else {
            if (selected != null && playermoved == false){
                return !(selectedX == x && selectedY == y) && (validMove(selectedX, selectedY, x, y) || canCapture(selectedX, selectedY, x, y));
            }
            if (selected != null && selected.hasCaptured()){
                return !selected.isBomb() && canCapture(selectedX, selectedY, x, y);
            }
            return false;
        }
    }

    /*  Checks if xf,yf is a valid move
        Accounted for: if it can capture using the xf,yf
    */
    private boolean validMove(int xi, int yi, int xf, int yf){
        if (pieces[xi][yi].isKing()){
            return (xi - xf == 1 && yi - yf == -1) || (xi - xf == -1 && yi - yf == -1) || (xi - xf == 1 && yi - yf == 1) || 
                (xi - xf == -1 && yi - yf == 1);
        }
        else{
            if (currentTurn == 0){
                return ((xi - xf == 1 && yi - yf == -1) || (xi - xf == -1 && yi - yf == -1));
            }
            if (currentTurn == 1){
                return (xi - xf == 1 && yi - yf == 1) || (xi - xf == -1 && yi - yf == 1);
            }
        }
        return false;
    }

    /*  Checks if there is a piece that can be captured and if the x,y coordinate
    */
    private boolean canCapture(int xi, int yi, int xf, int yf){
        if (!pieces[xi][yi].isKing()){
            if (currentTurn == 0){ // fire's turn
                if ((xf == xi + 2 && yf == yi + 2) && inBound(xi + 1, yi + 1) && pieces[xi + 1][yi + 1] != null){ 
                    return pieceAt(xi + 1, yi + 1).side() != currentTurn; 
                }
                if ((xf == xi - 2 && yf == yi + 2) && inBound(xi - 1, yi + 1) && pieces[xi - 1][yi + 1] != null){
                    return pieceAt(xi - 1, yi + 1).side() != currentTurn;
                }
            }
            if (currentTurn == 1){
                if ((xf == xi + 2 && yf == yi - 2) && inBound(xi + 1, yi - 1) && pieces[xi + 1][yi - 1] != null){
                    return pieceAt(xi + 1, yi - 1).side() != currentTurn; 
                }
                if ((xf == xi - 2 && yf == yi - 2) && inBound(xi - 1, yi - 1) && pieces[xi - 1][yi - 1] != null){
                    return pieceAt(xi - 1, yi - 1).side() != currentTurn;
                }
            }
        }
        else{
            if ((xf == xi + 2 && yf == yi + 2) && inBound(xi + 1, yi + 1) && pieces[xi + 1][yi + 1] != null){ 
                return pieceAt(xi + 1, yi + 1).side() != currentTurn; 
            }
            if ((xf == xi - 2 && yf == yi + 2) && inBound(xi - 1, yi + 1) && pieces[xi - 1][yi + 1] != null){
                return pieceAt(xi - 1, yi + 1).side() != currentTurn;
            }
            if ((xf == xi + 2 && yf == yi - 2) && inBound(xi + 1, yi - 1) && pieces[xi + 1][yi - 1] != null){
                return pieceAt(xi + 1, yi - 1).side() != currentTurn;
            }
            if ((xf == xi - 2 && yf == yi - 2) && inBound(xi - 1, yi - 1) && pieces[xi - 1][yi - 1] != null){
                return pieceAt(xi - 1, yi - 1).side() != currentTurn;
            }
        }
        return false;
    }


    public void endTurn(){
        if (currentTurn == 0){
            currentTurn = 1;
        }
        else if (currentTurn == 1){
            currentTurn = 0;
        }
        selected.doneCapturing(); // ADDED THIS TODAY
        selected = null;
        playermoved = false;

    }

    public boolean canEndTurn(){
        return playermoved || (selected != null && selected.hasCaptured());
    }

    public String winner(){
        if (numberOfWaterPieces == 0 && numberOfFirePieces == 0){
            return "No one";
        }
        else if (numberOfFirePieces == 0){
            return "Water";
        }
        else if (numberOfWaterPieces == 0){
            return "Fire";
        }
        else{
            return null;
        }
    }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        board.drawBoard();

        while (board.winner() == null){
            while(true) {
                if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    if (board.canSelect((int) x, (int) y)){
                        board.select((int) x, (int) y);
                        board.fillSquareWhite((int) x, (int) y);
                    }
                }
                if (StdDrawPlus.isSpacePressed()){
                    if (board.canEndTurn()){
                        board.endTurn();
                        board.drawBoard();
                    }
                } 
                StdDrawPlus.show(1);           
            }
        }
    System.out.println(board.winner());
    }
}
