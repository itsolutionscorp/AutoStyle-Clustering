import java.text.SimpleDateFormat;
import java.util.*;
import java.io.Serializable;

public class CommitNode implements Serializable {

	public int id;
	public CommitNode parentNode;
	public CommitNode pointer;

	public String name; // branchname
	public String nameOfNode;
	public String message;
	public String time;

	public ArrayList<String> snapshots; 
	public ArrayList<String> parentFiles;
	public ArrayList<String> pathsToFiles; 
	
	/**
	 * A constructor for a CommitNode.
	 * This CommitNode is a pointer/branch.
	 * @param p 
	 * 		a CommitNode this CommitNode is pointing to.
	 * @param nameOfBranch
	 * 		a string that represents the name of the pointer/branch
	 * 		
	 */
	public CommitNode(CommitNode p, String nameOfBranch) {
		name = nameOfBranch;
		pointer = p;

	}
	/**
	 * A constructor for a CommitNode.
	 * This CommitNode is a pointer/branch.
	 * @param p 
	 * 		a CommitNode this CommitNode is pointing to.
	 * @param nameOfBranch
	 * 		a string that represents the name of the pointer/branch
	 * 		
	 */
	public CommitNode(CommitNode c) {
		pointer = c;
	}

	/**
	 * A constructor for a CommitNode.
	 * This CommitNode holds all information (including all 
	 * filenames and pathnames of parent's snapshots) of a 
	 * particular commit. 
	 * -called when user commits.
	 * @param pNode
	 * 		a CommitNode that is the parent of the node
	 * @param msg
	 * 		a string message the user inputs to describe a commit
	 * @param  commitID
	 * 		an int that represents what number commit the node is
	 * 
	 */
	public CommitNode(CommitNode pNode, String msg, int commitID) {

		id = commitID;
		message = msg;
		parentNode = pNode;
		nameOfNode = "Commit " + commitID;
		
		pathsToFiles = new ArrayList<String>();
		parentFiles = new ArrayList<String>();
		snapshots = new ArrayList<String>();

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(now);

	}

}