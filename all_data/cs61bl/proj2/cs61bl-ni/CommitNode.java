import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommitNode implements Serializable {
	// store info of previous node

	private String message;
	private String branchname;
	private String time;
	private HashSet<String> file;
	private CommitNode prev;
	private int id;

	/**
	 * CommitNode constructor
	 * 
	 * @param msg
	 *            commit message
	 * @param bname
	 *            branch name
	 * @param tobeadded
	 *            path of tracking files
	 * @param previous
	 *            previous commit
	 * @param num
	 *            id of the commit
	 */
	public CommitNode(String msg, String bname, HashSet<String> tobeadded, CommitNode previous, int num) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		time = format.format(now);
		message = msg;
		branchname = bname;
		if (tobeadded != null) {
			file = (HashSet<String>) tobeadded.clone();
		}
		prev = previous;
		id = num;
	}

	/**
	 * Gitlet log helper function
	 */
	public void log() {
		System.out.println("===");
		System.out.println("Commit " + id);
		System.out.println(time);
		System.out.println(message);
		System.out.println();
		if (prev != null) {
			prev.log();
		}
	}

	/**
	 * get previous CommitNode
	 * 
	 * @return
	 */
	public CommitNode getPrev() {
		return prev;
	}

	/**
	 * get commit id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * get commit time
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * get commit branch name
	 * 
	 * @return
	 */
	public String getBranch() {
		return branchname;
	}

	/**
	 * get commit message
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * get all the file path in this commit
	 * 
	 * @return
	 */
	public HashSet<String> getPath() {
		return file;
	}

	/**
	 * get file path of a file
	 * 
	 * @param filename
	 * @return
	 */
	public String getFilePath(String filename) {
		for (String s : file) {
			Path p = Paths.get(s);
			if (p.getFileName().toString().equals(filename)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * check if the commit is tracking the file
	 * 
	 * @param filename
	 * @return
	 */
	public boolean contains(String filename) {
		for (String s : file) {
			Path p = Paths.get(s);
			if (p.getFileName().toString().equals(filename)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * commit helper function, updates file path
	 * 
	 * @param files
	 */
	public void addFile(File[] files) {
		for (File f : files) {
			if (getFilePath(f.getName()) != null) {
				file.remove(getFilePath(f.getName()));
			}
			file.add(f.getPath());
		}
	}

	/**
	 * how many file is the commit tracking
	 * 
	 * @return
	 */
	public int filesize() {
		return file.size();
	}

}
