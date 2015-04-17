public class Board {

	private static Piece[][] boardState;
    private int turn = 1;
    private Piece selected = null;
    private boolean move = false;
    private int xClick;
    private int yClick;
    private int selectedX;
    private int selectedY;

    public Board(boolean shouldBeEmpty){
        boardState = new Piece[8][8];
    	if (shouldBeEmpty == false){

    		for (int row = 0; row < 8; row ++){
    			//System.out.println("row " + row);
    			//Empty rows are 3 and 4
    			if (row != 3 && row != 4){
    				for (int col = 0; col < 8; col = col + 2){
    					//System.out.println("col " + col);
    					
                        //Set up Team
    					boolean team = true;
    					if (row > 4) {
    						team = false;
    					}

    					//Set up Type
    					String type = "";
    					if (row == 0 || row == 7){
    						type = "pawn";
    					} else if (row == 1 || row == 6){
    						type = "shield";
    					} else {
    						type = "bomb";
    					}

    					//Make each piece
    					if (row % 2 == 0){
    						boardState[col][row] = new Piece(team, this, col, row, type);
                            //System.out.println("i hate 3 am coding T.T");
    					} else {
    						boardState[col + 1][row] = new Piece(team, this, col + 1, row, type);    						
    					}
    				}
    			}
    		}
    	}
    }

    private static void drawBoard(int N) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece check = boardState[i][j];
                if (check != null){
                    String strType = "";
                    if (check.isFire()){
                        strType = "fire";
                    } else {
                        strType = "water";
                    }
                    if (check.isKing()){
                        strType = strType + "-crowned";
                    }

                    String typeClassifier = "";
                    if (check.isBomb()){
                        typeClassifier = "bomb";
                    } else if (check.isShield()){
                        typeClassifier = "shield";
                    } else {
                        typeClassifier = "pawn";
                    }

                    StdDrawPlus.picture(i + .5, j + .5, "img/"+ typeClassifier + "-" + strType + ".png", 1, 1);   
                }
            }
        }
    }

    public Piece pieceAt(int x, int y){
        if (x >= 0 && x < 8 && y >= 0 && y < 8){
            return boardState[x][y];
        }
        else {
            return null;
        }
    }

    public void place(Piece p, int x, int y){
        if (p != null){
            if (x >= -1 && x < 8 && y >= 0 && y < 8){
                int oldX = selectedX;
                int oldY = selectedY;
                boardState[oldX][oldY] = null;
                boardState[x][y] = p;
                p.move(x, y);
            }
        }
    }

    public Piece remove(int x, int y){
        
        if (x >= 0 && x < 8 && y >= 0 && y < 8){
            Piece removed = boardState[x][y];
            boardState[x][y] = null;
            
            return removed;
        } else {
            
            return null;
        }
    }

    public boolean canSelect(int x, int y){
    if (x >= 0 && x < 8 && y >= 0 && y < 8){
        if(selected == null && move == false){
            if (pieceAt(x, y) != null){
                if(pieceAt(x, y).side() == turn){
                    
                    return true;
                }
            }
            return false;
        } else {
            int xDirection;
            int yDirection;
            if (x > selectedX) xDirection = 1;
            else                        xDirection = -1;
            if (y > selectedY) yDirection = 1;
            else                        yDirection = -1;

            if (!selected.isKing()){
                if (selected.isFire()) yDirection = 1;
                else                   yDirection = -1;
            }

            if (selected != null){
                if (move == false){
                    if (pieceAt(x, y) != null) {
                        return true;
                    }
                    if ((selectedX + xDirection == x) && (selectedY + yDirection == y)){
                        return true;
                    }
                }
                if (selectedX + 2 * xDirection == x && selectedY + 2 * yDirection == y){
                    if (pieceAt(selectedX + xDirection, selectedY + yDirection) != null){
                        if (pieceAt(selectedX + xDirection, selectedY + yDirection).side() != turn){
                                return true;
                        } 
                                           
                    }
                                    
                }
            }
            

        }
    }
    return false;
    }

    public void select(int x, int y){
        if (pieceAt(x, y)!= null){
            selected = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
        } else {
            move = true;
            place(selected, x, y);
        }
    }

    public boolean canEndTurn(){
        return move;
    }

    public void endTurn(){
        move = false;

        if (turn == 1){
            turn = 0;
        } else {
            turn = 1;
        }

        selected = null;
    }

    public String winner(){
        int fireTotal = 0;
        int waterTotal = 0;
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++) {
                if (pieceAt(i, j)!= null){
                    if (pieceAt(i, j).isFire()) fireTotal = fireTotal + 1;
                    else                      waterTotal = waterTotal + 1;
                }
            }
        }
        if (fireTotal == 0 && waterTotal == 0){
            return "No one";
        } else if (fireTotal == 0){
            return "Water";
        } else if (waterTotal == 0){
            return "Fire";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);

        while(b.winner() == null || boardState != null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.xClick = (int) x;
                b.yClick = (int) y;
                if (b.canSelect(b.xClick, b.yClick)){
                    b.select(b.xClick, b.yClick);
                } 
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()){

                    b.endTurn();
                }
            }
            StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
        return;
    }
}
        