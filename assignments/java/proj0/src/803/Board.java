//Brian Sang
public class Board{

    private final static int SIZE = 8;
    private Piece[][] pieces;
    private Piece selected;
    private boolean isFireTurn;
    private boolean hasMoved;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                Piece curr = pieces[i][j];
                if(curr == selected && selected != null){
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(curr != null) {
                    String path = filePath(curr);
                    StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
                }
            }
        }
    }
    //Returns a string representing the img filepath associated with a piece
    private String filePath(Piece p){
        StringBuilder path = new StringBuilder("img/");
        path.append(typePath(p));
        path.append(".png");
        return path.toString();
    }
    //Helper method for filePath(Piece p)
    private String typePath(Piece p){
        StringBuilder path = new StringBuilder();
        if(p.isBomb()){
            path.append("bomb");
        }
        else if(p.isShield()){
            path.append("shield");
        }
        else{
            path.append("pawn");
        }
        if(p.isFire()){
            path.append("-fire");
        }
        else{
            path.append("-water");
        }
        if(p.isKing()){
            path.append("-crowned");
        }
        return path.toString();

    }
    public Board(boolean shouldBeEmpty){
        pieces = new Piece[SIZE][SIZE];
        isFireTurn = true;
        hasMoved = false;
        selected = null;
        if(!shouldBeEmpty){
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    if((i + j) % 2 == 0 && j != 3){
                        Piece p = null;
                        boolean isFire = false;
                        if(j < 4){
                            isFire = true;
                        }
                        if(j == 0 || j == 7){
                            p = new Piece(isFire, this, i, j, "pawn");
                        }
                        else if(j == 1 || j == 6){
                            p = new Piece(isFire, this, i, j, "shield");
                        }
                        else if(j == 2 || j == 5){
                            p = new Piece(isFire, this, i, j, "bomb");
                        }
                        pieces[i][j] = p;
                    }
                }
            }
        }

    }

    //returns array containing x,y coord of piece if it is found, otherwise return null
    private int[] findPiece(Piece p){ 
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(p == pieces[i][j]){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public Piece pieceAt(int x, int y){
        if(x >= SIZE || y >= SIZE || x < 0 || y < 0){
            return null;
        }
        else{
            return pieces[x][y];
        }
    }

    public void place(Piece p, int x, int y){
        if(x >= SIZE || y >= SIZE || x < 0 || y < 0 || p == null){
            return;
        }
        if(pieceAt(x, y) != null){
            remove(x, y);
        }
        if(selected != null){
            hasMoved = true;
        }
        pieces[x][y] = p;

    }

    public Piece remove(int x, int y){
        if(x >= SIZE || y >= SIZE || x < 0 || y < 0){
            System.out.println("Point is out of bounds.");
            return null;
        }
        Piece r = pieceAt(x, y);
        if(r == null){
            System.out.println("There is no piece at that location");
            return null;
        }
        pieces[x][y] = null;
        return r;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece p = pieceAt(xi, yi);
        if(xf > SIZE || yf > SIZE || xf < 0 || yf < 0){
            return false;
        }
        if(pieceAt(xf, yf) != null){
            return false;
        }
        //first make sure move in right direction
        if(!p.isKing()){
            if(p.isFire()){
                if(yf - yi < 0){
                    return false;
                }
            }
            else if (!p.isFire()){
                if(yf - yi > 0){
                    return false;
                }
            }
        }
        //see if capture possible
        if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2){
            Piece j = pieceAt((xi+xf)/2, (yi+yf)/2);
            return (j != null && p.isFire() != j.isFire());
        }
        ///see if just a move possible
        if(Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && !selected.hasCaptured()){
            return true;
        }
        return false;
    }

    public boolean canSelect(int x, int y){
        Piece p = pieceAt(x, y);
        if(p != null){
            //can only select a piece if you haven't moved yet
            if(isFireTurn == p.isFire()){
                return (!hasMoved);
            }
            return false;
        }
        else{
            int[] locs = findPiece(selected);
            if(selected != null && !hasMoved){
                return validMove(locs[0], locs[1], x, y);
            }
            return (selected != null && selected.hasCaptured() 
                    && validMove(locs[0], locs[1], x, y));
        }
    }

    public void select(int x, int y){
        Piece p = pieceAt(x, y);
        if(p != null){
            selected = p;
        }
        else{
            //move to empty square
            selected.move(x, y);
            //unselect bomb after explosion
            if(findPiece(selected) == null){
                selected = null;
            }
        }
    }

    public boolean canEndTurn(){
        return hasMoved;
    }

    public void endTurn(){
        hasMoved = false;
        //I auto unselect bombs if they explode, so that's why this check is needed
        if(selected != null){
            selected.doneCapturing();
        }
        selected = null;
        isFireTurn = !isFireTurn;
    }

    public String winner(){
        int[] nums = countPieces();
        if(nums[0] == 0 && nums[1] == 0){
            return "No one";
        }
        if(nums[0] == 0){
            return "Water";
        }
        if(nums[1] == 0){
            return "Fire";
        }
        return null;
    }

    //Counts the pieces on the grid, 0 index fire and 1 index water
    //Could have created new data structures instead of writing this, but saves memory
    private int[] countPieces(){
        int[] sums = new int[]{0,0};
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                Piece p = pieceAt(i, j);
                if(p != null){
                    if(p.isFire()){
                        sums[0]++;
                    }
                    else{
                        sums[1]++;
                    }
                }
            }
        }
        return sums;
    }

    public static void main(String[] args){
        StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);
        Board b = new Board(false);
        while(true){
            b.drawBoard(SIZE);
            if(b.winner() != null){
                System.out.println(b.winner());
                return;
            }
            if(StdDrawPlus.mousePressed()){
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if(b.canSelect(x, y)){
                    b.select(x, y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }
            StdDrawPlus.show(25);
        }
    }
}