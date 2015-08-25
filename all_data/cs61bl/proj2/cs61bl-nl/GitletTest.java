import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * Test case contains many print tests, so they should be unblocked and run one at a time.
 * @author caseyolen
 *
 */
public class GitletTest extends TestCase {

//	 public void testGitlet(){
//		 
//	 new Gitlet();
//	
//	 File test = new File(System.getProperty("user.dir") + "/.gitlet");
//	 assertTrue(Gitlet.getStagedNames(Gitlet.getStagedFiles()).isEmpty());
//	 ArrayList<File> contents = new
//	 ArrayList<File>(Arrays.asList(test.listFiles()));
//	 assertTrue(contents.contains(new File(System.getProperty("user.dir") +
//	 "/.gitlet/commit#0")));
//	
//	 
//	 Gitlet.printBranchLog();
//	 Gitlet.printStatus();
//	 new Gitlet();
//	
//	 Gitlet.removeGitlet();
//	 }
//	
//	 public void testAdd() {
//	 new Gitlet();
//	 File test = new File("File2.txt");
//	 Gitlet.add(test);
//	 assertTrue(Gitlet.getStagedNames(Gitlet.getStagedFiles()).contains(test.getName()));
//	
//	 File test2 = new File("test1.txt");
//	 Gitlet.add(test2);
//	 assertTrue(Gitlet.getStagedNames(Gitlet.getStagedFiles()).contains(test2.getName()));
//	
//	 File test3 = new File("not found");
//	 Gitlet.add(test3);
//	 assertFalse(Gitlet.getStagedNames(Gitlet.getStagedFiles()).contains(test3.getName()));
//	
//	 Gitlet.printStatus();
//	
//	 Gitlet.removeGitlet();
//	 }
//	
//	 public void testRemove(){
//	 new Gitlet();
//	 File test = new File("File2.txt");
//	 Gitlet.add(test);
//	 assertTrue(Gitlet.getStagedNames(Gitlet.getStagedFiles()).contains(test.getName()));
//	
//	 File test2 = new File("test1.txt");
//	 Gitlet.remove(test2.getName());
//	
//	 Gitlet.printStatus();
//	
//	 Gitlet.add(test2);
//	 assertFalse(Gitlet.getStagedNames(Gitlet.getStagedFiles()).contains(test2.getName()));
//	
//	 Gitlet.remove(test.getName());
//	 assertFalse(Gitlet.getStagedNames(Gitlet.getStagedFiles()).contains(test.getName()));
//	
//	
//	 Gitlet.removeGitlet();
//	 }
//
//	public void testCommit() {
//		new Gitlet();
//		File gitlet = new File(System.getProperty("user.dir") + "/.gitlet");
//
//		File test = new File("File2.txt");
//		File test2 = new File("test1.txt");
//		Gitlet.add(test);
//		Gitlet.add(test2);
//
//		Gitlet.printStatus();
//
//		Gitlet.commit("testing successful commit");
//		
//		ArrayList<File> contents = new ArrayList<File>(Arrays.asList(gitlet
//				.listFiles()));
//		assertTrue(contents.contains(new File(System.getProperty("user.dir")
//				+ "/.gitlet/commit#0")));
//		assertTrue(contents.contains(new File(System.getProperty("user.dir")
//				+ "/.gitlet/commit#1")));
//		assertTrue(Gitlet.getStagedNames(Gitlet.getStagedFiles()).isEmpty());
//		ArrayList<File> commit1contents = new ArrayList<File> (Arrays.asList((new File(System.getProperty("user.dir")
//				+ "/.gitlet/commit#1")).listFiles()));
//		assertTrue(commit1contents.contains(new File(System.getProperty("user.dir")
//				+ "/.gitlet/commit#1/File2.txt")));
//		assertTrue(commit1contents.contains(new File(System.getProperty("user.dir")
//				+ "/.gitlet/commit#1/test1.txt")));
//		assertTrue(Gitlet.getMyCommitTree().current() == 1);
//		assertTrue(Gitlet.getMyCommitTree().isCurrentBranch("master"));
//		assertTrue(Gitlet.getMyCommitTree().size() == 2);
//		
//		Gitlet.commit("no files have been staged");
//		
//		Gitlet.printGlobalLog();
//		Gitlet.removeGitlet();
//		
//		new Gitlet();
//			File test3 = new File("File2.txt");
//			File test4 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("testing multiple commits1");
//		Gitlet.add(test4);
//		Gitlet.commit("testing multiple commits2");
//		assertTrue(Gitlet.getMyCommitTree().current() == 2);
//		assertTrue(Gitlet.getMyCommitTree().isCurrentBranch("master"));
//		assertTrue(Gitlet.getMyCommitTree().size() == 3);
//		
//		Gitlet.printGlobalLog();
//		Gitlet.removeGitlet();
//		
//	}
//	
//	public void testCheckout(){
//		new Gitlet();
//
//		File test = new File("File2.txt");
//		File test2 = new File("test1.txt");
//		
//		Gitlet.add(test);
//		Gitlet.add(test2);
//		Gitlet.commit("test commit");
//		
//		ArrayList<File> workingdirectory = new ArrayList<File>(Arrays.asList((new File(System.getProperty("user.dir"))).listFiles()));
//		File committedFiles = new File(System.getProperty("user.dir") + "/.gitlet/commit#1");
//		
//		//delete original files from working directory
//		for (File f : workingdirectory){
//			if (f.getName().equals("File2.txt") || f.getName().equals("test1.txt")){
//				f.delete();
//			}
//		}
//		
//		for (File f :  workingdirectory){
//			assertTrue(!(f.getName().equals("File2.txt") || f.getName().equals("test1.txt")));
//		}
//		
//		//test checking out by file name
//		Gitlet.checkOutByName("File2.txt");
//		Gitlet.checkOutByName("test1.txt");
//		
//		assertTrue(workingdirectory.contains(new File(System.getProperty("user.dir")
//				+ "/File2.txt")));
//		assertTrue(workingdirectory.contains(new File(System.getProperty("user.dir")
//				+ "/test1.txt")));
//				
//		Gitlet.removeGitlet();
//	}
//	
//	public void testFind(){
//		new Gitlet();
//		File test = new File("File2.txt");
//		File test2 = new File("test1.txt");
//		
//		Gitlet.add(test);
//		Gitlet.add(test2);
//		Gitlet.commit("test commit");
//		Gitlet.find("test commit");
//		Gitlet.find("initial commit");
//		
//		
//		Gitlet.printBranchLog();
//		
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("test multiple commit");
//		Gitlet.find("test commit");
//		Gitlet.find("initial commit");
//		Gitlet.find("test multiple commit");
//		
//		Gitlet.printBranchLog();
//		
//		Gitlet.removeGitlet();
//	}
//	
//public void testBranchandRemoveBranch(){
//	new Gitlet();
//	File test = new File("File2.txt");
//	File test2 = new File("test1.txt");
//	File test3 = new File("test1.txt");
//	File test4 = new File("test1.txt");
//	
//	Gitlet.add(test2);
//	Gitlet.commit("test");
//	Gitlet.add(test);
//	Gitlet.commit("test2");
//	Gitlet.makeBranch("new branch");
//	
//	Gitlet.printStatus();
//	Gitlet.printGlobalLog();
//	
//	Gitlet.add(test3);
//	Gitlet.checkOutByBranch("new branch");
//	
//	Gitlet.printStatus();
//	
//	Gitlet.commit("test new Branch");
//	Gitlet.printStatus();
//	Gitlet.printBranchLog();
//	
//	//test checkout by branch name
//	ArrayList<File> workingdirectory = new ArrayList<File>(Arrays.asList((new File(System.getProperty("user.dir"))).listFiles()));
//	Gitlet.checkOutByBranch("master");
//	assertTrue(workingdirectory.contains(new File(System.getProperty("user.dir")
//			+ "/File2.txt")));
//	assertTrue(workingdirectory.contains(new File(System.getProperty("user.dir")
//			+ "/test1.txt")));
//	
//	//test checkout from commit ID
//	
//	
//	Gitlet.add(test3);
//	Gitlet.commit("test other new Branch");
//	
//	Gitlet.printStatus();
//	Gitlet.printBranchLog();
//	
//	Gitlet.removeBranch("master");
//	Gitlet.removeBranch("new branch");
//	
//	Gitlet.printStatus();
//	
//	Gitlet.removeGitlet();
//}
	
//	public void test2(){
//		new Gitlet();
//		File test1 = new File("test1.txt");
//		Gitlet.add(test1);
//		Gitlet.commit("first commit");
		
//		File test2 = new File("test1.txt");
//		Gitlet.add(test2);
//		Gitlet.commit("second commit");
//		
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("third commit");
//		Gitlet.reset(2);
		
//		Gitlet.printBranchLog();
//		Gitlet.removeGitlet();
//		
//	}
//	
//	public void test3(){
//		new Gitlet();
//		
//		File test1 = new File("test1.txt");
//		Gitlet.add(test1);
//		Gitlet.commit("a");
//		
//		Gitlet.makeBranch("yo");
//		Gitlet.checkOutByBranch("yo");
//		File test2 = new File("test1.txt");
//		Gitlet.add(test2);
//		Gitlet.commit("b");
//		Gitlet.checkOutByBranch("master");	
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("c");
//		Gitlet.find("b");
//		Gitlet.checkOut(2, "test1.txt");
//		
//		Gitlet.printGlobalLog();
//		Gitlet.removeGitlet();
//	}
//	
//	public void testRebase1(){
//		new Gitlet();
//		
//		File test1 = new File("test1.txt");
//		FileWriter writer1;
//		FileWriter writer2;
//		FileWriter writer3;
//		try {
//			writer1 = new FileWriter(test1);
//			writer1.write("a");
//			writer1.close();
//		} catch (IOException e) {
//			System.out.println("fail1");
//			return;
//		}
//		
//		Gitlet.add(test1);
//		Gitlet.commit("first");
//		
//		File test2 = new File("test1.txt");
//		Gitlet.add(test2);
//		Gitlet.commit("second");
//		
//		Gitlet.makeBranch("branch");
//		Gitlet.checkOutByBranch("branch");
//		try {
//			writer2 = new FileWriter(test1);
//			writer2.write("asdb");
//			writer2.close();
//		} catch (IOException e) {
//			System.out.println("fail");
//			return;
//		}
//
//		
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("third");
//		
//		Gitlet.checkOutByBranch("master");
//		File test4 = new File("test2.txt");
//		Gitlet.add(test4);
//		Gitlet.commit("fourth");
////		Gitlet.printBranchLog();
//		
//
//		File test5 = new File("test1.txt");
//		
//		try {
//			writer3 = new FileWriter(test5);
//			writer3.write("hi");
//			writer3.close();
//		} catch (IOException e) {
//			System.out.println("fail");
//			return;
//		}
//		
//		Gitlet.add(test5);
//		Gitlet.commit("fifth");
////		Gitlet.printBranchLog();
//		Gitlet.reset(5);
//	Gitlet.rebase("branch");
//		Gitlet.printBranchLog();
//		Gitlet.printStatus();
////
////		Gitlet.removeGitlet();
//	}

//	public void testRebase2(){
//		
//		new Gitlet();
//		
//		File test1 = new File("test1.txt");
//		FileWriter writer1;
//		FileWriter writer2;
//		FileWriter writer3;
//		try {
//			writer1 = new FileWriter(test1);
//			writer1.write("a");
//			writer1.close();
//		} catch (IOException e) {
//			System.out.println("fail1");
//			return;
//		}
//		
//		Gitlet.add(test1);
//		Gitlet.commit("first");
//		
//		File test2 = new File("test1.txt");
//		Gitlet.add(test2);
//		Gitlet.commit("second");
//		
//		Gitlet.makeBranch("branch");
//		
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("third");
//		
//		//Should print the three error messages;
//		Gitlet.rebase("master");
//		Gitlet.rebase("branch");
//		Gitlet.rebase("branc");
//		
//		Gitlet.removeGitlet();
//		
//		
//	}
//	
//	public void testRebase3(){
//		new Gitlet();
//		
//		File test1 = new File("test1.txt");
//		FileWriter writer1;
//		FileWriter writer2;
//		FileWriter writer3;
//		try {
//			writer1 = new FileWriter(test1);
//			writer1.write("a");
//			writer1.close();
//		} catch (IOException e) {
//			System.out.println("fail1");
//			return;
//		}
//		
//		Gitlet.add(test1);
//		Gitlet.commit("first");
//		
//		File test2 = new File("test1.txt");
//		Gitlet.add(test2);
//		Gitlet.commit("second");
//		
//		Gitlet.makeBranch("branch");
//		
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("third");
//		
//		Gitlet.checkOutByBranch("branch");
//		Gitlet.printBranchLog();
//		Gitlet.printGlobalLog();
//		Gitlet.printStatus();
//		
//		Gitlet.rebase("master");
//		Gitlet.printBranchLog();
//		Gitlet.printGlobalLog();
//		
//		Gitlet.removeGitlet();
//		
//	}
//	
//	public void testMerge(){
//		
//		new Gitlet();
//		File test1 = new File("test1.txt");
//		FileWriter writer1;
//		FileWriter writer2;
//		FileWriter writer3;
//		try {
//			writer1 = new FileWriter(test1);
//			writer1.write("a");
//			writer1.close();
//		} catch (IOException e) {
//			System.out.println("fail1");
//			return;
//		}
//		
//		Gitlet.add(test1);
//		Gitlet.commit("first");
//		
//		File test2 = new File("test1.txt");
//		Gitlet.add(test2);
//		Gitlet.commit("second");
//	
//		Gitlet.makeBranch("branch");
//		File test4 = new File("test1.txt");
//		Gitlet.add(test4);
//		Gitlet.commit("second");
//		File test5 = new File("test1.txt");
//		Gitlet.add(test5);
//		Gitlet.commit("second");
//		
//		Gitlet.checkOutByBranch("branch");
//		
//		try {
//			writer2 = new FileWriter(test1);
//			writer2.write("asdb");
//			writer2.close();
//		} catch (IOException e) {
//			System.out.println("fail");
//			return;
//		}
//		
//
//		File test3 = new File("test1.txt");
//		Gitlet.add(test3);
//		Gitlet.commit("second");
//		
//
//		File test7 = new File("test1.txt");
//		Gitlet.add(test7);
//		Gitlet.commit("second");
//		
//		Gitlet.merge("master");
		
//		Gitlet.removeGitlet();
		
//
//	}
	
//	public void testMerge2(){
//		
//		//create second branch
//		if(new File(".gitlet").exists()){
//			Gitlet.removeGitlet();
//			new Gitlet();
//		}else{
//			new Gitlet();
//		}		
//		try {
//			File file1=new File("hi.txt");
//			File file2=new File("yo.txt");
//			FileWriter f=new FileWriter(file1);
//			FileWriter f2=new FileWriter(file2);
//			f.write("hi");
//			f2.write("yo");
//			f2.flush();
//			f.flush();
//			Gitlet.add(file1);
//			Gitlet.commit("commit 1");
//			Gitlet.add(file2);
//			Gitlet.remove(file1.toString());
//			
//
//			
//			Gitlet.commit("commit 2");
//			f.write(".");
//			f2.write(".");
//			f2.flush();
//			f.flush();
//			Gitlet.add(file1);
//			Gitlet.add(file2);
//			Gitlet.commit("commit 3");
//			
//			//create a new branch
//			Gitlet.makeBranch("new branch");
//			Gitlet.checkOutByBranch("new branch");
//			Gitlet.printStatus();
//// just uncomment the code after each test case to test it 
//// each one is independent
//			//test case 1 
//			//		hi.txt is only modified in the given branch 
//			// 		add a new file foo.txt in commtit 4
//			//expected result.
//				//commit6:
//				//      hi.txt  (hi.A)
//				//      foo.txt (foo)
//				//		yo.txt  (yo.)
////				File file3=new File("foo.txt");
////				FileWriter f3=new FileWriter(file3);
////				f3.write("foo");
////				f3.flush();
////				f.write(" A");       
////				f.flush();
////				Gitlet.add(file1);
////				Gitlet.add(file3);
////				Gitlet.commit("commit 4");
////				
////				Gitlet.checkOutByBranch("master");
////				Gitlet.add(file2);
////				Gitlet.commit("commit 5");
////				Gitlet.merge("new branch");
//				
//			//test case:
//			//		2 file yo.txt is modified in both branch
//			//expected result.
//			//		ask user to create a new commit manually
//				
//
////				f2.write("C");  
////				f2.flush();
////				Gitlet.add(file2);
////				Gitlet.commit("commit 4");
////				
////				Gitlet.checkOutByBranch("master");
////				f2.write("B");
////				f2.flush();
////				Gitlet.add(file2);
////				Gitlet.commit("commit 5");
////			
////				Gitlet.merge("new branch");
////				Gitlet.reset(3);
//			//test case 3 
//			//		file hi.txt is only modified in the curren branch
//			//expected result.
//					//commmit 6
//					//     hi.txt ( hi.A )
//					//     yo.txt( yo. )
////				f.write(" A");
////				f.flush();
////				Gitlet.add(file1);
////				Gitlet.commit("commit 4"); // commit 4 is exactly the same as commit 3
////				
////				Gitlet.checkOutByBranch("master");
////				f.write("A");
////				f.flush();   // commit 5 now has a modified version of hi.txt (hi.A)
////				Gitlet.add(file2);
////				Gitlet.commit("commit 5");
////				
////				Gitlet.merge("new branch");
//				
//			
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
////	public void testEquals(){
////		assertEquals(FileProcess.equals(new File(Gitlet.commitFile+"3/hi.txt"),new File(Gitlet.commitFile+"5/hi.txt")),true);
////		System.out.println(FileProcess.equals(new File(Gitlet.commitFile+"3/foo.txt"),new File(Gitlet.commitFile+"5/foo.txt")));
////	}
	
	
	
	
}
