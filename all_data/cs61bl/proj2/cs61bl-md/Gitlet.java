import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Gitlet implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String myHead;
    private int myCount;
    private boolean conflictedstate = false;
    private HashMap<String, File> myUntracked;
    private HashMap<String, LinkedList<Commit>> myCommitsMessage;
    private HashMap<Integer, Commit> myCommitsID;
    private HashMap<String, Commit> myBranches;
    private HashMap<String, File> myStagingArea;
        
    /**
     * Initializes Gitlet. Creates the .gitlet folder and the initial
     * commit. 
     */

    public Gitlet() {
        myCount = 0;
        myUntracked = new HashMap<String, File>();
        myStagingArea = new HashMap<String, File>();
        myBranches = new HashMap<String, Commit>();
        myCommitsID = new HashMap<Integer, Commit>();
        myCommitsMessage = new HashMap<String, LinkedList<Commit>>();
        myHead = "master";
        this.init();
    }
    
    /** 
    *Initializes the Gitlet file systems. Creates the .gitlet folder and the folder for the 
    *initial Commit.
    */
    public void init() {
        File gitletfolder = new File("./.gitlet");
        gitletfolder.mkdir();
        File dir1 = new File("./.gitlet/0");
        dir1.mkdir();
        this.addCommit(new Commit(this, myCount++, dir1.getPath()));
        File dir2 = new File("./.gitlet/StagingArea");
        dir2.mkdir();
    }
    
    /**
    *Adds a Commit to both of the Commit HashMaps
    *
    *@param c the Commit to be added
    */
    public void addCommit(Commit c) {
        myBranches.put(myHead, c);
        if (myCommitsMessage.containsKey(c.getMessage())) {
            myCommitsMessage.get(c.getMessage()).add(c);
        } else { 
            LinkedList<Commit> l = new LinkedList<Commit>();
            l.add(c);
            myCommitsMessage.put(c.getMessage(), l);
        }
        myCommitsID.put(c.getID(), c);
    }
    
    /**
     * Creates a new Commit with the user-specified message
     * 
     * @throws IllegalArgumentException if there are no changes since the last
     * commit
     * @param message the message associated with the new Commit
     */
    public void commit(String message) {
        if (myStagingArea.isEmpty() && myUntracked.isEmpty()) {
            throw new IllegalArgumentException("No changes added to the commit.");
        } else {
            File dir = new File("./.gitlet/" + myCount);
            dir.mkdir();
            this.addCommit(new Commit(this, myCount++, dir.getPath(), message, myBranches.get(myHead)));
            conflictedstate = false;
            myUntracked.clear();
        }
    }
    
    /**
     * Adds the file to the staging area.
     * 
     * @param filename the name of the file to add
     * @throws IOException from Files.copy()
     */
    public void add(String filename) throws IOException {
        try {
            if (this.myUntracked.containsKey(filename)){
                this.myUntracked.remove(filename);
            } else{
                File newfile = new File("./.gitlet/StagingArea/" + filename);
                this.myStagingArea.put(filename, newfile);
                Path source = Paths.get("./" + filename);
                Path out = newfile.toPath();
                newfile.getParentFile().mkdirs();
                Files.copy(source, out);
            }
        } catch(NoSuchFileException e) {
            throw new IllegalArgumentException("File does not exist.");
        }
    }
    
    /**
     * Marks a file for untracking, OR removes it from the staging area.
     * 
     * @param filename
     * @throws IOException from Files.delete()
     */
    public void rm(String filename) throws IOException {
        //if not in staging area, unt//if not in staging area, untrack the file of myHead
        if (myStagingArea.get(filename) == null) {
            if (myBranches.get(myHead).getFile(filename) == null) {
                System.err.println("No reason to remove the file.");
            } else {
                File f = myBranches.get(myHead).getFile(filename);
                myUntracked.put(filename, f);
            }
        } else {
            Path temp = myStagingArea.get(filename).toPath();
            myStagingArea.remove(filename);
            Files.delete(temp);
        }
    }
    
    /**
     * Checkouts the given file from the Commit with the given id.
     * 
     * @param id the id of the commit
     * @param fileName the name of the file to be checked out
     * @throws IOException from Commit.checkout()
     */
    public void checkout(String id, String fileName) throws IOException {
        Integer ID = Integer.valueOf(Integer.parseInt(id));
        Commit temp = myCommitsID.get(ID);
        if (temp == null) {
            throw new IllegalArgumentException("No commit with that id exists.");
        }
        if (temp.getFile(fileName) == null) {
            throw new IllegalArgumentException("File does not exist in that commit.");
        }
        temp.checkout(fileName);
    }
    
    /**
     * Checks out all files from the branch with the given name, OR checks out the file with the 
     * given name from the current commit. The branch name takes precedence. Also changes the myHead 
     * pointer if the user checks out a branch name.
     * 
     * @param name either the branch name or the filename
     * @throws IOException
     */
    public void checkout(String name) throws IOException {
        if (myBranches.containsKey(name)) {
            conflictedcheck();
            if (name.equals(myHead)) {
                throw new IllegalArgumentException("No need to checkout the current branch.");
            }
            myBranches.get(name).checkoutAll();
            myHead = name;
        } else if (myBranches.get(myHead).getFile(name) == null) {
            throw new IllegalArgumentException("File does not exist in the most recent commit, or no such branch exists");
        } else {
            myBranches.get(myHead).checkout(name);
        }
    }

    /**
     * Prints out the information of all the commits, including the commits of other branches.
     */
