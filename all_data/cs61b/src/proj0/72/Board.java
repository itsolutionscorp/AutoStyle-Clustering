/**
 * @author Alven Wang
 */

public class Board {
	private boolean shouldBeEmpty;
	private static int N = 8;
	private static boolean[][] pieceBoolean = new boolean[N][N];
	private static Piece[][] pieces = new Piece[N][N];
	private static boolean[][] pointerBoolean = new boolean[N][N];
	private Piece pointer = null;
	/*if turn == 0, then it's fire's turn. 1 = water's turn */
	private int turn = 0;
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private Piece removed;
	private int oldX;
	private int oldY;

	public static void main(String[] args) {
        
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        board.drawBoard(N);

        while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)){
                	board.select(x, y);
                	if (!pieceBoolean[board.oldX][board.oldY]){
                		board.hasMoved = true;
                	}
                	pieceBoolean[(int) x][(int) y] = true;
                }
            }
            if (board.hasMoved){
            	if (StdDrawPlus.isSpacePressed()){
                	if (board.canEndTurn()){
                		board.endTurn();
                		board.winner();
                	}
            	}
            }      
            StdDrawPlus.show(50);
        }
    }

    /*
     *
     */
    public Board(boolean shouldBeEmpty){
    	if (shouldBeEmpty){
    		return;
    	}else{
    		for (int i = 0; i < 8; i += 1){
    			for (int j = 0; j < 8; j += 1) {
    				if (((i + j) % 2 == 0) && (j < 3 || j > 4)){


    					pieceBoolean[i][j] = true;

    					if (j == 0){
    						pieces[i][j] = new Piece(true, this, i, j, "pawn");
    					}else if (j == 1){
    						pieces[i][j] = new Piece(true, this, i, j, "shield");
    					}else if (j == 2){
    						pieces[i][j] = new Piece(true, this, i, j, "bomb");
    					}else if (j == 5){
    						pieces[i][j] = new Piece(false, this, i, j, "bomb");
    					}else if (j == 6){
    						pieces[i][j] = new Piece(false, this, i, j, "shield");
    					}else if (j == 7){
    						pieces[i][j] = new Piece(false, this, i, j, "pawn");
    					}else if (j == 4){
    					}
    					
    				}
    			}
    		}
    	}
    }

    public Piece pieceAt(int x, int y){
    	return pieces[x][y];
    }

    public void place(Piece p, int x, int y){
    	if (p == null || x > N || y > N || x < 0 || y < 0){
    		return;	
    	}else{
    		pieces[x][y] = p;
    		pieceBoolean[x][y] = true;
    	}
    }

    public void select(int x, int y){
    	/*if (x, y) is empty*/
    	if (!pieceBoolean[x][y]){
    		pieceBoolean[x][y] = true;
    		pieces[x][y] = pointer;
    		pointerBoolean[this.oldX][this.oldY] = false;
    		

    		pointer = pieces[x][y];
    		pointerBoolean[x][y] = true;
    		pointer.move(x,y);
    		pointer = null;

    	}
    	else{

    		if (hasSelected){
    			for (int i = 0; i < N; i++) {
            		for (int j = 0; j < N; j++) {
            			pointerBoolean[i][j] = false;
    				}
    			}
    		}
    		pointer = pieces[x][y];
    		oldX = x;
    		oldY = y;
    		pointerBoolean[x][y] = true;
    		hasSelected = true;
    		
    	}
    }

    public boolean canSelect(int x, int y){
    	if ((x + y) % 2 == 1){
    		return false;
    	}

    	/*if there is a piece at (x, y)*/
    	else if (pieceBoolean[x][y]){
    		/*if it is Fire's turn*/
    		if (turn % 2 == 0){
    			if (pieces[x][y].isFire()){
    				/*if player has not selected or has not moved, return true*/
    				if(!hasSelected || !hasMoved){
    					return true;
    				}return false;
    			}return false;
    		}else{
    			if (!pieces[x][y].isFire()){
    				if(!hasSelected || !hasMoved){
    					return true;
    				}return false;
    			}return false;
    		}
    	}else{
    		if (validMove(x, y)){
    			return true;
    		}return false;
    	}
    }

    public Piece remove(int x, int y){
    	if (x > N - 1 || y > N - 1){
    		System.out.println("can't remove");
    		return null;
    	}
    	else if (!pieceBoolean[x][y]){
    		System.out.println("no piece to remove");
    		return null;
    	}

    	removed = pieces[x][y];
    	pieces[x][y] = null;
    	pieceBoolean[x][y] = false;
    	return removed;
    }

    public boolean canEndTurn(){
    	if (hasMoved){
    		return true;
    	}
    	else if (pointer.hasCaptured()){
    		return true;
    	}return false;
    }

    public void endTurn(){
    	turn += 1;
    	hasSelected = false;
    	hasMoved = false;
    	pointer = null;
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	pointerBoolean[i][j] = false;
    		}
    	}
    }

    public String winner(){


    	int firecount = 0;
    	int watercount = 0;
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceBoolean[i][j]){
                	if (pieces[i][j].isFire()){
                		firecount += 1;
                	}else{
                		watercount += 1;
                	}
                }
            }
        }
        if (shouldBeEmpty){
        	return null;
        }

        if (firecount == 0 && watercount == 0){
        	return "No one";
        }if (firecount == 0){
        	return "Water";
        }if (watercount == 0){
        	return "Fire";
        }return null;
    }


    private boolean validMove(int x, int y){
    	if (pointer != null){
    		if (pointer.isKing()){
    			if (x - 1 >= 0 && y - 1 >= 0) {
    				if (pieces[x-1][y-1] == pointer){
    					return true;
    				}
    			}if (x + 1 <= 7 && y - 1 >= 0){
    				if (pieces[x+1][y-1] == pointer){
    					return true;
    				}	
    			}if (x - 1 >= 0 && y - 1 >= 0) {
    				if (pieces[x-1][y-1] == pointer){
    					return true;
    				}
    			}if (x + 1 <= 7 && y - 1 >= 0){
    				if (pieces[x+1][y-1] == pointer){
    					return true;
    				}	
    			}if (x - 2 >= 0 && y - 2 >= 0) {
    				if (pieceBoolean[x-1][y-1]){
    					if (pieces[x-2][y-2] == pointer && !pieces[x-1][y-1].isFire()){
    						return true;
    					}
    				}
    			}if (x + 2 <= 7 && y - 2 >= 0) {
    				if (pieceBoolean[x+1][y-1]){
    					if (pieces[x+2][y-2] == pointer && !pieces[x+1][y-1].isFire()){
    						return true;
    					}
    				}
    			}if (x - 2 >= 0 && y - 2 >= 0) {
    				if (pieceBoolean[x-1][y-1]){
    					if (pieces[x-2][y-2] == pointer && !pieces[x-1][y-1].isFire()){
    						return true;
    					}
    				}
    			}if (x + 2 <= 7 && y - 2 >= 0) {
    				if (pieceBoolean[x+1][y-1]){
    					if (pieces[x+2][y-2] == pointer && !pieces[x+1][y-1].isFire()){
    						return true;
    					}
    				}
    			}
    		}
    	}
    	if (turn % 2 == 0){




    		if (x - 1 >= 0 && y - 1 >= 0) {
    			if (pieces[x-1][y-1] == pointer){
    				return true;
    			}
    		}if (x + 1 <= 7 && y - 1 >= 0){
    			if (pieces[x+1][y-1] == pointer){
    				return true;
    			}	
    		}if (x - 2 >= 0 && y - 2 >= 0) {
    			if (pieceBoolean[x-1][y-1]){
    				if (pieces[x-2][y-2] == pointer && !pieces[x-1][y-1].isFire()){
    					return true;
    				}
    			}
    		}if (x + 2 <= 7 && y - 2 >= 0) {
    			if (pieceBoolean[x+1][y-1]){
    				if (pieces[x+2][y-2] == pointer && !pieces[x+1][y-1].isFire()){
    					return true;
    				}
    			}
    		}
    		return false;


    	}else if (turn % 2 == 1){
    		if (x - 1 >= 0 && y + 1 <= 7) {
    			if (pieces[x-1][y+1] == pointer){
    				return true;
    			}
    		}if (x + 1 <= 7 && y + 1 >= 0){
    			if (pieces[x+1][y+1] == pointer){
    				return true;
    			}
    		}if (x - 2 >= 0 && y + 2 <= 7) {
    			if (pieceBoolean[x-1][y+1]){
    				if (pieces[x-2][y+2] == pointer && pieces[x-1][y+1].isFire()){
    					return true;
    				}
    			}
    		}if (x + 2 <= 7 && y + 2 <= 7){
    			if (pieceBoolean[x+1][y+1]){
    				if (pieces[x+2][y+2] == pointer && pieces[x+1][y+1].isFire()){
    					return true;
    				}
    			}
    		}

    	}return false;
    }

    private String type(int x, int y){
    	if (pieces[x][y].isBomb()){
    		return "bomb";
    	}else if (pieces[x][y].isShield()){
    		return "shield";
    	}else{
    		return "pawn";
    	}
    }


	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pointerBoolean[i][j]){
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieceBoolean[i][j]){
                	if (pieces[i][j].isFire()){
                		if (pieces[i][j].isKing()){
                			if (!pieces[i][j].isBomb() && !pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}else if (pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}else if (pieces[i][j].isBomb()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                		}else{
							if (!pieces[i][j].isBomb() && !pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}else if (pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}else if (pieces[i][j].isBomb()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                	}else{
                		if (pieces[i][j].isKing()){
                			if (!pieces[i][j].isBomb() && !pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}else if (pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}else if (pieces[i][j].isBomb()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                		}else{
							if (!pieces[i][j].isBomb() && !pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}else if (pieces[i][j].isShield()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}else if (pieces[i][j].isBomb()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }
    }
}