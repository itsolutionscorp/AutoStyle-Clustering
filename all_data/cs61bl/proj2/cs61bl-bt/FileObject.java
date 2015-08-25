import java.io.*;
import java.util.*;
import java.text.*;

public class FileObject extends File {
	private String fileName;
	private long id;
	private String lastTime;
	private boolean staged;
	private boolean marked;
	private boolean modified;
	private boolean conflicted;
	
	/**
	 * Constructs a FileObject with given name as file name
	 * 
	 * @param name
	 * 			the name of this FileObject
	 */
	public FileObject(String name){
		super(name);
		fileName = name;
		staged = false;
		marked = false;
		modified = false;
		conflicted = false;		
	}
	
	/**
	 * Implements Java.io.File's constructor
	 * (Creates a new File instance from a parent abstract pathname and a child pathname string.)
	 * 
	 * @param arg0
	 * 			the parent abstract pathname
	 * @param arg1
	 * 			The child pathname string
	 */
	public FileObject(File arg0, String arg1) {
		super(arg0, arg1);
	}

	/**
	 * Gets the name of this FileObject
	 * 
	 * @return the name of this FileObject
	 */
	public String getFileName(){
		return fileName;
	}

	/**
	 * Tells whether the FileObject is staged for next Commit
	 * 
	 * @return true if this FileObject has been added to the staging area
	 */
	public boolean isStaged(){
		return staged;
	}
	
	/**
	 * Marks the FileObject as staged, to be included in the next Commit
	 */
	public void stage(){
		staged = true;
	}
	
	/**
	 * Removes the FileObject from staging area if it is staged.
	 * Unmarks it if it is marked for untracking.
	 */
	public void unstage(){
		if(staged)
			staged = false;
		else if (!marked)
			marked = true;
		else
			System.out.println("No reason to remove the file");
	}
	
	/**
	 * Tells whether this FileObject is marked for untracking.
	 *  
	 * @return true if marked 
	 */
	public boolean isMarkedForUntracking(){
		return marked;
	}
	
	/**
	 * Marks this FileObject for untracking,
	 * causing the file to be excluded for the next Commit.
	 */
	public void mark(){
		marked = true;
	}
	
	/**
	 * Set this FileObject to be tracked by the Commit it is in.
	 */
	public void unmark(){
		marked = false;
	}
	
	/**
	 * Tells if a FileObject is in a conflicted state when merging branches.
	 * 
	 * @return true if different versions of this file exists when merging
	 */
	public boolean conflicted(){
		return conflicted;
	}
	
	/**
	 * 
	 * @return true if this FileObject has been modified since
	 */
	public boolean isModified(){
		return modified;
	}
	
	/**
	 * Gets the file's name without any path to directory at the front.
	 * 
	 * @param path
	 * 			the full directory(including file name) of this FileObject
	 * @return the FileObject's name 
	 */
	public static String extractFileName(String path){
		int lastSlashInd = path.lastIndexOf("/");
		return path.substring(lastSlashInd+1);
	}
	
	/**
	 * Gets the file's directory without its name attached at the end.
	 * 
	 * @param path
	 * 			the full directory(including file name) of this FileObject
	 * @return String representation of this FileObject's path
	 */

	public static String extractFolders(String path){
		int lastSlashInd = path.lastIndexOf("/");
		if (lastSlashInd != -1){
			return path.substring(0, lastSlashInd);
		} else{
			return path.substring(0, 0);
		}
	}
}
