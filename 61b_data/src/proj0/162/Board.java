public class Board {
	private int N = 8;
	private Piece[][] pieces = new Piece[N][N];
	private String turn = "Fire";
	private Piece selected = null;
	private Piece moved = null;
	private int init_x;
	private int init_y;
			
	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
                
        while(true) {
            b.drawBoard(N);
    		if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
         		if (b.canSelect((int) x, (int) y)){
	               	b.select((int) x, (int) y);
               }
             }
            if (StdDrawPlus.isSpacePressed()) {
            	
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            }
               
            StdDrawPlus.show(100);
        }
       }


	
	private void defaultBoard() {

		String type = null;
		boolean isFire = false;
		int N = 8;
		pieces = new Piece[N][N];
		for (int i = 0; i < N; i++){

			for (int j = 0; j < N; j++){
				
				if ((j >= 0 && j<=2) || (j >=5 && j<=7)){
					if ((i + j)%2 ==0){
					if (j >= 0 && j <= 2){
						isFire = true;
					}
					if (j >= 5 && j <= 6){
						isFire = false;
					}
					if (j == 0  || j == 7 ){
						type = "pawn";
					}
					if (j == 1 || j == 6){
						type = "shield";
					}
					if (j == 2 || j == 5){
						type = "bomb";
					}
					pieces[i][j] = new Piece(isFire, this, i, j, type);
					}
					else{
						pieces[i][j] = null;

					}
				}
				else {
					pieces[i][j] = null;
				}
				}

	}

}

	// Create Board constructor
	public Board(boolean shouldBeEmpty ){
		if (shouldBeEmpty == false) {
			defaultBoard();
		}

	}
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null){
                	if ((i + j) % 2 == 0) {
                	StdDrawPlus.picture(i + .5, j + .5, draw(pieces[i][j]), 1, 1);
                }
        }
                
                
    }
    }
    	if (selected != null){
    		StdDrawPlus.filledSquare(init_x + .5, init_y + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			if (pieceAt(init_x, init_y)!= null){
			StdDrawPlus.picture(init_x + .5, init_y + .5, draw(pieces[init_x][init_y]), 1, 1);
			}
    	}
}

	// Save pictures in draw method and call it in drawBoard
	private String draw(Piece p){
		String s = null;
		if (p.isFire()){

            if (p.isShield()){
               	if (!p.isKing()){
					s =  "img/shield-fire.png";
					}
				else {
					s = "img/shield-fire-crowned.png";
					}
                }
            else if (p.isBomb()){
                if (!p.isKing()){
					s =  "img/bomb-fire.png";
                }
                else {
                	s = "img/bomb-fire-crowned.png";
                	}
                }
            else if (!p.isBomb() && !p.isShield()){
                if (!p.isKing()){
                	s = "img/pawn-fire.png";
                }
                else{
                	s = "img/pawn-fire-crowned.png";
                	}
                }


        }
        if (!p.isFire()){
			if (p.isShield()){
                if (!p.isKing()){
					s =  "img/shield-water.png";
				}
				else {
					s = "img/shield-water-crowned.png";
					}
                }
            else if (p.isBomb()){
                if (!p.isKing()){
					s =  "img/bomb-water.png";
                }
                else {
                	s = "img/bomb-water-crowned.png";
                	}
                }
            else if (!p.isBomb() && !p.isShield()){
                if (!p.isKing()){
                	s = "img/pawn-water.png";
                }
                else{
                	s = "img/pawn-water-crowned.png";
                	}
                }


        }

		return s;
	}

	public Piece pieceAt(int x, int y){
		if (x >= N || y >= N || x < 0 || y<0){
			return null;
		}
		if (pieces[x][y]!= null) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y){
		Piece p = pieceAt(x, y);
		if (p != null){
			if ((turn == "Fire" && p.side() == 1) || (turn == "Water" && p.side() == 0)){
				return false;
			}
			if (selected == null){
				return true;
			}
			if (moved == null){
				return true;
			}
			return false;
		}
		else {
			if (selected != null){
				if (moved == null) {
					if (validMove(init_x, init_y, x, y)){
						return true;
					}
				}
				if (selected.hasCaptured() && validMove(init_x, init_y, x, y)){

					return true;
				}
			}
			return false;
	}
}

	private boolean validMove(int xi, int yi, int xf, int yf){
		int distance_x = xf - xi;
		int distance_y = yf - yi;
		int avg_x = (xi + xf)/2;
		int avg_y = (yi + yf)/2;
		Piece curr_piece = pieceAt(xi, yi);
		Piece final_piece = pieceAt(xf,yf);
		
		if (curr_piece == null){
			return false;
		}
		if (Math.abs(distance_x) >= 3 || Math.abs(distance_y) >= 3 || distance_x == 0 || distance_y == 0){
			return false;
		}
		if (final_piece != null){
			return false;
		}
		if (curr_piece.side() == 0 && !curr_piece.isKing() && distance_y < 0){
			return false;
		}
		if (curr_piece.side() == 1 && !curr_piece.isKing() && distance_y > 0){
			return false;
		}
		if (curr_piece.hasCaptured() && Math.abs(distance_x) == 1 && Math.abs(distance_y) ==1){
			return false;
		}
		if (Math.abs(distance_x) == 2 && Math.abs(distance_y) == 2){
			if (pieceAt(avg_x, avg_y) == null || curr_piece.side() == pieceAt(avg_x, avg_y).side()){
				return false;
			}
		}
		if (Math.abs(distance_x) != Math.abs(distance_y)){
			return false;
		}
		return true;
	
	}

	public void select(int x, int y){
		Piece p = pieceAt(x, y);
		if (p!=null){
			selected = p;
			init_x = x;
			init_y = y;
		}
		else {
			
			selected.move(x, y);
			moved = selected;		
		}

	}

	public void place(Piece p, int x, int y){
		if (x < N && y < N){
			if(p!=null){
				if (pieceAt(x,y) != null){
				pieces[x][y] = null;
				}
				pieces[x][y] = p;
				init_x = x;
				init_y = y;
			}

		}
	}

	public Piece remove(int x, int y){
		if (x >= N || y >= N){
			System.out.println("The input (x,y) is out of bounds");
			return null;
		}
		else if(pieceAt(x, y) == null){
			System.out.println("There is no piece at this (x,y)");
			return null;
		}
		Piece p = pieceAt(x,y);
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn(){
		if (moved != null){
			return true;
		}
		if (selected != null && selected.hasCaptured()){
			return true;
		}
		return false;
	}

	public void endTurn(){
		if (selected.side() == 0){
			turn = "Water";
		}
		if (selected.side() == 1){
			turn = "Fire";
		}
		if (moved.hasCaptured()){
			moved.doneCapturing();
		}
		init_y = 0;
		init_x = 0;
		selected = null;
		moved = null;

	}

	public String winner(){
		String winner = null;
		int num_fire = 0;
		int num_water = 0;
		for (int i = 0; i < N; i ++){
			for (int j = 0; j < N; j ++){
				Piece p = pieceAt(i, j);
				if (p!= null){
					if (p.side()==0){
						num_fire += 1;
					}
					if (p.side() == 1){
						num_water +=1;
					}
				}
			}
		}
		if (num_fire == 0 && num_water != 0){
			winner = "Water";
		}
		if (num_water == 0 && num_fire != 0){
			winner = "Fire";
		}
		if (num_fire == 0 && num_water == 0){
			winner = "No one";
		}
		if (num_fire != 0 && num_water != 0){
		winner = null;
	}
		return winner;
	}
}