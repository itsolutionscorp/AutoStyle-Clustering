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
 * @authors Alex Yao: cs61bl-cc, Jennifer Chen: cs61bl-bx, Kai-li Yen: cs61bl-el
 * 
 *          Some code adapted from StackOverflow and Joseph Moghadam's Gitlet
 *          Test:
 * 
 *          http://stackoverflow.com/questions
 *          /779519/delete-files-recursively-in-java
 * 
 *          http://stackoverflow.com/questions/326390/how-to-create-a-java-
 *          string -from-the-contents-of-a-file
 * 
 *          http://stackoverflow.com/questions/1119385/junit-test-for-system-
 *          out- println
 * 
 */
public class GitletTest {
	private static final String GITLET_DIR = ".gitlet/";
	private static final String TESTING_DIR = "test_files/";
	private static final String STAGE_DIR = ".gitlet/stage/";

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
	 * Tests that init automatically creates the initial commit node Involves
	 * init and log.
	 */
	@Test
	public void testInitCommit() throws IOException {
		gitletFast("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
		String commitMessage1 = "initial commit";
		String logContent = gitletFast("log");
		assertArrayEquals(new String[] { commitMessage1 }, extractCommitMessages(logContent));
	}

	/**
	 * Tests that add places a file in the stage.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testAdd() throws IOException {
		gitletFast("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitletFast("add", wugFileName);
		File w = new File(STAGE_DIR + wugFileName);
		assertTrue(w.exists());
	}

	/**
	 * Tests that log after one commit conforms to the format in the project
	 * description. Involves init, add, commit, and log.
	 */

	@Test
	public void testBasicLog() throws IOException {
		gitletFast("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitletFast("add", wugFileName);
		String commitMessage2 = "added wug";
		gitletFast("commit", commitMessage2);
		String logContent = gitletFast("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitletFast("add", pugFileName);
		String commitMessage3 = "added pug";
		gitletFast("commit", commitMessage3);
		String logContent2 = gitletFast("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent2));
	}

	/**
	 * Tests that global log contains all of the commits. Involves init, add,
	 * branch, checkout, commit, and global log.
	 */
	@Test
	public void testGlobalLog() throws IOException {
		gitletFast("init");
		String commitMessage1 = "initial commit"; // initial commit
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitletFast("add", wugFileName);
		String commitMessage2 = "added wug";
		gitletFast("commit", commitMessage2); // second commit, master branch,
												// has wug
		gitletFast("branch", "branch2");
		gitletFast("checkout", "branch2"); // now on branch2
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitletFast("add", pugFileName);
		String commitMessage3 = "added pug";
		gitletFast("commit", commitMessage3); // third commit, branch2, has
												// pug/wug
		String hugFileName = TESTING_DIR + "hug.txt";
		String hugText = "This is a hug.";
		createFile(hugFileName, hugText);
		gitletFast("add", hugFileName);
		String commitMessage4 = "added hug";
		gitletFast("commit", commitMessage4); // fourth commit, branch2, has
												// pug/wug/hug
		gitletFast("checkout", "master"); // now on master branch
		String mugFileName = TESTING_DIR + "mug.txt";
		String mugText = "This is a mug.";
		createFile(mugFileName, mugText);
		gitletFast("add", mugFileName);
		String commitMessage5 = "added mug";
		gitletFast("commit", commitMessage5); // fifth commit, master, has
												// wug/mug

		String logContentMaster = gitletFast("log");
		assertArrayEquals(new String[] { commitMessage5, commitMessage2, commitMessage1 },
				extractCommitMessages(logContentMaster));
		gitletFast("checkout", "branch2"); // now on branch2
		String logContentB2 = gitletFast("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContentB2));
		String globalLogContent = gitletFast("global-log"); // global log should
															// have all messages
		assertArrayEquals(
				new String[] { commitMessage1, commitMessage2, commitMessage3, commitMessage4, commitMessage5 },
				extractCommitMessages(globalLogContent));
	}

	/**
	 * Tests that before a commit, if no file is added and rm is called, an
	 * error message prints. Also tests that if a file is added and removed
	 * before a commit, after the commit, the user cannot checkout the file
	 * because it does not exist in the commit.
	 * 
	 * Involves init, add, commit, checkout, and rm.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testStageRemove() throws IOException {
		gitletFast("init");
		String initialMessage = "initial commit";

		// wug
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String err1 = gitletFast("rm", wugFileName);
		assertTrue(err1.equals("No reason to remove the file."));

		gitletFast("add", wugFileName);

		// pug
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitletFast("add", pugFileName);

		gitletFast("rm", pugFileName);
		String commitMessage1 = "should only have wug";
		gitletFast("commit", commitMessage1);

		// t
		String err2 = gitletFast("checkout", pugFileName);
		assertTrue(err2.equals("File does not exist in the most recent commit, or no such branch exists."));

	}

	/**
	 * Tests that after a file is removed it will not exist in the next commit.
	 * Involves init, add, checkout, and commit.
	 */
	@Test
	public void testCommitRemove() throws IOException {
		gitletFast("init");

		// wug
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitletFast("add", wugFileName); // added wug

		gitletFast("commit", "commit wug");
		gitletFast("rm", wugFileName); //

		// pug
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitletFast("add", pugFileName); // added pug

		gitletFast("commit", "should have pug only.");

		String err1 = gitletFast("checkout", wugFileName);
		assertTrue(err1.equals("File does not exist in the most recent commit, or no such branch exists."));

	}

	/**
	 * Tests that rebase follows the description in the project description.
	 * Involves init, add, commit, checkout and rebase.
	 */
	@Test
	public void testRebase() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String file1 = TESTING_DIR + "a.txt";
		String file2 = TESTING_DIR + "b.txt";

		createFile(file1, "1");
		createFile(file2, "2");
		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "first on master");
		gitlet("branch", "branch2");

		createFile(file1, "1");
		createFile(file2, "2");
		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "master branched off");
		gitlet("checkout", "branch2");

		createFile(file2, "bchange2");
		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "duplicate on branch2");

		createFile(file1, "1b2");
		createFile(file2, "2b2");

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "branch2 now off - changed a b");
		gitlet("checkout", "master");

		createFile(file1, "1 m2");
		createFile(file2, "2 m2");

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "switch to master");

