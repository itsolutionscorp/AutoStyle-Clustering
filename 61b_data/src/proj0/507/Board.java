public class Board {
	private Piece[][] pieces;
	private Boolean fireturn = true;
	private Boolean amiholdingapiece = false;
	private Piece justselected;
    private int justselectedx;
    private int justselectedy;
    private Boolean haveimovedyet = false;
    private Boolean stop = false;
    private Boolean eat = false;

	public static void main(String[] args) {
		// calls drawBoard
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board coolboard = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */

        //if player has selected piece boolean
        // check whose turn it is
        // check to see if piece at coordinate is null or not
        // then return true
        

        while(true) {
            coolboard.drawBoard(N);
            // boolean fireturn = true;
            if (StdDrawPlus.mousePressed()) {
                double xcoord = StdDrawPlus.mouseX();
                double ycoord = StdDrawPlus.mouseY();
                int x = (int)xcoord;
                int y = (int)ycoord;


                if (coolboard.canSelect(x,y)) {
                	System.out.println("if this is true " + coolboard.fireturn + "then its fire's turn");
                	coolboard.select(x,y);
                }  
            }
            if (StdDrawPlus.isSpacePressed()) {
            	System.out.println("yay spacebar");
            	if (coolboard.canEndTurn() == true) {
            		coolboard.endTurn();
            	}
            }
                	
          	coolboard.winner();
               	// coolboard.remove(x,y);

                        
            StdDrawPlus.show(100);
            
        }

	}
	private void drawBoard(int N) {
		//draws a new board everytime you move.
		//want the code to look at piecees. iterate through every single piece, find the 
		//coordinates then draw it if something is there.
		
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                Piece current = pieces[i][j];
	                String stringg;
						if (current != null) {
		                    if (current.isBomb()) {
		                   		stringg = "img/bomb";
			                } else if (current.isShield()) {
			                	stringg = "img/shield";
			                } else {
			                	stringg = "img/pawn";
			                }

			                if (current.isFire()) {
			                	stringg = stringg + "-fire";
			                } else {
			                 	stringg = stringg + "-water";
			                }
			                   	
			                if (current.isKing()) {
			                	stringg = stringg + "-crowned";
			                }
			                StdDrawPlus.picture(i + .5, j + .5, stringg + ".png", 1, 1);
			                }
		            
		        }
			}
	}
	public Board(boolean shouldBeEmpty) {
	// constructors don't have void.
	//create double array
	//constructor that creates the beginning setup and starting positions.
		int N = 8;
        pieces = new Piece[N][N];
        for (int i = 0; i < N; i++) {
        	if (shouldBeEmpty == true) {
				continue;
			} else {
	        	if (i % 2 == 0) {
	        		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
	        		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
	        		pieces[i][6] = new Piece(false, this, i, 6, "shield");
	        	} else {
	        		pieces[i][1] = new Piece(true, this, i, 1, "shield");
	        		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
	        		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
	        	}
	        }
        }
	}


	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		} else {
			Piece gotit = pieces[x][y];
			if (gotit == null) {
				//System.out.println("got a null piece");
				return null;
			} else {
				//System.out.println(gotit + "<-- gotit piece");
				return gotit;
			}
		}
	}

	public boolean canSelect(int x, int y) {
		if (stop) {
			return false;
		}

		System.out.println(haveimovedyet);
		System.out.println(amiholdingapiece + "asdf");
		Piece piecetemp = pieceAt(x,y);
		Piece mid = pieceAt((justselectedx + x)/2 , (justselectedy + y)/ 2);
		if (piecetemp != null){
			// if (haveimovedyet == true) {
			// 	return false;
			// } else {
				// if (amiholdingapiece == true) {
			// amiholdingapiece = true;
				// 	// return false;
				// } else {
				// }
					if (this.fireturn == true) {
						if (piecetemp.isFire()) {
							if (justselected != null) {
								if (!haveimovedyet) {
									System.out.println("selected a second piece, but havent moved yet");
									return true;
								}
								return false;
							}
							else {
								System.out.println("selecting first piece");
								return true;
							}
						}
					}
					if (this.fireturn == false) {
						if (!piecetemp.isFire()) {
							if (justselected != null) {
								if (!haveimovedyet) {
									System.out.println("selected a second piece, but havent moved yet");
									return true;
								}
								return false;
							}
							else {
								System.out.println("selecting first piece");
								return true;
							}
						}
					}

			return false;
			 
		}
		else {

			if (justselected == null) {
				return false;
			}
			// if (amiholdingapiece == true) {
				if (justselected.isFire()){
					System.out.println("isFire");
					// if (haveimovedyet == true) {
					// 	return false;
					// }
					if (!haveimovedyet) {
						if (justselected.isKing()) {
							if (((justselectedx - 1 == x) || (justselectedx + 1 == x)) && ((justselectedy - 1 == y) || (justselectedy + 1 == y))){
								System.out.println("im a king and valid move. pressed on null");
								stop = true;
								return true;
							}

							if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && ((justselectedy - 2 == y) || (justselectedy + 2 == y))){
								System.out.println("king, valid capture, pressed null");
								return true;
							}
							return false;
						}
						if ((justselectedx - 1) == x && (justselectedy + 1) == y) {
							System.out.println("valid move");
							stop = true;
							return true; //if upperleft square is clicked
						} 
						else if ((justselectedx + 1) == x && (justselectedy + 1) == y) {
							System.out.println("valid move");
							stop = true;
							return true; //if upperright square is clicked
						}

						if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && (justselectedy + 2 == y)) {
							System.out.println("valid capture");
							return true;
						}
						System.out.println("pressed on null, havent moved.. but cant move");
						return false;
					}

					else if (eat) {
						if (justselected.isKing() && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && ((justselectedy - 2 == y) || (justselectedy + 2 == y))) {
							if (((justselectedx - 1 == x) || (justselectedx + 1 == x)) && ((justselectedy - 1 == y) || (justselectedy + 1 == y))){
								System.out.println("king, valid move, pressed null");
								stop = true;
								return true;
							}

							if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && ((justselectedy - 2 == y) || (justselectedy + 2 == y))){
								System.out.println("king, valid capture, pressed null");
								return true;
							}
							return false;
						}
						// if ((justselectedx - 1) == x && (justselectedy + 1) == y) {
						// 	System.out.println("valid move");
						// 	stop = true;
						// 	return true; //if upperleft square is clicked
						// } 
						// else if ((justselectedx + 1) == x && (justselectedy + 1) == y) {
						// 	System.out.println("valid move");
						// 	stop = true;
						// 	return true; //if upperright square is clicked
						// }

						if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && (justselectedy + 2 == y)) {
							System.out.println("valid capture");
							return true;
						}
						System.out.println("presseed on null, havent move, cant move");
						return false;
					}
						

						// if ((pieceAt((justselectedx - 1), (justselectedy + 1)) != null)) { //theres a piece on upperleft
						// 	System.out.println((pieceAt((justselectedx - 1), (justselectedy + 1)) != null)+" lalala");
						// 	x = x - 2;
						// 	y = y + 2;
						// 	return true;
						// }
						// else if ((pieceAt((justselectedx + 1), (justselectedy + 1)) != null)) { //theres a piece on upperright
						// 	x = x + 2;
						// 	y = y + 2;
						// 	return true;
						// }
						//only if fire kinged then can capture down use absolute value
						System.out.println("cant move");
						return false;
						// else {
						// 	return false;
						// }
				}


				if (!justselected.isFire()){
					// if (haveimovedyet == true) {
					// 	return false;
					// }
					if (!haveimovedyet) {
						if (justselected.isKing()) {
							if (((justselectedx - 1 == x) || (justselectedx + 1 == x)) && ((justselectedy - 1 == y) || (justselectedy + 1 == y))){
								System.out.println("king valid move");
								stop = true;
								return true;
							}

							if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && ((justselectedy - 2 == y) || (justselectedy + 2 == y))){
								System.out.println("king valid capture");
								return true;
							}
							return false;
						}
						if ((justselectedx - 1) == x && (justselectedy - 1) == y) {
							System.out.println("valid move");
							stop = true;
							return true; //if upperleft square is clicked
						} 
						else if ((justselectedx + 1) == x && (justselectedy - 1) == y) {
							System.out.println("valid move");
							stop = true;
							return true; //if upperright square is clicked
						}

						if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && (justselectedy - 2 == y)) {
							System.out.println("valid capture");
							return true;
						}
						return false;
					}

					else if (eat) {
						if (justselected.isKing() && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && ((justselectedy - 2 == y) || (justselectedy + 2 == y))) {
							if (((justselectedx - 1 == x) || (justselectedx + 1 == x)) && ((justselectedy - 1 == y) || (justselectedy + 1 == y))){
								System.out.println("king valid move");
								stop = true;
								return true;
							}

							if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && ((justselectedy - 2 == y) || (justselectedy + 2 == y))){
								System.out.println("king valid capture");
								return true;
							}
							return false;
						}
						// if ((justselectedx - 1) == x && (justselectedy - 1) == y) {
						// 	System.out.println("valid move");
						// 	stop = true;
						// 	return true; //if upperleft square is clicked
						// } 
						// else if ((justselectedx + 1) == x && (justselectedy - 1) == y) {
						// 	System.out.println("valid move");
						// 	stop = true;
						// 	return true; //if upperright square is clicked
						// }

						if ((mid != null) && ((justselectedx - 2 == x) || (justselectedx + 2 == x)) && (justselectedy - 2 == y)) {
							System.out.println("valid capture");
							return true;
						}
						return false;
					}


						// if ((justselectedx - 1) == x && (justselectedy - 1) == y) {
						// 	return true; //if lowerleft square is clicked
						// }
						// else if ((justselectedx + 1) == x && (justselectedy - 1) == y) {
						// 	return true; //if lowerright square is clicked
						// } 
						// System.out.println("hi im a water piece");
						// if ((pieceAt((justselectedx - 1), (justselectedy - 1)) != null)) { //theres a piece on lowerleft
						// 	System.out.println((pieceAt((justselectedx - 1), (justselectedy + 1)) != null)+" hohohohoho");
						// 	x = x - 2;
						// 	y = y - 2;
						// 	return true;
						// }
						// if ((pieceAt((justselectedx + 1), (justselectedy - 1)) != null)) { //theres a piece on lowerright
						// 	x = x + 2;
						// 	y = y - 2;
						// 	return true;
						// }
						// if (justselected.isKing()) {
						// 	if ((justselectedx - 1) == x && (justselectedy + 1) == y) {
						// 		return true; //if upperleft square is clicked
						// 	} 
						// 	else if ((justselectedx + 1) == x && (justselectedy + 1) == y) {
						// 		return true; //if upperright square is clicked
						// 	}
						// 	if ((pieceAt((justselectedx - 1), (justselectedy + 1)) != null)) { //theres a piece on upperleft
						// 		x = x - 2;
						// 		y = y + 2 ;
						// 		return true;
						// 	}
						// 	else if ((pieceAt((justselectedx + 1), (justselectedy + 1)) != null)) { //theres a piece on upperright
						// 		x = x + 2;
						// 		y = y + 2;
						// 		return true;
						// 	}
						// }
						// return false;

				}
			
			return false;
		}

	}

	public void select(int x, int y) {
		System.out.println(x);
		System.out.println(y);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	    if (pieceAt(x, y) == null) {
	    	//this.remove(justselectedx, justselectedy).move(x,y);
	    	justselected.move(x, y);
	    	// if (justselected.isBomb() && justselected.hasCaptured()) {
	    	// 	this.remove(x, y);
	    	// }
	    	//Checks to see if I ate any pieces 
	    	//If I did eat pieces, removes them.

	    	System.out.println("omg im movin");
	    	haveimovedyet = true;
	    	
	    	if (justselected.hasCaptured()) {
	    		eat = true;
	    		justselected = pieceAt(x, y);
	    		justselectedx = x;
	    		justselectedy = y;
	    	} 
	    }

	    else {
	    	amiholdingapiece = true;
	    	justselectedx = x;
	    	//System.out.println("upperright"+justselectedx);
            justselectedy = y;
            //System.out.println("jkjl;"+justselectedy);
            justselected = pieceAt(x, y);
	    }

	    // if (amiholdingapiece == false) {
	    // 	amiholdingapiece = true;
	    // 	justselectedx = x;
	    // 	//System.out.println("upperright"+justselectedx);
     //        justselectedy = y;
     //        //System.out.println("jkjl;"+justselectedy);
     //        justselected = pieceAt(x, y);
     //    } else {
     //    	//System.out.println("tgtgtgg");
	    // 	// amiholdingapiece = false;
	    // 	//System.out.println(justselectedx + ",badminton" + justselectedy);
	    // 	this.remove(justselectedx, justselectedy).move(x,y);
	    // 	if (justselected.isBomb() && justselected.hasCaptured()) {
	    // 		this.remove(x, y);
	    // 	}
	    // 	//Checks to see if I ate any pieces 
	    // 	//If I did eat pieces, removes them.

	    // 	System.out.println("omg im movin");
	    // 	haveimovedyet = true;
	    	
	    // 	if (justselected.hasCaptured()) {
	    // 		eat = true;
	    // 		justselected = pieceAt(x, y);
	    // 		justselectedx = x;
	    // 		justselectedy = y;
	    // 	} 
	    // 	// if (fireturn == true) {
     //  //       	fireturn = false;
     //  //       } else {
     //  //       	fireturn = true;
     //        // }
	    	
	    // }

        System.out.println("wowie " + x + " , " + y);	
	}
	public void place(Piece p, int x, int y) {
		if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8)) {
			pieces[x][y] = p;
			System.out.println(p);
			System.out.println("place is being called");
		}
		
	}
	public Piece remove(int x, int y) {
		if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8)){
			Piece removed = pieceAt(x,y);
			if (removed == null) {
				System.out.println("Sorry, there is nothing to remove.");
			}
			pieces[x][y] = null;
			// System.out.println(removed);
			return removed;
		}
		else {
			System.out.println("Sorry, it is out of bounds.");
			return null;
		}
		
	}
	public boolean canEndTurn() {
		//a piece must have been moved or performed a capture
		// if ((justselected != null) && (justselected.hasCaptured() == true)) {
		// 	return true;
		// }
		return haveimovedyet;
	}
	public void endTurn() {
		//check if there's a winner
		// fireturn = false;
		if (fireturn == true) {
            	fireturn = false;
            } else {
            	fireturn = true;
            }
        if (justselected != null) {
        	justselected.doneCapturing();
        }    
     
        justselected = null;
        haveimovedyet = false;
        amiholdingapiece = false;
        stop = false;

	}
	public String winner() {
		//check if there's a winner
		int N = 8;
		int numfirepieces = 0;
		int numwaterpieces = 0;
		for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if (pieceAt(i,j) != null){
	            		if (pieceAt(i,j).isFire()) {
							numfirepieces = numfirepieces + 1;
						}
						if (!pieceAt(i,j).isFire()) {
							numwaterpieces = numwaterpieces + 1;
						}
	            	}
	            }
	    }
	    if ((numwaterpieces == 0) && (numwaterpieces < numfirepieces)) {
	    	return ("Fire");
	    } 
	    if ((numfirepieces == 0) && (numwaterpieces > numfirepieces)) {
	    	return ("Water");
	    }
	    if (numwaterpieces == 0 && numfirepieces == 0) {
	    	return ("No one");
	    } else {
	    	return null;
	    }			
	}
}