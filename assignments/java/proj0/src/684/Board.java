import java.lang.Math;

public class Board 
{ 
		private Piece[][] piece_array;

		// start and turn 
		private boolean gameOver, isFireTurn;
		private boolean hasSelected, hasCaptured, hasMoved, inCombo;
		private Piece currentPiece, targetPiece;
		private int currentXCord, currentYCord, targetXCord, targetYCord;

		public Board(boolean shouldBeEmpty){
			// System.out.println("(in)board generated at: "+this);
			int N = 8;
			if(shouldBeEmpty){
				piece_array = new Piece[N][N];
			}
			else{
				piece_array = new Piece[N][N];
				generatePieces(piece_array);		
			}		
			gameOver    = false;
			isFireTurn  = true;
			hasSelected = false;
			hasCaptured = false;
			hasMoved    = false;
			currentXCord = 0; 
			currentYCord = 0; 
			inCombo = false;
		}

		private void generatePieces(Piece[][] a ){
			int N = 8;
			boolean team = true, king =false;
			for (int y = 0; y < N; y++) {
				team = (y < N / 2);
				for (int x = 0; x < N; x++) {
					boolean isEven = (x%2==0);
					// is pawn
					if( (y==0 && isEven) || (y==(N-1) && !isEven ) ){
						a[x][y] = new Piece(team, this, x, y, "pawn");
					}
					// is shield
					if ( (y==1 && !isEven) || (y==(N-2) && isEven ) ){
						a[x][y] = new Piece(team, this, x, y, "shield");
					}
					// is bomb
					if ( (y==2 && isEven) || (y==(N-3) && !isEven ) ){
						a[x][y] = new Piece(team, this, x, y, "bomb");
					}
				}
			}		
		}

