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
 *     	Some code adapted from StackOverflow:
 *
 *     	http://stackoverflow.com/questions
 *     	/779519/delete-files-recursively-in-java
 *
 *     	http://stackoverflow.com/questions/326390/how-to-create-a-java-string
 *     	-from-the-contents-of-a-file
 *
 *     	http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
 *     	println
 *
 */
public class GitletEndToEndTest {
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
     * Test that a file that has been committed at some point can be restored by checking it out from the commit it was committed at.
     */
    @Test
    public void testCommitCheckOut(){
    	
    	 System.out.println("I am testing to see if I was able to checkout to the original commit using the commit ID!");
    	
      	 //Initializes Strings for creating files!
      	 String FileName = TESTING_DIR + "FileName.txt";
      	 String OriginalText = "This is original.";
      	 String NewText = "This is new.";
      	 String OriginalMessage = "Added a FileName with OriginalText!";
      	 String NewMessage = "Edited a FileName with NewText";//from the user's perspective
      	 String logOutput = null;
      	 int StartOfId = -1;
      	 String commitId = null;
      	 int EndOfId = -1;
      	 
      	 //creates file with FileName and Original Text.
      	 createFile(FileName, OriginalText);
      	 System.out.println("Finished creating file!!!");
      	 
      	 //Init! Add the file! Commit!
      	 gitlet("init");
     	 gitlet("add", FileName);
     	 gitlet("commit", OriginalMessage);
     	 System.out.println("First time adding and committing!!!");
     	 
      	 //Get the commit ID!
     	 logOutput = gitlet("log");
     	 StartOfId = logOutput.indexOf("Commit ") + "Commit ".length();
     	 commitId = logOutput.substring(StartOfId);
     	 EndOfId = commitId.indexOf("\n");
     	 commitId = commitId.substring(0, EndOfId);
     	 System.out.println("I found the Commit ID which is " + commitId);
     	 
      	 //Edit the file
     	 writeFile(FileName, NewText);
     	 
      	 //Add the file and Commit!
    	 gitlet("add", FileName);
    	 gitlet("commit", NewMessage);
    	 System.out.println("Second time adding and committing the file!!!");
    	
      	 //Ensure the file is an edited version!
    	 assertEquals(NewText, getText(FileName));
    	 System.out.println("Checked successfully that file was edited!!!");
    	 
      	 //Check out to the Commit ID that we saved earlier!
    	 gitlet("checkout", commitId, FileName);
    	 System.out.println("Here we are checking out to old commit!!!");
    	 
      	 //Check that the file has now changed to the original!
      	 assertEquals(OriginalText, getText(FileName));
      	 System.out.println("Checked successfully that the file was reverted back to the stage it was at during the first commit!!!");
    }
    @Test
    public void testOldCommitCheckout() {
    	String oneFileName = TESTING_DIR + "oneFileName.txt";
    	String oneText = "This is my text.";
    	createFile(oneFileName, oneText);
    	String twoFileName = TESTING_DIR + "twoFileName.txt";
    	createFile(twoFileName, "This is also my text.");
    	
    	gitlet("init");
    	gitlet("add", oneFileName);
    	gitlet("add", twoFileName);
    	gitlet("commit", "added text 1");
    	
    	String testText = "This is not my text";
    	writeFile(oneFileName, testText);
    	gitlet("add", oneFileName);
    	String textMessage = "added text 2";
    	gitlet("commit", textMessage);
    	
    	writeFile(twoFileName, "This is also not my text");
    	gitlet("add", twoFileName);
    	gitlet("commit", "added text 3");
    	
    	String commitId = getFirstId(textMessage);
    	gitlet("checkout", commitId, oneFileName);
    	assertEquals(testText, getText(oneFileName)); 	
    }
    @Test
    public void testCheckoutIDFile() {
    	String oneFileName = TESTING_DIR + "oneFileName.txt";
    	String oneText = "This is text one.";
    	String twoFileName = TESTING_DIR + "twoFileName.txt";
    	String twoText = "This is text two.";
    	createFile(oneFileName, oneText);
    	createFile(twoFileName, twoText);
    	
    	gitlet("init");
    	gitlet("add", oneFileName);
    	gitlet("add", twoFileName);
    	String commitOne = "commit one";
    	gitlet("commit", commitOne);
    	
    	String newTextOne = "This is new text one.";
    	writeFile(oneFileName, newTextOne);
    	gitlet("add", oneFileName);
    	String commitTwo = "commit two";
    	gitlet("commit", commitTwo);
    	String branchOne = "branchOne";
    	gitlet("branch", branchOne);
    	gitlet("checkout", branchOne);
    	
    	String newTextTwo = "This is new text two.";
    	writeFile(twoFileName, newTextTwo);
    	gitlet("commit", "commit three");
    	
    	String commitIdOne = getFirstId(commitOne);
    	gitlet("checkout", commitIdOne, oneFileName);
    	assertEquals(oneText, getText(oneFileName));
    	String commitIdTwo = getFirstId(commitTwo);
    	gitlet("checkout", commitIdTwo, oneFileName);
    	assertEquals(newTextOne, getText(oneFileName));
    } 
    @Test
    public void testBackwordReset() {
    	String FileOne = TESTING_DIR + "FileOne.txt";
    	String TextOne = "This is text one.";
    	createFile(FileOne, TextOne);
    	
    	gitlet("init");
    	gitlet("add", FileOne);
    	gitlet("commit","Commit One");
    	createFile("FileTwo", "This is text two");
    	gitlet("add", "FileTwo");
    	gitlet("commit", "Commit Two");
    	createFile("FileThree", "This is text three.");
    	gitlet("add", "FileThree");
    	gitlet("commit", "Commit Three");
    	createFile("FileFour", "This is text four.");
    	gitlet("add", "FileFour");
    	gitlet("commit", "Commit Four");
    	createFile("FileFive", "This is text five.");
    	gitlet("add", "FileFive");
    	gitlet("commit", "Commit Five");
    	gitlet("log");
    	
    	gitlet("reset", "3");
    	gitlet("log");
    	
    }
    @Test
    public void testBranchesLog() {
    	createFile("FileOne", "This is text one.");
    	gitlet("init");
    	gitlet("add", "FileOne");
    	gitlet("commit", "Commit One");
    	gitlet("branch", "branchone");
    	gitlet ("add", "FileOne");
    	gitlet("commit", "Commit Two");
    	gitlet("log");
    	gitlet("checkout", "branchone");
    	gitlet("add", "FileOne");
    	gitlet("commit", "Commit Three");
    	gitlet("log");
    }
    /**
     * tests that merge will generate a conflicted file if a file has been modified in both branches <strong>since the split point</strong>.
     */
    @Test
    public void testMergeConflict(){
    	//init
    	//add one file
    	//commit 
    	//create another branch 
    	//switch to another branch
    	//edit the file 
    	//add
    	//commit
    	//switch to the master branch gitlet("checkout", "master")
    	//edit the file
    	//add
    	//commit
    	//merge
    	//checkmerge conflict whethere there is 
    	
    	//Create a branch with split commits!!!
    	String FileName = TESTING_DIR + "FileName.txt";
     	String OriginalText = "This is original.For MergeConflict";
     	String NewText = "This will be new.";
     	String New2Text = "This will be 2 new";
     	String OriginalMessage = "Adding a FileName with OriginalText!";
     	String NewMessage = "Editing a FileName with NewText";//from the user's perspective
     	String logOutput = null;
     	String branchName = "toAddBranch";
     	String commitId = null;

     	 //creates file with FileName and Original Text.
     	 createFile(FileName, OriginalText);
     	 System.out.println("Finished creating file!!!");
     	 
    	
     	//creates file with FileName and Original Text.
      	 createFile(FileName, OriginalText);
      	 System.out.println("Finished creating file!!!");
      	 
      	 //Init! Add the file! Commit!
      	 gitlet("init");
     	 gitlet("add", FileName);
     	 gitlet("commit", OriginalMessage);
     	 System.out.println("First time adding and committing!!!");
     	 
     	 //Create another branch!!!
     	 gitlet("branch",branchName);
     	 
     	 //Switch to another branch!!!
     	 gitlet("checkout", branchName);
     	 
     	 
      	 //Edit the file
     	 writeFile(FileName, NewText);
     	 
      	 //Add the file and Commit!
    	 gitlet("add", FileName);
    	 gitlet("commit", NewMessage);
    	 System.out.println("Second time adding and committing the file!!!");
    	
      	 //Ensure the file is an edited version!
    	 assertEquals(NewText, getText(FileName));
    	 System.out.println("Checked successfully that file was edited!!!");
    	 
    	 //switch to master branch!!!;
    	 gitlet("checkout", "master");
    	 
    	 //Edit the file
     	 writeFile(FileName, New2Text);
     	 
     	 //Add the file and Commit the file(for the 3rd time)
     	 gitlet("add", FileName);
     	 gitlet("commit",FileName);
    	 System.out.println("Third time adding and committing the file!!!");
    	 
     	 //merge
     	 String message = gitlet("merge", branchName);

     	 
     	 //Check if .conflictedfile exists and if the String error output has been produced!!!
     	 assertEquals("Encountered a merge conflict.", message.trim());
    }
    
