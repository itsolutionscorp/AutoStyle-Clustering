import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

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
     * Tests the functionality of the merge method.
     * Tests the general conflicted state.
     */
    @Test
    public void testMerge1 () {
        //General test case with merge conflict.
        //Setup system.
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit","test1");
        gitlet("branch","branch1");
        gitlet("checkout","branch1");
        writeFile(TESTING_DIR + "file2.txt", "Wugs are great");
        gitlet("add", TESTING_DIR + "file2.txt");
        gitlet("commit", "test2");
        writeFile(TESTING_DIR + "dir1/file2.txt", "I love wugs.");
        gitlet("add", TESTING_DIR + "dir1/file2.txt");
        gitlet("commit", "test3");
        gitlet("checkout","master");
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("wug3\n"));
        writeFile(TESTING_DIR + "dir1/file2.txt", "I love wugs.");
        gitlet("add", TESTING_DIR + "dir1/file2.txt");
        gitlet("commit", "test4");
        writeFile(TESTING_DIR + "dir2/dir1/file2.txt", "omgwug");
        gitlet("add", TESTING_DIR + "dir2/dir1/file2.txt");
        gitlet("commit", "test5");
        //Test Merge Result.
        gitletFast("merge","branch1");
        //assertTrue(gitlet("merge", "branch1").equals("Encountered a merge conflict."));
        assertTrue(gitlet("rebase", "branch1").equals("Cannot do this command until the merge conflict has been resolved."));
        assertTrue(gitlet("checkout", "branch1").equals("Cannot do this command until the merge conflict has been resolved."));
        gitlet("commit", "test6");
        assertTrue((new File(TESTING_DIR + "dir1/file2.txt.conflicted")).exists());
        assertTrue(!gitlet("checkout", TESTING_DIR + "dir1/file2.txt.conflicted").equals(
                "File does not exist in the most recent commit, or no such branch exists."));
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("I hate wugs."));
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("Wugs are great"));
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("I love wugs."));
        assertTrue(getText(TESTING_DIR + "dir2/dir1/file2.txt").equals("omgwug"));
        assertTrue(getText(TESTING_DIR + "dir3/file4.txt").equals("wug5\n"));
    }

    /**
     * Tests adding and removing files with merge.
     * 
     */
    @Test
    public void testMerge2 () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit","test1");
        gitlet("branch","branch1");
        gitlet("checkout","branch1");
        writeFile(TESTING_DIR + "file2.txt", "Wugs are great");
        gitlet("add", TESTING_DIR + "file2.txt");
        gitlet("remove", TESTING_DIR + "dir3/file4.txt");
        gitlet("commit", "test2");
        gitlet("remove", TESTING_DIR + "dir1/file2.txt");
        writeFile(TESTING_DIR + "file3.txt", "wug6\n");
        gitlet("add", TESTING_DIR + "file3.txt");
        gitlet("commit", "test3");
        gitlet("checkout", "master");
        writeFile(TESTING_DIR + "file2.txt", "wugwugwug");
        gitlet("add", TESTING_DIR + "file2.txt");
        gitlet("commit", "test4");
        writeFile(TESTING_DIR + "dir2/dir1/file2.txt","omgwug");
        gitlet("add", TESTING_DIR + "dir2/dir1/file2.txt");
        gitlet("commit", "test5");
        gitlet("merge", "branch1");
        assertTrue(getText(TESTING_DIR + "file3.txt").equals("wug6\n"));
        assertTrue(getText(TESTING_DIR + "file2.txt.conflicted").equals("Wugs are great"));
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wugwugwug"));
        assertTrue(gitlet("checkout","dir1/file2.txt").equals(
                "File does not exist in the most recent commit, or no such branch exists."));
        assertTrue(gitlet("checkout","dir3/file4.txt").equals(
                "File does not exist in the most recent commit, or no such branch exists."));
    }

    /**
     * Test for easy, successful merge.
     */
    @Test
    public void testEasyMerge() {
        gitlet("init");
        String initial = "initial commit";
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);
        String c1 = "added wug";
        gitlet("commit", c1);
        gitlet("branch", "newBranch");
        gitlet("checkout", "newBranch");
        String wugFileName2 = TESTING_DIR + "wug2.txt";
        String wugText2 = "This is NOT a wug.";
        createFile(wugFileName2, wugText2);
        gitlet("add", wugFileName2);
        String c2 = "added NOT a wug";
        gitlet("commit", c2);
        gitlet("checkout", "master");
        String c3 = "Merged master with newBranch.";
        gitlet("merge", "newBranch");
        assertArrayEquals(new String[] {initial, c1, c2, c3}, extractCommitMessages(gitlet("global-log")));
    }

    /**
     * Test for merge conflicts. Make sure a .conflict file is created
     */
    @Test
    public void testMergeConflicts() {
        gitlet("init");
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");
        gitlet("branch", "newBranch");
        gitlet("checkout", "newBranch");
        String wugText2 = "This is NOT a wug.";
        createFile(wugFileName, wugText2);
        gitlet("add", wugFileName);
        gitlet("commit", "added NOT a wug");
        gitlet("checkout", "master");
        String wugText3 = "This is SO a wug.";
        createFile(wugFileName, wugText3);
        gitlet("add", wugFileName);
        gitlet("commit", "added SO a wug");
        gitlet("merge", "newBranch");
        System.out.println(extractCommitMessages(gitlet("global-log")));
        assertEquals(wugText2, getText(wugFileName + ".conflicted"));
    }


    /**
     * 1) Tests that commit correctly backs up files in new backup directories.
     * 2) Tests correct error messages are thrown.
     */
    @Test
    public void testCommit () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test1");
        assertTrue((new File(GITLET_DIR + "1/" + TESTING_DIR + "file1.txt")).exists());
        assertTrue(gitlet("commit", "test2").equals("No changes added to the commit."));
        gitlet("add", TESTING_DIR + "file1.txt");
        assertTrue(gitlet("commit").equals("Please enter a commit message."));
    }

    /**
     * Tests that status correctly prints the status of multiple branches, the staging directory
     * and the files to be removed.
     */
    @Test
    public void testStatus () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test1");
        gitlet("branch", "branch1");
        writeFile(TESTING_DIR + "file1.txt", "I love wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("rm", TESTING_DIR + "file2.txt");
        assertTrue(gitlet("status").equals("=== Branches ===\n*master\nbranch1\n\n=== Staged Files ===\n"
                + TESTING_DIR + "file1.txt" + "\n\n=== Files Marked for Untracking ===\n" + 
                TESTING_DIR + "file2.txt"));
    }

    /**
     * Tests that checking out a file name will restore the version of the file
     * from the previous commit. Involves init, add, commit, and checkout.
     * Checks checking out file from a commit ID.
     */
    @Test
    public void testCheckout1 () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test1");
        gitlet("checkout","0",TESTING_DIR + "file1.txt");
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("wug\n"));
        assertTrue(gitlet("checkout","0",TESTING_DIR + "wug.txt").equals(
                "File does not exist in that commit."));
        assertTrue(gitlet("checkout","14",TESTING_DIR + "file1.txt").equals(
                "No commit with that id exists."));
    }

    /**
     * Test checking out a file from most recent commit.
     */
    @Test
    public void testCheckout2 () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test1");
        writeFile(TESTING_DIR + "file1.txt", "I love wugs.");
        gitlet("checkout",TESTING_DIR + "file1.txt");
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("I hate wugs."));
        assertTrue(gitlet("checkout", TESTING_DIR + "wug.txt").equals(
                "File does not exist in the most recent commit, or no such branch exists."));
    }

    /**
     * Test checking out a branch.
     */
    @Test
    public void testCheckout3 () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test1");
        gitlet("branch","branch1");
        writeFile(TESTING_DIR + "file1.txt", "I love wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test2");
        gitlet("checkout", "branch1");
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("I hate wugs."));
        writeFile(TESTING_DIR + "file1.txt", "Wugs are meh.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test3");
        gitlet("checkout", "master");
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("I love wugs."));
        gitlet("status");
        assertTrue(gitlet("checkout", "master").equals("No need to checkout the current branch."));
        assertTrue(gitlet("checkout", "master2").equals(
                "File does not exist in the most recent commit, or no such branch exists."));
    }

    /**
     * Tests the functionality of Rebase.
     * 1) Checks the branch structure changes as desired.
     * 2) Tests that files propagate through the replay.
     * THIS IS FOR A GENERAL CASE. SEE OTHER TESTS FOR SPECIFIC ERROR MESSAGES ETC.
     */
    @Test
    public void testRebase1() {
        //General Case Test
        //Setting up General Case.
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs.");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit","test1");
        gitlet("add", TESTING_DIR + "file2.txt");
        gitlet("commit","test2");
        gitlet("branch","branch1");
        gitlet("checkout","branch1");
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wug2\n"));
        writeFile(TESTING_DIR + "dir1/file2.txt", "I love wugs.");
        gitlet("add", TESTING_DIR + "dir1/file2.txt");
        gitlet("commit","test3");
        gitlet("checkout","master");
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wug2\n"));
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("wug3\n"));
        writeFile(TESTING_DIR + "file2.txt", "Wugs are great");
        gitlet("add", TESTING_DIR + "file2.txt");
        gitlet("commit","test4");
        writeFile(TESTING_DIR + "dir3/file4.txt", "wugwugwug");
        gitlet("add", TESTING_DIR + "dir3/file4.txt");
        gitlet("commit","test5");
        gitlet("checkout","branch1");
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wug2\n"));
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("I love wugs."));
        writeFile(TESTING_DIR + "dir2/dir1/file2.txt","omgwug");
        gitlet("add",TESTING_DIR + "dir2/dir1/file2.txt");
        writeFile(TESTING_DIR + "file2.txt", "wugyou");
        gitlet("add",TESTING_DIR + "file2.txt");
        gitlet("commit", "test6");
        //Checking that rebase worked as intended.
        gitlet("rebase", "master");
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("I hate wugs."));
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wugyou"));
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("I love wugs."));
        assertTrue(getText(TESTING_DIR + "dir2/dir1/file2.txt").equals("omgwug"));
        assertTrue(getText(TESTING_DIR + "dir3/file4.txt").equals("wugwugwug"));
        gitlet("checkout","7",TESTING_DIR + "file2.txt");
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("Wugs are great"));
        gitlet("checkout","7",TESTING_DIR + "dir2/dir1/file2.txt");
        assertTrue(getText(TESTING_DIR + "dir2/dir1/file2.txt").equals("wug4\n"));
        gitlet("checkout","master");
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("wug3\n"));
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("Wugs are great"));
        assertTrue(getText(TESTING_DIR + "file1.txt").equals("I hate wugs."));
        assertTrue(getText(TESTING_DIR + "dir2/dir1/file2.txt").equals("wug4\n"));
        assertTrue(getText(TESTING_DIR + "dir3/file4.txt").equals("wugwugwug"));
        gitlet("checkout", "4", TESTING_DIR + "dir3/file4.txt");
        assertTrue(getText(TESTING_DIR + "dir3/file4.txt").equals("wug5\n"));
        gitlet("checkout", "3", TESTING_DIR + "dir1/file2.txt");
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("I love wugs."));
        gitlet("checkout", "6", TESTING_DIR + "file2.txt");
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wugyou"));
        gitlet("checkout", "2", TESTING_DIR + "file2.txt");
        assertTrue(getText(TESTING_DIR + "file2.txt").equals("wug2\n"));
        gitlet("checkout", "2", TESTING_DIR + "dir1/file2.txt");
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("wug3\n"));
    }

    /**
     * Tests the functionality of Rebase for 2 specific linear cases.
     * 1) Current branch head pointer is ahead of the given argument => Error.
     * 2) Current branch head pointer is behind the given argument:
     *    Then the current branch head should be moved to the given argument,
     *    The file contents in the working directory should be reset to the given argument,
     *    No copies of Backup nodes should be made!
     * 3) At the end test the other error messages.
     */
    @Test
    public void testRebase2 () {
        gitlet("init");
        writeFile(TESTING_DIR + "file1.txt", "I hate wugs");
        gitlet("add", TESTING_DIR + "file1.txt");
        gitlet("commit", "test1");
        writeFile(TESTING_DIR + "file2.txt", "Wugs are great");
        gitlet("add", TESTING_DIR + "file2.txt");
        gitlet("commit", "test2");
        gitlet("branch", "branch1");
        writeFile(TESTING_DIR + "dir1/file2.txt", "I love wugs.");
        gitlet("add", TESTING_DIR + "dir1/file2.txt");
        gitlet("commit", "test3");
        writeFile(TESTING_DIR + "dir2/dir1/file2.txt", "omgwug");
        gitlet("add", TESTING_DIR + "dir2/dir1/file2.txt");
        gitlet("commit", "test4");
        assertTrue(gitlet("rebase", "branch1").equals("Already up-to-date."));
        gitlet("checkout", "branch1");
        gitlet("rebase", "master");
        assertTrue(getText(TESTING_DIR + "dir2/dir1/file2.txt").equals("omgwug"));
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("I love wugs."));
        gitlet("reset", "branch1");
        assertTrue(getText(TESTING_DIR + "dir2/dir1/file2.txt").equals("omgwug"));
        assertTrue(getText(TESTING_DIR + "dir1/file2.txt").equals("I love wugs."));
        assertTrue(gitlet("rebase", "branch1").equals("Cannot rebase a branch onto itself."));
        assertTrue(gitlet("rebase", "wug").equals("A branch with that name does not exist."));
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * Tests that the global-log function works as specified to the format in the spec.
     * Involves init, add, commit, and global-log
     */
    @Test
    public void testGlobalLog() {

        gitlet("init");
        String initial = "initial commit";

        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);

        String commitMessage1 = "first commit";
        gitlet("commit", commitMessage1);

        String wugFileName2 = TESTING_DIR + "wug2.txt";
        String wugText2 = "This is a wug2.";
        createFile(wugFileName2, wugText2);
        gitlet("add", wugFileName2);

        String commitMessage2 = "second commit";
        gitlet("commit", commitMessage2);

        String wugFileName3 = TESTING_DIR + "wug3.txt";
        String wugText3 = "This is a wug3.";
        createFile(wugFileName3, wugText3);
        gitlet("add", wugFileName3);

        String commitMessage3 = "third commit";
        gitlet("commit", commitMessage3);

        String logContent = gitlet("global-log");
        assertArrayEquals(new String[] {initial, commitMessage1, commitMessage2, commitMessage3}, extractCommitMessages(logContent));
    }

    /**
     * Test that the find function is able to display the id of a commit given a message.
     * Involves init, add, commit, and find.
     */
    @Test
    public void testFind() {
        gitlet("init");
        //String initial = "initial commit";

        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);

        String commitMessage1 = "first commit";
        gitlet("commit", commitMessage1);

        String wugFileName2 = TESTING_DIR + "wug2.txt";
        String wugText2 = "This is a wug2.";
        createFile(wugFileName2, wugText2);
        gitlet("add", wugFileName2);

        String commitMessage2 = "second commit";
        gitlet("commit", commitMessage2);

        String wugFileName3 = TESTING_DIR + "wug3.txt";
        String wugText3 = "This is a wug3.";
        createFile(wugFileName3, wugText3);
        gitlet("add", wugFileName3);

        String commitMessage3 = "third commit";
        gitlet("commit", commitMessage3);

        assertEquals(gitlet("find", "second commit"), "2");
        assertEquals(gitlet("find", "initial commit"), "0");
        assertEquals(gitlet("find", "first commit"), "1");
        assertEquals(gitlet("find", "wug wug wug"), "null");
    }

    /**
     * Test that the branch function created a new pointer pointing to the current node.
     * Involves init and branch.
     * 
     */
    @Test
    public void testBranch() {
        gitlet("init");
        String initial = "initial commit";

        gitlet("branch", "newbranch");
        assertEquals(gitlet("branch", "newbranch"), "A branch with that name already exists.");
    }

    /**
     * Tests that the  rm-branch removes a branch.
     * Involves init, branch, and rm-branch.
     */
    @Test
    public void testRemoveBranch() {
        gitlet("init");
        String initial = "initial commit";

        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);

        String commitMessage1 = "first commit";
        gitlet("commit", commitMessage1);

        String wugFileName2 = TESTING_DIR + "wug2.txt";
        String wugText2 = "This is a wug2.";
        createFile(wugFileName2, wugText2);
        gitlet("add", wugFileName2);

        String commitMessage2 = "second commit";
        gitlet("commit", commitMessage2);

        String wugFileName3 = TESTING_DIR + "wug3.txt";
        String wugText3 = "This is a wug3.";
        createFile(wugFileName3, wugText3);
        gitlet("add", wugFileName3);

        String commitMessage3 = "third commit";
        gitlet("commit", commitMessage3);

        gitlet("branch", "newbranch");
        gitlet("rm-branch", "newbranch");

        gitlet("branch", "secondbranch");
        gitlet("checkout", "secondbranch");
        assertEquals(gitlet("rm-branch", "secondbranch"), "Cannot remove the current branch.");

        assertEquals(gitlet("rm-branch", "wug wug wug"), "A branch with that name does not exist.");
    }

    @Test
    public void testReset() {
        gitlet("init");
        String initial = "initial commit";

        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);

        String commitMessage1 = "first commit";
        gitlet("commit", commitMessage1);

        String wugFileName2 = TESTING_DIR + "wug2.txt";
        String wugText2 = "This is a wug2.";
        createFile(wugFileName2, wugText2);
        gitlet("add", wugFileName2);

        String commitMessage2 = "second commit";
        gitlet("commit", commitMessage2);

        String wugFileName3 = TESTING_DIR + "wug3.txt";
        String wugText3 = "This is a wug3.";
        createFile(wugFileName3, wugText3);
        gitlet("add", wugFileName3);

        String commitMessage3 = "third commit";
        gitlet("commit", commitMessage3);

        gitlet("reset", "0");
        gitlet("log");
        gitlet("reset", "3");
        gitlet("log");
        assertEquals(gitlet("reset", "4"), "No commit with that id exists.");
    }

    //////////////////////////////////////////////////////////////////////

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
     * Executes the given command on the terminal, and return what it prints out
     * as a string.
     * 
     * Example: If you want to call the terminal command `java Gitlet add
     * wug.txt` you would call the method like so: `command("java", "Gitlet",
     * "add", "wug.txt");` The `...` syntax allows you to pass in however many
     * strings you want.
     */
    private static String command (String... args) {
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
     * Deletes existing gitlet system, resets the folder that stores files used
     * in testing.
     * 
     * This method runs before every @Test method. This is important to enforce
     * that all tests are independent and do not interact with one another.
     */
    @Before
    public void setUp() {
        long start = System.currentTimeMillis();
        File f = new File(GITLET_DIR);
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
        f.mkdir();
        try {
            PrintWriter pw1 = new PrintWriter(new File(TESTING_DIR + "file1.txt"));
            pw1.println("wug");
            pw1.close();
            PrintWriter pw2 = new PrintWriter(new File(TESTING_DIR + "file2.txt"));
            pw2.println("wug2");
            pw2.close();
            File dir1 = new File (TESTING_DIR + "dir1");
            dir1.mkdir();
            File dir2 = new File (TESTING_DIR + "dir2");
            dir2.mkdir();
            File dir3 = new File (TESTING_DIR + "dir3");
            dir3.mkdir();
            File dir4 = new File (TESTING_DIR + "dir2/dir1");
            dir4.mkdir();
            PrintWriter pw3 = new PrintWriter(new File(TESTING_DIR + "dir1/file2.txt"));
            pw3.println("wug3");
            pw3.close();
            PrintWriter pw4 = new PrintWriter(new File(TESTING_DIR + "dir2/dir1/file2.txt"));
            pw4.println("wug4");
            pw4.close();
            PrintWriter pw5 = new PrintWriter(new File(TESTING_DIR + "dir3/file4.txt"));
            pw5.println("wug5");
            pw5.close();
        }
        catch (Exception e) {
            throw new RuntimeException ("Could not create files");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @After
    public void tearDown() {
        try {
            recursiveDelete(new File(TESTING_DIR));
        }
        catch (Exception e) {

        }
    }
}

