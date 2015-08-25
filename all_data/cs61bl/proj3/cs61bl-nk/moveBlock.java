public class moveBlock {

	public Block b;
	private String repr;
	short newTopLeftJ;
	short newTopLeftI;
	short newBotRightJ;
	short newBotRightI;

	public moveBlock(Block block, String dir, short spaces) {
		b = block;
		switch (dir) {
		case "up":
			newTopLeftJ = (short) (b.getTopLeft().x - spaces);
			newTopLeftI = (short) (b.getTopLeft().y);
			newBotRightJ = (short) (b.getBottomRight().x - spaces);
			newBotRightI = (short) (b.getBottomRight().y);
			break;
		case "down":
			newTopLeftJ = (short) (b.getTopLeft().x + spaces);
			newTopLeftI = (short) (b.getTopLeft().y);
			newBotRightJ = (short) (b.getBottomRight().x + spaces);
			newBotRightI = (short) (b.getBottomRight().y);
			break;
		case "left":
			newTopLeftJ = (short) (b.getTopLeft().x);
			newTopLeftI = (short) (b.getTopLeft().y - spaces);
			newBotRightJ = (short) (b.getBottomRight().x);
			newBotRightI = (short) (b.getBottomRight().y - spaces);
			break;
		case "right":
			newTopLeftJ = (short) (b.getTopLeft().x);
			newTopLeftI = (short) (b.getTopLeft().y + spaces);
			newBotRightJ = (short) (b.getBottomRight().x);
			newBotRightI = (short) (b.getBottomRight().y + spaces);
			break;
		}
		this.repr = b.getTopLeft().x + " " + b.getTopLeft().y + " " + newTopLeftJ + " " + newTopLeftI;
	}

	public String toString() {
		return this.repr;
	}
}
