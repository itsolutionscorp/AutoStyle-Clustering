import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Calendar;
import java.util.HashSet;
import java.io.IOException;
import java.io.Serializable;

public class Commit implements Serializable{
	private int id;
	private String message;
	private Commit prevCommit;
	private HashMap<String, File> staged;
	private HashMap<String, File> files;
	private HashSet<String> removedFiles;
	private Calendar date;
	
	public Commit (Commit current, HashMap stagedFiles, String mes, int ident, HashSet rm) {
		prevCommit = current;
		staged = stagedFiles;
		message = mes;
		id = ident;
		date = Calendar.getInstance();
		removedFiles = rm;
		files = new HashMap<String, File>();
		try {
			processFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Puts all the files of the previous Commit into a map of files.
	 * 
	 * @throws Exception
	 * 			if the staged files are not empty 
	 */
	public void processFiles() throws Exception {
		if (prevCommit != null && !prevCommit.getFiles().isEmpty()) {
			for (String s : prevCommit.getFiles().keySet()) {
				File f = prevCommit.getFiles().get(s);
				files.put(s, f);
			}
		}
		if (!staged.isEmpty()) {
			for (String s : staged.keySet()) {
				File temp = staged.get(s);
				File destination = new File(".gitlet"+File.separator+"files" + File.separator + "commit"+id, s );
				File temptemp = new File(destination.getParent());
				temptemp.mkdirs();
				Files.copy(temp.toPath(), destination.toPath());
				files.put(s, destination);
				File stage = new File(".gitlet"+File.separator+"stagingArea");
				recursiveDelete(stage);
				stage.mkdir();
			}
		}
		
		for (String s : removedFiles) {
			if (files.containsKey(s)){
				files.get(s).delete();
				files.remove(s);
			}
		}
	}
	
	
	/** 
	 * Returns the files that have been staged.
	 * 
	 * @return the files that are staged. 
	 */
	public HashMap<String, File> getStaged() {
		return staged;
	}
	
	/**
	 * Returns the files that have been removed from a certain directory
	 * or folder. 
	 * 
	 * @return the files that have been removed
	 */
	public HashSet<String> getRemoved() {
		return removedFiles;
	}
	
	/**
	 * Returns the files of a certain directory or folder. 
	 * 
	 * @return the files of a certain directory or folder
	 */
	public HashMap<String, File> getFiles() {
		return files;
	}
	
	/**
	 * Returns the previous commit.
	 * 
	 * @return the previous commit
	 */
	public Commit getPrevCommit() {
		return prevCommit;
	}
	
	/**
	 * Returns a commit id.
	 * 
	 * @return a commit id
	 */
	public int returnID() {
		return id;
	}
	
	/**
	 * Returns a commit message.
	 * 
	 * @return a commit message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns the date on which a commit was committed. 
	 * 
	 * @return the date on which a commit was committed
	 */
	public Calendar returnDate() {
		return date;
	}
	
	/**
	 * Iterates through the files in a certain directory and recursively deletes them.
	 * 
	 * @param d
	 * 			a file or directory that holds other files to be deleted
	 * @throws IOException
	 * 			if the file to be deleted fails to be deleted
	 */
	public void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException("Failed to delete file " + d.getPath());
		}
	}

}