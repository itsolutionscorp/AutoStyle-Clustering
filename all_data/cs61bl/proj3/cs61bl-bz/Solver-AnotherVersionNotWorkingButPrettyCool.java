import java.util.*;
import java.io.*;

public class Solver {
	private StepsNode firstNode;
	private HashSet<StepsNode> tracker = new HashSet<StepsNode>();
	private LinkedHashMap<int[], Shape> goaltray;

	LinkedHashMap<Integer, Shape> shapes;


	public static void main(String[] args) {
		//		try{
		Solver s = new Solver();
		s.goaltray = new LinkedHashMap<int[], Shape>();
		s.shapes = new LinkedHashMap<Integer, Shape>();
		try{
			FileReader reader = new FileReader(args[1]);
			BufferedReader br = new BufferedReader(reader);
			String s1 = null;
			while((s1 = br.readLine()) != null) {
				String[] splited = s1.split(" ");
				int x = Integer.parseInt(splited[0]);
				int y = Integer.parseInt(splited[1]); 
				int x2 = Integer.parseInt(splited[2]);
				int y2 = Integer.parseInt(splited[3]);
				if (s.shapes.containsKey(((x2-x+1) + "" + (y2-y+1)).hashCode())){
					s.goaltray.put(new int[]{x,y} , s.shapes.get(((x2-x+1) + "" + (y2-y+1)).hashCode()));
				}else {
					s.shapes.put(((x2-x+1) + "" + (y2-y+1)).hashCode(),new Shape(x2-x+1,y2-y+1));
					s.goaltray.put(new int[]{x,y} , s.shapes.get(((x2-x+1) + "" + (y2-y+1)).hashCode()));
				}
			}
			br.close();
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("goal not exits");
		} catch (IOException e) {
			e.printStackTrace();
			//i dont care
		}


		s.firstNode = s.new StepsNode(args[0]);

		s.solve();
		//		} catch(Exception e){
		//			System.out.println(1);
		//			return;
		//		}
	}
	//
	//	public static boolean distance(StepsNode node, ArrayList<Square> sq ){
	//		double abc=0;
	//		double bcd=0;
	//		for (Square a : sq){
	//			abc += Math.pow(a, b)node sq dis;
	//			bcd += node sq dis;
	//		}
	//		return abc>bcd;
	//	}
	public void solve() {
		Stack<StepsNode> critical = new Stack<StepsNode>();
		critical.push(firstNode);
		StepsNode curNode = null;
		firstNode.Move();
		if (!firstNode.doWeHaveNext) {
			//nothing could move
			return;
		}

		while (!testsolved(critical)) {
			curNode = critical.pop();
			if (curNode.doWeHaveNext && !tracker.contains(curNode)) {
				for (StepsNode e:curNode.theNexts){
					critical.push(e);
				}
				tracker.add(curNode);
				critical.peek().Move();
			}
			if (critical.isEmpty()) {
				//not solution
				return;
			}
		}
		critical.peek().makeFinalPrint();
	}

	public boolean testsolved(Stack<StepsNode> b){
		boolean solved = true;
		for (int[] a :goaltray.keySet()){
			if (!b.peek().tray.containsKey(a)){
				solved= false;
				break;
			}
			if(!goaltray.get(a).equals(b.peek().tray.get(a))){
				System.out.println(goaltray.get(a).hashCode);
				System.out.println(b.peek().tray.get(a).hashCode);
				solved = false;
				break;
			}
		}
		System.out.println(Arrays.deepToString(goaltray.keySet().toArray()));
		System.out.println(Arrays.deepToString(b.peek().tray.keySet().toArray()));
		return solved;
	}
	public class StepsNode {
		StepsNode myParent;
		boolean doWeHaveNext = false;
		boolean[][] occupiedPos;
		LinkedHashSet<StepsNode> theNexts;
		LinkedHashMap<int[], Shape> tray;
		int hashCode;
		String moveMade;
		int xi;
		int yj;

