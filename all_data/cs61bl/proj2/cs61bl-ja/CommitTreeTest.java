import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.*;

public class CommitTreeTest {
    @Test
    public void testConstructor() {
        CommitTree tree = new CommitTree();
        assertEquals(1, tree.size());
        assertEquals("Initial commit.", tree.commitAtBranch("master").getMessage());
        assertEquals(1, tree.branchNames().size());
    }

    @Test
    public void testAddCommitAndBranch() {
        CommitTree tree = new CommitTree();
        Set<Path> commit1Files = new HashSet<>();
        commit1Files.add(Paths.get("files/cool.thing"));
        tree.addCommit("master", "Made a thing.", commit1Files, Collections.emptySet());
        assertEquals(2, tree.size());
        assertEquals("Made a thing.", tree.commitAtBranch("master").getMessage());
        tree.addBranch("totallyrad", tree.commitAtBranch("master"));
        Set<Path> radFiles = new HashSet<>();
        radFiles.add(Paths.get("files/radical.thing"));
        tree.addCommit("totallyrad", "Made something extra rad.", radFiles, Collections.emptySet());
        assertEquals("Made a thing.", tree.commitAtBranch("master").getMessage());
        assertEquals("Made something extra rad.", tree.commitAtBranch("totallyrad").getMessage());
        try {
            tree.addCommit("totallyrad", "Did nothing.", Collections.emptySet(), Collections.emptySet());
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("No changes to commit", ex.getMessage());
        }
        Set<Path> commit2Files = new HashSet<>();
        commit2Files.add(Paths.get("files/cooler.thing"));
        tree.addCommit("master", "Made something else even cooler", commit2Files, Collections.emptySet());
        Set<Path> union12 = new HashSet<>(commit1Files);
        union12.addAll(commit2Files);
        assertEquals(union12, tree.commitAtBranch("master").getFiles());
        assertEquals(3, tree.commitAtBranch("master").getId()); // failure should not increase the ID counter
        tree.addCommit("master", "The first cool thing is now passe.", Collections.emptySet(), commit1Files);
        assertEquals(commit2Files, tree.commitAtBranch("master").getFiles());
        List<Integer> ids = new ArrayList<>();
    }

    @Test
    public void testIterator() {
        CommitTree tree = new CommitTree();
        Set<Path> commit1Files = new HashSet<>();
        commit1Files.add(Paths.get("files/cool.thing"));
        tree.addCommit("master", "Made a thing.", commit1Files, Collections.emptySet());
        tree.addBranch("totallyrad", tree.commitAtBranch("master"));
        Set<Path> radFiles = new HashSet<>();
        radFiles.add(Paths.get("files/radical.thing"));
        tree.addCommit("totallyrad", "Made something extra rad.", radFiles, Collections.emptySet());
        Set<Path> commit2Files = new HashSet<>();
        commit2Files.add(Paths.get("files/cooler.thing"));
        tree.addCommit("master", "Made something else even cooler", commit2Files, Collections.emptySet());
        Set<Path> union12 = new HashSet<>(commit1Files);
        union12.addAll(commit2Files);
        tree.addCommit("master", "The first cool thing is now passe.", Collections.emptySet(), commit1Files);
        List<Integer> ids = new ArrayList<>();
        ids.add(0);
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        for (CommitTree.Commit c : tree)
            ids.remove(new Integer(c.getId()));
        assertEquals(Collections.emptyList(), ids);
    }

    @Test
    public void testHasDescendant() {
        CommitTree tree = new CommitTree();
        Set<Path> commit1Files = new HashSet<>();
        commit1Files.add(Paths.get("files/cool.thing"));
        CommitTree.Commit c1 = tree.addCommit("master", "Made a thing.", commit1Files, Collections.emptySet());
        tree.addBranch("totallyrad", tree.commitAtBranch("master"));
        Set<Path> radFiles = new HashSet<>();
        radFiles.add(Paths.get("files/radical.thing"));
        CommitTree.Commit c2 = tree.addCommit("totallyrad", "Made something extra rad.", radFiles, Collections.emptySet());
        Set<Path> commit2Files = new HashSet<>();
        commit2Files.add(Paths.get("files/cooler.thing"));
        CommitTree.Commit c3 = tree.addCommit("master", "Made something else even cooler", commit2Files, Collections.emptySet());
        CommitTree.Commit c4 = tree.addCommit("master", "The first cool thing is now passe.", Collections.emptySet(), commit1Files);
        assertTrue(c1.hasDescendant(c1)); // maybe not great, but it is convenient for findCommonAncestor
        assertTrue(c1.hasDescendant(c4));
        assertTrue(c1.hasDescendant(c2));
        assertFalse(c2.hasDescendant(c3));
        assertFalse(c4.hasDescendant(c3));
    }

    @Test
    public void testFindCommonAncestor() {
        CommitTree tree = new CommitTree();
        Set<Path> commit1Files = new HashSet<>();
        commit1Files.add(Paths.get("files/cool.thing"));
        CommitTree.Commit c1 = tree.addCommit("master", "Made a thing.", commit1Files, Collections.emptySet());
        tree.addBranch("totallyrad", tree.commitAtBranch("master"));
        Set<Path> radFiles = new HashSet<>();
        radFiles.add(Paths.get("files/radical.thing"));
        CommitTree.Commit c2 = tree.addCommit("totallyrad", "Made something extra rad.", radFiles, Collections.emptySet());
        Set<Path> commit2Files = new HashSet<>();
        commit2Files.add(Paths.get("files/cooler.thing"));
        CommitTree.Commit c3 = tree.addCommit("master", "Made something else even cooler", commit2Files, Collections.emptySet());
        CommitTree.Commit c4 = tree.addCommit("master", "The first cool thing is now passe.", Collections.emptySet(), commit1Files);
        assertSame(c1, c2.findCommonAncestor(c4));
        assertSame(c3, c3.findCommonAncestor(c4));
        assertSame(c3, c4.findCommonAncestor(c3));
        assertSame(c4, c4.findCommonAncestor(c4));
    }
}