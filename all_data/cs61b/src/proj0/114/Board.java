public class Board{
	private boolean shouldBeEmpty;
	private Piece[][] pieces;
    private boolean hasMoved;
    private boolean isPlayerFire;
    private Piece selectedPiece;
    private int locaX;
    private int locaY;


	public static void main(String[] args){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        if(b.shouldBeEmpty == false){
            while(true) {
                b.drawBoard();
                if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    if (b.canSelect((int) x, (int) y)){
                        b.select((int) x, (int) y);
                    }
                
                }            
                StdDrawPlus.show(100);
                if (StdDrawPlus.isSpacePressed()){
                    if (b.canEndTurn()){
                        b.endTurn();
                    }
                }
            }
        }
        else{
            b.drawBoard();
        }

	}
	public Board(boolean shouldBeEmpty){     
        pieces = new Piece[8][8];       
		this.shouldBeEmpty = shouldBeEmpty;
        isPlayerFire = true;
        hasMoved = false;
        selectedPiece = null;
        locaY = 0; locaX = 0;

        if (shouldBeEmpty == false){
            int N = 8;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (j == 0){
                        if (i % 2 == 0){
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                    }
                    if (j == 1){
                        if ((i + 1) % 2 == 0){
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                    }
                    if (j == 2){
                        if (i % 2 == 0){
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                    }
                    if (j == 5){
                        if ((i + 1) % 2 == 0){
                            pieces[i][j] = new Piece(false, this, i, j, "bomb"); 
                        }
                    }
                    if (j == 6){
                        if (i % 2 == 0){
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                    }
                    if (j == 7){
                        if ((i + 1) % 2 == 0){
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }
        }
	}
	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            
                if (pieces[i][j] != null){
                    String word;
                    if (pieces[i][j].isKing()){
                        word = "-crowned";
                    }
                    else{
                        word = "";
                    }
                    if (pieces[i][j].isFire()){
                        if (pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire" + word + ".png", 1, 1);
                        }
                        else{
                            if (pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire" + word + ".png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire" + word + ".png", 1, 1);
                            }
                        }
                    }
                    else{
                        if (pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water" + word + ".png", 1, 1);
                        }
                        else{
                            if (pieces[i][j].isShield()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water" + word + ".png", 1, 1);
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water" + word + ".png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }
	public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7 || x < 0 || y < 0){
            return null;
        }
        if (pieces[x][y] == null){
            return null;
        }
        return pieces[x][y];
	}
	public boolean canSelect(int x, int y){
        if (pieceAt(x, y) != null){
            if (pieces[x][y].isFire() == isPlayerFire){
                //if (selectedPiece != null){
                //    if (selectedPiece.hasCaptured()){
                //        return false;
                //    }
                //    return true;
                //}
                //else{
                //    if (!hasMoved){
                 //       return true;
                //    }
                //    return false;
                //}
                if (hasMoved){
                    return false;
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (x > 7 || y > 7 || x < 0 || y < 0){
                return false;
            }
            if (selectedPiece == null){
                return false;
            }
            else{
                if (selectedPiece.isFire() != isPlayerFire){
                    return false;
                }

                else{
                    if (!hasMoved){
                        if (validMove(locaX, locaY, x, y)){
                            return true;
                        }
                        return false;
                    }
                    else{
                        if (!selectedPiece.hasCaptured()){
                            return false;
                        }
                        else{
                            if (Math.abs(locaX - x) != 2 || Math.abs(locaY - y) != 2){
                                return false;
                            }
                            else{
                                if (canCaptureAgain(x, y)){
                                    return true;
                                }
                                return false;
                            }
                        }
                    }
                }
            }
        }

	}
	private boolean validMove(int xi, int yi, int xf, int yf){
        if (xf < 0 || xf > 7 || yf < 0 || yf > 7){
            return false;
        }
        else{
            Piece curr = pieces[xi][yi];
            if (curr.isKing()){
                if (Math.abs(xi - xf) == 1){
                    if (pieceAt(xf, yf) != null){
                        return false;
                    }
                    else{
                        if (Math.abs(yi - yf) != 1){
                            return false;
                        }
                        return true;
                    }
                }
                else{
                    if (Math.abs(xi - xf) == 2){
                        if (Math.abs(yi - yf) != 2){
                            return false;
                        }
                        else{
                            if (Math.abs(xi - xf) <= 2 && Math.abs(yi - yf) <= 2){
                                int xMid = (xf + (xi - xf) / 2);
                            
                                int yMid = (yf + (yi - yf) / 2);
                            
                                if (pieceAt(xMid, yMid) == null){
                                    return false;
                                }
                                else{
                                    if (pieces[xMid][yMid].isFire() == curr.isFire()){
                                        return false;
                                    }
                                    return true;
                                }
                            }
                            return false;
                            
                        }
                    }
                    return false;
                }

            }
            else{
                if (curr.isFire()){
                    if (yf - yi == 1){               
                        if (Math.abs(xf - xi) == 1){
                            if (pieceAt(xf, yf) == null){
                                return true;
                            }
                            return false;
                        }
                        return false;
                    }
                    else{
                        if (yf - yi == 2){
                            if (Math.abs(xf - xi) == 2){
                                int xMid = (xf + xi) / 2;
                                int yMid = (yf + yi) / 2;
                                if (pieceAt(xMid, yMid) == null){
                                    return false;
                                }
                                else{
                                    if (pieces[xMid][yMid].isFire() == curr.isFire()){
                                        return false;
                                    }
                                    else{
                                        if (pieceAt(xf, yf) != null){
                                            return false;
                                        }
                                        return true;
                                    }
                                }
                            }
                        }
                        return false;
                    }
                }
                else{
                    if (yf - yi == -1){               
                        if (Math.abs(xf - xi) == 1){
                            if (pieceAt(xf, yf) == null){
                                return true;
                            }
                            return false;
                        }
                        return false;
                    }
                    else{
                        if (yf - yi == -2){
                            if (Math.abs(xf - xi) == 2){
                                int xMid = (xf + xi) / 2;
                                int yMid = (yf + yi) / 2;
                                if (pieceAt(xMid, yMid) == null){
                                    return false;
                                }
                                else{
                                    if (pieces[xMid][yMid].isFire() == pieces[xi][yi].isFire()){
                                        return false;
                                    }
                                    else{
                                        if (pieceAt(xf, yf) != null){
                                            return false;
                                        }
                                        return true;
                                    }
                                }
                            }
                        }
                        return false;
                    }
                }
            }
        }
    }
    private boolean canCaptureAgain(int x, int y){
        System.out.println("capture again");
        if (x < 0 || x > 7 || y < 0 || y >7){
            return false;
        }
        //if (!selectedPiece.hasCaptured()){
        //    return false;
        //}
        else{
            if (selectedPiece.isKing()){
                if (pieceAt((x + locaX) / 2, (y + locaY) /2) == null){
                    return false;
                }
                else{
                    if (pieceAt((x + locaX) / 2, (y + locaY) /2).isFire() == selectedPiece.isFire()){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            else{
                if (pieceAt((x + locaX) / 2, (y + locaY) /2) == null){
                    return false;
                }
                else{
                    if (selectedPiece.isFire()){
                        if ((y - locaY) != 2 ){
                            return false;
                        }
                        else{
                            if (pieceAt((x + locaX) / 2, (y + locaY) /2) == null){
                                return false;
                            }
                            else{
                                if (pieceAt((x + locaX) / 2, (y + locaY) /2).isFire() == selectedPiece.isFire()){
                                    return false;
                                }
                                return true;
                            }
                        }
                    }
                    else{
                        if ((y - locaY) != -2){
                            return false;
                        }
                        else{
                            if (pieceAt((x + locaX) / 2, (y + locaY) /2) == null){
                                return false;
                            }
                            else{
                                if (pieceAt((x + locaX) / 2, (y + locaY) /2).isFire() == selectedPiece.isFire()){
                                    return false;
                                }
                                return true;
                            }
                        }
                    }
                }

            }
        }
    }
	public void select(int x, int y){        
        System.out.println("selected");
        
            if (pieceAt(x, y) != null){
                selectedPiece = pieces[x][y];
                locaX = x;
                locaY = y;
            }
            else{
                selectedPiece.move(x, y);
                if ((selectedPiece.isBomb()) && (Math.abs(locaX - x) == 2)){
                    selectedPiece = null;
                }
                hasMoved = true;
                locaX = x;
                locaY = y;                
            }
	}
	public void place(Piece p, int x, int y){
		if (pieceAt(x, y) != null){
            remove(x,y);
        }
        pieces[x][y] = p;
        System.out.println("piece");      		
	}
    public Piece remove(int x, int y){
        if (x > 7 || y > 7 || x < 0 || y < 0){
            System.out.println("Input out of bounds.");
            return null;
        }
        if (pieceAt(x, y) == null){
            System.out.println("No piece to remove.");
            return null;
        }
        Piece a = pieceAt(x, y);
        pieces[x][y] = null;
        return a;
	}
	public boolean canEndTurn(){
        return hasMoved; //&& selectedPiece != null;
	}
	public void endTurn(){
            
            isPlayerFire = !isPlayerFire;
            hasMoved = false;
            if (selectedPiece != null){    
                if (selectedPiece.hasCaptured()){
                    selectedPiece.doneCapturing();
                }
                selectedPiece = null;
            }
            if (winner() != null){
                System.out.println(winner());
                return;
            }       
	}
	public String winner(){
        int countFire = 0;
        int countWater = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (pieces[i][j] != null){
                    if (pieces[i][j].isFire()){
                        countFire += 1;
                    }
                    else{
                        countWater +=1;
                    }
                }
            }
        }
        if (countFire == 0){
            if (countWater == 0){
                return "No one";
            }
            else{
                return "Water";
            }
        }
        else{
            if (countWater == 0){
                return "Fire";
            }
            else{
                return null;
            }
        }
	}
}