import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/************************************************
 * /////////////// Node Class ////////////////////
 * 
 * @author Kevin Wu, Cynthia Diaz, Evan Patel, Jaehyun Sim
 * 
 **********************************************/
public class CommitTreeNode implements Serializable {
	private static final long serialVersionUID = -123454;
	private String myMessage;
	private int myID;
	private String myTime;
	private HashMap<String, String> trackedFiles;
	private CommitTreeNode myPrev;
	private Boolean isSplitPoint;
	private ArrayList<String> splitTo;

	/**
	 * Creates a commit tree node with default settings
	 */
	public CommitTreeNode() {
		myMessage = "initial commit";
		myID = 0;
		myPrev = null;
		trackedFiles = new HashMap<String, String>();
		isSplitPoint = false;
		splitTo = new ArrayList<String>();
		splitTo.add("master");

	}

	/**
	 * Creates a CommitTreeNode with information from a log and the branches
	 * it's associated with.
	 * 
	 * @param log
	 *            stores information about its time created, ID, and message
	 * @param branchName
	 *            is the name of the branch associated with this node
	 */
	public CommitTreeNode(Log log, String branchName) {
		isSplitPoint = false;
		splitTo = new ArrayList<String>();
		splitTo.add(branchName);
		myMessage = log.getmsg();
		myID = log.getid();
		myTime = log.getTime();
		myPrev = null;
		trackedFiles = new HashMap<String, String>();
	}

	/**
	 * Constructor wiht information such as its previous node and its log (ID,
	 * commit message) and the branches it is associated with
	 * 
	 * @param log
	 *            stores information about its ID and commit message as well as
	 *            time committed
	 * @param prev
	 *            refers to the previous CommitTreeNode
	 * @param branchName
	 *            is the name of the branch associated with this new node
	 */
	public CommitTreeNode(Log log, CommitTreeNode prev, String branchName) {
		splitTo = new ArrayList<String>();
		splitTo.add(branchName);
		isSplitPoint = false;
		myMessage = log.getmsg();
		myID = log.getid();
		myTime = log.getTime();
		myPrev = prev;
		trackedFiles = new HashMap<String, String>();
	}

	/**
	 * This method is called to set a node as a split point
	 */
	public void setSplitPoint() {
		isSplitPoint = true;
	}

	/**
	 * This method indicates whether the node is a split point
	 * 
	 * @return true if the node is a split point
	 */
	public Boolean isSplit() {
		return isSplitPoint;
	}

	/**
	 * Adds a branch to a list holding all the branches that split off from the
	 * node
	 * 
	 * @param branchName
	 *            is a branch that splits off from this node
	 */
	public void addSplit(String branchName) {
		splitTo.add(branchName);
	}

	/**
	 * Removes a branch associated with the node
	 * 
	 * @param branchname
	 *            is the branch's name which is to be removed.
	 */
	public void removeSplit(String branchname) {
		splitTo.remove(branchname);
		if (splitTo.size() == 1) {
			isSplitPoint = false;
		}
	}

	/**
	 * Returns a list of all the branches associated with the node
	 * 
	 * @return an ArrayList of branches splitting from the node
	 */
	public ArrayList<String> getBranches() {
		return splitTo;
	}

	/**
	 * Sets or changes the commit message associated with the node
	 * 
	 * @param msg
	 *            is a commit message.
	 */
	public void setMsg(String msg) {
		myMessage = msg;
	}

	/**
	 * Getter method for the previous node
	 * 
	 * @return the previous CommitTreeNode
	 */
	public CommitTreeNode prev() {
		return myPrev;
	}

	/**
	 * Setter method which changes the pointer of myPrev to the given node
	 * 
	 * @param prevNode
	 *            is another node which should be pointed to by myPrev
	 */
	public void setPrev(CommitTreeNode prevNode) {
		myPrev = prevNode;
	}

	/**
	 * Adds or changes the files which this node tracks
	 * 
	 * @param files
	 *            is a hashmap of tracked files.
	 */
	public void setTrackedFiles(HashMap<String, String> files) {
		trackedFiles = files;
	}

	/**
	 * Getter method for all the files which the node is tracking
	 * 
	 * @return a hashmap containing all the files which the node tracks.
	 */
	public HashMap<String, String> getTrackedFiles() {
		return trackedFiles;
	}

	/**
	 * Getter method to return the ID associated with the node
	 * 
	 * @return the ID of the node
	 */
	public int getID() {
		return myID;
	}

	/**
	 * Getter method for the commit message associated with the node
	 * 
	 * @return a String with the commit message
	 */
	public String getMsg() {
		return myMessage;
	}

	/**
	 * Setter method to change the given node's previous to this
	 * 
	 * @param node
	 *            represents the node in which its myPrev is changing to this
	 */
	public void makePrev(CommitTreeNode node) {
		node.setPrev(this);
	}

	/**
	 * Gitlet's REBASE method will call this when it finds the split point
	 * between 2 branches It will be called on the node (of the current branch)
	 * that splits off (from the given branch) Before calling this method,
	 * Gitlet's rebase will iterate through me (current branch) to make sure
	 * branchHead is not in my history
	 * 
	 * @param branchHead
	 *            a CommitTreeNode that is the head of the branch given to
	 *            Gitlet's REBASE method.
	 */
	public void rebase(CommitTreeNode branchHead) {
		// CHECK IF branchHead is already in my history
		myPrev = branchHead;
	}

}
