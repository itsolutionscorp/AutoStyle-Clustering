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
		gitlet("add", wugFileName);
		gitlet("commit", "added wug2");
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
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
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

	/****************************
	 * Gitlet Tests***************************************
	 */

	public void testInit() {
		gitlet("init");
		gitlet("init");

	}

	public void testAddLog() {
		// adds parthFileName and chuckyFileName to the staged folder
		String parthFileName = TESTING_DIR + "parth.txt";
		String parthText = "Parth smells funny.";
		createFile(parthFileName, parthText);
		String chuckyFileName = TESTING_DIR + "chucky.txt";
		String chuckyText = "Chucky smells like flowers";
		createFile(chuckyFileName, chuckyText);
		gitlet("add", parthFileName);
		gitlet("add", chuckyFileName);
		gitlet("commit", "losers");
		// checks if the files have been commited with the following message
		gitlet("log");

		// adds lyndaFileName to the staged folder and commits it
		String lyndaFileName = TESTING_DIR + "lynda.txt";
		String lyndaText = "Lynda smells amazing.";
		createFile(lyndaFileName, lyndaText);
		gitlet("add", lyndaFileName);
		gitlet("commit", "beautiful");

		String jennFileName = TESTING_DIR + "jenn.txt";
		String jennText = "Jennifer smells like chocolate.";
		createFile(jennFileName, jennText);
		gitlet("add", jennFileName);
		gitlet("commit", "dresses");
		// checks the new log to check if the files have been commited

		gitlet("branch", "pizza");
		gitlet("checkout", "pizza");
		String alexFileName = TESTING_DIR + "alex.txt";
		String alexText = "Alex smells like fart";
		createFile(alexFileName, alexText);
		gitlet("add", alexFileName);
		gitlet("commit", "ewwwww");
		// checks global log
		gitlet("global-log");
	}

	// Test that a file that has been committed at some point can be restored by
	// checking it
	// out from the commit it was committed at.
	@Test
	public void testCommitIdCheckout() {
		String pigFileName = TESTING_DIR + "pig.txt";
		String pigText = "The pig goes oink oink.";
		createFile(pigFileName, pigText);
		gitlet("init");
		gitlet("add", pigFileName);
		gitlet("commit", "oink");
		writeFile(pigFileName, "Wilber was a pig.");
		gitlet("add", pigFileName);
		gitlet("commit", "oink oink");
		writeFile(pigFileName, "Curly Tail");
		gitlet("add", pigFileName);
		gitlet("commit", "oink oink oink");
		gitlet("checkout", "3", pigFileName);
		assertEquals("Curly Tail", getText(pigFileName));
		gitlet("checkout", "1", pigFileName);
		assertEquals("The pig goes oink oink.", getText(pigFileName));
	}

	// Test that a file that has been committed at some point can be restored
	// by checking it out from a commit that tracks that version of the file,
	// even if the file wasn't staged prior to that commit.
	@Test
	public void testTracking() {
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "Dog says woof.";
		createFile(dogFileName, dogText);
		createFile(TESTING_DIR + "hi.txt", "hello my good friend");
		createFile(TESTING_DIR + "bye.txt", "bye");
		gitlet("init");
		gitlet("add", dogFileName);
		gitlet("commit", "woof");
		writeFile(dogFileName, "Whose a good boy?");
		gitlet("add", TESTING_DIR + "hi.txt");
		gitlet("commit", "woof woof");
		gitlet("add", TESTING_DIR + "bye.txt");
		gitlet("commit", "woof woof woof");
		gitlet("checkout", "3", dogFileName);
		assertEquals("Dog says woof.", getText(dogFileName));
	}

	// Test that you can checkout [id] [file] from an arbitrary commit in the
	// graph (even one in another branch).
	@Test
	public void testCommitIdCheckoutNewBranch() {
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "Cat says meow.";
		createFile(catFileName, catText);
		String mouseFileName = TESTING_DIR + "mouse.txt";
		String mouseText = "Mouse says squeak";
		createFile(mouseFileName, mouseText);
		gitlet("init");
		gitlet("add", catFileName);
		gitlet("commit", "meow");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", mouseFileName);
		gitlet("commit", "squeak");
		gitlet("checkout", "1", catFileName);
		assertEquals(catText, getText(catFileName));
	}

	// Test that resetting backward appropriately changes the output of log.
	@Test
	public void testResetLog() {
		String foxFileName = TESTING_DIR + "fox.txt";
		String foxText = "What does the fox say?";
		createFile(foxFileName, foxText);
		gitlet("init");
		gitlet("add", foxFileName);
		gitlet("commit", "No.");
		writeFile(foxFileName, "The fox is annoying.");
		gitlet("add", foxFileName);
		gitlet("commit", "No. No.");
		writeFile(foxFileName, "dingdingdingdingding");
		gitlet("add", foxFileName);
		gitlet("commit", "No. No. No.");
		gitlet("checkout", "3", foxFileName);
		assertEquals("dingdingdingdingding", getText(foxFileName));
		String s = gitlet("log"); // should print out commits 0,1,2,3
		gitlet("reset", "1");
		String d = gitlet("log"); // should print out commits 0,1
		assertArrayEquals(new String[] { "No. No. No.", "No. No.", "No.", "initial commit" }, extractCommitMessages(s));
		assertArrayEquals(new String[] { "No.", "initial commit" }, extractCommitMessages(d));
	}

	// Test that log adjusts appropriately when switching from one branch to
	// another.
	@Test
	public void testBranchLogs() {
		String penguinFileName = TESTING_DIR + "penguin.txt";
		String penguinText = "How do penguins sound?";
		createFile(penguinFileName, penguinText);
		gitlet("init");
		gitlet("add", penguinFileName);
		gitlet("commit", "Penguin noise");
		writeFile(penguinFileName, "Penguins are cute.");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", penguinFileName);
		gitlet("commit", "Penguin noises");
		writeFile(penguinFileName, "Tuxitos");
		gitlet("add", penguinFileName);
		gitlet("commit", "All the penguin noises");
		gitlet("checkout", "3", penguinFileName);
		assertEquals("Tuxitos", getText(penguinFileName));
		String s = gitlet("log"); // should print out commits 0,1,2,3
		gitlet("checkout", "master");
		String d = gitlet("log"); // should print out commits 0,1
		assertArrayEquals(
				new String[] { "All the penguin noises", "Penguin noises", "Penguin noise", "initial commit" },
				extractCommitMessages(s));
		assertArrayEquals(new String[] { "Penguin noise", "initial commit" }, extractCommitMessages(d));
	}

	// Test that merge will generate a .conflicted file if a file has been
	// modified
	// in both branches since the split point.
	@Test
	public void testMergeConflict() {
		String strawberryFileName = TESTING_DIR + "strawberry.txt";
		String strawberryText = "Strawberries are in season";
		createFile(strawberryFileName, strawberryText);
		gitlet("init");
		gitlet("add", strawberryFileName);
		gitlet("commit", "sweet");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		writeFile(strawberryFileName, "Strawberries are good.");
		gitlet("add", strawberryFileName);
		gitlet("commit", "super sweet");
		gitlet("checkout", "master");
		writeFile(strawberryFileName, "They are red");
		gitlet("add", strawberryFileName);
		gitlet("commit", "super good tasting");
		gitlet("merge", "branch1");
		File f2 = new File(TESTING_DIR + "strawberry.txt.conflicted");
		assertTrue(f2.exists());
	}

	// Test that merge will commit with files from the other branch if those
	// files
	// had been modified in the other branch but not in the current branch since
	// the split point.
	@Test
	public void testMergeNoConflict() {
		String velociraptorFileName = TESTING_DIR + "velociraptor.txt";
		String velociraptorText = "Velociraptors will eat you";
		createFile(velociraptorFileName, velociraptorText);
		gitlet("init");
		gitlet("add", velociraptorFileName);
		gitlet("commit", "rawr");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("commit", "rawr rawr");
		gitlet("checkout", "master");
		writeFile(velociraptorFileName, "Jurassic Parth");
		gitlet("add", velociraptorFileName);
		gitlet("commit", "rawr rawr rawr");
		gitlet("checkout", "branch1");
		gitlet("merge", "master");
		gitlet("checkout", "master");
		assertEquals("Jurassic Parth", getText(velociraptorFileName));
	}

	// Test that the output of log after a rebase includes the commit messages
	// from both branches involved in the rebase.
	@Test
	public void testRebaseLog() {
		String cowFileName = TESTING_DIR + "cow.txt";
		String cowText = "Cows go moo.";
		createFile(cowFileName, cowText);
		gitlet("init");
		gitlet("add", cowFileName);
		gitlet("commit", "moo"); // commit 1
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", cowFileName);
		gitlet("commit", "moo moo"); // commit 2
		gitlet("checkout", "master");
		writeFile(cowFileName, "Moo Moo was a happy cow");
		gitlet("add", cowFileName);
		gitlet("commit", "moo moo moo"); // commit 3
		String d = gitlet("log"); // prints out 0,1,3
		gitlet("checkout", "branch1");
		gitlet("rebase", "master");
		String s = gitlet("log"); // prints out 0,1,3,4
		System.out.println(d);
		assertArrayEquals(new String[] { "moo moo", "moo moo moo", "moo", "initial commit" }, extractCommitMessages(s));
		assertArrayEquals(new String[] { "moo moo moo", "moo", "initial commit" }, extractCommitMessages(d));
	}
	
	// Test that changes in the base branch propagate through the replayed
	// branch during a rebase, reset, and a merge.
	@Test
	public void testMergeRebaseReset(){
		String test1 = TESTING_DIR + "test1", test2 = TESTING_DIR + "test2", test3 = TESTING_DIR + "test3", test4 = TESTING_DIR + "test4", test5 = TESTING_DIR + "test5";
		createFile(test1, "1");
		createFile(test2, "2");
		createFile(test3, "3");
		createFile(test4, "4");
		createFile(test5, "5");
		gitlet("init");
		gitlet("branch", "branch1");
		gitlet("branch", "branch2");
		gitlet("add", test1);
		gitlet("commit", "test1");
		gitlet("add", test2);
		gitlet("commit", "test2");
		gitlet("checkout", "branch1");
		gitlet("add", test3);
		gitlet("commit", "test3");
		gitlet("add", test4);
		gitlet("commit", "test4");
		gitlet("checkout", "branch2");
		gitlet("add", test5);
		gitlet("commit", "test5");
		writeFile(test1, "test1");
		gitlet("add", test1);
		gitlet("commit", "test1-2");
		gitlet("checkout", "master");
		gitlet("merge", "branch2");
		gitlet("add", test1);
		gitlet("commit", "test1-3-7");
		String s = gitlet("log");
		gitlet("status");
		assertArrayEquals(new String[] { "test1-3-7", "test2", "test1", "initial commit" }, extractCommitMessages(s));
		gitlet("reset", "2");
		gitlet("checkout", "branch1");
		gitlet("rebase", "master");
		String d = gitlet("log");
		gitlet("status");
		assertArrayEquals(new String[] { "test4", "test3", "test2", "test1", "initial commit" }, extractCommitMessages(d));
	}

}