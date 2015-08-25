import static org.junit.Assert.*;

import java.nio.file.FileSystems;

import org.junit.Test;

public class FileTreeTest {

	@Test
	public void testAdd() {
		FileTree t = new FileTree();
		t.add(FileSystems.getDefault().getPath(".gitlet", "file"), 13);
		assertEquals((int)(t.getID(".gitlet/file")), 13);
		assertEquals((int)t.getID("NO exist"), -1);
	}
	
	@Test
	public void testRemove() {
		FileTree t = new FileTree();
		t.add(FileSystems.getDefault().getPath(".gitlet", "file"), 13);
		t.remove(FileSystems.getDefault().getPath(".gitlet", "file"));
		assertEquals((int)(t.getID(".gitlet/file")), -1);
	}
	
	@Test
	public void testRealPath() {
		FileTree t = new FileTree();
		t.add(FileSystems.getDefault().getPath(".gitlet", "file"), 13);
		assertEquals((t.getRealPath(".gitlet/file")), FileSystems.getDefault().getPath(".gitlet", "AllFiles","13"));
	}

}
