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
import java.util.*;

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
	 * Tests merge
	 * Involves, init, add, commit, checkout (branch), branch
	 * merge. Adapted from the graphic in merge description. Involves
	 * merge w/o conflict.
	 */
	@Test
	public void testMergeBasic() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String shrekFileName = TESTING_DIR + "shrek.txt";
		String wugText = "This is a wug.";
		String shrekText = "Shrek is love.";
		createFile(wugFileName, wugText);
		createFile(shrekFileName, shrekText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", shrekFileName);
		String commitMessage1 = "added wug & shrek";
		gitlet("commit", commitMessage1);
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		shrekText = "Shrek is life.";
		writeFile(shrekFileName, shrekText);
		String commitMessage5 = "Shrek is life.";
		gitlet("add", shrekFileName);
		gitlet("commit", commitMessage5);
		assertEquals("Shrek is life.", getText(shrekFileName));
		gitlet("checkout", "master");
		gitlet("status");
		gitlet("log");
		assertEquals("Shrek is love.", getText(shrekFileName));
		gitlet("merge", "branch");
		gitlet("log");
		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("Shrek is life.", getText(shrekFileName));
	}

	/**
	 * Tests that a branch can be rebased. 
	 * Each branch has only one commit since the split point.
	 * Involves init, add, commit, rebase, branch, checkout(branch) and log 
	 */
	@Test
	public void testBasicRebase() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add", wugFileName);
		String commitMessage2 = "first commit. Should be SplitPoint";
		gitlet("commit", commitMessage2 );
		assertEquals(wugText, getText(wugFileName));
		gitlet("branch", "branch");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "head of master branch";
		gitlet("commit", commitMessage3);
		assertEquals("This is not a wug.", getText(wugFileName));
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		gitlet("add", catFileName);
		String commitMessage6 = "new head of master branch";
		gitlet("commit", commitMessage6 );
		assertEquals(catText, getText(catFileName));
		//System.exit(0);
		gitlet("checkout", "branch");
		
		
		
		String ratFileName = TESTING_DIR + "rat.txt";
		String ratText = "This is a rat.";
		createFile(ratFileName, ratText);
		gitlet("add", ratFileName);
		String commitMessage7 = "head of branch branch";
		gitlet("commit", commitMessage7 );
		assertEquals(ratText, getText(ratFileName));
		
		String logContent = gitlet("log");
		/*System.out.println("==========================");
		String[] messages = extractCommitMessages(logContent);
		for(String s : messages){
			System.out.println(s + "\n-------------");
		}
		System.out.println("==========================");
		*/assertArrayEquals(new String[] { commitMessage7, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		
		gitlet("rebase", "master");
		gitlet("checkout", "master");
//		assertEquals(wugText, getText(wugFileName));
		
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] {commitMessage6, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent2));
		
		gitlet("checkout", "branch");
		String logContent4 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage7, commitMessage6, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent4));
	}
	
	/**
	 * Tests that a branch can be rebased 
	 * even if the branch has many different commits on it. 
	 * Then checks if you can add to the current branch afterwards
	 * Involves init, add, commit, rebase(branch name), branch, checkout(branch), log
	 */
	@Test
	public void testRebase() {
	String wugFileName = TESTING_DIR + "wug.txt";
	String wugText = "This is a wug.";
	createFile(wugFileName, wugText);
	gitlet("init");
	String commitMessage1 = "initial commit";
	gitlet("add", wugFileName);
	String commitMessage2 = "added wug";
	gitlet("commit", commitMessage2 );
	assertEquals(wugText, getText(wugFileName));
	gitlet("branch", "branch");
	gitlet("checkout", "branch");
	writeFile(wugFileName, "This is not a wug.");
	gitlet("add", wugFileName);
	String commitMessage3 = "This is not a wug";
	gitlet("commit", commitMessage3);
	assertEquals("This is not a wug.", getText(wugFileName));
	String turtleFileName = TESTING_DIR + "turtle.txt";
	String turtleText = "This is a turtle.";
	createFile(turtleFileName, turtleText);
	gitlet("add", turtleFileName);
	String commitMessage4 = "added turtle";
	gitlet("commit", commitMessage4);
	assertEquals(turtleText, getText(turtleFileName));
	String dogFileName = TESTING_DIR + "dog.txt";
	String dogText = "This is a dog.";
	createFile(dogFileName, dogText);
	gitlet("add", dogFileName);
	String commitMessage5 = "added dog";
	gitlet("commit", commitMessage5 );
	assertEquals(dogText, getText(dogFileName));
	String logContent = gitlet("log");
	assertArrayEquals(new String[] {commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
			extractCommitMessages(logContent));
	gitlet("checkout", "master");
	assertEquals(wugText, getText(wugFileName));
	String catFileName = TESTING_DIR + "cat.txt";
	String catText = "This is a cat.";
	createFile(catFileName, catText);
	gitlet("add", catFileName);
	String commitMessage6 = "added cat";
	gitlet("commit", commitMessage6 );
	assertEquals(catText, getText(catFileName));
	String logContent2 = gitlet("log");
	assertArrayEquals(new String[] {commitMessage6, commitMessage2, commitMessage1 },
			extractCommitMessages(logContent2));
	gitlet("rebase", "branch");
	gitlet("checkout", "branch");
	String logContent3 = gitlet("log");
	assertArrayEquals(new String[] { commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
			extractCommitMessages(logContent3));
	gitlet("checkout", "master");
	String logContent4 = gitlet("log");
	assertArrayEquals(new String[] { commitMessage6, commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
			extractCommitMessages(logContent4));
}
	
	/**
	 * Tests that a branch can be rebased 
	 * even if the branch has many different commits on it. 
	 * Then checks if you can add to the current branch afterwards
	 * Involves init, add, commit, rebase(branch name), branch, checkout(branch), log
	 */
	@Test
	public void testRebaseFailures() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add", wugFileName);
		String commitMessage2 = "first commit. Should be SplitPoint";
		gitlet("commit", commitMessage2 );
		assertEquals(wugText, getText(wugFileName));
		gitlet("branch", "branch");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "head of master branch";
		gitlet("commit", commitMessage3);
		assertEquals("This is not a wug.", getText(wugFileName));
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		gitlet("add", catFileName);
		String commitMessage6 = "new head of master branch";
		gitlet("commit", commitMessage6 );
		assertEquals(catText, getText(catFileName));
		String output = gitlet("rebase", "master");
		assertTrue(output.equals("Cannot merge a branch with itself."));
		gitlet("checkout", "branch");
		gitlet("rebase", "master");
		gitlet("checkout", "master");
		String logContent4 = gitlet("log");
		printArray(extractCommitMessages(logContent4));
		assertArrayEquals(new String[] { commitMessage6, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent4));
		gitlet("checkout", "branch");
		String logContent5 = gitlet("log");
		printArray(extractCommitMessages(logContent5));
		assertArrayEquals(new String[] { commitMessage6, 
										commitMessage3, 
										commitMessage2, 
										commitMessage1 },
				extractCommitMessages(logContent5));
		String output2 = gitlet("rebase", "steven");
		assertTrue(output2.equals("A branch with that name does not exist."));
	}
	
	public void printArray(Object[] objects) {
		for(Object o : objects) System.out.println(o);
	}
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
	 * Tests basic status
	 * uses init, add, branch, rm, and status
	 */
	@Test
	public void testBasicStatus() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		ArrayList<String> branches = new ArrayList<String>();
		ArrayList<String> staged = new ArrayList<String>();
		ArrayList<String> untracked = new ArrayList<String>();
		ArrayList<String>[] stats = new ArrayList[3];
		stats[0] = branches; stats[1] = staged; stats[2] = untracked;
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		branches.add("*master");
		staged.add(wugFileName);
		String statusContent = gitlet("status");
		ArrayList<String>[] status = extractStatusMessages(statusContent);
		assertArrayEquals(stats, status);
		gitlet("commit", "added wug");
		statusContent = gitlet("status");
		status = extractStatusMessages(statusContent);
		staged.remove(wugFileName);
		assertArrayEquals(stats, status);
		gitlet("rm", wugFileName); //finish working on this
		statusContent = gitlet("status");
		status = extractStatusMessages(statusContent);
		untracked.add(wugFileName);
		assertArrayEquals(stats, status);
		gitlet("commit", "removed wug");
		untracked.remove(wugFileName);
		statusContent = gitlet("status");
		status = extractStatusMessages(statusContent);
		assertArrayEquals(stats, status);
	}
	
	public static boolean equalList(List first, List second) {
		System.out.println(first.size() + " " + second.size());
		if(first.size() != second.size()) return false;
		for(int i = 0; i < first.size(); i++) {
			if(!first.get(i).equals(second.get(i))) {
				System.out.println("Error " + first.get(i) + " " + second.get(i));
				return false;
			}
		}
		return true;
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
	 * Tests that log changes after switching branches
	 * Involves init, add, commit, checkout (branch), log
	 */
	@Test
	public void testLogSwitch() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String commitMessage0 = "initial commit";
		String commitMessage1 = "added wug";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(wugFileName, "This is not a wug.");
		String commitMessage2 = "This is not a wug.";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		gitlet("checkout", "master");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}
	
	/**
	 * Test that resetting backward appropriately changes the output of log.
	 * Tests init, add, commit, reset (id), log, global-log
	 */
	@Test
	public void testReset() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String commitMessage0 = "initial commit";
		String commitMessage1 = "added wug";
		gitlet("init");
		writeFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		assertEquals(wugText, getText(wugFileName));
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		writeFile(wugFileName, "This is not a wug.");
		String commitMessage2 = "This is not a wug.";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		assertEquals("This is not a wug.", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		writeFile(wugFileName, "Shrek is love.");
		String commitMessage3 = "Shrek is love.";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		assertEquals("Shrek is love.", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		gitlet("reset", "2");
		assertEquals("This is not a wug.", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		gitlet("reset", "3");
		assertEquals("Shrek is love.", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
		gitlet("reset", "0");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage0 },
				extractCommitMessages(logContent));
		logContent = gitlet("global-log");
		assertArrayEquals(new String[] { commitMessage0, commitMessage1, commitMessage2, commitMessage3 },
				extractCommitMessages(logContent));
		String output = gitlet("reset", "4");
		assertEquals(output, "No commit with that id exists.");
	}
	
	/**
	 * Tests that a file can be checked out w/ its commit ID
	 * even if that commit was located in a different branch
	 * Involves init, add, commit, and checkout (id, fileName)
	 * checkout (branch), and branch
	 */
	@Test
	public void testCheckoutID() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		assertEquals(wugText, getText(wugFileName));
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "This is not a wug");
		assertEquals("This is not a wug.", getText(wugFileName));
		gitlet("log");
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		gitlet("log");
		gitlet("checkout", "2", wugFileName);
		assertEquals("This is not a wug.", getText(wugFileName));
		String output = gitlet("checkout", "3", wugFileName);
		assertEquals("No commit with that id exists.", output);
	}
	
	/**
     * test that find 
     * involves init, add, commit and find, 
     */
	@Test
    public void testFind(){
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);

        String wugFileName2 = TESTING_DIR + "wug1.txt";
        String wug1Text = "This is a wug2.";
        createFile(wugFileName, wug1Text);

        gitlet("init");
        gitlet("add", wugFileName);
        String commitMessage2 = "added wug";
        gitlet("commit", commitMessage2);
        String findContent = gitlet("find",commitMessage2);

        assertEquals("1",
                extractFindMessage(findContent)); 
        }
	
	private static String extractFindMessage(String findOutput) {
    	String[] findChunks = findOutput.split("Commit");
    	String commitId = findChunks[0];
    	return commitId;
    }
	
	/**
	 * Tests merge
	 * Involves, init, add, commit, checkout (branch), branch
	 * merge. Adapted from the graphic in merge description. Involves
	 * merge conflict.
	 */
	@Test
	public void testMergeConflict() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wg";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		String commitMessage1 = "added wg";
		gitlet("commit", commitMessage1);
		wugText = "This is a wug";
		writeFile(wugFileName, wugText);
		String commitMessage2 = "added wug";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		gitlet("branch", "branch");
		wugText = "This is a wug!";
		writeFile(wugFileName, wugText);
		String commitMessage3 = "added wug!";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		wugText = "This is a wug!!!";
		writeFile(wugFileName, wugText);
		String commitMessage4 = "added wug!!!";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage4);
		gitlet("checkout", "branch");
		wugText = "This is a wug.";
		writeFile(wugFileName, wugText);
		String commitMessage5 = "added wug.";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage5);
		wugText = "This is a wug...";
		writeFile(wugFileName, wugText);
		String commitMessage6 = "added wug...";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage6);
		gitlet("checkout", "master");
		gitlet("merge", "branch");
		String conflicted = wugFileName + ".conflicted";
		System.out.println(new File(conflicted).exists() + " testing conflicted");
		assertEquals("This is a wug!!!", getText(wugFileName));
		assertEquals("This is a wug...", getText(conflicted));
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
	 * Returns an array of status messages associated with what log has printed
	 */
	
	private static ArrayList<String>[] extractStatusMessages(String statusOutput) {
		String[] lines = statusOutput.split(LINE_SEPARATOR);
		ArrayList<String> branches = new ArrayList<String>();
		ArrayList<String> staged = new ArrayList<String>();
		ArrayList<String> untracked = new ArrayList<String>();
		int count = 0;
		for(String line : lines) {
			if(line.equals("")) continue;
			if(line.startsWith("===")) {
				count++;
				continue;
			}
			if(count == 1) {
				branches.add(line);
			} else if(count == 2){
				staged.add(line);
			} else {
				untracked.add(line);
			}
		}
		ArrayList<String>[] output = new ArrayList[3];
		output[0] = branches;
		output[1] = staged;
		output[2] = untracked;
		return output;
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