    @Test
    public void testMergeNonConflict(){
    	//init
    	//add one file
    	//commit 
    	//create another branch 
    	//switch to another branch
    	//edit the file 
    	//add
    	//commit
    	//switch to the master branch gitlet("checkout", "master")
    	//edit the file
    	//add
    	//commit
    	//merge
    	//checkmerge conflict whethere there is 
    	
    	//Create a branch with split commits!!!
    	String FileName = TESTING_DIR + "FileName.txt";
    	String FileName2 = TESTING_DIR + "FileName2.txt";
     	String OriginalText = "This is original";
     	String OriginalText2 = "This is original2";
     	String NewText = "This will be new";
     	String New2Text = "This will be 2 new";
     	String OriginalMessage = "Adding a FileName with OriginalText!";
     	String NewMessage = "Editing a FileName with NewText";//from the user's perspective
     	String logOutput = null;
     	String branchName = "toAddBranch";
     	String commitId = null;

     	 //creates file with FileName and Original Text.
     	 createFile(FileName, OriginalText);
     	 System.out.println("Finished creating file!!!");
     	 
    	
     	//creates file with FileName and Original Text.
      	 createFile(FileName2, OriginalText2);
      	 System.out.println("Finished creating file!!!");
      	 
      	 //Init! Add the file! Commit!
      	 gitlet("init");
     	 gitlet("add", FileName);
     	 gitlet("add", FileName2);
     	 gitlet("commit", OriginalMessage);
     	 System.out.println("First time adding and committing!!!");
     	 
     	 //Create another branch!!!
     	 gitlet("branch",branchName);
     	 
     	 //Switch to another branch!!!
     	 gitlet("checkout", branchName);
     	 
     	 
      	 //Edit the file
     	 writeFile(FileName2, New2Text);
     	 
      	 //Add the file and Commit!
    	 gitlet("add", FileName2);
    	 gitlet("commit", NewMessage);
    	 System.out.println("Second time adding and committing the file!!!");
    	 
    	 //switch to master branch!!!;
    	 gitlet("checkout", "master");
    	 
    	 
    	 assertEquals("This is original", getText(FileName));
      	 assertEquals("This is original2", getText(FileName2));
    	 //Edit the file
     	 writeFile(FileName, NewText);
     	 
     	 //Add the file and Commit the file(for the 3rd time)
     	 gitlet("add", FileName);
     	 gitlet("commit",FileName);
     	 gitlet("checkout", "master");
     	 assertEquals("This will be new", getText(FileName));
     	 assertEquals("This is original2", getText(FileName2));
    	 System.out.println("Third time adding and committing the file!!!");
    	 
     	 //merge
     	 gitlet("merge", branchName);
     	 gitlet("checkout", FileName);
     	 gitlet("checkout", FileName2);
     	 //gitlet("checkout", "master");
     	 //are both files the modified version;
     	 assertEquals("This will be new", getText(FileName));
     	 assertEquals("This will be 2 new", getText(FileName2));
    }
    @Test
    public void testRebaseLog() {
    	String FileOne = TESTING_DIR + "FileOne.txt";
    	String FileTwo = TESTING_DIR + "FileTwo.txt";
     	String TextOne = "This is text one.";
     	String TextTwo = "This is text two.";
     	String NewTextOne = "This is new text one.";
     	String NewTextTwo = "This is new text two.";
     	String branchName = "Branch One";
     	createFile(FileOne, TextOne);
     	createFile(FileTwo, TextTwo);
     	
     	gitlet("init"); //init
     	gitlet("add", FileOne);
     	gitlet("add",FileTwo); //add two files, content v1
     	gitlet("commit", "commit one"); //commit 1: file at v1
     	
     	gitlet("branch",branchName); //another branch
    	gitlet("checkout", branchName);
    	
    	writeFile(FileTwo, NewTextTwo); //file2 -> v2 | file1 - v1
   	 	gitlet("add", FileTwo);	// add file2 - v2
   	 	gitlet("commit", "commit two"); //commit 3 
   	 	
   	 	//gitlet("checkout", branchName);
   	 	assertEquals(TextOne, getText(FileOne)); //check file1 - v1
   	 	assertEquals(NewTextTwo, getText(FileTwo)); //check file2 - v2
   	 
   	 	gitlet("checkout", "master");
   	 
   	 	assertEquals(TextOne, getText(FileOne)); //file1 - v1
     	assertEquals(TextTwo, getText(FileTwo)); //file2 - v2
    	writeFile(FileOne, NewTextOne); //file1 -> v2
    	
    	gitlet("add", FileOne); // add file1 - v2
    	gitlet("commit", "Commit Three"); //commit 3
    	//gitlet("checkout", "master"); 
    	assertEquals(NewTextOne, getText(FileOne)); //check file 1 - v2
    	assertEquals(TextTwo, getText(FileTwo)); //check file2 - v1
   	 
    	//rebase
    	gitletFast("rebase", branchName); //rebase to new branch
    	gitlet("checkout", "master");
    	//are both files the modified version;
    	assertEquals(NewTextOne, getText(FileOne));
    	assertEquals(NewTextTwo, getText(FileTwo));
    	
    	//check log
    	gitlet("log");
    	
    }
    @Test
    public void testBasePropagate(){
    	//init
    	//add two different files
    	//commit 
    	//create another branch 
    	//switch to another branch
    	//edit the first(must remember which is first and which is second) file 
    	//add that file
    	//commit 
    	//switch to the master branch gitlet("checkout", "master")
    	//edit the second file
    	//add that file
    	//commit
    	//rebase
    	//check if you have both latest versions of the two files-->should display the content of the second file
    	
    	String FileName = TESTING_DIR + "FileName.txt";
    	String FileName2 = TESTING_DIR + "FileName2.txt";
     	String OriginalText = "This is original.For testBasePropagate.";
     	String OriginalText2 = "This is original2. For testBasePropagate.";
     	String NewText = "This will be new.";
     	String New2Text = "This will be 2 new";
     	String OriginalMessage = "Adding a FileName with OriginalText!";
     	String NewMessage = "Editing a FileName with NewText";//from the user's perspective
     	String logOutput = null;
     	String branchName = "toAddBranch";
     	String commitId = null;

     	 //creates file with FileName and Original Text.
     	 createFile(FileName, OriginalText); //file1 - v1
     	 System.out.println("Finished creating file!!!");
      	 createFile(FileName2, OriginalText2); //file2 - v1
      	 System.out.println("Finished creating file2!!!");
      	 
      	 //Init! Add the file! Commit!
      	 gitlet("init"); //init
     	 gitlet("add", FileName); //add file1 - v1
     	 gitlet("add", FileName2); //add file2 - v1
     	 gitlet("commit", OriginalMessage); //commit
     	 
     	 gitlet("branch",branchName); //branch another
     	 gitlet("checkout", branchName); //checkout another
     	 writeFile(FileName, NewText); //EDIT: file1 -> v2
     	 
    	 gitlet("add", FileName); //add file1 - v2
    	 gitlet("commit", NewMessage); //commit
    	
    	 assertEquals(NewText, getText(FileName)); //check file1 - v2
    	 
    	 gitlet("checkout", "master"); //checkout master
    	 
     	 writeFile(FileName2, New2Text); //EDIT: file2 -> v2
     	 
     	 gitlet("add", FileName2); //add file2 - v2
     	 gitlet("commit", NewMessage); //commit
     	 
     	 //another:	file1 - v2
     	 //			file2 - v1
    	 //master:	file1 - v1
     	 //			file2 - v2
     	 
     	 //rebase
     	 String message = gitlet("rebase", branchName);

     	 
     	 //Check if you have both latest versions of the two files-->should display the content of the second file
     	 assertEquals(NewText, getText(FileName));
     	 assertEquals(New2Text, getText(FileName2));    	
    }
    
