import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.text.*;
import static java.nio.file.StandardCopyOption.*;

public class Gitlet implements Serializable {
    Branch currentBranchHead = null;
    ArrayList<Branch> branchHeadsArr = new ArrayList<Branch>();
    HashMap<String, Branch> branchHeads = new HashMap<String, Branch>();
    HashMap<String, ArrayList<Backup>> messageDict = new HashMap<String, ArrayList<Backup>>();
    HashMap<Integer, Backup> idDict = new HashMap<Integer, Backup>();
    boolean inConflictedState = false;
    boolean hasInit = false;
    public static final String MAIN_PATH = ".gitlet/";
    public static final String STAGING_PATH = ".gitlet/staging/";

    ArrayList<File> untrackedFiles = new ArrayList<File>();
    private int hashCodeCounter = 0;

    /**
     * Resumes program from previous state, executes given command, exits
     * program.
     * 
     * @param args
     *            Possible list of commands for Gitlet to execute.
     */
    public static void main(String[] args) {
        Gitlet current;
        try {
            current = initialize(new FileInputStream(".gitlet/previous.ser"));
        } catch (FileNotFoundException e) {
            // Hasn't been initialized yet.
            current = new Gitlet();
        }
        try {
            current.parseCommands(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                current.saveState(new FileOutputStream(".gitlet/previous.ser"));
            } catch (Exception e) {

            }
        }
    }

    /**
     * Resumes program from a saved state. Expects the state to be found at
     * .gitlet/savedState relative to the current directory. If the state file
     * isn't found, it returns a brand new Gitlet object.
     * 
     * @param i
     *            a FileInputStream that reads in Files to be placed in an
     *            ObjectInputStream
     */
    private static Gitlet initialize(FileInputStream i) {
        try {
            ObjectInputStream in = new ObjectInputStream(i);
            Gitlet toReturn = (Gitlet) in.readObject();
            in.close();
            i.close();
            return toReturn;
        } catch (FileNotFoundException e) {
            return new Gitlet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Saves the current program state so it can be reloaded in future runs.
     * 
     * @param o
     *            a FileOutputStream that is used to place Files into an
     *            ObjectOutputStream.
     */
    private void saveState(FileOutputStream o) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(o);
            out.writeObject(this);
            out.close();
            o.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Goes through the list of String tokens. Attempts to process them and run
     * corresponding commands.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when an invalid sequence of tokens is given.
     */
    private void parseCommands(String[] commands) throws IllegalCommandException {
        if (commands.length == 0) {
            throw new IllegalCommandException("Please enter a command.");
        }
        switch (commands[0]) {
            case ("init"):
            parseInit(commands);
            break;
            case ("add"):
            parseAdd(commands);
            break;
            case ("commit"):
            parseCommit(commands);
            break;
            case ("rm"):
            parseRemove(commands);
            break;
            case ("log"):
            parseLog(commands);
            break;
            case ("global-log"):
            parseGlobalLog(commands);
            break;
            case ("find"):
            parseFind(commands);
            break;
            case ("status"):
            parseStatus(commands);
            break;
            case ("checkout"):
            parseCheckout(commands);
            break;
            case ("branch"):
            parseBranch(commands);
            break;
            case ("rm-branch"):
            parseRemoveBranch(commands);
            break;
            case ("reset"):
            parseReset(commands);
            break;
            case ("merge"):
            parseMerge(commands);
            break;
            case ("rebase"):
            parseRebase(commands);
            break;
            default:
            throw new IllegalCommandException("No command with that name exists.");
        }
    }

    /**
     * Parses the command tokens to run the init method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when an version of gitlet already exists in the current
     *             directory.
     */
    public void parseInit(String[] commands) throws IllegalCommandException {
        if (hasInit) {
            throw new IllegalCommandException(
                "A gitlet version control system already exists in the current directory.");
        }
        init();
    }

    /**
     * Parses the command tokens to run the add method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when a
     *             file name has not been specified.
     */
    public void parseAdd(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please enter a file name.");
        }
        add(commands[1]);
    }

    /**
     * Parses the command tokens to run the commit method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when a
     *             commit message has not been specified.
     */
    public void parseCommit(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please enter a commit message.");
        }
        commit(commands[1]);
    }

