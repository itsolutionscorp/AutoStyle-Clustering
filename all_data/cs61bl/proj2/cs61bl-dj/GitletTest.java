import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import jdk.nashorn.internal.ir.annotations.Ignore;

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

	@Test
	public void testStatus() {
		gitlet("init");

		String bugFileName = TESTING_DIR + "bug.txt";
		createFile(bugFileName, "This is a bug");

		// staged files, *master, no untracked
		gitlet("add", bugFileName);
		gitlet("status");

		// no staged, *master, no untracked
		gitlet("commit", "Added bug");
		gitlet("status");

		// untracked, *master, staged
		String tugFileName = TESTING_DIR + "tug.txt";
		createFile(tugFileName, "This is a tug");
		gitlet("add", tugFileName);
		gitlet("rm", bugFileName);
		gitlet("status");
		gitlet("commit", "Added tug removed bug");

		// *master
		gitlet("branch", "newBranch");
		gitlet("status");

		// *newBranch
		gitlet("checkout", "newBranch");
		gitlet("status");
	}

	public static boolean doesFileExists(String filePath) {
		return Files.exists(Paths.get(filePath));
	}

	/**
	 * Adding files normally.
	 */
	@Test
	public void xTestBasicAddCase1() {
		// test that testing_dir starts out empty
		LinkedList<File> files;
		files = new LinkedList<File>();
		Gitlet.collectFiles(TESTING_DIR, files);
		assertTrue(files.isEmpty());

		// initial set up
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");

		// check that staging area is empty
		files = new LinkedList<File>();
		Gitlet.collectFiles(".gitlet/stage/", files);
		assertTrue(files.isEmpty());

		// add wug.txt
		gitlet("add", wugFileName);

		// assert that wug.txt is staged
		files = new LinkedList<File>();
		Gitlet.collectFiles(".gitlet/stage/", files);
		assertTrue(!files.isEmpty());
		// System.out.println(files.get(0));
		assertTrue(files.get(0).toString()
				.equals(".gitlet/stage/test_files/wug.txt"));

		// make the commit
		gitlet("commit", "added wug");

		// check that staging area is empty
		files = new LinkedList<File>();
		Gitlet.collectFiles(".gitlet/stage/", files);
		assertTrue(files.isEmpty());
	}

	/**
	 * Adding files that are already being tracked.
	 */
	@Test
	public void xTestBasicAddCase2() {
		// test that testing_dir starts out empty
		LinkedList<File> files;

		// initial set up
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText1 = "This is a wug.";
		String wugText2 = "This is not a wug.";
		createFile(wugFileName, wugText1);
		gitlet("init");

		// add wug.txt
		gitlet("add", wugFileName);

		// make the commit
		gitlet("commit", "added wug");

		// update the file
		createFile(wugFileName, wugText2);

		gitlet("add", wugFileName);

		// make sure that it's staged
		files = new LinkedList<File>();
		Gitlet.collectFiles(".gitlet/stage/", files);
		System.out.println(files.get(0).toString());
		assertTrue(files.get(0).toString()
				.equals(".gitlet/stage/test_files/wug.txt"));

		// change wug.txt
		gitlet("commit", "edited wug");

		assertEquals(wugText2, getText(wugFileName));
	}

	public static void printDirectories() {
		System.out.println("WORKING DIR\n==========");
		pdh(GITLET_DIR, 0);
	}

	public static void pdh(String dir, int ind) {
		for (int i = 0; i < ind; i++) {
			System.out.print("    ");
		}

		System.out.println(dir);

		File currDir = new File(dir);

		if (!currDir.isDirectory()) {
			return;
		} else {
			File[] files = currDir.listFiles();
			for (File f : files) {
				pdh(f.toString(), ind + 1);
			}
		}
	}

	public static void collectFiles(String dir, LinkedList<File> filesLL) {
		LinkedList<Files> rtn = new LinkedList<Files>();

		File currDir = new File(dir);
		File[] files = currDir.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				collectFiles(f.toString(), filesLL);
			} else {
				filesLL.add(f);
			}
		}
	}

	/**
	 * Making sure that we're adding the latest snapshot of the file staged
	 */
	@Test
	public void xTestBasicAddCase3() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";

		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		// commit1's f1 should have "a"
		gitlet("add", f1);

		// overwrite
		writeFile(f1, text3);

		gitlet("commit", "added f1");

		// check to see that we're using the staged one
		gitlet("checkout", "1", f1);
		assertEquals(text1, getText(f1));
	}

	/**
	 * adding a file that's staged and tracked
	 */
	@Test
	public void xTestBasicAddCase4() {
		// initial set up
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";

		createFile(f1, text1);
		gitlet("init");

		gitlet("add", f1);
		gitlet("commit", "added f1");

		createFile(f1, text2);
		gitlet("add", f1);
		// System.out.println(getText("test_files/a.txt"));
		// System.out.println(getText(".gitlet/stage/test_files/a.txt"));

		createFile(f1, text3);
		gitlet("add", f1);
		// System.out.println(getText("test_files/a.txt"));
		// System.out.println(getText(".gitlet/stage/test_files/a.txt"));

		gitlet("commit", "f1 should be 3");
		gitlet("log");

		createFile(f1, text1);
		gitlet("checkout", "2", f1);

		assertEquals(text3, getText(f1));

	}

	@Test
	public void TestDoesNotStageFilesThatDontExist() {
		// initial set up
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";

		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("add", TESTING_DIR + "d.txt");

		assertTrue(!(new File(TESTING_DIR + "d.txt").exists()));
		assertTrue((new File(TESTING_DIR + "a.txt").exists()));
	}

	/* file that NOT tracked and NOT staged */
	@Test
	public void xTestRemove1() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";

		writeFile(f1, text1);
		writeFile(f2, text2);
		gitlet("init");

		gitlet("add", f1);
		gitlet("commit", "added f1 and f2");

		gitlet("add", f2);
		gitlet("rm", f3);

		gitlet("commit", "added f2");
	}

	/* file that NOT tracked and IS staged */
	@Test
	public void xTestRemove2() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("commit", "added f1 and f2");

		gitlet("add", f2);
		gitlet("rm", f2);
		gitlet("add", f3);

		gitlet("commit", "added f3");
		assertTrue((new File(".gitlet/commits/commit2/test_files/c.txt")
				.exists()));
		assertTrue(!(new File(".gitlet/commits/commit2/test_files/b.txt")
				.exists()));
	}

	/* file that IS tracked and NOT staged */
	@Test
	public void xTestRemove3() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("commit", "added f1 and f2");

		gitlet("rm", f2);

		gitlet("commit", "removed f2");
		assertTrue((new File(".gitlet/commits/commit1/test_files/a.txt")
				.exists()));
		assertTrue((new File(".gitlet/commits/commit1/test_files/b.txt")
				.exists()));
	}

	/* file that IS tracked and IS staged */
	@Test
	public void xTestRemove4() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("commit", "added f1 and f2");

		writeFile(f2, "hello");
		gitlet("add", f2);
		gitlet("rm", f2);
		gitlet("add", f3);

		gitlet("commit", "added f3");
	}

	/* empty commits aren't allowed */
	@Test
	public void xTestCommit1() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("commit", "added f1 and f2");

		String t1 = gitlet("log");
		gitlet("commit", "did nothing");
		String t2 = gitlet("log");

		assertTrue(t1.equals(t2));

		gitlet("add", f3);
		gitlet("rm", f3);
		String t3 = gitlet("log");
		gitlet("commit", "did nothing");
		String t4 = gitlet("log");
		assertTrue(t3.equals(t4));
	}

	/* branch tests */
	@Test
	public void xbranchTests() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("commit", "added f1 and f2");

		gitlet("numbranches");
		gitlet("branch", "b1");
		gitlet("numbranches");
		gitlet("branch", "b2");
		gitlet("numbranches");
		gitlet("branch", "b1");
		gitlet("numbranches");

		String s1 = gitlet("status");

		// test that we can't remove the branch we're currently on.
		gitlet("rm-branch", "master");
		String s2 = gitlet("status");
		assertTrue(s1.equals(s2));

		// removing a branch
		gitlet("rm-branch", "b1");
		s2 = gitlet("status");
		assertTrue(!s1.equals(s2));

		gitlet("rm-branch", "dne");
		s1 = gitlet("status");
		assertTrue(s1.equals(s2));
	}

	/* global log tests */
	@Test
	public void xglobalLog1() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String f4 = TESTING_DIR + "d.txt";
		String f5 = TESTING_DIR + "e.txt";
		String f6 = TESTING_DIR + "f.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		writeFile(f4, "4");
		writeFile(f5, "5");
		writeFile(f6, "6");
		gitlet("init");
		gitlet("branch", "b1");

		// commit 1
		gitlet("add", f1);
		gitlet("commit", "added f1");
		gitlet("branch", "b2");

		// commit 2
		gitlet("add", f2);
		gitlet("commit", "added f2");

		// commit 3
		writeFile(f1, "a new f1");
		gitlet("add", f1);
		gitlet("commit", "modified f1");

		gitlet("status");
		gitlet("checkout", "b1");
		gitlet("status");

		// commit 4
		gitlet("add", f2);
		gitlet("commit", "added f2 x");

		// commit 5
		gitlet("branch", "b3");
		gitlet("checkout", "b3");
		gitlet("add", f4);
		gitlet("commit", "added b4");

		// commit 6
		gitlet("checkout", "b1");
		gitlet("add", f5);
		gitlet("commit", "added f5");

		// commit 7
		gitlet("checkout", "b2");
		gitlet("add", f6);
		gitlet("commit", "added f6");

		// manual testing.
	}

	/* testing find */
	@Test
	public void xtestFind() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String f4 = TESTING_DIR + "d.txt";
		String f5 = TESTING_DIR + "e.txt";
		String f6 = TESTING_DIR + "f.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		writeFile(f4, "4");
		writeFile(f5, "5");
		writeFile(f6, "6");
		gitlet("init");

		// 1
		gitlet("add", f1);
		gitlet("commit", "added f1");

		gitlet("find", "added f0");
		gitlet("find", "added f1");

		// 2
		gitlet("add", f2);
		gitlet("commit", "added f2");

		// 3
		writeFile(f1, "shieeet");
		gitlet("add", f1);
		gitlet("commit", "modified f1");

		// 4
		gitlet("add", f3);
		gitlet("commit", "added f3");

		// 5
		writeFile(f1, "aww man");
		gitlet("add", f1);
		gitlet("commit", "modified f1");

		// 6
		gitlet("add", f4);
		gitlet("commit", "added f4");

		gitlet("find", "modified f1");

		// manual testing
	}

	/* testing checkout1 */
	@Test
	public void xtestCheckout1() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);

		// 1a: overwriting
		gitlet("init");

		gitlet("add", f1);
		gitlet("commit", "added f1");
		gitlet("branch", "b1");

		writeFile(f1, "2");
		assertTrue(getText(f1).equals("2"));

		gitlet("checkout", f1);
		assertTrue(getText(f1).equals("1"));

		// 1b: not overwriting
		gitlet("add", f2);
		gitlet("commit", "added f2");
		assertTrue((new File(f2)).exists());
		(new File(f2)).delete();
		assertTrue(!(new File(f2)).exists());
		gitlet("checkout", f2);
		assertTrue((new File(f2)).exists());
		assertTrue(getText(f2).equals("2"));

		// 2: not tracked by head
		// gitlet("add", f3);
		assertTrue((new File(f3)).exists());
		(new File(f3)).delete();
		gitlet("checkout", f3);
		assertTrue(!(new File(f3)).exists());
	}

	/* testing checkout2 */
	@Test
	public void xtestCheckout2() {
		System.out.println("first");
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);

		/*
		 * overwrite not overwrite target commit doesnt track the file target
		 * commit dne
		 */

		gitlet("init");
		gitlet("branch", "b1");

		gitlet("add", f1);
		gitlet("commit", "added f1");
		gitlet("add", f2);
		gitlet("commit", "added f2");

		gitlet("checkout", "b1");
		gitlet("status");

		(new File(f1)).delete();
		assertTrue(!(new File(f1)).exists());
		gitlet("checkout", "2", f1);
		assertTrue((new File(f1)).exists());
		assertTrue(getText(f1).equals("1"));

		writeFile(f2, "go away");
		gitlet("checkout", "2", f2);
		assertTrue(getText(f2).equals("2"));

		(new File(f3)).delete();
		assertTrue(!(new File(f3)).exists());
		gitlet("checkout", "2", f3);
		assertTrue(!(new File(f3)).exists());
	}

	/* testing checkout3 */
	@Test
	public void xtestCheckout3() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);

		gitlet("init");

		gitlet("add", f1);
		gitlet("commit", "added f1");

		gitlet("branch", "b1");
		gitlet("checkout", "b1");

		gitlet("add", f2);
		gitlet("commit", "added f2");

		gitlet("add", f3);
		gitlet("commit", "added f3");

		gitlet("checkout", "master");

		// modify a.txt
		writeFile(f1, "4");
		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("commit", "modified f1 and added f2");

		File f9 = new File(f3);
		f9.delete();

		assertTrue((new File(f1)).exists());
		assertTrue((new File(f2)).exists());
		assertTrue(!(new File(f3)).exists());

		assertTrue(getText(f1).equals("4"));
		assertTrue(getText(f2).equals("2"));

		gitlet("checkout", "b1");

		assertTrue((new File(f1)).exists());
		assertTrue((new File(f2)).exists());
		assertTrue((new File(f3)).exists());

		assertTrue(getText(f1).equals("1"));
		assertTrue(getText(f2).equals("2"));
		assertTrue(getText(f3).equals("3"));

		// test that status is changed upon checkout
		String s1 = gitlet("status");
		gitlet("checkout", "master");
		assertTrue(!s1.equals(gitlet("status")));

		assertTrue((new File(f1)).exists());
		assertTrue((new File(f2)).exists());
		assertTrue((new File(f3)).exists());
		assertTrue(getText(f1).equals("4"));
		assertTrue(getText(f2).equals("2"));
		assertTrue(getText(f3).equals("3"));

		gitlet("myFiles");
	}

	/* Tests merge. */
	@Test
	public void testMerge1() {
		// dont forget non-overlapping portion
		// test that files that are good still get staged even if there's a
		// conflict

		String f1 = TESTING_DIR + "a.txt";

		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String f4 = TESTING_DIR + "d.txt";
		String f5 = TESTING_DIR + "e.txt";
		String f6 = TESTING_DIR + "f.txt";
		String f7 = TESTING_DIR + "g.txt";
		String f8 = TESTING_DIR + "h.txt";

		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		String text4 = "4";
		String text5 = "5";
		String text6 = "6";
		String text7 = "7";
		String text8 = "8";

		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		writeFile(f4, text4);
		writeFile(f5, text5);
		writeFile(f6, text6);
		writeFile(f7, text7);
		writeFile(f8, text8);

		gitlet("init");
		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("add", f3);
		gitlet("add", f4);
		gitlet("commit", "added f1, f2, f3, f4");

		gitlet("branch", "b2");
		gitlet("checkout", "b2");

		writeFile(f3, "3_diff2");
		writeFile(f4, "4_diff2");
		writeFile(f6, "6_diff2");
		gitlet("add", f3);
		gitlet("add", f4);
		gitlet("add", f5);
		gitlet("add", f6);
		gitlet("add", f7);
		gitlet("commit", "added f5 and f7, and changed f3, f4, and f6.");

		gitlet("checkout", "master");
		writeFile(f2, "2_diff1");
		writeFile(f4, "4_diff1");
		gitlet("add", f2);
		gitlet("add", f4);
		gitlet("add", f5);
		writeFile(f6, "6");
		gitlet("add", f6);
		gitlet("add", f8);
		gitlet("commit", "added f5, f6, f8, and changed f2, f4");

		gitlet("merge", "b2");
		assertTrue((new File(f1)).exists());
		assertTrue((new File(f2)).exists());
		assertTrue((new File(f3)).exists());
		assertTrue((new File(f4)).exists());
		assertTrue((new File(f5)).exists());
		assertTrue((new File(f6)).exists());
		assertTrue((new File(f7)).exists());
		assertTrue((new File(f8)).exists());

		assertTrue(getText(f1).equals("1"));
		assertTrue(getText(f2).equals("2_diff1"));
		assertTrue(getText(f3).equals("3_diff2"));
		assertTrue(getText(f4).equals("4_diff1"));
		assertTrue(getText(f5).equals("5"));
		assertTrue(getText(f6).equals("6"));
		assertTrue(getText(f7).equals("7"));
		assertTrue(getText(f8).equals("8"));

		String f4c = f4 + ".conflicted";
		String f6c = f6 + ".conflicted";

		assertTrue((new File(f4c)).exists());
		System.out.println(getText(f6));
		System.out.println(getText(f6c));
		assertTrue((new File(f6c)).exists());

		assertTrue(getText(f4c).equals("4_diff2"));

		assertTrue(getText(f6c).equals("6_diff2"));

		System.out.println("hi");
		gitlet("commit", "merged");
		System.out.println("bye");
		gitlet("tree");
		gitlet("log");
		gitlet("status");
		gitlet("myFiles");

	}

	@Test
	public void simpleMerge() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String f4 = TESTING_DIR + "d.txt";
		String f5 = TESTING_DIR + "e.txt";
		String f6 = TESTING_DIR + "f.txt";
		String f7 = TESTING_DIR + "g.txt";
		String f8 = TESTING_DIR + "h.txt";
		String f9 = TESTING_DIR + "i.txt";
		String f10 = TESTING_DIR + "j.txt";
		String f11 = TESTING_DIR + "k.txt";
		String f12 = TESTING_DIR + "l.txt";

		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		String text4 = "4";
		String text5 = "5";
		String text6 = "6";
		String text7 = "7";
		String text8 = "8";
		String text9 = "9";
		String text10 = "10";
		String text11 = "11";
		String text12 = "12";

		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		writeFile(f4, text4);
		writeFile(f5, text5);
		writeFile(f6, text6);
		writeFile(f7, text7);
		writeFile(f8, text8);
		writeFile(f9, text9);
		writeFile(f10, text10);
		writeFile(f11, text11);
		writeFile(f12, text12);

		gitlet("init");
		gitlet("add", f1);
		gitlet("add", f4);
		gitlet("add", f5);
		gitlet("add", f6);
		gitlet("add", f11);
		gitlet("add", f12);
		gitlet("commit", "Added f1, f4, f5, f11, f12");

		gitlet("branch", "b1");

		writeFile(f5, "5_master");
		writeFile(f6, "6_master");
		writeFile(f8, "8_master");
		gitlet("add", f2);
		gitlet("add", f5);
		gitlet("add", f6);
		gitlet("add", f7);
		gitlet("add", f8);
		gitlet("rm", f11);
		gitlet("commit", "added f2 f7 f8 modified f5 f6 removed f11");

		gitlet("checkout", "b1");
		writeFile(f4, "4_b1");
		writeFile(f5, "5_b1");
		writeFile(f6, "6"); // back to normal
		writeFile(f8, "8_b1");
		gitlet("add", f3);
		gitlet("add", f4);
		gitlet("add", f5);
		gitlet("add", f6);
		gitlet("add", f7);
		gitlet("add", f8);
		gitlet("rm", f12);
		gitlet("commit", "added f3 f6 f8 and modified f4 f5, removed f12");

		gitlet("checkout", "master");
		gitlet("merge", "b1");

		// gitlet("merge", )

		// gitlet("log");
		// gitlet("global-log");

		assertTrue(getText(f1).equals("1"));
		assertTrue(getText(f2).equals("2"));
		assertTrue(getText(f3).equals("3"));
		assertTrue(getText(f4).equals("4_b1"));
		assertTrue(getText(f5).equals("5_master"));
		assertTrue(getText(f6).equals("6_master"));
		assertTrue(getText(f7).equals("7"));
		assertTrue(getText(f8).equals("8_master"));
		// assertTrue(getText(f10).equals("10"));
		assertTrue(getText(f11).equals("11"));
		assertTrue(getText(f12).equals("12"));

		String f1c = f1 + ".conflicted";
		String f2c = f2 + ".conflicted";
		String f3c = f3 + ".conflicted";
		String f4c = f4 + ".conflicted";
		String f5c = f5 + ".conflicted";
		String f6c = f6 + ".conflicted";
		String f7c = f7 + ".conflicted";
		String f8c = f8 + ".conflicted";
		String f9c = f9 + ".conflicted";
		String f10c = f10 + ".conflicted";
		String f11c = f11 + ".conflicted";
		String f12c = f12 + ".conflicted";

		assertTrue(!(new File(f1c).exists()));
		assertTrue(!(new File(f2c).exists()));
		assertTrue(!(new File(f3c).exists()));
		assertTrue(!(new File(f4c).exists()));
		assertTrue((new File(f5c).exists()));
		assertTrue(!(new File(f6c).exists()));
		assertTrue(!(new File(f7c).exists()));
		assertTrue((new File(f8c).exists()));
		assertTrue(!(new File(f9c).exists()));
		assertTrue(!(new File(f10c).exists()));
		assertTrue(!(new File(f11c).exists()));
		assertTrue(!(new File(f12c).exists()));

		assertTrue(getText(f5c).equals("5_b1"));
		assertTrue(getText(f8c).equals("8_b1"));

		gitlet("commit", "idgaf, fuck yo conflict");
		gitlet("myFiles");
		gitlet("tree");

	}

	@Test
	public void testMergeComplex() {
		gitlet("init");

		// add A, B, D, E to Master
		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		gitlet("commit", "Added A");

		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("commit", "Added B");

		String DFileName = "D.txt";
		String DText = "This is D.";
		createFile(DFileName, DText);
		gitlet("add", DFileName);
		gitlet("commit", "Added D");

		String EFileName = "E.txt";
		String EText = "This is E.";
		createFile(EFileName, EText);
		gitlet("add", EFileName);
		gitlet("commit", "Added E");

		// newBranch has modified A & B & D, deletes E
		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		String A2FileName = "A.txt";
		String A2Text = "This is newBranch's A.";
		createFile(A2FileName, A2Text);
		gitlet("add", A2FileName);
		gitlet("commit", "Modified A in NewBranch");

		gitlet("rm", "E.txt");

		String DFileName2 = "D.txt";
		String DText2 = "This is newBranch's D.";
		createFile(DFileName2, DText2);
		gitlet("add", DFileName2);
		gitlet("commit", "Modified D in newBranch");

		String BFileNewBranch = "B.txt";
		String BNewBranchText = "This is newBranch's B.";
		createFile(BFileNewBranch, BNewBranchText);
		gitlet("add", BFileNewBranch);
		gitlet("commit", "Modified B in newBranch");

		// master has old A, modified B, new C, deletes D, modifies E
		gitlet("checkout", "master");
		String CFileName = "C.txt";
		String CText = "This is C.";
		createFile(CFileName, CText);
		gitlet("add", CFileName);
		gitlet("commit", "Added C");

		String BFileMaster = "B.txt";
		String BMasterText = "This is Master's B.";
		createFile(BFileMaster, BMasterText);
		gitlet("add", BFileMaster);
		gitlet("commit", "Modified B in Master");

		gitlet("rm", "D.txt");

		String EFileName2 = "E.txt";
		String EText2 = "This is Master's New E.";
		createFile(EFileName2, EText2);
		gitlet("add", EFileName2);
		gitlet("commit", "Modified E in Master");

		// expected outcome: stage new A from newBranch (overwrite master A)
		// keep C from master, keep B from master, marked as 'conflicted'
		// stage D from newBranch, keep E from master
		gitlet("merge", "newBranch");

		// try to do commands banned by conflicted state -->should err until
		// commit
		gitlet("branch", "ThirdBranch");
		gitlet("rebase", "newBranch");
		gitlet("reset", "7");
		gitlet("rm-branch", "newBranch");

		// get out of conflicted state
		gitlet("commit", "Getting out of conflicted state");

		// try to merge branch with itself -->err
		gitlet("merge", "master");

	}

	@Test
	public void simpleRebase() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String f4 = TESTING_DIR + "d.txt";
		String f5 = TESTING_DIR + "e.txt";
		String f6 = TESTING_DIR + "f.txt";

		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		String text4 = "4";
		String text5 = "5";
		String text6 = "6";

		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		writeFile(f4, text4);
		writeFile(f5, text5);
		writeFile(f6, text6);

		gitlet("init");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("add", f3);
		gitlet("commit", "added f1, f2, f3");

		gitlet("branch", "b1");

		writeFile(f2, "19");
		writeFile(f3, "16");
		writeFile(f4, "27");
		writeFile(f6, "31");
		gitlet("add", f2);
		gitlet("add", f3);
		gitlet("add", f4);
		gitlet("add", f6);
		gitlet("commit", "x");

		gitlet("checkout", "b1");
		writeFile(f1, "5");
		writeFile(f2, "2");
		writeFile(f3, "3");
		writeFile(f4, "4");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("add", f3);
		gitlet("add", f4);
		gitlet("commit", "x");

		writeFile(f2, "3");
		gitlet("add", f2);
		gitlet("add", f5);
		gitlet("commit", "x");

		writeFile(f2, "4");
		writeFile(f3, "17");
		gitlet("add", f2);
		gitlet("add", f3);
		gitlet("commit", "x");

		gitlet("rebase", "master");

		gitlet("status");
		gitlet("tree");
		gitlet("cc");

		// gitlet("tree");
		gitlet("myFiles");
		// gitlet("status");
	}

	@Test
	public void thoroughRebase() {
		String f1 = TESTING_DIR + "a.txt";
		String f2 = TESTING_DIR + "b.txt";
		String f3 = TESTING_DIR + "c.txt";
		String text1 = "1";
		String text2 = "2";
		String text3 = "3";
		writeFile(f1, text1);
		writeFile(f2, text2);
		writeFile(f3, text3);
		gitlet("init");

		gitlet("add", f1);
		gitlet("add", f2);
		gitlet("commit", "added f1 and f2");

		gitlet("numbranches");
		gitlet("branch", "b1");
		gitlet("numbranches");
		gitlet("branch", "b2");
		gitlet("numbranches");
		gitlet("branch", "b1");
		gitlet("branch", "b4");

		// check for proper # of branches
		assertEquals(gitlet("numbranches"), "4");

		// Add f3 to b1
		gitlet("checkout", "b1");
		gitlet("add", f3);
		gitlet("commit", "added f3 to b1");

		assertTrue(doesFileExists(f3));

		// Add different f3 to b2
		gitlet("checkout", "b2");
		writeFile(f3, "4");
		gitlet("add", f3);
		gitlet("commit", "added modified f3 to b2");

		// extend b2 by one more commit
		writeFile(f3, "5");
		gitlet("add", f3);
		gitlet("commit", "added doubly modified f3 to b2");

		assertTrue(doesFileExists(f3));
		assertEquals(gitlet("cc"), "Currently on Commit4.");
		assertEquals(getText(f3), "5");

		// rebase b2 to b1, insure f3 still exists.
		gitlet("rebase", "b1");
		assertTrue(doesFileExists(f3));

		// testing that b1's f3 has been untouched
		gitlet("checkout", "b1");
		assertEquals(getText(f3), "3");

		// testing that commitID was properly updated
		// assertEquals(gitlet("cc"), "Currently on Commit2.");

		// testing that text for file f3 has been updated.
		gitlet("checkout", "b2");
		assertEquals(getText(f3), "5");
		// assertEquals(gitlet("cc"), "Currently on Commit6.");

		// going one commit back, landing after b1's last commit/b2's second to
		// most recent commit.
		// gitlet("checkout", "5", "f3");
		// assertEquals(getText(f3), "4");

		// add a commit between b1 and the rebased b2
		gitlet("checkout", "b1");
		assertEquals(getText(f3), "3");
		writeFile(f3, "6");
		assertEquals(getText(f3), "6");
		gitlet("add", f3);
		gitlet("commit", "altering f3 in b1");

		// check that we've moved up a commit
		assertEquals(getText(f3), "6");
		// assertEquals(gitlet("cc"), "Currently on Commit7.");

		// create third branch
		gitlet("branch", "b3");
		gitlet("checkout", "b3");
		assertEquals(getText(f3), "6");
		writeFile(f3, "7");
		gitlet("add", f3);
		gitlet("commit", "altering f3 in b3");
		// assertEquals(gitlet("cc"), "Currently on Commit8.");

		// rebase to b4, checkout master's f3, overwrite 7 to 3.
		gitlet("checkout", "b4");
		// assertEquals(gitlet("cc"), "Currently on Commit9.");
		writeFile(f3, "8");
		gitlet("add", f3);
		gitlet("commit", "altering f3 in b4");
		gitlet("checkout", "b3");
		gitlet("rebase", "b4");
		assertEquals(getText(f3), "7");
		// assertEquals(gitlet("cc"), "Currently on Commit12.");
		gitlet("checkout", "b4");
		writeFile(f3, text3);
		gitlet("add", f3);
		gitlet("commit", "writing f3 in master");
		assertEquals(getText(f3), "3");

		// checking that checkout properly overwrites b3's f3.
		// gitlet("checkout", "b3");
		// gitlet("checkout", "13", "f3");
		// assertEquals(getText(f3), "3");

		// check that other branch file holds proper info.
		gitlet("checkout", "b2");
		assertEquals(getText(f3), "5");
	}

	@Test
	public void testCmdCommitM() {
		gitlet("init");
		// commits are staged
		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		Path checkPath = Paths.get(".gitlet/stage/A.txt");
		assertTrue(Files.exists(checkPath));

		// staging area is cleared after commit, file goes to right commit
		gitlet("commit", "Added A");
		Path commitA = Paths.get(".gitlet/commits/commit1/A.txt");
		assertTrue(Files.exists(commitA));
		assertTrue(!Files.exists(checkPath));

		// commit 2 inherits commit 1's files
		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("commit", "Added B.txt");
		gitlet("myFiles");

		// new Branch inherits child's files
		gitlet("branch", "CBranch");
		gitlet("checkout", "CBranch");
		String CFileName = "C.txt";
		String CText = "This is C.";
		createFile(CFileName, CText);
		gitlet("add", CFileName);
		gitlet("commit", "Added C.txt");
		gitlet("myFiles");

		// remove C from CBranch, checkout master (no C), remove A then commit
		// -->should ignore remove C
		gitlet("rm", "C.txt");
		gitlet("status"); // C untracked
		gitlet("checkout", "master");
		gitlet("status");
		gitlet("rm", "A.txt");
		gitlet("status");
		gitlet("commit", "Removed A.txt, ignored remove C.txt");
		gitlet("myFiles"); // only B

		// can't make a commit without making changes, aborts
		gitlet("commit", "Changed nothing");
		gitlet("status");

		// no message -->err
		gitlet("commit");
		gitlet("commit", "");
		gitlet("global-log"); // should have 4 commits

	}

	@Test
	public void testCmdAddM() {
		gitlet("init");
		// files are staged
		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		File stagedA = new File(".gitlet/stage/A.txt");
		Path checkPath = Paths.get(".gitlet/stage/A.txt");
		assertTrue(Files.exists(checkPath));
		gitlet("status");
		gitlet("commit", "Added A.txt");

		// added then removed file; staged then untracked
		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("status");
		gitlet("rm", BFileName);
		gitlet("status");

		// changes to file content are reflected
		String A2FileName = "A.txt";
		String A2Text = "This is A 2.0.";
		createFile(A2FileName, A2Text);
		gitlet("add", A2FileName);
		gitlet("commit", "Added A 2.0");
		String locationA1 = ".gitlet/commits/commit1/A.txt";
		String locationA2 = ".gitlet/commits/commit2/A.txt";
		assertTrue(!printFileContentM(locationA1).equals(
				printFileContentM(locationA2)));

		// print err: File does not exist
		gitlet("add", "C.txt"); // FIX ME -->DOES NOT ERROR

		gitlet("status");
		gitlet("add", "D.txt"); // errors
		gitlet("status");
	}

	public String printFileContentM(String filename) {
		String rtn = "";
		try {
			BufferedReader inputReader = new BufferedReader(new FileReader(
					filename));
			String nextLine;
			while ((nextLine = inputReader.readLine()) != null) {
				System.out.println(nextLine);
				rtn += nextLine;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException ep) {
			ep.printStackTrace();
		}
		return rtn;
	}

	@Test
	public void testCmdRemoveM() {
		gitlet("init");

		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		gitlet("commit", "Added A.txt");

		// tests Untracked status
		gitlet("rm", AFileName);
		gitlet("status");
		gitlet("commit", "Removed AFileName");

		// not included in new commit
		Path checkCM2 = Paths.get(".gitlet/commits/commit2/A.txt");
		assertTrue(!Files.exists(checkCM2));
		gitlet("myFiles");

		// tests unstaging of staged file, not marked for 'untracked'
		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		File stagedB = new File(".gitlet/stage/B.txt");
		Path checkPath = Paths.get(".gitlet/stage/B.txt");
		assertTrue(Files.exists(checkPath));
		gitlet("status");
		gitlet("rm", BFileName);
		assertTrue(!Files.exists(checkPath));
		gitlet("status");

		// new commit shouldn't have B
		gitlet("commit", "Removed BFileName");
		gitlet("myFiles");

		// test failure case: file is neither staged nor tracked by head, print
		// error
		String CFileName = "C.txt";
		String CText = "This is C.";
		createFile(CFileName, CText);
		gitlet("rm", CFileName);
		gitlet("status");

	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testBasicCheckoutM() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		System.out.println(getText(wugFileName));
		System.out.println(wugText);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */

	@Test
	public void testBasicLogM() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String mugText = "This is a mug."; // adds new mugFile to commit
		String mugFileName = TESTING_DIR + "mug.txt";
		createFile(mugFileName, mugText);
		gitlet("add", mugFileName);
		String commitMessage3 = "added mug";
		gitlet("commit", commitMessage3);

		String commitMessage4 = "No change"; // No change -->should abort
		gitlet("commit", commitMessage4);

		gitlet("commit", ""); // Empty message -->abort --> "Please add message"

		String AText = "This is A."; // Added file then removed -->no change
										// -->abort
		String AFile = TESTING_DIR + "A.txt";
		createFile(AFile, AText);
		gitlet("add", AFile);
		gitlet("rm", AFile);
		gitlet("commit", "added then removed A");

		// should only be 3 commits
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(logContent));
	}

	@Test
	public void testFindM() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage = "added wug";
		gitlet("commit", commitMessage);

		// tug and wug and gug have same message
		String tugFileName = TESTING_DIR + "tug.txt";
		String tugText = "This is a tug.";
		createFile(tugFileName, tugText);
		gitlet("add", tugFileName);
		gitlet("commit", commitMessage);

		// bug is different
		String bugFileName = TESTING_DIR + "bug.txt";
		createFile(bugFileName, "This is a bug");
		gitlet("add", bugFileName);
		gitlet("commit", "Added bug");

		String gugFileName = TESTING_DIR + "gug.txt";
		createFile(gugFileName, "This is a gug");
		gitlet("add", gugFileName);
		gitlet("commit", commitMessage);

		gitlet("find", commitMessage); // should be 1,2,4
		gitlet("find", "Added bug"); // should be 3
		gitlet("find", "Zebra"); // should err

	}

	@Test
	public void testStatusM() {
		gitlet("init");

		String bugFileName = TESTING_DIR + "bug.txt";
		createFile(bugFileName, "This is a bug");

		// staged files, *master, no untracked
		gitlet("add", bugFileName);
		gitlet("status");

		// no staged, *master, no untracked
		gitlet("commit", "Added bug");
		gitlet("status");

		// staged, *master, untracked
		String tugFileName = TESTING_DIR + "tug.txt";
		createFile(tugFileName, "This is a tug");
		gitlet("add", tugFileName);
		gitlet("rm", bugFileName);
		gitlet("status");
		gitlet("commit", "Added tug removed bug");

		// *master
		gitlet("branch", "newBranch");
		gitlet("status");

		// *newBranch
		gitlet("checkout", "newBranch");
		gitlet("status");

	}

	@Test
	public void testGlobaLogM() {
		gitlet("init");

		String bugFileName = TESTING_DIR + "bug.txt";
		createFile(bugFileName, "This is a bug");
		gitlet("add", bugFileName);
		gitlet("commit", "Added bug");

		String tugFileName = TESTING_DIR + "tug.txt";
		createFile(tugFileName, "This is a tug");
		gitlet("add", tugFileName);
		gitlet("commit", "Added tug");

		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		gitlet("status");
		String hugFileName = TESTING_DIR + "hug.txt";
		createFile(hugFileName, "This is a hug");
		gitlet("add", hugFileName);
		gitlet("commit", "Added hug");

		String slugFileName = TESTING_DIR + "slug.txt";
		createFile(slugFileName, "This is a slug");
		gitlet("add", slugFileName);
		gitlet("commit", "Added slug");

		gitlet("branch", "AnotherOne");
		gitlet("checkout", "AnotherOne");
		gitlet("status");
		String yugFileName = TESTING_DIR + "yug.txt";
		createFile(yugFileName, "This is a gug");
		gitlet("add", yugFileName);
		gitlet("commit", "Added ylug");

		gitlet("global-log");

	}

	@Test
	public void testBranchM() {
		gitlet("init");
		String tugFileName = TESTING_DIR + "tug.txt";
		createFile(tugFileName, "This is a tug");
		gitlet("add", tugFileName);
		gitlet("commit", "Added tug");
		gitlet("myFiles");
		gitlet("status");

		// commit should inherit tug from commit1 + new yug
		gitlet("branch", "Branch2");
		gitlet("checkout", "Branch2");
		String yugFileName = TESTING_DIR + "yug.txt";
		createFile(yugFileName, "This is a gug");
		gitlet("add", yugFileName);
		gitlet("status");
		gitlet("commit", "Added yug to branch2's head");
		gitlet("myFiles");
		gitlet("status");

		// commit should inherit tug from commit 1 ("master") but NOT commit2
		// (yug) + new slug
		gitlet("checkout", "master");
		gitlet("branch", "Branch3");
		gitlet("checkout", "Branch3");
		String slugFileName = TESTING_DIR + "slug.txt";
		createFile(slugFileName, "This is a slug");
		gitlet("add", slugFileName);
		gitlet("status");
		gitlet("commit", slugFileName);
		gitlet("myFiles");

	}

	@Test
	public void testCheckout2M() {
		gitlet("init");

		String bugFileName = TESTING_DIR + "bug.txt";
		createFile(bugFileName, "This is a bug");
		gitlet("add", bugFileName);
		gitlet("commit", "added Bug");
		String location1 = ".gitlet/commits/commit1/" + bugFileName;
		gitlet("myFiles");

		// commit2 overwrite bug
		createFile(bugFileName, "This is NOT a bug");
		gitlet("add", bugFileName);
		gitlet("commit", "Bug 2.0");
		String location2 = ".gitlet/commits/commit2/" + bugFileName;
		gitlet("myFiles");

		// commit3 overwrite bug
		createFile(bugFileName, "YOYOYO");
		gitlet("add", bugFileName);
		gitlet("commit", "das cool");
		String location3 = ".gitlet/commits/commit3/" + bugFileName;
		gitlet("myFiles");

		// commit 4 checkout commit1's bug
		gitlet("checkout", "1", TESTING_DIR + "bug.txt");
		gitlet("add", bugFileName);
		gitlet("commit", "Commit 4 should be same as 1");
		String location4 = ".gitlet/commits/commit4/" + bugFileName;
		gitlet("myFiles");

		printDirectories();

		// commit 5 checkout commit2's bug
		gitlet("checkout", "2", TESTING_DIR + "bug.txt");
		gitlet("add", bugFileName);
		gitlet("commit", "Commit 5 should be same as 2");
		String location5 = ".gitlet/commits/commit5/" + bugFileName;

		// commit 6 checkout commit3's bug
		gitlet("checkout", "3", TESTING_DIR + "bug.txt");
		gitlet("add", bugFileName);
		gitlet("commit", "Commit 6 should be same as 3");
		String location6 = ".gitlet/commits/commit6/" + bugFileName;

		assertTrue(printFileContentM(location4).equals(
				printFileContentM(location1)));
		assertTrue(printFileContentM(location5).equals(
				printFileContentM(location2)));
		assertTrue(printFileContentM(location6).equals(
				printFileContentM(location3)));

	}

	@Test
	public void testCheckout3() {
		gitlet("init");

		String bugFileName = TESTING_DIR + "bug.txt";
		createFile(bugFileName, "This is a bug");
		gitlet("add", bugFileName);
		gitlet("commit", "added Bug");
		String location1 = ".gitlet/commits/commit1/" + bugFileName;

		// overwrite bug once
		gitlet("branch", "Branch2");
		gitlet("checkout", "Branch2");
		createFile(bugFileName, "This is NOT a bug");
		gitlet("add", bugFileName);
		gitlet("commit", "Bug 2.0");
		String location2 = ".gitlet/commits/commit2/" + bugFileName;

		// overwrite bug twice
		gitlet("checkout", "master");
		gitlet("add", bugFileName);
		gitlet("commit", "Commit 3 should be same as Commit1");
		String location3 = ".gitlet/commits/commit3/" + bugFileName;

		// commit1's bug
		gitlet("checkout", "Branch2");
		gitlet("add", bugFileName);
		gitlet("commit", "Commit 4 should be same as Commmit2");
		String location4 = ".gitlet/commits/commit4/" + bugFileName;

		assertTrue(printFileContentM(location4).equals(
				printFileContentM(location2)));
		assertTrue(printFileContentM(location3).equals(
				printFileContentM(location1)));

	}

	@Test
	public void testMergeComplexM() {
		gitlet("init");

		// add A, B, D, E to Master
		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		gitlet("commit", "Added A");

		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("commit", "Added B");

		String DFileName = "D.txt";
		String DText = "This is D.";
		createFile(DFileName, DText);
		gitlet("add", DFileName);
		gitlet("commit", "Added D");

		String EFileName = "E.txt";
		String EText = "This is E.";
		createFile(EFileName, EText);
		gitlet("add", EFileName);
		gitlet("commit", "Added E");

		// newBranch has modified A & B & D, deletes E
		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		String A2FileName = "A.txt";
		String A2Text = "This is newBranch's A.";
		createFile(A2FileName, A2Text);
		gitlet("add", A2FileName);
		gitlet("commit", "Modified A in NewBranch");

		gitlet("rm", "E.txt");

		String DFileName2 = "D.txt";
		String DText2 = "This is newBranch's D.";
		createFile(DFileName2, DText2);
		gitlet("add", DFileName2);
		gitlet("commit", "Modified D in newBranch");

		String BFileNewBranch = "B.txt";
		String BNewBranchText = "This is newBranch's B.";
		createFile(BFileNewBranch, BNewBranchText);
		gitlet("add", BFileNewBranch);
		gitlet("commit", "Modified B in newBranch");

		// master has old A, modified B, new C, deletes D, modifies E
		gitlet("checkout", "master");
		String CFileName = "C.txt";
		String CText = "This is C.";
		createFile(CFileName, CText);
		gitlet("add", CFileName);
		gitlet("commit", "Added C");

		String BFileMaster = "B.txt";
		String BMasterText = "This is Master's B.";
		createFile(BFileMaster, BMasterText);
		gitlet("add", BFileMaster);
		gitlet("commit", "Modified B in Master");

		gitlet("rm", "D.txt");

		String EFileName2 = "E.txt";
		String EText2 = "This is Master's New E.";
		createFile(EFileName2, EText2);
		gitlet("add", EFileName2);
		gitlet("commit", "Modified E in Master");

		// expected outcome: stage new A from newBranch (overwrite master A)
		// keep C from master, keep B from master, marked as 'conflicted'
		// stage D from newBranch, keep E from master
		gitlet("merge", "newBranch");

		// try to do commands banned by conflicted state -->should err until
		// commit
		gitlet("branch", "ThirdBranch");
		gitlet("rebase", "newBranch");
		gitlet("reset", "7");
		gitlet("rm-branch", "newBranch");

		// get out of conflicted state
		gitlet("commit", "Getting out of conflicted state");

		// try to merge branch with itself -->err
		gitlet("merge", "master");

	}

	@Test
	public void testMergeSimpleM() {
		gitlet("init");

		// add A, B, D, E to Master
		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		gitlet("commit", "Added A");

		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("commit", "Added B");

		// newBranch modifies A
		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		String A2FileName = "A.txt";
		String A2Text = "This is A 2.0.";
		createFile(A2FileName, A2Text);
		gitlet("add", A2FileName);
		gitlet("commit", "Modified A in NewBranch");

		gitlet("checkout", "master");

		gitlet("merge", "newBranch");
	}

	@Test
	public void testRebaseM() {
		gitlet("init");

		// add A, B to Master
		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		gitlet("commit", "Added A");

		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("commit", "Added B");

		// branchTwo modifies B, adds C, old A
		gitlet("branch", "branchTwo");
		gitlet("checkout", "branchTwo");
		String BFileNewBranch = "B.txt";
		String BNewBranchText = "This is branchTwo's B.";
		createFile(BFileNewBranch, BNewBranchText);
		gitlet("add", BFileNewBranch);
		gitlet("commit", "Modified B in branchTwo");

		String CFileName = "C.txt";
		String CText = "This is C.";
		createFile(CFileName, CText);
		gitlet("add", CFileName);
		gitlet("commit", "Added C");

		// branchThree modifies B (should propogate), modified A(should
		// propogate in replay), adds D
		gitlet("checkout", "master");
		gitlet("branch", "branchThree");
		gitlet("checkout", "branchThree");

		String BFileBranchThree = "B.txt";
		String BranchThreeText = "This is branchThree's B.";
		createFile(BFileBranchThree, BranchThreeText);
		gitlet("add", BFileBranchThree);
		gitlet("commit", "Modified B in BranchThree");

		String DFileName = "D.txt";
		String DText = "This is D.";
		createFile(DFileName, DText);
		gitlet("add", DFileName);
		gitlet("commit", "Added D");

		String AFileName2 = "A.txt";
		String AText2 = "This is NEW A that should propogate.";
		createFile(AFileName2, AText2);
		gitlet("add", AFileName2);
		gitlet("commit", "Modified A in branchTwo, should propogate");

		// branchThree is in the history of Branch4, gets A from commit7, D from
		// commit6, B from commit 5
		gitlet("branch", "branchFour");
		gitlet("checkout", "branchFour");
		String FFileName = "F.txt";
		String FText = "This is F.";
		createFile(FFileName, FText);
		gitlet("add", FFileName);
		gitlet("commit", "Added F");

		// failure case: nonExistantBranch
		gitlet("rebase", "nonExistantBranch");

		// failure case: can't rebase currentBranch
		gitlet("rebase", "branchFour");

		// failure case: input branch's head is in history of current branch
		// (up-to-date)
		gitlet("rebase", "branchThree");

		// normal case
		gitlet("checkout", "branchTwo");
		gitlet("tree");
		gitlet("rebase", "branchFour");
		gitlet("tree");
		gitlet("global-log");

		// expected: A from commit 7, B from commit 3, C from commit 4
		// TODO: what to do with D(new to Branch 3) and F (Branch 4=given
		// branch)
		gitlet("myFiles");

	}

	@Test
	public void testResetM() {
		gitlet("init");

		String AFileName = "A.txt";
		String AText = "This is A.";
		createFile(AFileName, AText);
		gitlet("add", AFileName);
		gitlet("commit", "Added A");

		String BFileName = "B.txt";
		String BText = "This is B.";
		createFile(BFileName, BText);
		gitlet("add", BFileName);
		gitlet("commit", "Added B.txt");

		String CFileName = "C.txt";
		String CText = "This is C.";
		createFile(CFileName, CText);
		gitlet("add", CFileName);
		gitlet("commit", "Added C.txt");

		// 3 commits in log before reset
		gitlet("log");

		gitlet("reset", "1");

		// 1 commit in log after reset
		gitlet("log");

		gitlet("branch", "NewBranch");
		gitlet("checkout", "NewBranch");
		String DFileName = "D.txt";
		String DText = "This is D.";
		createFile(DFileName, DText);
		gitlet("add", DFileName);
		gitlet("commit", "AddedD to NewBranch");

		// commit 4 should inherit files only from commit 1 ie A.txt + new D.txt
		gitlet("myFiles");

		String EFileName = "E.txt";
		String EText = "This is E.";
		createFile(EFileName, EText);
		gitlet("add", EFileName);
		gitlet("commit", "AddedE to NewBranch");

		gitlet("branch", "AnotherBranch");
		gitlet("checkout", "AnotherBranch");
		String FFileName = "F.txt";
		String FText = "This is F.";
		createFile(FFileName, FText);
		gitlet("add", FFileName);
		gitlet("commit", "AddedF to AnotherBranch");

		// commit 6 should have commit 1, 4, 5, and 6's files, skip 2 and 3
		gitlet("myFiles");

		// before reset: 6 commits
		gitlet("log");

		gitlet("reset", "3");

		// looks at commit3's files: A.txt, B.txt, C.txt
		gitlet("myFiles");

		// after reset: 3 commits
		gitlet("log");

	}

	@Test
	public void testRmBranchM() {
		gitlet("init");

		// master -->commit1
		String tugFileName = TESTING_DIR + "tug.txt";
		createFile(tugFileName, "This is a tug");
		gitlet("add", tugFileName);
		gitlet("commit", "Added tug");
		gitlet("myFiles");
		gitlet("status");

		// create branch2 -->commit2
		gitlet("branch", "Branch2");
		gitlet("checkout", "Branch2");
		String yugFileName = TESTING_DIR + "yug.txt";
		createFile(yugFileName, "This is a gug");
		gitlet("add", yugFileName);
		gitlet("status");
		gitlet("commit", "Added yug to branch2's head");
		gitlet("status");

		// can't delete branch you're on, err
		gitlet("rm-branch", "Branch 2");
		gitlet("status"); // branch2 still there

		// create and checkout Branch3
		gitlet("branch", "Branch3");
		gitlet("checkout", "Branch3");

		// branch3, create commit3
		String slugFileName = TESTING_DIR + "slug.txt";
		createFile(slugFileName, "This is a slug");
		gitlet("add", slugFileName);
		gitlet("commit", slugFileName);
		gitlet("status");

		// delete branch 3 -->commit 3 still exists;
		gitlet("checkout", "Branch2");
		gitlet("rm-branch", "Branch3");
		gitlet("status");
		String commit3File = ".gitlet/commits/commit3/" + TESTING_DIR
				+ "slug.txt";
		File commit3Path = new File(commit3File);
		Path checkCommit3 = Paths.get(commit3File);
		assertTrue(Files.exists(checkCommit3));

		// delete master -->commit 1 still exists
		gitlet("rm-branch", "master");
		gitlet("status");
		String commit1File = ".gitlet/commits/commit1/" + TESTING_DIR
				+ "tug.txt";
		File commit1Path = new File(commit1File);
		Path checkCommit1 = Paths.get(commit1File);
		assertTrue(Files.exists(checkCommit1));

		// commit3 with slug and commit 1 with tug still exist
		printDirectories();

	}
}