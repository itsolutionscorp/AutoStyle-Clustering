import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
     * Tests that log after rebase on a single layer of commits  conforms to the format in the spec.
     * Involves init, add, commit, rebase,checkout  and log.
    * Tests that the output of log after a rebase includes the commit messages from both branches involved in the rebase.
     */

//  /**These commented JUnit test cases require the instance variables of my Gitlet to be set to public.
//       *We set them private because we believed them to be correct style, but we also wrote
//      *some JUnit tests.
//      */
//    /**
//     * Tests a case of remove where the file is in the staging area and you want to 
//     * remove it from the staging area.
//     */
//    @Test
//    public void testRm(){
//        Gitlet git = new Gitlet();
//        File newfile = new File("./.gitlet/StagingArea/" + "a.txt");
//        git.myStagingArea.put("a.txt", newfile);;
//        assertEquals(newfile,git.myStagingArea.get("a.txt"));
//        try{
//            git.rm("a.txt");
//        } catch (Exception e){}
//        assertFalse(git.myStagingArea.containsKey("a.txt"));
//        assertEquals(git.myStagingArea.get("a.txt"),null);
//        try{
//            git.add("a.txt");
//        } catch (Exception e){}
//        git.commit("hello");
//    }
//    
//    @Test
//    /**
//     * Tests Branch to see if calling the method correctly adds a branch to the HashMap,
//     * and maps the corresponding commit to that branch.
//     */
//    public void testBranch(){
//        Gitlet git = new Gitlet();
//        git.branch("newb");
//        assertTrue(git.myBranches.containsKey("newb"));
//        assertEquals(git.myBranches.get("newb"),git.myCommitsID.get(0));
//    

    @Test
    public void testRebaseLog() {
        gitlet("init");
        String commitMessage1 = "initial commit";
        
        gitlet("branch","newb");
        
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        createFile(aFileName, aText);
        gitlet("add", aFileName);
        String commitMessage2 = "added a";
        gitlet("commit", commitMessage2);
        
        gitlet("checkout", "newb");
        
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(bFileName, bText);
        gitlet("add", bFileName);
        String commitMessage3 = "added b";
        gitlet("commit", commitMessage3);
        
        gitlet("rebase","master");

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
        
        gitlet("checkout", "master");
        String logContent2 = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
                extractCommitMessages(logContent2));
    }
    /**
     * Tests that log after rebase on multiple layers of commits  conforms to the format in the spec.
     * Involves init, add, commit, rebase,checkout  and log.
          * Test that the output of log after a rebase includes the commit messages from both branches involved in the rebase.
     */
    @Test
    public void testRebaseLog2() {
        gitlet("init");
        String commitMessage1 = "initial commit";
        
        gitlet("branch","newb");
        
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        createFile(aFileName, aText);
        gitlet("add", aFileName);
        String commitMessage2 = "added a";
        gitlet("commit", commitMessage2);
        
        String cFileName = TESTING_DIR + "c.txt";
        String cText = "c";
        createFile(cFileName, cText);
        gitlet("add", cFileName);
        String commitMessage3 = "added c";
        gitlet("commit", commitMessage3);
        
        gitlet("checkout", "newb");
        
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(bFileName, bText);
        gitlet("add", bFileName);
        String commitMessage4 = "added b";
        gitlet("commit", commitMessage4);
        
        String dFileName = TESTING_DIR + "d.txt";
        String dText = "d";
        createFile(dFileName, dText);
        gitlet("add", dFileName);
        String commitMessage5 = "added d";
        gitlet("commit", commitMessage5);
        
        gitlet("rebase","master");

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
        
        gitlet("checkout", "master");
        String logContent2 = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent2));
    }
    
    /**
    * Tests that changes in the base branch propagate through the replayed branch during a rebase with one layer of 
    *commits.
    * Involves init, add, commit, rebase,checkout  and log.
     */
