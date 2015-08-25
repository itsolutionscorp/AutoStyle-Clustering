

public class Block {
	private int topLeftX; 
	private int topLeftY; 
	private int botRightX; 
	private int botRightY; 	
	private int height;
	private int width; 
	
	public Block(int tlx, int tly, int brx, int bry) {
		topLeftX = tlx;
		topLeftY = tly; 
		botRightX = brx;
		botRightY = bry;
		height = brx - tlx; 
		width = bry - tly; 
	}
	
	public Block(Block b) {
		this(b.topLeftX, b.topLeftY, b.botRightX, b.botRightY);
	}
	
	@Override
	public boolean equals(Object o) {
		boolean eq = false;
		if (o instanceof Block) {
			Block otherBlock = (Block) o; 
			eq = (otherBlock.topLeftX == topLeftX) && (otherBlock.topLeftY == topLeftY) && (otherBlock.botRightX == botRightX) && (otherBlock.botRightY == botRightY); 
		}
		return eq; 
	}
	
	@Override
	public int hashCode() {
		return (int) Math.pow((double) topLeftX*topLeftX + topLeftY*topLeftY + botRightX*botRightX+ botRightY*botRightY, 2);
	}
	
	@Override
	public String toString() {
		return topLeftX + " " + topLeftY + " " + botRightX + " " + botRightY;
	}
	
	public void setTopLeft(int tlx, int tly) {
		topLeftX = tlx;
		topLeftY = tly; 
	}
	
	public void setBotRight(int brx, int bry) {
		botRightX = brx;
		botRightY = bry; 
	}
	
	public void move(int tlx, int tly, int brx, int bry) {
		setTopLeft(tlx, tly);
		setBotRight(brx, bry);
	}

	public int getTopLeftX() {
		return topLeftX;
	}

	public void setTopLeftX(int topLeftX) {
		this.topLeftX = topLeftX;
	}

	public int getTopLeftY() {
		return topLeftY;
	}

	public void setTopLeftY(int topLeftY) {
		this.topLeftY = topLeftY;
	}

	public int getBotRightX() {
		return botRightX;
	}

	public void setBotRightX(int botRightX) {
		this.botRightX = botRightX;
	}

	public int getBotRightY() {
		return botRightY;
	}

	public void setBotRightY(int botRightY) {
		this.botRightY = botRightY;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
}