    /**
     * Parses the command tokens to run the rm (remove) method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when a
     *             file name has not been specified.
     */
    public void parseRemove(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please enter a file name.");
        }
        rm(commands[1]);
    }

    /**
     * Parses the command tokens to run the log method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet.
     */
    public void parseLog(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        log();
    }

    /**
     * Parses the command tokens to run the global-log (globalLog) method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet.
     */
    public void parseGlobalLog(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        globalLog();
    }

    /**
     * Parses the command tokens to run the find method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when a
     *             commit message has not been specified.
     */
    public void parseFind(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please provide a commit message.");
        }
        find(commands[1]);
    }

    /**
     * Parses the command tokens to run the status method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet.
     */
    public void parseStatus(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        status();
    }

    /**
     * Parses the command tokens to run the checkout (checkout1 and checkout2)
     * method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when the
     *             commands token does not include the proper names or numbers.
     *             when the checkout method is called on the current branch
     *             head. when a file is specified that does not exist within the
     *             most recent commit when a branch is specified that does not
     *             exist. when an ID is specified that corresponds to a commit
     *             that does not exist.
     */
    public void parseCheckout(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException(
                "Please provide a file name, commit ID number and file name, or branch name.");
        }
        if (commands.length < 3) {
            // A valid checkout [commitId][fileName] is impossible.
            try {
                // Branch checkout takes precedence
                if (commands[1].equals(currentBranchHead.name)) {
                    throw new IllegalCommandException("No need to checkout the current branch.");
                }
                checkout2(commands[1]);
            } catch (IllegalCommandException e) {
                throw e;
            } catch (Exception e) {
                // No Branch exists with the name.
                try {
                    checkout1(currentBranchHead.pointTo.hashCode(), commands[1]);
                } catch (Exception e2) {
                    throw new IllegalCommandException(
                        "File does not exist in the most recent commit, or no such branch exists.");
                }
            }
        } else {
            // Assume checkout [commitId][fileName] occurred.
            try {
                checkout1(Integer.parseInt(commands[1]), commands[2]);
            } catch (NumberFormatException e) {
                throw new IllegalCommandException("No commit with that id exists.");
            }
        }
    }

    /**
     * Parses the command tokens to run the branch method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when the
     *             merge method has been called and the system is in a
     *             conflicted state. when a branch name has not been specified.
     */
    public void parseBranch(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (inConflictedState) {
            throw new IllegalCommandException("Cannot do this command until the merge conflict has been resolved.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please provide a branch name.");
        }
        branch(commands[1]);
    }

    /**
     * Parses the command tokens to run the rm-branch (removeBranch) method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when the
     *             merge method has been called and the system is in a
     *             conflicted state. when a branch name has not been specified.
     */
    public void parseRemoveBranch(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (inConflictedState) {
            throw new IllegalCommandException("Cannot do this command until the merge conflict has been resolved.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please provide a branch name.");
        }
        removeBranch(commands[1]);
    }

    /**
     * Parses the command tokens to run the reset method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when the
     *             merge method has been called and the system is in a
     *             conflicted state. when a commit ID has not been specified.
     *             when an ID is specified that corresponds to a commit that
     *             does not exist.
     */
    public void parseReset(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (inConflictedState) {
            throw new IllegalCommandException("Cannot do this command until the merge conflict has been resolved.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please provide a commit id.");
        }
        try {
            reset(Integer.parseInt(commands[1]));
        } catch (NumberFormatException e) {
            throw new IllegalCommandException("No commit with that id exists.");
        }
    }

    /**
     * Parses the command tokens to run the merge method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when the
     *             merge method has been called and the system is in a
     *             conflicted state. when a branch name has not been specified.
     */
    public void parseMerge(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (inConflictedState) {
            throw new IllegalCommandException("Cannot do this command until the merge conflict has been resolved.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please provide a branch name.");
        }
        merge(commands[1]);
    }

    /**
     * Parses the command tokens to run the rebase method.
     * 
     * @param commands
     *            List of command tokens to process.
     * @throws IllegalCommandException
     *             when a gitlet repository has not been created yet. when the
     *             merge method has been called and the system is in a
     *             conflicted state. when a branch name has not been specified.
     */
    public void parseRebase(String[] commands) throws IllegalCommandException {
        if (!hasInit) {
            throw new IllegalCommandException("Not a gitlet repository.");
        }
        if (inConflictedState) {
            throw new IllegalCommandException("Cannot do this command until the merge conflict has been resolved.");
        }
        if (commands.length < 2) {
            throw new IllegalCommandException("Please provide a branch name.");
        }
        rebase(commands[1]);
    }

    /**
     * Returns all files in all directories in the given list of files.
     * 
     * @param files
     *            The list of files to explore.
     * @return A list of all files in all directories in the original file list.
     */
    private ArrayList<File> getAllFiles(File[] files) {
        ArrayList<File> toReturn = new ArrayList<File>(files.length);
        for (File f : files) {
            if (f.isDirectory()) {
                toReturn.addAll(getAllFiles(f.listFiles()));
            } else {
                toReturn.add(f);
            }
        }
        return toReturn;
    }

    /**
     * Given a list of pure files -- no directories -- returns a list of trimmed
     * file path strings, relative to the current directory, to be later
     * displayed to the user.
     * 
     * @param files
     *            The list of files whose paths are to be trimmed.
     * @returns String list of trimmed file path strings.
     */
    private ArrayList<String> getFileStrings(ArrayList<File> files) {
        ArrayList<String> toReturn = new ArrayList<String>(files.size());
        for (File f : files) {
            toReturn.add(f.getPath());
        }
        return toReturn;
    }

    /**
     * Given a File object in the root directory, returns the File object
     * resulting from moving that File to the Backup directory, given by b.
     * 
     * @param orig
     *            The original File object in the root directory.
     * @param b
     *            The backup object to whose folder the File object will be
     *            transferred.
     * @return The modified File object with the new Backup directory.
     */
    private File toBackup(File orig, Backup b) {
        return new File(MAIN_PATH + b.hashCode() + "/" + orig.getPath());
    }

    /**
     * Given a File object in the root directory, returns the File object
     * resulting from moving that File to the Backup directory with hash code n.
     * 
     * @param orig
     *            The original File object in the root directory.
     * @param n
     *            The hash code corresponding to the Backup object to whose
     *            folder the File object will be transferred.
     * @return The modified File object with the new Backup directory.
     */
    private File toBackup(File orig, int n) {
        return new File(MAIN_PATH + n + "/" + orig.getPath());
    }

    /**
     * Given a File object in the root directory, returns the File object
     * resulting from moving that File to the Staging directory.
     * 
     * @param orig
     *            The original File object in the root directory.
     * @return The modified File object with the new Staging directory.
     */
    private File toStaging(File orig) {
        return new File(STAGING_PATH + orig.getPath());
    }

    /**
     * Given a File object in the staging directory, returns the File object
     * resulting from moving that File into the root directory.
     * 
     * @param orig
     *            The original File object in the staging directory.
     * @return The modified File object in the root directory.
     */
    private File fromStaging(File orig) {
        return new File(orig.getPath().substring((STAGING_PATH).length(), orig.getPath().length()));
    }

    /**
     * Given a File object in a given Backup directory, returns the File object
     * resulting from moving that File into the root directory.
     * 
     * @param store
     *            The original File object in a Backup directory.
     * @param b
     *            The Backup object in whose directory the File object resides.
     * @return The modified File object in the root directory.
     */
    private File fromBackup(File store, Backup b) {
        return new File(store.getPath().substring((MAIN_PATH + b.hashCode() + "/").length(), store.getPath().length()));
    }

    /**
     * Given a File object in any Backup directory, returns the File object
     * resulting from moving that File into the root directory.
     * 
     * @param store
     *            The original File object in a Backup directory.
     * @return The modified File object in the root directory.
     */
    private File fromBackup(File store) {
        String toProcess = store.getPath();
        for (int i = 0; i < 2; i++) {
            if (toProcess.contains("/")) {
                toProcess = toProcess.substring(toProcess.indexOf("/") + 1);
            }
        }
        return new File(toProcess);
    }

    /**
     * Given a File in a Backup directory, returns the hashCode of the Backup
     * object in whose directory the File resides.
     * 
     * @param f
     *            The original File in a backup directory.
     * @return The hashCode of the Backup directory belonging to the
     *         corresponding Backup object.
     */
    public static int getBackupNumber(File f) {
        String p = f.getPath();
        String p2 = p.substring(MAIN_PATH.length());
        return Integer.parseInt(p2.substring(0, p2.indexOf("/")));
    }

    /**
     * Copies the source file to the destination file, adding necessary
     * directories. If the destination file already exists, it is overwritten.
     * 
     * @param source
     *            The source file to copy.
     * @param destination
     *            The destination to copy the file to.
     * @throws IOException
     *             on error copying files.
     */
    private void hardCopy(File source, File destination) {
        if (source.isDirectory() || !source.exists()) {
            throw new IllegalArgumentException("Can only copy when source is an existing file.");
        }
        String destPath = destination.getPath();
        ArrayList<File> requiredDirs = new ArrayList<File>();
        for (int i = 0; i < destPath.length(); i++) {
            if (destPath.charAt(i) == '/') {
                requiredDirs.add(new File(destPath.substring(0, i)));
            }
        }
        for (File f : requiredDirs) {
            if (!f.exists()) {
                f.mkdir();
            }
        }
        try {
            Files.copy(source.toPath(), destination.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Fatal Error occurred when copying files.");
        }
    }

    /**
     * Clears the given directory of all files.
     * 
     * @param dir
     *            The File object representing the directory to clear of all
     *            files.
     */
    private void hardClear(File dir) {
        File[] toDelete = dir.listFiles();
        for (File f : toDelete) {
            if (f.isDirectory()) {
                hardClear(f);
            }
            f.delete();
        }
    }

    /**
     * Adds a Backup object to the message dictionary, accessing its value entry
     * replacing it with a new ArrayList<Backup> if it hasn't been initialized,
     * or adding it to the ArrayList<Backup> object if it has. Automatically
     * handles adding to the ArrayList value.
     * 
     * @param s
     *            The message to reference the Backup.
     * @param b
     *            The Backup that the message goes with.
     */
    private void addToMessageDict(String s, Backup b) {
        ArrayList<Backup> result = messageDict.get(s);
        if (result == null) {
            result = new ArrayList<Backup>();
        }
        result.add(b);
        messageDict.put(s, result);
    }

    /**
     * Returns the split point between backups b1 and b2. The most recently
     * created node shared in their histories.
     * 
     * @param b1
     *            One backup in the collection.
     * @param b2
     *            Another backup in the collection.
     */
    private Backup splitPoint(Backup b1, Backup b2) {
        while (b1 != null && b2 != null) {
            if (b1 == b2) {
                return b1;
            }
            if (b1.hashCode() > b2.hashCode()) {
                b1 = b1.previous;
            } else {
                b2 = b2.previous;
            }
        }
        return b1;
    }

    /**
     * Returns the original list of files filtered and containing only files
     * belonging to a backup with a commit ID greater than the given number.
     * 
     * @param files
     *            The ArrayList<File> of Files to filter.
     * @param commitId
     *            The cutoff commitId. Any files with an ID less than this will
     *            not get passed on.
     * @return An ArrayList<File> containing only those files with commitId's
     *         after the given cutoff.
     */
    private ArrayList<File> newFiles(ArrayList<File> files, int commitId) {
        ArrayList<File> toReturn = new ArrayList<File>();
        for (File f : files) {
            if (getBackupNumber(f) > commitId) {
                toReturn.add(f);
            }
        }
        return toReturn;
    }

    /**
     * Adds the files removed from the given backup to untrackedFiles
     * 
     * @param split:
     *            Backup
     * @param given:
     *            Backup
     */
    private void removedFiles(Backup split, Backup given) {
        for (File f : split.files) {
            if (given.filesHash.get(f) == null) {
                untrackedFiles.add(f);
            }
        }
    }

    /**
     * Updates a backup so it contains all new files and all more recent updated
     * files from the given ArrayList<File>.
     * 
     * @param b
     *            Backup object that will be updated.
     * @param f
     *            List of files to be updated.
     * @return Modified backup object (not a new Backup) containing al new and
     *         updated files.
     */
    private Backup update(Backup b, ArrayList<File> f) {
        HashMap<File, Integer> bf = b.filesHash;
        for (File file : f) {
            Integer extant = bf.get(fromBackup(file));
            if (extant != null) {
                if (extant < getBackupNumber(file)) {
                    // We need to update the file reference.
                    bf.put(fromBackup(file), getBackupNumber(file));
                } else {
                    // Do nothing -- up to date as is.
                }
            } else {
                b.addFile(file);
            }
        }
        // Update the ArrayList<File> files field of the Backup class.
        b.files.clear();
        Iterator<File> itr = b.filesHash.keySet().iterator();
        while (itr.hasNext()) {
            File current = itr.next();
            Integer value = b.filesHash.get(current);
            b.files.add(toBackup(current, value));
        }
        return b;
    }

    // End of helper methods.

    /**
     * 
     * Displays the commit history starting at the current head commit:
     * information about the each commit backwards until the earliest commit.
     * The information displayed should be the commit ID, the time the commit
     * was made, and the commit message.
     * 
     */
    private void log() {
        Backup tempBranchHead = currentBranchHead.pointTo;
        while (tempBranchHead != null) {
            System.out.println("===");
            System.out.println("Commit " + tempBranchHead.hashCode());
            System.out.println(tempBranchHead.formatDate.format(tempBranchHead.dNow));
            System.out.println(tempBranchHead.commitMessage);
            System.out.println();
            tempBranchHead = tempBranchHead.previous;
        }
    }

    /**
     * 
     * Displays the all commit history from all commits ever made: information
     * about the each commit backwards until the earliest commit. The
     * information displayed should be the commit ID, the time the commit was
     * made, and the commit message. As opposed to the log method, the order of
     * the commits does not matter.
     * 
     */
    private void globalLog() {
        int valsLength = idDict.size();
        for (int i = 0; i < valsLength; i++) {
            System.out.println("===");
            System.out.println("Commit " + i);
            System.out.println(idDict.get(i).formatDate.format(idDict.get(i).dNow));
            System.out.println(idDict.get(i).commitMessage);
            System.out.println();
        }
    }

    /**
     * 
     * Prints out the id of the commit that has the given commit message. If
     * there are multiple commits that have the commit message, then the method
     * prints the ID's out on separate lines.
     * 
     * @param message
     *            a String representing the commit message.
     */
    private void find(String message) {
        ArrayList<Backup> tempBackupList = messageDict.get(message);
        if (tempBackupList.size() == 0) {
            throw new IllegalArgumentException("Commit with given ID does not exist.");
        }
        for (Backup backups : tempBackupList) {
            System.out.println(backups.hashCode());
        }
    }

    /**
     * 
     * Creates a new branch with the given name, and points it at the current
     * head node. A branch is nothing more than a name for a pointer to a commit
     * node into the commit tree. Before you ever call branch, your code should
     * be running with a default branch called "master". IMPORTANT: Does NOT
     * immediately switch to the newly created branch. If a branch with the
     * given name already exists, print the error message A branch with that
     * name already exists.
     * 
     * @param branchName
     *            the String literal that will be used as the name of the new
     *            branch.
     */
    private void branch(String newName) {
        Branch newBranch = new Branch(currentBranchHead.pointTo, newName);
        if (branchHeads.get(newName) != null) {
            throw new IllegalArgumentException("A branch with that name already exists.");
        }
        branchHeads.put(newName, newBranch);
        branchHeadsArr.add(newBranch);
    }

    /**
     * 
     * Deletes the branch with the given name. Only deletes the pointer
     * associated with the branch.
     * 
     * @param branchName
     *            the String literal that is used to identify the name of the
     *            branch to be deleted.
     */
    private void removeBranch(String branchName) {
        Branch temp = branchHeads.get(branchName);
        if (temp == currentBranchHead) {
            throw new IllegalArgumentException("Cannot remove the current branch.");
        }
        if (temp == null) {
            throw new IllegalArgumentException("A branch with that name does not exist.");
        }
        branchHeadsArr.remove(temp);
        branchHeads.remove(branchName);
    }

    /**
     * 
     * Checks out all the files tracked by the given commit. Moves the current
     * branch's head to that commit node. If no commit with the given id exists,
     * print "No commit with that id exists."
     * 
     * @exception IllegalArgumentException
     *                if the ID specified corresponds to a commit that does not
     *                exist.
     * @param commitID
     *            the integer representation of the commit's ID.
     */
    private void reset(int commitID) throws IllegalCommandException {
        Backup temp = idDict.get(commitID);
        if (temp == null) {
            System.out.println("No commit with that id exists.");
            return;
        }
        currentBranchHead.pointTo = temp;
        for (File theFiles : temp.files) {
            hardCopy(theFiles, fromBackup(theFiles));
        }

    }

    /**
     * Creates a new gitlet version control system in the current directory.
     * This system will automatically start with one commit: a commit that
     * contains no files and has the commit message initial commit.
     */
    private void init() {
        File main = new File(MAIN_PATH);
        main.mkdirs();
        File staging = new File(STAGING_PATH);
        staging.mkdirs();

        File a = new File(".");
        Backup initBackup = new Backup("initial commit");
        for (File f : getAllFiles(a.listFiles())) {
            File toAdd = toBackup(new File(f.getPath().substring(2)), initBackup);
            hardCopy(f, toAdd);
            initBackup.addFile(toAdd);
        }
        currentBranchHead = new Branch(initBackup, "master");
        branchHeads.put("master", currentBranchHead);
        branchHeadsArr.add(currentBranchHead);
        addToMessageDict(currentBranchHead.pointTo.commitMessage, currentBranchHead.pointTo);
        idDict.put(0, currentBranchHead.pointTo);
        hasInit = true;
    }

    /**
     * Adds a copy of the file as it currently exists to the staging area.
     * Sometimes, for this reason, adding a file is also just called staging the
     * file. The staging area should be somewhere in .gitlet. If the file had
     * been marked for untracking (more on this in the description of the rm
     * command), then add just unmarks the file, and does not also add it to the
     * staging area. Uses toBackup(File), fromBackup(File) -> File
     *
     * @param pathName
     *            specified to retrieve the file used in the process of adding.
     */
    private void add(String pathName) {
        File f = new File(pathName);
        if (!f.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
        hardCopy(f, toStaging(f));
        if (untrackedFiles.contains(f)) {
            int index = untrackedFiles.indexOf(f);
            untrackedFiles.remove(index);
        }
    }

    /**
     * Mark the file for untracking;this means it will not be included in the
     * upcoming commit, even if it was tracked by that commit's parent.
     *
     * @param fileName
     *            specified to retrieve the file used in the process of removal.
     */
    private void rm(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
        // If the file had been staged, instead just unstage it,
        // and don't also mark it for untracking.
        untrackedFiles.add(f);
        File fb = toStaging(f);
        if (fb.exists()) {
            fb.delete();
        }
    }

    /**
     * 
     * @param branchName
     *            the specified branch that will be merged into the current
     *            branch.
     * @throws IllegalCommandException
     *             when the specified branch does not exist. when the user trues
     *             to rebase the specified branch onto itself. when the command
     *             system encounters a merge conflict.
     */
    public void merge(String branchName) throws IllegalCommandException {
        Branch b = branchHeads.get(branchName);
        if (b == null) {
            throw new IllegalCommandException("A branch with that name does not exist.");
        }
        Backup branch1 = currentBranchHead.pointTo;
        Backup branch2 = b.pointTo;
        if (branch1 == branch2) {
            throw new IllegalCommandException("Cannot rebase a branch onto itself.");
        }
        Backup splitPoint = splitPoint(branch1, branch2);
        if (splitPoint == null) {
            throw new RuntimeException("Should be a common history!");
        }
        ArrayList<File> toProcess = newFiles(branch2.files, splitPoint.hashCode());
        removedFiles(splitPoint, branch2);
        for (File f : toProcess) {
            File toFind = fromBackup(f);
            Integer cI = branch1.filesHash.get(toFind);
            if (cI == null) {
                hardCopy(f, toStaging(fromBackup(f)));
                hardCopy(f, fromBackup(f));
            } else {
                if (cI.intValue() <= splitPoint.hashCode()) {
                    hardCopy(f, toStaging(fromBackup(f)));
                    hardCopy(f, fromBackup(f));
                } else {
                    // Conflicting state.
                    inConflictedState = true;
                    File conflictedFile = new File(fromBackup(f).getPath() + ".conflicted");
                    hardCopy(f, conflictedFile);
                    hardCopy(f, toStaging(conflictedFile));
                }
            }
        }
        if (inConflictedState) {
            throw new IllegalCommandException("Encountered a merge conflict.");
        }
        commit("Merged " + currentBranchHead.name + " with " + b.name + ".");
    }

    /**
     * Creates a new Backup of the current directory. By defult new
     * Backup.equals(previous Backup). If files have been committed, the new
     * Backup will contain references to these new files in addition to any
     * original parent files.
     * 
     * @param message
     *            The user-defined message stored with and identifying the
     *            commit.
     * @throws IllegalCommandException
     *             If no changes have been added to the commit.
     */
    private void commit(String message) throws IllegalCommandException {
        // Get Files from staging area.
        File stagingArea = new File(STAGING_PATH);
        ArrayList<File> updates = getAllFiles(stagingArea.listFiles());
        if (updates.size() == 0 && !inConflictedState) {
            throw new IllegalCommandException("No changes added to the commit.");
        }
        // Make backup repository and new Backup object.
        Backup nextBackup = new Backup(message, currentBranchHead.pointTo);
        File newBackup = new File(".gitlet/" + nextBackup.hashCode());
        newBackup.mkdir();
        // Move Files from staging area into new backup location -- clears
        // Staging area.
        // Adds staging area File references to nextBackup.
        for (File f : updates) {
            File toAdd = toBackup(fromStaging(f), nextBackup);
            hardCopy(f, toAdd);
            nextBackup.addFile(toAdd);
        }
        hardClear(new File(STAGING_PATH));
        // Add non duplicate files from previous branch head Backup.
        // Take do not copy over files marked for removal.
        for (File f : currentBranchHead.pointTo.files) {
            if (!nextBackup.files.contains(toBackup(fromBackup(f), nextBackup))
            && !untrackedFiles.contains(fromBackup(f))) {
                nextBackup.addFile(f);
            }
        }
        // Incorporate the nextBackup into the existing data structures.
        // Clear out list of removed files -- they've already been removed.
        untrackedFiles.clear();
        currentBranchHead.pointTo = nextBackup;
        addToMessageDict(message, nextBackup);
        idDict.put(nextBackup.hashCode(), nextBackup);
        if (inConflictedState) {
            inConflictedState = false;
        }
    }

    /**
     * Prints out what branches currently exists and marks the current branch
     * with an *. Also reports staged files and those marked for untracking.
     */
    private void status() {
        System.out.println("=== Branches ===");
        System.out.println("*" + currentBranchHead.name);
        for (Branch b : branchHeadsArr) {
            if (b != currentBranchHead) {
                System.out.println(b.name);
            }
        }
        System.out.println("\n=== Staged Files ===");
        File stagingRoot = new File(STAGING_PATH);
        ArrayList<File> stagedFiles = getAllFiles(stagingRoot.listFiles());
        for (File f : stagedFiles) {
            System.out.println(fromStaging(f).getPath());
        }
        System.out.println("\n=== Files Marked for Untracking ===");
        for (File f : untrackedFiles) {
            System.out.println(f.getPath());
        }
    }

    /**
     * Takes the file in the backup with the given id and puts it in the working
     * directory, replacing duplicates.
     * 
     * @param id
     *            identification of the commit to pull the file from.
     * @param fileS
     *            the file to be pulled from the commit and put in the
     *            directory.
     * @throws IllegalCommandException
     *             Upon finding that the given id does not correspond to a
     *             backup.
     * @throws IllegalCommandException
     *             Upon finding that the file in the given id does not exist.
     */
    private void checkout1(int id, String fileS) throws IllegalCommandException {
        File toPut = new File(fileS);
        Backup toAccess = idDict.get(id);
        if (toAccess == null) {
            throw new IllegalCommandException("No commit with that id exists.");
        }
        File toCheckout = null;
        for (File f : toAccess.files) {
            if (toPut.equals(fromBackup(f))) {
                toCheckout = f;
            }
        }
        if (toCheckout == null) {
            if (id == currentBranchHead.pointTo.hashCode()) {
                // This error message results because checkout2 takes
                // precedence.
                // An error here means that checkout2 failed beforehand.
                throw new IllegalCommandException(
                    "File does not exist in the most recent commit, or no such branch exists.");
            } else {
                throw new IllegalCommandException("File does not exist in that commit.");
            }
        }
        hardCopy(toCheckout, toPut);
    }

    /**
     * Takes all files in the head of the branch and puts them in the current
     * directory, overwriting all duplicates. Also makes the branch the current
     * branch.
     * 
     * @param branch
     *            The name of the branch head to checkout.
     * @throws IllegalCommandException
     *             If the branch head name is the currentBranchHead name. If the
     *             merge conflict is unresolved.
     */
    private void checkout2(String branch) throws IllegalCommandException {
        Branch match = branchHeads.get(branch);
        if (match == currentBranchHead) {
            throw new IllegalCommandException("No need to checkout the current branch.");
        }
        if (match == null) {
            checkout1(currentBranchHead.pointTo.hashCode(), branch);
        } else {
            if (inConflictedState) {
                throw new IllegalCommandException("Cannot do this command until the merge conflict has been resolved.");
            }
            currentBranchHead = match;
            for (File f : match.pointTo.files) {
                hardCopy(f, fromBackup(f));
            }
        }
    }

    /**
     * Takes the current branch, copies it's backups until a common history with
     * the the given Branch, and puts it in front of the given branch. Does
     * nothing if the given branch is the current branch's history. Moves only
     * the current branch head if it's in the history of the given branch.
     * Updated and newly-added files in the given branch head are added to each
     * backup in the copy.
     * 
     * @param branch
     *            The branch to copy and place at the head of the current
     *            branch.
     * @throws IllegalCommandException
     *             If the given branch name does not correspond to that of any
     *             existing branch names. If the given branch name corresponds
     *             to that of the current branch. If the given branch name
     *             corresponds to that of a backup in the history of the current
     *             branch.
     * @throws RuntimeException
     *             If no common branch is found between the current branch and
     *             the given branch. This should never occur for a consistent
     *             internal structure.
     */
    private void rebase(String branch) throws IllegalCommandException {
        Branch rebasingStart = branchHeads.get(branch);
        if (rebasingStart == null) {
            throw new IllegalCommandException("A branch with that name does not exist.");
        }
        // Will be copied onto end of branch2.
        Backup branch1 = currentBranchHead.pointTo;
        // Destination for copying.
        Backup branch2 = rebasingStart.pointTo;
        if (branch1 == branch2) {
            throw new IllegalCommandException("Cannot rebase a branch onto itself.");
        }
        Backup intersection = splitPoint(branch1, branch2);
        if (intersection == null) {
            throw new RuntimeException("Should be a common history in rebase!");
        }
        if (intersection == branch2) {
            throw new IllegalCommandException("Already up-to-date.");
        }
        if (intersection == branch1) {
            currentBranchHead.pointTo = branch2;
            reset(branch2.hashCode());
            return;
        }
        // General Case: Need to copy over all backups from branch1 to branch2.
        // Copy backups and update their files.
        ArrayList<File> toUpdate = newFiles(branch2.files, intersection.hashCode());
        Backup newBranchHead = new Backup(branch1);
        Backup copies = newBranchHead;
        update(copies, toUpdate);
        while (branch1.previous != intersection) {
            branch1 = branch1.previous;
            copies.previous = new Backup(branch1);
            copies = copies.previous;
            update(copies, toUpdate);
        }
        copies.previous = branch1;
        int count = 0;
        int end = copies.hashCode();
        Backup start = newBranchHead;
        while (start != copies.previous) {
            start.hashCode = end - count;
            start = start.previous;
            count++;
        }
        // Add new backups to the hashMaps.
        Backup temp = newBranchHead;
        do {
            addToMessageDict(temp.getCommitMessage(), temp);
            idDict.put(temp.hashCode(), temp);
            temp = temp.previous;
        } while (temp != copies.previous);
        // Replace currentBranchHead pointer in memory.
        branchHeadsArr.remove(currentBranchHead);
        branchHeads.remove(currentBranchHead.name);
        Branch toAdd = new Branch(newBranchHead, currentBranchHead.name);
        branchHeads.put(toAdd.name, toAdd);
        branchHeadsArr.add(toAdd);
        currentBranchHead = toAdd;
        reset(currentBranchHead.pointTo.hashCode());
    }

    //End of specified user-inputted commands.
	
    /**
     * Exception class representing all errors arising from Illegal inputs or
     * series of inputs when running the Gitlet code.
     */
    private class IllegalCommandException extends Exception {
        /**
         * Constructor.
         * 
         * @param message
         *            message to be stored with the exception.
         */
        public IllegalCommandException(String message) {
            super(message);
        }

        /**
         * Default Constructor. Creates an IllegalCommandException with a
         * default message.
         */
        public IllegalCommandException() {
            super("Illegal Command was Entered.");
        }
    }

    /**
     * A Branch is nothing more than a pointer to a given commit node within a
     * commit tree.
     */
    private static class Branch implements Serializable {
        public Backup pointTo;
        public String name;

        /**
         * The Branch class constructor.
         * 
         * @param b
         *            the Backup object the Branch object points to.
         * @param n
         *            the name of the Branch object.
         */
        public Branch(Backup b, String n) {
            pointTo = b;
            name = n;
        }
    }

    /**
     * The Backup class is our way of storing Files, the messages that were
     * specified during commits, the time the commitMessage was made, and the
     * previous Backup object.
     */
    private class Backup implements Serializable {
        public ArrayList<File> files = new ArrayList<File>();
        public HashMap<File, Integer> filesHash = new HashMap<File, Integer>();
        public Backup previous = null;
        private int hashCode = 0;
        private String commitMessage;
        public final Date dNow;
        public SimpleDateFormat formatDate;

        /**
         * The Backup class constructor.
         * 
         * @param commitMessage
         *            the message that was specified during the commit.
         */
        public Backup(String commitMessage) {
            this(commitMessage, null);
        }

        /**
         * The Backup class constructor.
         * 
         * @param commitMessage
         *            the message that was specified during the commit.
         * @param previous
         *            the previous Backup object in the series of commits.
         */
        public Backup(String commitMessage, Backup previous) {
            dNow = new Date();
            formatDate = new SimpleDateFormat("yyyy-MM-dd ' ' hh:mm:ss");
            this.commitMessage = commitMessage;
            this.previous = previous;
            hashCode = hashCodeCounter;
            hashCodeCounter++;
        }

        /**
         * The Backup class constructor.
         * 
         * @param toCopy
         *            the Backup object that this constructor uses to make a new
         *            Backup object.
         */
        public Backup(Backup toCopy) {
            this(toCopy.commitMessage, toCopy.previous);
            for (File f : toCopy.files) {
                files.add(f);
            }
            Iterator<File> itr = toCopy.filesHash.keySet().iterator();
            while (itr.hasNext()) {
                File current = itr.next();
                Integer value = toCopy.filesHash.get(current);
                filesHash.put(current, value);
            }
        }

        /**
         * @return the integer representation of a specific Backup object's
         *         hashcode.
         */
        public int hashCode() {
            return hashCode;
        }

        /**
         * @return the String literal of a specific Backup object's commit
         *         message.
         */
        public String getCommitMessage() {
            return commitMessage;
        }

        /**
         * Helper method to add additional files.
         * 
         * @param file
         *            the file to add to the current list of files.
         */
        private void addFile(File file) {
            files.add(file);
            filesHash.put(fromBackup(file), getBackupNumber(file));
        }

    }
}

