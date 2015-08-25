public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>
{
	public boolean contains(T key)
	{
		return contains(myRoot, key);
	}
	private boolean contains(TreeNode t, T key)
	{
		if (t == null)
			return false;
		if (t.myItem.equals(key))
			return true;
		if (key.compareTo(t.myItem) > 0)
			return contains(t.myRight, key);
		return contains(t.myLeft, key);
	}
	public void add(T key)
	{
		myRoot = add(myRoot, key);
	}

	private TreeNode add(TreeNode t, T key)
	{
		if (t == null)
		{
			return new TreeNode(key);
		} 
		else if (key.compareTo(t.myItem) < 0)
		{
			t.myLeft = add(t.myLeft, key);
			return t;
		}
		else if (key.equals(t.myItem))
			return t;
		else
		{
			t.myRight = add(t.myRight, key);
			return t;
		}
	}

}
