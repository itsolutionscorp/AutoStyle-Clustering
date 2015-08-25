


import java.io.File;
import java.lang.ref.WeakReference;
import java.util.*;

public class Solver {

	private Tray startPoint;
	private Tray goalPoint;
	private HashSet<String> goalConfigs;
	private boolean reachedGoal;
//	private ArrayList<Tray> alreadySeen;
	private HashSet<Tray> alreadySeen; 
	private Stack<Tray> stack;
	private LinkedList<Tray> fringe;
	private Tray end;

	public Solver() {
		startPoint = null;
		goalPoint = null;
		goalConfigs = new HashSet<String>();
		reachedGoal = false;
//		alreadySeen = new ArrayList<Tray>();
		alreadySeen = new HashSet<Tray>();
		stack = new Stack<Tray>();
		fringe = new LinkedList<Tray>();
		end = null;
	}



	public void deadEndParent(Tray deadEnd) {
		Tray t = deadEnd;
		if (t == null) {
			return;
		}
		if (t.getChildren().size() > 1
				|| (t.getChildren().size() - t.numDeadEndChildren()) > 1)
			return;
		t.isDeadEnd = true;
		deadEndParent(t.parent);
	}

	public void makeChildren(Tray t) {
//		System.out.println(t);
		//System.out.println("MAKING CHILDREN FOR TRAY");

//		System.out.println(t.movableBlocks.isEmpty());
//		System.out.println(t.movableBlocks.toString());

		for (Block b:t.movableBlocks) {
//			System.out.println("TRYING TO MOVE " + b);
			for (int[] arr:b.placetomove) {
//				System.out.println("place to move (java sta): " + arr[0] + " " + arr[1]);
				int[] moveInfo = new int[4];
				moveInfo[0] = b.getTopLeftY();
				moveInfo[1] = b.getTopLeftX();
				moveInfo[2] = t.getHeight() - 1 - arr[1];
				moveInfo[3] = arr[0];
				//System.out.println("Top Left Y: " + moveInfo[0]);
				//System.out.println("Top Left X: " + moveInfo[1]);
				//System.out.println("Vertical: " + b.getVertical());
				//System.out.println("Java standard Y: " + (t.getHeight() - 1 - moveInfo[0]));
				//System.out.println("verifying block: " + t.myBlocks[moveInfo[1]][t.getHeight() - 1 - moveInfo[0]]);
				WeakReference<Tray> result = new WeakReference<Tray>(t.moveBlock(b.getTopLeftY(), b.getTopLeftX(), moveInfo[2], arr[0]));
				//Tray result = t.moveBlock(b.getTopLeftY(), b.getTopLeftX(), moveInfo[2], arr[0]);
				result.get().parseBlocks();

				if (alreadySeen.contains(result.get())) {
					//System.out.println("Already seen");
					continue;
				}

				alreadySeen.add(result.get());
//				System.out.println("made the move!");
				result.get().myPrev = moveInfo;
//				result.setEmpty();
//				result.getMovableBlocks();
				t.addChild(result.get());
				result.get().addParent(t);
				// checks for dead end
//				System.out.println(result);
				
				if (result.get().equals(t.parent)) {
//					System.out.println("Reached a dead end, same as parent");
					t.isDeadEnd = true;
					this.deadEndParent(t);
				}
				else if (result.get().movableBlocks.isEmpty()) {
//					System.out.println("Reached a dead end");
					t.isDeadEnd = true;
					deadEndParent(t);

				} else {
					//System.out.println("Child: " + result);
					if (t.isDeadEnd) {
//						System.out.println("i'm not dead");
						t.isDeadEnd = false;
					}
					stack.push(result.get());
//					fringe.offer(result);
//					System.out.println(result.config);
//					System.out.println(goalConfigs);
					if (this.goalPointCheck(result.get())) {
						stack.clear();
//						fringe.clear();
						reachedGoal = true;
						end = result.get();
						ArrayList<int[]> movesToSolution = new ArrayList<int[]>();
						while (end.myPrev != null) {
							movesToSolution.add(end.myPrev);
							end = end.parent;
						}

						if (! movesToSolution.isEmpty()) {
							for (int i = movesToSolution.size()-1; i >= 0; i--) {
								int[] placeholder = movesToSolution.get(i);
								System.out.println(placeholder[0] + " "+ placeholder[1] + " " + placeholder[2] + " " + placeholder[3]);
							}
						}
						return;
					}
				}
			}
		}
	}
	public void solve(Tray t) {
		makeChildren(t);
		while (!stack.isEmpty()) {
			Tray next = stack.pop();
			//System.out.println(next.isDeadEnd);
			makeChildren(next);
		}
	}
	
//	public void solve(Tray t) {
//		for (Block b:t.movableBlocks) {
//			for (int[] arr:b.placetomove) {
//				int[] moveInfo = new int[4];
//				moveInfo[0] = b.getTopLeftY();
//				moveInfo[1] = b.getTopLeftX();
//				moveInfo[2] = t.getHeight() - 1 - arr[1];
//				moveInfo[3] = arr[0];
//				Tray result = t.moveBlock(b.getTopLeftY(), b.getTopLeftX(), moveInfo[2], arr[0]);
//				result.parseBlocks();
//				if (alreadySeen.contains(result)) {
//					continue;
//				}
//
//				alreadySeen.add(result);
//				result.myPrev = moveInfo;
//				t.addChild(result);
//				result.addParent(t);
//				
//				if (result.equals(t.parent)) {
////					System.out.println("Reached a dead end, same as parent");
//					t.isDeadEnd = true;
//					this.deadEndParent(t);
//				}
//				else if (result.movableBlocks.isEmpty()) {
////					System.out.println("Reached a dead end");
//					t.isDeadEnd = true;
//					deadEndParent(t);
//
//				} else {
//					if (t.isDeadEnd) {
//						t.isDeadEnd = false;
//					}
//
//					if (this.goalPointCheck(result)) {
//						reachedGoal = true;
//						end = result;
//						ArrayList<int[]> movesToSolution = new ArrayList<int[]>();
//						while (end.myPrev != null) {
//							movesToSolution.add(end.myPrev);
//							end = end.parent;
//						}
//
//						if (! movesToSolution.isEmpty()) {
//							for (int i = movesToSolution.size()-1; i >= 0; i--) {
//								int[] placeholder = movesToSolution.get(i);
//								System.out.println(placeholder[0] + " "+ placeholder[1] + " " + placeholder[2] + " " + placeholder[3]);
//							}
//						}
//						return;
//					} else {
//						if (!result.isDeadEnd)
//							solve(result);
//					}
//				}
//				if (reachedGoal)
//					return;
//			}
//			if (reachedGoal)
//				return;
//		}
//		if (reachedGoal)
//			return;
//	}

