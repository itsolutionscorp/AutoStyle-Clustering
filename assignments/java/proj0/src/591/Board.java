
public class Board{
	private Piece[][] pieces;
	private static final int N = 8;
	private Piece selected = null;
	private boolean fireTurn = true;
	private boolean hasMoved = false;
	private int sX;
	private int sY;
	
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[N][N];
		if(!shouldBeEmpty){
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					/**add the fire pawns*/
					if((i==0 && j==0)||(i==2 && j==0)||(i==4 && j==0)||(i==6 && j==0)){
						pieces[i][j] = new Piece(true,this,i,j,"pawn");
					}
					/**add the fire shields*/
					else if((i==1 && j==1)||(i==3 && j==1)||(i==5 && j==1)||(i==7 && j==1)){
						pieces[i][j] = new Piece(true,this,i,j,"shield");
					}
					/**add the fire bombs*/
					else if((i==0 && j==2)||(i==2 && j==2)||(i==4 && j==2)||(i==6 && j==2)){
						pieces[i][j] = new Piece(true,this,i,j,"bomb");
					}
					/**add the water bombs*/
					else if((i==1 && j==5)||(i==3 && j==5)||(i==5 && j==5)||(i==7 && j==5)){
						pieces[i][j] = new Piece(false,this,i,j,"bomb");
					}
					/**add the water shields*/
					else if((i==0 && j==6)||(i==2 && j==6)||(i==4 && j==6)||(i==6 && j==6)){
						pieces[i][j] = new Piece(false,this,i,j,"shield");
					}
					/**add the water pawns*/
					else if((i==1 && j==7)||(i==3 && j==7)||(i==5 && j==7)||(i==7 && j==7)){
						pieces[i][j] = new Piece(false,this,i,j,"pawn");
					}
				}
			}
		}
	}
	
	private void drawBoard(){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				Piece curPiece = pieceAt(i,j);
				String elem = "water";
				String type = "pawn";
				String king = "";
				if(curPiece == null){
					continue;
				}
				else{
					if(curPiece.isFire()){
						elem = "fire";
					}
					if(curPiece.isBomb()){
						type = "bomb";
					}
					if(curPiece.isShield()){
						type = "shield";
					}
					if(curPiece.isKing()){
						king = "-crowned";
					}
					StdDrawPlus.picture(i + .5, j + .5, "img/"+type+"-"+elem+king+".png", 1, 1);
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y){
		if(x>N || y>N){
			return null;
		}
		return pieces[x][y];
	}
	
	public void place(Piece p, int x, int y){
		if(!(x>N || y>N)){
			pieces[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y){
		if(x>N || y>N){
			System.out.println("removal failed: index out of bounds");
			return null;
		}
		else if(pieces[x][y] == null){
			System.out.println("removal failed: no piece at that index");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}
	
	public void select(int x, int y){
		if(pieceAt(x,y) != null){
			selected = pieceAt(x,y);
			sX = x;
			sY = y;
		}
		else{
			selected.move(x,y);
			sX = x;
			sY = y;
			hasMoved = true;
		}
	}
	
	public boolean canEndTurn(){
		if(hasMoved || (selected != null && selected.hasCaptured())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void endTurn(){
		fireTurn = !fireTurn;
		hasMoved = false;
		selected.doneCapturing();
		selected = null;
	}
	
	public String winner(){
		int numFire = 0;
		int numWater = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(pieceAt(i,j) == null){
					continue;
				}
				else if(pieceAt(i,j).isFire()){
					numFire += 1;
 				}
				else{
					numWater += 1;
				}
			}
		}
		if(numWater == 0 && numFire == 0){
			return "No one";
		}
		else if((numWater > numFire)&&(numFire == 0)){
			return "Water";
		}
		else if((numFire > numWater)&&(numWater == 0)){
			return "Fire";
		}
		else if(numWater != 0 && numFire !=0){
			return null;
		}
		else{
			return null;
		}
	}
	
	public boolean canSelect(int x, int y){
		Piece target = pieceAt(x,y);
		if((target == null)){
			if((selected != null && !hasMoved && validMove(x,y, false))||(selected != null && selected.hasCaptured() && validMove(x,y, true))){
				return true;
			}
			else{
				return false;
			}
		}
		else if((selected == null ||(selected != null && !hasMoved))&& target.isFire() == fireTurn){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean validMove(int x, int y, boolean multiCap){
		if(multiCap){
			if(Math.abs(sX-x) == 2){
				Piece jumpedPiece = pieceAt((x+sX)/2,(y+sY)/2);
				if((selected.isKing() || selected.isFire()) && (y-sY == 2) && jumpedPiece != null && jumpedPiece.isFire() != fireTurn){
					return true;
				}
				else if((selected.isKing() || !selected.isFire()) && (y-sY == -2) && jumpedPiece != null && jumpedPiece.isFire() != fireTurn){
					return true;
				}
			}
		}
		else{
			if(Math.abs(sX-x) == 1){
				if((selected.isKing() || selected.isFire()) && (y-sY == 1)){
					return true;
				}
				else if((selected.isKing() || !selected.isFire()) && (y-sY == -1)){
					return true;
				}
			}
			else if(Math.abs(sX-x) == 2){
				Piece jumpedPiece = pieceAt((x+sX)/2,(y+sY)/2);
				if((selected.isKing() || selected.isFire()) && (y-sY == 2) && jumpedPiece != null && jumpedPiece.isFire() != fireTurn){
					return true;
				}
				else if((selected.isKing() || !selected.isFire()) && (y-sY == -2) && jumpedPiece != null && jumpedPiece.isFire() != fireTurn){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args){
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		while(true){
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x,y)){
					b.select(x,y);
				}
            }
			if(StdDrawPlus.isKeyPressed(32) && b.canEndTurn()){
				b.endTurn();
			}
			StdDrawPlus.show(100);
		}
	}
}