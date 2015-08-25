import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Commit implements Serializable {
    private Commit myParent;
    private int myID;
    private String myMessage;
    private String myTime;
    private File myFolder;
    private String myDirectory;
    private HashMap<String, File> myTracked;
    private HashMap<String, File> untracked;
    private Gitlet myGit;
    private Branch myBranch;
    
    /**
     * Commit constructor. This creates all the necessary things that
     * a commit will need.
     * @param parent
     *        This commit's parent commit.
     * @param id
     * 		  This commit's unique commit ID.
     * @param message
     *        This commit's user inputed message.
     * @param git
     *        This commit's associated Gitlet instance.
     */
    public Commit(Commit parent, int id, String message, Gitlet git) {
        myParent = parent;
        myID = id;
        myMessage = message;
        myGit = git;
        if (git.getCommitsByMessage().containsKey(myMessage)) {
            LinkedList<Integer> list = git.getCommitsByMessage().get(myMessage);
            list.addFirst(myID);
        } else {
            git.getCommitsByMessage().put(myMessage, new LinkedList<Integer>());
            git.getCommitsByMessage().get(myMessage).addFirst(myID);
        }
        myGit.returnMyCommits().put(id, this);//put the commits in myCommits HashMap by IDs.
        myBranch = myGit.getCurrentBranch();
        myBranch.setLeaf(this);
        myFolder = new File(myGit.getcommitsDir() + id);
        myFolder.mkdir();
        myDirectory = myFolder.getAbsolutePath() + "/";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        myTime = dateFormat.format(date);
        myTracked= new HashMap<String, File>();
        untracked = new HashMap<String, File>();
        if (myParent != null) { //TODO
            HashMap<String, File> parentTracked = myParent.getMyTracked();
            HashMap<String, File> parentUnTracked = myParent.getUntracked();
            myTracked.putAll(parentTracked);
            Set<String> unTrackedSet = parentUnTracked.keySet();
            Iterator<String> iter = unTrackedSet.iterator();
            while (iter.hasNext()){
                myTracked.remove(iter.next());
            }
            myParent.untracked.clear();
            FileTools.commitCopyTool(myGit.getMyStage(), myFolder, "", myTracked); //TODO
    		myGit.getMyStages().clear();//ADDED for STATUS
        }
    }
    
       public Commit(Commit toCopy, Commit split, Commit given, int id, Gitlet git){
    	myID = id;
    	myGit = git;
    	myMessage = toCopy.getMyMessage();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        myTime = dateFormat.format(date);
        myTracked = new HashMap<String, File>();
        untracked = new HashMap<String, File>();
        myFolder = new File(myGit.getcommitsDir() + id);
        myFolder.mkdir();
        myDirectory = myFolder.getAbsolutePath() + "/";
        myGit.returnMyCommits().put(id, this);
        myBranch = myGit.getCurrentBranch();
    	HashMap<String, File> copyHash = toCopy.getMyTracked();
    	HashMap<String, File> copySplit = split.getMyTracked();
    	HashMap<String, File> givenHash = given.getMyTracked();
    	Set<String> givenSet = givenHash.keySet();
    	Set<String> copySet = copyHash.keySet();
    	Set<String> splitSet = copySplit.keySet();
    	Iterator<String> splitIter = splitSet.iterator();
    	String f = null;
    	while (splitIter.hasNext()){
    		f = splitIter.next();
    		if (! copyHash.containsKey(f)){
    			//do nothing
    		} else if (! FileTools.compareFiles(copyHash.get(f), copySplit.get(f))){
    			myTracked.put(f, copyHash.get(f)); 
    		} else {
    			if (! givenHash.containsKey(f)){
    				//do nothing
    			} else if (! FileTools.compareFiles(copySplit.get(f), givenHash.get(f))){
    				myTracked.put(f, givenHash.get(f));
    			} else {
    				myTracked.put(f, copySplit.get(f));
    			}
    		}
    	}
    	Iterator<String> currIter = copySet.iterator();
    	while(currIter.hasNext()){
    		f=currIter.next();
    		if (! copySplit.containsKey(f)){
    			myTracked.put(f, copyHash.get(f));
    		}
    	}
    	Iterator<String> givenIter = givenSet.iterator();
    	while(givenIter.hasNext()){
    		f = givenIter.next();
    		if ((! copyHash.containsKey(f))&&(! copySplit.containsKey(f))){
    			myTracked.put(f, givenHash.get(f));
    		}
    	}
    }
    
    /**
     * Helper method for log and global log.
     * Basically prints the commit's information.
     */
    public void print(){
    	 System.out.print("===\nCommit " + this.getMyID() + "\n" 
    	 + this.getMyTime() + "\n" + this.getMyMessage() + "\n");   
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// GET METHODS ///////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Gets the commit's parent commit
     * @return
     *        Commit instance
     */
    public Commit getmyParent(){
        return this.myParent;
    }
    
    public void setmyParent(Commit parent){
        myParent = parent;
    }
    
    /**
     * Gets the commit's unique ID
     * @return
     *        int ID
     */
    public int getMyID(){
        return this.myID;
    }
    
    /**
     * Gets the commit's message
     * @return
     *        String message
     */
    public String getMyMessage() {
        return this.myMessage;
    }
    
    /**
     * Gets the commit's creation time
     * @return
     *       String time
     */
    public String getMyTime() {
        return this.myTime;
    }
    
    /**
     * Gets the commit's HashMap of tracked files
     * @return
     * 		  HashMap of tracked files
     */
    public HashMap<String, File> getMyTracked() {
        return this.myTracked;
    }
    
    /**
     * Gets the commit's HashMap of untracked files
     * @return
     *        HashMap of untracked files
     */
    public HashMap<String, File> getUntracked() {
        return this.untracked;
    }
    
    /**
     * Gets the commit's associated Gitlet instance
     * @return
     *        Gitlet instance
     */
    public Gitlet getMyGit() {
        return this.myGit;
    }
    
    /**
     * Gets the commit's associated folder in .gitlet
     * @return
     *        Folder represented by a File instance
     */
    public File myFolder() {
        return this.myFolder;
    }
    
    /**
     * Gets the commit's branch
     * @return
     *        Branch instance
     */
    public Branch getMyBranch(){
    	return myBranch;
    }
    
    /**
     * Gets the commit's folder in a string
     * @return
     *        String of the absolute path of the commit's folder
     */
    public String getmyDirectory() {
    	return myDirectory;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * This commit's hashcode.
     * @return
     *       int hashcode
     */
    public int HashCode() {
        return this.myID;
    }
}

