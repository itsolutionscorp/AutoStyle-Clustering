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

import org.junit.Before;
import org.junit.Test;

public class GitletTest {
	private static final String GITLET_DIR = ".gitlet/";
	private static final String TESTING_DIR = "test/";

	/* matches either unix/mac or windows line separators */
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

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

	private static void createFolder(String folderName) {
		File f = new File(folderName);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	@Test
	public void testInitialize() {
		gitlet("init");
		File git = new File(GITLET_DIR);
		assertTrue(git.exists());
		File stage = new File(GITLET_DIR + ".stagingArea");
		assertTrue(stage.exists());
		File commits = new File(GITLET_DIR + "commits");
		assertTrue(commits.exists());
		File initialCommit = new File(GITLET_DIR + "commits/0");
		assertTrue(initialCommit.exists());
	}

	@Test
	public void testAddandCommit() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		String wugName = TESTING_DIR + "wug.txt";
		String wugText = "im a wug";
		createFile(dogName, dogText);
		createFile(wugName, wugText);
		gitlet("init");
		gitlet("add", dogName);
		File dog = new File(".gitlet/.stagingArea/test/dog.txt");
		assertTrue(dog.exists());
		gitlet("commit", "dog");
		assertFalse(dog.exists());
		File realDog = new File(".gitlet/commits/1/test/dog.txt");
		assertTrue(realDog.exists());
		File testWug = new File(".gitlet/.stagingArea/test/wug.txt");
		gitlet("add", "test/wug.txt");
		assertTrue(testWug.exists());
	}