		gitlet("checkout", "branch2");
		gitlet("rebase", "master");

		assertEquals("1b2", getText(file1));
		assertEquals("2b2", getText(file2));
	}

	/**
	 * Tests the edge cases of rebase, along with ability to handle files in a
	 * nested folder structure. Tests that the correct files are propagated if
	 * the files at the head of the given branch have been changed since the
	 * split point.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testAdvancedRebase() throws IOException {
		gitletFast("init");
		command("mkdir", TESTING_DIR + "nest1");
		command("mkdir", TESTING_DIR + "nest1/nest2");
		command("mkdir", TESTING_DIR + "nest1/nest2/nest3");
		command("mkdir", TESTING_DIR + "nest1/nest2/nest3/nest4");
		createFile(TESTING_DIR + "nest1/a", "a1");
		createFile(TESTING_DIR + "nest1/nest2/a", "a1.a2");
		createFile(TESTING_DIR + "nest1/nest2/nest3/nest4/a", "a1.a2.a3.a4");
		File f = new File(TESTING_DIR + "nest1/a");
		assertTrue(f.exists());
		File a = new File(TESTING_DIR + "nest1/nest2/a");
		assertTrue(a.exists());
		File b = new File(TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		assertTrue(b.exists());
		// done creating initial files
		gitletFast("add", TESTING_DIR + "nest1/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "committed 'a' files.");
		gitletFast("branch", "branch2");
		// going to add the same files...
		gitletFast("add", TESTING_DIR + "nest1/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "committed the same 'a' files.");
		// change the file contents of n1/n2/n3/n4/a
		gitletFast("checkout", "branch2");
		writeFile(TESTING_DIR + "nest1/nest2/nest3/nest4/a", "b1.b2.b3.b4");
		gitletFast("add", TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "changed n1/n2/n3/n4 to b");
		// add a changed n1/n2 on master
		gitletFast("checkout", "master");
		writeFile(TESTING_DIR + "nest1/nest2/a", "b1.b2");
		assertEquals("b1.b2", getText(TESTING_DIR + "nest1/nest2/a"));
		gitletFast("add", TESTING_DIR + "nest1/nest2/a");
		gitletFast("commit", "made change to n1/n2/ > b");
		// checkout branch2
		gitletFast("checkout", "branch2");
		gitletFast("rebase", "master");
		String logContent = gitletFast("log");
		assertEquals(5, extractCommitMessages(logContent).length);
		assertEquals("b1.b2.b3.b4", getText(TESTING_DIR + "nest1/nest2/nest3/nest4/a"));
		assertEquals("a1", getText(TESTING_DIR + "nest1/a"));
		// expect to get the master's changed n1/n2 file
		getText(TESTING_DIR + "nest1/nest2/a");
		assertEquals("b1.b2", getText(TESTING_DIR + "nest1/nest2/a"));
	}

	/**
	 * Tests rebase when current branch exists in the history of the given
	 * branch. Should move current branch pointer to point to given branch.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testHistoryRebase() throws IOException {
		gitletFast("init");
		command("mkdir", TESTING_DIR + "nest1");
		command("mkdir", TESTING_DIR + "nest1/nest2");
		command("mkdir", TESTING_DIR + "nest1/nest2/nest3");
		command("mkdir", TESTING_DIR + "nest1/nest2/nest3/nest4");
		createFile(TESTING_DIR + "nest1/a", "a1");
		createFile(TESTING_DIR + "nest1/nest2/a", "a1.a2");
		createFile(TESTING_DIR + "nest1/nest2/nest3/nest4/a", "a1.a2.a3.a4");
		File f = new File(TESTING_DIR + "nest1/a");
		assertTrue(f.exists());
		File a = new File(TESTING_DIR + "nest1/nest2/a");
		assertTrue(a.exists());
		File b = new File(TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		assertTrue(b.exists());
		// done creating initial files
		gitletFast("add", TESTING_DIR + "nest1/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "committed 'a' files.");
		gitletFast("branch", "branch2");
		// going to add the same files...
		gitletFast("add", TESTING_DIR + "nest1/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/a");
		gitletFast("add", TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "committed the same 'a' files.");
		// change the file contents of n1/n2/n3/n4/a
		gitletFast("checkout", "branch2");
		writeFile(TESTING_DIR + "nest1/nest2/nest3/nest4/a", "b1.b2.b3.b4");
		gitletFast("add", TESTING_DIR + "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "changed n1/n2/n3/n4 to b");
		gitletFast("checkout", "master");
		gitletFast("rebase", "branch2");
		String logContent = gitletFast("log");
		assertEquals(4, extractCommitMessages(logContent).length);
		assertEquals("b1.b2.b3.b4", getText(TESTING_DIR + "nest1/nest2/nest3/nest4/a"));
		assertEquals("a1", getText(TESTING_DIR + "nest1/a"));
		// expect to get the master's changed n1/n2 file
		getText(TESTING_DIR + "nest1/nest2/a");
		assertEquals("a1.a2", getText(TESTING_DIR + "nest1/nest2/a"));
	}

	/**
	 * Tests that merge is successful with no conflicted files. Involves init,
	 * add, commit, branch, checkout, and merge.
	 */
	@Test
	public void testMerge() {

		String file1 = TESTING_DIR + "a.txt";
		String file2 = TESTING_DIR + "b.txt";
		String file3 = TESTING_DIR + "c.txt";
		String file4 = TESTING_DIR + "d.txt";

		createFile(file1, "1");
		createFile(file2, "2");
		createFile(file3, "3");
		createFile(file4, "4");

		gitlet("init");
		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		createFile(file1, "1 master");
		createFile(file2, "2 master");

		gitlet("add", file1);
		gitlet("add", file2);

		gitlet("commit", "master branched off");
		gitlet("checkout", "branch2");

		createFile(file3, "3 branch2");
		createFile(file4, "4 branch2");
		gitlet("add", file3);
		gitlet("add", file4);
		gitlet("commit", "branch2 now off");
		gitlet("merge", "master");

		assertEquals("1 master", getText(file1));
		assertEquals("2 master", getText(file2));
		assertEquals("3 branch2", getText(file3));
		assertEquals("4 branch2", getText(file4));
	}

	/**
	 * Tests that merge is successful with no conflicted files. Involves init,
	 * add, commit, branch, checkout, and merge.
	 */
	@Test
	public void testMergeConflicted() {

		String file1 = TESTING_DIR + "a.txt";
		String file2 = TESTING_DIR + "b.txt";
		String fileconflicted = TESTING_DIR + "a.txt.conflicted";
		String fileconflicted2 = TESTING_DIR + "b.txt.conflicted";

		createFile(file1, "1");
		createFile(file2, "2");

		gitlet("init");
		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		createFile(file1, "1 master");
		createFile(file2, "2 master");

		gitlet("add", file1);
		gitlet("add", file2);

		gitlet("commit", "master branched off");

		gitlet("checkout", "branch2");

		createFile(file1, "1 branch2");
		createFile(file2, "2 branch2");
		gitlet("add", file1);
		gitlet("add", file2);

		gitlet("commit", "branch2 now off");
		gitlet("merge", "master");

		assertEquals("1 branch2", getText(file1));
		assertEquals("2 branch2", getText(file2));
		assertEquals("1 master", getText(fileconflicted));
		assertEquals("2 master", getText(fileconflicted2));
	}

	/**
	 * Tests that merge works when a file is removed. Involves init, add,
	 * commit, branch, checkout, and merge.
	 */
	@Test
	public void testMergeRemoved() {

		String file1 = TESTING_DIR + "a.txt";
		String file2 = TESTING_DIR + "b.txt";
		String fileconflicted = TESTING_DIR + "a.txt.conflicted";
		String fileconflicted2 = TESTING_DIR + "b.txt.conflicted";

		createFile(file1, "1");
		createFile(file2, "2");

		gitlet("init");
		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		createFile(file1, "1 master");
		createFile(file2, "2 master");

		gitlet("rm", file1);
		gitlet("add", file2);

		gitlet("commit", "master branched off");

		gitlet("checkout", "branch2");

		createFile(file1, "1 branch2");
		createFile(file2, "2 branch2");
		gitlet("add", file1);
		gitlet("add", file2);

		gitlet("commit", "branch2 now off");
		gitlet("merge", "master");

		assertEquals("1 branch2", getText(file1));
		assertEquals("2 branch2", getText(file2));
		assertEquals("", getText(fileconflicted));
		assertEquals("2 master", getText(fileconflicted2));

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
	 * 
	 * @throws IOException
	 */
	private static String gitletFast(String... args) throws IOException {
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