import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Commit implements Serializable {
    
    private Gitlet myGitlet;
    private int myID;
    private String myMessage;
    private Commit myParent;
    private Timestamp myTime;
    private HashMap<String, File> myFiles;
    private String myDir;
    
    /**
     * Constructor for a Commit. Default to initial commit.
     * 
     * @param git the Gitlet for this commit
     * @param id the id of this Commit
     */
    public Commit(Gitlet git, int id, String dir) {
        myID = id;
        myMessage = "initial commit";
        myParent = null;
        myGitlet = git;
        myTime = new Timestamp(System.currentTimeMillis());
        myFiles = new HashMap<String, File>();
        myDir = dir;
    }
    
    /**
     * Constructor for a Commit. Calls the three argument constructor.
     * 
     * @param name the name of this Commit
     * @param parent the parent of this Commit
     * @param id the id of this Commit
     * @param git the Gitlet associated with this commit
     */
    public Commit(Gitlet git, int id, String dir, String message, Commit parent) {
        this(git, id, dir);
        myMessage = message;
        myParent = parent;
        myID = id;
        this.initialize();
    }
    
    /**
     * Constructor for a commit that copies the files from another commit.
     * Used for rebase.
     * 
     * @param git this commit's Gitlet
     * @param toCopy the commit to be copied
     * @param ID the ID of this commit
     */
    public Commit(Gitlet git, Commit toCopy,int ID){
        this(git, ID, "./.gitlet" + ID, toCopy.getMessage(), null);
        for (String filename : toCopy.myFiles.keySet()){
            myFiles.put(filename, toCopy.myFiles.get(filename));
            
        }
    }
    
    /**
     * Populates the HashMap of files that this commit holds. Also 
     * clears the staging area.
     */
    public void initialize() {
        try {
            HashMap<String, File> staging = myGitlet.getStagingArea();
            HashMap<String, File> untracked = myGitlet.getUntracked();
            HashMap<String, File> parent = new HashMap<String, File>();
            if (myParent != null){
                parent = myParent.myFiles;
            }
            for (String path : staging.keySet()) {
                File f = new File(myDir + "/" + path);
                f.getParentFile().mkdirs();
                myFiles.put(path, f);
                Path source = Paths.get("./.gitlet/StagingArea/" + path);
                Path out = Paths.get(myDir + "/" + path);
                Files.copy(source, out);
                Files.delete(source);
            }
            if (myParent!=null && !parent.isEmpty()){
                for (String path : parent.keySet()) {
                    if (!myFiles.containsKey(path) && !untracked.containsKey(path)) {
                        myFiles.put(path, parent.get(path));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        myGitlet.getStagingArea().clear();
    }
    
    public void setParent(Commit parent){
        myParent = parent;
    }
    /**
     * Checks out a file into the working directory
     * 
     * @param fileName the name of the file to be checked out
     * @throws IOException from Files.delete()
     */
    public void checkout(String fileName) throws IOException {
        File f = new File("./" + fileName);
        Path source = myFiles.get(fileName).toPath();
        Path out = Paths.get("./" + fileName);
        if (Files.exists(out)) {
            Files.delete(out);
        }
        f.getParentFile().mkdirs();
        Files.copy(source, out);
    }
    
    /**
     * Checks out all the files in this Commit's myFiles 
     * 
     * @throws IOException from Commit.checkout
     */
    public void checkoutAll() throws IOException {
        for (String file : myFiles.keySet()) {
            checkout(file);
        }
    }

    /**
     * ID getter method
     * 
     * @return the ID of the commit
     */
    public Integer getID() {
        return Integer.valueOf(myID);
    }
/**
 * Message getter method
 * 
 * @return this commit's message
 */
    public String getMessage() {
        return myMessage;
    }
    
    /**
     * Directory getter method
     * 
     * @return the directory of this commit
     */
    public String getDir() {
        return myDir;
    }
    
    /**
     * Prints the date, message, and ID of this commit for log and global-log
     */
    public String toString() {
        return "===\n" + "Commit " + myID + "\n" + 
                myTime.toString().substring(0, 19) + "\n" + myMessage + "\n" + "\n";
    }
    
    /**
     * Returns the toString of this commit and this commit's ancestors
     * 
     * @return the string representation of the history
     */
    public String log() {
        if (myParent == null) return toString();
        return toString() + myParent.log();
    }
    
    /**
     * Getter method for a file
     * 
     * @param filename the file to be gotten
     * @return the file associated with that filename
     */
    public File getFile(String filename) {
        return myFiles.get(filename);
    }
    
    /**
     * Getter method for the files in this commit
     * 
     * @return this commit's map of files
     */
    public HashMap<String, File> getFile() {
        return myFiles;
    }
    
    /**
     * Getter method for the parent of a commit
     * 
     * @return the parent of this Commit
     */
    public Commit getParent() {
        return myParent;
    }
    
}