	@Test
	public void remove() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("rm", wugFileName);
		// wug should be untracked
		gitlet("status");
	}

	@Test
	public void log() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "b1");
		writeFile(dogName, "im a cat");
		gitlet("add", dogName);
		gitlet("commit", "added cat");
		writeFile(dogName, "im a dog again");
		gitlet("add", dogName);
		gitlet("commit", "im a dog now");
		gitlet("log");
	}

	@Test
	public void logwithcheckout() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "b1");
		writeFile(dogName, "im a cat");
		gitlet("add", dogName);
		gitlet("commit", "added cat");
		gitlet("checkout", "b1");
		writeFile(dogName, "im a dog again");
		gitlet("add", dogName);
		gitlet("commit", "im a dog now");
		writeFile(dogName, "im a cat again");
		gitlet("add", dogName);
		gitlet("commit", "im a cat now");
		// Should be 4 3 1 0
		gitlet("log");
	}

	@Test
	public void globallog() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "b1");
		writeFile(dogName, "im a cat");
		gitlet("add", dogName);
		gitlet("commit", "added cat");
		gitlet("checkout", "b1");
		writeFile(dogName, "im a dog again");
		gitlet("add", dogName);
		gitlet("commit", "im a dog now");
		writeFile(dogName, "im a cat again");
		gitlet("add", dogName);
		gitlet("commit", "im a cat now");
		// Should be 4 3 2 1 0 (or all)
		gitlet("global-log");
	}

	@Test
	public void testFind() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "b1");
		writeFile(dogName, "im a cat");
		gitlet("add", dogName);
		gitlet("commit", "added cat");
		writeFile(dogName, "im a dog");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("find", "added dog");
		// should print commit 3 and 1
	}

	@Test
	public void Status() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "b1");
		writeFile(dogName, "im a cat");
		gitlet("add", dogName);
		gitlet("commit", "added cat");
		gitlet("checkout", "b1");
		writeFile(dogName, "im a dog again");
		gitlet("add", dogName);
		gitlet("commit", "im a dog now");
		writeFile(dogName, "im a cat again");
		gitlet("add", dogName);
		gitlet("status");
		// b1 is current branch, with test/dog.txt as staged
	}

	@Test
	public void testBranch() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "branch1");
		writeFile(dogName, "i like people");
		gitlet("add", dogName);
		gitlet("commit", "dog people");
		gitlet("status");
		// Master and branch1
	}

	@Test
	public void testRemoveBranch() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		gitlet("branch", "branch1");
		writeFile(dogName, "i like people");
		gitlet("add", dogName);
		gitlet("commit", "dog people");
		gitlet("rm-branch", "branch1");
		gitlet("status");
		// only have Master
	}

	@Test
	public void testReset() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "im a dog";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "added dog");
		writeFile(dogName, "whooo dog");
		gitlet("add", dogName);
		gitlet("commit", "whooo");
		writeFile(dogName, "whooo cat");
		gitlet("add", dogName);
		gitlet("commit", "cat");
		gitlet("reset", "1");
		assertEquals(dogText, getText(dogName));
		gitlet("reset", "2");
		assertEquals("whooo dog", getText(dogName));
	}

	@Test
	public void testCheckoutBranch() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "dog 1.0";
		createFile(dogName, dogText);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("commit", "dog1");
		writeFile(dogName, "dogdogdog");
		String fileText = GitletTest.getText(dogName);
		assertEquals("dogdogdog", fileText);
		gitlet("branch", "dog1.0");
		gitlet("add", dogName);
		gitlet("commit", "dogdogdog");
		gitlet("checkout", "dog1.0");
		fileText = getText(dogName);
		assertEquals("dog 1.0", fileText);
	}

	@Test
	public void testCheckoutID1() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "dog 1.0";
		gitlet("init");
		createFile(dogName, dogText);
		String fileText = GitletTest.getText(dogName);
		assertEquals("dog 1.0", fileText);
		gitlet("add", dogName);
		gitlet("commit", "dog1");
		writeFile(dogName, "dogdogdog");
		fileText = getText(dogName);
		assertEquals("dogdogdog", fileText);
		gitlet("add", dogName);
		gitlet("commit", "dog3x");
		gitlet("checkout", "1", dogName);
		fileText = getText(dogName);
		assertEquals("dog 1.0", fileText);
	}

	@Test
	public void testCheckoutID2() {
		createFolder(TESTING_DIR + "nest1/nest2/nest3/nest4");
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "dog 1.0";
		String wugName = TESTING_DIR + "nest1/nest2/nest3/nest4/wug.txt";
		String wugText = "wug";
		String poopName = TESTING_DIR + "nest1/nest2/poop.txt";
		String poopText = "poop";
		createFile(dogName, dogText);
		createFile(wugName, wugText);
		createFile(poopName, poopText);
		gitlet("init");
		String fileText = getText(dogName);
		assertEquals("dog 1.0", fileText);
		gitlet("add", dogName);
		gitlet("add", poopName);
		gitlet("add", wugName);
		gitlet("commit", "dog1 & nested wug");
		writeFile(dogName, "dogdogdog");
		writeFile(poopName, "super poop");
		writeFile(wugName, "wug 10000");
		fileText = getText(dogName);
		assertEquals("dogdogdog", fileText);
		gitlet("add", dogName);
		gitlet("add", poopName);
		gitlet("add", wugName);
		gitlet("commit", "dog3x & wug 10000 & super poop");
		gitlet("checkout", "1", wugName);
		fileText = getText(wugName);
		assertEquals("wug", fileText);
		gitlet("checkout", "2", wugName);
		fileText = getText(wugName);
		assertEquals("wug 10000", fileText);
		gitlet("checkout", "1", poopName);
		assertEquals("poop", getText(poopName));
		gitlet("checkout", "2", poopName);
		assertEquals("super poop", getText(poopName));
	}

	@Test
	public void testNestCheckout() {
		createFolder(TESTING_DIR + "nest1/nest2/nest3/nest4");
		String aName = TESTING_DIR + "a.txt";
		String aText = "1";
		String a4Name = TESTING_DIR + "nest1/nest2/nest3/nest4/a.txt";
		String a4Text = "3";
		String a1Name = TESTING_DIR + "nest1/a.txt";
		String a1Text = "2";
		createFile(aName, aText);
		createFile(a1Name, a1Text);
		createFile(a4Name, a4Text);
		gitlet("init");
		gitlet("add", aName);
		gitlet("commit", "first a");
		gitlet("add", a1Name);
		gitlet("add", a4Name);
		gitlet("commit", "nest1 nest 4 a");
		writeFile(aName, "changed no nest");
		writeFile(a1Name, "changed nest 1");
		writeFile(a4Name, "changed nest 4");
		gitlet("add", aName);
		gitlet("add", a1Name);
		gitlet("add", a4Name);
		gitlet("commit", "changed everything");
		gitlet("checkout", "2", aName);
		gitlet("checkout", "2", a1Name);
		gitlet("checkout", "2", a4Name);
		assertEquals("1", getText(aName));
		assertEquals("2", getText(a1Name));
		assertEquals("3", getText(a4Name));
	}

	@Test
	public void testRebase1() {
		String dog1FileName = TESTING_DIR + "dog.txt";
		String dog1Text = "This is a dog";
		createFile(dog1FileName, dog1Text);
		String dog2FileName = TESTING_DIR + "dog2.txt";
		String dog2Text = "This is dog 2.";
		createFile(dog2FileName, dog2Text);
		gitlet("init");
		gitlet("add", dog1FileName);
		gitlet("add", dog2FileName);
		gitlet("commit", "commit 1");
		gitlet("branch", "branch1");
		writeFile(dog1FileName, "this is dog1.");
		gitlet("add", dog1FileName);
		gitlet("commit", "rewrite dog1");
		gitlet("checkout", "branch1");
		writeFile(dog2FileName, "this is dog2");
		gitlet("add", dog2FileName);
		gitlet("commit", "rewrite dog2");
		writeFile(dog2FileName, "wheee");
		gitlet("add", dog2FileName);
		gitlet("commit", "rewrite dog2 2");
		System.out.println(gitlet("rebase", "master"));
		System.out.println(gitlet("log"));
		assertEquals("this is dog1.", getText(dog1FileName));

	}

	@Test
	public void testRebase2() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog 1.";
		createFile(dogName, dogText);
		String dog2Name = TESTING_DIR + "dog2.txt";
		String dog2Text = "This is a dog 2.";
		createFile(dog2Name, dog2Text);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("add", dog2Name);
		gitlet("commit", "commit 1");
		gitlet("branch", "b1");
		writeFile(dogName, "This is a 1.");
		gitlet("add", dogName);
		gitlet("commit", "rewrite dog1");
		gitlet("checkout", "b1");
		writeFile(dog2Name, "This is a 2.");
		gitlet("add", dog2Name);
		gitlet("commit", "rewrite dog2");
		writeFile(dog2Name, "This is a 22.");
		gitlet("add", dog2Name);
		gitlet("commit", "rewrite dog2");
		System.out.println(gitlet("rebase", "master"));
		System.out.println(gitlet("log"));
		gitlet("reset", "6");
		assertEquals("This is a 22.", getText(dog2Name));

	}

	@Test
	public void testRebase3() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog 1.";
		createFile(dogName, dogText);
		String dog2Name = TESTING_DIR + "dog2.txt";
		String dog2Text = "This is a dog 2.";
		createFile(dog2Name, dog2Text);
		gitlet("init");
		gitlet("add", dogName);
		gitlet("add", dog2Name);
		gitlet("commit", "commit 1");
		gitlet("branch", "b1");
		writeFile(dogName, "This is dog.");
		writeFile(dog2Name, "This is dog 2.");
		gitlet("add", dogName);
		gitlet("add", dog2Name);
		gitlet("commit", "rewrite dog1, dog2");
		gitlet("checkout", "b1");
		writeFile(dog2Name, "This is a dog 2a.");
		gitlet("add", dog2Name);
		gitlet("commit", "rewrite dog2");
		writeFile(dog2Name, "This is a dog 22.");
		gitlet("add", dog2Name);
		gitlet("commit", "rewrite dog2");
		gitlet("rebase", "master");
		System.out.println(gitlet("log"));
		assertEquals("This is dog.", getText(dogName));
		assertEquals("This is a dog 22.", getText(dog2Name));
	}

	@Test
	public void testRebase4() {
		String dogName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog 1.";
		createFile(dogName, dogText);
		String duckName = TESTING_DIR + "duck.txt";
		String duckText = "This is a duck 1.";
		createFile(duckName, duckText);
		String catName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat 1.";
		createFile(catName, catText);
		gitlet("init");
		gitlet("branch", "branch1");
		gitlet("add", dogName);
		writeFile(duckName, "This is a test");
		gitlet("add", duckName);
		gitlet("commit", "master 1");
		gitlet("checkout", "branch1");
		gitlet("add", catName);
		writeFile(dogName, "This is the other branch.");
		gitlet("add", dogName);
		gitlet("commit", "other 1");
		gitlet("rebase", "master");
		String dogText1 = getText(duckName);
		assertEquals("This is a test", dogText1);
	}

	
	 @Test
	 public void testRebase5() {
	 String dogName = TESTING_DIR + "dog.txt";
	 String dogText = "This is a wug 1.";
	 createFile(dogName, dogText);
	 String dog2Name = TESTING_DIR + "dog2.txt";
	 String dog2Text = "This is a wug 2.";
	 createFile(dog2Name, dog2Text);
	 gitlet("init");
	 gitlet("add", dogName);
	 gitlet("add", dog2Name);
	 gitlet("commit", "commit 1");
	 gitlet("branch", "branch1");
	 writeFile(dogName, "this is dog 1.");
	 writeFile(dog2Name, "This is lel");
	 gitlet("add", dogName);
	 gitlet("add", dog2Name);
	 gitlet("commit", "commit 2");
	 gitlet("checkout", "branch1");
	 writeFile(dog2Name, "this is dog2.");
	 gitlet("add", dog2Name);
	 gitlet("commit", "commit 3");
	 writeFile(dogName, "this is new dog.");
	 gitlet("add", dogName);
	 gitlet("commit", "rewrite dog1");
	 gitlet("rebase", "master");
	 System.out.println(gitlet("log"));
	 assertEquals("this is new dog.", getText(dogName));
	 assertEquals("this is dog2.", getText(dog2Name));
	 gitlet("checkout", "5", dogName);
	 assertEquals("this is dog 1.", getText(dogName));
	 }
	
	 @Test
	 public void testCheckoutFile(){
	 GitletTest.createFile("test/dog.txt", "original dog");
	 GitletTest.gitlet("init");
	 GitletTest.gitlet("add", "test/dog.txt");
	 GitletTest.gitlet("commit", "dog");
	 GitletTest.writeFile("test/dog.txt", "new dog");
	 GitletTest.gitlet("checkout", "test/dog.txt");
	 assertEquals("original dog", GitletTest.getText("test/dog.txt"));
	 }
	
	 @Test
	 public void testCheckoutFileTracked(){
	 GitletTest.createFile("test/dog.txt", "original dog");
	 GitletTest.createFile("test/wug.txt", "original wug");
	 GitletTest.gitlet("init");
	 GitletTest.gitlet("add", "test/dog.txt");
	 GitletTest.gitlet("commit", "dog");
	 GitletTest.writeFile("test/dog.txt", "new dog");
	 GitletTest.gitlet("add", "test/wug.txt");
	 GitletTest.gitlet("commit", "wug");
	 GitletTest.gitlet("checkout", "test/dog.txt");
	 assertEquals("original dog", GitletTest.getText("test/dog.txt"));
	 }
	
	 @Test
	 public void testCheckoutIDFromOtherBranch(){
	 GitletTest.createFile("test/superdog.txt", "super dawg");
	 GitletTest.createFile("test/master.txt", "mastah");
	 GitletTest.createFile("test/lazydog.txt", "lazy dawg");
	 GitletTest.gitlet("init");
	 GitletTest.gitlet("add", "test/superdog.txt");
	 GitletTest.gitlet("commit", "super dog"); //Commit 1
	 GitletTest.gitlet("branch", "dogbranch");
	 GitletTest.gitlet("add", "test/master.txt");
	 GitletTest.writeFile("test/superdog.txt", "master dog");
	 GitletTest.gitlet("add", "test/superdog.txt");
	 GitletTest.gitlet("commit", "mastah on master branch"); // Commit 2
	 GitletTest.gitlet("checkout", "dogbranch"); // switch to dog branch
	 GitletTest.gitlet("add", "test/lazydog.txt");
	 GitletTest.writeFile("test/superdog.txt", "super dog is back");
	 GitletTest.gitlet("add", "test/superdog.txt");
	 GitletTest.gitlet("commit", "lazy dog"); // Commit 3
	 GitletTest.gitlet("checkout", "2", "test/superdog.txt");
	 assertEquals("master dog", GitletTest.getText("test/superdog.txt"));
	 GitletTest.gitlet("checkout", "test/superdog.txt");
	 assertEquals("super dog is back",
	 GitletTest.getText("test/superdog.txt"));
	 }
	
	 @Test
	 public void testMerge1() {
	 GitletTest.createFile("test/testFile1.txt", "1");
	 GitletTest.createFile("test/testFile2.txt", "2");
	 GitletTest.gitlet("init");
	 GitletTest.gitlet("add", "testFile1.txt");
	 GitletTest.gitlet("add", "testFile2.txt");
	 GitletTest.gitlet("commit", "commit 1"); // first commit, split point
	 GitletTest.gitlet("branch", "b1");
	 GitletTest.writeFile("testFile1.txt", "1 modified"); // testFile1
	 // modified on
	 // master branch
	 GitletTest.gitlet("add", "testFile1.txt");
	 GitletTest.gitlet("commit", "master commit"); // second commit, master
	 GitletTest.gitlet("checkout", "b1");
	 GitletTest.writeFile("testFile2.txt", "2 modified");
	 GitletTest.gitlet("add", "testFile2.txt"); // TestFile2 modified on b1
	 GitletTest.gitlet("commit", "b1 commit"); // third commit, b1 branch
	 GitletTest.gitlet("merge", "master");
	 GitletTest.gitlet("reset", "4"); // reset to new commit made from merge
	 assertEquals("1 modified", GitletTest.getText("testFile1.txt"));
	 assertEquals("2 modified", GitletTest.getText("testFile2.txt"));
	 }

	@Test
	public void testMerge2() {
		createFile("dog.txt", "bow wow");
		gitlet("init");
		gitlet("add", "dog.txt");
		gitlet("commit", "one");
		gitlet("branch", "b1");
		gitlet("checkout", "b1");
		String dogFile = "dog.txt";
		String dogText = "woof woof";
		writeFile(dogFile, dogText);
		gitlet("add", "dog.txt");
		gitlet("commit", "two");
		gitlet("branch", "b2");
		gitlet("checkout", "b2");
		gitlet("reset", "1");
		gitlet("add", "dog.txt");
		gitlet("commit", "three");
		gitlet("merge", "b1");

		File newCommit = new File(".gitlet/commits/4");
		File dog = new File("dog.txt");
		assertTrue(newCommit.exists());
		assertTrue(dog.exists());
		assertEquals("woof woof", getText("dog.txt"));
	}

	@Test
	public void testMerge3() {
		createFile("dog.txt", "woof");
		createFile("cat.txt", "miaou");
		gitlet("init");
		gitlet("add", "dog.txt");
		gitlet("add", "cat.txt");
		gitlet("commit", "one");
		gitlet("branch", "b1");
		gitlet("checkout", "b1");
		String dogFile = "dog.txt";
		String dogText = "woof woof";
		writeFile(dogFile, dogText);
		gitlet("add", "dog.txt");
		gitlet("commit", "two");
		gitlet("branch", "b2");
		gitlet("checkout", "b2");
		gitlet("reset", "1");
		String catFile = "cat.txt";
		String catText = "miaou miaou";
		writeFile(catFile, catText);
		gitlet("add", "cat.txt");
		gitlet("commit", "three");
		gitlet("merge", "b1");

		assertEquals("woof woof", getText("dog.txt"));
		assertEquals("miaou miaou", getText("cat.txt"));
	}

	@Test
	public void testMerge4() {
		createFile("dog.txt", "woof");
		createFile("cat.txt", "miaou");
		gitlet("init");
		gitlet("add", "dog.txt");
		gitlet("add", "cat.txt");
		gitlet("commit", "one");
		gitlet("branch", "b1");
		gitlet("checkout", "b1");
		String dogFile = "dog.txt";
		String dogText = "woof woof";
		writeFile(dogFile, dogText);
		gitlet("add", "dog.txt");
		gitlet("commit", "two");
		gitlet("branch", "b2");
		gitlet("checkout", "b2");
		gitlet("reset", "1");
		gitlet("add", "cat.txt");
		gitlet("commit", "three");
		gitlet("merge", "b1");

		assertEquals("woof woof", getText("dog.txt"));
		assertEquals("miaou", getText("cat.txt"));
	}

	@Test
	public void testMerge5() {
		createFile("dog.txt", "woof");
		createFile("cat.txt", "miaou");
		gitlet("init");
		gitlet("add", "dog.txt");
		gitlet("add", "cat.txt");
		gitlet("commit", "one");
		gitlet("branch", "b1");
		gitlet("checkout", "b1");
		gitlet("add", "dog.txt");
		gitlet("commit", "two");
		gitlet("branch", "b2");
		gitlet("checkout", "b2");
		gitlet("reset", "1");
		String catFile = "cat.txt";
		String catText = "miaou miaou";
		writeFile(catFile, catText);
		gitlet("add", "cat.txt");
		gitlet("commit", "three");
		gitlet("merge", "b1");

		assertEquals("woof", getText("dog.txt"));
		assertEquals("miaou miaou", getText("cat.txt"));
	}
	
	@Test
	public void testMerge6() {
		createFile("dog.txt", "woof");
		gitlet("init");
		gitlet("add", "dog.txt");
		gitlet("commit", "one");
		
		gitlet("branch", "b1");
		gitlet("checkout", "b1");
		String dogFile = "dog.txt";
		String dogText = "woof woof";
		writeFile(dogFile, dogText);
		gitlet("add", "dog.txt");
		gitlet("commit", "two-A");
		
		gitlet("branch", "b2");
		gitlet("checkout", "b2");
		gitlet("reset", "1");
		dogText = "woof woof woof";
		writeFile(dogFile, dogText);
		gitlet("add", "dog.txt");
		gitlet("commit", "three-B");
		
		gitlet("merge", "b1");
		File conflictFile = new File(".gitlet/commits/4");
		File dog = new File("dog.txt.conflicted");
		assertFalse(conflictFile.exists());
		assertTrue(dog.exists());
		
		gitlet("add", "dog.txt.conflicted");
		gitlet("commit", "four");
		assertTrue(conflictFile.exists());
		File conflictedDog = new File("dog.txt.conflicted");
		assertTrue(conflictedDog.exists());
	}
}