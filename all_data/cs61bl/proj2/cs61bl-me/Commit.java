import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.io.File;

public class Commit implements Serializable {

	private int id;
	private Date commitTime;
	private String message;
	private Commit parent;
	private HashMap<String, Integer> tracked;

	public Commit(String message, Commit parent, ArrayList<String> staged,
			ArrayList<String> toUntrack) {
		createCommitTime();
		this.message = message;
		this.parent = parent;
		createID();
		tracked = new HashMap<String, Integer>();
		if (parent != null) {
			tracked.putAll(parent.tracked);
		}
		for (String fileName : staged) {
			tracked.put(fileName, id);
		}
		for (String fileName : toUntrack) {
			tracked.remove(fileName);
		}
	}

	/**
	 * Returns the id of the commit.
	 * 
	 * @return The id of the commit
	 */
	public int getID() {
		return id;
	}

	/**
	 * Returns the commit time (in Pacific Standard Time) of the commit.
	 * 
	 * @return The commit time (in Pacific Standard Time) of the commit
	 */
	public String getCommitTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		return formatter.format(commitTime);
	}

	/**
	 * Returns the commit time (in miliseconds) of the commit
	 * 
	 * @return The commit time (in miliseconds) of the commit
	 */
	public long getCommitTimeMilliseconds() {
		return commitTime.getTime();
	}

	/**
	 * Returns the message of the commit.
	 * 
	 * @return The message of the commit
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the parent commit of the commit.
	 * 
	 * @return The parent commit of the commit
	 */
	public Commit getParent() {
		return parent;
	}

	/**
	 * Returns an ArrayList of filenames the commit is currently tracking.
	 * 
	 * @return The ArrayList of filenames the commit is currently tracking
	 */
	public ArrayList<String> getTrackedFiles() {
		return new ArrayList<String>(tracked.keySet());
	}

	/**
	 * Returns whether or not the commit is currently tracking a given file
	 * 
	 * @param fileName
	 *            The fileName of the file to find
	 * @return true if the commit is tracking the given file
	 */
	public boolean isTracked(String fileName) {
		return tracked.containsKey(fileName);
	}

	/**
	 * Returns the commit id of the commit where the copy of the file is stored.
	 * 
	 * @param fileName
	 *            The fileName of the file to find
	 * @return The commit id of the commit where the copy of the file is stored,
	 *         or null if the file is not being tracked by this commit
	 */
	public Integer getTrackedCommitID(String fileName) {
		return tracked.get(fileName);
	}

	/**
	 * Generates an id for the commit. Only to be called once during
	 * construction.
	 */
	private void createID() {
		if (parent == null) {
			id = 0;
			return;
		}
		int dateField = (int) commitTime.getTime();
		int messageField = Math.abs(message.hashCode());
		int parentField = parent.getID() + 1;

		id = (dateField & 0x007f) << 24 | (messageField & 0x00ff) << 16
				| (parentField & 0x0000ffff);
	}

	/**
	 * Generates the commit time for the commit. Only to be called once during
	 * construction.
	 */
	private void createCommitTime() {
		commitTime = new Date();
	}

	/**
	 * Returns an ArrayList of the modification difference between this array
	 * and the given array.
	 * 
	 * This includes files that have been added or modified in this commit
	 * compared to the given commit, but not files that have been deleted
	 * 
	 * @param toCompare
	 *            The commit to compare to when getting the difference
	 * @return An ArrayList of the modification difference between this array
	 *         and the given array.
	 */
	public ArrayList<String> getModificationDiff(Commit toCompare) {
		ArrayList<String> diffArray = new ArrayList<String>();
		for (String fileName : tracked.keySet()) {
			if (!toCompare.tracked.containsKey(fileName)
					|| !tracked.get(fileName).equals(
							toCompare.tracked.get(fileName))) {
				diffArray.add(fileName);
			}
		}
		return diffArray;
	}

	/**
	 * Returns an ArrayList of the removal difference between this array and the
	 * given array.
	 * 
	 * This includes files that have been removed in this commit compared to the
	 * given commit
	 * 
	 * @param toCompare
	 *            The commit to compare to when getting the difference
	 * @return An ArrayList of the removal difference between this array and the
	 *         given array.
	 */
	public ArrayList<String> getRemovalDiff(Commit toCompare) {
		ArrayList<String> diffArray = new ArrayList<String>();
		for (String fileName : toCompare.tracked.keySet()) {
			if (!tracked.containsKey(fileName)) {
				diffArray.add(fileName);
			}
		}
		return diffArray;
	}

	public String toString() {
		return "Commit " + id + "\n" + getCommitTime() + "\n" + message;
	}

	// testing functions
	public void printTracked() {
		System.out.println("print tracked: ");
		System.out.println(tracked);
	}
}
