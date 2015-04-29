public class Board {
	private Piece[][] pieces = new Piece[8][8];
	// private Board b;
	private boolean selected = false;
	private boolean playeroneturn = true;
	private boolean moved = false;
	private boolean captured = false;
	private int selectx;
	private int selecty;
	private int x_;
	private int y_;
	private int firepiece = 0;
	private int waterpiece = 0;
	private boolean hasWinner = false;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == true){
			for (int x = 0; x < 8; x++){
				for (int y=0; y<8; y++){
					pieces[x][y] = null;
				}
			}
		} else {
			for (int x = 0; x < 8; x = x + 2) {
				pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
				pieces[x][6] = new Piece(false, this, x, 6, "shield");
			}
			for (int x = 1; x < 8; x = x + 2){
				pieces[x][1] = new Piece(true, this, x, 1, "shield");
				pieces[x][5] = new Piece(false, this, x, 5, "bomb");
				pieces[x][7] = new Piece(false, this, x, 7, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y){ //passed
		if (x > 7 || x < 0 || y > 7 || y < 0){
			return null;
		}
		if (pieces[x][y] == null){
			return null;
		}
		else return pieces[x][y];
	}

	public boolean canSelect(int x, int y){ //passed
		if (pieces[x][y] != null && playeroneturn == true && pieceAt(x,y).isFire()){
			//it is a piece + 1st player turn + piece is fire
			if ((selected == true && moved == false) || selected == false){
				return true;
			} return false;
		}
		else if (pieces[x][y] != null && playeroneturn == false 
			&& pieceAt(x,y).isFire() == false){
			//it is a piece + 2nd player turn + piece is water
			if ((selected == true && moved == false) || selected == false){
				return true;
			} return false;
		}
		else if (pieces[x][y] == null && selected == true && captured == false
				&& pieceAt(selectx,selecty).isFire() && pieceAt(selectx,selecty).isKing() == false
				&& (x - selectx == 1 || x - selectx == -1) && y - selecty == 1){
				if (moved == false){
					moved = true;
					return true;
				// } else if (moved == true && captured == true){
				// 	return false;
				}
				return false; 
				//nonKing fire piece to empty square, no catch
		}
		else if (pieces[x][y] == null && selected == true  && captured == false
				&& pieceAt(selectx,selecty).isFire() == false && pieceAt(selectx,selecty).isKing() == false
				&& (x - selectx == 1 || x - selectx == -1) && y - selecty == -1){
				if (moved == false){
					moved = true;
					return true;
				// } else if (moved == true && captured == true){
				// 	return false;
				}
				return false; 
				//nonKing water piece to empty sqaure, no catch
		}
		else if (pieces[x][y] == null && selected == true
				&& pieceAt(selectx,selecty).isKing() == true && (x - selectx == 1 || 
				x - selectx == -1) && (y - selecty == 1 || y - selecty == -1)){
				if (moved == false){
					moved = true;
					return true;
				// } else if (moved == true && captured == true){
				// 	return false;
				}
				return false; 
				//King to empty square, no catch
		}
		else if (pieces[x][y] == null && selected == true
			&& pieceAt(selectx,selecty).isFire() && pieceAt(selectx,selecty).isKing() == false &&
			(x - selectx == 2 || x - selectx == -2) && y - selecty == 2 &&
			pieceAt((x+selectx)/2, (y+selecty)/2).isFire() == false){
			moved = true;
			captured = true;
			return true; //nonKing fire piece to empty square, yes catch
		}

		else if (pieces[x][y] == null && selected == true
			&& pieceAt(selectx,selecty).isFire() == false 
			&& pieceAt(selectx,selecty).isKing() == false &&
			(x - selectx == 2 || x - selectx == -2) && y - selecty == -2 &&
			pieceAt((x+selectx)/2, (y+selecty)/2).isFire()){
			moved = true;
			captured = true;
			return true; //nonKing water piece to empty square, yes catch
		}
		else if (pieces[x][y] == null && selected == true 
			&& pieceAt(selectx,selecty).isFire() && pieceAt(selectx,selecty).isKing() &&
			(x - selectx == 2 || x - selectx == -2) && (y - selecty == 2 || y - selecty == -2) &&
			pieceAt((x+selectx)/2, (y+selecty)/2).isFire() == false){
			moved = true;
			captured = true;
			return true; //King fire piece to empty square, yes catch
		}
		else if (pieces[x][y] == null && selected == true
			&& pieceAt(selectx,selecty).isFire() == false && pieceAt(selectx,selecty).isKing() &&
			(x - selectx == 2 || x - selectx == -2) && (y - selecty == 2 || y - selecty == -2) &&
			pieceAt((x+selectx)/2, (y+selecty)/2).isFire()){
			moved = true;
			captured = true;
			return true; //King water piece to empty square, yes catch
		} else {
			return false;
		}
	}



	public void select(int x, int y){
			if (selected == true && pieces[x][y]==null){
				pieces[selectx][selecty].move(x,y);
				selected = false;
				moved = true;
				if (x - selectx == 2 || x - selectx == -2){
					captured = true;
					// if (pieceAt(selectx, selecty).isBomb()){
					// 	selected = false;
					// }
				}
			}
			selectx = x;
			selecty = y;
			selected = true;
		}

	public void place(Piece p, int x, int y){ //passed
		if (p == null){
			return ;
		} else if (x > 7 || x < 0 || y > 7 || y < 0){
			return ;
		} else if (pieces[x][y] != null){
			pieces[x][y] = remove(x,y);
			pieces[x][y] = p;
		} else pieces[x][y] = p;
	}

	public Piece remove(int x, int y){ //passed
		if (pieces[x][y] == null){
			System.out.println("No piece here");
			return null;
		} else if (x > 7 ||  x < 0 || y > 7 || y < 0){
			System.out.println("Out of bounds");
			return null;
		}else{ 
		Piece pointer = pieces[x][y];
		pieces[x][y] = null;
		return pointer;
	}
	}

	public boolean canEndTurn(){
		if (moved == true || captured == true){
			return true;
		} return false;
	}

	public void endTurn(){
		selected = false;
		moved = false;
		captured = false;
		if (playeroneturn == true){
			playeroneturn = false;
		}
		else{
			playeroneturn = true;
		}
	}

	public String winner(){
		firepiece = 0;
		waterpiece = 0;
		for (int i = 0; i < 8; i ++){
			for (int j = 0; j < 8; j ++){
				if (pieceAt(i,j) != null && pieceAt(i,j).isFire()){
					firepiece = firepiece + 1;
				}
				else if (pieceAt(i,j) != null && pieceAt(i,j).isFire() == false){
					waterpiece = waterpiece + 1;
				}
			}
		}
		if (firepiece == 0 && waterpiece > 0){
			hasWinner = true;
			return "Water";
		}
		else if (firepiece > 0 && waterpiece == 0){
			hasWinner = true;
			return "Fire";
		}
		else if (firepiece == 0 && waterpiece == 0){
			hasWinner = true;
			return "No one";
		}
		return null;
	}

	private void drawBoard(int N, int makeWhite, int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()){
                		if (pieces[i][j].isBomb() && pieces[i][j].isShield() == false
                			&& pieces[i][j].isKing() == false){
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                		}
                		else if (pieces[i][j].isBomb() && pieces[i][j].isShield() == false
                			&& pieces[i][j].isKing()){
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing() == false){
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing()){
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing() == false){
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing()){
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		}
                	}
                	else if (pieces[i][j].isFire() == false){

                		if (pieces[i][j].isBomb() && pieces[i][j].isShield() == false
                			&& pieces[i][j].isKing() == false){
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		}
                		else if (pieces[i][j].isBomb() && pieces[i][j].isShield() == false
                			&& pieces[i][j].isKing()){
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing() == false){
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing()){
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing() == false){
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                		else if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false
                			&& pieces[i][j].isKing()){
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                	}
	                }
	            }
            }
        }
     if( makeWhite == 1){
     	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if (pieces[x][y]!= null && pieces[x][y].isFire()){
        		if (pieces[x][y]!= null && pieces[x][y].isBomb() &&
        			pieces[x][y].isShield() == false && pieces[x][y].isKing() == false){
        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isBomb() &&
        			pieces[x][y].isShield() == false && pieces[x][y].isKing()){
        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isShield() &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing() == false){
        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isShield() &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing()){
        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
        		}
        		else if (pieces[x][y]!=null && pieces[x][y].isShield() == false &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing() == false){
        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
        		}
        		else if (pieces[x][y]!=null && pieces[x][y].isShield() == false &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing()){
        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
        		}
        	}
        	else{
        		if (pieces[x][y]!= null && pieces[x][y].isBomb() &&
        			pieces[x][y].isShield() == false && pieces[x][y].isKing() == false){
        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isBomb() &&
        			pieces[x][y].isShield() == false && pieces[x][y].isKing()){
        			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isShield() &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing() == false){
        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isShield() &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing()){
        			StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
        		}
        		else if (pieces[x][y]!= null && pieces[x][y].isShield() == false &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing() == false){
        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
            	}
            	else if (pieces[x][y]!= null && pieces[x][y].isShield() == false &&
        			pieces[x][y].isBomb() == false && pieces[x][y].isKing()){
        			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
            	}
            }
        }
	}

    public static void main(String[] args) {
        int N = 8;
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        board.drawBoard(N, 0, 0 ,0);
        // System.out.println(board.pieceAt(1,1).isShield()); //true
        // System.out.println(board.pieceAt(0,0).isShield()); //false

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(board.hasWinner == false) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY(); // change to int > canselect >if true, select
                board.x_ = (int) x;
                board.y_ = (int) y;
                if (board.canSelect(board.x_, board.y_)){
	            	board.select(board.x_, board.y_);
	            	// System.out.println(board.pieceAt(board.x_,board.y_));
	            	// if (board.pieceAt(board.x_,board.y_) != null){
		            	board.drawBoard(N,1,board.x_ ,board.y_);
		            // }
                }
            }
            if (StdDrawPlus.isSpacePressed() && board.canEndTurn()){
            	board.endTurn();
            	board.drawBoard(N, 0, 0, 0);
            }
            if (board.winner() != null){
            	System.out.println(board.winner());
            }
            StdDrawPlus.show(100);
        }
    }
}