	
	
	import static org.junit.Assert.*;

	import org.junit.Test;


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
		@Test
		public void testRebaseHistory() {
					gitlet("init");
					gitlet("add", "a.txt");
					gitlet("commit", "A");
					
					gitlet("branch", "newb"); 
					
					gitlet("add", "b.txt");
					gitlet("commit", "B");
					gitlet("add", "c.txt");
					gitlet("commit", "C");
					
					gitlet("checkout", "newb"); 
					
					gitlet("add", "d.txt");
					gitlet("commit", "E");
					gitlet("add", "e.txt");
					gitlet("commit", "D");
					gitlet("add", "f.txt");
					gitlet("commit", "F");
					gitlet("log");
					gitlet("checkout", "master"); 
					gitlet("log");
					
					System.out.println("***************made structure**************");

					gitlet("rebase", "newb");
					
					gitlet("status");
					gitlet("global-log");
					gitlet("checkout", "master");
					gitlet("rebase", "newb"); //should print error
					
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
		 * Tests that the find command returns the correct  
		 * corresponding commit ID for the given commit message.
		 */
		@Test
		public void testBasicFind() {
			gitlet("init");
			String dogFileName = TESTING_DIR + "dog.txt";
			String dogText = "This is a dog.";
			createFile(dogFileName, dogText);
			gitlet("add", dogFileName);
			gitlet("commit", "added dog");
			writeFile(dogFileName, "This is not a dog.");
			gitlet("add", dogFileName);
			gitlet("commit", "no more dog");
			assertEquals(gitlet("find", "no more dog"), "2");
			assertEquals(gitlet("find", "added dog"), "1");
		}
		
		/**
		 * Test that a file that has been committed at some point can be
		 * restored by checking it out from a commit that tracks that version
		 * of the file, even if the file wasn't staged prior to that commit.
		 * 
		 * First required test.
		 */
		@Test
		public void testFirstCheckout() {
			gitlet("init");
			
			String a = TESTING_DIR + "a.txt";
			String b = TESTING_DIR + "b.txt";
			String a_text = "Aa.";
			createFile(a, a_text);
			createFile(b, a_text);
			gitlet("add", a); // a.txt is now staged
			gitlet("commit", "First commit."); // add and commit file
			
			writeFile(a, "Changed a"); //changes text of the file
			gitlet("add", b);
			gitlet("commit", "Second commit."); // commit changed file
			
			writeFile(a, "Changed a again"); //changed again
			gitlet("add", a); 
			gitlet("commit", "Third commit."); 
			
			gitlet("checkout", "2", a);
			assertEquals(a_text, getText(a));
		}
		
		/**
		 * Tests that a file that has been committed before can be restored by 
		 * checking it out from a commit that tracks that version of the file,
		 * even if the file wasn't staged prior to that commit.
		 *
		 * Second required test.
		 */
		
		@Test
		public void testSecondCheckout() {
			gitlet("init");
			
			String a_file = TESTING_DIR + "a.txt";
			String a_text = "to be added.";
			String new_a = TESTING_DIR + "new_a.txt";
			String new_a_text = "to be changed and added";
			createFile(a_file, a_text);
			createFile(new_a, new_a_text);
			gitlet("add", a_file); // a.txt is now tracked
			gitlet("add", new_a); 
			gitlet("commit", "Committed two files."); // add and commit both files
			writeFile(new_a, "changed a");
			gitlet("commit", "new_a.txt changed, a.txt stayed the same"); // add only a_new.txt
			writeFile(new_a, "changed a again");
			gitlet("commit", "a.txt still the same."); // add only a_new.txt
			writeFile(a_file, "finally changed a.txt"); // modify a.txt without adding
			gitlet("checkout", a_file);
			assertEquals(a_text, getText(a_file));
		}
		
		/** 
		 * Test that you can checkout [id] [file] from an 
		 * arbitrary commit in the graph (even one in another branch).
		 * 
		 * Third required test.
		 */
		@Test
		public void testThirdCheckout() {
			gitlet("init");
			
			String a_file = TESTING_DIR + "a.txt";
			String a_text = "Aa.";
			createFile(a_file, a_text);
			gitlet("add", a_file); // a.txt is now staged
			gitlet("commit", "First commit."); // add and commit file
			
			writeFile(a_file, "Changed a"); //changes text of the file
			gitlet("add", a_file);
			gitlet("commit", "Second commit."); 
			
			String branchName = "Cool-beans";
			gitlet("branch", branchName);
			gitlet("checkout", branchName);
			
			writeFile(a_file, "Changed a again");
			gitlet("add", a_file);
			gitlet("commit", "Third commit."); 
			
			gitlet("checkout", "master");
			
			gitlet("commit");
			gitlet("checkout", "3", a_file);
			assertEquals("Changed a again", getText(a_file));

		}
		/**
		 * Test that resetting backward appropriately changes the output of log.
		 * 
		 * Fourth required test.
		 */
		@Test
		public void testResetBackwards() {
			gitlet("init");
			
			String a_file = TESTING_DIR + "a.txt";
			String a_text = "Aa.";
			createFile(a_file, a_text);
			gitlet("add", a_file); // a.txt is now staged
			gitlet("commit", "First commit."); // add and commit file
			writeFile(a_file, "Changed a"); //changes text of the file
			gitlet("add", a_file);
			gitlet("commit", "Second commit."); 
			String logContent = gitlet("log");
			writeFile(a_file, "Changed a again");
			gitlet("add", a_file);
			gitlet("commit", "Third commit."); 
			writeFile(a_file, "Changed a agaaaain");
			gitlet("add", a_file);
			gitlet("commit", "Fourth commit."); 
			gitlet("reset", "2");
			String afterResetLog = gitlet("log");
			assertTrue(logContent.equals(afterResetLog));
		}
		
		
		/**
		 * Test that log adjusts appropriately when switching from one branch to another.
		 * 
		 * Fifth required test.
		 */
		@Test
		public void testSwitchBranchLog(){
			gitlet("init");
			String a_file = TESTING_DIR + "a.txt";
			String a_text = "A is for apple.";
			createFile(a_file, a_text);
			
			//commits initial file
			gitlet("add", a_file); // a.txt is now staged
			String commitMsg1 = "First commit.";
			gitlet("commit", commitMsg1); // add and commit file

			//new commit
			writeFile(a_file, "A is for alligator"); 
			gitlet("add", a_file);
			String commitMsg2 = "Second commit.";
			gitlet("commit", commitMsg2); 
			
			//switches to new branch cool-beans
			String branchName = "cool-beans";
			gitlet("branch", branchName);
			gitlet("checkout", branchName);
			
			//new commit, the file is in cool-bean
			writeFile(a_file, "A is for air");
			gitlet("add", a_file);
			String commitMsg3 = "Third commit.";
			gitlet("commit", commitMsg3); 
			// it is in the cool bean
			//switches back to master branch
			gitlet("checkout", "master");
			
			//new commit
			writeFile(a_file, "A is for acorn");
			gitlet("add", a_file);
			String commitMsg4 = "Fourth commit.";
			gitlet("commit", commitMsg4);
			
			//check if master branch's log content is correct
			String masterLogOutput = gitlet("log");
			assertArrayEquals(new String[] {commitMsg4, commitMsg2, commitMsg1, "initial commit"},
					extractCommitMessages(masterLogOutput));
			
			gitlet("checkout", branchName); //switch branch
			
			//new commit for cool-beans branch
			writeFile(a_file, "A is for aardvark");
			gitlet("add", a_file);
			String commitMsg5 = "Fifth commit.";
			gitlet("commit", commitMsg5); 
			
			//new commit for cool-beans branch
			writeFile(a_file, "A is for airplane");
			gitlet("add", a_file);
			String commitMsg6 = "Sixth commit.";
			gitlet("commit", commitMsg6); 
			
			//check if cool-beans' log content is correct
			String coolBeansLogOutput = gitlet("log");
			assertArrayEquals(new String[] {commitMsg6, commitMsg5, commitMsg3, commitMsg2, commitMsg1, "initial commit"},
					extractCommitMessages(coolBeansLogOutput));
		}
		
		/**
		 * Test that merge will generate a .conflicted file if a file
		 * has been modified in both branches since the split point.
		 * 
		 * Sixth required test.
		 */
		@Test
		public void testConflictedMerge() {
			gitlet("init");
			
			String fileOne = TESTING_DIR + "one.txt";
			String textOne = "File one of my project.";
			String fileTwo = TESTING_DIR + "two.txt";
			String textTwo = "File two of my project.";
			createFile(fileOne, textOne);
			createFile(fileTwo, textTwo);
			gitlet("add", fileOne);
			gitlet("add", fileTwo);
			gitlet("commit", "Master commit 1"); 		//commit both files
			String branchName = "branch";        //create two branches
			gitlet("branch", branchName);
			gitlet("checkout", branchName);
			writeFile(fileOne, "File one of my project???"); //modify one file from new branch & commit
			gitlet("add", fileOne);
			gitlet("commit", "Branch commit 1");
			gitlet("checkout", "master"); 		//switch branches
			writeFile(fileOne, "File one of my project!!!"); //modify same file from master branch & commit
			gitlet("add", fileOne);
			gitlet("commit", "Master commit 2");
			gitlet("merge", branchName);		//merge
			File file = new File("test_files/one.txt.conflicted");
			assertTrue(file.exists()); 		//idk if this is correct...

		}
		
		/**
		 * Test that merge will commit with files from the other branch if those files had
		 * been modified in the other branch but not in the current branch since the split point.
		 * 
		 * Seventh required test.
		 */
		@Test
		public void testMergeBasic(){
			String fileOne = TESTING_DIR + "one.txt";
			String textOne = "File one of my project.";
			String fileTwo = TESTING_DIR + "two.txt";
			String textTwo = "File two of my project.";
			createFile(fileOne, textOne);
			createFile(fileTwo, textTwo);
			
			gitlet("add", fileOne);
			gitlet("add", fileTwo);
			gitlet("commit", "Master commit 1"); //commit both files
			
			String branchName = "branch";        //create new branch
			gitlet("branch", branchName);
			
			writeFile(fileOne, "one.txt modified");
			gitlet("add", fileOne);
			gitlet("commit", "Master commit 2"); //only One is changed in this branch
			
			gitlet("checkout", branchName);
			
			writeFile(fileTwo, "two.txt modified");
			gitlet("add", fileTwo);
			gitlet("commit", "Branch commit 1"); //Two is changed but One is unchanged in this branch
			
			gitlet("merge", "master"); //merge
			assertEquals(getText(fileOne), "one.txt modified");
			assertEquals(getText(fileTwo), "two.txt modified");
			
		}	
		
		/** 
		 * Test that the output of log after a rebase includes the commit 
		 * messages from both branches involved in the rebase.
		 *  
		 *  Eighth required test.
		 */
		@Test
		public void testLogAfterRebase() {
			gitlet("init");
			String a_file = TESTING_DIR + "a.txt";
			String a_text = "A is for apple.";
			createFile(a_file, a_text);
			
			//commits initial file
			gitlet("add", a_file); // a.txt is now staged
			String commitMsg1 = "First commit.";
			gitlet("commit", commitMsg1); // add and commit file

			//new commit
			writeFile(a_file, "A is for alligator"); 
			gitlet("add", a_file);
			String commitMsg2 = "Second commit.";
			gitlet("commit", commitMsg2); 
			
			//switches to new branch cool-beans
			String branchName = "cool-beans";
			gitlet("branch", branchName);
			gitlet("checkout", branchName);
			
			//new commit
			writeFile(a_file, "A is for air");
			gitlet("add", a_file);
			String commitMsg3 = "Third commit.";
			gitlet("commit", commitMsg3); 
			
			//switches back to master branch
			gitlet("checkout", "master");
			
			//new commit
			writeFile(a_file, "A is for acorn");
			gitlet("add", a_file);
			String commitMsg4 = "Fourth commit.";
			gitlet("commit", commitMsg4);
			
			gitlet("checkout", branchName); //switch branch
			
			//new commit for cool-beans branch
			writeFile(a_file, "A is for aardvark");
			gitlet("add", a_file);
			String commitMsg5 = "Fifth commit.";
			gitlet("commit", commitMsg5); 
			
			//new commit for cool-beans branch
			writeFile(a_file, "A is for airplane");
			gitlet("add", a_file);
			String commitMsg6 = "Sixth commit.";
			gitlet("commit", commitMsg6);
			
			gitlet("rebase", "master");
			String OverallLogOutput = gitlet("log");
			assertArrayEquals(new String[] { commitMsg1, commitMsg2, commitMsg4, commitMsg3, commitMsg5, commitMsg6 },
					extractCommitMessages(OverallLogOutput));
		}
		
		
		/** 
		 * Test that changes in the base branch propagate through the replayed branch during a rebase.
		 * 
		 * Final required test.
		 */
		@Test
		public void testBaseBranchRebase() {
			gitlet("init");
			String a_file = TESTING_DIR + "a.txt";
			String a_text = "A is for apple.";
			createFile(a_file, a_text);
			
			//commits initial file
			gitlet("add", a_file); // a.txt is now staged
			String commitMsg1 = "1";
			gitlet("commit", commitMsg1); // add and commit file

			//new commit
			writeFile(a_file, "A is for alligator"); 
			gitlet("add", a_file);
			String commitMsg2 = "Second commit.";
			gitlet("commit", commitMsg2); 
			
			gitlet("branch", "newBranch"); 
			
			writeFile(a_file, "A is for air");
			gitlet("add", a_file);
			String commitMsg3 = "Third commit.";
			gitlet("commit", commitMsg3); 
			
			gitlet("checkout", "newBranch");
			
			String b_file = TESTING_DIR + "b.txt";
			String b_text = "B is for baby.";
			createFile(b_file, b_text); 
			
//			writeFile(a_file, "A is for acorn");
//			gitlet("add", a_file);
			gitlet("add", b_file);
			String commitMsg4 = "Fourth commit.";
			gitlet("commit", commitMsg4);
			
			gitlet("rebase", "master");
			
			String unchanged_a = gitlet("checkout", "Third commit.");
			assertEquals(getText(a_file), unchanged_a);
			
			//unsure about this test	
		}
		
		@Test
			public void testRebaseMoveTwo() {
				gitlet("init");
				gitlet("add", "a.txt");
				gitlet("commit", "A");
				gitlet("add", "b.txt");
				gitlet("commit", "B");
				gitlet("branch", "newb"); 
				gitlet("add", "c.txt");
				gitlet("commit", "C");
				gitlet("add", "d.txt");
				gitlet("commit", "D");
				gitlet("checkout", "master");
				gitlet("rebase", "newb");
				
				gitlet("status");
				gitlet("log");
				gitlet("global-log");
				//gitlet("checkout", "master");
				gitlet("rebase", "newb");
				
				//
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
		 * @throws IOException 
		 * @throws ClassNotFoundException 
		 */
		private static String gitletFast(String... args) throws ClassNotFoundException, IOException {
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
