public class Board{

	private Piece[][] pieces;
    private int turn;
    private boolean[][] selected, moved;
    private boolean step;

	public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
        selected = new boolean[8][8];
        moved = new boolean[8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                selected[i][j] = false;
                moved[i][j] = false;
            }
        }
        turn = 0;
        step = false;
		if (! shouldBeEmpty){
            for (int i = 0; i < 8; i++){
            	if (i % 2 == 0){
            		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            		pieces[i][6] = new Piece(false, this, i, 6, "shield");
            	}
            	else{
            		pieces[i][1] = new Piece(true, this, i, 1, "shield");
            		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            	}
            }
        }
	}

    private static void drawBoard(int n, Piece[][] pieces){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null){
                	if (pieces[i][j].isFire()){
                		if (pieces[i][j].isBomb()){
                			if (pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                		}
                		else if (pieces[i][j].isShield()){
                	    	if (pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                		}
                		else{
                			if (pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                		}          		
                	}
                	else{
                    	if (pieces[i][j].isBomb()){
                			if (pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                		}
                		else if (pieces[i][j].isShield()){
                			if (pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                		}
                		else{
                			if (pieces[i][j].isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                		}
                	}
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x >= 0 && x < 8 && y >= 0 && y < 8 && pieces[x][y] != null){
        	return pieces[x][y];
        }
        else{
        	return null;
        }
    }

    public void place(Piece p, int x, int y){
    	if (x >= 0 && x < 8 && y >= 0 && y < 8 && p != null){
            pieces[x][y] = p;
    	    for (int i = 0; i < 8; i++){
    	    	for (int j = 0; j < 8; j++){
    	    		if (pieces[i][j] == p && i != x && j != y){
    	    			pieces[i][j] = null;
    	    		}
    	    	}
    	    }
    	}
    }

    public Piece remove(int x, int y){
    	if (x < 0 || x >= 8 || y < 0 || y >= 8){
    		System.out.println("(x,y) is out of bounds.");
    		return null;
    	}
    	else if (pieces[x][y] == null){
    		System.out.println("There is no piece at (x,y).");
    		return null;
    	}
    	else{
    		Piece p = pieces[x][y];
    		pieces[x][y] = null;
    		return p;
    	}
    }

    public boolean canSelect(int x, int y){
    	if (x < 0 || x >= 8 || y < 0 || y >= 8){
            return false;
        }
        else if (pieces[x][y] != null){
            if (turn == pieces[x][y].side()){
                return (!step);
            }
            else{
                return false;
            }
    	}
        else{
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    int avgx = (i + x) / 2;
                    int avgy = (j + y) / 2;
                    if (pieces[i][j] != null){
                        if (turn == pieces[i][j].side() && selected[i][j]){
                            if (! moved[i][j]){
                                if (pieces[i][j].isKing()){
                                    return ((Math.abs(i - x) == 1 && Math.abs(j - y) == 1) || (Math.abs(i - x) == 2 && Math.abs(j - y) == 2 && pieces[avgx][avgy] != null && pieces[avgx][avgy].side() != turn));
                                }
                                else if (turn == 0){
                                    return ((Math.abs(i - x) == 1 && (y - j) == 1) || (Math.abs(i - x) == 2 && (y - j) == 2 && pieces[avgx][avgy] != null && pieces[avgx][avgy].side() != turn));
                                }
                                else{
                                    return ((Math.abs(i - x) == 1 && (j - y) == 1) || (Math.abs(i - x) == 2 && (j - y) == 2 && pieces[avgx][avgy] != null && pieces[avgx][avgy].side() != turn));
                                }
                            }
                            else if (pieces[i][j].hasCaptured()){
                                if (pieces[i][j].isKing()){
                                    return (Math.abs(i - x) == 2 && Math.abs(j - y) == 2 && pieces[avgx][avgy] != null && pieces[avgx][avgy].side() != turn);
                                }
                                else if (turn == 0){
                                    return (Math.abs(i - x) == 2 && (y - j) == 2 && pieces[avgx][avgy] != null && pieces[avgx][avgy].side() != turn);
                                }
                                else{
                                    return (Math.abs(i - x) == 2 && (j - y) == 2 && pieces[avgx][avgy] != null && pieces[avgx][avgy].side() != turn);
                                }
                            }
                            else{
                                return false;
                            }
                        }   
                    }
                }
            }
            return false;
        }
    }

    public void select(int x, int y){
        if (pieces[x][y] != null){
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    selected[i][j] = false;
                }
            }
            selected[x][y] = true;
        }
        else{
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (pieces[i][j] != null){
                        if (selected[i][j]){
                            pieces[i][j].move(x, y);
                            selected[i][j] = false;
                        }
                    }
                }
            }
            selected[x][y] = true;
            moved[x][y] = true;
            step = true;
        }
    }

    public boolean canEndTurn(){
        return step;
    }

    public void endTurn(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                selected[i][j] = false;
                moved[i][j] = false;
                if (pieces[i][j] != null){
                    pieces[i][j].doneCapturing();
                }
            }
        }
        turn = 1 - turn;
        step = false;
    }

    public String winner(){
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (pieces[i][j] != null){
                    if (pieces[i][j].isFire()){
                        s1++;
                    }
                    else{
                        s2++;
                    }
                }
            }
        }
        if (s2 == 0 && s1 > 0){
            return "Fire";
        }
        else if (s1 ==0 && s2 > 0){
            return "Water";
        }
        else if (s1 == 0 && s2 == 0){
            return "No one";
        }
        else{
            return null;
        }
    }

    public static void main(String args[]){
		Board board = new Board(false);
        drawBoard(8, board.pieces);
        while (true){
            if (StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)){
                    board.select(x, y);
                }
            }
            drawBoard(8, board.pieces);
            StdDrawPlus.show(1);
            if (StdDrawPlus.isSpacePressed()){
                if (board.canEndTurn()){
                    board.endTurn();
                }
            }
        }
	}
}