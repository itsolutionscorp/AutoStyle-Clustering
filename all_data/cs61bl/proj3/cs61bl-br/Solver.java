import java.util.*;
import java.io.*;

public class Solver {
	private StepsNode firstNode;
	private HashSet<StepsNode> tracker = new HashSet<StepsNode>();
	private ArrayList<SquareBlock> endSquares;


	public static void main(String[] args) {
		try{
		Solver s = new Solver();
		s.endSquares = new ArrayList<SquareBlock>();
		try{
			FileReader reader = new FileReader(args[1]);
			BufferedReader br = new BufferedReader(reader);
			String s1 = null;
			while((s1 = br.readLine()) != null) {
				String[] splited = s1.split(" ");
				s.endSquares.add(new SquareBlock(Integer.parseInt(splited[0]), 
						Integer.parseInt(splited[1]), 
						Integer.parseInt(splited[2]),
						Integer.parseInt(splited[3])));
			}
			br.close();
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("goal not exits");
		} catch (IOException e) {
			e.printStackTrace();
			//i dont care
		}

		File puzzle =new File(args[0]);
		if (puzzle.exists()) {
			s.firstNode = new StepsNode(args[0]);
		} else{
			System.out.println("puzzle not exist");
			return;
		}
		s.solve();
		} catch(Exception e){
			return;
		}
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
		PriorityQueue<StepsNode> critical = new PriorityQueue<StepsNode>(11, new evalComparator());
		critical.add(firstNode);
		StepsNode curNode = null;
		if (!firstNode.couldMove()) {
			//nothing could move
			return;
		}
		while (!critical.peek().squares.containsAll(endSquares)) {
			curNode = critical.poll();
			if (curNode.couldMove() && !tracker.contains(curNode)) {
				curNode.makeMoves();
				for (StepsNode e:curNode.theNexts){
					critical.add(e);
				}
				tracker.add(curNode);
			}
			if (critical.isEmpty()) {
				//not solution
				return;
			}
		}
		critical.peek().makeFinalPrint();
	}
	private class evalComparator implements Comparator<StepsNode> {
		public int compare(StepsNode t, StepsNode t2) {
			int i = 0, j = 0;
			double normDistance = 0;
			for (SquareBlock b : t.squares) {
				for (SquareBlock b2 : endSquares) {
					if (b.fX-b.sX == b2.fX-b2.sX && b.fY-b.sY == b2.fY-b2.sY) {
						double temp = Math.pow(b2.fX - b.fX, 2) + Math.pow(b2.fY - b.fY, 2);
						if (temp < normDistance){
							normDistance = temp;
						}
						Double downDistance = Double.MAX_VALUE, leftDistance = Double.MAX_VALUE,
								rightDistance = Double.MAX_VALUE, upDistance = Double.MAX_VALUE;
						if (b.goDown) {
							downDistance = Math.pow(b2.fX - (b.fX+1), 2) + Math.pow(b2.fY - b.fY, 2);
							if (downDistance < normDistance)
								normDistance = downDistance;
						}
						if (b.goLeft) {
							leftDistance = Math.pow(b2.fX - b.fX , 2) + Math.pow(b2.fY - (b.fY-1), 2);
							if (leftDistance < normDistance)
								normDistance = leftDistance;
						}
						if (b.goRight) {
							rightDistance = Math.pow(b2.fX - b.fX, 2) + Math.pow(b2.fY - (b.fY+1), 2);
							if (rightDistance < normDistance)
								normDistance = rightDistance;
						}
						if (b.goUp) {
							upDistance = Math.pow(b2.fX - (b.fX-1), 2) + Math.pow(b2.fY - b.fY - 1, 2);
							if (upDistance < normDistance)
								normDistance = upDistance;
						}
					}
				}
				i += normDistance;

			} 

			normDistance = 0;
			for (SquareBlock b : t.squares) {
				for (SquareBlock b2 : endSquares) {
					if (b.fX-b.sX == b2.fX-b2.sX && b.fY-b.sY== b2.fY-b2.sY) {
						double temp = Math.pow(b2.fX - b.fX, 2) + Math.pow(b2.fY - b.fY, 2);
						if (temp < normDistance)
							normDistance = temp;
						Double downDistance = Double.MAX_VALUE, leftDistance = Double.MAX_VALUE,
								rightDistance = Double.MAX_VALUE, upDistance = Double.MAX_VALUE;
						if (b.goDown) {
							downDistance = Math.pow(b2.fX -( b.fX+1), 2) + Math.pow(b2.fY - b.fY, 2);
							if (downDistance < normDistance)
								normDistance = downDistance;
						}
						if (b.goLeft) {
							leftDistance = Math.pow(b2.fX - b.fX, 2) + Math.pow(b2.fY - (b.fY-1), 2);
							if (leftDistance < normDistance)
								normDistance = leftDistance;
						}
						if (b.goRight) {
							rightDistance = Math.pow(b2.fX - b.fX, 2) + Math.pow(b2.fY - (b.fY+1), 2);
							if (rightDistance < normDistance)
								normDistance = rightDistance;
						}
						if (b.goUp) {
							upDistance = Math.pow(b2.fX - (b.fX-1), 2) + Math.pow(b2.fY - b.fY, 2);
							if (upDistance < normDistance)
								normDistance = upDistance;
						}
					}
				}
				j += normDistance;
			}

			if (i < j) {
				return -1;
			}
			if (j > i) {
				return 1;
			}
			return 0;
		}
	}
}

