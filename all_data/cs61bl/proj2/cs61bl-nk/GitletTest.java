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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

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
	
	/**
	 * Based on the tests above, we wrote some end-to-end tests of our own. 
	 * 
	 * @author Soohee Lee, Nancy Li, Stefan Lam
	 * 
	 */
	@Test
	public void testCheckout2() {
		String first = TESTING_DIR + "1/" + "File1.txt";
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		String second = TESTING_DIR + "2/" + "File2.txt";
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		String third = TESTING_DIR + "3/" + "File3.txt";
		File thirdfile = new File(third);
		thirdfile.getParentFile().mkdirs();
		String thirdtext = "3";
		createFile(third, thirdtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", second);
		gitlet("commit", "second");
		gitlet("add", third);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		writeFile(second, "2 - changed");
		gitlet("checkout", first);
		gitlet("checkout", second);
		assertEquals(firsttext, getText(first));
		assertEquals(secondtext, getText(second));
	}
	@Test
	public void testCheckoutBranch() {
		String first = TESTING_DIR + "1/" + "File1.txt";
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("branch", "other");
		gitlet("checkout", "other");
		writeFile(first, "1 - changed");
		gitlet("add", first);
		gitlet("commit", "second");
		gitlet("checkout", "master");
		writeFile(first, "1 - changed again");
		gitlet("add", first);
		gitlet("commit", "third");
		gitlet("checkout", "other");
		assertEquals("1 - changed", getText(first));
		gitlet("checkout", "master");
		assertEquals("1 - changed again", getText(first));
	}
	@Test
	public void testCheckoutCommitID() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("commit", "first");
		writeFile(first, "1 - changed");
		writeFile(second, "2 - changed");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("commit", "second");
		writeFile(first.toString(), "1 - changed again");
		writeFile(second.toString(), "2 - changed again");
		gitlet("checkout", "2", first.toString());
		gitlet("checkout", "2", second.toString());
		assertEquals("1 - changed", getText(first.toString()));
		assertEquals("2 - changed", getText(second.toString()));
		gitlet("checkout", "1", first.toString());
		gitlet("checkout", "1", second.toString());
		assertEquals("1", getText(first.toString()));
		assertEquals("2", getText(second.toString()));
	}
	@Test
	public void testNestedDirectories() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		Path thirdpath = Paths.get(TESTING_DIR, "File3.txt");
		String third = thirdpath.toString();
		File thirdfile = new File(third);
		thirdfile.getParentFile().mkdirs();
		String thirdtext = "3";
		createFile(third, thirdtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("add", third);
		gitlet("commit", "first");
		gitlet("add", first);
		gitlet("commit", "second");
		writeFile(first.toString(), "1 - changed");
		writeFile(second.toString(), "2 - changed");
		writeFile(third.toString(), "3 - changed");
		gitlet("reset", "1");
		assertEquals("1", getText(first.toString()));
		assertEquals("2", getText(second.toString()));
		assertEquals("3", getText(third.toString()));
		gitlet("branch", "new");
		gitlet("checkout", "new");
		writeFile(first.toString(), "1 - changed again");
		gitlet("add", first);
		gitlet("commit", "third");
		gitlet("rm", second);
		gitlet("rm", third);
		gitlet("commit", "fourth");
		writeFile(first.toString(), "1 - changed");
		writeFile(second.toString(), "2 - changed");
		writeFile(third.toString(), "3 - changed");
		gitlet("checkout", "master");
		gitlet("log");
		gitlet("status");
		assertEquals("1", getText(first.toString()));
		assertEquals("2", getText(second.toString()));
		assertEquals("3", getText(third.toString()));
		writeFile(second.toString(), "2 - changed");
		writeFile(third.toString(), "3 - changed");
		gitlet("checkout", "new");
		gitlet("global-log");
		gitlet("log");
		gitlet("status");
		assertEquals("1 - changed again", getText(first.toString()));
		assertEquals("2 - changed", getText(second.toString()));
		assertEquals("3 - changed", getText(third.toString()));
	}
	@Test
	public void testRemove() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", second);
		gitlet("commit", "second");
		gitlet("rm", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		writeFile(second, "2 - changed");
		gitlet("checkout", "3", first);
		gitlet("checkout", "3", second);
		assertEquals("1 - changed", getText(first));
		assertEquals(secondtext, getText(second));
	}
	@Test
	public void testReset() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", second);
		gitlet("commit", "second");
		gitlet("rm", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		writeFile(second, "2 - changed");
		gitlet("reset", "3");
		assertEquals("1 - changed", getText(first));
		assertEquals(secondtext, getText(second));
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "third", "second", "first", "initial commit" },
				extractCommitMessages(logContent1));
		gitlet("reset", "2");
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { "second", "first", "initial commit" },
				extractCommitMessages(logContent2));
		gitlet("reset", "1");
		String logContent3 = gitlet("log");
		assertArrayEquals(new String[] { "first", "initial commit" },
				extractCommitMessages(logContent3));
		assertEquals("1", getText(first));
		assertEquals(secondtext, getText(second));
		writeFile(first, "1 - changed again");
		gitlet("checkout", first);
		assertEquals("1", getText(first));
		
	}
	@Test
	public void testMerge() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("commit", "second");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		gitlet("add", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		gitlet("add", first);
		gitlet("commit", "fourth");
		gitlet("checkout", "master");
		gitlet("status");
		writeFile(first, "1");
		writeFile(second, "2 - changed");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("commit", "fifth");
		writeFile(second, "2");
		gitlet("merge", "new");
		assertEquals("1 - changed", getText(first));
		assertEquals("2", getText(second));
		gitlet("log");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "Merged master with new.", "fifth", "second", "first", "initial commit" },
				extractCommitMessages(logContent1));
	}
	@Test
	public void testMergeUntracked() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("commit", "second");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		gitlet("add", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		gitlet("rm", first);
		gitlet("rm", second);
		gitlet("commit", "fourth");
		gitlet("checkout", "master");
		writeFile(first, "1");
		writeFile(second, "2 - changed");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("commit", "fifth");
		gitlet("merge", "new");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "Merged master with new.", "fifth", "second", "first", "initial commit" },
				extractCommitMessages(logContent1));
		writeFile(first, "11");
		writeFile(second, "22");
		gitlet("reset", "6");
		assertEquals("11", getText(first));
		assertEquals("2 - changed", getText(second));
	}
	@Test
	public void testMergeConflicted() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		Path thirdpath = Paths.get(TESTING_DIR, "3", "File3.txt");
		String third = thirdpath.toString();
		File thirdfile = new File(third);
		thirdfile.getParentFile().mkdirs();
		String thirdtext = "3";
		createFile(third, thirdtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("add", third);
		gitlet("commit", "second");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		gitlet("add", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		writeFile(third, "3 - changed");
		gitlet("add", first);
		gitlet("add", third);
		gitlet("commit", "fourth");
		gitlet("checkout", "master");
		gitlet("status");
		writeFile(first, "1");
		writeFile(second, "2 - changed");
		writeFile(third, "3 - changed again");
		gitlet("add", first);
		gitlet("add", second);
		gitlet("add", third);
		gitlet("commit", "fifth");
		writeFile(first, "11");
		writeFile(second, "22");
		writeFile(third, "33");
		gitlet("merge", "new");
		Path conflictedPath = Paths.get(TESTING_DIR, "3", "File3.txt.conflicted");
		String conflicted = conflictedPath.toString();
		File conflictedFile = new File(conflicted);
		assertTrue(conflictedFile.exists());
		assertEquals("1 - changed", getText(first));
		assertEquals("22", getText(second));
		assertEquals("33", getText(third));
		assertTrue(thirdfile.exists());
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "fifth", "second", "first", "initial commit" },
				extractCommitMessages(logContent1));
		writeFile(first, "11");
		writeFile(second, "22");
		writeFile(third, "33");
		String errs = gitlet("checkout", "new");
		assertEquals(errs, "Cannot do this command until the merge conflict has been resolved.");
		assertEquals("11", getText(first));
		assertEquals("22", getText(second));
		assertEquals("33", getText(third));
		gitlet("add", third);
		gitlet("commit", "sixth");
		gitlet("checkout", "new");
		assertEquals("1 - changed", getText(first));
		assertEquals("2", getText(second));
		assertEquals("3 - changed", getText(third));	
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { "fourth", "third", "second", "first", "initial commit" },
				extractCommitMessages(logContent2));
	}
	@Test
	public void testRebase() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", first);
		gitlet("commit", "second");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		gitlet("add", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed");
		gitlet("add", first);
		gitlet("commit", "fourth");
		gitlet("checkout", "master");
		assertEquals("1", getText(first));
		gitlet("add", first);
		gitlet("commit", "fifth");
		writeFile(first, "1 - changed again");
		gitlet("add", first);
		gitlet("commit", "sixth");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "sixth", "fifth", "second", "first", "initial commit" },
				extractCommitMessages(logContent1));
		gitlet("rebase", "new");
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { "sixth", "fifth", "fourth", "third", "second", "first", "initial commit" },
				extractCommitMessages(logContent2));
		writeFile(first, "11");
		gitlet("checkout", "7", first);
		assertEquals("1 - changed", getText(first));
		writeFile(first, "11");
		gitlet("checkout", "8", first);
		assertEquals("1 - changed again", getText(first));
	}
	@Test
	public void testRebaseUntracked() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		writeFile(first, "1 - changed");
		gitlet("add", first);
		gitlet("commit", "second");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		gitlet("rm", first);
		gitlet("commit", "third");
		gitlet("checkout", "master");
		gitlet("add", first);
		gitlet("commit", "fourth");
		writeFile(first, "1 - changed again");
		gitlet("add", first);
		gitlet("commit", "fifth");
		gitlet("rebase", "new");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] { "fifth", "fourth", "third", "second", "first", "initial commit" },
				extractCommitMessages(logContent1));
		gitlet("global-log");
		writeFile(first, "11");
		gitlet("checkout", "7", first);
		assertEquals("1 - changed again", getText(first));
		writeFile(first, "11");
		String err = gitlet("checkout", "6", first);
		assertEquals("11", getText(first));
		assertEquals(err, "File does not exist in that commit.");
	}
	@Test
	public void testcheckoutID() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		writeFile(first, "1 - changed");
		gitlet("add", first);
		gitlet("commit", "second");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		gitlet("rm", first);
		gitlet("commit", "third");
		String err = gitlet("checkout", "3", first);
		assertEquals("File does not exist in that commit.", err);
	}
	@Test
	public void testRebaseEdgeCases() {
		Path firstpath = Paths.get(TESTING_DIR, "1", "File1.txt");
		String first = firstpath.toString();
		File firstfile = new File(first);
		firstfile.getParentFile().mkdirs();
		String firsttext = "1";
		createFile(first, firsttext); 
		Path secondpath = Paths.get(TESTING_DIR, "2", "File2.txt");
		String second = secondpath.toString();
		File secondfile = new File(second);
		secondfile.getParentFile().mkdirs();
		String secondtext = "2";
		createFile(second, secondtext); 
		gitlet("init");
		gitlet("add", first);
		gitlet("commit", "first");
		gitlet("add", first);
		gitlet("commit", "second");
		gitlet("branch", "new");
		writeFile(first, "1 - changed");
		gitlet("add", first);
		gitlet("commit", "third");
		writeFile(first, "1 - changed - again");
		gitlet("add", second);
		gitlet("commit", "fourth");
		gitlet("log");
		String error = gitlet("rebase", "new");
		assertEquals(error, "Already up-to-date.");
	}

}