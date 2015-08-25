public class Block {
	public int[] topLeft;
	public int[] bottomRight;
	public int width;
	public int height;

	public Block(int[] topLeft, int[] bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.width = bottomRight[1] - topLeft[1] + 1;
		this.height = bottomRight[0] - topLeft[0] + 1;
	}

	@Override
	public boolean equals(Object o) {
		Block otherBlock = (Block) o;
		if (topLeft[0] == otherBlock.topLeft[0]
				&& topLeft[1] == otherBlock.topLeft[1]
				&& bottomRight[0] == otherBlock.bottomRight[0]
				&& bottomRight[1] == otherBlock.bottomRight[1]) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return ((topLeft[0] + 1) * 37 ) + ((topLeft[1] + 1) * 67) + ((bottomRight[0] + 1) * 31) + ((bottomRight[1] + 1) * 23);
	}
	
	@Override
	public String toString() {
		return topLeft[0] + " " + topLeft[1] + " " + bottomRight[0] + " "
				+ bottomRight[1];
	}
}