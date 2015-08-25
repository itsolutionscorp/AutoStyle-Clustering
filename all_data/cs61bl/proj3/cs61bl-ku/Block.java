public class Block {
	
	private ShortPoint botRight;
	private ShortPoint topLeft;
	private short height;
	private short width;
	
	public Block(ShortPoint tl, ShortPoint br) {
		this.botRight = br;
		this.topLeft = tl;
		height = (short) (br.getY() - tl.getY() + 1);
		width = (short) (br.getX() - tl.getX() + 1);
	}
	
	public ShortPoint getTopLeft() {
		return this.topLeft;
	}
	
	public ShortPoint getBotRight() {
		return this.botRight;
	}
	
	public void move(String s) {
		int topLeftX = (int) topLeft.getX();
		int topLeftY = (int) topLeft.getY();
		int botRightX = (int) botRight.getX();
		int botRightY = (int) botRight.getY();
		if (s.equals("left")) {
			topLeft.setLocation(topLeftX - 1, topLeftY);
			botRight.setLocation(botRightX - 1, botRightY);
		} else if (s.equals("right")) {
			topLeft.setLocation(topLeftX + 1, topLeftY);
			botRight.setLocation(botRightX + 1, botRightY);
		} else if (s.equals("up")) {
			topLeft.setLocation(topLeftX, topLeftY - 1);
			botRight.setLocation(botRightX, botRightY - 1);
		} else if (s.equals("down")) {
			topLeft.setLocation(topLeftX, topLeftY + 1);
			botRight.setLocation(botRightX, botRightY + 1);
		}
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (! (obj instanceof Block)) {
			return false;
		} else {
			Block b = (Block) obj;
			return topLeft.equals(b.getTopLeft()) && botRight.equals(b.getBotRight());
		}
	}

	public short getWidth() {
		return width;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((botRight == null) ? 0 : botRight.hashCode());
		result = prime * result + ((topLeft == null) ? 0 : topLeft.hashCode());
		return result;
	}

	public short getHeight() {
		return height;
	}
	
	public String toString() {
		String all = "";
		all = "(" + (int) topLeft.getY() + ", " + (int) topLeft.getX() + " + "
		+ (int) botRight.getY() + ", " + (int) botRight.getX() + ")";
		return all;
	}
	
	public Block clone() {
		return new Block(new ShortPoint(topLeft.getX(), topLeft.getY()), new ShortPoint(botRight.getX(), botRight.getY()));
	}
	
	
	
	
	
}