	public void solveQueue() {
		while (!fringe.isEmpty()) {
			Tray t = fringe.poll();
			for (Tray child: t.children) {
				if (child.isDeadEnd) {
					deadEndParent(t);
				} else {
					makeChildren(child);
					if (reachedGoal) {
						return;
					}
				}
			}
			if (reachedGoal) {
				break;
			}	
		}

	}

	public Tray blockReader(String fileName) {
		boolean goalFile = false;
		Tray result = null;
		TextIO.readFile(fileName);
		if (this.startPoint == null) {
			String line = TextIO.getln();
			line = line.trim();
			String[] dimensions = line.split(" ");
//			String height = Character.toString(line.charAt(0));
//			String width = Character.toString(line.charAt(2));
			int y = Integer.parseInt(dimensions[0]);
			int x = Integer.parseInt(dimensions[1]);
			result = new Tray(y, x);
		} else {
			goalFile = true;
			result = new Tray(startPoint.getHeight(), startPoint.getWidth());
		}
		String fileLine;
		while (!TextIO.eof()) {
			fileLine = TextIO.getln();
			fileLine = fileLine.trim();
			//			System.out.prntln(fileLine);
			String[] coords = fileLine.split(" ");
			int y1 = Integer.parseInt(coords[0]);
			int x1 = Integer.parseInt(coords[1]);
			int y2 = Integer.parseInt(coords[2]);
			int x2 = Integer.parseInt(coords[3]);
			//			System.out.println(y1 + "," + x1 + "," +y2 + "," +x2);
			Block b = new Block(y1, x1, y2, x2);
			result.addBlock(b);
			if (goalFile) {
				String goal = Integer.toString(y1) + Integer.toString(x1)
						+ Integer.toString(y2) + Integer.toString(x2);
				goalConfigs.add(goal);
				//				System.out.println(goalConfigs.toString());
			}
		}
		if (!goalFile){
//			result.setAdjacentBlocks();
			result.setMovableBlock();
			result.parseBlocks();
			alreadySeen.add(result);
//			System.out.println("PRINTING ALL THE EMPTY THINGS");
//			for (int[] arr:result.getEmpty()) {
//				System.out.println(arr[0] + " "+  arr[1]);
//			}
//			System.out.println("DONE PRINTING ALL THE EMPTY THINGS");

		}
		return result;
	}

