import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GitletTest {

	// Used methods and instance variables from test GitletTest.java example
	private static final String GITLET_DIR = ".gitlet/";
	private static final String TESTING_DIR = "test_files/";

	/* matches either unix/mac or windows line separators */
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

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

	// For use in extractStatusContents method;
	private static final String BRANCHES = "=== Branches ===";
	private static final String STAGED_FILES = "=== Staged Files ===";
	private static final String MARKED_FOR_UNTRACKING = "=== Files Marked for Untracking ===";

	/**
	 * Returns an array of String arrays associated with what status has
	 * printed. content[0] = branches, content[1] = staged files, and content[2]
	 * = files marked for untracking
	 */
	private static String[][] extractStatusContents(String statusOutput) {
		String[][] content = new String[3][];
		String[] split1 = statusOutput.split(STAGED_FILES);
		String[] split2 = split1[0].split(BRANCHES);
		String[] split3 = split1[1].split(MARKED_FOR_UNTRACKING);
		String[] branchLines = split2[1].split(LINE_SEPARATOR);
		if (branchLines.length > 1) {
			content[0] = new String[branchLines.length - 1];
			for (int i = 1, j = 0; i < branchLines.length; i += 1, j += 1) {
				content[0][j] = branchLines[i];
			}
		}
		String[] stagedLines = split3[0].split(LINE_SEPARATOR);
		if (stagedLines.length > 1) {
			content[1] = new String[stagedLines.length - 1];
			for (int i = 1, j = 0; i < stagedLines.length; i += 1, j += 1) {
				content[1][j] = stagedLines[i];
			}
		}
		if (split3.length > 1) {
			String[] markedLines = split3[1].split(LINE_SEPARATOR);
			content[2] = new String[markedLines.length - 1];
			for (int i = 1, j = 0; i < markedLines.length; i += 1, j += 1) {
				content[2][j] = markedLines[i];
			}
		}
		return content;
	}

	/**
	 * Returns a string array of commit ID's when find is used with a commit
	 * Message that multiple commits share.
	 */
	private String[] extractFind(String findContent) {
		return findContent.split(LINE_SEPARATOR);
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
	 * Tests that init creates a .gitlet directory and an initial commit.
	 */
	@Test
	public void testInitialize() {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());

		String commitMessage = "initial commit";
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage },
				extractCommitMessages(logContent));
	}

	/**
	 * Tests some basic functionality of commit and checkout commands;
	 */
	@Test
	public void testCommitandCheckout() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		assertEquals(command("java", "Gitlet", "init"),
				"A gitlet version control system already exists in the current directory.\n");
		assertEquals(command("java", "Gitlet"), "Please enter a command.\n");

		// Tests commit with no argument
		assertEquals(command("java", "Gitlet", "commit"),
				"Please enter a command.\n"); // does nothing
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage0 },
				extractCommitMessages(logContent));

		String masonFileName = TESTING_DIR + "mason.txt";
		String masonText1 = "Bonjour";
		createFile(masonFileName, masonText1);

		// Tests commit with empty staging
		String commitMessage1 = "Added Mason";
		assertEquals(command("java", "Gitlet", "commit", commitMessage1),
				"No changes added to the commit.\n"); // does nothing
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage0 },
				extractCommitMessages(logContent));

		// Tests adding
		gitlet("add", masonFileName);
		assertEquals(command("java", "Gitlet", "add", "nonexistentfile.txt"),
				"File does not exist.\n"); // does nothing
		gitlet("commit", commitMessage1);

		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		gitlet("add", "nonexistentfile.txt");
		String someCommitMessage = "I'm useless";
		gitlet("commit", someCommitMessage); // does nothing

		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		// Tests multiple files
		String davidFileName = TESTING_DIR + "david.txt";
		String davidText1 = "Did you miss me?";
		createFile(davidFileName, davidText1);
		gitlet("add", davidFileName);
		String commitMessage2 = "Added David";
		gitlet("commit", commitMessage2);

		String davidText2 = "I'm back";
		writeFile(davidFileName, davidText2);
		String masonText2 = "Hi there";
		writeFile(masonFileName, masonText2);

		gitlet("add", davidFileName);
		gitlet("add", masonFileName);
		String commitMessage3 = "Hello again";
		gitlet("commit", commitMessage3);

		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		gitlet("add", masonFileName);
		String commitMessage4 = "Testing";
		gitlet("commit", commitMessage4);

		gitlet("log");
		gitlet("checkout", "2", davidFileName);
		assertEquals(davidText1, getText(davidFileName));
		gitlet("checkout", "1", masonFileName);
		assertEquals(masonText1, getText(masonFileName));

		// Tests failure cases
		assertEquals(
				command("java", "Gitlet", "checkout", "nonexistentfile.txt"),
				"File does not exist in the most recent commit, or no such branch exists.\n");
		assertEquals(
				command("java", "Gitlet", "checkout", "10", masonFileName),
				"No commit with that id exists.\n");
		assertEquals(
				command("java", "Gitlet", "checkout", "4",
						"nonexistentfile.txt"),
				"File does not exist in that commit.\n");

	}

	/**
	 * Tests functionality of commit and checkout commands with more specific
	 * cases.
	 */
	@Test
	public void testCommitandCheckout2() {
		gitlet("init");

		// Test that a file that has been committed at some point can be
		// restored
		// by checking it out from the commit it was committed at.
		String masonFileName = TESTING_DIR + "mason.txt";
		String masonText1 = "Just some guy";
		createFile(masonFileName, masonText1);
		gitlet("add", masonFileName);
		String commitMessage1 = "Added Mason";
		gitlet("commit", commitMessage1);
		gitlet("checkout", masonFileName);

		String masonText2 = "Just a passerby";
		writeFile(masonFileName, masonText2);
		gitlet("add", masonFileName);
		String commitMessage2 = "Altered Mason";
		gitlet("commit", commitMessage2);

		String masonText3 = "Floating by";
		writeFile(masonFileName, masonText3);
		gitlet("add", masonFileName);
		String commitMessage3 = "Altered Mason again";
		gitlet("commit", commitMessage3);

		gitlet("checkout", masonFileName);
		assertEquals(masonText3, getText(masonFileName));

		gitlet("checkout", "1", masonFileName);
		assertEquals(masonText1, getText(masonFileName));

		gitlet("checkout", "2", masonFileName);
		assertEquals(masonText2, getText(masonFileName));

		// Test that a file that has been committed at some point can be
		// restored by checking
		// it out from a commit that tracks that version of the file, even if
		// the
		// file wasn't staged prior to that commit.
		String masonText4 = "Je m'appelle mason";
		createFile(masonFileName, masonText4);

		gitlet("add", masonFileName);
		String commitMessage4 = "Altered Mason once again";
		gitlet("commit", commitMessage4);
		gitlet("checkout", "4", masonFileName);
		assertEquals(masonText4, getText(masonFileName));

		gitlet("checkout", "2", masonFileName);
		assertEquals(masonText2, getText(masonFileName));

		// Test that you can checkout [id] [file] from an arbitrary commit
		// in the graph (even one in another branch).

		gitlet("branch", "David");
		gitlet("checkout", "David");

		String davidFileName = TESTING_DIR + "david.txt";
		String davidText1 = "Hi, I'm David";
		createFile(davidFileName, davidText1);
		gitlet("add", davidFileName);
		String commitMessage5 = "Changed David";
		gitlet("commit", commitMessage5);

		gitlet("checkout", "1", masonFileName);
		assertEquals(masonText1, getText(masonFileName));

		gitlet("checkout", "3", masonFileName);
		assertEquals(masonText3, getText(masonFileName));
	}

	/**
	 * Tests functionality of log command
	 */
	@Test
	public void testLog() {
		gitlet("init");
		String commitMessage0 = "initial commit";

		String jeffreyFileName = TESTING_DIR + "jeffrey.txt";
		String jeffreyText1 = "Hey there";
		createFile(jeffreyFileName, jeffreyText1);

		String commitMessage1 = "Jeffrey's big day";
		gitlet("add", jeffreyFileName);
		gitlet("commit", commitMessage1);

		String jeffreyText2 = "It's a good day today";
		writeFile(jeffreyFileName, jeffreyText2);
		gitlet("add", jeffreyFileName);
		String commitMessage2 = "Changed Jeffrey";
		gitlet("commit", commitMessage2);

		String davidBranch = "David";
		gitlet("branch", davidBranch);
		gitlet("checkout", davidBranch);
		String davidFileName = TESTING_DIR + "david.txt";
		String davidText1 = "Hi, I'm David";
		createFile(davidFileName, davidText1);

		gitlet("add", davidFileName);
		String commitMessage3 = "David's here";
		gitlet("commit", commitMessage3);

		String davidText2 = "I'm just a normal guy";
		writeFile(davidFileName, davidText2);
		gitlet("add", davidFileName);
		String commitMessage4 = "Changed David";
		gitlet("commit", commitMessage4);

		gitlet("checkout", "master");

		String davidText3 = "Hello again";
		writeFile(davidFileName, davidText3);
		gitlet("add", davidFileName);
		String commitMessage5 = "Added David to master branch";
		gitlet("commit", commitMessage5);

		// Test that log adjusts appropriately when switching from one branch to
		// another.
		String logContentMaster = gitlet("log");
		assertArrayEquals(new String[] { commitMessage5, commitMessage2,
				commitMessage1, commitMessage0 },
				extractCommitMessages(logContentMaster));

		gitlet("checkout", davidBranch);
		String logContentDavid = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContentDavid));

		// Test that resetting backward appropriately changes the output of log.
		gitlet("reset", "3");
		logContentDavid = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1, commitMessage0 },
				extractCommitMessages(logContentDavid));

		gitlet("checkout", "master");
		gitlet("reset", "2");
		logContentMaster = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1,
				commitMessage0 }, extractCommitMessages(logContentMaster));
	}

	/**
	 * Tests the functionality of reset command
	 */
	@Test
	public void testReset() {
		gitlet("init");

		String jeffreyFileName = TESTING_DIR + "jeffrey.txt";
		String jeffreyText1 = "Hello, I'm Jeffrey";
		createFile(jeffreyFileName, jeffreyText1);

		gitlet("add", jeffreyFileName);
		String commitMessage1 = "Added Jeffrey";
		gitlet("commit", commitMessage1);

		String jeffreyText2 = "It's nice to meet you";
		writeFile(jeffreyFileName, jeffreyText2);
		gitlet("add", jeffreyFileName);
		String commitMessage2 = "Changed Jeffrey";
		gitlet("commit", commitMessage2);

		String jeffreyText3 = "It's been a while";
		writeFile(jeffreyFileName, jeffreyText3);
		gitlet("add", jeffreyFileName);
		String commitMessage3 = "Altered Jeffrey again";
		gitlet("commit", commitMessage3);

		gitlet("checkout", jeffreyFileName);
		assertEquals(jeffreyText3, getText(jeffreyFileName));

		gitlet("reset", "1");
		assertEquals(jeffreyText1, getText(jeffreyFileName));

		gitlet("checkout", "3", jeffreyFileName);
		String jeffreyText4 = "I missed you";
		writeFile(jeffreyFileName, jeffreyText4);
		gitlet("add", jeffreyFileName);
		String commitMessage4 = "Again...";
		gitlet("commit", commitMessage4);

		gitlet("checkout", jeffreyFileName);
		assertEquals(jeffreyText4, getText(jeffreyFileName));

		gitlet("reset", "2");
		assertEquals(jeffreyText2, getText(jeffreyFileName));

		// Test failure case
		// assertEquals(command("java", "Gitlet", "reset", "20"),
		// "No commit with that id exists.\n");
	}

	/**
	 * Tests functionality of remove command
	 */
	@Test
	public void testRemove() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		gitlet("rm", "nonexistentfile.txt");
		assertEquals(command("java", "Gitlet", "rm", "nonexistentfile.txt"),
				"No reason to remove the file.\n");

		String masonFileName = TESTING_DIR + "mason.txt";
		String masonText1 = "I'm getting tired";
		createFile(masonFileName, masonText1);
		assertEquals(command("java", "Gitlet", "rm", "nonexistentfile.txt"),
				"No reason to remove the file.\n");

		String danielFileName = "daniel.txt";
		String danielText1 = "A new player has entered";
		createFile(danielFileName, danielText1);

		gitlet("add", masonFileName);
		gitlet("add", danielFileName);
		gitlet("rm", danielFileName);
		String commitMessage1 = "only added Mason";
		gitlet("commit", commitMessage1);

		String masonText2 = "Hey guys";
		writeFile(masonFileName, masonText2);
		String danielText2 = "I know I've changed";
		writeFile(danielFileName, danielText2);

		gitlet("checkout", "1", masonFileName);
		assertEquals(masonText1, getText(masonFileName));
		gitlet("checkout", "1", danielFileName);
		assertEquals(danielText2, getText(danielFileName)); // did not change

		gitlet("reset", "1");
		assertEquals(masonText1, getText(masonFileName));
		assertEquals(danielText2, getText(danielFileName)); // still did not
															// change

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}

	/**
	 * Tests functionality of remove branch command
	 */
	@Test
	public void testRemoveBrance() {
		gitlet("init");
		gitlet("branch", "Mason");
		gitlet("branch", "David");
		gitlet("branch", "Jeffrey");
		String[] curr = { "*master", "Mason", "David", "Jeffrey" };
		ArrayList<String> currBranches = new ArrayList<String>(
				Arrays.asList(curr));

		String statusMessage = gitlet("status");
		String[][] statusContent1 = extractStatusContents(statusMessage);
		for (String elem : statusContent1[0]) {
			assertTrue(currBranches.contains(elem));
		}

		gitlet("rm-branch", "Mason");
		gitlet("rm-branch", "David");
		assertEquals(command("java", "Gitlet", "rm-branch", "master"),
				"Cannot remove the current branch.\n"); // cannot remove current
														// branch
		assertEquals(
				command("java", "Gitlet", "rm-branch", "nonexistentbranch"),
				"A branch with that name does not exist.\n"); // connot remove
																// nonexistent
																// branch

		String[] curr2 = { "*master", "Jeffrey" };
		currBranches = new ArrayList<String>(Arrays.asList(curr2));

		statusMessage = gitlet("status");
		String[][] statusContent2 = extractStatusContents(statusMessage);
		for (String elem : statusContent2[0]) {
			assertTrue(currBranches.contains(elem));
		}
	}

	/**
	 * Tests the functionality of global-log command
	 */
	@Test
	public void testGlobalLog() {
		gitlet("init");
		String commitMessage0 = "initial commit";

		String davidFileName = TESTING_DIR + "david.txt";
		String davidText1 = "Welcome Back";
		createFile(davidFileName, davidText1);

		gitlet("add", davidFileName);
		String commitMessage1 = "added David";
		gitlet("commit", commitMessage1);

		gitlet("add", davidFileName);
		String commitMessage2 = "added David again";
		gitlet("commit", commitMessage2);

		gitlet("add", davidFileName);
		String commitMessage3 = "again, really?";
		gitlet("commit", commitMessage3);

		String gLogContent = gitlet("global-log");
		String[] commitMessages = extractCommitMessages(gLogContent);
		ArrayList<String> commitsSoFar = new ArrayList<String>(
				Arrays.asList(commitMessages));

		String[] shouldContain1 = { commitMessage0, commitMessage1,
				commitMessage2, commitMessage3 };
		for (String elem : shouldContain1) {
			assertTrue(commitsSoFar.contains(elem));
		}

		String jeffreyFileName = TESTING_DIR + "jeffrey.txt";
		String jeffreyText1 = "Did you forget about me?";
		createFile(jeffreyFileName, jeffreyText1);

		gitlet("branch", "Jeffrey");
		gitlet("checkout", "Jeffrey");

		gitlet("add", jeffreyFileName);
		String commitMessage4 = "added Jeffrey";
		gitlet("commit", commitMessage4);

		gitlet("add", jeffreyFileName);
		String commitMessage5 = "this is getting old";
		gitlet("commit", commitMessage5);

		gLogContent = gitlet("global-log");
		commitMessages = extractCommitMessages(gLogContent);
		commitsSoFar = new ArrayList<String>(Arrays.asList(commitMessages));

		String[] shouldContain2 = { commitMessage0, commitMessage1,
				commitMessage2, commitMessage3, commitMessage4, commitMessage5 };
		for (String elem : shouldContain2) {
			assertTrue(commitsSoFar.contains(elem));
		}

		gitlet("checkout", "master");
		gitlet("add", davidFileName);
		String commitMessage6 = "Seriously";
		gitlet("commit", commitMessage6);

		gitlet("add", davidFileName);
		String commitMessage7 = "You need to stop";
		gitlet("commit", commitMessage7);

		gLogContent = gitlet("global-log");
		commitMessages = extractCommitMessages(gLogContent);
		commitsSoFar = new ArrayList<String>(Arrays.asList(commitMessages));

		String[] shouldContain3 = { commitMessage0, commitMessage1,
				commitMessage2, commitMessage3, commitMessage4, commitMessage5,
				commitMessage6, commitMessage7 };
		for (String elem : shouldContain3) {
			assertTrue(commitsSoFar.contains(elem));
		}
	}

	/**
	 * Tests functionality of status command
	 */
	@Test
	public void testStatus() {
		gitlet("init");
		String masterBranch = "master";

		String statusMessage = gitlet("status");
		String[][] statusContents1 = extractStatusContents(statusMessage);

		ArrayList<String> currBranches = new ArrayList<String>();
		ArrayList<String> currStaged = new ArrayList<String>();
		ArrayList<String> currMarked = new ArrayList<String>();

		currBranches.add("*" + masterBranch);
		for (String elem : statusContents1[0]) {
			assertTrue(currBranches.contains(elem));
		}
		assertNull(statusContents1[1]); // no staged files
		assertNull(statusContents1[2]); // no files are marked for untracking

		// test that untracking a file is reflected in status
		String davidFileName = "david.txt";
		String davidText = "This is getting old";
		createFile(davidFileName, davidText);
		gitlet("rm", davidFileName);
		currMarked.add(davidFileName);

		String masonFileName = "mason.txt";
		String masonText = "Hello there";
		createFile(masonFileName, masonText);
		gitlet("add", masonFileName);
		currStaged.add(masonFileName);

		gitlet("branch", "Jeffrey");
		currBranches.add("Jeffrey");

		statusMessage = gitlet("status");
		String[][] statusContents2 = extractStatusContents(statusMessage);

		for (String elem : statusContents2[0]) {
			assertTrue(currBranches.contains(elem));
		}
		for (String elem : statusContents2[1]) {
			assertTrue(currStaged.contains(elem));
		}
		for (String elem : statusContents2[2]) {
			assertTrue(currMarked.contains(elem));
		}

		// test that branch switch reflects in status
		gitlet("checkout", "Jeffrey");

		String[] curr = { "master", "*Jeffrey" };
		currBranches = new ArrayList<String>(Arrays.asList(curr));

		// test that remove from staging reflects in status
		gitlet("rm", masonFileName);
		currStaged = null;

		statusMessage = gitlet("status");
		String[][] statusContents3 = extractStatusContents(statusMessage);

		for (String elem : statusContents3[0]) {
			assertTrue(currBranches.contains(elem));
		}
		assertNull(statusContents3[1]);
		for (String elem : statusContents3[2]) {
			assertTrue(currMarked.contains(elem));
		}
	}

	/**
	 * Tests the functionality of the find command.
	 */
	@Test
	public void testFind() {
		gitlet("init");
		String commitMessage0 = "initial commit";

		String masonFileName = TESTING_DIR + "mason.txt";
		String masonText = "Hello again";
		createFile(masonFileName, masonText);

		gitlet("add", masonFileName);
		String commitMessage1 = "added Mason";
		gitlet("commit", commitMessage1);

		gitlet("add", masonFileName);
		String commitMessage2 = "added Mason again";
		gitlet("commit", commitMessage2);

		gitlet("add", masonFileName);
		String commitMessage3 = "yes, again";
		gitlet("commit", commitMessage3);

		String findMessage = gitlet("find", commitMessage0);
		assertEquals(findMessage, "0");

		findMessage = gitlet("find", commitMessage1);
		assertEquals(findMessage, "1");

		findMessage = gitlet("find", commitMessage2);
		assertEquals(findMessage, "2");

		findMessage = gitlet("find", commitMessage3);
		assertEquals(findMessage, "3");

		// test using find with commits that share the same commit message

		gitlet("add", masonFileName);
		gitlet("commit", commitMessage3);

		gitlet("add", masonFileName);
		gitlet("commit", commitMessage3);

		String[] curr = { "3", "4", "5" };
		ArrayList<String> currIDs = new ArrayList<String>(Arrays.asList(curr));

		findMessage = gitlet("find", commitMessage3);
		String[] findContents1 = extractFind(findMessage);
		for (String elem : findContents1) {
			assertTrue(currIDs.contains(elem));
		}

		gitlet("add", masonFileName);
		gitlet("commit", commitMessage2);

		gitlet("add", masonFileName);
		gitlet("commit", commitMessage2);

		String[] curr2 = { "2", "6", "7" };
		currIDs = new ArrayList<String>(Arrays.asList(curr2));

		findMessage = gitlet("find", commitMessage2);
		String[] findContents2 = extractFind(findMessage);
		for (String elem : findContents2) {
			assertTrue(currIDs.contains(elem));
		}

		assertEquals(
				command("java", "Gitlet", "find", "Nonexistent commit message"),
				"Found no commit with that message.\n");

	}

	/**
	 * Tests the functionality of the merge command
	 */
	@Test
	public void testMerge1() {
		gitlet("init");

		String masonFileName = "mason.txt";
		String masonText1 = "Hey there";
		createFile(masonFileName, masonText1);

		String davidFileName = "david.txt";
		String davidText1 = "Almsot done";
		createFile(davidFileName, davidText1);

		// Test basic merge
		gitlet("add", masonFileName);
		String commitMessage1 = "commit 1";
		gitlet("commit", commitMessage1);

		gitlet("branch", "Mason");
		gitlet("add", masonFileName);
		String commitMessage2 = "master branch commit..";
		gitlet("commit", commitMessage2);
		gitlet("checkout", "Mason");

		String masonText2 = "changed mason";
		writeFile(masonFileName, masonText2);
		gitlet("add", masonFileName);
		String commitMessage3 = "Mason branch committ..";
		gitlet("commit", commitMessage3);

		gitlet("checkout", "master");
		String commitMessage4 = "Merged master with Mason.";
		gitlet("merge", "Mason");
		assertEquals(gitlet("find", commitMessage4), "4");

		// Test that merge will generate a .conflicted file if a file
		// has been modified in both branches since the split point.
		gitlet("branch", "Scott");

		String masonText3 = "Something else";
		writeFile(masonFileName, masonText3);
		gitlet("add", masonFileName);
		String commitMessage5 = "Try Again";
		gitlet("commit", commitMessage5);

		gitlet("checkout", "Scott");
		String masonText4 = "On no";
		writeFile(masonFileName, masonText4);
		gitlet("add", masonFileName);
		String commitMessage6 = "Something has changed";
		gitlet("commit", commitMessage6);

		gitlet("checkout", "master");
		assertEquals(command("java", "Gitlet", "merge", "Scott"),
				"Encountered a merge conflict.\n");
		File f = new File(masonFileName + ".conflicted");
		assertTrue(f.exists());
		assertEquals(command("java", "Gitlet", "branch", "Chan"),
				"Cannot do this command until the merge conflict has been resolved.\n");
	}

	/**
	 * Tests functionality of merge command part II
	 */
	@Test
	public void testMerge2() {
		gitlet("init");
		createFile(TESTING_DIR + "wug.txt", "");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "mastercommit");
		gitlet("branch", "b");
		gitlet("checkout", "b");
		writeFile(TESTING_DIR + "wug.txt", "made change");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "branchcommit");
		gitlet("checkout", "master");
		gitlet("merge", "b");
		File f = new File(TESTING_DIR + "wug.txt.conflicted");
		assertTrue(!f.exists());
		assertEquals(gitlet("find", "Merged master with b."), "3");
	}

	/**
	 * Tests functionality of rebase command
	 */
	@Test
	public void testRebase() {
		gitlet("init");

		String masonFileName = TESTING_DIR + "mason.txt";
		String masonText1 = "Hello again";
		createFile(masonFileName, masonText1);

		// Test error messages
		assertEquals(command("java", "Gitlet", "rebase", "nonexistentbranch"),
				"A branch with that name does not exist.\n");
		assertEquals(command("java", "Gitlet", "rebase", "master"),
				"Cannot rebase a branch onto itself.\n");

		gitlet("add", masonFileName);
		String commitMessage1 = "added Mason";
		gitlet("commit", commitMessage1);

		gitlet("branch", "Mason");
		gitlet("branch", "David");

		gitlet("checkout", "master");
		String masonText2 = "changed mason";
		writeFile(masonFileName, masonText2);
		gitlet("add", masonFileName);
		String commitMessage2 = "added to master";
		gitlet("commit", commitMessage2);

		gitlet("add", masonFileName);
		String commitMessage3 = "changed nothing";
		gitlet("commit", commitMessage3);
		String[] masterLog = extractCommitMessages(gitlet("log"));

		gitlet("checkout", "Mason");
		String masonText3 = "This is tiring";
		writeFile(masonFileName, masonText3);
		gitlet("add", masonFileName);
		String commitMessage4 = "added to mason branch";
		gitlet("commit", commitMessage4);

		gitlet("add", masonFileName);
		String commitMessage5 = "did not change anything";
		gitlet("commit", commitMessage5);
		String[] masonLog = extractCommitMessages(gitlet("log"));

		gitlet("rebase", "master");
		assertEquals(command("java", "Gitlet", "rebase", "master"),
				"Already up-to-date.\n");
		String[] rebaseLog = extractCommitMessages(gitlet("log"));

		gitlet("checkout", "David");
		writeFile(masonFileName, masonText1);
		gitlet("add", masonFileName);
		String commitMessage6 = "Just another test";
		gitlet("commit", commitMessage6);

		gitlet("add", masonFileName);
		String commitMessage7 = "Almost over";
		gitlet("commit", commitMessage7);

		// Test that the output of log after a rebase includes the commit
		// messages
		// from both branches involved in the rebase.
		ArrayList<String> rebaseLogContent = new ArrayList<String>(
				Arrays.asList(rebaseLog));
		for (String elem : masterLog) {
			assertTrue(rebaseLogContent.contains(elem));
		}
		for (String elem : masonLog) {
			assertTrue(rebaseLogContent.contains(elem));
		}

		// Test that changes in the base branch propagate through the replayed
		// branch during a rebase.
		gitlet("rebase", "master");
		gitlet("checkout", masonFileName);
		assertEquals(masonText2, getText(masonFileName));
	}

}