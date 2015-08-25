import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

public class Tray {

	private short[][] _config;
	private int _height;
	private int _width;
	private HashSet<int[]> _blocks;
	private HashMap<Tray, String> _children = null;
	private int _hashCode = 0;
	public int _heur = 0;
	private HashSet<int[]> _goal;


	public Tray(int height, int width, HashSet<int[]> goal) {
		_height = height;
		_width = width;
		_config = new short[height][width];
		_blocks = new HashSet<int[]>();
		_goal = goal;
		_heur = 0;
	}

	public int heur(int y1, int x1, int y2, int x2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	public HashSet<int[]> blocks() {
		return _blocks;
	}

	public short[][] config() {
		return _config;
	}

	public HashMap<Tray, String> children() {
		if (_children == null) {
			_children = this.generateChildren();
		}
		return _children;
	}

	public void addBlock(int[] coords) {
		_blocks.add(coords);
		for (int i= coords[0]; i <= coords[2]; i ++) {
			for (int j = coords[1]; j <= coords[3]; j ++) {
				_config[i][j] = blockValue(coords);
			}
		}
	}

	public short blockValue(int[] coords) {
		return (short)((coords[2]-coords[0]) * 256 + (coords[3]-coords[1]) + 1);
	}


	public boolean canAddBlock(int[] coords) {
		if (coords[0] < 0 || coords[2] > _height - 1
			|| coords[1] < 0 || coords[3] > _width - 1) {
			return false;
		}
		for (int i = coords[0]; i <= coords[2]; i++) {
			for (int j = coords[1]; j <= coords[3]; j ++) {
				if (_config[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean solved(int[][] solution) {
		for (int[] block: solution) {
			if (!containsArray(blocks(), block)) {
				return false;
			}
		}
		return true;
	}

	public boolean containsArray(HashSet<int[]> set, int[] array) {
		for (int[] b: set) {
			if (Arrays.equals(b, array)) {
				return true;
			}
		}
		return false;
	}

	public HashMap<Tray, String> generateChildren() {
		HashMap<Tray, String> children = new HashMap<Tray, String>();
		for (int[] block: blocks()) {
			if (this.canMoveLeft(block)) {
				children.put(this.moveLeft(block), this.leftRepr(block));
			}
			if (this.canMoveRight(block)) {
				children.put(this.moveRight(block), this.rightRepr(block));
			}
			if (this.canMoveUp(block)) {
				children.put(this.moveUp(block), this.upRepr(block));
			}
			if (this.canMoveDown(block)) {
				children.put(this.moveDown(block), this.downRepr(block));
			}
		}
		return children;
	}

	public boolean canMoveLeft(int[] block) {
		if (block[1] < 1) {
			return false;
		}
		for (int i = block[0]; i <= block[2]; i++) {
			if (_config[i][block[1]-1] != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveRight(int[] block) {
		if (block[3] >= _width - 1) {
			return false;
		}
		for (int i = block[0]; i <= block[2]; i++) {
			if (_config[i][block[3]+1] != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveUp(int[] block) {
		if (block[0] < 1) {
			return false;
		}
		for (int j = block[1]; j <= block[3]; j++) {
			if (_config[block[0]-1][j] != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveDown(int[] block) {
		if (block[2] >= _height - 1) {
			return false;
		}
		for (int j = block[1]; j <= block[3]; j++) {
			if (_config[block[2]+1][j] != 0) {
				return false;
			}
		}
		return true;
	}

	public Tray moveLeft(int[] block) {
		Tray movedLeft = new Tray(_height, _width, _goal);
		HashSet<int[]> newBlocks = (HashSet<int[]>)_blocks.clone();
		newBlocks.remove(block);
		int[] rpl = new int[4];
		rpl[0] = block[0];
		rpl[1] = block[1] - 1;
		rpl[2] = block[2];
		rpl[3] = block[3] - 1;
		newBlocks.add(rpl);
		movedLeft._blocks = newBlocks;
		short[][] newConfig = copyConfig();
		for (int i = rpl[0]; i <= rpl[2]; i ++) {
			newConfig[i][rpl[1]] = _config[block[0]][block[1]];
			newConfig[i][block[3]] = 0;
		}
		int heurChange = 0;
		for (int[] goal: _goal) {
			if ((rpl[0]-rpl[2]) == (goal[0] - goal[2]) && (rpl[1] - rpl[3]) == (goal[1] - goal[3])) {
				heurChange += heur(rpl[0], rpl[1], goal[0], goal[1]) - heur(block[0], block[1], goal[0], goal[1]);
				break;
			}
		}
		movedLeft._heur = _heur + heurChange;
		movedLeft._config = newConfig;
		return movedLeft;
	}

	public Tray moveRight(int[] block) {
		Tray movedRight = new Tray(_height, _width, _goal);
		HashSet<int[]> newBlocks = (HashSet<int[]>)_blocks.clone();
		newBlocks.remove(block);
		int[] rpl = new int[4];
		rpl[0] = block[0];
		rpl[1] = block[1] + 1;
		rpl[2] = block[2];
		rpl[3] = block[3] + 1;
		newBlocks.add(rpl);
		movedRight._blocks = newBlocks;
		short[][] newConfig = copyConfig();
		for (int i = rpl[0]; i <= rpl[2]; i ++) {
			newConfig[i][block[1]] = 0;
			newConfig[i][rpl[3]] = _config[block[0]][block[1]];
		}
		int heurChange = 0;
		for (int[] goal: _goal) {
			if ((rpl[0]-rpl[2]) == (goal[0] - goal[2]) && (rpl[1] - rpl[3]) == (goal[1] - goal[3])) {
				heurChange += heur(rpl[0], rpl[1], goal[0], goal[1]) - heur(block[0], block[1], goal[0], goal[1]);
				break;
			}
		}
		movedRight._heur = _heur + heurChange;
		movedRight._config = newConfig;
		return movedRight;
	}

	public Tray moveUp(int[] block) {
		Tray movedUp = new Tray(_height, _width, _goal);
		HashSet<int[]> newBlocks = (HashSet<int[]>)_blocks.clone();
		newBlocks.remove(block);
		int[] rpl = new int[4];
		rpl[0] = block[0] - 1;
		rpl[1] = block[1];
		rpl[2] = block[2] - 1;
		rpl[3] = block[3];
		newBlocks.add(rpl);
		movedUp._blocks = newBlocks;
		short[][] newConfig = copyConfig();
		for (int j = rpl[1]; j <= rpl[3]; j ++) {
			newConfig[block[2]][j] = 0;
			newConfig[rpl[0]][j] = _config[block[0]][block[1]];
		}
		int heurChange = 0;
		for (int[] goal: _goal) {
			if ((rpl[0]-rpl[2]) == (goal[0] - goal[2]) && (rpl[1] - rpl[3]) == (goal[1] - goal[3])) {
				heurChange += heur(rpl[0], rpl[1], goal[0], goal[1]) - heur(block[0], block[1], goal[0], goal[1]);
				break;
			}
		}
		movedUp._heur = _heur + heurChange;
		movedUp._config = newConfig;
		return movedUp;
	}

	public Tray moveDown(int[] block) {
		Tray movedDown = new Tray(_height, _width, _goal);
		HashSet<int[]> newBlocks = (HashSet<int[]>)_blocks.clone();
		newBlocks.remove(block);
		int[] rpl = new int[4];
		rpl[0] = block[0] + 1;
		rpl[1] = block[1];
		rpl[2] = block[2] + 1;
		rpl[3] = block[3];
		newBlocks.add(rpl);
		movedDown._blocks = newBlocks;
		short[][] newConfig = copyConfig();
		for (int j = rpl[1]; j <= rpl[3]; j ++) {
			newConfig[rpl[2]][j] = _config[block[0]][block[1]];
			newConfig[block[0]][j] = 0;
		}
		int heurChange = 0;
		for (int[] goal: _goal) {
			if ((rpl[0]-rpl[2]) == (goal[0] - goal[2]) && (rpl[1] - rpl[3]) == (goal[1] - goal[3])) {
				heurChange += heur(rpl[0], rpl[1], goal[0], goal[1]) - heur(block[0], block[1], goal[0], goal[1]);
				break;
			}
		}
		movedDown._heur = _heur + heurChange;
		movedDown._config = newConfig;
		return movedDown;
	}

	public String leftRepr(int[] block) {
		return block[0]+" "+block[1]+" "+block[0]+" "+(block[1]-1);
	}

	public String rightRepr(int[] block) {
		return block[0]+" "+block[1]+" "+block[0]+" "+(block[1]+1);
	}

	public String upRepr(int[] block) {
		return block[0]+" "+block[1]+" "+(block[0]-1)+" "+block[1];
	}

	public String downRepr(int[] block) {
		return block[0]+" "+block[1]+" "+(block[0]+1)+" "+block[1];
	}

	public void print() {
		for (short i = 0; i < _height; i++) {
			printHelper(_config[i]);
		}
		System.out.println();
	}

	public void printHelper(short[] row) {
		for (short j = 0; j < _width - 1; j++) {
				System.out.print(row[j]);
			}
		System.out.println(row[_width-1]);
	}

	public short[][] copyConfig() {
		short[][] copyConfig = new short[_height][_width];
		for (short i = 0; i < _height; i++) {
			short[] thisline = new short[_width];
			for (short j = 0; j < _width; j++) {
				thisline[j] = _config[i][j];
			}
			copyConfig[i] = thisline;
		}
		return copyConfig;
	}

	@Override
	public int hashCode() {
		if (_hashCode == 0) {
			int code = 0;
			for (int i = 0; i < _height; i++) {
				for (int j = 0; j < _width; j++) {
					code += pair(_config[i][j], pair(i+1, j+1));
				}
			}
			_hashCode = code;
		}
		return _hashCode;
	}

	public int pair(int x, int y) {
		return ((x+y)*(x+y+1)+y)/2;
	}

	@Override
	public boolean equals(Object o) {
		return Arrays.deepEquals(_config, ((Tray)o)._config);
	}

}
