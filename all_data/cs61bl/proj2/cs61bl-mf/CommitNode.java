import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Represents a commit node in the commit tree of a Gitlet object.
 * 
 * @author Rohan Nijhawan, Rocky Kamen-Rubio, Jeffrey Tang, and Mira Chiu
 *
 */

public class CommitNode implements Serializable {
	private int commitID;
	private Date timeMade;
	private CommitNode prev;
	private String message;
	private HashMap<File, File> files;

	/**
	 * Initializes an instance of the CommitNode class.
	 * 
	 * @param id
	 *            an int that represents the commit ID for the commit node
	 * @param p
	 *            a CommitNode that is the previous commit node
	 * @param m
	 *            a String that contains the commit message
	 * @param f
	 *            a HashMap that stores all files tracked by the commit, with
	 *            the user-given file name as the key and a commit ID'd version
	 *            of the file name.
	 */
	public CommitNode(int id, CommitNode p, String m, HashMap<File, File> f) {
		commitID = id;
		timeMade = new Date();
		prev = p;
		message = m;
		files = f;
	}

	/**
	 * Gets the commit ID of the commit node.
	 * 
	 * @return integer commit ID
	 */
	public int getID() {
		return commitID;
	}

	/**
	 * Gets the date and time at which the commit node was made.
	 * 
	 * @return a Date object
	 */
	public Date getDate() {
		return timeMade;
	}

	/**
	 * Gets the previous commit node, which is the last commit made prior to the
	 * current one.
	 * 
	 * @return a CommitNode object
	 */
	public CommitNode getPrev() {
		return prev;
	}

	/**
	 * Gets the commit message of the commit node.
	 * 
	 * @return a String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the HashMap of files tracked by the commit node.
	 * 
	 * @return a HashMap that maps File objects to File objects
	 */
	public HashMap<File, File> getFiles() {
		return files;
	}
}
