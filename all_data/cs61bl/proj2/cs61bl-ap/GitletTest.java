import static org.junit.Assert.assertArrayEquals;
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

		finishUp();
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

	public void finishUp() {
		File tDir = new File(TESTING_DIR);
		File gDir = new File(GITLET_DIR);
		try {
			recursiveDelete(tDir);
			recursiveDelete(gDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testErrorMsg() {
		gitlet("init");
		gitlet("init");// existing version control system
		gitlet("add", "aRandomFile");// file does not exist
		gitlet("commit");// no commmit message.
		gitlet("commit", "without changes");// no changes to that commit
		createFile("b", "abcd");
		gitlet("rm", "b");// no reason to remove that file
		gitlet("find", "a non-existent message");// find no commit with that
													// message
		gitlet("checkout", "fileThatDoesNotExist");
		gitlet("checkout", "1");// no commit with that id.
		gitlet("checkout", "0", "aFileThatIsNotTracked");// file does not exist
		gitlet("checkout", "master");// no need to checkout current branch
		gitlet("branch", "master");// a branch with that name already exists
		gitlet("rm-branch", "branch");
		gitlet("rm-branch", "master");
		gitlet("reset", "2");
		gitlet("merge", "branch");
		gitlet("merge", "master");
		gitlet("rebase", "branch");
		gitlet("rebase", "master");
	}

	@Test
	public void testAdd() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;

		gitlet("init");
		createFile(T + "a", "a");
		gitlet("add", T + "a");
		assertTrue(Files.exists(Paths.get(G + "staging/" + T + "a")));// can add
																		// file
		gitlet("commit", "added a");
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "a")));// empty
																		// staging
																		// after
																		// commit
		gitlet("add", T + "a");
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "a")));// cant
																		// add
																		// unchanged
																		// file
		writeFile(T + "a", "some change");
		gitlet("rm", T + "a");
		gitlet("add", T + "a");
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "a")));// add
																		// untracked
																		// files,
																		// does
																		// not
																		// stage
																		// them.
		gitlet("add", T + "a");
		assertTrue(Files.exists(Paths.get(G + "staging/" + T + "a")));// can add
																		// after
																		// removing
																		// the
																		// untracking
																		// label.
		try {
			Files.createDirectories(Paths.get(T + "example1/example2/example3"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gitlet("add", T + "example1");
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "example1")));// cant
																				// add
																				// directory
		createFile(T + "example1/example2/example3/a", "a");
		gitlet("add", T + "example1/example2/example3/a");
		assertTrue(Files.exists(Paths.get(G + "staging/" + T
				+ "example1/example2/example3/a")));
		finishUp();
	}

	@Test
	public void testCommit() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "a first");
		createFile(T + "b", "b first");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("commit", "added a and b");// 1
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "a")));
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "b")));
		assertEquals("a first", getText(G + "master/commit1/" + T + "a"));
		assertEquals("b first", getText(G + "master/commit1/" + T + "b"));// basics

		writeFile(T + "a", "a second");
		createFile(T + "c", "c second");
		gitlet("add", T + "a");
		gitlet("add", T + "c");
		gitlet("commit", "added c, changed a");// 2
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "a")));
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "c")));
		assertFalse(Files.exists(Paths.get(G + "master/commit2/" + T + "b")));// does
																				// not
																				// update
																				// unchanged
																				// files

		writeFile(T + "b", "something else");
		gitlet("checkout", T + "b");// last commit tracks b
		assertEquals("b first", getText(T + "b"));
		assertEquals("a second", getText(G + "master/commit2/" + T + "a"));
		assertEquals("c second", getText(G + "master/commit2/" + T + "c"));

		try {
			Files.createDirectories(Paths.get(T + "example1/example2/example3"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createFile(T + "example1/example2/example3/a", "a nested file");
		gitlet("add", T + "example1/example2/example3/a");
		gitlet("commit", "added nested file");
		assertFalse(Files.exists(Paths.get(G + "staging/" + T
				+ "example1/example2")));
		assertEquals("a nested file", getText(G + "master/commit3/" + T
				+ "example1/example2/example3/a"));
		finishUp();
	}

	@Test
	public void testRM() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "a first");
		createFile(T + "b", "b first");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		assertTrue(Files.exists(Paths.get(G + "staging/" + T + "a")));
		gitlet("rm", T + "a");
		assertFalse(Files.exists(Paths.get(G + "staging/" + T + "a")));// unstage
		assertTrue(Files.exists(Paths.get(G + "staging/" + T + "b")));
		gitlet("commit", "added b");// 1
		assertFalse(Files.exists(Paths.get(G + "master/commit1/" + T + "a")));
		assertTrue(Files.exists(Paths.get(G + "master/commit1/" + T + "b")));
		gitlet("add", T + "a");
		assertTrue(Files.exists(Paths.get(G + "staging/" + T + "a")));// untracking
																		// label
																		// gone
																		// after
																		// commit
		gitlet("rm", "b");
		gitlet("commit", "added a, removed b");// 2
		writeFile(T + "b", "something else");
		gitlet("checkout", "2", T + "B");
		assertEquals("something else", getText(T + "b"));// commit 2 does not
															// track b
		finishUp();
	}

	@Test
	public void testRebaseBasic3() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "1 master");
		createFile(T + "b", "2 master");
		createFile(T + "c", "3 master");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "first");
		gitlet("branch", "b2");
		gitlet("checkout", "b2");
		writeFile(T + "c", "3 b2");
		writeFile(T + "a", "1 b2");
		writeFile(T + "b", "2 b2");

		gitlet("add", T + "c");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("commit", "B2");
		gitlet("checkout", "master");
		gitlet("rebase", "b2");
		gitlet("checkout", "master");
		assertEquals("1 b2", getText(T + "a"));
		assertEquals("2 b2", getText(T + "b"));
		assertEquals("3 b2", getText(T + "c"));// changing head of activeBranch
		finishUp();

	}

	@Test
	public void testRebaseBasic1() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "");
		createFile(T + "b", "");
		createFile(T + "c", "");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "first");
		gitlet("branch", "b1");
		gitlet("branch", "b2");
		writeFile(T + "c", "x");
		createFile(T + "d", "");
		gitlet("checkout", "b2");
		writeFile(T + "c", "y");
		writeFile(T + "d", "y");
		createFile(T + "e", "y");
		gitlet("add", T + "c");
		gitlet("add", T + "d");
		gitlet("add", T + "e");
		gitlet("commit", "B2");
		assertEquals("Already up-to-date.", gitlet("rebase", "master"));// already
																		// uptodate
		finishUp();
	}

	@Test
	public void testRebaseBasic2() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "");
		createFile(T + "b", "");
		createFile(T + "c", "");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "first");
		gitlet("branch", "b1");
		gitlet("branch", "b2");
		writeFile(T + "c", "x");
		createFile(T + "d", "");
		gitlet("checkout", "b2");
		writeFile(T + "c", "y");
		writeFile(T + "d", "y");
		createFile(T + "e", "y");
		gitlet("add", T + "c");
		gitlet("add", T + "d");
		gitlet("add", T + "e");
		gitlet("commit", "B2");
		gitlet("checkout", "b1");
		assertEquals("Cannot rebase a branch onto itself.",
				gitlet("rebase", "b1"));// rebase into itself
		finishUp();
	}

	@Test
	public void testLog() {// fifth bullet point of part F
		String T = TESTING_DIR;
		gitlet("init");
		createFile(T + "a", "master");
		gitlet("branch", "A");
		gitlet("branch", "B");
		gitlet("checkout", "A");
		writeFile(T + "a", "a in A");
		gitlet("add", T + "a");
		gitlet("commit", "a in A");
		gitlet("checkout", "B");
		writeFile(T + "a", "a in B");
		gitlet("add", T + "a");
		gitlet("commit", "a in B");
		gitlet("log");
		gitlet("checkout", "A");
		gitlet("log");
		finishUp();
	}

	@Test
	public void testFind() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "");
		gitlet("add", T + "a");
		gitlet("commit", "added a");
		writeFile(T + "a", "changed");
		gitlet("add", T + "a");
		gitlet("commit", "added a");
		String rtn = gitlet("find", "added a");
		assertEquals("1\n2", rtn);
		finishUp();
	}

	@Test
	public void testBranch() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "");
		createFile(T + "b", "");
		createFile(T + "c", "");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "first");
		gitlet("branch", "B");
		writeFile(T + "a", "some");
		writeFile(T + "b", "weird");
		writeFile(T + "c", "stuff");
		gitlet("checkout", "B");
		assertEquals("", getText(T + "a"));
		assertEquals("", getText(T + "b"));
		assertEquals("", getText(T + "c"));// newly created branch points to the
											// original commit
		writeFile(T + "a", "some");
		writeFile(T + "b", "weird");
		writeFile(T + "c", "stuff");
		gitlet("checkout", "master");
		assertEquals("", getText(T + "a"));
		assertEquals("", getText(T + "b"));
		assertEquals("", getText(T + "c"));
		finishUp();
	}

	@Test
	public void testReset() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;
		gitlet("init");
		createFile(T + "a", "");
		createFile(T + "b", "");
		createFile(T + "c", "");
		createFile(T + "d", "this is not affected");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "first");// 1
		writeFile(T + "a", "some");
		writeFile(T + "b", "weird");
		writeFile(T + "c", "stuff");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "second");// 2
		writeFile(T + "a", "more");
		writeFile(T + "b", "weirder");
		writeFile(T + "c", "stuffs");
		gitlet("reset", "1");
		assertEquals("", getText(T + "a"));
		assertEquals("", getText(T + "b"));
		assertEquals("", getText(T + "c"));
		assertEquals("this is not affected", getText(T + "d"));
		gitlet("log");// fourth bullet point part F
		finishUp();
	}

	@Test
	public void testCheckoutFileByID() {
		String T = TESTING_DIR;

		gitlet("init");
		createFile(T + "a", "initial");
		gitlet("add", T + "a");
		gitlet("commit", "initial");// 1
		writeFile(T + "a", "changed");
		gitlet("add", T + "a");
		gitlet("commit", "changed");// 2
		gitlet("checkout", "1", T + "a");
		assertEquals("initial", getText(T + "a"));

		try {
			Files.createDirectories(Paths.get(T + "example1/example2/"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createFile(T + "example1/example2/a", "initial");
		gitlet("add", T + "example1/example2/a");
		gitlet("commit", "3");// 3
		writeFile(T + "example1/example2/a", "changed");
		gitlet("add", T + "example1/example2/a");
		gitlet("commit", "4");// 4
		gitlet("checkout", "3", T + "example1/example2/a");
		assertEquals("initial", getText(T + "example1/example2/a"));

		finishUp();
	}

	@Test
	public void testCheckoutFileFromLastCommit() {
		String T = TESTING_DIR;
		gitlet("init");
		createFile(T + "a", "initial");
		createFile(T + "b", "initial");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("commit", "1");// 1
		writeFile(T + "a", "changed");
		gitlet("add", T + "a");
		gitlet("commit", "2");// 2
		writeFile(T + "a", "changedAgain");
		assertEquals("changedAgain", getText(T + "a"));
		gitlet("checkout", T + "a");
		assertEquals("changed", getText(T + "a"));
		writeFile(T + "b", "changed");
		gitlet("checkout", T + "b");
		assertEquals("initial", getText(T + "b"));

		try {
			Files.createDirectories(Paths.get(T + "example1/example2/"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createFile(T + "example1/example2/a", "initial");
		gitlet("add", T + "example1/example2/a");
		gitlet("commit", "3");// 3
		writeFile(T + "example1/example2/a", "changed");
		gitlet("add", T + "example1/example2/a");
		gitlet("commit", "4");// 4
		writeFile(T + "example1/example2/a", "changedAgain");
		assertEquals("changedAgain", getText(T + "example1/example2/a"));
		gitlet("checkout", T + "example1/example2/a");
		assertEquals("changed", getText(T + "example1/example2/a"));

		finishUp();
	}

	@Test
	public void testCheckoutBranch() {
		String T = TESTING_DIR;
		gitlet("init");
		createFile(T + "a", "a");
		createFile(T + "b", "b");
		createFile(T + "c", "c");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "first");// 1

		gitlet("branch", "B");

		writeFile(T + "a", "a in master");
		writeFile(T + "c", "c in master");
		gitlet("add", T + "a");
		gitlet("add", T + "c");
		gitlet("commit", "A");// 2

		gitlet("checkout", "B");
		assertEquals("a", getText(T + "a"));
		assertEquals("c", getText(T + "c"));
		writeFile(T + "a", "a in B");
		writeFile(T + "b", "b in B");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("commit", "B");

		gitlet("checkout", "master");
		assertEquals("a in master", getText(T + "a"));
		assertEquals("b", getText(T + "b"));
		assertEquals("c in master", getText(T + "c"));

		gitlet("checkout", "B");
		assertEquals("a in B", getText(T + "a"));
		assertEquals("b in B", getText(T + "b"));
		assertEquals("c", getText(T + "c"));
	}
	
	public void testMerge(){
		// this tests a merge scenario when files in both branches 
		// have been modified since the split point
		String wugFile1 = TESTING_DIR + "wug1.txt";
		String wugText1 = "This is the first wug.";
		String wugFile2 = TESTING_DIR + "wug2.txt";
		String wugText2 = "This is the second wug.";
		String wugFile3 = TESTING_DIR + "wug3.txt";
		String wugText3 = "This is the third wug.";
		
		gitlet("init");
		createFile(wugFile1, wugText1);
		createFile(wugFile2, wugText2);
		createFile(wugFile3, wugText3);	
		File f1 = new File(wugFile1);
		assertTrue(f1.exists());
		File f2 = new File(wugFile3);
		assertTrue(f2.exists());
		File f3 = new File(wugFile3);
		assertTrue(f3.exists());
		// so the files are still in the working directory
		// not checking whether files in staging because this method ASSUMES add is working
		
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("add", wugFile3);
		f1 = new File(".gitlet/staging/" + wugFile1);
		assertTrue(f1.exists());
		f2 = new File(".gitlet/staging/" + wugFile2);
		assertTrue(f2.exists());
		f3 = new File(".gitlet/staging/" + wugFile3);
		assertTrue(f3.exists());
		// all files exist in staging area
		
		gitlet("commit","first commit");
		assertFalse(f1.exists());
		assertFalse(f2.exists());
		assertFalse(f3.exists());
		f1 = new File(".gitlet/master/commit1/" + wugFile1);
		assertTrue(f1.exists());
		f2 = new File(".gitlet/master/commit1/" + wugFile2);
		assertTrue(f2.exists());
		f3 = new File(".gitlet/master/commit1/" + wugFile3);
		assertTrue(f3.exists());
		// so these files aren't in staging or but in commit1 only
		
		gitlet("branch","cool-beans");
		writeFile(wugFile1, "This first wug is still in master");
		writeFile(wugFile2, "This second wug is still in master" );
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("commit","second commit");
		// not rechecking the add, whether file exists because other test methods look at these 
		// and I tested these before
		f1 = new File(".gitlet/master/commit2/" + wugFile1);
		assertTrue(f1.exists());
		f2 = new File(".gitlet/master/commit2/" + wugFile2);
		assertTrue(f2.exists());
		f3 = new File(".gitlet/master/commit2/" + wugFile3);
		assertFalse(f3.exists());
		// the files will still be in the master since we haven't checked out cool-beans branch
		
		gitlet("checkout", "cool-beans");
		// switching branch
		// assertEquals(wugText, getText(wugFileName));
		writeFile(wugFile1, "Now the first wug is cool");
		writeFile(wugFile2, "Now the second wug is cool");

		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("commit","First cool-beans commit");
		f1 = new File(".gitlet/cool-beans/commit3/" + wugFile1);
		assertTrue(f1.exists());
		f2 = new File(".gitlet/cool-beans/commit3/" + wugFile2);
		assertTrue(f2.exists());
		f3 = new File(".gitlet/cool-beans/commit3/" + wugFile3);
		assertFalse(f3.exists());
		// files are now commited in the new Branch 'Cool-beans'
		
		gitlet("checkout", "master");
		// now switching back to master. I will assume checkout passes with problems
		
		// Currently, the commit path has been split up. And both the wugFile1 and wugFile2
		// have been modified so we should get a merge conflict.
		assertEquals("Encountered a merge conflict.", gitlet("merge","cool-beans"));
		f1 = new File(wugFile1 + ".conflicted");
		assertEquals("Now the first wug is cool", getText(f1.toString()));
		f2 = new File(wugFile2 + ".conflicted");
		assertEquals("Now the second wug is cool", getText(f2.toString()));
		f3 = new File(wugFile3 + ".conflicted");
		assertFalse(f3.exists());
		// conflicted files 1 & 2 exist and they have been changed to given path's files.
		// sixth bulletin in Part F
		
		assertEquals("This first wug is still in master", getText(wugFile1.toString()));
		assertEquals("This second wug is still in master", getText(wugFile2.toString()));
		// working directory files should be the master latest commit from master
		
		f1 = new File(".gitlet/master/commit2/" + wugFile1);
		assertEquals("This first wug is still in master", getText(f1.toString()));
		f2 = new File(".gitlet/master/commit2/" + wugFile2);
		assertEquals("This second wug is still in master", getText(f2.toString()));
		// the text in the file in current branch stays the same
		
		f1 = new File(".gitlet/cool-beans/commit3/" + wugFile1);
		assertEquals("Now the first wug is cool", getText(f1.toString()));
		f2 = new File(".gitlet/cool-beans/commit3/" + wugFile2);
		assertEquals("Now the second wug is cool", getText(f2.toString()));
		// the text in the files in given branch stays the same
		
		// now let's make sure the methods we can't test won't work
		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("merge", "cool-beans"));	
		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("branch", "Mrkausik"));	
		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("rm-branch", "cool-beans"));
		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("reset", "1"));	
		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("rebase", "master"));
		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("merge", "cool-beans"));	
	}
	
	
	public void testMerge2() {
		// this should result in a successful merge.
		// Tests are more focused on merge now
		String wugFile1 = TESTING_DIR + "wug1.txt";
		String wugText1 = "This is the first wug.";
		String wugFile2 = TESTING_DIR + "wug2.txt";
		String wugText2 = "This is the second wug.";
		String wugFile3 = TESTING_DIR + "wug3.txt";
		String wugText3 = "This is the third wug.";
		gitlet("init");
		createFile(wugFile1, wugText1);
		createFile(wugFile2, wugText2);
		createFile(wugFile3, wugText3);	
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("add", wugFile3);
		gitlet("commit","commit1 in master");
		// commit 1 in master created with 3 files
		gitlet("branch","cool-beans");
		gitlet("checkout", "cool-beans");
		writeFile(wugFile1, "First wug in cool-beans");
		writeFile(wugFile2, "Second wug in cool-beans");
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("commit","commit2 in cool-beans");
		// commit 2 but in cool-beans
		File f1 = new File(".gitlet/cool-beans/commit2/" + wugFile1);
		assertTrue(f1.exists());
		File f2 = new File(".gitlet/cool-beans/commit2/" + wugFile2);
		assertTrue(f2.exists());
		File f3 = new File(".gitlet/cool-beans/commit2/" + wugFile3);
		assertFalse(f3.exists());
		
		gitlet("checkout", "master");
		gitlet("merge", "Spiderman");
		// assertEquals("A branch with that name does not exist.",
		//		gitlet("merge", "Spiderman")); // he fixed it
		gitlet("merge","cool-beans");
		// seventh bulletin in Part F
		f1 = new File(".gitlet/master/commit3/" + wugFile1);
		assertTrue(f1.exists());
		f2 = new File(".gitlet/master/commit3/" + wugFile2);
		assertTrue(f2.exists());
		f3 = new File(".gitlet/master/commit3/" + wugFile3);
		assertFalse(f3.exists());
	}

	
	public void testMerge3() {
		// this test tests when it goes straight into another branch and commits then branches
		String wugFile1 = TESTING_DIR + "wug1.txt";
		String wugText1 = "This is the first wug.";
		String wugFile2 = TESTING_DIR + "wug2.txt";
		String wugText2 = "This is the second wug.";
		String wugFile3 = TESTING_DIR + "wug3.txt";
		String wugText3 = "This is the third wug.";
		gitlet("init");
		createFile(wugFile1, wugText1);
		createFile(wugFile2, wugText2);
		createFile(wugFile3, wugText3);
		gitlet("branch","cool-beans");
		gitlet("checkout", "cool-beans");
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("add", wugFile3);
		gitlet("commit","commit1 in cool-beans");
		// commit 1 in master created with 3 files
		File f1 = new File(".gitlet/cool-beans/commit1/" + wugFile1);
		assertTrue(f1.exists());
		File f2 = new File(".gitlet/cool-beans/commit1/" + wugFile2);
		assertTrue(f2.exists());
		File f3 = new File(".gitlet/cool-beans/commit1/" + wugFile3);
		assertTrue(f3.exists());
		// initial commit is in cool-beans
		assertEquals("",gitlet("merge", "master"));
	}

	
	public void testMerge4() {
		String wugFile1 = TESTING_DIR + "wug1.txt";
		String wugText1 = "This is the first wug.";
		gitlet("init");
		createFile(wugFile1, wugText1);
		File f1 = new File(wugFile1);
		assertTrue(f1.exists());
		gitlet("branch","cool-beans");
		gitlet("add", wugFile1);
		gitlet("commit","commit1 in master");
		// 1 file committed in commit1
		File alpha = new File(wugFile1);
		try {
			recursiveDelete(alpha);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(f1.exists());
		// the wug file has been deleted 
		
		gitlet("checkout", "cool-beans");
		createFile(wugFile1, wugText1);
		assertTrue(f1.exists());
		gitlet("add", wugFile1);
		gitlet("commit","commit2 in cool-beans");
		f1 = new File(".gitlet/cool-beans/commit2/" + wugFile1);
		assertTrue(f1.exists());
		gitlet("checkout", "master");
		assertEquals("",gitlet("merge", "cool-beans"));
	}
	
	// now starts some of our end-to-end tests
	@Test
	public void test123() {
		String wugFile1 = TESTING_DIR + "wug1.txt";
		String wugText1 = "This is the first wug.";
		String wugFile2 = TESTING_DIR + "wug2.txt";
		String wugText2 = "This is the second wug.";
		String wugFile3 = TESTING_DIR + "wug3.txt";
		String wugText3 = "This is the third wug.";
		gitlet("init");
		createFile(wugFile1, wugText1);
		createFile(wugFile2, wugText2);
		createFile(wugFile3, wugText3);
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("add", wugFile3);
		gitlet("commit","commit1 in master");
		writeFile(wugFile1, "First wug has been changed");
		writeFile(wugFile2, "Second wug has been changed");
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("commit","commit2 in master");
		gitlet("branch","cool-beans");
		gitlet("checkout", "cool-beans");
		writeFile(wugFile1, "First wug in cool-beans");
		writeFile(wugFile2, "Second wug in cool-beans");
		gitlet("add", wugFile1);
		gitlet("add", wugFile2);
		gitlet("commit","commit3 in cool-beans");
		System.out.println(gitlet("checkout", "1", wugFile1));
		File f1 = new File(wugFile1);
		assertTrue(f1.exists());
		// first bulletin in Part F
		assertEquals("This is the first wug.", getText(f1.toString()));
		
		
		System.out.println(gitlet("checkout", "2", wugFile3));
		File f2 = new File(wugFile3);
		assertTrue(f2.exists());
		// second bulletin in Part F
		assertEquals("This is the third wug.", getText(f2.toString()));
		
		// both the above checkouts were done to an arbitrary commit in another branch
		// so this satisfies third bulletin in Part F
	}

	

	

	@Test
	public void rebaseUlti() {
		String T = TESTING_DIR;
		String G = GITLET_DIR;

		gitlet("init");
		createFile(T + "a", "");
		createFile(T + "b", "");
		createFile(T + "c", "");
		gitlet("add", T + "a");
		gitlet("add", T + "b");
		gitlet("add", T + "c");
		gitlet("commit", "c1");
		gitlet("branch", "b1");
		gitlet("branch", "b2");
		gitlet("checkout", "b1");
		writeFile(T + "b", "x");
		gitlet("add", T + "b");
		gitlet("commit", "c2");
		gitlet("branch", "b3");
		gitlet("branch", "b4");
		gitlet("checkout", "b4");
		writeFile(T + "c", "x");
		createFile(T + "d", "");
		writeFile(T + "g", "x");
		gitlet("add", T + "c");
		gitlet("add", T + "d");
		gitlet("add", T + "g");
		gitlet("commit", "c3");
		gitlet("checkout", "b2");
		writeFile(T + "c", "y");
		writeFile(T + "d", "y");
		createFile(T + "e", "y");
		gitlet("add", T + "c");
		gitlet("add", T + "d");
		gitlet("add", T + "e");
		gitlet("commit", "c4");
		gitlet("checkout", "b4");
		gitlet("rebase", "b2");
		// commit5
		assertEquals("", getText(G + "b2/commit5/" + T + "a"));
		assertEquals("x", getText(G + "b2/commit5/" + T + "b"));
		assertEquals("x", getText(G + "b2/commit5/" + T + "c"));
		assertEquals("", getText(G + "b2/commit5/" + T + "d"));
		assertEquals("x", getText(G + "b2/commit5/" + T + "g"));
		assertEquals("y", getText(G + "b2/commit5/" + T + "e"));
		// commit6
		assertEquals("", getText(G + "b2/commit6/" + T + "a"));
		assertEquals("x", getText(G + "b2/commit6/" + T + "b"));
		assertEquals("y", getText(G + "b2/commit6/" + T + "c"));// ninth bullet point of part F
		assertEquals("y", getText(G + "b2/commit6/" + T + "d"));
		assertEquals("y", getText(G + "b2/commit6/" + T + "e"));
		// working dir
		assertEquals("", getText(T + "a"));
		assertEquals("x", getText(T + "b"));
		assertEquals("x", getText(T + "c"));
		assertEquals("", getText(T + "d"));
		assertEquals("y", getText(T + "e"));
		assertEquals("x", getText(T + "g"));
		
		gitlet("checkout","b2");//eighth point of part F
		gitlet("log");
		gitlet("checkout","b4");
		gitlet("log");
		
		finishUp();
	}
}
