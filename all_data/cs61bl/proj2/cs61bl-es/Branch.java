import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Branch implements Serializable, Iterable<Commit> {
	private static final long serialVersionUID = 3537639254756460386L;
	private String name;
	Commit head;
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * branch constructor 
	 * @param name
	 * 			String of branch name 
	 * @param g
	 * 			Gitlet Object 
	 */
	public Branch(String name, Gitlet g) {
		if (name.equals("master")) {
			// default branch: master branch
			// get time stamp first
			Calendar cal = Calendar.getInstance();
			String init_timestamp = dateFormat.format(cal.getTime());
			// set name of the branch
			this.name = name;
			// create the initial commit using the above time stamp
			int new_id = 0; // the initial commit always has id 0
			this.head = new Commit(new_id, "initial commit", init_timestamp, null);
			// add <"initial commit", (0)> to commits_name_id
			ArrayList<Integer> id_0 = new ArrayList<Integer>();
			id_0.add(new_id);
			g.commits_name_id.put("initial commit", id_0);
			// add head to commits_id_pointer at index 0
			g.commits_id_pointer.put(new_id, head);
			// update num_of_commit -- now we got 1 commit
			g.num_of_commit++;
		} else {
			// custom branch (whose name is different from "master")
			// set name of the branch
			this.name = name;
			// set head to g.curr_branch.head
			this.head = g.curr_branch.head;
		}
	}

	/**
	 * overrides equals method to compare two objects
	 */
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Branch)obj).name); // two branches should not have the same name
	}

	/**
	 * determines hashcode for particular objects 
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	/**
	 * constructs Branch iterator 
	 */
	@Override
	public Iterator<Commit> iterator() {
		return new BranchIterator();
	}

	/**
	 * 
	 * Nested Branch Iterator class. Implements Iterator interface with 
	 * hasNext and Next and Remove methods (*Remove not implemented)
	 *
	 */
	private class BranchIterator implements Iterator<Commit> {

		private Commit current_commit;
		public BranchIterator() {
			current_commit = head; 
		}

		@Override
		public boolean hasNext() {
			return current_commit != null;
		}

		@Override
		public Commit next() {
			Commit rtn = current_commit;
			current_commit = current_commit.prev();
			return rtn;
		}

		@Override
		public void remove() {
			return;
		}

	}

	/**
	 * Attempts to mark an untracked file for tracking.
	 *   The file was previously removed since the most recent commit,
	 *   but now the user wants to add it back.
	 * @param file
	 *            a file that will be checked for transfer
	 * @return true if transfer is successful
	 */
	public boolean addHelper_moveFromUntrackToTrack(String file, Gitlet g) {
		Boolean transfer = g.removed.remove(file);
		if (transfer) {
			// the file exists and is marked as UNTRACKING,
			// so just mark it from UNTRACKING to TRACKING
			// as what we just did by using remove
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves file to the staging area.
	 * @param file
	 *            a file that will be copied to the staging area
	 */
	public void addHelper_moveToStage(String file) {
		// add a copy of the file to the staging area (replacing the existing if needed)
		Gitlet.copyAndPasteFileToFolder
		(file, Gitlet.STAGE_PATH, "", false);
	}

	/**
	 * Attempts to create a new commit.
	 *   Saves a backup of certain files so they can be restored at a later time.
	 * @param msg
	 *            name of the new commit
	 * @param g
	 *            a Gitlet object reference
	 */
	public void commit(String msg, Gitlet g) {
		// first, remember the moment (the time stamp) that we enter this method
		Calendar cal = Calendar.getInstance();
		String timestamp = dateFormat.format(cal.getTime());

		// then, check if the user can create a new commit
		boolean staging_area_empty = false;
		File stage_files = new File(Gitlet.STAGE_PATH);
		staging_area_empty = (stage_files.list().length == 0);
		if (staging_area_empty) {
			if (g.removed.isEmpty()) {
				// the staging area is empty and 
				// nothing is removed,
				// abort
				Gitlet.printError("No changes added to the commit.");
			}
		}
		// at this point, everything is fine and create a new commit:
		// (1) create the new commit object
		int new_id = new IDGenerator(msg, timestamp, head.id()).commitID();
		Commit new_commit = new Commit(new_id, msg, timestamp, head);
		g.num_of_commit++;
		// (2) copy head.tracked to new_commit.tracked
		new_commit.tracked.putAll(head.tracked);
		// (3) delete removed files from new_cmmit.tracked
		for (String victim : g.removed) {
			new_commit.tracked.remove(victim);
		}
		// (4) clear removed
		g.removed.clear();
		// (5) create a new empty commit folder with the name of the id
		String commit_path = Gitlet.GITLET_PATH + new_id;
		File commit_folder = new File(commit_path);
		commit_folder.mkdir();
		// (6) copy and paste everything from the staging area to the commit folder,
		//     put everything into new_commit.tracked,
		//     delete everything in the staging area
		try {
			Gitlet.copyAndPasteFolderToFolder(new File(Gitlet.STAGE_PATH), commit_folder, new_commit);
			recursiveDelete(new File(Gitlet.STAGE_PATH));
			File staging_folder = new File(Gitlet.STAGE_PATH);
			staging_folder.mkdir();
		} catch (IOException e) {
			/* Ignore EXCEPTIONNAME. */
		}
		// (7) update head, g.commits_name_id and g.commits_id_pointer 
		head = new_commit;

		// if the corresponding id array does not exist, create it first
		// then add new_id to the id array,
		// fianlly put <msg, id_array> into g.commits_name_id
		ArrayList<Integer> id_array = g.commits_name_id.get(msg);
		if (id_array == null) {
			id_array = new ArrayList<Integer>();
		}
		id_array.add(new_id);
		g.commits_name_id.put(msg, id_array);
		g.commits_id_pointer.put(new_id, head);
		return;
	}


	/**
	 * Attempts to remove a file. Mark the file for untracking; this means it will not be included 
	 * in the upcoming commit, even if it was tracked by that commit's parent. If the file had been 
	 * staged, instead just unstage it, and don't also mark it for untracking.
	 * @param file
	 *        a file to be removed
	 * @param g
	 * 				Gitlet object 
	 */
	public void remove(String file, Gitlet g) {
		// physically delete file from the staging area if the file is there
		File file_in_staging_area = new File(Gitlet.STAGE_PATH + file);
		if (file_in_staging_area.exists()) {
			file_in_staging_area.delete();
			// Note: we delete the file but not the folder,
			//       e.g. /Users/yba/Documents/U/gitlet/.gitlet/stage/test/wug.txt is gone
			//       but /Users/yba/Documents/U/gitlet/.gitlet/stage/test is still there
			return;
		}
		// check tracked of head and add it to removed if it is in tracked
		if (head.tracked.containsKey(file)) {
			g.removed.add(file);
			return;
		}
		// the file is neither staged nor tracked by the head commit, 
		// so print the error message
		Gitlet.printError("No reason to remove the file.");
		return;
	}

	/**
	 * Deletes the file and all files inside it, if it is a directory.
	 * @param d 
	 * 				file to be recursively deleted 
	 * @throws IOException
	 * 				when an I/O exception of some sort has occurred 
	 */
	static void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException();
		}
	}

	/**
	 * Getter method for name
	 */
	public String name() {
		return name;
	}
}