@Test
    public void testRebase() {
        gitlet("init");
        String commitMessage1 = "initial commit";

        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        createFile(aFileName, aText);
        gitlet("add", aFileName);
        String commitMessage2 = "added a";
        gitlet("commit", commitMessage2);
        
        gitlet("branch","newb");
        
        aText = "new a";
        writeFile(aFileName, aText);
        gitlet("add", aFileName);
        String commitMessage3 = "added a";
        gitlet("commit", commitMessage3);
        
        gitlet("checkout", "newb");
        
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(bFileName, bText);
        gitlet("add", bFileName);
        String commitMessage4 = "added b";
        gitlet("commit", commitMessage4);
        
        gitlet("rebase","master");
        gitlet("checkout", aFileName);

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
        
        assertEquals(getText(aFileName), "new a");
    }
    
    /**
    * Tests that changes in the base branch propagate through the replayed branch during a rebase with multipe layers 
    * of commits.
    * Involves init, add, commit, rebase,checkout  and log.
     */
    @Test
    public void testRebase2() {
        gitlet("init");
        String commitMessage1 = "initial commit";
        
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        createFile(aFileName, aText);
        gitlet("add", aFileName);
        String commitMessage2 = "added a";
        gitlet("commit", commitMessage2);
        
        gitlet("branch","newb");
        
        aText = "new a";
        writeFile(aFileName, aText);
        gitlet("add", aFileName);
        String commitMessage3 = "added a";
        gitlet("commit", commitMessage3);
        
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(bFileName, bText);
        gitlet("add", bFileName);
        String commitMessage4 = "added b";
        gitlet("commit", commitMessage4);
        
        gitlet("checkout", "newb");
        
        String cFileName = TESTING_DIR + "c.txt";
        String cText = "c";
        createFile(cFileName, cText);
        gitlet("add", cFileName);
        String commitMessage5 = "added c";
        gitlet("commit", commitMessage5);
        
        String dFileName = TESTING_DIR + "d.txt";
        String dText = "d";
        createFile(dFileName, dText);
        gitlet("add", dFileName);
        String commitMessage6 = "added d";
        gitlet("commit", commitMessage6);
        
        gitlet("rebase","master");
        gitlet("checkout", aFileName);
        
        gitlet("rebase","master");
        gitlet("checkout","master");
        gitlet("checkout","newb");

        assertEquals(getText(aFileName), "new a");
    }
    /**
          * Tests that changes in the base branch propagate through the replayed branch during a rebase with one layer of
 *commits. More extensive by changing the files in the directory and making sure that the correct files are written in 
*the directory when checked out (assuming checkout works).
     * Involves init, add, commit, rebase,checkout  and log.
     */
    //    @Test
    public void testRebase3() {
        gitlet("init");
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        String cFileName = TESTING_DIR + "c.txt";
        String cText = "c";
        createFile(aFileName, aText);
        createFile(bFileName, bText);
        createFile(cFileName, cText);
        
        gitlet("add", aFileName);
        gitlet("add", bFileName);
        gitlet("commit", "test");
        gitlet("branch", "new");
        
        writeFile(aFileName, "changed");
        writeFile(bFileName, "change 1");
        gitlet("add", aFileName);
        gitlet("add", bFileName);
        gitlet("commit", "master");
        
        gitlet("checkout", "new");
        gitlet("add", cFileName);
        gitlet("commit", "new1");
        
        writeFile(bFileName, "change 2");
        gitlet("add", bFileName);
        gitlet("commit", "new2");
        gitlet("rebase", "master");
        
        writeFile(cFileName, "c2");
        writeFile(bFileName, "change 3");
        writeFile(aFileName, "a3");
        gitlet("checkout", aFileName);
        gitlet("checkout", bFileName);
        gitlet("checkout", cFileName);
        assertEquals(cText, getText(cFileName));
        assertEquals("change 2", getText(bFileName));
        assertEquals("changed", getText(aFileName));
    }


    /**
     * Tests whether a file can be checked out even if it's not updated in the
     * most recent commit.
     */
   // @Test
    public void testCheckout2() {
        gitlet("init");
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(aFileName, aText);
        createFile(bFileName, bText);
        gitlet("add", aFileName);
        gitlet("add", bFileName);
        gitlet("commit", "a");
        writeFile(bFileName, "not b");
        gitlet("add", aFileName);
        gitlet("commit", "a2");
        gitlet("checkout", bFileName);
        assertEquals(bText, getText(bFileName));
    }
    
    /**
     * Tests whether a file can be checked out correctly from a previous
     * commit.
     */
   // @Test
    public void testCheckout3() {
        gitlet("init");
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        createFile(aFileName, aText);
        gitlet("add", aFileName);
        gitlet("commit", "a");
        
        writeFile(aFileName, "not a");
        gitlet("add", aFileName);
        gitlet("commit", "a2");
        gitlet("checkout", "1", aFileName);
        assertEquals(aText, getText(aFileName));
    }
    
    /**
     * Tests whether log updates correctly after a reset to a previous commit.
     */
   // @Test
    public void testLogWithReset() {
        gitlet("init");
        String commitMessage1 = "initial commit";
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(aFileName, aText);
        createFile(bFileName, bText);
        gitlet("add", aFileName);
        String commitMessage2 = "a";
        gitlet("commit", commitMessage2);
        gitlet("add", bFileName);
        String commitMessage3 = "b";
        gitlet("commit", commitMessage3);
        gitlet("reset", "1");
        String logContents = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, 
                extractCommitMessages(logContents));
    }
    
    /**
     * Tests whether log works correctly after changing branches.
     */
    @Test
    public void testLogWithBranch() {
        gitlet("init");
        String commitMessage1 = "initial commit";
        String commitMessage2 = "master 1";
        String commitMessage3 = "master 2";
        String commitMessage4 = "new 1";
        String commitMessage5 = "new 2";
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        String cFileName = TESTING_DIR + "c.txt";
        String cText = "c";
        String dFileName = TESTING_DIR + "d.txt";
        String dText = "d";
        createFile(aFileName, aText);
        createFile(bFileName, bText);
        createFile(cFileName, cText);
        createFile(dFileName, dText);
        
        gitlet("branch", "new");
        gitlet("add", aFileName);
        gitlet("commit", commitMessage2);
        gitlet("add", bFileName);
        gitlet("commit", commitMessage3);
        gitlet("checkout", "new");
        gitlet("add", cFileName);
        gitlet("commit", commitMessage4);
        gitlet("add", dFileName);
        gitlet("commit", commitMessage5);
        
        gitlet("checkout", "master");
        String logContents = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 }, 
                extractCommitMessages(logContents));
        gitlet("checkout", "new");
        logContents = gitlet("log");
        assertArrayEquals(new String[] { commitMessage5, commitMessage4, commitMessage1 }, 
                extractCommitMessages(logContents));
    }
    
    /**
     * Tests whether merge commits correctly if there are not .conflicted
     * files.
     */
    @Test
    public void testMerge(){
        gitlet("init");
        String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
        String bFileName = TESTING_DIR + "b.txt";
        String bText = "b";
        createFile(aFileName, aText);
        createFile(bFileName, bText);
        gitlet("add", aFileName);
        gitlet("commit", "a");
        gitlet("branch", "new");
        writeFile(aFileName, "not a");
        gitlet("add", aFileName);
        gitlet("commit", "a2");
        gitlet("checkout", "new");
        gitlet("add", bFileName);
        gitlet("commit", "b");
        gitlet("merge", "master");
        String logContents = gitlet("log");
        assertArrayEquals(new String[] {"Merged master with new.", "b", "a", "initial commit"},
                extractCommitMessages(logContents));
        gitlet("checkout", aFileName);
        assertEquals(getText(aFileName), "not a");
    }
    
    @Test
    public void testMergeModifiedOnlyInMergbranch() {
    	gitlet("init");
    	//added : file which is added only in mergbranch
    	//modified : file which is modified only in mergbranch
    	//removed : file which is removed only in mergbranch
    	String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
    	String addedFileName = TESTING_DIR + "added.txt";
    	String addedText = "initial";
    	String modifiedFileName = TESTING_DIR + "modified.txt";
    	String modifiedText = "initial";
    	String removedFileName = TESTING_DIR + "removed.txt";
    	String removedText = "initial";
    	createFile(aFileName, aText);
    	createFile(addedFileName, addedText);
        createFile(modifiedFileName, modifiedText);
        createFile(removedFileName, removedText);
       
        //splitpoint commit : modified.txt, removed.txt
        gitlet("add", modifiedFileName);
        gitlet("add", removedFileName);
        gitlet("commit", "splitpoint");
        gitlet("branch", "newb");
        //master commit : modified.txt, removed.txt, a.txt
        gitlet("add", aFileName);
        gitlet("commit", "in master");
        gitlet("checkout", "newb");
        //newb commit : added.txt, modified.txt(edited)
        gitlet("add", addedFileName);
        writeFile(modifiedFileName, "changed");
        gitlet("add", modifiedFileName);
        gitlet("add", addedFileName);
        gitlet("rm", removedFileName);
        writeFile(addedFileName, "aaaaa");
        gitlet("commit", "commit in newb");
        gitlet("checkout", "master");
        gitlet("merge", "newb");
        assertEquals(getText(addedFileName), "initial");
        assertEquals(getText(modifiedFileName), "changed");
    }
    
    @Test
    public void testMergeModifiedInBoth() {
    	gitlet("init");
    	//added : file which is added in both
    	//modified : file which is modified in both
    	String aFileName = TESTING_DIR + "a.txt";
        String aText = "a";
    	String addedFileName = TESTING_DIR + "added.txt";
    	String addedText = "initial";
    	String modifiedFileName = TESTING_DIR + "modified.txt";
    	String modifiedText = "initial";
    	String removedFileName = TESTING_DIR + "removed.txt";
    	String removedText = "initial";
    	createFile(aFileName, aText);
    	createFile(addedFileName, addedText);
        createFile(modifiedFileName, modifiedText);
        createFile(removedFileName, removedText);
        //splitpoint : modified.txt, removed.txt
        gitlet("add", removedFileName);
        gitlet("commit", "splitpoint");
        gitlet("branch", "newb");
        //master : added.txt(initial), modified.txt(master)
        writeFile(modifiedFileName, "master");
        gitlet("add", addedFileName);
        gitlet("add", modifiedFileName);
        gitlet("rm", removedFileName);
        gitlet("commit", "master commit");
        gitlet("checkout", "newb");
        //newb : added.txt(initial), modified.txt(newb)
        writeFile(modifiedFileName, "newb");
        gitlet("add", addedFileName);
        gitlet("add", modifiedFileName);
        gitlet("rm", removedFileName);
        gitlet("commit", "newb commit");
        gitlet("checkout", "master");
        gitlet("merge", "newb");
        writeFile(removedFileName, "whatever");
        File addConflict = new File(addedFileName + ".conflicted");
        File modConflict = new File(modifiedFileName + ".conflicted");
        assertTrue(addConflict.exists());
        assertTrue(modConflict.exists());
        assertEquals("initial", getText(addedFileName));
        assertEquals("master", getText(modifiedFileName));
        assertEquals("initial", getText(addedFileName + ".conflicted"));
        assertEquals("newb", getText(modifiedFileName + ".conflicted"));
    }
    /**
     * Tests whether status prints the correct information
     * Does not quite work. We don't know why, it looks the same, but it does not 
     * recognize them to be the same thing. We ended up running the test and being
     * able to test visually looking at the difference between the expected and given
     * (which were the same).
     */
