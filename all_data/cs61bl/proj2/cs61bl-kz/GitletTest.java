import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	private static final String STAGE_DIR = ".gitlet/stage";

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
	 * creates an initial commit, which is the other functionality of init. This
	 * is tested in the testBasicLog junit case.
	 */
	@Test
	public void testBasicInitialize() {
		this.setUp();
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
	}

	/**
	 * Tests that it will add a file to staging area Tests that a file marked
	 * for no tracking gets unmarked but not staged Prints error message when
	 * file does not exist (Not sure how to test yet) Test whether nested files
	 * work correctly :)))))))))) need to do now
	 */
	@Test
	public void testBasicAddRm() {
		gitlet("init");
		String cakeFlavor = TESTING_DIR + "cake.txt";
		String cakeText = "Whipcream frosting only!";
		createFile(cakeFlavor, cakeText);
		// adds file to the staging area
		gitlet("add", cakeFlavor);
		// checks that file is in staging area
		assertEquals(cakeText, getText(STAGE_DIR + "/" + cakeFlavor));
		// removes file from staging area
		// adds file to tracking
		gitlet("commit", "i am committing");
		// adds cakeFlavor to staging area
		gitlet("add", cakeFlavor);
		// should return true
		assertTrue(Paths.get(STAGE_DIR + "/" + cakeFlavor).toFile().exists());
		gitlet("rm", cakeFlavor);
		// should return false because cakeFlavor was rm from staging area
		assertFalse(Paths.get(STAGE_DIR + "/" + cakeFlavor).toFile().exists());
		// cakeFlavor marked for no tracking;
		gitlet("rm", cakeFlavor);
		// should print no reason to remove
		gitlet("rm", cakeFlavor);
		// file should be tracked again but not staged
		gitlet("add", cakeFlavor);
		// file should not be added to the stage
		assertFalse(Paths.get(STAGE_DIR + "/" + cakeFlavor).toFile().exists());
	}

	/**
	 * Tests whether the methods checkout, add, and remove work in conjunction
	 * Focused on making sure that tracking and untracking is functioning
	 * properly Also tests that changes are saved once a commit is made Tests
	 * whether each commit has a unique integer ID number and time of creation
	 * using log
	 */
	@Test
	public void testCommitAddRmLog() {
		gitlet("init");
		String cakeFlavor = TESTING_DIR + "cake.txt";
		String cakeText = "Whipcream frosting only!";
		createFile(cakeFlavor, cakeText);
		gitlet("add", cakeFlavor);
		// checks that file is in staging area
		assertEquals(cakeText, getText(STAGE_DIR + "/" + cakeFlavor));
		// removes file from staging area
		// adds file to tracking
		gitlet("commit", "i am committing");
		writeFile(cakeFlavor, "Shortcake say what!");
		// adds cakeFlavor to staging area
		gitlet("add", cakeFlavor);
		// should return true
		assertTrue(Paths.get(STAGE_DIR + "/" + cakeFlavor).toFile().exists());
		// commit to save changes to cake text
		gitlet("commit", "cake details");
		// check to see if commit worked
		assertEquals("Shortcake say what!", getText(cakeFlavor));
		// tests unique ID/ DOB for each commit
		gitlet("log");
	}
	

	/**
	 * Checks that you can checkout a file from a commit from a different branch.
	 * also tests that you can do that when the commit does not stage the file.
	 */
	@Test
	public void testCheckoutFromOtherBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "first");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		gitlet("commit", "second");
		String mugFileName = TESTING_DIR + "mug.txt";
		String mugText = "This is a mug.";
		createFile(mugFileName, mugText);
		gitlet("add", mugFileName);
		gitlet("commit", "third");
		gitlet("branch", "branch");
		String jugFileName = TESTING_DIR + "jug.txt";
		String jugText = "This is a jug.";
		createFile(jugFileName, jugText);
		gitlet("add", jugFileName);
		gitlet("commit", "fourth");
		gitlet("checkout", "branch");
		String snugFileName = TESTING_DIR + "snug.txt";
		String snugText = "This is a snug.";
		createFile(snugFileName, snugText);
		gitlet("add", snugFileName);
		gitlet("commit", "fifth");
		String drugFileName = TESTING_DIR + "drug.txt";
		String drugText = "This is a drug.";
		createFile(drugFileName, drugText);
		gitlet("add", drugFileName);
		gitlet("commit", "first");
		writeFile(pugFileName, "this is not a pug");
		gitlet("checkout", "3", pugFileName);
		assertEquals(pugText, getText(pugFileName));
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testBasicCheckout() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	@Test
	public void testCheckoutFileName() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
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
		String commitMessage1 = "second commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("log");
		gitlet("branch", "uno");
		writeFile(wugFileName, "This is a happy wug");
		gitlet("add", wugFileName);
		gitlet("commit", "branching further");
		gitlet("log");
		gitlet("checkout", "uno");
		writeFile(wugFileName, "This is not a wug");
		gitlet("add", wugFileName);
		gitlet("commit", "checking now");
		gitlet("log");

	}

	/**
	 * Tests whether it returns the ID of the right commit given a commit
	 * message Tests whether multiple commit IDs are printed if they have the
	 * same commit message
	 */
	@Test
	public void testBasicFind() {
		gitlet("init");
		String commitMessage1 = "commit message repetition check";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		String cupCakes = TESTING_DIR + "cake.txt";
		String cakeText = "This is a red velvet cupcake.";
		createFile(cupCakes, cakeText);
		String iceCream = TESTING_DIR + "ice.txt";
		String iceText = "We scream for iceCream.";
		createFile(iceCream, iceText);
		gitlet("add", iceCream);
		gitlet("commit", commitMessage1);
		gitlet("add", cupCakes);
		gitlet("commit", commitMessage1);
		gitlet("find", "added wug");
		gitlet("find", "commit message repetition check");
	}

	/**
	 * Tests that branch creates a new branch and does not automatically switch
	 * the current branch.
	 */
	@Test
	public void testBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "secondBranch");
		gitlet("status");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		String commitMessage3 = "added pug";
		gitlet("commit", commitMessage3);
		gitlet("status");
	}

	/**
	 * Tests that rmBranch removes branches according to the spec
	 */
	@Test
	public void testRMBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "secondBranch");
		gitlet("status");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		String commitMessage3 = "added pug";
		gitlet("commit", commitMessage3);
		gitlet("status");
		gitlet("rm-branch", "secondBranch");
		gitlet("status");
		gitlet("rm-branch", "master");
		gitlet("status");
	}

	/**
	 * Tests whether the history of all given commits is printing NEED TO TEST
	 * WHEN THERE ARE TWO BRANCHES
	 */
	@Test
	public void testBasicGlobalLog() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "secondBranch");
		gitlet("status");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		String commitMessage3 = "added pug";
		gitlet("commit", commitMessage3);
		gitlet("status");
		gitlet("global-log");
	}

	/**
	 * Tests that all branches are shown with a mark on the current branch.
	 * Tests that staged files and marked for untracking files are also accurate
	 */
	@Test
	public void testBasicStatus() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("status");
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "secondBranch");
		gitlet("status");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		String mugFileName = TESTING_DIR + "mug.txt";
		String mugText = "This is a mug.";
		createFile(mugFileName, mugText);
		gitlet("add", mugFileName);
		gitlet("status");
		gitlet("rm", mugFileName);
		gitlet("status");
		gitlet("rm", mugFileName);
		gitlet("status");
		String commitMessage3 = "added pug";
		gitlet("commit", commitMessage3);
		gitlet("status");
		System.out.println("end");
	}

	/**
	 * Tests whether reset changes the output of log
	 */
	@Test
	public void testBasicReset() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "first");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		gitlet("commit", "second");
		String mugFileName = TESTING_DIR + "mug.txt";
		String mugText = "This is a mug.";
		createFile(mugFileName, mugText);
		gitlet("add", mugFileName);
		gitlet("commit", "third");
		gitlet("branch", "branch");
		String jugFileName = TESTING_DIR + "jug.txt";
		String jugText = "This is a jug.";
		createFile(jugFileName, jugText);
		gitlet("add", jugFileName);
		gitlet("commit", "fourth");
		gitlet("log");
		gitlet("reset", "2");
		gitlet("log");
		assertFalse(Paths.get(STAGE_DIR +"/" + jugFileName).toFile().exists());
		
	}
	
	/**
	 * Tests that merge will generate a .conflicted file if the file
	 * modified in both the given and the current branch
	 */
	@Test
	public void testMergeConflict() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "wug";
		createFile(wugFileName, wugText);
		//modifying file in the master branch
		gitlet("add", wugFileName);
		gitlet("commit", "first");
		gitlet("branch", "uno");
		writeFile(wugFileName, "this is a wug");
		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);
		gitlet("checkout", "uno");
		//modifying file in the given branch 
		writeFile(wugFileName, "this is not a wug");
		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);
		gitlet("merge", "master");
		//modifying file in the given branch 
		
		assertTrue(Paths.get(wugFileName + ".conflicted").toFile().exists());
	}
	
	/**
	 * Tests that if a file is modified in either the given or the current branch
	 * but not both
	 * the modified file will be kept after the merge
	 */
	@Test
	public void testMergeOneBranchModified() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "wug";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "pug";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		gitlet("commit", "first");
		gitlet("branch", "uno");
		//modifying file in the master branch
		writeFile(wugFileName, "this is a wug");
		gitlet("add", wugFileName);
		gitlet("commit", "changing in master");
		gitlet("checkout", "uno");
		//modifying file in the given branch 
		writeFile(pugFileName, "this is not a wug");
		gitlet("add", pugFileName);
		gitlet("commit", "changing in branch");
		gitlet("merge", "master");
		gitlet("add", wugFileName);
		assertEquals("this is a wug", getText(STAGE_DIR + "/" + wugFileName));
		gitlet("add", pugFileName);
		assertEquals("this is not a wug", getText(STAGE_DIR + "/" + pugFileName));
	}
	

	@Test
	public void testRebase() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "first");
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		gitlet("commit", "second");
		String mugFileName = TESTING_DIR + "mug.txt";
		String mugText = "This is a mug.";
		createFile(mugFileName, mugText);
		gitlet("add", mugFileName);
		gitlet("commit", "third");
		gitlet("branch", "branch");
		String jugFileName = TESTING_DIR + "jug.txt";
		String jugText = "This is a jug.";
		createFile(jugFileName, jugText);
		gitlet("add", jugFileName);
		gitlet("commit", "fourth");
		gitlet("checkout", "branch");
		String snugFileName = TESTING_DIR + "snug.txt";
		String snugText = "This is a snug.";
		createFile(snugFileName, snugText);
		gitlet("add", snugFileName);
		gitlet("commit", "fifth");
		String drugFileName = TESTING_DIR + "drug.txt";
		String drugText = "This is a drug.";
		createFile(drugFileName, drugText);
		gitlet("add", drugFileName);
		gitlet("commit", "first");
		gitlet("status");
		System.out.println("Before rebase: ");
		gitlet("global-log");
		gitlet("rebase", "master");
		gitlet("status");
		System.out.println("After rebase: ");
		gitlet("global-log");
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
			try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));) {
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