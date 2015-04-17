public class Board{
    private Piece[][] Grid;
    private boolean isPieceSelected = false;
    private Piece pieceSelected;
    private int[] pieceSelectedPosition = new int[2];
    private boolean isFireTurn = true;
    private boolean hasMoved = false;
    public Board(boolean shouldBeEmpty){
        Grid = new Piece[8][8];
        if(!shouldBeEmpty){
            for(int i=0; i < 4; i++){
                Grid[2*i][0] = new Piece(true, this, 2*i, 0, "pawn");
                Grid[2*i+1][1] = new Piece(true, this, 2*i+1, 1, "shield");
                Grid[2*i][2] = new Piece(true, this, 2*i, 2, "bomb");
                Grid[2*i+1][5] = new Piece(false, this, 2*i+1, 5, "bomb");
                Grid[2*i][6] = new Piece(false, this, 2*i, 6, "shield");
                Grid[2*i+1][7] = new Piece(false, this, 2*i+1, 7, "pawn");
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x<0 || x>7 || y<0 || y>7){
            return null;
        }
        return Grid[x][y];
    }

    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x,y);
        if (x < 0 && x > 7 && y < 0 && y > 7){
            return false;
        }
        else if (isPieceSelected) {
            if (pieceSelected.hasCaptured()){
                if (p!=null){
                    return false;
                }
                else{
                    return validMove(pieceSelectedPosition[0], pieceSelectedPosition[1], x, y);
                }
            }
            else {
                if (p != null && p.isFire() == isFireTurn && !hasMoved){
                    return true;
                }
                else if (!hasMoved){
                    return validMove(pieceSelectedPosition[0], pieceSelectedPosition[1], x, y);
                }
                else{
                    return false;
                }
            }
        }
        else if (p != null && p.isFire() == isFireTurn && !hasMoved){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean validMove(int xi, int yi, int xf, int yf){
        if (xf < 0 && xf > 7 && yf < 0 && yf > 7){
            return false;
        }
        if (xi < 0 && xi > 7 && yi < 0 && yi > 7){
            return false;
        }
        int dx = xf - xi;
        int dy = yf - yi;
        if (Math.abs(dx) != Math.abs(dy)){
            return false;
        }
        if (dx == 0 || Math.abs(dx) > 2){
            return false;
        }
        Piece intial = pieceAt(xi, yi);
        Piece captured = pieceAt((xi + xf)/2 , (yi + yf)/2);
        Piece end = pieceAt(xf, yf);
        if (Math.abs(dx) == 2 && (captured == null || captured.isFire() == intial.isFire())){
            return false;
        }
        else if (intial == null || end != null){
            return false;
        }
        else if (intial.hasCaptured() && (Math.abs(dy) != 2)){
            return false;
        }
        else if (intial.isKing()){
            return true;
        }
        else if (intial.isFire() && dy > 0){
            return true;
        }
        else if (!intial.isFire() && dy < 0){
            return true;
        }
        else {
            return false;
        }
    }
    public void select(int x, int y){
        if (isPieceSelected){
            if (pieceAt(x,y) != null){
                setSelected(x,y);
            }
            else{
                pieceSelected.move(x,y);
                hasMoved = true;
                if (pieceSelected.isBomb() && pieceSelected.hasCaptured()){
                    resetSelected();
                }
                else{
                    setSelected(x,y);
                }
            }
        }
        else{
            setSelected(x,y);
        }
    }
    private void resetSelected(){
        isPieceSelected = false;
        pieceSelectedPosition[0] = -1;
        pieceSelectedPosition[1] = -1;
        pieceSelected = null;
    }
    private void setSelected(int x, int y){
        isPieceSelected = true;
        pieceSelectedPosition[0] = x;
        pieceSelectedPosition[1] = y;
        pieceSelected = pieceAt(x, y);

    }

    public void place(Piece p, int x, int y){
        if (!((x < 0) || (y < 0) || (x > 7) || (y > 7) || (p == null))){
            if (pieceAt(x,y) != null){
                remove(x, y);
            }
            Grid[x][y] = p;
        } 
    }
    public Piece remove(int x, int y){
        if ((x < 0) || (y < 0) || (x > 7) || (y > 7)){
            System.out.println("ERROR: trying to remove outside of bounds");
            return null;        
        }
        else{
            Piece p = Grid[x][y];
            Grid[x][y] = null;
            return p;
        }
    }
    public boolean canEndTurn(){
        return hasMoved;
    }
    public void endTurn(){
        isFireTurn = !isFireTurn;
        hasMoved = false;
        if (isPieceSelected){
            pieceSelected.doneCapturing();
            resetSelected();
        }
    }
    public String winner(){
        int[] num = numPieces();
        if ((num[0] == 0) && (num[1] ==0)){
            return "No one";
        }
        else if (num[0] == 0) {
            return "Water";
        }
        else if (num[1] == 0){
            return "Fire";
        }
        else{
            return null;
        }
    }

    private void drawBoard(){
        int x = -1;
        int y = -1;
        if (isPieceSelected){
            x = pieceSelectedPosition[0];
            y = pieceSelectedPosition[1]; 
        }
        Piece p;
        for (int i= 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((x==i) && (y==j)){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);   
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                p = pieceAt(i, j);
                if (p != null){
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType(p), 1, 1);
                }
            }
        }
    }
    private String pieceType(Piece p){
        if (p.isFire()){
            if(p.isKing()){
                if (p.isBomb()){
                    return "bomb-fire-crowned.png";
                }
                else if (p.isShield()) {
                    return "shield-fire-crowned.png";
                }
                else{// pawn
                    return "pawn-fire-crowned.png";
                }
            }
            else{
                if (p.isBomb()){
                    return "bomb-fire.png";
                }
                else if (p.isShield()) {
                    return "shield-fire.png";
                }
                else{// pawn
                    return "pawn-fire.png";
                }

            }
        }
        else{
            if(p.isKing()){
                if (p.isBomb()){
                    return "bomb-water-crowned.png";
                }
                else if (p.isShield()) {
                    return "shield-water-crowned.png";
                }
                else{// pawn
                    return "pawn-water-crowned.png";
                }
            }
            else{
                if (p.isBomb()){
                    return "bomb-water.png";
                }
                else if (p.isShield()) {
                    return "shield-water.png";
                }
                else{// pawn
                    return "pawn-water.png";
                }
            }
        }
    }
    private int[] numPieces(){
        Piece p;
        int[] num = {0, 0};
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                p = pieceAt(i,j);
                if (p != null){
                    num[0] += 1 - p.side();
                    num[1] += p.side();
                }
            }
        }
        return num;
    }
    public static void main(String args[]){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        int x;
        int y;
        int[] num = {12, 12};
        Board board = new Board(false);
        while((num[0] > 0) && (num[1] > 0)){
            board.drawBoard();
            num = board.numPieces();
            if (StdDrawPlus.mousePressed()){
                x = (int) StdDrawPlus.mouseX();
                y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x,y)){
                    board.select(x,y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if (board.canEndTurn()){
                    board.endTurn();
                }
            }
            StdDrawPlus.show(10);
        }
        System.out.println(board.winner());
    }
}