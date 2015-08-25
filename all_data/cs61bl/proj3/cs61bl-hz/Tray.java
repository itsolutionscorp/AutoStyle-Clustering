import java.awt.Rectangle;
import java.util.*;

public class Tray implements Comparable<Tray> {

	public static int length, height;
	public Rectangle[] blocks;
	public Tray parent;

	public static Tray goalConfig;
	public String move = "";
	public double priority;

	public Tray(Rectangle[] blocks) {
		this.blocks = blocks;
	}

	public Tray(ArrayList<Rectangle> blocks) {
		Rectangle[] r = new Rectangle[blocks.size()];
		this.blocks = blocks.toArray(r);
	}
	
	public void setPriority() {
		priority = calcPriority();
	}

	public void addNextMoves() {
		for (int i = 0; i < blocks.length; i++) {
			addMoveBlock(i);
		}
	}

	public Tray clone() {
		Rectangle[] copy = Arrays.copyOf(blocks, blocks.length);
		Tray clone = new Tray(copy);
		clone.parent = this;
		return clone;
	}
	
	public boolean solved() {
		return priority == 0;
	}

	public void addMoveBlock(int index) {
		Rectangle b = blocks[index];
		// moving left. -i
		Rectangle compare = new Rectangle(b.x, b.y, b.width, b.height);
		for (int i = 1; i <= b.x; i++) {
			compare.x -= 1;
			boolean success = true;

			for (Rectangle other : blocks) {
				if(other == blocks[index]) continue;
				if (other.intersects(compare)) {
					success = false;
					break;
				}
			}

			if (!success) {
				break;
			}

			Tray clone = clone();
			clone.blocks[index] = new Rectangle(compare.x, compare.y, compare.width, compare.height);
			clone.move = b.y + " " + b.x + " " + compare.y + " " + compare.x;
			clone.setPriority();
			Solver.addTray(clone);
		}

		// moving right. i
		compare = new Rectangle(b.x, b.y, b.width, b.height);
		for (int i = 1; i <= length - (b.x + b.width); i++) {
			compare.x += 1;
			boolean success = true;

			for (Rectangle other : blocks) {
				if(other == blocks[index]) continue;
				if (other.intersects(compare)) {
					success = false;
					break;
				}
			}

			if (!success) {
				break;
			}

			Tray clone = clone();
			clone.blocks[index] = new Rectangle(compare.x, compare.y, compare.width, compare.height);
			clone.move = b.y + " " + b.x + " " + compare.y + " " + compare.x;
			clone.setPriority();
			Solver.addTray(clone);
		}
		
		// moving up. i
		compare = new Rectangle(b.x, b.y, b.width, b.height);
		for (int i = 1; i <= b.y; i++) {
			compare.y -= 1;
			boolean success = true;

			for (Rectangle other : blocks) {
				if(other == blocks[index]) continue;
				if (other.intersects(compare)) {
					success = false;
					break;
				}
			}

			if (!success) {
				break;
			}

			Tray clone = clone();
			clone.blocks[index] = new Rectangle(compare.x, compare.y, compare.width, compare.height);
			clone.move = b.y + " " + b.x + " " + compare.y + " " + compare.x;
			clone.setPriority();
			Solver.addTray(clone);
		}
		
		// moving down. i
		compare = new Rectangle(b.x, b.y, b.width, b.height);
		for (int i = 1; i <= height - (b.y + b.height); i++) {
			compare.y += 1;
			boolean success = true;

			for (Rectangle other : blocks) {
				if(other == blocks[index]) continue;
				if (other.intersects(compare)) {
					success = false;
					break;
				}
			}

			if (!success) {
				break;
			}

			Tray clone = clone();
			clone.blocks[index] = new Rectangle(compare.x, compare.y, compare.width, compare.height);
			clone.move = b.y + " " + b.x + " " + compare.y + " " + compare.x;
			clone.setPriority();
			Solver.addTray(clone);
		}
	}
	
	public String toString() {
		String result= "";
		for(Rectangle block : blocks) {
			result += block.toString() + "\n"; 
		}
		return result;
	}
	
	public boolean equals(Object o2) {
		Tray other = (Tray) o2;
		if (other.priority != priority) return false;
		return Arrays.equals(other.blocks, blocks);
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		for(Rectangle rect : blocks) {
			result *= 7;
			result += rect.getX() + rect.getY();
		}
		return result;
	}


	public int calcPriority() {
		if(goalConfig == null) return 0; 
		int totalP = 0;
		Rectangle[] goalBlocks = goalConfig.blocks;
		for(Rectangle goalBlock : goalBlocks) {
			int p = 0;
			for(Rectangle block : blocks) {
				if(block.equals(goalBlock)) {
					p = 0;
					break;
				}
				if(block.width == goalBlock.width && block.height == goalBlock.height) {
						p += Math.abs(goalBlock.x - block.x) + Math.abs(goalBlock.y - block.y);
				} 
			}
			totalP += p;
		}
		return totalP;
	}

	@Override
	public int compareTo(Tray tray2) {
//		if(priority > tray2.priority){
//			return 1;
//		}
//		return -1;
		return 0;
	}

}
