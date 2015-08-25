import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
 
public class Gitlet implements java.io.Serializable {
 
    private static final long serialVersionUID = 123456789;
    private boolean isStaging;
    private HashSet<String> untracking;
    private HashSet<String> stage;
    private ArrayList<String> paths;
    private int count;
    private boolean conflicted;
    private HashMap<String, ArrayList<Integer>> messageToId;
    private String head;
    private HashMap<String, Integer> branches;
 
    /**
     * Interprets input and calls corresponding method. Updates the current
     * Gitlet object to the serialized one, or else creates a new instance.
     * 
     * @param args
     *            Takes in commands from the command line.
     * 
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Please enter a command.");
                return;
            }
            Gitlet G;
            if (args[0].equals("init")) {
                if (hasBeenStarted()) {
                    System.out
                            .println("A gitlet version control system already exists in the current directory.");
                    return;
                }
                G = new Gitlet();
                G.doInit(args);
            } else {
                try {
                    G = update();
                } catch (Exception e) {
                    System.out
                            .println("A gitlet version control system has yet to be initialized.");
                    return;
                }
                if (hasBeenStarted()) {
                    if (args[0].equals("add")) {
                        G.doAdd(args);
                    } else if (args[0].equals("commit")) {
                        G.doCommit(args);
                    } else if (args[0].equals("rm")) {
                        G.doRm(args);
                    } else if (args[0].equals("log")) {
                        G.doLog(args);
                    } else if (args[0].equals("global-log")) {
                        G.doGlobalLog(args);
                    } else if (args[0].equals("find")) {
                        G.doFind(args);
                    } else if (args[0].equals("status")) {
                        G.doStatus(args);
                    } else if (args[0].equals("checkout")) {
                        G.doCheckout(args);
                    } else if (args[0].equals("branch")) {
                        G.doBranch(args);
                    } else if (args[0].equals("rm-branch")) {
                        G.doRmBranch(args);
                    } else if (args[0].equals("reset")) {
                        G.doReset(args);
                    } else if (args[0].equals("merge")) {
                        G.doMerge(args);
                    } else if (args[0].equals("rebase")) {
                        G.doRebase(args);
                    } else {
                        System.out.println("No command with that name exists.");
                    }
                    G.saveGitlet();
                    return;
                } else {
                    System.out
                            .println("A gitlet version control system has yet to be initialized.");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("No command with that name exists.");
            return;
        }
    }
 
    /**
     * The constructor for a Gitlet instance.
     */
    public Gitlet() {
        isStaging = false;
        paths = new ArrayList<String>();
        count = 0;
        conflicted = false;
        messageToId = new HashMap<String, ArrayList<Integer>>();
        branches = new HashMap<String, Integer>();
        untracking = new HashSet<String>();
        stage = new HashSet<String>();
    }
 
    /**
     * Reads a serialized Gitlet instanced and allows restoring it's state
     * between commands.
     * 
     * @throws Exception
     *             If something goes wrong when locating currentGit.ser
     * @return The de-serialized Gitlet object representing the state of Gitlet
     *         when it was last closed.
     */
    public static Gitlet update() throws Exception {
        FileInputStream toUpdate = new FileInputStream(
                "./.gitlet/currentGit.ser");
        ObjectInputStream obj = new ObjectInputStream(toUpdate);
        Gitlet toReturn = (Gitlet) obj.readObject();
        obj.close();
        return toReturn;
    }
 
    /**
     * Serializes the current Gitlet object and overrides the last save. Allows
     * a saved constant state between commands.
     * 
     * @throws Exception
     *             If something goes wrong in the serialization process.
     */
    public void saveGitlet() throws Exception {
        OutputStream toSave = new FileOutputStream("./.gitlet/currentGit.ser");
        ObjectOutputStream obj_out = new ObjectOutputStream(toSave);
        obj_out.writeObject(this);
        obj_out.close();
    }
 
    /**
     * Checks to see if there is a serialized Gitlet instance stored.
     * 
     * @return true if there is a serialized Gitlet instance in the proper
     *         directory and false otherwise
     */
    public static boolean hasBeenStarted() {
        File f = new File("./.gitlet");
        return f.exists();
    }
 
