import java.text.DateFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Commit implements Serializable {

	private int id;
	private String message;
	private String time;
	private boolean isHead;
	// private ArrayList<String> myFiles = new ArrayList<String>();
	private HashMap<String, FileObject> myFiles2 = new HashMap<String, FileObject>();
	private HashMap<FileObject, String> myFileDir = new HashMap<FileObject, String>();
	private CommitNode myCommitNode;
	private CommitNode myParentCN;

	/**
	 * Creates a new commit with the given message and CommitNode. It records
	 * the time and date it was made and sets the commit to be the head of the
	 * current branch. By default, id is 0, but is overwritten so that it is
	 * given the correct id.
	 * 
	 * @param msg
	 *            the commit's message
	 * @param cn
	 *            the commit's CommitNode
	 */
	public Commit(String msg, CommitNode cn) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		time = dateFormat.format(cal.getTime());
		message = msg;
		isHead = true;
		id = 0;
		myCommitNode = cn;
		if (myCommitNode != null) {
			myParentCN = myCommitNode.parent();
		}
		if (Gitlet.hstr != null) {
			id = Gitlet.hstr.item().getID() + 1;
			myFiles2 = (HashMap<String, FileObject>) myParentCN.item().getFiles().clone();
			myFileDir = (HashMap<FileObject, String>) myParentCN.item().getFileDir().clone();
		}
	}

	/**
	 * Creates a new commit with the same attributes as the main commit
	 * constructor. However, its CommitNode is set to null.
	 * 
	 * @param msg
	 *            the commit's message
	 */
	public Commit(String msg) {
		this(msg, null);
	}

	/**
	 * Constructs a copy of an existing Commit, but with its own time and ID.
	 * 
	 * @param copy
	 * 			the Commit object to be copied
	 * @param cn
	 * 			the CommitNode to contain this Commit
	 * @param parentCN
	 * 			the parent CommitNode of this Commit
	 */
	public Commit(Commit copy, CommitNode cn, CommitNode parentCN) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		time = dateFormat.format(cal.getTime());
		id = Gitlet.hstr.item().getID() + 1;
		this.message = copy.message;
		this.isHead = false;
		this.myFiles2 = (HashMap<String, FileObject>) copy.myFiles2.clone();
		this.myFileDir = (HashMap<FileObject, String>) copy.myFileDir.clone();
		this.myCommitNode = cn;
		this.myParentCN = parentCN;
	}

	/**
	 * @return the commit's CommitNode's parent
	 */
	public CommitNode getParentCN() {
		return myParentCN;
	}

	/**
	 * @return the commit's CommitNode
	 */
	public CommitNode getCommitNode() {
		return myCommitNode;
	}

	/**
	 * Sets the commit's CommitNode's parent.
	 * 
	 * @param cn
	 *            the new commit's CommitNode's parent CommitNode
	 */
	public void setParentCN(CommitNode cn) {
		this.myParentCN = cn;
		this.myCommitNode.setParent(cn);
	}

	/**
	 * Sets the commit's CommitNode.
	 * 
	 * @param cn
	 *            the new commit's CommitNode
	 */
	public void setCommitNode(CommitNode cn) {
		this.myCommitNode = cn;
	}

	/**
	 * @return the HashMap containing all of the commit's files and their file
	 *         paths
	 */
	public HashMap<FileObject, String> getFileDir() {
		return myFileDir;
	}

	/**
	 * Adds a file and file path pair to myFileDir.
	 * 
	 * @param obj
	 *            FileObject to be added
	 * @param path
	 *            file path of the file to be added
	 */
	public void addFileDir(FileObject obj, String path) {
		myFileDir.put(obj, path);
	}

	/**
	 * Removes a file and file path pair from myFileDir.
	 * 
	 * @param obj
	 *            the key of the to-be-deleted myFileDir element
	 */
	public void removeFileDir(FileObject obj) {
		myFileDir.remove(obj);
	}

	/**
	 * @return the commit's unique ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * @return the commit's message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Removes a name and file pair from myFiles2.
	 * 
	 * @param s
	 *            the key of the to-be-deleted myFiles2 element
	 */
	public void removeFile(String s) {
		myFiles2.remove(s);
	}

	/**
	 * Adds a name and file pair to myFiles2.
	 * 
	 * @param s
	 *            name of the file to be added
	 * @param obj
	 *            file to be added
	 */
	public void addFile(String s, FileObject obj) {
		myFiles2.put(s, obj);
	}

	/**
	 * @return the date and time of when the commit was made
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return the HashMap containing all name and file pairs of this commit
	 */
	public HashMap<String, FileObject> getFiles() {
		return myFiles2;
	}

	/**
	 * Tells whether a file is currently being tracked the commit.
	 * 
	 * @param fileName
	 *            name of the file to check if being tracked
	 * @return true if the commit is currently tracking the file
	 */
	public boolean contains(String fileName) {
		return myFiles2.containsKey(fileName);
	}

	/**
	 * Finds the commit with the given id.
	 * 
	 * @param id
	 *            the id of the commit you want
	 * @return the commit with the given id
	 */
	public static Commit returnCommit(int id) {
		for (Commit a : Gitlet.allCommits) {
			if (a.getID() == id) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Tells whether the commit with the given id exists
	 * 
	 * @param id
	 *            the id of the commit you want to check
	 * @return true if the commit with the given id exists
	 */
	public static boolean commitExists(int id) {
		for (Commit a : Gitlet.allCommits) {
			if (a.getID() == id) {
				return true;
			}
		}
		return false;
	}
}