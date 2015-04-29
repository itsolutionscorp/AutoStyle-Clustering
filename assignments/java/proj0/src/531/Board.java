import java.util.Arrays;
public class Board{

    private Piece[][] pieces = new Piece[8][8];
    private boolean player1 = true;
    private boolean player2 = false;
    private boolean hasMovedPlayer1 = false;
    private boolean hasMovedPlayer2 = false;
    private int fire = 12;
    private int water = 12;
    private boolean selected = false;
    private int xPosition;
    private int yPosition;
    private boolean captured = false;
    private static int xClick;
    private static int yClick;
    private Piece selectedPiece = null;
    




	public static void main(String args[]){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        Board board = new Board(false);
        board.drawBoard();
        board.player1 = true;
        board.player2 = false;
        board.hasMovedPlayer1 = false;
        board.hasMovedPlayer2 = false;
        board.fire = 12;
        board.water = 12;
        board.selected = false;
        board.captured = false;
        board.selectedPiece = new Piece(true, board, 0, 0, "pawn");
    

        while (board.fire > 0 && board.water > 0){
            while (board.player1 == true){
                if(StdDrawPlus.mousePressed()) {
                    board.xClick = (int)StdDrawPlus.mouseX();
                    board.yClick = (int)StdDrawPlus.mouseY();
                    if (board.canSelect(xClick, yClick) == true) board.select(xClick, yClick);
                    while ((board.hasMovedPlayer1 == false) || ((board.hasMovedPlayer1 == true) && (board.captured == true))) {
                        if(StdDrawPlus.mousePressed()) {
                            board.xClick = (int)StdDrawPlus.mouseX();
                            board.yClick = (int)StdDrawPlus.mouseY();
                            if (board.canSelect(xClick, yClick) == true) board.selectedPiece.move(xClick, yClick);
                            if (board.selectedPiece.hasCaptured() == true){
                                board.remove((board.xPosition + xClick) / 2, (board.yPosition + yClick) / 2);
                                board.hasMovedPlayer1 = true;
                            }else 
                            board.hasMovedPlayer1 = true;
                            board.hasMovedPlayer1 = false;
                            board.selected = false;
                            board.selectedPiece = null;
                            board.captured = false;
                            board.player2 = true;
                            board.player1 = false;
                    }
                }
            }
                
            }
            while (board.player2 == true){
                if(StdDrawPlus.mousePressed()) {
                    board.xClick = (int)StdDrawPlus.mouseX();
                    board.yClick = (int)StdDrawPlus.mouseY();
                    if (board.canSelect(xClick, yClick) == true) board.select(xClick, yClick);
                    while ((board.hasMovedPlayer2 == false) || ((board.hasMovedPlayer2 == true) && board.captured == true)) {
                        if(StdDrawPlus.mousePressed()) {
                            board.xClick = (int)StdDrawPlus.mouseX();
                            board.yClick = (int)StdDrawPlus.mouseY();
                            if (board.canSelect(xClick, yClick) == true) board.selectedPiece.move(xClick, yClick);
                            if (board.selectedPiece.hasCaptured() == true){
                                board.remove((board.xPosition + xClick) / 2, (board.yPosition + yClick) / 2);
                                board.hasMovedPlayer2 = true;
                            }else board.hasMovedPlayer2 = true;
                            board.hasMovedPlayer2 = false;
                            board.selected = false;
                            board.selectedPiece = null;
                            board.captured = false;
                            board.player1 = true;
                            board.player2 = false;
                    }
                }
            }
            }
            return; /*return the winning message*/
        }
    }
        

