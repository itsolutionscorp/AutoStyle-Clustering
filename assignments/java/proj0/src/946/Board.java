public class Board{

    private boolean fireTurn = true, selected = false, moved = false;
    private Piece[][] pieces = new Piece[8][8];
    private int xsel = -1, ysel = -1;
    private String winner = "";
    private int fire = 0, water = 0;

	/*starts a GUI supported version of the game*/
	public static void main(String[] args) {
        Board b = new Board(false);  
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(b.winner() == null) { 
            boolean click = StdDrawPlus.mousePressed();
            if (click){
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y)){
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                }
            }  
            b.drawBoard();
            StdDrawPlus.show(100);
        }
	}

	/*Check if the Board is empty
	if it is, initialize an empty board.
	Otherwise, initializes a board with a default configuration*/

    private void drawBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (xsel == i && ysel == j) StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                String pic = "img/";
                if (pieces[i][j] != null) {
                    if(pieces[i][j].isShield()) pic = pic + "shield";
                    else if(pieces[i][j].isBomb()) pic = pic + "bomb";
                    else pic = pic + "pawn";
                    if(pieces[i][j].isFire()) pic = pic + "-fire";
                    else pic = pic + "-water";
                    if(pieces[i][j].isKing()) pic = pic + "-crowned";
                    pic = pic + ".png";
                    StdDrawPlus.picture(i + .5, j + .5, pic, 1, 1);
                }
            }
        }
    }

	public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty == true){
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j ++){
                    pieces[i][j] = null;
                }
            }
        }
        else{
            fire = 12;
            water = 12;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (j == 0 && i%2 == 0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    else if (j == 7 && i%2 == 1) pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    else if (j == 1 && i%2 == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
                    else if (j == 6 && i%2 == 0) pieces[i][j] = new Piece(false, this, i, j, "shield");
                    else if (j == 2 && i%2 == 0) pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    else if (j == 5 && i%2 == 1) pieces[i][j] = new Piece(false, this, i, j, "bomb");
                }
            }
        }
	}

	/*Get Piece at postition x,y, or return null*/
	public Piece pieceAt(int x, int y){
		if (x > 7 || y > 7 || x < 0 || y < 0 || pieces[x][y] == null){
			return null;
		}
		return pieces[x][y];
	}

	/*Returns true if the square at x,y can be selected*/
	public boolean canSelect(int x, int y){
        if(pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn){
            if (!selected){
                return true;
            }
            else{
                if(!moved){
                    return true;
                }
            }
        }
        else{
            if(!moved){
                return validMove(xsel, ysel, x, y);
            }
            else{
                if(pieceAt(xsel, ysel) != null && pieceAt(xsel, ysel).hasCaptured() && Math.abs(x - xsel) == 2 && Math.abs(y - ysel) == 2){
                    return validMove(xsel, ysel, x, y);
                }
            }
        }
		return false;
	}

	/*Check if a piece can make a valid move*/
	private boolean	validMove(int xi, int yi, int xf, int yf){
        int xm = (xi + xf) / 2;
        int ym = (yi + yf) / 2;
        Piece ini = pieceAt(xi, yi);
        Piece fin = pieceAt(xf, yf);
        Piece mid = pieceAt(xm, ym);
        if(xf < 8 && yf < 8 && xf > -1 && yf > -1 && xi > -1 && yi > -1){
            if (Math.abs(xf - xi) == 1){
                if (fin == null){
                    if (ini.isKing() && Math.abs(yf - yi) == 1) return true;
                    else if (ini.isFire() && (yf - yi) == 1) return true;
                    else if (!ini.isFire() && (yi - yf) == 1) return true;
                }
            }
            else if (Math.abs(xf - xi) == 2){
                if (mid != null && mid.isFire() != ini.isFire() && fin == null){
                    if (ini.isKing() && Math.abs(yf - yi) == 2) return true;
                    else if (ini.isFire() && (yf - yi) == 2) return true;
                    else if (!ini.isFire() && (yi - yf) == 2) return true;
                }
            }
        }

        return false;
	}

	/*Select the square at(x,y). assumes canSelect returns True
	  color the background white*/
	public void select(int x, int y){
        selected = true;
        if (pieceAt(x, y) == null){
            moved = true;
            pieces[xsel][ysel].move(x, y);
        }
        xsel = x;
        ysel = y;
	}

	/*Place a piece at point x,y. Can replace the original piece*/
	public void place(Piece p, int x, int y){
        pieces[x][y] = p;
        if(pieces[x][y].isFire()) fire = fire + 1;
        else water = water + 1;
	}
    
    /*Remove a piece and return the piece
    if out of bound, return message
    if nothing is there, return message*/
	public Piece remove(int x, int y){
		if (x > 7 || y > 7 || x < 0 || y < 0){
            System.out.println("Your are out of bound, noob");
            return null;
        }
        if (pieces[x][y] == null){
            System.out.println("haha, nothing is there");
            return null;
        }
        else{
            if(pieces[x][y].isFire()) fire = fire - 1;
            else water = water - 1; 
            Piece temp = pieces[x][y];
            pieces[x][y] = null;
            return temp;
        } 
	}

	/*Return if the player can end their turn
	a piece must be moved or perform a capture*/
	public boolean canEndTurn(){
		return moved;
	}

	/*Call end of a turn, handle everything*/
	public void endTurn(){
        moved = false;
        selected = false;
        fireTurn = !fireTurn;
        if (pieceAt(xsel, ysel) != null) pieceAt(xsel, ysel).doneCapturing();
        xsel = -1;
        ysel = -1;
	}

	/*Print out the winner of the game*/
	public String winner(){
        if (water == 0 && fire == 0) {
            winner = "No one";
            return winner;
        }
        else if(water == 0) {
            winner = "Fire";
            return winner;
        }
        else if(fire == 0) {
            winner = "Water";
            return winner;
        }
		else return null;
        
	}
}