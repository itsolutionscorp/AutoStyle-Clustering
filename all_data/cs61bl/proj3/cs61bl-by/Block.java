
import java.util.ArrayList;

public class Block {
	private int tlX;
	private int tlY;
	private int brX;
	private int brY;
	private int height;
	private int width;
	private int area;
	private Tray myTray;

	public Block(int tly, int tlx, int bry, int brx, Tray t) {
		tlX = tlx;
		tlY = tly;
		brX = brx;
		brY = bry;
		height = Math.abs(brY-tlY)+1;																	// WTF 
		width = Math.abs(brX - tlX)+1;
		area = height * width;
		myTray = t;
		if (myTray!=null) {
			myTray.setCoord(tlY, tlX, brY, brX, this);
			ArrayList<Integer> coords = new ArrayList<Integer>();
			coords.add(tly);																		// HEIGHT by WIDTH 
			coords.add(tlx);
			t.getBlocks().put(coords,this);	
		}
	}

	public int getTopLeftX() {
		return tlX;
	}
	
	public int getTopLeftY() {
		return tlY;
	}
	
	public int getBottRightX() {
		return brX;
	}
	
	public int getBottRightY() {
		return brY;
	}
	
	public int getWidth(){
	    return this.width;
	}
	
	public int getHeight(){
	    return this.height;
	}
	
	public int getArea(){
	    return this.area;
	}

	public void move(int tlyCurr, int tlxCurr, int tlyTo, int tlxTo) {
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(tlyCurr);
		coords.add(tlxCurr);
		myTray.getBlocks().remove(coords);
		myTray.setNull(tlY, tlX, brY, brX);													// HEIGHT by WIDTH
		int diffX = tlxTo - tlxCurr;													
		int diffY = tlyTo - tlyCurr;
		tlX = tlxTo;
		tlY = tlyTo;
		brX = brX + diffX;																	// WTF 
		brY = brY + diffY;						
		ArrayList<Integer> coords2= new ArrayList<Integer>();
		coords2.add(tlY);																	// HEIGHT by WIDTH		
		coords2.add(tlX);
		myTray.getBlocks().put(coords2, this);
		myTray.setCoord(tlY, tlX, brY, brX, this);											// HEIGHT by WIDTH		
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;	
		}
		Block compare = (Block) o;
		return (tlX == compare.tlX) && (tlY == compare.tlY) && (brX == compare.brX) && (brY == compare.brY);
	}

	@Override 
	public int hashCode() {
		int code = 0; 
		code += (tlX * 3) + (myTray.getTrayHeight() * 4);
		code += (tlY * 7) + (myTray.getTrayWidth() * 7);
		code += (brX * 17) + (myTray.getTrayHeight() * 3);
		code += (brY * 11) + (myTray.getTrayWidth() * 2);
		return code;
	}

	public String toString() {
		return tlY + " " + tlX + " " + brY + " " + brX;
	}
}