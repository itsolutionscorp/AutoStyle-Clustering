public class Board{
	private  Piece[][] pieces = new Piece[8][8];
	private int x1, y1, x2, y2;
	private boolean turn, selectnewpiece; //will be true for fire and false for water
	private boolean continuegame = true;
	private Piece pc;
	private boolean captured;
	private boolean bombcaptured;
	private boolean eturn;

	public static void main(String args[]){
		Board bd = new Board(false);
		while (bd.continuegame){
			if (StdDrawPlus.mousePressed()){
				double xint = StdDrawPlus.mouseX();
				double yint = StdDrawPlus.mouseY();
				int z1 = (int)xint;
				int z2 = (int)yint;
				if (bd.canSelect(z1,z2)==true){
					bd.select(z1,z2);
				}
			}
			if (bd.canEndTurn()==true && StdDrawPlus.isSpacePressed()==true){
				bd.endTurn();
			}
			//StdDrawPlus.show(100);
			if (bd.winner()!=null){
				bd.endTurn();
				System.out.println(bd.winner());
				bd.continuegame = false;
			}
		}
	}
	public Board(boolean shouldBeEmpty){
		int N = 8;
		turn = true;
		x1 = 9;
		x2 = 9;
		y2 = 9;
		y1 = 9;
		captured = false;
		selectnewpiece = false;
		bombcaptured = false;
		eturn = false;
		if (shouldBeEmpty == true){
			StdDrawPlus.setXscale(0,N);
			StdDrawPlus.setYscale(0,N);
			drawBoard(N);
		}
		else {
			StdDrawPlus.setXscale(0,N);
			StdDrawPlus.setYscale(0,N);
			drawBoard(N);
			for (int i=0; i<N;i = i +2){
				int j = 0;
				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
				pieces[i][j]= new Piece(true,this,i,j,"pawn");
			}	
			for (int i=1; i<N;i = i +2){
				int j = 1;
				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
				pieces[i][j]= new Piece(true,this,i,j,"shield");
			}
			for (int i=0; i<N;i = i +2){
				int j = 2;
				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
				pieces[i][j]= new Piece(true,this,i,j,"bomb");
			}
			for (int i=1; i<N;i = i +2){
				int j = 7;
				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
				pieces[i][j]= new Piece(false,this,i,j,"pawn");
			}
			for (int i=0; i<N;i = i +2){
				int j = 6;
				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
				pieces[i][j]= new Piece(false,this,i,j,"shield");
			}
			for (int i=1; i<N;i = i +2){
				int j = 5;
				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
				pieces[i][j]= new Piece(false,this,i,j,"bomb");
			}
		}

	}
	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}
	public Piece pieceAt(int x, int y){
		if (x>7 || y> 7 || x<0 || y<0){
			return null;
		}
		else{
			return pieces[x][y];
		}
	}
	
	public void place(Piece p,int x, int y){
		if (p != null && x < 8 && y < 8 && x >-1 && y>-1){
			if (pieceAt(x,y)!= p && pieceAt(x,y)!=null){
				remove(x,y);
			}
			if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			pieces[x][y]=p;
			draw(p,x,y);
			
		}
	}

	public Piece remove(int x, int y){
		if (x>7 || y>7 ||  x<0 || y<0){
			System.out.println("Input out of bounds");
			return null;
		}
		else if (pieceAt(x,y)==null){
			System.out.println("No Piece at (x,y)");
			return null;
		}
		else{
			if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			Piece p = pieceAt(x,y);
			pieces[x][y]=null;
			return p;
		}
	}

	public boolean canSelect(int x,int y){
		if (x>7 || y>7 || x<0 || y<0){
			return false;
		}
		if (bombcaptured == true){
			return false;
		}
		if (pieceAt(x,y)!=null && (eturn == false||captured == false)){
			if(pieceAt(x,y).isFire()==turn){
				if (x1>7){
					return true;
				}
				else if (x2>7){
					return true;
				}
				return false;
			}
			else return false;
		}
		else if (this.pieceAt(x,y)==null){
			if (x1>7 || y1>7){
				return false;
			}
			else if (this.validMove(x1,y1,x,y)==true){
				return true;
			}
			else return false;
		}
		else return false;
	}

	private boolean validMove(int xi,int yi, int xf, int yf){
		Piece pc = pieceAt(xi,yi);
		if (pc == null){
			return false;
		}
		if (pc.isKing() != true){
			if (Math.abs(xi-xf)==1 && captured==false){
				if (pc.isFire()==true && (yf-yi)==1){
					return true;
				}
				else if (pc.isFire()==false && (yf-yi)==-1){
					return true;
				}
				else return false;
			}
			else if (Math.abs(xi-xf)==2){
				if (pc.isFire()==true && (yf-yi)==2 && this.pieceAt((xf+xi)/2,(yf+yi)/2)!=null &&this.pieceAt((xf+xi)/2,(yf+yi)/2).isFire()!=true){
					return true;
				}
				else if (pc.isFire()==false && (yf-yi)==-2 && this.pieceAt((xf+xi)/2,(yf+yi)/2)!=null &&this.pieceAt((xf+xi)/2,(yf+yi)/2).isFire()!=false){
					return true;
				}
				else return false;
			}
			else return false;
		}
		
		else { 
			if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1 && captured==false){
				return true;
			}
			else if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2){
				if (pc.isFire()==true && this.pieceAt((xf+xi)/2,(yf+yi)/2)!=null && this.pieceAt((xf+xi)/2,(yf+yi)/2).isFire()!=true){
					return true;
				}
				else if (pc.isFire()==false && this.pieceAt((xf+xi)/2,(yf+yi)/2)!=null && this.pieceAt((xf+xi)/2,(yf+yi)/2).isFire()!=false){
					return true;
				}
				else return false;
			}
			else return false; 
		}
	}

	private String getName(Piece p){
		if (p.isShield()==true){
			return "shield";
		}
		else if (p.isBomb() == true){
			return "bomb";
		}
		else return "pawn";
	}

	private void draw(Piece p, int x, int y){
		String img = "img/" + getName(p) + "-";
			if (p.isFire() == true){
				img = img + "fire";
			}
			else {
				img = img +"water";
			}
			if (p.isKing() == true){
				img = img + "-crowned";
			}
			img = img +".png";
			StdDrawPlus.picture(x + 0.5, y + 0.5, img, 1, 1);
	}

	public void select(int x,int y){
		if (pieceAt(x,y)!=null && (eturn == false||captured == false)){
			if(pieceAt(x,y).isFire()==turn){
				if (x2>7){
					selectnewpiece=true;
				}
			}
		}
		else if (this.pieceAt(x,y)==null){
			selectnewpiece = false;
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		if (x1>7 && pieceAt(x,y)!= null){
			x1 = x;
			y1 = y;
			draw(pieceAt(x,y),x,y);
		}
		else if (x1<8){
			if (selectnewpiece==true){
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(x1 + .5, y1 + .5, .5);
				draw(pieceAt(x1,y1),x1,y1);
				x1 = 9;
				y1 = 9;
				select(x,y);
			}
			else {
				x2=x;
				y2 = y;
				pc = pieces[x1][y1];
				pc.move(x,y);
				eturn = true;
				if (pieceAt(x,y)!= null && pieceAt(x,y).hasCaptured()==true){
					captured = true;
					x1 = x;
					y1 = y;
					x2 = 9;
					y2 = 9;
				}
				else if (pieceAt(x,y)== null){
					bombcaptured = true;
				}
			}
		}
	}

	public boolean canEndTurn(){
		if (captured == true || x2<8 || bombcaptured == true || eturn ==true){
			return true;
		}
		else return false;
		//return eturn;
	}

	public void endTurn(){
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		if (pieceAt(x2,y2)!= null){
			StdDrawPlus.filledSquare(x2 + .5, y2 + .5, .5);
			draw(pieceAt(x2,y2),x2,y2);
		}
		if (pieceAt(x1,y1)!= null && captured == true){
			pieceAt(x1,y1).doneCapturing();
			StdDrawPlus.filledSquare(x1 + .5, y1 + .5, .5);
			draw(pieceAt(x1,y1),x1,y1);
		}
		x1 = 9;
		x2 = 9;
		y1 = 9;
		y2 = 9;
		turn = !turn;
		selectnewpiece = false;
		pc = null;
		captured = false;
		bombcaptured = false;
		eturn = false;
	}

	public String winner(){
		outerloop1:
		for (int i =0; i<8; i++){
			for (int j =0; j<8; j++){
				if (pieces[i][j]!=null){
					break outerloop1;
				}
				else if (i==7 && j==7){
					return "No one";
				}
			}
		}
		outerloop2:
		for (int i =0; i<8; i++){
			for (int j =0; j<8; j++){
				if (pieces[i][j]!=null && pieces[i][j].isFire()==true){
					break outerloop2;
				}
				if (i==7 && j==7){
					return "Water";
				}
			}
		}
		outerloop3:
		for (int i =0; i<8; i++){
			for (int j =0; j<8; j++){
				if (pieces[i][j]!=null && pieces[i][j].isFire()!=true){
					break outerloop3;
				}
				if (i==7 && j==7){
					return "Fire";
				}
			}
		}
		return null;
	}
}