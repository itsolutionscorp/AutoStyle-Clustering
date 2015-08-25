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
	
	@Test 
	public void testAdd(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		wugFileName = "wug.txt";
		gitlet("add", wugFileName);
		File f = new File(GITLET_DIR + "staging-area/");
		assertTrue(f.exists());
	}
	
	@Test
	public void testInit() {
		gitlet("init");
		File staging = new File(".gitlet/staging-area");
		assertTrue(staging.exists());
		String logContent = gitlet("log");
		String commitMessage1 = "initial commit";
		assertArrayEquals(new String[] { commitMessage1 }, extractCommitMessages(logContent));
	}
	
	@Test
	public void testResetBackwards() {
		gitlet("init");
		String aFileName = TESTING_DIR + "a";
		createFile(aFileName, "1");
		gitlet("add", aFileName);
		gitlet("commit", "first commit");
		createFile(aFileName, "2");
		gitlet("add", aFileName);
		gitlet("commit", "second commit");
		createFile(aFileName, "3");
		gitlet("add", aFileName);
		gitlet("commit", "third commit");
		String branchName = "branch1";
		gitlet("branch", branchName);
		gitlet("checkout", branchName);
		createFile(aFileName, "4");
		gitlet("add", aFileName);
		gitlet("commit", "fourth commit");
		createFile(aFileName, "5");
		gitlet("add", aFileName);
		gitlet("commit", "fifth commit");
		gitlet("checkout", "master");
		createFile(aFileName, "6");
		gitlet("add", aFileName);
		gitlet("commit", "sixth commit");
		createFile(aFileName, "7");
		gitlet("add", aFileName);
		gitlet("commit", "seventh commit");
		gitlet("reset", "4");
		String logContent = gitlet("log");
		String initialCommit = "initial commit";
		String commitMessage1 = "first commit";
		String commitMessage2 = "second commit";
		String commitMessage3 = "third commit";
		String commitMessage4 = "fourth commit";
		assertArrayEquals(new String[] { commitMessage4, commitMessage3, commitMessage2, commitMessage1, initialCommit },
				extractCommitMessages(logContent));
	}
	@Test
	public void testSwitchBranchLog() {
		gitlet("init");
		String aFileName = TESTING_DIR + "a";
		createFile(aFileName, "1");
		gitlet("add", aFileName);
		gitlet("commit", "first commit");
		createFile(aFileName, "2");
		gitlet("add", aFileName);
		gitlet("commit", "second commit");
		gitlet("branch", "branch1");
		createFile(aFileName, "3");
		gitlet("add", aFileName);
		gitlet("commit", "third commit");
		createFile(aFileName, "4");
		gitlet("add", aFileName);
		gitlet("commit", "fourth commit");
		gitlet("checkout", "branch1");
		createFile(aFileName, "5");
		gitlet("add", aFileName);
		gitlet("commit", "fifth commit");
		createFile(aFileName, "6");
		gitlet("add", aFileName);
		gitlet("commit", "sixth commit");
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {"sixth commit", "fifth commit", "second commit", "first commit", "initial commit" },
				extractCommitMessages(logContent));
		gitlet("checkout", "master");
		String logContentNewBranch = gitlet("log");
		assertArrayEquals(new String[] {"fourth commit", "third commit", "second commit", "first commit", "initial commit" },
				extractCommitMessages(logContentNewBranch));
	}


	@Test

	public void testComplexCheckout() {

	String wugFileName = TESTING_DIR + "wug.txt";

	String wugText = "This is a wug.";

	createFile(wugFileName, wugText);

	gitlet("init");

	gitlet("add", wugFileName);

	gitlet("commit", "added wug");

	writeFile(wugFileName, "This is not a wug.");

	gitlet("add", wugFileName);

	gitlet("commit", "added notwug");

	gitlet("checkout", "1", wugFileName);

	assertEquals(wugText, getText(wugFileName));

	}


	@Test

	public void testTrackedCheckout() {

	String wugFileName = TESTING_DIR + "wug.txt";

	String wugText = "This is a wug.";

	createFile(wugFileName, wugText);

	String wuggyFileName = TESTING_DIR + "wuggy.txt";

	String wuggyText = "This is a wuggy.";

	createFile(wuggyFileName, wuggyText);

	gitlet("init");

	gitlet("add", wugFileName);

	gitlet("commit", "added wug");

	gitlet("add", wuggyFileName);

	gitlet("commit", "added wuggy");

	writeFile(wugFileName, "This is not a wug.");

	writeFile(wuggyFileName, "This is not a wuggy.");

	gitlet("checkout", "2", wugFileName);

	assertEquals(wugText, getText(wugFileName));

	writeFile(wugFileName, "This is not a wug.");

	gitlet("checkout", wugFileName);

	assertEquals(wugText, getText(wugFileName));

	}


	public void testBranchCheckout() {

	gitlet("init");

	String a = TESTING_DIR +"a";

	String aText1 = "1";


	createFile(a, aText1);

	gitlet("add", a);

	gitlet("commit", "first commit with a");

	gitlet("branch", "branch1");


	gitlet("add", a);

	gitlet("commit", "master commit2");

	gitlet("checkout", "branch1");


	String aText2 = "changed a";

	createFile(a, aText2);

	gitlet("add", a);

	gitlet("commit", "branch1 commit2");

	gitlet("checkout", "master");


	gitlet("checkout", "3", "a");

	assertEquals(aText2, getText(a));

	  }

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 **/
	@Test
	public void testMergeConflict() {
		gitlet("init");
//		String aFileName = TESTING_DIR + "a";
		String aFileName = "a";
		String aText1 = "1";

		createFile(aFileName, aText1);
		gitlet("add", aFileName);
		gitlet("commit", "first commit with a");
		gitlet("branch", "branch1");

		String aText2 = "changed a";
		createFile(aFileName, aText2);
		gitlet("add", aFileName);
		gitlet("commit", "second commit with changed a on master");
		gitlet("checkout", "branch1");

		String aText3 = "changed a again";
		createFile(aFileName, aText3);
		gitlet("add", aFileName);
		gitlet("commit", "second commit with changed a on branch1");

		gitlet("merge", "master");

//		String aConflicted = TESTING_DIR + "a.conflicted";
		String aConflicted = "a.conflicted";
		assertEquals(aText2, getText(aConflicted));
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 **/
	@Test
	public void testMergeNoConflict() {
		gitlet("init");
//		String a = TESTING_DIR + "a";
		String a = "a";
		String aText1 = "1";
		
		createFile(a, aText1);
		gitlet("add", a);
		gitlet("commit", "first commit with a");
		gitlet("branch", "branch1");
		
		gitlet("add", a);
		gitlet("commit", "second commit on master did not change a");
		gitlet("checkout", "branch1");
		
		String aText2 = "changed a";
		createFile(a, aText2);
		gitlet("add", a);
		gitlet("commit", "second commit on branch1 changed a");
		gitlet("checkout", "master");
		gitlet("merge", "branch1");
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {"Merged master with branch1.", "second commit on master did not change a",
				"first commit with a", "initial commit" }, extractCommitMessages(logContent));
	}
	
	/**
	 * Test that merge will do nothing if a file had been modified in the current branch
	 * since the split point and does not exist in the given branch.
	 */
	@Test
	public void testMergeDoNothing() {
		gitlet("init");
		String aFileName = "a";
		String aText1 = "1";
		
		createFile(aFileName, aText1);
		gitlet("add", aFileName);
		gitlet("commit", "first commit with a");
		gitlet("branch", "branch1");
		
		String aText2 = "changed a";
		createFile(aFileName, aText2);
		gitlet("add", aFileName);
		gitlet("commit", "second commit on master with changed a");
		
		gitlet("checkout", "branch1");
		gitlet("rm", aFileName);
		gitlet("commit", "second commit on branch1 with untracked/removed a");
		
		gitlet("checkout", "master");
		gitlet("merge", "branch1");
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {"second commit on master with changed a", "first commit with a",
				"initial commit" }, extractCommitMessages(logContent));
	}

