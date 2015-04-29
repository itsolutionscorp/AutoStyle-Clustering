import java.lang.Math;

public class Board{
	private Piece[][] pieces = new Piece[8][8];
    private boolean fireTurn = true;
    private Piece selected = null;
    private Board b;
    private String winner = null;
    private boolean hasMoved = false;
    private int selectedX;
    private int selectedY;
	public static void main (String args[]){
            Board b  = new Board(false);
            b.drawBoard(8);
            // b.setPictures();
            while(true) {
                if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    if(b.canSelect((int) x, (int) y)){
                        b.select((int) x, (int) y);
                    }
                    b.drawBoard(8);
                    // b.setPictures();

                // b.drawBoard(8);    
                }   
                if(StdDrawPlus.isSpacePressed()){
                    if(b.canEndTurn()){
                        b.endTurn();
                    }
                }         
                // StdDrawPlus.show(100);
        }



            // StdDrawPlus.show(100);
            // setPictures();
	}
	public Board(boolean shouldBeEmpty){


		if (!shouldBeEmpty){
            setPieces(8);
		}

	}
	public Piece pieceAt(int x, int y){
        if(x > 7  || y >7 || x < 0 || y < 0){
            return null;
        }
		if (pieces[x][y] != null)
		{
			return pieces[x][y];
		}
		else{
			return null;
		}
	}
	public boolean canSelect(int x, int y){

		if(pieceAt(x, y) != null &&  pieceAt(x, y).isFire() == fireTurn){
            if (hasMoved == false){
                return true;            
            }
            else{
                return false;
            }
        }
        else if(pieceAt(x, y) == null){
            if ((selected != null && hasMoved == false && 
                validMove(selectedX, selectedY, x, y)) || (selected != null && 
                selected.hasCaptured() == true && validMove(selectedX, selectedY, x, y))){
                return true;
            }
            return false;
        }
        return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf)
    {
        if(xf < 0 || yf < 0 || xf > 7 || yf > 7){
            return false;
        }
        else if(pieceAt(xf, yf) != null){
            return false;
        }
        else if(pieceAt(xi, yi).isFire()){
            if(pieceAt(xi, yi).isKing()){
                if(pieceAt(xi, yi).hasCaptured()){
                    if(xf- xi == 2 && yf-yi ==2 && pieceAt(xi+1, yi+1) != null 
                        && pieceAt(xi+1, yi+1).isFire() == false){
                        return true;
                    }
                    else if(xf- xi == 2 && yi-yf ==2 && pieceAt(xi+1, yi-1) != null 
                        && pieceAt(xi+1, yi-1).isFire() == false){
                        return true;
                    }
                    else if(xi-xf == 2 && yf-yi ==2 && pieceAt(xi-1, yi+1) != null 
                        && pieceAt(xi-1, yi+1).isFire() == false){
                        return true;
                    }
                    else if(xi-xf == 2 && yi-yf ==2 && pieceAt(xi-1, yi-1) != null 
                        && pieceAt(xi-1, yi-1).isFire() == false){
                        return true;
                    }
                }
                else{
                    if(xf- xi == 2 && yf-yi ==2 && pieceAt(xi+1, yi+1) != null 
                        && pieceAt(xi+1, yi+1).isFire() == false){
                        return true;
                    }
                    else if(xf- xi == 2 && yi-yf ==2 && pieceAt(xi+1, yi-1) != null 
                        && pieceAt(xi+1, yi-1).isFire() == false){
                        return true;
                    }
                    else if(xi-xf == 2 && yf-yi ==2 && pieceAt(xi-1, yi+1) != null 
                        && pieceAt(xi-1, yi+1).isFire() == false){
                        return true;
                    }
                    else if(xi-xf == 2 && yi-yf ==2 && pieceAt(xi-1, yi-1) != null 
                        && pieceAt(xi-1, yi-1).isFire() == false){
                        return true;
                    }
                    else if(xf- xi == 1 && yf-yi == 1){
                        return true;
                    }
                    else if(xi- xf == 1 && yf-yi == 1){
                        return true;
                    }
                    else if(xf- xi == 1 && yi-yf == 1){
                        return true;
                    }
                    else if(xi- xf == 1 && yi-yf == 1){
                        return true;
                    }
                }
            }
            else{
                    if(xf- xi == 2 && yf-yi ==2 && pieceAt(xi+1, yi+1) != null 
                        && pieceAt(xi+1, yi+1).isFire() == false){
                        return true;
                    }
                    else if(xi-xf == 2 && yf-yi ==2 && pieceAt(xi-1, yi+1) != null 
                        && pieceAt(xi-1, yi+1).isFire() == false){
                        return true;
                    }
                    else if(xf- xi == 1 && yf-yi == 1){
                        return true;
                    }
                    else if(xi- xf == 1 && yf-yi == 1){
                        return true;
                    }
                }               
            }
        else if(pieceAt(xi, yi).isFire() == false){
            if(pieceAt(xi, yi).isKing()){
                if(pieceAt(xi, yi).hasCaptured()){
                    if(xf- xi == 2 && yf-yi ==2 && pieceAt(xi+1, yi+1) != null 
                        && pieceAt(xi+1, yi+1).isFire() == true){
                        return true;
                    }
                    else if(xf- xi == 2 && yi-yf ==2 && pieceAt(xi+1, yi-1) != null 
                        && pieceAt(xi+1, yi-1).isFire() == true){
                        return true;
                    }
                    else if(xi-xf == 2 && yf-yi ==2 && pieceAt(xi-1, yi+1) != null 
                        && pieceAt(xi-1, yi+1).isFire() == true){
                        return true;
                    }
                    else if(xi-xf == 2 && yi-yf ==2 && pieceAt(xi-1, yi-1) != null 
                        && pieceAt(xi-1, yi-1).isFire() == true){
                        return true;
                    }
                }
                else{
                    if(xf- xi == 2 && yf-yi ==2 && pieceAt(xi+1, yi+1) != null 
                        && pieceAt(xi+1, yi+1).isFire() == true){
                        return true;
                    }
                    else if(xf- xi == 2 && yi-yf ==2 && pieceAt(xi+1, yi-1) != null 
                        && pieceAt(xi+1, yi-1).isFire() == true){
                        return true;
                    }
                    else if(xi-xf == 2 && yf-yi ==2 && pieceAt(xi-1, yi+1) != null 
                        && pieceAt(xi-1, yi+1).isFire() == true){
                        return true;
                    }
                    else if(xi-xf == 2 && yi-yf ==2 && pieceAt(xi-1, yi-1) != null 
                        && pieceAt(xi-1, yi-1).isFire() == true){
                        return true;
                    }
                    else if(xf- xi == 1 && yf-yi == 1){
                        return true;
                    }
                    else if(xi- xf == 1 && yf-yi == 1){
                        return true;
                    }
                    else if(xf- xi == 1 && yi-yf == 1){
                        return true;
                    }
                    else if(xi- xf == 1 && yi-yf == 1){
                        return true;
                    }
                }
            }
            else{
                    if(xf- xi == 2 && yi-yf ==2 && pieceAt(xi+1, yi-1) != null 
                        && pieceAt(xi+1, yi-1).isFire() == true){
                        return true;
                    }
                    else if(xi-xf == 2 && yi-yf ==2 && pieceAt(xi-1, yi-1) != null 
                        && pieceAt(xi-1, yi-1).isFire() == true){
                        return true;
                    }
                    else if(xf- xi == 1 && yi-yf == 1){
                        return true;
                    }
                    else if(xi- xf == 1 && yi-yf == 1){
                        return true;
                    }
                }               
            }
        else{
            return false;
        }
        return false;

    }
        // else if(pieceAt(xi, yi).hasCaptured() && Math.abs(xf-xi) == 2 && yi-yf ==2 && pieceAt(xi, yi).isKing()){
        //     return true;
        // }
        // else if(pieceAt(xi, yi).hasCaptured() && Math.abs(xf-xi) == 2 && yf - yi ==2 && pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else if(pieceAt(xi, yi).hasCaptured() && Math.abs(xf-xi) == 2 && yi-yf ==2 && !pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else if((pieceAt(xi + 1, yi-1) != null && xf-xi == 2 && yi-yf ==2 && !pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else if((pieceAt(xi - 1, yi-1) != null && xi - xf  == 2 && yi-yf ==2 && !pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else if((pieceAt(xi + 1, yi+1) != null && xf-xi == 2 && yf- yi ==2 && pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else if((pieceAt(xi - 1, yi+1) != null && xi - xf  == 2 && yf-yi ==2 && pieceAt(xi, yi).isFire()){
        //     return true;
        // }      
        // else if(Math.abs(xf-xi) == 1 && Math.abs(yf - yi) == 1 && pieceAt(xi, yi).isKing()){
        //     return true;
        // }        
        // else if(Math.abs(xf-xi) == 1 && yf - yi ==1 && pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else if(Math.abs(xf-xi) == 1 && yi- yf ==1 && !pieceAt(xi, yi).isFire()){
        //     return true;
        // }
        // else{
        //     return false;
        // }

	public void select(int x, int y){
        selectedX = x;
        selectedY = y;
        if(pieces[x][y] != null){
            // System.out.println(pieces[x][y]);
            selected = pieceAt(x, y);
            // System.out.println(selected);

        }
        else{
            // System.out.println(pieces[x][y]);
            selected.move(x,y);

            hasMoved = true;
            // pieces[x][y] = selected;

        }
	}

	public void place(Piece p, int x, int y){
        if(x >= 0 && x < 8 && y >= 0 && y < 8){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(p == pieces[i][j]){
                    remove(i,j);
                }
            }
        }
        pieces[x][y] = p;
        }
    }
	public Piece remove(int x, int y){
        if(x > 7 || y > 7 || pieceAt(x,y) == null){
            System.out.println("Out of bounds, nothing selected");
            return null;
        }
        else if(pieceAt(x,y) == null){
            System.out.println("No piece to remove");
            return null;
        }
        else{
            Piece temp = pieces[x][y];
            pieces[x][y] = null; 
            return temp;
        }

	}
    
	public boolean canEndTurn(){
        return hasMoved;
	}
	 public void endTurn(){
            if(fireTurn){
                fireTurn = false;
            }
            else{
                fireTurn = true;
            }
           hasMoved = false;
           selected = null; 
        
	 }
    private boolean noWaterPieceLeft(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i, j) != null && pieces[i][j].isFire() == false){
                    return false;
                }
            }
        } 
        return true;    
    }
    private boolean noFirePieceLeft(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i, j) != null && pieces[i][j].isFire() == true){
                    return false;
                }
            }
        } 
        return true;    
    }
	public String winner(){
        if(noFirePieceLeft() && noWaterPieceLeft() ){
            return "No One";
        }
        else if(noFirePieceLeft()){
            return "Water";
        }
        else if(noWaterPieceLeft()){
            return "Fire";
        }
        else{
            return null;
        }
	}
    
    private void drawBoard(int N) { //Draws the board using pretty shit
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if(i== selectedX && j == selectedY){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                }
                else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
               		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
           		}
                if(pieceAt(i, j) != null){
                    if(pieces[i][j].isFire()){
                        if(pieces[i][j].isKing()){
                            if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else{
                        if(pieces[i][j].isKing()){
                            if(pieces[i][j].isShield()){
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                                    }
                            else if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }   
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
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
    private void setPieces(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j == 0 && i % 2 == 0) {
                    // StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    pieces[i][j] = new Piece(true, this, i, j, "pawn" );
                }
                else if (j == 1 && i % 2 == 1) {
                    // StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    pieces[i][j] = new Piece(true, this, i, j, "shield" );

                }
                else if (j == 2 && i % 2 == 0) {
                    // StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    pieces[i][j] = new Piece(true, this, i, j, "bomb" );

                }

                else if (j == 5 && i % 2 == 1) {
                    // StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    pieces[i][j] = new Piece(false, this, i, j, "bomb" );

                }
                else if (j == 6 && i % 2 == 0) {
                    // StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    pieces[i][j] = new Piece(false, this, i, j, "shield" );

                }
                else if (j == 7 && i % 2 == 1) {
                    // StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    pieces[i][j] = new Piece(false, this, i, j, "pawn" );

                }
                else{
                    pieces[i][j] = null;
                }
                
           	}

            
        }	
    }
    private void setPictures(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceAt(i, j) != null){
                    if(pieces[i][j].isFire()){
                        if(pieces[i][j].isKing()){
                            if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else{
                        if(pieces[i][j].isKing()){
                            if(pieces[i][j].isShield()){
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                                    }
                            else if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }   
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                        else{
                            if(pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else if(pieces[i][j].isBomb()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
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
 }