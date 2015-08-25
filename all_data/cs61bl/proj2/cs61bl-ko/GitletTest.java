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
	 * Tests that reset works on a basic level
	 */
	
	@Test
	public void testBasicReset() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "changed wug to this");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug");
		assertEquals("changed wug to this", getText(wugFileName));
		gitlet("reset", "1");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("status");
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
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testCheckoutInheritance() {
		String test1Name = TESTING_DIR + "test1.txt";
		String test2Name = TESTING_DIR + "test2.txt";
		String test3Name = TESTING_DIR + "test3.txt";
		createFile(test1Name, "test 1 original");
		createFile(test2Name, "test 2 original");
		createFile(test3Name, "test 3 original");

		gitlet("init");
		gitlet("add", test1Name);
		gitlet("rm", test1Name);
		gitlet("add", test1Name);
		gitlet("add", test2Name);
		gitlet("add", test3Name);
		gitlet("commit", "First");
		writeFile(test1Name, "Test 1 has been changed.");
		
		gitlet("add", test1Name);
		gitlet("add", test2Name);
		gitlet("commit", "Second");
		writeFile(test1Name, "Test 1 has been changed TWICE NOW.");
		writeFile(test2Name, "Test 2 has been changed.");
		writeFile(test3Name, "Test 3 has been changed.");

		gitlet("checkout", "1", test1Name);
		assertEquals("test 1 original", getText(test1Name));
		gitlet("checkout", test2Name);
		assertEquals("test 2 original", getText(test2Name));
		gitlet("checkout", test3Name);
		assertEquals("test 3 original", getText(test3Name)); //checks if test 3 inherited
	}
	
	/**
	 * Checks remove in a pretty basic fashion
	 */
	@Test
	public void testBasicRemove() {
		String Cola = TESTING_DIR + "Cola.txt";
		createFile(Cola, "My");
		gitlet("init");
		gitlet("add", Cola);
		gitlet("commit", "committed Cola");
		gitlet("rm", Cola);
		gitlet("commit", "removed Cola");
		writeFile(Cola, "Anything else  for Cola...");
		gitlet("checkout", "2", Cola);
		assertEquals(getText(Cola), "Anything else  for Cola...");
	}
	
	/**
	 * Checks multiple remove statements in a row
	 */
	@Test
	public void testMultipleRemove() {
		String Cola = TESTING_DIR + "Cola.txt";
		String Danse = TESTING_DIR + "Danse.txt";
		String Ride = TESTING_DIR + "Ride.txt";
		createFile(Cola, "My");
		createFile(Danse, "Oh");
		createFile(Ride, "I");
		gitlet("init");
		gitlet("add", Cola);
		gitlet("add", Danse);
		gitlet("add", Ride);
		gitlet("commit", "committed all three");
		gitlet("rm", Cola);
		gitlet("rm", Danse);
		gitlet("commit", "removed all but Ride");
		writeFile(Cola, "Anything else  for Cola...");
		writeFile(Danse, "Something for Danse");
		writeFile(Ride, "This shouldn't be reflected");
		gitlet("reset", "2");
		assertEquals(getText(Cola), "Anything else  for Cola...");
		assertEquals(getText(Danse), "Something for Danse");
		assertEquals(getText(Ride), "I");
	}
	
	/**
	 * Checks the core functionality of checkout
	 */
	
	@Test
	public void testMediumCheckout() {
		String Apparently = TESTING_DIR + "Apparently.txt";
		String SheKnows = TESTING_DIR + "SheKnows.txt";
		String LoveYourz = TESTING_DIR + "LoveYourz.txt";
		String PowerTrip = TESTING_DIR + "PowerTrip.txt";
		String CrookedSmile = TESTING_DIR + "CrookedSmile.txt";
		String NoRoleModelz = TESTING_DIR + "NoRoleModelz.txt";
		createFile(Apparently, "I keep my head high");
		createFile(SheKnows, "Damned if I do");
		createFile(LoveYourz, "No such thing");
		createFile(PowerTrip, "Got me up all night");
		createFile(CrookedSmile, "They tell me I should fix my grill");
		createFile(NoRoleModelz, "First things first");
		
		gitlet("init");
		gitlet("add", Apparently);
		gitlet("add", SheKnows);
		gitlet("commit", "A & SK"); // commit ID = 1
		gitlet("add", LoveYourz);
		writeFile(SheKnows, "Damned if I don't");
		gitlet("add", SheKnows);
		gitlet("commit", "LY & SK2"); // commit ID = 2
		gitlet("add", PowerTrip);
		gitlet("add", CrookedSmile);
		gitlet("commit", "PT & CS"); // commit ID = 3
		gitlet("add", NoRoleModelz);
		writeFile(Apparently, "I got my wings to carry me");
		writeFile(PowerTrip, "Constant drinking and love songs");
		gitlet("add", PowerTrip);
		gitlet("add", Apparently);
		gitlet("commit", "NRM"); // commitID = 4
		gitlet("checkout", "1", Apparently);
		assertEquals("I keep my head high", getText(Apparently));
		gitlet("checkout", "3", PowerTrip);
		assertEquals("Got me up all night", getText(PowerTrip));
		gitlet("checkout", CrookedSmile);
		assertEquals("They tell me I should fix my grill", getText(CrookedSmile));
		gitlet("checkout", LoveYourz);
		assertEquals("No such thing", getText(LoveYourz));
	}
	
	/**
	 * Tests if checking out from another branch updates the working directory
	 */
	
	@Test
	public void testCheckoutFromOtherBranch() {
		String Apparently = TESTING_DIR + "Apparently.txt";
		String SheKnows = TESTING_DIR + "SheKnows.txt";
		String LoveYourz = TESTING_DIR + "LoveYourz.txt";
		String PowerTrip = TESTING_DIR + "PowerTrip.txt";
		String NoRoleModelz = TESTING_DIR + "NoRoleModelz.txt";
		createFile(Apparently, "I keep my head high");
		createFile(SheKnows, "Damned if I do");
		createFile(LoveYourz, "No such thing");
		createFile(PowerTrip, "Got me up all night");
		createFile(NoRoleModelz, "First things first");
		
		gitlet("init");
		gitlet("branch", "JCole");
		gitlet("add", Apparently);
		gitlet("add", SheKnows);
		gitlet("commit", "first");
		gitlet("checkout", "JCole");
		gitlet("add", LoveYourz);
		gitlet("add", NoRoleModelz);
		gitlet("commit", "second");
		gitlet("checkout", "1", Apparently);
		assertEquals("I keep my head high", getText(Apparently));
		gitlet("checkout", "1", SheKnows);
		assertEquals("Damned if I do", getText(SheKnows));
		gitlet("checkout", "master");
		gitlet("checkout", "2", PowerTrip); // should return "file does not exist"
		assertEquals("Got me up all night", getText(PowerTrip)); // should still pass anyway
		gitlet("checkout", "2", LoveYourz);
		assertEquals("No such thing", getText(LoveYourz));
		gitlet("checkout", "2", NoRoleModelz);
		assertEquals("First things first", getText(NoRoleModelz));
	}
	
	/**
	 * Tests if checking out correctly affects the log
	 */
	
	@Test
	public void testCheckoutLog() {
		String Apparently = TESTING_DIR + "Apparently.txt";
		String SheKnows = TESTING_DIR + "SheKnows.txt";
		String LoveYourz = TESTING_DIR + "LoveYourz.txt";
		String PowerTrip = TESTING_DIR + "PowerTrip.txt";
		String NoRoleModelz = TESTING_DIR + "NoRoleModelz.txt";
		createFile(Apparently, "I keep my head high");
		createFile(SheKnows, "Damned if I do");
		createFile(LoveYourz, "No such thing");
		createFile(PowerTrip, "Got me up all night");
		createFile(NoRoleModelz, "First things first");
		
		gitlet("init");
		gitlet("branch", "JCole");
		gitlet("add", Apparently);
		gitlet("add", SheKnows);
		gitlet("commit", "first");
		gitlet("log");
		gitlet("checkout", "JCole");
		gitlet("add", LoveYourz);
		gitlet("add", NoRoleModelz);
		gitlet("commit", "second");
		gitlet("log");
		gitlet("global-log");
	}
	
	/**
	 * tests log changes appropriately when you reset
	 */
	@Test
	public void testResetLog() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "changed wug to this");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug");
		assertEquals("changed wug to this", getText(wugFileName));
		gitlet("log");
		// log should display commits 2, 1, 0

		writeFile(wugFileName, "changed wug AGAIN");
		gitlet("commit", "changed wug");

		gitlet("reset", "1");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("log");
		//now log should only display commits 1 and 0
		
		gitlet("global-log");
		//global log should display commits 0, 1, 2
	}
	
	/**
	 * tests log changes appropriately when you change branches
	 */
	@Test
	public void testBranchLog() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("branch", "coolbeans");
		
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "changed wug to this");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug");
		assertEquals("changed wug to this", getText(wugFileName));

		gitlet("log");
		//log should display commits 2, 1, 0
		
		
		gitlet("checkout", "coolbeans");

		writeFile(wugFileName, "changed wug to coolbeans version");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug");
		
		gitlet("log");
		//now log should only display commits 3 and 0 on coolbeans
		

		gitlet("checkout", "master");
		gitlet("log");
		//now log should only display commits 2, 1, 0 again from master
		
		gitlet("global-log");
		//global log should display commits 0, 1, 2, 3
	}
	
	/**
	 * Checks merge to see how it behaves if it is conflicted
	 */
	@Test
	public void testBasicMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "coolbeanz");
		String newWug = TESTING_DIR + "newwug.txt";
		String newWugText = "Something comPLETEly different.";
		createFile(newWug, newWugText);
		gitlet("add", newWug);
		gitlet("commit", "added newwug");
		gitlet("checkout", "coolbeanz");
		gitlet("status");
		writeFile(newWug, "Newest wug");
		gitlet("add", newWug);
		gitlet("commit", "coolbeanz path");
		gitlet("merge", "WHATEV");
		gitlet("merge", "coolbeanz");
		gitlet("checkout", "coolbeanz");
		gitlet("checkout", "master");
		gitlet("status");
		gitlet("merge", "coolbeanz");
		assertEquals(newWugText, getText(newWug));
		assertEquals(wugText, getText(wugFileName));
		assertEquals("Newest wug", getText(TESTING_DIR + "newwug.txt.conflicted"));
	}
	
	/**
	 * Tests to see if merge can deal with removes and check the files out fine
	 */
	
	@Test
		public void testMergeNewFilesNoConflict() {
			String test1Name = TESTING_DIR + "test1.txt";
			String test2Name = TESTING_DIR + "test2.txt";
			String test3Name = TESTING_DIR + "test3.txt";
			createFile(test1Name, "test 1 original");
			createFile(test2Name, "test 2 original");
			createFile(test3Name, "test 3 original");
			
			gitlet("init");
			gitlet("add", test1Name);
			gitlet("commit", "first"); //1 commit test1original
			
			gitlet("branch", "coolbeans"); //checkout coolbeans
			gitlet("add", test2Name);
			gitlet("rm", test1Name);
			gitlet("rm", test1Name);
			gitlet("commit", "second"); //2 commit test2original
			
			gitlet("checkout", "coolbeans");
			gitlet("add", test3Name);
			gitlet("rm", test2Name);
			gitlet("rm", test2Name);
			gitlet("commit", "third"); //3 commit test3original, test2orig
			
			gitlet("checkout", "master"); //checkout master
			gitlet("merge", "coolbeans");
			writeFile(test1Name, "Test 1 has been changed.");
			writeFile(test2Name, "Test 2 has been changed.");
			writeFile(test3Name, "Test 3 has been changed.");
			gitlet("reset", "4");
			assertEquals("Test 1 has been changed.", getText(test1Name));
			assertEquals("test 2 original", getText(test2Name));
			assertEquals("test 3 original", getText(test3Name));

		}

	/**
	 * Tests to see if merging without conflicts can reflect changed files well.
	 */
	
	@Test
	public void testMergeNoConflict() {
		String test1Name = TESTING_DIR + "test1.txt";
		String test2Name = TESTING_DIR + "test2.txt";
		String test3Name = TESTING_DIR + "test3.txt";
		createFile(test1Name, "test 1 original");
		createFile(test2Name, "test 2 original");
		createFile(test3Name, "test 3 original");
		
		gitlet("init");
		gitlet("add", test1Name);
		gitlet("add", test2Name);
		gitlet("add", test3Name);

		gitlet("commit", "first"); //1 commit test1original
		
		gitlet("branch", "coolbeans"); //checkout coolbeans
		gitlet("add", test2Name);
		gitlet("commit", "second"); //2 commit test2original
		
		gitlet("checkout", "coolbeans");
		String test4Name = TESTING_DIR + "test4.txt";
		createFile(test4Name, "test 4 original");
		gitlet("add", test4Name);
		gitlet("rm", test1Name);
		gitlet("rm", test2Name);
		gitlet("rm", test1Name);
		gitlet("rm", test2Name);
		gitlet("commit", "third"); //3 commit removes 1 & 2
		
		gitlet("checkout", "master"); //checkout master
		writeFile(test1Name, "Test 1 has been changed.");
		writeFile(test2Name, "Test 2 has been changed.");
		writeFile(test3Name, "Test 3 has been changed.");
		assertEquals("Test 1 has been changed.", getText(test1Name));
		assertEquals("Test 2 has been changed.", getText(test2Name));
		assertEquals("Test 3 has been changed.", getText(test3Name));

		gitlet("merge", "coolbeans");
		writeFile(test4Name, "Hehehe, I shouldn't be here");
		gitlet("reset", "4");
		assertEquals("Test 1 has been changed.", getText(test1Name));
		assertEquals("Test 2 has been changed.", getText(test2Name));
		assertEquals("test 3 original", getText(test3Name));
		assertEquals("test 4 original", getText(test4Name));

	}
	
	/**
	 * Tests if our merge can handle a conflict correctly.
	 */
	@Test
		public void testConflictMerge() {
			String testFile = TESTING_DIR + "test.txt";
			String testText = "Testing 1 2 3.";
			createFile(testFile, testText);
			
			gitlet("init");
			gitlet("add", testFile);
			gitlet("commit", "added test");
			
			gitlet("branch", "coolbeans");
			
			String test2 = TESTING_DIR + "test2.txt";
			String test2Text = "This is test2.";
			createFile(test2, test2Text);
			
			gitlet("add", test2);
			gitlet("commit", "added test2");
			
			gitlet("checkout", "coolbeans");
			
			writeFile(test2, "This should not be here!");
			gitlet("add", test2);
			gitlet("commit", "test2 coolbeans");
			
			gitlet("merge", "coolbeans");
			gitlet("checkout", "master");
			gitlet("merge", "coolbeans");
			gitlet("status");
			assertEquals(testText, getText(testFile));
			assertEquals(test2Text, getText(test2));
			assertEquals("This should not be here!", getText(TESTING_DIR + "test2.txt.conflicted"));
		}
		
		/**
		 * Tests for the merging special cases by removing in the master branch
		 */
	
		@Test
		public void testSpecialCase() {
			String testFile = TESTING_DIR + "test.txt";
			String testText = "Testing 1 2 3.";
			createFile(testFile, testText);
			
			gitlet("init");
			gitlet("add", testFile);
			gitlet("commit", "added test");
			
			gitlet("branch", "coolbeans");
			
			gitlet("rm", testFile);
			gitlet("commit", "removed test from master");
			
			gitlet("checkout", "coolbeans");
			
			writeFile(testFile, "This is changed in coolbeans.");
			gitlet("add", testFile);
			gitlet("commit", "test changed in coolbeans");
			
			gitlet("checkout", "master");
			gitlet("merge", "coolbeans");
			gitlet("reset", "4");
			assertEquals("This is changed in coolbeans.", getText(TESTING_DIR + "test.txt"));
		}
		
		/**
		 * Tests for the merging special cases by removing in the given branch
		 */
		
		@Test
		public void testSpecialCase2() {
			String testFile = TESTING_DIR + "test.txt";
			String testText = "Testing 1 2 3.";
			createFile(testFile, testText);
			
			gitlet("init");
			gitlet("add", testFile);
			gitlet("commit", "added test");
			
			gitlet("branch", "coolbeans");
			
			writeFile(testFile, "This is changed in master.");
			gitlet("add", testFile);
			gitlet("commit", "Changed in master");
			
			gitlet("checkout", "coolbeans");
			gitlet("rm", testFile);
			gitlet("commit", "test is untracked in coolbeans");
			
			gitlet("checkout", "master");
			gitlet("merge", "coolbeans");
			gitlet("reset", "4");
			assertEquals("This is changed in master.", getText(TESTING_DIR + "test.txt"));
		}

	/**
	 * A basic test for rebase, checks for all new commits made with new IDs and that the
	 * log changes correctly
	 */
	@Test
	public void testBasicRebase() {
		String Cola = TESTING_DIR + "Cola.txt";
		String Danse = TESTING_DIR + "Danse.txt";
		String Ride = TESTING_DIR + "Ride.txt";
		createFile(Cola, "My");
		createFile(Danse, "Oh");
		createFile(Ride, "I");
		gitlet("init");
		gitlet("add", Cola);
		gitlet("add", Ride);
		gitlet("commit", "two lana songs");
		gitlet("branch", "french");
		writeFile(Cola, "My kisses");
		gitlet("add", Cola);
		gitlet("commit", "changed Cola");
		writeFile(Cola, "My kisses taste");
		writeFile(Ride, "I been");
		gitlet("add", Cola);
		gitlet("add", Ride);
		gitlet("commit", "changed both Cola and Ride");
		
		gitlet("checkout", "french");
		gitlet("add", Danse);
		gitlet("commit", "added a french song, no french in the split, but it's in new commit");
		writeFile(Danse, "Oh ma");
		gitlet("add", Danse);
		gitlet("commit", "changed Danse");
		writeFile(Ride, "I been out");
		gitlet("add", Ride);
		gitlet("commit", "changed Ride");
		gitlet("checkout", "master");
		gitlet("log");
		gitlet("rebase", "french");
		
		assertEquals("Oh ma", getText(Danse));
		assertEquals("My kisses taste", getText(Cola));
		assertEquals("I been", getText(Ride));
		gitlet("log");
		gitlet("global-log");
	}
	
	/**
	 * Tests rebasing when the current branch is on the same
	 * branch history as the given branch.
	 */
	@Test
	public void testSpecialRebase() {
		String Cola = TESTING_DIR + "Cola.txt";
		String Danse = TESTING_DIR + "Danse.txt";
		String Ride = TESTING_DIR + "Ride.txt";
		createFile(Cola, "My");
		createFile(Danse, "Oh");
		createFile(Ride, "I");
		gitlet("init");
		gitlet("add", Cola);
		gitlet("add", Ride);
		gitlet("commit", "two lana songs");
		gitlet("branch", "french");
		writeFile(Cola, "My kisses");
		gitlet("add", Cola);
		gitlet("commit", "changed Cola");
		writeFile(Cola, "My kisses taste like");
		writeFile(Ride, "I been out");
		gitlet("add", Cola);
		gitlet("add", Ride);
		gitlet("commit", "changed both Cola and Ride");
		gitlet("checkout", "french");
		gitlet("log");
		gitlet("rebase", "master");
		gitlet("checkout", "master");
		
		assertEquals("Oh", getText(Danse));
		assertEquals("My kisses taste like", getText(Cola));
		assertEquals("I been out", getText(Ride));
		gitlet("log");
		gitlet("global-log");
	}
	
	/**
	 * A deeper test for rebase, checks for all new commits made with new IDs. Makes 
	 * sure all replayed commits have propagated changes.
	 */
	@Test
	public void testDeepRebasePropagation() {
		String Cola = TESTING_DIR + "Cola.txt";
		String Danse = TESTING_DIR + "Danse.txt";
		String Ride = TESTING_DIR + "Ride.txt";
		String Rider = TESTING_DIR + "Rider.txt";
		String fakeCola = TESTING_DIR + "fakeCola.txt";
		createFile(Cola, "My");
		createFile(Danse, "Oh");
		createFile(Danse, "Oh");
		createFile(Ride, "I");
		createFile(Rider, "Dying");
		createFile(fakeCola, "Drugs");
		gitlet("init");
		gitlet("add", Cola);
		gitlet("add", fakeCola);
		gitlet("add", Ride);
		gitlet("commit", "two lana songs");
		gitlet("branch", "french");
		
		gitlet("checkout", "french");
		gitlet("add", Danse);
		gitlet("commit", "added a french song, no french in the split, but it's in new commit");
		writeFile(Danse, "Oh ma");
		gitlet("add", Danse);
		gitlet("commit", "changed Danse");
		writeFile(Ride, "I been out");
		gitlet("add", Ride);
		gitlet("commit", "changed Ride");
		writeFile(Rider, "Dying young and");
		writeFile(Ride, "I been out on that open road");
		writeFile(Danse, "Oh ma douce souffrance");
		writeFile(fakeCola, "Drugs suck em up");
		gitlet("add", Rider);
		gitlet("add", Ride);
		gitlet("add", fakeCola);
		gitlet("commit", "added ride AND rider AND fakeCola");
		
		gitlet("checkout", "master");
		writeFile(Cola, "My kisses");
		gitlet("add", Cola);
		gitlet("commit", "changed Cola");
		writeFile(Cola, "My kisses taste");
		writeFile(Ride, "I been");
		gitlet("add", Cola);
		gitlet("add", Ride);
		gitlet("commit", "changed both Cola and Ride");
		gitlet("rm", Ride);
		gitlet("status");
		gitlet("commit", "removed Ride");
		writeFile(Cola, "My kisses taste like Pepsi Cola");
		gitlet("add", Cola);
		gitlet("commit", "Changed Cola after removing Ride");
		gitlet("add", Rider);
		gitlet("commit", "added Rider");
		
		writeFile(Ride, "You can be my full time baby");
		gitlet("log");
		gitlet("rebase", "french");
		gitlet("checkout", "15", Cola);
		
		assertEquals("Oh ma", getText(Danse));
		assertEquals("My kisses taste like Pepsi Cola", getText(Cola));
		assertEquals("You can be my full time baby", getText(Ride));
		assertEquals("Dying young and", getText(Rider));
		assertEquals("Drugs suck em up", getText(fakeCola));
		gitlet("log");
		gitlet("global-log");
		
		gitlet("reset", "13");
		gitlet("checkout", Ride); //should not exist
		assertEquals("My kisses taste", getText(Cola));
		assertEquals("You can be my full time baby", getText(Ride));
		assertEquals("Drugs suck em up", getText(fakeCola));
		assertEquals("Dying young and", getText(Rider));
		assertEquals("Oh ma", getText(Danse));
		
		gitlet("checkout", "french");
		assertEquals("Dying young and", getText(Rider));
		assertEquals("I been out on that open road", getText(Ride));
		assertEquals("Drugs suck em up", getText(fakeCola));
		assertEquals("Oh ma", getText(Danse));
		assertEquals("My", getText(Cola));
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
}