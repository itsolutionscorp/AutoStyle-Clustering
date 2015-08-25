import java.text.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.util.*;

/**
 * Class for a commit Node object
 * 
 * @author Anish Balaji
 * @author Jerry Li
 * @author MingFang Zhang
 * 
 * 
 */

public class Node implements Serializable {

	/* Node instance variables */
	private int id;
	private String time;
	private String commitMessage;
	private HashSet<String> files;
	private Node myParent;
	private HashMap<String, String> fileToPath;
	private HashMap<String, Boolean> tracking;

	/* METHODS */

	/**
	 * Constructor, create objects from the class blueprint
	 * 
	 * @param parent
	 *            A Node that stores many message about my Parent.
	 * @param id
	 *            A integer that stores ID numbers
	 * @param msg
	 *            A string that stores the message of a commit
	 */
	public Node(Node parent, int id, String msg) {
		this.id = id;
		commitMessage = msg;
		myParent = parent;
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
				.getInstance().getTime());
		files = new HashSet<String>();
		fileToPath = new HashMap<String, String>();
		if (parent == null) {
			tracking = new HashMap<String, Boolean>();
		} else {
			tracking = (HashMap<String, Boolean>) parent.getTracking().clone();
		}

	}

	/**
	 * Set the files in the stage
	 * 
	 * @param incoming
	 *            a string hashset that saves the name of files
	 * @return a string hashset that saves the name of files in the staging area
	 */
	public void setFiles(HashSet<String> incoming) {
		files = incoming;
	}

	/**
	 * Get the id of each commit
	 * 
	 * @return the id of the commit
	 */
	public int getID() {
		return id;
	}

	/**
	 * Get current time of each commit.
	 * 
	 * @return the current time of the commit.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Gets a node's file tracking statuses.
	 * 
	 * @return a HashMap with the node's tracking statuses.
	 */
	public HashMap<String, Boolean> getTracking() {
		return tracking;
	}

	/**
	 * Sets the commit node's tracking statuses.
	 * 
	 * @param t
	 *            HashMap that will be set to the node's tracking instance
	 *            variable.
	 */
	public void setTracking(HashMap<String, Boolean> t) {
		tracking = t;
	}

	/**
	 * Get input message of each commit.
	 * 
	 * @return the input message of the commit.
	 */
	public String getCommitMessage() {
		return commitMessage;
	}

	/**
	 * Get the names of the files saved in a Node.
	 * 
	 * @return A HashSet with the names of the files.
	 */
	public HashSet<String> getFiles() {
		return files;
	}

	/**
	 * Gets the files that this commit keeps track of
	 * 
	 * @return A HashMap with the file name keys to filePath values.
	 */
	public HashMap<String, String> getFilePaths() {
		return fileToPath;
	}

	/**
	 * set the filePaths of this node to the HashMap param allFiles
	 *
	 * @param allFiles
	 *            A HashMap that will be set to this node's known file paths.
	 */
	public void setFilePaths(HashMap<String, String> allFiles) {
		fileToPath = allFiles;
	}

	/**
	 * get the last version of files
	 * 
	 * @return my last version of files
	 */
	public Node getParent() {
		return myParent;
	}

	/**
	 * get the HashCode for a node
	 * 
	 * @return the hashcode for a commit node
	 */
	@Override
	public int hashCode() {
		return id;
	}

	/**
	 * Display the commit information in the prescribed form, used in log()
	 * method.
	 */
	public void printLog() {
		System.out.println("===");
		System.out.println("Commit " + getID());
		System.out.println(getTime());
		System.out.println(getCommitMessage());
		System.out.println();
	}

}
