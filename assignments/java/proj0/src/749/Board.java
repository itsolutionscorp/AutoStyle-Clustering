public class Board {
	private Piece[][] pieces;
	private int size = 8;
	private int side = 0;

	private int[] selection = new int[2];
	private boolean hasSelection = false;
	private boolean multiMode = false;
	private boolean done = false;

	private boolean hasWinner = false;

	//Board constructor
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[this.size][this.size];
		if(!shouldBeEmpty){
			for(int i=0;i<this.size;i++){
				for(int j=0;j<this.size;j++){
					if((j==0)&&(i%2==0)){
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					}
					else if((j==1)&&(i%2!=0)){
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					else if((j==2)&&(i%2==0)){
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
					else if((j==5)&&(i%2!=0)){
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
					else if((j==6)&&(i%2==0)){
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					}
					else if((j==7)&&(i%2!=0)){
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
	}

	//returns piece at (x, y) else null
	public Piece pieceAt(int x, int y){
		if((x>=this.size)||(y>=this.size)||(x<0)||(y<0)){
			return null;
		}
		else if(pieces[x][y]==null){
			return null;
		} else {
			return pieces[x][y];
		}
	}

	//cus DRY
	private boolean sameSide(Piece a, Piece b){
		if(a.side()==b.side()){
			return true;
		} else{
			return false;
		}
	}

	//herein lies, the jankiest logic of all time
	private boolean validMove(int x, int y){
		int xSelect = selection[0];
		int ySelect = selection[1];
		Piece piece = pieceAt(xSelect, ySelect);
		int direction;

		if(piece.isFire()) {
			direction = 1;
		} else{
			direction = -1;
		}
		//trololol
		if(y == (ySelect+direction)){
			if((x==xSelect+1)||(x==xSelect-1)){
				return true;
			} else{
				return false;
			}
		} 
		else if(y == (ySelect - direction)) {
			if(piece.isKing()&&((x==xSelect+1)||(x==xSelect-1))){
				return true;
			} else {
				return false;
			}
		} 
		else {
			return validCapture(x, y);
		}
	}

	//if else if else if else if else... blabbity blah
	private boolean validCapture(int x, int y){
		int xSelect = selection[0];
		int ySelect = selection[1];
		Piece piece = pieceAt(xSelect, ySelect);
		int direction;

		if(piece.isFire()){
			direction = 1;
		} else {
			direction = -1;
		}

		if((y == (ySelect + 2*direction))&&((x==xSelect+2)||(x==xSelect-2))){
			if((x==xSelect+2)&&(xSelect<7)&&(pieces[xSelect+1][ySelect+direction]!=null)){
				Piece potential = pieces[xSelect+1][ySelect+direction];
				return (!sameSide(piece, potential));
			}
			else if((x==xSelect-2)&&(xSelect>0)&&(pieces[xSelect-1][ySelect+direction]!=null)){
				Piece potential = pieces[xSelect-1][ySelect+direction];
				return (!sameSide(piece, potential));
			} else {
				return false;
			}
		}
		else if(y == (ySelect - 2*direction)){
			if(!piece.isKing()){
				return false;
			}
			else if((xSelect<7)&&(pieces[xSelect+1][ySelect-direction]!=null)){
				Piece potential = pieces[xSelect+1][ySelect-direction];
				return (!sameSide(piece, potential));
			}
			else if((xSelect>0)&&(pieces[xSelect-1][ySelect-direction]!=null)){
				Piece potential = pieces[xSelect-1][ySelect-direction];
				return (!sameSide(piece, potential));
			} else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	//true if square at (x, y) is selectable
	public boolean canSelect(int x, int y){
		if(this.done&&(!this.multiMode)){
			return false;
		}
		else if(pieceAt(x, y)==null){
			if(this.hasSelection){
				return validMove(x, y);
			} 
			else if(this.multiMode){
				return validCapture(x, y);
			}
			else {
				return false;
			}
		} else {
			Piece piece = pieceAt(x, y);
			if(this.multiMode){
				return false;
			}
			else if(piece.side()==this.side){
				return true;
			} else {
				return false;
			}
		}
	}

	//trololol. Also DRY
	private int sideFlipper(int x){
		if(x==1){
			return 0;
		} else{
			return 1;
		}
	}

	//selects square at (x, y)
	public void select(int x, int y){
		if((pieceAt(x,y))!=null&&(!this.multiMode)){
			this.hasSelection = true;
			selection[0] = x;
			selection[1] = y;
		} 
		else {
			Piece piece = pieceAt(selection[0], selection[1]);
			piece.move(x, y);
			this.done = true;
			this.hasSelection = false;
			if(piece.hasCaptured()&&(!piece.isBomb())){
				this.multiMode = true;
				selection[0] = x;
				selection[1] = y;
			}
		}
	}

	//place p at (x, y)
	public void place(Piece p, int x, int y){
		if((x<this.size)&&(y<this.size)&&(p!=null)){
			pieces[x][y] = p;
		}
	}

	//removes piece at (x, y) and returns it
	public Piece remove(int x, int y){
		if((x<this.size)&&y<(this.size)){
			if(pieceAt(x, y)==null){
				System.out.println("No piece there!");
				return null;
			} else {
				Piece removal = pieceAt(x, y);
				pieces[x][y] = null;
				return removal;
			}
		} else {
			System.out.println("Out of bounds!");
			return null;
		}
	}

	//piece must have moved or performed capture
	public boolean canEndTurn(){
		return this.done;
	}

	//called at end of a turn
	public void endTurn(){
		this.done = false;
		this.side = sideFlipper(this.side);
		this.multiMode = false;
		for(int i=0;i<this.size;i++){
			for(int j=0;j<this.size;j++){
				if(pieceAt(i, j)!=null){
					Piece piece = pieceAt(i, j);
					if(piece.hasCaptured()){
						piece.doneCapturing();
					}
				}
			}
		}
		if(winner()!=null){
			this.hasWinner = true;
		}
	}

	//returns winner of game
	public String winner(){
		boolean fire = false;
		boolean water = false;
		for(int i=0;i<this.size;i++){
			for(int j=0;j<this.size;j++){
				if(pieceAt(i, j)!=null){
					Piece survivor = pieceAt(i, j);
					if(survivor.side()==0){
						fire = true;
					} else {
						water = true;
					}
				}
			}
		}
		if(fire&&water){
			return null;
		}
		else if(fire&&(!water)){
			return "Fire";
		}
		else if((!fire)&&water){
			return "Water";
		} else {
			return "No one";
		}
	}

	//find filename of img to display for a piece
	private String findImg(Piece piece){
		String result;

		if(piece.isBomb()){
			result = "img/bomb-";
		}
		else if(piece.isShield()){
			result = "img/shield-";
		} else {
			result = "img/pawn-";
		}

		if(piece.isFire()){
			result = result + "fire";	
		} else {
			result = result + "water";
		}

		if(piece.isKing()){
			result = result + "-crowned";
		}

		result = result + ".png";
		return result;
	}

	//called every iteration to draw/update the board
	private void drawBoard(){
		StdDrawPlus.setXscale(0, this.size);
		StdDrawPlus.setYscale(0, this.size);
		for(int i=0;i<this.size;i++){
			for(int j=0;j<this.size;j++){
				if((this.hasSelection||this.multiMode)&&(i==selection[0])&&(j==selection[1])){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if((i+j)%2==0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);	
				}
				StdDrawPlus.filledSquare(i+.5, j+.5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if(pieceAt(i, j) != null){
					Piece piece = pieceAt(i, j);
					StdDrawPlus.picture(i+.5,j+.5, findImg(piece), 1, 1);	
				}
			}
		}
	}

	//monitor le inputs
	private void inputHelper(){
		if(StdDrawPlus.mousePressed()){
			int mouseX = (int) StdDrawPlus.mouseX();
			int mouseY = (int) StdDrawPlus.mouseY();
			if(canSelect(mouseX, mouseY)){
				select(mouseX, mouseY);
			}
		}
		if(StdDrawPlus.isSpacePressed()){
			if(canEndTurn()){
				endTurn();
			}
		}
	}

	//initializes game gui and stuff
	public static void main(String[] args){
		Board b = new Board(false);
		while(!b.hasWinner){
			b.inputHelper();
			b.drawBoard();
			StdDrawPlus.show(25);	
		}
	}
}