//	/**
//	 * Test that the output of log after a rebase includes the commit messages
//	 * from both branches involved in the rebase.
//	 **/
//	@Test
//	public void testRebaseLog() {
//		gitlet("init");
//		String commitMessage1 = "initial commit";
//		String wugFileName = TESTING_DIR + "wug.txt";
//		String wugText = "This is a wg";
//
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "1");
//
//		wugText = "This is a wug";
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "2");
//
//		gitlet("branch", "branch1");
//		gitlet("checkout", "branch1");
//
//		wugText = "This is a wug!";
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "3");
//
//		gitlet("branch", "branch2");
//		gitlet("checkout", "branch2");
//
//		wugText = "This is a wug??!";
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "4");
//
//		gitlet("checkout", "branch1");
//
//		wugText = "This is a wug!!!";
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "5");
//
//		gitlet("checkout", "master");
//
//		wugText = "This is a wug.";
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "6");
//
//		wugText = "This is a wug...";
//		createFile(wugFileName, wugText);
//		gitlet("add", wugFileName);
//		gitlet("commit", "7");
//
//		gitlet("checkout", "branch1");
//		gitlet("rebase", "master");
//
//		String logContent = gitlet("log");
//		assertArrayEquals(new String[] { "5", "3", "7", "6", "2", "1",
//				commitMessage1 }, extractCommitMessages(logContent));
//
//		gitlet("checkout", "master");
//		logContent = gitlet("log");
//		assertArrayEquals(new String[] { "7", "6", "2", "1", commitMessage1 },
//				extractCommitMessages(logContent));
//	}
//
//	/**
//	 * Test that changes in the base branch propagate through the replayed
//	 * branch during a rebase.
//	 **/
//	@Test
//	public void testRebasePropagation() {
//		gitlet("init");
//		String commitMessage1 = "initial commit";
//		String wug1FileName = TESTING_DIR + "wug1.txt";
//		String wug2FileName = TESTING_DIR + "wug2.txt";
//		String wug1Text = "This is a wg";
//		String wug2Text = "this is two wg";
//		
//		createFile(wug1FileName, wug1Text);
//		createFile(wug2FileName, wug2Text);
//		gitlet("add", wug1FileName);
//		gitlet("add", wug2FileName);
//		gitlet("commit", "1");
//		
//		gitlet("branch", "second");
//		gitlet("checkout", "second");
//		wug1Text = "This is a changed wg";
//		createFile(wug1FileName, wug1Text);
//		gitlet("add", wug1FileName);
//		gitlet("commit", "2");
//		
//		gitlet("checkout", "master");
//		wug2Text = "This is two changed wg";
//		createFile(wug2FileName, wug2Text);
//		gitlet("add", wug2FileName);
//		gitlet("commit", "3");
//		
//		gitlet("checkout", "second");
//		gitlet("rebase", "master");
//
//		String logContent = gitlet("log");
//		assertArrayEquals(new String[] { "2", "3", "1", commitMessage1 },
//				extractCommitMessages(logContent));
//
//		assertEquals(wug1Text, getText(wug1FileName));
//		assertEquals(wug2Text, getText(wug2FileName));
//		gitlet("checkout", "1.txt");
//		assertEquals(wug1Text, getText(wug1FileName));
//		gitlet("checkout", "2.txt");
//		assertEquals(wug2Text, getText(wug2FileName));
//	
//	}


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