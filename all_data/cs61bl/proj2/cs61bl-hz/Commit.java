import java.util.*;
import java.io.IOException;
import java.io.Serializable;


/**
 * Represents a commit. 
 */
public class Commit implements Serializable {

	private int id;
	private Commit parent;
	private ArrayList<Commit> children; // we'll see
	private boolean hasCommitted;
	private String message;
	String commitTime;
	private String sourceLocation;
	private HashMap<String, String> tracked;
	private HashSet<String> untracked;
	private HashMap<String, String> files;
	private ArrayList<String> staged;

	/**
	 * Creates a new commit.
	 * 
	 * @param parent
	 *            The parent of the commit
	 * @param id
	 *            Id associated with the commit
	 */
	public Commit(Commit parent, int id) {
		this.id = id;
		children = new ArrayList<Commit>();
		tracked = new HashMap<String, String>();
		untracked = new HashSet<String>();
		files = new HashMap<String, String>();
		staged = new ArrayList<String>();
		this.parent = parent;
		sourceLocation = FileIO.GITLET_PATH + "/" + id;

		if (parent != null) {
			for (String file : parent.tracked.keySet()) {
				tracked.put(file, parent.tracked.get(file));
			}
		}
		hasCommitted = false;
	}

	/**
	 * Adds child as one of the children of the commit
	 * 
	 * @param child
	 *            The child commit
	 */
	public void addChildren(Commit child) {
		children.add(child);
	}

	/**
	 * Retrieves an arrayList of all children of the commit
	 * 
	 * @return ArrayList of children
	 */
	public ArrayList<Commit> getChildren() {
		return children;
	}

	/**
	 * Sets the parent of commit as parent. Used in rare cases where the parent
	 * is not known at the time of creation.
	 * 
	 * @param parent
	 *            The parent commit.
	 */
	public void setParent(Commit parent) {
		this.parent = parent;
		tracked = new HashMap<String, String>();
		for (String file : parent.tracked.keySet()) {
			tracked.put(file, parent.tracked.get(file));
		}
	}
	
	/**
	 * Sets the parent of commit as parent. Tracks the parents files, but does not remove what
	 * was already tracked by the last parent. If the file already exists inside the tracked list,
	 * the value will be overwritten by the given parent.
	 * @param parent Parent commit
	 */
	public void setParentRebase(Commit parent) {
		this.parent = parent;
		for (String file : parent.tracked.keySet()) {
			tracked.put(file, parent.tracked.get(file));
		}
	}

	/**
	 * Sets the message of the commit
	 * 
	 * @param message
	 *            message
	 */
	public void setMessage(String message) {
		if (this.message == null) {
			this.message = message;
		}
	}

	/**
	 * Retrieves the message of the commit. If the commit has not been committed
	 * yet, will be null
	 * 
	 * @return Message of the commit
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Retrieves the parent of the commit
	 * 
	 * @return parent
	 */
	public Commit getParent() {
		return parent;
	}

	/**
	 * Retrieves the ID of the commit. ID should never be changed
	 * 
	 * @return id of the commit
	 */
	public int getId() {
		return id;
	}

	/**
	 * Marks the commit as committed. Should only be called by Commands.commit()
	 */
	public void setCommitted() {
		hasCommitted = true;
	}

	/**
	 * Determines whether the commit has been committed yet.
	 * 
	 * @return True if the commit has been committed.
	 */
	public boolean hasCommitted() {
		return hasCommitted;
	}

	/**
	 * Sets the String representation of time to time
	 * 
	 * @param time
	 *            the string representation of the time of commit
	 */
	public void setTime(String time) {
		commitTime = time;
	}

	/**
	 * Used by log and global-log. Prints the log for the commit
	 * @param println whether the log should have a new line at the end.
	 * 
	 */
	public void helperPrintLog(boolean println) {
		System.out.println("===");
		System.out.println("Commit " + id);
		System.out.println(commitTime);
		System.out.println(message);
		if(println) System.out.println();
	}

