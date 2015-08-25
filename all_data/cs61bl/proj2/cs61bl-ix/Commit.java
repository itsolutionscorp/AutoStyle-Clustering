import java.io.*;

import java.nio.file.*;
import java.util.*;

public class Commit implements Serializable {
	private static final long serialVersionUID = -681754400672649707L;
	
	private int myId;
	private int parentId;
	
	private String message;
	private FileTree fileTree;
	
	private Date commitDate;

	/**
	 * Constructor: create root commit
	 */
	public Commit() {
		this("initial commit", 0, -1, new FileTree());
	}
	
	/**
	 * The commit constructor for the all commits beside the initial commit.
	 * @param message
	 * 		the commit message
	 * @param id
	 * 		the commit id
	 * @param previousId
	 * 		the commit id of the previous Commit
	 * @param files
	 * 		the file tree for the corresponding commit
	 */
	public Commit(String message, int id, int prevId, FileTree files) {
		commitDate = new Date();
		myId = id;
		this.message = message;
		parentId = prevId;
		fileTree = files;
	}

	/**
	 * Sets the previous commitID of the commit
	 * @param ID
	 * 		the Commit ID that will be made the previous Commit
	 */
	public void setprev(int ID){
		parentId = ID;
	}
	
	/**
	 * Gets the CommitId of the previous Commit
	 * @return
	 * 		the commitId of the previous Commit
	 */
	public int previousCommit() {
		return parentId;
	}

	/**
	 * Returns the CommitID
	 * @return commitID
	 */
	public int getID() {
		return myId;
	}

	/**
	 * Gets the commit message of the commit
	 * @return 
	 * 		a string of the commit message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the FileTree of the Commit
	 * @return
	 * 		the FileTree of the commit
	 */
	public FileTree getFileTree() {
		return fileTree;
	}

	/**
	 * Gets the date of the Commit
	 * @return the date of the commit in a String
	 */
	public String getDate(){
		return commitDate.toString();
	}
	
	/**
	 * Checks if the filetree contains the given filename
	 * @param FileName
	 * 		the filename that's being checked in a String
	 * @return a boolean 
	 */
	public boolean contains(String FileName){
		return fileTree.getID(FileName) >= 0;
	}
	
	/**
	 * Gets the path of the file in the AllFiles folder
	 * @param Name
	 * 		a string
	 * @return a path - Actual storage in the repository
	 */
	public Path getRealFile(String Name){
		return fileTree.getRealPath(Name);
	}
	
	/**
	 * Gets an arraylist of the paths of AllFiles
	 * @return an ArrayList
	 */
	public ArrayList<Path> getAllFiles(){
		ArrayList<Path> temp = new ArrayList<Path>();
		for(String x: fileTree.returnallfiles()){
			temp.add(FileSystems.getDefault().getPath(x));
		}
		return temp;
	}
}