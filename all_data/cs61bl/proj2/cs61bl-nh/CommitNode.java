
import java.io.*;
import java.util.*;

public class CommitNode implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ID;
	private String message;
	private Date date;
	private CommitNode parentCommit;
	private Map<String, Integer> trackedFiles;
	private Set<String> branchesPartOf;
	private Map<String, CommitNode> children;
	
	/**
	 * Construct a CommitNode (basic constructor: takes in a parent commit, an ID, and a user-left message)
	 * @param parent Parent commit
	 * @param id Unique integer id of this commit
	 * @param msg Message left by user
	 */
	public CommitNode(CommitNode parent, int id, String msg) {
		parentCommit = parent;
		ID = id;
		message = msg;
		date = Calendar.getInstance().getTime();
		trackedFiles = new HashMap<String, Integer>();
		branchesPartOf = new HashSet<String>();
		children = new HashMap<String, CommitNode>();
	}
	
	/**
	 * Constructor to copy a commit
	 * @param toCopy CommitNode to copy instance variables from
	 */
	public CommitNode(CommitNode parent, CommitNode toCopy) {
		parentCommit = parent;
		//ID to be set in Gitlet.java
		message = toCopy.message();
		date = Calendar.getInstance().getTime();
		trackedFiles = toCopy.trackedFiles();
		branchesPartOf = toCopy.branches();
		children = toCopy.getChildren();
	}
	
	/**
	 * Getter method for this commit's id
	 * @return ID of this commit
	 */
	public int id() {
		return ID;
	}
	
	/**
	 * Set the ID for this commit (to be used in rebase, when a copy of a commit needs a new ID
	 * @param id New ID of copied commit
	 */
	public void setID(int id) {
		ID = id;
	}
	
	/**
	 * Getter method for this commit's message
	 * @return Message of this commit
	 */
	public String message() {
		return message;
	}
	
	/**
	 * Getter method for this commit's date
	 * @return Date of this commit (unformatted)
	 */
	public Date date() {
		return date;
	}
	
	/**
	 * Getter method for this commit's parent
	 * @return Parent commit node
	 */
	public CommitNode parent() {
		return parentCommit;
	}
	
	/**
	 * Start tracking a file given by the name and id of the last commit that changed this file
	 * @param fileName Name of file
	 * @param id ID of last commit that changed this file 
	 */
	public void trackFile(String fileName, int id) {
		trackedFiles.put(fileName, id);
	}
	
	/**
	 * Getter method for this commit's tracked files
	 * @return Map of files that this commit tracks with respective ID of last commit file was changed
	 */
	public Map<String, Integer> trackedFiles() {
		return trackedFiles;
	}
	
	/**
	 * Add a branch to this commit's list of branches it is part of
	 * @param branch
	 */
	public void addBranch(String branch) {
		branchesPartOf.add(branch);
	}
	
	/**
	 * Getter method for this commit's list of branches
	 * @return
	 */
	public Set<String> branches() {
		return branchesPartOf;
	}
	
	/**
	 * Add to the map of the commit's children organized by branch
	 * @param branch
	 * @param child
	 */
	public void addChild(String branch, CommitNode child) {
		children.put(branch, child);
	}
	
	/**
	 * Getter method for the commit's map of children
	 * @return Map of branch corresponding to child
	 */
	public Map<String, CommitNode> getChildren() {
		return children;
	}
}
