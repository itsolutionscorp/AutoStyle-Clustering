/**
 * @author Yufeng Gu
 */

public class Board{

	public static Piece [][] piecep;
    public static Piece [][] selected;
	public static boolean[][] pieces;
    public static boolean endturn = false;
    public static boolean can = true;
    public static boolean emptyboard;
    public static boolean player1 = true;
    public static boolean player2 = false;
	public static String[][] piecesName;
    public static String[][] pieceTW;
    public static String[][] boardColor;
    public static int xx;
    public static int yy;
    public static int N = 8;

	public static void main(String[] args){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N); 
        Board board = new Board(emptyboard);
        drawBlankBoard(N);
        drawStartBoard(N,board);
        while(true){
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xi =(int)(x);
                int yi =(int)(y);
                while (canSelect(xi,yi)){
                	select(xi,yi);
                    if(StdDrawPlus.mousePressed()){
                    selected[xx][yy].move(xi,yi);
                    drawBoard(N);
                }
                }   
                if (canEndTurn() && StdDrawPlus.isSpacePressed()) {
                    endTurn();
                }      
            StdDrawPlus.show(15);
        	}
		}
	}

	public Board (boolean shouldBeEmpty){

		emptyboard = shouldBeEmpty;
        this.endturn = endturn;
        piecep = new Piece[N][N];
        pieces = new boolean[N][N];
        piecesName = new String [N][N];
        boardColor = new String [N][N];
        selected = new Piece[N][N];
        pieceTW = new String[N][N];

	}

    private static void drawBlankBoard(int N){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    boardColor[i][j] = "gray";
                    }
                else {               
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    boardColor[i][j] = "red";
                    }   
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private static void drawStartBoard(int N,Board newBoard){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if(j==7 && (i+j)%2 == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        pieces[i][j]= false;
                        piecesName[i][j] = "pawn-water";
                        pieceTW[i][j] = "water";
                        piecep[i][j] = new Piece(false,newBoard,i,j,"pawn");
                    }
                if(j==6 && (i+j)%2 == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        pieces[i][j]= false;
                        piecesName[i][j] = "shield-water";
                        pieceTW[i][j] = "water";
                        piecep[i][j] = new Piece(false,newBoard,i,j,"shield");
                    }
                if(j==5 && (i+j)%2 == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        pieces[i][j]= false;
                        piecesName[i][j] = "bomb-water";
                        pieceTW[i][j] = "water";
                        piecep[i][j] = new Piece(false,newBoard,i,j,"bomb");

                    }
                if(j==2 && (i+j)%2 == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        pieces[i][j]= true;
                        piecesName[i][j] = "bomb-fire";
                        pieceTW[i][j] = "fire";
                        piecep[i][j] = new Piece(true,newBoard,i,j,"bomb");

                    }
                if(j==1 && (i+j)%2 == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        pieces[i][j]= true;
                        piecesName[i][j] = "shield-fire";
                        pieceTW[i][j] = "fire";
                        piecep[i][j] = new Piece(true,newBoard,i,j,"shield");

                    }
                if(j==0 && (i+j)%2 == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        pieces[i][j]= true;
                        piecesName[i][j] = "pawn-fire";
                        pieceTW[i][j] = "fire";
                        piecep[i][j] = new Piece(true,newBoard,i,j,"pawn");
                    }
            }
        }
    }

    private static void drawBoard(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (piecep[i][j]!= null){
                    if (piecep[i][j].isFire()){
                        if(piecep[i][j].isBomb()){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    }
                        else if(piecep[i][j].isShield()){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    }
                        else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                    if (piecep[i][j].isFire()==false){
                        if(piecep[i][j].isBomb()){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    }
                        else if(piecep[i][j].isShield()){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
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


	public static Piece pieceAt(int x, int y){
		if (piecep[x][y]!= null){
            return piecep[x][y];
		}
		else{
			return null;
		}
	}


	public static boolean canSelect(int x, int y){

        if (pieces[x][y] && !(canEndTurn())){
            return true;

        }
        else if(piecep[x][y] == null && (x+y)%2 == 0){
            if (piecep[x][y].isFire() && Math.abs(x-xx) ==1 && y - yy ==1){
                return true;
            }
            else if (piecep[x][y].isFire()== false && Math.abs(x-xx) ==1 && y - yy ==-1){
                return true;
            }
            else if (piecep[x][y].isFire()== false && x-xx ==2 && y - yy ==-2 && pieceTW[x+1][y+1]== "fire"){
                return true;
            }
            else if (piecep[x][y].isFire()== false && x-xx ==-2 && y - yy ==-2 && pieceTW[x-1][y+1]== "fire"){
                return true;
            }
            else if(piecep[x][y].isFire() && x-xx ==2 && y - yy ==2 && pieceTW[x-1][y-1]== "water"){
                return true;
            }
            else if(piecep[x][y].isFire() && x-xx ==-2 && y - yy ==2 && pieceTW[x+1][y-1]== "water"){
                return true;
            }
            else{
                return false;
            }
        }

        else{
            return false;
        }
    }


    public static void select(int x, int y){

        if(pieces[x][y]== true){ 
            selected[x][y] = pieceAt(x,y);
            xx = x;
            yy = y;
        }
        if (piecep[x][y]== null){
            place(selected[xx][yy],x,y);
        }
    }

	public static void place(Piece p, int x, int y){
        if (x>=0 && x <=7 && y>=0 && y <= 7 && p!=null){
            piecep[x][y]= p;
            pieces[x][y] = true;
            if (p.isFire()){
                pieceTW[x][y] = "fire";
            if (p.isFire() == false){
                pieceTW[x][y] = "water";
            }
            }
        }
    }

    public static Piece remove(int x, int y){
        if (x>=0 && x <=7 && y>=0 && y <= 7 && piecep[x][y]!=null){
            Piece temPiece = pieceAt(x,y);
            piecep[x][y]= null;
            pieces[x][y]= false;
            pieceTW[x][y] = null;
            return temPiece;
        }
        else if (x <=0 ||x >=7 || y <= 0 || y >= 7){
            return null;
        }
        else{
            return null;        
        }
    }

    public static boolean canEndTurn(){
        if (endturn == true){
            return true;
        }
        else{
            return false;
        }

    }

    public static void endTurn(){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (piecep[i][j]==null){
                    pieces[i][j] = false;
                }
                if (piecep[i][j] != null){
                    pieces[i][j]=!(pieces[i][j]);
                }
            }
        }

    }

   public static String winner() {
    int firewin =0;
    int waterwin =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceTW[i][j]== "fire"){
                    firewin=firewin+1;
                }
                if (pieceTW[i][j]== "water"){
                    waterwin =waterwin +1;
                }
            }
        }
    if (waterwin==0 && firewin!=0){
        return "fire wins";
    }
    else if (waterwin!=0 && firewin==0){
        return "water wins";
    }
    else{
        return null;
    }

   }
}