    /**
     * Creates the first instance of Commit, and the .gitlet folder structure.
     * 
     * @param args
     *            The command line arguments main was given. Only needed to
     *            verify there are no additional arguments given.
     * @throws Exception
     *             Throws an exception when there are too many arguments given
     *             in the command line.
     */
    public void doInit(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception("Init cannot take in arguments.");
        }
        new File("./.gitlet/").mkdir();
        new File("./.gitlet/commits/0/").mkdirs();
        new File("./.gitlet/stage/").mkdir();
        Commit toStart = new Commit(count, "initial commit", -1);
        toStart.setPath("./.gitlet/commits/0/commit.ser");
        toStart.saveCommit();
        paths.add("./.gitlet/commits/0/");
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(count);
        messageToId.put("initial commit", A);
        head = "master";
        branches.put(head, count);
        count++;
        saveGitlet();
    }
 
    /**
     * Adds a file to staging area to be stored by a future commit.
     * 
     * @param args
     *            Command line arguments to main. doAdd expects there to be a
     *            filename argument in args.
     * @throws Exception
     *             If no filename argument was given, or too many arguments were
     *             given.
     */
    public void doAdd(String[] args) throws Exception {
        if (args.length == 1) {
            throw new Exception("No file name provided.");
        }
        if (args.length > 2) {
            throw new Exception("Too many file names provided.");
        }
        if (untracking.contains(args[1])) {
            untracking.remove(args[1]);
            return;
        }
        File f = new File(args[1]);
        Path newPath = new File("./.gitlet/stage/" + args[1]).toPath();
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return;
        } else {
            isStaging = true;
            stage.add(args[1]);
            File to = new File("./.gitlet/stage/" + args[1]);
            if (to.exists())
                to.delete();
            newPath.getParent().toFile().mkdirs();
            Files.copy(f.toPath(), newPath);
            to.createNewFile();
        }
    }
 
    /**
     * Creates a new folder with the current commit ID and stores any files in
     * the staging area in this new commit.
     * 
     * @param args
     *            Command line arguments to main. doCommit expects there to be a
     *            commit message in args.
     * @throws Exception
     *             If the parent commit cannot be found.
     * @throws ClassNotFoundException
     *             Required by Files.copy. Thrown if error arises in copy.
     */
    public void doCommit(String[] args) throws ClassNotFoundException,
            Exception {
        if (args.length == 1) {
            System.out.println("Please enter a commit message.");
        } else if (!isStaging && untracking.isEmpty()) {
            System.out.println("No changes added to the commit.");
        } else {
            Commit toCommit = new Commit(count, args[1], branches.get(head));
            paths.add(paths.get(branches.get(head)) + count + "/");
            new File(paths.get(count)).mkdir();
            for (String fileName : stage) {
                toCommit.addFile(fileName, paths.get(count) + fileName);
                toCommit.addNewFile(fileName, paths.get(count) + fileName);
                new File(paths.get(count) + fileName).toPath().getParent()
                        .toFile().mkdirs();
                Files.copy(Paths.get("./.gitlet/stage/" + fileName),
                        Paths.get(paths.get(count) + fileName));
            }
            Commit parentCommit = getCommit(branches.get(head));
            HashMap<String, String> pFiles = parentCommit.files();
            for (String key : pFiles.keySet()) {
                if (untracking.contains(key)) {
                    continue;
                }
                if (!new File("./.gitlet/stage/" + key).exists()) {
                    toCommit.addFile(key, pFiles.get(key));
                }
            }
            if (messageToId.get(args[1]) == null) {
                ArrayList<Integer> A = new ArrayList<Integer>();
                A.add(count);
                messageToId.put(args[1], A);
            } else {
                ArrayList<Integer> A = messageToId.get(args[1]);
                A.add(count);
                messageToId.put(args[1], A);
            }
            untracking = new HashSet<String>();
            branches.put(head, count);
            isStaging = false;
            stageClear();
            conflicted = false;
            stage = new HashSet<String>();
            toCommit.setPath(paths.get(count) + "commit.ser");
            toCommit.saveCommit();
            stage.clear();
            count++;
        }
    }
 
    /**
     * Untracks the given file. If file was staged, doRm unstages it instead.
     * 
     * @param args
     *            Command line arguments to main. doRm expects there to be a
     *            filename argument in args.
     * 
     * @throws Exception
     *             If something goes from when getting the head commit
     *             (unlikely), or when the wrong number of arguments are given.
     */
    public void doRm(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception();
        }
        if (stage.contains(args[1])) {
            stage.remove(args[1]);
            new File("./.gitlet/stage/" + args[1]).delete();
        } else if (getCommit(branches.get(head)).files().containsKey(args[1])) {
            untracking.add(args[1]);
        } else {
            System.out.println("No reason to remove the file.");
        }
    }
 
    /**
     * Prints out the history of commits of the current head.
     * 
     * @param args
     *            Command line arguments to main. doLog expects no additional
     *            arguments.
     * 
     * @throws Exception
     *             Throws an exception when there are too many arguments given
     *             in the command line.
     */
    public void doLog(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception();
        }
        int current = branches.get(head);
        while (current != -1) {
            current = getCommit(current).log();
        }
    }
 
    /**
     * Returns the commit object corresponding to id.
     * 
     * @param id
     *            ID of the commit to be fetched.
     * @throws Exception
     *             Throws an exception when that commit ID does not exist.
     */
    public Commit getCommit(int id) {
        try {
            String toPath = paths.get(id);
            FileInputStream toUpdate = new FileInputStream(toPath
                    + "commit.ser");
            ObjectInputStream obj = new ObjectInputStream(toUpdate);
            Commit toReturn = (Commit) obj.readObject();
            obj.close();
            return toReturn;
        } catch (Exception e) {
            return null;
        }
    }
 
    /**
     * Prints out entire history of all commits.
     * 
     * @param args
     *            The command line arguments main was given. Only needed to
     *            verify there are no additional arguments given.
     */
    public void doGlobalLog(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception();
        }
        for (int i = 0; i < count; i++) {
            getCommit(i).log();
        }
    }
 
    /**
     * Prints out ID of the commit(s) associated with the given message. If
     * multiple, prints them out on separate lines.
     * 
     * @param args
     *            The command line arguments main was given. doFine expects a
     *            commit message argument to be given in args.
     * 
     * @throws Exception
     *             If no commit message is given in args, or if too many
     *             arguments are given.
     */
    public void doFind(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception();
        }
        ArrayList<Integer> A = messageToId.get(args[1]);
        if (A == null) {
            System.out.println("Found no commit with that message.");
        } else {
            for (Integer b : A) {
                System.out.println(b);
            }
        }
    }
 
    /**
     * Prints out existing branches (current branch marked with *). Also prints
     * out any staged or untracked files.
     * 
     * @param args
     *            The command line arguments main was given. Only needed to
     *            verify there are no additional arguments given.
     */
    public void doStatus(String[] args) {
        if (args.length != 1) {
            return;
        }
        System.out.println("=== Branches ===");
        for (String branch : branches.keySet()) {
            if (branch.equals(head)) {
                System.out.print("*");
            }
            System.out.println(branch);
        }
        System.out.println();
        System.out.println("=== Staged Files ===");
        for (String added : stage) {
            System.out.println(added);
        }
        System.out.println();
        System.out.println("=== Files Marked for Untracking ===");
        for (String untracked : untracking) {
            System.out.println(untracked);
        }
    }
 
    /**
     * Takes file(s) as it/they currently exist in the given argument and puts
     * it/them in the working directory, overwriting versions of files already
     * there. Branch names take precedence over file names.
     * 
     * @param args
     *            The command line arguments main was given. doCheckout expects
     *            one or two additional arguments.
     * @throws Exception
     *             If no additional argument was given, or if too many were
     *             given.
     */
    public void doCheckout(String[] args) throws Exception {
        if (args.length == 3) {
            if (Integer.parseInt(args[1]) < count) {
                Commit toCheckout = getCommit(Integer.parseInt(args[1]));
                if (toCheckout.files().containsKey(args[2])) {
                    Files.copy(
                            Paths.get((String) toCheckout.files().get(args[2])),
                            Paths.get(args[2]),
                            new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
                } else {
                    System.out.println("File does not exist in that commit.");
                    return;
                }
            } else {
                System.out.println("No commit with that id exists.");
                return;
            }
        } else if (args.length == 2) {
            if (branches.containsKey(args[1])) {
                if (conflicted) {
                    hasConflict();
                }
                if (args[1].equals(head)) {
                    System.out
                            .println("No need to checkout the current branch.");
                    return;
                } else {
                    HashMap<String, String> theHash = (HashMap<String, String>) getCommit(
                            branches.get(args[1])).files();
                    for (String pathName : theHash.keySet()) {
                        Files.copy(
                                Paths.get(theHash.get(pathName)),
                                Paths.get(pathName),
                                new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
                    }
                    head = args[1];
                }
            } else {
                Commit headCom = getCommit(branches.get(head));
                if (headCom.files().containsKey(args[1])) {
                    Files.copy(
                            Paths.get(headCom.files().get(args[1])),
                            Paths.get(args[1]),
                            new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
                } else {
                    System.out
                            .println("File does not exist in the most recent commit, or no such branch exists.");
                    return;
                }
            }
        } else {
            throw new Exception();
        }
    }
 
    /**
     * Creates a new branch pointer with the given name at the current head
     * commit.
     * 
     * @param args
     *            The command line arguments main was given. doBranch expects a
     *            new branch name to be given as an argument in args.
     * @throws Exception
     *             If no branch name was given, or multiple arguments were
     *             given.
     */
    public void doBranch(String[] args) throws Exception {
        if (conflicted) {
            hasConflict();
        }
        if (args.length != 2) {
            throw new Exception();
        }
        if (branches.containsKey(args[1])) {
            System.out.println("A branch with that name already exists.");
        } else {
            branches.put(args[1], branches.get(head));
        }
    }
 
    /**
     * Removes the branch pointer with the given name.
     * 
     * @param args
     *            The command line arguments main was given. doBranch expects a
     *            branch name to be given as an argument in args.
     * @throws Exception
     *             If no branch name was given, or multiple arguments were
     *             given.
     */
    public void doRmBranch(String[] args) throws Exception {
        if (conflicted) {
            hasConflict();
        }
        if (args.length != 2) {
            throw new Exception();
        }
        if (branches.containsKey(args[1])) {
            if (args[1].equals(head)) {
                System.out.println("Cannot remove the current branch.");
            } else {
                branches.remove(args[1]);
            }
        } else {
            System.out.println("A branch with that name does not exist.");
        }
    }
 
    /**
     * Checks out all files tracked by given commit and moves the current head
     * pointer to this commit ID.
     * 
     * @param args
     *            The command line arguments main was given. doReset expects an
     *            ID number to be given as an argument.
     * @throws Exception
     *             If number of arguments is too great, or no ID was given.
     */
    public void doReset(String[] args) throws Exception {
        if (conflicted) {
            hasConflict();
        }
        if (args.length != 2) {
            throw new Exception();
        }
        if (Integer.parseInt(args[1]) < count) {
            branches.put(head, Integer.parseInt(args[1]));
            String stored = head;
            head = null;
            doCheckout(new String[] { "checkout", stored });
            head = stored;
        } else {
            System.out.println("No commit with that id exists.");
            return;
        }
    }
 
    /**
     * Merges files from the given branch into the current branch in the form of
     * a new commit.
     * 
     * @param args
     *            The command line arguments main was given. doMerge expects an
     *            branch name to be given as an argument.
     * @throws Exception
     *             If too many arguments were given, or no branch name is given.
     */
    public void doMerge(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception();
        }
        if (conflicted) {
            hasConflict();
        }
        if (args[1].equals(head)) {
            System.out.println("Cannot merge a branch with itself.");
            return;
        }
        if (!branches.containsKey(args[1])) {
            System.out.println("A branch with that name does not exist.");
        }
        int commonId = commonParent(branches.get(head), branches.get(args[1]));
        Commit common = getCommit(commonId);
        Commit current = getCommit(branches.get(head));
        Commit given = getCommit(branches.get(args[1]));
        for (String fileName : given.files().keySet()) {
            String com = common.files().get(fileName);
            String give = given.files().get(fileName);
            String cur = current.files().get(fileName);
            if (com == null || !com.equals(give)) {
                if ((cur != null) && (com == null || !com.equals(cur))) {
                    File toCopy = new File(given.files().get(fileName));
                    if (new File(fileName + ".conflicted").exists()) {
                        new File(fileName + ".conflicted").delete();
                    }
                    Files.copy(toCopy.toPath(), new File(fileName
                            + ".conflicted").toPath());
                    conflicted = true;
                } else {
                    doCheckout(new String[] { "",
                            branches.get(args[1]).toString(), fileName });
                    doAdd(new String[] { "", fileName });
                }
            }
        }
        if (!conflicted) {
            String[] commitMessage = { "",
                    "Merged " + head + " with " + args[1] + "." };
            doCommit(commitMessage);
        } else {
            System.out.println("Encountered a merge conflict.");
            return;
        }
    }
 
    /**
     * Find the common parent of two branches.
     * 
     * @param c1
     *            ID of first branch
     * @param c2
     *            ID of second branch
     * @return ID of parent branch
     * 
     */
    public int commonParent(int c1, int c2) {
        if (c1 < c2) {
            return commonParent(c1, getCommit(c2).parentId());
        } else if (c1 == c2) {
            return c1;
        } else {
            return commonParent(getCommit(c1).parentId(), c2);
        }
    }
 
    /**
     * Finds the split point between current and given branch, copies the
     * current branch, and attaches it to the given branch. Current branch
     * pointer moved to the head of this new branch, old branch pointer lost.
     * 
     * @param args
     *            The command line arguments main was given. doRebase expects an
     *            branch name to be given as an argument.
     * @throws Exception
     *             If no branch name is given, or if too many arguments are
     *             given.
     */
    public void doRebase(String[] args) throws Exception {
        if (conflicted) {
            hasConflict();
        }
        if (args.length != 2) {
            throw new Exception();
        }
        if (!branches.containsKey(args[1])) {
            System.out.println("A branch with that name does not exist.");
            return;
        }
        if (head.equals(args[1])) {
            System.out.println("Cannot rebase a branch onto itself.");
            return;
        }
        int common = commonParent(branches.get(head), branches.get(args[1]));
        int originalHeadId = branches.get(head);
        ArrayList<Integer> history = historyUpTo(common, branches.get(head),
                branches.get(args[1]));
        branches.put(head, branches.get(args[1]));
        if (common == originalHeadId) {
            return;
        }
        for (int i = history.size() - 1; i >= 0; i--) {
            copyCommit(branches.get(head), history.get(i));
        }
    }
 
    /**
     * Copies a commit (oldID), and creates a new commit with the same files,
     * but different ID/Date.
     * 
     * @param parID
     *            ID of parent commit
     * @param oldID
     *            ID old commit
     * 
     */
    public void copyCommit(int parID, int oldID) {
        paths.add(paths.get(branches.get(head)) + count + "/");
        new File(paths.get(count)).mkdir();
        Commit parentCom = getCommit(parID);
        Commit oldCom = getCommit(oldID);
        Commit newCom = new Commit(count, oldCom.madeMessage(), parID);
        setFiles(parentCom, newCom); // adds files from parents
        uniqueOverride(newCom, getCommit(oldID).newFiles()); // only uniques
        newCom.setPath(paths.get(count) + "commit.ser");
        newCom.saveCommit();
        branches.put(head, count);
        count++;
    }
 
    /**
     * Copies list of files from fromCom to toCom.
     * 
     * @param fromCom
     *            Commit containing files you want to copy.
     * @param toCom
     *            Commit you want to copy files into.
     */
    public void setFiles(Commit fromCom, Commit toCom) {
        HashMap<String, String> pFiles = fromCom.files();
        for (String key : pFiles.keySet()) {
            if (untracking.contains(key)) {
                continue;
            }
            toCom.addFile(key, pFiles.get(key));
        }
    }
 
    /**
     * Adds files from a list of unique files to toCom. Will override previous
     * files.
     * 
     * @param toCom
     *            Commit you want to copy files into.
     * @param uniques
     *            A HashMap of unique files you are adding
     */
    public void uniqueOverride(Commit toCom, HashMap<String, String> uniques) {
        HashMap<String, String> pFiles = uniques;
        for (String key : pFiles.keySet()) {
            if (untracking.contains(key)) {
                continue;
            }
            toCom.addFile(key, pFiles.get(key));
        }
    }
 
    /**
     * Returns the history of the current branch up to its split-point with the
     * given branch.
     * 
     * @param common
     *            ID of split-point of current and given branches
     * @param current
     *            ID of head of current branch
     * @param given
     *            ID of head of given branch
     * @return ArrayList of IDs of commits in history.
     */
    private ArrayList<Integer> historyUpTo(int common, int current, int given)
            throws Exception {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (; current > common; current = getCommit(current).parentId()) {
            if (current == given) {
                System.out.println("Already up-to-date.");
                throw new Exception();
            }
            result.add(current);
        }
        return result;
    }
 
    /**
     * Throws an Exception when Gitlet is in a conflicted state.
     * 
     * @throws Exception
     *             Always. Is caught by functions higher up.
     */
    public void hasConflict() throws Exception {
        System.out
                .println("Cannot do this command until the merge conflict has been resolved.");
        throw new Exception();
    }
 
    /**
     * Clears the staging area. Adapted from:
     * http://www.coderanch.com/t/378308/java
     * /java/delete-existing-files-dierectry
     */
    public void stageClear() {
        File file = new File("./.gitlet/stage");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                if (myFile.isDirectory()) {
                    deleteDirectory(myFile);
                } else {
                    myFile.delete();
                }
            }
        }
    }
 
    /**
     * Deletes all files in a directory, then the directory itself.
     * 
     * @param path
     *            File object containing name of directory
     * */
    static public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
}