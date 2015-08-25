import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.*;

public class CommitNode implements Serializable{
	private String message;
	private int ID;
	private String myTime;
	private Calendar cal = Calendar.getInstance();
	private HashMap<String,File> myFiles;
	private CommitNode parentCommit;
	public int size;
	private String branch;
	private int branchLevel;
	
	private HashMap<String, File> tracking;
	private HashMap<String, File> untracking;
	
	CommitNode(String message, int ID, String branch, int branchLevel){
		this.message = message;
		this.ID = ID;
		TimeZone timezone = TimeZone.getDefault();
		DateFormat date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		date.setTimeZone(timezone);
		myTime = date.format(new Date());
				
		myFiles = new HashMap<String,File>();
		
		this.branch=branch;
		this.branchLevel = branchLevel;
		
		untracking = new HashMap<String, File>();
		tracking = new HashMap<String, File>();
	}
	
	/**
	 * Set the tracking HashMap in this CommitNode
	 * @param tracking
	 */
	public void setTracking(HashMap<String,File> tracking){
		this.tracking = tracking;
	}
	
	/**
	 * Set the untracking HashMap in this CommitNode
	 */
	public void setUntracking(HashMap<String,File> untracking){
		this.untracking = untracking;
	}
	
	/**
	 * Return this CommitNode's message
	 * @return
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * Return this CommitNode's parent
	 * @return
	 */
	public CommitNode parent(){
		return parentCommit; 
	}
	
	/**
	 * Set this CommitNode's parent
	 * @param parentCommit
	 */
	public void setParent(CommitNode parentCommit){
		this.parentCommit = parentCommit;
	}
	
	
	/**
	 * Return the HashMap of this CommitNode's files
	 * @return
	 */
	HashMap<String,File> myFiles(){
		return myFiles;
	}
	
	/**
	 * Add the given file to this CommitNode's files
	 * @param fileName
	 * @param newFile
	 */
	public void addFile(String fileName, File newFile){
		myFiles.put(fileName, newFile);
		size++;
	}
	
	/**
	 * Print all the information about this CommitNode 
	 * -To be used in the log method in CommitList
	 */
	public void Print(){
		System.out.println("===");
		System.out.println("Commit "+ ID);
		System.out.println(myTime);
		System.out.println(message);
	}
	
	
	/**
	 * Return this CommitNode's ID
	 * @return
	 */
	public int ID(){
		return ID;
	}
	
	/**
	 * Return this CommitNode's size
	 * @return
	 */
	public int size(){
		return size;
	}
	
	
	/**
	 * Determine if two CommitNode's are the same, i.e. has equal instance variables
	 * @param otherNode
	 * @return
	 */
	public boolean equals(CommitNode otherNode){
		if(otherNode == null){
			return false;
		}
		
		if(this.message.equals(otherNode.message) && this.ID==otherNode.ID  
			&& this.myTime.equals(otherNode.myTime) && this.myFiles.equals(otherNode.myFiles)
			&& this.branch.equals(otherNode.branch) && this.branchLevel==otherNode.branchLevel
			&& this.size == otherNode.size 
			&& this.cal.equals(otherNode.cal) ){
			System.out.println(branch);
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Return this CommitNode's branchLevel
	 * @return
	 */
	public int branchLevel() {
		return branchLevel;
	}
	
	
	
}
