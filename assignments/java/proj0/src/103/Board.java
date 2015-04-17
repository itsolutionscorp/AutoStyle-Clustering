
public class Board {
    private int size = 8;
    private Piece[][] pieces = new Piece[size][size];
    private boolean isFireTurn;
    private int selectedX;
    private int selectedY;
    private int numFirePieces;
    private int numRainPieces;
    private boolean hasMoved;

    
    public Board(boolean shouldBeEmpty){
        isFireTurn = true;
        if (!shouldBeEmpty){
            initialize(0, "pawn", true);
            initialize(1, "shield", true);
            initialize(2, "bomb", true);
            
            initialize(5, "bomb", false);
            initialize(6, "shield", false);
            initialize(7, "pawn", false);
            
            numFirePieces = 12;
            numRainPieces = 12;
        }
    }
    
    private void initialize(int row, String type, boolean isFire) {
           if (row == 1 || row == 5 || row == 7){
               for (int i = 0; i < size; i+=1){
                   if (i % 2 != 0){
                    Piece p = new Piece(isFire, this, i, row, type);
                    this.place(p, i, row);
                   }
               }
           }
           else {
                for (int i = 0; i < size; i+=1){
                    if (i % 2 == 0){
                        Piece p = new Piece(isFire, this, i, row, type);
                        this.place(p, i, row);
                    }
                }
            }
    }
    
    public Piece pieceAt(int x, int y){
        if (x >= size || y >= size || pieces[x][y] == null){
            return null;
        }
        return pieces[x][y];
    }
    
    public boolean canSelect(int x, int y) {
       if (!hasMoved) {
           if (pieceAt(x,y) != null && pieceAt(x,y).isFire() == isFireTurn){
               return true;
           }
           if (pieces[x][y] == null){
               if (validMove(selectedX, selectedY, x, y)) {
                   return true;
               }
           }
       }
       return validMove(selectedX, selectedY, x, y);
    }
    
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if(pieceAt(xi, yi) == null || pieceAt(xi, yi).isFire() != isFireTurn || pieceAt(xf, yf) != null){
            return false;
        }
        