	/**
	 * Sets the current instance to a copy of copy for rebase. Should have
	 * different ID and timestamp
	 * @param copy The commit to copy from
	 * 
	 */
	public void createCopy(Commit copy) {
		tracked = copy.tracked;
		untracked = copy.untracked;
		files = copy.files;
		staged = copy.staged;
	}

	/**
	 * Determines whether the file is being tracked in commit
	 * 
	 * @param file
	 *            Path of file
	 * @return True if the file is being tracked
	 */
	public boolean isTracked(String file) {
		return tracked.containsKey(file);
	}

	/**
	 * Determines whether the files is marked for untracking
	 * 
	 * @param file
	 *            Path of file
	 * @return True if the file is marked for untracking
	 */
	public boolean isUntracked(String file) {
		return untracked.contains(file);
	}

	/**
	 * Special case of add file, rm file. File will be tracked, but if the
	 * parent doesn't have the file, the file won't be tracked.
	 * 
	 * @param file Path of file
	 */
	public void stopTracking(String file) {
		tracked.remove(file);
	}

	/**
	 * Starts tracking the file
	 * 
	 * @param file
	 *            Path of file
	 */
	public void startTracking(String file) {
		tracked.put(file, FileIO.GITLET_PATH + "/" + id + "/" + file);
		untracked.remove(file);
	}

	/**
	 * Marks the file for untracking
	 * 
	 * @param file
	 *            Path of file
	 */
	public void startUntracking(String file) {
		tracked.remove(file);
		untracked.add(file);
	}

	/**
	 * Retrieves a set of files marked for untracking. Should not carry over to
	 * the child commit.
	 * 
	 * @return The set of files marked for untracking.
	 */
	public HashSet<String> getUntracked() {
		return untracked;
	}

	/**
	 * Retrieves a HashMap mapping path relative to commit folder to path
	 * relative to PWD. Includes all files that should exist in the commit.
	 * 
	 * @return Map of paths to paths
	 */
	public HashMap<String, String> getFiles() {
		return files;
	}

	/**
	 * Retrieves the path of the commit folder
	 * 
	 * @return Path of the commit folder
	 */
	public String getSourceLocation() {
		return sourceLocation;
	}

	/**
	 * Determines if the commit contains the file.
	 * 
	 * @param file
	 *            Path of file
	 * @return True if the commit contains the file
	 */
	public boolean containsFile(String file) {
		return files.containsKey(file);
	}

	/**
	 * Adds the file to the commit. Does not actually copy the file to the
	 * commit folder
	 * 
	 * @param file
	 *            Path of file relative to commit folder
	 * @param path
	 *            Path of file relative to PWD
	 */
	public void addFile(String file, String path) {
		files.put(file, path);
	}

	/**
	 * Places a file into the present working directory if the file exists in
	 * the commit
	 * 
	 * @param file
	 *            name of file. files.get(file) returns actual path
	 * @throws IOException
	 *             If there was an error copying file from commit folder to PWD
	 */
	public void placeFileInPWD(String file) throws IOException {
		FileIO.placeInPWD(files.get(file), file);
	}

	/**
	 * Copies all of the files in the commit into the PWD. Will overwrite!
	 * 
	 * @param file
	 * @throws IOException
	 *             if there was an error copying file from commit folder to PWD
	 */
	public void replacePWD() throws IOException {
		for (String file : files.keySet()) {
			placeFileInPWD(file);
		}
	}

	/**
	 * Returns a list of all files that have been staged in the commit.
	 * getFiles() should equal getStaged() + getTracked()
	 * 
	 * @return ArrayList of the paths of the files relative to the commit folder
	 */
	public ArrayList<String> getStaged() {
		return staged;
	}

	/**
	 * Retrieves a list of all files that are being tracked. getFiles() should
	 * equal getStaged() + getTracked()
	 * 
	 * @return HashMap mapping path of files relative to the commit folder to
	 *         the actual path of file. Path redirection.
	 */
	public HashMap<String, String> getTracked() {
		return tracked;
	}

	/**
	 * Adds file to the list of files that have been staged
	 * 
	 * @param file
	 *            File to add.
	 */
	public void addStaged(String file) {
		staged.add(file);
	}

}
