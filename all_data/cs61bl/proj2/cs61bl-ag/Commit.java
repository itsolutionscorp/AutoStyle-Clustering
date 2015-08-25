import java.io.Serializable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This creates a commit object for every commit made in Gitlet
 * 
 * @author Andrew Hild (AH)
 * @author Arshia Malkani (AX)
 * @author Katie Jiang (BI)
 * @author Nathaniel Low (AG)
 *
 */



@SuppressWarnings("serial")
public class Commit implements Serializable {
	
	
	// The parent, ID, comment, and time created associated with the Commit
	private Commit parent;
	private int id;
    private String comment, timeCreated;
       
	// Hashmap for fileNames as keys and the actual files and values
	private HashMap<String, File> files;
	// ArrayList of all the files in the commit
	private ArrayList<File> filesList;

    // A Hashmap with the fileNames as the key and the integer as the ID
    // in which the file was last modified as the value
    private HashMap<String, Integer> lastModified;
    // An arrayList of the files marked for untracking
    private ArrayList<File> untracking;
    
    /**
     * Constructor for initial Commit
     */
    public Commit() {
        files = new HashMap<String, File>();
        lastModified = new HashMap<String,Integer>();
        filesList = new ArrayList<File>();
        untracking = new ArrayList<File>();
        parent = null;
        // Get the date and time at which the commit was created. Thanks to
        // http://stackoverflow.com/questions/1459656/how-to-get-the-current-time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
        // for cluing me in to the SimpleDateFormat class.
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeCreated = s.format(d);
        id = 0;
        comment = "initial commit";
    }
    
    
    /**
     * Constructor for a commit
     * @param n 
     * 		Parent commit
     * @param 
     * 		ID the ID of current commit
     */
    public Commit(Commit n, int id) {
        files = new HashMap<String, File>();
        for (String s : n.files.keySet()) {
            files.put(s, n.files.get(s));
        }
        lastModified = new HashMap<String, Integer>();
        for (String s : n.lastModified.keySet()) {
            lastModified.put(s, n.lastModified.get(s));
        }
        untracking = new ArrayList<File>();
        for (File f : n.untracking) {
        	untracking.add(f);
        }
        filesList = new ArrayList<File>();
        for (File f : n.filesList) {
            filesList.add(f);
        }
        parent = n;
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeCreated = s.format(d);
        this.id=id;
    }
    
    /**
     * Constructor for a commit
     * @param n  
     * 		Parent commit
     * @param comment 
     * 		Comment of the current commit
     * @param id 
     * 		ID of current commit
     */
    public Commit(Commit n, String comment, int id) {
        files = new HashMap<String, File>();
        for (String s : n.files.keySet()) {
            files.put(s, n.files.get(s));
        }
        untracking = new ArrayList<File>();
        for (File f : n.untracking) {
        	untracking.add(f);
        }
        filesList = new ArrayList<File>();
        for (File f : n.filesList) {
            filesList.add(f);
        }
        parent = n;
        lastModified = new HashMap<String, Integer>();
        for (String s : n.lastModified.keySet()) {
            lastModified.put(s, n.lastModified.get(s));
        }
        this.comment = comment;
        this.id = id;
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeCreated = s.format(d);
    }
    
    /** 
     * Constructor for a commit during rebase. 
     * @param id 
     *  	ID of current commit
     * @param n
     * 		Parent commit
     * @param h
     * 		hashMap of the file names and IDs
     */
    public Commit(int id, Commit n, HashMap<String, Integer> h) {
    	files = new HashMap<String, File>();
        for (String s : n.files.keySet()) {
            files.put(s, n.files.get(s));
        }
        filesList = new ArrayList<File>();
        for (File f : n.filesList) {
            filesList.add(f);
        }
        untracking = new ArrayList<File>();
        for (File f : n.untracking) {
        	untracking.add(f);
        }
        parent = n.parent;
        lastModified = new HashMap<String, Integer>();
        for (String s : n.lastModified.keySet()) {
        	lastModified.put(s, n.lastModified.get(s));
        }
        for (String s : h.keySet()) {
        	if (lastModified.containsKey(s)) {
        		if (h.get(s) > n.getLastModified().get(s)){
        			lastModified.put(s, h.get(s));
        		}
        	}
        	else {
        		lastModified.put(s, h.get(s));
        	}
        }
        this.id = id;
        comment = n.comment;
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeCreated = s.format(d);
    }
    
    /**
     * Adds a file to the List of files and the hashMap of files
     * @param f
     * 		File to be added
     */
    public void add(File f) {
        files.put(f.getPath(), f);
        filesList.add(f);
    }
    
    /**
     * Adds the file to the untracking ArrayList
     * @param f
     * 		File to be untracked
     */
    public void untrack(File f) {
    	untracking.add(f);
    }
    
    /**
     * Recreates the untracking ArrayList to clear it
     */
    public void clearUntracked() {
    	untracking = new ArrayList<File>();
    }
    
    /**
     * Gets the ArrayList of untracked Files
     * @return ArrayList of files
     */
    public ArrayList<File> getUntracked() {
    	return untracking;
    }
    
    /**
     * Adds a file to the last Modified hashMap 
     * @param f
     * 		file being added to the hashMap
     */
    public void addLastModified(File f) {
        lastModified.put(f.getPath(), new Integer(id));
    }
    
    /**
     * Gets the files in the commit
     * @return ArrayList of files in the commit
     */
    public ArrayList<File> getTracked() {
        return filesList;
    }
    
    /**
     * Gets the files hashMap containing files names as keys and files as values
     * @return Files hashMap
     */
    public HashMap<String, File> getFiles() {
    	return files;
    }
    
    /**
     * Gets the hashMap of fileNames and keys and the ID of when the file was last modified as values.
     * @return LastModified hashMap
     */
    public HashMap<String, Integer> getLastModified(){
        return lastModified;
    }
    
    /**
     * Gets the parent of the Commit
     * @return parent commit
     */
    public Commit getParent() {
        return parent;
    }
    
    /**
     * Resets the parent of the commit
     * @param p
     * 		The new parent of the commit
     */
    public void setParent(Commit p) {
        parent = p;
    }
    
    /**
     * Gets the List of files associated with the Commit
     * @return list of files
     */
    public ArrayList<File> getFilesList() {
        return filesList;
    }
    
    /**
     * Gets the ID associated with the commit
     * @return ID
     */
    public int getID() {
        return id;
    }
    
    /**
     * Gets the comment associated with the COmmit
     * @return comment
     */
    public String getComment() {
        return comment;
    }
    
    /**
     * Converts the commit into a String that can be used in log
     * @return string in log format
     */

    public String toString() {
        return "===\nCommit " + id + "\n"+timeCreated+"\n" + comment;
    }
    
    /**
     * Checks if the Commit id is equal to the ID of the commit being passed in
     * @param c
     * 		The commit being compared the the current commit
     * @return true if the IDs of the commits are equal
     */
    public boolean equals(Commit c) {
    	if (id == c.getID()) {
    		return true;
    	}
    	return false;
    }
    
}