import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
 
public class Commit implements java.io.Serializable {
 
    private static final long serialVersionUID = 987654321;
    private int Id;
    private int parentId;
    private Date timeMade;
    private String madeMessage;
    private HashMap<String, String> files;
    private HashMap<String, String> newFiles;
    private String path;
 
    /**
     * Constructor for Commit Object
     * 
     * @param ID
     *            Id number to be associated with this commit.
     * @param message
     *            Commit message to be associated with this commit.
     * @param Parent
     *            Id of parent commit.
     */
    public Commit(int ID, String message, int Parent) {
        Id = ID;
        madeMessage = message;
        timeMade = new Date();
        files = new HashMap<String, String>();
        newFiles = new HashMap<String, String>();
        parentId = Parent;
        path = "";
    }
 
    /**
     * Reserializes this Commit object in its proper place, replacing the
     * previous serialization of the object if needed.
     */
    public void saveCommit() {
        try {
            OutputStream toSave = new FileOutputStream(path);
            ObjectOutputStream obj_out = new ObjectOutputStream(toSave);
            obj_out.writeObject(this);
            obj_out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
 
    /**
     * Public getter method for private madeMessage variable.
     * 
     * @return This Commit's message String.
     */
    public String madeMessage() {
        return madeMessage;
    }
 
    /**
     * Public getter method for private files variable.
     * 
     * @return This Commit's files HashMap.
     */
    public HashMap<String, String> files() {
        return files;
    }
     
    /**
     * Public getter method for private newFiles variable.
     * 
     * @return This Commit's newFiles HashMap.
     */
    public HashMap<String, String> newFiles() {
        return newFiles;
    }
 
    /**
     * Public setter method for private path variable.
     * 
     * @param path
     *            The file path this commit is located at.
     */
    public void setPath(String path) {
        this.path = path;
    }
 
    /**
     * Method to add a file to the snapshot held by this commit.
     * 
     * @param fileName
     *            The relative path to the file from the original working
     *            directory.
     * @param path
     *            The path location of the actual file within the commits
     *            directory. If this file is newly added, it will be a path to
     *            this commit's folder. Else, it might point to a different
     *            commit's folder.
     */
    public void addFile(String fileName, String path) {
        files.put(fileName, path);
    }
     
    /**
     * Adds files that are new to this particular commit
     * 
     * @param fileName
     *            The relative path to the file from the original working
     *            directory.
     * @param path
     *            The path location of the actual file within the commits
     *            directory. It will be a path to this commit's folder.
     *       
     */
    public void addNewFile(String fileName, String path) {
        newFiles.put(fileName, path);
    }
 
    /**
     * Public getter method for private parentId variable.
     * 
     * @return This Commit's parent's ID.
     */
    public int parentId() {
        return parentId;
    }
 
    /**
     * Print's this commit's log message.
     * 
     *  Date format source:
     * http://examples.javacodegeeks.com/core-java/text/java-dateformat-example/
     * 
     * @return This Commit's parent's ID.
     */
    public int log() {
        System.out.println("===");
        System.out.println("Commit " + Id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        System.out.println(format.format(timeMade));
        System.out.println(madeMessage);
        System.out.println();
        return parentId;
    }
 
}