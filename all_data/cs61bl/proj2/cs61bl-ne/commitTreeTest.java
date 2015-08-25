import java.util.*;
import java.io.*;

import junit.framework.TestCase;


public class commitTreeTest extends TestCase {
	
	CommitTree tree = new CommitTree();
	ArrayList<File> snapshot = new ArrayList<File>();

	
//	public void testConstructor(){
//		assertTrue(tree.size() == 1);
//		assertTrue(tree.contains(0));
//		assertTrue(tree.getSnapshot(0) == null);
//		
//		//Should print out Commit ID, timestamp, and "initial commit"
//		System.out.println("Test 0: Empty tree constructor");
//		tree.printLog(true);
//		
//		//Should print out branch name with a * to indicate it is the current branch
//		System.out.println("Test 1: Initial branch status");
//		tree.printBranchStatus();
//		
//	}
//	
//	public void testAdd(){
//		snapshot.add(new File("/test1"));
//		snapshot.add(new File("/File2"));
//		
//		tree.addCommit(snapshot, "first commit");
//		assertTrue(tree.size() == 2);
//		assertTrue(tree.contains(0) && tree.contains(1));
//		assertTrue(tree.getSnapshot(0) == null);
//		assertTrue(tree.current() == 1);
//		assertTrue(tree.getBranches().containsKey("master"));
//		assertTrue(tree.getCommits().get(1) == tree.getBranches().get("master"));
//		
//		//Should print global log with two commits
//		System.out.println("Test 3: Adding one commit");
//		tree.printLog(true);
//		
//		//Should print files in Commit 1
//		System.out.println("Test 4: Files in commit");
//		System.out.println(tree.getSnapshot(1));
//	}
	
//	public void testSplitPoint(){
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		
//		tree.addCommit(snapshot, "b");
//		
//		tree.addCommit(snapshot, "b");
//		tree.branch("branch");
//		tree.setCurrentBranch("branch");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		tree.setCurrentBranch("master");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		tree.addCommit(snapshot, "b");
//		
//		System.out.println(tree.findSplitPoint("branch", "master"));
//		
//		
//		
//	}
	
}
