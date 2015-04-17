import java.util.Arrays;

public class Board {
	private Piece[] pieces = new Piece[32];
	private boolean playerFire = true;
	private boolean selected = false;
	private boolean moved = false;
	private int selx;
	private int sely;
	private int clickx;
	private int clicky;
	private Piece selpiece = null;
	private int fireNum;
	private int waterNum;
	public static void main(String args[]) {
		Board b = new Board(false);
		while (b.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
				b.clickx = (int) StdDrawPlus.mouseX();
				b.clicky = (int) StdDrawPlus.mouseY();
				if (b.canSelect(b.clickx,b.clicky)) {
					b.select(b.clickx,b.clicky);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
		System.out.println(b.winner());
	}

	public Board(boolean shouldBeEmpty) {
		StdDrawPlus.setScale(0,8);
		StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(4,4,4);
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		int i = 0;
		while (i < 32) {
			double x = (i%4)*2 + 0.5;
			double y = (i/4)*2 + 0.5;
			if (i >= 16) {
				x = x + 1;
				y = y - 7;
			}
			StdDrawPlus.filledSquare(x,y,0.5);
			i = i + 1;
		}
		if (shouldBeEmpty == false) {
			Piece fpa = new Piece(true,this,0,0,"pawn");
			Piece fpb = new Piece(true,this,2,0,"pawn");
			Piece fpc = new Piece(true,this,4,0,"pawn");
			Piece fpd = new Piece(true,this,6,0,"pawn");
			Piece fsa = new Piece(true,this,1,1,"shield");
			Piece fsb = new Piece(true,this,3,1,"shield");
			Piece fsc = new Piece(true,this,5,1,"shield");
			Piece fsd = new Piece(true,this,7,1,"shield");
			Piece fba = new Piece(true,this,0,2,"bomb");
			Piece fbb = new Piece(true,this,2,2,"bomb");
			Piece fbc = new Piece(true,this,4,2,"bomb");
			Piece fbd = new Piece(true,this,6,2,"bomb");
			Piece wpa = new Piece(false,this,1,7,"pawn");
			Piece wpb = new Piece(false,this,3,7,"pawn");
			Piece wpc = new Piece(false,this,5,7,"pawn");
			Piece wpd = new Piece(false,this,7,7,"pawn");
			Piece wsa = new Piece(false,this,0,6,"shield");
			Piece wsb = new Piece(false,this,2,6,"shield");
			Piece wsc = new Piece(false,this,4,6,"shield");
			Piece wsd = new Piece(false,this,6,6,"shield");
			Piece wba = new Piece(false,this,1,5,"bomb");
			Piece wbb = new Piece(false,this,3,5,"bomb");
			Piece wbc = new Piece(false,this,5,5,"bomb");
			Piece wbd = new Piece(false,this,7,5,"bomb");
//			pieces = {fpa,fpb,fpc,fpd,fsa,fsb,fsc,fsd,fba,fbb,fbc,fbd,wpa,wpb,wpc,wpd,wsa,wsb,wsc,wsd,wba,wbb,wbc,wbd};
			pieces[0] = fpa;
			pieces[1] = fpb;
			pieces[2] = fpc;
			pieces[3] = fpd;
			pieces[4] = fsa;
			pieces[5] = fsb;
			pieces[6] = fsc;
			pieces[7] = fsd;
			pieces[8] = fba;
			pieces[9] = fbb;
			pieces[10] = fbc;
			pieces[11] = fbd;
			pieces[31] = wpa;
			pieces[30] = wpb;
			pieces[29] = wpc;
			pieces[28] = wpd;
			pieces[27] = wsa;
			pieces[26] = wsb;
			pieces[25] = wsc;
			pieces[24] = wsd;
			pieces[23] = wba;
			pieces[22] = wbb;
			pieces[21] = wbc;
			pieces[20] = wbd;
			for (Piece pie : pieces) {
				if (pie != null) {
					drawPiece(pie);
				}
			}
		}
	}

//	private void addPiece(Piece[] pieces1, Piece pie) {
//		Piece[] pieces2 = Arrays.copyOf(pieces1, pieces1.length+1);
//		pieces2[pieces1.length] = pie;
//		pieces = pieces2;
//	}

	private int indexOf(Piece needle, Piece[] haystack) {
    	for (int i=0; i<haystack.length; i++) {
        	if (haystack[i] != null && haystack[i] == needle) return i;
    	}
    	return -1;
	}

	private void drawPiece(Piece p) {
		int pi = indexOf(p,pieces);
		int px = 2*(pi%4) + (pi/4)%2;
		int py = pi/4;
		if (p.isKing() == false) {
			if (p.isFire() == true) {
				if (p.isBomb()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/bomb-fire.png",1,1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/shield-fire.png",1,1);
				}
				else {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/pawn-fire.png",1,1);
				}
			}
			else if (p.isFire() == false) {
				if (p.isBomb()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/bomb-water.png",1,1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/shield-water.png",1,1);
				}
				else {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/pawn-water.png",1,1);
				}
			}
		}
		else {
			if (p.isFire() == true) {
				if (p.isBomb()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/bomb-fire-crowned.png",1,1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/shield-fire-crowned.png",1,1);
				}
				else {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/pawn-fire-crowned.png",1,1);
				}
			}
			else if (p.isFire() == false) {
				if (p.isBomb()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/bomb-water-crowned.png",1,1);
				}
				else if (p.isShield()) {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/shield-water-crowned.png",1,1);
				}
				else {
					StdDrawPlus.picture(px+0.5,py+0.5,"img/pawn-water-crowned.png",1,1);
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		for (Piece pie : pieces) {
			int pi = indexOf(pie,pieces);
			int px = 2*(pi%4) + (pi/4)%2;
			int py = pi/4;
			if (pie != null) {
				if (px == x && py == y) {
					return pie;
				}
			}
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		Piece pie1 = pieceAt(x,y);
		if (pie1 != null) {
			if ((playerFire && pie1.isFire()) || (!playerFire && !pie1.isFire())) {
				if (!selected || !moved) {
					return true;
				}
			}
		}
		else if (pie1 == null) {
			if (selected && !moved) {
				return (canMove(x,y,selpiece) || canJump(x,y,selpiece));
			}
			else if (selected && selpiece.hasCaptured()) {
				return canJump(x,y,selpiece);
			}
		}
		return false;
	}

	private boolean canMove(int x, int y, Piece p) {
		if (pieceAt(x,y) == null && x >= 0 && x <= 7 && y >=0 && y <=7) {
			int pi = indexOf(p,pieces);
			int px = 2*(pi%4) + (pi/4)%2;
			int py = pi/4;
			if (p.isKing()) {
				if (Math.abs(px-x)==1 && Math.abs(py-y)==1) {
					return true;
				}
			}
			else if (p.isFire()) {
				if (Math.abs(px-x)==1 && y-py==1) {
					return true;
				}
			}
			else {
				if (Math.abs(px-x)==1 && py-y==1) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canJump(int x, int y, Piece p) {
		if (pieceAt(x,y) == null && x >= 0 && x <= 7 && y >=0 && y <=7) {
			int pi = indexOf(p,pieces);
			int px = 2*(pi%4) + (pi/4)%2;
			int py = pi/4;
			if (p.isKing()) {
				if (Math.abs(px-x)==2 && Math.abs(py-y)==2) {
					Piece pie1 = pieceAt((px+x)/2,(py+y)/2);
					if (pie1 != null && p.isFire() != pie1.isFire()) {
						return true;
					}
				}
			}
			else if (p.isFire()) {
				if (Math.abs(px-x)==2 && y-py==2) {
					Piece pie1 = pieceAt((px+x)/2,(py+y)/2);
					if (pie1 != null && p.isFire() != pie1.isFire()) {
						return true;
					}
				}
			}
			else {
				if (Math.abs(px-x)==2 && py-y==2) {
					Piece pie1 = pieceAt((px+x)/2,(py+y)/2);
					if (pie1 != null && p.isFire() != pie1.isFire()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (selpiece != null && pieceAt(x,y) == null) {
//			if (canJump(x,y,selpiece)) {
//				remove((x+selx)/2,(y+sely)/2);
//				selpiece.move(x,y);
//				selpiece.hasCaptured = true;
//				moved = true;
//				jumped = true;
//				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
//				StdDrawPlus.filledSquare(selx+0.5,sely+0.5,0.5);
//				drawPiece(selpiece);
//				selx = x;
//				sely = y;
//			}
//			else {
			int pi2 = 4*y+(x/2);
			int pi = indexOf(selpiece,pieces);
			pieces[pi2] = selpiece;
			pieces[pi] = null;
			selpiece.move(x,y);
			moved = true;
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(selx+0.5,sely+0.5,0.5);
			drawPiece(selpiece);
			selx = x;
			sely = y;
//			}
		}
		else if (selpiece == null) {
			selpiece = pieceAt(x,y);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x+0.5,y+0.5,0.5);
			drawPiece(selpiece);
			selected = true;
			selx = x;
			sely = y;
		}
		else {
			int pi = indexOf(selpiece,pieces);
			int px = 2*(pi%4) + (pi/4)%2;
			int py = pi/4;
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(px+0.5,py+0.5,0.5);
			drawPiece(selpiece);
			selpiece = pieceAt(x,y);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x+0.5,y+0.5,0.5);
			drawPiece(selpiece);
			selected = true;
			selx = x;
			sely = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (p != null && x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			p.move(x,y);
			int pi2 = 4*y+(x/2);
			pieces[pi2] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (pieceAt(x,y) != null) {
				Piece a = pieceAt(x,y);
				int pi2 = 4*y+(x/2);
				pieces[pi2] = null;
//				if (a.isFire()) {
//					fireNum = fireNum - 1;
//				}
//				else {
//					waterNum = waterNum - 1;
//				}
//				a.move(-3,0);
//				java.util.List<Piece> pieces2 = new java.util.ArrayList<Piece>(Arrays.asList(pieces));
//				pieces2.removeAll(Arrays.asList(a));
//				pieces = pieces2.toArray(pieces);
//				a = null;
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				StdDrawPlus.filledSquare(x+0.5,y+0.5,0.5);
				return a;
			}
			else {
				System.out.println("There is no piece there.");
				return null;
			}
		}
		else {
			System.out.println("That is out of bounds.");
			return null;
		}
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		playerFire = !playerFire;
		selected = false;
		moved = false;
		if (selpiece != null) {
			selpiece.doneCapturing();
		}
		selpiece = null;
	}

	public String winner() {
		fireNum = 0;
		waterNum = 0;
		for (Piece pie : pieces) {
			if (pie != null) {
				if (pie.isFire()) {
					fireNum = fireNum + 1;
				}
				else {
					waterNum = waterNum + 1;
				}
			}
		}
		if (fireNum == 0 && waterNum == 0) {
			return "No one";
		} 
		else if (fireNum == 0) {
			return "Water";
		}
		else if (waterNum == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}
}