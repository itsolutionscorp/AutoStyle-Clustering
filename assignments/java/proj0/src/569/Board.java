public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private boolean currently_selected = false;
    private int selected_x;
    private int selected_y;
    private Piece selectedPiece;
    private boolean moved = false;
    private int currTurn = 0;
    private boolean captured = false; 
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece currPiece = pieces[i][j];
                if (currPiece != null){
	                String img_location = "img/";
	                //check piece type
	                if(currPiece.isBomb()){
	                	img_location = img_location + "bomb-";
	                }
	                else if(currPiece.isShield()){
	                	img_location = img_location + "shield-";
	                }
	                else{
	                	img_location = img_location + "pawn-";
	                }
	                //check side
	                if(currPiece.isFire()){
	                	img_location = img_location + "fire";
	                }
	                else{
	                	img_location = img_location + "water";
	                }
	                //check if kinged
	                if(currPiece.isKing()){
	                	img_location = img_location + "-crowned";
	                }

	                img_location = img_location + ".png";


	                StdDrawPlus.picture(i + .5, j + .5, img_location, 1, 1);
                }
            }
        }
	}

	private void drawBoardSelected(int N, int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(i == x && j == y)  StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece currPiece = pieces[i][j];
                if (currPiece != null){
	                String img_location = "img/";
	                //check piece type
	                if(currPiece.isBomb()){
	                	img_location = img_location + "bomb-";
	                }
	                else if(currPiece.isShield()){
	                	img_location = img_location + "shield-";
	                }
	                else{
	                	img_location = img_location + "pawn-";
	                }
	                //check side
	                if(currPiece.isFire()){
	                	img_location = img_location + "fire";
	                }
	                else{
	                	img_location = img_location + "water";
	                }
	                //check if kinged
	                if(currPiece.isKing()){
	                	img_location = img_location + "-crowned";
	                }

	                img_location = img_location + ".png";


	                StdDrawPlus.picture(i + .5, j + .5, img_location, 1, 1);
                }
            }
        }
	}

    private Piece[][] generateIntitialBoardConfig(Board b, int size){
    	Piece[][] pieces = new Piece[size][size];
    	pieces[0][0] = new Piece(true, b, 0, 0, "pawn");
    	pieces[2][0] = new Piece(true, b, 2, 0, "pawn");
    	pieces[4][0] = new Piece(true, b, 4, 0, "pawn");
    	pieces[6][0] = new Piece(true, b, 6, 0, "pawn");

    	pieces[1][1] = new Piece(true, b, 1, 1, "shield");
    	pieces[3][1] = new Piece(true, b, 3, 1, "shield");
    	pieces[5][1] = new Piece(true, b, 5, 1, "shield");
    	pieces[7][1] = new Piece(true, b, 7, 1, "shield");

    	pieces[0][2] = new Piece(true, b, 0, 2, "bomb");
    	pieces[2][2] = new Piece(true, b, 2, 2, "bomb");
    	pieces[4][2] = new Piece(true, b, 4, 2, "bomb");
    	pieces[6][2] = new Piece(true, b, 6, 2, "bomb");


    	//top of board
    	pieces[1][7] = new Piece(false, b, 1, 7, "pawn");
    	pieces[3][7] = new Piece(false, b, 3, 7, "pawn");
    	pieces[5][7] = new Piece(false, b, 5, 7, "pawn");
    	pieces[7][7] = new Piece(false, b, 7, 7, "pawn");

    	pieces[0][6] = new Piece(false, b, 0, 6, "shield");
    	pieces[2][6] = new Piece(false, b, 2, 6, "shield");
    	pieces[4][6] = new Piece(false, b, 4, 6, "shield");
    	pieces[6][6] = new Piece(false, b, 6, 6, "shield");

    	pieces[1][5] = new Piece(false, b, 1, 5, "bomb");
    	pieces[3][5] = new Piece(false, b, 3, 5, "bomb");
    	pieces[5][5] = new Piece(false, b, 5, 5, "bomb");
    	pieces[7][5] = new Piece(false, b, 7, 5, "bomb");

    	return pieces;
    }

    private boolean validMove(int xo,int yo,int xn,int yn){
    	Piece toMove = pieces[xo][yo];
    	if(xo == xn && yo == yn){
    		return false;
    	}
    	//is a fire piece. can only move up
    	if(toMove.side() == 0 || toMove.isKing()){
    		if(xn == xo+1){
    			if(yn== yo+1){
    				if(pieces[xn][yn] == null){
    					return true;
    				}
    			}
    		}else if(xn == xo-1){
				if(yn== yo+1){
    				if(pieces[xn][yn] == null){
    					return true;
    				}
    			}
    		}
    		if(xn == xo+2){
    			if(yn== yo+2){
    				if(pieces[xn][yn] == null && pieces[xn-1][yn-1] != null){
    					return true;
    				}
    			}
    		}else if(xn == xo-2){
				if(yn== yo+2){
    				if(pieces[xn][yn] == null && pieces[xn+1][yn-1] != null){
    					return true;
    				}
    			}
    		}
    	}
		//is a water piece. can only move down
    	if(toMove.side() == 1 || toMove.isKing()){
    		if(xn == xo+1){
    			if(yn== yo-1){
    				if(pieces[xn][yn] == null){
    					return true;
    				}
    			}
    		}else if(xn == xo-1){
				if(yn== yo-1){
    				if(pieces[xn][yn] == null){
    					return true;
    				}
    			}
    		}
    		if(xn == xo+2){
    			if(yn== yo-2){
    				if(pieces[xn][yn] == null && pieces[xn-1][yn+1] != null){
    					return true;
    				}
    			}
    		}else if(xn == xo-2){
				if(yn== yo-2){
    				if(pieces[xn][yn] == null && pieces[xn+1][yn+1] != null){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

	private int okayForType(int xo, int yo, int xn, int yn){
		if(pieces[xo][yo].side() == 0){
			if (yo > yn){
				//this means piece needs to move up
				return 0;
			}
			//bad piece placement
			return -1;
		}
		else{
			if (yo > yn){
				//this means piece needs to move down
				return 1;
			}
			//bad piece placement
			return -1;
		}
	}

    private void goBoom(int x, int y){
    	if(selectedPiece.isBomb()){
    		int start_x =  Math.max(0, x - 1);
    		int finish_x = Math.min(7, x + 1);
    		int start_y =  Math.max(0, y - 1);
    		int finish_y = Math.min(7, y + 1);
    		pieces[x][y].doneCapturing();
    		pieces[x][y] = null;

    		for(int i = start_x; i <= finish_x; i++){
    			for(int j = start_y; j <= finish_y; j++){
    				if(pieces[i][j] != null && !pieces[i][j].isShield()){
    					pieces[i][j].doneCapturing();
    					pieces[i][j] = null;
    				}
    			}
    		}

    		currently_selected = false;
	    	selectedPiece = null;	
			selected_x = -1;
			selected_y = -1;
    	}
    }

    public Board(boolean shouldBeEmpty){
    	int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        if(!shouldBeEmpty){
        	//set initial board configuration
        	this.pieces = this.generateIntitialBoardConfig(this, N);
        }
        else{
        	this.pieces = new Piece[N][N];
        }
    }

    public Piece pieceAt(int x, int y){
    	try{
    		return pieces[x][y];
    	}
    	catch (ArrayIndexOutOfBoundsException e) {
    		return null;
    	}
    }

    public void place(Piece p, int x, int y){
    	try{
    		pieces[x][y] = p;
    	}
    	catch (ArrayIndexOutOfBoundsException e) {
    	}
    }

    public Piece remove(int x, int y){
    	try{
    		pieces[x][y].doneCapturing();
    		Piece toReturn = pieces[x][y];
    		pieces[x][y] = null;
    		return toReturn;
    	}
    	catch (ArrayIndexOutOfBoundsException e) {
    		return null;
    	}
    }

    public boolean canSelect(int x,int y){
    	//trying to select empty square first, if it is on your side
    	if(pieces[x][y] != null && pieces[x][y].side() == currTurn){
    		if(captured || moved){
    			return false;
    		} 
    			if(!currently_selected){
    				return true;
	    		}
	    		if(currently_selected && !moved){
	    			return true;
	    		}
    	}
    	if(pieces[x][y] == null && currently_selected){
    		if(!moved && validMove(selected_x,selected_y,x,y)){
    			return true;
    		}
    		//chain the move
    		int xx = (x-selected_x)/2 + selected_x;
			int yy = (y-selected_y)/2 + selected_y;
    		if(captured && validMove(selected_x,selected_y,x,y) && pieces[xx][yy] != null){
    			return true;
    		}
    	}
    	return false;
    }

    public void select(int x, int y){
    	//fresh select
		if(currently_selected == false){
			currently_selected = true;
			selected_x = x;
			selected_y = y;
			selectedPiece = pieces[x][y];
		}
		if(pieces[x][y] == null){
			//moving to adjacent spot
			if(Math.abs(x-selected_x) == 1 && Math.abs(y-selected_y) == 1){
				moved = true;
				currently_selected = false;

				pieces[selected_x][selected_y].move(x,y);

				selected_x = -1;
				selected_y = -1;
			}
			//jump and capture
			else{
				moved = true;				
				captured = true;
				currently_selected = true;

				int xx = (x-selected_x)/2 + selected_x;
				int yy = (y-selected_y)/2 + selected_y;
				pieces[xx][yy].doneCapturing();
    			pieces[xx][yy] = null;
				pieces[selected_x][selected_y].move(x,y);

				selected_x = x;
				selected_y = y;

				goBoom(x,y);
			}
		}else {
			if(!moved && pieces[x][y].side() == currTurn){
				currently_selected = true;
				selected_x = x;
				selected_y = y;
				selectedPiece = pieces[x][y];
			}
		}
    }

    public boolean canEndTurn(){

    	return (moved || captured);
    }

    public void endTurn(){
    	currently_selected = false;
	    selected_x = -1;
	    selected_y = -1;
	    selectedPiece = null;
	    moved = false;
	    captured = false; 
	    if(currTurn == 1){
	   		currTurn = 0;
	    }
	    else{
	    	currTurn = 1;
	    }

    }

	public String winner(){
		boolean empty = true;
		boolean allFire = true;
		boolean allWater = true;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(pieces[i][j] != null){
					if(pieces[i][j].side() == 0){
						allWater = false;
						empty = false;
					}
					if(pieces[i][j].side() == 1){
						allFire = false;
						empty = false;
					}
				}
			}
		}
		if(empty){
			return "No one";
		}
		else if(allFire){
			return "Fire";
		}
		else if(allWater){
			return "Water";
		}
		else{
			return null;
		}
	}

    public static void main(String[] args) {
        int N = 8;
        Board board = new Board(false);
		board.drawBoard(N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	if(board.winner() != null){
        		System.out.println(board.winner());
        		return;
        	}
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(board.canSelect((int)x,(int)y)){
                	board.select((int)x,(int)y);
                }
            }
          	if(StdDrawPlus.isSpacePressed()){
          		if(board.canEndTurn()){
          			board.endTurn();
          		}
          	}
            if(board.currently_selected){
            	board.drawBoardSelected(N, board.selected_x,board.selected_y);
            }            
            else{
            	board.drawBoard(N);
            }
            StdDrawPlus.show(100);
        }
    }
}