//    @Test
//    public void testStatus() {
//        gitlet("init");
//        String aFileName = TESTING_DIR + "a.txt";
//        String aText = "a";
//        String bFileName = TESTING_DIR + "b.txt";
//        String bText = "b";
//        createFile(aFileName, aText);
//        createFile(bFileName, bText);
//        
//        gitlet("add", aFileName);
//        gitlet("commit", "a");
//        gitlet("branch", "new");
//        gitlet("add", bFileName);
//        gitlet("rm", aFileName);
//        String result = gitlet("status");
//        assertEquals("=== Branches ===\n" +
//                "new\n" +
//                "*master\n" +
//                "\n" +
//                "=== Staged Files ===\n" +
//                bFileName + "\n" +
//                "\n" +
//                "=== Files Marked for Untracking ===\n" +
//                aFileName +
//                "\n", result);
//    }

    /*
     * 3. Test checkout works even if its in other branch
     */
    @Test
    public void testCheckOut() {
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        String pangolinFileName = TESTING_DIR + "pangolin.txt";
        String pangolinText = "This is a pangolin";
        createFile(wugFileName, wugText);
        createFile(pangolinFileName, pangolinText);
        gitlet("init");
        gitlet("branch", "newb");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");//ID:1
        gitlet("checkout", "newb");
        gitlet("add", pangolinFileName);
        gitlet("commit", "added pangolin in newb");//ID:2
        writeFile(wugFileName, "This is not a wug.");
        gitlet("checkout", "1", wugFileName);
        assertEquals(wugText, getText(wugFileName));
    }
    
    /*
     * 6. Test that merge will generate a .conflicted file if 
     * a file has been modified in both branches since the split point.
     */
    @Test
    public void testMerge2() {
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        String pangolinFileName = TESTING_DIR + "pangolin.txt";
        String pangolinText = "This is a pangolin";
        createFile(wugFileName, wugText);
        createFile(pangolinFileName, pangolinText);
        gitlet("init");
        gitlet("branch", "newb");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");//ID:1
        gitlet("checkout", "newb");
        writeFile(wugFileName, "This is not a wug.");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug to newb");
        gitlet("checkout", "master");
        gitlet("merge", "newb");
        File f = new File(TESTING_DIR + "wug.txt.conflicted");
        assertTrue(f.exists());
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