public void globalLog() {
        Iterator<Map.Entry<Integer, Commit>> iter = myCommitsID.entrySet().iterator();
        Map.Entry<Integer, Commit> pair;
        while (iter.hasNext()) {
            pair = (Map.Entry<Integer, Commit>) iter.next();
            System.out.print(pair.getValue().toString());
        }
    }
    
    
    /**
     * Returns string to be printed out when status command is input.
     * The String contains information of :
     * all the branches' name and indicates the current branch,
     * the files in staging area, and currently untracked files.
     * @return String to be printed
     */
    public String status(){
        String rtn = "";
        rtn += "=== Branches ===\n";
        for (String key : myBranches.keySet()){
            if (key.equals(myHead)){
                key = "*" + key;
            }
            rtn += key + "\n";
        }
        rtn += "\n";
        rtn += "=== Staged Files ===\n";
        for (String key : myStagingArea.keySet()){
            rtn += key + "\n";
        }
        rtn += "\n";
        rtn += "=== Files Marked for Untracking ===\n";
        for (String key :myUntracked.keySet()){
            rtn += key + "\n";
        }
        return rtn;
    }

    /**
     * prints out the id of commit with a given message.
     * @param message
     */
    public void find(String message) {
        LinkedList<Commit> ls = myCommitsMessage.get(message);
        if (ls == null) {
            throw new IllegalArgumentException("Found no commit with that message.");
        }
        for (Commit c : ls) {
            System.out.println(c.getID());
        }
    }
    

    /**
     * Creates new branch with given branchname which points to current commit.
     * @param branchname
     */
    public void branch(String branchname){
        myBranches.put(branchname, myBranches.get(myHead));

    }
    
    /**
     * Removes the branch with branchname.
     * Called only when the removal of the branch with branchname is possible.
     * @param branchname
     */
    public void removeBranch(String branchname){
        myBranches.remove(branchname);
    }

    /**
     * Checks out all the files tracked by the given commit. 
     * Also moves the current branch's head to that commit node. 
     * @param commitID
     * @throws IOException
     */
    public void reset(int commitID) throws IOException{
        Commit temp = myCommitsID.get(commitID);
        myBranches.put(myHead, temp);
        temp.checkoutAll();
    }
    

    /**
     *Merges files from the given branch into the current branch
     *
     *1)Checkout all files from head of given branch that have been modified 
     *after the split point of the given branch, but not modified in current branch
     *
     *2)Stage all of these modified files
     *
     *3)Files modified in current branch but not given branch should be unchanged
     *
     *4)Files modified in both current and given branch, should stay same as current branch
     *but the version of file of given branch should be copied into working directory with [oldfilename].conflicted
     *
     *5)If (!conflictedfiles) merge should auto commit with 
     *    msg "Merged [currentbranchname] with [givenbranchname]."
     *
     *6)If (conflictedfiles) print msg "Encountered a merge conflict."
     *     put gitlet into a conflicted state. where some commands are not allowed.
     *     COMMANDS ALLOWED: add, rm, commit, checkout [file], checkout [id][file]
     *                  log, global-log, find, status
     */

    public void merge(String mergbranch) throws IOException{
        HashMap<String, File> ModifiedOnlyInGiven = new HashMap<String, File>(); 
        HashMap<String, File> ModifiedInGivenAndCurrent = new HashMap<String, File>(); 
        Commit splitpoint = findsplit(mergbranch);
        Commit current = myBranches.get(myHead);
        Commit given = myBranches.get(mergbranch);
        for (String key : given.getFile().keySet()) {
            if (splitpoint.getFile(key) == null) {
                if (current.getFile(key) == null) {
                    ModifiedOnlyInGiven.put(key, given.getFile(key));
                }
                else if (current.getFile(key) != null
                		&& !sameContent(current.getFile(key).toPath(), given.getFile(key).toPath())) {
                	ModifiedInGivenAndCurrent.put(key, given.getFile(key));
                }
                else {
                    ModifiedInGivenAndCurrent.put(key, given.getFile(key));
                }
            }
        }
        for (String key : splitpoint.getFile().keySet()){
            if (given.getFile(key)!=null && 
                    !sameContent(given.getFile(key).toPath(), splitpoint.getFile(key).toPath())) {
                if (current.getFile(key)!=null &&
                        sameContent(current.getFile(key).toPath(), splitpoint.getFile(key).toPath())) {
                    ModifiedOnlyInGiven.put(key, given.getFile(key));
                }
                else {
                    if (current.getFile(key) == null) 
                        ModifiedOnlyInGiven.put(key, given.getFile(key));
                    else ModifiedInGivenAndCurrent.put(key, given.getFile(key));
                }    
            }            
        }
        for (String filename : ModifiedOnlyInGiven.keySet()){
            given.checkout(filename);
            add(filename);
        }
        for(String filename : ModifiedInGivenAndCurrent.keySet()){
            File newfile = new File("./" + filename + ".conflicted");
            Path source = given.getFile(filename).toPath();
            Path out = newfile.toPath();
            newfile.getParentFile().mkdirs();
            Files.copy(source, out);
            }
        if (ModifiedInGivenAndCurrent.isEmpty()){
        	if (ModifiedOnlyInGiven.isEmpty()) {
        		System.out.println("only current");
        		return;
        	}
            commit("Merged " + mergbranch + " with " + myHead + ".");
        }
        else{
            System.out.println("Encountered a merge conflict.");
            conflictedstate = true;
        }
    }
    /**
     * Checks whether two files have the same contents
     * 
     * @param file1 the first file to be checked
     * @param file2 the second file to be checked
     * @return true if the same, false if different
     * @throws IOException from InputStrem
     */
    //Taken From: http://stackoverflow.com/questions/27379059/determine-if-two-files-store-the-same-content
    public boolean sameContent(Path file1, Path file2) throws IOException {
            final long size = Files.size(file1);
            if (size != Files.size(file2))
                 return false;
            if (size < 4096)
                    return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));
            try (InputStream is1 = Files.newInputStream(file1);
                     InputStream is2 = Files.newInputStream(file2)) {
                  int data;
                  while ((data = is1.read()) != -1)
                           if (data != is2.read())return false;
            }
            return true;
    }

    /**
     * Finds splitpoint with branch named mergbranch.
     * Called only when the merge is possible.
     * @param mergbranch
     * @return Commit splitpoint
     */
    private Commit findsplit(String mergbranch) {
        Commit mcurrent = myBranches.get(mergbranch);
        Commit hcurrent = myBranches.get(myHead);
        int mergbranchID = myBranches.get(mergbranch).getID();
        int headbranchID = myBranches.get(myHead).getID();
        while (mergbranchID != headbranchID) {
            if (mergbranchID > headbranchID) {
                mergbranchID = mcurrent.getParent().getID();
                mcurrent = mcurrent.getParent();
            } else {
                headbranchID = hcurrent.getParent().getID();
                hcurrent = hcurrent.getParent();
            }
        }
        return hcurrent;
    }
    
    /**
     * Makes a copy of all the commits between the split point and the current branch. Accounts for changes that occur in the branch you want to rebase to as well. Uses recursion to start building from the split point and returns a Commit, ending with a copy of the updated �쐌yHead.��
     * Called in rebase when making copies to be appended to the given branch.
     * @param Commit splitPoint,Commit current, Commit branchname
     * @return Commit newCommit
     */
    public Commit copyCommits(Commit splitPoint, Commit current, String branchname) throws IOException{
        if(current.getParent()==splitPoint){
            File dir = new File("./.gitlet/" + myCount);
            dir.mkdir();
            Commit newCommit = new Commit(this, current,myCount++);
            for (String key : splitPoint.getFile().keySet()){
                if (current.getFile(key)!=null &&
                        sameContent(current.getFile(key).toPath(), splitPoint.getFile(key).toPath())) {
                        if (!sameContent(myBranches.get(branchname).getFile(key).toPath(), splitPoint.getFile(key).toPath())){
                            newCommit.getFile().put(key,myBranches.get(branchname).getFile(key));
                        }
                } 
            }
            this.addCommit(newCommit);
            newCommit.setParent(myBranches.get(branchname));
            return newCommit;
        } else {
            Commit parent = copyCommits(splitPoint,current.getParent(),branchname);
            File dir2 = new File("./.gitlet/" + myCount);
            dir2.mkdir();
            Commit newCommit2 = new Commit(this, current,myCount++);
            for (String key : splitPoint.getFile().keySet()){
                if (current.getFile(key)!=null &&
                        sameContent(current.getFile(key).toPath(), splitPoint.getFile(key).toPath())) {
                        if (!sameContent(myBranches.get(branchname).getFile(key).toPath(), splitPoint.getFile(key).toPath())){
                            newCommit2.getFile().put(key,myBranches.get(branchname).getFile(key));
                        }
                } 
            }
            newCommit2.setParent(parent);
            this.addCommit(newCommit2);
            return newCommit2;
        }
    }
    
    /**
     * 1) Snaps off the current branch at this point, 
     * then reattaches the current branch to the head of the given branch.
     * 
     * 2) If the commit at the front of the given branch 
     * has files that have been modified since the split point, 
     * these these changes should propagate through the replay.
     * 
     * 3) After successfully replaying nodes, reset to the node at the 
     * front of the replayed branch.
     * @param branchname
     */
    public void rebase(String branchname) throws IOException{
        Commit splitPoint = findsplit(branchname);
        if (splitPoint == myBranches.get(myHead)) {
            myBranches.put(myHead, myBranches.get(branchname));
        } else if (splitPoint == myBranches.get(branchname)){
            throw new IllegalArgumentException("Already up-to-date.");
        } else {
            Commit commitCopy = copyCommits(splitPoint, myBranches.get(myHead), branchname);
            myBranches.put(myHead,commitCopy);
        }
    }

    
    /**
     * Serializes Gitlet.
     */
    public void writeToFile() {
        try {
            FileOutputStream f = new FileOutputStream("./.gitlet/gitlet.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            f.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deserializes Gitlet.
     * 
     * @return Gitlet
     */
    public static Gitlet readFromFile() throws IOException, ClassNotFoundException {
        try {
            FileInputStream f = new FileInputStream("./.gitlet/gitlet.ser");
            ObjectInputStream out = new ObjectInputStream(f);
            Gitlet temp = (Gitlet) out.readObject();
            f.close();
            out.close();
            return temp;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
    
    /**
     * Used to access the staging area
     * 
     * @return the HashMap containing the file in the 
     * staging area
     */
    public HashMap<String, File> getStagingArea() {
        return myStagingArea;
    }
    
    /**
     * Getter method for the HashMap of files to be untracked
     * 
     * @return myUntracked
     */
    public HashMap<String, File> getUntracked() {
        return myUntracked;
    }
	
	    /**
	     * Checks if Gitlet is in conflicted state.
	     * @return if it�셲 conflicted state
	     */
	public void conflictedcheck(){
	        if (conflictedstate) {
	            throw new IllegalArgumentException("Cannot do this command until the merge conflict has been resolved");
	        }
	    }
	    
	/**
	 * Called when the user enters one argument
	 * 
	 * @param arg the argument
	 */
	public void command(String arg) {
		if (arg.equals("log")) {
			System.out.println(myBranches.get(myHead).log());
		} else if (arg.equals("global-log")) {
			globalLog();
		} else if (arg.equals("status")) {
			System.out.println(status());
		} else if (arg.equals("commit")) {
			throw new IllegalArgumentException("Please enter a commit message.");
		} else {
			noCommand();
		}
	}
	
	/**
	 * Called when the user passes in two arguments
	 * 
	 * @param arg1 argument 1
	 * @param arg2 argument 2
	 * @throws IOException
	 */
	public void command(String arg1, String arg2) throws IOException {
		if (arg1.equals("add")) {
			add(arg2);
		} else if (arg1.equals("commit")) {
			commit(arg2);
		} else if (arg1.equals("rm")) {
			rm(arg2);
		} else if (arg1.equals("find")) {
			find(arg2);
		} else if (arg1.equals("checkout")) {
			checkout(arg2);
		} else if (arg1.equals("branch")) {
			conflictedcheck();
			branch(arg2);
		} else if (arg1.equals("rm-branch")) {
			conflictedcheck();
			removeBranch(arg2);
		} else if (arg1.equals("reset")) {
			conflictedcheck();
			reset(Integer.parseInt(arg2));
		} else if (arg1.equals("merge")) {
			conflictedcheck();
			merge(arg2);
		} else if (arg1.equals("rebase")) {
			conflictedcheck();
			rebase(arg2);
		} else {
			noCommand();
		}
	}
	
	/**
	 * Called when the user passes in three arguments
	 * 
	 * @param arg1 argument 1
	 * @param arg2 argument 2
	 * @param arg3 argument 3
	 * @throws IOException
	 */
	public void command(String arg1, String arg2, String arg3) throws IOException {
		if (arg1.equals("checkout")) {
			checkout(arg2, arg3);
		} else {
			noCommand();
		}
	}
	
	/**
	 * Used to throw an exception if the command name does not exist
	 */
	public void noCommand() {
		throw new IllegalArgumentException("No command with that name exists.");
	}
	
	/**
	 * Gets input commands from keyboard and calls corresponding methods.
	 * @param args
		 */
		public static void main(String[] args) {
			try {
				Gitlet gitlet = readFromFile();
				if (args.length == 0) {
					throw new IllegalArgumentException("Please enter a command.");
				} else if (args[0].equals("init")) {
					if (gitlet == null)  {
						gitlet = new Gitlet();
					} else {
						throw new IllegalArgumentException("A gitlet version control system already exists in the current directory.");
					}
				} else if (args.length == 1) {
					gitlet.command(args[0]);
				} else if (args.length == 2) {
					gitlet.command(args[0], args[1]);
				} else {
					gitlet.command(args[0], args[1], args[2]);
				}
				gitlet.writeToFile();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}


    
}


