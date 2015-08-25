import java.io.*;
import java.util.*;

public class Solver {
	private static Tray myCurr;
	private static Tray myGoal;
	private static int myHeight;
	private static int myWidth;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String init = args[0];
		String goal = args[1];
		
		initConstructor(init);
		goalConstructor(goal);
		
		solve(myCurr);
	}
	
	public static void initConstructor(String init) throws FileNotFoundException, IOException  {
		BufferedReader br = new BufferedReader(new FileReader(new File(init)));
		String line;
		if ((line = br.readLine()) != null) {
			String[] elements = line.split(" ");
			if (elements.length != 2) {
				System.out.println("Invalid init and/or goal file.");
			}
			Scanner scanner = new Scanner(line);
			myHeight = scanner.nextInt();
			myWidth = scanner.nextInt();
			myCurr = new Tray(myHeight, myWidth);
		}
		while ((line = br.readLine()) != null) {
			Scanner scanner = new Scanner(line);
			int tlY = scanner.nextInt();
			int tlX = scanner.nextInt();
			int brY = scanner.nextInt();
			int brX = scanner.nextInt();
			myCurr.add(new Block(tlY, tlX, brY, brX));
		}		
	}
	
	public static void goalConstructor(String goal) throws FileNotFoundException, IOException  {
		BufferedReader br = new BufferedReader(new FileReader(new File(goal)));
		String line;
		myGoal = new Tray(myHeight, myWidth);
		while ((line = br.readLine()) != null) {
			String[] tmp = line.split(" ");
			if (tmp.length != 4) {
				System.out.println("Invalid init and/or goal file.");
				
			}
			Scanner scanner = new Scanner(line);
			int tlY = scanner.nextInt();
			int tlX = scanner.nextInt();
			int brY = scanner.nextInt();
			int brX = scanner.nextInt();
			if (tlY > brY  || tlX > brX) {
				System.out.println("Invalid init and/or goal file.");
				
			}
			myGoal.add(new Block(tlY, tlX, brY, brX));
		}
		br.close();
	}
	
	public static void solve(Tray init) {
		HashSet<Tray> alreadySeen = new HashSet<Tray>();
		LinkedList<Tray> readyToSee = new LinkedList<Tray>();
		
		init.setRoute(new LinkedList<int[]>());
		alreadySeen.add(init);
		readyToSee.push(init);
		while (!readyToSee.isEmpty()) {
			Tray curr = readyToSee.pop();
			if (check(curr)) {
				while (!curr.getRoute().isEmpty()) {
					int[] move = curr.getRoute().pop();
					System.out.println(move[0] + " " + move[1] + " " + move[2] + " " + move[3]);
				}
				return;
			}
			for (Block block : curr.getBlocks()) {
				for (int dx : new int[]{-1, 0, 1}) {
					for (int dy : new int[]{-1, 0, 1}) {
						if (dx * dx + dy * dy == 1 && curr.isSlidableHelper(block, dx, dy)) {
							Tray child = curr.slide(block, dx, dy);
							int prevY = block.topLeftY;
							int prevX = block.topLeftX;
							int nextY = prevY + dy;
							int nextX = prevX + dx;
							child.setRoute(curr.getRoute());
							child.addRoute(new int[] {prevY, prevX, nextY, nextX});
							if (!alreadySeen.contains(child)) {
								alreadySeen.add(child);
								readyToSee.add(child);
							}
						}
					}
				}
			}	
		}
	}
	
	public static boolean check(Tray curr) {
		return curr.check(myGoal);		
	}
}
