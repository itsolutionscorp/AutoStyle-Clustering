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

	@Test
	public void testCheckOut() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String tagFileName = TESTING_DIR + "tag.txt";
		String tagText = "This is a tag.";
		createFile(tagFileName, tagText);

		String bagFileName = TESTING_DIR + "bag.txt";
		String bagText = "This is a bag.";
		createFile(bagFileName, bagText);

		gitlet("init");// step 1

		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));// checkout our file
		gitlet("add", tagFileName);
		gitlet("commit", "added tag");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", "1", wugFileName);// checkout a commit
		assertEquals(wugText, getText(wugFileName));
		gitlet("add", bagFileName);
		gitlet("commit", "added bag");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", "2", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		// checkout a previous commit and file name

		writeFile(wugFileName, "This is a modified wug.");
		writeFile(tagFileName, "This is a modified tag.");
		gitlet("add", wugFileName);
		gitlet("add", tagFileName);
		gitlet("commit", "modified wug&tag");
		writeFile(wugFileName, "This is a wug.");
		gitlet("checkout", "master");
		// checkout current active branch, should not
		assertEquals(wugText, getText(wugFileName));
		tagText = "This is a modified tag.";
		wugText = "This is a modified wug.";
		gitlet("checkout", "4", tagFileName);
		gitlet("checkout", "4", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		assertEquals(tagText, getText(tagFileName));
		// checkout current id

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

	@Test
	public void testRemoveLogAndGlobalLog() {
		String tagFileName = TESTING_DIR + "tag.txt";
		String tagText = "This is a tag.";
		createFile(tagFileName, tagText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("add", tagFileName);
		gitlet("rm", tagFileName);// remove from stage
		gitlet("commit", "cannot commit");
		gitlet("add", tagFileName);
		gitlet("rm", wugFileName);// untrack
		gitlet("commit", "added wug");
		gitlet("find", "added wug");// find the two
		gitlet("find", "does not exist");
		writeFile(wugFileName, "This is a modified wug.");
		gitlet("checkout", wugFileName);
		assertTrue(!wugText.equals(getText(wugFileName)));
		gitlet("checkout", "1", wugFileName);
		assertTrue(wugText.equals(getText(wugFileName)));
	}

	@Test
	public void testBranch() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String tagFileName = TESTING_DIR + "tag.txt";
		String tagText = "This is a tag.";
		createFile(tagFileName, tagText);

		String bagFileName = TESTING_DIR + "bag.txt";
		String bagText = "This is a bag.";
		createFile(bagFileName, bagText);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wugcommit");
		gitlet("branch", "newbranch1");
		gitlet("checkout", "newbranch1");
		gitlet("add", tagFileName);
		writeFile(wugFileName, "This is modified a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "wug&tag");
		gitlet("add", bagFileName);
		gitlet("commit", "bag");
		gitlet("checkout", "master");
		// problem is when i check out files it only gets recovered
		// to working directory, not where it comes from
		assertEquals(wugText, getText(wugFileName));
		gitlet("rm-branch", "newbranch1");
		gitlet("rm-branch", "master");
		gitlet("status");
	}

	@Test
	public void testReset() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String tagFileName = TESTING_DIR + "tag.txt";
		String tagText = "This is a tag.";
		createFile(tagFileName, tagText);

		String bagFileName = TESTING_DIR + "bag.txt";
		String bagText = "This is a bag.";
		createFile(bagFileName, bagText);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("add", tagFileName);
		gitlet("commit", "tag");
		gitlet("branch", "newbranch");
		gitlet("rm", wugFileName);
		gitlet("add", bagFileName);
		gitlet("commit", "bag");

		writeFile(wugFileName, "This is modified a wug.");
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));

		gitlet("reset", "4");// no commit exists

		writeFile(wugFileName, "This is modified a wug.");
		writeFile(tagFileName, "This is modified a tag.");
		writeFile(bagFileName, "This is modified a bag.");
		gitlet("reset", "3");
		assertEquals(tagText, getText(tagFileName));
		assertEquals(bagText, getText(bagFileName));
		assertTrue(!wugText.equals(getText(wugFileName)));
	}

	@Test
	public void testMerge() {
		// basic merge , no conflict
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String tagFileName = TESTING_DIR + "tag.txt";
		String tagText = "This is a tag.";
		createFile(tagFileName, tagText);

		String bagFileName = TESTING_DIR + "bag.txt";
		String bagText = "This is a bag.";
		createFile(bagFileName, bagText);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("branch", "newbranch");
		gitlet("add", tagFileName);
		gitlet("commit", "tag");
		assertEquals(wugText, getText(wugFileName));
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		gitlet("add", bagFileName);
		gitlet("commit", "bag");
		gitlet("merge", "newbranch");
		writeFile(wugFileName, "This is modified a wug.");
		writeFile(tagFileName, "This is modified a tag.");
		writeFile(bagFileName, "This is modified a bag.");
		gitlet("log");
		gitlet("checkout", wugFileName);
		gitlet("checkout", bagFileName);
		gitlet("checkout", tagFileName);
		assertEquals(tagText, getText(tagFileName));
		assertEquals(bagText, getText(bagFileName));
		assertEquals(wugText, getText(wugFileName));

	}

	@Test
	public void testMerge2() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String tagFileName = TESTING_DIR + "tag.txt";
		String tagText = "This is a tag.";
		createFile(tagFileName, tagText);

		String bagFileName = TESTING_DIR + "bag.txt";
		String bagText = "This is a bag.";
		createFile(bagFileName, bagText);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", tagFileName);
		gitlet("commit", "wug and tag");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", bagFileName);
		gitlet("commit", "bag");

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is modified a wug.");
		String newwug = "This is modified a wug.";
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug and old tag");
		gitlet("checkout", wugFileName);
		assertEquals(newwug, getText(wugFileName));

		gitlet("checkout", "branch1");
		writeFile(tagFileName, "This is a modified tag");
		gitlet("add", tagFileName);
		gitlet("commit", "modified tag and old wug");

		gitlet("merge", "master");

		gitlet("status");
		gitlet("checkout", wugFileName);
		assertEquals(newwug, getText(wugFileName));
	}

	@Test
	public void testConflict() {
		String aFileName = TESTING_DIR + "a.txt";
		String aText = "a";
		createFile(aFileName, aText);

		String bFileName = TESTING_DIR + "b.txt";
		String bText = "b";
		createFile(bFileName, bText);

		String cFileName = TESTING_DIR + "c.txt";
		String cText = "c";
		createFile(cFileName, cText);

		String dFileName = TESTING_DIR + "d.txt";
		String dText = "d";
		createFile(dFileName, dText);

		String eFileName = TESTING_DIR + "e.txt";
		String eText = "e";
		createFile(eFileName, eText);

		gitlet("init");
		gitlet("add", aFileName);
		gitlet("add", bFileName);
		gitlet("add", cFileName);
		gitlet("commit", "abc");

		gitlet("branch", "newbranch");
		gitlet("checkout", "newbranch");
		writeFile(aFileName, "a1");
		gitlet("add", aFileName);
		gitlet("add", dFileName);
		gitlet("commit", "a1&d");

		writeFile(bFileName, "b1");
		gitlet("add", bFileName);
		gitlet("commit", "b1");

		gitlet("checkout", "master");
		writeFile(bFileName, "b2");
		writeFile(cFileName, "c1");
		gitlet("add", bFileName);
		gitlet("add", cFileName);
		gitlet("commit", "b2&c1");

		gitlet("add", eFileName);
		gitlet("commit", "e");
		gitlet("merge", "newbranch");
		gitlet("global-log");
		gitlet("branch", "newbranch2");
		gitlet("commit", "resolve conflict");
		gitlet("branch", "newbranch2");
		gitlet("status");
	}

	@Test
	public void testRebase() {
		String aFileName = TESTING_DIR + "a.txt";
		String aText = "a";
		createFile(aFileName, aText);

		String bFileName = TESTING_DIR + "b.txt";
		String bText = "b";
		createFile(bFileName, bText);

		String cFileName = TESTING_DIR + "c.txt";
		String cText = "c";
		createFile(cFileName, cText);

		String dFileName = TESTING_DIR + "d.txt";
		String dText = "d";
		createFile(dFileName, dText);

		String eFileName = TESTING_DIR + "e.txt";
		String eText = "e";
		createFile(eFileName, eText);
		
		String fFileName = TESTING_DIR + "f.txt";
		String fText = "f";
		createFile(fFileName, fText);

		gitlet("init");

		gitlet("add", aFileName);
		gitlet("add", bFileName);
		gitlet("add", cFileName);
		gitlet("commit", "abc");

		gitlet("branch", "newbranch");
		gitlet("checkout", "newbranch");
		writeFile(aFileName, "a1");
		gitlet("add", aFileName);
		gitlet("add", dFileName);
		gitlet("commit", "a1&d");

		writeFile(bFileName, "b1");
		gitlet("add", bFileName);
		gitlet("commit", "b1");

		gitlet("checkout", "master");
		writeFile(bFileName, "b2");
		writeFile(cFileName, "c1");
		gitlet("add", bFileName);
		gitlet("add", cFileName);
		gitlet("commit", "b2&c1");
		gitlet("add", eFileName);
		gitlet("commit", "e");
		gitlet("add", fFileName);
		writeFile(bFileName, "bbbb");
		writeFile(aFileName, "aaaa");
		writeFile(cFileName, "cccc");
		writeFile(dFileName, "dddd");
		writeFile(eFileName, "eeee");
		gitlet("rebase", "newbranch");
		gitlet("commit", "f");
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