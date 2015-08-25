import static org.junit.Assert.*;
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
	 * Tests that if the user deleted the folder after committing some files in
	 * it, checkout will rebuild the folder and ensures successful checking out
	 */
	public void testCheckoutDeletedPathInWorkingDir() {
		gitlet("init");
		String commitMessage1 = "first";
		String wugText = "This is a wug";
		File folder = new File(TESTING_DIR + "delete");
		folder.mkdir();
		String wugFileName = TESTING_DIR + "delete/wug.txt";
		createFile(wugFileName, wugText);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);

		folder.delete();
		gitlet("checkout", wugFileName);
	}

	/**
	 * Tests that add adds a tracking file to staging area or track a file that
	 * is currently untracked or file does not exist. Involves init,add and rm.
	 */
	@Test
	public void testAdd() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		File f = new File(GITLET_DIR + "/stage/wug.txt");
		assertTrue(f.exists());
		String birdFileName = TESTING_DIR + "bird.txt";
		String birdText = "This is a bird.";
		createFile(birdFileName, birdText);
		gitlet("add", birdFileName);
		gitlet("rm", birdFileName);
		File f1 = new File(GITLET_DIR + "/stage/bird.txt");
		assertTrue(!f1.exists());
		gitlet("add", birdFileName);
		assertTrue(f1.exists());
		String tigerFileName = TESTING_DIR + "tiger.txt";
		String addContent = gitlet("add", tigerFileName);
		assertEquals("File does not exist.", addContent);
	}

	/**
	 * Tests that commit saves a backup version of file
	 */
	public void testCommit() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String commitContent = gitlet("commit", "first");
		assertEquals("No changes added to the commit.", commitContent);
		gitlet("add", wugFileName);
		String commitContent2 = gitlet("commit");
		assertEquals("Please enter a commit message.", commitContent2);
		gitlet("commit", "first");
		File f1 = new File(GITLET_DIR + "1");
		assertTrue(f1.exists());
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
	 * Tests that a file can be restored from checkout even if the file is not
	 * staged prior to the commit. Involves init,add,commit and checkout.
	 */
	@Test
	public void testCheckout2() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		String birdFileName = TESTING_DIR + "bird.txt";
		String birdText = "This is a bird.";
		createFile(birdFileName, birdText);
		gitlet("add", birdFileName);
		gitlet("commit", "added bird");
		String tigerFileName = TESTING_DIR + "tiger.txt";
		String tigerText = "This is a tiger.";
		createFile(tigerFileName, tigerText);
		gitlet("add", tigerFileName);
		gitlet("commit", "added tiger");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Tests that can checkout[id][file] from an arbitrary commit in graph (even
	 * one in another branch). Involves init,add,commit,branch and checkout.
	 */
	@Test
	public void testCheckout3() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		String birdFileName = TESTING_DIR + "bird.txt";
		String birdText = "This is a bird.";
		createFile(birdFileName, birdText);
		gitlet("add", birdFileName);
		gitlet("commit", "added bird");
		gitlet("branch", "smile");
		gitlet("checkout", "smile");
		String tigerFileName = TESTING_DIR + "tiger.txt";
		String tigerText = "This is a tiger.";
		createFile(tigerFileName, tigerText);
		gitlet("add", tigerFileName);
		gitlet("commit", "added tiger");
		gitlet("checkout", "1", wugFileName);
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
	 * Tests that log adjusts appropriately when switching from one branch to
	 * another. Involves init, add, commit, branch, checkout and log.
	 */
	@Test
	public void testLog2() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "smile");
		gitlet("add", wugFileName);
		String commitMessage3 = "added wug2";
		gitlet("commit", commitMessage3);
		gitlet("checkout", "smile");
		gitlet("add", wugFileName);
		String commitMessage4 = "added wug3";
		gitlet("commit", commitMessage4);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage2,
				commitMessage1 }, extractCommitMessages(logContent));

		gitlet("checkout", "master");

		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(logContent2));
	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 * 
	 */
	@Test
	public void testReset() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String lolFileName = TESTING_DIR + "lol.txt";
		String wugText = "This is a wug.";
		String lolText = " this is lol.";
		createFile(wugFileName, wugText);
		createFile(lolFileName, lolText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("add", lolFileName);
		gitlet("commit", "added lol");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "added lol", "added wug",
				"initial commit" }, extractCommitMessages(logContent1));
		gitlet("reset", "1");
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { "added wug", "initial commit" },
				extractCommitMessages(logContent2));
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testConflicted() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "smile");
		gitlet("checkout", "smile");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug to new branch");
		gitlet("checkout", "master");
		writeFile(wugFileName, "Is this a wug?");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug to master branch");
		gitlet("merge", "smile");
		File conflicted = new File(wugFileName + ".conflicted");
		assertTrue(conflicted.exists());
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testMerge() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String lolFileName = TESTING_DIR + "lol.txt";
		String wugText = "This is a wug.";
		String lolText = " this is lol.";
		createFile(wugFileName, wugText);
		createFile(lolFileName, lolText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "smile");
		gitlet("checkout", "smile");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug to new branch");
		gitlet("checkout", "master");
		gitlet("add", lolFileName);
		gitlet("commit", "added lol");
		gitlet("merge", "smile");
		File conflicted = new File(wugFileName + ".conflicted");
		assertFalse(conflicted.exists());
		assertEquals("This is not a wug.", getText(TESTING_DIR + "wug.txt"));
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 * 
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testRebase() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "a.txt";
		String wugText = "This is a.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "first");
		gitlet("branch", "temp");
		gitlet("branch", "smile");
		gitlet("checkout", "smile");
		wugFileName = TESTING_DIR + "b.txt";
		wugText = "This is b.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "second");
		wugFileName = TESTING_DIR + "c.txt";
		wugText = "This is c.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "third");
		gitlet("checkout", "master");
		wugFileName = TESTING_DIR + "d.txt";
		wugText = "This is d.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "fourth");

		gitlet("branch", "sunny");
		gitlet("checkout", "sunny");
		wugFileName = TESTING_DIR + "e.txt";
		wugText = "This is e.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "fifth");
		gitlet("checkout", "temp");
		gitlet("checkout", "smile");
		File test = new File(TESTING_DIR + "c.txt");
		File test1 = new File(TESTING_DIR + "b.txt");
		File test2 = new File(TESTING_DIR + "d.txt");
		if (test.exists()) {
			test.delete();
		}
		if (test1.exists()) {
			test1.delete();
		}
		if (test2.exists()) {
			test2.delete();
		}
		gitlet("rebase", "master");
		assertTrue(test.exists());
		assertTrue(test1.exists());
		assertTrue(test2.exists());
		gitlet("checkout", "master");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "fourth", "first", "initial commit" },
				extractCommitMessages(logContent));
		gitlet("checkout", "smile");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "third", "second", "fourth", "first",
				"initial commit" }, extractCommitMessages(logContent));
		test = new File(GITLET_DIR + "7/c.txt");
		assertTrue(test.exists());
		test = new File(TESTING_DIR + "c.txt");
		if (test.exists()) {
			test.delete();
		}
		gitlet("checkout", "master");
		assertFalse(test.exists());
		test = new File(TESTING_DIR + "d.txt");
		if (test.exists()) {
			test.delete();
		}
		gitlet("checkout", "smile");
		assertTrue(test.exists());
		test = new File(TESTING_DIR + "b.txt");
		if (test.exists()) {
			test.delete();
		}
		gitlet("checkout", "2", TESTING_DIR + "b.txt");
		assertTrue(test.exists());

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