        //multicapture
        if (hasMoved){
            if (pieceAt(xi, yi).isKing()) {
                if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi - 2)) {
                    if (pieceAt((int)((xi + xf)/2), (int)((yi + yf)/2)) != null) {
                        return (pieceAt((int)((xi + xf)/2), (int)((yi + yf)/2)).isFire() != isFireTurn);
                    }
                }
            }
            if(pieceAt(xi, yi).isFire()) {
                if (yf == yi + 2) {
                    return (pieceAt((int)((xi+xf)/2),yi + 1) != null && pieceAt((int)((xi+xf)/2),yi + 1).isFire() != isFireTurn);
                }
            }
            else if (!pieceAt(xi, yi).isFire()) {
                if (yf == yi - 2) {
                    return (pieceAt((int)((xi+xf)/2),yi - 1) != null && pieceAt((int)((xi+xf)/2),yi - 1).isFire() != isFireTurn);
                }
            }
            return false;
        }
        
        
        if (pieceAt(xi, yi).isKing()){
            if (pieceAt(xf, yf) == null && (xf == xi+1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)){
                return true;
            }
            if ((xf == xi + 2 || xf == xi - 2) && (yf == yi + 2 || yf == yi - 2)) {
                if (pieceAt((int)((xi + xf)/2), (int)((yi + yf)/2)) != null) {
                    return (pieceAt((int)((xi + xf)/2), (int)((yi + yf)/2)).isFire() != isFireTurn);
                }
            }
        }
        else {
            if(pieceAt(xi, yi).isFire()) {
                if (pieceAt(xf, yf) == null && yf == yi + 1 && (xf == xi + 1 || xf == xi - 1)) {
                    return true;
                }
                else if (yf == yi + 2) {
                    return (pieceAt((int)((xi+xf)/2),yi + 1) != null && pieceAt((int)((xi+xf)/2),yi + 1).isFire() != isFireTurn);
                }
            }
            else if (!pieceAt(xi, yi).isFire()) {
                if (pieceAt(xf, yf) == null && yf == yi - 1 && (xf == xi + 1 || xf == xi - 1)) {
                    return true;
                }
                else if (yf == yi - 2) {
                    return (pieceAt((int)((xi+xf)/2),yi - 1) != null && pieceAt((int)((xi+xf)/2),yi - 1).isFire() != isFireTurn);
                }
            }
        }
        return false;
    }
    
    public void select(int x, int y) {
        // if you've selected a piece previously and want to move it to an empty space
        if (!hasMoved && pieceAt(x,y) != null){
            selectedX = x;
            selectedY = y;
            return;
        }
        if (pieceAt(selectedX, selectedY) != null && pieceAt(x,y) == null) {
            pieceAt(selectedX,selectedY).move(x, y);
            //pieceAt(x,y).doneCapturing();
            hasMoved = true;
            selectedX = x;
            selectedY = y;
            return;
        }
        

            //TODO: color background of selected square white
        
    }
    
    public void place(Piece p, int x, int y) {
        if (x >= size || y >= size){
            return;
        }
        if (p.isFire()){
            numFirePieces += 1;
        }
        else if (!p.isFire()){
            numRainPieces += 1;
        }
        pieces[x][y] = p;
        
    }
    
    public Piece remove(int x, int y){
        if (x >= size || y >= size) {
            System.out.println("Out of bounds.");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("Board does not contain a piece at this location.");
            return null;
        }
        Piece old = pieceAt(x,y);
        if (old.isFire()){
            numFirePieces -= 1;
        }
        else if (!old.isFire()) {
            numRainPieces -= 1;
        }
        pieces[x][y] = null;
        return old;
        
    }
    
    public boolean canEndTurn(){
        return hasMoved;
    }
    
    public void endTurn() {
        isFireTurn = !isFireTurn;
        hasMoved = false;
        
        // DONE CAPTURING????
        if(pieceAt(selectedX, selectedY)!= null){
            pieceAt(selectedX,selectedY).doneCapturing();

        }
    }
    
    public String winner(){
        if (numFirePieces > 0 && numRainPieces > 0){
            return null;
        }
        if (numFirePieces == 0 && numRainPieces > 0){
            return "Water";
        }
        if (numFirePieces > 0 && numRainPieces == 0){
            return "Fire";
        }
        return "No one";
        
    }
    
    /**
     * GUI METHODS
     */
    private String getPieceImage(Piece p){
        String result = "img/";
        if (p.isBomb()) result += "bomb";
        if (p.isShield()) result += "shield";
        if (!p.isBomb() && !p.isShield()) result += "pawn";
        if (p.isFire()) result += "-fire";
        if (!p.isFire()) result += "-water";
        if (p.isKing()) result += "-crowned";
        return result+".png";
    }
    
    private static void drawBoard(Board b){
        for (int i = 0; i < b.size; i++) {
            for (int j = 0; j < b.size; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, b.getPieceImage(b.pieceAt(i,j)), 1, 1);
                }
            }
        }
    }
    
    public static void main(String[] args){
        Board b = new Board(false);
//        Piece p = new Piece(true, b, 0, 0, "pawn");
//        Piece q = new Piece(false, b, 1, 1, "pawn");
//        b.place(p, 0, 0);
//        b.place(q, 1, 1);
        StdDrawPlus.setXscale(0, b.size);
        StdDrawPlus.setYscale(0, b.size);
        while(true){
            drawBoard(b);
            if (StdDrawPlus.mousePressed()){
                int x = (int)(StdDrawPlus.mouseX());
                int y = (int) (StdDrawPlus.mouseY());
                //System.out.println("clicked" +  x + y);
                if (b.canSelect(x, y)){
                    //System.out.println("canselect");
                    b.select(x, y);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                }
            }
            else if (StdDrawPlus.isSpacePressed()){
                //System.out.println(" " + b.canEndTurn());
                if (b.canEndTurn()) b.endTurn();
            }
            else if(b.winner() != null) return;
            StdDrawPlus.show(50);
        }
    }
}
