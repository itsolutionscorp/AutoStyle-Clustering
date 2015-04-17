import java.lang.Math;
//this is the good stuff
public class Board {
	private Piece [][] pieces;
	private final int FIRE=0, WATER=1;
	private String[] types;
	private String filepath;
	private int turn;
	private int xi;
	private int yi;
	private boolean hasMoved;
	private Piece selectedPiece;
	private int turnCount;
	public Board(boolean empty){
		filepath="c:/Users/Benjamin/cs61b/azj/proj0/img/";
		types = new String [] {"pawn", "shield","bomb"};
		pieces=new Piece[8][8];
		turn=FIRE;
		turnCount=0;
		hasMoved=false;
		selectedPiece=null;
		if(empty){
			return ;
		}
		else{
			Piece p;
			for(int row=0; row<8; row++){
				for(int col=0; col<8; col++){
					boolean isFire= (row<4);
					if((col+row) % 2==0){
						if(row==0 || row ==7){
							p=new Piece(isFire, this, col,row, types[0]);
//							this.place(p, row, col);
						}
						if(row == 1 || row == 6){
							p=new Piece(isFire, this, col,row, types[1]);
//							this.place(p, row, col);
						}
						if(row == 2 || row == 5){
							p=new Piece(isFire, this, col,row, types[2]);
//							this.place(p, row, col);
						}
					}
				}
			}
		}
	}
	public static void main (String[]args){
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b= new Board(false);
		while(b.winner()==null) {
			b.drawBoard(N);
			if (StdDrawPlus.mousePressed()) {
				if(b.canSelect((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY())){
					b.select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());	
				}

			}
			if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
					b.endTurn();					
			}
			StdDrawPlus.show(100);
		}
		System.out.println("the winner is "+b.winner());
		b.drawBoard(N);
		StdDrawPlus.show(100);
	}
	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(selectedPiece!=null && !hasMoved){
					if(i==xi && j==yi) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					else if ((i + j) % 2 == 0) 
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else StdDrawPlus.setPenColor(StdDrawPlus.RED);		
				}
				else {
					//					System.out.println("this shit is working");
					if ((i + j) % 2 == 0) 
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else StdDrawPlus.setPenColor(StdDrawPlus.RED);	

				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				//				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j]!=null) {
					Piece temp=pieces[i][j];
					String currType;
					String team;
					if(temp.isBomb()) currType="bomb";
					else if(temp.isShield()) currType="shield";
					else currType="pawn";
					if(temp.isFire()) team="-fire";
					else team="-water";
					if(temp.isKing()) team+="-crowned";
					team+=".png";
					StdDrawPlus.picture(i + .5, j + .5, filepath+currType+team, 1, 1);
				}
			}
		}
	}
	public Piece pieceAt(int x,int y){
		if((x<8 && y<8) && (x>=0 && y>=0)){
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y){
		if(x>7 || y>7 || x<0 || y<0)return false;
		Piece p=pieceAt(x,y);
		if(selectedPiece!=null){
			if(p==null){
				return isValidMove(this.xi, this.yi, x, y);
			}
			else if(p.side()==turn && !hasMoved){
				return true;
			}
			return false;
		}
		
		else if(selectedPiece==null && p!=null && p.side()==turn){
			return true;
		}
		return false;
	}

	private boolean isValidMove(int xi,int yi,int xf,int yf){
		Piece p=pieceAt(xf, yf);
		Piece orig=pieceAt(xi,yi);

		if(p!=null)return false;
		if(orig==null)return false;
//		if(hasMoved && !hasJumps(orig))return false;
		
		if(Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1){
			if(hasMoved) return false;
			if(!orig.isKing()){
				if(yi>yf && !orig.isFire()) return true;
				if(yi<yf && orig.isFire()) return true;
			}
			//if a piece is a king, then it can move into any unoccupied diagonal space
			if(orig.isKing())return true;
		}
		if(Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){
			if(!hasMoved || orig.hasCaptured())
				return isLegalJump(xi,yi,xf,yf);
		}


		return false;

	}
	//assumes diagonal coordinates are passed in
	private boolean isLegalJump(int xi, int yi, int xf, int yf){
		Piece orig=pieceAt(xi,yi);
		if(orig==null)return false;

		if(!orig.isKing()){
			if(yi<yf && orig.isFire()){
				Piece p=getJumpedPiece(xi,yi,xf,yf);
				if(p!=null)return p.side()!=orig.side();
			}
			if(yi>yf && !orig.isFire()){
				Piece p=getJumpedPiece(xi,yi,xf,yf);
				if(p!=null)return p.side()!=orig.side();
			}
		}
		if(orig.isKing()){
			Piece p=getJumpedPiece(xi,yi,xf,yf);
			if(p!=null)return p.side()!=orig.side();
		}
		return false;
	}

	private Piece getJumpedPiece(int xi, int yi, int xf, int yf){
		if(xi==xf || yi==yf)return null;
		if(xi<0 || xi>7 || yi<0 || yi>7 || xf<0 || xf>7 || yf<0 || yf>7){
			return null;
		}
		if(pieceAt(xf,yf)!=null){
			return null;
		}
		if(xi>xf){
			if(yi>yf){
				return pieceAt(xi-1,yi-1);
			}else return pieceAt(xi-1,yi+1);
		}
		else if(xi<xf){
			if(yi>yf){
				return pieceAt(xi+1,yi-1);
			}
		}
		return pieceAt(xi+1,yi+1);
	}

	public void select(int x, int y){
		Piece p= pieceAt(x,y);
		if(p!=null){
				selectedPiece=p;
				hasMoved=false;
		}
		else if((selectedPiece!=null && !hasMoved) || hasJumps(selectedPiece)){
			selectedPiece.move(x,y);
			hasMoved=true;
			turnCount+=1;
		}
		xi=x;
		yi=y;
		System.out.println("xi is "+xi+ " yi is "+yi);
	}

	public void place(Piece p, int x, int y){
		if(!(x>7 || y>7 || x<0 || y<0)){
			pieces[x][y]=p;
			hasMoved=true;
		}
	}

	public Piece remove(int x, int y){
		if(x>7 || y>7 || x<0 || y<0){
			System.out.println("homie, you trippin.  That's not a real square");
			return null;
		}
		else if(pieceAt(x,y)==null){
			System.out.println("bruh. Whatchu finna do? There ain't even a piece there");
			return null;
		}
		else{
			Piece temp= pieceAt(x,y);
			pieces[x][y]= null;
			return temp;
		}
	}

	public boolean canEndTurn(){
		if((hasMoved && turnCount>0)|| (selectedPiece!=null && selectedPiece.hasCaptured())){
			return true;
		}
		
		return false;
	}

	private boolean hasJumps(Piece p){
		if(p==null)return false;
		if(p.isBomb())return false;
		for(int i=xi-2; i<=xi+2; i=i+4){
			for(int j=yi-2; j<=yi+2; j=j+4){
				if(isLegalJump(xi, yi, i, j)){
					return true;
				}
			}
		}

		return false;
	}


	public void endTurn(){
		if(turn==WATER){
			turn=FIRE;
		}
		else{
			turn=WATER;
		}
		selectedPiece.doneCapturing();
		selectedPiece=null;
		hasMoved=false;
	}

	public String winner(){
		int firePieces=0;
		int waterPieces=0;
		Piece p;
		for(int x=0; x<8; x++){
			for(int y=0; y<8; y++){
				p=pieceAt(x,y);
				if(p!=null){
					if(p.isFire()) firePieces++;
					else waterPieces++;
				}
			}
		}
		if(firePieces!=0 && waterPieces!=0)return null;
		if(firePieces>0 && waterPieces==0)return "Fire";
		if(waterPieces>0 && firePieces==0)return "Water";
		return "no one";
		
	}

}