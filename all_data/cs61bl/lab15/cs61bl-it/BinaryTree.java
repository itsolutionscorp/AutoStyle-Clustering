import java.util.ArrayList;

public class BinaryTree {

	private TreeNode myRoot;

	public BinaryTree() {
		myRoot = null;
	}

	public BinaryTree(TreeNode t) {
		myRoot = t;
	}

	// Print the values in the tree in preorder: root value first,
	// then values in the left subtree (in preorder), then values
	// in the right subtree (in preorder).
	public void printPreorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printPreorder();
			System.out.println();
		}
	}

	// Print the values in the tree in inorder: values in the left
	// subtree first (in inorder), then the root value, then values
	// in the right subtree (in inorder).
	public void printInorder() {
		if (myRoot == null) {
			System.out.println("(empty tree)");
		} else {
			myRoot.printInorder();
			System.out.println();
		}
	}

	public void print() {
		if (myRoot != null) {
			myRoot.print(0);
		}
	}

	public void fillSampleTree1() {
		myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
	}

	public void fillSampleTree2() {
		myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
				new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
	}

	public void fillSampleTree3() {
		myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C",
				new TreeNode("D", new TreeNode("E"), new TreeNode("F")), null));
	}

	public void fillSampleTree4() {
		myRoot = new TreeNode("A", new TreeNode("B", new TreeNode("C"),
				new TreeNode("D")), new TreeNode("E", new TreeNode("F"),
				new TreeNode("G")));
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		print(t, "the empty tree");
		t.print();
		System.out.println(t.check());
		t.fillSampleTree1();
		t.print();
		System.out.println(t.check());
		print(t, "sample tree 1");
		t.fillSampleTree2();
		t.print();
		System.out.println(t.check());
		print(t, "sample tree 2");
		t.fillSampleTree3();
		t.print();
		System.out.println(t.check());
		print(t, "sample tree 3");
		t.fillSampleTree4();
		t.print();
		System.out.println(t.check());
		print(t, "sample tree 4");
		t.isCompletelyBalanced();
		t = fibTree(5);
		print(t, "sample tree fib");
		t.print();
		t.isCompletelyBalanced();
		
		String S= "((a+(5*(9+1)))+(6*5))";
		t=exprTree(S);
		t.print();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		t.optimize();
		t.print();
		
	}

	private static void print(BinaryTree t, String description) {
		System.out.println(description + " in preorder");
		t.printPreorder();
		System.out.println(description + " in inorder");
		t.printInorder();
		System.out.println();
	}

	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}

	public boolean isCompletelyBalanced() {
		if (myRoot != null) {
			return myRoot.isCompletelyBalanced();
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	private ArrayList alreadySeen;

	@SuppressWarnings("rawtypes")
	public boolean check() {
		alreadySeen = new ArrayList();
		try {
			isOK(myRoot);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	private void isOK(TreeNode t) throws IllegalStateException {
		if (t != null) {
			if (t == myRoot) {
				alreadySeen.add(t.myItem);
				if (t.myLeft != null) {
					isOK(t.myLeft);
				}
				if (t.myRight != null) {
					isOK(t.myRight);
				}
				System.out.println(alreadySeen);
			} else {
				if (alreadySeen.contains(t.myItem)) {
					throw new IllegalStateException(
							"your tree contains a dublicate node...you suck");
				} else {
					alreadySeen.add(t.myItem);
					if (t.myLeft != null) {
						isOK(t.myLeft);
					}
					if (t.myRight != null) {
						isOK(t.myRight);
					}
				}
			}

		}

	}

	public static BinaryTree fibTree(int n) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.fibTreeHelper(n);
		return result;
	}

	private TreeNode fibTreeHelper(int n) {
		if (n == 0) {
			TreeNode node = new TreeNode(0);

			return node;
		} else if (n == 1) {
			TreeNode node = new TreeNode(1);
			return node;
		} else {

			TreeNode node = new TreeNode((int) fibTreeHelper(n - 1).myItem
					+ (int) fibTreeHelper(n - 2).myItem, fibTreeHelper(n - 1),
					fibTreeHelper(n - 2));
			return node;
		}
	}

	public static BinaryTree exprTree(String s) {
		BinaryTree result = new BinaryTree();
		result.myRoot = result.exprTreeHelper(s);
		return result;
	}

	// Return the tree corresponding to the given arithmetic expression.
	// The expression is legal, fully parenthesized, contains no blanks,
	// and involves only the operations + and *.
	private TreeNode exprTreeHelper(String expr) {
		if (expr.charAt(0) != '(') {
			/*
			 * base case on digit ecpr
			 */
			if (!expr.contains("+") || !expr.contains("*")) {
				return new TreeNode(expr);
			}
			int opPos2 = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if ((expr.charAt(k) == '+' || expr.charAt(k) == '*')
						&& opPos == 0) {
					opPos = k;
					if (expr.charAt(opPos+ 1) == '(') {
						break;
					}
				}
				if ((expr.charAt(k) == '+' || expr.charAt(k) == '*')
						&& opPos != 0) {
					opPos2 = k;
				}
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
				TreeNode ret = new TreeNode(op, exprTreeHelper(opnd1),
						exprTreeHelper(opnd2));
				return ret;

		} else {
			// expr is a parenthesized expression.
			// Strip off the beginning and ending parentheses,
			// find the main operator (an occurrence of + or * not nested
			// in parentheses, and construct the two subtrees.
			int nesting = 0;
			int opPos = 0;
			for (int k = 1; k < expr.length() - 1; k++) {
				if (expr.charAt(k) == '(') {
					nesting++;
				} else if (expr.charAt(k) == ')') {
					nesting--;
				}
				if (nesting == 0
						&& (expr.charAt(k) == '+' || expr.charAt(k) == '*')) {
					opPos = k;
				}
			}
			String opnd1 = expr.substring(1, opPos);
			String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
			String op = expr.substring(opPos, opPos + 1);
			System.out.println("expression = " + expr);
			System.out.println("operand 1  = " + opnd1);
			System.out.println("operator   = " + op);
			System.out.println("operand 2  = " + opnd2);
			System.out.println();

			TreeNode ret = new TreeNode(op, exprTreeHelper(opnd1),
					exprTreeHelper(opnd2));
			return ret;
		}
	}

	public void optimize() {
		if (myRoot!=null){
			optimizeHelper(myRoot);
		}
	}
	public TreeNode optimizeHelper(TreeNode Root){
		       if (Root== myRoot){
		    	   TreeNode L= optimizeHelper(Root.myLeft);
		    	   TreeNode R= optimizeHelper(Root.myRight);
		    	   boolean Lisnum = false;
		    	   boolean Risnum = false;
		    	   try{
		    		   int num = Integer.parseInt((String)L.myItem);
		    		   Lisnum= true;
		    		 } catch (NumberFormatException e) {
		    		   // not an integer!
		    		 }
		    	   try{
		    		   int num = Integer.parseInt(R.myItem.toString());
		    		   Risnum= true;
		    		 } catch (NumberFormatException e) {
		    		   // not an integer!
		    		 }
		    	   
		    	   if (Risnum && Lisnum ){
		    		   if (Root.myItem== "*"){
		    			   Root.myItem= ""+ (Integer.parseInt((String)L.myItem) *  Integer.parseInt((String)R.myItem));
		    			   Root.myLeft= null;
		    			   Root.myRight= null;
		    		   }else{
		    			   Root.myItem= ""+ (Integer.parseInt((String)L.myItem) +  Integer.parseInt((String)R.myItem));
		    			   Root.myLeft= null;
		    			   Root.myRight= null;
		    			   
		    		   } 
		    	   }
		    	   return Root;
		    	   
		       }else{
		    	   if (Root.myItem.equals("*")|| Root.myItem.equals("+")){
		    		   boolean Lisnum = false;
			    	   boolean Risnum = false;
		    		   TreeNode L= optimizeHelper(Root.myLeft);
			    	   TreeNode R= optimizeHelper(Root.myRight);
			    	   try{
			    		   int num = Integer.parseInt((String)L.myItem);
			    		   Lisnum= true;
			    		 } catch (NumberFormatException e) {
			    		   // not an integer!
			    		 }
			    	   try{
			    		   int num = Integer.parseInt(R.myItem.toString());
			    		   Risnum= true;
			    		 } catch (NumberFormatException e) {
			    		   // not an integer!
			    		 }
			    	   
			    	   if (Risnum && Lisnum ){
			    		   if (Root.myItem.equals("*")){
			    			   Root.myItem= ""+ (Integer.parseInt((String)L.myItem) *  Integer.parseInt((String)R.myItem));
			    			   Root.myLeft= null;
			    			   Root.myRight= null;
			    		   }else{
			    			   Root.myItem= ""+ (Integer.parseInt((String)L.myItem) +  Integer.parseInt((String)R.myItem));
			    			   
			    			   Root.myRight= null;
			    			   Root.myLeft= null;
			    		   } 
			    	   }
			    	   return Root;  
		    	   }else{
		    		   return Root;  
		    	   }
		       }
	}
	
	
	
	private static class TreeNode {
		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;

		public TreeNode(Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}

		public TreeNode(Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
			myLeft = left;
			myRight = right;
		}

		private void printPreorder() {
			System.out.print(myItem + " ");
			if (myLeft != null) {
				myLeft.printPreorder();
			}
			if (myRight != null) {
				myRight.printPreorder();
			}
		}

		private void printInorder() {
			if (myLeft != null) {
				myLeft.printInorder();
			}
			System.out.print(myItem + " ");
			if (myRight != null) {
				myRight.printInorder();
			}
		}

		public int height() {
			if (myLeft == null && myRight == null) {
				return 1;
			} else {
				int bestSoFar;
				if (myLeft != null && myRight == null) {
					bestSoFar = (myLeft.height() + 1);
				} else if (myLeft == null && myRight != null) {
					bestSoFar = (myRight.height() + 1);
				} else {
					bestSoFar = Math.max(myLeft.height() + 1,
							myRight.height() + 1);
				}
				return bestSoFar;
			}
		}

		public boolean isCompletelyBalanced() {
			if ((myLeft == null && myRight == null)) {
				return true;
			} else if ((myLeft != null && myRight == null)
					|| (myLeft == null && myRight != null)) {
				return false;
			} else {
				return (myLeft.isCompletelyBalanced() && myRight
						.isCompletelyBalanced());

			}

		}

		private static final String indent1 = "    ";

		private void print(int indent) {
			if (myRight != null) {
				myRight.print(indent + 1);

			}
			println(myItem, indent);
			if (myLeft != null) {
				myLeft.print(indent + 1);
			}

		}

		private static void println(Object obj, int indent) {
			for (int k = 0; k < indent; k++) {
				System.out.print(indent1);
			}
			System.out.println(obj);
		}

	}
}