    @Test
    public void testRebase2() {
    	String FileOne = TESTING_DIR + "FileOne.txt";
    	String FileTwo = TESTING_DIR + "FileTwo.txt";
     	String TextOne = "This is text one.";
     	String TextTwo = "This is text two.";
     	String NewTextOne = "This is new text one.";
     	String NewTextTwo = "This is new text two.";
     	String NewNewTextOne = "This is new text one too.";
     	String NewNewTextTwo = "This is new text two too.";
     	String branchName = "Branch One";
     	createFile(FileOne, TextOne);
     	createFile(FileTwo, TextTwo);
     	
     	gitlet("init"); //init
     	gitlet("add", FileOne); //add file1 - v1
     	gitlet("add",FileTwo); //add file2 - v1
     	gitlet("commit", "commit one"); //commit 1
     	
     	gitlet("branch",branchName); //branch another
    	gitlet("checkout", branchName); //checkout another
    	
    	writeFile(FileTwo, NewTextTwo); //EDIT: file2 -> v2
   	 	gitlet("add", FileTwo); //add file2 - v2
   	 	gitlet("commit", "commit two"); //commit 2
   	 	
   	 	gitlet("checkout", branchName); //branch another
   	 	assertEquals(TextOne, getText(FileOne)); //check file1 - v1
   	 	assertEquals(NewTextTwo, getText(FileTwo)); //check file2 - v2
   	 	
   	 	writeFile(FileTwo, NewNewTextTwo); //EDIT: file2 -> v3
   	 	gitlet("add", FileTwo); // add file2 - v3
   	 	gitlet("commit", "commit three"); //commit 3
   	 	
   	 	gitlet("checkout", "master"); //checkout master
   	 
   	 	assertEquals(TextOne, getText(FileOne)); //check file1 - v1
     	assertEquals(TextTwo, getText(FileTwo)); //check file2 - v1
     	
    	writeFile(FileOne, NewTextOne); //EDIT: file1 -> v2
    	
    	gitlet("add", FileOne); //add file1 - v2
    	gitlet("commit", "commit four"); //commit 4
    	gitlet("checkout", "master"); //checkout master
    	
    	assertEquals(NewTextOne, getText(FileOne)); //check file1 - v2
    	assertEquals(TextTwo, getText(FileTwo)); //check file2 - v1
    	
    	writeFile(FileOne, NewNewTextOne); //EDIT: file1 -> v3
    	
    	gitlet("add", FileOne); //add file1 - v3
    	gitlet("commit", "commit five"); //commit 5
    	gitlet("checkout", "master"); //checkout master
    	
    	assertEquals(NewNewTextOne, getText(FileOne)); //check file1 - v3
    	assertEquals(TextTwo, getText(FileTwo)); //check file2 - v1
    	
    	//rebase
    	gitletFast("rebase", branchName);
    	gitlet("checkout", "master");
    	//are both files the modified version;
    	assertEquals(NewNewTextOne, getText(FileOne)); //check file1 - v3
    	assertEquals(NewNewTextTwo, getText(FileTwo)); //check file2 - v3
    	
    	gitlet("log");
    	
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
   		 try {
			Gitlet.main(args);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    
    private static String getFirstId(String message) {
		try {
			String content = gitlet("find", message);
			return content.split(LINE_SEPARATOR)[0];
		} catch (Exception e) {
			return "";
		}
	}
    
   
}