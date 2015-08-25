import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
 
/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * @author Joseph Moghadam
 * 
 *         Some code adapted from StackOverflow:
 * 
 *         http://stackoverflow.com/questions
 *         /779519/delete-files-recursively-in-java
 * 
 *         http://stackoverflow.com/questions/326390/how-to-create-a-java-string
 *         -from-the-contents-of-a-file
 * 
 *         http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
 *         println
 * 
 */
public class GitletTest {
    private static final String GITLET_DIR = ".gitlet/";
    private static final String TESTING_DIR = "test_files/";
 
    /* matches either unix/mac or windows line separators */
    private static final String LINE_SEPARATOR = "\r\n|[\r\n]";
 
    /**
     * Deletes existing gitlet system, resets the folder that stores files used
     * in testing.
     * 
     * This method runs before every @Test method. This is important to enforce
     * that all tests are independent and do not interact with one another.
     */
    @Before
    public void setUp() {
        File f = new File(GITLET_DIR);
        File g = new File("gitlet.ser");
        if (g.exists()) {
            g.delete();
        }
        if (f.exists()) {
            try {
                recursiveDelete(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f = new File(TESTING_DIR);
        if (f.exists()) {
            try {
                recursiveDelete(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f.mkdirs();
    }
    /**
     * THE FOLLOWING TESTS TEST THE BASIC FUNCTIONALITY OF THE GITLET PROGRAM
     */
    
    /**
     * Tests that init creates a .gitlet directory. Does NOT test that init
     * creates an initial commit, which is the other functionality of init.
     */
    @Test
    public void testBasicInitialize() {
        gitlet("init");
        File f = new File(GITLET_DIR);
        assertTrue(f.exists());
    }
    
    /**
     * Tests that a commit can be made when the only action was untracking a file (no staging)
     */
    @Test
    public void testCommitUntracked() {
        setUp();
        gitlet("init");
        String wugFileName = TESTING_DIR + "wug.txt";
        createFile(wugFileName, "This is a wug.");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");
        gitlet("rm", wugFileName); //Marks wug.txt for untracking
        gitlet("commit", "untracks wug");
        assertEquals(gitletFast("find", "untracks wug"), "2");
    }
 
    /**
     * Tests that checking out a file name will restore the version of the file
     * from the previous commit. Involves init, add, commit, and checkout.
     */
    @Test
    public void testBasicCheckout() {
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("init");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");
        writeFile(wugFileName, "This is not a wug.");
        gitlet("checkout", wugFileName);
        assertEquals(wugText, getText(wugFileName));
    }
 
    /**
     * Tests that log after one commit conforms to the format in the spec.
     * Involves init, add, commit, and log.
     */
    @Test
    public void testBasicLog() {
    	setUp();
        gitlet("init");
        String commitMessage1 = "initial commit";
 
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);
        String commitMessage2 = "added wug";
        gitlet("commit", commitMessage2);
 
        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
    }
     
    /**
     * Tests that branching works, also tests for rm-branch and checkout branch
     */
    @Test
    public void testBasicBranch() { //Includes the functionality test for rm-Branch
        setUp();
        String branchYo = "yo";
        String branchMaster = "master";
         
        gitletFast("init");
 
        String wugFileName = TESTING_DIR + "wug.txt";
        createFile(wugFileName, "This is a wug.");
        gitletFast("add", wugFileName);
        gitletFast("commit", "added wug");
         
        gitletFast("branch", branchYo);
        gitletFast("checkout", branchYo);
         
        writeFile(wugFileName, "This is a wuggy wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "YO wug");
         
        gitletFast("checkout", branchMaster);
        assertEquals("This is a wug.", getText(wugFileName)); //Checks that the commitNode in master branch retains the old text
         
        writeFile(wugFileName, "This is the wuggiest wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "3rd wug");
        gitletFast("checkout", "2", wugFileName);
        assertEquals("This is a wuggy wug", getText(wugFileName)); //Checks that commitNode 2 is in branchYo and retains the "This is a wuggy wug"
         
        String globalLogContent = gitletFast("global-log");
        assertArrayEquals(new String[] { "initial commit", "added wug", "YO wug", "3rd wug"},
                extractCommitMessages(globalLogContent));
         
        //Remove Branch
        gitletFast("rm-branch", branchYo);
        assertEquals(gitlet("checkout", branchYo), "File does not exist in the most recent commit, or no such branch exists.");
        assertEquals(gitlet("rm-branch", branchMaster), "Cannot remove the current branch.");
    }
    
    /**
     * Tests that reset to a previous commit works; appropriate files should be changed to the commit's versions of the files
     */
    @Test
    public void testBasicReset(){
        setUp();
        gitletFast("init");
        String wugFileName1 = TESTING_DIR + "file1.txt";
        createFile(wugFileName1, "hi");
        gitletFast("add", wugFileName1);
        gitletFast("commit", "first commit");
        String wugFileName2 = TESTING_DIR + "file2.txt";
        createFile(wugFileName2, "yo");
        gitletFast("add", wugFileName2);
        gitletFast("rm", wugFileName1);
        gitletFast("commit", "Second commit");
        writeFile(wugFileName2, "yo.");
        gitletFast("add", wugFileName1);
        gitletFast("add", wugFileName2);
        gitletFast("commit", "Third commit");
        gitletFast("reset", "2");
        assertEquals(getText(wugFileName1),"hi");
        assertEquals(getText(wugFileName2),"yo");
    }
     
    /**
     * Tests the basic functionality of merge
     * This tests the case where file is changed in current branch but not in the argument branch
     * The current branch's version should take precedence, no conflicted file should be created
     */
    @Test
    public void testBasicMerge(){
    	setUp();
        gitletFast("init");
        String wugFileName = TESTING_DIR + "file1.txt";
        createFile(wugFileName, "I am a wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "first commit");
        gitletFast("branch", "new_branch");
        gitletFast("checkout", "new_branch");
        writeFile(wugFileName, "I am a super wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "second commit, new branch, new life");
        gitletFast("merge", "master");
        System.out.println(getText(wugFileName));
        assertEquals(getText(wugFileName), "I am a super wug"); //Files changed on the current branch after split point
  
        //should stay the same
        //The file contents should be the same as the current director
      
        String conflictedFileName = TESTING_DIR + "file1.txt.conflicted";
        File conflictedFile = new File(conflictedFileName);
        assertFalse(conflictedFile.exists()); //shouldn't generate a conflicted file (file wasn't changed since split point in old branch
          
    }
    
    /**
     * Tests another case for merge
     * This tests that .conflicted files are created when there is a conflict in the merge
     * Also tests that certain commands are locked during a conflicted state
     */
    @Test
    public void testBasicMerge2(){
    	setUp();
        gitletFast("init");
        String wugFileName = TESTING_DIR + "file1.txt";
        createFile(wugFileName, "I am a wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "default commit");
        //should stay the same
        gitletFast("branch", "branch3");
          
        gitletFast("checkout", "branch3");
        writeFile(wugFileName, "I am the best type of wug.");
        gitletFast("add", wugFileName);
        gitletFast("commit", "first commit on branch3");
        //gitlet("branch","new_branch");
        gitletFast("checkout", "master");
        writeFile(wugFileName, "I am a high wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "first commit");
        gitlet("merge", "branch3"); //current branch: master, given: branch3
        assertEquals(getText(wugFileName), "I am a high wug");
        assertEquals(gitletFast("checkout", "branch3"), "Cannot do this command until the merge conflict has been resolved.");
        File conflict= new File(TESTING_DIR +"file1.txt.conflicted");
        assertTrue(conflict.exists()); //conflicted file exists because file was modified in both branches after split point
    }
      
    /**
     * Another test for merge
     * This tests that files modified in the given branch but not current branch override the current branch's versions.
     */
    @Test
    public void testBasicMerge3() {
    	//Tests if given branch file overrides current branch's file 
        //if changed after the split point
    	setUp();
        gitletFast("init");
        String wugFileName = TESTING_DIR + "file1.txt";
        createFile(wugFileName, "I am a wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "cool");
        gitletFast("branch", "branch2");
        gitletFast("checkout", "branch2");
        writeFile(wugFileName, "I am a better wug");
        gitletFast("add", wugFileName);
        gitletFast("commit", "weird commit");
        gitletFast("checkout", "master");
        gitletFast("merge", "branch2");
        
        assertEquals(getText(wugFileName), "I am a better wug"); 
    }
    
    /**
     * Rebase test
     * - new copy of branch tracks all files that only exist in given branch but not current branch
     * - propagation happens if file is modified in given branch but not current branch
     * - files modified in current branch takes precedence over files modified in given branch (propagation stops)
     * This is also test requirement 8 and test requirement 9
     */
    @Test
    public void testRebase() {
    	setUp();
    	//initial files a.txt with b.txt with A and B as content
    	gitletFast("init");
    	String AName = TESTING_DIR + "a.txt";
        createFile(AName, "A");
        String BName = TESTING_DIR + "b.txt";
        createFile(BName, "B");
        gitletFast("add", BName);
        gitletFast("add", AName);
        gitletFast("commit", "first commit; added a and b"); //this is the split point
        //create branch and checkout; modified files in this branch
        gitletFast("branch", "king");
        gitletFast("checkout", "king");
        writeFile(BName, "new B in king");
        gitletFast("add", BName);
        gitletFast("commit", "changed b in king");
        writeFile(AName, "new A in king");
        gitletFast("add", AName);
        gitletFast("commit", "changed a in king");
        //go back to master and modified files
        gitletFast("checkout", "master");
        writeFile(AName, "new A in master");
        gitletFast("add", AName);
        String CName = TESTING_DIR + "c.txt";
        createFile(CName, "C");
        gitletFast("add", CName);
        gitletFast("commit", "changed a, added c in master");
        //go back to king to rebase
        gitletFast("checkout", "king");
        gitletFast("rebase", "master"); //current: king; given: master
        //end products
        assertEquals(getText(AName), "new A in king");
        assertEquals(getText(BName), "new B in king");
        assertEquals(getText(CName), "C");
        //go back to master to check file content; propagation check
        //also test requirement 9
        gitletFast("checkout", "master");
        assertEquals(getText(AName), "new A in master");
        assertEquals(getText(BName), "B");
        assertEquals(getText(CName), "C");
        //different IDs
        gitletFast("find", "changed b in king"); //should print 2 and 5
        gitletFast("find", "changed a in king"); //should print 3 and 6
        
        //go back to king for log test
        //also test requirement 8
        gitletFast("checkout", "king");
        String logResults = gitletFast("log");
        assertArrayEquals(new String[] {"changed a in king", "changed b in king", "changed a, added c in master", "first commit; added a and b", "initial commit"}, 
        		extractCommitMessages(logResults));
    }
     
    /**
     * THE FOLLOWING TESTS SATISFY THE BASIC PROJECT REQUIREMENT FOR JUNIT TESTS
     */
     
    /**
     * Test that a file that has been committed at some point can be restored 
     * by checking it out from a commit that tracks that version of the file, even 
     * if the file wasn't staged prior to that commit.
     */
    @Test
    public void testRequirement2(){
        setUp();
        gitletFast("init");
         
        String wugFileName1 = TESTING_DIR + "file1.txt";
        createFile(wugFileName1, "hi");
        gitletFast("add", wugFileName1);
        gitletFast("commit", "first commit");
        String wugFileName2 = TESTING_DIR + "file2.txt";
        createFile(wugFileName2, "yo");
        gitletFast("add", wugFileName2);
        writeFile(wugFileName1, "hiWug!"); //wugFileName1 is modified
        gitletFast("commit", "Second commit"); //Commit 2 does not stage wugFileName1. However, the file is still tracked
        assertEquals(getText(wugFileName1),"hiWug!");
        gitletFast("checkout", "2", wugFileName1);
        assertEquals(getText(wugFileName1),"hi"); //wugFileName1 reverts to the old version in commit 1 (where it was last tracked)
        assertEquals(getText(wugFileName2),"yo");
    }
     
    /**
     * Test Requirement 3:
     * Test that you can checkout [id] [file] from an arbitrary commit in the graph (even one in another branch).
     * (We test a case where there are 3 branches which contain the same single fileName)
     */
     
    @Test
    public void testRequirement3() {
        setUp();
        gitletFast("init");
        String branch1 = "master";
        String branch2 = "Branch 2";
        String branch3 = "Branch 3";
         
        String wugFileName = TESTING_DIR + "file1.txt";
         
        //Master Branch
        createFile(wugFileName, "Branch 1");
        gitletFast("add", wugFileName);
        gitletFast("commit", "first commit");
         
        //Branch 2
        gitletFast("branch", branch2);
         
        //Branch 3
        gitletFast("branch", branch3);
         
        gitletFast("checkout", branch1);
        writeFile(wugFileName, "This is still Branch 1");
        gitletFast("add", wugFileName);
        gitletFast("commit", "Second commit, Branch 1");
 
        gitletFast("checkout", branch2);
        writeFile(wugFileName, "Branch 2");
        gitletFast("add", wugFileName);
        gitletFast("commit", "Third commit, Branch 2");
        writeFile(wugFileName, "This is still Branch 2");
        gitletFast("add", wugFileName);
        gitletFast("commit", "Fourth commit, Branch 2");
         
        gitletFast("checkout", branch3);
        writeFile(wugFileName, "Branch 3");
        gitletFast("add", wugFileName);
        gitletFast("commit", "Fifth commit, Branch 3");
        writeFile(wugFileName, "This is still Branch 3");
        gitletFast("add", wugFileName);
        gitletFast("commit", "Sixth commit, Branch 3");
         
        gitletFast("checkout", branch1);
         
        //Now we test that we can checkout every file for any arbitrary commit in the different branches
        gitletFast("checkout", "1", wugFileName);
        assertEquals(getText(wugFileName), "Branch 1");
        gitletFast("checkout", "2", wugFileName);
        assertEquals(getText(wugFileName), "This is still Branch 1");
         
        gitletFast("checkout", "3", wugFileName);
        assertEquals(getText(wugFileName), "Branch 2");
        gitletFast("checkout", "4", wugFileName);
        assertEquals(getText(wugFileName), "This is still Branch 2");
         
        gitletFast("checkout", "5", wugFileName);
        assertEquals(getText(wugFileName), "Branch 3");
        gitletFast("checkout", "6", wugFileName);
        assertEquals(getText(wugFileName), "This is still Branch 3");
    }
 
     
    /**
     * Test Requirement 4
     * Test that resetting backward appropriately changes the output of log.
     */
     
    @Test
    public void testRequirement4() {
        setUp();
        gitletFast("init");
        String wugFileName = TESTING_DIR + "file1.txt";
        createFile(wugFileName, "hi ALL");
        gitletFast("add", wugFileName);
        gitletFast("commit", "first commit");
         
        writeFile(wugFileName, "hi QUAN");
        gitletFast("add", wugFileName);
        gitletFast("commit", "second commit");
         
        writeFile(wugFileName, "hi KYLE");
        gitletFast("add", wugFileName);
        gitletFast("commit", "third commit");
         
        String currentLogContent = gitletFast("log");
        assertArrayEquals(new String[] { "third commit", "second commit", "first commit", "initial commit" },
                extractCommitMessages(currentLogContent));
         
        gitletFast("reset", "1");
        String resetLogContent = gitletFast("log");
        assertArrayEquals(new String[] {"first commit", "initial commit" },
                extractCommitMessages(resetLogContent));
 
    }
    
    /**
     * Test Requirement 5
     * Test that log adjusts appropriately when switching from one branch to another.
     */
    @Test
    public void testRequirement5() {
    	//set up and first commits
    	setUp();
    	gitletFast("init");
        String file1 = TESTING_DIR + "file1.txt";
        createFile(file1, "first file");
        gitletFast("add", file1);
        gitletFast("commit", "created first file");
        writeFile(file1, "change to first file");
        gitletFast("add", file1);
        gitletFast("commit", "changed at split point");
        //create new branch and make last commit in master
        gitletFast("branch", "otherBranch");
        writeFile(file1, "this file is changed again");
        gitletFast("add", file1);
        gitletFast("commit", "changed file in master");
        //checkout branch and make changes there
        gitletFast("checkout", "otherBranch");
        writeFile(file1, "last change probably");
        gitletFast("add", file1);
        gitletFast("commit", "changed file in other");
        String commitLogOther = gitletFast("log");
        //otherBranch's commit log
        assertArrayEquals(new String[] {"changed file in other", "changed at split point",  "created first file", "initial commit" },
                extractCommitMessages(commitLogOther));
        //master's commit log
        gitletFast("checkout", "master");
        String commitLogMaster = gitletFast("log");
        assertArrayEquals(new String[] {"changed file in master", "changed at split point",  "created first file", "initial commit" },
                extractCommitMessages(commitLogMaster));
    }
    
    /**
     * Test Requirement 6
     * Test that merge will generate a .conflicted file if a file has been modified in both branches since the split point.
     */
    @Test
    public void testRequirement6() {
    	setUp();
        gitletFast("init");
        String wugFile = TESTING_DIR + "wug.txt";
        createFile(wugFile, "Original wug");
        String secretWug = TESTING_DIR + "secretWug.txt";
        createFile(secretWug, "Secret wug");
        gitletFast("add", wugFile);
        gitletFast("add", secretWug);
        gitletFast("commit", "default branch commit");
        gitletFast("branch", "king");
        
        gitletFast("checkout", "king");
        writeFile(wugFile, "the king's wug");
        writeFile(secretWug, "the king's spy");
        gitletFast("add", wugFile);
        gitletFast("add", secretWug);
        gitletFast("commit", "first commit of king");
        
        gitletFast("checkout", "master");
        writeFile(wugFile, "the master's wug");
        writeFile(secretWug, "the master's spy");
        gitletFast("add", wugFile);
        gitletFast("add", secretWug);
        gitletFast("commit", "changed on master");
        gitlet("merge", "king"); //current branch: master, given: branch3
        assertEquals(getText(wugFile), "the master's wug");
        assertEquals(getText(secretWug), "the master's spy");
        assertEquals(gitletFast("checkout", "king"), "Cannot do this command until the merge conflict has been resolved.");
        File conflictedWug = new File(TESTING_DIR +"wug.txt.conflicted");
        File conflictedSecret = new File(TESTING_DIR + "secretWug.txt.conflicted");
        assertTrue(conflictedWug.exists()); //conflicted file exists because file was modified in both branches after split point
    	assertTrue(conflictedSecret.exists());
    }
    
    /**
     * Test Requirement 7
     * Test that merge will commit with files from the other branch if those files had been modified in the other branch but not in the current branch since the split point.
     */
    @Test
    public void testRequirement7() {
    	setUp();
        gitletFast("init");
        String wugFile = TESTING_DIR + "wug.txt";
        createFile(wugFile, "Original wug");
        String secretWug = TESTING_DIR + "secretWug.txt";
        createFile(secretWug, "Secret wug");
        gitletFast("add", wugFile);
        gitletFast("add", secretWug);
        gitletFast("commit", "default branch commit");
        gitletFast("branch", "king");
        
        gitletFast("checkout", "king");
        writeFile(wugFile, "the king's wug");
        writeFile(secretWug, "the king's spy");
        gitletFast("add", wugFile);
        gitletFast("add", secretWug);
        gitletFast("commit", "first commit of king");
        
        gitletFast("checkout", "master");
        writeFile(secretWug, "the master's spy");
        gitletFast("add", secretWug);
        gitletFast("commit", "changed secretWug on master");
        //Note: wug.txt hasn't been changed on master since split point but secretWug.txt has
        
        gitlet("merge", "king"); //current branch: master, given: branch3
        assertEquals(getText(wugFile), "the king's wug"); //other branch's modified wug
        assertEquals(getText(secretWug), "the master's spy"); //this branch's modifed secretWug
        assertEquals(gitletFast("checkout", "king"), "Cannot do this command until the merge conflict has been resolved.");
        //there should be a conflict with secretWug but not wug
        File conflictedWug = new File(TESTING_DIR +"wug.txt.conflicted");
        File conflictedSecret = new File(TESTING_DIR + "secretWug.txt.conflicted");
        assertFalse(conflictedWug.exists()); //conflicted file exists because file was modified in both branches after split point
    	assertTrue(conflictedSecret.exists());
    }
    
    
    /**
     * Calls a gitlet command using the terminal.
     * 
     * Warning: Gitlet will not print out anything _while_ it runs through this
     * command, though it will print out things at the end of this command. It
     * will also return this as a string.
     * 
     * The '...' syntax allows you to pass in an arbitrary number of String
     * arguments, which are packaged into a String[].
     */
    private static String gitlet(String... args) {
 
        String[] commandLineArgs = new String[args.length + 2];
        commandLineArgs[0] = "java";
        commandLineArgs[1] = "Gitlet";
        for (int i = 0; i < args.length; i++) {
            commandLineArgs[i + 2] = args[i];
        }
        String results = command(commandLineArgs);
        System.out.println(results);
        return results.trim();
    }
 
    /**
     * Another convenience method for calling Gitlet's main. This calls Gitlet's
     * main directly, rather than through the terminal. This is slightly
     * cheating the concept of end-to-end tests. But, this allows you to
     * actually use the debugger during the tests, which you might find helpful.
     * It's also a lot faster.
     * 
     * Warning: Like the other version of this method, Gitlet will not print out
     * anything _while_ it runs through this command, though it will print out
     * things at the end of this command. It will also return what it prints as
     * a string.
     */
    private static String gitletFast(String... args) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
        try {
            /*
             * Below we change System.out, so that when you call
             * System.out.println(), it won't print to the screen, but will
             * instead be added to the printingResults object.
             */
            System.setOut(new PrintStream(printingResults));
            Gitlet.main(args);
        } finally {
            /*
             * Restores System.out (So you can print normally).
             */
            System.setOut(originalOut);
        }
        System.out.println(printingResults.toString());
        return printingResults.toString().trim();
    }
 
    /**
     * Returns the text from a standard text file.
     */
    private static String getText(String fileName) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }
 
    /**
     * Creates a new file with the given fileName and gives it the text
     * fileText.
     */
    private static void createFile(String fileName, String fileText) {
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFile(fileName, fileText);
    }
 
    /**
     * Replaces all text in the existing file with the given text.
     */
    private static void writeFile(String fileName, String fileText) {
        FileWriter fw = null;
        try {
            File f = new File(fileName);
            fw = new FileWriter(f, false);
            fw.write(fileText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * Deletes the file and all files inside it, if it is a directory.
     * 
     * @throws IOException
     */
    private static void recursiveDelete(File d) throws IOException {
        if (d.isDirectory()) {
            for (File f : d.listFiles()) {
                recursiveDelete(f);
            }
        }
        if (!d.delete()) {
            throw new IOException("Failed to delete file " + d.getPath());
        }
    }
 
    /**
     * Returns an array of commit messages associated with what log has printed
     * out.
     */
    private static String[] extractCommitMessages(String logOutput) {
        String[] logChunks = logOutput.split("===");
        int numMessages = logChunks.length - 1;
        String[] messages = new String[numMessages];
        for (int i = 0; i < numMessages; i++) {
            String[] logLines = logChunks[i + 1].split(LINE_SEPARATOR);
            messages[i] = logLines[3];
        }
        return messages;
    }
 
    /**
     * Executes the given command on the terminal, and return what it prints out
     * as a string.
     * 
     * Example: If you want to call the terminal command `java Gitlet add
     * wug.txt` you would call the method like so: `command("java", "Gitlet",
     * "add", "wug.txt");` The `...` syntax allows you to pass in however many
     * strings you want.
     */
    private static String command(String... args) {
        try {
            StringBuilder results = new StringBuilder();
            Process p = Runtime.getRuntime().exec(args);
            p.waitFor();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    results.append(line).append(System.lineSeparator());
                }
                return results.toString();
            }
        } catch (IOException e) {
            return e.getMessage();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }
}