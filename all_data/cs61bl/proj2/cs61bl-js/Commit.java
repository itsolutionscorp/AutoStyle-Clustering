import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.io.Serializable;

public class Commit implements Serializable {
	protected HashMap<String, File> tracking;
	protected ArrayList <String> unTracking;
	private File folder;
	private int id;
	private String myMessage;
	private Date myTime;
	private Commit next;
	private Commit prev;
	private static int commitCount = 0;

	// *************CHANGE TO ".gitlet/" BEFORE SUBMITTING***************//
	private static final String GITLET_DIR = ".gitlet/";

	public Commit(String message, Commit previous,
			HashMap<String, File> tracked, String parent, int idNum) {
		id = idNum;
		myTime = new Date();

		myMessage = message;
		prev = previous;
		tracking = tracked;
		unTracking = new ArrayList<String>();
		if (tracked != null) {
			Iterator<Entry<String, File>> trackedIt = tracked.entrySet().iterator();
			while (trackedIt.hasNext()) {
				File temp = trackedIt.next().getValue();
				tracking.put(temp.getName(), temp);
			}
		}

		// Creates the commit folder
		folder = new File(GITLET_DIR + "/" + id);
		folder.mkdir();
	}

	/**
	 * Getter method; returns the (int) id of a commit
	 * 
	 * @return id of a commit
	 */

	public int getID() {
		return id;
	}

	/**
	 * Getter method; returns the folder from a directory.
	 * 
	 * @return (File class) folder.
	 */

	public File getFolder() {
		return folder;
	}

	/**
	 * Getter method; returns the message of a commit.
	 * 
	 * @return (String) message of a commit.
	 */

	public String getMessage() {
		return myMessage;
	}

	/**
	 * Getter method; returns the current time.
	 * 
	 * @return (Date class) the current time this method is called.
	 */

	public Date getTime() {
		return myTime;
	}

//	/**
//	 * Getter method; returns the next node from the current commit.
//	 * 
//	 * @return; returns the next node from the current commit.
//	 */
//
//	public Commit getNext() {
//		return next;
//	}

	/**
	 * Getter method; returns the previous node from the current commit.
	 * 
	 * @return; returns the previous node from the current commit.
	 */

	public Commit getPrev() {
		return prev;
	}

//	/**
//	 * Setter method; sets the next variable to store the next node from the
//	 * current commit.
//	 * 
//	 * @param nextCommit
//	 *            The given nextCommit will be set to the next node from the
//	 *            current commit.
//	 */
//
//	public void setNext(Commit nextCommit) {
//		next = nextCommit;
//	}

	public static void main(String args[]) {
	}

	/**
	 * This method keeps track of the number of commits made.
	 * 
	 * @return the number of commits made.
	 */

	public int createID() {
		int temp = commitCount;
		commitCount++;
		return temp;
	}

	/**
	 * Overrides the original Hashcode() method to return the id of the commit.
	 */

	public int hashCode() {
		return id;
	}
}