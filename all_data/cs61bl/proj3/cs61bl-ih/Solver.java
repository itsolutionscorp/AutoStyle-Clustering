import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Solver implements Iterable<Solver.Tray> {

	private ArrayList<Tray> trayList = new ArrayList<Tray>();
	public ArrayList<int[]> goals = new ArrayList<int[]>();
	public int height;
	public int width;

	@Override
	public Iterator<Tray> iterator() {
		return new FindTrayIterator();
	}

	private class FindTrayIterator implements Iterator<Tray> {

		private LinkedList<Tray> fringe;
		private HashSet<Double> visited;
		private boolean meetGoals = false;

		public FindTrayIterator() {
			fringe = new LinkedList<Tray>();
			visited = new HashSet<Double>();
			fringe.add(trayList.get(0));
		}

		@Override
		public boolean hasNext() {
			return !fringe.isEmpty() && !meetGoals;
		}

		@Override
		public Tray next() {
			Tray getFirst = fringe.poll();
			visited.add(getFirst.code);
			if (getFirst.meetGoals()) {
				meetGoals = true;
				return getFirst;
			}
			for (Tray.Block b : getFirst.blocks) {
				if (b.y1 - 1 >= 0) {
					if (getFirst.plate[b.y1 - 1][b.x1] == 0) {
						boolean chk = true;
						for (int j = b.x1 + 1; j <= b.x2; j++) {
							if (getFirst.plate[b.y1 - 1][j] != 0) {
								chk = false;
							}
						}
						if (chk) {
							Tray newTray = new Tray();
							for (Tray.Block bb : getFirst.blocks) {
								if (!bb.equals(b)) {
									newTray.blocks.add(newTray.new Block(bb.y1, bb.x1, bb.y2, bb.x2));
								} else {
									newTray.blocks.add(newTray.new Block(bb.y1 - 1, bb.x1, bb.y2 - 1, bb.x2));
									newTray.message = bb.y1 + " " + bb.x1 + " " + (bb.y1 - 1) + " " + bb.x1;
								}
							}
							newTray.finishTray();
							if (!visited.contains(newTray.code)) {
								newTray.from = getFirst;
								newTray.finishAdd();
								fringe.add(newTray);
							} else {
								newTray = null;
							}
						}
					}
				}
				if (b.y2 + 1 < height) {
					if (getFirst.plate[b.y2 + 1][b.x1] == 0) {
						boolean chk = true;
						for (int j = b.x1 + 1; j <= b.x2; j++) {
							if (getFirst.plate[b.y2 + 1][j] != 0) {
								chk = false;
							}
						}
						if (chk) {
							Tray newTray = new Tray();
							for (Tray.Block bb : getFirst.blocks) {
								if (!bb.equals(b)) {
									newTray.blocks.add(newTray.new Block(bb.y1, bb.x1, bb.y2, bb.x2));
								} else {
									newTray.blocks.add(newTray.new Block(bb.y1 + 1, bb.x1, bb.y2 + 1, bb.x2));
									newTray.message = bb.y1 + " " + bb.x1 + " " + (bb.y1 + 1) + " " + bb.x1;
								}
							}
							newTray.finishTray();
							if (!visited.contains(newTray.code)) {
								newTray.from = getFirst;
								newTray.finishAdd();
								fringe.add(newTray);
							} else {
								newTray = null;
							}
						}
					}
				}
				if (b.x1 - 1 >= 0) {
					if (getFirst.plate[b.y1][b.x1 - 1] == 0) {
						boolean chk = true;
						for (int j = b.y1 + 1; j <= b.y2; j++) {
							if (getFirst.plate[j][b.x1 - 1] != 0) {
								chk = false;
							}
						}
						if (chk) {
							Tray newTray = new Tray();
							for (Tray.Block bb : getFirst.blocks) {
								if (!bb.equals(b)) {
									newTray.blocks.add(newTray.new Block(bb.y1, bb.x1, bb.y2, bb.x2));
								} else {
									newTray.blocks.add(newTray.new Block(bb.y1, bb.x1 - 1, bb.y2, bb.x2 - 1));
									newTray.message = bb.y1 + " " + bb.x1 + " " + bb.y1 + " " + (bb.x1 - 1);
								}
							}
							newTray.finishTray();
							if (!visited.contains(newTray.code)) {
								newTray.from = getFirst;
								newTray.finishAdd();
								fringe.add(newTray);
							} else {
								newTray = null;
							}
						}
					}
				}
				if (b.x2 + 1 < width) {
					if (getFirst.plate[b.y1][b.x2 + 1] == 0) {
						boolean chk = true;
						for (int j = b.y1 + 1; j <= b.y2; j++) {
							if (getFirst.plate[j][b.x2 + 1] != 0) {
								chk = false;
							}
						}
						if (chk) {
							Tray newTray = new Tray();
							for (Tray.Block bb : getFirst.blocks) {
								if (!bb.equals(b)) {
									newTray.blocks.add(newTray.new Block(bb.y1, bb.x1, bb.y2, bb.x2));
								} else {
									newTray.blocks.add(newTray.new Block(bb.y1, bb.x1 + 1, bb.y2, bb.x2 + 1));
									newTray.message = bb.y1 + " " + bb.x1 + " " + bb.y1 + " " + (bb.x1 + 1);
								}
							}
							newTray.finishTray();
							if (!visited.contains(newTray.code)) {
								newTray.from = getFirst;
								newTray.finishAdd();
								fringe.add(newTray);
							} else {
								newTray = null;
							}
						}
					}
				}
			}
			return getFirst;
		}

	}

	public Solver(String s) {
		height = Integer.parseInt(s.substring(0, s.indexOf(" ")));
		width = Integer.parseInt(s.substring(s.indexOf(" ") + 1));
	}

	public class Tray {
		private ArrayList<Block> blocks = new ArrayList<Block>();
		private int[][] plate = new int[height][width];
		private double code = 0;
		private String message;
		private Tray from = null;

		public Tray() {
		}

		public boolean meetGoals() {
			for (int[] i : goals) {
				if (i[0] >= 0 && i[1] >= 0 && i[0] < height && i[1] < width) {
					int tempInt = plate[i[0]][i[1]];
					if (tempInt == 0) {
						return false;
					}
					for (int j = i[0] - 1; j <= i[2] + 1; j++) {
						for (int k = i[1] - 1; k <= i[3] + 1; k++) {
							if (j >= 0 && k >= 0 && j < height && k < width) {
								if (plate[j][k] != tempInt && j >= i[0] && j <= i[2] && k >= i[1] && k <= i[3]) {
									return false;
								} else if (plate[j][k] == tempInt
										&& (j == i[0] - 1 || j == i[2] + 1 || k == i[1] - 1 || k == i[3] + 1)) {
									return false;
								}
							}
						}
					}
				}
			}
			return true;
		}

		public void finishTray() {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					plate[i][j] = 0;
				}
			}
			Iterator<Block> iter = blocks.iterator();
			int n = 0;
			while (iter.hasNext()) {
				n++;
				Block b = iter.next();
				for (int i = b.y1; i <= b.y2; i++) {
					for (int j = b.x1; j <= b.x2; j++) {
						plate[i][j] = n;
					}
				}
				code += n * (Math.pow(1.1, b.y1 / 100.00 + 1) + b.x1 + 1);
			}
		}

		public void finishAdd() {
			trayList.add(this);
		}

		public void addBlock(String s) {
			blocks.add(
					new Block(
							Integer.parseInt(
									s.substring(0, s.indexOf(" "))),
							Integer.parseInt(
									s.substring(
											s.indexOf(" ")
													+ 1,
											s.indexOf(" ", s.indexOf(" ") + 1))),
					Integer.parseInt(s.substring(s.indexOf(" ", s.indexOf(" ") + 1) + 1, s.lastIndexOf(" "))),
					Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1))));
		}

		public class Block {
			public int y1;
			public int x1;
			public int y2;
			public int x2;

			private Block(int a, int b, int c, int d) {
				y1 = a;
				x1 = b;
				y2 = c;
				x2 = d;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		try{
			String file_name1 = System.getProperty("user.dir") + "/" + args[0];
			String file_name2 = System.getProperty("user.dir") + "/" + args[1];
			ReadFile file1 = new ReadFile(file_name1);
			ReadFile file2 = new ReadFile(file_name2);
			String[] aryLines1 = file1.OpenFile();
			String[] aryLines2 = file2.OpenFile();
			
			Solver solver = new Solver(aryLines1[0]);
			Solver.Tray firstTray = solver.new Tray();
			for (int t = 1; t < aryLines1.length; t++) {
				if (aryLines1[t] != null) {
					firstTray.addBlock(aryLines1[t]);
				}
			}
			firstTray.finishTray();
			firstTray.finishAdd();

			if (file2 != null) {
				for (int t = 0; t < aryLines2.length; t++) {
					solver.goals
							.add(new int[] { Integer.parseInt(aryLines2[t].substring(0, aryLines2[t].indexOf(" "))),
									Integer.parseInt(aryLines2[t].substring(aryLines2[t].indexOf(" ") + 1,
											aryLines2[t].indexOf(" ",
													aryLines2[t].indexOf(" ")
															+ 1))),
									Integer.parseInt(aryLines2[t].substring(
											aryLines2[t].indexOf(" ", aryLines2[t].indexOf(" ") + 1) + 1,
											aryLines2[t].lastIndexOf(" "))),
							Integer.parseInt(aryLines2[t].substring(aryLines2[t].lastIndexOf(" ") + 1)) });
				}
			}

			FindTrayIterator findIter = solver.new FindTrayIterator();
			while (findIter.hasNext()) {
				Tray temp = findIter.next();
				if (temp.meetGoals()) {
					ArrayList<String> out = new ArrayList<String>();
					if (temp.from != null) {
						while (temp.from != firstTray) {
							out.add(0, temp.message);
							temp = temp.from;
						}
						out.add(0, temp.message);
						for (String t : out) {
							System.out.println(t);
						}
						break;
					}

				}
			}
		} catch (Exception e){
			System.out.println("Invalid init and/or goal file.");
		}
		
	}
}
