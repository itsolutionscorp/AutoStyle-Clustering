import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.charset.Charset;

public class Gitlet implements Serializable {
    private static final String DIR = ".gitlet/";
    private int numCommits;
    private Commit headPointer;
    private Branch currentBranch;
    private HashMap<Integer, Commit> entries;
    private HashMap<String, File> staged;
    private HashMap<String, Branch> branches;
    private HashMap<String, File> untrack;
    private Boolean conflicted_state;

    public static void main(String[] args) {
        Gitlet g;
        try {
            FileInputStream file = new FileInputStream("git.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            Object one = input.readObject();
            g = (Gitlet) one;
        } catch (Exception e) {
            g = new Gitlet();
        }

        if (args.length == 0) {
            return;
        }
        String command = args[0];
        switch (command) {
        case "init":
            g.init();
            break;
        case "add":
            g.add(args[1]);
            break;
        case "commit":
            g.commit(args[1]);
            break;
        case "rm":
            g.remove(args[1]);
            break;
        case "log":
            g.log();
            break;
        case "global-log":
            g.globalLog();
            break;
        case "status":
            g.status();
            break;
        case "find":
            g.find(args[1]);
            break;
        case "checkout":
            if (args.length > 2) {
                g.checkout(Integer.parseInt(args[1]), args[2]);
            } else {
                g.checkout(args[1]);
            }
            break;
        case "branch":
            g.branch(args[1]);
            break;
        case "rm-branch":
            g.rmBranch(args[1]);
            break;
        case "reset":
            g.reset(Integer.parseInt(args[1]));
            break;
        case "merge":
            g.merge(args[1]);
            break;
        case "rebase":
            g.rebase(args[1]);
            break;
        default:
            System.out.println("Error");
        }
        try {
            FileOutputStream filestream = new FileOutputStream("git.ser");
            ObjectOutputStream os = new ObjectOutputStream(filestream);
            os.writeObject(g);
            os.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Initializes Gitlet
     */
    public Gitlet() {
        conflicted_state = false;
        numCommits = 0;
        entries = new HashMap<Integer, Commit>();
        staged = new HashMap<String, File>();
        branches = new HashMap<String, Branch>();
        untrack = new HashMap<String, File>();
    }

    /**
     * Makes a hidden folder named .gitlet. and make commit0.
     */
    public void init() {
        File f = new File(DIR);
        if (f.exists()) {
            System.out.println("A gitlet version control system already exists in the current directory.");
        } else {
            f.mkdirs();
            initialCommit();
        }
    }

    /**
     * metod that makes the inital commit.
     * 
     */
    public void initialCommit() {
        Commit current = new Commit(numCommits, "initial commit");
        String c = "Commit" + numCommits;
        headPointer = current;
        File folder = new File(DIR, c); // .gitlet/Commit0 into folder
        folder.mkdir();
        entries.put(numCommits, current);
        currentBranch = new Branch("master", headPointer, numCommits);
        branches.put(currentBranch.getBranchName(), currentBranch);
    }

    /**
     * if the file in the working directory is marked for untracking by a rm
     * command it un-untracks that file in the previous commit. if its not
     * makred for untracking, copy that file into the staging area.
     * 
     * @param file
     *            String file_name e.g)a.txt, b.txt;
     * 
     */
    public void add(String file) {
        // Marks files as staged. //inputs abstract path name String like
        // "Hello.txt" / "Hello"
        File fileToAdd = new File(file); // example : file = "a.txt"
        File dest = new File(DIR, file); // file = ".gitlet/a.txt"
        if (fileToAdd.exists()) {
            if (untrack.containsKey(file)) {
                untrack.remove(file);
            } else {
                // file version stage
                try {
                    copy(fileToAdd, dest);
                } catch (IOException I) {
                    System.out.println(I.toString());
                }
                // Hashmap version stage
                staged.put(dest.getName(), dest);
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    /**
     * Copies file source to dest(destination)
     * 
     * @param source
     *            file that you copy from
     * @param dest
     *            destination that you want to copy file to
     * @throws IOException
     * 
     */
    public static void copy(File source, File dest) throws IOException {
        CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES };
        Files.copy(source.toPath(), dest.toPath(), options);
    }

    /**
     * Compares the content of File a and File b
     * 
     * @param a
     *            File a
     * @param b
     *            File B
     * @return true if the content is identical
     * 
     */
    public static boolean compare(File a, File b) {
        try {
            List<String> aList = Files.readAllLines(a.toPath(), Charset.defaultCharset());
            List<String> bList = Files.readAllLines(b.toPath(), Charset.defaultCharset());
            if (aList.equals(bList)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Reveals the secrets of the human mind.
     * 
     * @param message
     *            a string that will be placed in the commit class as identity
     *            string
     */
    public void commit(String message) {
        if (message == null) {
            System.out.println("Please enter a commit message.");
        }
        if (staged.size() == 0 && untrack.size() == 0) {
            System.out.println("No changes added to the commit.");
            return;
        }
        // Make a directory to save current version of files
        numCommits++;
        String c = "Commit" + numCommits;
        File folder = new File(DIR, c); // filepath = .giltet/commitN
        folder.mkdir();

        // composition of new commit foler = default + staged
        // 1. default : copy files that are in previous commit physical
        for (File x : headPointer.track.values()) {
            if (untrack.containsKey(x.getName())) {
            } else {
                File dest = new File(folder, x.getName());
                try {
                    copy(x, dest);
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        }

        // 2. staged : overwrite files that are in the staging area.
        for (File f : staged.values()) {
            File temp = new File(folder, f.getName());
            // f is staged file, temp is pointing to the spot
            try {
                copy(f, temp);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        // removes file in staging area
        for (File p : staged.values()) {
            try {
                Files.delete(p.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Commit current = new Commit(numCommits, message);
        // Commit has three: myprev, track, untrack, headPointer?
        current.myPrev = headPointer;
        for (File k : folder.listFiles()) {
            current.setTrack(k.getName(), k);
        }
        headPointer = current;
        entries.put(numCommits, current);
        staged = new HashMap<String, File>();
        untrack = new HashMap<String, File>();
        currentBranch.setHeadOfBranch(headPointer);
        conflicted_state = false;
    }

    /**
     * if staging area contains the File with name. remove it from staging area
     * else add to untrack.
     * 
     * @param name
     *            File name
     */
    public void remove(String name) {
        if (!staged.containsKey(name)) {
            if (untrack.containsKey(name)) {
                System.out.println("No reason to remove the file.");
                return;
            } else {
                untrack.put(name, headPointer.track.get(name));
            }
        } else {
            File targetFile = new File(DIR, name);
            try {
                Files.delete(targetFile.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            staged.remove(name);
        }
    }

    /**
     * prints out history of commit tree.
     * 
     * 
     */
    public void log() {
        // Iterates through commit list and prints info.
        Commit current = headPointer;
        while (current != null) {
            System.out.println("===");
            System.out.println("Commit " + current.getId() + ".");
            System.out.println(current.getTime());
            System.out.println(current.getMessage());
            System.out.println("");
            current = current.myPrev;
        }
    }

    /**
     * Prints out all commits that have ever been made.
     */
    public void globalLog() {
        for (Commit x : entries.values()) {
            System.out.println("===");
            System.out.println("Commit " + x.getId() + ".");
            System.out.println(x.getTime());
            System.out.println(x.getMessage());
            System.out.println("");
        }
    }

    /**
     * Prints out id of commits that have same value as message with the (String
     * message)
     * 
     * @param message
     *            a String to compare with the commits message
     */
    public void find(String message) {
        int count = 0;
        for (Commit x : entries.values()) {
            if (message.equals(x.getMessage())) {
                System.out.println(x.getId());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Found no commit with that message.");
        }
    }

    /**
     * 1.prints out current branches(*in front signifies currentBranch; 2.prints
     * out staged files; 3.prints out files marked for untracking;
     */
    public void status() {
        System.out.println("=== Branches ===");
        if (branches.size() > 0) {
            for (Branch x : branches.values()) {
                if (currentBranch.getBranchName().equals(x.getBranchName()))
                    System.out.println("*" + x.getBranchName());
                else {
                    System.out.println(x);
                }
            }
        }
        System.out.println("");
        System.out.println("=== Staged Files ===");
        if (staged.size() > 0) {
            for (File x : staged.values()) {
                System.out.println(x.getName());
            }
        }
        System.out.println("");
        System.out.println("=== Files Marked for Untracking ===");
        if (untrack.size() > 0) {
            for (File x : untrack.values()) {
                System.out.println(x.getName());
            }
        }
    }

    /**
     * if there is a branch named (String name) switch files in the working
     * directory o be the branche's head's version of file. if its not a branch
     * name and is a file name in headPointer, copies that file into working
     * directory.
     * 
     * @param name
     *            a string that represents either a file or a branch
     * 
     */
    public void checkout(String name) {
        // Branch name
        if (branches.containsKey(name)) {
            if (conflicted_state) {
                System.out.println("Cannot do this command until the merge conflict has been resolved");
                return;
            }
            if (branches.get(name) == currentBranch) {
                System.out.println("No need to checkout the current branch.");
                return;
            }
            Commit headOfTheBranch = branches.get(name).getBranchHead();
            for (File p : headOfTheBranch.track.values()) {
                File dest = new File(p.getName());
                try {
                    copy(p, dest);
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
            currentBranch = branches.get(name); // Do we have to set Head of
            headPointer = headOfTheBranch;
        } // file name.
        else {
            File target = headPointer.track.get(name);
            File dest = new File(name);
            if (target == null) {
               System.out.println(" File does not exist in the most recent commit, or no such branch exists.");
            } else {
                try {
                    copy(target, dest);
                } catch (IOException i) {
                    System.out.println(i.toString());
                }
            }
        }
    }

    /**
     * copy the commit no.id. file name into working directory.
     * 
     * @param id
     *            a integer id of a commit
     * @param name
     *            a string : file name
     */
    public void checkout(int id, String name) {
        if (!entries.containsKey(id)) {
            System.out.println("No commit with that id exists.");
            return;
        } else {
            File target = entries.get(id).track.get(name);
            if (!entries.get(id).track.containsKey(name)) {
                System.out.println("File does not exist in that commit.");
                return;
            }
            File dest = new File(name);
            try {
                copy(target, dest);
            } catch (IOException i) {
                System.out.println(i.toString());
            }
        }
    }

    /**
     * Creates a branch
     * 
     * @param name
     *            a string that will become the name of the branch
     * 
     */
    public void branch(String name) {
        if (conflicted_state) {
            System.out.println("Cannot do this command until the merge conflict has been resolved");
            return;
        }
        if (branches.containsKey(name)) {
            System.out.println("A branch with that name already exists.");
        } else {
            Branch new_branch = new Branch(name, headPointer, headPointer.getId());
            branches.put(name, new_branch);
        }
    }

    /**
     * Removes a branch, but the commits made still remains the same;
     * 
     * @param name
     *            String branch name
     */
    public void rmBranch(String name) {
        if (conflicted_state) {
            System.out.println("Cannot do this command until the merge conflict has been resolved");
            return;
        }
        Branch placeholder = branches.get(name);
        if (placeholder != null) {
            branches.remove(name);
        } else if (placeholder == currentBranch) {
            System.out.println("Cannot remove the current branch.");
        } else {
            System.out.println("A branch with that name does not exist.");
        }
    }

    /**
     * restores the commit no.id versions of file
     * 
     * @param id
     *            integer id of commit
     * 
     */
    public void reset(int id) {
        if (conflicted_state) {
            System.out.println("Cannot do this command until the merge conflict has been resolved");
            return;
        }
        if (!entries.containsKey(id)) {
            System.out.println("No commit with that id exists.");
            return;
        }
        // String c = "Commit" + id;
        // File targetFolder = new File(DIR, c);
        for (File x : entries.get(id).track.values()) {
            try {
                File dest = new File(x.getName());
                copy(x, dest);
            } catch (IOException i) {
                System.out.println(i.toString());
            }
        }
        currentBranch.setHeadOfBranch(entries.get(id));
        headPointer = entries.get(id);
    }

    /**
     * Finds the split point of given Branch and current Branch
     * 
     * @param given
     *            a Branch that is given to find a splitPoint with current
     *            Branch
     */

    public Commit splitPointFinder(Branch given) {
        Commit givenBranchHead = given.getBranchHead();
        Commit currentBranchHead = currentBranch.getBranchHead();
        Commit givenBranchHeadPointer = givenBranchHead;
        Commit currentBranchHeadPointer = currentBranchHead;
        ArrayList<Integer> givenBranchids = new ArrayList<Integer>();
        HashMap<Integer, Integer> currentBranchids = new HashMap<Integer, Integer>();
        int splitPointid = -1;

        while (givenBranchHeadPointer != null) {
            givenBranchids.add(givenBranchHeadPointer.getId());
            givenBranchHeadPointer = givenBranchHeadPointer.myPrev;
        }
        while (currentBranchHeadPointer != null) {
            currentBranchids.put(currentBranchHeadPointer.getId(), currentBranchHeadPointer.getId());
            currentBranchHeadPointer = currentBranchHeadPointer.myPrev;
        }
        for (int i : givenBranchids) {
            if (currentBranchids.containsKey(i)) {
                splitPointid = i;
                break;
            }
        }
        return entries.get(splitPointid);
    }

    /**
     * Merge the file Firstly, finds splitpoint 1. if file in givenBranch
     * version of splitPoint file doesn't exist, remove it. 2. if file in
     * givenBranch version of splitPoint file changed its content 1) if
     * currentbranch file unchanged content check out the file and stage the
     * file 2) if currentbranch file changed content create a conflicted file
     * into working directory 3. if it created a file named conflict, make it a
     * conflicted state, and disable some methods. else commit with special
     * message.
     * 
     * @param name
     * name of the given branch that you want to merge from
     */
    public void merge(String name) {
        if (!branches.containsKey(name)) {
            System.out.println("A branch with that name does not exist.");
            return;
        }
        if (currentBranch == branches.get(name)) {
            System.out.println("Cannot merge a branch with itself.");
            return;
        }
        int count = 0;
        Commit givenBranchHead = branches.get(name).getBranchHead();
        Commit currentBranchHead = currentBranch.getBranchHead();
        String fileName = "";
        // Find split point
        Commit splitPoint = splitPointFinder(branches.get(name));
        // x files that Given Branch tracks.;
        for (File x : splitPoint.track.values()) {
            fileName = x.getName();
            if (!givenBranchHead.track.containsKey(fileName)) {
                remove(fileName);
                continue;
            } else {
                if (!compare(x, givenBranchHead.track.get(fileName))) {
                    if (currentBranchHead.track.containsKey(fileName)) {
                        if (compare(x, currentBranchHead.track.get(fileName))) {
                            checkout(givenBranchHead.getId(), fileName);
                            add(fileName);
                        } else {
                            File conflict_directory = new File(x.getName() + ".conflicted");
                            try {
                                    copy(givenBranchHead.track.get(fileName), conflict_directory);
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                            count++;
                        }
                    }
                }
            }
        }
        // 3.
        if (count > 0) {
            conflicted_state = true;
            System.out.println("Encountered a merge conglict.");
        } else {
            commit("Merged " + currentBranch.getBranchName() + " with " + branches.get(name).getBranchName());
        }
    }

    /**
     * Rebase
     * 
     * @param name
     * String name of the given branch
     * 
     * 
     */
    public void rebase(String name) {
        if (conflicted_state) {
                System.out.println("Cannot do this command until the merge conflict has been resolved");
                return;
        }

        if (!branches.containsKey(name)) {
                System.out.println("A branch with that name does not exist.");
                return;
        }
        if (currentBranch == branches.get(name)) {
                System.out.println("Cannot rebase a branch onto itself.");
                return;
        }

        Commit splitPoint = splitPointFinder(branches.get(name));
        Commit givenBranchHead = branches.get(name).getBranchHead();
        Commit givenBranchHeadPointer = givenBranchHead;
        Commit currentBranchHead = currentBranch.getBranchHead();
        Commit currentBranchHeadPointer = currentBranchHead;
        Stack<Commit> commitReversal = new Stack<Commit>();

        Commit placeholder = null;
        // when Current branch's head is in the history of given Branch, set
        // current branch's head to be given branch's head.
        while (givenBranchHeadPointer != null) {
                if (givenBranchHeadPointer == currentBranchHead) {
                        currentBranch.setHeadOfBranch(givenBranchHead);
                        headPointer = givenBranchHead;
                        System.out.println("Already up-to-date.");
                        return;
                }
                givenBranchHeadPointer = givenBranchHeadPointer.myPrev;
        }

        // put commits to replay in stack(so that it pops in reverse order.
        while (currentBranchHeadPointer != splitPoint) {
                commitReversal.push(currentBranchHeadPointer);
                currentBranchHeadPointer = currentBranchHeadPointer.myPrev;
        }

        // set currentBranchHead to be given Branch head to figure out myPrev.
        currentBranch.setHeadOfBranch(givenBranchHead);
        currentBranchHead = currentBranch.getBranchHead();

        // placeholder points to closer commits from splitPoint to farther one.
        while (!commitReversal.empty()) {
            // Makes a file directory coommit next.
            numCommits++;
            String c = "Commit" + numCommits;
            File folder = new File(DIR, c); // filepath = .giltet/commitN
            folder.mkdir();
            placeholder = commitReversal.pop();
            Commit replayNew = new Commit(numCommits, placeholder.getMessage());
            // takes care of myPrev
            replayNew.myPrev = currentBranchHead;
            // takes care of track.
            for (File x : splitPoint.track.values()) {
                if (placeholder.track.containsKey(x.getName())) {
                    if (compare(x, placeholder.track.get(x.getName()))) {
                        // propagate
                        if (currentBranchHead.track.containsKey(x.getName())) {
                            replayNew.setTrack(x.getName(), replayNew.myPrev.track.get(x.getName()));
                            try {
                                File dest = new File(folder, x.getName());
                                copy(replayNew.myPrev.track.get(x.getName()), dest);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        replayNew.setTrack(x.getName(), placeholder.track.get(x.getName()));
                        try {
                            File dest = new File(folder, x.getName());
                            copy(placeholder.track.get(x.getName()), dest);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            currentBranchHead = replayNew;
            headPointer = replayNew;
            currentBranch.setHeadOfBranch(headPointer);
            entries.put(replayNew.getId(), replayNew);
        }
    }

    private static class Commit implements Serializable {
        private int id;
        private String time;
        private String message;
        private Commit myPrev;
        private HashMap<String, File> track;

        public Commit(int id, String message) {
            // get current system time
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String t = form.format(cal.getTime()).toString();
            this.time = t;
            this.message = message;
            this.id = id;
            this.track = new HashMap<String, File>();
            this.myPrev = null;
        }

        public void setTrack(String file_name, File file_to_add) {
            track.put(file_name, file_to_add);
        }

        public int getId() {
            return this.id;
        }

        public String getTime() {
            return this.time;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private static class Branch implements Serializable {
        private String name;
        private Commit Head_of_Branch;
        private int id;

        public Branch(String branch_Name, Commit branch_head, int myID) {
            // get current system time
            name = branch_Name;
            Head_of_Branch = branch_head;
            id = myID;

        }

        public void setHeadOfBranch(Commit head_to_set) {
            Head_of_Branch = head_to_set;
        }

        public String getBranchName() {
            return name;
        }

        public String toString() {
            return name;
        }

        public Commit getBranchHead() {
            return Head_of_Branch;
        }

        public int getId() {
            return id;
        }
    }
}
