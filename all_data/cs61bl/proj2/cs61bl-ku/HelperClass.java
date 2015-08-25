import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class HelperClass {

	/**
	 * Recursive function that deletes a directory and all files within it.
	 * 
	 * @param f
	 *            The directory to delete
	 * @return void
	 */
	public static void deleteAll(File f) {
		if (!f.exists()) {
			return;
		}
		if (f.isDirectory()) {
			for (File in : f.listFiles()) {
				deleteAll(in);
			}
		}
		f.delete();
	}

	/**
	 * Checks to see if the contents of two files are equal, contain the same
	 * contents.
	 * 
	 * @param file1
	 *            The first file to compare.
	 * @param file2
	 *            The second file to compare.
	 * @return boolean
	 * @throws IOException
	 *             For input/output exceptions.
	 */
	public static boolean fileEquals(String path1, String path2) {
		File file1 = new File(path1);
		File file2 = new File(path2);
		byte[] f1;
		byte[] f2;
		try {
			f1 = Files.readAllBytes(file1.toPath());
			f2 = Files.readAllBytes(file2.toPath());
			return Arrays.equals(f1, f2);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Find the split node (common ancestor) of two given nodes.
	 * 
	 * @param node1
	 *            First node to use to search.
	 * @param node2
	 *            Second node to use to search.
	 * @return bNode
	 */
	public static bNode splitHelper(bNode node1, bNode node2) {
		ArrayList<Integer> id1 = new ArrayList<Integer>();
		ArrayList<Integer> id2 = new ArrayList<Integer>();
		bNode splitNode = node1;
		while (node1 != null) {
			id1.add(node1.getID());
			node1 = node1.getPrev();
		}
		while (node2 != null) {
			id2.add(node2.getID());
			node2 = node2.getPrev();
		}
		ArrayList<Integer> common = new ArrayList<Integer>(id1);
		common.retainAll(id2);
		Integer max = Collections.max(common);
		while (splitNode.getID() != max) {
			splitNode = splitNode.getPrev();
		}
		return splitNode;
	}

	/**
	 * Finds the modified files between two nodes, usually the head node of a
	 * branch and the split point node.
	 * 
	 * @param node1
	 *            A bNode to compare against.
	 * @param node2
	 *            A bNode to compare against.
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> findModified(bNode node1, bNode node2) {
		HashMap<String, String> modified = new HashMap<String, String>();
		HashMap<String, String> tracked1 = node1.getMyTracked();
		HashMap<String, String> tracked2 = node2.getMyTracked();
		for (String og : tracked1.keySet()) {
			if (tracked2.containsKey(og)) {
				if (!HelperClass.fileEquals(tracked1.get(og), tracked2.get(og))) {
					modified.put(og, tracked1.get(og));
				}
			} else {
				modified.put(og, tracked1.get(og));
			}
		}
		for (String og : tracked2.keySet()) {
			if (!tracked1.containsKey(og)) {
				modified.put(og, tracked2.get(og));
			}
		}
		return modified;
	}

	/**
	 * Checks to see if the input node is in the history of the current node for
	 * the rebase failure case.
	 * 
	 * @param current
	 *            The current node.
	 * @param input
	 *            The input node.
	 * @return boolean
	 */
	public static boolean history(bNode current, bNode input) {
		while (current != null) {
			if (input == current) { // if they both point to the same bNode
									// object
				return true;
			}
			current = current.getPrev();
		}
		return false;
	}

	/**
	 * Returns an ArrayList containing the commit IDs from the split node to the
	 * head (not including the split node).
	 * 
	 * @param head
	 *            The head of the branch.
	 * @param split
	 *            Split node between branches.
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> commitHistory(bNode head, bNode split) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (head.getID() != split.getID()) {
			list.add(head.getID());
			head = head.getPrev();
		}
		Collections.reverse(list);
		return list;
	}

}
