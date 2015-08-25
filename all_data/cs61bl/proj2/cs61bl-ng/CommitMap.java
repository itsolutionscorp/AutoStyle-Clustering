import java.io.*;
import java.util.*;

public class CommitMap implements Serializable {

	private ArrayList<CommitNode> idnode; // commit id as index
	private HashMap<String, LinkedList<CommitNode>> branchNode;
	private HashMap<String, LinkedList<CommitNode>> messageNode;

	/**
	 * constructor of CommitMap
	 */
	public CommitMap() {
		idnode = new ArrayList<CommitNode>();
		branchNode = new HashMap<String, LinkedList<CommitNode>>();
		messageNode = new HashMap<String, LinkedList<CommitNode>>();
		CommitNode init = new CommitNode("initial commit", "master", null, null, 0);
		commitNode(init);
	}

	/**
	 * commit helper function, stores commitnode into the map
	 * 
	 * @param a
	 */
	public void commitNode(CommitNode a) { // adding a new node to the system
		idnode.add(a);
		// order branch
		if (branchNode.get(a.getBranch()) == null) {
			LinkedList<CommitNode> temp = new LinkedList<CommitNode>();
			temp.add(a);
			branchNode.put(a.getBranch(), temp);
		} else {
			branchNode.get(a.getBranch()).addFirst(a);
		}
		// order message
		if (messageNode.get(a.getMessage()) == null) {
			LinkedList<CommitNode> temp = new LinkedList<CommitNode>();
			temp.add(a);
			messageNode.put(a.getMessage(), temp);
		} else {
			messageNode.get(a.getMessage()).addFirst(a);
		}

	}

	/**
	 * branch helper function, make a new branch in the map
	 * 
	 * @param branchname
	 */
	public void branch(String branchname) {
		LinkedList<CommitNode> temp = new LinkedList<CommitNode>();
		branchNode.put(branchname, temp);
	}

	/**
	 * get CommitNode with its id
	 * 
	 * @param index
	 * @return
	 */
	public CommitNode getNode(int index) {
		return idnode.get(index);
	}

	/**
	 * get last CommitNode
	 * 
	 * @return
	 */
	public CommitNode getLast() {
		return idnode.get(idnode.size() - 1);
	}

	/**
	 * get first CommitNode
	 * 
	 * @return
	 */
	public CommitNode getfirst() {
		return idnode.get(0);
	}

	/**
	 * used to assign id to new CommitNode
	 * 
	 * @return
	 */
	public int size() {
		return idnode.size();
	}

	/**
	 * get all CommitNodes with the same branch name
	 * 
	 * @param branch
	 * @return
	 */
	public LinkedList<CommitNode> getBranch(String branch) {
		LinkedList<CommitNode> temp = branchNode.get(branch);
		return temp;
	}

	/**
	 * get all CommitNodes with the same commit message
	 * 
	 * @param message
	 * @return
	 */
	public LinkedList<CommitNode> getMessage(String message) {
		LinkedList<CommitNode> temp = messageNode.get(message);
		return temp;
	}

	/**
	 * status helper function
	 * 
	 * @param branchname
	 */
	public void status(String branchname) {
		System.out.println("=== Branches ===");
		for (String s : branchNode.keySet()) {
			if (s.equals(branchname)) {
				System.out.println("*" + s);
			} else {
				System.out.println(s);
			}
		}
		System.out.println();
	}

	/**
	 * remove branch helper function
	 * 
	 * @param branchname
	 */
	public void rmBranch(String branchname) {
		branchNode.remove(branchname);
	}

	/**
	 * global-log helper function
	 */
	public void log() {
		for (CommitNode c : idnode) {
			System.out.println("===");
			System.out.println("Commit " + c.getId());
			System.out.println(c.getTime());
			System.out.println(c.getMessage());
			System.out.println();
		}
	}

}
