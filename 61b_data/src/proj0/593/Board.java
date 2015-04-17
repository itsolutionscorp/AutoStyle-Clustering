public class Board{
	private boolean shouldBeEmpty, sStatus = false, moved = false, validCapture = false, didCapture = false, start = true;
	private int bSize = 8, turn = 0;
    private int lastX = bSize, lastY = bSize;
    private Piece lastSelect = null;
	private Piece[][] pList = new Piece[bSize + 1][bSize + 1];
	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty = shouldBeEmpty;
	}

	public Piece pieceAt(int x, int y){
    	if (x < 0 || y >= bSize || x >= bSize || y < 0){
    		return null;
    	}
        else if(pList[x][y] == null){
            return null;
        }
        else{
    	   return pList[x][y];
        }
    }
	
	public boolean canSelect(int x, int y){
		Piece checkPiece = pieceAt(x,y);
        if(checkPiece != null){
            if (checkPiece.side() == turn){
                    if(!moved){
                        return true;
                    }              
                }
        }
        else if (checkPiece == null){
            if(x < bSize && x >= 0 && y < bSize && y >= 0){
                if(sStatus && validMove(lastX, lastY, x, y)){
                    if (!moved || (moved&&lastSelect.hasCaptured()&&validCapture)){
                        return true;
                    }
                }
            }
        }   
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
        int dX, dY;
        if (xf > -1 && xf < 8 && yf > -1 && yf < 8){
            dX = xf - xi;
            dY = yf - yi;
            if (pieceAt(xi, yi) != null){
			 if (pieceAt(xi, yi).isKing()){
                    return checkMoveUp(xi, yi, dX, dY)||checkMoveDown(xi, yi, dX, dY);
                }
                else if(turn == 0){
                    return checkMoveUp(xi, yi, dX, dY);
                }
                else{
                    return checkMoveDown(xi, yi, dX, dY);
                }
            }
		}
		return false;
	}
	
    private boolean checkMoveUp(int xi, int yi, int changeX, int changeY){
        if ((changeX == -1 && changeY == 1) ||(changeX == 1 && changeY == 1)){
            validCapture = false;
            return true;
        }
        else if((changeX == -2 && changeY == 2) ||(changeX == 2 && changeY == 2)){
            Piece inbetween = pieceAt(xi + changeX/2, yi + changeY/2);
            if (inbetween != null){
                if (pieceAt(xi, yi).side()!=inbetween.side()){
                    validCapture = true;
                    return true;
                }
                else{
                    validCapture = false;
                }
            }
            else{
                validCapture = false;
            }
        }
        return false;
    }

    private boolean checkMoveDown(int xi, int yi, int changeX, int changeY){
        if ((changeX == -1 && changeY == -1) ||(changeX == 1 && changeY == -1)){
            validCapture = false;
            return true;
        }
        else if((changeX == -2 && changeY == -2) ||(changeX == 2 && changeY == -2)){
            Piece inbetween = pieceAt(xi + changeX/2, yi + changeY/2);
            if (inbetween != null){
                if (pieceAt(xi, yi).side()!=inbetween.side()){
                    validCapture = true;
                    return true;
                }
                else{
                    validCapture = false;
                }
            }
            else{
                validCapture = false;    
            }
        }
        return false;
    }

    public void select(int x, int y){
        Piece pCheck = pieceAt(x,y), pCheck2;
        int dX = x - lastX, dY = y - lastY;
        if (pCheck != null){
            if (lastSelect != pCheck && lastSelect != null){
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(lastX + .5, lastY + .5, .5);
                StdDrawPlus.picture(lastX + .5, lastY + .5, getPictureLocation(lastX, lastY), 1, 1);
            } 
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            StdDrawPlus.picture(x + .5, y + .5, getPictureLocation(x, y), 1, 1);
            sStatus = true;
            lastSelect = pCheck;
            didCapture = lastSelect.hasCaptured();
            lastX = x;
            lastY = y;
        }
        else if(pCheck == null && lastSelect != null){
            int testX = dX, testY = dY;
            lastSelect.move(x,y);
            place(lastSelect, x, y);
            moved = true;
            sStatus = true;
            remove(lastX, lastY);    
            if (dX == 2 || dX == -2){
                if (lastSelect.isBomb()){
                    didCapture = false;
                    sStatus = false;
                    remove(x,y);
                    for (int z = 0; z < 4; z++){
                        if (z > 0){
                            if (z == 2){
                                testY = -testY;
                            }
                            testX = -testX;
                        }
                        pCheck2 = pList[x + testX/2][y + testY/2];
                        if (pCheck2!=null){
                            if(!pCheck2.isShield()){
                                remove(x + testX/2, y + testY/2);
                            }
                        }
                    }
                    if (pList[lastX + dX/2][lastY + dY/2] != null){
                        remove(lastX + dX/2, lastY + dY/2);
                    }
                    lastX = bSize;
                    lastY = bSize;

                }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                    StdDrawPlus.picture(x + .5, y + .5, getPictureLocation(x, y), 1, 1);
                    didCapture = lastSelect.hasCaptured();
                    remove(lastX + dX/2, lastY + dY/2);
                    lastX = x;
                    lastY = y;
                }
            }
            else{
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.picture(x + .5, y + .5, getPictureLocation(x, y), 1, 1);
                lastX = x;
                lastY = y;
            }    
        }
    	else{
            return;
        }
    }

    public void place(Piece p, int x, int y){
        if(x > bSize || y > bSize || x < 0 || y < 0 || p == null){
            return;    
        }
        else{
            pList[x][y] = p;
        }  
    }

    public Piece remove(int x, int y){
        if (x > bSize || x < 0 || y < 0 || y > bSize){
            System.out.println("Cannot remove: out of bounds.");
            return null;
        }
    	else if(pList[x][y] == null){
            System.out.println("Cannot remove: no piece at " + x + " " + y);
            return null;
        }
        else {
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            Piece removed = pList[x][y];
            pList[x][y] = null;
            return removed;
        }
    }

    public boolean canEndTurn(){
    	if (moved){
    		return true;
    	}
        else{
    	   return false;
        }
    }


    public void endTurn(){
        if (pList[lastX][lastY] != null){
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            StdDrawPlus.filledSquare(lastX + .5, lastY + .5, .5);
            StdDrawPlus.picture(lastX + .5, lastY + .5, getPictureLocation(lastX, lastY), 1, 1);
            pList[lastX][lastY].doneCapturing();
        }
        lastSelect = null;
        validCapture = false;
        didCapture = false;
        sStatus = false;
        turn = turn ^ 1;
        moved = false;
        lastX = bSize;
        lastY = bSize;
    }

    public String winner(){
    	int fire = 0, water = 0;
    	for (int a = 0; a < bSize; a++){
    		for (int b = 0; b < bSize; b++){
                if (pList[a][b]!=null){
                    if (pList[a][b].side() == 0){
                        fire += 1;
                    }
                    else if(pList[a][b].side() == 1){
                        water += 1;
                    }
                }
    		}
    	}
    	if (fire == 0 && water == 0){
    		return "No One";
    	}
    	else if(water == 0){
    		return "Fire";
    	}
    	else if(fire == 0){
    		return "water";
    	}
    	else{
    		return null;
    	}
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, bSize);
        StdDrawPlus.setYscale(0, bSize);
        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                else{             
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
    }

    private void createInitialUnits(){
        boolean isNotWater = true;
        String whatType, image, team = "-fire";
        for (int x = 0; x < bSize; x++){
            for (int y = 0; y < bSize; y++){
    	        if ((x + y) % 2 == 0){
                    if (start){
                        if (y > 4 || y < 3){
                            if (y > 4){
                                isNotWater = false;
                                team = "-water";
                            }
                            if (y < 3){
                                isNotWater = true;
                                team = "-fire";
                            }
                            if (y == 0 || y == 7){
                                whatType = "pawn";
                                image = "img/pawn";
                            }
                            else if (y == 1 || y == 6){
                                whatType = "shield";
                                image = "img/shield";
                            }
                            else if (y == 2 || y == 5){
                                whatType = "bomb";
                                image = "img/bomb";
                            }
                            else{
                                whatType = null;
                                image = null;
                            }
                        pList[x][y] = new Piece(isNotWater, this, x, y, whatType);
                        StdDrawPlus.picture(x + .5, y + .5, image + team + ".png", 1, 1);
                        }
                    }
                }
            }
        }	
    }

    private String getPictureLocation(int x, int y){
        String image, team, isCrowned;
        if(pList[x][y] != null){
            if(pList[x][y].isFire()){
                team = "-fire";
            }
            else{
                team = "-water";
            }
            if (pList[x][y].isShield()){
                image = "img/shield";
            }
            else if(pList[x][y].isBomb()){
                image = "img/bomb";
            }
            else{
                image = "img/pawn";
            }

            if (pList[x][y].isKing()){
                isCrowned = "-crowned";
            }
            else{
                isCrowned = "";
            }
            return image + team + isCrowned + ".png";
        }
        else{
            return null;
        }
    }

    private void startGame(){
        start = false;
        lastX = bSize;
        lastY = bSize;
    }

	public static void main(String [] args){
    	Board checkers = new Board(false);
    	int x, y;
        checkers.drawBoard();
        checkers.createInitialUnits();
        checkers.startGame();
        while(true){
			if (StdDrawPlus.mousePressed()) {
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();
				if(checkers.canSelect(x, y)){
					checkers.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				if(checkers.canEndTurn()){
					checkers.endTurn();
				}
			}
		}
	}
}	