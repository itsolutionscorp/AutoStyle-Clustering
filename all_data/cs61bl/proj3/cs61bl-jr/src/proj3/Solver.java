//package proj3;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Stack;

public class Solver {
	private int tray_length;
	private int tray_width;
	private Tray init_tray;
	private Tray last_tray;
	private ArrayList<Block> initBlocks;
	private ArrayList<Block> goalBlocks;
	private Stack<Tray> myTrayHistory;
	private Stack<Tray> fringe;

	public Solver() {
		last_tray = null;
		initBlocks = new ArrayList<Block>();
		goalBlocks = new ArrayList<Block>();
		myTrayHistory = new Stack<Tray>();
		fringe = new Stack<Tray>();
	}
	
	public void setTrayLength(int length) {
		tray_length = length;
	}
	
	public void setTrayWidth(int width) {
		tray_width = width;
	}

	public Stack<Tray> returnFringe() {
		return fringe;
	}
	
	public static void main(String[] args) throws IOException {
		Solver s = new Solver();
		try {
			BufferedReader initConfigFile = new BufferedReader(new FileReader(new File(args[0])));
			BufferedReader goalConfigFile = new BufferedReader(new FileReader(new File(args[1])));

			int init_line = 0;

			for (String initConfigLine = initConfigFile.readLine(); initConfigLine != null; initConfigLine = initConfigFile.readLine()) {
				if (init_line == 0) {
					String[] nums = initConfigLine.split(" ");
					s.setTrayLength(Integer.parseInt(nums[0]));
					s.setTrayWidth(Integer.parseInt(nums[1]));
					init_line++;
				} else {
					String[] nums = initConfigLine.split(" ");
					Block initBlock = new Block(Integer.parseInt(nums[0]),
							Integer.parseInt(nums[1]),
							Integer.parseInt(nums[2]),
							Integer.parseInt(nums[3]));
					s.initBlocks.add(initBlock);
					init_line++;
				}
			}

			s.init_tray = new Tray(s.tray_length, s.tray_width, null, null, s.initBlocks);
			s.init_tray.setCode();
			s.init_tray.addCodeToAlreadySeen();
			
			for (String goalConfigLine = goalConfigFile.readLine(); goalConfigLine != null; goalConfigLine = goalConfigFile.readLine()) {
				String[] nums = goalConfigLine.split(" ");
				Block goalBlock = new Block(Integer.parseInt(nums[0]),
						Integer.parseInt(nums[1]), 
						Integer.parseInt(nums[2]),
						Integer.parseInt(nums[3]));
				s.goalBlocks.add(goalBlock);
			}
		} catch (IOException e) { 
			System.out.println("File cannot be found.");
		} try {	
			s.returnFringe().push(s.init_tray);
			s.findLastTrays ();

			s.findHistory(s.last_tray);
			s.printPathToGoal(s.myTrayHistory);
		} catch (OutOfMemoryError e) {
			return;
		}
	}
	
	
	public void findLastTrays () {
		while (!fringe.empty()) {
			Tray t = fringe.pop();
			if (t == null) {
				return;
			}
			boolean goalBlockConfig = true;
			t.addCodeToAlreadySeen();
			t.setNextTrays();
			for (Block b : goalBlocks) {
				for (int i = 0; i < t.returnBlocks().size(); i++) {
					if (t.returnBlocks().get(i).returnCode().equals(b.returnCode())) {
						t.returnBlocks().remove(i);
						break;
					} else {
						if (i == t.returnBlocks().size() - 1) {
							goalBlockConfig = false;
							break;
						}
					}
				}
			}
			if (goalBlockConfig == true) {
				last_tray = t;
				break;
			} else {
				for (Tray a : t.returnNextTrays()) {
					fringe.push(a);
				}
			}
		}
	}
	
	public void findHistory (Tray t) {
		if (t == null) {
			return;
		}
		while (t.returnPrevTray() != null) {
			myTrayHistory.push(t);
			t = t.returnPrevTray();
		}
		return;
	}
	
	public void printPathToGoal (Stack<Tray> a) {
		while (!a.empty()) {
			Tray temp = a.pop();
			if (temp == null) continue;
			if (temp.returnPrevMove() != null) {
				System.out.println (temp.returnPrevMove());
			}
		}
		return;
	}
}
