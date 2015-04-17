public class Board {

	private Board b;
	private boolean shouldBeEmpty;
	private Piece[][] piece=new Piece[8][8];
	private Piece[][] pieceKinged=new Piece[8][8];
	private int whosTurn=0;
	private boolean canMove=true;
	private int currX=9;
	private int currY=9;
    private boolean hasCapturedMove=false;
    private int hasMoved=0;

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                if (shouldBeEmpty==false) {
                	setPiece(i,j);
                }

                
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
    	
    	if (shouldBeEmpty==false) {

        for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if (j==0 & i%2==0) {
                		piece[i][j]=new Piece(true, b, i,j, "pawn");
                }
                
                if (j==1 & i%2==1) {
                		piece[i][j]=new Piece(true, b, i,j, "shield");
                }

                if (j==2 & i%2==0) {
                	piece[i][j]=new Piece(true, b,i,j, "bomb");
                }

                //water pieces
                if (j==5 & i%2==1){
                	piece[i][j]=new Piece(false, b,i,j, "bomb");
                }

                if (j==6 & i%2==0){
                	piece[i][j]=new Piece(false, b,i,j, "shield");
                }

                if (j==7 & i%2==1){
                	piece[i][j]=new Piece(false, b,i,j, "pawn");
                }
           	}
        }
    }
        //drawBoard(8);
    }

    private void setPiece(int i, int j) {
    	//for (int i = 0; i < 8; i++) {
    		//for (int j = 0; j < 8; j++) {
    			if (piece[i][j]!=null) {
    			if (piece[i][j].isFire()==true){
    				if (piece [i][j].isKing()==true) {
    					if (piece[i][j].isBomb()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/bomb-fire-crowned.png",1,1);
						} else if (piece[i][j].isShield()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/shield-fire-crowned.png",1,1);
						} else {
							StdDrawPlus.picture(i+.5,j+.5,"img/pawn-fire-crowned.png",1,1);
						}


    				} else {
    					if (piece[i][j].isBomb()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/bomb-fire.png",1,1);
						} else if (piece[i][j].isShield()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/shield-fire.png",1,1);
						} else {
							StdDrawPlus.picture(i+.5,j+.5,"img/pawn-fire.png",1,1);
						}

    				}
    			}else {
    				if (piece [i][j].isKing()==true) {
    					if (piece[i][j].isBomb()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/bomb-water-crowned.png",1,1);
						} else if (piece[i][j].isShield()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/shield-water-crowned.png",1,1);
						} else {
							StdDrawPlus.picture(i+.5,j+.5,"img/pawn-water-crowned.png",1,1);
						}


    				} else {
    					if (piece[i][j].isBomb()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/bomb-water.png",1,1);
						} else if (piece[i][j].isShield()==true) {
							StdDrawPlus.picture(i+.5,j+.5,"img/shield-water.png",1,1);
						} else {
							StdDrawPlus.picture(i+.5,j+.5,"img/pawn-water.png",1,1);
						}

    				}

    			}
    		}
    		//}
    	//}
    }

    //start from pieceAt
    public Piece pieceAt(int x, int y) {
    	if (x>=8||y>=8){
    		return null;
    	}
    	return piece[x][y];
    }

    public boolean canSelect(int x, int y) {
    	if (piece[x][y]!=null) {
    		if (whosTurn==piece[x][y].side()){
                if (canMove){
                    currX=x;
                    currY=y;
                    return true;
                } 
                /** if (piece[x][y].hasCaptured()){
                    return true;
                } */
            }
            
    	} else if (validMove(currX, currY, x, y)==true){
    		return true;
    	} else if (hasCapturedMove){
            if (validMove(currX,currY,x,y)){
                hasCapturedMove=false;
                return true;
            }
        }
    	return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if (xi>8 || yi>8 || xf>8 || yf>8 || piece[xi][yi]==null){
    		return false;
    	}
    		if (piece[xi][yi].isKing()!=true){
    		if (pieceAt(xf,yf)==null) {
                if (piece[xi][yi].isFire()==true){
                    if (piece[xi][yi].hasCaptured()!=true) {
                        hasCapturedMove=false;
                        if (yf==yi+1 && xf==xi+1 && canMove==true){
                        canMove=false;
                        return true;
                    } else if (yf==yi+1 & xf==xi-1&& canMove==true) {
                        canMove=false;
                        return true;
                    } 
                    }
                    if (xf==xi+2 & yf==yi+2){
                        if (piece[xi+1][yi+1]!=null && piece[xi][yi].side()!=piece[xi+1][yi+1].side()){
                            remove(xi+1,yi+1);
                            return true;
                        }    
                    } else if (xf==xi-2 & yf==yi+2) {
                        if (piece[xi-1][yi+1]!=null && piece[xi][yi].side()!=piece[xi-1][yi+1].side()){
                            remove(xi-1,yi+1);
                            return true;
                        }
                    }
    			} else if (piece[xi][yi].isFire()!=true){
    				if (piece[xi][yi].hasCaptured()!=true) {
                        hasCapturedMove=false;
                        if (yf==yi-1 & xf==xi+1 && canMove==true){
                            canMove=false;
                        return true;
                    } else if (yf==yi-1 & xf==xi-1 && canMove==true) {
                        canMove=false;
                        return true;
                    } 
                } 
                if (xf==xi+2 & yf==yi-2){
                        if (piece[xi+1][yi-1]!=null && piece[xi][yi].side()!=piece[xi+1][yi-1].side()){
                            remove(xi+1,yi-1);
                            return true;
                        }    
                    } else if (xf==xi-2 & yf==yi-2) {
                        if (piece[xi-1][yi-1]!=null&& piece[xi][yi].side()!=piece[xi-1][yi-1].side()){
                            remove(xi-1,yi-1);
                            return true;
                        }
                    }
    			} 
    		}
    	} else if (piece[xi][yi].isKing()==true) {
                if (pieceAt(xf,yf)==null) {
    			     if (piece[xi][yi].hasCaptured()!=true){
                        if (canMove==true){
                            canMove=false;
                            if (xf==xi+1 || xf==xi-1){
                            if (yf==yi+1 || yf==yi-1) {
                                return true;
                            }
                            }
                        }
                        
                     }
                if (xf==xi+2 & yf==yi+2){
                if (piece[xi+1][yi+1]!=null && piece[xi][yi].side()!=piece[xi+1][yi+1].side()) {
                    remove(xi+1,yi+1);
                    return true;
                }
                
            } else if (xf==xi-2 & yf==yi+2){
                if (piece[xi-1][yi+1]!=null && piece[xi][yi].side()!=piece[xi-1][yi+1].side()){
                    remove(xi-1,yi+1);
                    return true;
                }
            } else if (xf==xi+2 & yf==yi-2){
                if (piece[xi+1][yi-1]!=null && piece[xi][yi].side()!=piece[xi+1][yi-1].side()){
                    remove(xi+1,yi-1);
                    return true;
                }
            } else if (xf==xi-2 & yf==yi-2){
                if (piece[xi-1][yi-1]!=null && piece[xi][yi].side()!=piece[xi-1][yi-1].side()){
                    remove(xi-1,yi-1);
                    return true;
                }
            }
            }
    		} 

    	return false;	

    
    }

    public void select(int x, int y) {
    	if (piece[x][y]==null) {
    		if (currX!=x || currY!=y){
                validMove(currX,currY,x,y);
                piece[currX][currY].move(x,y);
                piece[x][y]=piece[currX][currY];
                remove(currX,currY);
                piece[currX][currY]=null;   
                canMove=false;
                hasMoved=1;
                currX=x;
                currY=y;
                if (piece[x][y].hasCaptured()) {
                    hasCapturedMove=true;
                    piece[x][y].doneCapturing();
                    if (piece[x][y].isBomb()){
                        hasCapturedMove=false;
                        explosion(x,y);
                        // canMove=false;
                    }
                }
            }
    	} else {
    		currX=x;
    		currY=y;
    	}
    }

   public void place(Piece p, int x, int y) {
    	if (x<8 & y<8 & p!=null){
    		p.move(x,y);
    		piece[x][y]= p;
            p=null;
    	}
	}

    public Piece remove(int x, int y) {
    	if (x>=8 || y>=8){
    		System.out.println("This piece does not exist.");
    	} else if (piece[x][y]==null) {
    		System.out.println("There is no piece at this position");
    	}
    	Piece p=piece[x][y];
    	piece[x][y]=null;
    	return p;
    }

    private void explosion(int x, int y) {
        int xSquare;
        int ySquare;
        int yMedia;
        if (x>1){
            xSquare=x-1;
        } else {
            xSquare=0;
            
        }
        if(y>1) {
            ySquare=y-1;
            yMedia=y-1;
        } else{
            ySquare=0;
            yMedia=0;
        }
        
        int xFS;
        int yFS;
        if (x<7){
            xFS=x+1;
        } else{
            xFS=7;
        }
        if (y<7) {
            yFS=y+1;
        } else {
            yFS=7;
        }
        while (xSquare <= xFS){
            ySquare=yMedia;
            while (ySquare <= yFS) {
                if (piece[xSquare][ySquare]!=null && piece[xSquare][ySquare].isShield()!=true){
                    remove(xSquare,ySquare);
                }
                ySquare=ySquare+1;
            }
                xSquare=xSquare+1;
        }
    remove(x,y);
    }

    public boolean canEndTurn() {
            
            if (hasMoved==1 && canMove==false){
                return true;
            } 
        hasCapturedMove=false;
        return false;
        }

    public void endTurn() {
        if (whosTurn==0){
            whosTurn=1;
        } else{
            whosTurn=0;
        }
        canMove=true;
        hasCapturedMove=false;
        currX=0;
        currY=0;
    }

    public String winner() {
    	int countFire=0;
        int countWater=0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++){
                if (piece[i][j]!=null && piece[i][j].isFire()==true){
                    countFire+=1;
                } else if (piece[i][j]!=null && piece[i][j].isFire()!=true){
                    countWater+=1;
                }
            }
        }
        if (countFire==0 && countWater==0){
            return "No one";
        } else if (countWater==0){
            return "Fire";
        } else if (countFire==0){
            return "Water";
        }
        return null;
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Boolean canMove=true;
        // pieces = new boolean[N][N];
        Board b= new Board(false);
        b.drawBoard(8);
        //int a=0;
        //a= p.side(true);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        int xPos=0;
        int yPos=0;
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                xPos = (int)StdDrawPlus.mouseX();
                yPos = (int)StdDrawPlus.mouseY();
                	if (b.canSelect(xPos,yPos)==true) {
                        b.select(xPos,yPos);
                        b.drawBoard(8);
                	}
            }
            if (StdDrawPlus.isSpacePressed()){

                    if (b.canEndTurn()){
                    b.endTurn();
                }
                }
                

                        
            StdDrawPlus.show(100);
        }
    } 
 }

