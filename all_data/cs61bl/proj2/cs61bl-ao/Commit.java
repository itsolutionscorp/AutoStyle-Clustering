import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Path;

public class Commit implements Serializable {
	private final String msg;
	private long CommitTime;
	private final int id;
	private Commit prevCommit = null;
	public HashMap<String, String> trackedPaths = new HashMap<String, String>(); // TODO
																					// changed
																					// to
																					// public
																					// for
																					// debugging
																					// purpose,
																					// please
																					// change
																					// back
																					// for
																					// final
																					// submission

	public Commit(String msg, int commitId) {
		this.msg = msg;
		this.id = commitId;
	}

	public int getID() {
		return this.id;
	}

	public String getMsg() {
		return this.msg;
	}

	public Commit getPrev() {
		return this.prevCommit;
	}

	public void setPrevCommit(Commit prev) {
		this.prevCommit = prev;
	}

	public long commitTime() {
		return this.CommitTime;
	}

	public void finishingCommit() {
		this.CommitTime = System.currentTimeMillis();
	}

	public HashMap<String, String> getTrackedPaths() {
		return trackedPaths;
	}

	public void inheritTrackedPaths(HashMap other) { // copy over pointers from
														// parents
		trackedPaths = new HashMap<String, String>(other);
	}

	public void addToTracked(String fileName, Path filePath) { // add/update
																// when there
																// are new or
																// modified
																// files.
		trackedPaths.put(fileName, filePath.toString());
	}

	public boolean containsKey(String k) {
		return trackedPaths.containsKey(k);
	}

}
