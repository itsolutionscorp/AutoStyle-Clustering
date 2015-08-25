import static org.junit.Assert.*;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that provides JUnit tests for Gitlet.
 * 
 * Helper methods and basic tests taken from GitletTest.java file, 
 * written by Joseph Moghadam and provided in CS 61BL Project 2 description document. 
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
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	@Test
	public void testBasicLog() {
		gitlet("init");
		String commitMessage1 = "Initial commit."; // save the initial commit message for 
													// final test

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText); // create file wug.txt
		gitlet("add", wugFileName); // add wug.txt 
		String commitMessage2 = "added wug"; 
		gitlet("commit", commitMessage2); // commit wug.txt with above message

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}
	
	/** 
	 * Begin our tests.
	 * Written by Jessica Amezcua, Tim Bloch, Vinay Satish, and James Young.
	 */
	
	/**
	 * Code for saving print statement output. Adapted from StackOverflow post:
	 * http://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	/**
	 * Basic test for add.
	 */
	
	@Test 
	public void testBasicAdd() {
		gitlet("init");
		
		String addFileName = TESTING_DIR + "add.txt";
		String addText = "Add me!";
		createFile(addFileName, addText); // create file add.txt
		gitlet("add"); // attempt to add add.txt
		
		String addResult = outContent.toString().replaceAll(LINE_SEPARATOR, "");
		assertEquals("java Gitlet add [file name]", 
				addResult); // test case where no file name given
		
		gitlet("add", addText); // should work properly, without error
	}
	
	/**
	 * Ensures that we cannot commit if no changes have been made to the added file(s).
	 */
	
	@Test
	public void testUnchangedCommit() {
		gitlet("init");
		
		String addFileName = TESTING_DIR + "add.txt";
		String addText = "Add me!";
		createFile(addFileName, addText);
		gitlet("add", addFileName);
		gitlet("commit", "Added and committed."); 
		
		gitlet("commit", "Nothing has changed, but let's commit anyways.");
		
		String addResult = outContent.toString().replaceAll(LINE_SEPARATOR, "");
		assertEquals("No changes to commit.", addResult); 
	}
	
	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. 
	 *
	 * Accounts for first required end-to-end test.
	 */
	
	@Test
	public void testBasicCheckout() {
		gitlet("init");
		
		String commitFileName = TESTING_DIR + "commit.txt";
		String commitText = "We want to try and checkout this commit.";
		createFile(commitFileName, commitText);
		gitlet("add", commitFileName);
		gitlet("commit", "Committed test file.");
		
		writeFile(commitFileName, "Test file edited. Replace with committed version.");
		
		gitlet("checkout", commitFileName);
		assertEquals(commitText, getText(commitFileName));
	}
	
	/**
	 * Tests that a file that has been committed before can be restored by checking it out
	 * from a commit that tracks that version of the file, even if the file wasn't staged
	 * prior to that commit.
	 *
	 * Accounts for second required end-to-end test.
	 */
	
	@Test
	public void testTrackedCheckout() {
		gitlet("init");
		
		String onceFileName = TESTING_DIR + "once.txt";
		String onceText = "We will add this once but never again.";
		String changeFileName = TESTING_DIR + "change.txt";
		String changeText = "We will modify and add this more than once.";
		createFile(onceFileName, onceText);
		createFile(changeFileName, changeText);
		gitlet("add", onceFileName); // once.txt is now tracked
		gitlet("add", changeFileName); 
		gitlet("commit", "Committed two files."); // add and commit both files
		
		writeFile(changeFileName, "We will modify this.");
		gitlet("commit", "change.txt was changed, but not once.txt."); // add only change.txt
		
		writeFile(changeFileName, "We will add this.");
		gitlet("commit", "once.txt remains unchanged."); // add only change.txt
		
		writeFile(onceFileName, "Changed once.txt. But not for long..."); // modify once.txt without adding
		
		gitlet("checkout", onceFileName);
		assertEquals(onceText, getText(onceFileName));
	}

	/**
	 * Tests that a file can be checked out from an arbitrary commit (specified by commit
	 * ID and file name), from any branch.
	 *
	 * Accounts for third required end-to-end test.
	 */
	
	@Test
	public void testArbitraryCheckout() {
		gitlet("init");
		
		String numFileName = TESTING_DIR + "num.txt";
		String numText = "1 2 3 4 -- num.txt is on the floor!";
		createFile(numFileName, numText);
		gitlet("add", numFileName);
		gitlet("commit", "Committed num.txt");
		
		writeFile(numFileName, "1 2 3 4 5");
		gitlet("add", numFileName);
		gitlet("commit", "Added 5!");
		
		gitlet("branch", "countdown"); // create new branch
		
		gitlet("checkout", "countdown"); // checkout to new branch in order to separate from master
		
		writeFile(numFileName, "5 4 3 2 1");
		String branchText = getText(numFileName);
		gitlet("add", numFileName);
		gitlet("commit", "Countdown...");
		
		gitlet("checkout", "master");
		
		writeFile(numFileName, "1 2 3 4 5 6");
		gitlet("add", numFileName);
		gitlet("commit", "I'd rather keep going up.");
		
		writeFile(numFileName, "1 2 3 4 5 6 7");
		gitlet("add", numFileName);
		gitlet("commit", "Keep going!");
		
		gitlet("checkout", "3", numFileName); // checkout to the version of the file in the countdown branch
		assertEquals(branchText, getText(numFileName));
	}

	/**
	 * Tests that resetting backward appropriately changes the output of log.
	 * 
	 * Accounts for fourth required end-to-end test.
	 */
	
	@Test
	public void testlogAfterReset() {
		gitlet("init");
		String initialCom = "Initial commit."; // id is 0
		
		String dogFile = TESTING_DIR + "dog.txt";
		String dogText = "Bark!";
		createFile(dogFile, dogText);
		gitlet("add", dogFile);
		String comMsg1 = "Woof!";
		gitlet("commit", comMsg1); // id is 1
		
		writeFile(dogFile, "I hate cats.");
		gitlet("add", dogFile);
		String comMsg2 = "Dogs hate cats.";
		gitlet("commit", comMsg2); // id is 2
		
		writeFile(dogFile, "Give me treats.");
		gitlet("add", dogFile);
		String comMsg3 = "Feed all the dogs.";
		gitlet("commit", comMsg3); //id is 3
		
		String dogLog = gitlet("log");
		assertArrayEquals(new String[] { comMsg3, comMsg2, comMsg1, initialCom }, extractCommitMessages(dogLog)); //Before reset
		
		gitlet("reset", "1");
		
		dogLog = gitlet("log");
		assertArrayEquals(new String[] { comMsg1, initialCom }, extractCommitMessages(dogLog)); //After reset	
	}
	
	/**
	 * Tests that what the log command displays changes based on what branch we are
	 * currently at.
	 *
	 * Accounts for fifth required end-to-end test.
	 */
	
	@Test
	public void testLogAdjust() {
		gitlet("init");
		String commitMessage1 = "Initial commit."; // save commit messages for purposes of final test
		
		String greekFileName = TESTING_DIR + "greek.txt";
		String greekText = "Sigma!";
		createFile(greekFileName, greekText);
		gitlet("add", greekFileName);
		String commitMessage2 = "Starts with Sigma..."; 
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "sorority"); // create new branch
		
		writeFile(greekFileName, "Sigma! Mu!");
		gitlet("add", greekFileName);
		String fratMessage1 = "Then...";
		gitlet("commit", fratMessage1);
		
		writeFile(greekFileName, "Sigma! Mu! Delta!");
		gitlet("add", greekFileName);
		String fratMessage2 = "Finally!";
		gitlet("commit", fratMessage2);
		
		gitlet("checkout", "sorority"); // change to sorority branch
		
		writeFile(greekFileName, "Sigma! Omnicron!");
		gitlet("add", greekFileName);
		String sorMessage1 = "Actually...";
		gitlet("commit", sorMessage1);
		
		writeFile(greekFileName, "Sigma! Omnicron! Pi!");
		gitlet("add", greekFileName);
		String sorMessage2 = "Done!";
		gitlet("commit", sorMessage2);
		
		String sororityLog = gitlet("log"); // load sorority branch log
		assertArrayEquals(new String[] { sorMessage2, sorMessage1, commitMessage2, 
				commitMessage1 }, extractCommitMessages(sororityLog));
		
		gitlet("checkout", "master"); // change back to master branch
		
		String fratLog = gitlet("log"); // load frat log, from master branch
		assertArrayEquals(new String[] { fratMessage2, fratMessage1, commitMessage2, 
				commitMessage1 }, extractCommitMessages(fratLog));
	}

	/**
	 * Tests that merge will generate a .conflicted file if a file has been modified in both branches since the split point.
	 * 
	 *  Accounts for the sixth required end-to-end test.
	 */
	
	@Test
	public void testConflictedMerge() {
		gitlet("init");
		String initialCom = "Initial commit.";
		
		String animalFile = TESTING_DIR + "cat.txt";
		String catText = "Meow";
		createFile(animalFile, catText);
		gitlet("add", animalFile);
		String comMsg1 = "I'm a soft little kitty.";
		gitlet("commit", comMsg1); 
		
		writeFile(animalFile, "Purr");
		gitlet("add", animalFile);
		String comMsg2 = "I am plotting world domination.";
		gitlet("commit", comMsg2);
		
		gitlet("branch", "puppies"); //create new branch
		
		writeFile(animalFile, "Mew mew");
		gitlet("add", animalFile);
		String catMsg3 = "I will destroy you all.";
		gitlet("commit", catMsg3);
		
		writeFile(animalFile, "Meowww");
		gitlet("add", animalFile);
		String catMsg4 = "Prepare for your demise.";
		gitlet("commit", catMsg4);
		
		gitlet("checkout", "puppies"); //change to puppies branch
		
		writeFile(animalFile, "Woof!");
		gitlet("add", animalFile);
		String dogMsg1 = "I love all humans.";
		gitlet("commit", dogMsg1);
		
		writeFile(animalFile, "Ruff!");
		gitlet("add", animalFile);
		String dogMsg2 = "Give me love and treats.";
		
		gitlet("merge", "master");
		String conflicted = outContent.toString().replaceAll(LINE_SEPARATOR, "");
		assertEquals("Encountered a merge conflict.", conflicted);
		//Not sure if this will work, or how to make it work:
		//assertTrue(animalFile.conflicted.exists());
	}
	
	/**
	 * Tests that merge will commit with files from the other branch if those files had been modified in the other branch but not in the current branch since the split point.
	 *
	 * Accounts for the seventh required end-to-end case.
	 */
	@Test
	public void testMerge() {
		gitlet("init");
		String initialCom = "Initial commit.";

		String penguinFile = TESTING_DIR + "penguin.txt";
		String penguinText = "Penguin!";
		String polarBearFile = TESTING_DIR + "polarBear.txt";
		String polarText = "Polar bear!";
		createFile(penguinFile, penguinText);
		gitlet("add", penguinFile);
		gitlet("add", polarBearFile);
		String comMsg1 = "Penguin and polar bear.";
		gitlet("commit", comMsg1);

		writeFile(penguinFile, "I'm a penguin!!");
		gitlet("add", penguinFile);
		String comMsg2 = "Second commit";
		gitlet("commit", comMsg2);

		gitlet("branch", "branch2");
		gitlet("checkout", "branch2");

		String change = "I'm a polar bear!";
		writeFile(polarBearFile, change);
		gitlet("add", polarBearFile);
		String comMsg3 = "I love cold weather!";
		gitlet("commit", comMsg3);

		gitlet("merge", "master");
		String polarBear = getText(polarBearFile);
		assertEquals(polarBear, change);
	}
	
	/**
	 * Tests that the output of a log after a rebase includes the commit messages from both branches involved in the rebase.
	 * 
	 * Accounts for the eighth required end-to-end case.
	 */
	
	@Test
	public void testLogRebase() {
		gitlet("init");
		String initialCom = "Initial commit.";

		String animalFile = TESTING_DIR + "cat2.txt";
		String catText = "Meow";
		createFile(animalFile, catText);
		gitlet("add", animalFile);
		String comMsg1 = "I'm a soft little kitty.";
		
		gitlet("commit", comMsg1); 
		writeFile(animalFile, "Purr");
		gitlet("add", animalFile);
		String comMsg2 = "I am plotting world domination.";
		gitlet("commit", comMsg2);
		
		gitlet("branch", "puppies"); //create new branch
		
		writeFile(animalFile, "Mew mew");
		gitlet("add", animalFile);
		String catMsg3 = "I will destroy you all.";
		gitlet("commit", catMsg3);
		
		writeFile(animalFile, "Meowww");
		gitlet("add", animalFile);
		String catMsg4 = "Prepare for your demise.";
		gitlet("commit", catMsg4);
		
		gitlet("checkout", "puppies"); //change to puppies branch
		
		writeFile(animalFile, "Woof!");
		gitlet("add", animalFile);
		String dogMsg1 = "I love all humans.";
		gitlet("commit", dogMsg1);
		
		writeFile(animalFile, "Ruff!");
		gitlet("add", animalFile);
		String dogMsg2 = "Give me love and treats.";
		gitlet("commit", dogMsg2);
		
		gitlet("rebase", "master");
		String animalLog = gitlet("log");
		assertArrayEquals(new String[] { dogMsg2, dogMsg1, catMsg4, catMsg3, comMsg2, comMsg1, initialCom }, extractCommitMessages(animalLog));
	}

	/**
	 * Tests that changes in the base branch propagate through the replayed branch during a rebase.
	 * 
	 * Accounts for the ninth required end-to-end case.
	 */
	
	@Test
	public void testRebaseChanges() {
		gitlet("init");
		String initialCom = "Initial commit.";
		
		String animalFile = TESTING_DIR + "cat3.txt";
		String catText = "Meow";
		createFile(animalFile, catText);
		gitlet("add", animalFile);
		String comMsg1 = "I'm a soft little kitty.";
		gitlet("commit", comMsg1); 
		
		writeFile(animalFile, "Purr");
		gitlet("add", animalFile);
		String comMsg2 = "I am plotting world domination.";
		gitlet("commit", comMsg2);
		
		gitlet("branch", "puppies"); //create new branch
		
		writeFile(animalFile, "Mew mew");
		gitlet("add", animalFile);
		String catMsg3 = "I will destroy you all.";
		gitlet("commit", catMsg3);
		
		writeFile(animalFile, "Meowww");
		gitlet("add", animalFile);
		String catMsg4 = "Prepare for your demise.";
		gitlet("commit", catMsg4);
		
		gitlet("checkout", "puppies"); //change to puppies branch

		String petFile = TESTING_DIR + "dog.txt";
		String petText = "Woof!";
		createFile(petFile, petText); // new file created
		gitlet("add", petFile);
		String dogMsg1 = "I love all humans.";
		gitlet("commit", dogMsg1); // new file committed along with unedited old file
		
		gitlet("rebase", "master");
		String fileText = getText(animalFile);
		assertEquals("Meowww", fileText);
	}
	
	/**
	 * Helper methods continued. 
	 */
	
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