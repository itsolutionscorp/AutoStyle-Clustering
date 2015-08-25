import java.util.*;
import java.time.*;
import java.time.format.*;
import java.io.*;
import java.nio.file.*;

//Use this class to be the myItem stored in the linked list in Gitlet.java
public class CommitNode implements Serializable {

	private String message;
	private int id;
	private HashMap<String, File> fileMap;
	private LocalDateTime timeStamp;
	private CommitNode myParent;

	/**
	 * Constructor for creating a CommitNode
	 * 
	 * @param message
	 *            takes in a message along with each commit to make it easily
	 *            identifiable
	 * @param id
	 *            an integer unique to each commit, also for the sake of easy
	 *            identification and location
	 * @param timeStamp
	 *            a time stamp marking when the commit was created
	 * @param files
	 *            a hashmap of file names and files that was in the staging area
	 *            to be saved with the commit
	 * @param parent
	 *            the commit node before the current
	 */
	public CommitNode(String message, int id, LocalDateTime timeStamp,
			HashMap<String, File> files, CommitNode parent) {
		this.message = message;
		this.id = id;
		this.timeStamp = timeStamp;
		this.fileMap = files;
		this.myParent = parent;
	}

	/**
	 * Gets the CommitNode's message
	 * 
	 * @return a String corresponding to this commits given message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets the CommitNode's ID
	 * 
	 * @return an integer corresponding to the commits ID
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Gets the CommitNode's time stamp
	 * 
	 * @return a String representing the time stamp the commit was created
	 */
	public String getTimeStamp() {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		return timeStamp.format(formatter);
	}

	/**
	 * Gets the CommitNode's parent
	 * 
	 * @return the current CommitNode's parent
	 */
	public CommitNode getParent() {
		return this.myParent;
	}

	/**
	 * Gets the CommitNode's file names as a list
	 * 
	 * @return an array of Strings corresponding to the files committed in this
	 *         commit
	 */
	public String[] getFileNames() {
		String[] files = new String[fileMap.size()];
		int i = 0;
		for (String s : fileMap.keySet()) {
			files[i] = s;
			i++;
		}
		return files;
	}

	/**
	 * Gets the CommitNode's File object, given the string as a path
	 * 
	 * @param fileName
	 *            takes in a String corresponding to one of the files in the
	 *            hash map
	 * @return the corresponding file in the hash map
	 */
	public File getFile(String fileName) {
		return fileMap.get(fileName);
	}

	/**
	 * Gets the CommitNode's hash map of files
	 * 
	 * @return the hash map of all the filename to file pairings
	 */
	public HashMap<String, File> getFileMap() {
		return fileMap;
	}

}
