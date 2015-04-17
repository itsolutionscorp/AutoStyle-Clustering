

public class Board {
	private static boolean[][] pieces;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    public static void main(String[] args) {
        boolean shouldBeEmpty = false;
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];
        drawBoard(N);
        if (shouldBeEmpty == true){}

        else{
            for (int k = 0; k< N; k++){
                for (int l = 0; l<N; l++){
                    if (k == 0){
                        if (l%2==0){
                            pieces[(int) k][(int) l] = true;
                            StdDrawPlus.picture(l+0.5, k + 0.5, "img/pawn-fire.png",1,1);
                        }
                    }
                    else if(k==1 ){
                        if (l%2!=0){
                            pieces[(int) k][(int) l] = true;
                            StdDrawPlus.picture(l+0.5, k + 0.5, "img/shield-fire.png",1,1);
                        }

                    }    
                    else if (k==2) {
                        if (l%2==0){
                            pieces[(int) k][(int) l] = true;
                            StdDrawPlus.picture(l+0.5, k + 0.5, "img/bomb-fire.png",1,1);
                        }
                    }
                    else if (k==5){
                        if (l%2!=0){
                            pieces[(int) k][(int) l] = true;
                            StdDrawPlus.picture(l+0.5, k + 0.5, "img/bomb-water.png",1,1);
                        }
                    }
                    else if (k==6){
                        if (l%2==0){
                            pieces[(int) k][(int) l] = true;
                            StdDrawPlus.picture(l+0.5, k + 0.5, "img/shield-water.png",1,1);
                        }
                    }
                    else if (k==7){
                        if (l%2!=0){
                            pieces[(int) k][(int) l] = true;
                            StdDrawPlus.picture(l+0.5, k + 0.5, "img/pawn-water.png",1,1);
                        }
                    }
                }    
            }
        }
    }
    public void place (Piece p, int x, int y){
        if (-1<x){
            if (x<8){
                if (p!=null){
                    pieces[(int) x][(int) y] = true;
                }
            }
        }
    }
    public boolean canSelect( int x, int y){
        if (pieces[(int x)][(int)y]!=null){
            
        }
    }

    public Piece pieceAt(int x, int y){
        if (pieces[x][y]!=null){
            Piece selected=pieces[(int) x][(int) y];
        }
    }
}
       