    private void drawBoard(){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null){
                    if (pieces[i][j].side() == 0){
                        if (pieces[i][j].isBomb() == true && pieces[i][j].isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        else if (pieces[i][j].isBomb() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        else if (pieces[i][j].isShield() == true && pieces[i][j].isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        else if (pieces[i][j].isShield() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        else if (pieces[i][j].isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }else{
                        if (pieces[i][j].isBomb() == true && pieces[i][j].isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        else if (pieces[i][j].isBomb() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        else if (pieces[i][j].isShield() == true && pieces[i][j].isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        else if (pieces[i][j].isShield() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        else if (pieces[i][j].isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);

                    }
                }
            }
        }
    }




    
    
        




	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == false){
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
                    if ((j == 7) && (i % 2 == 1)){
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }else if ((j == 6) && (i % 2 == 0)){
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }else if ((j == 5) && (i % 2 == 1)){
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }else if ((j == 0) && (i % 2 == 0)){
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }else if ((j == 1) && (i % 2 == 1)){
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }else if ((j == 2) && (i % 2 == 0)){
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                }
            }

           }

    }


                	








	public Piece pieceAt(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return null;
        }else if (pieces[x][y] == null){
            return null;
        }else{
            return pieces[x][y];
        }
    }

	public boolean canSelect(int x, int y){
        if (pieces[x][y] == null && selected == false) return false;
        else if (pieces[x][y] != null && selected == false) {
            if (pieces[x][y].isFire() == true && player1 == true) return true;
            else if (pieces[x][y].isFire() == false && player2 == true) return true;
            else return false;
        }
        else if (pieces[x][y] != null && selected == true && ((player1 == true && hasMovedPlayer1 == false) || (player2 == true && hasMovedPlayer2 == false))) return true;
        else if (pieces[x][y] == null && selected == true && validMove(xPosition, yPosition, x, y) == true) return true;
        else if (pieces[x][y] == null && selected == true && captured == true && validMove(xPosition, yPosition, x, y) == true) return true;
        else return false;
    }


    private boolean validMove(int x1, int y1, int x2, int y2){
        if (x1 < 0 || x1 > 7 || x2 < 0 || x2 > 7 || y1 < 0 || y1 > 7 || y2 < 0 || y2 > 7 || x1 == x2 || y1 == y2) return false;
        else if (pieces[x2][y2] != null) return false;
        else if (pieces[x1][y1] == null) return false;
        else if (pieces[x1][y1].isFire() == true){
            if (y2 < y1){
                if (pieces[x1][y1].isKing() == false) return false;
                else if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) return true;
                else if (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 2){
                    if (x1 > x2){
                        if (pieces[x1 - 1][y1 - 1].isFire() == false) return true; /*IF FAIL ADD CASE WHERE SEES IF PIECE EXISTS*/
                        else return false;
                    }else if (pieces[x1 + 1][y1 - 1].isFire() == false) return true;
                    else return false;
                    }
                }else{
                    if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) return true;
                    else if (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 2){
                        if (x1 > x2){
                            if (pieces[x1 - 1][y1 + 1].isFire() == false) return true; /*IF FAIL ADD CASE WHERE SEES IF PIECE EXISTS*/
                            else return false;
                        }else if (pieces[x1 + 1][y1 + 1].isFire() == false) return true;
                        else return false;
                    }
                }
        }else if (pieces[x1][y1].isFire() == false){
            if (y2 > y1){
                if (pieces[x1][y1].isKing() == false) return false;
                else if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) return true;
                else if (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 2){
                    if (x1 > x2){
                        if (pieces[x1 - 1][y1 + 1].isFire() == true) return true; /*IF FAIL ADD CASE WHERE SEES IF PIECE EXISTS*/
                        else return false;
                    }else if (pieces[x1 + 1][y1 + 1].isFire() == true) return true;
                    else return false;
                    }
                }else{
                    if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) return true;
                    else if (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 2){
                        if (x1 > x2){
                            if (pieces[x1 - 1][y1 + 1].isFire() == false) return true; /*IF FAIL ADD CASE WHERE SEES IF PIECE EXISTS*/
                            else return false;
                        }else if (pieces[x1 + 1][y1 + 1].isFire() == false) return true;
                        else return false;
                    }
                }
            }
        return false;
    }

        


	public void select(int x, int y){
        xPosition = x;
        yPosition = y;
        selectedPiece = pieces[x][y];
        selected = true;
        /*CASE FOR AFTER CATCHING*/
    }

	public void place(Piece p, int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return;

        }else if (p != null){
            pieces[x][y] = p;
            p = null;
            drawPiece(pieces[x][y], x, y);
        }
    }

	public Piece remove(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            System.out.println("Range is out of bounds.");
            return null;
        }else if (pieces[x][y] == null){
            System.out.println("There is no piece.");
            return null;
        }Piece result = pieces[x][y];
        if (pieces[x][y].isFire() == true){
            fire = fire - 1;
        }else{
            water = water - 1;
        }
        pieces[x][y] = null;
        if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        return result;

	}

    private void drawPiece(Piece p, int i, int j) {
        if (p.isFire() == true){
            if (p.isBomb() == true && p.isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
            else if (p.isBomb() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            else if (p.isShield() == true && p.isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
            else if (p.isShield() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            else if (p.isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        }else{
            if (p.isBomb() == true && p.isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
            else if (p.isBomb() == true) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            else if (p.isShield() == true && p.isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
            else if (p.isShield() == true) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            else if (p.isKing() == true) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
        }
    }

	public boolean canEndTurn(){
        if (hasMovedPlayer1 == true && player1 == true && captured == false) return true;
        else if (hasMovedPlayer2 == true && player2 == true && captured == false) return true;
        else return false;
    }

	

	public void endTurn(){
            if (player1 == true){
                hasMovedPlayer1 = false;
                selected = false;
                selectedPiece = null;
                captured = false;
                player1 = false;
                player2 = true;
            }else{
                hasMovedPlayer2 = false;
                selected = false;
                selectedPiece = null;
                captured = false;
                player1 = true;
                player2 = false;
            }
        }


	public String winner(){
        if (fire == 0 && water == 0){
            return("No one");
        }else if (fire == 0 ){
            return("Water");
        }else if (water == 0){
            return("Fire");
        }else{
            return null;
    }

}
}