		StepsNode(String file) {
			theNexts = new LinkedHashSet<StepsNode>();
			tray = new LinkedHashMap<int[], Shape>();
			shapes = new LinkedHashMap<Integer, Shape>();
			try {
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				String s1 = null;
				String[] splited;
				s1 = br.readLine();
				splited = s1.split(" ");
				xi = Integer.parseInt(splited[0]);
				yj = Integer.parseInt(splited[1]);

				occupiedPos = new boolean[xi][yj];
				while((s1 = br.readLine()) != null) {
					splited = s1.split(" ");
					int x = Integer.parseInt(splited[0]);
					int y = Integer.parseInt(splited[1]); 
					int x2 = Integer.parseInt(splited[2]);
					int y2 = Integer.parseInt(splited[3]);
					if (shapes.containsKey(((x2-x+1) + "" + (y2-y+1)).hashCode())){
						tray.put(new int[]{x,y} , shapes.get(((x2-x+1) + "" + (y2-y+1)).hashCode()));
					}else {
						shapes.put(((x2-x+1) + "" + (y2-y+1)).hashCode(),new Shape(x2-x+1,y2-y+1));
						tray.put(new int[]{x,y} , shapes.get(((x2-x+1) + "" + (y2-y+1)).hashCode()));
					}
					for (int i = x; i <= x2; i++) {
						for (int j = y; j <= y2; j++) {
							occupiedPos[i][j] = true;
						}
					}
				}
				br.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String s = "";
			for (int[] key : tray.keySet()) {
				s += key.hashCode();
				s += tray.get(key).hashCode;
			}
			hashCode = s.hashCode();
		}
		/**
		 * 
		 * @param prev
		 * @param x shape of the board
		 * @param y shape of the board
		 */
		StepsNode(StepsNode parent) {
			myParent = parent;
			occupiedPos = parent.occupiedPos.clone();
			theNexts = new LinkedHashSet<StepsNode>();
			tray = new LinkedHashMap<int[], Shape>();
			xi = parent.xi;
			yj = parent.yj;
		}

		public void makeFinalPrint() {
			StepsNode cur = this;
			Stack<String> printer = new Stack<String>();
			while (cur != null) {
				if (cur.myParent != null)
					printer.push(cur.moveMade);
				cur = cur.myParent;
			}
			while (!printer.isEmpty()) {
				System.out.println(printer.pop());
			}
		}

		public void Move() {
			for (int[] a : tray.keySet()) {
				if (a[1] > 0) {
					boolean left = true;
					for (int i = a[0]; i < a[0]+tray.get(a).lenX; i++) {
						if (occupiedPos[i][a[1] - 1]) {
							left = false;
							break;
						}
					}
					if (left){
						StepsNode newNode = new StepsNode(this);
						newNode.moveMade = "" + a[0] + " " + a[1] + " " + a[0] + " " + (a[1]-1);
						String s = "";
						for (int[] b : tray.keySet()) {
							if (b.equals(a)) {
								newNode.tray.put(new int[]{a[0], (a[1]-1)},tray.get(a));
								for (int k = a[0]; k < a[0]+tray.get(a).lenX; k++) {								
									newNode.occupiedPos[k][a[1]-1] = true;
									newNode.occupiedPos[k][a[1]+tray.get(a).lenY-1] = false;
								}
								s += (new int[]{a[0], (a[1]-1)}).hashCode();
								s += tray.get(b).hashCode;
							} else {
								newNode.tray.put(b, tray.get(b));
								s += b.hashCode();
								s += tray.get(b).hashCode;
							}
						}
						newNode.hashCode = s.hashCode();
						theNexts.add(newNode);
						doWeHaveNext = true;
					}
				}
				if (a[0] > 0) {
					boolean up = true;
					for (int j = a[1]; j <= a[1]+tray.get(a).lenY; j++) {
						if (occupiedPos[a[0]-1][j]) {
							up = false;
							break;
						}
						if (up){
							StepsNode newNode = new StepsNode(this);
							newNode.moveMade = "" + a[0] + " " + a[1] + " " + (a[0]-1) + " " + a[1];
							String s = "";
							for (int[] b : tray.keySet()) {
								if (b.equals(a)) {
									newNode.tray.put(new int[]{(a[0]-1), a[1]},tray.get(a));
									for (int k = a[1]; k <= a[1]+tray.get(a).lenY; k++) {								
										newNode.occupiedPos[a[0]+tray.get(a).lenX-1][k] = false;
										newNode.occupiedPos[a[0]-1][k] = true;
									}
									s += (new int[]{(a[0]-1), a[1]}).hashCode();
									s += tray.get(b).hashCode;
								} else {
									newNode.tray.put(b, tray.get(b));
									s += b.hashCode();
									s += tray.get(b).hashCode;
								}
							}
							newNode.hashCode = s.hashCode();
							theNexts.add(newNode);
							doWeHaveNext = true;
						}
					}
				}
				if (a[1]+tray.get(a).lenY< yj) {
					boolean right = true;
					for (int i = a[0]; i <= a[0]+tray.get(a).lenX-1; i++) {
						if (occupiedPos[i][a[1]+tray.get(a).lenY]) {
							right = false;
							break;
						}
					}
					if (right){
						StepsNode newNode = new StepsNode(this);
						newNode.moveMade = "" + a[0] + " " + a[1] + " " + a[0] + " " + (a[1]+1);
						String s = "";
						for (int[] b : tray.keySet()) {
							if (b.equals(a)) {
								newNode.tray.put(new int[]{a[0], (a[1]+1)},tray.get(a));
								for (int k = a[0]; k < a[0]+tray.get(a).lenX; k++) {								
									newNode.occupiedPos[k][a[1]] = false;
									newNode.occupiedPos[k][a[1]+tray.get(a).lenY] = true;
								}
								s += (new int[]{a[0], (a[1]+1)}).hashCode();
								s += tray.get(b).hashCode;
							} else {
								newNode.tray.put(b, tray.get(b));
								s += b.hashCode();
								s += tray.get(b).hashCode;
							}
						}
						newNode.hashCode = s.hashCode();
						theNexts.add(newNode);
						doWeHaveNext = true;
					}
				}
				
				if (a[0]+tray.get(a).lenX< xi) {
					boolean down = true;
					for (int j = a[1]; j <= a[1]+tray.get(a).lenY-1; j++) {
						if (occupiedPos[a[0]+tray.get(a).lenX][j]) {
							down = false;
							break;
						}
					}
					if (down){
						StepsNode newNode = new StepsNode(this);
						newNode.moveMade = "" + a[0] + " " + a[1] + " " + (a[0]+1) + " " + a[1];
						String s = "";
						for (int[] b : tray.keySet()) {
							if (b.equals(a)) {
								newNode.tray.put(new int[]{(a[0]+1), a[1]},tray.get(a));
								for (int k = a[1]; k < a[1]+tray.get(a).lenY; k++) {								
									newNode.occupiedPos[a[0]+tray.get(a).lenX][k] = true;
									newNode.occupiedPos[a[0]][k] = false;
								}
								s += (new int[]{(a[0]+1), a[1]}).hashCode();
								s += tray.get(b).hashCode;
							} else {
								newNode.tray.put(b, tray.get(b));
								s += b.hashCode();
								s += tray.get(b).hashCode;
							}
						}
						newNode.hashCode = s.hashCode();
						theNexts.add(newNode);
						doWeHaveNext = true;
					}

				}

			}
			
		}



		public int hashCode() {
			return hashCode;
		}

		public boolean equals(Object obj) {
			return obj.hashCode() == this.hashCode;
		}

	}
	static class Shape {
		int lenX, lenY;
		int hashCode;

		public Shape(int x, int y) {
			lenX = x;
			lenY = y;
			hashCode = (lenX + "" + lenY).hashCode();
		}

		public int hashCode() {
			return hashCode;
		}


		public boolean equals(Object obj) {
			return obj.hashCode() == this.hashCode;
		}

		public String toString(){
			return (lenX +" "+lenY);
		}
	}

}

