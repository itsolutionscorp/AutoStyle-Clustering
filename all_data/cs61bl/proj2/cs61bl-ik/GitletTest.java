import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;//////
import java.io.ByteArrayOutputStream;///////
import java.io.File;
import java.io.FileWriter;////////////
import java.io.IOException;
import java.io.InputStreamReader;/////////
import java.io.PrintStream;////////////////////////////remember once
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
	//@Test
	public void testBasicInitialize() {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	//@Test
	public void testBasicCheckout() {
		String wugFileName =  "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	//@Test
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
	//@Test
	public void testCheckoutAndRm(){
		gitlet("init");
		
		String wugFileName = TESTING_DIR + "wugs.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText); 
		String hugFileName = TESTING_DIR + "hugs.txt";
		String hugText = "I'm sad.";
		createFile(hugFileName, hugText); 
		gitlet("add", wugFileName);
		gitlet("add", hugFileName); 
		
		String commitMessage1 = "added wugs and hugs";
		gitlet("commit", commitMessage1);
		gitlet("rm", hugFileName);
		
		String mugFileName = TESTING_DIR + "mugs.txt";
		String mugText = "Mug = a kind of cup";
		createFile(mugFileName, mugText);
		gitlet("add", mugFileName); 
		String commitMessage2 = "added mugs after rm hugs";
		gitlet("commit", commitMessage2);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		writeFile(hugFileName, "Do you want a hug?");
		writeFile(mugFileName, "Mug also = to get robbed.");
		
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		assertEquals("Do you want a hug?", getText(hugFileName));
		assertEquals(mugText, getText(mugFileName));
	}
	//@Test 
	public void testCheckoutArbitrary(){
		gitlet("init");
		
		String wugFileName =  TESTING_DIR + "wug.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		
		String commitMessage1 = "added wugs";
		gitlet("commit", commitMessage1);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		gitlet("add", wugFileName);
		
		String commitMessage2 = "wugs experiment";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "coolbeans");
		gitlet("checkout", "coolbeans");
		writeFile (wugFileName, "Answer: Two wugS"); 
		gitlet("add", wugFileName);
		String commitMessage3 = "answer";
		gitlet("commit", commitMessage3);
		gitlet("checkout", "1", wugFileName);
		
		assertEquals(wugText, getText(wugFileName));
	
	}
	//@Test
	public void testlogAdjust(){
	gitlet("init");
		
		String wugFileName = TESTING_DIR + "wugs.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		
		String commitMessage1 = "added wugs";
		gitlet("commit", commitMessage1);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		gitlet("add", wugFileName);	
		String commitMessage2 = "wugs experiment";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "coolbeans");
		gitlet("checkout", "coolbeans");
		writeFile (wugFileName, "Answer: Two wugS"); 
		gitlet("add", wugFileName);
		String commitMessage3 = "answer";
		gitlet("commit", commitMessage3);
		gitlet("checkout", "master");
		assertEquals("Now there are two ____. What goes in the blank?", getText(wugFileName));
	
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, "initial commit" },
				extractCommitMessages(logContent));
	}
	//@Test
	public void testlogReset(){
	gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wugs.txt";
		String tugFileName = TESTING_DIR + "tugs.txt";
		String wugText = "This is a wug.";
		String tugText = "Tug-o-war";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wugs";
		gitlet("commit", commitMessage2);
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		gitlet("add", wugFileName);
		String commitMessage3 = "wugs experiment";
		gitlet("commit", commitMessage3);
		writeFile (tugFileName, tugText); 
		gitlet("add", tugFileName);
		String commitMessage4 = "answer";
		gitlet("commit", commitMessage4);
		gitlet("reset", "1");
	
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}
	//@Test
	public void testBranching() {
		gitlet("init");
		String wugFileName =  TESTING_DIR + "wugs.txt";
		String tugFileName =  TESTING_DIR + "tugs.txt";
		String wugText = "This is a wug."; 
		String tugText = "Tug-o-war";
		createFile(wugFileName, wugText);
		createFile(tugFileName, tugText);
		gitlet("add", tugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "first commit");
		gitlet("add", tugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "second commit.. no changes");
		gitlet("branch", "coolbeans");
		String hugFileName = "hugs.txt";
		String hugText = "Do you want a hug?";
		createFile(hugFileName, hugText);
		gitlet("add", hugFileName);
		gitlet("commit", "third commit... added new hugs.txt");
		gitlet("checkout", "coolbeans");
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		writeFile(tugFileName, "pulling");
		gitlet("add", tugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "fourth commit.. should have no hugs.txt");
		writeFile(hugFileName, "Aww..");
		writeFile(tugFileName, "won");
		writeFile(wugFileName, "This is the wugs experiment");
		gitlet("checkout", "master");
		assertEquals("Do you want a hug?", getText(hugFileName));
		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals(tugText, getText(tugFileName));
		gitlet("checkout", "coolbeans");
		assertEquals("Do you want a hug?", getText(hugFileName));
		assertEquals( "Now there are two ____. What goes in the blank?", getText(wugFileName));
		assertEquals("pulling", getText(tugFileName));
		
	}
	//@Test
	public void testmergeCaseOne(){
		gitlet("init");
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		
		String commitMessage1 = "added wugs";
		gitlet("commit", commitMessage1);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		gitlet("add", wugFileName);
		
		String commitMessage2 = "wugs experiment";
		gitlet("commit", commitMessage2);	
		
		writeFile (wugFileName, "Answer: Two wugS"); 
		gitlet("add", wugFileName);
		String commitMessage3 = "answer";
		gitlet("commit", commitMessage3);
		
		gitlet("branch", "coolbeans");
		
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));

		
		writeFile (wugFileName, "Good!"); 
		gitlet("add", wugFileName);
		String commitMessage4 = "good";
		gitlet("commit", commitMessage4);
		
		writeFile (wugFileName, "That's the right answer.");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		
		gitlet("add", wugFileName);
		String commitMessage5 = "back to one";
		gitlet("commit", commitMessage5);
		
		gitlet ("merge", "coolbeans");
		gitlet ("find", "Merged master with coolbeans."); //read what was printed; should be 6
		gitlet("checkout", wugFileName);
		
		assertEquals("Answer: Two wugS", getText(wugFileName));
		
	}
	/**
	 * Tests if current branch has not changed since splitpoint, and the other has,
	 *  merge takes the others file , by checking out and automatically committing.
	 * 
	 */
	//@Test 
	public void testmergeOtherBranch(){ 
		gitlet("init");
	
		String wugFileName = TESTING_DIR + "wugs.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		
		String commitMessage1 = "added wugs";
		gitlet("commit", commitMessage1);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		gitlet("add", wugFileName);
		
		String commitMessage2 = "wugs experiment";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "coolbeans");
	
		writeFile (wugFileName, "Answer: Two wugS"); 
		gitlet("add", wugFileName);
		String commitMessage3 = "answer";
		gitlet("commit", commitMessage3);
		
		gitlet("checkout", "coolbeans"); 
		
		gitlet("checkout", "2", wugFileName);
		gitlet("add", wugFileName);
		String commitMessage4 = "C4";
		gitlet("commit", commitMessage4);
		
		gitlet ("merge", "master");
		assertEquals("Answer: Two wugS", getText(wugFileName));
		gitlet ("find", "Merged coolbeans with master."); //should be five
		

	}
	//@Test 
	public void mergeConflict(){
	gitlet("init");
	
	String wugFileName = TESTING_DIR + "wugs.txt";
	String wugText = "This is a wug."; 
	createFile(wugFileName, wugText);
	gitlet("add", wugFileName);
	
	String commitMessage1 = "added wugs";
	gitlet("commit", commitMessage1);
	
	writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
	gitlet("add", wugFileName);
	
	String commitMessage2 = "wugs experiment";
	gitlet("commit", commitMessage2);
	
	gitlet("branch", "coolbeans");
	writeFile (wugFileName, "Answer: Two wugS"); 
	gitlet("add", wugFileName);
	String commitMessage3 = "answer";
	gitlet("commit", commitMessage3);
	
	gitlet("checkout", "coolbeans"); 
	
	writeFile (wugFileName, "woo");
	gitlet("add", wugFileName);
	String commitMessage4 = "C4";
	gitlet("commit", commitMessage4);
	
	gitlet ("merge", "master");
	assertEquals("woo", getText(wugFileName));
	gitlet ("find", "Merged coolbeans with master."); //should be five
	
	File f = new File(TESTING_DIR + "wugs.txt" + ".conflicted");
	assertTrue(f.exists());
	}
	/**
	 * Test that the output of log after a rebase includes 
	 * the commit messages from both branches involved in the rebase.
	 * Involves init, add, commit, branch, checkout [branch], rebase, log
	 */
	//@Test
	public void testRebaseLogAndALittlePropagation() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage1 = "first commit";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "coolbeans");
		gitlet("add", wugFileName);
		String commitMessage2 = "second commit";
		gitlet("commit", commitMessage2);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		gitlet("add", wugFileName);
		String commitMessage3 = "third commit";
		gitlet("commit", commitMessage3);
		
		gitlet("checkout", "coolbeans");
		
		writeFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage4 = "fourth commit.. started branching";
		gitlet("commit", commitMessage4);
		
		gitlet("add", wugFileName);
		String commitMessage5 = "fifth commit";
		gitlet("commit", commitMessage5);
		gitlet("rebase", "master");
		String logContent = gitlet("log"); 
		
		assertArrayEquals(new String[] { commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1, commitMessage0 },      //switched branch pointers
				extractCommitMessages(logContent));
		writeFile(wugFileName, "erase this");
		gitlet("checkout", "3", wugFileName);
		assertEquals("Now there are two ____. What goes in the blank?",getText(wugFileName));
		writeFile(wugFileName, "erase this");
		gitlet("checkout", "6", wugFileName);
		assertEquals("Now there are two ____. What goes in the blank?",getText(wugFileName));
		writeFile(wugFileName, "erase this");
		gitlet("checkout", "7", wugFileName);
		assertEquals("Now there are two ____. What goes in the blank?",getText(wugFileName));
	}
	/**
	 * Test that changes in the base branch propagate through the replayed branch during a rebase.
	 * Involves init, add, commit, checkout [branch], rebase, checkout [id] [filename]
	 */
	//@Test
	public void testRebasePropagation() {
		gitlet("init");
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug."; 
		createFile(wugFileName, wugText);
		
		String hugFileName = TESTING_DIR + "hugs.txt";
		String hugText = "I'm sad.";
		createFile(hugFileName, hugText); 
		
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		String commitMessage1 = "first commit";
		gitlet("commit", commitMessage1);
		
		writeFile(wugFileName, "Now there are two ____. What goes in the blank?");
		writeFile(hugFileName, "giving a hug");
		gitlet("add", hugFileName);
		gitlet("add", wugFileName);
		String commitMessage2 = "second commit";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "coolbeans");
		gitlet("checkout", "coolbeans");
		
		gitlet("add", hugFileName);
		gitlet("add", wugFileName);
		String commitMessage3 = "third commit... didn't modify any files";
		gitlet("commit", commitMessage3);
		
		writeFile(wugFileName, "Two wugS");
		writeFile(hugFileName, "Aww...");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		String commitMessage4 = "fourth commit.. modified both files";
		gitlet("commit", commitMessage4);
		
		gitlet("checkout", "master");
		gitlet("add", hugFileName);
		gitlet("add", wugFileName);
		String commitMessage5 = "fifth commit... didn't modify any files.. start branching"; //no branching here?
		gitlet("commit", commitMessage5);
		
		writeFile(wugFileName, "This is the wugs experiment");
		gitlet("add", wugFileName);
		String commitMessage6 = "sixth commit...modified only wugs.txt";
		gitlet("commit", commitMessage6);
		
		gitlet("rebase", "coolbeans");
		gitlet("checkout", "7", wugFileName);
		System.out.println(getText(wugFileName));
//		assertEquals("Two wugS", getText(wugFileName));
		
		gitlet("checkout", "7", hugFileName);
		assertEquals("Aww...", getText(hugFileName));
		
		gitlet("checkout", "8", wugFileName);
		assertEquals("This is the wugs experiment", getText(wugFileName));
		
		gitlet("checkout", "8", hugFileName);
		assertEquals("giving a hug", getText(hugFileName));
	}
	
	/** Tests that once you remove a branch and checkout the branch, it 
	 * would not overwrite the current file(s) in the working directory
	 * with the files from the node that the head of the branch was originally pointing
	 */
	//@Test
	public void testRmBranchandCheckout() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "wugs"; 
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage1 = "first commit";
		gitlet("commit", commitMessage1);
		gitlet("branch", "coolbeans");
		writeFile(wugFileName, "wugs wugs");
		gitlet("add", wugFileName);
		gitlet("commit", "Second commit");
		writeFile(wugFileName, "wugs wugs wugs");
		gitlet("add", wugFileName);
		gitlet("commit", "Second commit");
		gitlet("rm-branch", "coolbeans");
		gitlet("checkout", "coolbeans");
		assertEquals("wugs wugs wugs", getText(wugFileName));
		
		
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
