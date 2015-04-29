import java.util.Arrays;


public class Board{

    private Piece[][] pieceArray;
    private boolean isFireTurn;
    private Piece selectedPiece;
    private Piece selectedMove;
    private boolean turnMustEnd;
    private boolean moved;
    private boolean captured;
    private int x1, y1, x2, y2;

    public static void main(String args[]){
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board myBoard = new Board(false);
        while(true) {
            myBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double i = StdDrawPlus.mouseX();
                double j = StdDrawPlus.mouseY();
                int x = (int) i;
                int y = (int) j;              
                if (myBoard.canSelect(x, y)){
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                    myBoard.select(x, y);
                }
                StdDrawPlus.show(100);
            }
            if (StdDrawPlus.isSpacePressed()){
                if (myBoard.canEndTurn()){
                    myBoard.endTurn();
                }             
            }
            if (myBoard.winner() != null)       break;
        }       
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece temp = pieceArray[i][j];
                if (temp != null){
                    String type;

                    if (temp.isShield())        type = "shield";
                    else if (temp.isBomb())     type = "bomb";
                    else                        type = "pawn";
                    
                    if (temp.isFire() == true){
                        if (temp.isKing())
                            StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-fire-crowned.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-fire.png", 1, 1);
                    }else{
                        if (temp.isKing())
                            StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-water-crowned.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-water.png", 1, 1);
                    }
                }

            }
        }
        
    }
         
    
    public Board(boolean shouldbeEmpty){
        pieceArray = new Piece[8][8]; 
        if (shouldbeEmpty){
            pieceArray = new Piece[8][8];            
        }else{
            for (int x=0; x<8; x+=2){
                pieceArray[x][0] = new Piece(true, this, x, 0, "pawn");
            }
            for (int x=1; x<8; x+=2){
                pieceArray[x][1] = new Piece(true, this, x, 1, "shield");
            }
            for (int x=0; x<8; x+=2){
                pieceArray[x][2] = new Piece(true, this, x, 2, "bomb");
            }
            for (int x=1; x<8; x+=2){
                pieceArray[x][5] = new Piece(false, this, x, 5, "bomb");
            }
            for (int x=0; x<8; x+=2){
                pieceArray[x][6] = new Piece(false, this, x, 6, "shield");
            }
            for (int x=1; x<8; x+=2){
                pieceArray[x][7] = new Piece(false, this, x, 7, "pawn");
            }
        }
        isFireTurn = true;
        moved = false;
    }


    public Piece pieceAt(int x, int y){
        if (x >= 0 && x < 8 && y >= 0 && y < 8){
            return pieceArray[x][y];
        }
        return null;
    }

    public boolean canSelect(int x, int y){
        if (turnMustEnd)        return false;
        Piece curr = pieceArray[x][y];
        if (x >= 0 && x<8 && y >= 0 && y < 8){
            if (curr==null){
                if (selectedPiece != null){
                    if (!moved && validMove(x1, y1, x, y)){
                        return true;
                    }
                    else if (captured && (Math.abs(x1-x)==2) && validMove(x1, y1, x, y)){
                        return true;   
                    }
                    return false;
                }
            }
            else if ((isFireTurn && curr.isFire()) || (!isFireTurn && !curr.isFire())){                  
                if (selectedPiece != null){
                    return (!moved);
                }
                return true;
            }
        }
        return false;
    }

    private boolean validMove (int xi, int yi, int xf, int yf){
        if (pieceArray[xf][yf] != null){
            return false;
        }

	Piece curr = pieceArray[xi][yi];
        int x = xi - xf;
        int y = yi - yf;
        int xd = 0;
        int yd = 0;
        if (x > 0)  xd = -1;
        if (x < 0)  xd = 1;
        if (y > 0)  yd = -1;
        if (y < 0)  yd = 1;
	Piece middle = pieceArray[xi+xd][yi+yd];

        if (curr != null){
            if (curr.isKing()){
                if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){
                    turnMustEnd = true;
                    return true;
                }
                else if (Math.abs(xi - xf)==2 && Math.abs(yi - yf)==2){
                    return (middle.isFire() != curr.isFire());
                }
            }else if (curr.isFire() && (yf > yi)){
                if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){
                    turnMustEnd = true;
                    return true;
                }
                else if (Math.abs(xi - xf)==2 && Math.abs(yi - yf)==2){
                    return (middle.isFire() != curr.isFire());
                }
            }else if (!curr.isFire() && (yf < yi)){
                if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){
                    turnMustEnd = true;
                    return true;
                }
                else if (Math.abs(xi - xf)==2 && Math.abs(yi - yf)==2){
                    return (middle.isFire() != curr.isFire());
                }
            }                            
	}
        return false;
    }

        
    public void select(int x, int y){
        if (pieceArray[x][y] != null){
            x1 = x;
            y1 = y;
            selectedPiece = pieceArray[x][y];
        }else{
            x2 = x;
            y2 = y;
            selectedMove = pieceArray[x][y];
            selectedPiece.move(x, y);
            moved = true;
            if (selectedPiece.hasCaptured() && multiCap(x, y)){
                captured = true;
                selectedPiece = pieceAt(x, y);
                x1 = x;
                y1 = y;
            }
        }
    }

    private boolean multiCap(int x, int y){
        int ld, lu, ru, rd;
        int yjump;
        for (int i = x - 1; i <= (x+1); i+=2){
            for (int j = y - 1; j <= (y+1); j+=2){
                if (boundcheck(i, j) && (pieceArray[i][j] != null)){
                    if ((boundcheck(i-1, j-1) && validMove(x, y, i-1, j-1)) ||
                         (boundcheck(i-1, j+1) && validMove(x, y, i-1, j+1)) ||
                         (boundcheck(i+1, j-1) && validMove(x, y, i+1, j-1)) ||
                         (boundcheck(i+1, j+1) && validMove(x, y, i+1, j+1))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean boundcheck(int x, int y){
        return (x >= 0 && x<8 && y >= 0 && y < 8);
    }
        
    public void place(Piece p, int x, int y){
        if (x >= 0 && x < 8 && y >= 0 && y < 8){
            pieceArray[x][y] = p;
        } 
    }

    public Piece remove(int x, int y){
        Piece temp = pieceArray[x][y];
        pieceArray[x][y] = null;
        return temp;
    }

    public boolean canEndTurn(){
        return moved;    
    }

    public void endTurn(){
        if (isFireTurn){
            isFireTurn = false;
        }else{
            isFireTurn = true;
        }
        selectedPiece.doneCapturing();
        selectedPiece = null;
        moved = false;
        turnMustEnd = false;
    }

    public String winner(){
        int fire = 0;
        int water = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Piece temp = pieceArray[i][j];
                if (temp != null){
                    if (temp.isFire())      fire++;
                    else                    water++;
                }
            }
        }
        if (water > 0 && fire == 0)         return "Water";
        if (fire > 0 && water == 0)         return "Fire";
        if (fire == 0 && water == 0)        return "No one";
        return null;
    }
}