		private void drawBoard(int N){
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if((currentPiece != null) && currentPiece==piece_array[x][y]) 
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					else if ((x + y) % 2 == 0) 
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else                  
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				}
			}
		}


		private void drawPiece(int N){
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
						// except empty piece and captured item
					if(pieceAt(x,y)!=null && !pieceAt(x,y).hasCaptured()){
							// String pieceType = piece_array[x][y].pType;
						String team = "water", isKing = "-crowned";
						Boolean fire = piece_array[x][y].isFire();
						if(fire)
							team = "fire";

						if (piece_array[x][y].isKing()){
							team += isKing;
						}

						// draw each piece

						if( piece_array[x][y].isBomb() ){
							StdDrawPlus.picture(x + .5, y + .5, "img/bomb-"+team+".png", 1, 1);
						}
						else if( piece_array[x][y].isShield() )
							StdDrawPlus.picture(x + .5, y + .5, "img/shield-"+team+".png", 1, 1);
						else{
							StdDrawPlus.picture(x + .5, y + .5, "img/pawn-"+team+".png", 1, 1);
						}

					}
				}
			}
		}


		private boolean isOutBounded(int x, int y){
			if( x>7 || x<0 || y>7 || y<0 )	return true;
			else	return false;
		}


		private void spaceBar(){
			if( StdDrawPlus.isSpacePressed() && (hasMoved || hasCaptured) ){
				endTurn();
			}
		}

		private boolean validMove(int x, int y, int dx, int dy){
			if( !isOutBounded(x,y) ){
				// current Cords
				int distance = 2;
				int a = Math.abs(dx-x);
				int b = Math.abs(dy-y);
				int teamD = -1;
				Piece centerPiece = pieceAt((int)(x+dx)/2,(int)(y+dy)/2); 


				// range check  -> to see regular move for king and non-king

				// in range of 1
				if((a>0 && a < distance) && (b>0 && b < distance)){

					// king is correct 
					if(currentPiece.isKing()) return true;

					// non-king is correct
					else if( (currentPiece.isFire() && y<dy) || (!currentPiece.isFire() && y>dy) ) return true;
				}


				// in range - capture
				if((a>0 && a < distance+1) && (b>0 && b < distance+1)){

					// boolean isEnemy = (centerPiece.isFire() != currentPiece.isFire());
					if(centerPiece != null){
						//  king can move everywhere 
						if(currentPiece.isKing() && targetPiece==null){
							remove((int)(x+dx)/2,(int)(y+dy)/2);
							return true;
						}

						//  not king, 
						else if( (currentPiece.isFire() && y<dy) || (!currentPiece.isFire() && y>dy) ){

							if(currentPiece.isBomb())
							{
								// edge correction
								int jMax = dy+1;
								int jMin = dy-1;
								int iMax = dx+1;
								int iMin = dx-1;
								if(dy==7) jMax = 7;
								if(dy<=0) jMin = 0;
								if(dx==7) iMax = 7;
								if(dx<=0) iMin = 0;

								for(int j=jMin; j<=jMax; j++){
									for(int i=iMax; i>=iMin; i--){
										// System.out.println(i+" "+j);
										Piece tt = piece_array[i][j];
										boolean isEnemy = tt !=null&&(tt.isFire()!=currentPiece.isFire() );
										if(tt==null) continue;
										if( !tt.isShield() && isEnemy ){
											Piece temp = null;
											piece_array[i][j] = temp;
										}
											// if not shild 
											// die
									}
								}
								remove((x+dx)/2, (y+dy)/2);
								// remove(x,y);
								remove(x,y);
								hasSelected = false;
								hasCaptured = false;
								hasMoved    = false;
								Piece temp = null; 
								currentPiece=temp;
								// endTurn();
								if(isFireTurn){
									// System.out.println("water turn");
									isFireTurn = false;
								}
								else{
									// System.out.println("fire turn");
									isFireTurn = true;
								}

								return true;
							}
							// not bomb and capture
							else if(!currentPiece.isBomb()){
								//remove middle
								remove((int)(x+dx)/2,(int)(y+dy)/2);
								return true;
							}

							return true;
						}
					}	
				}

			}
			return false;
		}

		public boolean canSelect(int x, int y){
			if(isOutBounded(x,y)) return false;
			//turn match

			// base case
			if(!hasSelected) {
				// if choose empty piece
				if(hasMoved || hasCaptured) return false;
				if(pieceAt(x,y) == null)
					return false;
				// if choose a piece
				else{
					//check team
					if(isFireTurn && pieceAt(x,y).isFire() )
						return true;
					else if(!isFireTurn && !pieceAt(x,y).isFire() )
						return true;
				}
			}
			// after base case not captured and not moved yet
			else if(hasSelected && !(hasCaptured && hasMoved)){
				// System.out.println("("+ currentXCord + currentYCord + x + y +")");
				if(validMove(currentXCord, currentYCord, x, y)){
					// System.out.println("can move");
					return true;
				}
				else if(pieceAt(x,y)!= null && (pieceAt(x,y).isFire() == currentPiece.isFire() ) )
					return true;
				else{
					// System.out.println("cannot move");
					return false;
				}
			}
			// combo
			else if(hasSelected && hasCaptured && inCombo){
				System.out.println("c"+currentPiece+"t:"+ targetPiece);
				Piece centerPiece = pieceAt((int)(currentXCord+x)/2,(int)(currentYCord+ y)/2); 
				if(pieceAt(x,y)==null && centerPiece!= null && validMove(currentXCord, currentYCord, x, y))
					// remove((currentXCord+x)/2,(currentYCord+ y)/2);
					return true;
			}
			return false;
		}


		private void pieceSelect(int a, int b){
			// if (StdDrawPlus.mousePressed()) {
				int newX = a;
				int newY = b;

				if(hasSelected) {
					targetPiece = piece_array[newX][newY];
					targetXCord = newX;
					targetYCord = newY;
				}

				System.out.println("target: "+targetPiece);
				System.out.println("current: "+currentPiece);
				System.out.println("current x,y: "+currentXCord+", "+currentYCord);
				System.out.println("target x,y: "+targetXCord+", "+targetYCord);

				System.out.println("hasSelected: "+hasSelected);
				System.out.println("hasCaptured: "+hasCaptured);
				System.out.println("hasMoved: "+hasMoved);


				if( true ){
					// first select after turn start

					//base case
					if(pieceAt(newX, newY) != null && !hasSelected){
						currentPiece = piece_array[newX][newY];
						currentXCord = newX; 
						currentYCord = newY;
						hasSelected = true;
					}
					//once selected
					else if(hasSelected && !hasMoved && !hasCaptured){
						// System.out.println("secound option");

						//move to empty
						if(pieceAt(newX, newY) == null){
							// System.out.println("place");
							place(currentPiece, newX, newY);
						}

						// change piece in same team
						else if(pieceAt(a,b)!= null && (pieceAt(a,b).isFire() == currentPiece.isFire() || pieceAt(a,b)==currentPiece )){
							currentPiece = piece_array[a][b];
							currentXCord = a;
							currentYCord = b;
						}


					}
					// combo
					else if(hasCaptured && hasSelected && !hasMoved)
					{
						if(pieceAt(newX, newY) == null  &&  pieceAt( (newX+currentXCord)/2, (newY+currentYCord)/2 ) != null )
							// place(currentPiece, newX, newY);
							// currentPiece = piece_array[a][b];

							// capture remove
							remove(a,b);
							// move
							place(currentPiece, a, b);


					}
				}
		}


		public Piece pieceAt(int x, int y){ 
			if(isOutBounded(x,y)) return null;
			if(piece_array[x][y] == null) return null;
			return piece_array[x][y];
		} 


		public void select(int x, int y) {
			pieceSelect(x,y);
		}


		public Piece remove(int x, int y) {
			if(isOutBounded(x,y)){
				// System.out.println("outbounded");
				Piece temp = null;
				return temp;
			}
			else{
				Piece a = null;
				piece_array[x][y] = a;
				return piece_array[x][y];
			}
		}

		public boolean canEndTurn() {
			if(hasCaptured || hasMoved)
				return true;
			return false;
		}

		public void endTurn() {
			
			if(canEndTurn()){
				if(isFireTurn){
					// System.out.println("water turn");
					isFireTurn = false;
				}
				else{
					// System.out.println("fire turn");
					isFireTurn = true;
				}
				// turnOver = true;
				hasSelected = false;
				hasCaptured = false;
				hasMoved = false;
				Piece temp = null;
				currentPiece = temp;

			}

		}

		public String winner(){
				boolean noPiece = true;
				int fireSurvive=0, waterSurvive=0;

				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						if(piece_array[x][y]!=null){
							noPiece = false;
							if(piece_array[x][y].isFire())
								fireSurvive += 1;
							if(!piece_array[x][y].isFire())
								waterSurvive += 1;
						}
						else if(piece_array[x][y]==null){
							continue;
						}
					}	
				}			



				if(fireSurvive>0 && waterSurvive ==0)
					return "Fire";
				if(fireSurvive==0 && waterSurvive >0)
					return "water";
				if(fireSurvive>0 && waterSurvive >0)
					return null;
				if(!noPiece)
					return "No one";
				return "No one";
		} 


		public void place(Piece p, int x, int y) {
			if(isOutBounded(x,y)) return;
			if(p == null) return;
			piece_array[x][y] = p;
			p.move(x,y);
			Piece temp = null;
			p = temp;
			remove(currentXCord,currentYCord);
			if( Math.abs(currentXCord-x)==1 && Math.abs(currentYCord-y)==1  ) 
				hasMoved = true;
			hasCaptured = true;
			inCombo = true;
			currentXCord = x;
			currentYCord = y;
			// hasSelected = false;	

		}


		// starts a GUI supported version of the game
		public static void main(String[] args){
			int N = 8;
			int mouseX, mousey;
			StdDrawPlus.setXscale(0, N);
			StdDrawPlus.setYscale(0, N);
			boolean shouldBeEmpty = false;
			Board b = new Board(shouldBeEmpty);

			while(true) {
				b.drawBoard(N);
				b.drawPiece(N);
				
				if (StdDrawPlus.mousePressed()) 
				{
            		int x = (int) StdDrawPlus.mouseX();
            		int y = (int) StdDrawPlus.mouseY();
            
	            	if (b.canSelect(x, y)) 
	                	b.select(x, y);    
        		}

				b.spaceBar();	
				b.winner();
				StdDrawPlus.show(100);
			}

		}
}

