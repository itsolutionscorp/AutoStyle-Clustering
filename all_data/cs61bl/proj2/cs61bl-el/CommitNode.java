import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @authors Alex Yao: cs61bl-cc, Jennifer Chen: cs61bl-bx, Kai-li Yen: cs61bl-el
 * 
 *          Class that keeps track of a commit. Created each time a commit is
 *          made. Should store a commit message, commit ID, time the commit was
 *          made, the previous commit made, and references to files in the
 *          commit.
 */

public class CommitNode implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	// msg: commit message
	private String msg;

	// commitID: commit ID
	private int commitID;

	// time: time at which the commit was made
	private String time;

	// prev: the commit node that comes before this one (the parent commit node)
	private CommitNode prev;

	// nodeFiles: a hashmap that stores commit objects
	private HashMap<String, CommitObject> nodeFiles = new HashMap<String, CommitObject>();

	// untracked: an ArrayList of the name of the files that are untracked
	private ArrayList<String> untracked = new ArrayList<String>();

	/**
	 * Constructor that initiates prev, msg, commitID, and time.
	 * 
	 * @param myMsg
	 *            commit message
	 * @param myPrev
	 *            current commit node prior to creating this commit node
	 * @param myCount
	 *            commit ID for this commit node
	 */
	
	public CommitNode(String myMsg, CommitNode myPrev, int myCount) {
		prev = myPrev;
		msg = myMsg;
		commitID = myCount;

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String curTime = dateFormat.format(new Date());
		time = curTime;
	}

	/**
	 * Adds files to the nodeFiles HashMap.
	 * 
	 * @param filename
	 *            Name of the file to be added.
	 * @param f
	 *            File to be added.
	 */
	public void addFile(String filename, File f) {
		nodeFiles.put(filename, new CommitObject(f));
	}

	/**
	 * Class CommitObject keeps track of a file and whether or not it is tracked
	 * or untracked.
	 *
	 */
	private class CommitObject implements Serializable {
		// myFile: file stored in this CommitObject
		private File myFile;

		/**
		 * Constructor that sets instance variables. By default, the file is
		 * tracked unless specified otherwise.
		 * 
		 * @param f
		 *            File to be saved/associated with this CommitObject
		 */
		private CommitObject(File f) {
			myFile = f;
		}
	}

	// getKeySet and getFile methods in CommitNode

	/**
	 * Gets all the keys in the nodeFiles hashmap.
	 * 
	 * @return Returns a set of all the keys in nodeFiles. should be a set of
	 *         strings where the strings are file names.
	 */
	public Set<String> getKeySet() {
		return nodeFiles.keySet();
	}

	/**
	 * Adds an file name to the ArrayList 'untracked'.
	 * 
	 * @param filename
	 *            name of the file to be added
	 */
	public void addUntracked(String filename) {
		untracked.add(filename);
	}

	/**
	 * Removes a file name from the ArrayList 'untracked'. If the name does not
	 * exist in 'untracked', do nothing.
	 * 
	 * @param filename
	 *            name of the file to remove
	 */
	public void retrack(String filename) {
		if (untracked.contains(filename)) {
			untracked.remove(filename);
		}
	}

	/**
	 * Gets the file with the corresponding filename.
	 * 
	 * @param filename
	 *            name of the file to retrieve.
	 * @return Returns the file desired.
	 */
	public File getFile(String key) {
		if (nodeFiles.get(key) == null) {
			return null;
		}
		return nodeFiles.get(key).myFile;
	}

	/**
	 * Checks to see whether or not the commit contains the given file.
	 * 
	 * @param filename
	 *            name of the file to be checked
	 * @return Returns true if the commit contains the file. Returns false
	 *         otherwise.
	 */
	public boolean contains(String filename) {
		return nodeFiles.containsKey(filename);
	}

	/**
	 * Tells whether or not the given file is tracked.
	 * 
	 * @param filename
	 *            name of the file in question
	 * @return Returns true if file is tracked. Returns false otherwise.
	 */
	public boolean getTrack(String filename) {
		if (nodeFiles.containsKey(filename)) {
			return !untracked.contains(filename);
		} else {
			return false;
		}
	}

	/**
	 * Sets the track of the given file to the given boolean.
	 * 
	 * @param filename
	 *            name of the file
	 * @param b
	 *            new tracked status to be implemented.
	 */
	public void setTrack(String filename, Boolean b) {
		if (b == true) {
			untracked.remove(filename);
		} else {
			untracked.add(filename);
		}
	}

	/**
	 * Gets the commit message.
	 * 
	 * @return Returns the commit message.
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Gets the commit ID.
	 * 
	 * @return Returns the commit ID.
	 */
	public int getCommitID() {
		return commitID;
	}

	/**
	 * Returns the time stamp of the commit.
	 * 
	 * @return Returns time when the commit was made.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Gets the parent (previous) commit node.
	 * 
	 * @return Returns previous commit node.
	 */
	public CommitNode getPrev() {
		return prev;
	}

	/**
	 * Sets the given files to nodeFiles.
	 * 
	 * @param myFiles
	 *            the hashmap to change nodeFiles to
	 */
	public void setFiles(HashMap<String, CommitObject> myFiles) {
		nodeFiles = myFiles;
	}

	/**
	 * Gets the map of all the files in the commit.
	 * 
	 * @return Returns nodeFiles.
	 */
	public HashMap<String, CommitObject> getFiles() {
		return nodeFiles;
	}

	/**
	 * Copies over the files from a given commitNode to this commitNode's
	 * nodeFiles. If the file to be copied over is untracked in this commitNode,
	 * the file will not be copied over.
	 * 
	 * @param c
	 *            The commitNode whose files are to be copied over.
	 */
	public void copyNodeFiles(CommitNode c) {
		for (String name : c.getKeySet()) {
			if (!c.untracked.contains(name)) {
				nodeFiles.put(name, c.nodeFiles.get(name));
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