	public boolean goalPointCheck(Tray t) {
		HashSet<String> myConfig = t.parseBlocks();
		for (String a : goalConfigs) {
			if (!myConfig.contains(a))
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Solver s = new Solver();
		Integer height,width;
		boolean caughtException = false;
		boolean good2go = false;
		if (args.length != 2)
			System.out.println("Invalid init and/or goal file.");
		else {
			String initFileName = args[0];
			String goalFileName = args[1];
			File init = new File(args[0]);
			File goal = new File(args[1]);
			if (!init.exists() || !goal.exists())
				System.out.println("Invalid init and/or goal file.");
			else {
				// reads from init config file
				TextIO.readFile(initFileName);
				// read first line of init
				String check = TextIO.getln();
				// removes leading and trailing whitespace
				check.trim();
				String[] dimensions = check.split(" ");
				if (dimensions.length != 2) {
					System.out.println("Invalid init and/or goal file.");
					caughtException = true;
					return;
				}
				try {
					height = Integer.parseInt(dimensions[0]);
					if(height <=0 || height > 256){
						System.out.println("Invalid init and/or goal file.");
						caughtException = true;
						return;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid init and/or goal file.");
					caughtException = true;
					return;
				}
				try {
					width = Integer.parseInt(dimensions[1]);
					if(width <=0 || width > 256){
						System.out.println("Invalid init and/or goal file.");
						caughtException = true;
						return;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid init and/or goal file.");
					caughtException = true;
					return;
				}
				while (!TextIO.eof() && !caughtException) {
					check = TextIO.getln();
					check.trim();
					String[] blockC = check.split(" ");
					if (blockC.length != 4) {
						System.out.println("Invalid init and/or goal file.");
						caughtException = true;
						break;
					}
					for (String d : blockC) {
						int count  = 0;
						try {
							Integer intCheck = Integer.parseInt(d);
							if(count == 0 || count == 2){
								if(intCheck < 0 || intCheck > 256 || intCheck > height){
									System.out.println("Invalid init and/or goal file.");
									caughtException = true;
									break;
								}
							}
							else{
								if(intCheck < 0 || intCheck > 256 || intCheck > width){
									System.out.println("Invalid init and/or goal file.");
									caughtException = true;
									break;
								}
							}
						} catch (NumberFormatException e) {
							System.out
							.println("Invalid init and/or goal file.");
							caughtException = true;
							break;
						}
						count++;
					}
				}
				// reads from goal file
				TextIO.readFile(goalFileName);
				while (!TextIO.eof() && !caughtException) {
					check = TextIO.getln();
					check.trim();
					String[] blockC = check.split(" ");
					if (blockC.length != 4) {
						System.out.println("Invalid init and/or goal file.");
						caughtException = true;
						break;
					}
					for (String d : blockC) {
						int count  = 0;
						try {
							Integer intCheck = Integer.parseInt(d);
							if(count == 0 || count == 2){
								if(intCheck < 0 || intCheck > 256 || intCheck > height){
									System.out.println("Invalid init and/or goal file.");
									caughtException = true;
									break;
								}
							}
							else{
								if(intCheck < 0 || intCheck > 256 || intCheck > width){
									System.out.println("Invalid init and/or goal file.");
									caughtException = true;
									break;
								}
							}
						} catch (NumberFormatException e) {
							System.out.println("Invalid init and/or goal file.");
							caughtException = true;
							break;
						}
						count++;
					}
				}
				if (!caughtException){
					s.startPoint = s.blockReader(initFileName);

					good2go = true;
				}
				if (good2go) {
					s.goalPoint = s.blockReader(goalFileName);
//					s.fringe.offer(s.startPoint);
					//long begin = System.currentTimeMillis();
					s.solve(s.startPoint);
					
//					s.makeChildren(s.startPoint);
//					s.solveQueue();
					//long end = System.currentTimeMillis();
					//System.out.println("time elapsed: " + (end-begin));
				}
			}
		}
	}

}
