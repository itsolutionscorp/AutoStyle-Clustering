public class Board {
	
	private Piece[][] pieces;
	private boolean currentTurn; // true if Fire, false if Water
	private Piece selected_piece;
	private int selectedX;
	private int selectedY;
	private boolean has_moved;
	private static final int board_size = 8;

	public Board(boolean shouldBeEmpty){

		this.pieces = new Piece[board_size][board_size];
		this.currentTurn = true;
		this.selected_piece = null;
		this.selectedX = -1;
		this.selectedY = -1;
		this.has_moved = false;

		if(shouldBeEmpty){
			for(int i = 0; i < this.pieces.length; i++){
				for(int j = 0; j < this.pieces[0].length; j++){
					this.pieces[i][j] = null;
				}
			}
		}
		else{
			for(int i = 0; i < this.pieces[0].length; i++){
				for(int j = 0; j < this.pieces.length; j++){
					if((i == 0)&&(j % 2 == 0)){this.place(new Piece(true,this,j,i,"pawn"),j,i);}
					if((i == 1)&&(j % 2 == 1)){this.place(new Piece(true,this,j,i,"shield"),j,i);}
					if((i == 2)&&(j % 2 == 0)){this.place(new Piece(true,this,j,i,"bomb"),j,i);}
					if((i == 5)&&(j % 2 == 1)){this.place(new Piece(false,this,j,i,"bomb"),j,i);}
					if((i == 6)&&(j % 2 == 0)){this.place(new Piece(false,this,j,i,"shield"),j,i);}
					if((i == 7)&&(j % 2 == 1)){this.place(new Piece(false,this,j,i,"pawn"),j,i);}
				}
			}
		}
	}

	public Piece pieceAt(int x, int y){
		if(((x >= 0)&&(x < this.pieces.length))&&((y >= 0)&&(y < this.pieces[0].length))){
			return this.pieces[x][y];
		}
		else{
			return null;
		}
	}

	public boolean canSelect(int x, int y){
		if(this.selected_piece == null){
			if(this.pieceAt(x,y) == null){return false;} 
			if(this.currentTurn != this.pieceAt(x,y).isFire()){return false;}
			else{return true;}
		}
		else{
			if((this.pieceAt(x,y) == null) && (this.has_moved == false)){return (this.validMove(this.selectedX, this.selectedY, x, y) || this.validCapture(this.selectedX, this.selectedY, x, y));}
			else if((this.pieceAt(x,y) == null) && (this.has_moved == true)) {return this.validCapture(this.selectedX, this.selectedY, x, y);}
			else if((this.has_moved == false) && (this.currentTurn == this.pieceAt(x,y).isFire())){return true;}
			else{return false;}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if((xi > this.pieces.length - 1)||(xi < 0)||(xf > this.pieces.length - 1)||(xf < 0)||(yi > this.pieces.length - 1)||(yi < 0)||(yf > this.pieces.length - 1)||(yf < 0)){return false;} //Bound checking

		if(((yf > yi)&&(this.selected_piece.isFire())) || this.selected_piece.isKing()){ //fire
			if(((yi + 1) == yf)&&(((xi - 1) == xf)||((xi + 1) == xf))){return true;}//normal move
		}
		if(((yf < yi)&&(!(this.selected_piece.isFire()))) || this.selected_piece.isKing()){ //water
			if(((yi - 1) == yf)&&(((xi - 1) == xf)||((xi + 1) == xf))){return true;}//normal move
		}
		return false;
		
	}

	private boolean validCapture(int xi, int yi, int xf, int yf){
		if((xi > this.pieces.length - 1)||(xi < 0)||(xf > this.pieces.length - 1)||(xf < 0)||(yi > this.pieces.length - 1)||(yi < 0)||(yf > this.pieces.length - 1)||(yf < 0)){return false;} //Bound checking

		if(((yf > yi)&&(this.selected_piece.isFire())) || this.selected_piece.isKing()){ //fire
                        if(((yi + 2) == yf)&&((((xi - 2) == xf)&&(this.pieceAt(xi - 1, yi + 1) != null)&&(this.pieceAt(xi - 1, yi + 1).isFire() != this.selected_piece.isFire()))||
                                              (((xi + 2) == xf)&&(this.pieceAt(xi + 1, yi + 1) != null)&&(this.pieceAt(xi + 1, yi + 1).isFire() != this.selected_piece.isFire())))){return true;}//move with capture
                }
                if(((yf < yi)&&(!(this.selected_piece.isFire()))) || this.selected_piece.isKing()){ //water
                        if(((yi - 2) == yf)&&((((xi - 2) == xf)&&(this.pieceAt(xi - 1, yi - 1) != null)&&(this.pieceAt(xi - 1, yi - 1).isFire() != this.selected_piece.isFire()))||
                                              (((xi + 2) == xf)&&(this.pieceAt(xi + 1, yi - 1) != null)&&(this.pieceAt(xi + 1, yi - 1).isFire() != this.selected_piece.isFire())))){return true;}//move with capture
                }
                return false;
	}

	public void select(int x, int y){
		if(this.pieceAt(x,y) != null){
			this.selected_piece = this.pieceAt(x,y);
			this.selectedX = x;
			this.selectedY = y;
		}
		else{
			this.selected_piece.move(x,y);
			this.has_moved = true;
			selectedX = x;
			selectedY = y;
		}
	}
	
	public void place(Piece p, int x, int y){
		if(((x >= 0)&&(x < this.pieces.length))&&((y >= 0)&&(y < this.pieces[0].length))){
			this.pieces[x][y] = p;
                }
	}

	public Piece remove(int x, int y){
		Piece ptr = this.pieceAt(x,y);
		this.pieces[x][y] = null;
		return ptr;
	}

	public boolean canEndTurn(){
		return this.has_moved; 
	}

	public void endTurn(){
		if(this.selected_piece != null){
			this.selected_piece.doneCapturing();
			this.selected_piece = null;
			this.selectedX = -1;
			this.selectedY = -1;
		}
		this.currentTurn = !(this.currentTurn);
		this.has_moved = false;
	}

	public String winner(){
		int fire_pieces = 0;
		int water_pieces = 0;
		
		for(int i = 0; i < this.pieces.length; i++){
                	for(int j = 0; j < this.pieces[0].length; j++){
                        	if(this.pieceAt(i,j) != null && pieceAt(i,j).isFire()){fire_pieces += 1;}
				if(this.pieceAt(i,j) != null && !(pieceAt(i,j).isFire())){water_pieces += 1;}
                        }
                }

		if((fire_pieces <= 0) && (water_pieces <= 0)){return "No one";}
		else if(fire_pieces <= 0){return "Water";}
		else if(water_pieces <= 0){return "Fire";}
		else {return null;}
	}
	
	private void drawBoard(){
		
		for(int i = 0; i < this.pieces.length; i++){
			for(int j = 0; j < this.pieces.length; j++){
				if((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else		     StdDrawPlus.setPenColor(StdDrawPlus.RED);

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				
				if(this.pieces[i][j] != null){
			
					if((this.selected_piece != null)&&(this.pieces[i][j] == this.selected_piece)){StdDrawPlus.filledSquare(i + .5, j + .5, .5);}
					String path = "img/";
					
					if(this.pieces[i][j].isBomb()){path = path + "bomb-";}
					else if(this.pieces[i][j].isShield()){path = path + "shield-";}
					else{path = path + "pawn-";}

					if(this.pieces[i][j].isFire()){path = path + "fire";}
					else{path = path + "water";}

					if(this.pieces[i][j].isKing()){path = path + "-crowned";}
					path = path + ".png";
					StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0,board_size);
		StdDrawPlus.setYscale(0,board_size);
		Board bear_board = new Board(false);
		while(bear_board.winner() == null){
			bear_board.drawBoard();
			if(StdDrawPlus.mousePressed()){
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				
				if(bear_board.canSelect((int) x, (int) y)){
					bear_board.select((int) x,(int) y);
				}		
			}
			if(StdDrawPlus.isSpacePressed() && bear_board.canEndTurn()){bear_board.endTurn();}
			StdDrawPlus.show(10);
		}
	}
}
